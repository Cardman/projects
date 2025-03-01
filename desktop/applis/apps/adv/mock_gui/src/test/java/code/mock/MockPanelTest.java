package code.mock;

import code.gui.*;
import code.gui.images.MetaDimension;
import org.junit.Test;

public final class MockPanelTest extends EquallableMockGuiUtil {
    @Test
    public void p1() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newPageBox();
        p_.add(new MockPaintableLabelAbs());
        p_.add(new MockPlainLabel(""));
//        p_.repaintChildren(pr_.getImageFactory());
        assertEq(2,p_.getComponentCount());
        assertSame(MockLayout.PAGE,((MockPanel)p_).getLayout());
    }
    @Test
    public void p2() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newLineBox();
        AbsPanel g_ = pr_.getCompoFactory().newGrid(1, 1);
        MockPaintableLabelAbs comp_ = new MockPaintableLabelAbs();
        comp_.setSize(new MetaDimension(10,10));
        g_.add(comp_);
        g_.add(new MockPlainLabel(""));
        p_.add(g_);
        p_.add(new MockPaintableLabelAbs());
        MockPlainLabel one_ = new MockPlainLabel("");
        p_.add(one_,0);
        p_.add(one_,0);
//        p_.repaintSecondChildren(pr_.getImageFactory());
        assertEq(3,p_.getComponentCount());
    }
    @Test
    public void p3() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        MockClock comp_ = new MockClock();
        p_.add(comp_.getComponent());
        comp_.setTimeText(pr_.getThreadFactory());
        assertEq(1,p_.getComponentCount());
    }
    @Test
    public void p4() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newLineBox();
        AbsPanel g_ = pr_.getCompoFactory().newGrid(1, 1);
        g_.add(new MockPaintableLabelAbs());
        g_.add(new MockPlainLabel(""));
        p_.add(g_);
        p_.add(new MockPaintableLabelAbs());
        p_.add(new MockPlainLabel(""));
//        p_.repaintSecondChildren(pr_.getImageFactory());
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
        assertEq(0,p_.getComponentCount());
    }
    @Test
    public void p7() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        assertEq(0,p_.getComponentCount());
    }
    @Test
    public void p8() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        assertEq(0,p_.getComponentCount());
    }
    @Test
    public void p9() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        assertEq(0,p_.getComponentCount());
    }
    @Test
    public void p10() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
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
        p_.add(l1_,"");
        assertEq(-1,p_.remove(l2_));
        assertEq(1,p_.getComponentCount());
        p_.remove(0);
        assertEq(0,p_.getComponentCount());
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
    @Test
    public void p16() {
        MockProgramInfosSample pr_ = init();
        AbsPanel p_ = pr_.getCompoFactory().newLineBox();
        AbsPanel g_ = pr_.getCompoFactory().newGrid();
        MockPaintableLabelAbs comp_ = new MockPaintableLabelAbs();
        comp_.setSize(new MetaDimension(10,10));
        AbsGridConstraints cts_ = pr_.getCompoFactory().newGridCts();
        cts_.gridwidth(cts_.gridwidth());
        cts_.gridheight(cts_.gridheight());
        cts_.gridx(cts_.gridx());
        cts_.gridy(cts_.gridy());
        g_.add(comp_, cts_);
        g_.add(new MockPlainLabel(""));
        p_.add(g_);
        p_.add(new MockPaintableLabelAbs());
        MockPlainLabel one_ = new MockPlainLabel("");
        p_.add(one_,0);
        p_.add(one_,0);
//        p_.repaintSecondChildren(pr_.getImageFactory());
        assertEq(3,p_.getComponentCount());
    }
}
