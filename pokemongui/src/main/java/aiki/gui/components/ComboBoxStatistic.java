package aiki.gui.components;
import code.gui.ComboBox;
import aiki.fight.enums.Statistic;

public final class ComboBoxStatistic extends ComboBox<Statistic> {

//    @Override
//    public void refresh(List<Statistic> _order, String _language,
//            boolean _sortStrings) {
//        if (_sortStrings) {
//            List<Statistic> sortedByString_ = new List<Statistic>(_order);
////            sortedByString_.sort(new Comparator<Statistic>(){
////                public int compare(Statistic _o1, Statistic _o2) {
////                    Map<Statistic,String> tr_;
////                    tr_ = getFacade().getData().getTranslatedStatistics().getVal(Constants.getLanguage());
////                    return tr_.getVal(_o1).compareTo(tr_.getVal(_o2));
////                }
////            });
//            sortedByString_.sortElts(new ComparatorStatisticTr(getFacade().getData(), Constants.getLanguage()));
//            Map<Statistic,String> tr_;
//            tr_ = getFacade().getData().getTranslatedStatistics().getVal(Constants.getLanguage());
//            refresh(sortedByString_, tr_);
//        } else {
//            Map<Statistic,String> tr_;
//            tr_ = getFacade().getData().getTranslatedStatistics().getVal(Constants.getLanguage());
//            refresh(_order, tr_);
//        }
//    }

}
