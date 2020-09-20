package code.expressionlanguage.assign.blocks;


import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;

public final class AssSimStdMethodBlock extends AssBracedBlock {
    AssSimStdMethodBlock(boolean _completeNormally, boolean _completeNormallyGroup) {
        super(_completeNormally,_completeNormallyGroup);
    }
    public final void buildFctInstructions(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssBlock firstChild_ = getFirstChild();
        AssBlock en_ = this;
        if (firstChild_ == null) {
            return;
        }
        while (true) {
            AssBlock n_ = en_.getFirstChild();
            tryBuildExpressionLanguage(en_, _a, _page);
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                AssBracedBlock par_;
                par_ = en_.getParent();
                if (par_ == this) {
                    return;
                }
                if (par_ instanceof AssSimForMutableIterativeLoop) {
                    ((AssSimForMutableIterativeLoop)par_).buildIncrementPart(_a, _page);
                }
                en_ = par_;
            }
        }
    }

    private void tryBuildExpressionLanguage(AssBlock en_, AssignedVariablesBlock a, AnalyzedPageEl _page) {
        if (en_ instanceof AssBuildableElMethod) {
            ((AssBuildableElMethod)en_).buildExpressionLanguage(a, _page);
        }
    }
}
