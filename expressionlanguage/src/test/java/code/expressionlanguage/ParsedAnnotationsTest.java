package code.expressionlanguage;

import static code.expressionlanguage.EquallableElUtil.assertEq;

import org.junit.Test;

public final class ParsedAnnotationsTest {

    @Test
    public void parse1Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo;\n");
        file_.append("@MyAnnot\n");
        file_.append("$public $class pkg.MyClass{}");
        int index_ = "pkgtwo.MyClassTwo;\n".length();
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@MyAnnot",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@MyAnnot()",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@MyAnnot({})",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@MyAnnot(\"\")",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@MyAnnot(\"\\\"\")",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@MyAnnot(\"\\\\\")",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@MyAnnot('\"')",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@MyAnnot('\\\\')",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@MyAnnot(\"s\")",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@MyAnnot(f=1)",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@pkg.MyAnnot",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@pkg.MyAnnot()",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@pkg .MyAnnot",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@pkg .MyAnnot()",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(1,p_.getAnnotations().size());
        assertEq("@pkg.MyAnnot ()",p_.getAnnotations().first());
        assertEq(1,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(2,p_.getAnnotations().size());
        assertEq("@MyAnnotOne\n",p_.getAnnotations().first());
        assertEq("@MyAnnotTwo",p_.getAnnotations().last());
        assertEq(2,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
        assertEq(31,p_.getAnnotationsIndexes().last());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(2,p_.getAnnotations().size());
        assertEq("@MyAnnotOne()\n",p_.getAnnotations().first());
        assertEq("@MyAnnotTwo",p_.getAnnotations().last());
        assertEq(2,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
        assertEq(33,p_.getAnnotationsIndexes().last());
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
        ParsedAnnotations p_ = new ParsedAnnotations(file_.substring(index_),index_);
        p_.parse();
        assertEq(2,p_.getAnnotations().size());
        assertEq("@MyAnnotOne()\n",p_.getAnnotations().first());
        assertEq("@MyAnnotTwo()",p_.getAnnotations().last());
        assertEq(2,p_.getAnnotationsIndexes().size());
        assertEq(19,p_.getAnnotationsIndexes().first());
        assertEq(33,p_.getAnnotationsIndexes().last());
        assertEq("$public $class pkg.MyClass{}",p_.getAfter());
        assertEq(47, p_.getIndex());
    }
}
