package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.AbstractThreadFactory;
import org.junit.Test;

public final class ScheduledExecutorServiceStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        ContextEl ctx_ = gene(newLgNamesGuiSample(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})), null),new Options());
        StackCall st_ = stack(ctx_);
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ScheduledExecutorServiceStruct essOne_ = (ScheduledExecutorServiceStruct) call(new FctScheduledExecutorService0(th_, ""),null,ctx_,null,null,st_);
        ScheduledExecutorServiceStruct essTwo_ = (ScheduledExecutorServiceStruct) call(new FctScheduledExecutorService0(th_, ""),null,ctx_,null,null,st_);
        assertFalse(essOne_.sameReference(essTwo_));
    }
    @Test
    public void init2() {
        ContextEl ctx_ = gene(newLgNamesGuiSample(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})), null),new Options());
        StackCall st_ = stack(ctx_);
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ScheduledExecutorServiceStruct essOne_ = (ScheduledExecutorServiceStruct) call(new DfScheduledExecutorService(th_, ""),null,ctx_,null,st_);
        ScheduledExecutorServiceStruct essTwo_ = (ScheduledExecutorServiceStruct) call(new DfScheduledExecutorService(th_, ""),null,ctx_,null,st_);
        assertFalse(essOne_.sameReference(essTwo_));
    }
    @Test
    public void millis() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AbstractThreadFactory th_ = pr_.getThreadFactory();
        ScheduledExecutorServiceStruct essOne_ = new ScheduledExecutorServiceStruct(th_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = three(s_,new IntStruct(1),new IntStruct(1));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getExecContent().getCustAliases().setAliasFuture("_");
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        assertEq(stds_.getExecContent().getCustAliases().getAliasFuture(),call(new FctScheduledExecutorMillis0(""),null,ctx_,essOne_, list_,null).getClassName(ctx_));
    }
    @Test
    public void nanos() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AbstractThreadFactory th_ = pr_.getThreadFactory();
        ScheduledExecutorServiceStruct essOne_ = new ScheduledExecutorServiceStruct(th_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = three(s_,new IntStruct(1),new IntStruct(1));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getExecContent().getCustAliases().setAliasFuture("_");
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        assertEq(stds_.getExecContent().getCustAliases().getAliasFuture(),call(new FctScheduledExecutorNanos0(""),null,ctx_,essOne_, list_,null).getClassName(ctx_));
    }
    @Test
    public void shutdown1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AbstractThreadFactory th_ = pr_.getThreadFactory();
        ScheduledExecutorServiceStruct essOne_ = new ScheduledExecutorServiceStruct(th_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArgumentListCall list_ = three(s_,new IntStruct(1),new IntStruct(1));
        call(new FctExecutorServiceShutdown(""),null,null,essOne_, null,st_);
        Struct f_ = call(new FctScheduledExecutorMillis0(""), null, null, essOne_, list_, null);
        assertTrue(call(new FctFutureCancel(""),null,ctx_,f_,null,st_));
    }
    @Test
    public void shutdown2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        AbstractThreadFactory th_ = pr_.getThreadFactory();
        ScheduledExecutorServiceStruct essOne_ = new ScheduledExecutorServiceStruct(th_);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArgumentListCall list_ = three(s_,new IntStruct(1),new IntStruct(1));
        call(new FctExecutorServiceShutdown(""),null,null,essOne_, null,st_);
        Struct f_ = call(new FctScheduledExecutorNanos0(""), null, null, essOne_, list_, null);
        assertTrue(call(new FctFutureCancel(""),null,ctx_,f_,null,st_));
    }
}
