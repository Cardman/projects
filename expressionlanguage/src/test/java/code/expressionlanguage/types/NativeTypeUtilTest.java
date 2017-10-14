package code.expressionlanguage.types;

import static code.expressionlanguage.EquallableElUtil.assertEq;

import org.junit.Test;

@SuppressWarnings("static-method")
public class NativeTypeUtilTest {

    @Test
    public void getPrettyType1Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getField("SIMPLE_CLASS").getGenericType());
        assertEq("java.lang.String", res_);
    }
    @Test
    public void getPrettyType2Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getField("SIMPLE_ARRAY").getGenericType());
        assertEq("[java.lang.String", res_);
    }
    @Test
    public void getPrettyType3Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getField("TEMPLATE").getGenericType());
        assertEq("code.util.CustList<java.lang.String>", res_);
    }
    @Test
    public void getPrettyType4Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getField("TEMPLATE_DOUBLE").getGenericType());
        assertEq("code.util.IdMap<java.lang.String,java.lang.String>", res_);
    }
    @Test
    public void getPrettyType5Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getField("TEMPLATE_COMPLEX").getGenericType());
        assertEq("code.util.IdMap<code.util.IdMap<java.lang.String,java.lang.String>,java.lang.String>", res_);
    }
    @Test
    public void getPrettyType6Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getField("TEMPLATE_ARRAY").getGenericType());
        assertEq("code.util.CustList<[java.lang.String>", res_);
    }
    @Test
    public void getPrettyType7Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getField("ARRAY_TEMPLATE").getGenericType());
        assertEq("[code.util.CustList<java.lang.String>", res_);
    }
    @Test
    public void getPrettyType8Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getMethod("returnMethod").getGenericReturnType());
        assertEq("#E", res_);
    }
    @Test
    public void getPrettyType9Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getMethod("returnArrMethod").getGenericReturnType());
        assertEq("[#E", res_);
    }
    @Test
    public void getPrettyType10Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getField("TEMPLATE_COMPLEX_ARR").getGenericType());
        assertEq("code.util.IdMap<[java.lang.String,java.lang.String>", res_);
    }
    @Test
    public void getPrettyType11Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getMethod("returnGeneMethod").getGenericReturnType());
        assertEq("code.util.CustList<#E>", res_);
    }
    @Test
    public void getPrettyType12Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getMethod("returnGeneArrMethod").getGenericReturnType());
        assertEq("code.util.CustList<[#E>", res_);
    }
    @Test
    public void getPrettyType13Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getField("TEMPLATE_DOUBLE_END").getGenericType());
        assertEq("code.util.CustList<code.util.CustList<java.lang.String>>", res_);
    }
    @Test
    public void getPrettyType14Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getField("INT_CST").getGenericType());
        assertEq("$int", res_);
    }
    @Test
    public void getPrettyType15Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getField("INT_ARR_CST").getGenericType());
        assertEq("[$int", res_);
    }
    @Test
    public void getPrettyType16Test() {
        String res_ = NativeTypeUtil.getPrettyType(TypeCases.getField("INT_GENE_ARR_CST").getGenericType());
        assertEq("code.util.CustList<[$int>", res_);
    }
}
