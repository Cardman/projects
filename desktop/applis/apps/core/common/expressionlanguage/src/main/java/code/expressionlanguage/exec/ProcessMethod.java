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

    public static ArgumentWrapper instanceRecordArgument(ContextEl _cont, StackCall _stackCall, CustomFoundRecordConstructor _found) {
        AbstractPageEl page_ = ExecutingUtil.createRecordInstancing(_cont,_found);
        return loopAndReturn(_cont, _stackCall, page_);
    }

    public static ArgumentWrapper instanceArgument(ContextEl _cont, StackCall _stackCall, CustomFoundConstructor _found) {
        AbstractPageEl page_ = ExecutingUtil.createNewInstancing(_cont,_found);
        return loopAndReturn(_cont, _stackCall, page_);
    }

    public static ArgumentWrapper calculateArgument(CustomFoundMethod _custom, ContextEl _cont, StackCall _stackCall) {
        AbstractPageEl page_ = ExecutingUtil.createCallingMethod(_cont,_custom.getGl(), _custom.getClassName(),_custom.getPair(), _custom.getArguments());
        return loopAndReturn(_cont, _stackCall, page_);
    }
    public static ArgumentWrapper reflectArgument(ContextEl _cont, AbstractReflectElement _ref, StackCall _stackCall) {
        AbstractPageEl page_ = ExecutingUtil.createReflectMethod(_ref);
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
