package code.mock;

import code.gui.AbsPlainLabel;
import code.gui.AbstractMutableTreeNode;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.core.StringUtil;

public final class MockCommonFrameTreeSample extends MockAbsCommonFrame implements MockListTreeWindow{
    public MockCommonFrameTreeSample(AbstractProgramInfos _fr) {
        super(_fr, "");
        AbstractMutableTreeNode root_ = _fr.getCompoFactory().newMutableTreeNode("0");
        root_.add("1");
        MockTreeGui m_ = (MockTreeGui) _fr.getCompoFactory().newTreeGui(root_);
        m_.addTreeSelectionListener(new MockShortListTree(0,this));
        getContentPane().add(m_);
        getContentPane().add(_fr.getCompoFactory().newPlainLabel(""));
        pack();
    }

    @Override
    public void pack() {
        setVisible(isVisible());
    }

    @Override
    public void action(int _nb, AbstractMutableTreeNodeCore _node) {
        MockTreeGui m_ = (MockTreeGui) getContentPane().getComponent(0);
        m_.selectEvt();
        AbsPlainLabel lab_ = (AbsPlainLabel) getContentPane().getComponent(1);
        CustList<MockMutableTreeNode> path_ = MockTreeGui.getTreePath((MockMutableTreeNode) _node).getPath();
        CustList<String> ls_ = new CustList<String>();
        for (MockMutableTreeNode m : path_) {
            ls_.add(m.getUserObject());
        }
        lab_.setText(StringUtil.join(ls_,'/'));
    }
}
