package code.player.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.gui.GroupFrame;
import code.util.CustList;

class SetLanguage implements ActionListener {

    private String language;
    private CustList<GroupFrame> list;

    SetLanguage(String _language, CustList<GroupFrame> _list) {
        language = _language;
        list = _list;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        GroupFrame.changeStaticLanguage(language, list);
    }
}
