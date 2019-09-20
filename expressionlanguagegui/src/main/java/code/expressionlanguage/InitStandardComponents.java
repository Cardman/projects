package code.expressionlanguage;

import code.util.StringList;

public final class InitStandardComponents implements Runnable {
    private GuiContextEl context;
    private StringList mainArgs;

    public InitStandardComponents(GuiContextEl _context,
                                  StringList _mainArgs) {
        context = _context;
        mainArgs = _mainArgs;
    }
    @Override
    public void run() {
        context.initApplicationParts(mainArgs);
    }
}
