package code.gui;
import java.io.File;

import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

/**Thread safe class*/
public final class ThreadSearchingFile implements Runnable {

    private final FileOpenDialog dialog;

    private final CustList<File> backup;
//    private Cursor cursor;

    private final File folder;

    public ThreadSearchingFile(FileOpenDialog _dialog, CustList<File> _backup, File _folder) {
        dialog = _dialog;
        backup = _backup;
        folder = _folder;
//        cursor = dialog.getCursor();
//        dialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    @Override
    public void run() {
//        CustList<File> backup_ = new CustList<>(dialog.getFiles());
//        dialog.getFiles().clear();
//        dialog.init(dialog.getCurrentFolder(), dialog.getExtension());
//        dialog.applyChanges();
//        dialog.setKeepSearching(true);
        CustList<File> current_;
        current_ = new CustList<File>(folder);
        FileCount fc_ = new FileCount();
        while (true) {
            CustList<File> next_;
            next_ = new CustList<File>();
            if (!keep(current_,next_,fc_)) {
                return;
            }
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        dialog.setKeepSearching(false);
//        dialog.setCursor(cursor);
//        CustComponent.invokeLater(new AfterSearchingFile(dialog, cursor, true, results_));
    }
    boolean keep(CustList<File> _current,CustList<File> _next, FileCount _fc) {
        for (File d: _current) {
            File[] files_ = d.listFiles();
            if (files_ == null) {
                continue;
            }
            for (File f: files_) {
                if (!dialog.isKeepSearching()) {
//                        CustComponent.invokeLater(new AfterSearchingFile(dialog, cursor, false, results_));
//                        CustComponent.invokeLater(new AfterSearchingFile(dialog, false, results_, backup_));
                    CustComponent.invokeLater(new AfterSearchingFile(dialog, backup));
                    return false;
                }
                processSearch(_next, _fc, f);
            }
        }
        return true;
    }

    private void processSearch(CustList<File> _next, FileCount _fc, File _f) {
        _fc.incrSearch();
        CustComponent.invokeLater(new SettingInformation(dialog, _fc.getSearch(), _fc.getFound()));
        if (_f.isDirectory()) {
            if (StringUtil.contains(dialog.getExcludedFolders(), StringUtil.replaceBackSlash(_f.getAbsolutePath()))) {
                return;
            }
            _next.add(_f);
        } else {
            String ext_ = dialog.getExtension();
            String name_ = _f.getName();
            if (!name_.endsWith(ext_)) {
                return;
            }
            int len_ = ext_.length();
            String base_ = name_.substring(IndexConstants.FIRST_INDEX, name_.length() - len_);
            if (!StringUtil.match(base_, dialog.getTypedString())) {
                return;
            }
            _fc.incrFound();
            CustComponent.invokeLater(new SearchingFile(dialog, _f));
//                        if (StringList.match(f.getName(), dialog.getTypedString())) {
//                            if (f.getName().endsWith(dialog.getExtension())) {
//                                results_.add(f);
//                                CustComponent.invokeLater(new SearchingFile(dialog, f));
//                            }
//                        }
        }
    }
}
