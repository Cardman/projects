package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBoolean;

public abstract class FileFrame {
    public static final String GUI = "gui";
    public static final String CONFIRM = "confirm";
    public static final String FILE_DIAL = "file_dialog";
    private final AbsCommonFrame frame;
    private final AbstractProgramInfos prInfos;
    private final ClosingFileFrameEvent closing;

    protected FileFrame(AbstractProgramInfos _frameFact, AbstractAtomicBoolean _m){
//        fileDialogContent = _f;
        prInfos = _frameFact;
        frame = _frameFact.getFrameFactory().newCommonFrame(_frameFact,null);
        closing = new ClosingFileFrameEvent(frame, _m);
        frame.addWindowListener(closing);
    }

    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(GUI, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(GUI);
    }

    public static void enTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(FILE_DIAL,MessagesFileDialog.en());
        _lgs.getMapping().addEntry(FolderOpenFrame.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.en());
        _lgs.getMapping().addEntry(FileOpenFrame.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        _lgs.getMapping().addEntry(FileSaveFrame.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        _lgs.getMapping().addEntry(FileTable.FILE_TAB,MessagesFileTable.en());
        _lgs.getMapping().addEntry(CONFIRM,MessagesConfirmDialog.en());
    }

    public static void frTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(FILE_DIAL,MessagesFileDialog.fr());
        _lgs.getMapping().addEntry(FolderOpenFrame.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.fr());
        _lgs.getMapping().addEntry(FileOpenFrame.FILE_OPEN_DIAL,MessagesFileOpenDialog.fr());
        _lgs.getMapping().addEntry(FileSaveFrame.FILE_SAVE_DIAL,MessagesFileSaveDialog.fr());
        _lgs.getMapping().addEntry(FileTable.FILE_TAB,MessagesFileTable.fr());
        _lgs.getMapping().addEntry(CONFIRM,MessagesConfirmDialog.fr());
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
