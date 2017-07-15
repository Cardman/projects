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
import cards.gui.labels.selection.CardPresidentCellRenderer;
import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;

public class PresidentCardsScrollableList extends CardsScrollableList {

    private EnumList<Suit> couleurs;
    private boolean decroissant;
    private EnumListModel<CardPresident> modeleListe = new EnumListModel<CardPresident>();
    private Jl<CardPresident> liste = new Jl<CardPresident>(modeleListe);
    private JLabel remCards;

    public PresidentCardsScrollableList(int _nb, int _pmax, String _titre) {
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

    public void initSelectionCartePresident() {
        getListe().setCellRenderer(new CardPresidentCellRenderer());
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
            modeleListe.addElement(c);
        }
        setNbCartesRestantes(getNbCartesRestantes() - _m.total());
        remCards.setText(PLS+getNbCartesRestantes());
    }
    public void ajouterCartesPresident(HandPresident _m) {
        for(CardPresident c:_m) {
            if(modeleListe.isEmpty()) {
                modeleListe.addElement(c);
                setNbCartesRestantes(getNbCartesRestantes() - 1);
                continue;
            }
            CardPresident card_ = (CardPresident) modeleListe.lastElement();
            if(card_.vientAvant(c,decroissant,couleurs)) {
                modeleListe.addElement(c);
            } else {
                byte b=0;
                while(((CardPresident)modeleListe.get(b)).vientAvant(c,decroissant,couleurs)) {
                    b++;
                }
                modeleListe.add(b, c);
            }
            setNbCartesRestantes(getNbCartesRestantes() - 1);
        }
        remCards.setText(PLS+getNbCartesRestantes());
    }
    public void supprimerCartesPresident(HandPresident _cs) {
        int indice_;
        for(CardPresident c:_cs) {
            indice_=modeleListe.indexOf(c);
            if(indice_>-1) {
                modeleListe.removeElementAt(indice_);
                setNbCartesRestantes(getNbCartesRestantes() + 1);
            }
        }
        remCards.setText(PLS+getNbCartesRestantes());
    }
    public HandPresident valMainPresident() {
        HandPresident main_=new HandPresident();
        int taille_=taille();
        for (int i = CustList.FIRST_INDEX; i < taille_; i++) {
            main_.ajouter((CardPresident)modeleListe.get(i));
        }
        return main_;
    }
    public HandPresident getCartesPresidentSelectionnees() {
        if(getListe().isSelectionEmpty()) {
            return new HandPresident();
        }
        HandPresident main_=new HandPresident();
        for (Object c: liste.getSelectedValuesLs()) {
            main_.ajouter((CardPresident)c);
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
    public Jl<CardPresident> getListe() {
        return liste;
    }
    @Override
    protected JLabel getRemCards() {
        return remCards;
    }
}
