package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.sml.RowCol;
import code.util.StringList;

public final class NamePartType extends LeafPartType {

    public NamePartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }
    
    @Override
    public void checkDirectExistence(Analyzable _an, AccessingImportingBlock _rooted,RowCol _location) {
        StringList pr_ = new StringList();
        String suppl_ = EMPTY_STRING;
        if (getParent() instanceof InnerPartType) {
            PartType part_ = getPreviousSibling();
            if (part_ == null) {
                InnerPartType par_ = (InnerPartType) getParent();
                if (par_.isRemovedBefore()) {
                    suppl_ = ((RootBlock)_rooted).getParentType().getFullName();
                    pr_.add(suppl_);
                }
            }
            while (part_ != null) {
                if (part_ instanceof LeafPartType) {
                    pr_.add(((LeafPartType)part_).exportHeader());
                }
                part_ = part_.getPreviousSibling();
            }
            pr_ = pr_.getReverse();
        }
        String typeName_ = getTypeName();
        String type_ = typeName_;
        if (!pr_.isEmpty()) {
            type_ = StringList.concat(pr_.join(".."),"..",type_);
        }
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
            if (!pr_.isEmpty()) {
                setImportedTypeName(StringList.concat(suppl_,typeName_));
                return;
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
