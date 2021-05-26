package code.expressionlanguage.common;

final class StringDataUndefinedUtil {
    private StringDataUndefinedUtil() {
    }

    static boolean isUnassigned(char _ch) {
        return rgBounds(_ch, 888, 889) || rgBounds(_ch, 895, 899) || _ch == 907 || _ch == 909 || _ch == 930 || rgBounds(_ch, 1320, 1328) || rgBounds(_ch, 1367, 1368) || _ch == 1376 || _ch == 1416 || isUnassigned47(_ch);
    }

    private static boolean isUnassigned47(char _ch) {
        return rgBounds(_ch, 1419, 1422) || _ch == 1424 || rgBounds(_ch, 1480, 1487) || rgBounds(_ch, 1515, 1519) || rgBounds(_ch, 1525, 1535) || _ch == 1541 || rgBounds(_ch, 1564, 1565) || isUnassigned46(_ch);
    }

    private static boolean isUnassigned46(char _ch) {
        return _ch == 1806 || rgBounds(_ch, 1867, 1868) || rgBounds(_ch, 1970, 1983) || rgBounds(_ch, 2043, 2047) || rgBounds(_ch, 2094, 2095) || _ch == 2111 || rgBounds(_ch, 2140, 2141) || isUnassigned45(_ch);
    }

    private static boolean isUnassigned45(char _ch) {
        return rgBounds(_ch, 2143, 2207) || _ch == 2209 || rgBounds(_ch, 2221, 2275) || _ch == 2303 || _ch == 2424 || _ch == 2432 || _ch == 2436 || rgBounds(_ch, 2445, 2446) || rgBounds(_ch, 2449, 2450) || _ch == 2473 || _ch == 2481 || isUnassigned44(_ch);
    }

    private static boolean isUnassigned44(char _ch) {
        return rgBounds(_ch, 2483, 2485) || rgBounds(_ch, 2490, 2491) || rgBounds(_ch, 2501, 2502) || rgBounds(_ch, 2505, 2506) || rgBounds(_ch, 2511, 2518) || rgBounds(_ch, 2520, 2523) || _ch == 2526 || isUnassigned43(_ch);
    }

    private static boolean isUnassigned43(char _ch) {
        return rgBounds(_ch, 2532, 2533) || rgBounds(_ch, 2556, 2560) || _ch == 2564 || rgBounds(_ch, 2571, 2574) || rgBounds(_ch, 2577, 2578) || _ch == 2601 || _ch == 2609 || _ch == 2612 || isUnassigned42(_ch);
    }

    private static boolean isUnassigned42(char _ch) {
        return _ch == 2615 || rgBounds(_ch, 2618, 2619) || _ch == 2621 || rgBounds(_ch, 2627, 2630) || rgBounds(_ch, 2633, 2634) || rgBounds(_ch, 2638, 2640) || rgBounds(_ch, 2642, 2648) || isUnassigned41(_ch);
    }

    private static boolean isUnassigned41(char _ch) {
        return _ch == 2653 || rgBounds(_ch, 2655, 2661) || rgBounds(_ch, 2678, 2688) || _ch == 2692 || _ch == 2702 || _ch == 2706 || _ch == 2729 || _ch == 2737 || _ch == 2740 || isUnassigned40(_ch);
    }

    private static boolean isUnassigned40(char _ch) {
        return rgBounds(_ch, 2746, 2747) || _ch == 2758 || _ch == 2762 || rgBounds(_ch, 2766, 2767) || rgBounds(_ch, 2769, 2783) || rgBounds(_ch, 2788, 2789) || rgBounds(_ch, 2802, 2816) || isUnassigned39(_ch);
    }

    private static boolean isUnassigned39(char _ch) {
        return _ch == 2820 || rgBounds(_ch, 2829, 2830) || rgBounds(_ch, 2833, 2834) || _ch == 2857 || _ch == 2865 || _ch == 2868 || rgBounds(_ch, 2874, 2875) || rgBounds(_ch, 2885, 2886) || isUnassigned38(_ch);
    }

    private static boolean isUnassigned38(char _ch) {
        return rgBounds(_ch, 2889, 2890) || rgBounds(_ch, 2894, 2901) || rgBounds(_ch, 2904, 2907) || _ch == 2910 || rgBounds(_ch, 2916, 2917) || rgBounds(_ch, 2936, 2945) || _ch == 2948 || isUnassigned37(_ch);
    }

    private static boolean isUnassigned37(char _ch) {
        return rgBounds(_ch, 2955, 2957) || _ch == 2961 || rgBounds(_ch, 2966, 2968) || _ch == 2971 || _ch == 2973 || rgBounds(_ch, 2976, 2978) || rgBounds(_ch, 2981, 2983) || rgBounds(_ch, 2987, 2989) || isUnassigned36(_ch);
    }

    private static boolean isUnassigned36(char _ch) {
        return rgBounds(_ch, 3002, 3005) || rgBounds(_ch, 3011, 3013) || _ch == 3017 || rgBounds(_ch, 3022, 3023) || rgBounds(_ch, 3025, 3030) || rgBounds(_ch, 3032, 3045) || rgBounds(_ch, 3067, 3072) || isUnassigned35(_ch);
    }

    private static boolean isUnassigned35(char _ch) {
        return _ch == 3076 || _ch == 3085 || _ch == 3089 || _ch == 3113 || _ch == 3124 || rgBounds(_ch, 3130, 3132) || _ch == 3141 || _ch == 3145 || rgBounds(_ch, 3150, 3156) || _ch == 3159 || isUnassigned34(_ch);
    }

    private static boolean isUnassigned34(char _ch) {
        return rgBounds(_ch, 3162, 3167) || rgBounds(_ch, 3172, 3173) || rgBounds(_ch, 3184, 3191) || rgBounds(_ch, 3200, 3201) || _ch == 3204 || _ch == 3213 || _ch == 3217 || isUnassigned33(_ch);
    }

    private static boolean isUnassigned33(char _ch) {
        return _ch == 3241 || _ch == 3252 || rgBounds(_ch, 3258, 3259) || _ch == 3269 || _ch == 3273 || rgBounds(_ch, 3278, 3284) || rgBounds(_ch, 3287, 3293) || _ch == 3295 || rgBounds(_ch, 3300, 3301) || isUnassigned32(_ch);
    }

    private static boolean isUnassigned32(char _ch) {
        return _ch == 3312 || rgBounds(_ch, 3315, 3329) || _ch == 3332 || _ch == 3341 || _ch == 3345 || rgBounds(_ch, 3387, 3388) || _ch == 3397 || _ch == 3401 || rgBounds(_ch, 3407, 3414) || rgBounds(_ch, 3416, 3423) || isUnassigned31(_ch);
    }

    private static boolean isUnassigned31(char _ch) {
        return rgBounds(_ch, 3428, 3429) || rgBounds(_ch, 3446, 3448) || rgBounds(_ch, 3456, 3457) || _ch == 3460 || rgBounds(_ch, 3479, 3481) || _ch == 3506 || _ch == 3516 || isUnassigned30(_ch);
    }

    private static boolean isUnassigned30(char _ch) {
        return rgBounds(_ch, 3518, 3519) || rgBounds(_ch, 3527, 3529) || rgBounds(_ch, 3531, 3534) || _ch == 3541 || _ch == 3543 || rgBounds(_ch, 3552, 3569) || rgBounds(_ch, 3573, 3584) || isUnassigned29(_ch);
    }

    private static boolean isUnassigned29(char _ch) {
        return rgBounds(_ch, 3643, 3646) || rgBounds(_ch, 3676, 3712) || _ch == 3715 || rgBounds(_ch, 3717, 3718) || _ch == 3721 || rgBounds(_ch, 3723, 3724) || rgBounds(_ch, 3726, 3731) || isUnassigned28(_ch);
    }

    private static boolean isUnassigned28(char _ch) {
        return _ch == 3736 || _ch == 3744 || _ch == 3748 || _ch == 3750 || rgBounds(_ch, 3752, 3753) || _ch == 3756 || _ch == 3770 || rgBounds(_ch, 3774, 3775) || _ch == 3781 || _ch == 3783 || isUnassigned27(_ch);
    }

    private static boolean isUnassigned27(char _ch) {
        return rgBounds(_ch, 3790, 3791) || rgBounds(_ch, 3802, 3803) || rgBounds(_ch, 3808, 3839) || _ch == 3912 || rgBounds(_ch, 3949, 3952) || _ch == 3992 || _ch == 4029 || isUnassigned26(_ch);
    }

    private static boolean isUnassigned26(char _ch) {
        return _ch == 4045 || rgBounds(_ch, 4059, 4095) || _ch == 4294 || rgBounds(_ch, 4296, 4300) || rgBounds(_ch, 4302, 4303) || _ch == 4681 || rgBounds(_ch, 4686, 4687) || isUnassigned25(_ch);
    }

    private static boolean isUnassigned25(char _ch) {
        return _ch == 4695 || _ch == 4697 || rgBounds(_ch, 4702, 4703) || _ch == 4745 || rgBounds(_ch, 4750, 4751) || _ch == 4785 || rgBounds(_ch, 4790, 4791) || _ch == 4799 || _ch == 4801 || isUnassigned24(_ch);
    }

    private static boolean isUnassigned24(char _ch) {
        return rgBounds(_ch, 4806, 4807) || _ch == 4823 || _ch == 4881 || rgBounds(_ch, 4886, 4887) || rgBounds(_ch, 4955, 4956) || rgBounds(_ch, 4989, 4991) || rgBounds(_ch, 5018, 5023) || isUnassigned23(_ch);
    }

    private static boolean isUnassigned23(char _ch) {
        return rgBounds(_ch, 5109, 5119) || rgBounds(_ch, 5789, 5791) || rgBounds(_ch, 5873, 5887) || _ch == 5901 || rgBounds(_ch, 5909, 5919) || rgBounds(_ch, 5943, 5951) || rgBounds(_ch, 5972, 5983) || isUnassigned22(_ch);
    }

    private static boolean isUnassigned22(char _ch) {
        return _ch == 5997 || _ch == 6001 || rgBounds(_ch, 6004, 6015) || rgBounds(_ch, 6110, 6111) || rgBounds(_ch, 6122, 6127) || rgBounds(_ch, 6138, 6143) || _ch == 6159 || isUnassigned21(_ch);
    }

    private static boolean isUnassigned21(char _ch) {
        return rgBounds(_ch, 6170, 6175) || rgBounds(_ch, 6264, 6271) || rgBounds(_ch, 6315, 6319) || rgBounds(_ch, 6390, 6399) || rgBounds(_ch, 6429, 6431) || rgBounds(_ch, 6444, 6447) || rgBounds(_ch, 6460, 6463) || isUnassigned20(_ch);
    }

    private static boolean isUnassigned20(char _ch) {
        return rgBounds(_ch, 6465, 6467) || rgBounds(_ch, 6510, 6511) || rgBounds(_ch, 6517, 6527) || rgBounds(_ch, 6572, 6575) || rgBounds(_ch, 6602, 6607) || rgBounds(_ch, 6619, 6621) || rgBounds(_ch, 6684, 6685) || isUnassigned19(_ch);
    }

    private static boolean isUnassigned19(char _ch) {
        return _ch == 6751 || rgBounds(_ch, 6781, 6782) || rgBounds(_ch, 6794, 6799) || rgBounds(_ch, 6810, 6815) || rgBounds(_ch, 6830, 6911) || rgBounds(_ch, 6988, 6991) || rgBounds(_ch, 7037, 7039) || isUnassigned18(_ch);
    }

    private static boolean isUnassigned18(char _ch) {
        return rgBounds(_ch, 7156, 7163) || rgBounds(_ch, 7224, 7226) || rgBounds(_ch, 7242, 7244) || rgBounds(_ch, 7296, 7359) || rgBounds(_ch, 7368, 7375) || rgBounds(_ch, 7415, 7423) || rgBounds(_ch, 7655, 7675) || isUnassigned17(_ch);
    }

    private static boolean isUnassigned17(char _ch) {
        return rgBounds(_ch, 7958, 7959) || rgBounds(_ch, 7966, 7967) || rgBounds(_ch, 8006, 8007) || rgBounds(_ch, 8014, 8015) || _ch == 8024 || _ch == 8026 || _ch == 8028 || _ch == 8030 || isUnassigned16(_ch);
    }

    private static boolean isUnassigned16(char _ch) {
        return rgBounds(_ch, 8062, 8063) || _ch == 8117 || _ch == 8133 || rgBounds(_ch, 8148, 8149) || _ch == 8156 || rgBounds(_ch, 8176, 8177) || _ch == 8181 || _ch == 8191 || rgBounds(_ch, 8293, 8297) || isUnassigned15(_ch);
    }

    private static boolean isUnassigned15(char _ch) {
        return rgBounds(_ch, 8306, 8307) || _ch == 8335 || rgBounds(_ch, 8349, 8351) || rgBounds(_ch, 8379, 8399) || rgBounds(_ch, 8433, 8447) || rgBounds(_ch, 8586, 8591) || rgBounds(_ch, 9204, 9215) || isUnassigned14(_ch);
    }

    private static boolean isUnassigned14(char _ch) {
        return rgBounds(_ch, 9255, 9279) || rgBounds(_ch, 9291, 9311) || _ch == 9984 || rgBounds(_ch, 11085, 11087) || rgBounds(_ch, 11098, 11263) || _ch == 11311 || _ch == 11359 || isUnassigned13(_ch);
    }

    private static boolean isUnassigned13(char _ch) {
        return rgBounds(_ch, 11508, 11512) || _ch == 11558 || rgBounds(_ch, 11560, 11564) || rgBounds(_ch, 11566, 11567) || rgBounds(_ch, 11624, 11630) || rgBounds(_ch, 11633, 11646) || rgBounds(_ch, 11671, 11679) || isUnassigned12(_ch);
    }

    private static boolean isUnassigned12(char _ch) {
        return _ch == 11687 || _ch == 11695 || _ch == 11703 || _ch == 11711 || _ch == 11719 || _ch == 11727 || _ch == 11735 || _ch == 11743 || rgBounds(_ch, 11836, 11903) || _ch == 11930 || rgBounds(_ch, 12020, 12031) || isUnassigned11(_ch);
    }

    private static boolean isUnassigned11(char _ch) {
        return rgBounds(_ch, 12246, 12271) || rgBounds(_ch, 12284, 12287) || _ch == 12352 || rgBounds(_ch, 12439, 12440) || rgBounds(_ch, 12544, 12548) || rgBounds(_ch, 12590, 12592) || _ch == 12687 || isUnassigned10(_ch);
    }

    private static boolean isUnassigned10(char _ch) {
        return rgBounds(_ch, 12731, 12735) || rgBounds(_ch, 12772, 12783) || _ch == 12831 || _ch == 13055 || rgBounds(_ch, 19894, 19903) || rgBounds(_ch, 40909, 40959) || rgBounds(_ch, 42125, 42127) || isUnassigned9(_ch);
    }

    private static boolean isUnassigned9(char _ch) {
        return rgBounds(_ch, 42183, 42191) || rgBounds(_ch, 42540, 42559) || rgBounds(_ch, 42648, 42654) || rgBounds(_ch, 42744, 42751) || _ch == 42895 || rgBounds(_ch, 42900, 42911) || rgBounds(_ch, 42923, 42999) || isUnassigned8(_ch);
    }

    private static boolean isUnassigned8(char _ch) {
        return rgBounds(_ch, 43052, 43055) || rgBounds(_ch, 43066, 43071) || rgBounds(_ch, 43128, 43135) || rgBounds(_ch, 43205, 43213) || rgBounds(_ch, 43226, 43231) || rgBounds(_ch, 43260, 43263) || rgBounds(_ch, 43348, 43358) || isUnassigned7(_ch);
    }

    private static boolean isUnassigned7(char _ch) {
        return rgBounds(_ch, 43389, 43391) || _ch == 43470 || rgBounds(_ch, 43482, 43485) || rgBounds(_ch, 43488, 43519) || rgBounds(_ch, 43575, 43583) || rgBounds(_ch, 43598, 43599) || rgBounds(_ch, 43610, 43611) || isUnassigned6(_ch);
    }

    private static boolean isUnassigned6(char _ch) {
        return rgBounds(_ch, 43644, 43647) || rgBounds(_ch, 43715, 43738) || rgBounds(_ch, 43767, 43776) || rgBounds(_ch, 43783, 43784) || rgBounds(_ch, 43791, 43792) || rgBounds(_ch, 43799, 43807) || _ch == 43815 || isUnassigned5(_ch);
    }

    private static boolean isUnassigned5(char _ch) {
        return rgBounds(_ch, 43823, 43967) || rgBounds(_ch, 44014, 44015) || rgBounds(_ch, 44026, 44031) || rgBounds(_ch, 55204, 55215) || rgBounds(_ch, 55239, 55242) || rgBounds(_ch, 55292, 55295) || rgBounds(_ch, 64110, 64111) || isUnassigned4(_ch);
    }

    private static boolean isUnassigned4(char _ch) {
        return rgBounds(_ch, 64218, 64255) || rgBounds(_ch, 64263, 64274) || rgBounds(_ch, 64280, 64284) || _ch == 64311 || _ch == 64317 || _ch == 64319 || _ch == 64322 || isUnassigned3(_ch);
    }

    private static boolean isUnassigned3(char _ch) {
        return _ch == 64325 || rgBounds(_ch, 64450, 64466) || rgBounds(_ch, 64832, 64847) || rgBounds(_ch, 64912, 64913) || rgBounds(_ch, 64968, 65007) || rgBounds(_ch, 65022, 65023) || rgBounds(_ch, 65050, 65055) || isUnassigned2(_ch);
    }

    private static boolean isUnassigned2(char _ch) {
        return rgBounds(_ch, 65063, 65071) || _ch == 65107 || _ch == 65127 || rgBounds(_ch, 65132, 65135) || _ch == 65141 || rgBounds(_ch, 65277, 65278) || _ch == 65280 || isUnassigned1(_ch);
    }

    private static boolean isUnassigned1(char _ch) {
        return rgBounds(_ch, 65471, 65473) || rgBounds(_ch, 65480, 65481) || rgBounds(_ch, 65488, 65489) || rgBounds(_ch, 65496, 65497) || rgBounds(_ch, 65501, 65503) || _ch == 65511 || rgBounds(_ch, 65519, 65528) || _ch >= 65534;
    }

    private static boolean rgBounds(char _ch, int _mini, int _maxi) {
        return _ch >= _mini && _ch <= _maxi;
    }
}
