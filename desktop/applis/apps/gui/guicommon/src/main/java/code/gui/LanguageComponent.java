package code.gui;

import code.gui.events.LanguageChoice;
import code.gui.events.SetterLanguage;
import code.gui.initialize.AbstractProgramInfos;

public final class LanguageComponent {
    private final CustButtonGroup groupe = new CustButtonGroup();
    private final AbsPanel panel;
    public LanguageComponent(AbstractProgramInfos _frameFactory) {
        panel = _frameFactory.getCompoFactory().newGrid(0,1);
    }
    public void init(AbstractProgramInfos _frameFactory, SetterLanguage _window) {
        panel.removeAll();
        for (String l: _frameFactory.getLanguages()) {
            AbsRadioButton radio_ = _frameFactory.getCompoFactory().newRadioButton(_frameFactory.getDisplayLanguages().getVal(l));
            radio_.addMouseListener(new LanguageChoice(l, _window));
            groupe.add(radio_);
            panel.add(radio_);
        }
    }

    public CustButtonGroup getGroupe() {
        return groupe;
    }

    public AbsPanel getPanel() {
        return panel;
    }
}
