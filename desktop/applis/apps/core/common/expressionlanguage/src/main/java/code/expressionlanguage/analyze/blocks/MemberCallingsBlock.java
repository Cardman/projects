package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.reach.blocks.ReachMemberCallingsBlock;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.MappingLocalType;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.util.StringList;
import code.util.StringMap;

public abstract class MemberCallingsBlock extends BracedBlock implements AccessedFct,FunctionBlock,ReturnableWithSignature,WithContext {

    private final StringMap<MappingLocalType> mappings = new StringMap<MappingLocalType>();
    private final AnonymousElementsFct elements = new AnonymousElementsFct();
    MemberCallingsBlock(int _offset) {
        super(_offset);
    }

    private static void removeLabel(AbsBk _en, StringList _labels) {
        if (_en instanceof BreakableBlock && !((BreakableBlock) _en).getRealLabelInfo().getInfo().isEmpty()) {
            _labels.removeLast();
        }
    }

    public final void buildFctInstructionsReadOnly(AnalyzedPageEl _page) {
        _page.setSumOffset(getOffset());
        _page.zeroOffset();
        _page.setVariableIssue(false);
        AbsBk firstChild_ = getFirstChild();
        _page.setCurrentBlock(this);
        _page.setCurrentFct(this);
        _page.setCurrentFile(getFile());
        StringMap<StringList> vars_ = ContextUtil.getCurrentConstraints(_page);
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        AnalyzingEl anEl_ = new AnalyzingEl(mapping_);
        AbsBk en_ = this;
        StringList labels_ = anEl_.getLabels();
        if (firstChild_ == null) {
            checkIndexes(en_, _page);
            _page.getInfosVars().clear();
            ReachMemberCallingsBlock.newReachBlocks(this).buildFctInstructionsReadOnly(_page,anEl_);
            return;
        }
        while (true) {
            _page.setCurrentBlock(en_);
            anEl_.putLabel(this);
            if (en_ != this) {
                en_.checkLabelReference(anEl_, _page);
            }
            checkIndexes(en_, _page);
            AbsBk n_ = en_.getFirstChild();
            addParent(anEl_, en_, n_);
            boolean visit_ = visit(_page, en_);
            if (visit_ && n_ != null) {
                en_ = n_;
                continue;
            }
            checkTree(en_, anEl_, _page);
            removeLabel(en_, labels_);
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                BracedBlock par_;
                par_ = en_.getParent();
                _page.setCurrentBlock(par_);
                if (par_ == this) {
                    par_.removeAllVars(_page);
                    _page.getInfosVars().clear();
                    ReachMemberCallingsBlock.newReachBlocks(this).buildFctInstructionsReadOnly(_page,anEl_);
                    return;
                }
                checkTree(par_, anEl_, _page);
                par_.removeAllVars(_page);
                removeLabel(par_, labels_);
                en_ = par_;
            }
        }
    }

    private static void checkTree(AbsBk _en, AnalyzingEl _anEl, AnalyzedPageEl _page) {
        if (_en instanceof CheckableTree) {
            ((CheckableTree)_en).checkTree(_anEl, _page);
        }
    }

    private boolean visit(AnalyzedPageEl _page, AbsBk _en) {
        boolean visit_ = true;
        if (_en != this) {
            visit_ = tryBuildExpressionLanguageReadOnly(_en, _page);
        }
        return visit_;
    }

    private void checkIndexes(AbsBk _en, AnalyzedPageEl _page) {
        for (int i:_en.getBadIndexes()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(getFile());
            b_.setIndexFile(i);
            //underline index char
            b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
            _page.addLocError(b_);
            _en.addErrorBlock(b_.getBuiltError());
        }
    }

    private static void addParent(AnalyzingEl _anEl, AbsBk _en,
                                  AbsBk _n) {
        if (_en instanceof BracedBlock && _n != null && _en instanceof BreakableBlock) {
            _anEl.putLabel(_en, ((BreakableBlock) _en).getRealLabelInfo().getInfo());
        }
    }

    public StringMap<MappingLocalType> getRefMappings() {
        return AnaTypeUtil.getRefMappings(mappings);
    }

    public StringMap<MappingLocalType> getMappings() {
        return mappings;
    }

    @Override
    public AnonymousElementsFct getElements() {
        return elements;
    }

}
