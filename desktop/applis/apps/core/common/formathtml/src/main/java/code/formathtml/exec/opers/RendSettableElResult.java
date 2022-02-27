package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public interface RendSettableElResult {

    boolean resultCanBeSet();


    Argument calculateSetting(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

    Argument calculateCompoundString(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

    Argument calculateCompoundSetting(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, StringList _cl, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

    Argument calculateCompoundSetting(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, String _op, Argument _right, byte _cl, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

    Argument calculateSemiSetting(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, String _op, boolean _post, byte _cast, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

}
