package code.gui;

public final class ComboSelection implements ListSelection {

    private final PopupMenu popup;

    private final GraphicComboGrInt grList;

    public ComboSelection(PopupMenu _popup, GraphicComboGrInt _grList) {
        popup = _popup;
        grList = _grList;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        popup.setVisible(false);
        grList.selectItem(_e.getFirstIndex());
    }

}
