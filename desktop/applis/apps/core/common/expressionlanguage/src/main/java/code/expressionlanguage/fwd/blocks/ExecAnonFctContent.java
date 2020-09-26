package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.util.AnaCache;
import code.expressionlanguage.analyze.variables.AnaNamedLocalVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLoopVariable;
import code.expressionlanguage.exec.util.CacheInfo;
import code.expressionlanguage.exec.util.NameAndType;

public final class ExecAnonFctContent {
    private final CacheInfo cacheInfo = new CacheInfo();

    public ExecAnonFctContent(AnaAnonFctContent _cont) {
        AnaCache cache_ = _cont.getCache();
        for (AnaNamedLocalVariable e: cache_.getLocalVariables()) {
            cacheInfo.getCacheLocalNames().add(new NameAndType(e.getName(),e.getLocalVariable().getClassName()));
        }
        for (AnaNamedLoopVariable e: cache_.getLoopVariables()) {
            cacheInfo.getCacheLoopNames().add(new NameAndType(e.getName(),e.getLocalVariable().getIndexClassName()));
        }
    }
    public CacheInfo getCacheInfo() {
        return cacheInfo;
    }
}
