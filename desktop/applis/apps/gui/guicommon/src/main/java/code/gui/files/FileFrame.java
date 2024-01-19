package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.initialize.AbstractProgramInfos;

public abstract class FileFrame {
    private final AbsCommonFrame frame;
    private final FileDialogContent fileDialogContent;

    protected FileFrame(AbstractProgramInfos _frameFact, FileDialogContent _f){
        fileDialogContent = _f;
        frame = _frameFact.getFrameFactory().newCommonFrame("",_frameFact,null);
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public FileDialogContent getFileDialogContent() {
        return fileDialogContent;
    }
}
