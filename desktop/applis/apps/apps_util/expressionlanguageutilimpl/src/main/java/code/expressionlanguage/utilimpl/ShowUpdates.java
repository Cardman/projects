package code.expressionlanguage.utilimpl;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.expressionlanguage.utilcompo.ProgressingTests;
import code.expressionlanguage.utilcompo.RunnableContextEl;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ShowUpdates implements Runnable {
    private AtomicBoolean show = new AtomicBoolean();
    private Struct info;
    private RunnableContextEl ctx;
    private ProgressingTests progressingTests;
    private LgNamesWithNewAliases evolved;
    public ShowUpdates(Struct _info, RunnableContextEl _ctx, ProgressingTests _progressingTests,LgNamesWithNewAliases _evolved) {
        show.set(true);
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
        while (show.get()) {
            progressingTests.updateInfos(ctx,info,doneBefore_,methodBefore_,countBefore_, evolved);
            Struct count_ = ((FieldableStruct) info).getEntryStruct(new ClassField(infoTest_, infoTestCount_)).getStruct();
            Struct done_ = ((FieldableStruct) info).getEntryStruct(new ClassField(infoTest_, infoTestDone_)).getStruct();
            Struct method_ = ((FieldableStruct) info).getEntryStruct(new ClassField(infoTest_, curMethodName_)).getStruct();
            doneBefore_ = done_;
            methodBefore_ = method_;
            countBefore_ = count_;
        }
        progressingTests.finish(ctx,info, evolved);
    }
    public void stop() {
        show.set(false);
    }
}
