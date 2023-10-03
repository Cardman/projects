package code.gui;

public interface AbsTreePath {
    int getLength();
    AbsTreePath parent(AbsTreeGui _tree);
    AbstractMutableTreeNodeCore<String> data();
}
