package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ThreadStruct extends WithoutParentIdStruct implements Struct {

    private Thread thread;

    private AtomicBoolean ended = new AtomicBoolean();

    public ThreadStruct(Thread _thread) {
        thread = _thread;
    }

    public boolean isEnded() {
        return ended.get();
    }

    public void end() {
        ended.set(true);
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesWithNewAliases)_contextEl.getStandards()).getCustAliases().getAliasThread();
    }

}
