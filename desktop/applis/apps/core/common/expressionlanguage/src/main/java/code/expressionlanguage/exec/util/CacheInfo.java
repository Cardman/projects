package code.expressionlanguage.exec.util;

import code.util.CustList;

public final class CacheInfo {
    private final CustList<NameAndType> cacheLocalNames = new CustList<NameAndType>();
    private final CustList<NameAndType> cacheLoopNames = new CustList<NameAndType>();

    public CustList<NameAndType> getCacheLoopNames() {
        return cacheLoopNames;
    }

    public CustList<NameAndType> getCacheLocalNames() {
        return cacheLocalNames;
    }
}
