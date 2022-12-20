package code.mock;

import code.threads.AbstractConcurrentMap;
import code.threads.FileStruct;
import org.junit.Test;

public final class MockConcurrentMapTest extends EquallableMockGuiUtil {

    @Test
    public void m1() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        FileStruct v_ = new FileStruct(null, 0);
        assertNull(m_.put("", v_));
        assertSame(v_,m_.get(""));
    }

    @Test
    public void m2() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        assertNull(m_.replace("", new FileStruct(null,0)));
        assertNull(m_.get(""));
    }

    @Test
    public void m3() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        FileStruct v_ = new FileStruct(null, 0);
        assertNull(m_.put("", v_));
        FileStruct w_ = new FileStruct(null, 0);
        assertSame(v_,m_.replace("", w_));
        assertSame(w_,m_.get(""));
    }

    @Test
    public void m4() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        FileStruct v_ = new FileStruct(null, 0);
        assertNull(m_.putIfAbsent("", v_));
        assertSame(v_,m_.get(""));
    }

    @Test
    public void m5() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        FileStruct v_ = new FileStruct(null, 0);
        assertNull(m_.put("", v_));
        assertSame(v_,m_.putIfAbsent("", new FileStruct(null, 0)));
        assertSame(v_,m_.get(""));
    }

    @Test
    public void m6() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        assertNull(m_.remove(""));
    }

    @Test
    public void m7() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        FileStruct v_ = new FileStruct(null, 0);
        assertNull(m_.put("", v_));
        assertSame(v_,m_.remove(""));
    }

    @Test
    public void m8() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        assertFalse(m_.containsValue(new FileStruct(null,0)));
    }

    @Test
    public void m9() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        FileStruct v_ = new FileStruct(null, 0);
        assertNull(m_.put("", v_));
        assertTrue(m_.containsValue(v_));
    }

    @Test
    public void m10() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        assertNull(m_.put("", new FileStruct(null, 0)));
        assertFalse(m_.containsValue(new FileStruct(null,0)));
    }

    @Test
    public void m11() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        assertFalse(m_.containsKey(""));
    }

    @Test
    public void m12() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        assertNull(m_.put("", new FileStruct(null,0)));
        assertTrue(m_.containsKey(""));
    }

    @Test
    public void m13() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        assertNull(m_.put("", new FileStruct(null, 0)));
        assertFalse(m_.containsKey(" "));
    }

    @Test
    public void m14() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        assertNull(m_.put("", new FileStruct(null, 0)));
        assertTrue(m_.entrySet().iterator().hasNext());
    }

    @Test
    public void m15() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        assertNull(m_.put("", new FileStruct(null, 0)));
        assertTrue(m_.keySet().iterator().hasNext());
    }

    @Test
    public void m16() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        assertNull(m_.put("", new FileStruct(null, 0)));
        assertTrue(m_.values().iterator().hasNext());
    }

    @Test
    public void m17() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        assertNull(m_.put("", new FileStruct(null, 0)));
        m_.clear();
        assertTrue(m_.isEmpty());
    }

    @Test
    public void m18() {
        MockConcurrentMapStringFileStruct m_ = new MockConcurrentMapStringFileStruct();
        assertNull(m_.put("", new FileStruct(null, 0)));
        assertFalse(m_.isEmpty());
    }


    @Test
    public void m22() {
        AbstractConcurrentMap<String, FileStruct> m_ = init().getThreadFactory().newMapStringFileStruct();
        assertEq(0,m_.size());
    }

}
