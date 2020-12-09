package code.expressionlanguage.utilfiles;

import code.expressionlanguage.utilcompo.AbstractReporter;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.ReadFiles;
import code.util.StringMap;
import code.util.ints.UniformingString;

import java.io.File;

public final class DefaultReporter implements AbstractReporter {
    private final UniformingString uniformingString;

    public DefaultReporter(UniformingString _uniformingString) {
        uniformingString = _uniformingString;
    }

    @Override
    public String conf(String _fileConfOrContent) {
        return StreamTextFile.contentsOfFile(_fileConfOrContent);
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
                new File(par_).mkdirs();
            }
        }
        StreamTextFile.saveTextFile(full_,_content);
    }
}
