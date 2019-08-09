package code.expressionlanguage;

import code.util.StringList;

public final class InitStandardComponents implements Runnable {
    private GuiContextEl context;
    private StringList mainArgs;
    private String locale;
    public InitStandardComponents(GuiContextEl _context,
                                  StringList _mainArgs,
                                  String _locale) {
        context = _context;
        mainArgs = _mainArgs;
        locale = _locale;
    }
    @Override
    public void run() {
        context.initApplicationParts(locale,mainArgs);
    }
}
