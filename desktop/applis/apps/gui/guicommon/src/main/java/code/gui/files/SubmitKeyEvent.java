package code.gui.files;

import code.gui.AbsTextField;
import code.gui.AbsTxtComponent;
import code.gui.events.AbsActionListener;
import code.gui.events.AfterValidateText;
import code.gui.events.DefValidateText;
import code.util.CustList;
import code.util.StringList;

public class SubmitKeyEvent implements AbsActionListener,AfterValidateText {

    private final SingleFileSelection dialog;
    private final AbsTextField txt;

    public SubmitKeyEvent(SingleFileSelection _dialog, AbsTextField _t) {
        dialog = _dialog;
        txt = _t;
    }

    @Override
    public void act(AbsTxtComponent _compo, String _typed) {
        txt.setText(_typed);
        txt.setCaretPosition(_typed.length());
    }

    @Override
    public void action() {
        dialog.submitIfVisible();
    }

    @Override
    public StringList filter(String _look, CustList<String> _dict) {
        return DefValidateText.filt(_look, _dict);
    }

}
