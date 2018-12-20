package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.IdMap;

public interface ExecSettableElResult extends ExecOperable {

    boolean resultCanBeSet();
    ClassArgumentMatching getResultClass();

    Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right);

    void calculateSetting(
            ExecutableCode _conf, Argument _right);

    Argument calculateCompoundSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right);

    void calculateCompoundSetting(
            ExecutableCode _conf, String _op, Argument _right);

    Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post);

    void calculateSemiSetting(
            ExecutableCode _conf, String _op, boolean _post);
    Argument endCalculate(ExecutableCode _conf, Argument _right);
    Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right);
    Argument endCalculate(ExecutableCode _conf, boolean _post, Argument _stored, Argument _right);
    Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post, Argument _stored, Argument _right);
}
