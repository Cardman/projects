package code.expressionlanguage.methods;

import code.expressionlanguage.common.MessagesCdmBase;
import code.expressionlanguage.linkage.ExportCst;
import code.util.StringMap;
import org.junit.Test;

public final class Errors0Test extends ProcessMethodCommon {
    @Test
    public void report0Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", "");
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">" +
                "<a title=\"Bad index by parsing.\" class=\"e\"> " +
                "</a></span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report1Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", "\b");
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\"><a title=\"The characters ascii 8 are illegal.\" class=\"e\"> </a></span></pre></body></html>", filesExp_.firstValue());
    }


    @Test
    public void report2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" } ");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class pkg.Ex {\n" +
                " $public $static $int exmeth(){\n" +
                "  $return 1i;\n" +
                " }<a title=\"Bad index by parsing.\" class=\"e\"> " +
                "</a></span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.ExTwo</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">v</a>;\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"58\">w</a>;\n" +
                "}\n" +
                "$public $class pkg.Ex {\n" +
                " $public $static $int exmeth(){\n" +
                "  $return 1i;\n" +
                " }<a title=\"Bad index by parsing.\" class=\"e\"> </a></span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo{} ");
        xml_.append("$publi");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class pkg.ExTwo{} $publ<a title=\"Bad index by parsing.\" class=\"e\">i</a></span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExTwo{}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.ExTwo</a>{}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"42\" title=\"The type name pkg.ExTwo is duplicated with an other custom type.\" class=\"e\">pkg.ExTwo</a>{}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo{\n");
        xml_.append(" $public $class ExTwo{}\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.ExTwo</a>{\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"42\" title=\"The inner type simple name ExTwo is duplicated.\" class=\"e\">ExTwo</a>{}\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class ExTwo{\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"A type must have an non empty package.\" class=\"e\">ExTwo</a>{\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class a.#ExTwo{\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The part #ExTwo in a type is not valid. It must be a word.\" class=\"e\">a.#ExTwo</a>{\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class #a.ExTwo{\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The part #a in a type is not valid. It must be a word.\" class=\"e\">#a.ExTwo</a>{\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class .{\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"A type must have an non empty package.\n" +
                "\n" +
                "The part . in a type is not valid. It must be a word.\" class=\"e\">.</a>{\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class .ExTwo{\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"A type must have an non empty package.\n" +
                "\n" +
                "The part .ExTwo in a type is not valid. It must be a word.\" class=\"e\">.ExTwo</a>{\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Outer</a><a name=\""+ExportCst.PREF_REF+"24\" title=\"The part must not be empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<,T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Outer</a><a name=\""+ExportCst.PREF_REF+"24\" title=\"The part must not be empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a>,<a name=\""+ExportCst.PREF_REF+"26\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T,> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Outer</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"25\">T</a><a name=\""+ExportCst.PREF_REF+"26\" title=\"The part must not be empty.\" class=\"e\">,</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<,> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Outer</a><a name=\""+ExportCst.PREF_REF+"24\" title=\"The part must not be empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a><a name=\""+ExportCst.PREF_REF+"25\" title=\"The part must not be empty.\" class=\"e\">,</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T#> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Outer</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"25\" title=\"The part T# in a variable type is not valid. It must be a word.\" class=\"e\">T#</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<S,T#> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Outer</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"25\">S</a>,<a name=\""+ExportCst.PREF_REF+"27\" title=\"The part T# in a variable type is not valid. It must be a word.\" class=\"e\">T#</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T,T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Outer</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"25\">T</a>,<a name=\""+ExportCst.PREF_REF+"27\" title=\"The part T in a type variable is duplicated.\" class=\"e\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" #ONE{}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\" title=\"The part #ONE in a type is not valid. It must be a word.\n" +
                "\n" +
                "The field name #ONE is not valid. It must be a word.\" class=\"e\">#ONE</a>{}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" #ONE{(){}}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\" title=\"The part #ONE in a type is not valid. It must be a word.\n" +
                "\n" +
                "The field name #ONE is not valid. It must be a word.\" class=\"e\">#ONE</a>{<a name=\""+ExportCst.PREF_REF+"33\">(</a>){}}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" #ONE(1){($int i){}}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\" title=\"The part #ONE in a type is not valid. It must be a word.\n" +
                "\n" +
                "The field name #ONE is not valid. It must be a word.\n" +
                "\n" +
                "The constructor java.lang.Object($int) is undefined.\" class=\"e\">#ONE</a>(1){<a name=\""+ExportCst.PREF_REF+"36\">(</a>$int <a name=\""+ExportCst.PREF_REF+"42\">i</a>){}}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" #ONE(1+2){($int i){}}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\" title=\"The part #ONE in a type is not valid. It must be a word.\n" +
                "\n" +
                "The field name #ONE is not valid. It must be a word.\n" +
                "\n" +
                "The constructor java.lang.Object($int) is undefined.\" class=\"e\">#ONE</a>(1+2){<a name=\""+ExportCst.PREF_REF+"38\">(</a>$int <a name=\""+ExportCst.PREF_REF+"44\">i</a>){}}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.sub.MyClass {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.sub {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.sub.MyClass</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"50\" title=\"The type name pkg.sub is shadowed by the package pkg.sub.\" class=\"e\">pkg.sub</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.sub.two.MyClass {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.sub {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.sub.two.MyClass</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"54\" title=\"The type name pkg.sub is shadowed by the package pkg.sub.\n" +
                "\n" +
                "The type name pkg.sub is shadowed by the package pkg.sub.two.\" class=\"e\">pkg.sub</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:$void {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The type cannot be the key word $void.\" class=\"e\">pkg.MyClass</a>:<a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.MyClass:java.lang.CharSequence {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $abstract $class <a name=\""+ExportCst.PREF_REF+"25\" title=\"The type pkg.MyClass cannot have explicitly the type java.lang.CharSequence as super type because java.lang.CharSequence is reserved." +
                "\" class=\"e\">pkg.MyClass</a>:java.lang.CharSequence {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnotation:MyClass {\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.MyClass {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\" title=\"The type pkg.MyAnnotation cannot have the type MyClass as super type." +
                "\" class=\"e\">pkg.MyAnnotation</a>:<a title=\"pkg.MyClass\" href=\"#"+ExportCst.PREF_REF+"74\">MyClass</a> {\n" +
                "}\n" +
                "$public $abstract $class <a name=\""+ExportCst.PREF_REF+"74\">pkg.MyClass</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:$Enum<MyEnum> {\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The type pkg.MyClass cannot have explicitly the type java.lang.$Enum as super type because java.lang.$Enum is reserved." +
                "\" class=\"e\">pkg.MyClass</a>:$Enum"+MessagesCdmBase.LT+"<a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"59\">MyEnum</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $enum <a name=\""+ExportCst.PREF_REF+"59\">pkg.MyEnum</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:$en {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The type pkg.MyClass cannot have explicitly the type java.lang.$en as super type because java.lang.$en is reserved." +
                "\" class=\"e\">pkg.MyClass</a>:$en {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:MySuper:MySuper {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySuper {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The type pkg.MyClass cannot have the type pkg.MySuper duplicated as super type 2 times.\n" +
                "\n" +
                "The super types of the type pkg.MyClass could not be found." +
                "\" class=\"e\">pkg.MyClass</a>:<a title=\"pkg.MySuper\" href=\"#"+ExportCst.PREF_REF+"62\">MySuper</a>:<a title=\"pkg.MySuper\" href=\"#"+ExportCst.PREF_REF+"62\">MySuper</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"62\">pkg.MySuper</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The type pkg.MyClass cannot have the type pkg.MySuper..Inner as super type because pkg.MySuper..Inner is instance type.\" class=\"e\">pkg.MyClass</a>:<a title=\"pkg.MySuper\" href=\"#"+ExportCst.PREF_REF+"60\">MySuper</a>.<a title=\"pkg.MySuper..Inner\" href=\"#"+ExportCst.PREF_REF+"90\">Inner</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"60\">pkg.MySuper</a> {\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"90\">Inner</a> {\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyOuter</a> {\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"45\" title=\"The type pkg.MyOuter..MyClass cannot have the type pkg.MyOuter..MySuper..Inner as super type because pkg.MyOuter..MyClass has 1 parents types and pkg.MyOuter..MySuper..Inner has 2 parents types.\n" +
                "\n" +
                "The type pkg.MyOuter..MyClass cannot have the type pkg.MyOuter..MySuper..Inner as super type.\" class=\"e\">MyClass</a>:<a title=\"pkg.MyOuter..MySuper\" href=\"#"+ExportCst.PREF_REF+"88\">MySuper</a>.<a title=\"pkg.MyOuter..MySuper..Inner\" href=\"#"+ExportCst.PREF_REF+"115\">Inner</a> {\n" +
                " }\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"88\">MySuper</a> {\n" +
                "  $public $class <a name=\""+ExportCst.PREF_REF+"115\">Inner</a> {\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyInt:MyClass {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\" title=\"The interface pkg.MyInt cannot have the type pkg.MyClass as super type because pkg.MyClass is not an interface.\" class=\"e\">pkg.MyInt</a>:<a title=\"pkg.MyClass\" href=\"#"+ExportCst.PREF_REF+"56\">MyClass</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"56\">pkg.MyClass</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyClass {\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkg.MyClass {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The type pkg.MySub cannot have the type pkg.MyClass as super type because pkg.MyClass is final.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyClass\" href=\"#"+ExportCst.PREF_REF+"59\">MyClass</a> {\n" +
                "}\n" +
                "$public $final $class <a name=\""+ExportCst.PREF_REF+"59\">pkg.MyClass</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The class pkg.MySub cannot have more than one super class (2 times).\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyClass\" href=\"#"+ExportCst.PREF_REF+"63\">MyClass</a>:<a title=\"pkg.MySecClass\" href=\"#"+ExportCst.PREF_REF+"94\">MySecClass</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"63\">pkg.MyClass</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"94\">pkg.MySecClass</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The super types of the type pkg.MyClass could not be found.\" class=\"e\">pkg.MyClass</a>:<a title=\"The type MySupOne,MySupTwo is unknown.\" class=\"e\">MySupOne,MySupTwo</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"64\">pkg.MySupOne</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"96\">pkg.MySupTwo</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:MySup {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a>:<a title=\"The type pkg.MySup is not parameterized correctly.\n" +
                "\n" +
                "pkg.MySup\" href=\"#"+ExportCst.PREF_REF+"52\" class=\"e\">MySup</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"52\">pkg.MySup</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"62\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a>:<a title=\"pkg.MySup\" href=\"#"+ExportCst.PREF_REF+"61\">MySup</a><a title=\"The type pkg.MySup"+MessagesCdmBase.LT+"java.lang.Integer"+MessagesCdmBase.GT+" is not parameterized correctly.\" class=\"e\">"+MessagesCdmBase.LT+"</a>Integer"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"61\">pkg.MySup</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"71\">T</a>:<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"98\">MyCl</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"98\">pkg.MyCl</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:pkgtwo.MyCl {\n");
        xml_.append("}\n");
        xml_.append("$package $class pkgtwo.MyCl {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a>:<a title=\"The type pkgtwo.MyCl is not accessible from the type pkg.MyClass.\n" +
                "\n" +
                "pkgtwo.MyCl\" href=\"#"+ExportCst.PREF_REF+"59\" class=\"e\">pkgtwo.MyCl</a> {\n" +
                "}\n" +
                "$package $class <a name=\""+ExportCst.PREF_REF+"59\">pkgtwo.MyCl</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyInt<String>:MyInt<Object> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The type pkg.MySub cannot have the type pkg.MyInt duplicated as super type 2 times.\n" +
                "\n" +
                "The super types of the type pkg.MySub could not be found.\n" +
                "\n" +
                "The generic super types pkg.MyInt"+MessagesCdmBase.LT+"java.lang.String"+MessagesCdmBase.GT+""+MessagesCdmBase.AMP+"pkg.MyInt"+MessagesCdmBase.LT+"java.lang.Object"+MessagesCdmBase.GT+" are duplicated.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"76\">MyInt</a>"+MessagesCdmBase.LT+"String"+MessagesCdmBase.GT+":<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"76\">MyInt</a>"+MessagesCdmBase.LT+"Object"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"76\">pkg.MyInt</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"86\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The generic super types pkg.MyInt"+MessagesCdmBase.LT+"java.lang.String"+MessagesCdmBase.GT+""+MessagesCdmBase.AMP+"pkg.MyInt"+MessagesCdmBase.LT+"java.lang.Object"+MessagesCdmBase.GT+" are duplicated.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MySubInTwo\" href=\"#"+ExportCst.PREF_REF+"84\">MySubInTwo</a>"+MessagesCdmBase.LT+"String"+MessagesCdmBase.GT+":<a title=\"pkg.MySubInt\" href=\"#"+ExportCst.PREF_REF+"133\">MySubInt</a>"+MessagesCdmBase.LT+"Object"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"84\">pkg.MySubInTwo</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"99\">U</a>"+MessagesCdmBase.GT+":<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"180\">MyInt</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"99\">U</a>"+MessagesCdmBase.GT+" {}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"133\">pkg.MySubInt</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"146\">S</a>"+MessagesCdmBase.GT+":<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"180\">MyInt</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"146\">S</a>"+MessagesCdmBase.GT+" {}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"180\">pkg.MyInt</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"190\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyInt<String>:MyInt<Object> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The type pkg.MySub cannot have the type pkg.MyInt duplicated as super type 2 times.\n" +
                "\n" +
                "The super types of the type pkg.MySub could not be found.\n" +
                "\n" +
                "The generic super types pkg.MyInt"+MessagesCdmBase.LT+"java.lang.String"+MessagesCdmBase.GT+""+MessagesCdmBase.AMP+"pkg.MyInt"+MessagesCdmBase.LT+"java.lang.Object"+MessagesCdmBase.GT+" are duplicated.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"76\">MyInt</a>"+MessagesCdmBase.LT+"String<a title=\"The type pkg.MyInt is not parameterized correctly.\" class=\"e\">"+MessagesCdmBase.GT+"</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"76\">MyInt</a>"+MessagesCdmBase.LT+"Object<a title=\"The type pkg.MyInt is not parameterized correctly.\" class=\"e\">"+MessagesCdmBase.GT+"</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"76\">pkg.MyInt</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The argument ?pkg.MyIntTwo of the generic super type pkg.MyInt"+MessagesCdmBase.LT+"?pkg.MyIntTwo"+MessagesCdmBase.GT+" is bound. It cannot be used in generic super type.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"65\">MyInt</a>"+MessagesCdmBase.LT+"?<a title=\"pkg.MyIntTwo\" href=\"#"+ExportCst.PREF_REF+"101\">MyIntTwo</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"65\">pkg.MyInt</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"75\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"101\">pkg.MyIntTwo</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The argument !pkg.MyIntTwo of the generic super type pkg.MyInt"+MessagesCdmBase.LT+"!pkg.MyIntTwo"+MessagesCdmBase.GT+" is bound. It cannot be used in generic super type.\" class=\"e\">pkg.MySub</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"65\">MyInt</a>"+MessagesCdmBase.LT+"!<a title=\"pkg.MyIntTwo\" href=\"#"+ExportCst.PREF_REF+"101\">MyIntTwo</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"65\">pkg.MyInt</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"75\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"101\">pkg.MyIntTwo</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyInt<> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"56\">MyInt</a><a title=\"The type MyInt"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is unknown.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"56\">pkg.MyInt</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"66\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MyInt<!> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"57\">MyInt</a><a title=\"The type MyInt"+MessagesCdmBase.LT+"!"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">"+MessagesCdmBase.LT+"</a>!"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"57\">pkg.MyInt</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"67\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void #method(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\" title=\"The method name #method is not valid. It must be a word.\" class=\"e\">#method</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method($int #t){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>($int <a name=\""+ExportCst.PREF_REF+"54\" title=\"The parameter method name #t is not valid. It must be a word.\" class=\"e\">#t</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>(){\n" +
                " }\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"70\" title=\"The method method() is duplicated.\" class=\"e\">method</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public Inexist method(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public <a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a> <a name=\""+ExportCst.PREF_REF+"44\" title=\"A $throw block or a $return block is missing for the method method().\" class=\"e\">method</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public <a title=\"pkg.MyParam\" href=\"#"+ExportCst.PREF_REF+"82\">MyParam</a>"+MessagesCdmBase.LT+"String<a title=\"The type pkg.MyParam is not parameterized correctly.\" class=\"e\">"+MessagesCdmBase.GT+"</a> <a name=\""+ExportCst.PREF_REF+"52\" title=\"A $throw block or a $return block is missing for the method method().\" class=\"e\">method</a>(){\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"82\">pkg.MyParam</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public <a title=\"pkg.MyParam\" href=\"#"+ExportCst.PREF_REF+"82\">MyParam</a><a title=\"The type pkg.MyParam"+MessagesCdmBase.LT+"java.lang.String"+MessagesCdmBase.GT+" is not parameterized correctly.\" class=\"e\">"+MessagesCdmBase.LT+"</a>String"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"52\" title=\"A $throw block or a $return block is missing for the method method().\" class=\"e\">method</a>(){\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"82\">pkg.MyParam</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"94\">T</a>:<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"121\">MyCl</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"121\">pkg.MyCl</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method($int t,$int t){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>($int <a name=\""+ExportCst.PREF_REF+"54\">t</a>,$int <a name=\""+ExportCst.PREF_REF+"61\" title=\"The parameter function name t is duplicated.\" class=\"e\">t</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\">$public MySub(</a>){\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"49\" title=\"The constructor pkg.MySub() is duplicated.\" class=\"e\">$public MySub(</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public MySub($int #t){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\">$public MySub(</a>$int <a name=\""+ExportCst.PREF_REF+"47\" title=\"The parameter method name #t is not valid. It must be a word.\" class=\"e\">#t</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public MySub($int t,$int t){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\">$public MySub(</a>$int <a name=\""+ExportCst.PREF_REF+"47\">t</a>,$int <a name=\""+ExportCst.PREF_REF+"54\" title=\"The parameter function name t is duplicated.\" class=\"e\">t</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $operator<> $int(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $operator<a name=\""+ExportCst.PREF_REF+"37\" title=\"The operator symbol "+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is not valid.\n" +
                "\n" +
                "A $throw block or a $return block is missing for the method $static "+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"().\" class=\"e\">"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"</a> $int(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int $this($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\" title=\"A $throw block or a $return block is missing for the method []($int).\" class=\"e\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"52\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\" title=\"The indexer []=($int) get must be defined.\" class=\"e\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"53\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"53\">i</a>,$int <a name=\""+ExportCst.PREF_REF+"60\" title=\"The parameter function name i is duplicated.\" class=\"e\">i</a>){\n" +
                " }\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"81\" title=\"A $throw block or a $return block is missing for the method []($int,$int).\" class=\"e\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"92\">i</a>,$int <a name=\""+ExportCst.PREF_REF+"99\" title=\"The parameter function name i is duplicated.\" class=\"e\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"53\">i</a>){\n" +
                " }\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"74\" title=\"A $throw block or a $return block is missing for the method []($int).\" class=\"e\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"85\">i</a>){\n" +
                " }\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"107\" title=\"The indexer []=($int) is duplicated.\" class=\"e\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"118\">i</a>){\n" +
                " }\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"139\" title=\"The indexer []($int) is duplicated.\n" +
                "\n" +
                "A $throw block or a $return block is missing for the method []($int).\" class=\"e\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"150\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MySub {\n");
        xml_.append(" $int #annot();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"38\" title=\"The method name #annot is not valid. It must be a word.\" class=\"e\">#annot</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MySub {\n");
        xml_.append(" #ONE\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MySub</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\" title=\"The field name #ONE is not valid. It must be a word.\" class=\"e\">#ONE</a>\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MySub {\n");
        xml_.append(" #ONE;\n");
        xml_.append(" $public MySub(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MySub</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\" title=\"The field name #ONE is not valid. It must be a word.\" class=\"e\">#ONE</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"34\">$public MySub(</a>){}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"The block is unexpected.\" class=\"e\">$if</a>($true){}\n" +
                " {\n" +
                " $if($true){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int #e;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a title=\"The field name #e is not valid. It must be a word.\" class=\"e\">#e</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int e,e;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">e</a>,<a title=\"the field name e is duplicated.\" class=\"e\">e</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int e+e;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a title=\"No field could be retrieved.\" class=\"e\">e+e</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  $return 1;\n" +
                "  <a title=\"The code is unreachable in the function method()\" class=\"e\">$return</a> 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">$if</a> <a title=\"There must be an expression.\" class=\"e\">(</a>){\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">v</a> = 1;\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"54\">method</a>() {\n" +
                "  $if (<a title=\"pkg.MySub.v\" href=\"#"+ExportCst.PREF_REF+"33\">v</a>==<a title=\"pkg.MySub.v\" href=\"#"+ExportCst.PREF_REF+"33\">v</a>){\n" +
                "  }$else <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">$if</a> <a title=\"There must be an expression.\" class=\"e\">(</a>){\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">$while</a> <a title=\"There must be an expression.\" class=\"e\">(</a>){\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  $do{}\n" +
                "  <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">$while</a> <a title=\"There must be an expression.\" class=\"e\">(</a>);\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $case 1;{}\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\" title=\"A $throw block or a $return block is missing for the method method().\" class=\"e\">method</a>() {\n" +
                "  <a title=\"The $case block with expression 1 must be child of a block $switch.\" class=\"e\">$case</a> 1;{}\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  $switch <a title=\"There must be an expression.\" class=\"e\">(</a>){}\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        xml_.append("   $case;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"44\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"14\">MyEnum</a> <a name=\""+ExportCst.PREF_REF+"64\">e</a>;\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"81\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#"+ExportCst.PREF_REF+"64\">e</a>){\n" +
                "   <a title=\"The $case block with expression  is not constant.\" class=\"e\">$case</a>;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        xml_.append("   $case ONE;\n");
        xml_.append("   $case TWO;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\">TWO</a>\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"49\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"14\">MyEnum</a> <a name=\""+ExportCst.PREF_REF+"69\">e</a>;\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"86\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#"+ExportCst.PREF_REF+"69\">e</a>){\n" +
                "   <a title=\"The $case block with expression ONE is not constant.\" class=\"e\">$case</a> ONE;\n" +
                "   $case <a title=\"pkg.MyEnum.TWO\" href=\"#"+ExportCst.PREF_REF+"28\">TWO</a>;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report78Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case 1;\n");
        xml_.append("   $case 1;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">e</a>;\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"50\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#"+ExportCst.PREF_REF+"33\">e</a>){\n" +
                "   $case 1;\n" +
                "   <a title=\"The $case block with value 1 is duplicated in the parent $switch block.\" class=\"e\">$case</a> 1;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report79Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Integer e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case $null;\n");
        xml_.append("   $case $null;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Integer <a name=\""+ExportCst.PREF_REF+"36\">e</a>;\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"53\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#"+ExportCst.PREF_REF+"36\">e</a>){\n" +
                "   $case $null;\n" +
                "   <a title=\"The $case block with value  is duplicated in the parent $switch block.\" class=\"e\">$case</a> $null;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report80Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case \"\";\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">e</a>;\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"50\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#"+ExportCst.PREF_REF+"33\">e</a>){\n" +
                "   <a title=\"The $case block with value  is not a sub type of $int.\" class=\"e\">$case</a> <span class=\"s\">\"\"</span>;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        xml_.append("   $case $null;\n");
        xml_.append("   $case $null;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"44\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"14\">MyEnum</a> <a name=\""+ExportCst.PREF_REF+"64\">e</a>;\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"81\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#"+ExportCst.PREF_REF+"64\">e</a>){\n" +
                "   $case $null;\n" +
                "   <a title=\"The $case block with value  is duplicated in the parent $switch block.\" class=\"e\">$case</a> $null;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report82Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $default;{}\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\" title=\"A $throw block or a $return block is missing for the method method().\" class=\"e\">method</a>() {\n" +
                "  <a title=\"The $default block with expression  must be child of a block $switch.\" class=\"e\">$default</a>;{}\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        xml_.append("   $case $null;\n");
        xml_.append("   $case $null;\n");
        xml_.append("   $default;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"44\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"14\">MyEnum</a> <a name=\""+ExportCst.PREF_REF+"64\">e</a>;\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"81\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#"+ExportCst.PREF_REF+"64\">e</a>){\n" +
                "   $case $null;\n" +
                "   <a title=\"The $case block with value  is duplicated in the parent $switch block.\" class=\"e\">$case</a> $null;\n" +
                "   $default;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        xml_.append("   $case $null;\n");
        xml_.append("   $default;\n");
        xml_.append("   $default;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"44\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"14\">MyEnum</a> <a name=\""+ExportCst.PREF_REF+"64\">e</a>;\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"81\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#"+ExportCst.PREF_REF+"64\">e</a>){\n" +
                "   $case $null;\n" +
                "   $default;\n" +
                "   <a title=\"The $default block is duplicated in the parent $switch block.\" class=\"e\">$default</a>;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report85Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int e;\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $switch (e){\n");
        xml_.append("   $case ;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">e</a>;\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"50\">method</a>() {\n" +
                "  $switch (<a title=\"pkg.MySub.e\" href=\"#"+ExportCst.PREF_REF+"33\">e</a>){\n" +
                "   <a title=\"There must be an expression.\n" +
                "\n" +
                "The $case block with expression  is not constant.\" class=\"e\">$case</a> ;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  $return 1;\n" +
                "  $int <a name=\""+ExportCst.PREF_REF+"72\">i</a><a title=\"The code is unreachable in the function method()\" class=\"e\">;</a>\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  $switch (1){\n" +
                "   $int <a name=\""+ExportCst.PREF_REF+"75\">i</a><a title=\"The $switch block must contain only one of the blocks $case|$default.\" class=\"e\">;</a>\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">v</a> = 1;\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"54\">method</a>() {\n" +
                "  $if (<a title=\"pkg.MySub.v\" href=\"#"+ExportCst.PREF_REF+"33\">v</a>==<a title=\"pkg.MySub.v\" href=\"#"+ExportCst.PREF_REF+"33\">v</a>){\n" +
                "  }<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">$elseif</a> <a title=\"There must be an expression.\" class=\"e\">(</a>){\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  <a title=\"The $elseif block must be preceded by one of the blocks $if|$elseif.\n" +
                "\n" +
                "The code is unreachable in the function method()\" class=\"e\">$elseif</a> ($true){\n" +
                "  }\n" +
                "  <a title=\"The code is unreachable in the function method()\" class=\"e\">$return</a> 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  $else <a title=\"The $elseif block must be preceded by one of the blocks $if|$elseif.\n" +
                "\n" +
                "The code is unreachable in the function method()\" class=\"e\">$if</a> ($true){\n" +
                "  }\n" +
                "  <a title=\"The code is unreachable in the function method()\" class=\"e\">$return</a> 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  <a title=\"The $else block must be preceded by one of the blocks $if|$elseif.\n" +
                "\n" +
                "The code is unreachable in the function method()\" class=\"e\">$else</a> {\n" +
                "  }\n" +
                "  <a title=\"The code is unreachable in the function method()\" class=\"e\">$return</a> 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  $return 1;\n" +
                "  <a title=\"The code is unreachable in the function method()\" class=\"e\">{</a>\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  <a title=\"The $do block must be followed by one of the blocks $while.\" class=\"e\">$do</a>{}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  <a title=\"The $do block must be followed by one of the blocks $while.\" class=\"e\">$do</a>{}\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $do{}\n" +
                "  <a title=\"The $while block associated to a $do block must be empty.\" class=\"e\">$while</a>($true){$int <a name=\""+ExportCst.PREF_REF+"82\">i</a>;}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report96Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator<> $int(){\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$operator<a name=\""+ExportCst.PREF_REF+"9\" title=\"The operator symbol "+MessagesCdmBase.LT+""+MessagesCdmBase.GT+" is not valid.\n" +
                "\n" +
                "A $throw block or a $return block is missing for the method $static "+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"().\" class=\"e\">"+MessagesCdmBase.LT+""+MessagesCdmBase.GT+"</a> $int(){\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report97Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int($int i){\n");
        xml_.append("}\n");
        xml_.append("$operator+ $int($int i){\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$operator<a name=\""+ExportCst.PREF_REF+"9\" title=\"A $throw block or a $return block is missing for the method $static +($int).\" class=\"e\">+</a> $int($int <a name=\""+ExportCst.PREF_REF+"21\">i</a>){\n" +
                "}\n" +
                "$operator<a name=\""+ExportCst.PREF_REF+"36\" title=\"The operator $static +($int) is duplicated.\n" +
                "\n" +
                "A $throw block or a $return block is missing for the method $static +($int).\" class=\"e\">+</a> $int($int <a name=\""+ExportCst.PREF_REF+"48\">i</a>){\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report98Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int($int #i){\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$operator<a name=\""+ExportCst.PREF_REF+"9\" title=\"A $throw block or a $return block is missing for the method $static +($int).\" class=\"e\">+</a> $int($int <a name=\""+ExportCst.PREF_REF+"21\" title=\"The parameter method name #i is not valid. It must be a word.\" class=\"e\">#i</a>){\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report99Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int($int i,$int i){\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$operator<a name=\""+ExportCst.PREF_REF+"9\" title=\"A $throw block or a $return block is missing for the method $static +($int,$int).\" class=\"e\">+</a> $int($int <a name=\""+ExportCst.PREF_REF+"21\">i</a>,$int <a name=\""+ExportCst.PREF_REF+"28\" title=\"The parameter function name i is duplicated.\" class=\"e\">i</a>){\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  <a title=\"The $try block must be followed by one of the blocks $catch|$finally.\" class=\"e\">$try</a>{}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  <a title=\"The $catch block must be preceded by one of the blocks $catch|$try.\" class=\"e\">$catch</a>{}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  <a title=\"The $catch block must be preceded by one of the blocks $catch|$try.\" class=\"e\">$catch</a>($int <a name=\""+ExportCst.PREF_REF+"67\">i</a>){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  <a title=\"The $finally block must be preceded by one of the blocks $catch|$try.\" class=\"e\">$finally</a>{}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $try{}\n" +
                "  $catch($int <a name=\""+ExportCst.PREF_REF+"76\" title=\"The variable name #i is not valid. It must be a word.\" class=\"e\">#i</a>){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $for($int <a title=\"The variable name #v is not valid. It must be a word.\" class=\"e\">#v</a>:$new $int[]{}){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"65\">v</a>:$new $int[]{}){$int <a title=\"The variable name #v is not valid. It must be a word.\" class=\"e\">#v</a>;}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $iterableTable"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"81\">it</a> = $null;\n" +
                "  $for($int <a title=\"The variable name #v is not valid. It must be a word.\" class=\"e\">#v</a>, $int <a name=\""+ExportCst.PREF_REF+"114\">k</a>:<a href=\"#"+ExportCst.PREF_REF+"81\">it</a>){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $iterableTable"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"81\">it</a> = $null;\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"105\">v</a>, $int <a title=\"The variable name #k is not valid. It must be a word.\" class=\"e\">#k</a>:<a href=\"#"+ExportCst.PREF_REF+"81\">it</a>){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $iterableTable"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"81\">it</a> = $null;\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"105\">v</a>, $int <a name=\""+ExportCst.PREF_REF+"113\">k</a>:<a href=\"#"+ExportCst.PREF_REF+"81\">it</a>){$int <a title=\"The variable name #v is not valid. It must be a word.\" class=\"e\">#v</a><a title=\"The type $int cannot be implicitly cast to \" class=\"e\">=</a>1;}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"65\">v</a>:$new $int[]{}){$int <a title=\"The variable name #v is not valid. It must be a word.\" class=\"e\">#v</a>,<a name=\""+ExportCst.PREF_REF+"90\">w</a>;}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $iterableTable"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"81\">it</a> = $null;\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"105\">v</a>, $int <a name=\""+ExportCst.PREF_REF+"113\">k</a>:<a href=\"#"+ExportCst.PREF_REF+"81\">it</a>){$int <a title=\"The variable name #v is not valid. It must be a word.\" class=\"e\">#v</a><a title=\"The type $int cannot be implicitly cast to \" class=\"e\">=</a>1,<a name=\""+ExportCst.PREF_REF+"129\">w</a>;}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  <a title=\"There must be an expression.\n" +
                "\n" +
                "The type java.lang.Object cannot be implicitly cast to $int\" class=\"e\">$return</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  <a title=\"The type cannot be the key word $void.\" class=\"e\">$return</a> 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $for($int <a title=\"The variable name #i is not valid. It must be a word.\" class=\"e\">#i</a>;;){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $for($int <a title=\"The variable name #i is not valid. It must be a word.\" class=\"e\">#i</a><a title=\"The type $int cannot be implicitly cast to \" class=\"e\">=</a>0;$true;){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $for($int <a title=\"The variable name #i is not valid. It must be a word.\" class=\"e\">#i</a>,<a name=\""+ExportCst.PREF_REF+"68\">j</a>;;$true){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $for($int <a title=\"The variable name #i is not valid. It must be a word.\" class=\"e\">#i</a><a title=\"The type $int cannot be implicitly cast to \" class=\"e\">=</a>0,<a name=\""+ExportCst.PREF_REF+"70\">j</a>;;$true){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $for(;;){<a title=\"There must be an expression.\" class=\"e\">$throw</a>;}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $iter($int <a title=\"The variable name #i is not valid. It must be a word.\" class=\"e\">#i</a>=0;1;1){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  <a title=\"Bad index by parsing.\n" +
                "\n" +
                "The type java.lang.Object cannot be implicitly cast to $int\n" +
                "\n" +
                "The type java.lang.Object cannot be implicitly cast to $int\" class=\"e\">$iter</a>($int <a name=\""+ExportCst.PREF_REF+"66\">i</a>=0;<a title=\"The expression part is empty.\" class=\"e\">;</a>){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $if($true)<a name=\""+ExportCst.PREF_REF+"65\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $if($true)<a name=\""+ExportCst.PREF_REF+"65\">t</a>{\n" +
                "   $if($true)<a name=\""+ExportCst.PREF_REF+"81\" title=\"The label is duplicated.\" class=\"e\">t</a>;\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $switch(1)<a name=\""+ExportCst.PREF_REF+"65\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $while($true)<a name=\""+ExportCst.PREF_REF+"68\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $do <a name=\""+ExportCst.PREF_REF+"59\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>{}\n" +
                "  $while($true);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $try <a name=\""+ExportCst.PREF_REF+"60\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>{}\n" +
                "  $catch{}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $for(;;)<a name=\""+ExportCst.PREF_REF+"63\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>{}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $iter($int <a name=\""+ExportCst.PREF_REF+"66\">i</a>=0;1;1)<a name=\""+ExportCst.PREF_REF+"74\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>{}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"65\">i</a>:$new $int[]{})<a name=\""+ExportCst.PREF_REF+"81\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>{}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $iterableTable"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"81\">it</a> = $null;\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"105\">i</a>,$int <a name=\""+ExportCst.PREF_REF+"112\">j</a>:<a href=\"#"+ExportCst.PREF_REF+"81\">it</a>)<a name=\""+ExportCst.PREF_REF+"117\" title=\"A label must be a word (included characters dollars).\" class=\"e\">#t</a>{}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  <a title=\"The $break block must be inner of the blocks $switch|$for|$foreach|$do|$iter|$while.\" class=\"e\">$break</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $break <a title=\"The $break block with label lab must be inner of a labelled with $switch|$try|$catch|$finally|$if|$elseif|$else|$for|$foreach|$do|$iter|$while block.\" class=\"e\">lab</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $if($true)<a name=\""+ExportCst.PREF_REF+"65\">lab</a>{\n" +
                "   $break <a href=\"#"+ExportCst.PREF_REF+"65\">lab</a>;\n" +
                "  }\n" +
                "  <a title=\"The $continue block must be inner of the blocks $for|$foreach|$do|$iter|$while.\" class=\"e\">$continue</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  <a title=\"The $continue block must be inner of the blocks $for|$foreach|$do|$iter|$while.\" class=\"e\">$continue</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $continue <a title=\"The $continue block with label lab must be inner of a labelled with $for|$foreach|$do|$iter|$while block.\" class=\"e\">lab</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $iter($int <a name=\""+ExportCst.PREF_REF+"66\">i</a>=0;1;1)<a name=\""+ExportCst.PREF_REF+"74\">lab</a>{\n" +
                "   $continue <a href=\"#"+ExportCst.PREF_REF+"74\">lab</a>;\n" +
                "  }\n" +
                "  <a title=\"The $break block must be inner of the blocks $switch|$for|$foreach|$do|$iter|$while.\" class=\"e\">$break</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"62\">v</a>=$class<a title=\"There must be a type.\" class=\"e\">(</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"62\">v</a>=$class(<a title=\"The type , is unknown.\" class=\"e\">,</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"62\">v</a>=$(<a title=\"There must be a type.\" class=\"e\">)</a>1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"62\">v</a>=$(<a title=\"The type a is unknown.\" class=\"e\">a</a>)1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"62\">v</a>=$(<a title=\"The type a"+MessagesCdmBase.LT+"b,c"+MessagesCdmBase.GT+"d is unknown.\" class=\"e\">a"+MessagesCdmBase.LT+"b,c"+MessagesCdmBase.GT+"d</a>)1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report142Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MySub {\n");
        xml_.append(" $int annot();\n");
        xml_.append(" $int annot();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"38\">annot</a>();\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"53\" title=\"The method annot() is duplicated.\" class=\"e\">annot</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report143Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void(){\n");
        xml_.append(" }\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Ex</a> {\n" +
                " $public $static $void<a name=\""+ExportCst.PREF_REF+"46\" title=\"The method name  is not valid. It must be a word.\" class=\"e\">(</a>){\n" +
                " }\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"The type pkg.MyParam is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyParam\" href=\"#"+ExportCst.PREF_REF+"56\" class=\"e\">MyParam</a> <a name=\""+ExportCst.PREF_REF+"36\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"56\">pkg.MyParam</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"68\">T</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"62\">v</a>=$(<a title=\"pkg.MyParam\" href=\"#"+ExportCst.PREF_REF+"105\">MyParam</a>"+MessagesCdmBase.LT+"String<a title=\"The type pkg.MyParam is not parameterized correctly.\" class=\"e\">"+MessagesCdmBase.GT+"</a>)1;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"105\">pkg.MyParam</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyParam\" href=\"#"+ExportCst.PREF_REF+"62\">MyParam</a>"+MessagesCdmBase.LT+"<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"97\">MyCl</a><a title=\"The type pkg.MyParam is not parameterized correctly.\" class=\"e\">"+MessagesCdmBase.GT+"</a> <a name=\""+ExportCst.PREF_REF+"42\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"62\">pkg.MyParam</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"74\">T</a>,<a name=\""+ExportCst.PREF_REF+"76\">S</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"97\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyParam\" href=\"#"+ExportCst.PREF_REF+"62\">MyParam</a><a title=\"The type pkg.MyParam"+MessagesCdmBase.LT+"pkg.MyCl"+MessagesCdmBase.GT+" is not parameterized correctly.\" class=\"e\">"+MessagesCdmBase.LT+"</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"100\">MyCl</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"42\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"62\">pkg.MyParam</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"74\">T</a>:<a title=\"pkg.MyCo\" href=\"#"+ExportCst.PREF_REF+"127\">MyCo</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"100\">pkg.MyCl</a>{\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"127\">pkg.MyCo</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"The type Inexist"+MessagesCdmBase.LT+"MyCl"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">Inexist</a>"+MessagesCdmBase.LT+"<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"62\">MyCl</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"42\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"62\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $iterable"+MessagesCdmBase.LT+"<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"65\">MyCl</a><a title=\"The type $iterable"+MessagesCdmBase.LT+"MyCl."+MessagesCdmBase.GT+" is unknown.\" class=\"e\">.</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"45\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"65\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $iterable"+MessagesCdmBase.LT+"<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"71\">MyCl</a>.<a title=\"pkg.MyCl..Inner\" href=\"#"+ExportCst.PREF_REF+"97\">Inner</a><a title=\"The type $iterable"+MessagesCdmBase.LT+"MyCl.Inner."+MessagesCdmBase.GT+" is unknown.\" class=\"e\">.</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"51\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"71\">pkg.MyCl</a>{\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"97\">Inner</a>{\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report151Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $iterable<Inexist[]> v;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $iterable"+MessagesCdmBase.LT+"<a title=\"The type $iterable"+MessagesCdmBase.LT+"Inexist[]"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">Inexist</a>[]"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"49\">v</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report152Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $iterable<?Inexist> v;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $iterable"+MessagesCdmBase.LT+"?<a title=\"The type $iterable"+MessagesCdmBase.LT+"?Inexist"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">Inexist</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"48\">v</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $iterable"+MessagesCdmBase.LT+"<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"70\">MyCl</a><a title=\"The type pkg.MyCl..Inner is not parameterized correctly.\" class=\"e\">.</a><a title=\"pkg.MyCl..Inner\" href=\"#"+ExportCst.PREF_REF+"96\">Inner</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"50\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"70\">pkg.MyCl</a>{\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"96\">Inner</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"102\">T</a>"+MessagesCdmBase.GT+"{\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $iterable"+MessagesCdmBase.LT+"<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"72\">MyCl</a><a title=\"The type pkg.MyCl..Inner is not parameterized correctly.\" class=\"e\">.</a><a title=\"pkg.MyCl..Inner\" href=\"#"+ExportCst.PREF_REF+"98\">Inner</a>[]"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"52\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"72\">pkg.MyCl</a>{\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"98\">Inner</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"104\">T</a>"+MessagesCdmBase.GT+"{\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $iterable"+MessagesCdmBase.LT+"<a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"66\" class=\"e\">MyCl</a>[]"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"46\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"66\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"75\">T</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $iterable"+MessagesCdmBase.LT+"?<a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"65\" class=\"e\">MyCl</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"45\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"65\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"74\">T</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $iterable"+MessagesCdmBase.LT+"<a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"64\" class=\"e\">MyCl</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"44\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"64\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"73\">T</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyParam\" href=\"#"+ExportCst.PREF_REF+"126\">MyParam</a>"+MessagesCdmBase.LT+"<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"70\">MyCl</a><a title=\"The type pkg.MyCl..Inner is not parameterized correctly.\" class=\"e\">.</a><a title=\"pkg.MyCl..Inner\" href=\"#"+ExportCst.PREF_REF+"96\">Inner</a>[]"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"50\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"70\">pkg.MyCl</a>{\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"96\">Inner</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"102\">T</a>"+MessagesCdmBase.GT+"{\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"126\">pkg.MyParam</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"138\">S</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyParam\" href=\"#"+ExportCst.PREF_REF+"94\">MyParam</a>"+MessagesCdmBase.LT+"<a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"64\" class=\"e\">MyCl</a>[]"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"44\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"64\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"73\">T</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"94\">pkg.MyParam</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"106\">S</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyParam\" href=\"#"+ExportCst.PREF_REF+"93\">MyParam</a>"+MessagesCdmBase.LT+"?<a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"63\" class=\"e\">MyCl</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"43\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"63\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"72\">T</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"93\">pkg.MyParam</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"105\">S</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyParam\" href=\"#"+ExportCst.PREF_REF+"92\">MyParam</a>"+MessagesCdmBase.LT+"<a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"62\" class=\"e\">MyCl</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"42\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"62\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"71\">T</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"92\">pkg.MyParam</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"104\">S</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"60\">MyCl</a>\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"40\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"60\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"56\">MyCl</a>\n" +
                " <a name=\""+ExportCst.PREF_REF+"34\">ONE</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"56\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"58\">MyCl</a>\n" +
                " <a name=\""+ExportCst.PREF_REF+"34\">ONE</a>{};\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"58\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"67\">MyCl</a>\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"45\">v</a>();\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"67\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"66\">MyCl</a>\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"44\">v</a>();\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"66\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"66\">MyCl</a>\n" +
                " <a name=\""+ExportCst.PREF_REF+"39\">MySub(</a>){}\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"66\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"97\">MyCl</a>\n" +
                " $operator<a name=\""+ExportCst.PREF_REF+"48\">+</a> $int($int <a name=\""+ExportCst.PREF_REF+"60\">i</a>){\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"97\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\"><a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"The type MyCl is unknown.\" class=\"e\">MyCl</a>\n" +
                "$operator<a name=\""+ExportCst.PREF_REF+"15\">+</a> [<span class=\"i\">$static</span>;] $int($int <a name=\""+ExportCst.PREF_REF+"38\">i</a>){\n" +
                " $return 0;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"71\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"92\">MyCl</a>\n" +
                " $public $class [<span class=\"i\">$static</span>;] <a name=\""+ExportCst.PREF_REF+"65\">Inner</a>{\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"92\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"98\">MyCl</a>\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"44\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"55\">i</a>);\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"66\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"77\">i</a>);\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"98\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"37\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"48\">i</a>);\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"98\">MyCl</a>\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"66\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"77\">i</a>);\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"98\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"37\">$this</a>(<a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"97\">MyCl</a> $int <a name=\""+ExportCst.PREF_REF+"54\">i</a>);\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"65\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"76\">i</a>);\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"97\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"37\">$this</a>($int <a name=\""+ExportCst.PREF_REF+"48\">i</a>);\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"59\">$this</a>(<a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"97\">MyCl</a> $int <a name=\""+ExportCst.PREF_REF+"76\">i</a>);\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"97\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"104\">MyCl</a>\n" +
                " $static $int <a name=\""+ExportCst.PREF_REF+"52\">explicit</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"19\">MySub</a> <a name=\""+ExportCst.PREF_REF+"67\">i</a>){\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"104\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"108\">MyCl</a>\n" +
                " $static <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"19\">MySub</a> <a name=\""+ExportCst.PREF_REF+"53\">explicit</a>($int <a name=\""+ExportCst.PREF_REF+"67\">i</a>){\n" +
                "  $return $null;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"108\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " $static $int <a name=\""+ExportCst.PREF_REF+"45\">explicit</a>(<a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"103\">MyCl</a> <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"19\">MySub</a> <a name=\""+ExportCst.PREF_REF+"66\">i</a>){\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"103\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " $static <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"19\">MySub</a> <a name=\""+ExportCst.PREF_REF+"46\">explicit</a>(<a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"107\">MyCl</a> $int <a name=\""+ExportCst.PREF_REF+"66\">i</a>){\n" +
                "  $return $null;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"107\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"37\">v</a>(<a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"71\">MyCl</a> $int <a name=\""+ExportCst.PREF_REF+"50\">i</a>);\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"71\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"32\">MySub(</a><a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"71\">MyCl</a> $int <a name=\""+ExportCst.PREF_REF+"49\">i</a>){}\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"71\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"67\">MyCl</a>\n" +
                " <a name=\""+ExportCst.PREF_REF+"34\" title=\"pkg.MySub.pkg.MySub()\" href=\"#"+ExportCst.PREF_REF+"40\">ONE</a>;\n" +
                " <a name=\""+ExportCst.PREF_REF+"40\">MySub(</a>){}\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"67\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"76\">MyCl</a>\n" +
                " <a name=\""+ExportCst.PREF_REF+"34\" title=\"pkg.MySub.pkg.MySub($int)\" href=\"#"+ExportCst.PREF_REF+"43\">ONE</a>(1);\n" +
                " <a name=\""+ExportCst.PREF_REF+"43\">MySub(</a>$int <a name=\""+ExportCst.PREF_REF+"54\">i</a>){}\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"76\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"68\">MyCl</a>\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"45\">v</a>()1;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"68\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"69\">MyCl</a>\n" +
                " <a name=\""+ExportCst.PREF_REF+"34\">ONE</a>{};\n" +
                " <a name=\""+ExportCst.PREF_REF+"42\">MySub(</a>){}\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"69\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"71\">MyCl</a>\n" +
                " <a name=\""+ExportCst.PREF_REF+"34\" title=\"pkg.MySub-ONE.pkg.MySub-ONE($int)\" href=\"#"+ExportCst.PREF_REF+"41\">ONE</a>(1){<a name=\""+ExportCst.PREF_REF+"41\">(</a>$int <a name=\""+ExportCst.PREF_REF+"47\">i</a>){}};\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"71\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"The type MyCl is unknown.\" class=\"e\">MyCl</a>\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"41\">m</a>(){\n" +
                "  <b title=\"$int\">$var</b> <a name=\""+ExportCst.PREF_REF+"53\">i</a>=0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"34\">m</a>(){\n" +
                "  <a title=\"The inferring type $var is not defined for the variables i.\" class=\"e\">$var</a> <a name=\""+ExportCst.PREF_REF+"46\">i</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"The type MyCl is unknown.\" class=\"e\">MyCl</a>\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"41\">m</a>(){\n" +
                "  $for(<b title=\"$int\">$var</b> <a name=\""+ExportCst.PREF_REF+"58\">i</a>=0;;){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"34\">m</a>(){\n" +
                "  $for(<a title=\"The inferring type $var is not defined for the variables i.\" class=\"e\">$var</a> <a name=\""+ExportCst.PREF_REF+"51\">i</a>;;){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report190Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot({\"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"35\">MyAnnot</a>(<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">{</a><span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"35\">pkg.MyAnnot</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"57\">v</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report191Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot( {\"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"36\">MyAnnot</a>( <a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">{</a><span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"36\">pkg.MyAnnot</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"58\">v</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report192Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot({ \"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"36\">MyAnnot</a>(<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">{</a> <span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"36\">pkg.MyAnnot</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"58\">v</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MySub</a> {\n" +
                " <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"64\">MyCl</a>( <a title=\"After @ the type MyCl is not an annotation.\" class=\"e\">@</a><a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"64\">MyCl</a>)\n" +
                " <a name=\""+ExportCst.PREF_REF+"42\">ONE</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"64\">pkg.MyCl</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report194Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int<a title=\"No field could be retrieved.\" class=\"e\">;</a>\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $final<a title=\"There must be an expression.\" class=\"e\">;</a>\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The expression part is empty.\" class=\"e\">$int i</a>\n" +
                " <a title=\"Bad index by parsing.\" class=\"e\">}</a>\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"Bad index by parsing.\n" +
                "\n" +
                "The type java.lang.Object is unexpected.\" class=\"e\">$if</a><a title=\"There must be an expression.\" class=\"e\"> </a>{\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The block is unexpected.\" class=\"e\">$static</a> {\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report199Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $int i\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"Bad index by parsing.\" class=\"e\">{</a>\n" +
                "  $int i\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report200Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int i\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class pkg.MySub {\n" +
                " $int i<a title=\"Bad index by parsing.\" class=\"e\">\n" +
                "</a></span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyParam\" href=\"#"+ExportCst.PREF_REF+"74\">MyParam</a><a title=\"The type pkg.MyParam"+MessagesCdmBase.LT+"pkg.MySecond,pkg.MyFirst"+MessagesCdmBase.GT+" is not parameterized correctly.\" class=\"e\">"+MessagesCdmBase.LT+"</a><a title=\"pkg.MySecond\" href=\"#"+ExportCst.PREF_REF+"156\">MySecond</a><a title=\"The type pkg.MyParam"+MessagesCdmBase.LT+"pkg.MySecond,pkg.MyFirst"+MessagesCdmBase.GT+" is not parameterized correctly.\" class=\"e\">,</a><a title=\"pkg.MyFirst\" href=\"#"+ExportCst.PREF_REF+"126\">MyFirst</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"54\">i</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"74\">pkg.MyParam</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"86\">T</a>:<a title=\"pkg.MyFirst\" href=\"#"+ExportCst.PREF_REF+"126\">MyFirst</a>,<a name=\""+ExportCst.PREF_REF+"96\">S</a>:<a title=\"pkg.MySecond\" href=\"#"+ExportCst.PREF_REF+"156\">MySecond</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"126\">pkg.MyFirst</a>{\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"156\">pkg.MySecond</a>{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report202Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Inexist.Inner v;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"The type Inexist.Inner is unknown.\" class=\"e\">Inexist</a>.Inner <a name=\""+ExportCst.PREF_REF+"42\">v</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report203Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.Inexist.Inner v;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"The type pkg.Inexist.Inner is unknown.\" class=\"e\">pkg.Inexist</a>.Inner <a name=\""+ExportCst.PREF_REF+"46\">v</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"63\">pkg.MyEnum</a>..<a title=\"The type pkg.MyEnum..TWO is unknown.\" class=\"e\">TWO</a> <a name=\""+ExportCst.PREF_REF+"44\">v</a>;\n" +
                "}\n" +
                "$public $enum <a name=\""+ExportCst.PREF_REF+"63\">pkg.MyEnum</a>{\n" +
                " <a name=\""+ExportCst.PREF_REF+"76\">ONE</a>{}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"71\">pkg.MyEnum</a>..<a title=\"pkg.MyEnum-ONE\" href=\"#"+ExportCst.PREF_REF+"84\">ONE</a>.<a title=\"The type pkg.MyEnum..ONE.Inexist is unknown.\" class=\"e\">Inexist</a> <a name=\""+ExportCst.PREF_REF+"52\">v</a>;\n" +
                "}\n" +
                "$public $enum <a name=\""+ExportCst.PREF_REF+"71\">pkg.MyEnum</a>{\n" +
                " <a name=\""+ExportCst.PREF_REF+"84\">ONE</a>{\n" +
                "  $public $class <a name=\""+ExportCst.PREF_REF+"106\">Inner</a>{\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report206Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub.Inner v;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>.<a title=\"The type MySub.Inner is unknown.\" class=\"e\">Inner</a> <a name=\""+ExportCst.PREF_REF+"40\">v</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report207Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.MySub.Inner v;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">pkg.MySub</a>.<a title=\"The type pkg.MySub.Inner is unknown.\" class=\"e\">Inner</a> <a name=\""+ExportCst.PREF_REF+"44\">v</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"71\">pkg.MyEnum</a>..<a title=\"The type pkg.MyEnum..ONE"+MessagesCdmBase.LT+"String"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">ONE</a>"+MessagesCdmBase.LT+"String"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"52\">v</a>;\n" +
                "}\n" +
                "$public $enum <a name=\""+ExportCst.PREF_REF+"71\">pkg.MyEnum</a>{\n" +
                " <a name=\""+ExportCst.PREF_REF+"84\">ONE</a>{}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"71\">pkg.MyEnum</a>"+MessagesCdmBase.LT+"String"+MessagesCdmBase.GT+"<a title=\"The type pkg.MyEnum"+MessagesCdmBase.LT+"java.lang.String"+MessagesCdmBase.GT+"-ONE is not parameterized correctly.\" class=\"e\">..</a><a title=\"pkg.MyEnum-ONE\" href=\"#"+ExportCst.PREF_REF+"87\">ONE</a> <a name=\""+ExportCst.PREF_REF+"52\">v</a>;\n" +
                "}\n" +
                "$public $enum <a name=\""+ExportCst.PREF_REF+"71\">pkg.MyEnum</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"82\">T</a>"+MessagesCdmBase.GT+"{\n" +
                " <a name=\""+ExportCst.PREF_REF+"87\">ONE</a>"+MessagesCdmBase.LT+"String"+MessagesCdmBase.GT+"{}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyClass\" href=\"#"+ExportCst.PREF_REF+"74\">pkg.MyClass</a>"+MessagesCdmBase.LT+"String"+MessagesCdmBase.GT+"<a title=\"The type pkg.MyClass"+MessagesCdmBase.LT+"java.lang.String"+MessagesCdmBase.GT+"..Inner is not parameterized correctly.\" class=\"e\">.</a><a title=\"pkg.MyClass..Inner\" href=\"#"+ExportCst.PREF_REF+"114\">Inner</a> <a name=\""+ExportCst.PREF_REF+"54\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"74\">pkg.MyClass</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"86\">T</a>"+MessagesCdmBase.GT+"{\n" +
                " $public $static $class <a name=\""+ExportCst.PREF_REF+"114\">Inner</a>{\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyClass\" href=\"#"+ExportCst.PREF_REF+"67\">pkg.MyClass</a>..<a title=\"The type pkg.MyClass..Inner is unknown.\" class=\"e\">Inner</a> <a name=\""+ExportCst.PREF_REF+"47\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"67\">pkg.MyClass</a>{\n" +
                " $public $static $class <a name=\""+ExportCst.PREF_REF+"104\">Inner</a>{\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyClass\" href=\"#"+ExportCst.PREF_REF+"67\">pkg.MyClass</a>..<a title=\"The type pkg.MyClass..Inner is unknown.\" class=\"e\">Inner</a> <a name=\""+ExportCst.PREF_REF+"47\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"67\">pkg.MyClass</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"79\">T</a>"+MessagesCdmBase.GT+"{\n" +
                " $public $static $class <a name=\""+ExportCst.PREF_REF+"107\">Inner</a>{\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report213Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(?);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue(<a title=\"The type ? is unknown.\" class=\"e\">?</a>);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report214Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(?String);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue(<a title=\"The type ?String is unknown.\" class=\"e\">?</a>String);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report215Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($iterable<$void>);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue($iterable"+MessagesCdmBase.LT+"<a title=\"The type $iterable"+MessagesCdmBase.LT+"$void"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">$void</a>"+MessagesCdmBase.GT+");\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report216Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($Fct<$void,$int>);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue($Fct"+MessagesCdmBase.LT+"<a title=\"The type $Fct"+MessagesCdmBase.LT+"$void,$int"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">$void</a>,$int"+MessagesCdmBase.GT+");\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report217Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($Fct<?$int>);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue($Fct"+MessagesCdmBase.LT+"<a title=\"The type $Fct"+MessagesCdmBase.LT+"?$int"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">?</a>$int"+MessagesCdmBase.GT+");\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report218Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($void.$int);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue(<a title=\"The type $void.$int is unknown.\" class=\"e\">$void</a>.$int);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report219Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($void[]);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue(<a title=\"The type $void[] is unknown.\" class=\"e\">$void</a>[]);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report220Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($void);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue(<a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report221Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($Fct<a<b,c>d>);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue($Fct<a title=\"The type $Fct"+MessagesCdmBase.LT+"a"+MessagesCdmBase.LT+"b,c"+MessagesCdmBase.GT+"d"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">"+MessagesCdmBase.LT+"</a>a"+MessagesCdmBase.LT+"b,c"+MessagesCdmBase.GT+"d"+MessagesCdmBase.GT+");\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report222Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($iterable<!>);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue($iterable<a title=\"The type $iterable"+MessagesCdmBase.LT+"!"+MessagesCdmBase.GT+" is unknown.\" class=\"e\">"+MessagesCdmBase.LT+"</a>!"+MessagesCdmBase.GT+");\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report223Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue($iterable<$iterable<!>>);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue($iterable"+MessagesCdmBase.LT+"$iterable<a title=\"The type $iterable"+MessagesCdmBase.LT+"$iterable"+MessagesCdmBase.LT+"!"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" is unknown.\" class=\"e\">"+MessagesCdmBase.LT+"</a>!"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+");\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report224Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(!);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue(<a title=\"The type ! is unknown.\" class=\"e\">!</a>);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report225Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(a<b);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue(<a title=\"The type a"+MessagesCdmBase.LT+"b is unknown.\" class=\"e\">a"+MessagesCdmBase.LT+"b</a>);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report226Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(!String);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue(<a title=\"The type !String is unknown.\" class=\"e\">!</a>String);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report227Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=explicit(Object,)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=explicit(Object,<a title=\"There must be a type.\" class=\"e\">)</a>$null;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report228Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=explicit(Object,Object,)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<a title=\"The function $static explicit(java.lang.Object,) is undefined.\" class=\"e\">explicit</a>(Object,Object,<a title=\"There must be a type.\" class=\"e\">)</a>$null;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report229Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=explicit(Object,,Object)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<a title=\"The function $static explicit(java.lang.Object,) is undefined.\" class=\"e\">explicit</a>(Object,<a title=\"There must be a type.\" class=\"e\">,</a>Object)$null;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report230Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=explicit(Object,,)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<a title=\"The function $static explicit(java.lang.Object,) is undefined.\" class=\"e\">explicit</a>(Object,<a title=\"There must be a type.\" class=\"e\">,</a><a title=\"There must be a type.\" class=\"e\">)</a>$null;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report231Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$(Object,)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$(Object,<a title=\"There must be a type.\" class=\"e\">)</a>$null;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report232Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$(Object,Object,)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<a title=\"The function $static $(java.lang.Object,) is undefined.\" class=\"e\">$</a>(Object,Object,<a title=\"There must be a type.\" class=\"e\">)</a>$null;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report233Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$(Object,,Object)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<a title=\"The function $static $(java.lang.Object,) is undefined.\" class=\"e\">$</a>(Object,<a title=\"There must be a type.\" class=\"e\">,</a>Object)$null;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report234Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$(Object,,)$null;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<a title=\"The function $static $(java.lang.Object,) is undefined.\" class=\"e\">$</a>(Object,<a title=\"There must be a type.\" class=\"e\">,</a><a title=\"There must be a type.\" class=\"e\">)</a>$null;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report235Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($id(String<a title=\"There must be a type.\" class=\"e\">,</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report236Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,$static,));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($id(String,$static<a title=\"There must be a type.\" class=\"e\">,</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report237Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,0,));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($id(String,0<a title=\"There must be a type.\" class=\"e\">,</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report238Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,,));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($id(String<a title=\"There must be a type.\" class=\"e\">,</a><a title=\"There must be a type.\" class=\"e\">,</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report239Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,$static,0,));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($id(String,$static,0<a title=\"There must be a type.\" class=\"e\">,</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report240Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(!MySub);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue(<a title=\"The type !MySub is unknown.\" class=\"e\">!</a><a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report241Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=$defaultValue(?MySub);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=$defaultValue(<a title=\"The type ?MySub is unknown.\" class=\"e\">?</a><a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report242Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,,String));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($id(String<a title=\"There must be a type.\" class=\"e\">,</a>,String));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report243Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(String,$void));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($id(String,<a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report244Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.length($id(<a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report245Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(pkg.Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.length($id(<a title=\"The type pkg.Inexist is unknown.\" class=\"e\">pkg.Inexist</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report246Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(MySub.Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.length($id(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>.<a title=\"The type MySub.Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report247Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id(pkg.MySub.Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.length($id(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">pkg.MySub</a>.<a title=\"The type pkg.MySub.Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report248Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id());\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.length($id<a title=\"There must be a type.\" class=\"e\">(</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report249Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id($void));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.length($id(<a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report250Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id( $void));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.length($id( <a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report251Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id( Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.length($id( <a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report252Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id( pkg.Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.length($id( <a title=\"The type pkg.Inexist is unknown.\" class=\"e\">pkg.Inexist</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report253Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id( MySub.Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.length($id( <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>.<a title=\"The type MySub.Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report254Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($id( pkg.MySub.Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.length($id( <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">pkg.MySub</a>.<a title=\"The type pkg.MySub.Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report255Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($vararg($void));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($vararg(<a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report256Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($vararg( $void));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($vararg( <a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report257Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($vararg());\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($vararg<a title=\"There must be a type.\" class=\"e\">(</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report258Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($vararg(Inexist));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($vararg(<a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report259Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object v=\"\".length($vararg(MySub<String>));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($vararg(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>"+MessagesCdmBase.LT+"String<a title=\"The type pkg.MySub is not parameterized correctly.\" class=\"e\">"+MessagesCdmBase.GT+"</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report260Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub<T> {\n");
        xml_.append(" Object v=\"\".length($vararg(MySub));\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"25\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"38\">v</a>=<span class=\"s\">\"\"</span>.<a title=\"The function length() is undefined.\" class=\"e\">length</a>($vararg(<a title=\"The type pkg.MySub is not parameterized correctly.\n" +
                "\n" +
                "pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\" class=\"e\">MySub</a>));\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report261Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [$static;] $interfaces(Inexist) pkg.MySub<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class [<span class=\"i\">$static</span>;] $interfaces(<a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a>) <a name=\""+ExportCst.PREF_REF+"47\">pkg.MySub</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"57\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report262Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot({1,\"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"37\">MyAnnot</a>({1<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">,</a><span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"37\">pkg.MyAnnot</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"59\">v</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report263Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot( {1,\"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"38\">MyAnnot</a>( {1<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">,</a><span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"38\">pkg.MyAnnot</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"60\">v</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report264Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot({1, \"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"38\">MyAnnot</a>({1<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">,</a> <span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"38\">pkg.MyAnnot</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"60\">v</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report265Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot( {1,\"\"})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"38\">MyAnnot</a>( {1<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">,</a><span class=\"s\">\"\"</span>})\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"38\">pkg.MyAnnot</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"60\">v</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report266Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@Inex({})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\"><a title=\"After @ the type Inex is not an annotation.\" class=\"e\">@</a><a title=\"The type Inex is unknown.\" class=\"e\">Inex</a>(<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>})\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"30\">pkg.MyAnnot</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report267Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@Inex({1})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\"><a title=\"After @ the type Inex is not an annotation.\" class=\"e\">@</a><a title=\"The type Inex is unknown.\" class=\"e\">Inex</a>(<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>1})\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"31\">pkg.MyAnnot</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report268Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int m(];\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"40\" title=\"Bad index by parsing.\n" +
                "\n" +
                "The type java.lang.Object cannot be implicitly cast to $int\" class=\"e\">m</a><a title=\"The expression part is empty.\" class=\"e\">(]</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report269Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int m(a);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"40\" title=\"Bad index by parsing.\" class=\"e\">m</a>(a);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report270Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int ();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"40\" title=\"The method name  is not valid. It must be a word.\" class=\"e\">(</a>);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report271Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int m[);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MyAnnot</a> {\n" +
                " $int <a title=\"No field could be retrieved.\" class=\"e\">m[)</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report272Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" Object m();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MyAnnot</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"42\" title=\"The return type java.lang.Object in the function m() is unexpected.\" class=\"e\">m</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report273Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int m(){};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"40\">m</a>()<a title=\"The type $int is unexpected.\" class=\"e\">{</a>};\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report274Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" Object m(){};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MyAnnot</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"42\" title=\"The return type java.lang.Object in the function m() is unexpected.\" class=\"e\">m</a>()<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>};\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report275Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int m()\"\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"40\" title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">m</a>()<span class=\"s\">\"\"</span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report276Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[][] m()\"\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MyAnnot</a> {\n" +
                " $int[][] <a name=\""+ExportCst.PREF_REF+"44\" title=\"The return type [[$int in the function m() is unexpected.\n" +
                "\n" +
                "The type java.lang.String cannot be implicitly cast to [[$int\" class=\"e\">m</a>()<span class=\"s\">\"\"</span>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report277Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@Inex({ 1})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\"><a title=\"After @ the type Inex is not an annotation.\" class=\"e\">@</a><a title=\"The type Inex is unknown.\" class=\"e\">Inex</a>(<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a> 1})\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"32\">pkg.MyAnnot</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report278Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@Inex( {})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\"><a title=\"After @ the type Inex is not an annotation.\" class=\"e\">@</a><a title=\"The type Inex is unknown.\" class=\"e\">Inex</a>( <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>})\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"31\">pkg.MyAnnot</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report279Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" Object m() {};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $annotation <a name=\""+ExportCst.PREF_REF+"20\">pkg.MyAnnot</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"42\" title=\"The return type java.lang.Object in the function m() is unexpected.\" class=\"e\">m</a>() <a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>};\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report280Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass<T:> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"There must be a type.\" class=\"e\">pkg.MyClass</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"27\">T</a>:"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report281Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass:: {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The super types of the type pkg.MyClass could not be found.\n" +
                "\n" +
                "There must be a type.\n" +
                "\n" +
                "There must be a type.\" class=\"e\">pkg.MyClass</a>:: {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report282Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass: {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The super types of the type pkg.MyClass could not be found.\n" +
                "\n" +
                "There must be a type.\" class=\"e\">pkg.MyClass</a>: {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  <a title=\"The type $int is unexpected.\" class=\"e\">$for</a>(;1;){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  <a title=\"The type java.lang.String is not a primitive type or a wrapper type.\n" +
                "\n" +
                "The type $int cannot be implicitly cast to java.lang.String\n" +
                "\n" +
                "The type $int cannot be implicitly cast to java.lang.String\" class=\"e\">$iter</a>(String <a name=\""+ExportCst.PREF_REF+"47\">s</a>=<span class=\"s\">\"\"</span>;1;1){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  <a title=\"The type java.lang.String is not a primitive type or a wrapper type.\" class=\"e\">$for</a>[String](;;){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  <a title=\"The type java.lang.String is not a primitive type or a wrapper type.\" class=\"e\">$iter</a>[String]($int <a name=\""+ExportCst.PREF_REF+"53\">s</a>=0;1;1){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  <a title=\"The type  is unknown.\" class=\"e\">$switch</a>($null){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  <a title=\"The type java.lang.String is not a primitive type or a wrapper type.\" class=\"e\">$for</a>[String]($int <a name=\""+ExportCst.PREF_REF+"52\">i</a>:{}){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  $iterableTable"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"60\">it</a> = $null;\n" +
                "  <a title=\"The type java.lang.String is not a primitive type or a wrapper type.\" class=\"e\">$for</a>[String]($int <a name=\""+ExportCst.PREF_REF+"92\">i</a>,$int <a name=\""+ExportCst.PREF_REF+"99\">j</a>:<a href=\"#"+ExportCst.PREF_REF+"60\">it</a>){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"44\">i</a><a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">:</a>$new String[]{<span class=\"s\">\"\"</span>}){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  $iterable"+MessagesCdmBase.LT+"String"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"52\">it</a> = $null;\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"76\">i</a><a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">:</a><a href=\"#"+ExportCst.PREF_REF+"52\">it</a>){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  String <a name=\""+ExportCst.PREF_REF+"41\">it</a> = $null;\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"65\">i</a><a title=\"The type java.lang.Object cannot be implicitly cast to java.lang.$iterable\" class=\"e\">:</a><a href=\"#"+ExportCst.PREF_REF+"41\">it</a>){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  $iterableTable"+MessagesCdmBase.LT+"String,String"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"64\">it</a> = $null;\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"88\">i</a>,$int <a name=\""+ExportCst.PREF_REF+"95\">j</a><a title=\"The type java.lang.String cannot be implicitly cast to $int\n" +
                "\n" +
                "The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">:</a><a href=\"#"+ExportCst.PREF_REF+"64\">it</a>){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  String <a name=\""+ExportCst.PREF_REF+"41\">it</a> = $null;\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"65\">i</a>,$int <a name=\""+ExportCst.PREF_REF+"72\">j</a><a title=\"The type java.lang.Object cannot be implicitly cast to java.lang.$iterableTable\" class=\"e\">:</a><a href=\"#"+ExportCst.PREF_REF+"41\">it</a>){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"44\">i</a>,$int <a name=\""+ExportCst.PREF_REF+"51\">j</a><a title=\"The type java.lang.Object cannot be implicitly cast to java.lang.$iterableTable\n" +
                "\n" +
                "The value must not be null because of possible code.util.exceptions.NullObjectException.\" class=\"e\">:</a>$null){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"44\">i</a><a title=\"The value must not be null because of possible code.util.exceptions.NullObjectException.\" class=\"e\">:</a>($int[])$null){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"44\">i</a><a title=\"The value must not be null because of possible code.util.exceptions.NullObjectException.\" class=\"e\">:</a>($iterable"+MessagesCdmBase.LT+"$int"+MessagesCdmBase.GT+")$null){\n" +
                "  }\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report298Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot({1,})\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"35\">MyAnnot</a>({1<a title=\"The expression part is empty.\" class=\"e\">,</a>})\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"35\">pkg.MyAnnot</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"57\">v</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report299Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot({1, })\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int[] v();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"36\">MyAnnot</a>({1<a title=\"The expression part is empty.\" class=\"e\">,</a> })\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"36\">pkg.MyAnnot</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"58\">v</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  1<a title=\"The type $int is unexpected.\" class=\"e\">?</a>1:1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  $bool<a title=\"The type $int is unexpected.\" class=\"e\">(</a>1,1,1);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  <a title=\"The number of required operands 3 is different from the number of supplied arguments 4 for the operator $bool\" class=\"e\">$bool</a>(1,1,1,1);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
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
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  <a title=\"The expression part is empty.\" class=\"e\">($void)$null</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report304Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $int i}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The expression part is empty.\" class=\"e\">$int i</a><a title=\"Bad index by parsing.\" class=\"e\">}</a>\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report305Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  ();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The number of required operands 1 is different from the number of supplied arguments 0.\" class=\"e\">(</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report306Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $default();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The number of required operands 1 is different from the number of supplied arguments 0.\" class=\"e\">$default</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report307Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $int i=0;\n");
        xml_.append("  $int j=0;\n");
        xml_.append("  i++j;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $int <a name=\""+ExportCst.PREF_REF+"37\">i</a>=0;\n" +
                "  $int <a name=\""+ExportCst.PREF_REF+"49\">j</a>=0;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"37\">i</a><a title=\"The number of required operands 1 is different from the number of supplied arguments 2.\" class=\"e\">++</a><a href=\"#"+ExportCst.PREF_REF+"49\">j</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report308Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  String i=\"\";\n");
        xml_.append("  i++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  String <a name=\""+ExportCst.PREF_REF+"39\">i</a>=<span class=\"s\">\"\"</span>;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"39\">i</a><a title=\"The type java.lang.String is unexpected.\" class=\"e\">++</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report309Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  String i=\"\";\n");
        xml_.append("  ++i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  String <a name=\""+ExportCst.PREF_REF+"39\">i</a>=<span class=\"s\">\"\"</span>;\n" +
                "  <a title=\"The type java.lang.String is unexpected.\" class=\"e\">++</a><a href=\"#"+ExportCst.PREF_REF+"39\">i</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report310Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  \"\" $instanceof $int[] $int;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <span class=\"s\">\"\"</span> <a title=\"The number of required operands 1 is different from the number of supplied arguments 2.\" class=\"e\">$instanceof</a> $int[] <a title=\"There is no accessible field named $int from the type pkg.MySub in this context.\" class=\"e\">$int</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report311Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $valueOf();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The number of required operands 1 is different from the number of supplied arguments 0.\" class=\"e\">$valueOf</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report312Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $firstopt();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The number of required operands 1 is different from the number of supplied arguments 0.\" class=\"e\">$firstopt</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report313Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $valueOf(\"\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The number of required operands 1 is different from the number of supplied arguments 0.\" class=\"e\">$valueOf</a>(\"\");\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report314Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $int i = 1;\n");
        xml_.append("  i $instanceof;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $int <a name=\""+ExportCst.PREF_REF+"37\">i</a> = 1;\n" +
                "  <a title=\"The type i is unknown.\" class=\"e\">i</a> <a title=\"The expression part is empty.\n" +
                "\n" +
                "There must be a type.\" class=\"e\">$instanceof</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report315Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final $int;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final $int<a title=\"No field could be retrieved.\" class=\"e\">;</a>\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report316Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $int i = 0;\n");
        xml_.append("  i $instanceof $int[] i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $int <a name=\""+ExportCst.PREF_REF+"37\">i</a> = 0;\n" +
                "  <a title=\"The type i is unknown.\" class=\"e\">i</a> <a title=\"The expression part is empty.\n" +
                "\n" +
                "The number of required operands 1 is different from the number of supplied arguments 2.\" class=\"e\">$instanceof</a> $int[] <a href=\"#"+ExportCst.PREF_REF+"37\">i</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report317Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  ($int);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"There is no accessible field named $int from the type pkg.MySub in this context.\" class=\"e\">$int</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report318Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"There is no accessible field named MySub from the type pkg.MySub in this context.\" class=\"e\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report319Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $($int);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The expression part is empty.\" class=\"e\">$</a>($int);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report320Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $(MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The expression part is empty.\" class=\"e\">$</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report321Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The expression part is empty.\" class=\"e\">$</a>(<a title=\"There must be a type.\" class=\"e\">)</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report322Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Integer i;\n");
        xml_.append(" {\n");
        xml_.append("  i.;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Integer <a name=\""+ExportCst.PREF_REF+"36\">i</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"36\">i</a><a title=\"The expression part is empty.\" class=\"e\">.</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report323Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int[] i;\n");
        xml_.append(" {\n");
        xml_.append("  i[1,2];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"35\">i</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"35\">i</a>[1<a title=\"The number of required operands 1 is different from the number of supplied arguments 2 for the operator []\" class=\"e\">,</a>2];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report324Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int[] i;\n");
        xml_.append(" {\n");
        xml_.append("  i[,1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"35\">i</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"35\">i</a><a title=\"The expression part is empty.\" class=\"e\">[</a><a title=\"The number of required operands 1 is different from the number of supplied arguments 2 for the operator []\" class=\"e\">,</a>1];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report325Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  i[,1];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this(Object o, Object p){\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this(Object o, Object p){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">i</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a><a title=\"The expression part is empty.\n" +
                "\n" +
                "pkg.MySub.[](java.lang.Object,java.lang.Object)\" href=\"#"+ExportCst.PREF_REF+"111\" class=\"e\">[</a>,1<a title=\"pkg.MySub.[](java.lang.Object,java.lang.Object)\" href=\"#"+ExportCst.PREF_REF+"111\">]</a>;\n" +
                " }\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"67\">$this</a>(Object <a name=\""+ExportCst.PREF_REF+"80\">o</a>, Object <a name=\""+ExportCst.PREF_REF+"90\">p</a>){\n" +
                " }\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"111\">$this</a>(Object <a name=\""+ExportCst.PREF_REF+"124\">o</a>, Object <a name=\""+ExportCst.PREF_REF+"134\">p</a>){\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report326Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  i.$that[,1];\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $void $this(Object o, Object p);\n");
        xml_.append(" $public $abstract $int $this(Object o, Object p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"19\">MySub</a> <a name=\""+ExportCst.PREF_REF+"38\">i</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"38\">i</a>.$that<a title=\"The expression part is empty.\n" +
                "\n" +
                "The method [](java.lang.Object,java.lang.Object) from the type pkg.MySub must not be called directly because of abstract.\n" +
                "\n" +
                "pkg.MySub.[](java.lang.Object,java.lang.Object)\" href=\"#"+ExportCst.PREF_REF+"138\" class=\"e\">[</a>,1<a title=\"pkg.MySub.[](java.lang.Object,java.lang.Object)\" href=\"#"+ExportCst.PREF_REF+"138\">]</a>;\n" +
                " }\n" +
                " $public $abstract $void <a name=\""+ExportCst.PREF_REF+"87\">$this</a>(Object <a name=\""+ExportCst.PREF_REF+"100\">o</a>, Object <a name=\""+ExportCst.PREF_REF+"110\">p</a>);\n" +
                " $public $abstract $int <a name=\""+ExportCst.PREF_REF+"138\">$this</a>(Object <a name=\""+ExportCst.PREF_REF+"151\">o</a>, Object <a name=\""+ExportCst.PREF_REF+"161\">p</a>);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report327Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" $int[] i;\n");
        xml_.append(" {\n");
        xml_.append("  ?[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $void $this(Object o, Object p);\n");
        xml_.append(" $public $abstract $int $this(Object o, Object p);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"39\">i</a>;\n" +
                " {\n" +
                "  <a title=\"The expression part is empty.\" class=\"e\">?</a><a title=\"The type java.lang.Object is unexpected.\" class=\"e\">[</a>0];\n" +
                " }\n" +
                " $public $abstract $void <a name=\""+ExportCst.PREF_REF+"81\">$this</a>(Object <a name=\""+ExportCst.PREF_REF+"94\">o</a>, Object <a name=\""+ExportCst.PREF_REF+"104\">p</a>);\n" +
                " $public $abstract $int <a name=\""+ExportCst.PREF_REF+"132\">$this</a>(Object <a name=\""+ExportCst.PREF_REF+"145\">o</a>, Object <a name=\""+ExportCst.PREF_REF+"155\">p</a>);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report328Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  ((MySub)$null)[,1];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this(Object o, Object p){\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this(Object o, Object p){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  ((<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>)$null)<a title=\"The expression part is empty.\n" +
                "\n" +
                "The value must not be null because of possible code.util.exceptions.NullObjectException.\n" +
                "\n" +
                "pkg.MySub.[](java.lang.Object,java.lang.Object)\" href=\"#"+ExportCst.PREF_REF+"114\" class=\"e\">[</a>,1<a title=\"pkg.MySub.[](java.lang.Object,java.lang.Object)\" href=\"#"+ExportCst.PREF_REF+"114\">]</a>;\n" +
                " }\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"70\">$this</a>(Object <a name=\""+ExportCst.PREF_REF+"83\">o</a>, Object <a name=\""+ExportCst.PREF_REF+"93\">p</a>){\n" +
                " }\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"114\">$this</a>(Object <a name=\""+ExportCst.PREF_REF+"127\">o</a>, Object <a name=\""+ExportCst.PREF_REF+"137\">p</a>){\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report329Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  ((MySub[])$null)[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  ((<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>[])$null)<a title=\"The value must not be null because of possible code.util.exceptions.NullObjectException.\" class=\"e\">[</a>0];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report330Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  explicit();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The expression part is empty.\" class=\"e\">explicit</a>(<a title=\"There must be a type.\" class=\"e\">)</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report331Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $defaultValue();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $defaultValue<a title=\"There must be a type.\" class=\"e\">(</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report332Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $(Object,Object);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The expression part is empty.\" class=\"e\">$</a>(Object,Object);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report333Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  i.$that.m();\n");
        xml_.append(" }\n");
        xml_.append(" $void m();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"19\">MySub</a> <a name=\""+ExportCst.PREF_REF+"38\">i</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"38\">i</a>.$that.<a title=\"The method m() from the type pkg.MySub must not be called directly because of abstract.\n" +
                "\n" +
                "pkg.MySub.m()\" href=\"#"+ExportCst.PREF_REF+"69\" class=\"e\">m</a>();\n" +
                " }\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"69\">m</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report334Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  ((MySub)$null).m();\n");
        xml_.append(" }\n");
        xml_.append(" $void m();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " {\n" +
                "  ((<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"19\">MySub</a>)$null).<a title=\"The value must not be null because of possible code.util.exceptions.NullObjectException.\n" +
                "\n" +
                "pkg.MySub.m()\" href=\"#"+ExportCst.PREF_REF+"66\" class=\"e\">m</a>();\n" +
                " }\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"66\">m</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report335Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (($int[])$null).clone();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (($int[])$null).<a title=\"The value must not be null because of possible code.util.exceptions.NullObjectException.\" class=\"e\">clone</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report336Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (($int[])$null).clones();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (($int[])$null).<a title=\"Only the method clone can ne used for the array type [$int\" class=\"e\">clones</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report337Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $thisaccess(MyCl)m();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" $void m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $thisaccess(<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"82\">MyCl</a>)<a title=\"The type pkg.MySub cannot be implicitly cast to pkg.MyCl\n" +
                "\n" +
                "pkg.MyCl.m()\" href=\"#"+ExportCst.PREF_REF+"100\" class=\"e\">m</a>();\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"82\">pkg.MyCl</a> {\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"100\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report338Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $classchoice(MyCl)m();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" $void m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $classchoice(<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"83\">MyCl</a>)<a title=\"The method m() from the type pkg.MyCl must not be called directly because of abstract.\n" +
                "\n" +
                "pkg.MyCl.m()\" href=\"#"+ExportCst.PREF_REF+"101\" class=\"e\">m</a>();\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"83\">pkg.MyCl</a> {\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"101\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report339Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $classchoice(MyCl)inexist();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" $void m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $classchoice(<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"89\">MyCl</a>)<a title=\"The function inexist() is undefined.\" class=\"e\">inexist</a>();\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"89\">pkg.MyCl</a> {\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"107\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report340Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $superaccess(MySub)m();\n");
        xml_.append(" }\n");
        xml_.append(" $void m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $superaccess(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"19\">MySub</a>)<a title=\"The method m() from the type pkg.MySub must not be called directly because of abstract.\n" +
                "\n" +
                "pkg.MySub.m()\" href=\"#"+ExportCst.PREF_REF+"70\" class=\"e\">m</a>();\n" +
                " }\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"70\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report341Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $superaccess(MyCl)m();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" $void m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $superaccess(<a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"79\">MyCl</a>)<a title=\"The type pkg.MySub cannot be implicitly cast to pkg.MyCl\n" +
                "\n" +
                "pkg.MyCl.m()\" href=\"#"+ExportCst.PREF_REF+"97\" class=\"e\">m</a>();\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"79\">pkg.MyCl</a> {\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"97\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report342Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $superaccess(MySub)inexist();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $superaccess(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"19\">MySub</a>)<a title=\"The function inexist() is undefined.\" class=\"e\">inexist</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report343Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot(a=\"1\")\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"36\">MyAnnot</a>(<a title=\"pkg.MyAnnot.a()\" href=\"#"+ExportCst.PREF_REF+"56\">a</a><a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">=</a><span class=\"s\">\"1\"</span>)\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"36\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"56\">a</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report344Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot(a =\"1\")\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"37\">MyAnnot</a>(<a title=\"pkg.MyAnnot.a()\" href=\"#"+ExportCst.PREF_REF+"57\">a</a> <a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">=</a><span class=\"s\">\"1\"</span>)\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"37\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"57\">a</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report345Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot( a=\"1\")\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"37\">MyAnnot</a>( <a title=\"pkg.MyAnnot.a()\" href=\"#"+ExportCst.PREF_REF+"57\">a</a><a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">=</a><span class=\"s\">\"1\"</span>)\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"37\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"57\">a</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report346Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot(a=1,b=2)\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"38\">MyAnnot</a>(<a title=\"pkg.MyAnnot.a()\" href=\"#"+ExportCst.PREF_REF+"58\">a</a>=1,<a title=\"There is no accessible field named b from the type pkg.MyAnnot in this context.\" class=\"e\">b</a>=2)\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"38\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"58\">a</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report347Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot(a=1,a=2)\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"38\">MyAnnot</a>(<a title=\"The field a of the annotatation is supplied by duplicate.\n" +
                "\n" +
                "pkg.MyAnnot.a()\" href=\"#"+ExportCst.PREF_REF+"58\" class=\"e\">a</a>=1,<a title=\"The field a of the annotatation is supplied by duplicate.\n" +
                "\n" +
                "pkg.MyAnnot.a()\" href=\"#"+ExportCst.PREF_REF+"58\" class=\"e\">a</a>=2)\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"38\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"58\">a</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report348Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot()\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"31\">MyAnnot</a>(<a title=\"The field a of the annotatation is compulsory.\" class=\"e\">)</a>\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"31\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"51\">a</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report349Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot(a=1)\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append(" $int b();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"34\">MyAnnot</a>(<a title=\"pkg.MyAnnot.a()\" href=\"#"+ExportCst.PREF_REF+"54\">a</a>=1<a title=\"The field b of the annotatation is compulsory.\" class=\"e\">)</a>\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"34\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"54\">a</a>();\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"65\">b</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report350Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\"><a title=\"The field a of the annotatation is compulsory.\" class=\"e\">@</a><a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"29\">MyAnnot</a>\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"29\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"49\">a</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report351Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot(a=1)\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append(" $int b();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"34\">MyAnnot</a>(<a title=\"pkg.MyAnnot.a()\" href=\"#"+ExportCst.PREF_REF+"54\">a</a>=1<a title=\"The field b of the annotatation is compulsory.\" class=\"e\">)</a>\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"34\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"54\">a</a>();\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"65\">b</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report352Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot(1)\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append(" $int b();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"32\">MyAnnot</a>(1<a title=\"The field of the annotatation could not be found uniquely.\" class=\"e\">)</a>\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"32\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"52\">a</a>();\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"63\">b</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report353Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot(\"1\")\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"34\">MyAnnot</a><a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">(</a><span class=\"s\">\"1\"</span>)\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"34\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"54\">a</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report354Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnot(\"1\",b=2)\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append(" $int b();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"38\">MyAnnot</a>(<span class=\"s\">\"1\"</span>,<a title=\"pkg.MyAnnot.b()\" href=\"#"+ExportCst.PREF_REF+"69\">b</a>=2<a title=\"The field of the annotatation could not be found uniquely.\n" +
                "\n" +
                "The field a of the annotatation is compulsory.\" class=\"e\">)</a>\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"38\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"58\">a</a>();\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"69\">b</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report355Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  MySub m = $null;\n");
        xml_.append("  ((MySub)m)[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"38\">m</a> = $null;\n" +
                "  ((<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>)<a href=\"#"+ExportCst.PREF_REF+"38\">m</a>)<a title=\"The type pkg.MySub is unexpected.\" class=\"e\">[</a>0];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report356Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  MySub m = $null;\n");
        xml_.append("  $double v = 1.0;\n");
        xml_.append("  ((MySub[])m)[v];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"38\">m</a> = $null;\n" +
                "  $double <a name=\""+ExportCst.PREF_REF+"59\">v</a> = 1.0;\n" +
                "  ((<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>[])<a href=\"#"+ExportCst.PREF_REF+"38\">m</a>)[<a href=\"#"+ExportCst.PREF_REF+"59\">v</a><a title=\"The type $double is unexpected.\" class=\"e\">]</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report357Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  MySub m = $null;\n");
        xml_.append("  ((MySub[])m).len;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"38\">m</a> = $null;\n" +
                "  ((<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>[])<a href=\"#"+ExportCst.PREF_REF+"38\">m</a>).<a title=\"There is no accessible field named len from the type [pkg.MySub in this context.\" class=\"e\">len</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report358Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  ((MySub[])$null).length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  ((<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>[])$null).<a title=\"The value must not be null because of possible code.util.exceptions.NullObjectException.\" class=\"e\">length</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report359Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MyAnnotCont(@MyAnnot)\n");
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int a();\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.MyAnnotCont {\n");
        xml_.append(" MyAnnot b();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MyAnnotCont\" href=\"#"+ExportCst.PREF_REF+"90\">MyAnnotCont</a>(<a title=\"The field a of the annotatation is compulsory.\" class=\"e\">@</a><a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"43\">MyAnnot</a>)\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"43\">pkg.MyAnnot</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"63\">a</a>();\n" +
                "}\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"90\">pkg.MyAnnotCont</a> {\n" +
                " <a title=\"pkg.MyAnnot\" href=\"#"+ExportCst.PREF_REF+"43\">MyAnnot</a> <a name=\""+ExportCst.PREF_REF+"117\">b</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report360Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $Fct<$int,$int> f=$null;\n");
        xml_.append("  f.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $Fct"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"47\">f</a>=$null;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"47\">f</a>.<b>call</b>(<a title=\"The number of required arguments 1 is different from the number of supplied arguments 0 for the method of the elliptic type java.lang.$Fct\" class=\"e\">)</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report361Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $Fct<$int,$int> f=$null;\n");
        xml_.append("  f.call(1,2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $Fct"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"47\">f</a>=$null;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"47\">f</a>.<b>call</b>(1<a title=\"The number of required arguments 1 is different from the number of supplied arguments 2 for the method of the elliptic type java.lang.$Fct\" class=\"e\">,</a>2);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report362Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $Fct<$int,$int,$int,$int> f=$null;\n");
        xml_.append("  f.call(1,2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $Fct"+MessagesCdmBase.LT+"$int,$int,$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"57\">f</a>=$null;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"57\">f</a>.<b>call</b>(1,2<a title=\"The number of required arguments 3 is different from the number of supplied arguments 2 for the method of the elliptic type java.lang.$Fct\" class=\"e\">)</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report363Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $Fct<$int,$int> f=$null;\n");
        xml_.append("  f.call(\"1\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $Fct"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"47\">f</a>=$null;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"47\">f</a>.<b>call</b><a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">(</a><span class=\"s\">\"1\"</span>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report364Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $Fct<$int,$int> f=$null;\n");
        xml_.append("  f.callee(1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $Fct"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"47\">f</a>=$null;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"47\">f</a>.<a title=\"Only the method call can be used for the elliptic type java.lang.$Fct\" class=\"e\">callee</a>(1);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report365Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $Fct<$int,$int,$int> f=$null;\n");
        xml_.append("  f.call(1,\"1\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $Fct"+MessagesCdmBase.LT+"$int,$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"52\">f</a>=$null;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"52\">f</a>.<b>call</b>(1<a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">,</a><span class=\"s\">\"1\"</span>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report366Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $Fct<$int,$int> f=$null;\n");
        xml_.append("  f.call(1,2,3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $Fct"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"47\">f</a>=$null;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"47\">f</a>.<b>call</b>(1<a title=\"The number of required arguments 1 is different from the number of supplied arguments 3 for the method of the elliptic type java.lang.$Fct\" class=\"e\">,</a>2,3);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report367Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $Fct<$int> f=$null;\n");
        xml_.append("  f.call(1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $Fct"+MessagesCdmBase.LT+"$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"42\">f</a>=$null;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"42\">f</a>.<b>call</b><a title=\"The number of required arguments 0 is different from the number of supplied arguments 1 for the method of the elliptic type java.lang.$Fct\" class=\"e\">(</a>1);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report368Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $new $int[1.0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $new $int<a title=\"The type $double is unexpected.\" class=\"e\">[</a>1.0];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report369Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $new $int[]{1.0};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $new $int[]<a title=\"The type $double cannot be implicitly cast to $int\" class=\"e\">{</a>1.0};\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report370Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $new $int{1.0};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $new $int<a title=\"The type $int is unexpected.\" class=\"e\">{</a>1.0};\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report371Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $(){1};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $(<a title=\"There must be a type.\" class=\"e\">)</a><a title=\"The type  is unexpected.\" class=\"e\">{</a>1};\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report372Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  Object[] v = {{1}};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  Object[] <a name=\""+ExportCst.PREF_REF+"40\">v</a> = {<a title=\"The type java.lang.Object is unexpected.\" class=\"e\">{</a>1}};\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report373Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $int v = {1};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $int <a name=\""+ExportCst.PREF_REF+"36\">v</a> <a title=\"The type [java.lang.Object cannot be implicitly cast to $int\" class=\"e\">=</a> <a title=\"The type $int is unexpected.\" class=\"e\">{</a>1};\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report374Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $int[] v = {\"1\"};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $int[] <a name=\""+ExportCst.PREF_REF+"38\">v</a> = <a title=\"The type java.lang.String cannot be implicitly cast to $int\" class=\"e\">{</a><span class=\"s\">\"1\"</span>};\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report375Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $new $int[1][1.0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $new $int[1]<a title=\"The type $double is unexpected.\" class=\"e\">[</a>1.0];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report376Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $new MyCl(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <a title=\"The constructor pkg.MyCl($int) is undefined.\" class=\"e\">$new</a> <a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"15\">MyCl</a>(0);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report377Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $this(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\">MyCl(</a>){\n" +
                "  <a title=\"The constructor pkg.MyCl($int) is undefined.\" class=\"e\">$this</a>(0);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report378Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $super(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\">MyCl(</a>){\n" +
                "  <a title=\"There is no super custom type to be called from this class or an enum (singleton or normal).\" class=\"e\">$super</a>(0);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report379Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MySup {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $super(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MySup\" href=\"#"+ExportCst.PREF_REF+"74\">MySup</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  <a title=\"The constructor pkg.MySup($int) is undefined.\" class=\"e\">$super</a>(0);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"74\">pkg.MySup</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report380Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MySup {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $interfaces()(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MySup\" href=\"#"+ExportCst.PREF_REF+"81\">MySup</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  <a title=\"The call of a constructor of interface must refer a super interface of the calling type.\" class=\"e\">$interfaces</a>(<a title=\"There must be a type.\" class=\"e\">)</a>(0);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"81\">pkg.MySup</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report381Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MySup {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $interfaces(MySup)(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MySup\" href=\"#"+ExportCst.PREF_REF+"86\">MySup</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  <a title=\"The call of a constructor of interface must refer a super interface of the calling type.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MySup\" href=\"#"+ExportCst.PREF_REF+"86\">MySup</a>)(0);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"86\">pkg.MySup</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report382Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MySup {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $interfaces(MyInt)(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MySup\" href=\"#"+ExportCst.PREF_REF+"86\">MySup</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  <a title=\"The call of a constructor of interface must refer a super interface of the calling type.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"119\">MyInt</a>)(0);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"86\">pkg.MySup</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"119\">pkg.MyInt</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report383Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyInt {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $interfaces(MyInt)(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"90\">MyInt</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  <a title=\"The constructor pkg.MyInt($int) is undefined.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"90\">MyInt</a>)(0);\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"90\">pkg.MyInt</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report384Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyInt {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  ($interfaces(MyInt)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"91\">MyInt</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  (<a title=\"The call of a constructor using implicitly the instance must be applied at the end of the instruction.\" class=\"e\">$interfaces</a>(MyInt)());\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"91\">pkg.MyInt</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report385Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyInt {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $int i;\n");
        xml_.append("  $interfaces(MyInt)();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"99\">MyInt</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  $int <a name=\""+ExportCst.PREF_REF+"48\">i</a>;\n" +
                "  <a title=\"A call of a constructor of interface must be applied only after a call of a constructor using implicitly the instance.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"99\">MyInt</a>)();\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"99\">pkg.MyInt</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report386Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl:MyInt {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $interfaces(MyInt)();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"93\">MyInt</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"37\" title=\"A constructor of the type pkg.MyInt must not be called in the constructor.\" class=\"e\">MyCl(</a>){\n" +
                "  <a title=\"The call of a constructor of interface must not applied in a constructor of interface.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"93\">MyInt</a>)();\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"93\">pkg.MyInt</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report387Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyInt:MyInt2 {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $interfaces(MyInt)();\n");
        xml_.append("  $interfaces(MyInt2)();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt:MyInt2 {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"121\">MyInt</a>:<a title=\"pkg.MyInt2\" href=\"#"+ExportCst.PREF_REF+"161\">MyInt2</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"40\" title=\"A constructor of the type pkg.MyInt must not be called in the constructor.\n" +
                "\n" +
                "A constructor of the type pkg.MyInt2 must not be called in the constructor.\" class=\"e\">MyCl(</a>){\n" +
                "  $interfaces(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"121\">MyInt</a>)();\n" +
                "  <a title=\"The call of a constructor of the interface pkg.MyInt cannot be applied before calling the constructor of the interface pkg.MyInt2.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyInt2\" href=\"#"+ExportCst.PREF_REF+"161\">MyInt2</a>)();\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"121\">pkg.MyInt</a>:<a title=\"pkg.MyInt2\" href=\"#"+ExportCst.PREF_REF+"161\">MyInt2</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"161\">pkg.MyInt2</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report388Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MySup {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  ($super());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MySup\" href=\"#"+ExportCst.PREF_REF+"75\">MySup</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  (<a title=\"The call of a constructor using implicitly the instance must be applied at the end of the instruction.\" class=\"e\">$super</a>());\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"75\">pkg.MySup</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report389Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $super(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"31\">MyCl(</a>){\n" +
                "  <a title=\"The super constructor can be called only from a class or an enum (singleton or normal).\" class=\"e\">$super</a>(0);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report390Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MySup {\n");
        xml_.append(" {\n");
        xml_.append("  $super();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MySup\" href=\"#"+ExportCst.PREF_REF+"67\">MySup</a> {\n" +
                " {\n" +
                "  <a title=\"The call of a constructor using implicitly the instance must be applied in a constructor.\" class=\"e\">$super</a>();\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"67\">pkg.MySup</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report391Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MySup {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $if($true);\n");
        xml_.append("  $super();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MySup\" href=\"#"+ExportCst.PREF_REF+"87\">MySup</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  $if($true);\n" +
                "  <a title=\"The call of a constructor of the type or the super class using implicitly the instance must be applied on the first line.\" class=\"e\">$super</a>();\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"87\">pkg.MySup</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report392Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $(MyInt)($interfaces(MyInt)(),1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"93\">MyInt</a>)(<a title=\"The call of a constructor using implicitly the instance must be applied at the end of the instruction.\" class=\"e\">$interfaces</a>(MyInt)()<a title=\"The number of required operands 1 is different from the number of supplied arguments 2.\" class=\"e\">,</a>1);\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"93\">pkg.MyInt</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report393Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $()(1,$interfaces(MyInt)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $(<a title=\"There must be a type.\" class=\"e\">)</a>(1<a title=\"The number of required operands 1 is different from the number of supplied arguments 2.\" class=\"e\">,</a><a title=\"The call of a constructor of interface must refer a super interface of the calling type.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"88\">MyInt</a>)());\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"88\">pkg.MyInt</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report394Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $(MyOther)(1,$interfaces(MyInt)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyOther {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $(<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"124\">MyOther</a>)(1<a title=\"The number of required operands 1 is different from the number of supplied arguments 2.\" class=\"e\">,</a><a title=\"The call of a constructor of interface must refer a super interface of the calling type.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"95\">MyInt</a>)());\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"95\">pkg.MyInt</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"124\">pkg.MyOther</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report395Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $(MyInt)(1,$interfaces(MyOther)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyOther {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"95\">MyInt</a>)(1,<a title=\"The call of a constructor of interface must refer a super interface of the calling type.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"124\">MyOther</a>)());\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"95\">pkg.MyInt</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"124\">pkg.MyOther</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report396Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $(MyInt)(1,$interfaces(MyOther)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyOther {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"95\">MyInt</a>)(1,<a title=\"The call of a constructor of interface must refer a super interface of the calling type.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"128\">MyOther</a>)());\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"95\">pkg.MyInt</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"128\">pkg.MyOther</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report397Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $(MyInt<?>)(1,$interfaces(MyOther)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyOther {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"98\">MyInt</a>"+MessagesCdmBase.LT+"?"+MessagesCdmBase.GT+")(1,<a title=\"The call of a constructor of interface must refer a super interface of the calling type.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"134\">MyOther</a>)());\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"98\">pkg.MyInt</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"108\">T</a>"+MessagesCdmBase.GT+" {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"134\">pkg.MyOther</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report398Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $(MyInt)(1,$interfaces(MyOther)(0));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt:MyOther {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyOther {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"96\">MyInt</a>)(1,<a title=\"The constructor pkg.MyOther($int) is undefined.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"137\">MyOther</a>)(0));\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"96\">pkg.MyInt</a>:<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"137\">MyOther</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"137\">pkg.MyOther</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report399Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $(MyInt)(1,$interfaces(MyOther)(),$interfaces(MyOtherTwo)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt:MyOther {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyOther:MyOtherTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyOtherTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"121\">MyInt</a>)(1,$interfaces(<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"162\">MyOther</a>)(),<a title=\"The call of a constructor of the interface pkg.MyOther cannot be applied before calling the constructor of the interface pkg.MyOtherTwo.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyOtherTwo\" href=\"#"+ExportCst.PREF_REF+"208\">MyOtherTwo</a>)()<a title=\"The number of required operands 1 is different from the number of supplied arguments 3.\" class=\"e\">)</a>;\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"121\">pkg.MyInt</a>:<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"162\">MyOther</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"162\">pkg.MyOther</a>:<a title=\"pkg.MyOtherTwo\" href=\"#"+ExportCst.PREF_REF+"208\">MyOtherTwo</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"208\">pkg.MyOtherTwo</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report400Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $(MyInt)(1,$interfaces(MyOther)(),1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt:MyOther {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyOther {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"97\">MyInt</a>)(1,$interfaces(<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"138\">MyOther</a>)()<a title=\"The number of required operands 1 is different from the number of supplied arguments 3.\" class=\"e\">,</a>1);\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"97\">pkg.MyInt</a>:<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"138\">MyOther</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"138\">pkg.MyOther</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report401Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $(MyInt)(1,$interfaces(MyOther)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt:MyOther {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyOther:MyOtherTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyOtherTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"95\">MyInt</a>)(1,$interfaces(<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"136\">MyOther</a>)()<a title=\"The number of required operands 1 is different from the number of supplied arguments 2.\" class=\"e\">)</a>;\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"95\">pkg.MyInt</a>:<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"136\">MyOther</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"136\">pkg.MyOther</a>:<a title=\"pkg.MyOtherTwo\" href=\"#"+ExportCst.PREF_REF+"182\">MyOtherTwo</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"182\">pkg.MyOtherTwo</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report402Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  (1,$interfaces(MyOther)());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt:MyOther {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyOther:MyOtherTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyOtherTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  (1<a title=\"The number of required operands 1 is different from the number of supplied arguments 2.\" class=\"e\">,</a><a title=\"The call of a constructor of interface must refer a super interface of the calling type.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"128\">MyOther</a>)());\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"87\">pkg.MyInt</a>:<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"128\">MyOther</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"128\">pkg.MyOther</a>:<a title=\"pkg.MyOtherTwo\" href=\"#"+ExportCst.PREF_REF+"174\">MyOtherTwo</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"174\">pkg.MyOtherTwo</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report403Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MySup {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $this(0);\n");
        xml_.append("  $super();\n");
        xml_.append(" }\n");
        xml_.append(" MyCl($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MySup\" href=\"#"+ExportCst.PREF_REF+"103\">MySup</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  <a title=\"pkg.MyCl.pkg.MyCl($int)\" href=\"#"+ExportCst.PREF_REF+"69\">$this</a>(0);\n" +
                "  <a title=\"The call of a constructor of the type or the super class using implicitly the instance must be applied on the first line.\" class=\"e\">$super</a>();\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"69\">MyCl(</a>$int <a name=\""+ExportCst.PREF_REF+"79\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"103\">pkg.MySup</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report404Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyInt {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $this(0);\n");
        xml_.append("  $interfaces(MyInt)();\n");
        xml_.append(" }\n");
        xml_.append(" MyCl($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"148\">MyInt</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  <a title=\"pkg.MyCl.pkg.MyCl($int)\" href=\"#"+ExportCst.PREF_REF+"81\">$this</a>(0);\n" +
                "  <a title=\"A call of a constructor of interface must be applied only after a call of a constructor using implicitly the instance.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"148\">MyInt</a>)();\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"81\">MyCl(</a>$int <a name=\""+ExportCst.PREF_REF+"91\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"115\">pkg.MySup</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"148\">pkg.MyInt</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report405Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyInt {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $int i;\n");
        xml_.append("  $interfaces(MyInt)();\n");
        xml_.append(" }\n");
        xml_.append(" MyCl($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"146\">MyInt</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  $int <a name=\""+ExportCst.PREF_REF+"48\">i</a>;\n" +
                "  <a title=\"A call of a constructor of interface must be applied only after a call of a constructor using implicitly the instance.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"146\">MyInt</a>)();\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"79\">MyCl(</a>$int <a name=\""+ExportCst.PREF_REF+"89\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"113\">pkg.MySup</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"146\">pkg.MyInt</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report406Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyInt {\n");
        xml_.append(" MyCl(){\n");
        xml_.append("  $if($true);\n");
        xml_.append("  $interfaces(MyInt)();\n");
        xml_.append(" }\n");
        xml_.append(" MyCl($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySup {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"150\">MyInt</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\">MyCl(</a>){\n" +
                "  $if($true);\n" +
                "  <a title=\"A call of a constructor of interface must be applied only after a call of a constructor using implicitly the instance.\" class=\"e\">$interfaces</a>(<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"150\">MyInt</a>)();\n" +
                " }\n" +
                " <a name=\""+ExportCst.PREF_REF+"83\">MyCl(</a>$int <a name=\""+ExportCst.PREF_REF+"93\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"117\">pkg.MySup</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"150\">pkg.MyInt</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report407Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $new $int();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <a title=\"The type $int is not resolved for instancing.\" class=\"e\">$new</a> $int();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report408Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $new MyCl();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $abstract $class <a name=\""+ExportCst.PREF_REF+"25\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <a title=\"The type pkg.MyCl cannot be instantiated because of abstract.\" class=\"e\">$new</a> <a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"25\">MyCl</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report409Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyCl {;\n");
        xml_.append(" {\n");
        xml_.append("  $new MyCl();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyCl</a> {;\n" +
                " {\n" +
                "  <a title=\"A constructor of a enum cannot be called explicitly.\" class=\"e\">$new</a> <a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"14\">MyCl</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report410Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl<T> {\n");
        xml_.append(" {\n");
        xml_.append("  $new MyCl<?>();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"24\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " {\n" +
                "  <a title=\"The argument ? of the type pkg.MyCl"+MessagesCdmBase.LT+"?"+MessagesCdmBase.GT+" is bound. It cannot be used in constructor call.\" class=\"e\">$new</a> <a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"15\">MyCl</a>"+MessagesCdmBase.LT+"?"+MessagesCdmBase.GT+"();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report411Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $new MyClTwo.Inner();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyClTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <a title=\"The type pkg.MyCl cannot be implicitly cast to pkg.MyClTwo\" class=\"e\">$new</a> <a title=\"pkg.MyClTwo\" href=\"#"+ExportCst.PREF_REF+"73\">MyClTwo</a>.<a title=\"pkg.MyClTwo..Inner\" href=\"#"+ExportCst.PREF_REF+"103\">Inner</a>();\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"73\">pkg.MyClTwo</a> {\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"103\">Inner</a> {\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report412Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" $static {\n");
        xml_.append("  $new MyClTwo.Inner();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyClTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " $static {\n" +
                "  <a title=\"The type pkg.MyClTwo..Inner is not resolved for instancing.\" class=\"e\">$new</a> <a title=\"pkg.MyClTwo\" href=\"#"+ExportCst.PREF_REF+"81\">MyClTwo</a>.<a title=\"pkg.MyClTwo..Inner\" href=\"#"+ExportCst.PREF_REF+"111\">Inner</a>();\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"81\">pkg.MyClTwo</a> {\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"111\">Inner</a> {\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report413Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $new Inexist();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <a title=\"The type Inexist is not resolved for instancing.\" class=\"e\">$new</a> <a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report414Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyCl<T> {\n");
        xml_.append(" ONE;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"23\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a name=\""+ExportCst.PREF_REF+"29\" title=\"The type pkg.MyCl is not parameterized correctly.\" class=\"e\">ONE</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report414_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyCl<T> {\n");
        xml_.append(" ONE{};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"23\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a name=\""+ExportCst.PREF_REF+"29\" title=\"The type pkg.MyCl is not parameterized correctly.\" class=\"e\">ONE</a>{};\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report415Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyCl<T> {\n");
        xml_.append(" ONE{};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"23\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " <a name=\""+ExportCst.PREF_REF+"29\" title=\"The type pkg.MyCl is not parameterized correctly.\" class=\"e\">ONE</a>{};\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report416Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $new MyCl().$new Inner();\n");
        xml_.append(" }\n");
        xml_.append(" $public $class Inner<T>{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $new <a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"15\">MyCl</a>().<a title=\"The type pkg.MyCl..Inner is not parameterized correctly.\n" +
                "\n" +
                "The type Inner is not resolved for instancing.\" class=\"e\">$new</a> <a title=\"pkg.MyCl..Inner\" href=\"#"+ExportCst.PREF_REF+"76\">Inner</a>();\n" +
                " }\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"76\">Inner</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"82\">T</a>"+MessagesCdmBase.GT+"{\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report417Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $new MyCl().$new Inexist();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $new <a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"15\">MyCl</a>().<a title=\"The owner for the type Inexist is not resolved for instancing.\" class=\"e\">$new</a> Inexist();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report418Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl<T> {\n");
        xml_.append(" {\n");
        xml_.append("  MyCl<?> v = $new MyCl<Object>();\n");
        xml_.append("  v.$new Inexist();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"24\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " {\n" +
                "  <a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"15\">MyCl</a>"+MessagesCdmBase.LT+"?"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"42\">v</a> = $new <a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"15\">MyCl</a>"+MessagesCdmBase.LT+"Object"+MessagesCdmBase.GT+"();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"42\">v</a>.<a title=\"The argument ? of the type pkg.MyCl"+MessagesCdmBase.LT+"?"+MessagesCdmBase.GT+" is bound. It cannot be used in constructor call.\" class=\"e\">$new</a> Inexist();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report419Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  MyCl[] v = $new MyCl[0];\n");
        xml_.append("  v.$new Inexist();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"15\">MyCl</a>[] <a name=\""+ExportCst.PREF_REF+"38\">v</a> = $new <a title=\"pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"15\">MyCl</a>[0];\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"38\">v</a>.<a title=\"The type Inexist is not resolved for instancing.\" class=\"e\">$new</a> Inexist();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report420Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $this.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $this.<a title=\"There is no accessible field named v from the type pkg.MyCl in this context.\" class=\"e\">v</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report421Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $this.$superaccess(MyOther)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyOther {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $this.$superaccess(<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"81\">MyOther</a>)<a title=\"The type pkg.MyCl cannot be implicitly cast to pkg.MyOther\" class=\"e\">v</a>;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"81\">pkg.MyOther</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report422Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  v=0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyOther {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <a title=\"There is no accessible field named v from the type pkg.MyCl in this context.\" class=\"e\">v</a>=0;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"56\">pkg.MyOther</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report423Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyCl {\n");
        xml_.append(" ONE(1){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyCl</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"26\" title=\"The constructor pkg.MyCl-ONE($int) is undefined.\" class=\"e\">ONE</a>(1){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report424Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyCl {\n");
        xml_.append(" ONE(1)\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyCl</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"26\" title=\"The constructor pkg.MyCl($int) is undefined.\" class=\"e\">ONE</a>(1)\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report425Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method($int #i,$int... j) {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>($int <a name=\""+ExportCst.PREF_REF+"54\" title=\"The parameter method name #i is not valid. It must be a word.\" class=\"e\">#i</a>,$int... <a name=\""+ExportCst.PREF_REF+"65\">j</a>) {\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report426Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method($int i,$int... #j) {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>($int <a name=\""+ExportCst.PREF_REF+"54\">i</a>,$int... <a name=\""+ExportCst.PREF_REF+"64\" title=\"The parameter method name #j is not valid. It must be a word.\" class=\"e\">#j</a>) {\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report427Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $iterableTable<$int,$int> it = $null;\n");
        xml_.append("  $for($int a, $int a:it){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $iterableTable"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"81\">it</a> = $null;\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"105\">a</a>, $int <a title=\"The variable name a is not valid. It must not be the name of an other variable of the scope.\" class=\"e\">a</a>:<a href=\"#"+ExportCst.PREF_REF+"81\">it</a>){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report428Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $iterableTable<$int,$int> it = $null;\n");
        xml_.append("  $for($int a, $int #b:it){$int j=a;}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $iterableTable"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"81\">it</a> = $null;\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"105\">a</a>, $int <a title=\"The variable name #b is not valid. It must be a word.\" class=\"e\">#b</a>:<a href=\"#"+ExportCst.PREF_REF+"81\">it</a>){$int <a name=\""+ExportCst.PREF_REF+"125\">j</a>=<a href=\"#"+ExportCst.PREF_REF+"105\">a</a>;}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report429Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $void method() {\n");
        xml_.append("  $iterableTable<$int,$int> it = $null;\n");
        xml_.append("  $for($int #a, $int b:it){$int j=b;}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"42\">method</a>() {\n" +
                "  $iterableTable"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"81\">it</a> = $null;\n" +
                "  $for($int <a title=\"The variable name #a is not valid. It must be a word.\" class=\"e\">#a</a>, $int <a name=\""+ExportCst.PREF_REF+"114\">b</a>:<a href=\"#"+ExportCst.PREF_REF+"81\">it</a>){$int <a name=\""+ExportCst.PREF_REF+"125\">j</a>=<a href=\"#"+ExportCst.PREF_REF+"114\">b</a>;}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report430Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $int i,;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $int <a name=\""+ExportCst.PREF_REF+"37\">i</a><a title=\"The expression part is empty.\" class=\"e\">,</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report431Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@MySub(i=)\n");
        xml_.append("$public $annotation pkg.MySub {\n");
        xml_.append(" $int i();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">@<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"31\">MySub</a>(<a title=\"The expression part is empty.\n" +
                "\n" +
                "pkg.MySub.i()\" href=\"#"+ExportCst.PREF_REF+"49\" class=\"e\">i</a><a title=\"The type java.lang.Object cannot be implicitly cast to $int\" class=\"e\">=</a>)\n" +
                "$public $annotation <a name=\""+ExportCst.PREF_REF+"31\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"49\">i</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report432Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int[]v;\n");
        xml_.append(" {\n");
        xml_.append("  v[1,];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int[]<a name=\""+ExportCst.PREF_REF+"34\">v</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.v\" href=\"#"+ExportCst.PREF_REF+"34\">v</a>[1<a title=\"The expression part is empty.\" class=\"e\">,</a>];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report433Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int[]v;\n");
        xml_.append(" {\n");
        xml_.append("  v[1,2,];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int[]<a name=\""+ExportCst.PREF_REF+"34\">v</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.v\" href=\"#"+ExportCst.PREF_REF+"34\">v</a>[1<a title=\"The number of required operands 1 is different from the number of supplied arguments 3 for the operator []\" class=\"e\">,</a>2<a title=\"The expression part is empty.\" class=\"e\">,</a>];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report434Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int[]v;\n");
        xml_.append(" {\n");
        xml_.append("  v[1,,];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int[]<a name=\""+ExportCst.PREF_REF+"34\">v</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.v\" href=\"#"+ExportCst.PREF_REF+"34\">v</a>[1<a title=\"The expression part is empty.\" class=\"e\">,</a><a title=\"The expression part is empty.\" class=\"e\">,</a>];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report435Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int i,,k;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">i</a><a title=\"The expression part is empty.\n" +
                "\n" +
                "No field could be retrieved.\" class=\"e\">,</a><a title=\"The expression part is empty.\n" +
                "\n" +
                "No field could be retrieved.\" class=\"e\">,</a><a name=\""+ExportCst.PREF_REF+"36\">k</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report436Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $static $final $int i,,k;\n");
        xml_.append("}\n");
        xml_.append("$public $class [$static pkg.MySub.i;] pkg.MySub2 {\n");
        xml_.append(" {\n");
        xml_.append("  (i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $static $final $int <a name=\""+ExportCst.PREF_REF+"56\">i</a><a title=\"The expression part is empty.\n" +
                "\n" +
                "No field could be retrieved.\" class=\"e\">,</a><a title=\"The expression part is empty.\n" +
                "\n" +
                "No field could be retrieved.\" class=\"e\">,</a><a name=\""+ExportCst.PREF_REF+"59\">k</a>;\n" +
                "}\n" +
                "$public $class [<span class=\"i\">$static pkg.MySub.i</span>;] <a name=\""+ExportCst.PREF_REF+"102\">pkg.MySub2</a> {\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"56\">i</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report437Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int[] a;\n");
        xml_.append(" $int i,a[0],k;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"35\">a</a>;\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"44\">i</a>,<a title=\"pkg.MySub.a\" href=\"#"+ExportCst.PREF_REF+"35\">a</a><a title=\"No field could be retrieved.\" class=\"e\">[</a>0],<a name=\""+ExportCst.PREF_REF+"51\">k</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report438Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int[] a;\n");
        xml_.append(" $int i,a[0]=1,k;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"35\">a</a>;\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"44\">i</a>,<a title=\"pkg.MySub.a\" href=\"#"+ExportCst.PREF_REF+"35\">a</a><a title=\"No field could be retrieved.\" class=\"e\">[</a>0]=1,<a name=\""+ExportCst.PREF_REF+"53\">k</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report439Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int[] a;\n");
        xml_.append(" $int a[0]=1;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int[] <a name=\""+ExportCst.PREF_REF+"35\">a</a>;\n" +
                " $int <a title=\"No field could be retrieved.\" class=\"e\">a[0]=1</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report440Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (*);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">*</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report441Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub v;\n");
        xml_.append(" {\n");
        xml_.append("  (v*v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">v</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.v\" href=\"#"+ExportCst.PREF_REF+"34\">v</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator * are unexpected.\" class=\"e\">*</a><a title=\"pkg.MySub.v\" href=\"#"+ExportCst.PREF_REF+"34\">v</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report442Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (.e);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">.</a><a title=\"There is no accessible field named e from the type java.lang.Object in this context.\" class=\"e\">e</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report443Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int i;\n");
        xml_.append(" $int i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">i</a>;\n" +
                " $int <a title=\"the field name i is duplicated.\" class=\"e\">i</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report444Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int i;\n");
        xml_.append(" $int i;\n");
        xml_.append(" {\n");
        xml_.append("  i++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">i</a>;\n" +
                " $int <a title=\"the field name i is duplicated.\" class=\"e\">i</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"33\">i</a>++;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report445Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int i,i;\n");
        xml_.append(" {\n");
        xml_.append("  i++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">i</a>,<a title=\"the field name i is duplicated.\" class=\"e\">i</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"33\">i</a>++;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report446Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int i,i,j;\n");
        xml_.append(" {\n");
        xml_.append("  j++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">i</a>,<a title=\"the field name i is duplicated.\" class=\"e\">i</a>,<a name=\""+ExportCst.PREF_REF+"37\">j</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.j\" href=\"#"+ExportCst.PREF_REF+"37\">j</a>++;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report447Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  i[1,];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this(Object o, Object p){\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this(Object o, Object p){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">i</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a><a title=\"pkg.MySub.[](java.lang.Object,java.lang.Object)\" href=\"#"+ExportCst.PREF_REF+"111\">[</a>1<a title=\"The expression part is empty.\" class=\"e\">,</a><a title=\"pkg.MySub.[](java.lang.Object,java.lang.Object)\" href=\"#"+ExportCst.PREF_REF+"111\">]</a>;\n" +
                " }\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"67\">$this</a>(Object <a name=\""+ExportCst.PREF_REF+"80\">o</a>, Object <a name=\""+ExportCst.PREF_REF+"90\">p</a>){\n" +
                " }\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"111\">$this</a>(Object <a name=\""+ExportCst.PREF_REF+"124\">o</a>, Object <a name=\""+ExportCst.PREF_REF+"134\">p</a>){\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report448Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  (i<i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">i</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator "+MessagesCdmBase.LT+" are unexpected.\" class=\"e\">"+MessagesCdmBase.LT+"</a><a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report449Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  (i<=i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">i</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator "+MessagesCdmBase.LT+"= are unexpected.\" class=\"e\">"+MessagesCdmBase.LT+"=</a><a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report450Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  (i-=i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">i</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a><a title=\"The type pkg.MySub cannot be implicitly cast to pkg.MySub\" class=\"e\">-</a>=<a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report451Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  ($true&&i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">i</a>;\n" +
                " {\n" +
                "  ($true"+MessagesCdmBase.AMP+"<a title=\"The type pkg.MySub is unexpected.\" class=\"e\">"+MessagesCdmBase.AMP+"</a><a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report452Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  (i&&$true);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">i</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a><a title=\"The type pkg.MySub is unexpected.\" class=\"e\">"+MessagesCdmBase.AMP+"</a>"+MessagesCdmBase.AMP+"$true);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report453Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" MySub2 j;\n");
        xml_.append(" {\n");
        xml_.append("  (i&&j);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySub2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">i</a>;\n" +
                " <a title=\"pkg.MySub2\" href=\"#"+ExportCst.PREF_REF+"81\">MySub2</a> <a name=\""+ExportCst.PREF_REF+"45\">j</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a><a title=\"The type pkg.MySub is unexpected.\" class=\"e\">"+MessagesCdmBase.AMP+"</a><a title=\"The type pkg.MySub2 is unexpected.\" class=\"e\">"+MessagesCdmBase.AMP+"</a><a title=\"pkg.MySub.j\" href=\"#"+ExportCst.PREF_REF+"45\">j</a>);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"81\">pkg.MySub2</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report454Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (??);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">??</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report455Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  (i||);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySub2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">i</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a><a title=\"The expression part is empty.\" class=\"e\">||</a>);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"69\">pkg.MySub2</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report456Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  (||i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySub2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">i</a>;\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">||</a><a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a>);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"69\">pkg.MySub2</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report457Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  (||);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySub2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">i</a>;\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">||</a>);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"68\">pkg.MySub2</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report458Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  ( $lambda());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  ( $lambda(<a title=\"The number of required splitted parts by comas 2 is greater than the number of supplied splitted parts by comas 1.\" class=\"e\">)</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report459Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Int3 a;\n");
        xml_.append(" Int4 b;\n");
        xml_.append(" {\n");
        xml_.append("  ($true?a:b).$lambda($operator,Type);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int1 {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int2 {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int3:Int1:Int2 {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int4:Int1:Int2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.Int3\" href=\"#"+ExportCst.PREF_REF+"175\">Int3</a> <a name=\""+ExportCst.PREF_REF+"33\">a</a>;\n" +
                " <a title=\"pkg.Int4\" href=\"#"+ExportCst.PREF_REF+"217\">Int4</a> <a name=\""+ExportCst.PREF_REF+"42\">b</a>;\n" +
                " {\n" +
                "  ($true?<a title=\"pkg.MySub.a\" href=\"#"+ExportCst.PREF_REF+"33\">a</a>:<a title=\"pkg.MySub.b\" href=\"#"+ExportCst.PREF_REF+"42\">b</a>).<a title=\"The type pkg.Int1"+MessagesCdmBase.AMP+"pkg.Int2 is unexpected.\" class=\"e\">$lambda</a>($operator,Type);\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"111\">pkg.Int1</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"143\">pkg.Int2</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"175\">pkg.Int3</a>:<a title=\"pkg.Int1\" href=\"#"+ExportCst.PREF_REF+"111\">Int1</a>:<a title=\"pkg.Int2\" href=\"#"+ExportCst.PREF_REF+"143\">Int2</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"217\">pkg.Int4</a>:<a title=\"pkg.Int1\" href=\"#"+ExportCst.PREF_REF+"111\">Int1</a>:<a title=\"pkg.Int2\" href=\"#"+ExportCst.PREF_REF+"143\">Int2</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report460Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int i,1,k;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">i</a>,<a title=\"The field name 1 is not valid. It must not start with a digit.\" class=\"e\">1</a>,<a name=\""+ExportCst.PREF_REF+"37\">k</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report461Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int i,=1,k;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">i</a>,<a title=\"The expression part is empty.\n" +
                "\n" +
                "No field could be retrieved.\" class=\"e\">=</a>1,<a name=\""+ExportCst.PREF_REF+"38\">k</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report462Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int i,1=1,k;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">i</a>,<a title=\"The field name 1 is not valid. It must not start with a digit.\" class=\"e\">1</a>=1,<a name=\""+ExportCst.PREF_REF+"39\">k</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report463Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object i,l=1e,k;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">i</a>,<a name=\""+ExportCst.PREF_REF+"37\">l</a>=<a title=\"Bad number 1e\" class=\"e\">1e</a>,<a name=\""+ExportCst.PREF_REF+"42\">k</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report464Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object i,l= 1e,k;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">i</a>,<a name=\""+ExportCst.PREF_REF+"37\">l</a>= <a title=\"Bad number 1e\" class=\"e\">1e</a>,<a name=\""+ExportCst.PREF_REF+"43\">k</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report465Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" Object i,l=1ee1 ,k;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " Object <a name=\""+ExportCst.PREF_REF+"35\">i</a>,<a name=\""+ExportCst.PREF_REF+"37\">l</a>=<a title=\"Bad number 1ee1\" class=\"e\">1ee1</a> ,<a name=\""+ExportCst.PREF_REF+"45\">k</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report466Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator<a title=\"There must be a type.\" class=\"e\">,</a><a title=\"The operator symbol  is not valid.\" class=\"e\">)</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report467Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,MySub,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a><a title=\"The operator symbol  is not valid.\" class=\"e\">,</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report468Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,+,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"The function $static (java.lang.Object) is undefined.\" class=\"e\">+</a><a title=\"There must be a type.\" class=\"e\">,</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report469Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,MySub,+,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,<a title=\"The function $static (java.lang.Object) is undefined.\" class=\"e\">+</a><a title=\"There must be a type.\" class=\"e\">,</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report470Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,+,$id,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"The function $static (java.lang.Object) is undefined.\" class=\"e\">+</a>,$id<a title=\"There must be a type.\" class=\"e\">,</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report471Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,MySub,+,$id,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,<a title=\"The function $static (java.lang.Object) is undefined.\" class=\"e\">+</a>,$id<a title=\"There must be a type.\" class=\"e\">,</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report472Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,+,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"The function $static (pkg.MySub) is undefined.\" class=\"e\">+</a>,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report473Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,MySub,+,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,<a title=\"The function $static (pkg.MySub) is undefined.\" class=\"e\">+</a>,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report474Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,+,$id,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"The function $static (pkg.MySub) is undefined.\" class=\"e\">+</a>,$id,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report475Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,MySub,+,$id,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,<a title=\"The function $static (pkg.MySub) is undefined.\" class=\"e\">+</a>,$id,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report476Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda(MySub,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The number of required splitted parts by comas 3 is greater than the number of supplied splitted parts by comas 2.\" class=\"e\">$lambda</a>(MySub,);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report477Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda(MySub,,field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,,<a title=\"There is no accessible field named field from the type pkg.MySub in this context.\" class=\"e\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report478Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda(MySub,,$super,field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,,$super,<a title=\"There is no accessible field named field from the type pkg.MySub in this context.\" class=\"e\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report479Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $static().$lambda(MySub,,field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $static().$lambda(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,,<a title=\"There is no accessible field named field from the type pkg.MySub in this context.\" class=\"e\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report480Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub m;\n");
        xml_.append(" {\n");
        xml_.append("  m.$lambda(MySub,,field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">m</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.m\" href=\"#"+ExportCst.PREF_REF+"34\">m</a>.$lambda(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,,<a title=\"There is no accessible field named field from the type pkg.MySub in this context.\" class=\"e\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report481Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub m;\n");
        xml_.append(" {\n");
        xml_.append("  m.$lambda(MySub,,$super,field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">m</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.m\" href=\"#"+ExportCst.PREF_REF+"34\">m</a>.$lambda(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,,$super,<a title=\"There is no accessible field named field from the type pkg.MySub in this context.\" class=\"e\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report482Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $static().$lambda(MySub,, field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $static().$lambda(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,, <a title=\"There is no accessible field named field from the type pkg.MySub in this context.\" class=\"e\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report483Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub m;\n");
        xml_.append(" {\n");
        xml_.append("  m.$lambda(MySub2,,$super,field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySub2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">m</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.m\" href=\"#"+ExportCst.PREF_REF+"34\">m</a>.<a title=\"The type pkg.MySub cannot be implicitly cast to pkg.MySub2\" class=\"e\">$lambda</a>(<a title=\"pkg.MySub2\" href=\"#"+ExportCst.PREF_REF+"95\">MySub2</a>,,$super,field);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"95\">pkg.MySub2</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report484Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda(,,);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda(<a title=\"There must be a type.\" class=\"e\">,</a>,<a title=\"There is no accessible field named  from the type java.lang.Object in this context.\" class=\"e\">)</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report485Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,MySub,+,$id,MySub...,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,+,$id,MySub<a title=\"The three dots are unexpected here.\" class=\"e\">...</a>,MySub);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report486Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda($operator,MySub,+,MySub...,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $lambda($operator,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,+,MySub<a title=\"The three dots are unexpected here.\" class=\"e\">...</a>,MySub);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report487Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda(MySub,explicit,MySub,MySub,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The number of required splitted parts by comas 2 is lower than the number of supplied splitted parts by comas 3.\" class=\"e\">$lambda</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,explicit,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report488Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda(MySub,method,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The function method(pkg.MySub) is undefined.\" class=\"e\">$lambda</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,method,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report489Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda(MySub,$that,method,MySub);\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $void method(MySub m);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $abstract $class <a name=\""+ExportCst.PREF_REF+"25\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The method method(pkg.MySub) from the type pkg.MySub must not be called directly because of abstract.\n" +
                "\n" +
                "pkg.MySub.method(pkg.MySub)\" href=\"#"+ExportCst.PREF_REF+"105\" class=\"e\">$lambda</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"25\">MySub</a>,$that,method,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"25\">MySub</a>);\n" +
                " }\n" +
                " $public $abstract $void <a name=\""+ExportCst.PREF_REF+"105\">method</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"25\">MySub</a> <a name=\""+ExportCst.PREF_REF+"118\">m</a>);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report490Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda(MySub,$new,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $abstract $class <a name=\""+ExportCst.PREF_REF+"25\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The type pkg.MySub cannot be instantiated because of abstract.\" class=\"e\">$lambda</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"25\">MySub</a>,$new,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"25\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report491Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $lambda(MySub,$new,MySub);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <a title=\"The constructor pkg.MySub(pkg.MySub) is undefined.\" class=\"e\">$lambda</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,$new,<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report492Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $final $int field;\n");
        xml_.append(" {\n");
        xml_.append("  $lambda(MySub,,field,$int);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $final $int <a name=\""+ExportCst.PREF_REF+"40\">field</a>;\n" +
                " {\n" +
                "  <a title=\"The field field is already assigned.\n" +
                "\n" +
                "pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"40\" class=\"e\">$lambda</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>,,field,$int);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report493Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (+);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">+</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report494Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (-);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">-</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report495Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (!);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">!</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report496Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (~);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">~</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report497Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (+field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"The operands types pkg.MySub for the operator + are unexpected.\" class=\"e\">+</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report498Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (-field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"The operands types pkg.MySub for the operator - are unexpected.\" class=\"e\">-</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report499Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (!field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"The operands types pkg.MySub for the operator ! are unexpected.\" class=\"e\">!</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report500Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (~field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"The operands types pkg.MySub for the operator ~ are unexpected.\" class=\"e\">~</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report501Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (field+field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator + are unexpected.\" class=\"e\">+</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report502Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (field-field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator - are unexpected.\" class=\"e\">-</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report503Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (field&field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator "+MessagesCdmBase.AMP+" are unexpected.\" class=\"e\">"+MessagesCdmBase.AMP+"</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report504Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (field|field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator | are unexpected.\" class=\"e\">|</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report505Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (field^field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator ^ are unexpected.\" class=\"e\">^</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report506Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (field<<field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator "+MessagesCdmBase.LT+""+MessagesCdmBase.LT+" are unexpected.\" class=\"e\">"+MessagesCdmBase.LT+""+MessagesCdmBase.LT+"</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report507Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (field>>field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator "+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" are unexpected.\" class=\"e\">"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+"</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report508Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (field<<<field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator "+MessagesCdmBase.LT+""+MessagesCdmBase.LT+""+MessagesCdmBase.LT+" are unexpected.\" class=\"e\">"+MessagesCdmBase.LT+""+MessagesCdmBase.LT+""+MessagesCdmBase.LT+"</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report509Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (field>>>field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator "+MessagesCdmBase.GT+""+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" are unexpected.\" class=\"e\">"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+""+MessagesCdmBase.GT+"</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report510Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (field<<<<field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator "+MessagesCdmBase.LT+""+MessagesCdmBase.LT+""+MessagesCdmBase.LT+""+MessagesCdmBase.LT+" are unexpected.\" class=\"e\">"+MessagesCdmBase.LT+""+MessagesCdmBase.LT+""+MessagesCdmBase.LT+""+MessagesCdmBase.LT+"</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report511Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub field;\n");
        xml_.append(" {\n");
        xml_.append("  (field>>>>field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">field</a>;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a><a title=\"The operands types pkg.MySub;pkg.MySub for the operator "+MessagesCdmBase.GT+""+MessagesCdmBase.GT+""+MessagesCdmBase.GT+""+MessagesCdmBase.GT+" are unexpected.\" class=\"e\">"+MessagesCdmBase.GT+""+MessagesCdmBase.GT+""+MessagesCdmBase.GT+""+MessagesCdmBase.GT+"</a><a title=\"pkg.MySub.field\" href=\"#"+ExportCst.PREF_REF+"34\">field</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report512Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final $int i=0;\n");
        xml_.append(" $static {\n");
        xml_.append("  i++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final $int <a name=\""+ExportCst.PREF_REF+"48\">i</a>=0;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"48\">i</a><a title=\"The field i is already assigned.\" class=\"e\">++</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report513Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static {\n");
        xml_.append("  1++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static {\n" +
                "  1<a title=\"The assignment operator ++ is unexpected.\" class=\"e\">++</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report514Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final $int i=0;\n");
        xml_.append(" $static {\n");
        xml_.append("  i+=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final $int <a name=\""+ExportCst.PREF_REF+"48\">i</a>=0;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"48\">i</a>+<a title=\"The field i is already assigned.\" class=\"e\">=</a>1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report515Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub i;\n");
        xml_.append(" {\n");
        xml_.append("  i+=1;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ $int(MySub m, $int n){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">i</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"34\">i</a><a title=\"The type $int cannot be implicitly cast to pkg.MySub\n" +
                "\n" +
                "pkg.MySub.$static +(pkg.MySub,$int)\" href=\"#"+ExportCst.PREF_REF+"61\" class=\"e\">+</a><a title=\"The type $int cannot be implicitly cast to pkg.MySub\" class=\"e\">=</a>1;\n" +
                " }\n" +
                " $operator<a name=\""+ExportCst.PREF_REF+"61\">+</a> $int(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"74\">m</a>, $int <a name=\""+ExportCst.PREF_REF+"82\">n</a>){\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report516Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final MySub i = $null;\n");
        xml_.append(" $static {\n");
        xml_.append("  i+=1;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ $int(MySub m, $int n){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"49\">i</a> = $null;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"49\">i</a><a title=\"The field i is already assigned.\n" +
                "\n" +
                "The type $int cannot be implicitly cast to pkg.MySub\n" +
                "\n" +
                "pkg.MySub.$static +(pkg.MySub,$int)\" href=\"#"+ExportCst.PREF_REF+"92\" class=\"e\">+</a><a title=\"The field i is already assigned.\n" +
                "\n" +
                "The type $int cannot be implicitly cast to pkg.MySub\" class=\"e\">=</a>1;\n" +
                " }\n" +
                " $operator<a name=\""+ExportCst.PREF_REF+"92\">+</a> $int(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"105\">m</a>, $int <a name=\""+ExportCst.PREF_REF+"113\">n</a>){\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report517Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final MySub i = $null;\n");
        xml_.append(" {\n");
        xml_.append("  (i-=i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"49\">i</a> = $null;\n" +
                " {\n" +
                "  (<a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"49\">i</a><a title=\"The type pkg.MySub cannot be implicitly cast to pkg.MySub\" class=\"e\">-</a><a title=\"The field i is already assigned.\" class=\"e\">=</a><a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"49\">i</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report518Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final $int i=0;\n");
        xml_.append(" $static {\n");
        xml_.append("  i-=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final $int <a name=\""+ExportCst.PREF_REF+"48\">i</a>=0;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"48\">i</a>-<a title=\"The field i is already assigned.\" class=\"e\">=</a>1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report519Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final String i=\"\";\n");
        xml_.append(" $static {\n");
        xml_.append("  i+=$new MySub();\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString(){\n");
        xml_.append("  $return \"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final String <a name=\""+ExportCst.PREF_REF+"50\">i</a>=<span class=\"s\">\"\"</span>;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"50\">i</a><i>+</i><a title=\"The field i is already assigned.\" class=\"e\">=</a>$new <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>();\n" +
                " }\n" +
                " $public String <a name=\""+ExportCst.PREF_REF+"105\">$toString</a>(){\n" +
                "  $return <span class=\"s\">\"\"</span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report520Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final MySub i=$null;\n");
        xml_.append(" $static {\n");
        xml_.append("  i+=$null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"49\">i</a>=$null;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"49\">i</a><a title=\"The type  cannot be implicitly cast to pkg.MySub\" class=\"e\">+</a><a title=\"The field i is already assigned.\" class=\"e\">=</a>$null;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report521Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final $int i=0;\n");
        xml_.append(" $static {\n");
        xml_.append("  i+=(MySub)$null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final $int <a name=\""+ExportCst.PREF_REF+"48\">i</a>=0;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"48\">i</a><a title=\"The type pkg.MySub cannot be implicitly cast to $int\" class=\"e\">+</a><a title=\"The field i is already assigned.\" class=\"e\">=</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>)$null;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report522Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final $int i=0;\n");
        xml_.append(" $static $final $float f;\n");
        xml_.append(" $static {\n");
        xml_.append("  i+=f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final $int <a name=\""+ExportCst.PREF_REF+"48\">i</a>=0;\n" +
                " $static $final $float <a name=\""+ExportCst.PREF_REF+"76\">f</a>;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"48\">i</a><a title=\"The type $float cannot be implicitly cast to $int\" class=\"e\">+</a><a title=\"The field i is already assigned.\" class=\"e\">=</a><a title=\"pkg.MySub.f\" href=\"#"+ExportCst.PREF_REF+"76\">f</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report523Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final $int i=0;\n");
        xml_.append(" $static {\n");
        xml_.append("  i&=(MySub)$null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final $int <a name=\""+ExportCst.PREF_REF+"48\">i</a>=0;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"48\">i</a><a title=\"The type pkg.MySub cannot be implicitly cast to $int\" class=\"e\">"+MessagesCdmBase.AMP+"</a><a title=\"The field i is already assigned.\" class=\"e\">=</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>)$null;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report524Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final $int i=0;\n");
        xml_.append(" $static {\n");
        xml_.append("  i&&=(MySub)$null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final $int <a name=\""+ExportCst.PREF_REF+"48\">i</a>=0;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"48\">i</a><a title=\"The type pkg.MySub cannot be implicitly cast to $int\" class=\"e\">"+MessagesCdmBase.AMP+""+MessagesCdmBase.AMP+"</a><a title=\"The field i is already assigned.\" class=\"e\">=</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>)$null;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report525Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final $int i=0;\n");
        xml_.append(" $static {\n");
        xml_.append("  i??=(MySub)$null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final $int <a name=\""+ExportCst.PREF_REF+"48\">i</a>=0;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.i\" href=\"#"+ExportCst.PREF_REF+"48\">i</a><a title=\"The type pkg.MySub cannot be implicitly cast to $int\" class=\"e\">??</a><a title=\"The field i is already assigned.\" class=\"e\">=</a>(<a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a>)$null;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report526Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  1+=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  1<a title=\"The assignment operator += is unexpected.\" class=\"e\">+=</a>1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report527Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $int v;\n");
        xml_.append(" {\n");
        xml_.append("  v=$null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"33\">v</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.v\" href=\"#"+ExportCst.PREF_REF+"33\">v</a><a title=\"The type  cannot be implicitly cast to $int\" class=\"e\">=</a>$null;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report528Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MySub v;\n");
        xml_.append(" {\n");
        xml_.append("  v=0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"15\">MySub</a> <a name=\""+ExportCst.PREF_REF+"34\">v</a>;\n" +
                " {\n" +
                "  <a title=\"pkg.MySub.v\" href=\"#"+ExportCst.PREF_REF+"34\">v</a><a title=\"The type $int cannot be implicitly cast to pkg.MySub\" class=\"e\">=</a>0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report529Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static $final $int v=0;\n");
        xml_.append(" $static {\n");
        xml_.append("  v=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static $final $int <a name=\""+ExportCst.PREF_REF+"48\">v</a>=0;\n" +
                " $static {\n" +
                "  <a title=\"pkg.MySub.v\" href=\"#"+ExportCst.PREF_REF+"48\">v</a><a title=\"The field v is already assigned.\" class=\"e\">=</a>1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report530Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $static {\n");
        xml_.append("  1=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $static {\n" +
                "  1<a title=\"The assignment operator = is unexpected.\" class=\"e\">=</a>1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report531Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (1!1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (1<a title=\"The string ! is not an operator reference.\" class=\"e\">!</a>1);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report532Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (1<1<3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (1"+MessagesCdmBase.LT+"1<a title=\"The number of required operands 2 is different from the number of supplied arguments 3 for the operator "+MessagesCdmBase.LT+"\" class=\"e\">"+MessagesCdmBase.LT+"</a>3);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report533Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (.);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">.</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report534Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (< <)>=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a> <a title=\"The expression part is empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a>)<a title=\"The operands types $boolean;$int for the operator "+MessagesCdmBase.GT+"= are unexpected.\" class=\"e\">"+MessagesCdmBase.GT+"=</a>1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report535Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (< < )>=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a> <a title=\"The expression part is empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a> )<a title=\"The operands types $boolean;$int for the operator "+MessagesCdmBase.GT+"= are unexpected.\" class=\"e\">"+MessagesCdmBase.GT+"=</a>1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report536Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (< <2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a> <a title=\"The expression part is empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a>2);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report537Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (1< <2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (1<a title=\"The expression part is empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a> <a title=\"The expression part is empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a>2);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report538Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  '';\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  <span class=\"s\"><a title=\"Bad character format ''\" class=\"e\">''</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report539Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub:MySub2 {\n");
        xml_.append(" $int a,$super.f,b;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MySub2 {\n");
        xml_.append(" $int f;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a>:<a title=\"pkg.MySub2\" href=\"#"+ExportCst.PREF_REF+"71\">MySub2</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"40\">a</a>,$super.<a title=\"No field could be retrieved.\n" +
                "\n" +
                "pkg.MySub2.f\" href=\"#"+ExportCst.PREF_REF+"90\" class=\"e\">f</a>,<a name=\""+ExportCst.PREF_REF+"51\">b</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"71\">pkg.MySub2</a> {\n" +
                " $int <a name=\""+ExportCst.PREF_REF+"90\">f</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report540Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  ([a]);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  ([<a title=\"The variable a is undefined in this context.\" class=\"e\">a</a>]);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report541Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" ONE(1);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\" title=\"The constructor pkg.MyEnum($int) is undefined.\" class=\"e\">ONE</a>(1);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report542Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" ONE(1){};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\" title=\"The constructor pkg.MyEnum-ONE($int) is undefined.\" class=\"e\">ONE</a>(1){};\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report543Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" {\n");
        xml_.append("  $valueOf(MyEnum,\"\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " {\n" +
                "  <a title=\"The type pkg.MyEnum is unexpected.\" class=\"e\">$valueOf</a>(<a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"15\">MyEnum</a>,<span class=\"s\">\"\"</span>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report544Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {;\n");
        xml_.append(" {\n");
        xml_.append("  $valueOf(MyEnum,0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {;\n" +
                " {\n" +
                "  <a title=\"The type $int is unexpected.\" class=\"e\">$valueOf</a>(<a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"14\">MyEnum</a>,0);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report545Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" {\n");
        xml_.append("  $values(MyEnum);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " {\n" +
                "  <a title=\"The type pkg.MyEnum is unexpected.\" class=\"e\">$values</a>(<a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"15\">MyEnum</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report546Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {;\n");
        xml_.append(" {\n");
        xml_.append("  $firstopt(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {;\n" +
                " {\n" +
                "  <a title=\"The key word $firstopt is unexpected here.\" class=\"e\">$firstopt</a>(0);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report547Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum<T> {\n");
        xml_.append(" {\n");
        xml_.append("  $staticCall(MyEnum<?>).m();\n");
        xml_.append(" }\n");
        xml_.append(" $staticCall $void n(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"26\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " {\n" +
                "  <a title=\"The type pkg.MyEnum"+MessagesCdmBase.LT+"?"+MessagesCdmBase.GT+" is unexpected.\" class=\"e\">$staticCall</a>(<a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"15\">MyEnum</a>"+MessagesCdmBase.LT+"?"+MessagesCdmBase.GT+").<a title=\"The function $staticCall m() is undefined.\" class=\"e\">m</a>();\n" +
                " }\n" +
                " $staticCall $void <a name=\""+ExportCst.PREF_REF+"86\">n</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report548Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum<T> {\n");
        xml_.append(" {\n");
        xml_.append("  $static(pkgtwo.MyOther).m();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$class pkgtwo.MyOther {\n");
        xml_.append(" $static $void m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"26\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " {\n" +
                "  <a title=\"The type pkgtwo.MyOther is not accessible from the type pkg.MyEnum.\" class=\"e\">$static</a>(<a title=\"pkgtwo.MyOther\" href=\"#"+ExportCst.PREF_REF+"77\">pkgtwo.MyOther</a>).<a title=\"The function $static m() is undefined.\" class=\"e\">m</a>();\n" +
                " }\n" +
                "}\n" +
                "$class <a name=\""+ExportCst.PREF_REF+"77\">pkgtwo.MyOther</a> {\n" +
                " $static $void <a name=\""+ExportCst.PREF_REF+"109\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report549Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" {\n");
        xml_.append("  $vararg(MyEnum);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " {\n" +
                "  <a title=\"The key word $vararg is unexpected here.\" class=\"e\">$vararg</a>(MyEnum);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report550Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" $static {\n");
        xml_.append("  $super[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " $static {\n" +
                "  <a title=\"The context is static. The key word $super cannot be used in this context.\" class=\"e\">$super</a><a title=\"The type pkg.MyEnum is unexpected.\" class=\"e\">[</a>0];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report551Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" {\n");
        xml_.append("  m($id(MyEnum,$int...,$int));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " {\n" +
                "  <a title=\"The function m() is undefined.\" class=\"e\">m</a>($id(<a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"15\">MyEnum</a>,$int<a title=\"The three dots are unexpected here.\" class=\"e\">...</a>,$int));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report552Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" {\n");
        xml_.append("  $id(MyEnum,$int);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " {\n" +
                "  <a title=\"The key word $id is unexpected here.\" class=\"e\">$id</a>(MyEnum,$int);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report553Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" {\n");
        xml_.append("  m($id(Inexist));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " {\n" +
                "  <a title=\"The function m() is undefined.\" class=\"e\">m</a>($id(<a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a>));\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report554Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" {\n");
        xml_.append("  $operator(+,MyEnum)(1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " {\n" +
                "  <a title=\"The function $static +($int) is undefined.\" class=\"e\">$operator</a>(+,<a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"15\">MyEnum</a>)(1);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report555Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" {\n");
        xml_.append("  $operator(+,MyEnum,MyEnum)();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " {\n" +
                "  <a title=\"The number of required splitted parts by comas 2 is lower than the number of supplied splitted parts by comas 3.\" class=\"e\">$operator</a>(+,<a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"15\">MyEnum</a>,MyEnum)();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report556Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" $static {\n");
        xml_.append("  $this.m();\n");
        xml_.append(" }\n");
        xml_.append(" $void m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " $static {\n" +
                "  <a title=\"The context is static. The key word $this cannot be used in this context.\" class=\"e\">$this</a>.<a title=\"pkg.MyEnum.m()\" href=\"#"+ExportCst.PREF_REF+"62\">m</a>();\n" +
                " }\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"62\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report557Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" $static {\n");
        xml_.append("  MyEnum.$this.m();\n");
        xml_.append(" }\n");
        xml_.append(" $void m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " $static {\n" +
                "  <a title=\"pkg.MyEnum\" href=\"#"+ExportCst.PREF_REF+"15\">MyEnum</a>.<a title=\"The context is static. The key word $this cannot be used in this context.\" class=\"e\">$this</a>.<a title=\"pkg.MyEnum.m()\" href=\"#"+ExportCst.PREF_REF+"69\">m</a>();\n" +
                " }\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"69\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report558Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" {\n");
        xml_.append("  $this[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " {\n" +
                "  $this<a title=\"The type pkg.MyEnum is unexpected.\" class=\"e\">[</a>0];\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report559Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" $public $class Inner{\n");
        xml_.append("  $static $int i,1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"44\">Inner</a>{\n" +
                "  $static $int <a name=\""+ExportCst.PREF_REF+"66\">i</a>,<a title=\"The field name 1 is not valid. It must not start with a digit.\" class=\"e\">1</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report560Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" $public $class Inner{\n");
        xml_.append("  $static $int i,1,k;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"44\">Inner</a>{\n" +
                "  $static $int <a name=\""+ExportCst.PREF_REF+"66\">i</a>,<a title=\"The field name 1 is not valid. It must not start with a digit.\" class=\"e\">1</a>,<a name=\""+ExportCst.PREF_REF+"70\">k</a>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report561Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" {\n");
        xml_.append("  $for($int i:){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " {\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"43\">i</a><a title=\"The type java.lang.Object cannot be implicitly cast to java.lang.$iterable\" class=\"e\">:</a>){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report562Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" {\n");
        xml_.append("  $for($int i,$int j:){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " {\n" +
                "  $for($int <a name=\""+ExportCst.PREF_REF+"43\">i</a>,$int <a name=\""+ExportCst.PREF_REF+"50\">j</a><a title=\"The type java.lang.Object cannot be implicitly cast to java.lang.$iterableTable\" class=\"e\">:</a>){}\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report563Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyInt {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append(" {}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The type pkg.MyCl must have a constructor because of implementing interfaces with instance elements.\" class=\"e\">pkg.MyCl</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"53\">MyInt</a> {\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"53\">pkg.MyInt</a> {\n" +
                " {}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report564Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyInt {\n");
        xml_.append(" MyCl() {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.MyInt {\n");
        xml_.append(" {}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MyInt\" href=\"#"+ExportCst.PREF_REF+"66\">MyInt</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\" title=\"A constructor of the type pkg.MyInt must be called in the constructor.\" class=\"e\">MyCl(</a>) {\n" +
                " }\n" +
                "}\n" +
                "$public $interface <a name=\""+ExportCst.PREF_REF+"66\">pkg.MyInt</a> {\n" +
                " {}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report565Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  'ab';\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <span class=\"s\"><a title=\"Bad character format 'ab'\" class=\"e\">'ab'</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report566Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  \"\\ug000\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <span class=\"s\"><a title=\"Bad character format "+MessagesCdmBase.QUOT+"\\ug000"+MessagesCdmBase.QUOT+"\" class=\"e\">\"\\ug000\"</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report567Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  \"\\u\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <span class=\"s\"><a title=\"Bad character format "+MessagesCdmBase.QUOT+"\\u"+MessagesCdmBase.QUOT+"\" class=\"e\">\"\\u\"</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report568Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  \"\\a\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <span class=\"s\"><a title=\"Bad character format "+MessagesCdmBase.QUOT+"\\a"+MessagesCdmBase.QUOT+"\" class=\"e\">\"\\a\"</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report569Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  '\\ug000';\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <span class=\"s\"><a title=\"Bad character format '\\ug000'\" class=\"e\">'\\ug000'</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report570Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  '\\u';\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <span class=\"s\"><a title=\"Bad character format '\\u'\" class=\"e\">'\\u'</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report571Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  '\\a';\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <span class=\"s\"><a title=\"Bad character format '\\a'\" class=\"e\">'\\a'</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report572Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  '\\u1';\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <span class=\"s\"><a title=\"Bad character format '\\u1'\" class=\"e\">'\\u1'</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report573Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  \"\\u1\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  <span class=\"s\"><a title=\"Bad character format "+MessagesCdmBase.QUOT+"\\u1"+MessagesCdmBase.QUOT+"\" class=\"e\">\"\\u1\"</a></span>;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report574Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyCl2 {\n");
        xml_.append(" $public $void m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl2 {\n");
        xml_.append(" $public $final $void m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MyCl2\" href=\"#"+ExportCst.PREF_REF+"72\">MyCl2</a> {\n" +
                " $public $void <a name=\""+ExportCst.PREF_REF+"47\" title=\"The function m() of the type pkg.MyCl2 is final. So overriding it is forbidden.\" class=\"e\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"72\">pkg.MyCl2</a> {\n" +
                " $public $final $void <a name=\""+ExportCst.PREF_REF+"106\">m</a>(){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report575Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyCl2 {\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.MyCl2 {\n");
        xml_.append(" $public $abstract $void m();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"The method m() from the type pkg.MyCl2 must be overriden in the concrete type pkg.MyCl.\" class=\"e\">pkg.MyCl</a>:<a title=\"pkg.MyCl2\" href=\"#"+ExportCst.PREF_REF+"59\">MyCl2</a> {\n" +
                "}\n" +
                "$public $abstract $class <a name=\""+ExportCst.PREF_REF+"59\">pkg.MyCl2</a> {\n" +
                " $public $abstract $void <a name=\""+ExportCst.PREF_REF+"96\">m</a>();\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report576Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" $public MyCl() {\n");
        xml_.append("  $this();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\" title=\"The constructors pkg.MyCl()"+MessagesCdmBase.AMP+"pkg.MyCl() of the type pkg.MyCl belong to cyclic calls.\" class=\"e\">$public MyCl(</a>) {\n" +
                "  <a title=\"pkg.MyCl.pkg.MyCl()\" href=\"#"+ExportCst.PREF_REF+"27\">$this</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report577Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyCl2 {\n");
        xml_.append(" $public MyCl() {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl2 {\n");
        xml_.append(" $public MyCl2($int i) {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a>:<a title=\"pkg.MyCl2\" href=\"#"+ExportCst.PREF_REF+"70\">MyCl2</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"33\" title=\"No super constructor with implicit call is defined and accessible. The explicit call of a super constructor is required for the constructor pkg.MyCl().\" class=\"e\">$public MyCl(</a>) {\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"70\">pkg.MyCl2</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"83\">$public MyCl2(</a>$int <a name=\""+ExportCst.PREF_REF+"102\">i</a>) {\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report578Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl:MyCl2 {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl2 {\n");
        xml_.append(" $public MyCl2($int i) {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\" title=\"No super constructor with implicit call is defined and accessible. There must be at least one constructor for the type pkg.MyCl\" class=\"e\">pkg.MyCl</a>:<a title=\"pkg.MyCl2\" href=\"#"+ExportCst.PREF_REF+"49\">MyCl2</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"49\">pkg.MyCl2</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"62\">$public MyCl2(</a>$int <a name=\""+ExportCst.PREF_REF+"81\">i</a>) {\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report579Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class $interfaces(MyOther) pkg.MySub {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyOther {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class $interfaces(<a title=\"pkg.MyOther\" href=\"#"+ExportCst.PREF_REF+"65\">MyOther</a>) <a name=\""+ExportCst.PREF_REF+"36\" title=\"The type pkg.MyOther is not an interface.\" class=\"e\">pkg.MySub</a> {\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"65\">pkg.MyOther</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report580Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append("{\n");
        xml_.append(" $static($void).m();\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                "{\n" +
                " $static(<a title=\"The type cannot be the key word $void.\" class=\"e\">$void</a>).<a title=\"The function $static m() is undefined.\" class=\"e\">m</a>();\n" +
                "}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report581Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.MySub {\n");
        xml_.append(" int i,this,k;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.MySub</a> {\n" +
                " int <a name=\""+ExportCst.PREF_REF+"30\">i</a>,<a title=\"The field name this is not valid. It must not be a key word.\" class=\"e\">this</a>,<a name=\""+ExportCst.PREF_REF+"37\">k</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report582Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.MySub {\n");
        xml_.append(" MySub i,this=null,k;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrStdReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\""+ExportCst.PREF_REF+"13\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MySub\" href=\"#"+ExportCst.PREF_REF+"13\">MySub</a> <a name=\""+ExportCst.PREF_REF+"32\">i</a>,<a title=\"The field name this is not valid. It must not be a key word.\" class=\"e\">this</a>=null,<a name=\""+ExportCst.PREF_REF+"44\">k</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report583Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class {\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public <a title=\"A type must have an non empty package.\n" +
                "\n" +
                "The part  in a type is not valid. It must be a word.\" class=\"e\">$class</a> {\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report584Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class <>{\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public <a title=\"A type must have an non empty package.\n" +
                "\n" +
                "The part  in a type is not valid. It must be a word.\" class=\"e\">$class</a> <a name=\""+ExportCst.PREF_REF+"15\" title=\"The part must not be empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+"{\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report585Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append(" ()\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a>{\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\" title=\"The field name  is not valid. It must be a word.\" class=\"e\">(</a>)\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report586Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append(" (){}\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a>{\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\" title=\"The inner type simple name  is duplicated.\n" +
                "\n" +
                "The part  in a type is not valid. It must be a word.\n" +
                "\n" +
                "The field name  is not valid. It must be a word.\" class=\"e\">(</a>){}\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report587Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append(" ();\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a>{\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\" title=\"The field name  is not valid. It must be a word.\" class=\"e\">(</a>);\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report588Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append(" {}\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum pkg.MyEnum{\n" +
                " {}\n" +
                "<a title=\"Bad index by parsing.\" class=\"e\">}</a></span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report589Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append(" ONE();\n");
        xml_.append(" MyEnum($int i){");
        xml_.append(" }");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a>{\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\" title=\"The constructor pkg.MyEnum() is undefined.\" class=\"e\">ONE</a>();\n" +
                " <a name=\""+ExportCst.PREF_REF+"35\">MyEnum(</a>$int <a name=\""+ExportCst.PREF_REF+"47\">i</a>){ }}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report590Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append(" <>;\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a>{\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\" title=\"The type pkg.MyEnum"+MessagesCdmBase.LT+"java.lang.Object"+MessagesCdmBase.GT+" is not parameterized correctly.\n" +
                "\n" +
                "The field name  is not valid. It must be a word.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+";\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report591Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append(" <>{};\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a>{\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\" title=\"The inner type simple name  is duplicated.\n" +
                "\n" +
                "The part  in a type is not valid. It must be a word.\n" +
                "\n" +
                "There must be a type.\n" +
                "\n" +
                "The type pkg.MyEnum"+MessagesCdmBase.LT+"java.lang.Object"+MessagesCdmBase.GT+" is not parameterized correctly.\n" +
                "\n" +
                "The field name  is not valid. It must be a word.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+"{};\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report592Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append(" <>\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a>{\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\" title=\"The type pkg.MyEnum"+MessagesCdmBase.LT+"java.lang.Object"+MessagesCdmBase.GT+" is not parameterized correctly.\n" +
                "\n" +
                "The field name  is not valid. It must be a word.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+"\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report593Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum{\n");
        xml_.append(" <>{}\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a>{\n" +
                " <a name=\""+ExportCst.PREF_REF+"27\" title=\"The inner type simple name  is duplicated.\n" +
                "\n" +
                "The part  in a type is not valid. It must be a word.\n" +
                "\n" +
                "There must be a type.\n" +
                "\n" +
                "The type pkg.MyEnum"+MessagesCdmBase.LT+"java.lang.Object"+MessagesCdmBase.GT+" is not parameterized correctly.\n" +
                "\n" +
                "The field name  is not valid. It must be a word.\" class=\"e\">"+MessagesCdmBase.LT+"</a>"+MessagesCdmBase.GT+"{}\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report594Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  (< <);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " {\n" +
                "  (<a title=\"The expression part is empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a> <a title=\"The expression part is empty.\" class=\"e\">"+MessagesCdmBase.LT+"</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void report595Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String method(){\n");
        xml_.append("  ExClass e = $new ExClass();\n");
        xml_.append("  e.field=10;\n");
        xml_.append("  ExClass f = $new ExClass();\n");
        xml_.append("  f.field=1;\n");
        xml_.append("  e||=f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExClass {\n");
        xml_.append(" $public int field=2;\n");
        xml_.append(" $public $static $boolean $true(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $false(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append(" $operator&& $boolean (ExClass i, ExClass j){\n");
        xml_.append(" }\n");
        xml_.append(" $operator|| $boolean (ExClass i, ExClass j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExClass $($boolean i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"51\" title=\"A $throw block or a $return block is missing for the method $static method().\" class=\"e\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"177\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"71\">e</a> = $new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"177\">ExClass</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"71\">e</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"204\">field</a>=10;\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"177\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"115\">f</a> = $new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"177\">ExClass</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"115\">f</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"204\">field</a>=1;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"71\">e</a><a title=\"pkg.ExClass.$static $true($boolean,pkg.ExClass)\" href=\"#"+ExportCst.PREF_REF+"239\">|</a><a title=\"pkg.ExClass.$static ||(pkg.ExClass,pkg.ExClass)\" href=\"#"+ExportCst.PREF_REF+"367\">|</a>=<a href=\"#"+ExportCst.PREF_REF+"115\">f</a>;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"177\">pkg.ExClass</a> {\n" +
                " $public <a title=\"The type int is unknown.\" class=\"e\">int</a> <a name=\""+ExportCst.PREF_REF+"204\">field</a>=2;\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"239\" title=\"A $throw block or a $return block is missing for the method $static $true($boolean,pkg.ExClass).\" class=\"e\">$true</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"177\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"253\">i</a>){\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"286\" title=\"A $throw block or a $return block is missing for the method $static $false($boolean,pkg.ExClass).\" class=\"e\">$false</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"177\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"301\">i</a>){\n" +
                " }\n" +
                " $operator<a name=\""+ExportCst.PREF_REF+"318\" title=\"A $throw block or a $return block is missing for the method $static "+MessagesCdmBase.AMP+""+MessagesCdmBase.AMP+"(pkg.ExClass,pkg.ExClass).\" class=\"e\">"+MessagesCdmBase.AMP+""+MessagesCdmBase.AMP+"</a> $boolean (<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"177\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"339\">i</a>, <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"177\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"350\">j</a>){\n" +
                " }\n" +
                " $operator<a name=\""+ExportCst.PREF_REF+"367\" title=\"A $throw block or a $return block is missing for the method $static ||(pkg.ExClass,pkg.ExClass).\" class=\"e\">||</a> $boolean (<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"177\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"388\">i</a>, <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"177\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"399\">j</a>){\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"432\" title=\"A $throw block or a $return block is missing for the method $static $($boolean,pkg.ExClass).\" class=\"e\">$</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"177\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"442\">i</a>){\n" +
                " }\n" +
                " $public $static <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"177\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"474\" title=\"A $throw block or a $return block is missing for the method $static $(pkg.ExClass,$boolean).\" class=\"e\">$</a>($boolean <a name=\""+ExportCst.PREF_REF+"485\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report596Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  Object v = $null;\n");
        xml_.append("  $switch (v){\n");
        xml_.append("   $case CharSequence #v;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"61\">v</a> = $null;\n" +
                "  $switch (<a href=\"#"+ExportCst.PREF_REF+"61\">v</a>){\n" +
                "   $case CharSequence <a name=\""+ExportCst.PREF_REF+"109\" title=\"The variable name #v is not valid. It must be a word.\" class=\"e\">#v</a>;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report597Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  Object v = $null;\n");
        xml_.append("  $switch (v){\n");
        xml_.append("   $case CharSequence w;\n");
        xml_.append("   $case String w;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"61\">v</a> = $null;\n" +
                "  $switch (<a href=\"#"+ExportCst.PREF_REF+"61\">v</a>){\n" +
                "   $case CharSequence <a name=\""+ExportCst.PREF_REF+"109\">w</a>;\n" +
                "   <a title=\"The code is unreachable in the function method()\" class=\"e\">$case</a> String <a name=\""+ExportCst.PREF_REF+"128\">w</a>;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report598Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  Object v = $null;\n");
        xml_.append("  $switch (v){\n");
        xml_.append("   $default #w;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"61\">v</a> = $null;\n" +
                "  $switch (<a href=\"#"+ExportCst.PREF_REF+"61\">v</a>){\n" +
                "   $default <a name=\""+ExportCst.PREF_REF+"99\" title=\"The variable name #w is not valid. It must be a word.\" class=\"e\">#w</a>;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report599Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  Object v = $null;\n");
        xml_.append("  $switch (v){\n");
        xml_.append("   $default w;\n");
        xml_.append("   $case $int x;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"61\">v</a> = $null;\n" +
                "  $switch (<a href=\"#"+ExportCst.PREF_REF+"61\">v</a>){\n" +
                "   <a title=\"The $default block is duplicated in the parent $switch block.\" class=\"e\">$default</a> <a title=\"java.lang.Object\" name=\""+ExportCst.PREF_REF+"99\">w</a>;\n" +
                "   $case $int <a name=\""+ExportCst.PREF_REF+"116\">x</a>;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report600Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  Object v = $null;\n");
        xml_.append("  $switch (v){\n");
        xml_.append("   $case CharSequence;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"61\">v</a> = $null;\n" +
                "  $switch (<a href=\"#"+ExportCst.PREF_REF+"61\">v</a>){\n" +
                "   <a title=\"The $case block with expression CharSequence is not constant.\" class=\"e\">$case</a> <a title=\"There is no accessible field named CharSequence from the type pkg.MySub in this context.\" class=\"e\">CharSequence</a>;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report601Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  Object v = $null;\n");
        xml_.append("  $switch (v){\n");
        xml_.append("   $default;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"61\">v</a> = $null;\n" +
                "  $switch (<a href=\"#"+ExportCst.PREF_REF+"61\">v</a>){\n" +
                "   <a title=\"The variable name  is not valid. It must be a word.\" class=\"e\">$default</a>;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report602Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  Object v = $null;\n");
        xml_.append("  $switch (v){\n");
        xml_.append("   $case CharSeq w;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"61\">v</a> = $null;\n" +
                "  $switch (<a href=\"#"+ExportCst.PREF_REF+"61\">v</a>){\n" +
                "   $case <a title=\"The type CharSeq is unknown.\" class=\"e\">CharSeq</a> <a name=\""+ExportCst.PREF_REF+"104\">w</a>;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report603Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  Object v = $null;\n");
        xml_.append("  $switch (v){\n");
        xml_.append("   $case;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"61\">v</a> = $null;\n" +
                "  $switch (<a href=\"#"+ExportCst.PREF_REF+"61\">v</a>){\n" +
                "   <a title=\"There must be an expression.\n" +
                "\n" +
                "The $case block with expression  is not constant.\" class=\"e\">$case</a>;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report604Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  Object v = $null;\n");
        xml_.append("  $switch (v){\n");
        xml_.append("   $case $null;\n");
        xml_.append("   $case $null;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"61\">v</a> = $null;\n" +
                "  $switch (<a href=\"#"+ExportCst.PREF_REF+"61\">v</a>){\n" +
                "   $case $null;\n" +
                "   <a title=\"The $case block with value  is duplicated in the parent $switch block.\" class=\"e\">$case</a> $null;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report605Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $case 1;$default\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\" title=\"A $throw block or a $return block is missing for the method method().\" class=\"e\">method</a>() {\n" +
                "  <a title=\"Bad index by parsing.\n" +
                "\n" +
                "The $case block with expression 1 must be child of a block $switch.\" class=\"e\">$case</a> 1;$default\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report606Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $case 1;$default;");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\" title=\"A $throw block or a $return block is missing for the method method().\" class=\"e\">method</a>() {\n" +
                "  <a title=\"The $case block with expression 1 must be child of a block $switch.\" class=\"e\">$case</a> 1;<a title=\"Bad index by parsing.\n" +
                "\n" +
                "The $default block with expression  must be child of a block $switch.\" class=\"e\">$default</a>;</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report607Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $case 1;$default v");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\" title=\"A $throw block or a $return block is missing for the method method().\" class=\"e\">method</a>() {\n" +
                "  <a title=\"Bad index by parsing.\n" +
                "\n" +
                "The $case block with expression 1 must be child of a block $switch.\" class=\"e\">$case</a> 1;$default v</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report608Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  Object v = $null;\n");
        xml_.append("  $switch (v){\n");
        xml_.append("   $default v;\n");
        xml_.append("   $case $null;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  Object <a name=\""+ExportCst.PREF_REF+"61\">v</a> = $null;\n" +
                "  $switch (<a href=\"#"+ExportCst.PREF_REF+"61\">v</a>){\n" +
                "   <a title=\"The $default block is duplicated in the parent $switch block.\" class=\"e\">$default</a> <a name=\""+ExportCst.PREF_REF+"99\" title=\"The variable name v is not valid. It must not be the name of an other variable of the scope.\" class=\"e\">v</a>;\n" +
                "   $case $null;\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report609Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int method() {\n");
        xml_.append("  $math v = $null;\n");
        xml_.append("  $switch (v){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">method</a>() {\n" +
                "  $math <a name=\""+ExportCst.PREF_REF+"60\">v</a> = $null;\n" +
                "  <a title=\"The type java.lang.$math is unexpected.\" class=\"e\">$switch</a> (<a href=\"#"+ExportCst.PREF_REF+"60\">v</a>){\n" +
                "  }\n" +
                "  $return 1;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report610Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass {\n");
        xml_.append(" {\n");
        xml_.append("  $new MyClass()?1:1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $true(MyClass m){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a> {\n" +
                " {\n" +
                "  $new <a title=\"pkg.MyClass\" href=\"#"+ExportCst.PREF_REF+"15\">MyClass</a>()?1<a title=\"pkg.MyClass.$static $true($boolean,pkg.MyClass)\" href=\"#"+ExportCst.PREF_REF+"83\">:</a>1;\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"83\" title=\"A $throw block or a $return block is missing for the method $static $true($boolean,pkg.MyClass).\" class=\"e\">$true</a>(<a title=\"pkg.MyClass\" href=\"#"+ExportCst.PREF_REF+"15\">MyClass</a> <a name=\""+ExportCst.PREF_REF+"97\">m</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report611Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String method(){\n");
        xml_.append("  ExClass<?> e = $new ExClass<$int>();\n");
        xml_.append("  e.field=10;\n");
        xml_.append("  e.$true($null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExClass<T> {\n");
        xml_.append(" $public $int field=2;\n");
        xml_.append(" $public $static $boolean $true(ExClass<T> i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $false(ExClass<T> i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"51\" title=\"A $throw block or a $return block is missing for the method $static method().\" class=\"e\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"152\">ExClass</a>"+MessagesCdmBase.LT+"?"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"74\">e</a> = $new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"152\">ExClass</a>"+MessagesCdmBase.LT+"$int"+MessagesCdmBase.GT+"();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"74\">e</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"183\">field</a>=10;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"74\">e</a>.<a title=\"The function $true() is undefined.\" class=\"e\">$true</a>($null);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"152\">pkg.ExClass</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"164\">T</a>"+MessagesCdmBase.GT+" {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"183\">field</a>=2;\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"218\" title=\"A $throw block or a $return block is missing for the method $static $true($boolean,pkg.ExClass"+MessagesCdmBase.LT+"#T"+MessagesCdmBase.GT+").\" class=\"e\">$true</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"152\">ExClass</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"164\">T</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"235\">i</a>){\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"268\" title=\"A $throw block or a $return block is missing for the method $static $false($boolean,pkg.ExClass"+MessagesCdmBase.LT+"#T"+MessagesCdmBase.GT+").\" class=\"e\">$false</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"152\">ExClass</a>"+MessagesCdmBase.LT+"<a href=\"#"+ExportCst.PREF_REF+"164\">T</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"286\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report612Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String method(){\n");
        xml_.append("  ExClass e = $new ExClass();\n");
        xml_.append("  e.field=10;\n");
        xml_.append("  e.$true();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExClass {\n");
        xml_.append(" $public $int field=2;\n");
        xml_.append(" $public $static $boolean $true(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $false(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"51\" title=\"A $throw block or a $return block is missing for the method $static method().\" class=\"e\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"138\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"71\">e</a> = $new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"138\">ExClass</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"71\">e</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"166\">field</a>=10;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"71\">e</a>.<a title=\"The function $true() is undefined.\" class=\"e\">$true</a>();\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"138\">pkg.ExClass</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"166\">field</a>=2;\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"201\" title=\"A $throw block or a $return block is missing for the method $static $true($boolean,pkg.ExClass).\" class=\"e\">$true</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"138\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"215\">i</a>){\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"248\" title=\"A $throw block or a $return block is missing for the method $static $false($boolean,pkg.ExClass).\" class=\"e\">$false</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"138\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"263\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report613Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String method(){\n");
        xml_.append("  ExClass.$true($null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExClass {\n");
        xml_.append(" $public $int field=2;\n");
        xml_.append(" $public $static $boolean $true(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $false(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"51\" title=\"A $throw block or a $return block is missing for the method $static method().\" class=\"e\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"105\">ExClass</a>.<a title=\"The function $static $true() is undefined.\" class=\"e\">$true</a>($null);\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"105\">pkg.ExClass</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"133\">field</a>=2;\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"168\" title=\"A $throw block or a $return block is missing for the method $static $true($boolean,pkg.ExClass).\" class=\"e\">$true</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"105\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"182\">i</a>){\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"215\" title=\"A $throw block or a $return block is missing for the method $static $false($boolean,pkg.ExClass).\" class=\"e\">$false</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"105\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"230\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report614Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String method(){\n");
        xml_.append("  $return \"\";\n");
        xml_.append("  $if ($new ExClass()){\n");
        xml_.append("   $return \"Vrai\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"Faux\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExClass {\n");
        xml_.append(" $public $static $boolean $true(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $false(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"51\">method</a>(){\n" +
                "  $return <span class=\"s\">\"\"</span>;\n" +
                "  <a title=\"The code is unreachable in the function $static method()\" class=\"e\">$if</a> ($new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"160\">ExClass</a>())<a title=\"pkg.ExClass.$static $true($boolean,pkg.ExClass)\" href=\"#"+ExportCst.PREF_REF+"200\">{</a>\n" +
                "   <a title=\"The code is unreachable in the function $static method()\" class=\"e\">$return</a> <span class=\"s\">\"Vrai\"</span>;\n" +
                "  }\n" +
                "  <a title=\"The code is unreachable in the function $static method()\" class=\"e\">$return</a> <span class=\"s\">\"Faux\"</span>;\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"160\">pkg.ExClass</a> {\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"200\" title=\"A $throw block or a $return block is missing for the method $static $true($boolean,pkg.ExClass).\" class=\"e\">$true</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"160\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"214\">i</a>){\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"247\" title=\"A $throw block or a $return block is missing for the method $static $false($boolean,pkg.ExClass).\" class=\"e\">$false</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"160\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"262\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report615Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $intern;\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a> {\n" +
                " <a title=\"Bad index by parsing.\" class=\"e\">$intern</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report616Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $intern{);\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a> {\n" +
                " <a title=\"Bad index by parsing.\" class=\"e\">$intern</a>{);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report617Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply:Super {\n");
        xml_.append(" $intern{m():m(Super,$int...,$int)};\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Super {\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a>:<a title=\"pkg.Super\" href=\"#"+ExportCst.PREF_REF+"87\">Super</a> {\n" +
                " $intern{m():m(<a title=\"pkg.Super\" href=\"#"+ExportCst.PREF_REF+"87\">Super</a>,$int<a title=\"The three dots are unexpected here.\" class=\"e\">...</a>,$int)};\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"87\">pkg.Super</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report618Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply:Super {\n");
        xml_.append(" $intern{m($int...,$int)};\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Super {\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a>:<a title=\"pkg.Super\" href=\"#"+ExportCst.PREF_REF+"77\">Super</a> {\n" +
                " $intern{m($int<a title=\"The three dots are unexpected here.\" class=\"e\">...</a>,$int)};\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"77\">pkg.Super</a> {\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report619Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $void m()$intern(Apply:m(Apply,$int...,$int)){}\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a> {\n" +
                " $void <a name=\""+ExportCst.PREF_REF+"34\">m</a>()$intern(<a title=\"pkg.Apply\" href=\"#"+ExportCst.PREF_REF+"15\">Apply</a>:m(<a title=\"pkg.Apply\" href=\"#"+ExportCst.PREF_REF+"15\">Apply</a>,$int<a title=\"The three dots are unexpected here.\" class=\"e\">...</a>,$int)){}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report621Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $Fct<$int,$int> f=$null;\n");
        xml_.append("  f.metaInfo(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $Fct"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"47\">f</a>=$null;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"47\">f</a>.<b>metaInfo</b><a title=\"The number of required arguments 0 is different from the number of supplied arguments 1 for the method of the elliptic type java.lang.$Fct\" class=\"e\">(</a>0);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report622Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyCl {\n");
        xml_.append(" {\n");
        xml_.append("  $Fct<$int,$int> f=$null;\n");
        xml_.append("  f.instance(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyCl</a> {\n" +
                " {\n" +
                "  $Fct"+MessagesCdmBase.LT+"$int,$int"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"47\">f</a>=$null;\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"47\">f</a>.<b>instance</b><a title=\"The number of required arguments 0 is different from the number of supplied arguments 1 for the method of the elliptic type java.lang.$Fct\" class=\"e\">(</a>0);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report623Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $double i=1e+;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $double <a name=\""+ExportCst.PREF_REF+"36\">i</a><a title=\"The type java.lang.Number cannot be implicitly cast to $double\" class=\"e\">=</a><a title=\"Bad number 1e\" class=\"e\">1e</a><a title=\"The expression part is empty.\" class=\"e\">+</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report624Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $double i=1e+(1.0);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $double <a name=\""+ExportCst.PREF_REF+"36\">i</a>=<a title=\"Bad number 1e\" class=\"e\">1e</a>+(1.0);\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report625Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $double i=1e;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $double <a name=\""+ExportCst.PREF_REF+"36\">i</a>=<a title=\"Bad number 1e\" class=\"e\">1e</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report626Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $double i=1e*1.0;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $double <a name=\""+ExportCst.PREF_REF+"36\">i</a>=<a title=\"Bad number 1e\" class=\"e\">1e</a>*1.0;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report627Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $double i=1e*;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $double <a name=\""+ExportCst.PREF_REF+"36\">i</a><a title=\"The type java.lang.Number cannot be implicitly cast to $double\" class=\"e\">=</a><a title=\"Bad number 1e\" class=\"e\">1e</a><a title=\"The expression part is empty.\" class=\"e\">*</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report628Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $double i=1e*/;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $double <a name=\""+ExportCst.PREF_REF+"36\">i</a><a title=\"The type java.lang.Number cannot be implicitly cast to $double\" class=\"e\">=</a><a title=\"Bad number 1e\" class=\"e\">1e</a><a title=\"The expression part is empty.\" class=\"e\">*</a><a title=\"The expression part is empty.\" class=\"e\">/</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report629Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $double i=1e 1;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $double <a name=\""+ExportCst.PREF_REF+"36\">i</a>=<a title=\"Bad number 1e 1\" class=\"e\">1e 1</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report630Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $double i=1.e 1;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $double <a name=\""+ExportCst.PREF_REF+"36\">i</a>=<a title=\"Bad number 1.e 1\" class=\"e\">1.e 1</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report631Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $double i=1.e1 1;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $double <a name=\""+ExportCst.PREF_REF+"36\">i</a>=<a title=\"Bad number 1.e1 1\" class=\"e\">1.e1 1</a>;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report632Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $double i=1ee1 ;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $double <a name=\""+ExportCst.PREF_REF+"36\">i</a>=<a title=\"Bad number 1ee1\" class=\"e\">1ee1</a> ;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report633Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $double i=1ee 1 ;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $double <a name=\""+ExportCst.PREF_REF+"36\">i</a>=<a title=\"Bad number 1ee 1\" class=\"e\">1ee 1</a> ;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report634Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" pkg.MyClass.Inner v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyClass<T>{\n");
        xml_.append(" $public $class Inner{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyClass\" href=\"#"+ExportCst.PREF_REF+"66\">pkg.MyClass</a><a title=\"The type pkg.MyClass..Inner is not parameterized correctly.\" class=\"e\">.</a><a title=\"pkg.MyClass..Inner\" href=\"#"+ExportCst.PREF_REF+"98\">Inner</a> <a name=\""+ExportCst.PREF_REF+"46\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"66\">pkg.MyClass</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"78\">T</a>"+MessagesCdmBase.GT+"{\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"98\">Inner</a>{\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report635Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass<T>{\n");
        xml_.append(" $public $class Inner{\n");
        xml_.append(" }\n");
        xml_.append(" $public $class InnerTwo:MyClass.Inner{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"27\">T</a>"+MessagesCdmBase.GT+"{\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"47\">Inner</a>{\n" +
                " }\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"73\">InnerTwo</a>:<a title=\"pkg.MyClass\" href=\"#"+ExportCst.PREF_REF+"15\">MyClass</a><a title=\"The type pkg.MyClass..Inner is not parameterized correctly.\" class=\"e\">.</a><a title=\"pkg.MyClass..Inner\" href=\"#"+ExportCst.PREF_REF+"47\">Inner</a>{\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report636Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyClass<T>{\n");
        xml_.append(" $public $class Inner{\n");
        xml_.append(" }\n");
        xml_.append(" $static{\n");
        xml_.append("  $new Inner();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyClass</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"27\">T</a>"+MessagesCdmBase.GT+"{\n" +
                " $public $class <a name=\""+ExportCst.PREF_REF+"47\">Inner</a>{\n" +
                " }\n" +
                " $static{\n" +
                "  <a title=\"The type pkg.MyClass..Inner is not resolved for instancing.\" class=\"e\">$new</a> <a title=\"The type pkg.MyClass..Inner is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyClass..Inner\" href=\"#"+ExportCst.PREF_REF+"47\" class=\"e\">Inner</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report637Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MySub {\n");
        xml_.append(" {\n");
        xml_.append("  $thisaccess(MyCl)m();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $interface <a name=\""+ExportCst.PREF_REF+"19\">pkg.MySub</a> {\n" +
                " {\n" +
                "  $thisaccess(<a title=\"The type MyCl is unknown.\" class=\"e\">MyCl</a>)<a title=\"The function m() is undefined.\" class=\"e\">m</a>();\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report638Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String method(){\n");
        xml_.append("  ExClass e = $new ExClass();\n");
        xml_.append("  e.field=10;\n");
        xml_.append("  $classchoice(ExClass)$true();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExClass {\n");
        xml_.append(" $public $int field=2;\n");
        xml_.append(" $public $static $boolean $true(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $false(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"51\" title=\"A $throw block or a $return block is missing for the method $static method().\" class=\"e\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"157\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"71\">e</a> = $new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"157\">ExClass</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"71\">e</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"185\">field</a>=10;\n" +
                "  $classchoice(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"157\">ExClass</a>)<a title=\"The function $static $true() is undefined.\" class=\"e\">$true</a>();\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"157\">pkg.ExClass</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"185\">field</a>=2;\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"220\" title=\"A $throw block or a $return block is missing for the method $static $true($boolean,pkg.ExClass).\" class=\"e\">$true</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"157\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"234\">i</a>){\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"267\" title=\"A $throw block or a $return block is missing for the method $static $false($boolean,pkg.ExClass).\" class=\"e\">$false</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"157\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"282\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report639Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String method(){\n");
        xml_.append("  ExClass e = $new ExClass();\n");
        xml_.append("  e.field=10;\n");
        xml_.append("  $superaccess(ExClass)$true();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExClass {\n");
        xml_.append(" $public $int field=2;\n");
        xml_.append(" $public $static $boolean $true(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $false(ExClass i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.Apply</a> {\n" +
                " $public $static String <a name=\""+ExportCst.PREF_REF+"51\" title=\"A $throw block or a $return block is missing for the method $static method().\" class=\"e\">method</a>(){\n" +
                "  <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"157\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"71\">e</a> = $new <a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"157\">ExClass</a>();\n" +
                "  <a href=\"#"+ExportCst.PREF_REF+"71\">e</a>.<a title=\"pkg.ExClass.field\" href=\"#"+ExportCst.PREF_REF+"185\">field</a>=10;\n" +
                "  $superaccess(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"157\">ExClass</a>)<a title=\"The type pkg.Apply cannot be implicitly cast to pkg.ExClass\n" +
                "\n" +
                "The function $static $true() is undefined.\" class=\"e\">$true</a>();\n" +
                " }\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"157\">pkg.ExClass</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"185\">field</a>=2;\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"220\" title=\"A $throw block or a $return block is missing for the method $static $true($boolean,pkg.ExClass).\" class=\"e\">$true</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"157\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"234\">i</a>){\n" +
                " }\n" +
                " $public $static $boolean <a name=\""+ExportCst.PREF_REF+"267\" title=\"A $throw block or a $return block is missing for the method $static $false($boolean,pkg.ExClass).\" class=\"e\">$false</a>(<a title=\"pkg.ExClass\" href=\"#"+ExportCst.PREF_REF+"157\">ExClass</a> <a name=\""+ExportCst.PREF_REF+"282\">i</a>){\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report640Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $iterable<MyCl> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl<T>{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $iterable"+MessagesCdmBase.LT+"<a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"64\" class=\"e\">MyCl</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"44\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"64\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"73\">T</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report641Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" MyParam<MyCl> v;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyCl<T>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyParam<S>{\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " <a title=\"pkg.MyParam\" href=\"#"+ExportCst.PREF_REF+"92\">MyParam</a>"+MessagesCdmBase.LT+"<a title=\"The type pkg.MyCl is not parameterized correctly.\n" +
                "\n" +
                "pkg.MyCl\" href=\"#"+ExportCst.PREF_REF+"62\" class=\"e\">MyCl</a>"+MessagesCdmBase.GT+" <a name=\""+ExportCst.PREF_REF+"42\">v</a>;\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"62\">pkg.MyCl</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"71\">T</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "$public $class <a name=\""+ExportCst.PREF_REF+"92\">pkg.MyParam</a>"+MessagesCdmBase.LT+"<a name=\""+ExportCst.PREF_REF+"104\">S</a>"+MessagesCdmBase.GT+"{\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report642Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append(" ONE<Inexist>{}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $enum <a name=\""+ExportCst.PREF_REF+"14\">pkg.MyEnum</a> {\n" +
                " <a name=\""+ExportCst.PREF_REF+"28\" title=\"The type pkg.MyEnum"+MessagesCdmBase.LT+"java.lang.Object"+MessagesCdmBase.GT+" is not parameterized correctly.\" class=\"e\">ONE</a>"+MessagesCdmBase.LT+"<a title=\"The type Inexist is unknown.\" class=\"e\">Inexist</a>"+MessagesCdmBase.GT+"{}\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report643Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" {\n");
        xml_.append("  $values();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " {\n" +
                "  <a title=\"The type  is unexpected.\" class=\"e\">$values</a><a title=\"There must be a type.\" class=\"e\">(</a>);\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report644Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [) {\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public <a title=\"A type must have an non empty package.\n" +
                "\n" +
                "The part  in a type is not valid. It must be a word.\" class=\"e\">$class</a> [) {\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report645Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ {\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$operator<a title=\"Bad index by parsing.\" class=\"e\">+</a> {\n" +
                "}</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report646Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" $default;\n");
        xml_.append("  $int i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " <a title=\"The block is unexpected.\" class=\"e\">$default</a>;\n" +
                "  $int i;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }
    @Test
    public void report647Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MyEnum {\n");
        xml_.append(" $case 1;\n");
        xml_.append("  $int i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MyEnum</a> {\n" +
                " <a title=\"The block is unexpected.\" class=\"e\">$case</a> 1;\n" +
                "  $int i;\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }

    @Test
    public void reportWithoutErrorTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.MySub {\n");
        xml_.append(" $public $int m() {\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        StringMap<String> filesExp_ = ctxNotErrReadOnly(files_);
        assertEq("<html><head><link href=\"../../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">$public $class <a name=\""+ExportCst.PREF_REF+"15\">pkg.MySub</a> {\n" +
                " $public $int <a name=\""+ExportCst.PREF_REF+"41\">m</a>() {\n" +
                "  $return 0;\n" +
                " }\n" +
                "}\n" +
                "</span></pre></body></html>", filesExp_.firstValue());
    }


}
