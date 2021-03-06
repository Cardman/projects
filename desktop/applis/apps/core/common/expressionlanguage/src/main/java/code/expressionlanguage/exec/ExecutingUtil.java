package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class ExecutingUtil {
    private ExecutingUtil() {
    }

    static void processException(ContextEl _context, StackCall _stackCall) {
        if (!_stackCall.getInitializingTypeInfos().isFailInit()) {
            processGeneException(_context, _stackCall);
        }
    }

    public static void processGeneException(ContextEl _context, StackCall _stackCall) {
        CallingState callingState_ = _stackCall.getCallingState();
        if (callingState_ instanceof CustomFoundExc) {
            Struct exc_ = ((CustomFoundExc)callingState_).getStruct();
            LocalThrowing.removeBlockFinally(_context, exc_, _stackCall);
        }
    }

    static void processTagsBase(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        if (!ip_.checkCondition(_context, _stackCall)) {
            return;
        }
        ExecBlock en_ = ip_.getBlock();
        if (en_ != null) {
            ip_.setGlobalOffset(en_.getOffsetTrim());
            ip_.setOffset(0);
        }
        ip_.tryProcessEl(_context, _stackCall);
    }

    static AbstractPageEl processAfterOperation(ContextEl _context, StackCall _stackCall) {
        CallingState callingState_ = _stackCall.getCallingState();
        if (callingState_ != null) {
            return callingState_.processAfterOperation(_context, _stackCall);
        }
        return null;
    }


    public static AbstractPageEl createInstancingClass(NotInitializedClass _e, StackCall _stackCall) {
        return createInstancingClass(_e.getRootBlock(),_e.getClassName(),_e.getArgument(), _stackCall);
    }
    public static AbstractPageEl createInstancingClass(ExecRootBlock _rootBlock, String _class, Argument _fwd, StackCall _stackCall) {
        _stackCall.setCallingState(null);
        ExecBlock firstChild_ = _rootBlock.getFirstChild();
        StaticInitPageEl page_ = new StaticInitPageEl();
        Argument argGl_ = new Argument();
        page_.setGlobalClass(_class);
        page_.setFwd(_fwd);
        page_.setGlobalArgument(argGl_);
        page_.setReturnedArgument(argGl_);
        ReadWrite rw_ = new ReadWrite();
        rw_.setBlock(firstChild_);
        page_.setReadWrite(rw_);
        page_.setBlockRootType(_rootBlock);
        page_.setBlockRoot(_rootBlock);
        while (firstChild_ != null) {
            if (firstChild_ instanceof ExecStaticBlock) {
                page_.getProcessedBlocks().put((ExecInitBlock) firstChild_, BoolVal.FALSE);
            }
            firstChild_ = firstChild_.getNextSibling();
        }
        page_.setFile(_rootBlock.getFile());
        return page_;
    }

    public static AbstractPageEl createCallingMethod(ContextEl _context, CustomFoundMethod _e, StackCall _stackCall) {
        String cl_ = _e.getClassName();
        Parameters args_ = _e.getArguments();
        Argument gl_ = _e.getGl();
        return createCallingMethod(_context,gl_, cl_,_e.getPair(), args_, _stackCall);
    }

    public static AbstractPageEl createCallingSwitch(ContextEl _context, CustomFoundSwitch _e, StackCall _stackCall) {
        String cl_ = _e.getClassName();
        Argument gl_ = _e.getGl();
        _stackCall.setCallingState(null);
        AbstractSwitchMethodPageEl pageLoc_;
        if (_e.getSwitchMethod().getKind() == MethodAccessKind.STATIC_CALL) {
            pageLoc_ = new SwitchCastPageEl(Argument.createVoid(),cl_, _e.getValue());
        } else {
            pageLoc_ = new SwitchMethodPageEl(gl_,cl_, _e.getValue());
        }
        setSwitchInfos(_context, pageLoc_, _e.getType(),_e.getSwitchMethod(),_e.getCache());
        return pageLoc_;
    }
    public static AbstractPageEl createCallingMethod(ContextEl _context, Argument _gl, String _class, ExecTypeFunction _method, Parameters _args, StackCall _stackCall) {
        _stackCall.setCallingState(null);
        MethodPageEl pageLoc_ = new MethodPageEl(_gl,_class);
        pageLoc_.initReturnType(_args.getRight());
        setMethodInfos(_context, pageLoc_, _method, _args);
        return pageLoc_;
    }

    public static AbstractPageEl createCallingCast(ContextEl _context, CustomFoundCast _e, StackCall _stackCall) {
        String cl_ = _e.getClassName();
        Parameters args_ = _e.getArguments();
        return createCallingCast(_context,cl_,_e.getPair(), args_, _stackCall);
    }
    public static AbstractPageEl createCallingCast(ContextEl _context, String _class, ExecTypeFunction _method, Parameters _args, StackCall _stackCall) {
        _stackCall.setCallingState(null);
        CastPageEl pageLoc_ = new CastPageEl(Argument.createVoid(),_class);
        pageLoc_.initReturnType(_args.getRight());
        setMethodInfos(_context, pageLoc_, _method, _args);
        return pageLoc_;
    }
    private static void setMethodInfos(ContextEl _context, AbstractPageEl _page, ExecTypeFunction _block, Parameters _args) {
        ExecNamedFunctionBlock fct_ = _block.getFct();
        ExecRootBlock type_ = _block.getType();
        _page.setBlockRootType(type_);
        _page.setBlockRoot(fct_);
        _context.getCoverage().passCalls(_page);
        for (EntryCust<String, LocalVariable> e: _args.getParameters().entryList()) {
            _page.getRefParams().addEntry(e.getKey(),new VariableWrapper(e.getValue()));
        }
        _page.getRefParams().addAllEntries(_args.getRefParameters());
        _page.setCache(_args.getCache());
        ReadWrite rwLoc_ = new ReadWrite();
        rwLoc_.setBlock(fct_.getFirstChild());
        _page.setReadWrite(rwLoc_);
        _page.setFile(fct_.getFile());
    }
    private static void setSwitchInfos(ContextEl _context, AbstractSwitchMethodPageEl _page, ExecRootBlock _type, ExecAbstractSwitchMethod _block, Cache _cache) {
        _page.setBlockRootType(_type);
        _page.setBlockRoot(_block);
        _context.getCoverage().passCalls(_page);
        _page.setCache(_cache);
        _page.setFile(_block.getFile());
        ReadWrite rwLoc_ = new ReadWrite();
        rwLoc_.setBlock(_block);
        _page.setReadWrite(rwLoc_);
    }
    public static AbstractPageEl createRecordInstancing(ContextEl _context, CustomFoundRecordConstructor _e, StackCall _stackCall) {
        String cl_ = _e.getClassName();
        ExecRootBlock type_ = _e.getPair().getType();
        CustList<Argument> args_ = _e.getArguments();
        _stackCall.setCallingState(null);
        NewRecordPageEl page_ = new NewRecordPageEl(_e.getId(),args_);
        Struct str_ = NullStruct.NULL_VALUE;
        String fieldName_ = _e.getFieldName();
        int ordinal_ = _e.getChildIndex();
        Argument argGl_ = new Argument(_context.getInit().processInit(_context, str_, cl_,type_, fieldName_, ordinal_));
        page_.setGlobalArgument(argGl_);
        page_.setReturnedArgument(argGl_);
        ExecFileBlock file_ = type_.getFile();
        page_.setGlobalClass(cl_);
        ReadWrite rw_ = new ReadWrite();
        page_.setBlockRootTypes(type_);
        ExecMemberCallingsBlock ctor_ = _e.getPair().getFct();
        page_.setBlockRoot(ctor_);
        page_.setReadWrite(rw_);
        page_.setFile(file_);
        return page_;
    }
    public static AbstractPageEl createInstancing(ContextEl _context, CustomFoundConstructor _e, StackCall _stackCall) {
        InstancingStep in_ = _e.getInstanceStep();
        if (in_ == InstancingStep.NEWING) {
            return createNewInstancing(_context,_e, _stackCall);
        }
        return createForwardingInstancing(_context,_e, _stackCall);
    }
    public static CallConstructorPageEl createNewInstancing(ContextEl _context, CustomFoundConstructor _e, StackCall _stackCall) {
        String cl_ = _e.getClassName();
        ExecRootBlock type_ = _e.getPair().getType();
        Parameters args_ = _e.getArguments();
        _stackCall.setCallingState(null);
        Argument global_ = _e.getCurrentObject();
        CallConstructorPageEl page_ = new CallConstructorPageEl();
        Struct str_ = NullStruct.NULL_VALUE;
        if (global_ != null) {
            str_ = global_.getStruct();
        }
        String fieldName_ = _e.getFieldName();
        int ordinal_ = _e.getChildIndex();
        Argument argGl_ = new Argument(_context.getInit().processInit(_context, str_, cl_,type_, fieldName_, ordinal_));
        page_.setGlobalArgument(argGl_);
        page_.setReturnedArgument(argGl_);
        setInstanciationInfos(_context,page_,cl_, args_, _e.getPair());
        return page_;
    }

    public static NewAnnotationPageEl createAnnotation(ContextEl _context, String _class, ExecRootBlock _type,
                                                       StringMap<AnnotationTypeInfo> _id,
                                                       CustList<Argument> _args, StackCall _stackCall) {
        _stackCall.setCallingState(null);
        NewAnnotationPageEl page_;
        ExecFileBlock file_ = _type.getFile();
        page_ = new NewAnnotationPageEl(_id,_args);
        Argument argGl_ = new Argument(_context.getInit().processInitAnnot(_context, _class,_type));
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(argGl_);
        page_.setReturnedArgument(argGl_);
        ReadWrite rw_ = new ReadWrite();
        page_.setReadWrite(rw_);
        page_.setFile(file_);
        page_.setBlockRootTypes(_type);
        return page_;
    }
    private static AbstractPageEl createForwardingInstancing(ContextEl _context, CustomFoundConstructor _e, StackCall _stackCall) {
        _stackCall.setCallingState(null);
        CallConstructorPageEl page_ = new CallConstructorPageEl();
        String cl_ = _e.getClassName();
        Parameters args_ = _e.getArguments();
        Argument global_ = _e.getCurrentObject();
        page_.setGlobalArgument(global_);
        page_.setReturnedArgument(global_);
        setInstanciationInfos(_context,page_,cl_, args_, _e.getPair());
        return page_;
    }

    private static void setInstanciationInfos(ContextEl _context, AbstractCallingInstancingPageEl _page, String _class, Parameters _args, ExecTypeFunction _pair) {
        ExecRootBlock type_ = _pair.getType();
        ExecFileBlock file_ = type_.getFile();
        _page.setGlobalClass(_class);
        ReadWrite rw_ = new ReadWrite();
        _page.setBlockRootTypes(type_);
        ExecMemberCallingsBlock ctor_ = _pair.getFct();
        _page.setBlockRoot(ctor_);
        if (ctor_ != null) {
            _context.getCoverage().passCalls(_page);
            for (EntryCust<String, LocalVariable> e: _args.getParameters().entryList()) {
                _page.getRefParams().addEntry(e.getKey(),new VariableWrapper(e.getValue()));
            }
            _page.getRefParams().addAllEntries(_args.getRefParameters());
            ExecBlock firstChild_ = ctor_.getFirstChild();
            rw_.setBlock(firstChild_);
        }
        _page.setReadWrite(rw_);
        _page.setFile(file_);
    }
    public static FieldInitPageEl createInitFields(ExecRootBlock _type, String _class, Argument _current, StackCall _stackCall) {
        _stackCall.setCallingState(null);
        FieldInitPageEl page_ = new FieldInitPageEl();
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(_current);
        page_.setReturnedArgument(_current);
        page_.setBlockRootType(_type);
        ReadWrite rw_ = new ReadWrite();
        ExecBlock firstChild_ = _type.getFirstChild();
        rw_.setBlock(firstChild_);
        while (firstChild_ != null) {
            if (firstChild_ instanceof ExecInstanceBlock) {
                page_.getProcessedBlocks().put((ExecInitBlock) firstChild_, BoolVal.FALSE);
            }
            firstChild_ = firstChild_.getNextSibling();
        }
        page_.setReadWrite(rw_);
        page_.setBlockRoot(_type);
        page_.setFile(_type.getFile());
        return page_;
    }
    public static BlockPageEl createBlockPageEl(ContextEl _context, String _class, Argument _current, ExecRootBlock _rootBlock, ExecInitBlock _block, StackCall _stackCall) {
        _stackCall.setCallingState(null);
        ExecFileBlock file_ = _block.getFile();
        BlockPageEl page_ = new BlockPageEl();
        page_.setGlobalClass(_class);
        page_.setGlobalArgument(_current);
        page_.setReturnedArgument(_current);
        page_.setBlockRootType(_rootBlock);
        ReadWrite rw_ = new ReadWrite();
        ExecBlock firstChild_ = _block.getFirstChild();
        rw_.setBlock(firstChild_);
        page_.setReadWrite(rw_);
        page_.setBlockRoot(_block);
        _context.getCoverage().passCalls(page_);
        page_.setFile(file_);
        return page_;
    }

    public static AbstractReflectPageEl createReflectMethod(AbstractReflectElement _ref, StackCall _stackCall) {
        _stackCall.setCallingState(null);
        AbstractReflectPageEl pageLoc_;
        ReflectingType reflect_ = _ref.getReflect();
        if (_ref instanceof CustomReflectLambdaConstructor) {
            CustomReflectLambdaConstructor c_ = (CustomReflectLambdaConstructor) _ref;
            Argument args_ = c_.getArgument();
            ArgumentListCall array_ = c_.getArray();
            ConstructorMetaInfo metaInfo_ = c_.getGl();
            pageLoc_ = new ReflectLambdaConstructorPageEl(args_,array_, metaInfo_);
        } else if (_ref instanceof CustomReflectConstructor) {
            CustomReflectConstructor c_ = (CustomReflectConstructor) _ref;
            Argument args_ = c_.getArgument();
            ConstructorMetaInfo metaInfo_ = c_.getGl();
            pageLoc_ = new ReflectConstructorPageEl(args_, metaInfo_);
        } else if (_ref instanceof CustomReflectRecordConstructor) {
            CustomReflectRecordConstructor c_ = (CustomReflectRecordConstructor) _ref;
            CustList<Argument> args_ = c_.getArguments();
            pageLoc_ = new ReflectRecordConstructorPageEl(args_, c_.getRoot(),c_.getId(),c_.getClassName());
        } else if (_ref instanceof CustomReflectGetField) {
            CustomReflectGetField c_ = (CustomReflectGetField) _ref;
            FieldMetaInfo metaInfo_ = c_.getGl();
            Argument args_ = c_.getArgument();
            pageLoc_ = new ReflectGetFieldPageEl(args_, metaInfo_);
        } else if (_ref instanceof CustomReflectSetField) {
            CustomReflectSetField c_ = (CustomReflectSetField) _ref;
            FieldMetaInfo metaInfo_ = c_.getGl();
            Argument first_ = c_.getFirst();
            Argument last_ = c_.getLast();
            pageLoc_ = new ReflectSetFieldPageEl(first_,last_, metaInfo_);
        } else if (_ref instanceof CustomReflectMethodDefVal) {
            CustomReflectMethodDefVal c_ = (CustomReflectMethodDefVal) _ref;
            MethodMetaInfo metaInfo_ = c_.getGl();
            pageLoc_ = new ReflectGetDefaultValuePageEl(metaInfo_);
        } else if (_ref instanceof CustomReflectLambdaMethod) {
            CustomReflectLambdaMethod c_ = (CustomReflectLambdaMethod) _ref;
            MethodMetaInfo metaInfo_ = c_.getGl();
            AbstractRefectLambdaMethodPageEl refMet_;
            Argument instance_ = c_.getInstance();
            ArgumentListCall array_ = c_.getArray();
            Argument right_ = c_.getRight();
            if (reflect_ == ReflectingType.METHOD) {
                refMet_ = new LambdaPolymorphRefectMethodPageEl(instance_,array_,right_, metaInfo_);
            } else if (reflect_ == ReflectingType.DIRECT) {
                refMet_ = new LambdaDirectRefectMethodPageEl(instance_,array_,right_, metaInfo_);
            } else if (reflect_ == ReflectingType.STATIC_CALL) {
                refMet_ = new LambdaStaticCallMethodPageEl(instance_,array_,right_, metaInfo_);
            } else if (reflect_ == ReflectingType.CAST) {
                refMet_ = new LambdaCastRefectMethodPageEl(false, instance_,array_,right_, metaInfo_);
            } else if (reflect_ == ReflectingType.CAST_DIRECT) {
                refMet_ = new LambdaCastRefectMethodPageEl(true, instance_,array_,right_, metaInfo_);
            } else if (reflect_ == ReflectingType.STD_FCT) {
                refMet_ = new LambdaDirectStdRefectMethodPageEl(instance_,array_,right_, metaInfo_);
            } else if (reflect_ == ReflectingType.CLONE_FCT) {
                refMet_ = new LambdaDirectCloneRefectMethodPageEl(instance_,array_,right_, metaInfo_);
            } else if (reflect_ == ReflectingType.ENUM_METHODS) {
                refMet_ = new LambdaDirectEnumMethods(instance_,array_,right_, metaInfo_);
            } else {
                refMet_ = new LambdaAnnotationRefectMethodPageEl(instance_,array_,right_, metaInfo_);
            }
            pageLoc_ = refMet_;
        } else if (_ref instanceof CustomReflectMethod) {
            CustomReflectMethod c_ = (CustomReflectMethod) _ref;
            MethodMetaInfo metaInfo_ = c_.getGl();
            AbstractRefectMethodPageEl refMet_;
            Argument instance_ = c_.getInstance();
            Argument array_ = c_.getArray();
            if (reflect_ == ReflectingType.METHOD) {
                refMet_ = new PolymorphRefectMethodPageEl(instance_,array_, metaInfo_);
            } else if (reflect_ == ReflectingType.DIRECT) {
                refMet_ = new DirectRefectMethodPageEl(instance_,array_, metaInfo_);
            } else if (reflect_ == ReflectingType.STATIC_CALL) {
                refMet_ = new StaticCallMethodPageEl(instance_,array_, metaInfo_);
            } else if (reflect_ == ReflectingType.CAST) {
                refMet_ = new CastRefectMethodPageEl(false, instance_,array_, metaInfo_);
            } else if (reflect_ == ReflectingType.CAST_DIRECT) {
                refMet_ = new CastRefectMethodPageEl(true, instance_,array_, metaInfo_);
            } else if (reflect_ == ReflectingType.STD_FCT) {
                refMet_ = new DirectStdRefectMethodPageEl(instance_,array_, metaInfo_);
            } else if (reflect_ == ReflectingType.CLONE_FCT) {
                refMet_ = new DirectCloneRefectMethodPageEl(instance_,array_, metaInfo_);
            } else if (reflect_ == ReflectingType.ENUM_METHODS) {
                refMet_ = new DirectEnumMethods(instance_,array_, metaInfo_);
            } else {
                refMet_ = new DirectAnnotationRefectMethodPageEl(instance_,array_, metaInfo_);
            }
            pageLoc_ = refMet_;
        } else {
            CustomReflectAnnotations c_ = (CustomReflectAnnotations) _ref;
            CustList<Argument> args_ = c_.getArguments();
            pageLoc_ = new ReflectAnnotationPageEl(args_, c_.getGl());
            ((ReflectAnnotationPageEl)pageLoc_).setOnParameters(reflect_ == ReflectingType.ANNOTATION_PARAM);
        }
        pageLoc_.setLambda(_ref.isLambda());
        ReadWrite rwLoc_ = new ReadWrite();
        pageLoc_.setReadWrite(rwLoc_);
        return pageLoc_;
    }

    public static void addPage(ContextEl _cont, AbstractPageEl _page, StackCall _stackCall) {
        LgNames stds_ = _cont.getStandards();
        String sof_ = stds_.getContent().getCoreNames().getAliasSof();
        if (_cont.getStackOverFlow() >= IndexConstants.FIRST_INDEX && _cont.getStackOverFlow() <= _stackCall.nbPages()) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, sof_, _stackCall)));
        } else {
            _stackCall.addInternPage(_page);
        }
    }

}
