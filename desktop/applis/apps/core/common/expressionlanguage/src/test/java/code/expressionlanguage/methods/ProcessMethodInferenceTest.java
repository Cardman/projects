package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodInferenceTest extends ProcessMethodCommon {
    private static final String CUST_ITER_PATH = "pkg/CustIter";
    private static final String CUST_LIST_PATH = "pkg/CustList";
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] arr = {};\n");
        xml_.append("  $if (arr.length != 0i){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($int[])){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] arr = {1y};\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($int[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i] != 1y){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[][] arr = {{}};\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($int[][])){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 0i){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class($int[])){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[][] arr = {{2y}};\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 5i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($int[][])){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class($int[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i][0i] != 2y){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<java.lang.Integer> ex = $new ExTwo<java.lang.Integer>();\n");
        xml_.append("  java.lang.Integer[][] arr = ex.array;\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 5i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 0i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $package $final T[][] array = {$new T[]{}};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<java.lang.Number,java.lang.Integer> ex = $new ExTwo<java.lang.Number,java.lang.Integer>();\n");
        xml_.append("  java.lang.Number[][] arr = ex.array;\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 5i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class(java.lang.Number[][])){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 0i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T,S:T> {\n");
        xml_.append(" $package $final T[][] array = {$new S[]{}};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[][][] arr = {{{3y}}};\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 7i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($int[][][])){\n");
        xml_.append("   $return 6i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 1i){\n");
        xml_.append("   $return 5i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class($int[][])){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i][0i].length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i][0i]) != $class($int[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i][0i][0i] != 3y){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<java.lang.Integer> ex = $new ExTwo<java.lang.Integer>();\n");
        xml_.append("  ex.array = {{2i}};\n");
        xml_.append("  java.lang.Integer[][] arr = ex.array;\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 5i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i][0i] != 2i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $package T[][] array = {$new T[]{}};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var ex = $new ExTwo<java.lang.Integer>();\n");
        xml_.append("  ex.array = {{2i}};\n");
        xml_.append("  java.lang.Integer[][] arr = ex.array;\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 5i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i][0i] != 2i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $package T[][] array = {$new T[]{}};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var ex = $new ExTwo<java.lang.Integer>(), extwo = $new ExTwo<java.lang.Integer>();\n");
        xml_.append("  ex.array = {{2i}};\n");
        xml_.append("  java.lang.Integer[][] arr = ex.array;\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 5i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i][0i] != 2i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $package T[][] array = {$new T[]{}};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument101Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var ex = $new ExTwo<java.lang.Integer>(), extwo = $new ExTwo<java.lang.Integer>();\n");
        xml_.append("  ex.array = {{2i}};\n");
        xml_.append("  java.lang.Integer[][] arr = ex.array;\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 5i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i][0i] != 2i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $package T[][] array = $new T[][]{$new T[]{}};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument102Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var ex = $new ExTwo<java.lang.Integer>(), extwo = $new ExTwo<java.lang.Integer>();\n");
        xml_.append("  ex.array = $new java.lang.Integer[][]{{2i}};\n");
        xml_.append("  java.lang.Integer[][] arr = ex.array;\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 5i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i][0i] != 2i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $package T[][] array = $new T[][]{$new T[]{}};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument103Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var extwo,ex = $new ExTwo<java.lang.Integer>();\n");
        xml_.append("  extwo= $new ExTwo<java.lang.Integer>();\n");
        xml_.append("  ex.array = $new java.lang.Integer[][]{{2i}};\n");
        xml_.append("  java.lang.Integer[][] arr = ex.array;\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 5i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i][0i] != 2i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $package T[][] array = $new T[][]{$new T[]{}};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int res = 0i;\n");
        xml_.append("  $for($var i=0i;i<=4i;i++){\n");
        xml_.append("   res+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (res != 10i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument111Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int res = 0i;\n");
        xml_.append("  $for($var j,i=0i;i<=4i;i++){\n");
        xml_.append("   j=15;\n");
        xml_.append("   res+=i+j;\n");
        xml_.append("  }\n");
        xml_.append("  $if (res != 85i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int res = 0i;\n");
        xml_.append("  $int j = 0i;\n");
        xml_.append("  $for(;j<=4i;j++){\n");
        xml_.append("   res+=j;\n");
        xml_.append("  }\n");
        xml_.append("  $if (res != 10i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>();\n");
        xml_.append(" $public $int res;\n");
        xml_.append(" {\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  $foreach($var e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int[] arr(){\n");
        xml_.append("  $return {};\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] arr = arr();\n");
        xml_.append("  $if (arr.length != 0i){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($int[])){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] arr = ({});\n");
        xml_.append("  $if (arr.length != 0i){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($int[])){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $foreach($int i:{8,16}){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }

    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int var(){\n");
        xml_.append("  var t=8;\n");
        xml_.append("  t+=5;\n");
        xml_.append("  return 1i+(int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("var");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int var(){\n");
        xml_.append("  final var t=8;\n");
        xml_.append("  return 1i+(int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("var");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $foreach($int i:$bool(t==0,{8,16},{})){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int var(){\n");
        xml_.append("  var b=true;\n");
        xml_.append("  var t=b?(Integer)5:(Long)6;\n");
        xml_.append("  t+=5;\n");
        xml_.append("  return 1i+(int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("var");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }
    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo arr = $new();\n");
        xml_.append("  $if (arr.field != 0i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo arr = $new(10);\n");
        xml_.append("  $if (arr.field != 10){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo arr = catching2();\n");
        xml_.append("  $if (arr.field != 10){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo catching2(){\n");
        xml_.append("  $return $new(10);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var arr = $(ExTwo)$new(10);\n");
        xml_.append("  $if (arr.field != 10){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo arr = $new{}();\n");
        xml_.append("  $if (arr.field != 0i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo[] arr = {$new(10)};\n");
        xml_.append("  $if (arr[0].field != 10){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo[] arr = $new[1];\n");
        xml_.append("  arr[0]=$new(10);\n");
        xml_.append("  $if (arr[0].field != 10){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo[][] arr = $new[1][];\n");
        xml_.append("  arr[0]=$new[1];\n");
        xml_.append("  arr[0][0]=$new(10);\n");
        xml_.append("  $if (arr[0][0].field != 10){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo[] arr = catching2();\n");
        xml_.append("  arr[0]=$new(10);\n");
        xml_.append("  $if (arr[0].field != 10){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo[] catching2(){\n");
        xml_.append("  $return $new[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<$int>[] arr = $new ExTwo<>[]{$new ExTwo<>(10)};\n");
        xml_.append("  $if (arr[0].field != 10i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: $new ExTwo<>[]{$new ExTwo<$int>(10)}){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr()){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(){\n");
        xml_.append("  $return $new ExTwo<>[]{$new ExTwo<>(10)};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr($new ExTwo<>(10))){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int> e){\n");
        xml_.append("  $return $new ExTwo<>[]{e};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr($new ExTwo<>(10))){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int>... e){\n");
        xml_.append("  $return $new ExTwo<>[]{e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<$int>[] e= arr($new ExTwo<>(10),$new ExTwo<>(11));\n");
        xml_.append("  $return e[0].field + e[1].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int>... e){\n");
        xml_.append("  $return $new ExTwo<>[]{e[0],e[1]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(21, getNumber(ret_));
    }
    @Test
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr(10)){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int> e){\n");
        xml_.append("  $return $new ExTwo<>[]{e};\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr($int e){\n");
        xml_.append("  $return arr($new ExTwo<>(e));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr($new $int[]{10})){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int> e){\n");
        xml_.append("  $return $new ExTwo<>[]{e};\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr($int[] e){\n");
        xml_.append("  $return arr($new ExTwo<>(e[0]));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr()){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int> e){\n");
        xml_.append("  $return $new ExTwo<>[]{e};\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(){\n");
        xml_.append("  $return arr($new ExTwo<>(10));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr($id(Ex,ExTwo<$int>),$new ExTwo<>(10))){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int> e){\n");
        xml_.append("  $return $new ExTwo<>[]{e};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<$int>[] e= arr($new ExTwo<$int>[]{$new ExTwo<>(10),$new ExTwo<>(11)});\n");
        xml_.append("  $return e[0].field + e[1].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int>[] e){\n");
        xml_.append("  $return $new ExTwo<>[]{e[0],e[1]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(21, getNumber(ret_));
    }
    @Test
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr($vararg(ExTwo<$int>),$firstopt($new ExTwo<>(10)))){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int>... e){\n");
        xml_.append("  $return $new ExTwo<>[]{e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>();\n");
        xml_.append("  $return pass.arr($new ExTwo<>(10))[0].field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr(ExTwo<S> e){\n");
        xml_.append("  $return $new ExTwo<>[]{e};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>();\n");
        xml_.append("  $return pass.arr($new ExTwo<>(10));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr(ExTwo<S> e){\n");
        xml_.append("  $return $new ExTwo<>[]{e};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }

    @Test
    public void calculateArgument45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>();\n");
        xml_.append("  $return pass.arr($vararg(ExTwo<$int>),$firstopt($new ExTwo<>(10)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr(ExTwo<S>... e){\n");
        xml_.append("  $return $new ExTwo<>[]{e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($new ExTwo<>(10));\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree(ExTwo<S>... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($id(ExThree,ExTwo<S>...),$new ExTwo<>(10));\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree(ExTwo<S>... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($vararg(ExTwo<$int>),$firstopt($new ExTwo<>(10)));\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree(ExTwo<S>... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>();\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree(){\n");
        xml_.append("  $this($new ExTwo<>((S)10));\n");
        xml_.append(" }\n");
        xml_.append(" $public ExThree(ExTwo<S>... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr($new ExTwo<$int>[]{$new ExTwo<>(10)})){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int>... e){\n");
        xml_.append("  $return $new ExTwo<>[]{e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr($new ExTwo<>(9),$new ExTwo<>(10))){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int> d,ExTwo<$int>... e){\n");
        xml_.append("  $return $new ExTwo<>[]{d,e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: $operator(+,Ex)($new ExTwo<>(9),$new ExTwo<>(10))){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ ExTwo<$int>[] (ExTwo<$int> d,ExTwo<$int>... e){\n");
        xml_.append("  $return $new ExTwo<>[]{d,e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: $operator(+,Ex)($id($static,ExTwo<$int>,ExTwo<$int>...),$new ExTwo<>(9),$new ExTwo<>(10))){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ ExTwo<$int>[] (ExTwo<$int> d,ExTwo<$int>... e){\n");
        xml_.append("  $return $new ExTwo<>[]{d,e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[$new ExTwo<>(9),$new ExTwo<>(10)]=$new ExTwo<>[]{$new ExTwo<>(9),$new ExTwo<>(10)};\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for (ExTwo<$int> f: e[$new ExTwo<>(9),$new ExTwo<>(10)]){\n");
        xml_.append("   sum += f.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo<$int>[] arr = $new ExTwo<$int>[0];\n");
        xml_.append(" $public ExTwo<$int>[] $this(ExTwo<$int> d,ExTwo<$int>... e){\n");
        xml_.append("  $return $new ExTwo<>[]{d,e[0]};\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this(ExTwo<$int> d,ExTwo<$int>... e){\n");
        xml_.append("  arr = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(19, getNumber(ret_));
    }
    @Test
    public void calculateArgument55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[$new ExTwo<>(9)]=$new ExTwo<>[]{$new ExTwo<>(9)};\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for (ExTwo<$int> f: e[$new ExTwo<>(9)]){\n");
        xml_.append("   sum += f.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo<$int>[] arr = $new ExTwo<$int>[0];\n");
        xml_.append(" $public ExTwo<$int>[] $this(ExTwo<$int> d,ExTwo<$int>... e){\n");
        xml_.append("  $return $new ExTwo<>[]{d,e[0]};\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this(ExTwo<$int> d,ExTwo<$int>... e){\n");
        xml_.append("  arr = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo<$int>[] $this(ExTwo<$int> d){\n");
        xml_.append("  $return $that[d,$new ExTwo<$int>(10)];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this(ExTwo<$int> d){\n");
        xml_.append("  $that[d,$new ExTwo<$int>(10)] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(19, getNumber(ret_));
    }
    @Test
    public void calculateArgument56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[$new ExTwo<>(9)]=$new ExTwo<>[]{$new ExTwo<>(9)};\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for (ExTwo<$int> f: e[$new ExTwo<>(9)]){\n");
        xml_.append("   sum += f.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo<$int>[] arr = $new ExTwo<$int>[0];\n");
        xml_.append(" $public ExTwo<$int>[] $this(ExTwo<$int> d,ExTwo<$int>... e){\n");
        xml_.append("  $return $new ExTwo<>[]{d,e[0]};\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this(ExTwo<$int> d,ExTwo<$int>... e){\n");
        xml_.append("  arr = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo<$int>[] $this(ExTwo<$int> d){\n");
        xml_.append("  $return $this[d,$new ExTwo<$int>(10)];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this(ExTwo<$int> d){\n");
        xml_.append("  $this[d,$new ExTwo<$int>(10)] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(19, getNumber(ret_));
    }
    @Test
    public void calculateArgument57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[$id(Ex,ExTwo<$int>,ExTwo<$int>...),$new ExTwo<>(9),$new ExTwo<>(10)]=$new ExTwo<>[]{$new ExTwo<>(9),$new ExTwo<>(10)};\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for (ExTwo<$int> f: e[$id(Ex,ExTwo<$int>,ExTwo<$int>...),$new ExTwo<>(9),$new ExTwo<>(10)]){\n");
        xml_.append("   sum += f.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo<$int>[] arr = $new ExTwo<$int>[0];\n");
        xml_.append(" $public ExTwo<$int>[] $this(ExTwo<$int> d,ExTwo<$int>... e){\n");
        xml_.append("  $return $new ExTwo<>[]{d,e[0]};\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this(ExTwo<$int> d,ExTwo<$int>... e){\n");
        xml_.append("  arr = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(19, getNumber(ret_));
    }
    @Test
    public void calculateArgument58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($vararg(ExTwo<$int>),$firstopt($new ExTwo<>(10)));\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree(ExTwo<S>... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{e[0]};\n");
        xml_.append(" }\n");
        xml_.append(" $public ExThree(ExTwo<S> d,ExTwo<S>... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{d,e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<$int>[] arr = catching2();\n");
        xml_.append("  $return arr[0].field+arr[1].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($vararg(ExTwo<$int>),$new ExTwo<>(9),$firstopt($new ExTwo<>(10)));\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree(ExTwo<S>... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{e[0]};\n");
        xml_.append(" }\n");
        xml_.append(" $public ExThree(ExTwo<S> d,ExTwo<S>... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{d,e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(19, getNumber(ret_));
    }
    @Test
    public void calculateArgument60Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr(10)){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int> arr(ExTwo<$int> e){\n");
        xml_.append("  $return e;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr($int e){\n");
        xml_.append("  $return arr($new ExTwo<>(e));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static pkg.ExTwo<T>[] $(pkg.ExTwo<T> p){\n");
        xml_.append("  $return $new ExTwo<>[]{p};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<$int>[] e= arr($new ExTwo<>[]{$new ExTwo<>(10),$new ExTwo<>(11)});\n");
        xml_.append("  $return e[0].field + e[1].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int>[] e){\n");
        xml_.append("  $return $new ExTwo<>[]{e[0],e[1]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(21, getNumber(ret_));
    }
    @Test
    public void calculateArgument62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($new ExTwo<>[]{$new ExTwo<>(10)});\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree(ExTwo<S>... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{e[0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<$int>[] e= arr($new ExTwo<>[]{$new ExTwo<>(10),$new ExTwo<>(11)});\n");
        xml_.append("  $return e[0].field + e[1].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr(ExTwo<$int>[] e){\n");
        xml_.append("  $return $new ExTwo<>[]{e[0],e[1]};\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr($int e, $int f){\n");
        xml_.append("  $return $new ExTwo<>[]{$new ExTwo<>(e),$new ExTwo<>(f)};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(21, getNumber(ret_));
    }
    @Test
    public void calculateArgument64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($new ExTwo<>[]{$new ExTwo<>(10)});\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree(ExTwo<S>... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{e[0]};\n");
        xml_.append(" }\n");
        xml_.append(" $public ExThree(S a){\n");
        xml_.append("  arr=$new ExTwo<>[]{$new ExTwo<>(a)};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($new ExTwo<>[]{$new ExTwo<>(10)});\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree(ExTwo<S>[]... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{e[0][0]};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<java.lang.Integer> ex = $new ExTwo<java.lang.Integer>();\n");
        xml_.append("  ex.array = {{2i}};\n");
        xml_.append("  java.lang.Integer[][] arr = ex.array;\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 5i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i][0i] != 2i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $package $final T[][] array = {$new T[]{}};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var extwo;\n");
        xml_.append("  $var ex = $new ExTwo<java.lang.Integer>();\n");
        xml_.append("  ex.array = {{2i}};\n");
        xml_.append("  java.lang.Integer[][] arr = ex.array;\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 5i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class(java.lang.Integer[][])){\n");
        xml_.append("   $return 4i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i].length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(java.lang.Integer[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if (arr[0i][0i] != 2i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $package T[][] array = {$new T[]{}};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int res = 0i;\n");
        xml_.append("  $int j = 0i;\n");
        xml_.append("  $for($var i;j<=4i;j++){\n");
        xml_.append("   res+=j;\n");
        xml_.append("  }\n");
        xml_.append("  $if (res != 10i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var arr = {};\n");
        xml_.append("  $if (arr.length != 0i){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($int[])){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument5FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int arr = {}.length;\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument6FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int res = 0i;\n");
        xml_.append("  $for($var ([j])){\n");
        xml_.append("   $break;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument7FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo arr = $new(10);\n");
        xml_.append("  $if (arr.field != 10){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument8FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo arr = $new(\"10\");\n");
        xml_.append("  $if (arr.field != 10){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument9FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo[] arr = $new(10);\n");
        xml_.append("  $if (arr[0].field != 10){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument10FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo arr = {$new(10)};\n");
        xml_.append("  $if (arr[0].field != 10){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument11FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo[][] arr = $new[1];\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument12FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo[] arr = $new[1][];\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument13FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var arr = $new(10);\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument14FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var arr = $new[10];\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument15FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex[] arr = $new ExTwo<>[1];\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument16FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<$int> arr = $new ExTwo<>{};\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument17FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<$int> arr = $new ExTwo<>[]{};\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument18FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<$int>[][] arr = $new ExTwo<>[]{};\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument19FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  ExTwo<$int>[] arr = $new ExThree<>[]{};\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExThree(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument20FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var arr = $new ExThree<>[]{};\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExThree(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument21FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $var arr = $new Inexist<>[]{};\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExThree(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument22FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $new Inexist<>[]{};\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExThree(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument23FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr($new ExTwo<>(10))){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr($int... e){\n");
        xml_.append("  $return $new ExTwo<>[]{$new ExTwo<>(e[0])};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument24FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($new ExTwo<>(10));\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree($int e){\n");
        xml_.append("  arr=$new ExTwo<>[]{};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument25FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($new ExTwo<>(10));\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree($int... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument26FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($new ExTwo<$int>[]{$new ExTwo<>(10)});\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree($int... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument27FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($new ExTwo<$int>[][]{$new ExTwo<$int>[]{$new ExTwo<>(10)}});\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree($int... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument28FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return catching2()[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] catching2(){\n");
        xml_.append("  ExThree<$int> pass = $new ExThree<>($new ExTwo<>[][]{$new ExTwo<$int>[]{$new ExTwo<>(10)}});\n");
        xml_.append("  $return pass.arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public ExTwo<S>[] arr;\n");
        xml_.append(" $public ExThree($int... e){\n");
        xml_.append("  arr=$new ExTwo<>[]{};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument29FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $for (ExTwo<$int> e: arr($new ExTwo<>[]{$new ExTwo<>(10)})){\n");
        xml_.append("   $return e.field;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int>[] arr($int... e){\n");
        xml_.append("  $return $new ExTwo<>[]{$new ExTwo<>(e[0])};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public ExTwo(T p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    private static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<U> :$iterable<U>{\n");
        xml_.append(" $private U[] list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new U[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(U elt){\n");
        xml_.append("  add(length,elt);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,U elt){\n");
        xml_.append("  U[] newlist=$new U[length+1i];\n");
        xml_.append("  $iter($int i=0i;index;1i){\n");
        xml_.append("   newlist[i]=list[i];\n");
        xml_.append("  }\n");
        xml_.append("  newlist[index]=elt;\n");
        xml_.append("  $iter($int i=index+1i;length+1i;1i){\n");
        xml_.append("   newlist[i]=list[i-1i];\n");
        xml_.append("  }\n");
        xml_.append("  length++;\n");
        xml_.append("  list=newlist;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int size(){\n");
        xml_.append("  $return length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U get($int index){\n");
        xml_.append("  $return list[index];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,U elt){\n");
        xml_.append("  list[index]=elt;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;length-1i;1i){\n");
        xml_.append("   list[i]=list[i+1i];\n");
        xml_.append("  }\n");
        xml_.append("  list[length-1i]=$null;\n");
        xml_.append("  length--;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<U> iterator(){\n");
        xml_.append("  $return $new pkg.CustIter<U>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<T> :$iterator<T>{\n");
        xml_.append(" $private pkg.CustList<T> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public (pkg.CustList<T> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T next(){\n");
        xml_.append("  T out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
}
