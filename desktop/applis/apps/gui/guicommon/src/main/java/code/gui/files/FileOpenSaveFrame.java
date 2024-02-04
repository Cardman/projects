package code.gui.files;

import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FileOpenSaveFrame extends FileFrame {
    private final FileOpenDialogContent fileOpenDialogContent;
    private final FileSaveDialogContent fileSaveDialogContent;
    private AbsButton mainAction;
    private AbsButton closeAction;
    public FileOpenSaveFrame(AbstractProgramInfos _frameFact, AbsClosingFile _a) {
        super(_frameFact, _a);
        fileOpenDialogContent = new FileOpenDialogContent(_frameFact.getThreadFactory().newAtomicBoolean(), _frameFact.getThreadFactory().newAtomicBoolean(),_frameFact);
        fileSaveDialogContent = new FileSaveDialogContent(_frameFact, true);
    }

    public static void setFileSaveDialogByFrame(boolean _currentFolderRoot, String _folder, FileOpenSaveFrame _fileSave, AbsSaveFile _s,AbsContinueLoadFile _c) {
        FileOpenDialogContent o_ = _fileSave.getFileOpenDialogContent();
        FileSaveDialogContent s_ = _fileSave.getFileSaveDialogContent();
        AbsCompoFactory compoFactory_ = _fileSave.getPrInfos().getCompoFactory();
        AbsTabbedPane tabs_ = compoFactory_.newAbsTabbedPane();
        AbsPlainLabel labSave_ = compoFactory_.newPlainLabel("");
        AbsPlainLabel labOpen_ = compoFactory_.newPlainLabel("");
        s_.setFileSaveContentDialogByFrame(_currentFolderRoot,_folder, new OpenSavePostFileFrameEvent(_fileSave, s_, FileSaveDialog.FILE_SAVE_DIAL, MessagesFileSaveDialog.SAVE, tabs_, labSave_));
        o_.setFileOpenDialogPart(_currentFolderRoot,_folder, new OpenSavePostFileFrameEvent(_fileSave, o_, FileOpenDialog.FILE_OPEN_DIAL, MessagesFileOpenDialog.OPEN, tabs_, labOpen_));
        AbsPanel contentPane_ = compoFactory_.newBorder();
        contentPane_.add(tabs_, GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel buttons_ = compoFactory_.newPageBox();
        buttons_.add(labSave_);
        buttons_.add(labOpen_);

        StringMap<String> messages_ = FileDialog.getAppliTr(_fileSave.getPrInfos().currentLg()).getMapping().getVal(ConfirmDialog.CONFIRM).getMapping();
        AbsButton button_ = compoFactory_.newPlainButton(messages_.getVal(MessagesConfirmDialog.YES));
        _fileSave.mainAction = button_;
        button_.addActionListener(new SaveOpenSelectFileEvent(_fileSave,_s,_c,s_,o_));
        buttons_.add(button_);
//        button_ = _content.getProgramInfos().getCompoFactory().newPlainButton(messages_.getVal(MessagesConfirmDialog.NO));
//        button_.addActionListener(new SkipSelectFileEvent(_c,_content));
//        _content.getButtons().add(button_);
        button_ = compoFactory_.newPlainButton(messages_.getVal(MessagesConfirmDialog.NO));
        _fileSave.closeAction = button_;
        button_.addActionListener(new CloseSelectFileEvent(_fileSave.getClosing()));
        buttons_.add(button_);
        contentPane_.add(buttons_, GuiConstants.BORDER_LAYOUT_SOUTH);

        _fileSave.getFrame().setContentPane(contentPane_);
        _fileSave.getFrame().pack();
        _fileSave.getFrame().setVisible(true);
    }

    public AbsButton getMainAction() {
        return mainAction;
    }

    public AbsButton getCloseAction() {
        return closeAction;
    }

    public FileOpenDialogContent getFileOpenDialogContent() {
        return fileOpenDialogContent;
    }

    public FileSaveDialogContent getFileSaveDialogContent() {
        return fileSaveDialogContent;
    }
}
