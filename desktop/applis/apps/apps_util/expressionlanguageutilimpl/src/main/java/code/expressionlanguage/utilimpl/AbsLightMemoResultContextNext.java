package code.expressionlanguage.utilimpl;

import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.utilcompo.AbsLightResultContextNext;
import code.threads.AbstractAtomicBoolean;

public interface AbsLightMemoResultContextNext extends AbsLightResultContextNext {
    AbsLightContextGenerator generate(AbstractAtomicBoolean _at);
}
