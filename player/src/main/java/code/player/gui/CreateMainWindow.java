package code.player.gui;

import code.util.StringMap;

public class CreateMainWindow implements Runnable {
    private String lg;
    private StringMap<Object> args;
    public CreateMainWindow(String _lg, StringMap<Object> _args) {
        lg = _lg;
        args = _args;
    }

    @Override
    public void run() {
        MainWindow window_ = new MainWindow(lg);
        if (!args.isEmpty()) {
            window_.loadList(args.firstKey());
        }
    }
}
