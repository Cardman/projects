package code.formathtml.util;

import code.formathtml.ImportingPage;
import code.formathtml.RendParentBlock;

public interface RendRemovableVars {

    void removeVarAndLoop(ImportingPage _ip);

    RendParentBlock getBlock();
    RendParentBlock getCurrentVisitedBlock();
    RendParentBlock getLastBlock();
}
