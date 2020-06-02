package applications.gui;

import code.gui.GroupFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SetLanguage implements ActionListener {

    private String language;

    SetLanguage(String _language) {
        language = _language;
    }

    @Override
    public void actionPerformed(ActionEvent _arg) {
        GroupFrame.changeStaticLanguage(language);
    }
}
