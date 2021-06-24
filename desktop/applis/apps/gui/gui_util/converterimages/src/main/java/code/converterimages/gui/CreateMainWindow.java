package code.converterimages.gui;

import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

import java.awt.image.BufferedImage;

public final class CreateMainWindow implements Runnable {
    private final AbstractProgramInfos list;
    private String lg;
    private StringMap<Object> args;

    public CreateMainWindow(String _lg, StringMap<Object> _args, AbstractProgramInfos _list) {
        lg = _lg;
        args = _args;
        list = _list;
    }
    @Override
    public void run() {
        MainWindow mainWindow_ = new MainWindow(lg, list);
        if (!args.isEmpty()) {
            if (args.firstValue() instanceof AbstractImage) {
                mainWindow_.readOneImageArg(args.firstKey());
            } else {
                mainWindow_.writeOneImageArg(args.firstKey());
            }
        }
    }
}
