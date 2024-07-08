package code.player.gui;

import code.gui.AbsButton;
import code.gui.initialize.*;
import code.util.*;

public final class CreateMainWindowPlayer implements Runnable {
    private final AbstractProgramInfos list;
    private final String lg;
    private final StringList args;
    private final AbsButton mainButton;
    private WindowPlayer window;

    public CreateMainWindowPlayer(String _lg, StringList _args, AbstractProgramInfos _list, AbsButton _main) {
        lg = _lg;
        args = _args;
        list = _list;
        mainButton = _main;
    }

    @Override
    public void run() {
        window = new WindowPlayer(lg, list, mainButton);
        WindowPlayer window_ = window;
        if (!args.isEmpty()) {
            window_.loadList(args.first());
        }
    }

    public WindowPlayer getWindow() {
        return window;
    }
}
