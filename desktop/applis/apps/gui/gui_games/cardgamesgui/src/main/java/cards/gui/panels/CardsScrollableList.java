package cards.gui.panels;

import code.gui.AbsPlainLabel;
import code.gui.initialize.AbsCompoFactory;
import code.util.core.StringUtil;

/** */
public abstract class CardsScrollableList extends ScrollableList {
    protected static final String PLS="Pls: ";

    private int nbCartesRestantes;

    private int max;

    public CardsScrollableList(AbsCompoFactory _absCompoFactory) {
        super(_absCompoFactory);
    }

    protected void initText() {
        getRemCards().setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }

    void setMax(int _max) {
        max = _max;
    }
    /**Retourne le nombre total de cartes*/
    public int getMax() {
        return max;
    }
    protected abstract AbsPlainLabel getRemCards();
    public abstract int nombreCartesSelectionnees();
    protected int getNbCartesRestantes() {
        return nbCartesRestantes;
    }
    protected void setNbCartesRestantes(int _nbCartesRestantes) {
        nbCartesRestantes = _nbCartesRestantes;
    }
}
