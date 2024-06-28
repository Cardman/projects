package code.converterimages.gui;

import code.gui.initialize.*;

public final class CreateMainWindowConverter implements Runnable {
    private final AbstractProgramInfos list;
    private final String lg;
    private WindowConverter window;

    public CreateMainWindowConverter(String _lg, AbstractProgramInfos _list) {
        lg = _lg;
        list = _list;
    }
    @Override
    public void run() {
        window = new WindowConverter(lg, list);
//        WindowConverter mainWindow_ = window;
//        if (!args.isEmpty()) {
//            if (args.firstValue() == BoolVal.TRUE) {
//                mainWindow_.readOneImageArg(args.firstKey());
//            } else {
//                mainWindow_.writeOneImageArg(args.firstKey());
//            }
//        }
    }

    public WindowConverter getWindow() {
        return window;
    }
}
