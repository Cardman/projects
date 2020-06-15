package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

final class AnaVariablePartType extends AnaLeafPartType {
    AnaVariablePartType(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType, _type, _previousSeparator);
    }

    @Override
    void analyze(ContextEl _an, CustList<IntTreeMap<String>> _dels, String _globalType, AccessingImportingBlock _local, AccessingImportingBlock _rooted) {
        analyzeLine(_an,null,_dels,_local,_rooted);
    }

    @Override
    void analyzeLine(ContextEl _an, ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, AccessingImportingBlock _local, AccessingImportingBlock _rooted) {
        if (getParent() instanceof AnaInnerPartType) {
            return;
        }
        if (getParent() instanceof AnaTemplatePartType && getIndex() == 0) {
            return;
        }
        String type_ = getTypeName();
        String t_ = StringList.removeAllSpaces(type_);
        String trim_ = type_.trim();
        if (trim_.startsWith(Templates.PREFIX_VAR_TYPE)) {
            type_ = trim_.substring(Templates.PREFIX_VAR_TYPE.length()).trim();
        } else {
            t_ = StringList.concat(Templates.PREFIX_VAR_TYPE,t_);
        }
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (!_an.getAnalyzing().getAvailableVariables().contains(type_)) {
            return;
        }
        setAnalyzedType(t_);
    }

    @Override
    void analyzeAccessibleId(ContextEl _an, CustList<IntTreeMap<String>> _dels, AccessingImportingBlock _rooted) {
        if (getParent() instanceof AnaInnerPartType) {
            return;
        }
        if (getParent() instanceof AnaTemplatePartType && getIndex() == 0) {
            return;
        }
        String type_ = getTypeName();
        String t_ = StringList.removeAllSpaces(type_);
        if (type_.trim().startsWith(Templates.PREFIX_VAR_TYPE)) {
            type_ = type_.trim().substring(Templates.PREFIX_VAR_TYPE.length());
        } else {
            t_ = StringList.concat(Templates.PREFIX_VAR_TYPE,t_);
        }
        type_ = type_.trim();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (!_an.getAnalyzing().getAvailableVariables().contains(type_)) {
            return;
        }
        setAnalyzedType(t_);
    }

    @Override
    void analyzeTemplate(ContextEl _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        String type_ = getTypeName();
        setAnalyzedType(type_);
    }
}
