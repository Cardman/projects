package code.expressionlanguage.adv;

import code.mock.*;
import org.junit.Test;

public final class ChgSegmentPartEventTest extends EquallableElAdvUtil {
    @Test
    public void previous() {
        WindowCdmEditor w_=newWindowLoadDef();
        style(w_);
        tabEditor(w_).getCenter().select(3,4);
        findText(w_);
        assertTrue(((MockCustComponent) tabEditor(w_).getPrevOcc()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getNextOcc()).isDeepAccessible());
        ((MockPlainButton)tabEditor(w_).getPrevOcc()).getActionListeners().get(0).action();
        assertEq("l", tabEditor(w_).getFinder().getText());
        assertEq(11,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(3, tabEditor(w_).getParts().size());
        assertEq(2, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(3, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(3, tabEditor(w_).getParts().get(1).getBegin());
        assertEq(4, tabEditor(w_).getParts().get(1).getEnd());
        assertEq(9, tabEditor(w_).getParts().get(2).getBegin());
        assertEq(10, tabEditor(w_).getParts().get(2).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
        assertEq(2, tabEditor(w_).getCenter().getSelectionStart());
        assertEq(3, tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void next() {
        WindowCdmEditor w_=newWindowLoadDef();
        style(w_);
        tabEditor(w_).getCenter().select(3,4);
        findText(w_);
        assertTrue(((MockCustComponent) tabEditor(w_).getPrevOcc()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getNextOcc()).isDeepAccessible());
        ((MockPlainButton)tabEditor(w_).getNextOcc()).getActionListeners().get(0).action();
        assertEq("l", tabEditor(w_).getFinder().getText());
        assertEq(11,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(3, tabEditor(w_).getParts().size());
        assertEq(2, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(3, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(3, tabEditor(w_).getParts().get(1).getBegin());
        assertEq(4, tabEditor(w_).getParts().get(1).getEnd());
        assertEq(9, tabEditor(w_).getParts().get(2).getBegin());
        assertEq(10, tabEditor(w_).getParts().get(2).getEnd());
        assertEq(2, tabEditor(w_).getCurrentPart());
        assertEq(9, tabEditor(w_).getCenter().getSelectionStart());
        assertEq(10, tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void previousBack() {
        WindowCdmEditor w_=newWindowLoadDef();
        style(w_);
        tabEditor(w_).getCenter().select(2,3);
        findText(w_);
        assertTrue(((MockCustComponent) tabEditor(w_).getPrevOcc()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getNextOcc()).isDeepAccessible());
        ((MockPlainButton)tabEditor(w_).getPrevOcc()).getActionListeners().get(0).action();
        assertEq("l", tabEditor(w_).getFinder().getText());
        assertEq(11,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(3, tabEditor(w_).getParts().size());
        assertEq(2, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(3, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(3, tabEditor(w_).getParts().get(1).getBegin());
        assertEq(4, tabEditor(w_).getParts().get(1).getEnd());
        assertEq(9, tabEditor(w_).getParts().get(2).getBegin());
        assertEq(10, tabEditor(w_).getParts().get(2).getEnd());
        assertEq(2, tabEditor(w_).getCurrentPart());
        assertEq(9, tabEditor(w_).getCenter().getSelectionStart());
        assertEq(10, tabEditor(w_).getCenter().getSelectionEnd());
    }
    @Test
    public void nextAfter() {
        WindowCdmEditor w_=newWindowLoadDef();
        style(w_);
        tabEditor(w_).getCenter().select(9,10);
        findText(w_);
        assertTrue(((MockCustComponent) tabEditor(w_).getPrevOcc()).isDeepAccessible());
        assertTrue(((MockCustComponent) tabEditor(w_).getNextOcc()).isDeepAccessible());
        ((MockPlainButton)tabEditor(w_).getNextOcc()).getActionListeners().get(0).action();
        assertEq("l", tabEditor(w_).getFinder().getText());
        assertEq(11,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(4).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(5).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(6).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(7).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(8).size());
        assertEq(1,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(9).size());
        assertEq(0,((MockTextPane) tabEditor(w_).getCenter()).getAttrSets().getValue(10).size());
        assertEq(3, tabEditor(w_).getParts().size());
        assertEq(2, tabEditor(w_).getParts().get(0).getBegin());
        assertEq(3, tabEditor(w_).getParts().get(0).getEnd());
        assertEq(3, tabEditor(w_).getParts().get(1).getBegin());
        assertEq(4, tabEditor(w_).getParts().get(1).getEnd());
        assertEq(9, tabEditor(w_).getParts().get(2).getBegin());
        assertEq(10, tabEditor(w_).getParts().get(2).getEnd());
        assertEq(0, tabEditor(w_).getCurrentPart());
        assertEq(2, tabEditor(w_).getCenter().getSelectionStart());
        assertEq(3, tabEditor(w_).getCenter().getSelectionEnd());
    }

    private void style(WindowCdmEditor _w) {
        tabEditor(_w).getCenter().setText("hello world");
        executeOneTask(tabEditor(_w).getTaskManager());
    }
}
