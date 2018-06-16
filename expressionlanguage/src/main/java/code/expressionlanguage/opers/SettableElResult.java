package code.expressionlanguage.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.IdMap;

public interface SettableElResult {

    void setVariable(boolean _variable);
    void setCatenizeStrings();
    boolean resultCanBeSet();
    ClassArgumentMatching getResultClass();

    Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right);

    void calculateSetting(
            ExecutableCode _conf, Argument _right);

    Argument calculateCompoundSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right);

    void calculateCompoundSetting(
            ExecutableCode _conf, String _op, Argument _right);

    Argument calculateSemiSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post);

    void calculateSemiSetting(
            ExecutableCode _conf, String _op, boolean _post);
}
