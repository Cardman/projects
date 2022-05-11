package code.expressionlanguage.analyze.files;

import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;
import code.util.Ints;

public final class StringCommentIteration {
    private static final char DEL_CHAR = '\'';
    private static final char DEL_STRING = '"';
    private static final char DEL_TEXT = '`';
    private static final char ESCAPE = '\\';
    private boolean constTextChar;
    private boolean constTextString;
    private boolean constChar;
    private boolean constString;
    private boolean constText;

    private final Ints beginComments = new Ints();
    private final Ints endComments = new Ints();
    private final CustList<SegmentColorPart> segmentColorParts = new CustList<SegmentColorPart>();
    private final CustList<SegmentStringPart> stringParts = new CustList<SegmentStringPart>();
    private int index;
    private int begin;
    private CommentDelimiters current;
    private final int off;
    public StringCommentIteration(int _i,int _offset) {
        setIndex(_i);
        off = _offset;
    }

    public boolean code(String _file, CustList<CommentDelimiters> _comments, StringBuilder _str) {
        char currentChar_ = _file.charAt(index);
        if (current != null) {
            procComm(_file, _str);
            return false;
        }
        if (constTextChar) {
            textStr(_file, _str);
            return false;
        }
        if (constChar) {
            ch(_file, _str);
            return false;

        }
        if (constTextString) {
            textCh(_file, _str);
            return false;

        }
        if (constString) {
            str(_file, _str);
            return false;
        }
        if (constText) {
            text(_file, _str);
            return false;
        }
        CommentDelimiters skip_ = skip(_file, index, _comments);
        if (skip_ != null) {
            entComm(_str, skip_);
            return false;
        }
        if (currentChar_ == DEL_CHAR) {
            entCh(_file, _str);
            return false;
        }
        if (currentChar_ == DEL_STRING) {
            entStr(_file, _str);
            return false;
        }
        if (currentChar_ == DEL_TEXT) {
            entText(_file, _str);
            return false;
        }
        return true;
    }

    private void entText(String _file, StringBuilder _str) {
        char currentChar_ = _file.charAt(index);
        begin = index;
        _str.append(currentChar_);
        constText = true;

        index = index + 1;
    }

    public void appendCode(String _file, StringBuilder _str) {
        char currentChar_ = _file.charAt(index);
        _str.append(currentChar_);
        index = index + 1;
    }

    private void entComm(StringBuilder _str, CommentDelimiters _skip) {
        current = _skip;
        begin = index;
        beginComments.add(index+off);
        int beginLen_ = _skip.getBegin().length();
        index += beginLen_;
        for (int e = 0; e < beginLen_; e++) {
            _str.append(' ');
        }
    }

    private void entCh(String _file, StringBuilder _str) {
        int len_ = _file.length();
        char currentChar_ = _file.charAt(index);
        begin = index;
        _str.append(currentChar_);
        if (StringExpUtil.nextCharIs(_file,index+1, len_,DEL_CHAR)
                &&StringExpUtil.nextCharIs(_file,index+2, len_,DEL_CHAR)) {
            constTextChar = true;
            _str.append(_file.charAt(index+1));
            _str.append(_file.charAt(index+2));
            index += 3;
        } else {
            constChar = true;

            index = index + 1;
        }
    }

    private void entStr(String _file, StringBuilder _str) {
        int len_ = _file.length();
        char currentChar_ = _file.charAt(index);
        begin = index;
        _str.append(currentChar_);
        if (StringExpUtil.nextCharIs(_file,index+1, len_,DEL_STRING)
                &&StringExpUtil.nextCharIs(_file,index+2, len_,DEL_STRING)) {
            constTextString = true;
            _str.append(_file.charAt(index+1));
            _str.append(_file.charAt(index+2));
            index += 3;
        } else {
            constString = true;

            index = index + 1;
        }
    }

    private void text(String _file, StringBuilder _str) {
        int len_ = _file.length();
        char currentChar_ = _file.charAt(index);
        _str.append(currentChar_);
        if (index + 1 >= len_) {
            //ERROR
            index++;
        } else {
            if (currentChar_ == DEL_TEXT) {
                if (_file.charAt(index + 1) != DEL_TEXT) {

                    index = index + 1;
                    segmentColorParts.add(new SegmentColorPart(begin+off, index+off, SegmentType.STRING));
                    stringParts.add(new SegmentStringPart(begin+off, index+off, SegmentStringType.TEXT));
                    constText = false;
                } else {
                    _str.append(_file.charAt(index + 1));

                    index = index + 1;

                    index = index + 1;
                }
            } else {

                index = index + 1;
            }
        }
    }

    private void str(String _file, StringBuilder _str) {
        char currentChar_ = _file.charAt(index);
        _str.append(currentChar_);
        if (currentChar_ == ESCAPE) {
            escape(_file, _str);
        } else {
            if (currentChar_ == DEL_STRING) {

                index = index + 1;
                segmentColorParts.add(new SegmentColorPart(begin+off, index+off, SegmentType.STRING));
                stringParts.add(new SegmentStringPart(begin+off, index+off, SegmentStringType.STRING));
                constString = false;
            } else {
                index = index + 1;

            }

        }
    }

    private void textCh(String _file, StringBuilder _str) {
        int len_ = _file.length();
        char currentChar_ = _file.charAt(index);
        _str.append(currentChar_);
        if (currentChar_ == ESCAPE) {
            escape(_file, _str);

        } else {
            if (currentChar_ == DEL_STRING
                    && StringExpUtil.nextCharIs(_file,index+1, len_,DEL_STRING)
                    &&StringExpUtil.nextCharIs(_file,index+2, len_,DEL_STRING)) {
                _str.append(_file.charAt(index+1));
                _str.append(_file.charAt(index+2));
                index+=3;
                segmentColorParts.add(new SegmentColorPart(begin+off,index+off,SegmentType.STRING));
                stringParts.add(new SegmentStringPart(begin+off,index+off,SegmentStringType.TEXT_STRING));
                constTextString = false;
            } else {
                index = index + 1;

            }

        }
    }

    private void ch(String _file, StringBuilder _str) {
        char currentChar_ = _file.charAt(index);
        _str.append(currentChar_);
        if (currentChar_ == ESCAPE) {
            escape(_file, _str);
        } else {
            if (currentChar_ == DEL_CHAR) {

                index = index + 1;
                segmentColorParts.add(new SegmentColorPart(begin+off, index+off, SegmentType.STRING));
                stringParts.add(new SegmentStringPart(begin+off, index+off, SegmentStringType.CHAR));
                constChar = false;
            } else {
                index = index + 1;
            }
        }
    }

    private void textStr(String _file, StringBuilder _str) {
        int len_ = _file.length();
        char currentChar_ = _file.charAt(index);
        _str.append(currentChar_);
        if (currentChar_ == ESCAPE) {
            escape(_file, _str);

        } else {
            if (currentChar_ == DEL_CHAR
                    && StringExpUtil.nextCharIs(_file, index + 1, len_, DEL_CHAR)
                    && StringExpUtil.nextCharIs(_file, index + 2, len_, DEL_CHAR)) {
                _str.append(_file.charAt(index + 1));
                _str.append(_file.charAt(index + 2));
                index += 3;
                segmentColorParts.add(new SegmentColorPart(begin+off, index+off, SegmentType.STRING));
                stringParts.add(new SegmentStringPart(begin+off, index+off, SegmentStringType.TEXT_CHAR));
                constTextChar = false;
            } else {

                index = index + 1;
            }
        }
    }

    private void escape(String _file, StringBuilder _str) {
        int len_ = _file.length();
        if (index + 1 >= len_) {
            //ERROR
            index++;
        } else {
            _str.append(_file.charAt(index + 1));

            index = index + 1;

            index = index + 1;
        }
    }

    private void procComm(String _file, StringBuilder _str) {
        char currentChar_ = _file.charAt(index);
        String endCom_ = FileResolver.getEndCom(_file, index, current);
        int length_ = endCom_.length();
        if (length_ > 0) {
            index += length_;
            segmentColorParts.add(new SegmentColorPart(begin+off,index+off,SegmentType.COMMENT));
            FileResolver.appendEnd(index+off, endCom_, endComments);
            FileResolver.appendEndComment(_str, endCom_);
            current = null;
        } else {
            FileResolver.appendChar(_str, currentChar_);
            index++;
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _i) {
        index = _i;
    }

    private int getBegin() {
        return begin;
    }

    private CommentDelimiters getCurrent() {
        return current;
    }

    public void addEnd(int _len) {
        addEndComment(_len, getBegin(), getCurrent());
        addEndStr(_len, getBegin());
    }
    private void addEndComment(int _len, int _begin, CommentDelimiters _current) {
        if (_current != null) {
            endComments.add(_len - 1+off);
            segmentColorParts.add(new SegmentColorPart(_begin+off, _len+off,SegmentType.COMMENT));
        }
    }

    private void addEndStr(int _len, int _begin) {
        if (constTextChar) {
            segmentColorParts.add(new SegmentColorPart(_begin+off, _len+off,SegmentType.STRING));
            stringParts.add(new SegmentStringPart(_begin+off, _len+off,SegmentStringType.TEXT_CHAR));
        }
        if (constTextString) {
            segmentColorParts.add(new SegmentColorPart(_begin+off, _len+off,SegmentType.STRING));
            stringParts.add(new SegmentStringPart(_begin+off, _len+off,SegmentStringType.TEXT_STRING));
        }
        if (constChar) {
            segmentColorParts.add(new SegmentColorPart(_begin+off, _len+off,SegmentType.STRING));
            stringParts.add(new SegmentStringPart(_begin+off, _len+off,SegmentStringType.CHAR));
        }
        if (constString) {
            segmentColorParts.add(new SegmentColorPart(_begin+off, _len+off,SegmentType.STRING));
            stringParts.add(new SegmentStringPart(_begin+off, _len+off,SegmentStringType.STRING));
        }
        if (constText) {
            segmentColorParts.add(new SegmentColorPart(_begin+off, _len+off,SegmentType.STRING));
            stringParts.add(new SegmentStringPart(_begin+off, _len+off,SegmentStringType.TEXT));
        }
    }

    public Ints getBeginComments() {
        return beginComments;
    }

    public Ints getEndComments() {
        return endComments;
    }

    public CustList<SegmentColorPart> getSegmentColorParts() {
        return segmentColorParts;
    }

    public CustList<SegmentStringPart> getStringParts() {
        return stringParts;
    }

    private static CommentDelimiters skip(String _file, int _i, CustList<CommentDelimiters> _comments) {
        for (CommentDelimiters c: _comments) {
            if (_file.startsWith(c.getBegin(),_i)) {
                return c;
            }
        }
        return null;
    }
}
