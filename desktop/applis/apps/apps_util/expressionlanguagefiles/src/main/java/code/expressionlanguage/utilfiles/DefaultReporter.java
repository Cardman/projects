package code.expressionlanguage.utilfiles;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.*;
import code.gui.initialize.AbstractLightProgramInfos;
import code.stream.AbstractFileCoreStream;
import code.stream.BytesInfo;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.*;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

public final class DefaultReporter implements AbstractReporter {
    private final AbstractNameValidating nameValidating;
    private final UniformingString uniformingString;
    private final boolean memory;
    private final TechInfos threadFactory;
    private final AbstractFileCoreStream fileCoreStream;
    private final AbstractLightProgramInfos li;

    public DefaultReporter(AbstractLightProgramInfos _light, AbstractNameValidating _nameValidating, UniformingString _uniformingString, boolean _memory, TechInfos _threadFactory, AbstractFileCoreStream _fact) {
        li = _light;
        nameValidating = _nameValidating;
        uniformingString = _uniformingString;
        memory = _memory;
        threadFactory = _threadFactory;
        fileCoreStream = _fact;
    }

    @Override
    public StringMap<String> getSrc(String _archive, ExecutingOptions _exec, ReadFiles _results) {
        StringMap<String> zipFiles_ = _results.getZipFiles();
        StringMap<String> readZip_ = new StringMap<String>();
        if (_results.getType() == OutputType.FOLDER) {
            for (EntryCust<String,String> e: zipFiles_.entryList()) {
                if (!e.getKey().startsWith(_exec.getSrcFolder()+"/")) {
                    continue;
                }
                readZip_.addEntry(e.getKey(), e.getValue());
            }
            ReadFiles resultRes_ = getFiles(_archive+"/"+_exec.getResources());
            if (resultRes_.getType() == OutputType.FOLDER) {
                for (EntryCust<String,String> e: resultRes_.getZipFiles().entryList()) {
                    readZip_.addEntry(_exec.getResources()+"/"+e.getKey(), e.getValue());
                }
            }
        } else {
            for (EntryCust<String,String> e: zipFiles_.entryList()) {
                if (!e.getKey().startsWith(_exec.getSrcFolder()+"/")) {
                    continue;
                }
                readZip_.addEntry(e.getKey(), e.getValue());
            }
            for (EntryCust<String,String> e: zipFiles_.entryList()) {
                if (!e.getKey().startsWith(_exec.getResources()+"/")) {
                    continue;
                }
                readZip_.addEntry(e.getKey(), e.getValue());
            }
        }
        return readZip_;
    }

    @Override
    public String getFolderPath(String _folderPath, ExecutingOptions _exec,ReadFiles _results) {
        String folderPath_ = StringUtil.replaceBackSlashDot(_folderPath);
        if (_results.getType() != OutputType.FOLDER) {
            String absolutePath_ = StringUtil.replaceBackSlash(fileCoreStream.newFile(_folderPath).getAbsolutePath());
            int last_ = absolutePath_.lastIndexOf('/');
            if (last_ > -1) {
                folderPath_ = StringUtil.replaceBackSlashDot(absolutePath_.substring(0,last_));
            }
        }
        return folderPath_;
    }

    @Override
    public boolean koPaths(String _folderPath, ExecutingOptions _exec) {
        _exec.setLightProgramInfos(li);
        StringList foldersConf_ = new StringList();
        if (!nameValidating.okPath(_exec.getMainThread(),'/','\\')) {
            return true;
        }
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
        _exec.setCoverFolder(StringUtil.replaceBackSlashDot(_exec.getCoverFolder()));
        _exec.setErrorsFolder(StringUtil.replaceBackSlashDot(_exec.getErrorsFolder()));
        if (memory) {
            String outZip_ = _exec.getOutputZip().trim();
            String normal_ = StringUtil.replaceBackSlash(outZip_);
            int index_ = normal_.indexOf('/');
            if (index_ < 0) {
                return true;
            }
            if (!nameValidating.okPath(normal_,'/','\\')) {
                return true;
            }
            String folderOut_ = normal_.substring(0, index_);
            String fileOut_ = normal_.substring(index_+1);
            foldersConf_.add(folderOut_);
            _exec.setOutputFolder(_folderPath+folderOut_);
            _exec.setOutputZip(fileOut_);
        }
        if (foldersConf_.hasDuplicates()) {
            return true;
        }
        _exec.setOutput(StringUtil.replaceBackSlashDot(fileCoreStream.newFile(_folderPath).getAbsolutePath()));
        if (!memory) {
            _exec.setBaseFiles(StringUtil.replaceBackSlashDot(_exec.getOutput()+_exec.getFiles()));
        } else {
            _exec.setBaseFiles("/");
        }
        return false;
    }
    private static StringList list(ExecutingOptions _exec) {
        return new StringList(_exec.getLogFolder(), _exec.getCoverFolder(),
                _exec.getErrorsFolder(), _exec.getFiles(), _exec.getSrcFolder(), _exec.getResources());
    }
    @Override
    public String conf(String _fileConfOrContent) {
        return StreamTextFile.contentsOfFile(_fileConfOrContent,fileCoreStream, threadFactory.getTechStreams());
    }

    @Override
    public String confTxt(String _fileConfOrContent) {
        return _fileConfOrContent;
    }

    @Override
    public ReadBinFiles getBinFiles(String _archiveOrFolder) {
        return StreamFolderFile.getBinFiles(_archiveOrFolder,fileCoreStream,threadFactory.getTechStreams());
    }

    @Override
    public ReadFiles getFiles(String _archiveOrFolder) {
        return StreamFolderFile.getFiles(_archiveOrFolder,uniformingString,fileCoreStream,threadFactory.getTechStreams());
    }

    @Override
    public void coverFile(ExecutingOptions _ex, String _fileName, String _content) {
        saveFile(_ex.getOutput()+_ex.getCoverFolder(), _fileName, _content);
    }

    @Override
    public void errorFile(ExecutingOptions _ex, String _fileName, String _content) {
        saveFile(_ex.getOutput()+_ex.getErrorsFolder(), _fileName, _content);
    }

    @Override
    public BytesInfo exportErrs(ExecutingOptions _ex, AbstractLogger _log) {
        StringMap<ContentTime> out_ = MemoryReporter.exportErr(_log,threadFactory.getThreadFactory());
        if (!out_.isEmpty()) {
            return new BytesInfo(threadFactory.getZipFact().zipBinFiles(out_),false);
        }
        return new BytesInfo(new byte[0],true);
    }

    @Override
    public BytesInfo export(ExecutingOptions _ex,AbstractFileSystem _sys, AbstractLogger _log) {
        StringMap<ContentTime> out_ = MemoryReporter.exportSysLoggs(_ex, _sys, _log,threadFactory.getThreadFactory());
        if (!out_.isEmpty()) {
            return new BytesInfo(threadFactory.getZipFact().zipBinFiles(out_),false);
        }
        return new BytesInfo(new byte[0],true);
    }

    private void saveFile(String _folder, String _fileName, String _content) {
        String full_ = _folder + _fileName;
        StreamFolderFile.makeParent(full_,fileCoreStream);
        StreamTextFile.saveTextFile(full_,_content,threadFactory.getTechStreams());
    }
}
