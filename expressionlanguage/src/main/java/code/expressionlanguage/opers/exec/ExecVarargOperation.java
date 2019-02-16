package code.expressionlanguage.opers.exec;

import code.expressionlanguage.opers.VarargOperable;
import code.expressionlanguage.opers.VarargOperation;

public final class ExecVarargOperation extends ExecConstLeafOperation implements VarargOperable {

    public ExecVarargOperation(VarargOperation _v) {
        super(_v);
    }

}
