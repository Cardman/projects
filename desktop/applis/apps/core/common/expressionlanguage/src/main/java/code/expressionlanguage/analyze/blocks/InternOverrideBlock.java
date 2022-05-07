package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.IdFctOperation;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ResolvedId;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.ExtractedParts;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.FormattedMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.analyze.instr.PartOffsetsClassMethodId;
import code.expressionlanguage.analyze.instr.PartOffsetsClassMethodIdList;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class InternOverrideBlock extends Leaf {
    private final CustList<OverridingMethodDto> overrides = new CustList<OverridingMethodDto>();
    private final String definition;
    private final int definitionOffset;
    private final CustList<PartOffsetsClassMethodIdList> allPartsTypes = new CustList<PartOffsetsClassMethodIdList>();
    public InternOverrideBlock(int _offset, String _definition, int _definitionOffset) {
        super(_offset);
        definition = _definition;
        definitionOffset = _definitionOffset;
    }

    public void buildTypes(RootBlock _root, AnalyzedPageEl _page) {
        for (int i:getBadIndexes()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(getFile());
            b_.setIndexFile(i);
            //underline index char
            b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
            _page.addLocError(b_);
            addErrorBlock(b_.getBuiltError());
        }
        _page.setSumOffset(definitionOffset);
        StringList overrideList_ = StringUtil.splitChar(definition, ';');
        int sum_ = 0;
        for (String o: overrideList_) {
            _page.setOffset(sum_);
            int indexDef_ = o.indexOf(StringExpUtil.EXTENDS_DEF);
            StringList parts_ = StringUtil.splitInTwo(o, indexDef_);
            StringList superMethods_ = new StringList();
            String key_ = parts_.first();
            if (parts_.size() > 1) {
                superMethods_ = StringUtil.splitChar(parts_.last().substring(1),'|');
            }
            ExtractedParts extKey_ = StringExpUtil.tryToExtract(key_, '(', ')');
            String name_ = extKey_.getFirst();
            if (StringExpUtil.isIndexerOrInexist(name_)) {
                sum_ += o.length()+1;
                continue;
            }
            StringList typesKeys_ = StringExpUtil.getAllSepCommaTypes(extKey_.getSecond());
            if (typesKeys_.first().trim().isEmpty()) {
                typesKeys_ = new StringList();
            }
            String idCurrent_ = _root.getFullName();
            CustList<AnaResultPartType> partOffsets_ = new CustList<AnaResultPartType>();
            boolean retRefMain_ = false;
            if (name_.trim().startsWith("~")) {
                retRefMain_ = true;
                name_ = name_.trim().substring(1);
                name_ = name_.trim();
            }
            ResolvedId resolved_ = IdFctOperation.resolveArguments(0, retRefMain_, idCurrent_, name_, MethodAccessKind.INSTANCE, typesKeys_, key_, _page);
            partOffsets_.addAllElts(resolved_.getResults());
            MethodId methodId_ = resolved_.getId();
            if (methodId_ == null) {
                allPartsTypes.add(new PartOffsetsClassMethodIdList(partOffsets_,new CustList<PartOffsetsClassMethodId>(),resolved_.getInfo()));
                sum_ += o.length()+1;
                continue;
            }
            CustList<PartOffsetsClassMethodId> listPart_ = new CustList<PartOffsetsClassMethodId>();
            FormattedMethodId formattedMethodId_ = new FormattedMethodId(methodId_);
            OverridingMethodDto ov_ = new OverridingMethodDto(formattedMethodId_);
            int localSum_ = key_.length()+1;
            for (String s: superMethods_) {
                ExtractedParts extValue_ = StringExpUtil.tryToExtract(s, '(',')');
                String nameLoc_ = extValue_.getFirst();
                if (StringExpUtil.isIndexerOrInexist(nameLoc_)){
                    localSum_ += s.length()+1;
                    continue;
                }
                _page.setOffset(sum_+localSum_);
                StringList args_ = StringExpUtil.getAllSepCommaTypes(extValue_.getSecond());
                String firstFull_ = args_.first();
                int off_ = StringUtil.getFirstPrintableCharIndex(firstFull_);
                String fromType_ = firstFull_.trim();
                CustList<AnaResultPartType> superPartOffsets_ = new CustList<AnaResultPartType>();
                int firstPar_ = extValue_.getFirst().length();
                ResolvedIdType resolvedIdTypeDest_ = ResolvingTypes.resolveAccessibleIdTypeBlock(off_+firstPar_+1,fromType_, _page);
                String cl_ = resolvedIdTypeDest_.getFullName();
                superPartOffsets_.add(resolvedIdTypeDest_.getDels());
                AnaFormattedRootBlock formInfoDest_ = AnaInherits.getOverridingFullTypeByBases(_root, resolvedIdTypeDest_.getGeneType());
                if (formInfoDest_ == null) {
                    localSum_ += s.length()+1;
                    listPart_.add(new PartOffsetsClassMethodId(new CustList<AnaResultPartType>(),superPartOffsets_,null, null, 0, 0));
                    continue;
                }
                RootBlock formattedType_ = formInfoDest_.getRootBlock();
                boolean retRef_ = false;
                int delta_ = StringExpUtil.getOffset(nameLoc_);
                String nameLocId_ = nameLoc_.trim();
                if (nameLocId_.startsWith("~")) {
                    retRef_ = true;
                    delta_++;
                    nameLocId_ = nameLoc_.trim().substring(1);
                    delta_+= StringExpUtil.getOffset(nameLocId_);
                    nameLocId_ = nameLocId_.trim();
                }
                ResolvedId resolvedSuper_ = IdFctOperation.resolveArguments(1, retRef_, cl_,nameLocId_,MethodAccessKind.INSTANCE,args_,s, _page);
                superPartOffsets_.addAllElts(resolvedSuper_.getResults());
                MethodId superMethodId_ = resolvedSuper_.getId();
                if (superMethodId_ == null) {
                    localSum_ += s.length()+1;
                    listPart_.add(new PartOffsetsClassMethodId(new CustList<AnaResultPartType>(),superPartOffsets_,null, null, 0, 0,resolvedSuper_.getInfo()));
                    continue;
                }
                if (!FormattedMethodId.eqPartial(formattedMethodId_,new FormattedMethodId(superMethodId_.quickFormat(AnaInherits.getVarTypes(formInfoDest_))))) {
                    localSum_ += s.length()+1;
                    listPart_.add(new PartOffsetsClassMethodId(new CustList<AnaResultPartType>(),superPartOffsets_,null, null, 0, 0));
                    continue;
                }
                CustList<NamedCalledFunctionBlock> methods_ = formattedType_.getOverridableBlocks();
                CustList<GeneStringOverridable> list_ = new CustList<GeneStringOverridable>();
                ClassMethodId id_ = null;
                AnaTypeFct fct_ = null;
                for (NamedCalledFunctionBlock m: methods_) {
                    if (m.getId().eq(superMethodId_)) {
                        id_ = new ClassMethodId(cl_,m.getId());
                        GeneStringOverridable g_ = new GeneStringOverridable(formInfoDest_, m);
                        list_.add(g_);
                        fct_ = new AnaTypeFct();
                        fct_.setType(formattedType_);
                        fct_.setFunction(m);
                        break;
                    }
                }
                listPart_.add(new PartOffsetsClassMethodId(new CustList<AnaResultPartType>(),superPartOffsets_,id_,fct_, _page,delta_,nameLocId_.length()));
                CustList<GeneStringOverridable> methodIds_ = ov_.getMethodIds();
                methodIds_.addAllElts(list_);
                localSum_ += s.length()+1;
            }
            sum_ += o.length()+1;
            overrides.add(ov_);
            allPartsTypes.add(new PartOffsetsClassMethodIdList(partOffsets_,listPart_));
        }
    }

    public CustList<PartOffsetsClassMethodIdList> getAllPartsTypes() {
        return allPartsTypes;
    }

    public CustList<OverridingMethodDto> getOverrides() {
        return overrides;
    }
}
