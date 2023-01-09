package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.filenames.PathUtil;
import code.stream.BytesInfo;
import code.stream.core.ReadBinFiles;
import code.threads.AbstractThreadFactory;
import code.threads.FileStruct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MemoryFileSystem implements AbstractFileSystem {

    private final AbstractNameValidating nameValidating;
    private FolderStruct root;
    private final AbstractThreadFactory threadFactory;
    private FileSystemParameterizing fileSystemParameterizing;

    public MemoryFileSystem(AbstractNameValidating _nameValidating, AbstractThreadFactory _threadFact) {
        nameValidating = _nameValidating;
        threadFactory = _threadFact;
    }

    @Override
    public void build(ExecutingOptions _opt, ReadBinFiles _readBin) {
        fileSystemParameterizing = _opt.getFileSystemParameterizing();
        root = FolderStruct.build(fileSystemParameterizing,_readBin.getZipFolders(),_readBin.getZipFiles(),threadFactory);
    }

    @Override
    public void changeDir(String _file, RunnableContextEl _rCont) {
        String abs_ = absolutePath(_file, _rCont);
        String normal_ = StringUtil.replaceBackSlashDot(abs_);
        if (!isDirectory(normal_,_rCont)) {
            return;
        }
        _rCont.setCurrentDir(normal_);
    }

    @Override
    public String currentDir(RunnableContextEl _rCont) {
        return _rCont.getCurrentDir();
    }

    @Override
    public String contentsOfFile(String _file, RunnableContextEl _rCont) {
        String abs_ = absolutePath(_file, _rCont);
        StringList parts_ = PathUtil.splitParts(abs_);
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return null;
        }
        FileStruct val_ = curr_.getFiles().getVal(parts_.last());
        if (val_ == null) {
            return null;
        }
        byte[] content_ = val_.getContent();
        return StringUtil.decode(content_);
    }

    @Override
    public boolean saveTextFile(String _file, String _content, RunnableContextEl _rCont) {
        FileResultMem f_ = new FileResultMem(this,_file,_rCont);
        FolderStruct curr_ = f_.getFolder();
        if (curr_ == null) {
            return false;
        }
        String simpleName_ = f_.getSimpleName();
        FileStruct file_ = curr_.getFiles().getVal(simpleName_);
        if (file_ != null) {
            file_.setDatedContent(StringUtil.encode(_content),threadFactory);
            return true;
        }
        curr_.setupDate(threadFactory);
        curr_.getFiles().addEntry(simpleName_,new FileStruct(StringUtil.encode(_content),threadFactory));
        return true;
    }

    @Override
    public BytesInfo loadFile(String _file, RunnableContextEl _rCont) {
        String abs_ = absolutePath(_file, _rCont);
        StringList parts_ = PathUtil.splitParts(abs_);
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return new BytesInfo(new byte[0],true);
        }
        FileStruct val_ = curr_.getFiles().getVal(parts_.last());
        if (val_ == null) {
            return new BytesInfo(new byte[0],true);
        }
        return new BytesInfo(val_.getContent(),false);
    }

    @Override
    public boolean writeFile(String _file, byte[] _content, RunnableContextEl _rCont) {
        FileResultMem f_ = new FileResultMem(this,_file,_rCont);
        FolderStruct curr_ = f_.getFolder();
        if (curr_ == null) {
            return false;
        }
        String simpleName_ = f_.getSimpleName();
        FileStruct file_ = curr_.getFiles().getVal(simpleName_);
        if (file_ != null) {
            file_.setDatedContent(_content,threadFactory);
            return true;
        }
        curr_.setupDate(threadFactory);
        curr_.getFiles().addEntry(simpleName_,new FileStruct(_content,threadFactory));
        return true;
    }

    @Override
    public boolean delete(String _file, RunnableContextEl _rCont) {
        FolderResultMem fol_ = new FolderResultMem(this,_file,_rCont);
        FolderStruct curr_ = fol_.getCurrent();
        if (curr_ == null) {
            return false;
        }
        String simpleName_ = fol_.getSimpleName();
        boolean success_;
        if (fol_.getDelta() != 0) {
            FolderStruct del_ = curr_.getFolders().getVal(simpleName_);
            if (del_ != null) {
                if (!del_.getFolders().isEmpty()) {
                    return false;
                }
                if (!del_.getFiles().isEmpty()) {
                    return false;
                }
            }
            int count_ = curr_.getFolders().size();
            curr_.getFolders().removeKey(simpleName_);
            success_ = count_ != curr_.getFolders().size();
        } else {
            int count_ = curr_.getFiles().size();
            curr_.getFiles().removeKey(simpleName_);
            success_ = count_ != curr_.getFiles().size();
        }
        if (success_) {
            curr_.setupDate(threadFactory);
        }
        return success_;
    }

    @Override
    public boolean rename(String _origin, String _dest, RunnableContextEl _rCont) {
        String origin_ = absolutePath(_origin, _rCont);
        String destElt_ = absolutePath(_dest, _rCont);
        if (endsSep(origin_) && !endsSep(destElt_)) {
            return false;
        }
        if (endsSep(destElt_) && !endsSep(origin_)) {
            return false;
        }
        if (endsSep(origin_)) {
            return renameFolder(origin_, destElt_);
        }
        return renameFile(origin_, destElt_);
    }

    private boolean renameFile(String _origin, String _destElt) {
        StringList parts_ = PathUtil.splitParts(_origin);
        String simpleName_ = parts_.last();
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return false;
        }
        FileStruct file_ = curr_.getFiles().getVal(simpleName_);
        if (file_ == null) {
            return false;
        }
        StringList partsDest_ = PathUtil.splitParts(_destElt);
        String simpleNameDest_ = partsDest_.last();
        FolderStruct dest_ = getParentFolder(partsDest_);
        if (dest_ == null) {
            return false;
        }
        int indexDest_ = dest_.getFiles().indexOfEntry(simpleNameDest_);
        if (indexDest_ > -1) {
            return false;
        }
        dest_.getFiles().addEntry(simpleNameDest_,file_);
        curr_.getFiles().removeKey(simpleName_);
        dest_.setupDate(threadFactory);
        curr_.setupDate(threadFactory);
        return true;
    }

    private boolean renameFolder(String _origin, String _destElt) {
        StringList parts_ = PathUtil.splitParts(_origin);
        String simpleName_ = parts_.get(parts_.size()-2);
        FolderStruct curr_ = getParentFolder(parts_.left(parts_.size()-1));
        if (curr_ == null) {
            return false;
        }
        FolderStruct folder_ = curr_.getFolders().getVal(simpleName_);
        if (folder_ == null) {
            return false;
        }
        StringList partsDest_ = PathUtil.splitParts(_destElt);
        String simpleNameDest_ = partsDest_.get(partsDest_.size()-2);
        FolderStruct dest_ = getParentFolder(partsDest_.left(partsDest_.size()-1));
        if (dest_ == null) {
            return false;
        }
        int indexDest_ = dest_.getFolders().indexOfEntry(simpleNameDest_);
        if (indexDest_ > -1) {
            return false;
        }
        dest_.getFolders().addEntry(simpleNameDest_,folder_);
        curr_.getFolders().removeKey(simpleName_);
        dest_.setupDate(threadFactory);
        curr_.setupDate(threadFactory);
        return true;
    }

    @Override
    public boolean logToFile(String _file, String _content, RunnableContextEl _rCont) {
        FileResultMem f_ = new FileResultMem(this,_file,_rCont);
        FolderStruct curr_ = f_.getFolder();
        if (curr_ == null) {
            return false;
        }
        String simpleName_ = f_.getSimpleName();
        FileStruct file_ = curr_.getFiles().getVal(simpleName_);
        if (file_ == null) {
            curr_.getFiles().addEntry(simpleName_,new FileStruct(StringUtil.encode(_content),threadFactory));
            curr_.setupDate(threadFactory);
            return true;
        }
        byte[] content_ = StringUtil.encode(_content);
        byte[] val_ = file_.getContent();
        int already_ = val_.length;
        int contLen_ = content_.length;
        byte[] merged_ = new byte[already_ + contLen_];
        for (int i = 0; i < already_; i++) {
            val(merged_, i, val_, i);
        }
        for (int i = 0; i < contLen_; i++) {
            val(merged_, i + already_, content_, i);
        }
        file_.setDatedContent(merged_,threadFactory);
        return true;
    }

    private static void val(byte[] _merged, int _j, byte[] _content, int _i) {
        _merged[_j] = _content[_i];
    }

    @Override
    public String absolutePath(String _file, RunnableContextEl _rCont) {
        if (isAbsolute(_file, _rCont)) {
            return StringUtil.replaceBackSlash(_file);
        }
        String abs_ = _rCont.getCurrentDir();
        return PathUtil.transform(abs_,_file);
    }

    @Override
    public long length(String _file, RunnableContextEl _rCont) {
        String abs_ = absolutePath(_file, _rCont);
        StringList parts_ = PathUtil.splitParts(abs_);
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return 0;
        }
        FileStruct val_ = curr_.getFiles().getVal(parts_.last());
        if (val_ == null) {
            return 0;
        }
        return val_.getContent().length;
    }

    @Override
    public String getName(String _file, RunnableContextEl _rCont) {
        String abs_ = absolutePath(_file, _rCont);
        StringList parts_ = PathUtil.splitParts(abs_);
        return parts_.last();
    }

    @Override
    public String getParentPath(String _file, RunnableContextEl _rCont) {
        String abs_ = absolutePath(_file, _rCont);
        if (StringUtil.quickEq(abs_,"/")) {
            return "";
        }
        return adapt(abs_);
    }

    public static String adapt(String _abs) {
        StringList parts_ = PathUtil.splitParts(_abs);
        int nbElements_ = parts_.size() - 1;
        if (nbElements_ == 0) {
            return "/";
        }
        return StringUtil.replaceBackSlashDot(StringUtil.join(parts_.left(nbElements_),'/'));
    }

    @Override
    public boolean isDirectory(String _file, RunnableContextEl _rCont) {
        String abs_ = absolutePath(_file, _rCont);
        if (StringUtil.quickEq(abs_,"/")) {
            return true;
        }
        abs_ = skipLastSep(abs_);
        StringList parts_ = PathUtil.splitParts(abs_);
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return false;
        }
        return curr_.getFolders().getVal(parts_.last()) != null;
    }

    @Override
    public boolean isFile(String _file, RunnableContextEl _rCont) {
        String abs_ = absolutePath(_file, _rCont);
        if (StringUtil.quickEq(abs_,"/")) {
            return false;
        }
        StringList parts_ = PathUtil.splitParts(abs_);
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return false;
        }
        return curr_.getFiles().getVal(parts_.last()) != null;
    }

    @Override
    public StringList getRoots(RunnableContextEl _rCont) {
        return new StringList("/");
    }

    @Override
    public boolean isAbsolute(String _file, RunnableContextEl _rCont) {
        return _file.startsWith("/") || _file.startsWith("\\");
    }

    @Override
    public boolean mkdirs(String _file, RunnableContextEl _rCont) {
        String abs_ = skipLastSep(absolutePath(_file, _rCont));
        StringList parts_ = PathUtil.splitParts(abs_);
        String simpleName_ = parts_.last();
        if (!nameValidating.ok(simpleName_)) {
            return false;
        }
        FolderStruct curr_ = root;
        int nbFolders_ = parts_.size()-1;
        for (int i = 1; i < nbFolders_; i++) {
            String parent_ = parts_.get(i);
            FolderStruct next_ = curr_.getFolders().getVal(parent_);
            if (next_ == null) {
                if (!nameValidating.ok(parent_)) {
                    return false;
                }
                FolderStruct new_ = new FolderStruct(fileSystemParameterizing, threadFactory);
                curr_.getFolders().addEntry(parent_, new_);
                curr_.setupDate(threadFactory);
                next_ = new_;
            }
            curr_ = next_;
        }
        if (curr_.getFolders().contains(simpleName_)) {
            return false;
        }
        curr_.setupDate(threadFactory);
        curr_.getFolders().addEntry(simpleName_,new FolderStruct(fileSystemParameterizing, threadFactory));
        return true;
    }

    @Override
    public long lastModified(String _file, RunnableContextEl _rCont) {
        FolderResultMem res_ = new FolderResultMem(this,_file,_rCont);
        FolderStruct curr_ = res_.getCurrent();
        if (curr_ == null) {
            return 0;
        }
        String simpleName_ = res_.getSimpleName();
        if (res_.getDelta() != 0) {
            FolderStruct fol_ = curr_.getFolders().getVal(simpleName_);
            if (fol_ == null) {
                return 0;
            }
            return fol_.getLastDate();
        }
        FileStruct fol_ = curr_.getFiles().getVal(simpleName_);
        if (fol_ == null) {
            return 0;
        }
        return fol_.getLastDate();
    }
    static int delta(boolean _folder) {
        if (_folder) {
            return -1;
        }
        return 0;
    }

    @Override
    public StringList getFiles(String _file, RunnableContextEl _rCont) {
        String abs_ = absolutePath(_file, _rCont);
        if (StringUtil.quickEq(abs_,"/")) {
            return rootElts(root.getFiles().getKeys());
        }
        abs_ = skipLastSep(abs_);
        FolderStruct curr_ = getCurrentFolder(PathUtil.splitParts(abs_));
        if (curr_ == null) {
            return null;
        }
        return othersElts(StringUtil.replaceBackSlashDot(abs_), curr_.getFiles().getKeys());
    }

    @Override
    public StringList getFolders(String _file, RunnableContextEl _rCont) {
        String abs_ = absolutePath(_file, _rCont);
        if (StringUtil.quickEq(abs_,"/")) {
            return rootElts(root.getFolders().getKeys());
        }
        abs_ = skipLastSep(abs_);
        FolderStruct curr_ = getCurrentFolder(PathUtil.splitParts(abs_));
        if (curr_ == null) {
            return null;
        }
        return othersElts(StringUtil.replaceBackSlashDot(abs_), curr_.getFolders().getKeys());
    }

    private static StringList othersElts(String _rep, CustList<String> _keys) {
        StringList files_ = new StringList();
        for (String f: _keys) {
            files_.add(_rep +f);
        }
        files_.sort();
        return files_;
    }

    private static String skipLastSep(String _ab) {
        String abs_ = _ab;
        if (endsSep(abs_)) {
            abs_ = abs_.substring(0,abs_.length()-1);
        }
        return abs_;
    }

    private static StringList rootElts(CustList<String> _keys) {
        return othersElts("/", _keys);
    }

    private FolderStruct getCurrentFolder(CustList<String> _parts) {
        FolderStruct curr_ = root;
        int nbFolders_ = _parts.size();
        for (int i = 1; i < nbFolders_; i++) {
            if (curr_ == null) {
                return null;
            }
            curr_ = curr_.getFolders().getVal(_parts.get(i));
        }
        return curr_;
    }
    FolderStruct getParentFolder(CustList<String> _parts) {
        FolderStruct curr_ = root;
        int nbFolders_ = _parts.size()-1;
        for (int i = 1; i < nbFolders_; i++) {
            if (curr_ == null) {
                return null;
            }
            curr_ = curr_.getFolders().getVal(_parts.get(i));
        }
        return curr_;
    }

    public static boolean endsSep(String _file) {
        return _file.endsWith("/") || _file.endsWith("\\");
    }

    public FolderStruct getRoot() {
        return root;
    }

    AbstractNameValidating getNameValidating() {
        return nameValidating;
    }
}
