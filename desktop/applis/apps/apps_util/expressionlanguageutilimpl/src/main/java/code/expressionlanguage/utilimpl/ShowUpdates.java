package code.expressionlanguage.utilimpl;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.expressionlanguage.utilcompo.ProgressingTests;
import code.expressionlanguage.utilcompo.RunnableContextEl;

public final class ShowUpdates implements Runnable {
    private final Struct info;
    private final RunnableContextEl ctx;
    private final ProgressingTests progressingTests;
    private final LgNamesWithNewAliases evolved;
    public ShowUpdates(Struct _info, RunnableContextEl _ctx, ProgressingTests _progressingTests,LgNamesWithNewAliases _evolved) {
        info = _info;
        ctx = _ctx;
        progressingTests = _progressingTests;
        evolved = _evolved;
    }
    @Override
    public void run() {
        String infoTest_ = evolved.getCustAliases().getAliasInfoTest();
        String infoTestDone_ = evolved.getCustAliases().getAliasInfoTestDone();
        String curMethodName_ = evolved.getCustAliases().getAliasInfoTestCurrentMethod();
        String infoTestCount_ = evolved.getCustAliases().getAliasInfoTestCount();
        Struct doneBefore_ = ((FieldableStruct) info).getEntryStruct(new ClassField(infoTest_, infoTestDone_)).getStruct();
        Struct countBefore_ = ((FieldableStruct) info).getEntryStruct(new ClassField(infoTest_, infoTestCount_)).getStruct();
        Struct methodBefore_ = ((FieldableStruct) info).getEntryStruct(new ClassField(infoTest_, curMethodName_)).getStruct();
        progressingTests.updateInfos(ctx,info,doneBefore_,methodBefore_,countBefore_, evolved);
    }
}
