package code.expressionlanguage.analyze.files;

import code.expressionlanguage.EquallableElUtil;
import org.junit.Test;

public final class ParsedTypeTest extends EquallableElUtil {

    @Test
    public void parse1Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<Type>After");
        assertEq("pkgtwo.MyClassTwo<Type>",p_.getInstruction().toString());
        assertEq(23, p_.getCurrent());
        assertTrue(p_.isOk());
    }

    @Test
    public void parse1_Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<Type> After");
        assertEq("pkgtwo.MyClassTwo<Type>",p_.getInstruction().toString());
        assertEq(23, p_.getCurrent());
        assertTrue(p_.isOk());
    }

    @Test
    public void parse2Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo After");
        assertEq("pkgtwo.MyClassTwo",p_.getInstruction().toString());
        assertEq(17, p_.getCurrent());
        assertTrue(p_.isOk());
    }

    @Test
    public void parse3Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo[]After");
        assertEq("pkgtwo.MyClassTwo[]",p_.getInstruction().toString());
        assertEq(19, p_.getCurrent());
        assertTrue(p_.isOk());
    }

    @Test
    public void parse3_Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo[] After");
        assertEq("pkgtwo.MyClassTwo[]",p_.getInstruction().toString());
        assertEq(19, p_.getCurrent());
        assertTrue(p_.isOk());
    }

    @Test
    public void parse4Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo[][]After");
        assertEq("pkgtwo.MyClassTwo[][]",p_.getInstruction().toString());
        assertEq(21, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse5Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<Type>[]After");
        assertEq("pkgtwo.MyClassTwo<Type>[]",p_.getInstruction().toString());
        assertEq(25, p_.getCurrent());
        assertTrue(p_.isOk());
    }

    @Test
    public void parse6Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<Type>[][]After");
        assertEq("pkgtwo.MyClassTwo<Type>[][]",p_.getInstruction().toString());
        assertEq(27, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse7Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<Type>.Inner[]After");
        assertEq("pkgtwo.MyClassTwo<Type>.Inner[]",p_.getInstruction().toString());
        assertEq(31, p_.getCurrent());
        assertTrue(p_.isOk());
    }

    @Test
    public void parse8Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<Type>.Inner[][]After");
        assertEq("pkgtwo.MyClassTwo<Type>.Inner[][]",p_.getInstruction().toString());
        assertEq(33, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse9Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<Type>.Inner After");
        assertEq("pkgtwo.MyClassTwo<Type>.Inner",p_.getInstruction().toString());
        assertEq(29, p_.getCurrent());
        assertTrue(p_.isOk());
    }

    @Test
    public void parse10Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<Type>.Inner After");
        assertEq("pkgtwo.MyClassTwo<Type>.Inner",p_.getInstruction().toString());
        assertEq(29, p_.getCurrent());
        assertTrue(p_.isOk());
    }

    @Test
    public void parse11Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<One,Two>.Inner After");
        assertEq("pkgtwo.MyClassTwo<One,Two>.Inner",p_.getInstruction().toString());
        assertEq(32, p_.getCurrent());
        assertTrue(p_.isOk());
    }

    @Test
    public void parse12Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo. MyClassTwo After");
        assertEq("pkgtwo. MyClassTwo",p_.getInstruction().toString());
        assertEq(18, p_.getCurrent());
        assertTrue(p_.isOk());
    }

    @Test
    public void parse13Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo .MyClassTwo After");
        assertEq("pkgtwo .MyClassTwo",p_.getInstruction().toString());
        assertEq(18, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse14Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<One,Two>. Inner After");
        assertEq("pkgtwo.MyClassTwo<One,Two>. Inner",p_.getInstruction().toString());
        assertEq(33, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse15Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<One,Two> .Inner After");
        assertEq("pkgtwo.MyClassTwo<One,Two> .Inner",p_.getInstruction().toString());
        assertEq(33, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse16Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<One,Two>..Inner After");
        assertEq("pkgtwo.MyClassTwo<One,Two>..Inner",p_.getInstruction().toString());
        assertEq(33, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse17Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("pkgtwo.MyClassTwo<One,Two>...After");
        assertEq("pkgtwo.MyClassTwo<One,Two>...",p_.getInstruction().toString());
        assertEq(29, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse18Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("tab[0]a");
        assertEq("tab[",p_.getInstruction().toString());
        assertEq(4, p_.getCurrent());
        assertTrue(!p_.isOk());
    }
    @Test
    public void parse19Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp<U<Val >> [] []a");
        assertEq("Tmp<U<Val >> [] []",p_.getInstruction().toString());
        assertEq(18, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse20Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp<U<Val >> [] [] a");
        assertEq("Tmp<U<Val >> [] []",p_.getInstruction().toString());
        assertEq(18, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse21Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp<U<Val >> [] [] ...a");
        assertEq("Tmp<U<Val >> [] [] ...",p_.getInstruction().toString());
        assertEq(22, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse22Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp<U<Val >> ...a");
        assertEq("Tmp<U<Val >> ...",p_.getInstruction().toString());
        assertEq(16, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse23Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp<U<Val >> [ ] ...a");
        assertEq("Tmp<U<Val >> [ ] ...",p_.getInstruction().toString());
        assertEq(20, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse24Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp<U<Val[ ]>> a");
        assertEq("Tmp<U<Val[ ]>>",p_.getInstruction().toString());
        assertEq(14, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse25Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp.");
        assertEq("Tmp.",p_.getInstruction().toString());
        assertEq(4, p_.getCurrent());
        assertTrue(!p_.isOk());
    }
    @Test
    public void parse26Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp<Type>.");
        assertEq("Tmp<Type>",p_.getInstruction().toString());
        assertEq(9, p_.getCurrent());
        assertTrue(!p_.isOk());
    }
    @Test
    public void parse27Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp [] []a");
        assertEq("Tmp [] []",p_.getInstruction().toString());
        assertEq(9, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse28Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp < One , Two > . Next . NextTwo < Three , Four > .. After .. AfterTwo < Five , Six > a");
        assertEq("Tmp < One , Two > . Next . NextTwo < Three , Four > .. After .. AfterTwo < Five , Six >",p_.getInstruction().toString());
        assertEq(87, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse29Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp < One , Two > . Next . NextTwo < Three , Four > .. After [ ] a");
        assertEq("Tmp < One , Two > . Next . NextTwo < Three , Four > .. After [ ]",p_.getInstruction().toString());
        assertEq(64, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse30Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp < One , Two > . Next [ ] a");
        assertEq("Tmp < One , Two > . Next [ ]",p_.getInstruction().toString());
        assertEq(28, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse31Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp < One , Two > . Next , a");
        assertEq("Tmp < One , Two > . Next",p_.getInstruction().toString());
        assertEq(24, p_.getCurrent());
        assertTrue(!p_.isOk());
    }
    @Test
    public void parse32Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp < One , Two > . Next . NextTwo < Three , Four > .. After [ ] [ ] a");
        assertEq("Tmp < One , Two > . Next . NextTwo < Three , Four > .. After [ ] [ ]",p_.getInstruction().toString());
        assertEq(68, p_.getCurrent());
        assertTrue(p_.isOk());
    }
    @Test
    public void parse33Test() {
        ParsedType p_ = new ParsedType();
        p_.parse("Tmp < One , Two > . Next [ ] [ ] a");
        assertEq("Tmp < One , Two > . Next [ ] [ ]",p_.getInstruction().toString());
        assertEq(32, p_.getCurrent());
        assertTrue(p_.isOk());
    }

}
