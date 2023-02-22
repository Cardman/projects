package code.gui.events;

import code.gui.AbsTxtComponent;

public final class DefValidateText implements AfterValidateText {
    @Override
    public void act(AbsTxtComponent _compo, String _typed) {
        _compo.setText(_typed);
        _compo.setCaretPosition(_typed.length());
    }
}
