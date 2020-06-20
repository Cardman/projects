package code.expressionlanguage.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.AbstractReflectPageEl;
import code.expressionlanguage.exec.calls.CastPageEl;
import code.expressionlanguage.exec.calls.MethodPageEl;
import code.expressionlanguage.exec.calls.util.CallConstructor;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;

public final class ProcessMethod {
    private static final String EMPTY_STRING = "";

    private ProcessMethod() {
    }

    public static void initializeClass(String _class, ContextEl _cont) {
        if (_cont.getClasses().isSuccessfulInitialized(_class)) {
            return;
        }
        _cont.getClasses().getLocks().initClass(_class);
        ExecutingUtil.addPage(_cont,ExecutingUtil.createInstancingClass(_cont,_class));
        _cont.getInit().loopCalling(_cont);
    }
    public static void initializeClassPre(String _class, ContextEl _cont) {
        ExecutingUtil.addPage(_cont,ExecutingUtil.createInstancingClass(_cont,_class));
        _cont.getInit().loopCalling(_cont);
    }
    public static Argument instanceArgument(String _class, ExecRootBlock _root, Argument _global, ConstructorId _id, CustList<Argument> _args, ContextEl _cont) {
        CallConstructor call_ = new CallConstructor();
        call_.setArgument(_global);
        call_.setId(_id);
        call_.setFieldName(EMPTY_STRING);
        AbstractCallingInstancingPageEl page_ = ExecutingUtil.createInstancing(_cont,_class, _root,call_, _args);
        ExecutingUtil.addPage(_cont,page_);
        _cont.getInit().loopCalling(_cont);
        return page_.getGlobalArgument();
    }

    public static Argument calculateArgument(Argument _global, String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont, Argument _right) {
        MethodPageEl page_ = ExecutingUtil.createCallingMethod(_cont,_global, _class, _method, _args,_right);
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

    public static Argument castArgument(String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        CastPageEl page_ = ExecutingUtil.createCallingCast(_cont,_class, _method,_args);
        ExecutingUtil.addPage(_cont,page_);
        _cont.getInit().loopCalling(_cont);
        return page_.getReturnedArgument();
    }
}
