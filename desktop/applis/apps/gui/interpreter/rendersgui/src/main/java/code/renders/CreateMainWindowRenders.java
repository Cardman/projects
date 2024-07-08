package code.renders;

import code.gui.AbsButton;
import code.gui.CdmFactory;
import code.gui.initialize.*;
import code.util.*;

public final class CreateMainWindowRenders implements Runnable {
    private final CdmFactory list;
    private final AbstractProgramInfos programInfos;
    private final String language;

    private final StringList args;
    private final AbsButton mainButton;
    private WindowRenders window;

    public CreateMainWindowRenders(String _language, StringList _args, CdmFactory _list, AbstractProgramInfos _frames, AbsButton _main) {
        language = _language;
        args = _args;
        list = _list;
        programInfos = _frames;
        mainButton = _main;
    }

    @Override
    public void run() {
        window = new WindowRenders(language, list, programInfos, mainButton);
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
