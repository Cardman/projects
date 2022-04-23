package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.DefNodeContainer;

public final class RendRequestUtil {

    private RendRequestUtil() {
    }


    public static void setRendObject(DefNodeContainer _nodeContainer,
                                     Struct _attribute, ContextEl _context, RendStackCall _rendStackCall) {
        _nodeContainer.getInput().setValue(_rendStackCall.getStackCall(),_context,new Argument(_attribute));
        RendDynOperationNode.processCall(new Argument(_attribute),_context,_rendStackCall);
    }
}
