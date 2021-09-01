package code.converterimages.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;
import code.util.core.BoolVal;

public final class CreateMainWindowConverter implements Runnable {
    private final AbstractProgramInfos list;
    private String lg;
    private StringMap<BoolVal> args;

    public CreateMainWindowConverter(String _lg, StringMap<BoolVal> _args, AbstractProgramInfos _list) {
        lg = _lg;
        args = _args;
        list = _list;
    }
    @Override
    public void run() {
        WindowConverter mainWindow_ = new WindowConverter(lg, list);
        if (!args.isEmpty()) {
            if (args.firstValue() == BoolVal.TRUE) {
                mainWindow_.readOneImageArg(args.firstKey());
            } else {
                mainWindow_.writeOneImageArg(args.firstKey());
            }
        }
    }
}
