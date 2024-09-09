package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public interface RendSettableElResult {

    boolean resultCanBeSet();


    Struct calculateSetting(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Struct _right, ContextEl _context, RendStackCall _rendStack);

}
