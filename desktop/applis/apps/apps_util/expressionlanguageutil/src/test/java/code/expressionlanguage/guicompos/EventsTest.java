package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.fwd.opers.AnaLambdaCommonContent;
import code.expressionlanguage.fwd.opers.AnaLambdaFieldContent;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaFieldContent;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.expressionlanguage.utilimpl.*;
import code.gui.*;
import code.gui.events.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class EventsTest extends EquallableElUtUtil {
    @Test
    public void evt1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctWindowEvent(stds_.getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void evt2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctActionEvent(stds_.getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,four(BooleanStruct.of(true),BooleanStruct.of(true),BooleanStruct.of(true),new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
    }
    @Test
    public void act1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct e_ = call(new FctActionEvent(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(BooleanStruct.of(false), BooleanStruct.of(false), BooleanStruct.of(false), new StringStruct("")), st_);
        assertFalse(call(new FctActionEventIsAlt(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctActionEventIsCtrl(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctActionEventIsShift(),null,ctx_,e_,null,st_));
        assertEq("",call(new FctActionEventCommand(),null,ctx_,e_,null,st_));
    }
    @Test
    public void act2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct e_ = call(new FctActionEvent(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, four(BooleanStruct.of(true), BooleanStruct.of(true), BooleanStruct.of(true), new StringStruct("")), st_);
        assertTrue(call(new FctActionEventIsAlt(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctActionEventIsCtrl(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctActionEventIsShift(),null,ctx_,e_,null,st_));
        assertEq("",call(new FctActionEventCommand(),null,ctx_,e_,null,st_));
    }
    @Test
    public void key1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct e_ = call(new FctKeyEvent(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, five(BooleanStruct.of(false), BooleanStruct.of(false), BooleanStruct.of(false), new CharStruct((char) 0), new CharStruct((char) 0)), st_);
        assertFalse(call(new FctKeyEventIsAlt(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctKeyEventIsCtrl(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctKeyEventIsShift(),null,ctx_,e_,null,st_));
        assertEq(0,toLong(call(new FctKeyEventCode(),null,ctx_,e_,null,st_)));
        assertEq(0,toLong(call(new FctKeyEventChar(),null,ctx_,e_,null,st_)));
    }
    @Test
    public void key2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct e_ = call(new FctKeyEvent(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, five(BooleanStruct.of(true), BooleanStruct.of(true), BooleanStruct.of(true), new CharStruct((char) 0), new CharStruct((char) 0)), st_);
        assertTrue(call(new FctKeyEventIsAlt(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctKeyEventIsCtrl(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctKeyEventIsShift(),null,ctx_,e_,null,st_));
        assertEq(0,toLong(call(new FctKeyEventCode(),null,ctx_,e_,null,st_)));
        assertEq(0,toLong(call(new FctKeyEventChar(),null,ctx_,e_,null,st_)));
    }
    @Test
    public void mouse1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct e_ = call(new FctMouseEvent(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, nine(new IntStruct(0),new IntStruct(0),BooleanStruct.of(false), BooleanStruct.of(false), BooleanStruct.of(false),BooleanStruct.of(false), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(0)), st_);
        assertFalse(call(new FctMouseEventIsAlt(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctMouseEventIsCtrl(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctMouseEventIsShift(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctMouseEventIsLeft(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctMouseEventIsMiddle(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctMouseEventIsRight(),null,ctx_,e_,null,st_));
        assertEq(0,toLong(call(new FctMouseEventGetFirst(),null,ctx_,e_,null,st_)));
        assertEq(0,toLong(call(new FctMouseEventGetSecond(),null,ctx_,e_,null,st_)));
        assertEq(0,toLong(call(new FctMouseEventGetClicks(),null,ctx_,e_,null,st_)));
    }
    @Test
    public void mouse2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct e_ = call(new FctMouseEvent(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, nine(new IntStruct(0),new IntStruct(0),BooleanStruct.of(true), BooleanStruct.of(true), BooleanStruct.of(true),BooleanStruct.of(true), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(0)), st_);
        assertTrue(call(new FctMouseEventIsAlt(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctMouseEventIsCtrl(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctMouseEventIsShift(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctMouseEventIsLeft(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctMouseEventIsMiddle(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctMouseEventIsRight(),null,ctx_,e_,null,st_));
        assertEq(0,toLong(call(new FctMouseEventGetFirst(),null,ctx_,e_,null,st_)));
        assertEq(0,toLong(call(new FctMouseEventGetSecond(),null,ctx_,e_,null,st_)));
        assertEq(0,toLong(call(new FctMouseEventGetClicks(),null,ctx_,e_,null,st_)));
    }
    @Test
    public void wheel1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct e_ = call(new FctWheelEvent(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, ten(new IntStruct(0),new IntStruct(0),BooleanStruct.of(false), BooleanStruct.of(false), BooleanStruct.of(false),BooleanStruct.of(false), BooleanStruct.of(false), BooleanStruct.of(false), new IntStruct(0), new IntStruct(0)), st_);
        assertFalse(call(new FctMouseEventIsAlt(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctMouseEventIsCtrl(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctMouseEventIsShift(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctMouseEventIsLeft(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctMouseEventIsMiddle(),null,ctx_,e_,null,st_));
        assertFalse(call(new FctMouseEventIsRight(),null,ctx_,e_,null,st_));
        assertEq(0,toLong(call(new FctMouseEventGetFirst(),null,ctx_,e_,null,st_)));
        assertEq(0,toLong(call(new FctMouseEventGetSecond(),null,ctx_,e_,null,st_)));
        assertEq(0,toLong(call(new FctMouseEventGetClicks(),null,ctx_,e_,null,st_)));
        assertEq(0,toLong(call(new FctWheelRotatedClicks(),null,ctx_,e_,null,st_)));
    }
    @Test
    public void wheel2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct e_ = call(new FctWheelEvent(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, ten(new IntStruct(0),new IntStruct(0),BooleanStruct.of(true), BooleanStruct.of(true), BooleanStruct.of(true),BooleanStruct.of(true), BooleanStruct.of(true), BooleanStruct.of(true), new IntStruct(0), new IntStruct(0)), st_);
        assertTrue(call(new FctMouseEventIsAlt(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctMouseEventIsCtrl(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctMouseEventIsShift(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctMouseEventIsLeft(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctMouseEventIsMiddle(),null,ctx_,e_,null,st_));
        assertTrue(call(new FctMouseEventIsRight(),null,ctx_,e_,null,st_));
        assertEq(0,toLong(call(new FctMouseEventGetFirst(),null,ctx_,e_,null,st_)));
        assertEq(0,toLong(call(new FctMouseEventGetSecond(),null,ctx_,e_,null,st_)));
        assertEq(0,toLong(call(new FctMouseEventGetClicks(),null,ctx_,e_,null,st_)));
        assertEq(0,toLong(call(new FctWheelRotatedClicks(),null,ctx_,e_,null,st_)));
    }
}
