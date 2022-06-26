package code.stream;


import code.util.core.StringUtil;

public final class PathsUtil {
    private PathsUtil() {
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
}
