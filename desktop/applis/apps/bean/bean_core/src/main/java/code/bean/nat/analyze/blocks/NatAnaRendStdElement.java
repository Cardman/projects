package code.bean.nat.analyze.blocks;

import code.sml.NatAnalyzingDoc;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatAnaRendStdElement extends NatAnaRendElement {

    public NatAnaRendStdElement(Element _elt, AbstractNatBlockBuilder _b) {
        super(_elt, _b);
    }

    void sdtElement(StringList _list, NatAnalyzingDoc _anaDoc) {
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrBean()));
    }
}
