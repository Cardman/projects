package code.expressionlanguage.opers.exec;

import code.expressionlanguage.opers.ConstLeafOperation;

public abstract class ExecConstLeafOperation extends ExecLeafOperation implements ExecOperable {

    ExecConstLeafOperation(ConstLeafOperation _l) {
        super(_l);
    }

}
