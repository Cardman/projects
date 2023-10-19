package code.expressionlanguage.guicompos;

import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.CustInitializer;
import code.threads.AbstractAtomicLong;

public class GuiInitializer extends CustInitializer {
    private final WindowSetStruct windows;

    public GuiInitializer(AbstractAtomicLong _value, AbstractInterceptor _concurrent) {
        super(_value,_concurrent);
        windows = new WindowSetStruct(false,_concurrent);
    }

    public WindowSetStruct getWindows() {
        return windows;
    }
}
