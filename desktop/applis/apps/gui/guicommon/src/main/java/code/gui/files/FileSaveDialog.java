package code.gui.files;


import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.AbstractFile;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FileSaveDialog extends FileDialog implements SingleFileSelection {
    public static final String FILE_SAVE_DIAL = "file_save";

    private static final String CREATE = "+";

    private static final int NB_COLS = 24;

    private final AbsTextField typedString = getCompoFactory().newTextField(NB_COLS);

    private final AbsPanel searchingPanel = getCompoFactory().newLineBox();
    private AbsButton search;

    private AbsCommonFrame frame;

    public FileSaveDialog(AbstractProgramInfos _frameFact) {
        super(_frameFact);
    }

    public static void setFileSaveDialogByFrame(AbsCommonFrame _w, String _language, boolean _currentFolderRoot, String _folder, FileSaveDialog _fileSave) {
        _fileSave.setFileDialogByFrame(_language,_currentFolderRoot, _folder, _w);
        _fileSave.initSaveDialog(_w, _w.getFrames().getHomePath());
    }

    public static void setFileSaveDialog(AbsCommonFrame _c, AbsDialog _w, String _language, boolean _currentFolderRoot, String _folder, FileSaveDialog _fileSave) {
        _fileSave.setFileDialog(_w,_language,_currentFolderRoot, _folder);
        _fileSave.initSaveDialog(_c, _c.getFrames().getHomePath());
    }

    private void initSaveDialog(AbsCommonFrame _c, String _homePath) {
        frame =_c;
        StringMap<String> messages_ = getAppliTr(getProgramInfos().getTranslations().getMapping().getVal(_c.getLanguageKey())).getMapping().getVal(FILE_SAVE_DIAL).getMapping();
        AbsButton action_ = getCompoFactory().newPlainButton(messages_.getVal(MessagesFileSaveDialog.SAVE));
        action_.addActionListener(new SubmitMouseEvent(this));
        getButtons().add(action_);
        action_ = getCompoFactory().newPlainButton(messages_.getVal(MessagesFileSaveDialog.CANCEL));
        action_.addActionListener(new CancelSelectFileEvent(this));
        getButtons().add(action_);
        if (StringUtil.quickEq(getFolder(), _homePath)) {
            searchingPanel.removeAll();
            AbsPlainLabel label_;
            label_ = getCompoFactory().newPlainLabel(messages_.getVal(MessagesFileSaveDialog.FOLDER_NAME));
            search = getCompoFactory().newPlainButton(CREATE);
            search.addActionListener(new CreateFolderEvent(this));
            searchingPanel.add(label_);
            searchingPanel.add(typedString);
            searchingPanel.add(search);
            getAbsDialog().getPane().add(searchingPanel, GuiConstants.BORDER_LAYOUT_NORTH);
        }
        pack();
    }

    public void createFolder() {
        String typed_ = typedString.getText().trim();
        if (typed_.isEmpty()) {
            return;
        }
        AbstractMutableTreeNodeCore<String> path_ = getFolderSystem().selectEvt();
        if (path_ != null) {
            String str_ = buildPath(path_)+ typed_;
            if (koCreate(str_, getProgramInfos())) {
                return;
            }
            applyTreeChangeSelected();
        } else {
            if (koCreate(StringUtil.concat(getFolder(), StreamTextFile.SEPARATEUR, typed_), getProgramInfos())) {
                return;
            }
            applyTreeChange();
        }
    }

    static boolean koCreate(String _p, AbstractProgramInfos _pr) {
        return !_pr.getValidator().okPath(StreamFolderFile.getRelativeRootPath(_p,_pr.getFileCoreStream()), '/', '\\') || !StreamFolderFile.mkdirs(_p, _pr.getFileCoreStream());
    }

    @Override
    public void submitIfVisible() {
        submit();
    }

    public void submit() {
        String lg_ = getLang();
        StringMap<String> messages_ = getAppliTr(getProgramInfos().getTranslations().getMapping().getVal(lg_)).getMapping().getVal(FILE_SAVE_DIAL).getMapping();
        String errorTitle_ = messages_.getVal(MessagesFileSaveDialog.FORBIDDEN);
        String text_ = getFileName().getText();
        if (text_.trim().isEmpty()) {
            String errorContent_ = messages_.getVal(MessagesFileSaveDialog.FORBIDDEN_SPACES);
            getProgramInfos().getMessageDialogAbs().input(getAbsDialog(), errorContent_, errorTitle_,lg_, GuiConstants.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this, errorContent_, errorTitle_, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!getProgramInfos().getValidator().okPath(text_,'/','\\')) {
            String errorContent_ = messages_.getVal(MessagesFileSaveDialog.FORBIDDEN_SPECIAL_CHARS);
            getProgramInfos().getMessageDialogAbs().input(getAbsDialog(), errorContent_, errorTitle_, lg_, GuiConstants.ERROR_MESSAGE);
            return;
        }
        //get selected row first table
        AbstractFile file_ = getProgramInfos().getFileCoreStream().newFile(StringUtil.concat(getCurrentFolder(), text_));
        if (file_.exists()) {
            String mes_ = StringUtil.simpleStringsFormat(messages_.getVal(MessagesFileSaveDialog.BODY_CONF), StringUtil.concat(getCurrentFolder(), text_));
//            ConfirmDialog conf_ = new ConfirmDialog(
//                    this,
//                    mes_, messages.getVal(TITLE_CONF),
//                    getLang(),
//                    JOptionPane.YES_NO_OPTION);
            int answer_ = getProgramInfos().getConfirmDialogAns().input(
                    getAbsDialog(),frame,
                    mes_, messages_.getVal(MessagesFileSaveDialog.TITLE_CONF),
                    getLang(),
                    GuiConstants.YES_NO_OPTION);
            if (answer_ == GuiConstants.NO_OPTION) {
                return;
            }
        }
        setSelectedPath(StringUtil.concat(getCurrentFolder(), text_));
        getAbsDialog().closeWindow();
    }

    public static String getStaticSelectedPath(FileSaveDialog _dialog) {
        return _dialog.getSelectedPath();
    }

    public AbsButton getSearch() {
        return search;
    }

    public AbsTextField getTypedString() {
        return typedString;
    }
}
