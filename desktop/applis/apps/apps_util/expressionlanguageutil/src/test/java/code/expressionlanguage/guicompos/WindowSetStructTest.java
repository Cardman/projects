package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.fwd.Forwards;
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

public final class WindowSetStructTest extends EquallableElUtUtil {

    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new DfWindowSet(stds_.getCustAliases()),null,ctx_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctWindowSet(stds_.getCustAliases()),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void all1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        call(new FctWindowSetAll(stds_.getCustAliases()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void all2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctWindowSetAll(stds_.getCustAliases()),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void add1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        Struct all_ = call(new FctWindowSetAll(stds_.getCustAliases()), null, ctx_, null, null, st_);
        st_.getInitializingTypeInfos().getSensibleFields().add(all_);
        call(new FctWindowSetAdd(),null,ctx_,all_,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void add2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSetAll(stds_.getCustAliases()), null, ctx_, null, null, st_);
        call(new FctWindowSetAdd(),null,ctx_,all_,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void add3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSetAll(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowSetAdd(),null,ctx_,all_,one(d_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
    }
    @Test
    public void add4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSetAll(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowSetAdd(),null,ctx_,all_,one(d_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
    }
    @Test
    public void add5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSet(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertFalse(call(new FctWindowSetContains(),null,ctx_,all_,one(d_),st_));
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowSetAdd(),null,ctx_,all_,one(d_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        assertTrue(call(new FctWindowSetContains(),null,ctx_,all_,one(d_),st_));
    }
    @Test
    public void add6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSet(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertFalse(call(new FctWindowSetContains(),null,ctx_,all_,one(d_),st_));
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowSetAdd(),null,ctx_,all_,one(d_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        assertTrue(call(new FctWindowSetContains(),null,ctx_,all_,one(d_),st_));
    }
    @Test
    public void remove1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSet(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertFalse(call(new FctWindowSetContains(),null,ctx_,all_,one(d_),st_));
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowSetAdd(),null,ctx_,all_,one(d_),st_);
        call(new FctWindowSetRemove(),null,ctx_,all_,one(d_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        assertFalse(call(new FctWindowSetContains(),null,ctx_,all_,one(d_),st_));
    }
    @Test
    public void remove2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSet(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertFalse(call(new FctWindowSetContains(),null,ctx_,all_,one(d_),st_));
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowSetAdd(),null,ctx_,all_,one(d_),st_);
        call(new FctWindowSetRemove(),null,ctx_,all_,one(d_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        assertFalse(call(new FctWindowSetContains(),null,ctx_,all_,one(d_),st_));
    }
    @Test
    public void remove3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSetAll(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowSetAdd(),null,ctx_,all_,one(d_),st_);
        call(new FctWindowSetRemove(),null,ctx_,all_,one(d_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
    }
    @Test
    public void remove4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSetAll(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowSetAdd(),null,ctx_,all_,one(d_),st_);
        call(new FctWindowSetRemove(),null,ctx_,all_,one(d_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
    }
    @Test
    public void remove5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSetAll(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowSetAdd(),null,ctx_,all_,one(d_),st_);
        call(new FctWindowSetRemove(),null,ctx_,all_,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
    }
    @Test
    public void remove6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSetAll(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowSetAdd(),null,ctx_,all_,one(d_),st_);
        call(new FctWindowSetRemove(),null,ctx_,all_,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
    }
    @Test
    public void remove7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        Struct all_ = call(new FctWindowSetAll(stds_.getCustAliases()), null, ctx_, null, null, st_);
        st_.getInitializingTypeInfos().getSensibleFields().add(all_);
        call(new FctWindowSetRemove(),null,ctx_,all_,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void remove8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSet(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowSetAdd(),null,ctx_,all_,one(d_),st_);
        call(new FctWindowSetRemove(),null,ctx_,all_,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
    }
    @Test
    public void remove9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSet(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertEq(0,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowSetAdd(),null,ctx_,all_,one(d_),st_);
        call(new FctWindowSetRemove(),null,ctx_,all_,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq(1,((ArrayStruct)call(new FctWindowSetArray(),null,ctx_,all_,null,st_)).getLength());
        call(new FctWindowCloseAll(),null,ctx_,null,null,st_);
    }
    @Test
    public void add7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSet(stds_.getCustAliases()), null, ctx_, null, null, st_);
        call(new FctWindowSetAdd(),null,ctx_,all_,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void contains() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct all_ = call(new FctWindowSet(stds_.getCustAliases()), null, ctx_, null, null, st_);
        assertFalse(call(new FctWindowSetContains(),null,ctx_,all_,one(NullStruct.NULL_VALUE),st_));
    }
}
