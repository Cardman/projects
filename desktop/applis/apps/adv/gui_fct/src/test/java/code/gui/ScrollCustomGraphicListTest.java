package code.gui;

import code.gui.events.MockProgramInfosSecSample;
import code.gui.images.MetaRect;
import code.mock.*;
import code.util.Ints;
import org.junit.Test;

public final class ScrollCustomGraphicListTest extends EquallableGuiFctUtil {
    @Test
    public void t1() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        assertEq(0, gene_.size());
    }
    @Test
    public void t2() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        assertEq(1, gene_.size());
        assertEq("ONE", gene_.get(0));
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
        assertEq(1, gene_.getFocused().getIndex());
        assertEq("THREE", gene_.getFocused().getRow().getInfo());
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
        assertFalse(gene_.getRow(3).isSelected());
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
        click(gene_, 5, true, true);
        assertEq(2,gene_.nextIndex(30,false).getIndex());
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
        click(gene_, 15, true, true);
        assertEq(3,gene_.nextIndex(30,false).getIndex());
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
        click(gene_, 45, true, true);
        assertEq(7,gene_.nextIndex(50,false).getIndex());
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
        click(gene_, 75, true, true);
        assertEq(6,gene_.nextIndex(20,true).getIndex());
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
        click(gene_, 75, true, true);
        assertEq(5,gene_.nextIndex(30,true).getIndex());
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
        click(gene_, 25, true, true);
        assertEq(0,gene_.nextIndex(50,true).getIndex());
    }
    @Test
    public void t52() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        assertEq(-1,gene_.nextIndex(0,true).getIndex());
    }
    @Test
    public void t53() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        assertEq(-1,gene_.nextIndex(0,false).getIndex());
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
    }
    @Test
    public void t102() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        click(gene_,5,false,false);
        gene_.getElements().setLocation(0,0);
        gene_.moveShiftPage(true,gene_.getFocused().getRow(),new MetaRect(0,0,10,20));
        assertTrue(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
    }
    @Test
    public void t103() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        click(gene_,75,false,false);
        gene_.getElements().setLocation(0,-60);
        gene_.moveShiftPage(false,gene_.getFocused().getRow(),new MetaRect(0,0,10,20));
        assertTrue(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
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
        gene_.moveShiftPage(false);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
    }
    @Test
    public void t107() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        click(gene_,75,false,false);
        gene_.getElements().setLocation(0,-60);
        gene_.moveShiftPage(true);
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
    }
    @Test
    public void t108() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        located(gene_);
        click(gene_,5,false,false);
        gene_.getElements().setLocation(0,0);
        gene_.moveShiftPage(false);
        assertTrue(gene_.getRow(0).isSelected());
        assertFalse(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
    }
    @Test
    public void t109() {
        ScrollCustomGraphicList<String> gene_ = gene(true);
        located(gene_);
        click(gene_,75,false,false);
        gene_.getElements().setLocation(0,-60);
        gene_.moveShiftPage(true);
        assertTrue(gene_.getRow(7).isSelected());
        assertFalse(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
    }
    @Test
    public void t110() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        click(gene_,5,false,false);
        gene_.getElements().setLocation(0,0);
        gene_.movePage(true,new MetaRect(0,0,10,20));
        assertFalse(gene_.getRow(0).isSelected());
        assertTrue(gene_.getRow(1).isSelected());
        assertFalse(gene_.getRow(2).isSelected());
    }
    @Test
    public void t111() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        located(gene_);
        click(gene_,75,false,false);
        gene_.getElements().setLocation(0,-60);
        gene_.movePage(false,new MetaRect(0,0,10,20));
        assertFalse(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
        assertFalse(gene_.getRow(5).isSelected());
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
        click(gene_,5,false,false);
        gene_.getElements().setLocation(0,0);
        gene_.moveShiftPage(true,gene_.getFocused().getRow(),new MetaRect(0,0,10,90));
        assertTrue(gene_.getRow(7).isSelected());
        assertTrue(gene_.getRow(6).isSelected());
        assertTrue(gene_.getRow(5).isSelected());
    }
    @Test
    public void t115() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.add("ONE");
        gene_.rowCount(1);
        MockWithListSelectionSample w_ = new MockWithListSelectionSample(init(), "");
        gene_.getSelections().add(new MockListSelection(0, w_));
        action(gene_,GuiConstants.VK_A,GuiConstants.CTRL_DOWN_MASK);
        gene_.getScrollPane().recalculate();
        assertEq(-1,gene_.getFocused().getIndex());
        assertTrue(gene_.getRow(0).isSelected());
        assertEq(1,w_.getGraphicComboGrInt().getSelectedIndex());
        assertEq(1,gene_.getVisibleRowCount());
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
        gene_.movePage(true);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t123() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,-60);
        gene_.movePage(false);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t124() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,0);
        gene_.moveShiftPage(true);
        assertEq(-1,gene_.getFocused().getIndex());
    }
    @Test
    public void t125() {
        ScrollCustomGraphicList<String> gene_ = gene(false);
        gene_.getElements().setLocation(0,-60);
        gene_.moveShiftPage(false);
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
    private void action(ScrollCustomGraphicList<String> _gene, int _a, int _b) {
        ((MockAbstractAction)GuiBaseUtil.getAction(_gene.getElements(),_a,_b)).action();
    }
    private void click(ScrollCustomGraphicList<String> _gene, int _y, boolean _ctrl, boolean _shift) {
        ((MockCustComponent) _gene.getElements()).getMouseIntRelListeners().get(0).mouseReleased(new MockMouseCoords(0, _y),new KeyActionEvent(_ctrl,false, _shift),null);
    }

    private static ScrollCustomGraphicList<String> gene(boolean _simple) {
        MockProgramInfosSecSample pr_ = init();
        return gene(_simple, pr_);
    }

    private static ScrollCustomGraphicList<String> gene(boolean _simple, MockProgramInfosSecSample _pr) {
        return new ScrollCustomGraphicList<String>(_pr.getCompoFactory(), _pr.getImageFactory(), new CustCellRenderGeneSample(), _simple);
    }

    private void located(ScrollCustomGraphicList<String> _gene) {
        _gene.add("ONE");
        _gene.getElements().getComponent(0).setLocation(0,0);
        _gene.add("TWO");
        _gene.getElements().getComponent(1).setLocation(0,10);
        _gene.add("THREE");
        _gene.getElements().getComponent(2).setLocation(0,20);
        _gene.add("FOUR");
        _gene.getElements().getComponent(3).setLocation(0,30);
        _gene.add("FIVE");
        _gene.getElements().getComponent(4).setLocation(0,40);
        _gene.add("SIX");
        _gene.getElements().getComponent(5).setLocation(0,50);
        _gene.add("SEVEN");
        _gene.getElements().getComponent(6).setLocation(0,60);
        _gene.add("EIGHT");
        _gene.getElements().getComponent(7).setLocation(0,70);
    }

}
