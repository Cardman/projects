package code.expressionlanguage.utilcompo;

import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ShowUpdates implements Runnable {
    private AtomicBoolean show = new AtomicBoolean();
    private Struct info;
    private RunnableContextEl ctx;
    private ProgressingTests progressingTests;
    public ShowUpdates(Struct _info, RunnableContextEl _ctx, ProgressingTests _progressingTests) {
        show.set(true);
        info = _info;
        ctx = _ctx;
        progressingTests = _progressingTests;
    }
    @Override
    public void run() {
        String infoTest_ = ((LgNamesUtils)ctx.getStandards()).getAliasInfoTest();
        String infoTestDone_ = ((LgNamesUtils)ctx.getStandards()).getAliasInfoTestDone();
        String curMethodName_ = ((LgNamesUtils) ctx.getStandards()).getAliasInfoTestCurrentMethod();
        String infoTestCount_ = ((LgNamesUtils)ctx.getStandards()).getAliasInfoTestCount();
        Struct doneBefore_ = ((FieldableStruct) info).getStruct(new ClassField(infoTest_, infoTestDone_));
        Struct countBefore_ = ((FieldableStruct) info).getStruct(new ClassField(infoTest_, infoTestCount_));
        Struct methodBefore_ = ((FieldableStruct) info).getStruct(new ClassField(infoTest_, curMethodName_));
        while (show.get()) {
            progressingTests.updateInfos(ctx,info,doneBefore_,methodBefore_,countBefore_);
            Struct count_ = ((FieldableStruct) info).getStruct(new ClassField(infoTest_, infoTestCount_));
            Struct done_ = ((FieldableStruct) info).getStruct(new ClassField(infoTest_, infoTestDone_));
            Struct method_ = ((FieldableStruct) info).getStruct(new ClassField(infoTest_, curMethodName_));
            doneBefore_ = done_;
            methodBefore_ = method_;
            countBefore_ = count_;
        }
        progressingTests.finish(ctx,info);
    }
    public void stop() {
        show.set(false);
    }
}
