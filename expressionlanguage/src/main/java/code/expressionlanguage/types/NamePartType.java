package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeOwnersDepends;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.RootBlock;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

final class NamePartType extends LeafPartType {

    NamePartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }

    @Override
    void analyzeDepends(Analyzable _an,
            int _index, CustList<NatTreeMap<Integer, String>> _dels,
            RootBlock _rooted, boolean _exact) {
        CustList<PartType> previous_ = new CustList<PartType>();
        InnerPartType i_ = null;
        PartType parCur_ = null;
        int sizeDels_ = -1;
        int indexDels_ = -1;
        boolean lastInner_ = false;
        if (getParent() instanceof InnerPartType) {
            parCur_ = this;
            i_ = (InnerPartType) getParent();
            sizeDels_ = _dels.get(_dels.size() - 1).size();
            indexDels_ = getIndex();
        } else if (getParent() instanceof TemplatePartType && getParent().getParent() instanceof InnerPartType && getIndex() == 0) {
            parCur_ = getParent();
            i_ = (InnerPartType) getParent().getParent();
            sizeDels_ = _dels.get(_dels.size() - 2).size();
            indexDels_ = getParent().getIndex();
        }
        String fullName_ = _rooted.getFullName();
        if (i_ != null) {
            if (sizeDels_ == indexDels_ + 1) {
                lastInner_ = true;
            }
        }
        if (i_ != null) {
            PartType part_ = parCur_.getPreviousSibling();
            if (part_ == null) {
                if (i_.isRemovedBefore()) {
                    String anaType_ = getFoundInnerDepends(_an, _index, _dels, _rooted);
                    if (anaType_ != null) {
                        return;
                    }
                    _an.getCurrentBadIndexes().add(getIndexInType());
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
            StringList deps_;
            String type_ = getTypeName();
            TypeOwnersDepends res_ = TypeUtil.getOwnersDepends(lastInner_, fullName_, id_, type_, _an);
            StringList owners_ = res_.getTypeOwners();
            if (owners_.size() == 1) {
                deps_ = res_.getDepends();
                getTypeNames().addAllElts(deps_);
                String new_ = owners_.first();
                setAnalyzedType(StringList.concat(new_,"..",type_));
                return;
            }
            //ERROR
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (_an.getClasses().isCustomType(type_)) {
            if (_rooted.isTypeHidden(type_, _an)) {
                _an.getCurrentBadIndexes().add(getIndexInType());
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
            if (StringList.quickEq(getTypeName().trim(), _an.getStandards().getAliasVoid())) {
                String base_ = prev_.getAnalyzedType();
                base_ = Templates.getIdFromAllTypes(base_);
                if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                    setAnalyzedType(getTypeName().trim());
                    return;
                }
                _an.getCurrentBadIndexes().add(getIndexInType());
                String out_ = _an.getStandards().getAliasObject();
                setAnalyzedType(out_);
                return;
            }
        }
        if (_an.getOptions().isSingleInnerParts()) {
            //1 full name
            //2 inner from all super type (like ..Type when other option)
            String anaType_ = getFoundInnerDepends(_an, _index, _dels, _rooted);
            if (anaType_ != null) {
                return;
            }
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        String out_ = _an.lookupImportType(type_, _rooted);
        if (out_.isEmpty()) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            out_ = _an.getStandards().getAliasObject();
        }
        if (_rooted.isTypeHidden(out_, _an)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            out_ = _an.getStandards().getAliasObject();
        }
        setAnalyzedType(out_);
    }
    private String getFoundInnerDepends(Analyzable _an,
                                        int _index, CustList<NatTreeMap<Integer, String>> _dels,
                                        RootBlock _rooted) {
        InnerPartType i_ = null;
        int sizeDels_ = -1;
        int indexDels_ = -1;
        boolean lastInner_ = false;
        if (getParent() instanceof InnerPartType) {
            i_ = (InnerPartType) getParent();
            sizeDels_ = _dels.get(_dels.size() - 1).size();
            indexDels_ = getIndex();
        } else if (getParent() instanceof TemplatePartType && getParent().getParent() instanceof InnerPartType && getIndex() == 0) {
            i_ = (InnerPartType) getParent().getParent();
            sizeDels_ = _dels.get(_dels.size() - 2).size();
            indexDels_ = getParent().getIndex();
        }
        String fullName_ = _rooted.getFullName();
        if (i_ != null) {
            if (sizeDels_ == indexDels_ + 1) {
                lastInner_ = true;
            }
        }
        String type_ = getTypeName();
        StringList allAncestors_ = new StringList();
        RootBlock p_ = _rooted.getParentType();
        StringList deps_;
        while (p_ != null) {
            allAncestors_.add(p_.getFullName());
            p_ = p_.getParentType();
        }
        int ancestorIndex_ = _rooted.getAncestorsIndexes().get(_index);
        if (i_ == null || i_.getParent() == null) {
            if (ancestorIndex_ != -1) {
                String a_ = allAncestors_.get(ancestorIndex_);
                TypeOwnersDepends res_ = TypeUtil.getOwnersDepends(lastInner_, fullName_, a_, type_, _an);
                StringList owners_ = res_.getTypeOwners();
                deps_ = res_.getDepends();
                getTypeNames().addAllElts(deps_);
                String new_ = owners_.first();
                String conc_ = StringList.concat(new_,"..",type_);
                setAnalyzedType(conc_);
                return conc_;
            }
        }
        for (String a: allAncestors_) {
            TypeOwnersDepends res_ = TypeUtil.getOwnersDepends(lastInner_, fullName_, a, type_, _an);
            StringList owners_ = res_.getTypeOwners();
            if (owners_.size() == 1) {
                deps_ = res_.getDepends();
                getTypeNames().addAllElts(deps_);
                String new_ = owners_.first();
                String conc_ = StringList.concat(new_,"..",type_);
                setAnalyzedType(conc_);
                return conc_;
            }
            deps_ = res_.getDepends();
            getTypeNames().addAllElts(deps_);
        }
        TypeOwnersDepends res_ = _an.lookupImportMemberTypeDeps(type_, _rooted);
        StringList owners_ = res_.getTypeOwners();
        if (owners_.size() == 1) {
            String conc_ = owners_.first();
            setAnalyzedType(conc_);
            return owners_.first();
        }
        return null;
    }

    @Override
    void analyzeInherits(Analyzable _an, int _index,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            RootBlock _rooted,
            boolean _protected) {
        CustList<PartType> previous_ = new CustList<PartType>();
        InnerPartType i_ = null;
        PartType parCur_ = null;
        int sizeDels_ = -1;
        int indexDels_ = -1;
        if (getParent() instanceof InnerPartType) {
            parCur_ = this;
            i_ = (InnerPartType) getParent();
            sizeDels_ = _dels.get(_dels.size() - 1).size();
            indexDels_ = getIndex();
        } else if (getParent() instanceof TemplatePartType && getParent().getParent() instanceof InnerPartType && getIndex() == 0) {
            parCur_ = getParent();
            i_ = (InnerPartType) getParent().getParent();
            sizeDels_ = _dels.get(_dels.size() - 2).size();
            indexDels_ = getParent().getIndex();
        }
        boolean lastInner_ = false;
        if (i_ != null) {
            if (sizeDels_ == indexDels_ + 1) {
                lastInner_ = true;
            }
        }
        if (i_ != null) {
            PartType part_ = parCur_.getPreviousSibling();
            if (part_ == null) {
                if (i_.isRemovedBefore()) {
                    if (isMethodFound(_an, _index, _dels, _globalType, _rooted)) {
                        return;
                    }
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
            Classes classes_ = _an.getClasses();
            String type_ = getTypeName();
            StringList foundOwners_ = TypeUtil.getGenericOwners(true, lastInner_, _globalType, owner_, type_, _an);
            if (foundOwners_.size() == 1) {
                String id_ = Templates.getIdFromAllTypes(foundOwners_.first());
                String in_ = StringList.concat(id_,"..",type_);
                RootBlock inner_ = classes_.getClassBody(in_);
                if (inner_.isStaticType()) {
                    last_.setAnalyzedType(id_);
                    setAnalyzedType(StringList.concat(id_,"..",type_));
                    return;
                }
                if (!Templates.correctNbParameters(owner_, _an)) {
                    return;
                }
                String new_ = Templates.quickFormat(owner_, foundOwners_.first(), _an);
                last_.setAnalyzedType(new_);
                setAnalyzedType(StringList.concat(new_,"..",type_));
                return;
            }
            return;
        }
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (_an.getClasses().isCustomType(type_)) {
            analyzeFullType(_an, _rooted, type_);
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
            if (processFctVoid(_an, _dels)) {
                return;
            }
        }
        //_an.lookupImportMemberType(type_, _rooted,true);
        if (_an.getOptions().isSingleInnerParts()) {
            if (isMethodFound(_an, _index, _dels, _globalType, _rooted)) {
                return;
            }
            return;
        }
        lookupSimpleType(_an, _rooted, type_);
    }
    private boolean isMethodFound(Analyzable _an, int _index,
                                  CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
                                  RootBlock _rooted) {
        InnerPartType i_ = null;
        int sizeDels_ = -1;
        int indexDels_ = -1;
        if (getParent() instanceof InnerPartType) {
            i_ = (InnerPartType) getParent();
            sizeDels_ = _dels.get(_dels.size() - 1).size();
            indexDels_ = getIndex();
        } else if (getParent() instanceof TemplatePartType && getParent().getParent() instanceof InnerPartType && getIndex() == 0) {
            i_ = (InnerPartType) getParent().getParent();
            sizeDels_ = _dels.get(_dels.size() - 2).size();
            indexDels_ = getParent().getIndex();
        }
        boolean lastInner_ = false;
        if (i_ != null) {
            if (sizeDels_ == indexDels_ + 1) {
                lastInner_ = true;
            }
        }
        String type_ = getTypeName();
        StringList allAncestors_ = new StringList();
        RootBlock p_ = _rooted.getParentType();
        Classes classes_ = _an.getClasses();
        while (p_ != null) {
            allAncestors_.add(p_.getGenericString());
            p_ = p_.getParentType();
        }
        int ancestorIndex_ = _rooted.getAncestorsIndexes().get(_index);
        if (i_ == null || i_.getParent() == null) {
            if (ancestorIndex_ != -1) {
                String a_ = allAncestors_.get(ancestorIndex_);
                StringList owners_ = TypeUtil.getGenericOwners(true, lastInner_, _globalType, a_, type_, _an);
                String newId_ = Templates.getIdFromAllTypes(owners_.first());
                String f_ = Templates.quickFormat(_globalType, a_, _an);
                String in_ = StringList.concat(newId_,"..",type_);
                RootBlock inner_ = classes_.getClassBody(in_);
                if (inner_.isStaticType()) {
                    String new_ = Templates.getIdFromAllTypes(owners_.first());
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    return true;
                }
                String sup_ = owners_.first();
                String new_ = Templates.quickFormat(f_, sup_, _an);
                setAnalyzedType(StringList.concat(new_,"..",type_));
                return true;
            }
        }
        for (String a: allAncestors_) {
            StringList owners_ = TypeUtil.getGenericOwners(true, lastInner_, _globalType, a, type_, _an);
            if (owners_.size() == 1) {
                String f_ = Templates.quickFormat(_globalType, a, _an);
                String newId_ = Templates.getIdFromAllTypes(owners_.first());
                String in_ = StringList.concat(newId_,"..",type_);
                RootBlock inner_ = classes_.getClassBody(in_);
                if (inner_.isStaticType()) {
                    String new_ = Templates.getIdFromAllTypes(owners_.first());
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    return true;
                }
                String sup_ = owners_.first();
                String new_ = Templates.quickFormat(f_, sup_, _an);
                setAnalyzedType(StringList.concat(new_,"..",type_));
                return true;
            }
        }
        String owner_ = _an.lookupImportMemberType(type_, _rooted,true);
        if (!owner_.isEmpty()) {
            setAnalyzedType(owner_);
            return true;
        }
        _an.getCurrentBadIndexes().add(getIndexInType());
        return false;
    }
    @Override
    void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted) {
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
                if (i_.isRemovedBefore()) {
                    tryAnalyzeInnerParts(_an, _globalType, _rooted
                    );
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
            Classes classes_ = _an.getClasses();
            String type_ = getTypeName();
            StringList foundOwners_ = TypeUtil.getGenericOwners(false, true, _globalType, owner_, type_, _an);
            if (foundOwners_.size() == 1) {
                String idOwner_= Templates.getIdFromAllTypes(foundOwners_.first());
                String in_ = StringList.concat(idOwner_,"..",type_);
                RootBlock inner_ = classes_.getClassBody(in_);
                if (inner_.isStaticType()) {
                    String new_ = foundOwners_.first();
                    last_.setAnalyzedType(new_);
                    setAnalyzedType(StringList.concat(idOwner_,"..",type_));
                    return;
                }
                if (!Templates.correctNbParameters(owner_, _an)) {
                    return;
                }
                String new_ = Templates.quickFormat(owner_, foundOwners_.first(), _an);
                last_.setAnalyzedType(new_);
                setAnalyzedType(StringList.concat(new_,"..",type_));
                return;
            }
            return;
        }
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (_an.getClasses().isCustomType(type_)) {
            analyzeFullType(_an, _rooted, type_);
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
            if (processFctVoid(_an, _dels)) {
                return;
            }
        }
        if (_an.getOptions().isSingleInnerParts()) {
            tryAnalyzeInnerParts(_an, _globalType, _rooted
            );
            return;
        }
        lookupSimpleType(_an, _rooted, type_);
    }

    private void analyzeFullType(Analyzable _an, AccessingImportingBlock _rooted, String _type) {
        if (_rooted.isTypeHidden(_type, _an)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        setAnalyzedType(_type);
    }

    @Override
    void analyzeLine(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted) {
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
                if (i_.isRemovedBefore()) {
                    tryAnalyzeInnerPartsLine(_an, _globalType, _rooted);
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
            String type_ = getTypeName();
            String id_ = Templates.getIdFromAllTypes(owner_);
            StringList foundOwners_ = TypeUtil.getOwners(false, true, _globalType, id_, type_, false, _an);
            if (foundOwners_.size() == 1) {
                String new_ = foundOwners_.first();
                last_.setAnalyzedType(new_);
                setAnalyzedType(StringList.concat(new_,"..",type_));
            }
            return;
        }
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (_an.getClasses().isCustomType(type_)) {
            analyzeFullType(_an, _rooted, type_);
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
            if (processFctVoid(_an, _dels)) {
                return;
            }
        }
        //_an.lookupImportMemberType(type_, _rooted, false);
        if (_an.getOptions().isSingleInnerParts()) {
            tryAnalyzeInnerPartsLine(_an, _globalType, _rooted);
            return;
        }
        lookupSimpleType(_an, _rooted, type_);
    }

    private boolean processFctVoid(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels) {
        PartType prev_ = getParent().getFirstChild();
        if (StringList.quickEq(getTypeName().trim(), _an.getStandards().getAliasVoid())) {
            String base_ = prev_.getAnalyzedType();
            base_ = Templates.getIdFromAllTypes(base_);
            if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                setAnalyzedType(getTypeName().trim());
                return true;
            }
            _an.getCurrentBadIndexes().add(getIndexInType());
            return true;
        }
        return false;
    }

    private void lookupSimpleType(Analyzable _an, AccessingImportingBlock _rooted, String _type) {
        String out_ = _an.lookupImportType(_type, _rooted);
        if (out_.isEmpty()) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        analyzeFullType(_an, _rooted, out_);
    }

    private void tryAnalyzeInnerParts(Analyzable _an, String _globalType,
                                      AccessingImportingBlock _rooted) {
        String type_ = getTypeName();
        if (_rooted instanceof RootBlock) {
            RootBlock c = (RootBlock)_rooted;
            StringList allAncestors_ = new StringList();
            RootBlock p_ = c;
            Classes classes_ = _an.getClasses();
            while (p_ != null) {
                allAncestors_.add(p_.getGenericString());
                p_ = p_.getParentType();
            }
            for (String a: allAncestors_) {
                StringList owners_ = TypeUtil.getGenericOwners(false, true, _globalType, a, type_, _an);
                if (owners_.size() == 1) {
                    String genStr_ = owners_.first();
                    String id_ = Templates.getIdFromAllTypes(genStr_);
                    String f_ = Templates.quickFormat(_globalType, a, _an);
                    String in_ = StringList.concat(id_,"..",type_);
                    RootBlock inner_ = classes_.getClassBody(in_);
                    if (inner_.isStaticType()) {
                        setAnalyzedType(StringList.concat(id_,"..",type_));
                        return;
                    }
                    String new_ = Templates.quickFormat(f_, genStr_, _an);
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    return;
                }
            }
        }
        lookupImports(_an, _rooted, type_);
    }
    private void tryAnalyzeInnerPartsLine(Analyzable _an, String _globalType,
                                      AccessingImportingBlock _rooted) {
        String type_ = getTypeName();
        if (_rooted instanceof RootBlock) {
            RootBlock c = (RootBlock)_rooted;
            StringList allAncestors_ = new StringList();
            RootBlock p_ = c;
            while (p_ != null) {
                allAncestors_.add(p_.getFullName());
                p_ = p_.getParentType();
            }
            for (String a: allAncestors_) {
                StringList owners_ = TypeUtil.getOwners(false, true, _globalType, a, type_, false, _an);
                if (owners_.size() == 1) {
                    String new_ = owners_.first();
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    return;
                }
            }
        }
        lookupImports(_an, _rooted, type_);
    }

    private void lookupImports(Analyzable _an, AccessingImportingBlock _rooted, String _type) {
        String res_ = _an.lookupImportMemberType(_type, _rooted, false);
        if (!res_.isEmpty()) {
            setAnalyzedType(res_);
            return;
        }
        _an.getCurrentBadIndexes().add(getIndexInType());
    }

    @Override
    void analyzeAccessibleId(Analyzable _an, CustList<NatTreeMap<Integer,String>> _dels, AccessingImportingBlock _rooted) {
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
            String type_ = getTypeName();
            String in_ = StringList.concat(id_,"..",type_);
            Classes classes_ = _an.getClasses();
            RootBlock inner_ = classes_.getClassBody(in_);
            if (inner_ == null) {
                return;
            }
            if (inner_.isStaticType()) {
                last_.setAnalyzedType(id_);
                setAnalyzedType(StringList.concat(id_,"..",type_));
                return;
            }
            if (!Templates.correctNbParameters(owner_, _an)) {
                return;
            }
            last_.setAnalyzedType(owner_);
            setAnalyzedType(StringList.concat(owner_,"..",type_));
            return;
        }
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (_an.getClasses().isCustomType(type_)) {
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
            if (StringList.quickEq(getTypeName().trim(), _an.getStandards().getAliasVoid())) {
                String base_ = prev_.getAnalyzedType();
                base_ = Templates.getIdFromAllTypes(base_);
                if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                    setAnalyzedType(getTypeName().trim());
                    return;
                }
                return;
            }
        }
        
        String out_;
        if (_an.getOptions().isSingleInnerParts()) {
            out_ = _an.lookupSingleImportType(type_, _rooted);
        } else {
            out_ = _an.lookupImportType(type_, _rooted);
        }
        if (out_.isEmpty()) {
            return;
        }
        setAnalyzedType(out_);
    }
    @Override
    void checkDynExistence(Analyzable _an,CustList<NatTreeMap<Integer, String>>_dels) {
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
                pr_.add(((LeafPartType)f_).exportHeader());
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
            String base_ = ((NamePartType)prev_).getTypeName();
            if (StringList.quickEq(getTypeName().trim(), _an.getStandards().getAliasVoid())) {
                if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                    setImportedTypeName(getTypeName().trim());
                }
            }
        }
    }


}
