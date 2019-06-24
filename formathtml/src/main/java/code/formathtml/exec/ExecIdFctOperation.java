package code.formathtml.exec;

import code.expressionlanguage.opers.IdFctOperation;
import code.expressionlanguage.opers.util.ClassMethodId;

public final class ExecIdFctOperation extends ExecLeafOperation {

    private ClassMethodId method;

    public ExecIdFctOperation(IdFctOperation _i) {
        super(_i);
        method = _i.getMethod();
    }

    public ClassMethodId getMethod() {
        return method;
    }
}
