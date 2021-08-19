package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThreadFactory;
import code.threads.FileStruct;
import code.util.core.StringUtil;

public final class MemoryLogger implements AbstractLogger {
    private final AbstractConcurrentMap<String, FileStruct> logs;
    private final AbstractNameValidating nameValidating;
    private String errFile = "";
    private String errs = "";
    private AbstractIssuer issuer;
    private final AbstractThreadFactory threadFactory;

    public MemoryLogger(AbstractNameValidating _nameValidating, AbstractIssuer _issuer, AbstractThreadFactory _threadFact) {
        nameValidating = _nameValidating;
        issuer = _issuer;
        threadFactory = _threadFact;
        logs = _threadFact.newMapStringFileStruct();
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
        errFile = _fileName;
        errs += _content;
    }

    @Override
    public void log(String _folerName, String _fileName, String _content, RunnableContextEl _cont) {
        byte[] encode_ = StringUtil.encode(_content);
        FileStruct fileStruct_ = logs.putIfAbsent(_fileName, new FileStruct(encode_,threadFactory));
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
        fileStruct_.setDatedContent(merged_,threadFactory);
    }

    private static void val(byte[] _merged, int _j, byte[] _content, int _i) {
        _merged[_j] = _content[_i];
    }

    public String getErrFile() {
        return errFile;
    }

    public String getErrs() {
        return errs;
    }

    public AbstractConcurrentMap<String, FileStruct> getLogs() {
        return logs;
    }
}
