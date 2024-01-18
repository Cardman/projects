/**
    */
package code.gui.files;
import code.gui.AbsCommonFrame;
import code.gui.AbsButton;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

/**
 */
public final class FolderOpenDialog extends FileDialog implements SingleFileSelection {
    public static final String FOLDER_OPEN_DIAL = "folder_open";

    private static final String EMPTY_STRING = "";

    public FolderOpenDialog(AbstractProgramInfos _frameFact) {
        super(_frameFact);
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
        StringMap<String> messages_ = getAppliTr(getProgramInfos().getTranslations().getMapping().getVal(_language)).getMapping().getVal(FOLDER_OPEN_DIAL).getMapping();
        initByFrame(_language, _currentFolderRoot, false, EMPTY_STRING, _commonFrame);
        AbsButton action_ = getCompoFactory().newPlainButton(messages_.getVal(MessagesFolderOpenDialog.OPEN));
        action_.addActionListener(new SubmitMouseEvent(this));
        getButtons().add(action_);
        action_ = getCompoFactory().newPlainButton(messages_.getVal(MessagesFolderOpenDialog.CANCEL));
        action_.addActionListener(new CancelSelectFileEvent(this));
        getButtons().add(action_);
        pack();
    }

    @Override
    public void submitIfVisible() {
        if (getSelectedPath().isEmpty()) {
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
