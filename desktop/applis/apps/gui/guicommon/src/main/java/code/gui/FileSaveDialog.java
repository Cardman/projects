package code.gui;


import code.gui.events.CancelSelectFileEvent;
import code.gui.events.CreateFolderEvent;
import code.gui.events.SubmitKeyEvent;
import code.gui.events.SubmitMouseEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.AbstractFile;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FileSaveDialog extends FileDialog implements SingleFileSelection {
    private static final String DIALOG_ACCESS = "gui.filesavedialog";

    private static final String CANCEL = "cancel";

    private static final String TITLE_CONF = "titleConf";

    private static final String BODY_CONF = "bodyConf";

    private static final String FORBIDDEN_SPECIAL_CHARS = "forbiddenSpecialChars";

    private static final String FORBIDDEN_SPACES = "forbiddenSpaces";

    private static final String FORBIDDEN = "forbidden";

    private static final String SAVE = "save";

    private static final String FOLDER_NAME = "folderName";

    private static final String CREATE = "+";

    private static final int NB_COLS = 24;

    private final AbsTextField typedString = getCompoFactory().newTextField(NB_COLS);

    private final AbsPanel searchingPanel = getCompoFactory().newLineBox();

    private StringMap<String> messages;
    private AbsCommonFrame frame;

    public FileSaveDialog(AbstractProgramInfos _frameFact) {
        super(_frameFact);
        getAbsDialog().setAccessFile(DIALOG_ACCESS);
    }

    public static void setFileSaveDialogByFrame(AbsCommonFrame _w, String _language, boolean _currentFolderRoot, String _extension, String _folder, FileSaveDialog _fileSave) {
        _fileSave.setFileDialogByFrame(_language,_currentFolderRoot,_extension, _folder, _w);
        _fileSave.initSaveDialog(_w, _w.getFrames().getHomePath());
    }

    public static void setFileSaveDialog(AbsCommonFrame _c, AbsDialog _w, String _language, boolean _currentFolderRoot, String _extension, String _folder, FileSaveDialog _fileSave) {
        _fileSave.setFileDialog(_w,_language,_currentFolderRoot,_extension, _folder);
        _fileSave.initSaveDialog(_c, _c.getFrames().getHomePath());
    }

    private void initSaveDialog(AbsCommonFrame _c, String _homePath) {
        frame =_c;
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, _c.getLanguageKey(), getAbsDialog().getAccessFile());
        String loadedResourcesMessages_ = MessGuiGr.ms().getVal(fileName_);
        messages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        getFileName().addActionListener(new SubmitKeyEvent(this));
        AbsPlainButton action_ = getCompoFactory().newPlainButton(messages.getVal(SAVE));
        action_.addActionListener(new SubmitMouseEvent(this));
        getButtons().add(action_);
        action_ = getCompoFactory().newPlainButton(messages.getVal(CANCEL));
        action_.addActionListener(new CancelSelectFileEvent(this));
        getButtons().add(action_);
        if (StringUtil.quickEq(getFolder(), _homePath)) {
            searchingPanel.removeAll();
            AbsPlainLabel label_;
            label_ = getCompoFactory().newPlainLabel(messages.getVal(FOLDER_NAME));
            AbsPlainButton search_ = getCompoFactory().newPlainButton(CREATE);
            search_.addActionListener(new CreateFolderEvent(this));
            searchingPanel.add(label_);
            searchingPanel.add(typedString);
            searchingPanel.add(search_);
            getAbsDialog().getPane().add(searchingPanel, GuiConstants.BORDER_LAYOUT_NORTH);
        }
        pack();
    }

    public void createFolder() {
        if (typedString.getText().trim().isEmpty()) {
            return;
        }
        AbstractMutableTreeNode path_ = getFolderSystem().getSelected();
        if (path_ != null) {
            StringBuilder str_ = buildPath(path_);
            str_.append(typedString.getText());
            if (!getProgramInfos().getValidator().okPath(str_.toString(),'/','\\')) {
                return;
            }
            if (!StreamFolderFile.makeParent(str_.toString(), getProgramInfos().getFileCoreStream())) {
                return;
            }
            applyTreeChangeSelected();
        } else {
            if (!getProgramInfos().getValidator().okPath(StringUtil.concat(getFolder(),StreamTextFile.SEPARATEUR,typedString.getText().trim()),'/','\\')) {
                return;
            }
            if (!StreamFolderFile.makeParent(StringUtil.concat(getFolder(),StreamTextFile.SEPARATEUR,typedString.getText().trim()), getProgramInfos().getFileCoreStream())) {
                return;
            }
            applyTreeChange();
        }
    }

    @Override
    public void submitIfVisible() {
        if (!isVisible()) {
            return;
        }
        submit();
    }

    public void submit() {
        String errorTitle_ = messages.getVal(FORBIDDEN);
        String lg_ = getLang();
        String text_ = getFileName().getText();
        if (text_.trim().isEmpty()) {
            String errorContent_ = messages.getVal(FORBIDDEN_SPACES);
            getProgramInfos().getMessageDialogAbs().input(getAbsDialog(), errorContent_, errorTitle_,lg_, GuiConstants.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this, errorContent_, errorTitle_, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!getProgramInfos().getValidator().okPath(text_,'/','\\')) {
            String errorContent_ = messages.getVal(FORBIDDEN_SPECIAL_CHARS);
            getProgramInfos().getMessageDialogAbs().input(getAbsDialog(), errorContent_, errorTitle_, lg_, GuiConstants.ERROR_MESSAGE);
            return;
        }
        //get selected row first table
        AbstractFile file_ = getProgramInfos().getFileCoreStream().newFile(StringUtil.concat(getCurrentFolder(), text_,getExtension()));
        if (file_.exists()) {
            String mes_ = StringUtil.simpleStringsFormat(messages.getVal(BODY_CONF), StringUtil.concat(getCurrentFolder(), text_));
//            ConfirmDialog conf_ = new ConfirmDialog(
//                    this,
//                    mes_, messages.getVal(TITLE_CONF),
//                    getLang(),
//                    JOptionPane.YES_NO_OPTION);
            int answer_ = getProgramInfos().getConfirmDialogAns().input(
                    getAbsDialog(),frame,
                    mes_, messages.getVal(TITLE_CONF),
                    getLang(),
                    GuiConstants.YES_NO_OPTION);
            if (answer_ == GuiConstants.NO_OPTION) {
                return;
            }
        }
        setSelectedPath(StringUtil.concat(getCurrentFolder(), text_,getExtension()));
        getAbsDialog().closeWindow();
    }

    public static String getStaticSelectedPath(FileSaveDialog _dialog) {
        return _dialog.getSelectedPath();
    }

}
