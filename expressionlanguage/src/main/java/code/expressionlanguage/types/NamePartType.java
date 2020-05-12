package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.inherits.*;
import code.expressionlanguage.methods.*;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

final class NamePartType extends LeafPartType {

    NamePartType(ParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType, _type, _previousSeparator);
    }

    @Override
    void analyze(Analyzable _an, CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        if (skipGenericInners(_an,_dels, _rooted)) {
            return;
        }
        tryAnalyzeInnerParts(_an, _local,_rooted);
    }

    private boolean skipGenericInners(Analyzable _an, CustList<IntTreeMap<String>> _dels, AccessingImportingBlock _rooted) {
        PartType part_ = getPreviousPartType();
        String type_ = getTypeName();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (part_ != null) {
            analyzeGenericOnwer(_an, _rooted, part_, type_);
            return true;
        }
        return analyzeFull(_an, _dels, _rooted, type_);
    }
    private void analyzeGenericOnwer(Analyzable _an, AccessingImportingBlock _rooted, PartType _part, String _type) {
        String owner_ = _part.getAnalyzedType();
        Classes classes_ = _an.getClasses();
        if (StringList.quickEq("..",getPreviousSeparator())) {
            StringList foundOwners_ = TypeUtil.getEnumOwners(owner_, _type, _an);
            if (!foundOwners_.onlyOneElt()) {
                return;
            }
            String idOwner_= Templates.getIdFromAllTypes(foundOwners_.first());
            setAnalyzedType(StringList.concat(idOwner_,"-", _type));
            checkAccess(_an,_rooted,owner_);
            return;
        }
        StringList foundOwners_ = TypeUtil.getGenericOwners(owner_, _type, _an);
        if (!foundOwners_.onlyOneElt()) {
            return;
        }
        String idOwner_= Templates.getIdFromAllTypes(foundOwners_.first());
        String in_ = StringList.concat(idOwner_,"..", _type);
        RootBlock inner_ = classes_.getClassBody(in_);
        if (inner_.isStaticType()) {
            setAnalyzedType(StringList.concat(idOwner_,"..", _type));
            checkAccess(_an,_rooted,owner_);
            return;
        }
        if (!Templates.correctNbParameters(owner_, _an)) {
            return;
        }
        setAnalyzedType(StringList.concat(foundOwners_.first(),"..", _type));
        checkAccess(_an,_rooted,owner_);
    }

    @Override
    void analyzeTemplate(Analyzable _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        String type_ = getTypeName();
        PartType part_ = getPreviousPartType();
        if (part_ != null) {
            type_ = StringList.concat(part_.getAnalyzedType(), getPreviousSeparator(), type_);
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
    void analyzeLine(Analyzable _an, ReadyTypes _ready,CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        if (skipInners(_an,_ready,_dels, _rooted)) {
            return;
        }
        tryAnalyzeInnerPartsLine(_an, _ready,_local,_rooted);
    }

    private boolean skipInners(Analyzable _an, ReadyTypes _ready,CustList<IntTreeMap<String>> _dels, AccessingImportingBlock _rooted) {
        PartType part_ = getPreviousPartType();
        String type_ = getTypeName();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (part_ != null) {
            analyzeOnwer(_an, _ready,_rooted, part_, type_);
            return true;
        }
        return analyzeFull(_an, _dels, _rooted, type_);
    }

    private boolean analyzeFull(Analyzable _an, CustList<IntTreeMap<String>> _dels, AccessingImportingBlock _rooted, String _type) {
        String id_ = Templates.getIdFromAllTypes(_type);
        RootBlock root_ = _an.getClasses().getClassBody(id_);
        if (root_ != null) {
            analyzeFullType(_an, _rooted, _type);
            return true;
        }
        if (_an.getStandards().getStandards().contains(_type)) {
            setAnalyzedType(_type);
            return true;
        }
        if (PrimitiveTypeUtil.isPrimitive(_type, _an)) {
            setAnalyzedType(_type);
            return true;
        }
        return processFctVoid(_an, _dels);
    }

    private void analyzeOnwer(Analyzable _an, ReadyTypes _ready,AccessingImportingBlock _rooted, PartType _part, String _type) {
        String owner_ = _part.getAnalyzedType();
        String id_ = Templates.getIdFromAllTypes(owner_);
        if (!_ready.isReady(id_)) {
            return;
        }
        if (StringList.quickEq("..",getPreviousSeparator())) {
            StringList foundOwners_ = TypeUtil.getEnumOwners(id_, _type, _an);
            if (!foundOwners_.onlyOneElt()) {
                return;
            }
            String idOwner_= Templates.getIdFromAllTypes(foundOwners_.first());
            setAnalyzedType(StringList.concat(idOwner_,"-", _type));
            checkAccess(_an,_rooted,id_);
            return;
        }
        StringList foundOwners_ = TypeUtil.getOwners(id_, _type, _an);
        if (foundOwners_.onlyOneElt()) {
            String new_ = foundOwners_.first();
            setAnalyzedType(StringList.concat(new_,"..", _type));
            checkAccess(_an,_rooted,id_);
        }
    }

    private boolean processFctVoid(Analyzable _an, CustList<IntTreeMap< String>> _dels) {
        ParentPartType par_ = getParent();
        if (!(par_ instanceof TemplatePartType)) {
            return false;
        }
        PartType prev_ = par_.getFirstChild();
        if (StringList.quickEq(getTypeName().trim(), _an.getStandards().getAliasVoid())) {
            String base_ = prev_.getAnalyzedType();
            base_ = Templates.getIdFromAllTypes(base_);
            if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                setAnalyzedType(getTypeName().trim());
            }
            return true;
        }
        return false;
    }

    private void tryAnalyzeInnerParts(Analyzable _an,
                                      AccessingImportingBlock _local,
                                      AccessingImportingBlock _rooted) {
        if (_local instanceof RootBlock) {
            if (skipGenericImports(_an, (RootBlock)_local,_rooted)) {
                return;
            }
        }
        lookupImports(_an, _rooted, false, new AlwaysReadyTypes());
    }
    private boolean skipGenericImports(Analyzable _an,
                                       RootBlock _local,
                                       AccessingImportingBlock _rooted) {
        String type_ = getTypeName().trim();
        RootBlock p_ = _local;
        Classes classes_ = _an.getClasses();
        StringList allAncestors_ = new StringList();
        while (p_ != null) {
            allAncestors_.add(p_.getGenericString());
            p_ = p_.getParentType();
        }
        for (String a: allAncestors_) {
            StringList owners_ = TypeUtil.getGenericOwners(a, type_, _an);
            if (owners_.isEmpty()) {
                continue;
            }
            if (owners_.onlyOneElt()) {
                String genStr_ = owners_.first();
                String id_ = Templates.getIdFromAllTypes(genStr_);
                String in_ = StringList.concat(id_,"..",type_);
                RootBlock inner_ = classes_.getClassBody(in_);
                if (inner_.isStaticType()) {
                    setAnalyzedType(StringList.concat(id_,"..",type_));
                    checkAccess(_an,_rooted,a);
                    return true;
                }
                if (_an.isStaticAccess()) {
                    return true;
                }
                setAnalyzedType(StringList.concat(genStr_,"..",type_));
                checkAccess(_an,_rooted,a);
            }
            return true;
        }
        return false;
    }
    private void tryAnalyzeInnerPartsLine(Analyzable _an,
                                          ReadyTypes _ready,
                                          AccessingImportingBlock _local,
                                          AccessingImportingBlock _rooted) {
        if (_local instanceof RootBlock) {
            if (skipImports(_an,_ready,(RootBlock)_local,_rooted)) {
                return;
            }
        }
        lookupImports(_an, _rooted, true,_ready);
    }

    private boolean skipImports(Analyzable _an,
                                ReadyTypes _ready,
                                RootBlock _local,
                                AccessingImportingBlock _rooted) {
        String type_ = getTypeName().trim();
        RootBlock p_ = _local;
        StringList allAncestors_ = new StringList();
        while (p_ != null) {
            allAncestors_.add(p_.getFullName());
            p_ = p_.getParentType();
        }
        for (String a: allAncestors_) {
            if (!_ready.isReady(a)) {
                return true;
            }
            StringList owners_ = TypeUtil.getOwners(a, type_, _an);
            if (owners_.isEmpty()) {
                continue;
            }
            if (owners_.onlyOneElt()) {
                String new_ = owners_.first();
                setAnalyzedType(StringList.concat(new_,"..",type_));
                checkAccess(_an,_rooted,a);
            }
            return true;
        }
        return false;
    }
    private void lookupImports(Analyzable _an, AccessingImportingBlock _rooted, boolean _line, ReadyTypes _ready) {
        String type_ = getTypeName().trim();
        String res_ = ResolvingImportTypes.lookupImportType(_an,type_, _rooted, _ready);
        if (!res_.isEmpty()) {
            if (!_line) {
                if (_an.isStaticAccess()) {
                    if (!_an.getContextEl().getClassBody(res_).isStaticType()) {
                        return;
                    }
                }
            }
            setAnalyzedType(res_);
            checkAccess(_an,_rooted);
        }
    }

    private void checkAccess(Analyzable _an, AccessingImportingBlock _global) {
        StringList parts_ = Templates.getAllInnerTypes(getAnalyzedType());
        String idFound_ = Templates.getIdFromAllTypes(parts_.first());
        StringBuilder id_ = new StringBuilder(idFound_);
        StringBuilder idOwner_ = new StringBuilder(idFound_);
        checkAccess(_an,_global,idFound_,idFound_);
        int len_ = parts_.size();
        for (int i = 1; i < len_; i++) {
            idFound_ = Templates.getIdFromAllTypes(parts_.get(i));
            id_.append("..");
            id_.append(idFound_);
            checkAccess(_an,_global,id_.toString(),idOwner_.toString());
            idOwner_.append("..");
            idOwner_.append(idFound_);
        }
    }
    private void checkAccess(Analyzable _an, AccessingImportingBlock _global, String _owner) {
        checkAccess(_an,_global,getAnalyzedType(),_owner);
    }
    private void checkAccess(Analyzable _an, AccessingImportingBlock _global, String _found, String _owner) {
        String idOwner_ = Templates.getIdFromAllTypes(_owner);
        String idFound_ = Templates.getIdFromAllTypes(_found);
        RootBlock owner_ = _an.getClasses().getClassBody(idOwner_);
        RootBlock found_ = _an.getClasses().getClassBody(idFound_);
        if (found_ == null) {
            return;
        }
        if (_an.getAnalyzing().getHiddenTypes().isHidden(_global,found_)) {
            _an.getAnalyzing().getCurrentBadIndexes().add(getIndexInType());
        }
        if (owner_.isTypeHidden(found_,_an)) {
            _an.getAnalyzing().getCurrentBadIndexes().add(getIndexInType());
        }
    }

    @Override
    void analyzeAccessibleId(Analyzable _an, CustList<IntTreeMap<String>> _dels, AccessingImportingBlock _rooted) {
        PartType part_ = getPreviousPartType();
        String type_ = getTypeName();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (part_ != null) {
            String owner_ = part_.getAnalyzedType();
            String id_ = Templates.getIdFromAllTypes(owner_);
            String in_ = StringList.concat(id_,"..",type_);
            Classes classes_ = _an.getClasses();
            RootBlock inner_ = classes_.getClassBody(in_);
            if (inner_ == null) {
                return;
            }
            if (inner_.isStaticType()) {
                setAnalyzedType(StringList.concat(id_,"..",type_));
                return;
            }
            if (!Templates.correctNbParameters(owner_, _an)) {
                return;
            }
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
                }
                return;
            }
        }
        
        String out_ = ResolvingImportTypes.lookupImportType(_an,type_,_rooted, new AlwaysReadyTypes());
//        String out_ = _an.lookupImportType(type_,_rooted, new AlwaysReadyTypes());
        if (out_.isEmpty()) {
            return;
        }
        setAnalyzedType(out_);
    }
    @Override
    void checkDynExistence(Analyzable _an,CustList<IntTreeMap< String>>_dels) {
        CustList<String> pr_ = new CustList<String>();
        PartType curr_ = getPartType();
        PartType part_;
        if (curr_ != null) {
            part_ = curr_.getPreviousSibling();
        } else {
            part_ = null;
        }
        String typeName_ = getTypeName();
        typeName_ = StringExpUtil.removeDottedSpaces(typeName_);
        pr_.add(typeName_);
        while (part_ != null) {
            PartType c_ = getDeepFirstChild(curr_);
            pr_.add(0,((LeafPartType)c_).getPreviousSeparator());
            PartType f_ = getDeepFirstChild(part_);
            pr_.add(0,((LeafPartType)f_).exportHeader());
            part_ = part_.getPreviousSibling();
            curr_ = curr_.getPreviousSibling();
        }
        String type_ = StringList.join(pr_, "");
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

    private static PartType getDeepFirstChild(PartType _part) {
        PartType f_ = _part;
        while (f_.getFirstChild() != null) {
            f_ = f_.getFirstChild();
        }
        return f_;
    }

    private PartType getPreviousPartType() {
        if (getParent() instanceof InnerPartType) {
            return getPreviousSibling();
        }
        if (getParent() instanceof TemplatePartType && getParent().getParent() instanceof InnerPartType && getIndex() == 0) {
            return getParent().getPreviousSibling();
        }
        return null;
    }
    private PartType getPartType() {
        if (getParent() instanceof InnerPartType) {
            return this;
        }
        if (getParent() instanceof TemplatePartType && getParent().getParent() instanceof InnerPartType && getIndex() == 0) {
            return getParent();
        }
        return null;
    }
}
