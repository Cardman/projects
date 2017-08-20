package code.expressionlanguage;

import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.classes.MyDerivedInterface;
import code.expressionlanguage.classes.MyImplClass;
import code.expressionlanguage.classes.MyInterface;
import code.expressionlanguage.classes.MySecImplClass;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.Struct;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class PrimitiveTypeUtilTest {

    private static final String CUST_CLASS = "pkg.CustClass";
    private static final String PUBLIC_ACCESS = "PUBLIC";

    @Test
    public void canBeUseAsArgument1Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(PrimitiveTypeUtil.PRIM_BYTE, PrimitiveTypeUtil.PRIM_BYTE, null));
    }

    @Test
    public void canBeUseAsArgument2Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(PrimitiveTypeUtil.PRIM_INT, PrimitiveTypeUtil.PRIM_BYTE, null));
    }

    @Test
    public void canBeUseAsArgument3Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(Object.class.getName(), PrimitiveTypeUtil.PRIM_BYTE, null));
    }

    @Test
    public void canBeUseAsArgument4Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(Byte.class.getName(), PrimitiveTypeUtil.PRIM_BYTE, null));
    }

    @Test
    public void canBeUseAsArgument5Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(Number.class.getName(), PrimitiveTypeUtil.PRIM_BYTE, null));
    }

    @Test
    public void canBeUseAsArgument6Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(Number.class.getName(), Byte.class.getName(), null));
    }

    @Test
    public void canBeUseAsArgument7Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(Boolean.class.getName(), PrimitiveTypeUtil.PRIM_BOOLEAN, null));
    }

    @Test
    public void canBeUseAsArgument8Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(PrimitiveTypeUtil.PRIM_BOOLEAN, PrimitiveTypeUtil.PRIM_BOOLEAN, null));
    }

    @Test
    public void canBeUseAsArgument9Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(Object.class.getName());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_INT);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument10Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(Integer.class.getName());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_INT);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument11Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(Number.class.getName());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_INT);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument12Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_INT);
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_BYTE);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument13Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(Object.class.getName());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_BYTE);
        arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(arrInt_);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument14Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(Object.class.getName());
        arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(arrObj_);
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_BYTE);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument15Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(Boolean.class.getName(), Boolean.class.getName(), null));
    }

    @Test
    public void canBeUseAsArgument16Test() {
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(Number.class.getName(), Boolean.class.getName(), null));
    }

    @Test
    public void canBeUseAsArgument17Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_BYTE);
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_INT);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument18Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(Long.class.getName());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_INT);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument19Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(Byte.class.getName());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_INT);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument20Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(Number.class.getName());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_CHAR);
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument21Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(Character.class.getName());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_CHAR);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument22Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(Object.class.getName());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(Character.class.getName());
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument23Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(Object.class.getName());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_CHAR);
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument24Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(Long.class.getName());
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(Integer.class.getName());
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument25Test() {
        String arrObj_ = PrimitiveTypeUtil.PRIM_INT;
        String arrInt_ = Integer.class.getName();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument26Test() {
        String arrObj_ = PrimitiveTypeUtil.PRIM_BYTE;
        String arrInt_ = Integer.class.getName();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument27Test() {
        String arrObj_ = PrimitiveTypeUtil.PRIM_LONG;
        String arrInt_ = Integer.class.getName();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument28Test() {
        String arrObj_ = PrimitiveTypeUtil.getPrettyArrayType(PrimitiveTypeUtil.PRIM_INT);
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(Integer.class.getName());
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument29Test() {
        String arrObj_ = Number.class.getName();
        String arrInt_ = PrimitiveTypeUtil.getPrettyArrayType(Integer.class.getName());
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(arrObj_, arrInt_, null));
    }

    @Test
    public void canBeUseAsArgument30Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExFour","pkg.ExFour",classes_));
    }

    @Test
    public void canBeUseAsArgument31Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExFour","pkg.ExTwo",classes_));
    }

    @Test
    public void canBeUseAsArgument32Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExTwo","pkg.ExFour",classes_));
    }

    @Test
    public void canBeUseAsArgument33Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExTwo",Integer.class.getName(),classes_));
    }

    @Test
    public void canBeUseAsArgument34Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(Number.class.getName(),Integer.class.getName(),classes_));
    }

    @Test
    public void canBeUseAsArgument35Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("[pkg.ExFour","[pkg.ExFour",classes_));
    }

    @Test
    public void canBeUseAsArgument36Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("[pkg.ExFour","[pkg.ExTwo",classes_));
    }

    @Test
    public void canBeUseAsArgument37Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("[pkg.ExTwo","[pkg.ExFour",classes_));
    }

    @Test
    public void canBeUseAsArgument38Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("[pkg.ExTwo","[[pkg.ExTwo",classes_));
    }

    @Test
    public void canBeUseAsArgument39Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("[[pkg.ExTwo","[pkg.ExTwo",classes_));
    }

    @Test
    public void canBeUseAsArgument40Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("["+Object.class.getName(),"[pkg.ExTwo",classes_));
    }

    @Test
    public void canBeUseAsArgument41Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("[["+Object.class.getName(),"[pkg.ExTwo",classes_));
    }

    @Test
    public void canBeUseAsArgument42Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExTwo","[pkg.ExTwo",classes_));
    }

    @Test
    public void canBeUseAsArgument43Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExTwo",null,classes_));
    }

    @Test
    public void canBeUseAsArgument44Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(Object.class.getName(),null,classes_));
    }

    @Test
    public void canBeUseAsArgument45Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(Integer.class.getName(),null,classes_));
    }

    @Test
    public void canBeUseAsArgument46Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(PrimitiveTypeUtil.PRIM,null,classes_));
    }

    @Test
    public void canBeUseAsArgument47Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExFour'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(OperationNode.VOID_RETURN,null,classes_));
    }

    @Test
    public void canBeUseAsArgument48Test() {
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(MyInterface.class.getName(), PrimitiveTypeUtil.PRIM_BYTE, null));
    }

    @Test
    public void canBeUseAsArgument49Test() {
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(MyInterface.class.getName(), Byte.class.getName(), null));
    }

    @Test
    public void canBeUseAsArgument50Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(Object.class.getName(), MyInterface.class.getName(), null));
    }

    @Test
    public void canBeUseAsArgument51Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(MyInterface.class.getName(), MyInterface.class.getName(), null));
    }

    @Test
    public void canBeUseAsArgument52Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(MyInterface.class.getName(), MyDerivedInterface.class.getName(), null));
    }

    @Test
    public void canBeUseAsArgument53Test() {
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(MyDerivedInterface.class.getName(), MyInterface.class.getName(), null));
    }

    @Test
    public void canBeUseAsArgument54Test() {
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(MyDerivedInterface.class.getName(), MySecImplClass.class.getName(), null));
    }

    @Test
    public void canBeUseAsArgument55Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(MyDerivedInterface.class.getName(), MyImplClass.class.getName(), null));
    }

    @Test
    public void canBeUseAsArgument56Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(MyInterface.class.getName(), MyImplClass.class.getName(), null));
    }

    @Test
    public void canBeUseAsArgument57Test() {
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(MyInterface.class.getName(), Object.class.getName(), null));
    }

    @Test
    public void canBeUseAsArgument58Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("pkg.Ex","pkg.ExFour",classes_));
    }

    @Test
    public void canBeUseAsArgument59Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExFour","pkg.Ex",classes_));
    }

    @Test
    public void canBeUseAsArgument60Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("pkg.ExFour","pkg.ExFour",classes_));
    }

    @Test
    public void canBeUseAsArgument61Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument("[pkg.Ex","[pkg.ExFour",classes_));
    }

    @Test
    public void canBeUseAsArgument62Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("[pkg.ExFour","[pkg.Ex",classes_));
    }

    @Test
    public void canBeUseAsArgument63Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument("[pkg.ExFour","[pkg.ExFour",classes_));
    }

    @Test
    public void canBeUseAsArgument64Test() {
        assertTrue(PrimitiveTypeUtil.canBeUseAsArgument(PrimitiveTypeUtil.PRIM_INT, PrimitiveTypeUtil.PRIM_CHAR, null));
    }

    @Test
    public void canBeUseAsArgument65Test() {
        assertTrue(!PrimitiveTypeUtil.canBeUseAsArgument(PrimitiveTypeUtil.PRIM_CHAR, PrimitiveTypeUtil.PRIM_INT, null));
    }

    @Test
    public void getSubclasses1Test() {
        StringList classes_ = new StringList(Integer.class.getName(), Number.class.getName());
        StringList sub_ = PrimitiveTypeUtil.getSubclasses(classes_, null);
        assertEq(1, sub_.size());
        assertEq(Integer.class.getName(), sub_.get(0));
    }

    @Test
    public void getSubclasses2Test() {
        StringList classes_ = new StringList(String.class.getName(), Number.class.getName());
        StringList sub_ = PrimitiveTypeUtil.getSubclasses(classes_, null);
        assertEq(2, sub_.size());
        assertEq(String.class.getName(), sub_.get(0));
        assertEq(Number.class.getName(), sub_.get(1));
    }

    @Test
    public void getSubclasses3Test() {
        StringList classes_ = new StringList(OperationNode.VOID_RETURN, OperationNode.VOID_RETURN);
        StringList sub_ = PrimitiveTypeUtil.getSubclasses(classes_, null);
        assertEq(1, sub_.size());
        assertEq(OperationNode.VOID_RETURN, sub_.get(0));
    }

    @Test
    public void newCustomArray1Test() {
        Numbers<Integer> dims_ = new Numbers<Integer>(1);
        Struct customArray_ = PrimitiveTypeUtil.newCustomArray(CUST_CLASS, dims_);
        assertEq("["+CUST_CLASS, customArray_.getClassName());
        Struct[] instance_ = (Struct[]) customArray_.getInstance();
        assertEq(1, instance_.length);
        Struct elt_ = instance_[0];
        assertTrue(elt_.isNull());
    }

    @Test
    public void newCustomArray2Test() {
        Numbers<Integer> dims_ = new Numbers<Integer>(2);
        Struct customArray_ = PrimitiveTypeUtil.newCustomArray(CUST_CLASS, dims_);
        assertEq("["+CUST_CLASS, customArray_.getClassName());
        Struct[] instance_ = (Struct[]) customArray_.getInstance();
        assertEq(2, instance_.length);
        Struct elt_ = instance_[0];
        assertTrue(elt_.isNull());
        elt_ = instance_[1];
        assertTrue(elt_.isNull());
    }

    @Test
    public void newCustomArray3Test() {
        Numbers<Integer> dims_ = new Numbers<Integer>(2,3);
        Struct customArray_ = PrimitiveTypeUtil.newCustomArray(CUST_CLASS, dims_);
        assertEq("[["+CUST_CLASS, customArray_.getClassName());
        Struct[] instance_ = (Struct[]) customArray_.getInstance();
        assertEq(2, instance_.length);
        Struct subArray_ = instance_[0];
        assertEq("["+CUST_CLASS, subArray_.getClassName());
        Struct[] subInstance_ = (Struct[]) subArray_.getInstance();
        assertEq(3, subInstance_.length);
        Struct elt_ = subInstance_[0];
        assertTrue(elt_.isNull());
        elt_ = subInstance_[1];
        assertTrue(elt_.isNull());
        elt_ = subInstance_[2];
        assertTrue(elt_.isNull());
        subArray_ = instance_[1];
        assertEq("["+CUST_CLASS, subArray_.getClassName());
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
        Classes classes_ = new Classes(_files, cont_);
        cont_.setClasses(classes_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateInheritingClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        return cont_;
    }
}
