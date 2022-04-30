package code.expressionlanguage.analyze.files;

import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ParsedAnnotations {

    private static final char BEGIN_CALLING = '(';
    private static final char END_CALLING = ')';
    private static final char ANNOT = '@';
    private final Ints annotationsIndexes = new Ints();
    private final StringList annotations = new StringList();
    private final CustList<SegmentStringPart> parts = new CustList<SegmentStringPart>();
    private final String instruction;
    private String after = "";
    private int index;
    private final int instructionLocation;

    public ParsedAnnotations(String _instruction, int _instructionLocation) {
        instruction = _instruction;
        instructionLocation = _instructionLocation;
    }
    public void parse(CustList<SegmentStringPart> _parts, String... _keyWordClass) {
        int lenInst_ = instruction.length();
        int j_ = 0;
        int indexArobase_ = 0;
        int nbPars_ = 0;
        StringBuilder annotation_ = new StringBuilder();
        boolean endLoop_ = true;
        while (j_ < lenInst_) {
            int until_ = until(_parts, j_, annotation_);
            if (until_ > j_) {
                j_ = until_;
                continue;
            }
            char cur_ = instruction.charAt(j_);
            if (cur_ == END_CALLING) {
                nbPars_--;
            }
            if (cur_ == BEGIN_CALLING) {
                nbPars_++;
            }
            if (nbPars_ == 0) {
                if (StringExpUtil.isTypeLeafChar(cur_)) {
                    String after_ = instruction.substring(j_+1);
                    if (after_.isEmpty() || !isPart(after_.charAt(0))) {
                        String afterTrim_ = after_.trim();
                        if (afterTrim_.isEmpty() || afterTrim_.charAt(0) != '.' && !startsWithAnnot(afterTrim_,_keyWordClass) && afterTrim_.charAt(0) != BEGIN_CALLING) {
                            annotation_.append(cur_);
                            annotations.add(annotation_.toString());
                            annotationsIndexes.add(indexArobase_ + instructionLocation);
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
                            endLoop_ = false;
                            after = instruction.substring(j_);
                            break;
                        }
                    }
                }
                if (cur_ == END_CALLING) {
                    String after_ = instruction.substring(j_+1).trim();
                    if (after_.isEmpty() || !startsWithAnnot(after_,_keyWordClass)) {
                        annotation_.append(cur_);
                        annotations.add(annotation_.toString());
                        annotationsIndexes.add(indexArobase_ + instructionLocation);
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
                        endLoop_ = false;
                        after = instruction.substring(j_);
                        break;
                    }
                }
                if (cur_ == ANNOT) {
                    //Add annotation
                    if (!annotation_.toString().trim().isEmpty()) {
                        annotations.add(annotation_.toString());
                        annotationsIndexes.add(indexArobase_ + instructionLocation);
                    }
                    annotation_.delete(0, annotation_.length());
                    indexArobase_ = j_;
                }
            }
            annotation_.append(cur_);
            j_++;
        }
        if (endLoop_) {
            index = lenInst_ + instructionLocation;
        }
    }

    private int until(CustList<SegmentStringPart> _parts, int _j, StringBuilder _annotation) {
        int until_ = _j;
        for (SegmentStringPart s: _parts) {
            if (s.getBegin() == _j + instructionLocation) {
                parts.add(s);
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

    public CustList<SegmentStringPart> getParts() {
        return parts;
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
    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }
    public StringList getAnnotations() {
        return annotations;
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
