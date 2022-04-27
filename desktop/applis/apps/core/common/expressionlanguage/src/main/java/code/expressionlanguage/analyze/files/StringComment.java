package code.expressionlanguage.analyze.files;

import code.util.CustList;
import code.util.Ints;

public final class StringComment {
    private final String file;

    private final Ints beginComments;
    private final Ints endComments;
    private final CustList<SegmentColorPart> segmentColorParts;
    private final CustList<SegmentStringPart> stringParts;

    public StringComment(String _file, CustList<CommentDelimiters> _comments) {
        this(_file,_comments,0);
    }
    public StringComment(String _file, CustList<CommentDelimiters> _comments, int _offset) {
        StringCommentIteration strIt_ = new StringCommentIteration(0,_offset);
        StringBuilder str_ = new StringBuilder();
        int len_ = _file.length();
        while(strIt_.getIndex() < len_) {
            if (strIt_.code(_file,_comments,str_)) {
                strIt_.appendCode(_file, str_);
            }
        }
        this.file = str_.toString();
        strIt_.addEnd(len_);
        beginComments =strIt_.getBeginComments();
        endComments =strIt_.getEndComments();
        segmentColorParts =strIt_.getSegmentColorParts();
        stringParts =strIt_.getStringParts();
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

}
