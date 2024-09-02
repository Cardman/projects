package code.expressionlanguage.common;

import code.expressionlanguage.methods.*;
import org.junit.Test;

public final class StringDataUtilTest extends ProcessMethodCommon {
    @Test
    public void d1(){
        assertEq(15,StringDataUtil.getType((char)0));
        assertEq(9,StringDataUtil.getDirectionality((char)0));
        assertFalse(StringDataLetterUtil.isLetter((char)0));
        assertFalse(StringDataUtil.isLetterOrDigit((char)0));
    }
    @Test
    public void d2(){
        assertEq(12,StringDataUtil.getType((char)32));
        assertEq(12,StringDataUtil.getDirectionality((char)32));
        assertFalse(StringDataLetterUtil.isLetter((char)32));
        assertFalse(StringDataUtil.isLetterOrDigit((char)32));
    }
    @Test
    public void d3(){
        assertEq(24,StringDataUtil.getType((char)33));
        assertEq(13,StringDataUtil.getDirectionality((char)33));
        assertFalse(StringDataLetterUtil.isLetter((char)33));
        assertFalse(StringDataUtil.isLetterOrDigit((char)33));
    }
    @Test
    public void d4(){
        assertEq(26,StringDataUtil.getType((char)36));
        assertEq(5,StringDataUtil.getDirectionality((char)36));
        assertFalse(StringDataLetterUtil.isLetter((char)36));
        assertFalse(StringDataUtil.isLetterOrDigit((char)36));
    }
    @Test
    public void d5(){
        assertEq(24,StringDataUtil.getType((char)37));
        assertEq(5,StringDataUtil.getDirectionality((char)37));
        assertFalse(StringDataLetterUtil.isLetter((char)37));
        assertFalse(StringDataUtil.isLetterOrDigit((char)37));
    }
    @Test
    public void d6(){
        assertEq(21,StringDataUtil.getType((char)40));
        assertEq(13,StringDataUtil.getDirectionality((char)40));
        assertFalse(StringDataLetterUtil.isLetter((char)40));
        assertFalse(StringDataUtil.isLetterOrDigit((char)40));
    }
    @Test
    public void d7(){
        assertEq(22,StringDataUtil.getType((char)41));
        assertEq(13,StringDataUtil.getDirectionality((char)41));
        assertFalse(StringDataLetterUtil.isLetter((char)41));
        assertFalse(StringDataUtil.isLetterOrDigit((char)41));
    }
    @Test
    public void d8(){
        assertEq(24,StringDataUtil.getType((char)42));
        assertEq(13,StringDataUtil.getDirectionality((char)42));
        assertFalse(StringDataLetterUtil.isLetter((char)42));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42));
    }
    @Test
    public void d9(){
        assertEq(25,StringDataUtil.getType((char)43));
        assertEq(4,StringDataUtil.getDirectionality((char)43));
        assertFalse(StringDataLetterUtil.isLetter((char)43));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43));
    }
    @Test
    public void d10(){
        assertEq(24,StringDataUtil.getType((char)44));
        assertEq(7,StringDataUtil.getDirectionality((char)44));
        assertFalse(StringDataLetterUtil.isLetter((char)44));
        assertFalse(StringDataUtil.isLetterOrDigit((char)44));
    }
    @Test
    public void d11(){
        assertEq(20,StringDataUtil.getType((char)45));
        assertEq(4,StringDataUtil.getDirectionality((char)45));
        assertFalse(StringDataLetterUtil.isLetter((char)45));
        assertFalse(StringDataUtil.isLetterOrDigit((char)45));
    }
    @Test
    public void d12(){
        assertEq(24,StringDataUtil.getType((char)46));
        assertEq(7,StringDataUtil.getDirectionality((char)46));
        assertFalse(StringDataLetterUtil.isLetter((char)46));
        assertFalse(StringDataUtil.isLetterOrDigit((char)46));
    }
    @Test
    public void d13(){
        assertEq(9,StringDataUtil.getType((char)48));
        assertEq(3,StringDataUtil.getDirectionality((char)48));
        assertFalse(StringDataLetterUtil.isLetter((char)48));
        assertTrue(StringDataUtil.isLetterOrDigit((char)48));
    }
    @Test
    public void d14(){
        assertEq(24,StringDataUtil.getType((char)58));
        assertEq(7,StringDataUtil.getDirectionality((char)58));
        assertFalse(StringDataLetterUtil.isLetter((char)58));
        assertFalse(StringDataUtil.isLetterOrDigit((char)58));
    }
    @Test
    public void d15(){
        assertEq(25,StringDataUtil.getType((char)60));
        assertEq(13,StringDataUtil.getDirectionality((char)60));
        assertFalse(StringDataLetterUtil.isLetter((char)60));
        assertFalse(StringDataUtil.isLetterOrDigit((char)60));
    }
    @Test
    public void d16(){
        assertEq(24,StringDataUtil.getType((char)63));
        assertEq(13,StringDataUtil.getDirectionality((char)63));
        assertFalse(StringDataLetterUtil.isLetter((char)63));
        assertFalse(StringDataUtil.isLetterOrDigit((char)63));
    }
    @Test
    public void d17(){
        assertEq(1,StringDataUtil.getType((char)65));
        assertEq(0,StringDataUtil.getDirectionality((char)65));
        assertTrue(StringDataLetterUtil.isLetter((char)65));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65));
    }
    @Test
    public void d18(){
        assertEq(21,StringDataUtil.getType((char)91));
        assertEq(13,StringDataUtil.getDirectionality((char)91));
        assertFalse(StringDataLetterUtil.isLetter((char)91));
        assertFalse(StringDataUtil.isLetterOrDigit((char)91));
    }
    @Test
    public void d19(){
        assertEq(24,StringDataUtil.getType((char)92));
        assertEq(13,StringDataUtil.getDirectionality((char)92));
        assertFalse(StringDataLetterUtil.isLetter((char)92));
        assertFalse(StringDataUtil.isLetterOrDigit((char)92));
    }
    @Test
    public void d20(){
        assertEq(22,StringDataUtil.getType((char)93));
        assertEq(13,StringDataUtil.getDirectionality((char)93));
        assertFalse(StringDataLetterUtil.isLetter((char)93));
        assertFalse(StringDataUtil.isLetterOrDigit((char)93));
    }
    @Test
    public void d21(){
        assertEq(27,StringDataUtil.getType((char)94));
        assertEq(13,StringDataUtil.getDirectionality((char)94));
        assertFalse(StringDataLetterUtil.isLetter((char)94));
        assertFalse(StringDataUtil.isLetterOrDigit((char)94));
    }
    @Test
    public void d22(){
        assertEq(23,StringDataUtil.getType((char)95));
        assertEq(13,StringDataUtil.getDirectionality((char)95));
        assertFalse(StringDataLetterUtil.isLetter((char)95));
        assertFalse(StringDataUtil.isLetterOrDigit((char)95));
    }
    @Test
    public void d23(){
        assertEq(27,StringDataUtil.getType((char)96));
        assertEq(13,StringDataUtil.getDirectionality((char)96));
        assertFalse(StringDataLetterUtil.isLetter((char)96));
        assertFalse(StringDataUtil.isLetterOrDigit((char)96));
    }
    @Test
    public void d24(){
        assertEq(2,StringDataUtil.getType((char)97));
        assertEq(0,StringDataUtil.getDirectionality((char)97));
        assertTrue(StringDataLetterUtil.isLetter((char)97));
        assertTrue(StringDataUtil.isLetterOrDigit((char)97));
    }
    @Test
    public void d25(){
        assertEq(21,StringDataUtil.getType((char)123));
        assertEq(13,StringDataUtil.getDirectionality((char)123));
        assertFalse(StringDataLetterUtil.isLetter((char)123));
        assertFalse(StringDataUtil.isLetterOrDigit((char)123));
    }
    @Test
    public void d26(){
        assertEq(25,StringDataUtil.getType((char)124));
        assertEq(13,StringDataUtil.getDirectionality((char)124));
        assertFalse(StringDataLetterUtil.isLetter((char)124));
        assertFalse(StringDataUtil.isLetterOrDigit((char)124));
    }
    @Test
    public void d27(){
        assertEq(22,StringDataUtil.getType((char)125));
        assertEq(13,StringDataUtil.getDirectionality((char)125));
        assertFalse(StringDataLetterUtil.isLetter((char)125));
        assertFalse(StringDataUtil.isLetterOrDigit((char)125));
    }
    @Test
    public void d28(){
        assertEq(25,StringDataUtil.getType((char)126));
        assertEq(13,StringDataUtil.getDirectionality((char)126));
        assertFalse(StringDataLetterUtil.isLetter((char)126));
        assertFalse(StringDataUtil.isLetterOrDigit((char)126));
    }
    @Test
    public void d29(){
        assertEq(15,StringDataUtil.getType((char)127));
        assertEq(9,StringDataUtil.getDirectionality((char)127));
        assertFalse(StringDataLetterUtil.isLetter((char)127));
        assertFalse(StringDataUtil.isLetterOrDigit((char)127));
    }
    @Test
    public void d30(){
        assertEq(12,StringDataUtil.getType((char)160));
        assertEq(7,StringDataUtil.getDirectionality((char)160));
        assertFalse(StringDataLetterUtil.isLetter((char)160));
        assertFalse(StringDataUtil.isLetterOrDigit((char)160));
    }
    @Test
    public void d31(){
        assertEq(24,StringDataUtil.getType((char)161));
        assertEq(13,StringDataUtil.getDirectionality((char)161));
        assertFalse(StringDataLetterUtil.isLetter((char)161));
        assertFalse(StringDataUtil.isLetterOrDigit((char)161));
    }
    @Test
    public void d32(){
        assertEq(26,StringDataUtil.getType((char)162));
        assertEq(5,StringDataUtil.getDirectionality((char)162));
        assertFalse(StringDataLetterUtil.isLetter((char)162));
        assertFalse(StringDataUtil.isLetterOrDigit((char)162));
    }
    @Test
    public void d33(){
        assertEq(28,StringDataUtil.getType((char)166));
        assertEq(13,StringDataUtil.getDirectionality((char)166));
        assertFalse(StringDataLetterUtil.isLetter((char)166));
        assertFalse(StringDataUtil.isLetterOrDigit((char)166));
    }
    @Test
    public void d34(){
        assertEq(24,StringDataUtil.getType((char)167));
        assertEq(13,StringDataUtil.getDirectionality((char)167));
        assertFalse(StringDataLetterUtil.isLetter((char)167));
        assertFalse(StringDataUtil.isLetterOrDigit((char)167));
    }
    @Test
    public void d35(){
        assertEq(27,StringDataUtil.getType((char)168));
        assertEq(13,StringDataUtil.getDirectionality((char)168));
        assertFalse(StringDataLetterUtil.isLetter((char)168));
        assertFalse(StringDataUtil.isLetterOrDigit((char)168));
    }
    @Test
    public void d36(){
        assertEq(28,StringDataUtil.getType((char)169));
        assertEq(13,StringDataUtil.getDirectionality((char)169));
        assertFalse(StringDataLetterUtil.isLetter((char)169));
        assertFalse(StringDataUtil.isLetterOrDigit((char)169));
    }
    @Test
    public void d37(){
        assertEq(5,StringDataUtil.getType((char)170));
        assertEq(0,StringDataUtil.getDirectionality((char)170));
        assertTrue(StringDataLetterUtil.isLetter((char)170));
        assertTrue(StringDataUtil.isLetterOrDigit((char)170));
    }
    @Test
    public void d38(){
        assertEq(29,StringDataUtil.getType((char)171));
        assertEq(13,StringDataUtil.getDirectionality((char)171));
        assertFalse(StringDataLetterUtil.isLetter((char)171));
        assertFalse(StringDataUtil.isLetterOrDigit((char)171));
    }
    @Test
    public void d39(){
        assertEq(25,StringDataUtil.getType((char)172));
        assertEq(13,StringDataUtil.getDirectionality((char)172));
        assertFalse(StringDataLetterUtil.isLetter((char)172));
        assertFalse(StringDataUtil.isLetterOrDigit((char)172));
    }
    @Test
    public void d40(){
        assertEq(16,StringDataUtil.getType((char)173));
        assertEq(9,StringDataUtil.getDirectionality((char)173));
        assertFalse(StringDataLetterUtil.isLetter((char)173));
        assertFalse(StringDataUtil.isLetterOrDigit((char)173));
    }
    @Test
    public void d41(){
        assertEq(28,StringDataUtil.getType((char)174));
        assertEq(13,StringDataUtil.getDirectionality((char)174));
        assertFalse(StringDataLetterUtil.isLetter((char)174));
        assertFalse(StringDataUtil.isLetterOrDigit((char)174));
    }
    @Test
    public void d42(){
        assertEq(27,StringDataUtil.getType((char)175));
        assertEq(13,StringDataUtil.getDirectionality((char)175));
        assertFalse(StringDataLetterUtil.isLetter((char)175));
        assertFalse(StringDataUtil.isLetterOrDigit((char)175));
    }
    @Test
    public void d43(){
        assertEq(28,StringDataUtil.getType((char)176));
        assertEq(5,StringDataUtil.getDirectionality((char)176));
        assertFalse(StringDataLetterUtil.isLetter((char)176));
        assertFalse(StringDataUtil.isLetterOrDigit((char)176));
    }
    @Test
    public void d44(){
        assertEq(25,StringDataUtil.getType((char)177));
        assertEq(5,StringDataUtil.getDirectionality((char)177));
        assertFalse(StringDataLetterUtil.isLetter((char)177));
        assertFalse(StringDataUtil.isLetterOrDigit((char)177));
    }
    @Test
    public void d45(){
        assertEq(11,StringDataUtil.getType((char)178));
        assertEq(3,StringDataUtil.getDirectionality((char)178));
        assertFalse(StringDataLetterUtil.isLetter((char)178));
        assertFalse(StringDataUtil.isLetterOrDigit((char)178));
    }
    @Test
    public void d46(){
        assertEq(27,StringDataUtil.getType((char)180));
        assertEq(13,StringDataUtil.getDirectionality((char)180));
        assertFalse(StringDataLetterUtil.isLetter((char)180));
        assertFalse(StringDataUtil.isLetterOrDigit((char)180));
    }
    @Test
    public void d47(){
        assertEq(2,StringDataUtil.getType((char)181));
        assertEq(0,StringDataUtil.getDirectionality((char)181));
        assertTrue(StringDataLetterUtil.isLetter((char)181));
        assertTrue(StringDataUtil.isLetterOrDigit((char)181));
    }
    @Test
    public void d48(){
        assertEq(24,StringDataUtil.getType((char)182));
        assertEq(13,StringDataUtil.getDirectionality((char)182));
        assertFalse(StringDataLetterUtil.isLetter((char)182));
        assertFalse(StringDataUtil.isLetterOrDigit((char)182));
    }
    @Test
    public void d49(){
        assertEq(27,StringDataUtil.getType((char)184));
        assertEq(13,StringDataUtil.getDirectionality((char)184));
        assertFalse(StringDataLetterUtil.isLetter((char)184));
        assertFalse(StringDataUtil.isLetterOrDigit((char)184));
    }
    @Test
    public void d50(){
        assertEq(11,StringDataUtil.getType((char)185));
        assertEq(3,StringDataUtil.getDirectionality((char)185));
        assertFalse(StringDataLetterUtil.isLetter((char)185));
        assertFalse(StringDataUtil.isLetterOrDigit((char)185));
    }
    @Test
    public void d51(){
        assertEq(5,StringDataUtil.getType((char)186));
        assertEq(0,StringDataUtil.getDirectionality((char)186));
        assertTrue(StringDataLetterUtil.isLetter((char)186));
        assertTrue(StringDataUtil.isLetterOrDigit((char)186));
    }
    @Test
    public void d52(){
        assertEq(30,StringDataUtil.getType((char)187));
        assertEq(13,StringDataUtil.getDirectionality((char)187));
        assertFalse(StringDataLetterUtil.isLetter((char)187));
        assertFalse(StringDataUtil.isLetterOrDigit((char)187));
    }
    @Test
    public void d53(){
        assertEq(11,StringDataUtil.getType((char)188));
        assertEq(13,StringDataUtil.getDirectionality((char)188));
        assertFalse(StringDataLetterUtil.isLetter((char)188));
        assertFalse(StringDataUtil.isLetterOrDigit((char)188));
    }
    @Test
    public void d54(){
        assertEq(24,StringDataUtil.getType((char)191));
        assertEq(13,StringDataUtil.getDirectionality((char)191));
        assertFalse(StringDataLetterUtil.isLetter((char)191));
        assertFalse(StringDataUtil.isLetterOrDigit((char)191));
    }
    @Test
    public void d55(){
        assertEq(1,StringDataUtil.getType((char)192));
        assertEq(0,StringDataUtil.getDirectionality((char)192));
        assertTrue(StringDataLetterUtil.isLetter((char)192));
        assertTrue(StringDataUtil.isLetterOrDigit((char)192));
    }
    @Test
    public void d56(){
        assertEq(25,StringDataUtil.getType((char)215));
        assertEq(13,StringDataUtil.getDirectionality((char)215));
        assertFalse(StringDataLetterUtil.isLetter((char)215));
        assertFalse(StringDataUtil.isLetterOrDigit((char)215));
    }
    @Test
    public void d57(){
        assertEq(1,StringDataUtil.getType((char)216));
        assertEq(0,StringDataUtil.getDirectionality((char)216));
        assertTrue(StringDataLetterUtil.isLetter((char)216));
        assertTrue(StringDataUtil.isLetterOrDigit((char)216));
    }
    @Test
    public void d58(){
        assertEq(2,StringDataUtil.getType((char)223));
        assertEq(0,StringDataUtil.getDirectionality((char)223));
        assertTrue(StringDataLetterUtil.isLetter((char)223));
        assertTrue(StringDataUtil.isLetterOrDigit((char)223));
    }
    @Test
    public void d59(){
        assertEq(25,StringDataUtil.getType((char)247));
        assertEq(13,StringDataUtil.getDirectionality((char)247));
        assertFalse(StringDataLetterUtil.isLetter((char)247));
        assertFalse(StringDataUtil.isLetterOrDigit((char)247));
    }
    @Test
    public void d60(){
        assertEq(2,StringDataUtil.getType((char)248));
        assertEq(0,StringDataUtil.getDirectionality((char)248));
        assertTrue(StringDataLetterUtil.isLetter((char)248));
        assertTrue(StringDataUtil.isLetterOrDigit((char)248));
    }
    @Test
    public void d61(){
        assertEq(1,StringDataUtil.getType((char)256));
        assertEq(0,StringDataUtil.getDirectionality((char)256));
        assertTrue(StringDataLetterUtil.isLetter((char)256));
        assertTrue(StringDataUtil.isLetterOrDigit((char)256));
    }
    @Test
    public void d62(){
        assertEq(2,StringDataUtil.getType((char)257));
        assertEq(0,StringDataUtil.getDirectionality((char)257));
        assertTrue(StringDataLetterUtil.isLetter((char)257));
        assertTrue(StringDataUtil.isLetterOrDigit((char)257));
    }
    @Test
    public void d63(){
        assertEq(1,StringDataUtil.getType((char)258));
        assertEq(0,StringDataUtil.getDirectionality((char)258));
        assertTrue(StringDataLetterUtil.isLetter((char)258));
        assertTrue(StringDataUtil.isLetterOrDigit((char)258));
    }
    @Test
    public void d64(){
        assertEq(2,StringDataUtil.getType((char)259));
        assertEq(0,StringDataUtil.getDirectionality((char)259));
        assertTrue(StringDataLetterUtil.isLetter((char)259));
        assertTrue(StringDataUtil.isLetterOrDigit((char)259));
    }
    @Test
    public void d65(){
        assertEq(1,StringDataUtil.getType((char)260));
        assertEq(0,StringDataUtil.getDirectionality((char)260));
        assertTrue(StringDataLetterUtil.isLetter((char)260));
        assertTrue(StringDataUtil.isLetterOrDigit((char)260));
    }
    @Test
    public void d66(){
        assertEq(2,StringDataUtil.getType((char)261));
        assertEq(0,StringDataUtil.getDirectionality((char)261));
        assertTrue(StringDataLetterUtil.isLetter((char)261));
        assertTrue(StringDataUtil.isLetterOrDigit((char)261));
    }
    @Test
    public void d67(){
        assertEq(1,StringDataUtil.getType((char)262));
        assertEq(0,StringDataUtil.getDirectionality((char)262));
        assertTrue(StringDataLetterUtil.isLetter((char)262));
        assertTrue(StringDataUtil.isLetterOrDigit((char)262));
    }
    @Test
    public void d68(){
        assertEq(2,StringDataUtil.getType((char)263));
        assertEq(0,StringDataUtil.getDirectionality((char)263));
        assertTrue(StringDataLetterUtil.isLetter((char)263));
        assertTrue(StringDataUtil.isLetterOrDigit((char)263));
    }
    @Test
    public void d69(){
        assertEq(1,StringDataUtil.getType((char)264));
        assertEq(0,StringDataUtil.getDirectionality((char)264));
        assertTrue(StringDataLetterUtil.isLetter((char)264));
        assertTrue(StringDataUtil.isLetterOrDigit((char)264));
    }
    @Test
    public void d70(){
        assertEq(2,StringDataUtil.getType((char)265));
        assertEq(0,StringDataUtil.getDirectionality((char)265));
        assertTrue(StringDataLetterUtil.isLetter((char)265));
        assertTrue(StringDataUtil.isLetterOrDigit((char)265));
    }
    @Test
    public void d71(){
        assertEq(1,StringDataUtil.getType((char)266));
        assertEq(0,StringDataUtil.getDirectionality((char)266));
        assertTrue(StringDataLetterUtil.isLetter((char)266));
        assertTrue(StringDataUtil.isLetterOrDigit((char)266));
    }
    @Test
    public void d72(){
        assertEq(2,StringDataUtil.getType((char)267));
        assertEq(0,StringDataUtil.getDirectionality((char)267));
        assertTrue(StringDataLetterUtil.isLetter((char)267));
        assertTrue(StringDataUtil.isLetterOrDigit((char)267));
    }
    @Test
    public void d73(){
        assertEq(1,StringDataUtil.getType((char)268));
        assertEq(0,StringDataUtil.getDirectionality((char)268));
        assertTrue(StringDataLetterUtil.isLetter((char)268));
        assertTrue(StringDataUtil.isLetterOrDigit((char)268));
    }
    @Test
    public void d74(){
        assertEq(2,StringDataUtil.getType((char)269));
        assertEq(0,StringDataUtil.getDirectionality((char)269));
        assertTrue(StringDataLetterUtil.isLetter((char)269));
        assertTrue(StringDataUtil.isLetterOrDigit((char)269));
    }
    @Test
    public void d75(){
        assertEq(1,StringDataUtil.getType((char)270));
        assertEq(0,StringDataUtil.getDirectionality((char)270));
        assertTrue(StringDataLetterUtil.isLetter((char)270));
        assertTrue(StringDataUtil.isLetterOrDigit((char)270));
    }
    @Test
    public void d76(){
        assertEq(2,StringDataUtil.getType((char)271));
        assertEq(0,StringDataUtil.getDirectionality((char)271));
        assertTrue(StringDataLetterUtil.isLetter((char)271));
        assertTrue(StringDataUtil.isLetterOrDigit((char)271));
    }
    @Test
    public void d77(){
        assertEq(1,StringDataUtil.getType((char)272));
        assertEq(0,StringDataUtil.getDirectionality((char)272));
        assertTrue(StringDataLetterUtil.isLetter((char)272));
        assertTrue(StringDataUtil.isLetterOrDigit((char)272));
    }
    @Test
    public void d78(){
        assertEq(2,StringDataUtil.getType((char)273));
        assertEq(0,StringDataUtil.getDirectionality((char)273));
        assertTrue(StringDataLetterUtil.isLetter((char)273));
        assertTrue(StringDataUtil.isLetterOrDigit((char)273));
    }
    @Test
    public void d79(){
        assertEq(1,StringDataUtil.getType((char)274));
        assertEq(0,StringDataUtil.getDirectionality((char)274));
        assertTrue(StringDataLetterUtil.isLetter((char)274));
        assertTrue(StringDataUtil.isLetterOrDigit((char)274));
    }
    @Test
    public void d80(){
        assertEq(2,StringDataUtil.getType((char)275));
        assertEq(0,StringDataUtil.getDirectionality((char)275));
        assertTrue(StringDataLetterUtil.isLetter((char)275));
        assertTrue(StringDataUtil.isLetterOrDigit((char)275));
    }
    @Test
    public void d81(){
        assertEq(1,StringDataUtil.getType((char)276));
        assertEq(0,StringDataUtil.getDirectionality((char)276));
        assertTrue(StringDataLetterUtil.isLetter((char)276));
        assertTrue(StringDataUtil.isLetterOrDigit((char)276));
    }
    @Test
    public void d82(){
        assertEq(2,StringDataUtil.getType((char)277));
        assertEq(0,StringDataUtil.getDirectionality((char)277));
        assertTrue(StringDataLetterUtil.isLetter((char)277));
        assertTrue(StringDataUtil.isLetterOrDigit((char)277));
    }
    @Test
    public void d83(){
        assertEq(1,StringDataUtil.getType((char)278));
        assertEq(0,StringDataUtil.getDirectionality((char)278));
        assertTrue(StringDataLetterUtil.isLetter((char)278));
        assertTrue(StringDataUtil.isLetterOrDigit((char)278));
    }
    @Test
    public void d84(){
        assertEq(2,StringDataUtil.getType((char)279));
        assertEq(0,StringDataUtil.getDirectionality((char)279));
        assertTrue(StringDataLetterUtil.isLetter((char)279));
        assertTrue(StringDataUtil.isLetterOrDigit((char)279));
    }
    @Test
    public void d85(){
        assertEq(1,StringDataUtil.getType((char)280));
        assertEq(0,StringDataUtil.getDirectionality((char)280));
        assertTrue(StringDataLetterUtil.isLetter((char)280));
        assertTrue(StringDataUtil.isLetterOrDigit((char)280));
    }
    @Test
    public void d86(){
        assertEq(2,StringDataUtil.getType((char)281));
        assertEq(0,StringDataUtil.getDirectionality((char)281));
        assertTrue(StringDataLetterUtil.isLetter((char)281));
        assertTrue(StringDataUtil.isLetterOrDigit((char)281));
    }
    @Test
    public void d87(){
        assertEq(1,StringDataUtil.getType((char)282));
        assertEq(0,StringDataUtil.getDirectionality((char)282));
        assertTrue(StringDataLetterUtil.isLetter((char)282));
        assertTrue(StringDataUtil.isLetterOrDigit((char)282));
    }
    @Test
    public void d88(){
        assertEq(2,StringDataUtil.getType((char)283));
        assertEq(0,StringDataUtil.getDirectionality((char)283));
        assertTrue(StringDataLetterUtil.isLetter((char)283));
        assertTrue(StringDataUtil.isLetterOrDigit((char)283));
    }
    @Test
    public void d89(){
        assertEq(1,StringDataUtil.getType((char)284));
        assertEq(0,StringDataUtil.getDirectionality((char)284));
        assertTrue(StringDataLetterUtil.isLetter((char)284));
        assertTrue(StringDataUtil.isLetterOrDigit((char)284));
    }
    @Test
    public void d90(){
        assertEq(2,StringDataUtil.getType((char)285));
        assertEq(0,StringDataUtil.getDirectionality((char)285));
        assertTrue(StringDataLetterUtil.isLetter((char)285));
        assertTrue(StringDataUtil.isLetterOrDigit((char)285));
    }
    @Test
    public void d91(){
        assertEq(1,StringDataUtil.getType((char)286));
        assertEq(0,StringDataUtil.getDirectionality((char)286));
        assertTrue(StringDataLetterUtil.isLetter((char)286));
        assertTrue(StringDataUtil.isLetterOrDigit((char)286));
    }
    @Test
    public void d92(){
        assertEq(2,StringDataUtil.getType((char)287));
        assertEq(0,StringDataUtil.getDirectionality((char)287));
        assertTrue(StringDataLetterUtil.isLetter((char)287));
        assertTrue(StringDataUtil.isLetterOrDigit((char)287));
    }
    @Test
    public void d93(){
        assertEq(1,StringDataUtil.getType((char)288));
        assertEq(0,StringDataUtil.getDirectionality((char)288));
        assertTrue(StringDataLetterUtil.isLetter((char)288));
        assertTrue(StringDataUtil.isLetterOrDigit((char)288));
    }
    @Test
    public void d94(){
        assertEq(2,StringDataUtil.getType((char)289));
        assertEq(0,StringDataUtil.getDirectionality((char)289));
        assertTrue(StringDataLetterUtil.isLetter((char)289));
        assertTrue(StringDataUtil.isLetterOrDigit((char)289));
    }
    @Test
    public void d95(){
        assertEq(1,StringDataUtil.getType((char)290));
        assertEq(0,StringDataUtil.getDirectionality((char)290));
        assertTrue(StringDataLetterUtil.isLetter((char)290));
        assertTrue(StringDataUtil.isLetterOrDigit((char)290));
    }
    @Test
    public void d96(){
        assertEq(2,StringDataUtil.getType((char)291));
        assertEq(0,StringDataUtil.getDirectionality((char)291));
        assertTrue(StringDataLetterUtil.isLetter((char)291));
        assertTrue(StringDataUtil.isLetterOrDigit((char)291));
    }
    @Test
    public void d97(){
        assertEq(1,StringDataUtil.getType((char)292));
        assertEq(0,StringDataUtil.getDirectionality((char)292));
        assertTrue(StringDataLetterUtil.isLetter((char)292));
        assertTrue(StringDataUtil.isLetterOrDigit((char)292));
    }
    @Test
    public void d98(){
        assertEq(2,StringDataUtil.getType((char)293));
        assertEq(0,StringDataUtil.getDirectionality((char)293));
        assertTrue(StringDataLetterUtil.isLetter((char)293));
        assertTrue(StringDataUtil.isLetterOrDigit((char)293));
    }
    @Test
    public void d99(){
        assertEq(1,StringDataUtil.getType((char)294));
        assertEq(0,StringDataUtil.getDirectionality((char)294));
        assertTrue(StringDataLetterUtil.isLetter((char)294));
        assertTrue(StringDataUtil.isLetterOrDigit((char)294));
    }
    @Test
    public void d100(){
        assertEq(2,StringDataUtil.getType((char)295));
        assertEq(0,StringDataUtil.getDirectionality((char)295));
        assertTrue(StringDataLetterUtil.isLetter((char)295));
        assertTrue(StringDataUtil.isLetterOrDigit((char)295));
    }
    @Test
    public void d101(){
        assertEq(1,StringDataUtil.getType((char)296));
        assertEq(0,StringDataUtil.getDirectionality((char)296));
        assertTrue(StringDataLetterUtil.isLetter((char)296));
        assertTrue(StringDataUtil.isLetterOrDigit((char)296));
    }
    @Test
    public void d102(){
        assertEq(2,StringDataUtil.getType((char)297));
        assertEq(0,StringDataUtil.getDirectionality((char)297));
        assertTrue(StringDataLetterUtil.isLetter((char)297));
        assertTrue(StringDataUtil.isLetterOrDigit((char)297));
    }
    @Test
    public void d103(){
        assertEq(1,StringDataUtil.getType((char)298));
        assertEq(0,StringDataUtil.getDirectionality((char)298));
        assertTrue(StringDataLetterUtil.isLetter((char)298));
        assertTrue(StringDataUtil.isLetterOrDigit((char)298));
    }
    @Test
    public void d104(){
        assertEq(2,StringDataUtil.getType((char)299));
        assertEq(0,StringDataUtil.getDirectionality((char)299));
        assertTrue(StringDataLetterUtil.isLetter((char)299));
        assertTrue(StringDataUtil.isLetterOrDigit((char)299));
    }
    @Test
    public void d105(){
        assertEq(1,StringDataUtil.getType((char)300));
        assertEq(0,StringDataUtil.getDirectionality((char)300));
        assertTrue(StringDataLetterUtil.isLetter((char)300));
        assertTrue(StringDataUtil.isLetterOrDigit((char)300));
    }
    @Test
    public void d106(){
        assertEq(2,StringDataUtil.getType((char)301));
        assertEq(0,StringDataUtil.getDirectionality((char)301));
        assertTrue(StringDataLetterUtil.isLetter((char)301));
        assertTrue(StringDataUtil.isLetterOrDigit((char)301));
    }
    @Test
    public void d107(){
        assertEq(1,StringDataUtil.getType((char)302));
        assertEq(0,StringDataUtil.getDirectionality((char)302));
        assertTrue(StringDataLetterUtil.isLetter((char)302));
        assertTrue(StringDataUtil.isLetterOrDigit((char)302));
    }
    @Test
    public void d108(){
        assertEq(2,StringDataUtil.getType((char)303));
        assertEq(0,StringDataUtil.getDirectionality((char)303));
        assertTrue(StringDataLetterUtil.isLetter((char)303));
        assertTrue(StringDataUtil.isLetterOrDigit((char)303));
    }
    @Test
    public void d109(){
        assertEq(1,StringDataUtil.getType((char)304));
        assertEq(0,StringDataUtil.getDirectionality((char)304));
        assertTrue(StringDataLetterUtil.isLetter((char)304));
        assertTrue(StringDataUtil.isLetterOrDigit((char)304));
    }
    @Test
    public void d110(){
        assertEq(2,StringDataUtil.getType((char)305));
        assertEq(0,StringDataUtil.getDirectionality((char)305));
        assertTrue(StringDataLetterUtil.isLetter((char)305));
        assertTrue(StringDataUtil.isLetterOrDigit((char)305));
    }
    @Test
    public void d111(){
        assertEq(1,StringDataUtil.getType((char)306));
        assertEq(0,StringDataUtil.getDirectionality((char)306));
        assertTrue(StringDataLetterUtil.isLetter((char)306));
        assertTrue(StringDataUtil.isLetterOrDigit((char)306));
    }
    @Test
    public void d112(){
        assertEq(2,StringDataUtil.getType((char)307));
        assertEq(0,StringDataUtil.getDirectionality((char)307));
        assertTrue(StringDataLetterUtil.isLetter((char)307));
        assertTrue(StringDataUtil.isLetterOrDigit((char)307));
    }
    @Test
    public void d113(){
        assertEq(1,StringDataUtil.getType((char)308));
        assertEq(0,StringDataUtil.getDirectionality((char)308));
        assertTrue(StringDataLetterUtil.isLetter((char)308));
        assertTrue(StringDataUtil.isLetterOrDigit((char)308));
    }
    @Test
    public void d114(){
        assertEq(2,StringDataUtil.getType((char)309));
        assertEq(0,StringDataUtil.getDirectionality((char)309));
        assertTrue(StringDataLetterUtil.isLetter((char)309));
        assertTrue(StringDataUtil.isLetterOrDigit((char)309));
    }
    @Test
    public void d115(){
        assertEq(1,StringDataUtil.getType((char)310));
        assertEq(0,StringDataUtil.getDirectionality((char)310));
        assertTrue(StringDataLetterUtil.isLetter((char)310));
        assertTrue(StringDataUtil.isLetterOrDigit((char)310));
    }
    @Test
    public void d116(){
        assertEq(2,StringDataUtil.getType((char)311));
        assertEq(0,StringDataUtil.getDirectionality((char)311));
        assertTrue(StringDataLetterUtil.isLetter((char)311));
        assertTrue(StringDataUtil.isLetterOrDigit((char)311));
    }
    @Test
    public void d117(){
        assertEq(1,StringDataUtil.getType((char)313));
        assertEq(0,StringDataUtil.getDirectionality((char)313));
        assertTrue(StringDataLetterUtil.isLetter((char)313));
        assertTrue(StringDataUtil.isLetterOrDigit((char)313));
    }
    @Test
    public void d118(){
        assertEq(2,StringDataUtil.getType((char)314));
        assertEq(0,StringDataUtil.getDirectionality((char)314));
        assertTrue(StringDataLetterUtil.isLetter((char)314));
        assertTrue(StringDataUtil.isLetterOrDigit((char)314));
    }
    @Test
    public void d119(){
        assertEq(1,StringDataUtil.getType((char)315));
        assertEq(0,StringDataUtil.getDirectionality((char)315));
        assertTrue(StringDataLetterUtil.isLetter((char)315));
        assertTrue(StringDataUtil.isLetterOrDigit((char)315));
    }
    @Test
    public void d120(){
        assertEq(2,StringDataUtil.getType((char)316));
        assertEq(0,StringDataUtil.getDirectionality((char)316));
        assertTrue(StringDataLetterUtil.isLetter((char)316));
        assertTrue(StringDataUtil.isLetterOrDigit((char)316));
    }
    @Test
    public void d121(){
        assertEq(1,StringDataUtil.getType((char)317));
        assertEq(0,StringDataUtil.getDirectionality((char)317));
        assertTrue(StringDataLetterUtil.isLetter((char)317));
        assertTrue(StringDataUtil.isLetterOrDigit((char)317));
    }
    @Test
    public void d122(){
        assertEq(2,StringDataUtil.getType((char)318));
        assertEq(0,StringDataUtil.getDirectionality((char)318));
        assertTrue(StringDataLetterUtil.isLetter((char)318));
        assertTrue(StringDataUtil.isLetterOrDigit((char)318));
    }
    @Test
    public void d123(){
        assertEq(1,StringDataUtil.getType((char)319));
        assertEq(0,StringDataUtil.getDirectionality((char)319));
        assertTrue(StringDataLetterUtil.isLetter((char)319));
        assertTrue(StringDataUtil.isLetterOrDigit((char)319));
    }
    @Test
    public void d124(){
        assertEq(2,StringDataUtil.getType((char)320));
        assertEq(0,StringDataUtil.getDirectionality((char)320));
        assertTrue(StringDataLetterUtil.isLetter((char)320));
        assertTrue(StringDataUtil.isLetterOrDigit((char)320));
    }
    @Test
    public void d125(){
        assertEq(1,StringDataUtil.getType((char)321));
        assertEq(0,StringDataUtil.getDirectionality((char)321));
        assertTrue(StringDataLetterUtil.isLetter((char)321));
        assertTrue(StringDataUtil.isLetterOrDigit((char)321));
    }
    @Test
    public void d126(){
        assertEq(2,StringDataUtil.getType((char)322));
        assertEq(0,StringDataUtil.getDirectionality((char)322));
        assertTrue(StringDataLetterUtil.isLetter((char)322));
        assertTrue(StringDataUtil.isLetterOrDigit((char)322));
    }
    @Test
    public void d127(){
        assertEq(1,StringDataUtil.getType((char)323));
        assertEq(0,StringDataUtil.getDirectionality((char)323));
        assertTrue(StringDataLetterUtil.isLetter((char)323));
        assertTrue(StringDataUtil.isLetterOrDigit((char)323));
    }
    @Test
    public void d128(){
        assertEq(2,StringDataUtil.getType((char)324));
        assertEq(0,StringDataUtil.getDirectionality((char)324));
        assertTrue(StringDataLetterUtil.isLetter((char)324));
        assertTrue(StringDataUtil.isLetterOrDigit((char)324));
    }
    @Test
    public void d129(){
        assertEq(1,StringDataUtil.getType((char)325));
        assertEq(0,StringDataUtil.getDirectionality((char)325));
        assertTrue(StringDataLetterUtil.isLetter((char)325));
        assertTrue(StringDataUtil.isLetterOrDigit((char)325));
    }
    @Test
    public void d130(){
        assertEq(2,StringDataUtil.getType((char)326));
        assertEq(0,StringDataUtil.getDirectionality((char)326));
        assertTrue(StringDataLetterUtil.isLetter((char)326));
        assertTrue(StringDataUtil.isLetterOrDigit((char)326));
    }
    @Test
    public void d131(){
        assertEq(1,StringDataUtil.getType((char)327));
        assertEq(0,StringDataUtil.getDirectionality((char)327));
        assertTrue(StringDataLetterUtil.isLetter((char)327));
        assertTrue(StringDataUtil.isLetterOrDigit((char)327));
    }
    @Test
    public void d132(){
        assertEq(2,StringDataUtil.getType((char)328));
        assertEq(0,StringDataUtil.getDirectionality((char)328));
        assertTrue(StringDataLetterUtil.isLetter((char)328));
        assertTrue(StringDataUtil.isLetterOrDigit((char)328));
    }
    @Test
    public void d133(){
        assertEq(1,StringDataUtil.getType((char)330));
        assertEq(0,StringDataUtil.getDirectionality((char)330));
        assertTrue(StringDataLetterUtil.isLetter((char)330));
        assertTrue(StringDataUtil.isLetterOrDigit((char)330));
    }
    @Test
    public void d134(){
        assertEq(2,StringDataUtil.getType((char)331));
        assertEq(0,StringDataUtil.getDirectionality((char)331));
        assertTrue(StringDataLetterUtil.isLetter((char)331));
        assertTrue(StringDataUtil.isLetterOrDigit((char)331));
    }
    @Test
    public void d135(){
        assertEq(1,StringDataUtil.getType((char)332));
        assertEq(0,StringDataUtil.getDirectionality((char)332));
        assertTrue(StringDataLetterUtil.isLetter((char)332));
        assertTrue(StringDataUtil.isLetterOrDigit((char)332));
    }
    @Test
    public void d136(){
        assertEq(2,StringDataUtil.getType((char)333));
        assertEq(0,StringDataUtil.getDirectionality((char)333));
        assertTrue(StringDataLetterUtil.isLetter((char)333));
        assertTrue(StringDataUtil.isLetterOrDigit((char)333));
    }
    @Test
    public void d137(){
        assertEq(1,StringDataUtil.getType((char)334));
        assertEq(0,StringDataUtil.getDirectionality((char)334));
        assertTrue(StringDataLetterUtil.isLetter((char)334));
        assertTrue(StringDataUtil.isLetterOrDigit((char)334));
    }
    @Test
    public void d138(){
        assertEq(2,StringDataUtil.getType((char)335));
        assertEq(0,StringDataUtil.getDirectionality((char)335));
        assertTrue(StringDataLetterUtil.isLetter((char)335));
        assertTrue(StringDataUtil.isLetterOrDigit((char)335));
    }
    @Test
    public void d139(){
        assertEq(1,StringDataUtil.getType((char)336));
        assertEq(0,StringDataUtil.getDirectionality((char)336));
        assertTrue(StringDataLetterUtil.isLetter((char)336));
        assertTrue(StringDataUtil.isLetterOrDigit((char)336));
    }
    @Test
    public void d140(){
        assertEq(2,StringDataUtil.getType((char)337));
        assertEq(0,StringDataUtil.getDirectionality((char)337));
        assertTrue(StringDataLetterUtil.isLetter((char)337));
        assertTrue(StringDataUtil.isLetterOrDigit((char)337));
    }
    @Test
    public void d141(){
        assertEq(1,StringDataUtil.getType((char)338));
        assertEq(0,StringDataUtil.getDirectionality((char)338));
        assertTrue(StringDataLetterUtil.isLetter((char)338));
        assertTrue(StringDataUtil.isLetterOrDigit((char)338));
    }
    @Test
    public void d142(){
        assertEq(2,StringDataUtil.getType((char)339));
        assertEq(0,StringDataUtil.getDirectionality((char)339));
        assertTrue(StringDataLetterUtil.isLetter((char)339));
        assertTrue(StringDataUtil.isLetterOrDigit((char)339));
    }
    @Test
    public void d143(){
        assertEq(1,StringDataUtil.getType((char)340));
        assertEq(0,StringDataUtil.getDirectionality((char)340));
        assertTrue(StringDataLetterUtil.isLetter((char)340));
        assertTrue(StringDataUtil.isLetterOrDigit((char)340));
    }
    @Test
    public void d144(){
        assertEq(2,StringDataUtil.getType((char)341));
        assertEq(0,StringDataUtil.getDirectionality((char)341));
        assertTrue(StringDataLetterUtil.isLetter((char)341));
        assertTrue(StringDataUtil.isLetterOrDigit((char)341));
    }
    @Test
    public void d145(){
        assertEq(1,StringDataUtil.getType((char)342));
        assertEq(0,StringDataUtil.getDirectionality((char)342));
        assertTrue(StringDataLetterUtil.isLetter((char)342));
        assertTrue(StringDataUtil.isLetterOrDigit((char)342));
    }
    @Test
    public void d146(){
        assertEq(2,StringDataUtil.getType((char)343));
        assertEq(0,StringDataUtil.getDirectionality((char)343));
        assertTrue(StringDataLetterUtil.isLetter((char)343));
        assertTrue(StringDataUtil.isLetterOrDigit((char)343));
    }
    @Test
    public void d147(){
        assertEq(1,StringDataUtil.getType((char)344));
        assertEq(0,StringDataUtil.getDirectionality((char)344));
        assertTrue(StringDataLetterUtil.isLetter((char)344));
        assertTrue(StringDataUtil.isLetterOrDigit((char)344));
    }
    @Test
    public void d148(){
        assertEq(2,StringDataUtil.getType((char)345));
        assertEq(0,StringDataUtil.getDirectionality((char)345));
        assertTrue(StringDataLetterUtil.isLetter((char)345));
        assertTrue(StringDataUtil.isLetterOrDigit((char)345));
    }
    @Test
    public void d149(){
        assertEq(1,StringDataUtil.getType((char)346));
        assertEq(0,StringDataUtil.getDirectionality((char)346));
        assertTrue(StringDataLetterUtil.isLetter((char)346));
        assertTrue(StringDataUtil.isLetterOrDigit((char)346));
    }
    @Test
    public void d150(){
        assertEq(2,StringDataUtil.getType((char)347));
        assertEq(0,StringDataUtil.getDirectionality((char)347));
        assertTrue(StringDataLetterUtil.isLetter((char)347));
        assertTrue(StringDataUtil.isLetterOrDigit((char)347));
    }
    @Test
    public void d151(){
        assertEq(1,StringDataUtil.getType((char)348));
        assertEq(0,StringDataUtil.getDirectionality((char)348));
        assertTrue(StringDataLetterUtil.isLetter((char)348));
        assertTrue(StringDataUtil.isLetterOrDigit((char)348));
    }
    @Test
    public void d152(){
        assertEq(2,StringDataUtil.getType((char)349));
        assertEq(0,StringDataUtil.getDirectionality((char)349));
        assertTrue(StringDataLetterUtil.isLetter((char)349));
        assertTrue(StringDataUtil.isLetterOrDigit((char)349));
    }
    @Test
    public void d153(){
        assertEq(1,StringDataUtil.getType((char)350));
        assertEq(0,StringDataUtil.getDirectionality((char)350));
        assertTrue(StringDataLetterUtil.isLetter((char)350));
        assertTrue(StringDataUtil.isLetterOrDigit((char)350));
    }
    @Test
    public void d154(){
        assertEq(2,StringDataUtil.getType((char)351));
        assertEq(0,StringDataUtil.getDirectionality((char)351));
        assertTrue(StringDataLetterUtil.isLetter((char)351));
        assertTrue(StringDataUtil.isLetterOrDigit((char)351));
    }
    @Test
    public void d155(){
        assertEq(1,StringDataUtil.getType((char)352));
        assertEq(0,StringDataUtil.getDirectionality((char)352));
        assertTrue(StringDataLetterUtil.isLetter((char)352));
        assertTrue(StringDataUtil.isLetterOrDigit((char)352));
    }
    @Test
    public void d156(){
        assertEq(2,StringDataUtil.getType((char)353));
        assertEq(0,StringDataUtil.getDirectionality((char)353));
        assertTrue(StringDataLetterUtil.isLetter((char)353));
        assertTrue(StringDataUtil.isLetterOrDigit((char)353));
    }
    @Test
    public void d157(){
        assertEq(1,StringDataUtil.getType((char)354));
        assertEq(0,StringDataUtil.getDirectionality((char)354));
        assertTrue(StringDataLetterUtil.isLetter((char)354));
        assertTrue(StringDataUtil.isLetterOrDigit((char)354));
    }
    @Test
    public void d158(){
        assertEq(2,StringDataUtil.getType((char)355));
        assertEq(0,StringDataUtil.getDirectionality((char)355));
        assertTrue(StringDataLetterUtil.isLetter((char)355));
        assertTrue(StringDataUtil.isLetterOrDigit((char)355));
    }
    @Test
    public void d159(){
        assertEq(1,StringDataUtil.getType((char)356));
        assertEq(0,StringDataUtil.getDirectionality((char)356));
        assertTrue(StringDataLetterUtil.isLetter((char)356));
        assertTrue(StringDataUtil.isLetterOrDigit((char)356));
    }
    @Test
    public void d160(){
        assertEq(2,StringDataUtil.getType((char)357));
        assertEq(0,StringDataUtil.getDirectionality((char)357));
        assertTrue(StringDataLetterUtil.isLetter((char)357));
        assertTrue(StringDataUtil.isLetterOrDigit((char)357));
    }
    @Test
    public void d161(){
        assertEq(1,StringDataUtil.getType((char)358));
        assertEq(0,StringDataUtil.getDirectionality((char)358));
        assertTrue(StringDataLetterUtil.isLetter((char)358));
        assertTrue(StringDataUtil.isLetterOrDigit((char)358));
    }
    @Test
    public void d162(){
        assertEq(2,StringDataUtil.getType((char)359));
        assertEq(0,StringDataUtil.getDirectionality((char)359));
        assertTrue(StringDataLetterUtil.isLetter((char)359));
        assertTrue(StringDataUtil.isLetterOrDigit((char)359));
    }
    @Test
    public void d163(){
        assertEq(1,StringDataUtil.getType((char)360));
        assertEq(0,StringDataUtil.getDirectionality((char)360));
        assertTrue(StringDataLetterUtil.isLetter((char)360));
        assertTrue(StringDataUtil.isLetterOrDigit((char)360));
    }
    @Test
    public void d164(){
        assertEq(2,StringDataUtil.getType((char)361));
        assertEq(0,StringDataUtil.getDirectionality((char)361));
        assertTrue(StringDataLetterUtil.isLetter((char)361));
        assertTrue(StringDataUtil.isLetterOrDigit((char)361));
    }
    @Test
    public void d165(){
        assertEq(1,StringDataUtil.getType((char)362));
        assertEq(0,StringDataUtil.getDirectionality((char)362));
        assertTrue(StringDataLetterUtil.isLetter((char)362));
        assertTrue(StringDataUtil.isLetterOrDigit((char)362));
    }
    @Test
    public void d166(){
        assertEq(2,StringDataUtil.getType((char)363));
        assertEq(0,StringDataUtil.getDirectionality((char)363));
        assertTrue(StringDataLetterUtil.isLetter((char)363));
        assertTrue(StringDataUtil.isLetterOrDigit((char)363));
    }
    @Test
    public void d167(){
        assertEq(1,StringDataUtil.getType((char)364));
        assertEq(0,StringDataUtil.getDirectionality((char)364));
        assertTrue(StringDataLetterUtil.isLetter((char)364));
        assertTrue(StringDataUtil.isLetterOrDigit((char)364));
    }
    @Test
    public void d168(){
        assertEq(2,StringDataUtil.getType((char)365));
        assertEq(0,StringDataUtil.getDirectionality((char)365));
        assertTrue(StringDataLetterUtil.isLetter((char)365));
        assertTrue(StringDataUtil.isLetterOrDigit((char)365));
    }
    @Test
    public void d169(){
        assertEq(1,StringDataUtil.getType((char)366));
        assertEq(0,StringDataUtil.getDirectionality((char)366));
        assertTrue(StringDataLetterUtil.isLetter((char)366));
        assertTrue(StringDataUtil.isLetterOrDigit((char)366));
    }
    @Test
    public void d170(){
        assertEq(2,StringDataUtil.getType((char)367));
        assertEq(0,StringDataUtil.getDirectionality((char)367));
        assertTrue(StringDataLetterUtil.isLetter((char)367));
        assertTrue(StringDataUtil.isLetterOrDigit((char)367));
    }
    @Test
    public void d171(){
        assertEq(1,StringDataUtil.getType((char)368));
        assertEq(0,StringDataUtil.getDirectionality((char)368));
        assertTrue(StringDataLetterUtil.isLetter((char)368));
        assertTrue(StringDataUtil.isLetterOrDigit((char)368));
    }
    @Test
    public void d172(){
        assertEq(2,StringDataUtil.getType((char)369));
        assertEq(0,StringDataUtil.getDirectionality((char)369));
        assertTrue(StringDataLetterUtil.isLetter((char)369));
        assertTrue(StringDataUtil.isLetterOrDigit((char)369));
    }
    @Test
    public void d173(){
        assertEq(1,StringDataUtil.getType((char)370));
        assertEq(0,StringDataUtil.getDirectionality((char)370));
        assertTrue(StringDataLetterUtil.isLetter((char)370));
        assertTrue(StringDataUtil.isLetterOrDigit((char)370));
    }
    @Test
    public void d174(){
        assertEq(2,StringDataUtil.getType((char)371));
        assertEq(0,StringDataUtil.getDirectionality((char)371));
        assertTrue(StringDataLetterUtil.isLetter((char)371));
        assertTrue(StringDataUtil.isLetterOrDigit((char)371));
    }
    @Test
    public void d175(){
        assertEq(1,StringDataUtil.getType((char)372));
        assertEq(0,StringDataUtil.getDirectionality((char)372));
        assertTrue(StringDataLetterUtil.isLetter((char)372));
        assertTrue(StringDataUtil.isLetterOrDigit((char)372));
    }
    @Test
    public void d176(){
        assertEq(2,StringDataUtil.getType((char)373));
        assertEq(0,StringDataUtil.getDirectionality((char)373));
        assertTrue(StringDataLetterUtil.isLetter((char)373));
        assertTrue(StringDataUtil.isLetterOrDigit((char)373));
    }
    @Test
    public void d177(){
        assertEq(1,StringDataUtil.getType((char)374));
        assertEq(0,StringDataUtil.getDirectionality((char)374));
        assertTrue(StringDataLetterUtil.isLetter((char)374));
        assertTrue(StringDataUtil.isLetterOrDigit((char)374));
    }
    @Test
    public void d178(){
        assertEq(2,StringDataUtil.getType((char)375));
        assertEq(0,StringDataUtil.getDirectionality((char)375));
        assertTrue(StringDataLetterUtil.isLetter((char)375));
        assertTrue(StringDataUtil.isLetterOrDigit((char)375));
    }
    @Test
    public void d179(){
        assertEq(1,StringDataUtil.getType((char)376));
        assertEq(0,StringDataUtil.getDirectionality((char)376));
        assertTrue(StringDataLetterUtil.isLetter((char)376));
        assertTrue(StringDataUtil.isLetterOrDigit((char)376));
    }
    @Test
    public void d180(){
        assertEq(2,StringDataUtil.getType((char)378));
        assertEq(0,StringDataUtil.getDirectionality((char)378));
        assertTrue(StringDataLetterUtil.isLetter((char)378));
        assertTrue(StringDataUtil.isLetterOrDigit((char)378));
    }
    @Test
    public void d181(){
        assertEq(1,StringDataUtil.getType((char)379));
        assertEq(0,StringDataUtil.getDirectionality((char)379));
        assertTrue(StringDataLetterUtil.isLetter((char)379));
        assertTrue(StringDataUtil.isLetterOrDigit((char)379));
    }
    @Test
    public void d182(){
        assertEq(2,StringDataUtil.getType((char)380));
        assertEq(0,StringDataUtil.getDirectionality((char)380));
        assertTrue(StringDataLetterUtil.isLetter((char)380));
        assertTrue(StringDataUtil.isLetterOrDigit((char)380));
    }
    @Test
    public void d183(){
        assertEq(1,StringDataUtil.getType((char)381));
        assertEq(0,StringDataUtil.getDirectionality((char)381));
        assertTrue(StringDataLetterUtil.isLetter((char)381));
        assertTrue(StringDataUtil.isLetterOrDigit((char)381));
    }
    @Test
    public void d184(){
        assertEq(2,StringDataUtil.getType((char)382));
        assertEq(0,StringDataUtil.getDirectionality((char)382));
        assertTrue(StringDataLetterUtil.isLetter((char)382));
        assertTrue(StringDataUtil.isLetterOrDigit((char)382));
    }
    @Test
    public void d185(){
        assertEq(1,StringDataUtil.getType((char)385));
        assertEq(0,StringDataUtil.getDirectionality((char)385));
        assertTrue(StringDataLetterUtil.isLetter((char)385));
        assertTrue(StringDataUtil.isLetterOrDigit((char)385));
    }
    @Test
    public void d186(){
        assertEq(2,StringDataUtil.getType((char)387));
        assertEq(0,StringDataUtil.getDirectionality((char)387));
        assertTrue(StringDataLetterUtil.isLetter((char)387));
        assertTrue(StringDataUtil.isLetterOrDigit((char)387));
    }
    @Test
    public void d187(){
        assertEq(1,StringDataUtil.getType((char)388));
        assertEq(0,StringDataUtil.getDirectionality((char)388));
        assertTrue(StringDataLetterUtil.isLetter((char)388));
        assertTrue(StringDataUtil.isLetterOrDigit((char)388));
    }
    @Test
    public void d188(){
        assertEq(2,StringDataUtil.getType((char)389));
        assertEq(0,StringDataUtil.getDirectionality((char)389));
        assertTrue(StringDataLetterUtil.isLetter((char)389));
        assertTrue(StringDataUtil.isLetterOrDigit((char)389));
    }
    @Test
    public void d189(){
        assertEq(1,StringDataUtil.getType((char)390));
        assertEq(0,StringDataUtil.getDirectionality((char)390));
        assertTrue(StringDataLetterUtil.isLetter((char)390));
        assertTrue(StringDataUtil.isLetterOrDigit((char)390));
    }
    @Test
    public void d190(){
        assertEq(2,StringDataUtil.getType((char)392));
        assertEq(0,StringDataUtil.getDirectionality((char)392));
        assertTrue(StringDataLetterUtil.isLetter((char)392));
        assertTrue(StringDataUtil.isLetterOrDigit((char)392));
    }
    @Test
    public void d191(){
        assertEq(1,StringDataUtil.getType((char)393));
        assertEq(0,StringDataUtil.getDirectionality((char)393));
        assertTrue(StringDataLetterUtil.isLetter((char)393));
        assertTrue(StringDataUtil.isLetterOrDigit((char)393));
    }
    @Test
    public void d192(){
        assertEq(2,StringDataUtil.getType((char)396));
        assertEq(0,StringDataUtil.getDirectionality((char)396));
        assertTrue(StringDataLetterUtil.isLetter((char)396));
        assertTrue(StringDataUtil.isLetterOrDigit((char)396));
    }
    @Test
    public void d193(){
        assertEq(1,StringDataUtil.getType((char)398));
        assertEq(0,StringDataUtil.getDirectionality((char)398));
        assertTrue(StringDataLetterUtil.isLetter((char)398));
        assertTrue(StringDataUtil.isLetterOrDigit((char)398));
    }
    @Test
    public void d194(){
        assertEq(2,StringDataUtil.getType((char)402));
        assertEq(0,StringDataUtil.getDirectionality((char)402));
        assertTrue(StringDataLetterUtil.isLetter((char)402));
        assertTrue(StringDataUtil.isLetterOrDigit((char)402));
    }
    @Test
    public void d195(){
        assertEq(1,StringDataUtil.getType((char)403));
        assertEq(0,StringDataUtil.getDirectionality((char)403));
        assertTrue(StringDataLetterUtil.isLetter((char)403));
        assertTrue(StringDataUtil.isLetterOrDigit((char)403));
    }
    @Test
    public void d196(){
        assertEq(2,StringDataUtil.getType((char)405));
        assertEq(0,StringDataUtil.getDirectionality((char)405));
        assertTrue(StringDataLetterUtil.isLetter((char)405));
        assertTrue(StringDataUtil.isLetterOrDigit((char)405));
    }
    @Test
    public void d197(){
        assertEq(1,StringDataUtil.getType((char)406));
        assertEq(0,StringDataUtil.getDirectionality((char)406));
        assertTrue(StringDataLetterUtil.isLetter((char)406));
        assertTrue(StringDataUtil.isLetterOrDigit((char)406));
    }
    @Test
    public void d198(){
        assertEq(2,StringDataUtil.getType((char)409));
        assertEq(0,StringDataUtil.getDirectionality((char)409));
        assertTrue(StringDataLetterUtil.isLetter((char)409));
        assertTrue(StringDataUtil.isLetterOrDigit((char)409));
    }
    @Test
    public void d199(){
        assertEq(1,StringDataUtil.getType((char)412));
        assertEq(0,StringDataUtil.getDirectionality((char)412));
        assertTrue(StringDataLetterUtil.isLetter((char)412));
        assertTrue(StringDataUtil.isLetterOrDigit((char)412));
    }
    @Test
    public void d200(){
        assertEq(2,StringDataUtil.getType((char)414));
        assertEq(0,StringDataUtil.getDirectionality((char)414));
        assertTrue(StringDataLetterUtil.isLetter((char)414));
        assertTrue(StringDataUtil.isLetterOrDigit((char)414));
    }
    @Test
    public void d201(){
        assertEq(1,StringDataUtil.getType((char)415));
        assertEq(0,StringDataUtil.getDirectionality((char)415));
        assertTrue(StringDataLetterUtil.isLetter((char)415));
        assertTrue(StringDataUtil.isLetterOrDigit((char)415));
    }
    @Test
    public void d202(){
        assertEq(2,StringDataUtil.getType((char)417));
        assertEq(0,StringDataUtil.getDirectionality((char)417));
        assertTrue(StringDataLetterUtil.isLetter((char)417));
        assertTrue(StringDataUtil.isLetterOrDigit((char)417));
    }
    @Test
    public void d203(){
        assertEq(1,StringDataUtil.getType((char)418));
        assertEq(0,StringDataUtil.getDirectionality((char)418));
        assertTrue(StringDataLetterUtil.isLetter((char)418));
        assertTrue(StringDataUtil.isLetterOrDigit((char)418));
    }
    @Test
    public void d204(){
        assertEq(2,StringDataUtil.getType((char)419));
        assertEq(0,StringDataUtil.getDirectionality((char)419));
        assertTrue(StringDataLetterUtil.isLetter((char)419));
        assertTrue(StringDataUtil.isLetterOrDigit((char)419));
    }
    @Test
    public void d205(){
        assertEq(1,StringDataUtil.getType((char)420));
        assertEq(0,StringDataUtil.getDirectionality((char)420));
        assertTrue(StringDataLetterUtil.isLetter((char)420));
        assertTrue(StringDataUtil.isLetterOrDigit((char)420));
    }
    @Test
    public void d206(){
        assertEq(2,StringDataUtil.getType((char)421));
        assertEq(0,StringDataUtil.getDirectionality((char)421));
        assertTrue(StringDataLetterUtil.isLetter((char)421));
        assertTrue(StringDataUtil.isLetterOrDigit((char)421));
    }
    @Test
    public void d207(){
        assertEq(1,StringDataUtil.getType((char)422));
        assertEq(0,StringDataUtil.getDirectionality((char)422));
        assertTrue(StringDataLetterUtil.isLetter((char)422));
        assertTrue(StringDataUtil.isLetterOrDigit((char)422));
    }
    @Test
    public void d208(){
        assertEq(2,StringDataUtil.getType((char)424));
        assertEq(0,StringDataUtil.getDirectionality((char)424));
        assertTrue(StringDataLetterUtil.isLetter((char)424));
        assertTrue(StringDataUtil.isLetterOrDigit((char)424));
    }
    @Test
    public void d209(){
        assertEq(1,StringDataUtil.getType((char)425));
        assertEq(0,StringDataUtil.getDirectionality((char)425));
        assertTrue(StringDataLetterUtil.isLetter((char)425));
        assertTrue(StringDataUtil.isLetterOrDigit((char)425));
    }
    @Test
    public void d210(){
        assertEq(2,StringDataUtil.getType((char)426));
        assertEq(0,StringDataUtil.getDirectionality((char)426));
        assertTrue(StringDataLetterUtil.isLetter((char)426));
        assertTrue(StringDataUtil.isLetterOrDigit((char)426));
    }
    @Test
    public void d211(){
        assertEq(1,StringDataUtil.getType((char)428));
        assertEq(0,StringDataUtil.getDirectionality((char)428));
        assertTrue(StringDataLetterUtil.isLetter((char)428));
        assertTrue(StringDataUtil.isLetterOrDigit((char)428));
    }
    @Test
    public void d212(){
        assertEq(2,StringDataUtil.getType((char)429));
        assertEq(0,StringDataUtil.getDirectionality((char)429));
        assertTrue(StringDataLetterUtil.isLetter((char)429));
        assertTrue(StringDataUtil.isLetterOrDigit((char)429));
    }
    @Test
    public void d213(){
        assertEq(1,StringDataUtil.getType((char)430));
        assertEq(0,StringDataUtil.getDirectionality((char)430));
        assertTrue(StringDataLetterUtil.isLetter((char)430));
        assertTrue(StringDataUtil.isLetterOrDigit((char)430));
    }
    @Test
    public void d214(){
        assertEq(2,StringDataUtil.getType((char)432));
        assertEq(0,StringDataUtil.getDirectionality((char)432));
        assertTrue(StringDataLetterUtil.isLetter((char)432));
        assertTrue(StringDataUtil.isLetterOrDigit((char)432));
    }
    @Test
    public void d215(){
        assertEq(1,StringDataUtil.getType((char)433));
        assertEq(0,StringDataUtil.getDirectionality((char)433));
        assertTrue(StringDataLetterUtil.isLetter((char)433));
        assertTrue(StringDataUtil.isLetterOrDigit((char)433));
    }
    @Test
    public void d216(){
        assertEq(2,StringDataUtil.getType((char)436));
        assertEq(0,StringDataUtil.getDirectionality((char)436));
        assertTrue(StringDataLetterUtil.isLetter((char)436));
        assertTrue(StringDataUtil.isLetterOrDigit((char)436));
    }
    @Test
    public void d217(){
        assertEq(1,StringDataUtil.getType((char)437));
        assertEq(0,StringDataUtil.getDirectionality((char)437));
        assertTrue(StringDataLetterUtil.isLetter((char)437));
        assertTrue(StringDataUtil.isLetterOrDigit((char)437));
    }
    @Test
    public void d218(){
        assertEq(2,StringDataUtil.getType((char)438));
        assertEq(0,StringDataUtil.getDirectionality((char)438));
        assertTrue(StringDataLetterUtil.isLetter((char)438));
        assertTrue(StringDataUtil.isLetterOrDigit((char)438));
    }
    @Test
    public void d219(){
        assertEq(1,StringDataUtil.getType((char)439));
        assertEq(0,StringDataUtil.getDirectionality((char)439));
        assertTrue(StringDataLetterUtil.isLetter((char)439));
        assertTrue(StringDataUtil.isLetterOrDigit((char)439));
    }
    @Test
    public void d220(){
        assertEq(2,StringDataUtil.getType((char)441));
        assertEq(0,StringDataUtil.getDirectionality((char)441));
        assertTrue(StringDataLetterUtil.isLetter((char)441));
        assertTrue(StringDataUtil.isLetterOrDigit((char)441));
    }
    @Test
    public void d221(){
        assertEq(5,StringDataUtil.getType((char)443));
        assertEq(0,StringDataUtil.getDirectionality((char)443));
        assertTrue(StringDataLetterUtil.isLetter((char)443));
        assertTrue(StringDataUtil.isLetterOrDigit((char)443));
    }
    @Test
    public void d222(){
        assertEq(1,StringDataUtil.getType((char)444));
        assertEq(0,StringDataUtil.getDirectionality((char)444));
        assertTrue(StringDataLetterUtil.isLetter((char)444));
        assertTrue(StringDataUtil.isLetterOrDigit((char)444));
    }
    @Test
    public void d223(){
        assertEq(2,StringDataUtil.getType((char)445));
        assertEq(0,StringDataUtil.getDirectionality((char)445));
        assertTrue(StringDataLetterUtil.isLetter((char)445));
        assertTrue(StringDataUtil.isLetterOrDigit((char)445));
    }
    @Test
    public void d224(){
        assertEq(5,StringDataUtil.getType((char)448));
        assertEq(0,StringDataUtil.getDirectionality((char)448));
        assertTrue(StringDataLetterUtil.isLetter((char)448));
        assertTrue(StringDataUtil.isLetterOrDigit((char)448));
    }
    @Test
    public void d225(){
        assertEq(1,StringDataUtil.getType((char)452));
        assertEq(0,StringDataUtil.getDirectionality((char)452));
        assertTrue(StringDataLetterUtil.isLetter((char)452));
        assertTrue(StringDataUtil.isLetterOrDigit((char)452));
    }
    @Test
    public void d226(){
        assertEq(3,StringDataUtil.getType((char)453));
        assertEq(0,StringDataUtil.getDirectionality((char)453));
        assertTrue(StringDataLetterUtil.isLetter((char)453));
        assertTrue(StringDataUtil.isLetterOrDigit((char)453));
    }
    @Test
    public void d227(){
        assertEq(2,StringDataUtil.getType((char)454));
        assertEq(0,StringDataUtil.getDirectionality((char)454));
        assertTrue(StringDataLetterUtil.isLetter((char)454));
        assertTrue(StringDataUtil.isLetterOrDigit((char)454));
    }
    @Test
    public void d228(){
        assertEq(1,StringDataUtil.getType((char)455));
        assertEq(0,StringDataUtil.getDirectionality((char)455));
        assertTrue(StringDataLetterUtil.isLetter((char)455));
        assertTrue(StringDataUtil.isLetterOrDigit((char)455));
    }
    @Test
    public void d229(){
        assertEq(3,StringDataUtil.getType((char)456));
        assertEq(0,StringDataUtil.getDirectionality((char)456));
        assertTrue(StringDataLetterUtil.isLetter((char)456));
        assertTrue(StringDataUtil.isLetterOrDigit((char)456));
    }
    @Test
    public void d230(){
        assertEq(2,StringDataUtil.getType((char)457));
        assertEq(0,StringDataUtil.getDirectionality((char)457));
        assertTrue(StringDataLetterUtil.isLetter((char)457));
        assertTrue(StringDataUtil.isLetterOrDigit((char)457));
    }
    @Test
    public void d231(){
        assertEq(1,StringDataUtil.getType((char)458));
        assertEq(0,StringDataUtil.getDirectionality((char)458));
        assertTrue(StringDataLetterUtil.isLetter((char)458));
        assertTrue(StringDataUtil.isLetterOrDigit((char)458));
    }
    @Test
    public void d232(){
        assertEq(3,StringDataUtil.getType((char)459));
        assertEq(0,StringDataUtil.getDirectionality((char)459));
        assertTrue(StringDataLetterUtil.isLetter((char)459));
        assertTrue(StringDataUtil.isLetterOrDigit((char)459));
    }
    @Test
    public void d233(){
        assertEq(2,StringDataUtil.getType((char)460));
        assertEq(0,StringDataUtil.getDirectionality((char)460));
        assertTrue(StringDataLetterUtil.isLetter((char)460));
        assertTrue(StringDataUtil.isLetterOrDigit((char)460));
    }
    @Test
    public void d234(){
        assertEq(1,StringDataUtil.getType((char)461));
        assertEq(0,StringDataUtil.getDirectionality((char)461));
        assertTrue(StringDataLetterUtil.isLetter((char)461));
        assertTrue(StringDataUtil.isLetterOrDigit((char)461));
    }
    @Test
    public void d235(){
        assertEq(2,StringDataUtil.getType((char)462));
        assertEq(0,StringDataUtil.getDirectionality((char)462));
        assertTrue(StringDataLetterUtil.isLetter((char)462));
        assertTrue(StringDataUtil.isLetterOrDigit((char)462));
    }
    @Test
    public void d236(){
        assertEq(1,StringDataUtil.getType((char)463));
        assertEq(0,StringDataUtil.getDirectionality((char)463));
        assertTrue(StringDataLetterUtil.isLetter((char)463));
        assertTrue(StringDataUtil.isLetterOrDigit((char)463));
    }
    @Test
    public void d237(){
        assertEq(2,StringDataUtil.getType((char)464));
        assertEq(0,StringDataUtil.getDirectionality((char)464));
        assertTrue(StringDataLetterUtil.isLetter((char)464));
        assertTrue(StringDataUtil.isLetterOrDigit((char)464));
    }
    @Test
    public void d238(){
        assertEq(1,StringDataUtil.getType((char)465));
        assertEq(0,StringDataUtil.getDirectionality((char)465));
        assertTrue(StringDataLetterUtil.isLetter((char)465));
        assertTrue(StringDataUtil.isLetterOrDigit((char)465));
    }
    @Test
    public void d239(){
        assertEq(2,StringDataUtil.getType((char)466));
        assertEq(0,StringDataUtil.getDirectionality((char)466));
        assertTrue(StringDataLetterUtil.isLetter((char)466));
        assertTrue(StringDataUtil.isLetterOrDigit((char)466));
    }
    @Test
    public void d240(){
        assertEq(1,StringDataUtil.getType((char)467));
        assertEq(0,StringDataUtil.getDirectionality((char)467));
        assertTrue(StringDataLetterUtil.isLetter((char)467));
        assertTrue(StringDataUtil.isLetterOrDigit((char)467));
    }
    @Test
    public void d241(){
        assertEq(2,StringDataUtil.getType((char)468));
        assertEq(0,StringDataUtil.getDirectionality((char)468));
        assertTrue(StringDataLetterUtil.isLetter((char)468));
        assertTrue(StringDataUtil.isLetterOrDigit((char)468));
    }
    @Test
    public void d242(){
        assertEq(1,StringDataUtil.getType((char)469));
        assertEq(0,StringDataUtil.getDirectionality((char)469));
        assertTrue(StringDataLetterUtil.isLetter((char)469));
        assertTrue(StringDataUtil.isLetterOrDigit((char)469));
    }
    @Test
    public void d243(){
        assertEq(2,StringDataUtil.getType((char)470));
        assertEq(0,StringDataUtil.getDirectionality((char)470));
        assertTrue(StringDataLetterUtil.isLetter((char)470));
        assertTrue(StringDataUtil.isLetterOrDigit((char)470));
    }
    @Test
    public void d244(){
        assertEq(1,StringDataUtil.getType((char)471));
        assertEq(0,StringDataUtil.getDirectionality((char)471));
        assertTrue(StringDataLetterUtil.isLetter((char)471));
        assertTrue(StringDataUtil.isLetterOrDigit((char)471));
    }
    @Test
    public void d245(){
        assertEq(2,StringDataUtil.getType((char)472));
        assertEq(0,StringDataUtil.getDirectionality((char)472));
        assertTrue(StringDataLetterUtil.isLetter((char)472));
        assertTrue(StringDataUtil.isLetterOrDigit((char)472));
    }
    @Test
    public void d246(){
        assertEq(1,StringDataUtil.getType((char)473));
        assertEq(0,StringDataUtil.getDirectionality((char)473));
        assertTrue(StringDataLetterUtil.isLetter((char)473));
        assertTrue(StringDataUtil.isLetterOrDigit((char)473));
    }
    @Test
    public void d247(){
        assertEq(2,StringDataUtil.getType((char)474));
        assertEq(0,StringDataUtil.getDirectionality((char)474));
        assertTrue(StringDataLetterUtil.isLetter((char)474));
        assertTrue(StringDataUtil.isLetterOrDigit((char)474));
    }
    @Test
    public void d248(){
        assertEq(1,StringDataUtil.getType((char)475));
        assertEq(0,StringDataUtil.getDirectionality((char)475));
        assertTrue(StringDataLetterUtil.isLetter((char)475));
        assertTrue(StringDataUtil.isLetterOrDigit((char)475));
    }
    @Test
    public void d249(){
        assertEq(2,StringDataUtil.getType((char)476));
        assertEq(0,StringDataUtil.getDirectionality((char)476));
        assertTrue(StringDataLetterUtil.isLetter((char)476));
        assertTrue(StringDataUtil.isLetterOrDigit((char)476));
    }
    @Test
    public void d250(){
        assertEq(1,StringDataUtil.getType((char)478));
        assertEq(0,StringDataUtil.getDirectionality((char)478));
        assertTrue(StringDataLetterUtil.isLetter((char)478));
        assertTrue(StringDataUtil.isLetterOrDigit((char)478));
    }
    @Test
    public void d251(){
        assertEq(2,StringDataUtil.getType((char)479));
        assertEq(0,StringDataUtil.getDirectionality((char)479));
        assertTrue(StringDataLetterUtil.isLetter((char)479));
        assertTrue(StringDataUtil.isLetterOrDigit((char)479));
    }
    @Test
    public void d252(){
        assertEq(1,StringDataUtil.getType((char)480));
        assertEq(0,StringDataUtil.getDirectionality((char)480));
        assertTrue(StringDataLetterUtil.isLetter((char)480));
        assertTrue(StringDataUtil.isLetterOrDigit((char)480));
    }
    @Test
    public void d253(){
        assertEq(2,StringDataUtil.getType((char)481));
        assertEq(0,StringDataUtil.getDirectionality((char)481));
        assertTrue(StringDataLetterUtil.isLetter((char)481));
        assertTrue(StringDataUtil.isLetterOrDigit((char)481));
    }
    @Test
    public void d254(){
        assertEq(1,StringDataUtil.getType((char)482));
        assertEq(0,StringDataUtil.getDirectionality((char)482));
        assertTrue(StringDataLetterUtil.isLetter((char)482));
        assertTrue(StringDataUtil.isLetterOrDigit((char)482));
    }
    @Test
    public void d255(){
        assertEq(2,StringDataUtil.getType((char)483));
        assertEq(0,StringDataUtil.getDirectionality((char)483));
        assertTrue(StringDataLetterUtil.isLetter((char)483));
        assertTrue(StringDataUtil.isLetterOrDigit((char)483));
    }
    @Test
    public void d256(){
        assertEq(1,StringDataUtil.getType((char)484));
        assertEq(0,StringDataUtil.getDirectionality((char)484));
        assertTrue(StringDataLetterUtil.isLetter((char)484));
        assertTrue(StringDataUtil.isLetterOrDigit((char)484));
    }
    @Test
    public void d257(){
        assertEq(2,StringDataUtil.getType((char)485));
        assertEq(0,StringDataUtil.getDirectionality((char)485));
        assertTrue(StringDataLetterUtil.isLetter((char)485));
        assertTrue(StringDataUtil.isLetterOrDigit((char)485));
    }
    @Test
    public void d258(){
        assertEq(1,StringDataUtil.getType((char)486));
        assertEq(0,StringDataUtil.getDirectionality((char)486));
        assertTrue(StringDataLetterUtil.isLetter((char)486));
        assertTrue(StringDataUtil.isLetterOrDigit((char)486));
    }
    @Test
    public void d259(){
        assertEq(2,StringDataUtil.getType((char)487));
        assertEq(0,StringDataUtil.getDirectionality((char)487));
        assertTrue(StringDataLetterUtil.isLetter((char)487));
        assertTrue(StringDataUtil.isLetterOrDigit((char)487));
    }
    @Test
    public void d260(){
        assertEq(1,StringDataUtil.getType((char)488));
        assertEq(0,StringDataUtil.getDirectionality((char)488));
        assertTrue(StringDataLetterUtil.isLetter((char)488));
        assertTrue(StringDataUtil.isLetterOrDigit((char)488));
    }
    @Test
    public void d261(){
        assertEq(2,StringDataUtil.getType((char)489));
        assertEq(0,StringDataUtil.getDirectionality((char)489));
        assertTrue(StringDataLetterUtil.isLetter((char)489));
        assertTrue(StringDataUtil.isLetterOrDigit((char)489));
    }
    @Test
    public void d262(){
        assertEq(1,StringDataUtil.getType((char)490));
        assertEq(0,StringDataUtil.getDirectionality((char)490));
        assertTrue(StringDataLetterUtil.isLetter((char)490));
        assertTrue(StringDataUtil.isLetterOrDigit((char)490));
    }
    @Test
    public void d263(){
        assertEq(2,StringDataUtil.getType((char)491));
        assertEq(0,StringDataUtil.getDirectionality((char)491));
        assertTrue(StringDataLetterUtil.isLetter((char)491));
        assertTrue(StringDataUtil.isLetterOrDigit((char)491));
    }
    @Test
    public void d264(){
        assertEq(1,StringDataUtil.getType((char)492));
        assertEq(0,StringDataUtil.getDirectionality((char)492));
        assertTrue(StringDataLetterUtil.isLetter((char)492));
        assertTrue(StringDataUtil.isLetterOrDigit((char)492));
    }
    @Test
    public void d265(){
        assertEq(2,StringDataUtil.getType((char)493));
        assertEq(0,StringDataUtil.getDirectionality((char)493));
        assertTrue(StringDataLetterUtil.isLetter((char)493));
        assertTrue(StringDataUtil.isLetterOrDigit((char)493));
    }
    @Test
    public void d266(){
        assertEq(1,StringDataUtil.getType((char)494));
        assertEq(0,StringDataUtil.getDirectionality((char)494));
        assertTrue(StringDataLetterUtil.isLetter((char)494));
        assertTrue(StringDataUtil.isLetterOrDigit((char)494));
    }
    @Test
    public void d267(){
        assertEq(2,StringDataUtil.getType((char)495));
        assertEq(0,StringDataUtil.getDirectionality((char)495));
        assertTrue(StringDataLetterUtil.isLetter((char)495));
        assertTrue(StringDataUtil.isLetterOrDigit((char)495));
    }
    @Test
    public void d268(){
        assertEq(1,StringDataUtil.getType((char)497));
        assertEq(0,StringDataUtil.getDirectionality((char)497));
        assertTrue(StringDataLetterUtil.isLetter((char)497));
        assertTrue(StringDataUtil.isLetterOrDigit((char)497));
    }
    @Test
    public void d269(){
        assertEq(3,StringDataUtil.getType((char)498));
        assertEq(0,StringDataUtil.getDirectionality((char)498));
        assertTrue(StringDataLetterUtil.isLetter((char)498));
        assertTrue(StringDataUtil.isLetterOrDigit((char)498));
    }
    @Test
    public void d270(){
        assertEq(2,StringDataUtil.getType((char)499));
        assertEq(0,StringDataUtil.getDirectionality((char)499));
        assertTrue(StringDataLetterUtil.isLetter((char)499));
        assertTrue(StringDataUtil.isLetterOrDigit((char)499));
    }
    @Test
    public void d271(){
        assertEq(1,StringDataUtil.getType((char)500));
        assertEq(0,StringDataUtil.getDirectionality((char)500));
        assertTrue(StringDataLetterUtil.isLetter((char)500));
        assertTrue(StringDataUtil.isLetterOrDigit((char)500));
    }
    @Test
    public void d272(){
        assertEq(2,StringDataUtil.getType((char)501));
        assertEq(0,StringDataUtil.getDirectionality((char)501));
        assertTrue(StringDataLetterUtil.isLetter((char)501));
        assertTrue(StringDataUtil.isLetterOrDigit((char)501));
    }
    @Test
    public void d273(){
        assertEq(1,StringDataUtil.getType((char)502));
        assertEq(0,StringDataUtil.getDirectionality((char)502));
        assertTrue(StringDataLetterUtil.isLetter((char)502));
        assertTrue(StringDataUtil.isLetterOrDigit((char)502));
    }
    @Test
    public void d274(){
        assertEq(2,StringDataUtil.getType((char)505));
        assertEq(0,StringDataUtil.getDirectionality((char)505));
        assertTrue(StringDataLetterUtil.isLetter((char)505));
        assertTrue(StringDataUtil.isLetterOrDigit((char)505));
    }
    @Test
    public void d275(){
        assertEq(1,StringDataUtil.getType((char)506));
        assertEq(0,StringDataUtil.getDirectionality((char)506));
        assertTrue(StringDataLetterUtil.isLetter((char)506));
        assertTrue(StringDataUtil.isLetterOrDigit((char)506));
    }
    @Test
    public void d276(){
        assertEq(2,StringDataUtil.getType((char)507));
        assertEq(0,StringDataUtil.getDirectionality((char)507));
        assertTrue(StringDataLetterUtil.isLetter((char)507));
        assertTrue(StringDataUtil.isLetterOrDigit((char)507));
    }
    @Test
    public void d277(){
        assertEq(1,StringDataUtil.getType((char)508));
        assertEq(0,StringDataUtil.getDirectionality((char)508));
        assertTrue(StringDataLetterUtil.isLetter((char)508));
        assertTrue(StringDataUtil.isLetterOrDigit((char)508));
    }
    @Test
    public void d278(){
        assertEq(2,StringDataUtil.getType((char)509));
        assertEq(0,StringDataUtil.getDirectionality((char)509));
        assertTrue(StringDataLetterUtil.isLetter((char)509));
        assertTrue(StringDataUtil.isLetterOrDigit((char)509));
    }
    @Test
    public void d279(){
        assertEq(1,StringDataUtil.getType((char)510));
        assertEq(0,StringDataUtil.getDirectionality((char)510));
        assertTrue(StringDataLetterUtil.isLetter((char)510));
        assertTrue(StringDataUtil.isLetterOrDigit((char)510));
    }
    @Test
    public void d280(){
        assertEq(2,StringDataUtil.getType((char)511));
        assertEq(0,StringDataUtil.getDirectionality((char)511));
        assertTrue(StringDataLetterUtil.isLetter((char)511));
        assertTrue(StringDataUtil.isLetterOrDigit((char)511));
    }
    @Test
    public void d281(){
        assertEq(1,StringDataUtil.getType((char)512));
        assertEq(0,StringDataUtil.getDirectionality((char)512));
        assertTrue(StringDataLetterUtil.isLetter((char)512));
        assertTrue(StringDataUtil.isLetterOrDigit((char)512));
    }
    @Test
    public void d282(){
        assertEq(2,StringDataUtil.getType((char)513));
        assertEq(0,StringDataUtil.getDirectionality((char)513));
        assertTrue(StringDataLetterUtil.isLetter((char)513));
        assertTrue(StringDataUtil.isLetterOrDigit((char)513));
    }
    @Test
    public void d283(){
        assertEq(1,StringDataUtil.getType((char)514));
        assertEq(0,StringDataUtil.getDirectionality((char)514));
        assertTrue(StringDataLetterUtil.isLetter((char)514));
        assertTrue(StringDataUtil.isLetterOrDigit((char)514));
    }
    @Test
    public void d284(){
        assertEq(2,StringDataUtil.getType((char)515));
        assertEq(0,StringDataUtil.getDirectionality((char)515));
        assertTrue(StringDataLetterUtil.isLetter((char)515));
        assertTrue(StringDataUtil.isLetterOrDigit((char)515));
    }
    @Test
    public void d285(){
        assertEq(1,StringDataUtil.getType((char)516));
        assertEq(0,StringDataUtil.getDirectionality((char)516));
        assertTrue(StringDataLetterUtil.isLetter((char)516));
        assertTrue(StringDataUtil.isLetterOrDigit((char)516));
    }
    @Test
    public void d286(){
        assertEq(2,StringDataUtil.getType((char)517));
        assertEq(0,StringDataUtil.getDirectionality((char)517));
        assertTrue(StringDataLetterUtil.isLetter((char)517));
        assertTrue(StringDataUtil.isLetterOrDigit((char)517));
    }
    @Test
    public void d287(){
        assertEq(1,StringDataUtil.getType((char)518));
        assertEq(0,StringDataUtil.getDirectionality((char)518));
        assertTrue(StringDataLetterUtil.isLetter((char)518));
        assertTrue(StringDataUtil.isLetterOrDigit((char)518));
    }
    @Test
    public void d288(){
        assertEq(2,StringDataUtil.getType((char)519));
        assertEq(0,StringDataUtil.getDirectionality((char)519));
        assertTrue(StringDataLetterUtil.isLetter((char)519));
        assertTrue(StringDataUtil.isLetterOrDigit((char)519));
    }
    @Test
    public void d289(){
        assertEq(1,StringDataUtil.getType((char)520));
        assertEq(0,StringDataUtil.getDirectionality((char)520));
        assertTrue(StringDataLetterUtil.isLetter((char)520));
        assertTrue(StringDataUtil.isLetterOrDigit((char)520));
    }
    @Test
    public void d290(){
        assertEq(2,StringDataUtil.getType((char)521));
        assertEq(0,StringDataUtil.getDirectionality((char)521));
        assertTrue(StringDataLetterUtil.isLetter((char)521));
        assertTrue(StringDataUtil.isLetterOrDigit((char)521));
    }
    @Test
    public void d291(){
        assertEq(1,StringDataUtil.getType((char)522));
        assertEq(0,StringDataUtil.getDirectionality((char)522));
        assertTrue(StringDataLetterUtil.isLetter((char)522));
        assertTrue(StringDataUtil.isLetterOrDigit((char)522));
    }
    @Test
    public void d292(){
        assertEq(2,StringDataUtil.getType((char)523));
        assertEq(0,StringDataUtil.getDirectionality((char)523));
        assertTrue(StringDataLetterUtil.isLetter((char)523));
        assertTrue(StringDataUtil.isLetterOrDigit((char)523));
    }
    @Test
    public void d293(){
        assertEq(1,StringDataUtil.getType((char)524));
        assertEq(0,StringDataUtil.getDirectionality((char)524));
        assertTrue(StringDataLetterUtil.isLetter((char)524));
        assertTrue(StringDataUtil.isLetterOrDigit((char)524));
    }
    @Test
    public void d294(){
        assertEq(2,StringDataUtil.getType((char)525));
        assertEq(0,StringDataUtil.getDirectionality((char)525));
        assertTrue(StringDataLetterUtil.isLetter((char)525));
        assertTrue(StringDataUtil.isLetterOrDigit((char)525));
    }
    @Test
    public void d295(){
        assertEq(1,StringDataUtil.getType((char)526));
        assertEq(0,StringDataUtil.getDirectionality((char)526));
        assertTrue(StringDataLetterUtil.isLetter((char)526));
        assertTrue(StringDataUtil.isLetterOrDigit((char)526));
    }
    @Test
    public void d296(){
        assertEq(2,StringDataUtil.getType((char)527));
        assertEq(0,StringDataUtil.getDirectionality((char)527));
        assertTrue(StringDataLetterUtil.isLetter((char)527));
        assertTrue(StringDataUtil.isLetterOrDigit((char)527));
    }
    @Test
    public void d297(){
        assertEq(1,StringDataUtil.getType((char)528));
        assertEq(0,StringDataUtil.getDirectionality((char)528));
        assertTrue(StringDataLetterUtil.isLetter((char)528));
        assertTrue(StringDataUtil.isLetterOrDigit((char)528));
    }
    @Test
    public void d298(){
        assertEq(2,StringDataUtil.getType((char)529));
        assertEq(0,StringDataUtil.getDirectionality((char)529));
        assertTrue(StringDataLetterUtil.isLetter((char)529));
        assertTrue(StringDataUtil.isLetterOrDigit((char)529));
    }
    @Test
    public void d299(){
        assertEq(1,StringDataUtil.getType((char)530));
        assertEq(0,StringDataUtil.getDirectionality((char)530));
        assertTrue(StringDataLetterUtil.isLetter((char)530));
        assertTrue(StringDataUtil.isLetterOrDigit((char)530));
    }
    @Test
    public void d300(){
        assertEq(2,StringDataUtil.getType((char)531));
        assertEq(0,StringDataUtil.getDirectionality((char)531));
        assertTrue(StringDataLetterUtil.isLetter((char)531));
        assertTrue(StringDataUtil.isLetterOrDigit((char)531));
    }
    @Test
    public void d301(){
        assertEq(1,StringDataUtil.getType((char)532));
        assertEq(0,StringDataUtil.getDirectionality((char)532));
        assertTrue(StringDataLetterUtil.isLetter((char)532));
        assertTrue(StringDataUtil.isLetterOrDigit((char)532));
    }
    @Test
    public void d302(){
        assertEq(2,StringDataUtil.getType((char)533));
        assertEq(0,StringDataUtil.getDirectionality((char)533));
        assertTrue(StringDataLetterUtil.isLetter((char)533));
        assertTrue(StringDataUtil.isLetterOrDigit((char)533));
    }
    @Test
    public void d303(){
        assertEq(1,StringDataUtil.getType((char)534));
        assertEq(0,StringDataUtil.getDirectionality((char)534));
        assertTrue(StringDataLetterUtil.isLetter((char)534));
        assertTrue(StringDataUtil.isLetterOrDigit((char)534));
    }
    @Test
    public void d304(){
        assertEq(2,StringDataUtil.getType((char)535));
        assertEq(0,StringDataUtil.getDirectionality((char)535));
        assertTrue(StringDataLetterUtil.isLetter((char)535));
        assertTrue(StringDataUtil.isLetterOrDigit((char)535));
    }
    @Test
    public void d305(){
        assertEq(1,StringDataUtil.getType((char)536));
        assertEq(0,StringDataUtil.getDirectionality((char)536));
        assertTrue(StringDataLetterUtil.isLetter((char)536));
        assertTrue(StringDataUtil.isLetterOrDigit((char)536));
    }
    @Test
    public void d306(){
        assertEq(2,StringDataUtil.getType((char)537));
        assertEq(0,StringDataUtil.getDirectionality((char)537));
        assertTrue(StringDataLetterUtil.isLetter((char)537));
        assertTrue(StringDataUtil.isLetterOrDigit((char)537));
    }
    @Test
    public void d307(){
        assertEq(1,StringDataUtil.getType((char)538));
        assertEq(0,StringDataUtil.getDirectionality((char)538));
        assertTrue(StringDataLetterUtil.isLetter((char)538));
        assertTrue(StringDataUtil.isLetterOrDigit((char)538));
    }
    @Test
    public void d308(){
        assertEq(2,StringDataUtil.getType((char)539));
        assertEq(0,StringDataUtil.getDirectionality((char)539));
        assertTrue(StringDataLetterUtil.isLetter((char)539));
        assertTrue(StringDataUtil.isLetterOrDigit((char)539));
    }
    @Test
    public void d309(){
        assertEq(1,StringDataUtil.getType((char)540));
        assertEq(0,StringDataUtil.getDirectionality((char)540));
        assertTrue(StringDataLetterUtil.isLetter((char)540));
        assertTrue(StringDataUtil.isLetterOrDigit((char)540));
    }
    @Test
    public void d310(){
        assertEq(2,StringDataUtil.getType((char)541));
        assertEq(0,StringDataUtil.getDirectionality((char)541));
        assertTrue(StringDataLetterUtil.isLetter((char)541));
        assertTrue(StringDataUtil.isLetterOrDigit((char)541));
    }
    @Test
    public void d311(){
        assertEq(1,StringDataUtil.getType((char)542));
        assertEq(0,StringDataUtil.getDirectionality((char)542));
        assertTrue(StringDataLetterUtil.isLetter((char)542));
        assertTrue(StringDataUtil.isLetterOrDigit((char)542));
    }
    @Test
    public void d312(){
        assertEq(2,StringDataUtil.getType((char)543));
        assertEq(0,StringDataUtil.getDirectionality((char)543));
        assertTrue(StringDataLetterUtil.isLetter((char)543));
        assertTrue(StringDataUtil.isLetterOrDigit((char)543));
    }
    @Test
    public void d313(){
        assertEq(1,StringDataUtil.getType((char)544));
        assertEq(0,StringDataUtil.getDirectionality((char)544));
        assertTrue(StringDataLetterUtil.isLetter((char)544));
        assertTrue(StringDataUtil.isLetterOrDigit((char)544));
    }
    @Test
    public void d314(){
        assertEq(2,StringDataUtil.getType((char)545));
        assertEq(0,StringDataUtil.getDirectionality((char)545));
        assertTrue(StringDataLetterUtil.isLetter((char)545));
        assertTrue(StringDataUtil.isLetterOrDigit((char)545));
    }
    @Test
    public void d315(){
        assertEq(1,StringDataUtil.getType((char)546));
        assertEq(0,StringDataUtil.getDirectionality((char)546));
        assertTrue(StringDataLetterUtil.isLetter((char)546));
        assertTrue(StringDataUtil.isLetterOrDigit((char)546));
    }
    @Test
    public void d316(){
        assertEq(2,StringDataUtil.getType((char)547));
        assertEq(0,StringDataUtil.getDirectionality((char)547));
        assertTrue(StringDataLetterUtil.isLetter((char)547));
        assertTrue(StringDataUtil.isLetterOrDigit((char)547));
    }
    @Test
    public void d317(){
        assertEq(1,StringDataUtil.getType((char)548));
        assertEq(0,StringDataUtil.getDirectionality((char)548));
        assertTrue(StringDataLetterUtil.isLetter((char)548));
        assertTrue(StringDataUtil.isLetterOrDigit((char)548));
    }
    @Test
    public void d318(){
        assertEq(2,StringDataUtil.getType((char)549));
        assertEq(0,StringDataUtil.getDirectionality((char)549));
        assertTrue(StringDataLetterUtil.isLetter((char)549));
        assertTrue(StringDataUtil.isLetterOrDigit((char)549));
    }
    @Test
    public void d319(){
        assertEq(1,StringDataUtil.getType((char)550));
        assertEq(0,StringDataUtil.getDirectionality((char)550));
        assertTrue(StringDataLetterUtil.isLetter((char)550));
        assertTrue(StringDataUtil.isLetterOrDigit((char)550));
    }
    @Test
    public void d320(){
        assertEq(2,StringDataUtil.getType((char)551));
        assertEq(0,StringDataUtil.getDirectionality((char)551));
        assertTrue(StringDataLetterUtil.isLetter((char)551));
        assertTrue(StringDataUtil.isLetterOrDigit((char)551));
    }
    @Test
    public void d321(){
        assertEq(1,StringDataUtil.getType((char)552));
        assertEq(0,StringDataUtil.getDirectionality((char)552));
        assertTrue(StringDataLetterUtil.isLetter((char)552));
        assertTrue(StringDataUtil.isLetterOrDigit((char)552));
    }
    @Test
    public void d322(){
        assertEq(2,StringDataUtil.getType((char)553));
        assertEq(0,StringDataUtil.getDirectionality((char)553));
        assertTrue(StringDataLetterUtil.isLetter((char)553));
        assertTrue(StringDataUtil.isLetterOrDigit((char)553));
    }
    @Test
    public void d323(){
        assertEq(1,StringDataUtil.getType((char)554));
        assertEq(0,StringDataUtil.getDirectionality((char)554));
        assertTrue(StringDataLetterUtil.isLetter((char)554));
        assertTrue(StringDataUtil.isLetterOrDigit((char)554));
    }
    @Test
    public void d324(){
        assertEq(2,StringDataUtil.getType((char)555));
        assertEq(0,StringDataUtil.getDirectionality((char)555));
        assertTrue(StringDataLetterUtil.isLetter((char)555));
        assertTrue(StringDataUtil.isLetterOrDigit((char)555));
    }
    @Test
    public void d325(){
        assertEq(1,StringDataUtil.getType((char)556));
        assertEq(0,StringDataUtil.getDirectionality((char)556));
        assertTrue(StringDataLetterUtil.isLetter((char)556));
        assertTrue(StringDataUtil.isLetterOrDigit((char)556));
    }
    @Test
    public void d326(){
        assertEq(2,StringDataUtil.getType((char)557));
        assertEq(0,StringDataUtil.getDirectionality((char)557));
        assertTrue(StringDataLetterUtil.isLetter((char)557));
        assertTrue(StringDataUtil.isLetterOrDigit((char)557));
    }
    @Test
    public void d327(){
        assertEq(1,StringDataUtil.getType((char)558));
        assertEq(0,StringDataUtil.getDirectionality((char)558));
        assertTrue(StringDataLetterUtil.isLetter((char)558));
        assertTrue(StringDataUtil.isLetterOrDigit((char)558));
    }
    @Test
    public void d328(){
        assertEq(2,StringDataUtil.getType((char)559));
        assertEq(0,StringDataUtil.getDirectionality((char)559));
        assertTrue(StringDataLetterUtil.isLetter((char)559));
        assertTrue(StringDataUtil.isLetterOrDigit((char)559));
    }
    @Test
    public void d329(){
        assertEq(1,StringDataUtil.getType((char)560));
        assertEq(0,StringDataUtil.getDirectionality((char)560));
        assertTrue(StringDataLetterUtil.isLetter((char)560));
        assertTrue(StringDataUtil.isLetterOrDigit((char)560));
    }
    @Test
    public void d330(){
        assertEq(2,StringDataUtil.getType((char)561));
        assertEq(0,StringDataUtil.getDirectionality((char)561));
        assertTrue(StringDataLetterUtil.isLetter((char)561));
        assertTrue(StringDataUtil.isLetterOrDigit((char)561));
    }
    @Test
    public void d331(){
        assertEq(1,StringDataUtil.getType((char)562));
        assertEq(0,StringDataUtil.getDirectionality((char)562));
        assertTrue(StringDataLetterUtil.isLetter((char)562));
        assertTrue(StringDataUtil.isLetterOrDigit((char)562));
    }
    @Test
    public void d332(){
        assertEq(2,StringDataUtil.getType((char)563));
        assertEq(0,StringDataUtil.getDirectionality((char)563));
        assertTrue(StringDataLetterUtil.isLetter((char)563));
        assertTrue(StringDataUtil.isLetterOrDigit((char)563));
    }
    @Test
    public void d333(){
        assertEq(1,StringDataUtil.getType((char)570));
        assertEq(0,StringDataUtil.getDirectionality((char)570));
        assertTrue(StringDataLetterUtil.isLetter((char)570));
        assertTrue(StringDataUtil.isLetterOrDigit((char)570));
    }
    @Test
    public void d334(){
        assertEq(2,StringDataUtil.getType((char)572));
        assertEq(0,StringDataUtil.getDirectionality((char)572));
        assertTrue(StringDataLetterUtil.isLetter((char)572));
        assertTrue(StringDataUtil.isLetterOrDigit((char)572));
    }
    @Test
    public void d335(){
        assertEq(1,StringDataUtil.getType((char)573));
        assertEq(0,StringDataUtil.getDirectionality((char)573));
        assertTrue(StringDataLetterUtil.isLetter((char)573));
        assertTrue(StringDataUtil.isLetterOrDigit((char)573));
    }
    @Test
    public void d336(){
        assertEq(2,StringDataUtil.getType((char)575));
        assertEq(0,StringDataUtil.getDirectionality((char)575));
        assertTrue(StringDataLetterUtil.isLetter((char)575));
        assertTrue(StringDataUtil.isLetterOrDigit((char)575));
    }
    @Test
    public void d337(){
        assertEq(1,StringDataUtil.getType((char)577));
        assertEq(0,StringDataUtil.getDirectionality((char)577));
        assertTrue(StringDataLetterUtil.isLetter((char)577));
        assertTrue(StringDataUtil.isLetterOrDigit((char)577));
    }
    @Test
    public void d338(){
        assertEq(2,StringDataUtil.getType((char)578));
        assertEq(0,StringDataUtil.getDirectionality((char)578));
        assertTrue(StringDataLetterUtil.isLetter((char)578));
        assertTrue(StringDataUtil.isLetterOrDigit((char)578));
    }
    @Test
    public void d339(){
        assertEq(1,StringDataUtil.getType((char)579));
        assertEq(0,StringDataUtil.getDirectionality((char)579));
        assertTrue(StringDataLetterUtil.isLetter((char)579));
        assertTrue(StringDataUtil.isLetterOrDigit((char)579));
    }
    @Test
    public void d340(){
        assertEq(2,StringDataUtil.getType((char)583));
        assertEq(0,StringDataUtil.getDirectionality((char)583));
        assertTrue(StringDataLetterUtil.isLetter((char)583));
        assertTrue(StringDataUtil.isLetterOrDigit((char)583));
    }
    @Test
    public void d341(){
        assertEq(1,StringDataUtil.getType((char)584));
        assertEq(0,StringDataUtil.getDirectionality((char)584));
        assertTrue(StringDataLetterUtil.isLetter((char)584));
        assertTrue(StringDataUtil.isLetterOrDigit((char)584));
    }
    @Test
    public void d342(){
        assertEq(2,StringDataUtil.getType((char)585));
        assertEq(0,StringDataUtil.getDirectionality((char)585));
        assertTrue(StringDataLetterUtil.isLetter((char)585));
        assertTrue(StringDataUtil.isLetterOrDigit((char)585));
    }
    @Test
    public void d343(){
        assertEq(1,StringDataUtil.getType((char)586));
        assertEq(0,StringDataUtil.getDirectionality((char)586));
        assertTrue(StringDataLetterUtil.isLetter((char)586));
        assertTrue(StringDataUtil.isLetterOrDigit((char)586));
    }
    @Test
    public void d344(){
        assertEq(2,StringDataUtil.getType((char)587));
        assertEq(0,StringDataUtil.getDirectionality((char)587));
        assertTrue(StringDataLetterUtil.isLetter((char)587));
        assertTrue(StringDataUtil.isLetterOrDigit((char)587));
    }
    @Test
    public void d345(){
        assertEq(1,StringDataUtil.getType((char)588));
        assertEq(0,StringDataUtil.getDirectionality((char)588));
        assertTrue(StringDataLetterUtil.isLetter((char)588));
        assertTrue(StringDataUtil.isLetterOrDigit((char)588));
    }
    @Test
    public void d346(){
        assertEq(2,StringDataUtil.getType((char)589));
        assertEq(0,StringDataUtil.getDirectionality((char)589));
        assertTrue(StringDataLetterUtil.isLetter((char)589));
        assertTrue(StringDataUtil.isLetterOrDigit((char)589));
    }
    @Test
    public void d347(){
        assertEq(1,StringDataUtil.getType((char)590));
        assertEq(0,StringDataUtil.getDirectionality((char)590));
        assertTrue(StringDataLetterUtil.isLetter((char)590));
        assertTrue(StringDataUtil.isLetterOrDigit((char)590));
    }
    @Test
    public void d348(){
        assertEq(2,StringDataUtil.getType((char)591));
        assertEq(0,StringDataUtil.getDirectionality((char)591));
        assertTrue(StringDataLetterUtil.isLetter((char)591));
        assertTrue(StringDataUtil.isLetterOrDigit((char)591));
    }
    @Test
    public void d349(){
        assertEq(5,StringDataUtil.getType((char)660));
        assertEq(0,StringDataUtil.getDirectionality((char)660));
        assertTrue(StringDataLetterUtil.isLetter((char)660));
        assertTrue(StringDataUtil.isLetterOrDigit((char)660));
    }
    @Test
    public void d350(){
        assertEq(2,StringDataUtil.getType((char)661));
        assertEq(0,StringDataUtil.getDirectionality((char)661));
        assertTrue(StringDataLetterUtil.isLetter((char)661));
        assertTrue(StringDataUtil.isLetterOrDigit((char)661));
    }
    @Test
    public void d351(){
        assertEq(4,StringDataUtil.getType((char)688));
        assertEq(0,StringDataUtil.getDirectionality((char)688));
        assertTrue(StringDataLetterUtil.isLetter((char)688));
        assertTrue(StringDataUtil.isLetterOrDigit((char)688));
    }
    @Test
    public void d352(){
        assertEq(27,StringDataUtil.getType((char)706));
        assertEq(13,StringDataUtil.getDirectionality((char)706));
        assertFalse(StringDataLetterUtil.isLetter((char)706));
        assertFalse(StringDataUtil.isLetterOrDigit((char)706));
    }
    @Test
    public void d353(){
        assertEq(4,StringDataUtil.getType((char)710));
        assertEq(13,StringDataUtil.getDirectionality((char)710));
        assertTrue(StringDataLetterUtil.isLetter((char)710));
        assertTrue(StringDataUtil.isLetterOrDigit((char)710));
    }
    @Test
    public void d354(){
        assertEq(27,StringDataUtil.getType((char)722));
        assertEq(13,StringDataUtil.getDirectionality((char)722));
        assertFalse(StringDataLetterUtil.isLetter((char)722));
        assertFalse(StringDataUtil.isLetterOrDigit((char)722));
    }
    @Test
    public void d355(){
        assertEq(4,StringDataUtil.getType((char)736));
        assertEq(0,StringDataUtil.getDirectionality((char)736));
        assertTrue(StringDataLetterUtil.isLetter((char)736));
        assertTrue(StringDataUtil.isLetterOrDigit((char)736));
    }
    @Test
    public void d356(){
        assertEq(27,StringDataUtil.getType((char)741));
        assertEq(13,StringDataUtil.getDirectionality((char)741));
        assertFalse(StringDataLetterUtil.isLetter((char)741));
        assertFalse(StringDataUtil.isLetterOrDigit((char)741));
    }
    @Test
    public void d357(){
        assertEq(4,StringDataUtil.getType((char)748));
        assertEq(13,StringDataUtil.getDirectionality((char)748));
        assertTrue(StringDataLetterUtil.isLetter((char)748));
        assertTrue(StringDataUtil.isLetterOrDigit((char)748));
    }
    @Test
    public void d358(){
        assertEq(27,StringDataUtil.getType((char)749));
        assertEq(13,StringDataUtil.getDirectionality((char)749));
        assertFalse(StringDataLetterUtil.isLetter((char)749));
        assertFalse(StringDataUtil.isLetterOrDigit((char)749));
    }
    @Test
    public void d359(){
        assertEq(4,StringDataUtil.getType((char)750));
        assertEq(0,StringDataUtil.getDirectionality((char)750));
        assertTrue(StringDataLetterUtil.isLetter((char)750));
        assertTrue(StringDataUtil.isLetterOrDigit((char)750));
    }
    @Test
    public void d360(){
        assertEq(27,StringDataUtil.getType((char)751));
        assertEq(13,StringDataUtil.getDirectionality((char)751));
        assertFalse(StringDataLetterUtil.isLetter((char)751));
        assertFalse(StringDataUtil.isLetterOrDigit((char)751));
    }
    @Test
    public void d361(){
        assertEq(6,StringDataUtil.getType((char)768));
        assertEq(8,StringDataUtil.getDirectionality((char)768));
        assertFalse(StringDataLetterUtil.isLetter((char)768));
        assertFalse(StringDataUtil.isLetterOrDigit((char)768));
    }
    @Test
    public void d362(){
        assertEq(1,StringDataUtil.getType((char)880));
        assertEq(0,StringDataUtil.getDirectionality((char)880));
        assertTrue(StringDataLetterUtil.isLetter((char)880));
        assertTrue(StringDataUtil.isLetterOrDigit((char)880));
    }
    @Test
    public void d363(){
        assertEq(2,StringDataUtil.getType((char)881));
        assertEq(0,StringDataUtil.getDirectionality((char)881));
        assertTrue(StringDataLetterUtil.isLetter((char)881));
        assertTrue(StringDataUtil.isLetterOrDigit((char)881));
    }
    @Test
    public void d364(){
        assertEq(1,StringDataUtil.getType((char)882));
        assertEq(0,StringDataUtil.getDirectionality((char)882));
        assertTrue(StringDataLetterUtil.isLetter((char)882));
        assertTrue(StringDataUtil.isLetterOrDigit((char)882));
    }
    @Test
    public void d365(){
        assertEq(2,StringDataUtil.getType((char)883));
        assertEq(0,StringDataUtil.getDirectionality((char)883));
        assertTrue(StringDataLetterUtil.isLetter((char)883));
        assertTrue(StringDataUtil.isLetterOrDigit((char)883));
    }
    @Test
    public void d366(){
        assertEq(4,StringDataUtil.getType((char)884));
        assertEq(13,StringDataUtil.getDirectionality((char)884));
        assertTrue(StringDataLetterUtil.isLetter((char)884));
        assertTrue(StringDataUtil.isLetterOrDigit((char)884));
    }
    @Test
    public void d367(){
        assertEq(27,StringDataUtil.getType((char)885));
        assertEq(13,StringDataUtil.getDirectionality((char)885));
        assertFalse(StringDataLetterUtil.isLetter((char)885));
        assertFalse(StringDataUtil.isLetterOrDigit((char)885));
    }
    @Test
    public void d368(){
        assertEq(1,StringDataUtil.getType((char)886));
        assertEq(0,StringDataUtil.getDirectionality((char)886));
        assertTrue(StringDataLetterUtil.isLetter((char)886));
        assertTrue(StringDataUtil.isLetterOrDigit((char)886));
    }
    @Test
    public void d369(){
        assertEq(2,StringDataUtil.getType((char)887));
        assertEq(0,StringDataUtil.getDirectionality((char)887));
        assertTrue(StringDataLetterUtil.isLetter((char)887));
        assertTrue(StringDataUtil.isLetterOrDigit((char)887));
    }
    @Test
    public void d370(){
        assertEq(0,StringDataUtil.getType((char)888));
        assertEq(-1,StringDataUtil.getDirectionality((char)888));
        assertFalse(StringDataLetterUtil.isLetter((char)888));
        assertFalse(StringDataUtil.isLetterOrDigit((char)888));
    }
    @Test
    public void d371(){
        assertEq(4,StringDataUtil.getType((char)890));
        assertEq(0,StringDataUtil.getDirectionality((char)890));
        assertTrue(StringDataLetterUtil.isLetter((char)890));
        assertTrue(StringDataUtil.isLetterOrDigit((char)890));
    }
    @Test
    public void d372(){
        assertEq(2,StringDataUtil.getType((char)891));
        assertEq(0,StringDataUtil.getDirectionality((char)891));
        assertTrue(StringDataLetterUtil.isLetter((char)891));
        assertTrue(StringDataUtil.isLetterOrDigit((char)891));
    }
    @Test
    public void d373(){
        assertEq(24,StringDataUtil.getType((char)894));
        assertEq(13,StringDataUtil.getDirectionality((char)894));
        assertFalse(StringDataLetterUtil.isLetter((char)894));
        assertFalse(StringDataUtil.isLetterOrDigit((char)894));
    }
    @Test
    public void d374(){
        assertEq(0,StringDataUtil.getType((char)895));
        assertEq(-1,StringDataUtil.getDirectionality((char)895));
        assertFalse(StringDataLetterUtil.isLetter((char)895));
        assertFalse(StringDataUtil.isLetterOrDigit((char)895));
    }
    @Test
    public void d375(){
        assertEq(27,StringDataUtil.getType((char)900));
        assertEq(13,StringDataUtil.getDirectionality((char)900));
        assertFalse(StringDataLetterUtil.isLetter((char)900));
        assertFalse(StringDataUtil.isLetterOrDigit((char)900));
    }
    @Test
    public void d376(){
        assertEq(1,StringDataUtil.getType((char)902));
        assertEq(0,StringDataUtil.getDirectionality((char)902));
        assertTrue(StringDataLetterUtil.isLetter((char)902));
        assertTrue(StringDataUtil.isLetterOrDigit((char)902));
    }
    @Test
    public void d377(){
        assertEq(24,StringDataUtil.getType((char)903));
        assertEq(13,StringDataUtil.getDirectionality((char)903));
        assertFalse(StringDataLetterUtil.isLetter((char)903));
        assertFalse(StringDataUtil.isLetterOrDigit((char)903));
    }
    @Test
    public void d378(){
        assertEq(1,StringDataUtil.getType((char)904));
        assertEq(0,StringDataUtil.getDirectionality((char)904));
        assertTrue(StringDataLetterUtil.isLetter((char)904));
        assertTrue(StringDataUtil.isLetterOrDigit((char)904));
    }
    @Test
    public void d379(){
        assertEq(0,StringDataUtil.getType((char)907));
        assertEq(-1,StringDataUtil.getDirectionality((char)907));
        assertFalse(StringDataLetterUtil.isLetter((char)907));
        assertFalse(StringDataUtil.isLetterOrDigit((char)907));
    }
    @Test
    public void d380(){
        assertEq(1,StringDataUtil.getType((char)908));
        assertEq(0,StringDataUtil.getDirectionality((char)908));
        assertTrue(StringDataLetterUtil.isLetter((char)908));
        assertTrue(StringDataUtil.isLetterOrDigit((char)908));
    }
    @Test
    public void d381(){
        assertEq(0,StringDataUtil.getType((char)909));
        assertEq(-1,StringDataUtil.getDirectionality((char)909));
        assertFalse(StringDataLetterUtil.isLetter((char)909));
        assertFalse(StringDataUtil.isLetterOrDigit((char)909));
    }
    @Test
    public void d382(){
        assertEq(1,StringDataUtil.getType((char)910));
        assertEq(0,StringDataUtil.getDirectionality((char)910));
        assertTrue(StringDataLetterUtil.isLetter((char)910));
        assertTrue(StringDataUtil.isLetterOrDigit((char)910));
    }
    @Test
    public void d383(){
        assertEq(2,StringDataUtil.getType((char)912));
        assertEq(0,StringDataUtil.getDirectionality((char)912));
        assertTrue(StringDataLetterUtil.isLetter((char)912));
        assertTrue(StringDataUtil.isLetterOrDigit((char)912));
    }
    @Test
    public void d384(){
        assertEq(1,StringDataUtil.getType((char)913));
        assertEq(0,StringDataUtil.getDirectionality((char)913));
        assertTrue(StringDataLetterUtil.isLetter((char)913));
        assertTrue(StringDataUtil.isLetterOrDigit((char)913));
    }
    @Test
    public void d385(){
        assertEq(0,StringDataUtil.getType((char)930));
        assertEq(-1,StringDataUtil.getDirectionality((char)930));
        assertFalse(StringDataLetterUtil.isLetter((char)930));
        assertFalse(StringDataUtil.isLetterOrDigit((char)930));
    }
    @Test
    public void d386(){
        assertEq(1,StringDataUtil.getType((char)931));
        assertEq(0,StringDataUtil.getDirectionality((char)931));
        assertTrue(StringDataLetterUtil.isLetter((char)931));
        assertTrue(StringDataUtil.isLetterOrDigit((char)931));
    }
    @Test
    public void d387(){
        assertEq(2,StringDataUtil.getType((char)940));
        assertEq(0,StringDataUtil.getDirectionality((char)940));
        assertTrue(StringDataLetterUtil.isLetter((char)940));
        assertTrue(StringDataUtil.isLetterOrDigit((char)940));
    }
    @Test
    public void d388(){
        assertEq(1,StringDataUtil.getType((char)975));
        assertEq(0,StringDataUtil.getDirectionality((char)975));
        assertTrue(StringDataLetterUtil.isLetter((char)975));
        assertTrue(StringDataUtil.isLetterOrDigit((char)975));
    }
    @Test
    public void d389(){
        assertEq(2,StringDataUtil.getType((char)976));
        assertEq(0,StringDataUtil.getDirectionality((char)976));
        assertTrue(StringDataLetterUtil.isLetter((char)976));
        assertTrue(StringDataUtil.isLetterOrDigit((char)976));
    }
    @Test
    public void d390(){
        assertEq(1,StringDataUtil.getType((char)978));
        assertEq(0,StringDataUtil.getDirectionality((char)978));
        assertTrue(StringDataLetterUtil.isLetter((char)978));
        assertTrue(StringDataUtil.isLetterOrDigit((char)978));
    }
    @Test
    public void d391(){
        assertEq(2,StringDataUtil.getType((char)981));
        assertEq(0,StringDataUtil.getDirectionality((char)981));
        assertTrue(StringDataLetterUtil.isLetter((char)981));
        assertTrue(StringDataUtil.isLetterOrDigit((char)981));
    }
    @Test
    public void d392(){
        assertEq(1,StringDataUtil.getType((char)984));
        assertEq(0,StringDataUtil.getDirectionality((char)984));
        assertTrue(StringDataLetterUtil.isLetter((char)984));
        assertTrue(StringDataUtil.isLetterOrDigit((char)984));
    }
    @Test
    public void d393(){
        assertEq(2,StringDataUtil.getType((char)985));
        assertEq(0,StringDataUtil.getDirectionality((char)985));
        assertTrue(StringDataLetterUtil.isLetter((char)985));
        assertTrue(StringDataUtil.isLetterOrDigit((char)985));
    }
    @Test
    public void d394(){
        assertEq(1,StringDataUtil.getType((char)986));
        assertEq(0,StringDataUtil.getDirectionality((char)986));
        assertTrue(StringDataLetterUtil.isLetter((char)986));
        assertTrue(StringDataUtil.isLetterOrDigit((char)986));
    }
    @Test
    public void d395(){
        assertEq(2,StringDataUtil.getType((char)987));
        assertEq(0,StringDataUtil.getDirectionality((char)987));
        assertTrue(StringDataLetterUtil.isLetter((char)987));
        assertTrue(StringDataUtil.isLetterOrDigit((char)987));
    }
    @Test
    public void d396(){
        assertEq(1,StringDataUtil.getType((char)988));
        assertEq(0,StringDataUtil.getDirectionality((char)988));
        assertTrue(StringDataLetterUtil.isLetter((char)988));
        assertTrue(StringDataUtil.isLetterOrDigit((char)988));
    }
    @Test
    public void d397(){
        assertEq(2,StringDataUtil.getType((char)989));
        assertEq(0,StringDataUtil.getDirectionality((char)989));
        assertTrue(StringDataLetterUtil.isLetter((char)989));
        assertTrue(StringDataUtil.isLetterOrDigit((char)989));
    }
    @Test
    public void d398(){
        assertEq(1,StringDataUtil.getType((char)990));
        assertEq(0,StringDataUtil.getDirectionality((char)990));
        assertTrue(StringDataLetterUtil.isLetter((char)990));
        assertTrue(StringDataUtil.isLetterOrDigit((char)990));
    }
    @Test
    public void d399(){
        assertEq(2,StringDataUtil.getType((char)991));
        assertEq(0,StringDataUtil.getDirectionality((char)991));
        assertTrue(StringDataLetterUtil.isLetter((char)991));
        assertTrue(StringDataUtil.isLetterOrDigit((char)991));
    }
    @Test
    public void d400(){
        assertEq(1,StringDataUtil.getType((char)992));
        assertEq(0,StringDataUtil.getDirectionality((char)992));
        assertTrue(StringDataLetterUtil.isLetter((char)992));
        assertTrue(StringDataUtil.isLetterOrDigit((char)992));
    }
    @Test
    public void d401(){
        assertEq(2,StringDataUtil.getType((char)993));
        assertEq(0,StringDataUtil.getDirectionality((char)993));
        assertTrue(StringDataLetterUtil.isLetter((char)993));
        assertTrue(StringDataUtil.isLetterOrDigit((char)993));
    }
    @Test
    public void d402(){
        assertEq(1,StringDataUtil.getType((char)994));
        assertEq(0,StringDataUtil.getDirectionality((char)994));
        assertTrue(StringDataLetterUtil.isLetter((char)994));
        assertTrue(StringDataUtil.isLetterOrDigit((char)994));
    }
    @Test
    public void d403(){
        assertEq(2,StringDataUtil.getType((char)995));
        assertEq(0,StringDataUtil.getDirectionality((char)995));
        assertTrue(StringDataLetterUtil.isLetter((char)995));
        assertTrue(StringDataUtil.isLetterOrDigit((char)995));
    }
    @Test
    public void d404(){
        assertEq(1,StringDataUtil.getType((char)996));
        assertEq(0,StringDataUtil.getDirectionality((char)996));
        assertTrue(StringDataLetterUtil.isLetter((char)996));
        assertTrue(StringDataUtil.isLetterOrDigit((char)996));
    }
    @Test
    public void d405(){
        assertEq(2,StringDataUtil.getType((char)997));
        assertEq(0,StringDataUtil.getDirectionality((char)997));
        assertTrue(StringDataLetterUtil.isLetter((char)997));
        assertTrue(StringDataUtil.isLetterOrDigit((char)997));
    }
    @Test
    public void d406(){
        assertEq(1,StringDataUtil.getType((char)998));
        assertEq(0,StringDataUtil.getDirectionality((char)998));
        assertTrue(StringDataLetterUtil.isLetter((char)998));
        assertTrue(StringDataUtil.isLetterOrDigit((char)998));
    }
    @Test
    public void d407(){
        assertEq(2,StringDataUtil.getType((char)999));
        assertEq(0,StringDataUtil.getDirectionality((char)999));
        assertTrue(StringDataLetterUtil.isLetter((char)999));
        assertTrue(StringDataUtil.isLetterOrDigit((char)999));
    }
    @Test
    public void d408(){
        assertEq(1,StringDataUtil.getType((char)1000));
        assertEq(0,StringDataUtil.getDirectionality((char)1000));
        assertTrue(StringDataLetterUtil.isLetter((char)1000));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1000));
    }
    @Test
    public void d409(){
        assertEq(2,StringDataUtil.getType((char)1001));
        assertEq(0,StringDataUtil.getDirectionality((char)1001));
        assertTrue(StringDataLetterUtil.isLetter((char)1001));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1001));
    }
    @Test
    public void d410(){
        assertEq(1,StringDataUtil.getType((char)1002));
        assertEq(0,StringDataUtil.getDirectionality((char)1002));
        assertTrue(StringDataLetterUtil.isLetter((char)1002));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1002));
    }
    @Test
    public void d411(){
        assertEq(2,StringDataUtil.getType((char)1003));
        assertEq(0,StringDataUtil.getDirectionality((char)1003));
        assertTrue(StringDataLetterUtil.isLetter((char)1003));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1003));
    }
    @Test
    public void d412(){
        assertEq(1,StringDataUtil.getType((char)1004));
        assertEq(0,StringDataUtil.getDirectionality((char)1004));
        assertTrue(StringDataLetterUtil.isLetter((char)1004));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1004));
    }
    @Test
    public void d413(){
        assertEq(2,StringDataUtil.getType((char)1005));
        assertEq(0,StringDataUtil.getDirectionality((char)1005));
        assertTrue(StringDataLetterUtil.isLetter((char)1005));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1005));
    }
    @Test
    public void d414(){
        assertEq(1,StringDataUtil.getType((char)1006));
        assertEq(0,StringDataUtil.getDirectionality((char)1006));
        assertTrue(StringDataLetterUtil.isLetter((char)1006));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1006));
    }
    @Test
    public void d415(){
        assertEq(2,StringDataUtil.getType((char)1007));
        assertEq(0,StringDataUtil.getDirectionality((char)1007));
        assertTrue(StringDataLetterUtil.isLetter((char)1007));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1007));
    }
    @Test
    public void d416(){
        assertEq(1,StringDataUtil.getType((char)1012));
        assertEq(0,StringDataUtil.getDirectionality((char)1012));
        assertTrue(StringDataLetterUtil.isLetter((char)1012));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1012));
    }
    @Test
    public void d417(){
        assertEq(2,StringDataUtil.getType((char)1013));
        assertEq(0,StringDataUtil.getDirectionality((char)1013));
        assertTrue(StringDataLetterUtil.isLetter((char)1013));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1013));
    }
    @Test
    public void d418(){
        assertEq(25,StringDataUtil.getType((char)1014));
        assertEq(13,StringDataUtil.getDirectionality((char)1014));
        assertFalse(StringDataLetterUtil.isLetter((char)1014));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1014));
    }
    @Test
    public void d419(){
        assertEq(1,StringDataUtil.getType((char)1015));
        assertEq(0,StringDataUtil.getDirectionality((char)1015));
        assertTrue(StringDataLetterUtil.isLetter((char)1015));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1015));
    }
    @Test
    public void d420(){
        assertEq(2,StringDataUtil.getType((char)1016));
        assertEq(0,StringDataUtil.getDirectionality((char)1016));
        assertTrue(StringDataLetterUtil.isLetter((char)1016));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1016));
    }
    @Test
    public void d421(){
        assertEq(1,StringDataUtil.getType((char)1017));
        assertEq(0,StringDataUtil.getDirectionality((char)1017));
        assertTrue(StringDataLetterUtil.isLetter((char)1017));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1017));
    }
    @Test
    public void d422(){
        assertEq(2,StringDataUtil.getType((char)1019));
        assertEq(0,StringDataUtil.getDirectionality((char)1019));
        assertTrue(StringDataLetterUtil.isLetter((char)1019));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1019));
    }
    @Test
    public void d423(){
        assertEq(1,StringDataUtil.getType((char)1021));
        assertEq(0,StringDataUtil.getDirectionality((char)1021));
        assertTrue(StringDataLetterUtil.isLetter((char)1021));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1021));
    }
    @Test
    public void d424(){
        assertEq(2,StringDataUtil.getType((char)1072));
        assertEq(0,StringDataUtil.getDirectionality((char)1072));
        assertTrue(StringDataLetterUtil.isLetter((char)1072));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1072));
    }
    @Test
    public void d425(){
        assertEq(1,StringDataUtil.getType((char)1120));
        assertEq(0,StringDataUtil.getDirectionality((char)1120));
        assertTrue(StringDataLetterUtil.isLetter((char)1120));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1120));
    }
    @Test
    public void d426(){
        assertEq(2,StringDataUtil.getType((char)1121));
        assertEq(0,StringDataUtil.getDirectionality((char)1121));
        assertTrue(StringDataLetterUtil.isLetter((char)1121));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1121));
    }
    @Test
    public void d427(){
        assertEq(1,StringDataUtil.getType((char)1122));
        assertEq(0,StringDataUtil.getDirectionality((char)1122));
        assertTrue(StringDataLetterUtil.isLetter((char)1122));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1122));
    }
    @Test
    public void d428(){
        assertEq(2,StringDataUtil.getType((char)1123));
        assertEq(0,StringDataUtil.getDirectionality((char)1123));
        assertTrue(StringDataLetterUtil.isLetter((char)1123));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1123));
    }
    @Test
    public void d429(){
        assertEq(1,StringDataUtil.getType((char)1124));
        assertEq(0,StringDataUtil.getDirectionality((char)1124));
        assertTrue(StringDataLetterUtil.isLetter((char)1124));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1124));
    }
    @Test
    public void d430(){
        assertEq(2,StringDataUtil.getType((char)1125));
        assertEq(0,StringDataUtil.getDirectionality((char)1125));
        assertTrue(StringDataLetterUtil.isLetter((char)1125));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1125));
    }
    @Test
    public void d431(){
        assertEq(1,StringDataUtil.getType((char)1126));
        assertEq(0,StringDataUtil.getDirectionality((char)1126));
        assertTrue(StringDataLetterUtil.isLetter((char)1126));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1126));
    }
    @Test
    public void d432(){
        assertEq(2,StringDataUtil.getType((char)1127));
        assertEq(0,StringDataUtil.getDirectionality((char)1127));
        assertTrue(StringDataLetterUtil.isLetter((char)1127));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1127));
    }
    @Test
    public void d433(){
        assertEq(1,StringDataUtil.getType((char)1128));
        assertEq(0,StringDataUtil.getDirectionality((char)1128));
        assertTrue(StringDataLetterUtil.isLetter((char)1128));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1128));
    }
    @Test
    public void d434(){
        assertEq(2,StringDataUtil.getType((char)1129));
        assertEq(0,StringDataUtil.getDirectionality((char)1129));
        assertTrue(StringDataLetterUtil.isLetter((char)1129));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1129));
    }
    @Test
    public void d435(){
        assertEq(1,StringDataUtil.getType((char)1130));
        assertEq(0,StringDataUtil.getDirectionality((char)1130));
        assertTrue(StringDataLetterUtil.isLetter((char)1130));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1130));
    }
    @Test
    public void d436(){
        assertEq(2,StringDataUtil.getType((char)1131));
        assertEq(0,StringDataUtil.getDirectionality((char)1131));
        assertTrue(StringDataLetterUtil.isLetter((char)1131));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1131));
    }
    @Test
    public void d437(){
        assertEq(1,StringDataUtil.getType((char)1132));
        assertEq(0,StringDataUtil.getDirectionality((char)1132));
        assertTrue(StringDataLetterUtil.isLetter((char)1132));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1132));
    }
    @Test
    public void d438(){
        assertEq(2,StringDataUtil.getType((char)1133));
        assertEq(0,StringDataUtil.getDirectionality((char)1133));
        assertTrue(StringDataLetterUtil.isLetter((char)1133));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1133));
    }
    @Test
    public void d439(){
        assertEq(1,StringDataUtil.getType((char)1134));
        assertEq(0,StringDataUtil.getDirectionality((char)1134));
        assertTrue(StringDataLetterUtil.isLetter((char)1134));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1134));
    }
    @Test
    public void d440(){
        assertEq(2,StringDataUtil.getType((char)1135));
        assertEq(0,StringDataUtil.getDirectionality((char)1135));
        assertTrue(StringDataLetterUtil.isLetter((char)1135));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1135));
    }
    @Test
    public void d441(){
        assertEq(1,StringDataUtil.getType((char)1136));
        assertEq(0,StringDataUtil.getDirectionality((char)1136));
        assertTrue(StringDataLetterUtil.isLetter((char)1136));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1136));
    }
    @Test
    public void d442(){
        assertEq(2,StringDataUtil.getType((char)1137));
        assertEq(0,StringDataUtil.getDirectionality((char)1137));
        assertTrue(StringDataLetterUtil.isLetter((char)1137));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1137));
    }
    @Test
    public void d443(){
        assertEq(1,StringDataUtil.getType((char)1138));
        assertEq(0,StringDataUtil.getDirectionality((char)1138));
        assertTrue(StringDataLetterUtil.isLetter((char)1138));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1138));
    }
    @Test
    public void d444(){
        assertEq(2,StringDataUtil.getType((char)1139));
        assertEq(0,StringDataUtil.getDirectionality((char)1139));
        assertTrue(StringDataLetterUtil.isLetter((char)1139));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1139));
    }
    @Test
    public void d445(){
        assertEq(1,StringDataUtil.getType((char)1140));
        assertEq(0,StringDataUtil.getDirectionality((char)1140));
        assertTrue(StringDataLetterUtil.isLetter((char)1140));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1140));
    }
    @Test
    public void d446(){
        assertEq(2,StringDataUtil.getType((char)1141));
        assertEq(0,StringDataUtil.getDirectionality((char)1141));
        assertTrue(StringDataLetterUtil.isLetter((char)1141));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1141));
    }
    @Test
    public void d447(){
        assertEq(1,StringDataUtil.getType((char)1142));
        assertEq(0,StringDataUtil.getDirectionality((char)1142));
        assertTrue(StringDataLetterUtil.isLetter((char)1142));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1142));
    }
    @Test
    public void d448(){
        assertEq(2,StringDataUtil.getType((char)1143));
        assertEq(0,StringDataUtil.getDirectionality((char)1143));
        assertTrue(StringDataLetterUtil.isLetter((char)1143));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1143));
    }
    @Test
    public void d449(){
        assertEq(1,StringDataUtil.getType((char)1144));
        assertEq(0,StringDataUtil.getDirectionality((char)1144));
        assertTrue(StringDataLetterUtil.isLetter((char)1144));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1144));
    }
    @Test
    public void d450(){
        assertEq(2,StringDataUtil.getType((char)1145));
        assertEq(0,StringDataUtil.getDirectionality((char)1145));
        assertTrue(StringDataLetterUtil.isLetter((char)1145));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1145));
    }
    @Test
    public void d451(){
        assertEq(1,StringDataUtil.getType((char)1146));
        assertEq(0,StringDataUtil.getDirectionality((char)1146));
        assertTrue(StringDataLetterUtil.isLetter((char)1146));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1146));
    }
    @Test
    public void d452(){
        assertEq(2,StringDataUtil.getType((char)1147));
        assertEq(0,StringDataUtil.getDirectionality((char)1147));
        assertTrue(StringDataLetterUtil.isLetter((char)1147));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1147));
    }
    @Test
    public void d453(){
        assertEq(1,StringDataUtil.getType((char)1148));
        assertEq(0,StringDataUtil.getDirectionality((char)1148));
        assertTrue(StringDataLetterUtil.isLetter((char)1148));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1148));
    }
    @Test
    public void d454(){
        assertEq(2,StringDataUtil.getType((char)1149));
        assertEq(0,StringDataUtil.getDirectionality((char)1149));
        assertTrue(StringDataLetterUtil.isLetter((char)1149));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1149));
    }
    @Test
    public void d455(){
        assertEq(1,StringDataUtil.getType((char)1150));
        assertEq(0,StringDataUtil.getDirectionality((char)1150));
        assertTrue(StringDataLetterUtil.isLetter((char)1150));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1150));
    }
    @Test
    public void d456(){
        assertEq(2,StringDataUtil.getType((char)1151));
        assertEq(0,StringDataUtil.getDirectionality((char)1151));
        assertTrue(StringDataLetterUtil.isLetter((char)1151));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1151));
    }
    @Test
    public void d457(){
        assertEq(1,StringDataUtil.getType((char)1152));
        assertEq(0,StringDataUtil.getDirectionality((char)1152));
        assertTrue(StringDataLetterUtil.isLetter((char)1152));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1152));
    }
    @Test
    public void d458(){
        assertEq(2,StringDataUtil.getType((char)1153));
        assertEq(0,StringDataUtil.getDirectionality((char)1153));
        assertTrue(StringDataLetterUtil.isLetter((char)1153));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1153));
    }
    @Test
    public void d459(){
        assertEq(28,StringDataUtil.getType((char)1154));
        assertEq(0,StringDataUtil.getDirectionality((char)1154));
        assertFalse(StringDataLetterUtil.isLetter((char)1154));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1154));
    }
    @Test
    public void d460(){
        assertEq(6,StringDataUtil.getType((char)1155));
        assertEq(8,StringDataUtil.getDirectionality((char)1155));
        assertFalse(StringDataLetterUtil.isLetter((char)1155));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1155));
    }
    @Test
    public void d461(){
        assertEq(7,StringDataUtil.getType((char)1160));
        assertEq(8,StringDataUtil.getDirectionality((char)1160));
        assertFalse(StringDataLetterUtil.isLetter((char)1160));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1160));
    }
    @Test
    public void d462(){
        assertEq(1,StringDataUtil.getType((char)1162));
        assertEq(0,StringDataUtil.getDirectionality((char)1162));
        assertTrue(StringDataLetterUtil.isLetter((char)1162));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1162));
    }
    @Test
    public void d463(){
        assertEq(2,StringDataUtil.getType((char)1163));
        assertEq(0,StringDataUtil.getDirectionality((char)1163));
        assertTrue(StringDataLetterUtil.isLetter((char)1163));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1163));
    }
    @Test
    public void d464(){
        assertEq(1,StringDataUtil.getType((char)1164));
        assertEq(0,StringDataUtil.getDirectionality((char)1164));
        assertTrue(StringDataLetterUtil.isLetter((char)1164));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1164));
    }
    @Test
    public void d465(){
        assertEq(2,StringDataUtil.getType((char)1165));
        assertEq(0,StringDataUtil.getDirectionality((char)1165));
        assertTrue(StringDataLetterUtil.isLetter((char)1165));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1165));
    }
    @Test
    public void d466(){
        assertEq(1,StringDataUtil.getType((char)1166));
        assertEq(0,StringDataUtil.getDirectionality((char)1166));
        assertTrue(StringDataLetterUtil.isLetter((char)1166));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1166));
    }
    @Test
    public void d467(){
        assertEq(2,StringDataUtil.getType((char)1167));
        assertEq(0,StringDataUtil.getDirectionality((char)1167));
        assertTrue(StringDataLetterUtil.isLetter((char)1167));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1167));
    }
    @Test
    public void d468(){
        assertEq(1,StringDataUtil.getType((char)1168));
        assertEq(0,StringDataUtil.getDirectionality((char)1168));
        assertTrue(StringDataLetterUtil.isLetter((char)1168));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1168));
    }
    @Test
    public void d469(){
        assertEq(2,StringDataUtil.getType((char)1169));
        assertEq(0,StringDataUtil.getDirectionality((char)1169));
        assertTrue(StringDataLetterUtil.isLetter((char)1169));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1169));
    }
    @Test
    public void d470(){
        assertEq(1,StringDataUtil.getType((char)1170));
        assertEq(0,StringDataUtil.getDirectionality((char)1170));
        assertTrue(StringDataLetterUtil.isLetter((char)1170));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1170));
    }
    @Test
    public void d471(){
        assertEq(2,StringDataUtil.getType((char)1171));
        assertEq(0,StringDataUtil.getDirectionality((char)1171));
        assertTrue(StringDataLetterUtil.isLetter((char)1171));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1171));
    }
    @Test
    public void d472(){
        assertEq(1,StringDataUtil.getType((char)1172));
        assertEq(0,StringDataUtil.getDirectionality((char)1172));
        assertTrue(StringDataLetterUtil.isLetter((char)1172));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1172));
    }
    @Test
    public void d473(){
        assertEq(2,StringDataUtil.getType((char)1173));
        assertEq(0,StringDataUtil.getDirectionality((char)1173));
        assertTrue(StringDataLetterUtil.isLetter((char)1173));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1173));
    }
    @Test
    public void d474(){
        assertEq(1,StringDataUtil.getType((char)1174));
        assertEq(0,StringDataUtil.getDirectionality((char)1174));
        assertTrue(StringDataLetterUtil.isLetter((char)1174));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1174));
    }
    @Test
    public void d475(){
        assertEq(2,StringDataUtil.getType((char)1175));
        assertEq(0,StringDataUtil.getDirectionality((char)1175));
        assertTrue(StringDataLetterUtil.isLetter((char)1175));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1175));
    }
    @Test
    public void d476(){
        assertEq(1,StringDataUtil.getType((char)1176));
        assertEq(0,StringDataUtil.getDirectionality((char)1176));
        assertTrue(StringDataLetterUtil.isLetter((char)1176));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1176));
    }
    @Test
    public void d477(){
        assertEq(2,StringDataUtil.getType((char)1177));
        assertEq(0,StringDataUtil.getDirectionality((char)1177));
        assertTrue(StringDataLetterUtil.isLetter((char)1177));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1177));
    }
    @Test
    public void d478(){
        assertEq(1,StringDataUtil.getType((char)1178));
        assertEq(0,StringDataUtil.getDirectionality((char)1178));
        assertTrue(StringDataLetterUtil.isLetter((char)1178));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1178));
    }
    @Test
    public void d479(){
        assertEq(2,StringDataUtil.getType((char)1179));
        assertEq(0,StringDataUtil.getDirectionality((char)1179));
        assertTrue(StringDataLetterUtil.isLetter((char)1179));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1179));
    }
    @Test
    public void d480(){
        assertEq(1,StringDataUtil.getType((char)1180));
        assertEq(0,StringDataUtil.getDirectionality((char)1180));
        assertTrue(StringDataLetterUtil.isLetter((char)1180));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1180));
    }
    @Test
    public void d481(){
        assertEq(2,StringDataUtil.getType((char)1181));
        assertEq(0,StringDataUtil.getDirectionality((char)1181));
        assertTrue(StringDataLetterUtil.isLetter((char)1181));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1181));
    }
    @Test
    public void d482(){
        assertEq(1,StringDataUtil.getType((char)1182));
        assertEq(0,StringDataUtil.getDirectionality((char)1182));
        assertTrue(StringDataLetterUtil.isLetter((char)1182));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1182));
    }
    @Test
    public void d483(){
        assertEq(2,StringDataUtil.getType((char)1183));
        assertEq(0,StringDataUtil.getDirectionality((char)1183));
        assertTrue(StringDataLetterUtil.isLetter((char)1183));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1183));
    }
    @Test
    public void d484(){
        assertEq(1,StringDataUtil.getType((char)1184));
        assertEq(0,StringDataUtil.getDirectionality((char)1184));
        assertTrue(StringDataLetterUtil.isLetter((char)1184));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1184));
    }
    @Test
    public void d485(){
        assertEq(2,StringDataUtil.getType((char)1185));
        assertEq(0,StringDataUtil.getDirectionality((char)1185));
        assertTrue(StringDataLetterUtil.isLetter((char)1185));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1185));
    }
    @Test
    public void d486(){
        assertEq(1,StringDataUtil.getType((char)1186));
        assertEq(0,StringDataUtil.getDirectionality((char)1186));
        assertTrue(StringDataLetterUtil.isLetter((char)1186));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1186));
    }
    @Test
    public void d487(){
        assertEq(2,StringDataUtil.getType((char)1187));
        assertEq(0,StringDataUtil.getDirectionality((char)1187));
        assertTrue(StringDataLetterUtil.isLetter((char)1187));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1187));
    }
    @Test
    public void d488(){
        assertEq(1,StringDataUtil.getType((char)1188));
        assertEq(0,StringDataUtil.getDirectionality((char)1188));
        assertTrue(StringDataLetterUtil.isLetter((char)1188));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1188));
    }
    @Test
    public void d489(){
        assertEq(2,StringDataUtil.getType((char)1189));
        assertEq(0,StringDataUtil.getDirectionality((char)1189));
        assertTrue(StringDataLetterUtil.isLetter((char)1189));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1189));
    }
    @Test
    public void d490(){
        assertEq(1,StringDataUtil.getType((char)1190));
        assertEq(0,StringDataUtil.getDirectionality((char)1190));
        assertTrue(StringDataLetterUtil.isLetter((char)1190));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1190));
    }
    @Test
    public void d491(){
        assertEq(2,StringDataUtil.getType((char)1191));
        assertEq(0,StringDataUtil.getDirectionality((char)1191));
        assertTrue(StringDataLetterUtil.isLetter((char)1191));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1191));
    }
    @Test
    public void d492(){
        assertEq(1,StringDataUtil.getType((char)1192));
        assertEq(0,StringDataUtil.getDirectionality((char)1192));
        assertTrue(StringDataLetterUtil.isLetter((char)1192));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1192));
    }
    @Test
    public void d493(){
        assertEq(2,StringDataUtil.getType((char)1193));
        assertEq(0,StringDataUtil.getDirectionality((char)1193));
        assertTrue(StringDataLetterUtil.isLetter((char)1193));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1193));
    }
    @Test
    public void d494(){
        assertEq(1,StringDataUtil.getType((char)1194));
        assertEq(0,StringDataUtil.getDirectionality((char)1194));
        assertTrue(StringDataLetterUtil.isLetter((char)1194));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1194));
    }
    @Test
    public void d495(){
        assertEq(2,StringDataUtil.getType((char)1195));
        assertEq(0,StringDataUtil.getDirectionality((char)1195));
        assertTrue(StringDataLetterUtil.isLetter((char)1195));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1195));
    }
    @Test
    public void d496(){
        assertEq(1,StringDataUtil.getType((char)1196));
        assertEq(0,StringDataUtil.getDirectionality((char)1196));
        assertTrue(StringDataLetterUtil.isLetter((char)1196));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1196));
    }
    @Test
    public void d497(){
        assertEq(2,StringDataUtil.getType((char)1197));
        assertEq(0,StringDataUtil.getDirectionality((char)1197));
        assertTrue(StringDataLetterUtil.isLetter((char)1197));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1197));
    }
    @Test
    public void d498(){
        assertEq(1,StringDataUtil.getType((char)1198));
        assertEq(0,StringDataUtil.getDirectionality((char)1198));
        assertTrue(StringDataLetterUtil.isLetter((char)1198));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1198));
    }
    @Test
    public void d499(){
        assertEq(2,StringDataUtil.getType((char)1199));
        assertEq(0,StringDataUtil.getDirectionality((char)1199));
        assertTrue(StringDataLetterUtil.isLetter((char)1199));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1199));
    }
    @Test
    public void d500(){
        assertEq(1,StringDataUtil.getType((char)1200));
        assertEq(0,StringDataUtil.getDirectionality((char)1200));
        assertTrue(StringDataLetterUtil.isLetter((char)1200));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1200));
    }
    @Test
    public void d501(){
        assertEq(2,StringDataUtil.getType((char)1201));
        assertEq(0,StringDataUtil.getDirectionality((char)1201));
        assertTrue(StringDataLetterUtil.isLetter((char)1201));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1201));
    }
    @Test
    public void d502(){
        assertEq(1,StringDataUtil.getType((char)1202));
        assertEq(0,StringDataUtil.getDirectionality((char)1202));
        assertTrue(StringDataLetterUtil.isLetter((char)1202));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1202));
    }
    @Test
    public void d503(){
        assertEq(2,StringDataUtil.getType((char)1203));
        assertEq(0,StringDataUtil.getDirectionality((char)1203));
        assertTrue(StringDataLetterUtil.isLetter((char)1203));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1203));
    }
    @Test
    public void d504(){
        assertEq(1,StringDataUtil.getType((char)1204));
        assertEq(0,StringDataUtil.getDirectionality((char)1204));
        assertTrue(StringDataLetterUtil.isLetter((char)1204));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1204));
    }
    @Test
    public void d505(){
        assertEq(2,StringDataUtil.getType((char)1205));
        assertEq(0,StringDataUtil.getDirectionality((char)1205));
        assertTrue(StringDataLetterUtil.isLetter((char)1205));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1205));
    }
    @Test
    public void d506(){
        assertEq(1,StringDataUtil.getType((char)1206));
        assertEq(0,StringDataUtil.getDirectionality((char)1206));
        assertTrue(StringDataLetterUtil.isLetter((char)1206));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1206));
    }
    @Test
    public void d507(){
        assertEq(2,StringDataUtil.getType((char)1207));
        assertEq(0,StringDataUtil.getDirectionality((char)1207));
        assertTrue(StringDataLetterUtil.isLetter((char)1207));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1207));
    }
    @Test
    public void d508(){
        assertEq(1,StringDataUtil.getType((char)1208));
        assertEq(0,StringDataUtil.getDirectionality((char)1208));
        assertTrue(StringDataLetterUtil.isLetter((char)1208));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1208));
    }
    @Test
    public void d509(){
        assertEq(2,StringDataUtil.getType((char)1209));
        assertEq(0,StringDataUtil.getDirectionality((char)1209));
        assertTrue(StringDataLetterUtil.isLetter((char)1209));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1209));
    }
    @Test
    public void d510(){
        assertEq(1,StringDataUtil.getType((char)1210));
        assertEq(0,StringDataUtil.getDirectionality((char)1210));
        assertTrue(StringDataLetterUtil.isLetter((char)1210));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1210));
    }
    @Test
    public void d511(){
        assertEq(2,StringDataUtil.getType((char)1211));
        assertEq(0,StringDataUtil.getDirectionality((char)1211));
        assertTrue(StringDataLetterUtil.isLetter((char)1211));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1211));
    }
    @Test
    public void d512(){
        assertEq(1,StringDataUtil.getType((char)1212));
        assertEq(0,StringDataUtil.getDirectionality((char)1212));
        assertTrue(StringDataLetterUtil.isLetter((char)1212));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1212));
    }
    @Test
    public void d513(){
        assertEq(2,StringDataUtil.getType((char)1213));
        assertEq(0,StringDataUtil.getDirectionality((char)1213));
        assertTrue(StringDataLetterUtil.isLetter((char)1213));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1213));
    }
    @Test
    public void d514(){
        assertEq(1,StringDataUtil.getType((char)1214));
        assertEq(0,StringDataUtil.getDirectionality((char)1214));
        assertTrue(StringDataLetterUtil.isLetter((char)1214));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1214));
    }
    @Test
    public void d515(){
        assertEq(2,StringDataUtil.getType((char)1215));
        assertEq(0,StringDataUtil.getDirectionality((char)1215));
        assertTrue(StringDataLetterUtil.isLetter((char)1215));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1215));
    }
    @Test
    public void d516(){
        assertEq(1,StringDataUtil.getType((char)1216));
        assertEq(0,StringDataUtil.getDirectionality((char)1216));
        assertTrue(StringDataLetterUtil.isLetter((char)1216));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1216));
    }
    @Test
    public void d517(){
        assertEq(2,StringDataUtil.getType((char)1218));
        assertEq(0,StringDataUtil.getDirectionality((char)1218));
        assertTrue(StringDataLetterUtil.isLetter((char)1218));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1218));
    }
    @Test
    public void d518(){
        assertEq(1,StringDataUtil.getType((char)1219));
        assertEq(0,StringDataUtil.getDirectionality((char)1219));
        assertTrue(StringDataLetterUtil.isLetter((char)1219));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1219));
    }
    @Test
    public void d519(){
        assertEq(2,StringDataUtil.getType((char)1220));
        assertEq(0,StringDataUtil.getDirectionality((char)1220));
        assertTrue(StringDataLetterUtil.isLetter((char)1220));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1220));
    }
    @Test
    public void d520(){
        assertEq(1,StringDataUtil.getType((char)1221));
        assertEq(0,StringDataUtil.getDirectionality((char)1221));
        assertTrue(StringDataLetterUtil.isLetter((char)1221));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1221));
    }
    @Test
    public void d521(){
        assertEq(2,StringDataUtil.getType((char)1222));
        assertEq(0,StringDataUtil.getDirectionality((char)1222));
        assertTrue(StringDataLetterUtil.isLetter((char)1222));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1222));
    }
    @Test
    public void d522(){
        assertEq(1,StringDataUtil.getType((char)1223));
        assertEq(0,StringDataUtil.getDirectionality((char)1223));
        assertTrue(StringDataLetterUtil.isLetter((char)1223));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1223));
    }
    @Test
    public void d523(){
        assertEq(2,StringDataUtil.getType((char)1224));
        assertEq(0,StringDataUtil.getDirectionality((char)1224));
        assertTrue(StringDataLetterUtil.isLetter((char)1224));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1224));
    }
    @Test
    public void d524(){
        assertEq(1,StringDataUtil.getType((char)1225));
        assertEq(0,StringDataUtil.getDirectionality((char)1225));
        assertTrue(StringDataLetterUtil.isLetter((char)1225));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1225));
    }
    @Test
    public void d525(){
        assertEq(2,StringDataUtil.getType((char)1226));
        assertEq(0,StringDataUtil.getDirectionality((char)1226));
        assertTrue(StringDataLetterUtil.isLetter((char)1226));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1226));
    }
    @Test
    public void d526(){
        assertEq(1,StringDataUtil.getType((char)1227));
        assertEq(0,StringDataUtil.getDirectionality((char)1227));
        assertTrue(StringDataLetterUtil.isLetter((char)1227));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1227));
    }
    @Test
    public void d527(){
        assertEq(2,StringDataUtil.getType((char)1228));
        assertEq(0,StringDataUtil.getDirectionality((char)1228));
        assertTrue(StringDataLetterUtil.isLetter((char)1228));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1228));
    }
    @Test
    public void d528(){
        assertEq(1,StringDataUtil.getType((char)1229));
        assertEq(0,StringDataUtil.getDirectionality((char)1229));
        assertTrue(StringDataLetterUtil.isLetter((char)1229));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1229));
    }
    @Test
    public void d529(){
        assertEq(2,StringDataUtil.getType((char)1230));
        assertEq(0,StringDataUtil.getDirectionality((char)1230));
        assertTrue(StringDataLetterUtil.isLetter((char)1230));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1230));
    }
    @Test
    public void d530(){
        assertEq(1,StringDataUtil.getType((char)1232));
        assertEq(0,StringDataUtil.getDirectionality((char)1232));
        assertTrue(StringDataLetterUtil.isLetter((char)1232));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1232));
    }
    @Test
    public void d531(){
        assertEq(2,StringDataUtil.getType((char)1233));
        assertEq(0,StringDataUtil.getDirectionality((char)1233));
        assertTrue(StringDataLetterUtil.isLetter((char)1233));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1233));
    }
    @Test
    public void d532(){
        assertEq(1,StringDataUtil.getType((char)1234));
        assertEq(0,StringDataUtil.getDirectionality((char)1234));
        assertTrue(StringDataLetterUtil.isLetter((char)1234));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1234));
    }
    @Test
    public void d533(){
        assertEq(2,StringDataUtil.getType((char)1235));
        assertEq(0,StringDataUtil.getDirectionality((char)1235));
        assertTrue(StringDataLetterUtil.isLetter((char)1235));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1235));
    }
    @Test
    public void d534(){
        assertEq(1,StringDataUtil.getType((char)1236));
        assertEq(0,StringDataUtil.getDirectionality((char)1236));
        assertTrue(StringDataLetterUtil.isLetter((char)1236));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1236));
    }
    @Test
    public void d535(){
        assertEq(2,StringDataUtil.getType((char)1237));
        assertEq(0,StringDataUtil.getDirectionality((char)1237));
        assertTrue(StringDataLetterUtil.isLetter((char)1237));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1237));
    }
    @Test
    public void d536(){
        assertEq(1,StringDataUtil.getType((char)1238));
        assertEq(0,StringDataUtil.getDirectionality((char)1238));
        assertTrue(StringDataLetterUtil.isLetter((char)1238));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1238));
    }
    @Test
    public void d537(){
        assertEq(2,StringDataUtil.getType((char)1239));
        assertEq(0,StringDataUtil.getDirectionality((char)1239));
        assertTrue(StringDataLetterUtil.isLetter((char)1239));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1239));
    }
    @Test
    public void d538(){
        assertEq(1,StringDataUtil.getType((char)1240));
        assertEq(0,StringDataUtil.getDirectionality((char)1240));
        assertTrue(StringDataLetterUtil.isLetter((char)1240));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1240));
    }
    @Test
    public void d539(){
        assertEq(2,StringDataUtil.getType((char)1241));
        assertEq(0,StringDataUtil.getDirectionality((char)1241));
        assertTrue(StringDataLetterUtil.isLetter((char)1241));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1241));
    }
    @Test
    public void d540(){
        assertEq(1,StringDataUtil.getType((char)1242));
        assertEq(0,StringDataUtil.getDirectionality((char)1242));
        assertTrue(StringDataLetterUtil.isLetter((char)1242));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1242));
    }
    @Test
    public void d541(){
        assertEq(2,StringDataUtil.getType((char)1243));
        assertEq(0,StringDataUtil.getDirectionality((char)1243));
        assertTrue(StringDataLetterUtil.isLetter((char)1243));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1243));
    }
    @Test
    public void d542(){
        assertEq(1,StringDataUtil.getType((char)1244));
        assertEq(0,StringDataUtil.getDirectionality((char)1244));
        assertTrue(StringDataLetterUtil.isLetter((char)1244));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1244));
    }
    @Test
    public void d543(){
        assertEq(2,StringDataUtil.getType((char)1245));
        assertEq(0,StringDataUtil.getDirectionality((char)1245));
        assertTrue(StringDataLetterUtil.isLetter((char)1245));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1245));
    }
    @Test
    public void d544(){
        assertEq(1,StringDataUtil.getType((char)1246));
        assertEq(0,StringDataUtil.getDirectionality((char)1246));
        assertTrue(StringDataLetterUtil.isLetter((char)1246));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1246));
    }
    @Test
    public void d545(){
        assertEq(2,StringDataUtil.getType((char)1247));
        assertEq(0,StringDataUtil.getDirectionality((char)1247));
        assertTrue(StringDataLetterUtil.isLetter((char)1247));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1247));
    }
    @Test
    public void d546(){
        assertEq(1,StringDataUtil.getType((char)1248));
        assertEq(0,StringDataUtil.getDirectionality((char)1248));
        assertTrue(StringDataLetterUtil.isLetter((char)1248));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1248));
    }
    @Test
    public void d547(){
        assertEq(2,StringDataUtil.getType((char)1249));
        assertEq(0,StringDataUtil.getDirectionality((char)1249));
        assertTrue(StringDataLetterUtil.isLetter((char)1249));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1249));
    }
    @Test
    public void d548(){
        assertEq(1,StringDataUtil.getType((char)1250));
        assertEq(0,StringDataUtil.getDirectionality((char)1250));
        assertTrue(StringDataLetterUtil.isLetter((char)1250));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1250));
    }
    @Test
    public void d549(){
        assertEq(2,StringDataUtil.getType((char)1251));
        assertEq(0,StringDataUtil.getDirectionality((char)1251));
        assertTrue(StringDataLetterUtil.isLetter((char)1251));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1251));
    }
    @Test
    public void d550(){
        assertEq(1,StringDataUtil.getType((char)1252));
        assertEq(0,StringDataUtil.getDirectionality((char)1252));
        assertTrue(StringDataLetterUtil.isLetter((char)1252));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1252));
    }
    @Test
    public void d551(){
        assertEq(2,StringDataUtil.getType((char)1253));
        assertEq(0,StringDataUtil.getDirectionality((char)1253));
        assertTrue(StringDataLetterUtil.isLetter((char)1253));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1253));
    }
    @Test
    public void d552(){
        assertEq(1,StringDataUtil.getType((char)1254));
        assertEq(0,StringDataUtil.getDirectionality((char)1254));
        assertTrue(StringDataLetterUtil.isLetter((char)1254));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1254));
    }
    @Test
    public void d553(){
        assertEq(2,StringDataUtil.getType((char)1255));
        assertEq(0,StringDataUtil.getDirectionality((char)1255));
        assertTrue(StringDataLetterUtil.isLetter((char)1255));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1255));
    }
    @Test
    public void d554(){
        assertEq(1,StringDataUtil.getType((char)1256));
        assertEq(0,StringDataUtil.getDirectionality((char)1256));
        assertTrue(StringDataLetterUtil.isLetter((char)1256));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1256));
    }
    @Test
    public void d555(){
        assertEq(2,StringDataUtil.getType((char)1257));
        assertEq(0,StringDataUtil.getDirectionality((char)1257));
        assertTrue(StringDataLetterUtil.isLetter((char)1257));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1257));
    }
    @Test
    public void d556(){
        assertEq(1,StringDataUtil.getType((char)1258));
        assertEq(0,StringDataUtil.getDirectionality((char)1258));
        assertTrue(StringDataLetterUtil.isLetter((char)1258));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1258));
    }
    @Test
    public void d557(){
        assertEq(2,StringDataUtil.getType((char)1259));
        assertEq(0,StringDataUtil.getDirectionality((char)1259));
        assertTrue(StringDataLetterUtil.isLetter((char)1259));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1259));
    }
    @Test
    public void d558(){
        assertEq(1,StringDataUtil.getType((char)1260));
        assertEq(0,StringDataUtil.getDirectionality((char)1260));
        assertTrue(StringDataLetterUtil.isLetter((char)1260));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1260));
    }
    @Test
    public void d559(){
        assertEq(2,StringDataUtil.getType((char)1261));
        assertEq(0,StringDataUtil.getDirectionality((char)1261));
        assertTrue(StringDataLetterUtil.isLetter((char)1261));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1261));
    }
    @Test
    public void d560(){
        assertEq(1,StringDataUtil.getType((char)1262));
        assertEq(0,StringDataUtil.getDirectionality((char)1262));
        assertTrue(StringDataLetterUtil.isLetter((char)1262));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1262));
    }
    @Test
    public void d561(){
        assertEq(2,StringDataUtil.getType((char)1263));
        assertEq(0,StringDataUtil.getDirectionality((char)1263));
        assertTrue(StringDataLetterUtil.isLetter((char)1263));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1263));
    }
    @Test
    public void d562(){
        assertEq(1,StringDataUtil.getType((char)1264));
        assertEq(0,StringDataUtil.getDirectionality((char)1264));
        assertTrue(StringDataLetterUtil.isLetter((char)1264));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1264));
    }
    @Test
    public void d563(){
        assertEq(2,StringDataUtil.getType((char)1265));
        assertEq(0,StringDataUtil.getDirectionality((char)1265));
        assertTrue(StringDataLetterUtil.isLetter((char)1265));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1265));
    }
    @Test
    public void d564(){
        assertEq(1,StringDataUtil.getType((char)1266));
        assertEq(0,StringDataUtil.getDirectionality((char)1266));
        assertTrue(StringDataLetterUtil.isLetter((char)1266));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1266));
    }
    @Test
    public void d565(){
        assertEq(2,StringDataUtil.getType((char)1267));
        assertEq(0,StringDataUtil.getDirectionality((char)1267));
        assertTrue(StringDataLetterUtil.isLetter((char)1267));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1267));
    }
    @Test
    public void d566(){
        assertEq(1,StringDataUtil.getType((char)1268));
        assertEq(0,StringDataUtil.getDirectionality((char)1268));
        assertTrue(StringDataLetterUtil.isLetter((char)1268));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1268));
    }
    @Test
    public void d567(){
        assertEq(2,StringDataUtil.getType((char)1269));
        assertEq(0,StringDataUtil.getDirectionality((char)1269));
        assertTrue(StringDataLetterUtil.isLetter((char)1269));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1269));
    }
    @Test
    public void d568(){
        assertEq(1,StringDataUtil.getType((char)1270));
        assertEq(0,StringDataUtil.getDirectionality((char)1270));
        assertTrue(StringDataLetterUtil.isLetter((char)1270));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1270));
    }
    @Test
    public void d569(){
        assertEq(2,StringDataUtil.getType((char)1271));
        assertEq(0,StringDataUtil.getDirectionality((char)1271));
        assertTrue(StringDataLetterUtil.isLetter((char)1271));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1271));
    }
    @Test
    public void d570(){
        assertEq(1,StringDataUtil.getType((char)1272));
        assertEq(0,StringDataUtil.getDirectionality((char)1272));
        assertTrue(StringDataLetterUtil.isLetter((char)1272));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1272));
    }
    @Test
    public void d571(){
        assertEq(2,StringDataUtil.getType((char)1273));
        assertEq(0,StringDataUtil.getDirectionality((char)1273));
        assertTrue(StringDataLetterUtil.isLetter((char)1273));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1273));
    }
    @Test
    public void d572(){
        assertEq(1,StringDataUtil.getType((char)1274));
        assertEq(0,StringDataUtil.getDirectionality((char)1274));
        assertTrue(StringDataLetterUtil.isLetter((char)1274));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1274));
    }
    @Test
    public void d573(){
        assertEq(2,StringDataUtil.getType((char)1275));
        assertEq(0,StringDataUtil.getDirectionality((char)1275));
        assertTrue(StringDataLetterUtil.isLetter((char)1275));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1275));
    }
    @Test
    public void d574(){
        assertEq(1,StringDataUtil.getType((char)1276));
        assertEq(0,StringDataUtil.getDirectionality((char)1276));
        assertTrue(StringDataLetterUtil.isLetter((char)1276));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1276));
    }
    @Test
    public void d575(){
        assertEq(2,StringDataUtil.getType((char)1277));
        assertEq(0,StringDataUtil.getDirectionality((char)1277));
        assertTrue(StringDataLetterUtil.isLetter((char)1277));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1277));
    }
    @Test
    public void d576(){
        assertEq(1,StringDataUtil.getType((char)1278));
        assertEq(0,StringDataUtil.getDirectionality((char)1278));
        assertTrue(StringDataLetterUtil.isLetter((char)1278));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1278));
    }
    @Test
    public void d577(){
        assertEq(2,StringDataUtil.getType((char)1279));
        assertEq(0,StringDataUtil.getDirectionality((char)1279));
        assertTrue(StringDataLetterUtil.isLetter((char)1279));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1279));
    }
    @Test
    public void d578(){
        assertEq(1,StringDataUtil.getType((char)1280));
        assertEq(0,StringDataUtil.getDirectionality((char)1280));
        assertTrue(StringDataLetterUtil.isLetter((char)1280));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1280));
    }
    @Test
    public void d579(){
        assertEq(2,StringDataUtil.getType((char)1281));
        assertEq(0,StringDataUtil.getDirectionality((char)1281));
        assertTrue(StringDataLetterUtil.isLetter((char)1281));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1281));
    }
    @Test
    public void d580(){
        assertEq(1,StringDataUtil.getType((char)1282));
        assertEq(0,StringDataUtil.getDirectionality((char)1282));
        assertTrue(StringDataLetterUtil.isLetter((char)1282));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1282));
    }
    @Test
    public void d581(){
        assertEq(2,StringDataUtil.getType((char)1283));
        assertEq(0,StringDataUtil.getDirectionality((char)1283));
        assertTrue(StringDataLetterUtil.isLetter((char)1283));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1283));
    }
    @Test
    public void d582(){
        assertEq(1,StringDataUtil.getType((char)1284));
        assertEq(0,StringDataUtil.getDirectionality((char)1284));
        assertTrue(StringDataLetterUtil.isLetter((char)1284));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1284));
    }
    @Test
    public void d583(){
        assertEq(2,StringDataUtil.getType((char)1285));
        assertEq(0,StringDataUtil.getDirectionality((char)1285));
        assertTrue(StringDataLetterUtil.isLetter((char)1285));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1285));
    }
    @Test
    public void d584(){
        assertEq(1,StringDataUtil.getType((char)1286));
        assertEq(0,StringDataUtil.getDirectionality((char)1286));
        assertTrue(StringDataLetterUtil.isLetter((char)1286));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1286));
    }
    @Test
    public void d585(){
        assertEq(2,StringDataUtil.getType((char)1287));
        assertEq(0,StringDataUtil.getDirectionality((char)1287));
        assertTrue(StringDataLetterUtil.isLetter((char)1287));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1287));
    }
    @Test
    public void d586(){
        assertEq(1,StringDataUtil.getType((char)1288));
        assertEq(0,StringDataUtil.getDirectionality((char)1288));
        assertTrue(StringDataLetterUtil.isLetter((char)1288));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1288));
    }
    @Test
    public void d587(){
        assertEq(2,StringDataUtil.getType((char)1289));
        assertEq(0,StringDataUtil.getDirectionality((char)1289));
        assertTrue(StringDataLetterUtil.isLetter((char)1289));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1289));
    }
    @Test
    public void d588(){
        assertEq(1,StringDataUtil.getType((char)1290));
        assertEq(0,StringDataUtil.getDirectionality((char)1290));
        assertTrue(StringDataLetterUtil.isLetter((char)1290));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1290));
    }
    @Test
    public void d589(){
        assertEq(2,StringDataUtil.getType((char)1291));
        assertEq(0,StringDataUtil.getDirectionality((char)1291));
        assertTrue(StringDataLetterUtil.isLetter((char)1291));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1291));
    }
    @Test
    public void d590(){
        assertEq(1,StringDataUtil.getType((char)1292));
        assertEq(0,StringDataUtil.getDirectionality((char)1292));
        assertTrue(StringDataLetterUtil.isLetter((char)1292));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1292));
    }
    @Test
    public void d591(){
        assertEq(2,StringDataUtil.getType((char)1293));
        assertEq(0,StringDataUtil.getDirectionality((char)1293));
        assertTrue(StringDataLetterUtil.isLetter((char)1293));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1293));
    }
    @Test
    public void d592(){
        assertEq(1,StringDataUtil.getType((char)1294));
        assertEq(0,StringDataUtil.getDirectionality((char)1294));
        assertTrue(StringDataLetterUtil.isLetter((char)1294));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1294));
    }
    @Test
    public void d593(){
        assertEq(2,StringDataUtil.getType((char)1295));
        assertEq(0,StringDataUtil.getDirectionality((char)1295));
        assertTrue(StringDataLetterUtil.isLetter((char)1295));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1295));
    }
    @Test
    public void d594(){
        assertEq(1,StringDataUtil.getType((char)1296));
        assertEq(0,StringDataUtil.getDirectionality((char)1296));
        assertTrue(StringDataLetterUtil.isLetter((char)1296));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1296));
    }
    @Test
    public void d595(){
        assertEq(2,StringDataUtil.getType((char)1297));
        assertEq(0,StringDataUtil.getDirectionality((char)1297));
        assertTrue(StringDataLetterUtil.isLetter((char)1297));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1297));
    }
    @Test
    public void d596(){
        assertEq(1,StringDataUtil.getType((char)1298));
        assertEq(0,StringDataUtil.getDirectionality((char)1298));
        assertTrue(StringDataLetterUtil.isLetter((char)1298));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1298));
    }
    @Test
    public void d597(){
        assertEq(2,StringDataUtil.getType((char)1299));
        assertEq(0,StringDataUtil.getDirectionality((char)1299));
        assertTrue(StringDataLetterUtil.isLetter((char)1299));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1299));
    }
    @Test
    public void d598(){
        assertEq(1,StringDataUtil.getType((char)1300));
        assertEq(0,StringDataUtil.getDirectionality((char)1300));
        assertTrue(StringDataLetterUtil.isLetter((char)1300));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1300));
    }
    @Test
    public void d599(){
        assertEq(2,StringDataUtil.getType((char)1301));
        assertEq(0,StringDataUtil.getDirectionality((char)1301));
        assertTrue(StringDataLetterUtil.isLetter((char)1301));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1301));
    }
    @Test
    public void d600(){
        assertEq(1,StringDataUtil.getType((char)1302));
        assertEq(0,StringDataUtil.getDirectionality((char)1302));
        assertTrue(StringDataLetterUtil.isLetter((char)1302));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1302));
    }
    @Test
    public void d601(){
        assertEq(2,StringDataUtil.getType((char)1303));
        assertEq(0,StringDataUtil.getDirectionality((char)1303));
        assertTrue(StringDataLetterUtil.isLetter((char)1303));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1303));
    }
    @Test
    public void d602(){
        assertEq(1,StringDataUtil.getType((char)1304));
        assertEq(0,StringDataUtil.getDirectionality((char)1304));
        assertTrue(StringDataLetterUtil.isLetter((char)1304));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1304));
    }
    @Test
    public void d603(){
        assertEq(2,StringDataUtil.getType((char)1305));
        assertEq(0,StringDataUtil.getDirectionality((char)1305));
        assertTrue(StringDataLetterUtil.isLetter((char)1305));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1305));
    }
    @Test
    public void d604(){
        assertEq(1,StringDataUtil.getType((char)1306));
        assertEq(0,StringDataUtil.getDirectionality((char)1306));
        assertTrue(StringDataLetterUtil.isLetter((char)1306));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1306));
    }
    @Test
    public void d605(){
        assertEq(2,StringDataUtil.getType((char)1307));
        assertEq(0,StringDataUtil.getDirectionality((char)1307));
        assertTrue(StringDataLetterUtil.isLetter((char)1307));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1307));
    }
    @Test
    public void d606(){
        assertEq(1,StringDataUtil.getType((char)1308));
        assertEq(0,StringDataUtil.getDirectionality((char)1308));
        assertTrue(StringDataLetterUtil.isLetter((char)1308));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1308));
    }
    @Test
    public void d607(){
        assertEq(2,StringDataUtil.getType((char)1309));
        assertEq(0,StringDataUtil.getDirectionality((char)1309));
        assertTrue(StringDataLetterUtil.isLetter((char)1309));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1309));
    }
    @Test
    public void d608(){
        assertEq(1,StringDataUtil.getType((char)1310));
        assertEq(0,StringDataUtil.getDirectionality((char)1310));
        assertTrue(StringDataLetterUtil.isLetter((char)1310));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1310));
    }
    @Test
    public void d609(){
        assertEq(2,StringDataUtil.getType((char)1311));
        assertEq(0,StringDataUtil.getDirectionality((char)1311));
        assertTrue(StringDataLetterUtil.isLetter((char)1311));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1311));
    }
    @Test
    public void d610(){
        assertEq(1,StringDataUtil.getType((char)1312));
        assertEq(0,StringDataUtil.getDirectionality((char)1312));
        assertTrue(StringDataLetterUtil.isLetter((char)1312));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1312));
    }
    @Test
    public void d611(){
        assertEq(2,StringDataUtil.getType((char)1313));
        assertEq(0,StringDataUtil.getDirectionality((char)1313));
        assertTrue(StringDataLetterUtil.isLetter((char)1313));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1313));
    }
    @Test
    public void d612(){
        assertEq(1,StringDataUtil.getType((char)1314));
        assertEq(0,StringDataUtil.getDirectionality((char)1314));
        assertTrue(StringDataLetterUtil.isLetter((char)1314));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1314));
    }
    @Test
    public void d613(){
        assertEq(2,StringDataUtil.getType((char)1315));
        assertEq(0,StringDataUtil.getDirectionality((char)1315));
        assertTrue(StringDataLetterUtil.isLetter((char)1315));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1315));
    }
    @Test
    public void d614(){
        assertEq(1,StringDataUtil.getType((char)1316));
        assertEq(0,StringDataUtil.getDirectionality((char)1316));
        assertTrue(StringDataLetterUtil.isLetter((char)1316));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1316));
    }
    @Test
    public void d615(){
        assertEq(2,StringDataUtil.getType((char)1317));
        assertEq(0,StringDataUtil.getDirectionality((char)1317));
        assertTrue(StringDataLetterUtil.isLetter((char)1317));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1317));
    }
    @Test
    public void d616(){
        assertEq(1,StringDataUtil.getType((char)1318));
        assertEq(0,StringDataUtil.getDirectionality((char)1318));
        assertTrue(StringDataLetterUtil.isLetter((char)1318));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1318));
    }
    @Test
    public void d617(){
        assertEq(2,StringDataUtil.getType((char)1319));
        assertEq(0,StringDataUtil.getDirectionality((char)1319));
        assertTrue(StringDataLetterUtil.isLetter((char)1319));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1319));
    }
    @Test
    public void d618(){
        assertEq(0,StringDataUtil.getType((char)1320));
        assertEq(-1,StringDataUtil.getDirectionality((char)1320));
        assertFalse(StringDataLetterUtil.isLetter((char)1320));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1320));
    }
    @Test
    public void d619(){
        assertEq(1,StringDataUtil.getType((char)1329));
        assertEq(0,StringDataUtil.getDirectionality((char)1329));
        assertTrue(StringDataLetterUtil.isLetter((char)1329));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1329));
    }
    @Test
    public void d620(){
        assertEq(0,StringDataUtil.getType((char)1367));
        assertEq(-1,StringDataUtil.getDirectionality((char)1367));
        assertFalse(StringDataLetterUtil.isLetter((char)1367));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1367));
    }
    @Test
    public void d621(){
        assertEq(4,StringDataUtil.getType((char)1369));
        assertEq(0,StringDataUtil.getDirectionality((char)1369));
        assertTrue(StringDataLetterUtil.isLetter((char)1369));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1369));
    }
    @Test
    public void d622(){
        assertEq(24,StringDataUtil.getType((char)1370));
        assertEq(0,StringDataUtil.getDirectionality((char)1370));
        assertFalse(StringDataLetterUtil.isLetter((char)1370));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1370));
    }
    @Test
    public void d623(){
        assertEq(0,StringDataUtil.getType((char)1376));
        assertEq(-1,StringDataUtil.getDirectionality((char)1376));
        assertFalse(StringDataLetterUtil.isLetter((char)1376));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1376));
    }
    @Test
    public void d624(){
        assertEq(2,StringDataUtil.getType((char)1377));
        assertEq(0,StringDataUtil.getDirectionality((char)1377));
        assertTrue(StringDataLetterUtil.isLetter((char)1377));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1377));
    }
    @Test
    public void d625(){
        assertEq(0,StringDataUtil.getType((char)1416));
        assertEq(-1,StringDataUtil.getDirectionality((char)1416));
        assertFalse(StringDataLetterUtil.isLetter((char)1416));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1416));
    }
    @Test
    public void d626(){
        assertEq(24,StringDataUtil.getType((char)1417));
        assertEq(0,StringDataUtil.getDirectionality((char)1417));
        assertFalse(StringDataLetterUtil.isLetter((char)1417));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1417));
    }
    @Test
    public void d627(){
        assertEq(20,StringDataUtil.getType((char)1418));
        assertEq(13,StringDataUtil.getDirectionality((char)1418));
        assertFalse(StringDataLetterUtil.isLetter((char)1418));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1418));
    }
    @Test
    public void d628(){
        assertEq(0,StringDataUtil.getType((char)1419));
        assertEq(-1,StringDataUtil.getDirectionality((char)1419));
        assertFalse(StringDataLetterUtil.isLetter((char)1419));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1419));
    }
    @Test
    public void d629(){
        assertEq(26,StringDataUtil.getType((char)1423));
        assertEq(5,StringDataUtil.getDirectionality((char)1423));
        assertFalse(StringDataLetterUtil.isLetter((char)1423));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1423));
    }
    @Test
    public void d630(){
        assertEq(0,StringDataUtil.getType((char)1424));
        assertEq(-1,StringDataUtil.getDirectionality((char)1424));
        assertFalse(StringDataLetterUtil.isLetter((char)1424));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1424));
    }
    @Test
    public void d631(){
        assertEq(6,StringDataUtil.getType((char)1425));
        assertEq(8,StringDataUtil.getDirectionality((char)1425));
        assertFalse(StringDataLetterUtil.isLetter((char)1425));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1425));
    }
    @Test
    public void d632(){
        assertEq(20,StringDataUtil.getType((char)1470));
        assertEq(1,StringDataUtil.getDirectionality((char)1470));
        assertFalse(StringDataLetterUtil.isLetter((char)1470));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1470));
    }
    @Test
    public void d633(){
        assertEq(6,StringDataUtil.getType((char)1471));
        assertEq(8,StringDataUtil.getDirectionality((char)1471));
        assertFalse(StringDataLetterUtil.isLetter((char)1471));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1471));
    }
    @Test
    public void d634(){
        assertEq(24,StringDataUtil.getType((char)1472));
        assertEq(1,StringDataUtil.getDirectionality((char)1472));
        assertFalse(StringDataLetterUtil.isLetter((char)1472));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1472));
    }
    @Test
    public void d635(){
        assertEq(6,StringDataUtil.getType((char)1473));
        assertEq(8,StringDataUtil.getDirectionality((char)1473));
        assertFalse(StringDataLetterUtil.isLetter((char)1473));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1473));
    }
    @Test
    public void d636(){
        assertEq(24,StringDataUtil.getType((char)1475));
        assertEq(1,StringDataUtil.getDirectionality((char)1475));
        assertFalse(StringDataLetterUtil.isLetter((char)1475));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1475));
    }
    @Test
    public void d637(){
        assertEq(6,StringDataUtil.getType((char)1476));
        assertEq(8,StringDataUtil.getDirectionality((char)1476));
        assertFalse(StringDataLetterUtil.isLetter((char)1476));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1476));
    }
    @Test
    public void d638(){
        assertEq(24,StringDataUtil.getType((char)1478));
        assertEq(1,StringDataUtil.getDirectionality((char)1478));
        assertFalse(StringDataLetterUtil.isLetter((char)1478));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1478));
    }
    @Test
    public void d639(){
        assertEq(6,StringDataUtil.getType((char)1479));
        assertEq(8,StringDataUtil.getDirectionality((char)1479));
        assertFalse(StringDataLetterUtil.isLetter((char)1479));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1479));
    }
    @Test
    public void d640(){
        assertEq(0,StringDataUtil.getType((char)1480));
        assertEq(-1,StringDataUtil.getDirectionality((char)1480));
        assertFalse(StringDataLetterUtil.isLetter((char)1480));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1480));
    }
    @Test
    public void d641(){
        assertEq(5,StringDataUtil.getType((char)1488));
        assertEq(1,StringDataUtil.getDirectionality((char)1488));
        assertTrue(StringDataLetterUtil.isLetter((char)1488));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1488));
    }
    @Test
    public void d642(){
        assertEq(0,StringDataUtil.getType((char)1515));
        assertEq(-1,StringDataUtil.getDirectionality((char)1515));
        assertFalse(StringDataLetterUtil.isLetter((char)1515));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1515));
    }
    @Test
    public void d643(){
        assertEq(5,StringDataUtil.getType((char)1520));
        assertEq(1,StringDataUtil.getDirectionality((char)1520));
        assertTrue(StringDataLetterUtil.isLetter((char)1520));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1520));
    }
    @Test
    public void d644(){
        assertEq(24,StringDataUtil.getType((char)1523));
        assertEq(1,StringDataUtil.getDirectionality((char)1523));
        assertFalse(StringDataLetterUtil.isLetter((char)1523));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1523));
    }
    @Test
    public void d645(){
        assertEq(0,StringDataUtil.getType((char)1525));
        assertEq(-1,StringDataUtil.getDirectionality((char)1525));
        assertFalse(StringDataLetterUtil.isLetter((char)1525));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1525));
    }
    @Test
    public void d646(){
        assertEq(16,StringDataUtil.getType((char)1536));
        assertEq(6,StringDataUtil.getDirectionality((char)1536));
        assertFalse(StringDataLetterUtil.isLetter((char)1536));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1536));
    }
    @Test
    public void d647(){
        assertEq(0,StringDataUtil.getType((char)1541));
        assertEq(-1,StringDataUtil.getDirectionality((char)1541));
        assertFalse(StringDataLetterUtil.isLetter((char)1541));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1541));
    }
    @Test
    public void d648(){
        assertEq(25,StringDataUtil.getType((char)1542));
        assertEq(13,StringDataUtil.getDirectionality((char)1542));
        assertFalse(StringDataLetterUtil.isLetter((char)1542));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1542));
    }
    @Test
    public void d649(){
        assertEq(24,StringDataUtil.getType((char)1545));
        assertEq(5,StringDataUtil.getDirectionality((char)1545));
        assertFalse(StringDataLetterUtil.isLetter((char)1545));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1545));
    }
    @Test
    public void d650(){
        assertEq(26,StringDataUtil.getType((char)1547));
        assertEq(2,StringDataUtil.getDirectionality((char)1547));
        assertFalse(StringDataLetterUtil.isLetter((char)1547));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1547));
    }
    @Test
    public void d651(){
        assertEq(24,StringDataUtil.getType((char)1548));
        assertEq(7,StringDataUtil.getDirectionality((char)1548));
        assertFalse(StringDataLetterUtil.isLetter((char)1548));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1548));
    }
    @Test
    public void d652(){
        assertEq(28,StringDataUtil.getType((char)1550));
        assertEq(13,StringDataUtil.getDirectionality((char)1550));
        assertFalse(StringDataLetterUtil.isLetter((char)1550));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1550));
    }
    @Test
    public void d653(){
        assertEq(6,StringDataUtil.getType((char)1552));
        assertEq(8,StringDataUtil.getDirectionality((char)1552));
        assertFalse(StringDataLetterUtil.isLetter((char)1552));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1552));
    }
    @Test
    public void d654(){
        assertEq(24,StringDataUtil.getType((char)1563));
        assertEq(2,StringDataUtil.getDirectionality((char)1563));
        assertFalse(StringDataLetterUtil.isLetter((char)1563));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1563));
    }
    @Test
    public void d655(){
        assertEq(0,StringDataUtil.getType((char)1564));
        assertEq(-1,StringDataUtil.getDirectionality((char)1564));
        assertFalse(StringDataLetterUtil.isLetter((char)1564));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1564));
    }
    @Test
    public void d656(){
        assertEq(24,StringDataUtil.getType((char)1566));
        assertEq(2,StringDataUtil.getDirectionality((char)1566));
        assertFalse(StringDataLetterUtil.isLetter((char)1566));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1566));
    }
    @Test
    public void d657(){
        assertEq(5,StringDataUtil.getType((char)1568));
        assertEq(2,StringDataUtil.getDirectionality((char)1568));
        assertTrue(StringDataLetterUtil.isLetter((char)1568));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1568));
    }
    @Test
    public void d658(){
        assertEq(4,StringDataUtil.getType((char)1600));
        assertEq(2,StringDataUtil.getDirectionality((char)1600));
        assertTrue(StringDataLetterUtil.isLetter((char)1600));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1600));
    }
    @Test
    public void d659(){
        assertEq(5,StringDataUtil.getType((char)1601));
        assertEq(2,StringDataUtil.getDirectionality((char)1601));
        assertTrue(StringDataLetterUtil.isLetter((char)1601));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1601));
    }
    @Test
    public void d660(){
        assertEq(6,StringDataUtil.getType((char)1611));
        assertEq(8,StringDataUtil.getDirectionality((char)1611));
        assertFalse(StringDataLetterUtil.isLetter((char)1611));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1611));
    }
    @Test
    public void d661(){
        assertEq(9,StringDataUtil.getType((char)1632));
        assertEq(6,StringDataUtil.getDirectionality((char)1632));
        assertFalse(StringDataLetterUtil.isLetter((char)1632));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1632));
    }
    @Test
    public void d662(){
        assertEq(24,StringDataUtil.getType((char)1642));
        assertEq(5,StringDataUtil.getDirectionality((char)1642));
        assertFalse(StringDataLetterUtil.isLetter((char)1642));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1642));
    }
    @Test
    public void d663(){
        assertEq(5,StringDataUtil.getType((char)1646));
        assertEq(2,StringDataUtil.getDirectionality((char)1646));
        assertTrue(StringDataLetterUtil.isLetter((char)1646));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1646));
    }
    @Test
    public void d664(){
        assertEq(6,StringDataUtil.getType((char)1648));
        assertEq(8,StringDataUtil.getDirectionality((char)1648));
        assertFalse(StringDataLetterUtil.isLetter((char)1648));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1648));
    }
    @Test
    public void d665(){
        assertEq(5,StringDataUtil.getType((char)1649));
        assertEq(2,StringDataUtil.getDirectionality((char)1649));
        assertTrue(StringDataLetterUtil.isLetter((char)1649));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1649));
    }
    @Test
    public void d666(){
        assertEq(24,StringDataUtil.getType((char)1748));
        assertEq(2,StringDataUtil.getDirectionality((char)1748));
        assertFalse(StringDataLetterUtil.isLetter((char)1748));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1748));
    }
    @Test
    public void d667(){
        assertEq(5,StringDataUtil.getType((char)1749));
        assertEq(2,StringDataUtil.getDirectionality((char)1749));
        assertTrue(StringDataLetterUtil.isLetter((char)1749));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1749));
    }
    @Test
    public void d668(){
        assertEq(6,StringDataUtil.getType((char)1750));
        assertEq(8,StringDataUtil.getDirectionality((char)1750));
        assertFalse(StringDataLetterUtil.isLetter((char)1750));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1750));
    }
    @Test
    public void d669(){
        assertEq(16,StringDataUtil.getType((char)1757));
        assertEq(6,StringDataUtil.getDirectionality((char)1757));
        assertFalse(StringDataLetterUtil.isLetter((char)1757));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1757));
    }
    @Test
    public void d670(){
        assertEq(28,StringDataUtil.getType((char)1758));
        assertEq(13,StringDataUtil.getDirectionality((char)1758));
        assertFalse(StringDataLetterUtil.isLetter((char)1758));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1758));
    }
    @Test
    public void d671(){
        assertEq(6,StringDataUtil.getType((char)1759));
        assertEq(8,StringDataUtil.getDirectionality((char)1759));
        assertFalse(StringDataLetterUtil.isLetter((char)1759));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1759));
    }
    @Test
    public void d672(){
        assertEq(4,StringDataUtil.getType((char)1765));
        assertEq(2,StringDataUtil.getDirectionality((char)1765));
        assertTrue(StringDataLetterUtil.isLetter((char)1765));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1765));
    }
    @Test
    public void d673(){
        assertEq(6,StringDataUtil.getType((char)1767));
        assertEq(8,StringDataUtil.getDirectionality((char)1767));
        assertFalse(StringDataLetterUtil.isLetter((char)1767));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1767));
    }
    @Test
    public void d674(){
        assertEq(28,StringDataUtil.getType((char)1769));
        assertEq(13,StringDataUtil.getDirectionality((char)1769));
        assertFalse(StringDataLetterUtil.isLetter((char)1769));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1769));
    }
    @Test
    public void d675(){
        assertEq(6,StringDataUtil.getType((char)1770));
        assertEq(8,StringDataUtil.getDirectionality((char)1770));
        assertFalse(StringDataLetterUtil.isLetter((char)1770));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1770));
    }
    @Test
    public void d676(){
        assertEq(5,StringDataUtil.getType((char)1774));
        assertEq(2,StringDataUtil.getDirectionality((char)1774));
        assertTrue(StringDataLetterUtil.isLetter((char)1774));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1774));
    }
    @Test
    public void d677(){
        assertEq(9,StringDataUtil.getType((char)1776));
        assertEq(3,StringDataUtil.getDirectionality((char)1776));
        assertFalse(StringDataLetterUtil.isLetter((char)1776));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1776));
    }
    @Test
    public void d678(){
        assertEq(5,StringDataUtil.getType((char)1786));
        assertEq(2,StringDataUtil.getDirectionality((char)1786));
        assertTrue(StringDataLetterUtil.isLetter((char)1786));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1786));
    }
    @Test
    public void d679(){
        assertEq(28,StringDataUtil.getType((char)1789));
        assertEq(2,StringDataUtil.getDirectionality((char)1789));
        assertFalse(StringDataLetterUtil.isLetter((char)1789));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1789));
    }
    @Test
    public void d680(){
        assertEq(5,StringDataUtil.getType((char)1791));
        assertEq(2,StringDataUtil.getDirectionality((char)1791));
        assertTrue(StringDataLetterUtil.isLetter((char)1791));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1791));
    }
    @Test
    public void d681(){
        assertEq(24,StringDataUtil.getType((char)1792));
        assertEq(2,StringDataUtil.getDirectionality((char)1792));
        assertFalse(StringDataLetterUtil.isLetter((char)1792));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1792));
    }
    @Test
    public void d682(){
        assertEq(0,StringDataUtil.getType((char)1806));
        assertEq(-1,StringDataUtil.getDirectionality((char)1806));
        assertFalse(StringDataLetterUtil.isLetter((char)1806));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1806));
    }
    @Test
    public void d683(){
        assertEq(16,StringDataUtil.getType((char)1807));
        assertEq(2,StringDataUtil.getDirectionality((char)1807));
        assertFalse(StringDataLetterUtil.isLetter((char)1807));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1807));
    }
    @Test
    public void d684(){
        assertEq(5,StringDataUtil.getType((char)1808));
        assertEq(2,StringDataUtil.getDirectionality((char)1808));
        assertTrue(StringDataLetterUtil.isLetter((char)1808));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1808));
    }
    @Test
    public void d685(){
        assertEq(6,StringDataUtil.getType((char)1809));
        assertEq(8,StringDataUtil.getDirectionality((char)1809));
        assertFalse(StringDataLetterUtil.isLetter((char)1809));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1809));
    }
    @Test
    public void d686(){
        assertEq(5,StringDataUtil.getType((char)1810));
        assertEq(2,StringDataUtil.getDirectionality((char)1810));
        assertTrue(StringDataLetterUtil.isLetter((char)1810));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1810));
    }
    @Test
    public void d687(){
        assertEq(6,StringDataUtil.getType((char)1840));
        assertEq(8,StringDataUtil.getDirectionality((char)1840));
        assertFalse(StringDataLetterUtil.isLetter((char)1840));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1840));
    }
    @Test
    public void d688(){
        assertEq(0,StringDataUtil.getType((char)1867));
        assertEq(-1,StringDataUtil.getDirectionality((char)1867));
        assertFalse(StringDataLetterUtil.isLetter((char)1867));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1867));
    }
    @Test
    public void d689(){
        assertEq(5,StringDataUtil.getType((char)1869));
        assertEq(2,StringDataUtil.getDirectionality((char)1869));
        assertTrue(StringDataLetterUtil.isLetter((char)1869));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1869));
    }
    @Test
    public void d690(){
        assertEq(6,StringDataUtil.getType((char)1958));
        assertEq(8,StringDataUtil.getDirectionality((char)1958));
        assertFalse(StringDataLetterUtil.isLetter((char)1958));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1958));
    }
    @Test
    public void d691(){
        assertEq(5,StringDataUtil.getType((char)1969));
        assertEq(2,StringDataUtil.getDirectionality((char)1969));
        assertTrue(StringDataLetterUtil.isLetter((char)1969));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1969));
    }
    @Test
    public void d692(){
        assertEq(0,StringDataUtil.getType((char)1970));
        assertEq(-1,StringDataUtil.getDirectionality((char)1970));
        assertFalse(StringDataLetterUtil.isLetter((char)1970));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1970));
    }
    @Test
    public void d693(){
        assertEq(9,StringDataUtil.getType((char)1984));
        assertEq(1,StringDataUtil.getDirectionality((char)1984));
        assertFalse(StringDataLetterUtil.isLetter((char)1984));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1984));
    }
    @Test
    public void d694(){
        assertEq(5,StringDataUtil.getType((char)1994));
        assertEq(1,StringDataUtil.getDirectionality((char)1994));
        assertTrue(StringDataLetterUtil.isLetter((char)1994));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1994));
    }
    @Test
    public void d695(){
        assertEq(6,StringDataUtil.getType((char)2027));
        assertEq(8,StringDataUtil.getDirectionality((char)2027));
        assertFalse(StringDataLetterUtil.isLetter((char)2027));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2027));
    }
    @Test
    public void d696(){
        assertEq(4,StringDataUtil.getType((char)2036));
        assertEq(1,StringDataUtil.getDirectionality((char)2036));
        assertTrue(StringDataLetterUtil.isLetter((char)2036));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2036));
    }
    @Test
    public void d697(){
        assertEq(28,StringDataUtil.getType((char)2038));
        assertEq(13,StringDataUtil.getDirectionality((char)2038));
        assertFalse(StringDataLetterUtil.isLetter((char)2038));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2038));
    }
    @Test
    public void d698(){
        assertEq(24,StringDataUtil.getType((char)2039));
        assertEq(13,StringDataUtil.getDirectionality((char)2039));
        assertFalse(StringDataLetterUtil.isLetter((char)2039));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2039));
    }
    @Test
    public void d699(){
        assertEq(4,StringDataUtil.getType((char)2042));
        assertEq(1,StringDataUtil.getDirectionality((char)2042));
        assertTrue(StringDataLetterUtil.isLetter((char)2042));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2042));
    }
    @Test
    public void d700(){
        assertEq(0,StringDataUtil.getType((char)2043));
        assertEq(-1,StringDataUtil.getDirectionality((char)2043));
        assertFalse(StringDataLetterUtil.isLetter((char)2043));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2043));
    }
    @Test
    public void d701(){
        assertEq(5,StringDataUtil.getType((char)2048));
        assertEq(1,StringDataUtil.getDirectionality((char)2048));
        assertTrue(StringDataLetterUtil.isLetter((char)2048));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2048));
    }
    @Test
    public void d702(){
        assertEq(6,StringDataUtil.getType((char)2070));
        assertEq(8,StringDataUtil.getDirectionality((char)2070));
        assertFalse(StringDataLetterUtil.isLetter((char)2070));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2070));
    }
    @Test
    public void d703(){
        assertEq(4,StringDataUtil.getType((char)2074));
        assertEq(1,StringDataUtil.getDirectionality((char)2074));
        assertTrue(StringDataLetterUtil.isLetter((char)2074));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2074));
    }
    @Test
    public void d704(){
        assertEq(6,StringDataUtil.getType((char)2075));
        assertEq(8,StringDataUtil.getDirectionality((char)2075));
        assertFalse(StringDataLetterUtil.isLetter((char)2075));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2075));
    }
    @Test
    public void d705(){
        assertEq(4,StringDataUtil.getType((char)2084));
        assertEq(1,StringDataUtil.getDirectionality((char)2084));
        assertTrue(StringDataLetterUtil.isLetter((char)2084));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2084));
    }
    @Test
    public void d706(){
        assertEq(6,StringDataUtil.getType((char)2085));
        assertEq(8,StringDataUtil.getDirectionality((char)2085));
        assertFalse(StringDataLetterUtil.isLetter((char)2085));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2085));
    }
    @Test
    public void d707(){
        assertEq(4,StringDataUtil.getType((char)2088));
        assertEq(1,StringDataUtil.getDirectionality((char)2088));
        assertTrue(StringDataLetterUtil.isLetter((char)2088));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2088));
    }
    @Test
    public void d708(){
        assertEq(6,StringDataUtil.getType((char)2089));
        assertEq(8,StringDataUtil.getDirectionality((char)2089));
        assertFalse(StringDataLetterUtil.isLetter((char)2089));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2089));
    }
    @Test
    public void d709(){
        assertEq(0,StringDataUtil.getType((char)2094));
        assertEq(-1,StringDataUtil.getDirectionality((char)2094));
        assertFalse(StringDataLetterUtil.isLetter((char)2094));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2094));
    }
    @Test
    public void d710(){
        assertEq(24,StringDataUtil.getType((char)2096));
        assertEq(1,StringDataUtil.getDirectionality((char)2096));
        assertFalse(StringDataLetterUtil.isLetter((char)2096));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2096));
    }
    @Test
    public void d711(){
        assertEq(0,StringDataUtil.getType((char)2111));
        assertEq(-1,StringDataUtil.getDirectionality((char)2111));
        assertFalse(StringDataLetterUtil.isLetter((char)2111));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2111));
    }
    @Test
    public void d712(){
        assertEq(5,StringDataUtil.getType((char)2112));
        assertEq(1,StringDataUtil.getDirectionality((char)2112));
        assertTrue(StringDataLetterUtil.isLetter((char)2112));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2112));
    }
    @Test
    public void d713(){
        assertEq(6,StringDataUtil.getType((char)2137));
        assertEq(8,StringDataUtil.getDirectionality((char)2137));
        assertFalse(StringDataLetterUtil.isLetter((char)2137));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2137));
    }
    @Test
    public void d714(){
        assertEq(0,StringDataUtil.getType((char)2140));
        assertEq(-1,StringDataUtil.getDirectionality((char)2140));
        assertFalse(StringDataLetterUtil.isLetter((char)2140));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2140));
    }
    @Test
    public void d715(){
        assertEq(24,StringDataUtil.getType((char)2142));
        assertEq(1,StringDataUtil.getDirectionality((char)2142));
        assertFalse(StringDataLetterUtil.isLetter((char)2142));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2142));
    }
    @Test
    public void d716(){
        assertEq(0,StringDataUtil.getType((char)2143));
        assertEq(-1,StringDataUtil.getDirectionality((char)2143));
        assertFalse(StringDataLetterUtil.isLetter((char)2143));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2143));
    }
    @Test
    public void d717(){
        assertEq(5,StringDataUtil.getType((char)2208));
        assertEq(2,StringDataUtil.getDirectionality((char)2208));
        assertTrue(StringDataLetterUtil.isLetter((char)2208));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2208));
    }
    @Test
    public void d718(){
        assertEq(0,StringDataUtil.getType((char)2209));
        assertEq(-1,StringDataUtil.getDirectionality((char)2209));
        assertFalse(StringDataLetterUtil.isLetter((char)2209));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2209));
    }
    @Test
    public void d719(){
        assertEq(5,StringDataUtil.getType((char)2210));
        assertEq(2,StringDataUtil.getDirectionality((char)2210));
        assertTrue(StringDataLetterUtil.isLetter((char)2210));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2210));
    }
    @Test
    public void d720(){
        assertEq(0,StringDataUtil.getType((char)2221));
        assertEq(-1,StringDataUtil.getDirectionality((char)2221));
        assertFalse(StringDataLetterUtil.isLetter((char)2221));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2221));
    }
    @Test
    public void d721(){
        assertEq(6,StringDataUtil.getType((char)2276));
        assertEq(8,StringDataUtil.getDirectionality((char)2276));
        assertFalse(StringDataLetterUtil.isLetter((char)2276));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2276));
    }
    @Test
    public void d722(){
        assertEq(0,StringDataUtil.getType((char)2303));
        assertEq(-1,StringDataUtil.getDirectionality((char)2303));
        assertFalse(StringDataLetterUtil.isLetter((char)2303));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2303));
    }
    @Test
    public void d723(){
        assertEq(6,StringDataUtil.getType((char)2304));
        assertEq(8,StringDataUtil.getDirectionality((char)2304));
        assertFalse(StringDataLetterUtil.isLetter((char)2304));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2304));
    }
    @Test
    public void d724(){
        assertEq(8,StringDataUtil.getType((char)2307));
        assertEq(0,StringDataUtil.getDirectionality((char)2307));
        assertFalse(StringDataLetterUtil.isLetter((char)2307));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2307));
    }
    @Test
    public void d725(){
        assertEq(5,StringDataUtil.getType((char)2308));
        assertEq(0,StringDataUtil.getDirectionality((char)2308));
        assertTrue(StringDataLetterUtil.isLetter((char)2308));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2308));
    }
    @Test
    public void d726(){
        assertEq(6,StringDataUtil.getType((char)2362));
        assertEq(8,StringDataUtil.getDirectionality((char)2362));
        assertFalse(StringDataLetterUtil.isLetter((char)2362));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2362));
    }
    @Test
    public void d727(){
        assertEq(8,StringDataUtil.getType((char)2363));
        assertEq(0,StringDataUtil.getDirectionality((char)2363));
        assertFalse(StringDataLetterUtil.isLetter((char)2363));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2363));
    }
    @Test
    public void d728(){
        assertEq(6,StringDataUtil.getType((char)2364));
        assertEq(8,StringDataUtil.getDirectionality((char)2364));
        assertFalse(StringDataLetterUtil.isLetter((char)2364));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2364));
    }
    @Test
    public void d729(){
        assertEq(5,StringDataUtil.getType((char)2365));
        assertEq(0,StringDataUtil.getDirectionality((char)2365));
        assertTrue(StringDataLetterUtil.isLetter((char)2365));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2365));
    }
    @Test
    public void d730(){
        assertEq(8,StringDataUtil.getType((char)2366));
        assertEq(0,StringDataUtil.getDirectionality((char)2366));
        assertFalse(StringDataLetterUtil.isLetter((char)2366));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2366));
    }
    @Test
    public void d731(){
        assertEq(6,StringDataUtil.getType((char)2369));
        assertEq(8,StringDataUtil.getDirectionality((char)2369));
        assertFalse(StringDataLetterUtil.isLetter((char)2369));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2369));
    }
    @Test
    public void d732(){
        assertEq(8,StringDataUtil.getType((char)2377));
        assertEq(0,StringDataUtil.getDirectionality((char)2377));
        assertFalse(StringDataLetterUtil.isLetter((char)2377));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2377));
    }
    @Test
    public void d733(){
        assertEq(6,StringDataUtil.getType((char)2381));
        assertEq(8,StringDataUtil.getDirectionality((char)2381));
        assertFalse(StringDataLetterUtil.isLetter((char)2381));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2381));
    }
    @Test
    public void d734(){
        assertEq(8,StringDataUtil.getType((char)2382));
        assertEq(0,StringDataUtil.getDirectionality((char)2382));
        assertFalse(StringDataLetterUtil.isLetter((char)2382));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2382));
    }
    @Test
    public void d735(){
        assertEq(5,StringDataUtil.getType((char)2384));
        assertEq(0,StringDataUtil.getDirectionality((char)2384));
        assertTrue(StringDataLetterUtil.isLetter((char)2384));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2384));
    }
    @Test
    public void d736(){
        assertEq(6,StringDataUtil.getType((char)2385));
        assertEq(8,StringDataUtil.getDirectionality((char)2385));
        assertFalse(StringDataLetterUtil.isLetter((char)2385));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2385));
    }
    @Test
    public void d737(){
        assertEq(5,StringDataUtil.getType((char)2392));
        assertEq(0,StringDataUtil.getDirectionality((char)2392));
        assertTrue(StringDataLetterUtil.isLetter((char)2392));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2392));
    }
    @Test
    public void d738(){
        assertEq(6,StringDataUtil.getType((char)2402));
        assertEq(8,StringDataUtil.getDirectionality((char)2402));
        assertFalse(StringDataLetterUtil.isLetter((char)2402));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2402));
    }
    @Test
    public void d739(){
        assertEq(24,StringDataUtil.getType((char)2404));
        assertEq(0,StringDataUtil.getDirectionality((char)2404));
        assertFalse(StringDataLetterUtil.isLetter((char)2404));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2404));
    }
    @Test
    public void d740(){
        assertEq(9,StringDataUtil.getType((char)2406));
        assertEq(0,StringDataUtil.getDirectionality((char)2406));
        assertFalse(StringDataLetterUtil.isLetter((char)2406));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2406));
    }
    @Test
    public void d741(){
        assertEq(24,StringDataUtil.getType((char)2416));
        assertEq(0,StringDataUtil.getDirectionality((char)2416));
        assertFalse(StringDataLetterUtil.isLetter((char)2416));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2416));
    }
    @Test
    public void d742(){
        assertEq(4,StringDataUtil.getType((char)2417));
        assertEq(0,StringDataUtil.getDirectionality((char)2417));
        assertTrue(StringDataLetterUtil.isLetter((char)2417));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2417));
    }
    @Test
    public void d743(){
        assertEq(5,StringDataUtil.getType((char)2418));
        assertEq(0,StringDataUtil.getDirectionality((char)2418));
        assertTrue(StringDataLetterUtil.isLetter((char)2418));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2418));
    }
    @Test
    public void d744(){
        assertEq(0,StringDataUtil.getType((char)2424));
        assertEq(-1,StringDataUtil.getDirectionality((char)2424));
        assertFalse(StringDataLetterUtil.isLetter((char)2424));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2424));
    }
    @Test
    public void d745(){
        assertEq(5,StringDataUtil.getType((char)2425));
        assertEq(0,StringDataUtil.getDirectionality((char)2425));
        assertTrue(StringDataLetterUtil.isLetter((char)2425));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2425));
    }
    @Test
    public void d746(){
        assertEq(0,StringDataUtil.getType((char)2432));
        assertEq(-1,StringDataUtil.getDirectionality((char)2432));
        assertFalse(StringDataLetterUtil.isLetter((char)2432));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2432));
    }
    @Test
    public void d747(){
        assertEq(6,StringDataUtil.getType((char)2433));
        assertEq(8,StringDataUtil.getDirectionality((char)2433));
        assertFalse(StringDataLetterUtil.isLetter((char)2433));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2433));
    }
    @Test
    public void d748(){
        assertEq(8,StringDataUtil.getType((char)2434));
        assertEq(0,StringDataUtil.getDirectionality((char)2434));
        assertFalse(StringDataLetterUtil.isLetter((char)2434));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2434));
    }
    @Test
    public void d749(){
        assertEq(0,StringDataUtil.getType((char)2436));
        assertEq(-1,StringDataUtil.getDirectionality((char)2436));
        assertFalse(StringDataLetterUtil.isLetter((char)2436));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2436));
    }
    @Test
    public void d750(){
        assertEq(5,StringDataUtil.getType((char)2437));
        assertEq(0,StringDataUtil.getDirectionality((char)2437));
        assertTrue(StringDataLetterUtil.isLetter((char)2437));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2437));
    }
    @Test
    public void d751(){
        assertEq(0,StringDataUtil.getType((char)2445));
        assertEq(-1,StringDataUtil.getDirectionality((char)2445));
        assertFalse(StringDataLetterUtil.isLetter((char)2445));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2445));
    }
    @Test
    public void d752(){
        assertEq(5,StringDataUtil.getType((char)2447));
        assertEq(0,StringDataUtil.getDirectionality((char)2447));
        assertTrue(StringDataLetterUtil.isLetter((char)2447));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2447));
    }
    @Test
    public void d753(){
        assertEq(0,StringDataUtil.getType((char)2449));
        assertEq(-1,StringDataUtil.getDirectionality((char)2449));
        assertFalse(StringDataLetterUtil.isLetter((char)2449));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2449));
    }
    @Test
    public void d754(){
        assertEq(5,StringDataUtil.getType((char)2451));
        assertEq(0,StringDataUtil.getDirectionality((char)2451));
        assertTrue(StringDataLetterUtil.isLetter((char)2451));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2451));
    }
    @Test
    public void d755(){
        assertEq(0,StringDataUtil.getType((char)2473));
        assertEq(-1,StringDataUtil.getDirectionality((char)2473));
        assertFalse(StringDataLetterUtil.isLetter((char)2473));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2473));
    }
    @Test
    public void d756(){
        assertEq(5,StringDataUtil.getType((char)2474));
        assertEq(0,StringDataUtil.getDirectionality((char)2474));
        assertTrue(StringDataLetterUtil.isLetter((char)2474));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2474));
    }
    @Test
    public void d757(){
        assertEq(0,StringDataUtil.getType((char)2481));
        assertEq(-1,StringDataUtil.getDirectionality((char)2481));
        assertFalse(StringDataLetterUtil.isLetter((char)2481));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2481));
    }
    @Test
    public void d758(){
        assertEq(5,StringDataUtil.getType((char)2482));
        assertEq(0,StringDataUtil.getDirectionality((char)2482));
        assertTrue(StringDataLetterUtil.isLetter((char)2482));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2482));
    }
    @Test
    public void d759(){
        assertEq(0,StringDataUtil.getType((char)2483));
        assertEq(-1,StringDataUtil.getDirectionality((char)2483));
        assertFalse(StringDataLetterUtil.isLetter((char)2483));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2483));
    }
    @Test
    public void d760(){
        assertEq(5,StringDataUtil.getType((char)2486));
        assertEq(0,StringDataUtil.getDirectionality((char)2486));
        assertTrue(StringDataLetterUtil.isLetter((char)2486));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2486));
    }
    @Test
    public void d761(){
        assertEq(0,StringDataUtil.getType((char)2490));
        assertEq(-1,StringDataUtil.getDirectionality((char)2490));
        assertFalse(StringDataLetterUtil.isLetter((char)2490));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2490));
    }
    @Test
    public void d762(){
        assertEq(6,StringDataUtil.getType((char)2492));
        assertEq(8,StringDataUtil.getDirectionality((char)2492));
        assertFalse(StringDataLetterUtil.isLetter((char)2492));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2492));
    }
    @Test
    public void d763(){
        assertEq(5,StringDataUtil.getType((char)2493));
        assertEq(0,StringDataUtil.getDirectionality((char)2493));
        assertTrue(StringDataLetterUtil.isLetter((char)2493));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2493));
    }
    @Test
    public void d764(){
        assertEq(8,StringDataUtil.getType((char)2494));
        assertEq(0,StringDataUtil.getDirectionality((char)2494));
        assertFalse(StringDataLetterUtil.isLetter((char)2494));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2494));
    }
    @Test
    public void d765(){
        assertEq(6,StringDataUtil.getType((char)2497));
        assertEq(8,StringDataUtil.getDirectionality((char)2497));
        assertFalse(StringDataLetterUtil.isLetter((char)2497));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2497));
    }
    @Test
    public void d766(){
        assertEq(0,StringDataUtil.getType((char)2501));
        assertEq(-1,StringDataUtil.getDirectionality((char)2501));
        assertFalse(StringDataLetterUtil.isLetter((char)2501));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2501));
    }
    @Test
    public void d767(){
        assertEq(8,StringDataUtil.getType((char)2503));
        assertEq(0,StringDataUtil.getDirectionality((char)2503));
        assertFalse(StringDataLetterUtil.isLetter((char)2503));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2503));
    }
    @Test
    public void d768(){
        assertEq(0,StringDataUtil.getType((char)2505));
        assertEq(-1,StringDataUtil.getDirectionality((char)2505));
        assertFalse(StringDataLetterUtil.isLetter((char)2505));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2505));
    }
    @Test
    public void d769(){
        assertEq(8,StringDataUtil.getType((char)2507));
        assertEq(0,StringDataUtil.getDirectionality((char)2507));
        assertFalse(StringDataLetterUtil.isLetter((char)2507));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2507));
    }
    @Test
    public void d770(){
        assertEq(6,StringDataUtil.getType((char)2509));
        assertEq(8,StringDataUtil.getDirectionality((char)2509));
        assertFalse(StringDataLetterUtil.isLetter((char)2509));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2509));
    }
    @Test
    public void d771(){
        assertEq(5,StringDataUtil.getType((char)2510));
        assertEq(0,StringDataUtil.getDirectionality((char)2510));
        assertTrue(StringDataLetterUtil.isLetter((char)2510));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2510));
    }
    @Test
    public void d772(){
        assertEq(0,StringDataUtil.getType((char)2511));
        assertEq(-1,StringDataUtil.getDirectionality((char)2511));
        assertFalse(StringDataLetterUtil.isLetter((char)2511));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2511));
    }
    @Test
    public void d773(){
        assertEq(8,StringDataUtil.getType((char)2519));
        assertEq(0,StringDataUtil.getDirectionality((char)2519));
        assertFalse(StringDataLetterUtil.isLetter((char)2519));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2519));
    }
    @Test
    public void d774(){
        assertEq(0,StringDataUtil.getType((char)2520));
        assertEq(-1,StringDataUtil.getDirectionality((char)2520));
        assertFalse(StringDataLetterUtil.isLetter((char)2520));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2520));
    }
    @Test
    public void d775(){
        assertEq(5,StringDataUtil.getType((char)2524));
        assertEq(0,StringDataUtil.getDirectionality((char)2524));
        assertTrue(StringDataLetterUtil.isLetter((char)2524));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2524));
    }
    @Test
    public void d776(){
        assertEq(0,StringDataUtil.getType((char)2526));
        assertEq(-1,StringDataUtil.getDirectionality((char)2526));
        assertFalse(StringDataLetterUtil.isLetter((char)2526));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2526));
    }
    @Test
    public void d777(){
        assertEq(5,StringDataUtil.getType((char)2527));
        assertEq(0,StringDataUtil.getDirectionality((char)2527));
        assertTrue(StringDataLetterUtil.isLetter((char)2527));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2527));
    }
    @Test
    public void d778(){
        assertEq(6,StringDataUtil.getType((char)2530));
        assertEq(8,StringDataUtil.getDirectionality((char)2530));
        assertFalse(StringDataLetterUtil.isLetter((char)2530));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2530));
    }
    @Test
    public void d779(){
        assertEq(0,StringDataUtil.getType((char)2532));
        assertEq(-1,StringDataUtil.getDirectionality((char)2532));
        assertFalse(StringDataLetterUtil.isLetter((char)2532));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2532));
    }
    @Test
    public void d780(){
        assertEq(9,StringDataUtil.getType((char)2534));
        assertEq(0,StringDataUtil.getDirectionality((char)2534));
        assertFalse(StringDataLetterUtil.isLetter((char)2534));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2534));
    }
    @Test
    public void d781(){
        assertEq(5,StringDataUtil.getType((char)2544));
        assertEq(0,StringDataUtil.getDirectionality((char)2544));
        assertTrue(StringDataLetterUtil.isLetter((char)2544));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2544));
    }
    @Test
    public void d782(){
        assertEq(26,StringDataUtil.getType((char)2546));
        assertEq(5,StringDataUtil.getDirectionality((char)2546));
        assertFalse(StringDataLetterUtil.isLetter((char)2546));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2546));
    }
    @Test
    public void d783(){
        assertEq(11,StringDataUtil.getType((char)2548));
        assertEq(0,StringDataUtil.getDirectionality((char)2548));
        assertFalse(StringDataLetterUtil.isLetter((char)2548));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2548));
    }
    @Test
    public void d784(){
        assertEq(28,StringDataUtil.getType((char)2554));
        assertEq(0,StringDataUtil.getDirectionality((char)2554));
        assertFalse(StringDataLetterUtil.isLetter((char)2554));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2554));
    }
    @Test
    public void d785(){
        assertEq(26,StringDataUtil.getType((char)2555));
        assertEq(5,StringDataUtil.getDirectionality((char)2555));
        assertFalse(StringDataLetterUtil.isLetter((char)2555));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2555));
    }
    @Test
    public void d786(){
        assertEq(0,StringDataUtil.getType((char)2556));
        assertEq(-1,StringDataUtil.getDirectionality((char)2556));
        assertFalse(StringDataLetterUtil.isLetter((char)2556));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2556));
    }
    @Test
    public void d787(){
        assertEq(6,StringDataUtil.getType((char)2561));
        assertEq(8,StringDataUtil.getDirectionality((char)2561));
        assertFalse(StringDataLetterUtil.isLetter((char)2561));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2561));
    }
    @Test
    public void d788(){
        assertEq(8,StringDataUtil.getType((char)2563));
        assertEq(0,StringDataUtil.getDirectionality((char)2563));
        assertFalse(StringDataLetterUtil.isLetter((char)2563));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2563));
    }
    @Test
    public void d789(){
        assertEq(0,StringDataUtil.getType((char)2564));
        assertEq(-1,StringDataUtil.getDirectionality((char)2564));
        assertFalse(StringDataLetterUtil.isLetter((char)2564));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2564));
    }
    @Test
    public void d790(){
        assertEq(5,StringDataUtil.getType((char)2565));
        assertEq(0,StringDataUtil.getDirectionality((char)2565));
        assertTrue(StringDataLetterUtil.isLetter((char)2565));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2565));
    }
    @Test
    public void d791(){
        assertEq(0,StringDataUtil.getType((char)2571));
        assertEq(-1,StringDataUtil.getDirectionality((char)2571));
        assertFalse(StringDataLetterUtil.isLetter((char)2571));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2571));
    }
    @Test
    public void d792(){
        assertEq(5,StringDataUtil.getType((char)2575));
        assertEq(0,StringDataUtil.getDirectionality((char)2575));
        assertTrue(StringDataLetterUtil.isLetter((char)2575));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2575));
    }
    @Test
    public void d793(){
        assertEq(0,StringDataUtil.getType((char)2577));
        assertEq(-1,StringDataUtil.getDirectionality((char)2577));
        assertFalse(StringDataLetterUtil.isLetter((char)2577));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2577));
    }
    @Test
    public void d794(){
        assertEq(5,StringDataUtil.getType((char)2579));
        assertEq(0,StringDataUtil.getDirectionality((char)2579));
        assertTrue(StringDataLetterUtil.isLetter((char)2579));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2579));
    }
    @Test
    public void d795(){
        assertEq(0,StringDataUtil.getType((char)2601));
        assertEq(-1,StringDataUtil.getDirectionality((char)2601));
        assertFalse(StringDataLetterUtil.isLetter((char)2601));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2601));
    }
    @Test
    public void d796(){
        assertEq(5,StringDataUtil.getType((char)2602));
        assertEq(0,StringDataUtil.getDirectionality((char)2602));
        assertTrue(StringDataLetterUtil.isLetter((char)2602));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2602));
    }
    @Test
    public void d797(){
        assertEq(0,StringDataUtil.getType((char)2609));
        assertEq(-1,StringDataUtil.getDirectionality((char)2609));
        assertFalse(StringDataLetterUtil.isLetter((char)2609));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2609));
    }
    @Test
    public void d798(){
        assertEq(5,StringDataUtil.getType((char)2610));
        assertEq(0,StringDataUtil.getDirectionality((char)2610));
        assertTrue(StringDataLetterUtil.isLetter((char)2610));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2610));
    }
    @Test
    public void d799(){
        assertEq(0,StringDataUtil.getType((char)2612));
        assertEq(-1,StringDataUtil.getDirectionality((char)2612));
        assertFalse(StringDataLetterUtil.isLetter((char)2612));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2612));
    }
    @Test
    public void d800(){
        assertEq(5,StringDataUtil.getType((char)2613));
        assertEq(0,StringDataUtil.getDirectionality((char)2613));
        assertTrue(StringDataLetterUtil.isLetter((char)2613));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2613));
    }
    @Test
    public void d801(){
        assertEq(0,StringDataUtil.getType((char)2615));
        assertEq(-1,StringDataUtil.getDirectionality((char)2615));
        assertFalse(StringDataLetterUtil.isLetter((char)2615));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2615));
    }
    @Test
    public void d802(){
        assertEq(5,StringDataUtil.getType((char)2616));
        assertEq(0,StringDataUtil.getDirectionality((char)2616));
        assertTrue(StringDataLetterUtil.isLetter((char)2616));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2616));
    }
    @Test
    public void d803(){
        assertEq(0,StringDataUtil.getType((char)2618));
        assertEq(-1,StringDataUtil.getDirectionality((char)2618));
        assertFalse(StringDataLetterUtil.isLetter((char)2618));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2618));
    }
    @Test
    public void d804(){
        assertEq(6,StringDataUtil.getType((char)2620));
        assertEq(8,StringDataUtil.getDirectionality((char)2620));
        assertFalse(StringDataLetterUtil.isLetter((char)2620));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2620));
    }
    @Test
    public void d805(){
        assertEq(0,StringDataUtil.getType((char)2621));
        assertEq(-1,StringDataUtil.getDirectionality((char)2621));
        assertFalse(StringDataLetterUtil.isLetter((char)2621));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2621));
    }
    @Test
    public void d806(){
        assertEq(8,StringDataUtil.getType((char)2622));
        assertEq(0,StringDataUtil.getDirectionality((char)2622));
        assertFalse(StringDataLetterUtil.isLetter((char)2622));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2622));
    }
    @Test
    public void d807(){
        assertEq(6,StringDataUtil.getType((char)2625));
        assertEq(8,StringDataUtil.getDirectionality((char)2625));
        assertFalse(StringDataLetterUtil.isLetter((char)2625));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2625));
    }
    @Test
    public void d808(){
        assertEq(0,StringDataUtil.getType((char)2627));
        assertEq(-1,StringDataUtil.getDirectionality((char)2627));
        assertFalse(StringDataLetterUtil.isLetter((char)2627));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2627));
    }
    @Test
    public void d809(){
        assertEq(6,StringDataUtil.getType((char)2631));
        assertEq(8,StringDataUtil.getDirectionality((char)2631));
        assertFalse(StringDataLetterUtil.isLetter((char)2631));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2631));
    }
    @Test
    public void d810(){
        assertEq(0,StringDataUtil.getType((char)2633));
        assertEq(-1,StringDataUtil.getDirectionality((char)2633));
        assertFalse(StringDataLetterUtil.isLetter((char)2633));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2633));
    }
    @Test
    public void d811(){
        assertEq(6,StringDataUtil.getType((char)2635));
        assertEq(8,StringDataUtil.getDirectionality((char)2635));
        assertFalse(StringDataLetterUtil.isLetter((char)2635));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2635));
    }
    @Test
    public void d812(){
        assertEq(0,StringDataUtil.getType((char)2638));
        assertEq(-1,StringDataUtil.getDirectionality((char)2638));
        assertFalse(StringDataLetterUtil.isLetter((char)2638));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2638));
    }
    @Test
    public void d813(){
        assertEq(6,StringDataUtil.getType((char)2641));
        assertEq(8,StringDataUtil.getDirectionality((char)2641));
        assertFalse(StringDataLetterUtil.isLetter((char)2641));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2641));
    }
    @Test
    public void d814(){
        assertEq(0,StringDataUtil.getType((char)2642));
        assertEq(-1,StringDataUtil.getDirectionality((char)2642));
        assertFalse(StringDataLetterUtil.isLetter((char)2642));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2642));
    }
    @Test
    public void d815(){
        assertEq(5,StringDataUtil.getType((char)2649));
        assertEq(0,StringDataUtil.getDirectionality((char)2649));
        assertTrue(StringDataLetterUtil.isLetter((char)2649));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2649));
    }
    @Test
    public void d816(){
        assertEq(0,StringDataUtil.getType((char)2653));
        assertEq(-1,StringDataUtil.getDirectionality((char)2653));
        assertFalse(StringDataLetterUtil.isLetter((char)2653));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2653));
    }
    @Test
    public void d817(){
        assertEq(5,StringDataUtil.getType((char)2654));
        assertEq(0,StringDataUtil.getDirectionality((char)2654));
        assertTrue(StringDataLetterUtil.isLetter((char)2654));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2654));
    }
    @Test
    public void d818(){
        assertEq(0,StringDataUtil.getType((char)2655));
        assertEq(-1,StringDataUtil.getDirectionality((char)2655));
        assertFalse(StringDataLetterUtil.isLetter((char)2655));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2655));
    }
    @Test
    public void d819(){
        assertEq(9,StringDataUtil.getType((char)2662));
        assertEq(0,StringDataUtil.getDirectionality((char)2662));
        assertFalse(StringDataLetterUtil.isLetter((char)2662));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2662));
    }
    @Test
    public void d820(){
        assertEq(6,StringDataUtil.getType((char)2672));
        assertEq(8,StringDataUtil.getDirectionality((char)2672));
        assertFalse(StringDataLetterUtil.isLetter((char)2672));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2672));
    }
    @Test
    public void d821(){
        assertEq(5,StringDataUtil.getType((char)2674));
        assertEq(0,StringDataUtil.getDirectionality((char)2674));
        assertTrue(StringDataLetterUtil.isLetter((char)2674));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2674));
    }
    @Test
    public void d822(){
        assertEq(6,StringDataUtil.getType((char)2677));
        assertEq(8,StringDataUtil.getDirectionality((char)2677));
        assertFalse(StringDataLetterUtil.isLetter((char)2677));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2677));
    }
    @Test
    public void d823(){
        assertEq(0,StringDataUtil.getType((char)2678));
        assertEq(-1,StringDataUtil.getDirectionality((char)2678));
        assertFalse(StringDataLetterUtil.isLetter((char)2678));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2678));
    }
    @Test
    public void d824(){
        assertEq(6,StringDataUtil.getType((char)2689));
        assertEq(8,StringDataUtil.getDirectionality((char)2689));
        assertFalse(StringDataLetterUtil.isLetter((char)2689));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2689));
    }
    @Test
    public void d825(){
        assertEq(8,StringDataUtil.getType((char)2691));
        assertEq(0,StringDataUtil.getDirectionality((char)2691));
        assertFalse(StringDataLetterUtil.isLetter((char)2691));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2691));
    }
    @Test
    public void d826(){
        assertEq(0,StringDataUtil.getType((char)2692));
        assertEq(-1,StringDataUtil.getDirectionality((char)2692));
        assertFalse(StringDataLetterUtil.isLetter((char)2692));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2692));
    }
    @Test
    public void d827(){
        assertEq(5,StringDataUtil.getType((char)2693));
        assertEq(0,StringDataUtil.getDirectionality((char)2693));
        assertTrue(StringDataLetterUtil.isLetter((char)2693));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2693));
    }
    @Test
    public void d828(){
        assertEq(0,StringDataUtil.getType((char)2702));
        assertEq(-1,StringDataUtil.getDirectionality((char)2702));
        assertFalse(StringDataLetterUtil.isLetter((char)2702));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2702));
    }
    @Test
    public void d829(){
        assertEq(5,StringDataUtil.getType((char)2703));
        assertEq(0,StringDataUtil.getDirectionality((char)2703));
        assertTrue(StringDataLetterUtil.isLetter((char)2703));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2703));
    }
    @Test
    public void d830(){
        assertEq(0,StringDataUtil.getType((char)2706));
        assertEq(-1,StringDataUtil.getDirectionality((char)2706));
        assertFalse(StringDataLetterUtil.isLetter((char)2706));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2706));
    }
    @Test
    public void d831(){
        assertEq(5,StringDataUtil.getType((char)2707));
        assertEq(0,StringDataUtil.getDirectionality((char)2707));
        assertTrue(StringDataLetterUtil.isLetter((char)2707));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2707));
    }
    @Test
    public void d832(){
        assertEq(0,StringDataUtil.getType((char)2729));
        assertEq(-1,StringDataUtil.getDirectionality((char)2729));
        assertFalse(StringDataLetterUtil.isLetter((char)2729));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2729));
    }
    @Test
    public void d833(){
        assertEq(5,StringDataUtil.getType((char)2730));
        assertEq(0,StringDataUtil.getDirectionality((char)2730));
        assertTrue(StringDataLetterUtil.isLetter((char)2730));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2730));
    }
    @Test
    public void d834(){
        assertEq(0,StringDataUtil.getType((char)2737));
        assertEq(-1,StringDataUtil.getDirectionality((char)2737));
        assertFalse(StringDataLetterUtil.isLetter((char)2737));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2737));
    }
    @Test
    public void d835(){
        assertEq(5,StringDataUtil.getType((char)2738));
        assertEq(0,StringDataUtil.getDirectionality((char)2738));
        assertTrue(StringDataLetterUtil.isLetter((char)2738));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2738));
    }
    @Test
    public void d836(){
        assertEq(0,StringDataUtil.getType((char)2740));
        assertEq(-1,StringDataUtil.getDirectionality((char)2740));
        assertFalse(StringDataLetterUtil.isLetter((char)2740));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2740));
    }
    @Test
    public void d837(){
        assertEq(5,StringDataUtil.getType((char)2741));
        assertEq(0,StringDataUtil.getDirectionality((char)2741));
        assertTrue(StringDataLetterUtil.isLetter((char)2741));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2741));
    }
    @Test
    public void d838(){
        assertEq(0,StringDataUtil.getType((char)2746));
        assertEq(-1,StringDataUtil.getDirectionality((char)2746));
        assertFalse(StringDataLetterUtil.isLetter((char)2746));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2746));
    }
    @Test
    public void d839(){
        assertEq(6,StringDataUtil.getType((char)2748));
        assertEq(8,StringDataUtil.getDirectionality((char)2748));
        assertFalse(StringDataLetterUtil.isLetter((char)2748));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2748));
    }
    @Test
    public void d840(){
        assertEq(5,StringDataUtil.getType((char)2749));
        assertEq(0,StringDataUtil.getDirectionality((char)2749));
        assertTrue(StringDataLetterUtil.isLetter((char)2749));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2749));
    }
    @Test
    public void d841(){
        assertEq(8,StringDataUtil.getType((char)2750));
        assertEq(0,StringDataUtil.getDirectionality((char)2750));
        assertFalse(StringDataLetterUtil.isLetter((char)2750));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2750));
    }
    @Test
    public void d842(){
        assertEq(6,StringDataUtil.getType((char)2753));
        assertEq(8,StringDataUtil.getDirectionality((char)2753));
        assertFalse(StringDataLetterUtil.isLetter((char)2753));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2753));
    }
    @Test
    public void d843(){
        assertEq(0,StringDataUtil.getType((char)2758));
        assertEq(-1,StringDataUtil.getDirectionality((char)2758));
        assertFalse(StringDataLetterUtil.isLetter((char)2758));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2758));
    }
    @Test
    public void d844(){
        assertEq(6,StringDataUtil.getType((char)2759));
        assertEq(8,StringDataUtil.getDirectionality((char)2759));
        assertFalse(StringDataLetterUtil.isLetter((char)2759));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2759));
    }
    @Test
    public void d845(){
        assertEq(8,StringDataUtil.getType((char)2761));
        assertEq(0,StringDataUtil.getDirectionality((char)2761));
        assertFalse(StringDataLetterUtil.isLetter((char)2761));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2761));
    }
    @Test
    public void d846(){
        assertEq(0,StringDataUtil.getType((char)2762));
        assertEq(-1,StringDataUtil.getDirectionality((char)2762));
        assertFalse(StringDataLetterUtil.isLetter((char)2762));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2762));
    }
    @Test
    public void d847(){
        assertEq(8,StringDataUtil.getType((char)2763));
        assertEq(0,StringDataUtil.getDirectionality((char)2763));
        assertFalse(StringDataLetterUtil.isLetter((char)2763));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2763));
    }
    @Test
    public void d848(){
        assertEq(6,StringDataUtil.getType((char)2765));
        assertEq(8,StringDataUtil.getDirectionality((char)2765));
        assertFalse(StringDataLetterUtil.isLetter((char)2765));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2765));
    }
    @Test
    public void d849(){
        assertEq(0,StringDataUtil.getType((char)2766));
        assertEq(-1,StringDataUtil.getDirectionality((char)2766));
        assertFalse(StringDataLetterUtil.isLetter((char)2766));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2766));
    }
    @Test
    public void d850(){
        assertEq(5,StringDataUtil.getType((char)2768));
        assertEq(0,StringDataUtil.getDirectionality((char)2768));
        assertTrue(StringDataLetterUtil.isLetter((char)2768));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2768));
    }
    @Test
    public void d851(){
        assertEq(0,StringDataUtil.getType((char)2769));
        assertEq(-1,StringDataUtil.getDirectionality((char)2769));
        assertFalse(StringDataLetterUtil.isLetter((char)2769));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2769));
    }
    @Test
    public void d852(){
        assertEq(5,StringDataUtil.getType((char)2784));
        assertEq(0,StringDataUtil.getDirectionality((char)2784));
        assertTrue(StringDataLetterUtil.isLetter((char)2784));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2784));
    }
    @Test
    public void d853(){
        assertEq(6,StringDataUtil.getType((char)2786));
        assertEq(8,StringDataUtil.getDirectionality((char)2786));
        assertFalse(StringDataLetterUtil.isLetter((char)2786));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2786));
    }
    @Test
    public void d854(){
        assertEq(0,StringDataUtil.getType((char)2788));
        assertEq(-1,StringDataUtil.getDirectionality((char)2788));
        assertFalse(StringDataLetterUtil.isLetter((char)2788));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2788));
    }
    @Test
    public void d855(){
        assertEq(9,StringDataUtil.getType((char)2790));
        assertEq(0,StringDataUtil.getDirectionality((char)2790));
        assertFalse(StringDataLetterUtil.isLetter((char)2790));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2790));
    }
    @Test
    public void d856(){
        assertEq(24,StringDataUtil.getType((char)2800));
        assertEq(0,StringDataUtil.getDirectionality((char)2800));
        assertFalse(StringDataLetterUtil.isLetter((char)2800));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2800));
    }
    @Test
    public void d857(){
        assertEq(26,StringDataUtil.getType((char)2801));
        assertEq(5,StringDataUtil.getDirectionality((char)2801));
        assertFalse(StringDataLetterUtil.isLetter((char)2801));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2801));
    }
    @Test
    public void d858(){
        assertEq(0,StringDataUtil.getType((char)2802));
        assertEq(-1,StringDataUtil.getDirectionality((char)2802));
        assertFalse(StringDataLetterUtil.isLetter((char)2802));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2802));
    }
    @Test
    public void d859(){
        assertEq(6,StringDataUtil.getType((char)2817));
        assertEq(8,StringDataUtil.getDirectionality((char)2817));
        assertFalse(StringDataLetterUtil.isLetter((char)2817));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2817));
    }
    @Test
    public void d860(){
        assertEq(8,StringDataUtil.getType((char)2818));
        assertEq(0,StringDataUtil.getDirectionality((char)2818));
        assertFalse(StringDataLetterUtil.isLetter((char)2818));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2818));
    }
    @Test
    public void d861(){
        assertEq(0,StringDataUtil.getType((char)2820));
        assertEq(-1,StringDataUtil.getDirectionality((char)2820));
        assertFalse(StringDataLetterUtil.isLetter((char)2820));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2820));
    }
    @Test
    public void d862(){
        assertEq(5,StringDataUtil.getType((char)2821));
        assertEq(0,StringDataUtil.getDirectionality((char)2821));
        assertTrue(StringDataLetterUtil.isLetter((char)2821));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2821));
    }
    @Test
    public void d863(){
        assertEq(0,StringDataUtil.getType((char)2829));
        assertEq(-1,StringDataUtil.getDirectionality((char)2829));
        assertFalse(StringDataLetterUtil.isLetter((char)2829));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2829));
    }
    @Test
    public void d864(){
        assertEq(5,StringDataUtil.getType((char)2831));
        assertEq(0,StringDataUtil.getDirectionality((char)2831));
        assertTrue(StringDataLetterUtil.isLetter((char)2831));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2831));
    }
    @Test
    public void d865(){
        assertEq(0,StringDataUtil.getType((char)2833));
        assertEq(-1,StringDataUtil.getDirectionality((char)2833));
        assertFalse(StringDataLetterUtil.isLetter((char)2833));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2833));
    }
    @Test
    public void d866(){
        assertEq(5,StringDataUtil.getType((char)2835));
        assertEq(0,StringDataUtil.getDirectionality((char)2835));
        assertTrue(StringDataLetterUtil.isLetter((char)2835));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2835));
    }
    @Test
    public void d867(){
        assertEq(0,StringDataUtil.getType((char)2857));
        assertEq(-1,StringDataUtil.getDirectionality((char)2857));
        assertFalse(StringDataLetterUtil.isLetter((char)2857));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2857));
    }
    @Test
    public void d868(){
        assertEq(5,StringDataUtil.getType((char)2858));
        assertEq(0,StringDataUtil.getDirectionality((char)2858));
        assertTrue(StringDataLetterUtil.isLetter((char)2858));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2858));
    }
    @Test
    public void d869(){
        assertEq(0,StringDataUtil.getType((char)2865));
        assertEq(-1,StringDataUtil.getDirectionality((char)2865));
        assertFalse(StringDataLetterUtil.isLetter((char)2865));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2865));
    }
    @Test
    public void d870(){
        assertEq(5,StringDataUtil.getType((char)2866));
        assertEq(0,StringDataUtil.getDirectionality((char)2866));
        assertTrue(StringDataLetterUtil.isLetter((char)2866));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2866));
    }
    @Test
    public void d871(){
        assertEq(0,StringDataUtil.getType((char)2868));
        assertEq(-1,StringDataUtil.getDirectionality((char)2868));
        assertFalse(StringDataLetterUtil.isLetter((char)2868));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2868));
    }
    @Test
    public void d872(){
        assertEq(5,StringDataUtil.getType((char)2869));
        assertEq(0,StringDataUtil.getDirectionality((char)2869));
        assertTrue(StringDataLetterUtil.isLetter((char)2869));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2869));
    }
    @Test
    public void d873(){
        assertEq(0,StringDataUtil.getType((char)2874));
        assertEq(-1,StringDataUtil.getDirectionality((char)2874));
        assertFalse(StringDataLetterUtil.isLetter((char)2874));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2874));
    }
    @Test
    public void d874(){
        assertEq(6,StringDataUtil.getType((char)2876));
        assertEq(8,StringDataUtil.getDirectionality((char)2876));
        assertFalse(StringDataLetterUtil.isLetter((char)2876));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2876));
    }
    @Test
    public void d875(){
        assertEq(5,StringDataUtil.getType((char)2877));
        assertEq(0,StringDataUtil.getDirectionality((char)2877));
        assertTrue(StringDataLetterUtil.isLetter((char)2877));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2877));
    }
    @Test
    public void d876(){
        assertEq(8,StringDataUtil.getType((char)2878));
        assertEq(0,StringDataUtil.getDirectionality((char)2878));
        assertFalse(StringDataLetterUtil.isLetter((char)2878));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2878));
    }
    @Test
    public void d877(){
        assertEq(6,StringDataUtil.getType((char)2879));
        assertEq(8,StringDataUtil.getDirectionality((char)2879));
        assertFalse(StringDataLetterUtil.isLetter((char)2879));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2879));
    }
    @Test
    public void d878(){
        assertEq(8,StringDataUtil.getType((char)2880));
        assertEq(0,StringDataUtil.getDirectionality((char)2880));
        assertFalse(StringDataLetterUtil.isLetter((char)2880));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2880));
    }
    @Test
    public void d879(){
        assertEq(6,StringDataUtil.getType((char)2881));
        assertEq(8,StringDataUtil.getDirectionality((char)2881));
        assertFalse(StringDataLetterUtil.isLetter((char)2881));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2881));
    }
    @Test
    public void d880(){
        assertEq(0,StringDataUtil.getType((char)2885));
        assertEq(-1,StringDataUtil.getDirectionality((char)2885));
        assertFalse(StringDataLetterUtil.isLetter((char)2885));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2885));
    }
    @Test
    public void d881(){
        assertEq(8,StringDataUtil.getType((char)2887));
        assertEq(0,StringDataUtil.getDirectionality((char)2887));
        assertFalse(StringDataLetterUtil.isLetter((char)2887));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2887));
    }
    @Test
    public void d882(){
        assertEq(0,StringDataUtil.getType((char)2889));
        assertEq(-1,StringDataUtil.getDirectionality((char)2889));
        assertFalse(StringDataLetterUtil.isLetter((char)2889));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2889));
    }
    @Test
    public void d883(){
        assertEq(8,StringDataUtil.getType((char)2891));
        assertEq(0,StringDataUtil.getDirectionality((char)2891));
        assertFalse(StringDataLetterUtil.isLetter((char)2891));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2891));
    }
    @Test
    public void d884(){
        assertEq(6,StringDataUtil.getType((char)2893));
        assertEq(8,StringDataUtil.getDirectionality((char)2893));
        assertFalse(StringDataLetterUtil.isLetter((char)2893));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2893));
    }
    @Test
    public void d885(){
        assertEq(0,StringDataUtil.getType((char)2894));
        assertEq(-1,StringDataUtil.getDirectionality((char)2894));
        assertFalse(StringDataLetterUtil.isLetter((char)2894));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2894));
    }
    @Test
    public void d886(){
        assertEq(6,StringDataUtil.getType((char)2902));
        assertEq(8,StringDataUtil.getDirectionality((char)2902));
        assertFalse(StringDataLetterUtil.isLetter((char)2902));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2902));
    }
    @Test
    public void d887(){
        assertEq(8,StringDataUtil.getType((char)2903));
        assertEq(0,StringDataUtil.getDirectionality((char)2903));
        assertFalse(StringDataLetterUtil.isLetter((char)2903));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2903));
    }
    @Test
    public void d888(){
        assertEq(0,StringDataUtil.getType((char)2904));
        assertEq(-1,StringDataUtil.getDirectionality((char)2904));
        assertFalse(StringDataLetterUtil.isLetter((char)2904));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2904));
    }
    @Test
    public void d889(){
        assertEq(5,StringDataUtil.getType((char)2908));
        assertEq(0,StringDataUtil.getDirectionality((char)2908));
        assertTrue(StringDataLetterUtil.isLetter((char)2908));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2908));
    }
    @Test
    public void d890(){
        assertEq(0,StringDataUtil.getType((char)2910));
        assertEq(-1,StringDataUtil.getDirectionality((char)2910));
        assertFalse(StringDataLetterUtil.isLetter((char)2910));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2910));
    }
    @Test
    public void d891(){
        assertEq(5,StringDataUtil.getType((char)2911));
        assertEq(0,StringDataUtil.getDirectionality((char)2911));
        assertTrue(StringDataLetterUtil.isLetter((char)2911));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2911));
    }
    @Test
    public void d892(){
        assertEq(6,StringDataUtil.getType((char)2914));
        assertEq(8,StringDataUtil.getDirectionality((char)2914));
        assertFalse(StringDataLetterUtil.isLetter((char)2914));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2914));
    }
    @Test
    public void d893(){
        assertEq(0,StringDataUtil.getType((char)2916));
        assertEq(-1,StringDataUtil.getDirectionality((char)2916));
        assertFalse(StringDataLetterUtil.isLetter((char)2916));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2916));
    }
    @Test
    public void d894(){
        assertEq(9,StringDataUtil.getType((char)2918));
        assertEq(0,StringDataUtil.getDirectionality((char)2918));
        assertFalse(StringDataLetterUtil.isLetter((char)2918));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2918));
    }
    @Test
    public void d895(){
        assertEq(28,StringDataUtil.getType((char)2928));
        assertEq(0,StringDataUtil.getDirectionality((char)2928));
        assertFalse(StringDataLetterUtil.isLetter((char)2928));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2928));
    }
    @Test
    public void d896(){
        assertEq(5,StringDataUtil.getType((char)2929));
        assertEq(0,StringDataUtil.getDirectionality((char)2929));
        assertTrue(StringDataLetterUtil.isLetter((char)2929));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2929));
    }
    @Test
    public void d897(){
        assertEq(11,StringDataUtil.getType((char)2930));
        assertEq(0,StringDataUtil.getDirectionality((char)2930));
        assertFalse(StringDataLetterUtil.isLetter((char)2930));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2930));
    }
    @Test
    public void d898(){
        assertEq(0,StringDataUtil.getType((char)2936));
        assertEq(-1,StringDataUtil.getDirectionality((char)2936));
        assertFalse(StringDataLetterUtil.isLetter((char)2936));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2936));
    }
    @Test
    public void d899(){
        assertEq(6,StringDataUtil.getType((char)2946));
        assertEq(8,StringDataUtil.getDirectionality((char)2946));
        assertFalse(StringDataLetterUtil.isLetter((char)2946));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2946));
    }
    @Test
    public void d900(){
        assertEq(5,StringDataUtil.getType((char)2947));
        assertEq(0,StringDataUtil.getDirectionality((char)2947));
        assertTrue(StringDataLetterUtil.isLetter((char)2947));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2947));
    }
    @Test
    public void d901(){
        assertEq(0,StringDataUtil.getType((char)2948));
        assertEq(-1,StringDataUtil.getDirectionality((char)2948));
        assertFalse(StringDataLetterUtil.isLetter((char)2948));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2948));
    }
    @Test
    public void d902(){
        assertEq(5,StringDataUtil.getType((char)2949));
        assertEq(0,StringDataUtil.getDirectionality((char)2949));
        assertTrue(StringDataLetterUtil.isLetter((char)2949));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2949));
    }
    @Test
    public void d903(){
        assertEq(0,StringDataUtil.getType((char)2955));
        assertEq(-1,StringDataUtil.getDirectionality((char)2955));
        assertFalse(StringDataLetterUtil.isLetter((char)2955));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2955));
    }
    @Test
    public void d904(){
        assertEq(5,StringDataUtil.getType((char)2958));
        assertEq(0,StringDataUtil.getDirectionality((char)2958));
        assertTrue(StringDataLetterUtil.isLetter((char)2958));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2958));
    }
    @Test
    public void d905(){
        assertEq(0,StringDataUtil.getType((char)2961));
        assertEq(-1,StringDataUtil.getDirectionality((char)2961));
        assertFalse(StringDataLetterUtil.isLetter((char)2961));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2961));
    }
    @Test
    public void d906(){
        assertEq(5,StringDataUtil.getType((char)2962));
        assertEq(0,StringDataUtil.getDirectionality((char)2962));
        assertTrue(StringDataLetterUtil.isLetter((char)2962));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2962));
    }
    @Test
    public void d907(){
        assertEq(0,StringDataUtil.getType((char)2966));
        assertEq(-1,StringDataUtil.getDirectionality((char)2966));
        assertFalse(StringDataLetterUtil.isLetter((char)2966));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2966));
    }
    @Test
    public void d908(){
        assertEq(5,StringDataUtil.getType((char)2969));
        assertEq(0,StringDataUtil.getDirectionality((char)2969));
        assertTrue(StringDataLetterUtil.isLetter((char)2969));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2969));
    }
    @Test
    public void d909(){
        assertEq(0,StringDataUtil.getType((char)2971));
        assertEq(-1,StringDataUtil.getDirectionality((char)2971));
        assertFalse(StringDataLetterUtil.isLetter((char)2971));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2971));
    }
    @Test
    public void d910(){
        assertEq(5,StringDataUtil.getType((char)2972));
        assertEq(0,StringDataUtil.getDirectionality((char)2972));
        assertTrue(StringDataLetterUtil.isLetter((char)2972));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2972));
    }
    @Test
    public void d911(){
        assertEq(0,StringDataUtil.getType((char)2973));
        assertEq(-1,StringDataUtil.getDirectionality((char)2973));
        assertFalse(StringDataLetterUtil.isLetter((char)2973));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2973));
    }
    @Test
    public void d912(){
        assertEq(5,StringDataUtil.getType((char)2974));
        assertEq(0,StringDataUtil.getDirectionality((char)2974));
        assertTrue(StringDataLetterUtil.isLetter((char)2974));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2974));
    }
    @Test
    public void d913(){
        assertEq(0,StringDataUtil.getType((char)2976));
        assertEq(-1,StringDataUtil.getDirectionality((char)2976));
        assertFalse(StringDataLetterUtil.isLetter((char)2976));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2976));
    }
    @Test
    public void d914(){
        assertEq(5,StringDataUtil.getType((char)2979));
        assertEq(0,StringDataUtil.getDirectionality((char)2979));
        assertTrue(StringDataLetterUtil.isLetter((char)2979));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2979));
    }
    @Test
    public void d915(){
        assertEq(0,StringDataUtil.getType((char)2981));
        assertEq(-1,StringDataUtil.getDirectionality((char)2981));
        assertFalse(StringDataLetterUtil.isLetter((char)2981));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2981));
    }
    @Test
    public void d916(){
        assertEq(5,StringDataUtil.getType((char)2984));
        assertEq(0,StringDataUtil.getDirectionality((char)2984));
        assertTrue(StringDataLetterUtil.isLetter((char)2984));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2984));
    }
    @Test
    public void d917(){
        assertEq(0,StringDataUtil.getType((char)2987));
        assertEq(-1,StringDataUtil.getDirectionality((char)2987));
        assertFalse(StringDataLetterUtil.isLetter((char)2987));
        assertFalse(StringDataUtil.isLetterOrDigit((char)2987));
    }
    @Test
    public void d918(){
        assertEq(5,StringDataUtil.getType((char)2990));
        assertEq(0,StringDataUtil.getDirectionality((char)2990));
        assertTrue(StringDataLetterUtil.isLetter((char)2990));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2990));
    }
    @Test
    public void d919(){
        assertEq(0,StringDataUtil.getType((char)3002));
        assertEq(-1,StringDataUtil.getDirectionality((char)3002));
        assertFalse(StringDataLetterUtil.isLetter((char)3002));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3002));
    }
    @Test
    public void d920(){
        assertEq(8,StringDataUtil.getType((char)3006));
        assertEq(0,StringDataUtil.getDirectionality((char)3006));
        assertFalse(StringDataLetterUtil.isLetter((char)3006));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3006));
    }
    @Test
    public void d921(){
        assertEq(6,StringDataUtil.getType((char)3008));
        assertEq(8,StringDataUtil.getDirectionality((char)3008));
        assertFalse(StringDataLetterUtil.isLetter((char)3008));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3008));
    }
    @Test
    public void d922(){
        assertEq(8,StringDataUtil.getType((char)3009));
        assertEq(0,StringDataUtil.getDirectionality((char)3009));
        assertFalse(StringDataLetterUtil.isLetter((char)3009));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3009));
    }
    @Test
    public void d923(){
        assertEq(0,StringDataUtil.getType((char)3011));
        assertEq(-1,StringDataUtil.getDirectionality((char)3011));
        assertFalse(StringDataLetterUtil.isLetter((char)3011));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3011));
    }
    @Test
    public void d924(){
        assertEq(8,StringDataUtil.getType((char)3014));
        assertEq(0,StringDataUtil.getDirectionality((char)3014));
        assertFalse(StringDataLetterUtil.isLetter((char)3014));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3014));
    }
    @Test
    public void d925(){
        assertEq(0,StringDataUtil.getType((char)3017));
        assertEq(-1,StringDataUtil.getDirectionality((char)3017));
        assertFalse(StringDataLetterUtil.isLetter((char)3017));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3017));
    }
    @Test
    public void d926(){
        assertEq(8,StringDataUtil.getType((char)3018));
        assertEq(0,StringDataUtil.getDirectionality((char)3018));
        assertFalse(StringDataLetterUtil.isLetter((char)3018));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3018));
    }
    @Test
    public void d927(){
        assertEq(6,StringDataUtil.getType((char)3021));
        assertEq(8,StringDataUtil.getDirectionality((char)3021));
        assertFalse(StringDataLetterUtil.isLetter((char)3021));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3021));
    }
    @Test
    public void d928(){
        assertEq(0,StringDataUtil.getType((char)3022));
        assertEq(-1,StringDataUtil.getDirectionality((char)3022));
        assertFalse(StringDataLetterUtil.isLetter((char)3022));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3022));
    }
    @Test
    public void d929(){
        assertEq(5,StringDataUtil.getType((char)3024));
        assertEq(0,StringDataUtil.getDirectionality((char)3024));
        assertTrue(StringDataLetterUtil.isLetter((char)3024));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3024));
    }
    @Test
    public void d930(){
        assertEq(0,StringDataUtil.getType((char)3025));
        assertEq(-1,StringDataUtil.getDirectionality((char)3025));
        assertFalse(StringDataLetterUtil.isLetter((char)3025));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3025));
    }
    @Test
    public void d931(){
        assertEq(8,StringDataUtil.getType((char)3031));
        assertEq(0,StringDataUtil.getDirectionality((char)3031));
        assertFalse(StringDataLetterUtil.isLetter((char)3031));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3031));
    }
    @Test
    public void d932(){
        assertEq(0,StringDataUtil.getType((char)3032));
        assertEq(-1,StringDataUtil.getDirectionality((char)3032));
        assertFalse(StringDataLetterUtil.isLetter((char)3032));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3032));
    }
    @Test
    public void d933(){
        assertEq(9,StringDataUtil.getType((char)3046));
        assertEq(0,StringDataUtil.getDirectionality((char)3046));
        assertFalse(StringDataLetterUtil.isLetter((char)3046));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3046));
    }
    @Test
    public void d934(){
        assertEq(11,StringDataUtil.getType((char)3056));
        assertEq(0,StringDataUtil.getDirectionality((char)3056));
        assertFalse(StringDataLetterUtil.isLetter((char)3056));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3056));
    }
    @Test
    public void d935(){
        assertEq(28,StringDataUtil.getType((char)3059));
        assertEq(13,StringDataUtil.getDirectionality((char)3059));
        assertFalse(StringDataLetterUtil.isLetter((char)3059));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3059));
    }
    @Test
    public void d936(){
        assertEq(26,StringDataUtil.getType((char)3065));
        assertEq(5,StringDataUtil.getDirectionality((char)3065));
        assertFalse(StringDataLetterUtil.isLetter((char)3065));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3065));
    }
    @Test
    public void d937(){
        assertEq(28,StringDataUtil.getType((char)3066));
        assertEq(13,StringDataUtil.getDirectionality((char)3066));
        assertFalse(StringDataLetterUtil.isLetter((char)3066));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3066));
    }
    @Test
    public void d938(){
        assertEq(0,StringDataUtil.getType((char)3067));
        assertEq(-1,StringDataUtil.getDirectionality((char)3067));
        assertFalse(StringDataLetterUtil.isLetter((char)3067));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3067));
    }
    @Test
    public void d939(){
        assertEq(8,StringDataUtil.getType((char)3073));
        assertEq(0,StringDataUtil.getDirectionality((char)3073));
        assertFalse(StringDataLetterUtil.isLetter((char)3073));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3073));
    }
    @Test
    public void d940(){
        assertEq(0,StringDataUtil.getType((char)3076));
        assertEq(-1,StringDataUtil.getDirectionality((char)3076));
        assertFalse(StringDataLetterUtil.isLetter((char)3076));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3076));
    }
    @Test
    public void d941(){
        assertEq(5,StringDataUtil.getType((char)3077));
        assertEq(0,StringDataUtil.getDirectionality((char)3077));
        assertTrue(StringDataLetterUtil.isLetter((char)3077));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3077));
    }
    @Test
    public void d942(){
        assertEq(0,StringDataUtil.getType((char)3085));
        assertEq(-1,StringDataUtil.getDirectionality((char)3085));
        assertFalse(StringDataLetterUtil.isLetter((char)3085));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3085));
    }
    @Test
    public void d943(){
        assertEq(5,StringDataUtil.getType((char)3086));
        assertEq(0,StringDataUtil.getDirectionality((char)3086));
        assertTrue(StringDataLetterUtil.isLetter((char)3086));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3086));
    }
    @Test
    public void d944(){
        assertEq(0,StringDataUtil.getType((char)3089));
        assertEq(-1,StringDataUtil.getDirectionality((char)3089));
        assertFalse(StringDataLetterUtil.isLetter((char)3089));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3089));
    }
    @Test
    public void d945(){
        assertEq(5,StringDataUtil.getType((char)3090));
        assertEq(0,StringDataUtil.getDirectionality((char)3090));
        assertTrue(StringDataLetterUtil.isLetter((char)3090));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3090));
    }
    @Test
    public void d946(){
        assertEq(0,StringDataUtil.getType((char)3113));
        assertEq(-1,StringDataUtil.getDirectionality((char)3113));
        assertFalse(StringDataLetterUtil.isLetter((char)3113));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3113));
    }
    @Test
    public void d947(){
        assertEq(5,StringDataUtil.getType((char)3114));
        assertEq(0,StringDataUtil.getDirectionality((char)3114));
        assertTrue(StringDataLetterUtil.isLetter((char)3114));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3114));
    }
    @Test
    public void d948(){
        assertEq(0,StringDataUtil.getType((char)3124));
        assertEq(-1,StringDataUtil.getDirectionality((char)3124));
        assertFalse(StringDataLetterUtil.isLetter((char)3124));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3124));
    }
    @Test
    public void d949(){
        assertEq(5,StringDataUtil.getType((char)3125));
        assertEq(0,StringDataUtil.getDirectionality((char)3125));
        assertTrue(StringDataLetterUtil.isLetter((char)3125));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3125));
    }
    @Test
    public void d950(){
        assertEq(0,StringDataUtil.getType((char)3130));
        assertEq(-1,StringDataUtil.getDirectionality((char)3130));
        assertFalse(StringDataLetterUtil.isLetter((char)3130));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3130));
    }
    @Test
    public void d951(){
        assertEq(5,StringDataUtil.getType((char)3133));
        assertEq(0,StringDataUtil.getDirectionality((char)3133));
        assertTrue(StringDataLetterUtil.isLetter((char)3133));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3133));
    }
    @Test
    public void d952(){
        assertEq(6,StringDataUtil.getType((char)3134));
        assertEq(8,StringDataUtil.getDirectionality((char)3134));
        assertFalse(StringDataLetterUtil.isLetter((char)3134));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3134));
    }
    @Test
    public void d953(){
        assertEq(8,StringDataUtil.getType((char)3137));
        assertEq(0,StringDataUtil.getDirectionality((char)3137));
        assertFalse(StringDataLetterUtil.isLetter((char)3137));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3137));
    }
    @Test
    public void d954(){
        assertEq(0,StringDataUtil.getType((char)3141));
        assertEq(-1,StringDataUtil.getDirectionality((char)3141));
        assertFalse(StringDataLetterUtil.isLetter((char)3141));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3141));
    }
    @Test
    public void d955(){
        assertEq(6,StringDataUtil.getType((char)3142));
        assertEq(8,StringDataUtil.getDirectionality((char)3142));
        assertFalse(StringDataLetterUtil.isLetter((char)3142));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3142));
    }
    @Test
    public void d956(){
        assertEq(0,StringDataUtil.getType((char)3145));
        assertEq(-1,StringDataUtil.getDirectionality((char)3145));
        assertFalse(StringDataLetterUtil.isLetter((char)3145));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3145));
    }
    @Test
    public void d957(){
        assertEq(6,StringDataUtil.getType((char)3146));
        assertEq(8,StringDataUtil.getDirectionality((char)3146));
        assertFalse(StringDataLetterUtil.isLetter((char)3146));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3146));
    }
    @Test
    public void d958(){
        assertEq(0,StringDataUtil.getType((char)3150));
        assertEq(-1,StringDataUtil.getDirectionality((char)3150));
        assertFalse(StringDataLetterUtil.isLetter((char)3150));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3150));
    }
    @Test
    public void d959(){
        assertEq(6,StringDataUtil.getType((char)3157));
        assertEq(8,StringDataUtil.getDirectionality((char)3157));
        assertFalse(StringDataLetterUtil.isLetter((char)3157));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3157));
    }
    @Test
    public void d960(){
        assertEq(0,StringDataUtil.getType((char)3159));
        assertEq(-1,StringDataUtil.getDirectionality((char)3159));
        assertFalse(StringDataLetterUtil.isLetter((char)3159));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3159));
    }
    @Test
    public void d961(){
        assertEq(5,StringDataUtil.getType((char)3160));
        assertEq(0,StringDataUtil.getDirectionality((char)3160));
        assertTrue(StringDataLetterUtil.isLetter((char)3160));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3160));
    }
    @Test
    public void d962(){
        assertEq(0,StringDataUtil.getType((char)3162));
        assertEq(-1,StringDataUtil.getDirectionality((char)3162));
        assertFalse(StringDataLetterUtil.isLetter((char)3162));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3162));
    }
    @Test
    public void d963(){
        assertEq(5,StringDataUtil.getType((char)3168));
        assertEq(0,StringDataUtil.getDirectionality((char)3168));
        assertTrue(StringDataLetterUtil.isLetter((char)3168));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3168));
    }
    @Test
    public void d964(){
        assertEq(6,StringDataUtil.getType((char)3170));
        assertEq(8,StringDataUtil.getDirectionality((char)3170));
        assertFalse(StringDataLetterUtil.isLetter((char)3170));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3170));
    }
    @Test
    public void d965(){
        assertEq(0,StringDataUtil.getType((char)3172));
        assertEq(-1,StringDataUtil.getDirectionality((char)3172));
        assertFalse(StringDataLetterUtil.isLetter((char)3172));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3172));
    }
    @Test
    public void d966(){
        assertEq(9,StringDataUtil.getType((char)3174));
        assertEq(0,StringDataUtil.getDirectionality((char)3174));
        assertFalse(StringDataLetterUtil.isLetter((char)3174));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3174));
    }
    @Test
    public void d967(){
        assertEq(0,StringDataUtil.getType((char)3184));
        assertEq(-1,StringDataUtil.getDirectionality((char)3184));
        assertFalse(StringDataLetterUtil.isLetter((char)3184));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3184));
    }
    @Test
    public void d968(){
        assertEq(11,StringDataUtil.getType((char)3192));
        assertEq(13,StringDataUtil.getDirectionality((char)3192));
        assertFalse(StringDataLetterUtil.isLetter((char)3192));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3192));
    }
    @Test
    public void d969(){
        assertEq(28,StringDataUtil.getType((char)3199));
        assertEq(0,StringDataUtil.getDirectionality((char)3199));
        assertFalse(StringDataLetterUtil.isLetter((char)3199));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3199));
    }
    @Test
    public void d970(){
        assertEq(0,StringDataUtil.getType((char)3200));
        assertEq(-1,StringDataUtil.getDirectionality((char)3200));
        assertFalse(StringDataLetterUtil.isLetter((char)3200));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3200));
    }
    @Test
    public void d971(){
        assertEq(8,StringDataUtil.getType((char)3202));
        assertEq(0,StringDataUtil.getDirectionality((char)3202));
        assertFalse(StringDataLetterUtil.isLetter((char)3202));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3202));
    }
    @Test
    public void d972(){
        assertEq(0,StringDataUtil.getType((char)3204));
        assertEq(-1,StringDataUtil.getDirectionality((char)3204));
        assertFalse(StringDataLetterUtil.isLetter((char)3204));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3204));
    }
    @Test
    public void d973(){
        assertEq(5,StringDataUtil.getType((char)3205));
        assertEq(0,StringDataUtil.getDirectionality((char)3205));
        assertTrue(StringDataLetterUtil.isLetter((char)3205));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3205));
    }
    @Test
    public void d974(){
        assertEq(0,StringDataUtil.getType((char)3213));
        assertEq(-1,StringDataUtil.getDirectionality((char)3213));
        assertFalse(StringDataLetterUtil.isLetter((char)3213));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3213));
    }
    @Test
    public void d975(){
        assertEq(5,StringDataUtil.getType((char)3214));
        assertEq(0,StringDataUtil.getDirectionality((char)3214));
        assertTrue(StringDataLetterUtil.isLetter((char)3214));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3214));
    }
    @Test
    public void d976(){
        assertEq(0,StringDataUtil.getType((char)3217));
        assertEq(-1,StringDataUtil.getDirectionality((char)3217));
        assertFalse(StringDataLetterUtil.isLetter((char)3217));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3217));
    }
    @Test
    public void d977(){
        assertEq(5,StringDataUtil.getType((char)3218));
        assertEq(0,StringDataUtil.getDirectionality((char)3218));
        assertTrue(StringDataLetterUtil.isLetter((char)3218));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3218));
    }
    @Test
    public void d978(){
        assertEq(0,StringDataUtil.getType((char)3241));
        assertEq(-1,StringDataUtil.getDirectionality((char)3241));
        assertFalse(StringDataLetterUtil.isLetter((char)3241));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3241));
    }
    @Test
    public void d979(){
        assertEq(5,StringDataUtil.getType((char)3242));
        assertEq(0,StringDataUtil.getDirectionality((char)3242));
        assertTrue(StringDataLetterUtil.isLetter((char)3242));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3242));
    }
    @Test
    public void d980(){
        assertEq(0,StringDataUtil.getType((char)3252));
        assertEq(-1,StringDataUtil.getDirectionality((char)3252));
        assertFalse(StringDataLetterUtil.isLetter((char)3252));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3252));
    }
    @Test
    public void d981(){
        assertEq(5,StringDataUtil.getType((char)3253));
        assertEq(0,StringDataUtil.getDirectionality((char)3253));
        assertTrue(StringDataLetterUtil.isLetter((char)3253));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3253));
    }
    @Test
    public void d982(){
        assertEq(0,StringDataUtil.getType((char)3258));
        assertEq(-1,StringDataUtil.getDirectionality((char)3258));
        assertFalse(StringDataLetterUtil.isLetter((char)3258));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3258));
    }
    @Test
    public void d983(){
        assertEq(6,StringDataUtil.getType((char)3260));
        assertEq(8,StringDataUtil.getDirectionality((char)3260));
        assertFalse(StringDataLetterUtil.isLetter((char)3260));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3260));
    }
    @Test
    public void d984(){
        assertEq(5,StringDataUtil.getType((char)3261));
        assertEq(0,StringDataUtil.getDirectionality((char)3261));
        assertTrue(StringDataLetterUtil.isLetter((char)3261));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3261));
    }
    @Test
    public void d985(){
        assertEq(8,StringDataUtil.getType((char)3262));
        assertEq(0,StringDataUtil.getDirectionality((char)3262));
        assertFalse(StringDataLetterUtil.isLetter((char)3262));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3262));
    }
    @Test
    public void d986(){
        assertEq(6,StringDataUtil.getType((char)3263));
        assertEq(0,StringDataUtil.getDirectionality((char)3263));
        assertFalse(StringDataLetterUtil.isLetter((char)3263));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3263));
    }
    @Test
    public void d987(){
        assertEq(8,StringDataUtil.getType((char)3264));
        assertEq(0,StringDataUtil.getDirectionality((char)3264));
        assertFalse(StringDataLetterUtil.isLetter((char)3264));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3264));
    }
    @Test
    public void d988(){
        assertEq(0,StringDataUtil.getType((char)3269));
        assertEq(-1,StringDataUtil.getDirectionality((char)3269));
        assertFalse(StringDataLetterUtil.isLetter((char)3269));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3269));
    }
    @Test
    public void d989(){
        assertEq(6,StringDataUtil.getType((char)3270));
        assertEq(0,StringDataUtil.getDirectionality((char)3270));
        assertFalse(StringDataLetterUtil.isLetter((char)3270));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3270));
    }
    @Test
    public void d990(){
        assertEq(8,StringDataUtil.getType((char)3271));
        assertEq(0,StringDataUtil.getDirectionality((char)3271));
        assertFalse(StringDataLetterUtil.isLetter((char)3271));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3271));
    }
    @Test
    public void d991(){
        assertEq(0,StringDataUtil.getType((char)3273));
        assertEq(-1,StringDataUtil.getDirectionality((char)3273));
        assertFalse(StringDataLetterUtil.isLetter((char)3273));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3273));
    }
    @Test
    public void d992(){
        assertEq(8,StringDataUtil.getType((char)3274));
        assertEq(0,StringDataUtil.getDirectionality((char)3274));
        assertFalse(StringDataLetterUtil.isLetter((char)3274));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3274));
    }
    @Test
    public void d993(){
        assertEq(6,StringDataUtil.getType((char)3276));
        assertEq(8,StringDataUtil.getDirectionality((char)3276));
        assertFalse(StringDataLetterUtil.isLetter((char)3276));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3276));
    }
    @Test
    public void d994(){
        assertEq(0,StringDataUtil.getType((char)3278));
        assertEq(-1,StringDataUtil.getDirectionality((char)3278));
        assertFalse(StringDataLetterUtil.isLetter((char)3278));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3278));
    }
    @Test
    public void d995(){
        assertEq(8,StringDataUtil.getType((char)3285));
        assertEq(0,StringDataUtil.getDirectionality((char)3285));
        assertFalse(StringDataLetterUtil.isLetter((char)3285));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3285));
    }
    @Test
    public void d996(){
        assertEq(0,StringDataUtil.getType((char)3287));
        assertEq(-1,StringDataUtil.getDirectionality((char)3287));
        assertFalse(StringDataLetterUtil.isLetter((char)3287));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3287));
    }
    @Test
    public void d997(){
        assertEq(5,StringDataUtil.getType((char)3294));
        assertEq(0,StringDataUtil.getDirectionality((char)3294));
        assertTrue(StringDataLetterUtil.isLetter((char)3294));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3294));
    }
    @Test
    public void d998(){
        assertEq(0,StringDataUtil.getType((char)3295));
        assertEq(-1,StringDataUtil.getDirectionality((char)3295));
        assertFalse(StringDataLetterUtil.isLetter((char)3295));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3295));
    }
    @Test
    public void d999(){
        assertEq(5,StringDataUtil.getType((char)3296));
        assertEq(0,StringDataUtil.getDirectionality((char)3296));
        assertTrue(StringDataLetterUtil.isLetter((char)3296));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3296));
    }
    @Test
    public void d1000(){
        assertEq(6,StringDataUtil.getType((char)3298));
        assertEq(8,StringDataUtil.getDirectionality((char)3298));
        assertFalse(StringDataLetterUtil.isLetter((char)3298));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3298));
    }
    @Test
    public void d1001(){
        assertEq(0,StringDataUtil.getType((char)3300));
        assertEq(-1,StringDataUtil.getDirectionality((char)3300));
        assertFalse(StringDataLetterUtil.isLetter((char)3300));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3300));
    }
    @Test
    public void d1002(){
        assertEq(9,StringDataUtil.getType((char)3302));
        assertEq(0,StringDataUtil.getDirectionality((char)3302));
        assertFalse(StringDataLetterUtil.isLetter((char)3302));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3302));
    }
    @Test
    public void d1003(){
        assertEq(0,StringDataUtil.getType((char)3312));
        assertEq(-1,StringDataUtil.getDirectionality((char)3312));
        assertFalse(StringDataLetterUtil.isLetter((char)3312));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3312));
    }
    @Test
    public void d1004(){
        assertEq(5,StringDataUtil.getType((char)3313));
        assertEq(0,StringDataUtil.getDirectionality((char)3313));
        assertTrue(StringDataLetterUtil.isLetter((char)3313));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3313));
    }
    @Test
    public void d1005(){
        assertEq(0,StringDataUtil.getType((char)3315));
        assertEq(-1,StringDataUtil.getDirectionality((char)3315));
        assertFalse(StringDataLetterUtil.isLetter((char)3315));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3315));
    }
    @Test
    public void d1006(){
        assertEq(8,StringDataUtil.getType((char)3330));
        assertEq(0,StringDataUtil.getDirectionality((char)3330));
        assertFalse(StringDataLetterUtil.isLetter((char)3330));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3330));
    }
    @Test
    public void d1007(){
        assertEq(0,StringDataUtil.getType((char)3332));
        assertEq(-1,StringDataUtil.getDirectionality((char)3332));
        assertFalse(StringDataLetterUtil.isLetter((char)3332));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3332));
    }
    @Test
    public void d1008(){
        assertEq(5,StringDataUtil.getType((char)3333));
        assertEq(0,StringDataUtil.getDirectionality((char)3333));
        assertTrue(StringDataLetterUtil.isLetter((char)3333));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3333));
    }
    @Test
    public void d1009(){
        assertEq(0,StringDataUtil.getType((char)3341));
        assertEq(-1,StringDataUtil.getDirectionality((char)3341));
        assertFalse(StringDataLetterUtil.isLetter((char)3341));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3341));
    }
    @Test
    public void d1010(){
        assertEq(5,StringDataUtil.getType((char)3342));
        assertEq(0,StringDataUtil.getDirectionality((char)3342));
        assertTrue(StringDataLetterUtil.isLetter((char)3342));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3342));
    }
    @Test
    public void d1011(){
        assertEq(0,StringDataUtil.getType((char)3345));
        assertEq(-1,StringDataUtil.getDirectionality((char)3345));
        assertFalse(StringDataLetterUtil.isLetter((char)3345));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3345));
    }
    @Test
    public void d1012(){
        assertEq(5,StringDataUtil.getType((char)3346));
        assertEq(0,StringDataUtil.getDirectionality((char)3346));
        assertTrue(StringDataLetterUtil.isLetter((char)3346));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3346));
    }
    @Test
    public void d1013(){
        assertEq(0,StringDataUtil.getType((char)3387));
        assertEq(-1,StringDataUtil.getDirectionality((char)3387));
        assertFalse(StringDataLetterUtil.isLetter((char)3387));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3387));
    }
    @Test
    public void d1014(){
        assertEq(5,StringDataUtil.getType((char)3389));
        assertEq(0,StringDataUtil.getDirectionality((char)3389));
        assertTrue(StringDataLetterUtil.isLetter((char)3389));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3389));
    }
    @Test
    public void d1015(){
        assertEq(8,StringDataUtil.getType((char)3390));
        assertEq(0,StringDataUtil.getDirectionality((char)3390));
        assertFalse(StringDataLetterUtil.isLetter((char)3390));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3390));
    }
    @Test
    public void d1016(){
        assertEq(6,StringDataUtil.getType((char)3393));
        assertEq(8,StringDataUtil.getDirectionality((char)3393));
        assertFalse(StringDataLetterUtil.isLetter((char)3393));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3393));
    }
    @Test
    public void d1017(){
        assertEq(0,StringDataUtil.getType((char)3397));
        assertEq(-1,StringDataUtil.getDirectionality((char)3397));
        assertFalse(StringDataLetterUtil.isLetter((char)3397));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3397));
    }
    @Test
    public void d1018(){
        assertEq(8,StringDataUtil.getType((char)3398));
        assertEq(0,StringDataUtil.getDirectionality((char)3398));
        assertFalse(StringDataLetterUtil.isLetter((char)3398));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3398));
    }
    @Test
    public void d1019(){
        assertEq(0,StringDataUtil.getType((char)3401));
        assertEq(-1,StringDataUtil.getDirectionality((char)3401));
        assertFalse(StringDataLetterUtil.isLetter((char)3401));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3401));
    }
    @Test
    public void d1020(){
        assertEq(8,StringDataUtil.getType((char)3402));
        assertEq(0,StringDataUtil.getDirectionality((char)3402));
        assertFalse(StringDataLetterUtil.isLetter((char)3402));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3402));
    }
    @Test
    public void d1021(){
        assertEq(6,StringDataUtil.getType((char)3405));
        assertEq(8,StringDataUtil.getDirectionality((char)3405));
        assertFalse(StringDataLetterUtil.isLetter((char)3405));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3405));
    }
    @Test
    public void d1022(){
        assertEq(5,StringDataUtil.getType((char)3406));
        assertEq(0,StringDataUtil.getDirectionality((char)3406));
        assertTrue(StringDataLetterUtil.isLetter((char)3406));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3406));
    }
    @Test
    public void d1023(){
        assertEq(0,StringDataUtil.getType((char)3407));
        assertEq(-1,StringDataUtil.getDirectionality((char)3407));
        assertFalse(StringDataLetterUtil.isLetter((char)3407));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3407));
    }
    @Test
    public void d1024(){
        assertEq(8,StringDataUtil.getType((char)3415));
        assertEq(0,StringDataUtil.getDirectionality((char)3415));
        assertFalse(StringDataLetterUtil.isLetter((char)3415));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3415));
    }
    @Test
    public void d1025(){
        assertEq(0,StringDataUtil.getType((char)3416));
        assertEq(-1,StringDataUtil.getDirectionality((char)3416));
        assertFalse(StringDataLetterUtil.isLetter((char)3416));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3416));
    }
    @Test
    public void d1026(){
        assertEq(5,StringDataUtil.getType((char)3424));
        assertEq(0,StringDataUtil.getDirectionality((char)3424));
        assertTrue(StringDataLetterUtil.isLetter((char)3424));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3424));
    }
    @Test
    public void d1027(){
        assertEq(6,StringDataUtil.getType((char)3426));
        assertEq(8,StringDataUtil.getDirectionality((char)3426));
        assertFalse(StringDataLetterUtil.isLetter((char)3426));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3426));
    }
    @Test
    public void d1028(){
        assertEq(0,StringDataUtil.getType((char)3428));
        assertEq(-1,StringDataUtil.getDirectionality((char)3428));
        assertFalse(StringDataLetterUtil.isLetter((char)3428));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3428));
    }
    @Test
    public void d1029(){
        assertEq(9,StringDataUtil.getType((char)3430));
        assertEq(0,StringDataUtil.getDirectionality((char)3430));
        assertFalse(StringDataLetterUtil.isLetter((char)3430));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3430));
    }
    @Test
    public void d1030(){
        assertEq(11,StringDataUtil.getType((char)3440));
        assertEq(0,StringDataUtil.getDirectionality((char)3440));
        assertFalse(StringDataLetterUtil.isLetter((char)3440));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3440));
    }
    @Test
    public void d1031(){
        assertEq(0,StringDataUtil.getType((char)3446));
        assertEq(-1,StringDataUtil.getDirectionality((char)3446));
        assertFalse(StringDataLetterUtil.isLetter((char)3446));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3446));
    }
    @Test
    public void d1032(){
        assertEq(28,StringDataUtil.getType((char)3449));
        assertEq(0,StringDataUtil.getDirectionality((char)3449));
        assertFalse(StringDataLetterUtil.isLetter((char)3449));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3449));
    }
    @Test
    public void d1033(){
        assertEq(5,StringDataUtil.getType((char)3450));
        assertEq(0,StringDataUtil.getDirectionality((char)3450));
        assertTrue(StringDataLetterUtil.isLetter((char)3450));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3450));
    }
    @Test
    public void d1034(){
        assertEq(0,StringDataUtil.getType((char)3456));
        assertEq(-1,StringDataUtil.getDirectionality((char)3456));
        assertFalse(StringDataLetterUtil.isLetter((char)3456));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3456));
    }
    @Test
    public void d1035(){
        assertEq(8,StringDataUtil.getType((char)3458));
        assertEq(0,StringDataUtil.getDirectionality((char)3458));
        assertFalse(StringDataLetterUtil.isLetter((char)3458));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3458));
    }
    @Test
    public void d1036(){
        assertEq(0,StringDataUtil.getType((char)3460));
        assertEq(-1,StringDataUtil.getDirectionality((char)3460));
        assertFalse(StringDataLetterUtil.isLetter((char)3460));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3460));
    }
    @Test
    public void d1037(){
        assertEq(5,StringDataUtil.getType((char)3461));
        assertEq(0,StringDataUtil.getDirectionality((char)3461));
        assertTrue(StringDataLetterUtil.isLetter((char)3461));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3461));
    }
    @Test
    public void d1038(){
        assertEq(0,StringDataUtil.getType((char)3479));
        assertEq(-1,StringDataUtil.getDirectionality((char)3479));
        assertFalse(StringDataLetterUtil.isLetter((char)3479));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3479));
    }
    @Test
    public void d1039(){
        assertEq(5,StringDataUtil.getType((char)3482));
        assertEq(0,StringDataUtil.getDirectionality((char)3482));
        assertTrue(StringDataLetterUtil.isLetter((char)3482));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3482));
    }
    @Test
    public void d1040(){
        assertEq(0,StringDataUtil.getType((char)3506));
        assertEq(-1,StringDataUtil.getDirectionality((char)3506));
        assertFalse(StringDataLetterUtil.isLetter((char)3506));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3506));
    }
    @Test
    public void d1041(){
        assertEq(5,StringDataUtil.getType((char)3507));
        assertEq(0,StringDataUtil.getDirectionality((char)3507));
        assertTrue(StringDataLetterUtil.isLetter((char)3507));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3507));
    }
    @Test
    public void d1042(){
        assertEq(0,StringDataUtil.getType((char)3516));
        assertEq(-1,StringDataUtil.getDirectionality((char)3516));
        assertFalse(StringDataLetterUtil.isLetter((char)3516));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3516));
    }
    @Test
    public void d1043(){
        assertEq(5,StringDataUtil.getType((char)3517));
        assertEq(0,StringDataUtil.getDirectionality((char)3517));
        assertTrue(StringDataLetterUtil.isLetter((char)3517));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3517));
    }
    @Test
    public void d1044(){
        assertEq(0,StringDataUtil.getType((char)3518));
        assertEq(-1,StringDataUtil.getDirectionality((char)3518));
        assertFalse(StringDataLetterUtil.isLetter((char)3518));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3518));
    }
    @Test
    public void d1045(){
        assertEq(5,StringDataUtil.getType((char)3520));
        assertEq(0,StringDataUtil.getDirectionality((char)3520));
        assertTrue(StringDataLetterUtil.isLetter((char)3520));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3520));
    }
    @Test
    public void d1046(){
        assertEq(0,StringDataUtil.getType((char)3527));
        assertEq(-1,StringDataUtil.getDirectionality((char)3527));
        assertFalse(StringDataLetterUtil.isLetter((char)3527));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3527));
    }
    @Test
    public void d1047(){
        assertEq(6,StringDataUtil.getType((char)3530));
        assertEq(8,StringDataUtil.getDirectionality((char)3530));
        assertFalse(StringDataLetterUtil.isLetter((char)3530));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3530));
    }
    @Test
    public void d1048(){
        assertEq(0,StringDataUtil.getType((char)3531));
        assertEq(-1,StringDataUtil.getDirectionality((char)3531));
        assertFalse(StringDataLetterUtil.isLetter((char)3531));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3531));
    }
    @Test
    public void d1049(){
        assertEq(8,StringDataUtil.getType((char)3535));
        assertEq(0,StringDataUtil.getDirectionality((char)3535));
        assertFalse(StringDataLetterUtil.isLetter((char)3535));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3535));
    }
    @Test
    public void d1050(){
        assertEq(6,StringDataUtil.getType((char)3538));
        assertEq(8,StringDataUtil.getDirectionality((char)3538));
        assertFalse(StringDataLetterUtil.isLetter((char)3538));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3538));
    }
    @Test
    public void d1051(){
        assertEq(0,StringDataUtil.getType((char)3541));
        assertEq(-1,StringDataUtil.getDirectionality((char)3541));
        assertFalse(StringDataLetterUtil.isLetter((char)3541));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3541));
    }
    @Test
    public void d1052(){
        assertEq(6,StringDataUtil.getType((char)3542));
        assertEq(8,StringDataUtil.getDirectionality((char)3542));
        assertFalse(StringDataLetterUtil.isLetter((char)3542));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3542));
    }
    @Test
    public void d1053(){
        assertEq(0,StringDataUtil.getType((char)3543));
        assertEq(-1,StringDataUtil.getDirectionality((char)3543));
        assertFalse(StringDataLetterUtil.isLetter((char)3543));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3543));
    }
    @Test
    public void d1054(){
        assertEq(8,StringDataUtil.getType((char)3544));
        assertEq(0,StringDataUtil.getDirectionality((char)3544));
        assertFalse(StringDataLetterUtil.isLetter((char)3544));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3544));
    }
    @Test
    public void d1055(){
        assertEq(0,StringDataUtil.getType((char)3552));
        assertEq(-1,StringDataUtil.getDirectionality((char)3552));
        assertFalse(StringDataLetterUtil.isLetter((char)3552));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3552));
    }
    @Test
    public void d1056(){
        assertEq(8,StringDataUtil.getType((char)3570));
        assertEq(0,StringDataUtil.getDirectionality((char)3570));
        assertFalse(StringDataLetterUtil.isLetter((char)3570));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3570));
    }
    @Test
    public void d1057(){
        assertEq(24,StringDataUtil.getType((char)3572));
        assertEq(0,StringDataUtil.getDirectionality((char)3572));
        assertFalse(StringDataLetterUtil.isLetter((char)3572));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3572));
    }
    @Test
    public void d1058(){
        assertEq(0,StringDataUtil.getType((char)3573));
        assertEq(-1,StringDataUtil.getDirectionality((char)3573));
        assertFalse(StringDataLetterUtil.isLetter((char)3573));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3573));
    }
    @Test
    public void d1059(){
        assertEq(5,StringDataUtil.getType((char)3585));
        assertEq(0,StringDataUtil.getDirectionality((char)3585));
        assertTrue(StringDataLetterUtil.isLetter((char)3585));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3585));
    }
    @Test
    public void d1060(){
        assertEq(6,StringDataUtil.getType((char)3633));
        assertEq(8,StringDataUtil.getDirectionality((char)3633));
        assertFalse(StringDataLetterUtil.isLetter((char)3633));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3633));
    }
    @Test
    public void d1061(){
        assertEq(5,StringDataUtil.getType((char)3634));
        assertEq(0,StringDataUtil.getDirectionality((char)3634));
        assertTrue(StringDataLetterUtil.isLetter((char)3634));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3634));
    }
    @Test
    public void d1062(){
        assertEq(6,StringDataUtil.getType((char)3636));
        assertEq(8,StringDataUtil.getDirectionality((char)3636));
        assertFalse(StringDataLetterUtil.isLetter((char)3636));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3636));
    }
    @Test
    public void d1063(){
        assertEq(0,StringDataUtil.getType((char)3643));
        assertEq(-1,StringDataUtil.getDirectionality((char)3643));
        assertFalse(StringDataLetterUtil.isLetter((char)3643));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3643));
    }
    @Test
    public void d1064(){
        assertEq(26,StringDataUtil.getType((char)3647));
        assertEq(5,StringDataUtil.getDirectionality((char)3647));
        assertFalse(StringDataLetterUtil.isLetter((char)3647));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3647));
    }
    @Test
    public void d1065(){
        assertEq(5,StringDataUtil.getType((char)3648));
        assertEq(0,StringDataUtil.getDirectionality((char)3648));
        assertTrue(StringDataLetterUtil.isLetter((char)3648));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3648));
    }
    @Test
    public void d1066(){
        assertEq(4,StringDataUtil.getType((char)3654));
        assertEq(0,StringDataUtil.getDirectionality((char)3654));
        assertTrue(StringDataLetterUtil.isLetter((char)3654));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3654));
    }
    @Test
    public void d1067(){
        assertEq(6,StringDataUtil.getType((char)3655));
        assertEq(8,StringDataUtil.getDirectionality((char)3655));
        assertFalse(StringDataLetterUtil.isLetter((char)3655));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3655));
    }
    @Test
    public void d1068(){
        assertEq(24,StringDataUtil.getType((char)3663));
        assertEq(0,StringDataUtil.getDirectionality((char)3663));
        assertFalse(StringDataLetterUtil.isLetter((char)3663));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3663));
    }
    @Test
    public void d1069(){
        assertEq(9,StringDataUtil.getType((char)3664));
        assertEq(0,StringDataUtil.getDirectionality((char)3664));
        assertFalse(StringDataLetterUtil.isLetter((char)3664));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3664));
    }
    @Test
    public void d1070(){
        assertEq(24,StringDataUtil.getType((char)3674));
        assertEq(0,StringDataUtil.getDirectionality((char)3674));
        assertFalse(StringDataLetterUtil.isLetter((char)3674));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3674));
    }
    @Test
    public void d1071(){
        assertEq(0,StringDataUtil.getType((char)3676));
        assertEq(-1,StringDataUtil.getDirectionality((char)3676));
        assertFalse(StringDataLetterUtil.isLetter((char)3676));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3676));
    }
    @Test
    public void d1072(){
        assertEq(5,StringDataUtil.getType((char)3713));
        assertEq(0,StringDataUtil.getDirectionality((char)3713));
        assertTrue(StringDataLetterUtil.isLetter((char)3713));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3713));
    }
    @Test
    public void d1073(){
        assertEq(0,StringDataUtil.getType((char)3715));
        assertEq(-1,StringDataUtil.getDirectionality((char)3715));
        assertFalse(StringDataLetterUtil.isLetter((char)3715));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3715));
    }
    @Test
    public void d1074(){
        assertEq(5,StringDataUtil.getType((char)3716));
        assertEq(0,StringDataUtil.getDirectionality((char)3716));
        assertTrue(StringDataLetterUtil.isLetter((char)3716));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3716));
    }
    @Test
    public void d1075(){
        assertEq(0,StringDataUtil.getType((char)3717));
        assertEq(-1,StringDataUtil.getDirectionality((char)3717));
        assertFalse(StringDataLetterUtil.isLetter((char)3717));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3717));
    }
    @Test
    public void d1076(){
        assertEq(5,StringDataUtil.getType((char)3719));
        assertEq(0,StringDataUtil.getDirectionality((char)3719));
        assertTrue(StringDataLetterUtil.isLetter((char)3719));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3719));
    }
    @Test
    public void d1077(){
        assertEq(0,StringDataUtil.getType((char)3721));
        assertEq(-1,StringDataUtil.getDirectionality((char)3721));
        assertFalse(StringDataLetterUtil.isLetter((char)3721));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3721));
    }
    @Test
    public void d1078(){
        assertEq(5,StringDataUtil.getType((char)3722));
        assertEq(0,StringDataUtil.getDirectionality((char)3722));
        assertTrue(StringDataLetterUtil.isLetter((char)3722));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3722));
    }
    @Test
    public void d1079(){
        assertEq(0,StringDataUtil.getType((char)3723));
        assertEq(-1,StringDataUtil.getDirectionality((char)3723));
        assertFalse(StringDataLetterUtil.isLetter((char)3723));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3723));
    }
    @Test
    public void d1080(){
        assertEq(5,StringDataUtil.getType((char)3725));
        assertEq(0,StringDataUtil.getDirectionality((char)3725));
        assertTrue(StringDataLetterUtil.isLetter((char)3725));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3725));
    }
    @Test
    public void d1081(){
        assertEq(0,StringDataUtil.getType((char)3726));
        assertEq(-1,StringDataUtil.getDirectionality((char)3726));
        assertFalse(StringDataLetterUtil.isLetter((char)3726));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3726));
    }
    @Test
    public void d1082(){
        assertEq(5,StringDataUtil.getType((char)3732));
        assertEq(0,StringDataUtil.getDirectionality((char)3732));
        assertTrue(StringDataLetterUtil.isLetter((char)3732));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3732));
    }
    @Test
    public void d1083(){
        assertEq(0,StringDataUtil.getType((char)3736));
        assertEq(-1,StringDataUtil.getDirectionality((char)3736));
        assertFalse(StringDataLetterUtil.isLetter((char)3736));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3736));
    }
    @Test
    public void d1084(){
        assertEq(5,StringDataUtil.getType((char)3737));
        assertEq(0,StringDataUtil.getDirectionality((char)3737));
        assertTrue(StringDataLetterUtil.isLetter((char)3737));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3737));
    }
    @Test
    public void d1085(){
        assertEq(0,StringDataUtil.getType((char)3744));
        assertEq(-1,StringDataUtil.getDirectionality((char)3744));
        assertFalse(StringDataLetterUtil.isLetter((char)3744));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3744));
    }
    @Test
    public void d1086(){
        assertEq(5,StringDataUtil.getType((char)3745));
        assertEq(0,StringDataUtil.getDirectionality((char)3745));
        assertTrue(StringDataLetterUtil.isLetter((char)3745));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3745));
    }
    @Test
    public void d1087(){
        assertEq(0,StringDataUtil.getType((char)3748));
        assertEq(-1,StringDataUtil.getDirectionality((char)3748));
        assertFalse(StringDataLetterUtil.isLetter((char)3748));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3748));
    }
    @Test
    public void d1088(){
        assertEq(5,StringDataUtil.getType((char)3749));
        assertEq(0,StringDataUtil.getDirectionality((char)3749));
        assertTrue(StringDataLetterUtil.isLetter((char)3749));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3749));
    }
    @Test
    public void d1089(){
        assertEq(0,StringDataUtil.getType((char)3750));
        assertEq(-1,StringDataUtil.getDirectionality((char)3750));
        assertFalse(StringDataLetterUtil.isLetter((char)3750));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3750));
    }
    @Test
    public void d1090(){
        assertEq(5,StringDataUtil.getType((char)3751));
        assertEq(0,StringDataUtil.getDirectionality((char)3751));
        assertTrue(StringDataLetterUtil.isLetter((char)3751));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3751));
    }
    @Test
    public void d1091(){
        assertEq(0,StringDataUtil.getType((char)3752));
        assertEq(-1,StringDataUtil.getDirectionality((char)3752));
        assertFalse(StringDataLetterUtil.isLetter((char)3752));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3752));
    }
    @Test
    public void d1092(){
        assertEq(5,StringDataUtil.getType((char)3754));
        assertEq(0,StringDataUtil.getDirectionality((char)3754));
        assertTrue(StringDataLetterUtil.isLetter((char)3754));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3754));
    }
    @Test
    public void d1093(){
        assertEq(0,StringDataUtil.getType((char)3756));
        assertEq(-1,StringDataUtil.getDirectionality((char)3756));
        assertFalse(StringDataLetterUtil.isLetter((char)3756));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3756));
    }
    @Test
    public void d1094(){
        assertEq(5,StringDataUtil.getType((char)3757));
        assertEq(0,StringDataUtil.getDirectionality((char)3757));
        assertTrue(StringDataLetterUtil.isLetter((char)3757));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3757));
    }
    @Test
    public void d1095(){
        assertEq(6,StringDataUtil.getType((char)3761));
        assertEq(8,StringDataUtil.getDirectionality((char)3761));
        assertFalse(StringDataLetterUtil.isLetter((char)3761));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3761));
    }
    @Test
    public void d1096(){
        assertEq(5,StringDataUtil.getType((char)3762));
        assertEq(0,StringDataUtil.getDirectionality((char)3762));
        assertTrue(StringDataLetterUtil.isLetter((char)3762));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3762));
    }
    @Test
    public void d1097(){
        assertEq(6,StringDataUtil.getType((char)3764));
        assertEq(8,StringDataUtil.getDirectionality((char)3764));
        assertFalse(StringDataLetterUtil.isLetter((char)3764));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3764));
    }
    @Test
    public void d1098(){
        assertEq(0,StringDataUtil.getType((char)3770));
        assertEq(-1,StringDataUtil.getDirectionality((char)3770));
        assertFalse(StringDataLetterUtil.isLetter((char)3770));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3770));
    }
    @Test
    public void d1099(){
        assertEq(6,StringDataUtil.getType((char)3771));
        assertEq(8,StringDataUtil.getDirectionality((char)3771));
        assertFalse(StringDataLetterUtil.isLetter((char)3771));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3771));
    }
    @Test
    public void d1100(){
        assertEq(5,StringDataUtil.getType((char)3773));
        assertEq(0,StringDataUtil.getDirectionality((char)3773));
        assertTrue(StringDataLetterUtil.isLetter((char)3773));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3773));
    }
    @Test
    public void d1101(){
        assertEq(0,StringDataUtil.getType((char)3774));
        assertEq(-1,StringDataUtil.getDirectionality((char)3774));
        assertFalse(StringDataLetterUtil.isLetter((char)3774));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3774));
    }
    @Test
    public void d1102(){
        assertEq(5,StringDataUtil.getType((char)3776));
        assertEq(0,StringDataUtil.getDirectionality((char)3776));
        assertTrue(StringDataLetterUtil.isLetter((char)3776));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3776));
    }
    @Test
    public void d1103(){
        assertEq(0,StringDataUtil.getType((char)3781));
        assertEq(-1,StringDataUtil.getDirectionality((char)3781));
        assertFalse(StringDataLetterUtil.isLetter((char)3781));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3781));
    }
    @Test
    public void d1104(){
        assertEq(4,StringDataUtil.getType((char)3782));
        assertEq(0,StringDataUtil.getDirectionality((char)3782));
        assertTrue(StringDataLetterUtil.isLetter((char)3782));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3782));
    }
    @Test
    public void d1105(){
        assertEq(0,StringDataUtil.getType((char)3783));
        assertEq(-1,StringDataUtil.getDirectionality((char)3783));
        assertFalse(StringDataLetterUtil.isLetter((char)3783));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3783));
    }
    @Test
    public void d1106(){
        assertEq(6,StringDataUtil.getType((char)3784));
        assertEq(8,StringDataUtil.getDirectionality((char)3784));
        assertFalse(StringDataLetterUtil.isLetter((char)3784));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3784));
    }
    @Test
    public void d1107(){
        assertEq(0,StringDataUtil.getType((char)3790));
        assertEq(-1,StringDataUtil.getDirectionality((char)3790));
        assertFalse(StringDataLetterUtil.isLetter((char)3790));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3790));
    }
    @Test
    public void d1108(){
        assertEq(9,StringDataUtil.getType((char)3792));
        assertEq(0,StringDataUtil.getDirectionality((char)3792));
        assertFalse(StringDataLetterUtil.isLetter((char)3792));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3792));
    }
    @Test
    public void d1109(){
        assertEq(0,StringDataUtil.getType((char)3802));
        assertEq(-1,StringDataUtil.getDirectionality((char)3802));
        assertFalse(StringDataLetterUtil.isLetter((char)3802));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3802));
    }
    @Test
    public void d1110(){
        assertEq(5,StringDataUtil.getType((char)3804));
        assertEq(0,StringDataUtil.getDirectionality((char)3804));
        assertTrue(StringDataLetterUtil.isLetter((char)3804));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3804));
    }
    @Test
    public void d1111(){
        assertEq(0,StringDataUtil.getType((char)3808));
        assertEq(-1,StringDataUtil.getDirectionality((char)3808));
        assertFalse(StringDataLetterUtil.isLetter((char)3808));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3808));
    }
    @Test
    public void d1112(){
        assertEq(5,StringDataUtil.getType((char)3840));
        assertEq(0,StringDataUtil.getDirectionality((char)3840));
        assertTrue(StringDataLetterUtil.isLetter((char)3840));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3840));
    }
    @Test
    public void d1113(){
        assertEq(28,StringDataUtil.getType((char)3841));
        assertEq(0,StringDataUtil.getDirectionality((char)3841));
        assertFalse(StringDataLetterUtil.isLetter((char)3841));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3841));
    }
    @Test
    public void d1114(){
        assertEq(24,StringDataUtil.getType((char)3844));
        assertEq(0,StringDataUtil.getDirectionality((char)3844));
        assertFalse(StringDataLetterUtil.isLetter((char)3844));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3844));
    }
    @Test
    public void d1115(){
        assertEq(28,StringDataUtil.getType((char)3859));
        assertEq(0,StringDataUtil.getDirectionality((char)3859));
        assertFalse(StringDataLetterUtil.isLetter((char)3859));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3859));
    }
    @Test
    public void d1116(){
        assertEq(24,StringDataUtil.getType((char)3860));
        assertEq(0,StringDataUtil.getDirectionality((char)3860));
        assertFalse(StringDataLetterUtil.isLetter((char)3860));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3860));
    }
    @Test
    public void d1117(){
        assertEq(28,StringDataUtil.getType((char)3861));
        assertEq(0,StringDataUtil.getDirectionality((char)3861));
        assertFalse(StringDataLetterUtil.isLetter((char)3861));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3861));
    }
    @Test
    public void d1118(){
        assertEq(6,StringDataUtil.getType((char)3864));
        assertEq(8,StringDataUtil.getDirectionality((char)3864));
        assertFalse(StringDataLetterUtil.isLetter((char)3864));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3864));
    }
    @Test
    public void d1119(){
        assertEq(28,StringDataUtil.getType((char)3866));
        assertEq(0,StringDataUtil.getDirectionality((char)3866));
        assertFalse(StringDataLetterUtil.isLetter((char)3866));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3866));
    }
    @Test
    public void d1120(){
        assertEq(9,StringDataUtil.getType((char)3872));
        assertEq(0,StringDataUtil.getDirectionality((char)3872));
        assertFalse(StringDataLetterUtil.isLetter((char)3872));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3872));
    }
    @Test
    public void d1121(){
        assertEq(11,StringDataUtil.getType((char)3882));
        assertEq(0,StringDataUtil.getDirectionality((char)3882));
        assertFalse(StringDataLetterUtil.isLetter((char)3882));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3882));
    }
    @Test
    public void d1122(){
        assertEq(28,StringDataUtil.getType((char)3892));
        assertEq(0,StringDataUtil.getDirectionality((char)3892));
        assertFalse(StringDataLetterUtil.isLetter((char)3892));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3892));
    }
    @Test
    public void d1123(){
        assertEq(6,StringDataUtil.getType((char)3893));
        assertEq(8,StringDataUtil.getDirectionality((char)3893));
        assertFalse(StringDataLetterUtil.isLetter((char)3893));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3893));
    }
    @Test
    public void d1124(){
        assertEq(28,StringDataUtil.getType((char)3894));
        assertEq(0,StringDataUtil.getDirectionality((char)3894));
        assertFalse(StringDataLetterUtil.isLetter((char)3894));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3894));
    }
    @Test
    public void d1125(){
        assertEq(6,StringDataUtil.getType((char)3895));
        assertEq(8,StringDataUtil.getDirectionality((char)3895));
        assertFalse(StringDataLetterUtil.isLetter((char)3895));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3895));
    }
    @Test
    public void d1126(){
        assertEq(28,StringDataUtil.getType((char)3896));
        assertEq(0,StringDataUtil.getDirectionality((char)3896));
        assertFalse(StringDataLetterUtil.isLetter((char)3896));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3896));
    }
    @Test
    public void d1127(){
        assertEq(6,StringDataUtil.getType((char)3897));
        assertEq(8,StringDataUtil.getDirectionality((char)3897));
        assertFalse(StringDataLetterUtil.isLetter((char)3897));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3897));
    }
    @Test
    public void d1128(){
        assertEq(21,StringDataUtil.getType((char)3898));
        assertEq(13,StringDataUtil.getDirectionality((char)3898));
        assertFalse(StringDataLetterUtil.isLetter((char)3898));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3898));
    }
    @Test
    public void d1129(){
        assertEq(22,StringDataUtil.getType((char)3899));
        assertEq(13,StringDataUtil.getDirectionality((char)3899));
        assertFalse(StringDataLetterUtil.isLetter((char)3899));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3899));
    }
    @Test
    public void d1130(){
        assertEq(21,StringDataUtil.getType((char)3900));
        assertEq(13,StringDataUtil.getDirectionality((char)3900));
        assertFalse(StringDataLetterUtil.isLetter((char)3900));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3900));
    }
    @Test
    public void d1131(){
        assertEq(22,StringDataUtil.getType((char)3901));
        assertEq(13,StringDataUtil.getDirectionality((char)3901));
        assertFalse(StringDataLetterUtil.isLetter((char)3901));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3901));
    }
    @Test
    public void d1132(){
        assertEq(8,StringDataUtil.getType((char)3902));
        assertEq(0,StringDataUtil.getDirectionality((char)3902));
        assertFalse(StringDataLetterUtil.isLetter((char)3902));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3902));
    }
    @Test
    public void d1133(){
        assertEq(5,StringDataUtil.getType((char)3904));
        assertEq(0,StringDataUtil.getDirectionality((char)3904));
        assertTrue(StringDataLetterUtil.isLetter((char)3904));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3904));
    }
    @Test
    public void d1134(){
        assertEq(0,StringDataUtil.getType((char)3912));
        assertEq(-1,StringDataUtil.getDirectionality((char)3912));
        assertFalse(StringDataLetterUtil.isLetter((char)3912));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3912));
    }
    @Test
    public void d1135(){
        assertEq(5,StringDataUtil.getType((char)3913));
        assertEq(0,StringDataUtil.getDirectionality((char)3913));
        assertTrue(StringDataLetterUtil.isLetter((char)3913));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3913));
    }
    @Test
    public void d1136(){
        assertEq(0,StringDataUtil.getType((char)3949));
        assertEq(-1,StringDataUtil.getDirectionality((char)3949));
        assertFalse(StringDataLetterUtil.isLetter((char)3949));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3949));
    }
    @Test
    public void d1137(){
        assertEq(6,StringDataUtil.getType((char)3953));
        assertEq(8,StringDataUtil.getDirectionality((char)3953));
        assertFalse(StringDataLetterUtil.isLetter((char)3953));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3953));
    }
    @Test
    public void d1138(){
        assertEq(8,StringDataUtil.getType((char)3967));
        assertEq(0,StringDataUtil.getDirectionality((char)3967));
        assertFalse(StringDataLetterUtil.isLetter((char)3967));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3967));
    }
    @Test
    public void d1139(){
        assertEq(6,StringDataUtil.getType((char)3968));
        assertEq(8,StringDataUtil.getDirectionality((char)3968));
        assertFalse(StringDataLetterUtil.isLetter((char)3968));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3968));
    }
    @Test
    public void d1140(){
        assertEq(24,StringDataUtil.getType((char)3973));
        assertEq(0,StringDataUtil.getDirectionality((char)3973));
        assertFalse(StringDataLetterUtil.isLetter((char)3973));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3973));
    }
    @Test
    public void d1141(){
        assertEq(6,StringDataUtil.getType((char)3974));
        assertEq(8,StringDataUtil.getDirectionality((char)3974));
        assertFalse(StringDataLetterUtil.isLetter((char)3974));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3974));
    }
    @Test
    public void d1142(){
        assertEq(5,StringDataUtil.getType((char)3976));
        assertEq(0,StringDataUtil.getDirectionality((char)3976));
        assertTrue(StringDataLetterUtil.isLetter((char)3976));
        assertTrue(StringDataUtil.isLetterOrDigit((char)3976));
    }
    @Test
    public void d1143(){
        assertEq(6,StringDataUtil.getType((char)3981));
        assertEq(8,StringDataUtil.getDirectionality((char)3981));
        assertFalse(StringDataLetterUtil.isLetter((char)3981));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3981));
    }
    @Test
    public void d1144(){
        assertEq(0,StringDataUtil.getType((char)3992));
        assertEq(-1,StringDataUtil.getDirectionality((char)3992));
        assertFalse(StringDataLetterUtil.isLetter((char)3992));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3992));
    }
    @Test
    public void d1145(){
        assertEq(6,StringDataUtil.getType((char)3993));
        assertEq(8,StringDataUtil.getDirectionality((char)3993));
        assertFalse(StringDataLetterUtil.isLetter((char)3993));
        assertFalse(StringDataUtil.isLetterOrDigit((char)3993));
    }
    @Test
    public void d1146(){
        assertEq(0,StringDataUtil.getType((char)4029));
        assertEq(-1,StringDataUtil.getDirectionality((char)4029));
        assertFalse(StringDataLetterUtil.isLetter((char)4029));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4029));
    }
    @Test
    public void d1147(){
        assertEq(28,StringDataUtil.getType((char)4030));
        assertEq(0,StringDataUtil.getDirectionality((char)4030));
        assertFalse(StringDataLetterUtil.isLetter((char)4030));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4030));
    }
    @Test
    public void d1148(){
        assertEq(6,StringDataUtil.getType((char)4038));
        assertEq(8,StringDataUtil.getDirectionality((char)4038));
        assertFalse(StringDataLetterUtil.isLetter((char)4038));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4038));
    }
    @Test
    public void d1149(){
        assertEq(28,StringDataUtil.getType((char)4039));
        assertEq(0,StringDataUtil.getDirectionality((char)4039));
        assertFalse(StringDataLetterUtil.isLetter((char)4039));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4039));
    }
    @Test
    public void d1150(){
        assertEq(0,StringDataUtil.getType((char)4045));
        assertEq(-1,StringDataUtil.getDirectionality((char)4045));
        assertFalse(StringDataLetterUtil.isLetter((char)4045));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4045));
    }
    @Test
    public void d1151(){
        assertEq(28,StringDataUtil.getType((char)4046));
        assertEq(0,StringDataUtil.getDirectionality((char)4046));
        assertFalse(StringDataLetterUtil.isLetter((char)4046));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4046));
    }
    @Test
    public void d1152(){
        assertEq(24,StringDataUtil.getType((char)4048));
        assertEq(0,StringDataUtil.getDirectionality((char)4048));
        assertFalse(StringDataLetterUtil.isLetter((char)4048));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4048));
    }
    @Test
    public void d1153(){
        assertEq(28,StringDataUtil.getType((char)4053));
        assertEq(0,StringDataUtil.getDirectionality((char)4053));
        assertFalse(StringDataLetterUtil.isLetter((char)4053));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4053));
    }
    @Test
    public void d1154(){
        assertEq(24,StringDataUtil.getType((char)4057));
        assertEq(0,StringDataUtil.getDirectionality((char)4057));
        assertFalse(StringDataLetterUtil.isLetter((char)4057));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4057));
    }
    @Test
    public void d1155(){
        assertEq(0,StringDataUtil.getType((char)4059));
        assertEq(-1,StringDataUtil.getDirectionality((char)4059));
        assertFalse(StringDataLetterUtil.isLetter((char)4059));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4059));
    }
    @Test
    public void d1156(){
        assertEq(5,StringDataUtil.getType((char)4096));
        assertEq(0,StringDataUtil.getDirectionality((char)4096));
        assertTrue(StringDataLetterUtil.isLetter((char)4096));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4096));
    }
    @Test
    public void d1157(){
        assertEq(8,StringDataUtil.getType((char)4139));
        assertEq(0,StringDataUtil.getDirectionality((char)4139));
        assertFalse(StringDataLetterUtil.isLetter((char)4139));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4139));
    }
    @Test
    public void d1158(){
        assertEq(6,StringDataUtil.getType((char)4141));
        assertEq(8,StringDataUtil.getDirectionality((char)4141));
        assertFalse(StringDataLetterUtil.isLetter((char)4141));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4141));
    }
    @Test
    public void d1159(){
        assertEq(8,StringDataUtil.getType((char)4145));
        assertEq(0,StringDataUtil.getDirectionality((char)4145));
        assertFalse(StringDataLetterUtil.isLetter((char)4145));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4145));
    }
    @Test
    public void d1160(){
        assertEq(6,StringDataUtil.getType((char)4146));
        assertEq(8,StringDataUtil.getDirectionality((char)4146));
        assertFalse(StringDataLetterUtil.isLetter((char)4146));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4146));
    }
    @Test
    public void d1161(){
        assertEq(8,StringDataUtil.getType((char)4152));
        assertEq(0,StringDataUtil.getDirectionality((char)4152));
        assertFalse(StringDataLetterUtil.isLetter((char)4152));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4152));
    }
    @Test
    public void d1162(){
        assertEq(6,StringDataUtil.getType((char)4153));
        assertEq(8,StringDataUtil.getDirectionality((char)4153));
        assertFalse(StringDataLetterUtil.isLetter((char)4153));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4153));
    }
    @Test
    public void d1163(){
        assertEq(8,StringDataUtil.getType((char)4155));
        assertEq(0,StringDataUtil.getDirectionality((char)4155));
        assertFalse(StringDataLetterUtil.isLetter((char)4155));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4155));
    }
    @Test
    public void d1164(){
        assertEq(6,StringDataUtil.getType((char)4157));
        assertEq(8,StringDataUtil.getDirectionality((char)4157));
        assertFalse(StringDataLetterUtil.isLetter((char)4157));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4157));
    }
    @Test
    public void d1165(){
        assertEq(5,StringDataUtil.getType((char)4159));
        assertEq(0,StringDataUtil.getDirectionality((char)4159));
        assertTrue(StringDataLetterUtil.isLetter((char)4159));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4159));
    }
    @Test
    public void d1166(){
        assertEq(9,StringDataUtil.getType((char)4160));
        assertEq(0,StringDataUtil.getDirectionality((char)4160));
        assertFalse(StringDataLetterUtil.isLetter((char)4160));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4160));
    }
    @Test
    public void d1167(){
        assertEq(24,StringDataUtil.getType((char)4170));
        assertEq(0,StringDataUtil.getDirectionality((char)4170));
        assertFalse(StringDataLetterUtil.isLetter((char)4170));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4170));
    }
    @Test
    public void d1168(){
        assertEq(5,StringDataUtil.getType((char)4176));
        assertEq(0,StringDataUtil.getDirectionality((char)4176));
        assertTrue(StringDataLetterUtil.isLetter((char)4176));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4176));
    }
    @Test
    public void d1169(){
        assertEq(8,StringDataUtil.getType((char)4182));
        assertEq(0,StringDataUtil.getDirectionality((char)4182));
        assertFalse(StringDataLetterUtil.isLetter((char)4182));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4182));
    }
    @Test
    public void d1170(){
        assertEq(6,StringDataUtil.getType((char)4184));
        assertEq(8,StringDataUtil.getDirectionality((char)4184));
        assertFalse(StringDataLetterUtil.isLetter((char)4184));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4184));
    }
    @Test
    public void d1171(){
        assertEq(5,StringDataUtil.getType((char)4186));
        assertEq(0,StringDataUtil.getDirectionality((char)4186));
        assertTrue(StringDataLetterUtil.isLetter((char)4186));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4186));
    }
    @Test
    public void d1172(){
        assertEq(6,StringDataUtil.getType((char)4190));
        assertEq(8,StringDataUtil.getDirectionality((char)4190));
        assertFalse(StringDataLetterUtil.isLetter((char)4190));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4190));
    }
    @Test
    public void d1173(){
        assertEq(5,StringDataUtil.getType((char)4193));
        assertEq(0,StringDataUtil.getDirectionality((char)4193));
        assertTrue(StringDataLetterUtil.isLetter((char)4193));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4193));
    }
    @Test
    public void d1174(){
        assertEq(8,StringDataUtil.getType((char)4194));
        assertEq(0,StringDataUtil.getDirectionality((char)4194));
        assertFalse(StringDataLetterUtil.isLetter((char)4194));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4194));
    }
    @Test
    public void d1175(){
        assertEq(5,StringDataUtil.getType((char)4197));
        assertEq(0,StringDataUtil.getDirectionality((char)4197));
        assertTrue(StringDataLetterUtil.isLetter((char)4197));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4197));
    }
    @Test
    public void d1176(){
        assertEq(8,StringDataUtil.getType((char)4199));
        assertEq(0,StringDataUtil.getDirectionality((char)4199));
        assertFalse(StringDataLetterUtil.isLetter((char)4199));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4199));
    }
    @Test
    public void d1177(){
        assertEq(5,StringDataUtil.getType((char)4206));
        assertEq(0,StringDataUtil.getDirectionality((char)4206));
        assertTrue(StringDataLetterUtil.isLetter((char)4206));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4206));
    }
    @Test
    public void d1178(){
        assertEq(6,StringDataUtil.getType((char)4209));
        assertEq(8,StringDataUtil.getDirectionality((char)4209));
        assertFalse(StringDataLetterUtil.isLetter((char)4209));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4209));
    }
    @Test
    public void d1179(){
        assertEq(5,StringDataUtil.getType((char)4213));
        assertEq(0,StringDataUtil.getDirectionality((char)4213));
        assertTrue(StringDataLetterUtil.isLetter((char)4213));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4213));
    }
    @Test
    public void d1180(){
        assertEq(6,StringDataUtil.getType((char)4226));
        assertEq(8,StringDataUtil.getDirectionality((char)4226));
        assertFalse(StringDataLetterUtil.isLetter((char)4226));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4226));
    }
    @Test
    public void d1181(){
        assertEq(8,StringDataUtil.getType((char)4227));
        assertEq(0,StringDataUtil.getDirectionality((char)4227));
        assertFalse(StringDataLetterUtil.isLetter((char)4227));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4227));
    }
    @Test
    public void d1182(){
        assertEq(6,StringDataUtil.getType((char)4229));
        assertEq(8,StringDataUtil.getDirectionality((char)4229));
        assertFalse(StringDataLetterUtil.isLetter((char)4229));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4229));
    }
    @Test
    public void d1183(){
        assertEq(8,StringDataUtil.getType((char)4231));
        assertEq(0,StringDataUtil.getDirectionality((char)4231));
        assertFalse(StringDataLetterUtil.isLetter((char)4231));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4231));
    }
    @Test
    public void d1184(){
        assertEq(6,StringDataUtil.getType((char)4237));
        assertEq(8,StringDataUtil.getDirectionality((char)4237));
        assertFalse(StringDataLetterUtil.isLetter((char)4237));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4237));
    }
    @Test
    public void d1185(){
        assertEq(5,StringDataUtil.getType((char)4238));
        assertEq(0,StringDataUtil.getDirectionality((char)4238));
        assertTrue(StringDataLetterUtil.isLetter((char)4238));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4238));
    }
    @Test
    public void d1186(){
        assertEq(8,StringDataUtil.getType((char)4239));
        assertEq(0,StringDataUtil.getDirectionality((char)4239));
        assertFalse(StringDataLetterUtil.isLetter((char)4239));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4239));
    }
    @Test
    public void d1187(){
        assertEq(9,StringDataUtil.getType((char)4240));
        assertEq(0,StringDataUtil.getDirectionality((char)4240));
        assertFalse(StringDataLetterUtil.isLetter((char)4240));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4240));
    }
    @Test
    public void d1188(){
        assertEq(8,StringDataUtil.getType((char)4250));
        assertEq(0,StringDataUtil.getDirectionality((char)4250));
        assertFalse(StringDataLetterUtil.isLetter((char)4250));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4250));
    }
    @Test
    public void d1189(){
        assertEq(6,StringDataUtil.getType((char)4253));
        assertEq(8,StringDataUtil.getDirectionality((char)4253));
        assertFalse(StringDataLetterUtil.isLetter((char)4253));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4253));
    }
    @Test
    public void d1190(){
        assertEq(28,StringDataUtil.getType((char)4254));
        assertEq(0,StringDataUtil.getDirectionality((char)4254));
        assertFalse(StringDataLetterUtil.isLetter((char)4254));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4254));
    }
    @Test
    public void d1191(){
        assertEq(1,StringDataUtil.getType((char)4256));
        assertEq(0,StringDataUtil.getDirectionality((char)4256));
        assertTrue(StringDataLetterUtil.isLetter((char)4256));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4256));
    }
    @Test
    public void d1192(){
        assertEq(0,StringDataUtil.getType((char)4294));
        assertEq(-1,StringDataUtil.getDirectionality((char)4294));
        assertFalse(StringDataLetterUtil.isLetter((char)4294));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4294));
    }
    @Test
    public void d1193(){
        assertEq(1,StringDataUtil.getType((char)4295));
        assertEq(0,StringDataUtil.getDirectionality((char)4295));
        assertTrue(StringDataLetterUtil.isLetter((char)4295));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4295));
    }
    @Test
    public void d1194(){
        assertEq(0,StringDataUtil.getType((char)4296));
        assertEq(-1,StringDataUtil.getDirectionality((char)4296));
        assertFalse(StringDataLetterUtil.isLetter((char)4296));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4296));
    }
    @Test
    public void d1195(){
        assertEq(1,StringDataUtil.getType((char)4301));
        assertEq(0,StringDataUtil.getDirectionality((char)4301));
        assertTrue(StringDataLetterUtil.isLetter((char)4301));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4301));
    }
    @Test
    public void d1196(){
        assertEq(0,StringDataUtil.getType((char)4302));
        assertEq(-1,StringDataUtil.getDirectionality((char)4302));
        assertFalse(StringDataLetterUtil.isLetter((char)4302));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4302));
    }
    @Test
    public void d1197(){
        assertEq(5,StringDataUtil.getType((char)4304));
        assertEq(0,StringDataUtil.getDirectionality((char)4304));
        assertTrue(StringDataLetterUtil.isLetter((char)4304));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4304));
    }
    @Test
    public void d1198(){
        assertEq(24,StringDataUtil.getType((char)4347));
        assertEq(0,StringDataUtil.getDirectionality((char)4347));
        assertFalse(StringDataLetterUtil.isLetter((char)4347));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4347));
    }
    @Test
    public void d1199(){
        assertEq(4,StringDataUtil.getType((char)4348));
        assertEq(0,StringDataUtil.getDirectionality((char)4348));
        assertTrue(StringDataLetterUtil.isLetter((char)4348));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4348));
    }
    @Test
    public void d1200(){
        assertEq(5,StringDataUtil.getType((char)4349));
        assertEq(0,StringDataUtil.getDirectionality((char)4349));
        assertTrue(StringDataLetterUtil.isLetter((char)4349));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4349));
    }
    @Test
    public void d1201(){
        assertEq(0,StringDataUtil.getType((char)4681));
        assertEq(-1,StringDataUtil.getDirectionality((char)4681));
        assertFalse(StringDataLetterUtil.isLetter((char)4681));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4681));
    }
    @Test
    public void d1202(){
        assertEq(5,StringDataUtil.getType((char)4682));
        assertEq(0,StringDataUtil.getDirectionality((char)4682));
        assertTrue(StringDataLetterUtil.isLetter((char)4682));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4682));
    }
    @Test
    public void d1203(){
        assertEq(0,StringDataUtil.getType((char)4686));
        assertEq(-1,StringDataUtil.getDirectionality((char)4686));
        assertFalse(StringDataLetterUtil.isLetter((char)4686));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4686));
    }
    @Test
    public void d1204(){
        assertEq(5,StringDataUtil.getType((char)4688));
        assertEq(0,StringDataUtil.getDirectionality((char)4688));
        assertTrue(StringDataLetterUtil.isLetter((char)4688));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4688));
    }
    @Test
    public void d1205(){
        assertEq(0,StringDataUtil.getType((char)4695));
        assertEq(-1,StringDataUtil.getDirectionality((char)4695));
        assertFalse(StringDataLetterUtil.isLetter((char)4695));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4695));
    }
    @Test
    public void d1206(){
        assertEq(5,StringDataUtil.getType((char)4696));
        assertEq(0,StringDataUtil.getDirectionality((char)4696));
        assertTrue(StringDataLetterUtil.isLetter((char)4696));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4696));
    }
    @Test
    public void d1207(){
        assertEq(0,StringDataUtil.getType((char)4697));
        assertEq(-1,StringDataUtil.getDirectionality((char)4697));
        assertFalse(StringDataLetterUtil.isLetter((char)4697));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4697));
    }
    @Test
    public void d1208(){
        assertEq(5,StringDataUtil.getType((char)4698));
        assertEq(0,StringDataUtil.getDirectionality((char)4698));
        assertTrue(StringDataLetterUtil.isLetter((char)4698));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4698));
    }
    @Test
    public void d1209(){
        assertEq(0,StringDataUtil.getType((char)4702));
        assertEq(-1,StringDataUtil.getDirectionality((char)4702));
        assertFalse(StringDataLetterUtil.isLetter((char)4702));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4702));
    }
    @Test
    public void d1210(){
        assertEq(5,StringDataUtil.getType((char)4704));
        assertEq(0,StringDataUtil.getDirectionality((char)4704));
        assertTrue(StringDataLetterUtil.isLetter((char)4704));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4704));
    }
    @Test
    public void d1211(){
        assertEq(0,StringDataUtil.getType((char)4745));
        assertEq(-1,StringDataUtil.getDirectionality((char)4745));
        assertFalse(StringDataLetterUtil.isLetter((char)4745));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4745));
    }
    @Test
    public void d1212(){
        assertEq(5,StringDataUtil.getType((char)4746));
        assertEq(0,StringDataUtil.getDirectionality((char)4746));
        assertTrue(StringDataLetterUtil.isLetter((char)4746));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4746));
    }
    @Test
    public void d1213(){
        assertEq(0,StringDataUtil.getType((char)4750));
        assertEq(-1,StringDataUtil.getDirectionality((char)4750));
        assertFalse(StringDataLetterUtil.isLetter((char)4750));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4750));
    }
    @Test
    public void d1214(){
        assertEq(5,StringDataUtil.getType((char)4752));
        assertEq(0,StringDataUtil.getDirectionality((char)4752));
        assertTrue(StringDataLetterUtil.isLetter((char)4752));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4752));
    }
    @Test
    public void d1215(){
        assertEq(0,StringDataUtil.getType((char)4785));
        assertEq(-1,StringDataUtil.getDirectionality((char)4785));
        assertFalse(StringDataLetterUtil.isLetter((char)4785));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4785));
    }
    @Test
    public void d1216(){
        assertEq(5,StringDataUtil.getType((char)4786));
        assertEq(0,StringDataUtil.getDirectionality((char)4786));
        assertTrue(StringDataLetterUtil.isLetter((char)4786));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4786));
    }
    @Test
    public void d1217(){
        assertEq(0,StringDataUtil.getType((char)4790));
        assertEq(-1,StringDataUtil.getDirectionality((char)4790));
        assertFalse(StringDataLetterUtil.isLetter((char)4790));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4790));
    }
    @Test
    public void d1218(){
        assertEq(5,StringDataUtil.getType((char)4792));
        assertEq(0,StringDataUtil.getDirectionality((char)4792));
        assertTrue(StringDataLetterUtil.isLetter((char)4792));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4792));
    }
    @Test
    public void d1219(){
        assertEq(0,StringDataUtil.getType((char)4799));
        assertEq(-1,StringDataUtil.getDirectionality((char)4799));
        assertFalse(StringDataLetterUtil.isLetter((char)4799));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4799));
    }
    @Test
    public void d1220(){
        assertEq(5,StringDataUtil.getType((char)4800));
        assertEq(0,StringDataUtil.getDirectionality((char)4800));
        assertTrue(StringDataLetterUtil.isLetter((char)4800));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4800));
    }
    @Test
    public void d1221(){
        assertEq(0,StringDataUtil.getType((char)4801));
        assertEq(-1,StringDataUtil.getDirectionality((char)4801));
        assertFalse(StringDataLetterUtil.isLetter((char)4801));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4801));
    }
    @Test
    public void d1222(){
        assertEq(5,StringDataUtil.getType((char)4802));
        assertEq(0,StringDataUtil.getDirectionality((char)4802));
        assertTrue(StringDataLetterUtil.isLetter((char)4802));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4802));
    }
    @Test
    public void d1223(){
        assertEq(0,StringDataUtil.getType((char)4806));
        assertEq(-1,StringDataUtil.getDirectionality((char)4806));
        assertFalse(StringDataLetterUtil.isLetter((char)4806));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4806));
    }
    @Test
    public void d1224(){
        assertEq(5,StringDataUtil.getType((char)4808));
        assertEq(0,StringDataUtil.getDirectionality((char)4808));
        assertTrue(StringDataLetterUtil.isLetter((char)4808));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4808));
    }
    @Test
    public void d1225(){
        assertEq(0,StringDataUtil.getType((char)4823));
        assertEq(-1,StringDataUtil.getDirectionality((char)4823));
        assertFalse(StringDataLetterUtil.isLetter((char)4823));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4823));
    }
    @Test
    public void d1226(){
        assertEq(5,StringDataUtil.getType((char)4824));
        assertEq(0,StringDataUtil.getDirectionality((char)4824));
        assertTrue(StringDataLetterUtil.isLetter((char)4824));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4824));
    }
    @Test
    public void d1227(){
        assertEq(0,StringDataUtil.getType((char)4881));
        assertEq(-1,StringDataUtil.getDirectionality((char)4881));
        assertFalse(StringDataLetterUtil.isLetter((char)4881));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4881));
    }
    @Test
    public void d1228(){
        assertEq(5,StringDataUtil.getType((char)4882));
        assertEq(0,StringDataUtil.getDirectionality((char)4882));
        assertTrue(StringDataLetterUtil.isLetter((char)4882));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4882));
    }
    @Test
    public void d1229(){
        assertEq(0,StringDataUtil.getType((char)4886));
        assertEq(-1,StringDataUtil.getDirectionality((char)4886));
        assertFalse(StringDataLetterUtil.isLetter((char)4886));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4886));
    }
    @Test
    public void d1230(){
        assertEq(5,StringDataUtil.getType((char)4888));
        assertEq(0,StringDataUtil.getDirectionality((char)4888));
        assertTrue(StringDataLetterUtil.isLetter((char)4888));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4888));
    }
    @Test
    public void d1231(){
        assertEq(0,StringDataUtil.getType((char)4955));
        assertEq(-1,StringDataUtil.getDirectionality((char)4955));
        assertFalse(StringDataLetterUtil.isLetter((char)4955));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4955));
    }
    @Test
    public void d1232(){
        assertEq(6,StringDataUtil.getType((char)4957));
        assertEq(8,StringDataUtil.getDirectionality((char)4957));
        assertFalse(StringDataLetterUtil.isLetter((char)4957));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4957));
    }
    @Test
    public void d1233(){
        assertEq(24,StringDataUtil.getType((char)4960));
        assertEq(0,StringDataUtil.getDirectionality((char)4960));
        assertFalse(StringDataLetterUtil.isLetter((char)4960));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4960));
    }
    @Test
    public void d1234(){
        assertEq(11,StringDataUtil.getType((char)4969));
        assertEq(0,StringDataUtil.getDirectionality((char)4969));
        assertFalse(StringDataLetterUtil.isLetter((char)4969));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4969));
    }
    @Test
    public void d1235(){
        assertEq(0,StringDataUtil.getType((char)4989));
        assertEq(-1,StringDataUtil.getDirectionality((char)4989));
        assertFalse(StringDataLetterUtil.isLetter((char)4989));
        assertFalse(StringDataUtil.isLetterOrDigit((char)4989));
    }
    @Test
    public void d1236(){
        assertEq(5,StringDataUtil.getType((char)4992));
        assertEq(0,StringDataUtil.getDirectionality((char)4992));
        assertTrue(StringDataLetterUtil.isLetter((char)4992));
        assertTrue(StringDataUtil.isLetterOrDigit((char)4992));
    }
    @Test
    public void d1237(){
        assertEq(28,StringDataUtil.getType((char)5008));
        assertEq(13,StringDataUtil.getDirectionality((char)5008));
        assertFalse(StringDataLetterUtil.isLetter((char)5008));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5008));
    }
    @Test
    public void d1238(){
        assertEq(0,StringDataUtil.getType((char)5018));
        assertEq(-1,StringDataUtil.getDirectionality((char)5018));
        assertFalse(StringDataLetterUtil.isLetter((char)5018));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5018));
    }
    @Test
    public void d1239(){
        assertEq(5,StringDataUtil.getType((char)5024));
        assertEq(0,StringDataUtil.getDirectionality((char)5024));
        assertTrue(StringDataLetterUtil.isLetter((char)5024));
        assertTrue(StringDataUtil.isLetterOrDigit((char)5024));
    }
    @Test
    public void d1240(){
        assertEq(0,StringDataUtil.getType((char)5109));
        assertEq(-1,StringDataUtil.getDirectionality((char)5109));
        assertFalse(StringDataLetterUtil.isLetter((char)5109));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5109));
    }
    @Test
    public void d1241(){
        assertEq(20,StringDataUtil.getType((char)5120));
        assertEq(13,StringDataUtil.getDirectionality((char)5120));
        assertFalse(StringDataLetterUtil.isLetter((char)5120));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5120));
    }
    @Test
    public void d1242(){
        assertEq(5,StringDataUtil.getType((char)5121));
        assertEq(0,StringDataUtil.getDirectionality((char)5121));
        assertTrue(StringDataLetterUtil.isLetter((char)5121));
        assertTrue(StringDataUtil.isLetterOrDigit((char)5121));
    }
    @Test
    public void d1243(){
        assertEq(24,StringDataUtil.getType((char)5741));
        assertEq(0,StringDataUtil.getDirectionality((char)5741));
        assertFalse(StringDataLetterUtil.isLetter((char)5741));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5741));
    }
    @Test
    public void d1244(){
        assertEq(5,StringDataUtil.getType((char)5743));
        assertEq(0,StringDataUtil.getDirectionality((char)5743));
        assertTrue(StringDataLetterUtil.isLetter((char)5743));
        assertTrue(StringDataUtil.isLetterOrDigit((char)5743));
    }
    @Test
    public void d1245(){
        assertEq(12,StringDataUtil.getType((char)5760));
        assertEq(12,StringDataUtil.getDirectionality((char)5760));
        assertFalse(StringDataLetterUtil.isLetter((char)5760));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5760));
    }
    @Test
    public void d1246(){
        assertEq(5,StringDataUtil.getType((char)5761));
        assertEq(0,StringDataUtil.getDirectionality((char)5761));
        assertTrue(StringDataLetterUtil.isLetter((char)5761));
        assertTrue(StringDataUtil.isLetterOrDigit((char)5761));
    }
    @Test
    public void d1247(){
        assertEq(21,StringDataUtil.getType((char)5787));
        assertEq(13,StringDataUtil.getDirectionality((char)5787));
        assertFalse(StringDataLetterUtil.isLetter((char)5787));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5787));
    }
    @Test
    public void d1248(){
        assertEq(22,StringDataUtil.getType((char)5788));
        assertEq(13,StringDataUtil.getDirectionality((char)5788));
        assertFalse(StringDataLetterUtil.isLetter((char)5788));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5788));
    }
    @Test
    public void d1249(){
        assertEq(0,StringDataUtil.getType((char)5789));
        assertEq(-1,StringDataUtil.getDirectionality((char)5789));
        assertFalse(StringDataLetterUtil.isLetter((char)5789));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5789));
    }
    @Test
    public void d1250(){
        assertEq(5,StringDataUtil.getType((char)5792));
        assertEq(0,StringDataUtil.getDirectionality((char)5792));
        assertTrue(StringDataLetterUtil.isLetter((char)5792));
        assertTrue(StringDataUtil.isLetterOrDigit((char)5792));
    }
    @Test
    public void d1251(){
        assertEq(24,StringDataUtil.getType((char)5867));
        assertEq(0,StringDataUtil.getDirectionality((char)5867));
        assertFalse(StringDataLetterUtil.isLetter((char)5867));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5867));
    }
    @Test
    public void d1252(){
        assertEq(10,StringDataUtil.getType((char)5870));
        assertEq(0,StringDataUtil.getDirectionality((char)5870));
        assertFalse(StringDataLetterUtil.isLetter((char)5870));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5870));
    }
    @Test
    public void d1253(){
        assertEq(0,StringDataUtil.getType((char)5873));
        assertEq(-1,StringDataUtil.getDirectionality((char)5873));
        assertFalse(StringDataLetterUtil.isLetter((char)5873));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5873));
    }
    @Test
    public void d1254(){
        assertEq(5,StringDataUtil.getType((char)5888));
        assertEq(0,StringDataUtil.getDirectionality((char)5888));
        assertTrue(StringDataLetterUtil.isLetter((char)5888));
        assertTrue(StringDataUtil.isLetterOrDigit((char)5888));
    }
    @Test
    public void d1255(){
        assertEq(0,StringDataUtil.getType((char)5901));
        assertEq(-1,StringDataUtil.getDirectionality((char)5901));
        assertFalse(StringDataLetterUtil.isLetter((char)5901));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5901));
    }
    @Test
    public void d1256(){
        assertEq(5,StringDataUtil.getType((char)5902));
        assertEq(0,StringDataUtil.getDirectionality((char)5902));
        assertTrue(StringDataLetterUtil.isLetter((char)5902));
        assertTrue(StringDataUtil.isLetterOrDigit((char)5902));
    }
    @Test
    public void d1257(){
        assertEq(6,StringDataUtil.getType((char)5906));
        assertEq(8,StringDataUtil.getDirectionality((char)5906));
        assertFalse(StringDataLetterUtil.isLetter((char)5906));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5906));
    }
    @Test
    public void d1258(){
        assertEq(0,StringDataUtil.getType((char)5909));
        assertEq(-1,StringDataUtil.getDirectionality((char)5909));
        assertFalse(StringDataLetterUtil.isLetter((char)5909));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5909));
    }
    @Test
    public void d1259(){
        assertEq(5,StringDataUtil.getType((char)5920));
        assertEq(0,StringDataUtil.getDirectionality((char)5920));
        assertTrue(StringDataLetterUtil.isLetter((char)5920));
        assertTrue(StringDataUtil.isLetterOrDigit((char)5920));
    }
    @Test
    public void d1260(){
        assertEq(6,StringDataUtil.getType((char)5938));
        assertEq(8,StringDataUtil.getDirectionality((char)5938));
        assertFalse(StringDataLetterUtil.isLetter((char)5938));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5938));
    }
    @Test
    public void d1261(){
        assertEq(24,StringDataUtil.getType((char)5941));
        assertEq(0,StringDataUtil.getDirectionality((char)5941));
        assertFalse(StringDataLetterUtil.isLetter((char)5941));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5941));
    }
    @Test
    public void d1262(){
        assertEq(0,StringDataUtil.getType((char)5943));
        assertEq(-1,StringDataUtil.getDirectionality((char)5943));
        assertFalse(StringDataLetterUtil.isLetter((char)5943));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5943));
    }
    @Test
    public void d1263(){
        assertEq(5,StringDataUtil.getType((char)5952));
        assertEq(0,StringDataUtil.getDirectionality((char)5952));
        assertTrue(StringDataLetterUtil.isLetter((char)5952));
        assertTrue(StringDataUtil.isLetterOrDigit((char)5952));
    }
    @Test
    public void d1264(){
        assertEq(6,StringDataUtil.getType((char)5970));
        assertEq(8,StringDataUtil.getDirectionality((char)5970));
        assertFalse(StringDataLetterUtil.isLetter((char)5970));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5970));
    }
    @Test
    public void d1265(){
        assertEq(0,StringDataUtil.getType((char)5972));
        assertEq(-1,StringDataUtil.getDirectionality((char)5972));
        assertFalse(StringDataLetterUtil.isLetter((char)5972));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5972));
    }
    @Test
    public void d1266(){
        assertEq(5,StringDataUtil.getType((char)5984));
        assertEq(0,StringDataUtil.getDirectionality((char)5984));
        assertTrue(StringDataLetterUtil.isLetter((char)5984));
        assertTrue(StringDataUtil.isLetterOrDigit((char)5984));
    }
    @Test
    public void d1267(){
        assertEq(0,StringDataUtil.getType((char)5997));
        assertEq(-1,StringDataUtil.getDirectionality((char)5997));
        assertFalse(StringDataLetterUtil.isLetter((char)5997));
        assertFalse(StringDataUtil.isLetterOrDigit((char)5997));
    }
    @Test
    public void d1268(){
        assertEq(5,StringDataUtil.getType((char)5998));
        assertEq(0,StringDataUtil.getDirectionality((char)5998));
        assertTrue(StringDataLetterUtil.isLetter((char)5998));
        assertTrue(StringDataUtil.isLetterOrDigit((char)5998));
    }
    @Test
    public void d1269(){
        assertEq(0,StringDataUtil.getType((char)6001));
        assertEq(-1,StringDataUtil.getDirectionality((char)6001));
        assertFalse(StringDataLetterUtil.isLetter((char)6001));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6001));
    }
    @Test
    public void d1270(){
        assertEq(6,StringDataUtil.getType((char)6002));
        assertEq(8,StringDataUtil.getDirectionality((char)6002));
        assertFalse(StringDataLetterUtil.isLetter((char)6002));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6002));
    }
    @Test
    public void d1271(){
        assertEq(0,StringDataUtil.getType((char)6004));
        assertEq(-1,StringDataUtil.getDirectionality((char)6004));
        assertFalse(StringDataLetterUtil.isLetter((char)6004));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6004));
    }
    @Test
    public void d1272(){
        assertEq(5,StringDataUtil.getType((char)6016));
        assertEq(0,StringDataUtil.getDirectionality((char)6016));
        assertTrue(StringDataLetterUtil.isLetter((char)6016));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6016));
    }
    @Test
    public void d1273(){
        assertEq(6,StringDataUtil.getType((char)6068));
        assertEq(8,StringDataUtil.getDirectionality((char)6068));
        assertFalse(StringDataLetterUtil.isLetter((char)6068));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6068));
    }
    @Test
    public void d1274(){
        assertEq(8,StringDataUtil.getType((char)6070));
        assertEq(0,StringDataUtil.getDirectionality((char)6070));
        assertFalse(StringDataLetterUtil.isLetter((char)6070));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6070));
    }
    @Test
    public void d1275(){
        assertEq(6,StringDataUtil.getType((char)6071));
        assertEq(8,StringDataUtil.getDirectionality((char)6071));
        assertFalse(StringDataLetterUtil.isLetter((char)6071));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6071));
    }
    @Test
    public void d1276(){
        assertEq(8,StringDataUtil.getType((char)6078));
        assertEq(0,StringDataUtil.getDirectionality((char)6078));
        assertFalse(StringDataLetterUtil.isLetter((char)6078));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6078));
    }
    @Test
    public void d1277(){
        assertEq(6,StringDataUtil.getType((char)6086));
        assertEq(8,StringDataUtil.getDirectionality((char)6086));
        assertFalse(StringDataLetterUtil.isLetter((char)6086));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6086));
    }
    @Test
    public void d1278(){
        assertEq(8,StringDataUtil.getType((char)6087));
        assertEq(0,StringDataUtil.getDirectionality((char)6087));
        assertFalse(StringDataLetterUtil.isLetter((char)6087));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6087));
    }
    @Test
    public void d1279(){
        assertEq(6,StringDataUtil.getType((char)6089));
        assertEq(8,StringDataUtil.getDirectionality((char)6089));
        assertFalse(StringDataLetterUtil.isLetter((char)6089));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6089));
    }
    @Test
    public void d1280(){
        assertEq(24,StringDataUtil.getType((char)6100));
        assertEq(0,StringDataUtil.getDirectionality((char)6100));
        assertFalse(StringDataLetterUtil.isLetter((char)6100));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6100));
    }
    @Test
    public void d1281(){
        assertEq(4,StringDataUtil.getType((char)6103));
        assertEq(0,StringDataUtil.getDirectionality((char)6103));
        assertTrue(StringDataLetterUtil.isLetter((char)6103));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6103));
    }
    @Test
    public void d1282(){
        assertEq(24,StringDataUtil.getType((char)6104));
        assertEq(0,StringDataUtil.getDirectionality((char)6104));
        assertFalse(StringDataLetterUtil.isLetter((char)6104));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6104));
    }
    @Test
    public void d1283(){
        assertEq(26,StringDataUtil.getType((char)6107));
        assertEq(5,StringDataUtil.getDirectionality((char)6107));
        assertFalse(StringDataLetterUtil.isLetter((char)6107));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6107));
    }
    @Test
    public void d1284(){
        assertEq(5,StringDataUtil.getType((char)6108));
        assertEq(0,StringDataUtil.getDirectionality((char)6108));
        assertTrue(StringDataLetterUtil.isLetter((char)6108));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6108));
    }
    @Test
    public void d1285(){
        assertEq(6,StringDataUtil.getType((char)6109));
        assertEq(8,StringDataUtil.getDirectionality((char)6109));
        assertFalse(StringDataLetterUtil.isLetter((char)6109));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6109));
    }
    @Test
    public void d1286(){
        assertEq(0,StringDataUtil.getType((char)6110));
        assertEq(-1,StringDataUtil.getDirectionality((char)6110));
        assertFalse(StringDataLetterUtil.isLetter((char)6110));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6110));
    }
    @Test
    public void d1287(){
        assertEq(9,StringDataUtil.getType((char)6112));
        assertEq(0,StringDataUtil.getDirectionality((char)6112));
        assertFalse(StringDataLetterUtil.isLetter((char)6112));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6112));
    }
    @Test
    public void d1288(){
        assertEq(0,StringDataUtil.getType((char)6122));
        assertEq(-1,StringDataUtil.getDirectionality((char)6122));
        assertFalse(StringDataLetterUtil.isLetter((char)6122));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6122));
    }
    @Test
    public void d1289(){
        assertEq(11,StringDataUtil.getType((char)6128));
        assertEq(13,StringDataUtil.getDirectionality((char)6128));
        assertFalse(StringDataLetterUtil.isLetter((char)6128));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6128));
    }
    @Test
    public void d1290(){
        assertEq(0,StringDataUtil.getType((char)6138));
        assertEq(-1,StringDataUtil.getDirectionality((char)6138));
        assertFalse(StringDataLetterUtil.isLetter((char)6138));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6138));
    }
    @Test
    public void d1291(){
        assertEq(24,StringDataUtil.getType((char)6144));
        assertEq(13,StringDataUtil.getDirectionality((char)6144));
        assertFalse(StringDataLetterUtil.isLetter((char)6144));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6144));
    }
    @Test
    public void d1292(){
        assertEq(20,StringDataUtil.getType((char)6150));
        assertEq(13,StringDataUtil.getDirectionality((char)6150));
        assertFalse(StringDataLetterUtil.isLetter((char)6150));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6150));
    }
    @Test
    public void d1293(){
        assertEq(24,StringDataUtil.getType((char)6151));
        assertEq(13,StringDataUtil.getDirectionality((char)6151));
        assertFalse(StringDataLetterUtil.isLetter((char)6151));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6151));
    }
    @Test
    public void d1294(){
        assertEq(6,StringDataUtil.getType((char)6155));
        assertEq(8,StringDataUtil.getDirectionality((char)6155));
        assertFalse(StringDataLetterUtil.isLetter((char)6155));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6155));
    }
    @Test
    public void d1295(){
        assertEq(12,StringDataUtil.getType((char)6158));
        assertEq(12,StringDataUtil.getDirectionality((char)6158));
        assertFalse(StringDataLetterUtil.isLetter((char)6158));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6158));
    }
    @Test
    public void d1296(){
        assertEq(0,StringDataUtil.getType((char)6159));
        assertEq(-1,StringDataUtil.getDirectionality((char)6159));
        assertFalse(StringDataLetterUtil.isLetter((char)6159));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6159));
    }
    @Test
    public void d1297(){
        assertEq(9,StringDataUtil.getType((char)6160));
        assertEq(0,StringDataUtil.getDirectionality((char)6160));
        assertFalse(StringDataLetterUtil.isLetter((char)6160));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6160));
    }
    @Test
    public void d1298(){
        assertEq(0,StringDataUtil.getType((char)6170));
        assertEq(-1,StringDataUtil.getDirectionality((char)6170));
        assertFalse(StringDataLetterUtil.isLetter((char)6170));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6170));
    }
    @Test
    public void d1299(){
        assertEq(5,StringDataUtil.getType((char)6176));
        assertEq(0,StringDataUtil.getDirectionality((char)6176));
        assertTrue(StringDataLetterUtil.isLetter((char)6176));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6176));
    }
    @Test
    public void d1300(){
        assertEq(4,StringDataUtil.getType((char)6211));
        assertEq(0,StringDataUtil.getDirectionality((char)6211));
        assertTrue(StringDataLetterUtil.isLetter((char)6211));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6211));
    }
    @Test
    public void d1301(){
        assertEq(5,StringDataUtil.getType((char)6212));
        assertEq(0,StringDataUtil.getDirectionality((char)6212));
        assertTrue(StringDataLetterUtil.isLetter((char)6212));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6212));
    }
    @Test
    public void d1302(){
        assertEq(0,StringDataUtil.getType((char)6264));
        assertEq(-1,StringDataUtil.getDirectionality((char)6264));
        assertFalse(StringDataLetterUtil.isLetter((char)6264));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6264));
    }
    @Test
    public void d1303(){
        assertEq(5,StringDataUtil.getType((char)6272));
        assertEq(0,StringDataUtil.getDirectionality((char)6272));
        assertTrue(StringDataLetterUtil.isLetter((char)6272));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6272));
    }
    @Test
    public void d1304(){
        assertEq(6,StringDataUtil.getType((char)6313));
        assertEq(8,StringDataUtil.getDirectionality((char)6313));
        assertFalse(StringDataLetterUtil.isLetter((char)6313));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6313));
    }
    @Test
    public void d1305(){
        assertEq(5,StringDataUtil.getType((char)6314));
        assertEq(0,StringDataUtil.getDirectionality((char)6314));
        assertTrue(StringDataLetterUtil.isLetter((char)6314));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6314));
    }
    @Test
    public void d1306(){
        assertEq(0,StringDataUtil.getType((char)6315));
        assertEq(-1,StringDataUtil.getDirectionality((char)6315));
        assertFalse(StringDataLetterUtil.isLetter((char)6315));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6315));
    }
    @Test
    public void d1307(){
        assertEq(5,StringDataUtil.getType((char)6320));
        assertEq(0,StringDataUtil.getDirectionality((char)6320));
        assertTrue(StringDataLetterUtil.isLetter((char)6320));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6320));
    }
    @Test
    public void d1308(){
        assertEq(0,StringDataUtil.getType((char)6390));
        assertEq(-1,StringDataUtil.getDirectionality((char)6390));
        assertFalse(StringDataLetterUtil.isLetter((char)6390));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6390));
    }
    @Test
    public void d1309(){
        assertEq(5,StringDataUtil.getType((char)6400));
        assertEq(0,StringDataUtil.getDirectionality((char)6400));
        assertTrue(StringDataLetterUtil.isLetter((char)6400));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6400));
    }
    @Test
    public void d1310(){
        assertEq(0,StringDataUtil.getType((char)6429));
        assertEq(-1,StringDataUtil.getDirectionality((char)6429));
        assertFalse(StringDataLetterUtil.isLetter((char)6429));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6429));
    }
    @Test
    public void d1311(){
        assertEq(6,StringDataUtil.getType((char)6432));
        assertEq(8,StringDataUtil.getDirectionality((char)6432));
        assertFalse(StringDataLetterUtil.isLetter((char)6432));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6432));
    }
    @Test
    public void d1312(){
        assertEq(8,StringDataUtil.getType((char)6435));
        assertEq(0,StringDataUtil.getDirectionality((char)6435));
        assertFalse(StringDataLetterUtil.isLetter((char)6435));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6435));
    }
    @Test
    public void d1313(){
        assertEq(6,StringDataUtil.getType((char)6439));
        assertEq(8,StringDataUtil.getDirectionality((char)6439));
        assertFalse(StringDataLetterUtil.isLetter((char)6439));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6439));
    }
    @Test
    public void d1314(){
        assertEq(8,StringDataUtil.getType((char)6441));
        assertEq(0,StringDataUtil.getDirectionality((char)6441));
        assertFalse(StringDataLetterUtil.isLetter((char)6441));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6441));
    }
    @Test
    public void d1315(){
        assertEq(0,StringDataUtil.getType((char)6444));
        assertEq(-1,StringDataUtil.getDirectionality((char)6444));
        assertFalse(StringDataLetterUtil.isLetter((char)6444));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6444));
    }
    @Test
    public void d1316(){
        assertEq(8,StringDataUtil.getType((char)6448));
        assertEq(0,StringDataUtil.getDirectionality((char)6448));
        assertFalse(StringDataLetterUtil.isLetter((char)6448));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6448));
    }
    @Test
    public void d1317(){
        assertEq(6,StringDataUtil.getType((char)6450));
        assertEq(8,StringDataUtil.getDirectionality((char)6450));
        assertFalse(StringDataLetterUtil.isLetter((char)6450));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6450));
    }
    @Test
    public void d1318(){
        assertEq(8,StringDataUtil.getType((char)6451));
        assertEq(0,StringDataUtil.getDirectionality((char)6451));
        assertFalse(StringDataLetterUtil.isLetter((char)6451));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6451));
    }
    @Test
    public void d1319(){
        assertEq(6,StringDataUtil.getType((char)6457));
        assertEq(8,StringDataUtil.getDirectionality((char)6457));
        assertFalse(StringDataLetterUtil.isLetter((char)6457));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6457));
    }
    @Test
    public void d1320(){
        assertEq(0,StringDataUtil.getType((char)6460));
        assertEq(-1,StringDataUtil.getDirectionality((char)6460));
        assertFalse(StringDataLetterUtil.isLetter((char)6460));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6460));
    }
    @Test
    public void d1321(){
        assertEq(28,StringDataUtil.getType((char)6464));
        assertEq(13,StringDataUtil.getDirectionality((char)6464));
        assertFalse(StringDataLetterUtil.isLetter((char)6464));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6464));
    }
    @Test
    public void d1322(){
        assertEq(0,StringDataUtil.getType((char)6465));
        assertEq(-1,StringDataUtil.getDirectionality((char)6465));
        assertFalse(StringDataLetterUtil.isLetter((char)6465));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6465));
    }
    @Test
    public void d1323(){
        assertEq(24,StringDataUtil.getType((char)6468));
        assertEq(13,StringDataUtil.getDirectionality((char)6468));
        assertFalse(StringDataLetterUtil.isLetter((char)6468));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6468));
    }
    @Test
    public void d1324(){
        assertEq(9,StringDataUtil.getType((char)6470));
        assertEq(0,StringDataUtil.getDirectionality((char)6470));
        assertFalse(StringDataLetterUtil.isLetter((char)6470));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6470));
    }
    @Test
    public void d1325(){
        assertEq(5,StringDataUtil.getType((char)6480));
        assertEq(0,StringDataUtil.getDirectionality((char)6480));
        assertTrue(StringDataLetterUtil.isLetter((char)6480));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6480));
    }
    @Test
    public void d1326(){
        assertEq(0,StringDataUtil.getType((char)6510));
        assertEq(-1,StringDataUtil.getDirectionality((char)6510));
        assertFalse(StringDataLetterUtil.isLetter((char)6510));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6510));
    }
    @Test
    public void d1327(){
        assertEq(5,StringDataUtil.getType((char)6512));
        assertEq(0,StringDataUtil.getDirectionality((char)6512));
        assertTrue(StringDataLetterUtil.isLetter((char)6512));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6512));
    }
    @Test
    public void d1328(){
        assertEq(0,StringDataUtil.getType((char)6517));
        assertEq(-1,StringDataUtil.getDirectionality((char)6517));
        assertFalse(StringDataLetterUtil.isLetter((char)6517));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6517));
    }
    @Test
    public void d1329(){
        assertEq(5,StringDataUtil.getType((char)6528));
        assertEq(0,StringDataUtil.getDirectionality((char)6528));
        assertTrue(StringDataLetterUtil.isLetter((char)6528));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6528));
    }
    @Test
    public void d1330(){
        assertEq(0,StringDataUtil.getType((char)6572));
        assertEq(-1,StringDataUtil.getDirectionality((char)6572));
        assertFalse(StringDataLetterUtil.isLetter((char)6572));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6572));
    }
    @Test
    public void d1331(){
        assertEq(8,StringDataUtil.getType((char)6576));
        assertEq(0,StringDataUtil.getDirectionality((char)6576));
        assertFalse(StringDataLetterUtil.isLetter((char)6576));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6576));
    }
    @Test
    public void d1332(){
        assertEq(5,StringDataUtil.getType((char)6593));
        assertEq(0,StringDataUtil.getDirectionality((char)6593));
        assertTrue(StringDataLetterUtil.isLetter((char)6593));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6593));
    }
    @Test
    public void d1333(){
        assertEq(8,StringDataUtil.getType((char)6600));
        assertEq(0,StringDataUtil.getDirectionality((char)6600));
        assertFalse(StringDataLetterUtil.isLetter((char)6600));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6600));
    }
    @Test
    public void d1334(){
        assertEq(0,StringDataUtil.getType((char)6602));
        assertEq(-1,StringDataUtil.getDirectionality((char)6602));
        assertFalse(StringDataLetterUtil.isLetter((char)6602));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6602));
    }
    @Test
    public void d1335(){
        assertEq(9,StringDataUtil.getType((char)6608));
        assertEq(0,StringDataUtil.getDirectionality((char)6608));
        assertFalse(StringDataLetterUtil.isLetter((char)6608));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6608));
    }
    @Test
    public void d1336(){
        assertEq(11,StringDataUtil.getType((char)6618));
        assertEq(0,StringDataUtil.getDirectionality((char)6618));
        assertFalse(StringDataLetterUtil.isLetter((char)6618));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6618));
    }
    @Test
    public void d1337(){
        assertEq(0,StringDataUtil.getType((char)6619));
        assertEq(-1,StringDataUtil.getDirectionality((char)6619));
        assertFalse(StringDataLetterUtil.isLetter((char)6619));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6619));
    }
    @Test
    public void d1338(){
        assertEq(28,StringDataUtil.getType((char)6622));
        assertEq(13,StringDataUtil.getDirectionality((char)6622));
        assertFalse(StringDataLetterUtil.isLetter((char)6622));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6622));
    }
    @Test
    public void d1339(){
        assertEq(5,StringDataUtil.getType((char)6656));
        assertEq(0,StringDataUtil.getDirectionality((char)6656));
        assertTrue(StringDataLetterUtil.isLetter((char)6656));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6656));
    }
    @Test
    public void d1340(){
        assertEq(6,StringDataUtil.getType((char)6679));
        assertEq(8,StringDataUtil.getDirectionality((char)6679));
        assertFalse(StringDataLetterUtil.isLetter((char)6679));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6679));
    }
    @Test
    public void d1341(){
        assertEq(8,StringDataUtil.getType((char)6681));
        assertEq(0,StringDataUtil.getDirectionality((char)6681));
        assertFalse(StringDataLetterUtil.isLetter((char)6681));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6681));
    }
    @Test
    public void d1342(){
        assertEq(0,StringDataUtil.getType((char)6684));
        assertEq(-1,StringDataUtil.getDirectionality((char)6684));
        assertFalse(StringDataLetterUtil.isLetter((char)6684));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6684));
    }
    @Test
    public void d1343(){
        assertEq(24,StringDataUtil.getType((char)6686));
        assertEq(0,StringDataUtil.getDirectionality((char)6686));
        assertFalse(StringDataLetterUtil.isLetter((char)6686));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6686));
    }
    @Test
    public void d1344(){
        assertEq(5,StringDataUtil.getType((char)6688));
        assertEq(0,StringDataUtil.getDirectionality((char)6688));
        assertTrue(StringDataLetterUtil.isLetter((char)6688));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6688));
    }
    @Test
    public void d1345(){
        assertEq(8,StringDataUtil.getType((char)6741));
        assertEq(0,StringDataUtil.getDirectionality((char)6741));
        assertFalse(StringDataLetterUtil.isLetter((char)6741));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6741));
    }
    @Test
    public void d1346(){
        assertEq(6,StringDataUtil.getType((char)6742));
        assertEq(8,StringDataUtil.getDirectionality((char)6742));
        assertFalse(StringDataLetterUtil.isLetter((char)6742));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6742));
    }
    @Test
    public void d1347(){
        assertEq(8,StringDataUtil.getType((char)6743));
        assertEq(0,StringDataUtil.getDirectionality((char)6743));
        assertFalse(StringDataLetterUtil.isLetter((char)6743));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6743));
    }
    @Test
    public void d1348(){
        assertEq(6,StringDataUtil.getType((char)6744));
        assertEq(8,StringDataUtil.getDirectionality((char)6744));
        assertFalse(StringDataLetterUtil.isLetter((char)6744));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6744));
    }
    @Test
    public void d1349(){
        assertEq(0,StringDataUtil.getType((char)6751));
        assertEq(-1,StringDataUtil.getDirectionality((char)6751));
        assertFalse(StringDataLetterUtil.isLetter((char)6751));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6751));
    }
    @Test
    public void d1350(){
        assertEq(6,StringDataUtil.getType((char)6752));
        assertEq(8,StringDataUtil.getDirectionality((char)6752));
        assertFalse(StringDataLetterUtil.isLetter((char)6752));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6752));
    }
    @Test
    public void d1351(){
        assertEq(8,StringDataUtil.getType((char)6753));
        assertEq(0,StringDataUtil.getDirectionality((char)6753));
        assertFalse(StringDataLetterUtil.isLetter((char)6753));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6753));
    }
    @Test
    public void d1352(){
        assertEq(6,StringDataUtil.getType((char)6754));
        assertEq(8,StringDataUtil.getDirectionality((char)6754));
        assertFalse(StringDataLetterUtil.isLetter((char)6754));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6754));
    }
    @Test
    public void d1353(){
        assertEq(8,StringDataUtil.getType((char)6755));
        assertEq(0,StringDataUtil.getDirectionality((char)6755));
        assertFalse(StringDataLetterUtil.isLetter((char)6755));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6755));
    }
    @Test
    public void d1354(){
        assertEq(6,StringDataUtil.getType((char)6757));
        assertEq(8,StringDataUtil.getDirectionality((char)6757));
        assertFalse(StringDataLetterUtil.isLetter((char)6757));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6757));
    }
    @Test
    public void d1355(){
        assertEq(8,StringDataUtil.getType((char)6765));
        assertEq(0,StringDataUtil.getDirectionality((char)6765));
        assertFalse(StringDataLetterUtil.isLetter((char)6765));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6765));
    }
    @Test
    public void d1356(){
        assertEq(6,StringDataUtil.getType((char)6771));
        assertEq(8,StringDataUtil.getDirectionality((char)6771));
        assertFalse(StringDataLetterUtil.isLetter((char)6771));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6771));
    }
    @Test
    public void d1357(){
        assertEq(0,StringDataUtil.getType((char)6781));
        assertEq(-1,StringDataUtil.getDirectionality((char)6781));
        assertFalse(StringDataLetterUtil.isLetter((char)6781));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6781));
    }
    @Test
    public void d1358(){
        assertEq(6,StringDataUtil.getType((char)6783));
        assertEq(8,StringDataUtil.getDirectionality((char)6783));
        assertFalse(StringDataLetterUtil.isLetter((char)6783));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6783));
    }
    @Test
    public void d1359(){
        assertEq(9,StringDataUtil.getType((char)6784));
        assertEq(0,StringDataUtil.getDirectionality((char)6784));
        assertFalse(StringDataLetterUtil.isLetter((char)6784));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6784));
    }
    @Test
    public void d1360(){
        assertEq(0,StringDataUtil.getType((char)6794));
        assertEq(-1,StringDataUtil.getDirectionality((char)6794));
        assertFalse(StringDataLetterUtil.isLetter((char)6794));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6794));
    }
    @Test
    public void d1361(){
        assertEq(9,StringDataUtil.getType((char)6800));
        assertEq(0,StringDataUtil.getDirectionality((char)6800));
        assertFalse(StringDataLetterUtil.isLetter((char)6800));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6800));
    }
    @Test
    public void d1362(){
        assertEq(0,StringDataUtil.getType((char)6810));
        assertEq(-1,StringDataUtil.getDirectionality((char)6810));
        assertFalse(StringDataLetterUtil.isLetter((char)6810));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6810));
    }
    @Test
    public void d1363(){
        assertEq(24,StringDataUtil.getType((char)6816));
        assertEq(0,StringDataUtil.getDirectionality((char)6816));
        assertFalse(StringDataLetterUtil.isLetter((char)6816));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6816));
    }
    @Test
    public void d1364(){
        assertEq(4,StringDataUtil.getType((char)6823));
        assertEq(0,StringDataUtil.getDirectionality((char)6823));
        assertTrue(StringDataLetterUtil.isLetter((char)6823));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6823));
    }
    @Test
    public void d1365(){
        assertEq(24,StringDataUtil.getType((char)6824));
        assertEq(0,StringDataUtil.getDirectionality((char)6824));
        assertFalse(StringDataLetterUtil.isLetter((char)6824));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6824));
    }
    @Test
    public void d1366(){
        assertEq(0,StringDataUtil.getType((char)6830));
        assertEq(-1,StringDataUtil.getDirectionality((char)6830));
        assertFalse(StringDataLetterUtil.isLetter((char)6830));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6830));
    }
    @Test
    public void d1367(){
        assertEq(6,StringDataUtil.getType((char)6912));
        assertEq(8,StringDataUtil.getDirectionality((char)6912));
        assertFalse(StringDataLetterUtil.isLetter((char)6912));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6912));
    }
    @Test
    public void d1368(){
        assertEq(8,StringDataUtil.getType((char)6916));
        assertEq(0,StringDataUtil.getDirectionality((char)6916));
        assertFalse(StringDataLetterUtil.isLetter((char)6916));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6916));
    }
    @Test
    public void d1369(){
        assertEq(5,StringDataUtil.getType((char)6917));
        assertEq(0,StringDataUtil.getDirectionality((char)6917));
        assertTrue(StringDataLetterUtil.isLetter((char)6917));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6917));
    }
    @Test
    public void d1370(){
        assertEq(6,StringDataUtil.getType((char)6964));
        assertEq(8,StringDataUtil.getDirectionality((char)6964));
        assertFalse(StringDataLetterUtil.isLetter((char)6964));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6964));
    }
    @Test
    public void d1371(){
        assertEq(8,StringDataUtil.getType((char)6965));
        assertEq(0,StringDataUtil.getDirectionality((char)6965));
        assertFalse(StringDataLetterUtil.isLetter((char)6965));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6965));
    }
    @Test
    public void d1372(){
        assertEq(6,StringDataUtil.getType((char)6966));
        assertEq(8,StringDataUtil.getDirectionality((char)6966));
        assertFalse(StringDataLetterUtil.isLetter((char)6966));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6966));
    }
    @Test
    public void d1373(){
        assertEq(8,StringDataUtil.getType((char)6971));
        assertEq(0,StringDataUtil.getDirectionality((char)6971));
        assertFalse(StringDataLetterUtil.isLetter((char)6971));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6971));
    }
    @Test
    public void d1374(){
        assertEq(6,StringDataUtil.getType((char)6972));
        assertEq(8,StringDataUtil.getDirectionality((char)6972));
        assertFalse(StringDataLetterUtil.isLetter((char)6972));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6972));
    }
    @Test
    public void d1375(){
        assertEq(8,StringDataUtil.getType((char)6973));
        assertEq(0,StringDataUtil.getDirectionality((char)6973));
        assertFalse(StringDataLetterUtil.isLetter((char)6973));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6973));
    }
    @Test
    public void d1376(){
        assertEq(6,StringDataUtil.getType((char)6978));
        assertEq(8,StringDataUtil.getDirectionality((char)6978));
        assertFalse(StringDataLetterUtil.isLetter((char)6978));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6978));
    }
    @Test
    public void d1377(){
        assertEq(8,StringDataUtil.getType((char)6979));
        assertEq(0,StringDataUtil.getDirectionality((char)6979));
        assertFalse(StringDataLetterUtil.isLetter((char)6979));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6979));
    }
    @Test
    public void d1378(){
        assertEq(5,StringDataUtil.getType((char)6981));
        assertEq(0,StringDataUtil.getDirectionality((char)6981));
        assertTrue(StringDataLetterUtil.isLetter((char)6981));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6981));
    }
    @Test
    public void d1379(){
        assertEq(0,StringDataUtil.getType((char)6988));
        assertEq(-1,StringDataUtil.getDirectionality((char)6988));
        assertFalse(StringDataLetterUtil.isLetter((char)6988));
        assertFalse(StringDataUtil.isLetterOrDigit((char)6988));
    }
    @Test
    public void d1380(){
        assertEq(9,StringDataUtil.getType((char)6992));
        assertEq(0,StringDataUtil.getDirectionality((char)6992));
        assertFalse(StringDataLetterUtil.isLetter((char)6992));
        assertTrue(StringDataUtil.isLetterOrDigit((char)6992));
    }
    @Test
    public void d1381(){
        assertEq(24,StringDataUtil.getType((char)7002));
        assertEq(0,StringDataUtil.getDirectionality((char)7002));
        assertFalse(StringDataLetterUtil.isLetter((char)7002));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7002));
    }
    @Test
    public void d1382(){
        assertEq(28,StringDataUtil.getType((char)7009));
        assertEq(0,StringDataUtil.getDirectionality((char)7009));
        assertFalse(StringDataLetterUtil.isLetter((char)7009));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7009));
    }
    @Test
    public void d1383(){
        assertEq(6,StringDataUtil.getType((char)7019));
        assertEq(8,StringDataUtil.getDirectionality((char)7019));
        assertFalse(StringDataLetterUtil.isLetter((char)7019));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7019));
    }
    @Test
    public void d1384(){
        assertEq(28,StringDataUtil.getType((char)7028));
        assertEq(0,StringDataUtil.getDirectionality((char)7028));
        assertFalse(StringDataLetterUtil.isLetter((char)7028));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7028));
    }
    @Test
    public void d1385(){
        assertEq(0,StringDataUtil.getType((char)7037));
        assertEq(-1,StringDataUtil.getDirectionality((char)7037));
        assertFalse(StringDataLetterUtil.isLetter((char)7037));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7037));
    }
    @Test
    public void d1386(){
        assertEq(6,StringDataUtil.getType((char)7040));
        assertEq(8,StringDataUtil.getDirectionality((char)7040));
        assertFalse(StringDataLetterUtil.isLetter((char)7040));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7040));
    }
    @Test
    public void d1387(){
        assertEq(8,StringDataUtil.getType((char)7042));
        assertEq(0,StringDataUtil.getDirectionality((char)7042));
        assertFalse(StringDataLetterUtil.isLetter((char)7042));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7042));
    }
    @Test
    public void d1388(){
        assertEq(5,StringDataUtil.getType((char)7043));
        assertEq(0,StringDataUtil.getDirectionality((char)7043));
        assertTrue(StringDataLetterUtil.isLetter((char)7043));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7043));
    }
    @Test
    public void d1389(){
        assertEq(8,StringDataUtil.getType((char)7073));
        assertEq(0,StringDataUtil.getDirectionality((char)7073));
        assertFalse(StringDataLetterUtil.isLetter((char)7073));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7073));
    }
    @Test
    public void d1390(){
        assertEq(6,StringDataUtil.getType((char)7074));
        assertEq(8,StringDataUtil.getDirectionality((char)7074));
        assertFalse(StringDataLetterUtil.isLetter((char)7074));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7074));
    }
    @Test
    public void d1391(){
        assertEq(8,StringDataUtil.getType((char)7078));
        assertEq(0,StringDataUtil.getDirectionality((char)7078));
        assertFalse(StringDataLetterUtil.isLetter((char)7078));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7078));
    }
    @Test
    public void d1392(){
        assertEq(6,StringDataUtil.getType((char)7080));
        assertEq(8,StringDataUtil.getDirectionality((char)7080));
        assertFalse(StringDataLetterUtil.isLetter((char)7080));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7080));
    }
    @Test
    public void d1393(){
        assertEq(8,StringDataUtil.getType((char)7082));
        assertEq(0,StringDataUtil.getDirectionality((char)7082));
        assertFalse(StringDataLetterUtil.isLetter((char)7082));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7082));
    }
    @Test
    public void d1394(){
        assertEq(6,StringDataUtil.getType((char)7083));
        assertEq(8,StringDataUtil.getDirectionality((char)7083));
        assertFalse(StringDataLetterUtil.isLetter((char)7083));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7083));
    }
    @Test
    public void d1395(){
        assertEq(8,StringDataUtil.getType((char)7084));
        assertEq(0,StringDataUtil.getDirectionality((char)7084));
        assertFalse(StringDataLetterUtil.isLetter((char)7084));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7084));
    }
    @Test
    public void d1396(){
        assertEq(5,StringDataUtil.getType((char)7086));
        assertEq(0,StringDataUtil.getDirectionality((char)7086));
        assertTrue(StringDataLetterUtil.isLetter((char)7086));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7086));
    }
    @Test
    public void d1397(){
        assertEq(9,StringDataUtil.getType((char)7088));
        assertEq(0,StringDataUtil.getDirectionality((char)7088));
        assertFalse(StringDataLetterUtil.isLetter((char)7088));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7088));
    }
    @Test
    public void d1398(){
        assertEq(5,StringDataUtil.getType((char)7098));
        assertEq(0,StringDataUtil.getDirectionality((char)7098));
        assertTrue(StringDataLetterUtil.isLetter((char)7098));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7098));
    }
    @Test
    public void d1399(){
        assertEq(6,StringDataUtil.getType((char)7142));
        assertEq(8,StringDataUtil.getDirectionality((char)7142));
        assertFalse(StringDataLetterUtil.isLetter((char)7142));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7142));
    }
    @Test
    public void d1400(){
        assertEq(8,StringDataUtil.getType((char)7143));
        assertEq(0,StringDataUtil.getDirectionality((char)7143));
        assertFalse(StringDataLetterUtil.isLetter((char)7143));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7143));
    }
    @Test
    public void d1401(){
        assertEq(6,StringDataUtil.getType((char)7144));
        assertEq(8,StringDataUtil.getDirectionality((char)7144));
        assertFalse(StringDataLetterUtil.isLetter((char)7144));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7144));
    }
    @Test
    public void d1402(){
        assertEq(8,StringDataUtil.getType((char)7146));
        assertEq(0,StringDataUtil.getDirectionality((char)7146));
        assertFalse(StringDataLetterUtil.isLetter((char)7146));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7146));
    }
    @Test
    public void d1403(){
        assertEq(6,StringDataUtil.getType((char)7149));
        assertEq(8,StringDataUtil.getDirectionality((char)7149));
        assertFalse(StringDataLetterUtil.isLetter((char)7149));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7149));
    }
    @Test
    public void d1404(){
        assertEq(8,StringDataUtil.getType((char)7150));
        assertEq(0,StringDataUtil.getDirectionality((char)7150));
        assertFalse(StringDataLetterUtil.isLetter((char)7150));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7150));
    }
    @Test
    public void d1405(){
        assertEq(6,StringDataUtil.getType((char)7151));
        assertEq(8,StringDataUtil.getDirectionality((char)7151));
        assertFalse(StringDataLetterUtil.isLetter((char)7151));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7151));
    }
    @Test
    public void d1406(){
        assertEq(8,StringDataUtil.getType((char)7154));
        assertEq(0,StringDataUtil.getDirectionality((char)7154));
        assertFalse(StringDataLetterUtil.isLetter((char)7154));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7154));
    }
    @Test
    public void d1407(){
        assertEq(0,StringDataUtil.getType((char)7156));
        assertEq(-1,StringDataUtil.getDirectionality((char)7156));
        assertFalse(StringDataLetterUtil.isLetter((char)7156));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7156));
    }
    @Test
    public void d1408(){
        assertEq(24,StringDataUtil.getType((char)7164));
        assertEq(0,StringDataUtil.getDirectionality((char)7164));
        assertFalse(StringDataLetterUtil.isLetter((char)7164));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7164));
    }
    @Test
    public void d1409(){
        assertEq(5,StringDataUtil.getType((char)7168));
        assertEq(0,StringDataUtil.getDirectionality((char)7168));
        assertTrue(StringDataLetterUtil.isLetter((char)7168));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7168));
    }
    @Test
    public void d1410(){
        assertEq(8,StringDataUtil.getType((char)7204));
        assertEq(0,StringDataUtil.getDirectionality((char)7204));
        assertFalse(StringDataLetterUtil.isLetter((char)7204));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7204));
    }
    @Test
    public void d1411(){
        assertEq(6,StringDataUtil.getType((char)7212));
        assertEq(8,StringDataUtil.getDirectionality((char)7212));
        assertFalse(StringDataLetterUtil.isLetter((char)7212));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7212));
    }
    @Test
    public void d1412(){
        assertEq(8,StringDataUtil.getType((char)7220));
        assertEq(0,StringDataUtil.getDirectionality((char)7220));
        assertFalse(StringDataLetterUtil.isLetter((char)7220));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7220));
    }
    @Test
    public void d1413(){
        assertEq(6,StringDataUtil.getType((char)7222));
        assertEq(8,StringDataUtil.getDirectionality((char)7222));
        assertFalse(StringDataLetterUtil.isLetter((char)7222));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7222));
    }
    @Test
    public void d1414(){
        assertEq(0,StringDataUtil.getType((char)7224));
        assertEq(-1,StringDataUtil.getDirectionality((char)7224));
        assertFalse(StringDataLetterUtil.isLetter((char)7224));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7224));
    }
    @Test
    public void d1415(){
        assertEq(24,StringDataUtil.getType((char)7227));
        assertEq(0,StringDataUtil.getDirectionality((char)7227));
        assertFalse(StringDataLetterUtil.isLetter((char)7227));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7227));
    }
    @Test
    public void d1416(){
        assertEq(9,StringDataUtil.getType((char)7232));
        assertEq(0,StringDataUtil.getDirectionality((char)7232));
        assertFalse(StringDataLetterUtil.isLetter((char)7232));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7232));
    }
    @Test
    public void d1417(){
        assertEq(0,StringDataUtil.getType((char)7242));
        assertEq(-1,StringDataUtil.getDirectionality((char)7242));
        assertFalse(StringDataLetterUtil.isLetter((char)7242));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7242));
    }
    @Test
    public void d1418(){
        assertEq(5,StringDataUtil.getType((char)7245));
        assertEq(0,StringDataUtil.getDirectionality((char)7245));
        assertTrue(StringDataLetterUtil.isLetter((char)7245));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7245));
    }
    @Test
    public void d1419(){
        assertEq(9,StringDataUtil.getType((char)7248));
        assertEq(0,StringDataUtil.getDirectionality((char)7248));
        assertFalse(StringDataLetterUtil.isLetter((char)7248));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7248));
    }
    @Test
    public void d1420(){
        assertEq(5,StringDataUtil.getType((char)7258));
        assertEq(0,StringDataUtil.getDirectionality((char)7258));
        assertTrue(StringDataLetterUtil.isLetter((char)7258));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7258));
    }
    @Test
    public void d1421(){
        assertEq(4,StringDataUtil.getType((char)7288));
        assertEq(0,StringDataUtil.getDirectionality((char)7288));
        assertTrue(StringDataLetterUtil.isLetter((char)7288));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7288));
    }
    @Test
    public void d1422(){
        assertEq(24,StringDataUtil.getType((char)7294));
        assertEq(0,StringDataUtil.getDirectionality((char)7294));
        assertFalse(StringDataLetterUtil.isLetter((char)7294));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7294));
    }
    @Test
    public void d1423(){
        assertEq(0,StringDataUtil.getType((char)7296));
        assertEq(-1,StringDataUtil.getDirectionality((char)7296));
        assertFalse(StringDataLetterUtil.isLetter((char)7296));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7296));
    }
    @Test
    public void d1424(){
        assertEq(24,StringDataUtil.getType((char)7360));
        assertEq(0,StringDataUtil.getDirectionality((char)7360));
        assertFalse(StringDataLetterUtil.isLetter((char)7360));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7360));
    }
    @Test
    public void d1425(){
        assertEq(0,StringDataUtil.getType((char)7368));
        assertEq(-1,StringDataUtil.getDirectionality((char)7368));
        assertFalse(StringDataLetterUtil.isLetter((char)7368));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7368));
    }
    @Test
    public void d1426(){
        assertEq(6,StringDataUtil.getType((char)7376));
        assertEq(8,StringDataUtil.getDirectionality((char)7376));
        assertFalse(StringDataLetterUtil.isLetter((char)7376));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7376));
    }
    @Test
    public void d1427(){
        assertEq(24,StringDataUtil.getType((char)7379));
        assertEq(0,StringDataUtil.getDirectionality((char)7379));
        assertFalse(StringDataLetterUtil.isLetter((char)7379));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7379));
    }
    @Test
    public void d1428(){
        assertEq(6,StringDataUtil.getType((char)7380));
        assertEq(8,StringDataUtil.getDirectionality((char)7380));
        assertFalse(StringDataLetterUtil.isLetter((char)7380));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7380));
    }
    @Test
    public void d1429(){
        assertEq(8,StringDataUtil.getType((char)7393));
        assertEq(0,StringDataUtil.getDirectionality((char)7393));
        assertFalse(StringDataLetterUtil.isLetter((char)7393));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7393));
    }
    @Test
    public void d1430(){
        assertEq(6,StringDataUtil.getType((char)7394));
        assertEq(8,StringDataUtil.getDirectionality((char)7394));
        assertFalse(StringDataLetterUtil.isLetter((char)7394));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7394));
    }
    @Test
    public void d1431(){
        assertEq(5,StringDataUtil.getType((char)7401));
        assertEq(0,StringDataUtil.getDirectionality((char)7401));
        assertTrue(StringDataLetterUtil.isLetter((char)7401));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7401));
    }
    @Test
    public void d1432(){
        assertEq(6,StringDataUtil.getType((char)7405));
        assertEq(8,StringDataUtil.getDirectionality((char)7405));
        assertFalse(StringDataLetterUtil.isLetter((char)7405));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7405));
    }
    @Test
    public void d1433(){
        assertEq(5,StringDataUtil.getType((char)7406));
        assertEq(0,StringDataUtil.getDirectionality((char)7406));
        assertTrue(StringDataLetterUtil.isLetter((char)7406));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7406));
    }
    @Test
    public void d1434(){
        assertEq(8,StringDataUtil.getType((char)7410));
        assertEq(0,StringDataUtil.getDirectionality((char)7410));
        assertFalse(StringDataLetterUtil.isLetter((char)7410));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7410));
    }
    @Test
    public void d1435(){
        assertEq(6,StringDataUtil.getType((char)7412));
        assertEq(8,StringDataUtil.getDirectionality((char)7412));
        assertFalse(StringDataLetterUtil.isLetter((char)7412));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7412));
    }
    @Test
    public void d1436(){
        assertEq(5,StringDataUtil.getType((char)7413));
        assertEq(0,StringDataUtil.getDirectionality((char)7413));
        assertTrue(StringDataLetterUtil.isLetter((char)7413));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7413));
    }
    @Test
    public void d1437(){
        assertEq(0,StringDataUtil.getType((char)7415));
        assertEq(-1,StringDataUtil.getDirectionality((char)7415));
        assertFalse(StringDataLetterUtil.isLetter((char)7415));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7415));
    }
    @Test
    public void d1438(){
        assertEq(2,StringDataUtil.getType((char)7424));
        assertEq(0,StringDataUtil.getDirectionality((char)7424));
        assertTrue(StringDataLetterUtil.isLetter((char)7424));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7424));
    }
    @Test
    public void d1439(){
        assertEq(4,StringDataUtil.getType((char)7468));
        assertEq(0,StringDataUtil.getDirectionality((char)7468));
        assertTrue(StringDataLetterUtil.isLetter((char)7468));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7468));
    }
    @Test
    public void d1440(){
        assertEq(2,StringDataUtil.getType((char)7531));
        assertEq(0,StringDataUtil.getDirectionality((char)7531));
        assertTrue(StringDataLetterUtil.isLetter((char)7531));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7531));
    }
    @Test
    public void d1441(){
        assertEq(4,StringDataUtil.getType((char)7544));
        assertEq(0,StringDataUtil.getDirectionality((char)7544));
        assertTrue(StringDataLetterUtil.isLetter((char)7544));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7544));
    }
    @Test
    public void d1442(){
        assertEq(2,StringDataUtil.getType((char)7545));
        assertEq(0,StringDataUtil.getDirectionality((char)7545));
        assertTrue(StringDataLetterUtil.isLetter((char)7545));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7545));
    }
    @Test
    public void d1443(){
        assertEq(4,StringDataUtil.getType((char)7579));
        assertEq(0,StringDataUtil.getDirectionality((char)7579));
        assertTrue(StringDataLetterUtil.isLetter((char)7579));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7579));
    }
    @Test
    public void d1444(){
        assertEq(6,StringDataUtil.getType((char)7616));
        assertEq(8,StringDataUtil.getDirectionality((char)7616));
        assertFalse(StringDataLetterUtil.isLetter((char)7616));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7616));
    }
    @Test
    public void d1445(){
        assertEq(0,StringDataUtil.getType((char)7655));
        assertEq(-1,StringDataUtil.getDirectionality((char)7655));
        assertFalse(StringDataLetterUtil.isLetter((char)7655));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7655));
    }
    @Test
    public void d1446(){
        assertEq(6,StringDataUtil.getType((char)7676));
        assertEq(8,StringDataUtil.getDirectionality((char)7676));
        assertFalse(StringDataLetterUtil.isLetter((char)7676));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7676));
    }
    @Test
    public void d1447(){
        assertEq(1,StringDataUtil.getType((char)7680));
        assertEq(0,StringDataUtil.getDirectionality((char)7680));
        assertTrue(StringDataLetterUtil.isLetter((char)7680));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7680));
    }
    @Test
    public void d1448(){
        assertEq(2,StringDataUtil.getType((char)7681));
        assertEq(0,StringDataUtil.getDirectionality((char)7681));
        assertTrue(StringDataLetterUtil.isLetter((char)7681));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7681));
    }
    @Test
    public void d1449(){
        assertEq(1,StringDataUtil.getType((char)7682));
        assertEq(0,StringDataUtil.getDirectionality((char)7682));
        assertTrue(StringDataLetterUtil.isLetter((char)7682));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7682));
    }
    @Test
    public void d1450(){
        assertEq(2,StringDataUtil.getType((char)7683));
        assertEq(0,StringDataUtil.getDirectionality((char)7683));
        assertTrue(StringDataLetterUtil.isLetter((char)7683));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7683));
    }
    @Test
    public void d1451(){
        assertEq(1,StringDataUtil.getType((char)7684));
        assertEq(0,StringDataUtil.getDirectionality((char)7684));
        assertTrue(StringDataLetterUtil.isLetter((char)7684));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7684));
    }
    @Test
    public void d1452(){
        assertEq(2,StringDataUtil.getType((char)7685));
        assertEq(0,StringDataUtil.getDirectionality((char)7685));
        assertTrue(StringDataLetterUtil.isLetter((char)7685));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7685));
    }
    @Test
    public void d1453(){
        assertEq(1,StringDataUtil.getType((char)7686));
        assertEq(0,StringDataUtil.getDirectionality((char)7686));
        assertTrue(StringDataLetterUtil.isLetter((char)7686));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7686));
    }
    @Test
    public void d1454(){
        assertEq(2,StringDataUtil.getType((char)7687));
        assertEq(0,StringDataUtil.getDirectionality((char)7687));
        assertTrue(StringDataLetterUtil.isLetter((char)7687));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7687));
    }
    @Test
    public void d1455(){
        assertEq(1,StringDataUtil.getType((char)7688));
        assertEq(0,StringDataUtil.getDirectionality((char)7688));
        assertTrue(StringDataLetterUtil.isLetter((char)7688));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7688));
    }
    @Test
    public void d1456(){
        assertEq(2,StringDataUtil.getType((char)7689));
        assertEq(0,StringDataUtil.getDirectionality((char)7689));
        assertTrue(StringDataLetterUtil.isLetter((char)7689));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7689));
    }
    @Test
    public void d1457(){
        assertEq(1,StringDataUtil.getType((char)7690));
        assertEq(0,StringDataUtil.getDirectionality((char)7690));
        assertTrue(StringDataLetterUtil.isLetter((char)7690));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7690));
    }
    @Test
    public void d1458(){
        assertEq(2,StringDataUtil.getType((char)7691));
        assertEq(0,StringDataUtil.getDirectionality((char)7691));
        assertTrue(StringDataLetterUtil.isLetter((char)7691));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7691));
    }
    @Test
    public void d1459(){
        assertEq(1,StringDataUtil.getType((char)7692));
        assertEq(0,StringDataUtil.getDirectionality((char)7692));
        assertTrue(StringDataLetterUtil.isLetter((char)7692));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7692));
    }
    @Test
    public void d1460(){
        assertEq(2,StringDataUtil.getType((char)7693));
        assertEq(0,StringDataUtil.getDirectionality((char)7693));
        assertTrue(StringDataLetterUtil.isLetter((char)7693));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7693));
    }
    @Test
    public void d1461(){
        assertEq(1,StringDataUtil.getType((char)7694));
        assertEq(0,StringDataUtil.getDirectionality((char)7694));
        assertTrue(StringDataLetterUtil.isLetter((char)7694));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7694));
    }
    @Test
    public void d1462(){
        assertEq(2,StringDataUtil.getType((char)7695));
        assertEq(0,StringDataUtil.getDirectionality((char)7695));
        assertTrue(StringDataLetterUtil.isLetter((char)7695));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7695));
    }
    @Test
    public void d1463(){
        assertEq(1,StringDataUtil.getType((char)7696));
        assertEq(0,StringDataUtil.getDirectionality((char)7696));
        assertTrue(StringDataLetterUtil.isLetter((char)7696));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7696));
    }
    @Test
    public void d1464(){
        assertEq(2,StringDataUtil.getType((char)7697));
        assertEq(0,StringDataUtil.getDirectionality((char)7697));
        assertTrue(StringDataLetterUtil.isLetter((char)7697));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7697));
    }
    @Test
    public void d1465(){
        assertEq(1,StringDataUtil.getType((char)7698));
        assertEq(0,StringDataUtil.getDirectionality((char)7698));
        assertTrue(StringDataLetterUtil.isLetter((char)7698));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7698));
    }
    @Test
    public void d1466(){
        assertEq(2,StringDataUtil.getType((char)7699));
        assertEq(0,StringDataUtil.getDirectionality((char)7699));
        assertTrue(StringDataLetterUtil.isLetter((char)7699));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7699));
    }
    @Test
    public void d1467(){
        assertEq(1,StringDataUtil.getType((char)7700));
        assertEq(0,StringDataUtil.getDirectionality((char)7700));
        assertTrue(StringDataLetterUtil.isLetter((char)7700));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7700));
    }
    @Test
    public void d1468(){
        assertEq(2,StringDataUtil.getType((char)7701));
        assertEq(0,StringDataUtil.getDirectionality((char)7701));
        assertTrue(StringDataLetterUtil.isLetter((char)7701));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7701));
    }
    @Test
    public void d1469(){
        assertEq(1,StringDataUtil.getType((char)7702));
        assertEq(0,StringDataUtil.getDirectionality((char)7702));
        assertTrue(StringDataLetterUtil.isLetter((char)7702));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7702));
    }
    @Test
    public void d1470(){
        assertEq(2,StringDataUtil.getType((char)7703));
        assertEq(0,StringDataUtil.getDirectionality((char)7703));
        assertTrue(StringDataLetterUtil.isLetter((char)7703));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7703));
    }
    @Test
    public void d1471(){
        assertEq(1,StringDataUtil.getType((char)7704));
        assertEq(0,StringDataUtil.getDirectionality((char)7704));
        assertTrue(StringDataLetterUtil.isLetter((char)7704));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7704));
    }
    @Test
    public void d1472(){
        assertEq(2,StringDataUtil.getType((char)7705));
        assertEq(0,StringDataUtil.getDirectionality((char)7705));
        assertTrue(StringDataLetterUtil.isLetter((char)7705));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7705));
    }
    @Test
    public void d1473(){
        assertEq(1,StringDataUtil.getType((char)7706));
        assertEq(0,StringDataUtil.getDirectionality((char)7706));
        assertTrue(StringDataLetterUtil.isLetter((char)7706));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7706));
    }
    @Test
    public void d1474(){
        assertEq(2,StringDataUtil.getType((char)7707));
        assertEq(0,StringDataUtil.getDirectionality((char)7707));
        assertTrue(StringDataLetterUtil.isLetter((char)7707));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7707));
    }
    @Test
    public void d1475(){
        assertEq(1,StringDataUtil.getType((char)7708));
        assertEq(0,StringDataUtil.getDirectionality((char)7708));
        assertTrue(StringDataLetterUtil.isLetter((char)7708));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7708));
    }
    @Test
    public void d1476(){
        assertEq(2,StringDataUtil.getType((char)7709));
        assertEq(0,StringDataUtil.getDirectionality((char)7709));
        assertTrue(StringDataLetterUtil.isLetter((char)7709));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7709));
    }
    @Test
    public void d1477(){
        assertEq(1,StringDataUtil.getType((char)7710));
        assertEq(0,StringDataUtil.getDirectionality((char)7710));
        assertTrue(StringDataLetterUtil.isLetter((char)7710));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7710));
    }
    @Test
    public void d1478(){
        assertEq(2,StringDataUtil.getType((char)7711));
        assertEq(0,StringDataUtil.getDirectionality((char)7711));
        assertTrue(StringDataLetterUtil.isLetter((char)7711));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7711));
    }
    @Test
    public void d1479(){
        assertEq(1,StringDataUtil.getType((char)7712));
        assertEq(0,StringDataUtil.getDirectionality((char)7712));
        assertTrue(StringDataLetterUtil.isLetter((char)7712));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7712));
    }
    @Test
    public void d1480(){
        assertEq(2,StringDataUtil.getType((char)7713));
        assertEq(0,StringDataUtil.getDirectionality((char)7713));
        assertTrue(StringDataLetterUtil.isLetter((char)7713));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7713));
    }
    @Test
    public void d1481(){
        assertEq(1,StringDataUtil.getType((char)7714));
        assertEq(0,StringDataUtil.getDirectionality((char)7714));
        assertTrue(StringDataLetterUtil.isLetter((char)7714));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7714));
    }
    @Test
    public void d1482(){
        assertEq(2,StringDataUtil.getType((char)7715));
        assertEq(0,StringDataUtil.getDirectionality((char)7715));
        assertTrue(StringDataLetterUtil.isLetter((char)7715));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7715));
    }
    @Test
    public void d1483(){
        assertEq(1,StringDataUtil.getType((char)7716));
        assertEq(0,StringDataUtil.getDirectionality((char)7716));
        assertTrue(StringDataLetterUtil.isLetter((char)7716));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7716));
    }
    @Test
    public void d1484(){
        assertEq(2,StringDataUtil.getType((char)7717));
        assertEq(0,StringDataUtil.getDirectionality((char)7717));
        assertTrue(StringDataLetterUtil.isLetter((char)7717));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7717));
    }
    @Test
    public void d1485(){
        assertEq(1,StringDataUtil.getType((char)7718));
        assertEq(0,StringDataUtil.getDirectionality((char)7718));
        assertTrue(StringDataLetterUtil.isLetter((char)7718));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7718));
    }
    @Test
    public void d1486(){
        assertEq(2,StringDataUtil.getType((char)7719));
        assertEq(0,StringDataUtil.getDirectionality((char)7719));
        assertTrue(StringDataLetterUtil.isLetter((char)7719));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7719));
    }
    @Test
    public void d1487(){
        assertEq(1,StringDataUtil.getType((char)7720));
        assertEq(0,StringDataUtil.getDirectionality((char)7720));
        assertTrue(StringDataLetterUtil.isLetter((char)7720));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7720));
    }
    @Test
    public void d1488(){
        assertEq(2,StringDataUtil.getType((char)7721));
        assertEq(0,StringDataUtil.getDirectionality((char)7721));
        assertTrue(StringDataLetterUtil.isLetter((char)7721));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7721));
    }
    @Test
    public void d1489(){
        assertEq(1,StringDataUtil.getType((char)7722));
        assertEq(0,StringDataUtil.getDirectionality((char)7722));
        assertTrue(StringDataLetterUtil.isLetter((char)7722));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7722));
    }
    @Test
    public void d1490(){
        assertEq(2,StringDataUtil.getType((char)7723));
        assertEq(0,StringDataUtil.getDirectionality((char)7723));
        assertTrue(StringDataLetterUtil.isLetter((char)7723));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7723));
    }
    @Test
    public void d1491(){
        assertEq(1,StringDataUtil.getType((char)7724));
        assertEq(0,StringDataUtil.getDirectionality((char)7724));
        assertTrue(StringDataLetterUtil.isLetter((char)7724));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7724));
    }
    @Test
    public void d1492(){
        assertEq(2,StringDataUtil.getType((char)7725));
        assertEq(0,StringDataUtil.getDirectionality((char)7725));
        assertTrue(StringDataLetterUtil.isLetter((char)7725));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7725));
    }
    @Test
    public void d1493(){
        assertEq(1,StringDataUtil.getType((char)7726));
        assertEq(0,StringDataUtil.getDirectionality((char)7726));
        assertTrue(StringDataLetterUtil.isLetter((char)7726));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7726));
    }
    @Test
    public void d1494(){
        assertEq(2,StringDataUtil.getType((char)7727));
        assertEq(0,StringDataUtil.getDirectionality((char)7727));
        assertTrue(StringDataLetterUtil.isLetter((char)7727));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7727));
    }
    @Test
    public void d1495(){
        assertEq(1,StringDataUtil.getType((char)7728));
        assertEq(0,StringDataUtil.getDirectionality((char)7728));
        assertTrue(StringDataLetterUtil.isLetter((char)7728));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7728));
    }
    @Test
    public void d1496(){
        assertEq(2,StringDataUtil.getType((char)7729));
        assertEq(0,StringDataUtil.getDirectionality((char)7729));
        assertTrue(StringDataLetterUtil.isLetter((char)7729));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7729));
    }
    @Test
    public void d1497(){
        assertEq(1,StringDataUtil.getType((char)7730));
        assertEq(0,StringDataUtil.getDirectionality((char)7730));
        assertTrue(StringDataLetterUtil.isLetter((char)7730));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7730));
    }
    @Test
    public void d1498(){
        assertEq(2,StringDataUtil.getType((char)7731));
        assertEq(0,StringDataUtil.getDirectionality((char)7731));
        assertTrue(StringDataLetterUtil.isLetter((char)7731));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7731));
    }
    @Test
    public void d1499(){
        assertEq(1,StringDataUtil.getType((char)7732));
        assertEq(0,StringDataUtil.getDirectionality((char)7732));
        assertTrue(StringDataLetterUtil.isLetter((char)7732));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7732));
    }
    @Test
    public void d1500(){
        assertEq(2,StringDataUtil.getType((char)7733));
        assertEq(0,StringDataUtil.getDirectionality((char)7733));
        assertTrue(StringDataLetterUtil.isLetter((char)7733));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7733));
    }
    @Test
    public void d1501(){
        assertEq(1,StringDataUtil.getType((char)7734));
        assertEq(0,StringDataUtil.getDirectionality((char)7734));
        assertTrue(StringDataLetterUtil.isLetter((char)7734));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7734));
    }
    @Test
    public void d1502(){
        assertEq(2,StringDataUtil.getType((char)7735));
        assertEq(0,StringDataUtil.getDirectionality((char)7735));
        assertTrue(StringDataLetterUtil.isLetter((char)7735));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7735));
    }
    @Test
    public void d1503(){
        assertEq(1,StringDataUtil.getType((char)7736));
        assertEq(0,StringDataUtil.getDirectionality((char)7736));
        assertTrue(StringDataLetterUtil.isLetter((char)7736));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7736));
    }
    @Test
    public void d1504(){
        assertEq(2,StringDataUtil.getType((char)7737));
        assertEq(0,StringDataUtil.getDirectionality((char)7737));
        assertTrue(StringDataLetterUtil.isLetter((char)7737));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7737));
    }
    @Test
    public void d1505(){
        assertEq(1,StringDataUtil.getType((char)7738));
        assertEq(0,StringDataUtil.getDirectionality((char)7738));
        assertTrue(StringDataLetterUtil.isLetter((char)7738));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7738));
    }
    @Test
    public void d1506(){
        assertEq(2,StringDataUtil.getType((char)7739));
        assertEq(0,StringDataUtil.getDirectionality((char)7739));
        assertTrue(StringDataLetterUtil.isLetter((char)7739));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7739));
    }
    @Test
    public void d1507(){
        assertEq(1,StringDataUtil.getType((char)7740));
        assertEq(0,StringDataUtil.getDirectionality((char)7740));
        assertTrue(StringDataLetterUtil.isLetter((char)7740));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7740));
    }
    @Test
    public void d1508(){
        assertEq(2,StringDataUtil.getType((char)7741));
        assertEq(0,StringDataUtil.getDirectionality((char)7741));
        assertTrue(StringDataLetterUtil.isLetter((char)7741));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7741));
    }
    @Test
    public void d1509(){
        assertEq(1,StringDataUtil.getType((char)7742));
        assertEq(0,StringDataUtil.getDirectionality((char)7742));
        assertTrue(StringDataLetterUtil.isLetter((char)7742));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7742));
    }
    @Test
    public void d1510(){
        assertEq(2,StringDataUtil.getType((char)7743));
        assertEq(0,StringDataUtil.getDirectionality((char)7743));
        assertTrue(StringDataLetterUtil.isLetter((char)7743));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7743));
    }
    @Test
    public void d1511(){
        assertEq(1,StringDataUtil.getType((char)7744));
        assertEq(0,StringDataUtil.getDirectionality((char)7744));
        assertTrue(StringDataLetterUtil.isLetter((char)7744));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7744));
    }
    @Test
    public void d1512(){
        assertEq(2,StringDataUtil.getType((char)7745));
        assertEq(0,StringDataUtil.getDirectionality((char)7745));
        assertTrue(StringDataLetterUtil.isLetter((char)7745));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7745));
    }
    @Test
    public void d1513(){
        assertEq(1,StringDataUtil.getType((char)7746));
        assertEq(0,StringDataUtil.getDirectionality((char)7746));
        assertTrue(StringDataLetterUtil.isLetter((char)7746));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7746));
    }
    @Test
    public void d1514(){
        assertEq(2,StringDataUtil.getType((char)7747));
        assertEq(0,StringDataUtil.getDirectionality((char)7747));
        assertTrue(StringDataLetterUtil.isLetter((char)7747));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7747));
    }
    @Test
    public void d1515(){
        assertEq(1,StringDataUtil.getType((char)7748));
        assertEq(0,StringDataUtil.getDirectionality((char)7748));
        assertTrue(StringDataLetterUtil.isLetter((char)7748));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7748));
    }
    @Test
    public void d1516(){
        assertEq(2,StringDataUtil.getType((char)7749));
        assertEq(0,StringDataUtil.getDirectionality((char)7749));
        assertTrue(StringDataLetterUtil.isLetter((char)7749));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7749));
    }
    @Test
    public void d1517(){
        assertEq(1,StringDataUtil.getType((char)7750));
        assertEq(0,StringDataUtil.getDirectionality((char)7750));
        assertTrue(StringDataLetterUtil.isLetter((char)7750));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7750));
    }
    @Test
    public void d1518(){
        assertEq(2,StringDataUtil.getType((char)7751));
        assertEq(0,StringDataUtil.getDirectionality((char)7751));
        assertTrue(StringDataLetterUtil.isLetter((char)7751));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7751));
    }
    @Test
    public void d1519(){
        assertEq(1,StringDataUtil.getType((char)7752));
        assertEq(0,StringDataUtil.getDirectionality((char)7752));
        assertTrue(StringDataLetterUtil.isLetter((char)7752));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7752));
    }
    @Test
    public void d1520(){
        assertEq(2,StringDataUtil.getType((char)7753));
        assertEq(0,StringDataUtil.getDirectionality((char)7753));
        assertTrue(StringDataLetterUtil.isLetter((char)7753));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7753));
    }
    @Test
    public void d1521(){
        assertEq(1,StringDataUtil.getType((char)7754));
        assertEq(0,StringDataUtil.getDirectionality((char)7754));
        assertTrue(StringDataLetterUtil.isLetter((char)7754));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7754));
    }
    @Test
    public void d1522(){
        assertEq(2,StringDataUtil.getType((char)7755));
        assertEq(0,StringDataUtil.getDirectionality((char)7755));
        assertTrue(StringDataLetterUtil.isLetter((char)7755));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7755));
    }
    @Test
    public void d1523(){
        assertEq(1,StringDataUtil.getType((char)7756));
        assertEq(0,StringDataUtil.getDirectionality((char)7756));
        assertTrue(StringDataLetterUtil.isLetter((char)7756));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7756));
    }
    @Test
    public void d1524(){
        assertEq(2,StringDataUtil.getType((char)7757));
        assertEq(0,StringDataUtil.getDirectionality((char)7757));
        assertTrue(StringDataLetterUtil.isLetter((char)7757));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7757));
    }
    @Test
    public void d1525(){
        assertEq(1,StringDataUtil.getType((char)7758));
        assertEq(0,StringDataUtil.getDirectionality((char)7758));
        assertTrue(StringDataLetterUtil.isLetter((char)7758));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7758));
    }
    @Test
    public void d1526(){
        assertEq(2,StringDataUtil.getType((char)7759));
        assertEq(0,StringDataUtil.getDirectionality((char)7759));
        assertTrue(StringDataLetterUtil.isLetter((char)7759));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7759));
    }
    @Test
    public void d1527(){
        assertEq(1,StringDataUtil.getType((char)7760));
        assertEq(0,StringDataUtil.getDirectionality((char)7760));
        assertTrue(StringDataLetterUtil.isLetter((char)7760));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7760));
    }
    @Test
    public void d1528(){
        assertEq(2,StringDataUtil.getType((char)7761));
        assertEq(0,StringDataUtil.getDirectionality((char)7761));
        assertTrue(StringDataLetterUtil.isLetter((char)7761));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7761));
    }
    @Test
    public void d1529(){
        assertEq(1,StringDataUtil.getType((char)7762));
        assertEq(0,StringDataUtil.getDirectionality((char)7762));
        assertTrue(StringDataLetterUtil.isLetter((char)7762));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7762));
    }
    @Test
    public void d1530(){
        assertEq(2,StringDataUtil.getType((char)7763));
        assertEq(0,StringDataUtil.getDirectionality((char)7763));
        assertTrue(StringDataLetterUtil.isLetter((char)7763));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7763));
    }
    @Test
    public void d1531(){
        assertEq(1,StringDataUtil.getType((char)7764));
        assertEq(0,StringDataUtil.getDirectionality((char)7764));
        assertTrue(StringDataLetterUtil.isLetter((char)7764));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7764));
    }
    @Test
    public void d1532(){
        assertEq(2,StringDataUtil.getType((char)7765));
        assertEq(0,StringDataUtil.getDirectionality((char)7765));
        assertTrue(StringDataLetterUtil.isLetter((char)7765));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7765));
    }
    @Test
    public void d1533(){
        assertEq(1,StringDataUtil.getType((char)7766));
        assertEq(0,StringDataUtil.getDirectionality((char)7766));
        assertTrue(StringDataLetterUtil.isLetter((char)7766));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7766));
    }
    @Test
    public void d1534(){
        assertEq(2,StringDataUtil.getType((char)7767));
        assertEq(0,StringDataUtil.getDirectionality((char)7767));
        assertTrue(StringDataLetterUtil.isLetter((char)7767));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7767));
    }
    @Test
    public void d1535(){
        assertEq(1,StringDataUtil.getType((char)7768));
        assertEq(0,StringDataUtil.getDirectionality((char)7768));
        assertTrue(StringDataLetterUtil.isLetter((char)7768));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7768));
    }
    @Test
    public void d1536(){
        assertEq(2,StringDataUtil.getType((char)7769));
        assertEq(0,StringDataUtil.getDirectionality((char)7769));
        assertTrue(StringDataLetterUtil.isLetter((char)7769));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7769));
    }
    @Test
    public void d1537(){
        assertEq(1,StringDataUtil.getType((char)7770));
        assertEq(0,StringDataUtil.getDirectionality((char)7770));
        assertTrue(StringDataLetterUtil.isLetter((char)7770));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7770));
    }
    @Test
    public void d1538(){
        assertEq(2,StringDataUtil.getType((char)7771));
        assertEq(0,StringDataUtil.getDirectionality((char)7771));
        assertTrue(StringDataLetterUtil.isLetter((char)7771));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7771));
    }
    @Test
    public void d1539(){
        assertEq(1,StringDataUtil.getType((char)7772));
        assertEq(0,StringDataUtil.getDirectionality((char)7772));
        assertTrue(StringDataLetterUtil.isLetter((char)7772));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7772));
    }
    @Test
    public void d1540(){
        assertEq(2,StringDataUtil.getType((char)7773));
        assertEq(0,StringDataUtil.getDirectionality((char)7773));
        assertTrue(StringDataLetterUtil.isLetter((char)7773));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7773));
    }
    @Test
    public void d1541(){
        assertEq(1,StringDataUtil.getType((char)7774));
        assertEq(0,StringDataUtil.getDirectionality((char)7774));
        assertTrue(StringDataLetterUtil.isLetter((char)7774));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7774));
    }
    @Test
    public void d1542(){
        assertEq(2,StringDataUtil.getType((char)7775));
        assertEq(0,StringDataUtil.getDirectionality((char)7775));
        assertTrue(StringDataLetterUtil.isLetter((char)7775));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7775));
    }
    @Test
    public void d1543(){
        assertEq(1,StringDataUtil.getType((char)7776));
        assertEq(0,StringDataUtil.getDirectionality((char)7776));
        assertTrue(StringDataLetterUtil.isLetter((char)7776));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7776));
    }
    @Test
    public void d1544(){
        assertEq(2,StringDataUtil.getType((char)7777));
        assertEq(0,StringDataUtil.getDirectionality((char)7777));
        assertTrue(StringDataLetterUtil.isLetter((char)7777));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7777));
    }
    @Test
    public void d1545(){
        assertEq(1,StringDataUtil.getType((char)7778));
        assertEq(0,StringDataUtil.getDirectionality((char)7778));
        assertTrue(StringDataLetterUtil.isLetter((char)7778));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7778));
    }
    @Test
    public void d1546(){
        assertEq(2,StringDataUtil.getType((char)7779));
        assertEq(0,StringDataUtil.getDirectionality((char)7779));
        assertTrue(StringDataLetterUtil.isLetter((char)7779));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7779));
    }
    @Test
    public void d1547(){
        assertEq(1,StringDataUtil.getType((char)7780));
        assertEq(0,StringDataUtil.getDirectionality((char)7780));
        assertTrue(StringDataLetterUtil.isLetter((char)7780));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7780));
    }
    @Test
    public void d1548(){
        assertEq(2,StringDataUtil.getType((char)7781));
        assertEq(0,StringDataUtil.getDirectionality((char)7781));
        assertTrue(StringDataLetterUtil.isLetter((char)7781));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7781));
    }
    @Test
    public void d1549(){
        assertEq(1,StringDataUtil.getType((char)7782));
        assertEq(0,StringDataUtil.getDirectionality((char)7782));
        assertTrue(StringDataLetterUtil.isLetter((char)7782));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7782));
    }
    @Test
    public void d1550(){
        assertEq(2,StringDataUtil.getType((char)7783));
        assertEq(0,StringDataUtil.getDirectionality((char)7783));
        assertTrue(StringDataLetterUtil.isLetter((char)7783));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7783));
    }
    @Test
    public void d1551(){
        assertEq(1,StringDataUtil.getType((char)7784));
        assertEq(0,StringDataUtil.getDirectionality((char)7784));
        assertTrue(StringDataLetterUtil.isLetter((char)7784));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7784));
    }
    @Test
    public void d1552(){
        assertEq(2,StringDataUtil.getType((char)7785));
        assertEq(0,StringDataUtil.getDirectionality((char)7785));
        assertTrue(StringDataLetterUtil.isLetter((char)7785));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7785));
    }
    @Test
    public void d1553(){
        assertEq(1,StringDataUtil.getType((char)7786));
        assertEq(0,StringDataUtil.getDirectionality((char)7786));
        assertTrue(StringDataLetterUtil.isLetter((char)7786));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7786));
    }
    @Test
    public void d1554(){
        assertEq(2,StringDataUtil.getType((char)7787));
        assertEq(0,StringDataUtil.getDirectionality((char)7787));
        assertTrue(StringDataLetterUtil.isLetter((char)7787));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7787));
    }
    @Test
    public void d1555(){
        assertEq(1,StringDataUtil.getType((char)7788));
        assertEq(0,StringDataUtil.getDirectionality((char)7788));
        assertTrue(StringDataLetterUtil.isLetter((char)7788));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7788));
    }
    @Test
    public void d1556(){
        assertEq(2,StringDataUtil.getType((char)7789));
        assertEq(0,StringDataUtil.getDirectionality((char)7789));
        assertTrue(StringDataLetterUtil.isLetter((char)7789));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7789));
    }
    @Test
    public void d1557(){
        assertEq(1,StringDataUtil.getType((char)7790));
        assertEq(0,StringDataUtil.getDirectionality((char)7790));
        assertTrue(StringDataLetterUtil.isLetter((char)7790));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7790));
    }
    @Test
    public void d1558(){
        assertEq(2,StringDataUtil.getType((char)7791));
        assertEq(0,StringDataUtil.getDirectionality((char)7791));
        assertTrue(StringDataLetterUtil.isLetter((char)7791));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7791));
    }
    @Test
    public void d1559(){
        assertEq(1,StringDataUtil.getType((char)7792));
        assertEq(0,StringDataUtil.getDirectionality((char)7792));
        assertTrue(StringDataLetterUtil.isLetter((char)7792));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7792));
    }
    @Test
    public void d1560(){
        assertEq(2,StringDataUtil.getType((char)7793));
        assertEq(0,StringDataUtil.getDirectionality((char)7793));
        assertTrue(StringDataLetterUtil.isLetter((char)7793));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7793));
    }
    @Test
    public void d1561(){
        assertEq(1,StringDataUtil.getType((char)7794));
        assertEq(0,StringDataUtil.getDirectionality((char)7794));
        assertTrue(StringDataLetterUtil.isLetter((char)7794));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7794));
    }
    @Test
    public void d1562(){
        assertEq(2,StringDataUtil.getType((char)7795));
        assertEq(0,StringDataUtil.getDirectionality((char)7795));
        assertTrue(StringDataLetterUtil.isLetter((char)7795));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7795));
    }
    @Test
    public void d1563(){
        assertEq(1,StringDataUtil.getType((char)7796));
        assertEq(0,StringDataUtil.getDirectionality((char)7796));
        assertTrue(StringDataLetterUtil.isLetter((char)7796));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7796));
    }
    @Test
    public void d1564(){
        assertEq(2,StringDataUtil.getType((char)7797));
        assertEq(0,StringDataUtil.getDirectionality((char)7797));
        assertTrue(StringDataLetterUtil.isLetter((char)7797));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7797));
    }
    @Test
    public void d1565(){
        assertEq(1,StringDataUtil.getType((char)7798));
        assertEq(0,StringDataUtil.getDirectionality((char)7798));
        assertTrue(StringDataLetterUtil.isLetter((char)7798));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7798));
    }
    @Test
    public void d1566(){
        assertEq(2,StringDataUtil.getType((char)7799));
        assertEq(0,StringDataUtil.getDirectionality((char)7799));
        assertTrue(StringDataLetterUtil.isLetter((char)7799));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7799));
    }
    @Test
    public void d1567(){
        assertEq(1,StringDataUtil.getType((char)7800));
        assertEq(0,StringDataUtil.getDirectionality((char)7800));
        assertTrue(StringDataLetterUtil.isLetter((char)7800));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7800));
    }
    @Test
    public void d1568(){
        assertEq(2,StringDataUtil.getType((char)7801));
        assertEq(0,StringDataUtil.getDirectionality((char)7801));
        assertTrue(StringDataLetterUtil.isLetter((char)7801));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7801));
    }
    @Test
    public void d1569(){
        assertEq(1,StringDataUtil.getType((char)7802));
        assertEq(0,StringDataUtil.getDirectionality((char)7802));
        assertTrue(StringDataLetterUtil.isLetter((char)7802));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7802));
    }
    @Test
    public void d1570(){
        assertEq(2,StringDataUtil.getType((char)7803));
        assertEq(0,StringDataUtil.getDirectionality((char)7803));
        assertTrue(StringDataLetterUtil.isLetter((char)7803));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7803));
    }
    @Test
    public void d1571(){
        assertEq(1,StringDataUtil.getType((char)7804));
        assertEq(0,StringDataUtil.getDirectionality((char)7804));
        assertTrue(StringDataLetterUtil.isLetter((char)7804));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7804));
    }
    @Test
    public void d1572(){
        assertEq(2,StringDataUtil.getType((char)7805));
        assertEq(0,StringDataUtil.getDirectionality((char)7805));
        assertTrue(StringDataLetterUtil.isLetter((char)7805));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7805));
    }
    @Test
    public void d1573(){
        assertEq(1,StringDataUtil.getType((char)7806));
        assertEq(0,StringDataUtil.getDirectionality((char)7806));
        assertTrue(StringDataLetterUtil.isLetter((char)7806));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7806));
    }
    @Test
    public void d1574(){
        assertEq(2,StringDataUtil.getType((char)7807));
        assertEq(0,StringDataUtil.getDirectionality((char)7807));
        assertTrue(StringDataLetterUtil.isLetter((char)7807));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7807));
    }
    @Test
    public void d1575(){
        assertEq(1,StringDataUtil.getType((char)7808));
        assertEq(0,StringDataUtil.getDirectionality((char)7808));
        assertTrue(StringDataLetterUtil.isLetter((char)7808));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7808));
    }
    @Test
    public void d1576(){
        assertEq(2,StringDataUtil.getType((char)7809));
        assertEq(0,StringDataUtil.getDirectionality((char)7809));
        assertTrue(StringDataLetterUtil.isLetter((char)7809));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7809));
    }
    @Test
    public void d1577(){
        assertEq(1,StringDataUtil.getType((char)7810));
        assertEq(0,StringDataUtil.getDirectionality((char)7810));
        assertTrue(StringDataLetterUtil.isLetter((char)7810));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7810));
    }
    @Test
    public void d1578(){
        assertEq(2,StringDataUtil.getType((char)7811));
        assertEq(0,StringDataUtil.getDirectionality((char)7811));
        assertTrue(StringDataLetterUtil.isLetter((char)7811));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7811));
    }
    @Test
    public void d1579(){
        assertEq(1,StringDataUtil.getType((char)7812));
        assertEq(0,StringDataUtil.getDirectionality((char)7812));
        assertTrue(StringDataLetterUtil.isLetter((char)7812));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7812));
    }
    @Test
    public void d1580(){
        assertEq(2,StringDataUtil.getType((char)7813));
        assertEq(0,StringDataUtil.getDirectionality((char)7813));
        assertTrue(StringDataLetterUtil.isLetter((char)7813));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7813));
    }
    @Test
    public void d1581(){
        assertEq(1,StringDataUtil.getType((char)7814));
        assertEq(0,StringDataUtil.getDirectionality((char)7814));
        assertTrue(StringDataLetterUtil.isLetter((char)7814));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7814));
    }
    @Test
    public void d1582(){
        assertEq(2,StringDataUtil.getType((char)7815));
        assertEq(0,StringDataUtil.getDirectionality((char)7815));
        assertTrue(StringDataLetterUtil.isLetter((char)7815));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7815));
    }
    @Test
    public void d1583(){
        assertEq(1,StringDataUtil.getType((char)7816));
        assertEq(0,StringDataUtil.getDirectionality((char)7816));
        assertTrue(StringDataLetterUtil.isLetter((char)7816));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7816));
    }
    @Test
    public void d1584(){
        assertEq(2,StringDataUtil.getType((char)7817));
        assertEq(0,StringDataUtil.getDirectionality((char)7817));
        assertTrue(StringDataLetterUtil.isLetter((char)7817));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7817));
    }
    @Test
    public void d1585(){
        assertEq(1,StringDataUtil.getType((char)7818));
        assertEq(0,StringDataUtil.getDirectionality((char)7818));
        assertTrue(StringDataLetterUtil.isLetter((char)7818));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7818));
    }
    @Test
    public void d1586(){
        assertEq(2,StringDataUtil.getType((char)7819));
        assertEq(0,StringDataUtil.getDirectionality((char)7819));
        assertTrue(StringDataLetterUtil.isLetter((char)7819));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7819));
    }
    @Test
    public void d1587(){
        assertEq(1,StringDataUtil.getType((char)7820));
        assertEq(0,StringDataUtil.getDirectionality((char)7820));
        assertTrue(StringDataLetterUtil.isLetter((char)7820));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7820));
    }
    @Test
    public void d1588(){
        assertEq(2,StringDataUtil.getType((char)7821));
        assertEq(0,StringDataUtil.getDirectionality((char)7821));
        assertTrue(StringDataLetterUtil.isLetter((char)7821));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7821));
    }
    @Test
    public void d1589(){
        assertEq(1,StringDataUtil.getType((char)7822));
        assertEq(0,StringDataUtil.getDirectionality((char)7822));
        assertTrue(StringDataLetterUtil.isLetter((char)7822));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7822));
    }
    @Test
    public void d1590(){
        assertEq(2,StringDataUtil.getType((char)7823));
        assertEq(0,StringDataUtil.getDirectionality((char)7823));
        assertTrue(StringDataLetterUtil.isLetter((char)7823));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7823));
    }
    @Test
    public void d1591(){
        assertEq(1,StringDataUtil.getType((char)7824));
        assertEq(0,StringDataUtil.getDirectionality((char)7824));
        assertTrue(StringDataLetterUtil.isLetter((char)7824));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7824));
    }
    @Test
    public void d1592(){
        assertEq(2,StringDataUtil.getType((char)7825));
        assertEq(0,StringDataUtil.getDirectionality((char)7825));
        assertTrue(StringDataLetterUtil.isLetter((char)7825));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7825));
    }
    @Test
    public void d1593(){
        assertEq(1,StringDataUtil.getType((char)7826));
        assertEq(0,StringDataUtil.getDirectionality((char)7826));
        assertTrue(StringDataLetterUtil.isLetter((char)7826));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7826));
    }
    @Test
    public void d1594(){
        assertEq(2,StringDataUtil.getType((char)7827));
        assertEq(0,StringDataUtil.getDirectionality((char)7827));
        assertTrue(StringDataLetterUtil.isLetter((char)7827));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7827));
    }
    @Test
    public void d1595(){
        assertEq(1,StringDataUtil.getType((char)7828));
        assertEq(0,StringDataUtil.getDirectionality((char)7828));
        assertTrue(StringDataLetterUtil.isLetter((char)7828));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7828));
    }
    @Test
    public void d1596(){
        assertEq(2,StringDataUtil.getType((char)7829));
        assertEq(0,StringDataUtil.getDirectionality((char)7829));
        assertTrue(StringDataLetterUtil.isLetter((char)7829));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7829));
    }
    @Test
    public void d1597(){
        assertEq(1,StringDataUtil.getType((char)7838));
        assertEq(0,StringDataUtil.getDirectionality((char)7838));
        assertTrue(StringDataLetterUtil.isLetter((char)7838));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7838));
    }
    @Test
    public void d1598(){
        assertEq(2,StringDataUtil.getType((char)7839));
        assertEq(0,StringDataUtil.getDirectionality((char)7839));
        assertTrue(StringDataLetterUtil.isLetter((char)7839));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7839));
    }
    @Test
    public void d1599(){
        assertEq(1,StringDataUtil.getType((char)7840));
        assertEq(0,StringDataUtil.getDirectionality((char)7840));
        assertTrue(StringDataLetterUtil.isLetter((char)7840));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7840));
    }
    @Test
    public void d1600(){
        assertEq(2,StringDataUtil.getType((char)7841));
        assertEq(0,StringDataUtil.getDirectionality((char)7841));
        assertTrue(StringDataLetterUtil.isLetter((char)7841));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7841));
    }
    @Test
    public void d1601(){
        assertEq(1,StringDataUtil.getType((char)7842));
        assertEq(0,StringDataUtil.getDirectionality((char)7842));
        assertTrue(StringDataLetterUtil.isLetter((char)7842));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7842));
    }
    @Test
    public void d1602(){
        assertEq(2,StringDataUtil.getType((char)7843));
        assertEq(0,StringDataUtil.getDirectionality((char)7843));
        assertTrue(StringDataLetterUtil.isLetter((char)7843));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7843));
    }
    @Test
    public void d1603(){
        assertEq(1,StringDataUtil.getType((char)7844));
        assertEq(0,StringDataUtil.getDirectionality((char)7844));
        assertTrue(StringDataLetterUtil.isLetter((char)7844));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7844));
    }
    @Test
    public void d1604(){
        assertEq(2,StringDataUtil.getType((char)7845));
        assertEq(0,StringDataUtil.getDirectionality((char)7845));
        assertTrue(StringDataLetterUtil.isLetter((char)7845));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7845));
    }
    @Test
    public void d1605(){
        assertEq(1,StringDataUtil.getType((char)7846));
        assertEq(0,StringDataUtil.getDirectionality((char)7846));
        assertTrue(StringDataLetterUtil.isLetter((char)7846));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7846));
    }
    @Test
    public void d1606(){
        assertEq(2,StringDataUtil.getType((char)7847));
        assertEq(0,StringDataUtil.getDirectionality((char)7847));
        assertTrue(StringDataLetterUtil.isLetter((char)7847));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7847));
    }
    @Test
    public void d1607(){
        assertEq(1,StringDataUtil.getType((char)7848));
        assertEq(0,StringDataUtil.getDirectionality((char)7848));
        assertTrue(StringDataLetterUtil.isLetter((char)7848));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7848));
    }
    @Test
    public void d1608(){
        assertEq(2,StringDataUtil.getType((char)7849));
        assertEq(0,StringDataUtil.getDirectionality((char)7849));
        assertTrue(StringDataLetterUtil.isLetter((char)7849));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7849));
    }
    @Test
    public void d1609(){
        assertEq(1,StringDataUtil.getType((char)7850));
        assertEq(0,StringDataUtil.getDirectionality((char)7850));
        assertTrue(StringDataLetterUtil.isLetter((char)7850));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7850));
    }
    @Test
    public void d1610(){
        assertEq(2,StringDataUtil.getType((char)7851));
        assertEq(0,StringDataUtil.getDirectionality((char)7851));
        assertTrue(StringDataLetterUtil.isLetter((char)7851));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7851));
    }
    @Test
    public void d1611(){
        assertEq(1,StringDataUtil.getType((char)7852));
        assertEq(0,StringDataUtil.getDirectionality((char)7852));
        assertTrue(StringDataLetterUtil.isLetter((char)7852));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7852));
    }
    @Test
    public void d1612(){
        assertEq(2,StringDataUtil.getType((char)7853));
        assertEq(0,StringDataUtil.getDirectionality((char)7853));
        assertTrue(StringDataLetterUtil.isLetter((char)7853));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7853));
    }
    @Test
    public void d1613(){
        assertEq(1,StringDataUtil.getType((char)7854));
        assertEq(0,StringDataUtil.getDirectionality((char)7854));
        assertTrue(StringDataLetterUtil.isLetter((char)7854));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7854));
    }
    @Test
    public void d1614(){
        assertEq(2,StringDataUtil.getType((char)7855));
        assertEq(0,StringDataUtil.getDirectionality((char)7855));
        assertTrue(StringDataLetterUtil.isLetter((char)7855));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7855));
    }
    @Test
    public void d1615(){
        assertEq(1,StringDataUtil.getType((char)7856));
        assertEq(0,StringDataUtil.getDirectionality((char)7856));
        assertTrue(StringDataLetterUtil.isLetter((char)7856));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7856));
    }
    @Test
    public void d1616(){
        assertEq(2,StringDataUtil.getType((char)7857));
        assertEq(0,StringDataUtil.getDirectionality((char)7857));
        assertTrue(StringDataLetterUtil.isLetter((char)7857));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7857));
    }
    @Test
    public void d1617(){
        assertEq(1,StringDataUtil.getType((char)7858));
        assertEq(0,StringDataUtil.getDirectionality((char)7858));
        assertTrue(StringDataLetterUtil.isLetter((char)7858));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7858));
    }
    @Test
    public void d1618(){
        assertEq(2,StringDataUtil.getType((char)7859));
        assertEq(0,StringDataUtil.getDirectionality((char)7859));
        assertTrue(StringDataLetterUtil.isLetter((char)7859));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7859));
    }
    @Test
    public void d1619(){
        assertEq(1,StringDataUtil.getType((char)7860));
        assertEq(0,StringDataUtil.getDirectionality((char)7860));
        assertTrue(StringDataLetterUtil.isLetter((char)7860));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7860));
    }
    @Test
    public void d1620(){
        assertEq(2,StringDataUtil.getType((char)7861));
        assertEq(0,StringDataUtil.getDirectionality((char)7861));
        assertTrue(StringDataLetterUtil.isLetter((char)7861));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7861));
    }
    @Test
    public void d1621(){
        assertEq(1,StringDataUtil.getType((char)7862));
        assertEq(0,StringDataUtil.getDirectionality((char)7862));
        assertTrue(StringDataLetterUtil.isLetter((char)7862));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7862));
    }
    @Test
    public void d1622(){
        assertEq(2,StringDataUtil.getType((char)7863));
        assertEq(0,StringDataUtil.getDirectionality((char)7863));
        assertTrue(StringDataLetterUtil.isLetter((char)7863));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7863));
    }
    @Test
    public void d1623(){
        assertEq(1,StringDataUtil.getType((char)7864));
        assertEq(0,StringDataUtil.getDirectionality((char)7864));
        assertTrue(StringDataLetterUtil.isLetter((char)7864));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7864));
    }
    @Test
    public void d1624(){
        assertEq(2,StringDataUtil.getType((char)7865));
        assertEq(0,StringDataUtil.getDirectionality((char)7865));
        assertTrue(StringDataLetterUtil.isLetter((char)7865));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7865));
    }
    @Test
    public void d1625(){
        assertEq(1,StringDataUtil.getType((char)7866));
        assertEq(0,StringDataUtil.getDirectionality((char)7866));
        assertTrue(StringDataLetterUtil.isLetter((char)7866));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7866));
    }
    @Test
    public void d1626(){
        assertEq(2,StringDataUtil.getType((char)7867));
        assertEq(0,StringDataUtil.getDirectionality((char)7867));
        assertTrue(StringDataLetterUtil.isLetter((char)7867));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7867));
    }
    @Test
    public void d1627(){
        assertEq(1,StringDataUtil.getType((char)7868));
        assertEq(0,StringDataUtil.getDirectionality((char)7868));
        assertTrue(StringDataLetterUtil.isLetter((char)7868));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7868));
    }
    @Test
    public void d1628(){
        assertEq(2,StringDataUtil.getType((char)7869));
        assertEq(0,StringDataUtil.getDirectionality((char)7869));
        assertTrue(StringDataLetterUtil.isLetter((char)7869));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7869));
    }
    @Test
    public void d1629(){
        assertEq(1,StringDataUtil.getType((char)7870));
        assertEq(0,StringDataUtil.getDirectionality((char)7870));
        assertTrue(StringDataLetterUtil.isLetter((char)7870));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7870));
    }
    @Test
    public void d1630(){
        assertEq(2,StringDataUtil.getType((char)7871));
        assertEq(0,StringDataUtil.getDirectionality((char)7871));
        assertTrue(StringDataLetterUtil.isLetter((char)7871));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7871));
    }
    @Test
    public void d1631(){
        assertEq(1,StringDataUtil.getType((char)7872));
        assertEq(0,StringDataUtil.getDirectionality((char)7872));
        assertTrue(StringDataLetterUtil.isLetter((char)7872));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7872));
    }
    @Test
    public void d1632(){
        assertEq(2,StringDataUtil.getType((char)7873));
        assertEq(0,StringDataUtil.getDirectionality((char)7873));
        assertTrue(StringDataLetterUtil.isLetter((char)7873));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7873));
    }
    @Test
    public void d1633(){
        assertEq(1,StringDataUtil.getType((char)7874));
        assertEq(0,StringDataUtil.getDirectionality((char)7874));
        assertTrue(StringDataLetterUtil.isLetter((char)7874));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7874));
    }
    @Test
    public void d1634(){
        assertEq(2,StringDataUtil.getType((char)7875));
        assertEq(0,StringDataUtil.getDirectionality((char)7875));
        assertTrue(StringDataLetterUtil.isLetter((char)7875));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7875));
    }
    @Test
    public void d1635(){
        assertEq(1,StringDataUtil.getType((char)7876));
        assertEq(0,StringDataUtil.getDirectionality((char)7876));
        assertTrue(StringDataLetterUtil.isLetter((char)7876));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7876));
    }
    @Test
    public void d1636(){
        assertEq(2,StringDataUtil.getType((char)7877));
        assertEq(0,StringDataUtil.getDirectionality((char)7877));
        assertTrue(StringDataLetterUtil.isLetter((char)7877));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7877));
    }
    @Test
    public void d1637(){
        assertEq(1,StringDataUtil.getType((char)7878));
        assertEq(0,StringDataUtil.getDirectionality((char)7878));
        assertTrue(StringDataLetterUtil.isLetter((char)7878));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7878));
    }
    @Test
    public void d1638(){
        assertEq(2,StringDataUtil.getType((char)7879));
        assertEq(0,StringDataUtil.getDirectionality((char)7879));
        assertTrue(StringDataLetterUtil.isLetter((char)7879));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7879));
    }
    @Test
    public void d1639(){
        assertEq(1,StringDataUtil.getType((char)7880));
        assertEq(0,StringDataUtil.getDirectionality((char)7880));
        assertTrue(StringDataLetterUtil.isLetter((char)7880));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7880));
    }
    @Test
    public void d1640(){
        assertEq(2,StringDataUtil.getType((char)7881));
        assertEq(0,StringDataUtil.getDirectionality((char)7881));
        assertTrue(StringDataLetterUtil.isLetter((char)7881));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7881));
    }
    @Test
    public void d1641(){
        assertEq(1,StringDataUtil.getType((char)7882));
        assertEq(0,StringDataUtil.getDirectionality((char)7882));
        assertTrue(StringDataLetterUtil.isLetter((char)7882));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7882));
    }
    @Test
    public void d1642(){
        assertEq(2,StringDataUtil.getType((char)7883));
        assertEq(0,StringDataUtil.getDirectionality((char)7883));
        assertTrue(StringDataLetterUtil.isLetter((char)7883));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7883));
    }
    @Test
    public void d1643(){
        assertEq(1,StringDataUtil.getType((char)7884));
        assertEq(0,StringDataUtil.getDirectionality((char)7884));
        assertTrue(StringDataLetterUtil.isLetter((char)7884));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7884));
    }
    @Test
    public void d1644(){
        assertEq(2,StringDataUtil.getType((char)7885));
        assertEq(0,StringDataUtil.getDirectionality((char)7885));
        assertTrue(StringDataLetterUtil.isLetter((char)7885));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7885));
    }
    @Test
    public void d1645(){
        assertEq(1,StringDataUtil.getType((char)7886));
        assertEq(0,StringDataUtil.getDirectionality((char)7886));
        assertTrue(StringDataLetterUtil.isLetter((char)7886));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7886));
    }
    @Test
    public void d1646(){
        assertEq(2,StringDataUtil.getType((char)7887));
        assertEq(0,StringDataUtil.getDirectionality((char)7887));
        assertTrue(StringDataLetterUtil.isLetter((char)7887));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7887));
    }
    @Test
    public void d1647(){
        assertEq(1,StringDataUtil.getType((char)7888));
        assertEq(0,StringDataUtil.getDirectionality((char)7888));
        assertTrue(StringDataLetterUtil.isLetter((char)7888));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7888));
    }
    @Test
    public void d1648(){
        assertEq(2,StringDataUtil.getType((char)7889));
        assertEq(0,StringDataUtil.getDirectionality((char)7889));
        assertTrue(StringDataLetterUtil.isLetter((char)7889));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7889));
    }
    @Test
    public void d1649(){
        assertEq(1,StringDataUtil.getType((char)7890));
        assertEq(0,StringDataUtil.getDirectionality((char)7890));
        assertTrue(StringDataLetterUtil.isLetter((char)7890));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7890));
    }
    @Test
    public void d1650(){
        assertEq(2,StringDataUtil.getType((char)7891));
        assertEq(0,StringDataUtil.getDirectionality((char)7891));
        assertTrue(StringDataLetterUtil.isLetter((char)7891));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7891));
    }
    @Test
    public void d1651(){
        assertEq(1,StringDataUtil.getType((char)7892));
        assertEq(0,StringDataUtil.getDirectionality((char)7892));
        assertTrue(StringDataLetterUtil.isLetter((char)7892));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7892));
    }
    @Test
    public void d1652(){
        assertEq(2,StringDataUtil.getType((char)7893));
        assertEq(0,StringDataUtil.getDirectionality((char)7893));
        assertTrue(StringDataLetterUtil.isLetter((char)7893));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7893));
    }
    @Test
    public void d1653(){
        assertEq(1,StringDataUtil.getType((char)7894));
        assertEq(0,StringDataUtil.getDirectionality((char)7894));
        assertTrue(StringDataLetterUtil.isLetter((char)7894));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7894));
    }
    @Test
    public void d1654(){
        assertEq(2,StringDataUtil.getType((char)7895));
        assertEq(0,StringDataUtil.getDirectionality((char)7895));
        assertTrue(StringDataLetterUtil.isLetter((char)7895));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7895));
    }
    @Test
    public void d1655(){
        assertEq(1,StringDataUtil.getType((char)7896));
        assertEq(0,StringDataUtil.getDirectionality((char)7896));
        assertTrue(StringDataLetterUtil.isLetter((char)7896));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7896));
    }
    @Test
    public void d1656(){
        assertEq(2,StringDataUtil.getType((char)7897));
        assertEq(0,StringDataUtil.getDirectionality((char)7897));
        assertTrue(StringDataLetterUtil.isLetter((char)7897));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7897));
    }
    @Test
    public void d1657(){
        assertEq(1,StringDataUtil.getType((char)7898));
        assertEq(0,StringDataUtil.getDirectionality((char)7898));
        assertTrue(StringDataLetterUtil.isLetter((char)7898));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7898));
    }
    @Test
    public void d1658(){
        assertEq(2,StringDataUtil.getType((char)7899));
        assertEq(0,StringDataUtil.getDirectionality((char)7899));
        assertTrue(StringDataLetterUtil.isLetter((char)7899));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7899));
    }
    @Test
    public void d1659(){
        assertEq(1,StringDataUtil.getType((char)7900));
        assertEq(0,StringDataUtil.getDirectionality((char)7900));
        assertTrue(StringDataLetterUtil.isLetter((char)7900));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7900));
    }
    @Test
    public void d1660(){
        assertEq(2,StringDataUtil.getType((char)7901));
        assertEq(0,StringDataUtil.getDirectionality((char)7901));
        assertTrue(StringDataLetterUtil.isLetter((char)7901));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7901));
    }
    @Test
    public void d1661(){
        assertEq(1,StringDataUtil.getType((char)7902));
        assertEq(0,StringDataUtil.getDirectionality((char)7902));
        assertTrue(StringDataLetterUtil.isLetter((char)7902));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7902));
    }
    @Test
    public void d1662(){
        assertEq(2,StringDataUtil.getType((char)7903));
        assertEq(0,StringDataUtil.getDirectionality((char)7903));
        assertTrue(StringDataLetterUtil.isLetter((char)7903));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7903));
    }
    @Test
    public void d1663(){
        assertEq(1,StringDataUtil.getType((char)7904));
        assertEq(0,StringDataUtil.getDirectionality((char)7904));
        assertTrue(StringDataLetterUtil.isLetter((char)7904));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7904));
    }
    @Test
    public void d1664(){
        assertEq(2,StringDataUtil.getType((char)7905));
        assertEq(0,StringDataUtil.getDirectionality((char)7905));
        assertTrue(StringDataLetterUtil.isLetter((char)7905));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7905));
    }
    @Test
    public void d1665(){
        assertEq(1,StringDataUtil.getType((char)7906));
        assertEq(0,StringDataUtil.getDirectionality((char)7906));
        assertTrue(StringDataLetterUtil.isLetter((char)7906));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7906));
    }
    @Test
    public void d1666(){
        assertEq(2,StringDataUtil.getType((char)7907));
        assertEq(0,StringDataUtil.getDirectionality((char)7907));
        assertTrue(StringDataLetterUtil.isLetter((char)7907));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7907));
    }
    @Test
    public void d1667(){
        assertEq(1,StringDataUtil.getType((char)7908));
        assertEq(0,StringDataUtil.getDirectionality((char)7908));
        assertTrue(StringDataLetterUtil.isLetter((char)7908));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7908));
    }
    @Test
    public void d1668(){
        assertEq(2,StringDataUtil.getType((char)7909));
        assertEq(0,StringDataUtil.getDirectionality((char)7909));
        assertTrue(StringDataLetterUtil.isLetter((char)7909));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7909));
    }
    @Test
    public void d1669(){
        assertEq(1,StringDataUtil.getType((char)7910));
        assertEq(0,StringDataUtil.getDirectionality((char)7910));
        assertTrue(StringDataLetterUtil.isLetter((char)7910));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7910));
    }
    @Test
    public void d1670(){
        assertEq(2,StringDataUtil.getType((char)7911));
        assertEq(0,StringDataUtil.getDirectionality((char)7911));
        assertTrue(StringDataLetterUtil.isLetter((char)7911));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7911));
    }
    @Test
    public void d1671(){
        assertEq(1,StringDataUtil.getType((char)7912));
        assertEq(0,StringDataUtil.getDirectionality((char)7912));
        assertTrue(StringDataLetterUtil.isLetter((char)7912));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7912));
    }
    @Test
    public void d1672(){
        assertEq(2,StringDataUtil.getType((char)7913));
        assertEq(0,StringDataUtil.getDirectionality((char)7913));
        assertTrue(StringDataLetterUtil.isLetter((char)7913));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7913));
    }
    @Test
    public void d1673(){
        assertEq(1,StringDataUtil.getType((char)7914));
        assertEq(0,StringDataUtil.getDirectionality((char)7914));
        assertTrue(StringDataLetterUtil.isLetter((char)7914));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7914));
    }
    @Test
    public void d1674(){
        assertEq(2,StringDataUtil.getType((char)7915));
        assertEq(0,StringDataUtil.getDirectionality((char)7915));
        assertTrue(StringDataLetterUtil.isLetter((char)7915));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7915));
    }
    @Test
    public void d1675(){
        assertEq(1,StringDataUtil.getType((char)7916));
        assertEq(0,StringDataUtil.getDirectionality((char)7916));
        assertTrue(StringDataLetterUtil.isLetter((char)7916));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7916));
    }
    @Test
    public void d1676(){
        assertEq(2,StringDataUtil.getType((char)7917));
        assertEq(0,StringDataUtil.getDirectionality((char)7917));
        assertTrue(StringDataLetterUtil.isLetter((char)7917));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7917));
    }
    @Test
    public void d1677(){
        assertEq(1,StringDataUtil.getType((char)7918));
        assertEq(0,StringDataUtil.getDirectionality((char)7918));
        assertTrue(StringDataLetterUtil.isLetter((char)7918));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7918));
    }
    @Test
    public void d1678(){
        assertEq(2,StringDataUtil.getType((char)7919));
        assertEq(0,StringDataUtil.getDirectionality((char)7919));
        assertTrue(StringDataLetterUtil.isLetter((char)7919));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7919));
    }
    @Test
    public void d1679(){
        assertEq(1,StringDataUtil.getType((char)7920));
        assertEq(0,StringDataUtil.getDirectionality((char)7920));
        assertTrue(StringDataLetterUtil.isLetter((char)7920));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7920));
    }
    @Test
    public void d1680(){
        assertEq(2,StringDataUtil.getType((char)7921));
        assertEq(0,StringDataUtil.getDirectionality((char)7921));
        assertTrue(StringDataLetterUtil.isLetter((char)7921));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7921));
    }
    @Test
    public void d1681(){
        assertEq(1,StringDataUtil.getType((char)7922));
        assertEq(0,StringDataUtil.getDirectionality((char)7922));
        assertTrue(StringDataLetterUtil.isLetter((char)7922));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7922));
    }
    @Test
    public void d1682(){
        assertEq(2,StringDataUtil.getType((char)7923));
        assertEq(0,StringDataUtil.getDirectionality((char)7923));
        assertTrue(StringDataLetterUtil.isLetter((char)7923));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7923));
    }
    @Test
    public void d1683(){
        assertEq(1,StringDataUtil.getType((char)7924));
        assertEq(0,StringDataUtil.getDirectionality((char)7924));
        assertTrue(StringDataLetterUtil.isLetter((char)7924));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7924));
    }
    @Test
    public void d1684(){
        assertEq(2,StringDataUtil.getType((char)7925));
        assertEq(0,StringDataUtil.getDirectionality((char)7925));
        assertTrue(StringDataLetterUtil.isLetter((char)7925));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7925));
    }
    @Test
    public void d1685(){
        assertEq(1,StringDataUtil.getType((char)7926));
        assertEq(0,StringDataUtil.getDirectionality((char)7926));
        assertTrue(StringDataLetterUtil.isLetter((char)7926));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7926));
    }
    @Test
    public void d1686(){
        assertEq(2,StringDataUtil.getType((char)7927));
        assertEq(0,StringDataUtil.getDirectionality((char)7927));
        assertTrue(StringDataLetterUtil.isLetter((char)7927));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7927));
    }
    @Test
    public void d1687(){
        assertEq(1,StringDataUtil.getType((char)7928));
        assertEq(0,StringDataUtil.getDirectionality((char)7928));
        assertTrue(StringDataLetterUtil.isLetter((char)7928));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7928));
    }
    @Test
    public void d1688(){
        assertEq(2,StringDataUtil.getType((char)7929));
        assertEq(0,StringDataUtil.getDirectionality((char)7929));
        assertTrue(StringDataLetterUtil.isLetter((char)7929));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7929));
    }
    @Test
    public void d1689(){
        assertEq(1,StringDataUtil.getType((char)7930));
        assertEq(0,StringDataUtil.getDirectionality((char)7930));
        assertTrue(StringDataLetterUtil.isLetter((char)7930));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7930));
    }
    @Test
    public void d1690(){
        assertEq(2,StringDataUtil.getType((char)7931));
        assertEq(0,StringDataUtil.getDirectionality((char)7931));
        assertTrue(StringDataLetterUtil.isLetter((char)7931));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7931));
    }
    @Test
    public void d1691(){
        assertEq(1,StringDataUtil.getType((char)7932));
        assertEq(0,StringDataUtil.getDirectionality((char)7932));
        assertTrue(StringDataLetterUtil.isLetter((char)7932));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7932));
    }
    @Test
    public void d1692(){
        assertEq(2,StringDataUtil.getType((char)7933));
        assertEq(0,StringDataUtil.getDirectionality((char)7933));
        assertTrue(StringDataLetterUtil.isLetter((char)7933));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7933));
    }
    @Test
    public void d1693(){
        assertEq(1,StringDataUtil.getType((char)7934));
        assertEq(0,StringDataUtil.getDirectionality((char)7934));
        assertTrue(StringDataLetterUtil.isLetter((char)7934));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7934));
    }
    @Test
    public void d1694(){
        assertEq(2,StringDataUtil.getType((char)7935));
        assertEq(0,StringDataUtil.getDirectionality((char)7935));
        assertTrue(StringDataLetterUtil.isLetter((char)7935));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7935));
    }
    @Test
    public void d1695(){
        assertEq(1,StringDataUtil.getType((char)7944));
        assertEq(0,StringDataUtil.getDirectionality((char)7944));
        assertTrue(StringDataLetterUtil.isLetter((char)7944));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7944));
    }
    @Test
    public void d1696(){
        assertEq(2,StringDataUtil.getType((char)7952));
        assertEq(0,StringDataUtil.getDirectionality((char)7952));
        assertTrue(StringDataLetterUtil.isLetter((char)7952));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7952));
    }
    @Test
    public void d1697(){
        assertEq(0,StringDataUtil.getType((char)7958));
        assertEq(-1,StringDataUtil.getDirectionality((char)7958));
        assertFalse(StringDataLetterUtil.isLetter((char)7958));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7958));
    }
    @Test
    public void d1698(){
        assertEq(1,StringDataUtil.getType((char)7960));
        assertEq(0,StringDataUtil.getDirectionality((char)7960));
        assertTrue(StringDataLetterUtil.isLetter((char)7960));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7960));
    }
    @Test
    public void d1699(){
        assertEq(0,StringDataUtil.getType((char)7966));
        assertEq(-1,StringDataUtil.getDirectionality((char)7966));
        assertFalse(StringDataLetterUtil.isLetter((char)7966));
        assertFalse(StringDataUtil.isLetterOrDigit((char)7966));
    }
    @Test
    public void d1700(){
        assertEq(2,StringDataUtil.getType((char)7968));
        assertEq(0,StringDataUtil.getDirectionality((char)7968));
        assertTrue(StringDataLetterUtil.isLetter((char)7968));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7968));
    }
    @Test
    public void d1701(){
        assertEq(1,StringDataUtil.getType((char)7976));
        assertEq(0,StringDataUtil.getDirectionality((char)7976));
        assertTrue(StringDataLetterUtil.isLetter((char)7976));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7976));
    }
    @Test
    public void d1702(){
        assertEq(2,StringDataUtil.getType((char)7984));
        assertEq(0,StringDataUtil.getDirectionality((char)7984));
        assertTrue(StringDataLetterUtil.isLetter((char)7984));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7984));
    }
    @Test
    public void d1703(){
        assertEq(1,StringDataUtil.getType((char)7992));
        assertEq(0,StringDataUtil.getDirectionality((char)7992));
        assertTrue(StringDataLetterUtil.isLetter((char)7992));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7992));
    }
    @Test
    public void d1704(){
        assertEq(2,StringDataUtil.getType((char)8000));
        assertEq(0,StringDataUtil.getDirectionality((char)8000));
        assertTrue(StringDataLetterUtil.isLetter((char)8000));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8000));
    }
    @Test
    public void d1705(){
        assertEq(0,StringDataUtil.getType((char)8006));
        assertEq(-1,StringDataUtil.getDirectionality((char)8006));
        assertFalse(StringDataLetterUtil.isLetter((char)8006));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8006));
    }
    @Test
    public void d1706(){
        assertEq(1,StringDataUtil.getType((char)8008));
        assertEq(0,StringDataUtil.getDirectionality((char)8008));
        assertTrue(StringDataLetterUtil.isLetter((char)8008));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8008));
    }
    @Test
    public void d1707(){
        assertEq(0,StringDataUtil.getType((char)8014));
        assertEq(-1,StringDataUtil.getDirectionality((char)8014));
        assertFalse(StringDataLetterUtil.isLetter((char)8014));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8014));
    }
    @Test
    public void d1708(){
        assertEq(2,StringDataUtil.getType((char)8016));
        assertEq(0,StringDataUtil.getDirectionality((char)8016));
        assertTrue(StringDataLetterUtil.isLetter((char)8016));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8016));
    }
    @Test
    public void d1709(){
        assertEq(0,StringDataUtil.getType((char)8024));
        assertEq(-1,StringDataUtil.getDirectionality((char)8024));
        assertFalse(StringDataLetterUtil.isLetter((char)8024));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8024));
    }
    @Test
    public void d1710(){
        assertEq(1,StringDataUtil.getType((char)8025));
        assertEq(0,StringDataUtil.getDirectionality((char)8025));
        assertTrue(StringDataLetterUtil.isLetter((char)8025));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8025));
    }
    @Test
    public void d1711(){
        assertEq(0,StringDataUtil.getType((char)8026));
        assertEq(-1,StringDataUtil.getDirectionality((char)8026));
        assertFalse(StringDataLetterUtil.isLetter((char)8026));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8026));
    }
    @Test
    public void d1712(){
        assertEq(1,StringDataUtil.getType((char)8027));
        assertEq(0,StringDataUtil.getDirectionality((char)8027));
        assertTrue(StringDataLetterUtil.isLetter((char)8027));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8027));
    }
    @Test
    public void d1713(){
        assertEq(0,StringDataUtil.getType((char)8028));
        assertEq(-1,StringDataUtil.getDirectionality((char)8028));
        assertFalse(StringDataLetterUtil.isLetter((char)8028));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8028));
    }
    @Test
    public void d1714(){
        assertEq(1,StringDataUtil.getType((char)8029));
        assertEq(0,StringDataUtil.getDirectionality((char)8029));
        assertTrue(StringDataLetterUtil.isLetter((char)8029));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8029));
    }
    @Test
    public void d1715(){
        assertEq(0,StringDataUtil.getType((char)8030));
        assertEq(-1,StringDataUtil.getDirectionality((char)8030));
        assertFalse(StringDataLetterUtil.isLetter((char)8030));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8030));
    }
    @Test
    public void d1716(){
        assertEq(1,StringDataUtil.getType((char)8031));
        assertEq(0,StringDataUtil.getDirectionality((char)8031));
        assertTrue(StringDataLetterUtil.isLetter((char)8031));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8031));
    }
    @Test
    public void d1717(){
        assertEq(2,StringDataUtil.getType((char)8032));
        assertEq(0,StringDataUtil.getDirectionality((char)8032));
        assertTrue(StringDataLetterUtil.isLetter((char)8032));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8032));
    }
    @Test
    public void d1718(){
        assertEq(1,StringDataUtil.getType((char)8040));
        assertEq(0,StringDataUtil.getDirectionality((char)8040));
        assertTrue(StringDataLetterUtil.isLetter((char)8040));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8040));
    }
    @Test
    public void d1719(){
        assertEq(2,StringDataUtil.getType((char)8048));
        assertEq(0,StringDataUtil.getDirectionality((char)8048));
        assertTrue(StringDataLetterUtil.isLetter((char)8048));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8048));
    }
    @Test
    public void d1720(){
        assertEq(0,StringDataUtil.getType((char)8062));
        assertEq(-1,StringDataUtil.getDirectionality((char)8062));
        assertFalse(StringDataLetterUtil.isLetter((char)8062));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8062));
    }
    @Test
    public void d1721(){
        assertEq(2,StringDataUtil.getType((char)8064));
        assertEq(0,StringDataUtil.getDirectionality((char)8064));
        assertTrue(StringDataLetterUtil.isLetter((char)8064));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8064));
    }
    @Test
    public void d1722(){
        assertEq(3,StringDataUtil.getType((char)8072));
        assertEq(0,StringDataUtil.getDirectionality((char)8072));
        assertTrue(StringDataLetterUtil.isLetter((char)8072));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8072));
    }
    @Test
    public void d1723(){
        assertEq(2,StringDataUtil.getType((char)8080));
        assertEq(0,StringDataUtil.getDirectionality((char)8080));
        assertTrue(StringDataLetterUtil.isLetter((char)8080));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8080));
    }
    @Test
    public void d1724(){
        assertEq(3,StringDataUtil.getType((char)8088));
        assertEq(0,StringDataUtil.getDirectionality((char)8088));
        assertTrue(StringDataLetterUtil.isLetter((char)8088));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8088));
    }
    @Test
    public void d1725(){
        assertEq(2,StringDataUtil.getType((char)8096));
        assertEq(0,StringDataUtil.getDirectionality((char)8096));
        assertTrue(StringDataLetterUtil.isLetter((char)8096));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8096));
    }
    @Test
    public void d1726(){
        assertEq(3,StringDataUtil.getType((char)8104));
        assertEq(0,StringDataUtil.getDirectionality((char)8104));
        assertTrue(StringDataLetterUtil.isLetter((char)8104));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8104));
    }
    @Test
    public void d1727(){
        assertEq(2,StringDataUtil.getType((char)8112));
        assertEq(0,StringDataUtil.getDirectionality((char)8112));
        assertTrue(StringDataLetterUtil.isLetter((char)8112));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8112));
    }
    @Test
    public void d1728(){
        assertEq(0,StringDataUtil.getType((char)8117));
        assertEq(-1,StringDataUtil.getDirectionality((char)8117));
        assertFalse(StringDataLetterUtil.isLetter((char)8117));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8117));
    }
    @Test
    public void d1729(){
        assertEq(2,StringDataUtil.getType((char)8118));
        assertEq(0,StringDataUtil.getDirectionality((char)8118));
        assertTrue(StringDataLetterUtil.isLetter((char)8118));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8118));
    }
    @Test
    public void d1730(){
        assertEq(1,StringDataUtil.getType((char)8120));
        assertEq(0,StringDataUtil.getDirectionality((char)8120));
        assertTrue(StringDataLetterUtil.isLetter((char)8120));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8120));
    }
    @Test
    public void d1731(){
        assertEq(3,StringDataUtil.getType((char)8124));
        assertEq(0,StringDataUtil.getDirectionality((char)8124));
        assertTrue(StringDataLetterUtil.isLetter((char)8124));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8124));
    }
    @Test
    public void d1732(){
        assertEq(27,StringDataUtil.getType((char)8125));
        assertEq(13,StringDataUtil.getDirectionality((char)8125));
        assertFalse(StringDataLetterUtil.isLetter((char)8125));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8125));
    }
    @Test
    public void d1733(){
        assertEq(2,StringDataUtil.getType((char)8126));
        assertEq(0,StringDataUtil.getDirectionality((char)8126));
        assertTrue(StringDataLetterUtil.isLetter((char)8126));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8126));
    }
    @Test
    public void d1734(){
        assertEq(27,StringDataUtil.getType((char)8127));
        assertEq(13,StringDataUtil.getDirectionality((char)8127));
        assertFalse(StringDataLetterUtil.isLetter((char)8127));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8127));
    }
    @Test
    public void d1735(){
        assertEq(2,StringDataUtil.getType((char)8130));
        assertEq(0,StringDataUtil.getDirectionality((char)8130));
        assertTrue(StringDataLetterUtil.isLetter((char)8130));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8130));
    }
    @Test
    public void d1736(){
        assertEq(0,StringDataUtil.getType((char)8133));
        assertEq(-1,StringDataUtil.getDirectionality((char)8133));
        assertFalse(StringDataLetterUtil.isLetter((char)8133));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8133));
    }
    @Test
    public void d1737(){
        assertEq(2,StringDataUtil.getType((char)8134));
        assertEq(0,StringDataUtil.getDirectionality((char)8134));
        assertTrue(StringDataLetterUtil.isLetter((char)8134));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8134));
    }
    @Test
    public void d1738(){
        assertEq(1,StringDataUtil.getType((char)8136));
        assertEq(0,StringDataUtil.getDirectionality((char)8136));
        assertTrue(StringDataLetterUtil.isLetter((char)8136));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8136));
    }
    @Test
    public void d1739(){
        assertEq(3,StringDataUtil.getType((char)8140));
        assertEq(0,StringDataUtil.getDirectionality((char)8140));
        assertTrue(StringDataLetterUtil.isLetter((char)8140));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8140));
    }
    @Test
    public void d1740(){
        assertEq(27,StringDataUtil.getType((char)8141));
        assertEq(13,StringDataUtil.getDirectionality((char)8141));
        assertFalse(StringDataLetterUtil.isLetter((char)8141));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8141));
    }
    @Test
    public void d1741(){
        assertEq(2,StringDataUtil.getType((char)8144));
        assertEq(0,StringDataUtil.getDirectionality((char)8144));
        assertTrue(StringDataLetterUtil.isLetter((char)8144));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8144));
    }
    @Test
    public void d1742(){
        assertEq(0,StringDataUtil.getType((char)8148));
        assertEq(-1,StringDataUtil.getDirectionality((char)8148));
        assertFalse(StringDataLetterUtil.isLetter((char)8148));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8148));
    }
    @Test
    public void d1743(){
        assertEq(2,StringDataUtil.getType((char)8150));
        assertEq(0,StringDataUtil.getDirectionality((char)8150));
        assertTrue(StringDataLetterUtil.isLetter((char)8150));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8150));
    }
    @Test
    public void d1744(){
        assertEq(1,StringDataUtil.getType((char)8152));
        assertEq(0,StringDataUtil.getDirectionality((char)8152));
        assertTrue(StringDataLetterUtil.isLetter((char)8152));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8152));
    }
    @Test
    public void d1745(){
        assertEq(0,StringDataUtil.getType((char)8156));
        assertEq(-1,StringDataUtil.getDirectionality((char)8156));
        assertFalse(StringDataLetterUtil.isLetter((char)8156));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8156));
    }
    @Test
    public void d1746(){
        assertEq(27,StringDataUtil.getType((char)8157));
        assertEq(13,StringDataUtil.getDirectionality((char)8157));
        assertFalse(StringDataLetterUtil.isLetter((char)8157));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8157));
    }
    @Test
    public void d1747(){
        assertEq(2,StringDataUtil.getType((char)8160));
        assertEq(0,StringDataUtil.getDirectionality((char)8160));
        assertTrue(StringDataLetterUtil.isLetter((char)8160));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8160));
    }
    @Test
    public void d1748(){
        assertEq(1,StringDataUtil.getType((char)8168));
        assertEq(0,StringDataUtil.getDirectionality((char)8168));
        assertTrue(StringDataLetterUtil.isLetter((char)8168));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8168));
    }
    @Test
    public void d1749(){
        assertEq(27,StringDataUtil.getType((char)8173));
        assertEq(13,StringDataUtil.getDirectionality((char)8173));
        assertFalse(StringDataLetterUtil.isLetter((char)8173));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8173));
    }
    @Test
    public void d1750(){
        assertEq(0,StringDataUtil.getType((char)8176));
        assertEq(-1,StringDataUtil.getDirectionality((char)8176));
        assertFalse(StringDataLetterUtil.isLetter((char)8176));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8176));
    }
    @Test
    public void d1751(){
        assertEq(2,StringDataUtil.getType((char)8178));
        assertEq(0,StringDataUtil.getDirectionality((char)8178));
        assertTrue(StringDataLetterUtil.isLetter((char)8178));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8178));
    }
    @Test
    public void d1752(){
        assertEq(0,StringDataUtil.getType((char)8181));
        assertEq(-1,StringDataUtil.getDirectionality((char)8181));
        assertFalse(StringDataLetterUtil.isLetter((char)8181));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8181));
    }
    @Test
    public void d1753(){
        assertEq(2,StringDataUtil.getType((char)8182));
        assertEq(0,StringDataUtil.getDirectionality((char)8182));
        assertTrue(StringDataLetterUtil.isLetter((char)8182));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8182));
    }
    @Test
    public void d1754(){
        assertEq(1,StringDataUtil.getType((char)8184));
        assertEq(0,StringDataUtil.getDirectionality((char)8184));
        assertTrue(StringDataLetterUtil.isLetter((char)8184));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8184));
    }
    @Test
    public void d1755(){
        assertEq(3,StringDataUtil.getType((char)8188));
        assertEq(0,StringDataUtil.getDirectionality((char)8188));
        assertTrue(StringDataLetterUtil.isLetter((char)8188));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8188));
    }
    @Test
    public void d1756(){
        assertEq(27,StringDataUtil.getType((char)8189));
        assertEq(13,StringDataUtil.getDirectionality((char)8189));
        assertFalse(StringDataLetterUtil.isLetter((char)8189));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8189));
    }
    @Test
    public void d1757(){
        assertEq(0,StringDataUtil.getType((char)8191));
        assertEq(-1,StringDataUtil.getDirectionality((char)8191));
        assertFalse(StringDataLetterUtil.isLetter((char)8191));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8191));
    }
    @Test
    public void d1758(){
        assertEq(12,StringDataUtil.getType((char)8192));
        assertEq(12,StringDataUtil.getDirectionality((char)8192));
        assertFalse(StringDataLetterUtil.isLetter((char)8192));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8192));
    }
    @Test
    public void d1759(){
        assertEq(16,StringDataUtil.getType((char)8203));
        assertEq(9,StringDataUtil.getDirectionality((char)8203));
        assertFalse(StringDataLetterUtil.isLetter((char)8203));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8203));
    }
    @Test
    public void d1760(){
        assertEq(20,StringDataUtil.getType((char)8208));
        assertEq(13,StringDataUtil.getDirectionality((char)8208));
        assertFalse(StringDataLetterUtil.isLetter((char)8208));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8208));
    }
    @Test
    public void d1761(){
        assertEq(24,StringDataUtil.getType((char)8214));
        assertEq(13,StringDataUtil.getDirectionality((char)8214));
        assertFalse(StringDataLetterUtil.isLetter((char)8214));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8214));
    }
    @Test
    public void d1762(){
        assertEq(29,StringDataUtil.getType((char)8216));
        assertEq(13,StringDataUtil.getDirectionality((char)8216));
        assertFalse(StringDataLetterUtil.isLetter((char)8216));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8216));
    }
    @Test
    public void d1763(){
        assertEq(30,StringDataUtil.getType((char)8217));
        assertEq(13,StringDataUtil.getDirectionality((char)8217));
        assertFalse(StringDataLetterUtil.isLetter((char)8217));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8217));
    }
    @Test
    public void d1764(){
        assertEq(21,StringDataUtil.getType((char)8218));
        assertEq(13,StringDataUtil.getDirectionality((char)8218));
        assertFalse(StringDataLetterUtil.isLetter((char)8218));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8218));
    }
    @Test
    public void d1765(){
        assertEq(29,StringDataUtil.getType((char)8219));
        assertEq(13,StringDataUtil.getDirectionality((char)8219));
        assertFalse(StringDataLetterUtil.isLetter((char)8219));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8219));
    }
    @Test
    public void d1766(){
        assertEq(30,StringDataUtil.getType((char)8221));
        assertEq(13,StringDataUtil.getDirectionality((char)8221));
        assertFalse(StringDataLetterUtil.isLetter((char)8221));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8221));
    }
    @Test
    public void d1767(){
        assertEq(21,StringDataUtil.getType((char)8222));
        assertEq(13,StringDataUtil.getDirectionality((char)8222));
        assertFalse(StringDataLetterUtil.isLetter((char)8222));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8222));
    }
    @Test
    public void d1768(){
        assertEq(29,StringDataUtil.getType((char)8223));
        assertEq(13,StringDataUtil.getDirectionality((char)8223));
        assertFalse(StringDataLetterUtil.isLetter((char)8223));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8223));
    }
    @Test
    public void d1769(){
        assertEq(24,StringDataUtil.getType((char)8224));
        assertEq(13,StringDataUtil.getDirectionality((char)8224));
        assertFalse(StringDataLetterUtil.isLetter((char)8224));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8224));
    }
    @Test
    public void d1770(){
        assertEq(13,StringDataUtil.getType((char)8232));
        assertEq(12,StringDataUtil.getDirectionality((char)8232));
        assertFalse(StringDataLetterUtil.isLetter((char)8232));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8232));
    }
    @Test
    public void d1771(){
        assertEq(14,StringDataUtil.getType((char)8233));
        assertEq(10,StringDataUtil.getDirectionality((char)8233));
        assertFalse(StringDataLetterUtil.isLetter((char)8233));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8233));
    }
    @Test
    public void d1772(){
        assertEq(16,StringDataUtil.getType((char)8234));
        assertEq(14,StringDataUtil.getDirectionality((char)8234));
        assertFalse(StringDataLetterUtil.isLetter((char)8234));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8234));
    }
    @Test
    public void d1773(){
        assertEq(12,StringDataUtil.getType((char)8239));
        assertEq(7,StringDataUtil.getDirectionality((char)8239));
        assertFalse(StringDataLetterUtil.isLetter((char)8239));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8239));
    }
    @Test
    public void d1774(){
        assertEq(24,StringDataUtil.getType((char)8240));
        assertEq(5,StringDataUtil.getDirectionality((char)8240));
        assertFalse(StringDataLetterUtil.isLetter((char)8240));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8240));
    }
    @Test
    public void d1775(){
        assertEq(29,StringDataUtil.getType((char)8249));
        assertEq(13,StringDataUtil.getDirectionality((char)8249));
        assertFalse(StringDataLetterUtil.isLetter((char)8249));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8249));
    }
    @Test
    public void d1776(){
        assertEq(30,StringDataUtil.getType((char)8250));
        assertEq(13,StringDataUtil.getDirectionality((char)8250));
        assertFalse(StringDataLetterUtil.isLetter((char)8250));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8250));
    }
    @Test
    public void d1777(){
        assertEq(24,StringDataUtil.getType((char)8251));
        assertEq(13,StringDataUtil.getDirectionality((char)8251));
        assertFalse(StringDataLetterUtil.isLetter((char)8251));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8251));
    }
    @Test
    public void d1778(){
        assertEq(23,StringDataUtil.getType((char)8255));
        assertEq(13,StringDataUtil.getDirectionality((char)8255));
        assertFalse(StringDataLetterUtil.isLetter((char)8255));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8255));
    }
    @Test
    public void d1779(){
        assertEq(24,StringDataUtil.getType((char)8257));
        assertEq(13,StringDataUtil.getDirectionality((char)8257));
        assertFalse(StringDataLetterUtil.isLetter((char)8257));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8257));
    }
    @Test
    public void d1780(){
        assertEq(25,StringDataUtil.getType((char)8260));
        assertEq(7,StringDataUtil.getDirectionality((char)8260));
        assertFalse(StringDataLetterUtil.isLetter((char)8260));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8260));
    }
    @Test
    public void d1781(){
        assertEq(21,StringDataUtil.getType((char)8261));
        assertEq(13,StringDataUtil.getDirectionality((char)8261));
        assertFalse(StringDataLetterUtil.isLetter((char)8261));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8261));
    }
    @Test
    public void d1782(){
        assertEq(22,StringDataUtil.getType((char)8262));
        assertEq(13,StringDataUtil.getDirectionality((char)8262));
        assertFalse(StringDataLetterUtil.isLetter((char)8262));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8262));
    }
    @Test
    public void d1783(){
        assertEq(24,StringDataUtil.getType((char)8263));
        assertEq(13,StringDataUtil.getDirectionality((char)8263));
        assertFalse(StringDataLetterUtil.isLetter((char)8263));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8263));
    }
    @Test
    public void d1784(){
        assertEq(25,StringDataUtil.getType((char)8274));
        assertEq(13,StringDataUtil.getDirectionality((char)8274));
        assertFalse(StringDataLetterUtil.isLetter((char)8274));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8274));
    }
    @Test
    public void d1785(){
        assertEq(24,StringDataUtil.getType((char)8275));
        assertEq(13,StringDataUtil.getDirectionality((char)8275));
        assertFalse(StringDataLetterUtil.isLetter((char)8275));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8275));
    }
    @Test
    public void d1786(){
        assertEq(23,StringDataUtil.getType((char)8276));
        assertEq(13,StringDataUtil.getDirectionality((char)8276));
        assertFalse(StringDataLetterUtil.isLetter((char)8276));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8276));
    }
    @Test
    public void d1787(){
        assertEq(24,StringDataUtil.getType((char)8277));
        assertEq(13,StringDataUtil.getDirectionality((char)8277));
        assertFalse(StringDataLetterUtil.isLetter((char)8277));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8277));
    }
    @Test
    public void d1788(){
        assertEq(12,StringDataUtil.getType((char)8287));
        assertEq(12,StringDataUtil.getDirectionality((char)8287));
        assertFalse(StringDataLetterUtil.isLetter((char)8287));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8287));
    }
    @Test
    public void d1789(){
        assertEq(16,StringDataUtil.getType((char)8288));
        assertEq(9,StringDataUtil.getDirectionality((char)8288));
        assertFalse(StringDataLetterUtil.isLetter((char)8288));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8288));
    }
    @Test
    public void d1790(){
        assertEq(0,StringDataUtil.getType((char)8293));
        assertEq(-1,StringDataUtil.getDirectionality((char)8293));
        assertFalse(StringDataLetterUtil.isLetter((char)8293));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8293));
    }
    @Test
    public void d1791(){
        assertEq(16,StringDataUtil.getType((char)8298));
        assertEq(9,StringDataUtil.getDirectionality((char)8298));
        assertFalse(StringDataLetterUtil.isLetter((char)8298));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8298));
    }
    @Test
    public void d1792(){
        assertEq(11,StringDataUtil.getType((char)8304));
        assertEq(3,StringDataUtil.getDirectionality((char)8304));
        assertFalse(StringDataLetterUtil.isLetter((char)8304));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8304));
    }
    @Test
    public void d1793(){
        assertEq(4,StringDataUtil.getType((char)8305));
        assertEq(0,StringDataUtil.getDirectionality((char)8305));
        assertTrue(StringDataLetterUtil.isLetter((char)8305));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8305));
    }
    @Test
    public void d1794(){
        assertEq(0,StringDataUtil.getType((char)8306));
        assertEq(-1,StringDataUtil.getDirectionality((char)8306));
        assertFalse(StringDataLetterUtil.isLetter((char)8306));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8306));
    }
    @Test
    public void d1795(){
        assertEq(11,StringDataUtil.getType((char)8308));
        assertEq(3,StringDataUtil.getDirectionality((char)8308));
        assertFalse(StringDataLetterUtil.isLetter((char)8308));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8308));
    }
    @Test
    public void d1796(){
        assertEq(25,StringDataUtil.getType((char)8314));
        assertEq(4,StringDataUtil.getDirectionality((char)8314));
        assertFalse(StringDataLetterUtil.isLetter((char)8314));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8314));
    }
    @Test
    public void d1797(){
        assertEq(21,StringDataUtil.getType((char)8317));
        assertEq(13,StringDataUtil.getDirectionality((char)8317));
        assertFalse(StringDataLetterUtil.isLetter((char)8317));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8317));
    }
    @Test
    public void d1798(){
        assertEq(22,StringDataUtil.getType((char)8318));
        assertEq(13,StringDataUtil.getDirectionality((char)8318));
        assertFalse(StringDataLetterUtil.isLetter((char)8318));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8318));
    }
    @Test
    public void d1799(){
        assertEq(4,StringDataUtil.getType((char)8319));
        assertEq(0,StringDataUtil.getDirectionality((char)8319));
        assertTrue(StringDataLetterUtil.isLetter((char)8319));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8319));
    }
    @Test
    public void d1800(){
        assertEq(11,StringDataUtil.getType((char)8320));
        assertEq(3,StringDataUtil.getDirectionality((char)8320));
        assertFalse(StringDataLetterUtil.isLetter((char)8320));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8320));
    }
    @Test
    public void d1801(){
        assertEq(25,StringDataUtil.getType((char)8330));
        assertEq(4,StringDataUtil.getDirectionality((char)8330));
        assertFalse(StringDataLetterUtil.isLetter((char)8330));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8330));
    }
    @Test
    public void d1802(){
        assertEq(21,StringDataUtil.getType((char)8333));
        assertEq(13,StringDataUtil.getDirectionality((char)8333));
        assertFalse(StringDataLetterUtil.isLetter((char)8333));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8333));
    }
    @Test
    public void d1803(){
        assertEq(22,StringDataUtil.getType((char)8334));
        assertEq(13,StringDataUtil.getDirectionality((char)8334));
        assertFalse(StringDataLetterUtil.isLetter((char)8334));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8334));
    }
    @Test
    public void d1804(){
        assertEq(0,StringDataUtil.getType((char)8335));
        assertEq(-1,StringDataUtil.getDirectionality((char)8335));
        assertFalse(StringDataLetterUtil.isLetter((char)8335));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8335));
    }
    @Test
    public void d1805(){
        assertEq(4,StringDataUtil.getType((char)8336));
        assertEq(0,StringDataUtil.getDirectionality((char)8336));
        assertTrue(StringDataLetterUtil.isLetter((char)8336));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8336));
    }
    @Test
    public void d1806(){
        assertEq(0,StringDataUtil.getType((char)8349));
        assertEq(-1,StringDataUtil.getDirectionality((char)8349));
        assertFalse(StringDataLetterUtil.isLetter((char)8349));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8349));
    }
    @Test
    public void d1807(){
        assertEq(26,StringDataUtil.getType((char)8352));
        assertEq(5,StringDataUtil.getDirectionality((char)8352));
        assertFalse(StringDataLetterUtil.isLetter((char)8352));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8352));
    }
    @Test
    public void d1808(){
        assertEq(0,StringDataUtil.getType((char)8379));
        assertEq(-1,StringDataUtil.getDirectionality((char)8379));
        assertFalse(StringDataLetterUtil.isLetter((char)8379));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8379));
    }
    @Test
    public void d1809(){
        assertEq(6,StringDataUtil.getType((char)8400));
        assertEq(8,StringDataUtil.getDirectionality((char)8400));
        assertFalse(StringDataLetterUtil.isLetter((char)8400));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8400));
    }
    @Test
    public void d1810(){
        assertEq(7,StringDataUtil.getType((char)8413));
        assertEq(8,StringDataUtil.getDirectionality((char)8413));
        assertFalse(StringDataLetterUtil.isLetter((char)8413));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8413));
    }
    @Test
    public void d1811(){
        assertEq(6,StringDataUtil.getType((char)8417));
        assertEq(8,StringDataUtil.getDirectionality((char)8417));
        assertFalse(StringDataLetterUtil.isLetter((char)8417));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8417));
    }
    @Test
    public void d1812(){
        assertEq(7,StringDataUtil.getType((char)8418));
        assertEq(8,StringDataUtil.getDirectionality((char)8418));
        assertFalse(StringDataLetterUtil.isLetter((char)8418));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8418));
    }
    @Test
    public void d1813(){
        assertEq(6,StringDataUtil.getType((char)8421));
        assertEq(8,StringDataUtil.getDirectionality((char)8421));
        assertFalse(StringDataLetterUtil.isLetter((char)8421));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8421));
    }
    @Test
    public void d1814(){
        assertEq(0,StringDataUtil.getType((char)8433));
        assertEq(-1,StringDataUtil.getDirectionality((char)8433));
        assertFalse(StringDataLetterUtil.isLetter((char)8433));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8433));
    }
    @Test
    public void d1815(){
        assertEq(28,StringDataUtil.getType((char)8448));
        assertEq(13,StringDataUtil.getDirectionality((char)8448));
        assertFalse(StringDataLetterUtil.isLetter((char)8448));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8448));
    }
    @Test
    public void d1816(){
        assertEq(1,StringDataUtil.getType((char)8450));
        assertEq(0,StringDataUtil.getDirectionality((char)8450));
        assertTrue(StringDataLetterUtil.isLetter((char)8450));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8450));
    }
    @Test
    public void d1817(){
        assertEq(28,StringDataUtil.getType((char)8451));
        assertEq(13,StringDataUtil.getDirectionality((char)8451));
        assertFalse(StringDataLetterUtil.isLetter((char)8451));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8451));
    }
    @Test
    public void d1818(){
        assertEq(1,StringDataUtil.getType((char)8455));
        assertEq(0,StringDataUtil.getDirectionality((char)8455));
        assertTrue(StringDataLetterUtil.isLetter((char)8455));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8455));
    }
    @Test
    public void d1819(){
        assertEq(28,StringDataUtil.getType((char)8456));
        assertEq(13,StringDataUtil.getDirectionality((char)8456));
        assertFalse(StringDataLetterUtil.isLetter((char)8456));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8456));
    }
    @Test
    public void d1820(){
        assertEq(2,StringDataUtil.getType((char)8458));
        assertEq(0,StringDataUtil.getDirectionality((char)8458));
        assertTrue(StringDataLetterUtil.isLetter((char)8458));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8458));
    }
    @Test
    public void d1821(){
        assertEq(1,StringDataUtil.getType((char)8459));
        assertEq(0,StringDataUtil.getDirectionality((char)8459));
        assertTrue(StringDataLetterUtil.isLetter((char)8459));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8459));
    }
    @Test
    public void d1822(){
        assertEq(2,StringDataUtil.getType((char)8462));
        assertEq(0,StringDataUtil.getDirectionality((char)8462));
        assertTrue(StringDataLetterUtil.isLetter((char)8462));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8462));
    }
    @Test
    public void d1823(){
        assertEq(1,StringDataUtil.getType((char)8464));
        assertEq(0,StringDataUtil.getDirectionality((char)8464));
        assertTrue(StringDataLetterUtil.isLetter((char)8464));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8464));
    }
    @Test
    public void d1824(){
        assertEq(2,StringDataUtil.getType((char)8467));
        assertEq(0,StringDataUtil.getDirectionality((char)8467));
        assertTrue(StringDataLetterUtil.isLetter((char)8467));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8467));
    }
    @Test
    public void d1825(){
        assertEq(28,StringDataUtil.getType((char)8468));
        assertEq(13,StringDataUtil.getDirectionality((char)8468));
        assertFalse(StringDataLetterUtil.isLetter((char)8468));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8468));
    }
    @Test
    public void d1826(){
        assertEq(1,StringDataUtil.getType((char)8469));
        assertEq(0,StringDataUtil.getDirectionality((char)8469));
        assertTrue(StringDataLetterUtil.isLetter((char)8469));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8469));
    }
    @Test
    public void d1827(){
        assertEq(28,StringDataUtil.getType((char)8470));
        assertEq(13,StringDataUtil.getDirectionality((char)8470));
        assertFalse(StringDataLetterUtil.isLetter((char)8470));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8470));
    }
    @Test
    public void d1828(){
        assertEq(25,StringDataUtil.getType((char)8472));
        assertEq(13,StringDataUtil.getDirectionality((char)8472));
        assertFalse(StringDataLetterUtil.isLetter((char)8472));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8472));
    }
    @Test
    public void d1829(){
        assertEq(1,StringDataUtil.getType((char)8473));
        assertEq(0,StringDataUtil.getDirectionality((char)8473));
        assertTrue(StringDataLetterUtil.isLetter((char)8473));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8473));
    }
    @Test
    public void d1830(){
        assertEq(28,StringDataUtil.getType((char)8478));
        assertEq(13,StringDataUtil.getDirectionality((char)8478));
        assertFalse(StringDataLetterUtil.isLetter((char)8478));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8478));
    }
    @Test
    public void d1831(){
        assertEq(1,StringDataUtil.getType((char)8484));
        assertEq(0,StringDataUtil.getDirectionality((char)8484));
        assertTrue(StringDataLetterUtil.isLetter((char)8484));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8484));
    }
    @Test
    public void d1832(){
        assertEq(28,StringDataUtil.getType((char)8485));
        assertEq(13,StringDataUtil.getDirectionality((char)8485));
        assertFalse(StringDataLetterUtil.isLetter((char)8485));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8485));
    }
    @Test
    public void d1833(){
        assertEq(1,StringDataUtil.getType((char)8486));
        assertEq(0,StringDataUtil.getDirectionality((char)8486));
        assertTrue(StringDataLetterUtil.isLetter((char)8486));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8486));
    }
    @Test
    public void d1834(){
        assertEq(28,StringDataUtil.getType((char)8487));
        assertEq(13,StringDataUtil.getDirectionality((char)8487));
        assertFalse(StringDataLetterUtil.isLetter((char)8487));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8487));
    }
    @Test
    public void d1835(){
        assertEq(1,StringDataUtil.getType((char)8488));
        assertEq(0,StringDataUtil.getDirectionality((char)8488));
        assertTrue(StringDataLetterUtil.isLetter((char)8488));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8488));
    }
    @Test
    public void d1836(){
        assertEq(28,StringDataUtil.getType((char)8489));
        assertEq(13,StringDataUtil.getDirectionality((char)8489));
        assertFalse(StringDataLetterUtil.isLetter((char)8489));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8489));
    }
    @Test
    public void d1837(){
        assertEq(1,StringDataUtil.getType((char)8490));
        assertEq(0,StringDataUtil.getDirectionality((char)8490));
        assertTrue(StringDataLetterUtil.isLetter((char)8490));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8490));
    }
    @Test
    public void d1838(){
        assertEq(28,StringDataUtil.getType((char)8494));
        assertEq(5,StringDataUtil.getDirectionality((char)8494));
        assertFalse(StringDataLetterUtil.isLetter((char)8494));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8494));
    }
    @Test
    public void d1839(){
        assertEq(2,StringDataUtil.getType((char)8495));
        assertEq(0,StringDataUtil.getDirectionality((char)8495));
        assertTrue(StringDataLetterUtil.isLetter((char)8495));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8495));
    }
    @Test
    public void d1840(){
        assertEq(1,StringDataUtil.getType((char)8496));
        assertEq(0,StringDataUtil.getDirectionality((char)8496));
        assertTrue(StringDataLetterUtil.isLetter((char)8496));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8496));
    }
    @Test
    public void d1841(){
        assertEq(2,StringDataUtil.getType((char)8500));
        assertEq(0,StringDataUtil.getDirectionality((char)8500));
        assertTrue(StringDataLetterUtil.isLetter((char)8500));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8500));
    }
    @Test
    public void d1842(){
        assertEq(5,StringDataUtil.getType((char)8501));
        assertEq(0,StringDataUtil.getDirectionality((char)8501));
        assertTrue(StringDataLetterUtil.isLetter((char)8501));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8501));
    }
    @Test
    public void d1843(){
        assertEq(2,StringDataUtil.getType((char)8505));
        assertEq(0,StringDataUtil.getDirectionality((char)8505));
        assertTrue(StringDataLetterUtil.isLetter((char)8505));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8505));
    }
    @Test
    public void d1844(){
        assertEq(28,StringDataUtil.getType((char)8506));
        assertEq(13,StringDataUtil.getDirectionality((char)8506));
        assertFalse(StringDataLetterUtil.isLetter((char)8506));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8506));
    }
    @Test
    public void d1845(){
        assertEq(2,StringDataUtil.getType((char)8508));
        assertEq(0,StringDataUtil.getDirectionality((char)8508));
        assertTrue(StringDataLetterUtil.isLetter((char)8508));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8508));
    }
    @Test
    public void d1846(){
        assertEq(1,StringDataUtil.getType((char)8510));
        assertEq(0,StringDataUtil.getDirectionality((char)8510));
        assertTrue(StringDataLetterUtil.isLetter((char)8510));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8510));
    }
    @Test
    public void d1847(){
        assertEq(25,StringDataUtil.getType((char)8512));
        assertEq(13,StringDataUtil.getDirectionality((char)8512));
        assertFalse(StringDataLetterUtil.isLetter((char)8512));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8512));
    }
    @Test
    public void d1848(){
        assertEq(1,StringDataUtil.getType((char)8517));
        assertEq(0,StringDataUtil.getDirectionality((char)8517));
        assertTrue(StringDataLetterUtil.isLetter((char)8517));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8517));
    }
    @Test
    public void d1849(){
        assertEq(2,StringDataUtil.getType((char)8518));
        assertEq(0,StringDataUtil.getDirectionality((char)8518));
        assertTrue(StringDataLetterUtil.isLetter((char)8518));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8518));
    }
    @Test
    public void d1850(){
        assertEq(28,StringDataUtil.getType((char)8522));
        assertEq(13,StringDataUtil.getDirectionality((char)8522));
        assertFalse(StringDataLetterUtil.isLetter((char)8522));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8522));
    }
    @Test
    public void d1851(){
        assertEq(25,StringDataUtil.getType((char)8523));
        assertEq(13,StringDataUtil.getDirectionality((char)8523));
        assertFalse(StringDataLetterUtil.isLetter((char)8523));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8523));
    }
    @Test
    public void d1852(){
        assertEq(28,StringDataUtil.getType((char)8524));
        assertEq(13,StringDataUtil.getDirectionality((char)8524));
        assertFalse(StringDataLetterUtil.isLetter((char)8524));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8524));
    }
    @Test
    public void d1853(){
        assertEq(2,StringDataUtil.getType((char)8526));
        assertEq(0,StringDataUtil.getDirectionality((char)8526));
        assertTrue(StringDataLetterUtil.isLetter((char)8526));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8526));
    }
    @Test
    public void d1854(){
        assertEq(28,StringDataUtil.getType((char)8527));
        assertEq(0,StringDataUtil.getDirectionality((char)8527));
        assertFalse(StringDataLetterUtil.isLetter((char)8527));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8527));
    }
    @Test
    public void d1855(){
        assertEq(11,StringDataUtil.getType((char)8528));
        assertEq(13,StringDataUtil.getDirectionality((char)8528));
        assertFalse(StringDataLetterUtil.isLetter((char)8528));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8528));
    }
    @Test
    public void d1856(){
        assertEq(10,StringDataUtil.getType((char)8544));
        assertEq(0,StringDataUtil.getDirectionality((char)8544));
        assertFalse(StringDataLetterUtil.isLetter((char)8544));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8544));
    }
    @Test
    public void d1857(){
        assertEq(1,StringDataUtil.getType((char)8579));
        assertEq(0,StringDataUtil.getDirectionality((char)8579));
        assertTrue(StringDataLetterUtil.isLetter((char)8579));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8579));
    }
    @Test
    public void d1858(){
        assertEq(2,StringDataUtil.getType((char)8580));
        assertEq(0,StringDataUtil.getDirectionality((char)8580));
        assertTrue(StringDataLetterUtil.isLetter((char)8580));
        assertTrue(StringDataUtil.isLetterOrDigit((char)8580));
    }
    @Test
    public void d1859(){
        assertEq(10,StringDataUtil.getType((char)8581));
        assertEq(0,StringDataUtil.getDirectionality((char)8581));
        assertFalse(StringDataLetterUtil.isLetter((char)8581));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8581));
    }
    @Test
    public void d1860(){
        assertEq(11,StringDataUtil.getType((char)8585));
        assertEq(13,StringDataUtil.getDirectionality((char)8585));
        assertFalse(StringDataLetterUtil.isLetter((char)8585));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8585));
    }
    @Test
    public void d1861(){
        assertEq(0,StringDataUtil.getType((char)8586));
        assertEq(-1,StringDataUtil.getDirectionality((char)8586));
        assertFalse(StringDataLetterUtil.isLetter((char)8586));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8586));
    }
    @Test
    public void d1862(){
        assertEq(25,StringDataUtil.getType((char)8592));
        assertEq(13,StringDataUtil.getDirectionality((char)8592));
        assertFalse(StringDataLetterUtil.isLetter((char)8592));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8592));
    }
    @Test
    public void d1863(){
        assertEq(28,StringDataUtil.getType((char)8597));
        assertEq(13,StringDataUtil.getDirectionality((char)8597));
        assertFalse(StringDataLetterUtil.isLetter((char)8597));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8597));
    }
    @Test
    public void d1864(){
        assertEq(25,StringDataUtil.getType((char)8602));
        assertEq(13,StringDataUtil.getDirectionality((char)8602));
        assertFalse(StringDataLetterUtil.isLetter((char)8602));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8602));
    }
    @Test
    public void d1865(){
        assertEq(28,StringDataUtil.getType((char)8604));
        assertEq(13,StringDataUtil.getDirectionality((char)8604));
        assertFalse(StringDataLetterUtil.isLetter((char)8604));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8604));
    }
    @Test
    public void d1866(){
        assertEq(25,StringDataUtil.getType((char)8608));
        assertEq(13,StringDataUtil.getDirectionality((char)8608));
        assertFalse(StringDataLetterUtil.isLetter((char)8608));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8608));
    }
    @Test
    public void d1867(){
        assertEq(28,StringDataUtil.getType((char)8609));
        assertEq(13,StringDataUtil.getDirectionality((char)8609));
        assertFalse(StringDataLetterUtil.isLetter((char)8609));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8609));
    }
    @Test
    public void d1868(){
        assertEq(25,StringDataUtil.getType((char)8611));
        assertEq(13,StringDataUtil.getDirectionality((char)8611));
        assertFalse(StringDataLetterUtil.isLetter((char)8611));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8611));
    }
    @Test
    public void d1869(){
        assertEq(28,StringDataUtil.getType((char)8612));
        assertEq(13,StringDataUtil.getDirectionality((char)8612));
        assertFalse(StringDataLetterUtil.isLetter((char)8612));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8612));
    }
    @Test
    public void d1870(){
        assertEq(25,StringDataUtil.getType((char)8614));
        assertEq(13,StringDataUtil.getDirectionality((char)8614));
        assertFalse(StringDataLetterUtil.isLetter((char)8614));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8614));
    }
    @Test
    public void d1871(){
        assertEq(28,StringDataUtil.getType((char)8615));
        assertEq(13,StringDataUtil.getDirectionality((char)8615));
        assertFalse(StringDataLetterUtil.isLetter((char)8615));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8615));
    }
    @Test
    public void d1872(){
        assertEq(25,StringDataUtil.getType((char)8622));
        assertEq(13,StringDataUtil.getDirectionality((char)8622));
        assertFalse(StringDataLetterUtil.isLetter((char)8622));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8622));
    }
    @Test
    public void d1873(){
        assertEq(28,StringDataUtil.getType((char)8623));
        assertEq(13,StringDataUtil.getDirectionality((char)8623));
        assertFalse(StringDataLetterUtil.isLetter((char)8623));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8623));
    }
    @Test
    public void d1874(){
        assertEq(25,StringDataUtil.getType((char)8654));
        assertEq(13,StringDataUtil.getDirectionality((char)8654));
        assertFalse(StringDataLetterUtil.isLetter((char)8654));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8654));
    }
    @Test
    public void d1875(){
        assertEq(28,StringDataUtil.getType((char)8656));
        assertEq(13,StringDataUtil.getDirectionality((char)8656));
        assertFalse(StringDataLetterUtil.isLetter((char)8656));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8656));
    }
    @Test
    public void d1876(){
        assertEq(25,StringDataUtil.getType((char)8658));
        assertEq(13,StringDataUtil.getDirectionality((char)8658));
        assertFalse(StringDataLetterUtil.isLetter((char)8658));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8658));
    }
    @Test
    public void d1877(){
        assertEq(28,StringDataUtil.getType((char)8659));
        assertEq(13,StringDataUtil.getDirectionality((char)8659));
        assertFalse(StringDataLetterUtil.isLetter((char)8659));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8659));
    }
    @Test
    public void d1878(){
        assertEq(25,StringDataUtil.getType((char)8660));
        assertEq(13,StringDataUtil.getDirectionality((char)8660));
        assertFalse(StringDataLetterUtil.isLetter((char)8660));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8660));
    }
    @Test
    public void d1879(){
        assertEq(28,StringDataUtil.getType((char)8661));
        assertEq(13,StringDataUtil.getDirectionality((char)8661));
        assertFalse(StringDataLetterUtil.isLetter((char)8661));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8661));
    }
    @Test
    public void d1880(){
        assertEq(25,StringDataUtil.getType((char)8692));
        assertEq(13,StringDataUtil.getDirectionality((char)8692));
        assertFalse(StringDataLetterUtil.isLetter((char)8692));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8692));
    }
    @Test
    public void d1881(){
        assertEq(28,StringDataUtil.getType((char)8960));
        assertEq(13,StringDataUtil.getDirectionality((char)8960));
        assertFalse(StringDataLetterUtil.isLetter((char)8960));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8960));
    }
    @Test
    public void d1882(){
        assertEq(25,StringDataUtil.getType((char)8968));
        assertEq(13,StringDataUtil.getDirectionality((char)8968));
        assertFalse(StringDataLetterUtil.isLetter((char)8968));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8968));
    }
    @Test
    public void d1883(){
        assertEq(28,StringDataUtil.getType((char)8972));
        assertEq(13,StringDataUtil.getDirectionality((char)8972));
        assertFalse(StringDataLetterUtil.isLetter((char)8972));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8972));
    }
    @Test
    public void d1884(){
        assertEq(25,StringDataUtil.getType((char)8992));
        assertEq(13,StringDataUtil.getDirectionality((char)8992));
        assertFalse(StringDataLetterUtil.isLetter((char)8992));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8992));
    }
    @Test
    public void d1885(){
        assertEq(28,StringDataUtil.getType((char)8994));
        assertEq(13,StringDataUtil.getDirectionality((char)8994));
        assertFalse(StringDataLetterUtil.isLetter((char)8994));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8994));
    }
    @Test
    public void d1886(){
        assertEq(21,StringDataUtil.getType((char)9001));
        assertEq(13,StringDataUtil.getDirectionality((char)9001));
        assertFalse(StringDataLetterUtil.isLetter((char)9001));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9001));
    }
    @Test
    public void d1887(){
        assertEq(22,StringDataUtil.getType((char)9002));
        assertEq(13,StringDataUtil.getDirectionality((char)9002));
        assertFalse(StringDataLetterUtil.isLetter((char)9002));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9002));
    }
    @Test
    public void d1888(){
        assertEq(28,StringDataUtil.getType((char)9003));
        assertEq(13,StringDataUtil.getDirectionality((char)9003));
        assertFalse(StringDataLetterUtil.isLetter((char)9003));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9003));
    }
    @Test
    public void d1889(){
        assertEq(25,StringDataUtil.getType((char)9084));
        assertEq(13,StringDataUtil.getDirectionality((char)9084));
        assertFalse(StringDataLetterUtil.isLetter((char)9084));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9084));
    }
    @Test
    public void d1890(){
        assertEq(28,StringDataUtil.getType((char)9085));
        assertEq(13,StringDataUtil.getDirectionality((char)9085));
        assertFalse(StringDataLetterUtil.isLetter((char)9085));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9085));
    }
    @Test
    public void d1891(){
        assertEq(25,StringDataUtil.getType((char)9115));
        assertEq(13,StringDataUtil.getDirectionality((char)9115));
        assertFalse(StringDataLetterUtil.isLetter((char)9115));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9115));
    }
    @Test
    public void d1892(){
        assertEq(28,StringDataUtil.getType((char)9140));
        assertEq(13,StringDataUtil.getDirectionality((char)9140));
        assertFalse(StringDataLetterUtil.isLetter((char)9140));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9140));
    }
    @Test
    public void d1893(){
        assertEq(25,StringDataUtil.getType((char)9180));
        assertEq(13,StringDataUtil.getDirectionality((char)9180));
        assertFalse(StringDataLetterUtil.isLetter((char)9180));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9180));
    }
    @Test
    public void d1894(){
        assertEq(28,StringDataUtil.getType((char)9186));
        assertEq(13,StringDataUtil.getDirectionality((char)9186));
        assertFalse(StringDataLetterUtil.isLetter((char)9186));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9186));
    }
    @Test
    public void d1895(){
        assertEq(0,StringDataUtil.getType((char)9204));
        assertEq(-1,StringDataUtil.getDirectionality((char)9204));
        assertFalse(StringDataLetterUtil.isLetter((char)9204));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9204));
    }
    @Test
    public void d1896(){
        assertEq(28,StringDataUtil.getType((char)9216));
        assertEq(13,StringDataUtil.getDirectionality((char)9216));
        assertFalse(StringDataLetterUtil.isLetter((char)9216));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9216));
    }
    @Test
    public void d1897(){
        assertEq(0,StringDataUtil.getType((char)9255));
        assertEq(-1,StringDataUtil.getDirectionality((char)9255));
        assertFalse(StringDataLetterUtil.isLetter((char)9255));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9255));
    }
    @Test
    public void d1898(){
        assertEq(28,StringDataUtil.getType((char)9280));
        assertEq(13,StringDataUtil.getDirectionality((char)9280));
        assertFalse(StringDataLetterUtil.isLetter((char)9280));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9280));
    }
    @Test
    public void d1899(){
        assertEq(0,StringDataUtil.getType((char)9291));
        assertEq(-1,StringDataUtil.getDirectionality((char)9291));
        assertFalse(StringDataLetterUtil.isLetter((char)9291));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9291));
    }
    @Test
    public void d1900(){
        assertEq(11,StringDataUtil.getType((char)9312));
        assertEq(13,StringDataUtil.getDirectionality((char)9312));
        assertFalse(StringDataLetterUtil.isLetter((char)9312));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9312));
    }
    @Test
    public void d1901(){
        assertEq(28,StringDataUtil.getType((char)9372));
        assertEq(0,StringDataUtil.getDirectionality((char)9372));
        assertFalse(StringDataLetterUtil.isLetter((char)9372));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9372));
    }
    @Test
    public void d1902(){
        assertEq(11,StringDataUtil.getType((char)9450));
        assertEq(13,StringDataUtil.getDirectionality((char)9450));
        assertFalse(StringDataLetterUtil.isLetter((char)9450));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9450));
    }
    @Test
    public void d1903(){
        assertEq(28,StringDataUtil.getType((char)9472));
        assertEq(13,StringDataUtil.getDirectionality((char)9472));
        assertFalse(StringDataLetterUtil.isLetter((char)9472));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9472));
    }
    @Test
    public void d1904(){
        assertEq(25,StringDataUtil.getType((char)9655));
        assertEq(13,StringDataUtil.getDirectionality((char)9655));
        assertFalse(StringDataLetterUtil.isLetter((char)9655));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9655));
    }
    @Test
    public void d1905(){
        assertEq(28,StringDataUtil.getType((char)9656));
        assertEq(13,StringDataUtil.getDirectionality((char)9656));
        assertFalse(StringDataLetterUtil.isLetter((char)9656));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9656));
    }
    @Test
    public void d1906(){
        assertEq(25,StringDataUtil.getType((char)9665));
        assertEq(13,StringDataUtil.getDirectionality((char)9665));
        assertFalse(StringDataLetterUtil.isLetter((char)9665));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9665));
    }
    @Test
    public void d1907(){
        assertEq(28,StringDataUtil.getType((char)9666));
        assertEq(13,StringDataUtil.getDirectionality((char)9666));
        assertFalse(StringDataLetterUtil.isLetter((char)9666));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9666));
    }
    @Test
    public void d1908(){
        assertEq(25,StringDataUtil.getType((char)9720));
        assertEq(13,StringDataUtil.getDirectionality((char)9720));
        assertFalse(StringDataLetterUtil.isLetter((char)9720));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9720));
    }
    @Test
    public void d1909(){
        assertEq(28,StringDataUtil.getType((char)9728));
        assertEq(13,StringDataUtil.getDirectionality((char)9728));
        assertFalse(StringDataLetterUtil.isLetter((char)9728));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9728));
    }
    @Test
    public void d1910(){
        assertEq(25,StringDataUtil.getType((char)9839));
        assertEq(13,StringDataUtil.getDirectionality((char)9839));
        assertFalse(StringDataLetterUtil.isLetter((char)9839));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9839));
    }
    @Test
    public void d1911(){
        assertEq(28,StringDataUtil.getType((char)9840));
        assertEq(13,StringDataUtil.getDirectionality((char)9840));
        assertFalse(StringDataLetterUtil.isLetter((char)9840));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9840));
    }
    @Test
    public void d1912(){
        assertEq(0,StringDataUtil.getType((char)9984));
        assertEq(-1,StringDataUtil.getDirectionality((char)9984));
        assertFalse(StringDataLetterUtil.isLetter((char)9984));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9984));
    }
    @Test
    public void d1913(){
        assertEq(28,StringDataUtil.getType((char)9985));
        assertEq(13,StringDataUtil.getDirectionality((char)9985));
        assertFalse(StringDataLetterUtil.isLetter((char)9985));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9985));
    }
    @Test
    public void d1914(){
        assertEq(21,StringDataUtil.getType((char)10088));
        assertEq(13,StringDataUtil.getDirectionality((char)10088));
        assertFalse(StringDataLetterUtil.isLetter((char)10088));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10088));
    }
    @Test
    public void d1915(){
        assertEq(22,StringDataUtil.getType((char)10089));
        assertEq(13,StringDataUtil.getDirectionality((char)10089));
        assertFalse(StringDataLetterUtil.isLetter((char)10089));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10089));
    }
    @Test
    public void d1916(){
        assertEq(21,StringDataUtil.getType((char)10090));
        assertEq(13,StringDataUtil.getDirectionality((char)10090));
        assertFalse(StringDataLetterUtil.isLetter((char)10090));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10090));
    }
    @Test
    public void d1917(){
        assertEq(22,StringDataUtil.getType((char)10091));
        assertEq(13,StringDataUtil.getDirectionality((char)10091));
        assertFalse(StringDataLetterUtil.isLetter((char)10091));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10091));
    }
    @Test
    public void d1918(){
        assertEq(21,StringDataUtil.getType((char)10092));
        assertEq(13,StringDataUtil.getDirectionality((char)10092));
        assertFalse(StringDataLetterUtil.isLetter((char)10092));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10092));
    }
    @Test
    public void d1919(){
        assertEq(22,StringDataUtil.getType((char)10093));
        assertEq(13,StringDataUtil.getDirectionality((char)10093));
        assertFalse(StringDataLetterUtil.isLetter((char)10093));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10093));
    }
    @Test
    public void d1920(){
        assertEq(21,StringDataUtil.getType((char)10094));
        assertEq(13,StringDataUtil.getDirectionality((char)10094));
        assertFalse(StringDataLetterUtil.isLetter((char)10094));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10094));
    }
    @Test
    public void d1921(){
        assertEq(22,StringDataUtil.getType((char)10095));
        assertEq(13,StringDataUtil.getDirectionality((char)10095));
        assertFalse(StringDataLetterUtil.isLetter((char)10095));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10095));
    }
    @Test
    public void d1922(){
        assertEq(21,StringDataUtil.getType((char)10096));
        assertEq(13,StringDataUtil.getDirectionality((char)10096));
        assertFalse(StringDataLetterUtil.isLetter((char)10096));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10096));
    }
    @Test
    public void d1923(){
        assertEq(22,StringDataUtil.getType((char)10097));
        assertEq(13,StringDataUtil.getDirectionality((char)10097));
        assertFalse(StringDataLetterUtil.isLetter((char)10097));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10097));
    }
    @Test
    public void d1924(){
        assertEq(21,StringDataUtil.getType((char)10098));
        assertEq(13,StringDataUtil.getDirectionality((char)10098));
        assertFalse(StringDataLetterUtil.isLetter((char)10098));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10098));
    }
    @Test
    public void d1925(){
        assertEq(22,StringDataUtil.getType((char)10099));
        assertEq(13,StringDataUtil.getDirectionality((char)10099));
        assertFalse(StringDataLetterUtil.isLetter((char)10099));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10099));
    }
    @Test
    public void d1926(){
        assertEq(21,StringDataUtil.getType((char)10100));
        assertEq(13,StringDataUtil.getDirectionality((char)10100));
        assertFalse(StringDataLetterUtil.isLetter((char)10100));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10100));
    }
    @Test
    public void d1927(){
        assertEq(22,StringDataUtil.getType((char)10101));
        assertEq(13,StringDataUtil.getDirectionality((char)10101));
        assertFalse(StringDataLetterUtil.isLetter((char)10101));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10101));
    }
    @Test
    public void d1928(){
        assertEq(11,StringDataUtil.getType((char)10102));
        assertEq(13,StringDataUtil.getDirectionality((char)10102));
        assertFalse(StringDataLetterUtil.isLetter((char)10102));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10102));
    }
    @Test
    public void d1929(){
        assertEq(28,StringDataUtil.getType((char)10132));
        assertEq(13,StringDataUtil.getDirectionality((char)10132));
        assertFalse(StringDataLetterUtil.isLetter((char)10132));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10132));
    }
    @Test
    public void d1930(){
        assertEq(25,StringDataUtil.getType((char)10176));
        assertEq(13,StringDataUtil.getDirectionality((char)10176));
        assertFalse(StringDataLetterUtil.isLetter((char)10176));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10176));
    }
    @Test
    public void d1931(){
        assertEq(21,StringDataUtil.getType((char)10181));
        assertEq(13,StringDataUtil.getDirectionality((char)10181));
        assertFalse(StringDataLetterUtil.isLetter((char)10181));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10181));
    }
    @Test
    public void d1932(){
        assertEq(22,StringDataUtil.getType((char)10182));
        assertEq(13,StringDataUtil.getDirectionality((char)10182));
        assertFalse(StringDataLetterUtil.isLetter((char)10182));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10182));
    }
    @Test
    public void d1933(){
        assertEq(25,StringDataUtil.getType((char)10183));
        assertEq(13,StringDataUtil.getDirectionality((char)10183));
        assertFalse(StringDataLetterUtil.isLetter((char)10183));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10183));
    }
    @Test
    public void d1934(){
        assertEq(21,StringDataUtil.getType((char)10214));
        assertEq(13,StringDataUtil.getDirectionality((char)10214));
        assertFalse(StringDataLetterUtil.isLetter((char)10214));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10214));
    }
    @Test
    public void d1935(){
        assertEq(22,StringDataUtil.getType((char)10215));
        assertEq(13,StringDataUtil.getDirectionality((char)10215));
        assertFalse(StringDataLetterUtil.isLetter((char)10215));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10215));
    }
    @Test
    public void d1936(){
        assertEq(21,StringDataUtil.getType((char)10216));
        assertEq(13,StringDataUtil.getDirectionality((char)10216));
        assertFalse(StringDataLetterUtil.isLetter((char)10216));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10216));
    }
    @Test
    public void d1937(){
        assertEq(22,StringDataUtil.getType((char)10217));
        assertEq(13,StringDataUtil.getDirectionality((char)10217));
        assertFalse(StringDataLetterUtil.isLetter((char)10217));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10217));
    }
    @Test
    public void d1938(){
        assertEq(21,StringDataUtil.getType((char)10218));
        assertEq(13,StringDataUtil.getDirectionality((char)10218));
        assertFalse(StringDataLetterUtil.isLetter((char)10218));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10218));
    }
    @Test
    public void d1939(){
        assertEq(22,StringDataUtil.getType((char)10219));
        assertEq(13,StringDataUtil.getDirectionality((char)10219));
        assertFalse(StringDataLetterUtil.isLetter((char)10219));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10219));
    }
    @Test
    public void d1940(){
        assertEq(21,StringDataUtil.getType((char)10220));
        assertEq(13,StringDataUtil.getDirectionality((char)10220));
        assertFalse(StringDataLetterUtil.isLetter((char)10220));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10220));
    }
    @Test
    public void d1941(){
        assertEq(22,StringDataUtil.getType((char)10221));
        assertEq(13,StringDataUtil.getDirectionality((char)10221));
        assertFalse(StringDataLetterUtil.isLetter((char)10221));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10221));
    }
    @Test
    public void d1942(){
        assertEq(21,StringDataUtil.getType((char)10222));
        assertEq(13,StringDataUtil.getDirectionality((char)10222));
        assertFalse(StringDataLetterUtil.isLetter((char)10222));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10222));
    }
    @Test
    public void d1943(){
        assertEq(22,StringDataUtil.getType((char)10223));
        assertEq(13,StringDataUtil.getDirectionality((char)10223));
        assertFalse(StringDataLetterUtil.isLetter((char)10223));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10223));
    }
    @Test
    public void d1944(){
        assertEq(25,StringDataUtil.getType((char)10224));
        assertEq(13,StringDataUtil.getDirectionality((char)10224));
        assertFalse(StringDataLetterUtil.isLetter((char)10224));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10224));
    }
    @Test
    public void d1945(){
        assertEq(28,StringDataUtil.getType((char)10240));
        assertEq(0,StringDataUtil.getDirectionality((char)10240));
        assertFalse(StringDataLetterUtil.isLetter((char)10240));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10240));
    }
    @Test
    public void d1946(){
        assertEq(25,StringDataUtil.getType((char)10496));
        assertEq(13,StringDataUtil.getDirectionality((char)10496));
        assertFalse(StringDataLetterUtil.isLetter((char)10496));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10496));
    }
    @Test
    public void d1947(){
        assertEq(21,StringDataUtil.getType((char)10627));
        assertEq(13,StringDataUtil.getDirectionality((char)10627));
        assertFalse(StringDataLetterUtil.isLetter((char)10627));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10627));
    }
    @Test
    public void d1948(){
        assertEq(22,StringDataUtil.getType((char)10628));
        assertEq(13,StringDataUtil.getDirectionality((char)10628));
        assertFalse(StringDataLetterUtil.isLetter((char)10628));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10628));
    }
    @Test
    public void d1949(){
        assertEq(21,StringDataUtil.getType((char)10629));
        assertEq(13,StringDataUtil.getDirectionality((char)10629));
        assertFalse(StringDataLetterUtil.isLetter((char)10629));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10629));
    }
    @Test
    public void d1950(){
        assertEq(22,StringDataUtil.getType((char)10630));
        assertEq(13,StringDataUtil.getDirectionality((char)10630));
        assertFalse(StringDataLetterUtil.isLetter((char)10630));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10630));
    }
    @Test
    public void d1951(){
        assertEq(21,StringDataUtil.getType((char)10631));
        assertEq(13,StringDataUtil.getDirectionality((char)10631));
        assertFalse(StringDataLetterUtil.isLetter((char)10631));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10631));
    }
    @Test
    public void d1952(){
        assertEq(22,StringDataUtil.getType((char)10632));
        assertEq(13,StringDataUtil.getDirectionality((char)10632));
        assertFalse(StringDataLetterUtil.isLetter((char)10632));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10632));
    }
    @Test
    public void d1953(){
        assertEq(21,StringDataUtil.getType((char)10633));
        assertEq(13,StringDataUtil.getDirectionality((char)10633));
        assertFalse(StringDataLetterUtil.isLetter((char)10633));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10633));
    }
    @Test
    public void d1954(){
        assertEq(22,StringDataUtil.getType((char)10634));
        assertEq(13,StringDataUtil.getDirectionality((char)10634));
        assertFalse(StringDataLetterUtil.isLetter((char)10634));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10634));
    }
    @Test
    public void d1955(){
        assertEq(21,StringDataUtil.getType((char)10635));
        assertEq(13,StringDataUtil.getDirectionality((char)10635));
        assertFalse(StringDataLetterUtil.isLetter((char)10635));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10635));
    }
    @Test
    public void d1956(){
        assertEq(22,StringDataUtil.getType((char)10636));
        assertEq(13,StringDataUtil.getDirectionality((char)10636));
        assertFalse(StringDataLetterUtil.isLetter((char)10636));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10636));
    }
    @Test
    public void d1957(){
        assertEq(21,StringDataUtil.getType((char)10637));
        assertEq(13,StringDataUtil.getDirectionality((char)10637));
        assertFalse(StringDataLetterUtil.isLetter((char)10637));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10637));
    }
    @Test
    public void d1958(){
        assertEq(22,StringDataUtil.getType((char)10638));
        assertEq(13,StringDataUtil.getDirectionality((char)10638));
        assertFalse(StringDataLetterUtil.isLetter((char)10638));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10638));
    }
    @Test
    public void d1959(){
        assertEq(21,StringDataUtil.getType((char)10639));
        assertEq(13,StringDataUtil.getDirectionality((char)10639));
        assertFalse(StringDataLetterUtil.isLetter((char)10639));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10639));
    }
    @Test
    public void d1960(){
        assertEq(22,StringDataUtil.getType((char)10640));
        assertEq(13,StringDataUtil.getDirectionality((char)10640));
        assertFalse(StringDataLetterUtil.isLetter((char)10640));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10640));
    }
    @Test
    public void d1961(){
        assertEq(21,StringDataUtil.getType((char)10641));
        assertEq(13,StringDataUtil.getDirectionality((char)10641));
        assertFalse(StringDataLetterUtil.isLetter((char)10641));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10641));
    }
    @Test
    public void d1962(){
        assertEq(22,StringDataUtil.getType((char)10642));
        assertEq(13,StringDataUtil.getDirectionality((char)10642));
        assertFalse(StringDataLetterUtil.isLetter((char)10642));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10642));
    }
    @Test
    public void d1963(){
        assertEq(21,StringDataUtil.getType((char)10643));
        assertEq(13,StringDataUtil.getDirectionality((char)10643));
        assertFalse(StringDataLetterUtil.isLetter((char)10643));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10643));
    }
    @Test
    public void d1964(){
        assertEq(22,StringDataUtil.getType((char)10644));
        assertEq(13,StringDataUtil.getDirectionality((char)10644));
        assertFalse(StringDataLetterUtil.isLetter((char)10644));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10644));
    }
    @Test
    public void d1965(){
        assertEq(21,StringDataUtil.getType((char)10645));
        assertEq(13,StringDataUtil.getDirectionality((char)10645));
        assertFalse(StringDataLetterUtil.isLetter((char)10645));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10645));
    }
    @Test
    public void d1966(){
        assertEq(22,StringDataUtil.getType((char)10646));
        assertEq(13,StringDataUtil.getDirectionality((char)10646));
        assertFalse(StringDataLetterUtil.isLetter((char)10646));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10646));
    }
    @Test
    public void d1967(){
        assertEq(21,StringDataUtil.getType((char)10647));
        assertEq(13,StringDataUtil.getDirectionality((char)10647));
        assertFalse(StringDataLetterUtil.isLetter((char)10647));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10647));
    }
    @Test
    public void d1968(){
        assertEq(22,StringDataUtil.getType((char)10648));
        assertEq(13,StringDataUtil.getDirectionality((char)10648));
        assertFalse(StringDataLetterUtil.isLetter((char)10648));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10648));
    }
    @Test
    public void d1969(){
        assertEq(25,StringDataUtil.getType((char)10649));
        assertEq(13,StringDataUtil.getDirectionality((char)10649));
        assertFalse(StringDataLetterUtil.isLetter((char)10649));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10649));
    }
    @Test
    public void d1970(){
        assertEq(21,StringDataUtil.getType((char)10712));
        assertEq(13,StringDataUtil.getDirectionality((char)10712));
        assertFalse(StringDataLetterUtil.isLetter((char)10712));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10712));
    }
    @Test
    public void d1971(){
        assertEq(22,StringDataUtil.getType((char)10713));
        assertEq(13,StringDataUtil.getDirectionality((char)10713));
        assertFalse(StringDataLetterUtil.isLetter((char)10713));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10713));
    }
    @Test
    public void d1972(){
        assertEq(21,StringDataUtil.getType((char)10714));
        assertEq(13,StringDataUtil.getDirectionality((char)10714));
        assertFalse(StringDataLetterUtil.isLetter((char)10714));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10714));
    }
    @Test
    public void d1973(){
        assertEq(22,StringDataUtil.getType((char)10715));
        assertEq(13,StringDataUtil.getDirectionality((char)10715));
        assertFalse(StringDataLetterUtil.isLetter((char)10715));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10715));
    }
    @Test
    public void d1974(){
        assertEq(25,StringDataUtil.getType((char)10716));
        assertEq(13,StringDataUtil.getDirectionality((char)10716));
        assertFalse(StringDataLetterUtil.isLetter((char)10716));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10716));
    }
    @Test
    public void d1975(){
        assertEq(21,StringDataUtil.getType((char)10748));
        assertEq(13,StringDataUtil.getDirectionality((char)10748));
        assertFalse(StringDataLetterUtil.isLetter((char)10748));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10748));
    }
    @Test
    public void d1976(){
        assertEq(22,StringDataUtil.getType((char)10749));
        assertEq(13,StringDataUtil.getDirectionality((char)10749));
        assertFalse(StringDataLetterUtil.isLetter((char)10749));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10749));
    }
    @Test
    public void d1977(){
        assertEq(25,StringDataUtil.getType((char)10750));
        assertEq(13,StringDataUtil.getDirectionality((char)10750));
        assertFalse(StringDataLetterUtil.isLetter((char)10750));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10750));
    }
    @Test
    public void d1978(){
        assertEq(28,StringDataUtil.getType((char)11008));
        assertEq(13,StringDataUtil.getDirectionality((char)11008));
        assertFalse(StringDataLetterUtil.isLetter((char)11008));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11008));
    }
    @Test
    public void d1979(){
        assertEq(25,StringDataUtil.getType((char)11056));
        assertEq(13,StringDataUtil.getDirectionality((char)11056));
        assertFalse(StringDataLetterUtil.isLetter((char)11056));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11056));
    }
    @Test
    public void d1980(){
        assertEq(28,StringDataUtil.getType((char)11077));
        assertEq(13,StringDataUtil.getDirectionality((char)11077));
        assertFalse(StringDataLetterUtil.isLetter((char)11077));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11077));
    }
    @Test
    public void d1981(){
        assertEq(25,StringDataUtil.getType((char)11079));
        assertEq(13,StringDataUtil.getDirectionality((char)11079));
        assertFalse(StringDataLetterUtil.isLetter((char)11079));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11079));
    }
    @Test
    public void d1982(){
        assertEq(0,StringDataUtil.getType((char)11085));
        assertEq(-1,StringDataUtil.getDirectionality((char)11085));
        assertFalse(StringDataLetterUtil.isLetter((char)11085));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11085));
    }
    @Test
    public void d1983(){
        assertEq(28,StringDataUtil.getType((char)11088));
        assertEq(13,StringDataUtil.getDirectionality((char)11088));
        assertFalse(StringDataLetterUtil.isLetter((char)11088));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11088));
    }
    @Test
    public void d1984(){
        assertEq(0,StringDataUtil.getType((char)11098));
        assertEq(-1,StringDataUtil.getDirectionality((char)11098));
        assertFalse(StringDataLetterUtil.isLetter((char)11098));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11098));
    }
    @Test
    public void d1985(){
        assertEq(1,StringDataUtil.getType((char)11264));
        assertEq(0,StringDataUtil.getDirectionality((char)11264));
        assertTrue(StringDataLetterUtil.isLetter((char)11264));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11264));
    }
    @Test
    public void d1986(){
        assertEq(0,StringDataUtil.getType((char)11311));
        assertEq(-1,StringDataUtil.getDirectionality((char)11311));
        assertFalse(StringDataLetterUtil.isLetter((char)11311));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11311));
    }
    @Test
    public void d1987(){
        assertEq(2,StringDataUtil.getType((char)11312));
        assertEq(0,StringDataUtil.getDirectionality((char)11312));
        assertTrue(StringDataLetterUtil.isLetter((char)11312));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11312));
    }
    @Test
    public void d1988(){
        assertEq(0,StringDataUtil.getType((char)11359));
        assertEq(-1,StringDataUtil.getDirectionality((char)11359));
        assertFalse(StringDataLetterUtil.isLetter((char)11359));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11359));
    }
    @Test
    public void d1989(){
        assertEq(1,StringDataUtil.getType((char)11360));
        assertEq(0,StringDataUtil.getDirectionality((char)11360));
        assertTrue(StringDataLetterUtil.isLetter((char)11360));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11360));
    }
    @Test
    public void d1990(){
        assertEq(2,StringDataUtil.getType((char)11361));
        assertEq(0,StringDataUtil.getDirectionality((char)11361));
        assertTrue(StringDataLetterUtil.isLetter((char)11361));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11361));
    }
    @Test
    public void d1991(){
        assertEq(1,StringDataUtil.getType((char)11362));
        assertEq(0,StringDataUtil.getDirectionality((char)11362));
        assertTrue(StringDataLetterUtil.isLetter((char)11362));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11362));
    }
    @Test
    public void d1992(){
        assertEq(2,StringDataUtil.getType((char)11365));
        assertEq(0,StringDataUtil.getDirectionality((char)11365));
        assertTrue(StringDataLetterUtil.isLetter((char)11365));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11365));
    }
    @Test
    public void d1993(){
        assertEq(1,StringDataUtil.getType((char)11367));
        assertEq(0,StringDataUtil.getDirectionality((char)11367));
        assertTrue(StringDataLetterUtil.isLetter((char)11367));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11367));
    }
    @Test
    public void d1994(){
        assertEq(2,StringDataUtil.getType((char)11368));
        assertEq(0,StringDataUtil.getDirectionality((char)11368));
        assertTrue(StringDataLetterUtil.isLetter((char)11368));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11368));
    }
    @Test
    public void d1995(){
        assertEq(1,StringDataUtil.getType((char)11369));
        assertEq(0,StringDataUtil.getDirectionality((char)11369));
        assertTrue(StringDataLetterUtil.isLetter((char)11369));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11369));
    }
    @Test
    public void d1996(){
        assertEq(2,StringDataUtil.getType((char)11370));
        assertEq(0,StringDataUtil.getDirectionality((char)11370));
        assertTrue(StringDataLetterUtil.isLetter((char)11370));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11370));
    }
    @Test
    public void d1997(){
        assertEq(1,StringDataUtil.getType((char)11371));
        assertEq(0,StringDataUtil.getDirectionality((char)11371));
        assertTrue(StringDataLetterUtil.isLetter((char)11371));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11371));
    }
    @Test
    public void d1998(){
        assertEq(2,StringDataUtil.getType((char)11372));
        assertEq(0,StringDataUtil.getDirectionality((char)11372));
        assertTrue(StringDataLetterUtil.isLetter((char)11372));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11372));
    }
    @Test
    public void d1999(){
        assertEq(1,StringDataUtil.getType((char)11373));
        assertEq(0,StringDataUtil.getDirectionality((char)11373));
        assertTrue(StringDataLetterUtil.isLetter((char)11373));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11373));
    }
    @Test
    public void d2000(){
        assertEq(2,StringDataUtil.getType((char)11377));
        assertEq(0,StringDataUtil.getDirectionality((char)11377));
        assertTrue(StringDataLetterUtil.isLetter((char)11377));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11377));
    }
    @Test
    public void d2001(){
        assertEq(1,StringDataUtil.getType((char)11378));
        assertEq(0,StringDataUtil.getDirectionality((char)11378));
        assertTrue(StringDataLetterUtil.isLetter((char)11378));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11378));
    }
    @Test
    public void d2002(){
        assertEq(2,StringDataUtil.getType((char)11379));
        assertEq(0,StringDataUtil.getDirectionality((char)11379));
        assertTrue(StringDataLetterUtil.isLetter((char)11379));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11379));
    }
    @Test
    public void d2003(){
        assertEq(1,StringDataUtil.getType((char)11381));
        assertEq(0,StringDataUtil.getDirectionality((char)11381));
        assertTrue(StringDataLetterUtil.isLetter((char)11381));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11381));
    }
    @Test
    public void d2004(){
        assertEq(2,StringDataUtil.getType((char)11382));
        assertEq(0,StringDataUtil.getDirectionality((char)11382));
        assertTrue(StringDataLetterUtil.isLetter((char)11382));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11382));
    }
    @Test
    public void d2005(){
        assertEq(4,StringDataUtil.getType((char)11388));
        assertEq(0,StringDataUtil.getDirectionality((char)11388));
        assertTrue(StringDataLetterUtil.isLetter((char)11388));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11388));
    }
    @Test
    public void d2006(){
        assertEq(1,StringDataUtil.getType((char)11390));
        assertEq(0,StringDataUtil.getDirectionality((char)11390));
        assertTrue(StringDataLetterUtil.isLetter((char)11390));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11390));
    }
    @Test
    public void d2007(){
        assertEq(2,StringDataUtil.getType((char)11393));
        assertEq(0,StringDataUtil.getDirectionality((char)11393));
        assertTrue(StringDataLetterUtil.isLetter((char)11393));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11393));
    }
    @Test
    public void d2008(){
        assertEq(1,StringDataUtil.getType((char)11394));
        assertEq(0,StringDataUtil.getDirectionality((char)11394));
        assertTrue(StringDataLetterUtil.isLetter((char)11394));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11394));
    }
    @Test
    public void d2009(){
        assertEq(2,StringDataUtil.getType((char)11395));
        assertEq(0,StringDataUtil.getDirectionality((char)11395));
        assertTrue(StringDataLetterUtil.isLetter((char)11395));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11395));
    }
    @Test
    public void d2010(){
        assertEq(1,StringDataUtil.getType((char)11396));
        assertEq(0,StringDataUtil.getDirectionality((char)11396));
        assertTrue(StringDataLetterUtil.isLetter((char)11396));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11396));
    }
    @Test
    public void d2011(){
        assertEq(2,StringDataUtil.getType((char)11397));
        assertEq(0,StringDataUtil.getDirectionality((char)11397));
        assertTrue(StringDataLetterUtil.isLetter((char)11397));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11397));
    }
    @Test
    public void d2012(){
        assertEq(1,StringDataUtil.getType((char)11398));
        assertEq(0,StringDataUtil.getDirectionality((char)11398));
        assertTrue(StringDataLetterUtil.isLetter((char)11398));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11398));
    }
    @Test
    public void d2013(){
        assertEq(2,StringDataUtil.getType((char)11399));
        assertEq(0,StringDataUtil.getDirectionality((char)11399));
        assertTrue(StringDataLetterUtil.isLetter((char)11399));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11399));
    }
    @Test
    public void d2014(){
        assertEq(1,StringDataUtil.getType((char)11400));
        assertEq(0,StringDataUtil.getDirectionality((char)11400));
        assertTrue(StringDataLetterUtil.isLetter((char)11400));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11400));
    }
    @Test
    public void d2015(){
        assertEq(2,StringDataUtil.getType((char)11401));
        assertEq(0,StringDataUtil.getDirectionality((char)11401));
        assertTrue(StringDataLetterUtil.isLetter((char)11401));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11401));
    }
    @Test
    public void d2016(){
        assertEq(1,StringDataUtil.getType((char)11402));
        assertEq(0,StringDataUtil.getDirectionality((char)11402));
        assertTrue(StringDataLetterUtil.isLetter((char)11402));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11402));
    }
    @Test
    public void d2017(){
        assertEq(2,StringDataUtil.getType((char)11403));
        assertEq(0,StringDataUtil.getDirectionality((char)11403));
        assertTrue(StringDataLetterUtil.isLetter((char)11403));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11403));
    }
    @Test
    public void d2018(){
        assertEq(1,StringDataUtil.getType((char)11404));
        assertEq(0,StringDataUtil.getDirectionality((char)11404));
        assertTrue(StringDataLetterUtil.isLetter((char)11404));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11404));
    }
    @Test
    public void d2019(){
        assertEq(2,StringDataUtil.getType((char)11405));
        assertEq(0,StringDataUtil.getDirectionality((char)11405));
        assertTrue(StringDataLetterUtil.isLetter((char)11405));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11405));
    }
    @Test
    public void d2020(){
        assertEq(1,StringDataUtil.getType((char)11406));
        assertEq(0,StringDataUtil.getDirectionality((char)11406));
        assertTrue(StringDataLetterUtil.isLetter((char)11406));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11406));
    }
    @Test
    public void d2021(){
        assertEq(2,StringDataUtil.getType((char)11407));
        assertEq(0,StringDataUtil.getDirectionality((char)11407));
        assertTrue(StringDataLetterUtil.isLetter((char)11407));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11407));
    }
    @Test
    public void d2022(){
        assertEq(1,StringDataUtil.getType((char)11408));
        assertEq(0,StringDataUtil.getDirectionality((char)11408));
        assertTrue(StringDataLetterUtil.isLetter((char)11408));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11408));
    }
    @Test
    public void d2023(){
        assertEq(2,StringDataUtil.getType((char)11409));
        assertEq(0,StringDataUtil.getDirectionality((char)11409));
        assertTrue(StringDataLetterUtil.isLetter((char)11409));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11409));
    }
    @Test
    public void d2024(){
        assertEq(1,StringDataUtil.getType((char)11410));
        assertEq(0,StringDataUtil.getDirectionality((char)11410));
        assertTrue(StringDataLetterUtil.isLetter((char)11410));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11410));
    }
    @Test
    public void d2025(){
        assertEq(2,StringDataUtil.getType((char)11411));
        assertEq(0,StringDataUtil.getDirectionality((char)11411));
        assertTrue(StringDataLetterUtil.isLetter((char)11411));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11411));
    }
    @Test
    public void d2026(){
        assertEq(1,StringDataUtil.getType((char)11412));
        assertEq(0,StringDataUtil.getDirectionality((char)11412));
        assertTrue(StringDataLetterUtil.isLetter((char)11412));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11412));
    }
    @Test
    public void d2027(){
        assertEq(2,StringDataUtil.getType((char)11413));
        assertEq(0,StringDataUtil.getDirectionality((char)11413));
        assertTrue(StringDataLetterUtil.isLetter((char)11413));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11413));
    }
    @Test
    public void d2028(){
        assertEq(1,StringDataUtil.getType((char)11414));
        assertEq(0,StringDataUtil.getDirectionality((char)11414));
        assertTrue(StringDataLetterUtil.isLetter((char)11414));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11414));
    }
    @Test
    public void d2029(){
        assertEq(2,StringDataUtil.getType((char)11415));
        assertEq(0,StringDataUtil.getDirectionality((char)11415));
        assertTrue(StringDataLetterUtil.isLetter((char)11415));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11415));
    }
    @Test
    public void d2030(){
        assertEq(1,StringDataUtil.getType((char)11416));
        assertEq(0,StringDataUtil.getDirectionality((char)11416));
        assertTrue(StringDataLetterUtil.isLetter((char)11416));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11416));
    }
    @Test
    public void d2031(){
        assertEq(2,StringDataUtil.getType((char)11417));
        assertEq(0,StringDataUtil.getDirectionality((char)11417));
        assertTrue(StringDataLetterUtil.isLetter((char)11417));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11417));
    }
    @Test
    public void d2032(){
        assertEq(1,StringDataUtil.getType((char)11418));
        assertEq(0,StringDataUtil.getDirectionality((char)11418));
        assertTrue(StringDataLetterUtil.isLetter((char)11418));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11418));
    }
    @Test
    public void d2033(){
        assertEq(2,StringDataUtil.getType((char)11419));
        assertEq(0,StringDataUtil.getDirectionality((char)11419));
        assertTrue(StringDataLetterUtil.isLetter((char)11419));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11419));
    }
    @Test
    public void d2034(){
        assertEq(1,StringDataUtil.getType((char)11420));
        assertEq(0,StringDataUtil.getDirectionality((char)11420));
        assertTrue(StringDataLetterUtil.isLetter((char)11420));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11420));
    }
    @Test
    public void d2035(){
        assertEq(2,StringDataUtil.getType((char)11421));
        assertEq(0,StringDataUtil.getDirectionality((char)11421));
        assertTrue(StringDataLetterUtil.isLetter((char)11421));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11421));
    }
    @Test
    public void d2036(){
        assertEq(1,StringDataUtil.getType((char)11422));
        assertEq(0,StringDataUtil.getDirectionality((char)11422));
        assertTrue(StringDataLetterUtil.isLetter((char)11422));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11422));
    }
    @Test
    public void d2037(){
        assertEq(2,StringDataUtil.getType((char)11423));
        assertEq(0,StringDataUtil.getDirectionality((char)11423));
        assertTrue(StringDataLetterUtil.isLetter((char)11423));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11423));
    }
    @Test
    public void d2038(){
        assertEq(1,StringDataUtil.getType((char)11424));
        assertEq(0,StringDataUtil.getDirectionality((char)11424));
        assertTrue(StringDataLetterUtil.isLetter((char)11424));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11424));
    }
    @Test
    public void d2039(){
        assertEq(2,StringDataUtil.getType((char)11425));
        assertEq(0,StringDataUtil.getDirectionality((char)11425));
        assertTrue(StringDataLetterUtil.isLetter((char)11425));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11425));
    }
    @Test
    public void d2040(){
        assertEq(1,StringDataUtil.getType((char)11426));
        assertEq(0,StringDataUtil.getDirectionality((char)11426));
        assertTrue(StringDataLetterUtil.isLetter((char)11426));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11426));
    }
    @Test
    public void d2041(){
        assertEq(2,StringDataUtil.getType((char)11427));
        assertEq(0,StringDataUtil.getDirectionality((char)11427));
        assertTrue(StringDataLetterUtil.isLetter((char)11427));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11427));
    }
    @Test
    public void d2042(){
        assertEq(1,StringDataUtil.getType((char)11428));
        assertEq(0,StringDataUtil.getDirectionality((char)11428));
        assertTrue(StringDataLetterUtil.isLetter((char)11428));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11428));
    }
    @Test
    public void d2043(){
        assertEq(2,StringDataUtil.getType((char)11429));
        assertEq(0,StringDataUtil.getDirectionality((char)11429));
        assertTrue(StringDataLetterUtil.isLetter((char)11429));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11429));
    }
    @Test
    public void d2044(){
        assertEq(1,StringDataUtil.getType((char)11430));
        assertEq(0,StringDataUtil.getDirectionality((char)11430));
        assertTrue(StringDataLetterUtil.isLetter((char)11430));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11430));
    }
    @Test
    public void d2045(){
        assertEq(2,StringDataUtil.getType((char)11431));
        assertEq(0,StringDataUtil.getDirectionality((char)11431));
        assertTrue(StringDataLetterUtil.isLetter((char)11431));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11431));
    }
    @Test
    public void d2046(){
        assertEq(1,StringDataUtil.getType((char)11432));
        assertEq(0,StringDataUtil.getDirectionality((char)11432));
        assertTrue(StringDataLetterUtil.isLetter((char)11432));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11432));
    }
    @Test
    public void d2047(){
        assertEq(2,StringDataUtil.getType((char)11433));
        assertEq(0,StringDataUtil.getDirectionality((char)11433));
        assertTrue(StringDataLetterUtil.isLetter((char)11433));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11433));
    }
    @Test
    public void d2048(){
        assertEq(1,StringDataUtil.getType((char)11434));
        assertEq(0,StringDataUtil.getDirectionality((char)11434));
        assertTrue(StringDataLetterUtil.isLetter((char)11434));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11434));
    }
    @Test
    public void d2049(){
        assertEq(2,StringDataUtil.getType((char)11435));
        assertEq(0,StringDataUtil.getDirectionality((char)11435));
        assertTrue(StringDataLetterUtil.isLetter((char)11435));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11435));
    }
    @Test
    public void d2050(){
        assertEq(1,StringDataUtil.getType((char)11436));
        assertEq(0,StringDataUtil.getDirectionality((char)11436));
        assertTrue(StringDataLetterUtil.isLetter((char)11436));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11436));
    }
    @Test
    public void d2051(){
        assertEq(2,StringDataUtil.getType((char)11437));
        assertEq(0,StringDataUtil.getDirectionality((char)11437));
        assertTrue(StringDataLetterUtil.isLetter((char)11437));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11437));
    }
    @Test
    public void d2052(){
        assertEq(1,StringDataUtil.getType((char)11438));
        assertEq(0,StringDataUtil.getDirectionality((char)11438));
        assertTrue(StringDataLetterUtil.isLetter((char)11438));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11438));
    }
    @Test
    public void d2053(){
        assertEq(2,StringDataUtil.getType((char)11439));
        assertEq(0,StringDataUtil.getDirectionality((char)11439));
        assertTrue(StringDataLetterUtil.isLetter((char)11439));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11439));
    }
    @Test
    public void d2054(){
        assertEq(1,StringDataUtil.getType((char)11440));
        assertEq(0,StringDataUtil.getDirectionality((char)11440));
        assertTrue(StringDataLetterUtil.isLetter((char)11440));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11440));
    }
    @Test
    public void d2055(){
        assertEq(2,StringDataUtil.getType((char)11441));
        assertEq(0,StringDataUtil.getDirectionality((char)11441));
        assertTrue(StringDataLetterUtil.isLetter((char)11441));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11441));
    }
    @Test
    public void d2056(){
        assertEq(1,StringDataUtil.getType((char)11442));
        assertEq(0,StringDataUtil.getDirectionality((char)11442));
        assertTrue(StringDataLetterUtil.isLetter((char)11442));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11442));
    }
    @Test
    public void d2057(){
        assertEq(2,StringDataUtil.getType((char)11443));
        assertEq(0,StringDataUtil.getDirectionality((char)11443));
        assertTrue(StringDataLetterUtil.isLetter((char)11443));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11443));
    }
    @Test
    public void d2058(){
        assertEq(1,StringDataUtil.getType((char)11444));
        assertEq(0,StringDataUtil.getDirectionality((char)11444));
        assertTrue(StringDataLetterUtil.isLetter((char)11444));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11444));
    }
    @Test
    public void d2059(){
        assertEq(2,StringDataUtil.getType((char)11445));
        assertEq(0,StringDataUtil.getDirectionality((char)11445));
        assertTrue(StringDataLetterUtil.isLetter((char)11445));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11445));
    }
    @Test
    public void d2060(){
        assertEq(1,StringDataUtil.getType((char)11446));
        assertEq(0,StringDataUtil.getDirectionality((char)11446));
        assertTrue(StringDataLetterUtil.isLetter((char)11446));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11446));
    }
    @Test
    public void d2061(){
        assertEq(2,StringDataUtil.getType((char)11447));
        assertEq(0,StringDataUtil.getDirectionality((char)11447));
        assertTrue(StringDataLetterUtil.isLetter((char)11447));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11447));
    }
    @Test
    public void d2062(){
        assertEq(1,StringDataUtil.getType((char)11448));
        assertEq(0,StringDataUtil.getDirectionality((char)11448));
        assertTrue(StringDataLetterUtil.isLetter((char)11448));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11448));
    }
    @Test
    public void d2063(){
        assertEq(2,StringDataUtil.getType((char)11449));
        assertEq(0,StringDataUtil.getDirectionality((char)11449));
        assertTrue(StringDataLetterUtil.isLetter((char)11449));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11449));
    }
    @Test
    public void d2064(){
        assertEq(1,StringDataUtil.getType((char)11450));
        assertEq(0,StringDataUtil.getDirectionality((char)11450));
        assertTrue(StringDataLetterUtil.isLetter((char)11450));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11450));
    }
    @Test
    public void d2065(){
        assertEq(2,StringDataUtil.getType((char)11451));
        assertEq(0,StringDataUtil.getDirectionality((char)11451));
        assertTrue(StringDataLetterUtil.isLetter((char)11451));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11451));
    }
    @Test
    public void d2066(){
        assertEq(1,StringDataUtil.getType((char)11452));
        assertEq(0,StringDataUtil.getDirectionality((char)11452));
        assertTrue(StringDataLetterUtil.isLetter((char)11452));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11452));
    }
    @Test
    public void d2067(){
        assertEq(2,StringDataUtil.getType((char)11453));
        assertEq(0,StringDataUtil.getDirectionality((char)11453));
        assertTrue(StringDataLetterUtil.isLetter((char)11453));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11453));
    }
    @Test
    public void d2068(){
        assertEq(1,StringDataUtil.getType((char)11454));
        assertEq(0,StringDataUtil.getDirectionality((char)11454));
        assertTrue(StringDataLetterUtil.isLetter((char)11454));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11454));
    }
    @Test
    public void d2069(){
        assertEq(2,StringDataUtil.getType((char)11455));
        assertEq(0,StringDataUtil.getDirectionality((char)11455));
        assertTrue(StringDataLetterUtil.isLetter((char)11455));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11455));
    }
    @Test
    public void d2070(){
        assertEq(1,StringDataUtil.getType((char)11456));
        assertEq(0,StringDataUtil.getDirectionality((char)11456));
        assertTrue(StringDataLetterUtil.isLetter((char)11456));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11456));
    }
    @Test
    public void d2071(){
        assertEq(2,StringDataUtil.getType((char)11457));
        assertEq(0,StringDataUtil.getDirectionality((char)11457));
        assertTrue(StringDataLetterUtil.isLetter((char)11457));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11457));
    }
    @Test
    public void d2072(){
        assertEq(1,StringDataUtil.getType((char)11458));
        assertEq(0,StringDataUtil.getDirectionality((char)11458));
        assertTrue(StringDataLetterUtil.isLetter((char)11458));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11458));
    }
    @Test
    public void d2073(){
        assertEq(2,StringDataUtil.getType((char)11459));
        assertEq(0,StringDataUtil.getDirectionality((char)11459));
        assertTrue(StringDataLetterUtil.isLetter((char)11459));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11459));
    }
    @Test
    public void d2074(){
        assertEq(1,StringDataUtil.getType((char)11460));
        assertEq(0,StringDataUtil.getDirectionality((char)11460));
        assertTrue(StringDataLetterUtil.isLetter((char)11460));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11460));
    }
    @Test
    public void d2075(){
        assertEq(2,StringDataUtil.getType((char)11461));
        assertEq(0,StringDataUtil.getDirectionality((char)11461));
        assertTrue(StringDataLetterUtil.isLetter((char)11461));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11461));
    }
    @Test
    public void d2076(){
        assertEq(1,StringDataUtil.getType((char)11462));
        assertEq(0,StringDataUtil.getDirectionality((char)11462));
        assertTrue(StringDataLetterUtil.isLetter((char)11462));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11462));
    }
    @Test
    public void d2077(){
        assertEq(2,StringDataUtil.getType((char)11463));
        assertEq(0,StringDataUtil.getDirectionality((char)11463));
        assertTrue(StringDataLetterUtil.isLetter((char)11463));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11463));
    }
    @Test
    public void d2078(){
        assertEq(1,StringDataUtil.getType((char)11464));
        assertEq(0,StringDataUtil.getDirectionality((char)11464));
        assertTrue(StringDataLetterUtil.isLetter((char)11464));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11464));
    }
    @Test
    public void d2079(){
        assertEq(2,StringDataUtil.getType((char)11465));
        assertEq(0,StringDataUtil.getDirectionality((char)11465));
        assertTrue(StringDataLetterUtil.isLetter((char)11465));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11465));
    }
    @Test
    public void d2080(){
        assertEq(1,StringDataUtil.getType((char)11466));
        assertEq(0,StringDataUtil.getDirectionality((char)11466));
        assertTrue(StringDataLetterUtil.isLetter((char)11466));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11466));
    }
    @Test
    public void d2081(){
        assertEq(2,StringDataUtil.getType((char)11467));
        assertEq(0,StringDataUtil.getDirectionality((char)11467));
        assertTrue(StringDataLetterUtil.isLetter((char)11467));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11467));
    }
    @Test
    public void d2082(){
        assertEq(1,StringDataUtil.getType((char)11468));
        assertEq(0,StringDataUtil.getDirectionality((char)11468));
        assertTrue(StringDataLetterUtil.isLetter((char)11468));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11468));
    }
    @Test
    public void d2083(){
        assertEq(2,StringDataUtil.getType((char)11469));
        assertEq(0,StringDataUtil.getDirectionality((char)11469));
        assertTrue(StringDataLetterUtil.isLetter((char)11469));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11469));
    }
    @Test
    public void d2084(){
        assertEq(1,StringDataUtil.getType((char)11470));
        assertEq(0,StringDataUtil.getDirectionality((char)11470));
        assertTrue(StringDataLetterUtil.isLetter((char)11470));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11470));
    }
    @Test
    public void d2085(){
        assertEq(2,StringDataUtil.getType((char)11471));
        assertEq(0,StringDataUtil.getDirectionality((char)11471));
        assertTrue(StringDataLetterUtil.isLetter((char)11471));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11471));
    }
    @Test
    public void d2086(){
        assertEq(1,StringDataUtil.getType((char)11472));
        assertEq(0,StringDataUtil.getDirectionality((char)11472));
        assertTrue(StringDataLetterUtil.isLetter((char)11472));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11472));
    }
    @Test
    public void d2087(){
        assertEq(2,StringDataUtil.getType((char)11473));
        assertEq(0,StringDataUtil.getDirectionality((char)11473));
        assertTrue(StringDataLetterUtil.isLetter((char)11473));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11473));
    }
    @Test
    public void d2088(){
        assertEq(1,StringDataUtil.getType((char)11474));
        assertEq(0,StringDataUtil.getDirectionality((char)11474));
        assertTrue(StringDataLetterUtil.isLetter((char)11474));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11474));
    }
    @Test
    public void d2089(){
        assertEq(2,StringDataUtil.getType((char)11475));
        assertEq(0,StringDataUtil.getDirectionality((char)11475));
        assertTrue(StringDataLetterUtil.isLetter((char)11475));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11475));
    }
    @Test
    public void d2090(){
        assertEq(1,StringDataUtil.getType((char)11476));
        assertEq(0,StringDataUtil.getDirectionality((char)11476));
        assertTrue(StringDataLetterUtil.isLetter((char)11476));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11476));
    }
    @Test
    public void d2091(){
        assertEq(2,StringDataUtil.getType((char)11477));
        assertEq(0,StringDataUtil.getDirectionality((char)11477));
        assertTrue(StringDataLetterUtil.isLetter((char)11477));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11477));
    }
    @Test
    public void d2092(){
        assertEq(1,StringDataUtil.getType((char)11478));
        assertEq(0,StringDataUtil.getDirectionality((char)11478));
        assertTrue(StringDataLetterUtil.isLetter((char)11478));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11478));
    }
    @Test
    public void d2093(){
        assertEq(2,StringDataUtil.getType((char)11479));
        assertEq(0,StringDataUtil.getDirectionality((char)11479));
        assertTrue(StringDataLetterUtil.isLetter((char)11479));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11479));
    }
    @Test
    public void d2094(){
        assertEq(1,StringDataUtil.getType((char)11480));
        assertEq(0,StringDataUtil.getDirectionality((char)11480));
        assertTrue(StringDataLetterUtil.isLetter((char)11480));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11480));
    }
    @Test
    public void d2095(){
        assertEq(2,StringDataUtil.getType((char)11481));
        assertEq(0,StringDataUtil.getDirectionality((char)11481));
        assertTrue(StringDataLetterUtil.isLetter((char)11481));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11481));
    }
    @Test
    public void d2096(){
        assertEq(1,StringDataUtil.getType((char)11482));
        assertEq(0,StringDataUtil.getDirectionality((char)11482));
        assertTrue(StringDataLetterUtil.isLetter((char)11482));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11482));
    }
    @Test
    public void d2097(){
        assertEq(2,StringDataUtil.getType((char)11483));
        assertEq(0,StringDataUtil.getDirectionality((char)11483));
        assertTrue(StringDataLetterUtil.isLetter((char)11483));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11483));
    }
    @Test
    public void d2098(){
        assertEq(1,StringDataUtil.getType((char)11484));
        assertEq(0,StringDataUtil.getDirectionality((char)11484));
        assertTrue(StringDataLetterUtil.isLetter((char)11484));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11484));
    }
    @Test
    public void d2099(){
        assertEq(2,StringDataUtil.getType((char)11485));
        assertEq(0,StringDataUtil.getDirectionality((char)11485));
        assertTrue(StringDataLetterUtil.isLetter((char)11485));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11485));
    }
    @Test
    public void d2100(){
        assertEq(1,StringDataUtil.getType((char)11486));
        assertEq(0,StringDataUtil.getDirectionality((char)11486));
        assertTrue(StringDataLetterUtil.isLetter((char)11486));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11486));
    }
    @Test
    public void d2101(){
        assertEq(2,StringDataUtil.getType((char)11487));
        assertEq(0,StringDataUtil.getDirectionality((char)11487));
        assertTrue(StringDataLetterUtil.isLetter((char)11487));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11487));
    }
    @Test
    public void d2102(){
        assertEq(1,StringDataUtil.getType((char)11488));
        assertEq(0,StringDataUtil.getDirectionality((char)11488));
        assertTrue(StringDataLetterUtil.isLetter((char)11488));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11488));
    }
    @Test
    public void d2103(){
        assertEq(2,StringDataUtil.getType((char)11489));
        assertEq(0,StringDataUtil.getDirectionality((char)11489));
        assertTrue(StringDataLetterUtil.isLetter((char)11489));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11489));
    }
    @Test
    public void d2104(){
        assertEq(1,StringDataUtil.getType((char)11490));
        assertEq(0,StringDataUtil.getDirectionality((char)11490));
        assertTrue(StringDataLetterUtil.isLetter((char)11490));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11490));
    }
    @Test
    public void d2105(){
        assertEq(2,StringDataUtil.getType((char)11491));
        assertEq(0,StringDataUtil.getDirectionality((char)11491));
        assertTrue(StringDataLetterUtil.isLetter((char)11491));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11491));
    }
    @Test
    public void d2106(){
        assertEq(28,StringDataUtil.getType((char)11493));
        assertEq(13,StringDataUtil.getDirectionality((char)11493));
        assertFalse(StringDataLetterUtil.isLetter((char)11493));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11493));
    }
    @Test
    public void d2107(){
        assertEq(1,StringDataUtil.getType((char)11499));
        assertEq(0,StringDataUtil.getDirectionality((char)11499));
        assertTrue(StringDataLetterUtil.isLetter((char)11499));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11499));
    }
    @Test
    public void d2108(){
        assertEq(2,StringDataUtil.getType((char)11500));
        assertEq(0,StringDataUtil.getDirectionality((char)11500));
        assertTrue(StringDataLetterUtil.isLetter((char)11500));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11500));
    }
    @Test
    public void d2109(){
        assertEq(1,StringDataUtil.getType((char)11501));
        assertEq(0,StringDataUtil.getDirectionality((char)11501));
        assertTrue(StringDataLetterUtil.isLetter((char)11501));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11501));
    }
    @Test
    public void d2110(){
        assertEq(2,StringDataUtil.getType((char)11502));
        assertEq(0,StringDataUtil.getDirectionality((char)11502));
        assertTrue(StringDataLetterUtil.isLetter((char)11502));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11502));
    }
    @Test
    public void d2111(){
        assertEq(6,StringDataUtil.getType((char)11503));
        assertEq(8,StringDataUtil.getDirectionality((char)11503));
        assertFalse(StringDataLetterUtil.isLetter((char)11503));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11503));
    }
    @Test
    public void d2112(){
        assertEq(1,StringDataUtil.getType((char)11506));
        assertEq(0,StringDataUtil.getDirectionality((char)11506));
        assertTrue(StringDataLetterUtil.isLetter((char)11506));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11506));
    }
    @Test
    public void d2113(){
        assertEq(2,StringDataUtil.getType((char)11507));
        assertEq(0,StringDataUtil.getDirectionality((char)11507));
        assertTrue(StringDataLetterUtil.isLetter((char)11507));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11507));
    }
    @Test
    public void d2114(){
        assertEq(0,StringDataUtil.getType((char)11508));
        assertEq(-1,StringDataUtil.getDirectionality((char)11508));
        assertFalse(StringDataLetterUtil.isLetter((char)11508));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11508));
    }
    @Test
    public void d2115(){
        assertEq(24,StringDataUtil.getType((char)11513));
        assertEq(13,StringDataUtil.getDirectionality((char)11513));
        assertFalse(StringDataLetterUtil.isLetter((char)11513));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11513));
    }
    @Test
    public void d2116(){
        assertEq(11,StringDataUtil.getType((char)11517));
        assertEq(13,StringDataUtil.getDirectionality((char)11517));
        assertFalse(StringDataLetterUtil.isLetter((char)11517));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11517));
    }
    @Test
    public void d2117(){
        assertEq(24,StringDataUtil.getType((char)11518));
        assertEq(13,StringDataUtil.getDirectionality((char)11518));
        assertFalse(StringDataLetterUtil.isLetter((char)11518));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11518));
    }
    @Test
    public void d2118(){
        assertEq(2,StringDataUtil.getType((char)11520));
        assertEq(0,StringDataUtil.getDirectionality((char)11520));
        assertTrue(StringDataLetterUtil.isLetter((char)11520));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11520));
    }
    @Test
    public void d2119(){
        assertEq(0,StringDataUtil.getType((char)11558));
        assertEq(-1,StringDataUtil.getDirectionality((char)11558));
        assertFalse(StringDataLetterUtil.isLetter((char)11558));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11558));
    }
    @Test
    public void d2120(){
        assertEq(2,StringDataUtil.getType((char)11559));
        assertEq(0,StringDataUtil.getDirectionality((char)11559));
        assertTrue(StringDataLetterUtil.isLetter((char)11559));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11559));
    }
    @Test
    public void d2121(){
        assertEq(0,StringDataUtil.getType((char)11560));
        assertEq(-1,StringDataUtil.getDirectionality((char)11560));
        assertFalse(StringDataLetterUtil.isLetter((char)11560));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11560));
    }
    @Test
    public void d2122(){
        assertEq(2,StringDataUtil.getType((char)11565));
        assertEq(0,StringDataUtil.getDirectionality((char)11565));
        assertTrue(StringDataLetterUtil.isLetter((char)11565));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11565));
    }
    @Test
    public void d2123(){
        assertEq(0,StringDataUtil.getType((char)11566));
        assertEq(-1,StringDataUtil.getDirectionality((char)11566));
        assertFalse(StringDataLetterUtil.isLetter((char)11566));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11566));
    }
    @Test
    public void d2124(){
        assertEq(5,StringDataUtil.getType((char)11568));
        assertEq(0,StringDataUtil.getDirectionality((char)11568));
        assertTrue(StringDataLetterUtil.isLetter((char)11568));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11568));
    }
    @Test
    public void d2125(){
        assertEq(0,StringDataUtil.getType((char)11624));
        assertEq(-1,StringDataUtil.getDirectionality((char)11624));
        assertFalse(StringDataLetterUtil.isLetter((char)11624));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11624));
    }
    @Test
    public void d2126(){
        assertEq(4,StringDataUtil.getType((char)11631));
        assertEq(0,StringDataUtil.getDirectionality((char)11631));
        assertTrue(StringDataLetterUtil.isLetter((char)11631));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11631));
    }
    @Test
    public void d2127(){
        assertEq(24,StringDataUtil.getType((char)11632));
        assertEq(0,StringDataUtil.getDirectionality((char)11632));
        assertFalse(StringDataLetterUtil.isLetter((char)11632));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11632));
    }
    @Test
    public void d2128(){
        assertEq(0,StringDataUtil.getType((char)11633));
        assertEq(-1,StringDataUtil.getDirectionality((char)11633));
        assertFalse(StringDataLetterUtil.isLetter((char)11633));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11633));
    }
    @Test
    public void d2129(){
        assertEq(6,StringDataUtil.getType((char)11647));
        assertEq(8,StringDataUtil.getDirectionality((char)11647));
        assertFalse(StringDataLetterUtil.isLetter((char)11647));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11647));
    }
    @Test
    public void d2130(){
        assertEq(5,StringDataUtil.getType((char)11648));
        assertEq(0,StringDataUtil.getDirectionality((char)11648));
        assertTrue(StringDataLetterUtil.isLetter((char)11648));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11648));
    }
    @Test
    public void d2131(){
        assertEq(0,StringDataUtil.getType((char)11671));
        assertEq(-1,StringDataUtil.getDirectionality((char)11671));
        assertFalse(StringDataLetterUtil.isLetter((char)11671));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11671));
    }
    @Test
    public void d2132(){
        assertEq(5,StringDataUtil.getType((char)11680));
        assertEq(0,StringDataUtil.getDirectionality((char)11680));
        assertTrue(StringDataLetterUtil.isLetter((char)11680));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11680));
    }
    @Test
    public void d2133(){
        assertEq(0,StringDataUtil.getType((char)11687));
        assertEq(-1,StringDataUtil.getDirectionality((char)11687));
        assertFalse(StringDataLetterUtil.isLetter((char)11687));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11687));
    }
    @Test
    public void d2134(){
        assertEq(5,StringDataUtil.getType((char)11688));
        assertEq(0,StringDataUtil.getDirectionality((char)11688));
        assertTrue(StringDataLetterUtil.isLetter((char)11688));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11688));
    }
    @Test
    public void d2135(){
        assertEq(0,StringDataUtil.getType((char)11695));
        assertEq(-1,StringDataUtil.getDirectionality((char)11695));
        assertFalse(StringDataLetterUtil.isLetter((char)11695));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11695));
    }
    @Test
    public void d2136(){
        assertEq(5,StringDataUtil.getType((char)11696));
        assertEq(0,StringDataUtil.getDirectionality((char)11696));
        assertTrue(StringDataLetterUtil.isLetter((char)11696));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11696));
    }
    @Test
    public void d2137(){
        assertEq(0,StringDataUtil.getType((char)11703));
        assertEq(-1,StringDataUtil.getDirectionality((char)11703));
        assertFalse(StringDataLetterUtil.isLetter((char)11703));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11703));
    }
    @Test
    public void d2138(){
        assertEq(5,StringDataUtil.getType((char)11704));
        assertEq(0,StringDataUtil.getDirectionality((char)11704));
        assertTrue(StringDataLetterUtil.isLetter((char)11704));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11704));
    }
    @Test
    public void d2139(){
        assertEq(0,StringDataUtil.getType((char)11711));
        assertEq(-1,StringDataUtil.getDirectionality((char)11711));
        assertFalse(StringDataLetterUtil.isLetter((char)11711));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11711));
    }
    @Test
    public void d2140(){
        assertEq(5,StringDataUtil.getType((char)11712));
        assertEq(0,StringDataUtil.getDirectionality((char)11712));
        assertTrue(StringDataLetterUtil.isLetter((char)11712));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11712));
    }
    @Test
    public void d2141(){
        assertEq(0,StringDataUtil.getType((char)11719));
        assertEq(-1,StringDataUtil.getDirectionality((char)11719));
        assertFalse(StringDataLetterUtil.isLetter((char)11719));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11719));
    }
    @Test
    public void d2142(){
        assertEq(5,StringDataUtil.getType((char)11720));
        assertEq(0,StringDataUtil.getDirectionality((char)11720));
        assertTrue(StringDataLetterUtil.isLetter((char)11720));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11720));
    }
    @Test
    public void d2143(){
        assertEq(0,StringDataUtil.getType((char)11727));
        assertEq(-1,StringDataUtil.getDirectionality((char)11727));
        assertFalse(StringDataLetterUtil.isLetter((char)11727));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11727));
    }
    @Test
    public void d2144(){
        assertEq(5,StringDataUtil.getType((char)11728));
        assertEq(0,StringDataUtil.getDirectionality((char)11728));
        assertTrue(StringDataLetterUtil.isLetter((char)11728));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11728));
    }
    @Test
    public void d2145(){
        assertEq(0,StringDataUtil.getType((char)11735));
        assertEq(-1,StringDataUtil.getDirectionality((char)11735));
        assertFalse(StringDataLetterUtil.isLetter((char)11735));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11735));
    }
    @Test
    public void d2146(){
        assertEq(5,StringDataUtil.getType((char)11736));
        assertEq(0,StringDataUtil.getDirectionality((char)11736));
        assertTrue(StringDataLetterUtil.isLetter((char)11736));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11736));
    }
    @Test
    public void d2147(){
        assertEq(0,StringDataUtil.getType((char)11743));
        assertEq(-1,StringDataUtil.getDirectionality((char)11743));
        assertFalse(StringDataLetterUtil.isLetter((char)11743));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11743));
    }
    @Test
    public void d2148(){
        assertEq(6,StringDataUtil.getType((char)11744));
        assertEq(8,StringDataUtil.getDirectionality((char)11744));
        assertFalse(StringDataLetterUtil.isLetter((char)11744));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11744));
    }
    @Test
    public void d2149(){
        assertEq(24,StringDataUtil.getType((char)11776));
        assertEq(13,StringDataUtil.getDirectionality((char)11776));
        assertFalse(StringDataLetterUtil.isLetter((char)11776));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11776));
    }
    @Test
    public void d2150(){
        assertEq(29,StringDataUtil.getType((char)11778));
        assertEq(13,StringDataUtil.getDirectionality((char)11778));
        assertFalse(StringDataLetterUtil.isLetter((char)11778));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11778));
    }
    @Test
    public void d2151(){
        assertEq(30,StringDataUtil.getType((char)11779));
        assertEq(13,StringDataUtil.getDirectionality((char)11779));
        assertFalse(StringDataLetterUtil.isLetter((char)11779));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11779));
    }
    @Test
    public void d2152(){
        assertEq(29,StringDataUtil.getType((char)11780));
        assertEq(13,StringDataUtil.getDirectionality((char)11780));
        assertFalse(StringDataLetterUtil.isLetter((char)11780));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11780));
    }
    @Test
    public void d2153(){
        assertEq(30,StringDataUtil.getType((char)11781));
        assertEq(13,StringDataUtil.getDirectionality((char)11781));
        assertFalse(StringDataLetterUtil.isLetter((char)11781));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11781));
    }
    @Test
    public void d2154(){
        assertEq(24,StringDataUtil.getType((char)11782));
        assertEq(13,StringDataUtil.getDirectionality((char)11782));
        assertFalse(StringDataLetterUtil.isLetter((char)11782));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11782));
    }
    @Test
    public void d2155(){
        assertEq(29,StringDataUtil.getType((char)11785));
        assertEq(13,StringDataUtil.getDirectionality((char)11785));
        assertFalse(StringDataLetterUtil.isLetter((char)11785));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11785));
    }
    @Test
    public void d2156(){
        assertEq(30,StringDataUtil.getType((char)11786));
        assertEq(13,StringDataUtil.getDirectionality((char)11786));
        assertFalse(StringDataLetterUtil.isLetter((char)11786));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11786));
    }
    @Test
    public void d2157(){
        assertEq(24,StringDataUtil.getType((char)11787));
        assertEq(13,StringDataUtil.getDirectionality((char)11787));
        assertFalse(StringDataLetterUtil.isLetter((char)11787));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11787));
    }
    @Test
    public void d2158(){
        assertEq(29,StringDataUtil.getType((char)11788));
        assertEq(13,StringDataUtil.getDirectionality((char)11788));
        assertFalse(StringDataLetterUtil.isLetter((char)11788));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11788));
    }
    @Test
    public void d2159(){
        assertEq(30,StringDataUtil.getType((char)11789));
        assertEq(13,StringDataUtil.getDirectionality((char)11789));
        assertFalse(StringDataLetterUtil.isLetter((char)11789));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11789));
    }
    @Test
    public void d2160(){
        assertEq(24,StringDataUtil.getType((char)11790));
        assertEq(13,StringDataUtil.getDirectionality((char)11790));
        assertFalse(StringDataLetterUtil.isLetter((char)11790));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11790));
    }
    @Test
    public void d2161(){
        assertEq(20,StringDataUtil.getType((char)11799));
        assertEq(13,StringDataUtil.getDirectionality((char)11799));
        assertFalse(StringDataLetterUtil.isLetter((char)11799));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11799));
    }
    @Test
    public void d2162(){
        assertEq(24,StringDataUtil.getType((char)11800));
        assertEq(13,StringDataUtil.getDirectionality((char)11800));
        assertFalse(StringDataLetterUtil.isLetter((char)11800));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11800));
    }
    @Test
    public void d2163(){
        assertEq(20,StringDataUtil.getType((char)11802));
        assertEq(13,StringDataUtil.getDirectionality((char)11802));
        assertFalse(StringDataLetterUtil.isLetter((char)11802));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11802));
    }
    @Test
    public void d2164(){
        assertEq(24,StringDataUtil.getType((char)11803));
        assertEq(13,StringDataUtil.getDirectionality((char)11803));
        assertFalse(StringDataLetterUtil.isLetter((char)11803));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11803));
    }
    @Test
    public void d2165(){
        assertEq(29,StringDataUtil.getType((char)11804));
        assertEq(13,StringDataUtil.getDirectionality((char)11804));
        assertFalse(StringDataLetterUtil.isLetter((char)11804));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11804));
    }
    @Test
    public void d2166(){
        assertEq(30,StringDataUtil.getType((char)11805));
        assertEq(13,StringDataUtil.getDirectionality((char)11805));
        assertFalse(StringDataLetterUtil.isLetter((char)11805));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11805));
    }
    @Test
    public void d2167(){
        assertEq(24,StringDataUtil.getType((char)11806));
        assertEq(13,StringDataUtil.getDirectionality((char)11806));
        assertFalse(StringDataLetterUtil.isLetter((char)11806));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11806));
    }
    @Test
    public void d2168(){
        assertEq(29,StringDataUtil.getType((char)11808));
        assertEq(13,StringDataUtil.getDirectionality((char)11808));
        assertFalse(StringDataLetterUtil.isLetter((char)11808));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11808));
    }
    @Test
    public void d2169(){
        assertEq(30,StringDataUtil.getType((char)11809));
        assertEq(13,StringDataUtil.getDirectionality((char)11809));
        assertFalse(StringDataLetterUtil.isLetter((char)11809));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11809));
    }
    @Test
    public void d2170(){
        assertEq(21,StringDataUtil.getType((char)11810));
        assertEq(13,StringDataUtil.getDirectionality((char)11810));
        assertFalse(StringDataLetterUtil.isLetter((char)11810));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11810));
    }
    @Test
    public void d2171(){
        assertEq(22,StringDataUtil.getType((char)11811));
        assertEq(13,StringDataUtil.getDirectionality((char)11811));
        assertFalse(StringDataLetterUtil.isLetter((char)11811));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11811));
    }
    @Test
    public void d2172(){
        assertEq(21,StringDataUtil.getType((char)11812));
        assertEq(13,StringDataUtil.getDirectionality((char)11812));
        assertFalse(StringDataLetterUtil.isLetter((char)11812));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11812));
    }
    @Test
    public void d2173(){
        assertEq(22,StringDataUtil.getType((char)11813));
        assertEq(13,StringDataUtil.getDirectionality((char)11813));
        assertFalse(StringDataLetterUtil.isLetter((char)11813));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11813));
    }
    @Test
    public void d2174(){
        assertEq(21,StringDataUtil.getType((char)11814));
        assertEq(13,StringDataUtil.getDirectionality((char)11814));
        assertFalse(StringDataLetterUtil.isLetter((char)11814));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11814));
    }
    @Test
    public void d2175(){
        assertEq(22,StringDataUtil.getType((char)11815));
        assertEq(13,StringDataUtil.getDirectionality((char)11815));
        assertFalse(StringDataLetterUtil.isLetter((char)11815));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11815));
    }
    @Test
    public void d2176(){
        assertEq(21,StringDataUtil.getType((char)11816));
        assertEq(13,StringDataUtil.getDirectionality((char)11816));
        assertFalse(StringDataLetterUtil.isLetter((char)11816));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11816));
    }
    @Test
    public void d2177(){
        assertEq(22,StringDataUtil.getType((char)11817));
        assertEq(13,StringDataUtil.getDirectionality((char)11817));
        assertFalse(StringDataLetterUtil.isLetter((char)11817));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11817));
    }
    @Test
    public void d2178(){
        assertEq(24,StringDataUtil.getType((char)11818));
        assertEq(13,StringDataUtil.getDirectionality((char)11818));
        assertFalse(StringDataLetterUtil.isLetter((char)11818));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11818));
    }
    @Test
    public void d2179(){
        assertEq(4,StringDataUtil.getType((char)11823));
        assertEq(13,StringDataUtil.getDirectionality((char)11823));
        assertTrue(StringDataLetterUtil.isLetter((char)11823));
        assertTrue(StringDataUtil.isLetterOrDigit((char)11823));
    }
    @Test
    public void d2180(){
        assertEq(24,StringDataUtil.getType((char)11824));
        assertEq(13,StringDataUtil.getDirectionality((char)11824));
        assertFalse(StringDataLetterUtil.isLetter((char)11824));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11824));
    }
    @Test
    public void d2181(){
        assertEq(20,StringDataUtil.getType((char)11834));
        assertEq(13,StringDataUtil.getDirectionality((char)11834));
        assertFalse(StringDataLetterUtil.isLetter((char)11834));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11834));
    }
    @Test
    public void d2182(){
        assertEq(0,StringDataUtil.getType((char)11836));
        assertEq(-1,StringDataUtil.getDirectionality((char)11836));
        assertFalse(StringDataLetterUtil.isLetter((char)11836));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11836));
    }
    @Test
    public void d2183(){
        assertEq(28,StringDataUtil.getType((char)11904));
        assertEq(13,StringDataUtil.getDirectionality((char)11904));
        assertFalse(StringDataLetterUtil.isLetter((char)11904));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11904));
    }
    @Test
    public void d2184(){
        assertEq(0,StringDataUtil.getType((char)11930));
        assertEq(-1,StringDataUtil.getDirectionality((char)11930));
        assertFalse(StringDataLetterUtil.isLetter((char)11930));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11930));
    }
    @Test
    public void d2185(){
        assertEq(28,StringDataUtil.getType((char)11931));
        assertEq(13,StringDataUtil.getDirectionality((char)11931));
        assertFalse(StringDataLetterUtil.isLetter((char)11931));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11931));
    }
    @Test
    public void d2186(){
        assertEq(0,StringDataUtil.getType((char)12020));
        assertEq(-1,StringDataUtil.getDirectionality((char)12020));
        assertFalse(StringDataLetterUtil.isLetter((char)12020));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12020));
    }
    @Test
    public void d2187(){
        assertEq(28,StringDataUtil.getType((char)12032));
        assertEq(13,StringDataUtil.getDirectionality((char)12032));
        assertFalse(StringDataLetterUtil.isLetter((char)12032));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12032));
    }
    @Test
    public void d2188(){
        assertEq(0,StringDataUtil.getType((char)12246));
        assertEq(-1,StringDataUtil.getDirectionality((char)12246));
        assertFalse(StringDataLetterUtil.isLetter((char)12246));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12246));
    }
    @Test
    public void d2189(){
        assertEq(28,StringDataUtil.getType((char)12272));
        assertEq(13,StringDataUtil.getDirectionality((char)12272));
        assertFalse(StringDataLetterUtil.isLetter((char)12272));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12272));
    }
    @Test
    public void d2190(){
        assertEq(0,StringDataUtil.getType((char)12284));
        assertEq(-1,StringDataUtil.getDirectionality((char)12284));
        assertFalse(StringDataLetterUtil.isLetter((char)12284));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12284));
    }
    @Test
    public void d2191(){
        assertEq(12,StringDataUtil.getType((char)12288));
        assertEq(12,StringDataUtil.getDirectionality((char)12288));
        assertFalse(StringDataLetterUtil.isLetter((char)12288));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12288));
    }
    @Test
    public void d2192(){
        assertEq(24,StringDataUtil.getType((char)12289));
        assertEq(13,StringDataUtil.getDirectionality((char)12289));
        assertFalse(StringDataLetterUtil.isLetter((char)12289));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12289));
    }
    @Test
    public void d2193(){
        assertEq(28,StringDataUtil.getType((char)12292));
        assertEq(13,StringDataUtil.getDirectionality((char)12292));
        assertFalse(StringDataLetterUtil.isLetter((char)12292));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12292));
    }
    @Test
    public void d2194(){
        assertEq(4,StringDataUtil.getType((char)12293));
        assertEq(0,StringDataUtil.getDirectionality((char)12293));
        assertTrue(StringDataLetterUtil.isLetter((char)12293));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12293));
    }
    @Test
    public void d2195(){
        assertEq(5,StringDataUtil.getType((char)12294));
        assertEq(0,StringDataUtil.getDirectionality((char)12294));
        assertTrue(StringDataLetterUtil.isLetter((char)12294));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12294));
    }
    @Test
    public void d2196(){
        assertEq(10,StringDataUtil.getType((char)12295));
        assertEq(0,StringDataUtil.getDirectionality((char)12295));
        assertFalse(StringDataLetterUtil.isLetter((char)12295));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12295));
    }
    @Test
    public void d2197(){
        assertEq(21,StringDataUtil.getType((char)12296));
        assertEq(13,StringDataUtil.getDirectionality((char)12296));
        assertFalse(StringDataLetterUtil.isLetter((char)12296));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12296));
    }
    @Test
    public void d2198(){
        assertEq(22,StringDataUtil.getType((char)12297));
        assertEq(13,StringDataUtil.getDirectionality((char)12297));
        assertFalse(StringDataLetterUtil.isLetter((char)12297));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12297));
    }
    @Test
    public void d2199(){
        assertEq(21,StringDataUtil.getType((char)12298));
        assertEq(13,StringDataUtil.getDirectionality((char)12298));
        assertFalse(StringDataLetterUtil.isLetter((char)12298));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12298));
    }
    @Test
    public void d2200(){
        assertEq(22,StringDataUtil.getType((char)12299));
        assertEq(13,StringDataUtil.getDirectionality((char)12299));
        assertFalse(StringDataLetterUtil.isLetter((char)12299));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12299));
    }
    @Test
    public void d2201(){
        assertEq(21,StringDataUtil.getType((char)12300));
        assertEq(13,StringDataUtil.getDirectionality((char)12300));
        assertFalse(StringDataLetterUtil.isLetter((char)12300));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12300));
    }
    @Test
    public void d2202(){
        assertEq(22,StringDataUtil.getType((char)12301));
        assertEq(13,StringDataUtil.getDirectionality((char)12301));
        assertFalse(StringDataLetterUtil.isLetter((char)12301));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12301));
    }
    @Test
    public void d2203(){
        assertEq(21,StringDataUtil.getType((char)12302));
        assertEq(13,StringDataUtil.getDirectionality((char)12302));
        assertFalse(StringDataLetterUtil.isLetter((char)12302));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12302));
    }
    @Test
    public void d2204(){
        assertEq(22,StringDataUtil.getType((char)12303));
        assertEq(13,StringDataUtil.getDirectionality((char)12303));
        assertFalse(StringDataLetterUtil.isLetter((char)12303));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12303));
    }
    @Test
    public void d2205(){
        assertEq(21,StringDataUtil.getType((char)12304));
        assertEq(13,StringDataUtil.getDirectionality((char)12304));
        assertFalse(StringDataLetterUtil.isLetter((char)12304));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12304));
    }
    @Test
    public void d2206(){
        assertEq(22,StringDataUtil.getType((char)12305));
        assertEq(13,StringDataUtil.getDirectionality((char)12305));
        assertFalse(StringDataLetterUtil.isLetter((char)12305));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12305));
    }
    @Test
    public void d2207(){
        assertEq(28,StringDataUtil.getType((char)12306));
        assertEq(13,StringDataUtil.getDirectionality((char)12306));
        assertFalse(StringDataLetterUtil.isLetter((char)12306));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12306));
    }
    @Test
    public void d2208(){
        assertEq(21,StringDataUtil.getType((char)12308));
        assertEq(13,StringDataUtil.getDirectionality((char)12308));
        assertFalse(StringDataLetterUtil.isLetter((char)12308));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12308));
    }
    @Test
    public void d2209(){
        assertEq(22,StringDataUtil.getType((char)12309));
        assertEq(13,StringDataUtil.getDirectionality((char)12309));
        assertFalse(StringDataLetterUtil.isLetter((char)12309));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12309));
    }
    @Test
    public void d2210(){
        assertEq(21,StringDataUtil.getType((char)12310));
        assertEq(13,StringDataUtil.getDirectionality((char)12310));
        assertFalse(StringDataLetterUtil.isLetter((char)12310));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12310));
    }
    @Test
    public void d2211(){
        assertEq(22,StringDataUtil.getType((char)12311));
        assertEq(13,StringDataUtil.getDirectionality((char)12311));
        assertFalse(StringDataLetterUtil.isLetter((char)12311));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12311));
    }
    @Test
    public void d2212(){
        assertEq(21,StringDataUtil.getType((char)12312));
        assertEq(13,StringDataUtil.getDirectionality((char)12312));
        assertFalse(StringDataLetterUtil.isLetter((char)12312));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12312));
    }
    @Test
    public void d2213(){
        assertEq(22,StringDataUtil.getType((char)12313));
        assertEq(13,StringDataUtil.getDirectionality((char)12313));
        assertFalse(StringDataLetterUtil.isLetter((char)12313));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12313));
    }
    @Test
    public void d2214(){
        assertEq(21,StringDataUtil.getType((char)12314));
        assertEq(13,StringDataUtil.getDirectionality((char)12314));
        assertFalse(StringDataLetterUtil.isLetter((char)12314));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12314));
    }
    @Test
    public void d2215(){
        assertEq(22,StringDataUtil.getType((char)12315));
        assertEq(13,StringDataUtil.getDirectionality((char)12315));
        assertFalse(StringDataLetterUtil.isLetter((char)12315));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12315));
    }
    @Test
    public void d2216(){
        assertEq(20,StringDataUtil.getType((char)12316));
        assertEq(13,StringDataUtil.getDirectionality((char)12316));
        assertFalse(StringDataLetterUtil.isLetter((char)12316));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12316));
    }
    @Test
    public void d2217(){
        assertEq(21,StringDataUtil.getType((char)12317));
        assertEq(13,StringDataUtil.getDirectionality((char)12317));
        assertFalse(StringDataLetterUtil.isLetter((char)12317));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12317));
    }
    @Test
    public void d2218(){
        assertEq(22,StringDataUtil.getType((char)12318));
        assertEq(13,StringDataUtil.getDirectionality((char)12318));
        assertFalse(StringDataLetterUtil.isLetter((char)12318));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12318));
    }
    @Test
    public void d2219(){
        assertEq(28,StringDataUtil.getType((char)12320));
        assertEq(13,StringDataUtil.getDirectionality((char)12320));
        assertFalse(StringDataLetterUtil.isLetter((char)12320));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12320));
    }
    @Test
    public void d2220(){
        assertEq(10,StringDataUtil.getType((char)12321));
        assertEq(0,StringDataUtil.getDirectionality((char)12321));
        assertFalse(StringDataLetterUtil.isLetter((char)12321));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12321));
    }
    @Test
    public void d2221(){
        assertEq(6,StringDataUtil.getType((char)12330));
        assertEq(8,StringDataUtil.getDirectionality((char)12330));
        assertFalse(StringDataLetterUtil.isLetter((char)12330));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12330));
    }
    @Test
    public void d2222(){
        assertEq(8,StringDataUtil.getType((char)12334));
        assertEq(0,StringDataUtil.getDirectionality((char)12334));
        assertFalse(StringDataLetterUtil.isLetter((char)12334));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12334));
    }
    @Test
    public void d2223(){
        assertEq(20,StringDataUtil.getType((char)12336));
        assertEq(13,StringDataUtil.getDirectionality((char)12336));
        assertFalse(StringDataLetterUtil.isLetter((char)12336));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12336));
    }
    @Test
    public void d2224(){
        assertEq(4,StringDataUtil.getType((char)12337));
        assertEq(0,StringDataUtil.getDirectionality((char)12337));
        assertTrue(StringDataLetterUtil.isLetter((char)12337));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12337));
    }
    @Test
    public void d2225(){
        assertEq(28,StringDataUtil.getType((char)12342));
        assertEq(13,StringDataUtil.getDirectionality((char)12342));
        assertFalse(StringDataLetterUtil.isLetter((char)12342));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12342));
    }
    @Test
    public void d2226(){
        assertEq(10,StringDataUtil.getType((char)12344));
        assertEq(0,StringDataUtil.getDirectionality((char)12344));
        assertFalse(StringDataLetterUtil.isLetter((char)12344));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12344));
    }
    @Test
    public void d2227(){
        assertEq(4,StringDataUtil.getType((char)12347));
        assertEq(0,StringDataUtil.getDirectionality((char)12347));
        assertTrue(StringDataLetterUtil.isLetter((char)12347));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12347));
    }
    @Test
    public void d2228(){
        assertEq(5,StringDataUtil.getType((char)12348));
        assertEq(0,StringDataUtil.getDirectionality((char)12348));
        assertTrue(StringDataLetterUtil.isLetter((char)12348));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12348));
    }
    @Test
    public void d2229(){
        assertEq(24,StringDataUtil.getType((char)12349));
        assertEq(13,StringDataUtil.getDirectionality((char)12349));
        assertFalse(StringDataLetterUtil.isLetter((char)12349));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12349));
    }
    @Test
    public void d2230(){
        assertEq(28,StringDataUtil.getType((char)12350));
        assertEq(13,StringDataUtil.getDirectionality((char)12350));
        assertFalse(StringDataLetterUtil.isLetter((char)12350));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12350));
    }
    @Test
    public void d2231(){
        assertEq(0,StringDataUtil.getType((char)12352));
        assertEq(-1,StringDataUtil.getDirectionality((char)12352));
        assertFalse(StringDataLetterUtil.isLetter((char)12352));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12352));
    }
    @Test
    public void d2232(){
        assertEq(5,StringDataUtil.getType((char)12353));
        assertEq(0,StringDataUtil.getDirectionality((char)12353));
        assertTrue(StringDataLetterUtil.isLetter((char)12353));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12353));
    }
    @Test
    public void d2233(){
        assertEq(0,StringDataUtil.getType((char)12439));
        assertEq(-1,StringDataUtil.getDirectionality((char)12439));
        assertFalse(StringDataLetterUtil.isLetter((char)12439));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12439));
    }
    @Test
    public void d2234(){
        assertEq(6,StringDataUtil.getType((char)12441));
        assertEq(8,StringDataUtil.getDirectionality((char)12441));
        assertFalse(StringDataLetterUtil.isLetter((char)12441));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12441));
    }
    @Test
    public void d2235(){
        assertEq(27,StringDataUtil.getType((char)12443));
        assertEq(13,StringDataUtil.getDirectionality((char)12443));
        assertFalse(StringDataLetterUtil.isLetter((char)12443));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12443));
    }
    @Test
    public void d2236(){
        assertEq(4,StringDataUtil.getType((char)12445));
        assertEq(0,StringDataUtil.getDirectionality((char)12445));
        assertTrue(StringDataLetterUtil.isLetter((char)12445));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12445));
    }
    @Test
    public void d2237(){
        assertEq(5,StringDataUtil.getType((char)12447));
        assertEq(0,StringDataUtil.getDirectionality((char)12447));
        assertTrue(StringDataLetterUtil.isLetter((char)12447));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12447));
    }
    @Test
    public void d2238(){
        assertEq(20,StringDataUtil.getType((char)12448));
        assertEq(13,StringDataUtil.getDirectionality((char)12448));
        assertFalse(StringDataLetterUtil.isLetter((char)12448));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12448));
    }
    @Test
    public void d2239(){
        assertEq(5,StringDataUtil.getType((char)12449));
        assertEq(0,StringDataUtil.getDirectionality((char)12449));
        assertTrue(StringDataLetterUtil.isLetter((char)12449));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12449));
    }
    @Test
    public void d2240(){
        assertEq(24,StringDataUtil.getType((char)12539));
        assertEq(13,StringDataUtil.getDirectionality((char)12539));
        assertFalse(StringDataLetterUtil.isLetter((char)12539));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12539));
    }
    @Test
    public void d2241(){
        assertEq(4,StringDataUtil.getType((char)12540));
        assertEq(0,StringDataUtil.getDirectionality((char)12540));
        assertTrue(StringDataLetterUtil.isLetter((char)12540));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12540));
    }
    @Test
    public void d2242(){
        assertEq(5,StringDataUtil.getType((char)12543));
        assertEq(0,StringDataUtil.getDirectionality((char)12543));
        assertTrue(StringDataLetterUtil.isLetter((char)12543));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12543));
    }
    @Test
    public void d2243(){
        assertEq(0,StringDataUtil.getType((char)12544));
        assertEq(-1,StringDataUtil.getDirectionality((char)12544));
        assertFalse(StringDataLetterUtil.isLetter((char)12544));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12544));
    }
    @Test
    public void d2244(){
        assertEq(5,StringDataUtil.getType((char)12549));
        assertEq(0,StringDataUtil.getDirectionality((char)12549));
        assertTrue(StringDataLetterUtil.isLetter((char)12549));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12549));
    }
    @Test
    public void d2245(){
        assertEq(0,StringDataUtil.getType((char)12590));
        assertEq(-1,StringDataUtil.getDirectionality((char)12590));
        assertFalse(StringDataLetterUtil.isLetter((char)12590));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12590));
    }
    @Test
    public void d2246(){
        assertEq(5,StringDataUtil.getType((char)12593));
        assertEq(0,StringDataUtil.getDirectionality((char)12593));
        assertTrue(StringDataLetterUtil.isLetter((char)12593));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12593));
    }
    @Test
    public void d2247(){
        assertEq(0,StringDataUtil.getType((char)12687));
        assertEq(-1,StringDataUtil.getDirectionality((char)12687));
        assertFalse(StringDataLetterUtil.isLetter((char)12687));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12687));
    }
    @Test
    public void d2248(){
        assertEq(28,StringDataUtil.getType((char)12688));
        assertEq(0,StringDataUtil.getDirectionality((char)12688));
        assertFalse(StringDataLetterUtil.isLetter((char)12688));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12688));
    }
    @Test
    public void d2249(){
        assertEq(11,StringDataUtil.getType((char)12690));
        assertEq(0,StringDataUtil.getDirectionality((char)12690));
        assertFalse(StringDataLetterUtil.isLetter((char)12690));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12690));
    }
    @Test
    public void d2250(){
        assertEq(28,StringDataUtil.getType((char)12694));
        assertEq(0,StringDataUtil.getDirectionality((char)12694));
        assertFalse(StringDataLetterUtil.isLetter((char)12694));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12694));
    }
    @Test
    public void d2251(){
        assertEq(5,StringDataUtil.getType((char)12704));
        assertEq(0,StringDataUtil.getDirectionality((char)12704));
        assertTrue(StringDataLetterUtil.isLetter((char)12704));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12704));
    }
    @Test
    public void d2252(){
        assertEq(0,StringDataUtil.getType((char)12731));
        assertEq(-1,StringDataUtil.getDirectionality((char)12731));
        assertFalse(StringDataLetterUtil.isLetter((char)12731));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12731));
    }
    @Test
    public void d2253(){
        assertEq(28,StringDataUtil.getType((char)12736));
        assertEq(13,StringDataUtil.getDirectionality((char)12736));
        assertFalse(StringDataLetterUtil.isLetter((char)12736));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12736));
    }
    @Test
    public void d2254(){
        assertEq(0,StringDataUtil.getType((char)12772));
        assertEq(-1,StringDataUtil.getDirectionality((char)12772));
        assertFalse(StringDataLetterUtil.isLetter((char)12772));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12772));
    }
    @Test
    public void d2255(){
        assertEq(5,StringDataUtil.getType((char)12784));
        assertEq(0,StringDataUtil.getDirectionality((char)12784));
        assertTrue(StringDataLetterUtil.isLetter((char)12784));
        assertTrue(StringDataUtil.isLetterOrDigit((char)12784));
    }
    @Test
    public void d2256(){
        assertEq(28,StringDataUtil.getType((char)12800));
        assertEq(0,StringDataUtil.getDirectionality((char)12800));
        assertFalse(StringDataLetterUtil.isLetter((char)12800));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12800));
    }
    @Test
    public void d2257(){
        assertEq(0,StringDataUtil.getType((char)12831));
        assertEq(-1,StringDataUtil.getDirectionality((char)12831));
        assertFalse(StringDataLetterUtil.isLetter((char)12831));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12831));
    }
    @Test
    public void d2258(){
        assertEq(11,StringDataUtil.getType((char)12832));
        assertEq(0,StringDataUtil.getDirectionality((char)12832));
        assertFalse(StringDataLetterUtil.isLetter((char)12832));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12832));
    }
    @Test
    public void d2259(){
        assertEq(28,StringDataUtil.getType((char)12842));
        assertEq(0,StringDataUtil.getDirectionality((char)12842));
        assertFalse(StringDataLetterUtil.isLetter((char)12842));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12842));
    }
    @Test
    public void d2260(){
        assertEq(11,StringDataUtil.getType((char)12872));
        assertEq(0,StringDataUtil.getDirectionality((char)12872));
        assertFalse(StringDataLetterUtil.isLetter((char)12872));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12872));
    }
    @Test
    public void d2261(){
        assertEq(28,StringDataUtil.getType((char)12880));
        assertEq(13,StringDataUtil.getDirectionality((char)12880));
        assertFalse(StringDataLetterUtil.isLetter((char)12880));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12880));
    }
    @Test
    public void d2262(){
        assertEq(11,StringDataUtil.getType((char)12881));
        assertEq(13,StringDataUtil.getDirectionality((char)12881));
        assertFalse(StringDataLetterUtil.isLetter((char)12881));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12881));
    }
    @Test
    public void d2263(){
        assertEq(28,StringDataUtil.getType((char)12896));
        assertEq(0,StringDataUtil.getDirectionality((char)12896));
        assertFalse(StringDataLetterUtil.isLetter((char)12896));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12896));
    }
    @Test
    public void d2264(){
        assertEq(11,StringDataUtil.getType((char)12928));
        assertEq(0,StringDataUtil.getDirectionality((char)12928));
        assertFalse(StringDataLetterUtil.isLetter((char)12928));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12928));
    }
    @Test
    public void d2265(){
        assertEq(28,StringDataUtil.getType((char)12938));
        assertEq(0,StringDataUtil.getDirectionality((char)12938));
        assertFalse(StringDataLetterUtil.isLetter((char)12938));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12938));
    }
    @Test
    public void d2266(){
        assertEq(11,StringDataUtil.getType((char)12977));
        assertEq(13,StringDataUtil.getDirectionality((char)12977));
        assertFalse(StringDataLetterUtil.isLetter((char)12977));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12977));
    }
    @Test
    public void d2267(){
        assertEq(28,StringDataUtil.getType((char)12992));
        assertEq(0,StringDataUtil.getDirectionality((char)12992));
        assertFalse(StringDataLetterUtil.isLetter((char)12992));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12992));
    }
    @Test
    public void d2268(){
        assertEq(0,StringDataUtil.getType((char)13055));
        assertEq(-1,StringDataUtil.getDirectionality((char)13055));
        assertFalse(StringDataLetterUtil.isLetter((char)13055));
        assertFalse(StringDataUtil.isLetterOrDigit((char)13055));
    }
    @Test
    public void d2269(){
        assertEq(28,StringDataUtil.getType((char)13056));
        assertEq(0,StringDataUtil.getDirectionality((char)13056));
        assertFalse(StringDataLetterUtil.isLetter((char)13056));
        assertFalse(StringDataUtil.isLetterOrDigit((char)13056));
    }
    @Test
    public void d2270(){
        assertEq(5,StringDataUtil.getType((char)13312));
        assertEq(0,StringDataUtil.getDirectionality((char)13312));
        assertTrue(StringDataLetterUtil.isLetter((char)13312));
        assertTrue(StringDataUtil.isLetterOrDigit((char)13312));
    }
    @Test
    public void d2271(){
        assertEq(0,StringDataUtil.getType((char)19894));
        assertEq(-1,StringDataUtil.getDirectionality((char)19894));
        assertFalse(StringDataLetterUtil.isLetter((char)19894));
        assertFalse(StringDataUtil.isLetterOrDigit((char)19894));
    }
    @Test
    public void d2272(){
        assertEq(28,StringDataUtil.getType((char)19904));
        assertEq(13,StringDataUtil.getDirectionality((char)19904));
        assertFalse(StringDataLetterUtil.isLetter((char)19904));
        assertFalse(StringDataUtil.isLetterOrDigit((char)19904));
    }
    @Test
    public void d2273(){
        assertEq(5,StringDataUtil.getType((char)19968));
        assertEq(0,StringDataUtil.getDirectionality((char)19968));
        assertTrue(StringDataLetterUtil.isLetter((char)19968));
        assertTrue(StringDataUtil.isLetterOrDigit((char)19968));
    }
    @Test
    public void d2274(){
        assertEq(0,StringDataUtil.getType((char)40909));
        assertEq(-1,StringDataUtil.getDirectionality((char)40909));
        assertFalse(StringDataLetterUtil.isLetter((char)40909));
        assertFalse(StringDataUtil.isLetterOrDigit((char)40909));
    }
    @Test
    public void d2275(){
        assertEq(5,StringDataUtil.getType((char)40960));
        assertEq(0,StringDataUtil.getDirectionality((char)40960));
        assertTrue(StringDataLetterUtil.isLetter((char)40960));
        assertTrue(StringDataUtil.isLetterOrDigit((char)40960));
    }
    @Test
    public void d2276(){
        assertEq(4,StringDataUtil.getType((char)40981));
        assertEq(0,StringDataUtil.getDirectionality((char)40981));
        assertTrue(StringDataLetterUtil.isLetter((char)40981));
        assertTrue(StringDataUtil.isLetterOrDigit((char)40981));
    }
    @Test
    public void d2277(){
        assertEq(5,StringDataUtil.getType((char)40982));
        assertEq(0,StringDataUtil.getDirectionality((char)40982));
        assertTrue(StringDataLetterUtil.isLetter((char)40982));
        assertTrue(StringDataUtil.isLetterOrDigit((char)40982));
    }
    @Test
    public void d2278(){
        assertEq(0,StringDataUtil.getType((char)42125));
        assertEq(-1,StringDataUtil.getDirectionality((char)42125));
        assertFalse(StringDataLetterUtil.isLetter((char)42125));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42125));
    }
    @Test
    public void d2279(){
        assertEq(28,StringDataUtil.getType((char)42128));
        assertEq(13,StringDataUtil.getDirectionality((char)42128));
        assertFalse(StringDataLetterUtil.isLetter((char)42128));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42128));
    }
    @Test
    public void d2280(){
        assertEq(0,StringDataUtil.getType((char)42183));
        assertEq(-1,StringDataUtil.getDirectionality((char)42183));
        assertFalse(StringDataLetterUtil.isLetter((char)42183));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42183));
    }
    @Test
    public void d2281(){
        assertEq(5,StringDataUtil.getType((char)42192));
        assertEq(0,StringDataUtil.getDirectionality((char)42192));
        assertTrue(StringDataLetterUtil.isLetter((char)42192));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42192));
    }
    @Test
    public void d2282(){
        assertEq(4,StringDataUtil.getType((char)42232));
        assertEq(0,StringDataUtil.getDirectionality((char)42232));
        assertTrue(StringDataLetterUtil.isLetter((char)42232));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42232));
    }
    @Test
    public void d2283(){
        assertEq(24,StringDataUtil.getType((char)42238));
        assertEq(0,StringDataUtil.getDirectionality((char)42238));
        assertFalse(StringDataLetterUtil.isLetter((char)42238));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42238));
    }
    @Test
    public void d2284(){
        assertEq(5,StringDataUtil.getType((char)42240));
        assertEq(0,StringDataUtil.getDirectionality((char)42240));
        assertTrue(StringDataLetterUtil.isLetter((char)42240));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42240));
    }
    @Test
    public void d2285(){
        assertEq(4,StringDataUtil.getType((char)42508));
        assertEq(0,StringDataUtil.getDirectionality((char)42508));
        assertTrue(StringDataLetterUtil.isLetter((char)42508));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42508));
    }
    @Test
    public void d2286(){
        assertEq(24,StringDataUtil.getType((char)42509));
        assertEq(13,StringDataUtil.getDirectionality((char)42509));
        assertFalse(StringDataLetterUtil.isLetter((char)42509));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42509));
    }
    @Test
    public void d2287(){
        assertEq(5,StringDataUtil.getType((char)42512));
        assertEq(0,StringDataUtil.getDirectionality((char)42512));
        assertTrue(StringDataLetterUtil.isLetter((char)42512));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42512));
    }
    @Test
    public void d2288(){
        assertEq(9,StringDataUtil.getType((char)42528));
        assertEq(0,StringDataUtil.getDirectionality((char)42528));
        assertFalse(StringDataLetterUtil.isLetter((char)42528));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42528));
    }
    @Test
    public void d2289(){
        assertEq(5,StringDataUtil.getType((char)42538));
        assertEq(0,StringDataUtil.getDirectionality((char)42538));
        assertTrue(StringDataLetterUtil.isLetter((char)42538));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42538));
    }
    @Test
    public void d2290(){
        assertEq(0,StringDataUtil.getType((char)42540));
        assertEq(-1,StringDataUtil.getDirectionality((char)42540));
        assertFalse(StringDataLetterUtil.isLetter((char)42540));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42540));
    }
    @Test
    public void d2291(){
        assertEq(1,StringDataUtil.getType((char)42560));
        assertEq(0,StringDataUtil.getDirectionality((char)42560));
        assertTrue(StringDataLetterUtil.isLetter((char)42560));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42560));
    }
    @Test
    public void d2292(){
        assertEq(2,StringDataUtil.getType((char)42561));
        assertEq(0,StringDataUtil.getDirectionality((char)42561));
        assertTrue(StringDataLetterUtil.isLetter((char)42561));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42561));
    }
    @Test
    public void d2293(){
        assertEq(1,StringDataUtil.getType((char)42562));
        assertEq(0,StringDataUtil.getDirectionality((char)42562));
        assertTrue(StringDataLetterUtil.isLetter((char)42562));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42562));
    }
    @Test
    public void d2294(){
        assertEq(2,StringDataUtil.getType((char)42563));
        assertEq(0,StringDataUtil.getDirectionality((char)42563));
        assertTrue(StringDataLetterUtil.isLetter((char)42563));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42563));
    }
    @Test
    public void d2295(){
        assertEq(1,StringDataUtil.getType((char)42564));
        assertEq(0,StringDataUtil.getDirectionality((char)42564));
        assertTrue(StringDataLetterUtil.isLetter((char)42564));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42564));
    }
    @Test
    public void d2296(){
        assertEq(2,StringDataUtil.getType((char)42565));
        assertEq(0,StringDataUtil.getDirectionality((char)42565));
        assertTrue(StringDataLetterUtil.isLetter((char)42565));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42565));
    }
    @Test
    public void d2297(){
        assertEq(1,StringDataUtil.getType((char)42566));
        assertEq(0,StringDataUtil.getDirectionality((char)42566));
        assertTrue(StringDataLetterUtil.isLetter((char)42566));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42566));
    }
    @Test
    public void d2298(){
        assertEq(2,StringDataUtil.getType((char)42567));
        assertEq(0,StringDataUtil.getDirectionality((char)42567));
        assertTrue(StringDataLetterUtil.isLetter((char)42567));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42567));
    }
    @Test
    public void d2299(){
        assertEq(1,StringDataUtil.getType((char)42568));
        assertEq(0,StringDataUtil.getDirectionality((char)42568));
        assertTrue(StringDataLetterUtil.isLetter((char)42568));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42568));
    }
    @Test
    public void d2300(){
        assertEq(2,StringDataUtil.getType((char)42569));
        assertEq(0,StringDataUtil.getDirectionality((char)42569));
        assertTrue(StringDataLetterUtil.isLetter((char)42569));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42569));
    }
    @Test
    public void d2301(){
        assertEq(1,StringDataUtil.getType((char)42570));
        assertEq(0,StringDataUtil.getDirectionality((char)42570));
        assertTrue(StringDataLetterUtil.isLetter((char)42570));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42570));
    }
    @Test
    public void d2302(){
        assertEq(2,StringDataUtil.getType((char)42571));
        assertEq(0,StringDataUtil.getDirectionality((char)42571));
        assertTrue(StringDataLetterUtil.isLetter((char)42571));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42571));
    }
    @Test
    public void d2303(){
        assertEq(1,StringDataUtil.getType((char)42572));
        assertEq(0,StringDataUtil.getDirectionality((char)42572));
        assertTrue(StringDataLetterUtil.isLetter((char)42572));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42572));
    }
    @Test
    public void d2304(){
        assertEq(2,StringDataUtil.getType((char)42573));
        assertEq(0,StringDataUtil.getDirectionality((char)42573));
        assertTrue(StringDataLetterUtil.isLetter((char)42573));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42573));
    }
    @Test
    public void d2305(){
        assertEq(1,StringDataUtil.getType((char)42574));
        assertEq(0,StringDataUtil.getDirectionality((char)42574));
        assertTrue(StringDataLetterUtil.isLetter((char)42574));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42574));
    }
    @Test
    public void d2306(){
        assertEq(2,StringDataUtil.getType((char)42575));
        assertEq(0,StringDataUtil.getDirectionality((char)42575));
        assertTrue(StringDataLetterUtil.isLetter((char)42575));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42575));
    }
    @Test
    public void d2307(){
        assertEq(1,StringDataUtil.getType((char)42576));
        assertEq(0,StringDataUtil.getDirectionality((char)42576));
        assertTrue(StringDataLetterUtil.isLetter((char)42576));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42576));
    }
    @Test
    public void d2308(){
        assertEq(2,StringDataUtil.getType((char)42577));
        assertEq(0,StringDataUtil.getDirectionality((char)42577));
        assertTrue(StringDataLetterUtil.isLetter((char)42577));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42577));
    }
    @Test
    public void d2309(){
        assertEq(1,StringDataUtil.getType((char)42578));
        assertEq(0,StringDataUtil.getDirectionality((char)42578));
        assertTrue(StringDataLetterUtil.isLetter((char)42578));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42578));
    }
    @Test
    public void d2310(){
        assertEq(2,StringDataUtil.getType((char)42579));
        assertEq(0,StringDataUtil.getDirectionality((char)42579));
        assertTrue(StringDataLetterUtil.isLetter((char)42579));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42579));
    }
    @Test
    public void d2311(){
        assertEq(1,StringDataUtil.getType((char)42580));
        assertEq(0,StringDataUtil.getDirectionality((char)42580));
        assertTrue(StringDataLetterUtil.isLetter((char)42580));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42580));
    }
    @Test
    public void d2312(){
        assertEq(2,StringDataUtil.getType((char)42581));
        assertEq(0,StringDataUtil.getDirectionality((char)42581));
        assertTrue(StringDataLetterUtil.isLetter((char)42581));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42581));
    }
    @Test
    public void d2313(){
        assertEq(1,StringDataUtil.getType((char)42582));
        assertEq(0,StringDataUtil.getDirectionality((char)42582));
        assertTrue(StringDataLetterUtil.isLetter((char)42582));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42582));
    }
    @Test
    public void d2314(){
        assertEq(2,StringDataUtil.getType((char)42583));
        assertEq(0,StringDataUtil.getDirectionality((char)42583));
        assertTrue(StringDataLetterUtil.isLetter((char)42583));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42583));
    }
    @Test
    public void d2315(){
        assertEq(1,StringDataUtil.getType((char)42584));
        assertEq(0,StringDataUtil.getDirectionality((char)42584));
        assertTrue(StringDataLetterUtil.isLetter((char)42584));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42584));
    }
    @Test
    public void d2316(){
        assertEq(2,StringDataUtil.getType((char)42585));
        assertEq(0,StringDataUtil.getDirectionality((char)42585));
        assertTrue(StringDataLetterUtil.isLetter((char)42585));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42585));
    }
    @Test
    public void d2317(){
        assertEq(1,StringDataUtil.getType((char)42586));
        assertEq(0,StringDataUtil.getDirectionality((char)42586));
        assertTrue(StringDataLetterUtil.isLetter((char)42586));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42586));
    }
    @Test
    public void d2318(){
        assertEq(2,StringDataUtil.getType((char)42587));
        assertEq(0,StringDataUtil.getDirectionality((char)42587));
        assertTrue(StringDataLetterUtil.isLetter((char)42587));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42587));
    }
    @Test
    public void d2319(){
        assertEq(1,StringDataUtil.getType((char)42588));
        assertEq(0,StringDataUtil.getDirectionality((char)42588));
        assertTrue(StringDataLetterUtil.isLetter((char)42588));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42588));
    }
    @Test
    public void d2320(){
        assertEq(2,StringDataUtil.getType((char)42589));
        assertEq(0,StringDataUtil.getDirectionality((char)42589));
        assertTrue(StringDataLetterUtil.isLetter((char)42589));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42589));
    }
    @Test
    public void d2321(){
        assertEq(1,StringDataUtil.getType((char)42590));
        assertEq(0,StringDataUtil.getDirectionality((char)42590));
        assertTrue(StringDataLetterUtil.isLetter((char)42590));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42590));
    }
    @Test
    public void d2322(){
        assertEq(2,StringDataUtil.getType((char)42591));
        assertEq(0,StringDataUtil.getDirectionality((char)42591));
        assertTrue(StringDataLetterUtil.isLetter((char)42591));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42591));
    }
    @Test
    public void d2323(){
        assertEq(1,StringDataUtil.getType((char)42592));
        assertEq(0,StringDataUtil.getDirectionality((char)42592));
        assertTrue(StringDataLetterUtil.isLetter((char)42592));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42592));
    }
    @Test
    public void d2324(){
        assertEq(2,StringDataUtil.getType((char)42593));
        assertEq(0,StringDataUtil.getDirectionality((char)42593));
        assertTrue(StringDataLetterUtil.isLetter((char)42593));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42593));
    }
    @Test
    public void d2325(){
        assertEq(1,StringDataUtil.getType((char)42594));
        assertEq(0,StringDataUtil.getDirectionality((char)42594));
        assertTrue(StringDataLetterUtil.isLetter((char)42594));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42594));
    }
    @Test
    public void d2326(){
        assertEq(2,StringDataUtil.getType((char)42595));
        assertEq(0,StringDataUtil.getDirectionality((char)42595));
        assertTrue(StringDataLetterUtil.isLetter((char)42595));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42595));
    }
    @Test
    public void d2327(){
        assertEq(1,StringDataUtil.getType((char)42596));
        assertEq(0,StringDataUtil.getDirectionality((char)42596));
        assertTrue(StringDataLetterUtil.isLetter((char)42596));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42596));
    }
    @Test
    public void d2328(){
        assertEq(2,StringDataUtil.getType((char)42597));
        assertEq(0,StringDataUtil.getDirectionality((char)42597));
        assertTrue(StringDataLetterUtil.isLetter((char)42597));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42597));
    }
    @Test
    public void d2329(){
        assertEq(1,StringDataUtil.getType((char)42598));
        assertEq(0,StringDataUtil.getDirectionality((char)42598));
        assertTrue(StringDataLetterUtil.isLetter((char)42598));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42598));
    }
    @Test
    public void d2330(){
        assertEq(2,StringDataUtil.getType((char)42599));
        assertEq(0,StringDataUtil.getDirectionality((char)42599));
        assertTrue(StringDataLetterUtil.isLetter((char)42599));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42599));
    }
    @Test
    public void d2331(){
        assertEq(1,StringDataUtil.getType((char)42600));
        assertEq(0,StringDataUtil.getDirectionality((char)42600));
        assertTrue(StringDataLetterUtil.isLetter((char)42600));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42600));
    }
    @Test
    public void d2332(){
        assertEq(2,StringDataUtil.getType((char)42601));
        assertEq(0,StringDataUtil.getDirectionality((char)42601));
        assertTrue(StringDataLetterUtil.isLetter((char)42601));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42601));
    }
    @Test
    public void d2333(){
        assertEq(1,StringDataUtil.getType((char)42602));
        assertEq(0,StringDataUtil.getDirectionality((char)42602));
        assertTrue(StringDataLetterUtil.isLetter((char)42602));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42602));
    }
    @Test
    public void d2334(){
        assertEq(2,StringDataUtil.getType((char)42603));
        assertEq(0,StringDataUtil.getDirectionality((char)42603));
        assertTrue(StringDataLetterUtil.isLetter((char)42603));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42603));
    }
    @Test
    public void d2335(){
        assertEq(1,StringDataUtil.getType((char)42604));
        assertEq(0,StringDataUtil.getDirectionality((char)42604));
        assertTrue(StringDataLetterUtil.isLetter((char)42604));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42604));
    }
    @Test
    public void d2336(){
        assertEq(2,StringDataUtil.getType((char)42605));
        assertEq(0,StringDataUtil.getDirectionality((char)42605));
        assertTrue(StringDataLetterUtil.isLetter((char)42605));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42605));
    }
    @Test
    public void d2337(){
        assertEq(5,StringDataUtil.getType((char)42606));
        assertEq(0,StringDataUtil.getDirectionality((char)42606));
        assertTrue(StringDataLetterUtil.isLetter((char)42606));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42606));
    }
    @Test
    public void d2338(){
        assertEq(6,StringDataUtil.getType((char)42607));
        assertEq(8,StringDataUtil.getDirectionality((char)42607));
        assertFalse(StringDataLetterUtil.isLetter((char)42607));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42607));
    }
    @Test
    public void d2339(){
        assertEq(7,StringDataUtil.getType((char)42608));
        assertEq(8,StringDataUtil.getDirectionality((char)42608));
        assertFalse(StringDataLetterUtil.isLetter((char)42608));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42608));
    }
    @Test
    public void d2340(){
        assertEq(24,StringDataUtil.getType((char)42611));
        assertEq(13,StringDataUtil.getDirectionality((char)42611));
        assertFalse(StringDataLetterUtil.isLetter((char)42611));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42611));
    }
    @Test
    public void d2341(){
        assertEq(6,StringDataUtil.getType((char)42612));
        assertEq(8,StringDataUtil.getDirectionality((char)42612));
        assertFalse(StringDataLetterUtil.isLetter((char)42612));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42612));
    }
    @Test
    public void d2342(){
        assertEq(24,StringDataUtil.getType((char)42622));
        assertEq(13,StringDataUtil.getDirectionality((char)42622));
        assertFalse(StringDataLetterUtil.isLetter((char)42622));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42622));
    }
    @Test
    public void d2343(){
        assertEq(4,StringDataUtil.getType((char)42623));
        assertEq(13,StringDataUtil.getDirectionality((char)42623));
        assertTrue(StringDataLetterUtil.isLetter((char)42623));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42623));
    }
    @Test
    public void d2344(){
        assertEq(1,StringDataUtil.getType((char)42624));
        assertEq(0,StringDataUtil.getDirectionality((char)42624));
        assertTrue(StringDataLetterUtil.isLetter((char)42624));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42624));
    }
    @Test
    public void d2345(){
        assertEq(2,StringDataUtil.getType((char)42625));
        assertEq(0,StringDataUtil.getDirectionality((char)42625));
        assertTrue(StringDataLetterUtil.isLetter((char)42625));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42625));
    }
    @Test
    public void d2346(){
        assertEq(1,StringDataUtil.getType((char)42626));
        assertEq(0,StringDataUtil.getDirectionality((char)42626));
        assertTrue(StringDataLetterUtil.isLetter((char)42626));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42626));
    }
    @Test
    public void d2347(){
        assertEq(2,StringDataUtil.getType((char)42627));
        assertEq(0,StringDataUtil.getDirectionality((char)42627));
        assertTrue(StringDataLetterUtil.isLetter((char)42627));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42627));
    }
    @Test
    public void d2348(){
        assertEq(1,StringDataUtil.getType((char)42628));
        assertEq(0,StringDataUtil.getDirectionality((char)42628));
        assertTrue(StringDataLetterUtil.isLetter((char)42628));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42628));
    }
    @Test
    public void d2349(){
        assertEq(2,StringDataUtil.getType((char)42629));
        assertEq(0,StringDataUtil.getDirectionality((char)42629));
        assertTrue(StringDataLetterUtil.isLetter((char)42629));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42629));
    }
    @Test
    public void d2350(){
        assertEq(1,StringDataUtil.getType((char)42630));
        assertEq(0,StringDataUtil.getDirectionality((char)42630));
        assertTrue(StringDataLetterUtil.isLetter((char)42630));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42630));
    }
    @Test
    public void d2351(){
        assertEq(2,StringDataUtil.getType((char)42631));
        assertEq(0,StringDataUtil.getDirectionality((char)42631));
        assertTrue(StringDataLetterUtil.isLetter((char)42631));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42631));
    }
    @Test
    public void d2352(){
        assertEq(1,StringDataUtil.getType((char)42632));
        assertEq(0,StringDataUtil.getDirectionality((char)42632));
        assertTrue(StringDataLetterUtil.isLetter((char)42632));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42632));
    }
    @Test
    public void d2353(){
        assertEq(2,StringDataUtil.getType((char)42633));
        assertEq(0,StringDataUtil.getDirectionality((char)42633));
        assertTrue(StringDataLetterUtil.isLetter((char)42633));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42633));
    }
    @Test
    public void d2354(){
        assertEq(1,StringDataUtil.getType((char)42634));
        assertEq(0,StringDataUtil.getDirectionality((char)42634));
        assertTrue(StringDataLetterUtil.isLetter((char)42634));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42634));
    }
    @Test
    public void d2355(){
        assertEq(2,StringDataUtil.getType((char)42635));
        assertEq(0,StringDataUtil.getDirectionality((char)42635));
        assertTrue(StringDataLetterUtil.isLetter((char)42635));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42635));
    }
    @Test
    public void d2356(){
        assertEq(1,StringDataUtil.getType((char)42636));
        assertEq(0,StringDataUtil.getDirectionality((char)42636));
        assertTrue(StringDataLetterUtil.isLetter((char)42636));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42636));
    }
    @Test
    public void d2357(){
        assertEq(2,StringDataUtil.getType((char)42637));
        assertEq(0,StringDataUtil.getDirectionality((char)42637));
        assertTrue(StringDataLetterUtil.isLetter((char)42637));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42637));
    }
    @Test
    public void d2358(){
        assertEq(1,StringDataUtil.getType((char)42638));
        assertEq(0,StringDataUtil.getDirectionality((char)42638));
        assertTrue(StringDataLetterUtil.isLetter((char)42638));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42638));
    }
    @Test
    public void d2359(){
        assertEq(2,StringDataUtil.getType((char)42639));
        assertEq(0,StringDataUtil.getDirectionality((char)42639));
        assertTrue(StringDataLetterUtil.isLetter((char)42639));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42639));
    }
    @Test
    public void d2360(){
        assertEq(1,StringDataUtil.getType((char)42640));
        assertEq(0,StringDataUtil.getDirectionality((char)42640));
        assertTrue(StringDataLetterUtil.isLetter((char)42640));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42640));
    }
    @Test
    public void d2361(){
        assertEq(2,StringDataUtil.getType((char)42641));
        assertEq(0,StringDataUtil.getDirectionality((char)42641));
        assertTrue(StringDataLetterUtil.isLetter((char)42641));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42641));
    }
    @Test
    public void d2362(){
        assertEq(1,StringDataUtil.getType((char)42642));
        assertEq(0,StringDataUtil.getDirectionality((char)42642));
        assertTrue(StringDataLetterUtil.isLetter((char)42642));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42642));
    }
    @Test
    public void d2363(){
        assertEq(2,StringDataUtil.getType((char)42643));
        assertEq(0,StringDataUtil.getDirectionality((char)42643));
        assertTrue(StringDataLetterUtil.isLetter((char)42643));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42643));
    }
    @Test
    public void d2364(){
        assertEq(1,StringDataUtil.getType((char)42644));
        assertEq(0,StringDataUtil.getDirectionality((char)42644));
        assertTrue(StringDataLetterUtil.isLetter((char)42644));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42644));
    }
    @Test
    public void d2365(){
        assertEq(2,StringDataUtil.getType((char)42645));
        assertEq(0,StringDataUtil.getDirectionality((char)42645));
        assertTrue(StringDataLetterUtil.isLetter((char)42645));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42645));
    }
    @Test
    public void d2366(){
        assertEq(1,StringDataUtil.getType((char)42646));
        assertEq(0,StringDataUtil.getDirectionality((char)42646));
        assertTrue(StringDataLetterUtil.isLetter((char)42646));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42646));
    }
    @Test
    public void d2367(){
        assertEq(2,StringDataUtil.getType((char)42647));
        assertEq(0,StringDataUtil.getDirectionality((char)42647));
        assertTrue(StringDataLetterUtil.isLetter((char)42647));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42647));
    }
    @Test
    public void d2368(){
        assertEq(0,StringDataUtil.getType((char)42648));
        assertEq(-1,StringDataUtil.getDirectionality((char)42648));
        assertFalse(StringDataLetterUtil.isLetter((char)42648));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42648));
    }
    @Test
    public void d2369(){
        assertEq(6,StringDataUtil.getType((char)42655));
        assertEq(8,StringDataUtil.getDirectionality((char)42655));
        assertFalse(StringDataLetterUtil.isLetter((char)42655));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42655));
    }
    @Test
    public void d2370(){
        assertEq(5,StringDataUtil.getType((char)42656));
        assertEq(0,StringDataUtil.getDirectionality((char)42656));
        assertTrue(StringDataLetterUtil.isLetter((char)42656));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42656));
    }
    @Test
    public void d2371(){
        assertEq(10,StringDataUtil.getType((char)42726));
        assertEq(0,StringDataUtil.getDirectionality((char)42726));
        assertFalse(StringDataLetterUtil.isLetter((char)42726));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42726));
    }
    @Test
    public void d2372(){
        assertEq(6,StringDataUtil.getType((char)42736));
        assertEq(8,StringDataUtil.getDirectionality((char)42736));
        assertFalse(StringDataLetterUtil.isLetter((char)42736));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42736));
    }
    @Test
    public void d2373(){
        assertEq(24,StringDataUtil.getType((char)42738));
        assertEq(0,StringDataUtil.getDirectionality((char)42738));
        assertFalse(StringDataLetterUtil.isLetter((char)42738));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42738));
    }
    @Test
    public void d2374(){
        assertEq(0,StringDataUtil.getType((char)42744));
        assertEq(-1,StringDataUtil.getDirectionality((char)42744));
        assertFalse(StringDataLetterUtil.isLetter((char)42744));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42744));
    }
    @Test
    public void d2375(){
        assertEq(27,StringDataUtil.getType((char)42752));
        assertEq(13,StringDataUtil.getDirectionality((char)42752));
        assertFalse(StringDataLetterUtil.isLetter((char)42752));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42752));
    }
    @Test
    public void d2376(){
        assertEq(4,StringDataUtil.getType((char)42775));
        assertEq(13,StringDataUtil.getDirectionality((char)42775));
        assertTrue(StringDataLetterUtil.isLetter((char)42775));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42775));
    }
    @Test
    public void d2377(){
        assertEq(27,StringDataUtil.getType((char)42784));
        assertEq(13,StringDataUtil.getDirectionality((char)42784));
        assertFalse(StringDataLetterUtil.isLetter((char)42784));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42784));
    }
    @Test
    public void d2378(){
        assertEq(1,StringDataUtil.getType((char)42786));
        assertEq(0,StringDataUtil.getDirectionality((char)42786));
        assertTrue(StringDataLetterUtil.isLetter((char)42786));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42786));
    }
    @Test
    public void d2379(){
        assertEq(2,StringDataUtil.getType((char)42787));
        assertEq(0,StringDataUtil.getDirectionality((char)42787));
        assertTrue(StringDataLetterUtil.isLetter((char)42787));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42787));
    }
    @Test
    public void d2380(){
        assertEq(1,StringDataUtil.getType((char)42788));
        assertEq(0,StringDataUtil.getDirectionality((char)42788));
        assertTrue(StringDataLetterUtil.isLetter((char)42788));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42788));
    }
    @Test
    public void d2381(){
        assertEq(2,StringDataUtil.getType((char)42789));
        assertEq(0,StringDataUtil.getDirectionality((char)42789));
        assertTrue(StringDataLetterUtil.isLetter((char)42789));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42789));
    }
    @Test
    public void d2382(){
        assertEq(1,StringDataUtil.getType((char)42790));
        assertEq(0,StringDataUtil.getDirectionality((char)42790));
        assertTrue(StringDataLetterUtil.isLetter((char)42790));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42790));
    }
    @Test
    public void d2383(){
        assertEq(2,StringDataUtil.getType((char)42791));
        assertEq(0,StringDataUtil.getDirectionality((char)42791));
        assertTrue(StringDataLetterUtil.isLetter((char)42791));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42791));
    }
    @Test
    public void d2384(){
        assertEq(1,StringDataUtil.getType((char)42792));
        assertEq(0,StringDataUtil.getDirectionality((char)42792));
        assertTrue(StringDataLetterUtil.isLetter((char)42792));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42792));
    }
    @Test
    public void d2385(){
        assertEq(2,StringDataUtil.getType((char)42793));
        assertEq(0,StringDataUtil.getDirectionality((char)42793));
        assertTrue(StringDataLetterUtil.isLetter((char)42793));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42793));
    }
    @Test
    public void d2386(){
        assertEq(1,StringDataUtil.getType((char)42794));
        assertEq(0,StringDataUtil.getDirectionality((char)42794));
        assertTrue(StringDataLetterUtil.isLetter((char)42794));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42794));
    }
    @Test
    public void d2387(){
        assertEq(2,StringDataUtil.getType((char)42795));
        assertEq(0,StringDataUtil.getDirectionality((char)42795));
        assertTrue(StringDataLetterUtil.isLetter((char)42795));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42795));
    }
    @Test
    public void d2388(){
        assertEq(1,StringDataUtil.getType((char)42796));
        assertEq(0,StringDataUtil.getDirectionality((char)42796));
        assertTrue(StringDataLetterUtil.isLetter((char)42796));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42796));
    }
    @Test
    public void d2389(){
        assertEq(2,StringDataUtil.getType((char)42797));
        assertEq(0,StringDataUtil.getDirectionality((char)42797));
        assertTrue(StringDataLetterUtil.isLetter((char)42797));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42797));
    }
    @Test
    public void d2390(){
        assertEq(1,StringDataUtil.getType((char)42798));
        assertEq(0,StringDataUtil.getDirectionality((char)42798));
        assertTrue(StringDataLetterUtil.isLetter((char)42798));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42798));
    }
    @Test
    public void d2391(){
        assertEq(2,StringDataUtil.getType((char)42799));
        assertEq(0,StringDataUtil.getDirectionality((char)42799));
        assertTrue(StringDataLetterUtil.isLetter((char)42799));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42799));
    }
    @Test
    public void d2392(){
        assertEq(1,StringDataUtil.getType((char)42802));
        assertEq(0,StringDataUtil.getDirectionality((char)42802));
        assertTrue(StringDataLetterUtil.isLetter((char)42802));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42802));
    }
    @Test
    public void d2393(){
        assertEq(2,StringDataUtil.getType((char)42803));
        assertEq(0,StringDataUtil.getDirectionality((char)42803));
        assertTrue(StringDataLetterUtil.isLetter((char)42803));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42803));
    }
    @Test
    public void d2394(){
        assertEq(1,StringDataUtil.getType((char)42804));
        assertEq(0,StringDataUtil.getDirectionality((char)42804));
        assertTrue(StringDataLetterUtil.isLetter((char)42804));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42804));
    }
    @Test
    public void d2395(){
        assertEq(2,StringDataUtil.getType((char)42805));
        assertEq(0,StringDataUtil.getDirectionality((char)42805));
        assertTrue(StringDataLetterUtil.isLetter((char)42805));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42805));
    }
    @Test
    public void d2396(){
        assertEq(1,StringDataUtil.getType((char)42806));
        assertEq(0,StringDataUtil.getDirectionality((char)42806));
        assertTrue(StringDataLetterUtil.isLetter((char)42806));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42806));
    }
    @Test
    public void d2397(){
        assertEq(2,StringDataUtil.getType((char)42807));
        assertEq(0,StringDataUtil.getDirectionality((char)42807));
        assertTrue(StringDataLetterUtil.isLetter((char)42807));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42807));
    }
    @Test
    public void d2398(){
        assertEq(1,StringDataUtil.getType((char)42808));
        assertEq(0,StringDataUtil.getDirectionality((char)42808));
        assertTrue(StringDataLetterUtil.isLetter((char)42808));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42808));
    }
    @Test
    public void d2399(){
        assertEq(2,StringDataUtil.getType((char)42809));
        assertEq(0,StringDataUtil.getDirectionality((char)42809));
        assertTrue(StringDataLetterUtil.isLetter((char)42809));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42809));
    }
    @Test
    public void d2400(){
        assertEq(1,StringDataUtil.getType((char)42810));
        assertEq(0,StringDataUtil.getDirectionality((char)42810));
        assertTrue(StringDataLetterUtil.isLetter((char)42810));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42810));
    }
    @Test
    public void d2401(){
        assertEq(2,StringDataUtil.getType((char)42811));
        assertEq(0,StringDataUtil.getDirectionality((char)42811));
        assertTrue(StringDataLetterUtil.isLetter((char)42811));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42811));
    }
    @Test
    public void d2402(){
        assertEq(1,StringDataUtil.getType((char)42812));
        assertEq(0,StringDataUtil.getDirectionality((char)42812));
        assertTrue(StringDataLetterUtil.isLetter((char)42812));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42812));
    }
    @Test
    public void d2403(){
        assertEq(2,StringDataUtil.getType((char)42813));
        assertEq(0,StringDataUtil.getDirectionality((char)42813));
        assertTrue(StringDataLetterUtil.isLetter((char)42813));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42813));
    }
    @Test
    public void d2404(){
        assertEq(1,StringDataUtil.getType((char)42814));
        assertEq(0,StringDataUtil.getDirectionality((char)42814));
        assertTrue(StringDataLetterUtil.isLetter((char)42814));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42814));
    }
    @Test
    public void d2405(){
        assertEq(2,StringDataUtil.getType((char)42815));
        assertEq(0,StringDataUtil.getDirectionality((char)42815));
        assertTrue(StringDataLetterUtil.isLetter((char)42815));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42815));
    }
    @Test
    public void d2406(){
        assertEq(1,StringDataUtil.getType((char)42816));
        assertEq(0,StringDataUtil.getDirectionality((char)42816));
        assertTrue(StringDataLetterUtil.isLetter((char)42816));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42816));
    }
    @Test
    public void d2407(){
        assertEq(2,StringDataUtil.getType((char)42817));
        assertEq(0,StringDataUtil.getDirectionality((char)42817));
        assertTrue(StringDataLetterUtil.isLetter((char)42817));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42817));
    }
    @Test
    public void d2408(){
        assertEq(1,StringDataUtil.getType((char)42818));
        assertEq(0,StringDataUtil.getDirectionality((char)42818));
        assertTrue(StringDataLetterUtil.isLetter((char)42818));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42818));
    }
    @Test
    public void d2409(){
        assertEq(2,StringDataUtil.getType((char)42819));
        assertEq(0,StringDataUtil.getDirectionality((char)42819));
        assertTrue(StringDataLetterUtil.isLetter((char)42819));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42819));
    }
    @Test
    public void d2410(){
        assertEq(1,StringDataUtil.getType((char)42820));
        assertEq(0,StringDataUtil.getDirectionality((char)42820));
        assertTrue(StringDataLetterUtil.isLetter((char)42820));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42820));
    }
    @Test
    public void d2411(){
        assertEq(2,StringDataUtil.getType((char)42821));
        assertEq(0,StringDataUtil.getDirectionality((char)42821));
        assertTrue(StringDataLetterUtil.isLetter((char)42821));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42821));
    }
    @Test
    public void d2412(){
        assertEq(1,StringDataUtil.getType((char)42822));
        assertEq(0,StringDataUtil.getDirectionality((char)42822));
        assertTrue(StringDataLetterUtil.isLetter((char)42822));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42822));
    }
    @Test
    public void d2413(){
        assertEq(2,StringDataUtil.getType((char)42823));
        assertEq(0,StringDataUtil.getDirectionality((char)42823));
        assertTrue(StringDataLetterUtil.isLetter((char)42823));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42823));
    }
    @Test
    public void d2414(){
        assertEq(1,StringDataUtil.getType((char)42824));
        assertEq(0,StringDataUtil.getDirectionality((char)42824));
        assertTrue(StringDataLetterUtil.isLetter((char)42824));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42824));
    }
    @Test
    public void d2415(){
        assertEq(2,StringDataUtil.getType((char)42825));
        assertEq(0,StringDataUtil.getDirectionality((char)42825));
        assertTrue(StringDataLetterUtil.isLetter((char)42825));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42825));
    }
    @Test
    public void d2416(){
        assertEq(1,StringDataUtil.getType((char)42826));
        assertEq(0,StringDataUtil.getDirectionality((char)42826));
        assertTrue(StringDataLetterUtil.isLetter((char)42826));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42826));
    }
    @Test
    public void d2417(){
        assertEq(2,StringDataUtil.getType((char)42827));
        assertEq(0,StringDataUtil.getDirectionality((char)42827));
        assertTrue(StringDataLetterUtil.isLetter((char)42827));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42827));
    }
    @Test
    public void d2418(){
        assertEq(1,StringDataUtil.getType((char)42828));
        assertEq(0,StringDataUtil.getDirectionality((char)42828));
        assertTrue(StringDataLetterUtil.isLetter((char)42828));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42828));
    }
    @Test
    public void d2419(){
        assertEq(2,StringDataUtil.getType((char)42829));
        assertEq(0,StringDataUtil.getDirectionality((char)42829));
        assertTrue(StringDataLetterUtil.isLetter((char)42829));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42829));
    }
    @Test
    public void d2420(){
        assertEq(1,StringDataUtil.getType((char)42830));
        assertEq(0,StringDataUtil.getDirectionality((char)42830));
        assertTrue(StringDataLetterUtil.isLetter((char)42830));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42830));
    }
    @Test
    public void d2421(){
        assertEq(2,StringDataUtil.getType((char)42831));
        assertEq(0,StringDataUtil.getDirectionality((char)42831));
        assertTrue(StringDataLetterUtil.isLetter((char)42831));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42831));
    }
    @Test
    public void d2422(){
        assertEq(1,StringDataUtil.getType((char)42832));
        assertEq(0,StringDataUtil.getDirectionality((char)42832));
        assertTrue(StringDataLetterUtil.isLetter((char)42832));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42832));
    }
    @Test
    public void d2423(){
        assertEq(2,StringDataUtil.getType((char)42833));
        assertEq(0,StringDataUtil.getDirectionality((char)42833));
        assertTrue(StringDataLetterUtil.isLetter((char)42833));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42833));
    }
    @Test
    public void d2424(){
        assertEq(1,StringDataUtil.getType((char)42834));
        assertEq(0,StringDataUtil.getDirectionality((char)42834));
        assertTrue(StringDataLetterUtil.isLetter((char)42834));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42834));
    }
    @Test
    public void d2425(){
        assertEq(2,StringDataUtil.getType((char)42835));
        assertEq(0,StringDataUtil.getDirectionality((char)42835));
        assertTrue(StringDataLetterUtil.isLetter((char)42835));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42835));
    }
    @Test
    public void d2426(){
        assertEq(1,StringDataUtil.getType((char)42836));
        assertEq(0,StringDataUtil.getDirectionality((char)42836));
        assertTrue(StringDataLetterUtil.isLetter((char)42836));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42836));
    }
    @Test
    public void d2427(){
        assertEq(2,StringDataUtil.getType((char)42837));
        assertEq(0,StringDataUtil.getDirectionality((char)42837));
        assertTrue(StringDataLetterUtil.isLetter((char)42837));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42837));
    }
    @Test
    public void d2428(){
        assertEq(1,StringDataUtil.getType((char)42838));
        assertEq(0,StringDataUtil.getDirectionality((char)42838));
        assertTrue(StringDataLetterUtil.isLetter((char)42838));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42838));
    }
    @Test
    public void d2429(){
        assertEq(2,StringDataUtil.getType((char)42839));
        assertEq(0,StringDataUtil.getDirectionality((char)42839));
        assertTrue(StringDataLetterUtil.isLetter((char)42839));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42839));
    }
    @Test
    public void d2430(){
        assertEq(1,StringDataUtil.getType((char)42840));
        assertEq(0,StringDataUtil.getDirectionality((char)42840));
        assertTrue(StringDataLetterUtil.isLetter((char)42840));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42840));
    }
    @Test
    public void d2431(){
        assertEq(2,StringDataUtil.getType((char)42841));
        assertEq(0,StringDataUtil.getDirectionality((char)42841));
        assertTrue(StringDataLetterUtil.isLetter((char)42841));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42841));
    }
    @Test
    public void d2432(){
        assertEq(1,StringDataUtil.getType((char)42842));
        assertEq(0,StringDataUtil.getDirectionality((char)42842));
        assertTrue(StringDataLetterUtil.isLetter((char)42842));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42842));
    }
    @Test
    public void d2433(){
        assertEq(2,StringDataUtil.getType((char)42843));
        assertEq(0,StringDataUtil.getDirectionality((char)42843));
        assertTrue(StringDataLetterUtil.isLetter((char)42843));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42843));
    }
    @Test
    public void d2434(){
        assertEq(1,StringDataUtil.getType((char)42844));
        assertEq(0,StringDataUtil.getDirectionality((char)42844));
        assertTrue(StringDataLetterUtil.isLetter((char)42844));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42844));
    }
    @Test
    public void d2435(){
        assertEq(2,StringDataUtil.getType((char)42845));
        assertEq(0,StringDataUtil.getDirectionality((char)42845));
        assertTrue(StringDataLetterUtil.isLetter((char)42845));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42845));
    }
    @Test
    public void d2436(){
        assertEq(1,StringDataUtil.getType((char)42846));
        assertEq(0,StringDataUtil.getDirectionality((char)42846));
        assertTrue(StringDataLetterUtil.isLetter((char)42846));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42846));
    }
    @Test
    public void d2437(){
        assertEq(2,StringDataUtil.getType((char)42847));
        assertEq(0,StringDataUtil.getDirectionality((char)42847));
        assertTrue(StringDataLetterUtil.isLetter((char)42847));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42847));
    }
    @Test
    public void d2438(){
        assertEq(1,StringDataUtil.getType((char)42848));
        assertEq(0,StringDataUtil.getDirectionality((char)42848));
        assertTrue(StringDataLetterUtil.isLetter((char)42848));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42848));
    }
    @Test
    public void d2439(){
        assertEq(2,StringDataUtil.getType((char)42849));
        assertEq(0,StringDataUtil.getDirectionality((char)42849));
        assertTrue(StringDataLetterUtil.isLetter((char)42849));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42849));
    }
    @Test
    public void d2440(){
        assertEq(1,StringDataUtil.getType((char)42850));
        assertEq(0,StringDataUtil.getDirectionality((char)42850));
        assertTrue(StringDataLetterUtil.isLetter((char)42850));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42850));
    }
    @Test
    public void d2441(){
        assertEq(2,StringDataUtil.getType((char)42851));
        assertEq(0,StringDataUtil.getDirectionality((char)42851));
        assertTrue(StringDataLetterUtil.isLetter((char)42851));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42851));
    }
    @Test
    public void d2442(){
        assertEq(1,StringDataUtil.getType((char)42852));
        assertEq(0,StringDataUtil.getDirectionality((char)42852));
        assertTrue(StringDataLetterUtil.isLetter((char)42852));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42852));
    }
    @Test
    public void d2443(){
        assertEq(2,StringDataUtil.getType((char)42853));
        assertEq(0,StringDataUtil.getDirectionality((char)42853));
        assertTrue(StringDataLetterUtil.isLetter((char)42853));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42853));
    }
    @Test
    public void d2444(){
        assertEq(1,StringDataUtil.getType((char)42854));
        assertEq(0,StringDataUtil.getDirectionality((char)42854));
        assertTrue(StringDataLetterUtil.isLetter((char)42854));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42854));
    }
    @Test
    public void d2445(){
        assertEq(2,StringDataUtil.getType((char)42855));
        assertEq(0,StringDataUtil.getDirectionality((char)42855));
        assertTrue(StringDataLetterUtil.isLetter((char)42855));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42855));
    }
    @Test
    public void d2446(){
        assertEq(1,StringDataUtil.getType((char)42856));
        assertEq(0,StringDataUtil.getDirectionality((char)42856));
        assertTrue(StringDataLetterUtil.isLetter((char)42856));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42856));
    }
    @Test
    public void d2447(){
        assertEq(2,StringDataUtil.getType((char)42857));
        assertEq(0,StringDataUtil.getDirectionality((char)42857));
        assertTrue(StringDataLetterUtil.isLetter((char)42857));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42857));
    }
    @Test
    public void d2448(){
        assertEq(1,StringDataUtil.getType((char)42858));
        assertEq(0,StringDataUtil.getDirectionality((char)42858));
        assertTrue(StringDataLetterUtil.isLetter((char)42858));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42858));
    }
    @Test
    public void d2449(){
        assertEq(2,StringDataUtil.getType((char)42859));
        assertEq(0,StringDataUtil.getDirectionality((char)42859));
        assertTrue(StringDataLetterUtil.isLetter((char)42859));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42859));
    }
    @Test
    public void d2450(){
        assertEq(1,StringDataUtil.getType((char)42860));
        assertEq(0,StringDataUtil.getDirectionality((char)42860));
        assertTrue(StringDataLetterUtil.isLetter((char)42860));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42860));
    }
    @Test
    public void d2451(){
        assertEq(2,StringDataUtil.getType((char)42861));
        assertEq(0,StringDataUtil.getDirectionality((char)42861));
        assertTrue(StringDataLetterUtil.isLetter((char)42861));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42861));
    }
    @Test
    public void d2452(){
        assertEq(1,StringDataUtil.getType((char)42862));
        assertEq(0,StringDataUtil.getDirectionality((char)42862));
        assertTrue(StringDataLetterUtil.isLetter((char)42862));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42862));
    }
    @Test
    public void d2453(){
        assertEq(2,StringDataUtil.getType((char)42863));
        assertEq(0,StringDataUtil.getDirectionality((char)42863));
        assertTrue(StringDataLetterUtil.isLetter((char)42863));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42863));
    }
    @Test
    public void d2454(){
        assertEq(4,StringDataUtil.getType((char)42864));
        assertEq(0,StringDataUtil.getDirectionality((char)42864));
        assertTrue(StringDataLetterUtil.isLetter((char)42864));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42864));
    }
    @Test
    public void d2455(){
        assertEq(2,StringDataUtil.getType((char)42865));
        assertEq(0,StringDataUtil.getDirectionality((char)42865));
        assertTrue(StringDataLetterUtil.isLetter((char)42865));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42865));
    }
    @Test
    public void d2456(){
        assertEq(1,StringDataUtil.getType((char)42873));
        assertEq(0,StringDataUtil.getDirectionality((char)42873));
        assertTrue(StringDataLetterUtil.isLetter((char)42873));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42873));
    }
    @Test
    public void d2457(){
        assertEq(2,StringDataUtil.getType((char)42874));
        assertEq(0,StringDataUtil.getDirectionality((char)42874));
        assertTrue(StringDataLetterUtil.isLetter((char)42874));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42874));
    }
    @Test
    public void d2458(){
        assertEq(1,StringDataUtil.getType((char)42875));
        assertEq(0,StringDataUtil.getDirectionality((char)42875));
        assertTrue(StringDataLetterUtil.isLetter((char)42875));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42875));
    }
    @Test
    public void d2459(){
        assertEq(2,StringDataUtil.getType((char)42876));
        assertEq(0,StringDataUtil.getDirectionality((char)42876));
        assertTrue(StringDataLetterUtil.isLetter((char)42876));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42876));
    }
    @Test
    public void d2460(){
        assertEq(1,StringDataUtil.getType((char)42877));
        assertEq(0,StringDataUtil.getDirectionality((char)42877));
        assertTrue(StringDataLetterUtil.isLetter((char)42877));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42877));
    }
    @Test
    public void d2461(){
        assertEq(2,StringDataUtil.getType((char)42879));
        assertEq(0,StringDataUtil.getDirectionality((char)42879));
        assertTrue(StringDataLetterUtil.isLetter((char)42879));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42879));
    }
    @Test
    public void d2462(){
        assertEq(1,StringDataUtil.getType((char)42880));
        assertEq(0,StringDataUtil.getDirectionality((char)42880));
        assertTrue(StringDataLetterUtil.isLetter((char)42880));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42880));
    }
    @Test
    public void d2463(){
        assertEq(2,StringDataUtil.getType((char)42881));
        assertEq(0,StringDataUtil.getDirectionality((char)42881));
        assertTrue(StringDataLetterUtil.isLetter((char)42881));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42881));
    }
    @Test
    public void d2464(){
        assertEq(1,StringDataUtil.getType((char)42882));
        assertEq(0,StringDataUtil.getDirectionality((char)42882));
        assertTrue(StringDataLetterUtil.isLetter((char)42882));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42882));
    }
    @Test
    public void d2465(){
        assertEq(2,StringDataUtil.getType((char)42883));
        assertEq(0,StringDataUtil.getDirectionality((char)42883));
        assertTrue(StringDataLetterUtil.isLetter((char)42883));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42883));
    }
    @Test
    public void d2466(){
        assertEq(1,StringDataUtil.getType((char)42884));
        assertEq(0,StringDataUtil.getDirectionality((char)42884));
        assertTrue(StringDataLetterUtil.isLetter((char)42884));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42884));
    }
    @Test
    public void d2467(){
        assertEq(2,StringDataUtil.getType((char)42885));
        assertEq(0,StringDataUtil.getDirectionality((char)42885));
        assertTrue(StringDataLetterUtil.isLetter((char)42885));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42885));
    }
    @Test
    public void d2468(){
        assertEq(1,StringDataUtil.getType((char)42886));
        assertEq(0,StringDataUtil.getDirectionality((char)42886));
        assertTrue(StringDataLetterUtil.isLetter((char)42886));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42886));
    }
    @Test
    public void d2469(){
        assertEq(2,StringDataUtil.getType((char)42887));
        assertEq(0,StringDataUtil.getDirectionality((char)42887));
        assertTrue(StringDataLetterUtil.isLetter((char)42887));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42887));
    }
    @Test
    public void d2470(){
        assertEq(4,StringDataUtil.getType((char)42888));
        assertEq(13,StringDataUtil.getDirectionality((char)42888));
        assertTrue(StringDataLetterUtil.isLetter((char)42888));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42888));
    }
    @Test
    public void d2471(){
        assertEq(27,StringDataUtil.getType((char)42889));
        assertEq(0,StringDataUtil.getDirectionality((char)42889));
        assertFalse(StringDataLetterUtil.isLetter((char)42889));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42889));
    }
    @Test
    public void d2472(){
        assertEq(1,StringDataUtil.getType((char)42891));
        assertEq(0,StringDataUtil.getDirectionality((char)42891));
        assertTrue(StringDataLetterUtil.isLetter((char)42891));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42891));
    }
    @Test
    public void d2473(){
        assertEq(2,StringDataUtil.getType((char)42892));
        assertEq(0,StringDataUtil.getDirectionality((char)42892));
        assertTrue(StringDataLetterUtil.isLetter((char)42892));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42892));
    }
    @Test
    public void d2474(){
        assertEq(1,StringDataUtil.getType((char)42893));
        assertEq(0,StringDataUtil.getDirectionality((char)42893));
        assertTrue(StringDataLetterUtil.isLetter((char)42893));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42893));
    }
    @Test
    public void d2475(){
        assertEq(2,StringDataUtil.getType((char)42894));
        assertEq(0,StringDataUtil.getDirectionality((char)42894));
        assertTrue(StringDataLetterUtil.isLetter((char)42894));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42894));
    }
    @Test
    public void d2476(){
        assertEq(0,StringDataUtil.getType((char)42895));
        assertEq(-1,StringDataUtil.getDirectionality((char)42895));
        assertFalse(StringDataLetterUtil.isLetter((char)42895));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42895));
    }
    @Test
    public void d2477(){
        assertEq(1,StringDataUtil.getType((char)42896));
        assertEq(0,StringDataUtil.getDirectionality((char)42896));
        assertTrue(StringDataLetterUtil.isLetter((char)42896));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42896));
    }
    @Test
    public void d2478(){
        assertEq(2,StringDataUtil.getType((char)42897));
        assertEq(0,StringDataUtil.getDirectionality((char)42897));
        assertTrue(StringDataLetterUtil.isLetter((char)42897));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42897));
    }
    @Test
    public void d2479(){
        assertEq(1,StringDataUtil.getType((char)42898));
        assertEq(0,StringDataUtil.getDirectionality((char)42898));
        assertTrue(StringDataLetterUtil.isLetter((char)42898));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42898));
    }
    @Test
    public void d2480(){
        assertEq(2,StringDataUtil.getType((char)42899));
        assertEq(0,StringDataUtil.getDirectionality((char)42899));
        assertTrue(StringDataLetterUtil.isLetter((char)42899));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42899));
    }
    @Test
    public void d2481(){
        assertEq(0,StringDataUtil.getType((char)42900));
        assertEq(-1,StringDataUtil.getDirectionality((char)42900));
        assertFalse(StringDataLetterUtil.isLetter((char)42900));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42900));
    }
    @Test
    public void d2482(){
        assertEq(1,StringDataUtil.getType((char)42912));
        assertEq(0,StringDataUtil.getDirectionality((char)42912));
        assertTrue(StringDataLetterUtil.isLetter((char)42912));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42912));
    }
    @Test
    public void d2483(){
        assertEq(2,StringDataUtil.getType((char)42913));
        assertEq(0,StringDataUtil.getDirectionality((char)42913));
        assertTrue(StringDataLetterUtil.isLetter((char)42913));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42913));
    }
    @Test
    public void d2484(){
        assertEq(1,StringDataUtil.getType((char)42914));
        assertEq(0,StringDataUtil.getDirectionality((char)42914));
        assertTrue(StringDataLetterUtil.isLetter((char)42914));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42914));
    }
    @Test
    public void d2485(){
        assertEq(2,StringDataUtil.getType((char)42915));
        assertEq(0,StringDataUtil.getDirectionality((char)42915));
        assertTrue(StringDataLetterUtil.isLetter((char)42915));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42915));
    }
    @Test
    public void d2486(){
        assertEq(1,StringDataUtil.getType((char)42916));
        assertEq(0,StringDataUtil.getDirectionality((char)42916));
        assertTrue(StringDataLetterUtil.isLetter((char)42916));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42916));
    }
    @Test
    public void d2487(){
        assertEq(2,StringDataUtil.getType((char)42917));
        assertEq(0,StringDataUtil.getDirectionality((char)42917));
        assertTrue(StringDataLetterUtil.isLetter((char)42917));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42917));
    }
    @Test
    public void d2488(){
        assertEq(1,StringDataUtil.getType((char)42918));
        assertEq(0,StringDataUtil.getDirectionality((char)42918));
        assertTrue(StringDataLetterUtil.isLetter((char)42918));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42918));
    }
    @Test
    public void d2489(){
        assertEq(2,StringDataUtil.getType((char)42919));
        assertEq(0,StringDataUtil.getDirectionality((char)42919));
        assertTrue(StringDataLetterUtil.isLetter((char)42919));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42919));
    }
    @Test
    public void d2490(){
        assertEq(1,StringDataUtil.getType((char)42920));
        assertEq(0,StringDataUtil.getDirectionality((char)42920));
        assertTrue(StringDataLetterUtil.isLetter((char)42920));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42920));
    }
    @Test
    public void d2491(){
        assertEq(2,StringDataUtil.getType((char)42921));
        assertEq(0,StringDataUtil.getDirectionality((char)42921));
        assertTrue(StringDataLetterUtil.isLetter((char)42921));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42921));
    }
    @Test
    public void d2492(){
        assertEq(1,StringDataUtil.getType((char)42922));
        assertEq(0,StringDataUtil.getDirectionality((char)42922));
        assertTrue(StringDataLetterUtil.isLetter((char)42922));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42922));
    }
    @Test
    public void d2493(){
        assertEq(0,StringDataUtil.getType((char)42923));
        assertEq(-1,StringDataUtil.getDirectionality((char)42923));
        assertFalse(StringDataLetterUtil.isLetter((char)42923));
        assertFalse(StringDataUtil.isLetterOrDigit((char)42923));
    }
    @Test
    public void d2494(){
        assertEq(4,StringDataUtil.getType((char)43000));
        assertEq(0,StringDataUtil.getDirectionality((char)43000));
        assertTrue(StringDataLetterUtil.isLetter((char)43000));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43000));
    }
    @Test
    public void d2495(){
        assertEq(2,StringDataUtil.getType((char)43002));
        assertEq(0,StringDataUtil.getDirectionality((char)43002));
        assertTrue(StringDataLetterUtil.isLetter((char)43002));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43002));
    }
    @Test
    public void d2496(){
        assertEq(5,StringDataUtil.getType((char)43003));
        assertEq(0,StringDataUtil.getDirectionality((char)43003));
        assertTrue(StringDataLetterUtil.isLetter((char)43003));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43003));
    }
    @Test
    public void d2497(){
        assertEq(6,StringDataUtil.getType((char)43010));
        assertEq(8,StringDataUtil.getDirectionality((char)43010));
        assertFalse(StringDataLetterUtil.isLetter((char)43010));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43010));
    }
    @Test
    public void d2498(){
        assertEq(5,StringDataUtil.getType((char)43011));
        assertEq(0,StringDataUtil.getDirectionality((char)43011));
        assertTrue(StringDataLetterUtil.isLetter((char)43011));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43011));
    }
    @Test
    public void d2499(){
        assertEq(6,StringDataUtil.getType((char)43014));
        assertEq(8,StringDataUtil.getDirectionality((char)43014));
        assertFalse(StringDataLetterUtil.isLetter((char)43014));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43014));
    }
    @Test
    public void d2500(){
        assertEq(5,StringDataUtil.getType((char)43015));
        assertEq(0,StringDataUtil.getDirectionality((char)43015));
        assertTrue(StringDataLetterUtil.isLetter((char)43015));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43015));
    }
    @Test
    public void d2501(){
        assertEq(6,StringDataUtil.getType((char)43019));
        assertEq(8,StringDataUtil.getDirectionality((char)43019));
        assertFalse(StringDataLetterUtil.isLetter((char)43019));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43019));
    }
    @Test
    public void d2502(){
        assertEq(5,StringDataUtil.getType((char)43020));
        assertEq(0,StringDataUtil.getDirectionality((char)43020));
        assertTrue(StringDataLetterUtil.isLetter((char)43020));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43020));
    }
    @Test
    public void d2503(){
        assertEq(8,StringDataUtil.getType((char)43043));
        assertEq(0,StringDataUtil.getDirectionality((char)43043));
        assertFalse(StringDataLetterUtil.isLetter((char)43043));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43043));
    }
    @Test
    public void d2504(){
        assertEq(6,StringDataUtil.getType((char)43045));
        assertEq(8,StringDataUtil.getDirectionality((char)43045));
        assertFalse(StringDataLetterUtil.isLetter((char)43045));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43045));
    }
    @Test
    public void d2505(){
        assertEq(8,StringDataUtil.getType((char)43047));
        assertEq(0,StringDataUtil.getDirectionality((char)43047));
        assertFalse(StringDataLetterUtil.isLetter((char)43047));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43047));
    }
    @Test
    public void d2506(){
        assertEq(28,StringDataUtil.getType((char)43048));
        assertEq(13,StringDataUtil.getDirectionality((char)43048));
        assertFalse(StringDataLetterUtil.isLetter((char)43048));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43048));
    }
    @Test
    public void d2507(){
        assertEq(0,StringDataUtil.getType((char)43052));
        assertEq(-1,StringDataUtil.getDirectionality((char)43052));
        assertFalse(StringDataLetterUtil.isLetter((char)43052));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43052));
    }
    @Test
    public void d2508(){
        assertEq(11,StringDataUtil.getType((char)43056));
        assertEq(0,StringDataUtil.getDirectionality((char)43056));
        assertFalse(StringDataLetterUtil.isLetter((char)43056));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43056));
    }
    @Test
    public void d2509(){
        assertEq(28,StringDataUtil.getType((char)43062));
        assertEq(0,StringDataUtil.getDirectionality((char)43062));
        assertFalse(StringDataLetterUtil.isLetter((char)43062));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43062));
    }
    @Test
    public void d2510(){
        assertEq(26,StringDataUtil.getType((char)43064));
        assertEq(5,StringDataUtil.getDirectionality((char)43064));
        assertFalse(StringDataLetterUtil.isLetter((char)43064));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43064));
    }
    @Test
    public void d2511(){
        assertEq(28,StringDataUtil.getType((char)43065));
        assertEq(5,StringDataUtil.getDirectionality((char)43065));
        assertFalse(StringDataLetterUtil.isLetter((char)43065));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43065));
    }
    @Test
    public void d2512(){
        assertEq(0,StringDataUtil.getType((char)43066));
        assertEq(-1,StringDataUtil.getDirectionality((char)43066));
        assertFalse(StringDataLetterUtil.isLetter((char)43066));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43066));
    }
    @Test
    public void d2513(){
        assertEq(5,StringDataUtil.getType((char)43072));
        assertEq(0,StringDataUtil.getDirectionality((char)43072));
        assertTrue(StringDataLetterUtil.isLetter((char)43072));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43072));
    }
    @Test
    public void d2514(){
        assertEq(24,StringDataUtil.getType((char)43124));
        assertEq(13,StringDataUtil.getDirectionality((char)43124));
        assertFalse(StringDataLetterUtil.isLetter((char)43124));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43124));
    }
    @Test
    public void d2515(){
        assertEq(0,StringDataUtil.getType((char)43128));
        assertEq(-1,StringDataUtil.getDirectionality((char)43128));
        assertFalse(StringDataLetterUtil.isLetter((char)43128));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43128));
    }
    @Test
    public void d2516(){
        assertEq(8,StringDataUtil.getType((char)43136));
        assertEq(0,StringDataUtil.getDirectionality((char)43136));
        assertFalse(StringDataLetterUtil.isLetter((char)43136));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43136));
    }
    @Test
    public void d2517(){
        assertEq(5,StringDataUtil.getType((char)43138));
        assertEq(0,StringDataUtil.getDirectionality((char)43138));
        assertTrue(StringDataLetterUtil.isLetter((char)43138));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43138));
    }
    @Test
    public void d2518(){
        assertEq(8,StringDataUtil.getType((char)43188));
        assertEq(0,StringDataUtil.getDirectionality((char)43188));
        assertFalse(StringDataLetterUtil.isLetter((char)43188));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43188));
    }
    @Test
    public void d2519(){
        assertEq(6,StringDataUtil.getType((char)43204));
        assertEq(8,StringDataUtil.getDirectionality((char)43204));
        assertFalse(StringDataLetterUtil.isLetter((char)43204));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43204));
    }
    @Test
    public void d2520(){
        assertEq(0,StringDataUtil.getType((char)43205));
        assertEq(-1,StringDataUtil.getDirectionality((char)43205));
        assertFalse(StringDataLetterUtil.isLetter((char)43205));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43205));
    }
    @Test
    public void d2521(){
        assertEq(24,StringDataUtil.getType((char)43214));
        assertEq(0,StringDataUtil.getDirectionality((char)43214));
        assertFalse(StringDataLetterUtil.isLetter((char)43214));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43214));
    }
    @Test
    public void d2522(){
        assertEq(9,StringDataUtil.getType((char)43216));
        assertEq(0,StringDataUtil.getDirectionality((char)43216));
        assertFalse(StringDataLetterUtil.isLetter((char)43216));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43216));
    }
    @Test
    public void d2523(){
        assertEq(0,StringDataUtil.getType((char)43226));
        assertEq(-1,StringDataUtil.getDirectionality((char)43226));
        assertFalse(StringDataLetterUtil.isLetter((char)43226));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43226));
    }
    @Test
    public void d2524(){
        assertEq(6,StringDataUtil.getType((char)43232));
        assertEq(8,StringDataUtil.getDirectionality((char)43232));
        assertFalse(StringDataLetterUtil.isLetter((char)43232));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43232));
    }
    @Test
    public void d2525(){
        assertEq(5,StringDataUtil.getType((char)43250));
        assertEq(0,StringDataUtil.getDirectionality((char)43250));
        assertTrue(StringDataLetterUtil.isLetter((char)43250));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43250));
    }
    @Test
    public void d2526(){
        assertEq(24,StringDataUtil.getType((char)43256));
        assertEq(0,StringDataUtil.getDirectionality((char)43256));
        assertFalse(StringDataLetterUtil.isLetter((char)43256));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43256));
    }
    @Test
    public void d2527(){
        assertEq(5,StringDataUtil.getType((char)43259));
        assertEq(0,StringDataUtil.getDirectionality((char)43259));
        assertTrue(StringDataLetterUtil.isLetter((char)43259));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43259));
    }
    @Test
    public void d2528(){
        assertEq(0,StringDataUtil.getType((char)43260));
        assertEq(-1,StringDataUtil.getDirectionality((char)43260));
        assertFalse(StringDataLetterUtil.isLetter((char)43260));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43260));
    }
    @Test
    public void d2529(){
        assertEq(9,StringDataUtil.getType((char)43264));
        assertEq(0,StringDataUtil.getDirectionality((char)43264));
        assertFalse(StringDataLetterUtil.isLetter((char)43264));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43264));
    }
    @Test
    public void d2530(){
        assertEq(5,StringDataUtil.getType((char)43274));
        assertEq(0,StringDataUtil.getDirectionality((char)43274));
        assertTrue(StringDataLetterUtil.isLetter((char)43274));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43274));
    }
    @Test
    public void d2531(){
        assertEq(6,StringDataUtil.getType((char)43302));
        assertEq(8,StringDataUtil.getDirectionality((char)43302));
        assertFalse(StringDataLetterUtil.isLetter((char)43302));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43302));
    }
    @Test
    public void d2532(){
        assertEq(24,StringDataUtil.getType((char)43310));
        assertEq(0,StringDataUtil.getDirectionality((char)43310));
        assertFalse(StringDataLetterUtil.isLetter((char)43310));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43310));
    }
    @Test
    public void d2533(){
        assertEq(5,StringDataUtil.getType((char)43312));
        assertEq(0,StringDataUtil.getDirectionality((char)43312));
        assertTrue(StringDataLetterUtil.isLetter((char)43312));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43312));
    }
    @Test
    public void d2534(){
        assertEq(6,StringDataUtil.getType((char)43335));
        assertEq(8,StringDataUtil.getDirectionality((char)43335));
        assertFalse(StringDataLetterUtil.isLetter((char)43335));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43335));
    }
    @Test
    public void d2535(){
        assertEq(8,StringDataUtil.getType((char)43346));
        assertEq(0,StringDataUtil.getDirectionality((char)43346));
        assertFalse(StringDataLetterUtil.isLetter((char)43346));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43346));
    }
    @Test
    public void d2536(){
        assertEq(0,StringDataUtil.getType((char)43348));
        assertEq(-1,StringDataUtil.getDirectionality((char)43348));
        assertFalse(StringDataLetterUtil.isLetter((char)43348));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43348));
    }
    @Test
    public void d2537(){
        assertEq(24,StringDataUtil.getType((char)43359));
        assertEq(0,StringDataUtil.getDirectionality((char)43359));
        assertFalse(StringDataLetterUtil.isLetter((char)43359));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43359));
    }
    @Test
    public void d2538(){
        assertEq(5,StringDataUtil.getType((char)43360));
        assertEq(0,StringDataUtil.getDirectionality((char)43360));
        assertTrue(StringDataLetterUtil.isLetter((char)43360));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43360));
    }
    @Test
    public void d2539(){
        assertEq(0,StringDataUtil.getType((char)43389));
        assertEq(-1,StringDataUtil.getDirectionality((char)43389));
        assertFalse(StringDataLetterUtil.isLetter((char)43389));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43389));
    }
    @Test
    public void d2540(){
        assertEq(6,StringDataUtil.getType((char)43392));
        assertEq(8,StringDataUtil.getDirectionality((char)43392));
        assertFalse(StringDataLetterUtil.isLetter((char)43392));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43392));
    }
    @Test
    public void d2541(){
        assertEq(8,StringDataUtil.getType((char)43395));
        assertEq(0,StringDataUtil.getDirectionality((char)43395));
        assertFalse(StringDataLetterUtil.isLetter((char)43395));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43395));
    }
    @Test
    public void d2542(){
        assertEq(5,StringDataUtil.getType((char)43396));
        assertEq(0,StringDataUtil.getDirectionality((char)43396));
        assertTrue(StringDataLetterUtil.isLetter((char)43396));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43396));
    }
    @Test
    public void d2543(){
        assertEq(6,StringDataUtil.getType((char)43443));
        assertEq(8,StringDataUtil.getDirectionality((char)43443));
        assertFalse(StringDataLetterUtil.isLetter((char)43443));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43443));
    }
    @Test
    public void d2544(){
        assertEq(8,StringDataUtil.getType((char)43444));
        assertEq(0,StringDataUtil.getDirectionality((char)43444));
        assertFalse(StringDataLetterUtil.isLetter((char)43444));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43444));
    }
    @Test
    public void d2545(){
        assertEq(6,StringDataUtil.getType((char)43446));
        assertEq(8,StringDataUtil.getDirectionality((char)43446));
        assertFalse(StringDataLetterUtil.isLetter((char)43446));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43446));
    }
    @Test
    public void d2546(){
        assertEq(8,StringDataUtil.getType((char)43450));
        assertEq(0,StringDataUtil.getDirectionality((char)43450));
        assertFalse(StringDataLetterUtil.isLetter((char)43450));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43450));
    }
    @Test
    public void d2547(){
        assertEq(6,StringDataUtil.getType((char)43452));
        assertEq(8,StringDataUtil.getDirectionality((char)43452));
        assertFalse(StringDataLetterUtil.isLetter((char)43452));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43452));
    }
    @Test
    public void d2548(){
        assertEq(8,StringDataUtil.getType((char)43453));
        assertEq(0,StringDataUtil.getDirectionality((char)43453));
        assertFalse(StringDataLetterUtil.isLetter((char)43453));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43453));
    }
    @Test
    public void d2549(){
        assertEq(24,StringDataUtil.getType((char)43457));
        assertEq(0,StringDataUtil.getDirectionality((char)43457));
        assertFalse(StringDataLetterUtil.isLetter((char)43457));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43457));
    }
    @Test
    public void d2550(){
        assertEq(0,StringDataUtil.getType((char)43470));
        assertEq(-1,StringDataUtil.getDirectionality((char)43470));
        assertFalse(StringDataLetterUtil.isLetter((char)43470));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43470));
    }
    @Test
    public void d2551(){
        assertEq(4,StringDataUtil.getType((char)43471));
        assertEq(0,StringDataUtil.getDirectionality((char)43471));
        assertTrue(StringDataLetterUtil.isLetter((char)43471));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43471));
    }
    @Test
    public void d2552(){
        assertEq(9,StringDataUtil.getType((char)43472));
        assertEq(0,StringDataUtil.getDirectionality((char)43472));
        assertFalse(StringDataLetterUtil.isLetter((char)43472));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43472));
    }
    @Test
    public void d2553(){
        assertEq(0,StringDataUtil.getType((char)43482));
        assertEq(-1,StringDataUtil.getDirectionality((char)43482));
        assertFalse(StringDataLetterUtil.isLetter((char)43482));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43482));
    }
    @Test
    public void d2554(){
        assertEq(24,StringDataUtil.getType((char)43486));
        assertEq(0,StringDataUtil.getDirectionality((char)43486));
        assertFalse(StringDataLetterUtil.isLetter((char)43486));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43486));
    }
    @Test
    public void d2555(){
        assertEq(0,StringDataUtil.getType((char)43488));
        assertEq(-1,StringDataUtil.getDirectionality((char)43488));
        assertFalse(StringDataLetterUtil.isLetter((char)43488));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43488));
    }
    @Test
    public void d2556(){
        assertEq(5,StringDataUtil.getType((char)43520));
        assertEq(0,StringDataUtil.getDirectionality((char)43520));
        assertTrue(StringDataLetterUtil.isLetter((char)43520));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43520));
    }
    @Test
    public void d2557(){
        assertEq(6,StringDataUtil.getType((char)43561));
        assertEq(8,StringDataUtil.getDirectionality((char)43561));
        assertFalse(StringDataLetterUtil.isLetter((char)43561));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43561));
    }
    @Test
    public void d2558(){
        assertEq(8,StringDataUtil.getType((char)43567));
        assertEq(0,StringDataUtil.getDirectionality((char)43567));
        assertFalse(StringDataLetterUtil.isLetter((char)43567));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43567));
    }
    @Test
    public void d2559(){
        assertEq(6,StringDataUtil.getType((char)43569));
        assertEq(8,StringDataUtil.getDirectionality((char)43569));
        assertFalse(StringDataLetterUtil.isLetter((char)43569));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43569));
    }
    @Test
    public void d2560(){
        assertEq(8,StringDataUtil.getType((char)43571));
        assertEq(0,StringDataUtil.getDirectionality((char)43571));
        assertFalse(StringDataLetterUtil.isLetter((char)43571));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43571));
    }
    @Test
    public void d2561(){
        assertEq(6,StringDataUtil.getType((char)43573));
        assertEq(8,StringDataUtil.getDirectionality((char)43573));
        assertFalse(StringDataLetterUtil.isLetter((char)43573));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43573));
    }
    @Test
    public void d2562(){
        assertEq(0,StringDataUtil.getType((char)43575));
        assertEq(-1,StringDataUtil.getDirectionality((char)43575));
        assertFalse(StringDataLetterUtil.isLetter((char)43575));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43575));
    }
    @Test
    public void d2563(){
        assertEq(5,StringDataUtil.getType((char)43584));
        assertEq(0,StringDataUtil.getDirectionality((char)43584));
        assertTrue(StringDataLetterUtil.isLetter((char)43584));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43584));
    }
    @Test
    public void d2564(){
        assertEq(6,StringDataUtil.getType((char)43587));
        assertEq(8,StringDataUtil.getDirectionality((char)43587));
        assertFalse(StringDataLetterUtil.isLetter((char)43587));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43587));
    }
    @Test
    public void d2565(){
        assertEq(5,StringDataUtil.getType((char)43588));
        assertEq(0,StringDataUtil.getDirectionality((char)43588));
        assertTrue(StringDataLetterUtil.isLetter((char)43588));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43588));
    }
    @Test
    public void d2566(){
        assertEq(6,StringDataUtil.getType((char)43596));
        assertEq(8,StringDataUtil.getDirectionality((char)43596));
        assertFalse(StringDataLetterUtil.isLetter((char)43596));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43596));
    }
    @Test
    public void d2567(){
        assertEq(8,StringDataUtil.getType((char)43597));
        assertEq(0,StringDataUtil.getDirectionality((char)43597));
        assertFalse(StringDataLetterUtil.isLetter((char)43597));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43597));
    }
    @Test
    public void d2568(){
        assertEq(0,StringDataUtil.getType((char)43598));
        assertEq(-1,StringDataUtil.getDirectionality((char)43598));
        assertFalse(StringDataLetterUtil.isLetter((char)43598));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43598));
    }
    @Test
    public void d2569(){
        assertEq(9,StringDataUtil.getType((char)43600));
        assertEq(0,StringDataUtil.getDirectionality((char)43600));
        assertFalse(StringDataLetterUtil.isLetter((char)43600));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43600));
    }
    @Test
    public void d2570(){
        assertEq(0,StringDataUtil.getType((char)43610));
        assertEq(-1,StringDataUtil.getDirectionality((char)43610));
        assertFalse(StringDataLetterUtil.isLetter((char)43610));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43610));
    }
    @Test
    public void d2571(){
        assertEq(24,StringDataUtil.getType((char)43612));
        assertEq(0,StringDataUtil.getDirectionality((char)43612));
        assertFalse(StringDataLetterUtil.isLetter((char)43612));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43612));
    }
    @Test
    public void d2572(){
        assertEq(5,StringDataUtil.getType((char)43616));
        assertEq(0,StringDataUtil.getDirectionality((char)43616));
        assertTrue(StringDataLetterUtil.isLetter((char)43616));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43616));
    }
    @Test
    public void d2573(){
        assertEq(4,StringDataUtil.getType((char)43632));
        assertEq(0,StringDataUtil.getDirectionality((char)43632));
        assertTrue(StringDataLetterUtil.isLetter((char)43632));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43632));
    }
    @Test
    public void d2574(){
        assertEq(5,StringDataUtil.getType((char)43633));
        assertEq(0,StringDataUtil.getDirectionality((char)43633));
        assertTrue(StringDataLetterUtil.isLetter((char)43633));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43633));
    }
    @Test
    public void d2575(){
        assertEq(28,StringDataUtil.getType((char)43639));
        assertEq(0,StringDataUtil.getDirectionality((char)43639));
        assertFalse(StringDataLetterUtil.isLetter((char)43639));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43639));
    }
    @Test
    public void d2576(){
        assertEq(5,StringDataUtil.getType((char)43642));
        assertEq(0,StringDataUtil.getDirectionality((char)43642));
        assertTrue(StringDataLetterUtil.isLetter((char)43642));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43642));
    }
    @Test
    public void d2577(){
        assertEq(8,StringDataUtil.getType((char)43643));
        assertEq(0,StringDataUtil.getDirectionality((char)43643));
        assertFalse(StringDataLetterUtil.isLetter((char)43643));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43643));
    }
    @Test
    public void d2578(){
        assertEq(0,StringDataUtil.getType((char)43644));
        assertEq(-1,StringDataUtil.getDirectionality((char)43644));
        assertFalse(StringDataLetterUtil.isLetter((char)43644));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43644));
    }
    @Test
    public void d2579(){
        assertEq(5,StringDataUtil.getType((char)43648));
        assertEq(0,StringDataUtil.getDirectionality((char)43648));
        assertTrue(StringDataLetterUtil.isLetter((char)43648));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43648));
    }
    @Test
    public void d2580(){
        assertEq(6,StringDataUtil.getType((char)43696));
        assertEq(8,StringDataUtil.getDirectionality((char)43696));
        assertFalse(StringDataLetterUtil.isLetter((char)43696));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43696));
    }
    @Test
    public void d2581(){
        assertEq(5,StringDataUtil.getType((char)43697));
        assertEq(0,StringDataUtil.getDirectionality((char)43697));
        assertTrue(StringDataLetterUtil.isLetter((char)43697));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43697));
    }
    @Test
    public void d2582(){
        assertEq(6,StringDataUtil.getType((char)43698));
        assertEq(8,StringDataUtil.getDirectionality((char)43698));
        assertFalse(StringDataLetterUtil.isLetter((char)43698));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43698));
    }
    @Test
    public void d2583(){
        assertEq(5,StringDataUtil.getType((char)43701));
        assertEq(0,StringDataUtil.getDirectionality((char)43701));
        assertTrue(StringDataLetterUtil.isLetter((char)43701));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43701));
    }
    @Test
    public void d2584(){
        assertEq(6,StringDataUtil.getType((char)43703));
        assertEq(8,StringDataUtil.getDirectionality((char)43703));
        assertFalse(StringDataLetterUtil.isLetter((char)43703));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43703));
    }
    @Test
    public void d2585(){
        assertEq(5,StringDataUtil.getType((char)43705));
        assertEq(0,StringDataUtil.getDirectionality((char)43705));
        assertTrue(StringDataLetterUtil.isLetter((char)43705));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43705));
    }
    @Test
    public void d2586(){
        assertEq(6,StringDataUtil.getType((char)43710));
        assertEq(8,StringDataUtil.getDirectionality((char)43710));
        assertFalse(StringDataLetterUtil.isLetter((char)43710));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43710));
    }
    @Test
    public void d2587(){
        assertEq(5,StringDataUtil.getType((char)43712));
        assertEq(0,StringDataUtil.getDirectionality((char)43712));
        assertTrue(StringDataLetterUtil.isLetter((char)43712));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43712));
    }
    @Test
    public void d2588(){
        assertEq(6,StringDataUtil.getType((char)43713));
        assertEq(8,StringDataUtil.getDirectionality((char)43713));
        assertFalse(StringDataLetterUtil.isLetter((char)43713));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43713));
    }
    @Test
    public void d2589(){
        assertEq(5,StringDataUtil.getType((char)43714));
        assertEq(0,StringDataUtil.getDirectionality((char)43714));
        assertTrue(StringDataLetterUtil.isLetter((char)43714));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43714));
    }
    @Test
    public void d2590(){
        assertEq(0,StringDataUtil.getType((char)43715));
        assertEq(-1,StringDataUtil.getDirectionality((char)43715));
        assertFalse(StringDataLetterUtil.isLetter((char)43715));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43715));
    }
    @Test
    public void d2591(){
        assertEq(5,StringDataUtil.getType((char)43739));
        assertEq(0,StringDataUtil.getDirectionality((char)43739));
        assertTrue(StringDataLetterUtil.isLetter((char)43739));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43739));
    }
    @Test
    public void d2592(){
        assertEq(4,StringDataUtil.getType((char)43741));
        assertEq(0,StringDataUtil.getDirectionality((char)43741));
        assertTrue(StringDataLetterUtil.isLetter((char)43741));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43741));
    }
    @Test
    public void d2593(){
        assertEq(24,StringDataUtil.getType((char)43742));
        assertEq(0,StringDataUtil.getDirectionality((char)43742));
        assertFalse(StringDataLetterUtil.isLetter((char)43742));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43742));
    }
    @Test
    public void d2594(){
        assertEq(5,StringDataUtil.getType((char)43744));
        assertEq(0,StringDataUtil.getDirectionality((char)43744));
        assertTrue(StringDataLetterUtil.isLetter((char)43744));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43744));
    }
    @Test
    public void d2595(){
        assertEq(8,StringDataUtil.getType((char)43755));
        assertEq(0,StringDataUtil.getDirectionality((char)43755));
        assertFalse(StringDataLetterUtil.isLetter((char)43755));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43755));
    }
    @Test
    public void d2596(){
        assertEq(6,StringDataUtil.getType((char)43756));
        assertEq(8,StringDataUtil.getDirectionality((char)43756));
        assertFalse(StringDataLetterUtil.isLetter((char)43756));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43756));
    }
    @Test
    public void d2597(){
        assertEq(8,StringDataUtil.getType((char)43758));
        assertEq(0,StringDataUtil.getDirectionality((char)43758));
        assertFalse(StringDataLetterUtil.isLetter((char)43758));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43758));
    }
    @Test
    public void d2598(){
        assertEq(24,StringDataUtil.getType((char)43760));
        assertEq(0,StringDataUtil.getDirectionality((char)43760));
        assertFalse(StringDataLetterUtil.isLetter((char)43760));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43760));
    }
    @Test
    public void d2599(){
        assertEq(5,StringDataUtil.getType((char)43762));
        assertEq(0,StringDataUtil.getDirectionality((char)43762));
        assertTrue(StringDataLetterUtil.isLetter((char)43762));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43762));
    }
    @Test
    public void d2600(){
        assertEq(4,StringDataUtil.getType((char)43763));
        assertEq(0,StringDataUtil.getDirectionality((char)43763));
        assertTrue(StringDataLetterUtil.isLetter((char)43763));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43763));
    }
    @Test
    public void d2601(){
        assertEq(8,StringDataUtil.getType((char)43765));
        assertEq(0,StringDataUtil.getDirectionality((char)43765));
        assertFalse(StringDataLetterUtil.isLetter((char)43765));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43765));
    }
    @Test
    public void d2602(){
        assertEq(6,StringDataUtil.getType((char)43766));
        assertEq(8,StringDataUtil.getDirectionality((char)43766));
        assertFalse(StringDataLetterUtil.isLetter((char)43766));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43766));
    }
    @Test
    public void d2603(){
        assertEq(0,StringDataUtil.getType((char)43767));
        assertEq(-1,StringDataUtil.getDirectionality((char)43767));
        assertFalse(StringDataLetterUtil.isLetter((char)43767));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43767));
    }
    @Test
    public void d2604(){
        assertEq(5,StringDataUtil.getType((char)43777));
        assertEq(0,StringDataUtil.getDirectionality((char)43777));
        assertTrue(StringDataLetterUtil.isLetter((char)43777));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43777));
    }
    @Test
    public void d2605(){
        assertEq(0,StringDataUtil.getType((char)43783));
        assertEq(-1,StringDataUtil.getDirectionality((char)43783));
        assertFalse(StringDataLetterUtil.isLetter((char)43783));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43783));
    }
    @Test
    public void d2606(){
        assertEq(5,StringDataUtil.getType((char)43785));
        assertEq(0,StringDataUtil.getDirectionality((char)43785));
        assertTrue(StringDataLetterUtil.isLetter((char)43785));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43785));
    }
    @Test
    public void d2607(){
        assertEq(0,StringDataUtil.getType((char)43791));
        assertEq(-1,StringDataUtil.getDirectionality((char)43791));
        assertFalse(StringDataLetterUtil.isLetter((char)43791));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43791));
    }
    @Test
    public void d2608(){
        assertEq(5,StringDataUtil.getType((char)43793));
        assertEq(0,StringDataUtil.getDirectionality((char)43793));
        assertTrue(StringDataLetterUtil.isLetter((char)43793));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43793));
    }
    @Test
    public void d2609(){
        assertEq(0,StringDataUtil.getType((char)43799));
        assertEq(-1,StringDataUtil.getDirectionality((char)43799));
        assertFalse(StringDataLetterUtil.isLetter((char)43799));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43799));
    }
    @Test
    public void d2610(){
        assertEq(5,StringDataUtil.getType((char)43808));
        assertEq(0,StringDataUtil.getDirectionality((char)43808));
        assertTrue(StringDataLetterUtil.isLetter((char)43808));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43808));
    }
    @Test
    public void d2611(){
        assertEq(0,StringDataUtil.getType((char)43815));
        assertEq(-1,StringDataUtil.getDirectionality((char)43815));
        assertFalse(StringDataLetterUtil.isLetter((char)43815));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43815));
    }
    @Test
    public void d2612(){
        assertEq(5,StringDataUtil.getType((char)43816));
        assertEq(0,StringDataUtil.getDirectionality((char)43816));
        assertTrue(StringDataLetterUtil.isLetter((char)43816));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43816));
    }
    @Test
    public void d2613(){
        assertEq(0,StringDataUtil.getType((char)43823));
        assertEq(-1,StringDataUtil.getDirectionality((char)43823));
        assertFalse(StringDataLetterUtil.isLetter((char)43823));
        assertFalse(StringDataUtil.isLetterOrDigit((char)43823));
    }
    @Test
    public void d2614(){
        assertEq(5,StringDataUtil.getType((char)43968));
        assertEq(0,StringDataUtil.getDirectionality((char)43968));
        assertTrue(StringDataLetterUtil.isLetter((char)43968));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43968));
    }
    @Test
    public void d2615(){
        assertEq(8,StringDataUtil.getType((char)44003));
        assertEq(0,StringDataUtil.getDirectionality((char)44003));
        assertFalse(StringDataLetterUtil.isLetter((char)44003));
        assertFalse(StringDataUtil.isLetterOrDigit((char)44003));
    }
    @Test
    public void d2616(){
        assertEq(6,StringDataUtil.getType((char)44005));
        assertEq(8,StringDataUtil.getDirectionality((char)44005));
        assertFalse(StringDataLetterUtil.isLetter((char)44005));
        assertFalse(StringDataUtil.isLetterOrDigit((char)44005));
    }
    @Test
    public void d2617(){
        assertEq(8,StringDataUtil.getType((char)44006));
        assertEq(0,StringDataUtil.getDirectionality((char)44006));
        assertFalse(StringDataLetterUtil.isLetter((char)44006));
        assertFalse(StringDataUtil.isLetterOrDigit((char)44006));
    }
    @Test
    public void d2618(){
        assertEq(6,StringDataUtil.getType((char)44008));
        assertEq(8,StringDataUtil.getDirectionality((char)44008));
        assertFalse(StringDataLetterUtil.isLetter((char)44008));
        assertFalse(StringDataUtil.isLetterOrDigit((char)44008));
    }
    @Test
    public void d2619(){
        assertEq(8,StringDataUtil.getType((char)44009));
        assertEq(0,StringDataUtil.getDirectionality((char)44009));
        assertFalse(StringDataLetterUtil.isLetter((char)44009));
        assertFalse(StringDataUtil.isLetterOrDigit((char)44009));
    }
    @Test
    public void d2620(){
        assertEq(24,StringDataUtil.getType((char)44011));
        assertEq(0,StringDataUtil.getDirectionality((char)44011));
        assertFalse(StringDataLetterUtil.isLetter((char)44011));
        assertFalse(StringDataUtil.isLetterOrDigit((char)44011));
    }
    @Test
    public void d2621(){
        assertEq(8,StringDataUtil.getType((char)44012));
        assertEq(0,StringDataUtil.getDirectionality((char)44012));
        assertFalse(StringDataLetterUtil.isLetter((char)44012));
        assertFalse(StringDataUtil.isLetterOrDigit((char)44012));
    }
    @Test
    public void d2622(){
        assertEq(6,StringDataUtil.getType((char)44013));
        assertEq(8,StringDataUtil.getDirectionality((char)44013));
        assertFalse(StringDataLetterUtil.isLetter((char)44013));
        assertFalse(StringDataUtil.isLetterOrDigit((char)44013));
    }
    @Test
    public void d2623(){
        assertEq(0,StringDataUtil.getType((char)44014));
        assertEq(-1,StringDataUtil.getDirectionality((char)44014));
        assertFalse(StringDataLetterUtil.isLetter((char)44014));
        assertFalse(StringDataUtil.isLetterOrDigit((char)44014));
    }
    @Test
    public void d2624(){
        assertEq(9,StringDataUtil.getType((char)44016));
        assertEq(0,StringDataUtil.getDirectionality((char)44016));
        assertFalse(StringDataLetterUtil.isLetter((char)44016));
        assertTrue(StringDataUtil.isLetterOrDigit((char)44016));
    }
    @Test
    public void d2625(){
        assertEq(0,StringDataUtil.getType((char)44026));
        assertEq(-1,StringDataUtil.getDirectionality((char)44026));
        assertFalse(StringDataLetterUtil.isLetter((char)44026));
        assertFalse(StringDataUtil.isLetterOrDigit((char)44026));
    }
    @Test
    public void d2626(){
        assertEq(5,StringDataUtil.getType((char)44032));
        assertEq(0,StringDataUtil.getDirectionality((char)44032));
        assertTrue(StringDataLetterUtil.isLetter((char)44032));
        assertTrue(StringDataUtil.isLetterOrDigit((char)44032));
    }
    @Test
    public void d2627(){
        assertEq(0,StringDataUtil.getType((char)55204));
        assertEq(-1,StringDataUtil.getDirectionality((char)55204));
        assertFalse(StringDataLetterUtil.isLetter((char)55204));
        assertFalse(StringDataUtil.isLetterOrDigit((char)55204));
    }
    @Test
    public void d2628(){
        assertEq(5,StringDataUtil.getType((char)55216));
        assertEq(0,StringDataUtil.getDirectionality((char)55216));
        assertTrue(StringDataLetterUtil.isLetter((char)55216));
        assertTrue(StringDataUtil.isLetterOrDigit((char)55216));
    }
    @Test
    public void d2629(){
        assertEq(0,StringDataUtil.getType((char)55239));
        assertEq(-1,StringDataUtil.getDirectionality((char)55239));
        assertFalse(StringDataLetterUtil.isLetter((char)55239));
        assertFalse(StringDataUtil.isLetterOrDigit((char)55239));
    }
    @Test
    public void d2630(){
        assertEq(5,StringDataUtil.getType((char)55243));
        assertEq(0,StringDataUtil.getDirectionality((char)55243));
        assertTrue(StringDataLetterUtil.isLetter((char)55243));
        assertTrue(StringDataUtil.isLetterOrDigit((char)55243));
    }
    @Test
    public void d2631(){
        assertEq(0,StringDataUtil.getType((char)55292));
        assertEq(-1,StringDataUtil.getDirectionality((char)55292));
        assertFalse(StringDataLetterUtil.isLetter((char)55292));
        assertFalse(StringDataUtil.isLetterOrDigit((char)55292));
    }
    @Test
    public void d2632(){
        assertEq(19,StringDataUtil.getType((char)55296));
        assertEq(0,StringDataUtil.getDirectionality((char)55296));
        assertFalse(StringDataLetterUtil.isLetter((char)55296));
        assertFalse(StringDataUtil.isLetterOrDigit((char)55296));
    }
    @Test
    public void d2633(){
        assertEq(18,StringDataUtil.getType((char)57344));
        assertEq(0,StringDataUtil.getDirectionality((char)57344));
        assertFalse(StringDataLetterUtil.isLetter((char)57344));
        assertFalse(StringDataUtil.isLetterOrDigit((char)57344));
    }
    @Test
    public void d2634(){
        assertEq(5,StringDataUtil.getType((char)63744));
        assertEq(0,StringDataUtil.getDirectionality((char)63744));
        assertTrue(StringDataLetterUtil.isLetter((char)63744));
        assertTrue(StringDataUtil.isLetterOrDigit((char)63744));
    }
    @Test
    public void d2635(){
        assertEq(0,StringDataUtil.getType((char)64110));
        assertEq(-1,StringDataUtil.getDirectionality((char)64110));
        assertFalse(StringDataLetterUtil.isLetter((char)64110));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64110));
    }
    @Test
    public void d2636(){
        assertEq(5,StringDataUtil.getType((char)64112));
        assertEq(0,StringDataUtil.getDirectionality((char)64112));
        assertTrue(StringDataLetterUtil.isLetter((char)64112));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64112));
    }
    @Test
    public void d2637(){
        assertEq(0,StringDataUtil.getType((char)64218));
        assertEq(-1,StringDataUtil.getDirectionality((char)64218));
        assertFalse(StringDataLetterUtil.isLetter((char)64218));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64218));
    }
    @Test
    public void d2638(){
        assertEq(2,StringDataUtil.getType((char)64256));
        assertEq(0,StringDataUtil.getDirectionality((char)64256));
        assertTrue(StringDataLetterUtil.isLetter((char)64256));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64256));
    }
    @Test
    public void d2639(){
        assertEq(0,StringDataUtil.getType((char)64263));
        assertEq(-1,StringDataUtil.getDirectionality((char)64263));
        assertFalse(StringDataLetterUtil.isLetter((char)64263));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64263));
    }
    @Test
    public void d2640(){
        assertEq(2,StringDataUtil.getType((char)64275));
        assertEq(0,StringDataUtil.getDirectionality((char)64275));
        assertTrue(StringDataLetterUtil.isLetter((char)64275));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64275));
    }
    @Test
    public void d2641(){
        assertEq(0,StringDataUtil.getType((char)64280));
        assertEq(-1,StringDataUtil.getDirectionality((char)64280));
        assertFalse(StringDataLetterUtil.isLetter((char)64280));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64280));
    }
    @Test
    public void d2642(){
        assertEq(5,StringDataUtil.getType((char)64285));
        assertEq(1,StringDataUtil.getDirectionality((char)64285));
        assertTrue(StringDataLetterUtil.isLetter((char)64285));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64285));
    }
    @Test
    public void d2643(){
        assertEq(6,StringDataUtil.getType((char)64286));
        assertEq(8,StringDataUtil.getDirectionality((char)64286));
        assertFalse(StringDataLetterUtil.isLetter((char)64286));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64286));
    }
    @Test
    public void d2644(){
        assertEq(5,StringDataUtil.getType((char)64287));
        assertEq(1,StringDataUtil.getDirectionality((char)64287));
        assertTrue(StringDataLetterUtil.isLetter((char)64287));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64287));
    }
    @Test
    public void d2645(){
        assertEq(25,StringDataUtil.getType((char)64297));
        assertEq(4,StringDataUtil.getDirectionality((char)64297));
        assertFalse(StringDataLetterUtil.isLetter((char)64297));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64297));
    }
    @Test
    public void d2646(){
        assertEq(5,StringDataUtil.getType((char)64298));
        assertEq(1,StringDataUtil.getDirectionality((char)64298));
        assertTrue(StringDataLetterUtil.isLetter((char)64298));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64298));
    }
    @Test
    public void d2647(){
        assertEq(0,StringDataUtil.getType((char)64311));
        assertEq(-1,StringDataUtil.getDirectionality((char)64311));
        assertFalse(StringDataLetterUtil.isLetter((char)64311));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64311));
    }
    @Test
    public void d2648(){
        assertEq(5,StringDataUtil.getType((char)64312));
        assertEq(1,StringDataUtil.getDirectionality((char)64312));
        assertTrue(StringDataLetterUtil.isLetter((char)64312));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64312));
    }
    @Test
    public void d2649(){
        assertEq(0,StringDataUtil.getType((char)64317));
        assertEq(-1,StringDataUtil.getDirectionality((char)64317));
        assertFalse(StringDataLetterUtil.isLetter((char)64317));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64317));
    }
    @Test
    public void d2650(){
        assertEq(5,StringDataUtil.getType((char)64318));
        assertEq(1,StringDataUtil.getDirectionality((char)64318));
        assertTrue(StringDataLetterUtil.isLetter((char)64318));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64318));
    }
    @Test
    public void d2651(){
        assertEq(0,StringDataUtil.getType((char)64319));
        assertEq(-1,StringDataUtil.getDirectionality((char)64319));
        assertFalse(StringDataLetterUtil.isLetter((char)64319));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64319));
    }
    @Test
    public void d2652(){
        assertEq(5,StringDataUtil.getType((char)64320));
        assertEq(1,StringDataUtil.getDirectionality((char)64320));
        assertTrue(StringDataLetterUtil.isLetter((char)64320));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64320));
    }
    @Test
    public void d2653(){
        assertEq(0,StringDataUtil.getType((char)64322));
        assertEq(-1,StringDataUtil.getDirectionality((char)64322));
        assertFalse(StringDataLetterUtil.isLetter((char)64322));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64322));
    }
    @Test
    public void d2654(){
        assertEq(5,StringDataUtil.getType((char)64323));
        assertEq(1,StringDataUtil.getDirectionality((char)64323));
        assertTrue(StringDataLetterUtil.isLetter((char)64323));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64323));
    }
    @Test
    public void d2655(){
        assertEq(0,StringDataUtil.getType((char)64325));
        assertEq(-1,StringDataUtil.getDirectionality((char)64325));
        assertFalse(StringDataLetterUtil.isLetter((char)64325));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64325));
    }
    @Test
    public void d2656(){
        assertEq(5,StringDataUtil.getType((char)64326));
        assertEq(1,StringDataUtil.getDirectionality((char)64326));
        assertTrue(StringDataLetterUtil.isLetter((char)64326));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64326));
    }
    @Test
    public void d2657(){
        assertEq(27,StringDataUtil.getType((char)64434));
        assertEq(2,StringDataUtil.getDirectionality((char)64434));
        assertFalse(StringDataLetterUtil.isLetter((char)64434));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64434));
    }
    @Test
    public void d2658(){
        assertEq(0,StringDataUtil.getType((char)64450));
        assertEq(-1,StringDataUtil.getDirectionality((char)64450));
        assertFalse(StringDataLetterUtil.isLetter((char)64450));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64450));
    }
    @Test
    public void d2659(){
        assertEq(5,StringDataUtil.getType((char)64467));
        assertEq(2,StringDataUtil.getDirectionality((char)64467));
        assertTrue(StringDataLetterUtil.isLetter((char)64467));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64467));
    }
    @Test
    public void d2660(){
        assertEq(21,StringDataUtil.getType((char)64830));
        assertEq(13,StringDataUtil.getDirectionality((char)64830));
        assertFalse(StringDataLetterUtil.isLetter((char)64830));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64830));
    }
    @Test
    public void d2661(){
        assertEq(22,StringDataUtil.getType((char)64831));
        assertEq(13,StringDataUtil.getDirectionality((char)64831));
        assertFalse(StringDataLetterUtil.isLetter((char)64831));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64831));
    }
    @Test
    public void d2662(){
        assertEq(0,StringDataUtil.getType((char)64832));
        assertEq(-1,StringDataUtil.getDirectionality((char)64832));
        assertFalse(StringDataLetterUtil.isLetter((char)64832));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64832));
    }
    @Test
    public void d2663(){
        assertEq(5,StringDataUtil.getType((char)64848));
        assertEq(2,StringDataUtil.getDirectionality((char)64848));
        assertTrue(StringDataLetterUtil.isLetter((char)64848));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64848));
    }
    @Test
    public void d2664(){
        assertEq(0,StringDataUtil.getType((char)64912));
        assertEq(-1,StringDataUtil.getDirectionality((char)64912));
        assertFalse(StringDataLetterUtil.isLetter((char)64912));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64912));
    }
    @Test
    public void d2665(){
        assertEq(5,StringDataUtil.getType((char)64914));
        assertEq(2,StringDataUtil.getDirectionality((char)64914));
        assertTrue(StringDataLetterUtil.isLetter((char)64914));
        assertTrue(StringDataUtil.isLetterOrDigit((char)64914));
    }
    @Test
    public void d2666(){
        assertEq(0,StringDataUtil.getType((char)64968));
        assertEq(-1,StringDataUtil.getDirectionality((char)64968));
        assertFalse(StringDataLetterUtil.isLetter((char)64968));
        assertFalse(StringDataUtil.isLetterOrDigit((char)64968));
    }
    @Test
    public void d2667(){
        assertEq(5,StringDataUtil.getType((char)65008));
        assertEq(2,StringDataUtil.getDirectionality((char)65008));
        assertTrue(StringDataLetterUtil.isLetter((char)65008));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65008));
    }
    @Test
    public void d2668(){
        assertEq(26,StringDataUtil.getType((char)65020));
        assertEq(2,StringDataUtil.getDirectionality((char)65020));
        assertFalse(StringDataLetterUtil.isLetter((char)65020));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65020));
    }
    @Test
    public void d2669(){
        assertEq(28,StringDataUtil.getType((char)65021));
        assertEq(13,StringDataUtil.getDirectionality((char)65021));
        assertFalse(StringDataLetterUtil.isLetter((char)65021));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65021));
    }
    @Test
    public void d2670(){
        assertEq(0,StringDataUtil.getType((char)65022));
        assertEq(-1,StringDataUtil.getDirectionality((char)65022));
        assertFalse(StringDataLetterUtil.isLetter((char)65022));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65022));
    }
    @Test
    public void d2671(){
        assertEq(6,StringDataUtil.getType((char)65024));
        assertEq(8,StringDataUtil.getDirectionality((char)65024));
        assertFalse(StringDataLetterUtil.isLetter((char)65024));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65024));
    }
    @Test
    public void d2672(){
        assertEq(24,StringDataUtil.getType((char)65040));
        assertEq(13,StringDataUtil.getDirectionality((char)65040));
        assertFalse(StringDataLetterUtil.isLetter((char)65040));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65040));
    }
    @Test
    public void d2673(){
        assertEq(21,StringDataUtil.getType((char)65047));
        assertEq(13,StringDataUtil.getDirectionality((char)65047));
        assertFalse(StringDataLetterUtil.isLetter((char)65047));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65047));
    }
    @Test
    public void d2674(){
        assertEq(22,StringDataUtil.getType((char)65048));
        assertEq(13,StringDataUtil.getDirectionality((char)65048));
        assertFalse(StringDataLetterUtil.isLetter((char)65048));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65048));
    }
    @Test
    public void d2675(){
        assertEq(24,StringDataUtil.getType((char)65049));
        assertEq(13,StringDataUtil.getDirectionality((char)65049));
        assertFalse(StringDataLetterUtil.isLetter((char)65049));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65049));
    }
    @Test
    public void d2676(){
        assertEq(0,StringDataUtil.getType((char)65050));
        assertEq(-1,StringDataUtil.getDirectionality((char)65050));
        assertFalse(StringDataLetterUtil.isLetter((char)65050));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65050));
    }
    @Test
    public void d2677(){
        assertEq(6,StringDataUtil.getType((char)65056));
        assertEq(8,StringDataUtil.getDirectionality((char)65056));
        assertFalse(StringDataLetterUtil.isLetter((char)65056));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65056));
    }
    @Test
    public void d2678(){
        assertEq(0,StringDataUtil.getType((char)65063));
        assertEq(-1,StringDataUtil.getDirectionality((char)65063));
        assertFalse(StringDataLetterUtil.isLetter((char)65063));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65063));
    }
    @Test
    public void d2679(){
        assertEq(24,StringDataUtil.getType((char)65072));
        assertEq(13,StringDataUtil.getDirectionality((char)65072));
        assertFalse(StringDataLetterUtil.isLetter((char)65072));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65072));
    }
    @Test
    public void d2680(){
        assertEq(20,StringDataUtil.getType((char)65073));
        assertEq(13,StringDataUtil.getDirectionality((char)65073));
        assertFalse(StringDataLetterUtil.isLetter((char)65073));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65073));
    }
    @Test
    public void d2681(){
        assertEq(23,StringDataUtil.getType((char)65075));
        assertEq(13,StringDataUtil.getDirectionality((char)65075));
        assertFalse(StringDataLetterUtil.isLetter((char)65075));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65075));
    }
    @Test
    public void d2682(){
        assertEq(21,StringDataUtil.getType((char)65077));
        assertEq(13,StringDataUtil.getDirectionality((char)65077));
        assertFalse(StringDataLetterUtil.isLetter((char)65077));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65077));
    }
    @Test
    public void d2683(){
        assertEq(22,StringDataUtil.getType((char)65078));
        assertEq(13,StringDataUtil.getDirectionality((char)65078));
        assertFalse(StringDataLetterUtil.isLetter((char)65078));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65078));
    }
    @Test
    public void d2684(){
        assertEq(21,StringDataUtil.getType((char)65079));
        assertEq(13,StringDataUtil.getDirectionality((char)65079));
        assertFalse(StringDataLetterUtil.isLetter((char)65079));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65079));
    }
    @Test
    public void d2685(){
        assertEq(22,StringDataUtil.getType((char)65080));
        assertEq(13,StringDataUtil.getDirectionality((char)65080));
        assertFalse(StringDataLetterUtil.isLetter((char)65080));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65080));
    }
    @Test
    public void d2686(){
        assertEq(21,StringDataUtil.getType((char)65081));
        assertEq(13,StringDataUtil.getDirectionality((char)65081));
        assertFalse(StringDataLetterUtil.isLetter((char)65081));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65081));
    }
    @Test
    public void d2687(){
        assertEq(22,StringDataUtil.getType((char)65082));
        assertEq(13,StringDataUtil.getDirectionality((char)65082));
        assertFalse(StringDataLetterUtil.isLetter((char)65082));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65082));
    }
    @Test
    public void d2688(){
        assertEq(21,StringDataUtil.getType((char)65083));
        assertEq(13,StringDataUtil.getDirectionality((char)65083));
        assertFalse(StringDataLetterUtil.isLetter((char)65083));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65083));
    }
    @Test
    public void d2689(){
        assertEq(22,StringDataUtil.getType((char)65084));
        assertEq(13,StringDataUtil.getDirectionality((char)65084));
        assertFalse(StringDataLetterUtil.isLetter((char)65084));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65084));
    }
    @Test
    public void d2690(){
        assertEq(21,StringDataUtil.getType((char)65085));
        assertEq(13,StringDataUtil.getDirectionality((char)65085));
        assertFalse(StringDataLetterUtil.isLetter((char)65085));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65085));
    }
    @Test
    public void d2691(){
        assertEq(22,StringDataUtil.getType((char)65086));
        assertEq(13,StringDataUtil.getDirectionality((char)65086));
        assertFalse(StringDataLetterUtil.isLetter((char)65086));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65086));
    }
    @Test
    public void d2692(){
        assertEq(21,StringDataUtil.getType((char)65087));
        assertEq(13,StringDataUtil.getDirectionality((char)65087));
        assertFalse(StringDataLetterUtil.isLetter((char)65087));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65087));
    }
    @Test
    public void d2693(){
        assertEq(22,StringDataUtil.getType((char)65088));
        assertEq(13,StringDataUtil.getDirectionality((char)65088));
        assertFalse(StringDataLetterUtil.isLetter((char)65088));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65088));
    }
    @Test
    public void d2694(){
        assertEq(21,StringDataUtil.getType((char)65089));
        assertEq(13,StringDataUtil.getDirectionality((char)65089));
        assertFalse(StringDataLetterUtil.isLetter((char)65089));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65089));
    }
    @Test
    public void d2695(){
        assertEq(22,StringDataUtil.getType((char)65090));
        assertEq(13,StringDataUtil.getDirectionality((char)65090));
        assertFalse(StringDataLetterUtil.isLetter((char)65090));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65090));
    }
    @Test
    public void d2696(){
        assertEq(21,StringDataUtil.getType((char)65091));
        assertEq(13,StringDataUtil.getDirectionality((char)65091));
        assertFalse(StringDataLetterUtil.isLetter((char)65091));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65091));
    }
    @Test
    public void d2697(){
        assertEq(22,StringDataUtil.getType((char)65092));
        assertEq(13,StringDataUtil.getDirectionality((char)65092));
        assertFalse(StringDataLetterUtil.isLetter((char)65092));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65092));
    }
    @Test
    public void d2698(){
        assertEq(24,StringDataUtil.getType((char)65093));
        assertEq(13,StringDataUtil.getDirectionality((char)65093));
        assertFalse(StringDataLetterUtil.isLetter((char)65093));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65093));
    }
    @Test
    public void d2699(){
        assertEq(21,StringDataUtil.getType((char)65095));
        assertEq(13,StringDataUtil.getDirectionality((char)65095));
        assertFalse(StringDataLetterUtil.isLetter((char)65095));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65095));
    }
    @Test
    public void d2700(){
        assertEq(22,StringDataUtil.getType((char)65096));
        assertEq(13,StringDataUtil.getDirectionality((char)65096));
        assertFalse(StringDataLetterUtil.isLetter((char)65096));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65096));
    }
    @Test
    public void d2701(){
        assertEq(24,StringDataUtil.getType((char)65097));
        assertEq(13,StringDataUtil.getDirectionality((char)65097));
        assertFalse(StringDataLetterUtil.isLetter((char)65097));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65097));
    }
    @Test
    public void d2702(){
        assertEq(23,StringDataUtil.getType((char)65101));
        assertEq(13,StringDataUtil.getDirectionality((char)65101));
        assertFalse(StringDataLetterUtil.isLetter((char)65101));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65101));
    }
    @Test
    public void d2703(){
        assertEq(24,StringDataUtil.getType((char)65104));
        assertEq(7,StringDataUtil.getDirectionality((char)65104));
        assertFalse(StringDataLetterUtil.isLetter((char)65104));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65104));
    }
    @Test
    public void d2704(){
        assertEq(0,StringDataUtil.getType((char)65107));
        assertEq(-1,StringDataUtil.getDirectionality((char)65107));
        assertFalse(StringDataLetterUtil.isLetter((char)65107));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65107));
    }
    @Test
    public void d2705(){
        assertEq(24,StringDataUtil.getType((char)65108));
        assertEq(13,StringDataUtil.getDirectionality((char)65108));
        assertFalse(StringDataLetterUtil.isLetter((char)65108));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65108));
    }
    @Test
    public void d2706(){
        assertEq(20,StringDataUtil.getType((char)65112));
        assertEq(13,StringDataUtil.getDirectionality((char)65112));
        assertFalse(StringDataLetterUtil.isLetter((char)65112));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65112));
    }
    @Test
    public void d2707(){
        assertEq(21,StringDataUtil.getType((char)65113));
        assertEq(13,StringDataUtil.getDirectionality((char)65113));
        assertFalse(StringDataLetterUtil.isLetter((char)65113));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65113));
    }
    @Test
    public void d2708(){
        assertEq(22,StringDataUtil.getType((char)65114));
        assertEq(13,StringDataUtil.getDirectionality((char)65114));
        assertFalse(StringDataLetterUtil.isLetter((char)65114));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65114));
    }
    @Test
    public void d2709(){
        assertEq(21,StringDataUtil.getType((char)65115));
        assertEq(13,StringDataUtil.getDirectionality((char)65115));
        assertFalse(StringDataLetterUtil.isLetter((char)65115));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65115));
    }
    @Test
    public void d2710(){
        assertEq(22,StringDataUtil.getType((char)65116));
        assertEq(13,StringDataUtil.getDirectionality((char)65116));
        assertFalse(StringDataLetterUtil.isLetter((char)65116));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65116));
    }
    @Test
    public void d2711(){
        assertEq(21,StringDataUtil.getType((char)65117));
        assertEq(13,StringDataUtil.getDirectionality((char)65117));
        assertFalse(StringDataLetterUtil.isLetter((char)65117));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65117));
    }
    @Test
    public void d2712(){
        assertEq(22,StringDataUtil.getType((char)65118));
        assertEq(13,StringDataUtil.getDirectionality((char)65118));
        assertFalse(StringDataLetterUtil.isLetter((char)65118));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65118));
    }
    @Test
    public void d2713(){
        assertEq(24,StringDataUtil.getType((char)65119));
        assertEq(5,StringDataUtil.getDirectionality((char)65119));
        assertFalse(StringDataLetterUtil.isLetter((char)65119));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65119));
    }
    @Test
    public void d2714(){
        assertEq(25,StringDataUtil.getType((char)65122));
        assertEq(4,StringDataUtil.getDirectionality((char)65122));
        assertFalse(StringDataLetterUtil.isLetter((char)65122));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65122));
    }
    @Test
    public void d2715(){
        assertEq(20,StringDataUtil.getType((char)65123));
        assertEq(4,StringDataUtil.getDirectionality((char)65123));
        assertFalse(StringDataLetterUtil.isLetter((char)65123));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65123));
    }
    @Test
    public void d2716(){
        assertEq(25,StringDataUtil.getType((char)65124));
        assertEq(13,StringDataUtil.getDirectionality((char)65124));
        assertFalse(StringDataLetterUtil.isLetter((char)65124));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65124));
    }
    @Test
    public void d2717(){
        assertEq(0,StringDataUtil.getType((char)65127));
        assertEq(-1,StringDataUtil.getDirectionality((char)65127));
        assertFalse(StringDataLetterUtil.isLetter((char)65127));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65127));
    }
    @Test
    public void d2718(){
        assertEq(24,StringDataUtil.getType((char)65128));
        assertEq(13,StringDataUtil.getDirectionality((char)65128));
        assertFalse(StringDataLetterUtil.isLetter((char)65128));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65128));
    }
    @Test
    public void d2719(){
        assertEq(26,StringDataUtil.getType((char)65129));
        assertEq(5,StringDataUtil.getDirectionality((char)65129));
        assertFalse(StringDataLetterUtil.isLetter((char)65129));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65129));
    }
    @Test
    public void d2720(){
        assertEq(24,StringDataUtil.getType((char)65130));
        assertEq(5,StringDataUtil.getDirectionality((char)65130));
        assertFalse(StringDataLetterUtil.isLetter((char)65130));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65130));
    }
    @Test
    public void d2721(){
        assertEq(0,StringDataUtil.getType((char)65132));
        assertEq(-1,StringDataUtil.getDirectionality((char)65132));
        assertFalse(StringDataLetterUtil.isLetter((char)65132));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65132));
    }
    @Test
    public void d2722(){
        assertEq(5,StringDataUtil.getType((char)65136));
        assertEq(2,StringDataUtil.getDirectionality((char)65136));
        assertTrue(StringDataLetterUtil.isLetter((char)65136));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65136));
    }
    @Test
    public void d2723(){
        assertEq(0,StringDataUtil.getType((char)65141));
        assertEq(-1,StringDataUtil.getDirectionality((char)65141));
        assertFalse(StringDataLetterUtil.isLetter((char)65141));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65141));
    }
    @Test
    public void d2724(){
        assertEq(5,StringDataUtil.getType((char)65142));
        assertEq(2,StringDataUtil.getDirectionality((char)65142));
        assertTrue(StringDataLetterUtil.isLetter((char)65142));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65142));
    }
    @Test
    public void d2725(){
        assertEq(0,StringDataUtil.getType((char)65277));
        assertEq(-1,StringDataUtil.getDirectionality((char)65277));
        assertFalse(StringDataLetterUtil.isLetter((char)65277));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65277));
    }
    @Test
    public void d2726(){
        assertEq(16,StringDataUtil.getType((char)65279));
        assertEq(9,StringDataUtil.getDirectionality((char)65279));
        assertFalse(StringDataLetterUtil.isLetter((char)65279));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65279));
    }
    @Test
    public void d2727(){
        assertEq(0,StringDataUtil.getType((char)65280));
        assertEq(-1,StringDataUtil.getDirectionality((char)65280));
        assertFalse(StringDataLetterUtil.isLetter((char)65280));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65280));
    }
    @Test
    public void d2728(){
        assertEq(24,StringDataUtil.getType((char)65281));
        assertEq(13,StringDataUtil.getDirectionality((char)65281));
        assertFalse(StringDataLetterUtil.isLetter((char)65281));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65281));
    }
    @Test
    public void d2729(){
        assertEq(26,StringDataUtil.getType((char)65284));
        assertEq(5,StringDataUtil.getDirectionality((char)65284));
        assertFalse(StringDataLetterUtil.isLetter((char)65284));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65284));
    }
    @Test
    public void d2730(){
        assertEq(24,StringDataUtil.getType((char)65285));
        assertEq(5,StringDataUtil.getDirectionality((char)65285));
        assertFalse(StringDataLetterUtil.isLetter((char)65285));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65285));
    }
    @Test
    public void d2731(){
        assertEq(21,StringDataUtil.getType((char)65288));
        assertEq(13,StringDataUtil.getDirectionality((char)65288));
        assertFalse(StringDataLetterUtil.isLetter((char)65288));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65288));
    }
    @Test
    public void d2732(){
        assertEq(22,StringDataUtil.getType((char)65289));
        assertEq(13,StringDataUtil.getDirectionality((char)65289));
        assertFalse(StringDataLetterUtil.isLetter((char)65289));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65289));
    }
    @Test
    public void d2733(){
        assertEq(24,StringDataUtil.getType((char)65290));
        assertEq(13,StringDataUtil.getDirectionality((char)65290));
        assertFalse(StringDataLetterUtil.isLetter((char)65290));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65290));
    }
    @Test
    public void d2734(){
        assertEq(25,StringDataUtil.getType((char)65291));
        assertEq(4,StringDataUtil.getDirectionality((char)65291));
        assertFalse(StringDataLetterUtil.isLetter((char)65291));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65291));
    }
    @Test
    public void d2735(){
        assertEq(24,StringDataUtil.getType((char)65292));
        assertEq(7,StringDataUtil.getDirectionality((char)65292));
        assertFalse(StringDataLetterUtil.isLetter((char)65292));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65292));
    }
    @Test
    public void d2736(){
        assertEq(20,StringDataUtil.getType((char)65293));
        assertEq(4,StringDataUtil.getDirectionality((char)65293));
        assertFalse(StringDataLetterUtil.isLetter((char)65293));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65293));
    }
    @Test
    public void d2737(){
        assertEq(24,StringDataUtil.getType((char)65294));
        assertEq(7,StringDataUtil.getDirectionality((char)65294));
        assertFalse(StringDataLetterUtil.isLetter((char)65294));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65294));
    }
    @Test
    public void d2738(){
        assertEq(9,StringDataUtil.getType((char)65296));
        assertEq(3,StringDataUtil.getDirectionality((char)65296));
        assertFalse(StringDataLetterUtil.isLetter((char)65296));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65296));
    }
    @Test
    public void d2739(){
        assertEq(24,StringDataUtil.getType((char)65306));
        assertEq(7,StringDataUtil.getDirectionality((char)65306));
        assertFalse(StringDataLetterUtil.isLetter((char)65306));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65306));
    }
    @Test
    public void d2740(){
        assertEq(25,StringDataUtil.getType((char)65308));
        assertEq(13,StringDataUtil.getDirectionality((char)65308));
        assertFalse(StringDataLetterUtil.isLetter((char)65308));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65308));
    }
    @Test
    public void d2741(){
        assertEq(24,StringDataUtil.getType((char)65311));
        assertEq(13,StringDataUtil.getDirectionality((char)65311));
        assertFalse(StringDataLetterUtil.isLetter((char)65311));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65311));
    }
    @Test
    public void d2742(){
        assertEq(1,StringDataUtil.getType((char)65313));
        assertEq(0,StringDataUtil.getDirectionality((char)65313));
        assertTrue(StringDataLetterUtil.isLetter((char)65313));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65313));
    }
    @Test
    public void d2743(){
        assertEq(21,StringDataUtil.getType((char)65339));
        assertEq(13,StringDataUtil.getDirectionality((char)65339));
        assertFalse(StringDataLetterUtil.isLetter((char)65339));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65339));
    }
    @Test
    public void d2744(){
        assertEq(24,StringDataUtil.getType((char)65340));
        assertEq(13,StringDataUtil.getDirectionality((char)65340));
        assertFalse(StringDataLetterUtil.isLetter((char)65340));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65340));
    }
    @Test
    public void d2745(){
        assertEq(22,StringDataUtil.getType((char)65341));
        assertEq(13,StringDataUtil.getDirectionality((char)65341));
        assertFalse(StringDataLetterUtil.isLetter((char)65341));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65341));
    }
    @Test
    public void d2746(){
        assertEq(27,StringDataUtil.getType((char)65342));
        assertEq(13,StringDataUtil.getDirectionality((char)65342));
        assertFalse(StringDataLetterUtil.isLetter((char)65342));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65342));
    }
    @Test
    public void d2747(){
        assertEq(23,StringDataUtil.getType((char)65343));
        assertEq(13,StringDataUtil.getDirectionality((char)65343));
        assertFalse(StringDataLetterUtil.isLetter((char)65343));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65343));
    }
    @Test
    public void d2748(){
        assertEq(27,StringDataUtil.getType((char)65344));
        assertEq(13,StringDataUtil.getDirectionality((char)65344));
        assertFalse(StringDataLetterUtil.isLetter((char)65344));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65344));
    }
    @Test
    public void d2749(){
        assertEq(2,StringDataUtil.getType((char)65345));
        assertEq(0,StringDataUtil.getDirectionality((char)65345));
        assertTrue(StringDataLetterUtil.isLetter((char)65345));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65345));
    }
    @Test
    public void d2750(){
        assertEq(21,StringDataUtil.getType((char)65371));
        assertEq(13,StringDataUtil.getDirectionality((char)65371));
        assertFalse(StringDataLetterUtil.isLetter((char)65371));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65371));
    }
    @Test
    public void d2751(){
        assertEq(25,StringDataUtil.getType((char)65372));
        assertEq(13,StringDataUtil.getDirectionality((char)65372));
        assertFalse(StringDataLetterUtil.isLetter((char)65372));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65372));
    }
    @Test
    public void d2752(){
        assertEq(22,StringDataUtil.getType((char)65373));
        assertEq(13,StringDataUtil.getDirectionality((char)65373));
        assertFalse(StringDataLetterUtil.isLetter((char)65373));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65373));
    }
    @Test
    public void d2753(){
        assertEq(25,StringDataUtil.getType((char)65374));
        assertEq(13,StringDataUtil.getDirectionality((char)65374));
        assertFalse(StringDataLetterUtil.isLetter((char)65374));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65374));
    }
    @Test
    public void d2754(){
        assertEq(21,StringDataUtil.getType((char)65375));
        assertEq(13,StringDataUtil.getDirectionality((char)65375));
        assertFalse(StringDataLetterUtil.isLetter((char)65375));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65375));
    }
    @Test
    public void d2755(){
        assertEq(22,StringDataUtil.getType((char)65376));
        assertEq(13,StringDataUtil.getDirectionality((char)65376));
        assertFalse(StringDataLetterUtil.isLetter((char)65376));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65376));
    }
    @Test
    public void d2756(){
        assertEq(24,StringDataUtil.getType((char)65377));
        assertEq(13,StringDataUtil.getDirectionality((char)65377));
        assertFalse(StringDataLetterUtil.isLetter((char)65377));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65377));
    }
    @Test
    public void d2757(){
        assertEq(21,StringDataUtil.getType((char)65378));
        assertEq(13,StringDataUtil.getDirectionality((char)65378));
        assertFalse(StringDataLetterUtil.isLetter((char)65378));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65378));
    }
    @Test
    public void d2758(){
        assertEq(22,StringDataUtil.getType((char)65379));
        assertEq(13,StringDataUtil.getDirectionality((char)65379));
        assertFalse(StringDataLetterUtil.isLetter((char)65379));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65379));
    }
    @Test
    public void d2759(){
        assertEq(24,StringDataUtil.getType((char)65380));
        assertEq(13,StringDataUtil.getDirectionality((char)65380));
        assertFalse(StringDataLetterUtil.isLetter((char)65380));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65380));
    }
    @Test
    public void d2760(){
        assertEq(5,StringDataUtil.getType((char)65382));
        assertEq(0,StringDataUtil.getDirectionality((char)65382));
        assertTrue(StringDataLetterUtil.isLetter((char)65382));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65382));
    }
    @Test
    public void d2761(){
        assertEq(4,StringDataUtil.getType((char)65392));
        assertEq(0,StringDataUtil.getDirectionality((char)65392));
        assertTrue(StringDataLetterUtil.isLetter((char)65392));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65392));
    }
    @Test
    public void d2762(){
        assertEq(5,StringDataUtil.getType((char)65393));
        assertEq(0,StringDataUtil.getDirectionality((char)65393));
        assertTrue(StringDataLetterUtil.isLetter((char)65393));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65393));
    }
    @Test
    public void d2763(){
        assertEq(4,StringDataUtil.getType((char)65438));
        assertEq(0,StringDataUtil.getDirectionality((char)65438));
        assertTrue(StringDataLetterUtil.isLetter((char)65438));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65438));
    }
    @Test
    public void d2764(){
        assertEq(5,StringDataUtil.getType((char)65440));
        assertEq(0,StringDataUtil.getDirectionality((char)65440));
        assertTrue(StringDataLetterUtil.isLetter((char)65440));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65440));
    }
    @Test
    public void d2765(){
        assertEq(0,StringDataUtil.getType((char)65471));
        assertEq(-1,StringDataUtil.getDirectionality((char)65471));
        assertFalse(StringDataLetterUtil.isLetter((char)65471));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65471));
    }
    @Test
    public void d2766(){
        assertEq(5,StringDataUtil.getType((char)65474));
        assertEq(0,StringDataUtil.getDirectionality((char)65474));
        assertTrue(StringDataLetterUtil.isLetter((char)65474));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65474));
    }
    @Test
    public void d2767(){
        assertEq(0,StringDataUtil.getType((char)65480));
        assertEq(-1,StringDataUtil.getDirectionality((char)65480));
        assertFalse(StringDataLetterUtil.isLetter((char)65480));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65480));
    }
    @Test
    public void d2768(){
        assertEq(5,StringDataUtil.getType((char)65482));
        assertEq(0,StringDataUtil.getDirectionality((char)65482));
        assertTrue(StringDataLetterUtil.isLetter((char)65482));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65482));
    }
    @Test
    public void d2769(){
        assertEq(0,StringDataUtil.getType((char)65488));
        assertEq(-1,StringDataUtil.getDirectionality((char)65488));
        assertFalse(StringDataLetterUtil.isLetter((char)65488));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65488));
    }
    @Test
    public void d2770(){
        assertEq(5,StringDataUtil.getType((char)65490));
        assertEq(0,StringDataUtil.getDirectionality((char)65490));
        assertTrue(StringDataLetterUtil.isLetter((char)65490));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65490));
    }
    @Test
    public void d2771(){
        assertEq(0,StringDataUtil.getType((char)65496));
        assertEq(-1,StringDataUtil.getDirectionality((char)65496));
        assertFalse(StringDataLetterUtil.isLetter((char)65496));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65496));
    }
    @Test
    public void d2772(){
        assertEq(5,StringDataUtil.getType((char)65498));
        assertEq(0,StringDataUtil.getDirectionality((char)65498));
        assertTrue(StringDataLetterUtil.isLetter((char)65498));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65498));
    }
    @Test
    public void d2773(){
        assertEq(0,StringDataUtil.getType((char)65501));
        assertEq(-1,StringDataUtil.getDirectionality((char)65501));
        assertFalse(StringDataLetterUtil.isLetter((char)65501));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65501));
    }
    @Test
    public void d2774(){
        assertEq(26,StringDataUtil.getType((char)65504));
        assertEq(5,StringDataUtil.getDirectionality((char)65504));
        assertFalse(StringDataLetterUtil.isLetter((char)65504));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65504));
    }
    @Test
    public void d2775(){
        assertEq(25,StringDataUtil.getType((char)65506));
        assertEq(13,StringDataUtil.getDirectionality((char)65506));
        assertFalse(StringDataLetterUtil.isLetter((char)65506));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65506));
    }
    @Test
    public void d2776(){
        assertEq(27,StringDataUtil.getType((char)65507));
        assertEq(13,StringDataUtil.getDirectionality((char)65507));
        assertFalse(StringDataLetterUtil.isLetter((char)65507));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65507));
    }
    @Test
    public void d2777(){
        assertEq(28,StringDataUtil.getType((char)65508));
        assertEq(13,StringDataUtil.getDirectionality((char)65508));
        assertFalse(StringDataLetterUtil.isLetter((char)65508));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65508));
    }
    @Test
    public void d2778(){
        assertEq(26,StringDataUtil.getType((char)65509));
        assertEq(5,StringDataUtil.getDirectionality((char)65509));
        assertFalse(StringDataLetterUtil.isLetter((char)65509));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65509));
    }
    @Test
    public void d2779(){
        assertEq(0,StringDataUtil.getType((char)65511));
        assertEq(-1,StringDataUtil.getDirectionality((char)65511));
        assertFalse(StringDataLetterUtil.isLetter((char)65511));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65511));
    }
    @Test
    public void d2780(){
        assertEq(28,StringDataUtil.getType((char)65512));
        assertEq(13,StringDataUtil.getDirectionality((char)65512));
        assertFalse(StringDataLetterUtil.isLetter((char)65512));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65512));
    }
    @Test
    public void d2781(){
        assertEq(25,StringDataUtil.getType((char)65513));
        assertEq(13,StringDataUtil.getDirectionality((char)65513));
        assertFalse(StringDataLetterUtil.isLetter((char)65513));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65513));
    }
    @Test
    public void d2782(){
        assertEq(28,StringDataUtil.getType((char)65517));
        assertEq(13,StringDataUtil.getDirectionality((char)65517));
        assertFalse(StringDataLetterUtil.isLetter((char)65517));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65517));
    }
    @Test
    public void d2783(){
        assertEq(0,StringDataUtil.getType((char)65519));
        assertEq(-1,StringDataUtil.getDirectionality((char)65519));
        assertFalse(StringDataLetterUtil.isLetter((char)65519));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65519));
    }
    @Test
    public void d2784(){
        assertEq(16,StringDataUtil.getType((char)65529));
        assertEq(13,StringDataUtil.getDirectionality((char)65529));
        assertFalse(StringDataLetterUtil.isLetter((char)65529));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65529));
    }
    @Test
    public void d2785(){
        assertEq(28,StringDataUtil.getType((char)65532));
        assertEq(13,StringDataUtil.getDirectionality((char)65532));
        assertFalse(StringDataLetterUtil.isLetter((char)65532));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65532));
    }
    @Test
    public void d2786(){
        assertEq(0,StringDataUtil.getType((char)65534));
        assertEq(-1,StringDataUtil.getDirectionality((char)65534));
        assertFalse(StringDataLetterUtil.isLetter((char)65534));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65534));
    }
    @Test
    public void d2787(){
        assertEq(25,StringDataUtil.getType((char)1544));
        assertEq(2,StringDataUtil.getDirectionality((char)1544));
        assertFalse(StringDataLetterUtil.isLetter((char)1544));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1544));
    }
    @Test
    public void d2788(){
        assertEq(25,StringDataUtil.getType((char)8316));
        assertEq(13,StringDataUtil.getDirectionality((char)8316));
        assertFalse(StringDataLetterUtil.isLetter((char)8316));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8316));
    }
    @Test
    public void d2789(){
        assertEq(25,StringDataUtil.getType((char)8722));
        assertEq(4,StringDataUtil.getDirectionality((char)8722));
        assertFalse(StringDataLetterUtil.isLetter((char)8722));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8722));
    }
    @Test
    public void d2790(){
        assertEq(25,StringDataUtil.getType((char)8723));
        assertEq(5,StringDataUtil.getDirectionality((char)8723));
        assertFalse(StringDataLetterUtil.isLetter((char)8723));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8723));
    }
    @Test
    public void d2791(){
        assertEq(28,StringDataUtil.getType((char)9900));
        assertEq(0,StringDataUtil.getDirectionality((char)9900));
        assertFalse(StringDataLetterUtil.isLetter((char)9900));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9900));
    }
    @Test
    public void d2792(){
        assertEq(28,StringDataUtil.getType((char)9109));
        assertEq(0,StringDataUtil.getDirectionality((char)9109));
        assertFalse(StringDataLetterUtil.isLetter((char)9109));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9109));
    }
    @Test
    public void d2793(){
        assertEq(28,StringDataUtil.getType((char)9014));
        assertEq(0,StringDataUtil.getDirectionality((char)9014));
        assertFalse(StringDataLetterUtil.isLetter((char)9014));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9014));
    }
    @Test
    public void d2794(){
        assertEq(28,StringDataUtil.getType((char)12829));
        assertEq(13,StringDataUtil.getDirectionality((char)12829));
        assertFalse(StringDataLetterUtil.isLetter((char)12829));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12829));
    }
    @Test
    public void d2795(){
        assertEq(28,StringDataUtil.getType((char)12924));
        assertEq(13,StringDataUtil.getDirectionality((char)12924));
        assertFalse(StringDataLetterUtil.isLetter((char)12924));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12924));
    }
    @Test
    public void d2796(){
        assertEq(28,StringDataUtil.getType((char)13004));
        assertEq(13,StringDataUtil.getDirectionality((char)13004));
        assertFalse(StringDataLetterUtil.isLetter((char)13004));
        assertFalse(StringDataUtil.isLetterOrDigit((char)13004));
    }
    @Test
    public void d2797(){
        assertEq(28,StringDataUtil.getType((char)13175));
        assertEq(13,StringDataUtil.getDirectionality((char)13175));
        assertFalse(StringDataLetterUtil.isLetter((char)13175));
        assertFalse(StringDataUtil.isLetterOrDigit((char)13175));
    }
    @Test
    public void d2798(){
        assertEq(28,StringDataUtil.getType((char)13179));
        assertEq(0,StringDataUtil.getDirectionality((char)13179));
        assertFalse(StringDataLetterUtil.isLetter((char)13179));
        assertFalse(StringDataUtil.isLetterOrDigit((char)13179));
    }
    @Test
    public void d2799(){
        assertEq(28,StringDataUtil.getType((char)13278));
        assertEq(13,StringDataUtil.getDirectionality((char)13278));
        assertFalse(StringDataLetterUtil.isLetter((char)13278));
        assertFalse(StringDataUtil.isLetterOrDigit((char)13278));
    }
    @Test
    public void d2800(){
        assertEq(28,StringDataUtil.getType((char)13280));
        assertEq(0,StringDataUtil.getDirectionality((char)13280));
        assertFalse(StringDataLetterUtil.isLetter((char)13280));
        assertFalse(StringDataUtil.isLetterOrDigit((char)13280));
    }
    @Test
    public void d2801(){
        assertEq(24,StringDataUtil.getType((char)1645));
        assertEq(2,StringDataUtil.getDirectionality((char)1645));
        assertFalse(StringDataLetterUtil.isLetter((char)1645));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1645));
    }
    @Test
    public void d2802(){
        assertEq(24,StringDataUtil.getType((char)65106));
        assertEq(7,StringDataUtil.getDirectionality((char)65106));
        assertFalse(StringDataLetterUtil.isLetter((char)65106));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65106));
    }
    @Test
    public void d2803(){
        assertEq(24,StringDataUtil.getType((char)65109));
        assertEq(7,StringDataUtil.getDirectionality((char)65109));
        assertFalse(StringDataLetterUtil.isLetter((char)65109));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65109));
    }
    @Test
    public void d2804(){
        assertEq(24,StringDataUtil.getType((char)1644));
        assertEq(6,StringDataUtil.getDirectionality((char)1644));
        assertFalse(StringDataLetterUtil.isLetter((char)1644));
        assertFalse(StringDataUtil.isLetterOrDigit((char)1644));
    }
    @Test
    public void d2805(){
        assertEq(24,StringDataUtil.getType((char)65105));
        assertEq(13,StringDataUtil.getDirectionality((char)65105));
        assertFalse(StringDataLetterUtil.isLetter((char)65105));
        assertFalse(StringDataUtil.isLetterOrDigit((char)65105));
    }
    @Test
    public void d2806(){
        assertEq(15,StringDataUtil.getType((char)10));
        assertEq(10,StringDataUtil.getDirectionality((char)10));
        assertFalse(StringDataLetterUtil.isLetter((char)10));
        assertFalse(StringDataUtil.isLetterOrDigit((char)10));
    }
    @Test
    public void d2807(){
        assertEq(15,StringDataUtil.getType((char)11));
        assertEq(11,StringDataUtil.getDirectionality((char)11));
        assertFalse(StringDataLetterUtil.isLetter((char)11));
        assertFalse(StringDataUtil.isLetterOrDigit((char)11));
    }
    @Test
    public void d2808(){
        assertEq(15,StringDataUtil.getType((char)12));
        assertEq(12,StringDataUtil.getDirectionality((char)12));
        assertFalse(StringDataLetterUtil.isLetter((char)12));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12));
    }
    @Test
    public void d2809(){
        assertEq(15,StringDataUtil.getType((char)13));
        assertEq(10,StringDataUtil.getDirectionality((char)13));
        assertFalse(StringDataLetterUtil.isLetter((char)13));
        assertFalse(StringDataUtil.isLetterOrDigit((char)13));
    }
    @Test
    public void d2810(){
        assertEq(15,StringDataUtil.getType((char)9));
        assertEq(11,StringDataUtil.getDirectionality((char)9));
        assertFalse(StringDataLetterUtil.isLetter((char)9));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9));
    }
    @Test
    public void d2811(){
        assertEq(15,StringDataUtil.getType((char)30));
        assertEq(10,StringDataUtil.getDirectionality((char)30));
        assertFalse(StringDataLetterUtil.isLetter((char)30));
        assertFalse(StringDataUtil.isLetterOrDigit((char)30));
    }
    @Test
    public void d2812(){
        assertEq(15,StringDataUtil.getType((char)31));
        assertEq(11,StringDataUtil.getDirectionality((char)31));
        assertFalse(StringDataLetterUtil.isLetter((char)31));
        assertFalse(StringDataUtil.isLetterOrDigit((char)31));
    }
    @Test
    public void d2813(){
        assertEq(15,StringDataUtil.getType((char)133));
        assertEq(10,StringDataUtil.getDirectionality((char)133));
        assertFalse(StringDataLetterUtil.isLetter((char)133));
        assertFalse(StringDataUtil.isLetterOrDigit((char)133));
    }
    @Test
    public void d2814(){
        assertEq(16,StringDataUtil.getType((char)8206));
        assertEq(0,StringDataUtil.getDirectionality((char)8206));
        assertFalse(StringDataLetterUtil.isLetter((char)8206));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8206));
    }
    @Test
    public void d2815(){
        assertEq(16,StringDataUtil.getType((char)8207));
        assertEq(1,StringDataUtil.getDirectionality((char)8207));
        assertFalse(StringDataLetterUtil.isLetter((char)8207));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8207));
    }
    @Test
    public void d2816(){
        assertEq(16,StringDataUtil.getType((char)8235));
        assertEq(16,StringDataUtil.getDirectionality((char)8235));
        assertFalse(StringDataLetterUtil.isLetter((char)8235));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8235));
    }
    @Test
    public void d2817(){
        assertEq(16,StringDataUtil.getType((char)8236));
        assertEq(18,StringDataUtil.getDirectionality((char)8236));
        assertFalse(StringDataLetterUtil.isLetter((char)8236));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8236));
    }
    @Test
    public void d2818(){
        assertEq(16,StringDataUtil.getType((char)8237));
        assertEq(15,StringDataUtil.getDirectionality((char)8237));
        assertFalse(StringDataLetterUtil.isLetter((char)8237));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8237));
    }
    @Test
    public void d2819(){
        assertEq(16,StringDataUtil.getType((char)8238));
        assertEq(17,StringDataUtil.getDirectionality((char)8238));
        assertFalse(StringDataLetterUtil.isLetter((char)8238));
        assertFalse(StringDataUtil.isLetterOrDigit((char)8238));
    }
    @Test
    public void d2820(){
        assertEq(11,StringDataUtil.getType((char)9371));
        assertEq(3,StringDataUtil.getDirectionality((char)9371));
        assertFalse(StringDataLetterUtil.isLetter((char)9371));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9371));
    }
    @Test
    public void d2821(){
        assertEq(22,StringDataUtil.getType((char)12319));
        assertEq(13,StringDataUtil.getDirectionality((char)12319));
        assertFalse(StringDataLetterUtil.isLetter((char)12319));
        assertFalse(StringDataUtil.isLetterOrDigit((char)12319));
    }
    @Test
    public void d2822(){
        assertEq(24,StringDataUtil.getType((char)34));
        assertEq(13,StringDataUtil.getDirectionality((char)34));
        assertFalse(StringDataLetterUtil.isLetter((char)34));
        assertFalse(StringDataUtil.isLetterOrDigit((char)34));
    }
    @Test
    public void d2823(){
        assertEq(2,StringDataUtil.getType((char)7578));
        assertEq(0,StringDataUtil.getDirectionality((char)7578));
        assertTrue(StringDataLetterUtil.isLetter((char)7578));
        assertTrue(StringDataUtil.isLetterOrDigit((char)7578));
    }
    @Test
    public void d2824(){
        assertEq(2,StringDataUtil.getType((char)42801));
        assertEq(0,StringDataUtil.getDirectionality((char)42801));
        assertTrue(StringDataLetterUtil.isLetter((char)42801));
        assertTrue(StringDataUtil.isLetterOrDigit((char)42801));
    }
    @Test
    public void d2825(){
        assertEq(4,StringDataUtil.getType((char)1766));
        assertEq(2,StringDataUtil.getDirectionality((char)1766));
        assertTrue(StringDataLetterUtil.isLetter((char)1766));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1766));
    }
    @Test
    public void d2826(){
        assertEq(4,StringDataUtil.getType((char)2037));
        assertEq(1,StringDataUtil.getDirectionality((char)2037));
        assertTrue(StringDataLetterUtil.isLetter((char)2037));
        assertTrue(StringDataUtil.isLetterOrDigit((char)2037));
    }
    @Test
    public void d2827(){
        assertEq(4,StringDataUtil.getType((char)1369));
        assertEq(0,StringDataUtil.getDirectionality((char)1369));
        assertTrue(StringDataLetterUtil.isLetter((char)1369));
        assertTrue(StringDataUtil.isLetterOrDigit((char)1369));
    }
    @Test
    public void d2828(){
        assertEq(4,StringDataUtil.getType((char)43764));
        assertEq(0,StringDataUtil.getDirectionality((char)43764));
        assertTrue(StringDataLetterUtil.isLetter((char)43764));
        assertTrue(StringDataUtil.isLetterOrDigit((char)43764));
    }
    @Test
    public void d2829(){
        assertEq(4,StringDataUtil.getType((char)65439));
        assertEq(0,StringDataUtil.getDirectionality((char)65439));
        assertTrue(StringDataLetterUtil.isLetter((char)65439));
        assertTrue(StringDataUtil.isLetterOrDigit((char)65439));
    }
    @Test
    public void d2830(){
        assertEq(24,StringDataUtil.getType((char)59));
        assertEq(13,StringDataUtil.getDirectionality((char)59));
        assertFalse(StringDataLetterUtil.isLetter((char)59));
        assertFalse(StringDataUtil.isLetterOrDigit((char)59));
    }
    @Test
    public void d2831(){
        assertEq(28,StringDataUtil.getType((char)9398));
        assertEq(0,StringDataUtil.getDirectionality((char)9398));
        assertFalse(StringDataLetterUtil.isLetter((char)9398));
        assertFalse(StringDataUtil.isLetterOrDigit((char)9398));
    }
}
