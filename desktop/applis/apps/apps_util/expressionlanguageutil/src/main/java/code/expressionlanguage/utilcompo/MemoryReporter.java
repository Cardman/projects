package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.initialize.AbstractLightProgramInfos;
import code.stream.BytesInfo;
import code.stream.core.*;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThreadFactory;
import code.threads.FileStruct;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.DefaultUniformingString;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

import java.util.Map.Entry;

public final class MemoryReporter implements AbstractReporter {
    private final byte[] conf;
    private final BytesInfo src;
    private final BytesInfo files;
    private final AbstractNameValidating nameValidating;
    private final UniformingString uniformingString;
    private final AbstractZipFact zipFact;
    private final AbstractThreadFactory threadFactory;
//    private final AbstractLightProgramInfos li;
    private final StringMap<ContentTime> reports = new StringMap<ContentTime>();

    public MemoryReporter(AbstractLightProgramInfos _light, byte[] _conf, BytesInfo _src, BytesInfo _files, AbstractNameValidating _nameValidating, DefaultUniformingString _uniformingString) {
        conf = _conf;
        this.src = _src;
        this.files = _files;
        nameValidating = _nameValidating;
        uniformingString = _uniformingString;
//        li = _light;
        zipFact = _light.getZipFact();
        threadFactory = _light.getThreadFactory();
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
//        _exec.setLightProgramInfos(li);
        if (!nameValidating.okPath(_exec.getOutputZip(),'/','\\')) {
            return true;
        }
        if (!nameValidating.okPath(_exec.getMainThread(),'/','\\')) {
            return true;
        }
        StringList foldersConf_ = new StringList();
        for (String f: list(_exec)) {
            String normal_ = StringUtil.replaceBackSlash(f);
            if (!nameValidating.okPath(normal_,'/','\\')) {
                return true;
            }
            int index_ = normal_.indexOf('/');
            if (index_ < 0) {
                foldersConf_.add(normal_);
            } else {
                foldersConf_.add(normal_.substring(0,index_));
            }
        }
        _exec.setOutputFolder(_folderPath);
        _exec.setBaseFiles("/");
        return foldersConf_.hasDuplicates();
    }
    private static StringList list(ExecutingOptions _exec) {
        return new StringList(_exec.getLogFolder(), _exec.getCoverFolder(),
                _exec.getErrorsFolder(), _exec.getFiles(), _exec.getSrcFolder(), _exec.getResources());
    }
    @Override
    public String conf(String _fileConfOrContent) {
        return tryDecode(getConf());
    }
    @Override
    public String confTxt(String _fileConfOrContent) {
        return tryDecode(getConf());
    }

    public byte[] getConf() {
        return conf;
    }

    public static String tryDecode(byte[] _content) {
        if (_content == null) {
            return null;
        }
        return StringUtil.decode(_content);
    }

    @Override
    public ReadBinFiles getBinFiles(String _archiveOrFolder) {
        return StreamZipFile.getZippedBinFiles(files, zipFact);
    }

    @Override
    public ReadFiles getFiles(String _archiveOrFolder) {
        return StreamZipFile.getZippedFiles(uniformingString,src, zipFact);
    }

    @Override
    public void coverFile(ExecutingOptions _ex, String _fileName, String _content) {
        String coversFolder_ = _ex.getCoverFolder();
        reports.addEntry(coversFolder_+"/"+_fileName,new ContentTime(StringUtil.encode(_content), threadFactory.millis()));
    }

    @Override
    public void errorFile(ExecutingOptions _ex, String _fileName, String _content) {
        String errorsFolder_ = _ex.getErrorsFolder();
        reports.addEntry(errorsFolder_+"/"+_fileName,new ContentTime(StringUtil.encode(_content), threadFactory.millis()));
    }

    @Override
    public BytesInfo exportErrs(ExecutingOptions _ex, AbstractLogger _log) {
        StringMap<ContentTime> out_ = exportErr(_log, threadFactory);
        out_.addAllEntries(reports);
        return redirect(out_, zipFact);
    }

    @Override
    public BytesInfo export(ExecutingOptions _ex,AbstractFileSystem _sys,AbstractLogger _log) {
        StringMap<ContentTime> out_ = exportSysLoggs(_ex, _sys, _log, threadFactory);
        out_.addAllEntries(reports);
        return redirect(out_, zipFact);
    }

    public static BytesInfo redirect(StringMap<ContentTime> _o, AbstractZipFact _zip) {
        if (!_o.isEmpty()) {
            return new BytesInfo(_zip.zipBinFiles(_o), false);
        }
        return new BytesInfo(new byte[0],true);
    }

    public static StringMap<ContentTime> exportErr(AbstractLogger _log, AbstractThreadFactory _threadFact) {
        StringMap<ContentTime> out_ = new StringMap<ContentTime>();
        if (_log instanceof MemoryLogger) {
            String errFile_ = ((MemoryLogger) _log).getErrFile();
            if (!errFile_.isEmpty()) {
                String errs_ = ((MemoryLogger) _log).getErrs();
                out_.addEntry(errFile_,new ContentTime(StringUtil.encode(errs_),_threadFact.millis()));
            }
        }
        return out_;
    }
    public static StringMap<ContentTime> exportSysLoggs(ExecutingOptions _ex, AbstractFileSystem _sys, AbstractLogger _log, AbstractThreadFactory _threadFact) {
        StringMap<ContentTime> out_ = exportErr(_log,_threadFact);
        if (_log instanceof MemoryLogger) {
            AbstractConcurrentMap<String, FileStruct> logs_ = ((MemoryLogger) _log).getLogs();
            for (Entry<String, FileStruct> e: logs_.entrySet()) {
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
    public static void buildError(ReportedMessages _reportedMessages, ExecutingOptions _exec, FileInfos _infos, String _time) {
        AbstractLogger logger_ = _infos.getLogger();
        if (_reportedMessages.notAllEmptyErrors()) {
            String folder_ = _exec.getOutput()+_exec.getLogFolder();
            String dtPart_ = _time +_exec.getOutputTxt();
            logger_.logErr(folder_,"_"+dtPart_, _time +":"+_reportedMessages.displayErrors());
            logger_.logErr(folder_,"_"+dtPart_, _time +":"+_reportedMessages.displayWarnings());
            logger_.logErr(folder_,"_"+dtPart_, _time +":"+_reportedMessages.displayStdErrors());
            logger_.logErr(folder_,"_"+dtPart_, _time +":"+_reportedMessages.displayMessageErrors());
        }
        buildWarning(_reportedMessages, _exec, _infos, _time);
    }

    public static void buildWarning(ReportedMessages _reportedMessages, ExecutingOptions _exec, FileInfos _infos, String _time) {
        AbstractLogger logger_ = _infos.getLogger();
        if (!_reportedMessages.isEmptyWarnings()) {
            String folder_ = _exec.getOutput()+_exec.getLogFolder();
            String dtPart_ = _time +_exec.getOutputTxt();
            logger_.logErr(folder_,"_"+dtPart_, _time +":"+_reportedMessages.displayWarnings());
        }
    }
}
