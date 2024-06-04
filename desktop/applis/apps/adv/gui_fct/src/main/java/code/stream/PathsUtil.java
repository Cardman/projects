package code.stream;


import code.util.StringList;
import code.util.core.StringUtil;

public final class PathsUtil {

    public static final String SEPARATEUR = "/";
    private PathsUtil() {
    }

    public static StringList listRootsAbPath(AbstractFileCoreStream _list) {
        AbstractListRoot roots_ = _list.newFileList();
        StringList l_ = new StringList();
        int length_ = roots_.length();
        for (int i = 0; i< length_; i++) {
            l_.add(StringUtil.replaceBackSlashDot(roots_.path(i)));
        }
        return l_;
    }

    public static FileListInfo abs(AbstractFile _current, AbstractFileCoreStream _fac) {
        String[] list_ = _current.list();
        if (list_ == null) {
            return new FileListInfo(null);
        }
        String parent_ = StringUtil.replaceBackSlashDot(_current.getAbsolutePath());
        int length_ = list_.length;
        AbstractFile[] res_ = new AbstractFile[length_];
        for (int i = 0; i < length_; i++) {
            set(res_,i,_fac.newFile(parent_+list_[i]));
        }
        return new FileListInfo(res_);
    }
    private static void set(AbstractFile[] _dest, int _index, AbstractFile _src) {
        _dest[_index] = _src;
    }

    public static boolean isAbsolute(String _path,AbstractFileCoreStream _list) {
        AbstractFile file_ = _list.newFile(_path);
        String absPath_ = StringUtil.replaceBackSlashDot(file_.getAbsolutePath());
        String path_ = StringUtil.replaceBackSlashDot(_path);
        return StringUtil.quickEq(absPath_,path_);
    }

    public static boolean mkdirs(String _folder,AbstractFileCoreStream _list) {
        return _list.newFile(_folder).mkdirs();
    }
    public static String getRelativeRootPath(String _absolute,AbstractFileCoreStream _list) {
        for (String r: listRootsAbPath(_list)) {
            if (_absolute.startsWith(r)) {
                return _absolute.substring(r.length());
            }
        }
        return _absolute;
    }
}
