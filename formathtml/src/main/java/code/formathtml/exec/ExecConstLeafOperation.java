package code.formathtml.exec;

import code.expressionlanguage.opers.ConstLeafOperation;
import code.expressionlanguage.opers.exec.ExecOperable;

public abstract class ExecConstLeafOperation extends ExecLeafOperation implements ExecOperable {

    ExecConstLeafOperation(ConstLeafOperation _l) {
        super(_l);
    }

}
