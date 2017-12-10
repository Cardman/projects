package code.xml.components;

import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.xml.RowCol;

public final class DocumentBuilder {

    private static final char BEGIN_COMMENT = '!';
    private static final String END_COMMENT = "-->";
    private static final String NEXT_COMMENT = "--";

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

//        private static final int NB_INDENT = 4;

    private static final char END_ESCAPED = ';';

    private static final char EQUALS = '=';

    private boolean ignoreComments;

    private boolean indentWhileWriting;

    private int tabWidth = 4;

    private DocumentBuilder(){
    }

    public static DocumentBuilder newDocumentBuilder() {
        return new DocumentBuilder();
    }

    public static String transformSpecialChars(String _htmlText) {
        return transformSpecialChars(_htmlText, true, true, false);
    }

    public static String transformSpecialChars(String _htmlText, boolean _affectEamp, boolean _affectLtGt, boolean _error) {
        StringMap<String> map_ = new StringMap<String>();
        map_.put(E_NBSP, StringList.simpleFormat(ENCODE, (int)NBSP));
        map_.put(E_IEXCL, StringList.simpleFormat(ENCODE, (int)IEXCL));
        map_.put(E_CENT, StringList.simpleFormat(ENCODE, (int)CENT));
        map_.put(E_POUND, StringList.simpleFormat(ENCODE, (int)POUND));
        map_.put(E_CURREN, StringList.simpleFormat(ENCODE, (int)CURREN));
        map_.put(E_YEN, StringList.simpleFormat(ENCODE, (int)YEN));
        map_.put(E_BRVBAR, StringList.simpleFormat(ENCODE, (int)BRVBAR));
        map_.put(E_SECT, StringList.simpleFormat(ENCODE, (int)SECT));
        map_.put(E_UML, StringList.simpleFormat(ENCODE, (int)UML));
        map_.put(E_COPY, StringList.simpleFormat(ENCODE, (int)COPY));
        map_.put(E_ORDF, StringList.simpleFormat(ENCODE, (int)ORDF));
        map_.put(E_LAQUO, StringList.simpleFormat(ENCODE, (int)LAQUO));
        map_.put(E_NOT, StringList.simpleFormat(ENCODE, (int)NOT));
        map_.put(E_SHY, StringList.simpleFormat(ENCODE, (int)SHY));
        map_.put(E_REG, StringList.simpleFormat(ENCODE, (int)REG));
        map_.put(E_MACR, StringList.simpleFormat(ENCODE, (int)MACR));
        map_.put(E_DEG, StringList.simpleFormat(ENCODE, (int)DEG));
        map_.put(E_PLUSMN, StringList.simpleFormat(ENCODE, (int)PLUSMN));
        map_.put(E_SUP2, StringList.simpleFormat(ENCODE, (int)SUP2));
        map_.put(E_SUP3, StringList.simpleFormat(ENCODE, (int)SUP3));
        map_.put(E_ACUTE, StringList.simpleFormat(ENCODE, (int)ACUTE));
        map_.put(E_MICRO, StringList.simpleFormat(ENCODE, (int)MICRO));
        map_.put(E_PARA, StringList.simpleFormat(ENCODE, (int)PARA));
        map_.put(E_MIDDOT, StringList.simpleFormat(ENCODE, (int)MIDDOT));
        map_.put(E_CEDIL, StringList.simpleFormat(ENCODE, (int)CEDIL));
        map_.put(E_SUP1, StringList.simpleFormat(ENCODE, (int)SUP1));
        map_.put(E_ORDM, StringList.simpleFormat(ENCODE, (int)ORDM));
        map_.put(E_RAQUO, StringList.simpleFormat(ENCODE, (int)RAQUO));
        map_.put(E_FRAC14, StringList.simpleFormat(ENCODE, (int)FRAC14));
        map_.put(E_FRAC12, StringList.simpleFormat(ENCODE, (int)FRAC12));
        map_.put(E_FRAC34, StringList.simpleFormat(ENCODE, (int)FRAC34));
        map_.put(E_IQUEST, StringList.simpleFormat(ENCODE, (int)IQUEST));
        map_.put(E_U_AGRAVE, StringList.simpleFormat(ENCODE, (int)U_A_GRAVE));
        map_.put(E_U_AACUTE, StringList.simpleFormat(ENCODE, (int)U_A_ACUTE));
        map_.put(E_U_ACIRC, StringList.simpleFormat(ENCODE, (int)U_A_CIRC));
        map_.put(E_U_ATILDE, StringList.simpleFormat(ENCODE, (int)U_A_TILDE));
        map_.put(E_U_AUML, StringList.simpleFormat(ENCODE, (int)U_A_UML));
        map_.put(E_U_ARING, StringList.simpleFormat(ENCODE, (int)U_A_RING));
        map_.put(E_U_AELIG, StringList.simpleFormat(ENCODE, (int)U_AE_LIG));
        map_.put(E_U_CCEDIL, StringList.simpleFormat(ENCODE, (int)U_C_CEDIL));
        map_.put(E_U_EGRAVE, StringList.simpleFormat(ENCODE, (int)U_E_GRAVE));
        map_.put(E_U_EACUTE, StringList.simpleFormat(ENCODE, (int)U_E_ACUTE));
        map_.put(E_U_ECIRC, StringList.simpleFormat(ENCODE, (int)U_E_CIRC));
        map_.put(E_U_EUML, StringList.simpleFormat(ENCODE, (int)U_E_UML));
        map_.put(E_U_IGRAVE, StringList.simpleFormat(ENCODE, (int)U_I_GRAVE));
        map_.put(E_U_IACUTE, StringList.simpleFormat(ENCODE, (int)U_I_ACUTE));
        map_.put(E_U_ICIRC, StringList.simpleFormat(ENCODE, (int)U_I_CIRC));
        map_.put(E_U_IUML, StringList.simpleFormat(ENCODE, (int)U_I_UML));
        map_.put(E_U_ETH, StringList.simpleFormat(ENCODE, (int)U_ETH));
        map_.put(E_U_NTILDE, StringList.simpleFormat(ENCODE, (int)U_N_TILDE));
        map_.put(E_U_OGRAVE, StringList.simpleFormat(ENCODE, (int)U_O_GRAVE));
        map_.put(E_U_OACUTE, StringList.simpleFormat(ENCODE, (int)U_O_ACUTE));
        map_.put(E_U_OCIRC, StringList.simpleFormat(ENCODE, (int)U_O_CIRC));
        map_.put(E_U_OTILDE, StringList.simpleFormat(ENCODE, (int)U_O_TILDE));
        map_.put(E_U_OUML, StringList.simpleFormat(ENCODE, (int)U_O_UML));
        map_.put(E_TIMES, StringList.simpleFormat(ENCODE, (int)TIMES));
        map_.put(E_U_OSLASH, StringList.simpleFormat(ENCODE, (int)U_O_SLASH));
        map_.put(E_U_UGRAVE, StringList.simpleFormat(ENCODE, (int)U_U_GRAVE));
        map_.put(E_U_UACUTE, StringList.simpleFormat(ENCODE, (int)U_U_ACUTE));
        map_.put(E_U_UCIRC, StringList.simpleFormat(ENCODE, (int)U_U_CIRC));
        map_.put(E_U_UUML, StringList.simpleFormat(ENCODE, (int)U_U_UML));
        map_.put(E_U_YACUTE, StringList.simpleFormat(ENCODE, (int)U_Y_ACUTE));
        map_.put(E_U_THORN, StringList.simpleFormat(ENCODE, (int)U_THORN));
        map_.put(E_SZLIG, StringList.simpleFormat(ENCODE, (int)SZLIG));
        map_.put(E_AGRAVE, StringList.simpleFormat(ENCODE, (int)AGRAVE));
        map_.put(E_AACUTE, StringList.simpleFormat(ENCODE, (int)AACUTE));
        map_.put(E_ACIRC, StringList.simpleFormat(ENCODE, (int)ACIRC));
        map_.put(E_ATILDE, StringList.simpleFormat(ENCODE, (int)ATILDE));
        map_.put(E_AUML, StringList.simpleFormat(ENCODE, (int)AUML));
        map_.put(E_ARING, StringList.simpleFormat(ENCODE, (int)ARING));
        map_.put(E_AELIG, StringList.simpleFormat(ENCODE, (int)AELIG));
        map_.put(E_CCEDIL, StringList.simpleFormat(ENCODE, (int)CCEDIL));
        map_.put(E_EGRAVE, StringList.simpleFormat(ENCODE, (int)EGRAVE));
        map_.put(E_EACUTE, StringList.simpleFormat(ENCODE, (int)EACUTE));
        map_.put(E_ECIRC, StringList.simpleFormat(ENCODE, (int)ECIRC));
        map_.put(E_EUML, StringList.simpleFormat(ENCODE, (int)EUML));
        map_.put(E_IGRAVE, StringList.simpleFormat(ENCODE, (int)IGRAVE));
        map_.put(E_IACUTE, StringList.simpleFormat(ENCODE, (int)IACUTE));
        map_.put(E_ICIRC, StringList.simpleFormat(ENCODE, (int)ICIRC));
        map_.put(E_IUML, StringList.simpleFormat(ENCODE, (int)IUML));
        map_.put(E_ETH, StringList.simpleFormat(ENCODE, (int)ETH));
        map_.put(E_NTILDE, StringList.simpleFormat(ENCODE, (int)NTILDE));
        map_.put(E_OGRAVE, StringList.simpleFormat(ENCODE, (int)OGRAVE));
        map_.put(E_OACUTE, StringList.simpleFormat(ENCODE, (int)OACUTE));
        map_.put(E_OCIRC, StringList.simpleFormat(ENCODE, (int)OCIRC));
        map_.put(E_OTILDE, StringList.simpleFormat(ENCODE, (int)OTILDE));
        map_.put(E_OUML, StringList.simpleFormat(ENCODE, (int)OUML));
        map_.put(E_DIVIDE, StringList.simpleFormat(ENCODE, (int)DIVIDE));
        map_.put(E_OSLASH, StringList.simpleFormat(ENCODE, (int)OSLASH));
        map_.put(E_UGRAVE, StringList.simpleFormat(ENCODE, (int)UGRAVE));
        map_.put(E_UACUTE, StringList.simpleFormat(ENCODE, (int)UACUTE));
        map_.put(E_UCIRC, StringList.simpleFormat(ENCODE, (int)UCIRC));
        map_.put(E_UUML, StringList.simpleFormat(ENCODE, (int)UUML));
        map_.put(E_YACUTE, StringList.simpleFormat(ENCODE, (int)YACUTE));
        map_.put(E_THORN, StringList.simpleFormat(ENCODE, (int)THORN));
        map_.put(E_YUML, StringList.simpleFormat(ENCODE, (int)YUML));
        map_.put(E_QUOT, StringList.simpleFormat(ENCODE, (int)QUOT));
        if (_affectLtGt) {
            map_.put(E_LT, StringList.simpleFormat(ENCODE, (int)LT));
            map_.put(E_GT, StringList.simpleFormat(ENCODE, (int)GT));
        }
        map_.put(E_APOS, StringList.simpleFormat(ENCODE, (int)APOS));
        map_.put(E_U_OELIG, StringList.simpleFormat(ENCODE, (int)U_OE_LIG));
        map_.put(E_OELIG, StringList.simpleFormat(ENCODE, (int)OELIG));
        map_.put(E_S_CARON, StringList.simpleFormat(ENCODE, (int)U_SCARON));
        map_.put(E_SCARON, StringList.simpleFormat(ENCODE, (int)SCARON));
        map_.put(E_U_YUML, StringList.simpleFormat(ENCODE, (int)U_Y_UML));
        map_.put(E_CIRC, StringList.simpleFormat(ENCODE, (int)CIRC));
        map_.put(E_TILDE, StringList.simpleFormat(ENCODE, (int)TILDE));
        map_.put(E_ENSP, StringList.simpleFormat(ENCODE, (int)ENSP));
        map_.put(E_EMSP, StringList.simpleFormat(ENCODE, (int)EMSP));
        map_.put(E_THINSP, StringList.simpleFormat(ENCODE, (int)THINSP));
        map_.put(E_ZWNJ, StringList.simpleFormat(ENCODE, (int)ZWNJ));
        map_.put(E_ZWJ, StringList.simpleFormat(ENCODE, (int)ZWJ));
        map_.put(E_LRM, StringList.simpleFormat(ENCODE, (int)LRM));
        map_.put(E_RLM, StringList.simpleFormat(ENCODE, (int)RLM));
        map_.put(E_NDASH, StringList.simpleFormat(ENCODE, (int)NDASH));
        map_.put(E_MDASH, StringList.simpleFormat(ENCODE, (int)MDASH));
        map_.put(E_LSQUO, StringList.simpleFormat(ENCODE, (int)LSQUO));
        map_.put(E_RSQUO, StringList.simpleFormat(ENCODE, (int)RSQUO));
        map_.put(E_SBQUO, StringList.simpleFormat(ENCODE, (int)SBQUO));
        map_.put(E_LDQUO, StringList.simpleFormat(ENCODE, (int)LDQUO));
        map_.put(E_RDQUO, StringList.simpleFormat(ENCODE, (int)RDQUO));
        map_.put(E_BDQUO, StringList.simpleFormat(ENCODE, (int)BDQUO));
        map_.put(E_DAGGER, StringList.simpleFormat(ENCODE, (int)D_AGGER));
        map_.put(E_U_DAGGER, StringList.simpleFormat(ENCODE, (int)DAGGER));
        map_.put(E_PERMIL, StringList.simpleFormat(ENCODE, (int)PERMIL));
        map_.put(E_LSAQUO, StringList.simpleFormat(ENCODE, (int)LSAQUO));
        map_.put(E_RSAQUO, StringList.simpleFormat(ENCODE, (int)RSAQUO));
        map_.put(E_EURO, StringList.simpleFormat(ENCODE, (int)EURO));
        map_.put(E_FNOF, StringList.simpleFormat(ENCODE, (int)FNOF));
        map_.put(E_U_ALPHA, StringList.simpleFormat(ENCODE, (int)U_A_LPHA));
        map_.put(E_U_BETA, StringList.simpleFormat(ENCODE, (int)U_B_ETA));
        map_.put(E_U_GAMMA, StringList.simpleFormat(ENCODE, (int)U_G_AMMA));
        map_.put(E_U_DELTA, StringList.simpleFormat(ENCODE, (int)U_D_ELTA));
        map_.put(E_U_EPSILON, StringList.simpleFormat(ENCODE, (int)U_E_PSILON));
        map_.put(E_U_ZETA, StringList.simpleFormat(ENCODE, (int)U_Z_ETA));
        map_.put(E_U_ETA, StringList.simpleFormat(ENCODE, (int)U_E_TA));
        map_.put(E_U_THETA, StringList.simpleFormat(ENCODE, (int)U_T_HETA));
        map_.put(E_U_IOTA, StringList.simpleFormat(ENCODE, (int)U_I_OTA));
        map_.put(E_U_KAPPA, StringList.simpleFormat(ENCODE, (int)U_K_APPA));
        map_.put(E_U_LAMBDA, StringList.simpleFormat(ENCODE, (int)U_L_AMBDA));
        map_.put(E_U_MU, StringList.simpleFormat(ENCODE, (int)U_M_U));
        map_.put(E_U_NU, StringList.simpleFormat(ENCODE, (int)U_N_U));
        map_.put(E_U_XI, StringList.simpleFormat(ENCODE, (int)U_X_I));
        map_.put(E_U_OMICRON, StringList.simpleFormat(ENCODE, (int)U_O_MICRON));
        map_.put(E_U_PI, StringList.simpleFormat(ENCODE, (int)U_P_I));
        map_.put(E_U_RHO, StringList.simpleFormat(ENCODE, (int)U_R_HO));
        map_.put(E_U_SIGMA, StringList.simpleFormat(ENCODE, (int)U_S_IGMA));
        map_.put(E_U_TAU, StringList.simpleFormat(ENCODE, (int)U_T_AU));
        map_.put(E_U_UPSILON, StringList.simpleFormat(ENCODE, (int)U_U_PSILON));
        map_.put(E_U_PHI, StringList.simpleFormat(ENCODE, (int)U_P_HI));
        map_.put(E_U_CHI, StringList.simpleFormat(ENCODE, (int)U_C_HI));
        map_.put(E_U_PSI, StringList.simpleFormat(ENCODE, (int)U_P_SI));
        map_.put(E_U_OMEGA, StringList.simpleFormat(ENCODE, (int)U_O_MEGA));
        map_.put(E_ALPHA, StringList.simpleFormat(ENCODE, (int)ALPHA));
        map_.put(E_BETA, StringList.simpleFormat(ENCODE, (int)BETA));
        map_.put(E_GAMMA, StringList.simpleFormat(ENCODE, (int)GAMMA));
        map_.put(E_DELTA, StringList.simpleFormat(ENCODE, (int)DELTA));
        map_.put(E_EPSILON, StringList.simpleFormat(ENCODE, (int)EPSILON));
        map_.put(E_ZETA, StringList.simpleFormat(ENCODE, (int)ZETA));
        map_.put(E_ETA, StringList.simpleFormat(ENCODE, (int)ETA));
        map_.put(E_THETA, StringList.simpleFormat(ENCODE, (int)THETA));
        map_.put(E_IOTA, StringList.simpleFormat(ENCODE, (int)IOTA));
        map_.put(E_KAPPA, StringList.simpleFormat(ENCODE, (int)KAPPA));
        map_.put(E_LAMBDA, StringList.simpleFormat(ENCODE, (int)LAMBDA));
        map_.put(E_MU, StringList.simpleFormat(ENCODE, (int)MU));
        map_.put(E_NU, StringList.simpleFormat(ENCODE, (int)NU));
        map_.put(E_XI, StringList.simpleFormat(ENCODE, (int)XI));
        map_.put(E_OMICRON, StringList.simpleFormat(ENCODE, (int)OMICRON));
        map_.put(E_PI, StringList.simpleFormat(ENCODE, (int)PI));
        map_.put(E_RHO, StringList.simpleFormat(ENCODE, (int)RHO));
        map_.put(E_SIGMAF, StringList.simpleFormat(ENCODE, (int)SIGMAF));
        map_.put(E_SIGMA, StringList.simpleFormat(ENCODE, (int)SIGMA));
        map_.put(E_TAU, StringList.simpleFormat(ENCODE, (int)TAU));
        map_.put(E_UPSILON, StringList.simpleFormat(ENCODE, (int)UPSILON));
        map_.put(E_PHI, StringList.simpleFormat(ENCODE, (int)PHI));
        map_.put(E_CHI, StringList.simpleFormat(ENCODE, (int)CHI));
        map_.put(E_PSI, StringList.simpleFormat(ENCODE, (int)PSI));
        map_.put(E_OMEGA, StringList.simpleFormat(ENCODE, (int)OMEGA));
        map_.put(E_THETASYM, StringList.simpleFormat(ENCODE, (int)THETASYM));
        map_.put(E_UPSIH, StringList.simpleFormat(ENCODE, (int)UPSIH));
        map_.put(E_PIV, StringList.simpleFormat(ENCODE, (int)PIV));
        map_.put(E_BULL, StringList.simpleFormat(ENCODE, (int)BULL));
        map_.put(E_HELLIP, StringList.simpleFormat(ENCODE, (int)HELLIP));
        map_.put(E_PRIME, StringList.simpleFormat(ENCODE, (int)PRIME));
        map_.put(E_P_RIME, StringList.simpleFormat(ENCODE, (int)U_PRIME));
        map_.put(E_OLINE, StringList.simpleFormat(ENCODE, (int)OLINE));
        map_.put(E_FRASL, StringList.simpleFormat(ENCODE, (int)FRASL));
        map_.put(E_WEIERP, StringList.simpleFormat(ENCODE, (int)WEIERP));
        map_.put(E_IMAGE, StringList.simpleFormat(ENCODE, (int)IMAGE));
        map_.put(E_REAL, StringList.simpleFormat(ENCODE, (int)REAL));
        map_.put(E_TRADE, StringList.simpleFormat(ENCODE, (int)TRADE));
        map_.put(E_ALEFSYM, StringList.simpleFormat(ENCODE, (int)ALEFSYM));
        map_.put(E_LARR, StringList.simpleFormat(ENCODE, (int)LARR));
        map_.put(E_UARR, StringList.simpleFormat(ENCODE, (int)UARR));
        map_.put(E_RARR, StringList.simpleFormat(ENCODE, (int)RARR));
        map_.put(E_DARR, StringList.simpleFormat(ENCODE, (int)DARR));
        map_.put(E_HARR, StringList.simpleFormat(ENCODE, (int)HARR));
        map_.put(E_CRARR, StringList.simpleFormat(ENCODE, (int)CRARR));
        map_.put(E_L_ARR, StringList.simpleFormat(ENCODE, (int)U_LARR));
        map_.put(E_U_ARR, StringList.simpleFormat(ENCODE, (int)U_UARR));
        map_.put(E_R_ARR, StringList.simpleFormat(ENCODE, (int)U_RARR));
        map_.put(E_D_ARR, StringList.simpleFormat(ENCODE, (int)U_DARR));
        map_.put(E_H_ARR, StringList.simpleFormat(ENCODE, (int)U_HARR));
        map_.put(E_FORALL, StringList.simpleFormat(ENCODE, (int)FORALL));
        map_.put(E_PART, StringList.simpleFormat(ENCODE, (int)PART));
        map_.put(E_EXIST, StringList.simpleFormat(ENCODE, (int)EXIST));
        map_.put(E_EMPTY, StringList.simpleFormat(ENCODE, (int)EMPTY));
        map_.put(E_NABLA, StringList.simpleFormat(ENCODE, (int)NABLA));
        map_.put(E_ISIN, StringList.simpleFormat(ENCODE, (int)ISIN));
        map_.put(E_NOTIN, StringList.simpleFormat(ENCODE, (int)NOTIN));
        map_.put(E_NI, StringList.simpleFormat(ENCODE, (int)NI));
        map_.put(E_PROD, StringList.simpleFormat(ENCODE, (int)PROD));
        map_.put(E_SUM, StringList.simpleFormat(ENCODE, (int)SUM));
        map_.put(E_MINUS, StringList.simpleFormat(ENCODE, (int)MINUS));
        map_.put(E_LOWAST, StringList.simpleFormat(ENCODE, (int)LOWAST));
        map_.put(E_RADIC, StringList.simpleFormat(ENCODE, (int)RADIC));
        map_.put(E_PROP, StringList.simpleFormat(ENCODE, (int)PROP));
        map_.put(E_INFIN, StringList.simpleFormat(ENCODE, (int)INFIN));
        map_.put(E_ANG, StringList.simpleFormat(ENCODE, (int)ANG));
        map_.put(E_AND, StringList.simpleFormat(ENCODE, (int)AND));
        map_.put(E_OR, StringList.simpleFormat(ENCODE, (int)OR));
        map_.put(E_CAP, StringList.simpleFormat(ENCODE, (int)CAP));
        map_.put(E_CUP, StringList.simpleFormat(ENCODE, (int)CUP));
        map_.put(E_INT, StringList.simpleFormat(ENCODE, (int)INT));
        map_.put(E_THERE4, StringList.simpleFormat(ENCODE, (int)THERE4));
        map_.put(E_SIM, StringList.simpleFormat(ENCODE, (int)SIM));
        map_.put(E_CONG, StringList.simpleFormat(ENCODE, (int)CONG));
        map_.put(E_ASYMP, StringList.simpleFormat(ENCODE, (int)ASYMP));
        map_.put(E_NE, StringList.simpleFormat(ENCODE, (int)NE));
        map_.put(E_EQUIV, StringList.simpleFormat(ENCODE, (int)EQUIV));
        map_.put(E_LE, StringList.simpleFormat(ENCODE, (int)LE));
        map_.put(E_GE, StringList.simpleFormat(ENCODE, (int)GE));
        map_.put(E_SUB, StringList.simpleFormat(ENCODE, (int)SUB));
        map_.put(E_SUP, StringList.simpleFormat(ENCODE, (int)SUP));
        map_.put(E_NSUB, StringList.simpleFormat(ENCODE, (int)NSUB));
        map_.put(E_SUBE, StringList.simpleFormat(ENCODE, (int)SUBE));
        map_.put(E_SUPE, StringList.simpleFormat(ENCODE, (int)SUPE));
        map_.put(E_OPLUS, StringList.simpleFormat(ENCODE, (int)OPLUS));
        map_.put(E_OTIMES, StringList.simpleFormat(ENCODE, (int)OTIMES));
        map_.put(E_PERP, StringList.simpleFormat(ENCODE, (int)PERP));
        map_.put(E_SDOT, StringList.simpleFormat(ENCODE, (int)SDOT));
        map_.put(E_LCEIL, StringList.simpleFormat(ENCODE, (int)LCEIL));
        map_.put(E_RCEIL, StringList.simpleFormat(ENCODE, (int)RCEIL));
        map_.put(E_LFLOOR, StringList.simpleFormat(ENCODE, (int)LFLOOR));
        map_.put(E_RFLOOR, StringList.simpleFormat(ENCODE, (int)RFLOOR));
        map_.put(E_LANG, StringList.simpleFormat(ENCODE, (int)LANG));
        map_.put(E_RANG, StringList.simpleFormat(ENCODE, (int)RANG));
        map_.put(E_LOZ, StringList.simpleFormat(ENCODE, (int)LOZ));
        map_.put(E_SPADES, StringList.simpleFormat(ENCODE, (int)SPADES));
        map_.put(E_CLUBS, StringList.simpleFormat(ENCODE, (int)CLUBS));
        map_.put(E_HEARTS, StringList.simpleFormat(ENCODE, (int)HEARTS));
        map_.put(E_DIAMS, StringList.simpleFormat(ENCODE, (int)DIAMS));
        if (_affectEamp) {
            map_.put(E_AMP, StringList.simpleFormat(ENCODE, (int)ASCII_38));
        }
        int length_ = _htmlText.length();
        StringBuilder str_ = new StringBuilder();
        int i_ = 0;
        int iBegin_ = 0;
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
                    int ascii_ = Integer.parseInt(strValue_);
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
                    int ascii_;
                    try {
                        ascii_ = Integer.parseInt(strValue_);
                    } catch (NumberFormatException _0) {
                        if (_error) {
                            return null;
                        }
                        str_.append(strValue_);
                        continue;
                    }
                    char char_ = (char) ascii_;
                    str_.append(char_);
                    i_++;
                    continue;
                }
                str_.append(_htmlText.substring(iBegin_, i_ + 1));
                i_++;
            }
        }
        return str_.toString();
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
        DocumentResult res_ = new DocumentResult();
        int len_ = _expected.length();
        int indexFoot_ = 0;
        ReadingState state_ = ReadingState.HEADER;
        boolean addChild_ = true;
        char delimiterAttr_ = 0;
        CustList<Object> infos_ = new CustList<Object>();
        StringBuilder attributeName_ = new StringBuilder();
        StringBuilder tagName_ = new StringBuilder();
        StringList stack_ = new StringList();
        int row_ = 1;
        int col_ = 0;
        int tabWidth_ = 4;
        StringBuilder currentText_ = new StringBuilder();
        boolean finished_ = false;
        StringBuilder attributeValue_ = new StringBuilder();
        CustList<Attr> attrs_ = new CustList<Attr>();
        if (_expected.isEmpty()) {
            res_.setLocation(new RowCol());
            return false;
        }
        if (_found.isEmpty()) {
            res_.setLocation(new RowCol());
            return false;
        }
        int i_ = CustList.FIRST_INDEX;
        col_ = 1;
        if (_expected.charAt(i_) != LT) {
            RowCol rc_ = new RowCol();
            rc_.setRow(row_);
            rc_.setCol(col_);
            res_.setLocation(rc_);
            return false;
        }
        if (_found.charAt(i_) != LT) {
            RowCol rc_ = new RowCol();
            rc_.setRow(row_);
            rc_.setCol(col_);
            res_.setLocation(rc_);
            return false;
        }
        i_++;
        while (i_ < len_) {
            char curChar_ = _expected.charAt(i_);
            if (curChar_ == LINE_RETURN) {
                row_ ++;
                col_ = 1;
            } else {
                col_++;
                if (curChar_ == TAB) {
                    col_ += tabWidth_ - 1;
                }
            }
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
                    infos_.add(ReadingState.HEADER);
                    infos_.add(tagName_.toString());
                    infos_.add(new NamedNodeMap(attrs_));
                    attrs_ = new CustList<Attr>();
                    if (addChild_) {
                        stack_.add(tagName_.toString()+GT_CHAR);
                    } else {
                        if (stack_.isEmpty()) {
                            finished_ = true;
                            break;
                        }
                        infos_.add(EMPTY_STRING);
                    }
                    tagName_.delete(CustList.FIRST_INDEX, tagName_.length());
                    if (i_ + 2 >= len_) {
                        break;
                    }
                    if (_expected.charAt(i_ + 1) == LT_CHAR) {
                        if (_expected.charAt(i_ + 2) == SLASH) {
                            i_++;
                            //_expected.charAt(i_) == '<'
                            i_++;
                            //_expected.charAt(i_) == '/'
                            i_++;
                            //_expected.charAt(i_) is the first character of end tag
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
                        char next_ = _expected.charAt(nextPrintable_);
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
                if (_expected.charAt(i_ + 1) != GT_CHAR) {
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
                        char next_ = _expected.charAt(nextPrintable_);
                        if (!Character.isWhitespace(next_)) {
                            break;
                        }
                        nextPrintable_++;
                    }
                    if (nextPrintable_ == len_) {
                        break;
                    }
                    if (_expected.charAt(nextPrintable_) != EQUALS) {
                        break;
                    }
                    i_ = nextPrintable_;
                }
                if (i_ + 1 >= len_) {
                    break;
                }
                char nextEq_ = _expected.charAt(i_ + 1);
                if (nextEq_ != APOS_CHAR && nextEq_ != QUOT_CHAR) {
                    if (!Character.isWhitespace(nextEq_)) {
                        break;
                    }
                    int nextPrintable_ = i_ + 1;
                    while (nextPrintable_ < len_) {
                        char next_ = _expected.charAt(nextPrintable_);
                        if (!Character.isWhitespace(next_)) {
                            break;
                        }
                        nextPrintable_++;
                    }
                    if (nextPrintable_ == len_) {
                        break;
                    }
                    char nextCharDel_ = _expected.charAt(nextPrintable_);
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
                char del_ = _expected.charAt(i_);
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
                Attr attr_ = new Attr();
                attr_.setName(attributeName_.toString());
                attr_.setEscapedValue(attributeValue_.toString());
                attrs_.add(attr_);
                attributeName_.delete(0, attributeName_.length());
                attributeValue_.delete(0, attributeValue_.length());
                int nextPrintable_ = i_ + 1;
                while (nextPrintable_ < len_) {
                    char next_ = _expected.charAt(nextPrintable_);
                    if (!Character.isWhitespace(next_)) {
                        break;
                    }
                    nextPrintable_++;
                }
                if (nextPrintable_ == len_) {
                    break;
                }
                char nextPr_ = _expected.charAt(nextPrintable_);
                boolean endHead_ = false;
                if (nextPr_ == SLASH) {
                    i_ = nextPrintable_ + 1;
                    if (i_ >= len_) {
                        break;
                    }
                    if (_expected.charAt(i_) != GT_CHAR) {
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
                    infos_.add(ReadingState.HEADER);
                    infos_.add(tagName_.toString());
                    infos_.add(new NamedNodeMap(attrs_));
                    attrs_ = new CustList<Attr>();
                    if (addChild_) {
                        stack_.add(tagName_.toString()+GT_CHAR);
                    } else {
                        if (stack_.isEmpty()) {
                            finished_ = true;
                            break;
                        }
                        infos_.add(EMPTY_STRING);
                    }
                    tagName_.delete(CustList.FIRST_INDEX, tagName_.length());
                    if (i_ + 2 >= len_) {
                        break;
                    }
                    if (_expected.charAt(i_ + 1) == LT_CHAR) {
                        if (_expected.charAt(i_ + 2) == SLASH) {
                            i_++;
                            //_expected.charAt(i_) == '<'
                            i_++;
                            //_expected.charAt(i_) == '/'
                            i_++;
                            //_expected.charAt(i_) is the first character of end tag
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
                infos_.add(ReadingState.TEXT);
                infos_.add(DocumentBuilder.transformSpecialChars(currentText_.toString()));
                currentText_.delete(0, currentText_.length());
                if (i_ + 1 >= len_) {
                    break;
                }
                if (_expected.charAt(i_ + 1) == SLASH) {
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
            if (state_ == ReadingState.FOOTER) {
                int indexTag_ = i_ - indexFoot_;
                String lastTag_ = stack_.last();
                if (lastTag_.charAt(indexTag_) != _expected.charAt(i_)) {
                    break;
                }
                if (_expected.charAt(i_) == GT_CHAR) {
                    //end tag
                    stack_.removeLast();
                    infos_.add(ReadingState.FOOTER);
                    infos_.add(lastTag_);
                    if (stack_.isEmpty()) {
                        finished_ = true;
                        break;
                    }
                    if (i_ + 2 >= len_) {
                        break;
                    }
                    if (_expected.charAt(i_ + 1) == LT_CHAR) {
                        if (_expected.charAt(i_ + 2) == SLASH) {
                            i_++;
                            //_expected.charAt(i_) == '<'
                            i_++;
                            //_expected.charAt(i_) == '/'
                            i_++;
                            //_expected.charAt(i_) is the first character of end tag
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
                continue;
            }
        }
        if (!finished_) {
            RowCol rc_ = new RowCol();
            rc_.setRow(row_);
            rc_.setCol(col_);
            res_.setLocation(rc_);
            return false;
        }
        row_ = 1;
        col_ = 1;
        len_ = _found.length();
        i_ = 1;
        finished_ = false;
        indexFoot_ = 0;
        state_ = ReadingState.HEADER;
        addChild_ = true;
        delimiterAttr_ = 0;
        attributeName_ = new StringBuilder();
        tagName_ = new StringBuilder();
        stack_ = new StringList();
        currentText_ = new StringBuilder();
        attributeValue_ = new StringBuilder();
        attrs_ = new CustList<Attr>();
        int deep_ = 0;
        //TODO found doc
        int indexInfo_ = CustList.SECOND_INDEX;
        while (i_ < len_) {
            char curChar_ = _found.charAt(i_);
            if (curChar_ == LINE_RETURN) {
                row_ ++;
                col_ = 1;
            } else {
                col_++;
                if (curChar_ == TAB) {
                    col_ += tabWidth_ - 1;
                }
            }
            if (state_ == ReadingState.HEADER) {
                String info_ = (String) infos_.get(indexInfo_);
                if (!_found.substring(i_).startsWith(info_)) {
                    break;
                }
                int endIndex_ = i_ + info_.length();
                if (_found.charAt(endIndex_) != GT_CHAR) {
                    if (_found.charAt(endIndex_) != SLASH) {
                        if (!Character.isWhitespace(_found.charAt(endIndex_))) {
                            break;
                        }
                    }
                }
                NamedNodeMap expAttr_ = (NamedNodeMap) infos_.get(indexInfo_+1);
                if (expAttr_.isEmpty()) {
                    Object possibleLeaf_ = null;
                    if (indexInfo_ + 2 < infos_.size()) {
                        possibleLeaf_ = infos_.get(indexInfo_ + 2);
                    }
                    if (possibleLeaf_ instanceof String && ((String)possibleLeaf_).isEmpty()) {
                        if (_found.charAt(endIndex_) != SLASH) {
                            break;
                        }
                        if (endIndex_ + 1 >= len_) {
                            break;
                        }
                        if (_found.charAt(endIndex_ + 1) != GT_CHAR) {
                            break;
                        }
                        endIndex_++;
                        if (deep_ == 0) {
                            break;
                        }
                        indexInfo_++;
                    } else {
                        if (_found.charAt(endIndex_) != GT_CHAR) {
                            break;
                        }
                        deep_++;
                    }
                    indexInfo_++;
                    indexInfo_++;
                    i_ = endIndex_;
                    if (i_ + 2 >= len_) {
                        break;
                    }
                    Object possibleEnd_ = infos_.get(indexInfo_);
                    indexInfo_++;
                    if (_found.charAt(i_ + 1) == LT_CHAR) {
                        if (_found.charAt(i_ + 2) == SLASH) {
                            if (possibleEnd_ != ReadingState.FOOTER) {
                                break;
                            }
                            i_++;
                            //_found.charAt(i_) == '<'
                            i_++;
                            //_found.charAt(i_) == '/'
                            i_++;
                            //_found.charAt(i_) is the first character of end tag
                            state_ = ReadingState.FOOTER;
                            continue;
                        }
                        if (possibleEnd_ != ReadingState.HEADER) {
                            break;
                        }
                        i_++;
                        i_++;
                        state_ = ReadingState.HEADER;
                        continue;
                    }
                    if (possibleEnd_ != ReadingState.TEXT) {
                        break;
                    }
                    state_ = ReadingState.TEXT;
                    i_++;
                    continue;
                }
                if (!Character.isWhitespace(_found.charAt(endIndex_))) {
                    break;
                }
                attrs_ = new CustList<Attr>();
                int nextPrintable_ = endIndex_;
                while (nextPrintable_ < len_) {
                    char next_ = _found.charAt(nextPrintable_);
                    if (!Character.isWhitespace(next_)) {
                        break;
                    }
                    nextPrintable_++;
                }
                if (nextPrintable_ == len_) {
                    break;
                }
                i_ = nextPrintable_;
                boolean ok_ = false;
                while (i_ < len_) {
                    curChar_ =_found.charAt(i_);
                    boolean okAttrName_ = false;
                    while (i_ < len_) {
                        curChar_ = _found.charAt(i_);
                        if (Character.isWhitespace(curChar_) || curChar_ == EQUALS) {
                            okAttrName_ = true;
                            break;
                        }
                        if (curChar_ == ENCODED) {
                            break;
                        }
                        if (curChar_ == SLASH) {
                            break;
                        }
                        if (curChar_ == LT_CHAR) {
                            break;
                        }
                        if (curChar_ == GT_CHAR) {
                            break;
                        }
                        attributeName_.append(curChar_);
                        i_++;
                        continue;
                    }
                    if (!okAttrName_) {
                        break;
                    }
                    if (curChar_ != EQUALS) {
                        //Character.isWhitespace(curChar_)
                        nextPrintable_ = i_;
                        while (nextPrintable_ < len_) {
                            char next_ = _found.charAt(nextPrintable_);
                            if (!Character.isWhitespace(next_)) {
                                break;
                            }
                            nextPrintable_++;
                        }
                        if (nextPrintable_ == len_) {
                            break;
                        }
                        if (_found.charAt(nextPrintable_) != EQUALS) {
                            break;
                        }
                        i_ = nextPrintable_;
                    }
                    if (i_ + 1 >= len_) {
                        break;
                    }
                    char nextEq_ = _found.charAt(i_ + 1);
                    if (nextEq_ != APOS_CHAR && nextEq_ != QUOT_CHAR) {
                        if (!Character.isWhitespace(nextEq_)) {
                            break;
                        }
                        nextPrintable_ = i_ + 1;
                        while (nextPrintable_ < len_) {
                            char next_ = _found.charAt(nextPrintable_);
                            if (!Character.isWhitespace(next_)) {
                                break;
                            }
                            nextPrintable_++;
                        }
                        if (nextPrintable_ == len_) {
                            break;
                        }
                        char nextCharDel_ = _found.charAt(nextPrintable_);
                        if (nextCharDel_ != APOS_CHAR && nextCharDel_ != QUOT_CHAR) {
                            break;
                        }
                        i_ = nextPrintable_;
                    } else {
                        i_++;
                    }
                    String foundName_ = attributeName_.toString();
                    boolean okName_ = true;
                    for (Attr a: attrs_) {
                        if (StringList.quickEq(a.getName(), foundName_)) {
                            okName_ = false;
                            break;
                        }
                    }
                    if (!okName_) {
                        break;
                    }
                    char del_ = _found.charAt(i_);
                    delimiterAttr_ = del_;
                    i_++;
                    curChar_ = _found.charAt(i_);
                    if (curChar_ == LT_CHAR) {
                        break;
                    }
                    if (curChar_ == GT_CHAR) {
                        break;
                    }
                    boolean okAttrValue_ = false;
                    while (i_ < len_) {
                        curChar_ = _found.charAt(i_);
                        if (curChar_ == delimiterAttr_) {
                            okAttrValue_ = true;
                            break;
                        }
                        if (curChar_ == LT_CHAR) {
                            break;
                        }
                        if (curChar_ == GT_CHAR) {
                            break;
                        }
                        attributeValue_.append(curChar_);
                        i_++;
                    }
                    if (!okAttrValue_) {
                        break;
                    }
                    Attr attr_ = new Attr();
                    attr_.setName(attributeName_.toString());
                    attr_.setEscapedValue(attributeValue_.toString());
                    attrs_.add(attr_);
                    attributeName_.delete(0, attributeName_.length());
                    attributeValue_.delete(0, attributeValue_.length());
                    nextPrintable_ = i_ + 1;
                    while (nextPrintable_ < len_) {
                        char next_ = _found.charAt(nextPrintable_);
                        if (!Character.isWhitespace(next_)) {
                            break;
                        }
                        nextPrintable_++;
                    }
                    if (nextPrintable_ == len_) {
                        break;
                    }
                    char nextPr_ = _found.charAt(nextPrintable_);
                    boolean endHead_ = false;
                    if (nextPr_ == SLASH) {
                        i_ = nextPrintable_ + 1;
                        if (i_ >= len_) {
                            break;
                        }
                        if (_found.charAt(i_) != GT_CHAR) {
                            break;
                        }
                        i_ = nextPrintable_;
                        endHead_ = true;
                    }
                    if (nextPr_ == GT_CHAR) {
                        i_ = nextPrintable_;
                        endHead_ = true;
                    }
                    if (endHead_) {
                        ok_ = true;
                        break;
                    }
                    i_ = nextPrintable_;
                }
                if (!ok_) {
                    break;
                }
                StringList expList_ = new StringList();
                StringList foundList_ = new StringList();
                for (Attr a: expAttr_) {
                    expList_.add(a.getName());
                }
                for (Attr a: attrs_) {
                    foundList_.add(a.getName());
                }
                if (!StringList.equalsSet(expList_, foundList_)) {
                    return false;
                }
                for (Attr a: attrs_) {
                    for (Attr b: expAttr_) {
                        if (!StringList.quickEq(a.getName(), b.getName())) {
                            continue;
                        }
                        if (!StringList.quickEq(a.getValue(), b.getValue())) {
                            return false;
                        }
                    }
                }
                Object possibleLeaf_ = null;
                if (indexInfo_ + 2 < infos_.size()) {
                    possibleLeaf_ = infos_.get(indexInfo_ + 2);
                }
                if (possibleLeaf_ instanceof String && ((String)possibleLeaf_).isEmpty()) {
                    if (_found.charAt(i_) != SLASH) {
                        break;
                    }
                    if (i_ + 1 >= len_) {
                        break;
                    }
                    i_++;
                    if (_found.charAt(i_) != GT_CHAR) {
                        break;
                    }
                    if (deep_ == 0) {
                        break;
                    }
                    indexInfo_++;
                } else {
                    if (_found.charAt(i_) != GT_CHAR) {
                        break;
                    }
                    deep_++;
                }
                indexInfo_++;
                indexInfo_++;
                if (i_ + 2 >= len_) {
                    break;
                }
                Object possibleEnd_ = infos_.get(indexInfo_);
                indexInfo_++;
                if (_found.charAt(i_ + 1) == LT_CHAR) {
                    if (_found.charAt(i_ + 2) == SLASH) {
                        if (possibleEnd_ != ReadingState.FOOTER) {
                            break;
                        }
                        i_++;
                        //_found.charAt(i_) == '<'
                        i_++;
                        //_found.charAt(i_) == '/'
                        i_++;
                        //_found.charAt(i_) is the first character of end tag
                        state_ = ReadingState.FOOTER;
                        continue;
                    }
                    if (possibleEnd_ != ReadingState.HEADER) {
                        break;
                    }
                    i_++;
                    i_++;
                    state_ = ReadingState.HEADER;
                    continue;
                }
                if (possibleEnd_ != ReadingState.TEXT) {
                    break;
                }
                state_ = ReadingState.TEXT;
                i_++;
                continue;
            }
            if (state_ == ReadingState.TEXT) {
                String info_ = (String) infos_.get(indexInfo_);
                int endText_ = _found.indexOf(LT_CHAR, i_);
                if (endText_ < CustList.INDEX_NOT_FOUND_ELT) {
                    break;
                }
                String text_ = DocumentBuilder.transformSpecialChars(_found.substring(i_, endText_));
                text_ = StringList.removeStrings(text_, "\r");
                if (!StringList.quickEq(text_, info_)) {
                    break;
                }
                int endIndex_ = endText_;
                if (_found.charAt(endIndex_) != LT_CHAR) {
                    break;
                }
                i_ = endIndex_;
                if (i_ + 1 >= len_) {
                    break;
                }
                indexInfo_++;
                if (_found.charAt(i_ + 1) == SLASH) {
                    if (infos_.get(indexInfo_) != ReadingState.FOOTER) {
                        break;
                    }
                    indexInfo_++;
                    i_++;
                    i_++;
                    state_ = ReadingState.FOOTER;
                    continue;
                }
                if (infos_.get(indexInfo_) != ReadingState.HEADER) {
                    break;
                }
                indexInfo_++;
                i_++;
                state_ = ReadingState.HEADER;
                continue;
            }
            if (state_ == ReadingState.FOOTER) {
                String info_ = (String) infos_.get(indexInfo_);
                if (!_found.substring(i_).startsWith(info_)) {
                    break;
                }
                i_ += info_.length() - 1;
                //end tag
                indexInfo_++;
                deep_--;
                if (deep_ == 0) {
                    break;
                }
                if (i_ + 2 >= len_) {
                    break;
                }
                if (_found.charAt(i_ + 1) == LT_CHAR) {
                    if (_found.charAt(i_ + 2) == SLASH) {
                        if (infos_.get(indexInfo_) != ReadingState.FOOTER) {
                            break;
                        }
                        indexInfo_++;
                        i_++;
                        //_found.charAt(i_) == '<'
                        i_++;
                        //_found.charAt(i_) == '/'
                        i_++;
                        //_found.charAt(i_) is the first character of end tag
                        state_ = ReadingState.FOOTER;
                        continue;
                    }
                    if (infos_.get(indexInfo_) != ReadingState.HEADER) {
                        break;
                    }
                    indexInfo_++;
                    i_++;
                    i_++;
                    state_ = ReadingState.HEADER;
                    continue;
                }
                if (infos_.get(indexInfo_) != ReadingState.TEXT) {
                    break;
                }
                indexInfo_++;
                state_ = ReadingState.TEXT;
                i_++;
                continue;
            }
        }
        if (deep_ == 0) {
            return true;
        }
        return false;
    }
    public Document newDocument() {
        return new Document(getTabWidth());
    }
    public DocumentResult parse(String _input) {
        DocumentResult res_ = new DocumentResult();
        Document doc_ = new Document(getTabWidth());
        int len_ = _input.length();
        int indexFoot_ = 0;
        ReadingState state_ = ReadingState.HEADER;
//        boolean inHead_ = false;
//        boolean inFoot_ = false;
//        boolean inComment_ = false;
//        boolean inAttibuteName_ = false;
//        boolean inAttibuteValue_ = false;
//        boolean inText_ = false;
        boolean addChild_ = true;
        char delimiterAttr_ = 0;
        StringBuilder attributeName_ = new StringBuilder();
        StringBuilder tagName_ = new StringBuilder();
        StringList stack_ = new StringList();
        int row_ = 1;
        int col_ = 0;
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
        col_ = 1;
        if (_input.charAt(i_) != LT) {
            RowCol rc_ = new RowCol();
            rc_.setRow(row_);
            rc_.setCol(col_);
            res_.setLocation(rc_);
            return res_;
        }
        i_++;
        while (i_ < len_) {
            char curChar_ = _input.charAt(i_);
            if (curChar_ == LINE_RETURN) {
                row_ ++;
                col_ = 1;
            } else {
                col_++;
                if (curChar_ == TAB) {
                    col_ += doc_.getTabWidth() - 1;
                }
            }
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
                        stack_.add(tagName_.toString()+GT_CHAR);
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
                Attr attr_ = doc_.createAttribute(attributeName_.toString());
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
                        stack_.add(tagName_.toString()+GT_CHAR);
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
            if (state_ == ReadingState.FOOTER) {
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
                continue;
            }
        }
        if (!finished_) {
            RowCol rc_ = new RowCol();
            rc_.setRow(row_);
            rc_.setCol(col_);
            res_.setLocation(rc_);
            return res_;
        }
        res_.setDocument(doc_);
        return res_;
    }

    public boolean isIgnoreComments() {
        return ignoreComments;
    }

    public void setIgnoreComments(boolean _ignoreComments) {
        ignoreComments = _ignoreComments;
    }

    public boolean isIndentWhileWriting() {
        return indentWhileWriting;
    }

    public void setIndentWhileWriting(boolean _indentWhileWriting) {
        indentWhileWriting = _indentWhileWriting;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        if (_tabWidth < 0) {
            tabWidth = 4;
        } else {
            tabWidth = _tabWidth;
        }
    }
}
