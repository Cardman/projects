package code.expressionlanguage.methods.util;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.CatchEval;

public final class FoundCatch {

//    private Class<?> exceptionClass;
    
    private CatchEval catchBl;

//    private Element catchElement;

    private int importingPage;

    private int tryBlock;

    private int catchBlock;

    private Block processingFinallyBlock;
//    private Element processingFinally;

    private boolean cause;

//    public Class<?> getExceptionClass() {
//        return exceptionClass;
//    }

//    public void setExceptionClass(Class<?> _exceptionClass) {
//        exceptionClass = _exceptionClass;
//    }
    
    public boolean processCatchingBlock() {
        return catchBl != null || processingFinallyBlock != null;
    }

//    public boolean processCatching() {
//        return catchElement != null || processingFinally != null;
//    }
//
//    public Element getCatchElement() {
//        return catchElement;
//    }
//
//    public void setCatchElement(Element _catchElement) {
//        catchElement = _catchElement;
//    }

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

//    public Element getProcessingFinally() {
//        return processingFinally;
//    }
//
//    public void setProcessingFinally(Element _processingFinally) {
//        processingFinally = _processingFinally;
//    }

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
