package code.gui.files;

import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.Translations;
import code.stream.*;
import code.stream.comparators.FileNameComparator;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class FileDialogContent {

    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";

    private static final int NB_COLS = 32;
    private AbsPanel buttons;
    private AbsTextField fileName;
    private AutoCompleteDocument auto;
    private AbsTreeGui folderSystem;
    private FileTable fileModel;
    private AbsTableGui fileTable;
    private boolean currentFolderRoot;
    private String selectedPath;
    private String selectedAbsolutePath = EMPTY_STRING;
    private String currentFolder = EMPTY_STRING;
    private String currentTitle;
    private String lang;
    private boolean addTypingFileName;
    private String folder = EMPTY_STRING;
    private AbsPanel contentPane;
    private AbsTextArea errors;

    private final AbstractProgramInfos programInfos;
    private AbsPostFileDialogEvent postFileDialogEvent;

    protected FileDialogContent(AbstractProgramInfos _frameFact){
        programInfos = _frameFact;
        fileName = _frameFact.getCompoFactory().newTextField(NB_COLS);
        buttons = _frameFact.getCompoFactory().newLineBox();
        errors = _frameFact.getCompoFactory().newTextArea();
    }
    protected void setFileDialogByFrame(String _language, boolean _currentFolderRoot, String _folder, AbsPostFileDialogEvent _post) {
        initByFrame(_language,_currentFolderRoot, true, _folder, _post);
    }

    protected void initByFrame(String _language, boolean _currentFolderRoot, boolean _addTypingFileName, String _folder, AbsPostFileDialogEvent _post) {
        //super(_w,true);
        addTypingFileName = _addTypingFileName;
        folder = _folder;
        initDialog(_language, _currentFolderRoot,_post);
    }

    protected void setFileDialog(String _language, boolean _currentFolderRoot, String _folder, AbsPostFileDialogEvent _post) {
        initByDialog(_language,_currentFolderRoot, _folder,_post);
    }

    protected void initByDialog(String _language, boolean _currentFolderRoot, String _folder, AbsPostFileDialogEvent _post) {
        //super(_w,true);
        addTypingFileName = true;
        folder = _folder;
        initDialog(_language, _currentFolderRoot,_post);
    }

    private void initDialog(String _language, boolean _currentFolderRoot,AbsPostFileDialogEvent _post) {
        postFileDialogEvent = _post;
        lang = _language;
        currentFolderRoot = _currentFolderRoot;
        selectedPath = EMPTY_STRING;
        selectedAbsolutePath = EMPTY_STRING;
        if (currentFolderRoot) {
            String root_ = StringUtil.replaceBackSlash(programInfos.getFileCoreStream().newFile(folder).getAbsolutePath());
            currentFolder = StringUtil.concat(root_,StreamTextFile.SEPARATEUR);
        }
        Translations trs_ = programInfos.getTranslations();
        fileModel = new FileTable(_language,trs_,programInfos.getThreadFactory(),programInfos.getCompoFactory());
        StringMap<String> appTr_ = FileDialog.getAppliTr(trs_.getMapping().getVal(_language)).getMapping().getVal(FileDialog.FILE_DIAL).getMapping();
        currentTitle = appTr_.getVal(MessagesFileDialog.FILES);
        if (currentFolderRoot) {
            currentTitle = StringUtil.concat(currentTitle, SPACE, currentFolder);
        }
        postFileDialogEvent.title(currentTitle);
        fileTable = fileModel.getTable();
        fileTable.setReorderingAllowed(false);
        fileTable.addHeaderListener(new ClickHeaderEvent(this));
        fileTable.setMultiSelect(false);
        fileTable.addListSelectionListener(new ClickRowEvent(this));
        AbsPanel openSaveFile_ = programInfos.getCompoFactory().newPageBox();
        fileName = programInfos.getCompoFactory().newTextField(NB_COLS);
        auto = new AutoCompleteDocument(fileName,new StringList(), programInfos,new SubmitKeyEvent(this, fileName));
        if (addTypingFileName) {
            AbsPanel fieldFile_ = programInfos.getCompoFactory().newLineBox();
            fieldFile_.add(programInfos.getCompoFactory().newPlainLabel(appTr_.getVal(MessagesFileDialog.NAME)));
            fieldFile_.add(fileName);
            openSaveFile_.add(fieldFile_);
        }
        buttons = programInfos.getCompoFactory().newLineBox();
        openSaveFile_.add(buttons);
        errors = programInfos.getCompoFactory().newTextArea(1,32);
        AbsPanel contentPane_ = programInfos.getCompoFactory().newBorder();
        if (currentFolderRoot) {
            AbstractMutableTreeNodeCore<String> default_ = programInfos.getCompoFactory().newMutableTreeNode(StringUtil.replaceBackSlashDot(currentFolder));
            CustList<AbstractFile> currentFiles_ = sorted(programInfos.getFileCoreStream().newFile(currentFolder));
            CustList<AbstractFile> filesList_ = new CustList<AbstractFile>();
            folderSystem = programInfos.getCompoFactory().newTreeGui(default_);
            folderSystem.select(folderSystem.getRoot());
            refreshList(folderSystem.selectEvt(),filesList_, currentFiles_);
        } else {
            AbstractMutableTreeNodeCore<String> default_ = programInfos.getCompoFactory().newMutableTreeNode(EMPTY_STRING);
            for (String f: StreamFolderFile.listRootsAbPath(programInfos.getFileCoreStream())) {
                default_.add(getCompoFactory().newMutableTreeNode(f));
            }
            folderSystem = programInfos.getCompoFactory().newTreeGui(default_);
            folderSystem.setRootVisible(false);
        }
        AbsSplitPane fileSelector_ = programInfos.getCompoFactory().newHorizontalSplitPane(programInfos.getCompoFactory().newAbsScrollPane(folderSystem),programInfos.getCompoFactory().newAbsScrollPane(fileTable));
        folderSystem.addTreeSelectionListener(new DeployTreeEvent(this));
        contentPane_.add(fileSelector_, GuiConstants.BORDER_LAYOUT_CENTER);
        contentPane_.add(openSaveFile_, GuiConstants.BORDER_LAYOUT_SOUTH);
        contentPane_.add(programInfos.getCompoFactory().newAbsScrollPane(errors), GuiConstants.BORDER_LAYOUT_EAST);
        contentPane = contentPane_;
    }

    public abstract void submitIfVisible();
    public AbsTextArea getErrors() {
        return errors;
    }

    public AbsPanel getContentPane() {
        return contentPane;
    }


    public AbsPostFileDialogEvent getPostFileDialogEvent() {
        return postFileDialogEvent;
    }

    public void refreshList(CustList<AbstractFile> _filesList) {
        StringList list_ = new StringList();
        for (AbstractFile f: _filesList) {
            list_.add(f.getName());
        }
        getAuto().setDictionary(list_);
        fileModel.setupFiles(_filesList, currentFolder);
    }

    public AutoCompleteDocument getAuto() {
        return auto;
    }

    public void clickHeader(AbsMouseLocation _e) {
        int col_ = fileTable.columnAtPoint(_e.getXcoord(),_e.getYcoord());
        fileModel.setSorting(col_);
    }

    public void clickRow() {
        // do some actions here, for example
        // print first column value from selected row
        int index_ = fileTable.getSelectedRow();
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        selectedPath = fileTable.getValueAt(index_, FileTable.PATH_INDEX);
        selectedAbsolutePath = fileModel.getSelectedFilePath(index_);
        if (addTypingFileName) {
            fileName.setText(fileTable.getValueAt(index_, FileTable.NAME_INDEX));
        }
    }

    public void applyTreeChange() {
        String str_ = getFolder();
        currentFolder = str_;
        StringMap<String> appTr_ = FileDialog.getAppliTr(programInfos.getTranslations().getMapping().getVal(lang)).getMapping().getVal(FileDialog.FILE_DIAL).getMapping();
        currentTitle = StringUtil.simpleStringsFormat(appTr_.getVal(MessagesFileDialog.FILES_PARAM), currentFolder);
        postFileDialogEvent.title(currentTitle);
        AbstractFile currentFolder_ = programInfos.getFileCoreStream().newFile(str_);
//        if (!currentFolder_.exists()) {
//            return;
//        }
        CustList<AbstractFile> files_ = new CustList<AbstractFile>();
        CustList<AbstractFile> currentFiles_ = sorted(currentFolder_);
        for (AbstractFile l: currentFiles_) {
            if (!l.isDirectory()) {
                files_.add(l);
            }
        }
        MutableTreeNodeUtil.reload(folderSystem);
        refreshList(files_);
    }

    private CustList<AbstractFile> sorted(AbstractFile _currentFolder) {
        FileListInfo filesArray_ = PathsUtil.abs(_currentFolder, programInfos.getFileCoreStream());
        CustList<AbstractFile> currentFiles_ = new CustList<AbstractFile>(filesArray_.getNames());
        currentFiles_.sortElts(new FileNameComparator());
        return currentFiles_;
    }

    public void applyTreeChangeSelected() {
        AbstractMutableTreeNodeCore<String> sel_ = folderSystem.selectEvt();
        if (sel_ == null) {
            return;
        }
        String str_ = buildPath(sel_);
        currentFolder = str_;
        StringMap<String> appTr_ = FileDialog.getAppliTr(programInfos.getTranslations().getMapping().getVal(lang)).getMapping().getVal(FileDialog.FILE_DIAL).getMapping();
        currentTitle = StringUtil.simpleStringsFormat(appTr_.getVal(MessagesFileDialog.FILES_PARAM), currentFolder);
        postFileDialogEvent.title(currentTitle);
        AbstractFile currentFolder_ = programInfos.getFileCoreStream().newFile(str_);
        if (!currentFolder_.exists()) {
            sel_.removeFromParent();
            return;
        }
        sel_.removeAllChildren();
        CustList<AbstractFile> files_ = new CustList<AbstractFile>();
        CustList<AbstractFile> currentFiles_ = sorted(currentFolder_);
        refreshList(sel_,files_, currentFiles_);
        MutableTreeNodeUtil.reload(folderSystem);
    }

    private void refreshList(AbstractMutableTreeNodeCore<String> _sel,CustList<AbstractFile> _files, CustList<AbstractFile> _currentFiles) {
        for (AbstractFile f : _currentFiles) {
            if (f.isDirectory()) {
                _sel.add(getCompoFactory().newMutableTreeNode(f.getName()+"/"));
            } else {
                _files.add(f);
            }
        }
        refreshList(_files);
    }

    static String buildPath(AbstractMutableTreeNodeCore<String> _treePath) {
        return GuiBaseUtil.buildPath(_treePath,"");
    }

    public String getSelectedPath() {
        return selectedPath;
    }

    protected String getSelectedAbsolutePath() {
        return selectedAbsolutePath;
    }

    public AbsPanel getButtons() {
        return buttons;
    }

    protected AbsTextField getFileName() {
        return fileName;
    }

    protected AbsTreeGui getFolderSystem() {
        return folderSystem;
    }

    protected FileTable getFileModel() {
        return fileModel;
    }

    protected AbsTableGui getFileTable() {
        return fileTable;
    }

    protected boolean isCurrentFolderRoot() {
        return currentFolderRoot;
    }

    public void setSelectedPath(String _selectedPath) {
        selectedPath = _selectedPath;
    }

    public void setSelectedAbsolutePath(String _selectedAbsolutePath) {
        selectedAbsolutePath = _selectedAbsolutePath;
    }

    protected void setCurrentFolder(String _currentFolder) {
        currentFolder = _currentFolder;
    }

    protected String getCurrentFolder() {
        return currentFolder;
    }

    protected void setCurrentTitle(String _currentTitle) {
        currentTitle = _currentTitle;
    }

    protected String getCurrentTitle() {
        return currentTitle;
    }

    protected String getLang() {
        return lang;
    }

    protected String getFolder() {
        return folder;
    }

    public AbsCompoFactory getCompoFactory() {
        return programInfos.getCompoFactory();
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

}
