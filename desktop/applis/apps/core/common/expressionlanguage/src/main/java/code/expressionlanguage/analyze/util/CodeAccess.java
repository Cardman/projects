package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.RootBlock;

final class CodeAccess {
    private final int code;
    private final RootBlock outer;
    private final RootBlock root;

    CodeAccess(int _code, RootBlock _outer, RootBlock _root) {
        this.code = _code;
        outer = _outer;
        this.root = _root;
    }

    RootBlock getRoot() {
        return root;
    }

    RootBlock getOuter() {
        return outer;
    }

    int getCode() {
        return code;
    }
}
