package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.IdFctOperation;
import code.expressionlanguage.analyze.types.GeneStringOverridable;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.common.ExtractedParts;
import code.expressionlanguage.common.GeneCustStaticMethod;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetAccessInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;

public final class OverridableBlock extends NamedFunctionBlock implements GeneCustStaticMethod,ReturnableWithSignature {

    private int modifierOffset;

    private final boolean staticMethod;
    private final boolean staticCallMethod;

    private final boolean finalMethod;
    private final boolean abstractMethod;

    private final boolean normalMethod;
    private MethodKind kind;
    private CustList<PartOffset> allInternParts = new CustList<PartOffset>();
    private String definition  = "";
    private int definitionOffset;
    private StringMap<ClassMethodId> overrides = new StringMap<ClassMethodId>();

    public OverridableBlock(ContextEl _importingPage,
                            OffsetAccessInfo _access,
                            OffsetStringInfo _retType, OffsetStringInfo _fctName,
                            StringList _paramTypes, Ints _paramTypesOffset,
                            StringList _paramNames, Ints _paramNamesOffset,
                            OffsetStringInfo _modifier, OffsetsBlock _offset) {
        super(_access, _retType, _fctName, _paramTypes, _paramTypesOffset, _paramNames, _paramNamesOffset, _offset);
        modifierOffset = _modifier.getOffset();
        String modifier_ = _modifier.getInfo();
        KeyWords keyWords_ = _importingPage.getKeyWords();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String keyWordStaticCall_ = keyWords_.getKeyWordStaticCall();
        String keyWordFinal_ = keyWords_.getKeyWordFinal();
        String keyWordAbstract_ = keyWords_.getKeyWordAbstract();
        String keyWordNormal_ = keyWords_.getKeyWordNormal();
        staticMethod = StringList.quickEq(modifier_, keyWordStatic_);
        staticCallMethod = StringList.quickEq(modifier_, keyWordStaticCall_);
        finalMethod = StringList.quickEq(modifier_, keyWordFinal_);
        abstractMethod = StringList.quickEq(modifier_, keyWordAbstract_);
        normalMethod = StringList.quickEq(modifier_, keyWordNormal_);
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setDefinitionOffset(int definitionOffset) {
        this.definitionOffset = definitionOffset;
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }

    public int getModifierOffset() {
        return modifierOffset;
    }

    public MethodModifier getModifier() {
        if (abstractMethod) {
            return MethodModifier.ABSTRACT;
        }
        if (finalMethod) {
            return MethodModifier.FINAL;
        }
        if (staticCallMethod) {
            return MethodModifier.STATIC_CALL;
        }
        if (staticMethod) {
            return MethodModifier.STATIC;
        }
        return MethodModifier.NORMAL;
    }

    public boolean hiddenInstance() {
        return staticCallMethod || staticMethod;
    }
    @Override
    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        StringList pTypes_ = new StringList();
        if (kind == MethodKind.EXPLICIT_CAST || kind == MethodKind.IMPLICIT_CAST
                ||kind == MethodKind.TRUE_OPERATOR || kind == MethodKind.FALSE_OPERATOR) {
            pTypes_.add(getImportedReturnType());
        }
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
        }
        return new MethodId(MethodId.getKind(getModifier()), name_, pTypes_, isVarargs());
    }

    @Override
    public boolean isStaticMethod() {
        return staticMethod;
    }

    public boolean isFinalMethod() {
        return finalMethod;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public boolean isStaticCallMethod() {
        return staticCallMethod;
    }

    public boolean isNormalMethod() {
        return normalMethod;
    }

    @Override
    public MethodAccessKind getStaticContext() {
        if (staticMethod) {
            return MethodAccessKind.STATIC;
        }
        if (staticCallMethod) {
            return MethodAccessKind.STATIC_CALL;
        }
        return MethodAccessKind.INSTANCE;
    }

    @Override
    public void setAssignmentAfterCallReadOnly(ContextEl _an, AnalyzingEl _anEl) {
        checkReturnFctOverridable(_an, _anEl);
    }

    private void checkReturnFctOverridable(ContextEl _an, AnalyzingEl _anEl) {
        LgNames stds_ = _an.getStandards();
        if (!StringList.quickEq(getImportedReturnType(), stds_.getAliasVoid())) {
            if (!isAbstractMethod() && _anEl.canCompleteNormally(this)) {
                //error
                FoundErrorInterpret miss_ = new FoundErrorInterpret();
                miss_.setIndexFile(getOffset().getOffsetTrim());
                miss_.setFileName(getFile().getFileName());
                //return type len
                miss_.buildError(_an.getAnalysisMessages().getMissingAbrupt(),
                        _an.getKeyWords().getKeyWordThrow(),
                        _an.getKeyWords().getKeyWordReturn(),
                        getPseudoSignature(_an));
                _an.addError(miss_);
                addNameErrors(miss_);
            }
        }
    }
    public void buildTypes(RootBlock _root, ContextEl _context) {
        int indexDefOv_ = definition.indexOf('(');
        AnalyzedPageEl analyzing_ = _context.getAnalyzing();
        analyzing_.setGlobalOffset(definitionOffset+indexDefOv_+1);
        ExtractedParts extractedParts_ = StringExpUtil.tryToExtract(definition, '(', ')');
        StringList overrideList_ = StringList.splitChar(extractedParts_.getSecond(), ';');
        int sum_ = 0;
        for (String o: overrideList_) {
            analyzing_.setOffset(sum_);
            int indexDef_ = o.indexOf(Templates.EXTENDS_DEF);
            StringList parts_ = StringList.splitInTwo(o, indexDef_);
            if (parts_.size() <= 1) {
                sum_ += o.length()+1;
                continue;
            }
            String key_ = parts_.first();
            int off_ = StringList.getFirstPrintableCharIndex(key_);
            String clKey_ = ResolvingImportTypes.resolveAccessibleIdType(_context,off_,key_);
            allInternParts.addAllElts(analyzing_.getCurrentParts());
            RootBlock root_ = analyzing_.getAnaClassBody(clKey_);
            if (root_ == null) {
                sum_ += o.length()+1;
                continue;
            }
            if (!root_.isSubTypeOf(_root.getFullName(),_context)) {
                sum_ += o.length()+1;
                continue;
            }
            String sgn_ = parts_.last().substring(1);
            ExtractedParts extr_ = StringExpUtil.tryToExtract(sgn_,'(',')');
            String nameLoc_ = extr_.getFirst().trim();
            if (StringExpUtil.isIndexerOrInexist(nameLoc_)) {
                sum_ += o.length() + 1;
                continue;
            }
            analyzing_.setOffset(sum_+indexDef_+1);
            StringList args_ = StringExpUtil.getAllSepCommaTypes(extr_.getSecond());
            String firstFull_ = args_.first();
            off_ = StringList.getFirstPrintableCharIndex(firstFull_);
            String fromType_ = StringExpUtil.removeDottedSpaces(firstFull_);
            int firstPar_ = extr_.getFirst().length();
            String clDest_ = ResolvingImportTypes.resolveAccessibleIdType(_context,off_+firstPar_+1,fromType_);
            CustList<PartOffset> superPartOffsets_ = new CustList<PartOffset>();
            superPartOffsets_.addAllElts(analyzing_.getCurrentParts());
            String formattedDest_ = Templates.getOverridingFullTypeByBases(root_, clDest_, _context);
            if (formattedDest_.isEmpty()) {
                allInternParts.addAllElts(superPartOffsets_);
                sum_ += o.length()+1;
                continue;
            }
            MethodId methodIdDest_ = IdFctOperation.resolveArguments(1,_context,clDest_,nameLoc_,MethodAccessKind.INSTANCE,args_,sgn_, superPartOffsets_);
            if (methodIdDest_ == null) {
                allInternParts.addAllElts(superPartOffsets_);
                sum_ += o.length()+1;
                continue;
            }
            CustList<PartOffset> partMethods_ = new CustList<PartOffset>();
            RootBlock rootSuper_ = analyzing_.getAnaClassBody(clDest_);
            CustList<OverridableBlock> methods_ = ClassesUtil.getMethodExecBlocks(rootSuper_);
            String formattedDeclaring_ = Templates.getOverridingFullTypeByBases(root_, _root.getFullName(), _context);
            if (!getId().quickOverrideFormat(formattedDeclaring_,_context).eqPartial(MethodId.to(methodIdDest_.quickFormat(formattedDest_,_context)))) {
                allInternParts.addAllElts(superPartOffsets_);
                sum_ += o.length()+1;
                continue;
            }
            int rc_ = _context.getCurrentLocationIndex()+off_;
            for (OverridableBlock m: methods_) {
                if (m.isAbstractMethod()) {
                    continue;
                }
                if (m.getId().eq(methodIdDest_)) {
                    ClassMethodId ref_ = new ClassMethodId(clDest_,m.getId());
                    CustList<PartOffset> partMethod_ = new CustList<PartOffset>();
                    StringList l_ = new StringList();
                    LinkageUtil.addParts(_context,analyzing_.getRefFoundTypes(),_root.getFile().getRenderFileName(),ref_,rc_,nameLoc_.length(), l_,l_,partMethod_,-1);
                    partMethods_.addAllElts(partMethod_);
                    overrides.put(clKey_,new ClassMethodId(formattedDest_,methodIdDest_));
                    break;
                }
            }
            allInternParts.addAllElts(partMethods_);
            allInternParts.addAllElts(superPartOffsets_);
            sum_ += o.length()+1;
        }
    }

    public StringMap<ClassMethodId> getOverrides() {
        return overrides;
    }

    public CustList<PartOffset> getAllInternParts() {
        return allInternParts;
    }

    public MethodKind getKind() {
        return kind;
    }

    public void setKind(MethodKind _kind) {
        kind = _kind;
    }

}
