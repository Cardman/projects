package code.expressionlanguage.gui.unit;

import code.gui.CdmFactory;
import code.gui.initialize.*;
import code.util.*;

public final class CreateMainWindowUnit implements Runnable {
    private final CdmFactory list;
    private final AbstractProgramInfos programInfos;
    private String language;
    private StringList args;

    public CreateMainWindowUnit(String _language, StringList _args, CdmFactory _list, AbstractProgramInfos _frames) {
        language = _language;
        args = _args;
        list = _list;
        programInfos = _frames;
    }

    @Override
    public void run() {
        WindowUnit window_ = new WindowUnit(language, list,programInfos);
        if (!args.isEmpty()) {
            window_.launchFileConf(args.first(), window_);
        }
    }
}
