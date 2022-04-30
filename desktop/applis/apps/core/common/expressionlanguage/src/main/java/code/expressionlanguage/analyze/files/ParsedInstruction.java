package code.expressionlanguage.analyze.files;

import code.util.CustList;
import code.util.core.StringUtil;

public final class ParsedInstruction {
    private final StringBuilder builder = new StringBuilder();
    private final CustList<SegmentStringPart> stringParts = new CustList<SegmentStringPart>();
    private int instructionLocation;
    private int index;
    private char curChar;
    private int firstPrIndex = -1;

    public char getCurChar() {
        return curChar;
    }

    public void setCurChar(char _c) {
        this.curChar = _c;
    }

    public int instLoc() {
        if (getFirstPrIndex() >= 0) {
            return getInstructionLocation()+ getFirstPrIndex();
        }
        return getInstructionLocation();
    }
    public StringBuilder getBuilder() {
        return builder;
    }

    public CustList<SegmentStringPart> getStringParts() {
        return stringParts;
    }

    public void clear() {
        stringParts.clear();
        builder.delete(0, builder.length());
        firstPrIndex = -1;
    }

    public int getFirstPrIndex() {
        return firstPrIndex;
    }

    public void append(char _ch) {
        builder.append(_ch);
        if (firstPrIndex == -1 && !StringUtil.isWhitespace(_ch)) {
            firstPrIndex = builder.length()-1;
        }
    }

    public int getInstructionLocation() {
        return instructionLocation;
    }

    public void setInstructionLocation(int _inst) {
        this.instructionLocation = _inst;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _in) {
        this.index = _in;
    }
}
