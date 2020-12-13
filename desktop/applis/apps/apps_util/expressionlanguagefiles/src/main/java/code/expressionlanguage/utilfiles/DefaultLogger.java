package code.expressionlanguage.utilfiles;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractIssuer;
import code.expressionlanguage.utilcompo.AbstractLogger;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.core.StringUtil;

public final class DefaultLogger implements AbstractLogger {
    private final AbstractNameValidating nameValidating;
    private AbstractIssuer issuer;

    public DefaultLogger(AbstractNameValidating _nameValidating, AbstractIssuer _issuer) {
        nameValidating = _nameValidating;
        issuer = _issuer;
    }

    public AbstractIssuer getIssuer() {
        return issuer;
    }
    @Override
    public AbstractNameValidating getNameValidating() {
        return nameValidating;
    }

    @Override
    public void logErr(String _folerName, String _fileName, String _content, RunnableContextEl _cont) {
        String toFile_ = StringUtil.concat(_folerName,"/",_fileName);
        StreamFolderFile.makeParent(toFile_);
        StreamTextFile.logToFile(toFile_, _content);
    }

    @Override
    public void log(String _folderName,String _fileName, String _content, RunnableContextEl _cont) {
        String toFile_ = StringUtil.concat(_folderName,"/",_fileName);
        StreamFolderFile.makeParent(toFile_);
        StreamTextFile.logToFile(toFile_, _content);
    }

}
