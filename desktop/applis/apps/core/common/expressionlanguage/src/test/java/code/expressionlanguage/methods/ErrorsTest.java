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
                "The type cannot be the key word $void.\n" +
                "\n" +
                "The type java.lang.Object is not parameterized correctly.\" class=\"e\">pkg.MyClass</a>:$void {\n" +
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type java.lang.Object is not parameterized correctly.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyInt\" href=\"#m56\">MyInt</a><a title=\"The type MyInt&lt;&gt; is unknown.\" class=\"e\">&lt;</a>&gt; {\n" +
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
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The type java.lang.Object is not parameterized correctly.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyInt\" href=\"#m57\">MyInt</a><a title=\"The type MyInt&lt;!&gt; is unknown.\" class=\"e\">&lt;</a>!&gt; {\n" +
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
                " $public <a title=\"pkg.MyParam\" href=\"#m82\">MyParam</a>&lt;String<a title=\"The type pkg.MyParam is not parameterized correctly.\" class=\"e\">&gt;</a> <a name=\"m52\">method</a>(){\n" +
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
                " $public <a title=\"pkg.MyParam\" href=\"#m82\">MyParam</a><a title=\"The type pkg.MyParam&lt;java.lang.String&gt; is not parameterized correctly.\" class=\"e\">&lt;</a>String&gt; <a name=\"m52\">method</a>(){\n" +
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
                " $operator<a name=\"m37\" title=\"The operator symbol &lt;&gt; is not valid.\" class=\"e\">&lt;&gt;</a> $int(){\n" +
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
                " <a title=\"The block is unexpected.\" class=\"e\">$if</a>($true){}\n" +
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
    @Test
    public void report69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $return 1;\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  $return 1;\n" +
                "  <a title=\"The code is unreachable in the function method()\" class=\"e\">$return</a> 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $if (){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">$if</a> <a title=\"There must be an expression.\" class=\"e\">(</a>){\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int v = 1;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $if (v==v){\n");
        xml_.append("  }$else $if (){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $int <a name=\"m33\">v</a> = 1;\n" +
                " $public $int <a name=\"m54\">method</a>() {\n" +
                "  $if (<a title=\"pkg.MySub.v\" href=\"#m33\">v</a>==<a title=\"pkg.MySub.v\" href=\"#m33\">v</a>){\n" +
                "  }$else <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">$if</a> <a title=\"There must be an expression.\" class=\"e\">(</a>){\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $while (){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">$while</a> <a title=\"There must be an expression.\" class=\"e\">(</a>){\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $do{}\n");
        xml_.append("  $while ();\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  $do{}\n" +
                "  <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">$while</a> <a title=\"There must be an expression.\" class=\"e\">(</a>);\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $case 1:{}\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  <a title=\"The $case block with expression 1 must be child of a block $switch.\" class=\"e\">$case</a> 1:{}\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report75Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (){}\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">$switch</a> <a title=\"There must be an expression.\" class=\"e\">(</a>){}\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report76Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyEnum e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MyEnum </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m44\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyEnum\" href=\"#m14\">MyEnum</a> <a name=\"m64\">e</a>;\n" +
                " $public $int <a name=\"m81\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#m64\">e</a>){\n" +
                "   <a title=\"There must be an expression.\" class=\"e\">$case</a><a title=\"The $case block with expression  is not constant.\" class=\"e\">:</a>\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report77Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" TWO\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyEnum e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case ONE:\n");
        xml_.append("   $case TWO:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MyEnum </a>{\n" +
                " <a name=\"m28\">TWO</a>\n" +
                "}\n" +
                "$public $class <a name=\"m49\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyEnum\" href=\"#m14\">MyEnum</a> <a name=\"m69\">e</a>;\n" +
                " $public $int <a name=\"m86\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#m69\">e</a>){\n" +
                "   $case <a title=\"The $case block with expression ONE is not constant.\" class=\"e\">ONE</a>:\n" +
                "   $case <a title=\"pkg.MyEnum.TWO\" href=\"#m28\">TWO</a>:\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report78Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case 1:\n");
        xml_.append("   $case 1:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $int <a name=\"m33\">e</a>;\n" +
                " $public $int <a name=\"m50\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#m33\">e</a>){\n" +
                "   $case 1:\n" +
                "   <a title=\"The $case block with value 1 is duplicated in the parent $switch block.\" class=\"e\">$case</a> 1:\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report79Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Integer e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case $null:\n");
        xml_.append("   $case $null:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Integer <a name=\"m36\">e</a>;\n" +
                " $public $int <a name=\"m53\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#m36\">e</a>){\n" +
                "   $case $null:\n" +
                "   <a title=\"The $case block with value  is duplicated in the parent $switch block.\" class=\"e\">$case</a> $null:\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report80Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case \"\":\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $int <a name=\"m33\">e</a>;\n" +
                " $public $int <a name=\"m50\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#m33\">e</a>){\n" +
                "   <a title=\"The $case block with value  is not a sub type of $int.\" class=\"e\">$case</a> <span class=\"s\">\"\"</span>:\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report81Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyEnum e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case $null:\n");
        xml_.append("   $case $null:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MyEnum </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m44\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyEnum\" href=\"#m14\">MyEnum</a> <a name=\"m64\">e</a>;\n" +
                " $public $int <a name=\"m81\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#m64\">e</a>){\n" +
                "   $case $null:\n" +
                "   <a title=\"The $case block with value  is duplicated in the parent $switch block.\" class=\"e\">$case</a> $null:\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report82Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $default:{}\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  <a title=\"The $default block with expression  must be child of a block $switch.\" class=\"e\">$default</a>:{}\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report83Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyEnum e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case $null:\n");
        xml_.append("   $case $null:\n");
        xml_.append("   $default:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MyEnum </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m44\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyEnum\" href=\"#m14\">MyEnum</a> <a name=\"m64\">e</a>;\n" +
                " $public $int <a name=\"m81\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#m64\">e</a>){\n" +
                "   $case $null:\n" +
                "   <a title=\"The $case block with value  is duplicated in the parent $switch block.\" class=\"e\">$case</a> $null:\n" +
                "   $default:\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report84Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyEnum e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case $null:\n");
        xml_.append("   $default:\n");
        xml_.append("   $default:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MyEnum </a>{\n" +
                "}\n" +
                "$public $class <a name=\"m44\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyEnum\" href=\"#m14\">MyEnum</a> <a name=\"m64\">e</a>;\n" +
                " $public $int <a name=\"m81\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#m64\">e</a>){\n" +
                "   $case $null:\n" +
                "   $default:\n" +
                "   <a title=\"The $default block is duplicated in the parent $switch block.\" class=\"e\">$default</a>:\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report85Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case :\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $int <a name=\"m33\">e</a>;\n" +
                " $public $int <a name=\"m50\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#m33\">e</a>){\n" +
                "   <a title=\"There must be an expression.\n" +
                "\n" +
                "The $case block with expression  is not constant.\" class=\"e\">$case</a> :\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report86Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $return 1;\n");
        xml_.append("  $int i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  $return 1;\n" +
                "  $int <a name=\"m72\">i</a><a title=\"The code is unreachable in the function method()\" class=\"e\">;</a>\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report87Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (1){\n");
        xml_.append("   $int i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  $switch (1){\n" +
                "   $int <a name=\"m75\">i</a><a title=\"The $switch block must contain only one of the blocks $case|$default.\" class=\"e\">;</a>\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report88Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int v = 1;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $if (v==v){\n");
        xml_.append("  }$elseif (){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $int <a name=\"m33\">v</a> = 1;\n" +
                " $public $int <a name=\"m54\">method</a>() {\n" +
                "  $if (<a title=\"pkg.MySub.v\" href=\"#m33\">v</a>==<a title=\"pkg.MySub.v\" href=\"#m33\">v</a>){\n" +
                "  }<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">$elseif</a> <a title=\"There must be an expression.\" class=\"e\">(</a>){\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report89Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $elseif ($true){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  <a title=\"The code is unreachable in the function method()\n" +
                "\n" +
                "The $elseif block must be preceded by one of the blocks $if|$elseif.\" class=\"e\">$elseif</a> ($true){\n" +
                "  }\n" +
                "  <a title=\"The code is unreachable in the function method()\" class=\"e\">$return</a> 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report90Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $else $if ($true){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  $else <a title=\"The code is unreachable in the function method()\n" +
                "\n" +
                "The $elseif block must be preceded by one of the blocks $if|$elseif.\" class=\"e\">$if</a> ($true){\n" +
                "  }\n" +
                "  <a title=\"The code is unreachable in the function method()\" class=\"e\">$return</a> 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report91Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $else {\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  <a title=\"The code is unreachable in the function method()\n" +
                "\n" +
                "The $else block must be preceded by one of the blocks $if|$elseif.\" class=\"e\">$else</a> {\n" +
                "  }\n" +
                "  <a title=\"The code is unreachable in the function method()\" class=\"e\">$return</a> 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report92Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $return 1;\n");
        xml_.append("  {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  $return 1;\n" +
                "  <a title=\"The code is unreachable in the function method()\" class=\"e\">{</a>\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report93Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $do{}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  <a title=\"The $do block must be followed by one of the blocks $while.\" class=\"e\">$do</a>{}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report94Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $do{}\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  <a title=\"The $do block must be followed by one of the blocks $while.\" class=\"e\">$do</a>{}\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report95Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $do{}\n");
        xml_.append("  $while($true){$int i;}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $do{}\n" +
                "  <a title=\"The $while block associated to a $do block must be empty.\" class=\"e\">$while</a>($true){$int <a name=\"m82\">i</a>;}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report96Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator<> $int(){\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\" title=\"The operator symbol &lt;&gt; is not valid.\" class=\"e\">&lt;&gt;</a> $int(){\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report97Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int($int i){\n");
        xml_.append("}\n");
        xml_.append("$operator+ $int($int i){\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\">+</a> $int($int <a name=\"m21\">i</a>){\n" +
                "}\n" +
                "$operator<a name=\"m36\" title=\"The operator $static +($int) is duplicated.\" class=\"e\">+</a> $int($int <a name=\"m48\">i</a>){\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report98Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int($int #i){\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\">+</a> $int($int <a name=\"m21\" title=\"The parameter method name #i is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">#i</a>){\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report99Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int($int i,$int i){\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$operator<a name=\"m9\">+</a> $int($int <a name=\"m21\">i</a>,$int <a name=\"m28\" title=\"The parameter function name i is duplicated.\" class=\"e\">i</a>){\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report100Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $try{}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  <a title=\"The $try block must be followed by one of the blocks $catch|$finally.\" class=\"e\">$try</a>{}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report101Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $catch{}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  <a title=\"The $catch block must be preceded by one of the blocks $catch|$try.\" class=\"e\">$catch</a>{}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report102Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $catch($int i){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  <a title=\"The $catch block must be preceded by one of the blocks $catch|$try.\" class=\"e\">$catch</a>($int <a name=\"m67\">i</a>){}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report103Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $finally{}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  <a title=\"The $finally block must be preceded by one of the blocks $catch|$try.\" class=\"e\">$finally</a>{}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report104Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $try{}\n");
        xml_.append("  $catch($int #i){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $try{}\n" +
                "  $catch($int <a name=\"m76\" title=\"The variable name #i is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope. class=\"e\"\">#i</a>){}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report105Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $for($int #v:$new $int[]{}){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $for($int <a name=\"m65\" title=\"The variable name #v is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope.\" class=\"e\">#v</a>:$new $int[]{}){}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report106Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $for($int v:$new $int[]{}){$int #v;}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $for($int <a name=\"m65\">v</a>:$new $int[]{}){$int <a name=\"m87\" title=\"The variable name #v is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope.\" class=\"e\">#v</a>;}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report107Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $iterableTable<$int,$int> it = $null;\n");
        xml_.append("  $for($int #v, $int k:it){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $iterableTable&lt;$int,$int&gt; <a name=\"m81\">it</a> = $null;\n" +
                "  $for($int <a name=\"m105\" title=\"The variable name #v is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope. class=\"e\"\">#v</a>, $int <a name=\"m114\">k</a>:<a href=\"#m81\">it</a>){}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report108Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $iterableTable<$int,$int> it = $null;\n");
        xml_.append("  $for($int v, $int #k:it){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $iterableTable&lt;$int,$int&gt; <a name=\"m81\">it</a> = $null;\n" +
                "  $for($int <a name=\"m105\">v</a>, $int <a name=\"m113\" title=\"The variable name #k is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope. class=\"e\"\">#k</a>:<a href=\"#m81\">it</a>){}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report109Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $iterableTable<$int,$int> it = $null;\n");
        xml_.append("  $for($int v, $int k:it){$int #v=1;}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $iterableTable&lt;$int,$int&gt; <a name=\"m81\">it</a> = $null;\n" +
                "  $for($int <a name=\"m105\">v</a>, $int <a name=\"m113\">k</a>:<a href=\"#m81\">it</a>){$int <a name=\"m124\" title=\"The variable name #v is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope.\" class=\"e\">#v</a>=1;}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report110Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $for($int v:$new $int[]{}){$int #v,w;}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $for($int <a name=\"m65\">v</a>:$new $int[]{}){$int <a name=\"m87\" title=\"The variable name #v is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope.\" class=\"e\">#v</a>,<a name=\"m90\">w</a>;}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report111Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $iterableTable<$int,$int> it = $null;\n");
        xml_.append("  $for($int v, $int k:it){$int #v=1,w;}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $iterableTable&lt;$int,$int&gt; <a name=\"m81\">it</a> = $null;\n" +
                "  $for($int <a name=\"m105\">v</a>, $int <a name=\"m113\">k</a>:<a href=\"#m81\">it</a>){$int <a name=\"m124\" title=\"The variable name #v is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope.\" class=\"e\">#v</a>=1,<a name=\"m129\">w</a>;}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report112Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $int <a name=\"m41\">method</a>() {\n" +
                "  <a title=\"There must be an expression.\n" +
                "\n" +
                "The type java.lang.Object cannot be implicitly cast to $int\" class=\"e\">$return</a>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report113Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  <a title=\"The type cannot be the key word $void.\" class=\"e\">$return</a> 1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report114Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $for($int #i;;){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $for($int <a name=\"m65\" title=\"The variable name #i is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope.\" class=\"e\">#i</a>;;){}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report115Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $for($int #i=0;$true;){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $for($int <a name=\"m65\" title=\"The variable name #i is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope.\" class=\"e\">#i</a>=0;$true;){}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report116Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $for($int #i,j;;$true){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $for($int <a name=\"m65\" title=\"The variable name #i is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope.\" class=\"e\">#i</a>,<a name=\"m68\">j</a>;;$true){}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report117Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $for($int #i=0,j;;$true){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $for($int <a name=\"m65\" title=\"The variable name #i is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope.\" class=\"e\">#i</a>=0,<a name=\"m70\">j</a>;;$true){}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report118Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $for(;;){$throw;}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $for(;;){<a title=\"There must be an expression.\" class=\"e\">$throw</a>;}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report119Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $iter($int #i=0;1;1){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $iter($int <a name=\"m66\" title=\"The variable name #i is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit. It must not be the name of an other variable of the scope.\" class=\"e\">#i</a>=0;1;1){}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report120Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $iter($int i=0;;){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  <a title=\"Bad index by parsing.\n" +
                "\n" +
                "The type java.lang.Object cannot be implicitly cast to $int\n" +
                "\n" +
                "The type java.lang.Object cannot be implicitly cast to $int\" class=\"e\">$iter</a>($int <a name=\"m66\">i</a>=0;<a title=\"The expression part is empty.\" class=\"e\">;</a>){}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report121Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $if($true)#t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $if($true)<a name=\"m65\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report122Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $if($true)t{\n");
        xml_.append("   $if($true)t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $if($true)<a name=\"m65\">t</a>{\n" +
                "   $if($true)<a name=\"m81\" title=\"The label is duplicated.\" class=\"e\">t</a>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report123Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $switch(1)#t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $switch(1)<a name=\"m65\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report124Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $while($true)#t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $while($true)<a name=\"m68\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report125Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $do #t{}\n");
        xml_.append("  $while($true);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $do <a name=\"m59\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>{}\n" +
                "  $while($true);\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report126Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $try #t{}\n");
        xml_.append("  $catch{}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $try <a name=\"m60\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>{}\n" +
                "  $catch{}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report127Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $for(;;)#t{}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $for(;;)<a name=\"m63\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>{}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report128Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $iter($int i=0;1;1)#t{}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $iter($int <a name=\"m66\">i</a>=0;1;1)<a name=\"m74\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>{}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report129Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $for($int i:$new $int[]{})#t{}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $for($int <a name=\"m65\">i</a>:$new $int[]{})<a name=\"m81\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>{}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report130Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $iterableTable<$int,$int> it = $null;\n");
        xml_.append("  $for($int i,$int j:it)#t{}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $iterableTable&lt;$int,$int&gt; <a name=\"m81\">it</a> = $null;\n" +
                "  $for($int <a name=\"m105\">i</a>,$int <a name=\"m112\">j</a>:<a href=\"#m81\">it</a>)<a name=\"m117\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>{}\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report131Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $break;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  <a title=\"The $break block must be inner of the blocks $switch|$for|$foreach|$do|$iter|$while.\" class=\"e\">$break</a>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report132Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $break lab;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $break <a title=\"The $break block with label lab must be inner of a labelled with $switch|$try|$catch|$finally|$if|$elseif|$else|$for|$foreach|$do|$iter|$while block.\" class=\"e\">lab</a>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report133Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $if($true)lab{\n");
        xml_.append("   $break lab;\n");
        xml_.append("  }\n");
        xml_.append("  $continue;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $if($true)<a name=\"m65\">lab</a>{\n" +
                "   $break <a href=\"#65\">lab</a>;\n" +
                "  }\n" +
                "  <a title=\"The $continue block must be inner of the blocks $for|$foreach|$do|$iter|$while.\" class=\"e\">$continue</a>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report134Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $continue;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  <a title=\"The $continue block must be inner of the blocks $for|$foreach|$do|$iter|$while.\" class=\"e\">$continue</a>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report135Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $continue lab;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $continue <a title=\"The $continue block with label lab must be inner of a labelled with $for|$foreach|$do|$iter|$while block.\" class=\"e\">lab</a>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report136Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $iter($int i=0;1;1)lab{\n");
        xml_.append("   $continue lab;\n");
        xml_.append("  }\n");
        xml_.append("  $break;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  $iter($int <a name=\"m66\">i</a>=0;1;1)<a name=\"m74\">lab</a>{\n" +
                "   $continue <a href=\"#74\">lab</a>;\n" +
                "  }\n" +
                "  <a title=\"The $break block must be inner of the blocks $switch|$for|$foreach|$do|$iter|$while.\" class=\"e\">$break</a>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report137Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  Object v=$class();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  Object <a name=\"m62\">v</a>=$class<a title=\"There must be a type.\" class=\"e\">(</a>);\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report138Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  Object v=$class(,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  Object <a name=\"m62\">v</a>=$class(<a title=\"The type , is unknown.\" class=\"e\">,</a>);\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report139Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  Object v=$()1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  Object <a name=\"m62\">v</a>=<a title=\"There must be a type.\" class=\"e\">$</a>()1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report140Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  Object v=$(a)1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  Object <a name=\"m62\">v</a>=$(<a title=\"The type a is unknown.\" class=\"e\">a</a>)1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report141Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  Object v=$(a<b,c>d)1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  Object <a name=\"m62\">v</a>=$(<a title=\"The type a&lt;b,c&gt;d is unknown.\" class=\"e\">a&lt;b,c&gt;d</a>)1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report142Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MySub {\n");
        xml_.append(" $int annot();\n");
        xml_.append(" $int annot();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MySub </a>{\n" +
                " $int <a name=\"m38\">annot</a>();\n" +
                " $int <a name=\"m53\" title=\"The method annot() is duplicated.\" class=\"e\">annot</a>();\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report143Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void(){\n");
        xml_.append(" }\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.Ex </a>{\n" +
                " $public $static $void<a name=\"m46\" title=\"The method name  is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">(</a>){\n" +
                " }\n" +
                "}</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report144Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyParam v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyParam<T>{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"The type pkg.MyParam is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyParam\" href=\"#m56\" class=\"e\">MyParam</a> <a name=\"m36\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m56\">pkg.MyParam</a>&lt;<a name=\"m68\">T</a>&gt;{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report145Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  Object v=$(MyParam<String>)1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyParam{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $public $void <a name=\"m42\">method</a>() {\n" +
                "  Object <a name=\"m62\">v</a>=$(<a title=\"pkg.MyParam\" href=\"#m105\">MyParam</a>&lt;String<a title=\"The type pkg.MyParam is not parameterized correctly.\" class=\"e\">&gt;</a>)1;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m105\">pkg.MyParam</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report146Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyParam<MyCl> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyParam<T,S>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyParam\" href=\"#m62\">MyParam</a>&lt;<a title=\"pkg.MyCl\" href=\"#m97\">MyCl</a><a title=\"The type pkg.MyParam is not parameterized correctly.\" class=\"e\">&gt;</a> <a name=\"m42\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m62\">pkg.MyParam</a>&lt;<a name=\"m74\">T</a>,<a name=\"m76\">S</a>&gt;{\n" +
                "}\n" +
                "$public $class <a name=\"m97\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report147Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyParam<MyCl> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyParam<T:MyCo>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCo{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyParam\" href=\"#m62\">MyParam</a><a title=\"The type pkg.MyParam&lt;pkg.MyCl&gt; is not parameterized correctly.\" class=\"e\">&lt;</a><a title=\"pkg.MyCl\" href=\"#m100\">MyCl</a>&gt; <a name=\"m42\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m62\">pkg.MyParam</a>&lt;<a name=\"m74\">T</a>:<a title=\"pkg.MyCo\" href=\"#m127\">MyCo</a>&gt;{\n" +
                "}\n" +
                "$public $class <a name=\"m100\">pkg.MyCl</a>{\n" +
                "}\n" +
                "$public $class <a name=\"m127\">pkg.MyCo</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report148Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Inexist<MyCl> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"The type Inexist&lt;MyCl&gt; is unknown.\" class=\"e\">Inexist</a>&lt;<a title=\"pkg.MyCl\" href=\"#m62\">MyCl</a>&gt; <a name=\"m42\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m62\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report149Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $iterable<MyCl.> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $iterable&lt;<a title=\"pkg.MyCl\" href=\"#m65\">MyCl</a><a title=\"The type $iterable&lt;MyCl.&gt; is unknown.\" class=\"e\">.</a>&gt; <a name=\"m45\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m65\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report150Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $iterable<MyCl.Inner.> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append(" $public $class Inner{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $iterable&lt;<a title=\"pkg.MyCl\" href=\"#m71\">MyCl</a>.<a title=\"pkg.MyCl..Inner\" href=\"#m97\">Inner</a><a title=\"The type $iterable&lt;MyCl.Inner.&gt; is unknown.\" class=\"e\">.</a>&gt; <a name=\"m51\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m71\">pkg.MyCl</a>{\n" +
                " $public $class <a name=\"m97\">Inner</a>{\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report151Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $iterable<Inexist[]> v;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $iterable&lt;<a title=\"The type $iterable&lt;Inexist[]&gt; is unknown.\" class=\"e\">Inexist</a>[]&gt; <a name=\"m49\">v</a>;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report152Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $iterable<?Inexist> v;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $iterable&lt;?<a title=\"The type $iterable&lt;?Inexist&gt; is unknown.\" class=\"e\">Inexist</a>&gt; <a name=\"m48\">v</a>;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report153Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $iterable<MyCl.Inner> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append(" $public $class Inner<T>{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $iterable<a title=\"The type java.lang.$iterable&lt;pkg.MyCl..Inner&gt; is not parameterized correctly.\" class=\"e\">&lt;</a><a title=\"pkg.MyCl\" href=\"#m70\">MyCl</a><a title=\"The type pkg.MyCl..Inner is not parameterized correctly.\" class=\"e\">.</a><a title=\"pkg.MyCl..Inner\" href=\"#m96\">Inner</a>&gt; <a name=\"m50\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m70\">pkg.MyCl</a>{\n" +
                " $public $class <a name=\"m96\">Inner</a>&lt;<a name=\"m102\">T</a>&gt;{\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report154Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $iterable<MyCl.Inner[]> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append(" $public $class Inner<T>{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $iterable<a title=\"The type java.lang.$iterable&lt;[pkg.MyCl..Inner&gt; is not parameterized correctly.\" class=\"e\">&lt;</a><a title=\"pkg.MyCl\" href=\"#m72\">MyCl</a><a title=\"The type pkg.MyCl..Inner is not parameterized correctly.\" class=\"e\">.</a><a title=\"pkg.MyCl..Inner\" href=\"#m98\">Inner</a>[]&gt; <a name=\"m52\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m72\">pkg.MyCl</a>{\n" +
                " $public $class <a name=\"m98\">Inner</a>&lt;<a name=\"m104\">T</a>&gt;{\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report155Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $iterable<MyCl[]> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl<T>{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $iterable<a title=\"The type java.lang.$iterable&lt;[pkg.MyCl&gt; is not parameterized correctly.\" class=\"e\">&lt;</a><a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#m66\" class=\"e\">MyCl</a>[]&gt; <a name=\"m46\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m66\">pkg.MyCl</a>&lt;<a name=\"m75\">T</a>&gt;{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report156Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $iterable<?MyCl> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl<T>{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $iterable<a title=\"The type java.lang.$iterable&lt;?pkg.MyCl&gt; is not parameterized correctly.\" class=\"e\">&lt;</a>?<a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#m65\" class=\"e\">MyCl</a>&gt; <a name=\"m45\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m65\">pkg.MyCl</a>&lt;<a name=\"m74\">T</a>&gt;{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report157Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $iterable<MyCl> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl<T>{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $iterable<a title=\"The type java.lang.$iterable&lt;pkg.MyCl&gt; is not parameterized correctly.\" class=\"e\">&lt;</a><a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#m64\" class=\"e\">MyCl</a>&gt; <a name=\"m44\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m64\">pkg.MyCl</a>&lt;<a name=\"m73\">T</a>&gt;{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report158Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyParam<MyCl.Inner[]> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append(" $public $class Inner<T>{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyParam<S>{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyParam\" href=\"#m126\">MyParam</a><a title=\"The type pkg.MyParam&lt;[pkg.MyCl..Inner&gt; is not parameterized correctly.\" class=\"e\">&lt;</a><a title=\"pkg.MyCl\" href=\"#m70\">MyCl</a><a title=\"The type pkg.MyCl..Inner is not parameterized correctly.\" class=\"e\">.</a><a title=\"pkg.MyCl..Inner\" href=\"#m96\">Inner</a>[]&gt; <a name=\"m50\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m70\">pkg.MyCl</a>{\n" +
                " $public $class <a name=\"m96\">Inner</a>&lt;<a name=\"m102\">T</a>&gt;{\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m126\">pkg.MyParam</a>&lt;<a name=\"m138\">S</a>&gt;{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report159Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyParam<MyCl[]> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl<T>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyParam<S>{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyParam\" href=\"#m94\">MyParam</a><a title=\"The type pkg.MyParam&lt;[pkg.MyCl&gt; is not parameterized correctly.\" class=\"e\">&lt;</a><a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#m64\" class=\"e\">MyCl</a>[]&gt; <a name=\"m44\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m64\">pkg.MyCl</a>&lt;<a name=\"m73\">T</a>&gt;{\n" +
                "}\n" +
                "$public $class <a name=\"m94\">pkg.MyParam</a>&lt;<a name=\"m106\">S</a>&gt;{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report160Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyParam<?MyCl> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl<T>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyParam<S>{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyParam\" href=\"#m93\">MyParam</a><a title=\"The type pkg.MyParam&lt;?pkg.MyCl&gt; is not parameterized correctly.\" class=\"e\">&lt;</a>?<a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#m63\" class=\"e\">MyCl</a>&gt; <a name=\"m43\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m63\">pkg.MyCl</a>&lt;<a name=\"m72\">T</a>&gt;{\n" +
                "}\n" +
                "$public $class <a name=\"m93\">pkg.MyParam</a>&lt;<a name=\"m105\">S</a>&gt;{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report161Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyParam<MyCl> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl<T>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyParam<S>{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyParam\" href=\"#m92\">MyParam</a><a title=\"The type pkg.MyParam&lt;pkg.MyCl&gt; is not parameterized correctly.\" class=\"e\">&lt;</a><a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#m62\" class=\"e\">MyCl</a>&gt; <a name=\"m42\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m62\">pkg.MyCl</a>&lt;<a name=\"m71\">T</a>&gt;{\n" +
                "}\n" +
                "$public $class <a name=\"m92\">pkg.MyParam</a>&lt;<a name=\"m104\">S</a>&gt;{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report162Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" $int v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m60\">MyCl</a>\n" +
                " $int <a name=\"m40\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m60\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report163Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" ONE;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m56\">MyCl</a>\n" +
                " <a name=\"m34\">ONE</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m56\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report164Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" ONE{};\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m58\">MyCl</a>\n" +
                " <a name=\"m34\">ONE</a>{};\n" +
                "}\n" +
                "$public $class <a name=\"m58\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report165Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" $int v();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m67\">MyCl</a>\n" +
                " $int <a name=\"m45\">v</a>();\n" +
                "}\n" +
                "$public $class <a name=\"m67\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report166Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" $int v();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m66\">MyCl</a>\n" +
                " $int <a name=\"m44\">v</a>();\n" +
                "}\n" +
                "$public $class <a name=\"m66\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report167Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" MySub(){}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m66\">MyCl</a>\n" +
                " <a name=\"m39\">MySub(</a>){}\n" +
                "}\n" +
                "$public $class <a name=\"m66\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report168Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" $operator+ $int($int i){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m97\">MyCl</a>\n" +
                " $operator<a name=\"m48\">+</a> $int($int <a name=\"m60\">i</a>){\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m97\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report169Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyCl\n");
        xml_.append("$operator+ [$static;] $int($int i){\n");
        xml_.append(" $return 0;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"The type MyCl is unknown.\" class=\"e\">MyCl</a>\n" +
                "$operator<a name=\"m15\">+</a> [<span class=\"i\">$static</span>;] $int($int <a name=\"m38\">i</a>){\n" +
                " $return 0;\n" +
                "}\n" +
                "$public $class <a name=\"m71\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report170Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" $public $class [$static;] Inner{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m92\">MyCl</a>\n" +
                " $public $class [<span class=\"i\">$static</span>;] <a name=\"m65\">Inner</a>{\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m92\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report171Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" $int $this($int i);\n");
        xml_.append(" $void $this($int i);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m98\">MyCl</a>\n" +
                " $int <a name=\"m44\">$this</a>($int <a name=\"m55\">i</a>);\n" +
                " $void <a name=\"m66\">$this</a>($int <a name=\"m77\">i</a>);\n" +
                "}\n" +
                "$public $class <a name=\"m98\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report172Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" $int $this($int i);\n");
        xml_.append(" @MyCl\n");
        xml_.append(" $void $this($int i);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " $int <a name=\"m37\">$this</a>($int <a name=\"m48\">i</a>);\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m98\">MyCl</a>\n" +
                " $void <a name=\"m66\">$this</a>($int <a name=\"m77\">i</a>);\n" +
                "}\n" +
                "$public $class <a name=\"m98\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report173Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" $int $this(@MyCl $int i);\n");
        xml_.append(" $void $this($int i);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " $int <a name=\"m37\">$this</a>(<a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m97\">MyCl</a> $int <a name=\"m54\">i</a>);\n" +
                " $void <a name=\"m65\">$this</a>($int <a name=\"m76\">i</a>);\n" +
                "}\n" +
                "$public $class <a name=\"m97\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report174Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" $int $this($int i);\n");
        xml_.append(" $void $this(@MyCl $int i);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " $int <a name=\"m37\">$this</a>($int <a name=\"m48\">i</a>);\n" +
                " $void <a name=\"m59\">$this</a>(<a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m97\">MyCl</a> $int <a name=\"m76\">i</a>);\n" +
                "}\n" +
                "$public $class <a name=\"m97\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report175Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" $static $int explicit(MySub i){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m104\">MyCl</a>\n" +
                " $static $int <a name=\"m52\">explicit</a>(<a title=\"pkg.MySub\" href=\"#m19\">MySub</a> <a name=\"m67\">i</a>){\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m104\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report176Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" $static MySub explicit($int i){\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m108\">MyCl</a>\n" +
                " $static <a title=\"pkg.MySub\" href=\"#m19\">MySub</a> <a name=\"m53\">explicit</a>($int <a name=\"m67\">i</a>){\n" +
                "  $return $null;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m108\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report177Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" $static $int explicit(@MyCl MySub i){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " $static $int <a name=\"m45\">explicit</a>(<a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m103\">MyCl</a> <a title=\"pkg.MySub\" href=\"#m19\">MySub</a> <a name=\"m66\">i</a>){\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m103\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report178Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" $static MySub explicit(@MyCl $int i){\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " $static <a title=\"pkg.MySub\" href=\"#m19\">MySub</a> <a name=\"m46\">explicit</a>(<a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m107\">MyCl</a> $int <a name=\"m66\">i</a>){\n" +
                "  $return $null;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\"m107\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report179Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" $int v(@MyCl $int i);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " $int <a name=\"m37\">v</a>(<a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m71\">MyCl</a> $int <a name=\"m50\">i</a>);\n" +
                "}\n" +
                "$public $class <a name=\"m71\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report180Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" MySub(@MyCl $int i){}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $interface <a name=\"m19\">pkg.MySub </a>{\n" +
                " <a name=\"m32\">MySub(</a><a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m71\">MyCl</a> $int <a name=\"m49\">i</a>){}\n" +
                "}\n" +
                "$public $class <a name=\"m71\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report181Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" ONE;\n");
        xml_.append(" MySub(){}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m67\">MyCl</a>\n" +
                " <a name=\"m34\" title=\"pkg.MySub.pkg.MySub()\" href=\"#m40\">ONE</a>;\n" +
                " <a name=\"m40\">MySub(</a>){}\n" +
                "}\n" +
                "$public $class <a name=\"m67\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report182Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" ONE(1);\n");
        xml_.append(" MySub($int i){}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m76\">MyCl</a>\n" +
                " <a name=\"m34\" title=\"pkg.MySub.pkg.MySub($int)\" href=\"#m43\">ONE</a>(1);\n" +
                " <a name=\"m43\">MySub(</a>$int <a name=\"m54\">i</a>){}\n" +
                "}\n" +
                "$public $class <a name=\"m76\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report183Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" $int v()1;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m68\">MyCl</a>\n" +
                " $int <a name=\"m45\">v</a>()1;\n" +
                "}\n" +
                "$public $class <a name=\"m68\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report184Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" ONE{};\n");
        xml_.append(" MySub(){}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m69\">MyCl</a>\n" +
                " <a name=\"m34\">ONE</a>{};\n" +
                " <a name=\"m42\">MySub(</a>){}\n" +
                "}\n" +
                "$public $class <a name=\"m69\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report185Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" ONE(1){($int i){}};\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m71\">MyCl</a>\n" +
                " <a name=\"m34\" title=\"pkg.MySub-ONE.pkg.MySub-ONE($int)\" href=\"#m41\">ONE</a>(1){<a name=\"m41\">(</a>$int <a name=\"m47\">i</a>){}};\n" +
                "}\n" +
                "$public $class <a name=\"m71\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report186Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" $void m(){\n");
        xml_.append("  $var i=0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"The type MyCl is unknown.\" class=\"e\">MyCl</a>\n" +
                " $void <a name=\"m41\">m</a>(){\n" +
                "  <b title=\"$int\">$var</b> <a name=\"m53\">i</a>=0;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report187Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $void m(){\n");
        xml_.append("  $var i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $void <a name=\"m34\">m</a>(){\n" +
                "  <a title=\"The inferring type $var is not defined for the variables i.\" class=\"e\">$var</a> <a name=\"m46\">i</a>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report188Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" @MyCl\n");
        xml_.append(" $void m(){\n");
        xml_.append("  $for($var i=0;;){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"The type MyCl is unknown.\" class=\"e\">MyCl</a>\n" +
                " $void <a name=\"m41\">m</a>(){\n" +
                "  $for(<b title=\"$int\">$var</b> <a name=\"m58\">i</a>=0;;){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report189Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $void m(){\n");
        xml_.append("  $for($var i;;){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $void <a name=\"m34\">m</a>(){\n" +
                "  $for(<a title=\"The inferring type $var is not defined for the variables i.\" class=\"e\">$var</a> <a name=\"m51\">i</a>;;){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report190Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot({\"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>@<a title=\"pkg.MyAnnot\" href=\"#m35\">MyAnnot</a>(<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">{</a><span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\"m35\">pkg.MyAnnot </a>{\n" +
                " $int[] <a name=\"m57\">v</a>();\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report191Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot( {\"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>@<a title=\"pkg.MyAnnot\" href=\"#m36\">MyAnnot</a>( <a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">{</a><span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\"m36\">pkg.MyAnnot </a>{\n" +
                " $int[] <a name=\"m58\">v</a>();\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report192Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot({ \"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>@<a title=\"pkg.MyAnnot\" href=\"#m36\">MyAnnot</a>(<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">{</a> <span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\"m36\">pkg.MyAnnot </a>{\n" +
                " $int[] <a name=\"m58\">v</a>();\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report193Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MySub {\n");
        xml_.append(" @MyCl( @MyCl)\n");
        xml_.append(" ONE;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $enum <a name=\"m14\">pkg.MySub </a>{\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m64\">MyCl</a>( <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#m64\">MyCl</a>)\n" +
                " <a name=\"m42\">ONE</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m64\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report194Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " $int<a title=\"No field could be retrieved.\" class=\"e\">;</a>\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report195Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $final;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " {\n" +
                "  $final<a title=\"There must be an expression.\" class=\"e\">;</a>\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report196Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $int i\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " {\n" +
                "  <a title=\"The expression part is empty.\" class=\"e\">$int i</a>\n" +
                " <a title=\"Bad index by parsing.\" class=\"e\">}</a>\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report197Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $if {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " {\n" +
                "  <a title=\"Bad index by parsing.\n" +
                "\n" +
                "The type java.lang.Object is unexpected.\" class=\"e\">$if</a><a title=\"There must be an expression.\" class=\"e\"> </a>{\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report198Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $static {\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " {\n" +
                "  <a title=\"The block is unexpected.\" class=\"e\">$static</a> {\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report199Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $int i\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"Bad index by parsing.\" class=\"e\">{</a>\n" +
                "  $int i\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report200Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int i\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class pkg.MySub {\n" +
                " $int i<a title=\"Bad index by parsing.\" class=\"e\">\n" +
                "</a></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report201Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyParam<MySecond,MyFirst> i;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyParam<T:MyFirst,S:MySecond>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyFirst{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySecond{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyParam\" href=\"#m74\">MyParam</a><a title=\"The type pkg.MyParam&lt;pkg.MySecond,pkg.MyFirst&gt; is not parameterized correctly.\" class=\"e\">&lt;</a><a title=\"pkg.MySecond\" href=\"#m156\">MySecond</a><a title=\"The type pkg.MyParam&lt;pkg.MySecond,pkg.MyFirst&gt; is not parameterized correctly.\" class=\"e\">,</a><a title=\"pkg.MyFirst\" href=\"#m126\">MyFirst</a>&gt; <a name=\"m54\">i</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m74\">pkg.MyParam</a>&lt;<a name=\"m86\">T</a>:<a title=\"pkg.MyFirst\" href=\"#m126\">MyFirst</a>,<a name=\"m96\">S</a>:<a title=\"pkg.MySecond\" href=\"#m156\">MySecond</a>&gt;{\n" +
                "}\n" +
                "$public $class <a name=\"m126\">pkg.MyFirst</a>{\n" +
                "}\n" +
                "$public $class <a name=\"m156\">pkg.MySecond</a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report202Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Inexist.Inner v;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"The type Inexist.Inner is unknown.\" class=\"e\">Inexist</a>.Inner <a name=\"m42\">v</a>;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report203Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.Inexist.Inner v;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"The type pkg.Inexist.Inner is unknown.\" class=\"e\">pkg.Inexist</a>.Inner <a name=\"m46\">v</a>;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report204Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.MyEnum..TWO v;\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append(" ONE{}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyEnum\" href=\"#m63\">pkg.MyEnum</a>..<a title=\"The type pkg.MyEnum..TWO is unknown.\" class=\"e\">TWO</a> <a name=\"m44\">v</a>;\n" +
                "}\n" +
                "$public $enum <a name=\"m63\">pkg.MyEnum</a>{\n" +
                " <a name=\"m76\">ONE</a>{}\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report205Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.MyEnum..ONE.Inexist v;\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append(" ONE{\n");
        xml_.append("  $public $class Inner{\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyEnum\" href=\"#m71\">pkg.MyEnum</a>..<a title=\"pkg.MyEnum-ONE\" href=\"#m84\">ONE</a>.<a title=\"The type pkg.MyEnum..ONE.Inexist is unknown.\" class=\"e\">Inexist</a> <a name=\"m52\">v</a>;\n" +
                "}\n" +
                "$public $enum <a name=\"m71\">pkg.MyEnum</a>{\n" +
                " <a name=\"m84\">ONE</a>{\n" +
                "  $public $class <a name=\"m106\">Inner</a>{\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report206Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub.Inner v;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MySub\" href=\"#m15\">MySub</a>.<a title=\"The type MySub.Inner is unknown.\" class=\"e\">Inner</a> <a name=\"m40\">v</a>;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report207Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.MySub.Inner v;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MySub\" href=\"#m15\">pkg.MySub</a>.<a title=\"The type pkg.MySub.Inner is unknown.\" class=\"e\">Inner</a> <a name=\"m44\">v</a>;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report208Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.MyEnum..ONE<String> v;\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append(" ONE{}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyEnum\" href=\"#m71\">pkg.MyEnum</a>..<a title=\"The type pkg.MyEnum..ONE&lt;String&gt; is unknown.\" class=\"e\">ONE</a>&lt;String&gt; <a name=\"m52\">v</a>;\n" +
                "}\n" +
                "$public $enum <a name=\"m71\">pkg.MyEnum</a>{\n" +
                " <a name=\"m84\">ONE</a>{}\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report209Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.MyEnum<String>..ONE v;\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.MyEnum<T>{\n");
        xml_.append(" ONE<String>{}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyEnum\" href=\"#m71\">pkg.MyEnum</a>&lt;String&gt;..<a title=\"The type pkg.MyEnum&lt;String&gt;..ONE is unknown.\" class=\"e\">ONE</a> <a name=\"m52\">v</a>;\n" +
                "}\n" +
                "$public $enum <a name=\"m71\">pkg.MyEnum</a>&lt;<a name=\"m82\">T</a>&gt;{\n" +
                " <a name=\"m87\">ONE</a>&lt;String&gt;{}\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report210Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.MyClass<String>.Inner v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyClass<T>{\n");
        xml_.append(" $public $static $class Inner{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyClass\" href=\"#m74\">pkg.MyClass</a>&lt;String&gt;.<a title=\"The type pkg.MyClass&lt;String&gt;.Inner is unknown.\" class=\"e\">Inner</a> <a name=\"m54\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m74\">pkg.MyClass</a>&lt;<a name=\"m86\">T</a>&gt;{\n" +
                " $public $static $class <a name=\"m114\">Inner</a>{\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report211Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.MyClass..Inner v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyClass{\n");
        xml_.append(" $public $static $class Inner{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyClass\" href=\"#m67\">pkg.MyClass</a>..<a title=\"The type pkg.MyClass..Inner is unknown.\" class=\"e\">Inner</a> <a name=\"m47\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m67\">pkg.MyClass</a>{\n" +
                " $public $static $class <a name=\"m104\">Inner</a>{\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report212Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.MyClass..Inner v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyClass<T>{\n");
        xml_.append(" $public $static $class Inner{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " <a title=\"pkg.MyClass\" href=\"#m67\">pkg.MyClass</a>..<a title=\"The type pkg.MyClass..Inner is unknown.\" class=\"e\">Inner</a> <a name=\"m47\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\"m67\">pkg.MyClass</a>&lt;<a name=\"m79\">T</a>&gt;{\n" +
                " $public $static $class <a name=\"m107\">Inner</a>{\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report213Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(?);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue(<a title=\"The type ? is unknown.\" class=\"e\">?</a>);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report214Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(?String);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue(<a title=\"The type ?String is unknown.\" class=\"e\">?</a>String);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report215Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($iterable<$void>);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue($iterable&lt;<a title=\"The type $iterable&lt;$void&gt; is unknown.\" class=\"e\">$void</a>&gt;);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report216Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($Fct<$void,$int>);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue($Fct&lt;<a title=\"The type $Fct&lt;$void,$int&gt; is unknown.\" class=\"e\">$void</a>,$int&gt;);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report217Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($Fct<?$int>);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue($Fct&lt;<a title=\"The type $Fct&lt;?$int&gt; is unknown.\" class=\"e\">?</a>$int&gt;);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report218Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($void.$int);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue(<a title=\"The type $void.$int is unknown.\" class=\"e\">$void</a>.$int);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report219Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($void[]);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue(<a title=\"The type $void[] is unknown.\" class=\"e\">$void</a>[]);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report220Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($void);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue(<a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report221Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($Fct<a<b,c>d>);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue($Fct<a title=\"The type $Fct&lt;a&lt;b,c&gt;d&gt; is unknown.\" class=\"e\">&lt;</a>a&lt;b,c&gt;d&gt;);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report222Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($iterable<!>);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue($iterable<a title=\"The type $iterable&lt;!&gt; is unknown.\" class=\"e\">&lt;</a>!&gt;);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report223Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($iterable<$iterable<!>>);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue($iterable&lt;$iterable<a title=\"The type $iterable&lt;$iterable&lt;!&gt;&gt; is unknown.\" class=\"e\">&lt;</a>!&gt;&gt;);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report224Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(!);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue(<a title=\"The type ! is unknown.\" class=\"e\">!</a>);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report225Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(a<b);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue(<a title=\"The type a&lt;b is unknown.\" class=\"e\">a&lt;b</a>);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report226Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(!String);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue(<a title=\"The type !String is unknown.\" class=\"e\">!</a>String);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report227Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=explicit(Object,)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=explicit(Object,<a title=\"There must be a type.\" class=\"e\">)</a>$null;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report228Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=explicit(Object,Object,)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=explicit(Object,Object,<a title=\"There must be a type.\" class=\"e\">)</a>$null;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report229Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=explicit(Object,,Object)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=explicit(Object,<a title=\"There must be a type.\" class=\"e\">,</a>Object)$null;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report230Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=explicit(Object,,)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=explicit(Object,<a title=\"There must be a type.\" class=\"e\">,</a><a title=\"There must be a type.\" class=\"e\">)</a>$null;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report231Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$(Object,)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$(Object,<a title=\"There must be a type.\" class=\"e\">)</a>$null;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report232Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$(Object,Object,)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$(Object,Object,<a title=\"There must be a type.\" class=\"e\">)</a>$null;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report233Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$(Object,,Object)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$(Object,<a title=\"There must be a type.\" class=\"e\">,</a>Object)$null;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report234Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$(Object,,)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$(Object,<a title=\"There must be a type.\" class=\"e\">,</a><a title=\"There must be a type.\" class=\"e\">)</a>$null;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report235Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id(String<a title=\"There must be a type.\" class=\"e\">,</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report236Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,$static,));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id(String,$static<a title=\"There must be a type.\" class=\"e\">,</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report237Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,0,));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id(String,0<a title=\"There must be a type.\" class=\"e\">,</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report238Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,,));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id(String<a title=\"There must be a type.\" class=\"e\">,</a><a title=\"There must be a type.\" class=\"e\">,</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report239Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,$static,0,));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id(String,$static,0<a title=\"There must be a type.\" class=\"e\">,</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report240Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(!MySub);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue(<a title=\"The type !MySub is unknown.\" class=\"e\">!</a><a title=\"pkg.MySub\" href=\"#m15\">MySub</a>);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report241Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(?MySub);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=$defaultValue(<a title=\"The type ?MySub is unknown.\" class=\"e\">?</a><a title=\"pkg.MySub\" href=\"#m15\">MySub</a>);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report242Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,,String));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id(String<a title=\"There must be a type.\" class=\"e\">,</a>,String));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report243Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,$void));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id(String,<a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report244Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id(<a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report245Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(pkg.Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id(<a title=\"The type pkg.Inexist is unknown.\" class=\"e\">pkg.Inexist</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report246Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(MySub.Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id(<a title=\"pkg.MySub\" href=\"#m15\">MySub</a>.<a title=\"The type MySub.Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report247Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(pkg.MySub.Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id(<a title=\"pkg.MySub\" href=\"#m15\">pkg.MySub</a>.<a title=\"The type pkg.MySub.Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report248Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id());\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id<a title=\"There must be a type.\" class=\"e\">(</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report249Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id($void));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id(<a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report250Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id( $void));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id( <a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report251Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id( Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id( <a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report252Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id( pkg.Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id( <a title=\"The type pkg.Inexist is unknown.\" class=\"e\">pkg.Inexist</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report253Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id( MySub.Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id( <a title=\"pkg.MySub\" href=\"#m15\">MySub</a>.<a title=\"The type MySub.Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report254Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id( pkg.MySub.Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($id( <a title=\"pkg.MySub\" href=\"#m15\">pkg.MySub</a>.<a title=\"The type pkg.MySub.Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report255Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($vararg($void));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($vararg(<a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report256Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($vararg( $void));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($vararg( <a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report257Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($vararg());\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($vararg<a title=\"There must be a type.\" class=\"e\">(</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report258Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($vararg(Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($vararg(<a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report259Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($vararg(MySub<String>));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " Object <a name=\"m35\">v</a>=<span class=\"s\">\"\"</span>.length($vararg(<a title=\"pkg.MySub\" href=\"#m15\">MySub</a>&lt;String<a title=\"The type pkg.MySub is not parameterized correctly.\" class=\"e\">&gt;</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report260Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub<T> {\n");
        xml_.append(" Object v=\"\".length($vararg(MySub));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub</a>&lt;<a name=\"m25\">T</a>&gt; {\n" +
                " Object <a name=\"m38\">v</a>=<span class=\"s\">\"\"</span>.length($vararg(<a title=\"The type pkg.MySub is not parameterized correctly.\n" +
                "\n" +
                "pkg.MySub\" href=\"#m15\" class=\"e\">MySub</a>));\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report261Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [$static;] $interfaces(Inexist) pkg.MySub<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class [<span class=\"i\">$static</span>;] $interfaces(<a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a>) <a name=\"m47\">pkg.MySub</a>&lt;<a name=\"m57\">T</a>&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report262Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot({1,\"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>@<a title=\"pkg.MyAnnot\" href=\"#m37\">MyAnnot</a>({1<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">,</a><span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\"m37\">pkg.MyAnnot </a>{\n" +
                " $int[] <a name=\"m59\">v</a>();\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report263Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot( {1,\"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>@<a title=\"pkg.MyAnnot\" href=\"#m38\">MyAnnot</a>( {1<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">,</a><span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\"m38\">pkg.MyAnnot </a>{\n" +
                " $int[] <a name=\"m60\">v</a>();\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report264Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot({1, \"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>@<a title=\"pkg.MyAnnot\" href=\"#m38\">MyAnnot</a>({1<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">,</a> <span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\"m38\">pkg.MyAnnot </a>{\n" +
                " $int[] <a name=\"m60\">v</a>();\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report265Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot( {1,\"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>@<a title=\"pkg.MyAnnot\" href=\"#m38\">MyAnnot</a>( {1<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">,</a><span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\"m38\">pkg.MyAnnot </a>{\n" +
                " $int[] <a name=\"m60\">v</a>();\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report266Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@Inex({})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><a title=\"After @ the type Inex is not an annotation.\" class=\"e\">@</a><a title=\"The type Inex is unknown.\" class=\"e\">Inex</a>(<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>})\n" +
                "$public $annotation <a name=\"m30\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report267Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@Inex({1})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><a title=\"After @ the type Inex is not an annotation.\" class=\"e\">@</a><a title=\"The type Inex is unknown.\" class=\"e\">Inex</a>(<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>1})\n" +
                "$public $annotation <a name=\"m31\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report268Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int m(];\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\" title=\"Bad index by parsing.\n" +
                "\n" +
                "The type java.lang.Object cannot be implicitly cast to $int\" class=\"e\">m</a>(];\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report269Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int m(a);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\" title=\"Bad index by parsing.\" class=\"e\">m</a>(a);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report270Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int ();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\" title=\"The method name  is not valid. It must be a word that is not a key word, not a primitive type. Besides, it must not start with a digit.\" class=\"e\">(</a>);\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report271Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int m[);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a title=\"No field could be retrieved.\" class=\"e\">m[)</a>;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report272Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" Object m();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " Object <a name=\"m42\" title=\"The type java.lang.Object is unexpected.\" class=\"e\">m</a>();\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report273Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int m(){};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\">m</a>()<a title=\"The type $int is unexpected.\" class=\"e\">{</a>};\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report274Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" Object m(){};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " Object <a name=\"m42\" title=\"The type java.lang.Object is unexpected.\" class=\"e\">m</a>()<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>};\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report275Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int m()\"\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int <a name=\"m40\" title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">m</a>()<span class=\"s\">\"\"</span>;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report276Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[][] m()\"\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " $int[][] <a name=\"m44\" title=\"The type [[$int is unexpected.\n" +
                "\n" +
                "The type java.lang.String cannot be implicitly cast to [[$int\" class=\"e\">m</a>()<span class=\"s\">\"\"</span>;\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report277Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@Inex({ 1})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><a title=\"After @ the type Inex is not an annotation.\" class=\"e\">@</a><a title=\"The type Inex is unknown.\" class=\"e\">Inex</a>(<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a> 1})\n" +
                "$public $annotation <a name=\"m32\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report278Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@Inex( {})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><a title=\"After @ the type Inex is not an annotation.\" class=\"e\">@</a><a title=\"The type Inex is unknown.\" class=\"e\">Inex</a>( <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>})\n" +
                "$public $annotation <a name=\"m31\">pkg.MyAnnot </a>{\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report279Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" Object m() {};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $annotation <a name=\"m20\">pkg.MyAnnot </a>{\n" +
                " Object <a name=\"m42\" title=\"The type java.lang.Object is unexpected.\" class=\"e\">m</a>() <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>};\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report280Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass<T:> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"There must be a type.\n" +
                "\n" +
                "The type java.lang.Object is not parameterized correctly.\" class=\"e\">pkg.MyClass</a>&lt;<a name=\"m27\">T</a>:&gt; {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report281Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:: {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The super types of the type pkg.MyClass could not be found.\n" +
                "\n" +
                "There must be a type.\n" +
                "\n" +
                "There must be a type.\n" +
                "\n" +
                "The type java.lang.Object is not parameterized correctly.\n" +
                "\n" +
                "The type java.lang.Object is not parameterized correctly.\" class=\"e\">pkg.MyClass</a>:: {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report282Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass: {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\" title=\"The super types of the type pkg.MyClass could not be found.\n" +
                "\n" +
                "There must be a type.\n" +
                "\n" +
                "The type java.lang.Object is not parameterized correctly.\" class=\"e\">pkg.MyClass</a>: {\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report283Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $for(;1;){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  <a title=\"The type $int is unexpected.\" class=\"e\">$for</a>(;1;){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report284Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $iter(String s=\"\";1;1){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  <a title=\"The type java.lang.String is not a primitive type or a wrapper type.\n" +
                "\n" +
                "The type $int cannot be implicitly cast to java.lang.String\n" +
                "\n" +
                "The type $int cannot be implicitly cast to java.lang.String\" class=\"e\">$iter</a>(String <a name=\"m47\">s</a>=<span class=\"s\">\"\"</span>;1;1){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report285Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $for[String](;;){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  <a title=\"The type java.lang.Object is not a primitive type or a wrapper type.\" class=\"e\">$for</a>[String](;;){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report286Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $iter[String]($int s=0;1;1){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  <a title=\"The type java.lang.Object is not a primitive type or a wrapper type.\" class=\"e\">$iter</a>[String]($int <a name=\"m53\">s</a>=0;1;1){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report287Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $switch($null){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  <a title=\"The type  is unknown.\" class=\"e\">$switch</a>($null){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report288Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $for[String]($int i:{}){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  <a title=\"The type java.lang.Object is not a primitive type or a wrapper type.\" class=\"e\">$for</a>[String]($int <a name=\"m52\">i</a>:{}){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report289Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $iterableTable<$int,$int> it = $null;\n");
        xml_.append("  $for[String]($int i,$int j:it){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  $iterableTable&lt;$int,$int&gt; <a name=\"m60\">it</a> = $null;\n" +
                "  <a title=\"The type java.lang.Object is not a primitive type or a wrapper type.\" class=\"e\">$for</a>[String]($int <a name=\"m92\">i</a>,$int <a name=\"m99\">j</a>:<a href=\"#m60\">it</a>){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report290Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $for($int i:$new String[]{\"\"}){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  $for($int <a name=\"m44\">i</a><a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">:</a>$new String[]{<span class=\"s\">\"\"</span>}){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report291Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $iterable<String> it = $null;\n");
        xml_.append("  $for($int i:it){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  $iterable&lt;String&gt; <a name=\"m52\">it</a> = $null;\n" +
                "  $for($int <a name=\"m76\">i</a><a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">:</a><a href=\"#m52\">it</a>){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report292Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  String it = $null;\n");
        xml_.append("  $for($int i:it){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  String <a name=\"m41\">it</a> = $null;\n" +
                "  $for($int <a name=\"m65\">i</a><a title=\"The type java.lang.Object cannot be implicitly cast to java.lang.$iterable\" class=\"e\">:</a><a href=\"#m41\">it</a>){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report293Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $iterableTable<String,String> it = $null;\n");
        xml_.append("  $for($int i,$int j:it){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  $iterableTable&lt;String,String&gt; <a name=\"m64\">it</a> = $null;\n" +
                "  $for($int <a name=\"m88\">i</a>,$int <a name=\"m95\">j</a><a title=\"The type java.lang.String cannot be implicitly cast to $int\n" +
                "\n" +
                "The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">:</a><a href=\"#m64\">it</a>){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report294Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  String it = $null;\n");
        xml_.append("  $for($int i,$int j:it){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  String <a name=\"m41\">it</a> = $null;\n" +
                "  $for($int <a name=\"m65\">i</a>,$int <a name=\"m72\">j</a><a title=\"The type java.lang.Object cannot be implicitly cast to java.lang.$iterableTable\" class=\"e\">:</a><a href=\"#m41\">it</a>){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report295Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $for($int i,$int j:$null){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  $for($int <a name=\"m44\">i</a>,$int <a name=\"m51\">j</a><a title=\"The value must not be null because of possible code.util.exceptions.NullObjectException.\" class=\"e\">:</a>$null){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report296Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $for($int i:($int[])$null){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  $for($int <a name=\"m44\">i</a><a title=\"The value must not be null because of possible code.util.exceptions.NullObjectException.\" class=\"e\">:</a>($int[])$null){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report297Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $for($int i:($iterable<$int>)$null){\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  $for($int <a name=\"m44\">i</a><a title=\"The value must not be null because of possible code.util.exceptions.NullObjectException.\" class=\"e\">:</a>($iterable&lt;$int&gt;)$null){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report298Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot({1,})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>@<a title=\"pkg.MyAnnot\" href=\"#m35\">MyAnnot</a>({1<a title=\"The type java.lang.Object cannot be implicitly cast to $int\" class=\"e\">,</a><a title=\"The expression part is empty.\" class=\"e\">}</a>)\n" +
                "$public $annotation <a name=\"m35\">pkg.MyAnnot </a>{\n" +
                " $int[] <a name=\"m57\">v</a>();\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report299Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot({1, })\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>@<a title=\"pkg.MyAnnot\" href=\"#m36\">MyAnnot</a>({1<a title=\"The type java.lang.Object cannot be implicitly cast to $int\" class=\"e\">,</a> <a title=\"The expression part is empty.\" class=\"e\">}</a>)\n" +
                "$public $annotation <a name=\"m36\">pkg.MyAnnot </a>{\n" +
                " $int[] <a name=\"m58\">v</a>();\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report300Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  1?1:1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  1<a title=\"The type $int is unexpected.\" class=\"e\">?</a>1:1;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report301Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $bool(1,1,1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  $bool(1<a title=\"The type $int is unexpected.\" class=\"e\">,</a>1,1);\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report302Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $bool(1,1,1,1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  <a title=\"The number of required operands 3 is different from the number of supplied arguments 4 for the operator $bool\" class=\"e\">$bool</a>(1,1,1,1);\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report303Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  ($void)$null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MyClass </a>{\n" +
                " {\n" +
                "  <a title=\"The expression part is empty.\" class=\"e\">($void)$null</a>;\n" +
                " }\n" +
                "}\n" +
                "</pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report304Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $int i}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElErrorReadOnlyDef();
        files_.put("src/pkg/Ex", xml_.toString());
        validateAndCheckErrors(files_, cont_);
        StringMap<String> filesExp_ = ExecFileBlock.errors(cont_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre>$public $class <a name=\"m15\">pkg.MySub </a>{\n" +
                " {\n" +
                "  <a title=\"The expression part is empty.\" class=\"e\">$int i</a><a title=\"Bad index by parsing.\" class=\"e\">}</a>\n" +
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
