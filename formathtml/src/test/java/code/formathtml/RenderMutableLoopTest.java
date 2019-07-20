package code.formathtml;

import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class RenderMutableLoopTest extends CommonRender {
    @Test
    public void process1Test() {
        Configuration context_ = contextEl();
        Document documentResult_ = DocumentBuilder.parseSaxNotNullRowCol("<html><c:for className=\"$var\" init=\"i=0\" condition=\"i;&lt;4\" step=\"i;++\">{i;}-<c:if condition=\"i;%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>").getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", documentResult_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>",FormatHtml.getRes(rendDocumentBlock_,context_));
    }
    @Test
    public void process2Test() {
        Configuration context_ = contextEl();
        Document documentResult_ = DocumentBuilder.parseSaxNotNullRowCol("<html><c:for className=\"$var\" init=\"i=0\" condition=\"i;&lt;4\" step=\"i;+=1\">{i;}-<c:if condition=\"i;%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>").getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", documentResult_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>",FormatHtml.getRes(rendDocumentBlock_,context_));
    }
    @Test
    public void process3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+b;.;inst:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$operator< $boolean(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $return a;.;inst<b;.;inst:\n");
        xml_.append("}\n");
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration context_ = contextEl(files_);
        Document documentResult_ = DocumentBuilder.parseSaxNotNullRowCol("<html><c:for className=\"$var\" init=\"i=$new pkg.Ex()\" condition=\"i;&lt;$new pkg.Ex(4)\" step=\"i;++\">{i;inst}-<c:if condition=\"i;inst%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>").getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", documentResult_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>",FormatHtml.getRes(rendDocumentBlock_,context_));
    }
    @Test
    public void process4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+b;.;inst:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$operator< $boolean(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $return a;.;inst<b;.;inst:\n");
        xml_.append("}\n");
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration context_ = contextEl(files_);
        Document documentResult_ = DocumentBuilder.parseSaxNotNullRowCol("<html><c:for className=\"$var\" init=\"i=$new pkg.Ex()\" condition=\"i;&lt;$new pkg.Ex(4)\" step=\"i;+=$new pkg.Ex(1)\">{i;inst}-<c:if condition=\"i;inst%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>").getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", documentResult_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>",FormatHtml.getRes(rendDocumentBlock_,context_));
    }
    @Test
    public void process5Test() {
        Configuration context_ = contextEl();
        Document documentResult_ = DocumentBuilder.parseSaxNotNullRowCol("<html><c:set className=\"$var\" value=\"i=0\"/><c:for init=\"i;.=0\" condition=\"i;.&lt;4\" step=\"i;.++\">{i;.}-<c:if condition=\"i;.%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>").getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", documentResult_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>",FormatHtml.getRes(rendDocumentBlock_,context_));
    }
    @Test
    public void process6Test() {
        Configuration context_ = contextEl();
        Document documentResult_ = DocumentBuilder.parseSaxNotNullRowCol("<html><c:for className=\"$int\" init=\"i=0\" condition=\"i;&lt;4\" step=\"i;++\">{i;}-<c:if condition=\"i;%2==0\">Pair</c:if><c:else>Impair</c:else>-</c:for></html>").getDocument();
        RendDocumentBlock rendDocumentBlock_ = RendBlock.newRendDocumentBlock(context_, "c:", documentResult_);
        context_.getAnalyzing().setEnabledInternVars(false);
        rendDocumentBlock_.buildFctInstructions(context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq("<html>0-Pair-1-Impair-2-Pair-3-Impair-</html>",FormatHtml.getRes(rendDocumentBlock_,context_));
    }
}
