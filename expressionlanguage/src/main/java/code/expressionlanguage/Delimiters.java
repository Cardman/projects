package code.expressionlanguage;
import code.util.CustList;
import code.util.Numbers;

public final class Delimiters {

    private int firstPrintableChar = CustList.INDEX_NOT_FOUND_ELT;
    private int absoluteOffset;
    private int childOffest;
    private int indexBegin;
    private int indexEnd;
    private char begin;
    private char end;
    private boolean partOfString;
    private Numbers<Integer> allowedOperatorsIndexes = new Numbers<Integer>();
    private Numbers<Integer> delStringsChars = new Numbers<Integer>();

    public Numbers<Integer> getAllowedOperatorsIndexes() {
        return allowedOperatorsIndexes;
    }

    public Numbers<Integer> getDelStringsChars() {
        return delStringsChars;
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
    public char getBegin() {
        return begin;
    }
    public void setBegin(char _begin) {
        begin = _begin;
    }
    public char getEnd() {
        return end;
    }
    public void setEnd(char _end) {
        end = _end;
    }
    public boolean isPartOfString() {
        return partOfString;
    }
    public void setPartOfString(boolean _partOfString) {
        partOfString = _partOfString;
    }
}
