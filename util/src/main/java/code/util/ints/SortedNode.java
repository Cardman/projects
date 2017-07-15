package code.util.ints;

public interface SortedNode<T extends SortedNode<T>> {

    T getFirstChild();

    T getNextSibling();

    T getParent();
}
