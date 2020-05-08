package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessedBlock;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

final class VariablePartType extends LeafPartType {

    VariablePartType(ParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType, _type,_previousSeparator);
    }

    @Override
    void analyze(Analyzable _an, CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        analyzeLine(_an,null,_dels,_globalType,_local,_rooted);
    }

    @Override
    void analyzeTemplate(Analyzable _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        String type_ = getTypeName();
        setAnalyzedType(type_);
    }

    @Override
    void analyzeLine(Analyzable _an, ReadyTypes _ready,CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        if (getParent() instanceof InnerPartType) {
            return;
        }
        if (getParent() instanceof TemplatePartType && getIndex() == 0) {
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
        if (!_an.getAvailableVariables().contains(type_)) {
            return;
        }
        setAnalyzedType(t_);
    }
    @Override
    void analyzeAccessibleId(Analyzable _an,
            CustList<IntTreeMap< String>> _dels,
                             AccessingImportingBlock _rooted) {
        if (getParent() instanceof InnerPartType) {
            return;
        }
        if (getParent() instanceof TemplatePartType && getIndex() == 0) {
            return;
        }
        String type_ = getTypeName();
        String t_ = StringList.removeAllSpaces(type_);
        type_ = type_.trim().substring(Templates.PREFIX_VAR_TYPE.length()).trim();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (!_an.getAvailableVariables().contains(type_)) {
            return;
        }
        setAnalyzedType(t_);
    }
    @Override
    void checkDynExistence(Analyzable _an,CustList<IntTreeMap< String>>_dels) {
        
    }
}
