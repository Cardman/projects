package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.sample.CustLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
import code.util.core.IndexConstants;
import org.junit.Test;

public final class CallersRefTest extends ProcessMethodCommon {
    @Test
    public void refs1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){\n");
        xml_.append("$iter($int i=0;1;1){\n");
        xml_.append("$return ([i]);\n");
        xml_.append("}\n");
        xml_.append("$return 1;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",91);
        assertEq(1, variablesParamsUseSize(r_));
        assertEq(89, variablesParamsUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", variablesParamsUseElt(r_, 0).getFile().getFileName());
        assertEq(71, variablesParamsUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", variablesParamsUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(51, variablesParamsUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", variablesParamsUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){\n");
        xml_.append("$iter($int i=0;1;1){\n");
        xml_.append("$return (:$int)->{$iter($int i=0;1;1){$return ([#i])+([i]);}$return 1;}.call();\n");
        xml_.append("}\n");
        xml_.append("$return 1;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",129);
        assertEq(1, variablesParamsUseSize(r_));
        assertEq(127, variablesParamsUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", variablesParamsUseElt(r_, 0).getFile().getFileName());
        assertEq(71, variablesParamsUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", variablesParamsUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(96, variablesParamsUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", variablesParamsUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=0;};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",45);
        assertFalse(r_.contains(CallerKind.VARIABLES));
    }
    @Test
    public void refs4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=0,b=a;};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",51);
        assertEq(1, variablesParamsUseSize(r_));
        assertEq(51, variablesParamsUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getFile().getFileName());
        assertEq(45, variablesParamsUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, variablesParamsUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=0,b=(:$int)->{$int a=0;$return #a+a;}.call();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",78);
        assertEq(1, variablesParamsUseSize(r_));
        assertEq(78, variablesParamsUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getFile().getFileName());
        assertEq(45, variablesParamsUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(58, variablesParamsUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]++;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",57);
        assertEq(1, variablesParamsUseSize(r_));
        assertEq(57, variablesParamsUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getFile().getFileName());
        assertEq(132, variablesParamsUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", variablesParamsUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, variablesParamsUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]++;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",53);
        assertEq(1, variablesParamsUseSize(r_));
        assertEq(53, variablesParamsUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getFile().getFileName());
        assertEq(125, variablesParamsUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", variablesParamsUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, variablesParamsUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=0;\n");
        xml_.append("$if(a==0){}};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",45);
        assertEq(1, variablesParamsUseSize(r_));
        assertEq(54, variablesParamsUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getFile().getFileName());
        assertEq(45, variablesParamsUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, variablesParamsUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=0;\n");
        xml_.append("$switch(a){$case $int b:a+b==0;$default b;}};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",45);
        assertEq(2, variablesParamsUseSize(r_));
        assertEq(58, variablesParamsUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getFile().getFileName());
        assertEq(45, variablesParamsUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, variablesParamsUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCaller().getFile().getFileName());
        assertEq(74, variablesParamsUseElt(r_, 1).getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 1).getFile().getFileName());
        assertEq(45, variablesParamsUseElt(r_, 1).getCallee().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 1).getCallee().getFile().getFileName());
        assertEq(38, variablesParamsUseElt(r_, 1).getCaller().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 1).getCaller().getFile().getFileName());
    }
    @Test
    public void refs10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=([b]);};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",47);
        assertFalse(r_.contains(CallerKind.VARIABLES));
    }
    @Test
    public void refs11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int #a;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",45);
        assertFalse(r_.contains(CallerKind.VARIABLES));
    }
    @Test
    public void refs12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$for($int a=0;;){}};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",50);
        assertFalse(r_.contains(CallerKind.VARIABLES));
    }
    @Test
    public void refs13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$for($int a:{}){}};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",50);
        assertFalse(r_.contains(CallerKind.VARIABLES));
    }
    @Test
    public void refs14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$for($int a,$int b:{}){$throw $null;}};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",50);
        assertFalse(r_.contains(CallerKind.VARIABLES));
    }
    @Test
    public void refs15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int b=$switch(a){$case $int b:a+b==0;$return 0;$default b;$return 0;};};\n");
        xml_.append("$static{}\n");
        xml_.append("Outer2(){}\n");
        xml_.append("}\n");
        xml_.append("$operator/ pkg.Outer(pkg.Outer a, pkg.Outer b){$return $null;}\n");
        xml_.append("$public $annotation pkg.Annot{$int meth();}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",45);
        assertFalse(r_.contains(CallerKind.VARIABLES));
    }
    @Test
    public void refs16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",57);
        assertEq(1, variablesParamsUseSize(r_));
        assertEq(57, variablesParamsUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getFile().getFileName());
        assertEq(164, variablesParamsUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", variablesParamsUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, variablesParamsUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",53);
        assertEq(1, variablesParamsUseSize(r_));
        assertEq(53, variablesParamsUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getFile().getFileName());
        assertEq(171, variablesParamsUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", variablesParamsUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, variablesParamsUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field,field2;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",53);
        assertEq(1, variablesParamsUseSize(r_));
        assertEq(53, variablesParamsUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getFile().getFileName());
        assertEq(171, variablesParamsUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", variablesParamsUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, variablesParamsUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", variablesParamsUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs19() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field,field2=field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex3",50);
        assertEq(1, fieldsUse(r_).size());
        assertEq(50, fieldsUse(r_).get(0).getIndex());
        assertEq("pkg/Ex3", fieldsUse(r_).get(0).getFile().getFileName());
        assertEq(37, fieldsUse(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", fieldsUse(r_).get(0).getCallee().getFile().getFileName());
        assertEq(43, fieldsUse(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex3", fieldsUse(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs20() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field=field2,field2;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex3",43);
        assertEq(1, fieldsUse(r_).size());
        assertEq(43, fieldsUse(r_).get(0).getIndex());
        assertEq("pkg/Ex3", fieldsUse(r_).get(0).getFile().getFileName());
        assertEq(50, fieldsUse(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", fieldsUse(r_).get(0).getCallee().getFile().getFileName());
        assertEq(37, fieldsUse(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex3", fieldsUse(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs21() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field,=field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex3",43);
        assertFalse(r_.contains(CallerKind.FIELD));
    }
    @Test
    public void refs22() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field,field2=inex;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex3",50);
        assertFalse(r_.contains(CallerKind.FIELD));
    }
    @Test
    public void refs23() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$lambda(Outer2,,ONE);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",40);
        assertEq(1, fieldsRefUseSize(r_));
        assertEq(56, fieldsRefUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", fieldsRefUseElt(r_, 0).getFile().getFileName());
        assertEq(27, fieldsRefUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex2", fieldsRefUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, fieldsRefUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", fieldsRefUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs24() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class pkg.Outer:Outer3 {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$lambda(Outer,$new,Outer3.field);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",66);
        assertEq(1, fieldsRefUseSize(r_));
        assertEq(66, fieldsRefUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", fieldsRefUseElt(r_, 0).getFile().getFileName());
        assertEq(37, fieldsRefUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex3", fieldsRefUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, fieldsRefUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", fieldsRefUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs25() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class pkg.Outer:Outer3 {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$lambda(Outer,$new,Outer3.inex);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",66);
        assertFalse(r_.contains(CallerKind.FIELD_REF));
    }
    @Test
    public void refs26() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class pkg.Outer:Outer3 {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer(Outer3.field:0);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",58);
        assertEq(1, fieldsUseInit(r_).size());
        assertEq(58, fieldsUseInit(r_).get(0).getIndex());
        assertEq("pkg/Ex2", fieldsUseInit(r_).get(0).getFile().getFileName());
        assertEq(37, fieldsUseInit(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", fieldsUseInit(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, fieldsUseInit(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", fieldsUseInit(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs27() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field,field2=Short.MIN_VALUE+Short.MAX_VALUE+field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex3",56);
        assertEq(1, fieldsUse(r_).size());
        assertEq(56, fieldsUse(r_).get(0).getIndex());
        assertEq("pkg/Ex3", fieldsUse(r_).get(0).getFile().getFileName());
        assertEq(0, fieldsUse(r_).get(0).getCallee().getIndex());
        assertEq("", fieldsUse(r_).get(0).getCallee().getFileName());
        assertEq(43, fieldsUse(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex3", fieldsUse(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs28() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field,field2=Short.MIN_VALUE+Short.MAX_VALUE+field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex3",82);
        assertEq(1, fieldsUse(r_).size());
        assertEq(82, fieldsUse(r_).get(0).getIndex());
        assertEq("pkg/Ex3", fieldsUse(r_).get(0).getFile().getFileName());
        assertEq(37, fieldsUse(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", fieldsUse(r_).get(0).getCallee().getFile().getFileName());
        assertEq(43, fieldsUse(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex3", fieldsUse(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs29() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("callee();\n");
        xml_.append("}\n");
        xml_.append("$public $static $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",60);
        assertEq(1, callNamedUseSize(r_));
        assertEq(59, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(93, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(49, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs30() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("$int i=0;\n");
        xml_.append("$int j=\"\".indexOf(' ',i);\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",81);
        assertEq(1, callNamedUseSize(r_));
        assertEq(79, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(0, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("", callNamedUseElt(r_, 0).getCallee().getFileName());
        assertEq(49, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs31() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("$int i=0;\n");
        xml_.append("$int j=\"\".indexOf(' ',i);\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",49);
        assertFalse(r_.contains(CallerKind.NAME));
    }
    @Test
    public void refs32() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("$int i=0;\n");
        xml_.append("$int j=\"\".indexOf(' ',i),k=\"\".indexOf(' ');\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",81);
        assertEq(1, callNamedUseSize(r_));
        assertEq(79, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(0, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("", callNamedUseElt(r_, 0).getCallee().getFileName());
        assertEq(49, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs33() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class pkg.Outer:Outer3 {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$lambda(Outer,THREE);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",54);
        assertEq(1, callNamedRefUse(r_).size());
        assertEq(54, callNamedRefUse(r_).get(0).getIndex());
        assertEq("pkg/Ex2", callNamedRefUse(r_).get(0).getFile().getFileName());
        assertEq(48, callNamedRefUse(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedRefUse(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, callNamedRefUse(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", callNamedRefUse(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs34() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void method(){\n");
        xml_.append("callee();\n");
        xml_.append("}\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",52);
        assertEq(1, callNamedUseSize(r_));
        assertEq(51, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(77, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(41, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
        assertEq(1, callNamedUsePoly(r_).size());
        assertEq(51, callNamedUsePoly(r_).get(0).getIndex());
        assertEq("pkg/Ex", callNamedUsePoly(r_).get(0).getFile().getFileName());
        assertEq(77, callNamedUsePoly(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUsePoly(r_).get(0).getCallee().getFile().getFileName());
        assertEq(41, callNamedUsePoly(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUsePoly(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs35() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void method(){\n");
        xml_.append("callee();\n");
        xml_.append("}\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2:Outer {\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",48);
        assertEq(1, callNamedUsePoly(r_).size());
        assertEq(51, callNamedUsePoly(r_).get(0).getIndex());
        assertEq("pkg/Ex", callNamedUsePoly(r_).get(0).getFile().getFileName());
        assertEq(48, callNamedUsePoly(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", callNamedUsePoly(r_).get(0).getCallee().getFile().getFileName());
        assertEq(41, callNamedUsePoly(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUsePoly(r_).get(0).getCaller().getFile().getFileName());
        assertFalse(r_.contains(CallerKind.NAME));
        assertFalse(allNamedOverridden(r_).isEmpty());
        assertFalse(callNamedOverriding(r_).isEmpty());
    }
    @Test
    public void refs36() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void method(){\n");
        xml_.append("$that.callee();\n");
        xml_.append("}\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",58);
        assertEq(1, callNamedUseSize(r_));
        assertEq(57, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(83, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(41, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
        assertFalse(r_.contains(CallerKind.NAME_POLY));
    }
    @Test
    public void refs37() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void callee(){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer3.f.$lambda(Outer,$that,callee);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3:Outer {\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("$static Outer f;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",69);
        assertEq(1, callNamedRefUse(r_).size());
        assertEq(69, callNamedRefUse(r_).get(0).getIndex());
        assertEq("pkg/Ex2", callNamedRefUse(r_).get(0).getFile().getFileName());
        assertEq(41, callNamedRefUse(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedRefUse(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, callNamedRefUse(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", callNamedRefUse(r_).get(0).getCaller().getFile().getFileName());
        assertFalse(r_.contains(CallerKind.NAME_REF_POLY));
    }
    @Test
    public void refs38() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void callee(){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer3.f.$lambda(Outer,callee);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3:Outer {\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("$static Outer f;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex3",48);
        assertEq(1, callNamedRefUsePoly(r_).size());
        assertEq(63, callNamedRefUsePoly(r_).get(0).getIndex());
        assertEq("pkg/Ex2", callNamedRefUsePoly(r_).get(0).getFile().getFileName());
        assertEq(48, callNamedRefUsePoly(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", callNamedRefUsePoly(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, callNamedRefUsePoly(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", callNamedRefUsePoly(r_).get(0).getCaller().getFile().getFileName());
        assertFalse(r_.contains(CallerKind.NAME_REF));
    }
    @Test
    public void refs39() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void callee(){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer3.f.$lambda(Outer,callee);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3:Outer {\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("$static Outer f;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",63);
        assertEq(1, callNamedRefUsePoly(r_).size());
        assertEq(63, callNamedRefUsePoly(r_).get(0).getIndex());
        assertEq("pkg/Ex2", callNamedRefUsePoly(r_).get(0).getFile().getFileName());
        assertEq(41, callNamedRefUsePoly(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedRefUsePoly(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, callNamedRefUsePoly(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", callNamedRefUsePoly(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, callNamedRefUse(r_).size());
        assertEq(63, callNamedRefUse(r_).get(0).getIndex());
        assertEq("pkg/Ex2", callNamedRefUse(r_).get(0).getFile().getFileName());
        assertEq(41, callNamedRefUse(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedRefUse(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, callNamedRefUse(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", callNamedRefUse(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs40() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void method(){\n");
        xml_.append("callee();\n");
        xml_.append("}\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2:Outer {\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",51);
        assertEq(1, callNamedUsePoly(r_).size());
        assertEq(51, callNamedUsePoly(r_).get(0).getIndex());
        assertEq("pkg/Ex", callNamedUsePoly(r_).get(0).getFile().getFileName());
        assertEq(77, callNamedUsePoly(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUsePoly(r_).get(0).getCallee().getFile().getFileName());
        assertEq(41, callNamedUsePoly(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUsePoly(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, callNamedUseSize(r_));
        assertEq(51, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(77, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(41, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs41() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer<$int> v=$new();\n");
        xml_.append("v[$id(Outer,T,[]=,Outer,T),0]++;\n");
        xml_.append("}\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2<T>:Outer<T> {\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",54);
        assertFalse(r_.contains(CallerKind.NAME));
        assertEq(1, callNamedUsePoly(r_).size());
        assertEq(85, callNamedUsePoly(r_).get(0).getIndex());
        assertEq("pkg/Ex", callNamedUsePoly(r_).get(0).getFile().getFileName());
        assertEq(54, callNamedUsePoly(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", callNamedUsePoly(r_).get(0).getCallee().getFile().getFileName());
        assertEq(52, callNamedUsePoly(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUsePoly(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs42() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer<$int> v=$new();\n");
        xml_.append("v[$id(Outer,T,[]=,Outer,T),0]++;\n");
        xml_.append("}\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2<T>:Outer<T> {\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",81);
        assertFalse(r_.contains(CallerKind.NAME));
        assertEq(1, callNamedUsePoly(r_).size());
        assertEq(85, callNamedUsePoly(r_).get(0).getIndex());
        assertEq("pkg/Ex", callNamedUsePoly(r_).get(0).getFile().getFileName());
        assertEq(81, callNamedUsePoly(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", callNamedUsePoly(r_).get(0).getCallee().getFile().getFileName());
        assertEq(52, callNamedUsePoly(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUsePoly(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs43() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer<$int> v=$new();\n");
        xml_.append("v.$that[$id(Outer,T,[]=,Outer,T),0]++;\n");
        xml_.append("}\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2<T>:Outer<T> {\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",91);
        assertFalse(r_.contains(CallerKind.NAME_POLY));
        assertEq(2, callNamedUseSize(r_));
        assertEq(91, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(166, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(52, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
        assertEq(91, callNamedUseElt(r_, 1).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 1).getFile().getFileName());
        assertEq(139, callNamedUseElt(r_, 1).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 1).getCallee().getFile().getFileName());
        assertEq(52, callNamedUseElt(r_, 1).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 1).getCaller().getFile().getFileName());
    }
    @Test
    public void refs44() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$static $boolean $(Outer o){$return $true;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$for(;;)label{$break label;}}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",48);
        assertEq(1, labels(r_).size());
        assertEq(60, labels(r_).get(0).getIndex());
        assertEq("pkg/Ex2", labels(r_).get(0).getFile().getFileName());
        assertEq(48, labels(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", labels(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, labels(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", labels(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs45() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$static $boolean $(Outer o){$return $true;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$for(;;)label{$continue label;}}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",48);
        assertEq(1, labels(r_).size());
        assertEq(63, labels(r_).get(0).getIndex());
        assertEq("pkg/Ex2", labels(r_).get(0).getFile().getFileName());
        assertEq(48, labels(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", labels(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, labels(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", labels(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs46() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[0]++;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",43);
        assertEq(1, callNamedUseSize(r_));
        assertEq(43, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(54, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(34, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs47() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Object o=$(Outer,Outer2)$null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",49);
        assertEq(1, callNamedUseSize(r_));
        assertEq(49, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(48, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs48() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$operator- $staticCall Outer(Outer o,Outer p){$return $null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer a=$operator(-,Outer)(a,Outer.ONE);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",48);
        assertEq(1, callNamedUseSize(r_));
        assertEq(48, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(80, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs49() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$operator- $staticCall Outer(Outer o,Outer p){$return $null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer a=a-Outer.ONE;$int b=a==a?0:1;a-=Outer.ONE;a&&a;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",49);
        assertEq(2, callNamedUseSize(r_));
        assertEq(49, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(80, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
        assertEq(77, callNamedUseElt(r_, 1).getIndex());
        assertEq("pkg/Ex2", callNamedUseElt(r_, 1).getFile().getFileName());
        assertEq(80, callNamedUseElt(r_, 1).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 1).getCallee().getFile().getFileName());
        assertEq(38, callNamedUseElt(r_, 1).getCaller().getIndex());
        assertEq("pkg/Ex2", callNamedUseElt(r_, 1).getCaller().getFile().getFileName());
    }
    @Test
    public void refs50() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer2 n=$null;Outer o=n;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",63);
        assertEq(1, callNamedUseImpl(r_).size());
        assertEq(63, callNamedUseImpl(r_).get(0).getIndex());
        assertEq("pkg/Ex2", callNamedUseImpl(r_).get(0).getFile().getFileName());
        assertEq(48, callNamedUseImpl(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseImpl(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, callNamedUseImpl(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", callNamedUseImpl(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs51() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer2 n=$null;Outer o=n;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("{Outer2 n=$null;Outer o=n;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",63);
        assertEq(2, r_.getVal(CallerKind.NAME_IMPL).size());
    }
    @Test
    public void refs52() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer o=$new();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",48);
        assertEq(1, instanceNewTypes(r_).size());
        assertEq(48, instanceNewTypes(r_).get(0).getIndex());
        assertEq("pkg/Ex2", instanceNewTypes(r_).get(0).getFile().getFileName());
        assertEq(15, instanceNewTypes(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", instanceNewTypes(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, instanceNewTypes(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", instanceNewTypes(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs53() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer o=$lambda(Outer,$new);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",62);
        assertEq(1, instanceNewTypesRef(r_).size());
        assertEq(62, instanceNewTypesRef(r_).get(0).getIndex());
        assertEq("pkg/Ex2", instanceNewTypesRef(r_).get(0).getFile().getFileName());
        assertEq(15, instanceNewTypesRef(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", instanceNewTypesRef(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, instanceNewTypesRef(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", instanceNewTypesRef(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs54() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer2 {\n");
        xml_.append("$public Outer(){$super();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",50);
        assertEq(1, instanceNewTypesFwd(r_).size());
        assertEq(50, instanceNewTypesFwd(r_).get(0).getIndex());
        assertEq("pkg/Ex", instanceNewTypesFwd(r_).get(0).getFile().getFileName());
        assertEq(15, instanceNewTypesFwd(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", instanceNewTypesFwd(r_).get(0).getCallee().getFile().getFileName());
        assertEq(34, instanceNewTypesFwd(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", instanceNewTypesFwd(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs55() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(f=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",33);
        assertEq(1, callNamedFieldUseSize(r_));
        assertEq(33, callNamedFieldUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedFieldUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(26, callNamedFieldUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCaller().getFile().getFileName());
        assertEq(1, callNamedUseSize(r_));
        assertEq(216, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(143, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs56() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(f=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations($class(Annot))[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",33);
        assertEq(1, callNamedFieldUseSize(r_));
        assertEq(33, callNamedFieldUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedFieldUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(26, callNamedFieldUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs57() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(f=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations($class(SecAnnot))[0]).f();\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.SecAnnot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",33);
        assertFalse(r_.contains(CallerKind.ANNOT_INIT_MEMBER));
        assertFalse(r_.contains(CallerKind.INTERFACES_INIT));
        assertFalse(r_.contains(CallerKind.INTERFACES_INIT_REF));
    }
    @Test
    public void refs58() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(f=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations(c)[0]).f();\n");
        xml_.append("$Class c=$class(Annot);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",33);
        assertEq(1, callNamedFieldUseSize(r_));
        assertEq(33, callNamedFieldUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedFieldUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(26, callNamedFieldUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs59() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("(@Annot(f=2)$int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredConstructors()[0].getAnnotationsParameters()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",51);
        assertEq(1, callNamedFieldUseSize(r_));
        assertEq(51, callNamedFieldUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedFieldUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(44, callNamedFieldUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCaller().getFile().getFileName());
        assertEq(1, callNamedUseSize(r_));
        assertEq(227, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(143, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs60() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("(@Annot(f=2)$int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredConstructors()[0].getAnnotationsParameters($class(Annot))[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",51);
        assertEq(1, callNamedFieldUseSize(r_));
        assertEq(51, callNamedFieldUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedFieldUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(44, callNamedFieldUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCaller().getFile().getFileName());
        assertEq(1, callNamedUseSize(r_));
        assertEq(240, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(143, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs61() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("$void $this($int i,@Annot(f=2)$int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredMethods()[0].getAnnotationsSupp()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",69);
        assertEq(1, callNamedFieldUseSize(r_));
        assertEq(69, callNamedFieldUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getFile().getFileName());
        assertEq(127, callNamedFieldUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(62, callNamedFieldUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCaller().getFile().getFileName());
        assertEq(1, callNamedUseSize(r_));
        assertEq(239, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(127, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(166, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs62() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("$void $this($int i,@Annot(f=2)$int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredMethods()[0].getAnnotationsSupp($class(Annot))[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",69);
        assertEq(1, callNamedFieldUseSize(r_));
        assertEq(69, callNamedFieldUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getFile().getFileName());
        assertEq(127, callNamedFieldUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(62, callNamedFieldUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCaller().getFile().getFileName());
        assertEq(1, callNamedUseSize(r_));
        assertEq(252, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(127, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(166, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs63() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("$void $this($int i,$int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("Annot g()@Annot(f=2);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Annot).getDeclaredMethods()[0].getDefaultValue()).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",137);
        assertEq(1, callNamedFieldUseSize(r_));
        assertEq(137, callNamedFieldUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getFile().getFileName());
        assertEq(116, callNamedFieldUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(127, callNamedFieldUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCaller().getFile().getFileName());
        assertEq(1, callNamedUseSize(r_));
        assertEq(244, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(116, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(177, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs64() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int g=$switch[$int:@Annot(f=2):@Annot(f=2)](0){$default;$return 0;};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",97);
        assertEq(2, callNamedFieldUseSizeAll(r_));
        assertEq(1, callNamedFieldUseSize(r_, 0));
        assertEq(97, callNamedFieldUseElt(r_, 0,0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0,0).getFile().getFileName());
        assertEq(36, callNamedFieldUseElt(r_, 0,0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0,0).getCallee().getFile().getFileName());
        assertEq(90, callNamedFieldUseElt(r_, 0,0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0,0).getCaller().getFile().getFileName());
        assertEq(1, callNamedFieldUseSize(r_, 1));
        assertEq(109, callNamedFieldUseElt(r_, 1,0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 1,0).getFile().getFileName());
        assertEq(36, callNamedFieldUseElt(r_, 1,0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 1,0).getCallee().getFile().getFileName());
        assertEq(102, callNamedFieldUseElt(r_, 1,0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 1,0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs65() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int g=$switch[$int:@Annot(e=2):@Annot(e=2)](0){$default;$return 0;};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",97);
        assertFalse(r_.contains(CallerKind.NAME_FIELD));
    }
    @Test
    public void refs66() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(f=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations($null)[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",33);
        assertEq(1, callNamedFieldUseSize(r_));
        assertEq(33, callNamedFieldUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedFieldUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(26, callNamedFieldUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs67() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@Annot(f=2)\n");
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",7);
        assertEq(1, callNamedFieldUseSize(r_));
        assertEq(7, callNamedFieldUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedFieldUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(0, callNamedFieldUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCaller().getFile().getFileName());
        assertEq(1, callNamedUseSize(r_));
        assertEq(188, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(143, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs68() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@Annot(f=2)\n");
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",7);
        assertEq(1, callNamedFieldUseSize(r_));
        assertEq(7, callNamedFieldUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedFieldUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(0, callNamedFieldUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedFieldUseElt(r_, 0).getCaller().getFile().getFileName());
        assertFalse(r_.contains(CallerKind.ANNOT_INIT_MEMBER));
        assertEq(1, callNamedUseSize(r_));
        assertEq(216, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(104, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(143, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs69() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$Fct<Outer2> fct;\n");
        xml_.append("{fct=$lambda(Outer2,,ONE);}\n");
        xml_.append("{fct.call();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",78);
        assertEq(1, dynCallPotential(r_).size());
        assertEq(90, dynCallPotential(r_).get(0).getIndex());
        assertEq("pkg/Ex2", dynCallPotential(r_).get(0).getFile().getFileName());
        assertEq(27, dynCallPotential(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", dynCallPotential(r_).get(0).getCallee().getFile().getFileName());
        assertEq(84, dynCallPotential(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", dynCallPotential(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs70() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$Fct<Outer2> fct;\n");
        xml_.append("{fct=$lambda(Outer2,,ONE);}\n");
        xml_.append("$Fct<Outer> fct2;\n");
        xml_.append("{fct2.call();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",78);
        assertFalse(r_.contains(CallerKind.DYN_CALL_POT));
    }
    @Test
    public void refs71() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$Fct fct;\n");
        xml_.append("{fct=$lambda(Outer2,,ONE);}\n");
        xml_.append("{fct.call();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",70);
        assertEq(1, dynCallPotential(r_).size());
        assertEq(82, dynCallPotential(r_).get(0).getIndex());
        assertEq("pkg/Ex2", dynCallPotential(r_).get(0).getFile().getFileName());
        assertEq(27, dynCallPotential(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", dynCallPotential(r_).get(0).getCallee().getFile().getFileName());
        assertEq(76, dynCallPotential(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", dynCallPotential(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs72() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$Fct<Outer2> fct;\n");
        xml_.append("{fct=$lambda(Outer2,,ONE);}\n");
        xml_.append("$Fct<Outer> fct2;\n");
        xml_.append("{fct2.inex();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",78);
        assertFalse(r_.contains(CallerKind.DYN_CALL_POT));
    }
    @Test
    public void refs73() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$Fct<Outer2> fct;\n");
        xml_.append("{fct=$lambda(Outer2,,ONE);}\n");
        xml_.append("$Fct<Outer> fct2;\n");
        xml_.append("{fct2.metaInfo();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",78);
        assertFalse(r_.contains(CallerKind.DYN_CALL_POT));
    }
    @Test
    public void refs74() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2<T> {\n");
        xml_.append("ONE<$int>,\n");
        xml_.append("TWO<$int>{};\n");
        xml_.append("$Fct<T,Outer> fct;\n");
        xml_.append("{fct.call($null);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",78);
        assertFalse(r_.contains(CallerKind.DYN_CALL_POT));
    }
    @Test
    public void refs75() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2<T> {\n");
        xml_.append("ONE<$int>,\n");
        xml_.append("TWO<$int>{};\n");
        xml_.append("$Fct<Outer,T> fct;\n");
        xml_.append("{fct.call($null);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",78);
        assertFalse(r_.contains(CallerKind.DYN_CALL_POT));
    }
    @Test
    public void refs76() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2<T> {\n");
        xml_.append("ONE<$int>,\n");
        xml_.append("TWO<$int>{};\n");
        xml_.append("$Fct<Outer,Outer2<T>> fct;\n");
        xml_.append("{fct.call($null);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",78);
        assertFalse(r_.contains(CallerKind.DYN_CALL_POT));
    }
    @Test
    public void refs77() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2<T> {\n");
        xml_.append("ONE<$int>,\n");
        xml_.append("TWO<$int>{};\n");
        xml_.append("$Fct<Outer,$Fct<T>> fct;\n");
        xml_.append("{fct.call($null);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",78);
        assertFalse(r_.contains(CallerKind.DYN_CALL_POT));
    }
    @Test
    public void refs78() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2<T> {\n");
        xml_.append("ONE<$int>,\n");
        xml_.append("TWO<$int>{};\n");
        xml_.append("$Fct<Outer,~$Fct<T>> fct;\n");
        xml_.append("{fct.call($null);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",78);
        assertFalse(r_.contains(CallerKind.DYN_CALL_POT));
    }
    @Test
    public void refs79() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class pkg.Outer:Outer3 {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$Fct<$byte,Outer> fct;\n");
        xml_.append("{fct=$lambda(Outer,$new,Outer3.field);}\n");
        xml_.append("{fct.call(1y);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",93);
        assertEq(1, dynCallPotential(r_).size());
        assertEq(107, dynCallPotential(r_).get(0).getIndex());
        assertEq("pkg/Ex2", dynCallPotential(r_).get(0).getFile().getFileName());
        assertEq(37, dynCallPotential(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", dynCallPotential(r_).get(0).getCallee().getFile().getFileName());
        assertEq(101, dynCallPotential(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", dynCallPotential(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs80() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(f=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",27);
        assertEq(1, annotCandidatesCallsInitMembers(r_).size());
        assertEq(195, annotCandidatesCallsInitMembers(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getFile().getFileName());
        assertEq(88, annotCandidatesCallsInitMembers(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitMembers(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, annotCandidatesCallsInitArobaseMembers(r_).size());
        assertEq(195, annotCandidatesCallsInitArobaseMembers(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getFile().getFileName());
        assertEq(26, annotCandidatesCallsInitArobaseMembers(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitArobaseMembers(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs81() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(f=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations($class(Annot))[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",27);
        assertEq(1, annotCandidatesCallsInitMembers(r_).size());
        assertEq(195, annotCandidatesCallsInitMembers(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getFile().getFileName());
        assertEq(88, annotCandidatesCallsInitMembers(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitMembers(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, annotCandidatesCallsInitArobaseMembers(r_).size());
        assertEq(195, annotCandidatesCallsInitArobaseMembers(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getFile().getFileName());
        assertEq(26, annotCandidatesCallsInitArobaseMembers(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitArobaseMembers(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs82() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(f=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations(c)[0]).f();\n");
        xml_.append("$Class c=$class(Annot);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",27);
        assertEq(1, annotCandidatesCallsInitMembers(r_).size());
        assertEq(195, annotCandidatesCallsInitMembers(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getFile().getFileName());
        assertEq(88, annotCandidatesCallsInitMembers(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitMembers(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, annotCandidatesCallsInitArobaseMembers(r_).size());
        assertEq(195, annotCandidatesCallsInitArobaseMembers(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getFile().getFileName());
        assertEq(26, annotCandidatesCallsInitArobaseMembers(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitArobaseMembers(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs83() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("(@Annot(f=2)$int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredConstructors()[0].getAnnotationsParameters()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",45);
        assertEq(1, annotCandidatesCallsInitParameters(r_).size());
        assertEq(196, annotCandidatesCallsInitParameters(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitParameters(r_).get(0).getFile().getFileName());
        assertEq(88, annotCandidatesCallsInitParameters(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitParameters(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitParameters(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitParameters(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, annotCandidatesCallsInitArobaseParameters(r_).size());
        assertEq(196, annotCandidatesCallsInitArobaseParameters(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseParameters(r_).get(0).getFile().getFileName());
        assertEq(44, annotCandidatesCallsInitArobaseParameters(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseParameters(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitArobaseParameters(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseParameters(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs84() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("(@Annot(f=2)$int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredConstructors()[0].getAnnotationsParameters($class(Annot))[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",45);
        assertEq(1, annotCandidatesCallsInitParameters(r_).size());
        assertEq(196, annotCandidatesCallsInitParameters(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitParameters(r_).get(0).getFile().getFileName());
        assertEq(88, annotCandidatesCallsInitParameters(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitParameters(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitParameters(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitParameters(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, annotCandidatesCallsInitArobaseParameters(r_).size());
        assertEq(196, annotCandidatesCallsInitArobaseParameters(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseParameters(r_).get(0).getFile().getFileName());
        assertEq(44, annotCandidatesCallsInitArobaseParameters(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseParameters(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitArobaseParameters(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseParameters(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs85() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("$void $this($int i,@Annot(f=2)$int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredMethods()[0].getAnnotationsSupp()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",63);
        assertEq(1, annotCandidatesCallsInitSuppl(r_).size());
        assertEq(214, annotCandidatesCallsInitSuppl(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitSuppl(r_).get(0).getFile().getFileName());
        assertEq(111, annotCandidatesCallsInitSuppl(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitSuppl(r_).get(0).getCallee().getFile().getFileName());
        assertEq(166, annotCandidatesCallsInitSuppl(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitSuppl(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, annotCandidatesCallsInitArobaseSuppl(r_).size());
        assertEq(214, annotCandidatesCallsInitArobaseSuppl(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseSuppl(r_).get(0).getFile().getFileName());
        assertEq(62, annotCandidatesCallsInitArobaseSuppl(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseSuppl(r_).get(0).getCallee().getFile().getFileName());
        assertEq(166, annotCandidatesCallsInitArobaseSuppl(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseSuppl(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs86() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("$void $this($int i,@Annot(f=2)$int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredMethods()[0].getAnnotationsSupp($class(Annot))[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",63);
        assertEq(1, annotCandidatesCallsInitSuppl(r_).size());
        assertEq(214, annotCandidatesCallsInitSuppl(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitSuppl(r_).get(0).getFile().getFileName());
        assertEq(111, annotCandidatesCallsInitSuppl(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitSuppl(r_).get(0).getCallee().getFile().getFileName());
        assertEq(166, annotCandidatesCallsInitSuppl(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitSuppl(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, annotCandidatesCallsInitArobaseSuppl(r_).size());
        assertEq(214, annotCandidatesCallsInitArobaseSuppl(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseSuppl(r_).get(0).getFile().getFileName());
        assertEq(62, annotCandidatesCallsInitArobaseSuppl(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseSuppl(r_).get(0).getCallee().getFile().getFileName());
        assertEq(166, annotCandidatesCallsInitArobaseSuppl(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseSuppl(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs87() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("$void $this($int i,$int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("Annot g()@Annot(f=2);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Annot).getDeclaredMethods()[0].getDefaultValue()).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",131);
        assertEq(1, annotCandidatesCallsInitDefValue(r_).size());
        assertEq(225, annotCandidatesCallsInitDefValue(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitDefValue(r_).get(0).getFile().getFileName());
        assertEq(100, annotCandidatesCallsInitDefValue(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitDefValue(r_).get(0).getCallee().getFile().getFileName());
        assertEq(177, annotCandidatesCallsInitDefValue(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitDefValue(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, annotCandidatesCallsInitArobaseDefValue(r_).size());
        assertEq(225, annotCandidatesCallsInitArobaseDefValue(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseDefValue(r_).get(0).getFile().getFileName());
        assertEq(127, annotCandidatesCallsInitArobaseDefValue(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseDefValue(r_).get(0).getCallee().getFile().getFileName());
        assertEq(177, annotCandidatesCallsInitArobaseDefValue(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseDefValue(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs88() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(f=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations($null)[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",27);
        assertEq(1, annotCandidatesCallsInitMembers(r_).size());
        assertEq(195, annotCandidatesCallsInitMembers(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getFile().getFileName());
        assertEq(88, annotCandidatesCallsInitMembers(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitMembers(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, annotCandidatesCallsInitArobaseMembers(r_).size());
        assertEq(195, annotCandidatesCallsInitArobaseMembers(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getFile().getFileName());
        assertEq(26, annotCandidatesCallsInitArobaseMembers(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitArobaseMembers(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs89() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@Annot(f=2)\n");
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",1);
        assertEq(1, annotCandidatesCallsInitMembers(r_).size());
        assertEq(167, annotCandidatesCallsInitMembers(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getFile().getFileName());
        assertEq(88, annotCandidatesCallsInitMembers(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitMembers(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitMembers(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, annotCandidatesCallsInitArobaseMembers(r_).size());
        assertEq(167, annotCandidatesCallsInitArobaseMembers(r_).get(0).getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getFile().getFileName());
        assertEq(0, annotCandidatesCallsInitArobaseMembers(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getCallee().getFile().getFileName());
        assertEq(143, annotCandidatesCallsInitArobaseMembers(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", annotCandidatesCallsInitArobaseMembers(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs90() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@Annot(f=2)\n");
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",1);
        assertEq(1, instanceArobase(r_).size());
        assertEq(1, instanceArobase(r_).get(0).getIndex());
        assertEq("pkg/Ex", instanceArobase(r_).get(0).getFile().getFileName());
        assertEq(88, instanceArobase(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", instanceArobase(r_).get(0).getCallee().getFile().getFileName());
        assertEq(0, instanceArobase(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", instanceArobase(r_).get(0).getCaller().getFile().getFileName());
        assertSame(EnSrcLocation.ANNOTATION, instanceArobase(r_).get(0).getCaller().build(null).getKind());
    }
    @Test
    public void refs91() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("$staticCall(Outer2<String>).callee($id(Outer2,T),\"\");\n");
        xml_.append("}\n");
        xml_.append("$public $static $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2<T> {\n");
        xml_.append("$public $staticCall $void callee(T p){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",105);
        assertEq(1, typesFinders(r_).size());
        assertEq(105, typesFinders(r_).get(0).getIndex());
        assertEq("pkg/Ex", typesFinders(r_).get(0).getFile().getFileName());
        assertEq(26, typesFinders(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", typesFinders(r_).get(0).getCallee().getFile().getFileName());
        assertEq(49, typesFinders(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", typesFinders(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs92() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("callee($vararg(Outer<$int>),$firstopt($new()));\n");
        xml_.append("}\n");
        xml_.append("$public $static $void callee(Outer<$int>... i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",78);
        assertEq(1, typesFinders(r_).size());
        assertEq(77, typesFinders(r_).get(0).getIndex());
        assertEq("pkg/Ex", typesFinders(r_).get(0).getFile().getFileName());
        assertEq(15, typesFinders(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", typesFinders(r_).get(0).getCallee().getFile().getFileName());
        assertEq(52, typesFinders(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", typesFinders(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs93() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer.callee();\n");
        xml_.append("}\n");
        xml_.append("$public $static $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",60);
        assertEq(1, staticAccess(r_).size());
        assertEq(59, staticAccess(r_).get(0).getIndex());
        assertEq("pkg/Ex", staticAccess(r_).get(0).getFile().getFileName());
        assertEq(15, staticAccess(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", staticAccess(r_).get(0).getCallee().getFile().getFileName());
        assertEq(49, staticAccess(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", staticAccess(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs94() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $staticCall $void method(){\n");
        xml_.append("$staticCall(Outer<T>).callee();\n");
        xml_.append("}\n");
        xml_.append("$public $staticCall $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",84);
        assertEq(1, staticAccess(r_).size());
        assertEq(84, staticAccess(r_).get(0).getIndex());
        assertEq("pkg/Ex", staticAccess(r_).get(0).getFile().getFileName());
        assertEq(25, staticAccess(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", staticAccess(r_).get(0).getCallee().getFile().getFileName());
        assertEq(56, staticAccess(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", staticAccess(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs95() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $staticCall $void method(){\n");
        xml_.append("$staticCall(Outer<String>).callee();\n");
        xml_.append("$staticCall(Outer<StringBuilder>).callee();\n");
        xml_.append("}\n");
        xml_.append("$public $staticCall $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",84);
        assertEq(1, staticAccess(r_).size());
        assertEq(84, staticAccess(r_).get(0).getIndex());
        assertEq("pkg/Ex", staticAccess(r_).get(0).getFile().getFileName());
        assertEq(0, staticAccess(r_).get(0).getCallee().getIndex());
        assertEq("", staticAccess(r_).get(0).getCallee().getFileName());
        assertEq(56, staticAccess(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", staticAccess(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs96() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer[]{};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",45);
        assertEq(1, instanceNewTypesEltArray(r_).size());
        assertEq(45, instanceNewTypesEltArray(r_).get(0).getIndex());
        assertEq("pkg/Ex2", instanceNewTypesEltArray(r_).get(0).getFile().getFileName());
        assertEq(15, instanceNewTypesEltArray(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", instanceNewTypesEltArray(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, instanceNewTypesEltArray(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", instanceNewTypesEltArray(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs97() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer[0];}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",45);
        assertEq(1, instanceNewTypesEltArray(r_).size());
        assertEq(45, instanceNewTypesEltArray(r_).get(0).getIndex());
        assertEq("pkg/Ex2", instanceNewTypesEltArray(r_).get(0).getFile().getFileName());
        assertEq(15, instanceNewTypesEltArray(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", instanceNewTypesEltArray(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, instanceNewTypesEltArray(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", instanceNewTypesEltArray(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs98() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot2(g=@Annot(f=2))\n");
        xml_.append("ONE,\n");
        xml_.append("TWO;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot2{\n");
        xml_.append("Annot g();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",37);
        assertEq(1, instanceArobase(r_).size());
        assertEq(37, instanceArobase(r_).get(0).getIndex());
        assertEq("pkg/Ex", instanceArobase(r_).get(0).getFile().getFileName());
        assertEq(20, instanceArobase(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", instanceArobase(r_).get(0).getCallee().getFile().getFileName());
        assertEq(26, instanceArobase(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", instanceArobase(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs99() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$operator- $staticCall Outer(Outer o,Outer p){$return $null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer a=$operator(-,Outer)(a,Outer.ONE);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",60);
        assertEq(1, typesFinders(r_).size());
        assertEq(60, typesFinders(r_).get(0).getIndex());
        assertEq("pkg/Ex2", typesFinders(r_).get(0).getFile().getFileName());
        assertEq(14, typesFinders(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", typesFinders(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, typesFinders(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", typesFinders(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs100() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(f=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",27);
        assertFalse(r_.contains(CallerKind.ANNOT_INIT_MEMBER));
        assertFalse(r_.contains(CallerKind.ANNOT_AROBASE_MEMBER));
    }
    @Test
    public void refs101() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T>:Outer3 {\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer<Outer3>[0];}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",51);
        assertEq(1, instanceNewTypesEltArray(r_).size());
        assertEq(51, instanceNewTypesEltArray(r_).get(0).getIndex());
        assertEq("pkg/Ex2", instanceNewTypesEltArray(r_).get(0).getFile().getFileName());
        assertEq(19, instanceNewTypesEltArray(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", instanceNewTypesEltArray(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, instanceNewTypesEltArray(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", instanceNewTypesEltArray(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs102() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$public $class Inner<T>{}\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer().$new Inner<Outer3>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",64);
        assertEq(1, instanceNewTypesElt(r_).size());
        assertEq(64, instanceNewTypesElt(r_).get(0).getIndex());
        assertEq("pkg/Ex2", instanceNewTypesElt(r_).get(0).getFile().getFileName());
        assertEq(19, instanceNewTypesElt(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", instanceNewTypesElt(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, instanceNewTypesElt(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", instanceNewTypesElt(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs103() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class pkg.Outer:Outer3 {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$lambda(Outer,$new,Outer3.field);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",59);
        assertEq(1, typesFindersRef(r_).size());
        assertEq(59, typesFindersRef(r_).get(0).getIndex());
        assertEq("pkg/Ex2", typesFindersRef(r_).get(0).getFile().getFileName());
        assertEq(19, typesFindersRef(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", typesFindersRef(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, typesFindersRef(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", typesFindersRef(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs104() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(f=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations($class(SecAnnot))[0]).f();\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.SecAnnot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",27);
        assertFalse(r_.contains(CallerKind.ANNOT_INIT_MEMBER));
        assertFalse(r_.contains(CallerKind.INTERFACES_INIT));
        assertFalse(r_.contains(CallerKind.INTERFACES_INIT_REF));
    }
    @Test
    public void refs105() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int b=$switch(a){$case $int b:a+b==0;$return 0;$default b;$return 0;};};\n");
        xml_.append("$static{}\n");
        xml_.append("Outer2(){}\n");
        xml_.append("}\n");
        xml_.append("$operator/ pkg.Outer(pkg.Outer a, pkg.Outer b){$return $null;}\n");
        xml_.append("$public $annotation pkg.Annot{$int meth();}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",47);
        assertEq(1, callNamedUseSize(r_));
        assertEq(47, callNamedUseElt(r_, 0).getIndex());
        assertEq("pkg/Ex2", callNamedUseElt(r_, 0).getFile().getFileName());
        assertEq(57, callNamedUseElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex2", callNamedUseElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(38, callNamedUseElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex2", callNamedUseElt(r_, 0).getCaller().getFile().getFileName());
        assertFalse(r_.contains(CallerKind.RETURN));
    }
    @Test
    public void refs106() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=$valueOf(Outer,\"\").ordinal();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",56);
        assertEq(1, staticAccess(r_).size());
        assertEq(56, staticAccess(r_).get(0).getIndex());
        assertEq("pkg/Ex2", staticAccess(r_).get(0).getFile().getFileName());
        assertEq(14, staticAccess(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", staticAccess(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, staticAccess(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", staticAccess(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs107() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=$values(Outer).length;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",55);
        assertEq(1, staticAccess(r_).size());
        assertEq(55, staticAccess(r_).get(0).getIndex());
        assertEq("pkg/Ex2", staticAccess(r_).get(0).getFile().getFileName());
        assertEq(14, staticAccess(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", staticAccess(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, staticAccess(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", staticAccess(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs108() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Object o= (Outer)$null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",51);
        assertEq(1, castOperation(r_).size());
        assertEq(51, castOperation(r_).get(0).getIndex());
        assertEq("pkg/Ex2", castOperation(r_).get(0).getFile().getFileName());
        assertEq(15, castOperation(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", castOperation(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, castOperation(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", castOperation(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs109() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$boolean a=ONE $instanceof Outer;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",67);
        assertEq(1, instanceOperation(r_).size());
        assertEq(67, instanceOperation(r_).get(0).getIndex());
        assertEq("pkg/Ex2", instanceOperation(r_).get(0).getFile().getFileName());
        assertEq(14, instanceOperation(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", instanceOperation(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, instanceOperation(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", instanceOperation(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs110() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int g=$switch[$short:@Annot(e=2):@Annot(e=2)](0){$default;$return 0;};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",42);
        assertEq(1, returnType(r_).size());
        assertEq(42, returnType(r_).get(0).getIndex());
        assertEq("pkg/Ex", returnType(r_).get(0).getFile().getFileName());
        assertEq(0, returnType(r_).get(0).getCallee().getIndex());
        assertEq("", returnType(r_).get(0).getCallee().getFileName());
        assertEq(32, returnType(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", returnType(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs111() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$public $class Inner<T>{}\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer().$new Inner<Outer3>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",64);
        assertEq(1, instanceNewTypesEltFwd(r_).size());
        assertEq(107, instanceNewTypesEltFwd(r_).get(0).getIndex());
        assertEq("pkg/Ex", instanceNewTypesEltFwd(r_).get(0).getFile().getFileName());
        assertEq(19, instanceNewTypesEltFwd(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", instanceNewTypesEltFwd(r_).get(0).getCallee().getFile().getFileName());
        assertEq(80, instanceNewTypesEltFwd(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", instanceNewTypesEltFwd(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs112() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=$new Outer().$classchoice(Outer)[0];}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",73);
        assertEq(1, typesFinders(r_).size());
        assertEq(73, typesFinders(r_).get(0).getIndex());
        assertEq("pkg/Ex2", typesFinders(r_).get(0).getFile().getFileName());
        assertEq(15, typesFinders(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", typesFinders(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, typesFinders(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", typesFinders(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs113() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final Outer THREE=$defaultValue(Outer);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",80);
        assertEq(1, typesInfosDef(r_).size());
        assertEq(80, typesInfosDef(r_).get(0).getIndex());
        assertEq("pkg/Ex2", typesInfosDef(r_).get(0).getFile().getFileName());
        assertEq(14, typesInfosDef(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", typesInfosDef(r_).get(0).getCallee().getFile().getFileName());
        assertEq(60, typesInfosDef(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", typesInfosDef(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs114() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=$class(Outer).getName().length;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",54);
        assertEq(1, typesInfos(r_).size());
        assertEq(54, typesInfos(r_).get(0).getIndex());
        assertEq("pkg/Ex2", typesInfos(r_).get(0).getFile().getFileName());
        assertEq(14, typesInfos(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", typesInfos(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, typesInfos(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", typesInfos(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs115() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer a;\n");
        xml_.append("Outer b;\n");
        xml_.append("b=a->0;\n");
        xml_.append("(($Fct)b).call(0);\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",81);
        assertEq(1, callAnonRefUse(r_).size());
        assertEq(79, callAnonRefUse(r_).get(0).getIndex());
        assertEq("pkg/Ex", callAnonRefUse(r_).get(0).getFile().getFileName());
        assertEq(80, callAnonRefUse(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", callAnonRefUse(r_).get(0).getCallee().getFile().getFileName());
        assertEq(49, callAnonRefUse(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", callAnonRefUse(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, dynCallPotential(r_).size());
        assertEq(95, dynCallPotential(r_).get(0).getIndex());
        assertEq("pkg/Ex", dynCallPotential(r_).get(0).getFile().getFileName());
        assertEq(80, dynCallPotential(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", dynCallPotential(r_).get(0).getCallee().getFile().getFileName());
        assertEq(49, dynCallPotential(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", dynCallPotential(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs116() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$static $boolean $(Outer o){$return $true;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2<T:Outer3> {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer(){};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3 {\n");
        xml_.append("$void m()$intern(Outer3:m(Outer3)){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex3",52);
        assertEq(1, internEltsFctSize(r_));
        assertEq(52, internEltsFctElt(r_, 0).getIndex());
        assertEq("pkg/Ex3", internEltsFctElt(r_, 0).getFile().getFileName());
        assertEq(34, internEltsFctElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex3", internEltsFctElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(34, internEltsFctElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex3", internEltsFctElt(r_, 0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs117() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$static $boolean $(Outer o){$return $true;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2<T:Outer3> {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer(){};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3 {\n");
        xml_.append("$intern{m():m(Outer3)};\n");
        xml_.append("$void m()$intern(Outer3:m(Outer3);Outer3:m(Outer3)){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex3",40);
        assertEq(1, internElts(r_).size());
        assertEq(40, internElts(r_).get(0).getIndex());
        assertEq("pkg/Ex3", internElts(r_).get(0).getFile().getFileName());
        assertEq(58, internElts(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", internElts(r_).get(0).getCallee().getFile().getFileName());
        assertEq(15, internElts(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex3", internElts(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs118() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$static $boolean $(Outer o){$return $true;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2<T:Outer3> {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer(){};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3 {\n");
        xml_.append("$void m()$intern(Outer3:m(Outer3)){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex3",45);
        assertEq(2, internEltsFctSize(r_));
        assertEq(45, internEltsFctElt(r_, 0).getIndex());
        assertEq("pkg/Ex3", internEltsFctElt(r_, 0).getFile().getFileName());
        assertEq(15, internEltsFctElt(r_, 0).getCallee().getIndex());
        assertEq("pkg/Ex3", internEltsFctElt(r_, 0).getCallee().getFile().getFileName());
        assertEq(34, internEltsFctElt(r_, 0).getCaller().getIndex());
        assertEq("pkg/Ex3", internEltsFctElt(r_, 0).getCaller().getFile().getFileName());
        assertEq(54, internEltsFctElt(r_, 1).getIndex());
        assertEq("pkg/Ex3", internEltsFctElt(r_, 1).getFile().getFileName());
        assertEq(15, internEltsFctElt(r_, 1).getCallee().getIndex());
        assertEq("pkg/Ex3", internEltsFctElt(r_, 1).getCallee().getFile().getFileName());
        assertEq(34, internEltsFctElt(r_, 1).getCaller().getIndex());
        assertEq("pkg/Ex3", internEltsFctElt(r_, 1).getCaller().getFile().getFileName());
    }
    @Test
    public void refs119() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$static $boolean $(Outer o){$return $true;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2<T:Outer3> {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer(){};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3 {\n");
        xml_.append("$intern{m():m(Outer3)};\n");
        xml_.append("$void m()$intern(Outer3:m(Outer3);Outer3:m(Outer3)){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex3",42);
        assertEq(1, internElts(r_).size());
        assertEq(42, internElts(r_).get(0).getIndex());
        assertEq("pkg/Ex3", internElts(r_).get(0).getFile().getFileName());
        assertEq(15, internElts(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", internElts(r_).get(0).getCallee().getFile().getFileName());
        assertEq(15, internElts(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex3", internElts(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs120() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static Outer3 $(Outer o){$return $null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3 {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",44);
        assertEq(1, paramType(r_).size());
        assertEq(44, paramType(r_).get(0).getIndex());
        assertEq("pkg/Ex", paramType(r_).get(0).getFile().getFileName());
        assertEq(15, paramType(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", paramType(r_).get(0).getCallee().getFile().getFileName());
        assertEq(42, paramType(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", paramType(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs121() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static Outer2 $(Outer o){$return $null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2 {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",35);
        assertEq(1, returnType(r_).size());
        assertEq(35, returnType(r_).get(0).getIndex());
        assertEq("pkg/Ex", returnType(r_).get(0).getFile().getFileName());
        assertEq(15, returnType(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", returnType(r_).get(0).getCallee().getFile().getFileName());
        assertEq(42, returnType(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", returnType(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs122() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer a;\n");
        xml_.append("Outer b;\n");
        xml_.append("b=(Outer a:Outer)->a;\n");
        xml_.append("(($Fct)b).call(0);\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",80);
        assertEq(1, paramType(r_).size());
        assertEq(80, paramType(r_).get(0).getIndex());
        assertEq("pkg/Ex", paramType(r_).get(0).getFile().getFileName());
        assertEq(15, paramType(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", paramType(r_).get(0).getCallee().getFile().getFileName());
        assertEq(94, paramType(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", paramType(r_).get(0).getCaller().getFile().getFileName());
        assertEq(1, returnType(r_).size());
        assertEq(88, returnType(r_).get(0).getIndex());
        assertEq("pkg/Ex", returnType(r_).get(0).getFile().getFileName());
        assertEq(15, returnType(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", returnType(r_).get(0).getCallee().getFile().getFileName());
        assertEq(94, returnType(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", returnType(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs123() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("Outer b;\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer a;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",68);
        assertEq(1, variableDeclaring(r_).size());
        assertEq(68, variableDeclaring(r_).get(0).getIndex());
        assertEq("pkg/Ex", variableDeclaring(r_).get(0).getFile().getFileName());
        assertEq(15, variableDeclaring(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", variableDeclaring(r_).get(0).getCallee().getFile().getFileName());
        assertEq(58, variableDeclaring(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", variableDeclaring(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs124() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("Outer b;\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer a;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",27);
        assertEq(1, fieldDeclaring(r_).size());
        assertEq(27, fieldDeclaring(r_).get(0).getIndex());
        assertEq("pkg/Ex", fieldDeclaring(r_).get(0).getFile().getFileName());
        assertEq(15, fieldDeclaring(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex", fieldDeclaring(r_).get(0).getCallee().getFile().getFileName());
        assertEq(33, fieldDeclaring(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", fieldDeclaring(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs125() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$static $boolean $(Outer o){$return $true;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2<T:Outer3> {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer(){};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3 {\n");
        xml_.append("$intern{m():m(Outer3)};\n");
        xml_.append("$void m()$intern(Outer3:m(Outer3);Outer3:m(Outer3)){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",27);
        assertEq(1, constraints(r_).size());
        assertEq(27, constraints(r_).get(0).getIndex());
        assertEq("pkg/Ex2", constraints(r_).get(0).getFile().getFileName());
        assertEq(15, constraints(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", constraints(r_).get(0).getCallee().getFile().getFileName());
        assertEq(14, constraints(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", constraints(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs126() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$public $class Inner<T>{}\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer().$new Inner<Outer3>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",25);
        assertEq(1, inherits(r_).size());
        assertEq(25, inherits(r_).get(0).getIndex());
        assertEq("pkg/Ex", inherits(r_).get(0).getFile().getFileName());
        assertEq(19, inherits(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", inherits(r_).get(0).getCallee().getFile().getFileName());
        assertEq(15, inherits(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", inherits(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs127() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class $interfaces(Outer3;Outer3) pkg.Outer:Outer3 {\n");
        xml_.append("$public $class Inner<T>{}\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer().$new Inner<Outer3>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",35);
        assertEq(1, interfacesInstance(r_).size());
        assertEq(35, interfacesInstance(r_).get(0).getIndex());
        assertEq("pkg/Ex", interfacesInstance(r_).get(0).getFile().getFileName());
        assertEq(19, interfacesInstance(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", interfacesInstance(r_).get(0).getCallee().getFile().getFileName());
        assertEq(43, interfacesInstance(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", interfacesInstance(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs128() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class $interfaces(Outer3;Outer3) pkg.Outer:Outer3 {\n");
        xml_.append("$public $class Inner<T>{}\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer().$new Inner<Outer3>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex",28);
        assertEq(1, interfacesStatic(r_).size());
        assertEq(28, interfacesStatic(r_).get(0).getIndex());
        assertEq("pkg/Ex", interfacesStatic(r_).get(0).getFile().getFileName());
        assertEq(19, interfacesStatic(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex3", interfacesStatic(r_).get(0).getCallee().getFile().getFileName());
        assertEq(43, interfacesStatic(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex", interfacesStatic(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs129() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$Fct<String,$int> v;v.call(\"\");}\n");
        xml_.append("{$Fct<$int,String> v;v.call(0);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",62);
        assertEq(1, callDyn(r_).size());
        assertEq(62, callDyn(r_).get(0).getIndex());
        assertEq("pkg/Ex2", callDyn(r_).get(0).getFile().getFileName());
        assertEq(38, callDyn(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", callDyn(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs130() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$void method(T t){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2<T> {\n");
        xml_.append("$void m(T t){$new Outer<T>().$lambda(Outer<T>,method,T).call(t);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",77);
        assertFalse(dynCallPotential(r_).isEmpty());
    }
    @Test
    public void refs131() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$static $boolean $(Outer o){$return $true;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$for(Outer o,$var p:$($iterableTable<Outer,Outer2>)$null){}}\n");
        xml_.append("{$for(Outer2 o,$var p:$($iterableTable<Outer2,Outer>)$null){}}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",53);
        assertEq(1, variableDeclaringInferred(r_).size());
        assertEq(53, variableDeclaringInferred(r_).get(0).getIndex());
        assertEq("pkg/Ex2", variableDeclaringInferred(r_).get(0).getFile().getFileName());
        assertEq(53, variableDeclaringInferred(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", variableDeclaringInferred(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, variableDeclaringInferred(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", variableDeclaringInferred(r_).get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs132() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$static $boolean $(Outer o){$return $true;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$for($var o,Outer2 p:$($iterableTable<Outer,Outer2>)$null){}}\n");
        xml_.append("{$for(Outer o,$var p:$($iterableTable<Outer,Outer2>)$null){}}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> r_ = refs(files_,"pkg/Ex2",45);
        assertEq(1, variableDeclaringInferred(r_).size());
        assertEq(45, variableDeclaringInferred(r_).get(0).getIndex());
        assertEq("pkg/Ex2", variableDeclaringInferred(r_).get(0).getFile().getFileName());
        assertEq(45, variableDeclaringInferred(r_).get(0).getCallee().getIndex());
        assertEq("pkg/Ex2", variableDeclaringInferred(r_).get(0).getCallee().getFile().getFileName());
        assertEq(38, variableDeclaringInferred(r_).get(0).getCaller().getIndex());
        assertEq("pkg/Ex2", variableDeclaringInferred(r_).get(0).getCaller().getFile().getFileName());
    }

    private FileBlockIndex variablesParamsUseElt(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r, int _index) {
        return _r.getVal(CallerKind.VARIABLES).getValue(0).get(_index);
    }

    private int variablesParamsUseSize(IdMap<CallerKind, IdMap<SrcFileLocation, CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.VARIABLES).getValue(0).size();
    }

    private int fieldsRefUseSize(IdMap<CallerKind, IdMap<SrcFileLocation, CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.FIELD_REF).getValue(0).size();
    }

    private FileBlockIndex fieldsRefUseElt(IdMap<CallerKind, IdMap<SrcFileLocation, CustList<FileBlockIndex>>> _r, int _index) {
        return _r.getVal(CallerKind.FIELD_REF).getValue(0).get(_index);
    }

    private CustList<FileBlockIndex> fieldsUseInit(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.FIELD_INIT).getValue(0);
    }

    private CustList<FileBlockIndex> fieldsUse(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.FIELD).getValue(0);
    }

    private IdMap<SrcFileLocation, CustList<FileBlockIndex>> callNamedOverriding(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.OVERRIDING);
    }

    private IdMap<SrcFileLocation, CustList<FileBlockIndex>> allNamedOverridden(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.OVERRIDDEN);
    }

    private CustList<FileBlockIndex> callNamedRefUsePoly(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.NAME_REF_POLY).getValue(0);
    }

    private CustList<FileBlockIndex> callNamedRefUse(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.NAME_REF).getValue(0);
    }

    private CustList<FileBlockIndex> callNamedUsePoly(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.NAME_POLY).getValue(0);
    }

    private CustList<FileBlockIndex> labels(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.LABEL).getValue(0);
    }

    private CustList<FileBlockIndex> callNamedUseImpl(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.NAME_IMPL).getValue(0);
    }

    private CustList<FileBlockIndex> instanceNewTypes(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.INSTANCE).getValue(0);
    }

    private CustList<FileBlockIndex> instanceNewTypesRef(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.INSTANCE_REF).getValue(0);
    }


    private CustList<FileBlockIndex> instanceNewTypesFwd(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.INSTANCE_FWD).getValue(0);
    }


    private int callNamedFieldUseSizeAll(IdMap<CallerKind, IdMap<SrcFileLocation, CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.NAME_FIELD).size();
    }


    private int callNamedFieldUseSize(IdMap<CallerKind, IdMap<SrcFileLocation, CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.NAME_FIELD).getValue(0).size();
    }

    private int callNamedFieldUseSize(IdMap<CallerKind, IdMap<SrcFileLocation, CustList<FileBlockIndex>>> _r, int _index) {
        return _r.getVal(CallerKind.NAME_FIELD).getValue(_index).size();
    }

    private FileBlockIndex callNamedFieldUseElt(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r, int _index) {
        return _r.getVal(CallerKind.NAME_FIELD).getValue(0).get(_index);
    }

    private FileBlockIndex callNamedFieldUseElt(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r, int _first, int _index) {
        return _r.getVal(CallerKind.NAME_FIELD).getValue(_first).get(_index);
    }


    private CustList<FileBlockIndex> annotCandidatesCallsInitParameters(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.ANNOT_INIT_PARAMETER).getValue(0);
    }

    private CustList<FileBlockIndex> annotCandidatesCallsInitArobaseParameters(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.ANNOT_AROBASE_PARAMETER).getValue(0);
    }


    private CustList<FileBlockIndex> annotCandidatesCallsInitSuppl(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.ANNOT_INIT_SUPPL).getValue(0);
    }

    private CustList<FileBlockIndex> annotCandidatesCallsInitArobaseSuppl(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.ANNOT_AROBASE_SUPPL).getValue(0);
    }

    private CustList<FileBlockIndex> annotCandidatesCallsInitDefValue(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.ANNOT_INIT_DEF_VALUE).getValue(0);
    }

    private CustList<FileBlockIndex> annotCandidatesCallsInitArobaseDefValue(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.ANNOT_AROBASE_DEF_VALUE).getValue(0);
    }

    private CustList<FileBlockIndex> instanceArobase(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.AROBASE).getValue(0);
    }


    private CustList<FileBlockIndex> annotCandidatesCallsInitArobaseMembers(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.ANNOT_AROBASE_MEMBER).getValue(0);
    }


    private CustList<FileBlockIndex> instanceNewTypesEltArray(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.INSTANCE_ELT_ARRAY).getValue(0);
    }

    private CustList<FileBlockIndex> instanceNewTypesElt(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.INSTANCE_ELT).getValue(0);
    }


    private CustList<FileBlockIndex> typesFindersRef(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.TYPES_FINDERS_REF).getValue(0);
    }


    private CustList<FileBlockIndex> annotCandidatesCallsInitMembers(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.ANNOT_INIT_MEMBER).getValue(0);
    }

    private int callNamedUseSize(IdMap<CallerKind, IdMap<SrcFileLocation, CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.NAME).getValue(0).size();
    }

    private FileBlockIndex callNamedUseElt(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r, int _index) {
        return _r.getVal(CallerKind.NAME).getValue(0).get(_index);
    }

    private CustList<FileBlockIndex> staticAccess(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.STATIC).getValue(0);
    }

    private CustList<FileBlockIndex> castOperation(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.CAST).getValue(0);
    }

    private CustList<FileBlockIndex> instanceOperation(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.INSTANCEOF).getValue(0);
    }


    private CustList<FileBlockIndex> typesFinders(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.TYPES_FINDERS).getValue(0);
    }


    private CustList<FileBlockIndex> instanceNewTypesEltFwd(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.INSTANCE_ELT_FWD).getValue(0);
    }

    private CustList<FileBlockIndex> typesInfosDef(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.TYPES_INFOS_DEF).getValue(0);
    }


    private CustList<FileBlockIndex> typesInfos(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.TYPES_INFOS).getValue(0);
    }

    private CustList<FileBlockIndex> callAnonRefUse(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.ANON_REF).getValue(0);
    }

    private int internEltsFctSize(IdMap<CallerKind, IdMap<SrcFileLocation, CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.INTERN_ELTS_FCT).getValue(0).size();
    }

    private FileBlockIndex internEltsFctElt(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r, int _index) {
        return _r.getVal(CallerKind.INTERN_ELTS_FCT).getValue(0).get(_index);
    }

    private CustList<FileBlockIndex> internElts(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.INTERN_ELTS).getValue(0);
    }

    private CustList<FileBlockIndex> paramType(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.PARAM).getValue(0);
    }

    private CustList<FileBlockIndex> returnType(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.RETURN).getValue(0);
    }

    private CustList<FileBlockIndex> variableDeclaring(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.VAR_DECL).getValue(0);
    }

    private CustList<FileBlockIndex> fieldDeclaring(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.FIELD_DECL).getValue(0);
    }

    private CustList<FileBlockIndex> constraints(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.CONSTRAINTS).getValue(0);
    }

    private CustList<FileBlockIndex> inherits(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.INHERITS).getValue(0);
    }

    private CustList<FileBlockIndex> interfacesInstance(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.INTERFACES_INSTANCE).getValue(0);
    }

    private CustList<FileBlockIndex> interfacesStatic(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.INTERFACES_STATIC).getValue(0);
    }

    private CustList<FileBlockIndex> callDyn(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.CALL_DYN).getValue(0);
    }

    private CustList<FileBlockIndex> dynCallPotential(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.DYN_CALL_POT).getValue(0);
    }

    private CustList<FileBlockIndex> variableDeclaringInferred(IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> _r) {
        return _r.getVal(CallerKind.VAR_DECL_INFER).getValue(0);
    }

    private static IdMap<CallerKind, IdMap<SrcFileLocation,CustList<FileBlockIndex>>> refs(StringMap<String> _files, String _fileName, int _caret) {
        AnalyzedPageEl a_ = quickAnalyze(_files);
        return CallersRef.loop(a_,ResultExpressionOperationNode.locations(a_,_fileName,_caret));
    }

    private static AnalyzedPageEl quickAnalyze(StringMap<String> _files) {
        Options opt_ = newOptions();
        opt_.setReadOnly(true);
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();
        KeyWords kw_ = new KeyWords();
        setOpts(opt_, IndexConstants.INDEX_NOT_FOUND_ELT);
        return validateAndRetWithoutInitCheck(opt_,lgName_,kw_,_files,new StringMap<String>()).getPageEl();
    }
}
