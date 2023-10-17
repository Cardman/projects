package cards.gui.panels;


import cards.consts.Suit;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.selection.SuitCellRenderer;
import cards.main.CardFactories;
import code.gui.AbsPlainLabel;
import code.gui.GuiConstants;
import code.gui.ScrollCustomGraphicList;
import code.gui.images.MetaDimension;
import code.util.IdList;
import code.util.StringMap;
import code.util.core.IndexConstants;

/**
 */
public final class SuitsScrollableList extends ScrollableList {
    private static final String ACCESS = "cards.gui.panels.suitsscrollablelist";
    private static final String SUITS = "suits";
    private StringMap<String> messages = new StringMap<String>();
//    private EnumList<Suit> suits;
    private final ScrollCustomGraphicList<Suit> liste;
    public SuitsScrollableList(IdList<Suit> _couleurs, int _nb, WindowCardsInt _window) {
        super(_window.getCompoFactory());
        String lg_ = _window.getLanguageKey();
        messages = WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, lg_, ACCESS);
        AbsPlainLabel titrePanneau_ = _window.getCompoFactory().newPlainLabel(messages.getVal(SUITS));
        getContainer().add(titrePanneau_, GuiConstants.BORDER_LAYOUT_NORTH);
//        suits = _couleurs;
        liste= CardFactories.suit(_window.getCompoFactory(), _window.getImageFactory(),new SuitCellRenderer(_window));
        for (Suit couleur_ : _couleurs) {
            liste.add(couleur_);
        }
        //On peut selectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        liste.getScrollPane().setPreferredSize(new MetaDimension(100,10* _nb));
        getContainer().add(liste.getScrollPane(), GuiConstants.BORDER_LAYOUT_CENTER);
    }
    public IdList<Suit> getCouleurs() {
        int s_ = liste.size();
        IdList<Suit> valeurs_=new IdList<Suit>();
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
    public void supprimerCouleurs(IdList<Suit> _couleurs) {
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
    public IdList<Suit> getCouleursSelectionnees() {
        if(liste.isSelectionEmpty()) {
            return new IdList<Suit>();
        }
        IdList<Suit> valeurs_=new IdList<Suit>();
        for (int i: liste.getSelectedIndexes()) {
            valeurs_.add(liste.get(i));
        }
        //Appelee 2 fois
        return valeurs_;
    }

}
