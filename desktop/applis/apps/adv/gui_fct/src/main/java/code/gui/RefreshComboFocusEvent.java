package code.gui;

public final class RefreshComboFocusEvent implements AbsFocusListener {
    private final ScrollCustomCombo list;

    public RefreshComboFocusEvent(ScrollCustomCombo _l) {
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
