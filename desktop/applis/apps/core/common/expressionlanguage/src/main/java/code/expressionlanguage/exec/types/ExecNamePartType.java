package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StrTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;
import code.util.core.StringUtil;

final class ExecNamePartType extends ExecLeafPartType {
    ExecNamePartType(ExecParentPartType _parent, int _index, String _type, String _previousSeparator, String _previousOperator) {
        super(_parent, _index, _type, _previousSeparator, _previousOperator);
    }

    @Override
    void checkDynExistence(ContextEl _an, CustList<StrTypes> _dels) {
        ExecPartType part_ = getPreviousPartType();
        String typeName_ = getTypeName();
        typeName_ = StringExpUtil.removeDottedSpaces(typeName_);
        String concat_;
        if (part_ != null) {
            String prevSep_ = getPreviousSeparator();
            concat_ = StringUtil.concat(part_.getAnalyzedType(),prevSep_,typeName_);
        } else {
            concat_ = typeName_;
        }
        String id_ = StringExpUtil.getIdFromAllTypes(concat_);
        if (_an.getClassBody(id_) != null) {
            setImportedTypeName(typeName_);
            setAnalyzedType(concat_);
            return;
        }
        if (ExecClassArgumentMatching.isPrimitive(concat_, _an)) {
            setImportedTypeName(typeName_);
            setAnalyzedType(typeName_);
            return;
        }
        if (getParent() instanceof ExecTemplatePartType) {
            ExecPartType prev_ = getParent().getFirstChild();
            String base_ = ((ExecNamePartType)prev_).exportHeader();
            if (StringUtil.quickEq(getTypeName().trim(), _an.getStandards().getContent().getCoreNames().getAliasVoid())) {
                if (StringUtil.quickEq(base_.trim(), _an.getStandards().getContent().getReflect().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                    setImportedTypeName(getTypeName().trim());
                    setAnalyzedType(getTypeName().trim());
                }
            }
        }
    }

    private ExecPartType getPreviousPartType() {
        if (getParent() instanceof ExecInnerPartType) {
            return getPreviousSibling();
        }
        if (getParent() instanceof ExecTemplatePartType && getParent().getParent() instanceof ExecInnerPartType && getIndex() == 0) {
            return getParent().getPreviousSibling();
        }
        return null;
    }
}
