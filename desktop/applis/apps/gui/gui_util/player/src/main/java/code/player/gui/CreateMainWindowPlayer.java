package code.player.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;
import code.util.StringMap;

public class CreateMainWindowPlayer implements Runnable {
    private final AbstractProgramInfos list;
    private String lg;
    private StringList args;

    public CreateMainWindowPlayer(String _lg, StringList _args, AbstractProgramInfos _list) {
        lg = _lg;
        args = _args;
        list = _list;
    }

    @Override
    public void run() {
        WindowPlayer window_ = new WindowPlayer(lg, list);
        if (!args.isEmpty()) {
            window_.loadList(args.first());
        }
    }
}
