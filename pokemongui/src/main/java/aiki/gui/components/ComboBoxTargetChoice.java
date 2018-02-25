package aiki.gui.components;
import aiki.fight.moves.enums.TargetChoice;
import code.gui.ComboBox;

public final class ComboBoxTargetChoice extends ComboBox<TargetChoice> {

//    @Override
//    public void refresh(List<TargetChoice> _order, String _language,
//            boolean _sortStrings) {
//        if (_sortStrings) {
//            List<TargetChoice> sortedByString_ = new List<TargetChoice>(_order);
////            sortedByString_.sort(new Comparator<TargetChoice>(){
////                public int compare(TargetChoice _o1, TargetChoice _o2) {
////                    Map<TargetChoice,String> tr_;
////                    tr_ = getFacade().getData().getTranslatedTargets().getVal(Constants.getLanguage());
////                    return tr_.getVal(_o1).compareTo(tr_.getVal(_o2));
////                }
////            });
//            sortedByString_.sortElts(new ComparatorTrString<TargetChoice>(getFacade().getData().getTranslatedTargets().getVal(Constants.getLanguage())));
//            Map<TargetChoice,String> tr_;
//            tr_ = getFacade().getData().getTranslatedTargets().getVal(Constants.getLanguage());
//            refresh(sortedByString_, tr_);
//        } else {
//            Map<TargetChoice,String> tr_;
//            tr_ = getFacade().getData().getTranslatedTargets().getVal(Constants.getLanguage());
//            refresh(_order, tr_);
//        }
//    }

}
