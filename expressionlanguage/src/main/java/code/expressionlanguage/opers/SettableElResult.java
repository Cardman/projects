package code.expressionlanguage.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;

public interface SettableElResult {

    void setVariable(boolean _variable);
    void setCatenizeStrings();
    boolean resultCanBeSet();
    ClassArgumentMatching getResultClass();

    Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op);

    void calculateSetting(CustList<OperationNode> _nodes,
            ContextEl _conf, String _op);
}
