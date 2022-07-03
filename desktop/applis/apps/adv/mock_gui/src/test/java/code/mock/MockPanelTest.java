package code.mock;

import code.gui.AbsPanel;
import code.gui.images.MetaDimension;
import org.junit.Test;

public final class MockPanelTest extends EquallableMockGuiUtil {
    @Test
    public void p1() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newPageBox();
        p_.add(new MockPaintableLabel(new MockMetaLabel()));
        p_.add(new MockPlainLabel(""));
        p_.repaintChildren(pr_.getImageFactory());
        assertEq(2,p_.getComponentCount());
        assertSame(MockLayout.PAGE,((MockPanel)p_).getLayout());
    }
    @Test
    public void p2() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newLineBox();
        AbsPanel g_ = pr_.getCompoFactory().newGrid(1, 1);
        MockPaintableLabel comp_ = new MockPaintableLabel(new MockMetaLabel());
        comp_.setSize(new MetaDimension(10,10));
        g_.add(comp_);
        g_.add(new MockPlainLabel(""));
        p_.add(g_);
        p_.add(new MockPaintableLabel(new MockMetaLabel()));
        p_.add(new MockPlainLabel(""));
        p_.repaintSecondChildren(pr_.getImageFactory());
        assertEq(3,p_.getComponentCount());
    }
    @Test
    public void p3() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        MockClock comp_ = new MockClock();
        p_.add(comp_);
        comp_.setTimeText(pr_.getThreadFactory());
        assertEq(1,p_.getComponentCount());
    }
    @Test
    public void p4() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newLineBox();
        AbsPanel g_ = pr_.getCompoFactory().newGrid(1, 1);
        g_.add(new MockMetaLabel());
        g_.add(new MockPlainLabel(""));
        p_.add(g_);
        p_.add(new MockPaintableLabel(new MockMetaLabel()));
        p_.add(new MockPlainLabel(""));
        p_.repaintSecondChildren(pr_.getImageFactory());
        assertEq(3,p_.getComponentCount());
    }
    @Test
    public void p5() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        MockPlainLabel pl_ = new MockPlainLabel("");
        p_.add(pl_);
        p_.add(pl_);
        assertEq(1,p_.getComponentCount());
    }
    @Test
    public void p6() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        p_.add(new MockMetaLabel(),0);
        assertEq(1,p_.getComponentCount());
    }
    @Test
    public void p7() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        MockMetaLabel l_ = new MockMetaLabel();
        p_.add(l_,0);
        p_.add(l_,0);
        assertEq(1,p_.getComponentCount());
    }
    @Test
    public void p8() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        p_.add(new MockMetaLabel(),"");
        assertEq(1,p_.getComponentCount());
    }
    @Test
    public void p9() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        MockMetaLabel l_ = new MockMetaLabel();
        p_.add(l_,"");
        p_.add(l_,"");
        assertEq(1,p_.getComponentCount());
    }
    @Test
    public void p10() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        p_.add(new MockMetaLabel(),"");
        p_.remove(0);
        assertEq(0,p_.getComponentCount());
    }
    @Test
    public void p11() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        MockPlainLabel l_ = new MockPlainLabel("");
        p_.add(l_,"");
        assertEq(0,p_.remove(l_));
        assertEq(0,p_.getComponentCount());
    }
    @Test
    public void p12() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        MockPlainLabel l1_ = new MockPlainLabel("");
        MockPlainLabel l2_ = new MockPlainLabel("");
        p_.add(l1_,"");
        assertEq(-1,p_.remove(l2_));
        assertEq(1,p_.getComponentCount());
    }
    @Test
    public void p13() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        MockPlainLabel l_ = new MockPlainLabel("");
        p_.add(l_,"");
        p_.add(new MockPlainLabel(""),"");
        assertEq(0,p_.remove(l_));
        assertEq(1,p_.getComponentCount());
    }
    @Test
    public void p14() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        MockPlainLabel l_ = new MockPlainLabel("");
        p_.add(new MockPlainLabel(""),"");
        p_.add(l_,"");
        assertEq(1,p_.remove(l_));
        assertEq(1,p_.getComponentCount());
    }
    @Test
    public void p15() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        MockPlainLabel l_ = new MockPlainLabel("");
        p_.add(new MockPlainLabel(""),"");
        p_.add(l_,"");
        p_.removeAll();
        assertEq(0,p_.getComponentCount());
    }
}
