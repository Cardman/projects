package cards.gui.panels;

import java.awt.Dimension;

import cards.consts.Suit;
import cards.gui.WindowCards;
import cards.gui.labels.selection.CardTarotCellRenderer;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.AbsGraphicList;
import code.gui.AbsPlainLabel;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;
import code.util.EnumList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class TarotCardsScrollableList extends CardsScrollableList {

    private EnumList<Suit> couleurs;
    private boolean decroissant;
    private final AbsGraphicList<CardTarot> liste;
    private final AbsPlainLabel remCards;

    public TarotCardsScrollableList(AbsCompoFactory _compoFactory, int _nb, int _pmax, String _titre, AbsGraphicList<CardTarot> _liste) {
        super(_compoFactory);
        liste = _liste;
        setMax(_pmax);
        AbsPlainLabel titrePanneau_ = _compoFactory.newPlainLabel(_titre);
        getContainer().add(titrePanneau_, GuiConstants.BORDER_LAYOUT_NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        setNbCartesRestantes(_pmax);
        getContainer().add(liste.self(), GuiConstants.BORDER_LAYOUT_CENTER);
        remCards = _compoFactory.newPlainLabel(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
        getContainer().add(remCards, GuiConstants.BORDER_LAYOUT_SOUTH);
        getContainer().setPreferredSize(new Dimension(100,10*(_nb+4)));
    }
    public void initSelectionCarteTarot(WindowCards _window) {
        liste.setRender(new CardTarotCellRenderer(_window));
    }
    public void iniPileTarot(HandTarot _main) {
        ajouterCartesTarot(_main);
        initText();
    }
    public void setTriTarot(EnumList<Suit> _pcouleurs,boolean _psens) {
        couleurs=_pcouleurs;
        decroissant=_psens;
    }
    public void ajouterCartesTarotFin(HandTarot _m) {
        for(CardTarot c:_m) {
            liste.add(c);
        }
        setNbCartesRestantes(getNbCartesRestantes() - _m.total());
        remCards.setText(StringUtil.concatNbs(PLS,getNbCartesRestantes()));
    }
    /**Utilisee pour ajouter des cartes en respectant le tri*/
    public void ajouterCartesTarot(HandTarot _m) {
        for(CardTarot c:_m) {
            if(liste.isEmpty()) {
                liste.add(c);
                setNbCartesRestantes(getNbCartesRestantes() - 1);
                continue;
            }
            CardTarot card_ = liste.last();
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
    public void supprimerCartesTarot(HandTarot _cs) {
        int indice_;
        for(CardTarot c:_cs) {
            indice_ = -1;
            int i_ = -1;
            for (CardTarot v: liste.getList()) {
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
    public HandTarot valMainTarot() {
        HandTarot main_=new HandTarot();
        int taille_=taille();
        for (int i = IndexConstants.FIRST_INDEX; i < taille_; i++) {
            main_.ajouter(liste.get(i));
        }
        return main_;
    }
    public HandTarot getCartesTarotSelectionnees() {
        if(liste.isSelectionEmpty()) {
            return new HandTarot();
        }
        HandTarot main_=new HandTarot();
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
    public AbsGraphicList<CardTarot> getListe() {
        return liste;
    }
    @Override
    protected AbsPlainLabel getRemCards() {
        return remCards;
    }
}
