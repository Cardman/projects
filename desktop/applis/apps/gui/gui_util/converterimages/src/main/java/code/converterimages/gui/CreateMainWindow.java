package code.converterimages.gui;

import code.gui.GroupFrame;
import code.util.CustList;
import code.util.StringMap;

import java.awt.image.BufferedImage;

public final class CreateMainWindow implements Runnable {
    private final CustList<GroupFrame> list;
    private String lg;
    private StringMap<Object> args;

    public CreateMainWindow(String _lg, StringMap<Object> _args, CustList<GroupFrame> _list) {
        lg = _lg;
        args = _args;
        list = _list;
    }
    @Override
    public void run() {
        MainWindow mainWindow_ = new MainWindow(lg, list);
        if (!args.isEmpty()) {
            if (args.firstValue() instanceof BufferedImage) {
                mainWindow_.readOneImageArg(args.firstKey());
            } else {
                mainWindow_.writeOneImageArg(args.firstKey());
            }
        }
    }
}
