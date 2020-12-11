package code.expressionlanguage.utilfiles;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractReporter;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.OutputType;
import code.stream.core.ReadBinFiles;
import code.stream.core.ReadFiles;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import code.util.ints.UniformingString;

import java.io.File;

public final class DefaultReporter implements AbstractReporter {
    private final AbstractNameValidating nameValidating;
    private final UniformingString uniformingString;
    private final boolean memory;

    public DefaultReporter(AbstractNameValidating _nameValidating, UniformingString _uniformingString, boolean _memory) {
        nameValidating = _nameValidating;
        uniformingString = _uniformingString;
        memory = _memory;
    }

    @Override
    public AbstractNameValidating getNameValidating() {
        return nameValidating;
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
            String absolutePath_ = StringUtil.replaceBackSlash(new File(_folderPath).getAbsolutePath());
            int last_ = absolutePath_.lastIndexOf('/');
            if (last_ > -1) {
                folderPath_ = StringUtil.replaceBackSlashDot(absolutePath_.substring(0,last_));
            }
        }
        return folderPath_;
    }

    @Override
    public boolean koPaths(String _folderPath, ExecutingOptions _exec) {
        StringList foldersConf_ = new StringList();
        for (String f: list(_exec)) {
            if (f.trim().isEmpty()) {
                return true;
            }
            int index_ = f.indexOf('/');
            if (index_ < 0) {
                foldersConf_.add(f);
            } else {
                foldersConf_.add(f.substring(0,index_));
            }
        }
        if (memory) {
            String outZip_ = _exec.getOutputZip().trim();
            int index_ = outZip_.indexOf('/');
            if (index_ < 0) {
                return true;
            }
            String folderOut_ = outZip_.substring(0, index_);
            String fileOut_ = outZip_.substring(index_+1);
            foldersConf_.add(folderOut_);
            _exec.setOutputFolder(_folderPath+folderOut_);
            _exec.setOutputZip(fileOut_);
        }
        if (foldersConf_.hasDuplicates()) {
            return true;
        }
        _exec.setOutput(StringUtil.replaceBackSlashDot(new File(_folderPath).getAbsolutePath()));
        return false;
    }
    private static StringList list(ExecutingOptions _exec) {
        return new StringList(_exec.getLogFolder(), _exec.getCoverFolder(),
                _exec.getErrorsFolder(), _exec.getFiles(), _exec.getSrcFolder(), _exec.getResources());
    }
    @Override
    public String conf(String _fileConfOrContent) {
        return StreamTextFile.contentsOfFile(_fileConfOrContent);
    }

    @Override
    public ReadBinFiles getBinFiles(String _archiveOrFolder) {
        return StreamFolderFile.getBinFiles(_archiveOrFolder);
    }

    @Override
    public ReadFiles getFiles(String _archiveOrFolder) {
        return StreamFolderFile.getFiles(_archiveOrFolder,uniformingString);
    }

    @Override
    public void coverFile(String _folder, String _fileName, String _content, RunnableContextEl _rCont) {
        saveFile(_folder, _fileName, _content);
    }

    @Override
    public void errorFile(String _folder, String _fileName, String _content, RunnableContextEl _rCont) {
        saveFile(_folder, _fileName, _content);
    }

    private void saveFile(String _folder, String _fileName, String _content) {
        String full_ = _folder + _fileName;
        int end_ = full_.lastIndexOf('/');
        if (end_ > -1) {
            String par_ = full_.substring(0, end_);
            if (!par_.isEmpty()) {
                StreamFolderFile.mkdirs(par_);
            }
        }
        StreamTextFile.saveTextFile(full_,_content);
    }
}
