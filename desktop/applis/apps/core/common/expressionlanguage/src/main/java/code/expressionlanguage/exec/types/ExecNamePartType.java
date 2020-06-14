package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;

class ExecNamePartType extends ExecLeafPartType {
    ExecNamePartType(ExecParentPartType _parent, int _index, String _type, String _previousSeparator) {
        super(_parent, _index, _type, _previousSeparator);
    }

    @Override
    void checkDynExistence(ContextEl _an, CustList<IntTreeMap<String>> _dels) {
        CustList<String> pr_ = new CustList<String>();
        ExecPartType curr_ = getPartType();
        ExecPartType part_;
        if (curr_ != null) {
            part_ = curr_.getPreviousSibling();
        } else {
            part_ = null;
        }
        String typeName_ = getTypeName();
        typeName_ = StringExpUtil.removeDottedSpaces(typeName_);
        pr_.add(typeName_);
        while (part_ != null) {
            ExecPartType c_ = getDeepFirstChild(curr_);
            pr_.add(0,((ExecLeafPartType)c_).getPreviousSeparator());
            ExecPartType f_ = getDeepFirstChild(part_);
            pr_.add(0,((ExecLeafPartType)f_).exportHeader());
            part_ = part_.getPreviousSibling();
            curr_ = curr_.getPreviousSibling();
        }
        String type_ = StringList.join(pr_, "");
        if (_an.getClasses().isCustomType(type_)) {
            setImportedTypeName(typeName_);
            return;
        }
        if (_an.getStandards().getStandards().contains(type_)) {
            setImportedTypeName(typeName_);
            return;
        }
        if (PrimitiveTypeUtil.isPrimitive(type_, _an)) {
            setImportedTypeName(typeName_);
            return;
        }
        if (getParent() instanceof ExecTemplatePartType) {
            ExecPartType prev_ = getParent().getFirstChild();
            String base_ = ((ExecNamePartType)prev_).exportHeader();
            if (StringList.quickEq(getTypeName().trim(), _an.getStandards().getAliasVoid())) {
                if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                    setImportedTypeName(getTypeName().trim());
                }
            }
        }
    }

    @Override
    void analyzeTemplateExec(ContextEl _an, CustList<IntTreeMap<String>> _dels) {
        String type_ = getTypeName();
        ExecPartType part_ = getPreviousPartType();
        if (part_ != null) {
            type_ = StringList.concat(part_.getAnalyzedType(), getPreviousSeparator(), type_);
        }
        setAnalyzedType(type_);
    }

    private static ExecPartType getDeepFirstChild(ExecPartType _part) {
        ExecPartType f_ = _part;
        while (f_.getFirstChild() != null) {
            f_ = f_.getFirstChild();
        }
        return f_;
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
    private ExecPartType getPartType() {
        if (getParent() instanceof ExecInnerPartType) {
            return this;
        }
        if (getParent() instanceof ExecTemplatePartType && getParent().getParent() instanceof ExecInnerPartType && getIndex() == 0) {
            return getParent();
        }
        return null;
    }
}
