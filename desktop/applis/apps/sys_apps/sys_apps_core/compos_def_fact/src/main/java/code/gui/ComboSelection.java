package code.gui;

public final class ComboSelection implements ListSelection {

    private final AbsPopupMenu popup;

    private final GraphicComboGrInt grList;

    public ComboSelection(AbsPopupMenu _popup, GraphicComboGrInt _grList) {
        popup = _popup;
        grList = _grList;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        popup.setVisible(false);
        grList.selectItem(_e.getFirstIndex());
    }

}
