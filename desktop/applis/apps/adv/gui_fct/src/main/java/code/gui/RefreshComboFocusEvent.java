package code.gui;

public final class RefreshComboFocusEvent<T> implements AbsFocusListener {
    private final AbsScrollCustomCombo<T> list;

    public RefreshComboFocusEvent(AbsScrollCustomCombo<T> _l) {
        this.list = _l;
    }
    @Override
    public void focusGained() {
        list.refreshFocused();
    }

    @Override
    public void focusLost() {
        list.refreshFocused();
    }
}
