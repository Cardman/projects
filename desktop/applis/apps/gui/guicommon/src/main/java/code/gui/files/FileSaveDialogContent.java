package code.gui.files;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.StreamTextFile;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FileSaveDialogContent extends FileDialogContent {

    private static final String CREATE = "+";

    private static final int NB_COLS = 24;

    private final AbsTextField typedString = getCompoFactory().newTextField(NB_COLS);

    private final AbsPanel searchingPanel = getCompoFactory().newLineBox();
    private AbsButton search;

    private AbsWarnExistFile warnExistFile;
    public FileSaveDialogContent(AbstractProgramInfos _frameFact) {
        super(_frameFact);
    }

    public void setFileSaveDialogByFrame(AbsCommonFrame _w, String _language, boolean _currentFolderRoot, String _folder, AbsWarnExistFile _warn, AbsPostFileDialogEvent _post) {
        setFileDialogByFrame(_language,_currentFolderRoot, _folder, _post);
        initSaveDialog(_warn,_w, _w.getFrames().getHomePath());
    }

    public void setFileSaveDialog(AbsCommonFrame _c, String _language, boolean _currentFolderRoot, String _folder, AbsWarnExistFile _warn, AbsPostFileDialogEvent _post) {
        setFileDialog(_language,_currentFolderRoot, _folder,_post);
        initSaveDialog(_warn,_c, _c.getFrames().getHomePath());
    }

    private void initSaveDialog(AbsWarnExistFile _warn, AbsCommonFrame _c, String _homePath) {
        warnExistFile = _warn;
        StringMap<String> messages_ = FileDialog.getAppliTr(getProgramInfos().getTranslations().getMapping().getVal(_c.getLanguageKey())).getMapping().getVal(FileSaveDialog.FILE_SAVE_DIAL).getMapping();
        AbsButton action_ = getCompoFactory().newPlainButton(messages_.getVal(MessagesFileSaveDialog.SAVE));
        action_.addActionListener(new SubmitMouseEvent(this));
        getButtons().add(action_);
        action_ = getCompoFactory().newPlainButton(messages_.getVal(MessagesFileSaveDialog.CANCEL));
        action_.addActionListener(new CancelSelectFileEvent(this));
        getButtons().add(action_);
        if (StringUtil.quickEq(getFolder(), _homePath)) {
            searchingPanel.removeAll();
            AbsPlainLabel label_;
            label_ = getCompoFactory().newPlainLabel(messages_.getVal(MessagesFileSaveDialog.FOLDER_NAME));
            search = getCompoFactory().newPlainButton(CREATE);
            search.addActionListener(new CreateFolderEvent(this));
            searchingPanel.add(label_);
            searchingPanel.add(typedString);
            searchingPanel.add(search);
            getContentPane().add(searchingPanel, GuiConstants.BORDER_LAYOUT_NORTH);
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
            if (FileSaveDialog.koCreate(str_, getProgramInfos())) {
                return;
            }
            applyTreeChangeSelected();
        } else {
            if (FileSaveDialog.koCreate(StringUtil.concat(getFolder(), StreamTextFile.SEPARATEUR, typed_), getProgramInfos())) {
                return;
            }
            applyTreeChange();
        }
    }

    public void submitIfVisible() {
        submit();
    }

    public void submit() {
        getErrors().setText("");
        String lg_ = getLang();
        StringMap<String> messages_ = FileDialog.getAppliTr(getProgramInfos().getTranslations().getMapping().getVal(lg_)).getMapping().getVal(FileSaveDialog.FILE_SAVE_DIAL).getMapping();
        String text_ = getFileName().getText();
        if (text_.trim().isEmpty()) {
            String errorContent_ = messages_.getVal(MessagesFileSaveDialog.FORBIDDEN_SPACES);
            getErrors().setText(errorContent_);
            //JOptionPane.showMessageDialog(this, errorContent_, errorTitle_, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!getProgramInfos().getValidator().okPath(text_,'/','\\')) {
            String errorContent_ = messages_.getVal(MessagesFileSaveDialog.FORBIDDEN_SPECIAL_CHARS);
            getErrors().setText(errorContent_);
            return;
        }
        //get selected row first table
        String path_ = StringUtil.concat(getCurrentFolder(), text_);
        if (warnExistFile.skipSave(getProgramInfos(),path_,lg_)) {
            return;
        }
        getPostFileDialogEvent().act(path_);
    }

    public AbsButton getSearch() {
        return search;
    }

    public AbsTextField getTypedString() {
        return typedString;
    }
}
