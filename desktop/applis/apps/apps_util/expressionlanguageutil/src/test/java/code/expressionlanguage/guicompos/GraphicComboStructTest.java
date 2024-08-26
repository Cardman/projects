package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.AbstractIssuer;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.*;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import code.util.core.StringUtil;
import org.junit.Test;

public final class GraphicComboStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new DfCombo(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctCombo1(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(2,"");
        arr_.set(0,NullStruct.NULL_VALUE);
        arr_.set(1,new StringStruct(""));
        call(new FctCombo1(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,one(arr_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctCombo2(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,two(new IntStruct(-1),NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void getSelectedItem1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct combo_ = call(new FctCombo2(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(-1),arr_), st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctComboGetSelectedItem(),null,ctx_,combo_,null,st_));
    }
    @Test
    public void getSelectedItem2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct combo_ = call(new FctCombo2(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(2),arr_), st_);
        assertEq("2",call(new FctComboGetSelectedItem(),null,ctx_,combo_,null,st_));
    }
    @Test
    public void addItem1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static int run(){var g = new ComboBox();g.addItem(null);return g.getItemCount();}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertEq(0,toLong(combo_));
    }
    @Test
    public void addItem2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static int run(){var g = new ComboBox();g.addItem(\"\");return g.getItemCount();}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertEq(1,toLong(combo_));
    }
    @Test
    public void addItem3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static int run(){var g = new ComboBox();g.addItem(\"0\");g.addItem(\"1\");return g.getSelectedIndex();}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertEq(0,toLong(combo_));
    }
    @Test
    public void addItem4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ComboBox();var s=new StringBuilder();g.addListener((ListSelection)(int a,int b,boolean c:void)->{s.append(\"EVT\"+g.getItemCount());});g.addItem(\"0\");g.addItem(\"1\");return \"\"+s;}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertEq("EVT1",combo_);
    }
    @Test
    public void addItem5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ComboBox();var s=new StringBuilder();g.addListener((ListSelection)(int a,int b,boolean c:void)->{s.append(\"EVT\"+g.getSelectedIndex());});g.addItem(\"0\");g.addItem(\"1\");g.selectItem(1);return \"\"+s;}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertEq("EVT0EVT1",combo_);
    }
    @Test
    public void addItem6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ComboBox();var s=new StringBuilder();g.addItem(\"0\");g.addItem(\"1\");g.addListener((ListSelection)(int a,int b,boolean c:void)->{s.append(\"EVT\"+g.getItemCount());});g.removeItem(1);return \"\"+s;}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertEq("",combo_);
    }
    @Test
    public void addItem7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ComboBox();var s=new StringBuilder();g.addItem(\"0\");g.addItem(\"1\");g.addListener((ListSelection)(int a,int b,boolean c:void)->{s.append(\"EVT\"+g.getSelectedIndex());});g.selectItem(1);g.removeItem(1);return \"\"+s;}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertEq("EVT1EVT0",combo_);
    }
    @Test
    public void addItem8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ComboBox();var s=new StringBuilder();g.addItem(\"0\");g.addItem(\"1\");g.addListener((ListSelection)(int a,int b,boolean c:void)->{s.append(\"EVT\"+g.getItemCount());});g.removeAllItems();s.clear();g.removeAllItems();return \"\"+s;}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertEq("",combo_);
    }
    @Test
    public void addItem9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new ComboBox();var s=new StringBuilder();g.addItem(\"0\");g.addItem(\"1\");g.addListener((ListSelection)(int a,int b,boolean c:void)->{s.append(\"EVT\"+g.getItemCount());});g.removeAllItems();return \"\"+s;}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertEq("EVT0",combo_);
    }
    @Test
    public void addItem10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static int run(){var g = new ComboBox();g.addItem(\"0\");g.setVisibleRowCount(1);g.updateGraphics();return g.getVisibleRowCount();}}");
        Struct combo_ = ctxStr(pr_,files_);
        assertEq(1,toLong(combo_));
    }
    @Test
    public void selectItem() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct combo_ = call(new FctCombo2(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(2),arr_), st_);
        call(new FctComboSelectItem(),null,ctx_,combo_,one(new IntStruct(1)),st_);
        assertEq("1",call(new FctComboGetSelectedItem(),null,ctx_,combo_,null,st_));
        assertEq(1,toLong(call(new FctComboGetSelectedIndex(),null,ctx_,combo_,null,st_)));
    }
    @Test
    public void selectedItems1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct combo_ = call(new FctCombo2(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(2),arr_), st_);
        call(new FctComboSelectItem(),null,ctx_,combo_,one(new IntStruct(1)),st_);
        ArrayStruct out_ = (ArrayStruct) call(new FctComboGetSelectedIndexes(), null, ctx_, combo_, null, st_);
        assertEq(1,out_.getLength());
        assertEq(1,toLong(out_.get(0)));
    }
    @Test
    public void selectedItems2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct combo_ = call(new FctCombo2(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, two(new IntStruct(2),arr_), st_);
        call(new FctComboSelectItem(),null,ctx_,combo_,one(new IntStruct(-1)),st_);
        ArrayStruct out_ = (ArrayStruct) call(new FctComboGetSelectedIndexes(), null, ctx_, combo_, null, st_);
        assertEq(0,out_.getLength());
    }
    @Test
    public void addList1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        call(new FctComboAddListener(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,((GraphicComboStruct)ls_).getGraphicCombo().getSelections().size());
    }
    @Test
    public void addList2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        call(new FctComboAddListener(),null,ctx_,ls_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((GraphicComboStruct)ls_).getGraphicCombo().getSelections().size());
    }
    @Test
    public void removeList1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        call(new FctComboAddListener(),null,ctx_,ls_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctComboRemoveListener(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,((GraphicComboStruct)ls_).getGraphicCombo().getSelections().size());
    }
    @Test
    public void removeList2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctComboAddListener(),null,ctx_,ls_,one(list_),st_);
        call(new FctComboRemoveListener(),null,ctx_,ls_,one(list_),st_);
        assertEq(0,((GraphicComboStruct)ls_).getGraphicCombo().getSelections().size());
    }
    @Test
    public void lists1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctComboAddListener(),null,ctx_,ls_,one(list_),st_);
        ArrayStruct a_ = (ArrayStruct) call(new FctComboGetListeners(), null, ctx_, ls_, null, st_);
        assertEq(1,a_.getLength());
        assertSame(list_,a_.get(0));
    }
    @Test
    public void lists2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        ((GraphicComboStruct)ls_).getGraphicCombo().addListener(new MockListSampleSelection());
        ArrayStruct a_ = (ArrayStruct) call(new FctComboGetListeners(), null, ctx_, ls_, null, st_);
        assertEq(0,a_.getLength());
    }
    @Test
    public void toolTipText1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        call(new FctCompoToolTip1(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctCompoToolTip0(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void toolTipText2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        call(new FctCompoToolTip1(),null,ctx_,ls_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctCompoToolTip0(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        call(new FctCompoSetVisible(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsVisible(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void visible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        call(new FctCompoSetVisible(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        call(new FctInputSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctInputIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctCombo0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        call(new FctInputSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctInputIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void remove1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(2,"");
        arr_.set(0,NullStruct.NULL_VALUE);
        arr_.set(1,new StringStruct(""));
        Struct combo_ = call(new FctCombo1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctComboRemoveItem(),null,ctx_,combo_,one(new IntStruct(0)),st_);
        assertEq(0,toLong(call(new FctComboGetItemCount(),null,ctx_,combo_,null,st_)));
    }
    @Test
    public void remove2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(2,"");
        arr_.set(0,NullStruct.NULL_VALUE);
        arr_.set(1,new StringStruct(""));
        Struct combo_ = call(new FctCombo1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctComboRemoveAllItems(),null,ctx_,combo_,null,st_);
        assertEq(0,toLong(call(new FctComboGetItemCount(),null,ctx_,combo_,null,st_)));
    }
    private Struct ctxStr(MockProgramInfos _pr, StringMap<String> _p) {
        ContextEl ctx_ = ctx(_pr,_p);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        ExecFormattedRootBlock form_ = new ExecFormattedRootBlock(ex_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, "run", new StringList());
        return ArgumentListCall.toStr(EventStruct.invoke(NullStruct.NULL_VALUE, ctx_, new ExecTypeFunction(form_, ExecClassesUtil.getMethodBodiesById(ex_, id_).first()), resSt_, new ArgumentListCall()));
    }
    private ContextEl ctx(MockProgramInfos _p) {
        return ctx(_p,new StringMap<String>());
    }
    private ContextEl ctx(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = exOpt(_p);
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(), StringUtil.EN);
        Options opt_ = new Options();
        return buildMock(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files).getContext();
    }
    public static ResultContext buildMock(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
//        s_.addEntry("0",_definedLgNames.getGuiAliases().renderInterface(_definedKw, _definedLgNames.getContent()));
//        s_.addEntry("1",_definedLgNames.getGuiAliases().renderDefault(_definedKw, _definedLgNames.getContent()));
        s_.addEntry("0",_definedLgNames.getGuiAliases().listSelection(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass component_ = new StandardClass(_definedLgNames.getGuiAliases().getAliasComponent(), fields_, constructors_, methods_, _definedLgNames.getContent().getCoreNames().getAliasObject(), StdClassModifier.ABSTRACT);
        component_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());
//        StringList params_ = new StringList();
//        StandardMethod method_ = new StandardMethod(_definedLgNames.getGuiAliases().getAliasComponentGetWidth(), params_, _definedLgNames.getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctCompoGetWidth());
//        StandardNamedFunction.addFct(methods_, method_);
        StandardType.addType(_definedLgNames.getContent().getStandards(), _definedLgNames.getGuiAliases().getAliasComponent(), component_);

        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        StandardClass input_ = new StandardClass(_definedLgNames.getGuiAliases().getAliasInput(), fields_, constructors_, methods_, _definedLgNames.getGuiAliases().getAliasComponent(), StdClassModifier.ABSTRACT);
        input_.addSuperStdTypes(component_);
        input_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());
        StandardType.addType(_definedLgNames.getContent().getStandards(), _definedLgNames.getGuiAliases().getAliasInput(), input_);

//        _definedLgNames.getGuiAliases().color(_definedLgNames.getContent());
//        _definedLgNames.getGuiAliases().image(_definedLgNames.getContent(), _definedLgNames.getGuiExecutingBlocks());
//        _definedLgNames.getGuiAliases().font(_definedLgNames.getContent(), _definedLgNames.getGuiExecutingBlocks());
        _definedLgNames.getGuiAliases().buildCombo(_definedLgNames.getContent(),_definedLgNames.getExecContent().getCustAliases(),_definedLgNames.getGuiExecutingBlocks(),component_,input_);
        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMock(_exec, _definedLgNames, _files, page_, forwards_);
        LgNamesGui stds_ = (LgNamesGui) res_.getContext().getStandards();
//        stds_.getGuiExecutingBlocks().cellRender(stds_.getGuiAliases(), stds_.getContent(),res_.getContext().getClasses());
        stds_.getGuiExecutingBlocks().listSelection(stds_.getGuiAliases(), stds_.getContent(),res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }

    public static LgNamesGui newLgNamesGuiSampleGr(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(exOpt(_light));
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),StringUtil.EN);
        return stds_;
    }
}
