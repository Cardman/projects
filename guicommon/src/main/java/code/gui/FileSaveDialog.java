package code.gui;
import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.TreePath;

import code.gui.events.CancelSelectFileEvent;
import code.gui.events.CreateFolderEvent;
import code.gui.events.SubmitKeyEvent;
import code.gui.events.SubmitMouseEvent;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstFiles;
import code.util.consts.Constants;

public final class FileSaveDialog extends FileDialog implements SingleFileSelection {
    private static final String DIALOG_ACCESS = "gui.FileSaveDialog";

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

    private static final char[] FORBIDDEN_CHARS_FILE_NAME = new char[]{'<', '>', '?', QUOTE, '*', '/', '\\', '|', ':'};

    private static final int NB_COLS = 24;

    private JTextField typedString = new JTextField(NB_COLS);

    private JPanel searchingPanel = new JPanel();

    private StringMap<String> messages;

    private FileSaveDialog() {
    	setAccessFile(DIALOG_ACCESS);
    }

    public static void setFileSaveDialog(GroupFrame _w,String _language,boolean _currentFolderRoot, String _extension, String _folder, String... _excludedFolders) {
        DIALOG.setFileDialog(_w,_language,_currentFolderRoot,_extension, _folder, _excludedFolders);
        DIALOG.initSaveDialog();
    }

    public static void setFileSaveDialog(Dialog _w,String _language,boolean _currentFolderRoot, String _extension, String _folder, String... _excludedFolders) {
        DIALOG.setFileDialog(_w,_language,_currentFolderRoot,_extension, _folder, _excludedFolders);
        DIALOG.initSaveDialog();
    }

    private void initSaveDialog() {
        messages = getMessages(GuiConstants.FOLDER_MESSAGES_GUI);
        getFileName().addActionListener(new SubmitKeyEvent(this));
        LabelButton action_ = new LabelButton(messages.getVal(SAVE));
        action_.addMouseListener(new SubmitMouseEvent(this));
        getButtons().add(action_);
        action_ = new LabelButton(messages.getVal(CANCEL));
        action_.addMouseListener(new CancelSelectFileEvent(this));
        getButtons().add(action_);
        if (StringList.quickEq(getFolder(),ConstFiles.getHomePath())) {
            searchingPanel.removeAll();
            JLabel label_;
            label_ = new JLabel(messages.getVal(FOLDER_NAME));
            LabelButton search_ = new LabelButton(CREATE);
            search_.addMouseListener(new CreateFolderEvent(this));
            searchingPanel.add(label_);
            searchingPanel.add(typedString);
            searchingPanel.add(search_);
            getContentPane().add(searchingPanel, BorderLayout.NORTH);
        }
        pack();
    }

    public void createFolder() {
        if (typedString.getText().trim().isEmpty()) {
            return;
        }
        TreePath treePath_ = getFolderSystem().getSelectionPath();
        if (treePath_ == null) {
            new File(getFolder()+StreamTextFile.SEPARATEUR+typedString.getText().trim()).mkdirs();
            applyTreeChange();
        } else {
            StringList pathFull_ = new StringList();
            for (Object o: treePath_.getPath()) {
                pathFull_.add(o.toString());
            }
            pathFull_.removeObj(EMPTY_STRING);
            String str_ = EMPTY_STRING;
            for (Object o: pathFull_) {
                str_ += o.toString() + StreamTextFile.SEPARATEUR;
            }
            new File(str_+typedString.getText()).mkdirs();
            applyTreeChange(treePath_);
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
        if (getFileName().getText().trim().isEmpty()) {
            String errorContent_ = messages.getVal(FORBIDDEN_SPACES);
            ConfirmDialog.showMessage(this, errorContent_, errorTitle_, Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(this, errorContent_, errorTitle_, JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean hasForbbidenChars_ = false;
        for (char c: getFileName().getText().toCharArray()) {
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
//        if (!StringList.matchingRegExp(getFileName().getText(), EXCLUDED_CHARS).isEmpty()) {
//            String errorContent_ = messages.getVal(FORBIDDEN_SPECIAL_CHARS);
//            ConfirmDialog.showMessage(this, errorContent_, errorTitle_, Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
//            JOptionPane.showMessageDialog(this, errorContent_, errorTitle_, JOptionPane.ERROR_MESSAGE);
//            return;
//        }
        if (hasForbbidenChars_) {
            String errorContent_ = messages.getVal(FORBIDDEN_SPECIAL_CHARS);
            ConfirmDialog.showMessage(this, errorContent_, errorTitle_, Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        //get selected row first table
        File file_ = new File(getCurrentFolder()+getFileName().getText()+getExtension());
        if (file_.exists()) {
            String mes_ = StringList.simpleFormat(messages.getVal(BODY_CONF), getCurrentFolder()+getFileName().getText());
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
        setSelectedPath(getCurrentFolder()+getFileName().getText()+getExtension());
        closeWindow();
    }

    public static String getStaticSelectedPath() {
        return DIALOG.getSelectedPath();
    }
}
