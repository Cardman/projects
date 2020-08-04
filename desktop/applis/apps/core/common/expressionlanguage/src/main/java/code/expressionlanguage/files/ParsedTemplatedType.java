package code.expressionlanguage.files;

import code.util.CustList;
import code.util.Ints;

public final class ParsedTemplatedType {
    private static final char DEL_CHAR = '\'';
    private static final char DEL_STRING = '"';
    private static final char DEL_TEXT = '`';
    private static final char ESCAPE = '\\';
    private StringBuilder instruction;
    private int current;

    ParsedTemplatedType(StringBuilder _instruction, int _current) {
        instruction = _instruction;
        current = _current;
    }
    public void parse(String _file, CustList<CommentDelimiters> _comments, Ints _begin, Ints _end) {
        int len_ = _file.length();
        int count_ = 0;
        CommentDelimiters current_ = null;
        boolean constChar_ = false;
        boolean constString_ = false;
        boolean constText_ = false;
        while (current < len_) {
            char ch_ = _file.charAt(current);
            if (current_ != null) {
                String endCom_ = FileResolver.getEndCom(_file, current, current_);
                int length_ = endCom_.length();
                if (length_ > 0) {
                    current += length_;
                    FileResolver.appendEnd(current, endCom_, _end);
                    FileResolver.appendEndComment(instruction, endCom_);
                    current_ = null;
                    continue;
                }
                instruction.append(' ');
                current++;
                continue;
            }
            if (constChar_) {
                instruction.append(ch_);
                if (ch_ == ESCAPE) {
                    if (current + 1 >= len_) {
                        //ERROR
                        current++;
                        break;
                    }
                    instruction.append(_file.charAt(current+1));

                    current = current + 1;

                    current = current + 1;
                    continue;
                }
                if (ch_ == DEL_CHAR) {

                    current = current + 1;
                    constChar_ = false;
                    continue;
                }

                current = current + 1;
                continue;
            }
            if (constString_) {
                instruction.append(ch_);
                if (ch_ == ESCAPE) {
                    if (current + 1 >= len_) {
                        //ERROR
                        current++;
                        break;
                    }
                    instruction.append(_file.charAt(current+1));

                    current = current + 1;

                    current = current + 1;
                    continue;
                }
                if (ch_ == DEL_STRING) {

                    current = current + 1;
                    constString_ = false;
                    continue;
                }

                current = current + 1;
                continue;
            }
            if (constText_) {
                instruction.append(ch_);
                if (current + 1 >= len_) {
                    //ERROR
                    current++;
                    break;
                }
                if(ch_ == DEL_TEXT) {
                    if (_file.charAt(current + 1) != DEL_TEXT) {

                        current = current + 1;
                        constText_ = false;
                        continue;
                    }
                    instruction.append(_file.charAt(current+1));

                    current = current + 1;

                    current = current + 1;
                    continue;
                }

                current = current + 1;
                continue;
            }
            boolean skip_= false;
            for (CommentDelimiters c: _comments) {
                if (_file.startsWith(c.getBegin(),current)) {
                    current_ = c;
                    _begin.add(current);
                    int beginLen_ = c.getBegin().length();
                    current += beginLen_;
                    for (int e = 0; e < beginLen_; e++) {
                        instruction.append(' ');
                    }
                    skip_ = true;
                    break;
                }
            }
            if (skip_) {
                continue;
            }
            if (ch_ == DEL_CHAR) {
                instruction.append(ch_);
                constChar_ = true;

                current = current + 1;
                continue;
            }
            if (ch_ == DEL_STRING) {
                instruction.append(ch_);
                constString_ = true;

                current = current + 1;
                continue;
            }
            if (ch_ == DEL_TEXT) {
                instruction.append(ch_);
                constText_ = true;

                current = current + 1;
                continue;
            }
            instruction.append(ch_);
            if (ch_ == '<') {
                count_++;
            }
            if (ch_ == '>') {
                count_--;
            }
            current++;
            if (count_ == 0) {
                break;
            }
        }
    }

    StringBuilder getInstruction() {
        return instruction;
    }

    public int getCurrent() {
        return current;
    }
}
