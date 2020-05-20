package code.formathtml;

import code.util.StringMap;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class RenderForEachTableTest extends CommonRender {
    private static final String CUST_ITER_PATH = "pkg/CustIter";
    private static final String CUST_ITER_TABLE_PATH = "pkg/CustIterTable";
    private static final String CUST_LIST_PATH = "pkg/CustList";
    private static final String CUST_PAIR_PATH = "pkg/CustPair";
    private static final String CUST_TABLE_PATH = "pkg/CustTable";

    @Test
    public void process1Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$var\" value=\"v\" varClassName=\"$var\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process2Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,$null)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$var\" value=\"v\" varClassName=\"$int\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_, context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void process3Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add($null,1)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$int\" value=\"v\" varClassName=\"$int\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_, context_);
        assertNotNull(getException(context_));
    }


    @Test
    public void process4Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPairFailFirst());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(1,1)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$int\" value=\"v\" varClassName=\"$int\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_, context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void process5Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPairFailSecond());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(1,1)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$int\" value=\"v\" varClassName=\"$int\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_, context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void process6Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTableFailHas());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(1,1)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$int\" value=\"v\" varClassName=\"$int\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_, context_);
        assertNotNull(getException(context_));
    }

    @Test
    public void process7Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTableFailNext());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(1,1)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$int\" value=\"v\" varClassName=\"$int\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_, context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void process8Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTableFail());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(1,1)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$int\" value=\"v\" varClassName=\"$int\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_, context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void process9Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;\" value=\"inst\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$int\" value=\"v\" varClassName=\"$int\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_, context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void process10Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTableFailBis());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$int\" value=\"v\" varClassName=\"$int\" map=\"$new pkg.CustTable&lt;java.lang.Integer,java.lang.Integer&gt;()\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        RendBlock.getRes(rendDocumentBlock_, context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void process11Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$var\" value=\"v\" varClassName=\"$var\" map=\"(pkg.CustTable&lt;?java.lang.String,?java.lang.Integer&gt;)inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process12Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$var\" value=\"v\" varClassName=\"$var\" map=\"(pkg.CustTable&lt;!java.lang.String,!java.lang.Integer&gt;)inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process13Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$var\" value=\"v\" varClassName=\"$var\" map=\"(pkg.CustTable&lt;?,?&gt;)inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }

    @Test
    public void process14Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for key=\"k\" value=\"v\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td></tr></table></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }

    @Test
    public void process15Test() {
        String locale_ = "en";
        String folder_ = "messages";
        String relative_ = "sample/file";
        String content_ = "one=Description one\ntwo=Description two\nthree=desc &lt;{0}&gt;";
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for key=\"k\" value=\"v\" map=\"inst\">");
        xml_.append("<c:for key=\"l\" value=\"w\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td><td>{l}</td><td>{w}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        StringMap<String> files_ = new StringMap<String>();
        files_.put(EquallableExUtil.formatFile(folder_,locale_,relative_), content_);
        StringMap<String> filesSec_ = new StringMap<String>();
        filesSec_.put(CUST_ITER_PATH, getCustomIterator());
        filesSec_.put(CUST_LIST_PATH, getCustomList());
        filesSec_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        filesSec_.put(CUST_TABLE_PATH, getCustomTable());
        filesSec_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration conf_ = contextElFive(filesSec_);
        
        conf_.setMessagesFolder(folder_);
        conf_.setProperties(new StringMap<String>());
        conf_.getProperties().put("msg_example", relative_);
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, conf_);
        assertTrue(conf_.isEmptyErrors());
        assertEq("<html><body><table><tr><td>ONE</td><td>1</td><td>ONE</td><td>1</td></tr><tr><td>ONE</td><td>1</td><td>TWO</td><td>2</td></tr><tr><td>TWO</td><td>2</td><td>ONE</td><td>1</td></tr><tr><td>TWO</td><td>2</td><td>TWO</td><td>2</td></tr></table></body></html>", RendBlock.getRes(rendDocumentBlock_,conf_));
        assertNull(getException(conf_));
    }
    @Test
    public void process16Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$int\" value=\"v\" varClassName=\"$int\" map=\"$new pkg.CustTable&lt;&gt;()\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq("<html><body><table/></body></html>", RendBlock.getRes(rendDocumentBlock_,context_));
        assertNull(getException(context_));
    }
    @Test
    public void process1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"$var\" value=\"v\" varClassName=\"$var\" map=\"$null\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"java.lang.Integer\" value=\"v\" varClassName=\"java.lang.String\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{v}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process3FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"java.lang.Integer\" value=\"k\" varClassName=\"java.lang.String\" map=\"inst\">");
        xml_.append("<tr><td>{k}</td><td>{k}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"java.lang.Integer\" value=\"k\" varClassName=\"java.lang.String\" map=\"0\">");
        xml_.append("<tr><td>{k}</td><td>{k}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for key=\"#k\" keyClassName=\"java.lang.Integer\" value=\"#v\" varClassName=\"java.lang.String\" map=\"0\">");
        xml_.append("<tr><td>{k}</td><td>{k}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process6FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for key=\"k\" keyClassName=\"java.lang.Integer\" value=\"v\" varClassName=\"java.lang.String\" map=\"0\">");
        xml_.append("<c:for key=\"k\" keyClassName=\"java.lang.Integer\" value=\"v\" varClassName=\"java.lang.String\" map=\"0\">");
        xml_.append("<tr><td>{k}</td><td>{k}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process7FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for className=\"$var\" init=\"k=0\" condition=\"k;&lt;4\" step=\"k;++\">");
        xml_.append("<c:for key=\"k\" keyClassName=\"java.lang.Integer\" value=\"v\" varClassName=\"java.lang.String\" map=\"0\">");
        xml_.append("<tr><td>{k}</td><td>{k}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process8FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for className=\"$var\" init=\"v=0\" condition=\"v;&lt;4\" step=\"v;++\">");
        xml_.append("<c:for key=\"k\" keyClassName=\"java.lang.Integer\" value=\"v\" varClassName=\"java.lang.String\" map=\"0\">");
        xml_.append("<tr><td>{k}</td><td>{k}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    @Test
    public void process9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        Configuration context_ = contextElFive(files_);
        StringBuilder xml_ = new StringBuilder();
        xml_.append("<html><body><table>");
        xml_.append("<c:set className=\"pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;\" value=\"inst=$new pkg.CustTable&lt;java.lang.String,java.lang.Integer&gt;()\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;ONE&quot;,1)\"/>");
        xml_.append("<c:set value=\"inst.add(&quot;TWO&quot;,2)\"/>");
        xml_.append("<c:for indexClassName='java.lang.String' key=\"k\" keyClassName=\"java.lang.Integer\" value=\"v\" varClassName=\"java.lang.String\" map=\"0\">");
        xml_.append("<tr><td>{k}</td><td>{k}</td></tr>");
        xml_.append("</c:for>");
        xml_.append("</table></body></html>");
        String html_ = xml_.toString();
        RendDocumentBlock rendDocumentBlock_ = buildRendWithoutBean(html_, context_);
        assertTrue(!context_.isEmptyErrors());
    }
    private static String getCustomPairFailFirst() {
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
        xml_.append("  $int i = 1/0;\n");
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
    private static String getCustomPairFailSecond() {
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
        xml_.append("  $int i = 1/0;\n");
        xml_.append("  $return second;\n");
        xml_.append(" }\n");
        xml_.append(" $public $void setFirst(U f){\n");
        xml_.append("  first = f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomTableFail() {
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
        xml_.append("  $int i = 1/0;\n");
        xml_.append("  $return $new CustIterTable<U,V>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomTableFailBis() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustTable<U,V> :$iterableTable<U,V>{\n");
        xml_.append(" $private CustList<CustPair<U,V>> list;\n");
        xml_.append(" $public (){\n");
        xml_.append("  $int i = 1/0;\n");
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
    private static String getCustomIteratorTableFailNext() {
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
        xml_.append("  index/=0;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $boolean hasNextPair(){\n");
        xml_.append("  $return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIteratorTableFailHas() {
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
        xml_.append("  $return index/0<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
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
        xml_.append("$public $class pkg.CustList<#U> :$iterable<#U>{\n");
        xml_.append(" $private #U[] list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new #U[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(#U elt){\n");
        xml_.append("  add(length,elt);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,#U elt){\n");
        xml_.append("  #U[] newlist=$new #U[length+1i];\n");
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
        xml_.append(" $public $normal #U get($int index){\n");
        xml_.append("  $return list[index];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,#U elt){\n");
        xml_.append("  list[index]=elt;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;length-1i;1i){\n");
        xml_.append("   list[i]=list[i+1i];\n");
        xml_.append("  }\n");
        xml_.append("  list[length-1i]=$null;\n");
        xml_.append("  length--;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<#U> iterator(){\n");
        xml_.append("  $return $new pkg.CustIter<#U>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<#T> :$iterator<#T>{\n");
        xml_.append(" $private pkg.CustList<#T> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public (pkg.CustList<#T> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #T next(){\n");
        xml_.append("  #T out=list.get(index);\n");
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
