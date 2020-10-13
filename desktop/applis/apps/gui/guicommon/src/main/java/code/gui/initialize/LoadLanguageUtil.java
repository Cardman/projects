package code.gui.initialize;

import code.gui.LoadLanguage;
import code.gui.SoftApplicationCore;
import code.gui.ThreadInvoker;

public final class LoadLanguageUtil {
    private LoadLanguageUtil() {
    }
    public static void loadLaungage(SoftApplicationCore _soft, String _folder, String[] _args) {
        ThreadInvoker.invokeNow(new LoadLanguage(_soft, _folder, _args, null));
    }
}
