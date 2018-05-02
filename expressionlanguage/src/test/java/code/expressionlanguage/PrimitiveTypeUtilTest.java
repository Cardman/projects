package code.expressionlanguage;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.methods.Classes;
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

    private ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        Classes classes_;
        classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        Classes.tryBuildBracedClassesBodies(_files, cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        classes_.validateInheritingClasses(cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        return cont_;
    }
    private ContextEl simpleContextEl() {
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        return cont_;
    }
}
