package code.mock;

import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThread;
import code.threads.FileStruct;
import org.junit.Test;

public final class MockConcurrentMapTest extends EquallableMockGuiUtil {

    @Test
    public void m1() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", NullStruct.NULL_VALUE));
        assertTrue(NullStruct.NULL_VALUE.sameReference(m_.get("")));
    }

    @Test
    public void m2() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.replace("", NullStruct.NULL_VALUE));
        assertNull(m_.get(""));
    }

    @Test
    public void m3() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", NullStruct.NULL_VALUE));
        assertTrue(NullStruct.NULL_VALUE.sameReference(m_.replace("", new IntStruct(0))));
        assertTrue(new IntStruct(0).sameReference(m_.get("")));
    }

    @Test
    public void m4() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.putIfAbsent("", NullStruct.NULL_VALUE));
        assertTrue(NullStruct.NULL_VALUE.sameReference(m_.get("")));
    }

    @Test
    public void m5() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", NullStruct.NULL_VALUE));
        assertTrue(NullStruct.NULL_VALUE.sameReference(m_.putIfAbsent("", new IntStruct(0))));
        assertTrue(NullStruct.NULL_VALUE.sameReference(m_.get("")));
    }

    @Test
    public void m6() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.remove(""));
    }

    @Test
    public void m7() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", NullStruct.NULL_VALUE));
        assertTrue(NullStruct.NULL_VALUE.sameReference(m_.remove("")));
    }

    @Test
    public void m8() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertFalse(m_.containsValue(NullStruct.NULL_VALUE));
    }

    @Test
    public void m9() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", NullStruct.NULL_VALUE));
        assertTrue(m_.containsValue(NullStruct.NULL_VALUE));
    }

    @Test
    public void m10() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", new IntStruct(0)));
        assertFalse(m_.containsValue(NullStruct.NULL_VALUE));
    }

    @Test
    public void m11() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertFalse(m_.containsKey(""));
    }

    @Test
    public void m12() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", NullStruct.NULL_VALUE));
        assertTrue(m_.containsKey(""));
    }

    @Test
    public void m13() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", new IntStruct(0)));
        assertFalse(m_.containsKey(" "));
    }

    @Test
    public void m14() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", new IntStruct(0)));
        assertTrue(m_.entrySet().iterator().hasNext());
    }

    @Test
    public void m15() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", new IntStruct(0)));
        assertTrue(m_.keySet().iterator().hasNext());
    }

    @Test
    public void m16() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", new IntStruct(0)));
        assertTrue(m_.values().iterator().hasNext());
    }

    @Test
    public void m17() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", new IntStruct(0)));
        m_.clear();
        assertTrue(m_.isEmpty());
    }

    @Test
    public void m18() {
        MockConcurrentMapStringStruct m_ = new MockConcurrentMapStringStruct();
        assertNull(m_.put("", new IntStruct(0)));
        assertFalse(m_.isEmpty());
    }

    @Test
    public void m19() {
        AbstractConcurrentMap<String, Struct> m_ = init().getInterceptor().newMapStringStruct();
        assertEq(0,m_.size());
    }

    @Test
    public void m20() {
        AbstractConcurrentMap<Struct, Struct> m_ = init().getInterceptor().newMapStructStruct();
        assertEq(0,m_.size());
    }

    @Test
    public void m21() {
        AbstractConcurrentMap<AbstractThread, Struct> m_ = init().getInterceptor().newMapAbstractThreadStruct();
        assertEq(0,m_.size());
    }

    @Test
    public void m22() {
        AbstractConcurrentMap<String, FileStruct> m_ = init().getThreadFactory().newMapStringFileStruct();
        assertEq(0,m_.size());
    }

}
