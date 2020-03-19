package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.*;
import code.expressionlanguage.methods.*;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

final class NamePartType extends LeafPartType {

    NamePartType(ParentPartType _parent, int _index, int _indexInType, String _type) {
        super(_parent, _index, _indexInType, _type);
    }

    @Override
    void analyze(Analyzable _an, CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
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
                    tryAnalyzeInnerParts(_an, _globalType, _local,_rooted
                    );
                    return;
                }
            }
            while (part_ != null) {
                previous_.add(part_);
                part_ = part_.getPreviousSibling();
            }
        }
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (!previous_.isEmpty()) {
            previous_ = previous_.getReverse();
            PartType last_ = previous_.last();
            String owner_ = last_.getAnalyzedType();
            Classes classes_ = _an.getClasses();
            StringList foundOwners_ = TypeUtil.getGenericOwners(owner_, type_, _an);
            if (foundOwners_.onlyOneElt()) {
                String idOwner_= Templates.getIdFromAllTypes(foundOwners_.first());
                String in_ = StringList.concat(idOwner_,"..",type_);
                RootBlock inner_ = classes_.getClassBody(in_);
                if (inner_.isStaticType()) {
                    String new_ = foundOwners_.first();
                    last_.setAnalyzedType(new_);
                    setAnalyzedType(StringList.concat(idOwner_,"..",type_));
                    checkAccess(_an,_rooted,owner_);
                    return;
                }
                if (!Templates.correctNbParameters(owner_, _an)) {
                    return;
                }
                String new_ = Templates.quickFormat(owner_, foundOwners_.first(), _an);
                last_.setAnalyzedType(new_);
                setAnalyzedType(StringList.concat(new_,"..",type_));
                checkAccess(_an,_rooted,owner_);
                return;
            }
            return;
        }
        String id_ = Templates.getIdFromAllTypes(type_);
        RootBlock root_ = _an.getClasses().getClassBody(id_);
        if (root_ != null) {
            analyzeFullType(_an, _local, type_);
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
            tryAnalyzeInnerParts(_an, _globalType, _local,_rooted
            );
            return;
        }
        lookupSimpleType(_an, _local,_rooted, type_,false);
    }

    @Override
    void analyzeTemplate(Analyzable _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {


        InnerPartType i_ = null;
        PartType parCur_ = null;
        if (getParent() instanceof InnerPartType) {
            parCur_ = this;
            i_ = (InnerPartType) getParent();
        } else if (getParent() instanceof TemplatePartType && getParent().getParent() instanceof InnerPartType && getIndex() == 0) {
            parCur_ = getParent();
            i_ = (InnerPartType) getParent().getParent();
        }
        String type_ = getTypeName();
        if (i_ != null) {
            PartType part_ = parCur_.getPreviousSibling();
            if (part_ != null) {
                type_ = StringList.concat(part_.getAnalyzedType(), "..", type_);
            }
        }
        if (_dels.isEmpty() || _dels.last().size() -1 == getIndex()) {
            if (!Templates.correctNbParameters(type_, _an)) {
                return;
            }
        }
        setAnalyzedType(type_);
    }

    private void analyzeFullType(Analyzable _an, AccessingImportingBlock _root, String _type) {
        setAnalyzedType(_type);
        checkAccess(_an,_root);
    }

    @Override
    void analyzeLine(Analyzable _an, CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
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
                    tryAnalyzeInnerPartsLine(_an, _local,_rooted);
                    return;
                }
            }
            while (part_ != null) {
                previous_.add(part_);
                part_ = part_.getPreviousSibling();
            }
        }
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (!previous_.isEmpty()) {
            previous_ = previous_.getReverse();
            PartType last_ = previous_.last();
            String owner_ = last_.getAnalyzedType();
            String id_ = Templates.getIdFromAllTypes(owner_);
            StringList foundOwners_ = TypeUtil.getOwners(id_, type_, _an);
            if (foundOwners_.onlyOneElt()) {
                String new_ = foundOwners_.first();
                last_.setAnalyzedType(new_);
                setAnalyzedType(StringList.concat(new_,"..",type_));
                checkAccess(_an,_rooted,id_);
            }
            return;
        }
        String id_ = Templates.getIdFromAllTypes(type_);
        RootBlock root_ = _an.getClasses().getClassBody(id_);
        if (root_ != null) {
            analyzeFullType(_an, _local, type_);
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
            tryAnalyzeInnerPartsLine(_an, _local,_rooted);
            return;
        }
        lookupSimpleType(_an, _local,_rooted, type_,true);
    }

    private boolean processFctVoid(Analyzable _an, CustList<IntTreeMap< String>> _dels) {
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

    private void lookupSimpleType(Analyzable _an, AccessingImportingBlock _local, AccessingImportingBlock _rooted, String _type, boolean _line) {
        String out_ = _an.lookupImportType(_type, _rooted, _line);
        if (out_.isEmpty()) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        String id_ = Templates.getIdFromAllTypes(out_);
        RootBlock root_ = _an.getClasses().getClassBody(id_);
        if (root_ != null) {
            analyzeFullType(_an, _local, out_);
        } else {
            setAnalyzedType(out_);
        }
    }

    private void tryAnalyzeInnerParts(Analyzable _an, String _globalType,
                                      AccessingImportingBlock _local,
                                      AccessingImportingBlock _rooted) {
        String type_ = getTypeName().trim();
        if (_local instanceof RootBlock) {
            RootBlock c = (RootBlock)_local;
            StringList allAncestors_ = new StringList();
            RootBlock p_ = c;
            Classes classes_ = _an.getClasses();
            while (p_ != null) {
                allAncestors_.add(p_.getGenericString());
                p_ = p_.getParentType();
            }
            for (String a: allAncestors_) {
                StringList owners_ = TypeUtil.getGenericOwners(a, type_, _an);
                if (owners_.onlyOneElt()) {
                    String genStr_ = owners_.first();
                    String id_ = Templates.getIdFromAllTypes(genStr_);
                    String f_ = Templates.quickFormat(_globalType, a, _an);
                    String in_ = StringList.concat(id_,"..",type_);
                    RootBlock inner_ = classes_.getClassBody(in_);
                    if (inner_.isStaticType()) {
                        setAnalyzedType(StringList.concat(id_,"..",type_));
                        checkAccess(_an,_rooted,a);
                        return;
                    }
                    if (_an.isStaticAccess()) {
                        return;
                    }
                    String new_ = Templates.quickFormat(f_, genStr_, _an);
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    checkAccess(_an,_rooted,a);
                    return;
                }
            }
        }
        lookupImports(_an, _local,_rooted, type_,false);
    }
    private void tryAnalyzeInnerPartsLine(Analyzable _an,
                                          AccessingImportingBlock _local,
                                          AccessingImportingBlock _rooted) {
        String type_ = getTypeName().trim();
        if (_local instanceof RootBlock) {
            RootBlock c = (RootBlock)_local;
            StringList allAncestors_ = new StringList();
            RootBlock p_ = c;
            while (p_ != null) {
                allAncestors_.add(p_.getFullName());
                p_ = p_.getParentType();
            }
            for (String a: allAncestors_) {
                StringList owners_ = TypeUtil.getOwners(a, type_, _an);
                if (owners_.onlyOneElt()) {
                    String new_ = owners_.first();
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    checkAccess(_an,_rooted,a);
                    return;
                }
            }
        }
        lookupImports(_an, _local,_rooted, type_,true);
    }

    private void lookupImports(Analyzable _an, AccessingImportingBlock _local, AccessingImportingBlock _rooted, String _type, boolean _line) {
        String res_ = _an.lookupImportMemberType(_type, _rooted, false, new StringList());
        if (!res_.isEmpty()) {
            if (!_line) {
                if (_an.isStaticAccess()) {
                    if (!_an.getClassBody(res_).isStaticType()) {
                        _an.getCurrentBadIndexes().add(getIndexInType());
                        return;
                    }
                }
            }
            setAnalyzedType(res_);
            checkAccess(_an,_local);
            return;
        }
        _an.getCurrentBadIndexes().add(getIndexInType());
    }

    private void checkAccess(Analyzable _an,AccessingImportingBlock _global) {
        StringList parts_ = Templates.getAllInnerTypes(getAnalyzedType());
        String idFound_ = Templates.getIdFromAllTypes(parts_.first());
        StringBuilder id_ = new StringBuilder(idFound_);
        StringBuilder idOwner_ = new StringBuilder(idFound_);
        if (checkAccess(_an,_global,idFound_,idFound_,false)) {
            return;
        }
        int len_ = parts_.size();
        for (int i = 1; i < len_; i++) {
            idFound_ = Templates.getIdFromAllTypes(parts_.get(i));
            id_.append("..");
            id_.append(idFound_);
            if (checkAccess(_an,_global,id_.toString(),idOwner_.toString(),true)) {
                setAnalyzedType("");
                return;
            }
            idOwner_.append("..");
            idOwner_.append(idFound_);
        }
    }
    private void checkAccess(Analyzable _an,AccessingImportingBlock _global, String _owner) {
        if (checkAccess(_an,_global,getAnalyzedType(),_owner,true)){
            setAnalyzedType("");
        }
    }
    private boolean checkAccess(Analyzable _an,AccessingImportingBlock _global,String _found, String _owner, boolean _check) {
        String idOwner_ = Templates.getIdFromAllTypes(_owner);
        String idFound_ = Templates.getIdFromAllTypes(_found);
        RootBlock owner_ = _an.getClasses().getClassBody(idOwner_);
        RootBlock found_ = _an.getClasses().getClassBody(idFound_);
        if (found_ == null) {
            return false;
        }
        boolean err_ = false;
        if (_an.isHidden(_global,found_)) {
            setAnalyzedType("");
            _an.getCurrentBadIndexes().add(getIndexInType());
            err_ = true;
        }
        if (_check && owner_.isTypeHidden(found_,_an)) {
            setAnalyzedType("");
            _an.getCurrentBadIndexes().add(getIndexInType());
            err_ = true;
        }
        return err_;
    }

    @Override
    void analyzeAccessibleId(Analyzable _an, CustList<IntTreeMap<String>> _dels, AccessingImportingBlock _rooted) {
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
        String type_ = getTypeName();
        type_ = ContextEl.removeDottedSpaces(type_);
        if (!previous_.isEmpty()) {
            previous_ = previous_.getReverse();
            PartType last_ = previous_.last();
            String owner_ = last_.getAnalyzedType();
            String id_ = Templates.getIdFromAllTypes(owner_);
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
        
        String out_ = _an.lookupImportType(type_,_rooted,true);
        if (out_.isEmpty()) {
            return;
        }
        setAnalyzedType(out_);
    }
    @Override
    void checkDynExistence(Analyzable _an,CustList<IntTreeMap< String>>_dels) {
        CustList<String> pr_ = new CustList<String>();
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
            type_ = StringList.concat(StringList.join(pr_, ".."),"..",type_);
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
            String base_ = ((NamePartType)prev_).exportHeader();
            if (StringList.quickEq(getTypeName().trim(), _an.getStandards().getAliasVoid())) {
                if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                    setImportedTypeName(getTypeName().trim());
                }
            }
        }
    }


}
