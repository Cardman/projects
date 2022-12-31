package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.*;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.events.*;
import code.gui.events.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class MenusTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctMenuItem0(stds_.getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
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
        call(new FctMenuItem1(stds_.getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,one(new StringStruct("")),st_);
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
        call(new FctMenuItemCheck0(stds_.getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
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
        call(new FctMenuItemCheck0(stds_.getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,one(new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct m_ = call(new FctMenu0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctMenuAddSeparator(),null,ctx_,m_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctMenu1(stds_.getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,one(new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctMenuBar(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void text1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctAbsMenuItemAddActionListener(),null,ctx_,r_,one(NullStruct.NULL_VALUE),st_);
        call(new FctAbsMenuItemAddActionListener(),null,ctx_,r_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctAbsMenuSetText(),null,ctx_,r_,one(NullStruct.NULL_VALUE),st_);
        call(new FctAbsMenuGetText(),null,ctx_,r_,null,st_);
        call(new FctAbsMenuSetText(),null,ctx_,r_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctAbsMenuGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void text2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctMenuItemCheck0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctAbsMenuItemAddActionListener(),null,ctx_,r_,one(NullStruct.NULL_VALUE),st_);
        call(new FctAbsMenuItemAddActionListener(),null,ctx_,r_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctAbsMenuSetText(),null,ctx_,r_,one(NullStruct.NULL_VALUE),st_);
        call(new FctAbsMenuGetText(),null,ctx_,r_,null,st_);
        call(new FctAbsMenuSetText(),null,ctx_,r_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctAbsMenuGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void enabled1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetDeepEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetDeepEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItemCheck1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItemCheck1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void selected1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItemCheck1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuItemCheckSetSelected(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctMenuItemCheckIsSelected(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void selected2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItemCheck1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuItemCheckSetSelected(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctMenuItemCheckIsSelected(),null,ctx_,ls_,null,st_));
    }

    @Test
    public void count1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertEq(0,toLong(call(new FctMenuNb(),null,ctx_,ls_,null,st_)));
    }

    @Test
    public void count2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,toLong(call(new FctMenuNb(),null,ctx_,ls_,null,st_)));
        assertSame(NullStruct.NULL_VALUE,call(new FctMenuGetMenu(),null,ctx_,ls_,one(new IntStruct(0)),st_));
    }

    @Test
    public void count3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
        assertSame(menuItem_,call(new FctMenuGetMenu(),null,ctx_,menu_,one(new IntStruct(0)),st_));
        assertSame(menu_,call(new FctAbsMenuGetParentMenu(),null,ctx_,menuItem_,null,st_));
    }

    @Test
    public void count4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItemCheck1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctMenuRemove(),null,ctx_,menu_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem2_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctMenuRemove(),null,ctx_,menu_,one(menuItem2_),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctMenuRemove(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(0,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItemCheck1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctMenuRemove(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(0,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
        assertSame(NullStruct.NULL_VALUE,call(new FctMenuBarGet(),null,ctx_,bar_,one(new IntStruct(0)),st_));
    }

    @Test
    public void count11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu_),st_);
        assertEq(1,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
        assertSame(menu_,call(new FctMenuBarGet(),null,ctx_,bar_,one(new IntStruct(0)),st_));
    }

    @Test
    public void count12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu_),st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu_),st_);
        assertEq(1,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
        assertSame(menu_,call(new FctMenuBarGet(),null,ctx_,bar_,one(new IntStruct(0)),st_));
    }

    @Test
    public void count13() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu_),st_);
        call(new FctMenuBarRemove(),null,ctx_,bar_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
    }

    @Test
    public void count14() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem2_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menuItem_),st_);
        call(new FctMenuBarRemove(),null,ctx_,bar_,one(menuItem2_),st_);
        assertEq(1,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
    }

    @Test
    public void count15() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu_),st_);
        call(new FctMenuBarRemove(),null,ctx_,bar_,one(menu_),st_);
        assertEq(0,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
    }

    @Test
    public void count16() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu2_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menu2_),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count17() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu2_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menu2_),st_);
        call(new FctMenuRemove(),null,ctx_,menu_,one(menu2_),st_);
        assertEq(0,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count18() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu2_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu_),st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu2_),st_);
        assertEq(2,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
    }
}
