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

public final class TextAreaStructTest extends EquallableElUtUtil {

    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        call(new FctTextArea0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
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
        call(new FctTextArea1(stds_.getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,one(new StringStruct("")),st_);
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
        call(new FctTextArea2(stds_.getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,two(new IntStruct(5),new IntStruct(7)),st_);
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
        call(new FctTextArea3(stds_.getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,three(new StringStruct(""),new IntStruct(5),new IntStruct(7)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void text() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTextAreaSetText(),null,ctx_,r_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctTextAreaGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void toolTipText1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
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
        Struct ls_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
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
        Struct ls_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
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
        Struct ls_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
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
        Struct ls_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
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
        Struct ls_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctInputSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctInputIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void append() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("_")), st_);
        call(new FctTextAreaAppend(),null,ctx_,r_,one(new StringStruct("next")),st_);
        assertEq("_next",call(new FctTextAreaGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void insert() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("_")), st_);
        call(new FctTextAreaInsert(),null,ctx_,r_,two(new StringStruct("previous"),new IntStruct(0)),st_);
        assertEq("previous_",call(new FctTextAreaGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void replaceSelection1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("_replace_")), st_);
        call(new FctTextAreaSelect(),null,ctx_,r_,two(new IntStruct(1),new IntStruct(8)),st_);
        assertEq("replace",call(new FctTextAreaGetSelectedText(),null,ctx_,r_,null,st_));
        call(new FctTextAreaReplaceSelection(),null,ctx_,r_,one(new StringStruct("change")),st_);
        assertEq("_change_",call(new FctTextAreaGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void replaceSelection2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("_replace_")), st_);
        call(new FctTextAreaSetSelectionStart(),null,ctx_,r_,one(new IntStruct(1)),st_);
        call(new FctTextAreaSetSelectionEnd(),null,ctx_,r_,one(new IntStruct(8)),st_);
        call(new FctTextAreaSelect(),null,ctx_,r_,two(new IntStruct(1),new IntStruct(8)),st_);
        call(new FctTextAreaReplaceSelection(),null,ctx_,r_,one(new StringStruct("change")),st_);
        assertEq("_change_",call(new FctTextAreaGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void replaceAll() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("_replace_")), st_);
        call(new FctTextAreaSelectAll(),null,ctx_,r_,null,st_);
        call(new FctTextAreaReplaceSelection(),null,ctx_,r_,one(new StringStruct("change")),st_);
        assertEq("change",call(new FctTextAreaGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void replaceRange() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("_replace_")), st_);
        call(new FctTextAreaReplaceRange(),null,ctx_,r_,three(new StringStruct("change"),new IntStruct(1),new IntStruct(8)),st_);
        assertEq("_change_",call(new FctTextAreaGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void tabSize() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextArea1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTextAreaSetTabSize(),null,ctx_,r_,one(new IntStruct(8)),st_);
        assertEq(8,toLong(call(new FctTextAreaGetTabSize(),null,ctx_,r_,null,st_)));
    }
}
