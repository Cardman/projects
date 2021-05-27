package code.expressionlanguage.exec.util;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class ExecFormattedRootBlock {
    private final ExecRootBlock rootBlock;
    private final String formatted;

    public ExecFormattedRootBlock(ExecRootBlock _rootBlock) {
        this(_rootBlock,_rootBlock.getGenericString());
    }

    public ExecFormattedRootBlock(ExecRootBlock _rootBlock, String _formatted) {
        this.rootBlock = _rootBlock;
        this.formatted = _formatted;
    }
    public static ExecFormattedRootBlock build(String _formatted, Classes _classes) {
        String id_ = StringExpUtil.getIdFromAllTypes(_formatted);
        return new ExecFormattedRootBlock(_classes.getClassBody(id_),_formatted);
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public String getFormatted() {
        return formatted;
    }
}
