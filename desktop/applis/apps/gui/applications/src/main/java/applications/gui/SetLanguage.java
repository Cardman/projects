package applications.gui;

import code.gui.GroupFrame;
import code.gui.initialize.AbstractProgramInfos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SetLanguage implements ActionListener {

    private String language;
    private AbstractProgramInfos list;

    SetLanguage(String _language, AbstractProgramInfos _list) {
        language = _language;
        list = _list;
    }

    @Override
    public void actionPerformed(ActionEvent _arg) {
        GroupFrame.changeStaticLanguage(language, list);
    }
}
