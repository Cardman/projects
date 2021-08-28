package code.gui;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;

import code.gui.events.*;
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

    private static final String EMPTY_STRING = "";

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
    private ConfirmDialog dialog;

    private final AbsTextField typedString = getCompoFactory().newTextField(NB_COLS);

    private final AbsPanel searchingPanel = getCompoFactory().newLineBox();

    private StringMap<String> messages;
    private GroupFrame frame;

    public FileSaveDialog(AbstractProgramInfos _frameFact) {
        super(_frameFact);
        getAbsDialog().setAccessFile(DIALOG_ACCESS);
    }

    public static void setFileSaveDialogByFrame(GroupFrame _w, String _language, boolean _currentFolderRoot, String _extension, String _folder, String _homePath, String... _excludedFolders) {
        _w.getFileSaveDialog().dialog = _w.getConfirmDialog();
        _w.getFileSaveDialog().setFileDialogByFrame(_w,_language,_currentFolderRoot,_extension, _folder, _excludedFolders);
        _w.getFileSaveDialog().initSaveDialog(_w, _homePath);
    }

    public static void setFileSaveDialog(GroupFrame _c, AbsDialog _w, String _language, boolean _currentFolderRoot, String _extension, String _folder, String _homePath, GroupFrame _dialog, String... _excludedFolders) {
        _dialog.getFileSaveDialog().dialog = _dialog.getConfirmDialog();
        _dialog.getFileSaveDialog().setFileDialog(_c,_w,_language,_currentFolderRoot,_extension, _folder, _excludedFolders);
        _dialog.getFileSaveDialog().initSaveDialog(_c, _homePath);
    }

    private void initSaveDialog(GroupFrame _c, String _homePath) {
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
            getAbsDialog().getPane().add(searchingPanel, BorderLayout.NORTH);
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
            if (!frame.getValidator().okPath(str_.toString(),'/','\\')) {
                return;
            }
            if (!StreamFolderFile.makeParent(str_.toString(), getSuperFrame().getFileCoreStream())) {
                return;
            }
            applyTreeChangeSelected();
        } else {
            if (!frame.getValidator().okPath(StringUtil.concat(getFolder(),StreamTextFile.SEPARATEUR,typedString.getText().trim()),'/','\\')) {
                return;
            }
            if (!StreamFolderFile.makeParent(StringUtil.concat(getFolder(),StreamTextFile.SEPARATEUR,typedString.getText().trim()), getSuperFrame().getFileCoreStream())) {
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
        String lg_ = frame.getLanguageKey();
        String text_ = getFileName().getText();
        if (text_.trim().isEmpty()) {
            String errorContent_ = messages.getVal(FORBIDDEN_SPACES);
            ConfirmDialog.showMessage(getAbsDialog(), errorContent_, errorTitle_,lg_, JOptionPane.ERROR_MESSAGE, dialog);
            //JOptionPane.showMessageDialog(this, errorContent_, errorTitle_, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!frame.getValidator().okPath(text_,'/','\\')) {
            String errorContent_ = messages.getVal(FORBIDDEN_SPECIAL_CHARS);
            ConfirmDialog.showMessage(getAbsDialog(), errorContent_, errorTitle_, lg_, JOptionPane.ERROR_MESSAGE, dialog);
            return;
        }
        //get selected row first table
        AbstractFile file_ = getSuperFrame().getFileCoreStream().newFile(StringUtil.concat(getCurrentFolder(), text_,getExtension()));
        if (file_.exists()) {
            String mes_ = StringUtil.simpleStringsFormat(messages.getVal(BODY_CONF), StringUtil.concat(getCurrentFolder(), text_));
//            ConfirmDialog conf_ = new ConfirmDialog(
//                    this,
//                    mes_, messages.getVal(TITLE_CONF),
//                    getLang(),
//                    JOptionPane.YES_NO_OPTION);
            ConfirmDialog conf_ = ConfirmDialog.showMiniDialog(
                    getAbsDialog(),
                mes_, messages.getVal(TITLE_CONF),
                getLang(),
                JOptionPane.YES_NO_OPTION, dialog);
            int answer_ = conf_.getAnswer();
            if (answer_ == JOptionPane.NO_OPTION) {
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
