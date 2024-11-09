package code.gui;

public interface GeneComponentModel<T> {
    AbsCustComponent gene(int _select);
    T value();
    void value(T _v);
}
