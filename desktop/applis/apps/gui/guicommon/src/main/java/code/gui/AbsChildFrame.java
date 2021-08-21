package code.gui;

public interface AbsChildFrame extends ChangeableTitle {
    void setDialogIcon(AbsGroupFrame _group);
    void closeWindow();
    AbsCommonFrame getCommonFrame();
}
