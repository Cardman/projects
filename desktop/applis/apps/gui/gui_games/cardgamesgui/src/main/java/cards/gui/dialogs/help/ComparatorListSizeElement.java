package cards.gui.dialogs.help;
import code.util.core.IndexConstants;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorListSizeElement implements Comparing<HelpIndexes> {

    @Override
    public int compare(HelpIndexes _arg0, HelpIndexes _arg1) {
        if (_arg0.size() < _arg1.size()) {
            return SortConstants.NO_SWAP_SORT;
        }
        if (_arg0.size() > _arg1.size()) {
            return SortConstants.SWAP_SORT;
        }
        int len_ = _arg0.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (_arg0.get(i) < _arg1.get(i)) {
                return SortConstants.NO_SWAP_SORT;
            }
            if (_arg0.get(i) > _arg1.get(i)) {
                return SortConstants.SWAP_SORT;
            }
        }
        return SortConstants.EQ_CMP;
    }
}
