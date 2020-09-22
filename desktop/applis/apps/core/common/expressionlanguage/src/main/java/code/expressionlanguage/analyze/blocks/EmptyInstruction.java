package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.blocks.ExecEmptyInstruction;
import code.expressionlanguage.analyze.files.OffsetsBlock;

public final class EmptyInstruction extends Leaf implements BuildableElMethod {

    public EmptyInstruction(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        ExecEmptyInstruction exec_ = new ExecEmptyInstruction(getOffset());
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getCoverage().putBlockOperations(exec_,this);
    }


}
