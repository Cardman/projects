package code.expressionlanguage.common;

import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.util.StringList;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class StringExpUtilTwoTest extends ProcessMethodCommon {
    @Test
    public void toLower1() {
        assertEq(97, StringExpUtil.toLowerCase((char)65));
    }
    @Test
    public void toLower2() {
        assertEq(224, StringExpUtil.toLowerCase((char)192));
    }
    @Test
    public void toLower3() {
        assertEq(248, StringExpUtil.toLowerCase((char)216));
    }
    @Test
    public void toLower4() {
        assertEq(257, StringExpUtil.toLowerCase((char)256));
    }
    @Test
    public void toLower5() {
        assertEq(105, StringExpUtil.toLowerCase((char)304));
    }
    @Test
    public void toLower6() {
        assertEq(307, StringExpUtil.toLowerCase((char)306));
    }
    @Test
    public void toLower7() {
        assertEq(314, StringExpUtil.toLowerCase((char)313));
    }
    @Test
    public void toLower8() {
        assertEq(331, StringExpUtil.toLowerCase((char)330));
    }
    @Test
    public void toLower9() {
        assertEq(255, StringExpUtil.toLowerCase((char)376));
    }
    @Test
    public void toLower10() {
        assertEq(378, StringExpUtil.toLowerCase((char)377));
    }
    @Test
    public void toLower11() {
        assertEq(595, StringExpUtil.toLowerCase((char)385));
    }
    @Test
    public void toLower12() {
        assertEq(387, StringExpUtil.toLowerCase((char)386));
    }
    @Test
    public void toLower13() {
        assertEq(596, StringExpUtil.toLowerCase((char)390));
    }
    @Test
    public void toLower14() {
        assertEq(392, StringExpUtil.toLowerCase((char)391));
    }
    @Test
    public void toLower15() {
        assertEq(598, StringExpUtil.toLowerCase((char)393));
    }
    @Test
    public void toLower16() {
        assertEq(396, StringExpUtil.toLowerCase((char)395));
    }
    @Test
    public void toLower17() {
        assertEq(477, StringExpUtil.toLowerCase((char)398));
    }
    @Test
    public void toLower18() {
        assertEq(601, StringExpUtil.toLowerCase((char)399));
    }
    @Test
    public void toLower19() {
        assertEq(603, StringExpUtil.toLowerCase((char)400));
    }
    @Test
    public void toLower20() {
        assertEq(402, StringExpUtil.toLowerCase((char)401));
    }
    @Test
    public void toLower21() {
        assertEq(608, StringExpUtil.toLowerCase((char)403));
    }
    @Test
    public void toLower22() {
        assertEq(611, StringExpUtil.toLowerCase((char)404));
    }
    @Test
    public void toLower23() {
        assertEq(617, StringExpUtil.toLowerCase((char)406));
    }
    @Test
    public void toLower24() {
        assertEq(616, StringExpUtil.toLowerCase((char)407));
    }
    @Test
    public void toLower25() {
        assertEq(409, StringExpUtil.toLowerCase((char)408));
    }
    @Test
    public void toLower26() {
        assertEq(623, StringExpUtil.toLowerCase((char)412));
    }
    @Test
    public void toLower27() {
        assertEq(626, StringExpUtil.toLowerCase((char)413));
    }
    @Test
    public void toLower28() {
        assertEq(629, StringExpUtil.toLowerCase((char)415));
    }
    @Test
    public void toLower29() {
        assertEq(417, StringExpUtil.toLowerCase((char)416));
    }
    @Test
    public void toLower30() {
        assertEq(640, StringExpUtil.toLowerCase((char)422));
    }
    @Test
    public void toLower31() {
        assertEq(424, StringExpUtil.toLowerCase((char)423));
    }
    @Test
    public void toLower32() {
        assertEq(643, StringExpUtil.toLowerCase((char)425));
    }
    @Test
    public void toLower33() {
        assertEq(429, StringExpUtil.toLowerCase((char)428));
    }
    @Test
    public void toLower34() {
        assertEq(648, StringExpUtil.toLowerCase((char)430));
    }
    @Test
    public void toLower35() {
        assertEq(432, StringExpUtil.toLowerCase((char)431));
    }
    @Test
    public void toLower36() {
        assertEq(650, StringExpUtil.toLowerCase((char)433));
    }
    @Test
    public void toLower37() {
        assertEq(436, StringExpUtil.toLowerCase((char)435));
    }
    @Test
    public void toLower38() {
        assertEq(658, StringExpUtil.toLowerCase((char)439));
    }
    @Test
    public void toLower39() {
        assertEq(441, StringExpUtil.toLowerCase((char)440));
    }
    @Test
    public void toLower40() {
        assertEq(445, StringExpUtil.toLowerCase((char)444));
    }
    @Test
    public void toLower41() {
        assertEq(454, StringExpUtil.toLowerCase((char)452));
    }
    @Test
    public void toLower42() {
        assertEq(454, StringExpUtil.toLowerCase((char)453));
    }
    @Test
    public void toLower43() {
        assertEq(457, StringExpUtil.toLowerCase((char)455));
    }
    @Test
    public void toLower44() {
        assertEq(457, StringExpUtil.toLowerCase((char)456));
    }
    @Test
    public void toLower45() {
        assertEq(460, StringExpUtil.toLowerCase((char)458));
    }
    @Test
    public void toLower46() {
        assertEq(460, StringExpUtil.toLowerCase((char)459));
    }
    @Test
    public void toLower47() {
        assertEq(479, StringExpUtil.toLowerCase((char)478));
    }
    @Test
    public void toLower48() {
        assertEq(499, StringExpUtil.toLowerCase((char)497));
    }
    @Test
    public void toLower49() {
        assertEq(499, StringExpUtil.toLowerCase((char)498));
    }
    @Test
    public void toLower50() {
        assertEq(405, StringExpUtil.toLowerCase((char)502));
    }
    @Test
    public void toLower51() {
        assertEq(447, StringExpUtil.toLowerCase((char)503));
    }
    @Test
    public void toLower52() {
        assertEq(505, StringExpUtil.toLowerCase((char)504));
    }
    @Test
    public void toLower53() {
        assertEq(414, StringExpUtil.toLowerCase((char)544));
    }
    @Test
    public void toLower54() {
        assertEq(547, StringExpUtil.toLowerCase((char)546));
    }
    @Test
    public void toLower55() {
        assertEq(11365, StringExpUtil.toLowerCase((char)570));
    }
    @Test
    public void toLower56() {
        assertEq(572, StringExpUtil.toLowerCase((char)571));
    }
    @Test
    public void toLower57() {
        assertEq(410, StringExpUtil.toLowerCase((char)573));
    }
    @Test
    public void toLower58() {
        assertEq(11366, StringExpUtil.toLowerCase((char)574));
    }
    @Test
    public void toLower59() {
        assertEq(578, StringExpUtil.toLowerCase((char)577));
    }
    @Test
    public void toLower60() {
        assertEq(384, StringExpUtil.toLowerCase((char)579));
    }
    @Test
    public void toLower61() {
        assertEq(649, StringExpUtil.toLowerCase((char)580));
    }
    @Test
    public void toLower62() {
        assertEq(652, StringExpUtil.toLowerCase((char)581));
    }
    @Test
    public void toLower63() {
        assertEq(583, StringExpUtil.toLowerCase((char)582));
    }
    @Test
    public void toLower64() {
        assertEq(881, StringExpUtil.toLowerCase((char)880));
    }
    @Test
    public void toLower65() {
        assertEq(887, StringExpUtil.toLowerCase((char)886));
    }
    @Test
    public void toLower66() {
        assertEq(940, StringExpUtil.toLowerCase((char)902));
    }
    @Test
    public void toLower67() {
        assertEq(941, StringExpUtil.toLowerCase((char)904));
    }
    @Test
    public void toLower68() {
        assertEq(972, StringExpUtil.toLowerCase((char)908));
    }
    @Test
    public void toLower69() {
        assertEq(973, StringExpUtil.toLowerCase((char)910));
    }
    @Test
    public void toLower70() {
        assertEq(945, StringExpUtil.toLowerCase((char)913));
    }
    @Test
    public void toLower71() {
        assertEq(963, StringExpUtil.toLowerCase((char)931));
    }
    @Test
    public void toLower72() {
        assertEq(983, StringExpUtil.toLowerCase((char)975));
    }
    @Test
    public void toLower73() {
        assertEq(985, StringExpUtil.toLowerCase((char)984));
    }
    @Test
    public void toLower74() {
        assertEq(952, StringExpUtil.toLowerCase((char)1012));
    }
    @Test
    public void toLower75() {
        assertEq(1016, StringExpUtil.toLowerCase((char)1015));
    }
    @Test
    public void toLower76() {
        assertEq(1010, StringExpUtil.toLowerCase((char)1017));
    }
    @Test
    public void toLower77() {
        assertEq(1019, StringExpUtil.toLowerCase((char)1018));
    }
    @Test
    public void toLower78() {
        assertEq(891, StringExpUtil.toLowerCase((char)1021));
    }
    @Test
    public void toLower79() {
        assertEq(1104, StringExpUtil.toLowerCase((char)1024));
    }
    @Test
    public void toLower80() {
        assertEq(1072, StringExpUtil.toLowerCase((char)1040));
    }
    @Test
    public void toLower81() {
        assertEq(1121, StringExpUtil.toLowerCase((char)1120));
    }
    @Test
    public void toLower82() {
        assertEq(1163, StringExpUtil.toLowerCase((char)1162));
    }
    @Test
    public void toLower83() {
        assertEq(1231, StringExpUtil.toLowerCase((char)1216));
    }
    @Test
    public void toLower84() {
        assertEq(1218, StringExpUtil.toLowerCase((char)1217));
    }
    @Test
    public void toLower85() {
        assertEq(1233, StringExpUtil.toLowerCase((char)1232));
    }
    @Test
    public void toLower86() {
        assertEq(1377, StringExpUtil.toLowerCase((char)1329));
    }
    @Test
    public void toLower87() {
        assertEq(11520, StringExpUtil.toLowerCase((char)4256));
    }
    @Test
    public void toLower88() {
        assertEq(11559, StringExpUtil.toLowerCase((char)4295));
    }
    @Test
    public void toLower89() {
        assertEq(11565, StringExpUtil.toLowerCase((char)4301));
    }
    @Test
    public void toLower90() {
        assertEq(7681, StringExpUtil.toLowerCase((char)7680));
    }
    @Test
    public void toLower91() {
        assertEq(223, StringExpUtil.toLowerCase((char)7838));
    }
    @Test
    public void toLower92() {
        assertEq(7841, StringExpUtil.toLowerCase((char)7840));
    }
    @Test
    public void toLower93() {
        assertEq(7936, StringExpUtil.toLowerCase((char)7944));
    }
    @Test
    public void toLower94() {
        assertEq(7952, StringExpUtil.toLowerCase((char)7960));
    }
    @Test
    public void toLower95() {
        assertEq(7968, StringExpUtil.toLowerCase((char)7976));
    }
    @Test
    public void toLower96() {
        assertEq(7984, StringExpUtil.toLowerCase((char)7992));
    }
    @Test
    public void toLower97() {
        assertEq(8000, StringExpUtil.toLowerCase((char)8008));
    }
    @Test
    public void toLower98() {
        assertEq(8017, StringExpUtil.toLowerCase((char)8025));
    }
    @Test
    public void toLower99() {
        assertEq(8019, StringExpUtil.toLowerCase((char)8027));
    }
    @Test
    public void toLower100() {
        assertEq(8021, StringExpUtil.toLowerCase((char)8029));
    }
    @Test
    public void toLower101() {
        assertEq(8023, StringExpUtil.toLowerCase((char)8031));
    }
    @Test
    public void toLower102() {
        assertEq(8032, StringExpUtil.toLowerCase((char)8040));
    }
    @Test
    public void toLower103() {
        assertEq(8064, StringExpUtil.toLowerCase((char)8072));
    }
    @Test
    public void toLower104() {
        assertEq(8080, StringExpUtil.toLowerCase((char)8088));
    }
    @Test
    public void toLower105() {
        assertEq(8096, StringExpUtil.toLowerCase((char)8104));
    }
    @Test
    public void toLower106() {
        assertEq(8112, StringExpUtil.toLowerCase((char)8120));
    }
    @Test
    public void toLower107() {
        assertEq(8048, StringExpUtil.toLowerCase((char)8122));
    }
    @Test
    public void toLower108() {
        assertEq(8115, StringExpUtil.toLowerCase((char)8124));
    }
    @Test
    public void toLower109() {
        assertEq(8050, StringExpUtil.toLowerCase((char)8136));
    }
    @Test
    public void toLower110() {
        assertEq(8131, StringExpUtil.toLowerCase((char)8140));
    }
    @Test
    public void toLower111() {
        assertEq(8144, StringExpUtil.toLowerCase((char)8152));
    }
    @Test
    public void toLower112() {
        assertEq(8054, StringExpUtil.toLowerCase((char)8154));
    }
    @Test
    public void toLower113() {
        assertEq(8160, StringExpUtil.toLowerCase((char)8168));
    }
    @Test
    public void toLower114() {
        assertEq(8058, StringExpUtil.toLowerCase((char)8170));
    }
    @Test
    public void toLower115() {
        assertEq(8165, StringExpUtil.toLowerCase((char)8172));
    }
    @Test
    public void toLower116() {
        assertEq(8056, StringExpUtil.toLowerCase((char)8184));
    }
    @Test
    public void toLower117() {
        assertEq(8060, StringExpUtil.toLowerCase((char)8186));
    }
    @Test
    public void toLower118() {
        assertEq(8179, StringExpUtil.toLowerCase((char)8188));
    }
    @Test
    public void toLower119() {
        assertEq(969, StringExpUtil.toLowerCase((char)8486));
    }
    @Test
    public void toLower120() {
        assertEq(107, StringExpUtil.toLowerCase((char)8490));
    }
    @Test
    public void toLower121() {
        assertEq(229, StringExpUtil.toLowerCase((char)8491));
    }
    @Test
    public void toLower122() {
        assertEq(8526, StringExpUtil.toLowerCase((char)8498));
    }
    @Test
    public void toLower123() {
        assertEq(8560, StringExpUtil.toLowerCase((char)8544));
    }
    @Test
    public void toLower124() {
        assertEq(8580, StringExpUtil.toLowerCase((char)8579));
    }
    @Test
    public void toLower125() {
        assertEq(9424, StringExpUtil.toLowerCase((char)9398));
    }
    @Test
    public void toLower126() {
        assertEq(11312, StringExpUtil.toLowerCase((char)11264));
    }
    @Test
    public void toLower127() {
        assertEq(11361, StringExpUtil.toLowerCase((char)11360));
    }
    @Test
    public void toLower128() {
        assertEq(619, StringExpUtil.toLowerCase((char)11362));
    }
    @Test
    public void toLower129() {
        assertEq(7549, StringExpUtil.toLowerCase((char)11363));
    }
    @Test
    public void toLower130() {
        assertEq(637, StringExpUtil.toLowerCase((char)11364));
    }
    @Test
    public void toLower131() {
        assertEq(11368, StringExpUtil.toLowerCase((char)11367));
    }
    @Test
    public void toLower132() {
        assertEq(593, StringExpUtil.toLowerCase((char)11373));
    }
    @Test
    public void toLower133() {
        assertEq(625, StringExpUtil.toLowerCase((char)11374));
    }
    @Test
    public void toLower134() {
        assertEq(592, StringExpUtil.toLowerCase((char)11375));
    }
    @Test
    public void toLower135() {
        assertEq(594, StringExpUtil.toLowerCase((char)11376));
    }
    @Test
    public void toLower136() {
        assertEq(11379, StringExpUtil.toLowerCase((char)11378));
    }
    @Test
    public void toLower137() {
        assertEq(11382, StringExpUtil.toLowerCase((char)11381));
    }
    @Test
    public void toLower138() {
        assertEq(575, StringExpUtil.toLowerCase((char)11390));
    }
    @Test
    public void toLower139() {
        assertEq(11393, StringExpUtil.toLowerCase((char)11392));
    }
    @Test
    public void toLower140() {
        assertEq(11500, StringExpUtil.toLowerCase((char)11499));
    }
    @Test
    public void toLower141() {
        assertEq(11507, StringExpUtil.toLowerCase((char)11506));
    }
    @Test
    public void toLower142() {
        assertEq(42561, StringExpUtil.toLowerCase((char)42560));
    }
    @Test
    public void toLower143() {
        assertEq(42625, StringExpUtil.toLowerCase((char)42624));
    }
    @Test
    public void toLower144() {
        assertEq(42787, StringExpUtil.toLowerCase((char)42786));
    }
    @Test
    public void toLower145() {
        assertEq(42803, StringExpUtil.toLowerCase((char)42802));
    }
    @Test
    public void toLower146() {
        assertEq(42874, StringExpUtil.toLowerCase((char)42873));
    }
    @Test
    public void toLower147() {
        assertEq(7545, StringExpUtil.toLowerCase((char)42877));
    }
    @Test
    public void toLower148() {
        assertEq(42879, StringExpUtil.toLowerCase((char)42878));
    }
    @Test
    public void toLower149() {
        assertEq(42892, StringExpUtil.toLowerCase((char)42891));
    }
    @Test
    public void toLower150() {
        assertEq(613, StringExpUtil.toLowerCase((char)42893));
    }
    @Test
    public void toLower151() {
        assertEq(42897, StringExpUtil.toLowerCase((char)42896));
    }
    @Test
    public void toLower152() {
        assertEq(42913, StringExpUtil.toLowerCase((char)42912));
    }
    @Test
    public void toLower153() {
        assertEq(614, StringExpUtil.toLowerCase((char)42922));
    }
    @Test
    public void toLower154() {
        assertEq(65345, StringExpUtil.toLowerCase((char)65313));
    }
    @Test
    public void toLower155() {
        assertEq(65339, StringExpUtil.toLowerCase((char)65339));
    }
    @Test
    public void toLower156() {
        assertEq(65340, StringExpUtil.toLowerCase((char)65340));
    }
    @Test
    public void toLower157() {
        assertEq(1, StringExpUtil.toLowerCase((char)1));
    }
    @Test
    public void toUpper1() {
        assertEq(65, StringExpUtil.toUpperCase((char)97));
    }
    @Test
    public void toUpper2() {
        assertEq(924, StringExpUtil.toUpperCase((char)181));
    }
    @Test
    public void toUpper3() {
        assertEq(192, StringExpUtil.toUpperCase((char)224));
    }
    @Test
    public void toUpper4() {
        assertEq(216, StringExpUtil.toUpperCase((char)248));
    }
    @Test
    public void toUpper5() {
        assertEq(376, StringExpUtil.toUpperCase((char)255));
    }
    @Test
    public void toUpper6() {
        assertEq(256, StringExpUtil.toUpperCase((char)257));
    }
    @Test
    public void toUpper7() {
        assertEq(73, StringExpUtil.toUpperCase((char)305));
    }
    @Test
    public void toUpper8() {
        assertEq(306, StringExpUtil.toUpperCase((char)307));
    }
    @Test
    public void toUpper9() {
        assertEq(313, StringExpUtil.toUpperCase((char)314));
    }
    @Test
    public void toUpper10() {
        assertEq(330, StringExpUtil.toUpperCase((char)331));
    }
    @Test
    public void toUpper11() {
        assertEq(377, StringExpUtil.toUpperCase((char)378));
    }
    @Test
    public void toUpper12() {
        assertEq(579, StringExpUtil.toUpperCase((char)384));
    }
    @Test
    public void toUpper13() {
        assertEq(386, StringExpUtil.toUpperCase((char)387));
    }
    @Test
    public void toUpper14() {
        assertEq(391, StringExpUtil.toUpperCase((char)392));
    }
    @Test
    public void toUpper15() {
        assertEq(395, StringExpUtil.toUpperCase((char)396));
    }
    @Test
    public void toUpper16() {
        assertEq(401, StringExpUtil.toUpperCase((char)402));
    }
    @Test
    public void toUpper17() {
        assertEq(502, StringExpUtil.toUpperCase((char)405));
    }
    @Test
    public void toUpper18() {
        assertEq(408, StringExpUtil.toUpperCase((char)409));
    }
    @Test
    public void toUpper19() {
        assertEq(544, StringExpUtil.toUpperCase((char)414));
    }
    @Test
    public void toUpper20() {
        assertEq(416, StringExpUtil.toUpperCase((char)417));
    }
    @Test
    public void toUpper21() {
        assertEq(423, StringExpUtil.toUpperCase((char)424));
    }
    @Test
    public void toUpper22() {
        assertEq(428, StringExpUtil.toUpperCase((char)429));
    }
    @Test
    public void toUpper23() {
        assertEq(431, StringExpUtil.toUpperCase((char)432));
    }
    @Test
    public void toUpper24() {
        assertEq(435, StringExpUtil.toUpperCase((char)436));
    }
    @Test
    public void toUpper25() {
        assertEq(440, StringExpUtil.toUpperCase((char)441));
    }
    @Test
    public void toUpper26() {
        assertEq(444, StringExpUtil.toUpperCase((char)445));
    }
    @Test
    public void toUpper27() {
        assertEq(503, StringExpUtil.toUpperCase((char)447));
    }
    @Test
    public void toUpper28() {
        assertEq(452, StringExpUtil.toUpperCase((char)453));
    }
    @Test
    public void toUpper29() {
        assertEq(455, StringExpUtil.toUpperCase((char)456));
    }
    @Test
    public void toUpper30() {
        assertEq(458, StringExpUtil.toUpperCase((char)459));
    }
    @Test
    public void toUpper31() {
        assertEq(461, StringExpUtil.toUpperCase((char)462));
    }
    @Test
    public void toUpper32() {
        assertEq(478, StringExpUtil.toUpperCase((char)479));
    }
    @Test
    public void toUpper33() {
        assertEq(497, StringExpUtil.toUpperCase((char)498));
    }
    @Test
    public void toUpper34() {
        assertEq(500, StringExpUtil.toUpperCase((char)501));
    }
    @Test
    public void toUpper35() {
        assertEq(504, StringExpUtil.toUpperCase((char)505));
    }
    @Test
    public void toUpper36() {
        assertEq(546, StringExpUtil.toUpperCase((char)547));
    }
    @Test
    public void toUpper37() {
        assertEq(571, StringExpUtil.toUpperCase((char)572));
    }
    @Test
    public void toUpper38() {
        assertEq(11390, StringExpUtil.toUpperCase((char)575));
    }
    @Test
    public void toUpper39() {
        assertEq(577, StringExpUtil.toUpperCase((char)578));
    }
    @Test
    public void toUpper40() {
        assertEq(582, StringExpUtil.toUpperCase((char)583));
    }
    @Test
    public void toUpper41() {
        assertEq(11373, StringExpUtil.toUpperCase((char)593));
    }
    @Test
    public void toUpper42() {
        assertEq(11376, StringExpUtil.toUpperCase((char)594));
    }
    @Test
    public void toUpper43() {
        assertEq(385, StringExpUtil.toUpperCase((char)595));
    }
    @Test
    public void toUpper44() {
        assertEq(390, StringExpUtil.toUpperCase((char)596));
    }
    @Test
    public void toUpper45() {
        assertEq(393, StringExpUtil.toUpperCase((char)598));
    }
    @Test
    public void toUpper46() {
        assertEq(399, StringExpUtil.toUpperCase((char)601));
    }
    @Test
    public void toUpper47() {
        assertEq(400, StringExpUtil.toUpperCase((char)603));
    }
    @Test
    public void toUpper48() {
        assertEq(403, StringExpUtil.toUpperCase((char)608));
    }
    @Test
    public void toUpper49() {
        assertEq(404, StringExpUtil.toUpperCase((char)611));
    }
    @Test
    public void toUpper50() {
        assertEq(42893, StringExpUtil.toUpperCase((char)613));
    }
    @Test
    public void toUpper51() {
        assertEq(42922, StringExpUtil.toUpperCase((char)614));
    }
    @Test
    public void toUpper52() {
        assertEq(407, StringExpUtil.toUpperCase((char)616));
    }
    @Test
    public void toUpper53() {
        assertEq(406, StringExpUtil.toUpperCase((char)617));
    }
    @Test
    public void toUpper54() {
        assertEq(11362, StringExpUtil.toUpperCase((char)619));
    }
    @Test
    public void toUpper55() {
        assertEq(412, StringExpUtil.toUpperCase((char)623));
    }
    @Test
    public void toUpper56() {
        assertEq(11374, StringExpUtil.toUpperCase((char)625));
    }
    @Test
    public void toUpper57() {
        assertEq(413, StringExpUtil.toUpperCase((char)626));
    }
    @Test
    public void toUpper58() {
        assertEq(415, StringExpUtil.toUpperCase((char)629));
    }
    @Test
    public void toUpper59() {
        assertEq(11364, StringExpUtil.toUpperCase((char)637));
    }
    @Test
    public void toUpper60() {
        assertEq(422, StringExpUtil.toUpperCase((char)640));
    }
    @Test
    public void toUpper61() {
        assertEq(425, StringExpUtil.toUpperCase((char)643));
    }
    @Test
    public void toUpper62() {
        assertEq(430, StringExpUtil.toUpperCase((char)648));
    }
    @Test
    public void toUpper63() {
        assertEq(580, StringExpUtil.toUpperCase((char)649));
    }
    @Test
    public void toUpper64() {
        assertEq(433, StringExpUtil.toUpperCase((char)650));
    }
    @Test
    public void toUpper65() {
        assertEq(581, StringExpUtil.toUpperCase((char)652));
    }
    @Test
    public void toUpper66() {
        assertEq(439, StringExpUtil.toUpperCase((char)658));
    }
    @Test
    public void toUpper67() {
        assertEq(921, StringExpUtil.toUpperCase((char)837));
    }
    @Test
    public void toUpper68() {
        assertEq(880, StringExpUtil.toUpperCase((char)881));
    }
    @Test
    public void toUpper69() {
        assertEq(886, StringExpUtil.toUpperCase((char)887));
    }
    @Test
    public void toUpper70() {
        assertEq(1021, StringExpUtil.toUpperCase((char)891));
    }
    @Test
    public void toUpper71() {
        assertEq(902, StringExpUtil.toUpperCase((char)940));
    }
    @Test
    public void toUpper72() {
        assertEq(904, StringExpUtil.toUpperCase((char)941));
    }
    @Test
    public void toUpper73() {
        assertEq(913, StringExpUtil.toUpperCase((char)945));
    }
    @Test
    public void toUpper74() {
        assertEq(931, StringExpUtil.toUpperCase((char)962));
    }
    @Test
    public void toUpper75() {
        assertEq(931, StringExpUtil.toUpperCase((char)963));
    }
    @Test
    public void toUpper76() {
        assertEq(908, StringExpUtil.toUpperCase((char)972));
    }
    @Test
    public void toUpper77() {
        assertEq(910, StringExpUtil.toUpperCase((char)973));
    }
    @Test
    public void toUpper78() {
        assertEq(914, StringExpUtil.toUpperCase((char)976));
    }
    @Test
    public void toUpper79() {
        assertEq(920, StringExpUtil.toUpperCase((char)977));
    }
    @Test
    public void toUpper80() {
        assertEq(934, StringExpUtil.toUpperCase((char)981));
    }
    @Test
    public void toUpper81() {
        assertEq(928, StringExpUtil.toUpperCase((char)982));
    }
    @Test
    public void toUpper82() {
        assertEq(975, StringExpUtil.toUpperCase((char)983));
    }
    @Test
    public void toUpper83() {
        assertEq(984, StringExpUtil.toUpperCase((char)985));
    }
    @Test
    public void toUpper84() {
        assertEq(929, StringExpUtil.toUpperCase((char)1009));
    }
    @Test
    public void toUpper85() {
        assertEq(1017, StringExpUtil.toUpperCase((char)1010));
    }
    @Test
    public void toUpper86() {
        assertEq(917, StringExpUtil.toUpperCase((char)1013));
    }
    @Test
    public void toUpper87() {
        assertEq(1015, StringExpUtil.toUpperCase((char)1016));
    }
    @Test
    public void toUpper88() {
        assertEq(1018, StringExpUtil.toUpperCase((char)1019));
    }
    @Test
    public void toUpper89() {
        assertEq(1040, StringExpUtil.toUpperCase((char)1072));
    }
    @Test
    public void toUpper90() {
        assertEq(1024, StringExpUtil.toUpperCase((char)1104));
    }
    @Test
    public void toUpper91() {
        assertEq(1120, StringExpUtil.toUpperCase((char)1121));
    }
    @Test
    public void toUpper92() {
        assertEq(1162, StringExpUtil.toUpperCase((char)1163));
    }
    @Test
    public void toUpper93() {
        assertEq(1217, StringExpUtil.toUpperCase((char)1218));
    }
    @Test
    public void toUpper94() {
        assertEq(1232, StringExpUtil.toUpperCase((char)1233));
    }
    @Test
    public void toUpper95() {
        assertEq(1329, StringExpUtil.toUpperCase((char)1377));
    }
    @Test
    public void toUpper96() {
        assertEq(42877, StringExpUtil.toUpperCase((char)7545));
    }
    @Test
    public void toUpper97() {
        assertEq(11363, StringExpUtil.toUpperCase((char)7549));
    }
    @Test
    public void toUpper98() {
        assertEq(7680, StringExpUtil.toUpperCase((char)7681));
    }
    @Test
    public void toUpper99() {
        assertEq(7776, StringExpUtil.toUpperCase((char)7835));
    }
    @Test
    public void toUpper100() {
        assertEq(7840, StringExpUtil.toUpperCase((char)7841));
    }
    @Test
    public void toUpper101() {
        assertEq(7945, StringExpUtil.toUpperCase((char)7937));
    }
    @Test
    public void toUpper102() {
        assertEq(7960, StringExpUtil.toUpperCase((char)7952));
    }
    @Test
    public void toUpper103() {
        assertEq(7976, StringExpUtil.toUpperCase((char)7968));
    }
    @Test
    public void toUpper104() {
        assertEq(7992, StringExpUtil.toUpperCase((char)7984));
    }
    @Test
    public void toUpper105() {
        assertEq(8008, StringExpUtil.toUpperCase((char)8000));
    }
    @Test
    public void toUpper106() {
        assertEq(8025, StringExpUtil.toUpperCase((char)8017));
    }
    @Test
    public void toUpper107() {
        assertEq(8027, StringExpUtil.toUpperCase((char)8019));
    }
    @Test
    public void toUpper108() {
        assertEq(8029, StringExpUtil.toUpperCase((char)8021));
    }
    @Test
    public void toUpper109() {
        assertEq(8031, StringExpUtil.toUpperCase((char)8023));
    }
    @Test
    public void toUpper110() {
        assertEq(8040, StringExpUtil.toUpperCase((char)8032));
    }
    @Test
    public void toUpper111() {
        assertEq(8122, StringExpUtil.toUpperCase((char)8048));
    }
    @Test
    public void toUpper112() {
        assertEq(8136, StringExpUtil.toUpperCase((char)8050));
    }
    @Test
    public void toUpper113() {
        assertEq(8154, StringExpUtil.toUpperCase((char)8054));
    }
    @Test
    public void toUpper114() {
        assertEq(8184, StringExpUtil.toUpperCase((char)8056));
    }
    @Test
    public void toUpper115() {
        assertEq(8170, StringExpUtil.toUpperCase((char)8058));
    }
    @Test
    public void toUpper116() {
        assertEq(8186, StringExpUtil.toUpperCase((char)8060));
    }
    @Test
    public void toUpper117() {
        assertEq(8072, StringExpUtil.toUpperCase((char)8064));
    }
    @Test
    public void toUpper118() {
        assertEq(8088, StringExpUtil.toUpperCase((char)8080));
    }
    @Test
    public void toUpper119() {
        assertEq(8104, StringExpUtil.toUpperCase((char)8096));
    }
    @Test
    public void toUpper120() {
        assertEq(8120, StringExpUtil.toUpperCase((char)8112));
    }
    @Test
    public void toUpper121() {
        assertEq(8124, StringExpUtil.toUpperCase((char)8115));
    }
    @Test
    public void toUpper122() {
        assertEq(921, StringExpUtil.toUpperCase((char)8126));
    }
    @Test
    public void toUpper123() {
        assertEq(8140, StringExpUtil.toUpperCase((char)8131));
    }
    @Test
    public void toUpper124() {
        assertEq(8152, StringExpUtil.toUpperCase((char)8144));
    }
    @Test
    public void toUpper125() {
        assertEq(8168, StringExpUtil.toUpperCase((char)8160));
    }
    @Test
    public void toUpper126() {
        assertEq(8172, StringExpUtil.toUpperCase((char)8165));
    }
    @Test
    public void toUpper127() {
        assertEq(8188, StringExpUtil.toUpperCase((char)8179));
    }
    @Test
    public void toUpper128() {
        assertEq(8498, StringExpUtil.toUpperCase((char)8526));
    }
    @Test
    public void toUpper129() {
        assertEq(8544, StringExpUtil.toUpperCase((char)8560));
    }
    @Test
    public void toUpper130() {
        assertEq(8579, StringExpUtil.toUpperCase((char)8580));
    }
    @Test
    public void toUpper131() {
        assertEq(9398, StringExpUtil.toUpperCase((char)9424));
    }
    @Test
    public void toUpper132() {
        assertEq(11264, StringExpUtil.toUpperCase((char)11312));
    }
    @Test
    public void toUpper133() {
        assertEq(11360, StringExpUtil.toUpperCase((char)11361));
    }
    @Test
    public void toUpper134() {
        assertEq(570, StringExpUtil.toUpperCase((char)11365));
    }
    @Test
    public void toUpper135() {
        assertEq(574, StringExpUtil.toUpperCase((char)11366));
    }
    @Test
    public void toUpper136() {
        assertEq(11367, StringExpUtil.toUpperCase((char)11368));
    }
    @Test
    public void toUpper137() {
        assertEq(11378, StringExpUtil.toUpperCase((char)11379));
    }
    @Test
    public void toUpper138() {
        assertEq(11381, StringExpUtil.toUpperCase((char)11382));
    }
    @Test
    public void toUpper139() {
        assertEq(11392, StringExpUtil.toUpperCase((char)11393));
    }
    @Test
    public void toUpper140() {
        assertEq(11499, StringExpUtil.toUpperCase((char)11500));
    }
    @Test
    public void toUpper141() {
        assertEq(11506, StringExpUtil.toUpperCase((char)11507));
    }
    @Test
    public void toUpper142() {
        assertEq(4256, StringExpUtil.toUpperCase((char)11520));
    }
    @Test
    public void toUpper143() {
        assertEq(4295, StringExpUtil.toUpperCase((char)11559));
    }
    @Test
    public void toUpper144() {
        assertEq(4301, StringExpUtil.toUpperCase((char)11565));
    }
    @Test
    public void toUpper145() {
        assertEq(42560, StringExpUtil.toUpperCase((char)42561));
    }
    @Test
    public void toUpper146() {
        assertEq(42624, StringExpUtil.toUpperCase((char)42625));
    }
    @Test
    public void toUpper147() {
        assertEq(42786, StringExpUtil.toUpperCase((char)42787));
    }
    @Test
    public void toUpper148() {
        assertEq(42802, StringExpUtil.toUpperCase((char)42803));
    }
    @Test
    public void toUpper149() {
        assertEq(42873, StringExpUtil.toUpperCase((char)42874));
    }
    @Test
    public void toUpper150() {
        assertEq(42878, StringExpUtil.toUpperCase((char)42879));
    }
    @Test
    public void toUpper151() {
        assertEq(42891, StringExpUtil.toUpperCase((char)42892));
    }
    @Test
    public void toUpper152() {
        assertEq(42896, StringExpUtil.toUpperCase((char)42897));
    }
    @Test
    public void toUpper153() {
        assertEq(42912, StringExpUtil.toUpperCase((char)42913));
    }
    @Test
    public void toUpper154() {
        assertEq(65313, StringExpUtil.toUpperCase((char)65345));
    }
    @Test
    public void toUpper155() {
        assertEq(65371, StringExpUtil.toUpperCase((char)65371));
    }
    @Test
    public void toUpper156() {
        assertEq(42878, StringExpUtil.toUpperCase((char)42878));
    }
    @Test
    public void toUpper157() {
        assertEq(1, StringExpUtil.toUpperCase((char)1));
    }
    @Test
    public void toUpper158() {
        assertEq(83, StringExpUtil.toUpperCase((char)383));
    }
    @Test
    public void isLower1() {
        assertTrue(!StringExpUtil.isLowerCase((char)1));
    }
    @Test
    public void isLower2() {
        assertTrue(!StringExpUtil.isLowerCase((char)65));
    }
    @Test
    public void isLower3() {
        assertTrue(StringExpUtil.isLowerCase((char)97));
    }
    @Test
    public void isUpper1() {
        assertTrue(!StringExpUtil.isUpperCase((char)1));
    }
    @Test
    public void isUpper2() {
        assertTrue(StringExpUtil.isUpperCase((char)65));
    }
    @Test
    public void isUpper3() {
        assertTrue(!StringExpUtil.isUpperCase((char)97));
    }
    @Test
    public void digit1() {
        assertEq(-1, StringExpUtil.digit('0',1));
    }
    @Test
    public void digit2() {
        assertEq(-1, StringExpUtil.digit('0',37));
    }
    @Test
    public void digit3() {
        assertEq(-1, StringExpUtil.digit('8',5));
    }
    @Test
    public void digit4() {
        assertEq(8, StringExpUtil.digit('8',9));
    }
    @Test
    public void digit5() {
        assertEq(-1, StringExpUtil.digit('a',9));
    }
    @Test
    public void digit6() {
        assertEq(10, StringExpUtil.digit('a',11));
    }
    @Test
    public void digit7() {
        assertEq(-1, StringExpUtil.digit('A',9));
    }
    @Test
    public void digit8() {
        assertEq(10, StringExpUtil.digit('A',11));
    }
    @Test
    public void digit9() {
        assertEq(-1, StringExpUtil.digit('!',36));
    }
    @Test
    public void digit10() {
        assertEq(-1, StringExpUtil.digit('|',36));
    }
    @Test
    public void forDigit1() {
        assertEq(0, StringExpUtil.forDigit(0,1));
    }
    @Test
    public void forDigit2() {
        assertEq(0, StringExpUtil.forDigit(0,37));
    }
    @Test
    public void forDigit3() {
        assertEq(0, StringExpUtil.forDigit(8,5));
    }
    @Test
    public void forDigit4() {
        assertEq(0, StringExpUtil.forDigit(-1,2));
    }
    @Test
    public void forDigit5() {
        assertEq('1', StringExpUtil.forDigit(1,9));
    }
    @Test
    public void forDigit6() {
        assertEq('a', StringExpUtil.forDigit(10,11));
    }
    @Test
    public void forDigit7() {
        assertEq('b', StringExpUtil.forDigit(11,12));
    }
    @Test
    public void isUp1(){
        assertTrue(StringExpUtil.isUpperCase((char) 65));
    }
    @Test
    public void isUp2(){
        assertTrue(StringExpUtil.isUpperCase((char) 90));
    }
    @Test
    public void isUp3(){
        assertTrue(StringExpUtil.isUpperCase((char) 192));
    }
    @Test
    public void isUp4(){
        assertTrue(StringExpUtil.isUpperCase((char) 214));
    }
    @Test
    public void isUp5(){
        assertTrue(StringExpUtil.isUpperCase((char) 216));
    }
    @Test
    public void isUp6(){
        assertTrue(StringExpUtil.isUpperCase((char) 222));
    }
    @Test
    public void isUp7(){
        assertTrue(StringExpUtil.isUpperCase((char) 256));
    }
    @Test
    public void isUp8(){
        assertTrue(StringExpUtil.isUpperCase((char) 258));
    }
    @Test
    public void isUp9(){
        assertTrue(StringExpUtil.isUpperCase((char) 260));
    }
    @Test
    public void isUp10(){
        assertTrue(StringExpUtil.isUpperCase((char) 262));
    }
    @Test
    public void isUp11(){
        assertTrue(StringExpUtil.isUpperCase((char) 264));
    }
    @Test
    public void isUp12(){
        assertTrue(StringExpUtil.isUpperCase((char) 266));
    }
    @Test
    public void isUp13(){
        assertTrue(StringExpUtil.isUpperCase((char) 268));
    }
    @Test
    public void isUp14(){
        assertTrue(StringExpUtil.isUpperCase((char) 270));
    }
    @Test
    public void isUp15(){
        assertTrue(StringExpUtil.isUpperCase((char) 272));
    }
    @Test
    public void isUp16(){
        assertTrue(StringExpUtil.isUpperCase((char) 274));
    }
    @Test
    public void isUp17(){
        assertTrue(StringExpUtil.isUpperCase((char) 276));
    }
    @Test
    public void isUp18(){
        assertTrue(StringExpUtil.isUpperCase((char) 278));
    }
    @Test
    public void isUp19(){
        assertTrue(StringExpUtil.isUpperCase((char) 280));
    }
    @Test
    public void isUp20(){
        assertTrue(StringExpUtil.isUpperCase((char) 282));
    }
    @Test
    public void isUp21(){
        assertTrue(StringExpUtil.isUpperCase((char) 284));
    }
    @Test
    public void isUp22(){
        assertTrue(StringExpUtil.isUpperCase((char) 286));
    }
    @Test
    public void isUp23(){
        assertTrue(StringExpUtil.isUpperCase((char) 288));
    }
    @Test
    public void isUp24(){
        assertTrue(StringExpUtil.isUpperCase((char) 290));
    }
    @Test
    public void isUp25(){
        assertTrue(StringExpUtil.isUpperCase((char) 292));
    }
    @Test
    public void isUp26(){
        assertTrue(StringExpUtil.isUpperCase((char) 294));
    }
    @Test
    public void isUp27(){
        assertTrue(StringExpUtil.isUpperCase((char) 296));
    }
    @Test
    public void isUp28(){
        assertTrue(StringExpUtil.isUpperCase((char) 298));
    }
    @Test
    public void isUp29(){
        assertTrue(StringExpUtil.isUpperCase((char) 300));
    }
    @Test
    public void isUp30(){
        assertTrue(StringExpUtil.isUpperCase((char) 302));
    }
    @Test
    public void isUp31(){
        assertTrue(StringExpUtil.isUpperCase((char) 304));
    }
    @Test
    public void isUp32(){
        assertTrue(StringExpUtil.isUpperCase((char) 306));
    }
    @Test
    public void isUp33(){
        assertTrue(StringExpUtil.isUpperCase((char) 308));
    }
    @Test
    public void isUp34(){
        assertTrue(StringExpUtil.isUpperCase((char) 310));
    }
    @Test
    public void isUp35(){
        assertTrue(StringExpUtil.isUpperCase((char) 313));
    }
    @Test
    public void isUp36(){
        assertTrue(StringExpUtil.isUpperCase((char) 315));
    }
    @Test
    public void isUp37(){
        assertTrue(StringExpUtil.isUpperCase((char) 317));
    }
    @Test
    public void isUp38(){
        assertTrue(StringExpUtil.isUpperCase((char) 319));
    }
    @Test
    public void isUp39(){
        assertTrue(StringExpUtil.isUpperCase((char) 321));
    }
    @Test
    public void isUp40(){
        assertTrue(StringExpUtil.isUpperCase((char) 323));
    }
    @Test
    public void isUp41(){
        assertTrue(StringExpUtil.isUpperCase((char) 325));
    }
    @Test
    public void isUp42(){
        assertTrue(StringExpUtil.isUpperCase((char) 327));
    }
    @Test
    public void isUp43(){
        assertTrue(StringExpUtil.isUpperCase((char) 330));
    }
    @Test
    public void isUp44(){
        assertTrue(StringExpUtil.isUpperCase((char) 332));
    }
    @Test
    public void isUp45(){
        assertTrue(StringExpUtil.isUpperCase((char) 334));
    }
    @Test
    public void isUp46(){
        assertTrue(StringExpUtil.isUpperCase((char) 336));
    }
    @Test
    public void isUp47(){
        assertTrue(StringExpUtil.isUpperCase((char) 338));
    }
    @Test
    public void isUp48(){
        assertTrue(StringExpUtil.isUpperCase((char) 340));
    }
    @Test
    public void isUp49(){
        assertTrue(StringExpUtil.isUpperCase((char) 342));
    }
    @Test
    public void isUp50(){
        assertTrue(StringExpUtil.isUpperCase((char) 344));
    }
    @Test
    public void isUp51(){
        assertTrue(StringExpUtil.isUpperCase((char) 346));
    }
    @Test
    public void isUp52(){
        assertTrue(StringExpUtil.isUpperCase((char) 348));
    }
    @Test
    public void isUp53(){
        assertTrue(StringExpUtil.isUpperCase((char) 350));
    }
    @Test
    public void isUp54(){
        assertTrue(StringExpUtil.isUpperCase((char) 352));
    }
    @Test
    public void isUp55(){
        assertTrue(StringExpUtil.isUpperCase((char) 354));
    }
    @Test
    public void isUp56(){
        assertTrue(StringExpUtil.isUpperCase((char) 356));
    }
    @Test
    public void isUp57(){
        assertTrue(StringExpUtil.isUpperCase((char) 358));
    }
    @Test
    public void isUp58(){
        assertTrue(StringExpUtil.isUpperCase((char) 360));
    }
    @Test
    public void isUp59(){
        assertTrue(StringExpUtil.isUpperCase((char) 362));
    }
    @Test
    public void isUp60(){
        assertTrue(StringExpUtil.isUpperCase((char) 364));
    }
    @Test
    public void isUp61(){
        assertTrue(StringExpUtil.isUpperCase((char) 366));
    }
    @Test
    public void isUp62(){
        assertTrue(StringExpUtil.isUpperCase((char) 368));
    }
    @Test
    public void isUp63(){
        assertTrue(StringExpUtil.isUpperCase((char) 370));
    }
    @Test
    public void isUp64(){
        assertTrue(StringExpUtil.isUpperCase((char) 372));
    }
    @Test
    public void isUp65(){
        assertTrue(StringExpUtil.isUpperCase((char) 374));
    }
    @Test
    public void isUp66(){
        assertTrue(StringExpUtil.isUpperCase((char) 376));
    }
    @Test
    public void isUp67(){
        assertTrue(StringExpUtil.isUpperCase((char) 377));
    }
    @Test
    public void isUp68(){
        assertTrue(StringExpUtil.isUpperCase((char) 379));
    }
    @Test
    public void isUp69(){
        assertTrue(StringExpUtil.isUpperCase((char) 381));
    }
    @Test
    public void isUp70(){
        assertTrue(StringExpUtil.isUpperCase((char) 385));
    }
    @Test
    public void isUp71(){
        assertTrue(StringExpUtil.isUpperCase((char) 386));
    }
    @Test
    public void isUp72(){
        assertTrue(StringExpUtil.isUpperCase((char) 388));
    }
    @Test
    public void isUp73(){
        assertTrue(StringExpUtil.isUpperCase((char) 390));
    }
    @Test
    public void isUp74(){
        assertTrue(StringExpUtil.isUpperCase((char) 391));
    }
    @Test
    public void isUp75(){
        assertTrue(StringExpUtil.isUpperCase((char) 393));
    }
    @Test
    public void isUp76(){
        assertTrue(StringExpUtil.isUpperCase((char) 395));
    }
    @Test
    public void isUp77(){
        assertTrue(StringExpUtil.isUpperCase((char) 398));
    }
    @Test
    public void isUp78(){
        assertTrue(StringExpUtil.isUpperCase((char) 401));
    }
    @Test
    public void isUp79(){
        assertTrue(StringExpUtil.isUpperCase((char) 403));
    }
    @Test
    public void isUp80(){
        assertTrue(StringExpUtil.isUpperCase((char) 404));
    }
    @Test
    public void isUp81(){
        assertTrue(StringExpUtil.isUpperCase((char) 406));
    }
    @Test
    public void isUp82(){
        assertTrue(StringExpUtil.isUpperCase((char) 408));
    }
    @Test
    public void isUp83(){
        assertTrue(StringExpUtil.isUpperCase((char) 412));
    }
    @Test
    public void isUp84(){
        assertTrue(StringExpUtil.isUpperCase((char) 413));
    }
    @Test
    public void isUp85(){
        assertTrue(StringExpUtil.isUpperCase((char) 415));
    }
    @Test
    public void isUp86(){
        assertTrue(StringExpUtil.isUpperCase((char) 416));
    }
    @Test
    public void isUp87(){
        assertTrue(StringExpUtil.isUpperCase((char) 418));
    }
    @Test
    public void isUp88(){
        assertTrue(StringExpUtil.isUpperCase((char) 420));
    }
    @Test
    public void isUp89(){
        assertTrue(StringExpUtil.isUpperCase((char) 422));
    }
    @Test
    public void isUp90(){
        assertTrue(StringExpUtil.isUpperCase((char) 423));
    }
    @Test
    public void isUp91(){
        assertTrue(StringExpUtil.isUpperCase((char) 425));
    }
    @Test
    public void isUp92(){
        assertTrue(StringExpUtil.isUpperCase((char) 428));
    }
    @Test
    public void isUp93(){
        assertTrue(StringExpUtil.isUpperCase((char) 430));
    }
    @Test
    public void isUp94(){
        assertTrue(StringExpUtil.isUpperCase((char) 431));
    }
    @Test
    public void isUp95(){
        assertTrue(StringExpUtil.isUpperCase((char) 433));
    }
    @Test
    public void isUp96(){
        assertTrue(StringExpUtil.isUpperCase((char) 435));
    }
    @Test
    public void isUp97(){
        assertTrue(StringExpUtil.isUpperCase((char) 437));
    }
    @Test
    public void isUp98(){
        assertTrue(StringExpUtil.isUpperCase((char) 439));
    }
    @Test
    public void isUp99(){
        assertTrue(StringExpUtil.isUpperCase((char) 440));
    }
    @Test
    public void isUp100(){
        assertTrue(StringExpUtil.isUpperCase((char) 444));
    }
    @Test
    public void isUp101(){
        assertTrue(StringExpUtil.isUpperCase((char) 452));
    }
    @Test
    public void isUp102(){
        assertTrue(StringExpUtil.isUpperCase((char) 455));
    }
    @Test
    public void isUp103(){
        assertTrue(StringExpUtil.isUpperCase((char) 458));
    }
    @Test
    public void isUp104(){
        assertTrue(!StringExpUtil.isUpperCase((char) 459));
    }
    @Test
    public void isUp105(){
        assertTrue(StringExpUtil.isUpperCase((char) 461));
    }
    @Test
    public void isUp106(){
        assertTrue(StringExpUtil.isUpperCase((char) 463));
    }
    @Test
    public void isUp107(){
        assertTrue(StringExpUtil.isUpperCase((char) 465));
    }
    @Test
    public void isUp108(){
        assertTrue(StringExpUtil.isUpperCase((char) 467));
    }
    @Test
    public void isUp109(){
        assertTrue(StringExpUtil.isUpperCase((char) 469));
    }
    @Test
    public void isUp110(){
        assertTrue(StringExpUtil.isUpperCase((char) 471));
    }
    @Test
    public void isUp111(){
        assertTrue(StringExpUtil.isUpperCase((char) 473));
    }
    @Test
    public void isUp112(){
        assertTrue(StringExpUtil.isUpperCase((char) 475));
    }
    @Test
    public void isUp113(){
        assertTrue(StringExpUtil.isUpperCase((char) 478));
    }
    @Test
    public void isUp114(){
        assertTrue(StringExpUtil.isUpperCase((char) 480));
    }
    @Test
    public void isUp115(){
        assertTrue(StringExpUtil.isUpperCase((char) 482));
    }
    @Test
    public void isUp116(){
        assertTrue(StringExpUtil.isUpperCase((char) 484));
    }
    @Test
    public void isUp117(){
        assertTrue(StringExpUtil.isUpperCase((char) 486));
    }
    @Test
    public void isUp118(){
        assertTrue(StringExpUtil.isUpperCase((char) 488));
    }
    @Test
    public void isUp119(){
        assertTrue(StringExpUtil.isUpperCase((char) 490));
    }
    @Test
    public void isUp120(){
        assertTrue(StringExpUtil.isUpperCase((char) 492));
    }
    @Test
    public void isUp121(){
        assertTrue(StringExpUtil.isUpperCase((char) 494));
    }
    @Test
    public void isUp122(){
        assertTrue(StringExpUtil.isUpperCase((char) 497));
    }
    @Test
    public void isUp123(){
        assertTrue(!StringExpUtil.isUpperCase((char) 498));
    }
    @Test
    public void isUp124(){
        assertTrue(StringExpUtil.isUpperCase((char) 500));
    }
    @Test
    public void isUp125(){
        assertTrue(StringExpUtil.isUpperCase((char) 502));
    }
    @Test
    public void isUp126(){
        assertTrue(StringExpUtil.isUpperCase((char) 504));
    }
    @Test
    public void isUp127(){
        assertTrue(StringExpUtil.isUpperCase((char) 506));
    }
    @Test
    public void isUp128(){
        assertTrue(StringExpUtil.isUpperCase((char) 508));
    }
    @Test
    public void isUp129(){
        assertTrue(StringExpUtil.isUpperCase((char) 510));
    }
    @Test
    public void isUp130(){
        assertTrue(StringExpUtil.isUpperCase((char) 512));
    }
    @Test
    public void isUp131(){
        assertTrue(StringExpUtil.isUpperCase((char) 514));
    }
    @Test
    public void isUp132(){
        assertTrue(StringExpUtil.isUpperCase((char) 516));
    }
    @Test
    public void isUp133(){
        assertTrue(StringExpUtil.isUpperCase((char) 518));
    }
    @Test
    public void isUp134(){
        assertTrue(StringExpUtil.isUpperCase((char) 520));
    }
    @Test
    public void isUp135(){
        assertTrue(StringExpUtil.isUpperCase((char) 522));
    }
    @Test
    public void isUp136(){
        assertTrue(StringExpUtil.isUpperCase((char) 524));
    }
    @Test
    public void isUp137(){
        assertTrue(StringExpUtil.isUpperCase((char) 526));
    }
    @Test
    public void isUp138(){
        assertTrue(StringExpUtil.isUpperCase((char) 528));
    }
    @Test
    public void isUp139(){
        assertTrue(StringExpUtil.isUpperCase((char) 530));
    }
    @Test
    public void isUp140(){
        assertTrue(StringExpUtil.isUpperCase((char) 532));
    }
    @Test
    public void isUp141(){
        assertTrue(StringExpUtil.isUpperCase((char) 534));
    }
    @Test
    public void isUp142(){
        assertTrue(StringExpUtil.isUpperCase((char) 536));
    }
    @Test
    public void isUp143(){
        assertTrue(StringExpUtil.isUpperCase((char) 538));
    }
    @Test
    public void isUp144(){
        assertTrue(StringExpUtil.isUpperCase((char) 540));
    }
    @Test
    public void isUp145(){
        assertTrue(StringExpUtil.isUpperCase((char) 542));
    }
    @Test
    public void isUp146(){
        assertTrue(StringExpUtil.isUpperCase((char) 544));
    }
    @Test
    public void isUp147(){
        assertTrue(StringExpUtil.isUpperCase((char) 546));
    }
    @Test
    public void isUp148(){
        assertTrue(StringExpUtil.isUpperCase((char) 548));
    }
    @Test
    public void isUp149(){
        assertTrue(StringExpUtil.isUpperCase((char) 550));
    }
    @Test
    public void isUp150(){
        assertTrue(StringExpUtil.isUpperCase((char) 552));
    }
    @Test
    public void isUp151(){
        assertTrue(StringExpUtil.isUpperCase((char) 554));
    }
    @Test
    public void isUp152(){
        assertTrue(StringExpUtil.isUpperCase((char) 556));
    }
    @Test
    public void isUp153(){
        assertTrue(StringExpUtil.isUpperCase((char) 558));
    }
    @Test
    public void isUp154(){
        assertTrue(StringExpUtil.isUpperCase((char) 560));
    }
    @Test
    public void isUp155(){
        assertTrue(StringExpUtil.isUpperCase((char) 562));
    }
    @Test
    public void isUp156(){
        assertTrue(StringExpUtil.isUpperCase((char) 570));
    }
    @Test
    public void isUp157(){
        assertTrue(StringExpUtil.isUpperCase((char) 571));
    }
    @Test
    public void isUp158(){
        assertTrue(StringExpUtil.isUpperCase((char) 573));
    }
    @Test
    public void isUp159(){
        assertTrue(StringExpUtil.isUpperCase((char) 574));
    }
    @Test
    public void isUp160(){
        assertTrue(StringExpUtil.isUpperCase((char) 577));
    }
    @Test
    public void isUp161(){
        assertTrue(StringExpUtil.isUpperCase((char) 579));
    }
    @Test
    public void isUp162(){
        assertTrue(StringExpUtil.isUpperCase((char) 582));
    }
    @Test
    public void isUp163(){
        assertTrue(StringExpUtil.isUpperCase((char) 584));
    }
    @Test
    public void isUp164(){
        assertTrue(StringExpUtil.isUpperCase((char) 586));
    }
    @Test
    public void isUp165(){
        assertTrue(StringExpUtil.isUpperCase((char) 588));
    }
    @Test
    public void isUp166(){
        assertTrue(StringExpUtil.isUpperCase((char) 590));
    }
    @Test
    public void isUp167(){
        assertTrue(StringExpUtil.isUpperCase((char) 880));
    }
    @Test
    public void isUp168(){
        assertTrue(StringExpUtil.isUpperCase((char) 882));
    }
    @Test
    public void isUp169(){
        assertTrue(StringExpUtil.isUpperCase((char) 886));
    }
    @Test
    public void isUp170(){
        assertTrue(StringExpUtil.isUpperCase((char) 902));
    }
    @Test
    public void isUp171(){
        assertTrue(StringExpUtil.isUpperCase((char) 904));
    }
    @Test
    public void isUp172(){
        assertTrue(StringExpUtil.isUpperCase((char) 906));
    }
    @Test
    public void isUp173(){
        assertTrue(StringExpUtil.isUpperCase((char) 908));
    }
    @Test
    public void isUp174(){
        assertTrue(StringExpUtil.isUpperCase((char) 910));
    }
    @Test
    public void isUp175(){
        assertTrue(StringExpUtil.isUpperCase((char) 911));
    }
    @Test
    public void isUp176(){
        assertTrue(StringExpUtil.isUpperCase((char) 913));
    }
    @Test
    public void isUp177(){
        assertTrue(StringExpUtil.isUpperCase((char) 929));
    }
    @Test
    public void isUp178(){
        assertTrue(StringExpUtil.isUpperCase((char) 931));
    }
    @Test
    public void isUp179(){
        assertTrue(StringExpUtil.isUpperCase((char) 939));
    }
    @Test
    public void isUp180(){
        assertTrue(StringExpUtil.isUpperCase((char) 975));
    }
    @Test
    public void isUp181(){
        assertTrue(StringExpUtil.isUpperCase((char) 984));
    }
    @Test
    public void isUp182(){
        assertTrue(StringExpUtil.isUpperCase((char) 986));
    }
    @Test
    public void isUp183(){
        assertTrue(StringExpUtil.isUpperCase((char) 988));
    }
    @Test
    public void isUp184(){
        assertTrue(StringExpUtil.isUpperCase((char) 990));
    }
    @Test
    public void isUp185(){
        assertTrue(StringExpUtil.isUpperCase((char) 992));
    }
    @Test
    public void isUp186(){
        assertTrue(StringExpUtil.isUpperCase((char) 994));
    }
    @Test
    public void isUp187(){
        assertTrue(StringExpUtil.isUpperCase((char) 996));
    }
    @Test
    public void isUp188(){
        assertTrue(StringExpUtil.isUpperCase((char) 998));
    }
    @Test
    public void isUp189(){
        assertTrue(StringExpUtil.isUpperCase((char) 1000));
    }
    @Test
    public void isUp190(){
        assertTrue(StringExpUtil.isUpperCase((char) 1002));
    }
    @Test
    public void isUp191(){
        assertTrue(StringExpUtil.isUpperCase((char) 1004));
    }
    @Test
    public void isUp192(){
        assertTrue(StringExpUtil.isUpperCase((char) 1006));
    }
    @Test
    public void isUp193(){
        assertTrue(StringExpUtil.isUpperCase((char) 1012));
    }
    @Test
    public void isUp194(){
        assertTrue(StringExpUtil.isUpperCase((char) 1015));
    }
    @Test
    public void isUp195(){
        assertTrue(StringExpUtil.isUpperCase((char) 1017));
    }
    @Test
    public void isUp196(){
        assertTrue(StringExpUtil.isUpperCase((char) 1018));
    }
    @Test
    public void isUp197(){
        assertTrue(StringExpUtil.isUpperCase((char) 1021));
    }
    @Test
    public void isUp198(){
        assertTrue(StringExpUtil.isUpperCase((char) 1071));
    }
    @Test
    public void isUp199(){
        assertTrue(StringExpUtil.isUpperCase((char) 1120));
    }
    @Test
    public void isUp200(){
        assertTrue(StringExpUtil.isUpperCase((char) 1122));
    }
    @Test
    public void isUp201(){
        assertTrue(StringExpUtil.isUpperCase((char) 1124));
    }
    @Test
    public void isUp202(){
        assertTrue(StringExpUtil.isUpperCase((char) 1126));
    }
    @Test
    public void isUp203(){
        assertTrue(StringExpUtil.isUpperCase((char) 1128));
    }
    @Test
    public void isUp204(){
        assertTrue(StringExpUtil.isUpperCase((char) 1130));
    }
    @Test
    public void isUp205(){
        assertTrue(StringExpUtil.isUpperCase((char) 1132));
    }
    @Test
    public void isUp206(){
        assertTrue(StringExpUtil.isUpperCase((char) 1134));
    }
    @Test
    public void isUp207(){
        assertTrue(StringExpUtil.isUpperCase((char) 1136));
    }
    @Test
    public void isUp208(){
        assertTrue(StringExpUtil.isUpperCase((char) 1138));
    }
    @Test
    public void isUp209(){
        assertTrue(StringExpUtil.isUpperCase((char) 1140));
    }
    @Test
    public void isUp210(){
        assertTrue(StringExpUtil.isUpperCase((char) 1142));
    }
    @Test
    public void isUp211(){
        assertTrue(StringExpUtil.isUpperCase((char) 1144));
    }
    @Test
    public void isUp212(){
        assertTrue(StringExpUtil.isUpperCase((char) 1146));
    }
    @Test
    public void isUp213(){
        assertTrue(StringExpUtil.isUpperCase((char) 1148));
    }
    @Test
    public void isUp214(){
        assertTrue(StringExpUtil.isUpperCase((char) 1150));
    }
    @Test
    public void isUp215(){
        assertTrue(StringExpUtil.isUpperCase((char) 1152));
    }
    @Test
    public void isUp216(){
        assertTrue(StringExpUtil.isUpperCase((char) 1162));
    }
    @Test
    public void isUp217(){
        assertTrue(StringExpUtil.isUpperCase((char) 1164));
    }
    @Test
    public void isUp218(){
        assertTrue(StringExpUtil.isUpperCase((char) 1166));
    }
    @Test
    public void isUp219(){
        assertTrue(StringExpUtil.isUpperCase((char) 1168));
    }
    @Test
    public void isUp220(){
        assertTrue(StringExpUtil.isUpperCase((char) 1170));
    }
    @Test
    public void isUp221(){
        assertTrue(StringExpUtil.isUpperCase((char) 1172));
    }
    @Test
    public void isUp222(){
        assertTrue(StringExpUtil.isUpperCase((char) 1174));
    }
    @Test
    public void isUp223(){
        assertTrue(StringExpUtil.isUpperCase((char) 1176));
    }
    @Test
    public void isUp224(){
        assertTrue(StringExpUtil.isUpperCase((char) 1178));
    }
    @Test
    public void isUp225(){
        assertTrue(StringExpUtil.isUpperCase((char) 1180));
    }
    @Test
    public void isUp226(){
        assertTrue(StringExpUtil.isUpperCase((char) 1182));
    }
    @Test
    public void isUp227(){
        assertTrue(StringExpUtil.isUpperCase((char) 1184));
    }
    @Test
    public void isUp228(){
        assertTrue(StringExpUtil.isUpperCase((char) 1186));
    }
    @Test
    public void isUp229(){
        assertTrue(StringExpUtil.isUpperCase((char) 1188));
    }
    @Test
    public void isUp230(){
        assertTrue(StringExpUtil.isUpperCase((char) 1190));
    }
    @Test
    public void isUp231(){
        assertTrue(StringExpUtil.isUpperCase((char) 1192));
    }
    @Test
    public void isUp232(){
        assertTrue(StringExpUtil.isUpperCase((char) 1194));
    }
    @Test
    public void isUp233(){
        assertTrue(StringExpUtil.isUpperCase((char) 1196));
    }
    @Test
    public void isUp234(){
        assertTrue(StringExpUtil.isUpperCase((char) 1198));
    }
    @Test
    public void isUp235(){
        assertTrue(StringExpUtil.isUpperCase((char) 1200));
    }
    @Test
    public void isUp236(){
        assertTrue(StringExpUtil.isUpperCase((char) 1202));
    }
    @Test
    public void isUp237(){
        assertTrue(StringExpUtil.isUpperCase((char) 1204));
    }
    @Test
    public void isUp238(){
        assertTrue(StringExpUtil.isUpperCase((char) 1206));
    }
    @Test
    public void isUp239(){
        assertTrue(StringExpUtil.isUpperCase((char) 1208));
    }
    @Test
    public void isUp240(){
        assertTrue(StringExpUtil.isUpperCase((char) 1210));
    }
    @Test
    public void isUp241(){
        assertTrue(StringExpUtil.isUpperCase((char) 1212));
    }
    @Test
    public void isUp242(){
        assertTrue(StringExpUtil.isUpperCase((char) 1214));
    }
    @Test
    public void isUp243(){
        assertTrue(StringExpUtil.isUpperCase((char) 1216));
    }
    @Test
    public void isUp244(){
        assertTrue(StringExpUtil.isUpperCase((char) 1217));
    }
    @Test
    public void isUp245(){
        assertTrue(StringExpUtil.isUpperCase((char) 1219));
    }
    @Test
    public void isUp246(){
        assertTrue(StringExpUtil.isUpperCase((char) 1221));
    }
    @Test
    public void isUp247(){
        assertTrue(StringExpUtil.isUpperCase((char) 1223));
    }
    @Test
    public void isUp248(){
        assertTrue(StringExpUtil.isUpperCase((char) 1225));
    }
    @Test
    public void isUp249(){
        assertTrue(StringExpUtil.isUpperCase((char) 1227));
    }
    @Test
    public void isUp250(){
        assertTrue(StringExpUtil.isUpperCase((char) 1229));
    }
    @Test
    public void isUp251(){
        assertTrue(StringExpUtil.isUpperCase((char) 1232));
    }
    @Test
    public void isUp252(){
        assertTrue(StringExpUtil.isUpperCase((char) 1234));
    }
    @Test
    public void isUp253(){
        assertTrue(StringExpUtil.isUpperCase((char) 1236));
    }
    @Test
    public void isUp254(){
        assertTrue(StringExpUtil.isUpperCase((char) 1238));
    }
    @Test
    public void isUp255(){
        assertTrue(StringExpUtil.isUpperCase((char) 1240));
    }
    @Test
    public void isUp256(){
        assertTrue(StringExpUtil.isUpperCase((char) 1242));
    }
    @Test
    public void isUp257(){
        assertTrue(StringExpUtil.isUpperCase((char) 1244));
    }
    @Test
    public void isUp258(){
        assertTrue(StringExpUtil.isUpperCase((char) 1246));
    }
    @Test
    public void isUp259(){
        assertTrue(StringExpUtil.isUpperCase((char) 1248));
    }
    @Test
    public void isUp260(){
        assertTrue(StringExpUtil.isUpperCase((char) 1250));
    }
    @Test
    public void isUp261(){
        assertTrue(StringExpUtil.isUpperCase((char) 1252));
    }
    @Test
    public void isUp262(){
        assertTrue(StringExpUtil.isUpperCase((char) 1254));
    }
    @Test
    public void isUp263(){
        assertTrue(StringExpUtil.isUpperCase((char) 1256));
    }
    @Test
    public void isUp264(){
        assertTrue(StringExpUtil.isUpperCase((char) 1258));
    }
    @Test
    public void isUp265(){
        assertTrue(StringExpUtil.isUpperCase((char) 1260));
    }
    @Test
    public void isUp266(){
        assertTrue(StringExpUtil.isUpperCase((char) 1262));
    }
    @Test
    public void isUp267(){
        assertTrue(StringExpUtil.isUpperCase((char) 1264));
    }
    @Test
    public void isUp268(){
        assertTrue(StringExpUtil.isUpperCase((char) 1266));
    }
    @Test
    public void isUp269(){
        assertTrue(StringExpUtil.isUpperCase((char) 1268));
    }
    @Test
    public void isUp270(){
        assertTrue(StringExpUtil.isUpperCase((char) 1270));
    }
    @Test
    public void isUp271(){
        assertTrue(StringExpUtil.isUpperCase((char) 1272));
    }
    @Test
    public void isUp272(){
        assertTrue(StringExpUtil.isUpperCase((char) 1274));
    }
    @Test
    public void isUp273(){
        assertTrue(StringExpUtil.isUpperCase((char) 1276));
    }
    @Test
    public void isUp274(){
        assertTrue(StringExpUtil.isUpperCase((char) 1278));
    }
    @Test
    public void isUp275(){
        assertTrue(StringExpUtil.isUpperCase((char) 1280));
    }
    @Test
    public void isUp276(){
        assertTrue(StringExpUtil.isUpperCase((char) 1282));
    }
    @Test
    public void isUp277(){
        assertTrue(StringExpUtil.isUpperCase((char) 1284));
    }
    @Test
    public void isUp278(){
        assertTrue(StringExpUtil.isUpperCase((char) 1286));
    }
    @Test
    public void isUp279(){
        assertTrue(StringExpUtil.isUpperCase((char) 1288));
    }
    @Test
    public void isUp280(){
        assertTrue(StringExpUtil.isUpperCase((char) 1290));
    }
    @Test
    public void isUp281(){
        assertTrue(StringExpUtil.isUpperCase((char) 1292));
    }
    @Test
    public void isUp282(){
        assertTrue(StringExpUtil.isUpperCase((char) 1294));
    }
    @Test
    public void isUp283(){
        assertTrue(StringExpUtil.isUpperCase((char) 1296));
    }
    @Test
    public void isUp284(){
        assertTrue(StringExpUtil.isUpperCase((char) 1298));
    }
    @Test
    public void isUp285(){
        assertTrue(StringExpUtil.isUpperCase((char) 1300));
    }
    @Test
    public void isUp286(){
        assertTrue(StringExpUtil.isUpperCase((char) 1302));
    }
    @Test
    public void isUp287(){
        assertTrue(StringExpUtil.isUpperCase((char) 1304));
    }
    @Test
    public void isUp288(){
        assertTrue(StringExpUtil.isUpperCase((char) 1306));
    }
    @Test
    public void isUp289(){
        assertTrue(StringExpUtil.isUpperCase((char) 1308));
    }
    @Test
    public void isUp290(){
        assertTrue(StringExpUtil.isUpperCase((char) 1310));
    }
    @Test
    public void isUp291(){
        assertTrue(StringExpUtil.isUpperCase((char) 1312));
    }
    @Test
    public void isUp292(){
        assertTrue(StringExpUtil.isUpperCase((char) 1314));
    }
    @Test
    public void isUp293(){
        assertTrue(StringExpUtil.isUpperCase((char) 1316));
    }
    @Test
    public void isUp294(){
        assertTrue(StringExpUtil.isUpperCase((char) 1318));
    }
    @Test
    public void isUp295(){
        assertTrue(StringExpUtil.isUpperCase((char) 1329));
    }
    @Test
    public void isUp296(){
        assertTrue(StringExpUtil.isUpperCase((char) 1366));
    }
    @Test
    public void isUp297(){
        assertTrue(StringExpUtil.isUpperCase((char) 4256));
    }
    @Test
    public void isUp298(){
        assertTrue(StringExpUtil.isUpperCase((char) 4293));
    }
    @Test
    public void isUp299(){
        assertTrue(StringExpUtil.isUpperCase((char) 4295));
    }
    @Test
    public void isUp300(){
        assertTrue(StringExpUtil.isUpperCase((char) 4301));
    }
    @Test
    public void isUp301(){
        assertTrue(StringExpUtil.isUpperCase((char) 7680));
    }
    @Test
    public void isUp302(){
        assertTrue(StringExpUtil.isUpperCase((char) 7682));
    }
    @Test
    public void isUp303(){
        assertTrue(StringExpUtil.isUpperCase((char) 7684));
    }
    @Test
    public void isUp304(){
        assertTrue(StringExpUtil.isUpperCase((char) 7686));
    }
    @Test
    public void isUp305(){
        assertTrue(StringExpUtil.isUpperCase((char) 7688));
    }
    @Test
    public void isUp306(){
        assertTrue(StringExpUtil.isUpperCase((char) 7690));
    }
    @Test
    public void isUp307(){
        assertTrue(StringExpUtil.isUpperCase((char) 7692));
    }
    @Test
    public void isUp308(){
        assertTrue(StringExpUtil.isUpperCase((char) 7694));
    }
    @Test
    public void isUp309(){
        assertTrue(StringExpUtil.isUpperCase((char) 7696));
    }
    @Test
    public void isUp310(){
        assertTrue(StringExpUtil.isUpperCase((char) 7698));
    }
    @Test
    public void isUp311(){
        assertTrue(StringExpUtil.isUpperCase((char) 7700));
    }
    @Test
    public void isUp312(){
        assertTrue(StringExpUtil.isUpperCase((char) 7702));
    }
    @Test
    public void isUp313(){
        assertTrue(StringExpUtil.isUpperCase((char) 7704));
    }
    @Test
    public void isUp314(){
        assertTrue(StringExpUtil.isUpperCase((char) 7706));
    }
    @Test
    public void isUp315(){
        assertTrue(StringExpUtil.isUpperCase((char) 7708));
    }
    @Test
    public void isUp316(){
        assertTrue(StringExpUtil.isUpperCase((char) 7710));
    }
    @Test
    public void isUp317(){
        assertTrue(StringExpUtil.isUpperCase((char) 7712));
    }
    @Test
    public void isUp318(){
        assertTrue(StringExpUtil.isUpperCase((char) 7714));
    }
    @Test
    public void isUp319(){
        assertTrue(StringExpUtil.isUpperCase((char) 7716));
    }
    @Test
    public void isUp320(){
        assertTrue(StringExpUtil.isUpperCase((char) 7718));
    }
    @Test
    public void isUp321(){
        assertTrue(StringExpUtil.isUpperCase((char) 7720));
    }
    @Test
    public void isUp322(){
        assertTrue(StringExpUtil.isUpperCase((char) 7722));
    }
    @Test
    public void isUp323(){
        assertTrue(StringExpUtil.isUpperCase((char) 7724));
    }
    @Test
    public void isUp324(){
        assertTrue(StringExpUtil.isUpperCase((char) 7726));
    }
    @Test
    public void isUp325(){
        assertTrue(StringExpUtil.isUpperCase((char) 7728));
    }
    @Test
    public void isUp326(){
        assertTrue(StringExpUtil.isUpperCase((char) 7730));
    }
    @Test
    public void isUp327(){
        assertTrue(StringExpUtil.isUpperCase((char) 7732));
    }
    @Test
    public void isUp328(){
        assertTrue(StringExpUtil.isUpperCase((char) 7734));
    }
    @Test
    public void isUp329(){
        assertTrue(StringExpUtil.isUpperCase((char) 7736));
    }
    @Test
    public void isUp330(){
        assertTrue(StringExpUtil.isUpperCase((char) 7738));
    }
    @Test
    public void isUp331(){
        assertTrue(StringExpUtil.isUpperCase((char) 7740));
    }
    @Test
    public void isUp332(){
        assertTrue(StringExpUtil.isUpperCase((char) 7742));
    }
    @Test
    public void isUp333(){
        assertTrue(StringExpUtil.isUpperCase((char) 7744));
    }
    @Test
    public void isUp334(){
        assertTrue(StringExpUtil.isUpperCase((char) 7746));
    }
    @Test
    public void isUp335(){
        assertTrue(StringExpUtil.isUpperCase((char) 7748));
    }
    @Test
    public void isUp336(){
        assertTrue(StringExpUtil.isUpperCase((char) 7750));
    }
    @Test
    public void isUp337(){
        assertTrue(StringExpUtil.isUpperCase((char) 7752));
    }
    @Test
    public void isUp338(){
        assertTrue(StringExpUtil.isUpperCase((char) 7754));
    }
    @Test
    public void isUp339(){
        assertTrue(StringExpUtil.isUpperCase((char) 7756));
    }
    @Test
    public void isUp340(){
        assertTrue(StringExpUtil.isUpperCase((char) 7758));
    }
    @Test
    public void isUp341(){
        assertTrue(StringExpUtil.isUpperCase((char) 7760));
    }
    @Test
    public void isUp342(){
        assertTrue(StringExpUtil.isUpperCase((char) 7762));
    }
    @Test
    public void isUp343(){
        assertTrue(StringExpUtil.isUpperCase((char) 7764));
    }
    @Test
    public void isUp344(){
        assertTrue(StringExpUtil.isUpperCase((char) 7766));
    }
    @Test
    public void isUp345(){
        assertTrue(StringExpUtil.isUpperCase((char) 7768));
    }
    @Test
    public void isUp346(){
        assertTrue(StringExpUtil.isUpperCase((char) 7770));
    }
    @Test
    public void isUp347(){
        assertTrue(StringExpUtil.isUpperCase((char) 7772));
    }
    @Test
    public void isUp348(){
        assertTrue(StringExpUtil.isUpperCase((char) 7774));
    }
    @Test
    public void isUp349(){
        assertTrue(StringExpUtil.isUpperCase((char) 7776));
    }
    @Test
    public void isUp350(){
        assertTrue(StringExpUtil.isUpperCase((char) 7778));
    }
    @Test
    public void isUp351(){
        assertTrue(StringExpUtil.isUpperCase((char) 7780));
    }
    @Test
    public void isUp352(){
        assertTrue(StringExpUtil.isUpperCase((char) 7782));
    }
    @Test
    public void isUp353(){
        assertTrue(StringExpUtil.isUpperCase((char) 7784));
    }
    @Test
    public void isUp354(){
        assertTrue(StringExpUtil.isUpperCase((char) 7786));
    }
    @Test
    public void isUp355(){
        assertTrue(StringExpUtil.isUpperCase((char) 7788));
    }
    @Test
    public void isUp356(){
        assertTrue(StringExpUtil.isUpperCase((char) 7790));
    }
    @Test
    public void isUp357(){
        assertTrue(StringExpUtil.isUpperCase((char) 7792));
    }
    @Test
    public void isUp358(){
        assertTrue(StringExpUtil.isUpperCase((char) 7794));
    }
    @Test
    public void isUp359(){
        assertTrue(StringExpUtil.isUpperCase((char) 7796));
    }
    @Test
    public void isUp360(){
        assertTrue(StringExpUtil.isUpperCase((char) 7798));
    }
    @Test
    public void isUp361(){
        assertTrue(StringExpUtil.isUpperCase((char) 7800));
    }
    @Test
    public void isUp362(){
        assertTrue(StringExpUtil.isUpperCase((char) 7802));
    }
    @Test
    public void isUp363(){
        assertTrue(StringExpUtil.isUpperCase((char) 7804));
    }
    @Test
    public void isUp364(){
        assertTrue(StringExpUtil.isUpperCase((char) 7806));
    }
    @Test
    public void isUp365(){
        assertTrue(StringExpUtil.isUpperCase((char) 7808));
    }
    @Test
    public void isUp366(){
        assertTrue(StringExpUtil.isUpperCase((char) 7810));
    }
    @Test
    public void isUp367(){
        assertTrue(StringExpUtil.isUpperCase((char) 7812));
    }
    @Test
    public void isUp368(){
        assertTrue(StringExpUtil.isUpperCase((char) 7814));
    }
    @Test
    public void isUp369(){
        assertTrue(StringExpUtil.isUpperCase((char) 7816));
    }
    @Test
    public void isUp370(){
        assertTrue(StringExpUtil.isUpperCase((char) 7818));
    }
    @Test
    public void isUp371(){
        assertTrue(StringExpUtil.isUpperCase((char) 7820));
    }
    @Test
    public void isUp372(){
        assertTrue(StringExpUtil.isUpperCase((char) 7822));
    }
    @Test
    public void isUp373(){
        assertTrue(StringExpUtil.isUpperCase((char) 7824));
    }
    @Test
    public void isUp374(){
        assertTrue(StringExpUtil.isUpperCase((char) 7826));
    }
    @Test
    public void isUp375(){
        assertTrue(StringExpUtil.isUpperCase((char) 7828));
    }
    @Test
    public void isUp376(){
        assertTrue(StringExpUtil.isUpperCase((char) 7838));
    }
    @Test
    public void isUp377(){
        assertTrue(StringExpUtil.isUpperCase((char) 7840));
    }
    @Test
    public void isUp378(){
        assertTrue(StringExpUtil.isUpperCase((char) 7842));
    }
    @Test
    public void isUp379(){
        assertTrue(StringExpUtil.isUpperCase((char) 7844));
    }
    @Test
    public void isUp380(){
        assertTrue(StringExpUtil.isUpperCase((char) 7846));
    }
    @Test
    public void isUp381(){
        assertTrue(StringExpUtil.isUpperCase((char) 7848));
    }
    @Test
    public void isUp382(){
        assertTrue(StringExpUtil.isUpperCase((char) 7850));
    }
    @Test
    public void isUp383(){
        assertTrue(StringExpUtil.isUpperCase((char) 7852));
    }
    @Test
    public void isUp384(){
        assertTrue(StringExpUtil.isUpperCase((char) 7854));
    }
    @Test
    public void isUp385(){
        assertTrue(StringExpUtil.isUpperCase((char) 7856));
    }
    @Test
    public void isUp386(){
        assertTrue(StringExpUtil.isUpperCase((char) 7858));
    }
    @Test
    public void isUp387(){
        assertTrue(StringExpUtil.isUpperCase((char) 7860));
    }
    @Test
    public void isUp388(){
        assertTrue(StringExpUtil.isUpperCase((char) 7862));
    }
    @Test
    public void isUp389(){
        assertTrue(StringExpUtil.isUpperCase((char) 7864));
    }
    @Test
    public void isUp390(){
        assertTrue(StringExpUtil.isUpperCase((char) 7866));
    }
    @Test
    public void isUp391(){
        assertTrue(StringExpUtil.isUpperCase((char) 7868));
    }
    @Test
    public void isUp392(){
        assertTrue(StringExpUtil.isUpperCase((char) 7870));
    }
    @Test
    public void isUp393(){
        assertTrue(StringExpUtil.isUpperCase((char) 7872));
    }
    @Test
    public void isUp394(){
        assertTrue(StringExpUtil.isUpperCase((char) 7874));
    }
    @Test
    public void isUp395(){
        assertTrue(StringExpUtil.isUpperCase((char) 7876));
    }
    @Test
    public void isUp396(){
        assertTrue(StringExpUtil.isUpperCase((char) 7878));
    }
    @Test
    public void isUp397(){
        assertTrue(StringExpUtil.isUpperCase((char) 7880));
    }
    @Test
    public void isUp398(){
        assertTrue(StringExpUtil.isUpperCase((char) 7882));
    }
    @Test
    public void isUp399(){
        assertTrue(StringExpUtil.isUpperCase((char) 7884));
    }
    @Test
    public void isUp400(){
        assertTrue(StringExpUtil.isUpperCase((char) 7886));
    }
    @Test
    public void isUp401(){
        assertTrue(StringExpUtil.isUpperCase((char) 7888));
    }
    @Test
    public void isUp402(){
        assertTrue(StringExpUtil.isUpperCase((char) 7890));
    }
    @Test
    public void isUp403(){
        assertTrue(StringExpUtil.isUpperCase((char) 7892));
    }
    @Test
    public void isUp404(){
        assertTrue(StringExpUtil.isUpperCase((char) 7894));
    }
    @Test
    public void isUp405(){
        assertTrue(StringExpUtil.isUpperCase((char) 7896));
    }
    @Test
    public void isUp406(){
        assertTrue(StringExpUtil.isUpperCase((char) 7898));
    }
    @Test
    public void isUp407(){
        assertTrue(StringExpUtil.isUpperCase((char) 7900));
    }
    @Test
    public void isUp408(){
        assertTrue(StringExpUtil.isUpperCase((char) 7902));
    }
    @Test
    public void isUp409(){
        assertTrue(StringExpUtil.isUpperCase((char) 7904));
    }
    @Test
    public void isUp410(){
        assertTrue(StringExpUtil.isUpperCase((char) 7906));
    }
    @Test
    public void isUp411(){
        assertTrue(StringExpUtil.isUpperCase((char) 7908));
    }
    @Test
    public void isUp412(){
        assertTrue(StringExpUtil.isUpperCase((char) 7910));
    }
    @Test
    public void isUp413(){
        assertTrue(StringExpUtil.isUpperCase((char) 7912));
    }
    @Test
    public void isUp414(){
        assertTrue(StringExpUtil.isUpperCase((char) 7914));
    }
    @Test
    public void isUp415(){
        assertTrue(StringExpUtil.isUpperCase((char) 7916));
    }
    @Test
    public void isUp416(){
        assertTrue(StringExpUtil.isUpperCase((char) 7918));
    }
    @Test
    public void isUp417(){
        assertTrue(StringExpUtil.isUpperCase((char) 7920));
    }
    @Test
    public void isUp418(){
        assertTrue(StringExpUtil.isUpperCase((char) 7922));
    }
    @Test
    public void isUp419(){
        assertTrue(StringExpUtil.isUpperCase((char) 7924));
    }
    @Test
    public void isUp420(){
        assertTrue(StringExpUtil.isUpperCase((char) 7926));
    }
    @Test
    public void isUp421(){
        assertTrue(StringExpUtil.isUpperCase((char) 7928));
    }
    @Test
    public void isUp422(){
        assertTrue(StringExpUtil.isUpperCase((char) 7930));
    }
    @Test
    public void isUp423(){
        assertTrue(StringExpUtil.isUpperCase((char) 7932));
    }
    @Test
    public void isUp424(){
        assertTrue(StringExpUtil.isUpperCase((char) 7934));
    }
    @Test
    public void isUp425(){
        assertTrue(StringExpUtil.isUpperCase((char) 7944));
    }
    @Test
    public void isUp426(){
        assertTrue(StringExpUtil.isUpperCase((char) 7951));
    }
    @Test
    public void isUp427(){
        assertTrue(StringExpUtil.isUpperCase((char) 7960));
    }
    @Test
    public void isUp428(){
        assertTrue(StringExpUtil.isUpperCase((char) 7965));
    }
    @Test
    public void isUp429(){
        assertTrue(StringExpUtil.isUpperCase((char) 7976));
    }
    @Test
    public void isUp430(){
        assertTrue(StringExpUtil.isUpperCase((char) 7983));
    }
    @Test
    public void isUp431(){
        assertTrue(StringExpUtil.isUpperCase((char) 7992));
    }
    @Test
    public void isUp432(){
        assertTrue(StringExpUtil.isUpperCase((char) 7999));
    }
    @Test
    public void isUp433(){
        assertTrue(StringExpUtil.isUpperCase((char) 8008));
    }
    @Test
    public void isUp434(){
        assertTrue(StringExpUtil.isUpperCase((char) 8013));
    }
    @Test
    public void isUp435(){
        assertTrue(StringExpUtil.isUpperCase((char) 8025));
    }
    @Test
    public void isUp436(){
        assertTrue(StringExpUtil.isUpperCase((char) 8027));
    }
    @Test
    public void isUp437(){
        assertTrue(StringExpUtil.isUpperCase((char) 8029));
    }
    @Test
    public void isUp438(){
        assertTrue(StringExpUtil.isUpperCase((char) 8031));
    }
    @Test
    public void isUp439(){
        assertTrue(StringExpUtil.isUpperCase((char) 8040));
    }
    @Test
    public void isUp440(){
        assertTrue(StringExpUtil.isUpperCase((char) 8047));
    }
    @Test
    public void isUp441(){
        assertTrue(StringExpUtil.isUpperCase((char) 8120));
    }
    @Test
    public void isUp442(){
        assertTrue(StringExpUtil.isUpperCase((char) 8123));
    }
    @Test
    public void isUp443(){
        assertTrue(StringExpUtil.isUpperCase((char) 8136));
    }
    @Test
    public void isUp444(){
        assertTrue(StringExpUtil.isUpperCase((char) 8139));
    }
    @Test
    public void isUp445(){
        assertTrue(StringExpUtil.isUpperCase((char) 8152));
    }
    @Test
    public void isUp446(){
        assertTrue(StringExpUtil.isUpperCase((char) 8155));
    }
    @Test
    public void isUp447(){
        assertTrue(StringExpUtil.isUpperCase((char) 8168));
    }
    @Test
    public void isUp448(){
        assertTrue(StringExpUtil.isUpperCase((char) 8172));
    }
    @Test
    public void isUp449(){
        assertTrue(StringExpUtil.isUpperCase((char) 8184));
    }
    @Test
    public void isUp450(){
        assertTrue(StringExpUtil.isUpperCase((char) 8187));
    }
    @Test
    public void isUp451(){
        assertTrue(StringExpUtil.isUpperCase((char) 8486));
    }
    @Test
    public void isUp452(){
        assertTrue(StringExpUtil.isUpperCase((char) 8490));
    }
    @Test
    public void isUp453(){
        assertTrue(StringExpUtil.isUpperCase((char) 8491));
    }
    @Test
    public void isUp454(){
        assertTrue(StringExpUtil.isUpperCase((char) 8498));
    }
    @Test
    public void isUp455(){
        assertTrue(StringExpUtil.isUpperCase((char) 8579));
    }
    @Test
    public void isUp456(){
        assertTrue(StringExpUtil.isUpperCase((char) 11264));
    }
    @Test
    public void isUp457(){
        assertTrue(StringExpUtil.isUpperCase((char) 11310));
    }
    @Test
    public void isUp458(){
        assertTrue(StringExpUtil.isUpperCase((char) 11360));
    }
    @Test
    public void isUp459(){
        assertTrue(StringExpUtil.isUpperCase((char) 11362));
    }
    @Test
    public void isUp460(){
        assertTrue(StringExpUtil.isUpperCase((char) 11364));
    }
    @Test
    public void isUp461(){
        assertTrue(StringExpUtil.isUpperCase((char) 11367));
    }
    @Test
    public void isUp462(){
        assertTrue(StringExpUtil.isUpperCase((char) 11369));
    }
    @Test
    public void isUp463(){
        assertTrue(StringExpUtil.isUpperCase((char) 11371));
    }
    @Test
    public void isUp464(){
        assertTrue(StringExpUtil.isUpperCase((char) 11373));
    }
    @Test
    public void isUp465(){
        assertTrue(StringExpUtil.isUpperCase((char) 11376));
    }
    @Test
    public void isUp466(){
        assertTrue(StringExpUtil.isUpperCase((char) 11378));
    }
    @Test
    public void isUp467(){
        assertTrue(StringExpUtil.isUpperCase((char) 11381));
    }
    @Test
    public void isUp468(){
        assertTrue(StringExpUtil.isUpperCase((char) 11390));
    }
    @Test
    public void isUp469(){
        assertTrue(StringExpUtil.isUpperCase((char) 11392));
    }
    @Test
    public void isUp470(){
        assertTrue(StringExpUtil.isUpperCase((char) 11394));
    }
    @Test
    public void isUp471(){
        assertTrue(StringExpUtil.isUpperCase((char) 11396));
    }
    @Test
    public void isUp472(){
        assertTrue(StringExpUtil.isUpperCase((char) 11398));
    }
    @Test
    public void isUp473(){
        assertTrue(StringExpUtil.isUpperCase((char) 11400));
    }
    @Test
    public void isUp474(){
        assertTrue(StringExpUtil.isUpperCase((char) 11402));
    }
    @Test
    public void isUp475(){
        assertTrue(StringExpUtil.isUpperCase((char) 11404));
    }
    @Test
    public void isUp476(){
        assertTrue(StringExpUtil.isUpperCase((char) 11406));
    }
    @Test
    public void isUp477(){
        assertTrue(StringExpUtil.isUpperCase((char) 11408));
    }
    @Test
    public void isUp478(){
        assertTrue(StringExpUtil.isUpperCase((char) 11410));
    }
    @Test
    public void isUp479(){
        assertTrue(StringExpUtil.isUpperCase((char) 11412));
    }
    @Test
    public void isUp480(){
        assertTrue(StringExpUtil.isUpperCase((char) 11414));
    }
    @Test
    public void isUp481(){
        assertTrue(StringExpUtil.isUpperCase((char) 11416));
    }
    @Test
    public void isUp482(){
        assertTrue(StringExpUtil.isUpperCase((char) 11418));
    }
    @Test
    public void isUp483(){
        assertTrue(StringExpUtil.isUpperCase((char) 11420));
    }
    @Test
    public void isUp484(){
        assertTrue(StringExpUtil.isUpperCase((char) 11422));
    }
    @Test
    public void isUp485(){
        assertTrue(StringExpUtil.isUpperCase((char) 11424));
    }
    @Test
    public void isUp486(){
        assertTrue(StringExpUtil.isUpperCase((char) 11426));
    }
    @Test
    public void isUp487(){
        assertTrue(StringExpUtil.isUpperCase((char) 11428));
    }
    @Test
    public void isUp488(){
        assertTrue(StringExpUtil.isUpperCase((char) 11430));
    }
    @Test
    public void isUp489(){
        assertTrue(StringExpUtil.isUpperCase((char) 11432));
    }
    @Test
    public void isUp490(){
        assertTrue(StringExpUtil.isUpperCase((char) 11434));
    }
    @Test
    public void isUp491(){
        assertTrue(StringExpUtil.isUpperCase((char) 11436));
    }
    @Test
    public void isUp492(){
        assertTrue(StringExpUtil.isUpperCase((char) 11438));
    }
    @Test
    public void isUp493(){
        assertTrue(StringExpUtil.isUpperCase((char) 11440));
    }
    @Test
    public void isUp494(){
        assertTrue(StringExpUtil.isUpperCase((char) 11442));
    }
    @Test
    public void isUp495(){
        assertTrue(StringExpUtil.isUpperCase((char) 11444));
    }
    @Test
    public void isUp496(){
        assertTrue(StringExpUtil.isUpperCase((char) 11446));
    }
    @Test
    public void isUp497(){
        assertTrue(StringExpUtil.isUpperCase((char) 11448));
    }
    @Test
    public void isUp498(){
        assertTrue(StringExpUtil.isUpperCase((char) 11450));
    }
    @Test
    public void isUp499(){
        assertTrue(StringExpUtil.isUpperCase((char) 11452));
    }
    @Test
    public void isUp500(){
        assertTrue(StringExpUtil.isUpperCase((char) 11454));
    }
    @Test
    public void isUp501(){
        assertTrue(StringExpUtil.isUpperCase((char) 11456));
    }
    @Test
    public void isUp502(){
        assertTrue(StringExpUtil.isUpperCase((char) 11458));
    }
    @Test
    public void isUp503(){
        assertTrue(StringExpUtil.isUpperCase((char) 11460));
    }
    @Test
    public void isUp504(){
        assertTrue(StringExpUtil.isUpperCase((char) 11462));
    }
    @Test
    public void isUp505(){
        assertTrue(StringExpUtil.isUpperCase((char) 11464));
    }
    @Test
    public void isUp506(){
        assertTrue(StringExpUtil.isUpperCase((char) 11466));
    }
    @Test
    public void isUp507(){
        assertTrue(StringExpUtil.isUpperCase((char) 11468));
    }
    @Test
    public void isUp508(){
        assertTrue(StringExpUtil.isUpperCase((char) 11470));
    }
    @Test
    public void isUp509(){
        assertTrue(StringExpUtil.isUpperCase((char) 11472));
    }
    @Test
    public void isUp510(){
        assertTrue(StringExpUtil.isUpperCase((char) 11474));
    }
    @Test
    public void isUp511(){
        assertTrue(StringExpUtil.isUpperCase((char) 11476));
    }
    @Test
    public void isUp512(){
        assertTrue(StringExpUtil.isUpperCase((char) 11478));
    }
    @Test
    public void isUp513(){
        assertTrue(StringExpUtil.isUpperCase((char) 11480));
    }
    @Test
    public void isUp514(){
        assertTrue(StringExpUtil.isUpperCase((char) 11482));
    }
    @Test
    public void isUp515(){
        assertTrue(StringExpUtil.isUpperCase((char) 11484));
    }
    @Test
    public void isUp516(){
        assertTrue(StringExpUtil.isUpperCase((char) 11486));
    }
    @Test
    public void isUp517(){
        assertTrue(StringExpUtil.isUpperCase((char) 11488));
    }
    @Test
    public void isUp518(){
        assertTrue(StringExpUtil.isUpperCase((char) 11490));
    }
    @Test
    public void isUp519(){
        assertTrue(StringExpUtil.isUpperCase((char) 11499));
    }
    @Test
    public void isUp520(){
        assertTrue(StringExpUtil.isUpperCase((char) 11501));
    }
    @Test
    public void isUp521(){
        assertTrue(StringExpUtil.isUpperCase((char) 11506));
    }
    @Test
    public void isUp522(){
        assertTrue(StringExpUtil.isUpperCase((char) 42560));
    }
    @Test
    public void isUp523(){
        assertTrue(StringExpUtil.isUpperCase((char) 42562));
    }
    @Test
    public void isUp524(){
        assertTrue(StringExpUtil.isUpperCase((char) 42564));
    }
    @Test
    public void isUp525(){
        assertTrue(StringExpUtil.isUpperCase((char) 42566));
    }
    @Test
    public void isUp526(){
        assertTrue(StringExpUtil.isUpperCase((char) 42568));
    }
    @Test
    public void isUp527(){
        assertTrue(StringExpUtil.isUpperCase((char) 42570));
    }
    @Test
    public void isUp528(){
        assertTrue(StringExpUtil.isUpperCase((char) 42572));
    }
    @Test
    public void isUp529(){
        assertTrue(StringExpUtil.isUpperCase((char) 42574));
    }
    @Test
    public void isUp530(){
        assertTrue(StringExpUtil.isUpperCase((char) 42576));
    }
    @Test
    public void isUp531(){
        assertTrue(StringExpUtil.isUpperCase((char) 42578));
    }
    @Test
    public void isUp532(){
        assertTrue(StringExpUtil.isUpperCase((char) 42580));
    }
    @Test
    public void isUp533(){
        assertTrue(StringExpUtil.isUpperCase((char) 42582));
    }
    @Test
    public void isUp534(){
        assertTrue(StringExpUtil.isUpperCase((char) 42584));
    }
    @Test
    public void isUp535(){
        assertTrue(StringExpUtil.isUpperCase((char) 42586));
    }
    @Test
    public void isUp536(){
        assertTrue(StringExpUtil.isUpperCase((char) 42588));
    }
    @Test
    public void isUp537(){
        assertTrue(StringExpUtil.isUpperCase((char) 42590));
    }
    @Test
    public void isUp538(){
        assertTrue(StringExpUtil.isUpperCase((char) 42592));
    }
    @Test
    public void isUp539(){
        assertTrue(StringExpUtil.isUpperCase((char) 42594));
    }
    @Test
    public void isUp540(){
        assertTrue(StringExpUtil.isUpperCase((char) 42596));
    }
    @Test
    public void isUp541(){
        assertTrue(StringExpUtil.isUpperCase((char) 42598));
    }
    @Test
    public void isUp542(){
        assertTrue(StringExpUtil.isUpperCase((char) 42600));
    }
    @Test
    public void isUp543(){
        assertTrue(StringExpUtil.isUpperCase((char) 42602));
    }
    @Test
    public void isUp544(){
        assertTrue(StringExpUtil.isUpperCase((char) 42604));
    }
    @Test
    public void isUp545(){
        assertTrue(StringExpUtil.isUpperCase((char) 42624));
    }
    @Test
    public void isUp546(){
        assertTrue(StringExpUtil.isUpperCase((char) 42626));
    }
    @Test
    public void isUp547(){
        assertTrue(StringExpUtil.isUpperCase((char) 42628));
    }
    @Test
    public void isUp548(){
        assertTrue(StringExpUtil.isUpperCase((char) 42630));
    }
    @Test
    public void isUp549(){
        assertTrue(StringExpUtil.isUpperCase((char) 42632));
    }
    @Test
    public void isUp550(){
        assertTrue(StringExpUtil.isUpperCase((char) 42634));
    }
    @Test
    public void isUp551(){
        assertTrue(StringExpUtil.isUpperCase((char) 42636));
    }
    @Test
    public void isUp552(){
        assertTrue(StringExpUtil.isUpperCase((char) 42638));
    }
    @Test
    public void isUp553(){
        assertTrue(StringExpUtil.isUpperCase((char) 42640));
    }
    @Test
    public void isUp554(){
        assertTrue(StringExpUtil.isUpperCase((char) 42642));
    }
    @Test
    public void isUp555(){
        assertTrue(StringExpUtil.isUpperCase((char) 42644));
    }
    @Test
    public void isUp556(){
        assertTrue(StringExpUtil.isUpperCase((char) 42646));
    }
    @Test
    public void isUp557(){
        assertTrue(StringExpUtil.isUpperCase((char) 42786));
    }
    @Test
    public void isUp558(){
        assertTrue(StringExpUtil.isUpperCase((char) 42788));
    }
    @Test
    public void isUp559(){
        assertTrue(StringExpUtil.isUpperCase((char) 42790));
    }
    @Test
    public void isUp560(){
        assertTrue(StringExpUtil.isUpperCase((char) 42792));
    }
    @Test
    public void isUp561(){
        assertTrue(StringExpUtil.isUpperCase((char) 42794));
    }
    @Test
    public void isUp562(){
        assertTrue(StringExpUtil.isUpperCase((char) 42796));
    }
    @Test
    public void isUp563(){
        assertTrue(StringExpUtil.isUpperCase((char) 42798));
    }
    @Test
    public void isUp564(){
        assertTrue(StringExpUtil.isUpperCase((char) 42802));
    }
    @Test
    public void isUp565(){
        assertTrue(StringExpUtil.isUpperCase((char) 42804));
    }
    @Test
    public void isUp566(){
        assertTrue(StringExpUtil.isUpperCase((char) 42806));
    }
    @Test
    public void isUp567(){
        assertTrue(StringExpUtil.isUpperCase((char) 42808));
    }
    @Test
    public void isUp568(){
        assertTrue(StringExpUtil.isUpperCase((char) 42810));
    }
    @Test
    public void isUp569(){
        assertTrue(StringExpUtil.isUpperCase((char) 42812));
    }
    @Test
    public void isUp570(){
        assertTrue(StringExpUtil.isUpperCase((char) 42814));
    }
    @Test
    public void isUp571(){
        assertTrue(StringExpUtil.isUpperCase((char) 42816));
    }
    @Test
    public void isUp572(){
        assertTrue(StringExpUtil.isUpperCase((char) 42818));
    }
    @Test
    public void isUp573(){
        assertTrue(StringExpUtil.isUpperCase((char) 42820));
    }
    @Test
    public void isUp574(){
        assertTrue(StringExpUtil.isUpperCase((char) 42822));
    }
    @Test
    public void isUp575(){
        assertTrue(StringExpUtil.isUpperCase((char) 42824));
    }
    @Test
    public void isUp576(){
        assertTrue(StringExpUtil.isUpperCase((char) 42826));
    }
    @Test
    public void isUp577(){
        assertTrue(StringExpUtil.isUpperCase((char) 42828));
    }
    @Test
    public void isUp578(){
        assertTrue(StringExpUtil.isUpperCase((char) 42830));
    }
    @Test
    public void isUp579(){
        assertTrue(StringExpUtil.isUpperCase((char) 42832));
    }
    @Test
    public void isUp580(){
        assertTrue(StringExpUtil.isUpperCase((char) 42834));
    }
    @Test
    public void isUp581(){
        assertTrue(StringExpUtil.isUpperCase((char) 42836));
    }
    @Test
    public void isUp582(){
        assertTrue(StringExpUtil.isUpperCase((char) 42838));
    }
    @Test
    public void isUp583(){
        assertTrue(StringExpUtil.isUpperCase((char) 42840));
    }
    @Test
    public void isUp584(){
        assertTrue(StringExpUtil.isUpperCase((char) 42842));
    }
    @Test
    public void isUp585(){
        assertTrue(StringExpUtil.isUpperCase((char) 42844));
    }
    @Test
    public void isUp586(){
        assertTrue(StringExpUtil.isUpperCase((char) 42846));
    }
    @Test
    public void isUp587(){
        assertTrue(StringExpUtil.isUpperCase((char) 42848));
    }
    @Test
    public void isUp588(){
        assertTrue(StringExpUtil.isUpperCase((char) 42850));
    }
    @Test
    public void isUp589(){
        assertTrue(StringExpUtil.isUpperCase((char) 42852));
    }
    @Test
    public void isUp590(){
        assertTrue(StringExpUtil.isUpperCase((char) 42854));
    }
    @Test
    public void isUp591(){
        assertTrue(StringExpUtil.isUpperCase((char) 42856));
    }
    @Test
    public void isUp592(){
        assertTrue(StringExpUtil.isUpperCase((char) 42858));
    }
    @Test
    public void isUp593(){
        assertTrue(StringExpUtil.isUpperCase((char) 42860));
    }
    @Test
    public void isUp594(){
        assertTrue(StringExpUtil.isUpperCase((char) 42862));
    }
    @Test
    public void isUp595(){
        assertTrue(StringExpUtil.isUpperCase((char) 42873));
    }
    @Test
    public void isUp596(){
        assertTrue(StringExpUtil.isUpperCase((char) 42875));
    }
    @Test
    public void isUp597(){
        assertTrue(StringExpUtil.isUpperCase((char) 42877));
    }
    @Test
    public void isUp598(){
        assertTrue(StringExpUtil.isUpperCase((char) 42878));
    }
    @Test
    public void isUp599(){
        assertTrue(StringExpUtil.isUpperCase((char) 42880));
    }
    @Test
    public void isUp600(){
        assertTrue(StringExpUtil.isUpperCase((char) 42882));
    }
    @Test
    public void isUp601(){
        assertTrue(StringExpUtil.isUpperCase((char) 42884));
    }
    @Test
    public void isUp602(){
        assertTrue(StringExpUtil.isUpperCase((char) 42886));
    }
    @Test
    public void isUp603(){
        assertTrue(StringExpUtil.isUpperCase((char) 42891));
    }
    @Test
    public void isUp604(){
        assertTrue(StringExpUtil.isUpperCase((char) 42893));
    }
    @Test
    public void isUp605(){
        assertTrue(StringExpUtil.isUpperCase((char) 42896));
    }
    @Test
    public void isUp606(){
        assertTrue(StringExpUtil.isUpperCase((char) 42898));
    }
    @Test
    public void isUp607(){
        assertTrue(StringExpUtil.isUpperCase((char) 42912));
    }
    @Test
    public void isUp608(){
        assertTrue(StringExpUtil.isUpperCase((char) 42914));
    }
    @Test
    public void isUp609(){
        assertTrue(StringExpUtil.isUpperCase((char) 42916));
    }
    @Test
    public void isUp610(){
        assertTrue(StringExpUtil.isUpperCase((char) 42918));
    }
    @Test
    public void isUp611(){
        assertTrue(StringExpUtil.isUpperCase((char) 42920));
    }
    @Test
    public void isUp612(){
        assertTrue(StringExpUtil.isUpperCase((char) 42922));
    }
    @Test
    public void isUp613(){
        assertTrue(StringExpUtil.isUpperCase((char) 65313));
    }
    @Test
    public void isUp614(){
        assertTrue(StringExpUtil.isUpperCase((char) 65338));
    }
    @Test
    public void isUp615(){
        assertTrue(!StringExpUtil.isUpperCase((char) 888));
    }
    @Test
    public void isUp616(){
        assertTrue(!StringExpUtil.isUpperCase((char) 889));
    }
    @Test
    public void isUp617(){
        assertTrue(!StringExpUtil.isUpperCase((char) 895));
    }
    @Test
    public void isUp618(){
        assertTrue(!StringExpUtil.isUpperCase((char) 899));
    }
    @Test
    public void isUp619(){
        assertTrue(!StringExpUtil.isUpperCase((char) 907));
    }
    @Test
    public void isUp620(){
        assertTrue(!StringExpUtil.isUpperCase((char) 909));
    }
    @Test
    public void isUp621(){
        assertTrue(!StringExpUtil.isUpperCase((char) 930));
    }
    @Test
    public void isUp622(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1320));
    }
    @Test
    public void isUp623(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1328));
    }
    @Test
    public void isUp624(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1367));
    }
    @Test
    public void isUp625(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1368));
    }
    @Test
    public void isUp626(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1376));
    }
    @Test
    public void isUp627(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1416));
    }
    @Test
    public void isUp628(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1419));
    }
    @Test
    public void isUp629(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1422));
    }
    @Test
    public void isUp630(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1424));
    }
    @Test
    public void isUp631(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1480));
    }
    @Test
    public void isUp632(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1487));
    }
    @Test
    public void isUp633(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1515));
    }
    @Test
    public void isUp634(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1519));
    }
    @Test
    public void isUp635(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1525));
    }
    @Test
    public void isUp636(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1535));
    }
    @Test
    public void isUp637(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1541));
    }
    @Test
    public void isUp638(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1564));
    }
    @Test
    public void isUp639(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1565));
    }
    @Test
    public void isUp640(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1806));
    }
    @Test
    public void isUp641(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1867));
    }
    @Test
    public void isUp642(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1868));
    }
    @Test
    public void isUp643(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1970));
    }
    @Test
    public void isUp644(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1983));
    }
    @Test
    public void isUp645(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2043));
    }
    @Test
    public void isUp646(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2047));
    }
    @Test
    public void isUp647(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2094));
    }
    @Test
    public void isUp648(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2095));
    }
    @Test
    public void isUp649(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2111));
    }
    @Test
    public void isUp650(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2140));
    }
    @Test
    public void isUp651(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2141));
    }
    @Test
    public void isUp652(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2143));
    }
    @Test
    public void isUp653(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2207));
    }
    @Test
    public void isUp654(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2209));
    }
    @Test
    public void isUp655(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2221));
    }
    @Test
    public void isUp656(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2275));
    }
    @Test
    public void isUp657(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2303));
    }
    @Test
    public void isUp658(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2424));
    }
    @Test
    public void isUp659(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2432));
    }
    @Test
    public void isUp660(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2436));
    }
    @Test
    public void isUp661(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2445));
    }
    @Test
    public void isUp662(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2446));
    }
    @Test
    public void isUp663(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2449));
    }
    @Test
    public void isUp664(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2450));
    }
    @Test
    public void isUp665(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2473));
    }
    @Test
    public void isUp666(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2481));
    }
    @Test
    public void isUp667(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2483));
    }
    @Test
    public void isUp668(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2485));
    }
    @Test
    public void isUp669(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2490));
    }
    @Test
    public void isUp670(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2491));
    }
    @Test
    public void isUp671(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2501));
    }
    @Test
    public void isUp672(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2502));
    }
    @Test
    public void isUp673(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2505));
    }
    @Test
    public void isUp674(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2506));
    }
    @Test
    public void isUp675(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2511));
    }
    @Test
    public void isUp676(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2518));
    }
    @Test
    public void isUp677(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2520));
    }
    @Test
    public void isUp678(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2523));
    }
    @Test
    public void isUp679(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2526));
    }
    @Test
    public void isUp680(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2532));
    }
    @Test
    public void isUp681(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2533));
    }
    @Test
    public void isUp682(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2556));
    }
    @Test
    public void isUp683(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2560));
    }
    @Test
    public void isUp684(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2564));
    }
    @Test
    public void isUp685(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2571));
    }
    @Test
    public void isUp686(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2574));
    }
    @Test
    public void isUp687(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2577));
    }
    @Test
    public void isUp688(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2578));
    }
    @Test
    public void isUp689(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2601));
    }
    @Test
    public void isUp690(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2609));
    }
    @Test
    public void isUp691(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2612));
    }
    @Test
    public void isUp692(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2615));
    }
    @Test
    public void isUp693(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2618));
    }
    @Test
    public void isUp694(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2619));
    }
    @Test
    public void isUp695(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2621));
    }
    @Test
    public void isUp696(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2627));
    }
    @Test
    public void isUp697(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2630));
    }
    @Test
    public void isUp698(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2633));
    }
    @Test
    public void isUp699(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2634));
    }
    @Test
    public void isUp700(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2638));
    }
    @Test
    public void isUp701(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2640));
    }
    @Test
    public void isUp702(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2642));
    }
    @Test
    public void isUp703(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2648));
    }
    @Test
    public void isUp704(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2653));
    }
    @Test
    public void isUp705(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2655));
    }
    @Test
    public void isUp706(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2661));
    }
    @Test
    public void isUp707(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2678));
    }
    @Test
    public void isUp708(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2688));
    }
    @Test
    public void isUp709(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2692));
    }
    @Test
    public void isUp710(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2702));
    }
    @Test
    public void isUp711(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2706));
    }
    @Test
    public void isUp712(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2729));
    }
    @Test
    public void isUp713(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2737));
    }
    @Test
    public void isUp714(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2740));
    }
    @Test
    public void isUp715(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2746));
    }
    @Test
    public void isUp716(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2747));
    }
    @Test
    public void isUp717(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2758));
    }
    @Test
    public void isUp718(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2762));
    }
    @Test
    public void isUp719(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2766));
    }
    @Test
    public void isUp720(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2767));
    }
    @Test
    public void isUp721(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2769));
    }
    @Test
    public void isUp722(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2783));
    }
    @Test
    public void isUp723(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2788));
    }
    @Test
    public void isUp724(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2789));
    }
    @Test
    public void isUp725(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2802));
    }
    @Test
    public void isUp726(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2816));
    }
    @Test
    public void isUp727(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2820));
    }
    @Test
    public void isUp728(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2829));
    }
    @Test
    public void isUp729(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2830));
    }
    @Test
    public void isUp730(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2833));
    }
    @Test
    public void isUp731(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2834));
    }
    @Test
    public void isUp732(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2857));
    }
    @Test
    public void isUp733(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2865));
    }
    @Test
    public void isUp734(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2868));
    }
    @Test
    public void isUp735(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2874));
    }
    @Test
    public void isUp736(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2875));
    }
    @Test
    public void isUp737(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2885));
    }
    @Test
    public void isUp738(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2886));
    }
    @Test
    public void isUp739(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2889));
    }
    @Test
    public void isUp740(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2890));
    }
    @Test
    public void isUp741(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2894));
    }
    @Test
    public void isUp742(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2901));
    }
    @Test
    public void isUp743(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2904));
    }
    @Test
    public void isUp744(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2907));
    }
    @Test
    public void isUp745(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2910));
    }
    @Test
    public void isUp746(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2916));
    }
    @Test
    public void isUp747(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2917));
    }
    @Test
    public void isUp748(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2936));
    }
    @Test
    public void isUp749(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2945));
    }
    @Test
    public void isUp750(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2948));
    }
    @Test
    public void isUp751(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2955));
    }
    @Test
    public void isUp752(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2957));
    }
    @Test
    public void isUp753(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2961));
    }
    @Test
    public void isUp754(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2966));
    }
    @Test
    public void isUp755(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2968));
    }
    @Test
    public void isUp756(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2971));
    }
    @Test
    public void isUp757(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2973));
    }
    @Test
    public void isUp758(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2976));
    }
    @Test
    public void isUp759(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2978));
    }
    @Test
    public void isUp760(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2981));
    }
    @Test
    public void isUp761(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2983));
    }
    @Test
    public void isUp762(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2987));
    }
    @Test
    public void isUp763(){
        assertTrue(!StringExpUtil.isUpperCase((char) 2989));
    }
    @Test
    public void isUp764(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3002));
    }
    @Test
    public void isUp765(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3005));
    }
    @Test
    public void isUp766(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3011));
    }
    @Test
    public void isUp767(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3013));
    }
    @Test
    public void isUp768(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3017));
    }
    @Test
    public void isUp769(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3022));
    }
    @Test
    public void isUp770(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3023));
    }
    @Test
    public void isUp771(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3025));
    }
    @Test
    public void isUp772(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3030));
    }
    @Test
    public void isUp773(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3032));
    }
    @Test
    public void isUp774(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3045));
    }
    @Test
    public void isUp775(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3067));
    }
    @Test
    public void isUp776(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3072));
    }
    @Test
    public void isUp777(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3076));
    }
    @Test
    public void isUp778(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3085));
    }
    @Test
    public void isUp779(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3089));
    }
    @Test
    public void isUp780(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3113));
    }
    @Test
    public void isUp781(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3124));
    }
    @Test
    public void isUp782(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3130));
    }
    @Test
    public void isUp783(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3132));
    }
    @Test
    public void isUp784(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3141));
    }
    @Test
    public void isUp785(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3145));
    }
    @Test
    public void isUp786(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3150));
    }
    @Test
    public void isUp787(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3156));
    }
    @Test
    public void isUp788(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3159));
    }
    @Test
    public void isUp789(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3162));
    }
    @Test
    public void isUp790(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3167));
    }
    @Test
    public void isUp791(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3172));
    }
    @Test
    public void isUp792(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3173));
    }
    @Test
    public void isUp793(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3184));
    }
    @Test
    public void isUp794(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3191));
    }
    @Test
    public void isUp795(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3200));
    }
    @Test
    public void isUp796(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3201));
    }
    @Test
    public void isUp797(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3204));
    }
    @Test
    public void isUp798(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3213));
    }
    @Test
    public void isUp799(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3217));
    }
    @Test
    public void isUp800(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3241));
    }
    @Test
    public void isUp801(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3252));
    }
    @Test
    public void isUp802(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3258));
    }
    @Test
    public void isUp803(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3259));
    }
    @Test
    public void isUp804(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3269));
    }
    @Test
    public void isUp805(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3273));
    }
    @Test
    public void isUp806(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3278));
    }
    @Test
    public void isUp807(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3284));
    }
    @Test
    public void isUp808(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3287));
    }
    @Test
    public void isUp809(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3293));
    }
    @Test
    public void isUp810(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3295));
    }
    @Test
    public void isUp811(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3300));
    }
    @Test
    public void isUp812(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3301));
    }
    @Test
    public void isUp813(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3312));
    }
    @Test
    public void isUp814(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3315));
    }
    @Test
    public void isUp815(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3329));
    }
    @Test
    public void isUp816(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3332));
    }
    @Test
    public void isUp817(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3341));
    }
    @Test
    public void isUp818(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3345));
    }
    @Test
    public void isUp819(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3387));
    }
    @Test
    public void isUp820(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3388));
    }
    @Test
    public void isUp821(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3397));
    }
    @Test
    public void isUp822(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3401));
    }
    @Test
    public void isUp823(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3407));
    }
    @Test
    public void isUp824(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3414));
    }
    @Test
    public void isUp825(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3416));
    }
    @Test
    public void isUp826(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3423));
    }
    @Test
    public void isUp827(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3428));
    }
    @Test
    public void isUp828(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3429));
    }
    @Test
    public void isUp829(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3446));
    }
    @Test
    public void isUp830(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3448));
    }
    @Test
    public void isUp831(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3456));
    }
    @Test
    public void isUp832(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3457));
    }
    @Test
    public void isUp833(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3460));
    }
    @Test
    public void isUp834(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3479));
    }
    @Test
    public void isUp835(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3481));
    }
    @Test
    public void isUp836(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3506));
    }
    @Test
    public void isUp837(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3516));
    }
    @Test
    public void isUp838(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3518));
    }
    @Test
    public void isUp839(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3519));
    }
    @Test
    public void isUp840(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3527));
    }
    @Test
    public void isUp841(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3529));
    }
    @Test
    public void isUp842(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3531));
    }
    @Test
    public void isUp843(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3534));
    }
    @Test
    public void isUp844(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3541));
    }
    @Test
    public void isUp845(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3543));
    }
    @Test
    public void isUp846(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3552));
    }
    @Test
    public void isUp847(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3569));
    }
    @Test
    public void isUp848(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3573));
    }
    @Test
    public void isUp849(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3584));
    }
    @Test
    public void isUp850(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3643));
    }
    @Test
    public void isUp851(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3646));
    }
    @Test
    public void isUp852(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3676));
    }
    @Test
    public void isUp853(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3712));
    }
    @Test
    public void isUp854(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3715));
    }
    @Test
    public void isUp855(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3717));
    }
    @Test
    public void isUp856(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3718));
    }
    @Test
    public void isUp857(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3721));
    }
    @Test
    public void isUp858(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3723));
    }
    @Test
    public void isUp859(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3724));
    }
    @Test
    public void isUp860(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3726));
    }
    @Test
    public void isUp861(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3731));
    }
    @Test
    public void isUp862(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3736));
    }
    @Test
    public void isUp863(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3744));
    }
    @Test
    public void isUp864(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3748));
    }
    @Test
    public void isUp865(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3750));
    }
    @Test
    public void isUp866(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3752));
    }
    @Test
    public void isUp867(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3753));
    }
    @Test
    public void isUp868(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3756));
    }
    @Test
    public void isUp869(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3770));
    }
    @Test
    public void isUp870(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3774));
    }
    @Test
    public void isUp871(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3775));
    }
    @Test
    public void isUp872(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3781));
    }
    @Test
    public void isUp873(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3783));
    }
    @Test
    public void isUp874(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3790));
    }
    @Test
    public void isUp875(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3791));
    }
    @Test
    public void isUp876(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3802));
    }
    @Test
    public void isUp877(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3803));
    }
    @Test
    public void isUp878(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3808));
    }
    @Test
    public void isUp879(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3839));
    }
    @Test
    public void isUp880(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3912));
    }
    @Test
    public void isUp881(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3949));
    }
    @Test
    public void isUp882(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3952));
    }
    @Test
    public void isUp883(){
        assertTrue(!StringExpUtil.isUpperCase((char) 3992));
    }
    @Test
    public void isUp884(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4029));
    }
    @Test
    public void isUp885(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4045));
    }
    @Test
    public void isUp886(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4059));
    }
    @Test
    public void isUp887(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4095));
    }
    @Test
    public void isUp888(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4294));
    }
    @Test
    public void isUp889(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4296));
    }
    @Test
    public void isUp890(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4300));
    }
    @Test
    public void isUp891(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4302));
    }
    @Test
    public void isUp892(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4303));
    }
    @Test
    public void isUp893(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4681));
    }
    @Test
    public void isUp894(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4686));
    }
    @Test
    public void isUp895(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4687));
    }
    @Test
    public void isUp896(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4695));
    }
    @Test
    public void isUp897(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4697));
    }
    @Test
    public void isUp898(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4702));
    }
    @Test
    public void isUp899(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4703));
    }
    @Test
    public void isUp900(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4745));
    }
    @Test
    public void isUp901(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4750));
    }
    @Test
    public void isUp902(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4751));
    }
    @Test
    public void isUp903(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4785));
    }
    @Test
    public void isUp904(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4790));
    }
    @Test
    public void isUp905(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4791));
    }
    @Test
    public void isUp906(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4799));
    }
    @Test
    public void isUp907(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4801));
    }
    @Test
    public void isUp908(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4806));
    }
    @Test
    public void isUp909(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4807));
    }
    @Test
    public void isUp910(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4823));
    }
    @Test
    public void isUp911(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4881));
    }
    @Test
    public void isUp912(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4886));
    }
    @Test
    public void isUp913(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4887));
    }
    @Test
    public void isUp914(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4955));
    }
    @Test
    public void isUp915(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4956));
    }
    @Test
    public void isUp916(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4989));
    }
    @Test
    public void isUp917(){
        assertTrue(!StringExpUtil.isUpperCase((char) 4991));
    }
    @Test
    public void isUp918(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5018));
    }
    @Test
    public void isUp919(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5023));
    }
    @Test
    public void isUp920(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5109));
    }
    @Test
    public void isUp921(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5119));
    }
    @Test
    public void isUp922(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5789));
    }
    @Test
    public void isUp923(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5791));
    }
    @Test
    public void isUp924(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5873));
    }
    @Test
    public void isUp925(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5887));
    }
    @Test
    public void isUp926(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5901));
    }
    @Test
    public void isUp927(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5909));
    }
    @Test
    public void isUp928(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5919));
    }
    @Test
    public void isUp929(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5943));
    }
    @Test
    public void isUp930(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5951));
    }
    @Test
    public void isUp931(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5972));
    }
    @Test
    public void isUp932(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5983));
    }
    @Test
    public void isUp933(){
        assertTrue(!StringExpUtil.isUpperCase((char) 5997));
    }
    @Test
    public void isUp934(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6001));
    }
    @Test
    public void isUp935(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6004));
    }
    @Test
    public void isUp936(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6015));
    }
    @Test
    public void isUp937(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6110));
    }
    @Test
    public void isUp938(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6111));
    }
    @Test
    public void isUp939(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6122));
    }
    @Test
    public void isUp940(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6127));
    }
    @Test
    public void isUp941(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6138));
    }
    @Test
    public void isUp942(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6143));
    }
    @Test
    public void isUp943(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6159));
    }
    @Test
    public void isUp944(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6170));
    }
    @Test
    public void isUp945(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6175));
    }
    @Test
    public void isUp946(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6264));
    }
    @Test
    public void isUp947(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6271));
    }
    @Test
    public void isUp948(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6315));
    }
    @Test
    public void isUp949(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6319));
    }
    @Test
    public void isUp950(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6390));
    }
    @Test
    public void isUp951(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6399));
    }
    @Test
    public void isUp952(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6429));
    }
    @Test
    public void isUp953(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6431));
    }
    @Test
    public void isUp954(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6444));
    }
    @Test
    public void isUp955(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6447));
    }
    @Test
    public void isUp956(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6460));
    }
    @Test
    public void isUp957(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6463));
    }
    @Test
    public void isUp958(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6465));
    }
    @Test
    public void isUp959(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6467));
    }
    @Test
    public void isUp960(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6510));
    }
    @Test
    public void isUp961(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6511));
    }
    @Test
    public void isUp962(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6517));
    }
    @Test
    public void isUp963(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6527));
    }
    @Test
    public void isUp964(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6572));
    }
    @Test
    public void isUp965(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6575));
    }
    @Test
    public void isUp966(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6602));
    }
    @Test
    public void isUp967(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6607));
    }
    @Test
    public void isUp968(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6619));
    }
    @Test
    public void isUp969(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6621));
    }
    @Test
    public void isUp970(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6684));
    }
    @Test
    public void isUp971(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6685));
    }
    @Test
    public void isUp972(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6751));
    }
    @Test
    public void isUp973(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6781));
    }
    @Test
    public void isUp974(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6782));
    }
    @Test
    public void isUp975(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6794));
    }
    @Test
    public void isUp976(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6799));
    }
    @Test
    public void isUp977(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6810));
    }
    @Test
    public void isUp978(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6815));
    }
    @Test
    public void isUp979(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6830));
    }
    @Test
    public void isUp980(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6911));
    }
    @Test
    public void isUp981(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6988));
    }
    @Test
    public void isUp982(){
        assertTrue(!StringExpUtil.isUpperCase((char) 6991));
    }
    @Test
    public void isUp983(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7037));
    }
    @Test
    public void isUp984(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7039));
    }
    @Test
    public void isUp985(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7156));
    }
    @Test
    public void isUp986(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7163));
    }
    @Test
    public void isUp987(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7224));
    }
    @Test
    public void isUp988(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7226));
    }
    @Test
    public void isUp989(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7242));
    }
    @Test
    public void isUp990(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7244));
    }
    @Test
    public void isUp991(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7296));
    }
    @Test
    public void isUp992(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7359));
    }
    @Test
    public void isUp993(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7368));
    }
    @Test
    public void isUp994(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7375));
    }
    @Test
    public void isUp995(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7415));
    }
    @Test
    public void isUp996(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7423));
    }
    @Test
    public void isUp997(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7655));
    }
    @Test
    public void isUp998(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7675));
    }
    @Test
    public void isUp999(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7958));
    }
    @Test
    public void isUp1000(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7959));
    }
    @Test
    public void isUp1001(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7966));
    }
    @Test
    public void isUp1002(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7967));
    }
    @Test
    public void isUp1003(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8006));
    }
    @Test
    public void isUp1004(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8007));
    }
    @Test
    public void isUp1005(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8014));
    }
    @Test
    public void isUp1006(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8015));
    }
    @Test
    public void isUp1007(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8024));
    }
    @Test
    public void isUp1008(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8026));
    }
    @Test
    public void isUp1009(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8028));
    }
    @Test
    public void isUp1010(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8030));
    }
    @Test
    public void isUp1011(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8062));
    }
    @Test
    public void isUp1012(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8063));
    }
    @Test
    public void isUp1013(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8117));
    }
    @Test
    public void isUp1014(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8133));
    }
    @Test
    public void isUp1015(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8148));
    }
    @Test
    public void isUp1016(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8149));
    }
    @Test
    public void isUp1017(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8156));
    }
    @Test
    public void isUp1018(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8176));
    }
    @Test
    public void isUp1019(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8177));
    }
    @Test
    public void isUp1020(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8181));
    }
    @Test
    public void isUp1021(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8191));
    }
    @Test
    public void isUp1022(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8293));
    }
    @Test
    public void isUp1023(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8297));
    }
    @Test
    public void isUp1024(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8306));
    }
    @Test
    public void isUp1025(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8307));
    }
    @Test
    public void isUp1026(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8335));
    }
    @Test
    public void isUp1027(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8349));
    }
    @Test
    public void isUp1028(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8351));
    }
    @Test
    public void isUp1029(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8379));
    }
    @Test
    public void isUp1030(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8399));
    }
    @Test
    public void isUp1031(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8433));
    }
    @Test
    public void isUp1032(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8447));
    }
    @Test
    public void isUp1033(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8586));
    }
    @Test
    public void isUp1034(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8591));
    }
    @Test
    public void isUp1035(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9204));
    }
    @Test
    public void isUp1036(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9215));
    }
    @Test
    public void isUp1037(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9255));
    }
    @Test
    public void isUp1038(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9279));
    }
    @Test
    public void isUp1039(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9291));
    }
    @Test
    public void isUp1040(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9311));
    }
    @Test
    public void isUp1041(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9984));
    }
    @Test
    public void isUp1042(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11085));
    }
    @Test
    public void isUp1043(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11087));
    }
    @Test
    public void isUp1044(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11098));
    }
    @Test
    public void isUp1045(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11263));
    }
    @Test
    public void isUp1046(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11311));
    }
    @Test
    public void isUp1047(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11359));
    }
    @Test
    public void isUp1048(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11508));
    }
    @Test
    public void isUp1049(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11512));
    }
    @Test
    public void isUp1050(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11558));
    }
    @Test
    public void isUp1051(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11560));
    }
    @Test
    public void isUp1052(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11564));
    }
    @Test
    public void isUp1053(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11566));
    }
    @Test
    public void isUp1054(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11567));
    }
    @Test
    public void isUp1055(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11624));
    }
    @Test
    public void isUp1056(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11630));
    }
    @Test
    public void isUp1057(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11633));
    }
    @Test
    public void isUp1058(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11646));
    }
    @Test
    public void isUp1059(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11671));
    }
    @Test
    public void isUp1060(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11679));
    }
    @Test
    public void isUp1061(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11687));
    }
    @Test
    public void isUp1062(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11695));
    }
    @Test
    public void isUp1063(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11703));
    }
    @Test
    public void isUp1064(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11711));
    }
    @Test
    public void isUp1065(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11719));
    }
    @Test
    public void isUp1066(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11727));
    }
    @Test
    public void isUp1067(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11735));
    }
    @Test
    public void isUp1068(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11743));
    }
    @Test
    public void isUp1069(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11836));
    }
    @Test
    public void isUp1070(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11903));
    }
    @Test
    public void isUp1071(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11930));
    }
    @Test
    public void isUp1072(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12020));
    }
    @Test
    public void isUp1073(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12031));
    }
    @Test
    public void isUp1074(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12246));
    }
    @Test
    public void isUp1075(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12271));
    }
    @Test
    public void isUp1076(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12284));
    }
    @Test
    public void isUp1077(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12287));
    }
    @Test
    public void isUp1078(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12352));
    }
    @Test
    public void isUp1079(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12439));
    }
    @Test
    public void isUp1080(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12440));
    }
    @Test
    public void isUp1081(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12544));
    }
    @Test
    public void isUp1082(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12548));
    }
    @Test
    public void isUp1083(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12590));
    }
    @Test
    public void isUp1084(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12592));
    }
    @Test
    public void isUp1085(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12687));
    }
    @Test
    public void isUp1086(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12731));
    }
    @Test
    public void isUp1087(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12735));
    }
    @Test
    public void isUp1088(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12772));
    }
    @Test
    public void isUp1089(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12783));
    }
    @Test
    public void isUp1090(){
        assertTrue(!StringExpUtil.isUpperCase((char) 12831));
    }
    @Test
    public void isUp1091(){
        assertTrue(!StringExpUtil.isUpperCase((char) 13055));
    }
    @Test
    public void isUp1092(){
        assertTrue(!StringExpUtil.isUpperCase((char) 19894));
    }
    @Test
    public void isUp1093(){
        assertTrue(!StringExpUtil.isUpperCase((char) 19903));
    }
    @Test
    public void isUp1094(){
        assertTrue(!StringExpUtil.isUpperCase((char) 40909));
    }
    @Test
    public void isUp1095(){
        assertTrue(!StringExpUtil.isUpperCase((char) 40959));
    }
    @Test
    public void isUp1096(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42125));
    }
    @Test
    public void isUp1097(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42127));
    }
    @Test
    public void isUp1098(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42183));
    }
    @Test
    public void isUp1099(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42191));
    }
    @Test
    public void isUp1100(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42540));
    }
    @Test
    public void isUp1101(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42559));
    }
    @Test
    public void isUp1102(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42648));
    }
    @Test
    public void isUp1103(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42654));
    }
    @Test
    public void isUp1104(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42744));
    }
    @Test
    public void isUp1105(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42751));
    }
    @Test
    public void isUp1106(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42895));
    }
    @Test
    public void isUp1107(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42900));
    }
    @Test
    public void isUp1108(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42911));
    }
    @Test
    public void isUp1109(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42923));
    }
    @Test
    public void isUp1110(){
        assertTrue(!StringExpUtil.isUpperCase((char) 42999));
    }
    @Test
    public void isUp1111(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43052));
    }
    @Test
    public void isUp1112(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43055));
    }
    @Test
    public void isUp1113(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43066));
    }
    @Test
    public void isUp1114(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43071));
    }
    @Test
    public void isUp1115(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43128));
    }
    @Test
    public void isUp1116(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43135));
    }
    @Test
    public void isUp1117(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43205));
    }
    @Test
    public void isUp1118(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43213));
    }
    @Test
    public void isUp1119(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43226));
    }
    @Test
    public void isUp1120(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43231));
    }
    @Test
    public void isUp1121(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43260));
    }
    @Test
    public void isUp1122(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43263));
    }
    @Test
    public void isUp1123(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43348));
    }
    @Test
    public void isUp1124(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43358));
    }
    @Test
    public void isUp1125(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43389));
    }
    @Test
    public void isUp1126(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43391));
    }
    @Test
    public void isUp1127(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43470));
    }
    @Test
    public void isUp1128(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43482));
    }
    @Test
    public void isUp1129(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43485));
    }
    @Test
    public void isUp1130(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43488));
    }
    @Test
    public void isUp1131(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43519));
    }
    @Test
    public void isUp1132(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43575));
    }
    @Test
    public void isUp1133(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43583));
    }
    @Test
    public void isUp1134(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43598));
    }
    @Test
    public void isUp1135(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43599));
    }
    @Test
    public void isUp1136(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43610));
    }
    @Test
    public void isUp1137(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43611));
    }
    @Test
    public void isUp1138(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43644));
    }
    @Test
    public void isUp1139(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43647));
    }
    @Test
    public void isUp1140(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43715));
    }
    @Test
    public void isUp1141(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43738));
    }
    @Test
    public void isUp1142(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43767));
    }
    @Test
    public void isUp1143(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43776));
    }
    @Test
    public void isUp1144(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43783));
    }
    @Test
    public void isUp1145(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43784));
    }
    @Test
    public void isUp1146(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43791));
    }
    @Test
    public void isUp1147(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43792));
    }
    @Test
    public void isUp1148(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43799));
    }
    @Test
    public void isUp1149(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43807));
    }
    @Test
    public void isUp1150(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43815));
    }
    @Test
    public void isUp1151(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43823));
    }
    @Test
    public void isUp1152(){
        assertTrue(!StringExpUtil.isUpperCase((char) 43967));
    }
    @Test
    public void isUp1153(){
        assertTrue(!StringExpUtil.isUpperCase((char) 44014));
    }
    @Test
    public void isUp1154(){
        assertTrue(!StringExpUtil.isUpperCase((char) 44015));
    }
    @Test
    public void isUp1155(){
        assertTrue(!StringExpUtil.isUpperCase((char) 44026));
    }
    @Test
    public void isUp1156(){
        assertTrue(!StringExpUtil.isUpperCase((char) 44031));
    }
    @Test
    public void isUp1157(){
        assertTrue(!StringExpUtil.isUpperCase((char) 55204));
    }
    @Test
    public void isUp1158(){
        assertTrue(!StringExpUtil.isUpperCase((char) 55215));
    }
    @Test
    public void isUp1159(){
        assertTrue(!StringExpUtil.isUpperCase((char) 55239));
    }
    @Test
    public void isUp1160(){
        assertTrue(!StringExpUtil.isUpperCase((char) 55242));
    }
    @Test
    public void isUp1161(){
        assertTrue(!StringExpUtil.isUpperCase((char) 55292));
    }
    @Test
    public void isUp1162(){
        assertTrue(!StringExpUtil.isUpperCase((char) 55295));
    }
    @Test
    public void isUp1163(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64110));
    }
    @Test
    public void isUp1164(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64111));
    }
    @Test
    public void isUp1165(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64218));
    }
    @Test
    public void isUp1166(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64255));
    }
    @Test
    public void isUp1167(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64263));
    }
    @Test
    public void isUp1168(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64274));
    }
    @Test
    public void isUp1169(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64280));
    }
    @Test
    public void isUp1170(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64284));
    }
    @Test
    public void isUp1171(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64311));
    }
    @Test
    public void isUp1172(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64317));
    }
    @Test
    public void isUp1173(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64319));
    }
    @Test
    public void isUp1174(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64322));
    }
    @Test
    public void isUp1175(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64325));
    }
    @Test
    public void isUp1176(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64450));
    }
    @Test
    public void isUp1177(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64466));
    }
    @Test
    public void isUp1178(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64832));
    }
    @Test
    public void isUp1179(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64847));
    }
    @Test
    public void isUp1180(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64912));
    }
    @Test
    public void isUp1181(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64913));
    }
    @Test
    public void isUp1182(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64968));
    }
    @Test
    public void isUp1183(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65007));
    }
    @Test
    public void isUp1184(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65022));
    }
    @Test
    public void isUp1185(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65023));
    }
    @Test
    public void isUp1186(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65050));
    }
    @Test
    public void isUp1187(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65055));
    }
    @Test
    public void isUp1188(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65063));
    }
    @Test
    public void isUp1189(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65071));
    }
    @Test
    public void isUp1190(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65107));
    }
    @Test
    public void isUp1191(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65127));
    }
    @Test
    public void isUp1192(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65132));
    }
    @Test
    public void isUp1193(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65135));
    }
    @Test
    public void isUp1194(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65141));
    }
    @Test
    public void isUp1195(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65277));
    }
    @Test
    public void isUp1196(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65278));
    }
    @Test
    public void isUp1197(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65280));
    }
    @Test
    public void isUp1198(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65471));
    }
    @Test
    public void isUp1199(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65473));
    }
    @Test
    public void isUp1200(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65480));
    }
    @Test
    public void isUp1201(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65481));
    }
    @Test
    public void isUp1202(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65488));
    }
    @Test
    public void isUp1203(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65489));
    }
    @Test
    public void isUp1204(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65496));
    }
    @Test
    public void isUp1205(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65497));
    }
    @Test
    public void isUp1206(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65501));
    }
    @Test
    public void isUp1207(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65503));
    }
    @Test
    public void isUp1208(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65511));
    }
    @Test
    public void isUp1209(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65519));
    }
    @Test
    public void isUp1210(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65528));
    }
    @Test
    public void isUp1211(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65534));
    }
    @Test
    public void isUp1212(){
        assertTrue(StringExpUtil.isUpperCase((char) 1024));
    }
    @Test
    public void isUp1213(){
        assertTrue(StringExpUtil.isUpperCase((char) 8170));
    }
    @Test
    public void isUp1214(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65339));
    }
    @Test
    public void isUp1215(){
        assertTrue(StringExpUtil.isUpperCase((char) 11374));
    }
    @Test
    public void isUp1216(){
        assertTrue(StringExpUtil.isUpperCase((char) 11375));
    }
    @Test
    public void isUp1217(){
        assertTrue(StringExpUtil.isUpperCase((char) 11363));
    }
    @Test
    public void isUp1218(){
        assertTrue(StringExpUtil.isUpperCase((char) 580));
    }
    @Test
    public void isUp1219(){
        assertTrue(StringExpUtil.isUpperCase((char) 581));
    }
    @Test
    public void isUp1220(){
        assertTrue(StringExpUtil.isUpperCase((char) 503));
    }
    @Test
    public void isUp1221(){
        assertTrue(StringExpUtil.isUpperCase((char) 407));
    }
    @Test
    public void isUp1222(){
        assertTrue(StringExpUtil.isUpperCase((char) 399));
    }
    @Test
    public void isUp1223(){
        assertTrue(StringExpUtil.isUpperCase((char) 400));
    }
    @Test
    public void isUp1224(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1014));
    }
    @Test
    public void isUp1225(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1542));
    }
    @Test
    public void isUp1226(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1544));
    }
    @Test
    public void isUp1227(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8260));
    }
    @Test
    public void isUp1228(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8274));
    }
    @Test
    public void isUp1229(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8314));
    }
    @Test
    public void isUp1230(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8316));
    }
    @Test
    public void isUp1231(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8330));
    }
    @Test
    public void isUp1232(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8332));
    }
    @Test
    public void isUp1233(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8472));
    }
    @Test
    public void isUp1234(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8512));
    }
    @Test
    public void isUp1235(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8516));
    }
    @Test
    public void isUp1236(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8523));
    }
    @Test
    public void isUp1237(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8592));
    }
    @Test
    public void isUp1238(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8596));
    }
    @Test
    public void isUp1239(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8602));
    }
    @Test
    public void isUp1240(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8603));
    }
    @Test
    public void isUp1241(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8608));
    }
    @Test
    public void isUp1242(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8611));
    }
    @Test
    public void isUp1243(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8614));
    }
    @Test
    public void isUp1244(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8622));
    }
    @Test
    public void isUp1245(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8654));
    }
    @Test
    public void isUp1246(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8655));
    }
    @Test
    public void isUp1247(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8658));
    }
    @Test
    public void isUp1248(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8660));
    }
    @Test
    public void isUp1249(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8692));
    }
    @Test
    public void isUp1250(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8959));
    }
    @Test
    public void isUp1251(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8968));
    }
    @Test
    public void isUp1252(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8971));
    }
    @Test
    public void isUp1253(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8992));
    }
    @Test
    public void isUp1254(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8993));
    }
    @Test
    public void isUp1255(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9084));
    }
    @Test
    public void isUp1256(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9115));
    }
    @Test
    public void isUp1257(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9139));
    }
    @Test
    public void isUp1258(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9180));
    }
    @Test
    public void isUp1259(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9185));
    }
    @Test
    public void isUp1260(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9655));
    }
    @Test
    public void isUp1261(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9665));
    }
    @Test
    public void isUp1262(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9720));
    }
    @Test
    public void isUp1263(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9727));
    }
    @Test
    public void isUp1264(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9839));
    }
    @Test
    public void isUp1265(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10176));
    }
    @Test
    public void isUp1266(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10180));
    }
    @Test
    public void isUp1267(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10183));
    }
    @Test
    public void isUp1268(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10213));
    }
    @Test
    public void isUp1269(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10224));
    }
    @Test
    public void isUp1270(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10239));
    }
    @Test
    public void isUp1271(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10496));
    }
    @Test
    public void isUp1272(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10626));
    }
    @Test
    public void isUp1273(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10649));
    }
    @Test
    public void isUp1274(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10711));
    }
    @Test
    public void isUp1275(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10716));
    }
    @Test
    public void isUp1276(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10747));
    }
    @Test
    public void isUp1277(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10750));
    }
    @Test
    public void isUp1278(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11007));
    }
    @Test
    public void isUp1279(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11056));
    }
    @Test
    public void isUp1280(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11076));
    }
    @Test
    public void isUp1281(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11079));
    }
    @Test
    public void isUp1282(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11084));
    }
    @Test
    public void isUp1283(){
        assertTrue(!StringExpUtil.isUpperCase((char) 64297));
    }
    @Test
    public void isUp1284(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65122));
    }
    @Test
    public void isUp1285(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65124));
    }
    @Test
    public void isUp1286(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65126));
    }
    @Test
    public void isUp1287(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65291));
    }
    @Test
    public void isUp1288(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65308));
    }
    @Test
    public void isUp1289(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65310));
    }
    @Test
    public void isUp1290(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65372));
    }
    @Test
    public void isUp1291(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65374));
    }
    @Test
    public void isUp1292(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65506));
    }
    @Test
    public void isUp1293(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65513));
    }
    @Test
    public void isUp1294(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65516));
    }
    @Test
    public void isUp1295(){
        assertTrue(!StringExpUtil.isUpperCase((char) 65517));
    }
    @Test
    public void isUp1296(){
        assertTrue(!StringExpUtil.isUpperCase((char) 11008));
    }
    @Test
    public void isUp1297(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10712));
    }
    @Test
    public void isUp1298(){
        assertTrue(!StringExpUtil.isUpperCase((char) 10627));
    }
    @Test
    public void isUp1299(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8960));
    }
    @Test
    public void isUp1300(){
        assertTrue(!StringExpUtil.isUpperCase((char) 247));
    }
    @Test
    public void isUp1301(){
        assertTrue(!StringExpUtil.isUpperCase((char) 215));
    }
    @Test
    public void isUp1302(){
        assertTrue(!StringExpUtil.isUpperCase((char) 177));
    }
    @Test
    public void isUp1303(){
        assertTrue(!StringExpUtil.isUpperCase((char) 172));
    }
    @Test
    public void isUp1304(){
        assertTrue(StringExpUtil.isUpperCase((char) 8544));
    }
    @Test
    public void isUp1305(){
        assertTrue(StringExpUtil.isUpperCase((char) 9398));
    }
    @Test
    public void isUp1306(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8560));
    }
    @Test
    public void isUp1307(){
        assertTrue(!StringExpUtil.isUpperCase((char) 9424));
    }
    @Test
    public void isUp1308(){
        assertTrue(StringExpUtil.isUpperCase((char) 391));
    }
    @Test
    public void isUp1309(){
        assertTrue(StringExpUtil.isUpperCase((char) 393));
    }
    @Test
    public void isUp1310(){
        assertTrue(StringExpUtil.isUpperCase((char) 395));
    }
    @Test
    public void isUp1311(){
        assertTrue(!StringExpUtil.isUpperCase((char) 397));
    }
    @Test
    public void isUp1312(){
        assertTrue(StringExpUtil.isUpperCase((char) 401));
    }
    @Test
    public void isUp1313(){
        assertTrue(StringExpUtil.isUpperCase((char) 403));
    }
    @Test
    public void isUp1314(){
        assertTrue(StringExpUtil.isUpperCase((char) 408));
    }
    @Test
    public void isUp1315(){
        assertTrue(!StringExpUtil.isUpperCase((char) 411));
    }
    @Test
    public void isUp1316(){
        assertTrue(StringExpUtil.isUpperCase((char) 423));
    }
    @Test
    public void isUp1317(){
        assertTrue(StringExpUtil.isUpperCase((char) 425));
    }
    @Test
    public void isUp1318(){
        assertTrue(StringExpUtil.isUpperCase((char) 428));
    }
    @Test
    public void isUp1319(){
        assertTrue(StringExpUtil.isUpperCase((char) 430));
    }
    @Test
    public void isUp1320(){
        assertTrue(StringExpUtil.isUpperCase((char) 431));
    }
    @Test
    public void isUp1321(){
        assertTrue(StringExpUtil.isUpperCase((char) 433));
    }
    @Test
    public void isUp1322(){
        assertTrue(StringExpUtil.isUpperCase((char) 440));
    }
    @Test
    public void isUp1323(){
        assertTrue(!StringExpUtil.isUpperCase((char) 442));
    }
    @Test
    public void isUp1324(){
        assertTrue(StringExpUtil.isUpperCase((char) 444));
    }
    @Test
    public void isUp1325(){
        assertTrue(!StringExpUtil.isUpperCase((char) 446));
    }
    @Test
    public void isUp1326(){
        assertTrue(StringExpUtil.isUpperCase((char) 452));
    }
    @Test
    public void isUp1327(){
        assertTrue(StringExpUtil.isUpperCase((char) 455));
    }
    @Test
    public void isUp1328(){
        assertTrue(StringExpUtil.isUpperCase((char) 458));
    }
    @Test
    public void isUp1329(){
        assertTrue(!StringExpUtil.isUpperCase((char) 459));
    }
    @Test
    public void isUp1330(){
        assertTrue(StringExpUtil.isUpperCase((char) 461));
    }
    @Test
    public void isUp1331(){
        assertTrue(!StringExpUtil.isUpperCase((char) 476));
    }
    @Test
    public void isUp1332(){
        assertTrue(StringExpUtil.isUpperCase((char) 478));
    }
    @Test
    public void isUp1333(){
        assertTrue(!StringExpUtil.isUpperCase((char) 498));
    }
    @Test
    public void isUp1334(){
        assertTrue(StringExpUtil.isUpperCase((char) 500));
    }
    @Test
    public void isUp1335(){
        assertTrue(!StringExpUtil.isUpperCase((char) 591));
    }
    @Test
    public void isUp1336(){
        assertTrue(!StringExpUtil.isUpperCase((char) 593));
    }
    @Test
    public void isUp1337(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1007));
    }
    @Test
    public void isUp1338(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1009));
    }
    @Test
    public void isUp1339(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1230));
    }
    @Test
    public void isUp1340(){
        assertTrue(StringExpUtil.isUpperCase((char) 1232));
    }
    @Test
    public void isUp1341(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7935));
    }
    @Test
    public void isUp1342(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7937));
    }
    @Test
    public void isUp1343(){
        assertTrue(!StringExpUtil.isUpperCase((char) 383));
    }
    @Test
    public void isUp1344(){
        assertTrue(!StringExpUtil.isUpperCase((char) 392));
    }
    @Test
    public void isUp1345(){
        assertTrue(!StringExpUtil.isUpperCase((char) 396));
    }
    @Test
    public void isUp1346(){
        assertTrue(!StringExpUtil.isUpperCase((char) 402));
    }
    @Test
    public void isUp1347(){
        assertTrue(!StringExpUtil.isUpperCase((char) 409));
    }
    @Test
    public void isUp1348(){
        assertTrue(!StringExpUtil.isUpperCase((char) 410));
    }
    @Test
    public void isUp1349(){
        assertTrue(!StringExpUtil.isUpperCase((char) 424));
    }
    @Test
    public void isUp1350(){
        assertTrue(!StringExpUtil.isUpperCase((char) 429));
    }
    @Test
    public void isUp1351(){
        assertTrue(!StringExpUtil.isUpperCase((char) 432));
    }
    @Test
    public void isUp1352(){
        assertTrue(!StringExpUtil.isUpperCase((char) 441));
    }
    @Test
    public void isUp1353(){
        assertTrue(!StringExpUtil.isUpperCase((char) 445));
    }
    @Test
    public void isUp1354(){
        assertTrue(!StringExpUtil.isUpperCase((char) 453));
    }
    @Test
    public void isUp1355(){
        assertTrue(!StringExpUtil.isUpperCase((char) 454));
    }
    @Test
    public void isUp1356(){
        assertTrue(!StringExpUtil.isUpperCase((char) 456));
    }
    @Test
    public void isUp1357(){
        assertTrue(!StringExpUtil.isUpperCase((char) 457));
    }
    @Test
    public void isUp1358(){
        assertTrue(!StringExpUtil.isUpperCase((char) 460));
    }
    @Test
    public void isUp1359(){
        assertTrue(!StringExpUtil.isUpperCase((char) 477));
    }
    @Test
    public void isUp1360(){
        assertTrue(!StringExpUtil.isUpperCase((char) 499));
    }
    @Test
    public void isUp1361(){
        assertTrue(!StringExpUtil.isUpperCase((char) 592));
    }
    @Test
    public void isUp1362(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1008));
    }
    @Test
    public void isUp1363(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1231));
    }
    @Test
    public void isUp1364(){
        assertTrue(!StringExpUtil.isUpperCase((char) 7936));
    }
    @Test
    public void isUp1365(){
        assertTrue(StringExpUtil.isUpperCase((char) 8473));
    }
    @Test
    public void isUp1366(){
        assertTrue(StringExpUtil.isUpperCase((char) 8517));
    }
    @Test
    public void isUp1367(){
        assertTrue(StringExpUtil.isUpperCase((char) 8499));
    }
    @Test
    public void isUp1368(){
        assertTrue(StringExpUtil.isUpperCase((char) 8469));
    }
    @Test
    public void isUp1369(){
        assertTrue(StringExpUtil.isUpperCase((char) 8488));
    }
    @Test
    public void isUp1370(){
        assertTrue(StringExpUtil.isUpperCase((char) 8455));
    }
    @Test
    public void isUp1371(){
        assertTrue(StringExpUtil.isUpperCase((char) 8484));
    }
    @Test
    public void isUp1372(){
        assertTrue(StringExpUtil.isUpperCase((char) 8450));
    }
    @Test
    public void isUp1373(){
        assertTrue(StringExpUtil.isUpperCase((char) 978));
    }
    @Test
    public void isUp1374(){
        assertTrue(StringExpUtil.isUpperCase((char) 8492));
    }
    @Test
    public void isUp1375(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8188));
    }
    @Test
    public void isUp1376(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8140));
    }
    @Test
    public void isUp1377(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8124));
    }
    @Test
    public void isUp1378(){
        assertTrue(!StringExpUtil.isUpperCase((char) 8072));
    }
    @Test
    public void isUp1379(){
        assertTrue(!StringExpUtil.isUpperCase((char) 1632));
    }
    @Test
    public void isUp1380(){
        assertTrue(!StringExpUtil.isUpperCase('0'));
    }
    @Test
    public void isUp1381(){
        assertTrue(!StringExpUtil.isUpperCase((char)160));
    }
    @Test
    public void isLow1(){
        assertTrue(!StringExpUtil.isLowerCase((char)8544));
    }
    @Test
    public void isLow2(){
        assertTrue(StringExpUtil.isLowerCase((char)8560));
    }
    @Test
    public void isLow3(){
        assertTrue(!StringExpUtil.isLowerCase((char)9398));
    }
    @Test
    public void isLow4(){
        assertTrue(StringExpUtil.isLowerCase((char)9424));
    }
    @Test
    public void isLow5(){
        assertTrue(!StringExpUtil.isLowerCase((char)8602));
    }
    @Test
    public void isLow6(){
        assertTrue(StringExpUtil.isLowerCase((char) 382));
    }
    @Test
    public void isLow7(){
        assertTrue(StringExpUtil.isLowerCase((char) 384));
    }
    @Test
    public void isLow8(){
        assertTrue(!StringExpUtil.isLowerCase((char) 391));
    }
    @Test
    public void isLow9(){
        assertTrue(!StringExpUtil.isLowerCase((char) 393));
    }
    @Test
    public void isLow10(){
        assertTrue(!StringExpUtil.isLowerCase((char) 395));
    }
    @Test
    public void isLow11(){
        assertTrue(StringExpUtil.isLowerCase((char) 397));
    }
    @Test
    public void isLow12(){
        assertTrue(!StringExpUtil.isLowerCase((char) 401));
    }
    @Test
    public void isLow13(){
        assertTrue(!StringExpUtil.isLowerCase((char) 403));
    }
    @Test
    public void isLow14(){
        assertTrue(!StringExpUtil.isLowerCase((char) 408));
    }
    @Test
    public void isLow15(){
        assertTrue(StringExpUtil.isLowerCase((char) 411));
    }
    @Test
    public void isLow16(){
        assertTrue(!StringExpUtil.isLowerCase((char) 423));
    }
    @Test
    public void isLow17(){
        assertTrue(!StringExpUtil.isLowerCase((char) 425));
    }
    @Test
    public void isLow18(){
        assertTrue(!StringExpUtil.isLowerCase((char) 428));
    }
    @Test
    public void isLow19(){
        assertTrue(!StringExpUtil.isLowerCase((char) 430));
    }
    @Test
    public void isLow20(){
        assertTrue(!StringExpUtil.isLowerCase((char) 431));
    }
    @Test
    public void isLow21(){
        assertTrue(!StringExpUtil.isLowerCase((char) 433));
    }
    @Test
    public void isLow22(){
        assertTrue(!StringExpUtil.isLowerCase((char) 440));
    }
    @Test
    public void isLow23(){
        assertTrue(StringExpUtil.isLowerCase((char) 442));
    }
    @Test
    public void isLow24(){
        assertTrue(!StringExpUtil.isLowerCase((char) 444));
    }
    @Test
    public void isLow25(){
        assertTrue(StringExpUtil.isLowerCase((char) 446));
    }
    @Test
    public void isLow26(){
        assertTrue(!StringExpUtil.isLowerCase((char) 453));
    }
    @Test
    public void isLow27(){
        assertTrue(!StringExpUtil.isLowerCase((char) 455));
    }
    @Test
    public void isLow28(){
        assertTrue(!StringExpUtil.isLowerCase((char) 456));
    }
    @Test
    public void isLow29(){
        assertTrue(!StringExpUtil.isLowerCase((char) 458));
    }
    @Test
    public void isLow30(){
        assertTrue(!StringExpUtil.isLowerCase((char) 459));
    }
    @Test
    public void isLow31(){
        assertTrue(!StringExpUtil.isLowerCase((char) 461));
    }
    @Test
    public void isLow32(){
        assertTrue(StringExpUtil.isLowerCase((char) 476));
    }
    @Test
    public void isLow33(){
        assertTrue(!StringExpUtil.isLowerCase((char) 478));
    }
    @Test
    public void isLow34(){
        assertTrue(!StringExpUtil.isLowerCase((char) 498));
    }
    @Test
    public void isLow35(){
        assertTrue(!StringExpUtil.isLowerCase((char) 500));
    }
    @Test
    public void isLow36(){
        assertTrue(StringExpUtil.isLowerCase((char) 591));
    }
    @Test
    public void isLow37(){
        assertTrue(StringExpUtil.isLowerCase((char) 593));
    }
    @Test
    public void isLow38(){
        assertTrue(StringExpUtil.isLowerCase((char) 1007));
    }
    @Test
    public void isLow39(){
        assertTrue(StringExpUtil.isLowerCase((char) 1009));
    }
    @Test
    public void isLow40(){
        assertTrue(StringExpUtil.isLowerCase((char) 1230));
    }
    @Test
    public void isLow41(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1232));
    }
    @Test
    public void isLow42(){
        assertTrue(StringExpUtil.isLowerCase((char) 7935));
    }
    @Test
    public void isLow43(){
        assertTrue(StringExpUtil.isLowerCase((char) 7937));
    }

    @Test
    public void isLow44(){
        assertTrue(StringExpUtil.isLowerCase((char) 383));
    }
    @Test
    public void isLow45(){
        assertTrue(StringExpUtil.isLowerCase((char) 392));
    }
    @Test
    public void isLow46(){
        assertTrue(StringExpUtil.isLowerCase((char) 396));
    }
    @Test
    public void isLow47(){
        assertTrue(StringExpUtil.isLowerCase((char) 402));
    }
    @Test
    public void isLow48(){
        assertTrue(StringExpUtil.isLowerCase((char) 409));
    }
    @Test
    public void isLow49(){
        assertTrue(StringExpUtil.isLowerCase((char) 410));
    }
    @Test
    public void isLow50(){
        assertTrue(StringExpUtil.isLowerCase((char) 424));
    }
    @Test
    public void isLow51(){
        assertTrue(StringExpUtil.isLowerCase((char) 429));
    }
    @Test
    public void isLow52(){
        assertTrue(StringExpUtil.isLowerCase((char) 432));
    }
    @Test
    public void isLow53(){
        assertTrue(StringExpUtil.isLowerCase((char) 441));
    }
    @Test
    public void isLow54(){
        assertTrue(StringExpUtil.isLowerCase((char) 445));
    }
    @Test
    public void isLow55(){
        assertTrue(StringExpUtil.isLowerCase((char) 454));
    }
    @Test
    public void isLow56(){
        assertTrue(StringExpUtil.isLowerCase((char) 457));
    }
    @Test
    public void isLow57(){
        assertTrue(StringExpUtil.isLowerCase((char) 460));
    }
    @Test
    public void isLow58(){
        assertTrue(StringExpUtil.isLowerCase((char) 477));
    }
    @Test
    public void isLow59(){
        assertTrue(StringExpUtil.isLowerCase((char) 499));
    }
    @Test
    public void isLow60(){
        assertTrue(StringExpUtil.isLowerCase((char) 592));
    }
    @Test
    public void isLow61(){
        assertTrue(StringExpUtil.isLowerCase((char) 1008));
    }
    @Test
    public void isLow62(){
        assertTrue(StringExpUtil.isLowerCase((char) 1231));
    }
    @Test
    public void isLow63(){
        assertTrue(StringExpUtil.isLowerCase((char) 7936));
    }

    @Test
    public void isLow64(){
        assertTrue(!StringExpUtil.isLowerCase((char) 48));
    }
    @Test
    public void isLow65(){
        assertTrue(!StringExpUtil.isLowerCase((char) 57));
    }
    @Test
    public void isLow66(){
        assertTrue(!StringExpUtil.isLowerCase((char) 127));
    }
    @Test
    public void isLow67(){
        assertTrue(!StringExpUtil.isLowerCase((char) 160));
    }
    @Test
    public void isLow68(){
        assertTrue(StringExpUtil.isLowerCase((char) 170));
    }
    @Test
    public void isLow69(){
        assertTrue(StringExpUtil.isLowerCase((char) 186));
    }
    @Test
    public void isLow70(){
        assertTrue(StringExpUtil.isLowerCase((char) 223));
    }
    @Test
    public void isLow71(){
        assertTrue(StringExpUtil.isLowerCase((char) 312));
    }
    @Test
    public void isLow72(){
        assertTrue(StringExpUtil.isLowerCase((char) 329));
    }
    @Test
    public void isLow73(){
        assertTrue(StringExpUtil.isLowerCase((char) 397));
    }
    @Test
    public void isLow74(){
        assertTrue(StringExpUtil.isLowerCase((char) 411));
    }
    @Test
    public void isLow75(){
        assertTrue(StringExpUtil.isLowerCase((char) 426));
    }
    @Test
    public void isLow76(){
        assertTrue(StringExpUtil.isLowerCase((char) 427));
    }
    @Test
    public void isLow77(){
        assertTrue(StringExpUtil.isLowerCase((char) 442));
    }
    @Test
    public void isLow78(){
        assertTrue(StringExpUtil.isLowerCase((char) 446));
    }
    @Test
    public void isLow79(){
        assertTrue(!StringExpUtil.isLowerCase((char) 453));
    }
    @Test
    public void isLow80(){
        assertTrue(!StringExpUtil.isLowerCase((char) 456));
    }
    @Test
    public void isLow81(){
        assertTrue(!StringExpUtil.isLowerCase((char) 459));
    }
    @Test
    public void isLow82(){
        assertTrue(StringExpUtil.isLowerCase((char) 496));
    }
    @Test
    public void isLow83(){
        assertTrue(!StringExpUtil.isLowerCase((char) 498));
    }
    @Test
    public void isLow84(){
        assertTrue(StringExpUtil.isLowerCase((char) 545));
    }
    @Test
    public void isLow85(){
        assertTrue(StringExpUtil.isLowerCase((char) 564));
    }
    @Test
    public void isLow86(){
        assertTrue(StringExpUtil.isLowerCase((char) 569));
    }
    @Test
    public void isLow87(){
        assertTrue(StringExpUtil.isLowerCase((char) 597));
    }
    @Test
    public void isLow88(){
        assertTrue(StringExpUtil.isLowerCase((char) 600));
    }
    @Test
    public void isLow89(){
        assertTrue(StringExpUtil.isLowerCase((char) 602));
    }
    @Test
    public void isLow90(){
        assertTrue(StringExpUtil.isLowerCase((char) 604));
    }
    @Test
    public void isLow91(){
        assertTrue(StringExpUtil.isLowerCase((char) 607));
    }
    @Test
    public void isLow92(){
        assertTrue(StringExpUtil.isLowerCase((char) 609));
    }
    @Test
    public void isLow93(){
        assertTrue(StringExpUtil.isLowerCase((char) 610));
    }
    @Test
    public void isLow94(){
        assertTrue(StringExpUtil.isLowerCase((char) 612));
    }
    @Test
    public void isLow95(){
        assertTrue(StringExpUtil.isLowerCase((char) 624));
    }
    @Test
    public void isLow96(){
        assertTrue(StringExpUtil.isLowerCase((char) 627));
    }
    @Test
    public void isLow97(){
        assertTrue(StringExpUtil.isLowerCase((char) 628));
    }
    @Test
    public void isLow98(){
        assertTrue(StringExpUtil.isLowerCase((char) 630));
    }
    @Test
    public void isLow99(){
        assertTrue(StringExpUtil.isLowerCase((char) 636));
    }
    @Test
    public void isLow100(){
        assertTrue(StringExpUtil.isLowerCase((char) 638));
    }
    @Test
    public void isLow101(){
        assertTrue(StringExpUtil.isLowerCase((char) 639));
    }
    @Test
    public void isLow102(){
        assertTrue(StringExpUtil.isLowerCase((char) 641));
    }
    @Test
    public void isLow103(){
        assertTrue(StringExpUtil.isLowerCase((char) 647));
    }
    @Test
    public void isLow104(){
        assertTrue(StringExpUtil.isLowerCase((char) 653));
    }
    @Test
    public void isLow105(){
        assertTrue(StringExpUtil.isLowerCase((char) 696));
    }
    @Test
    public void isLow106(){
        assertTrue(StringExpUtil.isLowerCase((char) 704));
    }
    @Test
    public void isLow107(){
        assertTrue(StringExpUtil.isLowerCase((char) 705));
    }
    @Test
    public void isLow108(){
        assertTrue(StringExpUtil.isLowerCase((char) 736));
    }
    @Test
    public void isLow109(){
        assertTrue(StringExpUtil.isLowerCase((char) 740));
    }
    @Test
    public void isLow110(){
        assertTrue(!StringExpUtil.isLowerCase((char) 888));
    }
    @Test
    public void isLow111(){
        assertTrue(StringExpUtil.isLowerCase((char) 890));
    }
    @Test
    public void isLow112(){
        assertTrue(!StringExpUtil.isLowerCase((char) 895));
    }
    @Test
    public void isLow113(){
        assertTrue(!StringExpUtil.isLowerCase((char) 899));
    }
    @Test
    public void isLow114(){
        assertTrue(!StringExpUtil.isLowerCase((char) 907));
    }
    @Test
    public void isLow115(){
        assertTrue(!StringExpUtil.isLowerCase((char) 909));
    }
    @Test
    public void isLow116(){
        assertTrue(StringExpUtil.isLowerCase((char) 912));
    }
    @Test
    public void isLow117(){
        assertTrue(!StringExpUtil.isLowerCase((char) 930));
    }
    @Test
    public void isLow118(){
        assertTrue(StringExpUtil.isLowerCase((char) 944));
    }
    @Test
    public void isLow119(){
        assertTrue(StringExpUtil.isLowerCase((char) 1011));
    }
    @Test
    public void isLow120(){
        assertTrue(StringExpUtil.isLowerCase((char) 1020));
    }
    @Test
    public void isLow121(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1320));
    }
    @Test
    public void isLow122(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1328));
    }
    @Test
    public void isLow123(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1367));
    }
    @Test
    public void isLow124(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1368));
    }
    @Test
    public void isLow125(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1376));
    }
    @Test
    public void isLow126(){
        assertTrue(StringExpUtil.isLowerCase((char) 1415));
    }
    @Test
    public void isLow127(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1416));
    }
    @Test
    public void isLow128(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1419));
    }
    @Test
    public void isLow129(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1422));
    }
    @Test
    public void isLow130(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1424));
    }
    @Test
    public void isLow131(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1480));
    }
    @Test
    public void isLow132(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1487));
    }
    @Test
    public void isLow133(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1515));
    }
    @Test
    public void isLow134(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1519));
    }
    @Test
    public void isLow135(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1525));
    }
    @Test
    public void isLow136(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1535));
    }
    @Test
    public void isLow137(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1541));
    }
    @Test
    public void isLow138(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1564));
    }
    @Test
    public void isLow139(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1565));
    }
    @Test
    public void isLow140(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1632));
    }
    @Test
    public void isLow141(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1641));
    }
    @Test
    public void isLow142(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1776));
    }
    @Test
    public void isLow143(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1785));
    }
    @Test
    public void isLow144(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1806));
    }
    @Test
    public void isLow145(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1867));
    }
    @Test
    public void isLow146(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1868));
    }
    @Test
    public void isLow147(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1970));
    }
    @Test
    public void isLow148(){
        assertTrue(!StringExpUtil.isLowerCase((char) 1993));
    }
    @Test
    public void isLow149(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2043));
    }
    @Test
    public void isLow150(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2047));
    }
    @Test
    public void isLow151(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2094));
    }
    @Test
    public void isLow152(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2095));
    }
    @Test
    public void isLow153(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2111));
    }
    @Test
    public void isLow154(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2140));
    }
    @Test
    public void isLow155(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2141));
    }
    @Test
    public void isLow156(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2143));
    }
    @Test
    public void isLow157(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2207));
    }
    @Test
    public void isLow158(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2209));
    }
    @Test
    public void isLow159(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2221));
    }
    @Test
    public void isLow160(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2275));
    }
    @Test
    public void isLow161(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2303));
    }
    @Test
    public void isLow162(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2406));
    }
    @Test
    public void isLow163(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2415));
    }
    @Test
    public void isLow164(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2424));
    }
    @Test
    public void isLow165(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2432));
    }
    @Test
    public void isLow166(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2436));
    }
    @Test
    public void isLow167(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2445));
    }
    @Test
    public void isLow168(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2446));
    }
    @Test
    public void isLow169(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2449));
    }
    @Test
    public void isLow170(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2450));
    }
    @Test
    public void isLow171(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2473));
    }
    @Test
    public void isLow172(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2481));
    }
    @Test
    public void isLow173(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2483));
    }
    @Test
    public void isLow174(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2485));
    }
    @Test
    public void isLow175(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2490));
    }
    @Test
    public void isLow176(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2491));
    }
    @Test
    public void isLow177(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2501));
    }
    @Test
    public void isLow178(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2502));
    }
    @Test
    public void isLow179(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2505));
    }
    @Test
    public void isLow180(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2506));
    }
    @Test
    public void isLow181(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2511));
    }
    @Test
    public void isLow182(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2518));
    }
    @Test
    public void isLow183(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2520));
    }
    @Test
    public void isLow184(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2523));
    }
    @Test
    public void isLow185(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2526));
    }
    @Test
    public void isLow186(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2532));
    }
    @Test
    public void isLow187(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2543));
    }
    @Test
    public void isLow188(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2556));
    }
    @Test
    public void isLow189(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2560));
    }
    @Test
    public void isLow190(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2564));
    }
    @Test
    public void isLow191(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2571));
    }
    @Test
    public void isLow192(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2574));
    }
    @Test
    public void isLow193(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2577));
    }
    @Test
    public void isLow194(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2578));
    }
    @Test
    public void isLow195(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2601));
    }
    @Test
    public void isLow196(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2609));
    }
    @Test
    public void isLow197(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2612));
    }
    @Test
    public void isLow198(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2615));
    }
    @Test
    public void isLow199(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2618));
    }
    @Test
    public void isLow200(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2619));
    }
    @Test
    public void isLow201(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2621));
    }
    @Test
    public void isLow202(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2627));
    }
    @Test
    public void isLow203(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2630));
    }
    @Test
    public void isLow204(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2633));
    }
    @Test
    public void isLow205(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2634));
    }
    @Test
    public void isLow206(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2638));
    }
    @Test
    public void isLow207(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2640));
    }
    @Test
    public void isLow208(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2642));
    }
    @Test
    public void isLow209(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2648));
    }
    @Test
    public void isLow210(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2653));
    }
    @Test
    public void isLow211(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2655));
    }
    @Test
    public void isLow212(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2671));
    }
    @Test
    public void isLow213(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2678));
    }
    @Test
    public void isLow214(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2688));
    }
    @Test
    public void isLow215(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2692));
    }
    @Test
    public void isLow216(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2702));
    }
    @Test
    public void isLow217(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2706));
    }
    @Test
    public void isLow218(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2729));
    }
    @Test
    public void isLow219(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2737));
    }
    @Test
    public void isLow220(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2740));
    }
    @Test
    public void isLow221(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2746));
    }
    @Test
    public void isLow222(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2747));
    }
    @Test
    public void isLow223(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2758));
    }
    @Test
    public void isLow224(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2762));
    }
    @Test
    public void isLow225(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2766));
    }
    @Test
    public void isLow226(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2767));
    }
    @Test
    public void isLow227(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2769));
    }
    @Test
    public void isLow228(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2783));
    }
    @Test
    public void isLow229(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2788));
    }
    @Test
    public void isLow230(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2799));
    }
    @Test
    public void isLow231(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2802));
    }
    @Test
    public void isLow232(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2816));
    }
    @Test
    public void isLow233(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2820));
    }
    @Test
    public void isLow234(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2829));
    }
    @Test
    public void isLow235(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2830));
    }
    @Test
    public void isLow236(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2833));
    }
    @Test
    public void isLow237(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2834));
    }
    @Test
    public void isLow238(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2857));
    }
    @Test
    public void isLow239(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2865));
    }
    @Test
    public void isLow240(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2868));
    }
    @Test
    public void isLow241(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2874));
    }
    @Test
    public void isLow242(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2875));
    }
    @Test
    public void isLow243(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2885));
    }
    @Test
    public void isLow244(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2886));
    }
    @Test
    public void isLow245(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2889));
    }
    @Test
    public void isLow246(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2890));
    }
    @Test
    public void isLow247(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2894));
    }
    @Test
    public void isLow248(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2901));
    }
    @Test
    public void isLow249(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2904));
    }
    @Test
    public void isLow250(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2907));
    }
    @Test
    public void isLow251(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2910));
    }
    @Test
    public void isLow252(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2916));
    }
    @Test
    public void isLow253(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2927));
    }
    @Test
    public void isLow254(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2936));
    }
    @Test
    public void isLow255(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2945));
    }
    @Test
    public void isLow256(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2948));
    }
    @Test
    public void isLow257(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2955));
    }
    @Test
    public void isLow258(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2957));
    }
    @Test
    public void isLow259(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2961));
    }
    @Test
    public void isLow260(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2966));
    }
    @Test
    public void isLow261(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2968));
    }
    @Test
    public void isLow262(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2971));
    }
    @Test
    public void isLow263(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2973));
    }
    @Test
    public void isLow264(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2976));
    }
    @Test
    public void isLow265(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2978));
    }
    @Test
    public void isLow266(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2981));
    }
    @Test
    public void isLow267(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2983));
    }
    @Test
    public void isLow268(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2987));
    }
    @Test
    public void isLow269(){
        assertTrue(!StringExpUtil.isLowerCase((char) 2989));
    }
    @Test
    public void isLow270(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3002));
    }
    @Test
    public void isLow271(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3005));
    }
    @Test
    public void isLow272(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3011));
    }
    @Test
    public void isLow273(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3013));
    }
    @Test
    public void isLow274(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3017));
    }
    @Test
    public void isLow275(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3022));
    }
    @Test
    public void isLow276(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3023));
    }
    @Test
    public void isLow277(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3025));
    }
    @Test
    public void isLow278(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3030));
    }
    @Test
    public void isLow279(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3032));
    }
    @Test
    public void isLow280(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3055));
    }
    @Test
    public void isLow281(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3067));
    }
    @Test
    public void isLow282(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3072));
    }
    @Test
    public void isLow283(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3076));
    }
    @Test
    public void isLow284(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3085));
    }
    @Test
    public void isLow285(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3089));
    }
    @Test
    public void isLow286(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3113));
    }
    @Test
    public void isLow287(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3124));
    }
    @Test
    public void isLow288(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3130));
    }
    @Test
    public void isLow289(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3132));
    }
    @Test
    public void isLow290(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3141));
    }
    @Test
    public void isLow291(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3145));
    }
    @Test
    public void isLow292(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3150));
    }
    @Test
    public void isLow293(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3156));
    }
    @Test
    public void isLow294(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3159));
    }
    @Test
    public void isLow295(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3162));
    }
    @Test
    public void isLow296(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3167));
    }
    @Test
    public void isLow297(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3172));
    }
    @Test
    public void isLow298(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3191));
    }
    @Test
    public void isLow299(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3200));
    }
    @Test
    public void isLow300(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3201));
    }
    @Test
    public void isLow301(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3204));
    }
    @Test
    public void isLow302(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3213));
    }
    @Test
    public void isLow303(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3217));
    }
    @Test
    public void isLow304(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3241));
    }
    @Test
    public void isLow305(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3252));
    }
    @Test
    public void isLow306(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3258));
    }
    @Test
    public void isLow307(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3259));
    }
    @Test
    public void isLow308(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3269));
    }
    @Test
    public void isLow309(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3273));
    }
    @Test
    public void isLow310(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3278));
    }
    @Test
    public void isLow311(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3284));
    }
    @Test
    public void isLow312(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3287));
    }
    @Test
    public void isLow313(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3293));
    }
    @Test
    public void isLow314(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3295));
    }
    @Test
    public void isLow315(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3300));
    }
    @Test
    public void isLow316(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3312));
    }
    @Test
    public void isLow317(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3315));
    }
    @Test
    public void isLow318(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3329));
    }
    @Test
    public void isLow319(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3332));
    }
    @Test
    public void isLow320(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3341));
    }
    @Test
    public void isLow321(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3345));
    }
    @Test
    public void isLow322(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3387));
    }
    @Test
    public void isLow323(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3388));
    }
    @Test
    public void isLow324(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3397));
    }
    @Test
    public void isLow325(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3401));
    }
    @Test
    public void isLow326(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3407));
    }
    @Test
    public void isLow327(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3414));
    }
    @Test
    public void isLow328(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3416));
    }
    @Test
    public void isLow329(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3423));
    }
    @Test
    public void isLow330(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3428));
    }
    @Test
    public void isLow331(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3439));
    }
    @Test
    public void isLow332(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3446));
    }
    @Test
    public void isLow333(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3448));
    }
    @Test
    public void isLow334(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3456));
    }
    @Test
    public void isLow335(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3457));
    }
    @Test
    public void isLow336(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3460));
    }
    @Test
    public void isLow337(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3479));
    }
    @Test
    public void isLow338(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3481));
    }
    @Test
    public void isLow339(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3506));
    }
    @Test
    public void isLow340(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3516));
    }
    @Test
    public void isLow341(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3518));
    }
    @Test
    public void isLow342(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3519));
    }
    @Test
    public void isLow343(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3527));
    }
    @Test
    public void isLow344(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3529));
    }
    @Test
    public void isLow345(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3531));
    }
    @Test
    public void isLow346(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3534));
    }
    @Test
    public void isLow347(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3541));
    }
    @Test
    public void isLow348(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3543));
    }
    @Test
    public void isLow349(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3552));
    }
    @Test
    public void isLow350(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3569));
    }
    @Test
    public void isLow351(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3573));
    }
    @Test
    public void isLow352(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3584));
    }
    @Test
    public void isLow353(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3643));
    }
    @Test
    public void isLow354(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3646));
    }
    @Test
    public void isLow355(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3664));
    }
    @Test
    public void isLow356(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3673));
    }
    @Test
    public void isLow357(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3676));
    }
    @Test
    public void isLow358(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3712));
    }
    @Test
    public void isLow359(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3715));
    }
    @Test
    public void isLow360(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3717));
    }
    @Test
    public void isLow361(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3718));
    }
    @Test
    public void isLow362(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3721));
    }
    @Test
    public void isLow363(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3723));
    }
    @Test
    public void isLow364(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3724));
    }
    @Test
    public void isLow365(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3726));
    }
    @Test
    public void isLow366(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3731));
    }
    @Test
    public void isLow367(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3736));
    }
    @Test
    public void isLow368(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3744));
    }
    @Test
    public void isLow369(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3748));
    }
    @Test
    public void isLow370(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3750));
    }
    @Test
    public void isLow371(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3752));
    }
    @Test
    public void isLow372(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3753));
    }
    @Test
    public void isLow373(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3756));
    }
    @Test
    public void isLow374(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3770));
    }
    @Test
    public void isLow375(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3774));
    }
    @Test
    public void isLow376(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3775));
    }
    @Test
    public void isLow377(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3781));
    }
    @Test
    public void isLow378(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3783));
    }
    @Test
    public void isLow379(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3790));
    }
    @Test
    public void isLow380(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3803));
    }
    @Test
    public void isLow381(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3808));
    }
    @Test
    public void isLow382(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3839));
    }
    @Test
    public void isLow383(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3872));
    }
    @Test
    public void isLow384(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3881));
    }
    @Test
    public void isLow385(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3912));
    }
    @Test
    public void isLow386(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3949));
    }
    @Test
    public void isLow387(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3952));
    }
    @Test
    public void isLow388(){
        assertTrue(!StringExpUtil.isLowerCase((char) 3992));
    }
    @Test
    public void isLow389(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4029));
    }
    @Test
    public void isLow390(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4045));
    }
    @Test
    public void isLow391(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4059));
    }
    @Test
    public void isLow392(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4095));
    }
    @Test
    public void isLow393(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4160));
    }
    @Test
    public void isLow394(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4169));
    }
    @Test
    public void isLow395(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4240));
    }
    @Test
    public void isLow396(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4249));
    }
    @Test
    public void isLow397(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4294));
    }
    @Test
    public void isLow398(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4296));
    }
    @Test
    public void isLow399(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4300));
    }
    @Test
    public void isLow400(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4302));
    }
    @Test
    public void isLow401(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4303));
    }
    @Test
    public void isLow402(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4681));
    }
    @Test
    public void isLow403(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4686));
    }
    @Test
    public void isLow404(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4687));
    }
    @Test
    public void isLow405(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4695));
    }
    @Test
    public void isLow406(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4697));
    }
    @Test
    public void isLow407(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4702));
    }
    @Test
    public void isLow408(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4703));
    }
    @Test
    public void isLow409(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4745));
    }
    @Test
    public void isLow410(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4750));
    }
    @Test
    public void isLow411(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4751));
    }
    @Test
    public void isLow412(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4785));
    }
    @Test
    public void isLow413(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4790));
    }
    @Test
    public void isLow414(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4791));
    }
    @Test
    public void isLow415(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4799));
    }
    @Test
    public void isLow416(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4801));
    }
    @Test
    public void isLow417(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4806));
    }
    @Test
    public void isLow418(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4807));
    }
    @Test
    public void isLow419(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4823));
    }
    @Test
    public void isLow420(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4881));
    }
    @Test
    public void isLow421(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4886));
    }
    @Test
    public void isLow422(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4887));
    }
    @Test
    public void isLow423(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4955));
    }
    @Test
    public void isLow424(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4956));
    }
    @Test
    public void isLow425(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4989));
    }
    @Test
    public void isLow426(){
        assertTrue(!StringExpUtil.isLowerCase((char) 4991));
    }
    @Test
    public void isLow427(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5018));
    }
    @Test
    public void isLow428(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5023));
    }
    @Test
    public void isLow429(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5109));
    }
    @Test
    public void isLow430(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5119));
    }
    @Test
    public void isLow431(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5760));
    }
    @Test
    public void isLow432(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5789));
    }
    @Test
    public void isLow433(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5791));
    }
    @Test
    public void isLow434(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5873));
    }
    @Test
    public void isLow435(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5887));
    }
    @Test
    public void isLow436(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5901));
    }
    @Test
    public void isLow437(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5909));
    }
    @Test
    public void isLow438(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5919));
    }
    @Test
    public void isLow439(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5943));
    }
    @Test
    public void isLow440(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5951));
    }
    @Test
    public void isLow441(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5972));
    }
    @Test
    public void isLow442(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5983));
    }
    @Test
    public void isLow443(){
        assertTrue(!StringExpUtil.isLowerCase((char) 5997));
    }
    @Test
    public void isLow444(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6001));
    }
    @Test
    public void isLow445(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6004));
    }
    @Test
    public void isLow446(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6015));
    }
    @Test
    public void isLow447(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6110));
    }
    @Test
    public void isLow448(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6127));
    }
    @Test
    public void isLow449(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6138));
    }
    @Test
    public void isLow450(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6143));
    }
    @Test
    public void isLow451(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6158));
    }
    @Test
    public void isLow452(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6175));
    }
    @Test
    public void isLow453(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6264));
    }
    @Test
    public void isLow454(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6271));
    }
    @Test
    public void isLow455(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6315));
    }
    @Test
    public void isLow456(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6319));
    }
    @Test
    public void isLow457(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6390));
    }
    @Test
    public void isLow458(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6399));
    }
    @Test
    public void isLow459(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6429));
    }
    @Test
    public void isLow460(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6431));
    }
    @Test
    public void isLow461(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6444));
    }
    @Test
    public void isLow462(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6447));
    }
    @Test
    public void isLow463(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6460));
    }
    @Test
    public void isLow464(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6463));
    }
    @Test
    public void isLow465(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6465));
    }
    @Test
    public void isLow466(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6467));
    }
    @Test
    public void isLow467(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6470));
    }
    @Test
    public void isLow468(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6479));
    }
    @Test
    public void isLow469(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6510));
    }
    @Test
    public void isLow470(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6511));
    }
    @Test
    public void isLow471(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6517));
    }
    @Test
    public void isLow472(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6527));
    }
    @Test
    public void isLow473(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6572));
    }
    @Test
    public void isLow474(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6575));
    }
    @Test
    public void isLow475(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6602));
    }
    @Test
    public void isLow476(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6617));
    }
    @Test
    public void isLow477(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6619));
    }
    @Test
    public void isLow478(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6621));
    }
    @Test
    public void isLow479(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6684));
    }
    @Test
    public void isLow480(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6685));
    }
    @Test
    public void isLow481(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6751));
    }
    @Test
    public void isLow482(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6781));
    }
    @Test
    public void isLow483(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6782));
    }
    @Test
    public void isLow484(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6784));
    }
    @Test
    public void isLow485(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6815));
    }
    @Test
    public void isLow486(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6830));
    }
    @Test
    public void isLow487(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6911));
    }
    @Test
    public void isLow488(){
        assertTrue(!StringExpUtil.isLowerCase((char) 6988));
    }
    @Test
    public void isLow489(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7001));
    }
    @Test
    public void isLow490(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7037));
    }
    @Test
    public void isLow491(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7039));
    }
    @Test
    public void isLow492(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7088));
    }
    @Test
    public void isLow493(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7097));
    }
    @Test
    public void isLow494(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7156));
    }
    @Test
    public void isLow495(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7163));
    }
    @Test
    public void isLow496(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7224));
    }
    @Test
    public void isLow497(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7226));
    }
    @Test
    public void isLow498(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7232));
    }
    @Test
    public void isLow499(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7244));
    }
    @Test
    public void isLow500(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7248));
    }
    @Test
    public void isLow501(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7257));
    }
    @Test
    public void isLow502(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7296));
    }
    @Test
    public void isLow503(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7359));
    }
    @Test
    public void isLow504(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7368));
    }
    @Test
    public void isLow505(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7375));
    }
    @Test
    public void isLow506(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7415));
    }
    @Test
    public void isLow507(){
        assertTrue(StringExpUtil.isLowerCase((char) 7615));
    }
    @Test
    public void isLow508(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7655));
    }
    @Test
    public void isLow509(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7675));
    }
    @Test
    public void isLow510(){
        assertTrue(StringExpUtil.isLowerCase((char) 7830));
    }
    @Test
    public void isLow511(){
        assertTrue(StringExpUtil.isLowerCase((char) 7834));
    }
    @Test
    public void isLow512(){
        assertTrue(StringExpUtil.isLowerCase((char) 7836));
    }
    @Test
    public void isLow513(){
        assertTrue(StringExpUtil.isLowerCase((char) 7837));
    }
    @Test
    public void isLow514(){
        assertTrue(StringExpUtil.isLowerCase((char) 7839));
    }
    @Test
    public void isLow515(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7958));
    }
    @Test
    public void isLow516(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7959));
    }
    @Test
    public void isLow517(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7966));
    }
    @Test
    public void isLow518(){
        assertTrue(!StringExpUtil.isLowerCase((char) 7967));
    }
    @Test
    public void isLow519(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8006));
    }
    @Test
    public void isLow520(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8007));
    }
    @Test
    public void isLow521(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8014));
    }
    @Test
    public void isLow522(){
        assertTrue(StringExpUtil.isLowerCase((char) 8016));
    }
    @Test
    public void isLow523(){
        assertTrue(StringExpUtil.isLowerCase((char) 8018));
    }
    @Test
    public void isLow524(){
        assertTrue(StringExpUtil.isLowerCase((char) 8020));
    }
    @Test
    public void isLow525(){
        assertTrue(StringExpUtil.isLowerCase((char) 8022));
    }
    @Test
    public void isLow526(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8024));
    }
    @Test
    public void isLow527(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8026));
    }
    @Test
    public void isLow528(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8028));
    }
    @Test
    public void isLow529(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8030));
    }
    @Test
    public void isLow530(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8062));
    }
    @Test
    public void isLow531(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8063));
    }
    @Test
    public void isLow532(){
        assertTrue(StringExpUtil.isLowerCase((char) 8114));
    }
    @Test
    public void isLow533(){
        assertTrue(StringExpUtil.isLowerCase((char) 8116));
    }
    @Test
    public void isLow534(){
        assertTrue(StringExpUtil.isLowerCase((char) 8119));
    }
    @Test
    public void isLow535(){
        assertTrue(StringExpUtil.isLowerCase((char) 8130));
    }
    @Test
    public void isLow536(){
        assertTrue(StringExpUtil.isLowerCase((char) 8132));
    }
    @Test
    public void isLow537(){
        assertTrue(StringExpUtil.isLowerCase((char) 8135));
    }
    @Test
    public void isLow538(){
        assertTrue(StringExpUtil.isLowerCase((char) 8146));
    }
    @Test
    public void isLow539(){
        assertTrue(StringExpUtil.isLowerCase((char) 8151));
    }
    @Test
    public void isLow540(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8156));
    }
    @Test
    public void isLow541(){
        assertTrue(StringExpUtil.isLowerCase((char) 8162));
    }
    @Test
    public void isLow542(){
        assertTrue(StringExpUtil.isLowerCase((char) 8164));
    }
    @Test
    public void isLow543(){
        assertTrue(StringExpUtil.isLowerCase((char) 8166));
    }
    @Test
    public void isLow544(){
        assertTrue(StringExpUtil.isLowerCase((char) 8167));
    }
    @Test
    public void isLow545(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8176));
    }
    @Test
    public void isLow546(){
        assertTrue(StringExpUtil.isLowerCase((char) 8178));
    }
    @Test
    public void isLow547(){
        assertTrue(StringExpUtil.isLowerCase((char) 8180));
    }
    @Test
    public void isLow548(){
        assertTrue(StringExpUtil.isLowerCase((char) 8183));
    }
    @Test
    public void isLow549(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8191));
    }
    @Test
    public void isLow550(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8202));
    }
    @Test
    public void isLow551(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8239));
    }
    @Test
    public void isLow552(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8287));
    }
    @Test
    public void isLow553(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8293));
    }
    @Test
    public void isLow554(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8297));
    }
    @Test
    public void isLow555(){
        assertTrue(StringExpUtil.isLowerCase((char) 8305));
    }
    @Test
    public void isLow556(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8307));
    }
    @Test
    public void isLow557(){
        assertTrue(StringExpUtil.isLowerCase((char) 8319));
    }
    @Test
    public void isLow558(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8335));
    }
    @Test
    public void isLow559(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8351));
    }
    @Test
    public void isLow560(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8379));
    }
    @Test
    public void isLow561(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8399));
    }
    @Test
    public void isLow562(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8433));
    }
    @Test
    public void isLow563(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8447));
    }
    @Test
    public void isLow564(){
        assertTrue(StringExpUtil.isLowerCase((char) 8458));
    }
    @Test
    public void isLow565(){
        assertTrue(StringExpUtil.isLowerCase((char) 8462));
    }
    @Test
    public void isLow566(){
        assertTrue(StringExpUtil.isLowerCase((char) 8463));
    }
    @Test
    public void isLow567(){
        assertTrue(StringExpUtil.isLowerCase((char) 8467));
    }
    @Test
    public void isLow568(){
        assertTrue(StringExpUtil.isLowerCase((char) 8495));
    }
    @Test
    public void isLow569(){
        assertTrue(StringExpUtil.isLowerCase((char) 8500));
    }
    @Test
    public void isLow570(){
        assertTrue(StringExpUtil.isLowerCase((char) 8505));
    }
    @Test
    public void isLow571(){
        assertTrue(StringExpUtil.isLowerCase((char) 8508));
    }
    @Test
    public void isLow572(){
        assertTrue(StringExpUtil.isLowerCase((char) 8509));
    }
    @Test
    public void isLow573(){
        assertTrue(StringExpUtil.isLowerCase((char) 8518));
    }
    @Test
    public void isLow574(){
        assertTrue(StringExpUtil.isLowerCase((char) 8521));
    }
    @Test
    public void isLow575(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8586));
    }
    @Test
    public void isLow576(){
        assertTrue(!StringExpUtil.isLowerCase((char) 8591));
    }
    @Test
    public void isLow577(){
        assertTrue(!StringExpUtil.isLowerCase((char) 9204));
    }
    @Test
    public void isLow578(){
        assertTrue(!StringExpUtil.isLowerCase((char) 9215));
    }
    @Test
    public void isLow579(){
        assertTrue(!StringExpUtil.isLowerCase((char) 9255));
    }
    @Test
    public void isLow580(){
        assertTrue(!StringExpUtil.isLowerCase((char) 9279));
    }
    @Test
    public void isLow581(){
        assertTrue(!StringExpUtil.isLowerCase((char) 9291));
    }
    @Test
    public void isLow582(){
        assertTrue(!StringExpUtil.isLowerCase((char) 9311));
    }
    @Test
    public void isLow583(){
        assertTrue(!StringExpUtil.isLowerCase((char) 9984));
    }
    @Test
    public void isLow584(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11085));
    }
    @Test
    public void isLow585(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11087));
    }
    @Test
    public void isLow586(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11098));
    }
    @Test
    public void isLow587(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11263));
    }
    @Test
    public void isLow588(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11311));
    }
    @Test
    public void isLow589(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11359));
    }
    @Test
    public void isLow590(){
        assertTrue(StringExpUtil.isLowerCase((char) 11377));
    }
    @Test
    public void isLow591(){
        assertTrue(StringExpUtil.isLowerCase((char) 11380));
    }
    @Test
    public void isLow592(){
        assertTrue(StringExpUtil.isLowerCase((char) 11383));
    }
    @Test
    public void isLow593(){
        assertTrue(StringExpUtil.isLowerCase((char) 11389));
    }
    @Test
    public void isLow594(){
        assertTrue(StringExpUtil.isLowerCase((char) 11492));
    }
    @Test
    public void isLow595(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11508));
    }
    @Test
    public void isLow596(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11512));
    }
    @Test
    public void isLow597(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11558));
    }
    @Test
    public void isLow598(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11560));
    }
    @Test
    public void isLow599(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11564));
    }
    @Test
    public void isLow600(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11566));
    }
    @Test
    public void isLow601(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11567));
    }
    @Test
    public void isLow602(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11624));
    }
    @Test
    public void isLow603(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11630));
    }
    @Test
    public void isLow604(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11633));
    }
    @Test
    public void isLow605(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11646));
    }
    @Test
    public void isLow606(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11671));
    }
    @Test
    public void isLow607(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11679));
    }
    @Test
    public void isLow608(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11687));
    }
    @Test
    public void isLow609(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11695));
    }
    @Test
    public void isLow610(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11703));
    }
    @Test
    public void isLow611(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11711));
    }
    @Test
    public void isLow612(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11719));
    }
    @Test
    public void isLow613(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11727));
    }
    @Test
    public void isLow614(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11735));
    }
    @Test
    public void isLow615(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11743));
    }
    @Test
    public void isLow616(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11836));
    }
    @Test
    public void isLow617(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11903));
    }
    @Test
    public void isLow618(){
        assertTrue(!StringExpUtil.isLowerCase((char) 11930));
    }
    @Test
    public void isLow619(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12020));
    }
    @Test
    public void isLow620(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12031));
    }
    @Test
    public void isLow621(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12246));
    }
    @Test
    public void isLow622(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12271));
    }
    @Test
    public void isLow623(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12284));
    }
    @Test
    public void isLow624(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12288));
    }
    @Test
    public void isLow625(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12352));
    }
    @Test
    public void isLow626(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12439));
    }
    @Test
    public void isLow627(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12440));
    }
    @Test
    public void isLow628(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12544));
    }
    @Test
    public void isLow629(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12548));
    }
    @Test
    public void isLow630(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12590));
    }
    @Test
    public void isLow631(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12592));
    }
    @Test
    public void isLow632(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12687));
    }
    @Test
    public void isLow633(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12731));
    }
    @Test
    public void isLow634(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12735));
    }
    @Test
    public void isLow635(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12772));
    }
    @Test
    public void isLow636(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12783));
    }
    @Test
    public void isLow637(){
        assertTrue(!StringExpUtil.isLowerCase((char) 12831));
    }
    @Test
    public void isLow638(){
        assertTrue(!StringExpUtil.isLowerCase((char) 13055));
    }
    @Test
    public void isLow639(){
        assertTrue(!StringExpUtil.isLowerCase((char) 19894));
    }
    @Test
    public void isLow640(){
        assertTrue(!StringExpUtil.isLowerCase((char) 19903));
    }
    @Test
    public void isLow641(){
        assertTrue(!StringExpUtil.isLowerCase((char) 40909));
    }
    @Test
    public void isLow642(){
        assertTrue(!StringExpUtil.isLowerCase((char) 40959));
    }
    @Test
    public void isLow643(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42125));
    }
    @Test
    public void isLow644(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42127));
    }
    @Test
    public void isLow645(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42183));
    }
    @Test
    public void isLow646(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42191));
    }
    @Test
    public void isLow647(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42528));
    }
    @Test
    public void isLow648(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42537));
    }
    @Test
    public void isLow649(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42540));
    }
    @Test
    public void isLow650(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42559));
    }
    @Test
    public void isLow651(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42648));
    }
    @Test
    public void isLow652(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42654));
    }
    @Test
    public void isLow653(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42744));
    }
    @Test
    public void isLow654(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42751));
    }
    @Test
    public void isLow655(){
        assertTrue(StringExpUtil.isLowerCase((char) 42800));
    }
    @Test
    public void isLow656(){
        assertTrue(StringExpUtil.isLowerCase((char) 42801));
    }
    @Test
    public void isLow657(){
        assertTrue(StringExpUtil.isLowerCase((char) 42864));
    }
    @Test
    public void isLow658(){
        assertTrue(StringExpUtil.isLowerCase((char) 42872));
    }
    @Test
    public void isLow659(){
        assertTrue(StringExpUtil.isLowerCase((char) 42894));
    }
    @Test
    public void isLow660(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42895));
    }
    @Test
    public void isLow661(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42900));
    }
    @Test
    public void isLow662(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42911));
    }
    @Test
    public void isLow663(){
        assertTrue(!StringExpUtil.isLowerCase((char) 42923));
    }
    @Test
    public void isLow664(){
        assertTrue(StringExpUtil.isLowerCase((char) 43002));
    }
    @Test
    public void isLow665(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43052));
    }
    @Test
    public void isLow666(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43055));
    }
    @Test
    public void isLow667(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43066));
    }
    @Test
    public void isLow668(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43071));
    }
    @Test
    public void isLow669(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43128));
    }
    @Test
    public void isLow670(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43135));
    }
    @Test
    public void isLow671(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43205));
    }
    @Test
    public void isLow672(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43213));
    }
    @Test
    public void isLow673(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43216));
    }
    @Test
    public void isLow674(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43231));
    }
    @Test
    public void isLow675(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43260));
    }
    @Test
    public void isLow676(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43273));
    }
    @Test
    public void isLow677(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43348));
    }
    @Test
    public void isLow678(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43358));
    }
    @Test
    public void isLow679(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43389));
    }
    @Test
    public void isLow680(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43391));
    }
    @Test
    public void isLow681(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43470));
    }
    @Test
    public void isLow682(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43472));
    }
    @Test
    public void isLow683(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43485));
    }
    @Test
    public void isLow684(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43488));
    }
    @Test
    public void isLow685(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43519));
    }
    @Test
    public void isLow686(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43575));
    }
    @Test
    public void isLow687(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43583));
    }
    @Test
    public void isLow688(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43598));
    }
    @Test
    public void isLow689(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43611));
    }
    @Test
    public void isLow690(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43644));
    }
    @Test
    public void isLow691(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43647));
    }
    @Test
    public void isLow692(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43715));
    }
    @Test
    public void isLow693(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43738));
    }
    @Test
    public void isLow694(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43767));
    }
    @Test
    public void isLow695(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43776));
    }
    @Test
    public void isLow696(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43783));
    }
    @Test
    public void isLow697(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43784));
    }
    @Test
    public void isLow698(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43791));
    }
    @Test
    public void isLow699(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43792));
    }
    @Test
    public void isLow700(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43799));
    }
    @Test
    public void isLow701(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43807));
    }
    @Test
    public void isLow702(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43815));
    }
    @Test
    public void isLow703(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43823));
    }
    @Test
    public void isLow704(){
        assertTrue(!StringExpUtil.isLowerCase((char) 43967));
    }
    @Test
    public void isLow705(){
        assertTrue(!StringExpUtil.isLowerCase((char) 44014));
    }
    @Test
    public void isLow706(){
        assertTrue(!StringExpUtil.isLowerCase((char) 44031));
    }
    @Test
    public void isLow707(){
        assertTrue(!StringExpUtil.isLowerCase((char) 55204));
    }
    @Test
    public void isLow708(){
        assertTrue(!StringExpUtil.isLowerCase((char) 55215));
    }
    @Test
    public void isLow709(){
        assertTrue(!StringExpUtil.isLowerCase((char) 55239));
    }
    @Test
    public void isLow710(){
        assertTrue(!StringExpUtil.isLowerCase((char) 55242));
    }
    @Test
    public void isLow711(){
        assertTrue(!StringExpUtil.isLowerCase((char) 55292));
    }
    @Test
    public void isLow712(){
        assertTrue(!StringExpUtil.isLowerCase((char) 55295));
    }
    @Test
    public void isLow713(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64110));
    }
    @Test
    public void isLow714(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64111));
    }
    @Test
    public void isLow715(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64218));
    }
    @Test
    public void isLow716(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64284));
    }
    @Test
    public void isLow717(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64311));
    }
    @Test
    public void isLow718(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64317));
    }
    @Test
    public void isLow719(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64319));
    }
    @Test
    public void isLow720(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64322));
    }
    @Test
    public void isLow721(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64325));
    }
    @Test
    public void isLow722(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64450));
    }
    @Test
    public void isLow723(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64466));
    }
    @Test
    public void isLow724(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64832));
    }
    @Test
    public void isLow725(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64847));
    }
    @Test
    public void isLow726(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64912));
    }
    @Test
    public void isLow727(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64913));
    }
    @Test
    public void isLow728(){
        assertTrue(!StringExpUtil.isLowerCase((char) 64968));
    }
    @Test
    public void isLow729(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65007));
    }
    @Test
    public void isLow730(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65022));
    }
    @Test
    public void isLow731(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65023));
    }
    @Test
    public void isLow732(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65050));
    }
    @Test
    public void isLow733(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65055));
    }
    @Test
    public void isLow734(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65063));
    }
    @Test
    public void isLow735(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65071));
    }
    @Test
    public void isLow736(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65107));
    }
    @Test
    public void isLow737(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65127));
    }
    @Test
    public void isLow738(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65132));
    }
    @Test
    public void isLow739(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65135));
    }
    @Test
    public void isLow740(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65141));
    }
    @Test
    public void isLow741(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65277));
    }
    @Test
    public void isLow742(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65278));
    }
    @Test
    public void isLow743(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65280));
    }
    @Test
    public void isLow744(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65296));
    }
    @Test
    public void isLow745(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65305));
    }
    @Test
    public void isLow746(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65471));
    }
    @Test
    public void isLow747(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65473));
    }
    @Test
    public void isLow748(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65480));
    }
    @Test
    public void isLow749(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65481));
    }
    @Test
    public void isLow750(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65488));
    }
    @Test
    public void isLow751(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65489));
    }
    @Test
    public void isLow752(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65496));
    }
    @Test
    public void isLow753(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65497));
    }
    @Test
    public void isLow754(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65501));
    }
    @Test
    public void isLow755(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65503));
    }
    @Test
    public void isLow756(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65511));
    }
    @Test
    public void isLow757(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65519));
    }
    @Test
    public void isLow758(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65528));
    }
    @Test
    public void isLow759(){
        assertTrue(!StringExpUtil.isLowerCase((char) 65534));
    }
    @Test
    public void isLow760(){
        assertTrue(StringExpUtil.isLowerCase((char) 8336));
    }
    @Test
    public void isLow761(){
        assertTrue(!StringExpUtil.isLowerCase((char) 660));
    }

    @Test
    public void toUpper159(){
        assertEq(83, StringExpUtil.toUpperCase((char) 383));
    }
    @Test
    public void toUpper160(){
        assertEq(391, StringExpUtil.toUpperCase((char) 392));
    }
    @Test
    public void toUpper161(){
        assertEq(395, StringExpUtil.toUpperCase((char) 396));
    }
    @Test
    public void toUpper162(){
        assertEq(401, StringExpUtil.toUpperCase((char) 402));
    }
    @Test
    public void toUpper163(){
        assertEq(408, StringExpUtil.toUpperCase((char) 409));
    }
    @Test
    public void toUpper164(){
        assertEq(573, StringExpUtil.toUpperCase((char) 410));
    }
    @Test
    public void toUpper165(){
        assertEq(423, StringExpUtil.toUpperCase((char) 424));
    }
    @Test
    public void toUpper166(){
        assertEq(428, StringExpUtil.toUpperCase((char) 429));
    }
    @Test
    public void toUpper167(){
        assertEq(431, StringExpUtil.toUpperCase((char) 432));
    }
    @Test
    public void toUpper168(){
        assertEq(440, StringExpUtil.toUpperCase((char) 441));
    }
    @Test
    public void toUpper169(){
        assertEq(444, StringExpUtil.toUpperCase((char) 445));
    }
    @Test
    public void toUpper170(){
        assertEq(452, StringExpUtil.toUpperCase((char) 453));
    }
    @Test
    public void toUpper171(){
        assertEq(452, StringExpUtil.toUpperCase((char) 454));
    }
    @Test
    public void toUpper172(){
        assertEq(455, StringExpUtil.toUpperCase((char) 456));
    }
    @Test
    public void toUpper173(){
        assertEq(455, StringExpUtil.toUpperCase((char) 457));
    }
    @Test
    public void toUpper174(){
        assertEq(458, StringExpUtil.toUpperCase((char) 460));
    }
    @Test
    public void toUpper175(){
        assertEq(398, StringExpUtil.toUpperCase((char) 477));
    }
    @Test
    public void toUpper176(){
        assertEq(497, StringExpUtil.toUpperCase((char) 499));
    }
    @Test
    public void toUpper177(){
        assertEq(11375, StringExpUtil.toUpperCase((char) 592));
    }
    @Test
    public void toUpper178(){
        assertEq(922, StringExpUtil.toUpperCase((char) 1008));
    }
    @Test
    public void toUpper179(){
        assertEq(1216, StringExpUtil.toUpperCase((char) 1231));
    }
    @Test
    public void toUpper180(){
        assertEq(7944, StringExpUtil.toUpperCase((char) 7936));
    }
    @Test
    public void toUpper181(){
        assertEq(48, StringExpUtil.toUpperCase((char) 48));
    }
    @Test
    public void toUpper182(){
        assertEq(57, StringExpUtil.toUpperCase((char) 57));
    }
    @Test
    public void toUpper183(){
        assertEq(127, StringExpUtil.toUpperCase((char) 127));
    }
    @Test
    public void toUpper184(){
        assertEq(160, StringExpUtil.toUpperCase((char) 160));
    }
    @Test
    public void toUpper185(){
        assertEq(888, StringExpUtil.toUpperCase((char) 888));
    }
    @Test
    public void toUpper186(){
        assertEq(8544, StringExpUtil.toUpperCase((char) 8544));
    }
    @Test
    public void toUpper187(){
        assertEq(9398, StringExpUtil.toUpperCase((char) 9398));
    }
    @Test
    public void toUpper188(){
        assertEq(8602, StringExpUtil.toUpperCase((char) 8602));
    }
    @Test
    public void toUpper189(){
        assertEq(1632, StringExpUtil.toUpperCase((char) 1632));
    }
    @Test
    public void toLower158(){
        assertEq(1632, StringExpUtil.toLowerCase((char) 1632));
    }
    @Test
    public void toLower159(){
        assertEq(888, StringExpUtil.toLowerCase((char) 888));
    }
    @Test
    public void toLower160(){
        assertEq(160, StringExpUtil.toLowerCase((char) 160));
    }


    @Test
    public void getType0(){
        assertEq(StringExpUtil.SPACE_OTHER, StringExpUtil.getCustomType((char) 0));
    }
    @Test
    public void getType1(){
        assertEq(StringExpUtil.SPACE, StringExpUtil.getCustomType((char) 9));
    }
    @Test
    public void getType2(){
        assertEq(StringExpUtil.SPACE_OTHER, StringExpUtil.getCustomType((char) 11));
    }
    @Test
    public void getType3(){
        assertEq(StringExpUtil.SPACE, StringExpUtil.getCustomType((char) 32));
    }
    @Test
    public void getType4(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 33));
    }
    @Test
    public void getType5(){
        assertEq(StringExpUtil.QUOTES, StringExpUtil.getCustomType((char) 34));
    }
    @Test
    public void getType6(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 35));
    }
    @Test
    public void getType7(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 36));
    }
    @Test
    public void getType8(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 37));
    }
    @Test
    public void getType9(){
        assertEq(StringExpUtil.QUOTES, StringExpUtil.getCustomType((char) 39));
    }
    @Test
    public void getType10(){
        assertEq(StringExpUtil.DEL_LEFT, StringExpUtil.getCustomType((char) 40));
    }
    @Test
    public void getType11(){
        assertEq(StringExpUtil.DEL_RIGHT, StringExpUtil.getCustomType((char) 41));
    }
    @Test
    public void getType12(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 42));
    }
    @Test
    public void getType13(){
        assertEq(StringExpUtil.OPERATOR_SPEC, StringExpUtil.getCustomType((char) 44));
    }
    @Test
    public void getType14(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 45));
    }
    @Test
    public void getType15(){
        assertEq(StringExpUtil.OPERATOR_SPEC, StringExpUtil.getCustomType((char) 46));
    }
    @Test
    public void getType16(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 47));
    }
    @Test
    public void getType17(){
        assertEq(StringExpUtil.DIGIT, StringExpUtil.getCustomType((char) 48));
    }
    @Test
    public void getType18(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 58));
    }
    @Test
    public void getType19(){
        assertEq(StringExpUtil.PUNCTUATION, StringExpUtil.getCustomType((char) 59));
    }
    @Test
    public void getType20(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 60));
    }
    @Test
    public void getType21(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 65));
    }
    @Test
    public void getType22(){
        assertEq(StringExpUtil.DEL_LEFT, StringExpUtil.getCustomType((char) 91));
    }
    @Test
    public void getType23(){
        assertEq(StringExpUtil.ESCAPE, StringExpUtil.getCustomType((char) 92));
    }
    @Test
    public void getType24(){
        assertEq(StringExpUtil.DEL_RIGHT, StringExpUtil.getCustomType((char) 93));
    }
    @Test
    public void getType25(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 94));
    }
    @Test
    public void getType26(){
        assertEq(StringExpUtil.ID_SEP, StringExpUtil.getCustomType((char) 95));
    }
    @Test
    public void getType27(){
        assertEq(StringExpUtil.QUOTES, StringExpUtil.getCustomType((char) 96));
    }
    @Test
    public void getType28(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 97));
    }
    @Test
    public void getType29(){
        assertEq(StringExpUtil.DEL_LEFT, StringExpUtil.getCustomType((char) 123));
    }
    @Test
    public void getType30(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 124));
    }
    @Test
    public void getType31(){
        assertEq(StringExpUtil.DEL_RIGHT, StringExpUtil.getCustomType((char) 125));
    }
    @Test
    public void getType32(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 126));
    }
    @Test
    public void getType33(){
        assertEq(StringExpUtil.SPACE_OTHER, StringExpUtil.getCustomType((char) 127));
    }
    @Test
    public void getType34(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 161));
    }
    @Test
    public void getType35(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 162));
    }
    @Test
    public void getType36(){
        assertEq(StringExpUtil.MODIFIER, StringExpUtil.getCustomType((char) 166));
    }
    @Test
    public void getType37(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 167));
    }
    @Test
    public void getType38(){
        assertEq(StringExpUtil.MODIFIER, StringExpUtil.getCustomType((char) 168));
    }
    @Test
    public void getType39(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 170));
    }
    @Test
    public void getType40(){
        assertEq(StringExpUtil.PUNCTUATION_QUOTE, StringExpUtil.getCustomType((char) 171));
    }
    @Test
    public void getType41(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 172));
    }
    @Test
    public void getType42(){
        assertEq(StringExpUtil.MODIFIER, StringExpUtil.getCustomType((char) 173));
    }
    @Test
    public void getType43(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 177));
    }
    @Test
    public void getType44(){
        assertEq(StringExpUtil.MODIFIER, StringExpUtil.getCustomType((char) 178));
    }
    @Test
    public void getType45(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 181));
    }
    @Test
    public void getType46(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 182));
    }
    @Test
    public void getType47(){
        assertEq(StringExpUtil.MODIFIER, StringExpUtil.getCustomType((char) 184));
    }
    @Test
    public void getType48(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 186));
    }
    @Test
    public void getType49(){
        assertEq(StringExpUtil.PUNCTUATION_QUOTE, StringExpUtil.getCustomType((char) 187));
    }
    @Test
    public void getType50(){
        assertEq(StringExpUtil.MODIFIER, StringExpUtil.getCustomType((char) 188));
    }
    @Test
    public void getType51(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 191));
    }
    @Test
    public void getType52(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 192));
    }
    @Test
    public void getType53(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 215));
    }
    @Test
    public void getType54(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 216));
    }
    @Test
    public void getType55(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 223));
    }
    @Test
    public void getType56(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 224));
    }
    @Test
    public void getType57(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 247));
    }
    @Test
    public void getType58(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 248));
    }
    @Test
    public void getType59(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 256));
    }
    @Test
    public void getType60(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 257));
    }
    @Test
    public void getType61(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 258));
    }
    @Test
    public void getType62(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 259));
    }
    @Test
    public void getType63(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 260));
    }
    @Test
    public void getType64(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 261));
    }
    @Test
    public void getType65(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 262));
    }
    @Test
    public void getType66(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 263));
    }
    @Test
    public void getType67(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 264));
    }
    @Test
    public void getType68(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 265));
    }
    @Test
    public void getType69(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 266));
    }
    @Test
    public void getType70(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 267));
    }
    @Test
    public void getType71(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 268));
    }
    @Test
    public void getType72(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 269));
    }
    @Test
    public void getType73(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 270));
    }
    @Test
    public void getType74(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 271));
    }
    @Test
    public void getType75(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 272));
    }
    @Test
    public void getType76(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 273));
    }
    @Test
    public void getType77(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 274));
    }
    @Test
    public void getType78(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 275));
    }
    @Test
    public void getType79(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 276));
    }
    @Test
    public void getType80(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 277));
    }
    @Test
    public void getType81(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 278));
    }
    @Test
    public void getType82(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 279));
    }
    @Test
    public void getType83(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 280));
    }
    @Test
    public void getType84(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 281));
    }
    @Test
    public void getType85(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 282));
    }
    @Test
    public void getType86(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 283));
    }
    @Test
    public void getType87(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 284));
    }
    @Test
    public void getType88(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 285));
    }
    @Test
    public void getType89(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 286));
    }
    @Test
    public void getType90(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 287));
    }
    @Test
    public void getType91(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 288));
    }
    @Test
    public void getType92(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 289));
    }
    @Test
    public void getType93(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 290));
    }
    @Test
    public void getType94(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 291));
    }
    @Test
    public void getType95(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 292));
    }
    @Test
    public void getType96(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 293));
    }
    @Test
    public void getType97(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 294));
    }
    @Test
    public void getType98(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 295));
    }
    @Test
    public void getType99(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 296));
    }
    @Test
    public void getType100(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 297));
    }
    @Test
    public void getType101(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 298));
    }
    @Test
    public void getType102(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 299));
    }
    @Test
    public void getType103(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 300));
    }
    @Test
    public void getType104(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 301));
    }
    @Test
    public void getType105(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 302));
    }
    @Test
    public void getType106(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 303));
    }
    @Test
    public void getType107(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 304));
    }
    @Test
    public void getType108(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 305));
    }
    @Test
    public void getType109(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 306));
    }
    @Test
    public void getType110(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 307));
    }
    @Test
    public void getType111(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 308));
    }
    @Test
    public void getType112(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 309));
    }
    @Test
    public void getType113(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 310));
    }
    @Test
    public void getType114(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 311));
    }
    @Test
    public void getType115(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 312));
    }
    @Test
    public void getType116(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 313));
    }
    @Test
    public void getType117(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 314));
    }
    @Test
    public void getType118(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 315));
    }
    @Test
    public void getType119(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 316));
    }
    @Test
    public void getType120(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 317));
    }
    @Test
    public void getType121(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 318));
    }
    @Test
    public void getType122(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 319));
    }
    @Test
    public void getType123(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 320));
    }
    @Test
    public void getType124(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 321));
    }
    @Test
    public void getType125(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 322));
    }
    @Test
    public void getType126(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 323));
    }
    @Test
    public void getType127(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 324));
    }
    @Test
    public void getType128(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 325));
    }
    @Test
    public void getType129(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 326));
    }
    @Test
    public void getType130(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 327));
    }
    @Test
    public void getType131(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 328));
    }
    @Test
    public void getType132(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 329));
    }
    @Test
    public void getType133(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 330));
    }
    @Test
    public void getType134(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 331));
    }
    @Test
    public void getType135(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 332));
    }
    @Test
    public void getType136(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 333));
    }
    @Test
    public void getType137(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 334));
    }
    @Test
    public void getType138(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 335));
    }
    @Test
    public void getType139(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 336));
    }
    @Test
    public void getType140(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 337));
    }
    @Test
    public void getType141(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 338));
    }
    @Test
    public void getType142(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 339));
    }
    @Test
    public void getType143(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 340));
    }
    @Test
    public void getType144(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 341));
    }
    @Test
    public void getType145(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 342));
    }
    @Test
    public void getType146(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 343));
    }
    @Test
    public void getType147(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 344));
    }
    @Test
    public void getType148(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 345));
    }
    @Test
    public void getType149(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 346));
    }
    @Test
    public void getType150(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 347));
    }
    @Test
    public void getType151(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 348));
    }
    @Test
    public void getType152(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 349));
    }
    @Test
    public void getType153(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 350));
    }
    @Test
    public void getType154(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 351));
    }
    @Test
    public void getType155(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 352));
    }
    @Test
    public void getType156(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 353));
    }
    @Test
    public void getType157(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 354));
    }
    @Test
    public void getType158(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 355));
    }
    @Test
    public void getType159(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 356));
    }
    @Test
    public void getType160(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 357));
    }
    @Test
    public void getType161(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 358));
    }
    @Test
    public void getType162(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 359));
    }
    @Test
    public void getType163(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 360));
    }
    @Test
    public void getType164(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 361));
    }
    @Test
    public void getType165(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 362));
    }
    @Test
    public void getType166(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 363));
    }
    @Test
    public void getType167(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 364));
    }
    @Test
    public void getType168(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 365));
    }
    @Test
    public void getType169(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 366));
    }
    @Test
    public void getType170(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 367));
    }
    @Test
    public void getType171(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 368));
    }
    @Test
    public void getType172(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 369));
    }
    @Test
    public void getType173(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 370));
    }
    @Test
    public void getType174(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 371));
    }
    @Test
    public void getType175(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 372));
    }
    @Test
    public void getType176(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 373));
    }
    @Test
    public void getType177(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 374));
    }
    @Test
    public void getType178(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 375));
    }
    @Test
    public void getType179(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 376));
    }
    @Test
    public void getType180(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 378));
    }
    @Test
    public void getType181(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 379));
    }
    @Test
    public void getType182(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 380));
    }
    @Test
    public void getType183(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 381));
    }
    @Test
    public void getType184(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 382));
    }
    @Test
    public void getType185(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 385));
    }
    @Test
    public void getType186(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 387));
    }
    @Test
    public void getType187(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 388));
    }
    @Test
    public void getType188(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 389));
    }
    @Test
    public void getType189(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 390));
    }
    @Test
    public void getType190(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 392));
    }
    @Test
    public void getType191(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 393));
    }
    @Test
    public void getType192(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 396));
    }
    @Test
    public void getType193(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 397));
    }
    @Test
    public void getType194(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 398));
    }
    @Test
    public void getType195(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 402));
    }
    @Test
    public void getType196(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 403));
    }
    @Test
    public void getType197(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 405));
    }
    @Test
    public void getType198(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 406));
    }
    @Test
    public void getType199(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 409));
    }
    @Test
    public void getType200(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 411));
    }
    @Test
    public void getType201(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 412));
    }
    @Test
    public void getType202(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 414));
    }
    @Test
    public void getType203(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 415));
    }
    @Test
    public void getType204(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 417));
    }
    @Test
    public void getType205(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 418));
    }
    @Test
    public void getType206(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 419));
    }
    @Test
    public void getType207(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 420));
    }
    @Test
    public void getType208(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 421));
    }
    @Test
    public void getType209(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 422));
    }
    @Test
    public void getType210(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 424));
    }
    @Test
    public void getType211(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 425));
    }
    @Test
    public void getType212(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 426));
    }
    @Test
    public void getType213(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 428));
    }
    @Test
    public void getType214(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 429));
    }
    @Test
    public void getType215(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 430));
    }
    @Test
    public void getType216(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 432));
    }
    @Test
    public void getType217(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 433));
    }
    @Test
    public void getType218(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 436));
    }
    @Test
    public void getType219(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 437));
    }
    @Test
    public void getType220(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 438));
    }
    @Test
    public void getType221(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 439));
    }
    @Test
    public void getType222(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 441));
    }
    @Test
    public void getType223(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 442));
    }
    @Test
    public void getType224(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 443));
    }
    @Test
    public void getType225(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 444));
    }
    @Test
    public void getType226(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 445));
    }
    @Test
    public void getType227(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 446));
    }
    @Test
    public void getType228(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 447));
    }
    @Test
    public void getType229(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 448));
    }
    @Test
    public void getType230(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 452));
    }
    @Test
    public void getType231(){
        assertEq(StringExpUtil.LETTER_SENS_NO_CASE, StringExpUtil.getCustomType((char) 453));
    }
    @Test
    public void getType232(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 454));
    }
    @Test
    public void getType233(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 455));
    }
    @Test
    public void getType234(){
        assertEq(StringExpUtil.LETTER_SENS_NO_CASE, StringExpUtil.getCustomType((char) 456));
    }
    @Test
    public void getType235(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 457));
    }
    @Test
    public void getType236(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 458));
    }
    @Test
    public void getType237(){
        assertEq(StringExpUtil.LETTER_SENS_NO_CASE, StringExpUtil.getCustomType((char) 459));
    }
    @Test
    public void getType238(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 460));
    }
    @Test
    public void getType239(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 461));
    }
    @Test
    public void getType240(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 462));
    }
    @Test
    public void getType241(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 463));
    }
    @Test
    public void getType242(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 464));
    }
    @Test
    public void getType243(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 465));
    }
    @Test
    public void getType244(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 466));
    }
    @Test
    public void getType245(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 467));
    }
    @Test
    public void getType246(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 468));
    }
    @Test
    public void getType247(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 469));
    }
    @Test
    public void getType248(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 470));
    }
    @Test
    public void getType249(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 471));
    }
    @Test
    public void getType250(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 472));
    }
    @Test
    public void getType251(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 473));
    }
    @Test
    public void getType252(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 474));
    }
    @Test
    public void getType253(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 475));
    }
    @Test
    public void getType254(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 476));
    }
    @Test
    public void getType255(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 478));
    }
    @Test
    public void getType256(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 479));
    }
    @Test
    public void getType257(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 480));
    }
    @Test
    public void getType258(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 481));
    }
    @Test
    public void getType259(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 482));
    }
    @Test
    public void getType260(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 483));
    }
    @Test
    public void getType261(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 484));
    }
    @Test
    public void getType262(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 485));
    }
    @Test
    public void getType263(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 486));
    }
    @Test
    public void getType264(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 487));
    }
    @Test
    public void getType265(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 488));
    }
    @Test
    public void getType266(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 489));
    }
    @Test
    public void getType267(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 490));
    }
    @Test
    public void getType268(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 491));
    }
    @Test
    public void getType269(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 492));
    }
    @Test
    public void getType270(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 493));
    }
    @Test
    public void getType271(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 494));
    }
    @Test
    public void getType272(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 495));
    }
    @Test
    public void getType273(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 496));
    }
    @Test
    public void getType274(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 497));
    }
    @Test
    public void getType275(){
        assertEq(StringExpUtil.LETTER_SENS_NO_CASE, StringExpUtil.getCustomType((char) 498));
    }
    @Test
    public void getType276(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 499));
    }
    @Test
    public void getType277(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 500));
    }
    @Test
    public void getType278(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 501));
    }
    @Test
    public void getType279(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 502));
    }
    @Test
    public void getType280(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 505));
    }
    @Test
    public void getType281(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 506));
    }
    @Test
    public void getType282(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 507));
    }
    @Test
    public void getType283(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 508));
    }
    @Test
    public void getType284(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 509));
    }
    @Test
    public void getType285(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 510));
    }
    @Test
    public void getType286(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 511));
    }
    @Test
    public void getType287(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 512));
    }
    @Test
    public void getType288(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 513));
    }
    @Test
    public void getType289(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 514));
    }
    @Test
    public void getType290(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 515));
    }
    @Test
    public void getType291(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 516));
    }
    @Test
    public void getType292(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 517));
    }
    @Test
    public void getType293(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 518));
    }
    @Test
    public void getType294(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 519));
    }
    @Test
    public void getType295(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 520));
    }
    @Test
    public void getType296(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 521));
    }
    @Test
    public void getType297(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 522));
    }
    @Test
    public void getType298(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 523));
    }
    @Test
    public void getType299(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 524));
    }
    @Test
    public void getType300(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 525));
    }
    @Test
    public void getType301(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 526));
    }
    @Test
    public void getType302(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 527));
    }
    @Test
    public void getType303(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 528));
    }
    @Test
    public void getType304(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 529));
    }
    @Test
    public void getType305(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 530));
    }
    @Test
    public void getType306(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 531));
    }
    @Test
    public void getType307(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 532));
    }
    @Test
    public void getType308(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 533));
    }
    @Test
    public void getType309(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 534));
    }
    @Test
    public void getType310(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 535));
    }
    @Test
    public void getType311(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 536));
    }
    @Test
    public void getType312(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 537));
    }
    @Test
    public void getType313(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 538));
    }
    @Test
    public void getType314(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 539));
    }
    @Test
    public void getType315(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 540));
    }
    @Test
    public void getType316(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 541));
    }
    @Test
    public void getType317(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 542));
    }
    @Test
    public void getType318(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 543));
    }
    @Test
    public void getType319(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 544));
    }
    @Test
    public void getType320(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 545));
    }
    @Test
    public void getType321(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 546));
    }
    @Test
    public void getType322(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 547));
    }
    @Test
    public void getType323(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 548));
    }
    @Test
    public void getType324(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 549));
    }
    @Test
    public void getType325(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 550));
    }
    @Test
    public void getType326(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 551));
    }
    @Test
    public void getType327(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 552));
    }
    @Test
    public void getType328(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 553));
    }
    @Test
    public void getType329(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 554));
    }
    @Test
    public void getType330(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 555));
    }
    @Test
    public void getType331(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 556));
    }
    @Test
    public void getType332(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 557));
    }
    @Test
    public void getType333(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 558));
    }
    @Test
    public void getType334(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 559));
    }
    @Test
    public void getType335(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 560));
    }
    @Test
    public void getType336(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 561));
    }
    @Test
    public void getType337(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 562));
    }
    @Test
    public void getType338(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 563));
    }
    @Test
    public void getType339(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 564));
    }
    @Test
    public void getType340(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 570));
    }
    @Test
    public void getType341(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 572));
    }
    @Test
    public void getType342(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 573));
    }
    @Test
    public void getType343(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 575));
    }
    @Test
    public void getType344(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 577));
    }
    @Test
    public void getType345(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 578));
    }
    @Test
    public void getType346(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 579));
    }
    @Test
    public void getType347(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 583));
    }
    @Test
    public void getType348(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 584));
    }
    @Test
    public void getType349(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 585));
    }
    @Test
    public void getType350(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 586));
    }
    @Test
    public void getType351(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 587));
    }
    @Test
    public void getType352(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 588));
    }
    @Test
    public void getType353(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 589));
    }
    @Test
    public void getType354(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 590));
    }
    @Test
    public void getType355(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 591));
    }
    @Test
    public void getType356(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 597));
    }
    @Test
    public void getType357(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 598));
    }
    @Test
    public void getType358(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 600));
    }
    @Test
    public void getType359(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 601));
    }
    @Test
    public void getType360(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 602));
    }
    @Test
    public void getType361(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 603));
    }
    @Test
    public void getType362(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 604));
    }
    @Test
    public void getType363(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 608));
    }
    @Test
    public void getType364(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 609));
    }
    @Test
    public void getType365(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 611));
    }
    @Test
    public void getType366(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 612));
    }
    @Test
    public void getType367(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 613));
    }
    @Test
    public void getType368(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 615));
    }
    @Test
    public void getType369(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 616));
    }
    @Test
    public void getType370(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 618));
    }
    @Test
    public void getType371(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 619));
    }
    @Test
    public void getType372(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 620));
    }
    @Test
    public void getType373(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 623));
    }
    @Test
    public void getType374(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 624));
    }
    @Test
    public void getType375(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 625));
    }
    @Test
    public void getType376(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 627));
    }
    @Test
    public void getType377(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 629));
    }
    @Test
    public void getType378(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 630));
    }
    @Test
    public void getType379(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 637));
    }
    @Test
    public void getType380(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 638));
    }
    @Test
    public void getType381(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 640));
    }
    @Test
    public void getType382(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 641));
    }
    @Test
    public void getType383(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 643));
    }
    @Test
    public void getType384(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 644));
    }
    @Test
    public void getType385(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 648));
    }
    @Test
    public void getType386(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 653));
    }
    @Test
    public void getType387(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 658));
    }
    @Test
    public void getType388(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 659));
    }
    @Test
    public void getType389(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 660));
    }
    @Test
    public void getType390(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 661));
    }
    @Test
    public void getType391(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringExpUtil.getCustomType((char) 697));
    }
    @Test
    public void getType392(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 699));
    }
    @Test
    public void getType393(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 704));
    }
    @Test
    public void getType394(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 706));
    }
    @Test
    public void getType395(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringExpUtil.getCustomType((char) 710));
    }
    @Test
    public void getType396(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 720));
    }
    @Test
    public void getType397(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 722));
    }
    @Test
    public void getType398(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 736));
    }
    @Test
    public void getType399(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 741));
    }
    @Test
    public void getType400(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringExpUtil.getCustomType((char) 748));
    }
    @Test
    public void getType401(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 749));
    }
    @Test
    public void getType402(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 750));
    }
    @Test
    public void getType403(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 751));
    }
    @Test
    public void getType404(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 768));
    }
    @Test
    public void getType405(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 880));
    }
    @Test
    public void getType406(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 881));
    }
    @Test
    public void getType407(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 882));
    }
    @Test
    public void getType408(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 883));
    }
    @Test
    public void getType409(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringExpUtil.getCustomType((char) 884));
    }
    @Test
    public void getType410(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 885));
    }
    @Test
    public void getType411(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 886));
    }
    @Test
    public void getType412(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 887));
    }
    @Test
    public void getType413(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 888));
    }
    @Test
    public void getType414(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 890));
    }
    @Test
    public void getType415(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 891));
    }
    @Test
    public void getType416(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 894));
    }
    @Test
    public void getType417(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 895));
    }
    @Test
    public void getType418(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 900));
    }
    @Test
    public void getType419(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 902));
    }
    @Test
    public void getType420(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 903));
    }
    @Test
    public void getType421(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 904));
    }
    @Test
    public void getType422(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 907));
    }
    @Test
    public void getType423(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 908));
    }
    @Test
    public void getType424(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 909));
    }
    @Test
    public void getType425(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 910));
    }
    @Test
    public void getType426(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 912));
    }
    @Test
    public void getType427(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 913));
    }
    @Test
    public void getType428(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 930));
    }
    @Test
    public void getType429(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 931));
    }
    @Test
    public void getType430(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 940));
    }
    @Test
    public void getType431(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 944));
    }
    @Test
    public void getType432(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 945));
    }
    @Test
    public void getType433(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 975));
    }
    @Test
    public void getType434(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 976));
    }
    @Test
    public void getType435(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 978));
    }
    @Test
    public void getType436(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 981));
    }
    @Test
    public void getType437(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 984));
    }
    @Test
    public void getType438(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 985));
    }
    @Test
    public void getType439(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 986));
    }
    @Test
    public void getType440(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 987));
    }
    @Test
    public void getType441(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 988));
    }
    @Test
    public void getType442(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 989));
    }
    @Test
    public void getType443(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 990));
    }
    @Test
    public void getType444(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 991));
    }
    @Test
    public void getType445(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 992));
    }
    @Test
    public void getType446(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 993));
    }
    @Test
    public void getType447(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 994));
    }
    @Test
    public void getType448(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 995));
    }
    @Test
    public void getType449(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 996));
    }
    @Test
    public void getType450(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 997));
    }
    @Test
    public void getType451(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 998));
    }
    @Test
    public void getType452(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 999));
    }
    @Test
    public void getType453(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1000));
    }
    @Test
    public void getType454(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1001));
    }
    @Test
    public void getType455(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1002));
    }
    @Test
    public void getType456(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1003));
    }
    @Test
    public void getType457(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1004));
    }
    @Test
    public void getType458(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1005));
    }
    @Test
    public void getType459(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1006));
    }
    @Test
    public void getType460(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1007));
    }
    @Test
    public void getType461(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1011));
    }
    @Test
    public void getType462(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1012));
    }
    @Test
    public void getType463(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1013));
    }
    @Test
    public void getType464(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 1014));
    }
    @Test
    public void getType465(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1015));
    }
    @Test
    public void getType466(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1016));
    }
    @Test
    public void getType467(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1017));
    }
    @Test
    public void getType468(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1019));
    }
    @Test
    public void getType469(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1020));
    }
    @Test
    public void getType470(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1021));
    }
    @Test
    public void getType471(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1072));
    }
    @Test
    public void getType472(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1120));
    }
    @Test
    public void getType473(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1121));
    }
    @Test
    public void getType474(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1122));
    }
    @Test
    public void getType475(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1123));
    }
    @Test
    public void getType476(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1124));
    }
    @Test
    public void getType477(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1125));
    }
    @Test
    public void getType478(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1126));
    }
    @Test
    public void getType479(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1127));
    }
    @Test
    public void getType480(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1128));
    }
    @Test
    public void getType481(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1129));
    }
    @Test
    public void getType482(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1130));
    }
    @Test
    public void getType483(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1131));
    }
    @Test
    public void getType484(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1132));
    }
    @Test
    public void getType485(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1133));
    }
    @Test
    public void getType486(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1134));
    }
    @Test
    public void getType487(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1135));
    }
    @Test
    public void getType488(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1136));
    }
    @Test
    public void getType489(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1137));
    }
    @Test
    public void getType490(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1138));
    }
    @Test
    public void getType491(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1139));
    }
    @Test
    public void getType492(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1140));
    }
    @Test
    public void getType493(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1141));
    }
    @Test
    public void getType494(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1142));
    }
    @Test
    public void getType495(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1143));
    }
    @Test
    public void getType496(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1144));
    }
    @Test
    public void getType497(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1145));
    }
    @Test
    public void getType498(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1146));
    }
    @Test
    public void getType499(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1147));
    }
    @Test
    public void getType500(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1148));
    }
    @Test
    public void getType501(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1149));
    }
    @Test
    public void getType502(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1150));
    }
    @Test
    public void getType503(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1151));
    }
    @Test
    public void getType504(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1152));
    }
    @Test
    public void getType505(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1153));
    }
    @Test
    public void getType506(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 1154));
    }
    @Test
    public void getType507(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1155));
    }
    @Test
    public void getType508(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1162));
    }
    @Test
    public void getType509(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1163));
    }
    @Test
    public void getType510(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1164));
    }
    @Test
    public void getType511(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1165));
    }
    @Test
    public void getType512(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1166));
    }
    @Test
    public void getType513(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1167));
    }
    @Test
    public void getType514(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1168));
    }
    @Test
    public void getType515(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1169));
    }
    @Test
    public void getType516(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1170));
    }
    @Test
    public void getType517(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1171));
    }
    @Test
    public void getType518(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1172));
    }
    @Test
    public void getType519(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1173));
    }
    @Test
    public void getType520(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1174));
    }
    @Test
    public void getType521(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1175));
    }
    @Test
    public void getType522(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1176));
    }
    @Test
    public void getType523(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1177));
    }
    @Test
    public void getType524(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1178));
    }
    @Test
    public void getType525(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1179));
    }
    @Test
    public void getType526(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1180));
    }
    @Test
    public void getType527(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1181));
    }
    @Test
    public void getType528(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1182));
    }
    @Test
    public void getType529(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1183));
    }
    @Test
    public void getType530(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1184));
    }
    @Test
    public void getType531(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1185));
    }
    @Test
    public void getType532(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1186));
    }
    @Test
    public void getType533(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1187));
    }
    @Test
    public void getType534(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1188));
    }
    @Test
    public void getType535(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1189));
    }
    @Test
    public void getType536(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1190));
    }
    @Test
    public void getType537(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1191));
    }
    @Test
    public void getType538(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1192));
    }
    @Test
    public void getType539(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1193));
    }
    @Test
    public void getType540(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1194));
    }
    @Test
    public void getType541(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1195));
    }
    @Test
    public void getType542(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1196));
    }
    @Test
    public void getType543(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1197));
    }
    @Test
    public void getType544(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1198));
    }
    @Test
    public void getType545(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1199));
    }
    @Test
    public void getType546(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1200));
    }
    @Test
    public void getType547(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1201));
    }
    @Test
    public void getType548(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1202));
    }
    @Test
    public void getType549(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1203));
    }
    @Test
    public void getType550(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1204));
    }
    @Test
    public void getType551(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1205));
    }
    @Test
    public void getType552(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1206));
    }
    @Test
    public void getType553(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1207));
    }
    @Test
    public void getType554(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1208));
    }
    @Test
    public void getType555(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1209));
    }
    @Test
    public void getType556(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1210));
    }
    @Test
    public void getType557(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1211));
    }
    @Test
    public void getType558(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1212));
    }
    @Test
    public void getType559(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1213));
    }
    @Test
    public void getType560(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1214));
    }
    @Test
    public void getType561(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1215));
    }
    @Test
    public void getType562(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1216));
    }
    @Test
    public void getType563(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1218));
    }
    @Test
    public void getType564(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1219));
    }
    @Test
    public void getType565(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1220));
    }
    @Test
    public void getType566(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1221));
    }
    @Test
    public void getType567(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1222));
    }
    @Test
    public void getType568(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1223));
    }
    @Test
    public void getType569(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1224));
    }
    @Test
    public void getType570(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1225));
    }
    @Test
    public void getType571(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1226));
    }
    @Test
    public void getType572(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1227));
    }
    @Test
    public void getType573(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1228));
    }
    @Test
    public void getType574(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1229));
    }
    @Test
    public void getType575(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1230));
    }
    @Test
    public void getType576(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1232));
    }
    @Test
    public void getType577(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1233));
    }
    @Test
    public void getType578(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1234));
    }
    @Test
    public void getType579(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1235));
    }
    @Test
    public void getType580(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1236));
    }
    @Test
    public void getType581(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1237));
    }
    @Test
    public void getType582(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1238));
    }
    @Test
    public void getType583(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1239));
    }
    @Test
    public void getType584(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1240));
    }
    @Test
    public void getType585(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1241));
    }
    @Test
    public void getType586(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1242));
    }
    @Test
    public void getType587(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1243));
    }
    @Test
    public void getType588(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1244));
    }
    @Test
    public void getType589(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1245));
    }
    @Test
    public void getType590(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1246));
    }
    @Test
    public void getType591(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1247));
    }
    @Test
    public void getType592(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1248));
    }
    @Test
    public void getType593(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1249));
    }
    @Test
    public void getType594(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1250));
    }
    @Test
    public void getType595(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1251));
    }
    @Test
    public void getType596(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1252));
    }
    @Test
    public void getType597(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1253));
    }
    @Test
    public void getType598(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1254));
    }
    @Test
    public void getType599(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1255));
    }
    @Test
    public void getType600(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1256));
    }
    @Test
    public void getType601(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1257));
    }
    @Test
    public void getType602(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1258));
    }
    @Test
    public void getType603(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1259));
    }
    @Test
    public void getType604(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1260));
    }
    @Test
    public void getType605(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1261));
    }
    @Test
    public void getType606(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1262));
    }
    @Test
    public void getType607(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1263));
    }
    @Test
    public void getType608(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1264));
    }
    @Test
    public void getType609(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1265));
    }
    @Test
    public void getType610(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1266));
    }
    @Test
    public void getType611(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1267));
    }
    @Test
    public void getType612(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1268));
    }
    @Test
    public void getType613(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1269));
    }
    @Test
    public void getType614(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1270));
    }
    @Test
    public void getType615(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1271));
    }
    @Test
    public void getType616(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1272));
    }
    @Test
    public void getType617(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1273));
    }
    @Test
    public void getType618(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1274));
    }
    @Test
    public void getType619(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1275));
    }
    @Test
    public void getType620(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1276));
    }
    @Test
    public void getType621(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1277));
    }
    @Test
    public void getType622(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1278));
    }
    @Test
    public void getType623(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1279));
    }
    @Test
    public void getType624(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1280));
    }
    @Test
    public void getType625(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1281));
    }
    @Test
    public void getType626(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1282));
    }
    @Test
    public void getType627(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1283));
    }
    @Test
    public void getType628(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1284));
    }
    @Test
    public void getType629(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1285));
    }
    @Test
    public void getType630(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1286));
    }
    @Test
    public void getType631(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1287));
    }
    @Test
    public void getType632(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1288));
    }
    @Test
    public void getType633(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1289));
    }
    @Test
    public void getType634(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1290));
    }
    @Test
    public void getType635(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1291));
    }
    @Test
    public void getType636(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1292));
    }
    @Test
    public void getType637(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1293));
    }
    @Test
    public void getType638(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1294));
    }
    @Test
    public void getType639(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1295));
    }
    @Test
    public void getType640(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1296));
    }
    @Test
    public void getType641(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1297));
    }
    @Test
    public void getType642(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1298));
    }
    @Test
    public void getType643(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1299));
    }
    @Test
    public void getType644(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1300));
    }
    @Test
    public void getType645(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1301));
    }
    @Test
    public void getType646(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1302));
    }
    @Test
    public void getType647(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1303));
    }
    @Test
    public void getType648(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1304));
    }
    @Test
    public void getType649(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1305));
    }
    @Test
    public void getType650(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1306));
    }
    @Test
    public void getType651(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1307));
    }
    @Test
    public void getType652(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1308));
    }
    @Test
    public void getType653(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1309));
    }
    @Test
    public void getType654(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1310));
    }
    @Test
    public void getType655(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1311));
    }
    @Test
    public void getType656(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1312));
    }
    @Test
    public void getType657(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1313));
    }
    @Test
    public void getType658(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1314));
    }
    @Test
    public void getType659(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1315));
    }
    @Test
    public void getType660(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1316));
    }
    @Test
    public void getType661(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1317));
    }
    @Test
    public void getType662(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1318));
    }
    @Test
    public void getType663(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1319));
    }
    @Test
    public void getType664(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1320));
    }
    @Test
    public void getType665(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 1329));
    }
    @Test
    public void getType666(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1367));
    }
    @Test
    public void getType667(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 1369));
    }
    @Test
    public void getType668(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1370));
    }
    @Test
    public void getType669(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1376));
    }
    @Test
    public void getType670(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1377));
    }
    @Test
    public void getType671(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 1415));
    }
    @Test
    public void getType672(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1416));
    }
    @Test
    public void getType673(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1417));
    }
    @Test
    public void getType674(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 1418));
    }
    @Test
    public void getType675(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1419));
    }
    @Test
    public void getType676(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 1423));
    }
    @Test
    public void getType677(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1424));
    }
    @Test
    public void getType678(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1425));
    }
    @Test
    public void getType679(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 1470));
    }
    @Test
    public void getType680(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1471));
    }
    @Test
    public void getType681(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1472));
    }
    @Test
    public void getType682(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1473));
    }
    @Test
    public void getType683(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1475));
    }
    @Test
    public void getType684(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1476));
    }
    @Test
    public void getType685(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1478));
    }
    @Test
    public void getType686(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1479));
    }
    @Test
    public void getType687(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1480));
    }
    @Test
    public void getType688(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1488));
    }
    @Test
    public void getType689(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1515));
    }
    @Test
    public void getType690(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1520));
    }
    @Test
    public void getType691(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1523));
    }
    @Test
    public void getType692(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1525));
    }
    @Test
    public void getType693(){
        assertEq(StringExpUtil.SEPARATOR, StringExpUtil.getCustomType((char) 1536));
    }
    @Test
    public void getType694(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1541));
    }
    @Test
    public void getType695(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 1542));
    }
    @Test
    public void getType696(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1545));
    }
    @Test
    public void getType697(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 1547));
    }
    @Test
    public void getType698(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1548));
    }
    @Test
    public void getType699(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 1550));
    }
    @Test
    public void getType700(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1552));
    }
    @Test
    public void getType701(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1563));
    }
    @Test
    public void getType702(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1564));
    }
    @Test
    public void getType703(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1566));
    }
    @Test
    public void getType704(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1568));
    }
    @Test
    public void getType705(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1611));
    }
    @Test
    public void getType706(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 1632));
    }
    @Test
    public void getType707(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1642));
    }
    @Test
    public void getType708(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1646));
    }
    @Test
    public void getType709(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1648));
    }
    @Test
    public void getType710(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1649));
    }
    @Test
    public void getType711(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1748));
    }
    @Test
    public void getType712(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1749));
    }
    @Test
    public void getType713(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1750));
    }
    @Test
    public void getType714(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 1758));
    }
    @Test
    public void getType715(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1759));
    }
    @Test
    public void getType716(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1765));
    }
    @Test
    public void getType717(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1767));
    }
    @Test
    public void getType718(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 1769));
    }
    @Test
    public void getType719(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1770));
    }
    @Test
    public void getType720(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1774));
    }
    @Test
    public void getType721(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 1776));
    }
    @Test
    public void getType722(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1786));
    }
    @Test
    public void getType723(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 1789));
    }
    @Test
    public void getType724(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1791));
    }
    @Test
    public void getType725(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 1792));
    }
    @Test
    public void getType726(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1806));
    }
    @Test
    public void getType727(){
        assertEq(StringExpUtil.SEPARATOR, StringExpUtil.getCustomType((char) 1807));
    }
    @Test
    public void getType728(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1808));
    }
    @Test
    public void getType729(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1809));
    }
    @Test
    public void getType730(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1810));
    }
    @Test
    public void getType731(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1840));
    }
    @Test
    public void getType732(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1867));
    }
    @Test
    public void getType733(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1869));
    }
    @Test
    public void getType734(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 1958));
    }
    @Test
    public void getType735(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1969));
    }
    @Test
    public void getType736(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 1970));
    }
    @Test
    public void getType737(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 1984));
    }
    @Test
    public void getType738(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 1994));
    }
    @Test
    public void getType739(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2027));
    }
    @Test
    public void getType740(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 2036));
    }
    @Test
    public void getType741(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 2038));
    }
    @Test
    public void getType742(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 2039));
    }
    @Test
    public void getType743(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 2042));
    }
    @Test
    public void getType744(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2043));
    }
    @Test
    public void getType745(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 2048));
    }
    @Test
    public void getType746(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2070));
    }
    @Test
    public void getType747(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 2074));
    }
    @Test
    public void getType748(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2075));
    }
    @Test
    public void getType749(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 2084));
    }
    @Test
    public void getType750(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2085));
    }
    @Test
    public void getType751(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 2088));
    }
    @Test
    public void getType752(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2089));
    }
    @Test
    public void getType753(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2094));
    }
    @Test
    public void getType754(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 2096));
    }
    @Test
    public void getType755(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2111));
    }
    @Test
    public void getType756(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 2112));
    }
    @Test
    public void getType757(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2137));
    }
    @Test
    public void getType758(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2140));
    }
    @Test
    public void getType759(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 2142));
    }
    @Test
    public void getType760(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2143));
    }
    @Test
    public void getType761(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 2208));
    }
    @Test
    public void getType762(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2209));
    }
    @Test
    public void getType763(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 2210));
    }
    @Test
    public void getType764(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2221));
    }
    @Test
    public void getType765(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2276));
    }
    @Test
    public void getType766(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2303));
    }
    @Test
    public void getType767(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2304));
    }
    @Test
    public void getType768(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2308));
    }
    @Test
    public void getType769(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2362));
    }
    @Test
    public void getType770(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2365));
    }
    @Test
    public void getType771(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2366));
    }
    @Test
    public void getType772(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2384));
    }
    @Test
    public void getType773(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2385));
    }
    @Test
    public void getType774(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2392));
    }
    @Test
    public void getType775(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2402));
    }
    @Test
    public void getType776(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 2404));
    }
    @Test
    public void getType777(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 2406));
    }
    @Test
    public void getType778(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 2416));
    }
    @Test
    public void getType779(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2417));
    }
    @Test
    public void getType780(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2424));
    }
    @Test
    public void getType781(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2425));
    }
    @Test
    public void getType782(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2432));
    }
    @Test
    public void getType783(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2433));
    }
    @Test
    public void getType784(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2436));
    }
    @Test
    public void getType785(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2437));
    }
    @Test
    public void getType786(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2445));
    }
    @Test
    public void getType787(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2447));
    }
    @Test
    public void getType788(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2449));
    }
    @Test
    public void getType789(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2451));
    }
    @Test
    public void getType790(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2473));
    }
    @Test
    public void getType791(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2474));
    }
    @Test
    public void getType792(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2481));
    }
    @Test
    public void getType793(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2482));
    }
    @Test
    public void getType794(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2483));
    }
    @Test
    public void getType795(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2486));
    }
    @Test
    public void getType796(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2490));
    }
    @Test
    public void getType797(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2492));
    }
    @Test
    public void getType798(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2493));
    }
    @Test
    public void getType799(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2494));
    }
    @Test
    public void getType800(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2501));
    }
    @Test
    public void getType801(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2503));
    }
    @Test
    public void getType802(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2505));
    }
    @Test
    public void getType803(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2507));
    }
    @Test
    public void getType804(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2510));
    }
    @Test
    public void getType805(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2511));
    }
    @Test
    public void getType806(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2519));
    }
    @Test
    public void getType807(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2520));
    }
    @Test
    public void getType808(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2524));
    }
    @Test
    public void getType809(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2526));
    }
    @Test
    public void getType810(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2527));
    }
    @Test
    public void getType811(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2530));
    }
    @Test
    public void getType812(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2532));
    }
    @Test
    public void getType813(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 2534));
    }
    @Test
    public void getType814(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2544));
    }
    @Test
    public void getType815(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 2546));
    }
    @Test
    public void getType816(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 2548));
    }
    @Test
    public void getType817(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 2554));
    }
    @Test
    public void getType818(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 2555));
    }
    @Test
    public void getType819(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2556));
    }
    @Test
    public void getType820(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2561));
    }
    @Test
    public void getType821(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2564));
    }
    @Test
    public void getType822(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2565));
    }
    @Test
    public void getType823(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2571));
    }
    @Test
    public void getType824(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2575));
    }
    @Test
    public void getType825(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2577));
    }
    @Test
    public void getType826(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2579));
    }
    @Test
    public void getType827(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2601));
    }
    @Test
    public void getType828(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2602));
    }
    @Test
    public void getType829(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2609));
    }
    @Test
    public void getType830(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2610));
    }
    @Test
    public void getType831(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2612));
    }
    @Test
    public void getType832(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2613));
    }
    @Test
    public void getType833(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2615));
    }
    @Test
    public void getType834(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2616));
    }
    @Test
    public void getType835(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2618));
    }
    @Test
    public void getType836(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2620));
    }
    @Test
    public void getType837(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2621));
    }
    @Test
    public void getType838(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2622));
    }
    @Test
    public void getType839(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2627));
    }
    @Test
    public void getType840(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2631));
    }
    @Test
    public void getType841(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2633));
    }
    @Test
    public void getType842(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2635));
    }
    @Test
    public void getType843(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2638));
    }
    @Test
    public void getType844(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2641));
    }
    @Test
    public void getType845(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2642));
    }
    @Test
    public void getType846(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2649));
    }
    @Test
    public void getType847(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2653));
    }
    @Test
    public void getType848(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2654));
    }
    @Test
    public void getType849(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2655));
    }
    @Test
    public void getType850(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 2662));
    }
    @Test
    public void getType851(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2672));
    }
    @Test
    public void getType852(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2674));
    }
    @Test
    public void getType853(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2677));
    }
    @Test
    public void getType854(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2678));
    }
    @Test
    public void getType855(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2689));
    }
    @Test
    public void getType856(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2692));
    }
    @Test
    public void getType857(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2693));
    }
    @Test
    public void getType858(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2702));
    }
    @Test
    public void getType859(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2703));
    }
    @Test
    public void getType860(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2706));
    }
    @Test
    public void getType861(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2707));
    }
    @Test
    public void getType862(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2729));
    }
    @Test
    public void getType863(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2730));
    }
    @Test
    public void getType864(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2737));
    }
    @Test
    public void getType865(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2738));
    }
    @Test
    public void getType866(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2740));
    }
    @Test
    public void getType867(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2741));
    }
    @Test
    public void getType868(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2746));
    }
    @Test
    public void getType869(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2748));
    }
    @Test
    public void getType870(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2749));
    }
    @Test
    public void getType871(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2750));
    }
    @Test
    public void getType872(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2758));
    }
    @Test
    public void getType873(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2759));
    }
    @Test
    public void getType874(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2762));
    }
    @Test
    public void getType875(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2763));
    }
    @Test
    public void getType876(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2766));
    }
    @Test
    public void getType877(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2768));
    }
    @Test
    public void getType878(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2769));
    }
    @Test
    public void getType879(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2784));
    }
    @Test
    public void getType880(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2786));
    }
    @Test
    public void getType881(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2788));
    }
    @Test
    public void getType882(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 2790));
    }
    @Test
    public void getType883(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 2800));
    }
    @Test
    public void getType884(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 2801));
    }
    @Test
    public void getType885(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2802));
    }
    @Test
    public void getType886(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2817));
    }
    @Test
    public void getType887(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2820));
    }
    @Test
    public void getType888(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2821));
    }
    @Test
    public void getType889(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2829));
    }
    @Test
    public void getType890(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2831));
    }
    @Test
    public void getType891(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2833));
    }
    @Test
    public void getType892(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2835));
    }
    @Test
    public void getType893(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2857));
    }
    @Test
    public void getType894(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2858));
    }
    @Test
    public void getType895(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2865));
    }
    @Test
    public void getType896(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2866));
    }
    @Test
    public void getType897(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2868));
    }
    @Test
    public void getType898(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2869));
    }
    @Test
    public void getType899(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2874));
    }
    @Test
    public void getType900(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2876));
    }
    @Test
    public void getType901(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2877));
    }
    @Test
    public void getType902(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2878));
    }
    @Test
    public void getType903(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2885));
    }
    @Test
    public void getType904(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2887));
    }
    @Test
    public void getType905(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2889));
    }
    @Test
    public void getType906(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2891));
    }
    @Test
    public void getType907(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2894));
    }
    @Test
    public void getType908(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2902));
    }
    @Test
    public void getType909(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2904));
    }
    @Test
    public void getType910(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2908));
    }
    @Test
    public void getType911(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2910));
    }
    @Test
    public void getType912(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2911));
    }
    @Test
    public void getType913(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2914));
    }
    @Test
    public void getType914(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2916));
    }
    @Test
    public void getType915(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 2918));
    }
    @Test
    public void getType916(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 2928));
    }
    @Test
    public void getType917(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2929));
    }
    @Test
    public void getType918(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 2930));
    }
    @Test
    public void getType919(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2936));
    }
    @Test
    public void getType920(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 2946));
    }
    @Test
    public void getType921(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2947));
    }
    @Test
    public void getType922(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2948));
    }
    @Test
    public void getType923(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2949));
    }
    @Test
    public void getType924(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2955));
    }
    @Test
    public void getType925(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2958));
    }
    @Test
    public void getType926(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2961));
    }
    @Test
    public void getType927(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2962));
    }
    @Test
    public void getType928(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2966));
    }
    @Test
    public void getType929(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2969));
    }
    @Test
    public void getType930(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2971));
    }
    @Test
    public void getType931(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2972));
    }
    @Test
    public void getType932(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2973));
    }
    @Test
    public void getType933(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2974));
    }
    @Test
    public void getType934(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2976));
    }
    @Test
    public void getType935(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2979));
    }
    @Test
    public void getType936(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2981));
    }
    @Test
    public void getType937(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2984));
    }
    @Test
    public void getType938(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 2987));
    }
    @Test
    public void getType939(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 2990));
    }
    @Test
    public void getType940(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3002));
    }
    @Test
    public void getType941(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3006));
    }
    @Test
    public void getType942(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3011));
    }
    @Test
    public void getType943(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3014));
    }
    @Test
    public void getType944(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3017));
    }
    @Test
    public void getType945(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3018));
    }
    @Test
    public void getType946(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3022));
    }
    @Test
    public void getType947(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3024));
    }
    @Test
    public void getType948(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3025));
    }
    @Test
    public void getType949(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3031));
    }
    @Test
    public void getType950(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3032));
    }
    @Test
    public void getType951(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 3046));
    }
    @Test
    public void getType952(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 3056));
    }
    @Test
    public void getType953(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 3059));
    }
    @Test
    public void getType954(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 3065));
    }
    @Test
    public void getType955(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 3066));
    }
    @Test
    public void getType956(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3067));
    }
    @Test
    public void getType957(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3073));
    }
    @Test
    public void getType958(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3076));
    }
    @Test
    public void getType959(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3077));
    }
    @Test
    public void getType960(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3085));
    }
    @Test
    public void getType961(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3086));
    }
    @Test
    public void getType962(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3089));
    }
    @Test
    public void getType963(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3090));
    }
    @Test
    public void getType964(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3113));
    }
    @Test
    public void getType965(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3114));
    }
    @Test
    public void getType966(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3124));
    }
    @Test
    public void getType967(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3125));
    }
    @Test
    public void getType968(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3130));
    }
    @Test
    public void getType969(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3133));
    }
    @Test
    public void getType970(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3134));
    }
    @Test
    public void getType971(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3141));
    }
    @Test
    public void getType972(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3142));
    }
    @Test
    public void getType973(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3145));
    }
    @Test
    public void getType974(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3146));
    }
    @Test
    public void getType975(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3150));
    }
    @Test
    public void getType976(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3157));
    }
    @Test
    public void getType977(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3159));
    }
    @Test
    public void getType978(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3160));
    }
    @Test
    public void getType979(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3162));
    }
    @Test
    public void getType980(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3168));
    }
    @Test
    public void getType981(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3170));
    }
    @Test
    public void getType982(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3172));
    }
    @Test
    public void getType983(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 3174));
    }
    @Test
    public void getType984(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3184));
    }
    @Test
    public void getType985(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 3192));
    }
    @Test
    public void getType986(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 3199));
    }
    @Test
    public void getType987(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3200));
    }
    @Test
    public void getType988(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3202));
    }
    @Test
    public void getType989(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3204));
    }
    @Test
    public void getType990(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3205));
    }
    @Test
    public void getType991(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3213));
    }
    @Test
    public void getType992(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3214));
    }
    @Test
    public void getType993(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3217));
    }
    @Test
    public void getType994(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3218));
    }
    @Test
    public void getType995(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3241));
    }
    @Test
    public void getType996(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3242));
    }
    @Test
    public void getType997(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3252));
    }
    @Test
    public void getType998(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3253));
    }
    @Test
    public void getType999(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3258));
    }
    @Test
    public void getType1000(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3260));
    }
    @Test
    public void getType1001(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3261));
    }
    @Test
    public void getType1002(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3262));
    }
    @Test
    public void getType1003(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3269));
    }
    @Test
    public void getType1004(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3270));
    }
    @Test
    public void getType1005(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3273));
    }
    @Test
    public void getType1006(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3274));
    }
    @Test
    public void getType1007(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3278));
    }
    @Test
    public void getType1008(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3285));
    }
    @Test
    public void getType1009(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3287));
    }
    @Test
    public void getType1010(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3294));
    }
    @Test
    public void getType1011(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3295));
    }
    @Test
    public void getType1012(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3296));
    }
    @Test
    public void getType1013(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3298));
    }
    @Test
    public void getType1014(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3300));
    }
    @Test
    public void getType1015(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 3302));
    }
    @Test
    public void getType1016(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3312));
    }
    @Test
    public void getType1017(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3313));
    }
    @Test
    public void getType1018(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3315));
    }
    @Test
    public void getType1019(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3330));
    }
    @Test
    public void getType1020(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3332));
    }
    @Test
    public void getType1021(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3333));
    }
    @Test
    public void getType1022(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3341));
    }
    @Test
    public void getType1023(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3342));
    }
    @Test
    public void getType1024(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3345));
    }
    @Test
    public void getType1025(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3346));
    }
    @Test
    public void getType1026(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3387));
    }
    @Test
    public void getType1027(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3389));
    }
    @Test
    public void getType1028(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3390));
    }
    @Test
    public void getType1029(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3397));
    }
    @Test
    public void getType1030(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3398));
    }
    @Test
    public void getType1031(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3401));
    }
    @Test
    public void getType1032(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3402));
    }
    @Test
    public void getType1033(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3406));
    }
    @Test
    public void getType1034(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3407));
    }
    @Test
    public void getType1035(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3415));
    }
    @Test
    public void getType1036(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3416));
    }
    @Test
    public void getType1037(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3424));
    }
    @Test
    public void getType1038(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3426));
    }
    @Test
    public void getType1039(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3428));
    }
    @Test
    public void getType1040(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 3430));
    }
    @Test
    public void getType1041(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 3440));
    }
    @Test
    public void getType1042(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3446));
    }
    @Test
    public void getType1043(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 3449));
    }
    @Test
    public void getType1044(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3450));
    }
    @Test
    public void getType1045(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3456));
    }
    @Test
    public void getType1046(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3458));
    }
    @Test
    public void getType1047(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3460));
    }
    @Test
    public void getType1048(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3461));
    }
    @Test
    public void getType1049(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3479));
    }
    @Test
    public void getType1050(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3482));
    }
    @Test
    public void getType1051(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3506));
    }
    @Test
    public void getType1052(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3507));
    }
    @Test
    public void getType1053(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3516));
    }
    @Test
    public void getType1054(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3517));
    }
    @Test
    public void getType1055(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3518));
    }
    @Test
    public void getType1056(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3520));
    }
    @Test
    public void getType1057(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3527));
    }
    @Test
    public void getType1058(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3530));
    }
    @Test
    public void getType1059(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3531));
    }
    @Test
    public void getType1060(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3535));
    }
    @Test
    public void getType1061(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3541));
    }
    @Test
    public void getType1062(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3542));
    }
    @Test
    public void getType1063(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3543));
    }
    @Test
    public void getType1064(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3544));
    }
    @Test
    public void getType1065(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3552));
    }
    @Test
    public void getType1066(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3570));
    }
    @Test
    public void getType1067(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 3572));
    }
    @Test
    public void getType1068(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3573));
    }
    @Test
    public void getType1069(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3585));
    }
    @Test
    public void getType1070(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3633));
    }
    @Test
    public void getType1071(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3634));
    }
    @Test
    public void getType1072(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3636));
    }
    @Test
    public void getType1073(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3643));
    }
    @Test
    public void getType1074(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 3647));
    }
    @Test
    public void getType1075(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3648));
    }
    @Test
    public void getType1076(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3655));
    }
    @Test
    public void getType1077(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 3663));
    }
    @Test
    public void getType1078(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 3664));
    }
    @Test
    public void getType1079(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 3674));
    }
    @Test
    public void getType1080(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3676));
    }
    @Test
    public void getType1081(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3713));
    }
    @Test
    public void getType1082(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3715));
    }
    @Test
    public void getType1083(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3716));
    }
    @Test
    public void getType1084(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3717));
    }
    @Test
    public void getType1085(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3719));
    }
    @Test
    public void getType1086(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3721));
    }
    @Test
    public void getType1087(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3722));
    }
    @Test
    public void getType1088(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3723));
    }
    @Test
    public void getType1089(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3725));
    }
    @Test
    public void getType1090(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3726));
    }
    @Test
    public void getType1091(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3732));
    }
    @Test
    public void getType1092(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3736));
    }
    @Test
    public void getType1093(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3737));
    }
    @Test
    public void getType1094(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3744));
    }
    @Test
    public void getType1095(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3745));
    }
    @Test
    public void getType1096(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3748));
    }
    @Test
    public void getType1097(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3749));
    }
    @Test
    public void getType1098(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3750));
    }
    @Test
    public void getType1099(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3751));
    }
    @Test
    public void getType1100(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3752));
    }
    @Test
    public void getType1101(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3754));
    }
    @Test
    public void getType1102(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3756));
    }
    @Test
    public void getType1103(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3757));
    }
    @Test
    public void getType1104(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3761));
    }
    @Test
    public void getType1105(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3762));
    }
    @Test
    public void getType1106(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3764));
    }
    @Test
    public void getType1107(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3770));
    }
    @Test
    public void getType1108(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3771));
    }
    @Test
    public void getType1109(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3773));
    }
    @Test
    public void getType1110(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3774));
    }
    @Test
    public void getType1111(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3776));
    }
    @Test
    public void getType1112(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3781));
    }
    @Test
    public void getType1113(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3782));
    }
    @Test
    public void getType1114(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3783));
    }
    @Test
    public void getType1115(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3784));
    }
    @Test
    public void getType1116(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3790));
    }
    @Test
    public void getType1117(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 3792));
    }
    @Test
    public void getType1118(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3802));
    }
    @Test
    public void getType1119(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3804));
    }
    @Test
    public void getType1120(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3808));
    }
    @Test
    public void getType1121(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3840));
    }
    @Test
    public void getType1122(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 3841));
    }
    @Test
    public void getType1123(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 3844));
    }
    @Test
    public void getType1124(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 3859));
    }
    @Test
    public void getType1125(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 3860));
    }
    @Test
    public void getType1126(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 3861));
    }
    @Test
    public void getType1127(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3864));
    }
    @Test
    public void getType1128(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 3866));
    }
    @Test
    public void getType1129(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 3872));
    }
    @Test
    public void getType1130(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 3882));
    }
    @Test
    public void getType1131(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 3892));
    }
    @Test
    public void getType1132(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3893));
    }
    @Test
    public void getType1133(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 3894));
    }
    @Test
    public void getType1134(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3895));
    }
    @Test
    public void getType1135(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 3896));
    }
    @Test
    public void getType1136(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3897));
    }
    @Test
    public void getType1137(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 3898));
    }
    @Test
    public void getType1138(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3902));
    }
    @Test
    public void getType1139(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3904));
    }
    @Test
    public void getType1140(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3912));
    }
    @Test
    public void getType1141(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3913));
    }
    @Test
    public void getType1142(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3949));
    }
    @Test
    public void getType1143(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3953));
    }
    @Test
    public void getType1144(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 3973));
    }
    @Test
    public void getType1145(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3974));
    }
    @Test
    public void getType1146(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 3976));
    }
    @Test
    public void getType1147(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3981));
    }
    @Test
    public void getType1148(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 3992));
    }
    @Test
    public void getType1149(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 3993));
    }
    @Test
    public void getType1150(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4029));
    }
    @Test
    public void getType1151(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 4030));
    }
    @Test
    public void getType1152(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 4038));
    }
    @Test
    public void getType1153(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 4039));
    }
    @Test
    public void getType1154(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4045));
    }
    @Test
    public void getType1155(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 4046));
    }
    @Test
    public void getType1156(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 4048));
    }
    @Test
    public void getType1157(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 4053));
    }
    @Test
    public void getType1158(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 4057));
    }
    @Test
    public void getType1159(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4059));
    }
    @Test
    public void getType1160(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4096));
    }
    @Test
    public void getType1161(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 4139));
    }
    @Test
    public void getType1162(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4159));
    }
    @Test
    public void getType1163(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 4160));
    }
    @Test
    public void getType1164(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 4170));
    }
    @Test
    public void getType1165(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4176));
    }
    @Test
    public void getType1166(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 4182));
    }
    @Test
    public void getType1167(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4186));
    }
    @Test
    public void getType1168(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 4190));
    }
    @Test
    public void getType1169(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4193));
    }
    @Test
    public void getType1170(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 4194));
    }
    @Test
    public void getType1171(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4197));
    }
    @Test
    public void getType1172(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 4199));
    }
    @Test
    public void getType1173(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4206));
    }
    @Test
    public void getType1174(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 4209));
    }
    @Test
    public void getType1175(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4213));
    }
    @Test
    public void getType1176(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 4226));
    }
    @Test
    public void getType1177(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4238));
    }
    @Test
    public void getType1178(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 4239));
    }
    @Test
    public void getType1179(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 4240));
    }
    @Test
    public void getType1180(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 4250));
    }
    @Test
    public void getType1181(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 4254));
    }
    @Test
    public void getType1182(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 4256));
    }
    @Test
    public void getType1183(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4294));
    }
    @Test
    public void getType1184(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 4295));
    }
    @Test
    public void getType1185(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4296));
    }
    @Test
    public void getType1186(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 4301));
    }
    @Test
    public void getType1187(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4302));
    }
    @Test
    public void getType1188(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4304));
    }
    @Test
    public void getType1189(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 4347));
    }
    @Test
    public void getType1190(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4348));
    }
    @Test
    public void getType1191(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4681));
    }
    @Test
    public void getType1192(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4682));
    }
    @Test
    public void getType1193(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4686));
    }
    @Test
    public void getType1194(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4688));
    }
    @Test
    public void getType1195(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4695));
    }
    @Test
    public void getType1196(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4696));
    }
    @Test
    public void getType1197(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4697));
    }
    @Test
    public void getType1198(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4698));
    }
    @Test
    public void getType1199(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4702));
    }
    @Test
    public void getType1200(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4704));
    }
    @Test
    public void getType1201(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4745));
    }
    @Test
    public void getType1202(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4746));
    }
    @Test
    public void getType1203(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4750));
    }
    @Test
    public void getType1204(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4752));
    }
    @Test
    public void getType1205(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4785));
    }
    @Test
    public void getType1206(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4786));
    }
    @Test
    public void getType1207(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4790));
    }
    @Test
    public void getType1208(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4792));
    }
    @Test
    public void getType1209(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4799));
    }
    @Test
    public void getType1210(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4800));
    }
    @Test
    public void getType1211(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4801));
    }
    @Test
    public void getType1212(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4802));
    }
    @Test
    public void getType1213(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4806));
    }
    @Test
    public void getType1214(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4808));
    }
    @Test
    public void getType1215(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4823));
    }
    @Test
    public void getType1216(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4824));
    }
    @Test
    public void getType1217(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4881));
    }
    @Test
    public void getType1218(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4882));
    }
    @Test
    public void getType1219(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4886));
    }
    @Test
    public void getType1220(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4888));
    }
    @Test
    public void getType1221(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4955));
    }
    @Test
    public void getType1222(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 4957));
    }
    @Test
    public void getType1223(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 4960));
    }
    @Test
    public void getType1224(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 4969));
    }
    @Test
    public void getType1225(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 4989));
    }
    @Test
    public void getType1226(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 4992));
    }
    @Test
    public void getType1227(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 5008));
    }
    @Test
    public void getType1228(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 5018));
    }
    @Test
    public void getType1229(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 5024));
    }
    @Test
    public void getType1230(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 5109));
    }
    @Test
    public void getType1231(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 5120));
    }
    @Test
    public void getType1232(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 5121));
    }
    @Test
    public void getType1233(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 5741));
    }
    @Test
    public void getType1234(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 5743));
    }
    @Test
    public void getType1235(){
        assertEq(StringExpUtil.SPACE_OTHER, StringExpUtil.getCustomType((char) 5760));
    }
    @Test
    public void getType1236(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 5761));
    }
    @Test
    public void getType1237(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 5787));
    }
    @Test
    public void getType1238(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 5789));
    }
    @Test
    public void getType1239(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 5792));
    }
    @Test
    public void getType1240(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 5867));
    }
    @Test
    public void getType1241(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 5870));
    }
    @Test
    public void getType1242(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 5873));
    }
    @Test
    public void getType1243(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 5888));
    }
    @Test
    public void getType1244(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 5901));
    }
    @Test
    public void getType1245(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 5902));
    }
    @Test
    public void getType1246(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 5906));
    }
    @Test
    public void getType1247(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 5909));
    }
    @Test
    public void getType1248(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 5920));
    }
    @Test
    public void getType1249(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 5938));
    }
    @Test
    public void getType1250(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 5941));
    }
    @Test
    public void getType1251(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 5943));
    }
    @Test
    public void getType1252(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 5952));
    }
    @Test
    public void getType1253(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 5970));
    }
    @Test
    public void getType1254(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 5972));
    }
    @Test
    public void getType1255(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 5984));
    }
    @Test
    public void getType1256(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 5997));
    }
    @Test
    public void getType1257(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 5998));
    }
    @Test
    public void getType1258(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6001));
    }
    @Test
    public void getType1259(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6002));
    }
    @Test
    public void getType1260(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6004));
    }
    @Test
    public void getType1261(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6016));
    }
    @Test
    public void getType1262(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6068));
    }
    @Test
    public void getType1263(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 6100));
    }
    @Test
    public void getType1264(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6103));
    }
    @Test
    public void getType1265(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 6104));
    }
    @Test
    public void getType1266(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 6107));
    }
    @Test
    public void getType1267(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6108));
    }
    @Test
    public void getType1268(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6109));
    }
    @Test
    public void getType1269(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6110));
    }
    @Test
    public void getType1270(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 6112));
    }
    @Test
    public void getType1271(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6122));
    }
    @Test
    public void getType1272(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 6128));
    }
    @Test
    public void getType1273(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6138));
    }
    @Test
    public void getType1274(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 6144));
    }
    @Test
    public void getType1275(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 6150));
    }
    @Test
    public void getType1276(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 6151));
    }
    @Test
    public void getType1277(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6155));
    }
    @Test
    public void getType1278(){
        assertEq(StringExpUtil.SPACE_OTHER, StringExpUtil.getCustomType((char) 6158));
    }
    @Test
    public void getType1279(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6159));
    }
    @Test
    public void getType1280(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 6160));
    }
    @Test
    public void getType1281(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6170));
    }
    @Test
    public void getType1282(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6176));
    }
    @Test
    public void getType1283(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6264));
    }
    @Test
    public void getType1284(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6272));
    }
    @Test
    public void getType1285(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6313));
    }
    @Test
    public void getType1286(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6314));
    }
    @Test
    public void getType1287(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6315));
    }
    @Test
    public void getType1288(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6320));
    }
    @Test
    public void getType1289(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6390));
    }
    @Test
    public void getType1290(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6400));
    }
    @Test
    public void getType1291(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6429));
    }
    @Test
    public void getType1292(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6432));
    }
    @Test
    public void getType1293(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6444));
    }
    @Test
    public void getType1294(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6448));
    }
    @Test
    public void getType1295(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6460));
    }
    @Test
    public void getType1296(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 6464));
    }
    @Test
    public void getType1297(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6465));
    }
    @Test
    public void getType1298(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 6468));
    }
    @Test
    public void getType1299(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 6470));
    }
    @Test
    public void getType1300(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6480));
    }
    @Test
    public void getType1301(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6510));
    }
    @Test
    public void getType1302(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6512));
    }
    @Test
    public void getType1303(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6517));
    }
    @Test
    public void getType1304(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6528));
    }
    @Test
    public void getType1305(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6572));
    }
    @Test
    public void getType1306(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6576));
    }
    @Test
    public void getType1307(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6593));
    }
    @Test
    public void getType1308(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6600));
    }
    @Test
    public void getType1309(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6602));
    }
    @Test
    public void getType1310(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 6608));
    }
    @Test
    public void getType1311(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 6618));
    }
    @Test
    public void getType1312(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6619));
    }
    @Test
    public void getType1313(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 6622));
    }
    @Test
    public void getType1314(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6656));
    }
    @Test
    public void getType1315(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6679));
    }
    @Test
    public void getType1316(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6684));
    }
    @Test
    public void getType1317(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 6686));
    }
    @Test
    public void getType1318(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6688));
    }
    @Test
    public void getType1319(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6741));
    }
    @Test
    public void getType1320(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6751));
    }
    @Test
    public void getType1321(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6752));
    }
    @Test
    public void getType1322(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6781));
    }
    @Test
    public void getType1323(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6783));
    }
    @Test
    public void getType1324(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 6784));
    }
    @Test
    public void getType1325(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6794));
    }
    @Test
    public void getType1326(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 6800));
    }
    @Test
    public void getType1327(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6810));
    }
    @Test
    public void getType1328(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 6816));
    }
    @Test
    public void getType1329(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6823));
    }
    @Test
    public void getType1330(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 6824));
    }
    @Test
    public void getType1331(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6830));
    }
    @Test
    public void getType1332(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6912));
    }
    @Test
    public void getType1333(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6917));
    }
    @Test
    public void getType1334(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 6964));
    }
    @Test
    public void getType1335(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 6981));
    }
    @Test
    public void getType1336(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 6988));
    }
    @Test
    public void getType1337(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 6992));
    }
    @Test
    public void getType1338(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 7002));
    }
    @Test
    public void getType1339(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 7009));
    }
    @Test
    public void getType1340(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 7019));
    }
    @Test
    public void getType1341(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 7028));
    }
    @Test
    public void getType1342(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 7037));
    }
    @Test
    public void getType1343(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 7040));
    }
    @Test
    public void getType1344(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 7043));
    }
    @Test
    public void getType1345(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 7073));
    }
    @Test
    public void getType1346(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 7086));
    }
    @Test
    public void getType1347(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 7088));
    }
    @Test
    public void getType1348(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 7098));
    }
    @Test
    public void getType1349(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 7142));
    }
    @Test
    public void getType1350(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 7156));
    }
    @Test
    public void getType1351(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 7164));
    }
    @Test
    public void getType1352(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 7168));
    }
    @Test
    public void getType1353(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 7204));
    }
    @Test
    public void getType1354(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 7224));
    }
    @Test
    public void getType1355(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 7227));
    }
    @Test
    public void getType1356(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 7232));
    }
    @Test
    public void getType1357(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 7242));
    }
    @Test
    public void getType1358(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 7245));
    }
    @Test
    public void getType1359(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 7248));
    }
    @Test
    public void getType1360(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 7258));
    }
    @Test
    public void getType1361(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 7294));
    }
    @Test
    public void getType1362(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 7296));
    }
    @Test
    public void getType1363(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 7360));
    }
    @Test
    public void getType1364(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 7368));
    }
    @Test
    public void getType1365(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 7376));
    }
    @Test
    public void getType1366(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 7379));
    }
    @Test
    public void getType1367(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 7380));
    }
    @Test
    public void getType1368(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 7401));
    }
    @Test
    public void getType1369(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 7405));
    }
    @Test
    public void getType1370(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 7406));
    }
    @Test
    public void getType1371(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 7410));
    }
    @Test
    public void getType1372(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 7413));
    }
    @Test
    public void getType1373(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 7415));
    }
    @Test
    public void getType1374(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7424));
    }
    @Test
    public void getType1375(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7545));
    }
    @Test
    public void getType1376(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7546));
    }
    @Test
    public void getType1377(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7549));
    }
    @Test
    public void getType1378(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7550));
    }
    @Test
    public void getType1379(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 7616));
    }
    @Test
    public void getType1380(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 7655));
    }
    @Test
    public void getType1381(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 7676));
    }
    @Test
    public void getType1382(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7680));
    }
    @Test
    public void getType1383(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7681));
    }
    @Test
    public void getType1384(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7682));
    }
    @Test
    public void getType1385(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7683));
    }
    @Test
    public void getType1386(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7684));
    }
    @Test
    public void getType1387(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7685));
    }
    @Test
    public void getType1388(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7686));
    }
    @Test
    public void getType1389(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7687));
    }
    @Test
    public void getType1390(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7688));
    }
    @Test
    public void getType1391(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7689));
    }
    @Test
    public void getType1392(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7690));
    }
    @Test
    public void getType1393(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7691));
    }
    @Test
    public void getType1394(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7692));
    }
    @Test
    public void getType1395(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7693));
    }
    @Test
    public void getType1396(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7694));
    }
    @Test
    public void getType1397(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7695));
    }
    @Test
    public void getType1398(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7696));
    }
    @Test
    public void getType1399(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7697));
    }
    @Test
    public void getType1400(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7698));
    }
    @Test
    public void getType1401(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7699));
    }
    @Test
    public void getType1402(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7700));
    }
    @Test
    public void getType1403(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7701));
    }
    @Test
    public void getType1404(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7702));
    }
    @Test
    public void getType1405(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7703));
    }
    @Test
    public void getType1406(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7704));
    }
    @Test
    public void getType1407(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7705));
    }
    @Test
    public void getType1408(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7706));
    }
    @Test
    public void getType1409(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7707));
    }
    @Test
    public void getType1410(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7708));
    }
    @Test
    public void getType1411(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7709));
    }
    @Test
    public void getType1412(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7710));
    }
    @Test
    public void getType1413(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7711));
    }
    @Test
    public void getType1414(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7712));
    }
    @Test
    public void getType1415(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7713));
    }
    @Test
    public void getType1416(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7714));
    }
    @Test
    public void getType1417(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7715));
    }
    @Test
    public void getType1418(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7716));
    }
    @Test
    public void getType1419(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7717));
    }
    @Test
    public void getType1420(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7718));
    }
    @Test
    public void getType1421(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7719));
    }
    @Test
    public void getType1422(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7720));
    }
    @Test
    public void getType1423(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7721));
    }
    @Test
    public void getType1424(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7722));
    }
    @Test
    public void getType1425(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7723));
    }
    @Test
    public void getType1426(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7724));
    }
    @Test
    public void getType1427(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7725));
    }
    @Test
    public void getType1428(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7726));
    }
    @Test
    public void getType1429(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7727));
    }
    @Test
    public void getType1430(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7728));
    }
    @Test
    public void getType1431(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7729));
    }
    @Test
    public void getType1432(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7730));
    }
    @Test
    public void getType1433(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7731));
    }
    @Test
    public void getType1434(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7732));
    }
    @Test
    public void getType1435(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7733));
    }
    @Test
    public void getType1436(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7734));
    }
    @Test
    public void getType1437(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7735));
    }
    @Test
    public void getType1438(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7736));
    }
    @Test
    public void getType1439(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7737));
    }
    @Test
    public void getType1440(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7738));
    }
    @Test
    public void getType1441(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7739));
    }
    @Test
    public void getType1442(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7740));
    }
    @Test
    public void getType1443(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7741));
    }
    @Test
    public void getType1444(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7742));
    }
    @Test
    public void getType1445(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7743));
    }
    @Test
    public void getType1446(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7744));
    }
    @Test
    public void getType1447(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7745));
    }
    @Test
    public void getType1448(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7746));
    }
    @Test
    public void getType1449(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7747));
    }
    @Test
    public void getType1450(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7748));
    }
    @Test
    public void getType1451(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7749));
    }
    @Test
    public void getType1452(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7750));
    }
    @Test
    public void getType1453(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7751));
    }
    @Test
    public void getType1454(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7752));
    }
    @Test
    public void getType1455(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7753));
    }
    @Test
    public void getType1456(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7754));
    }
    @Test
    public void getType1457(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7755));
    }
    @Test
    public void getType1458(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7756));
    }
    @Test
    public void getType1459(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7757));
    }
    @Test
    public void getType1460(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7758));
    }
    @Test
    public void getType1461(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7759));
    }
    @Test
    public void getType1462(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7760));
    }
    @Test
    public void getType1463(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7761));
    }
    @Test
    public void getType1464(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7762));
    }
    @Test
    public void getType1465(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7763));
    }
    @Test
    public void getType1466(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7764));
    }
    @Test
    public void getType1467(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7765));
    }
    @Test
    public void getType1468(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7766));
    }
    @Test
    public void getType1469(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7767));
    }
    @Test
    public void getType1470(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7768));
    }
    @Test
    public void getType1471(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7769));
    }
    @Test
    public void getType1472(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7770));
    }
    @Test
    public void getType1473(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7771));
    }
    @Test
    public void getType1474(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7772));
    }
    @Test
    public void getType1475(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7773));
    }
    @Test
    public void getType1476(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7774));
    }
    @Test
    public void getType1477(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7775));
    }
    @Test
    public void getType1478(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7776));
    }
    @Test
    public void getType1479(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7777));
    }
    @Test
    public void getType1480(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7778));
    }
    @Test
    public void getType1481(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7779));
    }
    @Test
    public void getType1482(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7780));
    }
    @Test
    public void getType1483(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7781));
    }
    @Test
    public void getType1484(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7782));
    }
    @Test
    public void getType1485(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7783));
    }
    @Test
    public void getType1486(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7784));
    }
    @Test
    public void getType1487(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7785));
    }
    @Test
    public void getType1488(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7786));
    }
    @Test
    public void getType1489(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7787));
    }
    @Test
    public void getType1490(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7788));
    }
    @Test
    public void getType1491(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7789));
    }
    @Test
    public void getType1492(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7790));
    }
    @Test
    public void getType1493(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7791));
    }
    @Test
    public void getType1494(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7792));
    }
    @Test
    public void getType1495(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7793));
    }
    @Test
    public void getType1496(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7794));
    }
    @Test
    public void getType1497(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7795));
    }
    @Test
    public void getType1498(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7796));
    }
    @Test
    public void getType1499(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7797));
    }
    @Test
    public void getType1500(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7798));
    }
    @Test
    public void getType1501(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7799));
    }
    @Test
    public void getType1502(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7800));
    }
    @Test
    public void getType1503(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7801));
    }
    @Test
    public void getType1504(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7802));
    }
    @Test
    public void getType1505(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7803));
    }
    @Test
    public void getType1506(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7804));
    }
    @Test
    public void getType1507(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7805));
    }
    @Test
    public void getType1508(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7806));
    }
    @Test
    public void getType1509(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7807));
    }
    @Test
    public void getType1510(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7808));
    }
    @Test
    public void getType1511(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7809));
    }
    @Test
    public void getType1512(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7810));
    }
    @Test
    public void getType1513(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7811));
    }
    @Test
    public void getType1514(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7812));
    }
    @Test
    public void getType1515(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7813));
    }
    @Test
    public void getType1516(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7814));
    }
    @Test
    public void getType1517(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7815));
    }
    @Test
    public void getType1518(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7816));
    }
    @Test
    public void getType1519(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7817));
    }
    @Test
    public void getType1520(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7818));
    }
    @Test
    public void getType1521(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7819));
    }
    @Test
    public void getType1522(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7820));
    }
    @Test
    public void getType1523(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7821));
    }
    @Test
    public void getType1524(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7822));
    }
    @Test
    public void getType1525(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7823));
    }
    @Test
    public void getType1526(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7824));
    }
    @Test
    public void getType1527(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7825));
    }
    @Test
    public void getType1528(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7826));
    }
    @Test
    public void getType1529(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7827));
    }
    @Test
    public void getType1530(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7828));
    }
    @Test
    public void getType1531(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7829));
    }
    @Test
    public void getType1532(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7830));
    }
    @Test
    public void getType1533(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7835));
    }
    @Test
    public void getType1534(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7836));
    }
    @Test
    public void getType1535(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7838));
    }
    @Test
    public void getType1536(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7839));
    }
    @Test
    public void getType1537(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7840));
    }
    @Test
    public void getType1538(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7841));
    }
    @Test
    public void getType1539(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7842));
    }
    @Test
    public void getType1540(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7843));
    }
    @Test
    public void getType1541(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7844));
    }
    @Test
    public void getType1542(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7845));
    }
    @Test
    public void getType1543(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7846));
    }
    @Test
    public void getType1544(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7847));
    }
    @Test
    public void getType1545(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7848));
    }
    @Test
    public void getType1546(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7849));
    }
    @Test
    public void getType1547(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7850));
    }
    @Test
    public void getType1548(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7851));
    }
    @Test
    public void getType1549(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7852));
    }
    @Test
    public void getType1550(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7853));
    }
    @Test
    public void getType1551(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7854));
    }
    @Test
    public void getType1552(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7855));
    }
    @Test
    public void getType1553(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7856));
    }
    @Test
    public void getType1554(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7857));
    }
    @Test
    public void getType1555(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7858));
    }
    @Test
    public void getType1556(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7859));
    }
    @Test
    public void getType1557(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7860));
    }
    @Test
    public void getType1558(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7861));
    }
    @Test
    public void getType1559(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7862));
    }
    @Test
    public void getType1560(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7863));
    }
    @Test
    public void getType1561(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7864));
    }
    @Test
    public void getType1562(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7865));
    }
    @Test
    public void getType1563(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7866));
    }
    @Test
    public void getType1564(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7867));
    }
    @Test
    public void getType1565(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7868));
    }
    @Test
    public void getType1566(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7869));
    }
    @Test
    public void getType1567(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7870));
    }
    @Test
    public void getType1568(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7871));
    }
    @Test
    public void getType1569(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7872));
    }
    @Test
    public void getType1570(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7873));
    }
    @Test
    public void getType1571(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7874));
    }
    @Test
    public void getType1572(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7875));
    }
    @Test
    public void getType1573(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7876));
    }
    @Test
    public void getType1574(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7877));
    }
    @Test
    public void getType1575(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7878));
    }
    @Test
    public void getType1576(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7879));
    }
    @Test
    public void getType1577(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7880));
    }
    @Test
    public void getType1578(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7881));
    }
    @Test
    public void getType1579(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7882));
    }
    @Test
    public void getType1580(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7883));
    }
    @Test
    public void getType1581(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7884));
    }
    @Test
    public void getType1582(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7885));
    }
    @Test
    public void getType1583(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7886));
    }
    @Test
    public void getType1584(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7887));
    }
    @Test
    public void getType1585(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7888));
    }
    @Test
    public void getType1586(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7889));
    }
    @Test
    public void getType1587(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7890));
    }
    @Test
    public void getType1588(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7891));
    }
    @Test
    public void getType1589(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7892));
    }
    @Test
    public void getType1590(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7893));
    }
    @Test
    public void getType1591(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7894));
    }
    @Test
    public void getType1592(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7895));
    }
    @Test
    public void getType1593(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7896));
    }
    @Test
    public void getType1594(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7897));
    }
    @Test
    public void getType1595(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7898));
    }
    @Test
    public void getType1596(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7899));
    }
    @Test
    public void getType1597(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7900));
    }
    @Test
    public void getType1598(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7901));
    }
    @Test
    public void getType1599(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7902));
    }
    @Test
    public void getType1600(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7903));
    }
    @Test
    public void getType1601(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7904));
    }
    @Test
    public void getType1602(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7905));
    }
    @Test
    public void getType1603(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7906));
    }
    @Test
    public void getType1604(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7907));
    }
    @Test
    public void getType1605(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7908));
    }
    @Test
    public void getType1606(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7909));
    }
    @Test
    public void getType1607(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7910));
    }
    @Test
    public void getType1608(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7911));
    }
    @Test
    public void getType1609(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7912));
    }
    @Test
    public void getType1610(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7913));
    }
    @Test
    public void getType1611(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7914));
    }
    @Test
    public void getType1612(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7915));
    }
    @Test
    public void getType1613(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7916));
    }
    @Test
    public void getType1614(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7917));
    }
    @Test
    public void getType1615(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7918));
    }
    @Test
    public void getType1616(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7919));
    }
    @Test
    public void getType1617(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7920));
    }
    @Test
    public void getType1618(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7921));
    }
    @Test
    public void getType1619(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7922));
    }
    @Test
    public void getType1620(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7923));
    }
    @Test
    public void getType1621(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7924));
    }
    @Test
    public void getType1622(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7925));
    }
    @Test
    public void getType1623(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7926));
    }
    @Test
    public void getType1624(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7927));
    }
    @Test
    public void getType1625(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7928));
    }
    @Test
    public void getType1626(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7929));
    }
    @Test
    public void getType1627(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7930));
    }
    @Test
    public void getType1628(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7931));
    }
    @Test
    public void getType1629(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7932));
    }
    @Test
    public void getType1630(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7933));
    }
    @Test
    public void getType1631(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7934));
    }
    @Test
    public void getType1632(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7935));
    }
    @Test
    public void getType1633(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7944));
    }
    @Test
    public void getType1634(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7952));
    }
    @Test
    public void getType1635(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 7958));
    }
    @Test
    public void getType1636(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7960));
    }
    @Test
    public void getType1637(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 7966));
    }
    @Test
    public void getType1638(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7968));
    }
    @Test
    public void getType1639(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7976));
    }
    @Test
    public void getType1640(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 7984));
    }
    @Test
    public void getType1641(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 7992));
    }
    @Test
    public void getType1642(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8000));
    }
    @Test
    public void getType1643(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8006));
    }
    @Test
    public void getType1644(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8008));
    }
    @Test
    public void getType1645(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8014));
    }
    @Test
    public void getType1646(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8016));
    }
    @Test
    public void getType1647(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8017));
    }
    @Test
    public void getType1648(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8018));
    }
    @Test
    public void getType1649(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8019));
    }
    @Test
    public void getType1650(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8020));
    }
    @Test
    public void getType1651(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8021));
    }
    @Test
    public void getType1652(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8022));
    }
    @Test
    public void getType1653(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8023));
    }
    @Test
    public void getType1654(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8024));
    }
    @Test
    public void getType1655(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8025));
    }
    @Test
    public void getType1656(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8026));
    }
    @Test
    public void getType1657(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8027));
    }
    @Test
    public void getType1658(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8028));
    }
    @Test
    public void getType1659(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8029));
    }
    @Test
    public void getType1660(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8030));
    }
    @Test
    public void getType1661(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8031));
    }
    @Test
    public void getType1662(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8032));
    }
    @Test
    public void getType1663(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8040));
    }
    @Test
    public void getType1664(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8048));
    }
    @Test
    public void getType1665(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8062));
    }
    @Test
    public void getType1666(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8064));
    }
    @Test
    public void getType1667(){
        assertEq(StringExpUtil.LETTER_SEMI_SENS_NO_CASE, StringExpUtil.getCustomType((char) 8072));
    }
    @Test
    public void getType1668(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8080));
    }
    @Test
    public void getType1669(){
        assertEq(StringExpUtil.LETTER_SEMI_SENS_NO_CASE, StringExpUtil.getCustomType((char) 8088));
    }
    @Test
    public void getType1670(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8096));
    }
    @Test
    public void getType1671(){
        assertEq(StringExpUtil.LETTER_SEMI_SENS_NO_CASE, StringExpUtil.getCustomType((char) 8104));
    }
    @Test
    public void getType1672(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8112));
    }
    @Test
    public void getType1673(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8114));
    }
    @Test
    public void getType1674(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8115));
    }
    @Test
    public void getType1675(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8116));
    }
    @Test
    public void getType1676(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8117));
    }
    @Test
    public void getType1677(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8118));
    }
    @Test
    public void getType1678(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8120));
    }
    @Test
    public void getType1679(){
        assertEq(StringExpUtil.LETTER_SEMI_SENS_NO_CASE, StringExpUtil.getCustomType((char) 8124));
    }
    @Test
    public void getType1680(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 8125));
    }
    @Test
    public void getType1681(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8126));
    }
    @Test
    public void getType1682(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 8127));
    }
    @Test
    public void getType1683(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8130));
    }
    @Test
    public void getType1684(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8131));
    }
    @Test
    public void getType1685(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8132));
    }
    @Test
    public void getType1686(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8133));
    }
    @Test
    public void getType1687(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8134));
    }
    @Test
    public void getType1688(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8136));
    }
    @Test
    public void getType1689(){
        assertEq(StringExpUtil.LETTER_SEMI_SENS_NO_CASE, StringExpUtil.getCustomType((char) 8140));
    }
    @Test
    public void getType1690(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 8141));
    }
    @Test
    public void getType1691(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8144));
    }
    @Test
    public void getType1692(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8146));
    }
    @Test
    public void getType1693(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8148));
    }
    @Test
    public void getType1694(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8150));
    }
    @Test
    public void getType1695(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8152));
    }
    @Test
    public void getType1696(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8156));
    }
    @Test
    public void getType1697(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 8157));
    }
    @Test
    public void getType1698(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8160));
    }
    @Test
    public void getType1699(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8162));
    }
    @Test
    public void getType1700(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8165));
    }
    @Test
    public void getType1701(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8166));
    }
    @Test
    public void getType1702(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8168));
    }
    @Test
    public void getType1703(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 8173));
    }
    @Test
    public void getType1704(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8176));
    }
    @Test
    public void getType1705(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8178));
    }
    @Test
    public void getType1706(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8179));
    }
    @Test
    public void getType1707(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8180));
    }
    @Test
    public void getType1708(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8181));
    }
    @Test
    public void getType1709(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8182));
    }
    @Test
    public void getType1710(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8184));
    }
    @Test
    public void getType1711(){
        assertEq(StringExpUtil.LETTER_SEMI_SENS_NO_CASE, StringExpUtil.getCustomType((char) 8188));
    }
    @Test
    public void getType1712(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 8189));
    }
    @Test
    public void getType1713(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8191));
    }
    @Test
    public void getType1714(){
        assertEq(StringExpUtil.SPACE_OTHER, StringExpUtil.getCustomType((char) 8192));
    }
    @Test
    public void getType1715(){
        assertEq(StringExpUtil.SEPARATOR, StringExpUtil.getCustomType((char) 8203));
    }
    @Test
    public void getType1716(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 8208));
    }
    @Test
    public void getType1717(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 8214));
    }
    @Test
    public void getType1718(){
        assertEq(StringExpUtil.PUNCTUATION_QUOTE, StringExpUtil.getCustomType((char) 8216));
    }
    @Test
    public void getType1719(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 8218));
    }
    @Test
    public void getType1720(){
        assertEq(StringExpUtil.PUNCTUATION_QUOTE, StringExpUtil.getCustomType((char) 8219));
    }
    @Test
    public void getType1721(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 8222));
    }
    @Test
    public void getType1722(){
        assertEq(StringExpUtil.PUNCTUATION_QUOTE, StringExpUtil.getCustomType((char) 8223));
    }
    @Test
    public void getType1723(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 8224));
    }
    @Test
    public void getType1724(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 8232));
    }
    @Test
    public void getType1725(){
        assertEq(StringExpUtil.SPACE_OTHER, StringExpUtil.getCustomType((char) 8239));
    }
    @Test
    public void getType1726(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 8240));
    }
    @Test
    public void getType1727(){
        assertEq(StringExpUtil.PUNCTUATION_QUOTE, StringExpUtil.getCustomType((char) 8249));
    }
    @Test
    public void getType1728(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 8251));
    }
    @Test
    public void getType1729(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 8255));
    }
    @Test
    public void getType1730(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 8257));
    }
    @Test
    public void getType1731(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8260));
    }
    @Test
    public void getType1732(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 8261));
    }
    @Test
    public void getType1733(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 8263));
    }
    @Test
    public void getType1734(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8274));
    }
    @Test
    public void getType1735(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 8275));
    }
    @Test
    public void getType1736(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 8276));
    }
    @Test
    public void getType1737(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 8277));
    }
    @Test
    public void getType1738(){
        assertEq(StringExpUtil.SPACE_OTHER, StringExpUtil.getCustomType((char) 8287));
    }
    @Test
    public void getType1739(){
        assertEq(StringExpUtil.SEPARATOR, StringExpUtil.getCustomType((char) 8288));
    }
    @Test
    public void getType1740(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8293));
    }
    @Test
    public void getType1741(){
        assertEq(StringExpUtil.SEPARATOR, StringExpUtil.getCustomType((char) 8298));
    }
    @Test
    public void getType1742(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8305));
    }
    @Test
    public void getType1743(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8306));
    }
    @Test
    public void getType1744(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 8308));
    }
    @Test
    public void getType1745(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8314));
    }
    @Test
    public void getType1746(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 8317));
    }
    @Test
    public void getType1747(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8319));
    }
    @Test
    public void getType1748(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 8320));
    }
    @Test
    public void getType1749(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8330));
    }
    @Test
    public void getType1750(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 8333));
    }
    @Test
    public void getType1751(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8335));
    }
    @Test
    public void getType1752(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8336));
    }
    @Test
    public void getType1753(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8349));
    }
    @Test
    public void getType1754(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 8352));
    }
    @Test
    public void getType1755(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8379));
    }
    @Test
    public void getType1756(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 8400));
    }
    @Test
    public void getType1757(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8433));
    }
    @Test
    public void getType1758(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8448));
    }
    @Test
    public void getType1759(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8450));
    }
    @Test
    public void getType1760(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8451));
    }
    @Test
    public void getType1761(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8455));
    }
    @Test
    public void getType1762(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8456));
    }
    @Test
    public void getType1763(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8458));
    }
    @Test
    public void getType1764(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8459));
    }
    @Test
    public void getType1765(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8462));
    }
    @Test
    public void getType1766(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8464));
    }
    @Test
    public void getType1767(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8467));
    }
    @Test
    public void getType1768(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8468));
    }
    @Test
    public void getType1769(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8469));
    }
    @Test
    public void getType1770(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8470));
    }
    @Test
    public void getType1771(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8472));
    }
    @Test
    public void getType1772(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8473));
    }
    @Test
    public void getType1773(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8478));
    }
    @Test
    public void getType1774(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8484));
    }
    @Test
    public void getType1775(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8485));
    }
    @Test
    public void getType1776(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8486));
    }
    @Test
    public void getType1777(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8487));
    }
    @Test
    public void getType1778(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8488));
    }
    @Test
    public void getType1779(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8489));
    }
    @Test
    public void getType1780(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8490));
    }
    @Test
    public void getType1781(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8492));
    }
    @Test
    public void getType1782(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8494));
    }
    @Test
    public void getType1783(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8495));
    }
    @Test
    public void getType1784(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8496));
    }
    @Test
    public void getType1785(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8498));
    }
    @Test
    public void getType1786(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8499));
    }
    @Test
    public void getType1787(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8500));
    }
    @Test
    public void getType1788(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 8501));
    }
    @Test
    public void getType1789(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8505));
    }
    @Test
    public void getType1790(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8506));
    }
    @Test
    public void getType1791(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8508));
    }
    @Test
    public void getType1792(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8510));
    }
    @Test
    public void getType1793(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8512));
    }
    @Test
    public void getType1794(){
        assertEq(StringExpUtil.LETTER_INSENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8517));
    }
    @Test
    public void getType1795(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8518));
    }
    @Test
    public void getType1796(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8522));
    }
    @Test
    public void getType1797(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8523));
    }
    @Test
    public void getType1798(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8524));
    }
    @Test
    public void getType1799(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8526));
    }
    @Test
    public void getType1800(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8527));
    }
    @Test
    public void getType1801(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 8528));
    }
    @Test
    public void getType1802(){
        assertEq(StringExpUtil.ROMAN_DIGIT, StringExpUtil.getCustomType((char) 8544));
    }
    @Test
    public void getType1803(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 8576));
    }
    @Test
    public void getType1804(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 8579));
    }
    @Test
    public void getType1805(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 8580));
    }
    @Test
    public void getType1806(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 8581));
    }
    @Test
    public void getType1807(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 8586));
    }
    @Test
    public void getType1808(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8592));
    }
    @Test
    public void getType1809(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8597));
    }
    @Test
    public void getType1810(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8602));
    }
    @Test
    public void getType1811(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8604));
    }
    @Test
    public void getType1812(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8608));
    }
    @Test
    public void getType1813(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8609));
    }
    @Test
    public void getType1814(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8611));
    }
    @Test
    public void getType1815(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8612));
    }
    @Test
    public void getType1816(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8614));
    }
    @Test
    public void getType1817(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8615));
    }
    @Test
    public void getType1818(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8622));
    }
    @Test
    public void getType1819(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8623));
    }
    @Test
    public void getType1820(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8654));
    }
    @Test
    public void getType1821(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8656));
    }
    @Test
    public void getType1822(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8658));
    }
    @Test
    public void getType1823(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8659));
    }
    @Test
    public void getType1824(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8660));
    }
    @Test
    public void getType1825(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8661));
    }
    @Test
    public void getType1826(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8692));
    }
    @Test
    public void getType1827(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8960));
    }
    @Test
    public void getType1828(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8968));
    }
    @Test
    public void getType1829(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8972));
    }
    @Test
    public void getType1830(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 8992));
    }
    @Test
    public void getType1831(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8994));
    }
    @Test
    public void getType1832(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 9001));
    }
    @Test
    public void getType1833(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9003));
    }
    @Test
    public void getType1834(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 9084));
    }
    @Test
    public void getType1835(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9085));
    }
    @Test
    public void getType1836(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 9115));
    }
    @Test
    public void getType1837(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9140));
    }
    @Test
    public void getType1838(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 9180));
    }
    @Test
    public void getType1839(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9186));
    }
    @Test
    public void getType1840(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 9204));
    }
    @Test
    public void getType1841(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9216));
    }
    @Test
    public void getType1842(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 9255));
    }
    @Test
    public void getType1843(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9280));
    }
    @Test
    public void getType1844(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 9291));
    }
    @Test
    public void getType1845(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 9312));
    }
    @Test
    public void getType1846(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9372));
    }
    @Test
    public void getType1847(){
        assertEq(StringExpUtil.SENS_OTHER_LETTER, StringExpUtil.getCustomType((char) 9398));
    }
    @Test
    public void getType1848(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 9450));
    }
    @Test
    public void getType1849(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9472));
    }
    @Test
    public void getType1850(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 9655));
    }
    @Test
    public void getType1851(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9656));
    }
    @Test
    public void getType1852(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 9665));
    }
    @Test
    public void getType1853(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9666));
    }
    @Test
    public void getType1854(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 9720));
    }
    @Test
    public void getType1855(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9728));
    }
    @Test
    public void getType1856(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 9839));
    }
    @Test
    public void getType1857(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9840));
    }
    @Test
    public void getType1858(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 9984));
    }
    @Test
    public void getType1859(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 9985));
    }
    @Test
    public void getType1860(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 10088));
    }
    @Test
    public void getType1861(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 10102));
    }
    @Test
    public void getType1862(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 10132));
    }
    @Test
    public void getType1863(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 10176));
    }
    @Test
    public void getType1864(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 10181));
    }
    @Test
    public void getType1865(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 10183));
    }
    @Test
    public void getType1866(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 10214));
    }
    @Test
    public void getType1867(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 10224));
    }
    @Test
    public void getType1868(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 10240));
    }
    @Test
    public void getType1869(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 10496));
    }
    @Test
    public void getType1870(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 10627));
    }
    @Test
    public void getType1871(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 10649));
    }
    @Test
    public void getType1872(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 10712));
    }
    @Test
    public void getType1873(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 10716));
    }
    @Test
    public void getType1874(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 10748));
    }
    @Test
    public void getType1875(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 10750));
    }
    @Test
    public void getType1876(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 11008));
    }
    @Test
    public void getType1877(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 11056));
    }
    @Test
    public void getType1878(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 11077));
    }
    @Test
    public void getType1879(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 11079));
    }
    @Test
    public void getType1880(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11085));
    }
    @Test
    public void getType1881(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 11088));
    }
    @Test
    public void getType1882(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11098));
    }
    @Test
    public void getType1883(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11264));
    }
    @Test
    public void getType1884(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11311));
    }
    @Test
    public void getType1885(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11312));
    }
    @Test
    public void getType1886(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11359));
    }
    @Test
    public void getType1887(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11360));
    }
    @Test
    public void getType1888(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11361));
    }
    @Test
    public void getType1889(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11362));
    }
    @Test
    public void getType1890(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11365));
    }
    @Test
    public void getType1891(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11367));
    }
    @Test
    public void getType1892(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11368));
    }
    @Test
    public void getType1893(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11369));
    }
    @Test
    public void getType1894(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11370));
    }
    @Test
    public void getType1895(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11371));
    }
    @Test
    public void getType1896(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11372));
    }
    @Test
    public void getType1897(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11373));
    }
    @Test
    public void getType1898(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11377));
    }
    @Test
    public void getType1899(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11378));
    }
    @Test
    public void getType1900(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11379));
    }
    @Test
    public void getType1901(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11380));
    }
    @Test
    public void getType1902(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11381));
    }
    @Test
    public void getType1903(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11382));
    }
    @Test
    public void getType1904(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11383));
    }
    @Test
    public void getType1905(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11390));
    }
    @Test
    public void getType1906(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11393));
    }
    @Test
    public void getType1907(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11394));
    }
    @Test
    public void getType1908(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11395));
    }
    @Test
    public void getType1909(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11396));
    }
    @Test
    public void getType1910(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11397));
    }
    @Test
    public void getType1911(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11398));
    }
    @Test
    public void getType1912(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11399));
    }
    @Test
    public void getType1913(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11400));
    }
    @Test
    public void getType1914(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11401));
    }
    @Test
    public void getType1915(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11402));
    }
    @Test
    public void getType1916(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11403));
    }
    @Test
    public void getType1917(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11404));
    }
    @Test
    public void getType1918(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11405));
    }
    @Test
    public void getType1919(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11406));
    }
    @Test
    public void getType1920(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11407));
    }
    @Test
    public void getType1921(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11408));
    }
    @Test
    public void getType1922(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11409));
    }
    @Test
    public void getType1923(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11410));
    }
    @Test
    public void getType1924(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11411));
    }
    @Test
    public void getType1925(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11412));
    }
    @Test
    public void getType1926(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11413));
    }
    @Test
    public void getType1927(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11414));
    }
    @Test
    public void getType1928(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11415));
    }
    @Test
    public void getType1929(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11416));
    }
    @Test
    public void getType1930(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11417));
    }
    @Test
    public void getType1931(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11418));
    }
    @Test
    public void getType1932(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11419));
    }
    @Test
    public void getType1933(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11420));
    }
    @Test
    public void getType1934(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11421));
    }
    @Test
    public void getType1935(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11422));
    }
    @Test
    public void getType1936(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11423));
    }
    @Test
    public void getType1937(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11424));
    }
    @Test
    public void getType1938(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11425));
    }
    @Test
    public void getType1939(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11426));
    }
    @Test
    public void getType1940(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11427));
    }
    @Test
    public void getType1941(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11428));
    }
    @Test
    public void getType1942(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11429));
    }
    @Test
    public void getType1943(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11430));
    }
    @Test
    public void getType1944(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11431));
    }
    @Test
    public void getType1945(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11432));
    }
    @Test
    public void getType1946(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11433));
    }
    @Test
    public void getType1947(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11434));
    }
    @Test
    public void getType1948(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11435));
    }
    @Test
    public void getType1949(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11436));
    }
    @Test
    public void getType1950(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11437));
    }
    @Test
    public void getType1951(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11438));
    }
    @Test
    public void getType1952(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11439));
    }
    @Test
    public void getType1953(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11440));
    }
    @Test
    public void getType1954(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11441));
    }
    @Test
    public void getType1955(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11442));
    }
    @Test
    public void getType1956(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11443));
    }
    @Test
    public void getType1957(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11444));
    }
    @Test
    public void getType1958(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11445));
    }
    @Test
    public void getType1959(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11446));
    }
    @Test
    public void getType1960(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11447));
    }
    @Test
    public void getType1961(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11448));
    }
    @Test
    public void getType1962(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11449));
    }
    @Test
    public void getType1963(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11450));
    }
    @Test
    public void getType1964(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11451));
    }
    @Test
    public void getType1965(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11452));
    }
    @Test
    public void getType1966(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11453));
    }
    @Test
    public void getType1967(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11454));
    }
    @Test
    public void getType1968(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11455));
    }
    @Test
    public void getType1969(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11456));
    }
    @Test
    public void getType1970(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11457));
    }
    @Test
    public void getType1971(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11458));
    }
    @Test
    public void getType1972(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11459));
    }
    @Test
    public void getType1973(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11460));
    }
    @Test
    public void getType1974(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11461));
    }
    @Test
    public void getType1975(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11462));
    }
    @Test
    public void getType1976(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11463));
    }
    @Test
    public void getType1977(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11464));
    }
    @Test
    public void getType1978(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11465));
    }
    @Test
    public void getType1979(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11466));
    }
    @Test
    public void getType1980(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11467));
    }
    @Test
    public void getType1981(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11468));
    }
    @Test
    public void getType1982(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11469));
    }
    @Test
    public void getType1983(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11470));
    }
    @Test
    public void getType1984(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11471));
    }
    @Test
    public void getType1985(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11472));
    }
    @Test
    public void getType1986(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11473));
    }
    @Test
    public void getType1987(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11474));
    }
    @Test
    public void getType1988(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11475));
    }
    @Test
    public void getType1989(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11476));
    }
    @Test
    public void getType1990(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11477));
    }
    @Test
    public void getType1991(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11478));
    }
    @Test
    public void getType1992(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11479));
    }
    @Test
    public void getType1993(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11480));
    }
    @Test
    public void getType1994(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11481));
    }
    @Test
    public void getType1995(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11482));
    }
    @Test
    public void getType1996(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11483));
    }
    @Test
    public void getType1997(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11484));
    }
    @Test
    public void getType1998(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11485));
    }
    @Test
    public void getType1999(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11486));
    }
    @Test
    public void getType2000(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11487));
    }
    @Test
    public void getType2001(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11488));
    }
    @Test
    public void getType2002(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11489));
    }
    @Test
    public void getType2003(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11490));
    }
    @Test
    public void getType2004(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11491));
    }
    @Test
    public void getType2005(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11492));
    }
    @Test
    public void getType2006(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 11493));
    }
    @Test
    public void getType2007(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11499));
    }
    @Test
    public void getType2008(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11500));
    }
    @Test
    public void getType2009(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11501));
    }
    @Test
    public void getType2010(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11502));
    }
    @Test
    public void getType2011(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 11503));
    }
    @Test
    public void getType2012(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 11506));
    }
    @Test
    public void getType2013(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11507));
    }
    @Test
    public void getType2014(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11508));
    }
    @Test
    public void getType2015(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 11513));
    }
    @Test
    public void getType2016(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 11517));
    }
    @Test
    public void getType2017(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 11518));
    }
    @Test
    public void getType2018(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11520));
    }
    @Test
    public void getType2019(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11558));
    }
    @Test
    public void getType2020(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11559));
    }
    @Test
    public void getType2021(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11560));
    }
    @Test
    public void getType2022(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 11565));
    }
    @Test
    public void getType2023(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11566));
    }
    @Test
    public void getType2024(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 11568));
    }
    @Test
    public void getType2025(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11624));
    }
    @Test
    public void getType2026(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 11631));
    }
    @Test
    public void getType2027(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 11632));
    }
    @Test
    public void getType2028(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11633));
    }
    @Test
    public void getType2029(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 11647));
    }
    @Test
    public void getType2030(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 11648));
    }
    @Test
    public void getType2031(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11671));
    }
    @Test
    public void getType2032(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 11680));
    }
    @Test
    public void getType2033(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11687));
    }
    @Test
    public void getType2034(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 11688));
    }
    @Test
    public void getType2035(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11695));
    }
    @Test
    public void getType2036(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 11696));
    }
    @Test
    public void getType2037(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11703));
    }
    @Test
    public void getType2038(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 11704));
    }
    @Test
    public void getType2039(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11711));
    }
    @Test
    public void getType2040(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 11712));
    }
    @Test
    public void getType2041(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11719));
    }
    @Test
    public void getType2042(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 11720));
    }
    @Test
    public void getType2043(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11727));
    }
    @Test
    public void getType2044(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 11728));
    }
    @Test
    public void getType2045(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11735));
    }
    @Test
    public void getType2046(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 11736));
    }
    @Test
    public void getType2047(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11743));
    }
    @Test
    public void getType2048(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 11744));
    }
    @Test
    public void getType2049(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 11776));
    }
    @Test
    public void getType2050(){
        assertEq(StringExpUtil.PUNCTUATION_QUOTE, StringExpUtil.getCustomType((char) 11778));
    }
    @Test
    public void getType2051(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 11782));
    }
    @Test
    public void getType2052(){
        assertEq(StringExpUtil.PUNCTUATION_QUOTE, StringExpUtil.getCustomType((char) 11785));
    }
    @Test
    public void getType2053(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 11787));
    }
    @Test
    public void getType2054(){
        assertEq(StringExpUtil.PUNCTUATION_QUOTE, StringExpUtil.getCustomType((char) 11788));
    }
    @Test
    public void getType2055(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 11790));
    }
    @Test
    public void getType2056(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 11799));
    }
    @Test
    public void getType2057(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 11800));
    }
    @Test
    public void getType2058(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 11802));
    }
    @Test
    public void getType2059(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 11803));
    }
    @Test
    public void getType2060(){
        assertEq(StringExpUtil.PUNCTUATION_QUOTE, StringExpUtil.getCustomType((char) 11804));
    }
    @Test
    public void getType2061(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 11806));
    }
    @Test
    public void getType2062(){
        assertEq(StringExpUtil.PUNCTUATION_QUOTE, StringExpUtil.getCustomType((char) 11808));
    }
    @Test
    public void getType2063(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 11810));
    }
    @Test
    public void getType2064(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 11818));
    }
    @Test
    public void getType2065(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringExpUtil.getCustomType((char) 11823));
    }
    @Test
    public void getType2066(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 11824));
    }
    @Test
    public void getType2067(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 11834));
    }
    @Test
    public void getType2068(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11836));
    }
    @Test
    public void getType2069(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 11904));
    }
    @Test
    public void getType2070(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 11930));
    }
    @Test
    public void getType2071(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 11931));
    }
    @Test
    public void getType2072(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 12020));
    }
    @Test
    public void getType2073(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12032));
    }
    @Test
    public void getType2074(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 12246));
    }
    @Test
    public void getType2075(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12272));
    }
    @Test
    public void getType2076(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 12284));
    }
    @Test
    public void getType2077(){
        assertEq(StringExpUtil.SPACE_OTHER, StringExpUtil.getCustomType((char) 12288));
    }
    @Test
    public void getType2078(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 12289));
    }
    @Test
    public void getType2079(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12292));
    }
    @Test
    public void getType2080(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 12293));
    }
    @Test
    public void getType2081(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 12295));
    }
    @Test
    public void getType2082(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 12296));
    }
    @Test
    public void getType2083(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12306));
    }
    @Test
    public void getType2084(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 12308));
    }
    @Test
    public void getType2085(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 12316));
    }
    @Test
    public void getType2086(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 12317));
    }
    @Test
    public void getType2087(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12320));
    }
    @Test
    public void getType2088(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 12321));
    }
    @Test
    public void getType2089(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 12336));
    }
    @Test
    public void getType2090(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 12337));
    }
    @Test
    public void getType2091(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12342));
    }
    @Test
    public void getType2092(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 12344));
    }
    @Test
    public void getType2093(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 12347));
    }
    @Test
    public void getType2094(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 12349));
    }
    @Test
    public void getType2095(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12350));
    }
    @Test
    public void getType2096(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 12352));
    }
    @Test
    public void getType2097(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 12353));
    }
    @Test
    public void getType2098(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 12439));
    }
    @Test
    public void getType2099(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 12441));
    }
    @Test
    public void getType2100(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 12443));
    }
    @Test
    public void getType2101(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 12445));
    }
    @Test
    public void getType2102(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 12448));
    }
    @Test
    public void getType2103(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 12449));
    }
    @Test
    public void getType2104(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 12539));
    }
    @Test
    public void getType2105(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 12540));
    }
    @Test
    public void getType2106(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 12544));
    }
    @Test
    public void getType2107(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 12549));
    }
    @Test
    public void getType2108(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 12590));
    }
    @Test
    public void getType2109(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 12593));
    }
    @Test
    public void getType2110(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 12687));
    }
    @Test
    public void getType2111(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12688));
    }
    @Test
    public void getType2112(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 12690));
    }
    @Test
    public void getType2113(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12694));
    }
    @Test
    public void getType2114(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 12704));
    }
    @Test
    public void getType2115(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 12731));
    }
    @Test
    public void getType2116(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12736));
    }
    @Test
    public void getType2117(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 12772));
    }
    @Test
    public void getType2118(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 12784));
    }
    @Test
    public void getType2119(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12800));
    }
    @Test
    public void getType2120(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 12831));
    }
    @Test
    public void getType2121(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 12832));
    }
    @Test
    public void getType2122(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12842));
    }
    @Test
    public void getType2123(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 12872));
    }
    @Test
    public void getType2124(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12880));
    }
    @Test
    public void getType2125(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 12881));
    }
    @Test
    public void getType2126(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12896));
    }
    @Test
    public void getType2127(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 12928));
    }
    @Test
    public void getType2128(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12938));
    }
    @Test
    public void getType2129(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 12977));
    }
    @Test
    public void getType2130(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 12992));
    }
    @Test
    public void getType2131(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 13055));
    }
    @Test
    public void getType2132(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 13056));
    }
    @Test
    public void getType2133(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 13312));
    }
    @Test
    public void getType2134(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 19894));
    }
    @Test
    public void getType2135(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 19904));
    }
    @Test
    public void getType2136(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 19968));
    }
    @Test
    public void getType2137(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 40909));
    }
    @Test
    public void getType2138(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 40960));
    }
    @Test
    public void getType2139(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 42125));
    }
    @Test
    public void getType2140(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 42128));
    }
    @Test
    public void getType2141(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 42183));
    }
    @Test
    public void getType2142(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 42192));
    }
    @Test
    public void getType2143(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 42238));
    }
    @Test
    public void getType2144(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 42240));
    }
    @Test
    public void getType2145(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 42509));
    }
    @Test
    public void getType2146(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 42512));
    }
    @Test
    public void getType2147(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 42528));
    }
    @Test
    public void getType2148(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 42538));
    }
    @Test
    public void getType2149(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 42540));
    }
    @Test
    public void getType2150(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42560));
    }
    @Test
    public void getType2151(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42561));
    }
    @Test
    public void getType2152(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42562));
    }
    @Test
    public void getType2153(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42563));
    }
    @Test
    public void getType2154(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42564));
    }
    @Test
    public void getType2155(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42565));
    }
    @Test
    public void getType2156(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42566));
    }
    @Test
    public void getType2157(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42567));
    }
    @Test
    public void getType2158(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42568));
    }
    @Test
    public void getType2159(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42569));
    }
    @Test
    public void getType2160(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42570));
    }
    @Test
    public void getType2161(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42571));
    }
    @Test
    public void getType2162(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42572));
    }
    @Test
    public void getType2163(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42573));
    }
    @Test
    public void getType2164(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42574));
    }
    @Test
    public void getType2165(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42575));
    }
    @Test
    public void getType2166(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42576));
    }
    @Test
    public void getType2167(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42577));
    }
    @Test
    public void getType2168(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42578));
    }
    @Test
    public void getType2169(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42579));
    }
    @Test
    public void getType2170(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42580));
    }
    @Test
    public void getType2171(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42581));
    }
    @Test
    public void getType2172(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42582));
    }
    @Test
    public void getType2173(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42583));
    }
    @Test
    public void getType2174(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42584));
    }
    @Test
    public void getType2175(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42585));
    }
    @Test
    public void getType2176(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42586));
    }
    @Test
    public void getType2177(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42587));
    }
    @Test
    public void getType2178(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42588));
    }
    @Test
    public void getType2179(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42589));
    }
    @Test
    public void getType2180(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42590));
    }
    @Test
    public void getType2181(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42591));
    }
    @Test
    public void getType2182(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42592));
    }
    @Test
    public void getType2183(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42593));
    }
    @Test
    public void getType2184(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42594));
    }
    @Test
    public void getType2185(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42595));
    }
    @Test
    public void getType2186(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42596));
    }
    @Test
    public void getType2187(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42597));
    }
    @Test
    public void getType2188(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42598));
    }
    @Test
    public void getType2189(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42599));
    }
    @Test
    public void getType2190(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42600));
    }
    @Test
    public void getType2191(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42601));
    }
    @Test
    public void getType2192(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42602));
    }
    @Test
    public void getType2193(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42603));
    }
    @Test
    public void getType2194(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42604));
    }
    @Test
    public void getType2195(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42605));
    }
    @Test
    public void getType2196(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 42606));
    }
    @Test
    public void getType2197(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 42607));
    }
    @Test
    public void getType2198(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 42611));
    }
    @Test
    public void getType2199(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 42612));
    }
    @Test
    public void getType2200(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 42622));
    }
    @Test
    public void getType2201(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringExpUtil.getCustomType((char) 42623));
    }
    @Test
    public void getType2202(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42624));
    }
    @Test
    public void getType2203(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42625));
    }
    @Test
    public void getType2204(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42626));
    }
    @Test
    public void getType2205(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42627));
    }
    @Test
    public void getType2206(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42628));
    }
    @Test
    public void getType2207(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42629));
    }
    @Test
    public void getType2208(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42630));
    }
    @Test
    public void getType2209(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42631));
    }
    @Test
    public void getType2210(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42632));
    }
    @Test
    public void getType2211(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42633));
    }
    @Test
    public void getType2212(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42634));
    }
    @Test
    public void getType2213(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42635));
    }
    @Test
    public void getType2214(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42636));
    }
    @Test
    public void getType2215(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42637));
    }
    @Test
    public void getType2216(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42638));
    }
    @Test
    public void getType2217(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42639));
    }
    @Test
    public void getType2218(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42640));
    }
    @Test
    public void getType2219(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42641));
    }
    @Test
    public void getType2220(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42642));
    }
    @Test
    public void getType2221(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42643));
    }
    @Test
    public void getType2222(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42644));
    }
    @Test
    public void getType2223(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42645));
    }
    @Test
    public void getType2224(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42646));
    }
    @Test
    public void getType2225(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42647));
    }
    @Test
    public void getType2226(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 42648));
    }
    @Test
    public void getType2227(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 42655));
    }
    @Test
    public void getType2228(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 42656));
    }
    @Test
    public void getType2229(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 42726));
    }
    @Test
    public void getType2230(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 42738));
    }
    @Test
    public void getType2231(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 42744));
    }
    @Test
    public void getType2232(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 42752));
    }
    @Test
    public void getType2233(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringExpUtil.getCustomType((char) 42775));
    }
    @Test
    public void getType2234(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 42784));
    }
    @Test
    public void getType2235(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42786));
    }
    @Test
    public void getType2236(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42787));
    }
    @Test
    public void getType2237(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42788));
    }
    @Test
    public void getType2238(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42789));
    }
    @Test
    public void getType2239(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42790));
    }
    @Test
    public void getType2240(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42791));
    }
    @Test
    public void getType2241(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42792));
    }
    @Test
    public void getType2242(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42793));
    }
    @Test
    public void getType2243(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42794));
    }
    @Test
    public void getType2244(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42795));
    }
    @Test
    public void getType2245(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42796));
    }
    @Test
    public void getType2246(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42797));
    }
    @Test
    public void getType2247(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42798));
    }
    @Test
    public void getType2248(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42799));
    }
    @Test
    public void getType2249(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42800));
    }
    @Test
    public void getType2250(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42802));
    }
    @Test
    public void getType2251(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42803));
    }
    @Test
    public void getType2252(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42804));
    }
    @Test
    public void getType2253(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42805));
    }
    @Test
    public void getType2254(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42806));
    }
    @Test
    public void getType2255(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42807));
    }
    @Test
    public void getType2256(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42808));
    }
    @Test
    public void getType2257(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42809));
    }
    @Test
    public void getType2258(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42810));
    }
    @Test
    public void getType2259(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42811));
    }
    @Test
    public void getType2260(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42812));
    }
    @Test
    public void getType2261(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42813));
    }
    @Test
    public void getType2262(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42814));
    }
    @Test
    public void getType2263(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42815));
    }
    @Test
    public void getType2264(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42816));
    }
    @Test
    public void getType2265(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42817));
    }
    @Test
    public void getType2266(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42818));
    }
    @Test
    public void getType2267(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42819));
    }
    @Test
    public void getType2268(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42820));
    }
    @Test
    public void getType2269(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42821));
    }
    @Test
    public void getType2270(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42822));
    }
    @Test
    public void getType2271(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42823));
    }
    @Test
    public void getType2272(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42824));
    }
    @Test
    public void getType2273(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42825));
    }
    @Test
    public void getType2274(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42826));
    }
    @Test
    public void getType2275(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42827));
    }
    @Test
    public void getType2276(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42828));
    }
    @Test
    public void getType2277(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42829));
    }
    @Test
    public void getType2278(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42830));
    }
    @Test
    public void getType2279(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42831));
    }
    @Test
    public void getType2280(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42832));
    }
    @Test
    public void getType2281(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42833));
    }
    @Test
    public void getType2282(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42834));
    }
    @Test
    public void getType2283(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42835));
    }
    @Test
    public void getType2284(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42836));
    }
    @Test
    public void getType2285(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42837));
    }
    @Test
    public void getType2286(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42838));
    }
    @Test
    public void getType2287(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42839));
    }
    @Test
    public void getType2288(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42840));
    }
    @Test
    public void getType2289(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42841));
    }
    @Test
    public void getType2290(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42842));
    }
    @Test
    public void getType2291(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42843));
    }
    @Test
    public void getType2292(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42844));
    }
    @Test
    public void getType2293(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42845));
    }
    @Test
    public void getType2294(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42846));
    }
    @Test
    public void getType2295(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42847));
    }
    @Test
    public void getType2296(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42848));
    }
    @Test
    public void getType2297(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42849));
    }
    @Test
    public void getType2298(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42850));
    }
    @Test
    public void getType2299(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42851));
    }
    @Test
    public void getType2300(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42852));
    }
    @Test
    public void getType2301(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42853));
    }
    @Test
    public void getType2302(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42854));
    }
    @Test
    public void getType2303(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42855));
    }
    @Test
    public void getType2304(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42856));
    }
    @Test
    public void getType2305(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42857));
    }
    @Test
    public void getType2306(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42858));
    }
    @Test
    public void getType2307(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42859));
    }
    @Test
    public void getType2308(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42860));
    }
    @Test
    public void getType2309(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42861));
    }
    @Test
    public void getType2310(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42862));
    }
    @Test
    public void getType2311(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42863));
    }
    @Test
    public void getType2312(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42864));
    }
    @Test
    public void getType2313(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42873));
    }
    @Test
    public void getType2314(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42874));
    }
    @Test
    public void getType2315(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42875));
    }
    @Test
    public void getType2316(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42876));
    }
    @Test
    public void getType2317(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42877));
    }
    @Test
    public void getType2318(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42879));
    }
    @Test
    public void getType2319(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42880));
    }
    @Test
    public void getType2320(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42881));
    }
    @Test
    public void getType2321(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42882));
    }
    @Test
    public void getType2322(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42883));
    }
    @Test
    public void getType2323(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42884));
    }
    @Test
    public void getType2324(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42885));
    }
    @Test
    public void getType2325(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42886));
    }
    @Test
    public void getType2326(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42887));
    }
    @Test
    public void getType2327(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DIR_OTHER_NEUTRALS, StringExpUtil.getCustomType((char) 42888));
    }
    @Test
    public void getType2328(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 42889));
    }
    @Test
    public void getType2329(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42891));
    }
    @Test
    public void getType2330(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42892));
    }
    @Test
    public void getType2331(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42893));
    }
    @Test
    public void getType2332(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42894));
    }
    @Test
    public void getType2333(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 42895));
    }
    @Test
    public void getType2334(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42896));
    }
    @Test
    public void getType2335(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42897));
    }
    @Test
    public void getType2336(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42898));
    }
    @Test
    public void getType2337(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42899));
    }
    @Test
    public void getType2338(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 42900));
    }
    @Test
    public void getType2339(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42912));
    }
    @Test
    public void getType2340(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42913));
    }
    @Test
    public void getType2341(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42914));
    }
    @Test
    public void getType2342(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42915));
    }
    @Test
    public void getType2343(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42916));
    }
    @Test
    public void getType2344(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42917));
    }
    @Test
    public void getType2345(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42918));
    }
    @Test
    public void getType2346(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42919));
    }
    @Test
    public void getType2347(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42920));
    }
    @Test
    public void getType2348(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 42921));
    }
    @Test
    public void getType2349(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 42922));
    }
    @Test
    public void getType2350(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 42923));
    }
    @Test
    public void getType2351(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 43000));
    }
    @Test
    public void getType2352(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43003));
    }
    @Test
    public void getType2353(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43010));
    }
    @Test
    public void getType2354(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43011));
    }
    @Test
    public void getType2355(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43014));
    }
    @Test
    public void getType2356(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43015));
    }
    @Test
    public void getType2357(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43019));
    }
    @Test
    public void getType2358(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43020));
    }
    @Test
    public void getType2359(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43043));
    }
    @Test
    public void getType2360(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 43048));
    }
    @Test
    public void getType2361(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43052));
    }
    @Test
    public void getType2362(){
        assertEq(StringExpUtil.LETTERS_DIGITS_OTHER, StringExpUtil.getCustomType((char) 43056));
    }
    @Test
    public void getType2363(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 43062));
    }
    @Test
    public void getType2364(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 43064));
    }
    @Test
    public void getType2365(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 43065));
    }
    @Test
    public void getType2366(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43066));
    }
    @Test
    public void getType2367(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43072));
    }
    @Test
    public void getType2368(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 43124));
    }
    @Test
    public void getType2369(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43128));
    }
    @Test
    public void getType2370(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43136));
    }
    @Test
    public void getType2371(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43138));
    }
    @Test
    public void getType2372(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43188));
    }
    @Test
    public void getType2373(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43205));
    }
    @Test
    public void getType2374(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 43214));
    }
    @Test
    public void getType2375(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 43216));
    }
    @Test
    public void getType2376(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43226));
    }
    @Test
    public void getType2377(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43232));
    }
    @Test
    public void getType2378(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43250));
    }
    @Test
    public void getType2379(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 43256));
    }
    @Test
    public void getType2380(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43259));
    }
    @Test
    public void getType2381(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43260));
    }
    @Test
    public void getType2382(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 43264));
    }
    @Test
    public void getType2383(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43274));
    }
    @Test
    public void getType2384(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43302));
    }
    @Test
    public void getType2385(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 43310));
    }
    @Test
    public void getType2386(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43312));
    }
    @Test
    public void getType2387(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43335));
    }
    @Test
    public void getType2388(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43348));
    }
    @Test
    public void getType2389(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 43359));
    }
    @Test
    public void getType2390(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43360));
    }
    @Test
    public void getType2391(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43389));
    }
    @Test
    public void getType2392(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43392));
    }
    @Test
    public void getType2393(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43396));
    }
    @Test
    public void getType2394(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43443));
    }
    @Test
    public void getType2395(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 43457));
    }
    @Test
    public void getType2396(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43470));
    }
    @Test
    public void getType2397(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43471));
    }
    @Test
    public void getType2398(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 43472));
    }
    @Test
    public void getType2399(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43482));
    }
    @Test
    public void getType2400(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 43486));
    }
    @Test
    public void getType2401(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43488));
    }
    @Test
    public void getType2402(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43520));
    }
    @Test
    public void getType2403(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43561));
    }
    @Test
    public void getType2404(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43575));
    }
    @Test
    public void getType2405(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43584));
    }
    @Test
    public void getType2406(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43587));
    }
    @Test
    public void getType2407(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43588));
    }
    @Test
    public void getType2408(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43596));
    }
    @Test
    public void getType2409(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43598));
    }
    @Test
    public void getType2410(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 43600));
    }
    @Test
    public void getType2411(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43610));
    }
    @Test
    public void getType2412(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 43612));
    }
    @Test
    public void getType2413(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43616));
    }
    @Test
    public void getType2414(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 43639));
    }
    @Test
    public void getType2415(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43642));
    }
    @Test
    public void getType2416(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43643));
    }
    @Test
    public void getType2417(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43644));
    }
    @Test
    public void getType2418(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43648));
    }
    @Test
    public void getType2419(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43696));
    }
    @Test
    public void getType2420(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43697));
    }
    @Test
    public void getType2421(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43698));
    }
    @Test
    public void getType2422(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43701));
    }
    @Test
    public void getType2423(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43703));
    }
    @Test
    public void getType2424(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43705));
    }
    @Test
    public void getType2425(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43710));
    }
    @Test
    public void getType2426(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43712));
    }
    @Test
    public void getType2427(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43713));
    }
    @Test
    public void getType2428(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43714));
    }
    @Test
    public void getType2429(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43715));
    }
    @Test
    public void getType2430(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43739));
    }
    @Test
    public void getType2431(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 43742));
    }
    @Test
    public void getType2432(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43744));
    }
    @Test
    public void getType2433(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43755));
    }
    @Test
    public void getType2434(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 43760));
    }
    @Test
    public void getType2435(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43762));
    }
    @Test
    public void getType2436(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 43765));
    }
    @Test
    public void getType2437(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43767));
    }
    @Test
    public void getType2438(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43777));
    }
    @Test
    public void getType2439(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43783));
    }
    @Test
    public void getType2440(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43785));
    }
    @Test
    public void getType2441(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43791));
    }
    @Test
    public void getType2442(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43793));
    }
    @Test
    public void getType2443(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43799));
    }
    @Test
    public void getType2444(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43808));
    }
    @Test
    public void getType2445(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43815));
    }
    @Test
    public void getType2446(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43816));
    }
    @Test
    public void getType2447(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 43823));
    }
    @Test
    public void getType2448(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 43968));
    }
    @Test
    public void getType2449(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 44003));
    }
    @Test
    public void getType2450(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 44011));
    }
    @Test
    public void getType2451(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 44012));
    }
    @Test
    public void getType2452(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 44014));
    }
    @Test
    public void getType2453(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 44016));
    }
    @Test
    public void getType2454(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 44026));
    }
    @Test
    public void getType2455(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 44032));
    }
    @Test
    public void getType2456(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 55204));
    }
    @Test
    public void getType2457(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 55216));
    }
    @Test
    public void getType2458(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 55239));
    }
    @Test
    public void getType2459(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 55243));
    }
    @Test
    public void getType2460(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 55292));
    }
    @Test
    public void getType2461(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 55296));
    }
    @Test
    public void getType2462(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 63744));
    }
    @Test
    public void getType2463(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64110));
    }
    @Test
    public void getType2464(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 64112));
    }
    @Test
    public void getType2465(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64218));
    }
    @Test
    public void getType2466(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 64256));
    }
    @Test
    public void getType2467(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64263));
    }
    @Test
    public void getType2468(){
        assertEq(StringExpUtil.LETTER_INSENS_LOWER_CASE, StringExpUtil.getCustomType((char) 64275));
    }
    @Test
    public void getType2469(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64280));
    }
    @Test
    public void getType2470(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 64285));
    }
    @Test
    public void getType2471(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 64286));
    }
    @Test
    public void getType2472(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 64287));
    }
    @Test
    public void getType2473(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 64297));
    }
    @Test
    public void getType2474(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 64298));
    }
    @Test
    public void getType2475(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64311));
    }
    @Test
    public void getType2476(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 64312));
    }
    @Test
    public void getType2477(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64317));
    }
    @Test
    public void getType2478(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 64318));
    }
    @Test
    public void getType2479(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64319));
    }
    @Test
    public void getType2480(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 64320));
    }
    @Test
    public void getType2481(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64322));
    }
    @Test
    public void getType2482(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 64323));
    }
    @Test
    public void getType2483(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64325));
    }
    @Test
    public void getType2484(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 64326));
    }
    @Test
    public void getType2485(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 64434));
    }
    @Test
    public void getType2486(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64450));
    }
    @Test
    public void getType2487(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 64467));
    }
    @Test
    public void getType2488(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 64830));
    }
    @Test
    public void getType2489(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64832));
    }
    @Test
    public void getType2490(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 64848));
    }
    @Test
    public void getType2491(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64912));
    }
    @Test
    public void getType2492(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 64914));
    }
    @Test
    public void getType2493(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 64968));
    }
    @Test
    public void getType2494(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 65008));
    }
    @Test
    public void getType2495(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 65020));
    }
    @Test
    public void getType2496(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 65021));
    }
    @Test
    public void getType2497(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65022));
    }
    @Test
    public void getType2498(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 65024));
    }
    @Test
    public void getType2499(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65040));
    }
    @Test
    public void getType2500(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 65047));
    }
    @Test
    public void getType2501(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65049));
    }
    @Test
    public void getType2502(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65050));
    }
    @Test
    public void getType2503(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 65056));
    }
    @Test
    public void getType2504(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65063));
    }
    @Test
    public void getType2505(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65072));
    }
    @Test
    public void getType2506(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 65073));
    }
    @Test
    public void getType2507(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 65077));
    }
    @Test
    public void getType2508(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65093));
    }
    @Test
    public void getType2509(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 65095));
    }
    @Test
    public void getType2510(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65097));
    }
    @Test
    public void getType2511(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 65101));
    }
    @Test
    public void getType2512(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65104));
    }
    @Test
    public void getType2513(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65107));
    }
    @Test
    public void getType2514(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65108));
    }
    @Test
    public void getType2515(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 65112));
    }
    @Test
    public void getType2516(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 65113));
    }
    @Test
    public void getType2517(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65119));
    }
    @Test
    public void getType2518(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 65122));
    }
    @Test
    public void getType2519(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 65123));
    }
    @Test
    public void getType2520(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 65124));
    }
    @Test
    public void getType2521(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65127));
    }
    @Test
    public void getType2522(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65128));
    }
    @Test
    public void getType2523(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 65129));
    }
    @Test
    public void getType2524(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65130));
    }
    @Test
    public void getType2525(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65132));
    }
    @Test
    public void getType2526(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 65136));
    }
    @Test
    public void getType2527(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65141));
    }
    @Test
    public void getType2528(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_RIGHT_TO_LEFT, StringExpUtil.getCustomType((char) 65142));
    }
    @Test
    public void getType2529(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65277));
    }
    @Test
    public void getType2530(){
        assertEq(StringExpUtil.SEPARATOR, StringExpUtil.getCustomType((char) 65279));
    }
    @Test
    public void getType2531(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65280));
    }
    @Test
    public void getType2532(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65281));
    }
    @Test
    public void getType2533(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 65284));
    }
    @Test
    public void getType2534(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65285));
    }
    @Test
    public void getType2535(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 65288));
    }
    @Test
    public void getType2536(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65290));
    }
    @Test
    public void getType2537(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 65291));
    }
    @Test
    public void getType2538(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65292));
    }
    @Test
    public void getType2539(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 65293));
    }
    @Test
    public void getType2540(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65294));
    }
    @Test
    public void getType2541(){
        assertEq(StringExpUtil.DIGIT_OTHER, StringExpUtil.getCustomType((char) 65296));
    }
    @Test
    public void getType2542(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65306));
    }
    @Test
    public void getType2543(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 65308));
    }
    @Test
    public void getType2544(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65311));
    }
    @Test
    public void getType2545(){
        assertEq(StringExpUtil.LETTER_SENS_UPPER_CASE, StringExpUtil.getCustomType((char) 65313));
    }
    @Test
    public void getType2546(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 65339));
    }
    @Test
    public void getType2547(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65340));
    }
    @Test
    public void getType2548(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 65341));
    }
    @Test
    public void getType2549(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 65342));
    }
    @Test
    public void getType2550(){
        assertEq(StringExpUtil.PUNCTUATION_CONNECTOR, StringExpUtil.getCustomType((char) 65343));
    }
    @Test
    public void getType2551(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 65344));
    }
    @Test
    public void getType2552(){
        assertEq(StringExpUtil.LETTER_SENS_LOWER_CASE, StringExpUtil.getCustomType((char) 65345));
    }
    @Test
    public void getType2553(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 65371));
    }
    @Test
    public void getType2554(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 65372));
    }
    @Test
    public void getType2555(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 65373));
    }
    @Test
    public void getType2556(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 65374));
    }
    @Test
    public void getType2557(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 65375));
    }
    @Test
    public void getType2558(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65377));
    }
    @Test
    public void getType2559(){
        assertEq(StringExpUtil.PUNCTUATION_BOUND, StringExpUtil.getCustomType((char) 65378));
    }
    @Test
    public void getType2560(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 65380));
    }
    @Test
    public void getType2561(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 65382));
    }
    @Test
    public void getType2562(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65471));
    }
    @Test
    public void getType2563(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 65474));
    }
    @Test
    public void getType2564(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65480));
    }
    @Test
    public void getType2565(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 65482));
    }
    @Test
    public void getType2566(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65488));
    }
    @Test
    public void getType2567(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 65490));
    }
    @Test
    public void getType2568(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65496));
    }
    @Test
    public void getType2569(){
        assertEq(StringExpUtil.LETTER_INSENS_NO_CASE_DEF_DIR, StringExpUtil.getCustomType((char) 65498));
    }
    @Test
    public void getType2570(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65501));
    }
    @Test
    public void getType2571(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 65504));
    }
    @Test
    public void getType2572(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 65506));
    }
    @Test
    public void getType2573(){
        assertEq(StringExpUtil.SYMBOL_MODIFIER, StringExpUtil.getCustomType((char) 65507));
    }
    @Test
    public void getType2574(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 65508));
    }
    @Test
    public void getType2575(){
        assertEq(StringExpUtil.CURRENCY, StringExpUtil.getCustomType((char) 65509));
    }
    @Test
    public void getType2576(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65511));
    }
    @Test
    public void getType2577(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 65512));
    }
    @Test
    public void getType2578(){
        assertEq(StringExpUtil.MATH, StringExpUtil.getCustomType((char) 65513));
    }
    @Test
    public void getType2579(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 65517));
    }
    @Test
    public void getType2580(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65519));
    }
    @Test
    public void getType2581(){
        assertEq(StringExpUtil.SEPARATOR, StringExpUtil.getCustomType((char) 65529));
    }
    @Test
    public void getType2582(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 65532));
    }
    @Test
    public void getType2583(){
        assertEq(StringExpUtil.UNASSIGNED, StringExpUtil.getCustomType((char) 65534));
    }
    @Test
    public void getType2584(){
        assertEq(StringExpUtil.SYMBOL_OTHER, StringExpUtil.getCustomType((char) 8598));
    }
    @Test
    public void getType2585(){
        assertEq(StringExpUtil.PUNCTUATION_OTHER, StringExpUtil.getCustomType((char) 6818));
    }
    @Test
    public void getType2586(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 38));
    }
    @Test
    public void getType2587(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 43));
    }
    @Test
    public void getType2588(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 61));
    }
    @Test
    public void getType2589(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 62));
    }
    @Test
    public void getType2590(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 63));
    }
    @Test
    public void getType2591(){
        assertEq(StringExpUtil.OPERATOR_LANGUAGE, StringExpUtil.getCustomType((char) 64));
    }
    @Test
    public void getType2592(){
        assertEq(StringExpUtil.SPACE, StringExpUtil.getCustomType((char) 10));
    }
    @Test
    public void getType2593(){
        assertEq(StringExpUtil.OTHER, StringExpUtil.getCustomType((char) 837));
    }

    @Test
    public void isLetter1() {
        assertTrue(StringExpUtil.isLetter('a'));
    }

    @Test
    public void isLetter2() {
        assertTrue(!StringExpUtil.isLetter('1'));
    }
    @Test
    public void isLetter3() {
        assertTrue(StringExpUtil.isLetter((char)65500));
    }

    @Test
    public void isLetter4() {
        assertTrue(!StringExpUtil.isLetter((char)65501));
    }

    @Test
    public void isLetter5() {
        assertTrue(!StringExpUtil.isLetter((char)215));
    }

    @Test
    public void isLetter6() {
        assertTrue(!StringExpUtil.isLetter((char)191));
    }

    @Test
    public void isLetter7() {
        assertTrue(!StringExpUtil.isLetter((char)128));
    }

    @Test
    public void isLetter8() {
        assertTrue(!StringExpUtil.isLetter('_'));
    }

    @Test
    public void isLetter9() {
        assertTrue(StringExpUtil.isLetter('A'));
    }
    @Test
    public void isLetterOrDigit1() {
        assertTrue(StringExpUtil.isLetterOrDigit((char)65500));
    }

    @Test
    public void isLetterOrDigit2() {
        assertTrue(!StringExpUtil.isLetterOrDigit((char)65501));
    }

    @Test
    public void isLetterOrDigit3() {
        assertTrue(!StringExpUtil.isLetterOrDigit((char)247));
    }

    @Test
    public void isLetterOrDigit4() {
        assertTrue(StringExpUtil.isLetterOrDigit((char)192));
    }

    @Test
    public void isLetterOrDigit5() {
        assertTrue(!StringExpUtil.isLetterOrDigit((char)191));
    }

    @Test
    public void isLetterOrDigit6() {
        assertTrue(!StringExpUtil.isLetterOrDigit((char)128));
    }

    @Test
    public void isLetterOrDigit7() {
        assertTrue(!StringExpUtil.isLetterOrDigit('_'));
    }

    @Test
    public void isLetterOrDigit8() {
        assertTrue(StringExpUtil.isLetterOrDigit('A'));
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
            int dir_ = StringExpUtil.getDirectionality((char) i);
            int type_ = StringExpUtil.getType((char) i);
            max_ = Math.max(dir_,max_);
            min_ = Math.min(dir_,min_);
            maxType_ = Math.max(type_,maxType_);
            minType_ = Math.min(type_,minType_);
            if (StringExpUtil.isLetter((char) i)) {
                if (minLetter_ == -1) {
                    minLetter_ = i;
                }
                maxLetter_ = i;
            }
            if (StringExpUtil.isLetterOrDigit((char) i)) {
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
        assertTrue(ClassMetaInfo.isNotVariable(null,""));
        assertTrue(ClassMetaInfo.isNotVariable(null,"#"));
        assertTrue(ClassMetaInfo.isNotVariable(0,""));
        assertTrue(!ClassMetaInfo.isNotVariable(0,"#"));
    }
    @Test
    public void toLowerCaseTest() {
        assertEq("0", StringExpUtil.toLowerCase("0"));
    }
    @Test
    public void toUpperCaseTest() {
        assertEq("0", StringExpUtil.toUpperCase("0"));
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
