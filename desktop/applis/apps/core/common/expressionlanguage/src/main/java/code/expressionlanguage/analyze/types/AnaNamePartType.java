package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.MappingLocalType;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.*;

final class AnaNamePartType extends AnaLeafPartType {

    private boolean checkAccessLoop;
    private String owner="";
    AnaNamePartType(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType, _type, _previousSeparator);
    }

    @Override
    void analyze(ContextEl _an, CustList<IntTreeMap< String>> _dels, String _globalType, AccessedBlock _local, AccessedBlock _rooted) {
        if (skipGenericInners(_an,_dels)) {
            return;
        }
        tryAnalyzeInnerParts(_an, _local,_rooted);
    }

    private boolean skipGenericInners(ContextEl _an, CustList<IntTreeMap<String>> _dels) {
        AnaPartType part_ = getPreviousPartType();
        String type_ = getTypeName();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (part_ != null) {
            analyzeGenericOnwer(_an, part_, type_);
            return true;
        }
        return analyzeFull(_an, _dels, type_);
    }
    private void analyzeGenericOnwer(ContextEl _an, AnaPartType _part, String _type) {
        String owner_ = _part.getAnalyzedType();
        if (StringList.quickEq("..",getPreviousSeparator())) {
            StringList foundOwners_ = AnaTypeUtil.getEnumOwners(owner_, _type, _an);
            if (!foundOwners_.onlyOneElt()) {
                return;
            }
            setAnalyzedType(StringList.concat(foundOwners_.first(), "-", _type));
            owner = owner_;
            return;
        }
        StringList foundOwners_ = AnaTypeUtil.getGenericOwners(owner_, _type, _an);
        if (!foundOwners_.onlyOneElt()) {
            return;
        }
        setAnalyzedType(StringList.concat(foundOwners_.first(),"..", _type));
        owner = owner_;
    }

    private void analyzeFullType(String _type) {
        setAnalyzedType(_type);
        checkAccessLoop = true;
    }

    @Override
    void analyzeLine(ContextEl _an, ReadyTypes _ready,CustList<IntTreeMap< String>> _dels, AccessedBlock _local,AccessedBlock _rooted) {
        if (skipInners(_an,_ready,_dels)) {
            return;
        }
        tryAnalyzeInnerPartsLine(_an, _ready,_local,_rooted);
    }

    private boolean skipInners(ContextEl _an, ReadyTypes _ready, CustList<IntTreeMap<String>> _dels) {
        AnaPartType part_ = getPreviousPartType();
        String type_ = getTypeName();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (part_ != null) {
            analyzeOnwer(_an, _ready, part_, type_);
            return true;
        }
        return analyzeFull(_an, _dels, type_);
    }

    private boolean analyzeFull(ContextEl _an, CustList<IntTreeMap<String>> _dels, String _type) {
        String id_ = StringExpUtil.getIdFromAllTypes(_type);
        RootBlock root_ = _an.getAnalyzing().getAnaClassBody(id_);
        if (root_ != null) {
            analyzeFullType(_type);
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

    private void analyzeOnwer(ContextEl _an, ReadyTypes _ready, AnaPartType _part, String _type) {
        String owner_ = _part.getAnalyzedType();
        String id_ = StringExpUtil.getIdFromAllTypes(owner_);
        if (!_ready.isReady(id_)) {
            return;
        }
        if (StringList.quickEq("..",getPreviousSeparator())) {
            StringList foundOwners_ = AnaTypeUtil.getEnumOwners(id_, _type, _an);
            if (!foundOwners_.onlyOneElt()) {
                return;
            }
            String idOwner_= StringExpUtil.getIdFromAllTypes(foundOwners_.first());
            setAnalyzedType(StringList.concat(idOwner_,"-", _type));
            owner = id_;
            return;
        }
        StringList foundOwners_ = AnaTypeUtil.getOwners(id_, _type, _an);
        if (foundOwners_.onlyOneElt()) {
            String new_ = foundOwners_.first();
            setAnalyzedType(StringList.concat(new_,"..", _type));
            owner = id_;
        }
    }

    private boolean processFctVoid(ContextEl _an, CustList<IntTreeMap< String>> _dels) {
        AnaParentPartType par_ = getParent();
        if (!(par_ instanceof AnaTemplatePartType)) {
            return false;
        }
        AnaPartType prev_ = par_.getFirstChild();
        if (StringList.quickEq(getTypeName().trim(), _an.getStandards().getAliasVoid())) {
            String base_ = prev_.getAnalyzedType();
            base_ = StringExpUtil.getIdFromAllTypes(base_);
            if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                setAnalyzedType(getTypeName().trim());
            }
            return true;
        }
        return false;
    }

    private void tryAnalyzeInnerParts(ContextEl _an,
                                      AccessedBlock _local,
                                      AccessedBlock _rooted) {
        if (_local instanceof RootBlock) {
            if (skipGenericImports(_an, (RootBlock)_local)) {
                return;
            }
        }
        lookupImports(_an, _rooted, new AlwaysReadyTypes());
    }
    private boolean skipGenericImports(ContextEl _an,
                                       RootBlock _local) {
        String type_ = getTypeName().trim();
        RootBlock p_ = _local;
        StringMap<ResultTypeAncestor> allAncestors_ = new StringMap<ResultTypeAncestor>();
        MappingLocalType resolved_ = _an.getAnalyzing().getMappingLocal().getVal(type_);
        if (resolved_ != null) {
            ResultTypeAncestor res_ = new ResultTypeAncestor(true, resolved_.getSuffixedName());
            res_.setResolvedType(resolved_.getType());
            allAncestors_.addEntry(resolved_.getParentType().getGenericString(), res_);
        }
        while (p_ != null) {
            allAncestors_.addEntry(p_.getGenericString(), new ResultTypeAncestor(false,type_));
            p_ = p_.getParentType();
        }
        for (EntryCust<String,ResultTypeAncestor> e: allAncestors_.entryList()) {
            RootBlock inner_ = null;
            String genStr_ = "";
            String id_ = "";
            String resType_;
            String a = e.getKey();
            if (!e.getValue().isLocal()) {
                StringList owners_ = AnaTypeUtil.getGenericOwners(a, type_, _an);
                if (owners_.isEmpty()) {
                    continue;
                }
                if (owners_.onlyOneElt()) {
                    genStr_ = owners_.first();
                    id_ = StringExpUtil.getIdFromAllTypes(genStr_);
                    String in_ = StringList.concat(id_,"..",type_);
                    inner_ = _an.getAnalyzing().getAnaClassBody(in_);
                }
                resType_ = type_;
            } else {
                inner_ = e.getValue().getResolvedType();
                genStr_ = a;
                id_ = StringExpUtil.getIdFromAllTypes(genStr_);
                resType_ = e.getValue().getSimpleName();
            }
            if (inner_ != null) {
                if (inner_.isStaticType()) {
                    setAnalyzedType(StringList.concat(id_,"..",resType_));
                    owner = a;
                    return true;
                }
                if (_an.getAnalyzing().getTokenValidation().isStaticAccess()) {
                    setAnalyzedType(StringList.concat(id_,"..",resType_));
                    owner = a;
                    return true;
                }
                setAnalyzedType(StringList.concat(genStr_,"..",resType_));
                owner = a;
            }
            return true;
        }
        return false;
    }
    private void tryAnalyzeInnerPartsLine(ContextEl _an,
                                          ReadyTypes _ready,
                                          AccessedBlock _local,
                                          AccessedBlock _rooted) {
        String type_ = getTypeName().trim();
        MappingLocalType resolved_ = _an.getAnalyzing().getMappingLocal().getVal(type_);
        if (resolved_ != null) {
            setAnalyzedType(resolved_.getFullName());
            checkAccessLoop = true;
            return;
        }
        if (_local instanceof RootBlock) {
            if (skipImports(_an,_ready,(RootBlock)_local)) {
                return;
            }
        }
        lookupImports(_an, _rooted, _ready);
    }

    private boolean skipImports(ContextEl _an,
                                ReadyTypes _ready,
                                RootBlock _local) {
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
            StringList owners_ = AnaTypeUtil.getOwners(a, type_, _an);
            if (owners_.isEmpty()) {
                continue;
            }
            if (owners_.onlyOneElt()) {
                String new_ = owners_.first();
                setAnalyzedType(StringList.concat(new_,"..",type_));
                owner = a;
            }
            return true;
        }
        return false;
    }
    private void lookupImports(ContextEl _an, AccessedBlock _rooted, ReadyTypes _ready) {
        String type_ = getTypeName().trim();
        String res_ = ResolvingImportTypes.lookupImportType(_an,type_, _rooted, _ready);
        if (!res_.isEmpty()) {
            setAnalyzedType(res_);
            checkAccessLoop = true;
        }
    }
    void checkAccessGeneral(ContextEl _an) {
        String analyzedType_ = getAnalyzedType();
        int indexInType_ = getIndexInType();
        if (checkAccessLoop) {
            getInaccessibleTypes().addAllElts(checkAccess(_an, analyzedType_, indexInType_));
        } else {
            getInaccessibleTypes().addAllElts(checkAccessIntern(_an, analyzedType_, owner, indexInType_));
        }
    }

    private static CustList<InaccessibleType> checkAccess(ContextEl _an, String _analyzedType, int _indexInType) {
        StringList parts_ = StringExpUtil.getAllPartInnerTypes(_analyzedType);
        String idFound_ = StringExpUtil.getIdFromAllTypes(parts_.first());
        StringBuilder id_ = new StringBuilder(idFound_);
        StringBuilder idOwner_ = new StringBuilder(idFound_);
        CustList<InaccessibleType> l_ = checkAccessIntern(_an, idFound_, idFound_, _indexInType);
        int len_ = parts_.size();
        for (int i = 2; i < len_; i+=2) {
            idFound_ = StringExpUtil.getIdFromAllTypes(parts_.get(i));
            id_.append(parts_.get(i-1));
            id_.append(idFound_);
            l_.addAllElts(checkAccessIntern(_an, id_.toString(),idOwner_.toString(), _indexInType));
            idOwner_.append(parts_.get(i-1));
            idOwner_.append(idFound_);
        }
        return l_;
    }

    private static CustList<InaccessibleType> checkAccessIntern(ContextEl _an, String _found, String _owner, int _indexInType) {
        String idOwner_ = StringExpUtil.getIdFromAllTypes(_owner);
        String idFound_ = StringExpUtil.getIdFromAllTypes(_found);
        RootBlock found_ = _an.getAnalyzing().getAnaClassBody(idFound_);
        if (found_ == null) {
            return new CustList<InaccessibleType>();
        }
        CustList<InaccessibleType> l_ = new CustList<InaccessibleType>();
        ExecAccessingImportingBlock gl_ = _an.getAnalyzing().getCurrentGlobalBlock().getImportingAcces();
        if (_an.getAnalyzing().getHiddenTypes().isHidden(gl_,found_)) {
            InaccessibleType i_ = new InaccessibleType(_indexInType, idFound_);
            _an.getAnalyzing().getCurrentBadIndexes().add(i_);
            l_.add(i_);
        }
        Accessed a_ = new Accessed(found_.getAccess(), found_.getPackageName(), found_.getParentFullName(), found_.getFullName(), found_.getOuterFullName());
        if (new TypeAccessor(idOwner_).isTypeHidden(a_,_an)) {
            InaccessibleType i_ = new InaccessibleType(_indexInType, idFound_);
            _an.getAnalyzing().getCurrentBadIndexes().add(i_);
            l_.add(i_);
        }
        return l_;
    }

    @Override
    void analyzeAccessibleId(ContextEl _an, CustList<IntTreeMap<String>> _dels, AccessedBlock _rooted) {
        AnaPartType part_ = getPreviousPartType();
        String type_ = getTypeName();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (part_ != null) {
            String owner_ = part_.getAnalyzedType();
            String id_ = StringExpUtil.getIdFromAllTypes(owner_);
            String sep_;
            if (StringList.quickEq("..",getPreviousSeparator())) {
                sep_ = "-";
            } else {
                sep_ = "..";
            }
            String in_ = StringList.concat(id_,sep_,type_);
            RootBlock inner_ = _an.getAnalyzing().getAnaClassBody(in_);
            if (inner_ == null) {
                return;
            }
            setAnalyzedType(StringList.concat(owner_,sep_,type_));
            return;
        }
        if (_an.getAnalyzing().getAnaGeneType(_an,type_) != null) {
            setAnalyzedType(type_);
            return;
        }
        if (PrimitiveTypeUtil.isPrimitive(type_, _an)) {
            setAnalyzedType(type_);
            return;
        }
        if (getParent() instanceof AnaTemplatePartType) {
            AnaPartType prev_ = getParent().getFirstChild();
            if (StringList.quickEq(getTypeName().trim(), _an.getStandards().getAliasVoid())) {
                String base_ = prev_.getAnalyzedType();
                base_ = StringExpUtil.getIdFromAllTypes(base_);
                if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                    setAnalyzedType(getTypeName().trim());
                }
                return;
            }
        }

        String out_ = ResolvingImportTypes.lookupImportType(_an,type_,_rooted, new AlwaysReadyTypes());
        if (out_.isEmpty()) {
            return;
        }
        setAnalyzedType(out_);
    }

    void processOffsets(ContextEl _an, AccessedBlock _rooted) {
        if (!_an.isGettingParts()) {
            return;
        }
        String curr_ = ((Block)_rooted).getFile().getRenderFileName();
        String imported_ = getAnalyzedType();
        String idCl_ = StringExpUtil.getIdFromAllTypes(imported_);
        AnaGeneType g_ = _an.getAnalyzing().getAnaGeneType(_an,idCl_);
        if (ContextUtil.isFromCustFile(g_)) {
            String ref_ = ((RootBlock) g_).getFile().getRenderFileName();
            String rel_ = LinkageUtil.relativize(curr_,ref_);
            int id_ = ((RootBlock) g_).getIdRowCol();
            setTitleRef(g_.getFullName());
            setHref(rel_+"#m"+id_);
        }
    }
    void processInaccessibleOffsets(ContextEl _an, String _gl) {
        if (!_an.isGettingParts()) {
            return;
        }
        for (InaccessibleType i: getInaccessibleTypes()) {
            getErrs().add(FoundErrorInterpret.buildARError(_an.getAnalysisMessages().getInaccessibleType(),
                    i.getType(),_gl));
        }
    }
    AnaPartType getPreviousPartType() {
        if (getParent() instanceof AnaInnerPartType) {
            return getPreviousSibling();
        }
        if (getParent() instanceof AnaTemplatePartType && getParent().getParent() instanceof AnaInnerPartType && getIndex() == 0) {
            return getParent().getPreviousSibling();
        }
        return null;
    }
}
