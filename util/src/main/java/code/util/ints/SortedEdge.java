package code.util.ints;

public interface SortedEdge<T> extends GraphElement<T> {

    void setOrder(int o);
    int getOrder();
}
