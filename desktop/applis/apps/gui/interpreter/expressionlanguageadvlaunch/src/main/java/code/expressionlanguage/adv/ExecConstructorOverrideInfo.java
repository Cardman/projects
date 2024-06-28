package code.expressionlanguage.adv;

import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.structs.ConstructorMetaInfo;

public final class ExecConstructorOverrideInfo {
    private final ConstructorMetaInfo metaInfo;
    private final ExecOverrideInfo overrideInfo;

    public ExecConstructorOverrideInfo(ConstructorMetaInfo _m, ExecOverrideInfo _o) {
        this.metaInfo = _m;
        this.overrideInfo = _o;
    }

    public ConstructorMetaInfo getMetaInfo() {
        return metaInfo;
    }

    public ExecOverrideInfo getOverrideInfo() {
        return overrideInfo;
    }
}
