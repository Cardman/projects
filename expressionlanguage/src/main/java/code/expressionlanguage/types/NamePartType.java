package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.sml.RowCol;
import code.util.CustList;
import code.util.StringList;

public final class NamePartType extends LeafPartType {

    public NamePartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }
    
    @Override
    public void checkDirectExistence(Analyzable _an, AccessingImportingBlock _rooted,RowCol _location) {
        StringList pr_ = new StringList();
        String suppl_ = EMPTY_STRING;
        InnerPartType i_ = null;
        PartType parCur_ = null;
        if (getParent() instanceof InnerPartType) {
            parCur_ = this;
            i_ = (InnerPartType) getParent();
        } else if (getParent() instanceof TemplatePartType && getParent().getParent() instanceof InnerPartType && getIndex() == 0) {
            parCur_ = getParent();
            i_ = (InnerPartType) getParent().getParent();
        }
        if (i_ != null) {
            PartType part_ = parCur_.getPreviousSibling();
            if (part_ == null) {
                InnerPartType par_ = i_;
                if (par_.isRemovedBefore()) {
                    suppl_ = ((RootBlock)_rooted).getParentType().getGenericString();
                    pr_.add(suppl_);
                }
            }
            while (part_ != null) {
                PartType f_ = part_;
                while (f_.getFirstChild() != null) {
                    f_ = f_.getFirstChild();
                }
                if (f_ instanceof LeafPartType) {
                    pr_.add(((LeafPartType)f_).exportHeader());
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
                if (!suppl_.isEmpty()) {
                    setImportedTypeName(StringList.concat(suppl_,"..",typeName_));
                    return;
                }
                setImportedTypeName(typeName_);
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
        if (getParent() instanceof TemplatePartType) {
            PartType prev_ = getParent().getFirstChild();
            if (prev_ instanceof NamePartType) {
                String base_ = ((NamePartType)prev_).getTypeName();
                if (StringList.quickEq(getTypeName().trim(), _an.getStandards().getAliasVoid())) {
                    if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct())) {
                        setImportedTypeName(getTypeName().trim());
                        return;
                    }
                }
            }
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
    public void analyze(Analyzable _an, String _globalType, AccessingImportingBlock _rooted,
            RowCol _location) {
        CustList<PartType> previous_ = new CustList<PartType>();
        InnerPartType i_ = null;
        PartType parCur_ = null;
        if (getParent() instanceof InnerPartType) {
            parCur_ = this;
            i_ = (InnerPartType) getParent();
        } else if (getParent() instanceof TemplatePartType && getParent().getParent() instanceof InnerPartType && getIndex() == 0) {
            parCur_ = getParent();
            i_ = (InnerPartType) getParent().getParent();
        }
        if (i_ != null) {
            PartType part_ = parCur_.getPreviousSibling();
            if (part_ == null) {
                InnerPartType par_ = i_;
                if (par_.isRemovedBefore()) {
                    String type_ = getTypeName();
                    RootBlock c = (RootBlock)_rooted;
                    StringList allPossibleDirectSuperTypes_ = new StringList();
                    CustList<RootBlock> innersCandidates_ = new CustList<RootBlock>();
                    StringList allAncestors_ = new StringList();
                    RootBlock p_ = c;
                    Classes classes_ = _an.getClasses();
                    while (p_ != null) {
                        allAncestors_.add(p_.getFullName());
                        p_ = p_.getParentType();
                    }
                    for (String a: allAncestors_) {
                        StringList c_ = new StringList(a);
                        while (true) {
                            StringList new_ = new StringList();
                            for (String s: c_) {
                                RootBlock sub_ = classes_.getClassBody(s);
                                if (sub_ == null) {
                                    continue;
                                }
                                boolean add_ = false;
                                for (Block b: Classes.getDirectChildren(sub_)) {
                                    if (!(b instanceof RootBlock)) {
                                        continue;
                                    }
                                    RootBlock inner_ = (RootBlock) b;
                                    if (StringList.quickEq(inner_.getName(), type_)) {
                                        allPossibleDirectSuperTypes_.add(s);
                                        innersCandidates_.add(inner_);
                                        add_ = true;
                                    }
                                }
                                if (add_) {
                                    continue;
                                }
                                for (String t: sub_.getImportedDirectSuperTypes()) {
                                    String id_ = Templates.getIdFromAllTypes(t);
                                    new_.add(id_);
                                }
                            }
                            if (new_.isEmpty()) {
                                break;
                            }
                            c_ = new_;
                        }
                    }
                    allPossibleDirectSuperTypes_.removeDuplicates();
                    if (allPossibleDirectSuperTypes_.size() == 1) {
                        if (innersCandidates_.first().isStaticType()) {
                            String new_ = allPossibleDirectSuperTypes_.first();
                            setAnalyzedType(StringList.concat(new_,"..",type_));
                            return;
                        }
                        if (!Templates.correctNbParameters(_globalType, _an)) {
                            String new_ = allPossibleDirectSuperTypes_.first();
                            setAnalyzedType(StringList.concat(new_,"..",type_));
                            return;
                        }
                        String new_ = Templates.getFullTypeByBases(_globalType, allPossibleDirectSuperTypes_.first(), _an);
                        setAnalyzedType(StringList.concat(new_,"..",type_));
                        return;
                    }
                    //ERROR
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(type_);
                    un_.setFileName(_rooted.getFile().getFileName());
                    un_.setRc(_location);
                    _an.getClasses().addError(un_);
                    return;
                }
            }
            while (part_ != null) {
                previous_.add(part_);
                part_ = part_.getPreviousSibling();
            }
        }
        if (!previous_.isEmpty()) {
            previous_ = previous_.getReverse();
            PartType last_ = previous_.last();
            String owner_ = last_.getAnalyzedType();
            String id_ = Templates.getIdFromAllTypes(owner_);
            Classes classes_ = _an.getClasses();
            StringList foundOwners_ = new StringList();
            CustList<RootBlock> innersCandidates_ = new CustList<RootBlock>();
            StringList c_ = new StringList(id_);
            String type_ = getTypeName();
            while (true) {
                StringList new_ = new StringList();
                for (String s: c_) {
                    RootBlock sub_ = classes_.getClassBody(s);
                    if (sub_ == null) {
                        continue;
                    }
                    boolean add_ = false;
                    for (Block b: Classes.getDirectChildren(sub_)) {
                        if (!(b instanceof RootBlock)) {
                            continue;
                        }
                        RootBlock inner_ = (RootBlock) b;
                        if (StringList.quickEq(inner_.getName(), type_)) {
                            foundOwners_.add(s);
                            innersCandidates_.add(inner_);
                            add_ = true;
                        }
                    }
                    if (add_) {
                        continue;
                    }
                    for (String t: sub_.getImportedDirectSuperTypes()) {
                        String idLoc_ = Templates.getIdFromAllTypes(t);
                        new_.add(idLoc_);
                    }
                }
                if (new_.isEmpty()) {
                    break;
                }
                c_ = new_;
            }
            foundOwners_.removeDuplicates();
            if (foundOwners_.size() == 1) {
                String old_ = last_.getAnalyzedType();
                if (innersCandidates_.first().isStaticType()) {
                    String new_ = foundOwners_.first();
                    last_.setAnalyzedType(new_);
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    return;
                }
                if (!Templates.correctNbParameters(old_, _an)) {
                    String new_ = foundOwners_.first();
                    last_.setAnalyzedType(new_);
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    return;
                }
                String new_ = Templates.getFullTypeByBases(old_, foundOwners_.first(), _an);
                last_.setAnalyzedType(new_);
                setAnalyzedType(StringList.concat(new_,"..",type_));
                return;
            }
            //ERROR
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(type_);
            un_.setFileName(_rooted.getFile().getFileName());
            un_.setRc(_location);
            _an.getClasses().addError(un_);
            return;
        }
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
            setAnalyzedType(type_);
            return;
        }
        if (_an.getStandards().getStandards().contains(type_)) {
            setAnalyzedType(type_);
            return;
        }
        if (PrimitiveTypeUtil.isPrimitive(type_, _an)) {
            setAnalyzedType(type_);
            return;
        }
        if (getParent() instanceof TemplatePartType) {
            PartType prev_ = getParent().getFirstChild();
            if (prev_ instanceof NamePartType) {
                String base_ = ((NamePartType)prev_).getTypeName();
                if (StringList.quickEq(getTypeName().trim(), _an.getStandards().getAliasVoid())) {
                    if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct())) {
                        setAnalyzedType(getTypeName().trim());
                        return;
                    }
                }
            }
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
        setAnalyzedType(out_);
    }
    @Override
    public void checkDynExistence(Analyzable _an) {
        StringList pr_ = new StringList();
        InnerPartType i_ = null;
        PartType parCur_ = null;
        if (getParent() instanceof InnerPartType) {
            parCur_ = this;
            i_ = (InnerPartType) getParent();
        } else if (getParent() instanceof TemplatePartType && getParent().getParent() instanceof InnerPartType && getIndex() == 0) {
            parCur_ = getParent();
            i_ = (InnerPartType) getParent().getParent();
        }
        if (i_ != null) {
            PartType part_ = parCur_.getPreviousSibling();
            while (part_ != null) {
                PartType f_ = part_;
                while (f_.getFirstChild() != null) {
                    f_ = f_.getFirstChild();
                }
                if (f_ instanceof LeafPartType) {
                    pr_.add(((LeafPartType)f_).exportHeader());
                }
                part_ = part_.getPreviousSibling();
            }
            pr_ = pr_.getReverse();
        }
        String typeName_ = getTypeName();
        typeName_ = ContextEl.removeDottedSpaces(typeName_);
        String type_ = typeName_;
        if (!pr_.isEmpty()) {
            type_ = StringList.concat(pr_.join(".."),"..",type_);
        }
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
        if (getParent() instanceof TemplatePartType) {
            PartType prev_ = getParent().getFirstChild();
            if (prev_ instanceof NamePartType) {
                String base_ = ((NamePartType)prev_).getTypeName();
                if (StringList.quickEq(getTypeName().trim(), _an.getStandards().getAliasVoid())) {
                    if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct())) {
                        setImportedTypeName(getTypeName().trim());
                    }
                }
            }
        }
    }

}
