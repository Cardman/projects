package code.expressionlanguage.exec.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.AbstractStackCall;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.functionid.MethodAccessKind;

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

    public ExecFormattedRootBlock(ExecFormattedRootBlock _rootBlock, String _formatted) {
        this.rootBlock = _rootBlock.rootBlock;
        this.formatted = _formatted;
    }
    public static ExecFormattedRootBlock defValue() {
        return new ExecFormattedRootBlock((ExecRootBlock)null,"");
    }
    public static ExecFormattedRootBlock gene(ExecFormattedRootBlock _rootBlock) {
        return new ExecFormattedRootBlock(_rootBlock.getRootBlock());
    }
    public static ExecFormattedRootBlock build(String _formatted, Classes _classes) {
        String id_ = StringExpUtil.getIdFromAllTypes(_formatted);
        return new ExecFormattedRootBlock(_classes.getClassBody(id_),_formatted);
    }

    public static ExecFormattedRootBlock quickFormat(ExecFormattedRootBlock _type, ExecFormattedRootBlock _second) {
        return new ExecFormattedRootBlock(_second, ExecInherits.quickFormat(_type,_second.getFormatted()));
    }

    public static ExecFormattedRootBlock getFullObject(String _subType, ExecFormattedRootBlock _superType, ContextEl _context) {
        return new ExecFormattedRootBlock(_superType, ExecInherits.getFullObject(_subType,_superType.getFormatted(),_context));
    }

    public static ExecFormattedRootBlock formatType(ExecFormattedRootBlock _type, MethodAccessKind _kind, AbstractStackCall _stackCall) {
        if (_kind == MethodAccessKind.STATIC_CALL) {
            return StackCall.formatVarType(_stackCall,_type);
        }
        return _type;
    }

    public static String formatType(ExecFormattedRootBlock _rootBlock, String _formatted, MethodAccessKind _kind) {
        if (_kind == MethodAccessKind.STATIC_CALL) {
            return ExecInherits.quickFormat(_rootBlock, _formatted);
        }
        return _formatted;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public String getFormatted() {
        return formatted;
    }
}
