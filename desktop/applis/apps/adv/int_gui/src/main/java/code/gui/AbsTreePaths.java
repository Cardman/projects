package code.gui;

public interface AbsTreePaths {
    int getLength();
    void add(AbsTreePath _path);
    void remove(int _path);
    void set(int _index, AbsTreePath _path);
    AbsTreePath elt(int _index);
    AbsTreePath elt(AbstractMutableTreeNode _root,int _index);
}
