package code.expressionlanguage.types;

import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;

public interface AbstractHiddenTypes {
    boolean isHidden(AccessingImportingBlock _global, RootBlock _type);
}
