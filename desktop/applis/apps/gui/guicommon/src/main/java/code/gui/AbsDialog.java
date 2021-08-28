package code.gui;

import code.gui.images.AbstractImageFactory;

public interface AbsDialog extends ChangeableTitle, AbsCloseableDialog {
    void setDefaultCloseOperation(int _operation);
    void setResizable(boolean _resizable);
    void setLocationRelativeTo(AbsGroupFrame _onwer);
    void setLocationRelativeTo(AbsDialog _onwer);
    void setLocationRelativeToWindow(Iconifiable _i);
    String getAccessFile();
    void setAccessFile(String _accessFile);
    void setDialogIcon(AbstractImageFactory _fact, Iconifiable _group);
    void setContentPane(AbsPanel _contentPane);
    void setContentPane(AbsScrollPane _contentPane);
    AbsPanel getPane();
    void setModal(boolean _modal);
    void setVisible(boolean _b);
}
