package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.functionid.MethodAccessKind;

public interface WithContext {
    MethodAccessKind getStaticContext();
}
