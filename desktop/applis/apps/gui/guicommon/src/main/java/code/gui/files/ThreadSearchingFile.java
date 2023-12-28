package code.gui.files;

import code.gui.AbsButton;
import code.gui.GuiBaseUtil;
import code.stream.AbstractFile;
import code.stream.PathsUtil;
import code.util.CustList;
import code.util.core.StringUtil;

/**Thread safe class*/
public final class ThreadSearchingFile implements Runnable {

    private final FileOpenDialog dialog;

    private final CustList<AbstractFile> backup;
//    private Cursor cursor;

    private final AbstractFile folder;
    private final AbsButton searchButton;
    public ThreadSearchingFile(FileOpenDialog _dialog, CustList<AbstractFile> _backup, AbstractFile _folder, AbsButton _but) {
        dialog = _dialog;
        backup = _backup;
        folder = _folder;
        searchButton =_but;
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
        CustList<AbstractFile> current_;
        current_ = new CustList<AbstractFile>(folder);
        FileCount fc_ = new FileCount();
        while (true) {
            CustList<AbstractFile> next_;
            next_ = new CustList<AbstractFile>();
            if (!keep(current_,next_,fc_)) {
                searchButton.setEnabled(true);
                return;
            }
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        dialog.setKeepSearching(false);
        searchButton.setEnabled(true);
//        dialog.setCursor(cursor);
//        CustComponent.invokeLater(new AfterSearchingFile(dialog, cursor, true, results_));
    }
    boolean keep(CustList<AbstractFile> _current,CustList<AbstractFile> _next, FileCount _fc) {
        for (AbstractFile d: _current) {
            for (AbstractFile f: PathsUtil.abs(d,dialog.getProgramInfos().getFileCoreStream()).getNames()) {
                if (!dialog.isKeepSearching()) {
//                        CustComponent.invokeLater(new AfterSearchingFile(dialog, cursor, false, results_));
//                        CustComponent.invokeLater(new AfterSearchingFile(dialog, false, results_, backup_));
                    GuiBaseUtil.invokeLater(new AfterSearchingFile(dialog, backup), dialog.getProgramInfos());
                    return false;
                }
                processSearch(_next, _fc, f);
            }
        }
        return true;
    }

    private void processSearch(CustList<AbstractFile> _next, FileCount _fc, AbstractFile _f) {
        _fc.incrSearch();
        GuiBaseUtil.invokeLater(new SettingInformation(dialog, _fc.getSearch(), _fc.getFound()), dialog.getProgramInfos());
        if (_f.isDirectory()) {
            _next.add(_f);
        } else {
//            String ext_ = dialog.getExtension();
            String name_ = _f.getName();
//            if (!name_.endsWith(ext_)) {
//                return;
//            }
//            int len_ = ext_.length();
//            String base_ = name_.substring(IndexConstants.FIRST_INDEX, name_.length() - len_);
//            if (!StringUtil.match(base_, dialog.getTypedString())) {
            if (!StringUtil.match(name_, dialog.typedString())) {
                return;
            }
            _fc.incrFound();
            GuiBaseUtil.invokeLater(new SearchingFile(dialog, _f), dialog.getProgramInfos());
//                        if (StringList.match(f.getName(), dialog.getTypedString())) {
//                            if (f.getName().endsWith(dialog.getExtension())) {
//                                results_.add(f);
//                                CustComponent.invokeLater(new SearchingFile(dialog, f));
//                            }
//                        }
        }
    }
}
