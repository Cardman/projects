package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.blocks.BracedBlock;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ParsedInstruction {
    private final StringBuilder builderAfter = new StringBuilder();
    private final StringBuilder builderTrLeft = new StringBuilder();
    private final CustList<SegmentStringPart> stringParts = new CustList<SegmentStringPart>();
    private int instructionLocation;
    private int index;
    private char curChar;
    private int firstPrIndex = -1;
    private int afterOffset;
    private int lenInstr;
    private ResultParsedAnnots annotationsTypes;
    private EndInstruction endInstruction = EndInstruction.NONE;
    private BracedBlock currentParent;
    private String packageName = "";
    private boolean parsed;
    private boolean emptyInstr;

    public char getCurChar() {
        return curChar;
    }

    public void setCurChar(char _c) {
        this.curChar = _c;
    }

    public int instLoc() {
        return instLoc(getInstructionLocation());
    }

    public int instLocAfter() {
        return afterOffset;
    }

    public int instLoc(int _loc) {
        if (getFirstPrIndex() >= 0) {
            return _loc+ getFirstPrIndex();
        }
        return _loc;
    }

    public CustList<SegmentStringPart> getStringParts() {
        return stringParts;
    }

    public void clear() {
        stringParts.clear();
        lenInstr = 0;
        builderAfter.delete(0, builderAfter.length());
        builderTrLeft.delete(0, builderTrLeft.length());
        firstPrIndex = -1;
        parsed = false;
    }

    public int getFirstPrIndex() {
        return firstPrIndex;
    }

    public void appendCh(char _ch) {
        lenInstr++;
        if (firstPrIndex == -1 && !StringUtil.isWhitespace(_ch)) {
            appendPart(_ch);
            firstPrIndex = lenInstr-1;
            afterOffset = instructionLocation+firstPrIndex;
        } else if (firstPrIndex > -1){
            appendPart(_ch);
        }
    }
    private void appendPart(char _ch) {
        if (parsed) {
            builderAfter.append(_ch);
            return;
        }
        builderTrLeft.append(_ch);
    }

    public int getLenInstr() {
        return lenInstr;
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

    public void parseAnnotation(InputTypeCreation _input, KeyWords _keyWords) {
        if (parsed) {
            return;
        }
        parsed = true;
        ResultParsedAnnots annotationsTypes_ = new ResultParsedAnnots();
        String trLeft_ = builderTrLeft.toString();
        int instructionTrimLocation_ = instLoc();
        String after_;
        if (ParsedAnnotations.startsWithAnnot(trLeft_, _keyWords.getKeyWordClass(), _keyWords.getKeyWordInterface())) {
            // accessOffesType_ == nextIndex_ == i_ + 1;
            afterOffset = next(0,annotationsTypes_,_input.getOffset(),getStringParts(),instructionTrimLocation_,trLeft_,_keyWords.getKeyWordClass(), _keyWords.getKeyWordInterface());
            after_ = annotationsTypes_.getPre();
        } else {
            afterOffset = instructionTrimLocation_;
            after_ = trLeft_;
        }
        builderAfter.append(after_);
        annotationsTypes = annotationsTypes_;
    }

    public static int next(int _delta, ResultParsedAnnots _annotations, int _offset, CustList<SegmentStringPart> _parts, int _j, String _part, String... _keyWordClass) {
        ParsedAnnotations parse_ = new ParsedAnnotations(_part, _delta + _j + _offset);
        parse_.parse(_parts,_keyWordClass);
        _annotations.set(parse_);
        return parse_.getIndex() - _delta - _offset;
    }

    public ResultParsedAnnots getAnnotationsTypes() {
        return annotationsTypes;
    }

    public StringBuilder getBuilderAfter() {
        return builderAfter;
    }

    public String getAfter() {
        return getBuilderAfter().toString();
    }

    public int getAfterOffset() {
        return afterOffset;
    }

    public EndInstruction getEndInstruction() {
        return endInstruction;
    }

    public void setEndInstruction(EndInstruction _end) {
        this.endInstruction = _end;
    }

    public BracedBlock getCurrentParent() {
        return currentParent;
    }

    public void setCurrentParent(BracedBlock _cur) {
        this.currentParent = _cur;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String _p) {
        this.packageName = _p;
    }

    public boolean isEmptyInstr() {
        return emptyInstr;
    }

    public void setEmptyInstr(boolean _e) {
        this.emptyInstr = _e;
    }
}
