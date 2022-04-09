package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecSwitchInstanceMethod;
import code.expressionlanguage.exec.calls.PageElContent;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.calls.util.CustomFoundSwitch;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.IdentifiableUtil;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class ExecTemplates {

    public static final String ARR_BEG_STRING = "[";
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final String SUB_TYPE = "?";
    public static final String SUP_TYPE = "!";

    private ExecTemplates(){
    }

    public static String checkParams(ContextEl _conf, String _classNameFound, Identifiable _methodId,
                                     Argument _previous, CustList<Argument> _firstArgs, StackCall _stackCall) {
        LgNames stds_ = _conf.getStandards();
        String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
        String classFormat_ = _classNameFound;
        if (!_methodId.isStaticMethod()) {
            String className_ = Argument.getNullableValue(_previous).getStruct().getClassName(_conf);
            classFormat_ = ExecInherits.getQuickFullTypeByBases(className_, classFormat_, _conf);
            if (classFormat_.isEmpty()) {
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, ExecFieldTemplates.getBadCastMessage(_classNameFound, className_), cast_, _stackCall)));
                return "";
            }
        }
        if (okArgsSet(_methodId, _firstArgs, _conf, _stackCall) != null) {
            return "";
        }
        return classFormat_;
    }

    public static Struct okArgsSet(Identifiable _id, CustList<Argument> _firstArgs, ContextEl _conf, StackCall _stackCall) {
        Struct ex_ = okArgsEx(_id, _firstArgs, _conf, _stackCall);
        if (ex_ != null) {
            _stackCall.setCallingState(new CustomFoundExc(ex_));
        }
        return ex_;
    }

    public static Parameters okArgsSet(ExecNamedFunctionBlock _id, ExecFormattedRootBlock _classNameFound, Cache _cache, ArgumentListCall _firstArgs, ContextEl _conf, StackCall _stackCall) {
        Parameters p_ = new Parameters();
        possibleCheck(_classNameFound, _cache, _conf, _stackCall, p_);
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        ParametersTypes params_ = fetchParamTypes(_id, _classNameFound);
        checkNb(_conf, _stackCall, p_, argumentWrappers_, params_);
        checkArgs(_conf, _stackCall, p_, argumentWrappers_, params_);
        procRight(_id, _classNameFound, _conf, _firstArgs.getRight(), _stackCall, p_);
        if (p_.getError() != null) {
            _stackCall.setCallingState(new CustomFoundExc(p_.getError()));
        }
        return p_;
    }

    public static void okArgsSetSwCall(ExecAbstractSwitchMethod _id, ContextEl _conf, StackCall _stackCall, Argument _value, ExecFormattedRootBlock _globalClass, PageElContent _contentEx) {
        Argument instance_ = _contentEx.getGlobalArgument();
        if (_id instanceof ExecSwitchInstanceMethod) {
            Parameters out_ = okArgsExSw(_id, _globalClass, new HiddenCache(_contentEx), _conf, _stackCall, _value);
            if (out_.getError() == null) {
                _stackCall.setCallingState(new CustomFoundSwitch(instance_, _globalClass, _id, out_.getCache(), _value));
            } else {
                _stackCall.setCallingState(new CustomFoundExc(out_.getError()));
            }
            return;
        }
        _stackCall.setCallingState(new CustomFoundSwitch(instance_, _globalClass, _id, new HiddenCache(_contentEx),_value));
    }

    public static Parameters okArgsSetSw(ExecAbstractSwitchMethod _id, ExecFormattedRootBlock _classNameFound, Cache _cache, ContextEl _conf, StackCall _stackCall, CustList<Argument> _arguments) {
        if (_arguments.isEmpty()) {
            Parameters p_ = new Parameters();
            LgNames stds_ = _conf.getStandards();
            String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
            StringBuilder mess_ = countDiff(0, 1);
            ErrorStruct error_ = new ErrorStruct(_conf, mess_.toString(), cast_, _stackCall);
            _stackCall.setCallingState(new CustomFoundExc(error_));
            p_.setError(error_);
            return p_;
        }
        Parameters ex_ = okArgsExSw(_id, _classNameFound,_cache, _conf, _stackCall, _arguments.first());
        if (ex_.getError() != null) {
            _stackCall.setCallingState(new CustomFoundExc(ex_.getError()));
        }
        return ex_;
    }

    private static Struct okArgsEx(Identifiable _id, CustList<Argument> _firstArgs, ContextEl _conf, StackCall _stackCall) {
        StringList params_ = new StringList();
        IdentifiableUtil.appendLeftPart(0,params_,_id);
        if (_firstArgs.size() != params_.size()) {
            LgNames stds_ = _conf.getStandards();
            String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
            StringBuilder mess_ = countDiff(_firstArgs.size(), params_.size());
            return new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall);
        }
        int i_ = IndexConstants.FIRST_INDEX;
        for (Argument a: _firstArgs) {
            String param_ = params_.get(i_);
            Struct ex_ = ExecInheritsAdv.checkObjectEx(param_, a.getStruct().getClassName(_conf), _conf, _stackCall);
            if (ex_ != null) {
                return ex_;
            }
            i_++;
        }
        return null;
    }

    private static void checkNb(ContextEl _conf, StackCall _stackCall, Parameters _p, CustList<ArgumentWrapper> _argumentWrappers, ParametersTypes _params) {
        if (_p.getError() != null) {
            return;
        }
        for (Sizes s: new CustList<Sizes>(
                new Sizes(_argumentWrappers.size(), _params.getTypesAll().size())
        )) {
            if (s.getArg() != s.getParam()) {
                LgNames stds_ = _conf.getStandards();
                String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                StringBuilder mess_ = countDiff(s.getArg(), s.getParam());
                _p.setError(new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall));
                break;
            }
        }
    }

    private static void checkArgs(ContextEl _conf, StackCall _stackCall, Parameters _p, CustList<ArgumentWrapper> _argumentWrappers, ParametersTypes _params) {
        int i_ = IndexConstants.FIRST_INDEX;
        for (ArgumentWrapper a: _argumentWrappers) {
            checkArg(_conf, _stackCall, _params,i_, _p, a);
            i_++;
        }
    }

    private static void checkArg(ContextEl _conf, StackCall _stackCall, ParametersTypes _params, int _i, Parameters _p, ArgumentWrapper _a) {
        if (_p.getError() != null) {
            return;
        }
        String param_ = _params.getTypesAll().get(_i);
        AbstractWrapper w_ = _a.getWrapper();
        if (w_ == null) {
            Argument a_ = _a.getValue();
            Struct ex_ = ExecInheritsAdv.checkObjectEx(param_, a_.getStruct().getClassName(_conf), _conf, _stackCall);
            if (ex_ != null) {
                _p.setError(ex_);
                return;
            }
            Struct struct_ = a_.getStruct();
            LocalVariable lv_ = LocalVariable.newLocalVariable(struct_,param_);
            _p.getRefParameters().addEntry(_params.getNamesAll().get(_i),new VariableWrapper(lv_));
        } else {
            Struct ex_ = ExecInheritsAdv.checkObjectEx(param_, w_.getClassName(_stackCall, _conf), _conf, _stackCall);
            if (ex_ != null) {
                _p.setError(ex_);
                return;
            }
            _p.getRefParameters().addEntry(_params.getNamesAll().get(_i),getWrap(w_));
        }
    }

    private static void procRight(ExecNamedFunctionBlock _id, ExecFormattedRootBlock _classNameFound, ContextEl _conf, Argument _right, StackCall _stackCall, Parameters _p) {
        if (_p.getError() != null) {
            return;
        }
        if (_id != null&&_right != null) {
            String type_ = _id.getImportedReturnType();
            type_ = ExecInherits.quickFormat(_classNameFound, type_);
            Struct ex_ = ExecInheritsAdv.checkObjectEx(type_, _right.getStruct().getClassName(_conf), _conf, _stackCall);
            if (ex_ != null) {
                _p.setError(ex_);
            } else {
                _p.setRight(_right);
                LocalVariable lv_ = LocalVariable.newLocalVariable(_right.getStruct(),type_);
                _p.getRefParameters().addEntry(_conf.getClasses().getKeyWordValue(),new VariableWrapper(lv_));
            }
        }
    }

    private static Parameters okArgsExSw(ExecAbstractSwitchMethod _id, ExecFormattedRootBlock _classNameFound, Cache _cache, ContextEl _conf, StackCall _stackCall, Argument _value) {
        Parameters p_ = new Parameters();
        possibleCheck(_classNameFound, _cache, _conf, _stackCall, p_);
        if (p_.getError() != null) {
            return p_;
        }
        String c_ = _id.getImportedParamType();
        c_ = ExecInherits.quickFormat(_classNameFound, c_);
        Struct ex_ = ExecInheritsAdv.checkObjectEx(c_, _value.getStruct().getClassName(_conf), _conf, _stackCall);
        if (ex_ != null) {
            p_.setError(ex_);
        }
        return p_;
    }

    private static void possibleCheck(ExecFormattedRootBlock _classNameFound, Cache _cache, ContextEl _conf, StackCall _stackCall, Parameters _p) {
        if (_cache != null) {
            _p.setCache(_cache);
            Struct err_ = _cache.checkCache(_classNameFound, _conf, _stackCall);
            if (err_ != null) {
                _p.setError(err_);
            }
        }
    }

    public static StringBuilder countDiff(int _argsCount, int _paramsCount) {
        StringBuilder mess_ = new StringBuilder();
        mess_.append(_argsCount);
        mess_.append("!=");
        mess_.append(_paramsCount);
        return mess_;
    }

    private static ParametersTypes fetchParamTypes(ExecNamedFunctionBlock _id, ExecFormattedRootBlock _classNameFound) {
        ParametersTypes parametersTypes_ = new ParametersTypes();
        StringList paramsAll_ = new StringList();
        StringList namesAll_ = new StringList();
        if (_id == null) {
            parametersTypes_.setNamesAll(namesAll_);
            parametersTypes_.setTypesAll(paramsAll_);
            return parametersTypes_;
        }
        int i_ = 0;
        for (String c: _id.getImportedParametersTypes()) {
            paramsAll_.add(varType(c,_classNameFound,_id,i_));
            namesAll_.add(_id.getParametersName(i_));
            i_++;
        }
        parametersTypes_.setNamesAll(namesAll_);
        parametersTypes_.setTypesAll(paramsAll_);
        return parametersTypes_;
    }

    public static void wrapAndCallDirect(ArgumentListCall _in,ExecTypeFunction _pair, CustList<Argument> _firstArgs, Argument _right,ExecFormattedRootBlock _classFormat) {
        ExecNamedFunctionBlock fct_ = _pair.getFct();
        if (fct_ == null) {
            return;
        }
        int i_ = 0;
        for (String c: fct_.getImportedParametersTypes()) {
            if (fct_.getParametersRef(i_) == BoolVal.TRUE) {
                Struct struct_ = _firstArgs.get(i_).getStruct();
                LocalVariable local_ = LocalVariable.newLocalVariable(struct_, varType(c,_classFormat,fct_,i_));
                ReflectVariableWrapper v_ = new ReflectVariableWrapper(local_);
//                out_.getWrappers().add(v_);
                _in.getArgumentWrappers().add(new ArgumentWrapper(_firstArgs.get(i_),v_));
            } else {
//                out_.getArguments().add(_firstArgs.get(i_));
                _in.getArgumentWrappers().add(new ArgumentWrapper(_firstArgs.get(i_),null));
            }
            i_++;
        }
        _in.setRight(_right);
    }

    private static String varType(String _c,ExecFormattedRootBlock _classFormat, ExecNamedFunctionBlock _fct, int _i) {
        String c_ = ExecInherits.quickFormat(_classFormat, _c);
        if (_i + 1 == _fct.getImportedParametersTypes().size() && _fct.isVarargs()) {
            return StringExpUtil.getPrettyArrayType(c_);
        }
        return c_;
    }
    public static Parameters wrapAndCall(ExecTypeFunction _pair, ExecFormattedRootBlock _formatted, Argument _previous, ContextEl _conf, StackCall _stackCall, ArgumentListCall _argList) {
        ExecNamedFunctionBlock fct_ = _pair.getFct();
        Parameters p_ = okArgsSet(fct_,_formatted,null,_argList,_conf,_stackCall);
        if (p_.getError() == null) {
            _stackCall.setCallingState(new CustomFoundMethod(_previous,_formatted, _pair, p_));
        }
        return p_;
    }

    public static AbstractWrapper getWrap(AbstractWrapper _w) {
        if (_w == null) {
            return new VariableWrapper(LocalVariable.newLocalVariable(NullStruct.NULL_VALUE,""));
        }
        return _w;
    }
}
