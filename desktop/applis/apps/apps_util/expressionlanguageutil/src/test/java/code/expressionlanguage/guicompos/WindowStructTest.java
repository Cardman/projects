package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.gui.events.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class WindowStructTest extends EquallableElUtUtil {

    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new DfDialog(stds_.getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,st_);
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
        call(new FctDialog(stds_.getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new DfFrame(stds_.getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctFrame(stds_.getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void bar1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowSetMenuBar(),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        Struct bar_ = call(new FctMenuBar(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowSetMenuBar(),null,ctx_,d_,one(bar_),st_);
        assertSame(bar_,call(new FctWindowGetMenuBar(), null,ctx_,d_,null,st_));
    }

    @Test
    public void bar2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowSetMenuBar(),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        Struct bar_ = call(new FctMenuBar(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowSetMenuBar(),null,ctx_,d_,one(bar_),st_);
        assertSame(bar_,call(new FctWindowGetMenuBar(), null,ctx_,d_,null,st_));
    }
    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowSetVisible(),null,ctx_,d_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctWindowIsVisible(),null,ctx_,d_,null,st_));
    }
    @Test
    public void visible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowSetVisible(),null,ctx_,d_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctWindowIsVisible(),null,ctx_,d_,null,st_));
    }

    @Test
    public void visible3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowSetVisible(),null,ctx_,d_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctWindowIsVisible(),null,ctx_,d_,null,st_));
    }
    @Test
    public void visible4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowSetVisible(),null,ctx_,d_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctWindowIsVisible(),null,ctx_,d_,null,st_));
    }
    @Test
    public void pack1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowPack(),null,ctx_,d_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void pack2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowPack(),null,ctx_,d_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void location() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        Struct w_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        Struct ls_ = call(new FctTextLabel1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctWindowRelative(),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        call(new FctWindowRelative(),null,ctx_,w_,one(NullStruct.NULL_VALUE),st_);
        call(new FctWindowRelative(),null,ctx_,d_,one(w_),st_);
        call(new FctWindowRelative(),null,ctx_,w_,one(d_),st_);
        call(new FctWindowRelative(),null,ctx_,d_,one(ls_),st_);
        call(new FctWindowRelative(),null,ctx_,w_,one(ls_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void pane1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowContentPane(),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        Struct bar_ = call(new FctPanel(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, null, st_);
        call(new FctWindowContentPane(),null,ctx_,d_,one(bar_),st_);
        call(new FctWindowDispose(),null,ctx_,d_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }

    @Test
    public void pane2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowContentPane(),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        Struct bar_ = call(new FctPanel(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, null, st_);
        call(new FctWindowContentPane(),null,ctx_,d_,one(bar_),st_);
        call(new FctWindowDispose(),null,ctx_,d_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void addList1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((ArrayStruct)call(new FctWindowGetList(""),null,ctx_,d_,null,st_)).getLength());
    }
    @Test
    public void addList2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((ArrayStruct)call(new FctWindowGetList(""),null,ctx_,d_,null,st_)).getLength());
    }
    @Test
    public void removeList1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        Struct l_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(l_),st_);
        call(new FctWindowRemoveList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(l_),st_);
        assertEq(0,((ArrayStruct)call(new FctWindowGetList(""),null,ctx_,d_,null,st_)).getLength());
    }
    @Test
    public void removeList2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        Struct l_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(l_),st_);
        call(new FctWindowRemoveList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(l_),st_);
        assertEq(0,((ArrayStruct)call(new FctWindowGetList(""),null,ctx_,d_,null,st_)).getLength());
    }
    @Test
    public void removeList3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        Struct l_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        Struct l1_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(l_),st_);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(l1_),st_);
        call(new FctWindowRemoveList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(l_),st_);
        assertEq(1,((ArrayStruct)call(new FctWindowGetList(""),null,ctx_,d_,null,st_)).getLength());
    }
    @Test
    public void removeList4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        Struct l_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        Struct l1_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(l_),st_);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(l1_),st_);
        call(new FctWindowRemoveList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(l_),st_);
        assertEq(1,((ArrayStruct)call(new FctWindowGetList(""),null,ctx_,d_,null,st_)).getLength());
    }
    @Test
    public void removeList5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctFrame(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        Struct l_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        ((FrameStruct)d_).addWindowListener((AbsWindowListener)null);
        call(new FctWindowAddList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(l_),st_);
        call(new FctWindowRemoveList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(NullStruct.NULL_VALUE),st_);
        call(new FctWindowRemoveList(stds_.getGuiExecutingBlocks()),null,ctx_,d_,one(l_),st_);
        assertEq(0,((ArrayStruct)call(new FctWindowGetList(""),null,ctx_,d_,null,st_)).getLength());
    }
    @Test
    public void mult1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctDialogSetModal(),null,ctx_,d_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctDialogIsModal(),null,ctx_,d_,null,st_));
    }
    @Test
    public void mult2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct d_ = call(new FctDialog(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctDialogSetModal(),null,ctx_,d_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctDialogIsModal(),null,ctx_,d_,null,st_));
    }
}
