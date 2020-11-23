package code.expressionlanguage.exec.opers;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.FormattedParameters;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class ExecInvokingOperation extends ExecMethodOperation implements ExecPossibleIntermediateDotted, AtomicExecCalculableOperation {
    private boolean intermediate;

    protected ExecInvokingOperation(
            ExecOperationContent _opCont, boolean _intermediateDottedOperation) {
        super(_opCont);
        intermediate = _intermediateDottedOperation;
    }

    protected ExecInvokingOperation(int _indexChild, ExecClassArgumentMatching _res, int _order,
                                 boolean _intermediate) {
        super(_indexChild,_res,_order);
        intermediate = _intermediate;
    }

    protected CustList<Argument> fectchInstFormattedArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _className, ExecRootBlock _rootBlock, String _lastType, int _naturalVararg) {
        String lastType_ = ExecTemplates.quickFormat(_rootBlock,_className, _lastType);
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
            } else {
                args_.add(getArgument(_all,c));
                filter_.add(c);
            }
        }
        while (!named_.isEmpty()) {
            int minIndex_ = named_.first().getIndex();
            int size_ = named_.size();
            int i_ = 0;
            for (int i = 1; i < size_; i++) {
                ExecNamedArgumentOperation elt_ = named_.get(i);
                int index_ = elt_.getIndex();
                if (index_ < minIndex_) {
                    minIndex_ = index_;
                    i_ = i;
                }
            }
            args_.add(getArgument(_all,named_.get(i_)));
            named_.remove(i_);
        }
        return out_;
    }
    static CustList<Argument> listArguments(CustList<ExecOperationNode> _children, int _natVararg, String _lastType, CustList<Argument> _nodes) {
        if (_natVararg > -1) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Struct> optArgs_ = new CustList<Struct>();
            int lenCh_ = _children.size();
            int natVarArg_ = _natVararg;
            for (int i = IndexConstants.FIRST_INDEX; i < lenCh_; i++) {
                if (ExecConstLeafOperation.isFilter(_children.get(i))) {
                    natVarArg_++;
                    continue;
                }
                Argument a_ = _nodes.get(i);
                if (i >= natVarArg_) {
                    optArgs_.add(a_.getStruct());
                } else {
                    firstArgs_.add(a_);
                }
            }
            String clArr_ = StringExpUtil.getPrettyArrayType(_lastType);
            ArrayStruct str_ = NumParsers.setElements(optArgs_,clArr_);
            Argument argRem_ = new Argument(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        int lenCh_ = _children.size();
        for (int i = IndexConstants.FIRST_INDEX; i < lenCh_; i++) {
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
        if (ExecTemplates.okArgsSet(_constId, _className, _arguments, _conf) != null) {
            return new Argument();
        }
        ResultErrorStd res_ = ApplyCoreMethodUtil.newInstance(_conf, _constId, Argument.toArgArray(_arguments));
        return new Argument(res_.getResult());
    }

    public static Argument instancePrepareCust(ContextEl _conf, String _className, ExecTypeFunction _pair,
                                               Argument _previous, CustList<Argument> _arguments, String _fieldName,
                                               int _blockIndex) {
        LgNames stds_ = _conf.getStandards();
        ExecRootBlock type_ = _pair.getType();
        checkNeeded(_conf, _className, _previous, stds_, type_);
        if (_conf.callsOrException()) {
            return new Argument();
        }
        ExecNamedFunctionBlock fct_ = _pair.getFct();
        Parameters parameters_ = ExecTemplates.okArgsSet(type_, fct_, _className, null, _arguments, _conf, null, true);
        if (parameters_.getError() != null) {
            return new Argument();
        }
        Argument needed_;
        if (type_.withoutInstance()) {
            needed_ = new Argument();
        } else {
            needed_ = new Argument(Argument.getNullableValue(_previous).getStruct());
        }
        _conf.setCallingState(new CustomFoundConstructor(_className, _pair, _fieldName, _blockIndex, needed_, parameters_, InstancingStep.NEWING));
        return Argument.createVoid();
    }

    private static void checkNeeded(ContextEl _conf, String _className, Argument _previous, LgNames _stds, ExecRootBlock _g) {
        if (_g.withoutInstance()) {
            return;
        }
        //From analyze
        StringList parts_ = StringExpUtil.getAllPartInnerTypes(_className);
        String param_ = StringUtil.join(parts_.left(parts_.size()-2), "");
        if (_previous.isNull()) {
            String npe_;
            npe_ = _stds.getContent().getCoreNames().getAliasNullPe();
            _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_)));
            return;
        }
        String arg_ = _previous.getStruct().getClassName(_conf);
        if (!ExecTemplates.isCorrectExecute(arg_, param_, _conf)) {
            String cast_;
            cast_ = _stds.getContent().getCoreNames().getAliasCastType();
            _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, StringUtil.concat(arg_, RETURN_LINE, param_, RETURN_LINE), cast_)));
        }
    }

    public static ExecOverrideInfo polymorphOrSuper(boolean _super,ContextEl _conf, Struct _previous, String _className, ExecTypeFunction _named) {
        if (_super) {
            return new ExecOverrideInfo(_className,_named);
        }
        return polymorph(_conf, _previous, _named);
    }
    public static ExecOverrideInfo polymorph(ContextEl _conf, Struct _previous, ExecTypeFunction _named) {
        String argClassName_ = Argument.getNull(_previous).getClassName(_conf);
        String base_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        ExecRootBlock type_ = _named.getType();
        ExecNamedFunctionBlock fct_ = _named.getFct();
        ExecOverrideInfo res_ = type_.getRedirections().getVal(fct_,base_);
        if (res_ != null) {
            return res_;
        }
        return new ExecOverrideInfo(type_.getGenericString(),_named);
    }
    public static Argument callStd(AbstractExiting _exit, ContextEl _cont, String _classNameFound, MethodId _methodId, Argument _previous, CustList<Argument> _firstArgs) {
        ExecTemplates.checkParams(_cont, _classNameFound, _methodId, _previous, _firstArgs);
        if (_cont.callsOrException()) {
            return Argument.createVoid();
        }
        String idClassNameFound_ = StringExpUtil.getIdFromAllTypes(_classNameFound);

        ClassMethodId dyn_ = new ClassMethodId(idClassNameFound_, _methodId);
        ResultErrorStd res_ = LgNames.invokeMethod(_cont, dyn_, _previous.getStruct(), _exit, Argument.toArgArray(_firstArgs));
        return new Argument(res_.getResult());
    }

    public static Argument tryGetEnumValues(AbstractExiting _exit, ContextEl _cont, ExecRootBlock _r, ClassCategory _category) {
        if (isNotEnumType(_r, _category)) {
            return new Argument();
        }
        String className_ = _r.getWildCardElement();
        return getEnumValues(_exit,className_,_r, _cont);
    }

    public static Argument tryGetEnumValue(AbstractExiting _exit, ContextEl _cont, ExecRootBlock _r, ClassCategory _category, Argument _clArg) {
        if (isNotEnumType(_r, _category)) {
            return new Argument();
        }
        String enumName_ = _r.getFullName();
        return getEnumValue(_exit,enumName_,_r, _clArg, _cont);
    }


    public static Argument callPrepare(AbstractExiting _exit, ContextEl _cont, String _classNameFound, ExecTypeFunction _rootBlock, Argument _previous, Cache _cache, CustList<Argument> _firstArgs, Argument _right, MethodAccessKind _kind, String _name) {
        ExecRootBlock type_ = _rootBlock.getType();
        ExecNamedFunctionBlock fct_ = _rootBlock.getFct();
        if (!(fct_ instanceof ExecOverridableBlock)&&!(fct_ instanceof ExecAnonymousFunctionBlock)) {
            if (fct_ instanceof ExecOperatorBlock) {
                FormattedParameters classFound_ = checkParameters(_cont, _classNameFound, _rootBlock, _previous, _cache,_firstArgs, CallPrepareState.METHOD,null, _right, _kind);
                if (_cont.callsOrException()) {
                    return Argument.createVoid();
                }
                _cont.setCallingState(new CustomFoundMethod(_previous, classFound_.getFormattedClass(), _rootBlock, classFound_.getParameters()));
                return Argument.createVoid();
            }
            //static enum methods
            LgNames stds_ = _cont.getStandards();
            if (_firstArgs.size() != 1) {
                return tryGetEnumValues(_exit,_cont,type_,  ClassCategory.ENUM);
            }
            Argument arg_ = _firstArgs.first();
            Struct ex_ = ExecTemplates.checkObjectEx(stds_.getContent().getCharSeq().getAliasString(), arg_, _cont);
            if (ex_ != null) {
                _cont.setCallingState(new CustomFoundExc(ex_));
                return Argument.createVoid();
            }
            return tryGetEnumValue(_exit,_cont,type_, ClassCategory.ENUM, arg_);
        }
        if (FunctionIdUtil.isOperatorName(_name)) {
            FormattedParameters classFound_ = checkParameters(_cont, _classNameFound, _rootBlock, _previous,_cache, _firstArgs, CallPrepareState.METHOD,null, _right, _kind);
            if (_cont.callsOrException()) {
                return Argument.createVoid();
            }
            _cont.setCallingState(new CustomFoundMethod(_previous, classFound_.getFormattedClass(), _rootBlock, classFound_.getParameters()));
            return Argument.createVoid();
        }
        FormattedParameters classFound_ = checkParameters(_cont, _classNameFound, _rootBlock, _previous, _cache,_firstArgs, CallPrepareState.METHOD,null, _right, _kind);
        if (_cont.callsOrException()) {
            return Argument.createVoid();
        }
        Struct prev_ =_previous.getStruct();
        if (fct_ instanceof ExecOverridableBlock&&prev_ instanceof AbstractFunctionalInstance) {
            ExecOverridableBlock gene_ = (ExecOverridableBlock) fct_;
            if (gene_.isAbstractMethod()) {
                Argument fctInst_ = new Argument(((AbstractFunctionalInstance)prev_).getFunctional());
                return prepareCallDyn(fctInst_, _firstArgs, _cont);
            }
        }
        if (_kind == MethodAccessKind.STATIC_CALL) {
            _cont.setCallingState(new CustomFoundCast(classFound_.getFormattedClass(), _rootBlock, classFound_.getParameters()));
            return Argument.createVoid();
        }
        _cont.setCallingState(new CustomFoundMethod(_previous, classFound_.getFormattedClass(), _rootBlock, classFound_.getParameters()));
        return Argument.createVoid();
    }

    private static boolean isNotEnumType(ExecRootBlock _r, ClassCategory _category) {
        return _r == null || _category != ClassCategory.ENUM;
    }

    public static void checkParametersOperators(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named,
                                                CustList<Argument> _firstArgs, String _className, MethodAccessKind _kind) {
        String classNameFound_ = _className;
        classNameFound_ = ClassMethodId.formatType(classNameFound_,_conf, _kind);
        checkParametersOperatorsFormatted(_exit, _conf, _named, _firstArgs, classNameFound_, _kind);
    }

    public static void checkParametersOperatorsFormatted(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named, CustList<Argument> _firstArgs, String _classNameFound, MethodAccessKind _kind) {
        if (_exit.hasToExit(_classNameFound)) {
            return;
        }
        checkParameters(_conf, _classNameFound, _named, Argument.createVoid(),null, _firstArgs, CallPrepareState.OPERATOR,null, null, _kind);
    }

    public static void checkParametersCtors(ContextEl _conf, String _classNameFound,
                                            ExecTypeFunction _named,
                                            CustList<Argument> _firstArgs,
                                            InstancingStep _kindCall) {
        Argument arg_ = _conf.getLastPage().getGlobalArgument();
        checkParameters(_conf, _classNameFound, _named, arg_,null, _firstArgs,CallPrepareState.CTOR, _kindCall,null, MethodAccessKind.INSTANCE);
    }

    public static FormattedParameters checkParameters(ContextEl _conf, String _classNameFound, ExecTypeFunction _methodId,
                                                      Argument _previous, Cache _cache, CustList<Argument> _firstArgs,
                                                      CallPrepareState _state,
                                                      InstancingStep _kindCall, Argument _right, MethodAccessKind _kind) {
        ExecRootBlock type_ = _methodId.getType();
        ExecNamedFunctionBlock fct_ = _methodId.getFct();
        FormattedParameters classFormat_ = ExecTemplates.checkParams(_conf,_classNameFound,type_,fct_,_previous,_cache,_firstArgs,_right, _kind);
        if (_conf.callsOrException()) {
            return classFormat_;
        }
        if (_state == CallPrepareState.METHOD) {
            return classFormat_;
        }
        Parameters parameters_ = classFormat_.getParameters();
        if (_state == CallPrepareState.CTOR) {
            _conf.setCallingState(new CustomFoundConstructor(_classNameFound, _methodId, EMPTY_STRING, -1, _previous, parameters_, _kindCall));
            return classFormat_;
        }
        if (type_ == null) {
            _conf.setCallingState(new CustomFoundMethod(Argument.createVoid(), _classNameFound, _methodId, parameters_));
            return classFormat_;
        }
        _conf.setCallingState(new CustomFoundCast(_classNameFound, _methodId, parameters_));
        return classFormat_;
    }

    public static Argument getInstanceCall(Argument _previous, ContextEl _conf) {
        Struct ls_ = _previous.getStruct();
        if (ls_ instanceof LambdaStruct) {
            return ((LambdaStruct) ls_).getInstanceCall();
        }
        LgNames lgNames_ = _conf.getStandards();
        String null_;
        null_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_)));
        return Argument.createVoid();
    }
    public static Argument getMetaInfo(Argument _previous, ContextEl _conf) {
        Struct ls_ = _previous.getStruct();
        if (ls_ instanceof LambdaStruct) {
            return new Argument(((LambdaStruct) ls_).getMetaInfo());
        }
        LgNames lgNames_ = _conf.getStandards();
        String null_;
        null_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_)));
        return Argument.createVoid();
    }
    public static Argument prepareCallDyn(Argument _previous, CustList<Argument> _values, ContextEl _conf) {
        CustList<Argument> values_ = new CustList<Argument>();
        for (Argument v: _values) {
            values_.add(Argument.getNullableValue(v));
        }
        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        LgNames lgNames_ = _conf.getStandards();
        if (ls_ instanceof LambdaStruct) {
            String typeFct_ = ls_.getClassName(_conf);
            StringList parts_ = StringExpUtil.getAllTypes(typeFct_);
            CustList<String> paramsFct_ = parts_.leftMinusOne(parts_.size() - 2);
            int valuesSize_ = values_.size();
            if (valuesSize_ != paramsFct_.size()) {
                String null_ = lgNames_.getContent().getCoreNames().getAliasBadArgNumber();
                _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, ExecTemplates.countDiff(valuesSize_, paramsFct_.size()).toString(), null_)));
                return new Argument();
            }
            for (int i = 0; i < valuesSize_; i++) {
                Argument arg_ = values_.get(i);
                String param_ = paramsFct_.get(i);
                if (!ExecTemplates.checkQuick(param_, arg_, _conf)) {
                    return new Argument();
                }
            }
        }
        if (ls_ instanceof LambdaConstructorStruct) {
            LambdaConstructorStruct l_ = (LambdaConstructorStruct) ls_;
            String forId_ = StringUtil.nullToEmpty(l_.getFormClassName());
            if (forId_.startsWith(ARR)) {
                Ints dims_ = new Ints();
                for (Argument a: values_) {
                    int dim_ = NumParsers.convertToNumber(a.getStruct()).intStruct();
                    dims_.add(dim_);
                }
                String c_ = forId_.substring(ARR.length());
                Struct res_ = ExecTemplates.newCustomArrayOrExc(c_, dims_, _conf);
                if (res_ instanceof ErrorStruct) {
                    _conf.setCallingState(new CustomFoundExc(res_));
                    return new Argument();
                }
                return new Argument(res_);
            }
            Struct metaInfo_ = l_.getMetaInfo();
            Argument instance_ = l_.getInstanceCall();
            if (l_.isSafeInstance()&&instance_.isNull()) {
                String last_ = StringExpUtil.getAllTypes(l_.getClassName(_conf)).last();
                return new Argument(ExecClassArgumentMatching.defaultValue(last_,_conf));
            }
            String obj_ = _conf.getStandards().getContent().getCoreNames().getAliasObject();
            obj_ = StringExpUtil.getPrettyArrayType(obj_);
            ConstructorMetaInfo meta_ = NumParsers.getCtor(metaInfo_);
            if (!l_.isShiftInstance()) {
                ArrayStruct arr_ = feed(values_, obj_);
                CustList<Argument> nList_ = new CustList<Argument>();
                nList_.add(new Argument(arr_));
                _conf.setCallingState(new CustomReflectConstructor(meta_, nList_, true));
                return new Argument();
            }
            ArrayStruct arr_ = feedInserted(values_, instance_.getStruct(), obj_);
            CustList<Argument> nList_ = new CustList<Argument>();
            nList_.add(new Argument(arr_));
            _conf.setCallingState(new CustomReflectConstructor(meta_, nList_, true));
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
                    realInstance_ = ExecTemplates.getFirstArgument(values_);
                }
                if (StringUtil.quickEq(l_.getReturnFieldType(), lgNames_.getContent().getPrimTypes().getAliasPrimBoolean())) {
                    String ownerType_ = StringUtil.nullToEmpty(l_.getOwnerType());
                    boolean res_ = ExecTemplates.safeObject(ownerType_,realInstance_,_conf) == ErrorType.NOTHING;
                    return new Argument(BooleanStruct.of(res_));
                }
                return new Argument(realInstance_.getStruct().getParent());
            }
            boolean static_ = l_.isStaticField();
            int nbAncestors_ = l_.getAncestor();
            String clName_ = StringUtil.nullToEmpty(idField_.getClassName());
            Struct metaInfo_ = l_.getMetaInfo();
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
            if (!static_) {
                Struct value_;
                if (!l_.isShiftInstance()) {
                    value_ = instance_.getStruct();
                } else {
                    value_ = ExecTemplates.getFirstArgument(values_).getStruct();
                }
                realInstance_ = new Argument(ExecTemplates.getParent(nbAncestors_, clName_, value_, _conf));
                if (_conf.callsOrException()) {
                    return new Argument();
                }
            } else {
                realInstance_ = new Argument();
            }
            nList_.add(realInstance_);
            if (aff_) {
                nList_.add(ExecTemplates.getLastArgument(values_));
            }
            FieldMetaInfo method_ = NumParsers.getField(metaInfo_);
            _conf.setCallingState(new CustomReflectField(type_, method_, nList_, true));
            return new Argument();
        }
        if (ls_ instanceof LambdaMethodStruct) {
            LambdaMethodStruct l_ =  (LambdaMethodStruct) ls_;
            int nbAncestors_ = l_.getAncestor();
            String id_;
            if (l_.isStaticCall()) {
                id_ = StringUtil.nullToEmpty(l_.getFormClassName());
            } else {
                id_ = StringExpUtil.getIdFromAllTypes(StringUtil.nullToEmpty(l_.getFormClassName()));
            }
            boolean static_ = l_.getKind() != MethodAccessKind.INSTANCE;
            Struct metaInfo_ = l_.getMetaInfo();
            Struct instanceStruct_ = l_.getInstanceCall().getStruct();
            if (l_.isSafeInstance()&&instanceStruct_ == NullStruct.NULL_VALUE) {
                String last_ = StringExpUtil.getAllTypes(l_.getClassName(_conf)).last();
                return new Argument(ExecClassArgumentMatching.defaultValue(last_,_conf));
            }
            String obj_ = _conf.getStandards().getContent().getCoreNames().getAliasObject();
            obj_ = StringExpUtil.getPrettyArrayType(obj_);
            MethodMetaInfo method_ = NumParsers.getMethod(metaInfo_);
            if (!l_.isShiftInstance()) {
                ArrayStruct arr_ = feed(values_, obj_);
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
                return redirect(_conf, l_, nList_, method_);
            }
            if (FunctionIdUtil.isOperatorName(l_.getMethodName())) {
                ArrayStruct arr_ = feedInserted(values_, instanceStruct_, obj_);
                CustList<Argument> nList_ = new CustList<Argument>();
                nList_.add(new Argument(arr_));
                _conf.setCallingState(new CustomReflectMethod(ReflectingType.DIRECT, method_, nList_, true));
                return new Argument();
            }
            int len_ = Math.max(0, values_.size() - 1);
            ArrayStruct arr_ = ExecTemplates.getArray(values_.leftMinusOne(len_),obj_);
            CustList<Argument> nList_ = new CustList<Argument>();
            Struct value_ = ExecTemplates.getFirstArgument(values_).getStruct();
            Argument firstValue_ = new Argument(ExecTemplates.getParent(nbAncestors_, id_, value_, _conf));
            if (_conf.callsOrException()) {
                return new Argument();
            }
            nList_.add(firstValue_);
            nList_.add(new Argument(arr_));
            return redirect(_conf, l_, nList_, method_);
        }
        String null_;
        null_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_)));
        return Argument.createVoid();
    }

    private static ArrayStruct feed(CustList<Argument> _values, String _obj) {
        ArrayStruct arr_ = new ArrayStruct(_values.size(), _obj);
        int i_ = 0;
        for (Argument v: _values) {
            arr_.set(i_, v.getStruct());
            i_++;
        }
        return arr_;
    }

    private static ArrayStruct feedInserted(CustList<Argument> _values, Struct _instance, String _obj) {
        ArrayStruct arr_ = new ArrayStruct(_values.size()+1, _obj);
        int i_ = 1;
        arr_.set(0, _instance);
        for (Argument v: _values) {
            arr_.set(i_, v.getStruct());
            i_++;
        }
        return arr_;
    }

    private static Argument redirect(ContextEl _conf, LambdaMethodStruct _l, CustList<Argument> _nList, MethodMetaInfo _method) {
        String name_ = _l.getMethodName();
        if (StringUtil.nullToEmpty(_l.getFormClassName()).startsWith(StringExpUtil.ARR_CLASS) && name_.startsWith("[]")) {
            Struct arr_ = ExecTemplates.getFirstArgument(_nList).getStruct();
            ArrayStruct argArr_ = ExecArrayFieldOperation.getArray(ExecTemplates.getArgument(_nList,1).getStruct(),_conf);
            int lastIndex_ = argArr_.getLength() - 1;
            if (lastIndex_ < 0) {
                return new Argument(new IntStruct(ExecArrayFieldOperation.getLength(arr_,_conf)));
            }
            if (StringUtil.quickEq(name_,"[]")) {
                for (int i = 0; i < lastIndex_; i++) {
                    Struct ind_ = argArr_.get(i);
                    arr_ = ExecTemplates.getElement(arr_,ind_,_conf);
                    if (_conf.callsOrException()) {
                        return new Argument();
                    }
                }
                Struct ind_ = argArr_.get(lastIndex_);
                return new Argument(ExecTemplates.getElement(arr_,ind_,_conf));
            }
            int beforeLastIndex_ = lastIndex_ - 1;
            for (int i = 0; i < lastIndex_; i++) {
                Struct ind_ = argArr_.get(i);
                if (i < beforeLastIndex_) {
                    arr_ = ExecTemplates.getElement(arr_, ind_, _conf);
                } else {
                    Struct right_ = argArr_.get(lastIndex_);
                    ExecTemplates.setElement(arr_,ind_, right_,_conf);
                }
                if (_conf.callsOrException()) {
                    return new Argument();
                }
            }
            Struct right_ = argArr_.get(lastIndex_);
            return new Argument(right_);
        }
        if (_method.isDirectCast()) {
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.CAST_DIRECT, _method, _nList, true));
            return new Argument();
        }
        if (_method.getStdCallee() != null) {
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.STD_FCT, _method, _nList, true));
            return new Argument();
        }
        if (_method.getPair().getFct() instanceof ExecAnonymousFunctionBlock) {
            if (_method.isStaticCall()) {
                _conf.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _method, _nList, true));
                return new Argument();
            }
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.DIRECT, _method, _nList, true));
            return new Argument();
        }
        ExecRootBlock e_ = _method.getPair().getType();
        if (e_ instanceof ExecAnnotationBlock) {
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.ANNOT_FCT, _method, _nList, true));
            return new Argument();
        }
        if (_method.getPair().getFct() != null) {
            if (_method.isExpCast()) {
                _conf.setCallingState(new CustomReflectMethod(ReflectingType.CAST, _method, _nList, true));
                return new Argument();
            }
            if (_method.isStaticCall()) {
                _conf.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _method, _nList, true));
                return new Argument();
            }
            if (!_l.isPolymorph()) {
                _conf.setCallingState(new CustomReflectMethod(ReflectingType.DIRECT, _method, _nList, true));
                return new Argument();
            }
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.METHOD, _method, _nList, true));
            return new Argument();
        }
        redirectGenerated(_conf, _nList,true, _method);
        return new Argument();
    }
    public static void redirectGenerated(ContextEl _conf, CustList<Argument> _nList, boolean _lambda, MethodMetaInfo _method) {
        ExecRootBlock e_ = _method.getPair().getType();
        if (e_ != null) {
            _conf.setCallingState(new CustomReflectMethod(ReflectingType.ENUM_METHODS, _method, _nList, _lambda));
            return;
        }
        _conf.setCallingState(new CustomReflectMethod(ReflectingType.CLONE_FCT, _method, _nList, _lambda));
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
        String clArr_ = _class;
        clArr_ = StringExpUtil.getPrettyArrayType(clArr_);
        ArrayStruct array_ = new ArrayStruct(enums_.size(), clArr_);
        int i_ = IndexConstants.FIRST_INDEX;
        for (Struct o: enums_) {
            array_.set(i_,o);
            i_++;
        }
        return new Argument(array_);
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
            if (StringUtil.quickEq(fieldName_, ((StringStruct) name_).getInstance())) {
                Struct str_ = classes_.getStaticField(new ClassField(enumName_, fieldName_),b.getImportedClassName(),_conf);
                _conf.getInitializingTypeInfos().addSensibleField(_conf,enumName_, str_);
                return new Argument(str_);
            }
        }
        return new Argument();
    }
}
