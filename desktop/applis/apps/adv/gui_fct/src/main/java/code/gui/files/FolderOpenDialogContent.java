package code.gui.files;

import code.gui.initialize.AbstractProgramInfos;

public final class FolderOpenDialogContent extends FileDialogContent {

    private static final String EMPTY_STRING = "";

    public FolderOpenDialogContent(AbstractProgramInfos _frameFact) {
        super(_frameFact);
    }

    public void setFolderOpenDialog(boolean _currentFolderRoot, AbsPostFileDialogEvent _post,AbsButtonsOpenFolderPanel _build) {
        initByFrame(_currentFolderRoot, false, EMPTY_STRING, _post);
        initFolderOpenDialog(_build);
    }

    public void setFolderOpenDialogPart(boolean _currentFolderRoot, AbsPostFileDialogEvent _post) {
        initByFrame(_currentFolderRoot, false, EMPTY_STRING, _post);
        getPostFileDialogEvent().visible(getContentPane());
    }
//
//    public void setFolderOpenDialog(boolean _currentFolderRoot, AbsPostFileDialogEvent _post) {
//        initFolderOpenDialog(_currentFolderRoot, _post);
//    }
    public void initFolderOpenDialog(AbsButtonsOpenFolderPanel _build) {
        _build.build(this);
        getPostFileDialogEvent().visible(getContentPane());
    }
//    /**
//     * @param _currentFolderRoot
//     * @param _post
//     */
//    public void initFolderOpenDialog(boolean _currentFolderRoot, AbsPostFileDialogEvent _post) {
//        StringMap<String> messages_ = FileDialog.getAppliTr(getProgramInfos().currentLg()).getMapping().getVal(FolderOpenDialog.FOLDER_OPEN_DIAL).getMapping();
//        initByFrame(_currentFolderRoot, false, EMPTY_STRING, _post);
//        AbsButton action_ = getCompoFactory().newPlainButton(messages_.getVal(MessagesFolderOpenDialog.OPEN));
//        action_.addActionListener(new SubmitMouseEvent(this));
//        getButtons().add(action_);
//        action_ = getCompoFactory().newPlainButton(messages_.getVal(MessagesFolderOpenDialog.CANCEL));
//        action_.addActionListener(new CancelSelectFileEvent(this));
//        getButtons().add(action_);
//        getPostFileDialogEvent().visible(getContentPane());
//    }
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
    public String getSelectedPath() {
        return super.getCurrentFolder();
    }
}
