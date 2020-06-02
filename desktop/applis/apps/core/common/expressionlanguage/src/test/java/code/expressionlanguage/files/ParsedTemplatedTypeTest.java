package code.expressionlanguage.files;

import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;

public final class ParsedTemplatedTypeTest {

    @Test
    public void parse1Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        p_.parse("pkgtwo.MyClassTwo<Type>After");
        assertEq("pkgtwo.MyClassTwo<Type>",p_.getInstruction().toString());
        assertEq(23, p_.getCurrent());
    }

    @Test
    public void parse2Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        p_.parse("pkgtwo.MyClassTwo<Type,Second>After");
        assertEq("pkgtwo.MyClassTwo<Type,Second>",p_.getInstruction().toString());
        assertEq(30, p_.getCurrent());
    }

    @Test
    public void parse3Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        p_.parse("pkgtwo.MyClassTwo<Type,Second[]>After");
        assertEq("pkgtwo.MyClassTwo<Type,Second[]>",p_.getInstruction().toString());
        assertEq(32, p_.getCurrent());
    }


    @Test
    public void parse4Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        p_.parse("pkgtwo.MyClassTwo<Type<Param>>After");
        assertEq("pkgtwo.MyClassTwo<Type<Param>>",p_.getInstruction().toString());
        assertEq(30, p_.getCurrent());
    }

    @Test
    public void parse5Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        p_.parse("pkgtwo.MyClassTwo<Type<Param>.Inner>After");
        assertEq("pkgtwo.MyClassTwo<Type<Param>.Inner>",p_.getInstruction().toString());
        assertEq(36, p_.getCurrent());
    }

    @Test
    public void parse6Test() {
        StringBuilder file_ = new StringBuilder();
        file_.append("pkgtwo.MyClassTwo");
        int index_ = "pkgtwo.MyClassTwo".length();
        ParsedTemplatedType p_ = new ParsedTemplatedType(file_,index_);
        p_.parse("pkgtwo.MyClassTwo<Type");
        assertEq("pkgtwo.MyClassTwo<Type",p_.getInstruction().toString());
        assertEq(22, p_.getCurrent());
    }
}
