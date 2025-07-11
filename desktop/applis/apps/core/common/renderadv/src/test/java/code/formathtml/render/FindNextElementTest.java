package code.formathtml.render;

import code.sml.RendKeyWordsGroup;
import code.util.*;
import org.junit.Test;

import code.sml.DocumentBuilder;
import code.sml.DocumentResult;

public final class FindNextElementTest extends EquallableRenderAdvUtil {

    @Test
    public void next1Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello World");
        doc_.append("</body>\n");
        doc_.append("</html>");
        MetaDocument out_ = getMetaDocument(doc_);
        FindNextElement f_ = find(out_);
        f_.next("o");
        IdMap<MetaSearchableContent, CustList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(1, segs_.size());
        assertEq(1, segs_.firstValue().size());
        assertEq(4, segs_.firstValue().first().getBegin());
        assertEq(5, segs_.firstValue().first().getEnd());
        assertNotNull(f_.getLabel());
    }

    @Test
    public void next2Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello World");
        doc_.append("</body>\n");
        doc_.append("</html>");
        MetaDocument out_ = getMetaDocument(doc_);
        FindNextElement f_ = find(out_);
        f_.next("o");
        f_.next("o");
        IdMap<MetaSearchableContent, CustList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(1, segs_.size());
        assertEq(2, segs_.firstValue().size());
        assertEq(4, segs_.firstValue().first().getBegin());
        assertEq(5, segs_.firstValue().first().getEnd());
        assertEq(7, segs_.firstValue().get(1).getBegin());
        assertEq(8, segs_.firstValue().get(1).getEnd());
        assertNotNull(f_.getLabel());
    }
    @Test
    public void next3Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello World");
        doc_.append("</body>\n");
        doc_.append("</html>");
        MetaDocument out_ = getMetaDocument(doc_);
        FindNextElement f_ = find(out_);
        f_.next("a");
        IdMap<MetaSearchableContent, CustList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(0, segs_.size());
        assertNull(f_.getLabel());
    }
    @Test
    public void next4Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello<a>World</a>");
        doc_.append("</body>\n");
        doc_.append("</html>");
        MetaDocument out_ = getMetaDocument(doc_);
        FindNextElement f_ = find(out_);
        f_.next("o");
        f_.next("o");
        IdMap<MetaSearchableContent, CustList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(2, segs_.size());
        assertEq(1, segs_.firstValue().size());
        assertEq(4, segs_.firstValue().first().getBegin());
        assertEq(5, segs_.firstValue().first().getEnd());
        assertEq(1, segs_.lastValue().size());
        assertEq(1, segs_.lastValue().first().getBegin());
        assertEq(2, segs_.lastValue().first().getEnd());
        assertNotNull(f_.getLabel());
    }
    @Test
    public void next5Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello<br/>World");
        doc_.append("</body>\n");
        doc_.append("</html>");
        MetaDocument out_ = getMetaDocument(doc_);
        FindNextElement f_ = find(out_);
        f_.next("o");
        f_.next("o");
        IdMap<MetaSearchableContent, CustList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(1, segs_.size());
        assertEq(1, segs_.firstValue().size());
        assertEq(1, segs_.firstValue().first().getBegin());
        assertEq(2, segs_.firstValue().first().getEnd());
        assertNotNull(f_.getLabel());
    }
    @Test
    public void next6Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello<a>World</a>");
        doc_.append("</body>\n");
        doc_.append("</html>");
        MetaDocument out_ = getMetaDocument(doc_);
        FindNextElement f_ = find(out_);
        f_.next("o");
        f_.next("o");
        f_.next("o");
        IdMap<MetaSearchableContent, CustList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(0, segs_.size());
        assertNull(f_.getLabel());
    }
    @Test
    public void next7Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello<a>World</a>");
        doc_.append("</body>\n");
        doc_.append("</html>");
        MetaDocument out_ = getMetaDocument(doc_);
        FindNextElement f_ = find(out_);
        f_.next("loWorld");
        IdMap<MetaSearchableContent, CustList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(2, segs_.size());
        assertEq(1, segs_.firstValue().size());
        assertEq(3, segs_.firstValue().first().getBegin());
        assertEq(5, segs_.firstValue().first().getEnd());
        assertEq(1, segs_.lastValue().size());
        assertEq(0, segs_.lastValue().first().getBegin());
        assertEq(5, segs_.lastValue().first().getEnd());
        assertNotNull(f_.getLabel());
    }
    @Test
    public void next8Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello<a>World</a>Everybody");
        doc_.append("</body>\n");
        doc_.append("</html>");
        MetaDocument out_ = getMetaDocument(doc_);
        FindNextElement f_ = find(out_);
        f_.next("loWorldEvery");
        IdMap<MetaSearchableContent, CustList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(3, segs_.size());
        assertEq(1, segs_.firstValue().size());
        assertEq(3, segs_.firstValue().first().getBegin());
        assertEq(5, segs_.firstValue().first().getEnd());
        assertEq(1, segs_.values().get(1).size());
        assertEq(0, segs_.values().get(1).first().getBegin());
        assertEq(5, segs_.values().get(1).first().getEnd());
        assertEq(1, segs_.lastValue().size());
        assertEq(0, segs_.lastValue().first().getBegin());
        assertEq(5, segs_.lastValue().first().getEnd());
        assertNotNull(f_.getLabel());
    }
    @Test
    public void next9Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("</body>\n");
        doc_.append("</html>");
        MetaDocument out_ = getMetaDocument(doc_);
        FindNextElement f_ = find(out_);
        f_.next("loWorldEvery");
        IdMap<MetaSearchableContent, CustList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(0, segs_.size());
        assertNull(f_.getLabel());
    }
    @Test
    public void next10Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("<pre>\n");
        doc_.append("</pre>\n");
        doc_.append("</body>\n");
        doc_.append("</html>");
        MetaDocument out_ = getMetaDocument(doc_);
        FindNextElement f_ = find(out_);
        f_.next("");
        IdMap<MetaSearchableContent, CustList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(1, segs_.size());
        assertNotNull(f_.getLabel());
    }
    @Test
    public void next11Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello World<hr/>Everybody");
        doc_.append("</body>\n");
        doc_.append("</html>");
        MetaDocument out_ = getMetaDocument(doc_);
        FindNextElement f_ = find(out_);
        f_.next("loWorldEvery");
        IdMap<MetaSearchableContent, CustList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(0, segs_.size());
        assertNull(f_.getLabel());
    }
    @Test
    public void next12Test() {
        IdList<MetaSearchableContent> parts_ = new IdList<MetaSearchableContent>();
        parts_.add(new MetaSearchableContent("ta",0,0,0));
        parts_.add(new MetaSearchableContent("ta",1,0,0));
        FindNextElement f_ = new FindNextElement(parts_);
        f_.next("t");
        assertEq(1, f_.getSegments().size());
        f_.next("t");
        assertEq(1, f_.getSegments().size());
    }
    private static MetaDocument getMetaDocument(StringBuilder _doc) {
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(_doc.toString());
        return MetaDocument.newInstance(res_.getDocument(), new RendKeyWordsGroup(),"ABCDEF",new SampleCharacterCaseConverter());
    }

    private FindNextElement find(MetaDocument _o) {
        return new FindNextElement(_o.getTxtParts());
    }

}
