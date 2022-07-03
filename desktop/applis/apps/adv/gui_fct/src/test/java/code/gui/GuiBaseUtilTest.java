package code.gui;

import code.mock.*;
import code.util.CustList;
import org.junit.Test;

public class GuiBaseUtilTest extends EquallableGuiFctUtil {
    @Test
    public void pack() {
        MockPanel mockPanel_ = new MockPanel(MockLayout.GRID);
        mockPanel_.add(new MockPlainLabel("_"));
        MockPanel sub_ = new MockPanel(MockLayout.GRID);
        sub_.add(new MockPlainLabel("-"));
        sub_.add(new MockScrollPane(new MockTextArea("_")));
        mockPanel_.add(sub_);
        GuiBaseUtil.recalculate(mockPanel_);
        CustList<MockCustComponent> accessible_ = mockPanel_.getAccessible();
        assertEq(2, accessible_.size());
        assertTrue(accessible_.get(0) instanceof MockPlainLabel);
        assertTrue(accessible_.get(1) instanceof MockPanel);
        CustList<MockCustComponent> suAcc_ = accessible_.get(1).getAccessible();
        assertEq(2, suAcc_.size());
        assertTrue(suAcc_.get(0) instanceof MockPlainLabel);
        assertTrue(suAcc_.get(1) instanceof MockScrollPane);
        CustList<MockCustComponent> suSuAcc_ = suAcc_.get(1).getAccessible();
        assertEq(1, suSuAcc_.size());
        assertTrue(suSuAcc_.get(0) instanceof MockTextArea);
    }
    @Test
    public void indexOf() {
        assertEq(-1, GuiBaseUtil.indexOf(new CustList<AbsCustComponent>(),null));
    }

}
