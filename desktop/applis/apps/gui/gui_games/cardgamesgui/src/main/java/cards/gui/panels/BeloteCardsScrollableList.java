package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.Suit;
import cards.gui.MainWindow;
import cards.gui.labels.selection.CardBeloteCellRenderer;
import code.gui.AbsGraphicList;
import code.gui.GraphicList;
import code.gui.TextLabel;
import code.util.EnumList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class BeloteCardsScrollableList extends CardsScrollableList {

    private EnumList<Suit> couleurs;
    private Order ordre;
    private boolean decroissant;
    private final AbsGraphicList<CardBelote> liste;
    private final TextLabel remCards;

    public BeloteCardsScrollableList(int _nb, int _pmax, String _titre, AbsGraphicList<CardBelote> _liste) {
        liste = _liste;
        setMax(_pmax);
        TextLabel titrePanneau_ = new TextLabel(_titre, SwingConstants.CENTER);
        getContainer().add(titrePanneau_, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        setNbCartesRestantes(_pmax);
        getContainer().add(liste.self(),BorderLayout.CENTER);
        remCards = new TextLabel(StringUtil.concatNbs(PLS,getNbCartesRestantes()), SwingConstants.CENTER);
        getContainer().add(remCards, BorderLayout.SOUTH);
        getContainer().setPreferredSize(new Dimension(100,10*(_nb+4)));
    }

    public void initSelectionCarteBelote(MainWindow _window) {
        liste.setRender(new CardBeloteCellRenderer(_window));
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
        for (CardBelote c: liste.getSelectedValuesLs()) {
            main_.ajouter(c);
        }
        return main_;
    }
    public int taille() {
        return liste.size();
    }
    /**Retourne le nombre de cartes selectionnees*/
    @Override
    public int nombreCartesSelectionnees() {
        return liste.getSelectedValuesLs().size();
    }
    public AbsGraphicList<CardBelote> getListe() {
        return liste;
    }
    @Override
    protected TextLabel getRemCards() {
        return remCards;
    }
}
