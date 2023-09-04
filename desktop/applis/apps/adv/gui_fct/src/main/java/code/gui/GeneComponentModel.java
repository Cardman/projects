package code.gui;

public interface GeneComponentModel<T> {
    AbsCustComponent gene();
    AbsCustComponent gene(T _d);
    T value();
    T value(T _v);
}
