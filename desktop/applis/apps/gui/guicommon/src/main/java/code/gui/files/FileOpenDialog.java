package code.gui.files;




import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.AbstractFile;
import code.stream.StreamFolderFile;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractThread;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class FileOpenDialog extends FileDialog implements SingleFileSelection {
    private static final String DIALOG_ACCESS = "gui.fileopendialog";

    private static final String SEARCH = "search";
    private static final String TYPE_TEXT = "typeText";
    private static final String CANCEL = "cancel";
    private static final String OPEN = "open";
    private static final String CANCEL_SEARCHING = "cancelSearching";
    private static final String STOP_SEARCHING = "stopSearching";
    private static final String FILE_COUNT = "fileCount";
    private static final String RESULT_COUNT = "resultCount";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ERROR_TITLE = "errorTitle";
//    private static final String ERROR_TYPING = "errorTyping";
    private static final int NB_COLS = 24;
    private AbsTextField typedString = getCompoFactory().newTextField(NB_COLS);
    private final AbsPanel searchingPanel = getCompoFactory().newPageBox();

    private StringMap<String> messages;

    private AbstractThread thread;

    private final AbstractAtomicBoolean keepSearching;

    private final AbstractAtomicBoolean showNewResults;

    private AbsPlainLabel searchedFiles = getCompoFactory().newPlainLabel("");

    private AbsPlainLabel foundFiles = getCompoFactory().newPlainLabel("");
    private AbsCommonFrame frame;
    private final AbstractAtomicBoolean enabledSearch;
    private AbsButton searchButton;
    private AbsButton stop;

    public FileOpenDialog(AbstractAtomicBoolean _keepSearching, AbstractAtomicBoolean _showNewResults, AbstractProgramInfos _frameFact){
        super(_frameFact);
        enabledSearch = _frameFact.getThreadFactory().newAtomicBoolean(true);
        getAbsDialog().setAccessFile(DIALOG_ACCESS);
        keepSearching = _keepSearching;
        showNewResults = _showNewResults;
    }
    public static void setFileOpenDialog(String _language, boolean _currentFolderRoot, String _folder, FileOpenDialog _fileOpen, AbsCommonFrame _fr) {
        _fileOpen.setFileDialogByFrame(_language, _currentFolderRoot, _folder, _fr);
//        DIALOG.initFileOpenDialog(_w, _language, _currentFolderRoot, _extension, _folder, _excludedFolders);
        _fileOpen.initFileOpenDialog(_fr);
    }

//    private void initFileOpenDialog(GroupFrame _w,String _language,boolean _currentFolderRoot, String _extension, String _folder, String... _excludedFolders) {
//    }
    private void initFileOpenDialog(AbsCommonFrame _c) {
        frame = _c;
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, _c.getLanguageKey(), getAbsDialog().getAccessFile());
        String loadedResourcesMessages_ = MessGuiGr.ms().getVal(fileName_);
        messages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        AbsButton action_ = getCompoFactory().newPlainButton(messages.getVal(OPEN));
        action_.addActionListener(new SubmitMouseEvent(this));
        getButtons().add(action_);
        action_ = getCompoFactory().newPlainButton(messages.getVal(CANCEL));
        action_.addActionListener(new CancelSelectFileEvent(this));
        getButtons().add(action_);
        AbsPlainLabel label_;
        label_ = getCompoFactory().newPlainLabel(messages.getVal(TYPE_TEXT));
        searchButton = getCompoFactory().newPlainButton(messages.getVal(SEARCH));
        searchButton.addActionListener(new SearchingEvent(this, searchButton));
        searchButton.setEnabled(enabledSearch.get());
        searchingPanel.removeAll();
        AbsPanel panel_ = getCompoFactory().newLineBox();
        panel_.add(label_);
        typedString = getCompoFactory().newTextField(NB_COLS);
        panel_.add(typedString);
        panel_.add(searchButton);
        searchingPanel.add(panel_);
        stop = getCompoFactory().newPlainButton(messages.getVal(STOP_SEARCHING));
        stop.addActionListener(new StopSearchingEvent(this, true));
        searchingPanel.add(stop);
        AbsButton cancelSearching_ = getCompoFactory().newPlainButton(messages.getVal(CANCEL_SEARCHING));
        cancelSearching_.addActionListener(new StopSearchingEvent(this, false));
        searchingPanel.add(cancelSearching_);
        searchedFiles = getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(messages.getVal(FILE_COUNT), 0));
        searchingPanel.add(searchedFiles);
        foundFiles = getCompoFactory().newPlainLabel(StringUtil.simpleNumberFormat(messages.getVal(RESULT_COUNT), 0));
        searchingPanel.add(foundFiles);
        getAbsDialog().getPane().add(searchingPanel, GuiConstants.BORDER_LAYOUT_NORTH);
        pack();
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
        String fileName_ = getFileName().getText();
        String selectedRelPath_ = StringUtil.concat(getCurrentFolder(), fileName_);
        if (getProgramInfos().getFileCoreStream().newFile(selectedRelPath_).exists()) {
            closeWindow();
            setSelectedPath(selectedRelPath_);
            setSelectedAbsolutePath(selectedRelPath_);
            return;
        }
        String selectedPath_ = getSelectedAbsolutePath();
        String lg_ = frame.getLanguageKey();
        if (!selectedPath_.isEmpty()) {
            selectedPath_ = StringUtil.replaceBackSlash(selectedPath_);
            proc(selectedPath_, messages, lg_);
            return;
        }
        if (getFileTable().getSelectedRowCount() == 1) {
            selectedPath_ = getFileModel().getSelectedFilePath(getFileTable().getSelectedRow());
            proc(selectedPath_, messages, lg_);
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
            getProgramInfos().getMessageDialogAbs().input(getAbsDialog(), StringUtil.simpleStringsFormat(messages.getVal(ERROR_MESSAGE), selectedPath_), messages.getVal(ERROR_TITLE), lg_, GuiConstants.ERROR_MESSAGE);
            setSelectedPath("");
            return;
        }
        setSelectedAbsolutePath(selectedPath_);
        setSelectedPath(selectedPath_);
        closeWindow();
    }

    private void proc(String _selectedPath, StringMap<String> _messages, String _lg) {
        if (!getProgramInfos().getFileCoreStream().newFile(_selectedPath).exists()) {
            getProgramInfos().getMessageDialogAbs().input(getAbsDialog(), StringUtil.simpleStringsFormat(_messages.getVal(FileOpenDialog.ERROR_MESSAGE), _selectedPath), _messages.getVal(FileOpenDialog.ERROR_TITLE), _lg, GuiConstants.ERROR_MESSAGE);
            setSelectedPath("");
            setSelectedAbsolutePath("");
            return;
        }
        setSelectedPath(_selectedPath);
        setSelectedAbsolutePath(_selectedPath);
        closeWindow();
    }

    public void setInformations(long _s, long _f) {
        searchedFiles.setText(StringUtil.simpleNumberFormat(messages.getVal(FILE_COUNT), _s));
        foundFiles.setText(StringUtil.simpleNumberFormat(messages.getVal(RESULT_COUNT), _f));
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

    public static String getStaticSelectedPath(FileOpenDialog _dialog) {
        return _dialog.getSelectedPath();
    }

    public String typedString() {
        return typedString.getText();
    }

    public AbsTextField getTypedString() {
        return typedString;
    }

    public AbsButton getStop() {
        return stop;
    }

    protected void init(String _folder) {
        getFileModel().init(_folder);
    }

    protected CustList<AbstractFile> getFiles() {
        return getFileModel().getFiles();
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
