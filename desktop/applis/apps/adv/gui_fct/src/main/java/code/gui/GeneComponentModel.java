package code.gui;

public interface GeneComponentModel<T> {
    AbsCustComponent gene();
    T value();
    T value(T _v);
}
