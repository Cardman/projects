package code.formathtml;

import code.formathtml.stacks.RendTryBlockStack;

public interface RendEval extends RendBreakableBlock, RendBuildableElMethod {
    void processToFinally(ImportingPage _ip, RendTryBlockStack _stack);
}
