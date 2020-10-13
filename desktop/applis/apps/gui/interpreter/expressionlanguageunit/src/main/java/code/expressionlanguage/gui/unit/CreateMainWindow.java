package code.expressionlanguage.gui.unit;

import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class CreateMainWindow implements Runnable {
    private final AbstractProgramInfos list;
    private String language;
    private StringMap<Object> args;

    public CreateMainWindow(String _language, StringMap<Object> _args, AbstractProgramInfos _list) {
        language = _language;
        args = _args;
        list = _list;
    }

    @Override
    public void run() {
        MainWindow window_ = new MainWindow(language, list);
        if (!args.isEmpty()) {
            window_.launchFileConf(args.firstKey());
        }
    }
}
