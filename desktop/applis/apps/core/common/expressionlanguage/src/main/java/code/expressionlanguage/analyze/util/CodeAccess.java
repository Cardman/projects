package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.RootBlock;

final class CodeAccess {
    private final int code;
    private final RootBlock root;

    CodeAccess(int _code, RootBlock _root) {
        this.code = _code;
        this.root = _root;
    }

    RootBlock getRoot() {
        return root;
    }

    int getCode() {
        return code;
    }
}
