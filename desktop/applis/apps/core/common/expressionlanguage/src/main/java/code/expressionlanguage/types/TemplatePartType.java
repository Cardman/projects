package code.expressionlanguage.types;

import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

final class TemplatePartType extends BinaryType {

    TemplatePartType(ParentPartType _parent, int _index) {
        super(_parent, _index);
    }

    String getBegin() {
        return EMPTY_STRING;
    }

    private String getSeparator(int _index) {
        if (_index == 0) {
            return Templates.TEMPLATE_BEGIN;
        }
        return Templates.TEMPLATE_SEP;
    }

    String getEnd() {
        return Templates.TEMPLATE_END;
    }

    @Override
    void setAnalyzedType(CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        String tempClFull_ = fetchTemplate();
        setAnalyzedType(tempClFull_);
    }

    private String fetchTemplate() {
        PartType f_ = getFirstChild();
        CustList<PartType> ch_ = new CustList<PartType>();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = getBegin();
        int len_ = ch_.size() - 1;
        for (int i = 0; i < len_; i++) {
            t_ = StringList.concat(t_, ch_.get(i).getAnalyzedType(),getSeparator(i));
        }
        return StringList.concat(t_, ch_.last().getAnalyzedType(),getEnd());
    }
}
