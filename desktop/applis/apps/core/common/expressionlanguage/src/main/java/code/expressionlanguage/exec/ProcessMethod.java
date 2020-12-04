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
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;
import code.util.StringMap;

public final class ProcessMethod {
    private static final String EMPTY_STRING = "";

    private ProcessMethod() {
    }

    public static void initializeClass(String _class, ExecRootBlock _rootBlock, ContextEl _cont) {
        if (_cont.getLocks().getState(_class) == InitClassState.SUCCESS) {
            return;
        }
        _cont.getLocks().initClass(_class);
        ExecutingUtil.addPage(_cont,ExecutingUtil.createInstancingClass(_cont,_rootBlock,_class,null));
        _cont.getInit().loopCalling(_cont);
    }
    public static void initializeClassPre(String _class,ExecRootBlock _rootBlock, ContextEl _cont) {
        ExecutingUtil.addPage(_cont,ExecutingUtil.createInstancingClass(_cont,_rootBlock,_class,null));
        _cont.getInit().loopCalling(_cont);
    }

    public static ArgumentWrapper instanceRecordArgument(String _class, ExecTypeFunction _root, StringMap<String> _id, CustList<Argument> _args, ContextEl _cont) {
        CustomFoundRecordConstructor found_ = new CustomFoundRecordConstructor(_class, _root,_id, EMPTY_STRING,-1,_args);
        AbstractPageEl page_ = ExecutingUtil.createRecordInstancing(_cont,found_);
        ExecutingUtil.addPage(_cont,page_);
        _cont.getInit().loopCalling(_cont);
        return new ArgumentWrapper(ExpressionLanguage.tryUnwrapp(page_.getWrapper(),page_.getReturnedArgument(),_cont),page_.getWrapper());
    }

    public static ArgumentWrapper instanceArgument(String _class, ExecTypeFunction _root, Argument _global, Parameters _args, ContextEl _cont) {
        CustomFoundConstructor found_ = new CustomFoundConstructor(_class, _root, EMPTY_STRING,-1, _global,_args,InstancingStep.NEWING);
        AbstractPageEl page_ = ExecutingUtil.createNewInstancing(_cont,found_);
        ExecutingUtil.addPage(_cont,page_);
        _cont.getInit().loopCalling(_cont);
        return new ArgumentWrapper(ExpressionLanguage.tryUnwrapp(page_.getWrapper(),page_.getReturnedArgument(),_cont),page_.getWrapper());
    }

    public static ArgumentWrapper calculateArgument(Argument _global, String _class, ExecTypeFunction _method, Parameters _args, ContextEl _cont) {
        AbstractPageEl page_ = ExecutingUtil.createCallingMethod(_cont,_global, _class, _method, _args);
        ExecutingUtil.addPage(_cont,page_);
        _cont.getInit().loopCalling(_cont);
        return new ArgumentWrapper(ExpressionLanguage.tryUnwrapp(page_.getWrapper(),page_.getReturnedArgument(),_cont),page_.getWrapper());
    }
    public static ArgumentWrapper reflectArgument(ContextEl _cont, AbstractReflectElement _ref) {
        AbstractPageEl page_ = ExecutingUtil.createReflectMethod(_cont,_ref);
        ExecutingUtil.addPage(_cont,page_);
        _cont.getInit().loopCalling(_cont);
        return new ArgumentWrapper(ExpressionLanguage.tryUnwrapp(page_.getWrapper(),page_.getReturnedArgument(),_cont),page_.getWrapper());
    }

    public static ArgumentWrapper castArgument(String _class, ExecTypeFunction _method, Parameters _args, ContextEl _cont) {
        AbstractPageEl page_ = ExecutingUtil.createCallingCast(_cont,_class, _method,_args);
        ExecutingUtil.addPage(_cont,page_);
        _cont.getInit().loopCalling(_cont);
        return new ArgumentWrapper(ExpressionLanguage.tryUnwrapp(page_.getWrapper(),page_.getReturnedArgument(),_cont),page_.getWrapper());
    }
}
