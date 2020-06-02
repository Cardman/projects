package code.expressionlanguage.files;

import code.util.Ints;
import code.util.StringList;

public final class ParsedAnnotations {

    private static final char BEGIN_CALLING = '(';
    private static final char END_CALLING = ')';
    private static final char DEL_CHAR = '\'';
    private static final char DEL_STRING = '"';
    private static final char DEL_TEXT = '`';
    private static final char ESCAPE = '\\';
    private static final char ANNOT = '@';
    private Ints annotationsIndexes = new Ints();
    private StringList annotations = new StringList();
    private String instruction = "";
    private String after = "";
    private int index;
    private int instructionLocation;

    public ParsedAnnotations(String _instruction, int _instructionLocation) {
        instruction = _instruction;
        instructionLocation = _instructionLocation;
    }
    public void parse() {
        int lenInst_ = instruction.length();
        int j_ = 0;
        int nbPars_ = 0;
        StringBuilder annotation_ = new StringBuilder();
        boolean quoted_ = false;
        boolean quotedChar_ = false;
        boolean quotedText_ = false;
        boolean endLoop_ = true;
        while (j_ < lenInst_) {
            char cur_ = instruction.charAt(j_);
            if (quotedChar_) {
                annotation_.append(cur_);
                if (cur_ == ESCAPE) {
                    j_++;
                    annotation_.append(instruction.charAt(j_));
                    j_++;
                    continue;
                }
                if (cur_ == DEL_CHAR) {
                    quotedChar_ = false;
                }
                j_++;
                continue;
            }
            if (quoted_) {
                annotation_.append(cur_);
                if (cur_ == ESCAPE) {
                    j_++;
                    annotation_.append(instruction.charAt(j_));
                    j_++;
                    continue;
                }
                if (cur_ == DEL_STRING) {
                    quoted_ = false;
                }
                j_++;
                continue;
            }
            if (quotedText_) {
                annotation_.append(cur_);
                if (cur_ == DEL_TEXT) {
                    if (instruction.charAt(j_ + 1) != DEL_TEXT) {
                        quotedText_ = false;
                        j_++;
                        continue;
                    }
                    j_++;
                    annotation_.append(instruction.charAt(j_));
                }
                j_++;
                continue;
            }
            if (cur_ == DEL_CHAR) {
                annotation_.append(cur_);
                quotedChar_ = true;
                j_++;
                continue;
            }
            if (cur_ == DEL_STRING) {
                annotation_.append(cur_);
                quoted_ = true;
                j_++;
                continue;
            }
            if (cur_ == DEL_TEXT) {
                annotation_.append(cur_);
                quotedText_ = true;
                j_++;
                continue;
            }
            if (cur_ == END_CALLING) {
                nbPars_--;
            }
            if (cur_ == BEGIN_CALLING) {
                nbPars_++;
            }
            if (StringList.isDollarWordChar(cur_) && nbPars_ == 0) {
                String after_ = instruction.substring(j_+1);
                if (after_.isEmpty() || !isPart(after_.charAt(0))) {
                    String afterTrim_ = after_.trim();
                    if (afterTrim_.isEmpty() || afterTrim_.charAt(0) != '.' && afterTrim_.charAt(0) != ANNOT  && afterTrim_.charAt(0) != BEGIN_CALLING) {
                        annotation_.append(cur_);
                        annotations.add(annotation_.toString());
                        index = j_ + instructionLocation;
                        index++;
                        j_++;
                        while (j_ < lenInst_) {
                            if (!Character.isWhitespace(instruction.charAt(j_))) {
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
            if (cur_ == END_CALLING && nbPars_ == 0) {
                String after_ = instruction.substring(j_+1).trim();
                if (after_.isEmpty() || after_.charAt(0) != ANNOT) {
                    annotation_.append(cur_);
                    annotations.add(annotation_.toString());
                    index = j_ + instructionLocation;
                    index++;
                    j_++;
                    while (j_ < lenInst_) {
                        if (!Character.isWhitespace(instruction.charAt(j_))) {
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
            if (nbPars_ == 0 && cur_ == ANNOT) {
                //Add annotation
                if (!annotation_.toString().trim().isEmpty()) {
                    annotations.add(annotation_.toString());
                }
                annotation_.delete(0, annotation_.length());
            }
            if (nbPars_ == 0 && cur_ == ANNOT) {
                annotationsIndexes.add(j_ + instructionLocation);
            }
            annotation_.append(cur_);
            j_++;
        }
        if (endLoop_) {
            index = lenInst_;
        }
    }
    private static boolean isPart(char _char) {
        if (StringList.isDollarWordChar(_char)) {
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
    
}
