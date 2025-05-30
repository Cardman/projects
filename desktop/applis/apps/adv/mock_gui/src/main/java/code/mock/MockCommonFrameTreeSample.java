package code.mock;

import code.gui.AbsPlainLabel;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.core.StringUtil;

public final class MockCommonFrameTreeSample extends MockAbsCommonFrame implements MockListTreeWindow{
    public MockCommonFrameTreeSample(AbstractProgramInfos _fr) {
        super("");
        AbstractMutableTreeNodeCore<String> root_ = _fr.getCompoFactory().newMutableTreeNode("0");
        root_.add(_fr.getCompoFactory().newMutableTreeNode("1"));
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
    public void action(int _nb, AbstractMutableTreeNodeCore<String> _node) {
        MockTreeGui m_ = (MockTreeGui) ((MockPanel)getContentPane()).getComponent(0);
        m_.selectEvt();
        AbsPlainLabel lab_ = (AbsPlainLabel) ((MockPanel)getContentPane()).getComponent(1);
        MockMutableTreeNode curr_ = (MockMutableTreeNode) _node;
        CustList<MockMutableTreeNode> path_ = new CustList<MockMutableTreeNode>();
        while (curr_ != null) {
            path_.add(0, curr_);
            curr_ = (MockMutableTreeNode) curr_.getParent();
        }
        CustList<String> ls_ = new CustList<String>();
        for (MockMutableTreeNode m : path_) {
            ls_.add(m.info());
        }
        lab_.setText(StringUtil.join(ls_,'/'));
    }
}
