package code.mock;

import code.gui.AbsShortListTree;
import code.util.IdList;

public final class MockTreeComponent extends MockCustComponent{
    private boolean rootVisible;
    private MockTreePath selectionPath = new MockTreePath();
    private final IdList<AbsShortListTree> inner = new IdList<AbsShortListTree>();

    public boolean isRootVisible() {
        return rootVisible;
    }

    public void setRootVisible(boolean _rootVisible) {
        rootVisible= _rootVisible;
    }

    public MockTreePath getSelectionPath() {
        return selectionPath;
    }

    public void setSelectionPath(MockTreePath _treePath) {
        selectionPath = _treePath;
    }

    public void addTreeSelectionListener(AbsShortListTree _sel) {
        inner.add(_sel);
    }

    public void removeTreeSelectionListener(AbsShortListTree _sel) {
        inner.removeObj(_sel);
    }
}
