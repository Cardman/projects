package code.gui;
import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import code.gui.events.CancelSelectFileEvent;
import code.gui.events.CreateFolderEvent;
import code.gui.events.SubmitKeyEvent;
import code.gui.events.SubmitMouseEvent;
import code.stream.StreamTextFile;
import code.util.CharList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FileSaveDialog extends FileDialog implements SingleFileSelection {
    private static final String DIALOG_ACCESS = "gui.filesavedialog";

    private static final FileSaveDialog DIALOG = new FileSaveDialog();

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

    private static final char QUOTE = 34;

    private static final char[] FORBIDDEN_CHARS_FILE_NAME = CharList.wrapCharArray('<', '>', '?', QUOTE, '*', '/', '\\', '|', ':');

    private static final int NB_COLS = 24;

    private TextField typedString = new TextField(NB_COLS);

    private Panel searchingPanel = Panel.newLineBox();

    private StringMap<String> messages;
    private CommonFrame frame;

    private FileSaveDialog() {
        setAccessFile(DIALOG_ACCESS);
    }

    public static void setFileSaveDialogByFrame(GroupFrame _w, String _language, boolean _currentFolderRoot, String _extension, String _folder, String _homePath, String... _excludedFolders) {
        DIALOG.setFileDialogByFrame(_w,_language,_currentFolderRoot,_extension, _folder, _excludedFolders);
        DIALOG.initSaveDialog(_w, _homePath);
    }

    public static void setFileSaveDialog(CommonFrame _c, Dialog _w, String _language, boolean _currentFolderRoot, String _extension, String _folder, String _homePath, String... _excludedFolders) {
        DIALOG.setFileDialog(_c,_w,_language,_currentFolderRoot,_extension, _folder, _excludedFolders);
        DIALOG.initSaveDialog(_c, _homePath);
    }

    private void initSaveDialog(CommonFrame _c, String _homePath) {
        frame =_c;
        messages = getMessages(_c, GuiConstants.FOLDER_MESSAGES_GUI);
        getFileName().addActionListener(new SubmitKeyEvent(this));
        LabelButton action_ = new LabelButton(messages.getVal(SAVE));
        action_.addMouseListener(new SubmitMouseEvent(this));
        getButtons().add(action_);
        action_ = new LabelButton(messages.getVal(CANCEL));
        action_.addMouseListener(new CancelSelectFileEvent(this));
        getButtons().add(action_);
        if (StringUtil.quickEq(getFolder(), _homePath)) {
            searchingPanel.removeAll();
            TextLabel label_;
            label_ = new TextLabel(messages.getVal(FOLDER_NAME));
            LabelButton search_ = new LabelButton(CREATE);
            search_.addMouseListener(new CreateFolderEvent(this));
            searchingPanel.add(label_);
            searchingPanel.add(typedString);
            searchingPanel.add(search_);
            getPane().add(searchingPanel, BorderLayout.NORTH);
        }
        pack();
    }

    public void createFolder() {
        if (typedString.getText().trim().isEmpty()) {
            return;
        }
        Object sel_;
        sel_ =getFolderSystem().getLastSelectedPathComponent();
        if (sel_ instanceof DefaultMutableTreeNode) {
            StringBuilder str_ = buildPath((DefaultMutableTreeNode) sel_);
            str_.append(typedString.getText());
            new File(str_.toString()).mkdirs();
            applyTreeChangeSelected();
        } else {
            new File(StringUtil.concat(getFolder(),StreamTextFile.SEPARATEUR,typedString.getText().trim())).mkdirs();
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
            ConfirmDialog.showMessage(this, errorContent_, errorTitle_,lg_, JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this, errorContent_, errorTitle_, JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean hasForbbidenChars_ = false;
        for (char c: text_.toCharArray()) {
            for (char e: FORBIDDEN_CHARS_FILE_NAME) {
                if (c == e) {
                    hasForbbidenChars_ = true;
                    break;
                }
            }
            if (hasForbbidenChars_) {
                break;
            }
        }
        if (StringUtil.quickEq(text_,"..")) {
            hasForbbidenChars_ = true;
        }
        if (StringUtil.quickEq(text_,".")) {
            hasForbbidenChars_ = true;
        }
        if (hasForbbidenChars_) {
            String errorContent_ = messages.getVal(FORBIDDEN_SPECIAL_CHARS);
            ConfirmDialog.showMessage(this, errorContent_, errorTitle_, lg_, JOptionPane.ERROR_MESSAGE);
            return;
        }
        //get selected row first table
        File file_ = new File(StringUtil.concat(getCurrentFolder(), text_,getExtension()));
        if (file_.exists()) {
            String mes_ = StringUtil.simpleStringsFormat(messages.getVal(BODY_CONF), StringUtil.concat(getCurrentFolder(), text_));
//            ConfirmDialog conf_ = new ConfirmDialog(
//                    this,
//                    mes_, messages.getVal(TITLE_CONF),
//                    getLang(),
//                    JOptionPane.YES_NO_OPTION);
            ConfirmDialog conf_ = ConfirmDialog.showMiniDialog(
                this,
                mes_, messages.getVal(TITLE_CONF),
                getLang(),
                JOptionPane.YES_NO_OPTION);
            int answer_ = conf_.getAnswer();
            if (answer_ == JOptionPane.NO_OPTION) {
                return;
            }
        }
        setSelectedPath(StringUtil.concat(getCurrentFolder(), text_,getExtension()));
        closeWindow();
    }

    public static String getStaticSelectedPath() {
        return DIALOG.getSelectedPath();
    }
}
