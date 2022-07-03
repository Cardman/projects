package code.gui;

import org.junit.Test;

public class SelectionInfoTest extends EquallableIntGuiUtil {
    @Test
    public void t1() {
        SelectionInfo cur_ = new SelectionInfo(1, 1,true);
        assertTrue(cur_.isMethodAction());
        assertEq(1, cur_.getFirstIndex()* cur_.getLastIndex());
    }
}
