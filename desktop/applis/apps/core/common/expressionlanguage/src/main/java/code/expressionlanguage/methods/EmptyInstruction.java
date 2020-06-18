package code.expressionlanguage.methods;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecEmptyInstruction;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.util.CustList;

public final class EmptyInstruction extends Leaf implements BuildableElMethod {

    public EmptyInstruction(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecEmptyInstruction exec_ = new ExecEmptyInstruction(getOffset());
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }


}
