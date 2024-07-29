package code.renders;

import code.gui.CdmFactory;
import code.gui.LanguagesButtonsPair;
import code.gui.initialize.*;
import code.util.*;

public final class CreateMainWindowRenders implements Runnable {
    private final CdmFactory list;
    private final AbstractProgramInfos programInfos;
    private final String language;

    private final StringList args;
    private final LanguagesButtonsPair pair;
    private WindowRenders window;

    public CreateMainWindowRenders(String _language, StringList _args, CdmFactory _list, AbstractProgramInfos _frames, LanguagesButtonsPair _p) {
        language = _language;
        args = _args;
        list = _list;
        programInfos = _frames;
        pair = _p;
    }

    @Override
    public void run() {
        window = new WindowRenders(language, list, programInfos, pair);
        WindowRenders mainWindow_ = window;
        if (!args.isEmpty()) {
            mainWindow_.loadRenderConf(args.first());
        }
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

    public WindowRenders getWindow() {
        return window;
    }
}
