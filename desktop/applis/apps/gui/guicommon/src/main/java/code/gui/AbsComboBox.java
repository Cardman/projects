package code.gui;

public abstract class AbsComboBox {
    private final GraphicComboGrInt combo;
    protected AbsComboBox(GraphicComboGrInt _combo) {
        combo = _combo;
    }

    public int getSelectedIndex() {
        return combo.getSelectedIndex();
    }

    public int getItemCount() {
        return combo.getItemCount();
    }

    public void selectItem(int _index) {
        combo.selectItem(_index);
    }

    public void addItem(String _object) {
        combo.addItem(_object);
    }

    public void setListener(ListSelection _listener) {
        combo.setListener(_listener);
    }

    public AbsCustComponent self() {
        return combo.self();
    }

    public GraphicComboGrInt getCombo() {
        return combo;
    }
}
