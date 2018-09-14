package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.sml.RowCol;
import code.util.StringList;

public final class VariablePartType extends LeafPartType {

    public VariablePartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }
    
    @Override
    public void checkExistence(Analyzable _an, AccessingImportingBlock _rooted,RowCol _location) {
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
        }
        setImportedTypeName(t_);
    }

    @Override
    public void checkDirectExistence(Analyzable _an, AccessingImportingBlock _rooted,RowCol _location) {
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
        }
        setImportedTypeName(t_);
    }
    @Override
    public void analyze(Analyzable _an, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact, RowCol _location) {
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
        }
        setAnalyzedType(t_);
    }
    @Override
    public void analyze(Analyzable _an, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact) {
        String type_ = getTypeName();
        String t_ = StringList.removeAllSpaces(type_);
        type_ = type_.trim().substring(Templates.PREFIX_VAR_TYPE.length()).trim();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (_an.getAvailableVariables().containsStr(type_)) {
            setAnalyzedType(t_);
        }
    }
    @Override
    public void checkDynExistence(Analyzable _an) {
        
    }

}
