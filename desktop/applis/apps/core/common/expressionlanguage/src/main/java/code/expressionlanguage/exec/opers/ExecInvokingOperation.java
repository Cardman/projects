package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ExecTypeFunctionInst;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.IdMap;
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

    protected void setCheckedResult(Argument _res, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stack, boolean _set) {
        if (_stack.getStopper().hasValueStd(_stack)) {
            _stack.getBreakPointInfo().getStackState().resetVisitAndCheckBp();
            _stack.getBreakPointInfo().getBreakPointMiddleInfo().setCalculated(new ArgumentWrapper(_res,null));
            return;
        }
        setResult(_res, _conf, _nodes, _stack, _set);
    }
    public void setResult(Argument _res, ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stack, boolean _set) {
        if (_set) {
            setQuickNoConvertSimpleArgument(_res, _conf, _nodes, _stack);
            return;
        }
        setSimpleArgument(_res, _conf, _nodes, _stack);
    }
    public static ArgumentListCall fectchInstFormattedArgs(ExecFormattedRootBlock _formatted, ExecInstancingCommonContent _lastType, ContextEl _conf, StackCall _stack, CustList<ExecOperationInfo> _infos) {
        String lastType_ = ExecInherits.quickFormat(_formatted, _lastType.getLastType());
        return fectchArgs(lastType_, _lastType.getNaturalVararg(), _conf,_stack, _infos);
    }

    public static ArgumentListCall fetchFormattedArgs(ContextEl _conf, StackCall _stack, Struct _pr, ExecTypeFunctionInst _ins, CustList<ExecOperationInfo> _infos) {
        String cl_ = _pr.getClassName(_conf);
        String lastType_ = ExecFieldTemplates.formatType(_conf, _ins.getPair().getType(), _ins.getInst().getLastType(), cl_);
        return fectchArgs(lastType_, _ins.getInst().getNaturalVararg(), _conf,_stack, _infos);
    }

    public static ArgumentListCall fectchArgs(String _lastType, int _naturalVararg, ContextEl _conf, StackCall _stack, CustList<ExecOperationInfo> _infos) {
        ArgumentList argumentList_ = fectchArgs(_lastType, _naturalVararg, _infos);
        if (argumentList_.getNaturalVararg() <= -1) {
            return argumentList_.getArguments();
        }
        ExecArrayTemplates.checkedElements(ExecArrayFieldOperation.getArray(ArgumentListCall.toStr(argumentList_.getArguments().getArgumentWrappers().last().getValue()),_conf),_conf,_stack);
        return argumentList_.getArguments();
    }

    public static ArgumentList fectchArgs(String _lastType, int _naturalVararg, CustList<ExecOperationInfo> _infos) {
        ArgumentList argumentList_ = listNamedArguments(_naturalVararg, _infos);
        CustList<ArgumentWrapper> first_ = argumentList_.getArguments().getArgumentWrappers();
        listArguments(argumentList_.getNaturalVararg(), _lastType, first_);
        return argumentList_;
    }
    public static ArgumentList listNamedArguments(int _naturalVararg, CustList<ExecOperationInfo> _infos) {
        ArgumentList out_ = listNamedArguments(_infos);
        out_.setNaturalVararg(_naturalVararg);
        return out_;
    }

    public static void listArguments(int _natVararg, String _lastType, CustList<ArgumentWrapper> _nodes) {
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
        ArrayStruct str_ = ArrayStruct.instance(clArr_, optArgs_);
        reord_.add(new ArgumentWrapper(new Argument(str_),null));
        _nodes.clear();
        _nodes.addAllElts(reord_);
    }

    public static Argument getAnnotation(Argument _previous, String _name, ContextEl _conf, StackCall _stackCall) {
        Struct argPrev_ = _previous.getStruct();
        String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
        if (!(argPrev_ instanceof AnnotationStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return new Argument();
        }
        String clName_ = argPrev_.getClassName(_conf);
        Struct ret_ = ExecFieldTemplates.getInstanceField(_previous,_conf, _stackCall, new ClassField(clName_, _name)).getStruct();
        return swallowCopy(ret_);
    }

    public static Argument swallowCopy(Struct _ret) {
        Argument a_;
        if (_ret instanceof ArrayStruct) {
            ArrayStruct orig_ = (ArrayStruct) _ret;
            a_ = new Argument(orig_.swallowCopy());
        } else {
            a_ = new Argument(_ret);
        }
        return a_;
    }

    public static Argument cloneArray(Argument _previous, ContextEl _conf, StackCall _stackCall) {
        Struct argPrev_ = _previous.getStruct();
        String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
        if (!(argPrev_ instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return new Argument();
        }
        //clone object
        ArrayStruct arr_ = (ArrayStruct) argPrev_;
        ArrayStruct copy_ = arr_.swallowCopy();
        _stackCall.getInitializingTypeInfos().addSensibleElementsFromClonedArray(arr_, copy_);
        return new Argument(copy_);
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
        ExecOverrideInfo res_ = _conf.getClasses().getRedirection(_named,base_);
        if (res_ != null) {
            return res_;
        }
        ExecRootBlock type_ = _named.getType();
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

    public static void prepareCallDynReflect(Argument _previous, ArrayStruct _values, int _ref, ContextEl _conf, StackCall _stackCall) {
        CustList<String> paramsFct_ = paramsFct(_previous, _conf);
        CustomFoundExc c_ = foundExc(_values, _conf, _stackCall, paramsFct_);
        if (c_ != null) {
            _stackCall.setCallingState(c_);
            return;
        }
        ArgumentListCall argumentListCall_ = new ArgumentListCall();
        int i_ = 0;
        for (String c: paramsFct_) {
            if (c.startsWith("~")) {
                ArgumentWrapper wr_ = wrapper(_values,_ref, i_, c.substring(1));
                argumentListCall_.getArgumentWrappers().add(wr_);
            } else {
                argumentListCall_.getArgumentWrappers().add(new ArgumentWrapper(_values.get(i_)));
            }
            i_++;
        }
        AbstractParamChecker.prepareCallDyn(_previous,argumentListCall_,_ref,_conf, _stackCall);
    }
    public static CustomFoundExc foundExc(ArrayStruct _values, ContextEl _conf, StackCall _stackCall, CustList<String> _params) {
        int valuesSize_ = _values.getLength();
        if (valuesSize_ != _params.size()) {
            LgNames lgNames_ = _conf.getStandards();
            String null_ = lgNames_.getContent().getCoreNames().getAliasBadArgNumber();
            return new CustomFoundExc(new ErrorStruct(_conf, ExecTemplates.countDiff(valuesSize_, _params.size()).toString(), null_, _stackCall));
        }
        for (int i = 0; i < valuesSize_; i++) {
            Struct arg_ = _values.get(i);
            String param_ = _params.get(i);
            if (param_.startsWith("~")) {
                param_ = param_.substring(1);
            }
            Struct err_ = ExecInheritsAdv.checkObjectEx(param_, arg_.getClassName(_conf), _conf, _stackCall);
            if (err_ != null) {
                return new CustomFoundExc(err_);
            }
        }
        return null;
    }

    public static CustList<String> paramsFct(Argument _previous, ContextEl _conf) {
        StringList parts_ = StringExpUtil.getAllTypes(Argument.getNullableValue(_previous).getStruct().getClassName(_conf));
        return parts_.leftMinusOne(parts_.size() - 2);
    }

    public static ArgumentWrapper wrapper(ArrayStruct _values, int _ref, int _i, String _type) {
        boolean r_ = _ref == 1;
        boolean later_ = _ref == 2;
        Struct struct_ = _values.get(_i);
        LocalVariable local_ = LocalVariable.newLocalVariable(struct_, _type);
        AbstractWrapper v_;
        if (later_) {
            v_ = new ReflectVariableLaterWrapper(local_, _values, _i);
        } else {
            v_ = new ReflectVariableWrapper(local_, _values,index(r_, _i));
        }
        return new ArgumentWrapper(new Argument(_values.get(_i)), v_);
    }

    public static int index(boolean _r, int _i) {
        if (_r) {
            return _i;
        }
        return -1;
    }

    public static void prepareCallDynNormal(Argument _previous, ArgumentListCall _values, ContextEl _conf, StackCall _stackCall) {
        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        ArgumentListCall call_ = new ArgumentListCall();
        if (!(ls_ instanceof LambdaStruct)) {
            AbstractParamChecker.prepareCallDyn(_previous,call_,0,_conf, _stackCall);
            return;
        }
        prepareCallDynNormalDefault(_previous, _values.getArgumentWrappers(), _conf, _stackCall);
    }

    private static void prepareCallDynNormalDefault(Argument _previous, CustList<ArgumentWrapper> _values, ContextEl _conf, StackCall _stackCall) {
        CustomFoundExc ex_ = foundExc(_previous, _values, _conf, _stackCall);
        if (ex_ != null) {
            _stackCall.setCallingState(ex_);
            return;
        }
        ArgumentListCall call_ = new ArgumentListCall();
        call_.getArgumentWrappers().addAllElts(_values);
        AbstractParamChecker.prepareCallDyn(_previous, call_,0, _conf, _stackCall);
    }

    public static CustomFoundExc foundExc(Argument _previous, CustList<ArgumentWrapper> _values, ContextEl _conf, StackCall _stackCall) {
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
            return new CustomFoundExc(new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall));
        }
        for (Sizes s: new CustList<Sizes>(
                new Sizes(values_, types_.size()),
                new Sizes(wrappers_, typesRef_.size())
        )) {
            if (s.getArg() != s.getParam()) {
                LgNames stds_ = _conf.getStandards();
                String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                StringBuilder mess_ = ExecTemplates.countDiff(s.getArg(), s.getParam());
                return new CustomFoundExc(new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall));
            }

        }
        int i_ = IndexConstants.FIRST_INDEX;
        for (ArgumentWrapper a: _values) {
            String param_ = paramsFct_.get(i_);
            if (param_.startsWith("~")) {
                if (a.getWrapper() == null) {
                    LgNames stds_ = _conf.getStandards();
                    String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
                    return new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(param_, a.getValue().getStruct().getClassName(_conf)),cast_, _stackCall));
                }
            } else {
                if (a.getWrapper() != null) {
                    LgNames stds_ = _conf.getStandards();
                    String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
                    return new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(param_, a.getWrapper().getClassName(_conf)),cast_, _stackCall));
                }
            }
            i_++;
        }
        return koArgs(typesAll_, _values, _conf, _stackCall);
    }
    private static CustomFoundExc koArgs(StringList _typesAll,CustList<ArgumentWrapper> _values, ContextEl _conf, StackCall _stackCall) {
        int i_ = IndexConstants.FIRST_INDEX;
        for (ArgumentWrapper a:_values) {
            String param_ = _typesAll.get(i_);
            String arg_ = getClName(_conf, a);
            Struct err_ = ExecInheritsAdv.checkObjectEx(param_, arg_, _conf, _stackCall);
            if (err_ != null) {
                return new CustomFoundExc(err_);
            }
            i_++;
        }
        return null;
    }
    private static String getClName(ContextEl _conf, ArgumentWrapper _a) {
        AbstractWrapper wr_ = _a.getWrapper();
        String arg_;
        if (wr_ == null) {
            arg_ = _a.getValue().getStruct().getClassName(_conf);
        } else {
            arg_ = wr_.getClassName(_conf);
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
