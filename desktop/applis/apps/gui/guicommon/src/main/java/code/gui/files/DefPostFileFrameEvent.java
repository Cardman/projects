package code.gui.files;

import code.gui.AbsPanel;

public final class DefPostFileFrameEvent implements AbsPostFileDialogEvent {
    private final FileFrame fileFrame;

    public DefPostFileFrameEvent(FileFrame _f) {
        this.fileFrame = _f;
    }

    @Override
    public String act(String _path) {
        fileFrame.getFileDialogContent().setSelectedAbsolutePath(_path);
        fileFrame.getFileDialogContent().setSelectedPath(_path);
        fileFrame.closeFrameFile();
        return _path;
    }

    @Override
    public String title(String _path) {
        fileFrame.getFrame().setTitle(_path);
        return _path;
    }

    @Override
    public String visible(AbsPanel _panel) {
        fileFrame.getFrame().setContentPane(_panel);
        fileFrame.getFrame().pack();
        fileFrame.getFrame().setVisible(true);
        return "";
    }
}
