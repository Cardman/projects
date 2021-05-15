package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;
import code.util.core.StringUtil;

public final class ExecExplicitContent {
    private final String className;
    private final int offset;
    private final ExecFormattedRootBlock formattedType;

    public ExecExplicitContent(AnaExplicitContent _cont, Forwards _fwd) {
        className = _cont.getClassName();
        offset = _cont.getOffset();
        AnaFormattedRootBlock implicit_ = _cont.getFormattedTypeOwner();
        if (implicit_ == null) {
            formattedType = new ExecFormattedRootBlock(null,"");
            return;
        }
        formattedType = FetchMemberUtil.fwdFormatType(implicit_, _fwd);
    }
    public ExecExplicitContent(AnaCallFctContent _cont, Forwards _fwd) {
        className = _cont.getFormattedType().getFormatted();
        offset = StringUtil.getFirstPrintableCharIndex(_cont.getMethodName());
        formattedType = FetchMemberUtil.fwdFormatType(_cont.getFormattedType(),_fwd);
    }

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public int getOffset() {
        return offset;
    }

    public String getClassName() {
        return className;
    }
}
