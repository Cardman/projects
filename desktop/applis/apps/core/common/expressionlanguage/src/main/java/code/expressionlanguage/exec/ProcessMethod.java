package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;
import code.expressionlanguage.exec.inherits.IndirectCalledFctUtil;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ProcessMethod {

    private ProcessMethod() {
    }

    public static void initializeClass(String _class, ExecRootBlock _rootBlock, ContextEl _cont, StackCall _stackCall) {
        if (stateMismatch(_rootBlock, _cont, InitClassState.SUCCESS)) {
            _cont.getLocks().initClass(_rootBlock);
            calculate(new NotInitializedClass(new ExecFormattedRootBlock(_rootBlock, _class), _rootBlock, null), _cont, _stackCall);
        }
    }

    public static boolean stateMismatch(ExecRootBlock _rootBlock, ContextEl _cont, InitClassState _state) {
        return !stateMatch(_rootBlock, _cont, _state);
    }

    public static boolean stateMatch(ExecRootBlock _rootBlock, ContextEl _cont, InitClassState _state) {
        return _cont.getLocks().getState(_rootBlock) == _state;
    }

    public static void initializeClassPre(String _class, ExecRootBlock _rootBlock, ContextEl _cont, StackCall _stackCall) {
        calculate(new NotInitializedClass(new ExecFormattedRootBlock(_rootBlock,_class),_rootBlock,null),_cont,_stackCall);
    }

    public static String convertStr(Struct _str, ContextEl _r, StackCall _stackCall) {
        Argument out_ = new Argument(_str);
        out_ = IndirectCalledFctUtil.processString(out_, _r, _stackCall);
        out_ = tryCalculate(_r, _stackCall, out_);
        return ExecCatOperation.getDisplayable(out_, _r).getInstance();
    }

    public static Argument tryCalculate(ContextEl _r, StackCall _stackCall, Argument _out) {
        Argument out_;
        CallingState state_ = _stackCall.getCallingState();
        if (state_ != null) {
            out_ = calculate(state_, _r, _stackCall).getValue();
        } else {
            out_ = _out;
        }
        return out_;
    }

    public static String error(ContextEl _cont, StackCall _stackCall) {
        CustomFoundExc exc_ = _stackCall.trueException();
        if (exc_ != null) {
            Struct exception_ = exc_.getStruct();
            if (exception_ instanceof DisplayableStruct) {
                return ((DisplayableStruct)exception_).getDisplayedString(_cont).getInstance();
            }
            _stackCall.setNullCallingState();
            Argument out_ = new Argument(exception_);
            out_ = IndirectCalledFctUtil.processString(out_, _cont, _stackCall);
            CallingState state_ = _stackCall.getCallingState();
            boolean convert_ = false;
            if (state_ instanceof CustomFoundMethod) {
                CustomFoundMethod method_ = (CustomFoundMethod) state_;
                out_ = calculate(method_, _cont, _stackCall).getValue();
                convert_ = true;
            }
            if (!_cont.callsOrException(_stackCall)) {
                if (convert_) {
                    out_ = new Argument(ExecCatOperation.getDisplayable(out_,_cont));
                }
                return NumParsers.getString(out_.getStruct()).getInstance();
            }
            return _cont.getStandards().getDisplayedStrings().getNullString();
        }
        return null;
    }

    public static ArgumentWrapper calculate(CallingState _custom, ContextEl _cont, StackCall _stackCall) {
        AbstractPageEl page_ = _custom.processAfterOperation(_cont,_stackCall);
        if (page_ == null) {
            _stackCall.setReturnedArgument(NullStruct.NULL_VALUE);
            _stackCall.setWrapper(null);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        return loopAndReturn(_cont, _stackCall, page_);
    }

    private static ArgumentWrapper loopAndReturn(ContextEl _cont, StackCall _stackCall, AbstractPageEl _page) {
        loop(_cont, _stackCall, _page);
        return _stackCall.aw();
    }

    private static void loop(ContextEl _cont, StackCall _stackCall, AbstractPageEl _page) {
        ExecutingUtil.addPage(_page, _stackCall);
        _cont.getInit().loopCalling(_cont, _stackCall);
    }
}
