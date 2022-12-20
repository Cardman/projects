package code.expressionlanguage.guicompos;

import code.gui.CdmFactory;
import code.gui.initialize.*;
import code.util.StringList;

public final class CreateMainWindowFull implements Runnable {
    private final CdmFactory list;
    private final String language;
    private final StringList args;

    public CreateMainWindowFull(String _language, StringList _args, CdmFactory _list) {
        language = _language;
        args = _args;
        list = _list;
    }

    @Override
    public void run() {
        WindowFull window_ = new WindowFull(language, list);
        if (!args.isEmpty()) {
            window_.launchFileConf(args.first(),false);
        }
    }
}
