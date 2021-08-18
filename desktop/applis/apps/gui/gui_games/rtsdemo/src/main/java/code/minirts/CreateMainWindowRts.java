package code.minirts;

import code.gui.initialize.AbstractProgramInfos;

public class CreateMainWindowRts implements Runnable {
    private final AbstractProgramInfos list;
    private String lg;

    public CreateMainWindowRts(String _lg, AbstractProgramInfos _list) {
        lg = _lg;
        list = _list;
    }

    @Override
    public void run() {
        new WindowRts(lg, list);
    }
}
