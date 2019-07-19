package code.formathtml.util;
import code.formathtml.RendBlock;
import code.sml.Element;

public interface RendBlockHtml {

    RendBlock getReadNode();

    void setReadNode(RendBlock _readNode);

    Element getWriteNode();

    void setWriteNode(Element _writeNode);
}
