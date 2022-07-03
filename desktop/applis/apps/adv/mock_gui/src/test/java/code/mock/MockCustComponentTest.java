package code.mock;

import code.gui.AbsPanel;
import code.gui.events.*;
import code.gui.images.MetaDimension;
import org.junit.Test;

public final class MockCustComponentTest extends EquallableMockGuiUtil {
    @Test
    public void c1() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        AbsPanel p_ = pr_.getCompoFactory().newBorder();
        p_.add(f_);
        MetaDimension d_ = p_.getPreferredSizeValue();
        assertEq(1,d_.getHeight());
        assertEq(1,d_.getWidth());
    }
    @Test
    public void c2() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        AbsPanel p_ = pr_.getCompoFactory().newBorder();
        p_.add(f_);
        p_.setPreferredSize(new MetaDimension(2,2));
        MetaDimension d_ = p_.getPreferredSizeValue();
        assertEq(2,d_.getHeight());
        assertEq(2,d_.getWidth());
    }
    @Test
    public void c3() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        AbsPanel p_ = pr_.getCompoFactory().newBorder();
        p_.add(f_);
        p_.setPreferredSize(null);
        MetaDimension d_ = p_.getPreferredSizeValue();
        assertEq(1,d_.getHeight());
        assertEq(1,d_.getWidth());
    }
    @Test
    public void c4() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        AbsPanel p_ = pr_.getCompoFactory().newBorder();
        p_.add(f_);
        p_.setVisible(false);
        MetaDimension d_ = p_.getPreferredSizeValue();
        assertEq(0,d_.getHeight());
        assertEq(0,d_.getWidth());
    }
    @Test
    public void c5() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        AbsPanel p_ = pr_.getCompoFactory().newBorder();
        p_.add(f_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        assertEq(1,f_.getHeight());
        assertEq(1,f_.getWidth());
    }
    @Test
    public void c6() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        AbsPanel p_ = pr_.getCompoFactory().newAbsolute();
        f_.setSize(new MetaDimension(15,2));
        p_.add(f_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        assertEq(2,f_.getHeight());
        assertEq(15,f_.getWidth());
    }
    @Test
    public void c7() {
        MockTextField f_ = new MockTextField();
        assertEq(1,f_.heightFont());
        assertEq(5,f_.stringWidth("hello"));
    }
    @Test
    public void c8() {
        MockTextField f_ = new MockTextField();
        f_.setNullFont();
        assertEq(1,f_.heightFont());
        assertEq(5,f_.stringWidth("hello"));
    }
    @Test
    public void c9() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        p_.add(f_);
        assertEq(0,p_.getAccessible().size());
    }
    @Test
    public void c10() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        f_.setPreferredSize(new MetaDimension(1,1));
        f_.setVisible(false);
        p_.add(f_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        assertEq(0,p_.getAccessible().size());
    }
    @Test
    public void c11() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        f_.setPreferredSize(new MetaDimension(1,1));
        p_.add(f_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        assertEq(1,p_.getAccessible().size());
    }
    @Test
    public void c12() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        p_.add(f_);
        assertEq(1,p_.getVisible().size());
    }
    @Test
    public void c13() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        f_.setPreferredSize(new MetaDimension(1,1));
        f_.setVisible(false);
        p_.add(f_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        assertEq(0,p_.getVisible().size());
    }
    @Test
    public void c14() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        f_.setPreferredSize(new MetaDimension(1,1));
        p_.add(f_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        assertEq(1,p_.getVisible().size());
    }
    @Test
    public void c15() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        f_.setPreferredSize(new MetaDimension(1,1));
        f_.setEnabled(false);
        p_.add(f_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        assertEq(1,p_.getVisible().size());
    }
    @Test
    public void c16() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        p_.setVisible(false);
        f_.setPreferredSize(new MetaDimension(1,1));
        f_.setEnabled(false);
        p_.add(f_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        assertEq(0,p_.getVisible().size());
    }
    @Test
    public void c17() {
        MockTextField f_ = new MockTextField();
        f_.setHandCursor();
        f_.setFont("",0,0);
        f_.setBackground(10);
        f_.setForeground(10);
        assertEq(10,f_.getBackgroundValue());
        assertEq(10,f_.getForegroundValue());
        f_.setBackground(f_);
        f_.setForeground(f_);
        assertEq(10,f_.getBackgroundValue());
        assertEq(10,f_.getForegroundValue());
        f_.setAutoscrolls(true);
        assertTrue(f_.isAutoscrolls());
        f_.setToolTipText("_");
        assertEq("_",f_.getToolTipText());
        f_.addMouseListener((AbsMouseListener) null);
        f_.addMouseListener((AbsMouseListenerPresRel) null);
        f_.addMouseListener((AbsMouseListenerEnt) null);
        f_.addMouseListener((AbsMouseListenerCl) null);
        f_.addMouseListener((AbsMouseListenerEer) null);
        f_.addMouseListener((AbsMouseListenerIntRel) null);
        f_.addMouseListener((AbsMouseListenerWithoutClick) null);
        f_.addMouseListener((AbsMouseListenerWithoutClickPr) null);
        f_.addMouseMotionListener(null);
        f_.addMouseWheelListener(null);
        f_.addKeyListener(null);
        f_.addKeyListener((AbsKeyListenerPress)null);
        f_.addKeyListener((AbsKeyListenerReleased)null);
        assertEq(1,f_.getMouseClListeners().size());
        assertEq(1,f_.getMouseEerListeners().size());
        assertEq(1,f_.getMouseEntListeners().size());
        assertEq(1,f_.getMouseListeners().size());
        assertEq(1,f_.getMouseMotionListeners().size());
        assertEq(1,f_.getMouseWheelListeners().size());
        assertEq(1,f_.getMousePresRelListeners().size());
        assertEq(1,f_.getMouseWithoutClickListeners().size());
        assertEq(1,f_.getMouseWithoutClickPrListeners().size());
        assertEq(1,f_.getKeyListeners().size());
        assertEq(1,f_.getKeyPressListeners().size());
        assertEq(1,f_.getKeyReleasedListeners().size());
        assertEq(1,f_.getMouseIntRelListeners().size());
        f_.removeMouseListener(null);
        f_.removeMouseMotionListener(null);
        f_.removeMouseWheelListener(null);
        f_.removeKeyListener(null);
        assertEq(0,f_.getMouseListeners().size());
        assertEq(0,f_.getMouseMotionListeners().size());
        assertEq(0,f_.getMouseWheelListeners().size());
        assertEq(0,f_.getKeyListeners().size());
        f_.validate();
        f_.revalidate();
        f_.setLineBorder(1);
        assertEq("line 1",f_.getBorder());
        f_.setLineBorder(1,2);
        assertEq("line 1 2",f_.getBorder());
        f_.setTitledBorder("_");
        assertEq("title _",f_.getBorder());
        f_.setRaisedBorder();
        assertEq("raised",f_.getBorder());
        f_.setLoweredBorder();
        assertEq("lower",f_.getBorder());
        f_.top();
        assertSame(MockPosition.LEFT,f_.getVertical());
        assertSame(MockPosition.CENTER,f_.getHorizontal());
        f_.bottom();
        assertSame(MockPosition.RIGHT,f_.getVertical());
        assertSame(MockPosition.CENTER,f_.getHorizontal());
        f_.centerVert();
        assertSame(MockPosition.CENTER,f_.getVertical());
        assertSame(MockPosition.CENTER,f_.getHorizontal());
        f_.left();
        assertSame(MockPosition.CENTER,f_.getVertical());
        assertSame(MockPosition.LEFT,f_.getHorizontal());
        f_.right();
        assertSame(MockPosition.CENTER,f_.getVertical());
        assertSame(MockPosition.RIGHT,f_.getHorizontal());
        f_.centerHoriz();
        assertSame(MockPosition.CENTER,f_.getVertical());
        assertSame(MockPosition.CENTER,f_.getHorizontal());
        f_.setOpaque(true);
        assertTrue(f_.isOpaque());
        f_.setLocation(3,4);
        assertEq(3,f_.getXcoords());
        assertEq(4,f_.getYcoords());
    }
}
