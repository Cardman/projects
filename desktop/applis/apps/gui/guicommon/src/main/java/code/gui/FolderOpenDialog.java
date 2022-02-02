/**
    */
package code.gui;
import code.gui.events.CancelSelectFileEvent;
import code.gui.events.SubmitMouseEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;

/**
 */
public final class FolderOpenDialog extends FileDialog implements SingleFileSelection {
    private static final String DIALOG_ACCESS = "gui.folderopendialog";

    private static final String EMPTY_STRING = "";

    private static final String CANCEL = "cancel";

    private static final String OPEN = "open";

    private StringMap<String> messages;

    public FolderOpenDialog(AbstractProgramInfos _frameFact) {
        super(_frameFact);
        getAbsDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void setFolderOpenDialog(GroupFrame _w, String _language,
            boolean _currentFolderRoot) {
        _w.getFolderOpenDialog().initFolderOpenDialog(_w, _language, _currentFolderRoot);
    }
    /**
    @param _w
    @param _language
    @param _currentFolderRoot
    */
    private void initFolderOpenDialog(GroupFrame _w, String _language,
            boolean _currentFolderRoot) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, _w.getLanguageKey(), getAbsDialog().getAccessFile());
        String loadedResourcesMessages_ = MessGuiGr.ms().getVal(fileName_);
        messages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        initByFrame(_w, _language, _currentFolderRoot, false, EMPTY_STRING, EMPTY_STRING);
        AbsPlainButton action_ = getCompoFactory().newPlainButton(messages.getVal(OPEN));
        action_.addActionListener(new SubmitMouseEvent(this));
        getButtons().add(action_);
        action_ = getCompoFactory().newPlainButton(messages.getVal(CANCEL));
        action_.addActionListener(new CancelSelectFileEvent(this));
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
        getAbsDialog().closeWindow();
    }

    @Override
    public void setSelectedPath(String _selectedPath) {
        super.setCurrentFolder(_selectedPath);
    }

    public static String getStaticSelectedPath(FolderOpenDialog _dialog) {
        return _dialog.getSelectedPath();
    }

    @Override
    protected String getSelectedPath() {
        return super.getCurrentFolder();
    }
}
