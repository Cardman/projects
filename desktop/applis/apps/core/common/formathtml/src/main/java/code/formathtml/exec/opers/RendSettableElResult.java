package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.IdMap;

public interface RendSettableElResult {

    boolean resultCanBeSet();


    Argument calculateSetting(
            IdMap<RendDynOperationNode,ArgumentsPair> _nodes, Configuration _conf, Argument _right);

    Argument calculateCompoundSetting(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast);

    Argument calculateSemiSetting(
            IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _stored, byte _cast);
    Argument endCalculate(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, Configuration _conf, Argument _right);

    Argument endCalculate(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right);

}
