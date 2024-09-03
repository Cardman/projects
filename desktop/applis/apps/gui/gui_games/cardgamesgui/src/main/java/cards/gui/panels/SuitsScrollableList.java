package cards.gui.panels;


import cards.consts.Suit;
import cards.gui.WindowCardsInt;
import cards.gui.labels.selection.SuitCellRenderer;
import cards.main.CardFactories;
import code.gui.AbsPlainLabel;
import code.gui.files.MessagesGuiFct;
import code.gui.images.MetaDimension;
import code.util.IdList;

/**
 */
public final class SuitsScrollableList extends ScrollableList<Suit> {
//    private EnumList<Suit> suits;
    public SuitsScrollableList(IdList<Suit> _couleurs, int _nb, WindowCardsInt _window, String _title) {
        super(_window.getFrames(),CardFactories.suit(_window.getCompoFactory(), _window.getImageFactory(),new SuitCellRenderer(_window)));
        AbsPlainLabel titrePanneau_ = _window.getCompoFactory().newPlainLabel(_title);
        getContainer().add(titrePanneau_, MessagesGuiFct.BORDER_LAYOUT_NORTH);
//        suits = _couleurs;
        for (Suit couleur_ : _couleurs) {
            getListe().add(couleur_);
        }
        //On peut selectionner plusieurs elements dans la liste listeCouleurs en
        //utilisant "ctrl + A", "ctrl", "maj+clic", comme dans explorer
        getListe().getScrollPane().setPreferredSize(new MetaDimension(100,10* _nb));
        getContainer().add(getListe().getScrollPane(), MessagesGuiFct.BORDER_LAYOUT_CENTER);
    }
    public void ajouterCouleur(Suit _couleur) {
        // recuperer les valeurs dans le formulaire
        // et ajouter le nouvel etudiant corrrespondant
        getListe().add(_couleur);
//        suits.add(_couleur);
    }
    public void supprimerCouleurs() {
        supprimerElts();
//        for (Suit s: _couleurs) {
////            if (suits.containsObj(s)) {
////                int i_ = suits.indexOfObj(s);
////                modeleListeCouleurs.removeElementAt(i_);
////                suits.removeObj(s);
////            }
//            int index_ = 0;
//            for (Suit v: liste.getList()) {
//                if (v == s) {
//                    liste.remove(index_);
//                    break;
//                }
//                index_++;
//            }
//        }
    }
    public void toutSupprimer() {
        getListe().clearRevalidate();
//        suits.clear();
    }
}
