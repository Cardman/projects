package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.util.CacheInfo;
import code.expressionlanguage.functionid.MethodModifier;

public interface WithCache {
    CacheInfo getCacheInfo();
    MethodModifier getModifier();
}
