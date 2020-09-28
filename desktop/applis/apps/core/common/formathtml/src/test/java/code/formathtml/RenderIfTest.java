package code.formathtml;



import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderIfTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if><c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif></body></html>";
        assertEq("<html><body>NOT EMPTY</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif><c:else>EMPTY</c:else></body></html>";
        assertEq("<html><body>EMPTY</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;s&quot;.length()==1\">ONE</c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif><c:else>EMPTY</c:else></body></html>";
        assertEq("<html><body>ONE</body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if><c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif><c:else>EMPTY</c:else></body></html>";
        assertEq("<html><body>NOT EMPTY</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;s&quot;.length()==1\">ONE</c:if></body></html>";
        assertEq("<html><body>ONE</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if><c:elseif condition=\"&quot;string&quot;.length()==2\">TWO</c:elseif><c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif><c:else>EMPTY</c:else></body></html>";
        assertEq("<html><body>NOT EMPTY</body></html>", getRes(html_, new StringMap<String>()));

    }
    @Test
    public void process9Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1/0\">ONE</c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif></body></html>";
        assertNotNull(getEx(html_, new StringMap<String>()));
    }
    @Test
    public void process10Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if><c:elseif condition=\"&quot;&quot;.length()&gt;1/0\">NOT EMPTY</c:elseif></body></html>";
        assertNotNull(getEx(html_, new StringMap<String>()));
    }


    @Test
    public void process11Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\" label='label'>ONE</c:if><c:elseif condition=\"&quot;string&quot;.length()==2\">TWO</c:elseif><c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY<c:break label='label'/></c:elseif><c:else>EMPTY</c:else></body></html>";
        assertEq("<html><body>NOT EMPTY</body></html>", getRes(html_, new StringMap<String>()));

    }
    @Test
    public void process12Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;s&quot;.length()==1\" label='label'>ONE<c:break label='label'/></c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif><c:else>EMPTY</c:else></body></html>";
        assertEq("<html><body>ONE</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process13Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\" label='label'>ONE</c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif><c:else>EMPTY<c:break label='label'/></c:else></body></html>";
        assertEq("<html><body>EMPTY</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process14Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if>\n<c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process15Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if>\n<c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif></body></html>";
        assertEq("<html><body>NOT EMPTY</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process16Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if>\n<c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif>\n<c:else>EMPTY</c:else></body></html>";
        assertEq("<html><body>EMPTY</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process17Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if>\n<c:else>EMPTY</c:else></body></html>";
        assertEq("<html><body>EMPTY</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process18Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\">ONE</c:if>\n<c:elseif condition=\"&quot;string&quot;.length()==2\">TWO</c:elseif>\n<c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif>\n<c:else>EMPTY</c:else></body></html>";
        assertEq("<html><body>NOT EMPTY</body></html>", getRes(html_, new StringMap<String>()));

    }
    @Test
    public void process19Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"$new pkg.Ex()\">ONE</c:if>\n<c:elseif condition=\"&quot;string&quot;.length()==2\">TWO</c:elseif>\n<c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif>\n<c:else>EMPTY</c:else></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.Ex{");
        enum_.append(" $public $int v = 10;");
        enum_.append(" $public $static $boolean $(Ex e){");
        enum_.append("  $return e.v == 10;");
        enum_.append(" }");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>ONE</body></html>", getRes(html_, files_));

    }
    @Test
    public void process20Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"$new pkg.Ex()\">ONE</c:if>\n<c:elseif condition=\"&quot;string&quot;.length()==2\">TWO</c:elseif>\n<c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif>\n<c:else>EMPTY</c:else></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.Ex{");
        enum_.append(" $public $int v = 10;");
        enum_.append(" $public $static $boolean $true(Ex e){");
        enum_.append("  $return e.v == 10;");
        enum_.append(" }");
        enum_.append(" $public $static $boolean $false(Ex e){");
        enum_.append("  $return e.v != 10;");
        enum_.append(" }");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>ONE</body></html>", getRes(html_, files_));

    }
    @Test
    public void process21Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"&quot;&quot;.length()==1\" label='lab'>ONE</c:if><c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY<c:break label='lab'/></c:elseif></body></html>";
        assertEq("<html><body>NOT EMPTY</body></html>", getRes(html_, new StringMap<String>()));
    }
    private Struct getEx(String html_, StringMap<String> _file) {
        return getCommEx(html_, _file);
    }
    private String getRes(String html_, StringMap<String> files_) {
        return getCommRes(html_,files_);
    }

    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:else>EMPTY</c:else></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process2FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:elseif condition=\"&quot;string&quot;.length()==2\">TWO</c:elseif>\n<c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif>\n<c:else>EMPTY</c:else></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));

    }
    @Test
    public void process3FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>\n<c:else>EMPTY</c:else></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process4FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body>\n<c:elseif condition=\"&quot;string&quot;.length()==2\">TWO</c:elseif>\n<c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY</c:elseif>\n<c:else>EMPTY</c:else></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));

    }
    @Test
    public void process5FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"1\">ONE</c:if><c:elseif condition=\"!&quot;&quot;.isEmpty()\">NOT EMPTY</c:elseif></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process6FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:elseif condition=\"!&quot;string&quot;.isEmpty()\">NOT EMPTY<c:break label='lab'/></c:elseif></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process7FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:else>NOT EMPTY<c:break label='lab'/></c:else></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process8FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition=\"!&quot;string&quot;.isEmpty()\" label=','>NOT EMPTY</c:if></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    private boolean hasErr(String html_, StringMap<String> _files) {
        return hasCommErr(html_, _files);
    }
}
