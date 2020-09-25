package code.expressionlanguage.exec.opers;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.FormattedParameters;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.stds.AliasReflection;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.*;

public abstract class ExecInvokingOperation extends ExecMethodOperation implements ExecPossibleIntermediateDotted, AtomicExecCalculableOperation {
    private boolean intermediate;

    protected ExecInvokingOperation(
            ExecOperationContent _opCont, boolean _intermediateDottedOperation) {
        super(_opCont);
        intermediate = _intermediateDottedOperation;
    }

    public ExecInvokingOperation(int _indexChild, ExecClassArgumentMatching _res, int _order,
                                 boolean _intermediate) {
        super(_indexChild,_res,_order);
        intermediate = _intermediate;
    }

    protected CustList<Argument> fectchInstFormattedArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String className_, ExecRootBlock _rootBlock, String _lastType, int _naturalVararg) {
        String lastType_ = ExecTemplates.quickFormat(_rootBlock,className_, _lastType);
        return fectchArgs(_nodes, lastType_, _naturalVararg);
    }

    protected CustList<Argument> fetchFormattedArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _pr, String _className, ExecRootBlock _rootBlock, String _lastType, int _naturalVararg) {
        String cl_ = _pr.getClassName(_conf);
        String base_ = StringExpUtil.getIdFromAllTypes(_className);
        String clGen_ = ExecTemplates.getSuperGeneric(cl_, base_, _conf);
        String lastType_ = ExecTemplates.quickFormat(_rootBlock,clGen_, _lastType);
        return fectchArgs(_nodes,lastType_, _naturalVararg);
    }

    protected CustList<Argument> fectchArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _lastType, int _naturalVararg) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ArgumentList argumentList_ = listNamedArguments(_nodes, chidren_);
        CustList<Argument> first_ = argumentList_.getArguments();
        CustList<ExecOperationNode> filter_ = argumentList_.getFilter();
        return listArguments(filter_, _naturalVararg, _lastType, first_);
    }
    static ArgumentList listNamedArguments(IdMap<ExecOperationNode, ArgumentsPair> _all, CustList<ExecOperationNode> _children) {
        ArgumentList out_ = new ArgumentList();
        CustList<Argument> args_ = out_.getArguments();
        CustList<ExecOperationNode> filter_ = out_.getFilter();
        CustList<ExecNamedArgumentOperation> named_ = new CustList<ExecNamedArgumentOperation>();
        for (ExecOperationNode c: _children) {
            if (c instanceof ExecNamedArgumentOperation) {
                named_.add((ExecNamedArgumentOperation)c);
                filter_.add(c);
            } else if (!(c instanceof ExecStaticInitOperation)){
                args_.add(getArgument(_all,c));
                filter_.add(c);
            }
        }
        while (!named_.isEmpty()) {
            ExecNamedArgumentOperation min_ = named_.first();
            int minIndex_ = min_.getIndex();
            int size_ = named_.size();
            int i_ = 0;
            for (int i = 1; i < size_; i++) {
                ExecNamedArgumentOperation elt_ = named_.get(i);
                int index_ = elt_.getIndex();
                if (index_ < minIndex_) {
                    min_ = elt_;
                    minIndex_ = index_;
                    i_ = i;
                }
            }
            args_.add(getArgument(_all,min_));
            named_.remove(i_);
        }
        return out_;
    }
    static CustList<Argument> listArguments(CustList<ExecOperationNode> _children, int _natVararg, String _lastType, CustList<Argument> _nodes) {
        if (_natVararg > -1) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            int lenCh_ = _children.size();
            int natVarArg_ = _natVararg;
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                if (ExecConstLeafOperation.isFilter(_children.get(i))) {
                    natVarArg_++;
                    continue;
                }
                Argument a_ = _nodes.get(i);
                if (i >= natVarArg_) {
                    optArgs_.add(a_);
                } else {
                    firstArgs_.add(a_);
                }
            }
            int len_ = optArgs_.size();
            Struct[] array_ = new Struct[len_];
            String clArr_ = StringExpUtil.getPrettyArrayType(_lastType);
            ArrayStruct str_ = new ArrayStruct(array_,clArr_);
            ExecTemplates.setElements(optArgs_,str_);
            Argument argRem_ = new Argument(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        int lenCh_ = _children.size();
        for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
            if (ExecConstLeafOperation.isFilter(_children.get(i))) {
                continue;
            }
            Argument a_ = _nodes.get(i);
            firstArgs_.add(a_);
        }
        return firstArgs_;
    }
    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    public static Argument instancePrepareStd(ContextEl _conf, String _className, ConstructorId _constId,
                                              CustList<Argument> _arguments) {
        if (!ExecTemplates.okArgs(_constId, _className,_arguments, _conf,null)) {
            return new Argument();
        }
        ResultErrorStd res_ = ApplyCoreMethodUtil.newInstance(_conf, _constId, Argument.toArgArray(_arguments));
        return new Argument(res_.getResult());
    }

    public static Argument instancePrepare(ContextEl _conf, String _className, ExecRootBlock _root,ExecNamedFunctionBlock _constId,
                                           Argument _previous, CustList<Argument> _arguments) {
        return instancePrepareCust(_conf, _className,_root, _constId, _previous, _arguments, "", -1);
    }
    public static Argument instancePrepareFormat(PageEl _page, ContextEl _conf, String _className, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _ctor,
                                                 Argument _previous, CustList<Argument> _arguments, String _fieldName,
                                                 int _blockIndex) {
        String className_ = _page.formatVarType(_className,_conf);
        return instancePrepareCust(_conf,className_, _rootBlock,_ctor,_previous,_arguments,_fieldName,_blockIndex);
    }

    private static Argument instancePrepareCust(ContextEl _conf, String _className, ExecRootBlock _root,ExecNamedFunctionBlock _constId,
                                            Argument _previous, CustList<Argument> _arguments, String _fieldName,
                                            int _blockIndex) {
        LgNames stds_ = _conf.getStandards();
        checkNeeded(_conf, _className, _previous, stds_, _root);
        if (_conf.callsOrException()) {
            return new Argument();
        }
        Parameters parameters_ = ExecTemplates.okArgs(_root, _constId, false, _className, _arguments, _conf, null);
        if (parameters_.getError() != null) {
            return new Argument();
        }
        Argument needed_;
        if (_root.withoutInstance()) {
            needed_ = new Argument();
        } else {
            needed_ = new Argument(Argument.getNullableValue(_previous).getStruct());
        }
        _conf.setCallingState(new CustomFoundConstructor(_className, _root, _fieldName, _blockIndex,_constId, needed_, parameters_, InstancingStep.NEWING));
        return Argument.createVoid();
    }

    private static void checkNeeded(ContextEl _conf, String _className, Argument _previous, LgNames stds_, ExecRootBlock g_) {
        if (g_.withoutInstance()) {
            return;
        }
        //From analyze
        StringList parts_ = StringExpUtil.getAllPartInnerTypes(_className);
        String param_ = StringList.join(parts_.sub(0, parts_.size()-2), "");
        if (_previous.isNull()) {
            String npe_;
            npe_ = stds_.getAliasNullPe();
            _conf.setException(new ErrorStruct(_conf,npe_));
            return;
        }
        String arg_ = _previous.getStruct().getClassName(_conf);
        if (!ExecTemplates.isCorrectExecute(arg_, param_, _conf)) {
            String cast_;
            cast_ = stds_.getAliasCastType();
            _conf.setException(new ErrorStruct(_conf,cast_));
        }
    }

    public static Argument instancePrepareAnnotation(ContextEl _conf, String _className, ExecRootBlock _root, StringMap<AnnotationTypeInfo> _fieldNames, CustList<Argument> _arguments) {
        _conf.setCallingState(new CustomFoundAnnotation(_className,_root, _fieldNames, _arguments));
        return Argument.createVoid();
    }

    public static ExecOverrideInfo polymorphOrSuper(boolean _super,ContextEl _conf, Struct _previous, String _className, ExecRootBlock _root, ExecNamedFunctionBlock _named) {
        if (_super) {
            return new ExecOverrideInfo(_className,_root,_named);
        }
        return polymorph(_conf, _previous, _root, _named);
    }
    public static ExecOverrideInfo polymorph(ContextEl _conf, Struct _previous, ExecRootBlock _root, ExecNamedFunctionBlock _named) {
        String argClassName_ = Argument.getNull(_previous).getClassName(_conf);
        String base_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        ExecOverrideInfo res_ = _root.getRedirections().getVal(_named,base_);
        if (res_ != null) {
            return res_;
        }
        return new ExecOverrideInfo(_root.getGenericString(),_root, _named);
    }
    public static Argument callStd(AbstractExiting _exit,ContextEl _cont, String _classNameFound, MethodId _methodId, Argument _previous, CustList<Argument> _firstArgs, Argument _right) {
        checkParameters(_cont, _classNameFound, _methodId, _previous, _firstArgs, _right);
        LgNames stds_ = _cont.getStandards();
        String cast_;
        cast_ = stds_.getAliasCastType();
        if (_cont.callsOrException()) {
            return Argument.createVoid();
        }
        String idClassNameFound_ = StringExpUtil.getIdFromAllTypes(_classNameFound);
        Classes classes_ = _cont.getClasses();
        String aliasForName_ = stds_.getAliasForName();
        String aliasValueOf_ = stds_.getAliasEnumValueOf();
        String aliasEnumsValues_ = stds_.getAliasGetEnumConstants();
        String aliasDefaultInstance_ = stds_.getAliasDefaultInstance();
        String aliasInit_ = stds_.getAliasInit();
        String aliasAnnotated_ = stds_.getAliasAnnotated();
        String aliasGetAnnotations_ = stds_.getAliasGetAnnotations();
        String aliasGetAnnotationsParam_ = stds_.getAliasGetAnnotationsParameters();
        String aliasGetDeclaredAnonymousLambda_ = stds_.getAliasGetDeclaredAnonymousLambda();
        if (StringList.quickEq(aliasAnnotated_, idClassNameFound_)) {
            if (StringList.quickEq(aliasGetAnnotations_, _methodId.getName())) {
                _cont.setCallingState(new CustomReflectMethod(ReflectingType.ANNOTATION, _previous, _firstArgs, false));
                return new Argument();
            }
            if (StringList.quickEq(aliasGetAnnotationsParam_, _methodId.getName())) {
                _cont.setCallingState(new CustomReflectMethod(ReflectingType.ANNOTATION_PARAM, _previous, _firstArgs, false));
                return new Argument();
            }
            AnnotatedStruct annotated_ = NumParsers.getAnnotated(_previous.getStruct());
            if (StringList.quickEq(aliasGetDeclaredAnonymousLambda_, _methodId.getName())) {
                return new Argument(fetchAnonLambdaCallee(_cont,annotated_,Argument.toArgArray(_firstArgs)));
            }
            String fileName_ = annotated_.getFileName();
            return new Argument(new StringStruct(fileName_));
        }
        String aliasField_ = stds_.getAliasField();
        String aliasMethod_ = stds_.getAliasMethod();
        String aliasConstructor_ = stds_.getAliasConstructor();
        String aliasClass_ = stds_.getAliasClassType();
        if (StringList.quickEq(aliasClass_, idClassNameFound_)) {
            if (StringList.quickEq(aliasValueOf_, _methodId.getName())) {
                ClassMetaInfo cl_ = NumParsers.getClass(_previous.getStruct());
                String enumName_ = cl_.getName();
                ExecRootBlock r_ = classes_.getClassBody(enumName_);
                ClassCategory category_ = cl_.getCategory();
                Argument clArg_ = _firstArgs.first();
                return tryGetEnumValue(_exit, _cont, r_, category_, clArg_);
            }
            if (StringList.quickEq(aliasEnumsValues_, _methodId.getName())) {
                ClassMetaInfo cl_ = NumParsers.getClass(_previous.getStruct());
                String enumName_ = cl_.getName();
                ExecRootBlock r_ = classes_.getClassBody(enumName_);
                ClassCategory category_ = cl_.getCategory();
                return tryGetEnumValues(_exit, _cont, r_, category_);
            }
            if (StringList.quickEq(aliasForName_, _methodId.getName())) {
                Argument clArg_ = _firstArgs.first();
                Struct struct_ = clArg_.getStruct();
                if (!(struct_ instanceof StringStruct)) {
                    _cont.setException(new ErrorStruct(_cont,stds_.getAliasNullPe()));
                    return new Argument();
                }
                String clDyn_ = ((StringStruct) struct_).getInstance();
                if (StringList.quickEq(clDyn_.trim(), _cont.getStandards().getAliasVoid())) {
                    return new Argument(ExecutingUtil.getClassMetaInfo(_cont,clDyn_));
                }
                String res_ = ExecTemplates.correctClassPartsDynamic(clDyn_, _cont);
                if (res_.isEmpty()) {
                    _cont.setException(new ErrorStruct(_cont,clDyn_,stds_.getAliasClassNotFoundError()));
                    return new Argument();
                }
                if (BooleanStruct.isTrue(_firstArgs.last().getStruct())) {
                    if (_exit.hasToExit(res_)) {
                        return Argument.createVoid();
                    }
                }
                return new Argument(ExecutingUtil.getClassMetaInfo(_cont,res_));
            }
            if (StringList.quickEq(aliasDefaultInstance_, _methodId.getName())) {
                ClassMetaInfo cl_ = NumParsers.getClass(_previous.getStruct());
                String className_ = cl_.getName();
                String id_ = StringExpUtil.getIdFromAllTypes(className_);
                GeneType type_ = _cont.getClassBody(id_);
                if (type_ == null) {
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    _cont.setException(new ErrorStruct(_cont,null_));
                    return Argument.createVoid();
                }
                if (StringList.quickEq(id_,aliasMethod_)
                        ||StringList.quickEq(id_,aliasConstructor_)
                        ||StringList.quickEq(id_,aliasField_)
                        ||StringList.quickEq(id_,aliasClass_)) {
                    return new Argument(ApplyCoreMethodUtil.defaultMeta(_cont,id_,_firstArgs));
                }
                if (ExecutingUtil.isAbstractType(type_)) {
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    _cont.setException(new ErrorStruct(_cont,null_));
                    return Argument.createVoid();
                }
                String res_ = ExecTemplates.correctClassPartsDynamicWildCard(className_,_cont);
                if (res_.isEmpty()) {
                    String null_;
                    null_ = stds_.getAliasNullPe();
                    _cont.setException(new ErrorStruct(_cont,null_));
                    return Argument.createVoid();
                }
                className_ = res_;
                String first_;
                if (!(type_ instanceof ExecRootBlock)) {
                    return new Argument(ApplyCoreMethodUtil.defaultInstance(_cont,id_,_firstArgs));
                }
                ExecRootBlock root_ = (ExecRootBlock) type_;
                CustList<ExecRootBlock> needRoot_;
                needRoot_ = root_.getSelfAndParentTypes();
                ExecRootBlock firstType_ = needRoot_.first();
                first_ = firstType_.getFullName();
                CustList<ExecRootBlock> need_ = new CustList<ExecRootBlock>();
                for (ExecRootBlock r: needRoot_) {
                    need_.add(r);
                }
                if (!_firstArgs.isEmpty()) {
                    Struct par_ = _firstArgs.first().getStruct();
                    if (root_.withoutInstance()) {
                        if (_exit.hasToExit(first_)) {
                            return Argument.createVoid();
                        }
                        par_ = NullStruct.NULL_VALUE;
                    } else {
                        if (par_ == NullStruct.NULL_VALUE) {
                            String null_;
                            null_ = stds_.getAliasNullPe();
                            _cont.setException(new ErrorStruct(_cont,null_));
                            return Argument.createVoid();
                        }
                        String argCl_ = par_.getClassName(_cont);
                        //From analyze
                        StringList inners_ = StringExpUtil.getAllPartInnerTypes(className_);
                        String param_ = StringList.join(inners_.mid(0, inners_.size() - 2), "");
                        if (!ExecTemplates.isCorrectExecute(argCl_, param_, _cont)) {
                            _cont.setException(new ErrorStruct(_cont,cast_));
                            return Argument.createVoid();
                        }
                    }
                    Initializer in_ = _cont.getInit();
                    String genStr_ = root_.getGenericString();
                    String form_ = ExecTemplates.quickFormat(type_,className_, genStr_);
                    par_ = in_.processInit(_cont, par_, form_,root_, EMPTY_STRING, 0);
                    return new Argument(par_);
                }
                Struct parent_ = NullStruct.NULL_VALUE;
                int start_ = 0;
                if (root_.withoutInstance()) {
                    if (_exit.hasToExit(first_)) {
                        return Argument.createVoid();
                    }
                } else {
                    if (firstType_ instanceof ExecInnerElementBlock) {
                        ExecInnerElementBlock i_ = (ExecInnerElementBlock) firstType_;
                        String classFieldName_ = i_.getRealImportedClassName();
                        String idCl_ = StringExpUtil.getIdFromAllTypes(classFieldName_);
                        if (_exit.hasToExit(idCl_)) {
                            return Argument.createVoid();
                        }
                        String fieldName_ = i_.getUniqueFieldName();
                        StringMap<StringMap<Struct>> staticFields_ = _cont.getClasses().getStaticFields();
                        Struct staticField_ = Classes.getStaticField(new ClassField(idCl_, fieldName_), staticFields_);
                        parent_ = Argument.getNull(staticField_);
                        start_ = 1;
                    }
                }
                Initializer in_ = _cont.getInit();
                for (ExecRootBlock r: need_.mid(start_)) {
                    String genStr_ = r.getGenericString();
                    String form_ = ExecTemplates.quickFormat(type_,className_, genStr_);
                    parent_ = in_.processInit(_cont, parent_, form_,r, EMPTY_STRING, 0);
                }
                return new Argument(parent_);
            }
            if (StringList.quickEq(aliasInit_, _methodId.getName())) {
                ClassMetaInfo cl_ = NumParsers.getClass(_previous.getStruct());
                String clDyn_ = cl_.getName();
                _exit.hasToExit(clDyn_);
                return Argument.createVoid();
            }
        }
        String aliasGetField_ = stds_.getAliasGetField();
        String aliasSetField_ = stds_.getAliasSetField();
        String aliasInvoke_ = stds_.getAliasInvoke();
        String aliasInvokeDirect_ = stds_.getAliasInvokeDirect();
        String aliasGetDefaultValue_ = stds_.getAliasGetDefaultValue();
        String aliasNewInstance_ = stds_.getAliasNewInstance();
        if (StringList.quickEq(aliasMethod_, idClassNameFound_)) {
            if (StringList.quickEq(aliasGetDefaultValue_, _methodId.getName())) {
                _cont.setCallingState(new CustomReflectMethod(ReflectingType.DEFAULT_VALUE, _previous, _firstArgs, false));
                return new Argument();
            }
            boolean invoke_ = StringList.quickEq(aliasInvoke_, _methodId.getName());
            boolean invokeDirect_ = StringList.quickEq(aliasInvokeDirect_, _methodId.getName());
            if (invoke_ || invokeDirect_) {
                MethodMetaInfo m_ = NumParsers.getMethod(_previous.getStruct());
                if (m_.isInvokable()) {
                    if (m_.getStdCallee() != null) {
                        _cont.setCallingState(new CustomReflectMethod(ReflectingType.STD_FCT, _previous, _firstArgs, false));
                        return new Argument();
                    }
                    ExecRootBlock e_ = m_.getDeclaring();
                    if (e_ instanceof ExecAnnotationBlock) {
                        _cont.setCallingState(new CustomReflectMethod(ReflectingType.ANNOT_FCT, _previous, _firstArgs, false));
                        return new Argument();
                    }
                }
            }
            if (invoke_) {
                MethodMetaInfo m_ = NumParsers.getMethod(_previous.getStruct());
                if (!m_.isInvokable()) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _cont.setException(new ErrorStruct(_cont,null_));
                    return new Argument();
                }
                if (m_.isExpCast()) {
                    _cont.setCallingState(new CustomReflectMethod(ReflectingType.CAST, _previous, _firstArgs, false));
                    return new Argument();
                }
                if (m_.getCalleeInv() != null) {
                    if (m_.isStaticCall()) {
                        _cont.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _previous, _firstArgs, false));
                        return new Argument();
                    }
                    _cont.setCallingState(new CustomReflectMethod(ReflectingType.METHOD, _previous, _firstArgs, false));
                    return new Argument();
                }
            }
            if (invokeDirect_) {
                MethodMetaInfo m_ = NumParsers.getMethod(_previous.getStruct());
                if (!m_.isInvokable()) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _cont.setException(new ErrorStruct(_cont,null_));
                    return new Argument();
                }
                if (m_.isExpCast()) {
                    _cont.setCallingState(new CustomReflectMethod(ReflectingType.CAST_DIRECT, _previous, _firstArgs, false));
                    return new Argument();
                }
                if (m_.getCalleeInv() != null) {
                    if (m_.isStaticCall()) {
                        _cont.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _previous, _firstArgs, false));
                        return new Argument();
                    }
                    _cont.setCallingState(new CustomReflectMethod(ReflectingType.DIRECT, _previous, _firstArgs, false));
                    return new Argument();
                }
            }
            if (invoke_ || invokeDirect_) {
                MethodMetaInfo m_ = NumParsers.getMethod(_previous.getStruct());
                redirectGenerated(_cont,m_,_previous, _firstArgs,false);
                return new Argument();
            }
        }
        String aliasFct_ = stds_.getAliasFct();
        if (StringList.quickEq(aliasFct_, idClassNameFound_)) {
            if (_firstArgs.isEmpty()) {
                if (StringList.quickEq(_methodId.getName(),_cont.getStandards().getAliasMetaInfo())) {
                    return getMetaInfo(_previous, _cont);
                }
                return getInstanceCall(_previous, _cont);
            }
            Argument instance_ = _firstArgs.first();
            Struct inst_ = instance_.getStruct();
            if (!(inst_ instanceof ArrayStruct)) {
                _cont.setException(new ErrorStruct(_cont,stds_.getAliasNullPe()));
                return new Argument();
            }
            ArrayStruct arr_ = (ArrayStruct) inst_;
            Struct[] real_ = arr_.getInstance();
            CustList<Argument> ar_ = new CustList<Argument>();
            for (Struct str_ : real_) {
                ar_.add(new Argument(str_));
            }
            return prepareCallDyn(_previous, ar_, _cont);
        }
        if (StringList.quickEq(aliasConstructor_, idClassNameFound_)) {
            if (StringList.quickEq(aliasNewInstance_, _methodId.getName())) {
                ConstructorMetaInfo meta_ = NumParsers.getCtor(_previous.getStruct());
                if(!meta_.isInvokable()) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _cont.setException(new ErrorStruct(_cont,null_));
                    return new Argument();
                }
                _cont.setCallingState(new CustomReflectMethod(ReflectingType.CONSTRUCTOR, _previous, _firstArgs, false));
                return new Argument();
            }
        }
        if (StringList.quickEq(aliasField_, idClassNameFound_)) {
            if (StringList.quickEq(aliasGetField_, _methodId.getName())) {
                FieldMetaInfo meta_ = NumParsers.getField(_previous.getStruct());
                if (!meta_.isInvokable()) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _cont.setException(new ErrorStruct(_cont,null_));
                    return new Argument();
                }
                _cont.setCallingState(new CustomReflectMethod(ReflectingType.GET_FIELD, _previous, _firstArgs, false));
                return new Argument();
            }
            if (StringList.quickEq(aliasSetField_, _methodId.getName())) {
                FieldMetaInfo meta_ = NumParsers.getField(_previous.getStruct());
                if (!meta_.isInvokable()) {
                    String null_;
                    null_ = stds_.getAliasIllegalArg();
                    _cont.setException(new ErrorStruct(_cont,null_));
                    return new Argument();
                }
                _cont.setCallingState(new CustomReflectMethod(ReflectingType.SET_FIELD, _previous, _firstArgs, false));
                return new Argument();
            }
        }
        ClassMethodId dyn_ = new ClassMethodId(idClassNameFound_, _methodId);
        ResultErrorStd res_ = LgNames.invokeMethod(_cont, dyn_, _previous.getStruct(), Argument.toArgArray(_firstArgs));
        return new Argument(res_.getResult());
    }

    public static Argument tryGetEnumValues(AbstractExiting _exit, ContextEl _cont, ExecRootBlock r_, ClassCategory category_) {
        if (isNotEnumType(r_, category_)) {
            return new Argument();
        }
        String className_ = r_.getWildCardElement();
        return getEnumValues(_exit,className_,r_, _cont);
    }

    public static Argument tryGetEnumValue(AbstractExiting _exit, ContextEl _cont, ExecRootBlock r_, ClassCategory category_, Argument clArg_) {
        if (isNotEnumType(r_, category_)) {
            return new Argument();
        }
        String enumName_ = r_.getFullName();
        return getEnumValue(_exit,enumName_,r_, clArg_, _cont);
    }

    private static ArrayStruct fetchAnonLambdaCallee(ContextEl _cont, AnnotatedStruct _annot, Argument... _args) {
        CustList<MethodMetaInfo> candidates_;
        candidates_ = new CustList<MethodMetaInfo>();
        Struct[] args_ = AnaApplyCoreMethodUtil.getObjects(_args);
        LgNames standards_ = _cont.getStandards();
        String aliasMethod_ = standards_.getAliasMethod();
        CustList<MethodMetaInfo> methods_ = new CustList<MethodMetaInfo>();
        String declaringClass_ = _annot.getDeclaringClass();
        for (ExecAnonymousFunctionBlock f: _annot.getAnonymousLambda()) {
            MethodId id_ = f.getId();
            ExecRootBlock _type = f.getParentType();
            String ret_ = f.getImportedReturnType();
            boolean param_ = id_.getKind() == MethodAccessKind.STATIC_CALL;
            MethodId fid_ = ExecutingUtil.tryFormatId(declaringClass_, _cont, id_);
            String idType_ = _type.getFullName();
            String formCl_ = ExecutingUtil.tryFormatType(idType_, declaringClass_, _cont);
            String idCl_ = _type.getFullName();
            if (param_) {
                idCl_ = declaringClass_;
            }
            MethodMetaInfo met_ = new MethodMetaInfo(declaringClass_,f.getAccess(), idCl_, id_, f.getModifier(), ret_, fid_, formCl_);
            met_.setCache(new Cache(f,standards_.getAliasObject()));
            met_.setAnnotableBlock(f);
            met_.setCallee(f);
            met_.setCalleeInv(f);
            met_.setDeclaring(_type);
            met_.setFileName(f.getFile().getFileName());
            methods_.add(met_);
        }
        if (args_.length == 0) {
            for (MethodMetaInfo e: methods_) {
                candidates_.add(e);
            }
        } else {
            AliasReflection.filterMethods(_cont, args_, declaringClass_, candidates_, methods_);
        }
        String className_= StringExpUtil.getPrettyArrayType(aliasMethod_);
        Struct[] methodsArr_ = new Struct[candidates_.size()];
        int index_ = 0;
        for (MethodMetaInfo c: candidates_) {
            methodsArr_[index_] = c;
            index_++;
        }
        return new ArrayStruct(methodsArr_, className_);
    }
    public static Argument callPrepare(AbstractExiting _exit, ContextEl _cont, String _classNameFound, ExecRootBlock _rootBlock, Argument _previous, CustList<Argument> _firstArgs, Argument _right, ExecNamedFunctionBlock _named, MethodAccessKind _kind, String _name) {
        return callPrepare(_exit,_cont,_classNameFound,_rootBlock, _previous,null,_firstArgs,_right,_named, _kind, _name);
    }
    public static Argument callPrepare(AbstractExiting _exit, ContextEl _cont, String _classNameFound, ExecRootBlock _rootBlock, Argument _previous, Cache _cache, CustList<Argument> _firstArgs, Argument _right, ExecNamedFunctionBlock _named, MethodAccessKind _kind, String _name) {
        if (!(_named instanceof ExecOverridableBlock)&&!(_named instanceof ExecAnonymousFunctionBlock)) {
            if (_named instanceof ExecOperatorBlock) {
                FormattedParameters classFound_ = checkParameters(_cont, _classNameFound, _rootBlock,_named, _previous, _cache,_firstArgs, CallPrepareState.METHOD,null, _right);
                if (_cont.callsOrException()) {
                    return Argument.createVoid();
                }
                _cont.setCallingState(new CustomFoundMethod(_previous, classFound_.getFormattedClass(),_rootBlock, _named, classFound_.getParameters()));
                return Argument.createVoid();
            }
            //static enum methods
            LgNames stds_ = _cont.getStandards();
            if (_firstArgs.size() != 1) {
                return tryGetEnumValues(_exit,_cont,_rootBlock,  ClassCategory.ENUM);
            }
            Argument arg_ = _firstArgs.first();
            Struct ex_ = ExecTemplates.checkObjectEx(stds_.getAliasString(), arg_, _cont);
            if (ex_ != null) {
                _cont.setException(ex_);
                return Argument.createVoid();
            }
            return tryGetEnumValue(_exit,_cont,_rootBlock, ClassCategory.ENUM, arg_);
        }
        if (FunctionIdUtil.isOperatorName(_name)) {
            FormattedParameters classFound_ = checkParameters(_cont, _classNameFound, _rootBlock,_named, _previous,_cache, _firstArgs, CallPrepareState.METHOD,null, _right);
            if (_cont.callsOrException()) {
                return Argument.createVoid();
            }
            _cont.setCallingState(new CustomFoundMethod(_previous, classFound_.getFormattedClass(),_rootBlock, _named, classFound_.getParameters()));
            return Argument.createVoid();
        }
        FormattedParameters classFound_ = checkParameters(_cont, _classNameFound, _rootBlock,_named, _previous, _cache,_firstArgs, CallPrepareState.METHOD,null, _right);
        if (_cont.callsOrException()) {
            return Argument.createVoid();
        }
        Struct prev_ =_previous.getStruct();
        if (_named instanceof ExecOverridableBlock&&prev_ instanceof AbstractFunctionalInstance) {
            ExecOverridableBlock gene_ = (ExecOverridableBlock) _named;
            if (gene_.isAbstractMethod()) {
                Argument fct_ = new Argument(((AbstractFunctionalInstance)prev_).getFunctional());
                return prepareCallDyn(fct_, _firstArgs, _cont);
            }
        }
        if (_kind == MethodAccessKind.STATIC_CALL) {
            _cont.setCallingState(new CustomFoundCast(classFound_.getFormattedClass(),_rootBlock, _named, classFound_.getParameters()));
            return Argument.createVoid();
        }
        _cont.setCallingState(new CustomFoundMethod(_previous, classFound_.getFormattedClass(),_rootBlock, _named, classFound_.getParameters()));
        return Argument.createVoid();
    }

    private static boolean isNotEnumType(ExecRootBlock r_, ClassCategory _category) {
        return r_ == null || _category != ClassCategory.ENUM;
    }

    public static void checkParametersOperators(AbstractExiting _exit, ContextEl _conf, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _named,
                                                CustList<Argument> _firstArgs, String _className, MethodAccessKind _kind) {
        String classNameFound_ = _className;
        classNameFound_ = ClassMethodId.formatType(classNameFound_,_conf, _kind);
        if (_exit.hasToExit(classNameFound_)) {
            return;
        }
        checkParameters(_conf, classNameFound_,_rootBlock, _named, Argument.createVoid(),null, _firstArgs, CallPrepareState.OPERATOR,null, null);
    }

    private static void checkParameters(ContextEl _conf, String _classNameFound, Identifiable _methodId,
                                        Argument _previous, CustList<Argument> _firstArgs,
                                        Argument _right) {
        ExecTemplates.checkParams(_conf, _classNameFound, _methodId, _previous, _firstArgs, _right);
    }
    public static void checkParametersCtors(ContextEl _conf, String _classNameFound,
                                            ExecRootBlock _rootBlock, ExecNamedFunctionBlock _named,
                                            CustList<Argument> _firstArgs,
                                            InstancingStep _kindCall) {
        Argument arg_ = _conf.getLastPage().getGlobalArgument();
        checkParametersCtors(_conf,_classNameFound,_rootBlock,_named,arg_,_firstArgs,_kindCall);
    }
    public static void checkParametersCtors(ContextEl _conf, String _classNameFound,
                                            ExecRootBlock _rootBlock, ExecNamedFunctionBlock _named,
                                            Argument _previous, CustList<Argument> _firstArgs,
                                            InstancingStep _kindCall) {
        checkParameters(_conf,_classNameFound,_rootBlock,_named,_previous,null,_firstArgs,CallPrepareState.CTOR,_kindCall,null);
    }
    private static FormattedParameters checkParameters(ContextEl _conf, String _classNameFound, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _methodId,
                                          Argument _previous, Cache _cache, CustList<Argument> _firstArgs,
                                          CallPrepareState _state,
                                          InstancingStep _kindCall, Argument _right) {
        FormattedParameters classFormat_ = ExecTemplates.checkParams(_conf,_classNameFound,_rootBlock,_methodId,_previous,_cache,_firstArgs,_right);
        if (_conf.callsOrException()) {
            return classFormat_;
        }
        if (_state == CallPrepareState.METHOD) {
            return classFormat_;
        }
        Parameters parameters_ = classFormat_.getParameters();
        if (_state == CallPrepareState.CTOR) {
            _conf.setCallingState(new CustomFoundConstructor(_classNameFound, _rootBlock, EMPTY_STRING, -1,  _methodId, _previous, parameters_, _kindCall));
            return classFormat_;
        }
        if (_rootBlock == null) {
            _conf.setCallingState(new CustomFoundMethod(Argument.createVoid(), _classNameFound,_rootBlock, _methodId, parameters_));
            return classFormat_;
        }
        _conf.setCallingState(new CustomFoundCast(_classNameFound,_rootBlock,  _methodId, parameters_));
        return classFormat_;
    }

    public static Argument getInstanceCall(Argument _previous, ContextEl _conf) {
        Struct ls_ = _previous.getStruct();
        if (ls_ instanceof LambdaStruct) {
            return ((LambdaStruct) ls_).getInstanceCall();
        }
        LgNames lgNames_ = _conf.getStandards();
        String null_;
        null_ = lgNames_.getAliasNullPe();
        _conf.setException(new ErrorStruct(_conf,null_));
        return Argument.createVoid();
    }
    public static Argument getMetaInfo(Argument _previous, ContextEl _conf) {
        Struct ls_ = _previous.getStruct();
        if (ls_ instanceof LambdaStruct) {
            return new Argument(((LambdaStruct) ls_).getMetaInfo());
        }
        LgNames lgNames_ = _conf.getStandards();
        String null_;
        null_ = lgNames_.getAliasNullPe();
        _conf.setException(new ErrorStruct(_conf,null_));
        return Argument.createVoid();
    }
    public static Argument prepareCallDyn(Argument _previous, CustList<Argument> _values, ContextEl _conf) {
        Struct ls_ = _previous.getStruct();
        LgNames lgNames_ = _conf.getStandards();
        if (ls_ instanceof LambdaStruct) {
            String typeFct_ = ls_.getClassName(_conf);
            StringList parts_ = StringExpUtil.getAllTypes(typeFct_);
            CustList<String> paramsFct_ = parts_.mid(1, parts_.size() - 2);
            int valuesSize_ = _values.size();
            if (valuesSize_ != paramsFct_.size()) {
                String null_;
                null_ = lgNames_.getAliasIllegalArg();
                _conf.setException(new ErrorStruct(_conf,null_));
                return new Argument();
            }
            for (int i = 0; i < valuesSize_; i++) {
                Argument arg_ = _values.get(i);
                String param_ = paramsFct_.get(i);
                if (!ExecTemplates.checkQuick(param_, arg_, _conf)) {
                    return new Argument();
                }
            }
        }
        if (ls_ instanceof LambdaConstructorStruct) {
            LambdaConstructorStruct l_ = (LambdaConstructorStruct) ls_;
            String forId_ = l_.getFormClassName();
            if (forId_.startsWith(ARR)) {
                Ints dims_ = new Ints();
                for (Argument a: _values) {
                    int dim_ = NumParsers.convertToNumber(a.getStruct()).intStruct();
                    dims_.add(dim_);
                }
                String c_ = forId_.substring(ARR.length());
                Struct res_ = ExecTemplates.newCustomArrayOrExc(c_, dims_, _conf);
                if (res_ instanceof ErrorStruct) {
                    _conf.setException(res_);
                    return new Argument();
                }
                return new Argument(res_);
            }
            Argument pr_ = new Argument(l_.getMetaInfo());
            Argument instance_ = l_.getInstanceCall();
            if (l_.isSafeInstance()&&instance_.isNull()) {
                String last_ = StringExpUtil.getAllTypes(l_.getClassName(_conf)).last();
                return new Argument(ExecClassArgumentMatching.defaultValue(last_,_conf));
            }
            String obj_ = _conf.getStandards().getAliasObject();
            obj_ = StringExpUtil.getPrettyArrayType(obj_);
            if (!l_.isShiftInstance()) {
                ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()],obj_);
                int i_ = 0;
                for (Argument v: _values) {
                    arr_.getInstance()[i_] = v.getStruct();
                    i_++;
                }
                CustList<Argument> nList_ = new CustList<Argument>();
                nList_.add(new Argument(arr_));
                _conf.setCallingState(new CustomReflectMethod(ReflectingType.CONSTRUCTOR, pr_, nList_, true));
                return new Argument();
            }
            ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()+1],obj_);
            int i_ = 1;
            arr_.getInstance()[0] = instance_.getStruct();
            for (Argument v: _values) {
                arr_.getInstance()[i_] = v.getStruct();
                i_++;
            }
            CustList<Argument> nList_ = new CustList<Argument>();
            nList_.add(new Argument(arr_));
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.CONSTRUCTOR, pr_, nList_, true));
            return new Argument();
        }
        if (ls_ instanceof LambdaFieldStruct) {
            LambdaFieldStruct l_ =  (LambdaFieldStruct) ls_;
            boolean aff_ = l_.isAffect();
            ClassField idField_ = l_.getFid();
            if (idField_ == null) {
                Argument instance_ = l_.getInstanceCall();
                Argument realInstance_;
                if (!l_.isShiftInstance()) {
                    realInstance_ = instance_;
                } else {
                    realInstance_ = _values.first();
                }
                if (StringList.quickEq(l_.getReturnFieldType(),lgNames_.getAliasPrimBoolean())) {
                    String ownerType_ = l_.getOwnerType();
                    boolean res_ = ExecTemplates.safeObject(ownerType_,realInstance_,_conf) == ErrorType.NOTHING;
                    return new Argument(BooleanStruct.of(res_));
                }
                return new Argument(realInstance_.getStruct().getParent());
            }
            boolean static_ = l_.isStaticField();
            int nbAncestors_ = l_.getAncestor();
            String clName_ = idField_.getClassName();
            Argument pr_ = new Argument(l_.getMetaInfo());
            ReflectingType type_;
            if (aff_) {
                type_ = ReflectingType.SET_FIELD;
            } else {
                type_ = ReflectingType.GET_FIELD;
            }
            Argument instance_ = l_.getInstanceCall();
            if (l_.isSafeInstance()&&instance_.isNull()) {
                String last_ = StringExpUtil.getAllTypes(l_.getClassName(_conf)).last();
                return new Argument(ExecClassArgumentMatching.defaultValue(last_,_conf));
            }
            CustList<Argument> nList_ = new CustList<Argument>();
            Argument realInstance_;
            Struct value_;
            if (static_) {
                value_ = NullStruct.NULL_VALUE;
            } else if (!l_.isShiftInstance()) {
                value_ = instance_.getStruct();
            } else {
                value_ = _values.first().getStruct();
            }
            if (!static_) {
                realInstance_ = new Argument(ExecTemplates.getParent(nbAncestors_, clName_, value_, _conf));
                if (_conf.callsOrException()) {
                    return new Argument();
                }
            } else {
                realInstance_ = new Argument();
            }
            nList_.add(realInstance_);
            if (aff_) {
                nList_.add(_values.last());
            }
            _conf.setCallingState(new CustomReflectMethod(type_, pr_, nList_, true));
            return new Argument();
        }
        if (ls_ instanceof LambdaMethodStruct) {
            LambdaMethodStruct l_ =  (LambdaMethodStruct) ls_;
            int nbAncestors_ = l_.getAncestor();
            String id_;
            MethodId fid_ = l_.getFid();
            if (l_.isStaticCall()) {
                id_ = l_.getFormClassName();
            } else {
                id_ = StringExpUtil.getIdFromAllTypes(l_.getFormClassName());
            }
            boolean static_ = fid_.isStaticMethod();
            Argument pr_ = new Argument(l_.getMetaInfo());
            Struct instanceStruct_ = l_.getInstanceCall().getStruct();
            if (l_.isSafeInstance()&&instanceStruct_ == NullStruct.NULL_VALUE) {
                String last_ = StringExpUtil.getAllTypes(l_.getClassName(_conf)).last();
                return new Argument(ExecClassArgumentMatching.defaultValue(last_,_conf));
            }
            String obj_ = _conf.getStandards().getAliasObject();
            obj_ = StringExpUtil.getPrettyArrayType(obj_);
            if (!l_.isShiftInstance()) {
                ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()],obj_);
                int i_ = 0;
                for (Argument v: _values) {
                    arr_.getInstance()[i_] = v.getStruct();
                    i_++;
                }
                CustList<Argument> nList_ = new CustList<Argument>();
                Argument instance_;
                if (!static_) {
                    instance_ = new Argument(ExecTemplates.getParent(nbAncestors_, id_, instanceStruct_, _conf));
                    if (_conf.callsOrException()) {
                        return new Argument();
                    }
                } else {
                    instance_ = new Argument(instanceStruct_);
                }
                nList_.add(instance_);
                nList_.add(new Argument(arr_));
                return redirect(_conf, l_, pr_, nList_);
            }
            if (FunctionIdUtil.isOperatorName(fid_.getName())) {
                ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()+1],obj_);
                int i_ = 1;
                arr_.getInstance()[0] = instanceStruct_;
                for (Argument v: _values) {
                    arr_.getInstance()[i_] = v.getStruct();
                    i_++;
                }
                CustList<Argument> nList_ = new CustList<Argument>();
                nList_.add(new Argument(arr_));
                _conf.setCallingState(new CustomReflectMethod(ReflectingType.DIRECT, pr_, nList_, true));
                return new Argument();
            }
            ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()-1],obj_);
            int i_ = 0;
            for (Argument v: _values.mid(1, _values.size() - 1)) {
                arr_.getInstance()[i_] = v.getStruct();
                i_++;
            }
            CustList<Argument> nList_ = new CustList<Argument>();
            Struct value_ = _values.first().getStruct();
            Argument firstValue_ = new Argument(ExecTemplates.getParent(nbAncestors_, id_, value_, _conf));
            if (_conf.callsOrException()) {
                return new Argument();
            }
            nList_.add(firstValue_);
            nList_.add(new Argument(arr_));
            return redirect(_conf, l_, pr_, nList_);
        }
        String null_;
        null_ = lgNames_.getAliasNullPe();
        _conf.setException(new ErrorStruct(_conf,null_));
        return Argument.createVoid();
    }

    private static Argument redirect(ContextEl _conf, LambdaMethodStruct _l, Argument _pr, CustList<Argument> _nList) {
        if (_l.getFormClassName().startsWith(PrimitiveTypeUtil.ARR_CLASS) && _l.getFid().getName().startsWith("[]")) {
            Struct arr_ = _nList.first().getStruct();
            ArrayStruct argArr_ = ExecArrayFieldOperation.getArray(_nList.get(1).getStruct(),_conf);
            int len_ = argArr_.getInstance().length - 1;
            if (len_ < 0) {
                return new Argument(new IntStruct(ExecArrayFieldOperation.getLength(arr_,_conf)));
            }
            if (!StringList.quickEq(_l.getFid().getName(),"[]")) {
                len_--;
            }
            for (int i = 0; i < len_; i++) {
                Struct ind_ = argArr_.getInstance()[i];
                arr_ = ExecTemplates.getElement(arr_,ind_,_conf);
                if (_conf.callsOrException()) {
                    return new Argument();
                }
            }
            Struct ind_ = argArr_.getInstance()[len_];
            if (StringList.quickEq(_l.getFid().getName(),"[]")) {
                return new Argument(ExecTemplates.getElement(arr_,ind_,_conf));
            }
            Struct right_ = argArr_.getInstance()[len_ + 1];
            ExecTemplates.setElement(arr_,ind_, right_,_conf);
            return new Argument(right_);
        }
        if (_l.isDirectCast()) {
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.CAST_DIRECT, _pr, _nList, true));
            return new Argument();
        }
        if (_l.isExpCast()) {
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.CAST, _pr, _nList, true));
            return new Argument();
        }
        MethodMetaInfo m_ = NumParsers.getMethod(_l.getMetaInfo());
        if (m_.getStdCallee() != null) {
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.STD_FCT, _pr, _nList, true));
            return new Argument();
        }
        if (m_.getCalleeInv() instanceof ExecAnonymousFunctionBlock) {
            if (_l.getFid().getKind() == MethodAccessKind.STATIC_CALL) {
                _conf.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _pr, _nList, true));
                return new Argument();
            }
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.DIRECT, _pr, _nList, true));
            return new Argument();
        }
        ExecRootBlock e_ = m_.getDeclaring();
        if (e_ instanceof ExecAnnotationBlock) {
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.ANNOT_FCT, _pr, _nList, true));
            return new Argument();
        }
        if (m_.getCalleeInv() != null) {
            if (_l.getFid().getKind() == MethodAccessKind.STATIC_CALL) {
                _conf.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _pr, _nList, true));
                return new Argument();
            }
            if (!_l.isPolymorph()) {
                _conf.setCallingState(new CustomReflectMethod(ReflectingType.DIRECT, _pr, _nList, true));
                return new Argument();
            }
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.METHOD, _pr, _nList, true));
            return new Argument();
        }
        redirectGenerated(_conf,m_,_pr,_nList,true);
        return new Argument();
    }
    private static void redirectGenerated(ContextEl _conf, Struct _l, Argument _pr, CustList<Argument> _nList, boolean _lambda) {
        MethodMetaInfo m_ = NumParsers.getMethod(_l);
        ExecRootBlock e_ = m_.getDeclaring();
        if (e_ != null) {
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.ENUM_METHODS, _pr, _nList, _lambda));
            return;
        }
        _conf.setCallingState(new CustomReflectMethod(ReflectingType.CLONE_FCT, _pr, _nList, _lambda));
    }
    private static Argument getEnumValues(AbstractExiting _exit, String _class, ExecRootBlock _root, ContextEl _conf) {
        String id_ = StringExpUtil.getIdFromAllTypes(_class);
        if (_exit.hasToExit(id_)) {
            return Argument.createVoid();
        }
        Classes classes_ = _conf.getClasses();
        CustList<Struct> enums_ = new CustList<Struct>();
        for (ExecInnerTypeOrElement b: _root.getEnumElements()) {
            String fieldName_ = b.getUniqueFieldName();
            Struct str_ = classes_.getStaticField(new ClassField(id_, fieldName_),b.getImportedClassName(),_conf);
            _conf.getInitializingTypeInfos().addSensibleField(_conf,id_, str_);
            enums_.add(str_);
        }
        Struct[] o_ = new Struct[enums_.size()];
        int i_ = CustList.FIRST_INDEX;
        for (Struct o: enums_) {
            o_[i_] = o;
            i_++;
        }
        String clArr_ = _class;
        clArr_ = StringExpUtil.getPrettyArrayType(clArr_);
        return new Argument(new ArrayStruct(o_,clArr_));
    }
    private static Argument getEnumValue(AbstractExiting _exit, String _class, ExecRootBlock _root, Argument _name, ContextEl _conf) {
        String enumName_ = StringExpUtil.getIdFromAllTypes(_class);
        if (_exit.hasToExit(enumName_)) {
            return Argument.createVoid();
        }
        Struct name_ = _name.getStruct();
        if (!(name_ instanceof StringStruct)) {
            return new Argument();
        }
        Classes classes_ = _conf.getClasses();
        for (ExecInnerTypeOrElement b: _root.getEnumElements()) {
            String fieldName_ = b.getUniqueFieldName();
            if (StringList.quickEq(fieldName_, ((StringStruct) name_).getInstance())) {
                Struct str_ = classes_.getStaticField(new ClassField(enumName_, fieldName_),b.getImportedClassName(),_conf);
                _conf.getInitializingTypeInfos().addSensibleField(_conf,enumName_, str_);
                return new Argument(str_);
            }
        }
        return new Argument();
    }
}
