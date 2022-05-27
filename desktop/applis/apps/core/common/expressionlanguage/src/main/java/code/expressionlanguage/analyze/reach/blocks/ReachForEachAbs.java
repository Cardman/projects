package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ForEachLoopAbs;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;

public abstract class ReachForEachAbs extends ReachBracedBlock implements ReachBreakableBlock,ReachBuildableElMethod,ReachAbruptGroup {
    private final ForEachLoopAbs meta;
    private final String label;
    protected ReachForEachAbs(AbsBk _info, ForEachLoopAbs _loop) {
        super(_info);
        meta = _loop;
        label = _loop.getLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setSumOffset(meta.getExpressionOffset());
        _page.zeroOffset();
        OperationNode root_ = meta.getRoot();
        Argument argument_ =  ReachOperationUtil.tryCalculate(root_, _page);
        if (Argument.isNullValue(argument_)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFile(_page.getCurrentFile());
            static_.setIndexFile(_page);
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
