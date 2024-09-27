package code.minirts;

import code.gui.LanguagesButtonsPair;
import code.gui.initialize.AbstractProgramInfos;

public final class CreateMainWindowRts implements Runnable {
    private final AbstractProgramInfos list;
    private final LanguagesButtonsPair pair;
    private final int[][] image;
    private WindowRts windowRts;

    public CreateMainWindowRts(AbstractProgramInfos _list, LanguagesButtonsPair _p, int[][] _icon) {
        list = _list;
        pair = _p;
        image = _icon;
    }

    @Override
    public void run() {
        windowRts = new WindowRts(list, pair, image);
    }

    public WindowRts getWindowRts() {
        return windowRts;
    }
}
