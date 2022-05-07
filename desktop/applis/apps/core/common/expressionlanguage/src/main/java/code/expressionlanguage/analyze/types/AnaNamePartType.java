package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultTokenValidation;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.MappingLocalType;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

final class AnaNamePartType extends AnaLeafPartType {

    private boolean checkAccessLoop;
    private String owner="";
    private String titleRef = "";
    private int value;
    private boolean buildRef;
    private boolean voidType;
    private FileBlock currentFile;
    private FileBlock refFileName;
    private String gl = "";
    AnaNamePartType(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator, AnalysisMessages _messages) {
        super(_parent, _index, _indexInType, _type, _previousSeparator, _messages);
    }

    @Override
    void analyze(String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        setLoc(_loc);
        if (skipGenericInners(_page)) {
            return;
        }
        tryAnalyzeInnerParts(_local,_rooted, _page);
    }

    private boolean skipGenericInners(AnalyzedPageEl _page) {
        AnaPartType part_ = getPreviousPartType();
        String type_ = getTypeName();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (part_ != null) {
            analyzeGenericOnwer(part_, type_, _page);
            return true;
        }
        return analyzeFull(type_, _page);
    }
    private void analyzeGenericOnwer(AnaPartType _part, String _type, AnalyzedPageEl _page) {
        String owner_ = _part.getAnalyzedType();
        if (StringUtil.quickEq("..",getPreviousSeparator())) {
            OwnerListResultInfo foundOwners_ = AnaTypeUtil.getEnumOwners(owner_, _type, _page);
            if (!foundOwners_.onlyOneElt()) {
                return;
            }
            setFoundType(foundOwners_.firstElt().getOwned());
            setAnalyzedType(foundOwners_.firstElt().getOwnedGeneName());
            owner = owner_;
            return;
        }
        OwnerListResultInfo foundOwners_ = AnaTypeUtil.getGenericOwners(owner_, _type, _page);
        if (!foundOwners_.onlyOneElt()) {
            return;
        }
        setFoundType(foundOwners_.firstElt().getOwned());
        setAnalyzedType(foundOwners_.firstElt().getOwnedGeneName());
        owner = owner_;
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        setLoc(_loc);
        if (skipInners(_ready, _page)) {
            return;
        }
        tryAnalyzeInnerPartsLine(_ready,_local,_rooted, _page);
    }

    private boolean skipInners(ReadyTypes _ready, AnalyzedPageEl _page) {
        AnaPartType part_ = getPreviousPartType();
        String type_ = getTypeName();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (part_ != null) {
            analyzeOnwer(_ready, part_, type_, _page);
            return true;
        }
        return analyzeFull(type_, _page);
    }

    private boolean analyzeFull(String _type, AnalyzedPageEl _page) {
        String id_ = StringExpUtil.getIdFromAllTypes(_type);
        RootBlock root_ = _page.getAnaClassBody(id_);
        if (root_ != null) {
            setFoundType(root_);
            setAnalyzedType(_type);
            checkAccessLoop = true;
            return true;
        }
        StandardType val_ = _page.getStandardsTypes().getVal(_type);
        if (val_ != null) {
            setFoundType(val_);
            setAnalyzedType(_type);
            return true;
        }
        if (AnaTypeUtil.isPrimitive(_type, _page)) {
            setAnalyzedType(_type);
            return true;
        }
        return processFctVoid(_page);
    }

    private void analyzeOnwer(ReadyTypes _ready, AnaPartType _part, String _type, AnalyzedPageEl _page) {
        String owner_ = _part.getAnalyzedType();
        String id_ = StringExpUtil.getIdFromAllTypes(owner_);
        if (!_ready.isReady(id_)) {
            return;
        }
        if (StringUtil.quickEq("..",getPreviousSeparator())) {
            OwnerListResultInfo foundOwners_ = AnaTypeUtil.getEnumOwners(id_, _type, _page);
            if (!foundOwners_.onlyOneElt()) {
                return;
            }
            setFoundType(foundOwners_.firstElt().getOwned());
            setAnalyzedType(foundOwners_.firstElt().getOwnedGeneName());
            owner = id_;
            return;
        }
        OwnerListResultInfo foundOwners_ = AnaTypeUtil.getOwners(id_, _type, _page);
        if (foundOwners_.onlyOneElt()) {
            setFoundType(foundOwners_.firstElt().getOwned());
            setAnalyzedType(foundOwners_.firstElt().getOwnedGeneName());
            owner = id_;
        }
    }

    private boolean processFctVoid(AnalyzedPageEl _page) {
        AnaParentPartType par_ = getParent();
        if (StringUtil.quickEq(getTypeName().trim(), _page.getAliasVoid())) {
            voidType = par_ == null;
        }
        if (!(par_ instanceof AnaTemplatePartType)) {
            return false;
        }
        AnaPartType prev_ = par_.getFirstChild();
        if (StringUtil.quickEq(getTypeName().trim(), _page.getAliasVoid())) {
            String base_ = prev_.getAnalyzedType();
            base_ = StringExpUtil.getIdFromAllTypes(base_);
            if (StringUtil.quickEq(base_.trim(), _page.getAliasFct()) && par_.getStrTypes().size() == getIndex() + 1) {
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
        } else {
            String type_ = getTypeName().trim();
            MappingLocalType resolved_ = _page.getMappingLocal().getVal(type_);
            if (resolved_ != null) {
                setFoundType(resolved_.getType());
                setAnalyzedType(resolved_.getFullName());
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
            allAncestors_.addEntry(resolved_.getParentTypeGenericString(), res_);
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
                OwnerListResultInfo owners_ = AnaTypeUtil.getGenericOwners(a, type_, _page);
                if (owners_.isEmpty()) {
                    continue;
                }
                if (owners_.onlyOneElt()) {
                    OwnerResultInfo info_ = owners_.firstElt();
                    genStr_ = info_.getOwnerName();
                    id_ = StringExpUtil.getIdFromAllTypes(genStr_);
                    inner_ = info_.getOwned();
                }
                resType_ = type_;
            } else {
                inner_ = e.getValue().getResolvedType();
                genStr_ = a;
                id_ = StringExpUtil.getIdFromAllTypes(genStr_);
                resType_ = e.getValue().getSimpleName();
            }
            if (inner_ != null) {
                setFoundType(inner_);
                if (inner_.withoutInstance()) {
                    setAnalyzedType(StringUtil.concat(id_,"..",resType_));
                    owner = a;
                    return true;
                }
                if (DefaultTokenValidation.isStaticAcc(_page)) {
                    setAnalyzedType(StringUtil.concat(id_,"..",resType_));
                    owner = a;
                    return true;
                }
                setAnalyzedType(StringUtil.concat(genStr_,"..",resType_));
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
        } else {
            String type_ = getTypeName().trim();
            MappingLocalType resolved_ = _page.getMappingLocal().getVal(type_);
            if (resolved_ != null) {
                setFoundType(resolved_.getType());
                setAnalyzedType(resolved_.getFullName());
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
            allAncestors_.addEntry(resolved_.getParentFullName(), res_);
        }
        while (p_ != null) {
            allAncestors_.addEntry(p_.getFullName(), new ResultTypeAncestor(false,type_));
            p_ = p_.getParentType();
        }
        for (EntryCust<String,ResultTypeAncestor> e: allAncestors_.entryList()) {
            String a = e.getKey();
            if (!_ready.isReady(a)) {
                return true;
            }
            if (!e.getValue().isLocal()) {
                OwnerListResultInfo owners_ = AnaTypeUtil.getOwners(a, type_, _page);
                if (owners_.isEmpty()) {
                    continue;
                }
                if (owners_.onlyOneElt()) {
                    setFoundType(owners_.firstElt().getOwned());
                    setAnalyzedType(owners_.firstElt().getOwnedGeneName());
                    owner = a;
                }
            } else {
                String resType_ = e.getValue().getSimpleName();
                setFoundType(e.getValue().getResolvedType());
                setAnalyzedType(StringUtil.concat(a,"..",resType_));
                owner = a;
            }
            return true;
        }
        return false;
    }
    private void lookupImports(AccessedBlock _rooted, ReadyTypes _ready, AnalyzedPageEl _page) {
        String type_ = getTypeName().trim();
        ResolvedIdTypeContent res_ = ResolvingImportTypes.lookupImportType(type_, _rooted, _ready, _page);
        if (res_ != null) {
            setFoundType(res_.getGeneType());
            setAnalyzedType(res_.getFullName());
            checkAccessLoop = true;
        }
    }
    void checkAccessGeneral(String _gl, AnalyzedPageEl _page) {
        gl = _gl;
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
        AccessingImportingBlock gl_ = _page.getImportingAcces();
        Accessed a_ = new Accessed(found_.getAccess(), found_.getPackageName(), found_.getParentType(), found_);
        if (gl_.isTypeHidden(a_, _page)) {
            InaccessibleType i_ = new InaccessibleType(_indexInType, idFound_);
            _page.getCurrentBadIndexes().add(i_);
            l_.add(i_);
        }
        if (new TypeAccessor(idOwner_).isTypeHidden(a_, _page)) {
            InaccessibleType i_ = new InaccessibleType(_indexInType, idFound_);
            _page.getCurrentBadIndexes().add(i_);
            l_.add(i_);
        }
        return l_;
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        setLoc(_loc);
        AnaPartType part_ = getPreviousPartType();
        String type_ = getTypeName();
        type_ = StringExpUtil.removeDottedSpaces(type_);
        if (part_ != null) {
            String owner_ = part_.getAnalyzedType();
            String id_ = StringExpUtil.getIdFromAllTypes(owner_);
            String sep_;
            if (StringUtil.quickEq("..",getPreviousSeparator())) {
                sep_ = "-";
            } else {
                sep_ = "..";
            }
            String in_ = StringUtil.concat(id_,sep_,type_);
            RootBlock inner_ = _page.getAnaClassBody(in_);
            if (inner_ == null) {
                return;
            }
            setAnalyzedType(StringUtil.concat(owner_,sep_,type_));
            setFoundType(inner_);
            return;
        }
        AnaGeneType anaGeneType_ = _page.getAnaGeneType(type_);
        if (anaGeneType_ != null) {
            setAnalyzedType(type_);
            setFoundType(anaGeneType_);
            return;
        }
        if (AnaTypeUtil.isPrimitive(type_, _page)) {
            setAnalyzedType(type_);
            return;
        }
        if (StringUtil.quickEq(getTypeName().trim(), _page.getAliasVoid())) {
            voidType = getParent() == null;
        }
        if (getParent() instanceof AnaTemplatePartType) {
            AnaPartType prev_ = getParent().getFirstChild();
            if (StringUtil.quickEq(getTypeName().trim(), _page.getAliasVoid())) {
                String base_ = prev_.getAnalyzedType();
                base_ = StringExpUtil.getIdFromAllTypes(base_);
                if (StringUtil.quickEq(base_.trim(), _page.getAliasFct()) && getParent().getStrTypes().size() == getIndex() + 1) {
                    setAnalyzedType(getTypeName().trim());
                }
                return;
            }
        }

        ResolvedIdTypeContent out_ = ResolvingImportTypes.lookupImportType(type_,_rooted, new AlwaysReadyTypes(), _page);
        if (out_ == null) {
            return;
        }
        setFoundType(out_.getGeneType());
        setAnalyzedType(out_.getFullName());
    }

    boolean isVoidType() {
        return voidType;
    }

    void setVoidType(boolean _voidType) {
        this.voidType = _voidType;
    }

    void processOffsets(FileBlock _rooted) {
//        AnaGeneType g_ = _page.getAnaGeneType(idCl_);
        if (ContextUtil.isFromCustFile(getFoundType())) {
            int id_ = ((RootBlock) getFoundType()).getIdRowCol();
            String imported_ = getAnalyzedType();
            String idCl_ = StringExpUtil.getIdFromAllTypes(imported_);
            setTitleRef(idCl_);
            value = id_;
            buildRef = true;
            currentFile = _rooted;
            refFileName = ((RootBlock) getFoundType()).getFile();
        }
    }

    String getTitleRef() {
        return titleRef;
    }

    void setTitleRef(String _titleRef) {
        titleRef = _titleRef;
    }

    int getValue() {
        return value;
    }

    boolean isBuildRef() {
        return buildRef;
    }

    FileBlock getCurrentFile() {
        return currentFile;
    }

    FileBlock getRefFileName() {
        return refFileName;
    }

    String getGl() {
        return gl;
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
