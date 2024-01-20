package cards.gui.panels;

import code.gui.AbsPlainLabel;
import code.gui.GuiConstants;
import code.gui.ScrollCustomGraphicList;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.IdList;
import code.util.core.IndexConstants;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

/** */
public class CardsScrollableList<T> extends ScrollableList implements AbsCardsScrollableList {
    protected static final String PLS="Pls: ";

    private int nbCartesRestantes;

    private int max;
    private final AbsPlainLabel remCards;
    private final ScrollCustomGraphicList<T> liste;
    private final Comparing<T> comparing;
    public CardsScrollableList(AbsCompoFactory _absCompoFactory, int _nb, int _pmax, String _titre, ScrollCustomGraphicList<T> _scrollable, Comparing<T> _cmp) {
        super(_absCompoFactory);
        comparing = _cmp;
        liste = _scrollable;
        setMax(_pmax);
        AbsPlainLabel titrePanneau_ = _absCompoFactory.newPlainLabel(_titre);
        getContainer().add(titrePanneau_, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.getScrollPane().setPreferredSize(new MetaDimension(100,10* _nb));
        setNbCartesRestantes(_pmax);
        getContainer().add(liste.getScrollPane(), GuiConstants.BORDER_LAYOUT_CENTER);
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
            liste.add(c);
        }
        setNbCartesRestantes(getMax() - liste.size());
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }
    public void ajouterCartes(CustList<T> _m) {
        for(T c:_m) {
            if(liste.isEmpty()) {
                liste.add(c);
                continue;
            }
            T card_ = liste.last();
            if (comparing.compare(card_,c) == SortConstants.NO_SWAP_SORT) {
                liste.add(c);
            } else {
                byte b=0;
                while(comparing.compare(liste.get(b),c) == SortConstants.NO_SWAP_SORT) {
                    b++;
                }
                liste.add(b, c);
            }
        }
        setNbCartesRestantes(getMax() - liste.size());
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }
    public void supprimerCartes(CustList<T> _cs) {
        for(T c:_cs) {
            IdList<T> ls_ = new IdList<T>(liste.getList());
            liste.remove(ls_.indexOfObj(c));
        }
        setNbCartesRestantes(getMax() - liste.size());
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }
    public IdList<T> valMain() {
        IdList<T> main_=new IdList<T>();
        int taille_=taille();
        for (int i = IndexConstants.FIRST_INDEX; i < taille_; i++) {
            main_.add(liste.get(i));
        }
        return main_;
    }
    public CustList<T> getCartesSelectionnees() {
        CustList<T> main_=new CustList<T>();
        for (int i: liste.getSelectedIndexes()) {
            main_.add(liste.get(i));
        }
        return main_;
    }
    public int taille() {
        return liste.size();
    }
    public ScrollCustomGraphicList<T> getListe() {
        return liste;
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
        return liste.getSelectedValuesLsLen();
    }
    protected int getNbCartesRestantes() {
        return nbCartesRestantes;
    }
    protected void setNbCartesRestantes(int _nbCartesRestantes) {
        nbCartesRestantes = _nbCartesRestantes;
    }
}
