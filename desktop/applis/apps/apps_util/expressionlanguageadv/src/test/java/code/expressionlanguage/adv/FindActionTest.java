package code.expressionlanguage.adv;

import code.gui.GuiBaseUtil;
import code.gui.GuiConstants;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import org.junit.Test;

public final class FindActionTest extends EquallableElAdvUtil {
    @Test
    public void action1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        ((MockAbstractAction)GuiBaseUtil.getAction(w_.getCenter(), GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK)).action();
        assertTrue(((MockCustComponent)w_.getFinder()).isAccessible());
        assertTrue(((MockCustComponent)w_.getCloseFinder()).isAccessible());
        assertEq("",w_.getFinder().getText());
        assertEq(0,((MockTextPane)w_.getCenter()).getAttrSets().size());
    }
    @Test
    public void action2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getCenter().setText("hello");
        w_.getCenter().select(2,3);
        ((MockAbstractAction)GuiBaseUtil.getAction(w_.getCenter(), GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK)).action();
        assertTrue(((MockCustComponent)w_.getFinder()).isAccessible());
        assertTrue(((MockCustComponent)w_.getCloseFinder()).isAccessible());
        assertEq("l",w_.getFinder().getText());
        assertEq(5,((MockTextPane)w_.getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getCenter()).getAttrSets().getValue(0).size());
        assertEq(0,((MockTextPane)w_.getCenter()).getAttrSets().getValue(1).size());
        assertEq(1,((MockTextPane)w_.getCenter()).getAttrSets().getValue(2).size());
        assertEq(1,((MockTextPane)w_.getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getCenter()).getAttrSets().getValue(4).size());
    }
    @Test
    public void action3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getCenter().setText("hello");
        w_.getCenter().select(2,3);
        ((MockAbstractAction)GuiBaseUtil.getAction(w_.getCenter(), GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK)).action();
        ((MockPlainButton)w_.getCloseFinder()).getActionListeners().get(0).action();
        assertFalse(w_.getFinderPanel().isVisible());
    }
    @Test
    public void action4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        WindowCdmEditor w_ =window(pr_);
        w_.getCenter().setText("hello");
        w_.getCenter().select(2,3);
        ((MockAbstractAction)GuiBaseUtil.getAction(w_.getCenter(), GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK)).action();
        ((MockPlainButton)w_.getCloseFinder()).getActionListeners().get(0).action();
        w_.getCenter().select(1,2);
        ((MockAbstractAction)GuiBaseUtil.getAction(w_.getCenter(), GuiConstants.VK_F,GuiConstants.CTRL_DOWN_MASK)).action();
        assertEq("e",w_.getFinder().getText());
        assertEq(5,((MockTextPane)w_.getCenter()).getAttrSets().size());
        assertEq(0,((MockTextPane)w_.getCenter()).getAttrSets().getValue(0).size());
        assertEq(1,((MockTextPane)w_.getCenter()).getAttrSets().getValue(1).size());
        assertEq(0,((MockTextPane)w_.getCenter()).getAttrSets().getValue(2).size());
        assertEq(0,((MockTextPane)w_.getCenter()).getAttrSets().getValue(3).size());
        assertEq(0,((MockTextPane)w_.getCenter()).getAttrSets().getValue(4).size());
    }
}
