package cards.gui.panels;
import java.awt.Dimension;

import javax.swing.JLabel;

/** */
public abstract class CardsScrollableList extends ScrollableList {
    protected static final String PLS="Pls: ";

    private int nbCartesRestantes;

    private int max;

    protected void initText() {
        getRemCards().setText(PLS+getNbCartesRestantes());
    }
    /**Utilise pour le solitaire pour reduire la largeur*/
    public void setPreference(int _nb) {
        setPreferredSize(new Dimension(50,10*(_nb+4)));
    }
    void setMax(int _max) {
        max = _max;
    }
    /**Retourne le nombre total de cartes*/
    public int getMax() {
        return max;
    }
    protected abstract JLabel getRemCards();
    public abstract int nombreCartesSelectionnees();
    protected int getNbCartesRestantes() {
        return nbCartesRestantes;
    }
    protected void setNbCartesRestantes(int _nbCartesRestantes) {
        nbCartesRestantes = _nbCartesRestantes;
    }
}
