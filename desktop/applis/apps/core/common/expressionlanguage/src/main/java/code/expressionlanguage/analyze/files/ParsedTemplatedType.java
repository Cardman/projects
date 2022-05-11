package code.expressionlanguage.analyze.files;

import code.util.CustList;
import code.util.Ints;

public final class ParsedTemplatedType {
    private static final char DEL_CHAR = '\'';
    private static final char DEL_STRING = '"';
    private static final char DEL_TEXT = '`';
    private static final char ESCAPE = '\\';
    private final StringBuilder instruction;
    private int current;
    private boolean stop;
    private int count;
    private CommentDelimiters currentCom;
    private boolean constChar;
    private boolean constString;
    private boolean constText;

    ParsedTemplatedType(StringBuilder _instruction, int _current) {
        instruction = _instruction;
        current = _current;
    }
    public void parse(String _file, CustList<CommentDelimiters> _comments, Ints _begin, Ints _end) {
        int len_ = _file.length();
        while (!stop&&current < len_) {
            iterate(_file, _comments, _begin, _end);
        }
    }

    private void iterate(String _file, CustList<CommentDelimiters> _comments, Ints _begin, Ints _end) {
        char ch_ = _file.charAt(current);
        if (currentCom != null) {
            comment(_file, _end);
            return;
        }
        if (constChar) {
            ch(_file);
            return;
        }
        if (constString) {
            str(_file);
            return;
        }
        if (constText) {
            tx(_file);
            return;
        }
        boolean skip_ = skip(_file, _comments, _begin);
        if (skip_) {
            return;
        }
        if (ch_ == DEL_CHAR) {
            instruction.append(ch_);
            constChar = true;

            current = current + 1;
            return;
        }
        if (ch_ == DEL_STRING) {
            instruction.append(ch_);
            constString = true;

            current = current + 1;
            return;
        }
        if (ch_ == DEL_TEXT) {
            instruction.append(ch_);
            constText = true;

            current = current + 1;
            return;
        }
        instruction.append(ch_);
        if (ch_ == '<') {
            count++;
        }
        if (ch_ == '>') {
            count--;
        }
        current++;
        if (count == 0) {
            stop = true;
        }
    }

    private boolean skip(String _file, CustList<CommentDelimiters> _comments, Ints _begin) {
        boolean skip_= false;
        for (CommentDelimiters c: _comments) {
            if (_file.startsWith(c.getBegin(),current)) {
                currentCom = c;
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
        return skip_;
    }

    private void tx(String _file) {
        char ch_ = _file.charAt(current);
        int len_ = _file.length();
        instruction.append(ch_);
        if (current + 1 >= len_) {
            //ERROR
            current++;
            stop = true;
            return;
        }
        if(ch_ == DEL_TEXT) {
            if (_file.charAt(current + 1) != DEL_TEXT) {

                current = current + 1;
                constText = false;
                return;
            }
            instruction.append(_file.charAt(current+1));

            current = current + 1;

            current = current + 1;
            return;
        }

        current = current + 1;
    }

    private void str(String _file) {
        char ch_ = _file.charAt(current);
        instruction.append(ch_);
        if (ch_ == ESCAPE) {
            escape(_file);
            return;
        }
        if (ch_ == DEL_STRING) {

            current = current + 1;
            constString = false;
            return;
        }

        current = current + 1;
    }

    private void ch(String _file) {
        char ch_ = _file.charAt(current);
        instruction.append(ch_);
        if (ch_ == ESCAPE) {
            escape(_file);
            return;
        }
        if (ch_ == DEL_CHAR) {

            current = current + 1;
            constChar = false;
            return;
        }

        current = current + 1;
    }

    private void escape(String _file) {
        int len_ = _file.length();
        if (current + 1 >= len_) {
            //ERROR
            current++;
            stop = true;
            return;
        }
        instruction.append(_file.charAt(current+1));

        current = current + 1;

        current = current + 1;
    }

    private void comment(String _file, Ints _end) {
        String endCom_ = FileResolver.getEndCom(_file, current, currentCom);
        int length_ = endCom_.length();
        if (length_ > 0) {
            current += length_;
            FileResolver.appendEnd(current, endCom_, _end);
            FileResolver.appendEndComment(instruction, endCom_);
            currentCom = null;
            return;
        }
        instruction.append(' ');
        current++;
    }

    StringBuilder getInstruction() {
        return instruction;
    }

    public int getCurrent() {
        return current;
    }
}
