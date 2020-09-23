package code.formathtml.util;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.RendBlock;
import code.formathtml.RendDocumentBlock;
import code.formathtml.errors.RendAnalysisMessages;
import code.util.EntryCust;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class AnalyzingDoc {
    private StringList languages = new StringList();

    private RendBlock currentBlock;
    private String internGlobalClass="";
    private String attribute="";
    private String fileName="";
    private RendDocumentBlock currentDoc;
    private RendAnalysisMessages rendAnalysisMessages = new RendAnalysisMessages();

    private int nextIndex;
    private OperationNode currentRoot;

    public String getLocationFile(String _fileName, int _sum) {
        return StringList.concat(Integer.toString(_sum));
    }

    public static int getSum(int _offset, int _glOffset, RendBlock _currentBlock, String _attribute) {
        int delta_ = getDelta(_offset, _currentBlock, _attribute);
        return _glOffset+_offset+delta_;
    }

    public int getSum(int _offset) {
        if (currentBlock == null) {
            return 0;
        }
        int delta_ = getDelta(_offset, currentBlock, attribute);
        return _offset+delta_;
    }

    private static int getDelta(int _offset, RendBlock _currentBlock, String _attribute) {
        int delta_ = 0;
        IntTreeMap< Integer> esc_ = getEscapedChars(_currentBlock, _attribute);
        if (esc_ != null) {
            int nbIndexes_ = getIndexesCount(esc_, _offset);
            for (int i = 0; i < nbIndexes_; i++) {
                delta_ += esc_.getValue(i);
            }
        }
        return delta_;
    }

    private static int getIndexesCount(IntTreeMap< Integer> _t, int _offset) {
        int delta_ = 0;
        int count_ = 0;
        for (EntryCust<Integer, Integer> e: _t.entryList()) {
            if (e.getKey() - delta_ >= _offset) {
                return count_;
            }
            delta_ += e.getValue();
            count_++;
        }
        return count_;
    }
    private static IntTreeMap<Integer> getEscapedChars(RendBlock _currentBlock, String _attribute) {
        StringMap<IntTreeMap<Integer>> escapedChars_ = _currentBlock.getEscapedChars();
        for (EntryCust<String, IntTreeMap< Integer>> t: escapedChars_.entryList()) {
            String c_ = t.getKey();
            if (!StringList.quickEq(c_, _attribute)) {
                continue;
            }
            return t.getValue();
        }
        return null;
    }

    public boolean isInternGlobal() {
        return !getInternGlobalClass().isEmpty();
    }

    public void setAttribute(String _attribute) {
        attribute = _attribute;
    }

    public StringList getLanguages() {
        return languages;
    }

    public void setLanguages(StringList _languages) {
        languages = _languages;
    }

    public RendBlock getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(RendBlock _currentBlock) {
        currentBlock = _currentBlock;
    }

    public String getInternGlobalClass() {
        return internGlobalClass;
    }

    public void setInternGlobalClass(String _internGlobalClass) {
        internGlobalClass = _internGlobalClass;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

    public RendDocumentBlock getCurrentDoc() {
        return currentDoc;
    }

    public void setCurrentDoc(RendDocumentBlock _currentDoc) {
        currentDoc = _currentDoc;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }

    public RendAnalysisMessages getRendAnalysisMessages() {
        return rendAnalysisMessages;
    }

    public void setRendAnalysisMessages(RendAnalysisMessages rendAnalysisMessages) {
        this.rendAnalysisMessages = rendAnalysisMessages;
    }

    public OperationNode getCurrentRoot() {
        return currentRoot;
    }

    public void setCurrentRoot(OperationNode currentRoot) {
        this.currentRoot = currentRoot;
    }
}
