package pokecards.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.GroupFrame;

class SetLanguage extends MouseAdapter {

    private String language;

    SetLanguage(String _language) {
        language = _language;
    }

    @Override
    public void mouseReleased(MouseEvent _arg) {
        GroupFrame.changeStaticLanguage(language);
    }
}
