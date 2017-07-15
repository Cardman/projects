package code.maths.litteral;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.Numbers;

public class Delimiters {

    private int firstPrintableChar = CustList.INDEX_NOT_FOUND_ELT;
    private int absoluteOffset;
    private int childOffest;
    private int indexBegin;
    private int indexEnd;
    private NatTreeMap<Integer,Integer> delimitersStringsChars = new NatTreeMap<Integer,Integer>();
    private NatTreeMap<Integer,Integer> callings = new NatTreeMap<Integer,Integer>();
    public boolean inStringOrCharConst(int _index) {
        for (EntryCust<Integer,Integer> e: delimitersStringsChars.entryList()) {
            if (e.getKey() <= _index && e.getValue() >= _index) {
                return true;
            }
        }
        return false;
    }
    public boolean inSameStringOrCharConst(int _index, int _indexTwo) {
        for (EntryCust<Integer,Integer> e: delimitersStringsChars.entryList()) {
            if (e.getKey() <= _index && e.getValue() >= _index) {
                if (e.getKey() <= _indexTwo && e.getValue() >= _indexTwo) {
                    return true;
                }
            }
        }
        return false;
    }
    public Numbers<Integer> getCalls(int _index) {
        Numbers<Integer> nbs_ = new Numbers<Integer>();
        for (EntryCust<Integer,Integer> e: callings.entryList()) {
            if (e.getKey() <= _index && e.getValue() >= _index) {
                nbs_.add(e.getKey());
            }
        }
        return nbs_;
    }
    public int nbCalls(int _index) {
        int nbCalls_ = 0;
        for (EntryCust<Integer,Integer> e: callings.entryList()) {
            if (e.getKey() <= _index && e.getValue() >= _index) {
                nbCalls_++;
            }
        }
        return nbCalls_;
    }

    public int getFirstPrintableChar() {
        return firstPrintableChar;
    }
    public void setFirstPrintableChar(int _firstPrintableChar) {
        firstPrintableChar = _firstPrintableChar;
    }

    public int getAbsoluteOffset() {
        return absoluteOffset;
    }
    public void setAbsoluteOffset(int _absoluteOffset) {
        absoluteOffset = _absoluteOffset;
    }
    public int getChildOffest() {
        return childOffest;
    }
    public void setChildOffest(int _childOffest) {
        childOffest = _childOffest;
    }
    public int getIndexBegin() {
        return indexBegin;
    }
    public void setIndexBegin(int _indexBegin) {
        indexBegin = _indexBegin;
    }
    public int getIndexEnd() {
        return indexEnd;
    }
    public void setIndexEnd(int _indexEnd) {
        indexEnd = _indexEnd;
    }
    public NatTreeMap<Integer, Integer> getDelimitersStringsChars() {
        return delimitersStringsChars;
    }
    public void setDelimitersStringsChars(
            NatTreeMap<Integer, Integer> _delimitersStringsChars) {
        delimitersStringsChars = _delimitersStringsChars;
    }
    public NatTreeMap<Integer, Integer> getCallings() {
        return callings;
    }
    public void setCallings(NatTreeMap<Integer, Integer> _callings) {
        callings = _callings;
    }
}
