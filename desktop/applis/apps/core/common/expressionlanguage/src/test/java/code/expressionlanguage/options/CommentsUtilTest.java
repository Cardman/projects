package code.expressionlanguage.options;

import code.expressionlanguage.files.CommentDelimiters;
import code.util.CustList;
import code.util.StringList;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;

public final class CommentsUtilTest {
    @Test
    public void test1() {
        CustList<CommentDelimiters> user_ = new CustList<CommentDelimiters>();
        CustList<CommentDelimiters> default_ = new CustList<CommentDelimiters>();
        CommentsUtil.checkAndUpdateComments(user_,default_);
        assertEq(0,user_.size());
    }
    @Test
    public void test2() {
        CustList<CommentDelimiters> user_ = new CustList<CommentDelimiters>();
        user_.add(new CommentDelimiters("\\\\",new StringList("\n")));
        user_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        CustList<CommentDelimiters> default_ = new CustList<CommentDelimiters>();
        CommentsUtil.checkAndUpdateComments(user_,default_);
        assertEq(2,user_.size());
        assertEq("\\\\",user_.first().getBegin());
        assertEq(new StringList("\r\n","\r","\n"),user_.first().getEnd());
        assertEq("\\*",user_.last().getBegin());
        assertEq(new StringList("*\\"),user_.last().getEnd());
    }
    @Test
    public void test3() {
        CustList<CommentDelimiters> user_ = new CustList<CommentDelimiters>();
        user_.add(new CommentDelimiters(">",new StringList("\n")));
        user_.add(new CommentDelimiters("<html>",new StringList("</html>")));
        user_.add(new CommentDelimiters("\\\\",new StringList("\n")));
        user_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        CustList<CommentDelimiters> default_ = new CustList<CommentDelimiters>();
        CommentsUtil.checkAndUpdateComments(user_,default_);
        assertEq(4,user_.size());
        assertEq(">",user_.first().getBegin());
        assertEq(new StringList("\r\n","\r","\n"),user_.first().getEnd());
        assertEq("<html>",user_.get(1).getBegin());
        assertEq(new StringList("</html>"),user_.get(1).getEnd());
        assertEq("\\\\",user_.get(2).getBegin());
        assertEq(new StringList("\r\n","\r","\n"),user_.get(2).getEnd());
        assertEq("\\*",user_.last().getBegin());
        assertEq(new StringList("*\\"),user_.last().getEnd());
    }
    @Test
    public void test4() {
        CustList<CommentDelimiters> user_ = new CustList<CommentDelimiters>();
        user_.add(new CommentDelimiters("\\\\",new StringList("\n")));
        user_.add(new CommentDelimiters("\\",new StringList("*\\")));
        CustList<CommentDelimiters> default_ = new CustList<CommentDelimiters>();
        CommentsUtil.checkAndUpdateComments(user_,default_);
        assertEq(0,user_.size());
    }
    @Test
    public void test5() {
        CustList<CommentDelimiters> user_ = new CustList<CommentDelimiters>();
        user_.add(new CommentDelimiters("\\",new StringList("\n")));
        user_.add(new CommentDelimiters("\\\\",new StringList("*\\")));
        CustList<CommentDelimiters> default_ = new CustList<CommentDelimiters>();
        CommentsUtil.checkAndUpdateComments(user_,default_);
        assertEq(0,user_.size());
    }
    @Test
    public void test6() {
        CustList<CommentDelimiters> user_ = new CustList<CommentDelimiters>();
        user_.add(new CommentDelimiters("(",new StringList("")));
        CustList<CommentDelimiters> default_ = new CustList<CommentDelimiters>();
        CommentsUtil.checkAndUpdateComments(user_,default_);
        assertEq(0,user_.size());
    }
    @Test
    public void test7() {
        CustList<CommentDelimiters> user_ = new CustList<CommentDelimiters>();
        user_.add(new CommentDelimiters("( )",new StringList("")));
        CustList<CommentDelimiters> default_ = new CustList<CommentDelimiters>();
        CommentsUtil.checkAndUpdateComments(user_,default_);
        assertEq(0,user_.size());
    }
    @Test
    public void test8() {
        CustList<CommentDelimiters> user_ = new CustList<CommentDelimiters>();
        user_.add(new CommentDelimiters("\n",new StringList("")));
        CustList<CommentDelimiters> default_ = new CustList<CommentDelimiters>();
        CommentsUtil.checkAndUpdateComments(user_,default_);
        assertEq(0,user_.size());
    }
}
