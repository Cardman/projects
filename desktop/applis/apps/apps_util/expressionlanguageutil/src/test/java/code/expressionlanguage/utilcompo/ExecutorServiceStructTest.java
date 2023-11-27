package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.EquallableElUtUtil;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockInterceptor;
import code.mock.MockRunnableStruct;
import code.threads.AbstractThreadFactory;
import code.threads.ConcreteBoolean;
import org.junit.Test;

public final class ExecutorServiceStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ContextEl ctx_ = gene(newLgNamesGuiSample(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})), null),new Options());
        StackCall st_ = stack(ctx_);
        ExecutorServiceStruct essOne_ = (ExecutorServiceStruct) call(new FctExecutorService0(new MockInterceptor(), th_, ""),null,ctx_,null,null,st_);
        ExecutorServiceStruct essTwo_ = (ExecutorServiceStruct) call(new FctExecutorService0(new MockInterceptor(), th_, ""),null,ctx_,null,null,st_);
        assertFalse(essOne_.sameReference(essTwo_));
    }
    @Test
    public void init2() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ContextEl ctx_ = gene(newLgNamesGuiSample(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})), null),new Options());
        StackCall st_ = stack(ctx_);
        ArgumentListCall list_ = one(new IntStruct(2));
        ExecutorServiceStruct essOne_ = (ExecutorServiceStruct) call(new FctExecutorService1(new MockInterceptor(), th_, ""),null,ctx_,null, list_,st_);
        assertTrue(essOne_.sameReference(essOne_));
    }
    @Test
    public void init3() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ContextEl ctx_ = gene(newLgNamesGuiSample(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})), null),new Options());
        StackCall st_ = stack(ctx_);
        ExecutorServiceStruct essOne_ = (ExecutorServiceStruct) call(new DfExecutorService(new MockInterceptor(), th_, ""),null,ctx_,null,st_);
        ExecutorServiceStruct essTwo_ = (ExecutorServiceStruct) call(new DfExecutorService(new MockInterceptor(), th_, ""),null,ctx_,null,st_);
        assertFalse(essOne_.sameReference(essTwo_));
    }
    @Test
    public void execute() {
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),new ConcreteBoolean(),2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        ContextEl ctx_ = gene(newLgNamesGuiSample(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})), null),new Options());
        StackCall st_ = stack(ctx_);
        call(new FctExecutorServiceExecute0(""),null,ctx_,essOne_, list_,st_);
        assertTrue(s_.isStarted());
    }
    @Test
    public void shutdown() {
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),new ConcreteBoolean(),2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        ContextEl ctx_ = gene(newLgNamesGuiSample(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})), null),new Options());
        StackCall st_ = stack(ctx_);
        assertFalse(call(new FctExecutorServiceStopped(),null,ctx_,essOne_, null,st_));
        call(new FctExecutorServiceShutdown(""),null,ctx_,essOne_, null,st_);
        assertTrue(call(new FctExecutorServiceStopped(),null,ctx_,essOne_, null,st_));
        call(new FctExecutorServiceExecute0(""),null,ctx_,essOne_, list_,st_);
        assertFalse(s_.isStarted());
    }
    @Test
    public void shutdownMult() {
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),new ConcreteBoolean(),2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        ContextEl ctx_ = gene(newLgNamesGuiSample(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})), null),new Options());
        StackCall st_ = stack(ctx_);
        call(new FctFutureAttendre(""),null,ctx_,call(new FctExecutorServiceSubmit0(""),null,ctx_,essOne_, list_,st_),null,null);
        call(new FctExecutorServiceShutdown(""),null,ctx_,essOne_, null,st_);
        call(new FctExecutorServiceExecute0(""),null,ctx_,essOne_, list_,st_);
        assertTrue(s_.isStarted());
    }
    @Test
    public void cancelFuture1() {
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),new ConcreteBoolean(),2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        ContextEl ctx_ = gene(newLgNamesGuiSample(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})), null),new Options());
        StackCall st_ = stack(ctx_);
        assertFalse(call(new FctFutureCancel(""),null,null,call(new FctExecutorServiceSubmit0(""),null,ctx_,essOne_, list_,st_),null,st_));
    }
    @Test
    public void cancelFuture2() {
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),new ConcreteBoolean(),2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        ContextEl ctx_ = gene(newLgNamesGuiSample(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})), null),new Options());
        StackCall st_ = stack(ctx_);
        call(new FctExecutorServiceShutdown(""),null,ctx_,essOne_, null,st_);
        Struct f_ = call(new FctExecutorServiceSubmit0(""),null, ctx_, essOne_, list_, st_);
        assertTrue(call(new FctFutureCancel(""),null,ctx_, f_,null,st_));
    }
    @Test
    public void no() {
        ContextEl ctx_ = gene(newLgNamesGuiSample(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})), null),new Options());
        StackCall st_ = stack(ctx_);
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),new ConcreteBoolean(),2);
        call(new FctExecutorServiceShutdown(""),null,ctx_,essOne_, null,st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctExecutorServiceSubmit0(""),null, ctx_, essOne_,  one(NullStruct.NULL_VALUE), st_));
    }
}
