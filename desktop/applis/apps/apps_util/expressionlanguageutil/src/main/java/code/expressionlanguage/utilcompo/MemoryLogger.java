package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.util.core.StringUtil;

import java.util.concurrent.ConcurrentHashMap;

public final class MemoryLogger implements AbstractLogger {
    private final ConcurrentHashMap<String,FileStruct> logs = new ConcurrentHashMap<String,FileStruct>();
    private final AbstractNameValidating nameValidating;

    public MemoryLogger(AbstractNameValidating _nameValidating) {
        nameValidating = _nameValidating;
    }

    @Override
    public AbstractNameValidating getNameValidating() {
        return nameValidating;
    }

    @Override
    public void log(String _folerName, String _fileName, String _content, RunnableContextEl _cont) {
        byte[] encode_ = StringUtil.encode(_content);
        FileStruct fileStruct_ = logs.putIfAbsent(_fileName, new FileStruct(encode_));
        if (fileStruct_ == null) {
            return;
        }
        byte[] old_ = fileStruct_.getContent();
        int already_ = old_.length;
        int contLen_ = encode_.length;
        byte[] merged_ = new byte[already_ + contLen_];
        for (int i = 0; i < already_; i++) {
            val(merged_, i, old_, i);
        }
        for (int i = 0; i < contLen_; i++) {
            val(merged_, i + already_, encode_, i);
        }
        fileStruct_.setDatedContent(merged_);
    }

    private static void val(byte[] _merged, int _j, byte[] _content, int _i) {
        _merged[_j] = _content[_i];
    }

    public ConcurrentHashMap<String, FileStruct> getLogs() {
        return logs;
    }
}
