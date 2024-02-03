package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.initialize.AbstractProgramInfos;

public abstract class FileFrame {
    private final AbsCommonFrame frame;
    private final FileDialogContent fileDialogContent;
    private final AbsClosingFile cancelFile;

    protected FileFrame(AbstractProgramInfos _frameFact, FileDialogContent _f, AbsClosingFile _a){
        fileDialogContent = _f;
        frame = _frameFact.getFrameFactory().newCommonFrame("",_frameFact,null);
        frame.addWindowListener(new ClosingFileFrameEvent(frame,_a));
        cancelFile = _a;
    }
    public void closeFrameFile() {
        cancelFile.closeFrameFile(frame);
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public FileDialogContent getFileDialogContent() {
        return fileDialogContent;
    }
}
