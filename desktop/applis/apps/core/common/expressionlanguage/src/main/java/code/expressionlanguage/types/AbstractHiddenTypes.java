package code.expressionlanguage.types;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.blocks.ExecAccessingImportingBlock;

public interface AbstractHiddenTypes {
    boolean isHidden(ExecAccessingImportingBlock _global, RootBlock _type);
}
