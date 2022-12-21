package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.EquallableElUtUtil;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockRunnableStruct;
import code.threads.AbstractThreadFactory;
import org.junit.Test;

public final class ExecutorServiceStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ExecutorServiceStruct essOne_ = (ExecutorServiceStruct) call(new FctExecutorService0(th_),null,null,null,null,null);
        ExecutorServiceStruct essTwo_ = (ExecutorServiceStruct) call(new FctExecutorService0(th_),null,null,null,null,null);
        assertFalse(essOne_.sameReference(essTwo_));
    }
    @Test
    public void init2() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ArgumentListCall list_ = one(new IntStruct(2));
        ExecutorServiceStruct essOne_ = (ExecutorServiceStruct) call(new FctExecutorService1(th_),null,null,null, list_,null);
        assertTrue(essOne_.sameReference(essOne_));
    }
    @Test
    public void init3() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ExecutorServiceStruct essOne_ = (ExecutorServiceStruct) call(new DfExecutorService(th_),null,null,null,null);
        ExecutorServiceStruct essTwo_ = (ExecutorServiceStruct) call(new DfExecutorService(th_),null,null,null,null);
        assertFalse(essOne_.sameReference(essTwo_));
    }
    @Test
    public void execute() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(th_,2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        call(new FctExecutorServiceExecute0(),null,null,essOne_, list_,null);
        assertTrue(s_.isStarted());
    }
    @Test
    public void shutdown() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(th_,2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        call(new FctExecutorServiceShutdown(),null,null,essOne_, null,null);
        call(new FctExecutorServiceExecute0(),null,null,essOne_, list_,null);
        assertFalse(s_.isStarted());
    }
    @Test
    public void shutdownMult() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(th_,2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        call(new FctFutureAttendre(),null,null,call(new FctExecutorServiceSubmit0(),null,null,essOne_, list_,null),null,null);
        call(new FctExecutorServiceShutdown(),null,null,essOne_, null,null);
        call(new FctExecutorServiceExecute0(),null,null,essOne_, list_,null);
        assertTrue(s_.isStarted());
    }
    @Test
    public void cancelFuture1() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(th_,2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        assertFalse(call(new FctFutureCancel(),null,null,call(new FctExecutorServiceSubmit0(),null,null,essOne_, list_,null),null,null));
    }
    @Test
    public void cancelFuture2() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(th_,2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        call(new FctExecutorServiceShutdown(),null,null,essOne_, null,null);
        Struct f_ = call(new FctExecutorServiceSubmit0(),null, null, essOne_, list_, null);
        assertTrue(call(new FctFutureCancel(),null,null, f_,null,null));
    }
}
