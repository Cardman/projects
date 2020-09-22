package code.expressionlanguage.analyze.files;

import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;

public final class ParsedTemplatedTypeTest {

    @Test
    public void parse1Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        parse(p_, "pkgtwo.MyClassTwo<Type>After");
        assertEq("pkgtwo.MyClassTwo<Type>",p_.getInstruction().toString());
        assertEq(23, p_.getCurrent());
    }

    @Test
    public void parse2Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        parse(p_, "pkgtwo.MyClassTwo<Type,Second>After");
        assertEq("pkgtwo.MyClassTwo<Type,Second>",p_.getInstruction().toString());
        assertEq(30, p_.getCurrent());
    }

    @Test
    public void parse3Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        parse(p_, "pkgtwo.MyClassTwo<Type,Second[]>After");
        assertEq("pkgtwo.MyClassTwo<Type,Second[]>",p_.getInstruction().toString());
        assertEq(32, p_.getCurrent());
    }


    @Test
    public void parse4Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        parse(p_, "pkgtwo.MyClassTwo<Type<Param>>After");
        assertEq("pkgtwo.MyClassTwo<Type<Param>>",p_.getInstruction().toString());
        assertEq(30, p_.getCurrent());
    }

    @Test
    public void parse5Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        parse(p_, "pkgtwo.MyClassTwo<Type<Param>.Inner>After");
        assertEq("pkgtwo.MyClassTwo<Type<Param>.Inner>",p_.getInstruction().toString());
        assertEq(36, p_.getCurrent());
    }

    @Test
    public void parse6Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        parse(p_, "pkgtwo.MyClassTwo<Type");
        assertEq("pkgtwo.MyClassTwo<Type",p_.getInstruction().toString());
        assertEq(22, p_.getCurrent());
    }

    @Test
    public void parse7Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        Ints begin_ = new Ints();
        Ints ends_ = new Ints();
        parse(p_, "pkgtwo.MyClassTwo<\\*Comment*\\Type",comments_,begin_,ends_);
        assertEq("pkgtwo.MyClassTwo<           Type",p_.getInstruction().toString());
        assertEq(33, p_.getCurrent());
        assertEq(1, begin_.size());
        assertEq(18, begin_.first());
        assertEq(1, ends_.size());
        assertEq(28, ends_.first());
    }

    @Test
    public void parse8Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        Ints begin_ = new Ints();
        Ints ends_ = new Ints();
        parse(p_, "pkgtwo.MyClassTwo<\"String\"Type",comments_,begin_,ends_);
        assertEq("pkgtwo.MyClassTwo<\"String\"Type",p_.getInstruction().toString());
        assertEq(30, p_.getCurrent());
        assertEq(0, begin_.size());
        assertEq(0, ends_.size());
    }

    @Test
    public void parse9Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        Ints begin_ = new Ints();
        Ints ends_ = new Ints();
        parse(p_, "pkgtwo.MyClassTwo<'String'Type",comments_,begin_,ends_);
        assertEq("pkgtwo.MyClassTwo<'String'Type",p_.getInstruction().toString());
        assertEq(30, p_.getCurrent());
        assertEq(0, begin_.size());
        assertEq(0, ends_.size());
    }

    @Test
    public void parse10Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        Ints begin_ = new Ints();
        Ints ends_ = new Ints();
        parse(p_, "pkgtwo.MyClassTwo<`String`Type",comments_,begin_,ends_);
        assertEq("pkgtwo.MyClassTwo<`String`Type",p_.getInstruction().toString());
        assertEq(30, p_.getCurrent());
        assertEq(0, begin_.size());
        assertEq(0, ends_.size());
    }

    @Test
    public void parse11Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        Ints begin_ = new Ints();
        Ints ends_ = new Ints();
        parse(p_, "pkgtwo.MyClassTwo<\"String\\\"\"Type",comments_,begin_,ends_);
        assertEq("pkgtwo.MyClassTwo<\"String\\\"\"Type",p_.getInstruction().toString());
        assertEq(32, p_.getCurrent());
        assertEq(0, begin_.size());
        assertEq(0, ends_.size());
    }

    @Test
    public void parse12Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        Ints begin_ = new Ints();
        Ints ends_ = new Ints();
        parse(p_, "pkgtwo.MyClassTwo<'String\\\"'Type",comments_,begin_,ends_);
        assertEq("pkgtwo.MyClassTwo<'String\\\"'Type",p_.getInstruction().toString());
        assertEq(32, p_.getCurrent());
        assertEq(0, begin_.size());
        assertEq(0, ends_.size());
    }

    @Test
    public void parse13Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        Ints begin_ = new Ints();
        Ints ends_ = new Ints();
        parse(p_, "pkgtwo.MyClassTwo<`String``after`Type",comments_,begin_,ends_);
        assertEq("pkgtwo.MyClassTwo<`String``after`Type",p_.getInstruction().toString());
        assertEq(37, p_.getCurrent());
        assertEq(0, begin_.size());
        assertEq(0, ends_.size());
    }

    @Test
    public void parse14Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        Ints begin_ = new Ints();
        Ints ends_ = new Ints();
        parse(p_, "pkgtwo.MyClassTwo<`String",comments_,begin_,ends_);
        assertEq("pkgtwo.MyClassTwo<`String",p_.getInstruction().toString());
        assertEq(25, p_.getCurrent());
        assertEq(0, begin_.size());
        assertEq(0, ends_.size());
    }

    @Test
    public void parse15Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        Ints begin_ = new Ints();
        Ints ends_ = new Ints();
        parse(p_, "pkgtwo.MyClassTwo<\"String\\",comments_,begin_,ends_);
        assertEq("pkgtwo.MyClassTwo<\"String\\",p_.getInstruction().toString());
        assertEq(26, p_.getCurrent());
        assertEq(0, begin_.size());
        assertEq(0, ends_.size());
    }

    @Test
    public void parse16Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        Ints begin_ = new Ints();
        Ints ends_ = new Ints();
        parse(p_, "pkgtwo.MyClassTwo<'String\\",comments_,begin_,ends_);
        assertEq("pkgtwo.MyClassTwo<'String\\",p_.getInstruction().toString());
        assertEq(26, p_.getCurrent());
        assertEq(0, begin_.size());
        assertEq(0, ends_.size());
    }
    private static void parse(ParsedTemplatedType p_, String _file) {
        parse(p_,_file, new CustList<CommentDelimiters>(), new Ints(), new Ints());
    }

    private static void parse(ParsedTemplatedType p_, String _file, CustList<CommentDelimiters> _comments, Ints _begin, Ints _end) {
        p_.parse(_file, _comments, _begin, _end);
    }
}
