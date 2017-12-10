package code.gui;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import code.sml.util.ExtractFromFiles;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;

public class FileTable extends AbstractTableModel {

    static final int NAME_INDEX = 0;

    static final int DATE_INDEX = 1;

    static final int SIZE_INDEX = 2;

    static final int PATH_INDEX = 3;
    private static final String ACCESS = "gui.FileTable";

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

    private StringMap<String> messages = ExtractFromFiles.getMessagesFromLocaleClass(GuiConstants.FOLDER_MESSAGES_GUI, Constants.getLanguage(),ACCESS);

    private CustList<File> files = new CustList<File>();

    private String extension;

    private String folder;

    private int indexOfSorted = CustList.INDEX_NOT_FOUND_ELT;

    private boolean increasing;

    @Override
    public int getRowCount() {
        return files.size();
    }

    @Override
    public int getColumnCount() {
        return NB_COLS;
    }

    public void setSorting(int _col) {
        indexOfSorted = _col;
        increasing = !increasing;
        files.sortElts(new FileComparator(increasing, indexOfSorted, folder));
        //        switch(indexOfSorted) {
        //        case NAME_INDEX:
        //            if (increasing) {
        //                files.sort(new Comparator<File>(){
        //                    public int compare(File _o1, File _o2) {
        //                        return _o1.getName().compareTo(_o2.getName());
        //                    }
        //                });
        //            } else {
        //                files.sort(new Comparator<File>(){
        //                    public int compare(File _o1, File _o2) {
        //                        return _o2.getName().compareTo(_o1.getName());
        //                    }
        //                });
        //            }
        //            break;
        //        case DATE_INDEX:
        //            if (increasing) {
        //                files.sort(new Comparator<File>(){
        //                    public int compare(File _o1, File _o2) {
        //                        long lg_ = _o1.lastModified() - _o2.lastModified();
        //                        if (lg_ > 0) {
        //                            return 1;
        //                        }
        //                        if (lg_ < 0) {
        //                            return -1;
        //                        }
        //                        return 0;
        //                    }
        //                });
        //            } else {
        //                files.sort(new Comparator<File>(){
        //                    public int compare(File _o1, File _o2) {
        //                        long lg_ = _o2.lastModified() - _o1.lastModified();
        //                        if (lg_ > 0) {
        //                            return 1;
        //                        }
        //                        if (lg_ < 0) {
        //                            return -1;
        //                        }
        //                        return 0;
        //                    }
        //                });
        //            }
        //            break;
        //        case SIZE_INDEX:
        //            if (increasing) {
        //                files.sort(new Comparator<File>(){
        //                    public int compare(File _o1, File _o2) {
        //                        long lg_ = _o1.length() - _o2.length();
        //                        if (lg_ > 0) {
        //                            return 1;
        //                        }
        //                        if (lg_ < 0) {
        //                            return -1;
        //                        }
        //                        return 0;
        //                    }
        //                });
        //            } else {
        //                files.sort(new Comparator<File>(){
        //                    public int compare(File _o1, File _o2) {
        //                        long lg_ = _o2.length() - _o1.length();
        //                        if (lg_ > 0) {
        //                            return 1;
        //                        }
        //                        if (lg_ < 0) {
        //                            return -1;
        //                        }
        //                        return 0;
        //                    }
        //                });
        //            }
        //            break;
        //        case PATH_INDEX:
        //            if (increasing) {
        //                files.sort(new Comparator<File>(){
        //                    public int compare(File _o1, File _o2) {
        //                        String returnOne_ = _o1.getAbsolutePath();
        //                        returnOne_ = returnOne_.substring(folder.length());
        //                        returnOne_ = returnOne_.replace(StringList.BACK_SLASH, StreamTextFile.SEPARATEUR);
        //                        StringList pathOne_ = new StringList(returnOne_.split(StreamTextFile.SEPARATEUR));
        //                        String returnTwo_ = _o2.getAbsolutePath();
        //                        returnTwo_ = returnTwo_.substring(folder.length());
        //                        returnTwo_ = returnTwo_.replace(StringList.BACK_SLASH, StreamTextFile.SEPARATEUR);
        //                        StringList pathTwo_ = new StringList(returnTwo_.split(StreamTextFile.SEPARATEUR));
        //                        int min_ = Math.min(pathOne_.size(), pathTwo_.size());
        //                        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
        //                            int res_ = pathOne_.get(i).compareTo(pathTwo_.get(i));
        //                            if (res_ != 0) {
        //                                return res_;
        //                            }
        //                        }
        //                        return pathOne_.size() - pathTwo_.size();
        //                    }
        //                });
        //            } else {
        //                files.sort(new Comparator<File>(){
        //                    public int compare(File _o1, File _o2) {
        //                        String returnOne_ = _o1.getAbsolutePath();
        //                        returnOne_ = returnOne_.substring(folder.length());
        //                        returnOne_ = returnOne_.replace(StringList.BACK_SLASH, StreamTextFile.SEPARATEUR);
        //                        StringList pathOne_ = new StringList(returnOne_.split(StreamTextFile.SEPARATEUR));
        //                        String returnTwo_ = _o2.getAbsolutePath();
        //                        returnTwo_ = returnTwo_.substring(folder.length());
        //                        returnTwo_ = returnTwo_.replace(StringList.BACK_SLASH, StreamTextFile.SEPARATEUR);
        //                        StringList pathTwo_ = new StringList(returnTwo_.split(StreamTextFile.SEPARATEUR));
        //                        int min_ = Math.min(pathOne_.size(), pathTwo_.size());
        //                        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
        //                            int res_ = pathTwo_.get(i).compareTo(pathOne_.get(i));
        //                            if (res_ != 0) {
        //                                return res_;
        //                            }
        //                        }
        //                        return pathTwo_.size() - pathOne_.size();
        //                    }
        //                });
        //            }
        //            break;
        //            default:
        //        }
        fireTableStructureChanged();
        fireTableDataChanged();
    }

    @Override
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
        return head_+end_;
    }

    @Override
    public Object getValueAt(int _rowIndex, int _columnIndex) {
        File currentFile_;
        currentFile_ = files.get(_rowIndex);
        if(_columnIndex == NAME_INDEX) {
            if (!extension.isEmpty()) {
                //                return currentFile_.getName().replaceAll(StringList.quote(extension)+END_REG_EXP, EMPTY_STRING);
                return StringList.replaceEnd(currentFile_.getName(), extension);
                // currentFile_.getName().replaceAll(StringList.quote(extension)+END_REG_EXP, EMPTY_STRING);
            }
            return currentFile_.getName();
        } else if(_columnIndex == DATE_INDEX) {
            return new SimpleDateFormat(DATE_FORMAT).format(
                    new Date(currentFile_.lastModified()));
        } else if(_columnIndex == SIZE_INDEX) {
            return Long.toString(currentFile_.length());
        } else if(_columnIndex == PATH_INDEX) {
            String return_ = currentFile_.getAbsolutePath();
            return_ = return_.substring(folder.length());
            return_ = StringList.replaceBackSlash(return_);
            return return_;
        }
        return null;
    }

    public void setupFiles(CustList<File> _list,String _folder, String _extension) {
        indexOfSorted = CustList.INDEX_NOT_FOUND_ELT;
        extension = _extension;
        increasing = false;
        files.clear();
        files.addAllElts(_list);
        folder = _folder;
        fireTableDataChanged();
        fireTableStructureChanged();
    }

    public void setupFile(File _file) {
        files.add(_file);
        fireTableDataChanged();
        fireTableStructureChanged();
    }

    public void init(String _folder, String _extension) {
        indexOfSorted = CustList.INDEX_NOT_FOUND_ELT;
        extension = _extension;
        increasing = false;
        folder = _folder;
    }

    public void applyChanges() {
        fireTableDataChanged();
        fireTableStructureChanged();
    }

    public CustList<File> getFiles() {
        return files;
    }

    public String getSelectedFile(int _index) {
        return files.get(_index).getName();
    }

    public String getSelectedFilePath(int _index) {
        return files.get(_index).getAbsolutePath();
    }
}
