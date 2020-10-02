package code.expressionlanguage.utilfiles;

import code.expressionlanguage.utilcompo.AbstractLogger;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.stream.StreamTextFile;
import code.util.StringList;

import java.io.File;

public final class DefaultLogger implements AbstractLogger {
    @Override
    public void log(String _folderName,String _fileName, String _content, RunnableContextEl _cont) {
        new File(_folderName).mkdirs();
        String toFile_ = StringList.concat(_folderName,"/",_fileName);
        StreamTextFile.logToFile(toFile_, _content);
    }
}
