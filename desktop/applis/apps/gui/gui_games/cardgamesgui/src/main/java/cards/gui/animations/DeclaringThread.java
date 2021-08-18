package cards.gui.animations;
import cards.belote.DeclareHandBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.WindowCards;
import cards.gui.labels.MiniBeloteCard;
import code.gui.Panel;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class DeclaringThread implements Runnable {

    private Panel panelToSet;

    private DeclareHandBelote usDecl;
    private WindowCards window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public DeclaringThread(Panel _panelToSet, DeclareHandBelote _usDecl, WindowCards _window) {
        panelToSet = _panelToSet;
        usDecl = _usDecl;
        window = _window;
    }

    @Override
    public void run() {
        String lg_ = window.getLanguageKey();
        panelToSet.removeAll();
        for(CardBelote c: usDecl.getHand()) {
            MiniBeloteCard carte_=new MiniBeloteCard(lg_,c);
            panelToSet.add(carte_);
        }
        panelToSet.validate();
    }
}
