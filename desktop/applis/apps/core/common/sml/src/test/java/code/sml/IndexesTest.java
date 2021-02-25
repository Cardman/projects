package code.sml;

import code.sml.indexes.DocumentIndexer;
import code.sml.indexes.ElementOffsetsNext;
import code.util.Ints;
import code.util.*;
import code.util.StringMap;
import org.junit.Test;

public class IndexesTest extends EquallableRowColUtil {


    @Test
    public void getIndexes1Test() {
        String html_ = "<tag>&#233;</tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Ints indexes_ = DocumentIndexer.getIndexes(node_);
        assertEq(0, indexes_.size());
    }

    @Test
    public void getIndexes2Test() {
        String html_ = "<tag>&#233;</tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Ints indexes_ = DocumentIndexer.getIndexes(node_.getFirstChild());
        assertEq(1, indexes_.size());
        assertEq(0, indexes_.get(0));
    }

    @Test
    public void getIndexes3Test() {
        String html_ = "<tag>&#233;<ta>&#234;</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Ints indexes_ = DocumentIndexer.getIndexes(node_.getFirstChild().getNextSibling().getFirstChild());
        assertEq(2, indexes_.size());
        assertEq(1, indexes_.get(0));
        assertEq(0, indexes_.get(1));
    }

    @Test
    public void getIndexes4Test() {
        String html_ = "<tag><inner/><after/></tag>";
        Document doc_ = DocumentBuilder.parseNoTextDocument(html_);
        Element node_ = doc_.getDocumentElement();
        Ints indexes_ = DocumentIndexer.getIndexes(node_.getFirstChild());
        assertEq(1, indexes_.size());
        assertEq(0, indexes_.get(0));
    }

    @Test
    public void getIndexOfNodeOrAttribute1Test() {
        String html_ = "<tag>\u00E9<ta>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getFirstChild();
        assertEq(10, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute2Test() {
        String html_ = "<tag>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getFirstChild();
        assertEq(12, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute3Test() {
        String html_ = "<tag>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling();
        assertEq(9, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute4Test() {
        String html_ = "<tag>233<tag>234</tag></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling();
        assertEq(9, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute5Test() {
        String html_ = "<tag><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getNextSibling().getFirstChild();
        assertEq(17, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute6Test() {
        String html_ = "<tag><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getNextSibling();
        assertEq(14, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute7Test() {
        String html_ = "<tag><ta where=\"here\">Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getNextSibling().getNextSibling();
        assertEq(43, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute8Test() {
        String html_ = "<tag><ta where=\"here\">Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild();
        assertEq(9, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, "where"));
    }

    @Test
    public void getIndexOfNodeOrAttribute9Test() {
        String html_ = "<tag><ta where=\"h'ere\" when='n\"ow'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild();
        assertEq(9, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, "where"));
    }

    @Test
    public void getIndexOfNodeOrAttribute10Test() {
        String html_ = "<tag><ta where=\"h'ere\" when='n\"ow'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild();
        assertEq(23, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, "when"));
    }

    @Test
    public void getIndexOfNodeOrAttribute11Test() {
        String html_ = "<tag>233<ta>&amp;</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getFirstChild();
        assertEq(12, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute12Test() {
        String html_ = "<tag>233<ta>&#38;</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getFirstChild();
        assertEq(12, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute13Test() {
        String html_ = "<tag>\u00E9<ta>\u00E9</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getFirstChild();
        assertEq(10, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute14Test() {
        String html_ = "<tag>233<ta>233</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getFirstChild();
        assertEq(12, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute15Test() {
        String html_ = "<tag>233<ta> 234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling();
        assertEq(9, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute16Test() {
        String html_ = "<tag>233<ta><a/></ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getFirstChild();
        assertEq(13, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute17Test() {
        String html_ = "<tag>233<ta></ta><a/></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getNextSibling();
        assertEq(18, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }

    @Test
    public void getIndexOfNodeOrAttribute18Test() {
        String html_ = "<tag>233<ta></ta><a/></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        assertEq(1, DocumentIndexer.getIndexOfNodeOrAttribute(html_, node_, ""));
    }


    @Test
    public void getIndexOfNodeOrAttribute19Test() {
        String html_ = "<tag><ta where=\"here\">Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getNextSibling().getNextSibling();
        assertEq(43, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, "", true));
    }

    @Test
    public void getIndexOfNodeOrAttribute20Test() {
        String html_ = "<tag><ta where=\"here\">Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild();
        assertEq(16, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, "where", true));
    }

    @Test
    public void getIndexOfNodeOrAttribute21Test() {
        String html_ = "<tag><ta where=\"h'ere\" when='n\"ow'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild();
        assertEq(16, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, "where", true));
    }

    @Test
    public void getIndexOfNodeOrAttribute22Test() {
        String html_ = "<tag><ta where=\"h'ere\" when='n\"ow'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild();
        assertEq(29, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, "when", true));
    }

    @Test
    public void getIndexOfNodeOrAttribute23Test() {
        String html_ = "<tag><ta when='n\"ow' where=\"h'ere\">Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild();
        assertEq(28, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, "where", true));
    }

    @Test
    public void getIndexOfNodeOrAttribute24Test() {
        String html_ = "<tag><ta when='n\"ow' where=\"h'ere\">Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild();
        assertEq(34, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, "how", true));
    }
    @Test
    public void getIndexOfNodeOrAttribute25Test() {
        String html_ = "<tag>\u00E9<ta>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getFirstChild();
        assertEq(10, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }
    @Test
    public void getIndexOfNodeOrAttribute26Test() {
        String html_ = "<tag>\u00E9<ta>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling();
        assertEq(7, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, ""));
    }
    @Test
    public void getIndexOfNodeOrAttribute27Test() {
        String html_ = "<tag>\u00E9<ta myattr='value'>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling();
        assertEq(10, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, "myattr"));
    }
    @Test
    public void getIndexOfNodeOrAttribute28Test() {
        String html_ = "<tag>\u00E9<ta myattr='value'>\u00EA</ta><sta myattr=\"value\">\u00EA</sta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling().getNextSibling();
        assertEq(44, DocumentIndexer.getIndexOfNodeOrAttribute(html_, n_, "myattr", true));
    }

    @Test
    public void getRowColOfString1Test() {
        String str_ = "simple";
        RowCol rc_ = DocumentIndexer.getRowColOfString(str_, 1, 4);
        assertEq(1, rc_.getRow());
        assertEq(2, rc_.getCol());
    }

    @Test
    public void getRowColOfString2Test() {
        String str_ = "first line\nsecond line";
        RowCol rc_ = DocumentIndexer.getRowColOfString(str_, 11, 4);
        assertEq(2, rc_.getRow());
        assertEq(1, rc_.getCol());
    }

    @Test
    public void getRowColOfString3Test() {
        String str_ = "first line\nsecond line";
        RowCol rc_ = DocumentIndexer.getRowColOfString(str_, 10, 4);
        assertEq(2, rc_.getRow());
        assertEq(0, rc_.getCol());
    }

    @Test
    public void getRowColOfString4Test() {
        String str_ = "first line\n\tsecond line";
        RowCol rc_ = DocumentIndexer.getRowColOfString(str_, 12, 4);
        assertEq(2, rc_.getRow());
        assertEq(5, rc_.getCol());
    }

    @Test
    public void getRowColOfString5Test() {
        String str_ = "\tfirst line\nsecond line";
        RowCol rc_ = DocumentIndexer.getRowColOfString(str_, 3, 4);
        assertEq(1, rc_.getRow());
        assertEq(7, rc_.getCol());
    }

    @Test
    public void getRowColOfString6Test() {
        String str_ = "first line\nsecond line";
        RowCol rc_ = DocumentIndexer.getRowColOfString(str_, 3, 4);
        assertEq(1, rc_.getRow());
        assertEq(4, rc_.getCol());
    }

    @Test
    public void getIndexesOfElementOrAttribute1Test() {
        String html_ = "<tag>\u00E9<ta>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(1);
        rc_.setCol(2);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(1);
        rc_.setCol(6);
        assertEq(rc_, e_.getEndHeader());
        assertEq(6, e_.getNextElt());
//        assertEq(0, e_.getNextEltLineReturn());
    }

    @Test
    public void getIndexesOfElementOrAttribute2Test() {
        String html_ = "<tag>\n\u00E9<ta>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(1);
        rc_.setCol(2);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(1);
        rc_.setCol(6);
        assertEq(rc_, e_.getEndHeader());
        assertEq(7, e_.getNextElt());
//        assertEq(5, e_.getNextEltLineReturn());
    }


    @Test
    public void getIndexesOfElementOrAttribute3Test() {
        String html_ = " <tag>\n\u00E9<ta>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_.trim());
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(1);
        rc_.setCol(3);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(1);
        rc_.setCol(7);
        assertEq(rc_, e_.getEndHeader());
        assertEq(8, e_.getNextElt());
//        assertEq(6, e_.getNextEltLineReturn());
    }


    @Test
    public void getIndexesOfElementOrAttribute4Test() {
        String html_ = "<tag>\n\u00E9<ta>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(2);
        rc_.setCol(3);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(6);
        assertEq(rc_, e_.getEndHeader());
        assertEq(-1, e_.getNextElt());
//        assertEq(0, e_.getNextEltLineReturn());
    }


    @Test
    public void getIndexesOfElementOrAttribute5Test() {
        String html_ = " <tag>\n\u00E9<ta>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_.trim());
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(2);
        rc_.setCol(3);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(6);
        assertEq(rc_, e_.getEndHeader());
        assertEq(-1, e_.getNextElt());
//        assertEq(0, e_.getNextEltLineReturn());
    }
    @Test
    public void getIndexesOfElementOrAttribute6Test() {
        String html_ = "<tag a='o'>\u00E9<ta>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(2, m_.size());
        rc_.setRow(1);
        rc_.setCol(2);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(1);
        rc_.setCol(9);
        assertEq(rc_, m_.getVal("a"));
        rc_.setRow(1);
        rc_.setCol(12);
        assertEq(rc_, e_.getEndHeader());
        assertEq(12, e_.getNextElt());
//        assertEq(0, e_.getNextEltLineReturn());
    }
    @Test
    public void getIndexesOfElementOrAttribute7Test() {
        String html_ = "<tag><ta where=\"h'ere\" when='n\"ow'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(3, m_.size());
        rc_.setRow(1);
        rc_.setCol(7);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(1);
        rc_.setCol(17);
        assertEq(rc_, m_.getVal("where"));
        rc_.setRow(1);
        rc_.setCol(30);
        assertEq(rc_, m_.getVal("when"));
        rc_.setRow(1);
        rc_.setCol(36);
        assertEq(rc_, e_.getEndHeader());
        assertEq(47, e_.getNextElt());
//        assertEq(0, e_.getNextEltLineReturn());
    }

    @Test
    public void getIndexesOfElementOrAttribute8Test() {
        String html_ = "<tag>\u00E9<ta myattr='value'>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(2, m_.size());
        rc_.setRow(1);
        rc_.setCol(8);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(1);
        rc_.setCol(19);
        assertEq(rc_, m_.getVal("myattr"));
        rc_.setRow(1);
        rc_.setCol(26);
        assertEq(rc_, e_.getEndHeader());
        assertEq(-1, e_.getNextElt());
//        assertEq(0, e_.getNextEltLineReturn());
    }

    @Test
    public void getIndexesOfElementOrAttribute9Test() {
        String html_ = "<tag>\u00E9<ta myattr='value'>\u00EA</ta><sta myattr=\"value\">\u00EA</sta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(2, m_.size());
        rc_.setRow(1);
        rc_.setCol(33);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(1);
        rc_.setCol(45);
        assertEq(rc_, m_.getVal("myattr"));
        rc_.setRow(1);
        rc_.setCol(52);
        assertEq(rc_, e_.getEndHeader());
        assertEq(-1, e_.getNextElt());
//        assertEq(0, e_.getNextEltLineReturn());
    }

    @Test
    public void getIndexesOfElementOrAttribute10Test() {
        String html_ = "<tag>\n<ta where=\"h'ere\" when='n\"ow'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(3, m_.size());
        rc_.setRow(2);
        rc_.setCol(2);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(12);
        assertEq(rc_, m_.getVal("where"));
        rc_.setRow(2);
        rc_.setCol(25);
        assertEq(rc_, m_.getVal("when"));
        rc_.setRow(2);
        rc_.setCol(31);
        assertEq(rc_, e_.getEndHeader());
        assertEq(48, e_.getNextElt());
//        assertEq(0, e_.getNextEltLineReturn());
    }

    @Test
    public void getIndexesOfElementOrAttribute11Test() {
        String html_ = "\n<tag>\u00E9<ta>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_.trim());
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(2);
        rc_.setCol(2);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(6);
        assertEq(rc_, e_.getEndHeader());
        assertEq(7, e_.getNextElt());
//        assertEq(0, e_.getNextEltLineReturn());
    }

    @Test
    public void getIndexesOfElementOrAttribute12Test() {
        String html_ = "\n<tag>\n\u00E9<ta>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_.trim());
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(2);
        rc_.setCol(2);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(6);
        assertEq(rc_, e_.getEndHeader());
        assertEq(8, e_.getNextElt());
//        assertEq(5, e_.getNextEltLineReturn());
    }


    @Test
    public void getIndexesOfElementOrAttribute13Test() {
        String html_ = "\n <tag>\n\u00E9<ta>\u00EA</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_.trim());
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(2);
        rc_.setCol(3);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(7);
        assertEq(rc_, e_.getEndHeader());
        assertEq(9, e_.getNextElt());
//        assertEq(6, e_.getNextEltLineReturn());
    }

    @Test
    public void getIndexesOfElementOrAttribute14Test() {
        String html_ = "<tag>\n\t<ta where=\"h'ere\" when='n\"ow'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(3, m_.size());
        rc_.setRow(2);
        rc_.setCol(6);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(16);
        assertEq(rc_, m_.getVal("where"));
        rc_.setRow(2);
        rc_.setCol(29);
        assertEq(rc_, m_.getVal("when"));
        rc_.setRow(2);
        rc_.setCol(35);
        assertEq(rc_, e_.getEndHeader());
        assertEq(49, e_.getNextElt());
//        assertEq(0, e_.getNextEltLineReturn());
    }

    @Test
    public void getIndexesOfElementOrAttribute15Test() {
        String html_ = "<tag>\n\t<ta where=\"h\n'ere\" when='n\"ow'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(3, m_.size());
        rc_.setRow(2);
        rc_.setCol(6);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(16);
        assertEq(rc_, m_.getVal("where"));
        rc_.setRow(3);
        rc_.setCol(13);
        assertEq(rc_, m_.getVal("when"));
        rc_.setRow(3);
        rc_.setCol(19);
        assertEq(rc_, e_.getEndHeader());
        assertEq(50, e_.getNextElt());
        assertEq(2, e_.getOffsets().size());
        assertEq(0, e_.getOffsets().getVal("when").size());
        assertEq(1, e_.getOffsets().getVal("where").size());
        assertEq(1, e_.getOffsets().getVal("where").get(0));
//        assertEq(0, e_.getNextEltLineReturn());
    }

    @Test
    public void getIndexesOfElementOrAttribute16Test() {
        String html_ = "<tag>\n\t<ta where=\"h\n\t'ere\" when='n\"ow'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(3, m_.size());
        rc_.setRow(2);
        rc_.setCol(6);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(16);
        assertEq(rc_, m_.getVal("where"));
        rc_.setRow(3);
        rc_.setCol(17);
        assertEq(rc_, m_.getVal("when"));
        rc_.setRow(3);
        rc_.setCol(23);
        assertEq(rc_, e_.getEndHeader());
        assertEq(51, e_.getNextElt());
        assertEq(2, e_.getOffsets().size());
        assertEq(0, e_.getOffsets().getVal("when").size());
        assertEq(1, e_.getOffsets().getVal("where").size());
        assertEq(1, e_.getOffsets().getVal("where").get(0));
        assertEq(2, e_.getTabs().size());
        assertEq(0, e_.getTabs().getVal("when").size());
        assertEq(1, e_.getTabs().getVal("where").size());
        assertEq(2, e_.getTabs().getVal("where").get(0));
//        assertEq(0, e_.getNextEltLineReturn());
    }

    @Test
    public void getIndexesOfElementOrAttribute17Test() {
        String html_ = "<ta>\n\t<tag where=\"h\n\t'ere\" when='n\"ow'>Content</tag><ta/>233<ta>234</ta></ta>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(3, m_.size());
        rc_.setRow(2);
        rc_.setCol(6);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(17);
        assertEq(rc_, m_.getVal("where"));
        rc_.setRow(3);
        rc_.setCol(17);
        assertEq(rc_, m_.getVal("when"));
        rc_.setRow(3);
        rc_.setCol(23);
        assertEq(rc_, e_.getEndHeader());
        assertEq(52, e_.getNextElt());
        assertEq(2, e_.getOffsets().size());
        assertEq(0, e_.getOffsets().getVal("when").size());
        assertEq(1, e_.getOffsets().getVal("where").size());
        assertEq(1, e_.getOffsets().getVal("where").get(0));
        assertEq(2, e_.getTabs().size());
        assertEq(0, e_.getTabs().getVal("when").size());
        assertEq(1, e_.getTabs().getVal("where").size());
        assertEq(2, e_.getTabs().getVal("where").get(0));
//        assertEq(0, e_.getNextEltLineReturn());
    }

    @Test
    public void getIndexesOfElementOrAttribute18Test() {
        String html_ = "<ta>\n\t<ta where=\"h\n\t'ere\" when='n\"ow'>Content</ta><tag/>233<ta>234</ta></ta>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(3, m_.size());
        rc_.setRow(2);
        rc_.setCol(6);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(16);
        assertEq(rc_, m_.getVal("where"));
        rc_.setRow(3);
        rc_.setCol(17);
        assertEq(rc_, m_.getVal("when"));
        rc_.setRow(3);
        rc_.setCol(23);
        assertEq(rc_, e_.getEndHeader());
        assertEq(50, e_.getNextElt());
        assertEq(2, e_.getOffsets().size());
        assertEq(0, e_.getOffsets().getVal("when").size());
        assertEq(1, e_.getOffsets().getVal("where").size());
        assertEq(1, e_.getOffsets().getVal("where").get(0));
        assertEq(2, e_.getTabs().size());
        assertEq(0, e_.getTabs().getVal("when").size());
        assertEq(1, e_.getTabs().getVal("where").size());
        assertEq(2, e_.getTabs().getVal("where").get(0));
//        assertEq(0, e_.getNextEltLineReturn());
    }

    @Test
    public void getIndexesOfElementOrAttribute19Test() {
        String html_ = "<ta>\n\t<ta>Content</ta><tag/>233<ta>234</ta></ta>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(2);
        rc_.setCol(6);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(9);
        assertEq(rc_, e_.getEndHeader());
        assertEq(22, e_.getNextElt());
        assertEq(0, e_.getOffsets().size());
        assertEq(0, e_.getTabs().size());
    }

    @Test
    public void getIndexesOfElementOrAttribute20Test() {
        String html_ = "<ta>\n\t<tag>Content</tag><ta>5</ta>233<tag>234</tag></ta>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(2);
        rc_.setCol(6);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(2);
        rc_.setCol(10);
        assertEq(rc_, e_.getEndHeader());
        assertEq(24, e_.getNextElt());
        assertEq(0, e_.getOffsets().size());
        assertEq(0, e_.getTabs().size());
    }

    @Test
    public void getIndexesOfElementOrAttribute21Test() {
        String html_ = "<tag><ta>Content</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(1);
        rc_.setCol(2);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(1);
        rc_.setCol(6);
        assertEq(rc_, e_.getEndHeader());
        assertEq(5, e_.getNextElt());
        assertEq(0, e_.getOffsets().size());
        assertEq(0, e_.getTabs().size());
    }

    @Test
    public void getIndexesOfElementOrAttribute22Test() {
        String html_ = "<ta><tag>Content</tag></ta>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(1);
        rc_.setCol(2);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(1);
        rc_.setCol(5);
        assertEq(rc_, e_.getEndHeader());
        assertEq(4, e_.getNextElt());
        assertEq(0, e_.getOffsets().size());
        assertEq(0, e_.getTabs().size());
    }

    @Test
    public void getIndexesOfElementOrAttribute23Test() {
        String html_ = "<ta>\t<tag>Content</tag></ta>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        n_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(1);
        rc_.setCol(10);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(1);
        rc_.setCol(14);
        assertEq(rc_, e_.getEndHeader());
        assertEq(-1, e_.getNextElt());
        assertEq(0, e_.getOffsets().size());
        assertEq(0, e_.getTabs().size());
    }

    @Test
    public void getIndexesOfElementOrAttribute24Test() {
        String html_ = "<ta>\t<ta/></ta>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_;
        RowCol rc_ = new RowCol();
        rc_.setRow(0);
        rc_.setCol(0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, new ElementOffsetsNext(rc_, 0, 0), (Element) n_, 4);
        StringMap<RowCol> m_ = e_.getAttributes();
        assertEq(1, m_.size());
        rc_.setRow(1);
        rc_.setCol(2);
        assertEq(rc_, m_.getVal(""));
        rc_.setRow(1);
        rc_.setCol(5);
        assertEq(rc_, e_.getEndHeader());
        assertEq(5, e_.getNextElt());
        assertEq(0, e_.getOffsets().size());
        assertEq(0, e_.getTabs().size());
    }

    @Test
    public void getSpecialChars1Test() {
        String html_ = "<tag>\n\t<ta where=\"h&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        Node n_ = node_.getFirstChild().getNextSibling();
        StringMap<IntTreeMap<Integer>> t_ = DocumentIndexer.getSpecialChars(html_, (Element) n_);
        assertEq(2, t_.size());
        IntTreeMap< Integer> aOne_ = t_.getVal("where");
        assertEq(2, aOne_.size());
        assertEq(5, aOne_.getVal(1));
        assertEq(7, aOne_.getVal(11));
        IntTreeMap< Integer> aTwo_ = t_.getVal("when");
        assertEq(2, aTwo_.size());
        assertEq(5, aTwo_.getVal(4));
        assertEq(7, aTwo_.getVal(12));
    }

    @Test
    public void getOffset1Test() {
        String html_ = "<tag>\n\t<ta where=\"h&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("", e_.getAttributes(), s_, 0, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(2, rc_.getRow());
        assertEq(6, rc_.getCol());
        assertEq(8, e_.getBegin());
    }

    @Test
    public void getOffset2Test() {
        String html_ = "<tag>\n\t<ta where=\"h&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 0, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(2, rc_.getRow());
        assertEq(16, rc_.getCol());
    }

    @Test
    public void getOffset3Test() {
        String html_ = "<tag>\n\t<ta where=\"h&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 1, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(2, rc_.getRow());
        assertEq(17, rc_.getCol());
    }

    @Test
    public void getOffset4Test() {
        String html_ = "<tag>\n\t<ta where=\"h&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 2, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(2, rc_.getRow());
        assertEq(23, rc_.getCol());
    }

    @Test
    public void getOffset5Test() {
        String html_ = "<tag>\n\t<ta where=\"h&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 3, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(2, rc_.getRow());
        assertEq(24, rc_.getCol());
    }

    @Test
    public void getOffset6Test() {
        String html_ = "<tag>\n\t<ta where=\"h&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 6, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(2, rc_.getRow());
        assertEq(27, rc_.getCol());
    }


    @Test
    public void getOffset7Test() {
        String html_ = "<tag>\n\t<ta where=\"h&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 7, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(2, rc_.getRow());
        assertEq(35, rc_.getCol());
    }


    @Test
    public void getOffset8Test() {
        String html_ = "<tag>\n\t<ta where=\"\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 0, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(0, rc_.getCol());
    }

    @Test
    public void getOffset9Test() {
        String html_ = "<tag>\n\t<ta where=\"\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 1, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(1, rc_.getCol());
    }

    @Test
    public void getOffset10Test() {
        String html_ = "<tag>\n\t<ta where=\"\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 2, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(2, rc_.getCol());
    }

    @Test
    public void getOffset11Test() {
        String html_ = "<tag>\n\t<ta where=\"\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 3, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(8, rc_.getCol());
    }

    @Test
    public void getOffset12Test() {
        String html_ = "<tag>\n\t<ta where=\"\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 6, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(11, rc_.getCol());
    }


    @Test
    public void getOffset13Test() {
        String html_ = "<tag>\n\t<ta where=\"\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 7, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(12, rc_.getCol());
    }

    @Test
    public void getOffset14Test() {
        String html_ = "<tag>\n\t<ta where=\"\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 8, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(20, rc_.getCol());
    }


    @Test
    public void getOffset15Test() {
        String html_ = "<tag>\n\t<ta where=\"a\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 0, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(2, rc_.getRow());
        assertEq(16, rc_.getCol());
    }

    @Test
    public void getOffset16Test() {
        String html_ = "<tag>\n\t<ta where=\"a\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 1, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(0, rc_.getCol());
    }

    @Test
    public void getOffset17Test() {
        String html_ = "<tag>\n\t<ta where=\"a\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 2, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(1, rc_.getCol());
    }

    @Test
    public void getOffset18Test() {
        String html_ = "<tag>\n\t<ta where=\"a\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 3, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(2, rc_.getCol());
    }

    @Test
    public void getOffset19Test() {
        String html_ = "<tag>\n\t<ta where=\"a\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 6, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(10, rc_.getCol());
    }


    @Test
    public void getOffset20Test() {
        String html_ = "<tag>\n\t<ta where=\"a\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 7, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(11, rc_.getCol());
    }

    @Test
    public void getOffset21Test() {
        String html_ = "<tag>\n\t<ta where=\"a\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 8, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(12, rc_.getCol());
    }

    @Test
    public void getOffset22Test() {
        String html_ = "<tag>\n\t<ta where=\"a\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 4, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(8, rc_.getCol());
    }

    @Test
    public void getOffset23Test() {
        String html_ = "<tag>\n\t<ta where=\"a\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 9, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(20, rc_.getCol());
    }

    @Test
    public void getOffset24Test() {
        String html_ = "<tag>\n\t<ta where=\"a\nh&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("how", e_.getAttributes(), s_, 0, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(3, rc_.getRow());
        assertEq(58, rc_.getCol());
    }

    @Test
    public void getOffset25Test() {
        String html_ = "<tag>\n<ta where=\"\th&quot;'ere&eacute;ccent\" when='n\"ow&apos;sp&#40908;ace'>Content</ta><ta/>233<ta>234</ta></tag>";
        Document doc_ = DocumentBuilder.parseSax(html_);
        Node node_ = doc_.getDocumentElement();
        ElementOffsetsNext init_ = new ElementOffsetsNext(new RowCol(), 0, 0);
        ElementOffsetsNext e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, init_, (Element) node_, 4);
        node_ = node_.getFirstChild().getNextSibling();
        e_ = DocumentIndexer.getIndexesOfElementOrAttribute(html_, e_, (Element) node_, 4);
        StringMap<IntTreeMap<Integer>> s_;
        s_ = DocumentIndexer.getSpecialChars(html_, (Element) node_);
        RowCol rc_ = getOffset("where", e_.getAttributes(), s_, 1, e_.getOffsets(), e_.getTabs(), e_.getEndHeader(), 4);
        assertEq(2, rc_.getRow());
        assertEq(16, rc_.getCol());
    }
    private static RowCol getOffset(
            String _attribute,
            StringMap<RowCol> _attributes,
            StringMap<IntTreeMap<Integer>> _specialChars,
            int _offset,
            StringMap<Ints> _offsets,
            StringMap<Ints> _tabs,
            RowCol _endHeader,
            int _tabWidth) {
        if (!_attributes.contains(_attribute)) {
            return _endHeader;
        }
        return DocumentIndexer.getOffset(_attribute, _attributes, _specialChars, _offset, _offsets, _tabs, _tabWidth);
    }

}
