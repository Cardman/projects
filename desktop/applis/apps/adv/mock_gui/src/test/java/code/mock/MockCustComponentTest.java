package code.mock;

import code.gui.AbsCustComponent;
import code.gui.AbsPanel;
import code.gui.AbsScrollPane;
import code.gui.events.*;
import code.gui.images.MetaDimension;
import code.util.CustList;
import code.util.core.NumberUtil;
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
        assertEq(5,f_.stringWidth(f_.getMetaFont(),"hello"));
    }
    @Test
    public void c8() {
        MockTextField f_ = new MockTextField();
        f_.setNullFont();
        assertEq(1,f_.heightFont());
        assertEq(5,f_.stringWidth(f_.getMetaFont(),"hello"));
    }
    @Test
    public void c9() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        p_.add(f_);
        assertEq(0,p_.getAccessible().size());
        assertEq(0,p_.getTreeAccessible().size());
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
        assertEq(0,p_.getTreeAccessible().size());
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
        assertEq(1,p_.getTreeAccessible().size());
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
        f_.disabledRichText(true);
        f_.disabledRichText(false);
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
        MockFocusListener m_ = new MockFocusListener();
        f_.addFocusListener(m_);
        MockMouseListener mm_ = new MockMouseListener();
        f_.addMouseListener((AbsMouseListener) mm_);
        f_.addMouseListener((AbsMouseListenerPresRel) mm_);
        f_.addMouseListener((AbsMouseListenerEnt) mm_);
        f_.addMouseListener((AbsMouseListenerCl) mm_);
        f_.addMouseListener((AbsMouseListenerEer) mm_);
        f_.addMouseListener((AbsMouseListenerIntRel) mm_);
        f_.addMouseListener((AbsMouseListenerWithoutClick) mm_);
        f_.addMouseListener((AbsMouseListenerWithoutClickPr) mm_);
        mm_.mouseClicked(null,null,null);
        mm_.mouseEntered(null,null,null);
        mm_.mouseExited(null,null,null);
        mm_.mousePressed(null,null,null);
        mm_.mouseReleased(null,null,null);
        mm_.mouseDragged(null,null,null);
        mm_.mouseMoved(null,null,null);
        mm_.mouseWheelMoved(null,null,null, null);
        assertEq(1, NumberUtil.signum(mm_.getState()));
        f_.addMouseMotionListener(mm_);
        f_.addMouseWheelListener(mm_);
        MockKeyListener k_ = new MockKeyListener();
        k_.keyPressed(null,' ',0);
        k_.keyReleased(null,' ',0);
        k_.keyTyped(null,' ');
        assertEq(1,NumberUtil.signum(k_.getState()));
        f_.addKeyListener(k_);
        f_.addKeyListener((AbsKeyListenerPress)k_);
        f_.addKeyListener((AbsKeyListenerReleased)k_);
        assertEq(1,f_.getFocusListeners().size());
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
        f_.getFocusListeners().get(0).focusGained();
        assertTrue(m_.isGained());
        f_.getFocusListeners().get(0).focusLost();
        assertFalse(m_.isGained());
        f_.getFocusListeners().get(0).focusGained();
        assertTrue(m_.isGained());
        f_.getFocusListeners().get(0).focusGained();
        assertTrue(m_.isGained());
        f_.getFocusListeners().get(0).focusLost();
        assertFalse(m_.isGained());
        f_.getFocusListeners().get(0).focusLost();
        assertFalse(m_.isGained());
        f_.removeFocusListener(m_);
        f_.removeMouseListener(mm_);
        f_.removeMouseListener((AbsMouseListenerIntRel) mm_);
        f_.removeMouseMotionListener(mm_);
        f_.removeMouseWheelListener(mm_);
        f_.removeKeyListener(k_);
        assertEq(0,f_.getFocusListeners().size());
        assertEq(0,f_.getMouseListeners().size());
        assertEq(0,f_.getMouseListenersRel().size());
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
    @Test
    public void c18() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        MockPanel pSub_ = (MockPanel) pr_.getCompoFactory().newBorder();
        pSub_.setVisible(false);
        f_.setPreferredSize(new MetaDimension(1,1));
        pSub_.add(f_);
        p_.add(pSub_);
        MockTextArea ta_ = new MockTextArea();
        ta_.setPreferredSize(new MetaDimension(1,1));
        p_.add(ta_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        ta_.recalculate();
        CustList<AbsCustComponent> acc_ = p_.getTreeAccessible();
        assertEq(1, acc_.size());
        assertSame(ta_,acc_.get(0));
    }
    @Test
    public void c19() {
        MockProgramInfosSample pr_ = init();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        CustList<AbsCustComponent> acc_ = p_.getTreeAccessible();
        assertEq(0, acc_.size());
    }
    @Test
    public void c20() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        MockPanel pSub_ = (MockPanel) pr_.getCompoFactory().newBorder();
        pSub_.setVisible(false);
        f_.setPreferredSize(new MetaDimension(1,1));
        pSub_.add(f_);
        p_.add(pSub_);
        AbsPanel ta_ = pr_.getCompoFactory().newLineBoxLeaf();
        ta_.setPreferredSize(new MetaDimension(1,1));
        p_.add(ta_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        ta_.recalculate();
        CustList<AbsCustComponent> acc_ = p_.getTreeAccessible();
        assertEq(1, acc_.size());
        assertSame(ta_,acc_.get(0));
    }
    @Test
    public void c21() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        MockPanel pSub_ = (MockPanel) pr_.getCompoFactory().newBorder();
        pSub_.setVisible(false);
        f_.setPreferredSize(new MetaDimension(1,1));
        pSub_.add(f_);
        p_.add(pSub_);
        AbsScrollPane ta_ = pr_.getCompoFactory().newAbsScrollPaneLeaf(new MockPlainLabel(""));
        ta_.setPreferredSize(new MetaDimension(1,1));
        p_.add(ta_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        ta_.recalculate();
        CustList<AbsCustComponent> acc_ = p_.getTreeAccessible();
        assertEq(1, acc_.size());
        assertSame(ta_,acc_.get(0));
    }
    @Test
    public void c22() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        MockPanel pSub_ = (MockPanel) pr_.getCompoFactory().newBorder();
        pSub_.setVisible(false);
        f_.setPreferredSize(new MetaDimension(1,1));
        pSub_.add(f_);
        p_.add(pSub_);
        MockPlainLabel ta_ = new MockPlainLabel("");
        ta_.setPreferredSize(new MetaDimension(1,1));
        ta_.addMouseListener((AbsMouseListener) new MockMouseListener());
        p_.add(ta_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        ta_.recalculate();
        CustList<AbsCustComponent> acc_ = p_.getTreeAccessible();
        assertEq(1, acc_.size());
        assertSame(ta_,acc_.get(0));
    }
    @Test
    public void c23() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        MockPanel pSub_ = (MockPanel) pr_.getCompoFactory().newBorder();
        pSub_.setVisible(false);
        f_.setPreferredSize(new MetaDimension(1,1));
        pSub_.add(f_);
        p_.add(pSub_);
        MockScrollPane ta_ = new MockScrollPane(false,new MockPlainLabel(""));
        ta_.setPreferredSize(new MetaDimension(1,1));
        p_.add(ta_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        ta_.recalculate();
        CustList<AbsCustComponent> acc_ = p_.getTreeAccessible();
        assertEq(0, acc_.size());
    }
    @Test
    public void c24() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        MockPanel pSub_ = (MockPanel) pr_.getCompoFactory().newBorder();
        pSub_.setVisible(false);
        f_.setPreferredSize(new MetaDimension(1,1));
        pSub_.add(f_);
        p_.add(pSub_);
        MockTableGui ta_ = new MockTableGui();
        ta_.setPreferredSize(new MetaDimension(1,1));
        p_.add(ta_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        ta_.recalculate();
        CustList<AbsCustComponent> acc_ = p_.getTreeAccessible();
        assertEq(1, acc_.size());
        assertSame(ta_,acc_.get(0));
    }
    @Test
    public void c25() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        MockPanel pSub_ = (MockPanel) pr_.getCompoFactory().newBorder();
        pSub_.setVisible(false);
        f_.setPreferredSize(new MetaDimension(1,1));
        pSub_.add(f_);
        p_.add(pSub_);
        MockTreeGui ta_ = new MockTreeGui(new MockMutableTreeNode(""));
        ta_.setPreferredSize(new MetaDimension(1,1));
        p_.add(ta_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        ta_.recalculate();
        CustList<AbsCustComponent> acc_ = p_.getTreeAccessible();
        assertEq(1, acc_.size());
        assertSame(ta_,acc_.get(0));
    }
    @Test
    public void c26() {
        MockProgramInfosSample pr_ = init();
        MockTextField f_ = new MockTextField();
        MockPanel p_ = (MockPanel) pr_.getCompoFactory().newBorder();
        MockPanel pSub_ = (MockPanel) pr_.getCompoFactory().newBorder();
        pSub_.setVisible(false);
        f_.setPreferredSize(new MetaDimension(1,1));
        pSub_.add(f_);
        p_.add(pSub_);
        MockPlainLabel ta_ = new MockPlainLabel("");
        ta_.setPreferredSize(new MetaDimension(1,1));
        p_.add(ta_);
        p_.setSize(p_.getPreferredSizeValue());
        p_.recalculate();
        f_.recalculate();
        ta_.recalculate();
        CustList<AbsCustComponent> acc_ = p_.getTreeAccessible();
        assertEq(0, acc_.size());
    }
}
