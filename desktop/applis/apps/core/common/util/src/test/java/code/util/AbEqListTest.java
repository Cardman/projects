package code.util;

import code.util.classestest.MyCmp;
import code.util.classestest.MyEnum;
import code.util.classestest.MyEquallable;
import code.util.classestest.ComparatorEnum;
import org.junit.Test;

public class AbEqListTest extends EquallableExUtil {
    @Test
    public void index1Test() {
        EnumList<MyEnum> e_ = new EnumList<MyEnum>(new EnumList<MyEnum>());
        e_.add(MyEnum.ONE);
        e_.add(MyEnum.TWO);
        e_.add(MyEnum.THREE);
        e_.add(MyEnum.ONE);
        Ints indexes_ = e_.indexesOfObj(MyEnum.ONE);
        assertEq(2, indexes_.size());
        assertEq(0, indexes_.get(0));
        assertEq(3, indexes_.get(1));
    }
    @Test
    public void removeDuplates1Test() {
        EnumList<MyEnum> e_ = new EnumList<MyEnum>(new MyEnum[0]);
        e_.add(MyEnum.ONE);
        e_.add(MyEnum.TWO);
        e_.add(MyEnum.THREE);
        e_.add(MyEnum.ONE);
        e_.removeDuplicates();
        assertEq(3, e_.size());
        assertSame(MyEnum.ONE, e_.get(0));
        assertSame(MyEnum.TWO, e_.get(1));
        assertSame(MyEnum.THREE, e_.get(2));
    }
    @Test
    public void getGroupsSameCompare1Test() {
        EnumList<MyEnum> e_ = new EnumList<MyEnum>();
        e_.add(MyEnum.ONE);
        e_.add(MyEnum.TWO);
        e_.add(MyEnum.THREE);
        e_.add(MyEnum.ONE);
        CustList<EnumList<MyEnum>> gr_ = e_.getGroupsSameCompare(new ComparatorEnum());
        assertEq(3, gr_.size());
        assertSame(2, gr_.get(0).size());
        assertSame(MyEnum.ONE, gr_.get(0).get(0));
        assertSame(MyEnum.ONE, gr_.get(0).get(1));
        assertSame(1, gr_.get(1).size());
        assertSame(MyEnum.TWO, gr_.get(1).get(0));
        assertSame(1, gr_.get(2).size());
        assertSame(MyEnum.THREE, gr_.get(2).get(0));
    }
    @Test
    public void eqEnum1Test() {
        EnumList<MyEnum> e_ = new EnumList<MyEnum>(new CollCapacity(3));
        e_.add(MyEnum.ONE);
        e_.add(MyEnum.TWO);
        e_.add(MyEnum.THREE);
        e_.add(MyEnum.ONE);
        EnumList<MyEnum> f_ = new EnumList<MyEnum>();
        f_.add(MyEnum.ONE);
        f_.add(MyEnum.TWO);
        f_.add(MyEnum.THREE);
        f_.add(MyEnum.ONE);
        assertTrue(e_.eq(f_));
    }
    @Test
    public void eqEnum2Test() {
        EnumList<MyEnum> e_ = new EnumList<MyEnum>();
        e_.add(MyEnum.ONE);
        e_.add(MyEnum.TWO);
        e_.add(MyEnum.THREE);
        EnumList<MyEnum> f_ = new EnumList<MyEnum>();
        f_.add(MyEnum.ONE);
        f_.add(MyEnum.TWO);
        f_.add(MyEnum.THREE);
        f_.add(MyEnum.ONE);
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void eqEnum3Test() {
        EnumList<MyEnum> e_ = new EnumList<MyEnum>();
        e_.add(MyEnum.ONE);
        e_.add(MyEnum.TWO);
        e_.add(MyEnum.THREE);
        e_.add(MyEnum.THREE);
        EnumList<MyEnum> f_ = new EnumList<MyEnum>();
        f_.add(MyEnum.ONE);
        f_.add(MyEnum.TWO);
        f_.add(MyEnum.THREE);
        f_.add(MyEnum.ONE);
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void indexId1Test() {
        IdList<MyEnum> e_ = new IdList<MyEnum>(new IdList<MyEnum>());
        e_.add(MyEnum.ONE);
        e_.add(MyEnum.TWO);
        e_.add(MyEnum.THREE);
        e_.add(MyEnum.ONE);
        int index_ = e_.indexOfObj(MyEnum.ONE,1);
        assertEq(3, index_);
    }
    @Test
    public void indexId2Test() {
        IdList<MyEnum> e_ = new IdList<MyEnum>(new MyEnum[0]);
        e_.add(MyEnum.ONE);
        e_.add(MyEnum.TWO);
        e_.add(MyEnum.THREE);
        e_.add(MyEnum.ONE);
        int index_ = e_.indexOfObj(MyEnum.TWO,2);
        assertEq(-1, index_);
    }
    @Test
    public void eqId1Test() {
        IdList<MyEnum> e_ = new IdList<MyEnum>(new CollCapacity(4));
        e_.add(MyEnum.ONE);
        e_.add(MyEnum.TWO);
        e_.add(MyEnum.THREE);
        e_.add(MyEnum.ONE);
        IdList<MyEnum> f_ = new IdList<MyEnum>();
        f_.add(MyEnum.ONE);
        f_.add(MyEnum.TWO);
        f_.add(MyEnum.THREE);
        f_.add(MyEnum.ONE);
        assertTrue(e_.eq(f_));
    }
    @Test
    public void eqId2Test() {
        IdList<MyEnum> e_ = new IdList<MyEnum>();
        e_.add(MyEnum.ONE);
        e_.add(MyEnum.TWO);
        e_.add(MyEnum.THREE);
        IdList<MyEnum> f_ = new IdList<MyEnum>();
        f_.add(MyEnum.ONE);
        f_.add(MyEnum.TWO);
        f_.add(MyEnum.THREE);
        f_.add(MyEnum.ONE);
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void eqId3Test() {
        IdList<MyEnum> e_ = new IdList<MyEnum>();
        e_.add(MyEnum.ONE);
        e_.add(MyEnum.TWO);
        e_.add(MyEnum.THREE);
        e_.add(MyEnum.THREE);
        IdList<MyEnum> f_ = new IdList<MyEnum>();
        f_.add(MyEnum.ONE);
        f_.add(MyEnum.TWO);
        f_.add(MyEnum.THREE);
        f_.add(MyEnum.ONE);
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void indexEq1Test() {
        EqList<MyEquallable> e_ = new EqList<MyEquallable>(new EqList<MyEquallable>());
        e_.add(new MyEquallable(1));
        e_.add(new MyEquallable(2));
        e_.add(new MyEquallable(3));
        e_.add(new MyEquallable(1));
        int index_ = e_.indexOfObj(new MyEquallable(1),1);
        assertEq(3, index_);
    }
    @Test
    public void indexEq2Test() {
        EqList<MyEquallable> e_ = new EqList<MyEquallable>(new EqList<MyEquallable>());
        e_.add(new MyEquallable(1));
        e_.add(new MyEquallable(2));
        e_.add(new MyEquallable(3));
        e_.add(new MyEquallable(1));
        int index_ = e_.indexOfObj(new MyEquallable(2),2);
        assertEq(-1, index_);
    }
    @Test
    public void removeDuplatesEq1Test() {
        EqList<MyEquallable> e_ = new EqList<MyEquallable>(new MyEquallable[0]);
        e_.add(new MyEquallable(1));
        e_.add(new MyEquallable(2));
        e_.add(new MyEquallable(3));
        e_.add(new MyEquallable(1));
        e_.removeDuplicates();
        assertEq(3, e_.size());
        assertEq(1, e_.get(0).getInfo());
        assertEq(2, e_.get(1).getInfo());
        assertEq(3, e_.get(2).getInfo());
    }
    @Test
    public void hasDuplicatesEq1Test() {
        EqList<MyEquallable> e_ = new EqList<MyEquallable>(new MyEquallable[0]);
        e_.add(new MyEquallable(1));
        e_.add(new MyEquallable(2));
        e_.add(new MyEquallable(3));
        e_.add(new MyEquallable(1));
        assertTrue(e_.hasDuplicates());
    }
    @Test
    public void hasDuplicatesEq2Test() {
        EqList<MyEquallable> e_ = new EqList<MyEquallable>(new MyEquallable[0]);
        e_.add(new MyEquallable(1));
        e_.add(new MyEquallable(2));
        assertTrue(!e_.hasDuplicates());
    }
    @Test
    public void eqEq1Test() {
        EqList<MyEquallable> e_ = new EqList<MyEquallable>(new CollCapacity(3));
        e_.add(new MyEquallable(1));
        e_.add(new MyEquallable(2));
        e_.add(new MyEquallable(3));
        e_.add(new MyEquallable(1));
        EqList<MyEquallable> f_ = new EqList<MyEquallable>();
        f_.add(new MyEquallable(1));
        f_.add(new MyEquallable(2));
        f_.add(new MyEquallable(3));
        f_.add(new MyEquallable(1));
        assertTrue(e_.eq(f_));
    }
    @Test
    public void eqEq2Test() {
        EqList<MyEquallable> e_ = new EqList<MyEquallable>();
        e_.add(new MyEquallable(1));
        e_.add(new MyEquallable(2));
        e_.add(new MyEquallable(3));
        EqList<MyEquallable> f_ = new EqList<MyEquallable>();
        f_.add(new MyEquallable(1));
        f_.add(new MyEquallable(2));
        f_.add(new MyEquallable(3));
        f_.add(new MyEquallable(1));
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void eqEq3Test() {
        EqList<MyEquallable> e_ = new EqList<MyEquallable>();
        e_.add(new MyEquallable(1));
        e_.add(new MyEquallable(2));
        e_.add(new MyEquallable(3));
        e_.add(new MyEquallable(3));
        EqList<MyEquallable> f_ = new EqList<MyEquallable>();
        f_.add(new MyEquallable(1));
        f_.add(new MyEquallable(2));
        f_.add(new MyEquallable(3));
        f_.add(new MyEquallable(1));
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void indexBool1Test() {
        BooleanList e_ = new BooleanList(new BooleanList());
        e_.add(true);
        e_.add(false);
        e_.add(false);
        e_.add(true);
        Ints indexes_ = e_.indexesOfBool(true);
        assertEq(2, indexes_.size());
        assertEq(0, indexes_.get(0));
        assertEq(3, indexes_.get(1));
        assertTrue(e_.containsObj(true));
    }
    @Test
    public void indexBool2Test() {
        BooleanList e_ = new BooleanList(new BooleanList());
        e_.add(false);
        e_.add(false);
        Ints indexes_ = e_.indexesOfBool(true);
        assertEq(0, indexes_.size());
        assertTrue(!e_.containsObj(true));
    }
    @Test
    public void eqBool1Test() {
        BooleanList e_ = new BooleanList(new CollCapacity(3));
        e_.add(true);
        e_.add(false);
        e_.add(false);
        e_.add(true);
        BooleanList f_ = new BooleanList();
        f_.add(true);
        f_.add(false);
        f_.add(false);
        f_.add(true);
        assertTrue(e_.eq(f_));
    }
    @Test
    public void eqBool2Test() {
        BooleanList e_ = new BooleanList(new CollCapacity(3));
        e_.add(true);
        e_.add(false);
        e_.add(false);
        BooleanList f_ = new BooleanList(new Boolean[0]);
        f_.add(true);
        f_.add(false);
        f_.add(false);
        f_.add(true);
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void eqBool3Test() {
        BooleanList e_ = new BooleanList();
        e_.add(true);
        e_.add(false);
        e_.add(false);
        e_.add(false);
        BooleanList f_ = new BooleanList();
        f_.add(true);
        f_.add(false);
        f_.add(false);
        f_.add(true);
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void indexCmp1Test() {
        SortableCustList<MyCmp> e_ = new SortableCustList<MyCmp>(new SortableCustList<MyCmp>());
        e_.add(new MyCmp(1));
        e_.add(new MyCmp(2));
        e_.add(new MyCmp(3));
        e_.add(new MyCmp(1));
        int index_ = e_.indexOfObj(new MyCmp(1),1);
        assertEq(3, index_);
    }
    @Test
    public void indexCmp2Test() {
        SortableCustList<MyCmp> e_ = new SortableCustList<MyCmp>(new SortableCustList<MyCmp>());
        e_.add(new MyCmp(1));
        e_.add(new MyCmp(2));
        e_.add(new MyCmp(3));
        e_.add(new MyCmp(1));
        int index_ = e_.indexOfObj(new MyCmp(2),2);
        assertEq(-1, index_);
    }
    @Test
    public void eqCmp1Test() {
        SortableCustList<MyCmp> e_ = new SortableCustList<MyCmp>(new MyCmp[0]);
        e_.add(new MyCmp(1));
        e_.add(new MyCmp(2));
        e_.add(new MyCmp(3));
        e_.add(new MyCmp(1));
        SortableCustList<MyCmp> f_ = new SortableCustList<MyCmp>();
        f_.add(new MyCmp(1));
        f_.add(new MyCmp(2));
        f_.add(new MyCmp(3));
        f_.add(new MyCmp(1));
        assertTrue(e_.eq(f_));
    }
    @Test
    public void eqCmp2Test() {
        SortableCustList<MyCmp> e_ = new SortableCustList<MyCmp>();
        e_.add(new MyCmp(1));
        e_.add(new MyCmp(2));
        e_.add(new MyCmp(3));
        SortableCustList<MyCmp> f_ = new SortableCustList<MyCmp>();
        f_.add(new MyCmp(1));
        f_.add(new MyCmp(2));
        f_.add(new MyCmp(3));
        f_.add(new MyCmp(1));
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void eqCmp3Test() {
        SortableCustList<MyCmp> e_ = new SortableCustList<MyCmp>();
        e_.add(new MyCmp(1));
        e_.add(new MyCmp(2));
        e_.add(new MyCmp(3));
        e_.add(new MyCmp(3));
        SortableCustList<MyCmp> f_ = new SortableCustList<MyCmp>();
        f_.add(new MyCmp(1));
        f_.add(new MyCmp(2));
        f_.add(new MyCmp(3));
        f_.add(new MyCmp(1));
        assertTrue(!e_.eq(f_));
    }
    @Test
    public void sortTest() {
        SortableCustList<MyCmp> e_ = new SortableCustList<MyCmp>();
        e_.add(new MyCmp(1));
        e_.add(new MyCmp(2));
        e_.add(new MyCmp(3));
        e_.add(new MyCmp(1));
        e_.sort();
        SortableCustList<MyCmp> f_ = new SortableCustList<MyCmp>();
        f_.add(new MyCmp(1));
        f_.add(new MyCmp(1));
        f_.add(new MyCmp(2));
        f_.add(new MyCmp(3));
        assertTrue(e_.eq(f_));
    }
    @Test
    public void remove1Test() {
        SortableCustList<MyCmp> e_ = new SortableCustList<MyCmp>();
        e_.add(new MyCmp(1));
        e_.add(new MyCmp(2));
        e_.add(new MyCmp(3));
        e_.add(new MyCmp(1));
        e_.removeAllObj(new MyCmp(1));
        SortableCustList<MyCmp> f_ = new SortableCustList<MyCmp>();
        f_.add(new MyCmp(2));
        f_.add(new MyCmp(3));
        assertTrue(e_.eq(f_));
    }
    @Test
    public void remove2Test() {
        SortableCustList<MyCmp> e_ = new SortableCustList<MyCmp>();
        e_.add(new MyCmp(1));
        e_.add(new MyCmp(2));
        e_.add(new MyCmp(3));
        e_.add(new MyCmp(1));
        e_.removeObj(new MyCmp(4));
        SortableCustList<MyCmp> f_ = new SortableCustList<MyCmp>();
        f_.add(new MyCmp(1));
        f_.add(new MyCmp(2));
        f_.add(new MyCmp(3));
        f_.add(new MyCmp(1));
        assertTrue(e_.eq(f_));
    }
    @Test
    public void remove3Test() {
        SortableCustList<MyCmp> e_ = new SortableCustList<MyCmp>();
        e_.add(new MyCmp(1));
        e_.add(new MyCmp(2));
        e_.add(new MyCmp(3));
        e_.add(new MyCmp(1));
        SortableCustList<MyCmp> g_ = new SortableCustList<MyCmp>();
        g_.add(new MyCmp(2));
        g_.add(new MyCmp(3));
        g_.add(new MyCmp(5));
        e_.removeAllElements(g_);
        SortableCustList<MyCmp> f_ = new SortableCustList<MyCmp>();
        f_.add(new MyCmp(1));
        f_.add(new MyCmp(1));
        assertTrue(e_.eq(f_));
    }
    @Test
    public void remove4Test() {
        SortableCustList<MyCmp> e_ = new SortableCustList<MyCmp>();
        e_.add(new MyCmp(1));
        e_.add(new MyCmp(2));
        e_.add(new MyCmp(3));
        e_.add(new MyCmp(1));
        e_.removeObj(new MyCmp(1));
        SortableCustList<MyCmp> f_ = new SortableCustList<MyCmp>();
        f_.add(new MyCmp(2));
        f_.add(new MyCmp(3));
        f_.add(new MyCmp(1));
        assertTrue(e_.eq(f_));
    }
    @Test
    public void containsAllObj1Test() {
        SortableCustList<MyCmp> e_ = new SortableCustList<MyCmp>();
        e_.add(new MyCmp(1));
        e_.add(new MyCmp(2));
        e_.add(new MyCmp(3));
        e_.add(new MyCmp(1));
        SortableCustList<MyCmp> f_ = new SortableCustList<MyCmp>();
        f_.add(new MyCmp(1));
        f_.add(new MyCmp(1));
        assertTrue(e_.containsAllObj(f_));
    }
    @Test
    public void containsAllObj2Test() {
        SortableCustList<MyCmp> e_ = new SortableCustList<MyCmp>();
        e_.add(new MyCmp(1));
        e_.add(new MyCmp(2));
        e_.add(new MyCmp(3));
        e_.add(new MyCmp(1));
        SortableCustList<MyCmp> f_ = new SortableCustList<MyCmp>();
        f_.add(new MyCmp(1));
        f_.add(new MyCmp(4));
        assertTrue(!e_.containsAllObj(f_));
    }
}
