package code.expressionlanguage.exec.opers;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.analyze.opers.InvokingOperation;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.*;
import code.util.*;

public abstract class ExecInvokingOperation extends ExecMethodOperation implements ExecPossibleIntermediateDotted, AtomicExecCalculableOperation {
    private boolean intermediate;

    private Argument previousArgument;

    public ExecInvokingOperation(
            InvokingOperation _inter) {
        super(_inter);
        intermediate = _inter.isIntermediateDottedOperation();
        previousArgument = _inter.getPreviousArgument();
    }

    public ExecInvokingOperation(int _indexChild, ClassArgumentMatching _res, int _order,
                                 boolean _intermediate, Argument _previousArgument) {
        super(_indexChild,_res,_order);
        intermediate = _intermediate;
        previousArgument = _previousArgument;
    }

    static CustList<Argument> listArguments(CustList<ExecOperationNode> _children, int _natVararg, String _lastType, CustList<Argument> _nodes) {
        if (_natVararg > -1) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            int lenCh_ = _children.size();
            int natVarArg_ = _natVararg;
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                if (_children.get(i) instanceof ExecIdFctOperation
                        || _children.get(i) instanceof ExecVarargOperation) {
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
            Argument argRem_ = new Argument();
            int len_ = optArgs_.size();
            Struct[] array_ = new Struct[len_];
            String clArr_ = StringExpUtil.getPrettyArrayType(_lastType);
            ArrayStruct str_ = new ArrayStruct(array_,clArr_);
            ExecTemplates.setElements(optArgs_,str_);
            argRem_.setStruct(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        int lenCh_ = _children.size();
        for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
            if (_children.get(i) instanceof ExecIdFctOperation
                    || _children.get(i) instanceof ExecVarargOperation) {
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

    @Override
    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    public static Argument instancePrepare(ContextEl _conf, String _className, ConstructorId _constId,
                                           Argument _previous, CustList<Argument> _arguments) {
        return instancePrepare(_conf, _className, _constId, _previous, _arguments, "", -1);
    }
    public static Argument instancePrepareFormat(PageEl _page, ContextEl _conf, String _className, ConstructorId _constId,
                                                 Argument _previous, CustList<Argument> _arguments, String _fieldName,
                                                 int _blockIndex) {
        String className_ = _page.formatVarType(_className,_conf);
        return instancePrepare(_conf,className_,_constId,_previous,_arguments,_fieldName,_blockIndex);
    }
    private static Argument instancePrepare(ContextEl _conf, String _className, ConstructorId _constId,
                                            Argument _previous, CustList<Argument> _arguments, String _fieldName,
                                            int _blockIndex) {
        LgNames stds_ = _conf.getStandards();
        String idCl_ = StringExpUtil.getIdFromAllTypes(_className);
        GeneType g_ = _conf.getClassBody(idCl_);
        Argument needed_ = new Argument();
        setupNeeded(_conf, _className, _previous, stds_, g_, needed_);
        if (_conf.callsOrException()) {
            return new Argument();
        }
        String base_ = StringExpUtil.getIdFromAllTypes(_className);
        if (!ExecTemplates.okArgs(_constId,false, _className,_arguments, _conf,null)) {
            return new Argument();
        }
        ExecRootBlock execSuperClass_ = _conf.getClasses().getClassBody(base_);
        if (execSuperClass_ == null) {
            ResultErrorStd res_ = ApplyCoreMethodUtil.newInstance(_conf, _constId, Argument.toArgArray(_arguments));
            Argument arg_ = new Argument();
            arg_.setStruct(res_.getResult());
            return arg_;
        }
        _conf.setCallingState(new CustomFoundConstructor(_className, execSuperClass_, _fieldName, _blockIndex,_constId, needed_, _arguments, InstancingStep.NEWING));
        return Argument.createVoid();
    }

    private static void setupNeeded(ContextEl _conf, String _className, Argument _previous, LgNames stds_, GeneType g_, Argument needed_) {
        if (!(g_ instanceof ExecRootBlock)) {
            return;
        }
        ExecRootBlock r_ = (ExecRootBlock) g_;
        if (r_.withoutInstance()) {
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
        String arg_ = _previous.getObjectClassName(_conf);
        if (!ExecTemplates.isCorrectExecute(arg_, param_, _conf)) {
            String cast_;
            cast_ = stds_.getAliasCastType();
            _conf.setException(new ErrorStruct(_conf,cast_));
            return;
        }
        needed_.setStruct(_previous.getStruct());
    }

    public static Argument instancePrepareAnnotation(ContextEl _conf, String _className, StringMap<AnnotationTypeInfo> _fieldNames, CustList<Argument> _arguments) {
        String idClass_ = StringExpUtil.getIdFromAllTypes(_className);
        ExecRootBlock superClass_ = _conf.getClasses().getClassBody(idClass_);
        _conf.setCallingState(new CustomFoundAnnotation(_className,superClass_, _fieldNames, _arguments));
        return Argument.createVoid();
    }
    public static ClassMethodId polymorph(ContextEl _conf, Struct _previous, ClassMethodId _classMethodId) {
        String classNameFound_ = _classMethodId.getClassName();
        classNameFound_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
        String argClassName_ = _previous.getClassName(_conf);
        String base_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        MethodId id_ = _classMethodId.getConstraints();
        ExecRootBlock info_ = _conf.getClasses().getClassBody(classNameFound_);
        MethodId methodId_;
        if (info_ == null) {
            classNameFound_ = _classMethodId.getClassName();
            methodId_ = id_;
        } else {
            ClassMethodId res_ = info_.getRedirections().getVal(_classMethodId,base_);
            if (res_ != null) {
                classNameFound_ = res_.getClassName();
                methodId_ = res_.getConstraints();
            } else {
                classNameFound_ = _classMethodId.getClassName();
                methodId_ = id_;
            }
        }
        return new ClassMethodId(classNameFound_, methodId_);
    }
    public static Argument callPrepare(AbstractExiting _exit,ContextEl _cont, String _classNameFound, MethodId _methodId, Argument _previous, CustList<Argument> _firstArgs, Argument _right) {
        String classFound_ = checkParameters(_cont, _classNameFound, _methodId, _previous, _firstArgs, CallPrepareState.METHOD, null, _right);
        LgNames stds_ = _cont.getStandards();
        String cast_;
        cast_ = stds_.getAliasCastType();
        if (_cont.callsOrException()) {
            return Argument.createVoid();
        }
        if (FunctionIdUtil.isOperatorName(_methodId)) {
            _cont.setCallingState(new CustomFoundMethod(_previous, classFound_, _methodId, _firstArgs,null));
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
        if (!_methodId.isStaticMethod()) {
            String clName_ = _previous.getObjectClassName(_cont);
            Struct prev_ =_previous.getStruct();
            if (prev_ instanceof ArrayStruct) {
                //clone object
                Argument a_ = new Argument();
                ArrayStruct arr_ = (ArrayStruct) prev_;
                ArrayStruct copy_ = arr_.swallowCopy();
                _cont.getInitializingTypeInfos().addSensibleElementsFromClonedArray(arr_, copy_);
                a_.setStruct(copy_);
                return a_;
            }
            if (prev_ instanceof AnnotationStruct) {
                Struct ret_ = getInstanceField(clName_, _methodId.getName(),_previous,_cont).getStruct();
                Argument a_ = new Argument();
                if (ret_ instanceof ArrayStruct) {
                    ArrayStruct orig_ = (ArrayStruct) ret_;
                    a_.setStruct(orig_.swallowCopy());
                } else {
                    a_.setStruct(ret_);
                }
                return a_;
            }
        }
        if (StringList.quickEq(aliasAnnotated_, idClassNameFound_)) {
            if (StringList.quickEq(aliasGetAnnotations_, _methodId.getName())) {
                _cont.setCallingState(new CustomReflectMethod(ReflectingType.ANNOTATION, _previous, _firstArgs, false));
                return new Argument();
            }
            if (StringList.quickEq(aliasGetAnnotationsParam_, _methodId.getName())) {
                _cont.setCallingState(new CustomReflectMethod(ReflectingType.ANNOTATION_PARAM, _previous, _firstArgs, false));
                return new Argument();
            }
            String fileName_ = NumParsers.getAnnotated(_previous.getStruct()).getFileName();
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
                if (isNotEnumType(cl_, r_)) {
                    return new Argument();
                }
                Argument clArg_ = _firstArgs.first();
                return getEnumValue(_exit,enumName_,r_, clArg_, _cont);
            }
            if (StringList.quickEq(aliasEnumsValues_, _methodId.getName())) {
                ClassMetaInfo cl_ = NumParsers.getClass(_previous.getStruct());
                String enumName_ = cl_.getName();
                ExecRootBlock r_ = classes_.getClassBody(enumName_);
                if (isNotEnumType(cl_, r_)) {
                    return new Argument();
                }
                String className_ = r_.getWildCardElement();
                return getEnumValues(_exit,className_,r_, _cont);
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
                    Argument a_ = new Argument();
                    a_.setStruct(ExecutingUtil.getClassMetaInfo(_cont,clDyn_));
                    return a_;
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
                Argument a_ = new Argument();
                a_.setStruct(ExecutingUtil.getClassMetaInfo(_cont,res_));
                return a_;
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
                CustList<GeneType> need_ = new CustList<GeneType>();
                if (!(type_ instanceof ExecRootBlock)) {
                    return new Argument(ApplyCoreMethodUtil.defaultInstance(_cont,id_,_firstArgs));
                }
                CustList<ExecRootBlock> needRoot_;
                needRoot_ = ((ExecRootBlock)type_).getSelfAndParentTypes();
                ExecRootBlock firstType_ = needRoot_.first();
                first_ = firstType_.getFullName();
                for (ExecRootBlock r: needRoot_) {
                    need_.add(r);
                }
                if (!_firstArgs.isEmpty()) {
                    Struct par_ = _firstArgs.first().getStruct();
                    if (type_.withoutInstance()) {
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
                        String argCl_ = stds_.getStructClassName(par_, _cont);
                        //From analyze
                        StringList inners_ = StringExpUtil.getAllPartInnerTypes(className_);
                        String param_ = StringList.join(inners_.mid(0, inners_.size() - 2), "");
                        if (!ExecTemplates.isCorrectExecute(argCl_, param_, _cont)) {
                            _cont.setException(new ErrorStruct(_cont,cast_));
                            return Argument.createVoid();
                        }
                    }
                    Initializer in_ = _cont.getInit();
                    String genStr_ = type_.getGenericString();
                    String form_ = ExecTemplates.quickFormat(className_, genStr_, _cont);
                    par_ = in_.processInit(_cont, par_, form_, EMPTY_STRING, 0);
                    Argument a_ = new Argument();
                    a_.setStruct(par_);
                    return a_;
                }
                Struct parent_ = NullStruct.NULL_VALUE;
                int start_ = 0;
                if (type_.withoutInstance()) {
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
                        Struct staticField_ = _cont.getClasses().getStaticField(new ClassField(idCl_, fieldName_));
                        parent_ = Argument.getNull(staticField_);
                        start_ = 1;
                    }
                }
                Initializer in_ = _cont.getInit();
                for (GeneType r: need_.mid(start_)) {
                    String genStr_ = r.getGenericString();
                    String form_ = ExecTemplates.quickFormat(className_, genStr_, _cont);
                    parent_ = in_.processInit(_cont, parent_, form_, EMPTY_STRING, 0);
                }
                Argument a_ = new Argument();
                a_.setStruct(parent_);
                return a_;
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
                    String className_ = m_.getClassName();
                    className_ = StringExpUtil.getIdFromAllTypes(className_);
                    ExecRootBlock e_ = classes_.getClassBody(className_);
                    if (e_ == null) {
                        _cont.setCallingState(new CustomReflectMethod(ReflectingType.STD_FCT, _previous, _firstArgs, false));
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
                if (m_.isStaticCall()) {
                    _cont.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _previous, _firstArgs, false));
                    return new Argument();
                }
                _cont.setCallingState(new CustomReflectMethod(ReflectingType.METHOD, _previous, _firstArgs, false));
                return new Argument();
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
                if (m_.isStaticCall()) {
                    _cont.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _previous, _firstArgs, false));
                    return new Argument();
                }
                _cont.setCallingState(new CustomReflectMethod(ReflectingType.DIRECT, _previous, _firstArgs, false));
                return new Argument();
            }
        }
        Struct prev_ =_previous.getStruct();
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
        ExecRootBlock e_ = classes_.getClassBody(idClassNameFound_);
        if (e_ == null) {
            ClassMethodId dyn_ = new ClassMethodId(idClassNameFound_, _methodId);
            ResultErrorStd res_ = LgNames.invokeMethod(_cont, dyn_, _previous.getStruct(), Argument.toArgArray(_firstArgs));
            Argument argRes_ = new Argument();
            argRes_.setStruct(res_.getResult());
            return argRes_;
        }
        CustList<ExecOverridableBlock> methods_ = ExecBlock.getDeepMethodBodiesById(_cont, idClassNameFound_, _methodId);
        if (methods_.isEmpty()) {
            //static enum methods
            String values_ = _cont.getStandards().getAliasEnumValues();
            if (StringList.quickEq(_methodId.getName(), values_)) {
                String className_ = e_.getWildCardElement();
                return getEnumValues(_exit,className_,e_, _cont);
            }
            Argument arg_ = _firstArgs.first();
            return getEnumValue(_exit,idClassNameFound_,e_, arg_, _cont);
        }
        if (prev_ instanceof AbstractFunctionalInstance) {
            ExecOverridableBlock gene_ = methods_.first();
            if (gene_.isAbstractMethod()) {
                Argument fct_ = new Argument(((AbstractFunctionalInstance)prev_).getFunctional());
                return prepareCallDyn(fct_, _firstArgs, _cont);
            }
        }
        if (_methodId.getKind() == MethodAccessKind.STATIC_CALL) {
            _cont.setCallingState(new CustomFoundCast(classFound_, _methodId, _firstArgs));
            return Argument.createVoid();
        }
        _cont.setCallingState(new CustomFoundMethod(_previous, classFound_, _methodId, _firstArgs, _right));
        return Argument.createVoid();
    }

    public static boolean isNotEnumType(ClassMetaInfo cl_, ExecRootBlock r_) {
        return r_ == null || !cl_.isTypeEnum();
    }

    public static void checkParametersOperators(AbstractExiting _exit,ContextEl _conf, ClassMethodId _clMeth,
                                         Argument _previous, CustList<Argument> _firstArgs) {
        if (_exit.hasToExit(_clMeth.getClassName())) {
            return;
        }
        MethodId id_ = _clMeth.getConstraints();
        String classNameFound_ = _clMeth.getClassName();
        classNameFound_ = _clMeth.formatType(classNameFound_,_conf);
        checkParameters(_conf, classNameFound_, id_, _previous, _firstArgs, CallPrepareState.OPERATOR, null, null);
    }

    public static void checkParametersCtors(ContextEl _conf, String _classNameFound, Identifiable _methodId,
                                            Argument _previous, CustList<Argument> _firstArgs,
                                            InstancingStep _kindCall, Argument _right) {
        checkParameters(_conf, _classNameFound, _methodId, _previous, _firstArgs, CallPrepareState.CTOR, _kindCall, _right);
    }
    private static String checkParameters(ContextEl _conf, String _classNameFound, Identifiable _methodId,
                                          Argument _previous, CustList<Argument> _firstArgs,
                                          CallPrepareState _state,
                                          InstancingStep _kindCall, Argument _right) {
        String classFormat_ = checkParams(_conf,_classNameFound,_methodId,_previous,_firstArgs,_right);
        if (_conf.callsOrException()) {
            return classFormat_;
        }
        if (_state == CallPrepareState.METHOD) {
            return classFormat_;
        }
        if (_state == CallPrepareState.CTOR) {
            String idClass_ = StringExpUtil.getIdFromAllTypes(_classNameFound);
            ExecRootBlock execSuperClass_ = _conf.getClasses().getClassBody(idClass_);
            _conf.setCallingState(new CustomFoundConstructor(_classNameFound, execSuperClass_, EMPTY_STRING, -1, (ConstructorId) _methodId, _previous, _firstArgs, _kindCall));
            return "";
        }
        if (StringList.quickEq(_classNameFound,_conf.getStandards().getAliasObject())) {
            _conf.setCallingState(new CustomFoundMethod(Argument.createVoid(), _classNameFound, (MethodId) _methodId, _firstArgs, _right));
            return "";
        }
        _conf.setCallingState(new CustomFoundCast(_classNameFound, (MethodId) _methodId, _firstArgs));
        return "";
    }

    private static String checkParams(ContextEl _conf, String _classNameFound, Identifiable _methodId,
                                      Argument _previous, CustList<Argument> _firstArgs,
                                      Argument _right) {
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCastType();
        String classFormat_ = _classNameFound;
        if (!_methodId.isStaticMethod()) {
            String className_ = stds_.getStructClassName(_previous.getStruct(), _conf);
            classFormat_ = ExecTemplates.getQuickFullTypeByBases(className_, classFormat_, _conf);
            if (classFormat_.isEmpty()) {
                _conf.setException(new ErrorStruct(_conf,cast_));
                return "";
            }
        }
        if (!ExecTemplates.okArgs(_methodId,false,classFormat_,_firstArgs, _conf, _right)) {
            return "";
        }
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
            String typeFct_ = lgNames_.getStructClassName(ls_, _conf);
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
            Argument result_ = new Argument();
            LambdaConstructorStruct l_ = (LambdaConstructorStruct) ls_;
            String forId_ = l_.getFormClassName();
            if (forId_.startsWith(ARR)) {
                Ints dims_ = new Ints();
                String size_;
                size_ = lgNames_.getAliasBadSize();
                for (Argument a: _values) {
                    int dim_ = ClassArgumentMatching.convertToNumber(a.getStruct()).intStruct();
                    if (dim_ < 0) {
                        String mess_ = StringList.concat(Long.toString(dim_),"<0");
                        _conf.setException(new ErrorStruct(_conf,mess_,size_));
                        return result_;
                    }
                    dims_.add(dim_);
                }
                String c_ = forId_.substring(ARR.length());
                result_.setStruct(ExecTemplates.newCustomArray(c_, dims_, _conf));
                return result_;
            }
            Argument pr_ = new Argument();
            pr_.setStruct(l_.getMetaInfo());
            Argument instance_ = l_.getInstanceCall();
            if (l_.isSafeInstance()&&instance_.isNull()) {
                String last_ = StringExpUtil.getAllTypes(l_.getClassName(_conf)).last();
                return new Argument(PrimitiveTypeUtil.defaultValue(last_,_conf));
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
                    Argument arg_ = new Argument();
                    arg_.setStruct(BooleanStruct.of(res_));
                    return arg_;
                }
                return new Argument(realInstance_.getStruct().getParent());
            }
            boolean static_ = l_.isStaticField();
            int nbAncestors_ = l_.getAncestor();
            String clName_ = idField_.getClassName();
            Argument pr_ = new Argument();
            pr_.setStruct(l_.getMetaInfo());
            ReflectingType type_;
            if (aff_) {
                type_ = ReflectingType.SET_FIELD;
            } else {
                type_ = ReflectingType.GET_FIELD;
            }
            Argument instance_ = l_.getInstanceCall();
            if (l_.isSafeInstance()&&instance_.isNull()) {
                String last_ = StringExpUtil.getAllTypes(l_.getClassName(_conf)).last();
                return new Argument(PrimitiveTypeUtil.defaultValue(last_,_conf));
            }
            CustList<Argument> nList_ = new CustList<Argument>();
            Argument realInstance_;
            if (static_) {
                realInstance_ = new Argument();
            } else if (!l_.isShiftInstance()) {
                realInstance_ = new Argument();
                realInstance_.setStruct(instance_.getStruct());
            } else {
                realInstance_ = new Argument();
                realInstance_.setStruct(_values.first().getStruct());
            }
            if (!static_) {
                Struct value_ = realInstance_.getStruct();
                realInstance_.setStruct(ExecTemplates.getParent(nbAncestors_, clName_, value_, _conf));
                if (_conf.callsOrException()) {
                    return new Argument();
                }
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
            Argument pr_ = new Argument();
            pr_.setStruct(l_.getMetaInfo());
            Argument instance_ = new Argument();
            instance_.setStruct(l_.getInstanceCall().getStruct());
            if (l_.isSafeInstance()&&instance_.isNull()) {
                String last_ = StringExpUtil.getAllTypes(l_.getClassName(_conf)).last();
                return new Argument(PrimitiveTypeUtil.defaultValue(last_,_conf));
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
                if (!static_) {
                    Struct value_ = instance_.getStruct();
                    instance_.setStruct(ExecTemplates.getParent(nbAncestors_, id_, value_, _conf));
                    if (_conf.callsOrException()) {
                        return new Argument();
                    }
                }
                nList_.add(instance_);
                nList_.add(new Argument(arr_));
                return redirect(_conf, l_, pr_, nList_);
            }
            if (FunctionIdUtil.isOperatorName(fid_)) {
                ArrayStruct arr_ = new ArrayStruct(new Struct[_values.size()+1],obj_);
                int i_ = 1;
                arr_.getInstance()[0] = instance_.getStruct();
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
            Argument firstValue_ = new Argument();
            firstValue_.setStruct(_values.first().getStruct());
            Struct value_ = firstValue_.getStruct();
            firstValue_.setStruct(ExecTemplates.getParent(nbAncestors_, id_, value_, _conf));
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
                arr_ = getElement(arr_,ind_,_conf);
                if (_conf.callsOrException()) {
                    return new Argument();
                }
            }
            Struct ind_ = argArr_.getInstance()[len_];
            if (StringList.quickEq(_l.getFid().getName(),"[]")) {
                return new Argument(getElement(arr_,ind_,_conf));
            }
            Struct right_ = argArr_.getInstance()[len_ + 1];
            setElement(arr_,ind_, right_,_conf);
            return new Argument(right_);
        }
        if (_l.isStaticCall()) {
            if (_l.isDirectCast()) {
                _conf.setCallingState(new CustomReflectMethod(ReflectingType.CAST_DIRECT, _pr, _nList, true));
                return new Argument();
            }
            if (_l.isExpCast()) {
                _conf.setCallingState(new CustomReflectMethod(ReflectingType.CAST, _pr, _nList, true));
                return new Argument();
            }
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
    public static Struct getElement(Struct _struct, Struct _index, ContextEl _conf) {
        Struct elt_ = ExecTemplates.gearErrorWhenIndex(_struct, _index, _conf);
        _conf.getInitializingTypeInfos().addSensibleField(_struct, elt_);
        return elt_;
    }
    public static void setElement(Struct _struct, Struct _index, Struct _value, ContextEl _conf) {
        ExecTemplates.gearErrorWhenContain(_struct, _index, _value, _conf);
    }
    public static Argument getEnumValues(AbstractExiting _exit,String _class,ExecRootBlock _root, ContextEl _conf) {
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
        Argument argres_ = new Argument();
        argres_.setStruct(new ArrayStruct(o_,clArr_));
        return argres_;
    }
    public static Argument getEnumValue(AbstractExiting _exit,String _class, ExecRootBlock _root,Argument _name, ContextEl _conf) {
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
                Argument argres_ = new Argument();
                Struct str_ = classes_.getStaticField(new ClassField(enumName_, fieldName_),b.getImportedClassName(),_conf);
                _conf.getInitializingTypeInfos().addSensibleField(_conf,enumName_, str_);
                argres_.setStruct(str_);
                return argres_;
            }
        }
        return new Argument();
    }
    public static Argument getField(FieldMetaInfo _meta, Argument _previous, ContextEl _conf) {
        String baseClass_ = _meta.getDeclaringClass();
        baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
        String fieldName_ = _meta.getName();
        boolean isStaticField_ = _meta.isStaticField();
        String type_ = _meta.getType();
        return getField(new DefaultSetOffset(_conf),new DefaultExiting(_conf),baseClass_, fieldName_, isStaticField_,type_, _previous, _conf, -1);
    }
    public static Argument getField(AbstractSetOffset _setOffset,AbstractExiting _exit,String _className, String _fieldName, boolean _isStaticField, String _ret, Argument _previous, ContextEl _conf, int _possibleOffset) {
        if (_possibleOffset > -1) {
            _setOffset.setOffset(_possibleOffset);
        }

        if (_isStaticField) {
            return getStaticField(_exit,_className, _fieldName, _ret, _conf);
        }
        return getInstanceField(_className, _fieldName, _previous, _conf);
    }

    public static Argument getInstanceField(String _className, String _fieldName, Argument _previous, ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCastType();
        Argument a_;
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        Struct previous_ = _previous.getStruct();
        String argClassName_ = previous_.getClassName(_conf);
        if (!(previous_ instanceof FieldableStruct)) {
            if (previous_ == NullStruct.NULL_VALUE) {
                String npe_;
                npe_ = stds_.getAliasNullPe();
                _conf.setException(new ErrorStruct(_conf,npe_));
                return Argument.createVoid();
            }
            String base_ = StringExpUtil.getIdFromAllTypes(argClassName_);
            _conf.setException(new ErrorStruct(_conf, StringList.concat(base_,RETURN_LINE,_className,RETURN_LINE),cast_));
            return _previous;
        }
        String base_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        ClassFieldStruct entry_ = ((FieldableStruct) previous_).getEntryStruct(fieldId_);
        if (entry_ == null) {
            _conf.setException(new ErrorStruct(_conf, StringList.concat(base_,RETURN_LINE,_className,RETURN_LINE),cast_));
            return _previous;
        }
        Struct struct_ = entry_.getStruct();
        _conf.getInitializingTypeInfos().addSensibleField(previous_, struct_);
        a_ = new Argument();
        a_.setStruct(struct_);
        return a_;
    }

    public static Argument getStaticField(AbstractExiting _exit,String _className, String _fieldName, String _ret,ContextEl _conf) {
        Argument a_;
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        if (_exit.hasToExit(_className)) {
            return Argument.createVoid();
        }
        Struct struct_ = classes_.getStaticField(fieldId_,_ret, _conf);
        _conf.getInitializingTypeInfos().addSensibleField(_conf,fieldId_.getClassName(), struct_);
        a_ = new Argument();
        a_.setStruct(struct_);
        return a_;
    }

    public static Argument setField(FieldMetaInfo _meta, Argument _previous, Argument _right,ContextEl _conf) {
        String baseClass_ = _meta.getDeclaringClass();
        baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
        String fieldName_ = _meta.getName();
        boolean isStaticField_ = _meta.isStaticField();
        boolean isFinalField_ = _meta.isFinalField();
        String type_ = _meta.getType();
        return setField(new DefaultSetOffset(_conf),new DefaultExiting(_conf),baseClass_, fieldName_, isStaticField_, isFinalField_, true, type_, _previous, _right, _conf, -1);
    }
    public static Argument setField(AbstractSetOffset _setOffset,AbstractExiting _exit,String _className, String _fieldName,
                                    boolean _isStaticField, boolean _finalField, boolean _failIfFinal,
                                    String _returnType, Argument _previous,
                                    Argument _right,ContextEl _conf, int _possibleOffset) {
        if (_possibleOffset > -1) {
            _setOffset.setOffset(_possibleOffset);
        }
        if (_isStaticField) {
            return setStaticField(_exit,_className, _fieldName, _finalField, _failIfFinal, _returnType, _right, _conf);
        }
        return setInstanceField(_className, _fieldName, _returnType, _previous, _right, _conf);
    }

    public static Argument setInstanceField(String _className, String _fieldName, String _returnType, Argument _previous, Argument _right, ContextEl _conf) {
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        LgNames stds_ = _conf.getStandards();
        Struct previous_ = _previous.getStruct();
        String argClassName_ = previous_.getClassName(_conf);
        String cast_;
        cast_ = stds_.getAliasCastType();
        if (!(previous_ instanceof FieldableStruct)) {
            if (previous_ == NullStruct.NULL_VALUE) {
                String npe_;
                npe_ = stds_.getAliasNullPe();
                _conf.setException(new ErrorStruct(_conf,npe_));
                return Argument.createVoid();
            }
            String base_ = StringExpUtil.getIdFromAllTypes(argClassName_);
            _conf.setException(new ErrorStruct(_conf, StringList.concat(base_,RETURN_LINE,_className,RETURN_LINE),cast_));
            return Argument.createVoid();
        }
        String base_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        String classNameFound_ = _className;
        ClassFieldStruct entry_ = ((FieldableStruct) previous_).getEntryStruct(fieldId_);
        if (entry_ == null) {
            _conf.setException(new ErrorStruct(_conf, StringList.concat(base_,RETURN_LINE,classNameFound_,RETURN_LINE),cast_));
            return Argument.createVoid();
        }
        classNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, classNameFound_, _conf);
        String fieldType_;
        fieldType_ = _returnType;
        fieldType_ = ExecTemplates.quickFormat(classNameFound_, fieldType_, _conf);
        if (!ExecTemplates.checkQuick(fieldType_, _right, _conf)) {
            return Argument.createVoid();
        }
        if (_conf.getInitializingTypeInfos().isContainedSensibleFields(previous_)) {
            _conf.getInitializingTypeInfos().failInitEnums();
            return _right;
        }
        entry_.setStruct(_right.getStruct());
        return _right;
    }

    public static Argument setStaticField(AbstractExiting _exit,String _className, String _fieldName, boolean _finalField, boolean _failIfFinal, String _returnType, Argument _right, ContextEl _conf) {
        Classes classes_ = _conf.getClasses();
        ClassField fieldId_ = new ClassField(_className, _fieldName);
        LgNames stds_ = _conf.getStandards();
        if (_finalField && _failIfFinal) {
            String npe_;
            npe_ = stds_.getAliasIllegalArg();
            _conf.setException(new ErrorStruct(_conf,npe_));
            return Argument.createVoid();
        }
        if (_exit.hasToExit(_className)) {
            return Argument.createVoid();
        }
        String fieldType_;
        fieldType_ = _returnType;
        if (!ExecTemplates.checkQuick(fieldType_, _right, _conf)) {
            return Argument.createVoid();
        }
        if (_conf.getInitializingTypeInfos().isSensibleField(_conf,fieldId_.getClassName())) {
            _conf.getInitializingTypeInfos().failInitEnums();
            return _right;
        }
        classes_.initializeStaticField(fieldId_, _right.getStruct());
        return _right;
    }
}
