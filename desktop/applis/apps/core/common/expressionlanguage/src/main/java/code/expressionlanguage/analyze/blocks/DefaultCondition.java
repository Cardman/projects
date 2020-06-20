package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecDefaultCondition;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;

public final class DefaultCondition extends SwitchPartBlock {

    public DefaultCondition(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        checkDefault(_cont);
        ExecDefaultCondition exec_ = new ExecDefaultCondition(getOffset());
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }

    private void checkDefault(ContextEl _cont) {
        BracedBlock b_ = getParent();
        if (!(b_ instanceof SwitchBlock)) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            //key word len
            un_.buildError(_cont.getAnalysisMessages().getUnexpectedCaseDef(),
                    _cont.getKeyWords().getKeyWordDefault(),
                    "",
                    _cont.getKeyWords().getKeyWordSwitch());
            _cont.addError(un_);
        } else {
            _cont.getCoverage().putBlockOperationsSwitchs(_cont,b_,this);
            Block first_ = b_.getFirstChild();
            while (first_ != this) {
                if (first_ instanceof DefaultCondition) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(getFile().getFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    //key word len
                    un_.buildError(_cont.getAnalysisMessages().getUnexpectedDefDup(),
                            _cont.getKeyWords().getKeyWordDefault(),
                            _cont.getKeyWords().getKeyWordSwitch());
                    _cont.addError(un_);
                    break;
                }
                first_ = first_.getNextSibling();
            }
        }
    }

}
