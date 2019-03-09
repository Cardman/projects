package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

final class VariablePartType extends LeafPartType {

    VariablePartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }

    @Override
    void analyzeDepends(Analyzable _an,
            int _index, CustList<NatTreeMap<Integer, String>> _dels,
            RootBlock _rooted, boolean _exact) {
        String type_ = getTypeName();
        if (getParent() instanceof InnerPartType) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        if (getParent() instanceof TemplatePartType && getIndex() == 0) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        String t_ = StringList.removeAllSpaces(type_);
        String trim_ = type_.trim();
        if (trim_.startsWith(Templates.PREFIX_VAR_TYPE)) {
            type_ = trim_.substring(Templates.PREFIX_VAR_TYPE.length()).trim();
        } else {
            t_ = StringList.concat(Templates.PREFIX_VAR_TYPE,t_);
        }
        type_ = ContextEl.removeDottedSpaces(type_);
        if (!_an.getAvailableVariables().containsStr(type_)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
        }
        setAnalyzedType(t_);
    }
    @Override
    void analyzeInherits(Analyzable _an, int _index,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            RootBlock _rooted,
            boolean _protected) {
        if (getParent() instanceof InnerPartType) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        if (getParent() instanceof TemplatePartType && getIndex() == 0) {
            _an.getCurrentBadIndexes().add(getIndexInType());
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
        type_ = ContextEl.removeDottedSpaces(type_);
        if (!_an.getAvailableVariables().containsStr(type_)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        setAnalyzedType(t_);
    }
    @Override
    void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted) {
        analyzeLine(_an,_dels,_globalType,_rooted);
    }
    @Override
    void analyzeLine(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted) {
        if (getParent() instanceof InnerPartType) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        if (getParent() instanceof TemplatePartType && getIndex() == 0) {
            _an.getCurrentBadIndexes().add(getIndexInType());
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
        type_ = ContextEl.removeDottedSpaces(type_);
        if (!_an.getAvailableVariables().containsStr(type_)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        setAnalyzedType(t_);
    }
    @Override
    void analyzeAccessibleId(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels,
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
        type_ = ContextEl.removeDottedSpaces(type_);
        if (!_an.getAvailableVariables().containsStr(type_)) {
            return;
        }
        setAnalyzedType(t_);
    }
    @Override
    void checkDynExistence(Analyzable _an,CustList<NatTreeMap<Integer, String>>_dels) {
        
    }
}
