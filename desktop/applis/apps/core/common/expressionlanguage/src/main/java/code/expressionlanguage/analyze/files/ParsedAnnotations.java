package code.expressionlanguage.analyze.files;

import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;
import code.util.core.StringUtil;

public final class ParsedAnnotations {

    private static final char BEGIN_CALLING = '(';
    private static final char END_CALLING = ')';
    private static final char ANNOT = '@';
    private final CustList<SegmentStringPart> allParts = new CustList<SegmentStringPart>();
    private final CustList<SegmentStringPart> parts = new CustList<SegmentStringPart>();
    private final CustList<ResultParsedAnnot> retAnnots = new CustList<ResultParsedAnnot>();
    private final String instruction;
    private String after = "";
    private int index;
    private final int instructionLocation;
    private int indexArobase;
    private int nbPars;
    private final StringBuilder annotation = new StringBuilder();

    public ParsedAnnotations(String _instruction, int _instructionLocation) {
        instruction = _instruction;
        instructionLocation = _instructionLocation;
    }
    public void parse(CustList<SegmentStringPart> _parts, String... _keyWordClass) {
        int lenInst_ = instruction.length();
        int j_ = 0;
        boolean endLoop_ = true;
        while (j_ < lenInst_) {
            int next_ = tryIncr(j_, _parts, _keyWordClass);
            if (next_ <= j_) {
                endLoop_ = false;
                break;
            }
            j_ = next_;
        }
        if (endLoop_) {
            index = lenInst_ + instructionLocation;
        }
    }
    private int tryIncr(int _j, CustList<SegmentStringPart> _parts, String... _keyWordClass) {
        int until_ = until(_parts, _j, annotation);
        if (until_ > _j) {
            return until_;
        }
        char cur_ = instruction.charAt(_j);
        incrOrDecr(cur_);
        if (nbPars != 0) {
            annotation.append(cur_);
            return _j + 1;
        }
        if (StringExpUtil.isTypeLeafChar(cur_)) {
            String after_ = instruction.substring(_j + 1);
            if (after_.isEmpty() || !isPart(after_.charAt(0))) {
                String afterTrim_ = after_.trim();
                if (afterTrim_.isEmpty() || afterTrim_.charAt(0) != '.' && !startsWithAnnot(afterTrim_, _keyWordClass) && afterTrim_.charAt(0) != BEGIN_CALLING) {
                    annotation.append(cur_);
                    addAnnot();
                    incrAfterAnnot(_j);
                    return _j;
                }
            }
        }
        if (cur_ == END_CALLING) {
            String after_ = instruction.substring(_j + 1).trim();
            if (after_.isEmpty() || !startsWithAnnot(after_, _keyWordClass)) {
                annotation.append(cur_);
                addAnnot();
                incrAfterAnnot(_j);
                return _j;
            }
        }
        return defBehaviour(_j, cur_);
    }

    private void incrOrDecr(char _cur) {
        if (_cur == END_CALLING) {
            nbPars--;
        }
        if (_cur == BEGIN_CALLING) {
            nbPars++;
        }
    }

    private int defBehaviour(int _j, char _cur) {
        if (_cur == ANNOT) {
            //Add annotation
            if (!annotation.toString().trim().isEmpty()) {
                addAnnot();
            }
            annotation.delete(0, annotation.length());
            indexArobase = _j;
        }
        annotation.append(_cur);
        return _j + 1;
    }

    private void addAnnot() {
        ResultParsedAnnot resAnnot_ = new ResultParsedAnnot();
        resAnnot_.set(this.indexArobase + this.instructionLocation, this.annotation.toString(), this.parts);
        this.retAnnots.add(resAnnot_);
        this.parts.clear();
    }
    private void incrAfterAnnot(int _j) {
        int lenInst_ = instruction.length();
        int j_ = _j;
        index = j_ + instructionLocation;
        index++;
        j_++;
        while (j_ < lenInst_) {
            if (!StringUtil.isWhitespace(instruction.charAt(j_))) {
                break;
            }
            index++;
            j_++;
        }
        after = instruction.substring(j_);
    }

    private int until(CustList<SegmentStringPart> _parts, int _j, StringBuilder _annotation) {
        int until_ = _j;
        for (SegmentStringPart s: _parts) {
            if (s.getBegin() == _j + instructionLocation) {
                parts.add(s);
                allParts.add(s);
                int begin_ = s.getBegin() - instructionLocation;
                int end_ = s.getEnd() - instructionLocation;
                for (int i = begin_; i < end_; i++) {
                    _annotation.append(instruction.charAt(i));
                }
                until_ = s.getEnd() - instructionLocation;
                break;
            }
        }
        return until_;
    }

    public CustList<ResultParsedAnnot> getRetAnnots() {
        return retAnnots;
    }

    public CustList<SegmentStringPart> getAllParts() {
        return allParts;
    }

    private static boolean isPart(char _char) {
        if (StringExpUtil.isTypeLeafChar(_char)) {
            return true;
        }
        if (_char == '.') {
            return true;
        }
        return _char == BEGIN_CALLING;
    }

    public String getAfter() {
        return after;
    }
    public int getIndex() {
        return index;
    }

    static boolean startsWithAnnot(String _trimmedInstruction, String... _keyWordClass) {
        if (!StringExpUtil.nextCharIs(_trimmedInstruction,0,_trimmedInstruction.length(),ANNOT)) {
            return false;
        }
        boolean exist_ = false;
        for (String k:_keyWordClass) {
            if (StringExpUtil.startsWithArobaseKeyWord(_trimmedInstruction, k)) {
                exist_ = true;
                break;
            }
        }
        return !exist_;
    }
}
