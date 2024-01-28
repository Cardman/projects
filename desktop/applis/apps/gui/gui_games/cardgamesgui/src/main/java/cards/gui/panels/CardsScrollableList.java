package cards.gui.panels;

import code.gui.AbsPlainLabel;
import code.gui.GuiConstants;
import code.gui.ScrollCustomGraphicList;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

/** */
public class CardsScrollableList<T> extends ScrollableList<T> implements AbsCardsScrollableList {
    protected static final String PLS="Pls: ";

    private int nbCartesRestantes;

    private int max;
    private final AbsPlainLabel remCards;
    private final Comparing<T> comparing;
    public CardsScrollableList(AbsCompoFactory _absCompoFactory, int _nb, int _pmax, String _titre, ScrollCustomGraphicList<T> _scrollable, Comparing<T> _cmp) {
        super(_absCompoFactory, _scrollable);
        comparing = _cmp;
        setMax(_pmax);
        AbsPlainLabel titrePanneau_ = _absCompoFactory.newPlainLabel(_titre);
        getContainer().add(titrePanneau_, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        getListe().getScrollPane().setPreferredSize(new MetaDimension(100,10* _nb));
        setNbCartesRestantes(_pmax);
        getContainer().add(getListe().getScrollPane(), GuiConstants.BORDER_LAYOUT_CENTER);
        remCards = _absCompoFactory.newPlainLabel(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
        getContainer().add(remCards, GuiConstants.BORDER_LAYOUT_SOUTH);
        getContainer().setPreferredSize(new MetaDimension(100,10*(_nb+4)));
    }

    public void iniPile(CustList<T> _main) {
        ajouterCartes(_main);
        initText();
    }
    public void ajouterCartesFin(CustList<T> _m) {
        for(T c:_m) {
            getListe().add(c);
        }
        setNbCartesRestantes(getMax() - getListe().size());
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }
    public void ajouterCartes(CustList<T> _m) {
        for(T c:_m) {
            if(getListe().isEmpty()) {
                getListe().add(c);
                continue;
            }
            T card_ = getListe().last();
            if (comparing.compare(card_,c) == SortConstants.NO_SWAP_SORT) {
                getListe().add(c);
            } else {
                int b=0;
                while(comparing.compare(getListe().get(b),c) == SortConstants.NO_SWAP_SORT) {
                    b++;
                }
                getListe().add(b, c);
            }
        }
        setNbCartesRestantes(getMax() - getListe().size());
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }

    @Override
    public void supprimerElts() {
        super.supprimerElts();
        setNbCartesRestantes(getMax() - getListe().size());
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
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
    protected AbsPlainLabel getRemCards(){
        return remCards;
    }
    /**Retourne le nombre de cartes selectionnees*/
    @Override
    public int nombreCartesSelectionnees() {
        return getListe().getSelectedValuesLsLen();
    }
    protected int getNbCartesRestantes() {
        return nbCartesRestantes;
    }
    protected void setNbCartesRestantes(int _nbCartesRestantes) {
        nbCartesRestantes = _nbCartesRestantes;
    }
}
