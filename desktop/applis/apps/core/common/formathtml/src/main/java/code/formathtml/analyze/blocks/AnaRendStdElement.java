package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendStdElement extends AnaRendElement {
    AnaRendStdElement(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    public StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        AnaRendBlock par_ = this;
        while (par_ != null) {
            if (par_ instanceof AnaRendMessage || par_ instanceof AnaRendImport) {
                return new StringList();
            }
            par_ = par_.getParent();
        }
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrBean()));
        return list_;
    }
}
