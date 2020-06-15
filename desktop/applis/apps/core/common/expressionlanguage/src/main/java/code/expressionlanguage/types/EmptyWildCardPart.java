package code.expressionlanguage.types;

import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.*;

final class EmptyWildCardPart extends LeafPartType {

    EmptyWildCardPart(ParentPartType _parent, int _index,
                      String _type, String _previousSeparator) {
        super(_parent, _index, _type,_previousSeparator);
    }

    @Override
    void setAnalyzedType(CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        setAnalyzedType(Templates.SUB_TYPE);
    }

}
