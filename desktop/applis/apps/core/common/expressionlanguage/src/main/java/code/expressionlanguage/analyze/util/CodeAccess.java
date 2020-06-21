package code.expressionlanguage.analyze.util;

import code.expressionlanguage.exec.blocks.ExecRootBlock;

final class CodeAccess {
    private final int code;
    private final ExecRootBlock root;

    CodeAccess(int code, ExecRootBlock root) {
        this.code = code;
        this.root = root;
    }

    ExecRootBlock getRoot() {
        return root;
    }

    int getCode() {
        return code;
    }
}
