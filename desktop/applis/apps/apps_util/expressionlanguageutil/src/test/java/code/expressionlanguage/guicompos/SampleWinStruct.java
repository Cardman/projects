package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.mock.MockWindow;

public final class SampleWinStruct extends WindowStruct {
    public SampleWinStruct() {
        super(new MockWindow());
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return "";
    }
}
