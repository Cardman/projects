package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.AbsPanel;

public final class DefPostFileFrameEvent implements AbsPostFileDialogEvent {
    private final FileFrameInt fileFrame;
    private final FileDialogContent fileFrameContent;
    private final AbsCommonFrame frame;

    public DefPostFileFrameEvent(FileFrameInt _f, FileDialogContent _c, AbsCommonFrame _fr) {
        this.fileFrame = _f;
        this.fileFrameContent = _c;
        frame = _fr;
    }

    @Override
    public String act(String _path) {
        fileFrameContent.setSelectedAbsolutePath(_path);
        fileFrameContent.setSelectedPath(_path);
        fileFrame.closeFrameFile();
        return _path;
    }

    @Override
    public String title(String _path) {
        frame.setTitle(_path);
        return _path;
    }

    @Override
    public String visible(AbsPanel _panel) {
        frame.setContentPane(_panel);
        frame.pack();
        frame.setVisible(true);
        return "";
    }
}
