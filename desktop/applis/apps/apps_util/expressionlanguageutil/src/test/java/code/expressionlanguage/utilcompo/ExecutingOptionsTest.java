package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.*;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class ExecutingOptionsTest extends EquallableElUtUtil {
    @Test
    public void argsSet() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        ArrayStruct arg_ = new ArrayStruct(1, "");
        arg_.set(0,new StringStruct("arg"));
        call(new FctArgsSet(),null,ctx_,null,one(arg_),st_);
        StringList a_ = ((RunnableContextEl) ctx_).getArgs();
        assertEq(1,a_.size());
        assertEq("arg",a_.get(0));
    }
    @Test
    public void argsGet() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        ArrayStruct arg_ = new ArrayStruct(1, "");
        arg_.set(0,new StringStruct("arg"));
        call(new FctArgsSet(),null,ctx_,null,one(arg_),st_);
        ArrayStruct a_ = (ArrayStruct) call(new FctArgsGet(),null,ctx_,null,null,st_);
        assertEq(1,a_.getLength());
        assertEq("arg",a_.get(0));
    }
    @Test
    public void coverage() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(dbs(0.75)), new MockFileSet(2, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        opt_.setCovering(true);
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        RunnableStruct.setupThread((RunnableContextEl) ctx_);
        Struct arr_ = call(new FctCoverage(""), null, ctx_, null, null, st_);
        assertEq(3,((ArrayStruct)arr_).getLength());
        assertEq("src/sample.txt.html", call(new FctEntryTextName(),null,ctx_,((ArrayStruct) arr_).get(0),null,st_));
        assertEq("<html><head><meta content=\"text/html; charset=UTF-8\" http-equiv=\"content-type\"/><link href=\"../css/style.css\" rel=\"stylesheet\" type=\"text/css\"/></head><body><pre><span class=\"t\">public class <a name=\"m13\">pkg.Sample</a>{}</span></pre></body></html>", call(new FctEntryTextValue(),null,ctx_,((ArrayStruct) arr_).get(0),null,st_));
        assertEq(6,toLong(call(new FctEntryTextTime0(),null,ctx_,((ArrayStruct) arr_).get(0),null,st_)));
    }
    @Test
    public void interrupt1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctInterrupt(),null,ctx_,null,null,st_);
        assertTrue(stds_.getExecutingOptions().getInterrupt().get());
    }
    @Test
    public void interrupt2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        AbsPlainButton b_ = pr_.getCompoFactory().newPlainButton("");
        b_.setEnabled(false);
        stds_.getGuiExecutingBlocks().setStop(b_);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctInterrupt(),null,ctx_,null,null,st_);
        assertTrue(stds_.getExecutingOptions().getInterrupt().get());
        assertTrue(b_.isEnabled());
    }
    @Test
    public void invokeLater1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctCompoInvokeLater(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void invokeLater2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctCompoInvokeLater(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void invokeLater3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        ((RunnableContextEl)ctx_).getExecutingOptions().setInvokeDirect(true);
        StackCall st_ = stack(ctx_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctCompoInvokeLater(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,one(list_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void invokeLater4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        ((RunnableContextEl)ctx_).getExecutingOptions().setInvokeDirect(false);
        StackCall st_ = stack(ctx_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctCompoInvokeLater(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()),null,ctx_,null,one(list_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
}
