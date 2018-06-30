package code.expressionlanguage.types;

import code.expressionlanguage.Templates;

public final class TemplatePartType extends ParentPartType {

    public TemplatePartType(ParentPartType _parent, int _index, int _indexInType) {
        super(_parent, _index, _indexInType);
    }

    @Override
    public String getBegin() {
        return EMPTY_STRING;
    }

    @Override
    public String getSeparator(int _index) {
        if (_index == 0) {
            return Templates.TEMPLATE_BEGIN;
        }
        return Templates.TEMPLATE_SEP;
    }

    @Override
    public String getEnd() {
        return Templates.TEMPLATE_END;
    }

}
