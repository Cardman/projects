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
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.Suit;
import cards.gui.labels.selection.CardBeloteCellRenderer;

public class BeloteCardsScrollableList extends CardsScrollableList {

    private EnumList<Suit> couleurs;
    private Order ordre;
    private boolean decroissant;
    private EnumListModel<CardBelote> modeleListe = new EnumListModel<CardBelote>();
    private Jl<CardBelote> liste = new Jl<CardBelote>(modeleListe);
    private JLabel remCards;

    public BeloteCardsScrollableList(int _nb, int _pmax, String _titre) {
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

    public void initSelectionCarteBelote() {
        getListe().setCellRenderer(new CardBeloteCellRenderer());
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
            modeleListe.addElement(c);
        }
        setNbCartesRestantes(getNbCartesRestantes() - _m.total());
        remCards.setText(PLS+getNbCartesRestantes());
    }
    public void ajouterCartesBelote(HandBelote _m) {
        for(CardBelote c:_m) {
            if(modeleListe.isEmpty()) {
                modeleListe.addElement(c);
                setNbCartesRestantes(getNbCartesRestantes() - 1);
                continue;
            }
            CardBelote card_ = (CardBelote) modeleListe.lastElement();
            if(card_.vientAvant(c,decroissant,ordre,couleurs)) {
                modeleListe.addElement(c);
            } else {
                byte b=0;
                while(((CardBelote) modeleListe.get(b)).vientAvant(c,decroissant,ordre,couleurs)) {
                    b++;
                }
                modeleListe.add(b, c);
            }
            setNbCartesRestantes(getNbCartesRestantes() - 1);
        }
        remCards.setText(PLS+getNbCartesRestantes());
    }
    public void supprimerCartesBelote(HandBelote _cs) {
        int indice_;
        for(CardBelote c:_cs) {
            indice_=modeleListe.indexOf(c);
            if(indice_>-1) {
                modeleListe.removeElementAt(indice_);
                setNbCartesRestantes(getNbCartesRestantes() + 1);
            }
        }
        remCards.setText(PLS+getNbCartesRestantes());
    }
    public HandBelote valMainBelote() {
        HandBelote main_=new HandBelote();
        int taille_=taille();
        for (int i = CustList.FIRST_INDEX; i < taille_; i++) {
            main_.ajouter((CardBelote)modeleListe.get(i));
        }
        return main_;
    }
    public HandBelote getCartesBeloteSelectionnees() {
        if(getListe().isSelectionEmpty()) {
            return new HandBelote();
        }
        HandBelote main_=new HandBelote();
        for (Object c: liste.getSelectedValuesLs()) {
            main_.ajouter((CardBelote)c);
        }
        return main_;
    }
    public int taille() {
        return modeleListe.size();
    }
    /**Retourne le nombre de cartes selectionnees*/
    @Override
    public int nombreCartesSelectionnees() {
        return liste.getSelectedValuesLs().size();
    }
    public Jl<CardBelote> getListe() {
        return liste;
    }
    @Override
    protected JLabel getRemCards() {
        return remCards;
    }
}
