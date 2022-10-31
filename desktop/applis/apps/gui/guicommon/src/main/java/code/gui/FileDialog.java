package code.gui;





import code.gui.events.ClickHeaderEvent;
import code.gui.events.ClickRowEvent;
import code.gui.events.DeployTreeEvent;
import code.gui.images.AbstractImage;
import code.gui.images.MetaPoint;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.*;
import code.stream.comparators.FileNameComparator;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class FileDialog implements ChangeableTitle {
    private static final String DIALOG_ACCESS = "gui.filedialog";

    private static final String FILES_PARAM = "filesParam";
    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final String NAME = "name";
    private static final String FILES = "files";

    private static final int NB_COLS = 32;
    private AbsPanel buttons;
    private AbsTextField fileName;
    private AutoCompleteDocument auto;
    private AbsTreeGui folderSystem;
    private FileTable fileModel;
    private AbsTableGui fileTable;
    private boolean currentFolderRoot;
    private String selectedPath;
    private String selectedAbsolutePath;
    private String currentFolder;
    private String currentTitle;
    private String lang;
    private boolean addTypingFileName;
    private String extension = EMPTY_STRING;
    private String folder = EMPTY_STRING;
    private StringList excludedFolders = new StringList();

    private StringMap<String> messages;
    private final AbsDialog absDialog;
    private final AbstractProgramInfos programInfos;

    protected FileDialog(AbstractProgramInfos _frameFact){
        programInfos = _frameFact;
        fileName = _frameFact.getCompoFactory().newTextField(NB_COLS);
        buttons = _frameFact.getCompoFactory().newLineBox();
        absDialog = _frameFact.getFrameFactory().newDialog(new FileCloseableDialog(this));
    }

    @Override
    public String getTitle() {
        return absDialog.getTitle();
    }

    @Override
    public void setTitle(String _title) {
        absDialog.setTitle(_title);
    }

    @Override
    public MetaPoint getLocationOnScreen() {
        return absDialog.getLocationOnScreen();
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return absDialog.getImageIconFrame();
    }

    public AbsDialog getAbsDialog() {
        return absDialog;
    }

    @Override
    public boolean isVisible() {
        return absDialog.isVisible();
    }

    @Override
    public Ownable getOwner() {
        return absDialog.getOwner();
    }

    @Override
    public void setOwner(Ownable _owner) {
        absDialog.setOwner(_owner);
    }

    protected void setFileDialogByFrame(String _language, boolean _currentFolderRoot, String _extension, String _folder, AbsCommonFrame _c) {
        initByFrame(_language,_currentFolderRoot, true, _extension, _folder, _c);
    }

    protected void initByFrame(String _language, boolean _currentFolderRoot, boolean _addTypingFileName, String _extension, String _folder, AbsCommonFrame _commonFrame) {
        //super(_w,true);
        absDialog.setDialogIcon(programInfos.getImageFactory(), _commonFrame);
        absDialog.setModal(true);
        absDialog.setLocationRelativeTo(_commonFrame);
        extension = _extension;
        addTypingFileName = _addTypingFileName;
        folder = _folder;
        initDialog(_language, _currentFolderRoot);
    }

    protected void setFileDialog(AbsDialog _w, String _language, boolean _currentFolderRoot, String _extension, String _folder) {
        initByDialog(_w,_language,_currentFolderRoot, true, _extension, _folder);
    }

    protected void initByDialog(AbsDialog _w, String _language, boolean _currentFolderRoot, boolean _addTypingFileName, String _extension, String _folder) {
        //super(_w,true);
        absDialog.setDialogIcon(programInfos.getImageFactory(),_w);
        absDialog.setModal(true);
        absDialog.setLocationRelativeTo(_w);
        extension = _extension;
        addTypingFileName = _addTypingFileName;
        folder = _folder;
        initDialog(_language, _currentFolderRoot);
    }

    private void initDialog(String _language, boolean _currentFolderRoot) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, _language, DIALOG_ACCESS);
        String loadedResourcesMessages_ = MessGuiGr.ms().getVal(fileName_);
        messages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        lang = _language;
        currentFolderRoot = _currentFolderRoot;
        selectedPath = EMPTY_STRING;
        selectedAbsolutePath = EMPTY_STRING;
        excludedFolders = new StringList();
        if (currentFolderRoot) {
            String root_ = StringUtil.replaceBackSlash(programInfos.getFileCoreStream().newFile(folder).getAbsolutePath());
            currentFolder = StringUtil.concat(root_,StreamTextFile.SEPARATEUR);
            if (StringUtil.quickEq(currentFolder, StreamFolderFile.getCurrentPath(programInfos.getFileCoreStream()))) {
                for (String f: programInfos.getExcludedFolders()) {
                    excludedFolders.add(StringUtil.concat(currentFolder,f));
                }
            }
        }
        fileModel = new FileTable(_language,programInfos.getThreadFactory(),programInfos.getCompoFactory());
        currentTitle = messages.getVal(FILES);
        if (currentFolderRoot) {
            currentTitle = StringUtil.concat(currentTitle, SPACE, currentFolder);
        }
        setTitle(currentTitle);
        fileTable = fileModel.getTable();
        fileTable.setReorderingAllowed(false);
        fileTable.addHeaderListener(new ClickHeaderEvent(this));
        fileTable.setMultiSelect(false);
        fileTable.addListSelectionListener(new ClickRowEvent(this));
        AbsPanel openSaveFile_ = programInfos.getCompoFactory().newPageBox();
        fileName = programInfos.getCompoFactory().newTextField(NB_COLS);
        auto = new AutoCompleteDocument(fileName,new StringList(), programInfos);
        if (addTypingFileName) {
            AbsPanel fieldFile_ = programInfos.getCompoFactory().newLineBox();
            fieldFile_.add(programInfos.getCompoFactory().newPlainLabel(messages.getVal(NAME)));
            fieldFile_.add(fileName);
            openSaveFile_.add(fieldFile_);
        }
        buttons = programInfos.getCompoFactory().newLineBox();
        openSaveFile_.add(buttons);
        AbsPanel contentPane_ = programInfos.getCompoFactory().newBorder();
        contentPane_.add(openSaveFile_, GuiConstants.BORDER_LAYOUT_SOUTH);
        if (currentFolderRoot) {
            AbstractMutableTreeNode default_ = programInfos.getCompoFactory().newMutableTreeNode(currentFolder.substring(0, currentFolder.length() - 1));
            FileListInfo files_ = PathsUtil.abs(programInfos.getFileCoreStream().newFile(currentFolder),programInfos.getFileCoreStream());
            CustList<AbstractFile> currentFiles_ = new CustList<AbstractFile>(files_.getNames());
            currentFiles_.sortElts(new FileNameComparator());
            CustList<AbstractFile> filesList_ = new CustList<AbstractFile>();
            folderSystem = programInfos.getCompoFactory().newTreeGui(default_);
            refreshList(filesList_, currentFiles_);
            folderSystem.select(folderSystem.getRoot());
        } else {
            AbstractMutableTreeNode default_ = programInfos.getCompoFactory().newMutableTreeNode(EMPTY_STRING);
            for (String f: StreamFolderFile.listRootsAbPath(programInfos.getFileCoreStream())) {
                default_.add(StringUtil.join(StringUtil.splitStrings(f, StreamTextFile.SEPARATEUR), EMPTY_STRING));
            }
            folderSystem = programInfos.getCompoFactory().newTreeGui(default_);
            folderSystem.setRootVisible(false);
        }
        AbsSplitPane fileSelector_ = programInfos.getCompoFactory().newHorizontalSplitPane(programInfos.getCompoFactory().newAbsScrollPane(folderSystem.getTree()),programInfos.getCompoFactory().newAbsScrollPane(fileTable));
        folderSystem.addTreeSelectionListener(new DeployTreeEvent(this));
        contentPane_.add(fileSelector_, GuiConstants.BORDER_LAYOUT_CENTER);
        contentPane_.add(openSaveFile_, GuiConstants.BORDER_LAYOUT_SOUTH);
        absDialog.setContentPane(contentPane_);
    }

    public void refreshList(CustList<AbstractFile> _filesList) {
        StringList list_ = new StringList();
        for (AbstractFile f: _filesList) {
            list_.add(f.getName());
        }
        auto.setDictionary(list_);
        fileModel.setupFiles(_filesList, currentFolder, extension);
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
        currentTitle = StringUtil.simpleStringsFormat(messages.getVal(FILES_PARAM), currentFolder);
        setTitle(currentTitle);
        AbstractFile currentFolder_ = programInfos.getFileCoreStream().newFile(str_);
        if (!currentFolder_.exists()) {
            return;
        }
        CustList<AbstractFile> files_ = new CustList<AbstractFile>();
        FileListInfo filesArray_ = PathsUtil.abs(currentFolder_,programInfos.getFileCoreStream());
        CustList<AbstractFile> currentFiles_ = new CustList<AbstractFile>(filesArray_.getNames());
        currentFiles_.sortElts(new FileNameComparator());
        for (AbstractFile l: currentFiles_) {
            if (!l.isDirectory()&&l.getName().endsWith(extension)) {
                files_.add(l);
            }
        }
        MutableTreeNodeUtil.reload(folderSystem);
        refreshList(files_);
    }

    public void applyTreeChangeSelected() {
        AbstractMutableTreeNode sel_ = folderSystem.selectEvt();
        if (sel_ == null) {
            return;
        }
        StringBuilder str_ = buildPath(sel_);
        currentFolder = str_.toString();
        currentTitle = StringUtil.simpleStringsFormat(messages.getVal(FILES_PARAM), currentFolder);
        setTitle(currentTitle);
        AbstractFile currentFolder_ = programInfos.getFileCoreStream().newFile(str_.toString());
        if (!currentFolder_.exists()) {
            folderSystem.removeFromParent();
            return;
        }
        folderSystem.removeAllChildren();
        CustList<AbstractFile> files_ = new CustList<AbstractFile>();
        FileListInfo filesArray_ = PathsUtil.abs(currentFolder_,programInfos.getFileCoreStream());
        CustList<AbstractFile> currentFiles_ = new CustList<AbstractFile>(filesArray_.getNames());
        currentFiles_.sortElts(new FileNameComparator());
        refreshList(files_, currentFiles_);
        MutableTreeNodeUtil.reload(folderSystem);
    }

    private void refreshList(CustList<AbstractFile> _files, CustList<AbstractFile> _currentFiles) {
        for (AbstractFile f : _currentFiles) {
            if (f.isDirectory()) {
                if (StringUtil.contains(excludedFolders, StringUtil.replaceBackSlash(f.getAbsolutePath()))) {
                    continue;
                }
                folderSystem.add(f.getName());
            } else {
                if (f.getName().endsWith(extension)) {
                    _files.add(f);
                }
            }
        }
        refreshList(_files);
    }

    static StringBuilder buildPath(AbstractMutableTreeNode _treePath) {
        StringList pathFull_ = new StringList();
        AbstractMutableTreeNode current_ = _treePath;
        while (current_ != null) {
            pathFull_.add(0,current_.getUserObject());
            current_ = current_.getParent();
        }
        StringUtil.removeObj(pathFull_, EMPTY_STRING);
        StringBuilder str_ = new StringBuilder();
        for (String o: pathFull_) {
            str_.append(o).append(StreamTextFile.SEPARATEUR);
        }
        return str_;
    }

    @Override
    public void pack() {
        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
        absDialog.setVisible(true);
    }

    protected String getSelectedPath() {
        return selectedPath;
    }

    protected String getSelectedAbsolutePath() {
        return selectedAbsolutePath;
    }

    protected String getExtension() {
        return extension;
    }

    protected AbsPanel getButtons() {
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

    protected void setSelectedAbsolutePath(String _selectedAbsolutePath) {
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

    protected StringList getExcludedFolders() {
        return excludedFolders;
    }

    public AbsCompoFactory getCompoFactory() {
        return programInfos.getCompoFactory();
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

    public void closeWindow() {
        getAbsDialog().closeWindow();
    }
}
