package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;

public interface AnnotableBlock {

    void buildAnnotations(ExecAnnotableBlock _ann, AnalyzedPageEl _page);

}
