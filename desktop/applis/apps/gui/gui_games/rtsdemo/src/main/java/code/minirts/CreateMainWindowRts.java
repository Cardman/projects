package code.minirts;

import code.gui.initialize.AbstractProgramInfos;

public final class CreateMainWindowRts implements Runnable {
    private final AbstractProgramInfos list;
    private final String lg;
    private WindowRts windowRts;

    public CreateMainWindowRts(String _lg, AbstractProgramInfos _list) {
        lg = _lg;
        list = _list;
    }

    @Override
    public void run() {
        windowRts = new WindowRts(lg, list);
    }

    public WindowRts getWindowRts() {
        return windowRts;
    }
}
