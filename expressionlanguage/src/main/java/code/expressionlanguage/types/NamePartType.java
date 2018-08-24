package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.sml.RowCol;

public final class NamePartType extends LeafPartType {

    public NamePartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }

    @Override
    public void checkExistence(Analyzable _an, AccessingImportingBlock _rooted,RowCol _location) {
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (_an.getClasses().isCustomType(type_)) {
            if (_an.isDirectImport()) {
                if (!_rooted.isAccessibleType(type_, _an)) {
                    BadAccessClass err_ = new BadAccessClass();
                    err_.setFileName(_rooted.getFile().getFileName());
                    err_.setRc(new RowCol());
                    err_.setId(type_);
                    _an.getClasses().addError(err_);
                }
            } else if (!_rooted.canAccessClass(type_, _an)) {
                BadAccessClass err_ = new BadAccessClass();
                err_.setFileName(_rooted.getFile().getFileName());
                err_.setRc(new RowCol());
                err_.setId(type_);
                _an.getClasses().addError(err_);
            }
            setImportedTypeName(type_);
            return;
        }
        if (_an.getStandards().getStandards().contains(type_)) {
            setImportedTypeName(type_);
            return;
        }
        if (PrimitiveTypeUtil.isPrimitive(type_, _an)) {
            setImportedTypeName(type_);
            return;
        }
        String out_;
        if (_an.isDirectImport()) {
            out_ = _an.lookupImportsDirect(type_, _rooted);
        } else {
            out_ = _an.lookupImportsIndirect(type_, _rooted);
        }
        if (out_.isEmpty()) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(type_);
            un_.setFileName(_rooted.getFile().getFileName());
            un_.setRc(_location);
            _an.getClasses().addError(un_);
            out_ = _an.getStandards().getAliasObject();
        }
        setImportedTypeName(out_);
    }

    @Override
    public void checkDynExistence(Analyzable _an) {
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (_an.getClasses().isCustomType(type_)) {
            setImportedTypeName(type_);
            return;
        }
        if (_an.getStandards().getStandards().contains(type_)) {
            setImportedTypeName(type_);
            return;
        }
        if (PrimitiveTypeUtil.isPrimitive(type_, _an)) {
            setImportedTypeName(type_);
        }
    }

}
