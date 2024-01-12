package cards.gui.animations;

import cards.gui.WindowCardsInt;
import cards.gui.labels.MiniCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.AbsPanel;


/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class HandfulThread implements Runnable {

    private HandTarot poignee;

    private AbsPanel panelToSet;
    private WindowCardsInt window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public HandfulThread(HandTarot _poignee, AbsPanel _panelToSet, WindowCardsInt _window) {
        poignee = _poignee;
        panelToSet = _panelToSet;
        window = _window;
    }

    @Override
    public void run() {
        String lg_ = window.getLanguageKey();
        panelToSet.removeAll();
        for(CardTarot c:poignee) {
            MiniCard carte_=new MiniCard(lg_, window, c.getId().nb());
            panelToSet.add(carte_.getPaintableLabel());
        }
        panelToSet.validate();
    }
}
