package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.MethodLambdaParentRetriever;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.inherits.RangeChecker;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.opers.ExecCompoundAffectationStringOperation;
import code.expressionlanguage.exec.symbols.ExecOperDir;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.core.StringUtil;

public final class LambdaMethodWithoutInfo extends AbstractBasicReflectPageEl {
    private boolean checkElement;
    private final LambdaMethodStruct lambdaMethodStruct;
    private final MethodLambdaParentRetriever methodLambdaParentRetriever;
    private boolean entered;
    public LambdaMethodWithoutInfo(LambdaMethodStruct _lms, ArgumentListCall _l) {
        super(true);
        lambdaMethodStruct = _lms;
        methodLambdaParentRetriever = new MethodLambdaParentRetriever(_l,_lms);
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        entered = true;
        if (!methodLambdaParentRetriever.retrieve(_context, _stack)){
            return false;
        }
        checkElement = true;
        if (_stack.getStopper().isStopAtRef(_context, _stack)) {
            return false;
        }
        checkElement = false;
        Struct arg_ = arrMethods(_context, lambdaMethodStruct.getOperSymbol(), lambdaMethodStruct.getMethodName(), methodLambdaParentRetriever.getParent(), methodLambdaParentRetriever.getArray(), _stack);
        if (_context.callsOrException(_stack)) {
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }

    public int getAncestor(){
        return methodLambdaParentRetriever.getAncestor();
    }

    public boolean isEntered() {
        return entered;
    }

    public boolean isCheckedParent() {
        return methodLambdaParentRetriever.getParent() == null;
    }
    public String getName() {
        return lambdaMethodStruct.getMethodName();
    }

    public Struct getInstance() {
        return methodLambdaParentRetriever.getParent();
    }

    public Struct getOriginalInstance() {
        return methodLambdaParentRetriever.getOriginalInstance();
    }
    public ArgumentListCall getArguments() {
        return methodLambdaParentRetriever.getArray();
    }

    public boolean isCheckElement() {
        return checkElement;
    }

    public CommonOperSymbol current() {
        return lambdaMethodStruct.getOperSymbol();
    }
    private static Struct arrMethods(ContextEl _conf, CommonOperSymbol _operSymbol, String _l, Struct _instance, ArgumentListCall _call, StackCall _stackCall) {
        if (_operSymbol != null) {
            ExecOperDir e_ = new ExecOperDir(_operSymbol);
            return ExecCompoundAffectationStringOperation.calculatedValue(e_,ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(_call.getArgumentWrappers())), ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(_call.getArgumentWrappers())), _conf,_stackCall, _stackCall.getLastPage());
        }
        CustList<Struct> arguments_ = _call.getArguments();
        if (arguments_.isEmpty()) {
            return new IntStruct(ExecArrayFieldOperation.getLength(_instance, _conf));
        }
        if (StringUtil.quickEq(_l,"[:]")) {
            return rangeInts(_conf, _stackCall, arguments_, _instance);
        }
        if (StringUtil.quickEq(_l,"[:]=")) {
            return rangeIntsSet(_conf, _stackCall, arguments_, _instance,ArgumentListCall.getNull(_call.getRight()));
        }
        Struct range_ = arguments_.last();
        if (range_ instanceof RangeStruct) {
            Struct right_ = _call.getRight();
            if (right_ != null) {
                return ExecArrayTemplates.setRange(_instance, range_, right_,_conf,_stackCall);
            }
            return ExecArrayTemplates.getRange(_instance,range_, _conf, _stackCall);
        }
        return defArr(_conf, _call, _stackCall, _l, arguments_, _instance);
    }

    private static Struct defArr(ContextEl _conf, ArgumentListCall _call, StackCall _stackCall, String _name, CustList<Struct> _arguments, Struct _arrOrig) {
        int lastIndex_ = _arguments.size() - 1;
        Struct arr_ = _arrOrig;
        if (StringUtil.quickEq(_name,"[]=")) {
            for (int i = 0; i < lastIndex_; i++) {
                Struct ind_ = _arguments.get(i);
                arr_ = ExecArrayTemplates.getElement(arr_, ind_, _conf, _stackCall);
                if (_conf.callsOrException(_stackCall)) {
                    return NullStruct.NULL_VALUE;
                }
            }
            Struct ind_ = _arguments.get(lastIndex_);
            ExecArrayTemplates.setElement(arr_,ind_, ArgumentListCall.getNull(_call.getRight()), _conf, _stackCall);
            if (_conf.callsOrException(_stackCall)) {
                return NullStruct.NULL_VALUE;
            }
            return _call.getRight();
        }
        for (int i = 0; i < lastIndex_; i++) {
            Struct ind_ = _arguments.get(i);
            arr_ = ExecArrayTemplates.getElement(arr_,ind_, _conf, _stackCall);
            if (_conf.callsOrException(_stackCall)) {
                return NullStruct.NULL_VALUE;
            }
        }
        Struct ind_ = _arguments.get(lastIndex_);
        return ExecArrayTemplates.getElement(arr_,ind_, _conf, _stackCall);
    }

    private static Struct rangeInts(ContextEl _conf, StackCall _stackCall, CustList<Struct> _arguments, Struct _arr) {
        if (_arguments.size() == 2) {
            Struct lower_ = _arguments.get(0);
            Struct step_ = _arguments.get(1);
            Struct range_ = RangeChecker.rangeUnlimitStep(_conf, _stackCall, lower_, step_);
            if (_conf.callsOrException(_stackCall)) {
                return NullStruct.NULL_VALUE;
            }
            return ExecArrayTemplates.getRange(_arr,range_, _conf, _stackCall);
        }
        Struct lower_ = _arguments.last();
        Struct upper_ = new IntStruct(ExecArrayFieldOperation.getLength(_arr, _conf));
        Struct range_ = RangeChecker.range(_conf, _stackCall, lower_, upper_);
        if (_conf.callsOrException(_stackCall)) {
            return NullStruct.NULL_VALUE;
        }
        return ExecArrayTemplates.getRange(_arr,range_, _conf, _stackCall);
    }

    private static Struct rangeIntsSet(ContextEl _conf, StackCall _stackCall, CustList<Struct> _arguments, Struct _arr, Struct _right) {
        if (_arguments.size() == 2) {
            Struct lower_ = _arguments.get(0);
            Struct step_ = _arguments.get(1);
            Struct range_ = RangeChecker.rangeUnlimitStep(_conf, _stackCall, lower_, step_);
            if (_conf.callsOrException(_stackCall)) {
                return NullStruct.NULL_VALUE;
            }
            return ExecArrayTemplates.setRange(_arr, range_,_right, _conf, _stackCall);
        }
        Struct lower_ = _arguments.last();
        Struct upper_ = new IntStruct(ExecArrayFieldOperation.getLength(_arr, _conf));
        Struct range_ = RangeChecker.range(_conf, _stackCall, lower_, upper_);
        if (_conf.callsOrException(_stackCall)) {
            return NullStruct.NULL_VALUE;
        }
        return ExecArrayTemplates.setRange(_arr, range_,_right, _conf, _stackCall);
    }
}
