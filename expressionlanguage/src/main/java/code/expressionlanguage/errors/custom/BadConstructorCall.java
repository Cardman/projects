package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class BadConstructorCall extends FoundErrorInterpret {

    private int localOffset;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),SEP_INFO,Integer.toString(localOffset));
    }

    public int getLocalOffset() {
        return localOffset;
    }

    public void setLocalOffset(int _localOffset) {
        localOffset = _localOffset;
    }

}
