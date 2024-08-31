package code.player.gui;

import code.gui.LanguagesButtonsPair;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class CreateMainWindowPlayer implements Runnable {
    private final AbstractProgramInfos list;
    private final String lg;
    private final StringList args;
    private final LanguagesButtonsPair pair;
    private final AbstractImage image;
    private WindowPlayer window;

    public CreateMainWindowPlayer(String _lg, StringList _args, AbstractProgramInfos _list, LanguagesButtonsPair _p, AbstractImage _icon) {
        lg = _lg;
        args = _args;
        list = _list;
        pair = _p;
        image = _icon;
    }

    @Override
    public void run() {
        window = new WindowPlayer(lg, list, pair, image);
        WindowPlayer window_ = window;
        if (!args.isEmpty()) {
            window_.loadList(args.first());
        }
    }

    public WindowPlayer getWindow() {
        return window;
    }
}
