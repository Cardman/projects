package cards.gui.animations;
import javax.swing.JPanel;

import cards.gui.labels.MiniTarotCard;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class HandfulThread extends Thread {

    private HandTarot poignee;

    private JPanel panelToSet;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public HandfulThread(HandTarot _poignee, JPanel _panelToSet) {
        poignee = _poignee;
        panelToSet = _panelToSet;
    }

    @Override
    public void run() {
        panelToSet.removeAll();
        for(CardTarot c:poignee) {
            MiniTarotCard carte_=new MiniTarotCard(c);
            panelToSet.add(carte_);
        }
        panelToSet.validate();
    }
}
