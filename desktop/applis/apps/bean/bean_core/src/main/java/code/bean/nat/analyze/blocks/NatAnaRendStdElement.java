package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatAnalyzingDoc;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatAnaRendStdElement extends NatAnaRendElement {
    public NatAnaRendStdElement(Element _elt) {
        super(_elt);
    }

    void sdtElement(StringList _list, NatAnalyzingDoc _anaDoc) {
        _list.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrBean()));
    }
}
