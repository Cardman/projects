package code.gui;

import code.gui.events.MockProgramInfosSecSample;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.mock.*;
import org.junit.Test;

public final class ScrollCustomComboTest extends EquallableGuiFctUtil {
    @Test
    public void t1() {
        ScrollCustomCombo gene_ = gene();
        ((MockCustComponent)gene_.getSelected()).setFocused(true);
        gene_.getSelected().getFocusListeners().get(0).focusGained();
        ((MockCustComponent)gene_.getSelected()).setFocused(false);
        gene_.getSelected().getFocusListeners().get(0).focusLost();
        gene_.repaint();
        assertEq(0, gene_.size());
        assertTrue(gene_.isEmpty());
    }
    @Test
    public void t2() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        assertEq(1, gene_.size());
        assertEq("ONE", gene_.getList().get(0));
        assertFalse(gene_.isEmpty());
        assertEq(1,gene_.getList().getList().size());
        assertEq("ONE",gene_.getList().getList().get(0));
    }
    @Test
    public void t3() {
        ScrollCustomCombo gene_ = gene();
        click(gene_);
        assertTrue(gene_.getPopupMenu().isVisible());
    }
    @Test
    public void t4() {
        ScrollCustomCombo gene_ = gene();
        click(gene_);
        click(gene_);
        assertFalse(gene_.getPopupMenu().isVisible());
    }
    @Test
    public void t5() {
        ScrollCustomCombo gene_ = gene();
        action(gene_,GuiConstants.VK_F4);
        assertTrue(gene_.getPopupMenu().isVisible());
    }
    @Test
    public void t6() {
        ScrollCustomCombo gene_ = gene();
        action(gene_,GuiConstants.VK_F4);
        action(gene_,GuiConstants.VK_F4);
        assertFalse(gene_.getPopupMenu().isVisible());
    }
    @Test
    public void t7() {
        ScrollCustomCombo gene_ = gene();
        action(gene_,GuiConstants.VK_F4);
        action(gene_,GuiConstants.VK_ESCAPE);
        assertFalse(gene_.getPopupMenu().isVisible());
    }
    @Test
    public void t8() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        assertEq(2, gene_.size());
        assertEq("ONE", gene_.getList().get(0));
        assertEq("TWO", gene_.getList().get(1));
    }
    @Test
    public void t9() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.select(1);
        action(gene_,GuiConstants.VK_ENTER);
        assertFalse(gene_.getPopupMenu().isVisible());
        assertEq(1, gene_.getSelectedIndex());
        assertEq(1, gene_.getSelectedIndexes().size());
        assertEq(1, gene_.getSelectedIndexes().get(0));
    }
    @Test
    public void t10() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.select(-1);
        assertEq(0, gene_.getSelectedIndexes().size());
    }
    @Test
    public void t11() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.remove(1);
        assertEq(2, gene_.size());
        assertEq("ONE", gene_.getList().get(0));
        assertEq("THREE", gene_.getList().get(1));
    }
    @Test
    public void t12() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.remove(0);
        assertEq(2, gene_.size());
        assertEq("TWO", gene_.getList().get(0));
        assertEq("THREE", gene_.getList().get(1));
    }
    @Test
    public void t13() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.remove(2);
        assertEq(2, gene_.size());
        assertEq("ONE", gene_.getList().get(0));
        assertEq("TWO", gene_.getList().get(1));
    }
    @Test
    public void t14() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.remove(0);
        assertEq(0, gene_.size());
    }
    @Test
    public void t15() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(1);
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertTrue(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
    }
    @Test
    public void t16() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(1);
        gene_.remove(1);
        assertEq(0, gene_.getList().getFocused().getIndex());
        assertEq("ONE", gene_.getList().getFocused().getRow().getInfo());
    }
    @Test
    public void t17() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(0);
        assertTrue(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
    }
    @Test
    public void t18() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(0);
        gene_.remove(0);
        assertEq(0, gene_.getList().getFocused().getIndex());
        assertEq("TWO", gene_.getList().getFocused().getRow().getInfo());
    }
    @Test
    public void t19() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(2);
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertTrue(gene_.getList().getRow(2).isSelected());
    }
    @Test
    public void t20() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(2);
        gene_.remove(2);
        assertEq(1, gene_.getList().getFocused().getIndex());
        assertEq("TWO", gene_.getList().getFocused().getRow().getInfo());
    }
    @Test
    public void t21() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(2);
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertTrue(gene_.getList().getRow(2).isSelected());
    }
    @Test
    public void t22() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.select(0);
        gene_.remove(0);
        assertEq(-1, gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t23() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(-1);
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
    }
    @Test
    public void t27() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(1);
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertTrue(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertEq(1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t28() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.clear();
        assertEq(0,gene_.size());
    }
    @Test
    public void t29() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        click(gene_, 15);
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertTrue(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertEq(1,gene_.getList().getFocused().getIndex());
    }

    @Test
    public void t32() {
        ScrollCustomCombo gene_ = gene();
        click(gene_, 0);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t33() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        click(gene_, 15);
        click(gene_, 15);
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertTrue(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertEq(1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t52() {
        ScrollCustomCombo gene_ = gene();
        action(gene_,GuiConstants.VK_PAGE_UP);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t53() {
        ScrollCustomCombo gene_ = gene();
        action(gene_,GuiConstants.VK_PAGE_DOWN);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t54() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.remove(-1);
        assertEq(3, gene_.size());
        assertEq("ONE", gene_.getList().get(0));
        assertEq("TWO", gene_.getList().get(1));
        assertEq("THREE", gene_.getList().get(2));
    }
    @Test
    public void t60() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5);
        action(gene_,GuiConstants.VK_DOWN);
        assertEq(1,gene_.getList().getFocused().getIndex());
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertTrue(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
        assertFalse(gene_.getList().getRow(7).isSelected());
    }
    @Test
    public void t61() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5);
        action(gene_,GuiConstants.VK_DOWN);
        action(gene_,GuiConstants.VK_UP);
        assertEq(0,gene_.getList().getFocused().getIndex());
        assertTrue(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
        assertFalse(gene_.getList().getRow(7).isSelected());
    }
    @Test
    public void t62() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5);
        action(gene_,GuiConstants.VK_UP);
        assertEq(0,gene_.getList().getFocused().getIndex());
        assertTrue(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
        assertFalse(gene_.getList().getRow(7).isSelected());
    }
    @Test
    public void t63() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75);
        action(gene_,GuiConstants.VK_UP);
        assertEq(6,gene_.getList().getFocused().getIndex());
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(7).isSelected());
        assertTrue(gene_.getList().getRow(6).isSelected());
    }
    @Test
    public void t64() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75);
        action(gene_,GuiConstants.VK_UP);
        action(gene_,GuiConstants.VK_DOWN);
        assertEq(7,gene_.getList().getFocused().getIndex());
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertTrue(gene_.getList().getRow(7).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
    }
    @Test
    public void t65() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75);
        action(gene_,GuiConstants.VK_DOWN);
        assertEq(7,gene_.getList().getFocused().getIndex());
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertTrue(gene_.getList().getRow(7).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
    }
    @Test
    public void t78() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5);
        action(gene_,GuiConstants.VK_END);
        assertEq(7,gene_.getList().getFocused().getIndex());
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertTrue(gene_.getList().getRow(7).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
    }
    @Test
    public void t79() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5);
        action(gene_,GuiConstants.VK_END);
        action(gene_,GuiConstants.VK_HOME);
        assertEq(0,gene_.getList().getFocused().getIndex());
        assertTrue(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(7).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
    }
    @Test
    public void t80() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5);
        action(gene_,GuiConstants.VK_HOME);
        assertEq(0,gene_.getList().getFocused().getIndex());
        assertTrue(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(7).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
    }
    @Test
    public void t81() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75);
        action(gene_,GuiConstants.VK_HOME);
        assertEq(0,gene_.getList().getFocused().getIndex());
        assertFalse(gene_.getList().getRow(7).isSelected());
        assertTrue(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
    }
    @Test
    public void t82() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75);
        action(gene_,GuiConstants.VK_HOME);
        action(gene_,GuiConstants.VK_END);
        assertEq(7,gene_.getList().getFocused().getIndex());
        assertTrue(gene_.getList().getRow(7).isSelected());
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
    }
    @Test
    public void t83() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75);
        action(gene_,GuiConstants.VK_END);
        assertEq(7,gene_.getList().getFocused().getIndex());
        assertTrue(gene_.getList().getRow(7).isSelected());
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
    }
    @Test
    public void t84() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_HOME);
        assertEq(0,gene_.getList().getFocused().getIndex());
        assertTrue(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(7).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
    }
    @Test
    public void t85() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_END);
        assertEq(7,gene_.getList().getFocused().getIndex());
        assertTrue(gene_.getList().getRow(7).isSelected());
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
    }
    @Test
    public void t86() {
        ScrollCustomCombo gene_ = gene();
        action(gene_,GuiConstants.VK_HOME);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t87() {
        ScrollCustomCombo gene_ = gene();
        action(gene_,GuiConstants.VK_END);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t110() {
        ScrollCustomCombo gene_ = gene();
        located(gene_);
        gene_.getList().getGlobal().setPreferredSize(new MetaDimension(10,20));
        click(gene_,5);
        gene_.getList().getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_PAGE_DOWN);
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertTrue(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(6).isSelected());
        assertFalse(gene_.getList().getRow(7).isSelected());
    }
    @Test
    public void t111() {
        ScrollCustomCombo gene_ = gene();
        located(gene_);
        gene_.getList().getGlobal().setPreferredSize(new MetaDimension(10,20));
        click(gene_,75);
        gene_.getList().getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_PAGE_UP);
        assertFalse(gene_.getList().getRow(7).isSelected());
        assertTrue(gene_.getList().getRow(6).isSelected());
        assertFalse(gene_.getList().getRow(5).isSelected());
        assertFalse(gene_.getList().getRow(0).isSelected());
        assertFalse(gene_.getList().getRow(1).isSelected());
        assertFalse(gene_.getList().getRow(2).isSelected());
        assertFalse(gene_.getList().getRow(3).isSelected());
        assertFalse(gene_.getList().getRow(4).isSelected());
    }
    @Test
    public void t112() {
        ScrollCustomCombo gene_ = gene();
        gene_.getList().getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_DOWN);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t113() {
        ScrollCustomCombo gene_ = gene();
        gene_.getList().getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_UP);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t115() {
        ScrollCustomCombo gene_ = gene();
        MockWithListSelectionSample w_ = new MockWithListSelectionSample(init(), "");
        MockListSelection ls_ = new MockListSelection(0, w_);
        gene_.addListener(ls_);
        assertEq(1,gene_.getSelections().size());
        gene_.removeListener(ls_);
        assertEq(0,gene_.getSelections().size());
        gene_.setListener(ls_);
        assertEq(1,gene_.getSelections().size());
        gene_.setListener(null);
        assertEq(0,gene_.getSelections().size());
    }
    @Test
    public void t116() {
        ScrollCustomCombo gene_ = gene();
        gene_.setEnabled(true);
        assertTrue(gene_.isEnabled());
    }
    @Test
    public void t117() {
        ScrollCustomCombo gene_ = gene();
        gene_.setEnabled(false);
        assertFalse(gene_.isEnabled());
    }
    @Test
    public void t118() {
        ScrollCustomCombo gene_ = gene();
        gene_.setEnabled(true);
        assertTrue(gene_.isEnabled());
    }
    @Test
    public void t119() {
        ScrollCustomCombo gene_ = gene();
        gene_.setEnabled(false);
        assertFalse(gene_.isEnabled());
    }
    @Test
    public void t120() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.getList().getRow(0).focus(gene_.getList().getRow(0).isFocused());
        gene_.getList().getRow(0).anchor(gene_.getList().getRow(0).isAnchored());
        assertEq(1,gene_.forceRefresh());
        gene_.getList().getRow(0).focus(gene_.getList().getRow(0).isFocused());
        gene_.getList().getRow(0).anchor(gene_.getList().getRow(0).isAnchored());
        assertEq(1,gene_.forceRefresh());
    }
    @Test
    public void t121() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.select(0);
        gene_.setEnabled(false);
        click(gene_,15);
        assertFalse(gene_.getPopupMenu().isVisible());
    }
    @Test
    public void t122() {
        ScrollCustomCombo gene_ = gene();
        gene_.getList().getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_DOWN);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t123() {
        ScrollCustomCombo gene_ = gene();
        gene_.getList().getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_UP);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t124() {
        ScrollCustomCombo gene_ = gene();
        gene_.getList().getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_PAGE_DOWN);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t125() {
        ScrollCustomCombo gene_ = gene();
        gene_.getList().getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_PAGE_UP);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t126() {
        ScrollCustomCombo gene_ = gene();
        gene_.getList().getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_PAGE_DOWN);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t127() {
        ScrollCustomCombo gene_ = gene();
        gene_.getList().getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_PAGE_UP);
        assertEq(-1,gene_.getList().getFocused().getIndex());
    }
    @Test
    public void t155() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(2);
        gene_.remove(1);
        assertEq(1, gene_.getList().getFocused().getIndex());
        assertEq("THREE", gene_.getList().getFocused().getRow().getInfo());
    }
    @Test
    public void t156() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(0);
        gene_.remove(1);
        assertEq(0, gene_.getList().getFocused().getIndex());
        assertEq("ONE", gene_.getList().getFocused().getRow().getInfo());
    }
    @Test
    public void t172() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.clearRevalidate();
        assertEq(0, gene_.size());
        assertEq(0, gene_.getList().getSelectedValuesLsLen());
    }
    @Test
    public void t174() {
        ScrollCustomCombo gene_ = gene();
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        assertEq(0,gene_.getList().getSelectedIndex());
    }
    private void action(ScrollCustomCombo _gene, int _a) {
        ((MockAbstractAction)GuiBaseUtil.getAction(_gene.getSelected(),_a, 0)).action();
    }
    private void click(ScrollCustomCombo _gene, int _y) {
        ((MockCustComponent) _gene.getSelected()).getMouseIntRelListeners().get(0).mouseReleased(new MockMouseCoords(0, 0),new KeyActionEvent(false,false, false),null);
        ((MockCustComponent) _gene.getList().getElements()).getMouseIntRelListeners().get(0).mouseReleased(new MockMouseCoords(0, _y),new KeyActionEvent(false,false, false),null);
    }
    private void click(ScrollCustomCombo _gene) {
        ((MockCustComponent) _gene.getSelected()).getMouseIntRelListeners().get(0).mouseReleased(new MockMouseCoords(0, 0),new KeyActionEvent(false,false, false),null);
    }
    private static ScrollCustomCombo gene() {
        MockProgramInfosSecSample pr_ = init();
        return gene(pr_);
    }

    private static ScrollCustomCombo gene(MockProgramInfosSecSample _pr) {
        ScrollCustomCombo str_ = new DefScrollCustomCombo(_pr.getCompoFactory(), _pr.getImageFactory());
        str_.getList().getElements().setFont(str_.getGlobal().getMetaFont());
        str_.getList().getElements().setFont(new MetaFont("",0,8));
        return str_;
    }

    private void located(ScrollCustomCombo _gene) {
        locate(_gene, "ONE", 0, 0);
        locate(_gene, "TWO", 1, 10);
        locate(_gene, "THREE", 2, 20);
        locate(_gene, "FOUR", 3, 30);
        locate(_gene, "FIVE", 4, 40);
        locate(_gene, "SIX", 5, 50);
        locate(_gene, "SEVEN", 6, 60);
        locate(_gene, "EIGHT", 7, 70);
    }

    private void locate(ScrollCustomCombo _gene, String _info, int _i, int _y) {
        _gene.add(_info);
        _gene.getList().getElements().getComponent(_i).setLocation(0, _y);
    }

}
