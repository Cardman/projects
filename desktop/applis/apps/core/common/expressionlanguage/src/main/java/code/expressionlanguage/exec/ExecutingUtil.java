package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AnnotationTypeInfo;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.opers.ExecExplicitOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.BoolVal;

public final class ExecutingUtil {
    private ExecutingUtil() {
    }

    static void processException(StackCall _stackCall) {
        if (!_stackCall.isFailInit()) {
            processGeneException(_stackCall);
        }
    }

    public static void processGeneException(StackCall _stackCall) {
        CustomFoundExc callingState_ = _stackCall.trueException();
        if (callingState_ != null) {
            Struct exc_ = callingState_.getStruct();
            LocalThrowing.removeBlockFinally(exc_, _stackCall);
        }
    }

    public static AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stackCall) {
        CallingState callingState_ = _stackCall.getCallingState();
        if (callingState_ != null) {
            return callingState_.processAfterOperation(_context, _stackCall);
        }
        return null;
    }


    public static AbstractPageEl createInstancingClass(NotInitializedClass _e) {
        return createInstancingClass(_e.getRootBlock(),_e.getClassName(),_e.getArgument());
    }
    public static AbstractPageEl createInstancingClass(ExecRootBlock _rootBlock, ExecFormattedRootBlock _class, Struct _fwd) {
//        ExecBlock firstChild_ = _rootBlock.getFirstChild();
        CustList<ExecBlock> visit_ = _rootBlock.getAllStaticMembers();
        StaticInitPageEl page_ = new StaticInitPageEl(visit_,_class);
        Struct argGl_ = NullStruct.NULL_VALUE;
        page_.setFwd(_fwd);
        page_.setGlobalArgument(argGl_);
        page_.setReturnedArgument(argGl_);
        for (ExecStaticBlock i: _rootBlock.getAllStaticInits()) {
            page_.getProcessedBlocks().addEntry(i, BoolVal.FALSE);
        }
        if (!visit_.isEmpty()) {
            page_.setBlock(visit_.first());
        }
//        rw_.setBlock(firstChild_);
        page_.blockRoot(_rootBlock);
//        while (firstChild_ != null) {
//            if (firstChild_ instanceof ExecStaticBlock) {
//                page_.getProcessedBlocks().put((ExecInitBlock) firstChild_, BoolVal.FALSE);
//            }
//            firstChild_ = firstChild_.getNextSibling();
//        }
        page_.setFile(_rootBlock.getFile());
        return page_;
    }

    public static AbstractPageEl createCallingMethod(ContextEl _context, CustomFoundMethod _e) {
        ExecFormattedRootBlock cl_ = _e.getClassName();
        Parameters args_ = _e.getArguments();
        Struct gl_ = _e.getGl();
        return createCallingMethod(_context,gl_, cl_,_e.getPair(), args_);
    }

    public static AbstractPageEl createCallingSwitch(ContextEl _context, CustomFoundSwitch _e) {
        ExecFormattedRootBlock cl_ = _e.getClassName();
        Struct gl_ = _e.getGl();
        CommonSwitchMethodPageEl pageLoc_ = new CommonSwitchMethodPageEl(cl_, _e.getValue());
        if (_e.getSwitchMethod().getKind() == MethodAccessKind.STATIC_CALL) {
            pageLoc_.setGlobalArgument(NullStruct.NULL_VALUE);
        } else {
            pageLoc_.setGlobalArgument(gl_);
        }
        setSwitchInfos(_context, pageLoc_, _e.getSwitchMethod(),_e.getCache());
        return pageLoc_;
    }
    public static AbstractPageEl createCallingMethod(ContextEl _context, Struct _gl, ExecFormattedRootBlock _class, ExecTypeFunction _method, Parameters _args) {
        CommonMethodPageEl pageLoc_ = new CommonMethodPageEl(_class);
        pageLoc_.setGlobalArgument(_gl);
        pageLoc_.initReturnType(_args.getRight());
        setMethodInfos(_context, pageLoc_, _method, _args);
        return pageLoc_;
    }

    private static void setMethodInfos(ContextEl _context, CommonMethodPageEl _page, ExecTypeFunction _block, Parameters _args) {
        ExecNamedFunctionBlock fct_ = _block.getFct();
        _page.blockRootType(_block);
        _context.getCoverage().passCalls(_page);
        _page.getRefParams().addAllEntries(_args.getRefParameters());
        _page.setCache(_args.getCache());
        _page.setBlock(fct_.getFirstChild());
        _page.setFile(fct_.getFile());
    }
    private static void setSwitchInfos(ContextEl _context, CommonSwitchMethodPageEl _page, ExecAbstractSwitchMethod _block, Cache _cache) {
        _page.setBlockRoot(_block);
        _context.getCoverage().passCalls(_page);
        _page.setCache(_cache);
        _page.setFile(_block.getFile());
        _page.setBlock(_block);
    }
    public static AbstractPageEl createRecordInstancing(ContextEl _context, CustomFoundRecordConstructor _e) {
        ExecFormattedRootBlock cl_ = _e.getClassName();
        CustList<Struct> args_ = _e.getArguments();
        NewRecordPageEl page_ = new NewRecordPageEl(_e.getNamedFields(),args_,cl_, _e.getList());
        Struct str_ = _e.getArgument();
        String fieldName_ = _e.getFieldName();
        int ordinal_ = _e.getChildIndex();
        Struct argGl_ = _context.getInit().processInit(_context, str_, cl_, fieldName_, ordinal_);
        page_.setGlobalArgument(argGl_);
        page_.setReturnedArgument(argGl_);
        page_.blockRootTypes(_e.getPair());
        return page_;
    }
    public static AbstractPageEl createInstancing(ContextEl _context, CustomFoundConstructor _e) {
        AbstractCallingInstancingPageEl page_ = buildCtor(_e);
        Parameters args_ = _e.getArguments();
        Struct global_ = _e.getCurrentObject();
        page_.setGlobalArgument(global_);
        page_.setReturnedArgument(global_);
        setInstanciationInfos(_context,page_, args_, _e.getPair());
        return page_;
    }

    private static AbstractCallingInstancingPageEl buildCtor(CustomFoundConstructor _e) {
        boolean implicitConstr_ = false;
        ExecBlock blockRoot_ = _e.getPair().getFct();
        //No constructor found in the current class => call the super one
        //Constructor found in the current class but no explicit call to super in the code => call the super one
        //Constructor found in the current class but no explicit call to super in the code => call the super one
        if (!(blockRoot_ instanceof ExecConstructorBlock) || ((ExecConstructorBlock) blockRoot_).implicitConstr()) {
            //No constructor found in the current class => call the super one
            implicitConstr_ = true;
        }
        ExecFormattedRootBlock className_ = _e.getClassName();
        if (!implicitConstr_) {
            return new DirectCallConstructorPageEl(className_);
        }
        return new CallConstructorPageEl(className_);
    }

    public static NewAnnotationPageEl createAnnotation(ContextEl _context, ExecFormattedRootBlock _class, ExecRootBlock _type,
                                                       StringMap<AnnotationTypeInfo> _id,
                                                       CustList<Struct> _args) {
        NewAnnotationPageEl page_;
        page_ = new NewAnnotationPageEl(_id,_args,_class);
        Struct argGl_ = _context.getInit().processInitAnnot(_context, _class,_type);
        page_.setGlobalArgument(argGl_);
        page_.setReturnedArgument(argGl_);
        page_.fileTypeOffset(_type);
        return page_;
    }

    private static void setInstanciationInfos(ContextEl _context, AbstractCallingInstancingPageEl _page, Parameters _args, ExecTypeFunction _pair) {
        _page.blockRootTypes(_pair);
        ExecMemberCallingsBlock ctor_ = _pair.getFct();
        if (ctor_ != null) {
            _context.getCoverage().passCalls(_page);
            _page.getRefParams().addAllEntries(_args.getRefParameters());
            ExecBlock firstChild_ = ctor_.getFirstChild();
            _page.setBlock(firstChild_);
        }
    }
    public static FieldInitPageEl createInitFields(ExecRootBlock _type, ExecFormattedRootBlock _class, Struct _current) {
        CustList<ExecBlock> visit_ = _type.getAllInstanceMembers();
        FieldInitPageEl page_ = new FieldInitPageEl(visit_,_class);
        page_.setGlobalArgument(_current);
        page_.setReturnedArgument(_current);
        for (ExecInstanceBlock i: _type.getAllInstanceInits()) {
            page_.getProcessedBlocks().addEntry(i, BoolVal.FALSE);
        }
        if (!visit_.isEmpty()) {
            page_.setBlock(visit_.first());
        }
//        ExecBlock firstChild_ = _type.getFirstChild();
//        rw_.setBlock(firstChild_);
//        while (firstChild_ != null) {
//            if (firstChild_ instanceof ExecInstanceBlock) {
//                page_.getProcessedBlocks().put((ExecInitBlock) firstChild_, BoolVal.FALSE);
//            }
//            firstChild_ = firstChild_.getNextSibling();
//        }
        page_.blockRoot(_type);
        page_.setFile(_type.getFile());
        return page_;
    }
    public static BlockPageEl createBlockPageEl(ContextEl _context, ExecFormattedRootBlock _class, Struct _current, ExecInitBlock _block) {
        ExecFileBlock file_ = _block.getFile();
        BlockPageEl page_ = new BlockPageEl(_class);
        page_.setGlobalArgument(_current);
        page_.setReturnedArgument(_current);
        ExecBlock firstChild_ = _block.getFirstChild();
        page_.setBlock(firstChild_);
        page_.setBlockRoot(_block);
        _context.getCoverage().passCalls(page_);
        page_.setFile(file_);
        return page_;
    }

    public static AbstractPageEl createReflectMethod(AbstractReflectElement _ref) {
        AbstractPageEl pageLoc_;
        if (_ref instanceof CustomReflectLambdaConstructor) {
            CustomReflectLambdaConstructor c_ = (CustomReflectLambdaConstructor) _ref;
            Struct args_ = c_.getArgument();
            ArgumentListCall array_ = c_.getArray();
            ConstructorMetaInfo metaInfo_ = c_.getGl();
            pageLoc_ = new ReflectLambdaConstructorPageEl(args_,array_, metaInfo_, c_.getRef());
        } else if (_ref instanceof CustomReflectLambdaConstructorWithoutInfo) {
            pageLoc_ = lambdaCtor((CustomReflectLambdaConstructorWithoutInfo) _ref);
        } else if (_ref instanceof CustomReflectConstructor) {
            CustomReflectConstructor c_ = (CustomReflectConstructor) _ref;
            ConstructorMetaInfo metaInfo_ = c_.getGl();
            pageLoc_ = new ReflectConstructorPageEl(metaInfo_, c_.getArrRef());
        } else if (_ref instanceof CustomReflectRecordConstructor) {
            CustomReflectRecordConstructor c_ = (CustomReflectRecordConstructor) _ref;
            CustList<Struct> args_ = c_.getArguments();
            pageLoc_ = new ReflectRecordConstructorPageEl(args_, c_.getInstance(), c_.getRoot(), c_.getNamedFields(),c_.getClassName(), c_.getInts(), c_.getRef());
        } else if (_ref instanceof CustomReflectGetField) {
            CustomReflectGetField c_ = (CustomReflectGetField) _ref;
            FieldMetaInfo metaInfo_ = c_.getGl();
            pageLoc_ = new ReflectGetFieldPageEl(((CustomReflectGetField) _ref).getIntParentRetriever(),((CustomReflectGetField) _ref).getParent(), metaInfo_, _ref.isLambda());
        } else if (_ref instanceof CustomReflectSetField) {
            CustomReflectSetField c_ = (CustomReflectSetField) _ref;
            FieldMetaInfo metaInfo_ = c_.getGl();
            Struct last_ = c_.getLast();
            pageLoc_ = new ReflectSetFieldPageEl(((CustomReflectSetField) _ref).getIntParentRetriever(),((CustomReflectSetField) _ref).getParent(), last_, metaInfo_, _ref.isLambda());
        } else if (_ref instanceof CustomReflectLambdaToStr) {
            CustomReflectLambdaToStr c_ = (CustomReflectLambdaToStr) _ref;
            pageLoc_ = new LambdaToStrRefectMethodPageEl(c_.getArgument());
        } else if (_ref instanceof CustomReflectLambdaRdCod) {
            CustomReflectLambdaRdCod c_ = (CustomReflectLambdaRdCod) _ref;
            pageLoc_ = new LambdaRdCodRefectMethodPageEl(c_.getArgument());
        } else if (_ref instanceof CustomReflectLambdaVarMethod) {
            pageLoc_ = lambdaMethVar((CustomReflectLambdaVarMethod) _ref);
        } else if (_ref instanceof CustomReflectLambdaFieldWithoutInfo) {
            pageLoc_ = lambdaField((CustomReflectLambdaFieldWithoutInfo) _ref);
        } else if (_ref instanceof CustomReflectLambdaMethodWithoutInfo) {
            pageLoc_ = lambdaMeth((CustomReflectLambdaMethodWithoutInfo) _ref);
        } else if (_ref instanceof CustomReflectLambdaQuick) {
            pageLoc_ = lambdaQuick((CustomReflectLambdaQuick) _ref);
        } else if (_ref instanceof CustomAbstractReflectMethod) {
            pageLoc_ = reflectMethod((CustomAbstractReflectMethod) _ref);
        } else {
            CustomReflectAnnotations c_ = (CustomReflectAnnotations) _ref;
            ReflectingType reflect_ = c_.getReflect();
            CustList<Struct> args_ = c_.getArguments();
            pageLoc_ = new ReflectAnnotationPageEl(args_, c_.getGl());
            setFile(pageLoc_, c_.getGl());
            ((ReflectAnnotationPageEl)pageLoc_).setOnParameters(reflect_);
        }
        return pageLoc_;
    }

    private static void setFile(AbstractPageEl _pageLoc, AnnotatedStruct _b) {
        ExecBlock bl_ = _b.getBl();
        if (bl_ != null) {
            _pageLoc.setFile(bl_.getFile());
        }
    }

    private static AbstractPageEl reflectMethod(CustomAbstractReflectMethod _ref) {
        if (_ref instanceof CustomReflectMethod) {
            return reflectMethod((CustomReflectMethod) _ref);
        }
        if (_ref instanceof CustomReflectLambdaMethod) {
            return lambdaMeth((CustomReflectLambdaMethod) _ref);
        }
        CustomReflectMethodDefVal c_ = (CustomReflectMethodDefVal) _ref;
        MethodMetaInfo metaInfo_ = c_.getGl();
        ReflectGetDefaultValuePageEl pageLoc_ = new ReflectGetDefaultValuePageEl(metaInfo_);
        setFile(pageLoc_, metaInfo_);
        return pageLoc_;
    }
    private static AbstractPageEl reflectMethod(CustomReflectMethod _ref) {
        ReflectingType reflect_ = _ref.getReflect();
        AbstractPageEl pageLoc_;
        MethodMetaInfo metaInfo_ = _ref.getGl();
        AbstractPageEl refMet_;
        Struct instance_ = _ref.getInstance();
        ArrayRefState a_ = _ref.getArrRef();
        if (reflect_ == ReflectingType.METHOD) {
            refMet_ = new PolymorphRefectMethodPageEl(instance_, metaInfo_, a_);
        } else if (reflect_ == ReflectingType.DIRECT) {
            refMet_ = new DirectRefectMethodPageEl(instance_, metaInfo_, a_);
        } else if (reflect_ == ReflectingType.STATIC_CALL) {
            refMet_ = new StaticCallMethodPageEl(instance_, metaInfo_, a_);
        } else if (cast(reflect_)) {
            refMet_ = reflectCast(reflect_, metaInfo_, instance_, a_);
        } else if (reflect_ == ReflectingType.STD_FCT) {
            refMet_ = new DirectStdRefectMethodPageEl(instance_, metaInfo_, a_);
        } else if (reflect_ == ReflectingType.CLONE_FCT) {
            refMet_ = new DirectCallRefectMethodPageEl(instance_, metaInfo_, a_, new CloneQuickCall());
        } else if (reflect_ == ReflectingType.ENUM_METHODS) {
            refMet_ = new DirectEnumMethods(instance_, metaInfo_, a_);
        } else {
            refMet_ = new DirectCallRefectMethodPageEl(instance_, metaInfo_, a_, new AnnotationsQuickCall());
        }
        pageLoc_ = refMet_;
        return pageLoc_;
    }

    private static AbstractPageEl lambdaMethVar(CustomReflectLambdaVarMethod _ref) {
        ReflectingType reflect_ = _ref.getReflect();
        AbstractPageEl refMet_;
        ArgumentListCall array_ = _ref.getArray();
        if (reflect_ == ReflectingType.VAR_GET) {
            refMet_ = new LambdaVariableGetValuePageEl(array_);
        } else {
            refMet_ = new LambdaVariableSetValuePageEl(array_);
        }
        return refMet_;
    }
    private static AbstractPageEl lambdaMeth(CustomReflectLambdaMethod _ref) {
        ReflectingType reflect_ = _ref.getReflect();
        AbstractPageEl pageLoc_;
        MethodMetaInfo metaInfo_ = _ref.getGl();
        AbstractPageEl refMet_;
        ArgumentListCall array_ = _ref.getArray();
        LambdaMethodStruct lms_ = _ref.getLambdaMethodStruct();
        int r_ = _ref.getRef();
        if (reflect_ == ReflectingType.METHOD) {
            refMet_ = new LambdaPolymorphRefectMethodPageEl(array_, metaInfo_, r_, lms_);
        } else if (reflect_ == ReflectingType.DIRECT) {
            refMet_ = new LambdaDirectRefectMethodPageEl(array_, metaInfo_, r_, lms_);
        } else if (reflect_ == ReflectingType.STATIC_CALL) {
            refMet_ = new LambdaStaticCallMethodPageEl(array_, metaInfo_, r_, lms_);
        } else if (cast(reflect_)) {
            refMet_ = lambdaCast(reflect_, metaInfo_, array_, r_, lms_);
        } else if (reflect_ == ReflectingType.STD_FCT) {
            refMet_ = new LambdaDirectStdRefectMethodPageEl(array_, metaInfo_, r_, lms_);
        } else if (reflect_ == ReflectingType.CLONE_FCT) {
            refMet_ = new LambdaDirectCloneRefectMethodPageEl(array_, metaInfo_, r_, lms_);
        } else if (reflect_ == ReflectingType.ENUM_METHODS) {
            refMet_ = new LambdaDirectEnumMethods(array_, metaInfo_, r_, lms_);
        } else {
            refMet_ = new LambdaAnnotationRefectMethodPageEl(array_, metaInfo_, r_, lms_);
        }
        pageLoc_ = refMet_;
        return pageLoc_;
    }
    private static AbstractPageEl lambdaMeth(CustomReflectLambdaMethodWithoutInfo _ref) {
        return new LambdaMethodWithoutInfo(_ref.getLambdaMethodStruct(), _ref.getArray());
    }

    private static AbstractPageEl lambdaField(CustomReflectLambdaFieldWithoutInfo _ref) {
        return new LambdaFieldWithoutInfo(_ref.getLambdaFieldStruct(), _ref.getArray());
    }
    private static AbstractPageEl lambdaCtor(CustomReflectLambdaConstructorWithoutInfo _ref) {
        return new LambdaConstructorWithoutInfo(_ref.getLambdaConstructorStruct(), _ref.getArray());
    }

    private static AbstractPageEl lambdaQuick(CustomReflectLambdaQuick _ref) {
        return new LambdaQuick(_ref.getLambda());
    }

    private static AbstractRefectMethodPageEl reflectCast(ReflectingType _reflect, MethodMetaInfo _metaInfo, Struct _instance, ArrayRefState _a) {
        AbstractRefectMethodPageEl refMet_;
        String className_ = _metaInfo.getFormatted().getFormatted();
        if (direct(_reflect == ReflectingType.CAST_DIRECT, _metaInfo.getPair(), className_)) {
            refMet_ = new CastDirectRefectMethodPageEl(_instance, _metaInfo, _a);
        } else {
            refMet_ = new CastIndirectRefectMethodPageEl(_instance, _metaInfo, _a);
        }
        return refMet_;
    }

    private static AbstractRefectLambdaMethodPageEl lambdaCast(ReflectingType _reflect, MethodMetaInfo _metaInfo, ArgumentListCall _array, int _r, LambdaMethodStruct _lms) {
        AbstractRefectLambdaMethodPageEl refMet_;
        String className_ = _metaInfo.getFormatted().getFormatted();
        if (direct(_reflect == ReflectingType.CAST_DIRECT, _metaInfo.getPair(), className_)) {
            refMet_ = new LambdaCastDirectRefectMethodPageEl(_array, _metaInfo, _r, _lms);
        } else {
            refMet_ = new LambdaCastIndirectRefectMethodPageEl(_array, _metaInfo, _r, _lms);
        }
        return refMet_;
    }

    private static boolean cast(ReflectingType _reflect) {
        return _reflect == ReflectingType.CAST || _reflect == ReflectingType.CAST_DIRECT;
    }

    public static void addPage(AbstractPageEl _page, StackCall _stackCall) {
        _stackCall.entryReadWrite();
        _stackCall.addInternPage(_page);
    }

    private static boolean direct(boolean _direct, ExecTypeFunction _castOpId, String _className) {
        return _direct || ExecExplicitOperation.direct(_castOpId, _className);
    }
}
