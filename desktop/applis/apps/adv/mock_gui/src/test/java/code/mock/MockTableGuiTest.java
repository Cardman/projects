package code.mock;

import code.gui.events.AbsMouseListener;
import code.gui.events.AbsMouseListenerCl;
import org.junit.Test;

public final class MockTableGuiTest extends EquallableMockGuiUtil{
    @Test
    public void t1() {
        MockTableGui ta_ = new MockTableGui("0", "1");
        assertEq(0,ta_.getRowCount());
        assertEq(2,ta_.getColumnCount());
        assertEq("0",ta_.getColumnName(0));
        assertEq("1",ta_.getColumnName(1));
    }
    @Test
    public void t2() {
        MockTableGui ta_ = new MockTableGui("0", "1");
        ta_.setRowCount(1);
        assertEq(2,ta_.getColumnCount());
        assertEq("0",ta_.getColumnName(0));
        assertEq("1",ta_.getColumnName(1));
        assertEq(1,ta_.getRowCount());
        assertEq("",ta_.getValueAt(0,0));
        assertEq("",ta_.getValueAt(0,1));
    }
    @Test
    public void t3() {
        MockTableGui ta_ = new MockTableGui("0", "1");
        ta_.setRowCount(1);
        ta_.setRowCount(1);
        assertEq(2,ta_.getColumnCount());
        assertEq("0",ta_.getColumnName(0));
        assertEq("1",ta_.getColumnName(1));
        assertEq(1,ta_.getRowCount());
        assertEq("",ta_.getValueAt(0,0));
        assertEq("",ta_.getValueAt(0,1));
    }
    @Test
    public void t4() {
        MockTableGui ta_ = new MockTableGui("0", "1");
        ta_.setRowCount(1);
        ta_.setRowCount(0);
        assertEq(2,ta_.getColumnCount());
        assertEq("0",ta_.getColumnName(0));
        assertEq("1",ta_.getColumnName(1));
        assertEq(0,ta_.getRowCount());
    }
    @Test
    public void t5() {
        MockTableGui ta_ = new MockTableGui("0", "1");
        ta_.setRowCount(8);
        assertEq(2,ta_.getColumnCount());
        assertEq("0",ta_.getColumnName(0));
        assertEq("1",ta_.getColumnName(1));
        ta_.addSelectInterval(3,6);
        int[] sel_ = ta_.getSelectedRows();
        assertEq(4, sel_.length);
        assertEq(3, sel_[0]);
        assertEq(4, sel_[1]);
        assertEq(5, sel_[2]);
        assertEq(6, sel_[3]);
        assertEq(4, ta_.getSelectedRowCount());
    }
    @Test
    public void t6() {
        MockTableGui ta_ = new MockTableGui("0", "1");
        ta_.setRowCount(8);
        assertEq(2,ta_.getColumnCount());
        assertEq("0",ta_.getColumnName(0));
        assertEq("1",ta_.getColumnName(1));
        ta_.addSelectInterval(3,6);
        ta_.removeSelectInterval(4,5);
        int[] sel_ = ta_.getSelectedRows();
        assertEq(2, sel_.length);
        assertEq(3, sel_[0]);
        assertEq(6, sel_[1]);
        assertEq(2, ta_.getSelectedRowCount());
    }
    @Test
    public void t7() {
        MockTableGui ta_ = new MockTableGui("0", "1");
        ta_.setRowCount(8);
        assertEq(2,ta_.getColumnCount());
        assertEq("0",ta_.getColumnName(0));
        assertEq("1",ta_.getColumnName(1));
        ta_.addSelectInterval(3,6);
        ta_.removeSelectInterval(4,5);
        assertEq(3, ta_.getSelectedRow());
    }
    @Test
    public void t8() {
        MockTableGui ta_ = new MockTableGui("0", "1");
        ta_.setRowCount(8);
        assertEq(2,ta_.getColumnCount());
        assertEq("0",ta_.getColumnName(0));
        assertEq("1",ta_.getColumnName(1));
        ta_.addSelectInterval(3,6);
        ta_.removeSelectInterval(3,6);
        assertEq(-1, ta_.getSelectedRow());
    }
    @Test
    public void t9() {
        MockTableGui ta_ = new MockTableGui("0", "1","2","3","4","5");
        ta_.setRowCount(1);
        ta_.setValueAt("6",0,0);
        ta_.setValueAt("7",0,1);
        ta_.setValueAt("8",0,2);
        ta_.setValueAt("9",0,3);
        ta_.setValueAt("10",0,4);
        ta_.setValueAt("11",0,5);
        ta_.moveColumn(1,4);
        assertEq("6",ta_.getValueAt(0,0));
        assertEq("8",ta_.getValueAt(0,1));
        assertEq("9",ta_.getValueAt(0,2));
        assertEq("10",ta_.getValueAt(0,3));
        assertEq("7",ta_.getValueAt(0,4));
        assertEq("11",ta_.getValueAt(0,5));
        assertEq("0",ta_.getColumnName(0));
        assertEq("2",ta_.getColumnName(1));
        assertEq("3",ta_.getColumnName(2));
        assertEq("4",ta_.getColumnName(3));
        assertEq("1",ta_.getColumnName(4));
        assertEq("5",ta_.getColumnName(5));
    }
    @Test
    public void t10() {
        MockTableGui ta_ = new MockTableGui("0", "1","2","3","4","5");
        ta_.setRowCount(1);
        ta_.setValueAt("6",0,0);
        ta_.setValueAt("7",0,1);
        ta_.setValueAt("8",0,2);
        ta_.setValueAt("9",0,3);
        ta_.setValueAt("10",0,4);
        ta_.setValueAt("11",0,5);
        ta_.moveColumn(4,1);
        assertEq("6",ta_.getValueAt(0,0));
        assertEq("10",ta_.getValueAt(0,1));
        assertEq("7",ta_.getValueAt(0,2));
        assertEq("8",ta_.getValueAt(0,3));
        assertEq("9",ta_.getValueAt(0,4));
        assertEq("11",ta_.getValueAt(0,5));
        assertEq("0",ta_.getColumnName(0));
        assertEq("4",ta_.getColumnName(1));
        assertEq("1",ta_.getColumnName(2));
        assertEq("2",ta_.getColumnName(3));
        assertEq("3",ta_.getColumnName(4));
        assertEq("5",ta_.getColumnName(5));
    }
    @Test
    public void t11() {
        MockTableGui ta_ = new MockTableGui("0", "1","2","3","4","5");
        ta_.setRowCount(1);
        ta_.setValueAt("6",0,0);
        ta_.setValueAt("7",0,1);
        ta_.setValueAt("8",0,2);
        ta_.setValueAt("9",0,3);
        ta_.setValueAt("10",0,4);
        ta_.setValueAt("11",0,5);
        ta_.moveColumn(1,1);
        assertEq("6",ta_.getValueAt(0,0));
        assertEq("7",ta_.getValueAt(0,1));
        assertEq("8",ta_.getValueAt(0,2));
        assertEq("9",ta_.getValueAt(0,3));
        assertEq("10",ta_.getValueAt(0,4));
        assertEq("11",ta_.getValueAt(0,5));
        assertEq("0",ta_.getColumnName(0));
        assertEq("1",ta_.getColumnName(1));
        assertEq("2",ta_.getColumnName(2));
        assertEq("3",ta_.getColumnName(3));
        assertEq("4",ta_.getColumnName(4));
        assertEq("5",ta_.getColumnName(5));
    }
    @Test
    public void t12() {
        MockTableGui ta_ = new MockTableGui("0");
        ta_.setMultiSelect(true);
        assertTrue(ta_.isMultiSelect());
    }
    @Test
    public void t13() {
        MockTableGui ta_ = new MockTableGui("0");
        ta_.setMultiSelect(false);
        assertFalse(ta_.isMultiSelect());
    }
    @Test
    public void t14() {
        MockTableGui ta_ = new MockTableGui("0");
        assertEq(3,ta_.columnAtPoint(3,4));
        assertEq(4,ta_.rowAtPoint(3,4));
    }
    @Test
    public void t15() {
        MockTableGui ta_ = new MockTableGui("0");
        ta_.setReorderingAllowed(true);
        assertTrue(ta_.isReorderingAllowed());
        ta_.applyChanges();
    }
    @Test
    public void t16() {
        MockTableGui ta_ = new MockTableGui("0");
        ta_.addHeaderListener((AbsMouseListener) null);
        ta_.addHeaderListener((AbsMouseListenerCl) null);
        ta_.addListSelectionListener(null);
        assertEq(1, ta_.getHeadersCl().size());
        assertEq(1, ta_.getHeaders().size());
        assertEq(1, ta_.getSelection().size());
    }
}
