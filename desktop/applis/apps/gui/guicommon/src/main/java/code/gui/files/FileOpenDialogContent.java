package code.gui.files;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.AbstractFile;
import code.stream.StreamFolderFile;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractThread;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class FileOpenDialogContent extends FileDialogContent {

    //    private static final String ERROR_TYPING = "errorTyping";
    private static final int NB_COLS = 24;
    private AbsTextField typedString = getCompoFactory().newTextField(NB_COLS);
    private final AbsPanel searchingPanel = getCompoFactory().newPageBox();

    private AbstractThread thread;

    private final AbstractAtomicBoolean keepSearching;

    private final AbstractAtomicBoolean showNewResults;

    private AbsPlainLabel searchedFiles = getCompoFactory().newPlainLabel("");

    private AbsPlainLabel foundFiles = getCompoFactory().newPlainLabel("");
    private final AbstractAtomicBoolean enabledSearch;
    private AbsButton searchButton;
    private AbsButton stop;
    public FileOpenDialogContent(AbstractAtomicBoolean _keepSearching, AbstractAtomicBoolean _showNewResults, AbstractProgramInfos _frameFact) {
        super(_frameFact);
        enabledSearch = _frameFact.getThreadFactory().newAtomicBoolean(true);
        keepSearching = _keepSearching;
        showNewResults = _showNewResults;
    }

    public void setFileOpenDialog(boolean _currentFolderRoot, String _folder, AbsPostFileDialogEvent _post,AbsButtonsOpenPanel _build) {
//        DIALOG.initFileOpenDialog(_w, _language, _currentFolderRoot, _extension, _folder, _excludedFolders);
        setFileDialogByFrame(_currentFolderRoot,_folder, _post);
        initFileOpenDialog(_build);
    }

    public void setFileOpenDialogPart(boolean _currentFolderRoot, String _folder, AbsPostFileDialogEvent _post) {
//        DIALOG.initFileOpenDialog(_w, _language, _currentFolderRoot, _extension, _folder, _excludedFolders);
        setFileDialogByFrame(_currentFolderRoot,_folder, _post);
        common();
    }
    public void initFileOpenDialog(AbsButtonsOpenPanel _build) {
        _build.build(this);
        common();
    }

    private void common() {
        StringMap<String> messages_ = FileDialog.getAppliTr(getProgramInfos().currentLg()).getMapping().getVal(FileOpenDialog.FILE_OPEN_DIAL).getMapping();
        AbsPlainLabel label_;
        label_ = getCompoFactory().newPlainLabel(messages_.getVal(MessagesFileOpenDialog.TYPE_TEXT));
        searchButton = getCompoFactory().newPlainButton(messages_.getVal(MessagesFileOpenDialog.SEARCH));
        searchButton.addActionListener(new SearchingEvent(this, searchButton));
        searchButton.setEnabled(enabledSearch.get());
        searchingPanel.removeAll();
        AbsPanel panel_ = getCompoFactory().newLineBox();
        panel_.add(label_);
        typedString = getCompoFactory().newTextField(NB_COLS);
        panel_.add(typedString);
        panel_.add(searchButton);
        searchingPanel.add(panel_);
        stop = getCompoFactory().newPlainButton(messages_.getVal(MessagesFileOpenDialog.STOP_SEARCHING));
        stop.addActionListener(new StopSearchingEvent(this, true));
        searchingPanel.add(stop);
        AbsButton cancelSearching_ = getCompoFactory().newPlainButton(messages_.getVal(MessagesFileOpenDialog.CANCEL_SEARCHING));
        cancelSearching_.addActionListener(new StopSearchingEvent(this, false));
        searchingPanel.add(cancelSearching_);
        searchedFiles = getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(messages_.getVal(MessagesFileOpenDialog.FILE_COUNT), 0));
        searchingPanel.add(searchedFiles);
        foundFiles = getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(messages_.getVal(MessagesFileOpenDialog.RESULT_COUNT), 0));
        searchingPanel.add(foundFiles);
        getContentPane().add(searchingPanel, GuiConstants.BORDER_LAYOUT_NORTH);
        getPostFileDialogEvent().visible(getContentPane());
    }

    public AbsButton getSearchButton() {
        return searchButton;
    }

    public void searchFile(AbsButton _but) {
        AbstractFile currentFolder_ = getProgramInfos().getFileCoreStream().newFile(getCurrentFolder());
//        if (!currentFolder_.exists()) {
//            AbstractMutableTreeNodeCore<String> sel_ = getFolderSystem().selectEvt();
//            if (sel_ == null) {
//                return;
//            }
//            sel_.removeFromParent();
//            return;
//        }
        CustList<AbstractFile> backup_ = new CustList<AbstractFile>(getFiles());
        init(getCurrentFolder());
        getFileModel().clear();
        _but.setEnabled(false);
        setKeepSearching(true);
        thread = getProgramInfos().getThreadFactory().newThread(new ThreadSearchingFile(this, backup_, currentFolder_,_but));
        thread.start();
//        setKeepSearching(true);
//        Cursor cursor_ = getCursor();
//        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        CustList<File> results_;
//        results_ = new CustList<File>();
//        CustList<File> current_;
//        current_ = new CustList<File>(currentFolder_);
//        CustList<File> next_;
//        next_ = new CustList<File>();
//        while (true) {
//            for (File d: current_) {
//                File[] files_ = d.listFiles();
//                if (files_ == null) {
//                    continue;
//                }
//                for (File f: files_) {
//                    if (f.isDirectory()) {
//                        if (getExcludedFolders().containsObj(f.getAbsolutePath().replace(StringList.BACK_SLASH, StreamTextFile.SEPARATEUR))) {
//                            continue;
//                        }
//                        next_.add(f);
//                    } else {
//                        if (StringList.match(f.getName(), typedString.getText())) {
//                            if (f.getName().endsWith(getExtension())) {
//                                results_.add(f);
//                            }
//                        }
//                    }
//                }
//            }
//            if (next_.isEmpty()) {
//                break;
//            }
//            current_ = new CustList<File>(next_);
//            next_ = new CustList<File>();
//        }
//        getFileModel().setupFiles(results_,getCurrentFolder(), getExtension());
//        setCursor(cursor_);
//        setKeepSearching(false);
    }

    public AbstractThread getThread() {
        return thread;
    }

    @Override
    public void clickRow() {
        int index_ = getFileTable().getSelectedRow();
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        super.clickRow();
        submitIfVisible();
    }

    @Override
    public void submitIfVisible() {
        submit();
    }

    public void submit() {
        getErrors().setText("");
        String fileName_ = getFileName().getText();
        String selectedRelPath_ = StringUtil.concat(getCurrentFolder(), fileName_);
        if (getProgramInfos().getFileCoreStream().newFile(selectedRelPath_).exists()) {
            getPostFileDialogEvent().act(selectedRelPath_);
            return;
        }
        String selectedPath_ = getSelectedAbsolutePath();
        StringMap<String> messages_ = FileDialog.getAppliTr(getProgramInfos().currentLg()).getMapping().getVal(FileOpenDialog.FILE_OPEN_DIAL).getMapping();
        if (!selectedPath_.isEmpty()) {
            selectedPath_ = StringUtil.replaceBackSlash(selectedPath_);
            proc(selectedPath_, messages_);
            return;
        }
        if (getFileTable().getSelectedRowCount() == 1) {
            selectedPath_ = getFileModel().getSelectedFilePath(getFileTable().getSelectedRow());
            proc(selectedPath_, messages_);
            return;
        }
//        if (fileName_.isEmpty()) {
//            getProgramInfos().getMessageDialogAbs().input(getAbsDialog(), messages.getVal(ERROR_TYPING), messages.getVal(ERROR_TITLE),lg_, GuiConstants.ERROR_MESSAGE);
//            return;
//        }
        if (!StreamFolderFile.isAbsolute(fileName_, getProgramInfos().getFileCoreStream())) {
            selectedPath_ = StringUtil.concat(getCurrentFolder(), fileName_);
        } else {
            selectedPath_ = fileName_;
        }
        if (!getProgramInfos().getFileCoreStream().newFile(selectedPath_).exists()) {
            getErrors().setText(StringUtil.simpleStringsFormat(messages_.getVal(MessagesFileOpenDialog.ERROR_MESSAGE), selectedPath_));
            setSelectedPath("");
            return;
        }
        getPostFileDialogEvent().act(selectedPath_);
    }

    private void proc(String _selectedPath, StringMap<String> _messages) {
        if (!getProgramInfos().getFileCoreStream().newFile(_selectedPath).exists()) {
            getErrors().setText(StringUtil.simpleStringsFormat(_messages.getVal(MessagesFileOpenDialog.ERROR_MESSAGE), _selectedPath));
            setSelectedPath("");
            setSelectedAbsolutePath("");
            return;
        }
        getPostFileDialogEvent().act(_selectedPath);
    }

    public void setInformations(long _s, long _f) {
        StringMap<String> messages_ = FileDialog.getAppliTr(getProgramInfos().currentLg()).getMapping().getVal(FileOpenDialog.FILE_OPEN_DIAL).getMapping();
        searchedFiles.setText(StringUtil.simpleNumberFormat(messages_.getVal(MessagesFileOpenDialog.FILE_COUNT), _s));
        foundFiles.setText(StringUtil.simpleNumberFormat(messages_.getVal(MessagesFileOpenDialog.RESULT_COUNT), _f));
    }

//    @Override
//    public void closeWindow() {
////        if (thread != null) {
////            while (thread.isAlive()) {
////                continue;
////            }
////        }
//        setKeepSearching(false);
//        super.closeWindow();
//    }


    public String typedString() {
        return typedString.getText();
    }

    public AbsTextField getTypedString() {
        return typedString;
    }

    public AbsButton getStop() {
        return stop;
    }

    public void init(String _folder) {
        getFileModel().init(_folder);
    }

    public CustList<AbstractFile> getFiles() {
        return getFileModel().getFiles();
    }

    public AbstractAtomicBoolean getKeepSearching() {
        return keepSearching;
    }

    public boolean isKeepSearching() {
        return keepSearching.get();
    }

    public void setKeepSearching(boolean _keepSearching) {
        keepSearching.set(_keepSearching);
    }

    public boolean isShowNewResults() {
        return showNewResults.get();
    }

    public void setShowNewResults(boolean _showNewResults) {
        showNewResults.set(_showNewResults);
    }
}
