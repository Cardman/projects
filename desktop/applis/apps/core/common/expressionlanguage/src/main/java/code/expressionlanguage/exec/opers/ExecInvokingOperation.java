package code.expressionlanguage.exec.opers;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.*;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
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

    protected ArgumentListCall fectchInstFormattedArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _className, ExecRootBlock _rootBlock, String _lastType, int _naturalVararg) {
        String lastType_ = ExecTemplates.quickFormat(_rootBlock,_className, _lastType);
        return fectchArgs(_nodes, lastType_, _naturalVararg);
    }

    protected ArgumentListCall fetchFormattedArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _pr, String _className, ExecRootBlock _rootBlock, String _lastType, int _naturalVararg) {
        String cl_ = _pr.getClassName(_conf);
        String base_ = StringExpUtil.getIdFromAllTypes(_className);
        String clGen_ = ExecTemplates.getSuperGeneric(cl_, base_, _conf);
        String lastType_ = ExecTemplates.quickFormat(_rootBlock,clGen_, _lastType);
        return fectchArgs(_nodes,lastType_, _naturalVararg);
    }

    protected ArgumentListCall fectchArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _lastType, int _naturalVararg) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ArgumentList argumentList_ = listNamedArguments(_nodes, chidren_);
        CustList<Argument> first_ = argumentList_.getArguments().getArguments();
        CustList<ExecOperationNode> filter_ = argumentList_.getFilter();
        CustList<Argument> res_ = listArguments(filter_, _naturalVararg, _lastType, first_);
        first_.clear();
        first_.addAllElts(res_);
        return argumentList_.getArguments();
    }
    private static ArgumentList listNamedArguments(IdMap<ExecOperationNode, ArgumentsPair> _all, CustList<ExecOperationNode> _children) {
        ArgumentList out_ = new ArgumentList();
        CustList<Argument> args_ = out_.getArguments().getArguments();
        CustList<AbstractWrapper> wrappers_ = out_.getArguments().getWrappers();
        CustList<ExecOperationNode> filter_ = out_.getFilter();
        CustList<ExecNamedArgumentOperation> named_ = new CustList<ExecNamedArgumentOperation>();
        for (ExecOperationNode c: _children) {
            if (c instanceof ExecNamedArgumentOperation) {
                if (!(c.getFirstChild() instanceof ExecWrappOperation)) {
                    named_.add((ExecNamedArgumentOperation)c);
                    filter_.add(c);
                }
            } else if (!(c instanceof ExecWrappOperation)){
                args_.add(getArgument(_all,c));
                filter_.add(c);
            }
        }
        while (!named_.isEmpty()) {
            int minIndex_ = named_.first().getIndex();
            int size_ = named_.size();
            int i_ = 0;
            for (int i = 1; i < size_; i++) {
                int index_ = named_.get(i).getIndex();
                if (index_ < minIndex_) {
                    minIndex_ = index_;
                    i_ = i;
                }
            }
            args_.add(getArgument(_all,named_.get(i_)));
            named_.remove(i_);
        }
        for (ExecOperationNode c: _children) {
            if (c instanceof ExecNamedArgumentOperation) {
                if (c.getFirstChild() instanceof ExecWrappOperation) {
                    named_.add((ExecNamedArgumentOperation)c);
                }
            } else if (c instanceof ExecWrappOperation){
                ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_all, c);
                wrappers_.add(pair_.getWrapper());
            }
        }
        while (!named_.isEmpty()) {
            int minIndex_ = named_.first().getIndex();
            int size_ = named_.size();
            int i_ = 0;
            for (int i = 1; i < size_; i++) {
                int index_ = named_.get(i).getIndex();
                if (index_ < minIndex_) {
                    minIndex_ = index_;
                    i_ = i;
                }
            }
            ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_all, named_.get(i_).getFirstChild());
            wrappers_.add(pair_.getWrapper());
            named_.remove(i_);
        }
        return out_;
    }
    private static CustList<Argument> listArguments(CustList<ExecOperationNode> _children, int _natVararg, String _lastType, CustList<Argument> _nodes) {
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
                                               Argument _previous, ArgumentListCall _arguments, String _fieldName,
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


    public static Argument callPrepare(AbstractExiting _exit, ContextEl _cont, String _classNameFound, ExecTypeFunction _rootBlock, Argument _previous, Cache _cache, ArgumentListCall _firstArgs, Argument _right, MethodAccessKind _kind, String _name) {
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
            if (_firstArgs.getArguments().size() != 1) {
                return tryGetEnumValues(_exit,_cont,type_,  ClassCategory.ENUM);
            }
            Argument arg_ = _firstArgs.getArguments().first();
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
            if (((AbstractFunctionalInstance)prev_).getNamed() == fct_) {
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
                                                IdMap<ExecOperationNode, ArgumentsPair> _nodes, ExecMethodOperation _meth, String _className, MethodAccessKind _kind) {
        CustList<Argument> arguments_ = getArguments(_nodes, _meth);
        ArgumentListCall l_ = new ArgumentListCall();
        l_.getArguments().addAllElts(arguments_);
        checkParametersOperators(_exit, _conf, _named, l_, _className, _kind);
    }
    public static void checkParametersOperators(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named,
                                                ArgumentListCall _firstArgs, String _className, MethodAccessKind _kind) {
        String classNameFound_ = _className;
        classNameFound_ = ClassMethodId.formatType(classNameFound_,_conf, _kind);
        checkParametersOperatorsFormatted(_exit, _conf, _named, _firstArgs, classNameFound_, _kind);
    }

    public static void checkParametersOperatorsFormatted(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named, ArgumentListCall _firstArgs, String _classNameFound, MethodAccessKind _kind) {
        if (_exit.hasToExit(_classNameFound)) {
            return;
        }
        checkParameters(_conf, _classNameFound, _named, Argument.createVoid(),null, _firstArgs, CallPrepareState.OPERATOR,null, null, _kind);
    }

    public static void checkParametersCtors(ContextEl _conf, String _classNameFound,
                                            ExecTypeFunction _named,
                                            ArgumentListCall _firstArgs,
                                            InstancingStep _kindCall) {
        Argument arg_ = _conf.getLastPage().getGlobalArgument();
        checkParameters(_conf, _classNameFound, _named, arg_,null, _firstArgs,CallPrepareState.CTOR, _kindCall,null, MethodAccessKind.INSTANCE);
    }

    public static FormattedParameters checkParameters(ContextEl _conf, String _classNameFound, ExecTypeFunction _methodId,
                                                      Argument _previous, Cache _cache, ArgumentListCall _firstArgs,
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

    public static Argument prepareCallDynReflect(Argument _previous, CustList<Argument> _values, ContextEl _conf) {
        CustList<Argument> values_ = new CustList<Argument>();
        for (Argument v: _values) {
            values_.add(Argument.getNullableValue(v));
        }

        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        String typeFct_ = ls_.getClassName(_conf);
        StringList parts_ = StringExpUtil.getAllTypes(typeFct_);
        CustList<String> paramsFct_ = parts_.leftMinusOne(parts_.size() - 2);
        int valuesSize_ = values_.size();
        if (valuesSize_ != paramsFct_.size()) {
            LgNames lgNames_ = _conf.getStandards();
            String null_ = lgNames_.getContent().getCoreNames().getAliasBadArgNumber();
            _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, ExecTemplates.countDiff(valuesSize_, paramsFct_.size()).toString(), null_)));
            return new Argument();
        }
        for (int i = 0; i < valuesSize_; i++) {
            Argument arg_ = values_.get(i);
            String param_ = paramsFct_.get(i);
            if (param_.startsWith("~")) {
                param_ = param_.substring(1);
            }
            if (!ExecTemplates.checkQuick(param_, arg_, _conf)) {
                return new Argument();
            }
        }
        ArgumentListCall argumentListCall_ = new ArgumentListCall();
        int i_ = 0;
        for (String c: paramsFct_) {
            if (c.startsWith("~")) {
                Struct struct_ = values_.get(i_).getStruct();
                LocalVariable local_ = LocalVariable.newLocalVariable(struct_, c.substring(1));
                local_ = ExecTemplates.local(local_);
                VariableWrapper v_ = new VariableWrapper(local_);
                argumentListCall_.getWrappers().add(v_);
            } else {
                argumentListCall_.getArguments().add(values_.get(i_));
            }
            i_++;
        }
        return prepareCallDyn(_previous,argumentListCall_,_conf);
    }
    public static Argument prepareCallDynNormal(Argument _previous, CustList<ArgumentsPair> _values, ContextEl _conf) {
        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        ArgumentListCall call_ = new ArgumentListCall();
        if (ls_ instanceof LambdaStruct) {
            String typeFct_ = ls_.getClassName(_conf);
            StringList parts_ = StringExpUtil.getAllTypes(typeFct_);
            CustList<String> paramsFct_ = parts_.leftMinusOne(parts_.size() - 2);
            ParametersTypes pars_ = new ParametersTypes();
            StringList typesRef_ = new StringList();
            StringList types_ = new StringList();
            int parNb_ = paramsFct_.size();
            for (int i = 0; i < parNb_; i++) {
                String param_ = paramsFct_.get(i);
                if (param_.startsWith("~")) {
                    typesRef_.add(param_.substring(1));
                } else {
                    types_.add(param_);
                }
            }
            pars_.setTypes(types_);
            pars_.setTypesRef(typesRef_);
            CustList<Argument> values_ = new CustList<Argument>();
            CustList<AbstractWrapper> wrappers_ = new CustList<AbstractWrapper>();
            for (ArgumentsPair a: _values) {
                Argument arg_ = a.getArgument();
                if (arg_ != null) {
                    values_.add(arg_);
                } else {
                    wrappers_.add(a.getWrapper());
                }
            }
            if (_values.size() != parNb_) {
                LgNames stds_ = _conf.getStandards();
                String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                StringBuilder mess_ = ExecTemplates.countDiff(_values.size(), parNb_);
                _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf,mess_.toString(),cast_)));
                return new Argument();
            }
            for (Sizes s: new CustList<Sizes>(
                    new Sizes(values_.size(), pars_.getTypes().size()),
                    new Sizes(wrappers_.size(), pars_.getTypesRef().size())
            )) {
                if (s.getArg() != s.getParam()) {
                    LgNames stds_ = _conf.getStandards();
                    String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    StringBuilder mess_ = ExecTemplates.countDiff(s.getArg(), s.getParam());
                    _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf,mess_.toString(),cast_)));
                    return new Argument();
                }

            }
            int i_ = IndexConstants.FIRST_INDEX;
            for (ArgumentsPair a: _values) {
                String param_ = paramsFct_.get(i_);
                if (param_.startsWith("~")) {
                    if (a.getArgument() != null) {
                        LgNames stds_ = _conf.getStandards();
                        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
                        _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(param_, a.getArgument().getStruct().getClassName(_conf)),cast_)));
                        return new Argument();
                    }
                } else {
                    if (a.getArgument() == null) {
                        LgNames stds_ = _conf.getStandards();
                        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
                        _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(param_, ExecTemplates.getValue(a.getWrapper(),_conf).getClassName(_conf)),cast_)));
                        return new Argument();
                    }
                }
                i_++;
            }
            i_ = IndexConstants.FIRST_INDEX;
            for (Argument a: values_) {
                String param_ = pars_.getTypes().get(i_);
                if (!ExecTemplates.checkQuick(param_, a, _conf)) {
                    return new Argument();
                }
                i_++;
            }
            i_ = IndexConstants.FIRST_INDEX;
            for (AbstractWrapper w: wrappers_) {
                String param_ = pars_.getTypesRef().get(i_);
                Struct value_ = ExecTemplates.getValue(w, _conf);
                if (!ExecTemplates.checkQuick(param_, new Argument(value_), _conf)) {
                    return new Argument();
                }
                i_++;
            }
            call_.getArguments().addAllElts(values_);
            call_.getWrappers().addAllElts(wrappers_);
        }
        return prepareCallDyn(_previous,call_,_conf);
    }
    private static String getBadCastMessage(String _classNameFound, String _className) {
        return StringUtil.concat(_className, RETURN_LINE, _classNameFound, RETURN_LINE);
    }
    public static Argument prepareCallDyn(Argument _previous, ArgumentListCall _values, ContextEl _conf) {
        CustList<Argument> values_ = new CustList<Argument>();
        for (Argument v: _values.getArguments()) {
            values_.add(Argument.getNullableValue(v));
        }
        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        LgNames lgNames_ = _conf.getStandards();
        if (ls_ instanceof LambdaRecordConstructorStruct) {
            LambdaRecordConstructorStruct l_ = (LambdaRecordConstructorStruct) ls_;
            String clName_ = StringUtil.nullToEmpty(l_.getFormClassName());
            _conf.setCallingState(new CustomReflectRecordConstructor(l_.getRoot(),l_.getId(),clName_,values_,true));
            return new Argument();
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
            ConstructorMetaInfo meta_ = NumParsers.getCtor(metaInfo_);
            ArgumentListCall call_ = new ArgumentListCall();
            call_.getWrappers().addAllElts(_values.getWrappers());
            if (!l_.isShiftInstance()) {
                ExecRootBlock type_ = meta_.getPair().getType();
                if (type_ != null && !type_.isStaticType()) {
                    instance_ = ExecTemplates.getFirstArgument(values_);
                    values_ = values_.mid(1);
                }
                call_.getArguments().addAllElts(values_);
                _conf.setCallingState(new CustomReflectLambdaConstructor(meta_, instance_.getStruct(),call_, true));
                return new Argument();
            }
            call_.getArguments().addAllElts(values_);
            _conf.setCallingState(new CustomReflectLambdaConstructor(meta_, instance_.getStruct(),call_, true));
            return new Argument();
        }
        if (ls_ instanceof LambdaFieldStruct) {
            LambdaFieldStruct l_ =  (LambdaFieldStruct) ls_;
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
            Argument instance_ = l_.getInstanceCall();
            if (l_.isSafeInstance()&&instance_.isNull()) {
                String last_ = StringExpUtil.getAllTypes(l_.getClassName(_conf)).last();
                return new Argument(ExecClassArgumentMatching.defaultValue(last_,_conf));
            }
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
            ReflectingType type_;
            boolean aff_ = l_.isAffect();
            if (aff_) {
                type_ = ReflectingType.SET_FIELD;
                FieldMetaInfo method_ = NumParsers.getField(metaInfo_);
                _conf.setCallingState(new CustomReflectSetField(type_, method_, realInstance_,ExecTemplates.getLastArgument(values_), true));
                return new Argument();
            } else {
                type_ = ReflectingType.GET_FIELD;
                FieldMetaInfo method_ = NumParsers.getField(metaInfo_);
                _conf.setCallingState(new CustomReflectGetField(type_, method_, realInstance_, true));
                return new Argument();
            }
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
            MethodMetaInfo method_ = NumParsers.getMethod(metaInfo_);
            CustList<Argument> formal_;
            Argument right_;
            if (StringUtil.quickEq(l_.getMethodName(),"[]=")) {
                formal_ = values_.left(values_.size()-1);
                right_ = ExecTemplates.getLastArgument(values_);
            } else {
                formal_ = values_;
                right_ = null;
            }
            ArgumentListCall call_ = new ArgumentListCall();
            call_.getWrappers().addAllElts(_values.getWrappers());
            if (!l_.isShiftInstance()) {
                Argument instance_;
                if (!static_) {
                    instance_ = new Argument(ExecTemplates.getParent(nbAncestors_, id_, instanceStruct_, _conf));
                    if (_conf.callsOrException()) {
                        return new Argument();
                    }
                } else {
                    instance_ = new Argument(instanceStruct_);
                }
                call_.getArguments().addAllElts(formal_);
                return redirect(_conf, l_,instance_, call_, right_, method_);
            }
            if (FunctionIdUtil.isOperatorName(l_.getMethodName())) {
                formal_.add(0,new Argument(instanceStruct_));
                call_.getArguments().addAllElts(formal_);
                _conf.setCallingState(new CustomReflectLambdaMethod(ReflectingType.DIRECT, method_, new Argument(),call_,right_, true));
                return new Argument();
            }
            int len_ = Math.max(0, formal_.size() - 1);
            CustList<Argument> arr_ = formal_.leftMinusOne(len_);
            Struct value_ = ExecTemplates.getFirstArgument(formal_).getStruct();
            Argument firstValue_ = new Argument(ExecTemplates.getParent(nbAncestors_, id_, value_, _conf));
            if (_conf.callsOrException()) {
                return new Argument();
            }
            call_.getArguments().addAllElts(arr_);
            return redirect(_conf, l_, firstValue_, call_, right_, method_);
        }
        String null_;
        null_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_)));
        return Argument.createVoid();
    }

    private static Argument redirect(ContextEl _conf, LambdaMethodStruct _l, Argument _instance, ArgumentListCall _call, Argument _right, MethodMetaInfo _method) {
        CustList<Argument> arguments_ = _call.getArguments();
        String name_ = _l.getMethodName();
        if (StringUtil.nullToEmpty(_l.getFormClassName()).startsWith(StringExpUtil.ARR_CLASS) && name_.startsWith("[]")) {
            Struct arr_ = _instance.getStruct();
            int lastIndex_ = arguments_.size() - 1;
            if (lastIndex_ < 0) {
                return new Argument(new IntStruct(ExecArrayFieldOperation.getLength(arr_,_conf)));
            }
            if (StringUtil.quickEq(name_,"[]=")) {
                for (int i = 0; i < lastIndex_; i++) {
                    Struct ind_ = arguments_.get(i).getStruct();
                    arr_ = ExecTemplates.getElement(arr_, ind_, _conf);
                    if (_conf.callsOrException()) {
                        return new Argument();
                    }
                }
                Struct ind_ = arguments_.get(lastIndex_).getStruct();
                ExecTemplates.setElement(arr_,ind_, _right.getStruct(),_conf);
                if (_conf.callsOrException()) {
                    return new Argument();
                }
                return _right;
            }
            for (int i = 0; i < lastIndex_; i++) {
                Struct ind_ = arguments_.get(i).getStruct();
                arr_ = ExecTemplates.getElement(arr_,ind_,_conf);
                if (_conf.callsOrException()) {
                    return new Argument();
                }
            }
            Struct ind_ = arguments_.get(lastIndex_).getStruct();
            return new Argument(ExecTemplates.getElement(arr_,ind_,_conf));
        }
        if (_method.isDirectCast()) {
            _conf.setCallingState(new CustomReflectLambdaMethod(ReflectingType.CAST_DIRECT, _method, _instance,_call,_right, true));
            return new Argument();
        }
        if (_method.getStdCallee() != null) {
            _conf.setCallingState(new CustomReflectLambdaMethod(ReflectingType.STD_FCT, _method, _instance,_call,_right, true));
            return new Argument();
        }
        if (_method.getPair().getFct() instanceof ExecAnonymousFunctionBlock) {
            if (_method.isStaticCall()) {
                _conf.setCallingState(new CustomReflectLambdaMethod(ReflectingType.STATIC_CALL, _method, _instance,_call,_right, true));
                return new Argument();
            }
            _conf.setCallingState(new CustomReflectLambdaMethod(ReflectingType.DIRECT, _method, _instance,_call,_right, true));
            return new Argument();
        }
        ExecRootBlock e_ = _method.getPair().getType();
        if (e_ instanceof ExecAnnotationBlock) {
            _conf.setCallingState(new CustomReflectLambdaMethod(ReflectingType.ANNOT_FCT, _method, _instance,_call,_right, true));
            return new Argument();
        }
        if (_method.getPair().getFct() != null) {
            if (_method.isExpCast()) {
                _conf.setCallingState(new CustomReflectLambdaMethod(ReflectingType.CAST, _method, _instance,_call,_right, true));
                return new Argument();
            }
            if (_method.isStaticCall()) {
                _conf.setCallingState(new CustomReflectLambdaMethod(ReflectingType.STATIC_CALL, _method, _instance,_call,_right, true));
                return new Argument();
            }
            if (!_l.isPolymorph()) {
                _conf.setCallingState(new CustomReflectLambdaMethod(ReflectingType.DIRECT, _method, _instance,_call,_right, true));
                return new Argument();
            }
            _conf.setCallingState(new CustomReflectLambdaMethod(ReflectingType.METHOD, _method, _instance,_call,_right, true));
            return new Argument();
        }
        if (e_ != null) {
            _conf.setCallingState(new CustomReflectLambdaMethod(ReflectingType.ENUM_METHODS, _method, _instance,_call,_right, true));
            return new Argument();
        }
        _conf.setCallingState(new CustomReflectLambdaMethod(ReflectingType.CLONE_FCT, _method, _instance,_call,_right, true));
        return new Argument();
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
