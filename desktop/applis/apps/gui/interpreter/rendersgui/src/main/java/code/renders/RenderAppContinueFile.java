package code.renders;

import code.gui.files.AbsContinueFile;
import code.gui.files.FileDialogContent;

public final class RenderAppContinueFile implements AbsContinueFile {
    private final WindowRenders windowRenders;

    public RenderAppContinueFile(WindowRenders _w) {
        this.windowRenders = _w;
    }

    @Override
    public void next(FileDialogContent _content) {
        windowRenders.loadRenderConf(_content.getSelectedAbsolutePath());
    }
}
