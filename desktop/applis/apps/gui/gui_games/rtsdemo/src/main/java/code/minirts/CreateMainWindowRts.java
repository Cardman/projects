package code.minirts;

import code.gui.LanguagesButtonsPair;
import code.gui.initialize.AbstractProgramInfos;

public final class CreateMainWindowRts implements Runnable {
    private final AbstractProgramInfos list;
    private final String lg;
    private final LanguagesButtonsPair pair;
    private WindowRts windowRts;

    public CreateMainWindowRts(String _lg, AbstractProgramInfos _list, LanguagesButtonsPair _p) {
        lg = _lg;
        list = _list;
        pair = _p;
    }

    @Override
    public void run() {
        windowRts = new WindowRts(lg, list, pair);
    }

    public WindowRts getWindowRts() {
        return windowRts;
    }
}
