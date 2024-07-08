package code.minirts;

import code.gui.AbsButton;
import code.gui.initialize.AbstractProgramInfos;

public final class CreateMainWindowRts implements Runnable {
    private final AbstractProgramInfos list;
    private final String lg;
    private final AbsButton mainButton;
    private WindowRts windowRts;

    public CreateMainWindowRts(String _lg, AbstractProgramInfos _list, AbsButton _main) {
        lg = _lg;
        list = _list;
        mainButton = _main;
    }

    @Override
    public void run() {
        windowRts = new WindowRts(lg, list, mainButton);
    }

    public WindowRts getWindowRts() {
        return windowRts;
    }
}
