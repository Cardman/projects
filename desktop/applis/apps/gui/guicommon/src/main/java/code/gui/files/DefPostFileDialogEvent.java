package code.gui.files;

import code.gui.AbsPanel;

public final class DefPostFileDialogEvent implements AbsPostFileDialogEvent {
    private final FileDialog fileDialog;

    public DefPostFileDialogEvent(FileDialog _f) {
        this.fileDialog = _f;
    }

    @Override
    public String act(String _path) {
        fileDialog.setSelectedAbsolutePath(_path);
        fileDialog.setSelectedPath(_path);
        fileDialog.closeWindow();
        return _path;
    }

    @Override
    public String title(String _path) {
        fileDialog.setTitle(_path);
        return _path;
    }

    @Override
    public String visible(AbsPanel _panel) {
        fileDialog.getAbsDialog().setContentPane(_panel);
        fileDialog.pack();
        return "";
    }
}
