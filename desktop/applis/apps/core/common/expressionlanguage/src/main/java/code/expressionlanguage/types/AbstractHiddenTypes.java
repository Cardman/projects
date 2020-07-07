package code.expressionlanguage.types;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public interface AbstractHiddenTypes {
    boolean isHidden(ExecAccessingImportingBlock _global, RootBlock _type);
}
