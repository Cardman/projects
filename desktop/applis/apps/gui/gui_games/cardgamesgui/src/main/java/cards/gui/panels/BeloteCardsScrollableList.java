package cards.gui.panels;



import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.Suit;
import cards.gui.WindowCards;
import cards.gui.labels.selection.CardBeloteCellRenderer;
import code.gui.AbsGraphicList;
import code.gui.AbsPlainLabel;
import code.gui.GuiConstants;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.EnumList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class BeloteCardsScrollableList extends CardsScrollableList {

    private EnumList<Suit> couleurs;
    private Order ordre;
    private boolean decroissant;
    private final AbsGraphicList<CardBelote> liste;
    private final AbsPlainLabel remCards;

    public BeloteCardsScrollableList(WindowCards _parent, int _nb, int _pmax, String _titre) {
        super(_parent.getCompoFactory());
        liste = _parent.getCardFactories().getGeneBelote().createMult(_parent.getImageFactory(),new CardBeloteCellRenderer(_parent));
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

    public void iniPileBelote(HandBelote _main) {
        ajouterCartesBelote(_main);
        initText();
    }
    public void setTriBelote(EnumList<Suit> _pcouleurs,Order _pordre,boolean _psens) {
        couleurs=_pcouleurs;
        ordre=_pordre;
        decroissant=_psens;
    }
    public void ajouterCartesBeloteFin(HandBelote _m) {
        for(CardBelote c:_m) {
            liste.add(c);
        }
        setNbCartesRestantes(getNbCartesRestantes() - _m.total());
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }
    public void ajouterCartesBelote(HandBelote _m) {
        for(CardBelote c:_m) {
            if(liste.isEmpty()) {
                liste.add(c);
                setNbCartesRestantes(getNbCartesRestantes() - 1);
                continue;
            }
            CardBelote card_ = liste.last();
            if(card_.vientAvant(c,decroissant,ordre,couleurs)) {
                liste.add(c);
            } else {
                byte b=0;
                while(liste.get(b).vientAvant(c,decroissant,ordre,couleurs)) {
                    b++;
                }
                liste.add(b, c);
            }
            setNbCartesRestantes(getNbCartesRestantes() - 1);
        }
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }
    public void supprimerCartesBelote(HandBelote _cs) {
        int indice_;
        for(CardBelote c:_cs) {
            indice_ = -1;
            int i_ = -1;
            for (CardBelote v: liste.getList()) {
                if (v == c) {
                    i_ = indice_ + 1;
                    break;
                }
                indice_++;
            }
            if(i_>-1) {
                liste.remove(i_);
                setNbCartesRestantes(getNbCartesRestantes() + 1);
            }
        }
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }
    public HandBelote valMainBelote() {
        HandBelote main_=new HandBelote();
        int taille_=taille();
        for (int i = IndexConstants.FIRST_INDEX; i < taille_; i++) {
            main_.ajouter(liste.get(i));
        }
        return main_;
    }
    public HandBelote getCartesBeloteSelectionnees() {
        if(liste.isSelectionEmpty()) {
            return new HandBelote();
        }
        HandBelote main_=new HandBelote();
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
    public AbsGraphicList<CardBelote> getListe() {
        return liste;
    }
    @Override
    protected AbsPlainLabel getRemCards() {
        return remCards;
    }
}
