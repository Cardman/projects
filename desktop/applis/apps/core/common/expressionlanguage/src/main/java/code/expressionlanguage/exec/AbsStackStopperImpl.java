package code.expressionlanguage.exec;

import code.expressionlanguage.exec.dbg.AbsLogDbg;

public abstract class AbsStackStopperImpl implements AbsStackStopper {
    private final AbsLogDbg logger;
    protected AbsStackStopperImpl(AbsLogDbg _log) {
        logger = _log;
    }

    public AbsLogDbg getLogger() {
        return logger;
    }
}
