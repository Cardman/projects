package applications.gui;

import code.gui.GroupFrame;
import code.util.CustList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SetLanguage implements ActionListener {

    private String language;
    private CustList<GroupFrame> list;

    SetLanguage(String _language, CustList<GroupFrame> _list) {
        language = _language;
        list = _list;
    }

    @Override
    public void actionPerformed(ActionEvent _arg) {
        GroupFrame.changeStaticLanguage(language, list);
    }
}
