package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
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
import code.expressionlanguage.analyze.blocks.ExecAccessingImportingBlock;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.*;

final class AnaNamePartType extends AnaLeafPartType {

    private boolean checkAccessLoop;
    private String owner="";
    AnaNamePartType(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType, _type, _previousSeparator);
    }

    @Override
    void analyze(CustList<IntTreeMap<String>> _dels, String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        if (skipGenericInners(_dels, _page)) {
            return;
        }
        tryAnalyzeInnerParts(_local,_rooted, _page);
    }

    private boolean skipGenericInners(CustList<IntTreeMap<String>> _dels, AnalyzedPageEl _page) {
        AnaPartType part_ = getPreviousPartType();
        String type_ = getTypeName();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (part_ != null) {
            analyzeGenericOnwer(part_, type_, _page);
            return true;
        }
        return analyzeFull(_dels, type_, _page);
    }
    private void analyzeGenericOnwer(AnaPartType _part, String _type, AnalyzedPageEl _page) {
        String owner_ = _part.getAnalyzedType();
        if (StringList.quickEq("..",getPreviousSeparator())) {
            StringList foundOwners_ = AnaTypeUtil.getEnumOwners(owner_, _type, _page);
            if (!foundOwners_.onlyOneElt()) {
                return;
            }
            setAnalyzedType(StringList.concat(foundOwners_.first(), "-", _type));
            owner = owner_;
            return;
        }
        StringList foundOwners_ = AnaTypeUtil.getGenericOwners(owner_, _type, _page);
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
    void analyzeLine(ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        if (skipInners(_ready,_dels, _page)) {
            return;
        }
        tryAnalyzeInnerPartsLine(_ready,_local,_rooted, _page);
    }

    private boolean skipInners(ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, AnalyzedPageEl _page) {
        AnaPartType part_ = getPreviousPartType();
        String type_ = getTypeName();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (part_ != null) {
            analyzeOnwer(_ready, part_, type_, _page);
            return true;
        }
        return analyzeFull(_dels, type_, _page);
    }

    private boolean analyzeFull(CustList<IntTreeMap<String>> _dels, String _type, AnalyzedPageEl _page) {
        String id_ = StringExpUtil.getIdFromAllTypes(_type);
        RootBlock root_ = _page.getAnaClassBody(id_);
        if (root_ != null) {
            analyzeFullType(_type);
            return true;
        }
        if (_page.getStandards().getStandards().contains(_type)) {
            setAnalyzedType(_type);
            return true;
        }
        if (AnaTypeUtil.isPrimitive(_type, _page)) {
            setAnalyzedType(_type);
            return true;
        }
        return processFctVoid(_dels, _page);
    }

    private void analyzeOnwer(ReadyTypes _ready, AnaPartType _part, String _type, AnalyzedPageEl _page) {
        String owner_ = _part.getAnalyzedType();
        String id_ = StringExpUtil.getIdFromAllTypes(owner_);
        if (!_ready.isReady(id_)) {
            return;
        }
        if (StringList.quickEq("..",getPreviousSeparator())) {
            StringList foundOwners_ = AnaTypeUtil.getEnumOwners(id_, _type, _page);
            if (!foundOwners_.onlyOneElt()) {
                return;
            }
            String idOwner_= StringExpUtil.getIdFromAllTypes(foundOwners_.first());
            setAnalyzedType(StringList.concat(idOwner_,"-", _type));
            owner = id_;
            return;
        }
        StringList foundOwners_ = AnaTypeUtil.getOwners(id_, _type, _page);
        if (foundOwners_.onlyOneElt()) {
            String new_ = foundOwners_.first();
            setAnalyzedType(StringList.concat(new_,"..", _type));
            owner = id_;
        }
    }

    private boolean processFctVoid(CustList<IntTreeMap<String>> _dels, AnalyzedPageEl _page) {
        AnaParentPartType par_ = getParent();
        if (!(par_ instanceof AnaTemplatePartType)) {
            return false;
        }
        AnaPartType prev_ = par_.getFirstChild();
        if (StringList.quickEq(getTypeName().trim(), _page.getStandards().getAliasVoid())) {
            String base_ = prev_.getAnalyzedType();
            base_ = StringExpUtil.getIdFromAllTypes(base_);
            if (StringList.quickEq(base_.trim(), _page.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                setAnalyzedType(getTypeName().trim());
            }
            return true;
        }
        return false;
    }

    private void tryAnalyzeInnerParts(AccessedBlock _local,
                                      AccessedBlock _rooted, AnalyzedPageEl _page) {
        if (_local instanceof RootBlock) {
            if (skipGenericImports((RootBlock)_local, _page)) {
                return;
            }
        }
        lookupImports(_rooted, new AlwaysReadyTypes(), _page);
    }
    private boolean skipGenericImports(RootBlock _local, AnalyzedPageEl _page) {
        String type_ = getTypeName().trim();
        RootBlock p_ = _local;
        StringMap<ResultTypeAncestor> allAncestors_ = new StringMap<ResultTypeAncestor>();
        MappingLocalType resolved_ = _page.getMappingLocal().getVal(type_);
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
                StringList owners_ = AnaTypeUtil.getGenericOwners(a, type_, _page);
                if (owners_.isEmpty()) {
                    continue;
                }
                if (owners_.onlyOneElt()) {
                    genStr_ = owners_.first();
                    id_ = StringExpUtil.getIdFromAllTypes(genStr_);
                    String in_ = StringList.concat(id_,"..",type_);
                    inner_ = _page.getAnaClassBody(in_);
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
                if (_page.getTokenValidation().isStaticAccess()) {
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
    private void tryAnalyzeInnerPartsLine(ReadyTypes _ready,
                                          AccessedBlock _local,
                                          AccessedBlock _rooted, AnalyzedPageEl _page) {
        if (_local instanceof RootBlock) {
            if (skipImports(_ready,(RootBlock)_local, _page)) {
                return;
            }
        }
        lookupImports(_rooted, _ready, _page);
    }

    private boolean skipImports(ReadyTypes _ready,
                                RootBlock _local, AnalyzedPageEl _page) {
        String type_ = getTypeName().trim();
        RootBlock p_ = _local;
        StringMap<ResultTypeAncestor> allAncestors_ = new StringMap<ResultTypeAncestor>();
        MappingLocalType resolved_ = _page.getMappingLocal().getVal(type_);
        if (resolved_ != null) {
            ResultTypeAncestor res_ = new ResultTypeAncestor(true, resolved_.getSuffixedName());
            res_.setResolvedType(resolved_.getType());
            allAncestors_.addEntry(resolved_.getParentType().getFullName(), res_);
        }
        while (p_ != null) {
            allAncestors_.add(p_.getFullName(), new ResultTypeAncestor(false,type_));
            p_ = p_.getParentType();
        }
        for (EntryCust<String,ResultTypeAncestor> e: allAncestors_.entryList()) {
            String a = e.getKey();
            if (!_ready.isReady(a)) {
                return true;
            }
            if (!e.getValue().isLocal()) {
                StringList owners_ = AnaTypeUtil.getOwners(a, type_, _page);
                if (owners_.isEmpty()) {
                    continue;
                }
                if (owners_.onlyOneElt()) {
                    String new_ = owners_.first();
                    setAnalyzedType(StringList.concat(new_,"..",type_));
                    owner = a;
                }
            } else {
                String resType_ = e.getValue().getSimpleName();
                setAnalyzedType(StringList.concat(a,"..",resType_));
                owner = a;
            }
            return true;
        }
        return false;
    }
    private void lookupImports(AccessedBlock _rooted, ReadyTypes _ready, AnalyzedPageEl _page) {
        String type_ = getTypeName().trim();
        String res_ = ResolvingImportTypes.lookupImportType(type_, _rooted, _ready, _page);
        if (!res_.isEmpty()) {
            setAnalyzedType(res_);
            checkAccessLoop = true;
        }
    }
    void checkAccessGeneral(AnalyzedPageEl _page) {
        String analyzedType_ = getAnalyzedType();
        int indexInType_ = getIndexInType();
        if (checkAccessLoop) {
            getInaccessibleTypes().addAllElts(checkAccess(analyzedType_, indexInType_, _page));
        } else {
            getInaccessibleTypes().addAllElts(checkAccessIntern(analyzedType_, owner, indexInType_, _page));
        }
    }

    private static CustList<InaccessibleType> checkAccess(String _analyzedType, int _indexInType, AnalyzedPageEl _page) {
        StringList parts_ = StringExpUtil.getAllPartInnerTypes(_analyzedType);
        String idFound_ = StringExpUtil.getIdFromAllTypes(parts_.first());
        StringBuilder id_ = new StringBuilder(idFound_);
        StringBuilder idOwner_ = new StringBuilder(idFound_);
        CustList<InaccessibleType> l_ = checkAccessIntern(idFound_, idFound_, _indexInType, _page);
        int len_ = parts_.size();
        for (int i = 2; i < len_; i+=2) {
            idFound_ = StringExpUtil.getIdFromAllTypes(parts_.get(i));
            id_.append(parts_.get(i-1));
            id_.append(idFound_);
            l_.addAllElts(checkAccessIntern(id_.toString(),idOwner_.toString(), _indexInType, _page));
            idOwner_.append(parts_.get(i-1));
            idOwner_.append(idFound_);
        }
        return l_;
    }

    private static CustList<InaccessibleType> checkAccessIntern(String _found, String _owner, int _indexInType, AnalyzedPageEl _page) {
        String idOwner_ = StringExpUtil.getIdFromAllTypes(_owner);
        String idFound_ = StringExpUtil.getIdFromAllTypes(_found);
        RootBlock found_ = _page.getAnaClassBody(idFound_);
        if (found_ == null) {
            return new CustList<InaccessibleType>();
        }
        CustList<InaccessibleType> l_ = new CustList<InaccessibleType>();
        ExecAccessingImportingBlock gl_ = _page.getCurrentGlobalBlock().getImportingAcces();
        if (_page.getHiddenTypes().isHidden(gl_,found_)) {
            InaccessibleType i_ = new InaccessibleType(_indexInType, idFound_);
            _page.getCurrentBadIndexes().add(i_);
            l_.add(i_);
        }
        Accessed a_ = new Accessed(found_.getAccess(), found_.getPackageName(), found_.getParentFullName(), found_.getFullName(), found_.getOuterFullName());
        if (new TypeAccessor(idOwner_).isTypeHidden(a_, _page)) {
            InaccessibleType i_ = new InaccessibleType(_indexInType, idFound_);
            _page.getCurrentBadIndexes().add(i_);
            l_.add(i_);
        }
        return l_;
    }

    @Override
    void analyzeAccessibleId(CustList<IntTreeMap<String>> _dels, AccessedBlock _rooted, AnalyzedPageEl _page) {
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
            RootBlock inner_ = _page.getAnaClassBody(in_);
            if (inner_ == null) {
                return;
            }
            setAnalyzedType(StringList.concat(owner_,sep_,type_));
            return;
        }
        if (_page.getAnaGeneType(type_) != null) {
            setAnalyzedType(type_);
            return;
        }
        if (AnaTypeUtil.isPrimitive(type_, _page)) {
            setAnalyzedType(type_);
            return;
        }
        if (getParent() instanceof AnaTemplatePartType) {
            AnaPartType prev_ = getParent().getFirstChild();
            if (StringList.quickEq(getTypeName().trim(), _page.getStandards().getAliasVoid())) {
                String base_ = prev_.getAnalyzedType();
                base_ = StringExpUtil.getIdFromAllTypes(base_);
                if (StringList.quickEq(base_.trim(), _page.getStandards().getAliasFct()) && _dels.last().size() == getIndex() + 1) {
                    setAnalyzedType(getTypeName().trim());
                }
                return;
            }
        }

        String out_ = ResolvingImportTypes.lookupImportType(type_,_rooted, new AlwaysReadyTypes(), _page);
        if (out_.isEmpty()) {
            return;
        }
        setAnalyzedType(out_);
    }

    void processOffsets(AccessedBlock _rooted, AnalyzedPageEl _page) {
        if (!_page.isGettingParts()) {
            return;
        }
        String curr_ = ((Block)_rooted).getFile().getRenderFileName();
        String imported_ = getAnalyzedType();
        String idCl_ = StringExpUtil.getIdFromAllTypes(imported_);
        AnaGeneType g_ = _page.getAnaGeneType(idCl_);
        if (ContextUtil.isFromCustFile(g_)) {
            String ref_ = ((RootBlock) g_).getFile().getRenderFileName();
            String rel_ = LinkageUtil.relativize(curr_,ref_);
            int id_ = ((RootBlock) g_).getIdRowCol();
            setTitleRef(g_.getFullName());
            setHref(rel_+"#m"+id_);
        }
    }
    void processInaccessibleOffsets(String _gl, AnalyzedPageEl _page) {
        if (!_page.isGettingParts()) {
            return;
        }
        for (InaccessibleType i: getInaccessibleTypes()) {
            getErrs().add(FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getInaccessibleType(),
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
