package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ThreadStruct implements Struct {

    private Thread thread;

    private AtomicBoolean ended = new AtomicBoolean();

    public ThreadStruct(Thread _thread) {
        thread = _thread;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
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
        return ((LgNamesUtils)_contextEl.getStandards()).getAliasThread();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ThreadStruct)) {
            return false;
        }
        return thread == ((ThreadStruct)_other).thread;
    }
}
