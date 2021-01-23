package code.expressionlanguage.analyze.files;

import code.expressionlanguage.common.StringExpUtil;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ParsedAnnotations {

    private static final char BEGIN_CALLING = '(';
    private static final char END_CALLING = ')';
    private static final char DEL_CHAR = '\'';
    private static final char DEL_STRING = '"';
    private static final char DEL_TEXT = '`';
    private static final char ESCAPE = '\\';
    private static final char ANNOT = '@';
    private final Ints annotationsIndexes = new Ints();
    private final StringList annotations = new StringList();
    private String instruction = "";
    private String after = "";
    private int index;
    private final int instructionLocation;

    public ParsedAnnotations(String _instruction, int _instructionLocation) {
        instruction = _instruction;
        instructionLocation = _instructionLocation;
    }
    public void parse(String... _keyWordClass) {
        int lenInst_ = instruction.length();
        int j_ = 0;
        int nbPars_ = 0;
        StringBuilder annotation_ = new StringBuilder();
        boolean quoted_ = false;
        boolean quotedStringText_ = false;
        boolean quotedCharText_ = false;
        boolean quotedChar_ = false;
        boolean quotedText_ = false;
        boolean endLoop_ = true;
        while (j_ < lenInst_) {
            char cur_ = instruction.charAt(j_);
            if (quotedCharText_) {
                annotation_.append(cur_);
                if (cur_ == ESCAPE) {
                    j_++;
                    annotation_.append(instruction.charAt(j_));
                    j_++;
                    continue;
                }
                if (cur_ == DEL_CHAR
                        &&StringExpUtil.nextCharIs(instruction,j_+1,lenInst_,DEL_CHAR)
                        &&StringExpUtil.nextCharIs(instruction,j_+2,lenInst_,DEL_CHAR)) {
                    quotedCharText_ = false;
                    annotation_.append(instruction.charAt(j_+1));
                    annotation_.append(instruction.charAt(j_+2));
                    j_+=3;
                    continue;
                }
                j_++;
                continue;
            }
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
            if (quotedStringText_) {
                annotation_.append(cur_);
                if (cur_ == ESCAPE) {
                    j_++;
                    annotation_.append(instruction.charAt(j_));
                    j_++;
                    continue;
                }
                if (cur_ == DEL_STRING
                        &&StringExpUtil.nextCharIs(instruction,j_+1,lenInst_,DEL_STRING)
                        &&StringExpUtil.nextCharIs(instruction,j_+2,lenInst_,DEL_STRING)) {
                    quotedStringText_ = false;
                    annotation_.append(instruction.charAt(j_+1));
                    annotation_.append(instruction.charAt(j_+2));
                    j_+=3;
                    continue;
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
                if (are(lenInst_, j_, DEL_CHAR)) {
                    annotation_.append(cur_);
                    annotation_.append(instruction.charAt(j_+1));
                    annotation_.append(instruction.charAt(j_+2));
                    quotedCharText_ = true;
                    j_ += 3;
                    continue;
                }
                annotation_.append(cur_);
                quotedChar_ = true;
                j_++;
                continue;
            }
            if (cur_ == DEL_STRING) {
                if (are(lenInst_, j_, DEL_STRING)) {
                    annotation_.append(cur_);
                    annotation_.append(instruction.charAt(j_+1));
                    annotation_.append(instruction.charAt(j_+2));
                    quotedStringText_ = true;
                    j_ += 3;
                    continue;
                }
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
            if (nbPars_ == 0) {
                if (StringExpUtil.isTypeLeafChar(cur_)) {
                    String after_ = instruction.substring(j_+1);
                    if (after_.isEmpty() || !isPart(after_.charAt(0))) {
                        String afterTrim_ = after_.trim();
                        if (afterTrim_.isEmpty() || afterTrim_.charAt(0) != '.' && !startsWithAnnotFilter(afterTrim_,_keyWordClass) && afterTrim_.charAt(0) != BEGIN_CALLING) {
                            annotation_.append(cur_);
                            annotations.add(annotation_.toString());
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
                    if (after_.isEmpty() || !startsWithAnnotFilter(after_,_keyWordClass)) {
                        annotation_.append(cur_);
                        annotations.add(annotation_.toString());
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
                    }
                    annotation_.delete(0, annotation_.length());

                    annotationsIndexes.add(j_ + instructionLocation);
                }
            }
            annotation_.append(cur_);
            j_++;
        }
        if (endLoop_) {
            index = lenInst_ + instructionLocation;
        }
    }

    private boolean are(int _lenInst, int _j, char _delChar) {
        return StringExpUtil.nextCharIs(instruction, _j + 1, _lenInst, _delChar)
                && StringExpUtil.nextCharIs(instruction, _j + 2, _lenInst, _delChar);
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
    private static boolean startsWithAnnotFilter(String _trimmedInstruction, String... _keyWordClass) {
        if (_keyWordClass.length == 0) {
            return _trimmedInstruction.charAt(0) == ANNOT;
        }
        return startsWithAnnot(_trimmedInstruction, _keyWordClass);
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
