package code.expressionlanguage.utilcompo;

import code.expressionlanguage.fwd.AbsContextGenerator;
import code.threads.AbstractAtomicBoolean;

public interface AbsAdvContextGenerator extends AbsContextGenerator {
    AbstractAtomicBoolean getStop();
}
