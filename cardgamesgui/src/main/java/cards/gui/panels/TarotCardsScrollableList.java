package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import cards.consts.Suit;
import cards.gui.MainWindow;
import cards.gui.labels.selection.CardTarotCellRenderer;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.GraphicList;
import code.gui.TextLabel;
import code.util.CustList;
import code.util.EnumList;
import code.util.StringList;

public class TarotCardsScrollableList extends CardsScrollableList {

    private EnumList<Suit> couleurs;
    private boolean decroissant;
    private GraphicList<CardTarot> liste;
    private TextLabel remCards;

    public TarotCardsScrollableList(int _nb, int _pmax, String _titre) {
        liste = new GraphicList<CardTarot>(false);
        setMax(_pmax);
        TextLabel titrePanneau_ = new TextLabel(_titre, SwingConstants.CENTER);
        getContainer().add(titrePanneau_, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        setNbCartesRestantes(_pmax);
        getContainer().add(liste,BorderLayout.CENTER);
        remCards = new TextLabel(StringList.concatNbs(PLS,getNbCartesRestantes()), SwingConstants.CENTER);
        getContainer().add(remCards, BorderLayout.SOUTH);
        getContainer().setPreferredSize(new Dimension(100,10*(_nb+4)));
    }
    public void initSelectionCarteTarot(MainWindow _window) {
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
        remCards.setText(StringList.concatNbs(PLS,getNbCartesRestantes()));
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
        remCards.setText(StringList.concatNbs(PLS,getNbCartesRestantes()));
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
        remCards.setText(StringList.concatNbs(PLS,getNbCartesRestantes()));
    }
    public HandTarot valMainTarot() {
        HandTarot main_=new HandTarot();
        int taille_=taille();
        for (int i = CustList.FIRST_INDEX; i < taille_; i++) {
            main_.ajouter(liste.get(i));
        }
        return main_;
    }
    public HandTarot getCartesTarotSelectionnees() {
        if(liste.isSelectionEmpty()) {
            return new HandTarot();
        }
        HandTarot main_=new HandTarot();
        for (CardTarot c: liste.getSelectedValuesLs()) {
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
    public GraphicList<CardTarot> getListe() {
        return liste;
    }
    @Override
    protected TextLabel getRemCards() {
        return remCards;
    }
}
