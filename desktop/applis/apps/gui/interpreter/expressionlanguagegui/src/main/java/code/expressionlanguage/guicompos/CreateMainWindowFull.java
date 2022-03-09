package code.expressionlanguage.guicompos;

import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class CreateMainWindowFull implements Runnable {
    private final AbstractProgramInfos list;
    private final String language;
    private final StringList args;

    public CreateMainWindowFull(String _language, StringList _args, AbstractProgramInfos _list) {
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
