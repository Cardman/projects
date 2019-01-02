package code.formathtml.exec;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.exec.ExecOperable;

public interface DirectExecCalculableOperation extends ExecOperable {

    void calculate(ExecutableCode _conf);
}
