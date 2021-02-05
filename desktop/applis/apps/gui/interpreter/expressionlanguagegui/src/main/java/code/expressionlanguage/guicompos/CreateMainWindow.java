package code.expressionlanguage.guicompos;

import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class CreateMainWindow implements Runnable {
    private final AbstractProgramInfos list;
    private final GuiFactroy guiFactroy;
    private final String language;
    private final StringMap<Object> args;

    public CreateMainWindow(String _language, StringMap<Object> _args, AbstractProgramInfos _list, GuiFactroy _guiFactroy) {
        language = _language;
        args = _args;
        list = _list;
        guiFactroy = _guiFactroy;
    }

    @Override
    public void run() {
        MainWindow window_ = new MainWindow(language, list, guiFactroy);
        if (!args.isEmpty()) {
            window_.launchFileConf(args.firstKey(),false);
        }
    }
}
