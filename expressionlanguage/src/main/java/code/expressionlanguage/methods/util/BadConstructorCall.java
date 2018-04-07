package code.expressionlanguage.methods.util;

import code.sml.RowCol;
import code.util.StringList;

public final class BadConstructorCall extends FoundErrorInterpret {

    private RowCol localOffset;

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,localOffset.display());
    }

    public RowCol getLocalOffset() {
        return localOffset;
    }

    public void setLocalOffset(RowCol _localOffset) {
        localOffset = _localOffset;
    }

}
