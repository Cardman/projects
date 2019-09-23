package code.converterimages.gui;

import code.util.StringMap;

import java.awt.image.BufferedImage;

public final class CreateMainWindow implements Runnable {
    private String lg;
    private StringMap<Object> args;
    public CreateMainWindow(String _lg, StringMap<Object> _args) {
        lg = _lg;
        args = _args;
    }
    @Override
    public void run() {
        MainWindow mainWindow_ = new MainWindow(lg);
        if (!args.isEmpty()) {
            if (args.firstValue() instanceof BufferedImage) {
                mainWindow_.readOneImageArg(args.firstKey());
            } else {
                mainWindow_.writeOneImageArg(args.firstKey());
            }
        }
    }
}
