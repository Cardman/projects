package code.gui.events;

import code.gui.AbsTxtComponent;
import code.util.CustList;
import code.util.StringList;

public final class DefValEventSample implements AfterValidateText,AbsActionListener {
    @Override
    public void act(AbsTxtComponent _compo, String _typed) {
        _compo.setText(_typed);
        _compo.setCaretPosition(_typed.length());
    }
    @Override
    public StringList filter(String _look, CustList<String> _dict) {
        return DefValidateText.filt(_look, _dict);
    }

    @Override
    public void action() {
        filter("",new CustList<String>());
    }
}
