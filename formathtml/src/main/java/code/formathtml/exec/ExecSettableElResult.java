package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.exec.ExecOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public interface ExecSettableElResult extends ExecOperable {

    boolean resultCanBeSet();
    ClassArgumentMatching getResultClass();

    void calculateSetting(
            ExecutableCode _conf, Argument _right);

    void calculateCompoundSetting(
            ExecutableCode _conf, String _op, Argument _right);

    void calculateSemiSetting(
            ExecutableCode _conf, String _op, boolean _post);
    Argument endCalculate(ExecutableCode _conf, Argument _right);
 
    Argument endCalculate(ExecutableCode _conf, boolean _post, Argument _stored, Argument _right);

}
