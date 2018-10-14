package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.sml.RowCol;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

final class VariablePartType extends LeafPartType {

    public VariablePartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }

    @Override
    public void analyzeDepends(Analyzable _an,
            int _index, CustList<NatTreeMap<Integer, String>> _dels,
            RootBlock _rooted, boolean _exact, RowCol _location) {
        if (getParent() instanceof InnerPartType) {
            stopDepends();
            return;
        }
        if (getParent() instanceof TemplatePartType && getIndex() == 0) {
            stopDepends();
            return;
        }
        String type_ = getTypeName();
        String t_ = StringList.removeAllSpaces(type_);
        type_ = type_.trim().substring(Templates.PREFIX_VAR_TYPE.length()).trim();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (!_an.getAvailableVariables().containsStr(type_)) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(type_);
            un_.setFileName(_rooted.getFile().getFileName());
            un_.setRc(_location);
            _an.getClasses().addError(un_);
            stopDepends();
        }
        setAnalyzedType(t_);
    }
    @Override
    public void analyzeInherits(Analyzable _an, int _index,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            AccessingImportingBlock _rooted, boolean _exact,
            boolean _protected, RowCol _location) {
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
    public void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact, boolean _protected, RowCol _location) {
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
    public void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact) {
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
        if (_an.getAvailableVariables().containsStr(type_)) {
            setAnalyzedType(t_);
        }
    }
    @Override
    public void checkDynExistence(Analyzable _an,CustList<NatTreeMap<Integer, String>>_dels) {
        
    }

}
