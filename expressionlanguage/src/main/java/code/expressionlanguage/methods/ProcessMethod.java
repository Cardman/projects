package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.calls.AbstractReflectPageEl;
import code.expressionlanguage.calls.MethodPageEl;
import code.expressionlanguage.calls.util.CallConstructor;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
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
        _cont.addPage(_cont.createInstancingClass(_class));
        _cont.getInit().loopCalling(_cont);
    }
    public static void initializeClassPre(String _class, ContextEl _cont) {
        _cont.getClasses().preInitializeStaticFields(_class, _cont);
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
        return page_.getGlobalArgument();
    }

    public static Argument calculateArgument(Argument _global, String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        MethodPageEl page_ = _cont.createCallingMethod(_global, _class, _method, _args);
        _cont.addPage(page_);
        _cont.getInit().loopCalling(_cont);
        return page_.getReturnedArgument();
    }

    public static Argument reflectArgument(Argument _global, CustList<Argument> _args, ContextEl _cont, ReflectingType _reflect, boolean _lambda) {
        AbstractReflectPageEl page_ = _cont.createReflectMethod(_global, _args, _reflect, _lambda);
        _cont.addPage(page_);
        _cont.getInit().loopCalling(_cont);
        return page_.getReturnedArgument();
    }
}
