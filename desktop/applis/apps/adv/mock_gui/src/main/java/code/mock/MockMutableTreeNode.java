package code.mock;

import code.gui.AbstractMutableTreeNodeCore;
import code.gui.MutableTreeNodeNav;
import code.util.core.StringUtil;

public final class MockMutableTreeNode extends MutableTreeNodeNav<String> implements AbstractMutableTreeNodeCore<String> {
    private boolean accessible;

    public MockMutableTreeNode(String _name) {
        info(StringUtil.nullToEmpty(_name));
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean _a) {
        this.accessible = _a;
    }
}
