package code.renders;

import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;
import code.util.StringMap;

public final class CreateMainWindowRenders implements Runnable {
    private final AbstractProgramInfos list;
    private String language;

    private StringList args;

    public CreateMainWindowRenders(String _language, StringList _args, AbstractProgramInfos _list) {
        language = _language;
        args = _args;
        list = _list;
    }

    @Override
    public void run() {
        WindowRenders mainWindow_ = new WindowRenders(language, list);
        if (!args.isEmpty()) {
            mainWindow_.loadRenderConf(args.first());
        }
    }
}
