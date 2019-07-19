package code.formathtml;

import code.formathtml.util.RendTryBlockStack;

public interface RendEval extends RendBreakableBlock, RendBuildableElMethod {
    void processToFinally(ImportingPage _ip, RendTryBlockStack _stack);
}
