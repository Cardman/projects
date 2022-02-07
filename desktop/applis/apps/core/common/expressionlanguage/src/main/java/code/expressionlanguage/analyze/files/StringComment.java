package code.expressionlanguage.analyze.files;

import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;
import code.util.Ints;

public final class StringComment {
    private static final char DEL_CHAR = '\'';
    private static final char DEL_STRING = '"';
    private static final char DEL_TEXT = '`';
    private static final char ESCAPE = '\\';
    private boolean constTextChar;
    private boolean constTextString;
    private boolean constChar;
    private boolean constString;
    private boolean constText;
    private final String file;

    private final Ints beginComments = new Ints();
    private final Ints endComments = new Ints();
    private final CustList<SegmentColorPart> segmentColorParts = new CustList<SegmentColorPart>();
    private final CustList<SegmentStringPart> stringParts = new CustList<SegmentStringPart>();

    public StringComment(String _file, CustList<CommentDelimiters> _comments) {
        StringBuilder str_ = new StringBuilder();
        int len_ = _file.length();
        int i_ = 0;
        int begin_ = 0;
        CommentDelimiters current_ = null;
        while (i_ < len_) {
            char currentChar_ = _file.charAt(i_);
            if (current_ != null) {
                String endCom_ = FileResolver.getEndCom(_file, i_, current_);
                int length_ = endCom_.length();
                if (length_ > 0) {
                    i_ += length_;
                    segmentColorParts.add(new SegmentColorPart(begin_,i_,SegmentType.COMMENT));
                    FileResolver.appendEnd(i_, endCom_, endComments);
                    FileResolver.appendEndComment(str_, endCom_);
                    current_ = null;
                    continue;
                }
                FileResolver.appendChar(str_,currentChar_);
                i_++;
                continue;
            }
            if (constTextChar) {
                str_.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        i_++;
                        continue;
                    }
                    str_.append(_file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }
                if (currentChar_ == DEL_CHAR
                        && StringExpUtil.nextCharIs(_file,i_+1,len_,DEL_CHAR)
                        &&StringExpUtil.nextCharIs(_file,i_+2,len_,DEL_CHAR)) {
                    str_.append(_file.charAt(i_+1));
                    str_.append(_file.charAt(i_+2));
                    i_+=3;
                    segmentColorParts.add(new SegmentColorPart(begin_,i_,SegmentType.STRING));
                    stringParts.add(new SegmentStringPart(begin_,i_,SegmentStringType.TEXT_CHAR));
                    constTextChar = false;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            if (constChar) {
                str_.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        i_++;
                        continue;
                    }
                    str_.append(_file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }
                if (currentChar_ == DEL_CHAR) {

                    i_ = i_ + 1;
                    segmentColorParts.add(new SegmentColorPart(begin_,i_,SegmentType.STRING));
                    stringParts.add(new SegmentStringPart(begin_,i_,SegmentStringType.CHAR));
                    constChar = false;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            if (constTextString) {
                str_.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        i_++;
                        continue;
                    }
                    str_.append(_file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }
                if (currentChar_ == DEL_STRING
                        && StringExpUtil.nextCharIs(_file,i_+1,len_,DEL_STRING)
                        &&StringExpUtil.nextCharIs(_file,i_+2,len_,DEL_STRING)) {
                    str_.append(_file.charAt(i_+1));
                    str_.append(_file.charAt(i_+2));
                    i_+=3;
                    segmentColorParts.add(new SegmentColorPart(begin_,i_,SegmentType.STRING));
                    stringParts.add(new SegmentStringPart(begin_,i_,SegmentStringType.TEXT_STRING));
                    constTextString = false;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            if (constString) {
                str_.append(currentChar_);
                if (currentChar_ == ESCAPE) {
                    if (i_ + 1 >= len_) {
                        //ERROR
                        i_++;
                        continue;
                    }
                    str_.append(_file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }
                if (currentChar_ == DEL_STRING) {

                    i_ = i_ + 1;
                    segmentColorParts.add(new SegmentColorPart(begin_,i_,SegmentType.STRING));
                    stringParts.add(new SegmentStringPart(begin_,i_,SegmentStringType.STRING));
                    constString = false;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            if (constText) {
                str_.append(currentChar_);
                if (i_ + 1 >= len_) {
                    //ERROR
                    i_++;
                    continue;
                }
                if(currentChar_ == DEL_TEXT) {
                    if (_file.charAt(i_ + 1) != DEL_TEXT) {

                        i_ = i_ + 1;
                        segmentColorParts.add(new SegmentColorPart(begin_,i_,SegmentType.STRING));
                        stringParts.add(new SegmentStringPart(begin_,i_,SegmentStringType.TEXT));
                        constText = false;
                        continue;
                    }
                    str_.append(_file.charAt(i_+1));

                    i_ = i_ + 1;

                    i_ = i_ + 1;
                    continue;
                }

                i_ = i_ + 1;
                continue;
            }
            CommentDelimiters skip_ = skip(_file, i_, _comments);
            if (skip_ != null) {
                current_ = skip_;
                begin_ = i_;
                beginComments.add(i_);
                int beginLen_ = skip_.getBegin().length();
                i_ += beginLen_;
                for (int e = 0; e < beginLen_; e++) {
                    str_.append(' ');
                }
                continue;
            }
            if (currentChar_ == DEL_CHAR) {
                begin_ = i_;
                str_.append(currentChar_);
                if (StringExpUtil.nextCharIs(_file,i_+1,len_,DEL_CHAR)
                        &&StringExpUtil.nextCharIs(_file,i_+2,len_,DEL_CHAR)) {
                    constTextChar = true;
                    str_.append(_file.charAt(i_+1));
                    str_.append(_file.charAt(i_+2));
                    i_ += 3;
                    continue;
                }
                constChar = true;

                i_ = i_ + 1;
                continue;
            }
            if (currentChar_ == DEL_STRING) {
                begin_ = i_;
                str_.append(currentChar_);
                if (StringExpUtil.nextCharIs(_file,i_+1,len_,DEL_STRING)
                        &&StringExpUtil.nextCharIs(_file,i_+2,len_,DEL_STRING)) {
                    constTextString = true;
                    str_.append(_file.charAt(i_+1));
                    str_.append(_file.charAt(i_+2));
                    i_ += 3;
                    continue;
                }
                constString = true;

                i_ = i_ + 1;
                continue;
            }
            if (currentChar_ == DEL_TEXT) {
                begin_ = i_;
                str_.append(currentChar_);
                constText = true;

                i_ = i_ + 1;
                continue;
            }
            str_.append(currentChar_);
            i_ = i_ + 1;
        }
        this.file = str_.toString();
        addEndComment(len_, begin_, current_);
        addEndStr(len_, begin_);
    }

    private void addEndComment(int _len, int _begin, CommentDelimiters _current) {
        if (_current != null) {
            endComments.add(_len - 1);
            segmentColorParts.add(new SegmentColorPart(_begin, _len,SegmentType.COMMENT));
        }
    }

    private void addEndStr(int _len, int _begin) {
        if (constTextChar) {
            segmentColorParts.add(new SegmentColorPart(_begin, _len,SegmentType.STRING));
            stringParts.add(new SegmentStringPart(_begin, _len,SegmentStringType.TEXT_CHAR));
        }
        if (constTextString) {
            segmentColorParts.add(new SegmentColorPart(_begin, _len,SegmentType.STRING));
            stringParts.add(new SegmentStringPart(_begin, _len,SegmentStringType.TEXT_STRING));
        }
        if (constChar) {
            segmentColorParts.add(new SegmentColorPart(_begin, _len,SegmentType.STRING));
            stringParts.add(new SegmentStringPart(_begin, _len,SegmentStringType.CHAR));
        }
        if (constString) {
            segmentColorParts.add(new SegmentColorPart(_begin, _len,SegmentType.STRING));
            stringParts.add(new SegmentStringPart(_begin, _len,SegmentStringType.STRING));
        }
        if (constText) {
            segmentColorParts.add(new SegmentColorPart(_begin, _len,SegmentType.STRING));
            stringParts.add(new SegmentStringPart(_begin, _len,SegmentStringType.TEXT));
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

    public String getFile() {
        return file;
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
