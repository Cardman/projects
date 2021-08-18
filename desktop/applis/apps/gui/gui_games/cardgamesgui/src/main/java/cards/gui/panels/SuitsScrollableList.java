package cards.gui.panels;
import java.awt.BorderLayout;

import javax.swing.SwingConstants;

import cards.consts.Suit;
import cards.gui.WindowCards;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.selection.SuitCellRenderer;
import code.gui.AbsGraphicList;
import code.gui.TextLabel;
import code.util.EnumList;
import code.util.StringMap;
import code.util.core.IndexConstants;

/**
 */
public class SuitsScrollableList extends ScrollableList {
    private static final String ACCESS = "cards.gui.panels.suitsscrollablelist";
    private static final String SUITS = "suits";
    private StringMap<String> messages = new StringMap<String>();
//    private EnumList<Suit> suits;
    private final AbsGraphicList<Suit> liste;
    public SuitsScrollableList(EnumList<Suit> _couleurs, int _nb, WindowCards _window, AbsGraphicList<Suit> _liste) {
        String lg_ = _window.getLanguageKey();
        messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, lg_, ACCESS);
        TextLabel titrePanneau_ = new TextLabel(messages.getVal(SUITS), SwingConstants.CENTER);
        getContainer().add(titrePanneau_, BorderLayout.NORTH);
//        suits = _couleurs;
        liste= _liste;
        liste.setRender(new SuitCellRenderer(_window));
        for (Suit couleur_ : _couleurs) {
            liste.add(couleur_);
        }
        //On peut selectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.setVisibleRowCount(_nb);
        getContainer().add(liste.self(),BorderLayout.CENTER);
    }
    public EnumList<Suit> getCouleurs() {
        int s_ = liste.size();
        EnumList<Suit> valeurs_=new EnumList<Suit>();
        for (int i = IndexConstants.FIRST_INDEX; i < s_; i++) {
            valeurs_.add(liste.get(i));
        }
        return valeurs_;
//        return suits;
    }
    /**
    couleurs dans la liste
    (selectionnees ou non)*/
    public int nombreDeCouleurs() {
        return liste.size();
//        return suits.size();
    }
    public void ajouterCouleur(Suit _couleur) {
        // recuperer les valeurs dans le formulaire
        // et ajouter le nouvel etudiant corrrespondant
        liste.add(_couleur);
//        suits.add(_couleur);
    }
    public void supprimerCouleurs(EnumList<Suit> _couleurs) {
        for (Suit s: _couleurs) {
//            if (suits.containsObj(s)) {
//                int i_ = suits.indexOfObj(s);
//                modeleListeCouleurs.removeElementAt(i_);
//                suits.removeObj(s);
//            }
            int index_ = 0;
            for (Suit v: liste.getList()) {
                if (v == s) {
                    liste.remove(index_);
                    break;
                }
                index_++;
            }
        }
    }
    public void toutSupprimer() {
        liste.clearRevalidate();
//        suits.clear();
    }
    public EnumList<Suit> getCouleursSelectionnees() {
        if(liste.isSelectionEmpty()) {
            return new EnumList<Suit>();
        }
        EnumList<Suit> valeurs_=new EnumList<Suit>();
        for (Suit s: liste.getSelectedValuesLs()) {
            valeurs_.add(s);
        }
        //Appelee 2 fois
        return valeurs_;
    }

}
