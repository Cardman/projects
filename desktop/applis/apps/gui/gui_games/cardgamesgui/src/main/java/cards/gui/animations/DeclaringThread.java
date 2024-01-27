package cards.gui.animations;

import cards.belote.DeclareHandBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.WindowCardsInt;
import cards.gui.labels.MiniCard;
import code.gui.AbsPanel;
import code.sml.util.TranslationsLg;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class DeclaringThread implements Runnable {

    private AbsPanel panelToSet;

    private DeclareHandBelote usDecl;
    private WindowCardsInt window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public DeclaringThread(AbsPanel _panelToSet, DeclareHandBelote _usDecl, WindowCardsInt _window) {
        panelToSet = _panelToSet;
        usDecl = _usDecl;
        window = _window;
    }

    @Override
    public void run() {
        TranslationsLg lg_ = window.getFrames().currentLg();
        panelToSet.removeAll();
        for(CardBelote c: usDecl.getHand()) {
            MiniCard carte_=new MiniCard(lg_, window, c.getId().nb());
            panelToSet.add(carte_.getPaintableLabel());
        }
        panelToSet.validate();
    }
}
