package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.blocks.AccessingImportingBlock;

public interface AbstractHiddenTypes {
    boolean isHidden(AccessingImportingBlock _global, RootBlock _type);
}
