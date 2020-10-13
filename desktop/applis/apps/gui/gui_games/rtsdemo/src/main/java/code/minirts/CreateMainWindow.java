package code.minirts;

import code.gui.initialize.AbstractProgramInfos;

public class CreateMainWindow implements Runnable {
    private final AbstractProgramInfos list;
    private String lg;

    public CreateMainWindow(String _lg, AbstractProgramInfos _list) {
        lg = _lg;
        list = _list;
    }

    @Override
    public void run() {
        new MainWindow(lg, list);
    }
}
