package code.gui;

import org.junit.Test;

public class GrMultListTest extends EquallableIntGuiUtil {
    @Test
    public void gm() {
        GrMultList g_ = new GrMultList(new GraphicListSample());
        g_.add("");
        g_.add(0,"");
        g_.add(0,null,"");
        g_.clear();
        g_.getList();
        g_.clear();
        g_.clearAllRange();
        g_.clearRevalidate();
        g_.clearSelection();
        g_.set(0,"");
        g_.set(0,null,"");
        g_.remove(0);
        g_.get(0);
        g_.getGlobal();
        g_.getSelectedIndex();
        g_.getSelectedIndexes();
        g_.getSelectedValuesLsLen();
        g_.setSelectedIndice(0);
        g_.setListener(null);
        g_.isEmpty();
        g_.isSelectionEmpty();
        g_.setVisibleRowCount(0);
        g_.last();
        g_.scroll();
        g_.self();
        g_.visible();
        assertEq(0, g_.size());
    }
}
