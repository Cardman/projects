package code.mock;

import code.maths.montecarlo.*;
import org.junit.Test;

public final class MockClipStreamTest extends EquallableMockGuiUtil {
    @Test
    public void c1() {
        MockClipStream c_ = new MockClipStream(DefaultGenerator.sizeOne(), 152351, true);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        assertTrue(c_.isRunning());
        assertEq(0, list_.getKi());
        assertEq(0, list_.getPos());
        assertEq("", list_.getTy());

    }
    @Test
    public void c2() {
        MockClipStream c_ = new MockClipStream(DefaultGenerator.sizeOne(), 152351, true);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        c_.stop(120);
        assertFalse(c_.isRunning());
        assertEq(3, list_.getKi());
        assertEq(120, list_.getPos());
        assertEq("stop", list_.getTy());

    }
    @Test
    public void c3() {
        MockClipStream c_ = new MockClipStream(DefaultGenerator.sizeOne(), 152351, true);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        c_.stop(120);
        c_.resume();
        assertEq(3, list_.getKi());
        assertEq(120, list_.getPos());
        assertEq("start", list_.getTy());

    }
    @Test
    public void c4() {
        MockClipStream c_ = new MockClipStream(new DefaultGenerator(0.75), 152351, true);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        c_.stop(120);
        c_.resume();
        assertTrue(c_.closeClipStream());
        assertEq(3, list_.getKi());
        assertEq(120, list_.getPos());
        assertEq("close", list_.getTy());

    }
    @Test
    public void c5() {
        MockClipStream c_ = new MockClipStream(new DefaultGenerator(0.25), 152351, true);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        c_.stop(120);
        c_.resume();
        assertFalse(c_.closeClipStream());
        assertEq(3, list_.getKi());
        assertEq(120, list_.getPos());
        assertEq("close", list_.getTy());

    }
    @Test
    public void c6() {
        MockClipStream c_ = new MockClipStream(DefaultGenerator.sizeOne(), 152351, false);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        assertTrue(c_.isRunning());
        assertEq(0, list_.getKi());
        assertEq(0, list_.getPos());
        assertEq("", list_.getTy());

    }
    @Test
    public void c7() {
        MockClipStream c_ = new MockClipStream(DefaultGenerator.sizeOne(), 152351, false);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        c_.stop(120);
        assertFalse(c_.isRunning());
        assertEq(4, list_.getKi());
        assertEq(120, list_.getPos());
        assertEq("stop", list_.getTy());

    }
    @Test
    public void c8() {
        MockClipStream c_ = new MockClipStream(DefaultGenerator.sizeOne(), 152351, false);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        c_.stop(120);
        c_.resume();
        assertEq(4, list_.getKi());
        assertEq(120, list_.getPos());
        assertEq("start", list_.getTy());

    }
    @Test
    public void c9() {
        MockClipStream c_ = new MockClipStream(new DefaultGenerator(0.75), 152351, false);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        c_.stop(120);
        c_.resume();
        assertTrue(c_.closeClipStream());
        assertEq(4, list_.getKi());
        assertEq(120, list_.getPos());
        assertEq("close", list_.getTy());

    }
    @Test
    public void c10() {
        MockClipStream c_ = new MockClipStream(new DefaultGenerator(0.25), 152351, false);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        c_.stop(120);
        c_.resume();
        assertFalse(c_.closeClipStream());
        assertEq(4, list_.getKi());
        assertEq(120, list_.getPos());
        assertEq("close", list_.getTy());

    }
    @Test
    public void c11() {
        MockClipStream c_ = new MockClipStream(new DefaultGenerator(0.25), 152351, true);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        c_.setPosition(120);
        assertTrue(c_.isRunning());
    }
    @Test
    public void c12() {
        MockClipStream c_ = new MockClipStream(new DefaultGenerator(0.25), 152351, false);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        c_.setPosition(120);
        assertTrue(c_.isRunning());
    }
    @Test
    public void c13() {
        MockClipStream c_ = new MockClipStream(new DefaultGenerator(0.25), 152351, true);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        c_.setPosition(152351);
        assertFalse(c_.isRunning());
        assertEq(3, list_.getKi());
        assertEq(152351, list_.getPos());
        assertEq("stop", list_.getTy());
    }
    @Test
    public void c14() {
        MockClipStream c_ = new MockClipStream(new DefaultGenerator(0.25), 152351, false);
        MockLineShortListenable list_ = new MockLineShortListenable();
        c_.addLineListener(list_);
        c_.setPosition(152351);
        assertFalse(c_.isRunning());
        assertEq(4, list_.getKi());
        assertEq(152351, list_.getPos());
        assertEq("stop", list_.getTy());
    }
}
