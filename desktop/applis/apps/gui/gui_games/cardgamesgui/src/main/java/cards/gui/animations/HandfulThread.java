package cards.gui.animations;
import cards.gui.WindowCards;
import cards.gui.labels.MiniTarotCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.AbsPanel;
import code.gui.Panel;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class HandfulThread implements Runnable {

    private HandTarot poignee;

    private AbsPanel panelToSet;
    private WindowCards window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public HandfulThread(HandTarot _poignee, AbsPanel _panelToSet, WindowCards _window) {
        poignee = _poignee;
        panelToSet = _panelToSet;
        window = _window;
    }

    @Override
    public void run() {
        String lg_ = window.getLanguageKey();
        panelToSet.removeAll();
        for(CardTarot c:poignee) {
            MiniTarotCard carte_=new MiniTarotCard(lg_,c, window.getCompoFactory());
            panelToSet.add(carte_);
        }
        panelToSet.validate();
    }
}
