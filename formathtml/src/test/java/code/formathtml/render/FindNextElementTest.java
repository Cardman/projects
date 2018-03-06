package code.formathtml.render;

import static code.formathtml.EquallableExUtil.assertEq;

import org.junit.Test;

import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.util.EqList;
import code.util.IdMap;

@SuppressWarnings("static-method")
public class FindNextElementTest {

    @Test
    public void next1Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello World");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        FindNextElement f_ = new FindNextElement(out_);
        f_.next("o");
        IdMap<MetaSearchableLabel, EqList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(1, segs_.size());
        assertEq(1, segs_.firstValue().size());
        assertEq(4, segs_.firstValue().first().getBegin());
        assertEq(5, segs_.firstValue().first().getEnd());
    }

    @Test
    public void next2Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello World");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        FindNextElement f_ = new FindNextElement(out_);
        f_.next("o");
        f_.next("o");
        IdMap<MetaSearchableLabel, EqList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(1, segs_.size());
        assertEq(2, segs_.firstValue().size());
        assertEq(4, segs_.firstValue().first().getBegin());
        assertEq(5, segs_.firstValue().first().getEnd());
        assertEq(7, segs_.firstValue().get(1).getBegin());
        assertEq(8, segs_.firstValue().get(1).getEnd());
    }
    @Test
    public void next3Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello World");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        FindNextElement f_ = new FindNextElement(out_);
        f_.next("a");
        IdMap<MetaSearchableLabel, EqList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(0, segs_.size());
    }
    @Test
    public void next4Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello<a>World</a>");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        FindNextElement f_ = new FindNextElement(out_);
        f_.next("o");
        f_.next("o");
        IdMap<MetaSearchableLabel, EqList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(2, segs_.size());
        assertEq(1, segs_.firstValue().size());
        assertEq(4, segs_.firstValue().first().getBegin());
        assertEq(5, segs_.firstValue().first().getEnd());
        assertEq(1, segs_.lastValue().size());
        assertEq(1, segs_.lastValue().first().getBegin());
        assertEq(2, segs_.lastValue().first().getEnd());
    }
    @Test
    public void next5Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello<br/>World");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        FindNextElement f_ = new FindNextElement(out_);
        f_.next("o");
        f_.next("o");
        IdMap<MetaSearchableLabel, EqList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(1, segs_.size());
        assertEq(1, segs_.firstValue().size());
        assertEq(1, segs_.firstValue().first().getBegin());
        assertEq(2, segs_.firstValue().first().getEnd());
    }
    @Test
    public void next6Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello<a>World</a>");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        FindNextElement f_ = new FindNextElement(out_);
        f_.next("o");
        f_.next("o");
        f_.next("o");
        IdMap<MetaSearchableLabel, EqList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(0, segs_.size());
    }
    @Test
    public void next7Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello<a>World</a>");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        FindNextElement f_ = new FindNextElement(out_);
        f_.next("loWorld");
        IdMap<MetaSearchableLabel, EqList<SegmentPart>> segs_ = f_.getSegments();
        assertEq(2, segs_.size());
        assertEq(1, segs_.firstValue().size());
        assertEq(3, segs_.firstValue().first().getBegin());
        assertEq(5, segs_.firstValue().first().getEnd());
        assertEq(1, segs_.lastValue().size());
        assertEq(0, segs_.lastValue().first().getBegin());
        assertEq(5, segs_.lastValue().first().getEnd());
    }
    @Test
    public void next8Test() {
        StringBuilder doc_ = new StringBuilder();
        doc_.append("<html>\n");
        doc_.append("<body>\n");
        doc_.append("Hello<a>World</a>Everybody");
        doc_.append("</body>\n");
        doc_.append("</html>");
        DocumentResult res_ = DocumentBuilder.newDocumentBuilder().parse(doc_.toString());
        MetaDocument out_ = MetaDocument.newInstance(res_.getDocument());
        FindNextElement f_ = new FindNextElement(out_);
        f_.next("loWorldEvery");
        IdMap<MetaSearchableLabel, EqList<SegmentPart>> segs_ = f_.getSegments();
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
    }
}
