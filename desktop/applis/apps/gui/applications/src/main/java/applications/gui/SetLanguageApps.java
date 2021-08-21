package applications.gui;

import code.gui.FrameUtil;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;

class SetLanguageApps implements AbsActionListener {

    private String language;
    private AbstractProgramInfos list;

    SetLanguageApps(String _language, AbstractProgramInfos _list) {
        language = _language;
        list = _list;
    }

    @Override
    public void action() {
        FrameUtil.changeStaticLanguage(language, list);
    }
}
