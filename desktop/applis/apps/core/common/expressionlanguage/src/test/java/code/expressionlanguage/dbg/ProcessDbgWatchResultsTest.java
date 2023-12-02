package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StackCallReturnValue;
import code.expressionlanguage.exec.WatchResults;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.functionid.AbsractIdentifiableCommon;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgWatchResultsTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ResultContext res_ = resCtx("pkg.Ex",136,files_);
        Struct cont_ = endOper("(t+u)*v", "pkg.Ex", "exmeth", res_);
        assertEq(22,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new Ex[2].length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        std(res_);
        Struct cont_ = endOper("this.length+1", "pkg.Ex", "exmeth", res_);
        assertEq(3,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){try{return 1/0;} catch (Object e) {return 1;}}}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        divThrown(res_);
        Struct cont_ = endOper("this!=null", "pkg.Ex", "exmeth", res_);
        assertTrue(BooleanStruct.isTrue(cont_));
    }

    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v=3;\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  Ex e = new();\n");
        xml_.append("  e.v+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        res_.toggleWatchPoint("pkg/Ex",27);
        compoundReadWrite(res_, cf("pkg.Ex", "v"));
        Struct cont_ = endOper("v+1", "pkg.Ex", "catching", res_);
        assertEq(4,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f=3;public static int exmeth(){return new Ex().exmeth2(5);}public int exmeth2(int a){return f+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("a==5&&f==3",res_,"pkg/Ex",91);
        Struct cont_ = endOper("a+f", "pkg.Ex", "exmeth", res_);
        assertEq(8, ((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){int t = 8;int u = 3;return t%u;}}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        stdSimpleCondition(res_,"value+0==8&&#value+0==3","%","int","int",true,false);
        Struct cont_ = endOper("value+#value", "pkg.Ex", "exmeth", res_);
        assertEq(11, ((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int v=2;public class Inner{}public static int exmeth(){return new Ex().new Inner().v;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        stdThrownConditionInner(res_,"value==1&&Class.getClass($parent)==class(Ex)&&$parent.v==2");
        Struct cont_ = endOper("value+$parent.v", "pkg.Ex", "exmeth", res_);
        assertEq(3,((NumberStruct)cont_).intStruct());
    }

    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().substring(b:4,a:2).length();}public static String exmeth2(){return \"1234\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        enteringCondition("a==2&&b==4",res_,res_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,res_.getContext().getStandards().getCharSeq().getAliasSubstring(),false, res_.getContext().getStandards().getPrimTypes().getAliasPrimInteger(), res_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        Struct cont_ = endOper("a+b", "pkg.Ex", "exmeth", res_);
        assertEq(6,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){try{throw 1;} catch (Object e) {return 1;}}}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        intThrown(res_);
        Struct cont_ = endOper("this+2", "pkg.Ex", "exmeth", res_);
        assertEq(3,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test10() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        res_.toggleBreakPoint("pkg/Ex",114);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, res_);
        dbgContinueNormalValueStepBlock(stack_, res_.getContext());
        assertNull(WatchResults.dynamicAnalyze("",stack_, res_, new DefContextGenerator()).getWatchedObject());
    }
    @Test
    public void test11() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return end(v);}public static int end(int i){return i+1;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        res_.toggleBreakPoint("pkg/Ex",114);
        StackCallReturnValue stVal_ = goLoop(res_, "pkg.Ex", "exmeth", null);
        Struct cont1_ = WatchResults.dynamicAnalyze("v", res_, new DefContextGenerator(), stVal_.getStack().getCall(0)).getWatchedObject();
        assertEq(2,((NumberStruct)cont1_).intStruct());
        Struct cont2_ = WatchResults.dynamicAnalyze("i", res_, new DefContextGenerator(), stVal_.getStack().getCall(1)).getWatchedObject();
        assertEq(2,((NumberStruct)cont2_).intStruct());
    }
    @Test
    public void test12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {static int m(){int res=5;for(int e:{1,2,3}){res+=e;}return res;}}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        res_.toggleBreakPoint("pkg/Ex",55);
        StackCallReturnValue stVal_ = goLoop(res_, "pkg.Ex", "m", null);
        Struct cont1_ = WatchResults.dynamicAnalyze("res", res_, new DefContextGenerator(), stVal_.getStack().getLastPage()).getWatchedObject();
        assertEq(5,((NumberStruct)cont1_).intStruct());
    }
    @Test
    public void test13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {static int m(){int res=5;for(int e:{0}){res+=e;}return res;}}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        res_.toggleBreakPoint("pkg/Ex",55);
        StackCallReturnValue first_ = goLoop(res_, "pkg.Ex", "m", null);
        Struct cont1_ = WatchResults.dynamicAnalyze("res", res_, new DefContextGenerator(), dbgContinueNormal(first_.getStack(),res_.getContext()).getLastPage()).getWatchedObject();
        assertEq(5,((NumberStruct)cont1_).intStruct());
    }
    @Test
    public void test14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {static int m(){return (int)class(Ex).getDeclaredMethods(null,null,null,class(int))[0].invoke(null,2);}static int m(int v){return 5+v;}}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        res_.toggleBreakPoint("pkg/Ex",150);
        StackCallReturnValue first_ = goLoop(res_, "pkg.Ex", "m", null);
        assertNull(WatchResults.dynamicAnalyze("", res_, new DefContextGenerator(),first_.getStack().getCall(1)).getWatchedObject());
    }
    @Test
    public void test15() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        res_.toggleBreakPoint("pkg/Ex",114);
        StackCallReturnValue first_ = goLoop(res_, "pkg.Ex", "exmeth", null);
        StackCall stack_ = first_.getStack();
        StackCall next_ = dbgContinueNormalValueNextInstMethod(stack_, res_.getContext());
        Struct cont_ = WatchResults.dynamicAnalyze("t+u", next_, res_, new DefContextGenerator()).getWatchedObject();
        assertEq(11,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test16() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        res_.toggleBreakPoint("pkg/Ex",114);
        StackCallReturnValue first_ = goLoop(res_, "pkg.Ex", "exmeth", null);
        StackCall stack_ = first_.getStack();
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, res_.getContext());
        Struct cont_ = WatchResults.dynamicAnalyze("t+u", next_, res_, new DefContextGenerator()).getWatchedObject();
        assertEq(11,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test17() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return end(v);}public static int end(int i){return i+1;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        res_.toggleBreakPoint("pkg/Ex",62);
        StackCallReturnValue stVal_ = goLoop(res_, "pkg.Ex", "exmeth", null);
        Struct cont1_ = WatchResults.dynamicAnalyze("v", res_, new DefContextGenerator(), stVal_.getStack().getCall(0)).getWatchedObject();
        assertEq(2,((NumberStruct)cont1_).intStruct());
    }
    @Test
    public void test18() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){var f=(int a:int)->2*a;return 0;}}");
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        res_.toggleBreakPoint("pkg/Ex",70);
        StackCallReturnValue stVal_ = goLoop(res_, "pkg.Ex", "exmeth", null);
        Struct cont1_ = WatchResults.dynamicAnalyze("f!=null", res_, new DefContextGenerator(), stVal_.getStack().getCall(0)).getWatchedObject();
        assertTrue(BooleanStruct.isTrue(cont1_));
    }
    @Test
    public void test19() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){new Ex();return 0;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext res_ = ctxLgReadOnlyOkQuick("en",files_);
        res_.toggleBreakPoint("pkg/Ex",13);
        ((TypePointBlockPair)res_.tryGetPair("pkg/Ex",13)).getValue().setStaticType(false);
        ((TypePointBlockPair)res_.tryGetPair("pkg/Ex",13)).getValue().setInstanceType(true);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, res_);
        assertNull(WatchResults.dynamicAnalyze("",stack_, res_, new DefContextGenerator()).getWatchedObject());
    }
    private void divThrown(ResultContext _cond) {
        _cond.toggleExcPoint(_cond.getContext().getStandards().getCoreNames().getAliasDivisionZero(),ExcPointBlockKey.SAME);
        ExcPoint val_ = _cond.getPairExc(_cond.getContext().getStandards().getCoreNames().getAliasDivisionZero(), ExcPointBlockKey.SAME).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }

    private void intThrown(ResultContext _cond) {
        _cond.toggleExcPoint(_cond.getContext().getStandards().getNbAlias().getAliasInteger(),ExcPointBlockKey.SAME);
        ExcPoint val_ = _cond.getPairExc(_cond.getContext().getStandards().getNbAlias().getAliasInteger(), ExcPointBlockKey.SAME).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(false);
    }
    private void std(ResultContext _cont) {
        _cont.toggleArrPoint("[pkg.Ex",ExcPointBlockKey.SAME);
        ArrPoint val_ = _cont.getPairArr("[pkg.Ex", ExcPointBlockKey.SAME).getValue();
        disable(val_);
        val_.setLength(true);
    }

    private void disable(ArrPoint _val) {
        _val.setLength(false);
        _val.setIntGet(false);
        _val.setIntSet(false);
        _val.setIntCompoundGet(false);
        _val.setIntCompoundSet(false);
        _val.setIntCompoundSetErr(false);
        _val.setRangeGet(false);
        _val.setRangeSet(false);
        _val.setRangeCompoundGet(false);
        _val.setRangeCompoundSet(false);
        _val.setIntGetSet(false);
        _val.setInitArray(false);
        _val.setClone(false);
    }

    private void enteringCondition(String _newValue,ResultContext _cont, String _file, int _offset) {
        entering(_cont, _file, _offset);
        String id_ = MemberCallingsBlock.clName(ResultExpressionOperationNode.keyMethodBp(_offset, _cont.getPageEl().getPreviousFilesBodies().getVal(_file)));
        MethodPointBlockPair wp_ = _cont.getPair(id_);
        wp_.getValue().getResultEntry().analyze(wp_,_newValue,"", "", _cont,new DefContextGenerator());
        assertEq(_newValue,wp_.getValue().getResultEntry().getResultStr());
    }

    private void entering(ResultContext _cont, String _file, int _offset) {
        _cont.toggleBreakPoint(_file,_offset);
        String id_ = MemberCallingsBlock.clName(ResultExpressionOperationNode.keyMethodBp(_offset, _cont.getPageEl().getPreviousFilesBodies().getVal(_file)));
        _cont.getPair(id_).getValue().setEntry(true);
        _cont.getPair(id_).getValue().setExit(false);
    }
    private void stdSimpleCondition(ResultContext _cont, String _condition, String _symbol,String _first, String _second, boolean _simple, boolean _compound) {
        CompoOperNatPointBlockPair p_ = std(_cont, _symbol, _first, _second, _simple, _compound);
        CompOperNatPoint wp_ = p_.getValue();
        wp_.getResultSimple().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultSimple().getResultStr());
    }

    private CompoOperNatPointBlockPair std(ResultContext _cont, String _symbol,String _first, String _second, boolean _simple, boolean _compound) {
        CompoOperNatPointBlockPair p_ = (CompoOperNatPointBlockPair) _cont.toggleOperNatPoint(_symbol, _first, _second);
        CompOperNatPoint val_ = p_.getValue();
        val_.setSimple(_simple);
        val_.setCompound(_compound);
        return p_;
    }

    private void stdThrownConditionInner(ResultContext _cont, String _condition) {
        stdInner(_cont);
        ParPointBlockPair p_ = _cont.getPairPar("pkg.Ex..Inner", ExcPointBlockKey.SAME);
        ParPoint wp_ = p_.getValue();
        wp_.getResultGet().analyze(p_,_condition,"", "", _cont,new DefContextGenerator());
        assertEq(_condition,wp_.getResultGet().getResultStr());
    }

    private void stdInner(ResultContext _cont) {
        _cont.toggleParPoint("pkg.Ex..Inner",ExcPointBlockKey.SAME);
        ParPoint val_ = _cont.getPairPar("pkg.Ex..Inner", ExcPointBlockKey.SAME).getValue();
        val_.setGet(true);
    }

    private void enteringCondition(String _newValue,ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardNamedFunction s_ = entering(_cont, _clName, _id);
//        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        StdMethodPointBlockPair wp_ = _cont.getPair(s_);
        wp_.getValue().getResultEntry().analyze(wp_,_newValue,"", "", _cont,new DefContextGenerator());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_newValue, wp_, _cont, type_, new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
        assertEq(_newValue,wp_.getValue().getResultEntry().getResultStr());
//        wp_.getValue().getResultEntry().result(res_,_newValue);
    }

    private StandardNamedFunction entering(ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardNamedFunction s_ = toggled(_cont, _clName, _id);
        _cont.getPair(s_).getValue().setEntry(true);
        _cont.getPair(s_).getValue().setExit(false);
        return s_;
    }

    private StandardNamedFunction toggled(ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardType v_ = _cont.getContext().getStandards().getStandards().getVal(_clName);
        StandardNamedFunction s_ = _id.look(v_).first();
        _cont.toggleBreakPoint(v_,s_);
        return s_;
    }

    private void compoundReadWrite(ResultContext _cont, ClassField _cf) {
        pair(_cont, _cf).getValue().setRead(false);
        pair(_cont, _cf).getValue().setWrite(false);
        pair(_cont, _cf).getValue().setCompoundRead(true);
        pair(_cont, _cf).getValue().setCompoundWrite(true);
        pair(_cont, _cf).getValue().setCompoundWriteErr(true);
    }

    private static WatchPointBlockPair pair(ResultContext _cont, ClassField _cf) {
        int n_ = _cont.getPageEl().getAnaClassBody(_cf.getClassName()).getNumberAll();
        return _cont.getPairWatch(true,n_,_cf.getFieldName());
    }

    private ClassField cf(String _cl, String _f) {
        return new ClassField(_cl,_f);
    }
}
