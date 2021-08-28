package code.gui;

import code.gui.initialize.AbsCompoFactory;
import code.scripts.messages.gui.MessGuiGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.AbstractFile;
import code.threads.AbstractDate;
import code.threads.AbstractDateFactory;
import code.threads.AbstractThreadFactory;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class FileTable {

    static final int NAME_INDEX = 0;

    static final int DATE_INDEX = 1;

    static final int SIZE_INDEX = 2;

    static final int PATH_INDEX = 3;
    private static final String ACCESS = "gui.filetable";

    private static final String END_NUM = " 98";

    private static final String BEGIN_NUM = " 01";

    private static final String END_ALPH = " ZY";

    private static final String BEGIN_ALPH = " AB";

    private static final String PATH = "path";

    private static final String SIZE = "size";

    private static final String NAME = "name";

    private static final String DATE_FORMAT = "dd-MM-yyyy HH-mm-ss";

    private static final String MODIFIED = "modified";

    private static final String EMPTY_STRING = "";

    //    private static final String END_REG_EXP = "$";

    private static final int NB_COLS = 4;

    private final StringMap<String> messages;

    private final CustList<AbstractFile> files = new CustList<AbstractFile>();

    private String extension;

    private String folder;

    private int indexOfSorted = IndexConstants.INDEX_NOT_FOUND_ELT;

    private boolean increasing;

    private final AbsTableGui table;
    private final AbstractThreadFactory threadFactory;

    public FileTable(String _lg, AbstractThreadFactory _threadFactory, AbsCompoFactory _compoFactory) {
        threadFactory = _threadFactory;
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(GuiConstants.FOLDER_MESSAGES_GUI, _lg, ACCESS);
        String loadedResourcesMessages_ = MessGuiGr.ms().getVal(fileName_);
        messages = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        String[] cols_ = new String[NB_COLS];
        for (int i = 0; i < NB_COLS; i++) {
            cols_[i] = getColumnName(i);
        }
        table = _compoFactory.newTableGui(cols_);
    }

    public int getRowCount() {
        return files.size();
    }

    public static int getColumnCount() {
        return NB_COLS;
    }

    public void setSorting(int _col) {
        indexOfSorted = _col;
        increasing = !increasing;
        files.sortElts(new FileComparator(increasing, indexOfSorted, folder));
        int s_ = files.size();
        for (int j = 0; j < s_; j++) {
            for (int i = 0; i < NB_COLS; i++) {
                table.setValueAt(getValueAt(j, i), j, i);
            }
        }
        String[] cols_ = new String[NB_COLS];
        for (int i = 0; i < NB_COLS; i++) {
            cols_[i] = getColumnName(i);
        }
        table.setColumnIdentifiers(cols_);
        applyChanges();
    }

    public String getColumnName(int _columnIndex) {
        String end_ = EMPTY_STRING;
        if (_columnIndex == indexOfSorted) {
            if (_columnIndex == NAME_INDEX || _columnIndex == PATH_INDEX) {
                if (increasing) {
                    end_ = BEGIN_ALPH;
                } else {
                    end_ = END_ALPH;
                }
            } else {
                if (increasing) {
                    end_ = BEGIN_NUM;
                } else {
                    end_ = END_NUM;
                }
            }
        }
        String head_ = EMPTY_STRING;
        if(_columnIndex == NAME_INDEX) {
            head_ = messages.getVal(NAME);
        } else if(_columnIndex == DATE_INDEX) {
            head_ = messages.getVal(MODIFIED);
        } else if(_columnIndex == SIZE_INDEX) {
            head_ = messages.getVal(SIZE);
        } else if(_columnIndex == PATH_INDEX) {
            head_ = messages.getVal(PATH);
        }
        return StringUtil.concat(head_,end_);
    }

    public String getValueAt(int _rowIndex, int _columnIndex) {
        AbstractFile currentFile_;
        currentFile_ = files.get(_rowIndex);
        if(_columnIndex == NAME_INDEX) {
            if (!extension.isEmpty()) {
                return StringUtil.replaceEnd(currentFile_.getName(), extension);
            }
            return currentFile_.getName();
        } else if(_columnIndex == DATE_INDEX) {
            AbstractDateFactory dateFactory_ = threadFactory.getDateFactory();
            AbstractDate date_ = dateFactory_.newDate(currentFile_.lastModified());
            return date_.format(dateFactory_,DATE_FORMAT);
        } else if(_columnIndex == SIZE_INDEX) {
            return Long.toString(currentFile_.length());
        } else if(_columnIndex == PATH_INDEX) {
            String return_ = currentFile_.getAbsolutePath();
            return_ = return_.substring(folder.length());
            return_ = StringUtil.replaceBackSlash(return_);
            return return_;
        }
        return "";
    }

    public void setupFiles(CustList<AbstractFile> _list,String _folder, String _extension) {
        indexOfSorted = IndexConstants.INDEX_NOT_FOUND_ELT;
        extension = _extension;
        increasing = false;
        files.clear();
        files.addAllElts(_list);
        int len_ = _list.size();
        table.setRowCount(len_);
        folder = _folder;
        for (int j = 0; j <len_; j++) {
            int cols_ = getColumnCount();
            for (int i = 0; i < cols_; i++) {
                table.setValueAt(getValueAt(j, i), j, i);
            }
        }
        applyChanges();
    }

    public void setupFile(AbstractFile _file) {
        files.add(_file);
        table.setRowCount(files.size());
        int cols_ = getColumnCount();
        for (int i = 0; i < cols_; i++) {
            table.setValueAt(getValueAt(files.size() - 1, i), files.size() - 1, i);
        }
        applyChanges();
    }

    public void init(String _folder, String _extension) {
        indexOfSorted = IndexConstants.INDEX_NOT_FOUND_ELT;
        extension = _extension;
        increasing = false;
        folder = _folder;
    }

    public void clear() {
        int len_ = files.size();
        for (int j = 0; j <len_; j++) {
            int cols_ = getColumnCount();
            for (int i = 0; i < cols_; i++) {
                table.setValueAt("", j, i);
            }
        }
        files.clear();
        table.setRowCount(files.size());
        applyChanges();
    }
    public void applyChanges() {
        table.applyChanges();
    }

    public CustList<AbstractFile> getFiles() {
        return files;
    }

    public String getSelectedFile(int _index) {
        return files.get(_index).getName();
    }

    public String getSelectedFilePath(int _index) {
        return files.get(_index).getAbsolutePath();
    }

    public AbsTableGui getTable() {
        return table;
    }
}
