package code.expressionlanguage.opers.exec;

import code.expressionlanguage.ExecutableCode;

public interface DirectExecCalculableOperation extends ExecOperable {

    void calculate(ExecutableCode _conf);
}
