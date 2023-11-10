package code.expressionlanguage.utilimpl;

import code.expressionlanguage.analyze.AbsAliasFileBuilder;
import code.expressionlanguage.analyze.DefAliasFileBuilder;
import code.expressionlanguage.guicompos.LgNamesGui;
import code.expressionlanguage.utilcompo.AbsAdvContextGenerator;
import code.mock.MockAliasFileBuilder;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.StringMap;

public class SampleExecFileBuilderGene implements AbsFileBuilderListGene {
    @Override
    public CustList<AbsAliasFileBuilder> build(LgNamesGui _stds) {
        CustList<AbsAliasFileBuilder> builders_ = new CustList<AbsAliasFileBuilder>();
        builders_.add(new DefAliasFileBuilder());
        String ass_ = "public abstract final class $core.Assert{\n" +
                "    public static void assert(long a,long b){\n" +
                "        if(!static($core.ObjectsUtil).eq(a,b)){\n" +
                "            var d = new $core.Difference();\n" +
                "            d.expected=a;\n" +
                "            d.found=b;\n" +
                "            d.stackDiff = static($core.Stack).current();\n" +
                "            throw d;\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "public class $core.Difference{\n" +
                "    public $core.Object expected;\n" +
                "    public $core.Object found;\n" +
                "    public boolean foundNull;\n" +
                "    public boolean foundNotTrue;\n" +
                "    public $core.Stack[] stackDiff;\n" +
                "    public $core.String $toString(){\n" +
                "        var out=new $core.StringBuilder();\n" +
                "        for (var s: this.stackDiff){\n" +
                "            out.append(static($core.StringUtil).valueOf(s));\n" +
                "        }\n" +
                "        if (this.foundNotTrue){\n" +
                "            out.insert(0,\"\\n\");\n" +
                "            out.insert(0,static($core.StringUtil).valueOf(this.found));\n" +
                "            return out.toString();\n" +
                "        }\n" +
                "        if (this.foundNull){\n" +
                "            out.insert(0,\"\\n\");\n" +
                "            out.insert(0,static($core.StringUtil).valueOf(null));\n" +
                "            return out.toString();\n" +
                "        }\n" +
                "        out.insert(0,\"\\n\");\n" +
                "        out.insert(0,static($core.StringUtil).valueOf(this.found));\n" +
                "        out.insert(0,\"!=\");\n" +
                "        out.insert(0,static($core.StringUtil).valueOf(this.expected));\n" +
                "        return out.toString();\n" +
                "    }\n" +
                "}\n" +
                "\n";
        StringMap<String> ls_ = new StringMap<String>();
        String lsType_ = "public class $core.CustIterator<T> :$core.Iterator<T>{\n" +
                "    private $core.List<T> list;\n" +
                "    private int length;\n" +
                "    private int index;\n" +
                "    public ($core.List<T> a){\n" +
                "        this.list=a;\n" +
                "        this.length=this.list.size();\n" +
                "    }\n" +
                "    public T next(){\n" +
                "        T out=this.list[this.index];\n" +
                "        this.index++;\n" +
                "        return out;\n" +
                "    }\n" +
                "    public boolean hasNext(){\n" +
                "        return index<length;\n" +
                "    }\n" +
                "}\n" +
                "public class $core.List<T> :$core.Iterable<T>{\n" +
                "    private T[] array;\n" +
                "    private int length;\n" +
                "    public (){\n" +
                "        array=new T[0];\n" +
                "    }\n" +
                "    public ($core.List<T> a){\n" +
                "        this.array=a.array.clone();\n" +
                "        this.length=this.array.length;\n" +
                "    }\n" +
                "    public (T... a){\n" +
                "        this.array=a.clone();\n" +
                "        this.length=this.array.length;\n" +
                "    }\n" +
                "    public void add(T a){\n" +
                "        add(this.length,a);\n" +
                "    }\n" +
                "    public void add(int a,T b){\n" +
                "        T[] out=new T[this.length+1];\n" +
                "        iter(int i=0;a;1){\n" +
                "            out[i]=this.array[i];\n" +
                "        }\n" +
                "        out[a]=b;\n" +
                "        iter(int i=a+1;this.length+1;1){\n" +
                "            out[i]=this.array[i-1];\n" +
                "        }\n" +
                "        this.length++;\n" +
                "        this.array=out;\n" +
                "    }\n" +
                "    public int size(){\n" +
                "        return this.length;\n" +
                "    }\n" +
                "    public T this(int a){\n" +
                "        return this.array[a];\n" +
                "    }\n" +
                "    public void this(int a){\n" +
                "        this.array[a]=value;\n" +
                "    }\n" +
                "    public void remove(int a){\n" +
                "        T[] out=new T[this.length-1];\n" +
                "        iter(int i=0;a;1){\n" +
                "            out[i]=this.array[i];\n" +
                "        }\n" +
                "        iter(int i=a+1;this.length;1){\n" +
                "            out[i-1]=this.array[i];\n" +
                "        }\n" +
                "        this.array = out;\n" +
                "        this.length--;\n" +
                "    }\n" +
                "    public void clear(){\n" +
                "        this.length=0;\n" +
                "        this.array=new T[0];\n" +
                "    }\n" +
                "    public $core.Iterator<T> iterator(){\n" +
                "        return new $core.CustIterator<T>(this);\n" +
                "    }\n" +
                "}";
        ls_.put("list", lsType_);
        String tableType_ = "public class $core.PairImpl<T,U> :$core.Pair<T,U>{\n" +
                "    private T first;\n" +
                "    private U second;\n" +
                "    public (){\n" +
                "    }\n" +
                "    public (T a,U b){\n" +
                "        this.first=a;\n" +
                "        this.second=b;\n" +
                "    }\n" +
                "    public T getFirst(){\n" +
                "        return first;\n" +
                "    }\n" +
                "    public U getSecond(){\n" +
                "        return second;\n" +
                "    }\n" +
                "    public void setFirst(T a){\n" +
                "        this.first=a;\n" +
                "    }\n" +
                "    public void setSecond(U a){\n" +
                "        this.second=a;\n" +
                "    }\n" +
                "}\n" +
                "public class $core.CustIteratorTable<T,U> :$core.IteratorTable<T,U>{\n" +
                "    private $core.Table<T,U> list;\n" +
                "    private int length;\n" +
                "    private int index;\n" +
                "    public ($core.Table<T,U> a){\n" +
                "        this.list=a;\n" +
                "        this.length=this.list.size();\n" +
                "    }\n" +
                "    public $core.PairImpl<T,U> nextPair(){\n" +
                "        $core.PairImpl<T,U> out=this.list.get(this.index);\n" +
                "        this.index++;\n" +
                "        return out;\n" +
                "    }\n" +
                "    public boolean hasNextPair(){\n" +
                "        return index<length;\n" +
                "    }\n" +
                "}\n" +
                "public class $core.Table<T,U> :$core.IterableTable<T,U>{\n" +
                "    private $core.List<$core.PairImpl<T,U>> list;\n" +
                "    public (){\n" +
                "        list=new $core.List<$core.PairImpl<T,U>>();\n" +
                "    }\n" +
                "    public void add(T a,U b){\n" +
                "        this.list.add(new $core.PairImpl<T,U>(a,b));\n" +
                "    }\n" +
                "    public void add($core.PairImpl<T,U> a){\n" +
                "        this.list.add(a);\n" +
                "    }\n" +
                "    public int size(){\n" +
                "        return list.size();\n" +
                "    }\n" +
                "    public $core.PairImpl<T,U> get(int a){\n" +
                "        return this.list[a];\n" +
                "    }\n" +
                "    public T getFirst(int a){\n" +
                "        return this.list[a].getFirst();\n" +
                "    }\n" +
                "    public U getSecond(int a){\n" +
                "        return this.list[a].getSecond();\n" +
                "    }\n" +
                "    public void setFirst(int a, T b){\n" +
                "        this.list[a].setFirst(b);\n" +
                "    }\n" +
                "    public void setSecond(int a, U b){\n" +
                "        this.list[a].setSecond(b);\n" +
                "    }\n" +
                "    public void remove(int a){\n" +
                "        this.list.remove(a);\n" +
                "    }\n" +
                "    public $core.IteratorTable<T,U> iteratorTable(){\n" +
                "        return new $core.CustIteratorTable<T,U>(this);\n" +
                "    }\n" +
                "}";
        ls_.put("table", tableType_);
        String v_ = "public abstract final class $core.Execute{\n" +
                "    public static $core.Table<$core.Method,$core.Result> tests($core.InfoTest a){\n" +
                "        return launch(a,flat(groupClassMethod(a,groupClass(a))));\n" +
                "    }\n" +
                "    public static $core.Table<$core.Class,$core.List<$core.ExecutedTest>> groupClass($core.InfoTest a) {\n" +
                "        $core.Table<$core.Class,$core.List<$core.ExecutedTest>> out = new();\n" +
                "        for (var c: static($core.Class).getAllClasses()){\n" +
                "            $core.List<$core.Method> bs = new();\n" +
                "            $core.List<$core.Method> as = new();\n" +
                "            $core.List<$core.ExecutedTest> tts = new();\n" +
                "            for (var m: c.getDeclaredMethods()){\n" +
                "                var e = new $core.ExecutedTest();\n" +
                "                var a0 = m.getAnnotations(class($core.Test));\n" +
                "                if (static($core.ObjectsUtil).eq(a0.length,1)){\n" +
                "                    e.test = m;\n" +
                "                    a.count = static($core.Math).plus(a.count,1);\n" +
                "                    tts.add(e);\n" +
                "                }\n" +
                "            }\n" +
                "            if (static($core.Math).gt(tts.size(),0)){\n" +
                "                out.add(c,tts);\n" +
                "            }\n" +
                "        }\n" +
                "        return out;\n" +
                "    }\n" +
                "    public static $core.Table<$core.Class,$core.Table<$core.Method,$core.Result>> groupClassMethod($core.InfoTest a,$core.Table<$core.Class,$core.List<$core.ExecutedTest>> b) {\n" +
                "        $core.Table<$core.Class,$core.Table<$core.Method,$core.Result>> results = new();\n" +
                "        for (var c, var l: b){\n" +
                "            $core.Table<$core.Method,$core.Result> tts = new();\n" +
                "            for (var e: l){\n" +
                "                var tt = e.test;\n" +
                "                $core.Result res = new();\n" +
                "                res.container = c;\n" +
                "                res.executed = e;\n" +
                "                tts.add(tt,res);\n" +
                "                a.calls = static($core.Math).plus(a.calls,1);\n" +
                "            }\n" +
                "            if (static($core.Math).gt(tts.size(),0)){\n" +
                "                results.add(c,tts);\n" +
                "            }\n" +
                "        }\n" +
                "        return results;\n" +
                "    }\n" +
                "    public static $core.Table<$core.Method,$core.Result> flat($core.Table<$core.Class,$core.Table<$core.Method,$core.Result>> a){\n" +
                "        $core.Table<$core.Method,$core.Result> res = new();\n" +
                "        for (var c, var l: a){\n" +
                "            for (var m, var r: l){\n" +
                "                res.add(m,r);\n" +
                "            }\n" +
                "        }\n" +
                "        return res;\n" +
                "    }\n" +
                "    public static $core.Table<$core.Method,$core.Result> launch($core.InfoTest a,$core.Table<$core.Method,$core.Result> b){\n" +
                "        for (var m, var l: b){\n" +
                "            $core.Runnable tt1 = () -> {\n" +
                "                a.currentMethod = l.executed.test;\n" +
                "                a.currentParams = l.params;\n" +
                "                if (static($core.ObjectsUtil).eq(l.args,null)) {\n" +
                "                    return;\n" +
                "                }\n" +
                "                var res = execute(l.executed,l.container,l.args);\n" +
                "                l.duration = res.duration;\n" +
                "                l.success = res.success;\n" +
                "                l.failMessage = res.failMessage;\n" +
                "            };\n" +
                "            tt1.run();\n" +
                "        }\n" +
                "        return b;\n" +
                "    }\n" +
                "    public static $core.Result execute($core.ExecutedTest a,$core.Class b,$core.Object[] c){\n" +
                "        var processEx = false;\n" +
                "        var stTime = 0;\n" +
                "        long diff = -1;\n" +
                "        try {\n" +
                "            var ctor = b.getDeclaredConstructors(false)[0];\n" +
                "            processEx = true;\n" +
                "            var classTest = ctor.newInstance();\n" +
                "            stTime = 0;\n" +
                "            a.test.invoke(classTest, c);\n" +
                "            diff = 0-stTime;\n" +
                "            return setupNoException(a,diff);\n" +
                "        } catch ($core.InvokeTarget o){\n" +
                "            diff = diff==-1 ? 0-stTime : diff;\n" +
                "            return setupError(a,o,processEx,diff);\n" +
                "        } catch ($core.Object o){\n" +
                "            diff = diff==-1 ? 0-stTime : diff;\n" +
                "            return setupError(a,o,diff);\n" +
                "        } catch {\n" +
                "            diff = diff==-1 ? 0-stTime : diff;\n" +
                "            return setupError(a,null,diff);\n" +
                "        }\n" +
                "    }\n" +
                "    public static $core.String convert($core.Object a){\n" +
                "        try {\n" +
                "            return static($core.StringUtil).valueOf(a);\n" +
                "        } catch ($core.Object e){\n" +
                "            return static($core.Class).getClass(a).getName();\n" +
                "        } catch {\n" +
                "            return static($core.Class).getClass(a).getName();\n" +
                "        }\n" +
                "    }\n" +
                "    public static $core.Result setupNoException($core.ExecutedTest a,long b){\n" +
                "        var res = new $core.Result();\n" +
                "        res.duration = b/1000000;\n" +
                "        return res;\n" +
                "    }\n" +
                "    public static $core.Result setupError($core.ExecutedTest a, $core.InvokeTarget b, boolean c, long d){\n" +
                "        var res = new $core.Result();\n" +
                "        res.duration = d/1000000;\n" +
                "        var ex = b.getCause();\n" +
                "        res.success = false;\n" +
                "        res.failMessage = static($core.StringUtil).valueOf(ex);\n" +
                "        return res;\n" +
                "    }\n" +
                "    public static $core.Result setupError($core.ExecutedTest a, $core.Object b, long c){\n" +
                "        var res = new $core.Result();\n" +
                "        res.duration = c/1000000;\n" +
                "        res.success = false;\n" +
                "        res.failMessage = static($core.StringUtil).valueOf(b);\n" +
                "        return res;\n" +
                "    }\n" +
                "}\n" +
                "public class $core.InfoTest{\n" +
                "    public int count;\n" +
                "    public $core.AtomicInteger done=new();\n" +
                "    public int nbThreads;\n" +
                "    public long calls;\n" +
                "    public $core.Method currentMethod;\n" +
                "    public $core.String currentParams;\n" +
                "}\n" +
                "public class $core.Result{\n" +
                "    public long duration = -1;\n" +
                "    public boolean success = true;\n" +
                "    public $core.String params = \"\";\n" +
                "    public $core.String failMessage = \"\";\n" +
                "    public $core.Class container;\n" +
                "    public $core.ExecutedTest executed;\n" +
                "    public $core.Object[] args = {};\n" +
                "}\n" +
                "public class $core.ExecutedTest{\n" +
                "    public $core.Method test;\n" +
                "}\n" +
                "public annotation $core.Test{\n" +
                "}\n";
        ls_.addEntry("_", v_+"public interface $core.Runnable{\n" +
                "    public abstract void run();\n" +
                "    public static $core.Runnable $($core.Fct<void>a){\n" +
                "        return new(a){\n" +
                "            public $core.Fct<void> runner;\n" +
                "            public($core.Fct<void>a){\n" +
                "                this.runner=a;\n" +
                "            }\n" +
                "            public void run(){\n" +
                "                this.runner.call();\n" +
                "            }\n" +
                "        };\n" +
                "    }\n" +
                "}");
        ls_.addEntry("ass",ass_);
        builders_.add(new MockAliasFileBuilder(ls_));
        return builders_;
    }

    @Override
    public AbsAdvContextGenerator gene(AbstractAtomicBoolean _st) {
        return new SampleMockGeneExec(_st);
    }
}
