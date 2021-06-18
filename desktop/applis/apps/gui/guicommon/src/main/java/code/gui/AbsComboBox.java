package code.gui;

public abstract class AbsComboBox {
    private final GraphicComboGrInt combo;
    protected AbsComboBox(GraphicComboGrInt _combo) {
        combo = _combo;
    }

    public GraphicComboGrInt getCombo() {
        return combo;
    }
}
