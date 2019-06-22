package code.formathtml.exec;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.exec.Operable;

public interface DirectExecCalculableOperation extends Operable {

    void calculate(ExecutableCode _conf);
}
