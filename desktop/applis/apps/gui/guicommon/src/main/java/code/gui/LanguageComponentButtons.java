package code.gui;

import code.gui.events.LanguageChoiceButton;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;

public final class LanguageComponentButtons {
    private final CustList<AbsButton> groupe = new CustList<AbsButton>();
    private final AbsPanel panel;
    public LanguageComponentButtons(AbstractProgramInfos _frameFactory) {
        panel = _frameFactory.getCompoFactory().newGrid(0,1);
    }
    public void init(AbstractProgramInfos _frameFactory, LanguageDialogButtons _window, AbsChangeLanguage _frame) {
        panel.removeAll();
        for (String l: _frameFactory.getLanguages()) {
            AbsButton radio_ = _frameFactory.getCompoFactory().newPlainButton(_frameFactory.getDisplayLanguages().getVal(l));
            radio_.addActionListener(new LanguageChoiceButton(l, _window,_frame));
            getGroupe().add(radio_);
            panel.add(radio_);
        }
    }

    public CustList<AbsButton> getGroupe() {
        return groupe;
    }

    public AbsPanel getPanel() {
        return panel;
    }
}
