package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractCallingInstancingPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.MethodPageEl;
import code.expressionlanguage.methods.util.CallConstructor;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;

public final class ProcessMethod {
    private static final String EMPTY_STRING = "";

    private ProcessMethod() {
    }

    public static void initializeClass(String _class, ContextEl _cont) {
        _cont.getClasses().getLocks().initClass(_class);
        _cont.addPage(_cont.createInstancingClass(_class));
        _cont.getInit().loopCalling(_cont);
    }
    public static Argument instanceArgument(String _class, Argument _global, ConstructorId _id, CustList<Argument> _args, ContextEl _cont) {
        CallConstructor call_ = new CallConstructor();
        call_.setArgument(_global);
        call_.setId(_id);
        call_.setFieldName(EMPTY_STRING);
        AbstractCallingInstancingPageEl page_ = _cont.createInstancing(_class, call_, _args);
        _cont.addPage(page_);
        _cont.getInit().loopCalling(_cont);
        return page_.getReturnedArgument();
    }

    public static Argument calculateArgument(Argument _global, String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        MethodPageEl page_ = _cont.createCallingMethod(_global, _class, _method, _args);
        _cont.addPage(page_);
        _cont.getInit().loopCalling(_cont);
        return page_.getReturnedArgument();
    }
}
