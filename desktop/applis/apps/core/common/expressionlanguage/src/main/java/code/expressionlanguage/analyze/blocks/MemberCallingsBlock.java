package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.reach.blocks.ReachMemberCallingsBlock;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.MappingLocalType;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class MemberCallingsBlock extends BracedBlock implements FunctionBlock,ReturnableWithSignature {

    private final StringMap<MappingLocalType> mappings = new StringMap<MappingLocalType>();
    private final CustList<RootBlock> reserved = new CustList<RootBlock>();
    private final CustList<AnonymousTypeBlock> anonymous = new CustList<AnonymousTypeBlock>();
    private final CustList<AnonymousFunctionBlock> anonymousFct = new CustList<AnonymousFunctionBlock>();
    private final CustList<SwitchMethodBlock> switchMethods = new CustList<SwitchMethodBlock>();
    private int numberFct;
    private int numberBodyFct;
    MemberCallingsBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    private static void addPossibleEmpty(Block _en) {
        if (_en instanceof BracedBlock && _en.getFirstChild() == null) {
            if (!(_en instanceof SwitchBlock) && !(_en instanceof DoWhileCondition) && (_en instanceof BuildableElMethod || _en instanceof UnclassedBracedBlock)) {
                OffsetsBlock off_ = _en.getOffset();
                EmptyInstruction empty_ = new EmptyInstruction(off_);
                ((BracedBlock) _en).appendChild(empty_);
            }
        }
    }

    private static void removeLabel(Block _en, StringList _labels) {
        if (_en instanceof BreakableBlock && !((BreakableBlock) _en).getRealLabel().isEmpty()) {
            _labels.removeLast();
        }
    }

    public final void buildFctInstructionsReadOnly(AnalyzedPageEl _page) {
        _page.setGlobalOffset(getOffset().getOffsetTrim());
        _page.setOffset(0);
        _page.setVariableIssue(false);
        Block firstChild_ = getFirstChild();
        _page.setCurrentBlock(this);
        _page.setCurrentFct(this);
        StringMap<StringList> vars_ = ContextUtil.getCurrentConstraints(_page);
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        AnalyzingEl anEl_ = new AnalyzingEl(mapping_);
        Block en_ = this;
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
            addPossibleEmpty(en_);
            if (en_ != this) {
                en_.checkLabelReference(anEl_, _page);
            }
            checkIndexes(en_, _page);
            Block n_ = en_.getFirstChild();
            addParent(anEl_, en_, n_);
            boolean visit_ = true;
            if (en_ != this) {
                visit_ = tryBuildExpressionLanguageReadOnly(en_, _page);
            }
            if (visit_ && n_ != null) {
                en_ = n_;
                continue;
            }
            en_.checkTree(anEl_, _page);
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
                par_.checkTree(anEl_, _page);
                par_.removeAllVars(_page);
                removeLabel(par_, labels_);
                en_ = par_;
            }
        }
    }

    private void checkIndexes(Block _en, AnalyzedPageEl _page) {
        for (int i:_en.getBadIndexes()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(i);
            //underline index char
            b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
            _page.addLocError(b_);
            _en.addErrorBlock(b_.getBuiltError());
        }
    }

    private static void addParent(AnalyzingEl _anEl, Block _en,
                                  Block _n) {
        if (_en instanceof BracedBlock && _n != null) {
            if (_en instanceof BreakableBlock) {
                _anEl.putLabel(_en,((BreakableBlock)_en).getRealLabel());
            }
        }
    }

    public abstract  MethodAccessKind getStaticContext();

    public StringMap<MappingLocalType> getMappings() {
        return mappings;
    }

    public CustList<RootBlock> getReserved() {
        return reserved;
    }

    public CustList<AnonymousTypeBlock> getAnonymous() {
        return anonymous;
    }

    public CustList<AnonymousFunctionBlock> getAnonymousFct() {
        return anonymousFct;
    }

    public CustList<SwitchMethodBlock> getSwitchMethods() {
        return switchMethods;
    }

    public int getNumberFct() {
        return numberFct;
    }

    public void setNumberFct(int _numberFct) {
        this.numberFct = _numberFct;
    }

    public int getNumberBodyFct() {
        return numberBodyFct;
    }

    public void setNumberBodyFct(int _numberBodyFct) {
        numberBodyFct = _numberBodyFct;
    }
}
