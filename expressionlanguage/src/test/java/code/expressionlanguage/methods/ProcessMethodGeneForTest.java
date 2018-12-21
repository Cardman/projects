package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.StringMap;

public final class ProcessMethodGeneForTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;>0i:i;--){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;>0i:i;--){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $for($int i=4i:i;>0i:i;--){\n");
        xml_.append("   t;.*=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(240, ret_.getNumber());
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i,j=7i:i;>0i:i;--,j;-=2i){\n");
        xml_.append("   t;.+=i;+j;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(26, ret_.getNumber());
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;>0i:i;=decr(i;)){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int decr($int v){\n");
        xml_.append("  $return v;.;-1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:gt(i;,0i):i;--){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean gt($int v,$int c){\n");
        xml_.append("  $return v;.;>c;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=init():i;>0i:i;--){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int init(){\n");
        xml_.append("  $return 4i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=init():gt(i;,0i):i;=decr(i;)){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int init(){\n");
        xml_.append("  $return 4i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean gt($int v,$int c){\n");
        xml_.append("  $return v;.;>c;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int decr($int v){\n");
        xml_.append("  $return v;.;-1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,u:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for(u;.=4i:u;.>0i:u;.--){\n");
        xml_.append("   t;.+=u;.:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,u=4i:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for(:u;.>0i:u;.--){\n");
        xml_.append("   t;.+=u;.:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,u:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for(u;.=4i:u;.>0i:){\n");
        xml_.append("   t;.+=u;.:\n");
        xml_.append("   u;.--:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,u:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for(u;.=4i::u;.--){\n");
        xml_.append("   $if(u;.<=0i){\n");
        xml_.append("    $break:\n");
        xml_.append("   }\n");
        xml_.append("   t;.+=u;.:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;>0i:i;--){\n");
        xml_.append("   $if(i;%2==1i){\n");
        xml_.append("    t;.*=i;:\n");
        xml_.append("    $continue:\n");
        xml_.append("   }\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(14, ret_.getNumber());
    }
    @Test
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  $final $int l:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;>0i:i;--){\n");
        xml_.append("   $if(i;%2==1i){\n");
        xml_.append("    t;.*=i;:\n");
        xml_.append("    $continue:\n");
        xml_.append("   }\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(14, ret_.getNumber());
    }
    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  $final $int l:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i::i;--){\n");
        xml_.append("   $if(i;==2i){\n");
        xml_.append("    l;.=3i:\n");
        xml_.append("    $break:\n");
        xml_.append("   }\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.+l;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;>0i:i;--){\n");
        xml_.append("   $for($int j=i;:j;>0i:j;--){\n");
        xml_.append("    t;.+=i;+j;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(50, ret_.getNumber());
    }
    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  $final $int l:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i::i;--){\n");
        xml_.append("   $if(i;==2i){\n");
        xml_.append("    l;.=2i:\n");
        xml_.append("    $break:\n");
        xml_.append("   }\n");
        xml_.append("   l;.=3i:\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("   $return t;.+l;.:\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.+l;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(7, ret_.getNumber());
    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,u=4i:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($final $int i=1i:u;.>0i:){\n");
        xml_.append("   t;.+=u;.+i;:\n");
        xml_.append("   u;.--:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(14, ret_.getNumber());
    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;>0i:i;--){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;<0i:i;--){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;==0i:i;--){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4,j=7i:i;>0i:i;--,j;-=2i){\n");
        xml_.append("   t;.+=i;+j;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(26, ret_.getNumber());
    }
    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4,j=7i:i;>0i:i;--,j;-=2i){\n");
        xml_.append("   $if(i; == 4){\n");
        xml_.append("    i;=0:\n");
        xml_.append("   }\n");
        xml_.append("   t;.+=i;+j;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(7, ret_.getNumber());
    }
    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;>0i:i;--,u;.=1i){\n");
        xml_.append("   $int u:\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  $final $int l:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;>0i:i;--){\n");
        xml_.append("   $if(i;==2i){\n");
        xml_.append("    l;.=3i:\n");
        xml_.append("    $break:\n");
        xml_.append("   }\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.+l;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  $final $int l:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i::i;--){\n");
        xml_.append("   $if(i;==2i){\n");
        xml_.append("    l;.=3i:\n");
        xml_.append("    $continue:\n");
        xml_.append("   }\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($final $int i=4i:i;>0i:i;--){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument5FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $final $int i:\n");
        xml_.append("  $for(:t;.>0i:i;.=0i){\n");
        xml_.append("   t;.+=0i:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument6FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($final $int i:t;.>0i:i;=0i){\n");
        xml_.append("   t;.+=0i:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument7FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int i:\n");
        xml_.append(" $public(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for(:t;.>0i:i;;;=0i){\n");
        xml_.append("   t;.+=0i:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument8FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4,j=7i:i;>0i:i;--,j;-=2i){\n");
        xml_.append("   $if(i; == 4){\n");
        xml_.append("    $long u = 0:\n");
        xml_.append("    i;=u;.:\n");
        xml_.append("   }\n");
        xml_.append("   t;.+=i;+j;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument9FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $final $int u:\n");
        xml_.append("  $for($int i=4i:i;>0i:i;--,u;.=1i){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculateArgument10FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $final $int u:\n");
        xml_.append("  $for($int i=4i:(u;.=1i)>0i:i;--){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl(true,false);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
}
