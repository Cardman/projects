package code.sml;

import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

public final class DocumentBuilder {

    private static final char ASCII_32 = 32;

    private static final char ASCII_128 = 128;

    private static final char ASCII_38 = 38;
    private static final char IEXCL = 161;
    private static final char CENT = 162;
    private static final char POUND = 163;
    private static final char CURREN = 164;
    private static final char YEN = 165;
    private static final char BRVBAR = 166;
    private static final char SECT = 167;
    private static final char UML = 168;
    private static final char COPY = 169;
    private static final char ORDF = 170;
    private static final char LAQUO = 171;
    private static final char NOT = 172;
    private static final char SHY = 173;
    private static final char REG = 174;
    private static final char MACR = 175;
    private static final char DEG = 176;
    private static final char PLUSMN = 177;
    private static final char SUP2 = 178;
    private static final char SUP3 = 179;
    private static final char ACUTE = 180;
    private static final char MICRO = 181;
    private static final char PARA = 182;
    private static final char MIDDOT = 183;
    private static final char CEDIL = 184;
    private static final char SUP1 = 185;
    private static final char ORDM = 186;
    private static final char RAQUO = 187;
    private static final char FRAC14 = 188;
    private static final char FRAC12 = 189;
    private static final char FRAC34 = 190;
    private static final char IQUEST = 191;
    private static final char U_A_GRAVE = 192;
    private static final char U_A_ACUTE = 193;
    private static final char U_A_CIRC = 194;
    private static final char U_A_TILDE = 195;
    private static final char U_A_UML = 196;
    private static final char U_A_RING = 197;
    private static final char U_AE_LIG = 198;
    private static final char U_C_CEDIL = 199;
    private static final char U_E_GRAVE = 200;
    private static final char U_E_ACUTE = 201;
    private static final char U_E_CIRC = 202;
    private static final char U_E_UML = 203;
    private static final char U_I_GRAVE = 204;
    private static final char U_I_ACUTE = 205;
    private static final char U_I_CIRC = 206;
    private static final char U_I_UML = 207;
    private static final char U_ETH = 208;
    private static final char U_N_TILDE = 209;
    private static final char U_O_GRAVE = 210;
    private static final char U_O_ACUTE = 211;
    private static final char U_O_CIRC = 212;
    private static final char U_O_TILDE = 213;
    private static final char U_O_UML = 214;
    private static final char TIMES = 215;
    private static final char U_O_SLASH = 216;
    private static final char U_U_GRAVE = 217;
    private static final char U_U_ACUTE = 218;
    private static final char U_U_CIRC = 219;
    private static final char U_U_UML = 220;
    private static final char U_Y_ACUTE = 221;
    private static final char U_THORN = 222;
    private static final char SZLIG = 223;
    private static final char AGRAVE = 224;
    private static final char AACUTE = 225;
    private static final char ACIRC = 226;
    private static final char ATILDE = 227;
    private static final char AUML = 228;
    private static final char ARING = 229;
    private static final char AELIG = 230;
    private static final char CCEDIL = 231;
    private static final char EGRAVE = 232;
    private static final char EACUTE = 233;
    private static final char ECIRC = 234;
    private static final char EUML = 235;
    private static final char IGRAVE = 236;
    private static final char IACUTE = 237;
    private static final char ICIRC = 238;
    private static final char IUML = 239;
    private static final char ETH = 240;
    private static final char NTILDE = 241;
    private static final char OGRAVE = 242;
    private static final char OACUTE = 243;
    private static final char OCIRC = 244;
    private static final char OTILDE = 245;
    private static final char OUML = 246;
    private static final char DIVIDE = 247;
    private static final char OSLASH = 248;
    private static final char UGRAVE = 249;
    private static final char UACUTE = 250;
    private static final char UCIRC = 251;
    private static final char UUML = 252;
    private static final char YACUTE = 253;
    private static final char THORN = 254;
    private static final char YUML = 255;
    private static final char U_OE_LIG = 338;
    private static final char OELIG = 339;
    private static final char U_SCARON = 352;
    private static final char SCARON = 353;
    private static final char U_Y_UML = 376;
    private static final char CIRC = 710;
    private static final char TILDE = 732;
    private static final char ENSP = 8194;
    private static final char EMSP = 8195;
    private static final char THINSP = 8201;
    private static final char ZWNJ = 8204;
    private static final char ZWJ = 8205;
    private static final char LRM = 8206;
    private static final char RLM = 8207;
    private static final char NDASH = 8211;
    private static final char MDASH = 8212;
    private static final char LSQUO = 8216;
    private static final char RSQUO = 8217;
    private static final char SBQUO = 8218;
    private static final char LDQUO = 8220;
    private static final char RDQUO = 8221;
    private static final char BDQUO = 8222;
    private static final char D_AGGER = 8224;
    private static final char DAGGER = 8225;
    private static final char PERMIL = 8240;
    private static final char LSAQUO = 8249;
    private static final char RSAQUO = 8250;
    private static final char EURO = 8364;
    private static final char FNOF = 402;
    private static final char U_A_LPHA = 913;
    private static final char U_B_ETA = 914;
    private static final char U_G_AMMA = 915;
    private static final char U_D_ELTA = 916;
    private static final char U_E_PSILON = 917;
    private static final char U_Z_ETA = 918;
    private static final char U_E_TA = 919;
    private static final char U_T_HETA = 920;
    private static final char U_I_OTA = 921;
    private static final char U_K_APPA = 922;
    private static final char U_L_AMBDA = 923;
    private static final char U_M_U = 924;
    private static final char U_N_U = 925;
    private static final char U_X_I = 926;
    private static final char U_O_MICRON = 927;
    private static final char U_P_I = 928;
    private static final char U_R_HO = 929;
    private static final char U_S_IGMA = 931;
    private static final char U_T_AU = 932;
    private static final char U_U_PSILON = 933;
    private static final char U_P_HI = 934;
    private static final char U_C_HI = 935;
    private static final char U_P_SI = 936;
    private static final char U_O_MEGA = 937;
    private static final char ALPHA = 945;
    private static final char BETA = 946;
    private static final char GAMMA = 947;
    private static final char DELTA = 948;
    private static final char EPSILON = 949;
    private static final char ZETA = 950;
    private static final char ETA = 951;
    private static final char THETA = 952;
    private static final char IOTA = 953;
    private static final char KAPPA = 954;
    private static final char LAMBDA = 955;
    private static final char MU = 956;
    private static final char NU = 957;
    private static final char XI = 958;
    private static final char OMICRON = 959;
    private static final char PI = 960;
    private static final char RHO = 961;
    private static final char SIGMAF = 962;
    private static final char SIGMA = 963;
    private static final char TAU = 964;
    private static final char UPSILON = 965;
    private static final char PHI = 966;
    private static final char CHI = 967;
    private static final char PSI = 968;
    private static final char OMEGA = 969;
    private static final char THETASYM = 977;
    private static final char UPSIH = 978;
    private static final char PIV = 982;
    private static final char BULL = 8226;
    private static final char HELLIP = 8230;
    private static final char PRIME = 8242;
    private static final char U_PRIME = 8243;
    private static final char OLINE = 8254;
    private static final char FRASL = 8260;
    private static final char WEIERP = 8472;
    private static final char IMAGE = 8465;
    private static final char REAL = 8476;
    private static final char TRADE = 8482;
    private static final char ALEFSYM = 8501;
    private static final char LARR = 8592;
    private static final char UARR = 8593;
    private static final char RARR = 8594;
    private static final char DARR = 8595;
    private static final char HARR = 8596;
    private static final char CRARR = 8629;
    private static final char U_LARR = 8656;
    private static final char U_UARR = 8657;
    private static final char U_RARR = 8658;
    private static final char U_DARR = 8659;
    private static final char U_HARR = 8660;
    private static final char FORALL = 8704;
    private static final char PART = 8706;
    private static final char EXIST = 8707;
    private static final char EMPTY = 8709;
    private static final char NABLA = 8711;
    private static final char ISIN = 8712;
    private static final char NOTIN = 8713;
    private static final char NI = 8715;
    private static final char PROD = 8719;
    private static final char SUM = 8721;
    private static final char MINUS = 8722;
    private static final char LOWAST = 8727;
    private static final char RADIC = 8730;
    private static final char PROP = 8733;
    private static final char INFIN = 8734;
    private static final char ANG = 8736;
    private static final char AND = 8743;
    private static final char OR = 8744;
    private static final char CAP = 8745;
    private static final char CUP = 8746;
    private static final char INT = 8747;
    private static final char THERE4 = 8756;
    private static final char SIM = 8764;
    private static final char CONG = 8773;
    private static final char ASYMP = 8776;
    private static final char NE = 8800;
    private static final char EQUIV = 8801;
    private static final char LE = 8804;
    private static final char GE = 8805;
    private static final char SUB = 8834;
    private static final char SUP = 8835;
    private static final char NSUB = 8836;
    private static final char SUBE = 8838;
    private static final char SUPE = 8839;
    private static final char OPLUS = 8853;
    private static final char OTIMES = 8855;
    private static final char PERP = 8869;
    private static final char SDOT = 8901;
    private static final char LCEIL = 8968;
    private static final char RCEIL = 8969;
    private static final char LFLOOR = 8970;
    private static final char RFLOOR = 8971;
    private static final char LANG = 9001;
    private static final char RANG = 9002;
    private static final char LOZ = 9674;
    private static final char SPADES = 9824;
    private static final char CLUBS = 9827;
    private static final char HEARTS = 9829;
    private static final char DIAMS = 9830;
    private static final char NBSP = 160;
    private static final char QUOT = 34;
    private static final char APOS = 39;
    private static final char LT = 60;
    private static final char GT = 62;
    private static final String E_U_AELIG = "&AElig;";
    private static final String E_U_AACUTE = "&Aacute;";
    private static final String E_U_ACIRC = "&Acirc;";
    private static final String E_U_AGRAVE = "&Agrave;";
    private static final String E_U_ALPHA = "&Alpha;";
    private static final String E_U_ARING = "&Aring;";
    private static final String E_U_ATILDE = "&Atilde;";
    private static final String E_U_AUML = "&Auml;";
    private static final String E_U_BETA = "&Beta;";
    private static final String E_U_CCEDIL = "&Ccedil;";
    private static final String E_U_CHI = "&Chi;";
    private static final String E_U_DAGGER = "&Dagger;";
    private static final String E_U_DELTA = "&Delta;";
    private static final String E_U_ETH = "&ETH;";
    private static final String E_U_EACUTE = "&Eacute;";
    private static final String E_U_ECIRC = "&Ecirc;";
    private static final String E_U_EGRAVE = "&Egrave;";
    private static final String E_U_EPSILON = "&Epsilon;";
    private static final String E_U_ETA = "&Eta;";
    private static final String E_U_EUML = "&Euml;";
    private static final String E_U_GAMMA = "&Gamma;";
    private static final String E_U_IACUTE = "&Iacute;";
    private static final String E_U_ICIRC = "&Icirc;";
    private static final String E_U_IGRAVE = "&Igrave;";
    private static final String E_U_IOTA = "&Iota;";
    private static final String E_U_IUML = "&Iuml;";
    private static final String E_U_KAPPA = "&Kappa;";
    private static final String E_U_LAMBDA = "&Lambda;";
    private static final String E_U_MU = "&Mu;";
    private static final String E_U_NTILDE = "&Ntilde;";
    private static final String E_U_NU = "&Nu;";
    private static final String E_U_OELIG = "&OElig;";
    private static final String E_U_OACUTE = "&Oacute;";
    private static final String E_U_OCIRC = "&Ocirc;";
    private static final String E_U_OGRAVE = "&Ograve;";
    private static final String E_U_OMEGA = "&Omega;";
    private static final String E_U_OMICRON = "&Omicron;";
    private static final String E_U_OSLASH = "&Oslash;";
    private static final String E_U_OTILDE = "&Otilde;";
    private static final String E_U_OUML = "&Ouml;";
    private static final String E_U_PHI = "&Phi;";
    private static final String E_U_PI = "&Pi;";
    private static final String E_P_RIME = "&Prime;";
    private static final String E_U_PSI = "&Psi;";
    private static final String E_U_RHO = "&Rho;";
    private static final String E_S_CARON = "&Scaron;";
    private static final String E_U_SIGMA = "&Sigma;";
    private static final String E_U_THORN = "&THORN;";
    private static final String E_U_TAU = "&Tau;";
    private static final String E_U_THETA = "&Theta;";
    private static final String E_U_UACUTE = "&Uacute;";
    private static final String E_U_UCIRC = "&Ucirc;";
    private static final String E_U_UGRAVE = "&Ugrave;";
    private static final String E_U_UPSILON = "&Upsilon;";
    private static final String E_U_UUML = "&Uuml;";
    private static final String E_U_XI = "&Xi;";
    private static final String E_U_YACUTE = "&Yacute;";
    private static final String E_U_YUML = "&Yuml;";
    private static final String E_U_ZETA = "&Zeta;";
    private static final String E_AACUTE = "&aacute;";
    private static final String E_ACIRC = "&acirc;";
    private static final String E_ACUTE = "&acute;";
    private static final String E_AELIG = "&aelig;";
    private static final String E_AGRAVE = "&agrave;";
    private static final String E_ALEFSYM = "&alefsym;";
    private static final String E_ALPHA = "&alpha;";
    private static final String E_AMP = "&amp;";
    private static final String E_AND = "&and;";
    private static final String E_ANG = "&ang;";
    private static final String E_APOS = "&apos;";
    private static final String E_ARING = "&aring;";
    private static final String E_ASYMP = "&asymp;";
    private static final String E_ATILDE = "&atilde;";
    private static final String E_AUML = "&auml;";
    private static final String E_BDQUO = "&bdquo;";
    private static final String E_BETA = "&beta;";
    private static final String E_BRVBAR = "&brvbar;";
    private static final String E_BULL = "&bull;";
    private static final String E_CAP = "&cap;";
    private static final String E_CCEDIL = "&ccedil;";
    private static final String E_CEDIL = "&cedil;";
    private static final String E_CENT = "&cent;";
    private static final String E_CHI = "&chi;";
    private static final String E_CIRC = "&circ;";
    private static final String E_CLUBS = "&clubs;";
    private static final String E_CONG = "&cong;";
    private static final String E_COPY = "&copy;";
    private static final String E_CRARR = "&crarr;";
    private static final String E_CUP = "&cup;";
    private static final String E_CURREN = "&curren;";
    private static final String E_D_ARR = "&dArr;";
    private static final String E_DAGGER = "&dagger;";
    private static final String E_DARR = "&darr;";
    private static final String E_DEG = "&deg;";
    private static final String E_DELTA = "&delta;";
    private static final String E_DIAMS = "&diams;";
    private static final String E_DIVIDE = "&divide;";
    private static final String E_EACUTE = "&eacute;";
    private static final String E_ECIRC = "&ecirc;";
    private static final String E_EGRAVE = "&egrave;";
    private static final String E_EMPTY = "&empty;";
    private static final String E_EMSP = "&emsp;";
    private static final String E_ENSP = "&ensp;";
    private static final String E_EPSILON = "&epsilon;";
    private static final String E_EQUIV = "&equiv;";
    private static final String E_ETA = "&eta;";
    private static final String E_ETH = "&eth;";
    private static final String E_EUML = "&euml;";
    private static final String E_EURO = "&euro;";
    private static final String E_EXIST = "&exist;";
    private static final String E_FNOF = "&fnof;";
    private static final String E_FORALL = "&forall;";
    private static final String E_FRAC12 = "&frac12;";
    private static final String E_FRAC14 = "&frac14;";
    private static final String E_FRAC34 = "&frac34;";
    private static final String E_FRASL = "&frasl;";
    private static final String E_GAMMA = "&gamma;";
    private static final String E_GE = "&ge;";
    private static final String E_GT = "&gt;";
    private static final String E_H_ARR = "&hArr;";
    private static final String E_HARR = "&harr;";
    private static final String E_HEARTS = "&hearts;";
    private static final String E_HELLIP = "&hellip;";
    private static final String E_IACUTE = "&iacute;";
    private static final String E_ICIRC = "&icirc;";
    private static final String E_IEXCL = "&iexcl;";
    private static final String E_IGRAVE = "&igrave;";
    private static final String E_IMAGE = "&image;";
    private static final String E_INFIN = "&infin;";
    private static final String E_INT = "&int;";
    private static final String E_IOTA = "&iota;";
    private static final String E_IQUEST = "&iquest;";
    private static final String E_ISIN = "&isin;";
    private static final String E_IUML = "&iuml;";
    private static final String E_KAPPA = "&kappa;";
    private static final String E_L_ARR = "&lArr;";
    private static final String E_LAMBDA = "&lambda;";
    private static final String E_LANG = "&lang;";
    private static final String E_LAQUO = "&laquo;";
    private static final String E_LARR = "&larr;";
    private static final String E_LCEIL = "&lceil;";
    private static final String E_LDQUO = "&ldquo;";
    private static final String E_LE = "&le;";
    private static final String E_LFLOOR = "&lfloor;";
    private static final String E_LOWAST = "&lowast;";
    private static final String E_LOZ = "&loz;";
    private static final String E_LRM = "&lrm;";
    private static final String E_LSAQUO = "&lsaquo;";
    private static final String E_LSQUO = "&lsquo;";
    private static final String E_LT = "&lt;";
    private static final String E_MACR = "&macr;";
    private static final String E_MDASH = "&mdash;";
    private static final String E_MICRO = "&micro;";
    private static final String E_MIDDOT = "&middot;";
    private static final String E_MINUS = "&minus;";
    private static final String E_MU = "&mu;";
    private static final String E_NABLA = "&nabla;";
    private static final String E_NBSP = "&nbsp;";
    private static final String E_NDASH = "&ndash;";
    private static final String E_NE = "&ne;";
    private static final String E_NI = "&ni;";
    private static final String E_NOT = "&not;";
    private static final String E_NOTIN = "&notin;";
    private static final String E_NSUB = "&nsub;";
    private static final String E_NTILDE = "&ntilde;";
    private static final String E_NU = "&nu;";
    private static final String E_OACUTE = "&oacute;";
    private static final String E_OCIRC = "&ocirc;";
    private static final String E_OELIG = "&oelig;";
    private static final String E_OGRAVE = "&ograve;";
    private static final String E_OLINE = "&oline;";
    private static final String E_OMEGA = "&omega;";
    private static final String E_OMICRON = "&omicron;";
    private static final String E_OPLUS = "&oplus;";
    private static final String E_OR = "&or;";
    private static final String E_ORDF = "&ordf;";
    private static final String E_ORDM = "&ordm;";
    private static final String E_OSLASH = "&oslash;";
    private static final String E_OTILDE = "&otilde;";
    private static final String E_OTIMES = "&otimes;";
    private static final String E_OUML = "&ouml;";
    private static final String E_PARA = "&para;";
    private static final String E_PART = "&part;";
    private static final String E_PERMIL = "&permil;";
    private static final String E_PERP = "&perp;";
    private static final String E_PHI = "&phi;";
    private static final String E_PI = "&pi;";
    private static final String E_PIV = "&piv;";
    private static final String E_PLUSMN = "&plusmn;";
    private static final String E_POUND = "&pound;";
    private static final String E_PRIME = "&prime;";
    private static final String E_PROD = "&prod;";
    private static final String E_PROP = "&prop;";
    private static final String E_PSI = "&psi;";
    private static final String E_QUOT = "&quot;";
    private static final String E_R_ARR = "&rArr;";
    private static final String E_RADIC = "&radic;";
    private static final String E_RANG = "&rang;";
    private static final String E_RAQUO = "&raquo;";
    private static final String E_RARR = "&rarr;";
    private static final String E_RCEIL = "&rceil;";
    private static final String E_RDQUO = "&rdquo;";
    private static final String E_REAL = "&real;";
    private static final String E_REG = "&reg;";
    private static final String E_RFLOOR = "&rfloor;";
    private static final String E_RHO = "&rho;";
    private static final String E_RLM = "&rlm;";
    private static final String E_RSAQUO = "&rsaquo;";
    private static final String E_RSQUO = "&rsquo;";
    private static final String E_SBQUO = "&sbquo;";
    private static final String E_SCARON = "&scaron;";
    private static final String E_SDOT = "&sdot;";
    private static final String E_SECT = "&sect;";
    private static final String E_SHY = "&shy;";
    private static final String E_SIGMA = "&sigma;";
    private static final String E_SIGMAF = "&sigmaf;";
    private static final String E_SIM = "&sim;";
    private static final String E_SPADES = "&spades;";
    private static final String E_SUB = "&sub;";
    private static final String E_SUBE = "&sube;";
    private static final String E_SUM = "&sum;";
    private static final String E_SUP1 = "&sup1;";
    private static final String E_SUP2 = "&sup2;";
    private static final String E_SUP3 = "&sup3;";
    private static final String E_SUP = "&sup;";
    private static final String E_SUPE = "&supe;";
    private static final String E_SZLIG = "&szlig;";
    private static final String E_TAU = "&tau;";
    private static final String E_THERE4 = "&there4;";
    private static final String E_THETA = "&theta;";
    private static final String E_THETASYM = "&thetasym;";
    private static final String E_THINSP = "&thinsp;";
    private static final String E_THORN = "&thorn;";
    private static final String E_TILDE = "&tilde;";
    private static final String E_TIMES = "&times;";
    private static final String E_TRADE = "&trade;";
    private static final String E_U_ARR = "&uArr;";
    private static final String E_UACUTE = "&uacute;";
    private static final String E_UARR = "&uarr;";
    private static final String E_UCIRC = "&ucirc;";
    private static final String E_UGRAVE = "&ugrave;";
    private static final String E_UML = "&uml;";
    private static final String E_UPSIH = "&upsih;";
    private static final String E_UPSILON = "&upsilon;";
    private static final String E_UUML = "&uuml;";
    private static final String E_WEIERP = "&weierp;";
    private static final String E_XI = "&xi;";
    private static final String E_YACUTE = "&yacute;";
    private static final String E_YEN = "&yen;";
    private static final String E_YUML = "&yuml;";
    private static final String E_ZETA = "&zeta;";
    private static final String E_ZWJ = "&zwj;";
    private static final String E_ZWNJ = "&zwnj;";

    private static final String ENCODE = "&#{0};";

    private static final String EMPTY_STRING = "";

    private static final char ENCODED = '&';

    private static final char NUMBERED_CHAR = '#';

    private static final char SLASH = '/';

    private static final char TAB = '\t';

    private static final char LINE_RETURN = '\n';
    private static final char LT_CHAR = '<';
    private static final char GT_CHAR = '>';
    private static final char QUOT_CHAR = '"';
    private static final char APOS_CHAR = '\'';

    private static final char END_ESCAPED = ';';

    private static final char EQUALS = '=';

    private static final int DEFAULT_TAB = 4;

    private int tabWidth = DEFAULT_TAB;
    
    private DocumentBuilder(){
    }

    private DocumentBuilder(int _tabWidth){
        setTabWidth(_tabWidth);
    }

    public static Element getFirstElementByAttribute(Document _doc, String _attr, String _value) {
        NodeList all_ = _doc.getElementsByTagName();
        int lengthAll_ = all_.getLength();
        for (int i = CustList.FIRST_INDEX; i < lengthAll_; i++) {
            Node n_ = all_.item(i);
            if (StringList.quickEq(((Element) n_).getAttribute(_attr),_value)) {
                return (Element) n_;
            }
        }
        return null;
    }

    public static Element getElementById(Document _doc, String _attr, String _secAttr, String _id) {
        Element element_ = null;
        NodeList all_ = _doc.getElementsByTagName();
        int lengthAll_ = all_.getLength();
        for (int i = CustList.FIRST_INDEX; i < lengthAll_; i++) {
            Node n_ = all_.item(i);
            if (StringList.quickEq(((Element) n_).getAttribute(_attr),_id)) {
                element_ = (Element) n_;
                break;
            }
            if (StringList.quickEq(((Element) n_).getAttribute(_secAttr),_id)) {
                element_ = (Element) n_;
                break;
            }
        }
        return element_;
    }
    
    public static DocumentBuilder newDocumentBuilder() {
        return new DocumentBuilder();
    }

    public static DocumentBuilder newDocumentBuilder(int _tabWidth) {
        return new DocumentBuilder(_tabWidth);
    }

    protected static String transformSpecialCharsLtGt(String _htmlText) {
        return transformSpecialChars(_htmlText, true, true);
    }

    public static String transformSpecialChars(String _htmlText) {
        return transformSpecialChars(_htmlText, true, false);
    }

    public static String transformSpecialChars(String _htmlText, boolean _escapeAmp) {
        return transformSpecialChars(_htmlText, _escapeAmp, false);
    }

    public static String transformSpecialChars(String _htmlText, boolean _affectEamp, boolean _affectLtGt) {
        StringMap<String> map_ = new StringMap<String>();
        map_.put(E_NBSP, StringList.simpleNumberFormat(ENCODE, (int)NBSP));
        map_.put(E_IEXCL, StringList.simpleNumberFormat(ENCODE, (int)IEXCL));
        map_.put(E_CENT, StringList.simpleNumberFormat(ENCODE, (int)CENT));
        map_.put(E_POUND, StringList.simpleNumberFormat(ENCODE, (int)POUND));
        map_.put(E_CURREN, StringList.simpleNumberFormat(ENCODE, (int)CURREN));
        map_.put(E_YEN, StringList.simpleNumberFormat(ENCODE, (int)YEN));
        map_.put(E_BRVBAR, StringList.simpleNumberFormat(ENCODE, (int)BRVBAR));
        map_.put(E_SECT, StringList.simpleNumberFormat(ENCODE, (int)SECT));
        map_.put(E_UML, StringList.simpleNumberFormat(ENCODE, (int)UML));
        map_.put(E_COPY, StringList.simpleNumberFormat(ENCODE, (int)COPY));
        map_.put(E_ORDF, StringList.simpleNumberFormat(ENCODE, (int)ORDF));
        map_.put(E_LAQUO, StringList.simpleNumberFormat(ENCODE, (int)LAQUO));
        map_.put(E_NOT, StringList.simpleNumberFormat(ENCODE, (int)NOT));
        map_.put(E_SHY, StringList.simpleNumberFormat(ENCODE, (int)SHY));
        map_.put(E_REG, StringList.simpleNumberFormat(ENCODE, (int)REG));
        map_.put(E_MACR, StringList.simpleNumberFormat(ENCODE, (int)MACR));
        map_.put(E_DEG, StringList.simpleNumberFormat(ENCODE, (int)DEG));
        map_.put(E_PLUSMN, StringList.simpleNumberFormat(ENCODE, (int)PLUSMN));
        map_.put(E_SUP2, StringList.simpleNumberFormat(ENCODE, (int)SUP2));
        map_.put(E_SUP3, StringList.simpleNumberFormat(ENCODE, (int)SUP3));
        map_.put(E_ACUTE, StringList.simpleNumberFormat(ENCODE, (int)ACUTE));
        map_.put(E_MICRO, StringList.simpleNumberFormat(ENCODE, (int)MICRO));
        map_.put(E_PARA, StringList.simpleNumberFormat(ENCODE, (int)PARA));
        map_.put(E_MIDDOT, StringList.simpleNumberFormat(ENCODE, (int)MIDDOT));
        map_.put(E_CEDIL, StringList.simpleNumberFormat(ENCODE, (int)CEDIL));
        map_.put(E_SUP1, StringList.simpleNumberFormat(ENCODE, (int)SUP1));
        map_.put(E_ORDM, StringList.simpleNumberFormat(ENCODE, (int)ORDM));
        map_.put(E_RAQUO, StringList.simpleNumberFormat(ENCODE, (int)RAQUO));
        map_.put(E_FRAC14, StringList.simpleNumberFormat(ENCODE, (int)FRAC14));
        map_.put(E_FRAC12, StringList.simpleNumberFormat(ENCODE, (int)FRAC12));
        map_.put(E_FRAC34, StringList.simpleNumberFormat(ENCODE, (int)FRAC34));
        map_.put(E_IQUEST, StringList.simpleNumberFormat(ENCODE, (int)IQUEST));
        map_.put(E_U_AGRAVE, StringList.simpleNumberFormat(ENCODE, (int)U_A_GRAVE));
        map_.put(E_U_AACUTE, StringList.simpleNumberFormat(ENCODE, (int)U_A_ACUTE));
        map_.put(E_U_ACIRC, StringList.simpleNumberFormat(ENCODE, (int)U_A_CIRC));
        map_.put(E_U_ATILDE, StringList.simpleNumberFormat(ENCODE, (int)U_A_TILDE));
        map_.put(E_U_AUML, StringList.simpleNumberFormat(ENCODE, (int)U_A_UML));
        map_.put(E_U_ARING, StringList.simpleNumberFormat(ENCODE, (int)U_A_RING));
        map_.put(E_U_AELIG, StringList.simpleNumberFormat(ENCODE, (int)U_AE_LIG));
        map_.put(E_U_CCEDIL, StringList.simpleNumberFormat(ENCODE, (int)U_C_CEDIL));
        map_.put(E_U_EGRAVE, StringList.simpleNumberFormat(ENCODE, (int)U_E_GRAVE));
        map_.put(E_U_EACUTE, StringList.simpleNumberFormat(ENCODE, (int)U_E_ACUTE));
        map_.put(E_U_ECIRC, StringList.simpleNumberFormat(ENCODE, (int)U_E_CIRC));
        map_.put(E_U_EUML, StringList.simpleNumberFormat(ENCODE, (int)U_E_UML));
        map_.put(E_U_IGRAVE, StringList.simpleNumberFormat(ENCODE, (int)U_I_GRAVE));
        map_.put(E_U_IACUTE, StringList.simpleNumberFormat(ENCODE, (int)U_I_ACUTE));
        map_.put(E_U_ICIRC, StringList.simpleNumberFormat(ENCODE, (int)U_I_CIRC));
        map_.put(E_U_IUML, StringList.simpleNumberFormat(ENCODE, (int)U_I_UML));
        map_.put(E_U_ETH, StringList.simpleNumberFormat(ENCODE, (int)U_ETH));
        map_.put(E_U_NTILDE, StringList.simpleNumberFormat(ENCODE, (int)U_N_TILDE));
        map_.put(E_U_OGRAVE, StringList.simpleNumberFormat(ENCODE, (int)U_O_GRAVE));
        map_.put(E_U_OACUTE, StringList.simpleNumberFormat(ENCODE, (int)U_O_ACUTE));
        map_.put(E_U_OCIRC, StringList.simpleNumberFormat(ENCODE, (int)U_O_CIRC));
        map_.put(E_U_OTILDE, StringList.simpleNumberFormat(ENCODE, (int)U_O_TILDE));
        map_.put(E_U_OUML, StringList.simpleNumberFormat(ENCODE, (int)U_O_UML));
        map_.put(E_TIMES, StringList.simpleNumberFormat(ENCODE, (int)TIMES));
        map_.put(E_U_OSLASH, StringList.simpleNumberFormat(ENCODE, (int)U_O_SLASH));
        map_.put(E_U_UGRAVE, StringList.simpleNumberFormat(ENCODE, (int)U_U_GRAVE));
        map_.put(E_U_UACUTE, StringList.simpleNumberFormat(ENCODE, (int)U_U_ACUTE));
        map_.put(E_U_UCIRC, StringList.simpleNumberFormat(ENCODE, (int)U_U_CIRC));
        map_.put(E_U_UUML, StringList.simpleNumberFormat(ENCODE, (int)U_U_UML));
        map_.put(E_U_YACUTE, StringList.simpleNumberFormat(ENCODE, (int)U_Y_ACUTE));
        map_.put(E_U_THORN, StringList.simpleNumberFormat(ENCODE, (int)U_THORN));
        map_.put(E_SZLIG, StringList.simpleNumberFormat(ENCODE, (int)SZLIG));
        map_.put(E_AGRAVE, StringList.simpleNumberFormat(ENCODE, (int)AGRAVE));
        map_.put(E_AACUTE, StringList.simpleNumberFormat(ENCODE, (int)AACUTE));
        map_.put(E_ACIRC, StringList.simpleNumberFormat(ENCODE, (int)ACIRC));
        map_.put(E_ATILDE, StringList.simpleNumberFormat(ENCODE, (int)ATILDE));
        map_.put(E_AUML, StringList.simpleNumberFormat(ENCODE, (int)AUML));
        map_.put(E_ARING, StringList.simpleNumberFormat(ENCODE, (int)ARING));
        map_.put(E_AELIG, StringList.simpleNumberFormat(ENCODE, (int)AELIG));
        map_.put(E_CCEDIL, StringList.simpleNumberFormat(ENCODE, (int)CCEDIL));
        map_.put(E_EGRAVE, StringList.simpleNumberFormat(ENCODE, (int)EGRAVE));
        map_.put(E_EACUTE, StringList.simpleNumberFormat(ENCODE, (int)EACUTE));
        map_.put(E_ECIRC, StringList.simpleNumberFormat(ENCODE, (int)ECIRC));
        map_.put(E_EUML, StringList.simpleNumberFormat(ENCODE, (int)EUML));
        map_.put(E_IGRAVE, StringList.simpleNumberFormat(ENCODE, (int)IGRAVE));
        map_.put(E_IACUTE, StringList.simpleNumberFormat(ENCODE, (int)IACUTE));
        map_.put(E_ICIRC, StringList.simpleNumberFormat(ENCODE, (int)ICIRC));
        map_.put(E_IUML, StringList.simpleNumberFormat(ENCODE, (int)IUML));
        map_.put(E_ETH, StringList.simpleNumberFormat(ENCODE, (int)ETH));
        map_.put(E_NTILDE, StringList.simpleNumberFormat(ENCODE, (int)NTILDE));
        map_.put(E_OGRAVE, StringList.simpleNumberFormat(ENCODE, (int)OGRAVE));
        map_.put(E_OACUTE, StringList.simpleNumberFormat(ENCODE, (int)OACUTE));
        map_.put(E_OCIRC, StringList.simpleNumberFormat(ENCODE, (int)OCIRC));
        map_.put(E_OTILDE, StringList.simpleNumberFormat(ENCODE, (int)OTILDE));
        map_.put(E_OUML, StringList.simpleNumberFormat(ENCODE, (int)OUML));
        map_.put(E_DIVIDE, StringList.simpleNumberFormat(ENCODE, (int)DIVIDE));
        map_.put(E_OSLASH, StringList.simpleNumberFormat(ENCODE, (int)OSLASH));
        map_.put(E_UGRAVE, StringList.simpleNumberFormat(ENCODE, (int)UGRAVE));
        map_.put(E_UACUTE, StringList.simpleNumberFormat(ENCODE, (int)UACUTE));
        map_.put(E_UCIRC, StringList.simpleNumberFormat(ENCODE, (int)UCIRC));
        map_.put(E_UUML, StringList.simpleNumberFormat(ENCODE, (int)UUML));
        map_.put(E_YACUTE, StringList.simpleNumberFormat(ENCODE, (int)YACUTE));
        map_.put(E_THORN, StringList.simpleNumberFormat(ENCODE, (int)THORN));
        map_.put(E_YUML, StringList.simpleNumberFormat(ENCODE, (int)YUML));
        map_.put(E_QUOT, StringList.simpleNumberFormat(ENCODE, (int)QUOT));
        if (_affectLtGt) {
            map_.put(E_LT, StringList.simpleNumberFormat(ENCODE, (int)LT));
            map_.put(E_GT, StringList.simpleNumberFormat(ENCODE, (int)GT));
        }
        map_.put(E_APOS, StringList.simpleNumberFormat(ENCODE, (int)APOS));
        map_.put(E_U_OELIG, StringList.simpleNumberFormat(ENCODE, (int)U_OE_LIG));
        map_.put(E_OELIG, StringList.simpleNumberFormat(ENCODE, (int)OELIG));
        map_.put(E_S_CARON, StringList.simpleNumberFormat(ENCODE, (int)U_SCARON));
        map_.put(E_SCARON, StringList.simpleNumberFormat(ENCODE, (int)SCARON));
        map_.put(E_U_YUML, StringList.simpleNumberFormat(ENCODE, (int)U_Y_UML));
        map_.put(E_CIRC, StringList.simpleNumberFormat(ENCODE, (int)CIRC));
        map_.put(E_TILDE, StringList.simpleNumberFormat(ENCODE, (int)TILDE));
        map_.put(E_ENSP, StringList.simpleNumberFormat(ENCODE, (int)ENSP));
        map_.put(E_EMSP, StringList.simpleNumberFormat(ENCODE, (int)EMSP));
        map_.put(E_THINSP, StringList.simpleNumberFormat(ENCODE, (int)THINSP));
        map_.put(E_ZWNJ, StringList.simpleNumberFormat(ENCODE, (int)ZWNJ));
        map_.put(E_ZWJ, StringList.simpleNumberFormat(ENCODE, (int)ZWJ));
        map_.put(E_LRM, StringList.simpleNumberFormat(ENCODE, (int)LRM));
        map_.put(E_RLM, StringList.simpleNumberFormat(ENCODE, (int)RLM));
        map_.put(E_NDASH, StringList.simpleNumberFormat(ENCODE, (int)NDASH));
        map_.put(E_MDASH, StringList.simpleNumberFormat(ENCODE, (int)MDASH));
        map_.put(E_LSQUO, StringList.simpleNumberFormat(ENCODE, (int)LSQUO));
        map_.put(E_RSQUO, StringList.simpleNumberFormat(ENCODE, (int)RSQUO));
        map_.put(E_SBQUO, StringList.simpleNumberFormat(ENCODE, (int)SBQUO));
        map_.put(E_LDQUO, StringList.simpleNumberFormat(ENCODE, (int)LDQUO));
        map_.put(E_RDQUO, StringList.simpleNumberFormat(ENCODE, (int)RDQUO));
        map_.put(E_BDQUO, StringList.simpleNumberFormat(ENCODE, (int)BDQUO));
        map_.put(E_DAGGER, StringList.simpleNumberFormat(ENCODE, (int)D_AGGER));
        map_.put(E_U_DAGGER, StringList.simpleNumberFormat(ENCODE, (int)DAGGER));
        map_.put(E_PERMIL, StringList.simpleNumberFormat(ENCODE, (int)PERMIL));
        map_.put(E_LSAQUO, StringList.simpleNumberFormat(ENCODE, (int)LSAQUO));
        map_.put(E_RSAQUO, StringList.simpleNumberFormat(ENCODE, (int)RSAQUO));
        map_.put(E_EURO, StringList.simpleNumberFormat(ENCODE, (int)EURO));
        map_.put(E_FNOF, StringList.simpleNumberFormat(ENCODE, (int)FNOF));
        map_.put(E_U_ALPHA, StringList.simpleNumberFormat(ENCODE, (int)U_A_LPHA));
        map_.put(E_U_BETA, StringList.simpleNumberFormat(ENCODE, (int)U_B_ETA));
        map_.put(E_U_GAMMA, StringList.simpleNumberFormat(ENCODE, (int)U_G_AMMA));
        map_.put(E_U_DELTA, StringList.simpleNumberFormat(ENCODE, (int)U_D_ELTA));
        map_.put(E_U_EPSILON, StringList.simpleNumberFormat(ENCODE, (int)U_E_PSILON));
        map_.put(E_U_ZETA, StringList.simpleNumberFormat(ENCODE, (int)U_Z_ETA));
        map_.put(E_U_ETA, StringList.simpleNumberFormat(ENCODE, (int)U_E_TA));
        map_.put(E_U_THETA, StringList.simpleNumberFormat(ENCODE, (int)U_T_HETA));
        map_.put(E_U_IOTA, StringList.simpleNumberFormat(ENCODE, (int)U_I_OTA));
        map_.put(E_U_KAPPA, StringList.simpleNumberFormat(ENCODE, (int)U_K_APPA));
        map_.put(E_U_LAMBDA, StringList.simpleNumberFormat(ENCODE, (int)U_L_AMBDA));
        map_.put(E_U_MU, StringList.simpleNumberFormat(ENCODE, (int)U_M_U));
        map_.put(E_U_NU, StringList.simpleNumberFormat(ENCODE, (int)U_N_U));
        map_.put(E_U_XI, StringList.simpleNumberFormat(ENCODE, (int)U_X_I));
        map_.put(E_U_OMICRON, StringList.simpleNumberFormat(ENCODE, (int)U_O_MICRON));
        map_.put(E_U_PI, StringList.simpleNumberFormat(ENCODE, (int)U_P_I));
        map_.put(E_U_RHO, StringList.simpleNumberFormat(ENCODE, (int)U_R_HO));
        map_.put(E_U_SIGMA, StringList.simpleNumberFormat(ENCODE, (int)U_S_IGMA));
        map_.put(E_U_TAU, StringList.simpleNumberFormat(ENCODE, (int)U_T_AU));
        map_.put(E_U_UPSILON, StringList.simpleNumberFormat(ENCODE, (int)U_U_PSILON));
        map_.put(E_U_PHI, StringList.simpleNumberFormat(ENCODE, (int)U_P_HI));
        map_.put(E_U_CHI, StringList.simpleNumberFormat(ENCODE, (int)U_C_HI));
        map_.put(E_U_PSI, StringList.simpleNumberFormat(ENCODE, (int)U_P_SI));
        map_.put(E_U_OMEGA, StringList.simpleNumberFormat(ENCODE, (int)U_O_MEGA));
        map_.put(E_ALPHA, StringList.simpleNumberFormat(ENCODE, (int)ALPHA));
        map_.put(E_BETA, StringList.simpleNumberFormat(ENCODE, (int)BETA));
        map_.put(E_GAMMA, StringList.simpleNumberFormat(ENCODE, (int)GAMMA));
        map_.put(E_DELTA, StringList.simpleNumberFormat(ENCODE, (int)DELTA));
        map_.put(E_EPSILON, StringList.simpleNumberFormat(ENCODE, (int)EPSILON));
        map_.put(E_ZETA, StringList.simpleNumberFormat(ENCODE, (int)ZETA));
        map_.put(E_ETA, StringList.simpleNumberFormat(ENCODE, (int)ETA));
        map_.put(E_THETA, StringList.simpleNumberFormat(ENCODE, (int)THETA));
        map_.put(E_IOTA, StringList.simpleNumberFormat(ENCODE, (int)IOTA));
        map_.put(E_KAPPA, StringList.simpleNumberFormat(ENCODE, (int)KAPPA));
        map_.put(E_LAMBDA, StringList.simpleNumberFormat(ENCODE, (int)LAMBDA));
        map_.put(E_MU, StringList.simpleNumberFormat(ENCODE, (int)MU));
        map_.put(E_NU, StringList.simpleNumberFormat(ENCODE, (int)NU));
        map_.put(E_XI, StringList.simpleNumberFormat(ENCODE, (int)XI));
        map_.put(E_OMICRON, StringList.simpleNumberFormat(ENCODE, (int)OMICRON));
        map_.put(E_PI, StringList.simpleNumberFormat(ENCODE, (int)PI));
        map_.put(E_RHO, StringList.simpleNumberFormat(ENCODE, (int)RHO));
        map_.put(E_SIGMAF, StringList.simpleNumberFormat(ENCODE, (int)SIGMAF));
        map_.put(E_SIGMA, StringList.simpleNumberFormat(ENCODE, (int)SIGMA));
        map_.put(E_TAU, StringList.simpleNumberFormat(ENCODE, (int)TAU));
        map_.put(E_UPSILON, StringList.simpleNumberFormat(ENCODE, (int)UPSILON));
        map_.put(E_PHI, StringList.simpleNumberFormat(ENCODE, (int)PHI));
        map_.put(E_CHI, StringList.simpleNumberFormat(ENCODE, (int)CHI));
        map_.put(E_PSI, StringList.simpleNumberFormat(ENCODE, (int)PSI));
        map_.put(E_OMEGA, StringList.simpleNumberFormat(ENCODE, (int)OMEGA));
        map_.put(E_THETASYM, StringList.simpleNumberFormat(ENCODE, (int)THETASYM));
        map_.put(E_UPSIH, StringList.simpleNumberFormat(ENCODE, (int)UPSIH));
        map_.put(E_PIV, StringList.simpleNumberFormat(ENCODE, (int)PIV));
        map_.put(E_BULL, StringList.simpleNumberFormat(ENCODE, (int)BULL));
        map_.put(E_HELLIP, StringList.simpleNumberFormat(ENCODE, (int)HELLIP));
        map_.put(E_PRIME, StringList.simpleNumberFormat(ENCODE, (int)PRIME));
        map_.put(E_P_RIME, StringList.simpleNumberFormat(ENCODE, (int)U_PRIME));
        map_.put(E_OLINE, StringList.simpleNumberFormat(ENCODE, (int)OLINE));
        map_.put(E_FRASL, StringList.simpleNumberFormat(ENCODE, (int)FRASL));
        map_.put(E_WEIERP, StringList.simpleNumberFormat(ENCODE, (int)WEIERP));
        map_.put(E_IMAGE, StringList.simpleNumberFormat(ENCODE, (int)IMAGE));
        map_.put(E_REAL, StringList.simpleNumberFormat(ENCODE, (int)REAL));
        map_.put(E_TRADE, StringList.simpleNumberFormat(ENCODE, (int)TRADE));
        map_.put(E_ALEFSYM, StringList.simpleNumberFormat(ENCODE, (int)ALEFSYM));
        map_.put(E_LARR, StringList.simpleNumberFormat(ENCODE, (int)LARR));
        map_.put(E_UARR, StringList.simpleNumberFormat(ENCODE, (int)UARR));
        map_.put(E_RARR, StringList.simpleNumberFormat(ENCODE, (int)RARR));
        map_.put(E_DARR, StringList.simpleNumberFormat(ENCODE, (int)DARR));
        map_.put(E_HARR, StringList.simpleNumberFormat(ENCODE, (int)HARR));
        map_.put(E_CRARR, StringList.simpleNumberFormat(ENCODE, (int)CRARR));
        map_.put(E_L_ARR, StringList.simpleNumberFormat(ENCODE, (int)U_LARR));
        map_.put(E_U_ARR, StringList.simpleNumberFormat(ENCODE, (int)U_UARR));
        map_.put(E_R_ARR, StringList.simpleNumberFormat(ENCODE, (int)U_RARR));
        map_.put(E_D_ARR, StringList.simpleNumberFormat(ENCODE, (int)U_DARR));
        map_.put(E_H_ARR, StringList.simpleNumberFormat(ENCODE, (int)U_HARR));
        map_.put(E_FORALL, StringList.simpleNumberFormat(ENCODE, (int)FORALL));
        map_.put(E_PART, StringList.simpleNumberFormat(ENCODE, (int)PART));
        map_.put(E_EXIST, StringList.simpleNumberFormat(ENCODE, (int)EXIST));
        map_.put(E_EMPTY, StringList.simpleNumberFormat(ENCODE, (int)EMPTY));
        map_.put(E_NABLA, StringList.simpleNumberFormat(ENCODE, (int)NABLA));
        map_.put(E_ISIN, StringList.simpleNumberFormat(ENCODE, (int)ISIN));
        map_.put(E_NOTIN, StringList.simpleNumberFormat(ENCODE, (int)NOTIN));
        map_.put(E_NI, StringList.simpleNumberFormat(ENCODE, (int)NI));
        map_.put(E_PROD, StringList.simpleNumberFormat(ENCODE, (int)PROD));
        map_.put(E_SUM, StringList.simpleNumberFormat(ENCODE, (int)SUM));
        map_.put(E_MINUS, StringList.simpleNumberFormat(ENCODE, (int)MINUS));
        map_.put(E_LOWAST, StringList.simpleNumberFormat(ENCODE, (int)LOWAST));
        map_.put(E_RADIC, StringList.simpleNumberFormat(ENCODE, (int)RADIC));
        map_.put(E_PROP, StringList.simpleNumberFormat(ENCODE, (int)PROP));
        map_.put(E_INFIN, StringList.simpleNumberFormat(ENCODE, (int)INFIN));
        map_.put(E_ANG, StringList.simpleNumberFormat(ENCODE, (int)ANG));
        map_.put(E_AND, StringList.simpleNumberFormat(ENCODE, (int)AND));
        map_.put(E_OR, StringList.simpleNumberFormat(ENCODE, (int)OR));
        map_.put(E_CAP, StringList.simpleNumberFormat(ENCODE, (int)CAP));
        map_.put(E_CUP, StringList.simpleNumberFormat(ENCODE, (int)CUP));
        map_.put(E_INT, StringList.simpleNumberFormat(ENCODE, (int)INT));
        map_.put(E_THERE4, StringList.simpleNumberFormat(ENCODE, (int)THERE4));
        map_.put(E_SIM, StringList.simpleNumberFormat(ENCODE, (int)SIM));
        map_.put(E_CONG, StringList.simpleNumberFormat(ENCODE, (int)CONG));
        map_.put(E_ASYMP, StringList.simpleNumberFormat(ENCODE, (int)ASYMP));
        map_.put(E_NE, StringList.simpleNumberFormat(ENCODE, (int)NE));
        map_.put(E_EQUIV, StringList.simpleNumberFormat(ENCODE, (int)EQUIV));
        map_.put(E_LE, StringList.simpleNumberFormat(ENCODE, (int)LE));
        map_.put(E_GE, StringList.simpleNumberFormat(ENCODE, (int)GE));
        map_.put(E_SUB, StringList.simpleNumberFormat(ENCODE, (int)SUB));
        map_.put(E_SUP, StringList.simpleNumberFormat(ENCODE, (int)SUP));
        map_.put(E_NSUB, StringList.simpleNumberFormat(ENCODE, (int)NSUB));
        map_.put(E_SUBE, StringList.simpleNumberFormat(ENCODE, (int)SUBE));
        map_.put(E_SUPE, StringList.simpleNumberFormat(ENCODE, (int)SUPE));
        map_.put(E_OPLUS, StringList.simpleNumberFormat(ENCODE, (int)OPLUS));
        map_.put(E_OTIMES, StringList.simpleNumberFormat(ENCODE, (int)OTIMES));
        map_.put(E_PERP, StringList.simpleNumberFormat(ENCODE, (int)PERP));
        map_.put(E_SDOT, StringList.simpleNumberFormat(ENCODE, (int)SDOT));
        map_.put(E_LCEIL, StringList.simpleNumberFormat(ENCODE, (int)LCEIL));
        map_.put(E_RCEIL, StringList.simpleNumberFormat(ENCODE, (int)RCEIL));
        map_.put(E_LFLOOR, StringList.simpleNumberFormat(ENCODE, (int)LFLOOR));
        map_.put(E_RFLOOR, StringList.simpleNumberFormat(ENCODE, (int)RFLOOR));
        map_.put(E_LANG, StringList.simpleNumberFormat(ENCODE, (int)LANG));
        map_.put(E_RANG, StringList.simpleNumberFormat(ENCODE, (int)RANG));
        map_.put(E_LOZ, StringList.simpleNumberFormat(ENCODE, (int)LOZ));
        map_.put(E_SPADES, StringList.simpleNumberFormat(ENCODE, (int)SPADES));
        map_.put(E_CLUBS, StringList.simpleNumberFormat(ENCODE, (int)CLUBS));
        map_.put(E_HEARTS, StringList.simpleNumberFormat(ENCODE, (int)HEARTS));
        map_.put(E_DIAMS, StringList.simpleNumberFormat(ENCODE, (int)DIAMS));
        if (_affectEamp) {
            map_.put(E_AMP, StringList.simpleNumberFormat(ENCODE, (int)ASCII_38));
        }
        int length_ = _htmlText.length();
        StringBuilder str_ = new StringBuilder();
        int i_ = 0;
        int iBegin_;
        while (i_ < length_) {
            char ch_ = _htmlText.charAt(i_);
            if (ch_ != ENCODED) {
                str_.append(ch_);
                i_++;
                continue;
            }
            iBegin_ = i_;
            while(true) {
                i_++;
                if (i_ >= length_) {
                    break;
                }
                ch_ = _htmlText.charAt(i_);
                if (ch_ == END_ESCAPED) {
                    break;
                }
            }
            if (i_ >= length_) {
                str_.append(_htmlText.substring(iBegin_));
                break;
            }
            boolean add_ = false;
            for (EntryCust<String,String> k: map_.entryList()) {
                boolean equals_ = true;
                int j_ = 0;
                String key_ = k.getKey();
                for (int i = iBegin_; i <= i_; i++) {
                    if (_htmlText.charAt(i) != key_.charAt(j_)) {
                        equals_ = false;
                        break;
                    }
                    j_++;
                }
                if (equals_) {
                    String strValue_ = k.getValue();
                    strValue_ = strValue_.substring(2, strValue_.length() - 1);
                    int ascii_ = Numbers.parseInt(strValue_);
                    char char_ = (char) ascii_;
                    str_.append(char_);
                    i_++;
                    add_ = true;
                    break;
                }
            }
            if (!add_) {
                if (_htmlText.charAt(iBegin_ + 1) == NUMBERED_CHAR) {
                    String strValue_ = _htmlText.substring(iBegin_ + 2, i_);
                    int ascii_ = (int) Numbers.parseLongZero(strValue_);
                    char char_ = (char) ascii_;
                    str_.append(char_);
                    i_++;
                    continue;
                }
                str_.append(_htmlText, iBegin_, i_ + 1);
                i_++;
            }
        }
        return str_.toString();
    }
    public static String encodeHtml(String _htmlText) {
        StringMap<String> map_ = new StringMap<String>();
        map_.put(E_NBSP, StringList.simpleNumberFormat(ENCODE, (int)NBSP));
        map_.put(E_IEXCL, StringList.simpleNumberFormat(ENCODE, (int)IEXCL));
        map_.put(E_CENT, StringList.simpleNumberFormat(ENCODE, (int)CENT));
        map_.put(E_POUND, StringList.simpleNumberFormat(ENCODE, (int)POUND));
        map_.put(E_CURREN, StringList.simpleNumberFormat(ENCODE, (int)CURREN));
        map_.put(E_YEN, StringList.simpleNumberFormat(ENCODE, (int)YEN));
        map_.put(E_BRVBAR, StringList.simpleNumberFormat(ENCODE, (int)BRVBAR));
        map_.put(E_SECT, StringList.simpleNumberFormat(ENCODE, (int)SECT));
        map_.put(E_UML, StringList.simpleNumberFormat(ENCODE, (int)UML));
        map_.put(E_COPY, StringList.simpleNumberFormat(ENCODE, (int)COPY));
        map_.put(E_ORDF, StringList.simpleNumberFormat(ENCODE, (int)ORDF));
        map_.put(E_LAQUO, StringList.simpleNumberFormat(ENCODE, (int)LAQUO));
        map_.put(E_NOT, StringList.simpleNumberFormat(ENCODE, (int)NOT));
        map_.put(E_SHY, StringList.simpleNumberFormat(ENCODE, (int)SHY));
        map_.put(E_REG, StringList.simpleNumberFormat(ENCODE, (int)REG));
        map_.put(E_MACR, StringList.simpleNumberFormat(ENCODE, (int)MACR));
        map_.put(E_DEG, StringList.simpleNumberFormat(ENCODE, (int)DEG));
        map_.put(E_PLUSMN, StringList.simpleNumberFormat(ENCODE, (int)PLUSMN));
        map_.put(E_SUP2, StringList.simpleNumberFormat(ENCODE, (int)SUP2));
        map_.put(E_SUP3, StringList.simpleNumberFormat(ENCODE, (int)SUP3));
        map_.put(E_ACUTE, StringList.simpleNumberFormat(ENCODE, (int)ACUTE));
        map_.put(E_MICRO, StringList.simpleNumberFormat(ENCODE, (int)MICRO));
        map_.put(E_PARA, StringList.simpleNumberFormat(ENCODE, (int)PARA));
        map_.put(E_MIDDOT, StringList.simpleNumberFormat(ENCODE, (int)MIDDOT));
        map_.put(E_CEDIL, StringList.simpleNumberFormat(ENCODE, (int)CEDIL));
        map_.put(E_SUP1, StringList.simpleNumberFormat(ENCODE, (int)SUP1));
        map_.put(E_ORDM, StringList.simpleNumberFormat(ENCODE, (int)ORDM));
        map_.put(E_RAQUO, StringList.simpleNumberFormat(ENCODE, (int)RAQUO));
        map_.put(E_FRAC14, StringList.simpleNumberFormat(ENCODE, (int)FRAC14));
        map_.put(E_FRAC12, StringList.simpleNumberFormat(ENCODE, (int)FRAC12));
        map_.put(E_FRAC34, StringList.simpleNumberFormat(ENCODE, (int)FRAC34));
        map_.put(E_IQUEST, StringList.simpleNumberFormat(ENCODE, (int)IQUEST));
        map_.put(E_U_AGRAVE, StringList.simpleNumberFormat(ENCODE, (int)U_A_GRAVE));
        map_.put(E_U_AACUTE, StringList.simpleNumberFormat(ENCODE, (int)U_A_ACUTE));
        map_.put(E_U_ACIRC, StringList.simpleNumberFormat(ENCODE, (int)U_A_CIRC));
        map_.put(E_U_ATILDE, StringList.simpleNumberFormat(ENCODE, (int)U_A_TILDE));
        map_.put(E_U_AUML, StringList.simpleNumberFormat(ENCODE, (int)U_A_UML));
        map_.put(E_U_ARING, StringList.simpleNumberFormat(ENCODE, (int)U_A_RING));
        map_.put(E_U_AELIG, StringList.simpleNumberFormat(ENCODE, (int)U_AE_LIG));
        map_.put(E_U_CCEDIL, StringList.simpleNumberFormat(ENCODE, (int)U_C_CEDIL));
        map_.put(E_U_EGRAVE, StringList.simpleNumberFormat(ENCODE, (int)U_E_GRAVE));
        map_.put(E_U_EACUTE, StringList.simpleNumberFormat(ENCODE, (int)U_E_ACUTE));
        map_.put(E_U_ECIRC, StringList.simpleNumberFormat(ENCODE, (int)U_E_CIRC));
        map_.put(E_U_EUML, StringList.simpleNumberFormat(ENCODE, (int)U_E_UML));
        map_.put(E_U_IGRAVE, StringList.simpleNumberFormat(ENCODE, (int)U_I_GRAVE));
        map_.put(E_U_IACUTE, StringList.simpleNumberFormat(ENCODE, (int)U_I_ACUTE));
        map_.put(E_U_ICIRC, StringList.simpleNumberFormat(ENCODE, (int)U_I_CIRC));
        map_.put(E_U_IUML, StringList.simpleNumberFormat(ENCODE, (int)U_I_UML));
        map_.put(E_U_ETH, StringList.simpleNumberFormat(ENCODE, (int)U_ETH));
        map_.put(E_U_NTILDE, StringList.simpleNumberFormat(ENCODE, (int)U_N_TILDE));
        map_.put(E_U_OGRAVE, StringList.simpleNumberFormat(ENCODE, (int)U_O_GRAVE));
        map_.put(E_U_OACUTE, StringList.simpleNumberFormat(ENCODE, (int)U_O_ACUTE));
        map_.put(E_U_OCIRC, StringList.simpleNumberFormat(ENCODE, (int)U_O_CIRC));
        map_.put(E_U_OTILDE, StringList.simpleNumberFormat(ENCODE, (int)U_O_TILDE));
        map_.put(E_U_OUML, StringList.simpleNumberFormat(ENCODE, (int)U_O_UML));
        map_.put(E_TIMES, StringList.simpleNumberFormat(ENCODE, (int)TIMES));
        map_.put(E_U_OSLASH, StringList.simpleNumberFormat(ENCODE, (int)U_O_SLASH));
        map_.put(E_U_UGRAVE, StringList.simpleNumberFormat(ENCODE, (int)U_U_GRAVE));
        map_.put(E_U_UACUTE, StringList.simpleNumberFormat(ENCODE, (int)U_U_ACUTE));
        map_.put(E_U_UCIRC, StringList.simpleNumberFormat(ENCODE, (int)U_U_CIRC));
        map_.put(E_U_UUML, StringList.simpleNumberFormat(ENCODE, (int)U_U_UML));
        map_.put(E_U_YACUTE, StringList.simpleNumberFormat(ENCODE, (int)U_Y_ACUTE));
        map_.put(E_U_THORN, StringList.simpleNumberFormat(ENCODE, (int)U_THORN));
        map_.put(E_SZLIG, StringList.simpleNumberFormat(ENCODE, (int)SZLIG));
        map_.put(E_AGRAVE, StringList.simpleNumberFormat(ENCODE, (int)AGRAVE));
        map_.put(E_AACUTE, StringList.simpleNumberFormat(ENCODE, (int)AACUTE));
        map_.put(E_ACIRC, StringList.simpleNumberFormat(ENCODE, (int)ACIRC));
        map_.put(E_ATILDE, StringList.simpleNumberFormat(ENCODE, (int)ATILDE));
        map_.put(E_AUML, StringList.simpleNumberFormat(ENCODE, (int)AUML));
        map_.put(E_ARING, StringList.simpleNumberFormat(ENCODE, (int)ARING));
        map_.put(E_AELIG, StringList.simpleNumberFormat(ENCODE, (int)AELIG));
        map_.put(E_CCEDIL, StringList.simpleNumberFormat(ENCODE, (int)CCEDIL));
        map_.put(E_EGRAVE, StringList.simpleNumberFormat(ENCODE, (int)EGRAVE));
        map_.put(E_EACUTE, StringList.simpleNumberFormat(ENCODE, (int)EACUTE));
        map_.put(E_ECIRC, StringList.simpleNumberFormat(ENCODE, (int)ECIRC));
        map_.put(E_EUML, StringList.simpleNumberFormat(ENCODE, (int)EUML));
        map_.put(E_IGRAVE, StringList.simpleNumberFormat(ENCODE, (int)IGRAVE));
        map_.put(E_IACUTE, StringList.simpleNumberFormat(ENCODE, (int)IACUTE));
        map_.put(E_ICIRC, StringList.simpleNumberFormat(ENCODE, (int)ICIRC));
        map_.put(E_IUML, StringList.simpleNumberFormat(ENCODE, (int)IUML));
        map_.put(E_ETH, StringList.simpleNumberFormat(ENCODE, (int)ETH));
        map_.put(E_NTILDE, StringList.simpleNumberFormat(ENCODE, (int)NTILDE));
        map_.put(E_OGRAVE, StringList.simpleNumberFormat(ENCODE, (int)OGRAVE));
        map_.put(E_OACUTE, StringList.simpleNumberFormat(ENCODE, (int)OACUTE));
        map_.put(E_OCIRC, StringList.simpleNumberFormat(ENCODE, (int)OCIRC));
        map_.put(E_OTILDE, StringList.simpleNumberFormat(ENCODE, (int)OTILDE));
        map_.put(E_OUML, StringList.simpleNumberFormat(ENCODE, (int)OUML));
        map_.put(E_DIVIDE, StringList.simpleNumberFormat(ENCODE, (int)DIVIDE));
        map_.put(E_OSLASH, StringList.simpleNumberFormat(ENCODE, (int)OSLASH));
        map_.put(E_UGRAVE, StringList.simpleNumberFormat(ENCODE, (int)UGRAVE));
        map_.put(E_UACUTE, StringList.simpleNumberFormat(ENCODE, (int)UACUTE));
        map_.put(E_UCIRC, StringList.simpleNumberFormat(ENCODE, (int)UCIRC));
        map_.put(E_UUML, StringList.simpleNumberFormat(ENCODE, (int)UUML));
        map_.put(E_YACUTE, StringList.simpleNumberFormat(ENCODE, (int)YACUTE));
        map_.put(E_THORN, StringList.simpleNumberFormat(ENCODE, (int)THORN));
        map_.put(E_YUML, StringList.simpleNumberFormat(ENCODE, (int)YUML));
        map_.put(E_QUOT, StringList.simpleNumberFormat(ENCODE, (int)QUOT));
        map_.put(E_LT, StringList.simpleNumberFormat(ENCODE, (int)LT));
        map_.put(E_GT, StringList.simpleNumberFormat(ENCODE, (int)GT));
        map_.put(E_APOS, StringList.simpleNumberFormat(ENCODE, (int)APOS));
        map_.put(E_U_OELIG, StringList.simpleNumberFormat(ENCODE, (int)U_OE_LIG));
        map_.put(E_OELIG, StringList.simpleNumberFormat(ENCODE, (int)OELIG));
        map_.put(E_S_CARON, StringList.simpleNumberFormat(ENCODE, (int)U_SCARON));
        map_.put(E_SCARON, StringList.simpleNumberFormat(ENCODE, (int)SCARON));
        map_.put(E_U_YUML, StringList.simpleNumberFormat(ENCODE, (int)U_Y_UML));
        map_.put(E_CIRC, StringList.simpleNumberFormat(ENCODE, (int)CIRC));
        map_.put(E_TILDE, StringList.simpleNumberFormat(ENCODE, (int)TILDE));
        map_.put(E_ENSP, StringList.simpleNumberFormat(ENCODE, (int)ENSP));
        map_.put(E_EMSP, StringList.simpleNumberFormat(ENCODE, (int)EMSP));
        map_.put(E_THINSP, StringList.simpleNumberFormat(ENCODE, (int)THINSP));
        map_.put(E_ZWNJ, StringList.simpleNumberFormat(ENCODE, (int)ZWNJ));
        map_.put(E_ZWJ, StringList.simpleNumberFormat(ENCODE, (int)ZWJ));
        map_.put(E_LRM, StringList.simpleNumberFormat(ENCODE, (int)LRM));
        map_.put(E_RLM, StringList.simpleNumberFormat(ENCODE, (int)RLM));
        map_.put(E_NDASH, StringList.simpleNumberFormat(ENCODE, (int)NDASH));
        map_.put(E_MDASH, StringList.simpleNumberFormat(ENCODE, (int)MDASH));
        map_.put(E_LSQUO, StringList.simpleNumberFormat(ENCODE, (int)LSQUO));
        map_.put(E_RSQUO, StringList.simpleNumberFormat(ENCODE, (int)RSQUO));
        map_.put(E_SBQUO, StringList.simpleNumberFormat(ENCODE, (int)SBQUO));
        map_.put(E_LDQUO, StringList.simpleNumberFormat(ENCODE, (int)LDQUO));
        map_.put(E_RDQUO, StringList.simpleNumberFormat(ENCODE, (int)RDQUO));
        map_.put(E_BDQUO, StringList.simpleNumberFormat(ENCODE, (int)BDQUO));
        map_.put(E_DAGGER, StringList.simpleNumberFormat(ENCODE, (int)D_AGGER));
        map_.put(E_U_DAGGER, StringList.simpleNumberFormat(ENCODE, (int)DAGGER));
        map_.put(E_PERMIL, StringList.simpleNumberFormat(ENCODE, (int)PERMIL));
        map_.put(E_LSAQUO, StringList.simpleNumberFormat(ENCODE, (int)LSAQUO));
        map_.put(E_RSAQUO, StringList.simpleNumberFormat(ENCODE, (int)RSAQUO));
        map_.put(E_EURO, StringList.simpleNumberFormat(ENCODE, (int)EURO));
        map_.put(E_FNOF, StringList.simpleNumberFormat(ENCODE, (int)FNOF));
        map_.put(E_U_ALPHA, StringList.simpleNumberFormat(ENCODE, (int)U_A_LPHA));
        map_.put(E_U_BETA, StringList.simpleNumberFormat(ENCODE, (int)U_B_ETA));
        map_.put(E_U_GAMMA, StringList.simpleNumberFormat(ENCODE, (int)U_G_AMMA));
        map_.put(E_U_DELTA, StringList.simpleNumberFormat(ENCODE, (int)U_D_ELTA));
        map_.put(E_U_EPSILON, StringList.simpleNumberFormat(ENCODE, (int)U_E_PSILON));
        map_.put(E_U_ZETA, StringList.simpleNumberFormat(ENCODE, (int)U_Z_ETA));
        map_.put(E_U_ETA, StringList.simpleNumberFormat(ENCODE, (int)U_E_TA));
        map_.put(E_U_THETA, StringList.simpleNumberFormat(ENCODE, (int)U_T_HETA));
        map_.put(E_U_IOTA, StringList.simpleNumberFormat(ENCODE, (int)U_I_OTA));
        map_.put(E_U_KAPPA, StringList.simpleNumberFormat(ENCODE, (int)U_K_APPA));
        map_.put(E_U_LAMBDA, StringList.simpleNumberFormat(ENCODE, (int)U_L_AMBDA));
        map_.put(E_U_MU, StringList.simpleNumberFormat(ENCODE, (int)U_M_U));
        map_.put(E_U_NU, StringList.simpleNumberFormat(ENCODE, (int)U_N_U));
        map_.put(E_U_XI, StringList.simpleNumberFormat(ENCODE, (int)U_X_I));
        map_.put(E_U_OMICRON, StringList.simpleNumberFormat(ENCODE, (int)U_O_MICRON));
        map_.put(E_U_PI, StringList.simpleNumberFormat(ENCODE, (int)U_P_I));
        map_.put(E_U_RHO, StringList.simpleNumberFormat(ENCODE, (int)U_R_HO));
        map_.put(E_U_SIGMA, StringList.simpleNumberFormat(ENCODE, (int)U_S_IGMA));
        map_.put(E_U_TAU, StringList.simpleNumberFormat(ENCODE, (int)U_T_AU));
        map_.put(E_U_UPSILON, StringList.simpleNumberFormat(ENCODE, (int)U_U_PSILON));
        map_.put(E_U_PHI, StringList.simpleNumberFormat(ENCODE, (int)U_P_HI));
        map_.put(E_U_CHI, StringList.simpleNumberFormat(ENCODE, (int)U_C_HI));
        map_.put(E_U_PSI, StringList.simpleNumberFormat(ENCODE, (int)U_P_SI));
        map_.put(E_U_OMEGA, StringList.simpleNumberFormat(ENCODE, (int)U_O_MEGA));
        map_.put(E_ALPHA, StringList.simpleNumberFormat(ENCODE, (int)ALPHA));
        map_.put(E_BETA, StringList.simpleNumberFormat(ENCODE, (int)BETA));
        map_.put(E_GAMMA, StringList.simpleNumberFormat(ENCODE, (int)GAMMA));
        map_.put(E_DELTA, StringList.simpleNumberFormat(ENCODE, (int)DELTA));
        map_.put(E_EPSILON, StringList.simpleNumberFormat(ENCODE, (int)EPSILON));
        map_.put(E_ZETA, StringList.simpleNumberFormat(ENCODE, (int)ZETA));
        map_.put(E_ETA, StringList.simpleNumberFormat(ENCODE, (int)ETA));
        map_.put(E_THETA, StringList.simpleNumberFormat(ENCODE, (int)THETA));
        map_.put(E_IOTA, StringList.simpleNumberFormat(ENCODE, (int)IOTA));
        map_.put(E_KAPPA, StringList.simpleNumberFormat(ENCODE, (int)KAPPA));
        map_.put(E_LAMBDA, StringList.simpleNumberFormat(ENCODE, (int)LAMBDA));
        map_.put(E_MU, StringList.simpleNumberFormat(ENCODE, (int)MU));
        map_.put(E_NU, StringList.simpleNumberFormat(ENCODE, (int)NU));
        map_.put(E_XI, StringList.simpleNumberFormat(ENCODE, (int)XI));
        map_.put(E_OMICRON, StringList.simpleNumberFormat(ENCODE, (int)OMICRON));
        map_.put(E_PI, StringList.simpleNumberFormat(ENCODE, (int)PI));
        map_.put(E_RHO, StringList.simpleNumberFormat(ENCODE, (int)RHO));
        map_.put(E_SIGMAF, StringList.simpleNumberFormat(ENCODE, (int)SIGMAF));
        map_.put(E_SIGMA, StringList.simpleNumberFormat(ENCODE, (int)SIGMA));
        map_.put(E_TAU, StringList.simpleNumberFormat(ENCODE, (int)TAU));
        map_.put(E_UPSILON, StringList.simpleNumberFormat(ENCODE, (int)UPSILON));
        map_.put(E_PHI, StringList.simpleNumberFormat(ENCODE, (int)PHI));
        map_.put(E_CHI, StringList.simpleNumberFormat(ENCODE, (int)CHI));
        map_.put(E_PSI, StringList.simpleNumberFormat(ENCODE, (int)PSI));
        map_.put(E_OMEGA, StringList.simpleNumberFormat(ENCODE, (int)OMEGA));
        map_.put(E_THETASYM, StringList.simpleNumberFormat(ENCODE, (int)THETASYM));
        map_.put(E_UPSIH, StringList.simpleNumberFormat(ENCODE, (int)UPSIH));
        map_.put(E_PIV, StringList.simpleNumberFormat(ENCODE, (int)PIV));
        map_.put(E_BULL, StringList.simpleNumberFormat(ENCODE, (int)BULL));
        map_.put(E_HELLIP, StringList.simpleNumberFormat(ENCODE, (int)HELLIP));
        map_.put(E_PRIME, StringList.simpleNumberFormat(ENCODE, (int)PRIME));
        map_.put(E_P_RIME, StringList.simpleNumberFormat(ENCODE, (int)U_PRIME));
        map_.put(E_OLINE, StringList.simpleNumberFormat(ENCODE, (int)OLINE));
        map_.put(E_FRASL, StringList.simpleNumberFormat(ENCODE, (int)FRASL));
        map_.put(E_WEIERP, StringList.simpleNumberFormat(ENCODE, (int)WEIERP));
        map_.put(E_IMAGE, StringList.simpleNumberFormat(ENCODE, (int)IMAGE));
        map_.put(E_REAL, StringList.simpleNumberFormat(ENCODE, (int)REAL));
        map_.put(E_TRADE, StringList.simpleNumberFormat(ENCODE, (int)TRADE));
        map_.put(E_ALEFSYM, StringList.simpleNumberFormat(ENCODE, (int)ALEFSYM));
        map_.put(E_LARR, StringList.simpleNumberFormat(ENCODE, (int)LARR));
        map_.put(E_UARR, StringList.simpleNumberFormat(ENCODE, (int)UARR));
        map_.put(E_RARR, StringList.simpleNumberFormat(ENCODE, (int)RARR));
        map_.put(E_DARR, StringList.simpleNumberFormat(ENCODE, (int)DARR));
        map_.put(E_HARR, StringList.simpleNumberFormat(ENCODE, (int)HARR));
        map_.put(E_CRARR, StringList.simpleNumberFormat(ENCODE, (int)CRARR));
        map_.put(E_L_ARR, StringList.simpleNumberFormat(ENCODE, (int)U_LARR));
        map_.put(E_U_ARR, StringList.simpleNumberFormat(ENCODE, (int)U_UARR));
        map_.put(E_R_ARR, StringList.simpleNumberFormat(ENCODE, (int)U_RARR));
        map_.put(E_D_ARR, StringList.simpleNumberFormat(ENCODE, (int)U_DARR));
        map_.put(E_H_ARR, StringList.simpleNumberFormat(ENCODE, (int)U_HARR));
        map_.put(E_FORALL, StringList.simpleNumberFormat(ENCODE, (int)FORALL));
        map_.put(E_PART, StringList.simpleNumberFormat(ENCODE, (int)PART));
        map_.put(E_EXIST, StringList.simpleNumberFormat(ENCODE, (int)EXIST));
        map_.put(E_EMPTY, StringList.simpleNumberFormat(ENCODE, (int)EMPTY));
        map_.put(E_NABLA, StringList.simpleNumberFormat(ENCODE, (int)NABLA));
        map_.put(E_ISIN, StringList.simpleNumberFormat(ENCODE, (int)ISIN));
        map_.put(E_NOTIN, StringList.simpleNumberFormat(ENCODE, (int)NOTIN));
        map_.put(E_NI, StringList.simpleNumberFormat(ENCODE, (int)NI));
        map_.put(E_PROD, StringList.simpleNumberFormat(ENCODE, (int)PROD));
        map_.put(E_SUM, StringList.simpleNumberFormat(ENCODE, (int)SUM));
        map_.put(E_MINUS, StringList.simpleNumberFormat(ENCODE, (int)MINUS));
        map_.put(E_LOWAST, StringList.simpleNumberFormat(ENCODE, (int)LOWAST));
        map_.put(E_RADIC, StringList.simpleNumberFormat(ENCODE, (int)RADIC));
        map_.put(E_PROP, StringList.simpleNumberFormat(ENCODE, (int)PROP));
        map_.put(E_INFIN, StringList.simpleNumberFormat(ENCODE, (int)INFIN));
        map_.put(E_ANG, StringList.simpleNumberFormat(ENCODE, (int)ANG));
        map_.put(E_AND, StringList.simpleNumberFormat(ENCODE, (int)AND));
        map_.put(E_OR, StringList.simpleNumberFormat(ENCODE, (int)OR));
        map_.put(E_CAP, StringList.simpleNumberFormat(ENCODE, (int)CAP));
        map_.put(E_CUP, StringList.simpleNumberFormat(ENCODE, (int)CUP));
        map_.put(E_INT, StringList.simpleNumberFormat(ENCODE, (int)INT));
        map_.put(E_THERE4, StringList.simpleNumberFormat(ENCODE, (int)THERE4));
        map_.put(E_SIM, StringList.simpleNumberFormat(ENCODE, (int)SIM));
        map_.put(E_CONG, StringList.simpleNumberFormat(ENCODE, (int)CONG));
        map_.put(E_ASYMP, StringList.simpleNumberFormat(ENCODE, (int)ASYMP));
        map_.put(E_NE, StringList.simpleNumberFormat(ENCODE, (int)NE));
        map_.put(E_EQUIV, StringList.simpleNumberFormat(ENCODE, (int)EQUIV));
        map_.put(E_LE, StringList.simpleNumberFormat(ENCODE, (int)LE));
        map_.put(E_GE, StringList.simpleNumberFormat(ENCODE, (int)GE));
        map_.put(E_SUB, StringList.simpleNumberFormat(ENCODE, (int)SUB));
        map_.put(E_SUP, StringList.simpleNumberFormat(ENCODE, (int)SUP));
        map_.put(E_NSUB, StringList.simpleNumberFormat(ENCODE, (int)NSUB));
        map_.put(E_SUBE, StringList.simpleNumberFormat(ENCODE, (int)SUBE));
        map_.put(E_SUPE, StringList.simpleNumberFormat(ENCODE, (int)SUPE));
        map_.put(E_OPLUS, StringList.simpleNumberFormat(ENCODE, (int)OPLUS));
        map_.put(E_OTIMES, StringList.simpleNumberFormat(ENCODE, (int)OTIMES));
        map_.put(E_PERP, StringList.simpleNumberFormat(ENCODE, (int)PERP));
        map_.put(E_SDOT, StringList.simpleNumberFormat(ENCODE, (int)SDOT));
        map_.put(E_LCEIL, StringList.simpleNumberFormat(ENCODE, (int)LCEIL));
        map_.put(E_RCEIL, StringList.simpleNumberFormat(ENCODE, (int)RCEIL));
        map_.put(E_LFLOOR, StringList.simpleNumberFormat(ENCODE, (int)LFLOOR));
        map_.put(E_RFLOOR, StringList.simpleNumberFormat(ENCODE, (int)RFLOOR));
        map_.put(E_LANG, StringList.simpleNumberFormat(ENCODE, (int)LANG));
        map_.put(E_RANG, StringList.simpleNumberFormat(ENCODE, (int)RANG));
        map_.put(E_LOZ, StringList.simpleNumberFormat(ENCODE, (int)LOZ));
        map_.put(E_SPADES, StringList.simpleNumberFormat(ENCODE, (int)SPADES));
        map_.put(E_CLUBS, StringList.simpleNumberFormat(ENCODE, (int)CLUBS));
        map_.put(E_HEARTS, StringList.simpleNumberFormat(ENCODE, (int)HEARTS));
        map_.put(E_DIAMS, StringList.simpleNumberFormat(ENCODE, (int)DIAMS));
        map_.put(E_AMP, StringList.simpleNumberFormat(ENCODE, (int)ASCII_38));
        int length_ = _htmlText.length();
        StringBuilder str_ = new StringBuilder();
        int i_ = 0;
        int iBegin_;
        while (i_ < length_) {
            char ch_ = _htmlText.charAt(i_);
            if (ch_ != ENCODED) {
                str_.append(ch_);
                i_++;
                continue;
            }
            iBegin_ = i_;
            while(true) {
                i_++;
                if (i_ >= length_) {
                    break;
                }
                ch_ = _htmlText.charAt(i_);
                if (ch_ == END_ESCAPED) {
                    break;
                }
            }
            if (i_ >= length_) {
                str_.append(_htmlText.substring(iBegin_));
                break;
            }
            boolean add_ = false;
            for (EntryCust<String,String> k: map_.entryList()) {
                boolean equals_ = true;
                int j_ = 0;
                String key_ = k.getKey();
                for (int i = iBegin_; i <= i_; i++) {
                    //j_ is max as i_-iBegin_+1
                    if (_htmlText.charAt(i) != key_.charAt(j_)) {
                        equals_ = false;
                        break;
                    }
                    j_++;
                }
                if (equals_) {
                    str_.append(k.getValue());
                    i_++;
                    add_ = true;
                    break;
                }
            }
            if (!add_) {
                str_.append(_htmlText, iBegin_, i_ + 1);
                i_++;
            }
        }
        return str_.toString();
    }

    public static String encodeToHtml(String _text) {
        StringBuilder escapedXml_ = new StringBuilder();
        for (char c: _text.toCharArray()) {
            if (c >= ASCII_128 || c < ASCII_32) {
                escapedXml_.append(ENCODED);
                escapedXml_.append(NUMBERED_CHAR);
                escapedXml_.append((int)c);
                escapedXml_.append(END_ESCAPED);
            } else {
                escapedXml_.append(c);
            }
        }
        return escapedXml_.toString();
    }

    public static String escape(String _text, boolean _quote) {
        StringBuilder escapedXml_ = new StringBuilder();
        for (char c: _text.toCharArray()) {
            if (c == LT) {
                escapedXml_.append(E_LT);
                continue;
            }
            if (c == GT) {
                escapedXml_.append(E_GT);
                continue;
            }
            if (c == ENCODED) {
                escapedXml_.append(E_AMP);
                continue;
            }
            if (_quote) {
                if (c == QUOT) {
                    escapedXml_.append(E_QUOT);
                    continue;
                }
                if (c == APOS) {
                    escapedXml_.append(E_APOS);
                    continue;
                }
            }
            escapedXml_.append(c);
        }
        return escapedXml_.toString();
    }

    public static boolean equalsDocs(String _expected, String _found) {
        Document expDoc_ = DocumentBuilder.parseSax(_expected);
        Document foundDoc_ = DocumentBuilder.parseSax(_found);
        if (expDoc_ == null || foundDoc_ == null) {
            return false;
        }
        FullElement expElt_ = (FullElement) expDoc_.getDocumentElement();
        FullElement foundElt_ = (FullElement) foundDoc_.getDocumentElement();
        return StringList.quickEq(expElt_.exportSorted(),foundElt_.exportSorted());
    }
    public FullDocument newDocument() {
        return new FullDocument(getTabWidth());
    }
    public DocumentResult parseNoText(String _input) {
        DocumentResult res_ = new DocumentResult();
        NoTextDocument doc_ = new NoTextDocument(getTabWidth());
        int len_ = _input.length();
        int indexFoot_ = 0;
        ReadingState state_ = ReadingState.HEADER;
        boolean addChild_ = true;
        char delimiterAttr_ = 0;
        StringBuilder attributeName_ = new StringBuilder();
        StringBuilder tagName_ = new StringBuilder();
        StringList stack_ = new StringList();
//        StringBuilder currentText_ = new StringBuilder();
        Element currentElement_ = null;
        boolean finished_ = false;
//        StringBuilder currentComment_ = new StringBuilder();
        StringBuilder attributeValue_ = new StringBuilder();
        CustList<Attr> attrs_ = new CustList<Attr>();
        if (_input.isEmpty()) {
            res_.setLocation(new RowCol());
            return res_;
        }
        int i_ = CustList.FIRST_INDEX;
        if (_input.charAt(i_) != LT) {
            RowCol rc_ = new RowCol();
            rc_.setRow(1);
            rc_.setCol(1);
            res_.setLocation(rc_);
            return res_;
        }
        i_++;
        while (i_ < len_) {
            char curChar_ = _input.charAt(i_);
            if (state_ == ReadingState.HEADER) {
                if (curChar_ == LT_CHAR) {
                    break;
                }
                if (curChar_ == ENCODED) {
                    break;
                }
                if (tagName_.length() == 0) {
                    if (curChar_ == GT_CHAR) {
                        break;
                    }
                    if (curChar_ == SLASH) {
                        break;
                    }
                    if (Character.isWhitespace(curChar_)) {
                        break;
                    }
                    tagName_.append(curChar_);
                    i_++;
                    continue;
                }
                if (curChar_ == GT_CHAR) {
                    Element element_ = doc_.createElement(tagName_.toString());
                    element_.setAttributes(new NamedNodeMap(attrs_));
                    attrs_ = new CustList<Attr>();
                    if (doc_.getDocumentElement() == null) {
                        doc_.appendChild(element_);
                    } else {
                        currentElement_.appendChild(element_);
                    }
                    if (addChild_) {
                        currentElement_ = element_;
                        stack_.add(tagName_.append(GT_CHAR).toString());
                    } else {
                        if (stack_.isEmpty()) {
                            finished_ = true;
                            break;
                        }
                    }
                    tagName_.delete(CustList.FIRST_INDEX, tagName_.length());
                    if (i_ + 2 >= len_) {
                        break;
                    }
                    if (_input.charAt(i_ + 1) == LT_CHAR) {
                        if (_input.charAt(i_ + 2) == SLASH) {
                            i_++;
                            //_input.charAt(i_) == '<'
                            i_++;
                            //_input.charAt(i_) == '/'
                            i_++;
                            //_input.charAt(i_) is the first character of end tag
                            state_ = ReadingState.FOOTER;
                            indexFoot_ = i_;
                            continue;
                        }
                        i_++;
                        i_++;
                        state_ = ReadingState.HEADER;
                        addChild_ = true;
                        continue;
                    }
                    state_ = ReadingState.TEXT;
                    i_++;
                    continue;
                }
                if (Character.isWhitespace(curChar_)) {
                    int nextPrintable_ = i_;
                    while (nextPrintable_ < len_) {
                        char next_ = _input.charAt(nextPrintable_);
                        if (!Character.isWhitespace(next_)) {
                            break;
                        }
                        nextPrintable_++;
                    }
                    if (nextPrintable_ == len_) {
                        break;
                    }
                    state_ = ReadingState.ATTR_NAME;
                    i_ = nextPrintable_;
                    continue;
                }
                if (curChar_ != SLASH) {
                    tagName_.append(curChar_);
                    i_++;
                    continue;
                }
                if (i_ + 1 >= len_) {
                    break;
                }
                if (_input.charAt(i_ + 1) != GT_CHAR) {
                    break;
                }
                addChild_ = false;
                i_++;
                continue;
            }
            if (state_ == ReadingState.ATTR_NAME) {
                if (curChar_ == LT_CHAR) {
                    break;
                }
                if (curChar_ == ENCODED) {
                    break;
                }
                if (curChar_ == GT_CHAR) {
                    break;
                }
                if (curChar_ == SLASH) {
                    break;
                }
                if (!Character.isWhitespace(curChar_) && curChar_ != EQUALS) {
                    attributeName_.append(curChar_);
                    i_++;
                    continue;
                }
                if (curChar_ != EQUALS) {
                    //Character.isWhitespace(curChar_)
                    int nextPrintable_ = i_;
                    while (nextPrintable_ < len_) {
                        char next_ = _input.charAt(nextPrintable_);
                        if (!Character.isWhitespace(next_)) {
                            break;
                        }
                        nextPrintable_++;
                    }
                    if (nextPrintable_ == len_) {
                        break;
                    }
                    if (_input.charAt(nextPrintable_) != EQUALS) {
                        break;
                    }
                    i_ = nextPrintable_;
                }
                if (i_ + 1 >= len_) {
                    break;
                }
                char nextEq_ = _input.charAt(i_ + 1);
                if (nextEq_ != APOS_CHAR && nextEq_ != QUOT_CHAR) {
                    if (!Character.isWhitespace(nextEq_)) {
                        break;
                    }
                    int nextPrintable_ = i_ + 1;
                    while (nextPrintable_ < len_) {
                        char next_ = _input.charAt(nextPrintable_);
                        if (!Character.isWhitespace(next_)) {
                            break;
                        }
                        nextPrintable_++;
                    }
                    if (nextPrintable_ == len_) {
                        break;
                    }
                    char nextCharDel_ = _input.charAt(nextPrintable_);
                    if (nextCharDel_ != APOS_CHAR && nextCharDel_ != QUOT_CHAR) {
                        break;
                    }
                    i_ = nextPrintable_;
                } else {
                    i_++;
                }
                String foundName_ = attributeName_.toString();
                boolean ok_ = true;
                for (Attr a: attrs_) {
                    if (StringList.quickEq(a.getName(), foundName_)) {
                        ok_ = false;
                        break;
                    }
                }
                if (!ok_) {
                    break;
                }
                char del_ = _input.charAt(i_);
                delimiterAttr_ = del_;
                state_ = ReadingState.ATTR_VALUE;
                i_++;
                continue;
            }
            if (state_ == ReadingState.ATTR_VALUE) {
                if (curChar_ == LT_CHAR) {
                    break;
                }
                if (curChar_ == GT_CHAR) {
                    break;
                }
                if (curChar_ != delimiterAttr_) {
                    attributeValue_.append(curChar_);
                    i_++;
                    continue;
                }
                Attr attr_ = CoreDocument.createAttribute(attributeName_.toString());
                attr_.setEscapedValue(attributeValue_.toString());
                attrs_.add(attr_);
                attributeName_.delete(0, attributeName_.length());
                attributeValue_.delete(0, attributeValue_.length());
                int nextPrintable_ = i_ + 1;
                while (nextPrintable_ < len_) {
                    char next_ = _input.charAt(nextPrintable_);
                    if (!Character.isWhitespace(next_)) {
                        break;
                    }
                    nextPrintable_++;
                }
                if (nextPrintable_ == len_) {
                    break;
                }
                char nextPr_ = _input.charAt(nextPrintable_);
                boolean endHead_ = false;
                if (nextPr_ == SLASH) {
                    i_ = nextPrintable_ + 1;
                    if (i_ >= len_) {
                        break;
                    }
                    if (_input.charAt(i_) != GT_CHAR) {
                        break;
                    }
                    endHead_ = true;
                    addChild_ = false;
                }
                if (nextPr_ == GT_CHAR) {
                    i_ = nextPrintable_;
                    endHead_ = true;
                }
                if (endHead_) {
                    Element element_ = doc_.createElement(tagName_.toString());
                    element_.setAttributes(new NamedNodeMap(attrs_));
                    attrs_ = new CustList<Attr>();
                    if (doc_.getDocumentElement() == null) {
                        doc_.appendChild(element_);
                    } else {
                        currentElement_.appendChild(element_);
                    }
                    if (addChild_) {
                        currentElement_ = element_;
                        stack_.add(tagName_.append(GT_CHAR).toString());
                    } else {
                        if (stack_.isEmpty()) {
                            finished_ = true;
                            break;
                        }
                    }
                    tagName_.delete(CustList.FIRST_INDEX, tagName_.length());
                    if (i_ + 2 >= len_) {
                        break;
                    }
                    if (_input.charAt(i_ + 1) == LT_CHAR) {
                        if (_input.charAt(i_ + 2) == SLASH) {
                            i_++;
                            //_input.charAt(i_) == '<'
                            i_++;
                            //_input.charAt(i_) == '/'
                            i_++;
                            //_input.charAt(i_) is the first character of end tag
                            state_ = ReadingState.FOOTER;
                            indexFoot_ = i_;
                            continue;
                        }
                        state_ = ReadingState.HEADER;
                        addChild_ = true;
                        i_++;
                        i_++;
                        continue;
                    }
                    state_ = ReadingState.TEXT;
                    i_++;
                    continue;
                }
                state_ = ReadingState.ATTR_NAME;
                i_ = nextPrintable_;
                continue;
            }
            if (state_ == ReadingState.TEXT) {
                if (curChar_ == GT_CHAR) {
                    break;
                }
                if (curChar_ != LT_CHAR) {
//                    currentText_.append(curChar_);
                    i_++;
                    continue;
                }
//                Text attr_ = doc_.createEscapedTextNode(currentText_.toString());
//                currentText_.delete(0, currentText_.length());
//                currentElement_.appendChild(attr_);
                if (i_ + 1 >= len_) {
                    break;
                }
                if (_input.charAt(i_ + 1) == SLASH) {
                    i_++;
                    i_++;
                    indexFoot_ = i_;
                    state_ = ReadingState.FOOTER;
                    continue;
                }
                i_++;
                state_ = ReadingState.HEADER;
                addChild_ = true;
                continue;
            }
            int indexTag_ = i_ - indexFoot_;
            String lastTag_ = stack_.last();
            if (lastTag_.charAt(indexTag_) != _input.charAt(i_)) {
                break;
            }
            if (_input.charAt(i_) == GT_CHAR) {
                //end tag
                stack_.removeLast();
                Node parent_ = currentElement_.getParentNode();
                if (parent_ instanceof Element) {
                    currentElement_ = (Element) parent_;
                }
                if (stack_.isEmpty()) {
                    finished_ = true;
                    break;
                }
                if (i_ + 2 >= len_) {
                    break;
                }
                if (_input.charAt(i_ + 1) == LT_CHAR) {
                    if (_input.charAt(i_ + 2) == SLASH) {
                        i_++;
                        //_input.charAt(i_) == '<'
                        i_++;
                        //_input.charAt(i_) == '/'
                        i_++;
                        //_input.charAt(i_) is the first character of end tag
                        indexFoot_ = i_;
                        state_ = ReadingState.FOOTER;
                        continue;
                    }
                    i_++;
                    i_++;
                    state_ = ReadingState.HEADER;
                    addChild_ = true;
                    continue;
                }
                state_ = ReadingState.TEXT;
                i_++;
                continue;
            }
            i_++;
        }
        if (!finished_) {
            int max_;
            if (i_ < len_) {
                max_ = i_;
            } else {
                max_ = len_ - 1;
            }
            int row_ = 1;
            int col_ = 1;
            int j_ = CustList.FIRST_INDEX;
            while (j_ <= max_) {
                char curChar_ = _input.charAt(j_);
                if (curChar_ == LINE_RETURN) {
                    row_++;
                    col_ = 1;
                } else {
                    col_++;
                    if (curChar_ == TAB) {
                        col_ += doc_.getTabWidth() - 1;
                    }
                }
                j_++;
            }
            RowCol rc_ = new RowCol();
            rc_.setRow(row_);
            rc_.setCol(col_);
            res_.setLocation(rc_);
            return res_;
        }
        res_.setDocument(doc_);
        return res_;
    }
    public DocumentResult parse(String _input) {
        DocumentResult res_ = new DocumentResult();
        FullDocument doc_ = new FullDocument(getTabWidth());
        int len_ = _input.length();
        int indexFoot_ = 0;
        ReadingState state_ = ReadingState.HEADER;
        boolean addChild_ = true;
        char delimiterAttr_ = 0;
        StringBuilder attributeName_ = new StringBuilder();
        StringBuilder tagName_ = new StringBuilder();
        StringList stack_ = new StringList();
        StringBuilder currentText_ = new StringBuilder();
        Element currentElement_ = null;
        boolean finished_ = false;
//        StringBuilder currentComment_ = new StringBuilder();
        StringBuilder attributeValue_ = new StringBuilder();
        CustList<Attr> attrs_ = new CustList<Attr>();
        if (_input.isEmpty()) {
            res_.setLocation(new RowCol());
            return res_;
        }
        int i_ = CustList.FIRST_INDEX;
        if (_input.charAt(i_) != LT) {
            RowCol rc_ = new RowCol();
            rc_.setRow(1);
            rc_.setCol(1);
            res_.setLocation(rc_);
            return res_;
        }
        i_++;
        while (i_ < len_) {
            char curChar_ = _input.charAt(i_);
            if (state_ == ReadingState.HEADER) {
                if (curChar_ == LT_CHAR) {
                    break;
                }
                if (curChar_ == ENCODED) {
                    break;
                }
                if (tagName_.length() == 0) {
                    if (curChar_ == GT_CHAR) {
                        break;
                    }
                    if (curChar_ == SLASH) {
                        break;
                    }
                    if (Character.isWhitespace(curChar_)) {
                        break;
                    }
                    tagName_.append(curChar_);
                    i_++;
                    continue;
                }
                if (curChar_ == GT_CHAR) {
                    Element element_ = doc_.createElement(tagName_.toString());
                    element_.setAttributes(new NamedNodeMap(attrs_));
                    attrs_ = new CustList<Attr>();
                    if (doc_.getDocumentElement() == null) {
                        doc_.appendChild(element_);
                    } else {
                        currentElement_.appendChild(element_);
                    }
                    if (addChild_) {
                        currentElement_ = element_;
                        stack_.add(tagName_.append(GT_CHAR).toString());
                    } else {
                        if (stack_.isEmpty()) {
                            finished_ = true;
                            break;
                        }
                    }
                    tagName_.delete(CustList.FIRST_INDEX, tagName_.length());
                    if (i_ + 2 >= len_) {
                        break;
                    }
                    if (_input.charAt(i_ + 1) == LT_CHAR) {
                        if (_input.charAt(i_ + 2) == SLASH) {
                            i_++;
                            //_input.charAt(i_) == '<'
                            i_++;
                            //_input.charAt(i_) == '/'
                            i_++;
                            //_input.charAt(i_) is the first character of end tag
                            state_ = ReadingState.FOOTER;
                            indexFoot_ = i_;
                            continue;
                        }
                        i_++;
                        i_++;
                        state_ = ReadingState.HEADER;
                        addChild_ = true;
                        continue;
                    }
                    state_ = ReadingState.TEXT;
                    i_++;
                    continue;
                }
                if (Character.isWhitespace(curChar_)) {
                    int nextPrintable_ = i_;
                    while (nextPrintable_ < len_) {
                        char next_ = _input.charAt(nextPrintable_);
                        if (!Character.isWhitespace(next_)) {
                            break;
                        }
                        nextPrintable_++;
                    }
                    if (nextPrintable_ == len_) {
                        break;
                    }
                    state_ = ReadingState.ATTR_NAME;
                    i_ = nextPrintable_;
                    continue;
                }
                if (curChar_ != SLASH) {
                    tagName_.append(curChar_);
                    i_++;
                    continue;
                }
                if (i_ + 1 >= len_) {
                    break;
                }
                if (_input.charAt(i_ + 1) != GT_CHAR) {
                    break;
                }
                addChild_ = false;
                i_++;
                continue;
            }
            if (state_ == ReadingState.ATTR_NAME) {
                if (curChar_ == LT_CHAR) {
                    break;
                }
                if (curChar_ == ENCODED) {
                    break;
                }
                if (curChar_ == GT_CHAR) {
                    break;
                }
                if (curChar_ == SLASH) {
                    break;
                }
                if (!Character.isWhitespace(curChar_) && curChar_ != EQUALS) {
                    attributeName_.append(curChar_);
                    i_++;
                    continue;
                }
                if (curChar_ != EQUALS) {
                    //Character.isWhitespace(curChar_)
                    int nextPrintable_ = i_;
                    while (nextPrintable_ < len_) {
                        char next_ = _input.charAt(nextPrintable_);
                        if (!Character.isWhitespace(next_)) {
                            break;
                        }
                        nextPrintable_++;
                    }
                    if (nextPrintable_ == len_) {
                        break;
                    }
                    if (_input.charAt(nextPrintable_) != EQUALS) {
                        break;
                    }
                    i_ = nextPrintable_;
                }
                if (i_ + 1 >= len_) {
                    break;
                }
                char nextEq_ = _input.charAt(i_ + 1);
                if (nextEq_ != APOS_CHAR && nextEq_ != QUOT_CHAR) {
                    if (!Character.isWhitespace(nextEq_)) {
                        break;
                    }
                    int nextPrintable_ = i_ + 1;
                    while (nextPrintable_ < len_) {
                        char next_ = _input.charAt(nextPrintable_);
                        if (!Character.isWhitespace(next_)) {
                            break;
                        }
                        nextPrintable_++;
                    }
                    if (nextPrintable_ == len_) {
                        break;
                    }
                    char nextCharDel_ = _input.charAt(nextPrintable_);
                    if (nextCharDel_ != APOS_CHAR && nextCharDel_ != QUOT_CHAR) {
                        break;
                    }
                    i_ = nextPrintable_;
                } else {
                    i_++;
                }
                String foundName_ = attributeName_.toString();
                boolean ok_ = true;
                for (Attr a: attrs_) {
                    if (StringList.quickEq(a.getName(), foundName_)) {
                        ok_ = false;
                        break;
                    }
                }
                if (!ok_) {
                    break;
                }
                char del_ = _input.charAt(i_);
                delimiterAttr_ = del_;
                state_ = ReadingState.ATTR_VALUE;
                i_++;
                continue;
            }
            if (state_ == ReadingState.ATTR_VALUE) {
                if (curChar_ == LT_CHAR) {
                    break;
                }
                if (curChar_ == GT_CHAR) {
                    break;
                }
                if (curChar_ != delimiterAttr_) {
                    attributeValue_.append(curChar_);
                    i_++;
                    continue;
                }
                Attr attr_ = CoreDocument.createAttribute(attributeName_.toString());
                attr_.setEscapedValue(attributeValue_.toString());
                attrs_.add(attr_);
                attributeName_.delete(0, attributeName_.length());
                attributeValue_.delete(0, attributeValue_.length());
                int nextPrintable_ = i_ + 1;
                while (nextPrintable_ < len_) {
                    char next_ = _input.charAt(nextPrintable_);
                    if (!Character.isWhitespace(next_)) {
                        break;
                    }
                    nextPrintable_++;
                }
                if (nextPrintable_ == len_) {
                    break;
                }
                char nextPr_ = _input.charAt(nextPrintable_);
                boolean endHead_ = false;
                if (nextPr_ == SLASH) {
                    i_ = nextPrintable_ + 1;
                    if (i_ >= len_) {
                        break;
                    }
                    if (_input.charAt(i_) != GT_CHAR) {
                        break;
                    }
                    endHead_ = true;
                    addChild_ = false;
                }
                if (nextPr_ == GT_CHAR) {
                    i_ = nextPrintable_;
                    endHead_ = true;
                }
                if (endHead_) {
                    Element element_ = doc_.createElement(tagName_.toString());
                    element_.setAttributes(new NamedNodeMap(attrs_));
                    attrs_ = new CustList<Attr>();
                    if (doc_.getDocumentElement() == null) {
                        doc_.appendChild(element_);
                    } else {
                        currentElement_.appendChild(element_);
                    }
                    if (addChild_) {
                        currentElement_ = element_;
                        stack_.add(tagName_.append(GT_CHAR).toString());
                    } else {
                        if (stack_.isEmpty()) {
                            finished_ = true;
                            break;
                        }
                    }
                    tagName_.delete(CustList.FIRST_INDEX, tagName_.length());
                    if (i_ + 2 >= len_) {
                        break;
                    }
                    if (_input.charAt(i_ + 1) == LT_CHAR) {
                        if (_input.charAt(i_ + 2) == SLASH) {
                            i_++;
                            //_input.charAt(i_) == '<'
                            i_++;
                            //_input.charAt(i_) == '/'
                            i_++;
                            //_input.charAt(i_) is the first character of end tag
                            state_ = ReadingState.FOOTER;
                            indexFoot_ = i_;
                            continue;
                        }
                        state_ = ReadingState.HEADER;
                        addChild_ = true;
                        i_++;
                        i_++;
                        continue;
                    }
                    state_ = ReadingState.TEXT;
                    i_++;
                    continue;
                }
                state_ = ReadingState.ATTR_NAME;
                i_ = nextPrintable_;
                continue;
            }
            if (state_ == ReadingState.TEXT) {
                if (curChar_ == GT_CHAR) {
                    break;
                }
                if (curChar_ != LT_CHAR) {
                    currentText_.append(curChar_);
                    i_++;
                    continue;
                }
                Text attr_ = doc_.createEscapedTextNode(currentText_.toString());
                currentText_.delete(0, currentText_.length());
                currentElement_.appendChild(attr_);
                if (i_ + 1 >= len_) {
                    break;
                }
                if (_input.charAt(i_ + 1) == SLASH) {
                    i_++;
                    i_++;
                    indexFoot_ = i_;
                    state_ = ReadingState.FOOTER;
                    continue;
                }
                i_++;
                state_ = ReadingState.HEADER;
                addChild_ = true;
                continue;
            }
            int indexTag_ = i_ - indexFoot_;
            String lastTag_ = stack_.last();
            if (lastTag_.charAt(indexTag_) != _input.charAt(i_)) {
                break;
            }
            if (_input.charAt(i_) == GT_CHAR) {
                //end tag
                stack_.removeLast();
                Node parent_ = currentElement_.getParentNode();
                if (parent_ instanceof Element) {
                    currentElement_ = (Element) parent_;
                }
                if (stack_.isEmpty()) {
                    finished_ = true;
                    break;
                }
                if (i_ + 2 >= len_) {
                    break;
                }
                if (_input.charAt(i_ + 1) == LT_CHAR) {
                    if (_input.charAt(i_ + 2) == SLASH) {
                        i_++;
                        //_input.charAt(i_) == '<'
                        i_++;
                        //_input.charAt(i_) == '/'
                        i_++;
                        //_input.charAt(i_) is the first character of end tag
                        indexFoot_ = i_;
                        state_ = ReadingState.FOOTER;
                        continue;
                    }
                    i_++;
                    i_++;
                    state_ = ReadingState.HEADER;
                    addChild_ = true;
                    continue;
                }
                state_ = ReadingState.TEXT;
                i_++;
                continue;
            }
            i_++;
        }
        if (!finished_) {
            int max_;
            if (i_ < len_) {
                max_ = i_;
            } else {
                max_ = len_ - 1;
            }
            int row_ = 1;
            int col_ = 1;
            int j_ = CustList.FIRST_INDEX;
            while (j_ <= max_) {
                char curChar_ = _input.charAt(j_);
                if (curChar_ == LINE_RETURN) {
                    row_++;
                    col_ = 1;
                } else {
                    col_++;
                    if (curChar_ == TAB) {
                        col_ += doc_.getTabWidth() - 1;
                    }
                }
                j_++;
            }
            RowCol rc_ = new RowCol();
            rc_.setRow(row_);
            rc_.setCol(col_);
            res_.setLocation(rc_);
            return res_;
        }
        res_.setDocument(doc_);
        return res_;
    }

    public static String indent(String _xml) {
        int index_ = CustList.FIRST_INDEX;
        int indentation_ = CustList.SIZE_EMPTY;
        StringBuilder indented_ = new StringBuilder();
        while (true) {
            if (index_ >= _xml.length()) {
                break;
            }
            int i_ = index_;
            boolean change_ = true;
            if (_xml.charAt(i_) != LT) {
                change_ = false;
                while (_xml.charAt(i_) != LT) {
                    i_++;
                }
                i_--;
            } else {
                while (_xml.charAt(i_) != GT) {
                    i_++;
                }
            }
            boolean begin_ = false;
            boolean end_ = false;
            if (change_) {
                if (_xml.charAt(index_ + 1) == SLASH) {
                    end_ = true;
                } else if (_xml.charAt(i_ - 1) != SLASH) {
                    begin_ = true;
                }
            }
            if (end_) {
                indentation_--;
            }
            for (int i = CustList.FIRST_INDEX; i < indentation_; i++) {
                indented_.append(TAB);
            }
            indented_.append(_xml, index_, i_ + 1);
            indented_.append(LINE_RETURN);
            if (begin_) {
                indentation_++;
            }
            index_ = i_ + 1;
        }
        indented_.deleteCharAt(indented_.length() - 1);
        return indented_.toString();
    }

    public static String indentWithoutTextNode(String _xml) {
        int index_ = CustList.FIRST_INDEX;
        int indentation_ = CustList.SIZE_EMPTY;
        StringBuilder indented_ = new StringBuilder();
        while (true) {
            if (index_ >= _xml.length()) {
                break;
            }
            int i_ = index_;
            while (_xml.charAt(i_) != GT) {
                i_++;
            }
            boolean begin_ = false;
            boolean end_ = false;
            if (_xml.charAt(index_ + 1) == SLASH) {
                end_ = true;
            } else if (_xml.charAt(i_ - 1) != SLASH) {
                begin_ = true;
            }
            if (end_) {
                indentation_--;
            }
            for (int i = CustList.FIRST_INDEX; i < indentation_; i++) {
                indented_.append(TAB);
            }
            indented_.append(_xml, index_, i_ + 1);
            indented_.append(LINE_RETURN);
            if (begin_) {
                indentation_++;
            }
            index_ = i_ + 1;
        }
        indented_.deleteCharAt(indented_.length() - 1);
        return indented_.toString();
    }

    public static DocumentResult parseSaxHtmlRowCol(String _xml) {
        String enc_ = DocumentBuilder.encodeHtml(_xml);
        return parseSaxNotNullRowCol(enc_);
    }

    public static DocumentResult parseSaxNotNullRowCol(String _xml) {
        DocumentBuilder builder_ = newXmlDocumentBuilder();
        DocumentResult result_ = builder_.parse(_xml);
        return result_;
    }
    
    public static Document parseSax(String _xml) {
        if (_xml == null) {
            return null;
        }
        DocumentBuilder builder_ = newXmlDocumentBuilder();
        return builder_.parse(_xml).getDocument();
    }

    public static Document parseNoTextDocument(String _xml) {
        if (_xml == null) {
            return null;
        }
        DocumentBuilder builder_ = newXmlDocumentBuilder();
        return builder_.parseNoText(_xml).getDocument();
    }


    public static Document newXmlDocument() {
        DocumentBuilder builder_;
        builder_ = newXmlDocumentBuilder();
        return builder_.newDocument();
    }

    public static FullDocument newXmlDocument(int _tabWidth) {
        DocumentBuilder builder_;
        builder_ = newXmlDocumentBuilder(_tabWidth);
        return builder_.newDocument();
    }
    
    private static DocumentBuilder newXmlDocumentBuilder(int _tabWidth) {
        return DocumentBuilder.newDocumentBuilder(_tabWidth);
    }

    private static DocumentBuilder newXmlDocumentBuilder() {
        return DocumentBuilder.newDocumentBuilder();
    }

    public static RowCol getRowColOfNodeOrAttribute(String _xml, Node _node, int _offest,
            String _attribute, int _tabWidth, boolean _attrValue) {
        if (_node == null) {
            return new RowCol();
        }
        int index_ = getIndexOfNodeOrAttribute(_xml, _node, _attribute, _attrValue);
        return getRowColOfString(_xml, index_ + _offest, _tabWidth);
    }

    public static RowCol getRowColOfString(String _string, int _index, int _tabWidth) {
        int lastIndex_ = _string.lastIndexOf(LINE_RETURN, _index);
        int nbChars_ = 0;
        int nbLine_ = CustList.ONE_ELEMENT;
        for (int i = lastIndex_; i >= CustList.FIRST_INDEX; i--) {
            if (_string.charAt(i) == LINE_RETURN) {
                nbLine_++;
            }
        }
        for (int i = _index; i > lastIndex_; i--) {
            if (_string.charAt(i) == TAB) {
                nbChars_ += _tabWidth;
            } else {
                nbChars_++;
            }
        }
        RowCol rc_ = new RowCol();
        rc_.setRow(nbLine_);
        rc_.setCol(nbChars_);
        return rc_;
    }

    static int getIndexOfNodeOrAttribute(String _xml, Node _node, String _attribute) {
        return getIndexOfNodeOrAttribute(_xml, _node, _attribute, false);
    }

    public static
            ElementOffsetsNext getIndexesOfElementOrAttribute(
                    String _xml, ElementOffsetsNext _previous,
                    Element _node, int _tabWidth) {
        int next_ = CustList.INDEX_NOT_FOUND_ELT;
        StringMap<RowCol> m_ = new StringMap<RowCol>();
        StringMap<Numbers<Integer>> offsetsMap_;
        offsetsMap_ = new StringMap<Numbers<Integer>>();
        Numbers<Integer> offsets_ = new Numbers<Integer>();
        StringMap<Numbers<Integer>> tabsMap_;
        tabsMap_ = new StringMap<Numbers<Integer>>();
        Numbers<Integer> tabs_ = new Numbers<Integer>();
        int index_ = _previous.getNextElt();
        String nodeName_ = _node.getTagName();
        int found_ = _xml.indexOf(StringList.concat(String.valueOf(LT),nodeName_), index_);
        int nbLineReturns_ = 0;
        int minLine_ = _previous.getNextCol().getRow();
        int indexLoc_ = _previous.getNextCol().getCol();
        for (int i = index_; i <= found_; i++) {
            if (_xml.charAt(i) == LINE_RETURN) {
                nbLineReturns_++;
                indexLoc_ = 0;
            } else {
                indexLoc_++;
            }
        }
        RowCol rc_ = new RowCol();
        rc_.setRow(nbLineReturns_+minLine_+1);
        rc_.setCol(indexLoc_+1);
        m_.put(EMPTY_STRING, rc_);
        int len_ = _xml.length();
        int i_ = found_+1;
        int nodeLen_ = nodeName_.length();
        int k_ = 0;
        boolean addAttribute_ = true;
        Character delimiter_ = null;
        StringBuilder str_ = new StringBuilder();
        int j_ = indexLoc_;
        int offset_ = 0;
        RowCol endHeader_ = new RowCol();
        RowCol nextCol_ = new RowCol();
        while (i_ < len_) {
            char ch_ = _xml.charAt(i_);
            if (ch_ == GT) {
                if (addAttribute_) {
                    endHeader_.setRow(nbLineReturns_+minLine_+1);
                    endHeader_.setCol(j_+2);
                }
                addAttribute_ = false;
            }
            if (addAttribute_ && k_ > nodeLen_) {
                if (delimiter_ == null) {
                    if (ch_ == APOS) {
                        delimiter_ = ch_;
                    } else if (ch_ == QUOT) {
                        delimiter_ = ch_;
                    }
                    if (delimiter_ == null) {
                        if (!Character.isWhitespace(ch_) && ch_ != EQUALS) {
                            str_.append(ch_);
                        }
                    } else {
                        rc_ = new RowCol();
                        rc_.setRow(nbLineReturns_+minLine_+1);
                        rc_.setCol(j_+2);
                        offset_ = 0;
                    }
                } else {
                    if (ch_ == delimiter_) {
                        delimiter_ = null;
                        m_.put(str_.toString(), rc_);
                        offsetsMap_.put(str_.toString(), offsets_);
                        offsets_ = new Numbers<Integer>();
                        tabsMap_.put(str_.toString(), tabs_);
                        tabs_ = new Numbers<Integer>();
                        str_ = new StringBuilder();
                    } else if (ch_ == LINE_RETURN) {
                        offsets_.add(offset_);
                    } else if (ch_ == TAB) {
                        tabs_.add(offset_);
                    }
                    offset_++;
                }
            } else if (ch_ == LT) {
                if (_xml.charAt(i_ + 1) != SLASH) {
                    next_ = i_;
                    nextCol_.setRow(nbLineReturns_+minLine_);
                    nextCol_.setCol(j_);
                    break;
                }
            }
            k_++;
            if (ch_ == LINE_RETURN) {
                nbLineReturns_++;
                j_ = 0;
            } else {
                j_++;
                if (ch_ == TAB) {
                    j_+=_tabWidth-1;
                }
            }
            i_++;
        }
        return new ElementOffsetsNext(tabsMap_, offsetsMap_, m_, nextCol_, endHeader_, next_, found_ + 1);
    }
    public static RowCol getOffset(
            String _attribute,
            StringMap<RowCol> _attributes,
            StringMap<NatTreeMap<Integer,Integer>> _specialChars,
            int _offset,
            StringMap<Numbers<Integer>> _offsets,
            StringMap<Numbers<Integer>> _tabs,
            RowCol _endHeader,
            int _tabWidth) {
        if (!_attributes.contains(_attribute)) {
            return _endHeader;
        }
        RowCol offset_ = _attributes.getVal(_attribute);
        if (_attribute.isEmpty()) {
            return offset_;
        }
        int delta_ = 0;
        NatTreeMap<Integer, Integer> esc_ = _specialChars.getVal(_attribute);
        int nbIndexes_ = getIndexesCount(_offset, esc_);
        for (int i = 0; i < nbIndexes_; i++) {
            delta_ += esc_.getValue(i);
        }
        delta_ += _offset;
        Numbers<Integer> offsets_ = _offsets.getVal(_attribute);
        Numbers<Integer> tabs_ = _tabs.getVal(_attribute);
        RowCol ret_ = new RowCol();
        boolean exist_ = false;
        int index_ = CustList.FIRST_INDEX;
        if (!offsets_.isEmpty()) {
            int i_ = CustList.FIRST_INDEX;
            while (i_ < offsets_.size()) {
                if (offsets_.get(i_) > delta_) {
                    break;
                }
                i_++;
            }
            if (i_ > CustList.FIRST_INDEX) {
                exist_ = true;
                index_ = offsets_.get(i_ - 1);
            }
            ret_.setRow(offset_.getRow()+i_);
        } else {
            ret_.setRow(offset_.getRow());
        }
        int nb_ = 0;
        for (int i = index_; i < delta_; i++) {
            if (tabs_.containsObj(i)) {
                nb_ += _tabWidth;
            } else {
                nb_++;
            }
        }
        if (exist_) {
            ret_.setCol(nb_);
        } else {
            ret_.setCol(nb_+offset_.getCol());
        }
        return ret_;
    }
    private static int getIndexesCount(int _offset, NatTreeMap<Integer, Integer> _t) {
        int delta_ = 0;
        int count_ = 0;
        for (EntryCust<Integer, Integer> e: _t.entryList()) {
            if (e.getKey() - delta_ >= _offset) {
                return count_;
            }
            delta_ += e.getValue();
            count_++;
        }
        return count_;
    }
    static StringMap<NatTreeMap<Integer,Integer>> getSpecialChars(String _html, Element _element) {
        StringMap<NatTreeMap<Integer,Integer>> encoded_;
        encoded_ = new StringMap<NatTreeMap<Integer,Integer>>();
        int index_ = getIndexOfNodeOrAttribute(_html, _element, EMPTY_STRING);
        int endHeader_ = _html.indexOf(GT, index_);
        int beginHeader_ = index_ + _element.getTagName().length();
        StringMap<AttributePart> attr_;
        attr_ = getAttributes(_html, beginHeader_, endHeader_);
        for (EntryCust<String, AttributePart> e: attr_.entryList()) {
            encoded_.put(e.getKey(), getIndexesSpecChars(_html, e.getValue(), index_));
        }
        return encoded_;
    }
    private static NatTreeMap<Integer, Integer> getIndexesSpecChars(
            String _html, AttributePart _att, int _beginNode) {
        int begin_ = _att.getBegin();
        int end_ = _att.getEnd();
        int i_ = begin_;
        int delta_ = begin_ - _beginNode;
        NatTreeMap<Integer, Integer> indexes_;
        indexes_ = new NatTreeMap<Integer, Integer>();
        while (i_ < end_) {
            if (_html.charAt(i_) == ENCODED) {
                int beginEscaped_ = i_;
                i_++;
                while (_html.charAt(i_) != END_ESCAPED) {
                    i_++;
                }
                indexes_.put(beginEscaped_ - _beginNode - delta_, i_ - beginEscaped_);
            }
            i_++;
        }
        return indexes_;
    }
    public static StringMap<AttributePart> getAttributes(String _html, int _from, int _to) {
        StringMap<AttributePart> attributes_;
        attributes_ = new StringMap<AttributePart>();
        StringBuilder str_ = new StringBuilder();
        int beginToken_ = _from;
        Character delimiter_ = null;
        for (int i = _from; i < _to; i++) {
            char ch_ = _html.charAt(i);
            if (delimiter_ == null) {
                if (ch_ == APOS) {
                    delimiter_ = ch_;
                    beginToken_ = i + 1;
                } else if (ch_ == QUOT) {
                    delimiter_ = ch_;
                    beginToken_ = i + 1;
                }
            } else {
                if (ch_ == delimiter_) {
                    AttributePart attrPart_ = new AttributePart();
                    attrPart_.setBegin(beginToken_);
                    attrPart_.setEnd(i);
                    attributes_.put(str_.toString(), attrPart_);
                    str_ = new StringBuilder();
                    delimiter_ = null;
                    continue;
                }
            }
            if (delimiter_ == null) {
                if (Character.isWhitespace(ch_) || ch_ == EQUALS) {
                    continue;
                }
                str_.append(ch_);
            }
        }
        return attributes_;
    }

    public static int getIndexOfNodeOrAttribute(String _xml, Node _node, String _attribute, boolean _attrValue) {
        Document doc_ = _node.getOwnerDocument();
        Element root_ = doc_.getDocumentElement();
        CustList<Node> nodesBefore_ = getDeepChildNodesDocOrder(root_, _node);
        int nbSameNamedNodes_ = CustList.SIZE_EMPTY;
        if (_node instanceof Element) {
            String nodeName_ = ((Element) _node).getTagName();
            for (Node n: nodesBefore_) {
                if (!(n instanceof Element)) {
                    continue;
                }
                if (StringList.quickEq(((Element) n).getTagName(), nodeName_)) {
                    nbSameNamedNodes_++;
                }
            }
            int index_ = 0;
            int count_ = 0;
            int nb_ = nbSameNamedNodes_ + 1;
            int found_ = CustList.INDEX_NOT_FOUND_ELT;
            while (count_ < nb_) {
                found_ = _xml.indexOf(StringList.concat(String.valueOf(LT),nodeName_), index_) + 1;
                boolean isTag_ = true;
                int j_ = found_ + nodeName_.length();
                if (!Character.isWhitespace(_xml.charAt(j_))) {
                    if (_xml.charAt(j_) != GT) {
                        if (_xml.charAt(j_) != SLASH) {
                            isTag_ = false;
                        }
                    }
                }
                if (isTag_) {
                    count_++;
                }
                index_ = _xml.indexOf(LT, found_+ nodeName_.length());
            }
            if (_attribute.isEmpty()) {
                return found_;
            }
            int firstIndex_ = found_ + nodeName_.length();
            while (Character.isWhitespace(_xml.charAt(firstIndex_))) {
                firstIndex_++;
            }
            int lastIndex_ = _xml.indexOf(GT, firstIndex_);
            int beginToken_ = firstIndex_;
            StringBuilder str_ = new StringBuilder();
            Character delimiter_ = null;
            int foundAttr_ = CustList.INDEX_NOT_FOUND_ELT;
            for (int i = firstIndex_; i < lastIndex_; i++) {
                char ch_ = _xml.charAt(i);
                if (delimiter_ == null) {
                    if (ch_ == APOS) {
                        delimiter_ = ch_;
                    } else if (ch_ == QUOT) {
                        delimiter_ = ch_;
                    }
                } else {
                    if (ch_ == delimiter_) {
                        delimiter_ = null;
                        beginToken_ = i + 1;
                        while (Character.isWhitespace(_xml.charAt(beginToken_))) {
                            beginToken_++;
                        }
                        continue;
                    }
                }
                if (delimiter_ == null) {
                    if (Character.isWhitespace(ch_) || ch_ == EQUALS) {
                        if (StringList.quickEq(str_.toString(), _attribute)) {
                            foundAttr_ = beginToken_;
                            break;
                        }
                        if (ch_ == EQUALS) {
                            str_ = new StringBuilder();
                        }
                        continue;
                    }
                    str_.append(ch_);
                }
            }
            if (foundAttr_ == CustList.INDEX_NOT_FOUND_ELT) {
                return lastIndex_;
            }
            if (_attrValue) {
                foundAttr_ += _attribute.length();
                while (true) {
                    if (_xml.charAt(foundAttr_) == QUOT) {
                        break;
                    }
                    if (_xml.charAt(foundAttr_) == APOS) {
                        break;
                    }
                    foundAttr_++;
                }
                foundAttr_++;
            }
            return foundAttr_;
        }
        String searchedText_ = _node.getTextContent();
        for (Node n: nodesBefore_) {
            if (!(n instanceof Text)) {
                continue;
            }
            if (StringList.quickEq(n.getTextContent(), searchedText_)) {
                nbSameNamedNodes_++;
            }
        }
        StringBuilder arg_ = new StringBuilder();
        int i_ = _xml.indexOf(LT) + 1;
        int found_ = CustList.INDEX_NOT_FOUND_ELT;
        int count_ = 0;
        int nb_ = nbSameNamedNodes_ + 1;
        boolean inside_ = false;
        while (true) {
            if (count_ >= nb_) {
                break;
            }
            char cur_ = _xml.charAt(i_);
            if (cur_ == GT) {
                inside_ = true;
                i_++;
                found_ = i_;
            } else if (cur_ == LT) {
                inside_ = false;
                StringBuilder formatted_ = new StringBuilder();
                int j_ = 0;
                int lenArg_ = arg_.length();
                while (j_ < lenArg_) {
                    char curCharArg_ = arg_.charAt(j_);
                    if (curCharArg_ != ENCODED) {
                        formatted_.append(curCharArg_);
                    } else {
                        if (arg_.charAt(j_+1) == NUMBERED_CHAR) {
                            j_++;
                            j_++;
                            StringBuilder nbArg_ = new StringBuilder();
                            char charArg_ = arg_.charAt(j_);
                            while (charArg_ != END_ESCAPED) {
                                j_++;
                                nbArg_.append(charArg_);
                                charArg_ = arg_.charAt(j_);
                            }
                            int intArg_ = Numbers.parseInt(nbArg_.toString());
                            formatted_.append((char)intArg_);
                            j_++;
                            continue;
                        }
                        StringBuilder strArg_ = new StringBuilder();
                        char charArg_ = arg_.charAt(j_);
                        while (charArg_ != END_ESCAPED) {
                            j_++;
                            strArg_.append(charArg_);
                            charArg_ = arg_.charAt(j_);
                        }
                        String convered_ = DocumentBuilder.encodeHtml(strArg_.append(END_ESCAPED).toString());
                        convered_ = convered_.substring(CustList.SECOND_INDEX + 1, convered_.length() - 1);
                        int intArg_ = Numbers.parseInt(convered_);
                        formatted_.append((char)intArg_);
                        j_++;
                        continue;
                    }
                    j_++;
                }
                if (StringList.quickEq(formatted_.toString(), searchedText_)) {
                    count_++;
                }
                arg_ = new StringBuilder();
                i_++;
            } else if (inside_) {
                arg_.append(cur_);
                i_++;
            } else {
                i_++;
            }
        }
        return found_;
    }

    public static CustList<Node> getDeepChildNodesDocOrder(Node _root, Node _until) {
        CustList<Node> nodes_ = new CustList<Node>();
        if (_root == null) {
            return nodes_;
        }
        Node en_ = _root;
        while (true) {
            if (en_ == _until) {
                break;
            }
            nodes_.add(en_);
            Node n_ = en_.getFirstChild();
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            if (en_ == _root) {
                break;
            }
            boolean stop_ = false;
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                n_ = en_.getParentNode();
                if (n_ == _root) {
                    stop_ = true;
                    break;
                }
                en_ = n_;
            }
            if (stop_) {
                break;
            }
        }
        return nodes_;
    }

    public static Numbers<Integer> getIndexes(Node _node) {
        Node par_ = _node.getParentNode();
        Numbers<Integer> indexes_ = new Numbers<Integer>();
        if (par_ == null) {
            return indexes_;
        }
        Document doc_ = _node.getOwnerDocument();
        Element root_ = doc_.getDocumentElement();
        Node currentParent_ = par_;
        Node current_ = _node;
        while (current_ != root_) {
            int index_ = CustList.FIRST_INDEX;
            for (Node c : currentParent_.getChildNodes()) {
                if (c == current_) {
                    indexes_.add(CustList.FIRST_INDEX, index_);
                }
                index_++;
            }
            current_ = currentParent_;
            currentParent_ = currentParent_.getParentNode();
        }
        return indexes_;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        if (_tabWidth < 0) {
            tabWidth = DEFAULT_TAB;
        } else {
            tabWidth = _tabWidth;
        }
    }
}
