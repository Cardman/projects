package code.expressionlanguage.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractReflectPageEl;
import code.expressionlanguage.exec.calls.CastPageEl;
import code.expressionlanguage.exec.calls.MethodPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.Parameters;
import code.util.CustList;

public final class ProcessMethod {
    private static final String EMPTY_STRING = "";

    private ProcessMethod() {
    }

    public static void initializeClass(String _class, ExecRootBlock _rootBlock, ContextEl _cont) {
        if (_cont.getClasses().isSuccessfulInitialized(_class)) {
            return;
        }
        _cont.getClasses().getLocks().initClass(_class);
        ExecutingUtil.addPage(_cont,ExecutingUtil.createInstancingClass(_cont,_rootBlock,_class,null));
        _cont.getInit().loopCalling(_cont);
    }
    public static void initializeClassPre(String _class,ExecRootBlock _rootBlock, ContextEl _cont) {
        ExecutingUtil.addPage(_cont,ExecutingUtil.createInstancingClass(_cont,_rootBlock,_class,null));
        _cont.getInit().loopCalling(_cont);
    }

    public static Argument instanceArgument(String _class, ExecRootBlock _root, Argument _global, ExecNamedFunctionBlock _id, Parameters _args, ContextEl _cont) {
        CustomFoundConstructor found_ = new CustomFoundConstructor(_class,_root,EMPTY_STRING,-1,_id,_global,_args,InstancingStep.NEWING);
        AbstractCallingInstancingPageEl page_ = ExecutingUtil.createNewInstancing(_cont,found_);
        ExecutingUtil.addPage(_cont,page_);
        _cont.getInit().loopCalling(_cont);
        return page_.getGlobalArgument();
    }

    public static Argument calculateArgument(Argument _global, String _class, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _method, Parameters _args, ContextEl _cont) {
        MethodPageEl page_ = ExecutingUtil.createCallingMethod(_cont,_global, _class,_rootBlock, _method, _args);
        ExecutingUtil.addPage(_cont,page_);
        _cont.getInit().loopCalling(_cont);
        return page_.getReturnedArgument();
    }

    public static Argument reflectArgument(Argument _global, CustList<Argument> _args, ContextEl _cont, ReflectingType _reflect, boolean _lambda) {
        AbstractReflectPageEl page_ = ExecutingUtil.createReflectMethod(_cont,_global, _args, _reflect, _lambda);
        ExecutingUtil.addPage(_cont,page_);
        _cont.getInit().loopCalling(_cont);
        return page_.getReturnedArgument();
    }

    public static Argument castArgument(String _class, ExecRootBlock _rootBlock,ExecNamedFunctionBlock _method, Parameters _args, ContextEl _cont) {
        CastPageEl page_ = ExecutingUtil.createCallingCast(_cont,_class,_rootBlock, _method,_args);
        ExecutingUtil.addPage(_cont,page_);
        _cont.getInit().loopCalling(_cont);
        return page_.getReturnedArgument();
    }
}
