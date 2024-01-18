package code.gui.files;

import code.gui.AbsButton;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class FolderOpenDialogContent extends FileDialogContent {

    private static final String EMPTY_STRING = "";

    public FolderOpenDialogContent(AbstractProgramInfos _frameFact) {
        super(_frameFact);
    }
    public void setFolderOpenDialog(String _language,
                                    boolean _currentFolderRoot, AbsPostFileDialogEvent _post) {
        initFolderOpenDialog(_language, _currentFolderRoot, _post);
    }
    /**
     * @param _language
     * @param _currentFolderRoot
     * @param _post
     */
    public void initFolderOpenDialog(String _language,
                                     boolean _currentFolderRoot, AbsPostFileDialogEvent _post) {
        StringMap<String> messages_ = FileDialog.getAppliTr(getProgramInfos().getTranslations().getMapping().getVal(_language)).getMapping().getVal(FolderOpenDialog.FOLDER_OPEN_DIAL).getMapping();
        initByFrame(_language, _currentFolderRoot, false, EMPTY_STRING, _post);
        AbsButton action_ = getCompoFactory().newPlainButton(messages_.getVal(MessagesFolderOpenDialog.OPEN));
        action_.addActionListener(new SubmitMouseEvent(this));
        getButtons().add(action_);
        action_ = getCompoFactory().newPlainButton(messages_.getVal(MessagesFolderOpenDialog.CANCEL));
        action_.addActionListener(new CancelSelectFileEvent(this));
        getButtons().add(action_);
        getPostFileDialogEvent().visible(getContentPane());
    }
    @Override
    public void submitIfVisible() {
        if (getSelectedPath().isEmpty()) {
            return;
        }
        getPostFileDialogEvent().act(getSelectedPath());
    }

    @Override
    public void setSelectedPath(String _selectedPath) {
        super.setCurrentFolder(_selectedPath);
    }

    @Override
    protected String getSelectedPath() {
        return super.getCurrentFolder();
    }
}
