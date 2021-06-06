package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.util.Replacement;
import code.util.core.StringUtil;

public final class ReplacementStruct extends WithoutParentStruct {

    private final Replacement instance;

    public ReplacementStruct(Replacement _instance) {
        instance = _instance;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getCharSeq().getAliasReplacement();
    }

    public Replacement getInstance() {
        return instance;
    }


    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ReplacementStruct)) {
            return false;
        }
        ReplacementStruct other_ = NumParsers.getReplacement(_other);
        if (!StringUtil.quickEq(instance.getOldString(),other_.instance.getOldString())) {
            return false;
        }
        return StringUtil.quickEq(instance.getNewString(),other_.instance.getNewString());
    }

    @Override
    public long randCode() {
        long r_ = NumParsers.mergeRandCode(1,NumParsers.randCode(instance.getOldString()));
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(instance.getNewString()));
        return r_;
    }
}
