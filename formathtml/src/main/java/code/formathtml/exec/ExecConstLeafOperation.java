package code.formathtml.exec;

import code.expressionlanguage.opers.ConstLeafOperation;
import code.expressionlanguage.opers.exec.Operable;

public abstract class ExecConstLeafOperation extends ExecLeafOperation implements Operable {

    ExecConstLeafOperation(ConstLeafOperation _l) {
        super(_l);
    }

}
