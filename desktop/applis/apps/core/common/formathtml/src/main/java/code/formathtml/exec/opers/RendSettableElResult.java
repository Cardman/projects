package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public interface RendSettableElResult {

    boolean resultCanBeSet();


    Argument calculateSetting(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

    Argument calculateCompoundString(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

    Argument calculateCompoundSetting(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, String _op, Argument _right, ExecClassArgumentMatching _cl, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

    Argument calculateSemiSetting(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, String _op, boolean _post, byte _cast, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);
    Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

    Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

}
