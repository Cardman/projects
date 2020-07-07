package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.RootBlock;

final class CodeAccess {
    private final int code;
    private final RootBlock root;

    CodeAccess(int code, RootBlock root) {
        this.code = code;
        this.root = root;
    }

    RootBlock getRoot() {
        return root;
    }

    int getCode() {
        return code;
    }
}
