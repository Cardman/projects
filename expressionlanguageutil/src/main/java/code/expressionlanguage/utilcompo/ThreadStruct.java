package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ThreadStruct implements Struct {

    private Thread thread;

    public ThreadStruct(Thread _thread) {
        thread = _thread;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getAliasThread();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return equals(_other);
    }

    @Override
    public boolean equals(Object _other) {
        if (!(_other instanceof ThreadStruct)) {
            return false;
        }
        return thread == ((ThreadStruct)_other).thread;
    }

    @Override
    public int hashCode() {
        return thread.hashCode();
    }
}
