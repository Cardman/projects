package cards.gui.menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cards.gui.MainWindow;
import cards.tarot.enumerations.ChoiceTarot;

public class ListenerTrainingTarot implements ActionListener {

    private MainWindow window;

    private ChoiceTarot choiceTarot;

    public ListenerTrainingTarot(MainWindow _window, ChoiceTarot _choiceTarot) {
        window = _window;
        choiceTarot = _choiceTarot;
    }

    @Override
    public void actionPerformed(ActionEvent _arg0) {
        window.trainingTarot(choiceTarot);
    }
}
