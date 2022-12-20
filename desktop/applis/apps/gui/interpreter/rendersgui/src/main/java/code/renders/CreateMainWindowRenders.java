package code.renders;

import code.gui.CdmFactory;
import code.gui.initialize.*;
import code.util.*;

public final class CreateMainWindowRenders implements Runnable {
    private final CdmFactory list;
    private final AbstractProgramInfos programInfos;
    private String language;

    private StringList args;

    public CreateMainWindowRenders(String _language, StringList _args, CdmFactory _list, AbstractProgramInfos _frames) {
        language = _language;
        args = _args;
        list = _list;
        programInfos = _frames;
    }

    @Override
    public void run() {
        WindowRenders mainWindow_ = new WindowRenders(language, list, programInfos);
        if (!args.isEmpty()) {
            mainWindow_.loadRenderConf(args.first());
        }
    }
}
