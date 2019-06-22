package code.gui;
import java.io.File;

import javax.swing.SwingUtilities;

import code.util.CustList;
import code.util.StringList;

/**Thread safe class*/
public final class ThreadSearchingFile extends Thread {

    private FileOpenDialog dialog;

    private CustList<File> backup;
//    private Cursor cursor;

    private File folder;

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
        CustList<File> results_;
        results_ = new CustList<File>();
        CustList<File> current_;
        current_ = new CustList<File>(folder);
        CustList<File> next_;
        next_ = new CustList<File>();
        int nb_ = 0;
        while (true) {
            for (File d: current_) {
                File[] files_ = d.listFiles();
                if (files_ == null) {
                    continue;
                }
                for (File f: files_) {
                    if (!dialog.isKeepSearching()) {
//                        SwingUtilities.invokeLater(new AfterSearchingFile(dialog, cursor, false, results_));
//                        SwingUtilities.invokeLater(new AfterSearchingFile(dialog, false, results_, backup_));
                        SwingUtilities.invokeLater(new AfterSearchingFile(dialog, backup));
                        return;
                    }
                    nb_++;
                    SwingUtilities.invokeLater(new SettingInformation(dialog, nb_, results_.size()));
                    if (f.isDirectory()) {
                        if (StringList.contains(dialog.getExcludedFolders(), StringList.replaceBackSlash(f.getAbsolutePath()))) {
                            continue;
                        }
                        next_.add(f);
                    } else {
                        String ext_ = dialog.getExtension();
                        String name_ = f.getName();
                        if (!name_.endsWith(ext_)) {
                            continue;
                        }
                        int len_ = ext_.length();
                        String base_ = name_.substring(CustList.FIRST_INDEX, name_.length() - len_);
                        if (!StringList.match(base_, dialog.getTypedString())) {
                            continue;
                        }
                        results_.add(f);
                        SwingUtilities.invokeLater(new SearchingFile(dialog, f));
//                        if (StringList.match(f.getName(), dialog.getTypedString())) {
//                            if (f.getName().endsWith(dialog.getExtension())) {
//                                results_.add(f);
//                                SwingUtilities.invokeLater(new SearchingFile(dialog, f));
//                            }
//                        }
                    }
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            current_ = new CustList<File>(next_);
            next_ = new CustList<File>();
        }
        dialog.setKeepSearching(false);
//        dialog.setCursor(cursor);
//        SwingUtilities.invokeLater(new AfterSearchingFile(dialog, cursor, true, results_));
    }
}
