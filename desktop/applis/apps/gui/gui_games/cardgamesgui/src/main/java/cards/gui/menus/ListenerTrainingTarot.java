package cards.gui.menus;

import cards.gui.WindowCards;
import cards.tarot.enumerations.ChoiceTarot;
import code.gui.events.AbsActionListener;

public class ListenerTrainingTarot implements AbsActionListener {

    private WindowCards window;

    private ChoiceTarot choiceTarot;

    public ListenerTrainingTarot(WindowCards _window, ChoiceTarot _choiceTarot) {
        window = _window;
        choiceTarot = _choiceTarot;
    }

    @Override
    public void action() {
        window.trainingTarot(choiceTarot);
    }
}
