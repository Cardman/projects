package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.initialize.AbstractProgramInfos;

public abstract class FileFrame {
    private final AbsCommonFrame frame;
    private final AbstractProgramInfos prInfos;
    private final AbsClosingFile cancelFile;
    private final ClosingFileFrameEvent closing;

    protected FileFrame(AbstractProgramInfos _frameFact, AbsClosingFile _a){
//        fileDialogContent = _f;
        prInfos = _frameFact;
        frame = _frameFact.getFrameFactory().newCommonFrame("",_frameFact,null);
        closing = new ClosingFileFrameEvent(frame, _a);
        frame.addWindowListener(closing);
        cancelFile = _a;
    }

    public ClosingFileFrameEvent getClosing() {
        return closing;
    }

    public AbstractProgramInfos getPrInfos() {
        return prInfos;
    }

    public AbsClosingFile getCancelFile() {
        return cancelFile;
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

//    public FileDialogContent getFileDialogContent() {
//        return fileDialogContent;
//    }
}
