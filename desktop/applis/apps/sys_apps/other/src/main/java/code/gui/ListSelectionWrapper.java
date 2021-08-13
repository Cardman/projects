package code.gui;

public final class ListSelectionWrapper {
    private final ListSelection wrapped;

    public ListSelectionWrapper(ListSelection _wrapped) {
        this.wrapped = _wrapped;
    }

    public boolean match(AbstractSelectionListener _other) {
        return LabelButtonUtil.match(_other,wrapped);
    }
}
