package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.utilcompo.ExecutorServiceStruct;
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
        ExecutorServiceStruct essOne_ = (ExecutorServiceStruct) new FctExecutorService0(th_).call(null,null,null,null,null).getValue().getStruct();
        ExecutorServiceStruct essTwo_ = (ExecutorServiceStruct) new FctExecutorService0(th_).call(null,null,null,null,null).getValue().getStruct();
        assertFalse(essOne_.sameReference(essTwo_));
    }
    @Test
    public void init2() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ArgumentListCall list_ = new ArgumentListCall();
        list_.getArgumentWrappers().add(new ArgumentWrapper(new IntStruct(2)));
        ExecutorServiceStruct essOne_ = (ExecutorServiceStruct) new FctExecutorService1(th_).call(null,null,null, list_,null).getValue().getStruct();
        assertTrue(essOne_.sameReference(essOne_));
    }
    @Test
    public void init3() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ExecutorServiceStruct essOne_ = (ExecutorServiceStruct) new DfExecutorService(th_).call(null,null,null,null).getValue().getStruct();
        ExecutorServiceStruct essTwo_ = (ExecutorServiceStruct) new DfExecutorService(th_).call(null,null,null,null).getValue().getStruct();
        assertFalse(essOne_.sameReference(essTwo_));
    }
    @Test
    public void execute() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(th_,2);
        ArgumentListCall list_ = new ArgumentListCall();
        MockRunnableStruct s_ = new MockRunnableStruct("");
        list_.getArgumentWrappers().add(new ArgumentWrapper(s_));
        new FctExecutorServiceExecute0().call(null,null,essOne_, list_,null);
        assertTrue(s_.isStarted());
    }
    @Test
    public void shutdown() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(th_,2);
        ArgumentListCall list_ = new ArgumentListCall();
        MockRunnableStruct s_ = new MockRunnableStruct("");
        list_.getArgumentWrappers().add(new ArgumentWrapper(s_));
        new FctExecutorServiceShutdown().call(null,null,essOne_, null,null);
        new FctExecutorServiceExecute0().call(null,null,essOne_, list_,null);
        assertFalse(s_.isStarted());
    }
    @Test
    public void shutdownMult() {
        AbstractThreadFactory th_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"})).getThreadFactory();
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(th_,2);
        ArgumentListCall list_ = new ArgumentListCall();
        MockRunnableStruct s_ = new MockRunnableStruct("");
        list_.getArgumentWrappers().add(new ArgumentWrapper(s_));
        new FctFutureAttendre().call(null,null,new FctExecutorServiceSubmit0().call(null,null,essOne_, list_,null).getValue().getStruct(),null,null);
        new FctExecutorServiceShutdown().call(null,null,essOne_, null,null);
        new FctExecutorServiceExecute0().call(null,null,essOne_, list_,null);
        assertTrue(s_.isStarted());
    }
}
