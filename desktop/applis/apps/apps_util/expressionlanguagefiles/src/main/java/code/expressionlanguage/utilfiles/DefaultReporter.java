package code.expressionlanguage.utilfiles;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractReporter;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.ReadBinFiles;
import code.stream.core.ReadFiles;
import code.util.StringList;
import code.util.ints.UniformingString;

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
    public boolean koOutZip(String _folderPath,StringList _list, ExecutingOptions _exec) {
        if (memory) {
            String outZip_ = _exec.getOutputZip().trim();
            int index_ = outZip_.indexOf('/');
            if (index_ < 0) {
                return true;
            }
            String folderOut_ = outZip_.substring(0, index_);
            String fileOut_ = outZip_.substring(index_+1);
            _list.add(folderOut_);
            _exec.setOutputFolder(_folderPath+folderOut_);
            _exec.setOutputZip(fileOut_);
        }
        return false;
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
