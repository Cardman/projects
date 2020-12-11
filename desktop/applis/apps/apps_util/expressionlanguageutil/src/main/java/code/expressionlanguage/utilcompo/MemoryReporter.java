package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.stream.core.ContentTime;
import code.stream.core.ReadBinFiles;
import code.stream.core.ReadFiles;
import code.stream.core.StreamZipFile;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MemoryReporter implements AbstractReporter {
    private final byte[] conf;
    private final byte[] src;
    private final byte[] files;
    private final AbstractNameValidating nameValidating;
    private final UniformingString uniformingString = new DefaultUniformingString();
    private StringMap<ContentTime> reports = new StringMap<ContentTime>();

    public MemoryReporter(byte[] _conf, byte[] _src, byte[] _files, AbstractNameValidating _nameValidating) {
        conf = _conf;
        this.src = _src;
        this.files = _files;
        nameValidating = _nameValidating;
    }

    @Override
    public AbstractNameValidating getNameValidating() {
        return nameValidating;
    }

    @Override
    public StringMap<String> getSrc(String _archive, ExecutingOptions _exec, ReadFiles _results) {
        StringMap<String> zipFiles_ = _results.getZipFiles();
        StringMap<String> readZip_ = new StringMap<String>();
        for (EntryCust<String,String> e: zipFiles_.entryList()) {
            readZip_.addEntry(e.getKey(), e.getValue());
        }
        return readZip_;
    }

    @Override
    public String getFolderPath(String _folderPath, ExecutingOptions _exec, ReadFiles _results) {
        return _folderPath;
    }

    @Override
    public boolean koPaths(String _folderPath, ExecutingOptions _exec) {
        if (!nameValidating.okPath(_exec.getOutputZip(),'/','\\')) {
            return true;
        }
        StringList foldersConf_ = new StringList();
        for (String f: list(_exec)) {
            if (!nameValidating.okPath(f,'/','\\')) {
                return true;
            }
            int index_ = f.indexOf('/');
            if (index_ < 0) {
                foldersConf_.add(f);
            } else {
                foldersConf_.add(f.substring(0,index_));
            }
        }
        return foldersConf_.hasDuplicates();
    }
    private static StringList list(ExecutingOptions _exec) {
        return new StringList(_exec.getLogFolder(), _exec.getCoverFolder(),
                _exec.getErrorsFolder(), _exec.getFiles(), _exec.getSrcFolder(), _exec.getResources());
    }
    @Override
    public String conf(String _fileConfOrContent) {
        return StringUtil.decode(conf);
    }

    @Override
    public ReadBinFiles getBinFiles(String _archiveOrFolder) {
        return StreamZipFile.getZippedBinFiles(files);
    }

    @Override
    public ReadFiles getFiles(String _archiveOrFolder) {
        return StreamZipFile.getZippedFiles(uniformingString,src);
    }

    @Override
    public void coverFile(String _folder, String _fileName, String _content, RunnableContextEl _rCont) {
        String errorsFolder_ = _rCont.getExecutingOptions().getCoverFolder();
        reports.addEntry(errorsFolder_+"/"+_fileName,new ContentTime(StringUtil.encode(_content),System.currentTimeMillis()));
    }

    @Override
    public void errorFile(String _folder, String _fileName, String _content, RunnableContextEl _rCont) {
        String errorsFolder_ = _rCont.getExecutingOptions().getErrorsFolder();
        reports.addEntry(errorsFolder_+"/"+_fileName,new ContentTime(StringUtil.encode(_content),System.currentTimeMillis()));
    }

    @Override
    public byte[] export(ExecutingOptions _ex,AbstractFileSystem _sys,AbstractLogger _log) {
        StringMap<ContentTime> out_ = exportSysLoggs(_ex, _sys, _log);
        out_.addAllEntries(reports);
        if (!out_.isEmpty()) {
            return StreamZipFile.zipBinFiles(out_);
        }
        return null;
    }

    public static StringMap<ContentTime> exportSysLoggs(ExecutingOptions _ex, AbstractFileSystem _sys, AbstractLogger _log) {
        StringMap<ContentTime> out_ = new StringMap<ContentTime>();
        if (_log instanceof MemoryLogger) {
            ConcurrentHashMap<String, FileStruct> logs_ = ((MemoryLogger) _log).getLogs();
            for (Map.Entry<String, FileStruct> e: logs_.entrySet()) {
                String key_ = e.getKey();
                String toFile_ = StringUtil.concat(_ex.getLogFolder(),"/",key_);
                FileStruct value_ = e.getValue();
                out_.addEntry(toFile_,new ContentTime(value_.getContent(),value_.getLastDate()));
            }
        }
        if (_sys instanceof MemoryFileSystem) {
            StringMap<ContentTime> stringMap_ = ((MemoryFileSystem) _sys).getRoot().exportAll();
            for (EntryCust<String, ContentTime> e: stringMap_.entryList()) {
                ContentTime bytes_ = e.getValue();
                String key_ = e.getKey();
                out_.addEntry(_ex.getFiles()+"/"+key_,bytes_);
            }
        }
        return out_;
    }
}
