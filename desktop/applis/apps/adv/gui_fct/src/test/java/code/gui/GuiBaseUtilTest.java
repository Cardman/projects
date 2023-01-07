package code.gui;

import code.gui.events.*;
import code.gui.initialize.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class GuiBaseUtilTest extends EquallableGuiFctUtil {

    @Test
    public void eq1() {
        MockImage img_ = new MockImage(new int[2][3]);
        MockImage img2_ = new MockImage(new int[2][3]);
        assertTrue(GuiBaseUtil.eq(img_,img2_));
    }
    @Test
    public void eq2() {
        MockImage img_ = new MockImage(new int[2][3]);
        MockImage img2_ = new MockImage(new int[2][3]);
        img_.setRGB(1,2,29);
        assertFalse(GuiBaseUtil.eq(img_,img2_));
    }
    @Test
    public void eq3() {
        MockImage img_ = new MockImage(new int[2][3]);
        MockImage img2_ = new MockImage(new int[2][4]);
        assertFalse(GuiBaseUtil.eq(img_,img2_));
    }
    @Test
    public void eq4() {
        MockImage img_ = new MockImage(new int[2][3]);
        MockImage img2_ = new MockImage(new int[4][3]);
        assertFalse(GuiBaseUtil.eq(img_,img2_));
    }
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

    @Test
    public void prep() {
        AbsPreparedLabel l_ = GuiBaseUtil.prep(new MockImageFactory());
        assertEq(1, l_.getWidth());
        assertEq(1, l_.getHeight());
    }
    @Test
    public void invokeLater() {
        MockProgramInfosSecSample init_ = init();
        MockRunnable r_ = new MockRunnable();
        AbsCompoFactory ab_ = init_.getCompoFactory();
        GuiBaseUtil.invokeLater(r_,init_);
        assertFalse(r_.isStarted());
        ((MockCompoFactory)ab_).invoke();
        assertTrue(r_.isStarted());
    }
    @Test
    public void setOrient1() {
        AbsCompoFactory init_ = init().getCompoFactory();
        AbsSlider s_ = init_.newAbsSlider();
        GuiBaseUtil.setOrient(s_,GuiConstants.HORIZONTAL);
        assertEq(GuiConstants.HORIZONTAL,s_.getOrientation());
    }
    @Test
    public void setOrient2() {
        AbsCompoFactory init_ = init().getCompoFactory();
        AbsSlider s_ = init_.newAbsSlider();
        GuiBaseUtil.setOrient(s_,GuiConstants.VERTICAL);
        assertEq(GuiConstants.VERTICAL,s_.getOrientation());
    }
    @Test
    public void setOrientPr1() {
        AbsCompoFactory init_ = init().getCompoFactory();
        AbsProgressBar s_ = init_.newAbsProgressBar();
        GuiBaseUtil.setHorizProg(s_,true);
        assertTrue(s_.isHorizontal());
    }
    @Test
    public void setOrientPr2() {
        AbsCompoFactory init_ = init().getCompoFactory();
        AbsProgressBar s_ = init_.newAbsProgressBar();
        GuiBaseUtil.setHorizProg(s_,false);
        assertFalse(s_.isHorizontal());
    }
    @Test
    public void setSelectTable1() {
        AbsCompoFactory init_ = init().getCompoFactory();
        AbsTableGui s_ = init_.newTableGui("_");
        GuiBaseUtil.setSelectTable(s_,true);
        assertTrue(s_.isMultiSelect());
    }
    @Test
    public void setSelectTable2() {
        AbsCompoFactory init_ = init().getCompoFactory();
        AbsTableGui s_ = init_.newTableGui("_");
        GuiBaseUtil.setSelectTable(s_,false);
        assertFalse(s_.isMultiSelect());
    }
}
