package code.gui;

public final class RefreshFocusEvent<T> implements AbsFocusListener {
    private final ScrollCustomGraphicList<T> list;

    public RefreshFocusEvent(ScrollCustomGraphicList<T> _l) {
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
