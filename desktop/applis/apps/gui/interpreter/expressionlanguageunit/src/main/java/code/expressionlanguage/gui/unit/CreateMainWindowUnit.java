package code.expressionlanguage.gui.unit;

import code.gui.CdmFactory;
import code.gui.LanguagesButtonsPair;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class CreateMainWindowUnit implements Runnable {
    private final CdmFactory list;
    private final AbstractProgramInfos programInfos;
    private final StringList args;
    private final LanguagesButtonsPair pair;
    private WindowUnit window;

    public CreateMainWindowUnit(StringList _args, CdmFactory _list, AbstractProgramInfos _frames, LanguagesButtonsPair _p) {
        args = _args;
        list = _list;
        programInfos = _frames;
        pair = _p;
    }

    @Override
    public void run() {
        window = new WindowUnit(list, programInfos, pair);
        WindowUnit window_ = window;
        if (!args.isEmpty()) {
            window_.launchFileConf(args.first(), window_);
        }
    }

    public WindowUnit getWindow() {
        return window;
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }
}
