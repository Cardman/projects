package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public interface RendSettableElResult {

    boolean resultCanBeSet();


    Argument calculateSetting(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

}
