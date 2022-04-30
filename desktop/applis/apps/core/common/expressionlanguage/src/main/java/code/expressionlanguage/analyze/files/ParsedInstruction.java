package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ParsedInstruction {
    private final StringBuilder builder = new StringBuilder();
    private final StringBuilder builderTrLeft = new StringBuilder();
    private final CustList<SegmentStringPart> stringParts = new CustList<SegmentStringPart>();
    private int instructionLocation;
    private int index;
    private char curChar;
    private int firstPrIndex = -1;
    private int afterOffset;
    private String after = "";
    private ResultParsedAnnots annotationsTypes;

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
        builderTrLeft.delete(0, builderTrLeft.length());
        firstPrIndex = -1;
    }

    public int getFirstPrIndex() {
        return firstPrIndex;
    }

    public void appendCh(char _ch) {
        builder.append(_ch);
        if (firstPrIndex == -1 && !StringUtil.isWhitespace(_ch)) {
            builderTrLeft.append(_ch);
            firstPrIndex = builder.length()-1;
        } else if (firstPrIndex > -1){
            builderTrLeft.append(_ch);
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

    public void parseAnnotation(InputTypeCreation _input,
                                AnalyzedPageEl _page) {
        ResultParsedAnnots annotationsTypes_ = new ResultParsedAnnots();
        KeyWords keyWords_ = _page.getKeyWords();
        String trLeft_ = builderTrLeft.toString();
        int instructionTrimLocation_ = instLoc();
        if (ParsedAnnotations.startsWithAnnot(trLeft_, keyWords_.getKeyWordClass(),keyWords_.getKeyWordInterface())) {
            // accessOffesType_ == nextIndex_ == i_ + 1;
            ParsedAnnotations par_ = new ParsedAnnotations(trLeft_, instructionTrimLocation_ + _input.getOffset());
            par_.parse(getStringParts(),keyWords_.getKeyWordClass(),keyWords_.getKeyWordInterface());
            annotationsTypes_.set(par_);
            afterOffset = par_.getIndex() - _input.getOffset();
            after = par_.getAfter();
        } else {
            afterOffset = instructionTrimLocation_;
            after = trLeft_;
        }
        annotationsTypes = annotationsTypes_;
    }

    public ResultParsedAnnots getAnnotationsTypes() {
        return annotationsTypes;
    }

    public String getAfter() {
        return after;
    }

    public int getAfterOffset() {
        return afterOffset;
    }
}
