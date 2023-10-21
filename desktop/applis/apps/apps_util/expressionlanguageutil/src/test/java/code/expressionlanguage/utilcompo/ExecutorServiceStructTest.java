package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.EquallableElUtUtil;
import code.mock.MockInterceptor;
import code.mock.MockRunnableStruct;
import org.junit.Test;

public final class ExecutorServiceStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        ExecutorServiceStruct essOne_ = (ExecutorServiceStruct) call(new FctExecutorService0(new MockInterceptor()),null,null,null,null,null);
        ExecutorServiceStruct essTwo_ = (ExecutorServiceStruct) call(new FctExecutorService0(new MockInterceptor()),null,null,null,null,null);
        assertFalse(essOne_.sameReference(essTwo_));
    }
    @Test
    public void init2() {
        ArgumentListCall list_ = one(new IntStruct(2));
        ExecutorServiceStruct essOne_ = (ExecutorServiceStruct) call(new FctExecutorService1(new MockInterceptor()),null,null,null, list_,null);
        assertTrue(essOne_.sameReference(essOne_));
    }
    @Test
    public void init3() {
        ExecutorServiceStruct essOne_ = (ExecutorServiceStruct) call(new DfExecutorService(new MockInterceptor()),null,null,null,null);
        ExecutorServiceStruct essTwo_ = (ExecutorServiceStruct) call(new DfExecutorService(new MockInterceptor()),null,null,null,null);
        assertFalse(essOne_.sameReference(essTwo_));
    }
    @Test
    public void execute() {
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        call(new FctExecutorServiceExecute0(),null,null,essOne_, list_,null);
        assertTrue(s_.isStarted());
    }
    @Test
    public void shutdown() {
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        call(new FctExecutorServiceShutdown(),null,null,essOne_, null,null);
        call(new FctExecutorServiceExecute0(),null,null,essOne_, list_,null);
        assertFalse(s_.isStarted());
    }
    @Test
    public void shutdownMult() {
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        call(new FctFutureAttendre(),null,null,call(new FctExecutorServiceSubmit0(),null,null,essOne_, list_,null),null,null);
        call(new FctExecutorServiceShutdown(),null,null,essOne_, null,null);
        call(new FctExecutorServiceExecute0(),null,null,essOne_, list_,null);
        assertTrue(s_.isStarted());
    }
    @Test
    public void cancelFuture1() {
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        assertFalse(call(new FctFutureCancel(),null,null,call(new FctExecutorServiceSubmit0(),null,null,essOne_, list_,null),null,null));
    }
    @Test
    public void cancelFuture2() {
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),2);
        MockRunnableStruct s_ = new MockRunnableStruct("");
        ArgumentListCall list_ = one(s_);
        call(new FctExecutorServiceShutdown(),null,null,essOne_, null,null);
        Struct f_ = call(new FctExecutorServiceSubmit0(),null, null, essOne_, list_, null);
        assertTrue(call(new FctFutureCancel(),null,null, f_,null,null));
    }
    @Test
    public void no() {
        ExecutorServiceStruct essOne_ = new ExecutorServiceStruct(new MockInterceptor(),2);
        call(new FctExecutorServiceShutdown(),null,null,essOne_, null,null);
        assertSame(NullStruct.NULL_VALUE,call(new FctExecutorServiceSubmit0(),null, null, essOne_,  one(NullStruct.NULL_VALUE), null));
    }
}
