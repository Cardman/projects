package code.gui.initialize;

import code.gui.*;

public final class LoadLanguageUtil {
    private LoadLanguageUtil() {
    }
    public static void loadLaungage(SoftApplicationCore _soft, String _folder, String[] _args) {
        _soft.getFrames().getCompoFactory().invokeNow(new LoadLanguage(_soft, _folder, _args, null));
    }

}
