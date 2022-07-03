package code.gui;

public interface AbsChildFrame extends ChangeableTitle {
    void setDialogIcon(AbsCommonFrame _group);
    void closeWindow();
    AbsCommonFrame getCommonFrame();
}
