package code.expressionlanguage.utilimpl;

import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.threads.AbstractAtomicBoolean;

public interface AbsLightMemoResultContextNext {
    AbsLightContextGenerator generate(AbstractAtomicBoolean _at);
}
