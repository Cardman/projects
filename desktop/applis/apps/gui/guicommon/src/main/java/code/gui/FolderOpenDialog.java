/**
    */
package code.gui;
import code.gui.files.CancelSelectFileEvent;
import code.gui.files.SubmitMouseEvent;
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

    public FolderOpenDialog(AbstractProgramInfos _frameFact) {
        super(_frameFact);
        getAbsDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void setFolderOpenDialog(String _language,
                                           boolean _currentFolderRoot, FolderOpenDialog _folder, AbsCommonFrame _fr) {
        _folder.initFolderOpenDialog(_language, _currentFolderRoot, _fr);
    }
    /**
     @param _language
    @param _currentFolderRoot
     * @param _commonFrame
    */
    private void initFolderOpenDialog(String _language,
                                      boolean _currentFolderRoot, AbsCommonFrame _commonFrame) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, _language, getAbsDialog().getAccessFile());
        String loadedResourcesMessages_ = MessGuiGr.ms().getVal(fileName_);
        StringMap<String> messages_ = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        initByFrame(_language, _currentFolderRoot, false, EMPTY_STRING, EMPTY_STRING, _commonFrame);
        AbsPlainButton action_ = getCompoFactory().newPlainButton(messages_.getVal(OPEN));
        action_.addActionListener(new SubmitMouseEvent(this));
        getButtons().add(action_);
        action_ = getCompoFactory().newPlainButton(messages_.getVal(CANCEL));
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
