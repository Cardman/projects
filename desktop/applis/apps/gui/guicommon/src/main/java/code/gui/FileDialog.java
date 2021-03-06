package code.gui;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import code.gui.events.ClickHeaderEvent;
import code.gui.events.ClickRowEvent;
import code.gui.events.DeployTreeEvent;
import code.gui.initialize.AbstractGraphicStringListGenerator;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.comparators.FileNameComparator;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class FileDialog extends Dialog {
    private static final String DIALOG_ACCESS = "gui.filedialog";

    private static final String FILES_PARAM = "filesParam";
    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final String NAME = "name";
    private static final String FILES = "files";

    private static final int NB_COLS = 32;
    private Panel buttons = Panel.newLineBox();
    private TextField fileName = new TextField(NB_COLS);
    private AutoCompleteDocument auto;
    private TreeGui folderSystem;
    private FileTable fileModel;
    private TableGui fileTable;
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

    private GroupFrame superFrame;
    private StringMap<String> messages;

    protected FileDialog(){
    }
    protected void setFileDialogByFrame(GroupFrame _w,String _language, boolean _currentFolderRoot, String _extension, String _folder, String... _excludedFolders) {
        initByFrame(_w,_language,_currentFolderRoot, true, _extension, _folder, _excludedFolders);
    }

    protected void initByFrame(GroupFrame _w,String _language, boolean _currentFolderRoot, boolean _addTypingFileName, String _extension, String _folder, String... _excludedFolders) {
        //super(_w,true);
        setDialogIcon(_w);
        setModal(true);
        setLocationRelativeTo(_w);
        extension = _extension;
        addTypingFileName = _addTypingFileName;
        folder = _folder;
        superFrame = _w;
        initDialog(_language, _currentFolderRoot, _excludedFolders);
    }

    protected void setFileDialog(GroupFrame _c,Dialog _w,String _language, boolean _currentFolderRoot, String _extension, String _folder, String... _excludedFolders) {
        initByDialog(_c, _w,_language,_currentFolderRoot, true, _extension, _folder, _excludedFolders);
    }

    protected void initByDialog(GroupFrame _c,Dialog _w,String _language, boolean _currentFolderRoot, boolean _addTypingFileName, String _extension, String _folder, String... _excludedFolders) {
        //super(_w,true);
        setDialogIcon(_w);
        setModal(true);
        setLocationRelativeTo(_w);
        superFrame = _c;
        extension = _extension;
        addTypingFileName = _addTypingFileName;
        folder = _folder;
        initDialog(_language, _currentFolderRoot, _excludedFolders);
    }

    private void initDialog(String _language, boolean _currentFolderRoot, String... _excludedFolders) {
        String lg_ = superFrame.getLanguageKey();
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, lg_, DIALOG_ACCESS);
        String loadedResourcesMessages_ = MessGuiGr.ms().getVal(fileName_);
        messages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        lang = _language;
        currentFolderRoot = _currentFolderRoot;
        selectedPath = EMPTY_STRING;
        selectedAbsolutePath = EMPTY_STRING;
        excludedFolders = new StringList();
        if (currentFolderRoot) {
            String root_ = StringUtil.replaceBackSlash(new File(folder).getAbsolutePath());
            currentFolder = StringUtil.concat(root_,StreamTextFile.SEPARATEUR);
            if (StringUtil.quickEq(currentFolder, StreamFolderFile.getCurrentPath())) {
                for (String f: _excludedFolders) {
                    excludedFolders.add(StringUtil.concat(currentFolder,f));
                }
            }
        }
        fileModel = new FileTable(lg_);
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
        Panel openSaveFile_ = Panel.newPageBox();
        fileName = new TextField(NB_COLS);
        AbstractGraphicStringListGenerator gene_ = superFrame.getFrames().getGeneGraphicList();
        auto = new AutoCompleteDocument(fileName,new StringList(), this,gene_);
        if (addTypingFileName) {
            Panel fieldFile_ = Panel.newLineBox();
            fieldFile_.add(new TextLabel(messages.getVal(NAME)));
            fieldFile_.add(fileName);
            openSaveFile_.add(fieldFile_);
        }
        buttons = Panel.newLineBox();
        openSaveFile_.add(buttons);
        Panel contentPane_ = Panel.newBorder();
        contentPane_.add(openSaveFile_, BorderLayout.SOUTH);
        if (currentFolderRoot) {
            DefaultMutableTreeNode default_ = new DefaultMutableTreeNode(currentFolder.substring(0, currentFolder.length() - 1));
            File[] files_ = new File(currentFolder).listFiles();
            if (files_ != null) {
                CustList<File> currentFiles_ = new CustList<File>(files_);
                currentFiles_.sortElts(new FileNameComparator());
                CustList<File> filesList_ = new CustList<File>();
                retrieve(default_, filesList_, currentFiles_);
                refreshList(filesList_);
            }
            folderSystem = new TreeGui(default_);
        } else {
            DefaultMutableTreeNode default_ = new DefaultMutableTreeNode(EMPTY_STRING);
            for (String f: StreamFolderFile.listRootsAbPath()) {
                default_.add(new DefaultMutableTreeNode(StringUtil.join(StringUtil.splitStrings(f, StreamTextFile.SEPARATEUR), EMPTY_STRING)));
            }
            folderSystem = new TreeGui(default_);
            folderSystem.setRootVisible(false);
        }
        SplitPane fileSelector_ = new SplitPane(JSplitPane.HORIZONTAL_SPLIT,new ScrollPane(folderSystem),new ScrollPane(fileTable));
        folderSystem.addTreeSelectionListener(new DeployTreeEvent(this));
        contentPane_.add(fileSelector_, BorderLayout.CENTER);
        contentPane_.add(openSaveFile_, BorderLayout.SOUTH);
        setContentPane(contentPane_);
    }

    public void refreshList(CustList<File> _filesList) {
        StringList list_ = new StringList();
        for (File f: _filesList) {
            list_.add(f.getName());
        }
        auto.setDictionary(list_);
        fileModel.setupFiles(_filesList, currentFolder, extension);
    }

    public void clickHeader(MouseEvent _e) {
        int col_ = fileTable.columnAtPoint(_e.getX(),_e.getY());
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
        File currentFolder_ = new File(str_);
        if (!currentFolder_.exists()) {
            return;
        }
        CustList<File> files_ = new CustList<File>();
        File[] filesArray_ = currentFolder_.listFiles();
        if (filesArray_ != null) {
            CustList<File> currentFiles_ = new CustList<File>(filesArray_);
            currentFiles_.sortElts(new FileNameComparator());
            for (File l: currentFiles_) {
                if (!l.isDirectory()&&l.getName().endsWith(extension)) {
                    files_.add(l);
                }
            }
        }
        folderSystem.reload();
        refreshList(files_);
    }

    public void applyTreeChangeSelected() {
        Object sel_;
        sel_ =folderSystem.getLastSelectedPathComponent();
        if (!(sel_ instanceof DefaultMutableTreeNode)) {
            return;
        }
        DefaultMutableTreeNode selected_ = (DefaultMutableTreeNode) sel_;
        StringBuilder str_ = buildPath(selected_);
        currentFolder = str_.toString();
        currentTitle = StringUtil.simpleStringsFormat(messages.getVal(FILES_PARAM), currentFolder);
        setTitle(currentTitle);
        File currentFolder_ = new File(str_.toString());
        if (!currentFolder_.exists()) {
            selected_.removeFromParent();
            return;
        }
        selected_.removeAllChildren();
        CustList<File> files_ = new CustList<File>();
        File[] filesArray_ = currentFolder_.listFiles();
        if (filesArray_ != null) {
            CustList<File> currentFiles_ = new CustList<File>(filesArray_);
            currentFiles_.sortElts(new FileNameComparator());
            retrieve(selected_, files_, currentFiles_);
        }
        folderSystem.reload();
        refreshList(files_);
    }

    private void retrieve(DefaultMutableTreeNode _directoryNode, CustList<File> _files, CustList<File> _currentFiles) {
        for (File f : _currentFiles) {
            if (f.isDirectory()) {
                if (StringUtil.contains(excludedFolders, StringUtil.replaceBackSlash(f.getAbsolutePath()))) {
                    continue;
                }
                _directoryNode.add(new DefaultMutableTreeNode(f.getName()));
            } else {
                if (f.getName().endsWith(extension)) {
                    _files.add(f);
                }
            }
        }
    }

    static StringBuilder buildPath(DefaultMutableTreeNode _treePath) {
        StringList pathFull_ = new StringList();
        TreeNode current_ = _treePath;
        while (current_ != null) {
            pathFull_.add(0,(String)((DefaultMutableTreeNode)current_).getUserObject());
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
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        super.pack();
        setVisible(true);
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

    protected Panel getButtons() {
        return buttons;
    }

    protected TextField getFileName() {
        return fileName;
    }

    protected TreeGui getFolderSystem() {
        return folderSystem;
    }

    protected FileTable getFileModel() {
        return fileModel;
    }

    protected TableGui getFileTable() {
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
}
