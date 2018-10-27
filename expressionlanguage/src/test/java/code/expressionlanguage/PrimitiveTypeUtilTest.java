package code.expressionlanguage;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.DoubleStruct;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.Struct;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class PrimitiveTypeUtilTest {

    private static final String CUST_CLASS = "pkg.CustClass";
    private static final String ARR_ARR_CUST_CLASS = "[[pkg.CustClass";
    private static final String ARR_CUST_CLASS = "[pkg.CustClass";
    @Test
    public void canBeUseAsArgument1Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasPrimByte(), context_.getStandards().getAliasPrimByte(), context_));
    }

    @Test
    public void canBeUseAsArgument2Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasPrimInteger(), context_.getStandards().getAliasPrimByte(), context_));
    }

    @Test
    public void canBeUseAsArgument3Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasObject(), context_.getStandards().getAliasPrimByte(), context_));
    }

    @Test
    public void canBeUseAsArgument4Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasByte(), context_.getStandards().getAliasPrimByte(), context_));
    }

    @Test
    public void canBeUseAsArgument5Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasNumber(), context_.getStandards().getAliasPrimByte(), context_));
    }

    @Test
    public void canBeUseAsArgument6Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasNumber(), context_.getStandards().getAliasByte(), context_));
    }

    @Test
    public void canBeUseAsArgument7Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasBoolean(), context_.getStandards().getAliasPrimBoolean(), context_));
    }

    @Test
    public void canBeUseAsArgument8Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasPrimBoolean(), context_.getStandards().getAliasPrimBoolean(), context_));
    }

    @Test
    public void canBeUseAsArgument9Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasObject());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimInteger());
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument10Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasInteger());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimInteger());
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument11Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasNumber());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimInteger());
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument12Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimInteger());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimByte());
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument13Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasObject());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimByte());
        arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(arrInt_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument14Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasObject());
        arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(arrObj_);
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimByte());
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument15Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasBoolean(), context_.getStandards().getAliasBoolean(), context_));
    }

    @Test
    public void canBeUseAsArgument16Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasNumber(), context_.getStandards().getAliasBoolean(), context_));
    }

    @Test
    public void canBeUseAsArgument17Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimByte());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimInteger());
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument18Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasLong());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimInteger());
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument19Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasByte());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimInteger());
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument20Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasNumber());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimChar());
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument21Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasCharacter());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimChar());
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument22Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasObject());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasCharacter());
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument23Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasObject());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimChar());
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument24Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasLong());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasInteger());
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument25Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = context_.getStandards().getAliasPrimInteger();
        String arrInt_ = context_.getStandards().getAliasInteger();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument26Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = context_.getStandards().getAliasPrimByte();
        String arrInt_ = context_.getStandards().getAliasInteger();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument27Test() {
        ContextEl context_ = simpleContextEl();
        String arrInt_ = context_.getStandards().getAliasInteger();
        String arrLong_ = context_.getStandards().getAliasPrimLong();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrLong_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument28Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimInteger());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasInteger());
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument29Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = context_.getStandards().getAliasNumber();
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasInteger());
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument30Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExFour","pkg.ExFour",context_));
    }

    @Test
    public void canBeUseAsArgument31Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExFour","pkg.ExTwo",context_));
    }

    @Test
    public void canBeUseAsArgument32Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExTwo","pkg.ExFour",context_));
    }

    @Test
    public void canBeUseAsArgument33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExTwo",context_.getStandards().getAliasInteger(),context_));
    }

    @Test
    public void canBeUseAsArgument34Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasNumber(),context_.getStandards().getAliasInteger(),context_));
    }

    @Test
    public void canBeUseAsArgument35Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("[pkg.ExFour","[pkg.ExFour",context_));
    }

    @Test
    public void canBeUseAsArgument36Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("[pkg.ExFour","[pkg.ExTwo",context_));
    }

    @Test
    public void canBeUseAsArgument37Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("[pkg.ExTwo","[pkg.ExFour",context_));
    }

    @Test
    public void canBeUseAsArgument38Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("[pkg.ExTwo","[[pkg.ExTwo",context_));
    }

    @Test
    public void canBeUseAsArgument39Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("[[pkg.ExTwo","[pkg.ExTwo",context_));
    }

    @Test
    public void canBeUseAsArgument40Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("[java.lang.Object","[pkg.ExTwo",context_));
    }

    @Test
    public void canBeUseAsArgument41Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("[[java.lang.Object","[pkg.ExTwo",context_));
    }

    @Test
    public void canBeUseAsArgument42Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExTwo","[pkg.ExTwo",context_));
    }

    @Test
    public void canBeUseAsArgument43Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExTwo",null,context_));
    }

    @Test
    public void canBeUseAsArgument44Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasObject(),null,context_));
    }

    @Test
    public void canBeUseAsArgument45Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasInteger(),null,context_));
    }

    @Test
    public void canBeUseAsArgument46Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasPrimInteger(),null,context_));
    }

    @Test
    public void canBeUseAsArgument47Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasVoid(),null,context_));
    }

























    @Test
    public void canBeUseAsArgument52Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasNumber(), context_.getStandards().getAliasInteger(), context_));
    }













    @Test
    public void canBeUseAsArgument55Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasCharSequence(), context_.getStandards().getAliasString(), context_));
    }

    @Test
    public void canBeUseAsArgument56Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasCharSequence(), context_.getStandards().getAliasCharSequence(), context_));
    }







    @Test
    public void canBeUseAsArgument58Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("pkg.Ex","pkg.ExFour",context_));
    }

    @Test
    public void canBeUseAsArgument59Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExFour","pkg.Ex",context_));
    }

    @Test
    public void canBeUseAsArgument60Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExFour","pkg.ExFour",context_));
    }

    @Test
    public void canBeUseAsArgument61Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("[pkg.Ex","[pkg.ExFour",context_));
    }

    @Test
    public void canBeUseAsArgument62Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("[pkg.ExFour","[pkg.Ex",context_));
    }

    @Test
    public void canBeUseAsArgument63Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("[pkg.ExFour","[pkg.ExFour",context_));
    }

    @Test
    public void canBeUseAsArgument64Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasPrimInteger(), context_.getStandards().getAliasPrimChar(), context_));
    }

    @Test
    public void canBeUseAsArgument65Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasPrimChar(), context_.getStandards().getAliasPrimInteger(), context_));
    }
    
    @Test
    public void canBeUseAsArgument66Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasPrimByte(), context_.getStandards().getAliasPrimInteger(), context_));
    }

    @Test
    public void canBeUseAsArgument67Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasVoid(), context_.getStandards().getAliasObject(), context_));
    }

    @Test
    public void canBeUseAsArgument68Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasObject(), context_.getStandards().getAliasVoid(), context_));
    }

    @Test
    public void canBeUseAsArgument69Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(context_.getStandards().getAliasVoid(), context_.getStandards().getAliasVoid(), context_));
    }

    @Test
    public void canBeUseAsArgument70Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = context_.getStandards().getAliasObject();
        String arrInt_ = context_.getStandards().getAliasPrimChar();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument71Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = context_.getStandards().getAliasObject();
        String arrInt_ = context_.getStandards().getAliasPrimInteger();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument72Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = context_.getStandards().getAliasNumber();
        String arrInt_ = context_.getStandards().getAliasPrimInteger();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument73Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = context_.getStandards().getAliasBoolean();
        String arrInt_ = context_.getStandards().getAliasPrimBoolean();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument74Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = context_.getStandards().getAliasPrimBoolean();
        String arrInt_ = context_.getStandards().getAliasPrimBoolean();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void canBeUseAsArgument75Test() {
        ContextEl context_ = simpleContextEl();
        String arrObj_ = context_.getStandards().getAliasPrimBoolean();
        String arrInt_ = context_.getStandards().getAliasBoolean();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, context_));
    }

    @Test
    public void getSubclasses1Test() {
        ContextEl context_ = simpleContextEl();
        StringList classes_ = new StringList(context_.getStandards().getAliasInteger(), context_.getStandards().getAliasNumber());
        StringList sub_ = PrimitiveTypeUtil.getSubclasses(classes_, context_);
        assertEq(1, sub_.size());
        assertEq(context_.getStandards().getAliasInteger(), sub_.get(0));
    }

    @Test
    public void getSubclasses2Test() {
        ContextEl context_ = simpleContextEl();
        StringList classes_ = new StringList(context_.getStandards().getAliasString(), context_.getStandards().getAliasNumber());
        StringList sub_ = PrimitiveTypeUtil.getSubclasses(classes_, context_);
        assertEq(2, sub_.size());
        assertEq(context_.getStandards().getAliasString(), sub_.get(0));
        assertEq(context_.getStandards().getAliasNumber(), sub_.get(1));
    }

    @Test
    public void getSubclasses3Test() {
        ContextEl context_ = simpleContextEl();
        StringList classes_ = new StringList(context_.getStandards().getAliasVoid(), context_.getStandards().getAliasVoid());
        StringList sub_ = PrimitiveTypeUtil.getSubclasses(classes_, context_);
        assertEq(1, sub_.size());
        assertEq(context_.getStandards().getAliasVoid(), sub_.get(0));
    }

    @Test
    public void newCustomArray1Test() {
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        Numbers<Integer> dims_ = new Numbers<Integer>(1);
        Struct customArray_ = PrimitiveTypeUtil.newCustomArray(CUST_CLASS, dims_, cont_);
        assertEq(ARR_CUST_CLASS, customArray_.getClassName(null));
        Struct[] instance_ = (Struct[]) customArray_.getInstance();
        assertEq(1, instance_.length);
        Struct elt_ = instance_[0];
        assertTrue(elt_.isNull());
    }

    @Test
    public void newCustomArray2Test() {
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        Numbers<Integer> dims_ = new Numbers<Integer>(2);
        Struct customArray_ = PrimitiveTypeUtil.newCustomArray(CUST_CLASS, dims_, cont_);
        assertEq(ARR_CUST_CLASS, customArray_.getClassName(null));
        Struct[] instance_ = (Struct[]) customArray_.getInstance();
        assertEq(2, instance_.length);
        Struct elt_ = instance_[0];
        assertTrue(elt_.isNull());
        elt_ = instance_[1];
        assertTrue(elt_.isNull());
    }

    @Test
    public void newCustomArray3Test() {
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        Numbers<Integer> dims_ = new Numbers<Integer>(2,3);
        Struct customArray_ = PrimitiveTypeUtil.newCustomArray(CUST_CLASS, dims_, cont_);
        assertEq(ARR_ARR_CUST_CLASS, customArray_.getClassName(null));
        Struct[] instance_ = (Struct[]) customArray_.getInstance();
        assertEq(2, instance_.length);
        Struct subArray_ = instance_[0];
        assertEq(ARR_CUST_CLASS, subArray_.getClassName(null));
        Struct[] subInstance_ = (Struct[]) subArray_.getInstance();
        assertEq(3, subInstance_.length);
        Struct elt_ = subInstance_[0];
        assertTrue(elt_.isNull());
        elt_ = subInstance_[1];
        assertTrue(elt_.isNull());
        elt_ = subInstance_[2];
        assertTrue(elt_.isNull());
        subArray_ = instance_[1];
        assertEq(ARR_CUST_CLASS, subArray_.getClassName(null));
        subInstance_ = (Struct[]) subArray_.getInstance();
        assertEq(3, subInstance_.length);
        elt_ = subInstance_[0];
        assertTrue(elt_.isNull());
        elt_ = subInstance_[1];
        assertTrue(elt_.isNull());
        elt_ = subInstance_[2];
        assertTrue(elt_.isNull());
    }
    @Test
    public void getSuperTypesSet1Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("$boolean"), new StringMap<StringList>(), c_);
        assertEq(3, res_.size());
        assertTrue(res_.containsStr("$boolean"));
        assertTrue(res_.containsStr("java.lang.Boolean"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet2Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("$byte"), new StringMap<StringList>(), c_);
        assertEq(16, res_.size());
        assertTrue(res_.containsStr("$byte"));
        assertTrue(res_.containsStr("$double"));
        assertTrue(res_.containsStr("java.lang.Double"));
        assertTrue(res_.containsStr("java.lang.Number"));
        assertTrue(res_.containsStr("java.lang.Object"));
        assertTrue(res_.containsStr("$float"));
        assertTrue(res_.containsStr("java.lang.Float"));
        assertTrue(res_.containsStr("$long"));
        assertTrue(res_.containsStr("java.lang.Long"));
        assertTrue(res_.containsStr("$int"));
        assertTrue(res_.containsStr("java.lang.Integer"));
        assertTrue(res_.containsStr("$char"));
        assertTrue(res_.containsStr("java.lang.Character"));
        assertTrue(res_.containsStr("$short"));
        assertTrue(res_.containsStr("java.lang.Short"));
        assertTrue(res_.containsStr("java.lang.Byte"));
    }
    @Test
    public void getSuperTypesSet3Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("$char"), new StringMap<StringList>(), c_);
        assertEq(12, res_.size());
        assertTrue(res_.containsStr("$char"));
        assertTrue(res_.containsStr("$double"));
        assertTrue(res_.containsStr("java.lang.Double"));
        assertTrue(res_.containsStr("java.lang.Number"));
        assertTrue(res_.containsStr("java.lang.Object"));
        assertTrue(res_.containsStr("$float"));
        assertTrue(res_.containsStr("java.lang.Float"));
        assertTrue(res_.containsStr("$long"));
        assertTrue(res_.containsStr("java.lang.Long"));
        assertTrue(res_.containsStr("$int"));
        assertTrue(res_.containsStr("java.lang.Integer"));
        assertTrue(res_.containsStr("java.lang.Character"));
    }
    @Test
    public void getSuperTypesSet4Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("java.lang.Boolean"), new StringMap<StringList>(), c_);
        assertEq(2, res_.size());
        assertTrue(res_.containsStr("java.lang.Boolean"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet5Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("java.lang.Byte"), new StringMap<StringList>(), c_);
        assertEq(3, res_.size());
        assertTrue(res_.containsStr("java.lang.Byte"));
        assertTrue(res_.containsStr("java.lang.Number"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet6Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("java.lang.Character"), new StringMap<StringList>(), c_);
        assertEq(2, res_.size());
        assertTrue(res_.containsStr("java.lang.Character"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet7Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[$boolean"), new StringMap<StringList>(), c_);
        assertEq(4, res_.size());
        assertTrue(res_.containsStr("[$boolean"));
        assertTrue(res_.containsStr("[java.lang.Boolean"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet8Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[$byte"), new StringMap<StringList>(), c_);
        assertEq(17, res_.size());
        assertTrue(res_.containsStr("[$byte"));
        assertTrue(res_.containsStr("[$double"));
        assertTrue(res_.containsStr("[java.lang.Double"));
        assertTrue(res_.containsStr("[java.lang.Number"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("[$float"));
        assertTrue(res_.containsStr("[java.lang.Float"));
        assertTrue(res_.containsStr("[$long"));
        assertTrue(res_.containsStr("[java.lang.Long"));
        assertTrue(res_.containsStr("[$int"));
        assertTrue(res_.containsStr("[java.lang.Integer"));
        assertTrue(res_.containsStr("[$char"));
        assertTrue(res_.containsStr("[java.lang.Character"));
        assertTrue(res_.containsStr("[$short"));
        assertTrue(res_.containsStr("[java.lang.Short"));
        assertTrue(res_.containsStr("[java.lang.Byte"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet9Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[$char"), new StringMap<StringList>(), c_);
        assertEq(13, res_.size());
        assertTrue(res_.containsStr("[$char"));
        assertTrue(res_.containsStr("[$double"));
        assertTrue(res_.containsStr("[java.lang.Double"));
        assertTrue(res_.containsStr("[java.lang.Number"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("[$float"));
        assertTrue(res_.containsStr("[java.lang.Float"));
        assertTrue(res_.containsStr("[$long"));
        assertTrue(res_.containsStr("[java.lang.Long"));
        assertTrue(res_.containsStr("[$int"));
        assertTrue(res_.containsStr("[java.lang.Integer"));
        assertTrue(res_.containsStr("[java.lang.Character"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet10Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[java.lang.Boolean"), new StringMap<StringList>(), c_);
        assertEq(3, res_.size());
        assertTrue(res_.containsStr("[java.lang.Boolean"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet11Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[java.lang.Byte"), new StringMap<StringList>(), c_);
        assertEq(4, res_.size());
        assertTrue(res_.containsStr("[java.lang.Byte"));
        assertTrue(res_.containsStr("[java.lang.Number"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet12Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[java.lang.Character"), new StringMap<StringList>(), c_);
        assertEq(3, res_.size());
        assertTrue(res_.containsStr("[java.lang.Character"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("pkg.ExThree"), map_, c_);
        assertEq(2, res_.size());
        assertTrue(res_.containsStr("pkg.ExThree"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("pkg.ExTwo"), map_, c_);
        assertEq(3, res_.size());
        assertTrue(res_.containsStr("pkg.ExTwo"));
        assertTrue(res_.containsStr("pkg.ExThree"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[pkg.ExThree"), map_, c_);
        assertEq(3, res_.size());
        assertTrue(res_.containsStr("[pkg.ExThree"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[pkg.ExTwo"), map_, c_);
        assertEq(4, res_.size());
        assertTrue(res_.containsStr("[pkg.ExTwo"));
        assertTrue(res_.containsStr("[pkg.ExThree"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet17Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("#T"), map_, c_);
        assertEq(3, res_.size());
        assertTrue(res_.containsStr("#T"));
        assertTrue(res_.containsStr("java.lang.Number"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet18Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("S", new StringList("#T"));
        map_.put("T", new StringList("java.lang.Number"));
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("#S"), map_, c_);
        assertEq(4, res_.size());
        assertTrue(res_.containsStr("#S"));
        assertTrue(res_.containsStr("#T"));
        assertTrue(res_.containsStr("java.lang.Number"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet19Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[#T"), map_, c_);
        assertEq(4, res_.size());
        assertTrue(res_.containsStr("[#T"));
        assertTrue(res_.containsStr("[java.lang.Number"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet20Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("S", new StringList("#T"));
        map_.put("T", new StringList("java.lang.Number"));
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[#S"), map_, c_);
        assertEq(5, res_.size());
        assertTrue(res_.containsStr("[#S"));
        assertTrue(res_.containsStr("[#T"));
        assertTrue(res_.containsStr("[java.lang.Number"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("#T"), map_, c_);
        assertEq(4, res_.size());
        assertTrue(res_.containsStr("#T"));
        assertTrue(res_.containsStr("pkg.ExTwo"));
        assertTrue(res_.containsStr("pkg.ExThree"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[#T"), map_, c_);
        assertEq(5, res_.size());
        assertTrue(res_.containsStr("[#T"));
        assertTrue(res_.containsStr("[pkg.ExTwo"));
        assertTrue(res_.containsStr("[pkg.ExThree"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet23Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[[$boolean"), new StringMap<StringList>(), c_);
        assertEq(5, res_.size());
        assertTrue(res_.containsStr("[[$boolean"));
        assertTrue(res_.containsStr("[[java.lang.Boolean"));
        assertTrue(res_.containsStr("[[java.lang.Object"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet24Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[[$byte"), new StringMap<StringList>(), c_);
        assertEq(18, res_.size());
        assertTrue(res_.containsStr("[[$byte"));
        assertTrue(res_.containsStr("[[$double"));
        assertTrue(res_.containsStr("[[java.lang.Double"));
        assertTrue(res_.containsStr("[[java.lang.Number"));
        assertTrue(res_.containsStr("[[$float"));
        assertTrue(res_.containsStr("[[java.lang.Float"));
        assertTrue(res_.containsStr("[[$long"));
        assertTrue(res_.containsStr("[[java.lang.Long"));
        assertTrue(res_.containsStr("[[$int"));
        assertTrue(res_.containsStr("[[java.lang.Integer"));
        assertTrue(res_.containsStr("[[$char"));
        assertTrue(res_.containsStr("[[java.lang.Character"));
        assertTrue(res_.containsStr("[[$short"));
        assertTrue(res_.containsStr("[[java.lang.Short"));
        assertTrue(res_.containsStr("[[java.lang.Byte"));
        assertTrue(res_.containsStr("[[java.lang.Object"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet25Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[[$char"), new StringMap<StringList>(), c_);
        assertEq(14, res_.size());
        assertTrue(res_.containsStr("[[$char"));
        assertTrue(res_.containsStr("[[$double"));
        assertTrue(res_.containsStr("[[java.lang.Double"));
        assertTrue(res_.containsStr("[[java.lang.Number"));
        assertTrue(res_.containsStr("[[$float"));
        assertTrue(res_.containsStr("[[java.lang.Float"));
        assertTrue(res_.containsStr("[[$long"));
        assertTrue(res_.containsStr("[[java.lang.Long"));
        assertTrue(res_.containsStr("[[$int"));
        assertTrue(res_.containsStr("[[java.lang.Integer"));
        assertTrue(res_.containsStr("[[java.lang.Character"));
        assertTrue(res_.containsStr("[[java.lang.Object"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet26Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[[java.lang.Boolean"), new StringMap<StringList>(), c_);
        assertEq(4, res_.size());
        assertTrue(res_.containsStr("[[java.lang.Boolean"));
        assertTrue(res_.containsStr("[[java.lang.Object"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet27Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[[java.lang.Byte"), new StringMap<StringList>(), c_);
        assertEq(5, res_.size());
        assertTrue(res_.containsStr("[[java.lang.Byte"));
        assertTrue(res_.containsStr("[[java.lang.Number"));
        assertTrue(res_.containsStr("[[java.lang.Object"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet28Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[[java.lang.Character"), new StringMap<StringList>(), c_);
        assertEq(4, res_.size());
        assertTrue(res_.containsStr("[[java.lang.Character"));
        assertTrue(res_.containsStr("[[java.lang.Object"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet29Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[[pkg.ExThree"), map_, c_);
        assertEq(4, res_.size());
        assertTrue(res_.containsStr("[[pkg.ExThree"));
        assertTrue(res_.containsStr("[[java.lang.Object"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet30Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[[pkg.ExTwo"), map_, c_);
        assertEq(5, res_.size());
        assertTrue(res_.containsStr("[[pkg.ExTwo"));
        assertTrue(res_.containsStr("[[pkg.ExThree"));
        assertTrue(res_.containsStr("[[java.lang.Object"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet31Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[[#T"), map_, c_);
        assertEq(5, res_.size());
        assertTrue(res_.containsStr("[[#T"));
        assertTrue(res_.containsStr("[[java.lang.Number"));
        assertTrue(res_.containsStr("[[java.lang.Object"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet32Test() {
        ContextEl c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("S", new StringList("#T"));
        map_.put("T", new StringList("java.lang.Number"));
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[[#S"), map_, c_);
        assertEq(6, res_.size());
        assertTrue(res_.containsStr("[[#S"));
        assertTrue(res_.containsStr("[[#T"));
        assertTrue(res_.containsStr("[[java.lang.Number"));
        assertTrue(res_.containsStr("[[java.lang.Object"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        StringList res_ = PrimitiveTypeUtil.getSuperTypesSet(new StringList("[[#T"), map_, c_);
        assertEq(6, res_.size());
        assertTrue(res_.containsStr("[[#T"));
        assertTrue(res_.containsStr("[[pkg.ExTwo"));
        assertTrue(res_.containsStr("[[pkg.ExThree"));
        assertTrue(res_.containsStr("[[java.lang.Object"));
        assertTrue(res_.containsStr("[java.lang.Object"));
        assertTrue(res_.containsStr("java.lang.Object"));
    }
    @Test
    public void getTernarySubclasses1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = PrimitiveTypeUtil.getTernarySubclasses(new StringList("pkg.ExTwo","pkg.ExThree"), map_, c_);
        assertEq(2, res_.size());
        assertTrue(res_.containsStr("pkg.ExTwo"));
        assertTrue(res_.containsStr("pkg.ExThree"));
    }
    @Test
    public void getTernarySubclasses2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = PrimitiveTypeUtil.getTernarySubclasses(new StringList("pkg.ExTwo","pkg.ExThree","pkg.ExFour","pkg.ExFive"), map_, c_);
        assertEq(2, res_.size());
        assertTrue(res_.containsStr("pkg.ExTwo"));
        assertTrue(res_.containsStr("pkg.ExThree"));
    }
    @Test
    public void getTernarySubclasses3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = PrimitiveTypeUtil.getTernarySubclasses(new StringList("pkg.ExTwo","pkg.ExThree"), map_, c_);
        assertEq(2, res_.size());
        assertTrue(res_.containsStr("pkg.ExTwo"));
        assertTrue(res_.containsStr("pkg.ExThree"));
    }
    @Test
    public void getTernarySubclasses4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("pkg.ExThree"));
        StringList res_ = PrimitiveTypeUtil.getTernarySubclasses(new StringList("#T","#S"), map_, c_);
        assertEq(2, res_.size());
        assertTrue(res_.containsStr("#S"));
        assertTrue(res_.containsStr("#T"));
    }
    @Test
    public void getTernarySubclasses5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("pkg.ExThree"));
        StringList res_ = PrimitiveTypeUtil.getTernarySubclasses(new StringList("#T","#S","pkg.ExTwo"), map_, c_);
        assertEq(2, res_.size());
        assertTrue(res_.containsStr("#S"));
        assertTrue(res_.containsStr("#T"));
    }
    @Test
    public void getTernarySubclasses6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        StringList res_ = PrimitiveTypeUtil.getTernarySubclasses(new StringList("#T","#S","pkg.ExTwo"), map_, c_);
        assertEq(1, res_.size());
        assertTrue(res_.containsStr("#S"));
    }
    @Test
    public void getTernarySubclasses7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("pkg.ExThree"));
        StringList res_ = PrimitiveTypeUtil.getTernarySubclasses(new StringList("[#T","[#S"), map_, c_);
        assertEq(2, res_.size());
        assertTrue(res_.containsStr("[#S"));
        assertTrue(res_.containsStr("[#T"));
    }
    @Test
    public void getTernarySubclasses8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("pkg.ExThree"));
        StringList res_ = PrimitiveTypeUtil.getTernarySubclasses(new StringList("[#T","[#S","[pkg.ExTwo"), map_, c_);
        assertEq(2, res_.size());
        assertTrue(res_.containsStr("[#S"));
        assertTrue(res_.containsStr("[#T"));
    }
    @Test
    public void getTernarySubclasses9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        StringList res_ = PrimitiveTypeUtil.getTernarySubclasses(new StringList("[#T","[#S","[pkg.ExTwo"), map_, c_);
        assertEq(1, res_.size());
        assertTrue(res_.containsStr("[#S"));
    }
    @Test
    public void getTernarySubclasses10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        StringList res_ = PrimitiveTypeUtil.getTernarySubclasses(new StringList("[[#T","[#S","[pkg.ExTwo"), map_, c_);
        assertEq(2, res_.size());
        assertTrue(res_.containsStr("[#S"));
        assertTrue(res_.containsStr("[[#T"));
    }
    @Test
    public void getTernarySubclasses11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        StringList res_ = PrimitiveTypeUtil.getTernarySubclasses(new StringList("[#T","[[#S","[pkg.ExTwo"), map_, c_);
        assertEq(2, res_.size());
        assertTrue(res_.containsStr("[[#S"));
        assertTrue(res_.containsStr("[#T"));
    }
    @Test
    public void getResultTernary1Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Integer");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary2Test() {
        StringList one_ = new StringList("java.lang.Integer");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary3Test() {
        StringList one_ = new StringList("");
        StringList two_ = new StringList("java.lang.Integer");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Integer", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary4Test() {
        StringList one_ = new StringList("java.lang.Integer");
        StringList two_ = new StringList("");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Integer", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary5Test() {
        StringList one_ = new StringList("");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Integer", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary6Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Integer", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary7Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary8Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary9Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("$short");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary10Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary11Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary12Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary13Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$short");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary14Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary15Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$byte", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary16Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$byte", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary17Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary18Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary19Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$char");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary20Test() {
        StringList one_ = new StringList("$char");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary21Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary22Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary23Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary24Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary25Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary26Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary27Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary28Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary29Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$char");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary30Test() {
        StringList one_ = new StringList("$char");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary31Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary32Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary33Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary34Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary35Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$char");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary36Test() {
        StringList one_ = new StringList("$char");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary37Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary38Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary39Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary40Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary41Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$char");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary42Test() {
        StringList one_ = new StringList("$char");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary43Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$char");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(80000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary44Test() {
        StringList one_ = new StringList("$char");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(80000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary45Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$byte", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary46Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$byte", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary47Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary48Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary49Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Character");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary50Test() {
        StringList one_ = new StringList("java.lang.Character");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary51Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary52Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary53Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary54Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary55Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary56Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary57Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary58Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary59Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Character");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary60Test() {
        StringList one_ = new StringList("java.lang.Character");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary61Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary62Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary63Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary64Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary65Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Character");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary66Test() {
        StringList one_ = new StringList("java.lang.Character");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary67Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary68Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary69Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary70Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary71Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Character");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary72Test() {
        StringList one_ = new StringList("java.lang.Character");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary73Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Character");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(80000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary74Test() {
        StringList one_ = new StringList("java.lang.Character");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(80000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary75Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary76Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary77Test() {
        StringList one_ = new StringList("$double");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new DoubleStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$double", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary78Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$double");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new DoubleStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$double", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary79Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList one_ = new StringList("pkg.ExTwo");
        StringList two_ = new StringList("pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(2, res_.getTypes().size());
        assertTrue(res_.getTypes().containsStr("pkg.ExFour"));
        assertTrue(res_.getTypes().containsStr("pkg.ExFive"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary80Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList one_ = new StringList("[pkg.ExTwo");
        StringList two_ = new StringList("[pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(2, res_.getTypes().size());
        assertTrue(res_.getTypes().containsStr("[pkg.ExFour"));
        assertTrue(res_.getTypes().containsStr("[pkg.ExFive"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary81Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        StringList one_ = new StringList("#T");
        StringList two_ = new StringList("pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(2, res_.getTypes().size());
        assertTrue(res_.getTypes().containsStr("pkg.ExFour"));
        assertTrue(res_.getTypes().containsStr("pkg.ExFive"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary82Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        StringList one_ = new StringList("[pkg.ExTwo");
        StringList two_ = new StringList("[pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(2, res_.getTypes().size());
        assertTrue(res_.getTypes().containsStr("[pkg.ExFour"));
        assertTrue(res_.getTypes().containsStr("[pkg.ExFive"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary83Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        StringList one_ = new StringList("#T");
        StringList two_ = new StringList("#S");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertTrue(res_.getTypes().containsStr("#T"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary84Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        StringList one_ = new StringList("[#T");
        StringList two_ = new StringList("[#S");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertTrue(res_.getTypes().containsStr("[#T"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary85Test() {
        StringList one_ = new StringList("java.lang.Integer");
        StringList two_ = new StringList("java.lang.Integer");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Integer", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary86Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Number");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Number", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary87Test() {
        StringList one_ = new StringList("java.lang.Number");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Number", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary88Test() {
        StringList one_ = new StringList("java.lang.Integer");
        StringList two_ = new StringList("java.lang.Number");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Number", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary89Test() {
        StringList one_ = new StringList("java.lang.Number");
        StringList two_ = new StringList("java.lang.Integer");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Number", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary90Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour<java.lang.Number>:pkg.ExFive<java.lang.Object>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour<java.lang.Number>:pkg.ExFive<java.lang.Object>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive<#U> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList one_ = new StringList("pkg.ExTwo");
        StringList two_ = new StringList("pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(2, res_.getTypes().size());
        assertTrue(res_.getTypes().containsStr("pkg.ExFour<java.lang.Number>"));
        assertTrue(res_.getTypes().containsStr("pkg.ExFive<java.lang.Object>"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary91Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour<java.lang.Number>:pkg.ExFive<java.lang.Number>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour<java.lang.Number>:pkg.ExFive<java.lang.Object>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive<#U> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList one_ = new StringList("pkg.ExTwo");
        StringList two_ = new StringList("pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertTrue(res_.getTypes().containsStr("pkg.ExFour<java.lang.Number>"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary92Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour<java.lang.Object>:pkg.ExFive<java.lang.Number>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour<java.lang.Number>:pkg.ExFive<java.lang.Object>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive<#U> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList one_ = new StringList("pkg.ExTwo");
        StringList two_ = new StringList("pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertTrue(res_.getTypes().containsStr("java.lang.Object"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary93Test() {
        StringList one_ = new StringList("$Fct<java.lang.Number>");
        StringList two_ = new StringList("$Fct<java.lang.Integer>");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$Fct<java.lang.Number>", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary94Test() {
        StringList one_ = new StringList("$Fct<java.lang.Integer>");
        StringList two_ = new StringList("$Fct<java.lang.Number>");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$Fct<java.lang.Number>", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary95Test() {
        StringList one_ = new StringList("$Fct<#T>");
        StringList two_ = new StringList("$Fct<#U>");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        map_.put("U", new StringList("#T"));
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$Fct<#T>", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary96Test() {
        StringList one_ = new StringList("$Fct<#U>");
        StringList two_ = new StringList("$Fct<#T>");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        map_.put("U", new StringList("#T"));
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$Fct<#T>", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary97Test() {
        StringList one_ = new StringList("$Fct<#T>");
        StringList two_ = new StringList("$Fct<java.lang.Number>");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$Fct<java.lang.Number>", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary98Test() {
        StringList one_ = new StringList("$Fct<java.lang.Number>");
        StringList two_ = new StringList("$Fct<#T>");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = PrimitiveTypeUtil.getResultTernary(one_, argOne_, two_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$Fct<java.lang.Number>", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    private ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        cont_.getOptions().setSuffixVar(VariableSuffix.DISTINCT);
        Classes classes_;
        classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        return cont_;
    }
    private ContextEl simpleContextEl() {
        ContextEl cont_ = new ContextEl();
        cont_.getOptions().setSuffixVar(VariableSuffix.DISTINCT);
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        return cont_;
    }
}
