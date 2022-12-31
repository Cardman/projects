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

public final class PopupStructTest extends EquallableElUtUtil {

    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct p_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        Struct menuItem_ = call(new FctTextLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuShow(),null,ctx_,p_,three(NullStruct.NULL_VALUE,new IntStruct(1),new IntStruct(1)),st_);
        call(new FctPopupMenuShow(),null,ctx_,p_,three(menuItem_,new IntStruct(1),new IntStruct(1)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void count1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        assertEq(0,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,ls_,null,st_)));
    }

    @Test
    public void count2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,ls_,null,st_)));
        assertSame(NullStruct.NULL_VALUE,call(new FctPopupMenuGetMenu(),null,ctx_,ls_,one(new IntStruct(0)),st_));
    }

    @Test
    public void count3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(1,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,menu_,null,st_)));
        assertSame(menuItem_,call(new FctPopupMenuGetMenu(),null,ctx_,menu_,one(new IntStruct(0)),st_));
    }

    @Test
    public void count4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItemCheck1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(1,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(1,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuRemoveMenu(),null,ctx_,menu_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem2_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuRemoveMenu(),null,ctx_,menu_,one(menuItem2_),st_);
        assertEq(1,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuRemoveMenu(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(0,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItemCheck1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuRemoveMenu(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(0,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem2_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem2_),st_);
        assertEq(2,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem2_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem2_),st_);
        assertEq(2,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,menu_,null,st_)));
    }
    @Test
    public void count12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        assertEq(0,toLong(call(new FctPopupMenuNbComp(),null,ctx_,ls_,null,st_)));
    }

    @Test
    public void count13() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAdd(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,toLong(call(new FctPopupMenuNbComp(),null,ctx_,ls_,null,st_)));
        assertSame(NullStruct.NULL_VALUE,call(new FctPopupMenuGetComp(),null,ctx_,ls_,one(new IntStruct(0)),st_));
    }

    @Test
    public void count14() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctTextLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(1,toLong(call(new FctPopupMenuNbComp(),null,ctx_,menu_,null,st_)));
        assertSame(menuItem_,call(new FctPopupMenuGetComp(),null,ctx_,menu_,one(new IntStruct(0)),st_));
    }

    @Test
    public void count15() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctTextLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(1,toLong(call(new FctPopupMenuNbComp(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count16() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctTextLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuRemoveComp(),null,ctx_,menu_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,toLong(call(new FctPopupMenuNbComp(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count17() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctTextLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem2_ = call(new FctTextLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuRemoveComp(),null,ctx_,menu_,one(menuItem2_),st_);
        assertEq(1,toLong(call(new FctPopupMenuNbComp(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count18() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctTextLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuRemoveComp(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(0,toLong(call(new FctPopupMenuNbComp(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count19() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctTextLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem2_ = call(new FctTextLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuAdd(),null,ctx_,menu_,one(menuItem2_),st_);
        assertEq(2,toLong(call(new FctPopupMenuNbComp(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count20() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctTextLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem2_ = call(new FctTextLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuRemoveComp(),null,ctx_,menu_,one(menuItem2_),st_);
        assertEq(1,toLong(call(new FctPopupMenuNbComp(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count21() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuRemoveMenu(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(0,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count22() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem2_ = call(new FctMenu1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menuItem2_,one(menuItem_),st_);
        call(new FctPopupMenuAddMenu(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(0,toLong(call(new FctPopupMenuNbMenu(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count23() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctTextLabel0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctPopupMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctPopupMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(1,toLong(call(new FctPopupMenuNbComp(),null,ctx_,menu_,null,st_)));
    }
    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctCompoSetVisible(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsVisible(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void visible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctPopupMenu(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctCompoSetVisible(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,ls_,null,st_));
    }
}
