package code.expressionlanguage.common;

import code.expressionlanguage.methods.ProcessMethodCommon;
import code.util.StringList;
import org.junit.Test;

public final class StringExpUtilTwoTest extends ProcessMethodCommon {
    @Test
    public void toLower1() {
        assertEq(97, StringDataUtil.toLowerCase((char)65));
    }
    @Test
    public void toLower2() {
        assertEq(224, StringDataUtil.toLowerCase((char)192));
    }
    @Test
    public void toLower3() {
        assertEq(248, StringDataUtil.toLowerCase((char)216));
    }
    @Test
    public void toLower4() {
        assertEq(257, StringDataUtil.toLowerCase((char)256));
    }
    @Test
    public void toLower5() {
        assertEq(105, StringDataUtil.toLowerCase((char)304));
    }
    @Test
    public void toLower6() {
        assertEq(307, StringDataUtil.toLowerCase((char)306));
    }
    @Test
    public void toLower7() {
        assertEq(314, StringDataUtil.toLowerCase((char)313));
    }
    @Test
    public void toLower8() {
        assertEq(331, StringDataUtil.toLowerCase((char)330));
    }
    @Test
    public void toLower9() {
        assertEq(255, StringDataUtil.toLowerCase((char)376));
    }
    @Test
    public void toLower10() {
        assertEq(378, StringDataUtil.toLowerCase((char)377));
    }
    @Test
    public void toLower11() {
        assertEq(595, StringDataUtil.toLowerCase((char)385));
    }
    @Test
    public void toLower12() {
        assertEq(387, StringDataUtil.toLowerCase((char)386));
    }
    @Test
    public void toLower13() {
        assertEq(596, StringDataUtil.toLowerCase((char)390));
    }
    @Test
    public void toLower14() {
        assertEq(392, StringDataUtil.toLowerCase((char)391));
    }
    @Test
    public void toLower15() {
        assertEq(598, StringDataUtil.toLowerCase((char)393));
    }
    @Test
    public void toLower16() {
        assertEq(396, StringDataUtil.toLowerCase((char)395));
    }
    @Test
    public void toLower17() {
        assertEq(477, StringDataUtil.toLowerCase((char)398));
    }
    @Test
    public void toLower18() {
        assertEq(601, StringDataUtil.toLowerCase((char)399));
    }
    @Test
    public void toLower19() {
        assertEq(603, StringDataUtil.toLowerCase((char)400));
    }
    @Test
    public void toLower20() {
        assertEq(402, StringDataUtil.toLowerCase((char)401));
    }
    @Test
    public void toLower21() {
        assertEq(608, StringDataUtil.toLowerCase((char)403));
    }
    @Test
    public void toLower22() {
        assertEq(611, StringDataUtil.toLowerCase((char)404));
    }
    @Test
    public void toLower23() {
        assertEq(617, StringDataUtil.toLowerCase((char)406));
    }
    @Test
    public void toLower24() {
        assertEq(616, StringDataUtil.toLowerCase((char)407));
    }
    @Test
    public void toLower25() {
        assertEq(409, StringDataUtil.toLowerCase((char)408));
    }
    @Test
    public void toLower26() {
        assertEq(623, StringDataUtil.toLowerCase((char)412));
    }
    @Test
    public void toLower27() {
        assertEq(626, StringDataUtil.toLowerCase((char)413));
    }
    @Test
    public void toLower28() {
        assertEq(629, StringDataUtil.toLowerCase((char)415));
    }
    @Test
    public void toLower29() {
        assertEq(417, StringDataUtil.toLowerCase((char)416));
    }
    @Test
    public void toLower30() {
        assertEq(640, StringDataUtil.toLowerCase((char)422));
    }
    @Test
    public void toLower31() {
        assertEq(424, StringDataUtil.toLowerCase((char)423));
    }
    @Test
    public void toLower32() {
        assertEq(643, StringDataUtil.toLowerCase((char)425));
    }
    @Test
    public void toLower33() {
        assertEq(429, StringDataUtil.toLowerCase((char)428));
    }
    @Test
    public void toLower34() {
        assertEq(648, StringDataUtil.toLowerCase((char)430));
    }
    @Test
    public void toLower35() {
        assertEq(432, StringDataUtil.toLowerCase((char)431));
    }
    @Test
    public void toLower36() {
        assertEq(650, StringDataUtil.toLowerCase((char)433));
    }
    @Test
    public void toLower37() {
        assertEq(436, StringDataUtil.toLowerCase((char)435));
    }
    @Test
    public void toLower38() {
        assertEq(658, StringDataUtil.toLowerCase((char)439));
    }
    @Test
    public void toLower39() {
        assertEq(441, StringDataUtil.toLowerCase((char)440));
    }
    @Test
    public void toLower40() {
        assertEq(445, StringDataUtil.toLowerCase((char)444));
    }
    @Test
    public void toLower41() {
        assertEq(454, StringDataUtil.toLowerCase((char)452));
    }
    @Test
    public void toLower42() {
        assertEq(454, StringDataUtil.toLowerCase((char)453));
    }
    @Test
    public void toLower43() {
        assertEq(457, StringDataUtil.toLowerCase((char)455));
    }
    @Test
    public void toLower44() {
        assertEq(457, StringDataUtil.toLowerCase((char)456));
    }
    @Test
    public void toLower45() {
        assertEq(460, StringDataUtil.toLowerCase((char)458));
    }
    @Test
    public void toLower46() {
        assertEq(460, StringDataUtil.toLowerCase((char)459));
    }
    @Test
    public void toLower47() {
        assertEq(479, StringDataUtil.toLowerCase((char)478));
    }
    @Test
    public void toLower48() {
        assertEq(499, StringDataUtil.toLowerCase((char)497));
    }
    @Test
    public void toLower49() {
        assertEq(499, StringDataUtil.toLowerCase((char)498));
    }
    @Test
    public void toLower50() {
        assertEq(405, StringDataUtil.toLowerCase((char)502));
    }
    @Test
    public void toLower51() {
        assertEq(447, StringDataUtil.toLowerCase((char)503));
    }
    @Test
    public void toLower52() {
        assertEq(505, StringDataUtil.toLowerCase((char)504));
    }
    @Test
    public void toLower53() {
        assertEq(414, StringDataUtil.toLowerCase((char)544));
    }
    @Test
    public void toLower54() {
        assertEq(547, StringDataUtil.toLowerCase((char)546));
    }
    @Test
    public void toLower55() {
        assertEq(11365, StringDataUtil.toLowerCase((char)570));
    }
    @Test
    public void toLower56() {
        assertEq(572, StringDataUtil.toLowerCase((char)571));
    }
    @Test
    public void toLower57() {
        assertEq(410, StringDataUtil.toLowerCase((char)573));
    }
    @Test
    public void toLower58() {
        assertEq(11366, StringDataUtil.toLowerCase((char)574));
    }
    @Test
    public void toLower59() {
        assertEq(578, StringDataUtil.toLowerCase((char)577));
    }
    @Test
    public void toLower60() {
        assertEq(384, StringDataUtil.toLowerCase((char)579));
    }
    @Test
    public void toLower61() {
        assertEq(649, StringDataUtil.toLowerCase((char)580));
    }
    @Test
    public void toLower62() {
        assertEq(652, StringDataUtil.toLowerCase((char)581));
    }
    @Test
    public void toLower63() {
        assertEq(583, StringDataUtil.toLowerCase((char)582));
    }
    @Test
    public void toLower64() {
        assertEq(881, StringDataUtil.toLowerCase((char)880));
    }
    @Test
    public void toLower65() {
        assertEq(887, StringDataUtil.toLowerCase((char)886));
    }
    @Test
    public void toLower66() {
        assertEq(940, StringDataUtil.toLowerCase((char)902));
    }
    @Test
    public void toLower67() {
        assertEq(941, StringDataUtil.toLowerCase((char)904));
    }
    @Test
    public void toLower68() {
        assertEq(972, StringDataUtil.toLowerCase((char)908));
    }
    @Test
    public void toLower69() {
        assertEq(973, StringDataUtil.toLowerCase((char)910));
    }
    @Test
    public void toLower70() {
        assertEq(945, StringDataUtil.toLowerCase((char)913));
    }
    @Test
    public void toLower71() {
        assertEq(963, StringDataUtil.toLowerCase((char)931));
    }
    @Test
    public void toLower72() {
        assertEq(983, StringDataUtil.toLowerCase((char)975));
    }
    @Test
    public void toLower73() {
        assertEq(985, StringDataUtil.toLowerCase((char)984));
    }
    @Test
    public void toLower74() {
        assertEq(952, StringDataUtil.toLowerCase((char)1012));
    }
    @Test
    public void toLower75() {
        assertEq(1016, StringDataUtil.toLowerCase((char)1015));
    }
    @Test
    public void toLower76() {
        assertEq(1010, StringDataUtil.toLowerCase((char)1017));
    }
    @Test
    public void toLower77() {
        assertEq(1019, StringDataUtil.toLowerCase((char)1018));
    }
    @Test
    public void toLower78() {
        assertEq(891, StringDataUtil.toLowerCase((char)1021));
    }
    @Test
    public void toLower79() {
        assertEq(1104, StringDataUtil.toLowerCase((char)1024));
    }
    @Test
    public void toLower80() {
        assertEq(1072, StringDataUtil.toLowerCase((char)1040));
    }
    @Test
    public void toLower81() {
        assertEq(1121, StringDataUtil.toLowerCase((char)1120));
    }
    @Test
    public void toLower82() {
        assertEq(1163, StringDataUtil.toLowerCase((char)1162));
    }
    @Test
    public void toLower83() {
        assertEq(1231, StringDataUtil.toLowerCase((char)1216));
    }
    @Test
    public void toLower84() {
        assertEq(1218, StringDataUtil.toLowerCase((char)1217));
    }
    @Test
    public void toLower85() {
        assertEq(1233, StringDataUtil.toLowerCase((char)1232));
    }
    @Test
    public void toLower86() {
        assertEq(1377, StringDataUtil.toLowerCase((char)1329));
    }
    @Test
    public void toLower87() {
        assertEq(11520, StringDataUtil.toLowerCase((char)4256));
    }
    @Test
    public void toLower88() {
        assertEq(11559, StringDataUtil.toLowerCase((char)4295));
    }
    @Test
    public void toLower89() {
        assertEq(11565, StringDataUtil.toLowerCase((char)4301));
    }
    @Test
    public void toLower90() {
        assertEq(7681, StringDataUtil.toLowerCase((char)7680));
    }
    @Test
    public void toLower91() {
        assertEq(223, StringDataUtil.toLowerCase((char)7838));
    }
    @Test
    public void toLower92() {
        assertEq(7841, StringDataUtil.toLowerCase((char)7840));
    }
    @Test
    public void toLower93() {
        assertEq(7936, StringDataUtil.toLowerCase((char)7944));
    }
    @Test
    public void toLower94() {
        assertEq(7952, StringDataUtil.toLowerCase((char)7960));
    }
    @Test
    public void toLower95() {
        assertEq(7968, StringDataUtil.toLowerCase((char)7976));
    }
    @Test
    public void toLower96() {
        assertEq(7984, StringDataUtil.toLowerCase((char)7992));
    }
    @Test
    public void toLower97() {
        assertEq(8000, StringDataUtil.toLowerCase((char)8008));
    }
    @Test
    public void toLower98() {
        assertEq(8017, StringDataUtil.toLowerCase((char)8025));
    }
    @Test
    public void toLower99() {
        assertEq(8019, StringDataUtil.toLowerCase((char)8027));
    }
    @Test
    public void toLower100() {
        assertEq(8021, StringDataUtil.toLowerCase((char)8029));
    }
    @Test
    public void toLower101() {
        assertEq(8023, StringDataUtil.toLowerCase((char)8031));
    }
    @Test
    public void toLower102() {
        assertEq(8032, StringDataUtil.toLowerCase((char)8040));
    }
    @Test
    public void toLower103() {
        assertEq(8064, StringDataUtil.toLowerCase((char)8072));
    }
    @Test
    public void toLower104() {
        assertEq(8080, StringDataUtil.toLowerCase((char)8088));
    }
    @Test
    public void toLower105() {
        assertEq(8096, StringDataUtil.toLowerCase((char)8104));
    }
    @Test
    public void toLower106() {
        assertEq(8112, StringDataUtil.toLowerCase((char)8120));
    }
    @Test
    public void toLower107() {
        assertEq(8048, StringDataUtil.toLowerCase((char)8122));
    }
    @Test
    public void toLower108() {
        assertEq(8115, StringDataUtil.toLowerCase((char)8124));
    }
    @Test
    public void toLower109() {
        assertEq(8050, StringDataUtil.toLowerCase((char)8136));
    }
    @Test
    public void toLower110() {
        assertEq(8131, StringDataUtil.toLowerCase((char)8140));
    }
    @Test
    public void toLower111() {
        assertEq(8144, StringDataUtil.toLowerCase((char)8152));
    }
    @Test
    public void toLower112() {
        assertEq(8054, StringDataUtil.toLowerCase((char)8154));
    }
    @Test
    public void toLower113() {
        assertEq(8160, StringDataUtil.toLowerCase((char)8168));
    }
    @Test
    public void toLower114() {
        assertEq(8058, StringDataUtil.toLowerCase((char)8170));
    }
    @Test
    public void toLower115() {
        assertEq(8165, StringDataUtil.toLowerCase((char)8172));
    }
    @Test
    public void toLower116() {
        assertEq(8056, StringDataUtil.toLowerCase((char)8184));
    }
    @Test
    public void toLower117() {
        assertEq(8060, StringDataUtil.toLowerCase((char)8186));
    }
    @Test
    public void toLower118() {
        assertEq(8179, StringDataUtil.toLowerCase((char)8188));
    }
    @Test
    public void toLower119() {
        assertEq(969, StringDataUtil.toLowerCase((char)8486));
    }
    @Test
    public void toLower120() {
        assertEq(107, StringDataUtil.toLowerCase((char)8490));
    }
    @Test
    public void toLower121() {
        assertEq(229, StringDataUtil.toLowerCase((char)8491));
    }
    @Test
    public void toLower122() {
        assertEq(8526, StringDataUtil.toLowerCase((char)8498));
    }
    @Test
    public void toLower123() {
        assertEq(8560, StringDataUtil.toLowerCase((char)8544));
    }
    @Test
    public void toLower124() {
        assertEq(8580, StringDataUtil.toLowerCase((char)8579));
    }
    @Test
    public void toLower125() {
        assertEq(9424, StringDataUtil.toLowerCase((char)9398));
    }
    @Test
    public void toLower126() {
        assertEq(11312, StringDataUtil.toLowerCase((char)11264));
    }
    @Test
    public void toLower127() {
        assertEq(11361, StringDataUtil.toLowerCase((char)11360));
    }
    @Test
    public void toLower128() {
        assertEq(619, StringDataUtil.toLowerCase((char)11362));
    }
    @Test
    public void toLower129() {
        assertEq(7549, StringDataUtil.toLowerCase((char)11363));
    }
    @Test
    public void toLower130() {
        assertEq(637, StringDataUtil.toLowerCase((char)11364));
    }
    @Test
    public void toLower131() {
        assertEq(11368, StringDataUtil.toLowerCase((char)11367));
    }
    @Test
    public void toLower132() {
        assertEq(593, StringDataUtil.toLowerCase((char)11373));
    }
    @Test
    public void toLower133() {
        assertEq(625, StringDataUtil.toLowerCase((char)11374));
    }
    @Test
    public void toLower134() {
        assertEq(592, StringDataUtil.toLowerCase((char)11375));
    }
    @Test
    public void toLower135() {
        assertEq(594, StringDataUtil.toLowerCase((char)11376));
    }
    @Test
    public void toLower136() {
        assertEq(11379, StringDataUtil.toLowerCase((char)11378));
    }
    @Test
    public void toLower137() {
        assertEq(11382, StringDataUtil.toLowerCase((char)11381));
    }
    @Test
    public void toLower138() {
        assertEq(575, StringDataUtil.toLowerCase((char)11390));
    }
    @Test
    public void toLower139() {
        assertEq(11393, StringDataUtil.toLowerCase((char)11392));
    }
    @Test
    public void toLower140() {
        assertEq(11500, StringDataUtil.toLowerCase((char)11499));
    }
    @Test
    public void toLower141() {
        assertEq(11507, StringDataUtil.toLowerCase((char)11506));
    }
    @Test
    public void toLower142() {
        assertEq(42561, StringDataUtil.toLowerCase((char)42560));
    }
    @Test
    public void toLower143() {
        assertEq(42625, StringDataUtil.toLowerCase((char)42624));
    }
    @Test
    public void toLower144() {
        assertEq(42787, StringDataUtil.toLowerCase((char)42786));
    }
    @Test
    public void toLower145() {
        assertEq(42803, StringDataUtil.toLowerCase((char)42802));
    }
    @Test
    public void toLower146() {
        assertEq(42874, StringDataUtil.toLowerCase((char)42873));
    }
    @Test
    public void toLower147() {
        assertEq(7545, StringDataUtil.toLowerCase((char)42877));
    }
    @Test
    public void toLower148() {
        assertEq(42879, StringDataUtil.toLowerCase((char)42878));
    }
    @Test
    public void toLower149() {
        assertEq(42892, StringDataUtil.toLowerCase((char)42891));
    }
    @Test
    public void toLower150() {
        assertEq(613, StringDataUtil.toLowerCase((char)42893));
    }
    @Test
    public void toLower151() {
        assertEq(42897, StringDataUtil.toLowerCase((char)42896));
    }
    @Test
    public void toLower152() {
        assertEq(42913, StringDataUtil.toLowerCase((char)42912));
    }
    @Test
    public void toLower153() {
        assertEq(614, StringDataUtil.toLowerCase((char)42922));
    }
    @Test
    public void toLower154() {
        assertEq(65345, StringDataUtil.toLowerCase((char)65313));
    }
    @Test
    public void toLower155() {
        assertEq(65339, StringDataUtil.toLowerCase((char)65339));
    }
    @Test
    public void toLower156() {
        assertEq(65340, StringDataUtil.toLowerCase((char)65340));
    }
    @Test
    public void toLower157() {
        assertEq(1, StringDataUtil.toLowerCase((char)1));
    }
    @Test
    public void toUpper1() {
        assertEq(65, StringDataUtil.toUpperCase((char)97));
    }
    @Test
    public void toUpper2() {
        assertEq(924, StringDataUtil.toUpperCase((char)181));
    }
    @Test
    public void toUpper3() {
        assertEq(192, StringDataUtil.toUpperCase((char)224));
    }
    @Test
    public void toUpper4() {
        assertEq(216, StringDataUtil.toUpperCase((char)248));
    }
    @Test
    public void toUpper5() {
        assertEq(376, StringDataUtil.toUpperCase((char)255));
    }
    @Test
    public void toUpper6() {
        assertEq(256, StringDataUtil.toUpperCase((char)257));
    }
    @Test
    public void toUpper7() {
        assertEq(73, StringDataUtil.toUpperCase((char)305));
    }
    @Test
    public void toUpper8() {
        assertEq(306, StringDataUtil.toUpperCase((char)307));
    }
    @Test
    public void toUpper9() {
        assertEq(313, StringDataUtil.toUpperCase((char)314));
    }
    @Test
    public void toUpper10() {
        assertEq(330, StringDataUtil.toUpperCase((char)331));
    }
    @Test
    public void toUpper11() {
        assertEq(377, StringDataUtil.toUpperCase((char)378));
    }
    @Test
    public void toUpper12() {
        assertEq(579, StringDataUtil.toUpperCase((char)384));
    }
    @Test
    public void toUpper13() {
        assertEq(386, StringDataUtil.toUpperCase((char)387));
    }
    @Test
    public void toUpper14() {
        assertEq(391, StringDataUtil.toUpperCase((char)392));
    }
    @Test
    public void toUpper15() {
        assertEq(395, StringDataUtil.toUpperCase((char)396));
    }
    @Test
    public void toUpper16() {
        assertEq(401, StringDataUtil.toUpperCase((char)402));
    }
    @Test
    public void toUpper17() {
        assertEq(502, StringDataUtil.toUpperCase((char)405));
    }
    @Test
    public void toUpper18() {
        assertEq(408, StringDataUtil.toUpperCase((char)409));
    }
    @Test
    public void toUpper19() {
        assertEq(544, StringDataUtil.toUpperCase((char)414));
    }
    @Test
    public void toUpper20() {
        assertEq(416, StringDataUtil.toUpperCase((char)417));
    }
    @Test
    public void toUpper21() {
        assertEq(423, StringDataUtil.toUpperCase((char)424));
    }
    @Test
    public void toUpper22() {
        assertEq(428, StringDataUtil.toUpperCase((char)429));
    }
    @Test
    public void toUpper23() {
        assertEq(431, StringDataUtil.toUpperCase((char)432));
    }
    @Test
    public void toUpper24() {
        assertEq(435, StringDataUtil.toUpperCase((char)436));
    }
    @Test
    public void toUpper25() {
        assertEq(440, StringDataUtil.toUpperCase((char)441));
    }
    @Test
    public void toUpper26() {
        assertEq(444, StringDataUtil.toUpperCase((char)445));
    }
    @Test
    public void toUpper27() {
        assertEq(503, StringDataUtil.toUpperCase((char)447));
    }
    @Test
    public void toUpper28() {
        assertEq(452, StringDataUtil.toUpperCase((char)453));
    }
    @Test
    public void toUpper29() {
        assertEq(455, StringDataUtil.toUpperCase((char)456));
    }
    @Test
    public void toUpper30() {
        assertEq(458, StringDataUtil.toUpperCase((char)459));
    }
    @Test
    public void toUpper31() {
        assertEq(461, StringDataUtil.toUpperCase((char)462));
    }
    @Test
    public void toUpper32() {
        assertEq(478, StringDataUtil.toUpperCase((char)479));
    }
    @Test
    public void toUpper33() {
        assertEq(497, StringDataUtil.toUpperCase((char)498));
    }
    @Test
    public void toUpper34() {
        assertEq(500, StringDataUtil.toUpperCase((char)501));
    }
    @Test
    public void toUpper35() {
        assertEq(504, StringDataUtil.toUpperCase((char)505));
    }
    @Test
    public void toUpper36() {
        assertEq(546, StringDataUtil.toUpperCase((char)547));
    }
    @Test
    public void toUpper37() {
        assertEq(571, StringDataUtil.toUpperCase((char)572));
    }
    @Test
    public void toUpper38() {
        assertEq(11390, StringDataUtil.toUpperCase((char)575));
    }
    @Test
    public void toUpper39() {
        assertEq(577, StringDataUtil.toUpperCase((char)578));
    }
    @Test
    public void toUpper40() {
        assertEq(582, StringDataUtil.toUpperCase((char)583));
    }
    @Test
    public void toUpper41() {
        assertEq(11373, StringDataUtil.toUpperCase((char)593));
    }
    @Test
    public void toUpper42() {
        assertEq(11376, StringDataUtil.toUpperCase((char)594));
    }
    @Test
    public void toUpper43() {
        assertEq(385, StringDataUtil.toUpperCase((char)595));
    }
    @Test
    public void toUpper44() {
        assertEq(390, StringDataUtil.toUpperCase((char)596));
    }
    @Test
    public void toUpper45() {
        assertEq(393, StringDataUtil.toUpperCase((char)598));
    }
    @Test
    public void toUpper46() {
        assertEq(399, StringDataUtil.toUpperCase((char)601));
    }
    @Test
    public void toUpper47() {
        assertEq(400, StringDataUtil.toUpperCase((char)603));
    }
    @Test
    public void toUpper48() {
        assertEq(403, StringDataUtil.toUpperCase((char)608));
    }
    @Test
    public void toUpper49() {
        assertEq(404, StringDataUtil.toUpperCase((char)611));
    }
    @Test
    public void toUpper50() {
        assertEq(42893, StringDataUtil.toUpperCase((char)613));
    }
    @Test
    public void toUpper51() {
        assertEq(42922, StringDataUtil.toUpperCase((char)614));
    }
    @Test
    public void toUpper52() {
        assertEq(407, StringDataUtil.toUpperCase((char)616));
    }
    @Test
    public void toUpper53() {
        assertEq(406, StringDataUtil.toUpperCase((char)617));
    }
    @Test
    public void toUpper54() {
        assertEq(11362, StringDataUtil.toUpperCase((char)619));
    }
    @Test
    public void toUpper55() {
        assertEq(412, StringDataUtil.toUpperCase((char)623));
    }
    @Test
    public void toUpper56() {
        assertEq(11374, StringDataUtil.toUpperCase((char)625));
    }
    @Test
    public void toUpper57() {
        assertEq(413, StringDataUtil.toUpperCase((char)626));
    }
    @Test
    public void toUpper58() {
        assertEq(415, StringDataUtil.toUpperCase((char)629));
    }
    @Test
    public void toUpper59() {
        assertEq(11364, StringDataUtil.toUpperCase((char)637));
    }
    @Test
    public void toUpper60() {
        assertEq(422, StringDataUtil.toUpperCase((char)640));
    }
    @Test
    public void toUpper61() {
        assertEq(425, StringDataUtil.toUpperCase((char)643));
    }
    @Test
    public void toUpper62() {
        assertEq(430, StringDataUtil.toUpperCase((char)648));
    }
    @Test
    public void toUpper63() {
        assertEq(580, StringDataUtil.toUpperCase((char)649));
    }
    @Test
    public void toUpper64() {
        assertEq(433, StringDataUtil.toUpperCase((char)650));
    }
    @Test
    public void toUpper65() {
        assertEq(581, StringDataUtil.toUpperCase((char)652));
    }
    @Test
    public void toUpper66() {
        assertEq(439, StringDataUtil.toUpperCase((char)658));
    }
    @Test
    public void toUpper67() {
        assertEq(921, StringDataUtil.toUpperCase((char)837));
    }
    @Test
    public void toUpper68() {
        assertEq(880, StringDataUtil.toUpperCase((char)881));
    }
    @Test
    public void toUpper69() {
        assertEq(886, StringDataUtil.toUpperCase((char)887));
    }
    @Test
    public void toUpper70() {
        assertEq(1021, StringDataUtil.toUpperCase((char)891));
    }
    @Test
    public void toUpper71() {
        assertEq(902, StringDataUtil.toUpperCase((char)940));
    }
    @Test
    public void toUpper72() {
        assertEq(904, StringDataUtil.toUpperCase((char)941));
    }
    @Test
    public void toUpper73() {
        assertEq(913, StringDataUtil.toUpperCase((char)945));
    }
    @Test
    public void toUpper74() {
        assertEq(931, StringDataUtil.toUpperCase((char)962));
    }
    @Test
    public void toUpper75() {
        assertEq(931, StringDataUtil.toUpperCase((char)963));
    }
    @Test
    public void toUpper76() {
        assertEq(908, StringDataUtil.toUpperCase((char)972));
    }
    @Test
    public void toUpper77() {
        assertEq(910, StringDataUtil.toUpperCase((char)973));
    }
    @Test
    public void toUpper78() {
        assertEq(914, StringDataUtil.toUpperCase((char)976));
    }
    @Test
    public void toUpper79() {
        assertEq(920, StringDataUtil.toUpperCase((char)977));
    }
    @Test
    public void toUpper80() {
        assertEq(934, StringDataUtil.toUpperCase((char)981));
    }
    @Test
    public void toUpper81() {
        assertEq(928, StringDataUtil.toUpperCase((char)982));
    }
    @Test
    public void toUpper82() {
        assertEq(975, StringDataUtil.toUpperCase((char)983));
    }
    @Test
    public void toUpper83() {
        assertEq(984, StringDataUtil.toUpperCase((char)985));
    }
    @Test
    public void toUpper84() {
        assertEq(929, StringDataUtil.toUpperCase((char)1009));
    }
    @Test
    public void toUpper85() {
        assertEq(1017, StringDataUtil.toUpperCase((char)1010));
    }
    @Test
    public void toUpper86() {
        assertEq(917, StringDataUtil.toUpperCase((char)1013));
    }
    @Test
    public void toUpper87() {
        assertEq(1015, StringDataUtil.toUpperCase((char)1016));
    }
    @Test
    public void toUpper88() {
        assertEq(1018, StringDataUtil.toUpperCase((char)1019));
    }
    @Test
    public void toUpper89() {
        assertEq(1040, StringDataUtil.toUpperCase((char)1072));
    }
    @Test
    public void toUpper90() {
        assertEq(1024, StringDataUtil.toUpperCase((char)1104));
    }
    @Test
    public void toUpper91() {
        assertEq(1120, StringDataUtil.toUpperCase((char)1121));
    }
    @Test
    public void toUpper92() {
        assertEq(1162, StringDataUtil.toUpperCase((char)1163));
    }
    @Test
    public void toUpper93() {
        assertEq(1217, StringDataUtil.toUpperCase((char)1218));
    }
    @Test
    public void toUpper94() {
        assertEq(1232, StringDataUtil.toUpperCase((char)1233));
    }
    @Test
    public void toUpper95() {
        assertEq(1329, StringDataUtil.toUpperCase((char)1377));
    }
    @Test
    public void toUpper96() {
        assertEq(42877, StringDataUtil.toUpperCase((char)7545));
    }
    @Test
    public void toUpper97() {
        assertEq(11363, StringDataUtil.toUpperCase((char)7549));
    }
    @Test
    public void toUpper98() {
        assertEq(7680, StringDataUtil.toUpperCase((char)7681));
    }
    @Test
    public void toUpper99() {
        assertEq(7776, StringDataUtil.toUpperCase((char)7835));
    }
    @Test
    public void toUpper100() {
        assertEq(7840, StringDataUtil.toUpperCase((char)7841));
    }
    @Test
    public void toUpper101() {
        assertEq(7945, StringDataUtil.toUpperCase((char)7937));
    }
    @Test
    public void toUpper102() {
        assertEq(7960, StringDataUtil.toUpperCase((char)7952));
    }
    @Test
    public void toUpper103() {
        assertEq(7976, StringDataUtil.toUpperCase((char)7968));
    }
    @Test
    public void toUpper104() {
        assertEq(7992, StringDataUtil.toUpperCase((char)7984));
    }
    @Test
    public void toUpper105() {
        assertEq(8008, StringDataUtil.toUpperCase((char)8000));
    }
    @Test
    public void toUpper106() {
        assertEq(8025, StringDataUtil.toUpperCase((char)8017));
    }
    @Test
    public void toUpper107() {
        assertEq(8027, StringDataUtil.toUpperCase((char)8019));
    }
    @Test
    public void toUpper108() {
        assertEq(8029, StringDataUtil.toUpperCase((char)8021));
    }
    @Test
    public void toUpper109() {
        assertEq(8031, StringDataUtil.toUpperCase((char)8023));
    }
    @Test
    public void toUpper110() {
        assertEq(8040, StringDataUtil.toUpperCase((char)8032));
    }
    @Test
    public void toUpper111() {
        assertEq(8122, StringDataUtil.toUpperCase((char)8048));
    }
    @Test
    public void toUpper112() {
        assertEq(8136, StringDataUtil.toUpperCase((char)8050));
    }
    @Test
    public void toUpper113() {
        assertEq(8154, StringDataUtil.toUpperCase((char)8054));
    }
    @Test
    public void toUpper114() {
        assertEq(8184, StringDataUtil.toUpperCase((char)8056));
    }
    @Test
    public void toUpper115() {
        assertEq(8170, StringDataUtil.toUpperCase((char)8058));
    }
    @Test
    public void toUpper116() {
        assertEq(8186, StringDataUtil.toUpperCase((char)8060));
    }
    @Test
    public void toUpper117() {
        assertEq(8072, StringDataUtil.toUpperCase((char)8064));
    }
    @Test
    public void toUpper118() {
        assertEq(8088, StringDataUtil.toUpperCase((char)8080));
    }
    @Test
    public void toUpper119() {
        assertEq(8104, StringDataUtil.toUpperCase((char)8096));
    }
    @Test
    public void toUpper120() {
        assertEq(8120, StringDataUtil.toUpperCase((char)8112));
    }
    @Test
    public void toUpper121() {
        assertEq(8124, StringDataUtil.toUpperCase((char)8115));
    }
    @Test
    public void toUpper122() {
        assertEq(921, StringDataUtil.toUpperCase((char)8126));
    }
    @Test
    public void toUpper123() {
        assertEq(8140, StringDataUtil.toUpperCase((char)8131));
    }
    @Test
    public void toUpper124() {
        assertEq(8152, StringDataUtil.toUpperCase((char)8144));
    }
    @Test
    public void toUpper125() {
        assertEq(8168, StringDataUtil.toUpperCase((char)8160));
    }
    @Test
    public void toUpper126() {
        assertEq(8172, StringDataUtil.toUpperCase((char)8165));
    }
    @Test
    public void toUpper127() {
        assertEq(8188, StringDataUtil.toUpperCase((char)8179));
    }
    @Test
    public void toUpper128() {
        assertEq(8498, StringDataUtil.toUpperCase((char)8526));
    }
    @Test
    public void toUpper129() {
        assertEq(8544, StringDataUtil.toUpperCase((char)8560));
    }
    @Test
    public void toUpper130() {
        assertEq(8579, StringDataUtil.toUpperCase((char)8580));
    }
    @Test
    public void toUpper131() {
        assertEq(9398, StringDataUtil.toUpperCase((char)9424));
    }
    @Test
    public void toUpper132() {
        assertEq(11264, StringDataUtil.toUpperCase((char)11312));
    }
    @Test
    public void toUpper133() {
        assertEq(11360, StringDataUtil.toUpperCase((char)11361));
    }
    @Test
    public void toUpper134() {
        assertEq(570, StringDataUtil.toUpperCase((char)11365));
    }
    @Test
    public void toUpper135() {
        assertEq(574, StringDataUtil.toUpperCase((char)11366));
    }
    @Test
    public void toUpper136() {
        assertEq(11367, StringDataUtil.toUpperCase((char)11368));
    }
    @Test
    public void toUpper137() {
        assertEq(11378, StringDataUtil.toUpperCase((char)11379));
    }
    @Test
    public void toUpper138() {
        assertEq(11381, StringDataUtil.toUpperCase((char)11382));
    }
    @Test
    public void toUpper139() {
        assertEq(11392, StringDataUtil.toUpperCase((char)11393));
    }
    @Test
    public void toUpper140() {
        assertEq(11499, StringDataUtil.toUpperCase((char)11500));
    }
    @Test
    public void toUpper141() {
        assertEq(11506, StringDataUtil.toUpperCase((char)11507));
    }
    @Test
    public void toUpper142() {
        assertEq(4256, StringDataUtil.toUpperCase((char)11520));
    }
    @Test
    public void toUpper143() {
        assertEq(4295, StringDataUtil.toUpperCase((char)11559));
    }
    @Test
    public void toUpper144() {
        assertEq(4301, StringDataUtil.toUpperCase((char)11565));
    }
    @Test
    public void toUpper145() {
        assertEq(42560, StringDataUtil.toUpperCase((char)42561));
    }
    @Test
    public void toUpper146() {
        assertEq(42624, StringDataUtil.toUpperCase((char)42625));
    }
    @Test
    public void toUpper147() {
        assertEq(42786, StringDataUtil.toUpperCase((char)42787));
    }
    @Test
    public void toUpper148() {
        assertEq(42802, StringDataUtil.toUpperCase((char)42803));
    }
    @Test
    public void toUpper149() {
        assertEq(42873, StringDataUtil.toUpperCase((char)42874));
    }
    @Test
    public void toUpper150() {
        assertEq(42878, StringDataUtil.toUpperCase((char)42879));
    }
    @Test
    public void toUpper151() {
        assertEq(42891, StringDataUtil.toUpperCase((char)42892));
    }
    @Test
    public void toUpper152() {
        assertEq(42896, StringDataUtil.toUpperCase((char)42897));
    }
    @Test
    public void toUpper153() {
        assertEq(42912, StringDataUtil.toUpperCase((char)42913));
    }
    @Test
    public void toUpper154() {
        assertEq(65313, StringDataUtil.toUpperCase((char)65345));
    }
    @Test
    public void toUpper155() {
        assertEq(65371, StringDataUtil.toUpperCase((char)65371));
    }
    @Test
    public void toUpper156() {
        assertEq(42878, StringDataUtil.toUpperCase((char)42878));
    }
    @Test
    public void toUpper157() {
        assertEq(1, StringDataUtil.toUpperCase((char)1));
    }
    @Test
    public void toUpper158() {
        assertEq(83, StringDataUtil.toUpperCase((char)383));
    }
    @Test
    public void isLower1() {
        assertTrue(!StringDataUtil.isLowerCase((char)1));
    }
    @Test
    public void isLower2() {
        assertTrue(!StringDataUtil.isLowerCase((char)65));
    }
    @Test
    public void isLower3() {
        assertTrue(StringDataUtil.isLowerCase((char)97));
    }
    @Test
    public void isUpper1() {
        assertTrue(!StringDataUtil.isUpperCase((char)1));
    }
    @Test
    public void isUpper2() {
        assertTrue(StringDataUtil.isUpperCase((char)65));
    }
    @Test
    public void isUpper3() {
        assertTrue(!StringDataUtil.isUpperCase((char)97));
    }
    @Test
    public void digit1() {
        assertEq(-1, StringDataUtil.digit('0',1));
    }
    @Test
    public void digit2() {
        assertEq(-1, StringDataUtil.digit('0',37));
    }
    @Test
    public void digit3() {
        assertEq(-1, StringDataUtil.digit('8',5));
    }
    @Test
    public void digit4() {
        assertEq(8, StringDataUtil.digit('8',9));
    }
    @Test
    public void digit5() {
        assertEq(-1, StringDataUtil.digit('a',9));
    }
    @Test
    public void digit6() {
        assertEq(10, StringDataUtil.digit('a',11));
    }
    @Test
    public void digit7() {
        assertEq(-1, StringDataUtil.digit('A',9));
    }
    @Test
    public void digit8() {
        assertEq(10, StringDataUtil.digit('A',11));
    }
    @Test
    public void digit9() {
        assertEq(-1, StringDataUtil.digit('!',36));
    }
    @Test
    public void digit10() {
        assertEq(-1, StringDataUtil.digit('|',36));
    }
    @Test
    public void forDigit1() {
        assertEq(0, StringDataUtil.forDigit(0,1));
    }
    @Test
    public void forDigit2() {
        assertEq(0, StringDataUtil.forDigit(0,37));
    }
    @Test
    public void forDigit3() {
        assertEq(0, StringDataUtil.forDigit(8,5));
    }
    @Test
    public void forDigit4() {
        assertEq(0, StringDataUtil.forDigit(-1,2));
    }
    @Test
    public void forDigit5() {
        assertEq('1', StringDataUtil.forDigit(1,9));
    }
    @Test
    public void forDigit6() {
        assertEq('a', StringDataUtil.forDigit(10,11));
    }
    @Test
    public void forDigit7() {
        assertEq('b', StringDataUtil.forDigit(11,12));
    }
    @Test
    public void isUp1(){
        assertTrue(StringDataUtil.isUpperCase((char) 65));
    }
    @Test
    public void isUp2(){
        assertTrue(StringDataUtil.isUpperCase((char) 90));
    }
    @Test
    public void isUp3(){
        assertTrue(StringDataUtil.isUpperCase((char) 192));
    }
    @Test
    public void isUp4(){
        assertTrue(StringDataUtil.isUpperCase((char) 214));
    }
    @Test
    public void isUp5(){
        assertTrue(StringDataUtil.isUpperCase((char) 216));
    }
    @Test
    public void isUp6(){
        assertTrue(StringDataUtil.isUpperCase((char) 222));
    }
    @Test
    public void isUp7(){
        assertTrue(StringDataUtil.isUpperCase((char) 256));
    }
    @Test
    public void isUp8(){
        assertTrue(StringDataUtil.isUpperCase((char) 258));
    }
    @Test
    public void isUp9(){
        assertTrue(StringDataUtil.isUpperCase((char) 260));
    }
    @Test
    public void isUp10(){
        assertTrue(StringDataUtil.isUpperCase((char) 262));
    }
    @Test
    public void isUp11(){
        assertTrue(StringDataUtil.isUpperCase((char) 264));
    }
    @Test
    public void isUp12(){
        assertTrue(StringDataUtil.isUpperCase((char) 266));
    }
    @Test
    public void isUp13(){
        assertTrue(StringDataUtil.isUpperCase((char) 268));
    }
    @Test
    public void isUp14(){
        assertTrue(StringDataUtil.isUpperCase((char) 270));
    }
    @Test
    public void isUp15(){
        assertTrue(StringDataUtil.isUpperCase((char) 272));
    }
    @Test
    public void isUp16(){
        assertTrue(StringDataUtil.isUpperCase((char) 274));
    }
    @Test
    public void isUp17(){
        assertTrue(StringDataUtil.isUpperCase((char) 276));
    }
    @Test
    public void isUp18(){
        assertTrue(StringDataUtil.isUpperCase((char) 278));
    }
    @Test
    public void isUp19(){
        assertTrue(StringDataUtil.isUpperCase((char) 280));
    }
    @Test
    public void isUp20(){
        assertTrue(StringDataUtil.isUpperCase((char) 282));
    }
    @Test
    public void isUp21(){
        assertTrue(StringDataUtil.isUpperCase((char) 284));
    }
    @Test
    public void isUp22(){
        assertTrue(StringDataUtil.isUpperCase((char) 286));
    }
    @Test
    public void isUp23(){
        assertTrue(StringDataUtil.isUpperCase((char) 288));
    }
    @Test
    public void isUp24(){
        assertTrue(StringDataUtil.isUpperCase((char) 290));
    }
    @Test
    public void isUp25(){
        assertTrue(StringDataUtil.isUpperCase((char) 292));
    }
    @Test
    public void isUp26(){
        assertTrue(StringDataUtil.isUpperCase((char) 294));
    }
    @Test
    public void isUp27(){
        assertTrue(StringDataUtil.isUpperCase((char) 296));
    }
    @Test
    public void isUp28(){
        assertTrue(StringDataUtil.isUpperCase((char) 298));
    }
    @Test
    public void isUp29(){
        assertTrue(StringDataUtil.isUpperCase((char) 300));
    }
    @Test
    public void isUp30(){
        assertTrue(StringDataUtil.isUpperCase((char) 302));
    }
    @Test
    public void isUp31(){
        assertTrue(StringDataUtil.isUpperCase((char) 304));
    }
    @Test
    public void isUp32(){
        assertTrue(StringDataUtil.isUpperCase((char) 306));
    }
    @Test
    public void isUp33(){
        assertTrue(StringDataUtil.isUpperCase((char) 308));
    }
    @Test
    public void isUp34(){
        assertTrue(StringDataUtil.isUpperCase((char) 310));
    }
    @Test
    public void isUp35(){
        assertTrue(StringDataUtil.isUpperCase((char) 313));
    }
    @Test
    public void isUp36(){
        assertTrue(StringDataUtil.isUpperCase((char) 315));
    }
    @Test
    public void isUp37(){
        assertTrue(StringDataUtil.isUpperCase((char) 317));
    }
    @Test
    public void isUp38(){
        assertTrue(StringDataUtil.isUpperCase((char) 319));
    }
    @Test
    public void isUp39(){
        assertTrue(StringDataUtil.isUpperCase((char) 321));
    }
    @Test
    public void isUp40(){
        assertTrue(StringDataUtil.isUpperCase((char) 323));
    }
    @Test
    public void isUp41(){
        assertTrue(StringDataUtil.isUpperCase((char) 325));
    }
    @Test
    public void isUp42(){
        assertTrue(StringDataUtil.isUpperCase((char) 327));
    }
    @Test
    public void isUp43(){
        assertTrue(StringDataUtil.isUpperCase((char) 330));
    }
    @Test
    public void isUp44(){
        assertTrue(StringDataUtil.isUpperCase((char) 332));
    }
    @Test
    public void isUp45(){
        assertTrue(StringDataUtil.isUpperCase((char) 334));
    }
    @Test
    public void isUp46(){
        assertTrue(StringDataUtil.isUpperCase((char) 336));
    }
    @Test
    public void isUp47(){
        assertTrue(StringDataUtil.isUpperCase((char) 338));
    }
    @Test
    public void isUp48(){
        assertTrue(StringDataUtil.isUpperCase((char) 340));
    }
    @Test
    public void isUp49(){
        assertTrue(StringDataUtil.isUpperCase((char) 342));
    }
    @Test
    public void isUp50(){
        assertTrue(StringDataUtil.isUpperCase((char) 344));
    }
    @Test
    public void isUp51(){
        assertTrue(StringDataUtil.isUpperCase((char) 346));
    }
    @Test
    public void isUp52(){
        assertTrue(StringDataUtil.isUpperCase((char) 348));
    }
    @Test
    public void isUp53(){
        assertTrue(StringDataUtil.isUpperCase((char) 350));
    }
    @Test
    public void isUp54(){
        assertTrue(StringDataUtil.isUpperCase((char) 352));
    }
    @Test
    public void isUp55(){
        assertTrue(StringDataUtil.isUpperCase((char) 354));
    }
    @Test
    public void isUp56(){
        assertTrue(StringDataUtil.isUpperCase((char) 356));
    }
    @Test
    public void isUp57(){
        assertTrue(StringDataUtil.isUpperCase((char) 358));
    }
    @Test
    public void isUp58(){
        assertTrue(StringDataUtil.isUpperCase((char) 360));
    }
    @Test
    public void isUp59(){
        assertTrue(StringDataUtil.isUpperCase((char) 362));
    }
    @Test
    public void isUp60(){
        assertTrue(StringDataUtil.isUpperCase((char) 364));
    }
    @Test
    public void isUp61(){
        assertTrue(StringDataUtil.isUpperCase((char) 366));
    }
    @Test
    public void isUp62(){
        assertTrue(StringDataUtil.isUpperCase((char) 368));
    }
    @Test
    public void isUp63(){
        assertTrue(StringDataUtil.isUpperCase((char) 370));
    }
    @Test
    public void isUp64(){
        assertTrue(StringDataUtil.isUpperCase((char) 372));
    }
    @Test
    public void isUp65(){
        assertTrue(StringDataUtil.isUpperCase((char) 374));
    }
    @Test
    public void isUp66(){
        assertTrue(StringDataUtil.isUpperCase((char) 376));
    }
    @Test
    public void isUp67(){
        assertTrue(StringDataUtil.isUpperCase((char) 377));
    }
    @Test
    public void isUp68(){
        assertTrue(StringDataUtil.isUpperCase((char) 379));
    }
    @Test
    public void isUp69(){
        assertTrue(StringDataUtil.isUpperCase((char) 381));
    }
    @Test
    public void isUp70(){
        assertTrue(StringDataUtil.isUpperCase((char) 385));
    }
    @Test
    public void isUp71(){
        assertTrue(StringDataUtil.isUpperCase((char) 386));
    }
    @Test
    public void isUp72(){
        assertTrue(StringDataUtil.isUpperCase((char) 388));
    }
    @Test
    public void isUp73(){
        assertTrue(StringDataUtil.isUpperCase((char) 390));
    }
    @Test
    public void isUp74(){
        assertTrue(StringDataUtil.isUpperCase((char) 391));
    }
    @Test
    public void isUp75(){
        assertTrue(StringDataUtil.isUpperCase((char) 393));
    }
    @Test
    public void isUp76(){
        assertTrue(StringDataUtil.isUpperCase((char) 395));
    }
    @Test
    public void isUp77(){
        assertTrue(StringDataUtil.isUpperCase((char) 398));
    }
    @Test
    public void isUp78(){
        assertTrue(StringDataUtil.isUpperCase((char) 401));
    }
    @Test
    public void isUp79(){
        assertTrue(StringDataUtil.isUpperCase((char) 403));
    }
    @Test
    public void isUp80(){
        assertTrue(StringDataUtil.isUpperCase((char) 404));
    }
    @Test
    public void isUp81(){
        assertTrue(StringDataUtil.isUpperCase((char) 406));
    }
    @Test
    public void isUp82(){
        assertTrue(StringDataUtil.isUpperCase((char) 408));
    }
    @Test
    public void isUp83(){
        assertTrue(StringDataUtil.isUpperCase((char) 412));
    }
    @Test
    public void isUp84(){
        assertTrue(StringDataUtil.isUpperCase((char) 413));
    }
    @Test
    public void isUp85(){
        assertTrue(StringDataUtil.isUpperCase((char) 415));
    }
    @Test
    public void isUp86(){
        assertTrue(StringDataUtil.isUpperCase((char) 416));
    }
    @Test
    public void isUp87(){
        assertTrue(StringDataUtil.isUpperCase((char) 418));
    }
    @Test
    public void isUp88(){
        assertTrue(StringDataUtil.isUpperCase((char) 420));
    }
    @Test
    public void isUp89(){
        assertTrue(StringDataUtil.isUpperCase((char) 422));
    }
    @Test
    public void isUp90(){
        assertTrue(StringDataUtil.isUpperCase((char) 423));
    }
    @Test
    public void isUp91(){
        assertTrue(StringDataUtil.isUpperCase((char) 425));
    }
    @Test
    public void isUp92(){
        assertTrue(StringDataUtil.isUpperCase((char) 428));
    }
    @Test
    public void isUp93(){
        assertTrue(StringDataUtil.isUpperCase((char) 430));
    }
    @Test
    public void isUp94(){
        assertTrue(StringDataUtil.isUpperCase((char) 431));
    }
    @Test
    public void isUp95(){
        assertTrue(StringDataUtil.isUpperCase((char) 433));
    }
    @Test
    public void isUp96(){
        assertTrue(StringDataUtil.isUpperCase((char) 435));
    }
    @Test
    public void isUp97(){
        assertTrue(StringDataUtil.isUpperCase((char) 437));
    }
    @Test
    public void isUp98(){
        assertTrue(StringDataUtil.isUpperCase((char) 439));
    }
    @Test
    public void isUp99(){
        assertTrue(StringDataUtil.isUpperCase((char) 440));
    }
    @Test
    public void isUp100(){
        assertTrue(StringDataUtil.isUpperCase((char) 444));
    }
    @Test
    public void isUp101(){
        assertTrue(StringDataUtil.isUpperCase((char) 452));
    }
    @Test
    public void isUp102(){
        assertTrue(StringDataUtil.isUpperCase((char) 455));
    }
    @Test
    public void isUp103(){
        assertTrue(StringDataUtil.isUpperCase((char) 458));
    }
    @Test
    public void isUp104(){
        assertTrue(!StringDataUtil.isUpperCase((char) 459));
    }
    @Test
    public void isUp105(){
        assertTrue(StringDataUtil.isUpperCase((char) 461));
    }
    @Test
    public void isUp106(){
        assertTrue(StringDataUtil.isUpperCase((char) 463));
    }
    @Test
    public void isUp107(){
        assertTrue(StringDataUtil.isUpperCase((char) 465));
    }
    @Test
    public void isUp108(){
        assertTrue(StringDataUtil.isUpperCase((char) 467));
    }
    @Test
    public void isUp109(){
        assertTrue(StringDataUtil.isUpperCase((char) 469));
    }
    @Test
    public void isUp110(){
        assertTrue(StringDataUtil.isUpperCase((char) 471));
    }
    @Test
    public void isUp111(){
        assertTrue(StringDataUtil.isUpperCase((char) 473));
    }
    @Test
    public void isUp112(){
        assertTrue(StringDataUtil.isUpperCase((char) 475));
    }
    @Test
    public void isUp113(){
        assertTrue(StringDataUtil.isUpperCase((char) 478));
    }
    @Test
    public void isUp114(){
        assertTrue(StringDataUtil.isUpperCase((char) 480));
    }
    @Test
    public void isUp115(){
        assertTrue(StringDataUtil.isUpperCase((char) 482));
    }
    @Test
    public void isUp116(){
        assertTrue(StringDataUtil.isUpperCase((char) 484));
    }
    @Test
    public void isUp117(){
        assertTrue(StringDataUtil.isUpperCase((char) 486));
    }
    @Test
    public void isUp118(){
        assertTrue(StringDataUtil.isUpperCase((char) 488));
    }
    @Test
    public void isUp119(){
        assertTrue(StringDataUtil.isUpperCase((char) 490));
    }
    @Test
    public void isUp120(){
        assertTrue(StringDataUtil.isUpperCase((char) 492));
    }
    @Test
    public void isUp121(){
        assertTrue(StringDataUtil.isUpperCase((char) 494));
    }
    @Test
    public void isUp122(){
        assertTrue(StringDataUtil.isUpperCase((char) 497));
    }
    @Test
    public void isUp123(){
        assertTrue(!StringDataUtil.isUpperCase((char) 498));
    }
    @Test
    public void isUp124(){
        assertTrue(StringDataUtil.isUpperCase((char) 500));
    }
    @Test
    public void isUp125(){
        assertTrue(StringDataUtil.isUpperCase((char) 502));
    }
    @Test
    public void isUp126(){
        assertTrue(StringDataUtil.isUpperCase((char) 504));
    }
    @Test
    public void isUp127(){
        assertTrue(StringDataUtil.isUpperCase((char) 506));
    }
    @Test
    public void isUp128(){
        assertTrue(StringDataUtil.isUpperCase((char) 508));
    }
    @Test
    public void isUp129(){
        assertTrue(StringDataUtil.isUpperCase((char) 510));
    }
    @Test
    public void isUp130(){
        assertTrue(StringDataUtil.isUpperCase((char) 512));
    }
    @Test
    public void isUp131(){
        assertTrue(StringDataUtil.isUpperCase((char) 514));
    }
    @Test
    public void isUp132(){
        assertTrue(StringDataUtil.isUpperCase((char) 516));
    }
    @Test
    public void isUp133(){
        assertTrue(StringDataUtil.isUpperCase((char) 518));
    }
    @Test
    public void isUp134(){
        assertTrue(StringDataUtil.isUpperCase((char) 520));
    }
    @Test
    public void isUp135(){
        assertTrue(StringDataUtil.isUpperCase((char) 522));
    }
    @Test
    public void isUp136(){
        assertTrue(StringDataUtil.isUpperCase((char) 524));
    }
    @Test
    public void isUp137(){
        assertTrue(StringDataUtil.isUpperCase((char) 526));
    }
    @Test
    public void isUp138(){
        assertTrue(StringDataUtil.isUpperCase((char) 528));
    }
    @Test
    public void isUp139(){
        assertTrue(StringDataUtil.isUpperCase((char) 530));
    }
    @Test
    public void isUp140(){
        assertTrue(StringDataUtil.isUpperCase((char) 532));
    }
    @Test
    public void isUp141(){
        assertTrue(StringDataUtil.isUpperCase((char) 534));
    }
    @Test
    public void isUp142(){
        assertTrue(StringDataUtil.isUpperCase((char) 536));
    }
    @Test
    public void isUp143(){
        assertTrue(StringDataUtil.isUpperCase((char) 538));
    }
    @Test
    public void isUp144(){
        assertTrue(StringDataUtil.isUpperCase((char) 540));
    }
    @Test
    public void isUp145(){
        assertTrue(StringDataUtil.isUpperCase((char) 542));
    }
    @Test
    public void isUp146(){
        assertTrue(StringDataUtil.isUpperCase((char) 544));
    }
    @Test
    public void isUp147(){
        assertTrue(StringDataUtil.isUpperCase((char) 546));
    }
    @Test
    public void isUp148(){
        assertTrue(StringDataUtil.isUpperCase((char) 548));
    }
    @Test
    public void isUp149(){
        assertTrue(StringDataUtil.isUpperCase((char) 550));
    }
    @Test
    public void isUp150(){
        assertTrue(StringDataUtil.isUpperCase((char) 552));
    }
    @Test
    public void isUp151(){
        assertTrue(StringDataUtil.isUpperCase((char) 554));
    }
    @Test
    public void isUp152(){
        assertTrue(StringDataUtil.isUpperCase((char) 556));
    }
    @Test
    public void isUp153(){
        assertTrue(StringDataUtil.isUpperCase((char) 558));
    }
    @Test
    public void isUp154(){
        assertTrue(StringDataUtil.isUpperCase((char) 560));
    }
    @Test
    public void isUp155(){
        assertTrue(StringDataUtil.isUpperCase((char) 562));
    }
    @Test
    public void isUp156(){
        assertTrue(StringDataUtil.isUpperCase((char) 570));
    }
    @Test
    public void isUp157(){
        assertTrue(StringDataUtil.isUpperCase((char) 571));
    }
    @Test
    public void isUp158(){
        assertTrue(StringDataUtil.isUpperCase((char) 573));
    }
    @Test
    public void isUp159(){
        assertTrue(StringDataUtil.isUpperCase((char) 574));
    }
    @Test
    public void isUp160(){
        assertTrue(StringDataUtil.isUpperCase((char) 577));
    }
    @Test
    public void isUp161(){
        assertTrue(StringDataUtil.isUpperCase((char) 579));
    }
    @Test
    public void isUp162(){
        assertTrue(StringDataUtil.isUpperCase((char) 582));
    }
    @Test
    public void isUp163(){
        assertTrue(StringDataUtil.isUpperCase((char) 584));
    }
    @Test
    public void isUp164(){
        assertTrue(StringDataUtil.isUpperCase((char) 586));
    }
    @Test
    public void isUp165(){
        assertTrue(StringDataUtil.isUpperCase((char) 588));
    }
    @Test
    public void isUp166(){
        assertTrue(StringDataUtil.isUpperCase((char) 590));
    }
    @Test
    public void isUp167(){
        assertTrue(StringDataUtil.isUpperCase((char) 880));
    }
    @Test
    public void isUp168(){
        assertTrue(StringDataUtil.isUpperCase((char) 882));
    }
    @Test
    public void isUp169(){
        assertTrue(StringDataUtil.isUpperCase((char) 886));
    }
    @Test
    public void isUp170(){
        assertTrue(StringDataUtil.isUpperCase((char) 902));
    }
    @Test
    public void isUp171(){
        assertTrue(StringDataUtil.isUpperCase((char) 904));
    }
    @Test
    public void isUp172(){
        assertTrue(StringDataUtil.isUpperCase((char) 906));
    }
    @Test
    public void isUp173(){
        assertTrue(StringDataUtil.isUpperCase((char) 908));
    }
    @Test
    public void isUp174(){
        assertTrue(StringDataUtil.isUpperCase((char) 910));
    }
    @Test
    public void isUp175(){
        assertTrue(StringDataUtil.isUpperCase((char) 911));
    }
    @Test
    public void isUp176(){
        assertTrue(StringDataUtil.isUpperCase((char) 913));
    }
    @Test
    public void isUp177(){
        assertTrue(StringDataUtil.isUpperCase((char) 929));
    }
    @Test
    public void isUp178(){
        assertTrue(StringDataUtil.isUpperCase((char) 931));
    }
    @Test
    public void isUp179(){
        assertTrue(StringDataUtil.isUpperCase((char) 939));
    }
    @Test
    public void isUp180(){
        assertTrue(StringDataUtil.isUpperCase((char) 975));
    }
    @Test
    public void isUp181(){
        assertTrue(StringDataUtil.isUpperCase((char) 984));
    }
    @Test
    public void isUp182(){
        assertTrue(StringDataUtil.isUpperCase((char) 986));
    }
    @Test
    public void isUp183(){
        assertTrue(StringDataUtil.isUpperCase((char) 988));
    }
    @Test
    public void isUp184(){
        assertTrue(StringDataUtil.isUpperCase((char) 990));
    }
    @Test
    public void isUp185(){
        assertTrue(StringDataUtil.isUpperCase((char) 992));
    }
    @Test
    public void isUp186(){
        assertTrue(StringDataUtil.isUpperCase((char) 994));
    }
    @Test
    public void isUp187(){
        assertTrue(StringDataUtil.isUpperCase((char) 996));
    }
    @Test
    public void isUp188(){
        assertTrue(StringDataUtil.isUpperCase((char) 998));
    }
    @Test
    public void isUp189(){
        assertTrue(StringDataUtil.isUpperCase((char) 1000));
    }
    @Test
    public void isUp190(){
        assertTrue(StringDataUtil.isUpperCase((char) 1002));
    }
    @Test
    public void isUp191(){
        assertTrue(StringDataUtil.isUpperCase((char) 1004));
    }
    @Test
    public void isUp192(){
        assertTrue(StringDataUtil.isUpperCase((char) 1006));
    }
    @Test
    public void isUp193(){
        assertTrue(StringDataUtil.isUpperCase((char) 1012));
    }
    @Test
    public void isUp194(){
        assertTrue(StringDataUtil.isUpperCase((char) 1015));
    }
    @Test
    public void isUp195(){
        assertTrue(StringDataUtil.isUpperCase((char) 1017));
    }
    @Test
    public void isUp196(){
        assertTrue(StringDataUtil.isUpperCase((char) 1018));
    }
    @Test
    public void isUp197(){
        assertTrue(StringDataUtil.isUpperCase((char) 1021));
    }
    @Test
    public void isUp198(){
        assertTrue(StringDataUtil.isUpperCase((char) 1071));
    }
    @Test
    public void isUp199(){
        assertTrue(StringDataUtil.isUpperCase((char) 1120));
    }
    @Test
    public void isUp200(){
        assertTrue(StringDataUtil.isUpperCase((char) 1122));
    }
    @Test
    public void isUp201(){
        assertTrue(StringDataUtil.isUpperCase((char) 1124));
    }
    @Test
    public void isUp202(){
        assertTrue(StringDataUtil.isUpperCase((char) 1126));
    }
    @Test
    public void isUp203(){
        assertTrue(StringDataUtil.isUpperCase((char) 1128));
    }
    @Test
    public void isUp204(){
        assertTrue(StringDataUtil.isUpperCase((char) 1130));
    }
    @Test
    public void isUp205(){
        assertTrue(StringDataUtil.isUpperCase((char) 1132));
    }
    @Test
    public void isUp206(){
        assertTrue(StringDataUtil.isUpperCase((char) 1134));
    }
    @Test
    public void isUp207(){
        assertTrue(StringDataUtil.isUpperCase((char) 1136));
    }
    @Test
    public void isUp208(){
        assertTrue(StringDataUtil.isUpperCase((char) 1138));
    }
    @Test
    public void isUp209(){
        assertTrue(StringDataUtil.isUpperCase((char) 1140));
    }
    @Test
    public void isUp210(){
        assertTrue(StringDataUtil.isUpperCase((char) 1142));
    }
    @Test
    public void isUp211(){
        assertTrue(StringDataUtil.isUpperCase((char) 1144));
    }
    @Test
    public void isUp212(){
        assertTrue(StringDataUtil.isUpperCase((char) 1146));
    }
    @Test
    public void isUp213(){
        assertTrue(StringDataUtil.isUpperCase((char) 1148));
    }
    @Test
    public void isUp214(){
        assertTrue(StringDataUtil.isUpperCase((char) 1150));
    }
    @Test
    public void isUp215(){
        assertTrue(StringDataUtil.isUpperCase((char) 1152));
    }
    @Test
    public void isUp216(){
        assertTrue(StringDataUtil.isUpperCase((char) 1162));
    }
    @Test
    public void isUp217(){
        assertTrue(StringDataUtil.isUpperCase((char) 1164));
    }
    @Test
    public void isUp218(){
        assertTrue(StringDataUtil.isUpperCase((char) 1166));
    }
    @Test
    public void isUp219(){
        assertTrue(StringDataUtil.isUpperCase((char) 1168));
    }
    @Test
    public void isUp220(){
        assertTrue(StringDataUtil.isUpperCase((char) 1170));
    }
    @Test
    public void isUp221(){
        assertTrue(StringDataUtil.isUpperCase((char) 1172));
    }
    @Test
    public void isUp222(){
        assertTrue(StringDataUtil.isUpperCase((char) 1174));
    }
    @Test
    public void isUp223(){
        assertTrue(StringDataUtil.isUpperCase((char) 1176));
    }
    @Test
    public void isUp224(){
        assertTrue(StringDataUtil.isUpperCase((char) 1178));
    }
    @Test
    public void isUp225(){
        assertTrue(StringDataUtil.isUpperCase((char) 1180));
    }
    @Test
    public void isUp226(){
        assertTrue(StringDataUtil.isUpperCase((char) 1182));
    }
    @Test
    public void isUp227(){
        assertTrue(StringDataUtil.isUpperCase((char) 1184));
    }
    @Test
    public void isUp228(){
        assertTrue(StringDataUtil.isUpperCase((char) 1186));
    }
    @Test
    public void isUp229(){
        assertTrue(StringDataUtil.isUpperCase((char) 1188));
    }
    @Test
    public void isUp230(){
        assertTrue(StringDataUtil.isUpperCase((char) 1190));
    }
    @Test
    public void isUp231(){
        assertTrue(StringDataUtil.isUpperCase((char) 1192));
    }
    @Test
    public void isUp232(){
        assertTrue(StringDataUtil.isUpperCase((char) 1194));
    }
    @Test
    public void isUp233(){
        assertTrue(StringDataUtil.isUpperCase((char) 1196));
    }
    @Test
    public void isUp234(){
        assertTrue(StringDataUtil.isUpperCase((char) 1198));
    }
    @Test
    public void isUp235(){
        assertTrue(StringDataUtil.isUpperCase((char) 1200));
    }
    @Test
    public void isUp236(){
        assertTrue(StringDataUtil.isUpperCase((char) 1202));
    }
    @Test
    public void isUp237(){
        assertTrue(StringDataUtil.isUpperCase((char) 1204));
    }
    @Test
    public void isUp238(){
        assertTrue(StringDataUtil.isUpperCase((char) 1206));
    }
    @Test
    public void isUp239(){
        assertTrue(StringDataUtil.isUpperCase((char) 1208));
    }
    @Test
    public void isUp240(){
        assertTrue(StringDataUtil.isUpperCase((char) 1210));
    }
    @Test
    public void isUp241(){
        assertTrue(StringDataUtil.isUpperCase((char) 1212));
    }
    @Test
    public void isUp242(){
        assertTrue(StringDataUtil.isUpperCase((char) 1214));
    }
    @Test
    public void isUp243(){
        assertTrue(StringDataUtil.isUpperCase((char) 1216));
    }
    @Test
    public void isUp244(){
        assertTrue(StringDataUtil.isUpperCase((char) 1217));
    }
    @Test
    public void isUp245(){
        assertTrue(StringDataUtil.isUpperCase((char) 1219));
    }
    @Test
    public void isUp246(){
        assertTrue(StringDataUtil.isUpperCase((char) 1221));
    }
    @Test
    public void isUp247(){
        assertTrue(StringDataUtil.isUpperCase((char) 1223));
    }
    @Test
    public void isUp248(){
        assertTrue(StringDataUtil.isUpperCase((char) 1225));
    }
    @Test
    public void isUp249(){
        assertTrue(StringDataUtil.isUpperCase((char) 1227));
    }
    @Test
    public void isUp250(){
        assertTrue(StringDataUtil.isUpperCase((char) 1229));
    }
    @Test
    public void isUp251(){
        assertTrue(StringDataUtil.isUpperCase((char) 1232));
    }
    @Test
    public void isUp252(){
        assertTrue(StringDataUtil.isUpperCase((char) 1234));
    }
    @Test
    public void isUp253(){
        assertTrue(StringDataUtil.isUpperCase((char) 1236));
    }
    @Test
    public void isUp254(){
        assertTrue(StringDataUtil.isUpperCase((char) 1238));
    }
    @Test
    public void isUp255(){
        assertTrue(StringDataUtil.isUpperCase((char) 1240));
    }
    @Test
    public void isUp256(){
        assertTrue(StringDataUtil.isUpperCase((char) 1242));
    }
    @Test
    public void isUp257(){
        assertTrue(StringDataUtil.isUpperCase((char) 1244));
    }
    @Test
    public void isUp258(){
        assertTrue(StringDataUtil.isUpperCase((char) 1246));
    }
    @Test
    public void isUp259(){
        assertTrue(StringDataUtil.isUpperCase((char) 1248));
    }
    @Test
    public void isUp260(){
        assertTrue(StringDataUtil.isUpperCase((char) 1250));
    }
    @Test
    public void isUp261(){
        assertTrue(StringDataUtil.isUpperCase((char) 1252));
    }
    @Test
    public void isUp262(){
        assertTrue(StringDataUtil.isUpperCase((char) 1254));
    }
    @Test
    public void isUp263(){
        assertTrue(StringDataUtil.isUpperCase((char) 1256));
    }
    @Test
    public void isUp264(){
        assertTrue(StringDataUtil.isUpperCase((char) 1258));
    }
    @Test
    public void isUp265(){
        assertTrue(StringDataUtil.isUpperCase((char) 1260));
    }
    @Test
    public void isUp266(){
        assertTrue(StringDataUtil.isUpperCase((char) 1262));
    }
    @Test
    public void isUp267(){
        assertTrue(StringDataUtil.isUpperCase((char) 1264));
    }
    @Test
    public void isUp268(){
        assertTrue(StringDataUtil.isUpperCase((char) 1266));
    }
    @Test
    public void isUp269(){
        assertTrue(StringDataUtil.isUpperCase((char) 1268));
    }
    @Test
    public void isUp270(){
        assertTrue(StringDataUtil.isUpperCase((char) 1270));
    }
    @Test
    public void isUp271(){
        assertTrue(StringDataUtil.isUpperCase((char) 1272));
    }
    @Test
    public void isUp272(){
        assertTrue(StringDataUtil.isUpperCase((char) 1274));
    }
    @Test
    public void isUp273(){
        assertTrue(StringDataUtil.isUpperCase((char) 1276));
    }
    @Test
    public void isUp274(){
        assertTrue(StringDataUtil.isUpperCase((char) 1278));
    }
    @Test
    public void isUp275(){
        assertTrue(StringDataUtil.isUpperCase((char) 1280));
    }
    @Test
    public void isUp276(){
        assertTrue(StringDataUtil.isUpperCase((char) 1282));
    }
    @Test
    public void isUp277(){
        assertTrue(StringDataUtil.isUpperCase((char) 1284));
    }
    @Test
    public void isUp278(){
        assertTrue(StringDataUtil.isUpperCase((char) 1286));
    }
    @Test
    public void isUp279(){
        assertTrue(StringDataUtil.isUpperCase((char) 1288));
    }
    @Test
    public void isUp280(){
        assertTrue(StringDataUtil.isUpperCase((char) 1290));
    }
    @Test
    public void isUp281(){
        assertTrue(StringDataUtil.isUpperCase((char) 1292));
    }
    @Test
    public void isUp282(){
        assertTrue(StringDataUtil.isUpperCase((char) 1294));
    }
    @Test
    public void isUp283(){
        assertTrue(StringDataUtil.isUpperCase((char) 1296));
    }
    @Test
    public void isUp284(){
        assertTrue(StringDataUtil.isUpperCase((char) 1298));
    }
    @Test
    public void isUp285(){
        assertTrue(StringDataUtil.isUpperCase((char) 1300));
    }
    @Test
    public void isUp286(){
        assertTrue(StringDataUtil.isUpperCase((char) 1302));
    }
    @Test
    public void isUp287(){
        assertTrue(StringDataUtil.isUpperCase((char) 1304));
    }
    @Test
    public void isUp288(){
        assertTrue(StringDataUtil.isUpperCase((char) 1306));
    }
    @Test
    public void isUp289(){
        assertTrue(StringDataUtil.isUpperCase((char) 1308));
    }
    @Test
    public void isUp290(){
        assertTrue(StringDataUtil.isUpperCase((char) 1310));
    }
    @Test
    public void isUp291(){
        assertTrue(StringDataUtil.isUpperCase((char) 1312));
    }
    @Test
    public void isUp292(){
        assertTrue(StringDataUtil.isUpperCase((char) 1314));
    }
    @Test
    public void isUp293(){
        assertTrue(StringDataUtil.isUpperCase((char) 1316));
    }
    @Test
    public void isUp294(){
        assertTrue(StringDataUtil.isUpperCase((char) 1318));
    }
    @Test
    public void isUp295(){
        assertTrue(StringDataUtil.isUpperCase((char) 1329));
    }
    @Test
    public void isUp296(){
        assertTrue(StringDataUtil.isUpperCase((char) 1366));
    }
    @Test
    public void isUp297(){
        assertTrue(StringDataUtil.isUpperCase((char) 4256));
    }
    @Test
    public void isUp298(){
        assertTrue(StringDataUtil.isUpperCase((char) 4293));
    }
    @Test
    public void isUp299(){
        assertTrue(StringDataUtil.isUpperCase((char) 4295));
    }
    @Test
    public void isUp300(){
        assertTrue(StringDataUtil.isUpperCase((char) 4301));
    }
    @Test
    public void isUp301(){
        assertTrue(StringDataUtil.isUpperCase((char) 7680));
    }
    @Test
    public void isUp302(){
        assertTrue(StringDataUtil.isUpperCase((char) 7682));
    }
    @Test
    public void isUp303(){
        assertTrue(StringDataUtil.isUpperCase((char) 7684));
    }
    @Test
    public void isUp304(){
        assertTrue(StringDataUtil.isUpperCase((char) 7686));
    }
    @Test
    public void isUp305(){
        assertTrue(StringDataUtil.isUpperCase((char) 7688));
    }
    @Test
    public void isUp306(){
        assertTrue(StringDataUtil.isUpperCase((char) 7690));
    }
    @Test
    public void isUp307(){
        assertTrue(StringDataUtil.isUpperCase((char) 7692));
    }
    @Test
    public void isUp308(){
        assertTrue(StringDataUtil.isUpperCase((char) 7694));
    }
    @Test
    public void isUp309(){
        assertTrue(StringDataUtil.isUpperCase((char) 7696));
    }
    @Test
    public void isUp310(){
        assertTrue(StringDataUtil.isUpperCase((char) 7698));
    }
    @Test
    public void isUp311(){
        assertTrue(StringDataUtil.isUpperCase((char) 7700));
    }
    @Test
    public void isUp312(){
        assertTrue(StringDataUtil.isUpperCase((char) 7702));
    }
    @Test
    public void isUp313(){
        assertTrue(StringDataUtil.isUpperCase((char) 7704));
    }
    @Test
    public void isUp314(){
        assertTrue(StringDataUtil.isUpperCase((char) 7706));
    }
    @Test
    public void isUp315(){
        assertTrue(StringDataUtil.isUpperCase((char) 7708));
    }
    @Test
    public void isUp316(){
        assertTrue(StringDataUtil.isUpperCase((char) 7710));
    }
    @Test
    public void isUp317(){
        assertTrue(StringDataUtil.isUpperCase((char) 7712));
    }
    @Test
    public void isUp318(){
        assertTrue(StringDataUtil.isUpperCase((char) 7714));
    }
    @Test
    public void isUp319(){
        assertTrue(StringDataUtil.isUpperCase((char) 7716));
    }
    @Test
    public void isUp320(){
        assertTrue(StringDataUtil.isUpperCase((char) 7718));
    }
    @Test
    public void isUp321(){
        assertTrue(StringDataUtil.isUpperCase((char) 7720));
    }
    @Test
    public void isUp322(){
        assertTrue(StringDataUtil.isUpperCase((char) 7722));
    }
    @Test
    public void isUp323(){
        assertTrue(StringDataUtil.isUpperCase((char) 7724));
    }
    @Test
    public void isUp324(){
        assertTrue(StringDataUtil.isUpperCase((char) 7726));
    }
    @Test
    public void isUp325(){
        assertTrue(StringDataUtil.isUpperCase((char) 7728));
    }
    @Test
    public void isUp326(){
        assertTrue(StringDataUtil.isUpperCase((char) 7730));
    }
    @Test
    public void isUp327(){
        assertTrue(StringDataUtil.isUpperCase((char) 7732));
    }
    @Test
    public void isUp328(){
        assertTrue(StringDataUtil.isUpperCase((char) 7734));
    }
    @Test
    public void isUp329(){
        assertTrue(StringDataUtil.isUpperCase((char) 7736));
    }
    @Test
    public void isUp330(){
        assertTrue(StringDataUtil.isUpperCase((char) 7738));
    }
    @Test
    public void isUp331(){
        assertTrue(StringDataUtil.isUpperCase((char) 7740));
    }
    @Test
    public void isUp332(){
        assertTrue(StringDataUtil.isUpperCase((char) 7742));
    }
    @Test
    public void isUp333(){
        assertTrue(StringDataUtil.isUpperCase((char) 7744));
    }
    @Test
    public void isUp334(){
        assertTrue(StringDataUtil.isUpperCase((char) 7746));
    }
    @Test
    public void isUp335(){
        assertTrue(StringDataUtil.isUpperCase((char) 7748));
    }
    @Test
    public void isUp336(){
        assertTrue(StringDataUtil.isUpperCase((char) 7750));
    }
    @Test
    public void isUp337(){
        assertTrue(StringDataUtil.isUpperCase((char) 7752));
    }
    @Test
    public void isUp338(){
        assertTrue(StringDataUtil.isUpperCase((char) 7754));
    }
    @Test
    public void isUp339(){
        assertTrue(StringDataUtil.isUpperCase((char) 7756));
    }
    @Test
    public void isUp340(){
        assertTrue(StringDataUtil.isUpperCase((char) 7758));
    }
    @Test
    public void isUp341(){
        assertTrue(StringDataUtil.isUpperCase((char) 7760));
    }
    @Test
    public void isUp342(){
        assertTrue(StringDataUtil.isUpperCase((char) 7762));
    }
    @Test
    public void isUp343(){
        assertTrue(StringDataUtil.isUpperCase((char) 7764));
    }
    @Test
    public void isUp344(){
        assertTrue(StringDataUtil.isUpperCase((char) 7766));
    }
    @Test
    public void isUp345(){
        assertTrue(StringDataUtil.isUpperCase((char) 7768));
    }
    @Test
    public void isUp346(){
        assertTrue(StringDataUtil.isUpperCase((char) 7770));
    }
    @Test
    public void isUp347(){
        assertTrue(StringDataUtil.isUpperCase((char) 7772));
    }
    @Test
    public void isUp348(){
        assertTrue(StringDataUtil.isUpperCase((char) 7774));
    }
    @Test
    public void isUp349(){
        assertTrue(StringDataUtil.isUpperCase((char) 7776));
    }
    @Test
    public void isUp350(){
        assertTrue(StringDataUtil.isUpperCase((char) 7778));
    }
    @Test
    public void isUp351(){
        assertTrue(StringDataUtil.isUpperCase((char) 7780));
    }
    @Test
    public void isUp352(){
        assertTrue(StringDataUtil.isUpperCase((char) 7782));
    }
    @Test
    public void isUp353(){
        assertTrue(StringDataUtil.isUpperCase((char) 7784));
    }
    @Test
    public void isUp354(){
        assertTrue(StringDataUtil.isUpperCase((char) 7786));
    }
    @Test
    public void isUp355(){
        assertTrue(StringDataUtil.isUpperCase((char) 7788));
    }
    @Test
    public void isUp356(){
        assertTrue(StringDataUtil.isUpperCase((char) 7790));
    }
    @Test
    public void isUp357(){
        assertTrue(StringDataUtil.isUpperCase((char) 7792));
    }
    @Test
    public void isUp358(){
        assertTrue(StringDataUtil.isUpperCase((char) 7794));
    }
    @Test
    public void isUp359(){
        assertTrue(StringDataUtil.isUpperCase((char) 7796));
    }
    @Test
    public void isUp360(){
        assertTrue(StringDataUtil.isUpperCase((char) 7798));
    }
    @Test
    public void isUp361(){
        assertTrue(StringDataUtil.isUpperCase((char) 7800));
    }
    @Test
    public void isUp362(){
        assertTrue(StringDataUtil.isUpperCase((char) 7802));
    }
    @Test
    public void isUp363(){
        assertTrue(StringDataUtil.isUpperCase((char) 7804));
    }
    @Test
    public void isUp364(){
        assertTrue(StringDataUtil.isUpperCase((char) 7806));
    }
    @Test
    public void isUp365(){
        assertTrue(StringDataUtil.isUpperCase((char) 7808));
    }
    @Test
    public void isUp366(){
        assertTrue(StringDataUtil.isUpperCase((char) 7810));
    }
    @Test
    public void isUp367(){
        assertTrue(StringDataUtil.isUpperCase((char) 7812));
    }
    @Test
    public void isUp368(){
        assertTrue(StringDataUtil.isUpperCase((char) 7814));
    }
    @Test
    public void isUp369(){
        assertTrue(StringDataUtil.isUpperCase((char) 7816));
    }
    @Test
    public void isUp370(){
        assertTrue(StringDataUtil.isUpperCase((char) 7818));
    }
    @Test
    public void isUp371(){
        assertTrue(StringDataUtil.isUpperCase((char) 7820));
    }
    @Test
    public void isUp372(){
        assertTrue(StringDataUtil.isUpperCase((char) 7822));
    }
    @Test
    public void isUp373(){
        assertTrue(StringDataUtil.isUpperCase((char) 7824));
    }
    @Test
    public void isUp374(){
        assertTrue(StringDataUtil.isUpperCase((char) 7826));
    }
    @Test
    public void isUp375(){
        assertTrue(StringDataUtil.isUpperCase((char) 7828));
    }
    @Test
    public void isUp376(){
        assertTrue(StringDataUtil.isUpperCase((char) 7838));
    }
    @Test
    public void isUp377(){
        assertTrue(StringDataUtil.isUpperCase((char) 7840));
    }
    @Test
    public void isUp378(){
        assertTrue(StringDataUtil.isUpperCase((char) 7842));
    }
    @Test
    public void isUp379(){
        assertTrue(StringDataUtil.isUpperCase((char) 7844));
    }
    @Test
    public void isUp380(){
        assertTrue(StringDataUtil.isUpperCase((char) 7846));
    }
    @Test
    public void isUp381(){
        assertTrue(StringDataUtil.isUpperCase((char) 7848));
    }
    @Test
    public void isUp382(){
        assertTrue(StringDataUtil.isUpperCase((char) 7850));
    }
    @Test
    public void isUp383(){
        assertTrue(StringDataUtil.isUpperCase((char) 7852));
    }
    @Test
    public void isUp384(){
        assertTrue(StringDataUtil.isUpperCase((char) 7854));
    }
    @Test
    public void isUp385(){
        assertTrue(StringDataUtil.isUpperCase((char) 7856));
    }
    @Test
    public void isUp386(){
        assertTrue(StringDataUtil.isUpperCase((char) 7858));
    }
    @Test
    public void isUp387(){
        assertTrue(StringDataUtil.isUpperCase((char) 7860));
    }
    @Test
    public void isUp388(){
        assertTrue(StringDataUtil.isUpperCase((char) 7862));
    }
    @Test
    public void isUp389(){
        assertTrue(StringDataUtil.isUpperCase((char) 7864));
    }
    @Test
    public void isUp390(){
        assertTrue(StringDataUtil.isUpperCase((char) 7866));
    }
    @Test
    public void isUp391(){
        assertTrue(StringDataUtil.isUpperCase((char) 7868));
    }
    @Test
    public void isUp392(){
        assertTrue(StringDataUtil.isUpperCase((char) 7870));
    }
    @Test
    public void isUp393(){
        assertTrue(StringDataUtil.isUpperCase((char) 7872));
    }
    @Test
    public void isUp394(){
        assertTrue(StringDataUtil.isUpperCase((char) 7874));
    }
    @Test
    public void isUp395(){
        assertTrue(StringDataUtil.isUpperCase((char) 7876));
    }
    @Test
    public void isUp396(){
        assertTrue(StringDataUtil.isUpperCase((char) 7878));
    }
    @Test
    public void isUp397(){
        assertTrue(StringDataUtil.isUpperCase((char) 7880));
    }
    @Test
    public void isUp398(){
        assertTrue(StringDataUtil.isUpperCase((char) 7882));
    }
    @Test
    public void isUp399(){
        assertTrue(StringDataUtil.isUpperCase((char) 7884));
    }
    @Test
    public void isUp400(){
        assertTrue(StringDataUtil.isUpperCase((char) 7886));
    }
    @Test
    public void isUp401(){
        assertTrue(StringDataUtil.isUpperCase((char) 7888));
    }
    @Test
    public void isUp402(){
        assertTrue(StringDataUtil.isUpperCase((char) 7890));
    }
    @Test
    public void isUp403(){
        assertTrue(StringDataUtil.isUpperCase((char) 7892));
    }
    @Test
    public void isUp404(){
        assertTrue(StringDataUtil.isUpperCase((char) 7894));
    }
    @Test
    public void isUp405(){
        assertTrue(StringDataUtil.isUpperCase((char) 7896));
    }
    @Test
    public void isUp406(){
        assertTrue(StringDataUtil.isUpperCase((char) 7898));
    }
    @Test
    public void isUp407(){
        assertTrue(StringDataUtil.isUpperCase((char) 7900));
    }
    @Test
    public void isUp408(){
        assertTrue(StringDataUtil.isUpperCase((char) 7902));
    }
    @Test
    public void isUp409(){
        assertTrue(StringDataUtil.isUpperCase((char) 7904));
    }
    @Test
    public void isUp410(){
        assertTrue(StringDataUtil.isUpperCase((char) 7906));
    }
    @Test
    public void isUp411(){
        assertTrue(StringDataUtil.isUpperCase((char) 7908));
    }
    @Test
    public void isUp412(){
        assertTrue(StringDataUtil.isUpperCase((char) 7910));
    }
    @Test
    public void isUp413(){
        assertTrue(StringDataUtil.isUpperCase((char) 7912));
    }
    @Test
    public void isUp414(){
        assertTrue(StringDataUtil.isUpperCase((char) 7914));
    }
    @Test
    public void isUp415(){
        assertTrue(StringDataUtil.isUpperCase((char) 7916));
    }
    @Test
    public void isUp416(){
        assertTrue(StringDataUtil.isUpperCase((char) 7918));
    }
    @Test
    public void isUp417(){
        assertTrue(StringDataUtil.isUpperCase((char) 7920));
    }
    @Test
    public void isUp418(){
        assertTrue(StringDataUtil.isUpperCase((char) 7922));
    }
    @Test
    public void isUp419(){
        assertTrue(StringDataUtil.isUpperCase((char) 7924));
    }
    @Test
    public void isUp420(){
        assertTrue(StringDataUtil.isUpperCase((char) 7926));
    }
    @Test
    public void isUp421(){
        assertTrue(StringDataUtil.isUpperCase((char) 7928));
    }
    @Test
    public void isUp422(){
        assertTrue(StringDataUtil.isUpperCase((char) 7930));
    }
    @Test
    public void isUp423(){
        assertTrue(StringDataUtil.isUpperCase((char) 7932));
    }
    @Test
    public void isUp424(){
        assertTrue(StringDataUtil.isUpperCase((char) 7934));
    }
    @Test
    public void isUp425(){
        assertTrue(StringDataUtil.isUpperCase((char) 7944));
    }
    @Test
    public void isUp426(){
        assertTrue(StringDataUtil.isUpperCase((char) 7951));
    }
    @Test
    public void isUp427(){
        assertTrue(StringDataUtil.isUpperCase((char) 7960));
    }
    @Test
    public void isUp428(){
        assertTrue(StringDataUtil.isUpperCase((char) 7965));
    }
    @Test
    public void isUp429(){
        assertTrue(StringDataUtil.isUpperCase((char) 7976));
    }
    @Test
    public void isUp430(){
        assertTrue(StringDataUtil.isUpperCase((char) 7983));
    }
    @Test
    public void isUp431(){
        assertTrue(StringDataUtil.isUpperCase((char) 7992));
    }
    @Test
    public void isUp432(){
        assertTrue(StringDataUtil.isUpperCase((char) 7999));
    }
    @Test
    public void isUp433(){
        assertTrue(StringDataUtil.isUpperCase((char) 8008));
    }
    @Test
    public void isUp434(){
        assertTrue(StringDataUtil.isUpperCase((char) 8013));
    }
    @Test
    public void isUp435(){
        assertTrue(StringDataUtil.isUpperCase((char) 8025));
    }
    @Test
    public void isUp436(){
        assertTrue(StringDataUtil.isUpperCase((char) 8027));
    }
    @Test
    public void isUp437(){
        assertTrue(StringDataUtil.isUpperCase((char) 8029));
    }
    @Test
    public void isUp438(){
        assertTrue(StringDataUtil.isUpperCase((char) 8031));
    }
    @Test
    public void isUp439(){
        assertTrue(StringDataUtil.isUpperCase((char) 8040));
    }
    @Test
    public void isUp440(){
        assertTrue(StringDataUtil.isUpperCase((char) 8047));
    }
    @Test
    public void isUp441(){
        assertTrue(StringDataUtil.isUpperCase((char) 8120));
    }
    @Test
    public void isUp442(){
        assertTrue(StringDataUtil.isUpperCase((char) 8123));
    }
    @Test
    public void isUp443(){
        assertTrue(StringDataUtil.isUpperCase((char) 8136));
    }
    @Test
    public void isUp444(){
        assertTrue(StringDataUtil.isUpperCase((char) 8139));
    }
    @Test
    public void isUp445(){
        assertTrue(StringDataUtil.isUpperCase((char) 8152));
    }
    @Test
    public void isUp446(){
        assertTrue(StringDataUtil.isUpperCase((char) 8155));
    }
    @Test
    public void isUp447(){
        assertTrue(StringDataUtil.isUpperCase((char) 8168));
    }
    @Test
    public void isUp448(){
        assertTrue(StringDataUtil.isUpperCase((char) 8172));
    }
    @Test
    public void isUp449(){
        assertTrue(StringDataUtil.isUpperCase((char) 8184));
    }
    @Test
    public void isUp450(){
        assertTrue(StringDataUtil.isUpperCase((char) 8187));
    }
    @Test
    public void isUp451(){
        assertTrue(StringDataUtil.isUpperCase((char) 8486));
    }
    @Test
    public void isUp452(){
        assertTrue(StringDataUtil.isUpperCase((char) 8490));
    }
    @Test
    public void isUp453(){
        assertTrue(StringDataUtil.isUpperCase((char) 8491));
    }
    @Test
    public void isUp454(){
        assertTrue(StringDataUtil.isUpperCase((char) 8498));
    }
    @Test
    public void isUp455(){
        assertTrue(StringDataUtil.isUpperCase((char) 8579));
    }
    @Test
    public void isUp456(){
        assertTrue(StringDataUtil.isUpperCase((char) 11264));
    }
    @Test
    public void isUp457(){
        assertTrue(StringDataUtil.isUpperCase((char) 11310));
    }
    @Test
    public void isUp458(){
        assertTrue(StringDataUtil.isUpperCase((char) 11360));
    }
    @Test
    public void isUp459(){
        assertTrue(StringDataUtil.isUpperCase((char) 11362));
    }
    @Test
    public void isUp460(){
        assertTrue(StringDataUtil.isUpperCase((char) 11364));
    }
    @Test
    public void isUp461(){
        assertTrue(StringDataUtil.isUpperCase((char) 11367));
    }
    @Test
    public void isUp462(){
        assertTrue(StringDataUtil.isUpperCase((char) 11369));
    }
    @Test
    public void isUp463(){
        assertTrue(StringDataUtil.isUpperCase((char) 11371));
    }
    @Test
    public void isUp464(){
        assertTrue(StringDataUtil.isUpperCase((char) 11373));
    }
    @Test
    public void isUp465(){
        assertTrue(StringDataUtil.isUpperCase((char) 11376));
    }
    @Test
    public void isUp466(){
        assertTrue(StringDataUtil.isUpperCase((char) 11378));
    }
    @Test
    public void isUp467(){
        assertTrue(StringDataUtil.isUpperCase((char) 11381));
    }
    @Test
    public void isUp468(){
        assertTrue(StringDataUtil.isUpperCase((char) 11390));
    }
    @Test
    public void isUp469(){
        assertTrue(StringDataUtil.isUpperCase((char) 11392));
    }
    @Test
    public void isUp470(){
        assertTrue(StringDataUtil.isUpperCase((char) 11394));
    }
    @Test
    public void isUp471(){
        assertTrue(StringDataUtil.isUpperCase((char) 11396));
    }
    @Test
    public void isUp472(){
        assertTrue(StringDataUtil.isUpperCase((char) 11398));
    }
    @Test
    public void isUp473(){
        assertTrue(StringDataUtil.isUpperCase((char) 11400));
    }
    @Test
    public void isUp474(){
        assertTrue(StringDataUtil.isUpperCase((char) 11402));
    }
    @Test
    public void isUp475(){
        assertTrue(StringDataUtil.isUpperCase((char) 11404));
    }
    @Test
    public void isUp476(){
        assertTrue(StringDataUtil.isUpperCase((char) 11406));
    }
    @Test
    public void isUp477(){
        assertTrue(StringDataUtil.isUpperCase((char) 11408));
    }
    @Test
    public void isUp478(){
        assertTrue(StringDataUtil.isUpperCase((char) 11410));
    }
    @Test
    public void isUp479(){
        assertTrue(StringDataUtil.isUpperCase((char) 11412));
    }
    @Test
    public void isUp480(){
        assertTrue(StringDataUtil.isUpperCase((char) 11414));
    }
    @Test
    public void isUp481(){
        assertTrue(StringDataUtil.isUpperCase((char) 11416));
    }
    @Test
    public void isUp482(){
        assertTrue(StringDataUtil.isUpperCase((char) 11418));
    }
    @Test
    public void isUp483(){
        assertTrue(StringDataUtil.isUpperCase((char) 11420));
    }
    @Test
    public void isUp484(){
        assertTrue(StringDataUtil.isUpperCase((char) 11422));
    }
    @Test
    public void isUp485(){
        assertTrue(StringDataUtil.isUpperCase((char) 11424));
    }
    @Test
    public void isUp486(){
        assertTrue(StringDataUtil.isUpperCase((char) 11426));
    }
    @Test
    public void isUp487(){
        assertTrue(StringDataUtil.isUpperCase((char) 11428));
    }
    @Test
    public void isUp488(){
        assertTrue(StringDataUtil.isUpperCase((char) 11430));
    }
    @Test
    public void isUp489(){
        assertTrue(StringDataUtil.isUpperCase((char) 11432));
    }
    @Test
    public void isUp490(){
        assertTrue(StringDataUtil.isUpperCase((char) 11434));
    }
    @Test
    public void isUp491(){
        assertTrue(StringDataUtil.isUpperCase((char) 11436));
    }
    @Test
    public void isUp492(){
        assertTrue(StringDataUtil.isUpperCase((char) 11438));
    }
    @Test
    public void isUp493(){
        assertTrue(StringDataUtil.isUpperCase((char) 11440));
    }
    @Test
    public void isUp494(){
        assertTrue(StringDataUtil.isUpperCase((char) 11442));
    }
    @Test
    public void isUp495(){
        assertTrue(StringDataUtil.isUpperCase((char) 11444));
    }
    @Test
    public void isUp496(){
        assertTrue(StringDataUtil.isUpperCase((char) 11446));
    }
    @Test
    public void isUp497(){
        assertTrue(StringDataUtil.isUpperCase((char) 11448));
    }
    @Test
    public void isUp498(){
        assertTrue(StringDataUtil.isUpperCase((char) 11450));
    }
    @Test
    public void isUp499(){
        assertTrue(StringDataUtil.isUpperCase((char) 11452));
    }
    @Test
    public void isUp500(){
        assertTrue(StringDataUtil.isUpperCase((char) 11454));
    }
    @Test
    public void isUp501(){
        assertTrue(StringDataUtil.isUpperCase((char) 11456));
    }
    @Test
    public void isUp502(){
        assertTrue(StringDataUtil.isUpperCase((char) 11458));
    }
    @Test
    public void isUp503(){
        assertTrue(StringDataUtil.isUpperCase((char) 11460));
    }
    @Test
    public void isUp504(){
        assertTrue(StringDataUtil.isUpperCase((char) 11462));
    }
    @Test
    public void isUp505(){
        assertTrue(StringDataUtil.isUpperCase((char) 11464));
    }
    @Test
    public void isUp506(){
        assertTrue(StringDataUtil.isUpperCase((char) 11466));
    }
    @Test
    public void isUp507(){
        assertTrue(StringDataUtil.isUpperCase((char) 11468));
    }
    @Test
    public void isUp508(){
        assertTrue(StringDataUtil.isUpperCase((char) 11470));
    }
    @Test
    public void isUp509(){
        assertTrue(StringDataUtil.isUpperCase((char) 11472));
    }
    @Test
    public void isUp510(){
        assertTrue(StringDataUtil.isUpperCase((char) 11474));
    }
    @Test
    public void isUp511(){
        assertTrue(StringDataUtil.isUpperCase((char) 11476));
    }
    @Test
    public void isUp512(){
        assertTrue(StringDataUtil.isUpperCase((char) 11478));
    }
    @Test
    public void isUp513(){
        assertTrue(StringDataUtil.isUpperCase((char) 11480));
    }
    @Test
    public void isUp514(){
        assertTrue(StringDataUtil.isUpperCase((char) 11482));
    }
    @Test
    public void isUp515(){
        assertTrue(StringDataUtil.isUpperCase((char) 11484));
    }
    @Test
    public void isUp516(){
        assertTrue(StringDataUtil.isUpperCase((char) 11486));
    }
    @Test
    public void isUp517(){
        assertTrue(StringDataUtil.isUpperCase((char) 11488));
    }
    @Test
    public void isUp518(){
        assertTrue(StringDataUtil.isUpperCase((char) 11490));
    }
    @Test
    public void isUp519(){
        assertTrue(StringDataUtil.isUpperCase((char) 11499));
    }
    @Test
    public void isUp520(){
        assertTrue(StringDataUtil.isUpperCase((char) 11501));
    }
    @Test
    public void isUp521(){
        assertTrue(StringDataUtil.isUpperCase((char) 11506));
    }
    @Test
    public void isUp522(){
        assertTrue(StringDataUtil.isUpperCase((char) 42560));
    }
    @Test
    public void isUp523(){
        assertTrue(StringDataUtil.isUpperCase((char) 42562));
    }
    @Test
    public void isUp524(){
        assertTrue(StringDataUtil.isUpperCase((char) 42564));
    }
    @Test
    public void isUp525(){
        assertTrue(StringDataUtil.isUpperCase((char) 42566));
    }
    @Test
    public void isUp526(){
        assertTrue(StringDataUtil.isUpperCase((char) 42568));
    }
    @Test
    public void isUp527(){
        assertTrue(StringDataUtil.isUpperCase((char) 42570));
    }
    @Test
    public void isUp528(){
        assertTrue(StringDataUtil.isUpperCase((char) 42572));
    }
    @Test
    public void isUp529(){
        assertTrue(StringDataUtil.isUpperCase((char) 42574));
    }
    @Test
    public void isUp530(){
        assertTrue(StringDataUtil.isUpperCase((char) 42576));
    }
    @Test
    public void isUp531(){
        assertTrue(StringDataUtil.isUpperCase((char) 42578));
    }
    @Test
    public void isUp532(){
        assertTrue(StringDataUtil.isUpperCase((char) 42580));
    }
    @Test
    public void isUp533(){
        assertTrue(StringDataUtil.isUpperCase((char) 42582));
    }
    @Test
    public void isUp534(){
        assertTrue(StringDataUtil.isUpperCase((char) 42584));
    }
    @Test
    public void isUp535(){
        assertTrue(StringDataUtil.isUpperCase((char) 42586));
    }
    @Test
    public void isUp536(){
        assertTrue(StringDataUtil.isUpperCase((char) 42588));
    }
    @Test
    public void isUp537(){
        assertTrue(StringDataUtil.isUpperCase((char) 42590));
    }
    @Test
    public void isUp538(){
        assertTrue(StringDataUtil.isUpperCase((char) 42592));
    }
    @Test
    public void isUp539(){
        assertTrue(StringDataUtil.isUpperCase((char) 42594));
    }
    @Test
    public void isUp540(){
        assertTrue(StringDataUtil.isUpperCase((char) 42596));
    }
    @Test
    public void isUp541(){
        assertTrue(StringDataUtil.isUpperCase((char) 42598));
    }
    @Test
    public void isUp542(){
        assertTrue(StringDataUtil.isUpperCase((char) 42600));
    }
    @Test
    public void isUp543(){
        assertTrue(StringDataUtil.isUpperCase((char) 42602));
    }
    @Test
    public void isUp544(){
        assertTrue(StringDataUtil.isUpperCase((char) 42604));
    }
    @Test
    public void isUp545(){
        assertTrue(StringDataUtil.isUpperCase((char) 42624));
    }
    @Test
    public void isUp546(){
        assertTrue(StringDataUtil.isUpperCase((char) 42626));
    }
    @Test
    public void isUp547(){
        assertTrue(StringDataUtil.isUpperCase((char) 42628));
    }
    @Test
    public void isUp548(){
        assertTrue(StringDataUtil.isUpperCase((char) 42630));
    }
    @Test
    public void isUp549(){
        assertTrue(StringDataUtil.isUpperCase((char) 42632));
    }
    @Test
    public void isUp550(){
        assertTrue(StringDataUtil.isUpperCase((char) 42634));
    }
    @Test
    public void isUp551(){
        assertTrue(StringDataUtil.isUpperCase((char) 42636));
    }
    @Test
    public void isUp552(){
        assertTrue(StringDataUtil.isUpperCase((char) 42638));
    }
    @Test
    public void isUp553(){
        assertTrue(StringDataUtil.isUpperCase((char) 42640));
    }
    @Test
    public void isUp554(){
        assertTrue(StringDataUtil.isUpperCase((char) 42642));
    }
    @Test
    public void isUp555(){
        assertTrue(StringDataUtil.isUpperCase((char) 42644));
    }
    @Test
    public void isUp556(){
        assertTrue(StringDataUtil.isUpperCase((char) 42646));
    }
    @Test
    public void isUp557(){
        assertTrue(StringDataUtil.isUpperCase((char) 42786));
    }
    @Test
    public void isUp558(){
        assertTrue(StringDataUtil.isUpperCase((char) 42788));
    }
    @Test
    public void isUp559(){
        assertTrue(StringDataUtil.isUpperCase((char) 42790));
    }
    @Test
    public void isUp560(){
        assertTrue(StringDataUtil.isUpperCase((char) 42792));
    }
    @Test
    public void isUp561(){
        assertTrue(StringDataUtil.isUpperCase((char) 42794));
    }
    @Test
    public void isUp562(){
        assertTrue(StringDataUtil.isUpperCase((char) 42796));
    }
    @Test
    public void isUp563(){
        assertTrue(StringDataUtil.isUpperCase((char) 42798));
    }
    @Test
    public void isUp564(){
        assertTrue(StringDataUtil.isUpperCase((char) 42802));
    }
    @Test
    public void isUp565(){
        assertTrue(StringDataUtil.isUpperCase((char) 42804));
    }
    @Test
    public void isUp566(){
        assertTrue(StringDataUtil.isUpperCase((char) 42806));
    }
    @Test
    public void isUp567(){
        assertTrue(StringDataUtil.isUpperCase((char) 42808));
    }
    @Test
    public void isUp568(){
        assertTrue(StringDataUtil.isUpperCase((char) 42810));
    }
    @Test
    public void isUp569(){
        assertTrue(StringDataUtil.isUpperCase((char) 42812));
    }
    @Test
    public void isUp570(){
        assertTrue(StringDataUtil.isUpperCase((char) 42814));
    }
    @Test
    public void isUp571(){
        assertTrue(StringDataUtil.isUpperCase((char) 42816));
    }
    @Test
    public void isUp572(){
        assertTrue(StringDataUtil.isUpperCase((char) 42818));
    }
    @Test
    public void isUp573(){
        assertTrue(StringDataUtil.isUpperCase((char) 42820));
    }
    @Test
    public void isUp574(){
        assertTrue(StringDataUtil.isUpperCase((char) 42822));
    }
    @Test
    public void isUp575(){
        assertTrue(StringDataUtil.isUpperCase((char) 42824));
    }
    @Test
    public void isUp576(){
        assertTrue(StringDataUtil.isUpperCase((char) 42826));
    }
    @Test
    public void isUp577(){
        assertTrue(StringDataUtil.isUpperCase((char) 42828));
    }
    @Test
    public void isUp578(){
        assertTrue(StringDataUtil.isUpperCase((char) 42830));
    }
    @Test
    public void isUp579(){
        assertTrue(StringDataUtil.isUpperCase((char) 42832));
    }
    @Test
    public void isUp580(){
        assertTrue(StringDataUtil.isUpperCase((char) 42834));
    }
    @Test
    public void isUp581(){
        assertTrue(StringDataUtil.isUpperCase((char) 42836));
    }
    @Test
    public void isUp582(){
        assertTrue(StringDataUtil.isUpperCase((char) 42838));
    }
    @Test
    public void isUp583(){
        assertTrue(StringDataUtil.isUpperCase((char) 42840));
    }
    @Test
    public void isUp584(){
        assertTrue(StringDataUtil.isUpperCase((char) 42842));
    }
    @Test
    public void isUp585(){
        assertTrue(StringDataUtil.isUpperCase((char) 42844));
    }
    @Test
    public void isUp586(){
        assertTrue(StringDataUtil.isUpperCase((char) 42846));
    }
    @Test
    public void isUp587(){
        assertTrue(StringDataUtil.isUpperCase((char) 42848));
    }
    @Test
    public void isUp588(){
        assertTrue(StringDataUtil.isUpperCase((char) 42850));
    }
    @Test
    public void isUp589(){
        assertTrue(StringDataUtil.isUpperCase((char) 42852));
    }
    @Test
    public void isUp590(){
        assertTrue(StringDataUtil.isUpperCase((char) 42854));
    }
    @Test
    public void isUp591(){
        assertTrue(StringDataUtil.isUpperCase((char) 42856));
    }
    @Test
    public void isUp592(){
        assertTrue(StringDataUtil.isUpperCase((char) 42858));
    }
    @Test
    public void isUp593(){
        assertTrue(StringDataUtil.isUpperCase((char) 42860));
    }
    @Test
    public void isUp594(){
        assertTrue(StringDataUtil.isUpperCase((char) 42862));
    }
    @Test
    public void isUp595(){
        assertTrue(StringDataUtil.isUpperCase((char) 42873));
    }
    @Test
    public void isUp596(){
        assertTrue(StringDataUtil.isUpperCase((char) 42875));
    }
    @Test
    public void isUp597(){
        assertTrue(StringDataUtil.isUpperCase((char) 42877));
    }
    @Test
    public void isUp598(){
        assertTrue(StringDataUtil.isUpperCase((char) 42878));
    }
    @Test
    public void isUp599(){
        assertTrue(StringDataUtil.isUpperCase((char) 42880));
    }
    @Test
    public void isUp600(){
        assertTrue(StringDataUtil.isUpperCase((char) 42882));
    }
    @Test
    public void isUp601(){
        assertTrue(StringDataUtil.isUpperCase((char) 42884));
    }
    @Test
    public void isUp602(){
        assertTrue(StringDataUtil.isUpperCase((char) 42886));
    }
    @Test
    public void isUp603(){
        assertTrue(StringDataUtil.isUpperCase((char) 42891));
    }
    @Test
    public void isUp604(){
        assertTrue(StringDataUtil.isUpperCase((char) 42893));
    }
    @Test
    public void isUp605(){
        assertTrue(StringDataUtil.isUpperCase((char) 42896));
    }
    @Test
    public void isUp606(){
        assertTrue(StringDataUtil.isUpperCase((char) 42898));
    }
    @Test
    public void isUp607(){
        assertTrue(StringDataUtil.isUpperCase((char) 42912));
    }
    @Test
    public void isUp608(){
        assertTrue(StringDataUtil.isUpperCase((char) 42914));
    }
    @Test
    public void isUp609(){
        assertTrue(StringDataUtil.isUpperCase((char) 42916));
    }
    @Test
    public void isUp610(){
        assertTrue(StringDataUtil.isUpperCase((char) 42918));
    }
    @Test
    public void isUp611(){
        assertTrue(StringDataUtil.isUpperCase((char) 42920));
    }
    @Test
    public void isUp612(){
        assertTrue(StringDataUtil.isUpperCase((char) 42922));
    }
    @Test
    public void isUp613(){
        assertTrue(StringDataUtil.isUpperCase((char) 65313));
    }
    @Test
    public void isUp614(){
        assertTrue(StringDataUtil.isUpperCase((char) 65338));
    }
    @Test
    public void isUp615(){
        assertTrue(!StringDataUtil.isUpperCase((char) 888));
    }
    @Test
    public void isUp616(){
        assertTrue(!StringDataUtil.isUpperCase((char) 889));
    }
    @Test
    public void isUp617(){
        assertTrue(!StringDataUtil.isUpperCase((char) 895));
    }
    @Test
    public void isUp618(){
        assertTrue(!StringDataUtil.isUpperCase((char) 899));
    }
    @Test
    public void isUp619(){
        assertTrue(!StringDataUtil.isUpperCase((char) 907));
    }
    @Test
    public void isUp620(){
        assertTrue(!StringDataUtil.isUpperCase((char) 909));
    }
    @Test
    public void isUp621(){
        assertTrue(!StringDataUtil.isUpperCase((char) 930));
    }
    @Test
    public void isUp622(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1320));
    }
    @Test
    public void isUp623(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1328));
    }
    @Test
    public void isUp624(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1367));
    }
    @Test
    public void isUp625(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1368));
    }
    @Test
    public void isUp626(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1376));
    }
    @Test
    public void isUp627(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1416));
    }
    @Test
    public void isUp628(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1419));
    }
    @Test
    public void isUp629(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1422));
    }
    @Test
    public void isUp630(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1424));
    }
    @Test
    public void isUp631(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1480));
    }
    @Test
    public void isUp632(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1487));
    }
    @Test
    public void isUp633(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1515));
    }
    @Test
    public void isUp634(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1519));
    }
    @Test
    public void isUp635(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1525));
    }
    @Test
    public void isUp636(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1535));
    }
    @Test
    public void isUp637(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1541));
    }
    @Test
    public void isUp638(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1564));
    }
    @Test
    public void isUp639(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1565));
    }
    @Test
    public void isUp640(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1806));
    }
    @Test
    public void isUp641(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1867));
    }
    @Test
    public void isUp642(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1868));
    }
    @Test
    public void isUp643(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1970));
    }
    @Test
    public void isUp644(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1983));
    }
    @Test
    public void isUp645(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2043));
    }
    @Test
    public void isUp646(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2047));
    }
    @Test
    public void isUp647(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2094));
    }
    @Test
    public void isUp648(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2095));
    }
    @Test
    public void isUp649(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2111));
    }
    @Test
    public void isUp650(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2140));
    }
    @Test
    public void isUp651(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2141));
    }
    @Test
    public void isUp652(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2143));
    }
    @Test
    public void isUp653(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2207));
    }
    @Test
    public void isUp654(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2209));
    }
    @Test
    public void isUp655(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2221));
    }
    @Test
    public void isUp656(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2275));
    }
    @Test
    public void isUp657(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2303));
    }
    @Test
    public void isUp658(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2424));
    }
    @Test
    public void isUp659(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2432));
    }
    @Test
    public void isUp660(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2436));
    }
    @Test
    public void isUp661(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2445));
    }
    @Test
    public void isUp662(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2446));
    }
    @Test
    public void isUp663(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2449));
    }
    @Test
    public void isUp664(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2450));
    }
    @Test
    public void isUp665(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2473));
    }
    @Test
    public void isUp666(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2481));
    }
    @Test
    public void isUp667(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2483));
    }
    @Test
    public void isUp668(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2485));
    }
    @Test
    public void isUp669(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2490));
    }
    @Test
    public void isUp670(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2491));
    }
    @Test
    public void isUp671(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2501));
    }
    @Test
    public void isUp672(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2502));
    }
    @Test
    public void isUp673(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2505));
    }
    @Test
    public void isUp674(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2506));
    }
    @Test
    public void isUp675(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2511));
    }
    @Test
    public void isUp676(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2518));
    }
    @Test
    public void isUp677(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2520));
    }
    @Test
    public void isUp678(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2523));
    }
    @Test
    public void isUp679(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2526));
    }
    @Test
    public void isUp680(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2532));
    }
    @Test
    public void isUp681(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2533));
    }
    @Test
    public void isUp682(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2556));
    }
    @Test
    public void isUp683(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2560));
    }
    @Test
    public void isUp684(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2564));
    }
    @Test
    public void isUp685(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2571));
    }
    @Test
    public void isUp686(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2574));
    }
    @Test
    public void isUp687(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2577));
    }
    @Test
    public void isUp688(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2578));
    }
    @Test
    public void isUp689(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2601));
    }
    @Test
    public void isUp690(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2609));
    }
    @Test
    public void isUp691(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2612));
    }
    @Test
    public void isUp692(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2615));
    }
    @Test
    public void isUp693(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2618));
    }
    @Test
    public void isUp694(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2619));
    }
    @Test
    public void isUp695(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2621));
    }
    @Test
    public void isUp696(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2627));
    }
    @Test
    public void isUp697(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2630));
    }
    @Test
    public void isUp698(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2633));
    }
    @Test
    public void isUp699(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2634));
    }
    @Test
    public void isUp700(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2638));
    }
    @Test
    public void isUp701(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2640));
    }
    @Test
    public void isUp702(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2642));
    }
    @Test
    public void isUp703(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2648));
    }
    @Test
    public void isUp704(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2653));
    }
    @Test
    public void isUp705(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2655));
    }
    @Test
    public void isUp706(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2661));
    }
    @Test
    public void isUp707(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2678));
    }
    @Test
    public void isUp708(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2688));
    }
    @Test
    public void isUp709(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2692));
    }
    @Test
    public void isUp710(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2702));
    }
    @Test
    public void isUp711(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2706));
    }
    @Test
    public void isUp712(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2729));
    }
    @Test
    public void isUp713(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2737));
    }
    @Test
    public void isUp714(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2740));
    }
    @Test
    public void isUp715(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2746));
    }
    @Test
    public void isUp716(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2747));
    }
    @Test
    public void isUp717(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2758));
    }
    @Test
    public void isUp718(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2762));
    }
    @Test
    public void isUp719(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2766));
    }
    @Test
    public void isUp720(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2767));
    }
    @Test
    public void isUp721(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2769));
    }
    @Test
    public void isUp722(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2783));
    }
    @Test
    public void isUp723(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2788));
    }
    @Test
    public void isUp724(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2789));
    }
    @Test
    public void isUp725(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2802));
    }
    @Test
    public void isUp726(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2816));
    }
    @Test
    public void isUp727(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2820));
    }
    @Test
    public void isUp728(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2829));
    }
    @Test
    public void isUp729(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2830));
    }
    @Test
    public void isUp730(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2833));
    }
    @Test
    public void isUp731(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2834));
    }
    @Test
    public void isUp732(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2857));
    }
    @Test
    public void isUp733(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2865));
    }
    @Test
    public void isUp734(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2868));
    }
    @Test
    public void isUp735(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2874));
    }
    @Test
    public void isUp736(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2875));
    }
    @Test
    public void isUp737(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2885));
    }
    @Test
    public void isUp738(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2886));
    }
    @Test
    public void isUp739(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2889));
    }
    @Test
    public void isUp740(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2890));
    }
    @Test
    public void isUp741(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2894));
    }
    @Test
    public void isUp742(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2901));
    }
    @Test
    public void isUp743(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2904));
    }
    @Test
    public void isUp744(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2907));
    }
    @Test
    public void isUp745(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2910));
    }
    @Test
    public void isUp746(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2916));
    }
    @Test
    public void isUp747(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2917));
    }
    @Test
    public void isUp748(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2936));
    }
    @Test
    public void isUp749(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2945));
    }
    @Test
    public void isUp750(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2948));
    }
    @Test
    public void isUp751(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2955));
    }
    @Test
    public void isUp752(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2957));
    }
    @Test
    public void isUp753(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2961));
    }
    @Test
    public void isUp754(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2966));
    }
    @Test
    public void isUp755(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2968));
    }
    @Test
    public void isUp756(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2971));
    }
    @Test
    public void isUp757(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2973));
    }
    @Test
    public void isUp758(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2976));
    }
    @Test
    public void isUp759(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2978));
    }
    @Test
    public void isUp760(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2981));
    }
    @Test
    public void isUp761(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2983));
    }
    @Test
    public void isUp762(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2987));
    }
    @Test
    public void isUp763(){
        assertTrue(!StringDataUtil.isUpperCase((char) 2989));
    }
    @Test
    public void isUp764(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3002));
    }
    @Test
    public void isUp765(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3005));
    }
    @Test
    public void isUp766(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3011));
    }
    @Test
    public void isUp767(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3013));
    }
    @Test
    public void isUp768(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3017));
    }
    @Test
    public void isUp769(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3022));
    }
    @Test
    public void isUp770(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3023));
    }
    @Test
    public void isUp771(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3025));
    }
    @Test
    public void isUp772(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3030));
    }
    @Test
    public void isUp773(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3032));
    }
    @Test
    public void isUp774(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3045));
    }
    @Test
    public void isUp775(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3067));
    }
    @Test
    public void isUp776(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3072));
    }
    @Test
    public void isUp777(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3076));
    }
    @Test
    public void isUp778(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3085));
    }
    @Test
    public void isUp779(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3089));
    }
    @Test
    public void isUp780(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3113));
    }
    @Test
    public void isUp781(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3124));
    }
    @Test
    public void isUp782(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3130));
    }
    @Test
    public void isUp783(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3132));
    }
    @Test
    public void isUp784(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3141));
    }
    @Test
    public void isUp785(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3145));
    }
    @Test
    public void isUp786(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3150));
    }
    @Test
    public void isUp787(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3156));
    }
    @Test
    public void isUp788(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3159));
    }
    @Test
    public void isUp789(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3162));
    }
    @Test
    public void isUp790(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3167));
    }
    @Test
    public void isUp791(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3172));
    }
    @Test
    public void isUp792(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3173));
    }
    @Test
    public void isUp793(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3184));
    }
    @Test
    public void isUp794(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3191));
    }
    @Test
    public void isUp795(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3200));
    }
    @Test
    public void isUp796(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3201));
    }
    @Test
    public void isUp797(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3204));
    }
    @Test
    public void isUp798(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3213));
    }
    @Test
    public void isUp799(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3217));
    }
    @Test
    public void isUp800(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3241));
    }
    @Test
    public void isUp801(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3252));
    }
    @Test
    public void isUp802(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3258));
    }
    @Test
    public void isUp803(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3259));
    }
    @Test
    public void isUp804(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3269));
    }
    @Test
    public void isUp805(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3273));
    }
    @Test
    public void isUp806(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3278));
    }
    @Test
    public void isUp807(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3284));
    }
    @Test
    public void isUp808(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3287));
    }
    @Test
    public void isUp809(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3293));
    }
    @Test
    public void isUp810(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3295));
    }
    @Test
    public void isUp811(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3300));
    }
    @Test
    public void isUp812(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3301));
    }
    @Test
    public void isUp813(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3312));
    }
    @Test
    public void isUp814(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3315));
    }
    @Test
    public void isUp815(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3329));
    }
    @Test
    public void isUp816(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3332));
    }
    @Test
    public void isUp817(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3341));
    }
    @Test
    public void isUp818(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3345));
    }
    @Test
    public void isUp819(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3387));
    }
    @Test
    public void isUp820(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3388));
    }
    @Test
    public void isUp821(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3397));
    }
    @Test
    public void isUp822(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3401));
    }
    @Test
    public void isUp823(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3407));
    }
    @Test
    public void isUp824(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3414));
    }
    @Test
    public void isUp825(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3416));
    }
    @Test
    public void isUp826(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3423));
    }
    @Test
    public void isUp827(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3428));
    }
    @Test
    public void isUp828(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3429));
    }
    @Test
    public void isUp829(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3446));
    }
    @Test
    public void isUp830(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3448));
    }
    @Test
    public void isUp831(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3456));
    }
    @Test
    public void isUp832(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3457));
    }
    @Test
    public void isUp833(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3460));
    }
    @Test
    public void isUp834(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3479));
    }
    @Test
    public void isUp835(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3481));
    }
    @Test
    public void isUp836(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3506));
    }
    @Test
    public void isUp837(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3516));
    }
    @Test
    public void isUp838(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3518));
    }
    @Test
    public void isUp839(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3519));
    }
    @Test
    public void isUp840(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3527));
    }
    @Test
    public void isUp841(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3529));
    }
    @Test
    public void isUp842(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3531));
    }
    @Test
    public void isUp843(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3534));
    }
    @Test
    public void isUp844(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3541));
    }
    @Test
    public void isUp845(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3543));
    }
    @Test
    public void isUp846(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3552));
    }
    @Test
    public void isUp847(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3569));
    }
    @Test
    public void isUp848(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3573));
    }
    @Test
    public void isUp849(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3584));
    }
    @Test
    public void isUp850(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3643));
    }
    @Test
    public void isUp851(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3646));
    }
    @Test
    public void isUp852(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3676));
    }
    @Test
    public void isUp853(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3712));
    }
    @Test
    public void isUp854(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3715));
    }
    @Test
    public void isUp855(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3717));
    }
    @Test
    public void isUp856(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3718));
    }
    @Test
    public void isUp857(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3721));
    }
    @Test
    public void isUp858(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3723));
    }
    @Test
    public void isUp859(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3724));
    }
    @Test
    public void isUp860(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3726));
    }
    @Test
    public void isUp861(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3731));
    }
    @Test
    public void isUp862(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3736));
    }
    @Test
    public void isUp863(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3744));
    }
    @Test
    public void isUp864(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3748));
    }
    @Test
    public void isUp865(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3750));
    }
    @Test
    public void isUp866(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3752));
    }
    @Test
    public void isUp867(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3753));
    }
    @Test
    public void isUp868(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3756));
    }
    @Test
    public void isUp869(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3770));
    }
    @Test
    public void isUp870(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3774));
    }
    @Test
    public void isUp871(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3775));
    }
    @Test
    public void isUp872(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3781));
    }
    @Test
    public void isUp873(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3783));
    }
    @Test
    public void isUp874(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3790));
    }
    @Test
    public void isUp875(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3791));
    }
    @Test
    public void isUp876(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3802));
    }
    @Test
    public void isUp877(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3803));
    }
    @Test
    public void isUp878(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3808));
    }
    @Test
    public void isUp879(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3839));
    }
    @Test
    public void isUp880(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3912));
    }
    @Test
    public void isUp881(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3949));
    }
    @Test
    public void isUp882(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3952));
    }
    @Test
    public void isUp883(){
        assertTrue(!StringDataUtil.isUpperCase((char) 3992));
    }
    @Test
    public void isUp884(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4029));
    }
    @Test
    public void isUp885(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4045));
    }
    @Test
    public void isUp886(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4059));
    }
    @Test
    public void isUp887(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4095));
    }
    @Test
    public void isUp888(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4294));
    }
    @Test
    public void isUp889(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4296));
    }
    @Test
    public void isUp890(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4300));
    }
    @Test
    public void isUp891(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4302));
    }
    @Test
    public void isUp892(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4303));
    }
    @Test
    public void isUp893(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4681));
    }
    @Test
    public void isUp894(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4686));
    }
    @Test
    public void isUp895(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4687));
    }
    @Test
    public void isUp896(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4695));
    }
    @Test
    public void isUp897(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4697));
    }
    @Test
    public void isUp898(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4702));
    }
    @Test
    public void isUp899(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4703));
    }
    @Test
    public void isUp900(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4745));
    }
    @Test
    public void isUp901(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4750));
    }
    @Test
    public void isUp902(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4751));
    }
    @Test
    public void isUp903(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4785));
    }
    @Test
    public void isUp904(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4790));
    }
    @Test
    public void isUp905(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4791));
    }
    @Test
    public void isUp906(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4799));
    }
    @Test
    public void isUp907(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4801));
    }
    @Test
    public void isUp908(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4806));
    }
    @Test
    public void isUp909(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4807));
    }
    @Test
    public void isUp910(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4823));
    }
    @Test
    public void isUp911(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4881));
    }
    @Test
    public void isUp912(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4886));
    }
    @Test
    public void isUp913(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4887));
    }
    @Test
    public void isUp914(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4955));
    }
    @Test
    public void isUp915(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4956));
    }
    @Test
    public void isUp916(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4989));
    }
    @Test
    public void isUp917(){
        assertTrue(!StringDataUtil.isUpperCase((char) 4991));
    }
    @Test
    public void isUp918(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5018));
    }
    @Test
    public void isUp919(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5023));
    }
    @Test
    public void isUp920(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5109));
    }
    @Test
    public void isUp921(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5119));
    }
    @Test
    public void isUp922(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5789));
    }
    @Test
    public void isUp923(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5791));
    }
    @Test
    public void isUp924(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5873));
    }
    @Test
    public void isUp925(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5887));
    }
    @Test
    public void isUp926(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5901));
    }
    @Test
    public void isUp927(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5909));
    }
    @Test
    public void isUp928(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5919));
    }
    @Test
    public void isUp929(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5943));
    }
    @Test
    public void isUp930(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5951));
    }
    @Test
    public void isUp931(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5972));
    }
    @Test
    public void isUp932(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5983));
    }
    @Test
    public void isUp933(){
        assertTrue(!StringDataUtil.isUpperCase((char) 5997));
    }
    @Test
    public void isUp934(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6001));
    }
    @Test
    public void isUp935(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6004));
    }
    @Test
    public void isUp936(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6015));
    }
    @Test
    public void isUp937(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6110));
    }
    @Test
    public void isUp938(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6111));
    }
    @Test
    public void isUp939(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6122));
    }
    @Test
    public void isUp940(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6127));
    }
    @Test
    public void isUp941(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6138));
    }
    @Test
    public void isUp942(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6143));
    }
    @Test
    public void isUp943(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6159));
    }
    @Test
    public void isUp944(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6170));
    }
    @Test
    public void isUp945(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6175));
    }
    @Test
    public void isUp946(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6264));
    }
    @Test
    public void isUp947(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6271));
    }
    @Test
    public void isUp948(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6315));
    }
    @Test
    public void isUp949(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6319));
    }
    @Test
    public void isUp950(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6390));
    }
    @Test
    public void isUp951(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6399));
    }
    @Test
    public void isUp952(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6429));
    }
    @Test
    public void isUp953(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6431));
    }
    @Test
    public void isUp954(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6444));
    }
    @Test
    public void isUp955(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6447));
    }
    @Test
    public void isUp956(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6460));
    }
    @Test
    public void isUp957(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6463));
    }
    @Test
    public void isUp958(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6465));
    }
    @Test
    public void isUp959(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6467));
    }
    @Test
    public void isUp960(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6510));
    }
    @Test
    public void isUp961(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6511));
    }
    @Test
    public void isUp962(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6517));
    }
    @Test
    public void isUp963(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6527));
    }
    @Test
    public void isUp964(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6572));
    }
    @Test
    public void isUp965(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6575));
    }
    @Test
    public void isUp966(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6602));
    }
    @Test
    public void isUp967(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6607));
    }
    @Test
    public void isUp968(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6619));
    }
    @Test
    public void isUp969(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6621));
    }
    @Test
    public void isUp970(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6684));
    }
    @Test
    public void isUp971(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6685));
    }
    @Test
    public void isUp972(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6751));
    }
    @Test
    public void isUp973(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6781));
    }
    @Test
    public void isUp974(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6782));
    }
    @Test
    public void isUp975(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6794));
    }
    @Test
    public void isUp976(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6799));
    }
    @Test
    public void isUp977(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6810));
    }
    @Test
    public void isUp978(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6815));
    }
    @Test
    public void isUp979(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6830));
    }
    @Test
    public void isUp980(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6911));
    }
    @Test
    public void isUp981(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6988));
    }
    @Test
    public void isUp982(){
        assertTrue(!StringDataUtil.isUpperCase((char) 6991));
    }
    @Test
    public void isUp983(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7037));
    }
    @Test
    public void isUp984(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7039));
    }
    @Test
    public void isUp985(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7156));
    }
    @Test
    public void isUp986(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7163));
    }
    @Test
    public void isUp987(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7224));
    }
    @Test
    public void isUp988(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7226));
    }
    @Test
    public void isUp989(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7242));
    }
    @Test
    public void isUp990(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7244));
    }
    @Test
    public void isUp991(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7296));
    }
    @Test
    public void isUp992(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7359));
    }
    @Test
    public void isUp993(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7368));
    }
    @Test
    public void isUp994(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7375));
    }
    @Test
    public void isUp995(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7415));
    }
    @Test
    public void isUp996(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7423));
    }
    @Test
    public void isUp997(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7655));
    }
    @Test
    public void isUp998(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7675));
    }
    @Test
    public void isUp999(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7958));
    }
    @Test
    public void isUp1000(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7959));
    }
    @Test
    public void isUp1001(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7966));
    }
    @Test
    public void isUp1002(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7967));
    }
    @Test
    public void isUp1003(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8006));
    }
    @Test
    public void isUp1004(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8007));
    }
    @Test
    public void isUp1005(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8014));
    }
    @Test
    public void isUp1006(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8015));
    }
    @Test
    public void isUp1007(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8024));
    }
    @Test
    public void isUp1008(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8026));
    }
    @Test
    public void isUp1009(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8028));
    }
    @Test
    public void isUp1010(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8030));
    }
    @Test
    public void isUp1011(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8062));
    }
    @Test
    public void isUp1012(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8063));
    }
    @Test
    public void isUp1013(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8117));
    }
    @Test
    public void isUp1014(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8133));
    }
    @Test
    public void isUp1015(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8148));
    }
    @Test
    public void isUp1016(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8149));
    }
    @Test
    public void isUp1017(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8156));
    }
    @Test
    public void isUp1018(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8176));
    }
    @Test
    public void isUp1019(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8177));
    }
    @Test
    public void isUp1020(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8181));
    }
    @Test
    public void isUp1021(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8191));
    }
    @Test
    public void isUp1022(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8293));
    }
    @Test
    public void isUp1023(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8297));
    }
    @Test
    public void isUp1024(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8306));
    }
    @Test
    public void isUp1025(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8307));
    }
    @Test
    public void isUp1026(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8335));
    }
    @Test
    public void isUp1027(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8349));
    }
    @Test
    public void isUp1028(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8351));
    }
    @Test
    public void isUp1029(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8379));
    }
    @Test
    public void isUp1030(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8399));
    }
    @Test
    public void isUp1031(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8433));
    }
    @Test
    public void isUp1032(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8447));
    }
    @Test
    public void isUp1033(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8586));
    }
    @Test
    public void isUp1034(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8591));
    }
    @Test
    public void isUp1035(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9204));
    }
    @Test
    public void isUp1036(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9215));
    }
    @Test
    public void isUp1037(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9255));
    }
    @Test
    public void isUp1038(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9279));
    }
    @Test
    public void isUp1039(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9291));
    }
    @Test
    public void isUp1040(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9311));
    }
    @Test
    public void isUp1041(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9984));
    }
    @Test
    public void isUp1042(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11085));
    }
    @Test
    public void isUp1043(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11087));
    }
    @Test
    public void isUp1044(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11098));
    }
    @Test
    public void isUp1045(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11263));
    }
    @Test
    public void isUp1046(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11311));
    }
    @Test
    public void isUp1047(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11359));
    }
    @Test
    public void isUp1048(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11508));
    }
    @Test
    public void isUp1049(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11512));
    }
    @Test
    public void isUp1050(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11558));
    }
    @Test
    public void isUp1051(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11560));
    }
    @Test
    public void isUp1052(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11564));
    }
    @Test
    public void isUp1053(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11566));
    }
    @Test
    public void isUp1054(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11567));
    }
    @Test
    public void isUp1055(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11624));
    }
    @Test
    public void isUp1056(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11630));
    }
    @Test
    public void isUp1057(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11633));
    }
    @Test
    public void isUp1058(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11646));
    }
    @Test
    public void isUp1059(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11671));
    }
    @Test
    public void isUp1060(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11679));
    }
    @Test
    public void isUp1061(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11687));
    }
    @Test
    public void isUp1062(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11695));
    }
    @Test
    public void isUp1063(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11703));
    }
    @Test
    public void isUp1064(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11711));
    }
    @Test
    public void isUp1065(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11719));
    }
    @Test
    public void isUp1066(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11727));
    }
    @Test
    public void isUp1067(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11735));
    }
    @Test
    public void isUp1068(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11743));
    }
    @Test
    public void isUp1069(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11836));
    }
    @Test
    public void isUp1070(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11903));
    }
    @Test
    public void isUp1071(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11930));
    }
    @Test
    public void isUp1072(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12020));
    }
    @Test
    public void isUp1073(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12031));
    }
    @Test
    public void isUp1074(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12246));
    }
    @Test
    public void isUp1075(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12271));
    }
    @Test
    public void isUp1076(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12284));
    }
    @Test
    public void isUp1077(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12287));
    }
    @Test
    public void isUp1078(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12352));
    }
    @Test
    public void isUp1079(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12439));
    }
    @Test
    public void isUp1080(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12440));
    }
    @Test
    public void isUp1081(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12544));
    }
    @Test
    public void isUp1082(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12548));
    }
    @Test
    public void isUp1083(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12590));
    }
    @Test
    public void isUp1084(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12592));
    }
    @Test
    public void isUp1085(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12687));
    }
    @Test
    public void isUp1086(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12731));
    }
    @Test
    public void isUp1087(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12735));
    }
    @Test
    public void isUp1088(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12772));
    }
    @Test
    public void isUp1089(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12783));
    }
    @Test
    public void isUp1090(){
        assertTrue(!StringDataUtil.isUpperCase((char) 12831));
    }
    @Test
    public void isUp1091(){
        assertTrue(!StringDataUtil.isUpperCase((char) 13055));
    }
    @Test
    public void isUp1092(){
        assertTrue(!StringDataUtil.isUpperCase((char) 19894));
    }
    @Test
    public void isUp1093(){
        assertTrue(!StringDataUtil.isUpperCase((char) 19903));
    }
    @Test
    public void isUp1094(){
        assertTrue(!StringDataUtil.isUpperCase((char) 40909));
    }
    @Test
    public void isUp1095(){
        assertTrue(!StringDataUtil.isUpperCase((char) 40959));
    }
    @Test
    public void isUp1096(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42125));
    }
    @Test
    public void isUp1097(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42127));
    }
    @Test
    public void isUp1098(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42183));
    }
    @Test
    public void isUp1099(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42191));
    }
    @Test
    public void isUp1100(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42540));
    }
    @Test
    public void isUp1101(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42559));
    }
    @Test
    public void isUp1102(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42648));
    }
    @Test
    public void isUp1103(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42654));
    }
    @Test
    public void isUp1104(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42744));
    }
    @Test
    public void isUp1105(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42751));
    }
    @Test
    public void isUp1106(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42895));
    }
    @Test
    public void isUp1107(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42900));
    }
    @Test
    public void isUp1108(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42911));
    }
    @Test
    public void isUp1109(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42923));
    }
    @Test
    public void isUp1110(){
        assertTrue(!StringDataUtil.isUpperCase((char) 42999));
    }
    @Test
    public void isUp1111(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43052));
    }
    @Test
    public void isUp1112(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43055));
    }
    @Test
    public void isUp1113(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43066));
    }
    @Test
    public void isUp1114(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43071));
    }
    @Test
    public void isUp1115(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43128));
    }
    @Test
    public void isUp1116(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43135));
    }
    @Test
    public void isUp1117(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43205));
    }
    @Test
    public void isUp1118(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43213));
    }
    @Test
    public void isUp1119(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43226));
    }
    @Test
    public void isUp1120(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43231));
    }
    @Test
    public void isUp1121(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43260));
    }
    @Test
    public void isUp1122(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43263));
    }
    @Test
    public void isUp1123(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43348));
    }
    @Test
    public void isUp1124(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43358));
    }
    @Test
    public void isUp1125(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43389));
    }
    @Test
    public void isUp1126(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43391));
    }
    @Test
    public void isUp1127(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43470));
    }
    @Test
    public void isUp1128(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43482));
    }
    @Test
    public void isUp1129(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43485));
    }
    @Test
    public void isUp1130(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43488));
    }
    @Test
    public void isUp1131(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43519));
    }
    @Test
    public void isUp1132(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43575));
    }
    @Test
    public void isUp1133(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43583));
    }
    @Test
    public void isUp1134(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43598));
    }
    @Test
    public void isUp1135(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43599));
    }
    @Test
    public void isUp1136(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43610));
    }
    @Test
    public void isUp1137(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43611));
    }
    @Test
    public void isUp1138(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43644));
    }
    @Test
    public void isUp1139(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43647));
    }
    @Test
    public void isUp1140(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43715));
    }
    @Test
    public void isUp1141(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43738));
    }
    @Test
    public void isUp1142(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43767));
    }
    @Test
    public void isUp1143(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43776));
    }
    @Test
    public void isUp1144(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43783));
    }
    @Test
    public void isUp1145(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43784));
    }
    @Test
    public void isUp1146(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43791));
    }
    @Test
    public void isUp1147(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43792));
    }
    @Test
    public void isUp1148(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43799));
    }
    @Test
    public void isUp1149(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43807));
    }
    @Test
    public void isUp1150(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43815));
    }
    @Test
    public void isUp1151(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43823));
    }
    @Test
    public void isUp1152(){
        assertTrue(!StringDataUtil.isUpperCase((char) 43967));
    }
    @Test
    public void isUp1153(){
        assertTrue(!StringDataUtil.isUpperCase((char) 44014));
    }
    @Test
    public void isUp1154(){
        assertTrue(!StringDataUtil.isUpperCase((char) 44015));
    }
    @Test
    public void isUp1155(){
        assertTrue(!StringDataUtil.isUpperCase((char) 44026));
    }
    @Test
    public void isUp1156(){
        assertTrue(!StringDataUtil.isUpperCase((char) 44031));
    }
    @Test
    public void isUp1157(){
        assertTrue(!StringDataUtil.isUpperCase((char) 55204));
    }
    @Test
    public void isUp1158(){
        assertTrue(!StringDataUtil.isUpperCase((char) 55215));
    }
    @Test
    public void isUp1159(){
        assertTrue(!StringDataUtil.isUpperCase((char) 55239));
    }
    @Test
    public void isUp1160(){
        assertTrue(!StringDataUtil.isUpperCase((char) 55242));
    }
    @Test
    public void isUp1161(){
        assertTrue(!StringDataUtil.isUpperCase((char) 55292));
    }
    @Test
    public void isUp1162(){
        assertTrue(!StringDataUtil.isUpperCase((char) 55295));
    }
    @Test
    public void isUp1163(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64110));
    }
    @Test
    public void isUp1164(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64111));
    }
    @Test
    public void isUp1165(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64218));
    }
    @Test
    public void isUp1166(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64255));
    }
    @Test
    public void isUp1167(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64263));
    }
    @Test
    public void isUp1168(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64274));
    }
    @Test
    public void isUp1169(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64280));
    }
    @Test
    public void isUp1170(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64284));
    }
    @Test
    public void isUp1171(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64311));
    }
    @Test
    public void isUp1172(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64317));
    }
    @Test
    public void isUp1173(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64319));
    }
    @Test
    public void isUp1174(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64322));
    }
    @Test
    public void isUp1175(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64325));
    }
    @Test
    public void isUp1176(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64450));
    }
    @Test
    public void isUp1177(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64466));
    }
    @Test
    public void isUp1178(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64832));
    }
    @Test
    public void isUp1179(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64847));
    }
    @Test
    public void isUp1180(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64912));
    }
    @Test
    public void isUp1181(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64913));
    }
    @Test
    public void isUp1182(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64968));
    }
    @Test
    public void isUp1183(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65007));
    }
    @Test
    public void isUp1184(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65022));
    }
    @Test
    public void isUp1185(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65023));
    }
    @Test
    public void isUp1186(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65050));
    }
    @Test
    public void isUp1187(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65055));
    }
    @Test
    public void isUp1188(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65063));
    }
    @Test
    public void isUp1189(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65071));
    }
    @Test
    public void isUp1190(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65107));
    }
    @Test
    public void isUp1191(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65127));
    }
    @Test
    public void isUp1192(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65132));
    }
    @Test
    public void isUp1193(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65135));
    }
    @Test
    public void isUp1194(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65141));
    }
    @Test
    public void isUp1195(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65277));
    }
    @Test
    public void isUp1196(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65278));
    }
    @Test
    public void isUp1197(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65280));
    }
    @Test
    public void isUp1198(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65471));
    }
    @Test
    public void isUp1199(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65473));
    }
    @Test
    public void isUp1200(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65480));
    }
    @Test
    public void isUp1201(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65481));
    }
    @Test
    public void isUp1202(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65488));
    }
    @Test
    public void isUp1203(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65489));
    }
    @Test
    public void isUp1204(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65496));
    }
    @Test
    public void isUp1205(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65497));
    }
    @Test
    public void isUp1206(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65501));
    }
    @Test
    public void isUp1207(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65503));
    }
    @Test
    public void isUp1208(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65511));
    }
    @Test
    public void isUp1209(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65519));
    }
    @Test
    public void isUp1210(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65528));
    }
    @Test
    public void isUp1211(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65534));
    }
    @Test
    public void isUp1212(){
        assertTrue(StringDataUtil.isUpperCase((char) 1024));
    }
    @Test
    public void isUp1213(){
        assertTrue(StringDataUtil.isUpperCase((char) 8170));
    }
    @Test
    public void isUp1214(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65339));
    }
    @Test
    public void isUp1215(){
        assertTrue(StringDataUtil.isUpperCase((char) 11374));
    }
    @Test
    public void isUp1216(){
        assertTrue(StringDataUtil.isUpperCase((char) 11375));
    }
    @Test
    public void isUp1217(){
        assertTrue(StringDataUtil.isUpperCase((char) 11363));
    }
    @Test
    public void isUp1218(){
        assertTrue(StringDataUtil.isUpperCase((char) 580));
    }
    @Test
    public void isUp1219(){
        assertTrue(StringDataUtil.isUpperCase((char) 581));
    }
    @Test
    public void isUp1220(){
        assertTrue(StringDataUtil.isUpperCase((char) 503));
    }
    @Test
    public void isUp1221(){
        assertTrue(StringDataUtil.isUpperCase((char) 407));
    }
    @Test
    public void isUp1222(){
        assertTrue(StringDataUtil.isUpperCase((char) 399));
    }
    @Test
    public void isUp1223(){
        assertTrue(StringDataUtil.isUpperCase((char) 400));
    }
    @Test
    public void isUp1224(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1014));
    }
    @Test
    public void isUp1225(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1542));
    }
    @Test
    public void isUp1226(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1544));
    }
    @Test
    public void isUp1227(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8260));
    }
    @Test
    public void isUp1228(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8274));
    }
    @Test
    public void isUp1229(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8314));
    }
    @Test
    public void isUp1230(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8316));
    }
    @Test
    public void isUp1231(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8330));
    }
    @Test
    public void isUp1232(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8332));
    }
    @Test
    public void isUp1233(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8472));
    }
    @Test
    public void isUp1234(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8512));
    }
    @Test
    public void isUp1235(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8516));
    }
    @Test
    public void isUp1236(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8523));
    }
    @Test
    public void isUp1237(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8592));
    }
    @Test
    public void isUp1238(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8596));
    }
    @Test
    public void isUp1239(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8602));
    }
    @Test
    public void isUp1240(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8603));
    }
    @Test
    public void isUp1241(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8608));
    }
    @Test
    public void isUp1242(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8611));
    }
    @Test
    public void isUp1243(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8614));
    }
    @Test
    public void isUp1244(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8622));
    }
    @Test
    public void isUp1245(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8654));
    }
    @Test
    public void isUp1246(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8655));
    }
    @Test
    public void isUp1247(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8658));
    }
    @Test
    public void isUp1248(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8660));
    }
    @Test
    public void isUp1249(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8692));
    }
    @Test
    public void isUp1250(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8959));
    }
    @Test
    public void isUp1251(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8968));
    }
    @Test
    public void isUp1252(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8971));
    }
    @Test
    public void isUp1253(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8992));
    }
    @Test
    public void isUp1254(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8993));
    }
    @Test
    public void isUp1255(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9084));
    }
    @Test
    public void isUp1256(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9115));
    }
    @Test
    public void isUp1257(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9139));
    }
    @Test
    public void isUp1258(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9180));
    }
    @Test
    public void isUp1259(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9185));
    }
    @Test
    public void isUp1260(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9655));
    }
    @Test
    public void isUp1261(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9665));
    }
    @Test
    public void isUp1262(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9720));
    }
    @Test
    public void isUp1263(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9727));
    }
    @Test
    public void isUp1264(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9839));
    }
    @Test
    public void isUp1265(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10176));
    }
    @Test
    public void isUp1266(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10180));
    }
    @Test
    public void isUp1267(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10183));
    }
    @Test
    public void isUp1268(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10213));
    }
    @Test
    public void isUp1269(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10224));
    }
    @Test
    public void isUp1270(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10239));
    }
    @Test
    public void isUp1271(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10496));
    }
    @Test
    public void isUp1272(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10626));
    }
    @Test
    public void isUp1273(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10649));
    }
    @Test
    public void isUp1274(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10711));
    }
    @Test
    public void isUp1275(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10716));
    }
    @Test
    public void isUp1276(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10747));
    }
    @Test
    public void isUp1277(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10750));
    }
    @Test
    public void isUp1278(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11007));
    }
    @Test
    public void isUp1279(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11056));
    }
    @Test
    public void isUp1280(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11076));
    }
    @Test
    public void isUp1281(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11079));
    }
    @Test
    public void isUp1282(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11084));
    }
    @Test
    public void isUp1283(){
        assertTrue(!StringDataUtil.isUpperCase((char) 64297));
    }
    @Test
    public void isUp1284(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65122));
    }
    @Test
    public void isUp1285(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65124));
    }
    @Test
    public void isUp1286(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65126));
    }
    @Test
    public void isUp1287(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65291));
    }
    @Test
    public void isUp1288(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65308));
    }
    @Test
    public void isUp1289(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65310));
    }
    @Test
    public void isUp1290(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65372));
    }
    @Test
    public void isUp1291(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65374));
    }
    @Test
    public void isUp1292(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65506));
    }
    @Test
    public void isUp1293(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65513));
    }
    @Test
    public void isUp1294(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65516));
    }
    @Test
    public void isUp1295(){
        assertTrue(!StringDataUtil.isUpperCase((char) 65517));
    }
    @Test
    public void isUp1296(){
        assertTrue(!StringDataUtil.isUpperCase((char) 11008));
    }
    @Test
    public void isUp1297(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10712));
    }
    @Test
    public void isUp1298(){
        assertTrue(!StringDataUtil.isUpperCase((char) 10627));
    }
    @Test
    public void isUp1299(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8960));
    }
    @Test
    public void isUp1300(){
        assertTrue(!StringDataUtil.isUpperCase((char) 247));
    }
    @Test
    public void isUp1301(){
        assertTrue(!StringDataUtil.isUpperCase((char) 215));
    }
    @Test
    public void isUp1302(){
        assertTrue(!StringDataUtil.isUpperCase((char) 177));
    }
    @Test
    public void isUp1303(){
        assertTrue(!StringDataUtil.isUpperCase((char) 172));
    }
    @Test
    public void isUp1304(){
        assertTrue(StringDataUtil.isUpperCase((char) 8544));
    }
    @Test
    public void isUp1305(){
        assertTrue(StringDataUtil.isUpperCase((char) 9398));
    }
    @Test
    public void isUp1306(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8560));
    }
    @Test
    public void isUp1307(){
        assertTrue(!StringDataUtil.isUpperCase((char) 9424));
    }
    @Test
    public void isUp1308(){
        assertTrue(StringDataUtil.isUpperCase((char) 391));
    }
    @Test
    public void isUp1309(){
        assertTrue(StringDataUtil.isUpperCase((char) 393));
    }
    @Test
    public void isUp1310(){
        assertTrue(StringDataUtil.isUpperCase((char) 395));
    }
    @Test
    public void isUp1311(){
        assertTrue(!StringDataUtil.isUpperCase((char) 397));
    }
    @Test
    public void isUp1312(){
        assertTrue(StringDataUtil.isUpperCase((char) 401));
    }
    @Test
    public void isUp1313(){
        assertTrue(StringDataUtil.isUpperCase((char) 403));
    }
    @Test
    public void isUp1314(){
        assertTrue(StringDataUtil.isUpperCase((char) 408));
    }
    @Test
    public void isUp1315(){
        assertTrue(!StringDataUtil.isUpperCase((char) 411));
    }
    @Test
    public void isUp1316(){
        assertTrue(StringDataUtil.isUpperCase((char) 423));
    }
    @Test
    public void isUp1317(){
        assertTrue(StringDataUtil.isUpperCase((char) 425));
    }
    @Test
    public void isUp1318(){
        assertTrue(StringDataUtil.isUpperCase((char) 428));
    }
    @Test
    public void isUp1319(){
        assertTrue(StringDataUtil.isUpperCase((char) 430));
    }
    @Test
    public void isUp1320(){
        assertTrue(StringDataUtil.isUpperCase((char) 431));
    }
    @Test
    public void isUp1321(){
        assertTrue(StringDataUtil.isUpperCase((char) 433));
    }
    @Test
    public void isUp1322(){
        assertTrue(StringDataUtil.isUpperCase((char) 440));
    }
    @Test
    public void isUp1323(){
        assertTrue(!StringDataUtil.isUpperCase((char) 442));
    }
    @Test
    public void isUp1324(){
        assertTrue(StringDataUtil.isUpperCase((char) 444));
    }
    @Test
    public void isUp1325(){
        assertTrue(!StringDataUtil.isUpperCase((char) 446));
    }
    @Test
    public void isUp1326(){
        assertTrue(StringDataUtil.isUpperCase((char) 452));
    }
    @Test
    public void isUp1327(){
        assertTrue(StringDataUtil.isUpperCase((char) 455));
    }
    @Test
    public void isUp1328(){
        assertTrue(StringDataUtil.isUpperCase((char) 458));
    }
    @Test
    public void isUp1329(){
        assertTrue(!StringDataUtil.isUpperCase((char) 459));
    }
    @Test
    public void isUp1330(){
        assertTrue(StringDataUtil.isUpperCase((char) 461));
    }
    @Test
    public void isUp1331(){
        assertTrue(!StringDataUtil.isUpperCase((char) 476));
    }
    @Test
    public void isUp1332(){
        assertTrue(StringDataUtil.isUpperCase((char) 478));
    }
    @Test
    public void isUp1333(){
        assertTrue(!StringDataUtil.isUpperCase((char) 498));
    }
    @Test
    public void isUp1334(){
        assertTrue(StringDataUtil.isUpperCase((char) 500));
    }
    @Test
    public void isUp1335(){
        assertTrue(!StringDataUtil.isUpperCase((char) 591));
    }
    @Test
    public void isUp1336(){
        assertTrue(!StringDataUtil.isUpperCase((char) 593));
    }
    @Test
    public void isUp1337(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1007));
    }
    @Test
    public void isUp1338(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1009));
    }
    @Test
    public void isUp1339(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1230));
    }
    @Test
    public void isUp1340(){
        assertTrue(StringDataUtil.isUpperCase((char) 1232));
    }
    @Test
    public void isUp1341(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7935));
    }
    @Test
    public void isUp1342(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7937));
    }
    @Test
    public void isUp1343(){
        assertTrue(!StringDataUtil.isUpperCase((char) 383));
    }
    @Test
    public void isUp1344(){
        assertTrue(!StringDataUtil.isUpperCase((char) 392));
    }
    @Test
    public void isUp1345(){
        assertTrue(!StringDataUtil.isUpperCase((char) 396));
    }
    @Test
    public void isUp1346(){
        assertTrue(!StringDataUtil.isUpperCase((char) 402));
    }
    @Test
    public void isUp1347(){
        assertTrue(!StringDataUtil.isUpperCase((char) 409));
    }
    @Test
    public void isUp1348(){
        assertTrue(!StringDataUtil.isUpperCase((char) 410));
    }
    @Test
    public void isUp1349(){
        assertTrue(!StringDataUtil.isUpperCase((char) 424));
    }
    @Test
    public void isUp1350(){
        assertTrue(!StringDataUtil.isUpperCase((char) 429));
    }
    @Test
    public void isUp1351(){
        assertTrue(!StringDataUtil.isUpperCase((char) 432));
    }
    @Test
    public void isUp1352(){
        assertTrue(!StringDataUtil.isUpperCase((char) 441));
    }
    @Test
    public void isUp1353(){
        assertTrue(!StringDataUtil.isUpperCase((char) 445));
    }
    @Test
    public void isUp1354(){
        assertTrue(!StringDataUtil.isUpperCase((char) 453));
    }
    @Test
    public void isUp1355(){
        assertTrue(!StringDataUtil.isUpperCase((char) 454));
    }
    @Test
    public void isUp1356(){
        assertTrue(!StringDataUtil.isUpperCase((char) 456));
    }
    @Test
    public void isUp1357(){
        assertTrue(!StringDataUtil.isUpperCase((char) 457));
    }
    @Test
    public void isUp1358(){
        assertTrue(!StringDataUtil.isUpperCase((char) 460));
    }
    @Test
    public void isUp1359(){
        assertTrue(!StringDataUtil.isUpperCase((char) 477));
    }
    @Test
    public void isUp1360(){
        assertTrue(!StringDataUtil.isUpperCase((char) 499));
    }
    @Test
    public void isUp1361(){
        assertTrue(!StringDataUtil.isUpperCase((char) 592));
    }
    @Test
    public void isUp1362(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1008));
    }
    @Test
    public void isUp1363(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1231));
    }
    @Test
    public void isUp1364(){
        assertTrue(!StringDataUtil.isUpperCase((char) 7936));
    }
    @Test
    public void isUp1365(){
        assertTrue(StringDataUtil.isUpperCase((char) 8473));
    }
    @Test
    public void isUp1366(){
        assertTrue(StringDataUtil.isUpperCase((char) 8517));
    }
    @Test
    public void isUp1367(){
        assertTrue(StringDataUtil.isUpperCase((char) 8499));
    }
    @Test
    public void isUp1368(){
        assertTrue(StringDataUtil.isUpperCase((char) 8469));
    }
    @Test
    public void isUp1369(){
        assertTrue(StringDataUtil.isUpperCase((char) 8488));
    }
    @Test
    public void isUp1370(){
        assertTrue(StringDataUtil.isUpperCase((char) 8455));
    }
    @Test
    public void isUp1371(){
        assertTrue(StringDataUtil.isUpperCase((char) 8484));
    }
    @Test
    public void isUp1372(){
        assertTrue(StringDataUtil.isUpperCase((char) 8450));
    }
    @Test
    public void isUp1373(){
        assertTrue(StringDataUtil.isUpperCase((char) 978));
    }
    @Test
    public void isUp1374(){
        assertTrue(StringDataUtil.isUpperCase((char) 8492));
    }
    @Test
    public void isUp1375(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8188));
    }
    @Test
    public void isUp1376(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8140));
    }
    @Test
    public void isUp1377(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8124));
    }
    @Test
    public void isUp1378(){
        assertTrue(!StringDataUtil.isUpperCase((char) 8072));
    }
    @Test
    public void isUp1379(){
        assertTrue(!StringDataUtil.isUpperCase((char) 1632));
    }
    @Test
    public void isUp1380(){
        assertTrue(!StringDataUtil.isUpperCase('0'));
    }
    @Test
    public void isUp1381(){
        assertTrue(!StringDataUtil.isUpperCase((char)160));
    }
    @Test
    public void isLow1(){
        assertTrue(!StringDataUtil.isLowerCase((char)8544));
    }
    @Test
    public void isLow2(){
        assertTrue(StringDataUtil.isLowerCase((char)8560));
    }
    @Test
    public void isLow3(){
        assertTrue(!StringDataUtil.isLowerCase((char)9398));
    }
    @Test
    public void isLow4(){
        assertTrue(StringDataUtil.isLowerCase((char)9424));
    }
    @Test
    public void isLow5(){
        assertTrue(!StringDataUtil.isLowerCase((char)8602));
    }
    @Test
    public void isLow6(){
        assertTrue(StringDataUtil.isLowerCase((char) 382));
    }
    @Test
    public void isLow7(){
        assertTrue(StringDataUtil.isLowerCase((char) 384));
    }
    @Test
    public void isLow8(){
        assertTrue(!StringDataUtil.isLowerCase((char) 391));
    }
    @Test
    public void isLow9(){
        assertTrue(!StringDataUtil.isLowerCase((char) 393));
    }
    @Test
    public void isLow10(){
        assertTrue(!StringDataUtil.isLowerCase((char) 395));
    }
    @Test
    public void isLow11(){
        assertTrue(StringDataUtil.isLowerCase((char) 397));
    }
    @Test
    public void isLow12(){
        assertTrue(!StringDataUtil.isLowerCase((char) 401));
    }
    @Test
    public void isLow13(){
        assertTrue(!StringDataUtil.isLowerCase((char) 403));
    }
    @Test
    public void isLow14(){
        assertTrue(!StringDataUtil.isLowerCase((char) 408));
    }
    @Test
    public void isLow15(){
        assertTrue(StringDataUtil.isLowerCase((char) 411));
    }
    @Test
    public void isLow16(){
        assertTrue(!StringDataUtil.isLowerCase((char) 423));
    }
    @Test
    public void isLow17(){
        assertTrue(!StringDataUtil.isLowerCase((char) 425));
    }
    @Test
    public void isLow18(){
        assertTrue(!StringDataUtil.isLowerCase((char) 428));
    }
    @Test
    public void isLow19(){
        assertTrue(!StringDataUtil.isLowerCase((char) 430));
    }
    @Test
    public void isLow20(){
        assertTrue(!StringDataUtil.isLowerCase((char) 431));
    }
    @Test
    public void isLow21(){
        assertTrue(!StringDataUtil.isLowerCase((char) 433));
    }
    @Test
    public void isLow22(){
        assertTrue(!StringDataUtil.isLowerCase((char) 440));
    }
    @Test
    public void isLow23(){
        assertTrue(StringDataUtil.isLowerCase((char) 442));
    }
    @Test
    public void isLow24(){
        assertTrue(!StringDataUtil.isLowerCase((char) 444));
    }
    @Test
    public void isLow25(){
        assertTrue(StringDataUtil.isLowerCase((char) 446));
    }
    @Test
    public void isLow26(){
        assertTrue(!StringDataUtil.isLowerCase((char) 453));
    }
    @Test
    public void isLow27(){
        assertTrue(!StringDataUtil.isLowerCase((char) 455));
    }
    @Test
    public void isLow28(){
        assertTrue(!StringDataUtil.isLowerCase((char) 456));
    }
    @Test
    public void isLow29(){
        assertTrue(!StringDataUtil.isLowerCase((char) 458));
    }
    @Test
    public void isLow30(){
        assertTrue(!StringDataUtil.isLowerCase((char) 459));
    }
    @Test
    public void isLow31(){
        assertTrue(!StringDataUtil.isLowerCase((char) 461));
    }
    @Test
    public void isLow32(){
        assertTrue(StringDataUtil.isLowerCase((char) 476));
    }
    @Test
    public void isLow33(){
        assertTrue(!StringDataUtil.isLowerCase((char) 478));
    }
    @Test
    public void isLow34(){
        assertTrue(!StringDataUtil.isLowerCase((char) 498));
    }
    @Test
    public void isLow35(){
        assertTrue(!StringDataUtil.isLowerCase((char) 500));
    }
    @Test
    public void isLow36(){
        assertTrue(StringDataUtil.isLowerCase((char) 591));
    }
    @Test
    public void isLow37(){
        assertTrue(StringDataUtil.isLowerCase((char) 593));
    }
    @Test
    public void isLow38(){
        assertTrue(StringDataUtil.isLowerCase((char) 1007));
    }
    @Test
    public void isLow39(){
        assertTrue(StringDataUtil.isLowerCase((char) 1009));
    }
    @Test
    public void isLow40(){
        assertTrue(StringDataUtil.isLowerCase((char) 1230));
    }
    @Test
    public void isLow41(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1232));
    }
    @Test
    public void isLow42(){
        assertTrue(StringDataUtil.isLowerCase((char) 7935));
    }
    @Test
    public void isLow43(){
        assertTrue(StringDataUtil.isLowerCase((char) 7937));
    }

    @Test
    public void isLow44(){
        assertTrue(StringDataUtil.isLowerCase((char) 383));
    }
    @Test
    public void isLow45(){
        assertTrue(StringDataUtil.isLowerCase((char) 392));
    }
    @Test
    public void isLow46(){
        assertTrue(StringDataUtil.isLowerCase((char) 396));
    }
    @Test
    public void isLow47(){
        assertTrue(StringDataUtil.isLowerCase((char) 402));
    }
    @Test
    public void isLow48(){
        assertTrue(StringDataUtil.isLowerCase((char) 409));
    }
    @Test
    public void isLow49(){
        assertTrue(StringDataUtil.isLowerCase((char) 410));
    }
    @Test
    public void isLow50(){
        assertTrue(StringDataUtil.isLowerCase((char) 424));
    }
    @Test
    public void isLow51(){
        assertTrue(StringDataUtil.isLowerCase((char) 429));
    }
    @Test
    public void isLow52(){
        assertTrue(StringDataUtil.isLowerCase((char) 432));
    }
    @Test
    public void isLow53(){
        assertTrue(StringDataUtil.isLowerCase((char) 441));
    }
    @Test
    public void isLow54(){
        assertTrue(StringDataUtil.isLowerCase((char) 445));
    }
    @Test
    public void isLow55(){
        assertTrue(StringDataUtil.isLowerCase((char) 454));
    }
    @Test
    public void isLow56(){
        assertTrue(StringDataUtil.isLowerCase((char) 457));
    }
    @Test
    public void isLow57(){
        assertTrue(StringDataUtil.isLowerCase((char) 460));
    }
    @Test
    public void isLow58(){
        assertTrue(StringDataUtil.isLowerCase((char) 477));
    }
    @Test
    public void isLow59(){
        assertTrue(StringDataUtil.isLowerCase((char) 499));
    }
    @Test
    public void isLow60(){
        assertTrue(StringDataUtil.isLowerCase((char) 592));
    }
    @Test
    public void isLow61(){
        assertTrue(StringDataUtil.isLowerCase((char) 1008));
    }
    @Test
    public void isLow62(){
        assertTrue(StringDataUtil.isLowerCase((char) 1231));
    }
    @Test
    public void isLow63(){
        assertTrue(StringDataUtil.isLowerCase((char) 7936));
    }

    @Test
    public void isLow64(){
        assertTrue(!StringDataUtil.isLowerCase((char) 48));
    }
    @Test
    public void isLow65(){
        assertTrue(!StringDataUtil.isLowerCase((char) 57));
    }
    @Test
    public void isLow66(){
        assertTrue(!StringDataUtil.isLowerCase((char) 127));
    }
    @Test
    public void isLow67(){
        assertTrue(!StringDataUtil.isLowerCase((char) 160));
    }
    @Test
    public void isLow68(){
        assertTrue(StringDataUtil.isLowerCase((char) 170));
    }
    @Test
    public void isLow69(){
        assertTrue(StringDataUtil.isLowerCase((char) 186));
    }
    @Test
    public void isLow70(){
        assertTrue(StringDataUtil.isLowerCase((char) 223));
    }
    @Test
    public void isLow71(){
        assertTrue(StringDataUtil.isLowerCase((char) 312));
    }
    @Test
    public void isLow72(){
        assertTrue(StringDataUtil.isLowerCase((char) 329));
    }
    @Test
    public void isLow73(){
        assertTrue(StringDataUtil.isLowerCase((char) 397));
    }
    @Test
    public void isLow74(){
        assertTrue(StringDataUtil.isLowerCase((char) 411));
    }
    @Test
    public void isLow75(){
        assertTrue(StringDataUtil.isLowerCase((char) 426));
    }
    @Test
    public void isLow76(){
        assertTrue(StringDataUtil.isLowerCase((char) 427));
    }
    @Test
    public void isLow77(){
        assertTrue(StringDataUtil.isLowerCase((char) 442));
    }
    @Test
    public void isLow78(){
        assertTrue(StringDataUtil.isLowerCase((char) 446));
    }
    @Test
    public void isLow79(){
        assertTrue(!StringDataUtil.isLowerCase((char) 453));
    }
    @Test
    public void isLow80(){
        assertTrue(!StringDataUtil.isLowerCase((char) 456));
    }
    @Test
    public void isLow81(){
        assertTrue(!StringDataUtil.isLowerCase((char) 459));
    }
    @Test
    public void isLow82(){
        assertTrue(StringDataUtil.isLowerCase((char) 496));
    }
    @Test
    public void isLow83(){
        assertTrue(!StringDataUtil.isLowerCase((char) 498));
    }
    @Test
    public void isLow84(){
        assertTrue(StringDataUtil.isLowerCase((char) 545));
    }
    @Test
    public void isLow85(){
        assertTrue(StringDataUtil.isLowerCase((char) 564));
    }
    @Test
    public void isLow86(){
        assertTrue(StringDataUtil.isLowerCase((char) 569));
    }
    @Test
    public void isLow87(){
        assertTrue(StringDataUtil.isLowerCase((char) 597));
    }
    @Test
    public void isLow88(){
        assertTrue(StringDataUtil.isLowerCase((char) 600));
    }
    @Test
    public void isLow89(){
        assertTrue(StringDataUtil.isLowerCase((char) 602));
    }
    @Test
    public void isLow90(){
        assertTrue(StringDataUtil.isLowerCase((char) 604));
    }
    @Test
    public void isLow91(){
        assertTrue(StringDataUtil.isLowerCase((char) 607));
    }
    @Test
    public void isLow92(){
        assertTrue(StringDataUtil.isLowerCase((char) 609));
    }
    @Test
    public void isLow93(){
        assertTrue(StringDataUtil.isLowerCase((char) 610));
    }
    @Test
    public void isLow94(){
        assertTrue(StringDataUtil.isLowerCase((char) 612));
    }
    @Test
    public void isLow95(){
        assertTrue(StringDataUtil.isLowerCase((char) 624));
    }
    @Test
    public void isLow96(){
        assertTrue(StringDataUtil.isLowerCase((char) 627));
    }
    @Test
    public void isLow97(){
        assertTrue(StringDataUtil.isLowerCase((char) 628));
    }
    @Test
    public void isLow98(){
        assertTrue(StringDataUtil.isLowerCase((char) 630));
    }
    @Test
    public void isLow99(){
        assertTrue(StringDataUtil.isLowerCase((char) 636));
    }
    @Test
    public void isLow100(){
        assertTrue(StringDataUtil.isLowerCase((char) 638));
    }
    @Test
    public void isLow101(){
        assertTrue(StringDataUtil.isLowerCase((char) 639));
    }
    @Test
    public void isLow102(){
        assertTrue(StringDataUtil.isLowerCase((char) 641));
    }
    @Test
    public void isLow103(){
        assertTrue(StringDataUtil.isLowerCase((char) 647));
    }
    @Test
    public void isLow104(){
        assertTrue(StringDataUtil.isLowerCase((char) 653));
    }
    @Test
    public void isLow105(){
        assertTrue(StringDataUtil.isLowerCase((char) 696));
    }
    @Test
    public void isLow106(){
        assertTrue(StringDataUtil.isLowerCase((char) 704));
    }
    @Test
    public void isLow107(){
        assertTrue(StringDataUtil.isLowerCase((char) 705));
    }
    @Test
    public void isLow108(){
        assertTrue(StringDataUtil.isLowerCase((char) 736));
    }
    @Test
    public void isLow109(){
        assertTrue(StringDataUtil.isLowerCase((char) 740));
    }
    @Test
    public void isLow110(){
        assertTrue(!StringDataUtil.isLowerCase((char) 888));
    }
    @Test
    public void isLow111(){
        assertTrue(StringDataUtil.isLowerCase((char) 890));
    }
    @Test
    public void isLow112(){
        assertTrue(!StringDataUtil.isLowerCase((char) 895));
    }
    @Test
    public void isLow113(){
        assertTrue(!StringDataUtil.isLowerCase((char) 899));
    }
    @Test
    public void isLow114(){
        assertTrue(!StringDataUtil.isLowerCase((char) 907));
    }
    @Test
    public void isLow115(){
        assertTrue(!StringDataUtil.isLowerCase((char) 909));
    }
    @Test
    public void isLow116(){
        assertTrue(StringDataUtil.isLowerCase((char) 912));
    }
    @Test
    public void isLow117(){
        assertTrue(!StringDataUtil.isLowerCase((char) 930));
    }
    @Test
    public void isLow118(){
        assertTrue(StringDataUtil.isLowerCase((char) 944));
    }
    @Test
    public void isLow119(){
        assertTrue(StringDataUtil.isLowerCase((char) 1011));
    }
    @Test
    public void isLow120(){
        assertTrue(StringDataUtil.isLowerCase((char) 1020));
    }
    @Test
    public void isLow121(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1320));
    }
    @Test
    public void isLow122(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1328));
    }
    @Test
    public void isLow123(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1367));
    }
    @Test
    public void isLow124(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1368));
    }
    @Test
    public void isLow125(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1376));
    }
    @Test
    public void isLow126(){
        assertTrue(StringDataUtil.isLowerCase((char) 1415));
    }
    @Test
    public void isLow127(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1416));
    }
    @Test
    public void isLow128(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1419));
    }
    @Test
    public void isLow129(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1422));
    }
    @Test
    public void isLow130(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1424));
    }
    @Test
    public void isLow131(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1480));
    }
    @Test
    public void isLow132(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1487));
    }
    @Test
    public void isLow133(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1515));
    }
    @Test
    public void isLow134(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1519));
    }
    @Test
    public void isLow135(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1525));
    }
    @Test
    public void isLow136(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1535));
    }
    @Test
    public void isLow137(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1541));
    }
    @Test
    public void isLow138(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1564));
    }
    @Test
    public void isLow139(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1565));
    }
    @Test
    public void isLow140(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1632));
    }
    @Test
    public void isLow141(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1641));
    }
    @Test
    public void isLow142(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1776));
    }
    @Test
    public void isLow143(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1785));
    }
    @Test
    public void isLow144(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1806));
    }
    @Test
    public void isLow145(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1867));
    }
    @Test
    public void isLow146(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1868));
    }
    @Test
    public void isLow147(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1970));
    }
    @Test
    public void isLow148(){
        assertTrue(!StringDataUtil.isLowerCase((char) 1993));
    }
    @Test
    public void isLow149(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2043));
    }
    @Test
    public void isLow150(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2047));
    }
    @Test
    public void isLow151(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2094));
    }
    @Test
    public void isLow152(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2095));
    }
    @Test
    public void isLow153(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2111));
    }
    @Test
    public void isLow154(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2140));
    }
    @Test
    public void isLow155(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2141));
    }
    @Test
    public void isLow156(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2143));
    }
    @Test
    public void isLow157(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2207));
    }
    @Test
    public void isLow158(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2209));
    }
    @Test
    public void isLow159(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2221));
    }
    @Test
    public void isLow160(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2275));
    }
    @Test
    public void isLow161(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2303));
    }
    @Test
    public void isLow162(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2406));
    }
    @Test
    public void isLow163(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2415));
    }
    @Test
    public void isLow164(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2424));
    }
    @Test
    public void isLow165(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2432));
    }
    @Test
    public void isLow166(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2436));
    }
    @Test
    public void isLow167(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2445));
    }
    @Test
    public void isLow168(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2446));
    }
    @Test
    public void isLow169(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2449));
    }
    @Test
    public void isLow170(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2450));
    }
    @Test
    public void isLow171(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2473));
    }
    @Test
    public void isLow172(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2481));
    }
    @Test
    public void isLow173(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2483));
    }
    @Test
    public void isLow174(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2485));
    }
    @Test
    public void isLow175(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2490));
    }
    @Test
    public void isLow176(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2491));
    }
    @Test
    public void isLow177(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2501));
    }
    @Test
    public void isLow178(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2502));
    }
    @Test
    public void isLow179(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2505));
    }
    @Test
    public void isLow180(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2506));
    }
    @Test
    public void isLow181(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2511));
    }
    @Test
    public void isLow182(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2518));
    }
    @Test
    public void isLow183(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2520));
    }
    @Test
    public void isLow184(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2523));
    }
    @Test
    public void isLow185(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2526));
    }
    @Test
    public void isLow186(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2532));
    }
    @Test
    public void isLow187(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2543));
    }
    @Test
    public void isLow188(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2556));
    }
    @Test
    public void isLow189(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2560));
    }
    @Test
    public void isLow190(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2564));
    }
    @Test
    public void isLow191(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2571));
    }
    @Test
    public void isLow192(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2574));
    }
    @Test
    public void isLow193(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2577));
    }
    @Test
    public void isLow194(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2578));
    }
    @Test
    public void isLow195(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2601));
    }
    @Test
    public void isLow196(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2609));
    }
    @Test
    public void isLow197(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2612));
    }
    @Test
    public void isLow198(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2615));
    }
    @Test
    public void isLow199(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2618));
    }
    @Test
    public void isLow200(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2619));
    }
    @Test
    public void isLow201(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2621));
    }
    @Test
    public void isLow202(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2627));
    }
    @Test
    public void isLow203(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2630));
    }
    @Test
    public void isLow204(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2633));
    }
    @Test
    public void isLow205(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2634));
    }
    @Test
    public void isLow206(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2638));
    }
    @Test
    public void isLow207(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2640));
    }
    @Test
    public void isLow208(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2642));
    }
    @Test
    public void isLow209(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2648));
    }
    @Test
    public void isLow210(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2653));
    }
    @Test
    public void isLow211(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2655));
    }
    @Test
    public void isLow212(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2671));
    }
    @Test
    public void isLow213(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2678));
    }
    @Test
    public void isLow214(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2688));
    }
    @Test
    public void isLow215(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2692));
    }
    @Test
    public void isLow216(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2702));
    }
    @Test
    public void isLow217(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2706));
    }
    @Test
    public void isLow218(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2729));
    }
    @Test
    public void isLow219(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2737));
    }
    @Test
    public void isLow220(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2740));
    }
    @Test
    public void isLow221(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2746));
    }
    @Test
    public void isLow222(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2747));
    }
    @Test
    public void isLow223(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2758));
    }
    @Test
    public void isLow224(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2762));
    }
    @Test
    public void isLow225(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2766));
    }
    @Test
    public void isLow226(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2767));
    }
    @Test
    public void isLow227(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2769));
    }
    @Test
    public void isLow228(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2783));
    }
    @Test
    public void isLow229(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2788));
    }
    @Test
    public void isLow230(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2799));
    }
    @Test
    public void isLow231(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2802));
    }
    @Test
    public void isLow232(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2816));
    }
    @Test
    public void isLow233(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2820));
    }
    @Test
    public void isLow234(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2829));
    }
    @Test
    public void isLow235(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2830));
    }
    @Test
    public void isLow236(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2833));
    }
    @Test
    public void isLow237(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2834));
    }
    @Test
    public void isLow238(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2857));
    }
    @Test
    public void isLow239(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2865));
    }
    @Test
    public void isLow240(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2868));
    }
    @Test
    public void isLow241(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2874));
    }
    @Test
    public void isLow242(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2875));
    }
    @Test
    public void isLow243(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2885));
    }
    @Test
    public void isLow244(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2886));
    }
    @Test
    public void isLow245(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2889));
    }
    @Test
    public void isLow246(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2890));
    }
    @Test
    public void isLow247(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2894));
    }
    @Test
    public void isLow248(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2901));
    }
    @Test
    public void isLow249(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2904));
    }
    @Test
    public void isLow250(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2907));
    }
    @Test
    public void isLow251(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2910));
    }
    @Test
    public void isLow252(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2916));
    }
    @Test
    public void isLow253(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2927));
    }
    @Test
    public void isLow254(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2936));
    }
    @Test
    public void isLow255(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2945));
    }
    @Test
    public void isLow256(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2948));
    }
    @Test
    public void isLow257(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2955));
    }
    @Test
    public void isLow258(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2957));
    }
    @Test
    public void isLow259(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2961));
    }
    @Test
    public void isLow260(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2966));
    }
    @Test
    public void isLow261(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2968));
    }
    @Test
    public void isLow262(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2971));
    }
    @Test
    public void isLow263(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2973));
    }
    @Test
    public void isLow264(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2976));
    }
    @Test
    public void isLow265(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2978));
    }
    @Test
    public void isLow266(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2981));
    }
    @Test
    public void isLow267(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2983));
    }
    @Test
    public void isLow268(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2987));
    }
    @Test
    public void isLow269(){
        assertTrue(!StringDataUtil.isLowerCase((char) 2989));
    }
    @Test
    public void isLow270(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3002));
    }
    @Test
    public void isLow271(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3005));
    }
    @Test
    public void isLow272(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3011));
    }
    @Test
    public void isLow273(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3013));
    }
    @Test
    public void isLow274(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3017));
    }
    @Test
    public void isLow275(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3022));
    }
    @Test
    public void isLow276(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3023));
    }
    @Test
    public void isLow277(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3025));
    }
    @Test
    public void isLow278(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3030));
    }
    @Test
    public void isLow279(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3032));
    }
    @Test
    public void isLow280(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3055));
    }
    @Test
    public void isLow281(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3067));
    }
    @Test
    public void isLow282(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3072));
    }
    @Test
    public void isLow283(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3076));
    }
    @Test
    public void isLow284(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3085));
    }
    @Test
    public void isLow285(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3089));
    }
    @Test
    public void isLow286(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3113));
    }
    @Test
    public void isLow287(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3124));
    }
    @Test
    public void isLow288(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3130));
    }
    @Test
    public void isLow289(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3132));
    }
    @Test
    public void isLow290(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3141));
    }
    @Test
    public void isLow291(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3145));
    }
    @Test
    public void isLow292(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3150));
    }
    @Test
    public void isLow293(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3156));
    }
    @Test
    public void isLow294(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3159));
    }
    @Test
    public void isLow295(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3162));
    }
    @Test
    public void isLow296(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3167));
    }
    @Test
    public void isLow297(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3172));
    }
    @Test
    public void isLow298(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3191));
    }
    @Test
    public void isLow299(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3200));
    }
    @Test
    public void isLow300(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3201));
    }
    @Test
    public void isLow301(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3204));
    }
    @Test
    public void isLow302(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3213));
    }
    @Test
    public void isLow303(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3217));
    }
    @Test
    public void isLow304(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3241));
    }
    @Test
    public void isLow305(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3252));
    }
    @Test
    public void isLow306(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3258));
    }
    @Test
    public void isLow307(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3259));
    }
    @Test
    public void isLow308(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3269));
    }
    @Test
    public void isLow309(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3273));
    }
    @Test
    public void isLow310(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3278));
    }
    @Test
    public void isLow311(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3284));
    }
    @Test
    public void isLow312(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3287));
    }
    @Test
    public void isLow313(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3293));
    }
    @Test
    public void isLow314(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3295));
    }
    @Test
    public void isLow315(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3300));
    }
    @Test
    public void isLow316(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3312));
    }
    @Test
    public void isLow317(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3315));
    }
    @Test
    public void isLow318(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3329));
    }
    @Test
    public void isLow319(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3332));
    }
    @Test
    public void isLow320(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3341));
    }
    @Test
    public void isLow321(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3345));
    }
    @Test
    public void isLow322(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3387));
    }
    @Test
    public void isLow323(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3388));
    }
    @Test
    public void isLow324(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3397));
    }
    @Test
    public void isLow325(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3401));
    }
    @Test
    public void isLow326(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3407));
    }
    @Test
    public void isLow327(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3414));
    }
    @Test
    public void isLow328(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3416));
    }
    @Test
    public void isLow329(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3423));
    }
    @Test
    public void isLow330(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3428));
    }
    @Test
    public void isLow331(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3439));
    }
    @Test
    public void isLow332(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3446));
    }
    @Test
    public void isLow333(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3448));
    }
    @Test
    public void isLow334(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3456));
    }
    @Test
    public void isLow335(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3457));
    }
    @Test
    public void isLow336(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3460));
    }
    @Test
    public void isLow337(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3479));
    }
    @Test
    public void isLow338(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3481));
    }
    @Test
    public void isLow339(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3506));
    }
    @Test
    public void isLow340(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3516));
    }
    @Test
    public void isLow341(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3518));
    }
    @Test
    public void isLow342(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3519));
    }
    @Test
    public void isLow343(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3527));
    }
    @Test
    public void isLow344(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3529));
    }
    @Test
    public void isLow345(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3531));
    }
    @Test
    public void isLow346(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3534));
    }
    @Test
    public void isLow347(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3541));
    }
    @Test
    public void isLow348(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3543));
    }
    @Test
    public void isLow349(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3552));
    }
    @Test
    public void isLow350(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3569));
    }
    @Test
    public void isLow351(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3573));
    }
    @Test
    public void isLow352(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3584));
    }
    @Test
    public void isLow353(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3643));
    }
    @Test
    public void isLow354(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3646));
    }
    @Test
    public void isLow355(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3664));
    }
    @Test
    public void isLow356(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3673));
    }
    @Test
    public void isLow357(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3676));
    }
    @Test
    public void isLow358(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3712));
    }
    @Test
    public void isLow359(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3715));
    }
    @Test
    public void isLow360(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3717));
    }
    @Test
    public void isLow361(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3718));
    }
    @Test
    public void isLow362(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3721));
    }
    @Test
    public void isLow363(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3723));
    }
    @Test
    public void isLow364(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3724));
    }
    @Test
    public void isLow365(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3726));
    }
    @Test
    public void isLow366(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3731));
    }
    @Test
    public void isLow367(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3736));
    }
    @Test
    public void isLow368(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3744));
    }
    @Test
    public void isLow369(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3748));
    }
    @Test
    public void isLow370(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3750));
    }
    @Test
    public void isLow371(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3752));
    }
    @Test
    public void isLow372(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3753));
    }
    @Test
    public void isLow373(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3756));
    }
    @Test
    public void isLow374(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3770));
    }
    @Test
    public void isLow375(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3774));
    }
    @Test
    public void isLow376(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3775));
    }
    @Test
    public void isLow377(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3781));
    }
    @Test
    public void isLow378(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3783));
    }
    @Test
    public void isLow379(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3790));
    }
    @Test
    public void isLow380(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3803));
    }
    @Test
    public void isLow381(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3808));
    }
    @Test
    public void isLow382(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3839));
    }
    @Test
    public void isLow383(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3872));
    }
    @Test
    public void isLow384(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3881));
    }
    @Test
    public void isLow385(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3912));
    }
    @Test
    public void isLow386(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3949));
    }
    @Test
    public void isLow387(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3952));
    }
    @Test
    public void isLow388(){
        assertTrue(!StringDataUtil.isLowerCase((char) 3992));
    }
    @Test
    public void isLow389(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4029));
    }
    @Test
    public void isLow390(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4045));
    }
    @Test
    public void isLow391(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4059));
    }
    @Test
    public void isLow392(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4095));
    }
    @Test
    public void isLow393(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4160));
    }
    @Test
    public void isLow394(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4169));
    }
    @Test
    public void isLow395(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4240));
    }
    @Test
    public void isLow396(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4249));
    }
    @Test
    public void isLow397(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4294));
    }
    @Test
    public void isLow398(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4296));
    }
    @Test
    public void isLow399(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4300));
    }
    @Test
    public void isLow400(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4302));
    }
    @Test
    public void isLow401(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4303));
    }
    @Test
    public void isLow402(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4681));
    }
    @Test
    public void isLow403(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4686));
    }
    @Test
    public void isLow404(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4687));
    }
    @Test
    public void isLow405(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4695));
    }
    @Test
    public void isLow406(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4697));
    }
    @Test
    public void isLow407(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4702));
    }
    @Test
    public void isLow408(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4703));
    }
    @Test
    public void isLow409(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4745));
    }
    @Test
    public void isLow410(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4750));
    }
    @Test
    public void isLow411(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4751));
    }
    @Test
    public void isLow412(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4785));
    }
    @Test
    public void isLow413(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4790));
    }
    @Test
    public void isLow414(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4791));
    }
    @Test
    public void isLow415(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4799));
    }
    @Test
    public void isLow416(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4801));
    }
    @Test
    public void isLow417(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4806));
    }
    @Test
    public void isLow418(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4807));
    }
    @Test
    public void isLow419(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4823));
    }
    @Test
    public void isLow420(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4881));
    }
    @Test
    public void isLow421(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4886));
    }
    @Test
    public void isLow422(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4887));
    }
    @Test
    public void isLow423(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4955));
    }
    @Test
    public void isLow424(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4956));
    }
    @Test
    public void isLow425(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4989));
    }
    @Test
    public void isLow426(){
        assertTrue(!StringDataUtil.isLowerCase((char) 4991));
    }
    @Test
    public void isLow427(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5018));
    }
    @Test
    public void isLow428(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5023));
    }
    @Test
    public void isLow429(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5109));
    }
    @Test
    public void isLow430(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5119));
    }
    @Test
    public void isLow431(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5760));
    }
    @Test
    public void isLow432(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5789));
    }
    @Test
    public void isLow433(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5791));
    }
    @Test
    public void isLow434(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5873));
    }
    @Test
    public void isLow435(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5887));
    }
    @Test
    public void isLow436(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5901));
    }
    @Test
    public void isLow437(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5909));
    }
    @Test
    public void isLow438(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5919));
    }
    @Test
    public void isLow439(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5943));
    }
    @Test
    public void isLow440(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5951));
    }
    @Test
    public void isLow441(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5972));
    }
    @Test
    public void isLow442(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5983));
    }
    @Test
    public void isLow443(){
        assertTrue(!StringDataUtil.isLowerCase((char) 5997));
    }
    @Test
    public void isLow444(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6001));
    }
    @Test
    public void isLow445(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6004));
    }
    @Test
    public void isLow446(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6015));
    }
    @Test
    public void isLow447(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6110));
    }
    @Test
    public void isLow448(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6127));
    }
    @Test
    public void isLow449(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6138));
    }
    @Test
    public void isLow450(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6143));
    }
    @Test
    public void isLow451(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6158));
    }
    @Test
    public void isLow452(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6175));
    }
    @Test
    public void isLow453(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6264));
    }
    @Test
    public void isLow454(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6271));
    }
    @Test
    public void isLow455(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6315));
    }
    @Test
    public void isLow456(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6319));
    }
    @Test
    public void isLow457(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6390));
    }
    @Test
    public void isLow458(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6399));
    }
    @Test
    public void isLow459(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6429));
    }
    @Test
    public void isLow460(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6431));
    }
    @Test
    public void isLow461(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6444));
    }
    @Test
    public void isLow462(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6447));
    }
    @Test
    public void isLow463(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6460));
    }
    @Test
    public void isLow464(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6463));
    }
    @Test
    public void isLow465(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6465));
    }
    @Test
    public void isLow466(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6467));
    }
    @Test
    public void isLow467(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6470));
    }
    @Test
    public void isLow468(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6479));
    }
    @Test
    public void isLow469(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6510));
    }
    @Test
    public void isLow470(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6511));
    }
    @Test
    public void isLow471(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6517));
    }
    @Test
    public void isLow472(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6527));
    }
    @Test
    public void isLow473(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6572));
    }
    @Test
    public void isLow474(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6575));
    }
    @Test
    public void isLow475(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6602));
    }
    @Test
    public void isLow476(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6617));
    }
    @Test
    public void isLow477(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6619));
    }
    @Test
    public void isLow478(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6621));
    }
    @Test
    public void isLow479(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6684));
    }
    @Test
    public void isLow480(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6685));
    }
    @Test
    public void isLow481(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6751));
    }
    @Test
    public void isLow482(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6781));
    }
    @Test
    public void isLow483(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6782));
    }
    @Test
    public void isLow484(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6784));
    }
    @Test
    public void isLow485(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6815));
    }
    @Test
    public void isLow486(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6830));
    }
    @Test
    public void isLow487(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6911));
    }
    @Test
    public void isLow488(){
        assertTrue(!StringDataUtil.isLowerCase((char) 6988));
    }
    @Test
    public void isLow489(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7001));
    }
    @Test
    public void isLow490(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7037));
    }
    @Test
    public void isLow491(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7039));
    }
    @Test
    public void isLow492(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7088));
    }
    @Test
    public void isLow493(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7097));
    }
    @Test
    public void isLow494(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7156));
    }
    @Test
    public void isLow495(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7163));
    }
    @Test
    public void isLow496(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7224));
    }
    @Test
    public void isLow497(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7226));
    }
    @Test
    public void isLow498(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7232));
    }
    @Test
    public void isLow499(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7244));
    }
    @Test
    public void isLow500(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7248));
    }
    @Test
    public void isLow501(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7257));
    }
    @Test
    public void isLow502(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7296));
    }
    @Test
    public void isLow503(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7359));
    }
    @Test
    public void isLow504(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7368));
    }
    @Test
    public void isLow505(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7375));
    }
    @Test
    public void isLow506(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7415));
    }
    @Test
    public void isLow507(){
        assertTrue(StringDataUtil.isLowerCase((char) 7615));
    }
    @Test
    public void isLow508(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7655));
    }
    @Test
    public void isLow509(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7675));
    }
    @Test
    public void isLow510(){
        assertTrue(StringDataUtil.isLowerCase((char) 7830));
    }
    @Test
    public void isLow511(){
        assertTrue(StringDataUtil.isLowerCase((char) 7834));
    }
    @Test
    public void isLow512(){
        assertTrue(StringDataUtil.isLowerCase((char) 7836));
    }
    @Test
    public void isLow513(){
        assertTrue(StringDataUtil.isLowerCase((char) 7837));
    }
    @Test
    public void isLow514(){
        assertTrue(StringDataUtil.isLowerCase((char) 7839));
    }
    @Test
    public void isLow515(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7958));
    }
    @Test
    public void isLow516(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7959));
    }
    @Test
    public void isLow517(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7966));
    }
    @Test
    public void isLow518(){
        assertTrue(!StringDataUtil.isLowerCase((char) 7967));
    }
    @Test
    public void isLow519(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8006));
    }
    @Test
    public void isLow520(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8007));
    }
    @Test
    public void isLow521(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8014));
    }
    @Test
    public void isLow522(){
        assertTrue(StringDataUtil.isLowerCase((char) 8016));
    }
    @Test
    public void isLow523(){
        assertTrue(StringDataUtil.isLowerCase((char) 8018));
    }
    @Test
    public void isLow524(){
        assertTrue(StringDataUtil.isLowerCase((char) 8020));
    }
    @Test
    public void isLow525(){
        assertTrue(StringDataUtil.isLowerCase((char) 8022));
    }
    @Test
    public void isLow526(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8024));
    }
    @Test
    public void isLow527(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8026));
    }
    @Test
    public void isLow528(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8028));
    }
    @Test
    public void isLow529(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8030));
    }
    @Test
    public void isLow530(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8062));
    }
    @Test
    public void isLow531(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8063));
    }
    @Test
    public void isLow532(){
        assertTrue(StringDataUtil.isLowerCase((char) 8114));
    }
    @Test
    public void isLow533(){
        assertTrue(StringDataUtil.isLowerCase((char) 8116));
    }
    @Test
    public void isLow534(){
        assertTrue(StringDataUtil.isLowerCase((char) 8119));
    }
    @Test
    public void isLow535(){
        assertTrue(StringDataUtil.isLowerCase((char) 8130));
    }
    @Test
    public void isLow536(){
        assertTrue(StringDataUtil.isLowerCase((char) 8132));
    }
    @Test
    public void isLow537(){
        assertTrue(StringDataUtil.isLowerCase((char) 8135));
    }
    @Test
    public void isLow538(){
        assertTrue(StringDataUtil.isLowerCase((char) 8146));
    }
    @Test
    public void isLow539(){
        assertTrue(StringDataUtil.isLowerCase((char) 8151));
    }
    @Test
    public void isLow540(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8156));
    }
    @Test
    public void isLow541(){
        assertTrue(StringDataUtil.isLowerCase((char) 8162));
    }
    @Test
    public void isLow542(){
        assertTrue(StringDataUtil.isLowerCase((char) 8164));
    }
    @Test
    public void isLow543(){
        assertTrue(StringDataUtil.isLowerCase((char) 8166));
    }
    @Test
    public void isLow544(){
        assertTrue(StringDataUtil.isLowerCase((char) 8167));
    }
    @Test
    public void isLow545(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8176));
    }
    @Test
    public void isLow546(){
        assertTrue(StringDataUtil.isLowerCase((char) 8178));
    }
    @Test
    public void isLow547(){
        assertTrue(StringDataUtil.isLowerCase((char) 8180));
    }
    @Test
    public void isLow548(){
        assertTrue(StringDataUtil.isLowerCase((char) 8183));
    }
    @Test
    public void isLow549(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8191));
    }
    @Test
    public void isLow550(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8202));
    }
    @Test
    public void isLow551(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8239));
    }
    @Test
    public void isLow552(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8287));
    }
    @Test
    public void isLow553(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8293));
    }
    @Test
    public void isLow554(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8297));
    }
    @Test
    public void isLow555(){
        assertTrue(StringDataUtil.isLowerCase((char) 8305));
    }
    @Test
    public void isLow556(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8307));
    }
    @Test
    public void isLow557(){
        assertTrue(StringDataUtil.isLowerCase((char) 8319));
    }
    @Test
    public void isLow558(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8335));
    }
    @Test
    public void isLow559(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8351));
    }
    @Test
    public void isLow560(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8379));
    }
    @Test
    public void isLow561(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8399));
    }
    @Test
    public void isLow562(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8433));
    }
    @Test
    public void isLow563(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8447));
    }
    @Test
    public void isLow564(){
        assertTrue(StringDataUtil.isLowerCase((char) 8458));
    }
    @Test
    public void isLow565(){
        assertTrue(StringDataUtil.isLowerCase((char) 8462));
    }
    @Test
    public void isLow566(){
        assertTrue(StringDataUtil.isLowerCase((char) 8463));
    }
    @Test
    public void isLow567(){
        assertTrue(StringDataUtil.isLowerCase((char) 8467));
    }
    @Test
    public void isLow568(){
        assertTrue(StringDataUtil.isLowerCase((char) 8495));
    }
    @Test
    public void isLow569(){
        assertTrue(StringDataUtil.isLowerCase((char) 8500));
    }
    @Test
    public void isLow570(){
        assertTrue(StringDataUtil.isLowerCase((char) 8505));
    }
    @Test
    public void isLow571(){
        assertTrue(StringDataUtil.isLowerCase((char) 8508));
    }
    @Test
    public void isLow572(){
        assertTrue(StringDataUtil.isLowerCase((char) 8509));
    }
    @Test
    public void isLow573(){
        assertTrue(StringDataUtil.isLowerCase((char) 8518));
    }
    @Test
    public void isLow574(){
        assertTrue(StringDataUtil.isLowerCase((char) 8521));
    }
    @Test
    public void isLow575(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8586));
    }
    @Test
    public void isLow576(){
        assertTrue(!StringDataUtil.isLowerCase((char) 8591));
    }
    @Test
    public void isLow577(){
        assertTrue(!StringDataUtil.isLowerCase((char) 9204));
    }
    @Test
    public void isLow578(){
        assertTrue(!StringDataUtil.isLowerCase((char) 9215));
    }
    @Test
    public void isLow579(){
        assertTrue(!StringDataUtil.isLowerCase((char) 9255));
    }
    @Test
    public void isLow580(){
        assertTrue(!StringDataUtil.isLowerCase((char) 9279));
    }
    @Test
    public void isLow581(){
        assertTrue(!StringDataUtil.isLowerCase((char) 9291));
    }
    @Test
    public void isLow582(){
        assertTrue(!StringDataUtil.isLowerCase((char) 9311));
    }
    @Test
    public void isLow583(){
        assertTrue(!StringDataUtil.isLowerCase((char) 9984));
    }
    @Test
    public void isLow584(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11085));
    }
    @Test
    public void isLow585(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11087));
    }
    @Test
    public void isLow586(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11098));
    }
    @Test
    public void isLow587(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11263));
    }
    @Test
    public void isLow588(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11311));
    }
    @Test
    public void isLow589(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11359));
    }
    @Test
    public void isLow590(){
        assertTrue(StringDataUtil.isLowerCase((char) 11377));
    }
    @Test
    public void isLow591(){
        assertTrue(StringDataUtil.isLowerCase((char) 11380));
    }
    @Test
    public void isLow592(){
        assertTrue(StringDataUtil.isLowerCase((char) 11383));
    }
    @Test
    public void isLow593(){
        assertTrue(StringDataUtil.isLowerCase((char) 11389));
    }
    @Test
    public void isLow594(){
        assertTrue(StringDataUtil.isLowerCase((char) 11492));
    }
    @Test
    public void isLow595(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11508));
    }
    @Test
    public void isLow596(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11512));
    }
    @Test
    public void isLow597(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11558));
    }
    @Test
    public void isLow598(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11560));
    }
    @Test
    public void isLow599(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11564));
    }
    @Test
    public void isLow600(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11566));
    }
    @Test
    public void isLow601(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11567));
    }
    @Test
    public void isLow602(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11624));
    }
    @Test
    public void isLow603(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11630));
    }
    @Test
    public void isLow604(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11633));
    }
    @Test
    public void isLow605(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11646));
    }
    @Test
    public void isLow606(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11671));
    }
    @Test
    public void isLow607(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11679));
    }
    @Test
    public void isLow608(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11687));
    }
    @Test
    public void isLow609(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11695));
    }
    @Test
    public void isLow610(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11703));
    }
    @Test
    public void isLow611(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11711));
    }
    @Test
    public void isLow612(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11719));
    }
    @Test
    public void isLow613(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11727));
    }
    @Test
    public void isLow614(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11735));
    }
    @Test
    public void isLow615(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11743));
    }
    @Test
    public void isLow616(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11836));
    }
    @Test
    public void isLow617(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11903));
    }
    @Test
    public void isLow618(){
        assertTrue(!StringDataUtil.isLowerCase((char) 11930));
    }
    @Test
    public void isLow619(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12020));
    }
    @Test
    public void isLow620(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12031));
    }
    @Test
    public void isLow621(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12246));
    }
    @Test
    public void isLow622(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12271));
    }
    @Test
    public void isLow623(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12284));
    }
    @Test
    public void isLow624(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12288));
    }
    @Test
    public void isLow625(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12352));
    }
    @Test
    public void isLow626(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12439));
    }
    @Test
    public void isLow627(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12440));
    }
    @Test
    public void isLow628(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12544));
    }
    @Test
    public void isLow629(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12548));
    }
    @Test
    public void isLow630(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12590));
    }
    @Test
    public void isLow631(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12592));
    }
    @Test
    public void isLow632(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12687));
    }
    @Test
    public void isLow633(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12731));
    }
    @Test
    public void isLow634(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12735));
    }
    @Test
    public void isLow635(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12772));
    }
    @Test
    public void isLow636(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12783));
    }
    @Test
    public void isLow637(){
        assertTrue(!StringDataUtil.isLowerCase((char) 12831));
    }
    @Test
    public void isLow638(){
        assertTrue(!StringDataUtil.isLowerCase((char) 13055));
    }
    @Test
    public void isLow639(){
        assertTrue(!StringDataUtil.isLowerCase((char) 19894));
    }
    @Test
    public void isLow640(){
        assertTrue(!StringDataUtil.isLowerCase((char) 19903));
    }
    @Test
    public void isLow641(){
        assertTrue(!StringDataUtil.isLowerCase((char) 40909));
    }
    @Test
    public void isLow642(){
        assertTrue(!StringDataUtil.isLowerCase((char) 40959));
    }
    @Test
    public void isLow643(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42125));
    }
    @Test
    public void isLow644(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42127));
    }
    @Test
    public void isLow645(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42183));
    }
    @Test
    public void isLow646(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42191));
    }
    @Test
    public void isLow647(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42528));
    }
    @Test
    public void isLow648(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42537));
    }
    @Test
    public void isLow649(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42540));
    }
    @Test
    public void isLow650(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42559));
    }
    @Test
    public void isLow651(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42648));
    }
    @Test
    public void isLow652(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42654));
    }
    @Test
    public void isLow653(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42744));
    }
    @Test
    public void isLow654(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42751));
    }
    @Test
    public void isLow655(){
        assertTrue(StringDataUtil.isLowerCase((char) 42800));
    }
    @Test
    public void isLow656(){
        assertTrue(StringDataUtil.isLowerCase((char) 42801));
    }
    @Test
    public void isLow657(){
        assertTrue(StringDataUtil.isLowerCase((char) 42864));
    }
    @Test
    public void isLow658(){
        assertTrue(StringDataUtil.isLowerCase((char) 42872));
    }
    @Test
    public void isLow659(){
        assertTrue(StringDataUtil.isLowerCase((char) 42894));
    }
    @Test
    public void isLow660(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42895));
    }
    @Test
    public void isLow661(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42900));
    }
    @Test
    public void isLow662(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42911));
    }
    @Test
    public void isLow663(){
        assertTrue(!StringDataUtil.isLowerCase((char) 42923));
    }
    @Test
    public void isLow664(){
        assertTrue(StringDataUtil.isLowerCase((char) 43002));
    }
    @Test
    public void isLow665(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43052));
    }
    @Test
    public void isLow666(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43055));
    }
    @Test
    public void isLow667(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43066));
    }
    @Test
    public void isLow668(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43071));
    }
    @Test
    public void isLow669(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43128));
    }
    @Test
    public void isLow670(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43135));
    }
    @Test
    public void isLow671(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43205));
    }
    @Test
    public void isLow672(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43213));
    }
    @Test
    public void isLow673(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43216));
    }
    @Test
    public void isLow674(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43231));
    }
    @Test
    public void isLow675(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43260));
    }
    @Test
    public void isLow676(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43273));
    }
    @Test
    public void isLow677(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43348));
    }
    @Test
    public void isLow678(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43358));
    }
    @Test
    public void isLow679(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43389));
    }
    @Test
    public void isLow680(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43391));
    }
    @Test
    public void isLow681(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43470));
    }
    @Test
    public void isLow682(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43472));
    }
    @Test
    public void isLow683(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43485));
    }
    @Test
    public void isLow684(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43488));
    }
    @Test
    public void isLow685(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43519));
    }
    @Test
    public void isLow686(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43575));
    }
    @Test
    public void isLow687(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43583));
    }
    @Test
    public void isLow688(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43598));
    }
    @Test
    public void isLow689(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43611));
    }
    @Test
    public void isLow690(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43644));
    }
    @Test
    public void isLow691(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43647));
    }
    @Test
    public void isLow692(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43715));
    }
    @Test
    public void isLow693(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43738));
    }
    @Test
    public void isLow694(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43767));
    }
    @Test
    public void isLow695(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43776));
    }
    @Test
    public void isLow696(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43783));
    }
    @Test
    public void isLow697(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43784));
    }
    @Test
    public void isLow698(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43791));
    }
    @Test
    public void isLow699(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43792));
    }
    @Test
    public void isLow700(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43799));
    }
    @Test
    public void isLow701(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43807));
    }
    @Test
    public void isLow702(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43815));
    }
    @Test
    public void isLow703(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43823));
    }
    @Test
    public void isLow704(){
        assertTrue(!StringDataUtil.isLowerCase((char) 43967));
    }
    @Test
    public void isLow705(){
        assertTrue(!StringDataUtil.isLowerCase((char) 44014));
    }
    @Test
    public void isLow706(){
        assertTrue(!StringDataUtil.isLowerCase((char) 44031));
    }
    @Test
    public void isLow707(){
        assertTrue(!StringDataUtil.isLowerCase((char) 55204));
    }
    @Test
    public void isLow708(){
        assertTrue(!StringDataUtil.isLowerCase((char) 55215));
    }
    @Test
    public void isLow709(){
        assertTrue(!StringDataUtil.isLowerCase((char) 55239));
    }
    @Test
    public void isLow710(){
        assertTrue(!StringDataUtil.isLowerCase((char) 55242));
    }
    @Test
    public void isLow711(){
        assertTrue(!StringDataUtil.isLowerCase((char) 55292));
    }
    @Test
    public void isLow712(){
        assertTrue(!StringDataUtil.isLowerCase((char) 55295));
    }
    @Test
    public void isLow713(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64110));
    }
    @Test
    public void isLow714(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64111));
    }
    @Test
    public void isLow715(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64218));
    }
    @Test
    public void isLow716(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64284));
    }
    @Test
    public void isLow717(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64311));
    }
    @Test
    public void isLow718(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64317));
    }
    @Test
    public void isLow719(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64319));
    }
    @Test
    public void isLow720(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64322));
    }
    @Test
    public void isLow721(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64325));
    }
    @Test
    public void isLow722(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64450));
    }
    @Test
    public void isLow723(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64466));
    }
    @Test
    public void isLow724(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64832));
    }
    @Test
    public void isLow725(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64847));
    }
    @Test
    public void isLow726(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64912));
    }
    @Test
    public void isLow727(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64913));
    }
    @Test
    public void isLow728(){
        assertTrue(!StringDataUtil.isLowerCase((char) 64968));
    }
    @Test
    public void isLow729(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65007));
    }
    @Test
    public void isLow730(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65022));
    }
    @Test
    public void isLow731(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65023));
    }
    @Test
    public void isLow732(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65050));
    }
    @Test
    public void isLow733(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65055));
    }
    @Test
    public void isLow734(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65063));
    }
    @Test
    public void isLow735(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65071));
    }
    @Test
    public void isLow736(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65107));
    }
    @Test
    public void isLow737(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65127));
    }
    @Test
    public void isLow738(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65132));
    }
    @Test
    public void isLow739(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65135));
    }
    @Test
    public void isLow740(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65141));
    }
    @Test
    public void isLow741(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65277));
    }
    @Test
    public void isLow742(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65278));
    }
    @Test
    public void isLow743(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65280));
    }
    @Test
    public void isLow744(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65296));
    }
    @Test
    public void isLow745(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65305));
    }
    @Test
    public void isLow746(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65471));
    }
    @Test
    public void isLow747(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65473));
    }
    @Test
    public void isLow748(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65480));
    }
    @Test
    public void isLow749(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65481));
    }
    @Test
    public void isLow750(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65488));
    }
    @Test
    public void isLow751(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65489));
    }
    @Test
    public void isLow752(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65496));
    }
    @Test
    public void isLow753(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65497));
    }
    @Test
    public void isLow754(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65501));
    }
    @Test
    public void isLow755(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65503));
    }
    @Test
    public void isLow756(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65511));
    }
    @Test
    public void isLow757(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65519));
    }
    @Test
    public void isLow758(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65528));
    }
    @Test
    public void isLow759(){
        assertTrue(!StringDataUtil.isLowerCase((char) 65534));
    }
    @Test
    public void isLow760(){
        assertTrue(StringDataUtil.isLowerCase((char) 8336));
    }
    @Test
    public void isLow761(){
        assertTrue(!StringDataUtil.isLowerCase((char) 660));
    }

    @Test
    public void toUpper159(){
        assertEq(83, StringDataUtil.toUpperCase((char) 383));
    }
    @Test
    public void toUpper160(){
        assertEq(391, StringDataUtil.toUpperCase((char) 392));
    }
    @Test
    public void toUpper161(){
        assertEq(395, StringDataUtil.toUpperCase((char) 396));
    }
    @Test
    public void toUpper162(){
        assertEq(401, StringDataUtil.toUpperCase((char) 402));
    }
    @Test
    public void toUpper163(){
        assertEq(408, StringDataUtil.toUpperCase((char) 409));
    }
    @Test
    public void toUpper164(){
        assertEq(573, StringDataUtil.toUpperCase((char) 410));
    }
    @Test
    public void toUpper165(){
        assertEq(423, StringDataUtil.toUpperCase((char) 424));
    }
    @Test
    public void toUpper166(){
        assertEq(428, StringDataUtil.toUpperCase((char) 429));
    }
    @Test
    public void toUpper167(){
        assertEq(431, StringDataUtil.toUpperCase((char) 432));
    }
    @Test
    public void toUpper168(){
        assertEq(440, StringDataUtil.toUpperCase((char) 441));
    }
    @Test
    public void toUpper169(){
        assertEq(444, StringDataUtil.toUpperCase((char) 445));
    }
    @Test
    public void toUpper170(){
        assertEq(452, StringDataUtil.toUpperCase((char) 453));
    }
    @Test
    public void toUpper171(){
        assertEq(452, StringDataUtil.toUpperCase((char) 454));
    }
    @Test
    public void toUpper172(){
        assertEq(455, StringDataUtil.toUpperCase((char) 456));
    }
    @Test
    public void toUpper173(){
        assertEq(455, StringDataUtil.toUpperCase((char) 457));
    }
    @Test
    public void toUpper174(){
        assertEq(458, StringDataUtil.toUpperCase((char) 460));
    }
    @Test
    public void toUpper175(){
        assertEq(398, StringDataUtil.toUpperCase((char) 477));
    }
    @Test
    public void toUpper176(){
        assertEq(497, StringDataUtil.toUpperCase((char) 499));
    }
    @Test
    public void toUpper177(){
        assertEq(11375, StringDataUtil.toUpperCase((char) 592));
    }
    @Test
    public void toUpper178(){
        assertEq(922, StringDataUtil.toUpperCase((char) 1008));
    }
    @Test
    public void toUpper179(){
        assertEq(1216, StringDataUtil.toUpperCase((char) 1231));
    }
    @Test
    public void toUpper180(){
        assertEq(7944, StringDataUtil.toUpperCase((char) 7936));
    }
    @Test
    public void toUpper181(){
        assertEq(48, StringDataUtil.toUpperCase((char) 48));
    }
    @Test
    public void toUpper182(){
        assertEq(57, StringDataUtil.toUpperCase((char) 57));
    }
    @Test
    public void toUpper183(){
        assertEq(127, StringDataUtil.toUpperCase((char) 127));
    }
    @Test
    public void toUpper184(){
        assertEq(160, StringDataUtil.toUpperCase((char) 160));
    }
    @Test
    public void toUpper185(){
        assertEq(888, StringDataUtil.toUpperCase((char) 888));
    }
    @Test
    public void toUpper186(){
        assertEq(8544, StringDataUtil.toUpperCase((char) 8544));
    }
    @Test
    public void toUpper187(){
        assertEq(9398, StringDataUtil.toUpperCase((char) 9398));
    }
    @Test
    public void toUpper188(){
        assertEq(8602, StringDataUtil.toUpperCase((char) 8602));
    }
    @Test
    public void toUpper189(){
        assertEq(1632, StringDataUtil.toUpperCase((char) 1632));
    }
    @Test
    public void toLower158(){
        assertEq(1632, StringDataUtil.toLowerCase((char) 1632));
    }
    @Test
    public void toLower159(){
        assertEq(888, StringDataUtil.toLowerCase((char) 888));
    }
    @Test
    public void toLower160(){
        assertEq(160, StringDataUtil.toLowerCase((char) 160));
    }


    @Test
    public void getType0(){
        assertEq(StringDataUtil.SPACE_OTHER, StringDataUtil.getCustomType((char) 0));
    }
    @Test
    public void getType1(){
        assertEq(StringDataUtil.SPACE, StringDataUtil.getCustomType((char) 9));
    }
    @Test
    public void getType2(){
        assertEq(StringDataUtil.SPACE_OTHER, StringDataUtil.getCustomType((char) 11));
    }
    @Test
    public void getType3(){
        assertEq(StringDataUtil.SPACE, StringDataUtil.getCustomType((char) 32));
    }
    @Test
    public void getType4(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 33));
    }
    @Test
    public void getType5(){
        assertEq(StringDataUtil.QUOTES, StringDataUtil.getCustomType((char) 34));
    }
    @Test
    public void getType6(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 35));
    }
    @Test
    public void getType7(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 36));
    }
    @Test
    public void getType8(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 37));
    }
    @Test
    public void getType9(){
        assertEq(StringDataUtil.QUOTES, StringDataUtil.getCustomType((char) 39));
    }
    @Test
    public void getType10(){
        assertEq(StringDataUtil.DEL_LEFT, StringDataUtil.getCustomType((char) 40));
    }
    @Test
    public void getType11(){
        assertEq(StringDataUtil.DEL_RIGHT, StringDataUtil.getCustomType((char) 41));
    }
    @Test
    public void getType12(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 42));
    }
    @Test
    public void getType13(){
        assertEq(StringDataUtil.OPERATOR_SPEC, StringDataUtil.getCustomType((char) 44));
    }
    @Test
    public void getType14(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 45));
    }
    @Test
    public void getType15(){
        assertEq(StringDataUtil.OPERATOR_SPEC, StringDataUtil.getCustomType((char) 46));
    }
    @Test
    public void getType16(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 47));
    }
    @Test
    public void getType17(){
        assertEq(StringDataUtil.DIGIT_BASE, StringDataUtil.getCustomType((char) 48));
    }
    @Test
    public void getType18(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 58));
    }
    @Test
    public void getType19(){
        assertEq(StringDataUtil.PUNCTUATION, StringDataUtil.getCustomType((char) 59));
    }
    @Test
    public void getType20(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 60));
    }
    @Test
    public void getType21(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 65));
    }
    @Test
    public void getType22(){
        assertEq(StringDataUtil.DEL_LEFT, StringDataUtil.getCustomType((char) 91));
    }
    @Test
    public void getType23(){
        assertEq(StringDataUtil.ESCAPE, StringDataUtil.getCustomType((char) 92));
    }
    @Test
    public void getType24(){
        assertEq(StringDataUtil.DEL_RIGHT, StringDataUtil.getCustomType((char) 93));
    }
    @Test
    public void getType25(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 94));
    }
    @Test
    public void getType26(){
        assertEq(StringDataUtil.ID_SEP, StringDataUtil.getCustomType((char) 95));
    }
    @Test
    public void getType27(){
        assertEq(StringDataUtil.QUOTES, StringDataUtil.getCustomType((char) 96));
    }
    @Test
    public void getType28(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 97));
    }
    @Test
    public void getType29(){
        assertEq(StringDataUtil.DEL_LEFT, StringDataUtil.getCustomType((char) 123));
    }
    @Test
    public void getType30(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 124));
    }
    @Test
    public void getType31(){
        assertEq(StringDataUtil.DEL_RIGHT, StringDataUtil.getCustomType((char) 125));
    }
    @Test
    public void getType32(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 126));
    }
    @Test
    public void getType33(){
        assertEq(StringDataUtil.SPACE_OTHER, StringDataUtil.getCustomType((char) 127));
    }
    @Test
    public void getType34(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 161));
    }
    @Test
    public void getType35(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 162));
    }
    @Test
    public void getType36(){
        assertEq(StringDataUtil.MODIFIER, StringDataUtil.getCustomType((char) 166));
    }
    @Test
    public void getType37(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 167));
    }
    @Test
    public void getType38(){
        assertEq(StringDataUtil.MODIFIER, StringDataUtil.getCustomType((char) 168));
    }
    @Test
    public void getType39(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 170));
    }
    @Test
    public void getType40(){
        assertEq(StringDataUtil.PUNCTUATION_QUOTE, StringDataUtil.getCustomType((char) 171));
    }
    @Test
    public void getType41(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 172));
    }
    @Test
    public void getType42(){
        assertEq(StringDataUtil.MODIFIER, StringDataUtil.getCustomType((char) 173));
    }
    @Test
    public void getType43(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 177));
    }
    @Test
    public void getType44(){
        assertEq(StringDataUtil.MODIFIER, StringDataUtil.getCustomType((char) 178));
    }
    @Test
    public void getType45(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 181));
    }
    @Test
    public void getType46(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 182));
    }
    @Test
    public void getType47(){
        assertEq(StringDataUtil.MODIFIER, StringDataUtil.getCustomType((char) 184));
    }
    @Test
    public void getType48(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 186));
    }
    @Test
    public void getType49(){
        assertEq(StringDataUtil.PUNCTUATION_QUOTE, StringDataUtil.getCustomType((char) 187));
    }
    @Test
    public void getType50(){
        assertEq(StringDataUtil.MODIFIER, StringDataUtil.getCustomType((char) 188));
    }
    @Test
    public void getType51(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 191));
    }
    @Test
    public void getType52(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 192));
    }
    @Test
    public void getType53(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 215));
    }
    @Test
    public void getType54(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 216));
    }
    @Test
    public void getType55(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 223));
    }
    @Test
    public void getType56(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 224));
    }
    @Test
    public void getType57(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 247));
    }
    @Test
    public void getType58(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 248));
    }
    @Test
    public void getType59(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 256));
    }
    @Test
    public void getType60(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 257));
    }
    @Test
    public void getType61(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 258));
    }
    @Test
    public void getType62(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 259));
    }
    @Test
    public void getType63(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 260));
    }
    @Test
    public void getType64(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 261));
    }
    @Test
    public void getType65(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 262));
    }
    @Test
    public void getType66(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 263));
    }
    @Test
    public void getType67(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 264));
    }
    @Test
    public void getType68(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 265));
    }
    @Test
    public void getType69(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 266));
    }
    @Test
    public void getType70(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 267));
    }
    @Test
    public void getType71(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 268));
    }
    @Test
    public void getType72(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 269));
    }
    @Test
    public void getType73(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 270));
    }
    @Test
    public void getType74(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 271));
    }
    @Test
    public void getType75(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 272));
    }
    @Test
    public void getType76(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 273));
    }
    @Test
    public void getType77(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 274));
    }
    @Test
    public void getType78(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 275));
    }
    @Test
    public void getType79(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 276));
    }
    @Test
    public void getType80(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 277));
    }
    @Test
    public void getType81(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 278));
    }
    @Test
    public void getType82(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 279));
    }
    @Test
    public void getType83(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 280));
    }
    @Test
    public void getType84(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 281));
    }
    @Test
    public void getType85(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 282));
    }
    @Test
    public void getType86(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 283));
    }
    @Test
    public void getType87(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 284));
    }
    @Test
    public void getType88(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 285));
    }
    @Test
    public void getType89(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 286));
    }
    @Test
    public void getType90(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 287));
    }
    @Test
    public void getType91(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 288));
    }
    @Test
    public void getType92(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 289));
    }
    @Test
    public void getType93(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 290));
    }
    @Test
    public void getType94(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 291));
    }
    @Test
    public void getType95(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 292));
    }
    @Test
    public void getType96(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 293));
    }
    @Test
    public void getType97(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 294));
    }
    @Test
    public void getType98(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 295));
    }
    @Test
    public void getType99(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 296));
    }
    @Test
    public void getType100(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 297));
    }
    @Test
    public void getType101(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 298));
    }
    @Test
    public void getType102(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 299));
    }
    @Test
    public void getType103(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 300));
    }
    @Test
    public void getType104(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 301));
    }
    @Test
    public void getType105(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 302));
    }
    @Test
    public void getType106(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 303));
    }
    @Test
    public void getType107(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 304));
    }
    @Test
    public void getType108(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 305));
    }
    @Test
    public void getType109(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 306));
    }
    @Test
    public void getType110(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 307));
    }
    @Test
    public void getType111(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 308));
    }
    @Test
    public void getType112(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 309));
    }
    @Test
    public void getType113(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 310));
    }
    @Test
    public void getType114(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 311));
    }
    @Test
    public void getType115(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 312));
    }
    @Test
    public void getType116(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 313));
    }
    @Test
    public void getType117(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 314));
    }
    @Test
    public void getType118(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 315));
    }
    @Test
    public void getType119(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 316));
    }
    @Test
    public void getType120(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 317));
    }
    @Test
    public void getType121(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 318));
    }
    @Test
    public void getType122(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 319));
    }
    @Test
    public void getType123(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 320));
    }
    @Test
    public void getType124(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 321));
    }
    @Test
    public void getType125(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 322));
    }
    @Test
    public void getType126(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 323));
    }
    @Test
    public void getType127(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 324));
    }
    @Test
    public void getType128(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 325));
    }
    @Test
    public void getType129(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 326));
    }
    @Test
    public void getType130(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 327));
    }
    @Test
    public void getType131(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 328));
    }
    @Test
    public void getType132(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 329));
    }
    @Test
    public void getType133(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 330));
    }
    @Test
    public void getType134(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 331));
    }
    @Test
    public void getType135(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 332));
    }
    @Test
    public void getType136(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 333));
    }
    @Test
    public void getType137(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 334));
    }
    @Test
    public void getType138(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 335));
    }
    @Test
    public void getType139(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 336));
    }
    @Test
    public void getType140(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 337));
    }
    @Test
    public void getType141(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 338));
    }
    @Test
    public void getType142(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 339));
    }
    @Test
    public void getType143(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 340));
    }
    @Test
    public void getType144(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 341));
    }
    @Test
    public void getType145(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 342));
    }
    @Test
    public void getType146(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 343));
    }
    @Test
    public void getType147(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 344));
    }
    @Test
    public void getType148(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 345));
    }
    @Test
    public void getType149(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 346));
    }
    @Test
    public void getType150(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 347));
    }
    @Test
    public void getType151(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 348));
    }
    @Test
    public void getType152(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 349));
    }
    @Test
    public void getType153(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 350));
    }
    @Test
    public void getType154(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 351));
    }
    @Test
    public void getType155(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 352));
    }
    @Test
    public void getType156(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 353));
    }
    @Test
    public void getType157(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 354));
    }
    @Test
    public void getType158(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 355));
    }
    @Test
    public void getType159(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 356));
    }
    @Test
    public void getType160(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 357));
    }
    @Test
    public void getType161(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 358));
    }
    @Test
    public void getType162(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 359));
    }
    @Test
    public void getType163(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 360));
    }
    @Test
    public void getType164(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 361));
    }
    @Test
    public void getType165(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 362));
    }
    @Test
    public void getType166(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 363));
    }
    @Test
    public void getType167(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 364));
    }
    @Test
    public void getType168(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 365));
    }
    @Test
    public void getType169(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 366));
    }
    @Test
    public void getType170(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 367));
    }
    @Test
    public void getType171(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 368));
    }
    @Test
    public void getType172(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 369));
    }
    @Test
    public void getType173(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 370));
    }
    @Test
    public void getType174(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 371));
    }
    @Test
    public void getType175(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 372));
    }
    @Test
    public void getType176(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 373));
    }
    @Test
    public void getType177(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 374));
    }
    @Test
    public void getType178(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 375));
    }
    @Test
    public void getType179(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 376));
    }
    @Test
    public void getType180(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 378));
    }
    @Test
    public void getType181(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 379));
    }
    @Test
    public void getType182(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 380));
    }
    @Test
    public void getType183(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 381));
    }
    @Test
    public void getType184(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 382));
    }
    @Test
    public void getType185(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 385));
    }
    @Test
    public void getType186(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 387));
    }
    @Test
    public void getType187(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 388));
    }
    @Test
    public void getType188(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 389));
    }
    @Test
    public void getType189(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 390));
    }
    @Test
    public void getType190(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 392));
    }
    @Test
    public void getType191(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 393));
    }
    @Test
    public void getType192(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 396));
    }
    @Test
    public void getType193(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 397));
    }
    @Test
    public void getType194(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 398));
    }
    @Test
    public void getType195(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 402));
    }
    @Test
    public void getType196(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 403));
    }
    @Test
    public void getType197(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 405));
    }
    @Test
    public void getType198(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 406));
    }
    @Test
    public void getType199(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 409));
    }
    @Test
    public void getType200(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 411));
    }
    @Test
    public void getType201(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 412));
    }
    @Test
    public void getType202(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 414));
    }
    @Test
    public void getType203(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 415));
    }
    @Test
    public void getType204(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 417));
    }
    @Test
    public void getType205(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 418));
    }
    @Test
    public void getType206(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 419));
    }
    @Test
    public void getType207(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 420));
    }
    @Test
    public void getType208(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 421));
    }
    @Test
    public void getType209(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 422));
    }
    @Test
    public void getType210(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 424));
    }
    @Test
    public void getType211(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 425));
    }
    @Test
    public void getType212(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 426));
    }
    @Test
    public void getType213(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 428));
    }
    @Test
    public void getType214(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 429));
    }
    @Test
    public void getType215(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 430));
    }
    @Test
    public void getType216(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 432));
    }
    @Test
    public void getType217(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 433));
    }
    @Test
    public void getType218(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 436));
    }
    @Test
    public void getType219(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 437));
    }
    @Test
    public void getType220(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 438));
    }
    @Test
    public void getType221(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 439));
    }
    @Test
    public void getType222(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 441));
    }
    @Test
    public void getType223(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 442));
    }
    @Test
    public void getType224(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 443));
    }
    @Test
    public void getType225(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 444));
    }
    @Test
    public void getType226(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 445));
    }
    @Test
    public void getType227(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 446));
    }
    @Test
    public void getType228(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 447));
    }
    @Test
    public void getType229(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 448));
    }
    @Test
    public void getType230(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 452));
    }
    @Test
    public void getType231(){
        assertEq(StringDataUtil.LETTER_SENS_NO_CASE, StringDataUtil.getCustomType((char) 453));
    }
    @Test
    public void getType232(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 454));
    }
    @Test
    public void getType233(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 455));
    }
    @Test
    public void getType234(){
        assertEq(StringDataUtil.LETTER_SENS_NO_CASE, StringDataUtil.getCustomType((char) 456));
    }
    @Test
    public void getType235(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 457));
    }
    @Test
    public void getType236(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 458));
    }
    @Test
    public void getType237(){
        assertEq(StringDataUtil.LETTER_SENS_NO_CASE, StringDataUtil.getCustomType((char) 459));
    }
    @Test
    public void getType238(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 460));
    }
    @Test
    public void getType239(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 461));
    }
    @Test
    public void getType240(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 462));
    }
    @Test
    public void getType241(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 463));
    }
    @Test
    public void getType242(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 464));
    }
    @Test
    public void getType243(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 465));
    }
    @Test
    public void getType244(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 466));
    }
    @Test
    public void getType245(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 467));
    }
    @Test
    public void getType246(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 468));
    }
    @Test
    public void getType247(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 469));
    }
    @Test
    public void getType248(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 470));
    }
    @Test
    public void getType249(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 471));
    }
    @Test
    public void getType250(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 472));
    }
    @Test
    public void getType251(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 473));
    }
    @Test
    public void getType252(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 474));
    }
    @Test
    public void getType253(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 475));
    }
    @Test
    public void getType254(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 476));
    }
    @Test
    public void getType255(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 478));
    }
    @Test
    public void getType256(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 479));
    }
    @Test
    public void getType257(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 480));
    }
    @Test
    public void getType258(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 481));
    }
    @Test
    public void getType259(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 482));
    }
    @Test
    public void getType260(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 483));
    }
    @Test
    public void getType261(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 484));
    }
    @Test
    public void getType262(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 485));
    }
    @Test
    public void getType263(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 486));
    }
    @Test
    public void getType264(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 487));
    }
    @Test
    public void getType265(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 488));
    }
    @Test
    public void getType266(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 489));
    }
    @Test
    public void getType267(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 490));
    }
    @Test
    public void getType268(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 491));
    }
    @Test
    public void getType269(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 492));
    }
    @Test
    public void getType270(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 493));
    }
    @Test
    public void getType271(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 494));
    }
    @Test
    public void getType272(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 495));
    }
    @Test
    public void getType273(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 496));
    }
    @Test
    public void getType274(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 497));
    }
    @Test
    public void getType275(){
        assertEq(StringDataUtil.LETTER_SENS_NO_CASE, StringDataUtil.getCustomType((char) 498));
    }
    @Test
    public void getType276(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 499));
    }
    @Test
    public void getType277(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 500));
    }
    @Test
    public void getType278(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 501));
    }
    @Test
    public void getType279(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 502));
    }
    @Test
    public void getType280(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 505));
    }
    @Test
    public void getType281(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 506));
    }
    @Test
    public void getType282(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 507));
    }
    @Test
    public void getType283(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 508));
    }
    @Test
    public void getType284(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 509));
    }
    @Test
    public void getType285(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 510));
    }
    @Test
    public void getType286(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 511));
    }
    @Test
    public void getType287(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 512));
    }
    @Test
    public void getType288(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 513));
    }
    @Test
    public void getType289(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 514));
    }
    @Test
    public void getType290(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 515));
    }
    @Test
    public void getType291(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 516));
    }
    @Test
    public void getType292(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 517));
    }
    @Test
    public void getType293(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 518));
    }
    @Test
    public void getType294(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 519));
    }
    @Test
    public void getType295(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 520));
    }
    @Test
    public void getType296(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 521));
    }
    @Test
    public void getType297(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 522));
    }
    @Test
    public void getType298(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 523));
    }
    @Test
    public void getType299(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 524));
    }
    @Test
    public void getType300(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 525));
    }
    @Test
    public void getType301(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 526));
    }
    @Test
    public void getType302(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 527));
    }
    @Test
    public void getType303(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 528));
    }
    @Test
    public void getType304(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 529));
    }
    @Test
    public void getType305(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 530));
    }
    @Test
    public void getType306(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 531));
    }
    @Test
    public void getType307(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 532));
    }
    @Test
    public void getType308(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 533));
    }
    @Test
    public void getType309(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 534));
    }
    @Test
    public void getType310(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 535));
    }
    @Test
    public void getType311(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 536));
    }
    @Test
    public void getType312(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 537));
    }
    @Test
    public void getType313(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 538));
    }
    @Test
    public void getType314(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 539));
    }
    @Test
    public void getType315(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 540));
    }
    @Test
    public void getType316(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 541));
    }
    @Test
    public void getType317(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 542));
    }
    @Test
    public void getType318(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 543));
    }
    @Test
    public void getType319(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 544));
    }
    @Test
    public void getType320(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 545));
    }
    @Test
    public void getType321(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 546));
    }
    @Test
    public void getType322(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 547));
    }
    @Test
    public void getType323(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 548));
    }
    @Test
    public void getType324(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 549));
    }
    @Test
    public void getType325(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 550));
    }
    @Test
    public void getType326(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 551));
    }
    @Test
    public void getType327(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 552));
    }
    @Test
    public void getType328(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 553));
    }
    @Test
    public void getType329(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 554));
    }
    @Test
    public void getType330(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 555));
    }
    @Test
    public void getType331(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 556));
    }
    @Test
    public void getType332(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 557));
    }
    @Test
    public void getType333(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 558));
    }
    @Test
    public void getType334(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 559));
    }
    @Test
    public void getType335(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 560));
    }
    @Test
    public void getType336(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 561));
    }
    @Test
    public void getType337(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 562));
    }
    @Test
    public void getType338(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 563));
    }
    @Test
    public void getType339(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 564));
    }
    @Test
    public void getType340(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 570));
    }
    @Test
    public void getType341(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 572));
    }
    @Test
    public void getType342(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 573));
    }
    @Test
    public void getType343(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 575));
    }
    @Test
    public void getType344(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 577));
    }
    @Test
    public void getType345(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 578));
    }
    @Test
    public void getType346(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 579));
    }
    @Test
    public void getType347(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 583));
    }
    @Test
    public void getType348(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 584));
    }
    @Test
    public void getType349(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 585));
    }
    @Test
    public void getType350(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 586));
    }
    @Test
    public void getType351(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 587));
    }
    @Test
    public void getType352(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 588));
    }
    @Test
    public void getType353(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 589));
    }
    @Test
    public void getType354(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 590));
    }
    @Test
    public void getType355(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 591));
    }
    @Test
    public void getType356(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 597));
    }
    @Test
    public void getType357(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 598));
    }
    @Test
    public void getType358(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 600));
    }
    @Test
    public void getType359(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 601));
    }
    @Test
    public void getType360(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 602));
    }
    @Test
    public void getType361(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 603));
    }
    @Test
    public void getType362(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 604));
    }
    @Test
    public void getType363(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 608));
    }
    @Test
    public void getType364(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 609));
    }
    @Test
    public void getType365(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 611));
    }
    @Test
    public void getType366(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 612));
    }
    @Test
    public void getType367(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 613));
    }
    @Test
    public void getType368(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 615));
    }
    @Test
    public void getType369(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 616));
    }
    @Test
    public void getType370(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 618));
    }
    @Test
    public void getType371(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 619));
    }
    @Test
    public void getType372(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 620));
    }
    @Test
    public void getType373(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 623));
    }
    @Test
    public void getType374(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 624));
    }
    @Test
    public void getType375(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 625));
    }
    @Test
    public void getType376(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 627));
    }
    @Test
    public void getType377(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 629));
    }
    @Test
    public void getType378(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 630));
    }
    @Test
    public void getType379(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 637));
    }
    @Test
    public void getType380(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 638));
    }
    @Test
    public void getType381(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 640));
    }
    @Test
    public void getType382(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 641));
    }
    @Test
    public void getType383(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 643));
    }
    @Test
    public void getType384(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 644));
    }
    @Test
    public void getType385(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 648));
    }
    @Test
    public void getType386(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 653));
    }
    @Test
    public void getType387(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 658));
    }
    @Test
    public void getType388(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 659));
    }
    @Test
    public void getType389(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 660));
    }
    @Test
    public void getType390(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 661));
    }
    @Test
    public void getType391(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringDataUtil.getCustomType((char) 697));
    }
    @Test
    public void getType392(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 699));
    }
    @Test
    public void getType393(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 704));
    }
    @Test
    public void getType394(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 706));
    }
    @Test
    public void getType395(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringDataUtil.getCustomType((char) 710));
    }
    @Test
    public void getType396(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 720));
    }
    @Test
    public void getType397(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 722));
    }
    @Test
    public void getType398(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 736));
    }
    @Test
    public void getType399(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 741));
    }
    @Test
    public void getType400(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringDataUtil.getCustomType((char) 748));
    }
    @Test
    public void getType401(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 749));
    }
    @Test
    public void getType402(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 750));
    }
    @Test
    public void getType403(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 751));
    }
    @Test
    public void getType404(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 768));
    }
    @Test
    public void getType405(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 880));
    }
    @Test
    public void getType406(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 881));
    }
    @Test
    public void getType407(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 882));
    }
    @Test
    public void getType408(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 883));
    }
    @Test
    public void getType409(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringDataUtil.getCustomType((char) 884));
    }
    @Test
    public void getType410(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 885));
    }
    @Test
    public void getType411(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 886));
    }
    @Test
    public void getType412(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 887));
    }
    @Test
    public void getType413(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 888));
    }
    @Test
    public void getType414(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 890));
    }
    @Test
    public void getType415(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 891));
    }
    @Test
    public void getType416(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 894));
    }
    @Test
    public void getType417(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 895));
    }
    @Test
    public void getType418(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 900));
    }
    @Test
    public void getType419(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 902));
    }
    @Test
    public void getType420(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 903));
    }
    @Test
    public void getType421(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 904));
    }
    @Test
    public void getType422(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 907));
    }
    @Test
    public void getType423(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 908));
    }
    @Test
    public void getType424(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 909));
    }
    @Test
    public void getType425(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 910));
    }
    @Test
    public void getType426(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 912));
    }
    @Test
    public void getType427(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 913));
    }
    @Test
    public void getType428(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 930));
    }
    @Test
    public void getType429(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 931));
    }
    @Test
    public void getType430(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 940));
    }
    @Test
    public void getType431(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 944));
    }
    @Test
    public void getType432(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 945));
    }
    @Test
    public void getType433(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 975));
    }
    @Test
    public void getType434(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 976));
    }
    @Test
    public void getType435(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 978));
    }
    @Test
    public void getType436(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 981));
    }
    @Test
    public void getType437(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 984));
    }
    @Test
    public void getType438(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 985));
    }
    @Test
    public void getType439(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 986));
    }
    @Test
    public void getType440(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 987));
    }
    @Test
    public void getType441(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 988));
    }
    @Test
    public void getType442(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 989));
    }
    @Test
    public void getType443(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 990));
    }
    @Test
    public void getType444(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 991));
    }
    @Test
    public void getType445(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 992));
    }
    @Test
    public void getType446(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 993));
    }
    @Test
    public void getType447(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 994));
    }
    @Test
    public void getType448(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 995));
    }
    @Test
    public void getType449(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 996));
    }
    @Test
    public void getType450(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 997));
    }
    @Test
    public void getType451(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 998));
    }
    @Test
    public void getType452(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 999));
    }
    @Test
    public void getType453(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1000));
    }
    @Test
    public void getType454(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1001));
    }
    @Test
    public void getType455(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1002));
    }
    @Test
    public void getType456(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1003));
    }
    @Test
    public void getType457(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1004));
    }
    @Test
    public void getType458(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1005));
    }
    @Test
    public void getType459(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1006));
    }
    @Test
    public void getType460(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1007));
    }
    @Test
    public void getType461(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1011));
    }
    @Test
    public void getType462(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1012));
    }
    @Test
    public void getType463(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1013));
    }
    @Test
    public void getType464(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 1014));
    }
    @Test
    public void getType465(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1015));
    }
    @Test
    public void getType466(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1016));
    }
    @Test
    public void getType467(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1017));
    }
    @Test
    public void getType468(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1019));
    }
    @Test
    public void getType469(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1020));
    }
    @Test
    public void getType470(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1021));
    }
    @Test
    public void getType471(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1072));
    }
    @Test
    public void getType472(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1120));
    }
    @Test
    public void getType473(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1121));
    }
    @Test
    public void getType474(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1122));
    }
    @Test
    public void getType475(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1123));
    }
    @Test
    public void getType476(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1124));
    }
    @Test
    public void getType477(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1125));
    }
    @Test
    public void getType478(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1126));
    }
    @Test
    public void getType479(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1127));
    }
    @Test
    public void getType480(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1128));
    }
    @Test
    public void getType481(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1129));
    }
    @Test
    public void getType482(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1130));
    }
    @Test
    public void getType483(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1131));
    }
    @Test
    public void getType484(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1132));
    }
    @Test
    public void getType485(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1133));
    }
    @Test
    public void getType486(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1134));
    }
    @Test
    public void getType487(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1135));
    }
    @Test
    public void getType488(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1136));
    }
    @Test
    public void getType489(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1137));
    }
    @Test
    public void getType490(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1138));
    }
    @Test
    public void getType491(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1139));
    }
    @Test
    public void getType492(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1140));
    }
    @Test
    public void getType493(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1141));
    }
    @Test
    public void getType494(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1142));
    }
    @Test
    public void getType495(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1143));
    }
    @Test
    public void getType496(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1144));
    }
    @Test
    public void getType497(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1145));
    }
    @Test
    public void getType498(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1146));
    }
    @Test
    public void getType499(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1147));
    }
    @Test
    public void getType500(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1148));
    }
    @Test
    public void getType501(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1149));
    }
    @Test
    public void getType502(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1150));
    }
    @Test
    public void getType503(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1151));
    }
    @Test
    public void getType504(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1152));
    }
    @Test
    public void getType505(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1153));
    }
    @Test
    public void getType506(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 1154));
    }
    @Test
    public void getType507(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1155));
    }
    @Test
    public void getType508(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1162));
    }
    @Test
    public void getType509(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1163));
    }
    @Test
    public void getType510(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1164));
    }
    @Test
    public void getType511(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1165));
    }
    @Test
    public void getType512(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1166));
    }
    @Test
    public void getType513(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1167));
    }
    @Test
    public void getType514(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1168));
    }
    @Test
    public void getType515(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1169));
    }
    @Test
    public void getType516(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1170));
    }
    @Test
    public void getType517(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1171));
    }
    @Test
    public void getType518(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1172));
    }
    @Test
    public void getType519(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1173));
    }
    @Test
    public void getType520(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1174));
    }
    @Test
    public void getType521(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1175));
    }
    @Test
    public void getType522(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1176));
    }
    @Test
    public void getType523(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1177));
    }
    @Test
    public void getType524(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1178));
    }
    @Test
    public void getType525(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1179));
    }
    @Test
    public void getType526(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1180));
    }
    @Test
    public void getType527(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1181));
    }
    @Test
    public void getType528(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1182));
    }
    @Test
    public void getType529(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1183));
    }
    @Test
    public void getType530(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1184));
    }
    @Test
    public void getType531(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1185));
    }
    @Test
    public void getType532(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1186));
    }
    @Test
    public void getType533(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1187));
    }
    @Test
    public void getType534(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1188));
    }
    @Test
    public void getType535(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1189));
    }
    @Test
    public void getType536(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1190));
    }
    @Test
    public void getType537(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1191));
    }
    @Test
    public void getType538(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1192));
    }
    @Test
    public void getType539(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1193));
    }
    @Test
    public void getType540(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1194));
    }
    @Test
    public void getType541(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1195));
    }
    @Test
    public void getType542(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1196));
    }
    @Test
    public void getType543(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1197));
    }
    @Test
    public void getType544(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1198));
    }
    @Test
    public void getType545(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1199));
    }
    @Test
    public void getType546(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1200));
    }
    @Test
    public void getType547(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1201));
    }
    @Test
    public void getType548(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1202));
    }
    @Test
    public void getType549(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1203));
    }
    @Test
    public void getType550(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1204));
    }
    @Test
    public void getType551(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1205));
    }
    @Test
    public void getType552(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1206));
    }
    @Test
    public void getType553(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1207));
    }
    @Test
    public void getType554(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1208));
    }
    @Test
    public void getType555(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1209));
    }
    @Test
    public void getType556(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1210));
    }
    @Test
    public void getType557(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1211));
    }
    @Test
    public void getType558(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1212));
    }
    @Test
    public void getType559(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1213));
    }
    @Test
    public void getType560(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1214));
    }
    @Test
    public void getType561(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1215));
    }
    @Test
    public void getType562(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1216));
    }
    @Test
    public void getType563(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1218));
    }
    @Test
    public void getType564(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1219));
    }
    @Test
    public void getType565(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1220));
    }
    @Test
    public void getType566(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1221));
    }
    @Test
    public void getType567(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1222));
    }
    @Test
    public void getType568(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1223));
    }
    @Test
    public void getType569(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1224));
    }
    @Test
    public void getType570(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1225));
    }
    @Test
    public void getType571(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1226));
    }
    @Test
    public void getType572(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1227));
    }
    @Test
    public void getType573(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1228));
    }
    @Test
    public void getType574(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1229));
    }
    @Test
    public void getType575(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1230));
    }
    @Test
    public void getType576(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1232));
    }
    @Test
    public void getType577(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1233));
    }
    @Test
    public void getType578(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1234));
    }
    @Test
    public void getType579(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1235));
    }
    @Test
    public void getType580(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1236));
    }
    @Test
    public void getType581(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1237));
    }
    @Test
    public void getType582(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1238));
    }
    @Test
    public void getType583(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1239));
    }
    @Test
    public void getType584(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1240));
    }
    @Test
    public void getType585(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1241));
    }
    @Test
    public void getType586(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1242));
    }
    @Test
    public void getType587(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1243));
    }
    @Test
    public void getType588(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1244));
    }
    @Test
    public void getType589(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1245));
    }
    @Test
    public void getType590(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1246));
    }
    @Test
    public void getType591(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1247));
    }
    @Test
    public void getType592(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1248));
    }
    @Test
    public void getType593(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1249));
    }
    @Test
    public void getType594(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1250));
    }
    @Test
    public void getType595(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1251));
    }
    @Test
    public void getType596(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1252));
    }
    @Test
    public void getType597(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1253));
    }
    @Test
    public void getType598(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1254));
    }
    @Test
    public void getType599(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1255));
    }
    @Test
    public void getType600(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1256));
    }
    @Test
    public void getType601(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1257));
    }
    @Test
    public void getType602(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1258));
    }
    @Test
    public void getType603(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1259));
    }
    @Test
    public void getType604(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1260));
    }
    @Test
    public void getType605(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1261));
    }
    @Test
    public void getType606(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1262));
    }
    @Test
    public void getType607(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1263));
    }
    @Test
    public void getType608(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1264));
    }
    @Test
    public void getType609(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1265));
    }
    @Test
    public void getType610(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1266));
    }
    @Test
    public void getType611(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1267));
    }
    @Test
    public void getType612(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1268));
    }
    @Test
    public void getType613(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1269));
    }
    @Test
    public void getType614(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1270));
    }
    @Test
    public void getType615(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1271));
    }
    @Test
    public void getType616(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1272));
    }
    @Test
    public void getType617(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1273));
    }
    @Test
    public void getType618(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1274));
    }
    @Test
    public void getType619(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1275));
    }
    @Test
    public void getType620(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1276));
    }
    @Test
    public void getType621(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1277));
    }
    @Test
    public void getType622(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1278));
    }
    @Test
    public void getType623(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1279));
    }
    @Test
    public void getType624(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1280));
    }
    @Test
    public void getType625(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1281));
    }
    @Test
    public void getType626(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1282));
    }
    @Test
    public void getType627(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1283));
    }
    @Test
    public void getType628(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1284));
    }
    @Test
    public void getType629(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1285));
    }
    @Test
    public void getType630(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1286));
    }
    @Test
    public void getType631(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1287));
    }
    @Test
    public void getType632(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1288));
    }
    @Test
    public void getType633(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1289));
    }
    @Test
    public void getType634(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1290));
    }
    @Test
    public void getType635(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1291));
    }
    @Test
    public void getType636(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1292));
    }
    @Test
    public void getType637(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1293));
    }
    @Test
    public void getType638(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1294));
    }
    @Test
    public void getType639(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1295));
    }
    @Test
    public void getType640(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1296));
    }
    @Test
    public void getType641(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1297));
    }
    @Test
    public void getType642(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1298));
    }
    @Test
    public void getType643(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1299));
    }
    @Test
    public void getType644(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1300));
    }
    @Test
    public void getType645(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1301));
    }
    @Test
    public void getType646(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1302));
    }
    @Test
    public void getType647(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1303));
    }
    @Test
    public void getType648(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1304));
    }
    @Test
    public void getType649(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1305));
    }
    @Test
    public void getType650(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1306));
    }
    @Test
    public void getType651(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1307));
    }
    @Test
    public void getType652(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1308));
    }
    @Test
    public void getType653(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1309));
    }
    @Test
    public void getType654(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1310));
    }
    @Test
    public void getType655(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1311));
    }
    @Test
    public void getType656(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1312));
    }
    @Test
    public void getType657(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1313));
    }
    @Test
    public void getType658(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1314));
    }
    @Test
    public void getType659(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1315));
    }
    @Test
    public void getType660(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1316));
    }
    @Test
    public void getType661(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1317));
    }
    @Test
    public void getType662(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1318));
    }
    @Test
    public void getType663(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1319));
    }
    @Test
    public void getType664(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1320));
    }
    @Test
    public void getType665(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 1329));
    }
    @Test
    public void getType666(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1367));
    }
    @Test
    public void getType667(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 1369));
    }
    @Test
    public void getType668(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1370));
    }
    @Test
    public void getType669(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1376));
    }
    @Test
    public void getType670(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1377));
    }
    @Test
    public void getType671(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 1415));
    }
    @Test
    public void getType672(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1416));
    }
    @Test
    public void getType673(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1417));
    }
    @Test
    public void getType674(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 1418));
    }
    @Test
    public void getType675(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1419));
    }
    @Test
    public void getType676(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 1423));
    }
    @Test
    public void getType677(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1424));
    }
    @Test
    public void getType678(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1425));
    }
    @Test
    public void getType679(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 1470));
    }
    @Test
    public void getType680(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1471));
    }
    @Test
    public void getType681(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1472));
    }
    @Test
    public void getType682(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1473));
    }
    @Test
    public void getType683(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1475));
    }
    @Test
    public void getType684(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1476));
    }
    @Test
    public void getType685(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1478));
    }
    @Test
    public void getType686(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1479));
    }
    @Test
    public void getType687(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1480));
    }
    @Test
    public void getType688(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1488));
    }
    @Test
    public void getType689(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1515));
    }
    @Test
    public void getType690(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1520));
    }
    @Test
    public void getType691(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1523));
    }
    @Test
    public void getType692(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1525));
    }
    @Test
    public void getType693(){
        assertEq(StringDataUtil.SEPARATOR, StringDataUtil.getCustomType((char) 1536));
    }
    @Test
    public void getType694(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1541));
    }
    @Test
    public void getType695(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 1542));
    }
    @Test
    public void getType696(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1545));
    }
    @Test
    public void getType697(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 1547));
    }
    @Test
    public void getType698(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1548));
    }
    @Test
    public void getType699(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 1550));
    }
    @Test
    public void getType700(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1552));
    }
    @Test
    public void getType701(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1563));
    }
    @Test
    public void getType702(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1564));
    }
    @Test
    public void getType703(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1566));
    }
    @Test
    public void getType704(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1568));
    }
    @Test
    public void getType705(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1611));
    }
    @Test
    public void getType706(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 1632));
    }
    @Test
    public void getType707(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1642));
    }
    @Test
    public void getType708(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1646));
    }
    @Test
    public void getType709(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1648));
    }
    @Test
    public void getType710(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1649));
    }
    @Test
    public void getType711(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1748));
    }
    @Test
    public void getType712(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1749));
    }
    @Test
    public void getType713(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1750));
    }
    @Test
    public void getType714(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 1758));
    }
    @Test
    public void getType715(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1759));
    }
    @Test
    public void getType716(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1765));
    }
    @Test
    public void getType717(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1767));
    }
    @Test
    public void getType718(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 1769));
    }
    @Test
    public void getType719(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1770));
    }
    @Test
    public void getType720(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1774));
    }
    @Test
    public void getType721(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 1776));
    }
    @Test
    public void getType722(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1786));
    }
    @Test
    public void getType723(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 1789));
    }
    @Test
    public void getType724(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1791));
    }
    @Test
    public void getType725(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 1792));
    }
    @Test
    public void getType726(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1806));
    }
    @Test
    public void getType727(){
        assertEq(StringDataUtil.SEPARATOR, StringDataUtil.getCustomType((char) 1807));
    }
    @Test
    public void getType728(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1808));
    }
    @Test
    public void getType729(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1809));
    }
    @Test
    public void getType730(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1810));
    }
    @Test
    public void getType731(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1840));
    }
    @Test
    public void getType732(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1867));
    }
    @Test
    public void getType733(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1869));
    }
    @Test
    public void getType734(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 1958));
    }
    @Test
    public void getType735(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1969));
    }
    @Test
    public void getType736(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 1970));
    }
    @Test
    public void getType737(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 1984));
    }
    @Test
    public void getType738(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 1994));
    }
    @Test
    public void getType739(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2027));
    }
    @Test
    public void getType740(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 2036));
    }
    @Test
    public void getType741(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 2038));
    }
    @Test
    public void getType742(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 2039));
    }
    @Test
    public void getType743(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 2042));
    }
    @Test
    public void getType744(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2043));
    }
    @Test
    public void getType745(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 2048));
    }
    @Test
    public void getType746(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2070));
    }
    @Test
    public void getType747(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 2074));
    }
    @Test
    public void getType748(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2075));
    }
    @Test
    public void getType749(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 2084));
    }
    @Test
    public void getType750(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2085));
    }
    @Test
    public void getType751(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 2088));
    }
    @Test
    public void getType752(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2089));
    }
    @Test
    public void getType753(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2094));
    }
    @Test
    public void getType754(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 2096));
    }
    @Test
    public void getType755(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2111));
    }
    @Test
    public void getType756(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 2112));
    }
    @Test
    public void getType757(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2137));
    }
    @Test
    public void getType758(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2140));
    }
    @Test
    public void getType759(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 2142));
    }
    @Test
    public void getType760(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2143));
    }
    @Test
    public void getType761(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 2208));
    }
    @Test
    public void getType762(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2209));
    }
    @Test
    public void getType763(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 2210));
    }
    @Test
    public void getType764(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2221));
    }
    @Test
    public void getType765(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2276));
    }
    @Test
    public void getType766(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2303));
    }
    @Test
    public void getType767(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2304));
    }
    @Test
    public void getType768(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2308));
    }
    @Test
    public void getType769(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2362));
    }
    @Test
    public void getType770(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2365));
    }
    @Test
    public void getType771(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2366));
    }
    @Test
    public void getType772(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2384));
    }
    @Test
    public void getType773(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2385));
    }
    @Test
    public void getType774(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2392));
    }
    @Test
    public void getType775(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2402));
    }
    @Test
    public void getType776(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 2404));
    }
    @Test
    public void getType777(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 2406));
    }
    @Test
    public void getType778(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 2416));
    }
    @Test
    public void getType779(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2417));
    }
    @Test
    public void getType780(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2424));
    }
    @Test
    public void getType781(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2425));
    }
    @Test
    public void getType782(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2432));
    }
    @Test
    public void getType783(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2433));
    }
    @Test
    public void getType784(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2436));
    }
    @Test
    public void getType785(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2437));
    }
    @Test
    public void getType786(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2445));
    }
    @Test
    public void getType787(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2447));
    }
    @Test
    public void getType788(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2449));
    }
    @Test
    public void getType789(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2451));
    }
    @Test
    public void getType790(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2473));
    }
    @Test
    public void getType791(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2474));
    }
    @Test
    public void getType792(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2481));
    }
    @Test
    public void getType793(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2482));
    }
    @Test
    public void getType794(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2483));
    }
    @Test
    public void getType795(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2486));
    }
    @Test
    public void getType796(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2490));
    }
    @Test
    public void getType797(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2492));
    }
    @Test
    public void getType798(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2493));
    }
    @Test
    public void getType799(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2494));
    }
    @Test
    public void getType800(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2501));
    }
    @Test
    public void getType801(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2503));
    }
    @Test
    public void getType802(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2505));
    }
    @Test
    public void getType803(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2507));
    }
    @Test
    public void getType804(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2510));
    }
    @Test
    public void getType805(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2511));
    }
    @Test
    public void getType806(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2519));
    }
    @Test
    public void getType807(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2520));
    }
    @Test
    public void getType808(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2524));
    }
    @Test
    public void getType809(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2526));
    }
    @Test
    public void getType810(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2527));
    }
    @Test
    public void getType811(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2530));
    }
    @Test
    public void getType812(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2532));
    }
    @Test
    public void getType813(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 2534));
    }
    @Test
    public void getType814(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2544));
    }
    @Test
    public void getType815(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 2546));
    }
    @Test
    public void getType816(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 2548));
    }
    @Test
    public void getType817(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 2554));
    }
    @Test
    public void getType818(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 2555));
    }
    @Test
    public void getType819(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2556));
    }
    @Test
    public void getType820(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2561));
    }
    @Test
    public void getType821(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2564));
    }
    @Test
    public void getType822(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2565));
    }
    @Test
    public void getType823(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2571));
    }
    @Test
    public void getType824(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2575));
    }
    @Test
    public void getType825(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2577));
    }
    @Test
    public void getType826(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2579));
    }
    @Test
    public void getType827(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2601));
    }
    @Test
    public void getType828(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2602));
    }
    @Test
    public void getType829(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2609));
    }
    @Test
    public void getType830(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2610));
    }
    @Test
    public void getType831(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2612));
    }
    @Test
    public void getType832(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2613));
    }
    @Test
    public void getType833(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2615));
    }
    @Test
    public void getType834(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2616));
    }
    @Test
    public void getType835(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2618));
    }
    @Test
    public void getType836(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2620));
    }
    @Test
    public void getType837(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2621));
    }
    @Test
    public void getType838(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2622));
    }
    @Test
    public void getType839(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2627));
    }
    @Test
    public void getType840(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2631));
    }
    @Test
    public void getType841(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2633));
    }
    @Test
    public void getType842(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2635));
    }
    @Test
    public void getType843(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2638));
    }
    @Test
    public void getType844(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2641));
    }
    @Test
    public void getType845(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2642));
    }
    @Test
    public void getType846(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2649));
    }
    @Test
    public void getType847(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2653));
    }
    @Test
    public void getType848(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2654));
    }
    @Test
    public void getType849(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2655));
    }
    @Test
    public void getType850(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 2662));
    }
    @Test
    public void getType851(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2672));
    }
    @Test
    public void getType852(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2674));
    }
    @Test
    public void getType853(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2677));
    }
    @Test
    public void getType854(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2678));
    }
    @Test
    public void getType855(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2689));
    }
    @Test
    public void getType856(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2692));
    }
    @Test
    public void getType857(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2693));
    }
    @Test
    public void getType858(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2702));
    }
    @Test
    public void getType859(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2703));
    }
    @Test
    public void getType860(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2706));
    }
    @Test
    public void getType861(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2707));
    }
    @Test
    public void getType862(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2729));
    }
    @Test
    public void getType863(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2730));
    }
    @Test
    public void getType864(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2737));
    }
    @Test
    public void getType865(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2738));
    }
    @Test
    public void getType866(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2740));
    }
    @Test
    public void getType867(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2741));
    }
    @Test
    public void getType868(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2746));
    }
    @Test
    public void getType869(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2748));
    }
    @Test
    public void getType870(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2749));
    }
    @Test
    public void getType871(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2750));
    }
    @Test
    public void getType872(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2758));
    }
    @Test
    public void getType873(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2759));
    }
    @Test
    public void getType874(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2762));
    }
    @Test
    public void getType875(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2763));
    }
    @Test
    public void getType876(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2766));
    }
    @Test
    public void getType877(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2768));
    }
    @Test
    public void getType878(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2769));
    }
    @Test
    public void getType879(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2784));
    }
    @Test
    public void getType880(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2786));
    }
    @Test
    public void getType881(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2788));
    }
    @Test
    public void getType882(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 2790));
    }
    @Test
    public void getType883(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 2800));
    }
    @Test
    public void getType884(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 2801));
    }
    @Test
    public void getType885(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2802));
    }
    @Test
    public void getType886(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2817));
    }
    @Test
    public void getType887(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2820));
    }
    @Test
    public void getType888(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2821));
    }
    @Test
    public void getType889(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2829));
    }
    @Test
    public void getType890(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2831));
    }
    @Test
    public void getType891(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2833));
    }
    @Test
    public void getType892(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2835));
    }
    @Test
    public void getType893(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2857));
    }
    @Test
    public void getType894(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2858));
    }
    @Test
    public void getType895(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2865));
    }
    @Test
    public void getType896(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2866));
    }
    @Test
    public void getType897(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2868));
    }
    @Test
    public void getType898(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2869));
    }
    @Test
    public void getType899(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2874));
    }
    @Test
    public void getType900(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2876));
    }
    @Test
    public void getType901(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2877));
    }
    @Test
    public void getType902(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2878));
    }
    @Test
    public void getType903(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2885));
    }
    @Test
    public void getType904(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2887));
    }
    @Test
    public void getType905(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2889));
    }
    @Test
    public void getType906(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2891));
    }
    @Test
    public void getType907(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2894));
    }
    @Test
    public void getType908(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2902));
    }
    @Test
    public void getType909(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2904));
    }
    @Test
    public void getType910(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2908));
    }
    @Test
    public void getType911(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2910));
    }
    @Test
    public void getType912(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2911));
    }
    @Test
    public void getType913(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2914));
    }
    @Test
    public void getType914(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2916));
    }
    @Test
    public void getType915(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 2918));
    }
    @Test
    public void getType916(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 2928));
    }
    @Test
    public void getType917(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2929));
    }
    @Test
    public void getType918(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 2930));
    }
    @Test
    public void getType919(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2936));
    }
    @Test
    public void getType920(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 2946));
    }
    @Test
    public void getType921(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2947));
    }
    @Test
    public void getType922(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2948));
    }
    @Test
    public void getType923(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2949));
    }
    @Test
    public void getType924(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2955));
    }
    @Test
    public void getType925(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2958));
    }
    @Test
    public void getType926(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2961));
    }
    @Test
    public void getType927(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2962));
    }
    @Test
    public void getType928(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2966));
    }
    @Test
    public void getType929(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2969));
    }
    @Test
    public void getType930(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2971));
    }
    @Test
    public void getType931(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2972));
    }
    @Test
    public void getType932(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2973));
    }
    @Test
    public void getType933(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2974));
    }
    @Test
    public void getType934(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2976));
    }
    @Test
    public void getType935(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2979));
    }
    @Test
    public void getType936(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2981));
    }
    @Test
    public void getType937(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2984));
    }
    @Test
    public void getType938(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 2987));
    }
    @Test
    public void getType939(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 2990));
    }
    @Test
    public void getType940(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3002));
    }
    @Test
    public void getType941(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3006));
    }
    @Test
    public void getType942(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3011));
    }
    @Test
    public void getType943(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3014));
    }
    @Test
    public void getType944(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3017));
    }
    @Test
    public void getType945(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3018));
    }
    @Test
    public void getType946(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3022));
    }
    @Test
    public void getType947(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3024));
    }
    @Test
    public void getType948(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3025));
    }
    @Test
    public void getType949(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3031));
    }
    @Test
    public void getType950(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3032));
    }
    @Test
    public void getType951(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 3046));
    }
    @Test
    public void getType952(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 3056));
    }
    @Test
    public void getType953(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 3059));
    }
    @Test
    public void getType954(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 3065));
    }
    @Test
    public void getType955(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 3066));
    }
    @Test
    public void getType956(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3067));
    }
    @Test
    public void getType957(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3073));
    }
    @Test
    public void getType958(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3076));
    }
    @Test
    public void getType959(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3077));
    }
    @Test
    public void getType960(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3085));
    }
    @Test
    public void getType961(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3086));
    }
    @Test
    public void getType962(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3089));
    }
    @Test
    public void getType963(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3090));
    }
    @Test
    public void getType964(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3113));
    }
    @Test
    public void getType965(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3114));
    }
    @Test
    public void getType966(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3124));
    }
    @Test
    public void getType967(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3125));
    }
    @Test
    public void getType968(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3130));
    }
    @Test
    public void getType969(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3133));
    }
    @Test
    public void getType970(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3134));
    }
    @Test
    public void getType971(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3141));
    }
    @Test
    public void getType972(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3142));
    }
    @Test
    public void getType973(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3145));
    }
    @Test
    public void getType974(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3146));
    }
    @Test
    public void getType975(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3150));
    }
    @Test
    public void getType976(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3157));
    }
    @Test
    public void getType977(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3159));
    }
    @Test
    public void getType978(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3160));
    }
    @Test
    public void getType979(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3162));
    }
    @Test
    public void getType980(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3168));
    }
    @Test
    public void getType981(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3170));
    }
    @Test
    public void getType982(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3172));
    }
    @Test
    public void getType983(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 3174));
    }
    @Test
    public void getType984(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3184));
    }
    @Test
    public void getType985(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 3192));
    }
    @Test
    public void getType986(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 3199));
    }
    @Test
    public void getType987(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3200));
    }
    @Test
    public void getType988(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3202));
    }
    @Test
    public void getType989(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3204));
    }
    @Test
    public void getType990(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3205));
    }
    @Test
    public void getType991(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3213));
    }
    @Test
    public void getType992(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3214));
    }
    @Test
    public void getType993(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3217));
    }
    @Test
    public void getType994(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3218));
    }
    @Test
    public void getType995(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3241));
    }
    @Test
    public void getType996(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3242));
    }
    @Test
    public void getType997(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3252));
    }
    @Test
    public void getType998(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3253));
    }
    @Test
    public void getType999(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3258));
    }
    @Test
    public void getType1000(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3260));
    }
    @Test
    public void getType1001(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3261));
    }
    @Test
    public void getType1002(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3262));
    }
    @Test
    public void getType1003(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3269));
    }
    @Test
    public void getType1004(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3270));
    }
    @Test
    public void getType1005(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3273));
    }
    @Test
    public void getType1006(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3274));
    }
    @Test
    public void getType1007(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3278));
    }
    @Test
    public void getType1008(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3285));
    }
    @Test
    public void getType1009(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3287));
    }
    @Test
    public void getType1010(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3294));
    }
    @Test
    public void getType1011(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3295));
    }
    @Test
    public void getType1012(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3296));
    }
    @Test
    public void getType1013(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3298));
    }
    @Test
    public void getType1014(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3300));
    }
    @Test
    public void getType1015(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 3302));
    }
    @Test
    public void getType1016(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3312));
    }
    @Test
    public void getType1017(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3313));
    }
    @Test
    public void getType1018(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3315));
    }
    @Test
    public void getType1019(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3330));
    }
    @Test
    public void getType1020(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3332));
    }
    @Test
    public void getType1021(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3333));
    }
    @Test
    public void getType1022(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3341));
    }
    @Test
    public void getType1023(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3342));
    }
    @Test
    public void getType1024(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3345));
    }
    @Test
    public void getType1025(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3346));
    }
    @Test
    public void getType1026(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3387));
    }
    @Test
    public void getType1027(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3389));
    }
    @Test
    public void getType1028(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3390));
    }
    @Test
    public void getType1029(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3397));
    }
    @Test
    public void getType1030(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3398));
    }
    @Test
    public void getType1031(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3401));
    }
    @Test
    public void getType1032(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3402));
    }
    @Test
    public void getType1033(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3406));
    }
    @Test
    public void getType1034(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3407));
    }
    @Test
    public void getType1035(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3415));
    }
    @Test
    public void getType1036(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3416));
    }
    @Test
    public void getType1037(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3424));
    }
    @Test
    public void getType1038(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3426));
    }
    @Test
    public void getType1039(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3428));
    }
    @Test
    public void getType1040(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 3430));
    }
    @Test
    public void getType1041(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 3440));
    }
    @Test
    public void getType1042(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3446));
    }
    @Test
    public void getType1043(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 3449));
    }
    @Test
    public void getType1044(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3450));
    }
    @Test
    public void getType1045(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3456));
    }
    @Test
    public void getType1046(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3458));
    }
    @Test
    public void getType1047(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3460));
    }
    @Test
    public void getType1048(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3461));
    }
    @Test
    public void getType1049(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3479));
    }
    @Test
    public void getType1050(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3482));
    }
    @Test
    public void getType1051(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3506));
    }
    @Test
    public void getType1052(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3507));
    }
    @Test
    public void getType1053(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3516));
    }
    @Test
    public void getType1054(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3517));
    }
    @Test
    public void getType1055(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3518));
    }
    @Test
    public void getType1056(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3520));
    }
    @Test
    public void getType1057(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3527));
    }
    @Test
    public void getType1058(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3530));
    }
    @Test
    public void getType1059(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3531));
    }
    @Test
    public void getType1060(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3535));
    }
    @Test
    public void getType1061(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3541));
    }
    @Test
    public void getType1062(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3542));
    }
    @Test
    public void getType1063(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3543));
    }
    @Test
    public void getType1064(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3544));
    }
    @Test
    public void getType1065(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3552));
    }
    @Test
    public void getType1066(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3570));
    }
    @Test
    public void getType1067(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 3572));
    }
    @Test
    public void getType1068(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3573));
    }
    @Test
    public void getType1069(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3585));
    }
    @Test
    public void getType1070(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3633));
    }
    @Test
    public void getType1071(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3634));
    }
    @Test
    public void getType1072(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3636));
    }
    @Test
    public void getType1073(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3643));
    }
    @Test
    public void getType1074(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 3647));
    }
    @Test
    public void getType1075(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3648));
    }
    @Test
    public void getType1076(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3655));
    }
    @Test
    public void getType1077(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 3663));
    }
    @Test
    public void getType1078(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 3664));
    }
    @Test
    public void getType1079(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 3674));
    }
    @Test
    public void getType1080(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3676));
    }
    @Test
    public void getType1081(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3713));
    }
    @Test
    public void getType1082(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3715));
    }
    @Test
    public void getType1083(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3716));
    }
    @Test
    public void getType1084(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3717));
    }
    @Test
    public void getType1085(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3719));
    }
    @Test
    public void getType1086(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3721));
    }
    @Test
    public void getType1087(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3722));
    }
    @Test
    public void getType1088(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3723));
    }
    @Test
    public void getType1089(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3725));
    }
    @Test
    public void getType1090(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3726));
    }
    @Test
    public void getType1091(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3732));
    }
    @Test
    public void getType1092(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3736));
    }
    @Test
    public void getType1093(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3737));
    }
    @Test
    public void getType1094(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3744));
    }
    @Test
    public void getType1095(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3745));
    }
    @Test
    public void getType1096(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3748));
    }
    @Test
    public void getType1097(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3749));
    }
    @Test
    public void getType1098(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3750));
    }
    @Test
    public void getType1099(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3751));
    }
    @Test
    public void getType1100(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3752));
    }
    @Test
    public void getType1101(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3754));
    }
    @Test
    public void getType1102(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3756));
    }
    @Test
    public void getType1103(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3757));
    }
    @Test
    public void getType1104(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3761));
    }
    @Test
    public void getType1105(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3762));
    }
    @Test
    public void getType1106(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3764));
    }
    @Test
    public void getType1107(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3770));
    }
    @Test
    public void getType1108(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3771));
    }
    @Test
    public void getType1109(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3773));
    }
    @Test
    public void getType1110(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3774));
    }
    @Test
    public void getType1111(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3776));
    }
    @Test
    public void getType1112(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3781));
    }
    @Test
    public void getType1113(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3782));
    }
    @Test
    public void getType1114(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3783));
    }
    @Test
    public void getType1115(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3784));
    }
    @Test
    public void getType1116(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3790));
    }
    @Test
    public void getType1117(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 3792));
    }
    @Test
    public void getType1118(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3802));
    }
    @Test
    public void getType1119(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3804));
    }
    @Test
    public void getType1120(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3808));
    }
    @Test
    public void getType1121(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3840));
    }
    @Test
    public void getType1122(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 3841));
    }
    @Test
    public void getType1123(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 3844));
    }
    @Test
    public void getType1124(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 3859));
    }
    @Test
    public void getType1125(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 3860));
    }
    @Test
    public void getType1126(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 3861));
    }
    @Test
    public void getType1127(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3864));
    }
    @Test
    public void getType1128(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 3866));
    }
    @Test
    public void getType1129(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 3872));
    }
    @Test
    public void getType1130(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 3882));
    }
    @Test
    public void getType1131(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 3892));
    }
    @Test
    public void getType1132(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3893));
    }
    @Test
    public void getType1133(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 3894));
    }
    @Test
    public void getType1134(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3895));
    }
    @Test
    public void getType1135(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 3896));
    }
    @Test
    public void getType1136(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3897));
    }
    @Test
    public void getType1137(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 3898));
    }
    @Test
    public void getType1138(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3902));
    }
    @Test
    public void getType1139(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3904));
    }
    @Test
    public void getType1140(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3912));
    }
    @Test
    public void getType1141(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3913));
    }
    @Test
    public void getType1142(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3949));
    }
    @Test
    public void getType1143(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3953));
    }
    @Test
    public void getType1144(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 3973));
    }
    @Test
    public void getType1145(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3974));
    }
    @Test
    public void getType1146(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 3976));
    }
    @Test
    public void getType1147(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3981));
    }
    @Test
    public void getType1148(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 3992));
    }
    @Test
    public void getType1149(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 3993));
    }
    @Test
    public void getType1150(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4029));
    }
    @Test
    public void getType1151(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 4030));
    }
    @Test
    public void getType1152(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 4038));
    }
    @Test
    public void getType1153(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 4039));
    }
    @Test
    public void getType1154(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4045));
    }
    @Test
    public void getType1155(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 4046));
    }
    @Test
    public void getType1156(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 4048));
    }
    @Test
    public void getType1157(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 4053));
    }
    @Test
    public void getType1158(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 4057));
    }
    @Test
    public void getType1159(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4059));
    }
    @Test
    public void getType1160(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4096));
    }
    @Test
    public void getType1161(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 4139));
    }
    @Test
    public void getType1162(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4159));
    }
    @Test
    public void getType1163(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 4160));
    }
    @Test
    public void getType1164(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 4170));
    }
    @Test
    public void getType1165(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4176));
    }
    @Test
    public void getType1166(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 4182));
    }
    @Test
    public void getType1167(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4186));
    }
    @Test
    public void getType1168(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 4190));
    }
    @Test
    public void getType1169(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4193));
    }
    @Test
    public void getType1170(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 4194));
    }
    @Test
    public void getType1171(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4197));
    }
    @Test
    public void getType1172(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 4199));
    }
    @Test
    public void getType1173(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4206));
    }
    @Test
    public void getType1174(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 4209));
    }
    @Test
    public void getType1175(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4213));
    }
    @Test
    public void getType1176(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 4226));
    }
    @Test
    public void getType1177(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4238));
    }
    @Test
    public void getType1178(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 4239));
    }
    @Test
    public void getType1179(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 4240));
    }
    @Test
    public void getType1180(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 4250));
    }
    @Test
    public void getType1181(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 4254));
    }
    @Test
    public void getType1182(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 4256));
    }
    @Test
    public void getType1183(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4294));
    }
    @Test
    public void getType1184(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 4295));
    }
    @Test
    public void getType1185(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4296));
    }
    @Test
    public void getType1186(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 4301));
    }
    @Test
    public void getType1187(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4302));
    }
    @Test
    public void getType1188(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4304));
    }
    @Test
    public void getType1189(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 4347));
    }
    @Test
    public void getType1190(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4348));
    }
    @Test
    public void getType1191(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4681));
    }
    @Test
    public void getType1192(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4682));
    }
    @Test
    public void getType1193(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4686));
    }
    @Test
    public void getType1194(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4688));
    }
    @Test
    public void getType1195(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4695));
    }
    @Test
    public void getType1196(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4696));
    }
    @Test
    public void getType1197(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4697));
    }
    @Test
    public void getType1198(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4698));
    }
    @Test
    public void getType1199(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4702));
    }
    @Test
    public void getType1200(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4704));
    }
    @Test
    public void getType1201(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4745));
    }
    @Test
    public void getType1202(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4746));
    }
    @Test
    public void getType1203(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4750));
    }
    @Test
    public void getType1204(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4752));
    }
    @Test
    public void getType1205(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4785));
    }
    @Test
    public void getType1206(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4786));
    }
    @Test
    public void getType1207(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4790));
    }
    @Test
    public void getType1208(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4792));
    }
    @Test
    public void getType1209(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4799));
    }
    @Test
    public void getType1210(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4800));
    }
    @Test
    public void getType1211(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4801));
    }
    @Test
    public void getType1212(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4802));
    }
    @Test
    public void getType1213(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4806));
    }
    @Test
    public void getType1214(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4808));
    }
    @Test
    public void getType1215(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4823));
    }
    @Test
    public void getType1216(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4824));
    }
    @Test
    public void getType1217(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4881));
    }
    @Test
    public void getType1218(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4882));
    }
    @Test
    public void getType1219(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4886));
    }
    @Test
    public void getType1220(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4888));
    }
    @Test
    public void getType1221(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4955));
    }
    @Test
    public void getType1222(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 4957));
    }
    @Test
    public void getType1223(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 4960));
    }
    @Test
    public void getType1224(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 4969));
    }
    @Test
    public void getType1225(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 4989));
    }
    @Test
    public void getType1226(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 4992));
    }
    @Test
    public void getType1227(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 5008));
    }
    @Test
    public void getType1228(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 5018));
    }
    @Test
    public void getType1229(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 5024));
    }
    @Test
    public void getType1230(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 5109));
    }
    @Test
    public void getType1231(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 5120));
    }
    @Test
    public void getType1232(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 5121));
    }
    @Test
    public void getType1233(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 5741));
    }
    @Test
    public void getType1234(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 5743));
    }
    @Test
    public void getType1235(){
        assertEq(StringDataUtil.SPACE_OTHER, StringDataUtil.getCustomType((char) 5760));
    }
    @Test
    public void getType1236(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 5761));
    }
    @Test
    public void getType1237(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 5787));
    }
    @Test
    public void getType1238(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 5789));
    }
    @Test
    public void getType1239(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 5792));
    }
    @Test
    public void getType1240(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 5867));
    }
    @Test
    public void getType1241(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 5870));
    }
    @Test
    public void getType1242(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 5873));
    }
    @Test
    public void getType1243(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 5888));
    }
    @Test
    public void getType1244(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 5901));
    }
    @Test
    public void getType1245(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 5902));
    }
    @Test
    public void getType1246(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 5906));
    }
    @Test
    public void getType1247(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 5909));
    }
    @Test
    public void getType1248(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 5920));
    }
    @Test
    public void getType1249(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 5938));
    }
    @Test
    public void getType1250(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 5941));
    }
    @Test
    public void getType1251(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 5943));
    }
    @Test
    public void getType1252(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 5952));
    }
    @Test
    public void getType1253(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 5970));
    }
    @Test
    public void getType1254(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 5972));
    }
    @Test
    public void getType1255(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 5984));
    }
    @Test
    public void getType1256(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 5997));
    }
    @Test
    public void getType1257(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 5998));
    }
    @Test
    public void getType1258(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6001));
    }
    @Test
    public void getType1259(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6002));
    }
    @Test
    public void getType1260(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6004));
    }
    @Test
    public void getType1261(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6016));
    }
    @Test
    public void getType1262(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6068));
    }
    @Test
    public void getType1263(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 6100));
    }
    @Test
    public void getType1264(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6103));
    }
    @Test
    public void getType1265(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 6104));
    }
    @Test
    public void getType1266(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 6107));
    }
    @Test
    public void getType1267(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6108));
    }
    @Test
    public void getType1268(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6109));
    }
    @Test
    public void getType1269(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6110));
    }
    @Test
    public void getType1270(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 6112));
    }
    @Test
    public void getType1271(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6122));
    }
    @Test
    public void getType1272(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 6128));
    }
    @Test
    public void getType1273(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6138));
    }
    @Test
    public void getType1274(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 6144));
    }
    @Test
    public void getType1275(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 6150));
    }
    @Test
    public void getType1276(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 6151));
    }
    @Test
    public void getType1277(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6155));
    }
    @Test
    public void getType1278(){
        assertEq(StringDataUtil.SPACE_OTHER, StringDataUtil.getCustomType((char) 6158));
    }
    @Test
    public void getType1279(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6159));
    }
    @Test
    public void getType1280(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 6160));
    }
    @Test
    public void getType1281(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6170));
    }
    @Test
    public void getType1282(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6176));
    }
    @Test
    public void getType1283(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6264));
    }
    @Test
    public void getType1284(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6272));
    }
    @Test
    public void getType1285(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6313));
    }
    @Test
    public void getType1286(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6314));
    }
    @Test
    public void getType1287(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6315));
    }
    @Test
    public void getType1288(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6320));
    }
    @Test
    public void getType1289(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6390));
    }
    @Test
    public void getType1290(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6400));
    }
    @Test
    public void getType1291(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6429));
    }
    @Test
    public void getType1292(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6432));
    }
    @Test
    public void getType1293(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6444));
    }
    @Test
    public void getType1294(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6448));
    }
    @Test
    public void getType1295(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6460));
    }
    @Test
    public void getType1296(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 6464));
    }
    @Test
    public void getType1297(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6465));
    }
    @Test
    public void getType1298(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 6468));
    }
    @Test
    public void getType1299(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 6470));
    }
    @Test
    public void getType1300(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6480));
    }
    @Test
    public void getType1301(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6510));
    }
    @Test
    public void getType1302(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6512));
    }
    @Test
    public void getType1303(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6517));
    }
    @Test
    public void getType1304(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6528));
    }
    @Test
    public void getType1305(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6572));
    }
    @Test
    public void getType1306(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6576));
    }
    @Test
    public void getType1307(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6593));
    }
    @Test
    public void getType1308(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6600));
    }
    @Test
    public void getType1309(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6602));
    }
    @Test
    public void getType1310(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 6608));
    }
    @Test
    public void getType1311(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 6618));
    }
    @Test
    public void getType1312(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6619));
    }
    @Test
    public void getType1313(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 6622));
    }
    @Test
    public void getType1314(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6656));
    }
    @Test
    public void getType1315(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6679));
    }
    @Test
    public void getType1316(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6684));
    }
    @Test
    public void getType1317(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 6686));
    }
    @Test
    public void getType1318(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6688));
    }
    @Test
    public void getType1319(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6741));
    }
    @Test
    public void getType1320(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6751));
    }
    @Test
    public void getType1321(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6752));
    }
    @Test
    public void getType1322(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6781));
    }
    @Test
    public void getType1323(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6783));
    }
    @Test
    public void getType1324(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 6784));
    }
    @Test
    public void getType1325(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6794));
    }
    @Test
    public void getType1326(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 6800));
    }
    @Test
    public void getType1327(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6810));
    }
    @Test
    public void getType1328(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 6816));
    }
    @Test
    public void getType1329(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6823));
    }
    @Test
    public void getType1330(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 6824));
    }
    @Test
    public void getType1331(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6830));
    }
    @Test
    public void getType1332(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6912));
    }
    @Test
    public void getType1333(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6917));
    }
    @Test
    public void getType1334(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 6964));
    }
    @Test
    public void getType1335(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 6981));
    }
    @Test
    public void getType1336(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 6988));
    }
    @Test
    public void getType1337(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 6992));
    }
    @Test
    public void getType1338(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 7002));
    }
    @Test
    public void getType1339(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 7009));
    }
    @Test
    public void getType1340(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 7019));
    }
    @Test
    public void getType1341(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 7028));
    }
    @Test
    public void getType1342(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 7037));
    }
    @Test
    public void getType1343(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 7040));
    }
    @Test
    public void getType1344(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 7043));
    }
    @Test
    public void getType1345(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 7073));
    }
    @Test
    public void getType1346(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 7086));
    }
    @Test
    public void getType1347(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 7088));
    }
    @Test
    public void getType1348(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 7098));
    }
    @Test
    public void getType1349(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 7142));
    }
    @Test
    public void getType1350(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 7156));
    }
    @Test
    public void getType1351(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 7164));
    }
    @Test
    public void getType1352(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 7168));
    }
    @Test
    public void getType1353(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 7204));
    }
    @Test
    public void getType1354(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 7224));
    }
    @Test
    public void getType1355(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 7227));
    }
    @Test
    public void getType1356(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 7232));
    }
    @Test
    public void getType1357(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 7242));
    }
    @Test
    public void getType1358(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 7245));
    }
    @Test
    public void getType1359(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 7248));
    }
    @Test
    public void getType1360(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 7258));
    }
    @Test
    public void getType1361(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 7294));
    }
    @Test
    public void getType1362(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 7296));
    }
    @Test
    public void getType1363(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 7360));
    }
    @Test
    public void getType1364(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 7368));
    }
    @Test
    public void getType1365(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 7376));
    }
    @Test
    public void getType1366(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 7379));
    }
    @Test
    public void getType1367(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 7380));
    }
    @Test
    public void getType1368(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 7401));
    }
    @Test
    public void getType1369(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 7405));
    }
    @Test
    public void getType1370(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 7406));
    }
    @Test
    public void getType1371(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 7410));
    }
    @Test
    public void getType1372(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 7413));
    }
    @Test
    public void getType1373(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 7415));
    }
    @Test
    public void getType1374(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7424));
    }
    @Test
    public void getType1375(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7545));
    }
    @Test
    public void getType1376(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7546));
    }
    @Test
    public void getType1377(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7549));
    }
    @Test
    public void getType1378(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7550));
    }
    @Test
    public void getType1379(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 7616));
    }
    @Test
    public void getType1380(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 7655));
    }
    @Test
    public void getType1381(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 7676));
    }
    @Test
    public void getType1382(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7680));
    }
    @Test
    public void getType1383(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7681));
    }
    @Test
    public void getType1384(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7682));
    }
    @Test
    public void getType1385(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7683));
    }
    @Test
    public void getType1386(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7684));
    }
    @Test
    public void getType1387(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7685));
    }
    @Test
    public void getType1388(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7686));
    }
    @Test
    public void getType1389(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7687));
    }
    @Test
    public void getType1390(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7688));
    }
    @Test
    public void getType1391(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7689));
    }
    @Test
    public void getType1392(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7690));
    }
    @Test
    public void getType1393(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7691));
    }
    @Test
    public void getType1394(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7692));
    }
    @Test
    public void getType1395(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7693));
    }
    @Test
    public void getType1396(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7694));
    }
    @Test
    public void getType1397(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7695));
    }
    @Test
    public void getType1398(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7696));
    }
    @Test
    public void getType1399(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7697));
    }
    @Test
    public void getType1400(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7698));
    }
    @Test
    public void getType1401(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7699));
    }
    @Test
    public void getType1402(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7700));
    }
    @Test
    public void getType1403(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7701));
    }
    @Test
    public void getType1404(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7702));
    }
    @Test
    public void getType1405(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7703));
    }
    @Test
    public void getType1406(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7704));
    }
    @Test
    public void getType1407(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7705));
    }
    @Test
    public void getType1408(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7706));
    }
    @Test
    public void getType1409(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7707));
    }
    @Test
    public void getType1410(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7708));
    }
    @Test
    public void getType1411(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7709));
    }
    @Test
    public void getType1412(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7710));
    }
    @Test
    public void getType1413(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7711));
    }
    @Test
    public void getType1414(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7712));
    }
    @Test
    public void getType1415(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7713));
    }
    @Test
    public void getType1416(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7714));
    }
    @Test
    public void getType1417(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7715));
    }
    @Test
    public void getType1418(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7716));
    }
    @Test
    public void getType1419(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7717));
    }
    @Test
    public void getType1420(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7718));
    }
    @Test
    public void getType1421(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7719));
    }
    @Test
    public void getType1422(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7720));
    }
    @Test
    public void getType1423(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7721));
    }
    @Test
    public void getType1424(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7722));
    }
    @Test
    public void getType1425(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7723));
    }
    @Test
    public void getType1426(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7724));
    }
    @Test
    public void getType1427(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7725));
    }
    @Test
    public void getType1428(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7726));
    }
    @Test
    public void getType1429(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7727));
    }
    @Test
    public void getType1430(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7728));
    }
    @Test
    public void getType1431(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7729));
    }
    @Test
    public void getType1432(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7730));
    }
    @Test
    public void getType1433(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7731));
    }
    @Test
    public void getType1434(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7732));
    }
    @Test
    public void getType1435(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7733));
    }
    @Test
    public void getType1436(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7734));
    }
    @Test
    public void getType1437(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7735));
    }
    @Test
    public void getType1438(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7736));
    }
    @Test
    public void getType1439(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7737));
    }
    @Test
    public void getType1440(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7738));
    }
    @Test
    public void getType1441(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7739));
    }
    @Test
    public void getType1442(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7740));
    }
    @Test
    public void getType1443(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7741));
    }
    @Test
    public void getType1444(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7742));
    }
    @Test
    public void getType1445(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7743));
    }
    @Test
    public void getType1446(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7744));
    }
    @Test
    public void getType1447(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7745));
    }
    @Test
    public void getType1448(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7746));
    }
    @Test
    public void getType1449(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7747));
    }
    @Test
    public void getType1450(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7748));
    }
    @Test
    public void getType1451(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7749));
    }
    @Test
    public void getType1452(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7750));
    }
    @Test
    public void getType1453(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7751));
    }
    @Test
    public void getType1454(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7752));
    }
    @Test
    public void getType1455(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7753));
    }
    @Test
    public void getType1456(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7754));
    }
    @Test
    public void getType1457(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7755));
    }
    @Test
    public void getType1458(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7756));
    }
    @Test
    public void getType1459(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7757));
    }
    @Test
    public void getType1460(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7758));
    }
    @Test
    public void getType1461(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7759));
    }
    @Test
    public void getType1462(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7760));
    }
    @Test
    public void getType1463(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7761));
    }
    @Test
    public void getType1464(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7762));
    }
    @Test
    public void getType1465(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7763));
    }
    @Test
    public void getType1466(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7764));
    }
    @Test
    public void getType1467(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7765));
    }
    @Test
    public void getType1468(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7766));
    }
    @Test
    public void getType1469(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7767));
    }
    @Test
    public void getType1470(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7768));
    }
    @Test
    public void getType1471(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7769));
    }
    @Test
    public void getType1472(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7770));
    }
    @Test
    public void getType1473(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7771));
    }
    @Test
    public void getType1474(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7772));
    }
    @Test
    public void getType1475(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7773));
    }
    @Test
    public void getType1476(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7774));
    }
    @Test
    public void getType1477(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7775));
    }
    @Test
    public void getType1478(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7776));
    }
    @Test
    public void getType1479(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7777));
    }
    @Test
    public void getType1480(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7778));
    }
    @Test
    public void getType1481(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7779));
    }
    @Test
    public void getType1482(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7780));
    }
    @Test
    public void getType1483(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7781));
    }
    @Test
    public void getType1484(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7782));
    }
    @Test
    public void getType1485(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7783));
    }
    @Test
    public void getType1486(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7784));
    }
    @Test
    public void getType1487(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7785));
    }
    @Test
    public void getType1488(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7786));
    }
    @Test
    public void getType1489(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7787));
    }
    @Test
    public void getType1490(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7788));
    }
    @Test
    public void getType1491(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7789));
    }
    @Test
    public void getType1492(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7790));
    }
    @Test
    public void getType1493(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7791));
    }
    @Test
    public void getType1494(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7792));
    }
    @Test
    public void getType1495(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7793));
    }
    @Test
    public void getType1496(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7794));
    }
    @Test
    public void getType1497(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7795));
    }
    @Test
    public void getType1498(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7796));
    }
    @Test
    public void getType1499(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7797));
    }
    @Test
    public void getType1500(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7798));
    }
    @Test
    public void getType1501(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7799));
    }
    @Test
    public void getType1502(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7800));
    }
    @Test
    public void getType1503(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7801));
    }
    @Test
    public void getType1504(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7802));
    }
    @Test
    public void getType1505(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7803));
    }
    @Test
    public void getType1506(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7804));
    }
    @Test
    public void getType1507(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7805));
    }
    @Test
    public void getType1508(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7806));
    }
    @Test
    public void getType1509(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7807));
    }
    @Test
    public void getType1510(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7808));
    }
    @Test
    public void getType1511(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7809));
    }
    @Test
    public void getType1512(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7810));
    }
    @Test
    public void getType1513(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7811));
    }
    @Test
    public void getType1514(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7812));
    }
    @Test
    public void getType1515(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7813));
    }
    @Test
    public void getType1516(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7814));
    }
    @Test
    public void getType1517(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7815));
    }
    @Test
    public void getType1518(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7816));
    }
    @Test
    public void getType1519(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7817));
    }
    @Test
    public void getType1520(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7818));
    }
    @Test
    public void getType1521(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7819));
    }
    @Test
    public void getType1522(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7820));
    }
    @Test
    public void getType1523(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7821));
    }
    @Test
    public void getType1524(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7822));
    }
    @Test
    public void getType1525(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7823));
    }
    @Test
    public void getType1526(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7824));
    }
    @Test
    public void getType1527(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7825));
    }
    @Test
    public void getType1528(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7826));
    }
    @Test
    public void getType1529(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7827));
    }
    @Test
    public void getType1530(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7828));
    }
    @Test
    public void getType1531(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7829));
    }
    @Test
    public void getType1532(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7830));
    }
    @Test
    public void getType1533(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7835));
    }
    @Test
    public void getType1534(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7836));
    }
    @Test
    public void getType1535(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7838));
    }
    @Test
    public void getType1536(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7839));
    }
    @Test
    public void getType1537(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7840));
    }
    @Test
    public void getType1538(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7841));
    }
    @Test
    public void getType1539(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7842));
    }
    @Test
    public void getType1540(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7843));
    }
    @Test
    public void getType1541(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7844));
    }
    @Test
    public void getType1542(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7845));
    }
    @Test
    public void getType1543(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7846));
    }
    @Test
    public void getType1544(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7847));
    }
    @Test
    public void getType1545(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7848));
    }
    @Test
    public void getType1546(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7849));
    }
    @Test
    public void getType1547(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7850));
    }
    @Test
    public void getType1548(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7851));
    }
    @Test
    public void getType1549(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7852));
    }
    @Test
    public void getType1550(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7853));
    }
    @Test
    public void getType1551(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7854));
    }
    @Test
    public void getType1552(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7855));
    }
    @Test
    public void getType1553(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7856));
    }
    @Test
    public void getType1554(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7857));
    }
    @Test
    public void getType1555(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7858));
    }
    @Test
    public void getType1556(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7859));
    }
    @Test
    public void getType1557(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7860));
    }
    @Test
    public void getType1558(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7861));
    }
    @Test
    public void getType1559(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7862));
    }
    @Test
    public void getType1560(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7863));
    }
    @Test
    public void getType1561(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7864));
    }
    @Test
    public void getType1562(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7865));
    }
    @Test
    public void getType1563(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7866));
    }
    @Test
    public void getType1564(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7867));
    }
    @Test
    public void getType1565(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7868));
    }
    @Test
    public void getType1566(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7869));
    }
    @Test
    public void getType1567(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7870));
    }
    @Test
    public void getType1568(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7871));
    }
    @Test
    public void getType1569(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7872));
    }
    @Test
    public void getType1570(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7873));
    }
    @Test
    public void getType1571(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7874));
    }
    @Test
    public void getType1572(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7875));
    }
    @Test
    public void getType1573(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7876));
    }
    @Test
    public void getType1574(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7877));
    }
    @Test
    public void getType1575(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7878));
    }
    @Test
    public void getType1576(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7879));
    }
    @Test
    public void getType1577(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7880));
    }
    @Test
    public void getType1578(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7881));
    }
    @Test
    public void getType1579(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7882));
    }
    @Test
    public void getType1580(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7883));
    }
    @Test
    public void getType1581(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7884));
    }
    @Test
    public void getType1582(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7885));
    }
    @Test
    public void getType1583(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7886));
    }
    @Test
    public void getType1584(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7887));
    }
    @Test
    public void getType1585(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7888));
    }
    @Test
    public void getType1586(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7889));
    }
    @Test
    public void getType1587(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7890));
    }
    @Test
    public void getType1588(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7891));
    }
    @Test
    public void getType1589(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7892));
    }
    @Test
    public void getType1590(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7893));
    }
    @Test
    public void getType1591(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7894));
    }
    @Test
    public void getType1592(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7895));
    }
    @Test
    public void getType1593(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7896));
    }
    @Test
    public void getType1594(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7897));
    }
    @Test
    public void getType1595(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7898));
    }
    @Test
    public void getType1596(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7899));
    }
    @Test
    public void getType1597(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7900));
    }
    @Test
    public void getType1598(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7901));
    }
    @Test
    public void getType1599(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7902));
    }
    @Test
    public void getType1600(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7903));
    }
    @Test
    public void getType1601(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7904));
    }
    @Test
    public void getType1602(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7905));
    }
    @Test
    public void getType1603(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7906));
    }
    @Test
    public void getType1604(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7907));
    }
    @Test
    public void getType1605(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7908));
    }
    @Test
    public void getType1606(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7909));
    }
    @Test
    public void getType1607(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7910));
    }
    @Test
    public void getType1608(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7911));
    }
    @Test
    public void getType1609(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7912));
    }
    @Test
    public void getType1610(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7913));
    }
    @Test
    public void getType1611(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7914));
    }
    @Test
    public void getType1612(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7915));
    }
    @Test
    public void getType1613(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7916));
    }
    @Test
    public void getType1614(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7917));
    }
    @Test
    public void getType1615(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7918));
    }
    @Test
    public void getType1616(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7919));
    }
    @Test
    public void getType1617(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7920));
    }
    @Test
    public void getType1618(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7921));
    }
    @Test
    public void getType1619(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7922));
    }
    @Test
    public void getType1620(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7923));
    }
    @Test
    public void getType1621(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7924));
    }
    @Test
    public void getType1622(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7925));
    }
    @Test
    public void getType1623(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7926));
    }
    @Test
    public void getType1624(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7927));
    }
    @Test
    public void getType1625(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7928));
    }
    @Test
    public void getType1626(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7929));
    }
    @Test
    public void getType1627(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7930));
    }
    @Test
    public void getType1628(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7931));
    }
    @Test
    public void getType1629(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7932));
    }
    @Test
    public void getType1630(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7933));
    }
    @Test
    public void getType1631(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7934));
    }
    @Test
    public void getType1632(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7935));
    }
    @Test
    public void getType1633(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7944));
    }
    @Test
    public void getType1634(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7952));
    }
    @Test
    public void getType1635(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 7958));
    }
    @Test
    public void getType1636(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7960));
    }
    @Test
    public void getType1637(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 7966));
    }
    @Test
    public void getType1638(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7968));
    }
    @Test
    public void getType1639(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7976));
    }
    @Test
    public void getType1640(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 7984));
    }
    @Test
    public void getType1641(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 7992));
    }
    @Test
    public void getType1642(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8000));
    }
    @Test
    public void getType1643(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8006));
    }
    @Test
    public void getType1644(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8008));
    }
    @Test
    public void getType1645(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8014));
    }
    @Test
    public void getType1646(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8016));
    }
    @Test
    public void getType1647(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8017));
    }
    @Test
    public void getType1648(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8018));
    }
    @Test
    public void getType1649(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8019));
    }
    @Test
    public void getType1650(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8020));
    }
    @Test
    public void getType1651(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8021));
    }
    @Test
    public void getType1652(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8022));
    }
    @Test
    public void getType1653(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8023));
    }
    @Test
    public void getType1654(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8024));
    }
    @Test
    public void getType1655(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8025));
    }
    @Test
    public void getType1656(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8026));
    }
    @Test
    public void getType1657(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8027));
    }
    @Test
    public void getType1658(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8028));
    }
    @Test
    public void getType1659(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8029));
    }
    @Test
    public void getType1660(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8030));
    }
    @Test
    public void getType1661(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8031));
    }
    @Test
    public void getType1662(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8032));
    }
    @Test
    public void getType1663(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8040));
    }
    @Test
    public void getType1664(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8048));
    }
    @Test
    public void getType1665(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8062));
    }
    @Test
    public void getType1666(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8064));
    }
    @Test
    public void getType1667(){
        assertEq(StringDataUtil.LETTER_SEMI_SENS_NO_CASE, StringDataUtil.getCustomType((char) 8072));
    }
    @Test
    public void getType1668(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8080));
    }
    @Test
    public void getType1669(){
        assertEq(StringDataUtil.LETTER_SEMI_SENS_NO_CASE, StringDataUtil.getCustomType((char) 8088));
    }
    @Test
    public void getType1670(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8096));
    }
    @Test
    public void getType1671(){
        assertEq(StringDataUtil.LETTER_SEMI_SENS_NO_CASE, StringDataUtil.getCustomType((char) 8104));
    }
    @Test
    public void getType1672(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8112));
    }
    @Test
    public void getType1673(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8114));
    }
    @Test
    public void getType1674(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8115));
    }
    @Test
    public void getType1675(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8116));
    }
    @Test
    public void getType1676(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8117));
    }
    @Test
    public void getType1677(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8118));
    }
    @Test
    public void getType1678(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8120));
    }
    @Test
    public void getType1679(){
        assertEq(StringDataUtil.LETTER_SEMI_SENS_NO_CASE, StringDataUtil.getCustomType((char) 8124));
    }
    @Test
    public void getType1680(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 8125));
    }
    @Test
    public void getType1681(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8126));
    }
    @Test
    public void getType1682(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 8127));
    }
    @Test
    public void getType1683(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8130));
    }
    @Test
    public void getType1684(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8131));
    }
    @Test
    public void getType1685(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8132));
    }
    @Test
    public void getType1686(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8133));
    }
    @Test
    public void getType1687(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8134));
    }
    @Test
    public void getType1688(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8136));
    }
    @Test
    public void getType1689(){
        assertEq(StringDataUtil.LETTER_SEMI_SENS_NO_CASE, StringDataUtil.getCustomType((char) 8140));
    }
    @Test
    public void getType1690(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 8141));
    }
    @Test
    public void getType1691(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8144));
    }
    @Test
    public void getType1692(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8146));
    }
    @Test
    public void getType1693(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8148));
    }
    @Test
    public void getType1694(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8150));
    }
    @Test
    public void getType1695(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8152));
    }
    @Test
    public void getType1696(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8156));
    }
    @Test
    public void getType1697(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 8157));
    }
    @Test
    public void getType1698(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8160));
    }
    @Test
    public void getType1699(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8162));
    }
    @Test
    public void getType1700(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8165));
    }
    @Test
    public void getType1701(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8166));
    }
    @Test
    public void getType1702(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8168));
    }
    @Test
    public void getType1703(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 8173));
    }
    @Test
    public void getType1704(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8176));
    }
    @Test
    public void getType1705(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8178));
    }
    @Test
    public void getType1706(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8179));
    }
    @Test
    public void getType1707(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8180));
    }
    @Test
    public void getType1708(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8181));
    }
    @Test
    public void getType1709(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8182));
    }
    @Test
    public void getType1710(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8184));
    }
    @Test
    public void getType1711(){
        assertEq(StringDataUtil.LETTER_SEMI_SENS_NO_CASE, StringDataUtil.getCustomType((char) 8188));
    }
    @Test
    public void getType1712(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 8189));
    }
    @Test
    public void getType1713(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8191));
    }
    @Test
    public void getType1714(){
        assertEq(StringDataUtil.SPACE_OTHER, StringDataUtil.getCustomType((char) 8192));
    }
    @Test
    public void getType1715(){
        assertEq(StringDataUtil.SEPARATOR, StringDataUtil.getCustomType((char) 8203));
    }
    @Test
    public void getType1716(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 8208));
    }
    @Test
    public void getType1717(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 8214));
    }
    @Test
    public void getType1718(){
        assertEq(StringDataUtil.PUNCTUATION_QUOTE, StringDataUtil.getCustomType((char) 8216));
    }
    @Test
    public void getType1719(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 8218));
    }
    @Test
    public void getType1720(){
        assertEq(StringDataUtil.PUNCTUATION_QUOTE, StringDataUtil.getCustomType((char) 8219));
    }
    @Test
    public void getType1721(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 8222));
    }
    @Test
    public void getType1722(){
        assertEq(StringDataUtil.PUNCTUATION_QUOTE, StringDataUtil.getCustomType((char) 8223));
    }
    @Test
    public void getType1723(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 8224));
    }
    @Test
    public void getType1724(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 8232));
    }
    @Test
    public void getType1725(){
        assertEq(StringDataUtil.SPACE_OTHER, StringDataUtil.getCustomType((char) 8239));
    }
    @Test
    public void getType1726(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 8240));
    }
    @Test
    public void getType1727(){
        assertEq(StringDataUtil.PUNCTUATION_QUOTE, StringDataUtil.getCustomType((char) 8249));
    }
    @Test
    public void getType1728(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 8251));
    }
    @Test
    public void getType1729(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 8255));
    }
    @Test
    public void getType1730(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 8257));
    }
    @Test
    public void getType1731(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8260));
    }
    @Test
    public void getType1732(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 8261));
    }
    @Test
    public void getType1733(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 8263));
    }
    @Test
    public void getType1734(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8274));
    }
    @Test
    public void getType1735(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 8275));
    }
    @Test
    public void getType1736(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 8276));
    }
    @Test
    public void getType1737(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 8277));
    }
    @Test
    public void getType1738(){
        assertEq(StringDataUtil.SPACE_OTHER, StringDataUtil.getCustomType((char) 8287));
    }
    @Test
    public void getType1739(){
        assertEq(StringDataUtil.SEPARATOR, StringDataUtil.getCustomType((char) 8288));
    }
    @Test
    public void getType1740(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8293));
    }
    @Test
    public void getType1741(){
        assertEq(StringDataUtil.SEPARATOR, StringDataUtil.getCustomType((char) 8298));
    }
    @Test
    public void getType1742(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8305));
    }
    @Test
    public void getType1743(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8306));
    }
    @Test
    public void getType1744(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 8308));
    }
    @Test
    public void getType1745(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8314));
    }
    @Test
    public void getType1746(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 8317));
    }
    @Test
    public void getType1747(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8319));
    }
    @Test
    public void getType1748(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 8320));
    }
    @Test
    public void getType1749(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8330));
    }
    @Test
    public void getType1750(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 8333));
    }
    @Test
    public void getType1751(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8335));
    }
    @Test
    public void getType1752(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8336));
    }
    @Test
    public void getType1753(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8349));
    }
    @Test
    public void getType1754(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 8352));
    }
    @Test
    public void getType1755(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8379));
    }
    @Test
    public void getType1756(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 8400));
    }
    @Test
    public void getType1757(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8433));
    }
    @Test
    public void getType1758(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8448));
    }
    @Test
    public void getType1759(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8450));
    }
    @Test
    public void getType1760(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8451));
    }
    @Test
    public void getType1761(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8455));
    }
    @Test
    public void getType1762(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8456));
    }
    @Test
    public void getType1763(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8458));
    }
    @Test
    public void getType1764(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8459));
    }
    @Test
    public void getType1765(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8462));
    }
    @Test
    public void getType1766(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8464));
    }
    @Test
    public void getType1767(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8467));
    }
    @Test
    public void getType1768(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8468));
    }
    @Test
    public void getType1769(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8469));
    }
    @Test
    public void getType1770(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8470));
    }
    @Test
    public void getType1771(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8472));
    }
    @Test
    public void getType1772(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8473));
    }
    @Test
    public void getType1773(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8478));
    }
    @Test
    public void getType1774(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8484));
    }
    @Test
    public void getType1775(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8485));
    }
    @Test
    public void getType1776(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8486));
    }
    @Test
    public void getType1777(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8487));
    }
    @Test
    public void getType1778(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8488));
    }
    @Test
    public void getType1779(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8489));
    }
    @Test
    public void getType1780(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8490));
    }
    @Test
    public void getType1781(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8492));
    }
    @Test
    public void getType1782(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8494));
    }
    @Test
    public void getType1783(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8495));
    }
    @Test
    public void getType1784(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8496));
    }
    @Test
    public void getType1785(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8498));
    }
    @Test
    public void getType1786(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8499));
    }
    @Test
    public void getType1787(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8500));
    }
    @Test
    public void getType1788(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 8501));
    }
    @Test
    public void getType1789(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8505));
    }
    @Test
    public void getType1790(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8506));
    }
    @Test
    public void getType1791(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8508));
    }
    @Test
    public void getType1792(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8510));
    }
    @Test
    public void getType1793(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8512));
    }
    @Test
    public void getType1794(){
        assertEq(StringDataUtil.LETTER_INSENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8517));
    }
    @Test
    public void getType1795(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8518));
    }
    @Test
    public void getType1796(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8522));
    }
    @Test
    public void getType1797(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8523));
    }
    @Test
    public void getType1798(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8524));
    }
    @Test
    public void getType1799(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8526));
    }
    @Test
    public void getType1800(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8527));
    }
    @Test
    public void getType1801(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 8528));
    }
    @Test
    public void getType1802(){
        assertEq(StringDataUtil.ROMAN_DIGIT, StringDataUtil.getCustomType((char) 8544));
    }
    @Test
    public void getType1803(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 8576));
    }
    @Test
    public void getType1804(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 8579));
    }
    @Test
    public void getType1805(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 8580));
    }
    @Test
    public void getType1806(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 8581));
    }
    @Test
    public void getType1807(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 8586));
    }
    @Test
    public void getType1808(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8592));
    }
    @Test
    public void getType1809(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8597));
    }
    @Test
    public void getType1810(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8602));
    }
    @Test
    public void getType1811(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8604));
    }
    @Test
    public void getType1812(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8608));
    }
    @Test
    public void getType1813(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8609));
    }
    @Test
    public void getType1814(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8611));
    }
    @Test
    public void getType1815(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8612));
    }
    @Test
    public void getType1816(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8614));
    }
    @Test
    public void getType1817(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8615));
    }
    @Test
    public void getType1818(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8622));
    }
    @Test
    public void getType1819(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8623));
    }
    @Test
    public void getType1820(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8654));
    }
    @Test
    public void getType1821(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8656));
    }
    @Test
    public void getType1822(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8658));
    }
    @Test
    public void getType1823(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8659));
    }
    @Test
    public void getType1824(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8660));
    }
    @Test
    public void getType1825(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8661));
    }
    @Test
    public void getType1826(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8692));
    }
    @Test
    public void getType1827(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8960));
    }
    @Test
    public void getType1828(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8968));
    }
    @Test
    public void getType1829(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8972));
    }
    @Test
    public void getType1830(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 8992));
    }
    @Test
    public void getType1831(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8994));
    }
    @Test
    public void getType1832(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 9001));
    }
    @Test
    public void getType1833(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9003));
    }
    @Test
    public void getType1834(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 9084));
    }
    @Test
    public void getType1835(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9085));
    }
    @Test
    public void getType1836(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 9115));
    }
    @Test
    public void getType1837(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9140));
    }
    @Test
    public void getType1838(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 9180));
    }
    @Test
    public void getType1839(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9186));
    }
    @Test
    public void getType1840(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 9204));
    }
    @Test
    public void getType1841(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9216));
    }
    @Test
    public void getType1842(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 9255));
    }
    @Test
    public void getType1843(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9280));
    }
    @Test
    public void getType1844(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 9291));
    }
    @Test
    public void getType1845(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 9312));
    }
    @Test
    public void getType1846(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9372));
    }
    @Test
    public void getType1847(){
        assertEq(StringDataUtil.SENS_OTHER_LETTER, StringDataUtil.getCustomType((char) 9398));
    }
    @Test
    public void getType1848(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 9450));
    }
    @Test
    public void getType1849(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9472));
    }
    @Test
    public void getType1850(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 9655));
    }
    @Test
    public void getType1851(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9656));
    }
    @Test
    public void getType1852(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 9665));
    }
    @Test
    public void getType1853(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9666));
    }
    @Test
    public void getType1854(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 9720));
    }
    @Test
    public void getType1855(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9728));
    }
    @Test
    public void getType1856(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 9839));
    }
    @Test
    public void getType1857(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9840));
    }
    @Test
    public void getType1858(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 9984));
    }
    @Test
    public void getType1859(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 9985));
    }
    @Test
    public void getType1860(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 10088));
    }
    @Test
    public void getType1861(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 10102));
    }
    @Test
    public void getType1862(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 10132));
    }
    @Test
    public void getType1863(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 10176));
    }
    @Test
    public void getType1864(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 10181));
    }
    @Test
    public void getType1865(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 10183));
    }
    @Test
    public void getType1866(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 10214));
    }
    @Test
    public void getType1867(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 10224));
    }
    @Test
    public void getType1868(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 10240));
    }
    @Test
    public void getType1869(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 10496));
    }
    @Test
    public void getType1870(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 10627));
    }
    @Test
    public void getType1871(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 10649));
    }
    @Test
    public void getType1872(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 10712));
    }
    @Test
    public void getType1873(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 10716));
    }
    @Test
    public void getType1874(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 10748));
    }
    @Test
    public void getType1875(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 10750));
    }
    @Test
    public void getType1876(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 11008));
    }
    @Test
    public void getType1877(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 11056));
    }
    @Test
    public void getType1878(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 11077));
    }
    @Test
    public void getType1879(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 11079));
    }
    @Test
    public void getType1880(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11085));
    }
    @Test
    public void getType1881(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 11088));
    }
    @Test
    public void getType1882(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11098));
    }
    @Test
    public void getType1883(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11264));
    }
    @Test
    public void getType1884(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11311));
    }
    @Test
    public void getType1885(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11312));
    }
    @Test
    public void getType1886(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11359));
    }
    @Test
    public void getType1887(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11360));
    }
    @Test
    public void getType1888(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11361));
    }
    @Test
    public void getType1889(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11362));
    }
    @Test
    public void getType1890(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11365));
    }
    @Test
    public void getType1891(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11367));
    }
    @Test
    public void getType1892(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11368));
    }
    @Test
    public void getType1893(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11369));
    }
    @Test
    public void getType1894(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11370));
    }
    @Test
    public void getType1895(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11371));
    }
    @Test
    public void getType1896(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11372));
    }
    @Test
    public void getType1897(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11373));
    }
    @Test
    public void getType1898(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11377));
    }
    @Test
    public void getType1899(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11378));
    }
    @Test
    public void getType1900(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11379));
    }
    @Test
    public void getType1901(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11380));
    }
    @Test
    public void getType1902(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11381));
    }
    @Test
    public void getType1903(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11382));
    }
    @Test
    public void getType1904(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11383));
    }
    @Test
    public void getType1905(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11390));
    }
    @Test
    public void getType1906(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11393));
    }
    @Test
    public void getType1907(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11394));
    }
    @Test
    public void getType1908(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11395));
    }
    @Test
    public void getType1909(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11396));
    }
    @Test
    public void getType1910(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11397));
    }
    @Test
    public void getType1911(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11398));
    }
    @Test
    public void getType1912(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11399));
    }
    @Test
    public void getType1913(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11400));
    }
    @Test
    public void getType1914(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11401));
    }
    @Test
    public void getType1915(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11402));
    }
    @Test
    public void getType1916(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11403));
    }
    @Test
    public void getType1917(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11404));
    }
    @Test
    public void getType1918(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11405));
    }
    @Test
    public void getType1919(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11406));
    }
    @Test
    public void getType1920(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11407));
    }
    @Test
    public void getType1921(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11408));
    }
    @Test
    public void getType1922(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11409));
    }
    @Test
    public void getType1923(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11410));
    }
    @Test
    public void getType1924(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11411));
    }
    @Test
    public void getType1925(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11412));
    }
    @Test
    public void getType1926(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11413));
    }
    @Test
    public void getType1927(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11414));
    }
    @Test
    public void getType1928(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11415));
    }
    @Test
    public void getType1929(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11416));
    }
    @Test
    public void getType1930(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11417));
    }
    @Test
    public void getType1931(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11418));
    }
    @Test
    public void getType1932(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11419));
    }
    @Test
    public void getType1933(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11420));
    }
    @Test
    public void getType1934(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11421));
    }
    @Test
    public void getType1935(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11422));
    }
    @Test
    public void getType1936(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11423));
    }
    @Test
    public void getType1937(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11424));
    }
    @Test
    public void getType1938(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11425));
    }
    @Test
    public void getType1939(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11426));
    }
    @Test
    public void getType1940(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11427));
    }
    @Test
    public void getType1941(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11428));
    }
    @Test
    public void getType1942(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11429));
    }
    @Test
    public void getType1943(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11430));
    }
    @Test
    public void getType1944(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11431));
    }
    @Test
    public void getType1945(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11432));
    }
    @Test
    public void getType1946(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11433));
    }
    @Test
    public void getType1947(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11434));
    }
    @Test
    public void getType1948(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11435));
    }
    @Test
    public void getType1949(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11436));
    }
    @Test
    public void getType1950(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11437));
    }
    @Test
    public void getType1951(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11438));
    }
    @Test
    public void getType1952(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11439));
    }
    @Test
    public void getType1953(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11440));
    }
    @Test
    public void getType1954(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11441));
    }
    @Test
    public void getType1955(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11442));
    }
    @Test
    public void getType1956(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11443));
    }
    @Test
    public void getType1957(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11444));
    }
    @Test
    public void getType1958(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11445));
    }
    @Test
    public void getType1959(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11446));
    }
    @Test
    public void getType1960(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11447));
    }
    @Test
    public void getType1961(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11448));
    }
    @Test
    public void getType1962(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11449));
    }
    @Test
    public void getType1963(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11450));
    }
    @Test
    public void getType1964(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11451));
    }
    @Test
    public void getType1965(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11452));
    }
    @Test
    public void getType1966(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11453));
    }
    @Test
    public void getType1967(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11454));
    }
    @Test
    public void getType1968(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11455));
    }
    @Test
    public void getType1969(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11456));
    }
    @Test
    public void getType1970(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11457));
    }
    @Test
    public void getType1971(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11458));
    }
    @Test
    public void getType1972(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11459));
    }
    @Test
    public void getType1973(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11460));
    }
    @Test
    public void getType1974(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11461));
    }
    @Test
    public void getType1975(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11462));
    }
    @Test
    public void getType1976(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11463));
    }
    @Test
    public void getType1977(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11464));
    }
    @Test
    public void getType1978(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11465));
    }
    @Test
    public void getType1979(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11466));
    }
    @Test
    public void getType1980(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11467));
    }
    @Test
    public void getType1981(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11468));
    }
    @Test
    public void getType1982(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11469));
    }
    @Test
    public void getType1983(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11470));
    }
    @Test
    public void getType1984(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11471));
    }
    @Test
    public void getType1985(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11472));
    }
    @Test
    public void getType1986(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11473));
    }
    @Test
    public void getType1987(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11474));
    }
    @Test
    public void getType1988(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11475));
    }
    @Test
    public void getType1989(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11476));
    }
    @Test
    public void getType1990(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11477));
    }
    @Test
    public void getType1991(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11478));
    }
    @Test
    public void getType1992(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11479));
    }
    @Test
    public void getType1993(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11480));
    }
    @Test
    public void getType1994(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11481));
    }
    @Test
    public void getType1995(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11482));
    }
    @Test
    public void getType1996(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11483));
    }
    @Test
    public void getType1997(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11484));
    }
    @Test
    public void getType1998(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11485));
    }
    @Test
    public void getType1999(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11486));
    }
    @Test
    public void getType2000(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11487));
    }
    @Test
    public void getType2001(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11488));
    }
    @Test
    public void getType2002(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11489));
    }
    @Test
    public void getType2003(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11490));
    }
    @Test
    public void getType2004(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11491));
    }
    @Test
    public void getType2005(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11492));
    }
    @Test
    public void getType2006(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 11493));
    }
    @Test
    public void getType2007(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11499));
    }
    @Test
    public void getType2008(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11500));
    }
    @Test
    public void getType2009(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11501));
    }
    @Test
    public void getType2010(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11502));
    }
    @Test
    public void getType2011(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 11503));
    }
    @Test
    public void getType2012(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 11506));
    }
    @Test
    public void getType2013(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11507));
    }
    @Test
    public void getType2014(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11508));
    }
    @Test
    public void getType2015(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 11513));
    }
    @Test
    public void getType2016(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 11517));
    }
    @Test
    public void getType2017(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 11518));
    }
    @Test
    public void getType2018(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11520));
    }
    @Test
    public void getType2019(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11558));
    }
    @Test
    public void getType2020(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11559));
    }
    @Test
    public void getType2021(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11560));
    }
    @Test
    public void getType2022(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 11565));
    }
    @Test
    public void getType2023(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11566));
    }
    @Test
    public void getType2024(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 11568));
    }
    @Test
    public void getType2025(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11624));
    }
    @Test
    public void getType2026(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 11631));
    }
    @Test
    public void getType2027(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 11632));
    }
    @Test
    public void getType2028(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11633));
    }
    @Test
    public void getType2029(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 11647));
    }
    @Test
    public void getType2030(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 11648));
    }
    @Test
    public void getType2031(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11671));
    }
    @Test
    public void getType2032(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 11680));
    }
    @Test
    public void getType2033(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11687));
    }
    @Test
    public void getType2034(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 11688));
    }
    @Test
    public void getType2035(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11695));
    }
    @Test
    public void getType2036(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 11696));
    }
    @Test
    public void getType2037(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11703));
    }
    @Test
    public void getType2038(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 11704));
    }
    @Test
    public void getType2039(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11711));
    }
    @Test
    public void getType2040(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 11712));
    }
    @Test
    public void getType2041(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11719));
    }
    @Test
    public void getType2042(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 11720));
    }
    @Test
    public void getType2043(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11727));
    }
    @Test
    public void getType2044(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 11728));
    }
    @Test
    public void getType2045(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11735));
    }
    @Test
    public void getType2046(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 11736));
    }
    @Test
    public void getType2047(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11743));
    }
    @Test
    public void getType2048(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 11744));
    }
    @Test
    public void getType2049(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 11776));
    }
    @Test
    public void getType2050(){
        assertEq(StringDataUtil.PUNCTUATION_QUOTE, StringDataUtil.getCustomType((char) 11778));
    }
    @Test
    public void getType2051(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 11782));
    }
    @Test
    public void getType2052(){
        assertEq(StringDataUtil.PUNCTUATION_QUOTE, StringDataUtil.getCustomType((char) 11785));
    }
    @Test
    public void getType2053(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 11787));
    }
    @Test
    public void getType2054(){
        assertEq(StringDataUtil.PUNCTUATION_QUOTE, StringDataUtil.getCustomType((char) 11788));
    }
    @Test
    public void getType2055(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 11790));
    }
    @Test
    public void getType2056(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 11799));
    }
    @Test
    public void getType2057(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 11800));
    }
    @Test
    public void getType2058(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 11802));
    }
    @Test
    public void getType2059(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 11803));
    }
    @Test
    public void getType2060(){
        assertEq(StringDataUtil.PUNCTUATION_QUOTE, StringDataUtil.getCustomType((char) 11804));
    }
    @Test
    public void getType2061(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 11806));
    }
    @Test
    public void getType2062(){
        assertEq(StringDataUtil.PUNCTUATION_QUOTE, StringDataUtil.getCustomType((char) 11808));
    }
    @Test
    public void getType2063(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 11810));
    }
    @Test
    public void getType2064(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 11818));
    }
    @Test
    public void getType2065(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringDataUtil.getCustomType((char) 11823));
    }
    @Test
    public void getType2066(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 11824));
    }
    @Test
    public void getType2067(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 11834));
    }
    @Test
    public void getType2068(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11836));
    }
    @Test
    public void getType2069(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 11904));
    }
    @Test
    public void getType2070(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 11930));
    }
    @Test
    public void getType2071(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 11931));
    }
    @Test
    public void getType2072(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 12020));
    }
    @Test
    public void getType2073(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12032));
    }
    @Test
    public void getType2074(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 12246));
    }
    @Test
    public void getType2075(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12272));
    }
    @Test
    public void getType2076(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 12284));
    }
    @Test
    public void getType2077(){
        assertEq(StringDataUtil.SPACE_OTHER, StringDataUtil.getCustomType((char) 12288));
    }
    @Test
    public void getType2078(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 12289));
    }
    @Test
    public void getType2079(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12292));
    }
    @Test
    public void getType2080(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 12293));
    }
    @Test
    public void getType2081(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 12295));
    }
    @Test
    public void getType2082(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 12296));
    }
    @Test
    public void getType2083(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12306));
    }
    @Test
    public void getType2084(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 12308));
    }
    @Test
    public void getType2085(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 12316));
    }
    @Test
    public void getType2086(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 12317));
    }
    @Test
    public void getType2087(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12320));
    }
    @Test
    public void getType2088(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 12321));
    }
    @Test
    public void getType2089(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 12336));
    }
    @Test
    public void getType2090(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 12337));
    }
    @Test
    public void getType2091(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12342));
    }
    @Test
    public void getType2092(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 12344));
    }
    @Test
    public void getType2093(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 12347));
    }
    @Test
    public void getType2094(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 12349));
    }
    @Test
    public void getType2095(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12350));
    }
    @Test
    public void getType2096(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 12352));
    }
    @Test
    public void getType2097(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 12353));
    }
    @Test
    public void getType2098(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 12439));
    }
    @Test
    public void getType2099(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 12441));
    }
    @Test
    public void getType2100(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 12443));
    }
    @Test
    public void getType2101(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 12445));
    }
    @Test
    public void getType2102(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 12448));
    }
    @Test
    public void getType2103(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 12449));
    }
    @Test
    public void getType2104(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 12539));
    }
    @Test
    public void getType2105(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 12540));
    }
    @Test
    public void getType2106(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 12544));
    }
    @Test
    public void getType2107(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 12549));
    }
    @Test
    public void getType2108(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 12590));
    }
    @Test
    public void getType2109(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 12593));
    }
    @Test
    public void getType2110(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 12687));
    }
    @Test
    public void getType2111(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12688));
    }
    @Test
    public void getType2112(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 12690));
    }
    @Test
    public void getType2113(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12694));
    }
    @Test
    public void getType2114(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 12704));
    }
    @Test
    public void getType2115(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 12731));
    }
    @Test
    public void getType2116(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12736));
    }
    @Test
    public void getType2117(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 12772));
    }
    @Test
    public void getType2118(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 12784));
    }
    @Test
    public void getType2119(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12800));
    }
    @Test
    public void getType2120(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 12831));
    }
    @Test
    public void getType2121(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 12832));
    }
    @Test
    public void getType2122(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12842));
    }
    @Test
    public void getType2123(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 12872));
    }
    @Test
    public void getType2124(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12880));
    }
    @Test
    public void getType2125(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 12881));
    }
    @Test
    public void getType2126(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12896));
    }
    @Test
    public void getType2127(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 12928));
    }
    @Test
    public void getType2128(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12938));
    }
    @Test
    public void getType2129(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 12977));
    }
    @Test
    public void getType2130(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 12992));
    }
    @Test
    public void getType2131(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 13055));
    }
    @Test
    public void getType2132(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 13056));
    }
    @Test
    public void getType2133(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 13312));
    }
    @Test
    public void getType2134(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 19894));
    }
    @Test
    public void getType2135(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 19904));
    }
    @Test
    public void getType2136(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 19968));
    }
    @Test
    public void getType2137(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 40909));
    }
    @Test
    public void getType2138(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 40960));
    }
    @Test
    public void getType2139(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 42125));
    }
    @Test
    public void getType2140(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 42128));
    }
    @Test
    public void getType2141(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 42183));
    }
    @Test
    public void getType2142(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 42192));
    }
    @Test
    public void getType2143(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 42238));
    }
    @Test
    public void getType2144(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 42240));
    }
    @Test
    public void getType2145(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 42509));
    }
    @Test
    public void getType2146(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 42512));
    }
    @Test
    public void getType2147(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 42528));
    }
    @Test
    public void getType2148(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 42538));
    }
    @Test
    public void getType2149(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 42540));
    }
    @Test
    public void getType2150(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42560));
    }
    @Test
    public void getType2151(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42561));
    }
    @Test
    public void getType2152(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42562));
    }
    @Test
    public void getType2153(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42563));
    }
    @Test
    public void getType2154(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42564));
    }
    @Test
    public void getType2155(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42565));
    }
    @Test
    public void getType2156(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42566));
    }
    @Test
    public void getType2157(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42567));
    }
    @Test
    public void getType2158(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42568));
    }
    @Test
    public void getType2159(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42569));
    }
    @Test
    public void getType2160(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42570));
    }
    @Test
    public void getType2161(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42571));
    }
    @Test
    public void getType2162(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42572));
    }
    @Test
    public void getType2163(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42573));
    }
    @Test
    public void getType2164(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42574));
    }
    @Test
    public void getType2165(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42575));
    }
    @Test
    public void getType2166(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42576));
    }
    @Test
    public void getType2167(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42577));
    }
    @Test
    public void getType2168(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42578));
    }
    @Test
    public void getType2169(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42579));
    }
    @Test
    public void getType2170(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42580));
    }
    @Test
    public void getType2171(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42581));
    }
    @Test
    public void getType2172(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42582));
    }
    @Test
    public void getType2173(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42583));
    }
    @Test
    public void getType2174(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42584));
    }
    @Test
    public void getType2175(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42585));
    }
    @Test
    public void getType2176(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42586));
    }
    @Test
    public void getType2177(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42587));
    }
    @Test
    public void getType2178(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42588));
    }
    @Test
    public void getType2179(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42589));
    }
    @Test
    public void getType2180(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42590));
    }
    @Test
    public void getType2181(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42591));
    }
    @Test
    public void getType2182(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42592));
    }
    @Test
    public void getType2183(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42593));
    }
    @Test
    public void getType2184(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42594));
    }
    @Test
    public void getType2185(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42595));
    }
    @Test
    public void getType2186(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42596));
    }
    @Test
    public void getType2187(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42597));
    }
    @Test
    public void getType2188(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42598));
    }
    @Test
    public void getType2189(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42599));
    }
    @Test
    public void getType2190(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42600));
    }
    @Test
    public void getType2191(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42601));
    }
    @Test
    public void getType2192(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42602));
    }
    @Test
    public void getType2193(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42603));
    }
    @Test
    public void getType2194(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42604));
    }
    @Test
    public void getType2195(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42605));
    }
    @Test
    public void getType2196(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 42606));
    }
    @Test
    public void getType2197(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 42607));
    }
    @Test
    public void getType2198(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 42611));
    }
    @Test
    public void getType2199(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 42612));
    }
    @Test
    public void getType2200(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 42622));
    }
    @Test
    public void getType2201(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringDataUtil.getCustomType((char) 42623));
    }
    @Test
    public void getType2202(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42624));
    }
    @Test
    public void getType2203(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42625));
    }
    @Test
    public void getType2204(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42626));
    }
    @Test
    public void getType2205(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42627));
    }
    @Test
    public void getType2206(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42628));
    }
    @Test
    public void getType2207(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42629));
    }
    @Test
    public void getType2208(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42630));
    }
    @Test
    public void getType2209(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42631));
    }
    @Test
    public void getType2210(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42632));
    }
    @Test
    public void getType2211(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42633));
    }
    @Test
    public void getType2212(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42634));
    }
    @Test
    public void getType2213(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42635));
    }
    @Test
    public void getType2214(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42636));
    }
    @Test
    public void getType2215(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42637));
    }
    @Test
    public void getType2216(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42638));
    }
    @Test
    public void getType2217(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42639));
    }
    @Test
    public void getType2218(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42640));
    }
    @Test
    public void getType2219(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42641));
    }
    @Test
    public void getType2220(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42642));
    }
    @Test
    public void getType2221(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42643));
    }
    @Test
    public void getType2222(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42644));
    }
    @Test
    public void getType2223(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42645));
    }
    @Test
    public void getType2224(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42646));
    }
    @Test
    public void getType2225(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42647));
    }
    @Test
    public void getType2226(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 42648));
    }
    @Test
    public void getType2227(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 42655));
    }
    @Test
    public void getType2228(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 42656));
    }
    @Test
    public void getType2229(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 42726));
    }
    @Test
    public void getType2230(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 42738));
    }
    @Test
    public void getType2231(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 42744));
    }
    @Test
    public void getType2232(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 42752));
    }
    @Test
    public void getType2233(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringDataUtil.getCustomType((char) 42775));
    }
    @Test
    public void getType2234(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 42784));
    }
    @Test
    public void getType2235(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42786));
    }
    @Test
    public void getType2236(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42787));
    }
    @Test
    public void getType2237(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42788));
    }
    @Test
    public void getType2238(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42789));
    }
    @Test
    public void getType2239(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42790));
    }
    @Test
    public void getType2240(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42791));
    }
    @Test
    public void getType2241(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42792));
    }
    @Test
    public void getType2242(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42793));
    }
    @Test
    public void getType2243(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42794));
    }
    @Test
    public void getType2244(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42795));
    }
    @Test
    public void getType2245(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42796));
    }
    @Test
    public void getType2246(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42797));
    }
    @Test
    public void getType2247(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42798));
    }
    @Test
    public void getType2248(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42799));
    }
    @Test
    public void getType2249(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42800));
    }
    @Test
    public void getType2250(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42802));
    }
    @Test
    public void getType2251(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42803));
    }
    @Test
    public void getType2252(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42804));
    }
    @Test
    public void getType2253(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42805));
    }
    @Test
    public void getType2254(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42806));
    }
    @Test
    public void getType2255(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42807));
    }
    @Test
    public void getType2256(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42808));
    }
    @Test
    public void getType2257(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42809));
    }
    @Test
    public void getType2258(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42810));
    }
    @Test
    public void getType2259(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42811));
    }
    @Test
    public void getType2260(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42812));
    }
    @Test
    public void getType2261(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42813));
    }
    @Test
    public void getType2262(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42814));
    }
    @Test
    public void getType2263(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42815));
    }
    @Test
    public void getType2264(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42816));
    }
    @Test
    public void getType2265(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42817));
    }
    @Test
    public void getType2266(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42818));
    }
    @Test
    public void getType2267(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42819));
    }
    @Test
    public void getType2268(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42820));
    }
    @Test
    public void getType2269(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42821));
    }
    @Test
    public void getType2270(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42822));
    }
    @Test
    public void getType2271(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42823));
    }
    @Test
    public void getType2272(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42824));
    }
    @Test
    public void getType2273(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42825));
    }
    @Test
    public void getType2274(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42826));
    }
    @Test
    public void getType2275(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42827));
    }
    @Test
    public void getType2276(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42828));
    }
    @Test
    public void getType2277(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42829));
    }
    @Test
    public void getType2278(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42830));
    }
    @Test
    public void getType2279(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42831));
    }
    @Test
    public void getType2280(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42832));
    }
    @Test
    public void getType2281(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42833));
    }
    @Test
    public void getType2282(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42834));
    }
    @Test
    public void getType2283(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42835));
    }
    @Test
    public void getType2284(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42836));
    }
    @Test
    public void getType2285(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42837));
    }
    @Test
    public void getType2286(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42838));
    }
    @Test
    public void getType2287(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42839));
    }
    @Test
    public void getType2288(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42840));
    }
    @Test
    public void getType2289(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42841));
    }
    @Test
    public void getType2290(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42842));
    }
    @Test
    public void getType2291(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42843));
    }
    @Test
    public void getType2292(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42844));
    }
    @Test
    public void getType2293(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42845));
    }
    @Test
    public void getType2294(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42846));
    }
    @Test
    public void getType2295(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42847));
    }
    @Test
    public void getType2296(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42848));
    }
    @Test
    public void getType2297(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42849));
    }
    @Test
    public void getType2298(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42850));
    }
    @Test
    public void getType2299(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42851));
    }
    @Test
    public void getType2300(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42852));
    }
    @Test
    public void getType2301(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42853));
    }
    @Test
    public void getType2302(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42854));
    }
    @Test
    public void getType2303(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42855));
    }
    @Test
    public void getType2304(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42856));
    }
    @Test
    public void getType2305(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42857));
    }
    @Test
    public void getType2306(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42858));
    }
    @Test
    public void getType2307(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42859));
    }
    @Test
    public void getType2308(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42860));
    }
    @Test
    public void getType2309(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42861));
    }
    @Test
    public void getType2310(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42862));
    }
    @Test
    public void getType2311(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42863));
    }
    @Test
    public void getType2312(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42864));
    }
    @Test
    public void getType2313(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42873));
    }
    @Test
    public void getType2314(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42874));
    }
    @Test
    public void getType2315(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42875));
    }
    @Test
    public void getType2316(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42876));
    }
    @Test
    public void getType2317(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42877));
    }
    @Test
    public void getType2318(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42879));
    }
    @Test
    public void getType2319(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42880));
    }
    @Test
    public void getType2320(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42881));
    }
    @Test
    public void getType2321(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42882));
    }
    @Test
    public void getType2322(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42883));
    }
    @Test
    public void getType2323(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42884));
    }
    @Test
    public void getType2324(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42885));
    }
    @Test
    public void getType2325(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42886));
    }
    @Test
    public void getType2326(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42887));
    }
    @Test
    public void getType2327(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringDataUtil.getCustomType((char) 42888));
    }
    @Test
    public void getType2328(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 42889));
    }
    @Test
    public void getType2329(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42891));
    }
    @Test
    public void getType2330(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42892));
    }
    @Test
    public void getType2331(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42893));
    }
    @Test
    public void getType2332(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42894));
    }
    @Test
    public void getType2333(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 42895));
    }
    @Test
    public void getType2334(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42896));
    }
    @Test
    public void getType2335(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42897));
    }
    @Test
    public void getType2336(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42898));
    }
    @Test
    public void getType2337(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42899));
    }
    @Test
    public void getType2338(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 42900));
    }
    @Test
    public void getType2339(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42912));
    }
    @Test
    public void getType2340(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42913));
    }
    @Test
    public void getType2341(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42914));
    }
    @Test
    public void getType2342(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42915));
    }
    @Test
    public void getType2343(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42916));
    }
    @Test
    public void getType2344(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42917));
    }
    @Test
    public void getType2345(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42918));
    }
    @Test
    public void getType2346(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42919));
    }
    @Test
    public void getType2347(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42920));
    }
    @Test
    public void getType2348(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 42921));
    }
    @Test
    public void getType2349(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 42922));
    }
    @Test
    public void getType2350(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 42923));
    }
    @Test
    public void getType2351(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 43000));
    }
    @Test
    public void getType2352(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43003));
    }
    @Test
    public void getType2353(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43010));
    }
    @Test
    public void getType2354(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43011));
    }
    @Test
    public void getType2355(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43014));
    }
    @Test
    public void getType2356(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43015));
    }
    @Test
    public void getType2357(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43019));
    }
    @Test
    public void getType2358(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43020));
    }
    @Test
    public void getType2359(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43043));
    }
    @Test
    public void getType2360(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 43048));
    }
    @Test
    public void getType2361(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43052));
    }
    @Test
    public void getType2362(){
        assertEq(StringDataUtil.LETTERS_DIGITS_OTHER, StringDataUtil.getCustomType((char) 43056));
    }
    @Test
    public void getType2363(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 43062));
    }
    @Test
    public void getType2364(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 43064));
    }
    @Test
    public void getType2365(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 43065));
    }
    @Test
    public void getType2366(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43066));
    }
    @Test
    public void getType2367(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43072));
    }
    @Test
    public void getType2368(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 43124));
    }
    @Test
    public void getType2369(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43128));
    }
    @Test
    public void getType2370(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43136));
    }
    @Test
    public void getType2371(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43138));
    }
    @Test
    public void getType2372(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43188));
    }
    @Test
    public void getType2373(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43205));
    }
    @Test
    public void getType2374(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 43214));
    }
    @Test
    public void getType2375(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 43216));
    }
    @Test
    public void getType2376(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43226));
    }
    @Test
    public void getType2377(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43232));
    }
    @Test
    public void getType2378(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43250));
    }
    @Test
    public void getType2379(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 43256));
    }
    @Test
    public void getType2380(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43259));
    }
    @Test
    public void getType2381(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43260));
    }
    @Test
    public void getType2382(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 43264));
    }
    @Test
    public void getType2383(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43274));
    }
    @Test
    public void getType2384(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43302));
    }
    @Test
    public void getType2385(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 43310));
    }
    @Test
    public void getType2386(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43312));
    }
    @Test
    public void getType2387(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43335));
    }
    @Test
    public void getType2388(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43348));
    }
    @Test
    public void getType2389(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 43359));
    }
    @Test
    public void getType2390(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43360));
    }
    @Test
    public void getType2391(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43389));
    }
    @Test
    public void getType2392(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43392));
    }
    @Test
    public void getType2393(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43396));
    }
    @Test
    public void getType2394(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43443));
    }
    @Test
    public void getType2395(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 43457));
    }
    @Test
    public void getType2396(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43470));
    }
    @Test
    public void getType2397(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43471));
    }
    @Test
    public void getType2398(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 43472));
    }
    @Test
    public void getType2399(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43482));
    }
    @Test
    public void getType2400(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 43486));
    }
    @Test
    public void getType2401(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43488));
    }
    @Test
    public void getType2402(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43520));
    }
    @Test
    public void getType2403(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43561));
    }
    @Test
    public void getType2404(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43575));
    }
    @Test
    public void getType2405(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43584));
    }
    @Test
    public void getType2406(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43587));
    }
    @Test
    public void getType2407(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43588));
    }
    @Test
    public void getType2408(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43596));
    }
    @Test
    public void getType2409(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43598));
    }
    @Test
    public void getType2410(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 43600));
    }
    @Test
    public void getType2411(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43610));
    }
    @Test
    public void getType2412(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 43612));
    }
    @Test
    public void getType2413(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43616));
    }
    @Test
    public void getType2414(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 43639));
    }
    @Test
    public void getType2415(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43642));
    }
    @Test
    public void getType2416(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43643));
    }
    @Test
    public void getType2417(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43644));
    }
    @Test
    public void getType2418(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43648));
    }
    @Test
    public void getType2419(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43696));
    }
    @Test
    public void getType2420(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43697));
    }
    @Test
    public void getType2421(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43698));
    }
    @Test
    public void getType2422(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43701));
    }
    @Test
    public void getType2423(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43703));
    }
    @Test
    public void getType2424(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43705));
    }
    @Test
    public void getType2425(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43710));
    }
    @Test
    public void getType2426(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43712));
    }
    @Test
    public void getType2427(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43713));
    }
    @Test
    public void getType2428(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43714));
    }
    @Test
    public void getType2429(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43715));
    }
    @Test
    public void getType2430(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43739));
    }
    @Test
    public void getType2431(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 43742));
    }
    @Test
    public void getType2432(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43744));
    }
    @Test
    public void getType2433(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43755));
    }
    @Test
    public void getType2434(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 43760));
    }
    @Test
    public void getType2435(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43762));
    }
    @Test
    public void getType2436(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 43765));
    }
    @Test
    public void getType2437(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43767));
    }
    @Test
    public void getType2438(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43777));
    }
    @Test
    public void getType2439(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43783));
    }
    @Test
    public void getType2440(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43785));
    }
    @Test
    public void getType2441(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43791));
    }
    @Test
    public void getType2442(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43793));
    }
    @Test
    public void getType2443(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43799));
    }
    @Test
    public void getType2444(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43808));
    }
    @Test
    public void getType2445(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43815));
    }
    @Test
    public void getType2446(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43816));
    }
    @Test
    public void getType2447(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 43823));
    }
    @Test
    public void getType2448(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 43968));
    }
    @Test
    public void getType2449(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 44003));
    }
    @Test
    public void getType2450(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 44011));
    }
    @Test
    public void getType2451(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 44012));
    }
    @Test
    public void getType2452(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 44014));
    }
    @Test
    public void getType2453(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 44016));
    }
    @Test
    public void getType2454(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 44026));
    }
    @Test
    public void getType2455(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 44032));
    }
    @Test
    public void getType2456(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 55204));
    }
    @Test
    public void getType2457(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 55216));
    }
    @Test
    public void getType2458(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 55239));
    }
    @Test
    public void getType2459(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 55243));
    }
    @Test
    public void getType2460(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 55292));
    }
    @Test
    public void getType2461(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 55296));
    }
    @Test
    public void getType2462(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 63744));
    }
    @Test
    public void getType2463(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64110));
    }
    @Test
    public void getType2464(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 64112));
    }
    @Test
    public void getType2465(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64218));
    }
    @Test
    public void getType2466(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 64256));
    }
    @Test
    public void getType2467(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64263));
    }
    @Test
    public void getType2468(){
        assertEq(StringDataUtil.LETTER_INSENS_LOWER_CASE, StringDataUtil.getCustomType((char) 64275));
    }
    @Test
    public void getType2469(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64280));
    }
    @Test
    public void getType2470(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 64285));
    }
    @Test
    public void getType2471(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 64286));
    }
    @Test
    public void getType2472(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 64287));
    }
    @Test
    public void getType2473(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 64297));
    }
    @Test
    public void getType2474(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 64298));
    }
    @Test
    public void getType2475(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64311));
    }
    @Test
    public void getType2476(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 64312));
    }
    @Test
    public void getType2477(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64317));
    }
    @Test
    public void getType2478(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 64318));
    }
    @Test
    public void getType2479(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64319));
    }
    @Test
    public void getType2480(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 64320));
    }
    @Test
    public void getType2481(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64322));
    }
    @Test
    public void getType2482(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 64323));
    }
    @Test
    public void getType2483(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64325));
    }
    @Test
    public void getType2484(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 64326));
    }
    @Test
    public void getType2485(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 64434));
    }
    @Test
    public void getType2486(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64450));
    }
    @Test
    public void getType2487(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 64467));
    }
    @Test
    public void getType2488(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 64830));
    }
    @Test
    public void getType2489(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64832));
    }
    @Test
    public void getType2490(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 64848));
    }
    @Test
    public void getType2491(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64912));
    }
    @Test
    public void getType2492(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 64914));
    }
    @Test
    public void getType2493(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 64968));
    }
    @Test
    public void getType2494(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 65008));
    }
    @Test
    public void getType2495(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 65020));
    }
    @Test
    public void getType2496(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 65021));
    }
    @Test
    public void getType2497(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65022));
    }
    @Test
    public void getType2498(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 65024));
    }
    @Test
    public void getType2499(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65040));
    }
    @Test
    public void getType2500(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 65047));
    }
    @Test
    public void getType2501(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65049));
    }
    @Test
    public void getType2502(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65050));
    }
    @Test
    public void getType2503(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 65056));
    }
    @Test
    public void getType2504(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65063));
    }
    @Test
    public void getType2505(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65072));
    }
    @Test
    public void getType2506(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 65073));
    }
    @Test
    public void getType2507(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 65077));
    }
    @Test
    public void getType2508(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65093));
    }
    @Test
    public void getType2509(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 65095));
    }
    @Test
    public void getType2510(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65097));
    }
    @Test
    public void getType2511(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 65101));
    }
    @Test
    public void getType2512(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65104));
    }
    @Test
    public void getType2513(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65107));
    }
    @Test
    public void getType2514(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65108));
    }
    @Test
    public void getType2515(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 65112));
    }
    @Test
    public void getType2516(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 65113));
    }
    @Test
    public void getType2517(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65119));
    }
    @Test
    public void getType2518(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 65122));
    }
    @Test
    public void getType2519(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 65123));
    }
    @Test
    public void getType2520(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 65124));
    }
    @Test
    public void getType2521(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65127));
    }
    @Test
    public void getType2522(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65128));
    }
    @Test
    public void getType2523(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 65129));
    }
    @Test
    public void getType2524(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65130));
    }
    @Test
    public void getType2525(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65132));
    }
    @Test
    public void getType2526(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 65136));
    }
    @Test
    public void getType2527(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65141));
    }
    @Test
    public void getType2528(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringDataUtil.getCustomType((char) 65142));
    }
    @Test
    public void getType2529(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65277));
    }
    @Test
    public void getType2530(){
        assertEq(StringDataUtil.SEPARATOR, StringDataUtil.getCustomType((char) 65279));
    }
    @Test
    public void getType2531(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65280));
    }
    @Test
    public void getType2532(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65281));
    }
    @Test
    public void getType2533(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 65284));
    }
    @Test
    public void getType2534(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65285));
    }
    @Test
    public void getType2535(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 65288));
    }
    @Test
    public void getType2536(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65290));
    }
    @Test
    public void getType2537(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 65291));
    }
    @Test
    public void getType2538(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65292));
    }
    @Test
    public void getType2539(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 65293));
    }
    @Test
    public void getType2540(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65294));
    }
    @Test
    public void getType2541(){
        assertEq(StringDataUtil.DIGIT_OTHER, StringDataUtil.getCustomType((char) 65296));
    }
    @Test
    public void getType2542(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65306));
    }
    @Test
    public void getType2543(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 65308));
    }
    @Test
    public void getType2544(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65311));
    }
    @Test
    public void getType2545(){
        assertEq(StringDataUtil.LETTER_SENS_UPPER_CASE, StringDataUtil.getCustomType((char) 65313));
    }
    @Test
    public void getType2546(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 65339));
    }
    @Test
    public void getType2547(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65340));
    }
    @Test
    public void getType2548(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 65341));
    }
    @Test
    public void getType2549(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 65342));
    }
    @Test
    public void getType2550(){
        assertEq(StringDataUtil.PUNCTUATION_CONNECTOR, StringDataUtil.getCustomType((char) 65343));
    }
    @Test
    public void getType2551(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 65344));
    }
    @Test
    public void getType2552(){
        assertEq(StringDataUtil.LETTER_SENS_LOWER_CASE, StringDataUtil.getCustomType((char) 65345));
    }
    @Test
    public void getType2553(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 65371));
    }
    @Test
    public void getType2554(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 65372));
    }
    @Test
    public void getType2555(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 65373));
    }
    @Test
    public void getType2556(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 65374));
    }
    @Test
    public void getType2557(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 65375));
    }
    @Test
    public void getType2558(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65377));
    }
    @Test
    public void getType2559(){
        assertEq(StringDataUtil.PUNCTUATION_BOUND, StringDataUtil.getCustomType((char) 65378));
    }
    @Test
    public void getType2560(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 65380));
    }
    @Test
    public void getType2561(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 65382));
    }
    @Test
    public void getType2562(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65471));
    }
    @Test
    public void getType2563(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 65474));
    }
    @Test
    public void getType2564(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65480));
    }
    @Test
    public void getType2565(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 65482));
    }
    @Test
    public void getType2566(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65488));
    }
    @Test
    public void getType2567(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 65490));
    }
    @Test
    public void getType2568(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65496));
    }
    @Test
    public void getType2569(){
        assertEq(StringDataUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringDataUtil.getCustomType((char) 65498));
    }
    @Test
    public void getType2570(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65501));
    }
    @Test
    public void getType2571(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 65504));
    }
    @Test
    public void getType2572(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 65506));
    }
    @Test
    public void getType2573(){
        assertEq(StringDataUtil.SYMBOL_MODIFIER, StringDataUtil.getCustomType((char) 65507));
    }
    @Test
    public void getType2574(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 65508));
    }
    @Test
    public void getType2575(){
        assertEq(StringDataUtil.CURRENCY, StringDataUtil.getCustomType((char) 65509));
    }
    @Test
    public void getType2576(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65511));
    }
    @Test
    public void getType2577(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 65512));
    }
    @Test
    public void getType2578(){
        assertEq(StringDataUtil.MATH, StringDataUtil.getCustomType((char) 65513));
    }
    @Test
    public void getType2579(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 65517));
    }
    @Test
    public void getType2580(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65519));
    }
    @Test
    public void getType2581(){
        assertEq(StringDataUtil.SEPARATOR, StringDataUtil.getCustomType((char) 65529));
    }
    @Test
    public void getType2582(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 65532));
    }
    @Test
    public void getType2583(){
        assertEq(StringDataUtil.UNASSIGNED, StringDataUtil.getCustomType((char) 65534));
    }
    @Test
    public void getType2584(){
        assertEq(StringDataUtil.SYMBOL_OTHER, StringDataUtil.getCustomType((char) 8598));
    }
    @Test
    public void getType2585(){
        assertEq(StringDataUtil.PUNCTUATION_OTHER, StringDataUtil.getCustomType((char) 6818));
    }
    @Test
    public void getType2586(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 38));
    }
    @Test
    public void getType2587(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 43));
    }
    @Test
    public void getType2588(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 61));
    }
    @Test
    public void getType2589(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 62));
    }
    @Test
    public void getType2590(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 63));
    }
    @Test
    public void getType2591(){
        assertEq(StringDataUtil.OPERATOR_LANGUAGE, StringDataUtil.getCustomType((char) 64));
    }
    @Test
    public void getType2592(){
        assertEq(StringDataUtil.SPACE, StringDataUtil.getCustomType((char) 10));
    }
    @Test
    public void getType2593(){
        assertEq(StringDataUtil.OTHER, StringDataUtil.getCustomType((char) 837));
    }

    @Test
    public void isLetter1() {
        assertTrue(StringDataLetterUtil.isLetter('a'));
    }

    @Test
    public void isLetter2() {
        assertTrue(!StringDataLetterUtil.isLetter('1'));
    }
    @Test
    public void isLetter3() {
        assertTrue(StringDataLetterUtil.isLetter((char)65500));
    }

    @Test
    public void isLetter4() {
        assertTrue(!StringDataLetterUtil.isLetter((char)65501));
    }

    @Test
    public void isLetter5() {
        assertTrue(!StringDataLetterUtil.isLetter((char)215));
    }

    @Test
    public void isLetter6() {
        assertTrue(!StringDataLetterUtil.isLetter((char)191));
    }

    @Test
    public void isLetter7() {
        assertTrue(!StringDataLetterUtil.isLetter((char)128));
    }

    @Test
    public void isLetter8() {
        assertTrue(!StringDataLetterUtil.isLetter('_'));
    }

    @Test
    public void isLetter9() {
        assertTrue(StringDataLetterUtil.isLetter('A'));
    }
    @Test
    public void isLetterOrDigit1() {
        assertTrue(StringDataUtil.isLetterOrDigit((char)65500));
    }

    @Test
    public void isLetterOrDigit2() {
        assertTrue(!StringDataUtil.isLetterOrDigit((char)65501));
    }

    @Test
    public void isLetterOrDigit3() {
        assertTrue(!StringDataUtil.isLetterOrDigit((char)247));
    }

    @Test
    public void isLetterOrDigit4() {
        assertTrue(StringDataUtil.isLetterOrDigit((char)192));
    }

    @Test
    public void isLetterOrDigit5() {
        assertTrue(!StringDataUtil.isLetterOrDigit((char)191));
    }

    @Test
    public void isLetterOrDigit6() {
        assertTrue(!StringDataUtil.isLetterOrDigit((char)128));
    }

    @Test
    public void isLetterOrDigit7() {
        assertTrue(!StringDataUtil.isLetterOrDigit('_'));
    }

    @Test
    public void isLetterOrDigit8() {
        assertTrue(StringDataUtil.isLetterOrDigit('A'));
    }
    @Test
    public void type() {
        int max_ = -1;
        int maxType_ = -1;
        int min_ = 100;
        int minType_ = 100;
        int maxLetter_ = -1;
        int minLetter_ = -1;
        int maxLetterDigit_ = -1;
        int minLetterDigit_ = -1;
        for (int i = 0; i < 256*256;i++) {
            int dir_ = StringDataUtil.getDirectionality((char) i);
            int type_ = StringDataUtil.getType((char) i);
            max_ = Math.max(dir_,max_);
            min_ = Math.min(dir_,min_);
            maxType_ = Math.max(type_,maxType_);
            minType_ = Math.min(type_,minType_);
            if (StringDataLetterUtil.isLetter((char) i)) {
                if (minLetter_ == -1) {
                    minLetter_ = i;
                }
                maxLetter_ = i;
            }
            if (StringDataUtil.isLetterOrDigit((char) i)) {
                if (minLetterDigit_ == -1) {
                    minLetterDigit_ = i;
                }
                maxLetterDigit_ = i;
            }
        }
        assertEq(-1,min_);
        assertEq(18,max_);
        assertEq(0,minType_);
        assertEq(30,maxType_);
        assertEq('A',minLetter_);
        assertEq(65500,maxLetter_);
        assertEq('0',minLetterDigit_);
        assertEq(65500,maxLetterDigit_);
    }
    @Test
    public void toLowerCaseTest() {
        assertEq("0", StringDataUtil.toLowerCase("0"));
    }
    @Test
    public void toUpperCaseTest() {
        assertEq("0", StringDataUtil.toUpperCase("0"));
    }
    @Test
    public void getDollarWordSeparators1Test(){
        String string_ = ";./:";
        StringList parts_ = StringExpUtil.getDollarWordSeparators(string_);
        assertEq(1, parts_.size());
        assertEq(";./:", parts_.first());
    }
    @Test
    public void getDollarWordSeparators2Test(){
        String string_ = "hello";
        StringList parts_ = StringExpUtil.getDollarWordSeparators(string_);
        assertEq(2, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("hello", parts_.get(1));
    }
    @Test
    public void getDollarWordSeparators3Test(){
        String string_ = ";hello";
        StringList parts_ = StringExpUtil.getDollarWordSeparators(string_);
        assertEq(2, parts_.size());
        assertEq(";", parts_.get(0));
        assertEq("hello", parts_.get(1));
    }
    @Test
    public void getDollarWordSeparators4Test(){
        String string_ = "hello;";
        StringList parts_ = StringExpUtil.getDollarWordSeparators(string_);
        assertEq(3, parts_.size());
        assertEq("", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
    }
    @Test
    public void getDollarWordSeparators5Test(){
        String string_ = ":hello;";
        StringList parts_ = StringExpUtil.getDollarWordSeparators(string_);
        assertEq(3, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
    }
    @Test
    public void getDollarWordSeparators6Test(){
        String string_ = ":hello;world!";
        StringList parts_ = StringExpUtil.getDollarWordSeparators(string_);
        assertEq(5, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
        assertEq("world", parts_.get(3));
        assertEq("!", parts_.get(4));
    }
    @Test
    public void getDollarWordSeparators7Test(){
        String string_ = ":hello;world";
        StringList parts_ = StringExpUtil.getDollarWordSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";", parts_.get(2));
        assertEq("world", parts_.get(3));
    }
    @Test
    public void getDollarWordSeparators8Test(){
        String string_ = ":hello;,world";
        StringList parts_ = StringExpUtil.getDollarWordSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world", parts_.get(3));
    }
    @Test
    public void getDollarWordSeparators9Test(){
        String string_ = "";
        StringList parts_ = StringExpUtil.getDollarWordSeparators(string_);
        assertEq(0, parts_.size());
    }
    @Test
    public void getDollarWordSeparators10Test(){
        String string_ = ":hello_one;,world_one";
        StringList parts_ = StringExpUtil.getDollarWordSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello_one", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world_one", parts_.get(3));
    }
    @Test
    public void getDollarWordSeparators11Test(){
        String string_ = ":hello$one;,world$one";
        StringList parts_ = StringExpUtil.getDollarWordSeparators(string_);
        assertEq(4, parts_.size());
        assertEq(":", parts_.get(0));
        assertEq("hello$one", parts_.get(1));
        assertEq(";,", parts_.get(2));
        assertEq("world$one", parts_.get(3));
    }
    @Test
    public void isDollarWord1Test(){
        assertTrue(StringExpUtil.isDollarWord("1"));
    }
    @Test
    public void isDollarWord2Test(){
        assertTrue(StringExpUtil.isDollarWord("a"));
    }
    @Test
    public void isDollarWord3Test(){
        assertTrue(!StringExpUtil.isDollarWord("-1"));
    }
    @Test
    public void isDollarWord4Test(){
        assertTrue(!StringExpUtil.isDollarWord("-a"));
    }
    @Test
    public void isDollarWord5Test(){
        assertTrue(!StringExpUtil.isDollarWord("-"));
    }
    @Test
    public void isDollarWord6Test(){
        assertTrue(!StringExpUtil.isDollarWord(""));
    }
    @Test
    public void isDollarWord7Test(){
        assertTrue(StringExpUtil.isDollarWord("$"));
    }

}
