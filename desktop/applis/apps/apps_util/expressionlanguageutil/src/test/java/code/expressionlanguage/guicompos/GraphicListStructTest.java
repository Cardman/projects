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
import code.expressionlanguage.functionid.*;
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
import code.expressionlanguage.utilcompo.*;
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
    @Test
    public void remove() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(0),NullStruct.NULL_VALUE,new StringStruct("0")),st_);
        call(new FctGrListRemove(),null,ctx_,ls_,one(new IntStruct(0)),st_);
        assertEq(0, ((GraphicListStruct)ls_).getGrList().size());
    }
    @Test
    public void clear() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(0),NullStruct.NULL_VALUE,new StringStruct("0")),st_);
        call(new FctGrListClear(),null,ctx_,ls_,null,st_);
        assertEq(0, ((GraphicListStruct)ls_).getGrList().size());
    }
    @Test
    public void getListView() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(0),NullStruct.NULL_VALUE,new StringStruct("0")),st_);
        ArrayStruct arr_ = (ArrayStruct) call(new FctGrListGetListView(), null, ctx_, ls_, null, st_);
        assertEq(1, arr_.getLength());
        assertEq("0", arr_.get(0));
    }
    @Test
    public void indexes1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(0),NullStruct.NULL_VALUE,new StringStruct("0")),st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(1),NullStruct.NULL_VALUE,new StringStruct("1")),st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(2),NullStruct.NULL_VALUE,new StringStruct("2")),st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(3),NullStruct.NULL_VALUE,new StringStruct("3")),st_);
        call(new FctGrListSetSelectedIndexes(stds_.getGuiAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        ArrayStruct arr_ = (ArrayStruct) call(new FctGrListGetSelectedIndexes(), null, ctx_, ls_, null, st_);
        assertEq(0, arr_.getLength());
    }
    @Test
    public void indexes2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(0),NullStruct.NULL_VALUE,new StringStruct("0")),st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(1),NullStruct.NULL_VALUE,new StringStruct("1")),st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(2),NullStruct.NULL_VALUE,new StringStruct("2")),st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(3),NullStruct.NULL_VALUE,new StringStruct("3")),st_);
        ArrayStruct sel_ = new ArrayStruct(2, "");
        sel_.set(0,new IntStruct(1));
        sel_.set(1,new IntStruct(3));
        call(new FctGrListSetSelectedIndexes(stds_.getGuiAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,ls_,one(sel_),st_);
        ArrayStruct arr_ = (ArrayStruct) call(new FctGrListGetSelectedIndexes(), null, ctx_, ls_, null, st_);
        assertEq(2, arr_.getLength());
        assertEq(1, toLong(arr_.get(0)));
        assertEq(3, toLong(arr_.get(1)));
    }
    @Test
    public void indexes3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(0),NullStruct.NULL_VALUE,new StringStruct("0")),st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(1),NullStruct.NULL_VALUE,new StringStruct("1")),st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(2),NullStruct.NULL_VALUE,new StringStruct("2")),st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(3),NullStruct.NULL_VALUE,new StringStruct("3")),st_);
        ArrayStruct sel_ = new ArrayStruct(2, "");
        sel_.set(0,new IntStruct(1));
        sel_.set(1,new IntStruct(3));
        call(new FctGrListSetSelectedIndexes(stds_.getGuiAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,ls_,one(sel_),st_);
        call(new FctGrListClearSelection(stds_.getGuiAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,ls_,null,st_);
        ArrayStruct arr_ = (ArrayStruct) call(new FctGrListGetSelectedIndexes(), null, ctx_, ls_, null, st_);
        assertEq(0, arr_.getLength());
    }
    @Test
    public void addList1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAddSelection(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,((GraphicListStruct)ls_).getGrList().getListeners().size());
    }
    @Test
    public void addList2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAddSelection(),null,ctx_,ls_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((GraphicListStruct)ls_).getGrList().getListeners().size());
    }
    @Test
    public void removeList1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAddSelection(),null,ctx_,ls_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctGrListRemoveSelection(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,((GraphicListStruct)ls_).getGrList().getListeners().size());
    }
    @Test
    public void removeList2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctGrListAddSelection(),null,ctx_,ls_,one(list_),st_);
        call(new FctGrListRemoveSelection(),null,ctx_,ls_,one(list_),st_);
        assertEq(0,((GraphicListStruct)ls_).getGrList().getListeners().size());
    }
    @Test
    public void lists1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctGrListAddSelection(),null,ctx_,ls_,one(list_),st_);
        ArrayStruct a_ = (ArrayStruct) call(new FctGrListGetSelections(), null, ctx_, ls_, null, st_);
        assertEq(1,a_.getLength());
        assertSame(list_,a_.get(0));
    }
    @Test
    public void lists2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        ((GraphicListStruct)ls_).getGrList().addListener(null);
        ArrayStruct a_ = (ArrayStruct) call(new FctGrListGetSelections(), null, ctx_, ls_, null, st_);
        assertEq(0,a_.getLength());
    }
    @Test
    public void toolTipText1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctCompoToolTip1(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctCompoToolTip0(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void toolTipText2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctCompoToolTip1(),null,ctx_,ls_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctCompoToolTip0(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
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
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctCompoSetVisible(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctInputSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctInputIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctInputSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctInputIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void render1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        CdmFactory cdm_ = new CdmFactory(pr_, new MockInterceptor(), new MockAdvGraphicListGenerator(true), new AdvGraphicListGeneratorStruct());
        Options opt_ = new Options();
        ExecutingOptions e_ = new ExecutingOptions(pr_.getThreadFactory().newAtomicBoolean());
        e_.setLightProgramInfos(pr_);
        e_.setListGenerator(cdm_);
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static Fct run(){return $lambda(Sample,,instanceof);}}");
        ContextEl ctx_ = build(opt_, e_,new AnalysisMessages(),new KeyWords(),stds_, files_).getContext();
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        Struct r_ = call(new FctRender(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        ExecFormattedRootBlock form_ = new ExecFormattedRootBlock(ex_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, "run", new StringList());
        Struct lda_ = ArgumentListCall.toStr(RunnableStruct.invoke(new Argument(), form_, (RunnableContextEl) ctx_, new ExecTypeFunction(form_, ExecClassesUtil.getMethodBodiesById(ex_, id_).first()), resSt_, new ArgumentListCall()));
        call(new FctRenderSetPaint(),null,ctx_,r_,one(lda_),st_);
        call(new FctGrListSetRender(),null,ctx_,ls_,one(r_),st_);
        assertSame(r_,call(new FctGrListGetRender(),null,ctx_,ls_,null,st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void render2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        Forwards fwd_ = getForwards(stds_, opt_);
        ContextEl ctx_ = stds_.newContext(opt_, fwd_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        Struct r_ = call(new FctRender(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctGrListSetRender(),null,ctx_,ls_,one(r_),st_);
        assertSame(r_,call(new FctGrListGetRender(),null,ctx_,ls_,null,st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void render3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        Forwards fwd_ = getForwards(stds_, opt_);
        ContextEl ctx_ = stds_.newContext(opt_, fwd_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new DfGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, one(BooleanStruct.of(false)), st_);
        call(new FctGrListSetRender(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctGrListGetRender(),null,ctx_,ls_,null,st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void update() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        Forwards fwd_ = getForwards(stds_, opt_);
        ContextEl ctx_ = stds_.newContext(opt_, fwd_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctGrList(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListUpdateGraphics(),null,ctx_,ls_,one(new IntStruct(2)),st_);
        call(new FctGrListSetVisibleRowCount(),null,ctx_,ls_,one(new IntStruct(2)),st_);
        assertEq(2,toLong(call(new FctGrListGetVisibleRowCount(),null,ctx_,ls_,null,st_)));
    }
}
