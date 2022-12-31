package code.mock;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsGraphicListStr;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThread;
import org.junit.Test;

public final class MockRunnableStructTest extends EquallableMockCdmUtil {
    @Test
    public void c14() {
        AbstractInterceptorStdCaller int_ = new MockInterceptor().newInterceptorStdCaller("");
        assertTrue(int_.exitAfterCallInt(new MockInitializer(),null,null));
        assertTrue(int_.stop(new MockInitializer(),null,null));
        assertTrue(int_.invoke(new MockStdCaller(),null,null,null,null,null).getValue().getStruct().sameReference(NullStruct.NULL_VALUE));
    }
    @Test
    public void t17() {
        MockRunnableStruct r_ = new MockRunnableStruct("");
        MockThread th_ = new MockThread(r_, true, new MockAtomicLong());
        th_.start();
        assertTrue(r_.isStarted());
    }
    @Test
    public void t18() {
        MockRunnableStruct r_ = new MockRunnableStruct("");
        assertFalse(r_.isStarted());
    }
    @Test
    public void t19() {
        MockRunnableStruct r_ = new MockRunnableStruct("");
        assertEq("",r_.getClassName(null));
    }

    @Test
    public void m19() {
        AbstractConcurrentMap<String, Struct> m_ = new MockInterceptor().newMapStringStruct();
        assertEq(0,m_.size());
    }

    @Test
    public void m20() {
        AbstractConcurrentMap<Struct, Struct> m_ = new MockInterceptor().newMapStructStruct();
        assertEq(0,m_.size());
    }

    @Test
    public void m21() {
        AbstractConcurrentMap<AbstractThread, Struct> m_ = new MockInterceptor().newMapAbstractThreadStruct();
        assertEq(0,m_.size());
    }

    @Test
    public void m34() {
        MockAdvGraphicListGenerator g_ = new MockAdvGraphicListGenerator(true);
        assertTrue(g_.isCust());
        MockAdvGraphicListGenerator h_ = new MockAdvGraphicListGenerator(false);
        assertFalse(h_.isCust());
        AbsGraphicListStr f_ = g_.createMult(null,null);
        f_.add(NullStruct.NULL_VALUE);
        f_.setSelectedIndice(0);
        f_.setCustCell(null,null,null,null);
        f_.setDefCell(null,null);
        f_.updateGraphics();
        assertTrue(f_.isCust());
        AbsGraphicListStr e_ = g_.createSimple(null,null);
        e_.add(NullStruct.NULL_VALUE);
        e_.setSelectedIndice(0);
        e_.setCustCell(null,null,null,null);
        e_.setDefCell(null,null);
        e_.updateGraphics();
        assertTrue(e_.isCust());
    }
    @Test
    public void bs1() {
        MockMouseButtons m_ = new MockMouseButtons(false,false,false,0);
        assertFalse(m_.isLeftMouseButton());
        assertFalse(m_.isMiddleMouseButton());
        assertFalse(m_.isRightMouseButton());
        assertEq(0,m_.getClickCount());
    }
    @Test
    public void bs2() {
        MockMouseButtons m_ = new MockMouseButtons(true,true,true,1);
        assertTrue(m_.isLeftMouseButton());
        assertTrue(m_.isMiddleMouseButton());
        assertTrue(m_.isRightMouseButton());
        assertEq(1,m_.getClickCount());
    }
    @Test
    public void wh() {
        MockMouseWheel m_ = new MockMouseWheel(1);
        assertEq(1,m_.getWheelRotation());
    }
}
