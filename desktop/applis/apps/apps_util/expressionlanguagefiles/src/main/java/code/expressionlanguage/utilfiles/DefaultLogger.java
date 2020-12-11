package code.expressionlanguage.utilfiles;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractLogger;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.core.StringUtil;

import java.io.File;

public final class DefaultLogger implements AbstractLogger {
    private final AbstractNameValidating nameValidating;

    public DefaultLogger(AbstractNameValidating _nameValidating) {
        nameValidating = _nameValidating;
    }

    @Override
    public AbstractNameValidating getNameValidating() {
        return nameValidating;
    }

    @Override
    public void log(String _folderName,String _fileName, String _content, RunnableContextEl _cont) {
        StreamFolderFile.mkdirs(_folderName);
        String toFile_ = StringUtil.concat(_folderName,"/",_fileName);
        StreamTextFile.logToFile(toFile_, _content);
    }

}
