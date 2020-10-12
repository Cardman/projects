package code.expressionlanguage.gui.unit;

import code.gui.GroupFrame;
import code.util.CustList;
import code.util.StringMap;

public final class CreateMainWindow implements Runnable {
    private final CustList<GroupFrame> list;
    private String language;
    private StringMap<Object> args;

    public CreateMainWindow(String _language, StringMap<Object> _args, CustList<GroupFrame> _list) {
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
