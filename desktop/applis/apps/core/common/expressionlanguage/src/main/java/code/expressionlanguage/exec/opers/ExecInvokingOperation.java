package code.expressionlanguage.exec.opers;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.*;
import code.expressionlanguage.exec.util.*;
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
    private final boolean intermediate;

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
        String lastType_ = ExecInherits.quickFormat(_rootBlock,_className, _lastType);
        return fectchArgs(_nodes, lastType_, _naturalVararg);
    }

    protected ArgumentListCall fetchFormattedArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _pr, String _className, ExecRootBlock _rootBlock, String _lastType, int _naturalVararg) {
        String cl_ = _pr.getClassName(_conf);
        String base_ = StringExpUtil.getIdFromAllTypes(_className);
        String clGen_ = ExecInherits.getSuperGeneric(cl_, base_, _conf);
        String lastType_ = ExecInherits.quickFormat(_rootBlock,clGen_, _lastType);
        return fectchArgs(_nodes,lastType_, _naturalVararg);
    }

    protected ArgumentListCall fectchArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _lastType, int _naturalVararg) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ArgumentList argumentList_ = listNamedArguments(_nodes, chidren_,_naturalVararg);
        CustList<ArgumentWrapper> first_ = argumentList_.getArguments().getArgumentWrappers();
        listArguments(argumentList_.getNaturalVararg(), _lastType, first_);
        return argumentList_.getArguments();
    }
    private static ArgumentList listNamedArguments(IdMap<ExecOperationNode, ArgumentsPair> _all, CustList<ExecOperationNode> _children, int _naturalVararg) {
        ArgumentList out_ = new ArgumentList();
        out_.setNaturalVararg(_naturalVararg);
        CustList<ArgumentWrapper> wrappers_ = out_.getArguments().getArgumentWrappers();
        CustList<ExecNamedArgumentOperation> named_ = new CustList<ExecNamedArgumentOperation>();
        for (ExecOperationNode c: _children) {
            if (ExecConstLeafOperation.isFilter(c)) {
                continue;
            }
            ArgumentsPair calc_ = ExecHelper.getArgumentPair(_all,c);
            if (c instanceof ExecNamedArgumentOperation) {
                named_.add((ExecNamedArgumentOperation)c);
            } else {
                addToWrappers(c,calc_,wrappers_);
            }
        }
        while (!named_.isEmpty()) {
            ExecOperationIndexer indexer_ = new ExecOperationIndexer(named_);
            int i_ = indexer_.getIndex();
            ExecNamedArgumentOperation n_ = named_.get(i_);
            ArgumentsPair calc_ = ExecHelper.getArgumentPair(_all,n_);
            addToWrappers(n_.getFirstChild(),calc_,wrappers_);
            named_.remove(i_);
        }
        return out_;
    }
    private static void addToWrappers(ExecOperationNode _node, ArgumentsPair _pair,CustList<ArgumentWrapper> _wrappers) {
        if (!(_node instanceof ExecWrappOperation)) {
            _wrappers.add(new ArgumentWrapper(_pair.getArgument(),null));
        } else {
            _wrappers.add(new ArgumentWrapper(null,_pair.getWrapper()));
        }
    }
    public static void listArguments(int _natVararg, String _lastType, CustList<ArgumentWrapper> _nodes) {
        if (_natVararg <= -1) {
            return;
        }
        CustList<Struct> optArgs_ = new CustList<Struct>();
        CustList<ArgumentWrapper> reord_ = new CustList<ArgumentWrapper>();
        int lenCh_ = _nodes.size();
        for (int i = IndexConstants.FIRST_INDEX; i < lenCh_; i++) {
            ArgumentWrapper aw_ = _nodes.get(i);
            Argument a_ = aw_.getValue();
            if (a_ == null) {
                reord_.add(aw_);
                continue;
            }
            if (i >= _natVararg) {
                optArgs_.add(a_.getStruct());
            } else {
                reord_.add(aw_);
            }
        }
        String clArr_ = StringExpUtil.getPrettyArrayType(_lastType);
        ArrayStruct str_ = NumParsers.setElements(optArgs_,clArr_);
        reord_.add(new ArgumentWrapper(new Argument(str_),null));
        _nodes.clear();
        _nodes.addAllElts(reord_);
    }
    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    public static Argument instancePrepareStd(ContextEl _conf, ConstructorId _constId,
                                              ArgumentListCall _arguments, StackCall _stackCall) {
        CustList<Argument> args_ = _arguments.getArguments();
        if (ExecTemplates.okArgsSet(_constId, args_, _conf, _stackCall) != null) {
            return new Argument();
        }
        ResultErrorStd res_ = ApplyCoreMethodUtil.newInstance(_conf, _constId, _stackCall, Argument.toArgArray(args_));
        return new Argument(res_.getResult());
    }

    public static Argument instancePrepareCust(ContextEl _conf, String _className, ExecTypeFunction _pair,
                                               Argument _previous, ArgumentListCall _arguments, String _fieldName,
                                               int _blockIndex, StackCall _stackCall) {
        LgNames stds_ = _conf.getStandards();
        ExecRootBlock type_ = _pair.getType();
        checkNeeded(_conf, _className, _previous, stds_, type_, _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return new Argument();
        }
        ExecNamedFunctionBlock fct_ = _pair.getFct();
        Parameters parameters_ = ExecTemplates.okArgsSet(type_, fct_, _className, null, _arguments, _conf, null, true, _stackCall);
        if (parameters_.getError() != null) {
            return new Argument();
        }
        Argument needed_;
        if (type_.withoutInstance()) {
            needed_ = new Argument();
        } else {
            needed_ = new Argument(Argument.getNullableValue(_previous).getStruct());
        }
        _stackCall.setCallingState(new CustomFoundConstructor(_className, _pair, _fieldName, _blockIndex, needed_, parameters_, InstancingStep.NEWING));
        return Argument.createVoid();
    }

    private static void checkNeeded(ContextEl _conf, String _className, Argument _previous, LgNames _stds, ExecRootBlock _g, StackCall _stackCall) {
        if (_g.withoutInstance()) {
            return;
        }
        //From analyze
        StringList parts_ = StringExpUtil.getAllPartInnerTypes(_className);
        String param_ = StringUtil.join(parts_.left(parts_.size()-2), "");
        if (_previous.isNull()) {
            String npe_;
            npe_ = _stds.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return;
        }
        String arg_ = _previous.getStruct().getClassName(_conf);
        if (!ExecInherits.isCorrectExecute(arg_, param_, _conf)) {
            String cast_;
            cast_ = _stds.getContent().getCoreNames().getAliasCastType();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, StringUtil.concat(arg_, RETURN_LINE, param_, RETURN_LINE), cast_, _stackCall)));
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
    public static Argument callStd(AbstractExiting _exit, ContextEl _cont, String _classNameFound, MethodId _methodId, Argument _previous, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<Argument> args_ = _firstArgs.getArguments();
        ExecTemplates.checkParams(_cont, _classNameFound, _methodId, _previous, args_, _stackCall);
        if (_cont.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        String idClassNameFound_ = StringExpUtil.getIdFromAllTypes(_classNameFound);

        ClassMethodId dyn_ = new ClassMethodId(idClassNameFound_, _methodId);
        ResultErrorStd res_ = LgNames.invokeMethod(_cont, dyn_, _previous.getStruct(), _exit, _stackCall, Argument.toArgArray(args_));
        return new Argument(res_.getResult());
    }

    public static Argument tryGetEnumValues(AbstractExiting _exit, ContextEl _cont, ExecRootBlock _r, ClassCategory _category, StackCall _stackCall) {
        if (isNotEnumType(_r, _category)) {
            return new Argument();
        }
        String className_ = _r.getWildCardElement();
        return getEnumValues(_exit,className_,_r, _cont, _stackCall);
    }

    public static Argument tryGetEnumValue(AbstractExiting _exit, ContextEl _cont, ExecRootBlock _r, ClassCategory _category, Argument _clArg, StackCall _stackCall) {
        if (isNotEnumType(_r, _category)) {
            return new Argument();
        }
        String enumName_ = _r.getFullName();
        return getEnumValue(_exit,enumName_,_r, _clArg, _cont, _stackCall);
    }


    public static Argument callPrepare(ContextEl _cont, String _classNameFound, ExecTypeFunction _rootBlock, Argument _previous, Cache _cache, ArgumentListCall _firstArgs, Argument _right, MethodAccessKind _kind, StackCall _stackCall) {
        FormattedParameters classFound_ = checkParameters(_cont, _classNameFound, _rootBlock, _previous, _cache,_firstArgs, CallPrepareState.METHOD,null, _right, _kind, _stackCall);
        if (_cont.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        Struct prev_ =_previous.getStruct();
        if (prev_ instanceof AbstractFunctionalInstance && ((AbstractFunctionalInstance) prev_).getNamed() == _rootBlock.getFct()) {
            Argument fctInst_ = new Argument(((AbstractFunctionalInstance) prev_).getFunctional());
            return prepareCallDyn(fctInst_, _firstArgs, _cont, _stackCall);
        }
        _stackCall.setCallingState(new CustomFoundMethod(_previous, classFound_.getFormattedClass(), _rootBlock, classFound_.getParameters()));
        return Argument.createVoid();
    }

    public static Argument processEnums(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall, ExecRootBlock _type) {
        //static enum methods
        LgNames stds_ = _cont.getStandards();
        CustList<Argument> args_ = _firstArgs.getArguments();
        if (args_.size() != 1) {
            return tryGetEnumValues(_exit, _cont, _type,  ClassCategory.ENUM, _stackCall);
        }
        Argument arg_ = args_.first();
        Struct ex_ = ExecTemplates.checkObjectEx(stds_.getContent().getCharSeq().getAliasString(), Argument.getNullableValue(arg_).getStruct().getClassName(_cont), _cont, _stackCall);
        if (ex_ != null) {
            _stackCall.setCallingState(new CustomFoundExc(ex_));
            return Argument.createVoid();
        }
        return tryGetEnumValue(_exit, _cont, _type, ClassCategory.ENUM, arg_, _stackCall);
    }

    private static boolean isNotEnumType(ExecRootBlock _r, ClassCategory _category) {
        return _r == null || _category != ClassCategory.ENUM;
    }

    public static void checkParametersOperators(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named,
                                                IdMap<ExecOperationNode, ArgumentsPair> _nodes, ExecMethodOperation _meth, String _className, MethodAccessKind _kind, StackCall _stackCall) {
        CustList<Argument> arguments_ = getArguments(_nodes, _meth);
        ArgumentListCall l_ = new ArgumentListCall();
        l_.addAllArgs(arguments_);
        checkParametersOperators(_exit, _conf, _named, l_, _className, _kind, _stackCall);
    }
    public static void checkParametersOperators(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named,
                                                ArgumentListCall _firstArgs, String _className, MethodAccessKind _kind, StackCall _stackCall) {
        String classNameFound_ = _className;
        classNameFound_ = ClassMethodId.formatType(classNameFound_, _kind, _stackCall);
        checkParametersOperatorsFormatted(_exit, _conf, _named, _firstArgs, classNameFound_, _kind, _stackCall);
    }

    public static void checkParametersOperatorsFormatted(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named, ArgumentListCall _firstArgs, String _classNameFound, MethodAccessKind _kind, StackCall _stackCall) {
        if (_exit.hasToExit(_stackCall, _classNameFound)) {
            return;
        }
        checkParameters(_conf, _classNameFound, _named, Argument.createVoid(),null, _firstArgs, CallPrepareState.OPERATOR,null, null, _kind, _stackCall);
    }

    public static void checkParametersCtors(ContextEl _conf, String _classNameFound,
                                            ExecTypeFunction _named,
                                            ArgumentListCall _firstArgs,
                                            InstancingStep _kindCall, StackCall _stackCall) {
        Argument arg_ = _stackCall.getLastPage().getGlobalArgument();
        checkParameters(_conf, _classNameFound, _named, arg_,null, _firstArgs,CallPrepareState.CTOR, _kindCall,null, MethodAccessKind.INSTANCE, _stackCall);
    }

    public static FormattedParameters checkParameters(ContextEl _conf, String _classNameFound, ExecTypeFunction _methodId,
                                                      Argument _previous, Cache _cache, ArgumentListCall _firstArgs,
                                                      CallPrepareState _state,
                                                      InstancingStep _kindCall, Argument _right, MethodAccessKind _kind, StackCall _stackCall) {
        ExecRootBlock type_ = _methodId.getType();
        ExecNamedFunctionBlock fct_ = _methodId.getFct();
        FormattedParameters classFormat_ = ExecTemplates.checkParams(_conf,_classNameFound,type_,fct_,_previous,_cache,_firstArgs,_right, _kind, _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return classFormat_;
        }
        if (_state == CallPrepareState.METHOD) {
            return classFormat_;
        }
        Parameters parameters_ = classFormat_.getParameters();
        if (_state == CallPrepareState.CTOR) {
            _stackCall.setCallingState(new CustomFoundConstructor(_classNameFound, _methodId, EMPTY_STRING, -1, _previous, parameters_, _kindCall));
            return classFormat_;
        }
        _stackCall.setCallingState(new CustomFoundMethod(_classNameFound, _methodId, parameters_));
        return classFormat_;
    }

    public static Argument getInstanceCall(Argument _previous, ContextEl _conf, StackCall _stackCall) {
        Struct ls_ = _previous.getStruct();
        if (ls_ instanceof LambdaStruct) {
            return ((LambdaStruct) ls_).getInstanceCall();
        }
        LgNames lgNames_ = _conf.getStandards();
        String null_;
        null_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
        return Argument.createVoid();
    }
    public static Argument getMetaInfo(Argument _previous, ContextEl _conf, StackCall _stackCall) {
        Struct ls_ = _previous.getStruct();
        if (ls_ instanceof LambdaStruct) {
            return new Argument(((LambdaStruct) ls_).getMetaInfo());
        }
        LgNames lgNames_ = _conf.getStandards();
        String null_;
        null_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
        return Argument.createVoid();
    }

    public static Argument prepareCallDynReflect(Argument _previous, CustList<Argument> _values, ContextEl _conf, StackCall _stackCall) {
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
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, ExecTemplates.countDiff(valuesSize_, paramsFct_.size()).toString(), null_, _stackCall)));
            return new Argument();
        }
        for (int i = 0; i < valuesSize_; i++) {
            Argument arg_ = values_.get(i);
            String param_ = paramsFct_.get(i);
            if (param_.startsWith("~")) {
                param_ = param_.substring(1);
            }
            if (!ExecTemplates.checkQuick(param_, arg_.getStruct().getClassName(_conf), _conf, _stackCall)) {
                return new Argument();
            }
        }
        ArgumentListCall argumentListCall_ = new ArgumentListCall();
        int i_ = 0;
        for (String c: paramsFct_) {
            if (c.startsWith("~")) {
                Struct struct_ = values_.get(i_).getStruct();
                LocalVariable local_ = LocalVariable.newLocalVariable(struct_, c.substring(1));
                VariableWrapper v_ = new VariableWrapper(local_);
//                argumentListCall_.getWrappers().add(v_);
                argumentListCall_.getArgumentWrappers().add(new ArgumentWrapper(null,v_));
            } else {
//                argumentListCall_.getArguments().add(values_.get(i_));
                argumentListCall_.getArgumentWrappers().add(new ArgumentWrapper(values_.get(i_),null));
            }
            i_++;
        }
        return prepareCallDyn(_previous,argumentListCall_,_conf, _stackCall);
    }
    public static Argument prepareCallDynNormal(Argument _previous, CustList<ArgumentsPair> _values, ContextEl _conf, StackCall _stackCall) {
        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        ArgumentListCall call_ = new ArgumentListCall();
        if (ls_ instanceof LambdaStruct) {
            String typeFct_ = ls_.getClassName(_conf);
            StringList parts_ = StringExpUtil.getAllTypes(typeFct_);
            CustList<String> paramsFct_ = parts_.leftMinusOne(parts_.size() - 2);
            StringList typesRef_ = new StringList();
            StringList types_ = new StringList();
            StringList typesAll_ = new StringList();
            int parNb_ = paramsFct_.size();
            for (int i = 0; i < parNb_; i++) {
                String param_ = paramsFct_.get(i);
                if (param_.startsWith("~")) {
                    typesRef_.add(param_.substring(1));
                    typesAll_.add(param_.substring(1));
                } else {
                    types_.add(param_);
                    typesAll_.add(param_);
                }
            }
            CustList<ArgumentWrapper> pairs_ = new CustList<ArgumentWrapper>();
            CustList<Argument> values_ = new CustList<Argument>();
            CustList<AbstractWrapper> wrappers_ = new CustList<AbstractWrapper>();
            for (ArgumentsPair a: _values) {
                Argument arg_ = a.getArgument();
                if (arg_ != null) {
                    values_.add(arg_);
                    pairs_.add(new ArgumentWrapper(arg_,null));
                } else {
                    wrappers_.add(a.getWrapper());
                    pairs_.add(new ArgumentWrapper(null,a.getWrapper()));
                }
            }
            if (_values.size() != parNb_) {
                LgNames stds_ = _conf.getStandards();
                String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                StringBuilder mess_ = ExecTemplates.countDiff(_values.size(), parNb_);
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall)));
                return new Argument();
            }
            for (Sizes s: new CustList<Sizes>(
                    new Sizes(values_.size(), types_.size()),
                    new Sizes(wrappers_.size(), typesRef_.size())
            )) {
                if (s.getArg() != s.getParam()) {
                    LgNames stds_ = _conf.getStandards();
                    String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    StringBuilder mess_ = ExecTemplates.countDiff(s.getArg(), s.getParam());
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall)));
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
                        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(param_, a.getArgument().getStruct().getClassName(_conf)),cast_, _stackCall)));
                        return new Argument();
                    }
                } else {
                    if (a.getArgument() == null) {
                        LgNames stds_ = _conf.getStandards();
                        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
                        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(param_, ExecTemplates.getValue(a.getWrapper(),_conf, _stackCall).getClassName(_conf)),cast_, _stackCall)));
                        return new Argument();
                    }
                }
                i_++;
            }
            i_ = IndexConstants.FIRST_INDEX;
            for (ArgumentWrapper a:pairs_) {
                Argument val_ = a.getValue();
                String param_ = typesAll_.get(i_);
                if (val_ != null) {
                    if (!ExecTemplates.checkQuick(param_, val_.getStruct().getClassName(_conf), _conf, _stackCall)) {
                        return new Argument();
                    }
                } else {
                    Struct value_ = ExecTemplates.getValue(a.getWrapper(), _conf, _stackCall);
                    if (!ExecTemplates.checkQuick(param_, Argument.getNull(value_).getClassName(_conf), _conf, _stackCall)) {
                        return new Argument();
                    }
                }
                i_++;
            }
            call_.getArgumentWrappers().addAllElts(pairs_);
        }
        return prepareCallDyn(_previous,call_,_conf, _stackCall);
    }
    private static String getBadCastMessage(String _classNameFound, String _className) {
        return StringUtil.concat(_className, RETURN_LINE, _classNameFound, RETURN_LINE);
    }
    public static Argument prepareCallDyn(Argument _previous, ArgumentListCall _values, ContextEl _conf, StackCall _stackCall) {
        CustList<Argument> values_ = _values.getArguments();
        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        LgNames lgNames_ = _conf.getStandards();
        if (ls_ instanceof LambdaRecordConstructorStruct) {
            LambdaRecordConstructorStruct l_ = (LambdaRecordConstructorStruct) ls_;
            String clName_ = StringUtil.nullToEmpty(l_.getFormClassName());
            _stackCall.setCallingState(new CustomReflectRecordConstructor(l_.getRoot(),l_.getId(),clName_,values_,true));
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
                Struct res_ = ExecTemplates.newCustomArrayOrExc(c_, dims_, _conf, _stackCall);
                if (res_ instanceof ErrorStruct) {
                    _stackCall.setCallingState(new CustomFoundExc(res_));
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
            if (!l_.isShiftInstance()) {
                ExecRootBlock type_ = meta_.getPairType();
                CustList<ArgumentWrapper> argumentWrappers_ = _values.getArgumentWrappers();
                if (type_ != null && !type_.withoutInstance()) {
                    instance_ = ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(argumentWrappers_));
                    argumentWrappers_ = argumentWrappers_.mid(1);
                }
                call_.getArgumentWrappers().addAllElts(argumentWrappers_);
                _stackCall.setCallingState(new CustomReflectLambdaConstructor(meta_, instance_.getStruct(),call_, true));
                return new Argument();
            }
            call_.getArgumentWrappers().addAllElts(_values.getArgumentWrappers());
            _stackCall.setCallingState(new CustomReflectLambdaConstructor(meta_, instance_.getStruct(),call_, true));
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
                    CustList<ArgumentWrapper> argumentWrappers_ = _values.getArgumentWrappers();
                    realInstance_ = ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(argumentWrappers_));
                }
                Struct struct_ = realInstance_.getStruct();
                if (l_.isToStrField()) {
                    _stackCall.setCallingState(new CustomReflectLambdaToStr(realInstance_));
                    return Argument.createVoid();
                }
                if (l_.isRdCodField()) {
                    _stackCall.setCallingState(new CustomReflectLambdaRdCod(realInstance_));
                    return Argument.createVoid();
                }
                if (l_.isInstanceField()) {
                    String ownerType_ = StringUtil.nullToEmpty(l_.getOwnerType());
                    boolean res_ = ExecTemplates.safeObject(ownerType_, struct_.getClassName(_conf),_conf) == ErrorType.NOTHING;
                    return new Argument(BooleanStruct.of(res_));
                }
                return new Argument(struct_.getParent());
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
                    CustList<ArgumentWrapper> argumentWrappers_ = _values.getArgumentWrappers();
                    value_ = ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(argumentWrappers_)).getStruct();
                }
                realInstance_ = new Argument(ExecTemplates.getParent(nbAncestors_, clName_, value_, _conf, _stackCall));
                if (_conf.callsOrException(_stackCall)) {
                    return new Argument();
                }
            } else {
                realInstance_ = new Argument();
            }
            ReflectingType type_;
            boolean aff_ = l_.isAffect();
            FieldMetaInfo method_ = NumParsers.getField(metaInfo_);
            if (aff_) {
                type_ = ReflectingType.SET_FIELD;
                CustList<ArgumentWrapper> argumentWrappers_ = _values.getArgumentWrappers();
                _stackCall.setCallingState(new CustomReflectSetField(type_, method_, realInstance_, ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_)), true));
                return new Argument();
            }
            type_ = ReflectingType.GET_FIELD;
            _stackCall.setCallingState(new CustomReflectGetField(type_, method_, realInstance_, true));
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
            MethodMetaInfo method_ = NumParsers.getMethod(metaInfo_);
            CustList<ArgumentWrapper> argumentWrappers_ = _values.getArgumentWrappers();
            CustList<ArgumentWrapper> formal_;
            Argument right_;
            if (StringUtil.quickEq(l_.getMethodName(),"[]=")) {
                formal_ = argumentWrappers_.left(argumentWrappers_.size()-1);
                right_ = ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_));
            } else {
                formal_ = argumentWrappers_;
                right_ = null;
            }
            ArgumentListCall call_ = new ArgumentListCall();
            if (!l_.isShiftInstance()) {
                Argument instance_;
                if (!static_) {
                    instance_ = new Argument(ExecTemplates.getParent(nbAncestors_, id_, instanceStruct_, _conf, _stackCall));
                    if (_conf.callsOrException(_stackCall)) {
                        return new Argument();
                    }
                } else {
                    instance_ = new Argument(instanceStruct_);
                }
                call_.getArgumentWrappers().addAllElts(formal_);
                return redirect(_conf, l_,instance_, call_, right_, method_, _stackCall);
            }
            if (FunctionIdUtil.isOperatorName(l_.getMethodName())) {
                formal_.add(0,new ArgumentWrapper(new Argument(instanceStruct_),null));
                call_.getArgumentWrappers().addAllElts(formal_);
                return redirect(_conf, l_, Argument.createVoid(), call_, right_, method_, _stackCall);
            }
            int len_ = Math.max(0, formal_.size() - 1);
            CustList<ArgumentWrapper> arr_ = formal_.leftMinusOne(len_);
            Struct value_ = ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(formal_)).getStruct();
            Argument firstValue_ = new Argument(ExecTemplates.getParent(nbAncestors_, id_, value_, _conf, _stackCall));
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
            call_.getArgumentWrappers().addAllElts(arr_);
            return redirect(_conf, l_, firstValue_, call_, right_, method_, _stackCall);
        }
        String null_;
        null_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
        return Argument.createVoid();
    }

    private static Argument redirect(ContextEl _conf, LambdaMethodStruct _l, Argument _instance, ArgumentListCall _call, Argument _right, MethodMetaInfo _method, StackCall _stackCall) {
        String name_ = _l.getMethodName();
        if (StringUtil.nullToEmpty(_l.getFormClassName()).startsWith(StringExpUtil.ARR_CLASS) && (name_.startsWith("[]")||StringUtil.quickEq(name_,"[:]"))) {
            CustList<Argument> arguments_ = _call.getArguments();
            Struct arr_ = _instance.getStruct();
            int lastIndex_ = arguments_.size() - 1;
            if (lastIndex_ < 0) {
                return new Argument(new IntStruct(ExecArrayFieldOperation.getLength(arr_,_conf)));
            }
            if (StringUtil.quickEq(name_,"[:]")) {
                if (arguments_.size() == 2) {
                    Struct lower_ = arguments_.get(0).getStruct();
                    Struct step_ = arguments_.get(1).getStruct();
                    Argument range_ = ApplyCoreMethodUtil.rangeUnlimitStep(_conf, _stackCall, lower_, step_);
                    if (_conf.callsOrException(_stackCall)) {
                        return new Argument();
                    }
                    return new Argument(ExecTemplates.getRange(arr_,range_.getStruct(),_conf,_stackCall));
                }
                Struct lower_ = arguments_.get(lastIndex_).getStruct();
                Struct upper_ = new IntStruct(ExecArrayFieldOperation.getLength(arr_,_conf));
                Argument range_ = ApplyCoreMethodUtil.range(_conf, _stackCall, lower_, upper_);
                if (_conf.callsOrException(_stackCall)) {
                    return new Argument();
                }
                return new Argument(ExecTemplates.getRange(arr_,range_.getStruct(),_conf,_stackCall));
            }
            Struct range_ = arguments_.get(lastIndex_).getStruct();
            if (range_ instanceof RangeStruct) {
                return new Argument(ExecTemplates.getRange(arr_,range_,_conf,_stackCall));
            }
            if (StringUtil.quickEq(name_,"[]=")) {
                for (int i = 0; i < lastIndex_; i++) {
                    Struct ind_ = arguments_.get(i).getStruct();
                    arr_ = ExecTemplates.getElement(arr_, ind_, _conf, _stackCall);
                    if (_conf.callsOrException(_stackCall)) {
                        return new Argument();
                    }
                }
                Struct ind_ = arguments_.get(lastIndex_).getStruct();
                ExecTemplates.setElement(arr_,ind_, Argument.getNullableValue(_right).getStruct(),_conf, _stackCall);
                if (_conf.callsOrException(_stackCall)) {
                    return new Argument();
                }
                return _right;
            }
            for (int i = 0; i < lastIndex_; i++) {
                Struct ind_ = arguments_.get(i).getStruct();
                arr_ = ExecTemplates.getElement(arr_,ind_,_conf, _stackCall);
                if (_conf.callsOrException(_stackCall)) {
                    return new Argument();
                }
            }
            Struct ind_ = arguments_.get(lastIndex_).getStruct();
            return new Argument(ExecTemplates.getElement(arr_,ind_,_conf, _stackCall));
        }
        if (_method.getStdCallee() != null) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.STD_FCT, _method, _instance,_call,_right, true));
            return new Argument();
        }
        if (_method.getPairFct() instanceof ExecAnonymousFunctionBlock) {
            if (_method.isStaticCall()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.STATIC_CALL, _method, _instance,_call,_right, true));
                return new Argument();
            }
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.DIRECT, _method, _instance,_call,_right, true));
            return new Argument();
        }
        ExecRootBlock e_ = _method.getPairType();
        if (e_ instanceof ExecAnnotationBlock) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.ANNOT_FCT, _method, _instance,_call,_right, true));
            return new Argument();
        }
        if (_method.getPairFct() != null) {
            if (_method.isExpCast()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.CAST, _method, _instance,_call,_right, true));
                return new Argument();
            }
            if (_method.isStaticCall()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.STATIC_CALL, _method, _instance,_call,_right, true));
                return new Argument();
            }
            if (!_l.isPolymorph()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.DIRECT, _method, _instance,_call,_right, true));
                return new Argument();
            }
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.METHOD, _method, _instance,_call,_right, true));
            return new Argument();
        }
        if (e_ != null) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.ENUM_METHODS, _method, _instance,_call,_right, true));
            return new Argument();
        }
        if (_method.isDirectCast()) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.CAST_DIRECT, _method, _instance,_call,_right, true));
            return new Argument();
        }
        _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.CLONE_FCT, _method, _instance,_call,_right, true));
        return new Argument();
    }
    private static Argument getEnumValues(AbstractExiting _exit, String _class, ExecRootBlock _root, ContextEl _conf, StackCall _stackCall) {
        String id_ = StringExpUtil.getIdFromAllTypes(_class);
        if (_exit.hasToExit(_stackCall, id_)) {
            return Argument.createVoid();
        }
        Classes classes_ = _conf.getClasses();
        CustList<Struct> enums_ = new CustList<Struct>();
        for (ExecInnerTypeOrElement b: _root.getEnumElements()) {
            String fieldName_ = b.getUniqueFieldName();
            Struct str_ = classes_.getStaticField(new ClassField(id_, fieldName_),b.getImportedClassName(),_conf);
            _stackCall.getInitializingTypeInfos().addSensibleField(id_, str_, _stackCall);
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
    private static Argument getEnumValue(AbstractExiting _exit, String _class, ExecRootBlock _root, Argument _name, ContextEl _conf, StackCall _stackCall) {
        String enumName_ = StringExpUtil.getIdFromAllTypes(_class);
        if (_exit.hasToExit(_stackCall, enumName_)) {
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
                _stackCall.getInitializingTypeInfos().addSensibleField(enumName_, str_, _stackCall);
                return new Argument(str_);
            }
        }
        return new Argument();
    }
}
