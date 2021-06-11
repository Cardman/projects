package code.stream;


public final class PathsUtil {
    private PathsUtil() {
    }
    public static FileListInfo abs(AbstractFileCoreStream _fac,String _parent,String[] _children) {
        if (_children == null) {
            return new FileListInfo(null);
        }
        int length_ = _children.length;
        AbstractFile[] res_ = new AbstractFile[length_];
        for (int i = 0; i < length_; i++) {
            set(res_,i,_fac.newFile(_parent+_children[i]));
        }
        return new FileListInfo(res_);
    }
    private static void set(AbstractFile[] _dest, int _index, AbstractFile _src) {
        _dest[_index] = _src;
    }
}
