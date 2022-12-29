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
import code.gui.events.*;
import code.gui.events.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class GraphicListStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctGrList(stds_.getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,one(BooleanStruct.of(true)),st_);
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
        call(new FctGrList(stds_.getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,one(BooleanStruct.of(false)),st_);
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
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAdd0(stds_.getGuiAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,ls_,two(new IntStruct(0),new StringStruct("0")),st_);
        assertEq(1, ((GraphicListStruct)ls_).getGrList().size());
        assertEq("0", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(0)).getInstance());
    }
    @Test
    public void add2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(0),NullStruct.NULL_VALUE,new StringStruct("0")),st_);
        assertEq(1, ((GraphicListStruct)ls_).getGrList().size());
        assertEq("0", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(0)).getInstance());
    }
    @Test
    public void set1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAdd0(stds_.getGuiAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,ls_,two(new IntStruct(0),new StringStruct("0")),st_);
        call(new FctGrListSet0(stds_.getGuiAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,ls_,two(new IntStruct(-1),new StringStruct("2")),st_);
        call(new FctGrListSet0(stds_.getGuiAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,ls_,two(new IntStruct(0),new StringStruct("1")),st_);
        assertEq(1, ((GraphicListStruct)ls_).getGrList().size());
        assertEq("1", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(0)).getInstance());
    }
    @Test
    public void set2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(0),NullStruct.NULL_VALUE,new StringStruct("0")),st_);
        call(new FctGrListSet1(),null,ctx_,ls_,three(new IntStruct(-1),NullStruct.NULL_VALUE,new StringStruct("2")),st_);
        call(new FctGrListSet1(),null,ctx_,ls_,three(new IntStruct(0),NullStruct.NULL_VALUE,new StringStruct("1")),st_);
        assertEq(1, ((GraphicListStruct)ls_).getGrList().size());
        assertEq("1", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(0)).getInstance());
    }
}
