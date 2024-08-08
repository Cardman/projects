package code.expressionlanguage.adv;

import code.gui.CdmFactory;
import code.gui.LanguagesButtonsPair;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class CreateWindowCdm implements Runnable {
    private final CdmFactory list;
    private final StringList args;
    private final AbstractProgramInfos programInfos;
    private final LanguagesButtonsPair pair;
    private WindowCdmEditor window;

    public CreateWindowCdm(StringList _args, CdmFactory _list, AbstractProgramInfos _infos, LanguagesButtonsPair _p) {
        args = _args;
        list = _list;
        programInfos = _infos;
        pair = _p;
    }

    @Override
    public void run() {
        window = new WindowCdmEditor(programInfos, list, pair);
        WindowCdmEditor window_ = window;
        window_.setMainResultNext(new AdvResultContextNext(window_,programInfos,list));
        window_.setResultContextNext(window_.getMainResultNext());
        window_.updateCommentsInit(args);
    }

    public WindowCdmEditor getWindow() {
        return window;
    }
}
