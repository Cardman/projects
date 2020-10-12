package code.minirts;

import code.gui.GroupFrame;
import code.util.CustList;

public class CreateMainWindow implements Runnable {
    private final CustList<GroupFrame> list;
    private String lg;

    public CreateMainWindow(String _lg, CustList<GroupFrame> _list) {
        lg = _lg;
        list = _list;
    }

    @Override
    public void run() {
        new MainWindow(lg, list);
    }
}
