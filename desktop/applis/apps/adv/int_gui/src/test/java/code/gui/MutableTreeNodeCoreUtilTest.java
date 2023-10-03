package code.gui;

import code.util.Ints;
import org.junit.Test;

public final class MutableTreeNodeCoreUtilTest extends EquallableIntGuiUtil {
    @Test
    public void t1() {
        MutableTreeNodeNav<String> t_ = new MutableTreeNodeNav<String>();
        assertEq(-1,t_.getIndex());
        assertNull(t_.getParent());
        assertNull(t_.getChildAt(0));
    }
    @Test
    public void t2() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertEq(0,t2_.getIndex());
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t1_, t2_.getParent());
        assertSame(t2_, t1_.getChildAt(0));
        assertNull(t2_.getNextSibling());
        assertNull(t2_.getPreviousSibling());
        assertNull(t2_.getNextSiblingReal());
        assertNull(t2_.getFirstChildReal());
    }
    @Test
    public void t3() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertEq(0,t2_.getIndex());
        assertSame(t1_, t2_.getParent());
        assertSame(t2_, t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t3_, t1_.getChildAt(1));
        assertEq(1,t1_.getAntiIndex(t3_));
        assertSame(t3_,t2_.getNextSibling());
        assertSame(t2_,t3_.getPreviousSibling());
    }
    @Test
    public void t4() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t3_));
        assertEq(0,t1_.insert(t2_,0));
        assertEq(0,t2_.getIndex());
        assertSame(t1_, t2_.getParent());
        assertSame(t2_, t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t3_, t1_.getChildAt(1));
        assertEq(1,t1_.getAntiIndex(t3_));
        assertSame(t3_,t2_.getNextSibling());
        assertSame(t2_,t3_.getPreviousSibling());
    }
    @Test
    public void t5() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertEq(1,t1_.insert(t3_,1));
        assertEq(0,t2_.getIndex());
        assertSame(t1_, t2_.getParent());
        assertSame(t2_, t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t3_, t1_.getChildAt(1));
        assertEq(1,t1_.getAntiIndex(t3_));
        assertSame(t3_,t2_.getNextSibling());
        assertSame(t2_,t3_.getPreviousSibling());
    }
    @Test
    public void t6() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertEq(-1,t1_.insert(t3_,2));
        assertEq(0,t2_.getIndex());
        assertSame(t1_, t2_.getParent());
        assertSame(t2_, t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertNull(t2_.getNextSibling());
        assertNull(t2_.getPreviousSibling());
    }
    @Test
    public void t7() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertSame(t2_,t1_.remove(0));
        assertEq(-1,t2_.getIndex());
        assertNull(t2_.getParent());
        assertSame(t3_, t1_.getChildAt(0));
        assertEq(-1,t1_.getAntiIndex(t2_));
        assertEq(0,t1_.getAntiIndex(t3_));
        assertNull(t3_.getNextSibling());
        assertNull(t3_.getPreviousSibling());
    }
    @Test
    public void t8() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertSame(t3_,t1_.remove(1));
        assertEq(0,t2_.getIndex());
        assertSame(t1_, t2_.getParent());
        assertSame(t2_, t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertNull(t2_.getNextSibling());
        assertNull(t2_.getPreviousSibling());
    }
    @Test
    public void t9() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertEq(0,t1_.remove(t2_));
        assertEq(-1,t2_.getIndex());
        assertNull(t2_.getParent());
        assertSame(t3_, t1_.getChildAt(0));
        assertEq(-1,t1_.getAntiIndex(t2_));
        assertEq(0,t1_.getAntiIndex(t3_));
        assertNull(t3_.getNextSibling());
        assertNull(t3_.getPreviousSibling());
    }
    @Test
    public void t10() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertEq(1,t1_.remove(t3_));
        assertEq(0,t2_.getIndex());
        assertSame(t1_, t2_.getParent());
        assertSame(t2_, t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertNull(t2_.getNextSibling());
        assertNull(t2_.getPreviousSibling());
    }
    @Test
    public void t11() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertEq(-1,t1_.insert(t3_,-1));
        assertEq(0,t2_.getIndex());
        assertSame(t1_, t2_.getParent());
        assertSame(t2_, t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertNull(t2_.getNextSibling());
        assertNull(t2_.getPreviousSibling());
    }
    @Test
    public void t12() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertNull(t1_.remove(2));
        assertEq(0,t2_.getIndex());
        assertSame(t1_, t2_.getParent());
        assertSame(t2_, t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t3_, t1_.getChildAt(1));
        assertEq(1,t1_.getAntiIndex(t3_));
        assertSame(t3_,t2_.getNextSibling());
        assertSame(t2_,t3_.getPreviousSibling());
    }
    @Test
    public void t13() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertNull(t1_.remove(-1));
        assertEq(0,t2_.getIndex());
        assertSame(t1_, t2_.getParent());
        assertSame(t2_, t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t3_, t1_.getChildAt(1));
        assertEq(1,t1_.getAntiIndex(t3_));
        assertSame(t3_,t2_.getNextSibling());
        assertSame(t2_,t3_.getPreviousSibling());
    }
    @Test
    public void t14() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t4_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertEq(-1,t1_.remove(t4_));
        assertEq(0,t2_.getIndex());
        assertSame(t1_, t2_.getParent());
        assertSame(t2_, t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertSame(t3_, t1_.getChildAt(1));
        assertEq(1,t1_.getAntiIndex(t3_));
        assertSame(t3_,t2_.getNextSibling());
        assertSame(t2_,t3_.getPreviousSibling());
    }
    @Test
    public void t15() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertSame(t1_,t2_.removeFromParent());
        assertEq(-1,t2_.getIndex());
        assertNull(t2_.getParent());
        assertSame(t3_, t1_.getChildAt(0));
        assertEq(-1,t1_.getAntiIndex(t2_));
        assertEq(0,t1_.getAntiIndex(t3_));
        assertNull(t3_.getNextSibling());
        assertNull(t3_.getPreviousSibling());
    }
    @Test
    public void t16() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertSame(t1_,t3_.removeFromParent());
        assertEq(0,t2_.getIndex());
        assertSame(t1_, t2_.getParent());
        assertSame(t2_, t1_.getChildAt(0));
        assertEq(0,t1_.getAntiIndex(t2_));
        assertNull(t2_.getNextSibling());
        assertNull(t2_.getPreviousSibling());
    }
    @Test
    public void t17() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t1_.add(t2_));
        assertFalse(t1_.add(t3_));
        assertEq(2,t1_.removeAllChildren());
        assertNull(t1_.getChildAt(0));
        assertNull(t2_.getParent());
        assertNull(t3_.getParent());
        assertNull(t2_.getPreviousSibling());
        assertNull(t3_.getPreviousSibling());
        assertNull(t2_.getNextSibling());
        assertNull(t3_.getNextSibling());
    }
    @Test
    public void t18() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertFalse(t2_.add(t1_));
        assertFalse(t3_.add(t2_));
        assertSame(t1_,t3_.getElt(Ints.newList(0,0)));
        assertNull(t3_.getElt(Ints.newList(0,0,0)));
        assertNull(t3_.getElt(Ints.newList(0,0,0,0)));
    }
    @Test
    public void t19() {
        MutableTreeNodeNav<String> t1_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t2_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> t3_ = new MutableTreeNodeNav<String>();
        assertEq(-1,t3_.getAntiIndex(t1_));
        assertEq(-1,t3_.getAntiIndex(t2_));
        assertFalse(t3_.add(t1_));
        assertFalse(t3_.add(t2_));
        assertEq(0,t3_.getAntiIndex(t1_));
        assertEq(1,t3_.getAntiIndex(t2_));
        assertEq(2,t3_.getChildCount());
        assertSame(t1_,t3_.getChildAt(0));
        assertSame(t2_,t3_.getChildAt(1));
        assertSame(t3_,t1_.getParent());
        assertSame(t3_,t2_.getParent());
        assertNull(t1_.getPreviousSibling());
        assertSame(t2_,t1_.getNextSibling());
        assertSame(t1_,t2_.getPreviousSibling());
        assertNull(t2_.getNextSibling());
    }
    @Test
    public void getIndexes() {
        MutableTreeNodeNav<String> r_ = new MutableTreeNodeNav<String>();
        MutableTreeNodeNav<String> ch_ = new MutableTreeNodeNav<String>();
        r_.info("");
        assertEq("", r_.info());
        r_.add(ch_);
        Ints ls_ = ch_.getIndexes();
        assertEq(1,ls_.size());
        assertEq(0,ls_.get(0));
        assertSame(null,r_.simular(null));
        assertSame(ch_,r_.simular(ch_));
    }
}
