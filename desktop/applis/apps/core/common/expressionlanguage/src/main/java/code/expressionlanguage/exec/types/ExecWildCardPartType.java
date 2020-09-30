package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;

final class ExecWildCardPartType extends ExecParentPartType {
    private String prefix;
    ExecWildCardPartType(ExecParentPartType _parent, int _index, String _prefix) {
        super(_parent, _index);
        prefix = _prefix;
    }

    @Override
    String getBegin() {
        return prefix;
    }

    @Override
    String getPrettyBegin() {
        return prefix;
    }

    @Override
    String getPrettyEnd() {
        return EMPTY_STRING;
    }
    @Override
    String getEnd() {
        return EMPTY_STRING;
    }

    @Override
    boolean analyzeTree(ContextEl _an, CustList<IntTreeMap<String>> _dels) {
        if (!(getParent() instanceof ExecTemplatePartType)) {
            return false;
        }
        ExecPartType prev_ = getParent().getFirstChild();
        String base_ = ((ExecNamePartType)prev_).getTypeName();
        if (StringList.quickEq(base_.trim(), _an.getStandards().getContent().getReflect().getAliasFct())) {
            return false;
        }
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
        return true;
    }
}
