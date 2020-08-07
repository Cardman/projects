package code.formathtml;

import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderMutableLoopTest extends CommonRender {
    @Test
    public void process1Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"i&lt;4\" step=\"i++\">{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }

    @Test
    public void process2Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"i&lt;4\" step=\"i+=1\">{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+b.inst;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$operator< $boolean(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $return a.inst<b.inst;\n");
        xml_.append("}\n");
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration context_ = contextElFive(files_);
        String html_ = "<html><c:for className=\"$var\" init=\"i=$new pkg.Ex()\" condition=\"i&lt;$new pkg.Ex(4)\" step=\"i++\">{i.inst}-<c:if condition=\"i.inst%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+b.inst;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$operator< $boolean(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $return a.inst<b.inst;\n");
        xml_.append("}\n");
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex();\n");
        xml_.append(" o.inst = a.inst+1;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration context_ = contextElFive(files_);
        String html_ = "<html><c:for className=\"$var\" init=\"i=$new pkg.Ex()\" condition=\"i&lt;$new pkg.Ex(4)\" step=\"i+=$new pkg.Ex(1)\">{i.inst}-<c:if condition=\"i.inst%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process5Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:set className=\"$var\" value=\"i=0\"/><c:for init=\"i=0\" condition=\"i&lt;4\" step=\"i++\">{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process6Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$int\" init=\"i=0\" condition=\"i&lt;4\" step=\"i++\">{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process7Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:set className='$int' value='j'/><c:for className=\"$var\" init=\"i=0\" condition=\"j&lt;4\" step=\"\">{j}-<c:if condition=\"j%2==0\">Pair</c:if><c:else>Impair</c:else>-<c:set value='j++'/></c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process8Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:set className='$int' value='j'/><c:for className=\"$var\" init=\"\" condition=\"j&lt;4\" step=\"j++\">{j}-<c:if condition=\"j%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process9Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$int\" init=\"i=0\" condition=\"i&gt;4\" step=\"i++\">{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html/>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process10Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$int\" init=\"i=0\" condition=\"i&lt;2\" step=\"i++\"><c:for className=\"$int\" init=\"j=0\" condition=\"j&lt;2\" step=\"j++\">{i}-{j}-</c:for>+</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-0-0-1-+1-0-1-1-+</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process11Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$int\" init=\"i=1/0\" condition=\"i&lt;4\" step=\"i++\">{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void process12Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$int\" init=\"i=0\" condition=\"i&lt;4/0\" step=\"i++\">{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void process13Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$int\" init=\"i=0\" condition=\"i&lt;4\" step=\"i/=0\">{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void process14Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$int\" init=\"i=0\" condition=\"i&gt;4/(i-1)\" step=\"i++\">{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_,context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void process15Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"\" step=\"i++\"><c:if condition='i&gt;=4'><c:break/></c:if>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process16Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"\" step=\"i++\" label='lab'><c:if condition='i&gt;=4'><c:break label='lab'/></c:if>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process17Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"\" step=\"i++\" label='lab'><c:try><c:if condition='i&gt;=4'><c:break label='lab'/></c:if>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:try><c:finally>Finally-<c:if condition='i&gt;=4'><c:break label='lab'/></c:if></c:finally></c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-Finally-1-Impair-Finally-2-Pair-Finally-3-Impair-Finally-Finally-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process18Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"\" step=\"i++\" label='lab'><c:try><c:if condition='i&gt;=4'><c:break label='lab'/></c:if>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:try><c:catch>Catch-<c:if condition='i&gt;=4'><c:break label='lab'/></c:if></c:catch></c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process19Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"\" step=\"i++\" label='lab'><c:try><c:if condition='i&gt;=4'><c:break label='lab'/></c:if>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:try><c:finally>Finally-</c:finally></c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-Finally-1-Impair-Finally-2-Pair-Finally-3-Impair-Finally-Finally-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process20Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"\" step=\"i++\" label='lab'><c:try><c:if condition='i&gt;=4'><td><c:break label='lab'/></td></c:if>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:try><c:finally>Finally-</c:finally></c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-Finally-1-Impair-Finally-2-Pair-Finally-3-Impair-Finally-<td/>Finally-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process21Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"i&lt;4\" step=\"i++\">{i}-<c:if condition=\"i%2==0\">Pair-<c:continue/></c:if>Impair-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process22Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"i&lt;4\" step=\"i++\" label='label'>{i}-<c:if condition=\"i%2==0\">Pair-<c:continue label='label'/></c:if>Impair-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process23Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$int\" init=\"i=0\" condition=\"i&lt;2\" step=\"i++\" label='label'><c:for className=\"$int\" init=\"j=0\" condition=\"j&lt;2\" step=\"j++\">{i}-{j}-<c:if condition=\"i%2==0\">=<c:continue label='label'/></c:if></c:for>+</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-0-=1-0-1-1-+</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process24Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"i&lt;4\" step=\"i++\" label='label'>{i}-<c:if condition=\"i%2==0\">Pair-<c:try>Cont-<c:continue label='label'/></c:try><c:catch/></c:if>Impair-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-Cont-1-Impair-2-Pair-Cont-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process25Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"i&lt;4\" step=\"i++\" label='label'>{i}-<c:if condition=\"i%2==0\">Pair-<c:try>Cont-<c:continue label='label'/></c:try><c:finally>Finally-</c:finally></c:if>Impair-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-Cont-Finally-1-Impair-2-Pair-Cont-Finally-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }

    @Test
    public void process26Test() {
        Configuration context_ = contextElFive(new StringMap<String>());
        String html_ = "<html><c:set className='$int' value='j'/><c:for className=\"$var\" init=\"i=0\" condition=\"j&lt;4\" step=\"\">{j}-<c:if condition=\"j%2==0\">Pair</c:if><c:else>Impair</c:else>-<c:set value='j++'/></c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process27Test() {
        Configuration context_ = contextElFive(new StringMap<String>());
        String html_ = "<html><c:set className='$int' value='j'/><c:for className=\"$var\" init=\"\" condition=\"j&lt;4\" step=\"j++\">{j}-<c:if condition=\"j%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process28Test() {
        Configuration context_ = contextElFive(new StringMap<String>());
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"\" step=\"i++\"><c:if condition='i&gt;=4'><c:break/></c:if>{i}-<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process29Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:set className=\"$int\" value=\"i,j\"/><c:for init=\"i=0,j=0\" condition=\"i&lt;4\" step=\"i++,j+=2\">{i}-{j}--<c:if condition=\"i%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-0--Pair-1-2--Impair-2-4--Pair-3-6--Impair-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }

    @Test
    public void process30Test() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=4\" condition=\"i&gt;0\" step=\"i--\">{([i])}-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>0-1-2-3-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{");
        xml_.append(" $public $int v = 1;");
        xml_.append(" $public $static $boolean $(Ex e){");
        xml_.append("  $return e.v <= 4;");
        xml_.append(" }");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration context_ = contextElFive(files_);
        String html_ = "<html><c:for className=\"$var\" init=\"i=$new pkg.Ex()\" condition=\"i\" step=\"i.v++\">{i.v}-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>1-2-3-4-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{");
        xml_.append(" $public $int v = 1;");
        xml_.append(" $public $static $boolean $true(Ex e){");
        xml_.append("  $return e.v <= 4;");
        xml_.append(" }");
        xml_.append(" $public $static $boolean $false(Ex e){");
        xml_.append("  $return e.v > 4;");
        xml_.append(" }");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration context_ = contextElFive(files_);
        String html_ = "<html><c:for className=\"$var\" init=\"i=$new pkg.Ex()\" condition=\"i\" step=\"i.v++\">{i.v}-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html>1-2-3-4-</html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process1FailTest() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"i&lt;4\" step=\"i++\">{i}-<c:if condition=\"i%2==0\">Pair-<c:try>Cont-<c:continue label='label'/></c:try><c:finally>Finally-</c:finally></c:if>Impair-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process2FailTest() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"i&lt;4\" step=\"i++\">{i}-<c:if condition=\"i%2==0\">Pair-<c:try>Cont-<c:break label='label'/></c:try><c:finally>Finally-</c:finally></c:if>Impair-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process3FailTest() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"4\" step=\"i++\" label='label'>{i}-<c:if condition=\"i%2==0\">Pair-<c:try>Cont-<c:continue label='label'/></c:try><c:finally>Finally-</c:finally></c:if>Impair-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process4FailTest() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" indexClassName='java.lang.String' condition=\"4\" step=\"i++\" label='label'>{i}-<c:if condition=\"i%2==0\">Pair-<c:try>Cont-<c:continue label='label'/></c:try><c:finally>Finally-</c:finally></c:if>Impair-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process5FailTest() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"i&lt;4\" step=\"i++\" label='#t'>{i}-<c:if condition=\"i%2==0\">Pair-<c:try>Cont-<c:continue label='label'/></c:try><c:finally>Finally-</c:finally></c:if>Impair-</c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process6FailTest() {
        Configuration context_ = contextElFive();
        String html_ = "<html><c:for className=\"$var\" init=\"i=0\" condition=\"i&lt;4\" step=\"i++\" label='t'><c:for className=\"$var\" init=\"j=0\" condition=\"j;&lt;4\" step=\"j;++\" label='t'>{i}-</c:for></c:for></html>";
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
}
