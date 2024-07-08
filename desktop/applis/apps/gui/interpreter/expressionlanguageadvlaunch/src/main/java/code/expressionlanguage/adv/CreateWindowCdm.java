package code.expressionlanguage.adv;

import code.gui.AbsButton;
import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class CreateWindowCdm implements Runnable {
    private final CdmFactory list;
    private final String language;
    private final StringList args;
    private final AbstractProgramInfos programInfos;
    private final AbsButton mainButton;
    private WindowCdmEditor window;

    public CreateWindowCdm(String _language, StringList _args, CdmFactory _list, AbstractProgramInfos _infos, AbsButton _ma) {
        language = _language;
        args = _args;
        list = _list;
        programInfos = _infos;
        mainButton = _ma;
    }

    @Override
    public void run() {
        window = new WindowCdmEditor(language, programInfos, list, mainButton);
        WindowCdmEditor window_ = window;
        window_.setMainResultNext(new AdvResultContextNext(window_,programInfos,list));
        window_.setResultContextNext(window_.getMainResultNext());
        window_.updateCommentsInit(args);
    }

    public WindowCdmEditor getWindow() {
        return window;
    }
}
