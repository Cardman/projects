package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.exec.Operable;
import code.formathtml.Configuration;
import code.util.IdMap;

public interface RendSettableElResult {

    boolean resultCanBeSet();


    void calculateSetting(
            ExecutableCode _conf, Argument _right);

    void calculateCompoundSetting(
            ExecutableCode _conf, String _op, Argument _right);

    void calculateSemiSetting(
            ExecutableCode _conf, String _op, boolean _post);
    Argument endCalculate(ExecutableCode _conf, Argument _right);

    Argument endCalculate(ExecutableCode _conf, boolean _post, Argument _stored, Argument _right);


    void calculateSetting(
            IdMap<RendDynOperationNode,ArgumentsPair> _nodes, Configuration _conf, Argument _right);

    void calculateCompoundSetting(
            IdMap<RendDynOperationNode,ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right);

    void calculateSemiSetting(
            IdMap<RendDynOperationNode,ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post);
    Argument endCalculate(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, Configuration _conf, Argument _right);

    Argument endCalculate(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right);

}
