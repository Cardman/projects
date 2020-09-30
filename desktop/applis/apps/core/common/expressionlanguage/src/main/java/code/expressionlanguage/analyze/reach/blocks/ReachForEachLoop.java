package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ForEachLoop;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;

public final class ReachForEachLoop extends ReachBracedBlock implements ReachLoop {
    private ForEachLoop meta;
    private String label;
    protected ReachForEachLoop(ForEachLoop _info) {
        super(_info);
        meta = _info;
        label = _info.getLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setGlobalOffset(meta.getExpressionOffset());
        _page.setOffset(0);
        OperationNode root_ = meta.getRoot();
        Argument argument_ =  ReachOperationUtil.tryCalculate(root_, _page);
        if (Argument.isNullValue(argument_)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_page.getCurrentBlock().getFile().getFileName());
            static_.setIndexFile(_page.getTraceIndex());
            //separator char
            static_.buildError(_page.getAnalysisMessages().getNullValue(),
                    _page.getAliasNullPe());
            _page.addLocError(static_);
            meta.getSepErrors().add(static_.getBuiltError());
        }
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }
}
