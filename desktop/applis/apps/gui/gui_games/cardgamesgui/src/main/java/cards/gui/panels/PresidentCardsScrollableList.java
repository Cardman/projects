package cards.gui.panels;



import cards.consts.Suit;
import cards.gui.WindowCards;
import cards.gui.labels.selection.CardPresidentCellRenderer;
import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import code.gui.AbsGraphicList;
import code.gui.AbsPlainLabel;
import code.gui.GuiConstants;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.EnumList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class PresidentCardsScrollableList extends CardsScrollableList {

    private EnumList<Suit> couleurs;
    private boolean decroissant;
    private final AbsGraphicList<CardPresident> liste;
    private final AbsPlainLabel remCards;

    public PresidentCardsScrollableList(WindowCards _parent, int _nb, int _pmax, String _titre) {
        super(_parent.getCompoFactory());
        liste = _parent.getCardFactories().getGenePresident().createMult(_parent.getImageFactory(),new CardPresidentCellRenderer(_parent));
        setMax(_pmax);
        AbsPlainLabel titrePanneau_ = _parent.getCompoFactory().newPlainLabel(_titre);
        getContainer().add(titrePanneau_, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        setNbCartesRestantes(_pmax);
        getContainer().add(liste.self(), GuiConstants.BORDER_LAYOUT_CENTER);
        remCards = _parent.getCompoFactory().newPlainLabel(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
        getContainer().add(remCards, GuiConstants.BORDER_LAYOUT_SOUTH);
        getContainer().setPreferredSize(new MetaDimension(100,10*(_nb+4)));
    }

    public void iniPilePresident(HandPresident _main) {
        ajouterCartesPresident(_main);
        initText();
    }
    public void setTriPresident(EnumList<Suit> _pcouleurs,boolean _psens) {
        couleurs=_pcouleurs;
        decroissant=_psens;
    }
    public void ajouterCartesPresidentFin(HandPresident _m) {
        for(CardPresident c:_m) {
            liste.add(c);
        }
        setNbCartesRestantes(getNbCartesRestantes() - _m.total());
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }
    public void ajouterCartesPresident(HandPresident _m) {
        for(CardPresident c:_m) {
            if(liste.isEmpty()) {
                liste.add(c);
                setNbCartesRestantes(getNbCartesRestantes() - 1);
                continue;
            }
            CardPresident card_ = liste.last();
            if(card_.vientAvant(c,decroissant,couleurs)) {
                liste.add(c);
            } else {
                byte b=0;
                while(liste.get(b).vientAvant(c,decroissant,couleurs)) {
                    b++;
                }
                liste.add(b, c);
            }
            setNbCartesRestantes(getNbCartesRestantes() - 1);
        }
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }
    public void supprimerCartesPresident(HandPresident _cs) {
        int indice_;
        for(CardPresident c:_cs) {
            indice_ = -1;
            int i_ = -1;
            for (CardPresident v: liste.getList()) {
                if (v == c) {
                    i_ = indice_ + 1;
                    break;
                }
                indice_++;
            }
            if(i_>-1) {
                liste.remove(indice_);
                setNbCartesRestantes(getNbCartesRestantes() + 1);
            }
        }
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }
    public HandPresident valMainPresident() {
        HandPresident main_=new HandPresident();
        int taille_=taille();
        for (int i = IndexConstants.FIRST_INDEX; i < taille_; i++) {
            main_.ajouter(liste.get(i));
        }
        return main_;
    }
    public HandPresident getCartesPresidentSelectionnees() {
        if(liste.isSelectionEmpty()) {
            return new HandPresident();
        }
        HandPresident main_=new HandPresident();
        for (int i: liste.getSelectedIndexes()) {
            main_.ajouter(liste.get(i));
        }
        return main_;
    }
    public int taille() {
        return liste.size();
    }
    /**Retourne le nombre de cartes selectionnees*/
    @Override
    public int nombreCartesSelectionnees() {
        return liste.getSelectedValuesLsLen();
    }
    public AbsGraphicList<CardPresident> getListe() {
        return liste;
    }
    @Override
    protected AbsPlainLabel getRemCards() {
        return remCards;
    }
}
