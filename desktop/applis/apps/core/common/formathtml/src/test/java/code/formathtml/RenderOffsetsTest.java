package code.formathtml;

import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.*;
import code.formathtml.common.RendBlockUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringMap;
import org.junit.Test;

public final class RenderOffsetsTest extends CommonRender {
    @Test
    public void process1Test() {
        String file_ = "";
        file_ += "<html/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(6,firstChild_.getEndHeader());
    }
    @Test
    public void process2Test() {
        String file_ = "";
        file_ += "<html>";
        file_ += "</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(12,firstChild_.getEndHeader());
    }
    @Test
    public void process3Test() {
        String file_ = "";
        file_ += "<html> ";
        file_ += "</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendEmptyText);
        assertEq(6,firstChild_.getFirstChild().getOffset());
        assertEq(7,firstChild_.getFirstChild().getEndHeader());
    }
    @Test
    public void process4Test() {
        String file_ = "";
        file_ += "<html>_";
        file_ += "</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendText);
        assertEq(6,firstChild_.getFirstChild().getOffset());
        assertEq(7,firstChild_.getFirstChild().getEndHeader());
    }
    @Test
    public void process5Test() {
        String file_ = "";
        file_ += "<html> _ ";
        file_ += "</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendText);
        assertEq(6,firstChild_.getFirstChild().getOffset());
        assertEq(9,firstChild_.getFirstChild().getEndHeader());
    }
    @Test
    public void process6Test() {
        String file_ = "";
        file_ += "<html>";
        file_ += "<body>";
        file_ += "</body>";
        file_ += "</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendStdElement);
        assertEq(7,firstChild_.getFirstChild().getOffset());
        assertEq(18,firstChild_.getFirstChild().getEndHeader());
    }
    @Test
    public void process7Test() {
        String file_ = "";
        file_ += "<html>";
        file_ += "<body/>";
        file_ += "</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendStdElement);
        assertEq(7,firstChild_.getFirstChild().getOffset());
        assertEq(12,firstChild_.getFirstChild().getEndHeader());
    }
    @Test
    public void process8Test() {
        String file_ = "";
        file_ += "<html>_<body/>";
        file_ += "</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendText);
        assertEq(6,firstChild_.getFirstChild().getOffset());
        assertEq(7,firstChild_.getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getNextSibling() instanceof AnaRendStdElement);
        assertEq(8,firstChild_.getFirstChild().getNextSibling().getOffset());
        assertEq(13,firstChild_.getFirstChild().getNextSibling().getEndHeader());
    }
    @Test
    public void process9Test() {
        String file_ = "";
        file_ += "<html><body/>_";
        file_ += "</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendStdElement);
        assertEq(7,firstChild_.getFirstChild().getOffset());
        assertEq(12,firstChild_.getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getNextSibling() instanceof AnaRendText);
        assertEq(13,firstChild_.getFirstChild().getNextSibling().getOffset());
        assertEq(14,firstChild_.getFirstChild().getNextSibling().getEndHeader());
    }
    @Test
    public void process10Test() {
        String file_ = "";
        file_ += "<html><body>_";
        file_ += "</body></html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendStdElement);
        assertEq(7,firstChild_.getFirstChild().getOffset());
        assertEq(11,firstChild_.getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild() instanceof AnaRendText);
        assertEq(12,firstChild_.getFirstChild().getFirstChild().getOffset());
        assertEq(13,firstChild_.getFirstChild().getFirstChild().getEndHeader());
    }
    @Test
    public void process11Test() {
        String file_ = "";
        file_ += "<html><body>_";
        file_ += "</body>_</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendStdElement);
        assertEq(7,firstChild_.getFirstChild().getOffset());
        assertEq(11,firstChild_.getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild() instanceof AnaRendText);
        assertEq(12,firstChild_.getFirstChild().getFirstChild().getOffset());
        assertEq(13,firstChild_.getFirstChild().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getNextSibling() instanceof AnaRendText);
        assertEq(20,firstChild_.getFirstChild().getNextSibling().getOffset());
        assertEq(21,firstChild_.getFirstChild().getNextSibling().getEndHeader());
    }
    @Test
    public void process12Test() {
        String file_ = "";
        file_ += "<html>_<body>_";
        file_ += "</body></html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendText);
        assertEq(6,firstChild_.getFirstChild().getOffset());
        assertEq(7,firstChild_.getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getNextSibling() instanceof AnaRendStdElement);
        assertEq(8,firstChild_.getFirstChild().getNextSibling().getOffset());
        assertEq(12,firstChild_.getFirstChild().getNextSibling().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getNextSibling().getFirstChild() instanceof AnaRendText);
        assertEq(13,firstChild_.getFirstChild().getNextSibling().getFirstChild().getOffset());
        assertEq(14,firstChild_.getFirstChild().getNextSibling().getFirstChild().getEndHeader());
    }
    @Test
    public void process13Test() {
        String file_ = "";
        file_ += "<html><body><span>_";
        file_ += "</span></body>_</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendStdElement);
        assertEq(7,firstChild_.getFirstChild().getOffset());
        assertEq(11,firstChild_.getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild() instanceof AnaRendStdElement);
        assertEq(13,firstChild_.getFirstChild().getFirstChild().getOffset());
        assertEq(17,firstChild_.getFirstChild().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getFirstChild() instanceof AnaRendText);
        assertEq(18,firstChild_.getFirstChild().getFirstChild().getFirstChild().getOffset());
        assertEq(19,firstChild_.getFirstChild().getFirstChild().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getNextSibling() instanceof AnaRendText);
        assertEq(33,firstChild_.getFirstChild().getNextSibling().getOffset());
        assertEq(34,firstChild_.getFirstChild().getNextSibling().getEndHeader());
    }
    @Test
    public void process14Test() {
        String file_ = "";
        file_ += "<html><body><span>_";
        file_ += "</span>_</body>_</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendStdElement);
        assertEq(7,firstChild_.getFirstChild().getOffset());
        assertEq(11,firstChild_.getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild() instanceof AnaRendStdElement);
        assertEq(13,firstChild_.getFirstChild().getFirstChild().getOffset());
        assertEq(17,firstChild_.getFirstChild().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getFirstChild() instanceof AnaRendText);
        assertEq(18,firstChild_.getFirstChild().getFirstChild().getFirstChild().getOffset());
        assertEq(19,firstChild_.getFirstChild().getFirstChild().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getNextSibling() instanceof AnaRendText);
        assertEq(26,firstChild_.getFirstChild().getFirstChild().getNextSibling().getOffset());
        assertEq(27,firstChild_.getFirstChild().getFirstChild().getNextSibling().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getNextSibling() instanceof AnaRendText);
        assertEq(34,firstChild_.getFirstChild().getNextSibling().getOffset());
        assertEq(35,firstChild_.getFirstChild().getNextSibling().getEndHeader());
    }
    @Test
    public void process15Test() {
        String file_ = "";
        file_ += "<html><body><span>_";
        file_ += "</span><div>_</div></body>_</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendStdElement);
        assertEq(7,firstChild_.getFirstChild().getOffset());
        assertEq(11,firstChild_.getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild() instanceof AnaRendStdElement);
        assertEq(13,firstChild_.getFirstChild().getFirstChild().getOffset());
        assertEq(17,firstChild_.getFirstChild().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getFirstChild() instanceof AnaRendText);
        assertEq(18,firstChild_.getFirstChild().getFirstChild().getFirstChild().getOffset());
        assertEq(19,firstChild_.getFirstChild().getFirstChild().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getNextSibling() instanceof AnaRendStdElement);
        assertEq(27,firstChild_.getFirstChild().getFirstChild().getNextSibling().getOffset());
        assertEq(30,firstChild_.getFirstChild().getFirstChild().getNextSibling().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getNextSibling().getFirstChild() instanceof AnaRendText);
        assertEq(31,firstChild_.getFirstChild().getFirstChild().getNextSibling().getFirstChild().getOffset());
        assertEq(32,firstChild_.getFirstChild().getFirstChild().getNextSibling().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getNextSibling() instanceof AnaRendText);
        assertEq(45,firstChild_.getFirstChild().getNextSibling().getOffset());
        assertEq(46,firstChild_.getFirstChild().getNextSibling().getEndHeader());
    }
    @Test
    public void process15_1Test() {
        String file_ = "";
        file_ += "<html><body><span>_";
        file_ += "<span/></span><div>_</div></body>_</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendStdElement);
        assertEq(7,firstChild_.getFirstChild().getOffset());
        assertEq(11,firstChild_.getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild() instanceof AnaRendStdElement);
        assertEq(13,firstChild_.getFirstChild().getFirstChild().getOffset());
        assertEq(17,firstChild_.getFirstChild().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getFirstChild() instanceof AnaRendText);
        assertEq(18,firstChild_.getFirstChild().getFirstChild().getFirstChild().getOffset());
        assertEq(19,firstChild_.getFirstChild().getFirstChild().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getFirstChild().getNextSibling() instanceof AnaRendStdElement);
        assertEq(20,firstChild_.getFirstChild().getFirstChild().getFirstChild().getNextSibling().getOffset());
        assertEq(25,firstChild_.getFirstChild().getFirstChild().getFirstChild().getNextSibling().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getNextSibling() instanceof AnaRendStdElement);
        assertEq(34,firstChild_.getFirstChild().getFirstChild().getNextSibling().getOffset());
        assertEq(37,firstChild_.getFirstChild().getFirstChild().getNextSibling().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getNextSibling().getFirstChild() instanceof AnaRendText);
        assertEq(38,firstChild_.getFirstChild().getFirstChild().getNextSibling().getFirstChild().getOffset());
        assertEq(39,firstChild_.getFirstChild().getFirstChild().getNextSibling().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getNextSibling() instanceof AnaRendText);
        assertEq(52,firstChild_.getFirstChild().getNextSibling().getOffset());
        assertEq(53,firstChild_.getFirstChild().getNextSibling().getEndHeader());
    }
    @Test
    public void process15_2Test() {
        String file_ = "";
        file_ += "<html><body><span>_";
        file_ += "<span/></span>_<div>_</div></body>_</html>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(5,firstChild_.getEndHeader());
        assertTrue(firstChild_.getFirstChild() instanceof AnaRendStdElement);
        assertEq(7,firstChild_.getFirstChild().getOffset());
        assertEq(11,firstChild_.getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild() instanceof AnaRendStdElement);
        assertEq(13,firstChild_.getFirstChild().getFirstChild().getOffset());
        assertEq(17,firstChild_.getFirstChild().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getFirstChild() instanceof AnaRendText);
        assertEq(18,firstChild_.getFirstChild().getFirstChild().getFirstChild().getOffset());
        assertEq(19,firstChild_.getFirstChild().getFirstChild().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getFirstChild().getNextSibling() instanceof AnaRendStdElement);
        assertEq(20,firstChild_.getFirstChild().getFirstChild().getFirstChild().getNextSibling().getOffset());
        assertEq(25,firstChild_.getFirstChild().getFirstChild().getFirstChild().getNextSibling().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getNextSibling() instanceof AnaRendText);
        assertEq(33,firstChild_.getFirstChild().getFirstChild().getNextSibling().getOffset());
        assertEq(34,firstChild_.getFirstChild().getFirstChild().getNextSibling().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getNextSibling().getNextSibling() instanceof AnaRendStdElement);
        assertEq(35,firstChild_.getFirstChild().getFirstChild().getNextSibling().getNextSibling().getOffset());
        assertEq(38,firstChild_.getFirstChild().getFirstChild().getNextSibling().getNextSibling().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getFirstChild().getNextSibling().getNextSibling().getFirstChild() instanceof AnaRendText);
        assertEq(39,firstChild_.getFirstChild().getFirstChild().getNextSibling().getNextSibling().getFirstChild().getOffset());
        assertEq(40,firstChild_.getFirstChild().getFirstChild().getNextSibling().getNextSibling().getFirstChild().getEndHeader());
        assertTrue(firstChild_.getFirstChild().getNextSibling() instanceof AnaRendText);
        assertEq(53,firstChild_.getFirstChild().getNextSibling().getOffset());
        assertEq(54,firstChild_.getFirstChild().getNextSibling().getEndHeader());
    }
    @Test
    public void process16Test() {
        String file_ = "";
        file_ += "<html attr='value'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(19,firstChild_.getEndHeader());
        assertEq(1,firstChild_.getAttributeDelimiters().size());
        assertEq(12,firstChild_.getAttributeDelimiters().getVal("attr").getBegin());
        assertEq(17,firstChild_.getAttributeDelimiters().getVal("attr").getEnd());
    }
    @Test
    public void process17Test() {
        String file_ = "";
        file_ += "<html attr='val&lt;ue'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(23,firstChild_.getEndHeader());
        assertEq(1,firstChild_.getAttributeDelimiters().size());
        assertEq(12,firstChild_.getAttributeDelimiters().getVal("attr").getBegin());
        assertEq(21,firstChild_.getAttributeDelimiters().getVal("attr").getEnd());
    }
    @Test
    public void process18Test() {
        String file_ = "";
        file_ += "<html attr1='val&lt;ue' attr2='value'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(1,firstChild_.getOffset());
        assertEq(38,firstChild_.getEndHeader());
        assertEq(2,firstChild_.getAttributeDelimiters().size());
        assertEq(13,firstChild_.getAttributeDelimiters().getVal("attr1").getBegin());
        assertEq(22,firstChild_.getAttributeDelimiters().getVal("attr1").getEnd());
        assertEq(31,firstChild_.getAttributeDelimiters().getVal("attr2").getBegin());
        assertEq(36,firstChild_.getAttributeDelimiters().getVal("attr2").getEnd());
    }
    @Test
    public void process19Test() {
        String file_ = " ";
        file_ += "<html/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        AnaRendBlock firstChild_ = doc_.getFirstChild();
        assertTrue(firstChild_ instanceof AnaRendStdElement);
        assertEq(2,firstChild_.getOffset());
        assertEq(7,firstChild_.getEndHeader());
    }
    @Test
    public void process20Test() {
        String file_ = "";
        file_ += "<html attr='val&lt;ue'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        assertEq(1,doc_.getEscapedChar().size());
        assertEq(15,doc_.getEscapedChar().getKey(0));
        assertEq(3,doc_.getEscapedChar().getValue(0));
    }
    @Test
    public void process21Test() {
        String file_ = "";
        file_ += "<html attr='val&lt;ue'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        assertEq(14, RendBlockUtil.retrieve(14, doc_.getEscapedChar()));
    }
    @Test
    public void process22Test() {
        String file_ = "";
        file_ += "<html attr='val&lt;ue'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        assertEq(15, RendBlockUtil.retrieve(15, doc_.getEscapedChar()));
    }
    @Test
    public void process23Test() {
        String file_ = "";
        file_ += "<html attr='val&lt;ue'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        assertEq(19, RendBlockUtil.retrieve(16, doc_.getEscapedChar()));
    }
    @Test
    public void process24Test() {
        String file_ = "";
        file_ += "<html attr='val&lt;ue'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        assertEq(20, RendBlockUtil.retrieve(17, doc_.getEscapedChar()));
    }
    @Test
    public void process25Test() {
        String file_ = "";
        file_ += "<html attr='val&lt;&gt;ue'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        assertEq(19, RendBlockUtil.retrieve(16, doc_.getEscapedChar()));
    }
    @Test
    public void process26Test() {
        String file_ = "";
        file_ += "<html attr='val&lt;&gt;ue'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        assertEq(23, RendBlockUtil.retrieve(17, doc_.getEscapedChar()));
    }
    @Test
    public void process27Test() {
        String file_ = "";
        file_ += "<html attr='val&#60;&#62;ue'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        assertEq(15, RendBlockUtil.retrieve(15, doc_.getEscapedChar()));
    }
    @Test
    public void process28Test() {
        String file_ = "";
        file_ += "<html attr='val&#60;&#62;ue'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        assertEq(20, RendBlockUtil.retrieve(16, doc_.getEscapedChar()));
    }
    @Test
    public void process29Test() {
        String file_ = "";
        file_ += "<html attr='val&#60;&#62;ue'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        assertEq(25, RendBlockUtil.retrieve(17, doc_.getEscapedChar()));
    }
    @Test
    public void process30Test() {
        String file_ = "";
        file_ += "<html attr='val&inexist;ue'/>";
        AnaRendDocumentBlock doc_ = newRendDocumentBlock(file_);
        assertEq(24, RendBlockUtil.retrieve(24, doc_.getEscapedChar()));
    }
    @Test
    public void adjust1() {
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry("","");
        AnaRendBlock.adjust(m_);
        assertEq(1,m_.size());
        assertEq("",m_.firstValue());
    }
    @Test
    public void adjust2() {
        StringMap<StringMap<String>> l_ = new StringMap<StringMap<String>>();
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry("","");
        l_.addEntry("", m_);
        AnaRendBlock.adjustMap(l_);
        assertEq(1,l_.size());
        assertEq(1,l_.firstValue().size());
        assertEq("",l_.firstValue().firstValue());
    }
    private static AnaRendDocumentBlock newRendDocumentBlock(String _docText) {
        DualNavigationContext a_ = buildNav();
        Document doc_ = DocumentBuilder.parseSaxNotNullRowCol(_docText).getDocument();
        return AnaRendDocumentBlock.newRendDocumentBlock("c:", doc_, _docText, a_.getDualAnalyzedContext().getStds().getPrimTypes(), "page1.html",new AnalyzingDoc());
    }
}
