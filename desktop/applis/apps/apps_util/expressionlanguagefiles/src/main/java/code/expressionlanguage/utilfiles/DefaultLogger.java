package code.expressionlanguage.utilfiles;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.expressionlanguage.utilcompo.AbstractIssuer;
import code.expressionlanguage.utilcompo.AbstractLogger;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.stream.AbstractFileCoreStream;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.TechStreams;
import code.util.core.StringUtil;

public final class DefaultLogger implements AbstractLogger {
    private final AbstractNameValidating nameValidating;
    private AbstractIssuer issuer;
    private AbstractFileCoreStream list;
    private final TechStreams streams;

    public DefaultLogger(AbstractNameValidating _nameValidating, AbstractIssuer _issuer, AbstractFileCoreStream _list, TechStreams _streams) {
        nameValidating = _nameValidating;
        issuer = _issuer;
        list = _list;
        streams = _streams;
    }

    public AbstractIssuer getIssuer() {
        return issuer;
    }
    @Override
    public AbstractNameValidating getNameValidating() {
        return nameValidating;
    }

    @Override
    public void logErr(String _folerName, String _fileName, String _content) {
        String toFile_ = StringUtil.concat(_folerName,"/",_fileName);
        StreamFolderFile.makeParent(toFile_,list);
        StreamTextFile.logToFile(toFile_, _content,streams);
    }

    @Override
    public void log(String _folderName,String _fileName, String _content, RunnableContextEl _cont) {
        String toFile_ = StringUtil.concat(_folderName,"/",_fileName);
        StreamFolderFile.makeParent(toFile_,list);
        StreamTextFile.logToFile(toFile_, _content,streams);
    }

}
