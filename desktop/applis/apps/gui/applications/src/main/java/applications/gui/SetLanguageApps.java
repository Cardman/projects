package applications.gui;

import code.gui.events.AbsActionListener;
import code.gui.GroupFrame;
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
        GroupFrame.changeStaticLanguage(language, list);
    }
}
