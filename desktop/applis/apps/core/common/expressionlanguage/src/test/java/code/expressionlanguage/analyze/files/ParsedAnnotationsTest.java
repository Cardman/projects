package code.expressionlanguage.analyze.files;

import code.expressionlanguage.EquallableElUtil;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import org.junit.Test;

public final class ParsedAnnotationsTest extends EquallableElUtil {

    @Test
    public void parse1Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(28, p_.getIndex());
    }
    @Test
    public void parse2Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot()\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot()", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(30, p_.getIndex());
    }
    @Test
    public void parse3Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot({})\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot({})", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(32, p_.getIndex());
    }
    @Test
    public void parse4Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot(\"\")\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot(\"\")", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(32, p_.getIndex());
    }
    @Test
    public void parse5Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot(\"\\\"\")\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot(\"\\\"\")", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(34, p_.getIndex());
    }
    @Test
    public void parse6Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot(\"\\\\\")\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot(\"\\\\\")", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(34, p_.getIndex());
    }
    @Test
    public void parse7Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot('\"')\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot('\"')", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(33, p_.getIndex());
    }
    @Test
    public void parse8Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot('\\\\')\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot('\\\\')", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(34, p_.getIndex());
    }
    @Test
    public void parse9Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot(\"s\")\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot(\"s\")", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(33, p_.getIndex());
    }
    @Test
    public void parse10Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot(f=1)\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot(f=1)", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(33, p_.getIndex());
    }

    @Test
    public void parse11Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@pkg.MyAnnot\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@pkg.MyAnnot", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(32, p_.getIndex());
    }
    @Test
    public void parse12Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@pkg.MyAnnot()\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@pkg.MyAnnot()", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(34, p_.getIndex());
    }

    @Test
    public void parse13Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@pkg .MyAnnot\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@pkg .MyAnnot", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(33, p_.getIndex());
    }
    @Test
    public void parse14Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@pkg .MyAnnot()\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@pkg .MyAnnot()", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(35, p_.getIndex());
    }
    @Test
    public void parse15Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@pkg.MyAnnot ()\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@pkg.MyAnnot ()", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(35, p_.getIndex());
    }
    @Test
    public void parse16Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnotOne\n");
        file_.append("@MyAnnotTwo\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(2, getAnnotations(p_).size());
        assertEq("@MyAnnotOne\n", getAnnotations(p_).first());
        assertEq("@MyAnnotTwo", getAnnotations(p_).last());
        assertEq(2, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq(31, getAnnotationsIndexes(p_).last());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(43, p_.getIndex());
    }
    @Test
    public void parse17Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnotOne()\n");
        file_.append("@MyAnnotTwo\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(2, getAnnotations(p_).size());
        assertEq("@MyAnnotOne()\n", getAnnotations(p_).first());
        assertEq("@MyAnnotTwo", getAnnotations(p_).last());
        assertEq(2, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq(33, getAnnotationsIndexes(p_).last());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(45, p_.getIndex());
    }
    @Test
    public void parse18Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnotOne()\n");
        file_.append("@MyAnnotTwo()\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(2, getAnnotations(p_).size());
        assertEq("@MyAnnotOne()\n", getAnnotations(p_).first());
        assertEq("@MyAnnotTwo()", getAnnotations(p_).last());
        assertEq(2, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq(33, getAnnotationsIndexes(p_).last());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(47, p_.getIndex());
    }
    @Test
    public void parse19Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@pkg.MyAnnot\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@pkg.MyAnnot", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(32, p_.getIndex());
    }
    @Test
    public void parse20Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot(@MySec())\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot(@MySec())", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(38, p_.getIndex());
    }
    @Test
    public void parse21Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot(f=`first`)\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot(f=`first`)", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(39, p_.getIndex());
    }
    @Test
    public void parse22Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot(f=`first``second`)\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot(f=`first``second`)", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(47, p_.getIndex());
    }
    @Test
    public void parse23Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@pkg.MyAnnot\n");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@pkg.MyAnnot", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("",p_.getAfter());
        assertEq(32, p_.getIndex());
    }
    @Test
    public void parse24Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@pkg.MyAnnot");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@pkg.MyAnnot", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("",p_.getAfter());
        assertEq(31, p_.getIndex());
    }
    @Test
    public void parse25Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@pkg.MyAnnot()\n");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@pkg.MyAnnot()", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("",p_.getAfter());
        assertEq(34, p_.getIndex());
    }
    @Test
    public void parse26Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@pkg.MyAnnot()");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@pkg.MyAnnot()", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("",p_.getAfter());
        assertEq(33, p_.getIndex());
    }
    @Test
    public void parse27Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(0, getAnnotations(p_).size());
        assertEq(0, getAnnotationsIndexes(p_).size());
        assertEq("",p_.getAfter());
        assertEq(19, p_.getIndex());
    }

    @Test
    public void parse28Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(28, p_.getIndex());
    }

    @Test
    public void parse29Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;".length();
        ParsedAnnotations p_ = parse(file_, index_);
        assertEq(1, getAnnotations(p_).size());
        assertEq("@MyAnnot", getAnnotations(p_).first());
        assertEq(1, getAnnotationsIndexes(p_).size());
        assertEq(19, getAnnotationsIndexes(p_).first());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(28, p_.getIndex());
    }

    private StringList getAnnotations(ParsedAnnotations _p) {
        StringList ls_ = new StringList();
        for (ResultParsedAnnot i: _p.getRetAnnots()) {
            ls_.add(i.getAnnotation());
        }
        return ls_;
    }

    private Ints getAnnotationsIndexes(ParsedAnnotations _p) {
        Ints ls_ = new Ints();
        for (ResultParsedAnnot i: _p.getRetAnnots()) {
            ls_.add(i.getIndex());
        }
        return ls_;
    }
    private ParsedAnnotations parse(StringBuilder _file, int _index) {
        String substring_ = _file.substring(_index);
        ParsedAnnotations p_ = new ParsedAnnotations(substring_, _index);
        StringComment str_ = new StringComment(substring_,new CustList<CommentDelimiters>(),_index);
        p_.parse(str_.getStringParts(),"class");
        return p_;
    }
}
