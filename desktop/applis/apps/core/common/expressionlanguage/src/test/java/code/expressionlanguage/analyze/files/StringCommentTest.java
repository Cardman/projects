package code.expressionlanguage.analyze.files;

import code.expressionlanguage.EquallableElUtil;
import code.util.CustList;
import code.util.StringList;
import org.junit.Test;

public final class StringCommentTest extends EquallableElUtil {
    @Test
    public void results1(){
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        StringComment stringComment_ = new StringComment("this is 'c' a char", comments_);
        CustList<SegmentStringPart> stringParts_ = stringComment_.getStringParts();
        CustList<SegmentColorPart> segmentColorParts_ = stringComment_.getSegmentColorParts();
        assertEq(1, stringParts_.size());
        assertSame(SegmentStringType.CHAR, stringParts_.get(0).getStrType());
        assertEq(8, stringParts_.get(0).getBegin());
        assertEq(11, stringParts_.get(0).getEnd());
        assertEq(1, segmentColorParts_.size());
        assertSame(SegmentType.STRING, segmentColorParts_.get(0).getType());
        assertEq(8, segmentColorParts_.get(0).getBegin());
        assertEq(11, segmentColorParts_.get(0).getEnd());
    }
    @Test
    public void results2(){
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        StringComment stringComment_ = new StringComment("this is \"c\" a string", comments_);
        CustList<SegmentStringPart> stringParts_ = stringComment_.getStringParts();
        CustList<SegmentColorPart> segmentColorParts_ = stringComment_.getSegmentColorParts();
        assertEq(1, stringParts_.size());
        assertSame(SegmentStringType.STRING, stringParts_.get(0).getStrType());
        assertEq(8, stringParts_.get(0).getBegin());
        assertEq(11, stringParts_.get(0).getEnd());
        assertEq(1, segmentColorParts_.size());
        assertSame(SegmentType.STRING, segmentColorParts_.get(0).getType());
        assertEq(8, segmentColorParts_.get(0).getBegin());
        assertEq(11, segmentColorParts_.get(0).getEnd());
    }
    @Test
    public void results3(){
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        StringComment stringComment_ = new StringComment("this is '''c''' a char", comments_);
        CustList<SegmentStringPart> stringParts_ = stringComment_.getStringParts();
        CustList<SegmentColorPart> segmentColorParts_ = stringComment_.getSegmentColorParts();
        assertEq(1, stringParts_.size());
        assertSame(SegmentStringType.TEXT_CHAR, stringParts_.get(0).getStrType());
        assertEq(8, stringParts_.get(0).getBegin());
        assertEq(15, stringParts_.get(0).getEnd());
        assertEq(1, segmentColorParts_.size());
        assertSame(SegmentType.STRING, segmentColorParts_.get(0).getType());
        assertEq(8, segmentColorParts_.get(0).getBegin());
        assertEq(15, segmentColorParts_.get(0).getEnd());
    }
    @Test
    public void results4(){
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        StringComment stringComment_ = new StringComment("this is \"\"\"c\"\"\" a string", comments_);
        CustList<SegmentStringPart> stringParts_ = stringComment_.getStringParts();
        CustList<SegmentColorPart> segmentColorParts_ = stringComment_.getSegmentColorParts();
        assertEq(1, stringParts_.size());
        assertSame(SegmentStringType.TEXT_STRING, stringParts_.get(0).getStrType());
        assertEq(8, stringParts_.get(0).getBegin());
        assertEq(15, stringParts_.get(0).getEnd());
        assertEq(1, segmentColorParts_.size());
        assertSame(SegmentType.STRING, segmentColorParts_.get(0).getType());
        assertEq(8, segmentColorParts_.get(0).getBegin());
        assertEq(15, segmentColorParts_.get(0).getEnd());
    }
    @Test
    public void results5(){
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        StringComment stringComment_ = new StringComment("this is `c` a text", comments_);
        CustList<SegmentStringPart> stringParts_ = stringComment_.getStringParts();
        CustList<SegmentColorPart> segmentColorParts_ = stringComment_.getSegmentColorParts();
        assertEq(1, stringParts_.size());
        assertSame(SegmentStringType.TEXT, stringParts_.get(0).getStrType());
        assertEq(8, stringParts_.get(0).getBegin());
        assertEq(11, stringParts_.get(0).getEnd());
        assertEq(1, segmentColorParts_.size());
        assertSame(SegmentType.STRING, segmentColorParts_.get(0).getType());
        assertEq(8, segmentColorParts_.get(0).getBegin());
        assertEq(11, segmentColorParts_.get(0).getEnd());
    }
    @Test
    public void results6(){
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\/",new StringList("\\/")));
        StringComment stringComment_ = new StringComment("this is \\/c\\/ a comment", comments_);
        CustList<SegmentStringPart> stringParts_ = stringComment_.getStringParts();
        CustList<SegmentColorPart> segmentColorParts_ = stringComment_.getSegmentColorParts();
        assertEq(0, stringParts_.size());
        assertEq(1, segmentColorParts_.size());
        assertSame(SegmentType.COMMENT, segmentColorParts_.get(0).getType());
        assertEq(8, segmentColorParts_.get(0).getBegin());
        assertEq(13, segmentColorParts_.get(0).getEnd());
    }
}
