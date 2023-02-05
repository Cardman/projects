package code.player.gui;

import code.gui.GuiBaseUtil;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;

class SetLanguage implements AbsActionListener {

    private String language;
    private AbstractProgramInfos list;

    SetLanguage(String _language, AbstractProgramInfos _list) {
        language = _language;
        list = _list;
    }

    @Override
    public void action() {
        GuiBaseUtil.changeStaticLanguage(language, list, MessGuiGr.ms());
    }
}
