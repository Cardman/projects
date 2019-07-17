package code.formathtml.exec;

import code.expressionlanguage.opers.IdFctOperation;
import code.expressionlanguage.opers.util.ClassMethodId;

public final class RendIdFctOperation extends RendLeafOperation {

    private ClassMethodId method;

    public RendIdFctOperation(IdFctOperation _i) {
        super(_i);
        method = _i.getMethod();
    }

    public ClassMethodId getMethod() {
        return method;
    }
}
