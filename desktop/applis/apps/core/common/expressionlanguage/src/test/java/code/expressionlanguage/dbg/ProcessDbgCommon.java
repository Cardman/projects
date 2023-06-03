package code.expressionlanguage.dbg;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.sample.CustLgNames;
import code.util.StringMap;

public abstract class ProcessDbgCommon extends ProcessMethodCommon {
    protected static final String CUST_ITER_PATH = "pkg/CustIter";
    protected static final String CUST_LIST_PATH = "pkg/CustList";
    protected static final String CUST_ITER_TABLE_PATH = "pkg/CustIterTable";
    protected static final String CUST_PAIR_PATH = "pkg/CustPair";
    protected static final String CUST_TABLE_PATH = "pkg/CustTable";
    protected int now(StackCall _stack) {
        return _stack.getLastPage().getGlobalOffset();
    }
    protected static StackCall dbgNormal(String _class, MethodId _method, ResultContext _cont) {
        ExecClassesUtil.tryInitStaticlyTypes(_cont.getContext(), _cont.getForwards().getOptions());
        ExecRootBlock classBody_ = _cont.getContext().getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(_class));
        ExecNamedFunctionBlock method_ = ExecClassesUtil.getMethodBodiesById(classBody_, _method).first();
        Argument argGlLoc_ = new Argument();
        Parameters p_ = new Parameters();
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_cont.getContext());
        ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, _class), new ExecTypeFunction(classBody_, method_), p_), _cont.getContext(), stackCall_);
        return stackCall_;
    }

    protected static StackCall dbgContinueNormal(StackCall _stack, ContextEl _cont) {
        _cont.getInit().loopCalling(_cont, _stack);
        return _stack;
    }
    protected static ResultContext ctxLgReadOnlyOkQuick(String _lg, StringMap<String> _files, String... _types) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        opt_.setDebugging(false);
        opt_.setDebugging(true);
        addTypesInit(opt_, _types);
        CustLgNames lgName_ = getLgNames();
        assertEq("en",_lg);
        KeyWords kwl_ = en(lgName_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = getForwards(opt_,lgName_,kwl_,page_);
        AnalyzedPageEl a_ = validateWithoutInit(_files, page_);
        assertTrue( isEmptyErrors(a_));
        generalForward(a_, forwards_);
        ContextEl ctx_ = forwardAndClear(forwards_);
        ResultContext res_ = new ResultContext(a_, forwards_, a_.getMessages());
        res_.setContext(ctx_);
        return res_;
    }
    protected static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.CustList<U> :Iterable<U>{\n");
        xml_.append(" private U[] list;\n");
        xml_.append(" private int length;\n");
        xml_.append(" public (){\n");
        xml_.append("  list=new U[0i];\n");
        xml_.append(" }\n");
        xml_.append(" public void add(U elt){\n");
        xml_.append("  add(length,elt);\n");
        xml_.append(" }\n");
        xml_.append(" public void add(int index,U elt){\n");
        xml_.append("  U[] newlist=new U[length+1i];\n");
        xml_.append("  iter(int i=0i;index;1i){\n");
        xml_.append("   newlist[i]=list[i];\n");
        xml_.append("  }\n");
        xml_.append("  newlist[index]=elt;\n");
        xml_.append("  iter(int i=index+1i;length+1i;1i){\n");
        xml_.append("   newlist[i]=list[i-1i];\n");
        xml_.append("  }\n");
        xml_.append("  length++;\n");
        xml_.append("  list=newlist;\n");
        xml_.append(" }\n");
        xml_.append(" public int size(){\n");
        xml_.append("  return length;\n");
        xml_.append(" }\n");
        xml_.append(" public U get(int index){\n");
        xml_.append("  return list[index];\n");
        xml_.append(" }\n");
        xml_.append(" public void set(int index,U elt){\n");
        xml_.append("  list[index]=elt;\n");
        xml_.append(" }\n");
        xml_.append(" public void remove(int index){\n");
        xml_.append("  iter(int i=index;length-1i;1i){\n");
        xml_.append("   list[i]=list[i+1i];\n");
        xml_.append("  }\n");
        xml_.append("  list[length-1i]=null;\n");
        xml_.append("  length--;\n");
        xml_.append(" }\n");
        xml_.append(" public Iterator<U> iterator(){\n");
        xml_.append("  return new pkg.CustIter<U>(this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    protected static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.CustIter<T> :Iterator<T>{\n");
        xml_.append(" private pkg.CustList<T> list;\n");
        xml_.append(" private int length;\n");
        xml_.append(" private int index;\n");
        xml_.append(" public (pkg.CustList<T> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" public T next(){\n");
        xml_.append("  T out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean hasNext(){\n");
        xml_.append("  return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    protected static String getCustomPair() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.CustPair<U,V> :Pair<U,V>{\n");
        xml_.append(" private U first;\n");
        xml_.append(" private V second;\n");
        xml_.append(" public CustPair(){\n");
        xml_.append(" }\n");
        xml_.append(" public CustPair(U f,V s){\n");
        xml_.append("  first = f;\n");
        xml_.append("  second = s;\n");
        xml_.append(" }\n");
        xml_.append(" public U getFirst(){\n");
        xml_.append("  return first;\n");
        xml_.append(" }\n");
        xml_.append(" public V getSecond(){\n");
        xml_.append("  return second;\n");
        xml_.append(" }\n");
        xml_.append(" public void setFirst(U f){\n");
        xml_.append("  first = f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    protected static String getCustomTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.CustTable<U,V> :IterableTable<U,V>{\n");
        xml_.append(" private CustList<CustPair<U,V>> list;\n");
        xml_.append(" public (){\n");
        xml_.append("  list=new CustList<CustPair<U,V>>();\n");
        xml_.append(" }\n");
        xml_.append(" public void add(U f,V s){\n");
        xml_.append("  list.add(new CustPair<U,V>(f,s));\n");
        xml_.append(" }\n");
        xml_.append(" public void add(CustPair<U,V> p){\n");
        xml_.append("  list.add(p);\n");
        xml_.append(" }\n");
        xml_.append(" public int size(){\n");
        xml_.append("  return list.size();\n");
        xml_.append(" }\n");
        xml_.append(" public CustPair<U,V> get(int index){\n");
        xml_.append("  return list.get(index);\n");
        xml_.append(" }\n");
        xml_.append(" public IteratorTable<U,V> iteratorTable(){\n");
        xml_.append("  return new CustIterTable<U,V>(this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    protected static String getCustomIteratorTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.CustIterTable<U,V> :IteratorTable<U,V>{\n");
        xml_.append(" private CustTable<U,V> list;\n");
        xml_.append(" private int length;\n");
        xml_.append(" private int index;\n");
        xml_.append(" public CustIterTable(CustTable<U,V> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" public CustPair<U,V> nextPair(){\n");
        xml_.append("  CustPair<U,V> out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean hasNextPair(){\n");
        xml_.append("  return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
}
