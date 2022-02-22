package code.formathtml;

import code.util.StringMap;
import org.junit.Test;

public final class RenderTextAdvTest extends CommonRender {
    private static final String CUST_ITER_PATH = "pkg/CustIter";
    private static final String CUST_ITER_TABLE_PATH = "pkg/CustIterTable";
    private static final String CUST_LIST_PATH = "pkg/CustList";
    private static final String CUST_PAIR_PATH = "pkg/CustPair";
    private static final String CUST_TABLE_PATH = "pkg/CustTable";
    @Test
    public void process_1Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='T[]' value='i=$new[1]'/><c:set value='i[0]=(T)2'/>{i[0]}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_2Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int[]' value='i=$new[1]'/><c:set value='i[0]=elt()'/>{i[0]}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append(" $public T elt(){");
        file_.append("  $return (T)2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_3Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='T[]' value='i=$new T[]{(T)2}'/>{i[0]}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_4Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$Fct&lt;$int,T[]&gt;' value='i=$lambda(T[],$new,$int)'/><c:set className='T[]' value='a=i.call(1)'/><c:set value='a[0]=(T)2'/>{a[0]}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_5Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$Fct&lt;$int,T[]&gt;' value='i=$lambda(T[],$new,$int)'/>{$Class.getClass(i)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>java.lang.$Fct&lt;$int,[$int&gt;</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_6Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$Fct&lt;T,pkg.BeanRec&lt;T&gt;&gt;' value='i=$lambda(pkg.BeanRec&lt;T&gt;,$new,field)'/><c:set className='pkg.BeanRec&lt;T&gt;' value='a=i.call((T)2)'/>{a.field}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public @$class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_7Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$Fct&lt;T,pkg.BeanRec&lt;T&gt;&gt;' value='i=$lambda(pkg.BeanRec&lt;T&gt;,$new,T)'/><c:set className='pkg.BeanRec&lt;T&gt;' value='a=i.call((T)2)'/>{a.field}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_8Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanRec&lt;T&gt;' value='elt=$new((T)2)'/><c:set className='$Fct&lt;T,T&gt;' value='i=elt.$lambda(pkg.BeanRec&lt;T&gt;,meth,T)'/>{i.call((T)1)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append(" $public U meth(U p){");
        file_.append("  $return (U)(($int)field + ($int)p);");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_9Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanRec&lt;T&gt;' value='elt=$new((T)2)'/><c:set className='$Fct&lt;T&gt;' value='i=elt.$lambda(pkg.BeanRec&lt;T&gt;,,field)'/>{i.call()}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_10Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanRec&lt;T&gt;' value='elt=$new pkg.BeanRec&lt;&gt;((T)2)'/><c:set className='$Fct&lt;T,T&gt;' value='i=elt.$lambda(pkg.BeanRec&lt;T&gt;,meth,T)'/>{i.call((T)1)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append(" $public U meth(U p){");
        file_.append("  $return (U)(($int)field + ($int)p);");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>3</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_11Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='T[]' value='i=$new[1]'/><c:set value='i[0]=$staticCall(pkg.BeanOne&lt;T&gt;).elt()'/>{i[0]}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append(" $public $staticCall T elt(){");
        file_.append("  $return (T)2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_12Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='$int[]' value='i=$new[1]'/><c:set value='i[0]=$staticCall().elt()'/>{i[0]}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append(" $public $staticCall T elt(){");
        file_.append("  $return (T)2;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_13Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanRec&lt;T&gt;' value='elt=$new((T)2)'/><c:set className='T' value='i=elt'/>{i}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append(" $public $static U $(BeanRec<U> p){");
        file_.append("  $return p.field;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>{$class(T)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>$int</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>{$default((T)2)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_16Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanRec&lt;T&gt;' value='i=$null'/>{i?.field}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>0</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_17Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='T[]' value='i=$new[1]'/><c:set value='i[0]=(T)2'/>{i[0]$instanceof T}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>true</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_18Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanRec&lt;T&gt;' value='elt=explicit(pkg.BeanRec&lt;T&gt;)(T)2'/>{elt.field}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append(" $public $static BeanRec<U> explicit(U p){");
        file_.append("  $return $new(p);");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_19Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanRec&lt;T&gt;' value='elt=explicit(pkg.BeanRec&lt;T&gt;)$new pkg.BeanRec&lt;T&gt;((T)2)'/>{elt.field}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_20Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanRec&lt;T&gt;' value='elt=$new((T)2)'/><c:switch value='elt'><c:case className='pkg.BeanRec&lt;T&gt;' var='i' value='$true'>{i.field}</c:case></c:switch></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_21Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanRec&lt;T&gt;' value='elt=$new((T)2)'/><c:switch value='elt'><c:case className='pkg.BeanRec&lt;T&gt;' var='i' value='i.field == 3'>OTHER</c:case><c:case className='pkg.BeanRec&lt;T&gt;' var='i' value='$true'>{i.field}</c:case></c:switch></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_22Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanRec&lt;T&gt;' value='elt=$new((T)2)'/><c:switch value='elt'><c:default var='i'>{i.field}</c:default></c:switch></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_23Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanRec&lt;T&gt;' value='elt=$new((T)2)'/><c:switch value='elt'><c:case className='pkg.BeanRec2&lt;T&gt;' var='i' value='i.field == 3'>OTHER</c:case><c:case className='pkg.BeanRec&lt;T&gt;' var='i' value='$true'>{i.field}</c:case></c:switch></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec2<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec2(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_24Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:set className='pkg.BeanRec&lt;T&gt;' value='elt=$new((T)2)'/><c:switch value='elt'><c:case className='pkg.BeanRec2&lt;T&gt;' var='i'>OTHER</c:case><c:case className='pkg.BeanRec&lt;T&gt;' var='i' value='$true'>{i.field}</c:case></c:switch></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append("}");
        file_.append("$public $class pkg.BeanRec2<U>{");
        file_.append(" U field;");
        file_.append(" $public BeanRec2(U p){");
        file_.append("  field = p;");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_25Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:for className='T' init='i=(T)2' condition='($int)i&lt;3' step=''>{i}<c:set className='$int' value='j=($int)i+1'/><c:set value='i=(T)j'/></c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_26Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:for className='T' var='i' list='{(T)2}'>{i}</c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html c:bean=\"bean_one\"><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;T,T&gt;\" value=\"inst=$new()\"/>");
        xml_.append("<c:set value=\"inst.add((T)1,(T)2)\"/>");
        xml_.append("<c:set value=\"inst.add((T)3,(T)4)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"T\" value=\"v\" varClassName=\"T\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());
        assertEq("<html><body><table><tr><td>1</td><td>2</td></tr><tr><td>3</td><td>4</td></tr></table></body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html c:bean=\"bean_one\"><body><table>");
        xml_.append("<c:set className=\"pkg.CustList&lt;T&gt;\" value=\"inst=$new()\"/>");
        xml_.append("<c:set value=\"inst.add((T)1)\"/>");
        xml_.append("<c:set value=\"inst.add((T)2)\"/>");
        xml_.append("<c:for var=\"k\" className=\"T\" list=\"inst\">");
        xml_.append("<tr><td>{k}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());
        assertEq("<html><body><table><tr><td>1</td></tr><tr><td>2</td></tr></table></body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_29Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:for className='T' var='i' href='' list='{(T)2}'>{i}</c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_30Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:try><c:throw value='(T)2'/></c:try><c:catch className='T' var='e'>{e}</c:catch></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_31Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body>{$defaultValue(T)}</body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>0</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }

    @Test
    public void process_32Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description <a href=\"\">two</a>\nthree=desc &lt;{0}&gt;\nfour=''asp''";
        String html_ = "<html c:bean=\"bean_one\"><body><c:for className='T' init='i=(T)2' condition='($int)i&lt;3' step='i=(T)(($int)i+1)'>{i}</c:for></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableRenderUtil.formatFile(folder_,locale_,relative_), content_);
        files_.put("page1.html", html_);
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne<T>:code.bean.Bean{");
        file_.append(" $public $static $class Inner{");
        file_.append("  $public String textField=\"txt\";");
        file_.append("  $public $static String $(Inner v){");
        file_.append("   $return v.textField;");
        file_.append("  }");
        file_.append(" }");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>2</body></html>", getCommOneBeanParam(html_, files_, filesSec_));
    }
    private static String getCustomPair() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustPair<U,V> :$pair<U,V>{\n");
        xml_.append(" $private U first;\n");
        xml_.append(" $private V second;\n");
        xml_.append(" $public CustPair(){\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair(U f,V s){\n");
        xml_.append("  first = f;\n");
        xml_.append("  second = s;\n");
        xml_.append(" }\n");
        xml_.append(" $public U getFirst(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append(" $public V getSecond(){\n");
        xml_.append("  $return second;\n");
        xml_.append(" }\n");
        xml_.append(" $public $void setFirst(U f){\n");
        xml_.append("  first = f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustTable<U,V> :$iterableTable<U,V>{\n");
        xml_.append(" $private CustList<CustPair<U,V>> list;\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new CustList<CustPair<U,V>>();\n");
        xml_.append(" }\n");
        xml_.append(" $public $void add(U f,V s){\n");
        xml_.append("  list.add($new CustPair<U,V>(f,s));\n");
        xml_.append(" }\n");
        xml_.append(" $public $void add(CustPair<U,V> p){\n");
        xml_.append("  list.add(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int size(){\n");
        xml_.append("  $return list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair<U,V> get($int index){\n");
        xml_.append("  $return list.get(index);\n");
        xml_.append(" }\n");
        xml_.append(" $public $iteratorTable<U,V> iteratorTable(){\n");
        xml_.append("  $return $new CustIterTable<U,V>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIteratorTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIterTable<U,V> :$iteratorTable<U,V>{\n");
        xml_.append(" $private CustTable<U,V> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public CustIterTable(CustTable<U,V> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair<U,V> nextPair(){\n");
        xml_.append("  CustPair<U,V> out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $boolean hasNextPair(){\n");
        xml_.append("  $return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<U> :$iterable<U>{\n");
        xml_.append(" $private U[] list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new U[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(U elt){\n");
        xml_.append("  add(length,elt);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,U elt){\n");
        xml_.append("  U[] newlist=$new U[length+1i];\n");
        xml_.append("  $iter($int i=0i;index;1i){\n");
        xml_.append("   newlist[i]=list[i];\n");
        xml_.append("  }\n");
        xml_.append("  newlist[index]=elt;\n");
        xml_.append("  $iter($int i=index+1i;length+1i;1i){\n");
        xml_.append("   newlist[i]=list[i-1i];\n");
        xml_.append("  }\n");
        xml_.append("  length++;\n");
        xml_.append("  list=newlist;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int size(){\n");
        xml_.append("  $return length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U get($int index){\n");
        xml_.append("  $return list[index];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,U elt){\n");
        xml_.append("  list[index]=elt;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;length-1i;1i){\n");
        xml_.append("   list[i]=list[i+1i];\n");
        xml_.append("  }\n");
        xml_.append("  list[length-1i]=$null;\n");
        xml_.append("  length--;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<U> iterator(){\n");
        xml_.append("  $return $new pkg.CustIter<U>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<T> :$iterator<T>{\n");
        xml_.append(" $private pkg.CustList<T> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public (pkg.CustList<T> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T next(){\n");
        xml_.append("  T out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
}
