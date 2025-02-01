package code.gui.document;

public interface BeanDisplay<T> {
    int display(AbsBeanRender _rend, T _info, int _index);
}
