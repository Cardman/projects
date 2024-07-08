package code.expressionlanguage.guicompos;

import code.gui.AbsButton;
import code.gui.CdmFactory;
import code.gui.initialize.*;
import code.util.StringList;

public final class CreateMainWindowFull implements Runnable {
    private final CdmFactory list;
    private final String language;
    private final StringList args;
    private final AbstractProgramInfos programInfos;
    private final AbsButton mainButton;
    private WindowFull window;

    public CreateMainWindowFull(String _language, StringList _args, CdmFactory _list, AbstractProgramInfos _infos, AbsButton _main) {
        language = _language;
        args = _args;
        list = _list;
        programInfos = _infos;
        mainButton = _main;
    }

    @Override
    public void run() {
        window = new WindowFull(language, list, programInfos, mainButton);
        WindowFull window_ = window;
        if (!args.isEmpty()) {
            window_.launchFileConf(args.first(),false);
        }
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

    public WindowFull getWindow() {
        return window;
    }
}
