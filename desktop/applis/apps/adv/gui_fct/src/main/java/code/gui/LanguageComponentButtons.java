package code.gui;

import code.gui.events.AbsActionListenerAct;
import code.gui.events.LanguageChoiceButton;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;

public final class LanguageComponentButtons {
    private final CustList<AbsButton> groupe = new CustList<AbsButton>();
    private final AbsPanel panel;
    private final AbsActionListenerAct guard;
    public LanguageComponentButtons(AbstractProgramInfos _frameFactory, AbsActionListenerAct _act) {
        panel = _frameFactory.getCompoFactory().newGrid(0,1);
        guard = _act;
    }
    public void init(AbstractProgramInfos _frameFactory, LanguageDialogButtons _window, AbsChangeLanguage _frame) {
        getGroupe().clear();
        panel.removeAll();
        for (String l: _frameFactory.getLanguages()) {
            AbsButton radio_ = _frameFactory.getCompoFactory().newPlainButton(_frameFactory.getDisplayLanguages().getVal(l));
            radio_.addActionListener(guard,new LanguageChoiceButton(l, _window,_frame));
            getGroupe().add(radio_);
            panel.add(radio_);
        }
    }
    public void enableButtons(boolean _value) {
        for (AbsButton b: getGroupe()) {
            b.setEnabled(_value);
        }
    }

    public CustList<AbsButton> getGroupe() {
        return groupe;
    }

    public AbsPanel getPanel() {
        return panel;
    }
}
