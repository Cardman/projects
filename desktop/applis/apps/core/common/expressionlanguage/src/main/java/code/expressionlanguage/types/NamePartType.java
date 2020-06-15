package code.expressionlanguage.types;

import code.util.CustList;
import code.util.*;
import code.util.StringList;

final class NamePartType extends LeafPartType {

    NamePartType(ParentPartType _parent, int _index, String _type, String _previousSeparator) {
        super(_parent, _index, _type, _previousSeparator);
    }

    @Override
    void setAnalyzedType(CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        String type_ = getTypeName();
        PartType part_ = getPreviousPartType();
        if (part_ != null) {
            type_ = StringList.concat(part_.getAnalyzedType(), getPreviousSeparator(), type_);
        }
        setAnalyzedType(type_);
    }

    private PartType getPreviousPartType() {
        if (getParent() instanceof InnerPartType) {
            return getPreviousSibling();
        }
        if (getParent() instanceof TemplatePartType && getParent().getParent() instanceof InnerPartType && getIndex() == 0) {
            return getParent().getPreviousSibling();
        }
        return null;
    }
}
