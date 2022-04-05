package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.ReflectVariableWrapper;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringList;
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

    public static ArgumentListCall fectchInstFormattedArgs(ExecFormattedRootBlock _formatted, ExecInstancingCommonContent _lastType, ContextEl _conf, StackCall _stack, CustList<ExecOperationInfo> _infos) {
        String lastType_ = ExecInherits.quickFormat(_formatted, _lastType.getLastType());
        return fectchArgs(lastType_, _lastType.getNaturalVararg(), null,_conf,_stack, _infos);
    }

    protected static ArgumentListCall fetchFormattedArgs(ContextEl _conf, StackCall _stack, Struct _pr, ExecRootBlock _rootBlock, ExecInstFctContent _lastType, Argument _right, CustList<ExecOperationInfo> _infos) {
        String cl_ = _pr.getClassName(_conf);
        String lastType_ = ExecFieldTemplates.formatType(_conf, _rootBlock, _lastType.getLastType(), cl_);
        return fectchArgs(lastType_, _lastType.getNaturalVararg(),_right,_conf,_stack, _infos);
    }

    public static ArgumentListCall fectchArgs(String _lastType, int _naturalVararg, Argument _right, ContextEl _conf, StackCall _stack, CustList<ExecOperationInfo> _infos) {
        ArgumentList argumentList_ = listNamedArguments(_naturalVararg, _infos);
        CustList<ArgumentWrapper> first_ = argumentList_.getArguments().getArgumentWrappers();
        listArguments(argumentList_.getNaturalVararg(), _lastType, first_,_conf,_stack);
        argumentList_.getArguments().setRight(_right);
        return argumentList_.getArguments();
    }
    public static ArgumentList listNamedArguments(int _naturalVararg, CustList<ExecOperationInfo> _infos) {
        ArgumentList out_ = listNamedArguments(_infos);
        out_.setNaturalVararg(_naturalVararg);
        return out_;
    }

    public static void listArguments(int _natVararg, String _lastType, CustList<ArgumentWrapper> _nodes, ContextEl _conf, StackCall _stack) {
        if (_natVararg <= -1) {
            return;
        }
        CustList<Argument> optArgs_ = new CustList<Argument>();
        CustList<ArgumentWrapper> reord_ = new CustList<ArgumentWrapper>();
        int lenCh_ = _nodes.size();
        for (int i = IndexConstants.FIRST_INDEX; i < lenCh_; i++) {
            ArgumentWrapper aw_ = _nodes.get(i);
            if (aw_.getWrapper() != null) {
                reord_.add(aw_);
                continue;
            }
            Argument a_ = aw_.getValue();
            if (i >= _natVararg) {
                optArgs_.add(a_);
            } else {
                reord_.add(aw_);
            }
        }
        String clArr_ = StringExpUtil.getPrettyArrayType(_lastType);
        ArrayStruct str_ = new ArrayStruct(optArgs_.size(),clArr_);
        ExecArrayTemplates.setCheckedElements(optArgs_,str_,_conf,_stack);
        reord_.add(new ArgumentWrapper(new Argument(str_),null));
        _nodes.clear();
        _nodes.addAllElts(reord_);
    }
    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    public static ExecOverrideInfo polymorphOrSuper(boolean _super,ContextEl _conf, Struct _previous, ExecFormattedRootBlock _className, ExecTypeFunction _named) {
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
        return new ExecOverrideInfo(new ExecFormattedRootBlock(type_),_named);
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
            if (!ExecInheritsAdv.checkQuick(param_, arg_.getStruct().getClassName(_conf), _conf, _stackCall)) {
                return new Argument();
            }
        }
        ArgumentListCall argumentListCall_ = new ArgumentListCall();
        int i_ = 0;
        for (String c: paramsFct_) {
            if (c.startsWith("~")) {
                Struct struct_ = values_.get(i_).getStruct();
                LocalVariable local_ = LocalVariable.newLocalVariable(struct_, c.substring(1));
                ReflectVariableWrapper v_ = new ReflectVariableWrapper(local_);
                argumentListCall_.getArgumentWrappers().add(new ArgumentWrapper(values_.get(i_),v_));
            } else {
                argumentListCall_.getArgumentWrappers().add(new ArgumentWrapper(values_.get(i_),null));
            }
            i_++;
        }
        return AbstractParamChecker.prepareCallDyn(_previous,argumentListCall_,_conf, _stackCall);
    }
    public static Argument prepareCallDynNormal(Argument _previous, ArgumentListCall _values, ContextEl _conf, StackCall _stackCall) {
        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        ArgumentListCall call_ = new ArgumentListCall();
        if (!(ls_ instanceof LambdaStruct)) {
            return AbstractParamChecker.prepareCallDyn(_previous,call_,_conf, _stackCall);
        }
        return prepareCallDynNormalDefault(_previous, _values.getArgumentWrappers(), _conf, _stackCall);
    }

    private static Argument prepareCallDynNormalDefault(Argument _previous, CustList<ArgumentWrapper> _values, ContextEl _conf, StackCall _stackCall) {
        ArgumentListCall call_ = new ArgumentListCall();
        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        String typeFct_ = ls_.getClassName(_conf);
        StringList parts_ = StringExpUtil.getAllTypes(typeFct_);
        CustList<String> paramsFct_ = parts_.leftMinusOne(parts_.size() - 2);
        StringList typesRef_ = new StringList();
        StringList types_ = new StringList();
        StringList typesAll_ = new StringList();
        feedTypes(paramsFct_, typesRef_, types_, typesAll_);
        int wrappers_ = countWrappers(_values);
        int values_ = countValues(_values);
        int parNb_ = paramsFct_.size();
        if (_values.size() != parNb_) {
            LgNames stds_ = _conf.getStandards();
            String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
            StringBuilder mess_ = ExecTemplates.countDiff(_values.size(), parNb_);
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall)));
            return new Argument();
        }
        for (Sizes s: new CustList<Sizes>(
                new Sizes(values_, types_.size()),
                new Sizes(wrappers_, typesRef_.size())
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
        for (ArgumentWrapper a: _values) {
            String param_ = paramsFct_.get(i_);
            if (param_.startsWith("~")) {
                if (a.getWrapper() == null) {
                    LgNames stds_ = _conf.getStandards();
                    String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(param_, a.getValue().getStruct().getClassName(_conf)),cast_, _stackCall)));
                    return new Argument();
                }
            } else {
                if (a.getWrapper() != null) {
                    LgNames stds_ = _conf.getStandards();
                    String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(param_, a.getWrapper().getClassName(_stackCall, _conf)),cast_, _stackCall)));
                    return new Argument();
                }
            }
            i_++;
        }
        if (koArgs(typesAll_, _values, _conf, _stackCall)) {
            return new Argument();
        }
        call_.getArgumentWrappers().addAllElts(_values);
        return AbstractParamChecker.prepareCallDyn(_previous, call_, _conf, _stackCall);
    }

    private static boolean koArgs(StringList _typesAll,CustList<ArgumentWrapper> _values, ContextEl _conf, StackCall _stackCall) {
        int i_ = IndexConstants.FIRST_INDEX;
        for (ArgumentWrapper a:_values) {
            String param_ = _typesAll.get(i_);
            String arg_ = getClName(_conf, _stackCall, a);
            if (!ExecInheritsAdv.checkQuick(param_, arg_, _conf, _stackCall)) {
                return true;
            }
            i_++;
        }
        return false;
    }
    private static String getClName(ContextEl _conf, StackCall _stackCall, ArgumentWrapper _a) {
        AbstractWrapper wr_ = _a.getWrapper();
        String arg_;
        if (wr_ == null) {
            arg_ = _a.getValue().getStruct().getClassName(_conf);
        } else {
            arg_ = wr_.getClassName(_stackCall, _conf);
        }
        return arg_;
    }

    private static int countWrappers(CustList<ArgumentWrapper> _values) {
        int wrappers_ = 0;
        for (ArgumentWrapper a: _values) {
            AbstractWrapper wrapper_ = a.getWrapper();
            if (wrapper_ != null) {
                wrappers_++;
            }
        }
        return wrappers_;
    }

    private static int countValues(CustList<ArgumentWrapper> _values) {
        int values_ = 0;
        for (ArgumentWrapper a: _values) {
            AbstractWrapper wrapper_ = a.getWrapper();
            if (wrapper_ == null) {
                values_++;
            }
        }
        return values_;
    }

    private static void feedTypes(CustList<String> _paramsFct, StringList _typesRef, StringList _types, StringList _typesAll) {
        int parNb_ = _paramsFct.size();
        for (int i = 0; i < parNb_; i++) {
            String param_ = _paramsFct.get(i);
            if (param_.startsWith("~")) {
                _typesRef.add(param_.substring(1));
                _typesAll.add(param_.substring(1));
            } else {
                _types.add(param_);
                _typesAll.add(param_);
            }
        }
    }

    private static String getBadCastMessage(String _classNameFound, String _className) {
        return StringUtil.concat(_className, RETURN_LINE, _classNameFound, RETURN_LINE);
    }

}
