package cards.gui.animations;
import javax.swing.JPanel;

import cards.belote.DeclareHandBelote;
import cards.belote.enumerations.CardBelote;
import cards.gui.labels.MiniBeloteCard;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class DeclaringThread extends Thread {

    private JPanel panelToSet;

    private DeclareHandBelote usDecl;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public DeclaringThread(JPanel _panelToSet, DeclareHandBelote _usDecl) {
        panelToSet = _panelToSet;
        usDecl = _usDecl;
    }

    @Override
    public void run() {
        panelToSet.removeAll();
        for(CardBelote c: usDecl.getMain()) {
            MiniBeloteCard carte_=new MiniBeloteCard(c);
            panelToSet.add(carte_);
        }
        panelToSet.validate();
    }
}
