package code.mock;

import org.junit.Test;

public final class MockTabbedPaneTest extends EquallableMockGuiUtil{
    @Test
    public void t1() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        assertEq(1,t_.getComponentCount());
    }
    @Test
    public void t2() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        t_.add("",new MockPlainLabel(""));
        t_.setSelectedIndex(1);
        assertEq(2,t_.getComponentCount());
        assertEq(1,t_.getSelectedIndex());
    }
    @Test
    public void t3() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        t_.add("",new MockPlainLabel(""));
        t_.setSelectedIndex(1);
        t_.setSelectedIndex(2);
        assertEq(2,t_.getComponentCount());
        assertEq(1,t_.getSelectedIndex());
    }
    @Test
    public void t4() {
        MockTabbedPane t_ = new MockTabbedPane();
        MockPlainLabel c_ = new MockPlainLabel("");
        t_.add("", c_);
        t_.add("", c_);
        assertEq(1,t_.getComponentCount());
    }
    @Test
    public void t5() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        t_.add("",new MockPlainLabel(""));
        assertTrue(t_.setTab(0,new MockPlainLabel("")));
        assertEq(2,t_.getComponentCount());
        assertTrue(t_.setTab(3,new MockPlainLabel("")));
        new MockScrollPane().getChild();
    }
    @Test
    public void t6() {
        MockTabbedPane t_ = new MockTabbedPane();
        MockPlainLabel c_ = new MockPlainLabel("");
        t_.add("", c_);
        t_.add("", c_);
        assertFalse(t_.setTab(0,c_));
    }
    @Test
    public void t7() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        t_.removeAll();
        assertEq(0,t_.getComponentCount());
    }
    @Test
    public void t8() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        t_.remove(0);
        assertEq(0,t_.getComponentCount());
    }
    @Test
    public void t9() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        t_.setTitle(0,"_");
        assertEq("_",t_.getTitle(0));
    }
    @Test
    public void t10() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.addIntTab("",new MockPlainLabel(""),"");
        t_.setToolTipAt(0,"_");
        assertEq("_",t_.getToolTipAt(0));
    }
    @Test
    public void t11() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        t_.add("",new MockPlainLabel(""));
        t_.addChangeListener(new MockChangeListener(new MockWithChangeListenerSample(initDef(),""),0));
        t_.selectIndex(1);
        assertEq(2,t_.getComponentCount());
    }
    @Test
    public void t12() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        t_.add("",new MockPlainLabel(""));
        t_.addChangeListener(new MockChangeListener(new MockWithChangeListenerSample(initDef(),""),0));
        t_.selectIndex(0);
        t_.remove(1);
        assertEq(1,t_.getComponentCount());
    }
    @Test
    public void t13() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        t_.add("",new MockPlainLabel(""));
        t_.addChangeListener(new MockChangeListener(new MockWithChangeListenerSample(initDef(),""),0));
        t_.selectIndex(0);
        t_.remove(0);
        assertEq(1,t_.getComponentCount());
    }
    @Test
    public void t14() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        t_.add("",new MockPlainLabel(""));
        t_.add("",new MockPlainLabel(""));
        t_.addChangeListener(new MockChangeListener(new MockWithChangeListenerSample(initDef(),""),0));
        t_.selectIndex(1);
        t_.remove(2);
        assertEq(2,t_.getComponentCount());
    }
    @Test
    public void t15() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        t_.add("",new MockPlainLabel(""));
        t_.add("",new MockPlainLabel(""));
        t_.addChangeListener(new MockChangeListener(new MockWithChangeListenerSample(initDef(),""),0));
        t_.selectIndex(2);
        t_.remove(1);
        assertEq(2,t_.getComponentCount());
    }
    @Test
    public void t16() {
        MockTabbedPane t_ = new MockTabbedPane();
        t_.add("",new MockPlainLabel(""));
        t_.add("",new MockPlainLabel(""));
        t_.add("",new MockPlainLabel(""));
        MockChangeListener ls_ = new MockChangeListener(new MockWithChangeListenerSample(initDef(), ""), 0);
        t_.addChangeListener(ls_);
        t_.removeChangeListener(ls_);
        assertEq(0,t_.getChangeListeners().size());
    }
}
