package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import code.gui.EnumListModel;
import code.gui.Jl;
import code.util.CustList;
import code.util.EnumList;
import cards.consts.Suit;
import cards.gui.labels.selection.CardTarotCellRenderer;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;

public class TarotCardsScrollableList extends CardsScrollableList {

    private EnumList<Suit> couleurs;
    private boolean decroissant;
    private EnumListModel<CardTarot> modeleListe = new EnumListModel<CardTarot>();
    private Jl<CardTarot> liste = new Jl<CardTarot>(modeleListe);
    private JLabel remCards;

    public TarotCardsScrollableList(int _nb, int _pmax, String _titre) {
        setMax(_pmax);
        JLabel titrePanneau_ = new JLabel(_titre, SwingConstants.CENTER);
        add(titrePanneau_, BorderLayout.NORTH);
        //On peut slectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        setNbCartesRestantes(_pmax);
        add(new JScrollPane(liste),BorderLayout.CENTER);
        remCards = new JLabel(PLS+getNbCartesRestantes(), SwingConstants.CENTER);
        add(remCards, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(100,10*(_nb+4)));
    }
    public void initSelectionCarteTarot() {
        getListe().setCellRenderer(new CardTarotCellRenderer());
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
            modeleListe.addElement(c);
        }
        setNbCartesRestantes(getNbCartesRestantes() - _m.total());
        remCards.setText(PLS+getNbCartesRestantes());
    }
    /**Utilisee pour ajouter des cartes en respectant le tri*/
    public void ajouterCartesTarot(HandTarot _m) {
        for(CardTarot c:_m) {
            if(modeleListe.isEmpty()) {
                modeleListe.addElement(c);
                setNbCartesRestantes(getNbCartesRestantes() - 1);
                continue;
            }
            CardTarot card_ = (CardTarot) modeleListe.lastElement();
            if(card_.vientAvant(c,decroissant,couleurs)) {
                modeleListe.addElement(c);
            } else {
                byte b=0;
                while(((CardTarot)modeleListe.get(b)).vientAvant(c,decroissant,couleurs)) {
                    b++;
                }
                modeleListe.add(b, c);
            }
            setNbCartesRestantes(getNbCartesRestantes() - 1);
        }
        remCards.setText(PLS+getNbCartesRestantes());
    }
    public void supprimerCartesTarot(HandTarot _cs) {
        int indice_;
        for(CardTarot c:_cs) {
            indice_=modeleListe.indexOf(c);
            if(indice_>-1) {
                modeleListe.removeElementAt(indice_);
                setNbCartesRestantes(getNbCartesRestantes() + 1);
            }
        }
        remCards.setText(PLS+getNbCartesRestantes());
    }
    public HandTarot valMainTarot() {
        HandTarot main_=new HandTarot();
        int taille_=taille();
        for (int i = CustList.FIRST_INDEX; i < taille_; i++) {
            main_.ajouter((CardTarot)modeleListe.get(i));
        }
        return main_;
    }
    public HandTarot getCartesTarotSelectionnees() {
        if(getListe().isSelectionEmpty()) {
            return new HandTarot();
        }
        HandTarot main_=new HandTarot();
        for (Object c: liste.getSelectedValuesLs()) {
            main_.ajouter((CardTarot)c);
        }
        return main_;
    }
    public int taille() {
        return modeleListe.size();
    }
    /**Retourne le nombre de cartes selectionnees*/
    @Override
    public int nombreCartesSelectionnees() {
        return getListe().getSelectedValuesLs().size();
    }
    public Jl<CardTarot> getListe() {
        return liste;
    }
    @Override
    protected JLabel getRemCards() {
        return remCards;
    }
}
