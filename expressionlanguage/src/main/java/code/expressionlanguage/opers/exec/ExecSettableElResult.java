package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.util.IdMap;

public interface ExecSettableElResult extends ExecOperable {

    Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right);

    Argument calculateCompoundSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right);

    Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post);

    Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right);

    Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post, Argument _stored, Argument _right);
}
