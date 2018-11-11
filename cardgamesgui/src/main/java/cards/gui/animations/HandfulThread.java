package cards.gui.animations;
import cards.gui.MainWindow;
import cards.gui.labels.MiniTarotCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.Panel;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class HandfulThread extends Thread {

    private HandTarot poignee;

    private Panel panelToSet;
    private MainWindow window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public HandfulThread(HandTarot _poignee, Panel _panelToSet, MainWindow _window) {
        poignee = _poignee;
        panelToSet = _panelToSet;
        window = _window;
    }

    @Override
    public void run() {
        String lg_ = window.getLanguageKey();
        panelToSet.removeAll();
        for(CardTarot c:poignee) {
            MiniTarotCard carte_=new MiniTarotCard(lg_,c);
            panelToSet.add(carte_);
        }
        panelToSet.validate();
    }
}
