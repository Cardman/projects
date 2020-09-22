package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.util.AnalyzingDoc;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.StringList;

public final class RendStdElement extends RendElement {
    RendStdElement(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (StringList.quickEq(_read.getTagName(),StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getKeyWordParam()))) {
            _list.clear();
        }
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrBean()));
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {

    }
}
