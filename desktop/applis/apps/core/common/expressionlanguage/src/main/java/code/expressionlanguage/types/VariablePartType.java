package code.expressionlanguage.types;

import code.util.CustList;
import code.util.*;
import code.util.StringList;

final class VariablePartType extends LeafPartType {

    VariablePartType(ParentPartType _parent, int _index, String _type, String _previousSeparator) {
        super(_parent, _index, _type,_previousSeparator);
    }

    @Override
    void setAnalyzedType(CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        String type_ = getTypeName();
        setAnalyzedType(type_);
    }

}
