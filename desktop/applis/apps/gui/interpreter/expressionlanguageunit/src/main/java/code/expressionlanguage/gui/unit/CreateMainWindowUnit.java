package code.expressionlanguage.gui.unit;

import code.gui.CdmFactory;
import code.gui.initialize.*;
import code.util.*;

public final class CreateMainWindowUnit implements Runnable {
    private final CdmFactory list;
    private String language;
    private StringList args;

    public CreateMainWindowUnit(String _language, StringList _args, CdmFactory _list) {
        language = _language;
        args = _args;
        list = _list;
    }

    @Override
    public void run() {
        WindowUnit window_ = new WindowUnit(language, list);
        if (!args.isEmpty()) {
            window_.launchFileConf(args.first(), window_);
        }
    }
}
