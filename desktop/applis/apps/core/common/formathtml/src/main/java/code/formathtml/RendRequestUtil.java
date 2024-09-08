package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.DefNodeContainer;

public final class RendRequestUtil {

    private RendRequestUtil() {
    }


    public static ArgumentWrapper setRendObject(DefNodeContainer _nodeContainer,
                                     Struct _attribute, ContextEl _context, RendStackCall _rendStackCall) {
        _nodeContainer.getInput().setValue(_rendStackCall.getStackCall(),_context,new Argument(_attribute));
        return RendDynOperationNode.tryGetValue(_context, _rendStackCall, new ArgumentWrapper(_attribute));
    }
}
