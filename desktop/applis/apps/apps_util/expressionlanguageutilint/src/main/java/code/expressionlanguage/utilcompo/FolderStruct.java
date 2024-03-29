package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.PathUtil;
import code.stream.core.ContentTime;
import code.threads.AbstractThreadFactory;
import code.threads.FileStruct;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class FolderStruct {

    private final StringMap<FileStruct> files = new StringMap<FileStruct>();
    private final StringMap<FolderStruct> folders = new StringMap<FolderStruct>();
    private long lastDate;
    private final FileSystemParameterizing config;
    public FolderStruct(FileSystemParameterizing _fs, AbstractThreadFactory _threadFact) {
        setupDate(_threadFact);
        config = _fs;
    }

    public FolderStruct(FileSystemParameterizing _fs, long _lastDate) {
        lastDate = _lastDate;
        config = _fs;
    }

    public void setupDate(AbstractThreadFactory _threadFact) {
        lastDate = _threadFact.millis();
    }

    public long getLastDate() {
        return lastDate;
    }

    public static FolderStruct build(FileSystemParameterizing _fs,StringMap<ContentTime> _foldersElts, StringMap<ContentTime> _files,AbstractThreadFactory _threadFact) {
        FolderStruct root_ = new FolderStruct(_fs,_threadFact);
        for (EntryCust<String, ContentTime> f:_foldersElts.entryList()) {
            FolderStruct curr_ = root_;
            String key_ = f.getKey();
            for (String p: PathUtil.splitParts(key_)) {
                curr_ = curr_.tryAddFolder(_fs,p,f.getValue().getLastModifTime());
            }
        }
        for (EntryCust<String, ContentTime> e: _files.entryList()) {
            String key_ = e.getKey();
            StringList parts_ = PathUtil.splitParts(key_);
            int nbFolders_ = parts_.size()-1;
            FolderStruct curr_ = root_;
            for (int i = 0; i < nbFolders_; i++) {
                curr_ = curr_.folders.getVal(parts_.get(i));
            }
            ContentTime value_ = e.getValue();
            curr_.files.addEntry(parts_.last(),new FileStruct(value_.getContent(),value_.getLastModifTime()));
        }
        return root_;
    }

    private FolderStruct tryAddFolder(FileSystemParameterizing _fs, String _folderName, long _time) {
        FolderStruct folder_ = new FolderStruct(_fs,_time);
        int index_ = folders.indexOfEntry(_folderName);
        if (index_ > -1) {
            return folders.getValue(index_);
        }
        folders.addEntry(_folderName, folder_);
        return folder_;
    }

    public StringMap<FolderStruct> getFolders() {
        return folders;
    }

    public StringMap<FileStruct> getFiles() {
        return files;
    }

    public StringMap<ContentTime> exportAll() {
        StringMap<ContentTime> all_ = new StringMap<ContentTime>();
        StringMap<FolderStruct> curFolder_ = new StringMap<FolderStruct>();
        ExportedFolder export_ = export();
        for (EntryCust<String, ContentTime> f: export_.getOut().entryList()) {
            ContentTime value_ = f.getValue();
            all_.addEntry(f.getKey(), value_);
        }
        for (EntryCust<String, FolderStruct> f: export_.getFolders().entryList()) {
            FolderStruct value_ = f.getValue();
            curFolder_.addEntry(f.getKey(), value_);
        }
        while (true) {
            StringMap<FolderStruct> newFolder_ = new StringMap<FolderStruct>();
            for (EntryCust<String,FolderStruct> e: curFolder_.entryList()) {
                ExportedFolder exportLoc_ = e.getValue().export();
                for (EntryCust<String, ContentTime> f: exportLoc_.getOut().entryList()) {
                    all_.addEntry(e.getKey()+"/"+f.getKey(),f.getValue());
                }
                for (EntryCust<String, FolderStruct> f: exportLoc_.getFolders().entryList()) {
                    newFolder_.addEntry(e.getKey()+"/"+f.getKey(),f.getValue());
                }
            }
            if (newFolder_.isEmpty()) {
                break;
            }
            curFolder_ = newFolder_;
        }
        return all_;
    }
    private ExportedFolder export() {
        if (StringUtil.disjoint(files.getKeys(),folders.getKeys())) {
            StringMap<ContentTime> out_ = new StringMap<ContentTime>();
            for (EntryCust<String,FileStruct> e: files.entryList()) {
                out_.addEntry(e.getKey(),new ContentTime(e.getValue().getContent(),e.getValue().getLastDate()));
            }
            for (EntryCust<String,FolderStruct> e: folders.entryList()) {
                out_.addEntry(e.getKey(),new ContentTime(null,e.getValue().lastDate));
            }
            return new ExportedFolder(out_,folders);
        }
        StringMap<ContentTime> out_ = new StringMap<ContentTime>();
        StringMap<FolderStruct> folders_ = new StringMap<FolderStruct>();
        for (EntryCust<String,FileStruct> e: files.entryList()) {
            out_.addEntry(config.getFilePrefix()+e.getKey(),new ContentTime(e.getValue().getContent(),e.getValue().getLastDate()));
        }
        String fp_ = config.getFolderPrefix();
        for (EntryCust<String,FolderStruct> e: folders.entryList()) {
            out_.addEntry(fp_+e.getKey(),new ContentTime(null,e.getValue().lastDate));
            folders_.addEntry(fp_+e.getKey(),e.getValue());
        }
        return new ExportedFolder(out_,folders_);
    }

}
