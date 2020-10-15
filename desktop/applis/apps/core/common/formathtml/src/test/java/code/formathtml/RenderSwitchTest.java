package code.formathtml;



import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderSwitchTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='8'/></c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }

    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='10'/></c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='8'>Text</c:case></c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process4Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='10'>Text</c:case></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process5Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='8'>Text</c:case><c:case value='10'/></c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process6Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='10'>Text</c:case><c:case value='8'/></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process7Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='10'/><c:case value='8'>Text</c:case></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process8Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='8'/><c:case value='10'>Text</c:case></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process9Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:default/></c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process10Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:default>Text</c:default></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process11Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='8'/><c:default>Text</c:default></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process12Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:default>Text</c:default><c:case value='10'/></c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process13Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='10'/><c:default>Text</c:default></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process14Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:case value='\"8\"'/></c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process15Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:case value='\"10\"'/></c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process16Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:case value='\"8\"'>Text</c:case></c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process17Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:case value='\"10\"'>Text</c:case></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process18Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:case value='\"8\"'>Text</c:case><c:case value='\"10\"'/></c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process19Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:case value='\"10\"'>Text</c:case><c:case value='\"8\"'/></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process20Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:case value='\"10\"'/><c:case value='\"8\"'>Text</c:case></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process21Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:case value='\"8\"'/><c:case value='\"10\"'>Text</c:case></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process22Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:default/></c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process23Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:default>Text</c:default></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process24Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:case value='\"8\"'/><c:default>Text</c:default></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process25Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:default>Text</c:default><c:case value='\"10\"'/></c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process26Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='\"10\"'><c:case value='\"10\"'/><c:default>Text</c:default></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process27Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='(java.lang.String)$null'><c:case value='\"10\"'/><c:default>Text</c:default></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process28Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='(java.lang.String)$null'><c:case value='(java.lang.String)$null'/><c:default>Text</c:default></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process29Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='(java.lang.String)$null'><c:case value='(java.lang.String)$null'>Text</c:case></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process30Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='pkg.ExEnum.ONE'><c:case value='TWO'/><c:default>Text</c:default></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>Text</body></html>", getRes(html_, files_));
    }
    @Test
    public void process31Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='pkg.ExEnum.ONE'><c:case value='ONE'>Text</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>Text</body></html>", getRes(html_, files_));
    }
    @Test
    public void process32Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='pkg.ExEnum.ONE'><c:case value='TWO'>Text</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body/></html>", getRes(html_, files_));
    }
    @Test
    public void process33Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='pkg.ExEnum.ONE'><c:default>Text</c:default><c:case value='ONE'/></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body/></html>", getRes(html_, files_));
    }
    @Test
    public void process34Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='pkg.ExEnum.ONE'><c:case value='$null'>Text</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body/></html>", getRes(html_, files_));
    }
    @Test
    public void process35Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='pkg.ExEnum.ONE'><c:case value='TWO'>Text</c:case><c:case value='ONE'>Other</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>Other</body></html>", getRes(html_, files_));
    }
    @Test
    public void process36Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='(pkg.ExEnum)$null'><c:case value='TWO'>Text</c:case><c:case value='ONE'>Other</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body/></html>", getRes(html_, files_));
    }
    @Test
    public void process37Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:default>Text</c:default><c:case value='8'/></c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process38Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='(pkg.ExEnum)$null'><c:case value='TWO'>Text</c:case><c:case value='$null'>Other</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR;");
        enum_.append("$public $int field;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>Other</body></html>", getRes(html_, files_));
    }
    @Test
    public void process39Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='1/0'><c:case value='8'/></c:switch></body></html>";
        assertNotNull(getEx(html_, new StringMap<String>()));
    }

    @Test
    public void process40Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='10'>10<c:break/></c:case><c:case value='8'>8</c:case></c:switch></body></html>";
        assertEq("<html><body>10</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process41Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10' label='label'><c:case value='10'>10<c:break label='label'/></c:case><c:case value='8'>8</c:case></c:switch></body></html>";
        assertEq("<html><body>10</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process42Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'>\n<c:case value='8'>Text</c:case>\n<c:case value='10'/>\n</c:switch></body></html>";
        assertEq("<html><body/></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process43Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'>\n<c:case value='8'/>\n<c:default>Text</c:default>\n</c:switch></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process44Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:if condition='$true' label='myllabel'><c:switch value='10'><c:case value='10'>Text<c:break label='myllabel'/></c:case></c:switch>Not Displayed</c:if></body></html>";
        assertEq("<html><body>Text</body></html>", getRes(html_, new StringMap<String>()));
    }
    @Test
    public void process45Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$new pkg.ExSub()'><c:case className='pkg.ExSub' var='v'>{v.v}</c:case><c:default var='v'>Text</c:default></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExSub:ExAbs{");
        enum_.append("}");
        enum_.append("$public $class pkg.ExSubTwo:ExAbs{");
        enum_.append("}");
        enum_.append("$public $abstract $class pkg.ExAbs{");
        enum_.append(" $public $int v = 10;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>10</body></html>", getRes(html_, files_));
    }
    @Test
    public void process46Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$new pkg.ExSubTwo()'><c:case className='pkg.ExSub' var='v'>{v.v}</c:case><c:default var='v'>{v.v} Text</c:default></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExSub:ExAbs{");
        enum_.append("}");
        enum_.append("$public $class pkg.ExSubTwo:ExAbs{");
        enum_.append("}");
        enum_.append("$public $abstract $class pkg.ExAbs{");
        enum_.append(" $public $int v = 10;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>10 Text</body></html>", getRes(html_, files_));
    }
    @Test
    public void process47Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$new pkg.ExSubTwo()'><c:case className='pkg.ExSub' var='v'>{v.v}</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExSub:ExAbs{");
        enum_.append("}");
        enum_.append("$public $class pkg.ExSubTwo:ExAbs{");
        enum_.append("}");
        enum_.append("$public $abstract $class pkg.ExAbs{");
        enum_.append(" $public $int v = 10;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body/></html>", getRes(html_, files_));
    }
    @Test
    public void process48Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$(pkg.ExAbs)$null'><c:case className='pkg.ExSub' var='v'>{v.v}</c:case><c:default var='v'>Text</c:default></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExSub:ExAbs{");
        enum_.append("}");
        enum_.append("$public $class pkg.ExSubTwo:ExAbs{");
        enum_.append("}");
        enum_.append("$public $abstract $class pkg.ExAbs{");
        enum_.append(" $public $int v = 10;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>Text</body></html>", getRes(html_, files_));
    }
    @Test
    public void process49Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$(pkg.ExAbs)$null'><c:case className='pkg.ExSub' var='v'>{v.v}</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExSub:ExAbs{");
        enum_.append("}");
        enum_.append("$public $class pkg.ExSubTwo:ExAbs{");
        enum_.append("}");
        enum_.append("$public $abstract $class pkg.ExAbs{");
        enum_.append(" $public $int v = 10;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body/></html>", getRes(html_, files_));
    }
    @Test
    public void process50Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$(pkg.ExAbs)$null'><c:case className='pkg.ExSub' var='v'>{v.v}</c:case><c:case value='$null'>Text</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExSub:ExAbs{");
        enum_.append("}");
        enum_.append("$public $class pkg.ExSubTwo:ExAbs{");
        enum_.append("}");
        enum_.append("$public $abstract $class pkg.ExAbs{");
        enum_.append(" $public $int v = 10;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>Text</body></html>", getRes(html_, files_));
    }
    @Test
    public void process51Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$new pkg.ExSub[1]'><c:case className='pkg.ExSub[]' var='v'>{v.length}</c:case><c:default var='v'>Text</c:default></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExSub:ExAbs{");
        enum_.append("}");
        enum_.append("$public $class pkg.ExSubTwo:ExAbs{");
        enum_.append("}");
        enum_.append("$public $abstract $class pkg.ExAbs{");
        enum_.append(" $public $int v = 10;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>1</body></html>", getRes(html_, files_));
    }
    @Test
    public void process52Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$new pkg.ExSubTwo()'><c:case value='$null'></c:case>\n<c:default var='v'>{v.v} Text</c:default></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExSub:ExAbs{");
        enum_.append("}");
        enum_.append("$public $class pkg.ExSubTwo:ExAbs{");
        enum_.append("}");
        enum_.append("$public $abstract $class pkg.ExAbs{");
        enum_.append(" $public $int v = 10;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>10 Text</body></html>", getRes(html_, files_));
    }
    @Test
    public void process53Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$new pkg.ExSub()'><c:case className='pkg.ExSub' var='v'>{v.v}</c:case><c:case className='pkg.ExAbs' var='v'>-{v.v}</c:case><c:default var='v'>Text</c:default></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExSub:ExAbs{");
        enum_.append("}");
        enum_.append("$public $class pkg.ExSubTwo:ExAbs{");
        enum_.append("}");
        enum_.append("$public $abstract $class pkg.ExAbs{");
        enum_.append(" $public $int v = 10;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>10</body></html>", getRes(html_, files_));
    }
    @Test
    public void process54Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='(pkg.ExSub&lt;?&gt;)$new pkg.ExSub&lt;$int&gt;()'><c:case className='pkg.ExSub&lt;?&gt;' var='v'>{v.v}</c:case><c:case className='pkg.ExAbs' var='v'>-{v.v}</c:case><c:default var='v'>Text</c:default></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $final $class pkg.ExSub<T>:ExAbs{");
        enum_.append("}");
        enum_.append("$public $class pkg.ExSubTwo:ExAbs{");
        enum_.append("}");
        enum_.append("$public $abstract $class pkg.ExAbs{");
        enum_.append(" $public $int v = 10;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertEq("<html><body>10</body></html>", getRes(html_, files_));
    }
    private String getRes(String _html, StringMap<String> _files) {
        return getCommRes(_html, _files);
    }

    private Struct getEx(String _html, StringMap<String> _files) {
        return getCommEx(_html, _files);
    }

    @Test
    public void process0FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='\"ONE\"'/></c:switch></body></html>";
        StringMap<String> files_ = new StringMap<String>();
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process1FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='pkg.ExEnum.ONE'/></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR;");
        enum_.append("$public $int field;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void proces2FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='(pkg.ExEnum)$null'><c:case value='2'>Text</c:case><c:case value='$null'>Other</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR;");
        enum_.append("$public $int field;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process3FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='8'/><c:case value='8'/></c:switch></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process4FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:default/><c:default/></c:switch></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process5FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='pkg.ExEnum.ONE'><c:case value='TWO'>Text</c:case><c:case value='TWO'>Other</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process6FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='pkg.ExEnum.ONE'><c:case value='$null'>Text</c:case><c:case value='$null'>Other</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process7FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='pkg.ExEnum.ONE'><c:case value='pkg.ExEnum.res()'>Text</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR;");
        enum_.append("$public $static $int res(){");
        enum_.append(" $return 0;");
        enum_.append("}");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process8FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='pkg.ExEnum.ONE'><c:case value='pkg.ExEnum.res()'>Text</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR;");
        enum_.append("$public $static $void res(){");
        enum_.append("}");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process9FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$null'><c:case value='pkg.ExEnum.res()'>Text</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR;");
        enum_.append("$public $static $void res(){");
        enum_.append("}");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process10FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='0'><c:case value='pkg.ExEnum.res()'>Text</c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR;");
        enum_.append("$public $static $int res(){");
        enum_.append(" $return 0;");
        enum_.append("}");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process11FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:case value='pkg.ExEnum.res()'>Text</c:case></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR;");
        enum_.append("$public $static $int res(){");
        enum_.append(" $return 0;");
        enum_.append("}");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process12FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:default>Text</c:default></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $enum pkg.ExEnum{");
        enum_.append("ONE,TWO,THREE,FOUR;");
        enum_.append("$public $static $int res(){");
        enum_.append(" $return 0;");
        enum_.append("}");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process13FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10'><c:case value='8'/><c:if condition=\"$true\"/></c:switch></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process14FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$new pkg.ExCl()'><c:case value='$null'>Text</c:case></c:switch><c:case value='$null'>Text</c:case></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExCl{");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process15FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='pkg.ExCl.res()'><c:case value='$null'>Text</c:case></c:switch><c:case value='$null'>Text</c:case></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExCl{");
        enum_.append("$public $static $void res(){");
        enum_.append("}");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process16FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:break/></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process17FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:continue/></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    @Test
    public void process18FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$new pkg.ExSub()'><c:default var='1'></c:default><c:case className='pkg.ExSub' var='1'></c:case></c:switch></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExSub:ExAbs{");
        enum_.append("}");
        enum_.append("$public $class pkg.ExSubTwo:ExAbs{");
        enum_.append("}");
        enum_.append("$public $abstract $class pkg.ExAbs{");
        enum_.append(" $public $int v = 10;");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process19FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='$new java.lang.StringBuilder()'><c:case value='$null'>Text</c:case></c:switch><c:case value='$null'>Text</c:case></body></html>";
        StringBuilder enum_ = new StringBuilder();
        enum_.append("$public $class pkg.ExCl{");
        enum_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("ex_enum",enum_.toString());
        assertTrue(hasErr(html_, files_));
    }
    @Test
    public void process20FailTest() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:switch value='10' label=','><c:case value='8'/></c:switch></body></html>";
        assertTrue(hasErr(html_, new StringMap<String>()));
    }
    private boolean hasErr(String _html, StringMap<String> _files) {
        return hasCommErr(_html, _files);
    }
}
