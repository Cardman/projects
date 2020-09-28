package code.formathtml.util;

import code.formathtml.RendBlock;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.errors.RendAnalysisMessages;
import code.util.EntryCust;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class AnalyzingDoc {
    private StringList languages = new StringList();

    private AnaRendBlock currentBlock;
    private String internGlobalClass="";
    private String attribute="";
    private String fileName="";
    private AnaRendDocumentBlock currentDoc;
    private RendAnalysisMessages rendAnalysisMessages = new RendAnalysisMessages();

    private int nextIndex;

    public String getLocationFile(String _fileName, int _sum) {
        return StringList.concat(Integer.toString(_sum));
    }

    public static int getSum(int _offset, int _glOffset, RendBlock _currentBlock, String _attribute) {
        int delta_ = getDelta(_offset, _attribute, _currentBlock.getEscapedChars());
        return _glOffset+_offset+delta_;
    }

    public int getSum(int _offset) {
        if (currentBlock == null) {
            return 0;
        }
        int delta_ = getDelta(_offset, attribute, currentBlock.getEscapedChars());
        return _offset+delta_;
    }

    private static int getDelta(int _offset, String _attribute, StringMap<IntTreeMap<Integer>> _escapedChars) {
        int delta_ = 0;
        IntTreeMap< Integer> esc_ = getEscapedChars(_attribute, _escapedChars);
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
    private static IntTreeMap<Integer> getEscapedChars(String _attribute, StringMap<IntTreeMap<Integer>> _escapedChars) {
        for (EntryCust<String, IntTreeMap< Integer>> t: _escapedChars.entryList()) {
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

    public AnaRendBlock getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(AnaRendBlock currentBlock) {
        this.currentBlock = currentBlock;
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

    public AnaRendDocumentBlock getCurrentDoc() {
        return currentDoc;
    }

    public void setCurrentDoc(AnaRendDocumentBlock current) {
        this.currentDoc = current;
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

}
