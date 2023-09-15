package code.expressionlanguage.exec;

import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public class CoreCheckedExecOperationNodeInfos {
    private final ExecFormattedRootBlock declaring;
    private final Struct instance;
    private final Cache cache;

    public CoreCheckedExecOperationNodeInfos(ExecFormattedRootBlock _d, Struct _i) {
        this(_d,_i, ArrCheckedExecOperationNodeInfos.cacheMin());
    }

    public CoreCheckedExecOperationNodeInfos(ExecFormattedRootBlock _d, Struct _i, Cache _c) {
        this.declaring = _d;
        this.instance = _i;
        this.cache = _c;
    }

    public ExecFormattedRootBlock getDeclaring() {
        return declaring;
    }

    public Struct getInstance() {
        return instance;
    }

    public Cache getCache() {
        return cache;
    }

}
