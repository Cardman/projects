package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecAnnotableParametersBlock;

public interface AnnotableParametersBlock extends AnnotableBlock {

    void buildAnnotationsParameters(ContextEl _context, ExecAnnotableParametersBlock _ann);

}
