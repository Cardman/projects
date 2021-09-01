package code.expressionlanguage.guicompos;

import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;
import code.util.StringMap;

public final class CreateMainWindowFull implements Runnable {
    private final AbstractProgramInfos list;
    private final GuiFactroy guiFactroy;
    private final String language;
    private final StringList args;

    public CreateMainWindowFull(String _language, StringList _args, AbstractProgramInfos _list, GuiFactroy _guiFactroy) {
        language = _language;
        args = _args;
        list = _list;
        guiFactroy = _guiFactroy;
    }

    @Override
    public void run() {
        WindowFull window_ = new WindowFull(language, list, guiFactroy);
        if (!args.isEmpty()) {
            window_.launchFileConf(args.first(),false);
        }
    }
}
