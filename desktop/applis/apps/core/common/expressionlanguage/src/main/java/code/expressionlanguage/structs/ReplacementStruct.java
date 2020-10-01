package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.util.Replacement;
import code.util.StringList;

public final class ReplacementStruct extends WithoutParentStruct implements Struct {

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
        if (!StringList.quickEq(instance.getOldString(),other_.instance.getOldString())) {
            return false;
        }
        return StringList.quickEq(instance.getNewString(),other_.instance.getNewString());
    }

}
