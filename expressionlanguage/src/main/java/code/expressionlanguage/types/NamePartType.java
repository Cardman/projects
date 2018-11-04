package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeOwnersDepends;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.BadAccessClass;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.expressionlanguage.options.KeyWords;
import code.sml.RowCol;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

final class NamePartType extends LeafPartType {

    public NamePartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }

    @Override
    public void analyzeDepends(Analyzable _an,
            int _index, CustList<NatTreeMap<Integer, String>> _dels,
            RootBlock _rooted, boolean _exact, RowCol _location) {
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
                InnerPartType par_ = i_;
                if (par_.isRemovedBefore()) {
                    String type_ = getTypeName();
                    RootBlock c = _rooted;
                    CustList<RootBlock> innersCandidates_ = new CustList<RootBlock>();
                    StringList allAncestors_ = new StringList();
                    RootBlock p_ = c.getParentType();
                    Classes classes_ = _an.getClasses();
                    StringList deps_ = new StringList();
                    while (p_ != null) {
                        allAncestors_.add(p_.getFullName());
                        p_ = p_.getParentType();
                    }
                    int ancestorIndex_ = c.getAncestorsIndexes().get(_index);
                    if (i_.getParent() == null) {
                        if (ancestorIndex_ != -1) {
                            String a_ = allAncestors_.get(ancestorIndex_);
                            TypeOwnersDepends res_ = TypeUtil.getOwnersDepends(lastInner_, fullName_, a_, type_, _an);
                            StringList owners_ = res_.getTypeOwners();
                            String in_ = StringList.concat(owners_.first(),"..",type_);
                            RootBlock inner_ = classes_.getClassBody(in_);
                            innersCandidates_.add(inner_);
                            deps_ = res_.getDepends();
                            getTypeNames().addAllElts(deps_);
                            String new_ = owners_.first();
                            setAnalyzedType(StringList.concat(new_,"..",type_));
                            return;
                        }
                        TypeOwnersDepends res_ = _an.lookupImportMemberTypeDeps(type_, _rooted);
                        StringList owners_ = res_.getTypeOwners();
                        if (owners_.size() == 1) {
                            setAnalyzedType(owners_.first());
                            return;
                        }
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(type_);
                        un_.setFileName(_rooted.getFile().getFileName());
                        un_.setRc(_location);
                        _an.getClasses().addError(un_);
                        stopDepends();
                        return;
                    }
                    for (String a: allAncestors_) {
                        TypeOwnersDepends res_ = TypeUtil.getOwnersDepends(lastInner_, fullName_, a, type_, _an);
                        StringList owners_ = res_.getTypeOwners();
                        if (owners_.size() == 1) {
                            String in_ = StringList.concat(owners_.first(),"..",type_);
                            RootBlock inner_ = classes_.getClassBody(in_);
                            innersCandidates_.add(inner_);
                            deps_ = res_.getDepends();
                            getTypeNames().addAllElts(deps_);
                            String new_ = owners_.first();
                            setAnalyzedType(StringList.concat(new_,"..",type_));
                            return;
                        }
                        deps_ = res_.getDepends();
                        getTypeNames().addAllElts(deps_);
                    }
                    TypeOwnersDepends res_ = _an.lookupImportMemberTypeDeps(type_, _rooted);
                    StringList owners_ = res_.getTypeOwners();
                    if (owners_.size() == 1) {
                        setAnalyzedType(owners_.first());
                        return;
                    }
                    //ERROR
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(type_);
                    un_.setFileName(_rooted.getFile().getFileName());
                    un_.setRc(_location);
                    _an.getClasses().addError(un_);
                    stopDepends();
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
            StringList deps_ = new StringList();
            CustList<RootBlock> innersCandidates_ = new CustList<RootBlock>();
            String type_ = getTypeName();
            TypeOwnersDepends res_ = TypeUtil.getOwnersDepends(lastInner_, fullName_, id_, type_, _an);
            StringList owners_ = res_.getTypeOwners();
            if (owners_.size() == 1) {
                String in_ = StringList.concat(owners_.first(),"..",type_);
                RootBlock inner_ = classes_.getClassBody(in_);
                innersCandidates_.add(inner_);
                deps_ = res_.getDepends();
                getTypeNames().addAllElts(deps_);
                String new_ = owners_.first();
                setAnalyzedType(StringList.concat(new_,"..",type_));
                return;
            }
            //ERROR
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(type_);
            un_.setFileName(_rooted.getFile().getFileName());
            un_.setRc(_location);
            _an.getClasses().addError(un_);
            stopDepends();
            return;
        }
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (_an.getClasses().isCustomType(type_)) {
            if (!_rooted.canAccessClass(type_, _an)) {
                BadAccessClass err_ = new BadAccessClass();
                err_.setFileName(_rooted.getFile().getFileName());
                err_.setRc(new RowCol());
                err_.setId(type_);
                _an.getClasses().addError(err_);
            }
            setAnalyzedType(type_);
            return;
        }
        StringList parts_ = StringList.splitStrings(type_, ".");
        KeyWords keyWords_ = _an.getKeyWords();
        String keyWordLang_ = keyWords_.getKeyWordLang();
        if (StringList.quickEq(parts_.first().trim(), keyWordLang_)) {
            if (parts_.size() > 1) {
                String p_ = parts_.last().trim();
                if (_an.getStandards().getStandards().contains(p_)) {
                    setAnalyzedType(p_);
                    return;
                }
                String out_ = _an.getStandards().getAliasObject();
                setAnalyzedType(out_);
                UnknownClassName un_ = new UnknownClassName();
                un_.setClassName(type_);
                un_.setFileName(_rooted.getFile().getFileName());
                un_.setRc(_location);
                _an.getClasses().addError(un_);
                stopDepends();
                return;
            }
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
                    if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                        setAnalyzedType(getTypeName().trim());
                        return;
                    }
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(type_);
                    un_.setFileName(_rooted.getFile().getFileName());
                    un_.setRc(_location);
                    _an.getClasses().addError(un_);
                    String out_ = _an.getStandards().getAliasObject();
                    setAnalyzedType(out_);
                    stopDepends();
                    return;
                }
            }
        }
        
        String out_ = _an.lookupImportType(type_, _rooted);
        if (out_.isEmpty()) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(type_);
            un_.setFileName(_rooted.getFile().getFileName());
            un_.setRc(_location);
            _an.getClasses().addError(un_);
            out_ = _an.getStandards().getAliasObject();
            stopDepends();
        }
        if (!_rooted.canAccessClass(out_, _an)) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(type_);
            un_.setFileName(_rooted.getFile().getFileName());
            un_.setRc(_location);
            _an.getClasses().addError(un_);
            out_ = _an.getStandards().getAliasObject();
            stopDepends();
        }
        setAnalyzedType(out_);
    }

    @Override
    public void analyzeInherits(Analyzable _an, int _index,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            RootBlock _rooted, boolean _exact,
            boolean _protected, RowCol _location) {
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
                InnerPartType par_ = i_;
                if (par_.isRemovedBefore()) {
                    String type_ = getTypeName();
                    RootBlock c = _rooted;
                    StringMap<String> allPossibleDirectSuperTypes_ = new StringMap<String>();
                    CustList<RootBlock> innersCandidates_ = new CustList<RootBlock>();
                    StringList allAncestors_ = new StringList();
                    RootBlock p_ = c.getParentType();
                    Classes classes_ = _an.getClasses();
                    while (p_ != null) {
                        allAncestors_.add(p_.getFullName());
                        p_ = p_.getParentType();
                    }
                    if (!allAncestors_.isEmpty() && _globalType == null) {
                        return;
                    }
                    int ancestorIndex_ = c.getAncestorsIndexes().get(_index);
                    if (i_.getParent() == null) {
                        if (ancestorIndex_ != -1) {
                            String a_ = allAncestors_.get(ancestorIndex_);
                            StringList owners_ = TypeUtil.getOwners(true, lastInner_, _globalType, a_, type_, false, _an);
                            RootBlock g_ = classes_.getClassBody(a_);
                            String genStr_ = g_.getGenericString();
                            String f_ = Templates.quickFormat(_globalType, genStr_, _an);
                            allPossibleDirectSuperTypes_.put(owners_.first(),f_);
                            String in_ = StringList.concat(owners_.first(),"..",type_);
                            RootBlock inner_ = classes_.getClassBody(in_);
                            if (inner_.isStaticType()) {
                                String new_ = allPossibleDirectSuperTypes_.getKey(0);
                                setAnalyzedType(StringList.concat(new_,"..",type_));
                                return;
                            }
                            if (!_exact) {
                                String new_ = allPossibleDirectSuperTypes_.getKey(0);
                                setAnalyzedType(StringList.concat(new_,"..",type_));
                                return;
                            }
                            String sup_ = allPossibleDirectSuperTypes_.getKey(0);
                            String sub_ = allPossibleDirectSuperTypes_.getValue(0);
                            String new_ = Templates.getFullTypeByBases(sub_, sup_, _an);
                            setAnalyzedType(StringList.concat(new_,"..",type_));
                            return;
                        }
                        String owner_ = _an.lookupImportMemberType(type_, _rooted,true);
                        if (!owner_.isEmpty()) {
                            setAnalyzedType(owner_);
                        }
                        return;
                    }
                    for (String a: allAncestors_) {
                        StringList owners_ = TypeUtil.getOwners(true, lastInner_, _globalType, a, type_, false, _an);
                        if (owners_.size() == 1) {
                            RootBlock g_ = classes_.getClassBody(a);
                            String genStr_ = g_.getGenericString();
                            String f_ = Templates.quickFormat(_globalType, genStr_, _an);
                            allPossibleDirectSuperTypes_.put(owners_.first(),f_);
                            String in_ = StringList.concat(owners_.first(),"..",type_);
                            RootBlock inner_ = classes_.getClassBody(in_);
                            innersCandidates_.add(inner_);
                            break;
                        }
                    }
                    if (innersCandidates_.size() == 1) {
                        if (innersCandidates_.first().isStaticType()) {
                            String new_ = allPossibleDirectSuperTypes_.getKey(0);
                            setAnalyzedType(StringList.concat(new_,"..",type_));
                            return;
                        }
                        if (!_exact) {
                            String new_ = allPossibleDirectSuperTypes_.getKey(0);
                            setAnalyzedType(StringList.concat(new_,"..",type_));
                            return;
                        }
                        String sup_ = allPossibleDirectSuperTypes_.getKey(0);
                        String sub_ = allPossibleDirectSuperTypes_.getValue(0);
                        String new_ = Templates.getFullTypeByBases(sub_, sup_, _an);
                        setAnalyzedType(StringList.concat(new_,"..",type_));
                        return;
                    }
                    String owner_ = _an.lookupImportMemberType(type_, _rooted,true);
                    if (!owner_.isEmpty()) {
                        setAnalyzedType(owner_);
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
            String id_ = Templates.getIdFromAllTypes(owner_);
            Classes classes_ = _an.getClasses();
            String type_ = getTypeName();
            StringList foundOwners_ = TypeUtil.getOwners(true, lastInner_, _globalType, id_, type_, false, _an);
            if (foundOwners_.size() == 1) {
                String in_ = StringList.concat(foundOwners_.first(),"..",type_);
                RootBlock inner_ = classes_.getClassBody(in_);
                if (inner_.isStaticType()) {
                    String new_ = foundOwners_.first();
                    last_.setAnalyzedType(new_);
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    return;
                }
                if (!Templates.correctNbParameters(owner_, _an)) {
                    String new_ = foundOwners_.first();
                    last_.setAnalyzedType(new_);
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    return;
                }
                String new_ = Templates.getFullTypeByBases(owner_, foundOwners_.first(), _an);
                if (new_ == null) {
                    return;
                }
                last_.setAnalyzedType(new_);
                setAnalyzedType(StringList.concat(new_,"..",type_));
                return;
            }
            return;
        }
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (_an.getClasses().isCustomType(type_)) {
            if (!_rooted.canAccessClass(type_, _an)) {
                return;
            }
            setAnalyzedType(type_);
            return;
        }
        StringList parts_ = StringList.splitStrings(type_, ".");
        KeyWords keyWords_ = _an.getKeyWords();
        String keyWordLang_ = keyWords_.getKeyWordLang();
        if (StringList.quickEq(parts_.first().trim(), keyWordLang_)) {
            if (parts_.size() > 1) {
                String p_ = parts_.last().trim();
                if (_an.getStandards().getStandards().contains(p_)) {
                    setAnalyzedType(p_);
                    return;
                }
                return;
            }
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
                    if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                        setAnalyzedType(getTypeName().trim());
                        return;
                    }
                    return;
                }
            }
        }
        
        String out_ = _an.lookupImportType(type_, _rooted);
        if (out_.isEmpty()) {
            return;
        }
        if (!_rooted.canAccessClass(out_, _an)) {
            return;
        }
        setAnalyzedType(out_);
    }
    @Override
    public void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact, boolean _protectedInc, RowCol _location) {
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
                    if (_rooted instanceof RootBlock) {
                        RootBlock c = (RootBlock)_rooted;
                        StringMap<String> allPossibleDirectSuperTypes_ = new StringMap<String>();
                        CustList<RootBlock> innersCandidates_ = new CustList<RootBlock>();
                        StringList allAncestors_ = new StringList();
                        RootBlock p_ = c;
                        Classes classes_ = _an.getClasses();
                        while (p_ != null) {
                            allAncestors_.add(p_.getFullName());
                            p_ = p_.getParentType();
                        }
                        if (!allAncestors_.isEmpty() && _globalType == null) {
                            return;
                        }
                        for (String a: allAncestors_) {
                            StringList owners_ = TypeUtil.getOwners(false, _protectedInc, _globalType, a, type_, false, _an);
                            if (owners_.size() == 1) {
                                GeneType g_ = _an.getClassBody(a);
                                String genStr_ = g_.getGenericString();
                                String f_ = Templates.quickFormat(_globalType, genStr_, _an);
                                allPossibleDirectSuperTypes_.put(owners_.first(),f_);
                                String in_ = StringList.concat(owners_.first(),"..",type_);
                                RootBlock inner_ = classes_.getClassBody(in_);
                                innersCandidates_.add(inner_);
                                break;
                            }
                        }
                        if (innersCandidates_.size() == 1) {
                            if (innersCandidates_.first().isStaticType()) {
                                String new_ = allPossibleDirectSuperTypes_.getKey(0);
                                setAnalyzedType(StringList.concat(new_,"..",type_));
                                return;
                            }
                            if (!_exact) {
                                String new_ = allPossibleDirectSuperTypes_.getKey(0);
                                setAnalyzedType(StringList.concat(new_,"..",type_));
                                return;
                            }
                            String sup_ = allPossibleDirectSuperTypes_.getKey(0);
                            String sub_ = allPossibleDirectSuperTypes_.getValue(0);
                            String new_ = Templates.getFullTypeByBases(sub_, sup_, _an);
                            setAnalyzedType(StringList.concat(new_,"..",type_));
                            return;
                        }
                    }
                    if (_protectedInc) {
                        String res_ = _an.lookupImportMemberType(type_, _rooted, false);
                        if (!res_.isEmpty()) {
                            setAnalyzedType(res_);
                            return;
                        }
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
            String id_ = Templates.getIdFromAllTypes(owner_);
            Classes classes_ = _an.getClasses();
            String type_ = getTypeName();
            StringList foundOwners_ = TypeUtil.getOwners(false, _protectedInc, _globalType, id_, type_, false, _an);
            if (foundOwners_.size() == 1) {
                String in_ = StringList.concat(foundOwners_.first(),"..",type_);
                RootBlock inner_ = classes_.getClassBody(in_);
                if (inner_.isStaticType()) {
                    String new_ = foundOwners_.first();
                    last_.setAnalyzedType(new_);
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    return;
                }
                if (!Templates.correctNbParameters(owner_, _an)) {
                    String new_ = foundOwners_.first();
                    last_.setAnalyzedType(new_);
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    return;
                }
                String new_ = Templates.getFullTypeByBases(owner_, foundOwners_.first(), _an);
                if (new_ == null) {
                    return;
                }
                last_.setAnalyzedType(new_);
                setAnalyzedType(StringList.concat(new_,"..",type_));
                return;
            }
            return;
        }
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (_an.getClasses().isCustomType(type_)) {
            if (!_rooted.canAccessClass(type_, _an)) {
                return;
            }
            setAnalyzedType(type_);
            return;
        }
        StringList parts_ = StringList.splitStrings(type_, ".");
        KeyWords keyWords_ = _an.getKeyWords();
        String keyWordLang_ = keyWords_.getKeyWordLang();
        if (StringList.quickEq(parts_.first().trim(), keyWordLang_)) {
            if (parts_.size() > 1) {
                String p_ = parts_.last().trim();
                if (_an.getStandards().getStandards().contains(p_)) {
                    setAnalyzedType(p_);
                    return;
                }
                return;
            }
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
                    if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                        setAnalyzedType(getTypeName().trim());
                        return;
                    }
                    return;
                }
            }
        }
        
        String out_ = _an.lookupImportType(type_, _rooted);
        if (out_.isEmpty()) {
            return;
        }
        if (!_rooted.canAccessClass(out_, _an)) {
            return;
        }
        setAnalyzedType(out_);
    }
    @Override
    public void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact) {
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
                    if (_rooted instanceof RootBlock) {
                        RootBlock c = (RootBlock)_rooted;
                        StringMap<String> allPossibleDirectSuperTypes_ = new StringMap<String>();
                        CustList<RootBlock> innersCandidates_ = new CustList<RootBlock>();
                        StringList allAncestors_ = new StringList();
                        RootBlock p_ = c;
                        Classes classes_ = _an.getClasses();
                        while (p_ != null) {
                            allAncestors_.add(p_.getFullName());
                            p_ = p_.getParentType();
                        }
                        for (String a: allAncestors_) {
                            StringList owners_ = TypeUtil.getOwners(false, true, _globalType, a, type_, false, _an);
                            if (owners_.size() == 1) {
                                GeneType g_ = _an.getClassBody(a);
                                String genStr_ = g_.getGenericString();
                                String f_ = Templates.quickFormat(_globalType, genStr_, _an);
                                allPossibleDirectSuperTypes_.put(owners_.first(),f_);
                                String in_ = StringList.concat(owners_.first(),"..",type_);
                                RootBlock inner_ = classes_.getClassBody(in_);
                                innersCandidates_.add(inner_);
                                break;
                            }
                        }
                        if (innersCandidates_.size() == 1) {
                            if (innersCandidates_.first().isStaticType()) {
                                String new_ = allPossibleDirectSuperTypes_.getKey(0);
                                setAnalyzedType(StringList.concat(new_,"..",type_));
                                return;
                            }
                            if (!_exact) {
                                String new_ = allPossibleDirectSuperTypes_.getKey(0);
                                setAnalyzedType(StringList.concat(new_,"..",type_));
                                return;
                            }
                            String sup_ = allPossibleDirectSuperTypes_.getKey(0);
                            String sub_ = allPossibleDirectSuperTypes_.getValue(0);
                            String new_ = Templates.getFullTypeByBases(sub_, sup_, _an);
                            setAnalyzedType(StringList.concat(new_,"..",type_));
                            return;
                        }
                    }
                    String res_ = _an.lookupImportMemberType(type_, _rooted, false);
                    if (!res_.isEmpty()) {
                        setAnalyzedType(res_);
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
            String id_ = Templates.getIdFromAllTypes(owner_);
            Classes classes_ = _an.getClasses();
            String type_ = getTypeName();
            StringList foundOwners_ = TypeUtil.getOwners(false, true, _globalType, id_, type_, false, _an);
            if (foundOwners_.size() == 1) {
                String in_ = StringList.concat(foundOwners_.first(),"..",type_);
                RootBlock inner_ = classes_.getClassBody(in_);
                if (inner_.isStaticType()) {
                    String new_ = foundOwners_.first();
                    last_.setAnalyzedType(new_);
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    return;
                }
                if (!Templates.correctNbParameters(owner_, _an)) {
                    String new_ = foundOwners_.first();
                    last_.setAnalyzedType(new_);
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    return;
                }
                String new_ = Templates.getFullTypeByBases(owner_, foundOwners_.first(), _an);
                if (new_ == null) {
                    return;
                }
                last_.setAnalyzedType(new_);
                setAnalyzedType(StringList.concat(new_,"..",type_));
                return;
            }
            return;
        }
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (_an.getClasses().isCustomType(type_)) {
            if (!_rooted.canAccessClass(type_, _an)) {
                return;
            }
            setAnalyzedType(type_);
            return;
        }
        StringList parts_ = StringList.splitStrings(type_, ".");
        KeyWords keyWords_ = _an.getKeyWords();
        String keyWordLang_ = keyWords_.getKeyWordLang();
        if (StringList.quickEq(parts_.first().trim(), keyWordLang_)) {
            if (parts_.size() > 1) {
                String p_ = parts_.last().trim();
                if (_an.getStandards().getStandards().contains(p_)) {
                    setAnalyzedType(p_);
                }
                return;
            }
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
                    if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                        setAnalyzedType(getTypeName().trim());
                        return;
                    }
                    return;
                }
            }
        }
        
        String out_ = _an.lookupImportType(type_, _rooted);
        if (out_.isEmpty()) {
            return;
        }
        if (!_rooted.canAccessClass(out_, _an)) {
            return;
        }
        setAnalyzedType(out_);
    }

    @Override
    public void analyzeAccessibleId(Analyzable _an, CustList<NatTreeMap<Integer,String>> _dels, AccessingImportingBlock _rooted) {
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
                last_.setAnalyzedType(id_);
                setAnalyzedType(StringList.concat(id_,"..",type_));
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
        StringList parts_ = StringList.splitStrings(type_, ".");
        KeyWords keyWords_ = _an.getKeyWords();
        String keyWordLang_ = keyWords_.getKeyWordLang();
        if (StringList.quickEq(parts_.first().trim(), keyWordLang_)) {
            if (parts_.size() > 1) {
                String p_ = parts_.last().trim();
                if (_an.getStandards().getStandards().contains(p_)) {
                    setAnalyzedType(p_);
                    return;
                }
                return;
            }
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
                    if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                        setAnalyzedType(getTypeName().trim());
                        return;
                    }
                    return;
                }
            }
        }
        
        String out_ = _an.lookupImportType(type_, _rooted);
        if (out_.isEmpty()) {
            return;
        }
        setAnalyzedType(out_);
    }
    @Override
    public void checkDynExistence(Analyzable _an,CustList<NatTreeMap<Integer, String>>_dels) {
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
        StringList parts_ = StringList.splitStrings(type_, ".");
        KeyWords keyWords_ = _an.getKeyWords();
        String keyWordLang_ = keyWords_.getKeyWordLang();
        if (StringList.quickEq(parts_.first().trim(), keyWordLang_)) {
            if (parts_.size() > 1) {
                String p_ = parts_.last().trim();
                if (_an.getStandards().getStandards().contains(p_)) {
                    setImportedTypeName(p_);
                }
                return;
            }
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
                    if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                        setImportedTypeName(getTypeName().trim());
                    }
                }
            }
        }
    }


}
