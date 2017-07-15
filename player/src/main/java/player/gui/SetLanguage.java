package player.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.gui.GroupFrame;

class SetLanguage implements ActionListener {

    private String language;

    SetLanguage(String _language) {
        language = _language;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        GroupFrame.changeStaticLanguage(language);
    }
}
