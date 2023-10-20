package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.gui.*;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class GraphicListStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        call(getCaller(ctx_),null,ctx_,null,one(BooleanStruct.of(true)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init2() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        call(getCaller(ctx_),null,ctx_,null,one(BooleanStruct.of(false)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void add1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = new GrList(false);g.add(0,\"0\");return g;}}");
        Struct ls_ = ctxStr(pr_,files_);
        assertEq(1, ((GraphicListStruct)ls_).getGrList().size());
        assertEq("0", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(0)).getInstance());
    }
    @Test
    public void add2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = new GrList(false);g.add(0,new Image(1,1,true),\"0\");return g;}}");
        Struct ls_ = ctxStr(pr_,files_);
        assertEq(1, ((GraphicListStruct)ls_).getGrList().size());
        assertEq("0", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(0)).getInstance());
    }
    @Test
    public void add3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = new GrList(false);g.add(\"0\");return g;}}");
        Struct ls_ = ctxStr(pr_,files_);
        assertEq(1, ((GraphicListStruct)ls_).getGrList().size());
        assertEq("0", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(0)).getInstance());
    }
    @Test
    public void add4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = new GrList(false);g.add(-1,\"0\");return g;}}");
        Struct ls_ = ctxStr(pr_,files_);
        assertEq(0, ((GraphicListStruct)ls_).getGrList().size());
    }
    @Test
    public void set1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = new GrList(false);g.add(0,\"0\");g.set(-1,\"2\");g.set(0,\"1\");return g;}}");
        Struct ls_ = ctxStr(pr_,files_);
        assertEq(1, ((GraphicListStruct)ls_).getGrList().size());
        assertEq("1", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(0)).getInstance());
    }
    @Test
    public void set2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = new GrList(false);g.add(0,new Image(1,1,true),\"0\");g.set(-1,new Image(1,1,true),\"2\");g.set(0,new Image(1,1,true),\"1\");return g;}}");
        Struct ls_ = ctxStr(pr_,files_);
        assertEq(1, ((GraphicListStruct)ls_).getGrList().size());
        assertEq("1", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(0)).getInstance());
    }
    @Test
    public void remove() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = new GrList(false);g.add(\"0\");g.remove(0);return g;}}");
        Struct ls_ = ctxStr(pr_,files_);
        assertEq(0, ((GraphicListStruct)ls_).getGrList().size());
    }
    @Test
    public void clear() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = new GrList(false);g.add(\"0\");g.clear();return g;}}");
        Struct ls_ = ctxStr(pr_,files_);
        assertEq(0, ((GraphicListStruct)ls_).getGrList().size());
    }
    @Test
    public void getListView() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAdd1(),null,ctx_,ls_,three(new IntStruct(0),NullStruct.NULL_VALUE,new StringStruct("0")),st_);
        ArrayStruct arr_ = (ArrayStruct) call(new FctGrListGetListView(), null, ctx_, ls_, null, st_);
        assertEq(1, arr_.getLength());
        assertEq("0", arr_.get(0));
    }
    @Test
    public void indexes1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static int[] run(){var g = new GrList(false);g.add(\"0\");g.add(\"1\");g.add(\"2\");g.add(\"3\");g.setSelectedIndexes(null);return g.getSelectedIndexes();}}");
        ArrayStruct arr_ = (ArrayStruct) ctxStr(pr_,files_);
        assertEq(0, arr_.getLength());
    }
    @Test
    public void indexes2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static int[] run(){var g = new GrList(false);g.add(\"0\");g.add(\"1\");g.add(\"2\");g.add(\"3\");g.setSelectedIndexes(1,3);return g.getSelectedIndexes();}}");
        ArrayStruct arr_ = (ArrayStruct) ctxStr(pr_,files_);
        assertEq(2, arr_.getLength());
        assertEq(1, toLong(arr_.get(0)));
        assertEq(3, toLong(arr_.get(1)));
    }
    @Test
    public void indexes3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static int[] run(){var g = new GrList(false);g.add(\"0\");g.add(\"1\");g.add(\"2\");g.add(\"3\");g.setSelectedIndexes(1,3);g.clearSelection();return g.getSelectedIndexes();}}");
        ArrayStruct arr_ = (ArrayStruct) ctxStr(pr_,files_);
        assertEq(0, arr_.getLength());
    }
    @Test
    public void addList1() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAddSelection(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,((GraphicListStruct)ls_).getGrList().getSelections().size());
    }
    @Test
    public void addList2() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAddSelection(),null,ctx_,ls_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((GraphicListStruct)ls_).getGrList().getSelections().size());
    }
    @Test
    public void removeList1() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListAddSelection(),null,ctx_,ls_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctGrListRemoveSelection(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,((GraphicListStruct)ls_).getGrList().getSelections().size());
    }
    @Test
    public void removeList2() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctGrListAddSelection(),null,ctx_,ls_,one(list_),st_);
        call(new FctGrListRemoveSelection(),null,ctx_,ls_,one(list_),st_);
        assertEq(0,((GraphicListStruct)ls_).getGrList().getSelections().size());
    }
    @Test
    public void lists1() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctGrListAddSelection(),null,ctx_,ls_,one(list_),st_);
        ArrayStruct a_ = (ArrayStruct) call(new FctGrListGetSelections(), null, ctx_, ls_, null, st_);
        assertEq(1,a_.getLength());
        assertSame(list_,a_.get(0));
    }
    @Test
    public void lists2() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        ((GraphicListStruct)ls_).getGrList().addListener(new MockListSampleSelection());
        ArrayStruct a_ = (ArrayStruct) call(new FctGrListGetSelections(), null, ctx_, ls_, null, st_);
        assertEq(0,a_.getLength());
    }
    @Test
    public void toolTipText1() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctCompoToolTip1(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctCompoToolTip0(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void toolTipText2() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctCompoToolTip1(),null,ctx_,ls_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctCompoToolTip0(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void visible1() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctCompoSetVisible(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsVisible(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void visible2() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctCompoSetVisible(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled1() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctInputSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctInputIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled2() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctInputSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctInputIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void render1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static boolean run(){var g = new GrList(false);DefCellRender d = new();g.setRender(d);return g.getRender() == d;}}");
        Struct bs_ = ctxStr(pr_,files_);
        assertTrue(bs_);
    }
    @Test
    public void render2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static boolean run(){var g = new GrList(false);DefCellRender d = null;g.setRender(d);return g.getRender() != d;}}");
        Struct bs_ = ctxStr(pr_,files_);
        assertTrue(bs_);
    }
    @Test
    public void render3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new GrList(false);StringBuilder d = new StringBuilder();g.setRender((a,b,c,d,e,f,g)->{#d.append(StringUtil.valueOf(b));#d.append(\";\");return new Image(1,1,true);});g.add(\"1\");g.add(\"2\");return \"\"+d;}}");
        Struct bs_ = ctxStr(pr_,files_);
        assertEq("1;2;",bs_);
    }
    @Test
    public void update() {
        ContextEl ctx_ = ctx(newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"})));
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(getCaller(ctx_), null, ctx_, null, one(BooleanStruct.of(false)), st_);
        call(new FctGrListUpdateGraphics(),null,ctx_,ls_,one(new IntStruct(2)),st_);
        call(new FctGrListSetVisibleRowCount(),null,ctx_,ls_,one(new IntStruct(2)),st_);
        assertEq(2,toLong(call(new FctGrListGetVisibleRowCount(),null,ctx_,ls_,null,st_)));
    }
    @Test
    public void repaint1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = new GrList(false);g.add(\"2\");g.add(\"3\");g.updateGraphics();return g;}}");
        Struct ls_ = ctxStr(pr_,files_);
        assertEq(2, ((GraphicListStruct)ls_).getGrList().size());
        assertEq("2", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(0)).getInstance());
        assertEq("3", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(1)).getInstance());
    }
    @Test
    public void repaint2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = new GrList(false);g.add(\"ONE\");g.add(\"TWO\");g.add(\"THREE\");g.setSelectedIndexes(0,2);g.updateGraphics();return g;}}");
        Struct ls_ = ctxStr(pr_,files_);
        assertEq(3, ((GraphicListStruct)ls_).getGrList().size());
        assertEq("ONE", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(0)).getInstance());
        assertEq("TWO", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(1)).getInstance());
        assertEq("THREE", ((StringStruct) ((GraphicListStruct)ls_).getGrList().get(2)).getInstance());
    }
    @Test
    public void evts1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new GrList(false);StringBuilder d = new StringBuilder();g.addSelection((ListSelection)(int a,int b,boolean c:void)->{d.append(\"ONE\");});g.addSelection((ListSelection)(int a,int b,boolean c:void)->{d.append(\"TWO\");});g.setRender((a,b,c,d,e,f,g)->{#d.append(StringUtil.valueOf(b));#d.append(\";\");return new Image(1,1,true);});g.add(\"1\");g.add(\"2\");d.clear();g.setSelectedIndexes(0,1);return \"\"+d;}}");
        Struct bs_ = ctxStr(pr_,files_);
        assertEq("ONETWO1;2;",bs_);
    }
    @Test
    public void evts2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = new GrList(false);StringBuilder d = new StringBuilder();g.addSelection((ListSelection)(int a,int b,boolean c:void)->{d.append(\"ONE\");});g.addSelection((ListSelection)(int a,int b,boolean c:void)->{d.append(\"TWO\");});g.setRender((a,b,c,d,e,f,g)->{#d.append(StringUtil.valueOf(b));#d.append(\";\");return new Image(1,1,true);});g.add(\"1\");g.add(\"2\");d.clear();return g;}}");
        GraphicListStruct bs_ = (GraphicListStruct) ctxStr(pr_,files_);
        action(bs_.getGrList(),GuiConstants.VK_A,GuiConstants.CTRL_DOWN_MASK);
        assertEq(2,bs_.getGrList().getSelectedValuesLsLen());
    }
    @Test
    public void evts3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new GrList(false);StringBuilder d = new StringBuilder();g.addSelection((ListSelection)(int a,int b,boolean c:void)->{d.append(\"ONE\");});g.addSelection((ListSelection)(int a,int b,boolean c:void)->{d.append(\"TWO\");});g.setRender(new CellRender(d){public StringBuilder d;(StringBuilder d){this.d=d;}public Image generate(int a,Object b,boolean c,boolean d,boolean e,Font f,GrList g){this.d.append(StringUtil.valueOf(b));this.d.append(\";\");return new Image(1,1,true);}});g.add(\"1\");g.add(\"2\");d.clear();g.setSelectedIndexes(0,1);return \"\"+d;}}");
        Struct bs_ = ctxStr(pr_,files_);
        assertEq("ONETWO1;2;",bs_);
    }
    @Test
    public void evts4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static String run(){var g = new GrList(false);StringBuilder d = new StringBuilder();g.addSelection(new ListSelection(d){public StringBuilder d;(StringBuilder d){this.d=d;}public void valueChanged(int a,int b,boolean c){d.append(\"ONE\");}});g.addSelection(new ListSelection(d){public StringBuilder d;(StringBuilder d){this.d=d;}public void valueChanged(int a,int b,boolean c){d.append(\"TWO\");}});g.setRender((a,b,c,d,e,f,g)->{#d.append(StringUtil.valueOf(b));#d.append(\";\");return new Image(1,1,true);});g.add(\"1\");g.add(\"2\");d.clear();g.setSelectedIndexes(0,1);return \"\"+d;}}");
        Struct bs_ = ctxStr(pr_,files_);
        assertEq("ONETWO1;2;",bs_);
    }
    @Test
    public void evts5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = new GrList(false);StringBuilder d = new StringBuilder();g.addSelection((ListSelection)(int a,int b,boolean c:void)->{d.append(\"ONE\");});g.addSelection((ListSelection)(int a,int b,boolean c:void)->{d.append(\"TWO\");});g.setRender((CellRender)(a,b,c,d,e,f,g)->{#d.append(StringUtil.valueOf(b));#d.append(\";\");return new Image(1,1,true);});g.add(\"1\");g.add(\"2\");d.clear();return g;}}");
        GraphicListStruct bs_ = (GraphicListStruct) ctxStr(pr_,files_);
        action(bs_.getGrList(),GuiConstants.VK_A,GuiConstants.CTRL_DOWN_MASK);
        assertEq(2,bs_.getGrList().getSelectedValuesLsLen());
    }
    @Test
    public void evts6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static GrList run(){var g = (GrList)class(GrList).defaultInstance();StringBuilder d = new StringBuilder();g.addSelection((ListSelection)(int a,int b,boolean c:void)->{d.append(\"ONE\");});g.addSelection((ListSelection)(int a,int b,boolean c:void)->{d.append(\"TWO\");});g.setRender((CellRender)(a,b,c,d,e,f,g)->{#d.append(StringUtil.valueOf(b));#d.append(\";\");return new Image(1,1,true);});g.add(\"1\");g.add(\"2\");d.clear();return g;}}");
        GraphicListStruct bs_ = (GraphicListStruct) ctxStr(pr_,files_);
        assertEq(0,bs_.getGrList().getSelectedValuesLsLen());
    }
    private void action(ScrollCustomGraphicList<Struct> _gene, int _a, int _b) {
        ((MockAbstractAction)GuiBaseUtil.getAction(_gene.getElements(),_a,_b)).action();
    }
    private Struct ctxStr(MockProgramInfos _pr, StringMap<String> _p) {
        ContextEl ctx_ = ctx(_pr,_p);
        ExecRootBlock ex_ = ctx_.getClasses().getClassBody("pkg.Sample");
        StackCall resSt_ = StackCall.newInstance(InitPhase.NOTHING, ctx_);
        ExecFormattedRootBlock form_ = new ExecFormattedRootBlock(ex_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, "run", new StringList());
        return ArgumentListCall.toStr(EventStruct.invoke(NullStruct.NULL_VALUE, (RunnableContextEl) ctx_, new ExecTypeFunction(form_, ExecClassesUtil.getMethodBodiesById(ex_, id_).first()), resSt_, new ArgumentListCall()));
    }
    private ContextEl ctx(MockProgramInfos _p) {
        return ctx(_p,new StringMap<String>());
    }
    private ContextEl ctx(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = new ExecutingOptions();
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setLightProgramInfos(_p);
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMock(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files).getContext();
    }

    public static ResultContext buildMock(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getGuiAliases().renderInterface(_definedKw, _definedLgNames.getContent()));
        s_.addEntry("1",_definedLgNames.getGuiAliases().renderDefault(_definedKw, _definedLgNames.getContent()));
        s_.addEntry("2",_definedLgNames.getGuiAliases().listSelection(_definedKw, _definedLgNames.getContent()));
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

        _definedLgNames.getGuiAliases().color(_definedLgNames.getContent());
        _definedLgNames.getGuiAliases().image(_definedLgNames.getContent(), _definedLgNames.getGuiExecutingBlocks());
        _definedLgNames.getGuiAliases().font(_definedLgNames.getContent(), _definedLgNames.getGuiExecutingBlocks());
        _definedLgNames.getGuiAliases().buildGrList(_definedLgNames.getContent(),_definedLgNames.getExecContent().getCustAliases(),_definedLgNames.getGuiExecutingBlocks(),component_,input_);
        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMock(_exec, _definedLgNames, _files, page_, forwards_);
        LgNamesGui stds_ = (LgNamesGui) res_.getContext().getStandards();
        stds_.getGuiExecutingBlocks().cellRender(stds_.getGuiAliases(), stds_.getContent(),res_.getContext().getClasses());
        stds_.getGuiExecutingBlocks().listSelection(stds_.getGuiAliases(), stds_.getContent(),res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }
    private FctGrList getCaller(ContextEl _stds) {
        LgNamesGui lgui_ = (LgNamesGui) _stds.getStandards();
        return new FctGrList(lgui_.getExecContent().getCustAliases(), lgui_.getGuiExecutingBlocks(), "");
    }

    public static LgNamesGui newLgNamesGuiSampleGr(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),"en");
//        stds_.getExecContent().getCustAliases().build(new StringMap<String>(),new StringMap<String>(),new StringMap<String>());
//        basicStandards(stds_);
//        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
//        stds_.getExecContent().updateTranslations(_light.getTranslations(),_light.getLanguage(),"en");
//        stds_.getGuiAliases().setAliasGrList("$core.GrList");
//        StringMap<String> keys_ = LgNamesGui.extractAliasesKeys(stds_.getExecContent().getCustAliases());
//        stds_.getGuiAliases().otherAliasGui(LgNamesGui.addon(stds_.getExecContent().getCustAliases()),new StringMap<String>(),keys_);
//        stds_.getGuiAliases().renderInterface(,);
//        _files.addEntry("src/runnable.txt","public interface pkg.Runnable{public void run();}");
//        _files.addEntry("src/runnable.txt","public interface pkg.Runnable{public void run();}");
//        stds_.getStandards().addEntry("$core.GrList",new StandardClass("",new CustList<CstFieldInfo>(),new CustList<StandardConstructor>(),));
//        stds_.getExecContent().getCustAliases().build(new StringMap<String>(),new StringMap<String>(),new StringMap<String>());
//        basicStandards(stds_);
        return stds_;
    }
}
