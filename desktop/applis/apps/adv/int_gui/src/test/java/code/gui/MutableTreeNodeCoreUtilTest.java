package code.gui;

import code.util.Ints;
import org.junit.Test;

public final class MutableTreeNodeCoreUtilTest extends EquallableIntGuiUtil {
    @Test
    public void t1() {
        MutableTreeNodeCore t_ = new MutableTreeNodeCore();
        assertEq(-1,MutableTreeNodeCoreUtil.getIndex(t_));
        assertNull(MutableTreeNodeCoreUtil.getParent(t_));
        assertNull(MutableTreeNodeCoreUtil.getChildAt(t_,0));
    }
    @Test
    public void t2() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertEq(0,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertNull(MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertNull(MutableTreeNodeCoreUtil.getPreviousSibling(t2_));
    }
    @Test
    public void t3() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t3_));
        assertEq(0,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getChildAt(t1_,1));
        assertEq(1,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t3_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getPreviousSibling(t3_));
    }
    @Test
    public void t4() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t3_));
        assertEq(0,MutableTreeNodeCoreUtil.insert(t1_,t2_,0));
        assertEq(0,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getChildAt(t1_,1));
        assertEq(1,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t3_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getPreviousSibling(t3_));
    }
    @Test
    public void t5() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertEq(1,MutableTreeNodeCoreUtil.insert(t1_,t3_,1));
        assertEq(0,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getChildAt(t1_,1));
        assertEq(1,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t3_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getPreviousSibling(t3_));
    }
    @Test
    public void t6() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertEq(-1,MutableTreeNodeCoreUtil.insert(t1_,t3_,2));
        assertEq(0,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertNull(MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertNull(MutableTreeNodeCoreUtil.getPreviousSibling(t2_));
    }
    @Test
    public void t7() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t3_));
        assertSame(t2_,MutableTreeNodeCoreUtil.remove(t1_,0));
        assertEq(-1,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertNull(MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(-1,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t3_));
        assertNull(MutableTreeNodeCoreUtil.getNextSibling(t3_));
        assertNull(MutableTreeNodeCoreUtil.getPreviousSibling(t3_));
    }
    @Test
    public void t8() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t3_));
        assertSame(t3_,MutableTreeNodeCoreUtil.remove(t1_,1));
        assertEq(0,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertNull(MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertNull(MutableTreeNodeCoreUtil.getPreviousSibling(t2_));
    }
    @Test
    public void t9() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t3_));
        assertEq(0,MutableTreeNodeCoreUtil.remove(t1_,t2_));
        assertEq(-1,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertNull(MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(-1,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t3_));
        assertNull(MutableTreeNodeCoreUtil.getNextSibling(t3_));
        assertNull(MutableTreeNodeCoreUtil.getPreviousSibling(t3_));
    }
    @Test
    public void t10() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t3_));
        assertEq(1,MutableTreeNodeCoreUtil.remove(t1_,t3_));
        assertEq(0,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertNull(MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertNull(MutableTreeNodeCoreUtil.getPreviousSibling(t2_));
    }
    @Test
    public void t11() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertEq(-1,MutableTreeNodeCoreUtil.insert(t1_,t3_,-1));
        assertEq(0,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertNull(MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertNull(MutableTreeNodeCoreUtil.getPreviousSibling(t2_));
    }
    @Test
    public void t12() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t3_));
        assertNull(MutableTreeNodeCoreUtil.remove(t1_,2));
        assertEq(0,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getChildAt(t1_,1));
        assertEq(1,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t3_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getPreviousSibling(t3_));
    }
    @Test
    public void t13() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t3_));
        assertNull(MutableTreeNodeCoreUtil.remove(t1_,-1));
        assertEq(0,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getChildAt(t1_,1));
        assertEq(1,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t3_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getPreviousSibling(t3_));
    }
    @Test
    public void t14() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t4_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t3_));
        assertEq(-1,MutableTreeNodeCoreUtil.remove(t1_,t4_));
        assertEq(0,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getChildAt(t1_,1));
        assertEq(1,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t3_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getPreviousSibling(t3_));
    }
    @Test
    public void t15() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t3_));
        assertSame(t1_,MutableTreeNodeCoreUtil.removeFromParent(t2_));
        assertEq(-1,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertNull(MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t3_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(-1,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t3_));
        assertNull(MutableTreeNodeCoreUtil.getNextSibling(t3_));
        assertNull(MutableTreeNodeCoreUtil.getPreviousSibling(t3_));
    }
    @Test
    public void t16() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t3_));
        assertSame(t1_,MutableTreeNodeCoreUtil.removeFromParent(t3_));
        assertEq(0,MutableTreeNodeCoreUtil.getIndex(t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getParent(t2_));
        assertSame(t2_,MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertEq(0,MutableTreeNodeCoreUtil.getAntiIndex(t1_,t2_));
        assertNull(MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertNull(MutableTreeNodeCoreUtil.getPreviousSibling(t2_));
    }
    @Test
    public void t17() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t2_));
        assertFalse(MutableTreeNodeCoreUtil.add(t1_,t3_));
        assertEq(2,MutableTreeNodeCoreUtil.removeAllChildren(t1_));
        assertNull(MutableTreeNodeCoreUtil.getChildAt(t1_,0));
        assertNull(MutableTreeNodeCoreUtil.getParent(t2_));
        assertNull(MutableTreeNodeCoreUtil.getParent(t3_));
        assertNull(MutableTreeNodeCoreUtil.getPreviousSibling(t2_));
        assertNull(MutableTreeNodeCoreUtil.getPreviousSibling(t3_));
        assertNull(MutableTreeNodeCoreUtil.getNextSibling(t2_));
        assertNull(MutableTreeNodeCoreUtil.getNextSibling(t3_));
    }
    @Test
    public void t18() {
        MutableTreeNodeCore t1_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t2_ = new MutableTreeNodeCore();
        MutableTreeNodeCore t3_ = new MutableTreeNodeCore();
        assertFalse(MutableTreeNodeCoreUtil.add(t2_,t1_));
        assertFalse(MutableTreeNodeCoreUtil.add(t3_,t2_));
        assertSame(t1_,MutableTreeNodeCoreUtil.getElt(t3_, Ints.newList(0,0)));
        assertNull(MutableTreeNodeCoreUtil.getElt(t3_, Ints.newList(0,0,0)));
        assertNull(MutableTreeNodeCoreUtil.getElt(t3_, Ints.newList(0,0,0,0)));
    }
}
