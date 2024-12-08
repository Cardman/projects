package aiki.gui.components.editor;

import code.gui.*;
import code.gui.files.*;

public final class DefPostFileFrameEventImg implements AbsPostFileDialogEvent {
    private final FileDialogContent fileFrameContent;

    public DefPostFileFrameEventImg(FileDialogContent _c) {
        this.fileFrameContent = _c;
    }

    @Override
    public String act(String _path) {
        fileFrameContent.setSelectedAbsolutePath(_path);
        fileFrameContent.setSelectedPath(_path);
        return _path;
    }

    @Override
    public String title(String _path) {
        return _path;
    }

    @Override
    public String visible(AbsPanel _panel) {
        return "";
    }
}
