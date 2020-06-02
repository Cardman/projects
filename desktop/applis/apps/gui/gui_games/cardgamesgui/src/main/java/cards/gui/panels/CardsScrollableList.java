package cards.gui.panels;
import java.awt.Dimension;

import javax.swing.JLabel;

import code.gui.TextLabel;
import code.util.StringList;

/** */
public abstract class CardsScrollableList extends ScrollableList {
    protected static final String PLS="Pls: ";

    private int nbCartesRestantes;

    private int max;

    protected void initText() {
        getRemCards().setText(StringList.concatNbs(PLS,getNbCartesRestantes()));
    }

    void setMax(int _max) {
        max = _max;
    }
    /**Retourne le nombre total de cartes*/
    public int getMax() {
        return max;
    }
    protected abstract TextLabel getRemCards();
    public abstract int nombreCartesSelectionnees();
    protected int getNbCartesRestantes() {
        return nbCartesRestantes;
    }
    protected void setNbCartesRestantes(int _nbCartesRestantes) {
        nbCartesRestantes = _nbCartesRestantes;
    }
}
