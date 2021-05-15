package code.expressionlanguage.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.AbstractReflectElement;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.calls.util.CustomFoundRecordConstructor;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;
import code.util.StringMap;

public final class ProcessMethod {
    private static final String EMPTY_STRING = "";

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

    public static ArgumentWrapper instanceRecordArgument(ExecFormattedRootBlock _class, ExecTypeFunction _root, StringMap<String> _id, CustList<Argument> _args, ContextEl _cont, StackCall _stackCall) {
        CustomFoundRecordConstructor found_ = new CustomFoundRecordConstructor(_class, _root,_id, EMPTY_STRING,-1,_args);
        AbstractPageEl page_ = ExecutingUtil.createRecordInstancing(_cont,found_);
        return loopAndReturn(_cont, _stackCall, page_);
    }

    public static ArgumentWrapper instanceArgument(ExecFormattedRootBlock _class, ExecTypeFunction _root, Argument _global, Parameters _args, ContextEl _cont, StackCall _stackCall) {
        CustomFoundConstructor found_ = new CustomFoundConstructor(_class, _root, EMPTY_STRING,-1, _global,_args,InstancingStep.NEWING);
        AbstractPageEl page_ = ExecutingUtil.createNewInstancing(_cont,found_);
        return loopAndReturn(_cont, _stackCall, page_);
    }

    public static ArgumentWrapper calculateArgument(Argument _global, ExecFormattedRootBlock _class, ExecTypeFunction _method, Parameters _args, ContextEl _cont, StackCall _stackCall) {
        AbstractPageEl page_ = ExecutingUtil.createCallingMethod(_cont,_global, _class, _method, _args);
        return loopAndReturn(_cont, _stackCall, page_);
    }
    public static ArgumentWrapper reflectArgument(ContextEl _cont, AbstractReflectElement _ref, StackCall _stackCall) {
        AbstractPageEl page_ = ExecutingUtil.createReflectMethod(_ref);
        return loopAndReturn(_cont, _stackCall, page_);
    }

    private static ArgumentWrapper loopAndReturn(ContextEl _cont, StackCall _stackCall, AbstractPageEl _page) {
        loop(_cont, _stackCall, _page);
        return new ArgumentWrapper(ExpressionLanguage.tryUnwrapp(_page.getWrapper(), _page.getReturnedArgument(), _cont, _stackCall), _page.getWrapper());
    }

    private static void loop(ContextEl _cont, StackCall _stackCall, AbstractPageEl _page) {
        ExecutingUtil.addPage(_cont, _page, _stackCall);
        _cont.getInit().loopCalling(_cont, _stackCall);
    }

}
