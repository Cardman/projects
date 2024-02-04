package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractAtomicBoolean;

public abstract class FileFrame {
    private final AbsCommonFrame frame;
    private final AbstractProgramInfos prInfos;
    private final ClosingFileFrameEvent closing;

    protected FileFrame(AbstractProgramInfos _frameFact, AbstractAtomicBoolean _m){
//        fileDialogContent = _f;
        prInfos = _frameFact;
        frame = _frameFact.getFrameFactory().newCommonFrame("",_frameFact,null);
        closing = new ClosingFileFrameEvent(frame, _m);
        frame.addWindowListener(closing);
    }

    public ClosingFileFrameEvent getClosing() {
        return closing;
    }

    public AbstractProgramInfos getPrInfos() {
        return prInfos;
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

//    public FileDialogContent getFileDialogContent() {
//        return fileDialogContent;
//    }
}
