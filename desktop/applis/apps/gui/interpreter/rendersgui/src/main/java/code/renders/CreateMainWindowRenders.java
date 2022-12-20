package code.renders;

import code.gui.CdmFactory;
import code.gui.initialize.*;
import code.util.*;

public final class CreateMainWindowRenders implements Runnable {
    private final CdmFactory list;
    private String language;

    private StringList args;

    public CreateMainWindowRenders(String _language, StringList _args, CdmFactory _list) {
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
