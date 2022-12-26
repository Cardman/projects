package code.expressionlanguage;

import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import org.junit.Test;

public final class AddonClassesTest extends EquallableElUtUtil {
    @Test
    public void executorServiceStruct() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        assertEq(stds_.getCustAliases().getAliasExecutorService(),new ExecutorServiceStruct(pr_.getThreadFactory()).getClassName(stds_.newContext(opt_, getForwards(stds_, opt_))));
    }
    @Test
    public void futureStruct() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(pr_.getThreadFactory(),2);
        ArgumentListCall list_ = one(new MockRunnableStruct(""));
        assertEq(stds_.getCustAliases().getAliasFuture(),call(new FctExecutorServiceSubmit0(),null,null,essOne_, list_,null).getClassName(stds_.newContext(opt_, getForwards(stds_, opt_))));
    }
    @Test
    public void strMap() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        assertEq(stds_.getCustAliases().getAliasTableStringObject(),call(new FctTastr(new MockInterceptor()),null,null,null, null,null).getClassName(stds_.newContext(opt_, getForwards(stds_, opt_))));
    }
    @Test
    public void key() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        StringMapStruct m_ = (StringMapStruct)call(new FctTastr(new MockInterceptor()),null,null,null,null,null);
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        StringStruct value_ = new StringStruct("_");
        call(new FctTastrPut(),null,ctx_,m_,two(new StringStruct(""), value_),st_);
        ArrayStruct pairs_ = (ArrayStruct) call(new FctTastrPairs(), null, ctx_, m_, null, st_);
        Struct entry_ = pairs_.get(0);
        assertEq(stds_.getCustAliases().getAliasEntryStringObject(),entry_.getClassName(ctx_));
    }
    @Test
    public void scheduledExecutorServiceStruct() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        assertEq(stds_.getCustAliases().getAliasScheduledExecutorService(),new ScheduledExecutorServiceStruct(pr_.getThreadFactory()).getClassName(stds_.newContext(opt_, getForwards(stds_, opt_))));
    }
    @Test
    public void thread() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        assertEq(stds_.getCustAliases().getAliasThread(),call(new FctThread(stds_.getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_).getClassName(ctx_));
    }
    @Test
    public void threadSet() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        assertEq(stds_.getCustAliases().getAliasThreadSet(),call(new FctThreadSet(new MockInterceptor()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_).getClassName(ctx_));
    }
}
