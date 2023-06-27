package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public interface AbsCallContraints {

    boolean match(AbsCallContraints _call);
    boolean match(ContextEl _context, AbstractPageEl _call);


    String keyStr();
    String valueStr();
}
