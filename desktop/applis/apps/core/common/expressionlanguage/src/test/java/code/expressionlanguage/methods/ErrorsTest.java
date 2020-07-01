package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public class ErrorsTest extends ProcessMethodCommon {
    @Test
    public void report0Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", "");
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>" +
                "<a title=\"Bad index by parsing.\" class=\"e\"> " +
                "</a></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", "\b");
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><a title=\"The characters ascii 8 are illegal.\" class=\"e\">\b</a></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" } ");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class pkg.Ex {\n" +
                " $public $static $int exmeth(){\n" +
                "  $return 1i;\n" +
                " }<a title=\"Bad index by parsing.\" class=\"e\"> " +
                "</a></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int v;\n");
        xml_.append(" $public $int w;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" } ");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.ExTwo </a>{\n" +
                " $public $int <a name=\"m41\">v</a>;\n" +
                " $public $int <a name=\"m58\">w</a>;\n" +
                "}\n" +
                "$public $class pkg.Ex {\n" +
                " $public $static $int exmeth(){\n" +
                "  $return 1i;\n" +
                " }<a title=\"Bad index by parsing.\" class=\"e\"> </a></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo{} ");
        xml_.append("$publi");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class pkg.ExTwo{} <a title=\"Bad index by parsing.\" class=\"e\">$</a>publi</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExTwo{}");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.ExTwo</a>{}\n" +
                "$public $class <a name=\"m42\" title=\"The type name pkg.ExTwo is duplicated with an other custom type.\" class=\"e\">pkg.ExTwo</a>{}</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo{\n");
        xml_.append(" $public $class ExTwo{}\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.ExTwo</a>{\n" +
                " $public $class <a name=\"m42\" title=\"The inner type simple name ExTwo is duplicated.\" class=\"e\">ExTwo</a>{}\n" +
                "}</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class ExTwo{\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public <a title=\"A type must have an non empty package.\" class=\"e\">$class</a> <a name=\"m15\">ExTwo</a>{\n" +
                "}</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class a.#ExTwo{\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The part #ExTwo in a type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">a.#ExTwo</a>{\n" +
                "}</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class #a.ExTwo{\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The part #a in a type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">#a.ExTwo</a>{\n" +
                "}</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class .{\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public <a title=\"A type must have an non empty package.\" class=\"e\">$class</a> <a name=\"m15\" title=\"The part . in a type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">.</a>{\n" +
                "}</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class .ExTwo{\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public <a title=\"A type must have an non empty package.\" class=\"e\">$class</a> <a name=\"m15\" title=\"The part .ExTwo in a type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">.ExTwo</a>{\n" +
                "}</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Outer</a><a name=\"m24\" title=\"The part must not be empty.\" class=\"e\">&lt;</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<,T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Outer</a><a name=\"m24\" title=\"The part must not be empty.\" class=\"e\">&lt;</a>,<a name=\"m26\">T</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T,> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Outer</a>&lt;<a name=\"m25\">T</a><a name=\"m26\" title=\"The part must not be empty.\" class=\"e\">,</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<,> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Outer</a><a name=\"m24\" title=\"The part must not be empty.\" class=\"e\">&lt;</a><a name=\"m25\" title=\"The part must not be empty.\" class=\"e\">,</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T#> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Outer</a>&lt;<a name=\"m25\" title=\"The part T# in a variable type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">T#</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<S,T#> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Outer</a>&lt;<a name=\"m25\">S</a>,<a name=\"m27\" title=\"The part T# in a variable type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">T#</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T,T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Outer</a>&lt;<a name=\"m25\">T</a>,<a name=\"m27\" title=\"The part T in a variable type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">T</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" #ONE{}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MyEnum </a>{\n" +
                " <a name=\"m28\" title=\"The part #ONE in a type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">#ONE</a>{}\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" #ONE{(){}}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MyEnum </a>{\n" +
                " <a name=\"m28\" title=\"The part #ONE in a type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\n" +
                "\n" +
                "pkg.MyEnum-#ONE.pkg.MyEnum-#ONE()\" href=\"#m33\" class=\"e\">#ONE</a>{<a name=\"m33\">(</a>){}}\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" #ONE(1){($int i){}}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MyEnum </a>{\n" +
                " <a name=\"m28\" title=\"The part #ONE in a type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\n" +
                "\n" +
                "pkg.MyEnum-#ONE.pkg.MyEnum-#ONE($int)\" href=\"#m36\" class=\"e\">#ONE</a>(1){<a name=\"m36\">(</a>$int <a name=\"m42\">i</a>){}}\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" #ONE(1+2){($int i){}}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MyEnum </a>{\n" +
                " <a name=\"m28\" title=\"The part #ONE in a type is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\n" +
                "\n" +
                "pkg.MyEnum-#ONE.pkg.MyEnum-#ONE($int)\" href=\"#m38\" class=\"e\">#ONE</a>(1+2){<a name=\"m38\">(</a>$int <a name=\"m44\">i</a>){}}\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.sub.MyClass {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.sub {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.sub.MyClass </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m50\" title=\"The type name pkg.sub is shadowed by the package pkg.sub.\" class=\"e\">pkg.sub </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.sub.two.MyClass {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.sub {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.sub.two.MyClass </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m54\" title=\"The type name pkg.sub is shadowed by the package pkg.sub.\n" +
                "\n" +
                "The type name pkg.sub is shadowed by the package pkg.sub.two.\" class=\"e\">pkg.sub </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:$void {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type cannot be the key word $void.\n" +
                "\n" +
                "The type java.lang.Object is not parameterized correctly.\" class=\"e\">pkg.MyClass</a>:<a title=\"The type $void is unknown.\" class=\"e\">$void</a> {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.MyClass:java.lang.CharSequence {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $abstract $class <a name=\"m25\" title=\"The type pkg.MyClass cannot have explicitly the type java.lang.CharSequence as super type because java.lang.CharSequence is reserved." +
                "\" class=\"e\">pkg.MyClass</a>:java.lang.CharSequence {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotation:MyClass {\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.MyClass {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\" title=\"The type pkg.MyAnnotation cannot have the type MyClass as super type." +
                "\" class=\"e\">pkg.MyAnnotation</a>:<a title=\"pkg.MyClass\" href=\"#m74\">MyClass</a> {\n" +
                "}\n" +
                "$public $abstract $class <a name=\"m74\">pkg.MyClass </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:$Enum<MyEnum> {\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type pkg.MyClass cannot have explicitly the type java.lang.$Enum as super type because java.lang.$Enum is reserved." +
                "\" class=\"e\">pkg.MyClass</a>:$Enum&lt;<a title=\"pkg.MyEnum\" href=\"#m59\">MyEnum</a>&gt; {\n" +
                "}\n" +
                "$public $enum <a name=\"m59\">pkg.MyEnum </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:$en {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type pkg.MyClass cannot have explicitly the type java.lang.$en as super type because java.lang.$en is reserved." +
                "\" class=\"e\">pkg.MyClass</a>:$en {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:MySuper:MySuper {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySuper {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type pkg.MyClass cannot have the type pkg.MySuper duplicated as super type 2 times.\n" +
                "\n" +
                "The super types of the type pkg.MyClass could not be found." +
                "\" class=\"e\">pkg.MyClass</a>:<a title=\"pkg.MySuper\" href=\"#m62\">MySuper</a>:<a title=\"pkg.MySuper\" href=\"#m62\">MySuper</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m62\">pkg.MySuper </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:MySuper.Inner {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySuper {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type pkg.MyClass cannot have the type pkg.MySuper..Inner as super type because pkg.MySuper..Inner is instance type.\" class=\"e\">pkg.MyClass</a>:<a title=\"pkg.MySuper\" href=\"#m60\">MySuper</a>.<a title=\"pkg.MySuper..Inner\" href=\"#m90\">Inner</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m60\">pkg.MySuper </a>{\n" +
                " $public $class <a name=\"m90\">Inner </a>{\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyOuter {\n");
        xml_.append(" $public $class MyClass:MySuper.Inner {\n");
        xml_.append(" }\n");
        xml_.append(" $public $class MySuper {\n");
        xml_.append("  $public $class Inner {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyOuter </a>{\n" +
                " $public $class <a name=\"m45\" title=\"The type pkg.MyOuter..MyClass cannot have the type pkg.MyOuter..MySuper..Inner as super type because pkg.MyOuter..MyClass has 1 parents types and pkg.MyOuter..MySuper..Inner has 2 parents types.\n" +
                "\n" +
                "The type pkg.MyOuter..MyClass cannot have the type pkg.MyOuter..MySuper..Inner as super type.\" class=\"e\">MyClass</a>:<a title=\"pkg.MyOuter..MySuper\" href=\"#m88\">MySuper</a>.<a title=\"pkg.MyOuter..MySuper..Inner\" href=\"#m115\">Inner</a> {\n" +
                " }\n" +
                " $public $class <a name=\"m88\">MySuper </a>{\n" +
                "  $public $class <a name=\"m115\">Inner </a>{\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyInt:MyClass {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\" title=\"The interface pkg.MyInt cannot have the type pkg.MyClass as super type because pkg.MyClass is not an interface.\" class=\"e\">pkg.MyInt</a>:<a title=\"pkg.MyClass\" href=\"#m56\">MyClass</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m56\">pkg.MyClass </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyClass {\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkg.MyClass {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type pkg.MySub cannot have the type pkg.MyClass as super type because pkg.MyClass is final.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyClass\" href=\"#m59\">MyClass</a> {\n" +
                "}\n" +
                "$public $final $class <a name=\"m59\">pkg.MyClass </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyClass:MySecClass {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySecClass {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The class pkg.MySub cannot have more than one super class (2 times).\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyClass\" href=\"#m63\">MyClass</a>:<a title=\"pkg.MySecClass\" href=\"#m94\">MySecClass</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m63\">pkg.MyClass </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m94\">pkg.MySecClass </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:MySupOne,MySupTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySupOne {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySupTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The super types of the type pkg.MyClass could not be found.\n" +
                "\n" +
                "The type java.lang.Object is not parameterized correctly.\" class=\"e\">pkg.MyClass</a>:<a title=\"The type MySupOne,MySupTwo is unknown.\" class=\"e\">MySupOne,MySupTwo</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m64\">pkg.MySupOne </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m96\">pkg.MySupTwo </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:MySup {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type pkg.MySup is not parameterized correctly.\" class=\"e\">pkg.MyClass</a>:<a title=\"pkg.MySup\" href=\"#m52\">MySup</a> {\n" +
                "}\n" +
                "$public $class <a name=\"m52\">pkg.MySup</a>&lt;<a name=\"m62\">T</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:MySup<Integer> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup<T:MyCl> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type pkg.MySup&lt;java.lang.Integer&gt; is not parameterized correctly.\" class=\"e\">pkg.MyClass</a>:<a title=\"pkg.MySup\" href=\"#m61\">MySup</a>&lt;Integer&gt; {\n" +
                "}\n" +
                "$public $class <a name=\"m61\">pkg.MySup</a>&lt;<a name=\"m71\">T</a>:<a title=\"pkg.MyCl\" href=\"#m98\">MyCl</a>&gt; {\n" +
                "}\n" +
                "$public $class <a name=\"m98\">pkg.MyCl </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:pkgtwo.MyCl {\n");
        xml_.append("}\n");
        xml_.append("$package $class pkgtwo.MyCl {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass</a>:<a title=\"The type pkgtwo.MyCl is not accessible from the type pkg.MyClass.\n" +
                "\n" +
                "pkgtwo.MyCl\" href=\"#m59\" class=\"e\">pkgtwo.MyCl</a> {\n" +
                "}\n" +
                "$package $class <a name=\"m59\">pkgtwo.MyCl </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyInt<String>:MyInt<Object> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type pkg.MySub cannot have the type pkg.MyInt duplicated as super type 2 times.\n" +
                "\n" +
                "The super types of the type pkg.MySub could not be found.\n" +
                "\n" +
                "The generic super types pkg.MyInt&lt;java.lang.String&gt;&amp;pkg.MyInt&lt;java.lang.Object&gt; are duplicated.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyInt\" href=\"#m76\">MyInt</a>&lt;String&gt;:<a title=\"pkg.MyInt\" href=\"#m76\">MyInt</a>&lt;Object&gt; {\n" +
                "}\n" +
                "$public $interface <a name=\"m76\">pkg.MyInt</a>&lt;<a name=\"m86\">T</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MySubInTwo<String>:MySubInt<Object> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MySubInTwo<U>:MyInt<U> {}\n");
        xml_.append("$public $interface pkg.MySubInt<S>:MyInt<S> {}\n");
        xml_.append("$public $interface pkg.MyInt<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The generic super types pkg.MyInt&lt;java.lang.String&gt;&amp;pkg.MyInt&lt;java.lang.Object&gt; are duplicated.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MySubInTwo\" href=\"#m84\">MySubInTwo</a>&lt;String&gt;:<a title=\"pkg.MySubInt\" href=\"#m133\">MySubInt</a>&lt;Object&gt; {\n" +
                "}\n" +
                "$public $interface <a name=\"m84\">pkg.MySubInTwo</a>&lt;<a name=\"m99\">U</a>&gt;:<a title=\"pkg.MyInt\" href=\"#m180\">MyInt</a>&lt;<a href=\"#m99\">U</a>&gt; {}\n" +
                "$public $interface <a name=\"m133\">pkg.MySubInt</a>&lt;<a name=\"m146\">S</a>&gt;:<a title=\"pkg.MyInt\" href=\"#m180\">MyInt</a>&lt;<a href=\"#m146\">S</a>&gt; {}\n" +
                "$public $interface <a name=\"m180\">pkg.MyInt</a>&lt;<a name=\"m190\">T</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyInt<String>:MyInt<Object> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type pkg.MySub cannot have the type pkg.MyInt duplicated as super type 2 times.\n" +
                "\n" +
                "The super types of the type pkg.MySub could not be found.\n" +
                "\n" +
                "The generic super types pkg.MyInt&lt;java.lang.String&gt;&amp;pkg.MyInt&lt;java.lang.Object&gt; are duplicated.\n" +
                "\n" +
                "The type pkg.MyInt&lt;java.lang.String&gt; is not parameterized correctly.\n" +
                "\n" +
                "The type pkg.MyInt&lt;java.lang.Object&gt; is not parameterized correctly.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyInt\" href=\"#m76\">MyInt</a>&lt;String&gt;:<a title=\"pkg.MyInt\" href=\"#m76\">MyInt</a>&lt;Object&gt; {\n" +
                "}\n" +
                "$public $interface <a name=\"m76\">pkg.MyInt </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyInt<?MyIntTwo> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyIntTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The argument ?pkg.MyIntTwo of the generic super type pkg.MyInt&lt;?pkg.MyIntTwo&gt; is bound. It cannot be used in generic super type.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyInt\" href=\"#m65\">MyInt</a>&lt;?<a title=\"pkg.MyIntTwo\" href=\"#m101\">MyIntTwo</a>&gt; {\n" +
                "}\n" +
                "$public $interface <a name=\"m65\">pkg.MyInt</a>&lt;<a name=\"m75\">T</a>&gt; {\n" +
                "}\n" +
                "$public $interface <a name=\"m101\">pkg.MyIntTwo </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyInt<!MyIntTwo> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyIntTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The argument !pkg.MyIntTwo of the generic super type pkg.MyInt&lt;!pkg.MyIntTwo&gt; is bound. It cannot be used in generic super type.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyInt\" href=\"#m65\">MyInt</a>&lt;!<a title=\"pkg.MyIntTwo\" href=\"#m101\">MyIntTwo</a>&gt; {\n" +
                "}\n" +
                "$public $interface <a name=\"m65\">pkg.MyInt</a>&lt;<a name=\"m75\">T</a>&gt; {\n" +
                "}\n" +
                "$public $interface <a name=\"m101\">pkg.MyIntTwo </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyInt<> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type java.lang.Object is not parameterized correctly.\" class=\"e\">pkg.MySub</a>:MyInt&lt;<a title=\"The type MyInt&lt;&gt; is unknown.\" class=\"e\">&gt;</a> {\n" +
                "}\n" +
                "$public $interface <a name=\"m56\">pkg.MyInt</a>&lt;<a name=\"m66\">T</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyInt<!> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type java.lang.Object is not parameterized correctly.\" class=\"e\">pkg.MySub</a>:MyInt&lt;<a title=\"The type MyInt&lt;!&gt; is unknown.\" class=\"e\">!</a>&gt; {\n" +
                "}\n" +
                "$public $interface <a name=\"m57\">pkg.MyInt</a>&lt;<a name=\"m67\">T</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void #method(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\" title=\"The method name #method is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">#method</a>(){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method($int #t){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>($int <a name=\"m54\" title=\"The parameter method name #t is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">#t</a>){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method(){\n");
        xml_.append(" }\n");
        xml_.append(" $public $void method(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>(){\n" +
                " }\n" +
                " $public $void <a name=\"m70\" title=\"The method method() is duplicated.\" class=\"e\">method</a>(){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public Inexist method(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public <a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a> <a name=\"m44\">method</a>(){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public MyParam<String> method(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyParam {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public <a title=\"The type pkg.MyParam&lt;java.lang.String&gt; is not parameterized correctly.\" class=\"e\">MyParam&lt;String&gt;</a> <a name=\"m52\">method</a>(){\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m82\">pkg.MyParam </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public MyParam<String> method(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyParam<T:MyCl> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public MyParam<a title=\"The type pkg.MyParam&lt;java.lang.String&gt; is not parameterized correctly.\" class=\"e\">&lt;</a>String&gt; <a name=\"m52\">method</a>(){\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m82\">pkg.MyParam</a>&lt;<a name=\"m94\">T</a>:<a title=\"pkg.MyCl\" href=\"#m121\">MyCl</a>&gt; {\n" +
                "}\n" +
                "$public $class <a name=\"m121\">pkg.MyCl </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method($int t,$int t){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>($int <a name=\"m54\">t</a>,$int <a name=\"m61\" title=\"The parameter function name t is duplicated.\" class=\"e\">t</a>){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public MySub(){\n");
        xml_.append(" }\n");
        xml_.append(" $public MySub(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a name=\"m28\">$public MySub(</a>){\n" +
                " }\n" +
                " <a name=\"m49\" title=\"The constructor pkg.MySub() is duplicated.\" class=\"e\">$public MySub(</a>){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public MySub($int #t){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a name=\"m28\">$public MySub(</a>$int <a name=\"m47\" title=\"The parameter method name #t is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">#t</a>){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public MySub($int t,$int t){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a name=\"m28\">$public MySub(</a>$int <a name=\"m47\">t</a>,$int <a name=\"m54\" title=\"The parameter function name t is duplicated.\" class=\"e\">t</a>){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $operator<> $int(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $operator<a name=\"m37\" title=\"The method name &lt;&gt; is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">&lt;&gt;</a> $int(){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int $this($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\" title=\"The indexer []($int) set must be defined.\" class=\"e\">$this</a>($int <a name=\"m52\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\" title=\"The indexer []=($int) get must be defined.\" class=\"e\">$this</a>($int <a name=\"m53\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report60Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void $this($int i,$int i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int i,$int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">$this</a>($int <a name=\"m53\">i</a>,$int <a name=\"m60\" title=\"The parameter function name i is duplicated.\" class=\"e\">i</a>){\n" +
                " }\n" +
                " $public $int <a name=\"m81\">$this</a>($int <a name=\"m92\">i</a>,$int <a name=\"m99\" title=\"The parameter function name i is duplicated.\" class=\"e\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">$this</a>($int <a name=\"m53\">i</a>){\n" +
                " }\n" +
                " $public $int <a name=\"m74\">$this</a>($int <a name=\"m85\">i</a>){\n" +
                " }\n" +
                " $public $void <a name=\"m107\" title=\"The indexer []=($int) is duplicated.\" class=\"e\">$this</a>($int <a name=\"m118\">i</a>){\n" +
                " }\n" +
                " $public $int <a name=\"m139\" title=\"The indexer []($int) is duplicated.\" class=\"e\">$this</a>($int <a name=\"m150\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MySub {\n");
        xml_.append(" $int #annot();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MySub </a>{\n" +
                " $int <a name=\"m38\" title=\"The method name #annot is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">#annot</a>();\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MySub {\n");
        xml_.append(" #ONE\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MySub </a>{\n" +
                " <a name=\"m27\" title=\"The field name #ONE is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\">#ONE</a>\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MySub {\n");
        xml_.append(" #ONE;\n");
        xml_.append(" $public MySub(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MySub </a>{\n" +
                " <a name=\"m27\" title=\"The field name #ONE is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\n" +
                "\n" +
                "pkg.MySub.pkg.MySub()\" href=\"#m34\">#ONE</a>;\n" +
                " <a name=\"m34\">$public MySub(</a>){}\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $if($true){}\n");
        xml_.append(" {\n");
        xml_.append(" $if($true){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"The block is unexpected.\" class=\"e\">$</a>if($true){}\n" +
                " {\n" +
                " $if($true){}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int #e;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $int <a name=\"m33\" title=\"The field name #e is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">#e</a>;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int e,e;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $int <a name=\"m33\" title=\"the field name e is duplicated.\" class=\"e\">e</a>,<a name=\"m35\" title=\"the field name e is duplicated.\" class=\"e\">e</a>;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int e+e;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $int <a title=\"No field could be retrieved.\" class=\"e\">e+e</a>;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    private static void validateAndCheckErrors(StringMap<String> files_, ContextEl cont_) {
        validate(cont_,files_);
        assertTrue(!cont_.isEmptyErrors());
    }

    private static void validate(ContextEl _c, StringMap<String> _f) {
        validate(_c.getAnalysisMessages(),_c.getKeyWords(),_c.getStandards(),_f,_c);
    }
}
