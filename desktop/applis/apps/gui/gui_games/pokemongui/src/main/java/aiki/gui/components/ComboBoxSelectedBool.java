package aiki.gui.components;
import code.gui.ComboBox;
import aiki.facade.enums.SelectedBoolean;

public final class ComboBoxSelectedBool extends ComboBox<SelectedBoolean> {

//    @Override
//    public void refresh(List<SelectedBoolean> _order, String _language, boolean _sortStrings) {
//
//
//        //add elements to the combo box
//
//        if (_sortStrings) {
//            List<SelectedBoolean> sortedByString_ = new List<SelectedBoolean>(_order);
////            sortedByString_.sort(new Comparator<SelectedBoolean>(){
////                @Override
////                public int compare(SelectedBoolean _o1, SelectedBoolean _o2) {
////                    Map<SelectedBoolean,String> tr_;
////                    tr_ = getFacade().getTranslatedBooleansCurLanguage();
////                    return tr_.getVal(_o1).compareTo(tr_.getVal(_o2));
////                }
////            });
//            sortedByString_.sortElts(new ComparatorTrString<SelectedBoolean>(getFacade().getTranslatedBooleansCurLanguage()));
//            Map<SelectedBoolean,String> tr_;
//            tr_ = getFacade().getTranslatedBooleansCurLanguage();
//            refresh(sortedByString_, tr_);
//        } else {
//            Map<SelectedBoolean,String> tr_;
//            tr_ = getFacade().getTranslatedBooleansCurLanguage();
//            refresh(_order, tr_);
//        }
//    }
}
