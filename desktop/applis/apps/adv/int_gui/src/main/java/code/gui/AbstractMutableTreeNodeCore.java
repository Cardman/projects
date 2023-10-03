package code.gui;

import code.util.IdList;
import code.util.Ints;

public interface AbstractMutableTreeNodeCore<T> {
    AbstractMutableTreeNodeCore<T> getParent();

    AbstractMutableTreeNodeCore<T> getFirstChild();

    AbstractMutableTreeNodeCore<T> getNextSibling();

    void setParent(AbstractMutableTreeNodeCore<T> _v);

    void setFirstChild(AbstractMutableTreeNodeCore<T> _v);

    void setNextSibling(AbstractMutableTreeNodeCore<T> _v);
    int getAntiIndex(AbstractMutableTreeNodeCore<T> _treeNode);
    int getChildCount();

    AbstractMutableTreeNodeCore<T> getPreviousSibling();

    AbstractMutableTreeNodeCore<T> getChildAt(int _i);
    AbstractMutableTreeNodeCore<T> simular(AbstractMutableTreeNodeCore<String> _e);
    AbstractMutableTreeNodeCore<T> getElt(Ints _indexes);
    IdList<AbstractMutableTreeNodeCore<T>> children();
    int getIndex();
    boolean add(AbstractMutableTreeNodeCore<T> _treeNode);

    int insert(AbstractMutableTreeNodeCore<T> _treeNode, int _index);

    int removeAllChildren();

    AbstractMutableTreeNodeCore<T> getFirstChildReal();
    AbstractMutableTreeNodeCore<T> getNextSiblingReal();
    AbstractMutableTreeNodeCore<T> getParentReal();

    int remove(AbstractMutableTreeNodeCore<T> _treeNode);

    AbstractMutableTreeNodeCore<T> remove(int _index);

    AbstractMutableTreeNodeCore<T> removeFromParent();

    Ints getIndexes();
    T info();
    void info(T _t);
}
