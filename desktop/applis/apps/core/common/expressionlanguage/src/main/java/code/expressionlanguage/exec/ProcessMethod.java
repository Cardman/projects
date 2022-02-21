package code.expressionlanguage.exec;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

public final class ProcessMethod {

    private ProcessMethod() {
    }

    public static void initializeClass(String _class, ExecRootBlock _rootBlock, ContextEl _cont, StackCall _stackCall) {
        if (_cont.getLocks().getState(_class) == InitClassState.SUCCESS) {
            return;
        }
        _cont.getLocks().initClass(_class);
        loop(_cont, _stackCall, ExecutingUtil.createInstancingClass(_rootBlock,new ExecFormattedRootBlock(_rootBlock,_class),null));
    }
    public static void initializeClassPre(String _class, ExecRootBlock _rootBlock, ContextEl _cont, StackCall _stackCall) {
        loop(_cont, _stackCall, ExecutingUtil.createInstancingClass(_rootBlock,new ExecFormattedRootBlock(_rootBlock,_class),null));
    }

    public static ArgumentWrapper calculate(CallingState _custom, ContextEl _cont, StackCall _stackCall) {
        AbstractPageEl page_ = _custom.processAfterOperation(_cont,_stackCall);
        return loopAndReturn(_cont, _stackCall, page_);
    }

    private static ArgumentWrapper loopAndReturn(ContextEl _cont, StackCall _stackCall, AbstractPageEl _page) {
        loop(_cont, _stackCall, _page);
        return new ArgumentWrapper(_page.getReturnedArgument(), _page.getWrapper());
    }

    private static void loop(ContextEl _cont, StackCall _stackCall, AbstractPageEl _page) {
        ExecutingUtil.addPage(_cont, _page, _stackCall);
        _cont.getInit().loopCalling(_cont, _stackCall);
    }

}
