package code.gui.files;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.PathsUtil;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FileSaveDialogContent extends FileDialogContent {

    private static final String CREATE = "+";

    private static final int NB_COLS = 24;

    private final AbsTextField typedString = getCompoFactory().newTextField(NB_COLS);

    private final AbsPanel searchingPanel = getCompoFactory().newLineBox();
    private AbsButton search;
    private final boolean submitAuto;

    public FileSaveDialogContent(AbstractProgramInfos _frameFact) {
        this(_frameFact, false);
    }

    public FileSaveDialogContent(AbstractProgramInfos _frameFact, boolean _s) {
        super(_frameFact);
        submitAuto = _s;
    }

    public boolean isSubmitAuto() {
        return submitAuto;
    }

    public void setFileSaveDialogByFrame(boolean _currentFolderRoot, String _folder, AbsPostFileDialogEvent _post, AbsButtonsSavePanel _build) {
        setFileDialogByFrame(_currentFolderRoot, _folder, _post);
        initSaveDialog(getProgramInfos().getHomePath(), _build);
    }


    public void setFileSaveContentDialogByFrame(boolean _currentFolderRoot, String _folder, AbsPostFileDialogEvent _post) {
        setFileDialogByFrame(_currentFolderRoot, _folder, _post);
        initSaveDialog(getProgramInfos().getHomePath());
    }

    private void initSaveDialog(String _homePath, AbsButtonsSavePanel _build) {
        _build.build(this);
        initSaveDialog(_homePath);
    }

    private void initSaveDialog(String _homePath) {
        StringMap<String> messages_ = MessagesGuiFct.getAppliTr(getProgramInfos().currentLg()).getMapping().getVal(MessagesGuiFct.FILE_SAVE_DIAL).getMapping();
        if (StringUtil.quickEq(getFolder(), _homePath)) {
            searchingPanel.removeAll();
            AbsPlainLabel label_;
            label_ = getCompoFactory().newPlainLabel(messages_.getVal(MessagesFileSaveDialog.FOLDER_NAME));
            search = getCompoFactory().newPlainButton(CREATE);
            search.addActionListener(new CreateFolderEvent(this));
            searchingPanel.add(label_);
            searchingPanel.add(typedString);
            searchingPanel.add(search);
            getContentPane().add(searchingPanel, MessagesGuiFct.BORDER_LAYOUT_NORTH);
        }
        getPostFileDialogEvent().visible(getContentPane());
    }

    public void createFolder() {
        String typed_ = typedString.getText().trim();
        if (typed_.isEmpty()) {
            return;
        }
        AbstractMutableTreeNodeCore<String> path_ = getFolderSystem().selectEvt();
        if (path_ != null) {
            String str_ = buildPath(path_)+ typed_;
            if (koCreate(str_, getProgramInfos())) {
                return;
            }
            applyTreeChangeSelected();
        } else {
            if (koCreate(StringUtil.concat(getFolder(), PathsUtil.SEPARATEUR, typed_), getProgramInfos())) {
                return;
            }
            applyTreeChange();
        }
    }

    public static boolean koCreate(String _p, AbstractProgramInfos _pr) {
        return !_pr.getValidator().okPath(PathsUtil.getRelativeRootPath(_p,_pr.getFileCoreStream()), '/', '\\') || !PathsUtil.mkdirs(_p, _pr.getFileCoreStream());
    }
    @Override
    public void clickRow() {
        super.clickRow();
        if (submitAuto) {
            submitIfVisible();
        }
    }

    @Override
    public void submitIfVisible() {
        submit();
    }

    public void submit() {
        getErrors().setText("");
        StringMap<String> messages_ = MessagesGuiFct.getAppliTr(getProgramInfos().currentLg()).getMapping().getVal(MessagesGuiFct.FILE_SAVE_DIAL).getMapping();
        String text_ = getFileName().getText();
        if (text_.trim().isEmpty()) {
            String errorContent_ = messages_.getVal(MessagesFileSaveDialog.FORBIDDEN_SPACES);
            getErrors().setText(errorContent_);
            //JOptionPane.showMessageDialog(this, errorContent_, errorTitle_, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!getProgramInfos().getValidator().okPath(PathsUtil.getRelativeRootPath(text_,getProgramInfos().getFileCoreStream()),'/','\\')) {
            String errorContent_ = messages_.getVal(MessagesFileSaveDialog.FORBIDDEN_SPECIAL_CHARS);
            getErrors().setText(errorContent_);
            return;
        }
        if (PathsUtil.isAbsolute(text_,getProgramInfos().getFileCoreStream())) {
            getPostFileDialogEvent().act(text_);
            return;
        }
        //get selected row first table
        String path_ = StringUtil.concat(getCurrentFolder(), text_);
        getPostFileDialogEvent().act(path_);
    }

    public AbsButton getSearch() {
        return search;
    }

    public AbsTextField getTypedString() {
        return typedString;
    }
}
