package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;
import code.util.Ints;

final class ParseStringsCommentsState {

    private static final char DEL_CHAR = '\'';
    private static final char DEL_STRING = '"';
    private static final char DEL_TEXT = '`';
    private static final char ESCAPE = '\\';
    private boolean constTextChar;
    private boolean constTextString;
    private boolean constChar;
    private boolean constString;
    private boolean constText;
    private int index;
    private int instructionLocation;
    private final String file;
    private final StringBuilder instruction;
    private CommentDelimiters currentCom;
    ParseStringsCommentsState(int _index, String _file, StringBuilder _instruction) {
        index = _index;
        file = _file;
        instruction = _instruction;
    }
    void parse(int _instructionLocation,FileBlock _fileBlock, CustList<CommentDelimiters> _comments) {
        int instructionLocation_ = _instructionLocation;
        instructionLocation = _instructionLocation;
        int len_ = file.length();
        int i_ = index;
        CommentDelimiters current_ = null;
        Ints beginComments_ = _fileBlock.getBeginComments();
        Ints endComments_ = _fileBlock.getEndComments();
        while (i_ < len_) {
            char currentChar_ = file.charAt(i_);
            if (current_ != null) {
                String endCom_ = FileResolver.getEndCom(file, i_, current_);
                int length_ = endCom_.length();
                if (length_ > 0) {
                    i_ += length_;
                    FileResolver.appendEnd(i_, endCom_, endComments_);
                    FileResolver.appendEndComment(instruction, endCom_);
                    current_ = null;
                    currentCom = null;
                    continue;
                }
                instruction.append(' ');
                i_++;
                continue;
            }
            if (constTextChar) {
                instruction.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        i_++;
                        continue;
                    }
                    instruction.append(file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }
                if (currentChar_ == DEL_CHAR
                        && StringExpUtil.nextCharIs(file,i_+1,len_,DEL_CHAR)
                        &&StringExpUtil.nextCharIs(file,i_+2,len_,DEL_CHAR)) {
                    instruction.append(file.charAt(i_+1));
                    instruction.append(file.charAt(i_+2));
                    i_+=3;
                    constTextChar = false;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            if (constChar) {
                instruction.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        i_++;
                        continue;
                    }
                    instruction.append(file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }
                if (currentChar_ == DEL_CHAR) {

                    i_ = i_ + 1;
                    constChar = false;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            if (constTextString) {
                instruction.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        i_++;
                        continue;
                    }
                    instruction.append(file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }
                if (currentChar_ == DEL_STRING
                        && StringExpUtil.nextCharIs(file,i_+1,len_,DEL_STRING)
                        &&StringExpUtil.nextCharIs(file,i_+2,len_,DEL_STRING)) {
                    instruction.append(file.charAt(i_+1));
                    instruction.append(file.charAt(i_+2));
                    i_+=3;
                    constTextString = false;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            if (constString) {
                instruction.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        i_++;
                        continue;
                    }
                    instruction.append(file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }
                if (currentChar_ == DEL_STRING) {

                    i_ = i_ + 1;
                    constString = false;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            if (constText) {
                instruction.append(currentChar_);
                if (i_ + 1 >= len_) {
                    //ERROR
                    i_++;
                    continue;
                }
                if(currentChar_ == DEL_TEXT) {
                    if (file.charAt(i_ + 1) != DEL_TEXT) {

                        i_ = i_ + 1;
                        constText = false;
                        continue;
                    }
                    instruction.append(file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            CommentDelimiters skip_ = skip(file, i_, _comments);
            if (skip_ != null) {
                current_ = skip_;
                currentCom = skip_;
                beginComments_.add(i_);
                int beginLen_ = skip_.getBegin().length();
                i_ += beginLen_;
                for (int e = 0; e < beginLen_; e++) {
                    instruction.append(' ');
                }
                continue;
            }
            if (currentChar_ == DEL_CHAR) {
                instructionLocation_ = FileResolver.setInstLocation(instruction, instructionLocation_, i_);
                instruction.append(currentChar_);
                if (StringExpUtil.nextCharIs(file,i_+1,len_,DEL_CHAR)
                        &&StringExpUtil.nextCharIs(file,i_+2,len_,DEL_CHAR)) {
                    constTextChar = true;
                    instruction.append(file.charAt(i_+1));
                    instruction.append(file.charAt(i_+2));
                    i_ += 3;
                    continue;
                }
                constChar = true;

                i_ = i_ + 1;
                continue;
            }
            if (currentChar_ == DEL_STRING) {
                instructionLocation_ = FileResolver.setInstLocation(instruction, instructionLocation_, i_);
                instruction.append(currentChar_);
                if (StringExpUtil.nextCharIs(file,i_+1,len_,DEL_STRING)
                        &&StringExpUtil.nextCharIs(file,i_+2,len_,DEL_STRING)) {
                    constTextString = true;
                    instruction.append(file.charAt(i_+1));
                    instruction.append(file.charAt(i_+2));
                    i_ += 3;
                    continue;
                }
                constString = true;

                i_ = i_ + 1;
                continue;
            }
            if (currentChar_ == DEL_TEXT) {
                instructionLocation_ = FileResolver.setInstLocation(instruction, instructionLocation_, i_);
                instruction.append(currentChar_);
                constText = true;

                i_ = i_ + 1;
                continue;
            }
            break;
        }
        index = i_;
    }

    CommentDelimiters getCurrentCom() {
        return currentCom;
    }

    int getIndex() {
        return index;
    }

    int getInstructionLocation() {
        return instructionLocation;
    }

    static CommentDelimiters skip(String _file, int _i, CustList<CommentDelimiters> _comments) {
        for (CommentDelimiters c: _comments) {
            if (_file.startsWith(c.getBegin(),_i)) {
                return c;
            }
        }
        return null;
    }
}
