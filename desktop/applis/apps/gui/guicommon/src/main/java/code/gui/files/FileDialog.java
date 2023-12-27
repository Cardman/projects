package code.gui.files;





import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.images.MetaPoint;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.stream.DocumentReaderGuiUtil;
import code.gui.stream.DocumentWriterGuiUtil;
import code.images.BaseSixtyFourUtil;
import code.images.IntPoint;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.*;
import code.stream.comparators.FileNameComparator;
import code.stream.core.TechStreams;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class FileDialog implements ChangeableTitle,SingleFileSelection {
    private static final String DIALOG_ACCESS = "gui.filedialog";

    private static final String FILES_PARAM = "filesParam";
    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final String NAME = "name";
    private static final String FILES = "files";

    private static final int NB_COLS = 32;
    private static final int MIN_BORDER = 50;
    private static final String LANGUAGE_TXT = "langue.txt";
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

    public static AbstractImage getImage(String _icon, AbstractImageFactory _fact) {
        int[][] file_ = BaseSixtyFourUtil.getImageByString(_icon);
        return ConverterGraphicBufferedImage.decodeToImage(_fact,file_);
    }

    public static void setLocation(AbsCommonFrame _frame, TopLeftFrame _topLeft) {
        setLocation(_frame, _topLeft.getWidth(), _topLeft.getHeight());
    }

    private static void setLocation(AbsCommonFrame _frame, int _x, int _y) {
        int x_ = _x;
        int y_ = _y;
        IntPoint dims_ = getScreenSize(_frame.getFrames());
        if (x_ + MIN_BORDER > dims_.getXcoords()) {
            x_ = 0;
        }
        if (y_ + MIN_BORDER > dims_.getYcoords()) {
            y_ = 0;
        }
        if (x_ < 0) {
            x_ = 0;
        }
        if (y_ < 0) {
            y_ = 0;
        }
        _frame.setLocation(x_, y_);
    }

    private static IntPoint getScreenSize(AbstractProgramInfos _info) {
        int width_ = _info.getScreenWidth();
        int height_ = _info.getScreenHeight();
        return new IntPoint(width_, height_);
    }

    public static TopLeftFrame loadCoords(String _folder, String _file, AbstractFileCoreStream _fact, TechStreams _tech) {
//        return (TopLeftFrame) StreamTextFile.deserialiser(getFolderJarPath()+_file);
        return DocumentReaderGuiUtil.getTopLeftFrame(StreamTextFile.contentsOfFile(StringUtil.concat(_folder,StreamTextFile.SEPARATEUR,_file),_fact,_tech));
    }

    public static void saveCoords(String _folder, String _file, int _x, int _y, TechStreams _str) {
        TopLeftFrame topLeft_ = new TopLeftFrame();
        topLeft_.setWidth(_x);
        topLeft_.setHeight(_y);
//        StreamTextFile.save(getFolderJarPath()+_file, topLeft_);
        StreamTextFile.saveTextFile(StringUtil.concat(_folder,StreamTextFile.SEPARATEUR,_file), DocumentWriterGuiUtil.setTopLeftFrame(topLeft_),_str);
    }

    /**@throws LangueException*/
    public static String loadLanguage(String _dir, AbstractFileCoreStream _fact, TechStreams _tech, StringList _lgs) {
//        Node noeud_ = StreamTextFile.contenuDocumentXmlExterne(getFolderJarPath()+LANGUAGE);
        String language_ = StreamLanguageUtil.tryToGetXmlLanguage(_dir,_fact,_tech, _lgs);
        if (!language_.isEmpty()) {
            return language_;
        }
//        String content_ = StreamTextFile.contentsOfFile(ConstFiles.getFolderJarPath()+LANGUAGE_TXT);
        String content_ = StreamTextFile.contentsOfFile(StringUtil.concat(StreamFolderFile.getCurrentPath(_fact),LANGUAGE_TXT),_fact,_tech);
        if (content_ == null) {
            return EMPTY_STRING;
        }
        content_ = content_.trim();
        boolean valide_ = false;
        for (String l: _lgs) {
            if (StringUtil.quickEq(content_,l)) {
                valide_ = true;
            }
        }
        if(!valide_) {
            return EMPTY_STRING;
        }
        return content_;
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

    protected void setFileDialogByFrame(String _language, boolean _currentFolderRoot, String _folder, AbsCommonFrame _c) {
        initByFrame(_language,_currentFolderRoot, true, _folder, _c);
    }

    protected void initByFrame(String _language, boolean _currentFolderRoot, boolean _addTypingFileName, String _folder, AbsCommonFrame _commonFrame) {
        //super(_w,true);
        absDialog.setDialogIcon(programInfos.getImageFactory(), _commonFrame);
        absDialog.setModal(true);
        absDialog.setLocationRelativeTo(_commonFrame);
        addTypingFileName = _addTypingFileName;
        folder = _folder;
        initDialog(_language, _currentFolderRoot);
    }

    protected void setFileDialog(AbsDialog _w, String _language, boolean _currentFolderRoot, String _folder) {
        initByDialog(_w,_language,_currentFolderRoot, true, _folder);
    }

    protected void initByDialog(AbsDialog _w, String _language, boolean _currentFolderRoot, boolean _addTypingFileName, String _folder) {
        //super(_w,true);
        absDialog.setDialogIcon(programInfos.getImageFactory(),_w);
        absDialog.setModal(true);
        absDialog.setLocationRelativeTo(_w);
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
        auto = new AutoCompleteDocument(fileName,new StringList(), programInfos,new SubmitKeyEvent(this, fileName));
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
            AbstractMutableTreeNodeCore<String> default_ = programInfos.getCompoFactory().newMutableTreeNode(currentFolder.substring(0, currentFolder.length() - 1));
            FileListInfo files_ = PathsUtil.abs(programInfos.getFileCoreStream().newFile(currentFolder),programInfos.getFileCoreStream());
            CustList<AbstractFile> currentFiles_ = new CustList<AbstractFile>(files_.getNames());
            currentFiles_.sortElts(new FileNameComparator());
            CustList<AbstractFile> filesList_ = new CustList<AbstractFile>();
            folderSystem = programInfos.getCompoFactory().newTreeGui(default_);
            folderSystem.select(folderSystem.getRoot());
            refreshList(folderSystem.selectEvt(),filesList_, currentFiles_);
        } else {
            AbstractMutableTreeNodeCore<String> default_ = programInfos.getCompoFactory().newMutableTreeNode(EMPTY_STRING);
            for (String f: StreamFolderFile.listRootsAbPath(programInfos.getFileCoreStream())) {
                default_.add(getCompoFactory().newMutableTreeNode(StringUtil.join(StringUtil.splitStrings(f, StreamTextFile.SEPARATEUR), EMPTY_STRING)));
            }
            folderSystem = programInfos.getCompoFactory().newTreeGui(default_);
            folderSystem.setRootVisible(false);
        }
        AbsSplitPane fileSelector_ = programInfos.getCompoFactory().newHorizontalSplitPane(programInfos.getCompoFactory().newAbsScrollPane(folderSystem),programInfos.getCompoFactory().newAbsScrollPane(fileTable));
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
        fileModel.setupFiles(_filesList, currentFolder);
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
            if (!l.isDirectory()) {
                files_.add(l);
            }
        }
        MutableTreeNodeUtil.reload(folderSystem);
        refreshList(files_);
    }

    public void applyTreeChangeSelected() {
        AbstractMutableTreeNodeCore<String> sel_ = folderSystem.selectEvt();
        if (sel_ == null) {
            return;
        }
        StringBuilder str_ = buildPath(sel_);
        currentFolder = str_.toString();
        currentTitle = StringUtil.simpleStringsFormat(messages.getVal(FILES_PARAM), currentFolder);
        setTitle(currentTitle);
        AbstractFile currentFolder_ = programInfos.getFileCoreStream().newFile(str_.toString());
        if (!currentFolder_.exists()) {
            sel_.removeFromParent();
            return;
        }
        sel_.removeAllChildren();
        CustList<AbstractFile> files_ = new CustList<AbstractFile>();
        FileListInfo filesArray_ = PathsUtil.abs(currentFolder_,programInfos.getFileCoreStream());
        CustList<AbstractFile> currentFiles_ = new CustList<AbstractFile>(filesArray_.getNames());
        currentFiles_.sortElts(new FileNameComparator());
        refreshList(sel_,files_, currentFiles_);
        MutableTreeNodeUtil.reload(folderSystem);
    }

    private void refreshList(AbstractMutableTreeNodeCore<String> _sel,CustList<AbstractFile> _files, CustList<AbstractFile> _currentFiles) {
        for (AbstractFile f : _currentFiles) {
            if (f.isDirectory()) {
                if (StringUtil.contains(excludedFolders, StringUtil.replaceBackSlash(f.getAbsolutePath()))) {
                    continue;
                }
                _sel.add(getCompoFactory().newMutableTreeNode(f.getName()));
            } else {
                _files.add(f);
            }
        }
        refreshList(_files);
    }

    static StringBuilder buildPath(AbstractMutableTreeNodeCore<String> _treePath) {
        StringList pathFull_ = new StringList();
        AbstractMutableTreeNodeCore<String> current_ = _treePath;
        while (current_ != null) {
            pathFull_.add(0,current_.info());
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
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
        absDialog.setVisible(true);
    }

    protected String getSelectedPath() {
        return selectedPath;
    }

    protected String getSelectedAbsolutePath() {
        return selectedAbsolutePath;
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
