package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.IdFctOperation;
import code.expressionlanguage.analyze.types.GeneStringOverridable;
import code.expressionlanguage.analyze.types.OverridingMethodDto;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.common.ExtractedParts;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.FormattedMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.CustList;
import code.util.StringList;

public final class InternOverrideBlock extends Leaf {
    private CustList<OverridingMethodDto> overrides = new CustList<OverridingMethodDto>();
    private String definition;
    private int definitionOffset;
    private CustList<PartOffset> allParts = new CustList<PartOffset>();
    public InternOverrideBlock(OffsetsBlock _offset, String _definition, int _definitionOffset) {
        super(_offset);
        definition = _definition;
        definitionOffset = _definitionOffset;
    }

    public void buildTypes(RootBlock _root, ContextEl _context) {
        for (int i:getBadIndexes()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(i);
            //underline index char
            b_.buildError(_context.getAnalyzing().getAnalysisMessages().getBadIndexInParser());
            _context.getAnalyzing().addLocError(b_);
            setReachableError(true);
            getErrorsBlock().add(b_.getBuiltError());
        }
        AnalyzedPageEl analyzing_ = _context.getAnalyzing();
        analyzing_.setGlobalOffset(definitionOffset);
        StringList overrideList_ = StringList.splitChar(definition, ';');
        int sum_ = 0;
        for (String o: overrideList_) {
            analyzing_.setOffset(sum_);
            int indexDef_ = o.indexOf(Templates.EXTENDS_DEF);
            StringList parts_ = StringList.splitInTwo(o, indexDef_);
            StringList superMethods_ = new StringList();
            String key_ = parts_.first();
            if (parts_.size() > 1) {
                superMethods_ = StringList.splitChar(parts_.last().substring(1),'|');
            }
            ExtractedParts extKey_ = StringExpUtil.tryToExtract(key_, '(', ')');
            String name_ = extKey_.getFirst().trim();
            if (StringExpUtil.isIndexerOrInexist(name_)) {
                sum_ += o.length()+1;
                continue;
            }
            StringList typesKeys_ = StringExpUtil.getAllSepCommaTypes(extKey_.getSecond());
            if (typesKeys_.first().trim().isEmpty()) {
                typesKeys_ = new StringList();
            }
            String idCurrent_ = _root.getFullName();
            CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
            MethodId methodId_ = IdFctOperation.resolveArguments(0, _context, idCurrent_, name_, MethodAccessKind.INSTANCE, typesKeys_, key_, partOffsets_);
            if (methodId_ == null) {
                allParts.addAllElts(partOffsets_);
                sum_ += o.length()+1;
                continue;
            }
            allParts.addAllElts(partOffsets_);
            FormattedMethodId formattedMethodId_ = MethodId.to(methodId_);
            OverridingMethodDto ov_ = new OverridingMethodDto(formattedMethodId_);
            int localSum_ = key_.length()+1;
            for (String s: superMethods_) {
                ExtractedParts extValue_ = StringExpUtil.tryToExtract(s, '(',')');
                String nameLoc_ = extValue_.getFirst().trim();
                if (StringExpUtil.isIndexerOrInexist(nameLoc_)){
                    localSum_ += s.length()+1;
                    continue;
                }
                analyzing_.setOffset(sum_+localSum_);
                StringList args_ = StringExpUtil.getAllSepCommaTypes(extValue_.getSecond());
                String firstFull_ = args_.first();
                int off_ = StringList.getFirstPrintableCharIndex(firstFull_);
                String fromType_ = StringExpUtil.removeDottedSpaces(firstFull_);
                CustList<PartOffset> superPartOffsets_ = new CustList<PartOffset>();
                int firstPar_ = extValue_.getFirst().length();
                String cl_ = ResolvingImportTypes.resolveAccessibleIdType(_context,off_+firstPar_+1,fromType_);
                superPartOffsets_.addAllElts(analyzing_.getCurrentParts());
                String formatted_ = AnaTemplates.getOverridingFullTypeByBases(_root, cl_, _context);
                RootBlock formattedType_ = analyzing_.getAnaClassBody(StringExpUtil.getIdFromAllTypes(formatted_));
                if (formattedType_ == null) {
                    allParts.addAllElts(superPartOffsets_);
                    localSum_ += s.length()+1;
                    continue;
                }
                MethodId superMethodId_ = IdFctOperation.resolveArguments(1,_context,cl_,nameLoc_,MethodAccessKind.INSTANCE,args_,s, superPartOffsets_);
                if (superMethodId_ == null) {
                    allParts.addAllElts(superPartOffsets_);
                    localSum_ += s.length()+1;
                    continue;
                }
                if (!formattedMethodId_.eqPartial(superMethodId_.quickOverrideFormat(formattedType_,formatted_,_context))) {
                    allParts.addAllElts(superPartOffsets_);
                    localSum_ += s.length()+1;
                    continue;
                }
                CustList<PartOffset> partMethods_ = new CustList<PartOffset>();
                RootBlock root_ = analyzing_.getAnaClassBody(cl_);
                CustList<OverridableBlock> methods_ = ClassesUtil.getMethodExecBlocks(root_);
                CustList<GeneStringOverridable> list_ = new CustList<GeneStringOverridable>();
                int rc_ = _context.getAnalyzing().getTraceIndex();
                for (OverridableBlock m: methods_) {
                    if (m.getId().eq(superMethodId_)) {
                        ClassMethodId ref_ = new ClassMethodId(cl_,m.getId());
                        CustList<PartOffset> partMethod_ = new CustList<PartOffset>();
                        StringList l_ = new StringList();
                        LinkageUtil.addParts(analyzing_.getStandards().getDisplayedStrings(),analyzing_.getRefFoundTypes(),_root.getFile().getRenderFileName(),ref_,rc_,nameLoc_.length(), l_,l_,partMethod_,-1);
                        partMethods_.addAllElts(partMethod_);
                        GeneStringOverridable g_ = new GeneStringOverridable(formatted_,root_,m);
                        list_.add(g_);
                        break;
                    }
                }
                allParts.addAllElts(partMethods_);
                allParts.addAllElts(superPartOffsets_);
                CustList<GeneStringOverridable> methodIds_ = ov_.getMethodIds();
                methodIds_.addAllElts(list_);
                localSum_ += s.length()+1;
            }
            sum_ += o.length()+1;
            overrides.add(ov_);
        }
    }

    public CustList<PartOffset> getAllParts() {
        return allParts;
    }

    public CustList<OverridingMethodDto> getOverrides() {
        return overrides;
    }
}
