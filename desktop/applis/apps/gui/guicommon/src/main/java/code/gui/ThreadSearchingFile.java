package code.gui;

import code.stream.AbstractFile;
import code.stream.PathsUtil;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

/**Thread safe class*/
public final class ThreadSearchingFile implements Runnable {

    private final FileOpenDialog dialog;

    private final CustList<AbstractFile> backup;
//    private Cursor cursor;

    private final AbstractFile folder;

    public ThreadSearchingFile(FileOpenDialog _dialog, CustList<AbstractFile> _backup, AbstractFile _folder) {
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
        CustList<AbstractFile> current_;
        current_ = new CustList<AbstractFile>(folder);
        FileCount fc_ = new FileCount();
        while (true) {
            CustList<AbstractFile> next_;
            next_ = new CustList<AbstractFile>();
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
    boolean keep(CustList<AbstractFile> _current,CustList<AbstractFile> _next, FileCount _fc) {
        for (AbstractFile d: _current) {
            for (AbstractFile f: PathsUtil.abs(d,dialog.getSuperFrame().getFileCoreStream()).getNames()) {
                if (!dialog.isKeepSearching()) {
//                        CustComponent.invokeLater(new AfterSearchingFile(dialog, cursor, false, results_));
//                        CustComponent.invokeLater(new AfterSearchingFile(dialog, false, results_, backup_));
                    FrameUtil.invokeLater(new AfterSearchingFile(dialog, backup), dialog.getSuperFrame().getFrames());
                    return false;
                }
                processSearch(_next, _fc, f);
            }
        }
        return true;
    }

    private void processSearch(CustList<AbstractFile> _next, FileCount _fc, AbstractFile _f) {
        _fc.incrSearch();
        FrameUtil.invokeLater(new SettingInformation(dialog, _fc.getSearch(), _fc.getFound()), dialog.getSuperFrame().getFrames());
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
            FrameUtil.invokeLater(new SearchingFile(dialog, _f), dialog.getSuperFrame().getFrames());
//                        if (StringList.match(f.getName(), dialog.getTypedString())) {
//                            if (f.getName().endsWith(dialog.getExtension())) {
//                                results_.add(f);
//                                CustComponent.invokeLater(new SearchingFile(dialog, f));
//                            }
//                        }
        }
    }
}
