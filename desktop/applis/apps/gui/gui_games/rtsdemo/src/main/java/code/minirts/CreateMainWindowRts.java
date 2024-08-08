package code.minirts;

import code.gui.LanguagesButtonsPair;
import code.gui.initialize.AbstractProgramInfos;

public final class CreateMainWindowRts implements Runnable {
    private final AbstractProgramInfos list;
    private final LanguagesButtonsPair pair;
    private WindowRts windowRts;

    public CreateMainWindowRts(AbstractProgramInfos _list, LanguagesButtonsPair _p) {
        list = _list;
        pair = _p;
    }

    @Override
    public void run() {
        windowRts = new WindowRts(list, pair);
    }

    public WindowRts getWindowRts() {
        return windowRts;
    }
}
