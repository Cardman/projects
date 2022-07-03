package code.mock;

import code.stream.AbstractFile;
import code.threads.FileStruct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MockFile implements AbstractFile {
    private final MockFileSet fileSet;
    private String abs;
    public MockFile(MockFileSet _mfs, String _s) {
        fileSet = _mfs;
        abs = absolute(_mfs, _s);
    }
    public static String absolute(MockFileSet _mfs, String _s) {
        if (StringUtil.contains(_mfs.getRoots(),_s)) {
            return _s;
        }
        return otherThanRoot(_mfs, _s);
    }

    private static String otherThanRoot(MockFileSet _mfs, String _s) {
        String rep_ = prefixed(_mfs, _s);
        if (rep_.isEmpty()) {
            return "";
        }
        CustList<String> path_ = StringUtil.splitChar(rep_, '/');
        possibleRem(path_);
        StringList stack_ = new StringList();
        int len_ = path_.size();
        for (int i = 0; i < len_; i++) {
            String par_ = path_.get(i);
            if (StringUtil.quickEq("..", par_)) {
                if (stack_.size() <= 1) {
                    return "";
                }
                stack_.removeQuicklyLast();
            } else if (!StringUtil.quickEq(".", par_)) {
                if (i > 0&&!_mfs.getValidating().ok(par_)) {
                    return "";
                }
                stack_.add(par_);
            }
        }
        String full_ = StringUtil.join(stack_,'/');
        for (String r: _mfs.getRoots()) {
            if (StringUtil.quickEq(r,full_+"/")) {
                return r;
            }
        }
        return full_;
    }

    private static void possibleRem(CustList<String> _path) {
        if (_path.last().trim().isEmpty()) {
            _path.removeQuicklyLast();
        }
    }

    private static String prefixed(MockFileSet _mfs, String _s) {
        if (_s.isEmpty()) {
            return "";
        }
        String rep_ = StringUtil.replaceBackSlash(_s);
        StringList all_ = StringUtil.splitChar(rep_, '/');
        for (String p: ext(all_)) {
            if (p.trim().isEmpty()) {
                return "";
            }
        }
        for (String r: _mfs.getRoots()) {
            if (rep_.startsWith(r)) {
                return rep_;
            }
        }
        return StringUtil.replaceBackSlash(_mfs.getCurrentPath()+rep_);
    }

    private static CustList<String> ext(CustList<String> _all) {
        CustList<String> ret_ = new CustList<String>(_all);
        possibleRem(ret_);
        if (ret_.first().trim().isEmpty()) {
            ret_.remove(0);
        }
        return ret_;
    }
    @Override
    public String getName() {
        return StringUtil.splitChar(abs,'/').last();
    }

    @Override
    public String getAbsolutePath() {
        return abs;
    }

    @Override
    public String[] list() {
        if (!isDirectory()) {
            return new MockFileListInfoName(null).getSimples();
        }
        StringList files_ = new StringList();
        String from_;
        if (StringUtil.contains(fileSet.getRoots(),abs)) {
            from_ = abs;
        } else {
            from_ = abs + "/";
        }
        for (String p: fileSet.getFiles().getKeys()) {
            if (!StringUtil.quickEq(p,from_)&& p.startsWith(from_)) {
                String sub_ = p.substring(from_.length());
                addName(sub_, files_);
            }
        }
        files_.removeDuplicates();
        files_.sort();
        int len_ = files_.size();
        String[] l_ = new String[len_];
        for (int i = 0; i < len_; i++) {
            l_[i] = files_.get(i);
        }
        return l_;
    }

    private static void addName(String _sub, StringList _files) {
        int sep_ = _sub.indexOf('/');
        if (sep_ < 0) {
            _files.add(_sub);
        } else {
            _files.add(_sub.substring(0,sep_));
        }
    }

    @Override
    public boolean isDirectory() {
        FileStruct val_ = fileSet.getFiles().getVal(abs);
        if (val_ == null) {
            return false;
        }
        return val_.getContent() == null;
    }

    @Override
    public boolean exists() {
        return fileSet.getFiles().getVal(abs) != null;
    }

    @Override
    public long length() {
        FileStruct val_ = fileSet.getFiles().getVal(abs);
        if (val_ == null) {
            return 0;
        }
        return val_.getContent().length;
    }

    @Override
    public long lastModified() {
        FileStruct val_ = fileSet.getFiles().getVal(abs);
        if (val_ == null) {
            return 0;
        }
        return val_.getLastDate();
    }

    @Override
    public boolean renameTo(AbstractFile _abstractFile) {
        if (_abstractFile.exists()) {
            return false;
        }
        String otherAbs_ = _abstractFile.getAbsolutePath();
        String r_ = fileSet.linkedRoot(otherAbs_);
        if (!fileSet.getValidating().okPath(otherAbs_.substring(r_.length()), '/')) {
            return false;
        }
        FileStruct old_ = fileSet.getFiles().getVal(abs);
        if (old_ == null) {
            return false;
        }
        fileSet.getFiles().removeKey(abs);
        abs = otherAbs_;
        fileSet.getFiles().put(abs,old_);
        return true;
    }

    @Override
    public boolean delete() {
        for (String p: fileSet.getFiles().getKeys()) {
            if (!StringUtil.quickEq(p,abs)&& p.startsWith(abs+"/")) {
                return false;
            }
        }
        FileStruct old_ = fileSet.getFiles().getVal(abs);
        fileSet.getFiles().removeKey(abs);
        return old_ != null;
    }

    @Override
    public String getParent() {
        CustList<String> parts_ = parentParts();
        return StringUtil.join(parts_,'/');
    }

    private CustList<String> parentParts() {
        StringList list_ = StringUtil.splitChar(abs, '/');
        return list_.mid(0, list_.size() - 1);
    }

    @Override
    public boolean mkdirs() {
        String r_ = fileSet.linkedRoot(abs);
        if (!fileSet.getValidating().okPath(abs.substring(r_.length()), '/')) {
            return false;
        }
        StringList list_ = StringUtil.splitChar(abs, '/');
        StringList pars_ = new StringList();
        StringList parsNext_ = new StringList();
        int index_ = 0;
        for (String p: list_) {
            String intPath_ = StringUtil.join(pars_, '/');
            if (MockBinFact.load(intPath_,fileSet).getContent() != null) {
                return false;
            }
            parsNext_.add(p);
            if (index_ > 0&&fileSet.getFiles().getVal(intPath_) == null&&!StringUtil.quickEq(StringUtil.join(parsNext_, '/'),abs)) {
                fileSet.getFiles().addEntry(intPath_, new FileStruct(null,fileSet.getMockMillis().millis()));
            }
            pars_.add(p);
            index_++;
        }
        if (fileSet.getFiles().contains(abs)) {
            return false;
        }
        fileSet.getFiles().put(abs,new FileStruct(null,fileSet.getMockMillis().millis()));
        return true;
    }
}
