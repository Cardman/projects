package code.gui;

public abstract class AbsComboBox {
    private final ScrollCustomCombo combo;
    protected AbsComboBox(ScrollCustomCombo _combo) {
        combo = _combo;
    }

    public int getSelectedIndex() {
        return combo.getSelectedIndex();
    }

    public String getSelectedItem() {
        return GuiBaseUtil.getSelectedItem(combo);
    }

    public int getItemCount() {
        return combo.size();
    }

    public void selectItem(int _index) {
        combo.select(_index);
    }

    public void addItem(String _object) {
        combo.add(_object);
    }

    public void setListener(ListSelection _listener) {
        combo.setListener(_listener);
    }

    public AbsCustComponent self() {
        return combo.getGlobal();
    }

    public ScrollCustomCombo getCombo() {
        return combo;
    }
}
