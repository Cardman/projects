package code.player.gui;

import code.gui.GuiBaseUtil;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;

class SetLanguage implements AbsActionListener {

    private final String language;
    private final AbstractProgramInfos list;

    SetLanguage(String _language, AbstractProgramInfos _list) {
        language = _language;
        list = _list;
    }

    @Override
    public void action() {
        GuiBaseUtil.changeStaticLanguage(language, list, list.getCommon());
    }
}
