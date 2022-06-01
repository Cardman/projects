package code.formathtml;


import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class RenderWhileTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i&lt;=2'>{i}<br/><c:set value='i++'/><c:continue/></c:while></body></html>";
        assertEq("<html><body>0<br/>1<br/>2<br/></body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i&gt;=2'>{i}<br/><c:set value='i++'/></c:while></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i&lt;2'><c:set className='$var' value='j=0'/><c:while condition='j&lt;2'>{i}-{j}<br/><c:set value='j++'/></c:while><br/><c:set value='i++'/></c:while></body></html>";
        assertEq("<html><body>0-0<br/>0-1<br/><br/>1-0<br/>1-1<br/><br/></body></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i&gt;=2/0'>{i}<br/><c:set value='i++'/></c:while></body></html>";
        assertNotNull(getEx(html_, new StringMap<String>()));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i&gt;=2/(i-1)'>{i}<br/><c:set value='i++'/></c:while></body></html>";
        assertNotNull(getEx(html_, new StringMap<String>()));
    }

    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:bean='bean_one'><body><c:while condition='nb&lt;=2'>{nb}<br/><c:set value='nb++'/></c:while></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb;");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>0<br/>1<br/>2<br/></body></html>", getResOneBean(html_, filesSec_));
    }

    @Test
    public void process8Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html c:bean='bean_one'><body>Loop:<c:while condition='nb&lt;=2'>{nb}<br/><c:set value='nb++'/></c:while></body></html>";
        StringMap<String> filesSec_ = new StringMap<String>();
        StringBuilder file_ = new StringBuilder();
        file_.append("$public $class pkg.BeanOne:code.bean.Bean{");
        file_.append(" $public $int nb;");
        file_.append("}");
        filesSec_.put("my_file",file_.toString());
        assertEq("<html><body>Loop:0<br/>1<br/>2<br/></body></html>", getResOneBean(html_, filesSec_));
    }
    @Test
    public void process9Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i&lt;=2' label='lab'>{i}<br/><c:set value='i++'/><c:break label='lab'/></c:while></body></html>";
        assertEq("<html><body>0<br/></body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void processFailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className='$var' value='i=0'/><c:while condition='i&lt;=2' label=','>{i}<br/><c:set value='i++'/></c:while></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    private boolean hasErr(String _html, StringMap<String> _files) {
        return hasCommErr(_html, _files);
    }
    private Struct getEx(String _html, StringMap<String> _files) {
        return getCommEx(_html, _files);
    }

    private String getRes(String _html, StringMap<String> _files) {
        return getCommRes(_html,_files);
    }

    private String getResOneBean(String _html, StringMap<String> _filesSec) {
        return getCommOneBean(_html, _filesSec);
    }
}
