package code.expressionlanguage.adv;

import code.gui.CdmFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

public final class CreateWindowCdm implements Runnable {
    private final CdmFactory list;
    private final String language;
    private final StringList args;
    private final AbstractProgramInfos programInfos;
    private WindowCdmEditor window;

    public CreateWindowCdm(String _language, StringList _args, CdmFactory _list,AbstractProgramInfos _infos) {
        language = _language;
        args = _args;
        list = _list;
        programInfos = _infos;
    }

    @Override
    public void run() {
        window = new WindowCdmEditor(language, programInfos, list);
        WindowCdmEditor window_ = window;
        window_.setMainResultNext(new AdvResultContextNext(window_,programInfos,list));
        window_.setResultContextNext(window_.getMainResultNext());
        window_.updateCommentsInit(args);
    }

    public WindowCdmEditor getWindow() {
        return window;
    }
}
