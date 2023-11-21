package code.gui;

import code.gui.events.MockProgramInfosSecSample;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.mock.*;
import code.util.CustList;
import code.util.Ints;
import org.junit.Test;

public final class ScrollCustomGraphicListTest extends EquallableGuiFctUtil {
    @Test
    public void t1() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.applyRows();
        assertEq(0, gene_.size());
        assertTrue(gene_.isEmpty());
    }
    @Test
    public void t2() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        assertEq(1, gene_.size());
        assertEq("ONE", gene_.get(0));
        assertFalse(gene_.isEmpty());
        assertEq(1,gene_.getList().size());
        assertEq("ONE",gene_.getList().get(0));
        assertEq("ONE",gene_.last());
    }
    @Test
    public void t3() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add(0,"ONE");
        assertEq(1, gene_.size());
        assertEq("ONE", gene_.get(0));
    }
    @Test
    public void t4() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add(-1,"ONE");
        assertEq(0, gene_.size());
    }
    @Test
    public void t5() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        assertNull(gene_.get(0));
        assertNull(gene_.last());
    }
    @Test
    public void t6() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add(0,"TWO");
        gene_.add(0,"ONE");
        assertEq(2, gene_.size());
        assertEq("ONE", gene_.get(0));
        assertEq("TWO", gene_.get(1));
    }
    @Test
    public void t7() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add(0,"THREE");
        gene_.add(0,"ONE");
        gene_.add(1,"TWO");
        assertEq(3, gene_.size());
        assertEq("ONE", gene_.get(0));
        assertEq("TWO", gene_.get(1));
        assertEq("THREE", gene_.get(2));
    }
    @Test
    public void t8() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        assertEq(2, gene_.size());
        assertEq("ONE", gene_.get(0));
        assertEq("TWO", gene_.get(1));
    }
    @Test
    public void t9() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("2");
        gene_.set(1,"TWO");
        assertEq(2, gene_.size());
        assertEq("ONE", gene_.get(0));
        assertEq("TWO", gene_.get(1));
    }
    @Test
    public void t10() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.set(-1,"THREE");
        assertEq(2, gene_.size());
        assertEq("ONE", gene_.get(0));
        assertEq("TWO", gene_.get(1));
    }
    @Test
    public void t11() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.remove(1);
        assertEq(2, gene_.size());
        assertEq("ONE", gene_.get(0));
        assertEq("THREE", gene_.get(1));
    }
    @Test
    public void t12() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.remove(0);
        assertEq(2, gene_.size());
        assertEq("TWO", gene_.get(0));
        assertEq("THREE", gene_.get(1));
    }
    @Test
    public void t13() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.remove(2);
        assertEq(2, gene_.size());
        assertEq("ONE", gene_.get(0));
        assertEq("TWO", gene_.get(1));
    }
    @Test
    public void t14() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.remove(0);
        assertEq(0, gene_.size());
    }
    @Test
    public void t15() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(1);
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
    }
    @Test
    public void t16() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(1);
        gene_.remove(1);
        assertEq(0, gene_.getFocused().getIndex());
        assertEq("ONE", gene_.getFocused().getRow().getInfo());
    }
    @Test
    public void t17() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(0);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
    }
    @Test
    public void t18() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(0);
        gene_.remove(0);
        assertEq(0, gene_.getFocused().getIndex());
        assertEq("TWO", gene_.getFocused().getRow().getInfo());
    }
    @Test
    public void t19() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(2);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
    }
    @Test
    public void t20() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(2);
        gene_.remove(2);
        assertEq(1, gene_.getFocused().getIndex());
        assertEq("TWO", gene_.getFocused().getRow().getInfo());
    }
    @Test
    public void t21() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(2);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
    }
    @Test
    public void t22() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.select(0);
        gene_.remove(0);
        assertEq(-1, gene_.getFocused().getIndex());
    }
    @Test
    public void t23() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(-1);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
    }
    @Test
    public void t24() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(Ints.newList(0,1,2));
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t25() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(Ints.newList(0,2));
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t26() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(Ints.newList(0,2,1));
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t27() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(Ints.newList(-1,3,0,2,1));
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t28() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(Ints.newList());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t29() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        click(gene_, 15, false, false);
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertEq(1,gene_.getFocused().getIndex());
    }

    @Test
    public void t30() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        click(gene_, -5, false, false);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t31() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        click(gene_, 35, false, false);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t32() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        click(gene_, 0, false, false);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t33() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        click(gene_, 15, false, false);
        click(gene_, 15, false, false);
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t34() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        click(gene_, 15, true, false);
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t35() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        click(gene_, 15, true, false);
        click(gene_, 15, true, false);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t36() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        click(gene_, 15, true, false);
        click(gene_, 25, true, false);
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t37() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        click(gene_, 15, true, false);
        click(gene_, 25, true, false);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t38() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 25, false, false);
        click(gene_, 65, false, true);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertTrue(gene_.getRow(4).isSelected());
        assertTrue(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(6,gene_.getFocused().getIndex());
    }
    @Test
    public void t39() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        click(gene_, 25, false, false);
        click(gene_, 65, false, true);
        click(gene_, 35, false, true);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(3,gene_.getFocused().getIndex());
    }

    @Test
    public void t40() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 25, false, false);
        click(gene_, 65, false, true);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(6,gene_.getFocused().getIndex());
    }
    @Test
    public void t41() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, -5, false, true);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t42() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, true);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(0,gene_.getFocused().getIndex());
    }
    @Test
    public void t43() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 25, true, true);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t44() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 25, true, true);
        click(gene_, 25, true, true);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t45() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, -5, true, true);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t46() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        gene_.getElements().setLocation(0,0);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, true, true);
        action(gene_,GuiConstants.VK_PAGE_DOWN,0);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t47() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        gene_.getElements().setLocation(0,-10);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 15, true, true);
        action(gene_,GuiConstants.VK_PAGE_DOWN,0);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(3,gene_.getFocused().getIndex());
    }
    @Test
    public void t48() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        gene_.getElements().setLocation(0,0);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,50));
        click(gene_, 45, true, true);
        action(gene_,GuiConstants.VK_PAGE_DOWN,0);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertEq(7,gene_.getFocused().getIndex());
    }
    @Test
    public void t49() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        gene_.getElements().setLocation(0,-60);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,20));
        click(gene_, 75, true, true);
        action(gene_,GuiConstants.VK_PAGE_UP,0);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(6,gene_.getFocused().getIndex());
    }
    @Test
    public void t50() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        gene_.getElements().setLocation(0,-50);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 75, true, true);
        action(gene_,GuiConstants.VK_PAGE_UP,0);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertTrue(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(5,gene_.getFocused().getIndex());
    }
    @Test
    public void t51() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        gene_.getElements().setLocation(0,-20);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,50));
        click(gene_, 25, true, true);
        action(gene_,GuiConstants.VK_PAGE_UP,0);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(0,gene_.getFocused().getIndex());
    }
    @Test
    public void t52() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        action(gene_,GuiConstants.VK_PAGE_UP,0);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t53() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        action(gene_,GuiConstants.VK_PAGE_DOWN,0);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t54() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.remove(-1);
        assertEq(3, gene_.size());
        assertEq("ONE", gene_.get(0));
        assertEq("TWO", gene_.get(1));
        assertEq("THREE", gene_.get(2));
    }
    @Test
    public void t55() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 25, false, false);
        click(gene_, 75, false, true);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertTrue(gene_.getRow(4).isSelected());
        assertTrue(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertEq(7,gene_.getFocused().getIndex());
    }
    @Test
    public void t56() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        click(gene_, -5, true, false);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t57() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, -5, false, true);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t58() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        click(gene_, 5, false, true);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(0,gene_.getFocused().getIndex());
    }
    @Test
    public void t59() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(Ints.newList(0,2));
        Ints is_ = gene_.getSelectedIndexes();
        assertEq(2,is_.size());
        assertEq(0,is_.get(0));
        assertEq(2,is_.get(1));
    }
    @Test
    public void t60() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,0);
        assertEq(1,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t61() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,0);
        action(gene_,GuiConstants.VK_UP,0);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t62() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_UP,0);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t63() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_UP,0);
        assertEq(6,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
    }
    @Test
    public void t64() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_UP,0);
        action(gene_,GuiConstants.VK_DOWN,0);
        assertEq(7,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t65() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_DOWN,0);
        assertEq(7,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t66() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_UP,0);
        assertEq(7,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t67() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_DOWN,0);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t68() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(1,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t69() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        action(gene_,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t70() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(6,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
    }
    @Test
    public void t71() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(7,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t72() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t73() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(7,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t74() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(7,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t75() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t76() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(1,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t77() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(6,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
    }
    @Test
    public void t78() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_END,0);
        assertEq(7,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t79() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_END,0);
        action(gene_,GuiConstants.VK_HOME,0);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t80() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_HOME,0);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t81() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_HOME,0);
        assertEq(0,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t82() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_HOME,0);
        action(gene_,GuiConstants.VK_END,0);
        assertEq(7,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t83() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_END,0);
        assertEq(7,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t84() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_HOME,0);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t85() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_END,0);
        assertEq(7,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t86() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        action(gene_,GuiConstants.VK_HOME,0);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t87() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        action(gene_,GuiConstants.VK_END,0);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t88() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(7,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertTrue(gene_.getRow(4).isSelected());
        assertTrue(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
    }
    @Test
    public void t89() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK);
        action(gene_,GuiConstants.VK_HOME,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t90() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_HOME,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertTrue(gene_.getRow(4).isSelected());
        assertTrue(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
    }
    @Test
    public void t91() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_HOME,GuiConstants.SHIFT_DOWN_MASK);
        action(gene_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(7,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t92() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_HOME,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t93() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(7,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
    }
    @Test
    public void t94() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_HOME,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(7,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
    }
    @Test
    public void t95() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t96() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(7,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t97() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_HOME,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(0,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t98() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        action(gene_,GuiConstants.VK_HOME,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t99() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        action(gene_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t100() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(7,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t101() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_HOME,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(0,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
    }
    @Test
    public void t102() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,20));
        click(gene_,5,false,false);
        gene_.getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t103() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,20));
        click(gene_,75,false,false);
        gene_.getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_PAGE_UP,GuiConstants.SHIFT_DOWN_MASK);
        assertTrue(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
    }
    @Test
    public void t104() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t105() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t106() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        click(gene_,5,false,false);
        gene_.getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_PAGE_UP,GuiConstants.SHIFT_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t107() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        click(gene_,75,false,false);
        gene_.getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
    }
    @Test
    public void t108() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        located(gene_);
        click(gene_,5,false,false);
        gene_.getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_PAGE_UP,GuiConstants.SHIFT_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t109() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        located(gene_);
        click(gene_,75,false,false);
        gene_.getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
    }
    @Test
    public void t110() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,20));
        click(gene_,5,false,false);
        gene_.getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_PAGE_DOWN,0);
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
    }
    @Test
    public void t111() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,20));
        click(gene_,75,false,false);
        gene_.getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_PAGE_UP,0);
        assertFalse(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
    }
    @Test
    public void t112() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_DOWN,0);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t113() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_UP,0);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t114() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,90));
        click(gene_,5,false,false);
        gene_.getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        assertTrue(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
        assertTrue(gene_.getRow(5).isSelected());
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertTrue(gene_.getRow(4).isSelected());
    }
    @Test
    public void t115() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.setVisibleRowCount(1);
        gene_.applyRows();
        MockWithListSelectionSample w_ = new MockWithListSelectionSample(init(), "");
        MockListSelection ls_ = new MockListSelection(0, w_);
        gene_.addListener(ls_);
        assertEq(1,gene_.getSelections().size());
        action(gene_,GuiConstants.VK_A,GuiConstants.CTRL_DOWN_MASK);
        gene_.getGlobal().recalculate();
        assertEq(-1,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertEq(1,w_.getGraphicComboGrInt().getSelectedIndex());
        assertEq(1,gene_.getVisibleRowCount());
        gene_.removeListener(ls_);
        assertEq(0,gene_.getSelections().size());
        gene_.setListener(ls_);
        assertEq(1,gene_.getSelections().size());
        gene_.setListener(null);
        assertEq(0,gene_.getSelections().size());
    }
    @Test
    public void t116() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.setEnabled(true);
        assertTrue(gene_.isEnabled());
    }
    @Test
    public void t117() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.setEnabled(false);
        assertFalse(gene_.isEnabled());
    }
    @Test
    public void t118() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.setEnabled(true);
        assertTrue(gene_.isEnabled());
    }
    @Test
    public void t119() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.setEnabled(false);
        assertFalse(gene_.isEnabled());
    }
    @Test
    public void t120() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.getRow(0).focus(gene_.getRow(0).isFocused());
        gene_.getRow(0).anchor(gene_.getRow(0).isAnchored());
        assertEq(1,gene_.forceRefresh());
        gene_.getRow(0).focus(gene_.getRow(0).isFocused());
        gene_.getRow(0).anchor(gene_.getRow(0).isAnchored());
        assertEq(1,gene_.forceRefresh());
    }
    @Test
    public void t121() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.setEnabled(false);
        click(gene_,5,false,false);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t122() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_DOWN,0);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t123() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_UP,0);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t124() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_PAGE_DOWN,0);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t125() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_PAGE_UP,0);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t126() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_PAGE_DOWN,0);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t127() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_PAGE_UP,0);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t128() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,0);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t129() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,-60);
        action(gene_,GuiConstants.VK_PAGE_UP,GuiConstants.SHIFT_DOWN_MASK);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t130() {
        MockProgramInfosSecSample pr_ = init();
        ScrollCustomGraphicList<String> gene_ = standard(pr_.getCompoFactory(),pr_.getImageFactory(),true);
        gene_.getElements().setFont(new MetaFont("",0,8));
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.select(1);
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t131() {
        MockProgramInfosSecSample pr_ = init();
        ScrollCustomGraphicList<String> gene_ = standard(pr_.getCompoFactory(),pr_.getImageFactory(),false);
        gene_.getElements().setFont(new MetaFont("",0,8));
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.select(1);
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t132() {
        MockProgramInfosSecSample pr_ = init();
        ScrollCustomGraphicList<String> gene_ = standard(pr_.getCompoFactory(),pr_.getImageFactory(),false);
        gene_.getElements().setFont(new MetaFont("",0,8));
        gene_.add("ONE");
        gene_.select(0);
        ((MockCustComponent)gene_.getElements()).setFocused(true);
        gene_.getElements().getFocusListeners().get(0).focusGained();
        assertEq(0,gene_.getFocused().getIndex());
    }
    @Test
    public void t133() {
        MockProgramInfosSecSample pr_ = init();
        ScrollCustomGraphicList<String> gene_ = standard(pr_.getCompoFactory(),pr_.getImageFactory(),false);
        gene_.getElements().setFont(new MetaFont("",0,8));
        gene_.add("ONE");
        gene_.select(0);
        ((MockCustComponent)gene_.getElements()).setFocused(false);
        gene_.getElements().getFocusListeners().get(0).focusLost();
        assertEq(0,gene_.getFocused().getIndex());
    }
    @Test
    public void t134() {
        MockProgramInfosSecSample pr_ = init();
        ScrollCustomGraphicList<String> gene_ = standard(pr_.getCompoFactory(),pr_.getImageFactory(),false);
        gene_.getElements().setFont(new MetaFont("",0,8));
        gene_.add("ONE");
        ((MockCustComponent)gene_.getElements()).setFocused(true);
        gene_.getElements().getFocusListeners().get(0).focusGained();
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t135() {
        MockProgramInfosSecSample pr_ = init();
        ScrollCustomGraphicList<String> gene_ = standard(pr_.getCompoFactory(),pr_.getImageFactory(),false);
        gene_.getElements().setFont(new MetaFont("",0,8));
        gene_.add("ONE");
        ((MockCustComponent)gene_.getElements()).setFocused(false);
        gene_.getElements().getFocusListeners().get(0).focusLost();
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t136() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        click(gene_, 15, true, false);
        click(gene_, 35, true, false);
        click(gene_, 45, true, true);
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertTrue(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(4,gene_.getFocused().getIndex());
    }
    @Test
    public void t137() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        click(gene_, 15, true, false);
        click(gene_, 35, true, false);
        click(gene_, 45, true, true);
        click(gene_, 35, true, false);
        click(gene_, 45, true, true);
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(4,gene_.getFocused().getIndex());
    }
    @Test
    public void t138() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        click(gene_, 15, true, false);
        click(gene_, 45, true, false);
        click(gene_, 35, true, true);
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertTrue(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(3,gene_.getFocused().getIndex());
    }
    @Test
    public void t139() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        click(gene_, 15, true, false);
        click(gene_, 45, true, false);
        click(gene_, 35, true, true);
        click(gene_, 45, true, false);
        click(gene_, 35, true, true);
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(3,gene_.getFocused().getIndex());
    }
    @Test
    public void t140() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("THREE");
        gene_.select(1);
        gene_.add(1,"TWO");
        assertEq(2, gene_.getFocused().getIndex());
        assertEq("THREE", gene_.getFocused().getRow().getInfo());
    }
    @Test
    public void t141() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(1);
        gene_.add(0,"ONE");
        assertEq(2, gene_.getFocused().getIndex());
        assertEq("THREE", gene_.getFocused().getRow().getInfo());
    }
    @Test
    public void t142() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t143() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_UP,GuiConstants.CTRL_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(0,gene_.getFocused().getIndex());
    }
    @Test
    public void t144() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_UP,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(7,gene_.getFocused().getIndex());
    }
    @Test
    public void t145() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t146() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.CTRL_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t147() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertEq(7,gene_.getFocused().getIndex());
    }
    @Test
    public void t148() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.CTRL_DOWN_MASK);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t149() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t150() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.CTRL_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(7,gene_.getFocused().getIndex());
    }
    @Test
    public void t151() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertEq(7,gene_.getFocused().getIndex());
    }
    @Test
    public void t152() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.CTRL_DOWN_MASK);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t153() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertEq(7,gene_.getFocused().getIndex());
    }
    @Test
    public void t154() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("THREE");
        gene_.select(0);
        gene_.add(1,"TWO");
        assertEq(0, gene_.getFocused().getIndex());
        assertEq("ONE", gene_.getFocused().getRow().getInfo());
    }
    @Test
    public void t155() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(2);
        gene_.remove(1);
        assertEq(1, gene_.getFocused().getIndex());
        assertEq("THREE", gene_.getFocused().getRow().getInfo());
    }
    @Test
    public void t156() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.select(0);
        gene_.remove(1);
        assertEq(0, gene_.getFocused().getIndex());
        assertEq("ONE", gene_.getFocused().getRow().getInfo());
    }
    @Test
    public void t157() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_SPACE,0);
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(3,gene_.getFocused().getIndex());
    }
    @Test
    public void t158() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_SPACE,0);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t159() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_SPACE,0);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(3,gene_.getFocused().getIndex());
    }
    @Test
    public void t160() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_SPACE,GuiConstants.CTRL_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(3,gene_.getFocused().getIndex());
    }
    @Test
    public void t161() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_SPACE,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t162() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_SPACE,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(3,gene_.getFocused().getIndex());
    }
    @Test
    public void t163() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_SPACE,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_SPACE,GuiConstants.CTRL_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(3,gene_.getFocused().getIndex());
    }
    @Test
    public void t164() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_SPACE,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_SPACE,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertTrue(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(3,gene_.getFocused().getIndex());
    }
    @Test
    public void t165() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_SPACE,GuiConstants.SHIFT_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t166() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_SPACE,GuiConstants.SHIFT_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t167() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_SPACE,GuiConstants.SHIFT_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t168() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        click(gene_, 5, true, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_SPACE,GuiConstants.SHIFT_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t169() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_SPACE,GuiConstants.CTRL_DOWN_MASK+GuiConstants.SHIFT_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(1,gene_.getFocused().getIndex());
    }
    @Test
    public void t170() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        action(gene_,GuiConstants.VK_A,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_W,GuiConstants.CTRL_DOWN_MASK);
        gene_.getGlobal().recalculate();
        assertEq(-1,gene_.getFocused().getIndex());
        assertFalse(gene_.getRow(0).isSelected());
    }
    @Test
    public void t171() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        click(gene_, 5, false, false);
        click(gene_, 5, true, false);
        action(gene_,GuiConstants.VK_A,GuiConstants.CTRL_DOWN_MASK);
        gene_.getGlobal().recalculate();
        assertEq(0,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
    }
    @Test
    public void t172() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.clearRevalidate();
        assertEq(0, gene_.size());
        assertEq(0, gene_.getSelectedValuesLsLen());
    }
    @Test
    public void t173() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 15, false, false);
        click(gene_, 25, true, false);
        assertEq(1,gene_.getSelectedIndex());
        assertFalse(gene_.isSelectionEmpty());
    }
    @Test
    public void t174() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        assertEq(-1,gene_.getSelectedIndex());
        assertTrue(gene_.isSelectionEmpty());
    }
    @Test
    public void t175() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(1,gene_.getAnchor().getIndex());
    }
    @Test
    public void t176() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(0,gene_.getAnchor().getIndex());
    }
    @Test
    public void t177() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_UP,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(7,gene_.getAnchor().getIndex());
    }
    @Test
    public void t178() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(1,gene_.getAnchor().getIndex());
    }
    @Test
    public void t179() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(2,gene_.getAnchor().getIndex());
    }
    @Test
    public void t180() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertEq(7,gene_.getAnchor().getIndex());
    }
    @Test
    public void t181() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        assertEq(-1,gene_.getAnchor().getIndex());
    }
    @Test
    public void t182() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_PAGE_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(2,gene_.getAnchor().getIndex());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t183() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(7,gene_.getAnchor().getIndex());
    }
    @Test
    public void t184() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 75, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertEq(7,gene_.getAnchor().getIndex());
    }
    @Test
    public void t185() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        assertEq(-1,gene_.getAnchor().getIndex());
    }
    @Test
    public void t186() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        located(gene_);
        gene_.getGlobal().setPreferredSize(new MetaDimension(10,30));
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_END,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertTrue(gene_.getRow(7).isSelected());
        assertEq(7,gene_.getAnchor().getIndex());
    }
    @Test
    public void t187() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_B,GuiConstants.CTRL_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertTrue(gene_.getRow(4).isSelected());
        assertTrue(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(4,gene_.getAnchor().getIndex());
        assertEq(5,gene_.getFocused().getIndex());
    }
    @Test
    public void t188() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_B,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_N,GuiConstants.CTRL_DOWN_MASK);
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(4,gene_.getAnchor().getIndex());
        assertEq(5,gene_.getFocused().getIndex());
    }
    @Test
    public void t189() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        action(gene_,GuiConstants.VK_B,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(-1,gene_.getAnchor().getIndex());
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t190() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_B,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(2,gene_.getAnchor().getIndex());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t191() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_B,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertTrue(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(2,gene_.getAnchor().getIndex());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t192() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_B,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_N,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(2,gene_.getAnchor().getIndex());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t193() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        gene_.add("ONE");
        gene_.add("TWO");
        gene_.add("THREE");
        gene_.add("FOUR");
        gene_.add("FIVE");
        gene_.add("SIX");
        gene_.add("SEVEN");
        gene_.add("EIGHT");
        click(gene_, 5, false, false);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_DOWN,GuiConstants.SHIFT_DOWN_MASK+GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_B,GuiConstants.CTRL_DOWN_MASK);
        action(gene_,GuiConstants.VK_N,GuiConstants.CTRL_DOWN_MASK);
        assertFalse(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
        assertFalse(gene_.getRow(3).isSelected());
        assertFalse(gene_.getRow(4).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(7).isSelected());
        assertEq(2,gene_.getAnchor().getIndex());
        assertEq(2,gene_.getFocused().getIndex());
    }
    @Test
    public void t194() {
        MockProgramInfosSecSample pr_ = init();
        ScrollCustomGraphicList<String> gene_ = GuiBaseUtil.standard(pr_.getCompoFactory(),pr_.getImageFactory(),false, new CustList<String>("ONE","TWO"),Ints.newList(1),8);
        assertEq(1,gene_.getFocused().getIndex());
    }
    private void action(ScrollCustomGraphicList<String> _gene, int _a, int _b) {
        ((MockAbstractAction)GuiBaseUtil.getAction(_gene.getElements(),_a,_b)).action();
    }
    private void click(ScrollCustomGraphicList<String> _gene, int _y, boolean _ctrl, boolean _shift) {
        ((MockCustComponent) _gene.getElements()).getMouseIntRelListeners().get(0).mouseReleased(new CoreMouseLocation(0, _y),new KeyActionEvent(_ctrl,false, _shift),null);
    }

    private static ScrollCustomGraphicList<String> gene(boolean _simple) {
        MockProgramInfosSecSample pr_ = init();
        return gene(_simple, pr_);
    }

    private static ScrollCustomGraphicList<String> gene(boolean _simple, MockProgramInfosSecSample _pr) {
        ScrollCustomGraphicList<String> str_ = new DefScrollCustomGraphicList<String>(_pr.getCompoFactory(), _pr.getImageFactory(), new CustCellRenderString(_pr.getCompoFactory(), _pr.getImageFactory()), _simple);
        str_.getElements().setFont(new MetaFont("",0,8));
        return str_;
    }

    private void located(ScrollCustomGraphicList<String> _gene) {
        locate(_gene, "ONE", 0, 0);
        locate(_gene, "TWO", 1, 10);
        locate(_gene, "THREE", 2, 20);
        locate(_gene, "FOUR", 3, 30);
        locate(_gene, "FIVE", 4, 40);
        locate(_gene, "SIX", 5, 50);
        locate(_gene, "SEVEN", 6, 60);
        locate(_gene, "EIGHT", 7, 70);
    }

    private void locate(ScrollCustomGraphicList<String> _gene, String _info, int _i, int _y) {
        _gene.add(_info);
        _gene.getElements().getComponent(_i).setLocation(0, _y);
    }

    public static ScrollCustomGraphicList<String> standard(AbsCompoFactory _compo, AbstractImageFactory _img, boolean _simple) {
        return GuiBaseUtil.standard(_compo, _img, _simple, new CustList<String>(), Ints.newList(), 8);
    }
}
