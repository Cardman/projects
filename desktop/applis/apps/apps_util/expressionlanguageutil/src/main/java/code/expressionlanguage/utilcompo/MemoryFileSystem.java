package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.stream.core.ReadBinFiles;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

public final class MemoryFileSystem implements AbstractFileSystem {

    private final UniformingString uniformingString;
    private final AbstractNameValidating nameValidating;
    private FolderStruct root;

    public MemoryFileSystem(UniformingString _uniformingString, AbstractNameValidating _nameValidating) {
        uniformingString = _uniformingString;
        nameValidating = _nameValidating;
    }

    @Override
    public void build(String _base, ReadBinFiles _readBin) {
        root = FolderStruct.build(_readBin.getZipFolders(),_readBin.getZipFiles());
    }

    @Override
    public String contentsOfFile(String _file, RunnableContextEl _rCont) {
        StringList parts_ = StringUtil.splitChars(_file, '/','\\');
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
        byte[] content_ = StringUtil.encode(_content);
        StringList parts_ = StringUtil.splitChars(_file, '/','\\');
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return false;
        }
        String simpleName_ = parts_.last();
        if (!nameValidating.ok(simpleName_)) {
            return false;
        }
        FileStruct file_ = curr_.getFiles().getVal(simpleName_);
        if (file_ != null) {
            file_.setDatedContent(content_);
            return true;
        }
        curr_.setupDate();
        curr_.getFiles().addEntry(simpleName_,new FileStruct(content_));
        return true;
    }

    @Override
    public byte[] loadFile(String _file, RunnableContextEl _rCont) {
        StringList parts_ = StringUtil.splitChars(_file, '/','\\');
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return null;
        }
        FileStruct val_ = curr_.getFiles().getVal(parts_.last());
        if (val_ == null) {
            return null;
        }
        return val_.getContent();
    }

    @Override
    public boolean writeFile(String _file, byte[] _content, RunnableContextEl _rCont) {
        StringList parts_ = StringUtil.splitChars(_file, '/','\\');
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return false;
        }
        String simpleName_ = parts_.last();
        if (!nameValidating.ok(simpleName_)) {
            return false;
        }
        FileStruct file_ = curr_.getFiles().getVal(simpleName_);
        if (file_ != null) {
            file_.setDatedContent(_content);
            return true;
        }
        curr_.setupDate();
        curr_.getFiles().addEntry(simpleName_,new FileStruct(_content));
        return true;
    }

    @Override
    public boolean delete(String _file, RunnableContextEl _rCont) {

        CustList<String> parts_ = StringUtil.splitChars(_file, '/','\\');
        String simpleName_ = parts_.last();
        boolean folder_;
        if (endsSep(_file)) {
            simpleName_ = parts_.get(parts_.size()-2);
            parts_ = parts_.left(parts_.size()-1);
            folder_ = true;
        } else {
            folder_ = false;
        }
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return false;
        }
        boolean success_;
        if (folder_) {
            FolderStruct del_ = curr_.getFolders().getVal(simpleName_);
            if (!del_.getFolders().isEmpty()) {
                return false;
            }
            if (!del_.getFiles().isEmpty()) {
                return false;
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
            curr_.setupDate();
        }
        return success_;
    }

    @Override
    public boolean rename(String _origin, String _dest, RunnableContextEl _rCont) {
        if (endsSep(_origin) && !endsSep(_dest)) {
            return false;
        }
        if (endsSep(_dest) && !endsSep(_origin)) {
            return false;
        }
        if (endsSep(_origin)) {
            StringList parts_ = StringUtil.splitChars(_origin, '/','\\');
            String simpleName_ = parts_.get(parts_.size()-2);
            FolderStruct curr_ = getParentFolder(parts_.left(parts_.size()-1));
            if (curr_ == null) {
                return false;
            }
            FolderStruct folder_ = curr_.getFolders().getVal(simpleName_);
            if (folder_ == null) {
                return false;
            }
            StringList partsDest_ = StringUtil.splitChars(_dest, '/','\\');
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
            dest_.setupDate();
            curr_.setupDate();
        } else {
            StringList parts_ = StringUtil.splitChars(_origin, '/','\\');
            String simpleName_ = parts_.last();
            FolderStruct curr_ = getParentFolder(parts_);
            if (curr_ == null) {
                return false;
            }
            FileStruct file_ = curr_.getFiles().getVal(simpleName_);
            if (file_ == null) {
                return false;
            }
            StringList partsDest_ = StringUtil.splitChars(_dest, '/','\\');
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
            dest_.setupDate();
            curr_.setupDate();
        }
        return true;
    }

    @Override
    public boolean logToFile(String _file, String _content, RunnableContextEl _rCont) {
        byte[] content_ = StringUtil.encode(_content);
        StringList parts_ = StringUtil.splitChars(_file, '/','\\');
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return false;
        }
        String simpleName_ = parts_.last();
        if (!nameValidating.ok(simpleName_)) {
            return false;
        }
        FileStruct file_ = curr_.getFiles().getVal(simpleName_);
        if (file_ == null) {
            curr_.getFiles().addEntry(simpleName_,new FileStruct(content_));
            curr_.setupDate();
            return true;
        }
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
        file_.setDatedContent(merged_);
        return true;
    }

    private static void val(byte[] _merged, int _j, byte[] _content, int _i) {
        _merged[_j] = _content[_i];
    }

    @Override
    public String absolutePath(String _file, RunnableContextEl _rCont) {
        return _file;
    }

    @Override
    public long length(String _file, RunnableContextEl _rCont) {
        StringList parts_ = StringUtil.splitChars(_file, '/','\\');
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
        StringList parts_ = StringUtil.splitChars(_file, '/','\\');
        return parts_.last();
    }

    @Override
    public String getParentPath(String _file, RunnableContextEl _rCont) {
        StringList parts_ = StringUtil.splitChars(_file, '/','\\');
        return StringUtil.join(parts_.left(parts_.size()-1),'/');
    }

    @Override
    public boolean isDirectory(String _file, RunnableContextEl _rCont) {
        StringList parts_ = StringUtil.splitChars(_file, '/','\\');
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return false;
        }
        return curr_.getFolders().getVal(parts_.last()) != null;
    }

    @Override
    public boolean isFile(String _file, RunnableContextEl _rCont) {
        StringList parts_ = StringUtil.splitChars(_file, '/','\\');
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
        return true;
    }

    @Override
    public boolean mkdirs(String _file, RunnableContextEl _rCont) {
        StringList parts_ = StringUtil.splitChars(_file, '/','\\');
        String simpleName_ = parts_.last();
        if (!nameValidating.ok(simpleName_)) {
            return false;
        }
        FolderStruct curr_ = root;
        int nbFolders_ = parts_.size()-1;
        for (int i = 0; i < nbFolders_; i++) {
            String parent_ = parts_.get(i);
            FolderStruct next_ = curr_.getFolders().getVal(parent_);
            if (next_ == null) {
                if (!nameValidating.ok(parent_)) {
                    return false;
                }
                FolderStruct new_ = new FolderStruct();
                curr_.getFolders().addEntry(parent_, new_);
                curr_.setupDate();
                next_ = new_;
            }
            curr_ = next_;
        }
        if (curr_.getFolders().contains(simpleName_)) {
            return false;
        }
        curr_.setupDate();
        curr_.getFolders().addEntry(simpleName_,new FolderStruct());
        return true;
    }

    @Override
    public long lastModified(String _file, RunnableContextEl _rCont) {
        CustList<String> parts_ = StringUtil.splitChars(_file, '/','\\');
        String simpleName_ = parts_.last();
        boolean folder_;
        if (endsSep(_file)) {
            simpleName_ = parts_.get(parts_.size()-2);
            parts_ = parts_.left(parts_.size()-1);
            folder_ = true;
        } else {
            folder_ = false;
        }
        FolderStruct curr_ = getParentFolder(parts_);
        if (curr_ == null) {
            return 0;
        }
        if (folder_) {
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

    @Override
    public StringList getFiles(String _file, RunnableContextEl _rCont) {
        CustList<String> parts_ = StringUtil.splitChars(_file, '/','\\');
        FolderStruct curr_ = getCurrentFolder(parts_);
        if (curr_ == null) {
            return null;
        }
        StringList files_ = new StringList();
        String rep_ = StringUtil.replaceBackSlashDot(_file);
        for (String f: curr_.getFiles().getKeys()) {
            files_.add(rep_+f);
        }
        files_.sort();
        return files_;
    }

    @Override
    public StringList getFolders(String _file, RunnableContextEl _rCont) {
        CustList<String> parts_ = StringUtil.splitChars(_file, '/','\\');
        FolderStruct curr_ = getCurrentFolder(parts_);
        if (curr_ == null) {
            return null;
        }
        StringList files_ = new StringList();
        String rep_ = StringUtil.replaceBackSlashDot(_file);
        for (String f: curr_.getFolders().getKeys()) {
            files_.add(rep_+f);
        }
        files_.sort();
        return files_;
    }
    private FolderStruct getCurrentFolder(CustList<String> _parts) {
        FolderStruct curr_ = root;
        int nbFolders_ = _parts.size();
        for (int i = 0; i < nbFolders_; i++) {
            if (curr_ == null) {
                return null;
            }
            curr_ = curr_.getFolders().getVal(_parts.get(i));
        }
        return curr_;
    }
    private FolderStruct getParentFolder(CustList<String> _parts) {
        FolderStruct curr_ = root;
        int nbFolders_ = _parts.size()-1;
        for (int i = 0; i < nbFolders_; i++) {
            if (curr_ == null) {
                return null;
            }
            curr_ = curr_.getFolders().getVal(_parts.get(i));
        }
        return curr_;
    }

    private static boolean endsSep(String _file) {
        return _file.endsWith("/") || _file.endsWith("\\");
    }

    public FolderStruct getRoot() {
        return root;
    }
}
