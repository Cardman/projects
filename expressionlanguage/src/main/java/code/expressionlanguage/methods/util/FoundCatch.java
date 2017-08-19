package code.expressionlanguage.methods.util;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.CatchEval;

public final class FoundCatch {
    
    private CatchEval catchBl;

    private int importingPage;

    private int tryBlock;

    private int catchBlock;

    private Block processingFinallyBlock;

    private boolean cause;
    
    public boolean processCatchingBlock() {
        return catchBl != null || processingFinallyBlock != null;
    }

    public int getImportingPage() {
        return importingPage;
    }

    public void setImportingPage(int _importingPage) {
        importingPage = _importingPage;
    }

    public int getTryBlock() {
        return tryBlock;
    }

    public void setTryBlock(int _tryBlock) {
        tryBlock = _tryBlock;
    }

    public int getCatchBlock() {
        return catchBlock;
    }

    public void setCatchBlock(int _catchBlock) {
        catchBlock = _catchBlock;
    }

    public CatchEval getCatchBl() {
        return catchBl;
    }

    public void setCatchBl(CatchEval _catchBl) {
        catchBl = _catchBl;
    }

    public Block getProcessingFinallyBlock() {
        return processingFinallyBlock;
    }

    public void setProcessingFinallyBlock(Block _processingFinallyBlock) {
        processingFinallyBlock = _processingFinallyBlock;
    }

    public boolean isCause() {
        return cause;
    }

    public void setCause(boolean _cause) {
        cause = _cause;
    }
}
