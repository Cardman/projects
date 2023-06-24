package code.mock;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.AbsAdvContextGenerator;
import code.gui.AbsGraphicListStr;
import code.threads.AbstractConcurrentMap;
import code.threads.AbstractThread;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class MockRunnableStructTest extends EquallableMockCdmUtil {
    @Test
    public void c14() {
        AbstractInterceptorStdCaller int_ = new MockInterceptor().newInterceptorStdCaller("");
        assertTrue(int_.exitAfterCallInt(new MockInitializer(),null,null));
        assertTrue(int_.stop(new MockInitializer(),null,null));
        assertTrue(int_.stopNormal(new MockInitializer(),null,null));
        assertTrue(int_.invoke(new MockStdCaller(),null,null,null,null,null).getValue().getStruct().sameReference(NullStruct.NULL_VALUE));
    }
    @Test
    public void t17() {
        MockRunnableStruct r_ = new MockRunnableStruct("");
        MockThread th_ = new MockThread(r_, true, new MockAtomicLong());
        th_.start();
        assertTrue(r_.isStarted());
    }
    @Test
    public void t18() {
        MockRunnableStruct r_ = new MockRunnableStruct("");
        assertFalse(r_.isStarted());
    }
    @Test
    public void t19() {
        MockRunnableStruct r_ = new MockRunnableStruct("");
        assertEq("",r_.getClassName(null));
    }

    @Test
    public void m19() {
        AbstractConcurrentMap<String, Struct> m_ = new MockInterceptor().newMapStringStruct();
        assertEq(0,m_.size());
    }

    @Test
    public void m20() {
        AbstractConcurrentMap<Struct, Struct> m_ = new MockInterceptor().newMapStructStruct();
        assertEq(0,m_.size());
    }

    @Test
    public void m21() {
        AbstractConcurrentMap<AbstractThread, Struct> m_ = new MockInterceptor().newMapAbstractThreadStruct();
        assertEq(0,m_.size());
    }

    @Test
    public void m34() {
        MockAdvGraphicListGenerator g_ = new MockAdvGraphicListGenerator(true);
        assertTrue(g_.isCust());
        MockAdvGraphicListGenerator h_ = new MockAdvGraphicListGenerator(false);
        assertFalse(h_.isCust());
        AbsGraphicListStr f_ = g_.createMult(null,null);
        f_.add(NullStruct.NULL_VALUE);
        f_.setSelectedIndice(0);
        f_.setCustCell(null,null,null,null);
        f_.setDefCell(null,null);
        f_.updateGraphics();
        assertTrue(f_.isCust());
        AbsGraphicListStr e_ = g_.createSimple(null,null);
        e_.add(NullStruct.NULL_VALUE);
        e_.setSelectedIndice(0);
        e_.setCustCell(null,null,null,null);
        e_.setDefCell(null,null);
        e_.updateGraphics();
        assertTrue(e_.isCust());
    }
    @Test
    public void bs1() {
        MockMouseButtons m_ = new MockMouseButtons(false,false,false,0);
        assertFalse(m_.isLeftMouseButton());
        assertFalse(m_.isMiddleMouseButton());
        assertFalse(m_.isRightMouseButton());
        assertEq(0,m_.getClickCount());
    }
    @Test
    public void bs2() {
        MockMouseButtons m_ = new MockMouseButtons(true,true,true,1);
        assertTrue(m_.isLeftMouseButton());
        assertTrue(m_.isMiddleMouseButton());
        assertTrue(m_.isRightMouseButton());
        assertEq(1,m_.getClickCount());
    }
    @Test
    public void wh() {
        MockMouseWheel m_ = new MockMouseWheel(1);
        assertEq(1,m_.getWheelRotation());
    }
    @Test
    public void build() {
        MockResultContextNext m_ = new MockResultContextNext("src");
        ResultContext b_ = m_.init(new Options());
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        assertEq(1,m_.files(b_,src_).size());
        ResultContext user_ = m_.next(b_, m_.next(b_, src_));
        Forwards f_ = user_.getForwards();
        AnalyzedPageEl page_ = user_.getPageEl();
        ForwardInfos.generalForward(page_,f_);
        AbsAdvContextGenerator gn_ = m_.generate();
        ContextEl ctx_ = gn_.geneWith(f_);
        Classes.forwardAndClear(ctx_);
        user_.setContext(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Ex");
        ArgumentWrapper a_ = ProcessMethod.calculate(new CustomFoundMethod(new ExecFormattedRootBlock(ex_), new ExecTypeFunction(ex_, ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "exmeth", new CustList<String>())).first()), new Parameters()), user_.getContext(), StackCall.newInstance(InitPhase.NOTHING, ctx_));
        assertEq(1,((NumberStruct) ArgumentListCall.toStr(a_.getValue())).intStruct());
    }
    @Test
    public void buildAna() {
        MockResultContextNext m_ = new MockResultContextNext("src");
        ResultContext b_ = m_.init(new Options());
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt","public class pkg.Ex {public static int exmeth(){return 1;}}");
        AnalyzedPageEl resultAna_ = m_.nextAna(b_, m_.files(b_,src_));
        Forwards forwards_ = new Forwards(b_.getForwards().getGenerator(), b_.getForwards().getLoggable(), b_.getForwards().getFileBuilder(), b_.getForwards().getOptions());
        forwards_.getClasses().getCommon().setStaticFields(resultAna_.getStaticFields());
        ResultContext user_ = new ResultContext(resultAna_, forwards_, resultAna_.getMessages());
        Forwards f_ = user_.getForwards();
        AnalyzedPageEl page_ = user_.getPageEl();
        ForwardInfos.generalForward(page_,f_);
        AbsAdvContextGenerator gn_ = m_.generate();
        ContextEl ctx_ = gn_.geneWith(f_);
        Classes.forwardAndClear(ctx_);
        user_.setContext(ctx_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Ex");
        ArgumentWrapper a_ = ProcessMethod.calculate(new CustomFoundMethod(new ExecFormattedRootBlock(ex_), new ExecTypeFunction(ex_, ExecClassesUtil.getMethodBodiesById(ex_, new MethodId(MethodAccessKind.STATIC, "exmeth", new CustList<String>())).first()), new Parameters()), user_.getContext(), StackCall.newInstance(InitPhase.NOTHING, ctx_));
        assertEq(1,((NumberStruct) ArgumentListCall.toStr(a_.getValue())).intStruct());
    }
}
