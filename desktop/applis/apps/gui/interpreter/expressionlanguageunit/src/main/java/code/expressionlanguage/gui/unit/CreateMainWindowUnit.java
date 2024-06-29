package code.expressionlanguage.gui.unit;

import code.gui.CdmFactory;
import code.gui.initialize.*;
import code.util.*;

public final class CreateMainWindowUnit implements Runnable {
    private final CdmFactory list;
    private final AbstractProgramInfos programInfos;
    private final String language;
    private final StringList args;
    private WindowUnit window;

    public CreateMainWindowUnit(String _language, StringList _args, CdmFactory _list, AbstractProgramInfos _frames) {
        language = _language;
        args = _args;
        list = _list;
        programInfos = _frames;
    }

    @Override
    public void run() {
        window = new WindowUnit(language, list, programInfos);
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
