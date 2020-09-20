package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.blocks.ExecAnnotableParametersBlock;

public interface AnnotableParametersBlock extends AnnotableBlock {

    void buildAnnotationsParameters(ExecAnnotableParametersBlock _ann, AnalyzedPageEl _page);

}
