package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.MethodLambdaParentRetriever;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.inherits.RangeChecker;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.RangeStruct;
import code.expressionlanguage.structs.Struct;
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
        Argument arg_ = arrMethods(_context, lambdaMethodStruct.getMethodName(), methodLambdaParentRetriever.getParent(), methodLambdaParentRetriever.getArray(), _stack);
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

    private static Argument arrMethods(ContextEl _conf, String _l, Struct _instance, ArgumentListCall _call, StackCall _stackCall) {
        CustList<Argument> arguments_ = _call.getArguments();
        if (arguments_.isEmpty()) {
            return new Argument(new IntStruct(ExecArrayFieldOperation.getLength(_instance, _conf)));
        }
        if (StringUtil.quickEq(_l,"[:]")) {
            return rangeInts(_conf, _stackCall, arguments_, _instance);
        }
        if (StringUtil.quickEq(_l,"[:]=")) {
            return rangeIntsSet(_conf, _stackCall, arguments_, _instance,Argument.getNullableValue(_call.getRight()).getStruct());
        }
        Struct range_ = arguments_.last().getStruct();
        if (range_ instanceof RangeStruct) {
            Argument right_ = _call.getRight();
            if (right_ != null) {
                return new Argument(ExecArrayTemplates.setRange(_instance, range_, right_.getStruct(),_conf,_stackCall));
            }
            return new Argument(ExecArrayTemplates.getRange(_instance,range_, _conf, _stackCall));
        }
        return defArr(_conf, _call, _stackCall, _l, arguments_, _instance);
    }

    private static Argument defArr(ContextEl _conf, ArgumentListCall _call, StackCall _stackCall, String _name, CustList<Argument> _arguments, Struct _arrOrig) {
        int lastIndex_ = _arguments.size() - 1;
        Struct arr_ = _arrOrig;
        if (StringUtil.quickEq(_name,"[]=")) {
            for (int i = 0; i < lastIndex_; i++) {
                Struct ind_ = _arguments.get(i).getStruct();
                arr_ = ExecArrayTemplates.getElement(arr_, ind_, _conf, _stackCall);
                if (_conf.callsOrException(_stackCall)) {
                    return new Argument();
                }
            }
            Struct ind_ = _arguments.get(lastIndex_).getStruct();
            ExecArrayTemplates.setElement(arr_,ind_, Argument.getNullableValue(_call.getRight()).getStruct(), _conf, _stackCall);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
            return _call.getRight();
        }
        for (int i = 0; i < lastIndex_; i++) {
            Struct ind_ = _arguments.get(i).getStruct();
            arr_ = ExecArrayTemplates.getElement(arr_,ind_, _conf, _stackCall);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
        }
        Struct ind_ = _arguments.get(lastIndex_).getStruct();
        return new Argument(ExecArrayTemplates.getElement(arr_,ind_, _conf, _stackCall));
    }

    private static Argument rangeInts(ContextEl _conf, StackCall _stackCall, CustList<Argument> _arguments, Struct _arr) {
        if (_arguments.size() == 2) {
            Struct lower_ = _arguments.get(0).getStruct();
            Struct step_ = _arguments.get(1).getStruct();
            Argument range_ = RangeChecker.rangeUnlimitStep(_conf, _stackCall, lower_, step_);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
            return new Argument(ExecArrayTemplates.getRange(_arr,range_.getStruct(), _conf, _stackCall));
        }
        Struct lower_ = _arguments.last().getStruct();
        Struct upper_ = new IntStruct(ExecArrayFieldOperation.getLength(_arr, _conf));
        Argument range_ = RangeChecker.range(_conf, _stackCall, lower_, upper_);
        if (_conf.callsOrException(_stackCall)) {
            return new Argument();
        }
        return new Argument(ExecArrayTemplates.getRange(_arr,range_.getStruct(), _conf, _stackCall));
    }

    private static Argument rangeIntsSet(ContextEl _conf, StackCall _stackCall, CustList<Argument> _arguments, Struct _arr, Struct _right) {
        if (_arguments.size() == 2) {
            Struct lower_ = _arguments.get(0).getStruct();
            Struct step_ = _arguments.get(1).getStruct();
            Argument range_ = RangeChecker.rangeUnlimitStep(_conf, _stackCall, lower_, step_);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
            return new Argument(ExecArrayTemplates.setRange(_arr, range_.getStruct(),_right, _conf, _stackCall));
        }
        Struct lower_ = _arguments.last().getStruct();
        Struct upper_ = new IntStruct(ExecArrayFieldOperation.getLength(_arr, _conf));
        Argument range_ = RangeChecker.range(_conf, _stackCall, lower_, upper_);
        if (_conf.callsOrException(_stackCall)) {
            return new Argument();
        }
        return new Argument(ExecArrayTemplates.setRange(_arr, range_.getStruct(),_right, _conf, _stackCall));
    }
}
