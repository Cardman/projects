package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecLambdaFieldContent {
    private final ClassField classField;
    private final boolean staticField;
    private final boolean finalField;
    private final boolean affField;
    private final ExecRootBlock rootBlock;
    private final ExecAnnotableBlock infoBlock;
    public ExecLambdaFieldContent(ClassField _classField, AnaLambdaFieldContent _field, MemberId _id, Forwards _forwards) {
        classField = _classField;
        staticField = _field.isStaticField();
        finalField = _field.isFinalField();
        affField = _field.isAffField();
        rootBlock = FetchMemberUtil.fetchType(_id, _forwards);
        infoBlock = FetchMemberUtil.fetchField(_id, _forwards);
    }

    public ClassField getClassField() {
        return classField;
    }

    public boolean isStaticField() {
        return staticField;
    }

    public boolean isFinalField() {
        return finalField;
    }

    public boolean isAffField() {
        return affField;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public ExecAnnotableBlock getInfoBlock() {
        return infoBlock;
    }
}
