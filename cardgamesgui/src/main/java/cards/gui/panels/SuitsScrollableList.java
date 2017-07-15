package cards.gui.panels;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import code.gui.EnumListModel;
import code.gui.Jl;
import code.stream.ExtractFromFiles;
import code.util.CustList;
import code.util.EnumList;
import code.util.StringMap;
import code.util.consts.Constants;
import cards.consts.Suit;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.selection.SuitCellRenderer;
/**
 */
public class SuitsScrollableList extends ScrollableList {
    private static final String ACCESS = "cards.gui.panels.SuitsScrollableList";
    private static final String SUITS = "suits";
    private StringMap<String> messages = new StringMap<String>();
//    private EnumList<Suit> suits;
    private EnumListModel<Suit> modeleListeCouleurs = new EnumListModel<Suit>();
    private Jl<Suit> liste;
    public SuitsScrollableList(EnumList<Suit> _couleurs,int _nb) {
        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), ACCESS);
        JLabel titrePanneau_ = new JLabel(messages.getVal(SUITS), SwingConstants.CENTER);
        add(titrePanneau_, BorderLayout.NORTH);
//        suits = _couleurs;
        for (Suit couleur_ : _couleurs) {
            modeleListeCouleurs.addElement(couleur_);
        }
        liste=new Jl<Suit>(modeleListeCouleurs);
        liste.setCellRenderer(new SuitCellRenderer());
        setSelection(false, liste);
        //On peut selectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        add(new JScrollPane(liste),BorderLayout.CENTER);
    }
    public EnumList<Suit> getCouleurs() {
        int s_ = modeleListeCouleurs.size();
        EnumList<Suit> valeurs_=new EnumList<Suit>();
        for (int i = CustList.FIRST_INDEX; i < s_; i++) {
            valeurs_.add((Suit)modeleListeCouleurs.get(i));
        }
//        
//        for (Suit s: Collections.list(modeleListeCouleurs.elements())) {
//            valeurs_.add(s);
//        }
        return valeurs_;
//        return suits;
    }
    /**
    couleurs dans la liste
    (selectionnees ou non)*/
    public int nombreDeCouleurs() {
        return modeleListeCouleurs.size();
//        return suits.size();
    }
    public void ajouterCouleur(Suit _couleur) {
        // recuperer les valeurs dans le formulaire
        // et ajouter le nouvel etudiant corrrespondant
        modeleListeCouleurs.addElement(_couleur);
//        suits.add(_couleur);
    }
    public void supprimerCouleurs(EnumList<Suit> _couleurs) {
        for (Suit s: _couleurs) {
//            if (suits.containsObj(s)) {
//                int i_ = suits.indexOfObj(s);
//                modeleListeCouleurs.removeElementAt(i_);
//                suits.removeObj(s);
//            }
            if(modeleListeCouleurs.contains(s)) {
                
                modeleListeCouleurs.removeElementAt(modeleListeCouleurs.indexOf(s));
            }
        }
    }
    public void toutSupprimer() {
        modeleListeCouleurs.removeAllElements();
//        suits.clear();
    }
    public EnumList<Suit> getCouleursSelectionnees() {
        if(getListe().isSelectionEmpty()) {
            return new EnumList<Suit>();
        }
        EnumList<Suit> valeurs_=new EnumList<Suit>();
        for (Object s: getListe().getSelectedValuesLs()) {
            valeurs_.add((Suit)s);
        }
        //Appelee 2 fois
        return valeurs_;
    }
    private static void setSelection(boolean _selectionUnique,Jl<Suit> _liste) {
        if(_selectionUnique) {
            _liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        } else {
            _liste.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        }
    }
    public Jl<Suit> getListe() {
        return liste;
    }

}
