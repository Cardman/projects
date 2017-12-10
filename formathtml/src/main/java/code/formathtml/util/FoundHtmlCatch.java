package code.formathtml.util;
import code.xml.components.Element;

public final class FoundHtmlCatch {

    private Element catchElement;

    private int importingPage;

    private int tryBlock;

    private int catchBlock;

    private Element processingFinally;

    private boolean cause;

    public boolean processCatching() {
        return catchElement != null || processingFinally != null;
    }

    public Element getCatchElement() {
        return catchElement;
    }

    public void setCatchElement(Element _catchElement) {
        catchElement = _catchElement;
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

    public Element getProcessingFinally() {
        return processingFinally;
    }

    public void setProcessingFinally(Element _processingFinally) {
        processingFinally = _processingFinally;
    }

    public boolean isCause() {
        return cause;
    }

    public void setCause(boolean _cause) {
        cause = _cause;
    }
}
