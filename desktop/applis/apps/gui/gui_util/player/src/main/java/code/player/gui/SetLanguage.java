package code.player.gui;

import code.gui.FrameUtil;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbstractProgramInfos;

class SetLanguage implements AbsActionListener {

    private String language;
    private AbstractProgramInfos list;

    SetLanguage(String _language, AbstractProgramInfos _list) {
        language = _language;
        list = _list;
    }

    @Override
    public void action() {
        FrameUtil.changeStaticLanguage(language, list);
    }
}
