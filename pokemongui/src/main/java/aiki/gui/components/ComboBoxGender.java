package aiki.gui.components;
import aiki.map.pokemon.enums.Gender;
import code.gui.ComboBox;

public final class ComboBoxGender extends ComboBox<Gender> {

//    @Override
//    public void refresh(List<Gender> _order, String _language, boolean _sortStrings) {
//        if (_sortStrings) {
//            List<Gender> sortedByString_ = new List<Gender>(_order);
////            sortedByString_.sort(new Comparator<Gender>(){
////                public int compare(Gender _o1, Gender _o2) {
////                    Map<Gender,String> tr_;
////                    tr_ = getFacade().getTranslatedGendersCurLanguage();
////                    return tr_.getVal(_o1).compareTo(tr_.getVal(_o2));
////                }
////            });
//            sortedByString_.sortElts(new ComparatorTrString<Gender>(getFacade().getTranslatedGendersCurLanguage()));
//            Map<Gender,String> tr_;
//            tr_ = getFacade().getTranslatedGendersCurLanguage();
//            refresh(sortedByString_, tr_);
//        } else {
//            Map<Gender,String> tr_;
//            tr_ = getFacade().getTranslatedGendersCurLanguage();
//            refresh(_order, tr_);
//        }
//    }

}
