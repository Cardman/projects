package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecLambdaCommonContent {

    private final boolean intermediate;
    private final boolean safeInstance;
    private final String returnFieldType;
    private final String fileName;
    private final boolean shiftArgument;
    private final int ancestor;
    private final ExecFormattedRootBlock formattedType;

    public ExecLambdaCommonContent(AnaLambdaCommonContent _cont, Forwards _fwd) {
        intermediate = _cont.isIntermediate();
        safeInstance = _cont.isSafeInstance();
        returnFieldType = _cont.getReturnFieldType();
        fileName = _cont.getFileName();
        shiftArgument = _cont.isShiftArgument();
        ancestor = _cont.getAncestor();
        formattedType = ExecStaticEltContent.build(_fwd,_cont.getFoundFormatted());
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public boolean isShiftArgument() {
        return shiftArgument;
    }

    public boolean isSafeInstance() {
        return safeInstance;
    }

    public String getReturnFieldType() {
        return returnFieldType;
    }

    public String getFileName() {
        return fileName;
    }

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public int getAncestor() {
        return ancestor;
    }
}
