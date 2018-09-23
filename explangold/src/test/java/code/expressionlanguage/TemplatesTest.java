package code.expressionlanguage;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.StringList;

@SuppressWarnings("static-method")
public class TemplatesTest {

    @Test
    public void getTypes1Test(){
        assertEq(new StringList(), Templates.getTypes("String"));
    }
    @Test
    public void getTypes2Test(){
        assertEq(new StringList("String","Rate"), Templates.getTypes("Map<String,Rate>"));
    }
    @Test
    public void getTypes3Test(){
        assertEq(new StringList("String","Map<String,Rate>"), Templates.getTypes("Map<String,Map<String,Rate>>"));
    }
    @Test
    public void getTypes4Test(){
        assertEq(new StringList("Boolean"), Templates.getTypes("List<Boolean>"));
    }
    @Test
    public void getTypes5Test(){
        assertEq(new StringList("BooleanList"), Templates.getTypes("CustList<BooleanList>"));
    }
    @Test
    public void isCorrectWrite1Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("java.lang.String", context_));
    }

    @Test
    public void isCorrectWrite2Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite(context_.getStandards().getAliasPrimInteger(), context_));
    }

    @Test
    public void isCorrectWrite3Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("[java.lang.String", context_));
    }

    @Test
    public void isCorrectWrite4Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("[$int", context_));
    }

    @Test
    public void isCorrectWrite5Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("#E", context_));
    }

    @Test
    public void isCorrectWrite6Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("[#E", context_));
    }

    @Test
    public void isCorrectWrite7Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("code.util.CustList<#E>", context_));
    }

    @Test
    public void isCorrectWrite8Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("code.util.CustList<[#E>", context_));
    }

    @Test
    public void isCorrectWrite9Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("code.util.CustList<[$int>", context_));
    }

    @Test
    public void isCorrectWrite10Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("code.util.CustList<java.lang.String>", context_));
    }

    @Test
    public void isCorrectWrite11Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("code.util.CustList<code.util.CustList<java.lang.String>>", context_));
    }

    @Test
    public void isCorrectWrite12Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("code.util.CustList<code.util.CustList<java.lang.String>,java.lang.String>", context_));
    }

    @Test
    public void isCorrectWrite13Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("code.util.CustList<code.util.CustList<java.lang.String,java.lang.String>>", context_));
    }

    @Test
    public void isCorrectWrite15Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<code.util.CustList<java.lang.String,java.lang.String>>>", context_));
    }

    @Test
    public void isCorrectWrite16Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<$int>", context_));
    }

    @Test
    public void isCorrectWrite17Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<code.util.CustList<$int,java.lang.String>>", context_));
    }

    @Test
    public void isCorrectWrite18Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("code.util.CustList<[$int.int>", context_));
    }

    @Test
    public void isCorrectWrite19Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(Templates.isCorrectWrite("code.util.CustList<code.util.CustList<[$int.int,java.lang.String>>", context_));
    }

    @Test
    public void isCorrectWrite20Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<#E.int>", context_));
    }

    @Test
    public void isCorrectWrite21Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<#E<java.lang.String>>", context_));
    }

    @Test
    public void isCorrectWrite22Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<,>", context_));
    }

    @Test
    public void isCorrectWrite23Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<java.lang;String>", context_));
    }

    @Test
    public void isCorrectWrite24Test() {
        ContextEl context_ = simpleContextEl();
        assertTrue(!Templates.isCorrectWrite("code.util.CustList<<java.lang.String>>", context_));
    }

    private ContextEl simpleContextEl() {
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        return cont_;
    }
}
