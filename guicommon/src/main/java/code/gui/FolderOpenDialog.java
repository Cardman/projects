/**
    */
package code.gui;
import code.gui.events.CancelSelectFileEvent;
import code.gui.events.SubmitMouseEvent;
import code.stream.ExtractFromFiles;
import code.util.StringMap;
import code.util.consts.Constants;

/**
 */
public final class FolderOpenDialog extends FileDialog implements SingleFileSelection {
    private static final String DIALOG_ACCESS = "gui.FolderOpenDialog";

    private static final FolderOpenDialog DIALOG = new FolderOpenDialog();

    private static final String EMPTY_STRING = "";

    private static final String CANCEL = "cancel";

    private static final String OPEN = "open";

    private StringMap<String> messages = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, Constants.getLanguage(),DIALOG_ACCESS);

    private FolderOpenDialog() {
    }
    public static void setFolderOpenDialog(GroupFrame _w, String _language,
            boolean _currentFolderRoot) {
        DIALOG.initFolderOpenDialog(_w, _language, _currentFolderRoot);
    }
    /**
    @param _w
    @param _language
    @param _currentFolderRoot
    */
    private void initFolderOpenDialog(GroupFrame _w, String _language,
            boolean _currentFolderRoot) {
        init(_w, _language, _currentFolderRoot, false, EMPTY_STRING, EMPTY_STRING);
        LabelButton action_ = new LabelButton(messages.getVal(OPEN));
        action_.addMouseListener(new SubmitMouseEvent(this));
        getButtons().add(action_);
        action_ = new LabelButton(messages.getVal(CANCEL));
        action_.addMouseListener(new CancelSelectFileEvent(this));
        getButtons().add(action_);
        pack();
    }

    @Override
    public void submitIfVisible() {
        if (getSelectedPath() == null) {
            return;
        }
        if (!isVisible()) {
            return;
        }
        closeWindow();
    }

    @Override
    public void setSelectedPath(String _selectedPath) {
        super.setCurrentFolder(_selectedPath);
    }

    public static String getStaticSelectedPath() {
        return DIALOG.getSelectedPath();
    }

    @Override
    public String getSelectedPath() {
        return super.getCurrentFolder();
    }
}
