package pokecards.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.GroupFrame;

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
