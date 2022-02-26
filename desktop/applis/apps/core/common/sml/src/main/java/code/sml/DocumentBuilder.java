package code.sml;

import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

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

    private static final char ENCODED = '&';

    private static final char NUMBERED_CHAR = '#';

    private static final char END_ESCAPED = ';';

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
        for (int i = IndexConstants.FIRST_INDEX; i < lengthAll_; i++) {
            Node n_ = all_.item(i);
            if (StringUtil.quickEq(((Element) n_).getAttribute(_attr),_value)) {
                return (Element) n_;
            }
        }
        return null;
    }

    public static Element getElementById(Document _doc, String _attr, String _secAttr, String _id) {
        NodeList all_ = _doc.getElementsByTagName();
        int lengthAll_ = all_.getLength();
        for (int i = IndexConstants.FIRST_INDEX; i < lengthAll_; i++) {
            Node n_ = all_.item(i);
            if (StringUtil.quickEq(((Element) n_).getAttribute(_attr), _id) || StringUtil.quickEq(((Element) n_).getAttribute(_secAttr), _id)) {
                return (Element) n_;
            }
        }
        return null;
    }
    
    public static DocumentBuilder newDocumentBuilder() {
        return new DocumentBuilder();
    }

    public static DocumentBuilder newDocumentBuilder(int _tabWidth) {
        return new DocumentBuilder(_tabWidth);
    }

    static String transformSpecialCharsLtGt(String _htmlText) {
        return transformSpecialChars(_htmlText, true, true);
    }

    public static String transformSpecialChars(String _htmlText) {
        return transformSpecialChars(_htmlText, true, false);
    }

    public static String transformSpecialChars(String _htmlText, boolean _escapeAmp) {
        return transformSpecialChars(_htmlText, _escapeAmp, false);
    }

    public static String transformSpecialChars(String _htmlText, boolean _affectEamp, boolean _affectLtGt) {
        CustList<EncodedChar> map_ = possibleEncodes(_affectEamp, _affectLtGt);
        int length_ = _htmlText.length();
        StringBuilder str_ = new StringBuilder();
        int i_ = 0;
        AbstractEncodingText incr_ = new TryIncrEncodingText(i_,str_);
        return AbstractEncodingText.encodeCommon(_htmlText, map_, length_, incr_);
    }

    public static CustList<EncodedChar> possibleEncodes() {
        return possibleEncodes(true,true);
    }
    public static CustList<EncodedChar> possibleEncodes(boolean _affectEamp, boolean _affectLtGt) {
        CustList<EncodedChar> map_ = new CustList<EncodedChar>();
        format(map_);
        addLtGt(_affectLtGt, map_);
        addEamp(_affectEamp, map_);
        return map_;
    }

    private static void format(CustList<EncodedChar> _map) {
        format1(_map);
        format2(_map);
    }
    private static void format2(CustList<EncodedChar> _map) {
        _map.add(new EncodedChar(E_APOS,APOS));
        _map.add(new EncodedChar(E_U_OELIG,U_OE_LIG));
        _map.add(new EncodedChar(E_OELIG,OELIG));
        _map.add(new EncodedChar(E_S_CARON,U_SCARON));
        _map.add(new EncodedChar(E_SCARON,SCARON));
        _map.add(new EncodedChar(E_U_YUML,U_Y_UML));
        _map.add(new EncodedChar(E_CIRC,CIRC));
        _map.add(new EncodedChar(E_TILDE,TILDE));
        _map.add(new EncodedChar(E_ENSP,ENSP));
        _map.add(new EncodedChar(E_EMSP,EMSP));
        _map.add(new EncodedChar(E_THINSP,THINSP));
        _map.add(new EncodedChar(E_ZWNJ,ZWNJ));
        _map.add(new EncodedChar(E_ZWJ,ZWJ));
        _map.add(new EncodedChar(E_LRM,LRM));
        _map.add(new EncodedChar(E_RLM,RLM));
        _map.add(new EncodedChar(E_NDASH,NDASH));
        _map.add(new EncodedChar(E_MDASH,MDASH));
        _map.add(new EncodedChar(E_LSQUO,LSQUO));
        _map.add(new EncodedChar(E_RSQUO,RSQUO));
        _map.add(new EncodedChar(E_SBQUO,SBQUO));
        _map.add(new EncodedChar(E_LDQUO,LDQUO));
        _map.add(new EncodedChar(E_RDQUO,RDQUO));
        _map.add(new EncodedChar(E_BDQUO,BDQUO));
        _map.add(new EncodedChar(E_DAGGER,D_AGGER));
        _map.add(new EncodedChar(E_U_DAGGER,DAGGER));
        _map.add(new EncodedChar(E_PERMIL,PERMIL));
        _map.add(new EncodedChar(E_LSAQUO,LSAQUO));
        _map.add(new EncodedChar(E_RSAQUO,RSAQUO));
        _map.add(new EncodedChar(E_EURO,EURO));
        _map.add(new EncodedChar(E_FNOF,FNOF));
        _map.add(new EncodedChar(E_U_ALPHA,U_A_LPHA));
        _map.add(new EncodedChar(E_U_BETA,U_B_ETA));
        _map.add(new EncodedChar(E_U_GAMMA,U_G_AMMA));
        _map.add(new EncodedChar(E_U_DELTA,U_D_ELTA));
        _map.add(new EncodedChar(E_U_EPSILON,U_E_PSILON));
        _map.add(new EncodedChar(E_U_ZETA,U_Z_ETA));
        _map.add(new EncodedChar(E_U_ETA,U_E_TA));
        _map.add(new EncodedChar(E_U_THETA,U_T_HETA));
        _map.add(new EncodedChar(E_U_IOTA,U_I_OTA));
        _map.add(new EncodedChar(E_U_KAPPA,U_K_APPA));
        _map.add(new EncodedChar(E_U_LAMBDA,U_L_AMBDA));
        _map.add(new EncodedChar(E_U_MU,U_M_U));
        _map.add(new EncodedChar(E_U_NU,U_N_U));
        _map.add(new EncodedChar(E_U_XI,U_X_I));
        _map.add(new EncodedChar(E_U_OMICRON,U_O_MICRON));
        _map.add(new EncodedChar(E_U_PI,U_P_I));
        _map.add(new EncodedChar(E_U_RHO,U_R_HO));
        _map.add(new EncodedChar(E_U_SIGMA,U_S_IGMA));
        _map.add(new EncodedChar(E_U_TAU,U_T_AU));
        _map.add(new EncodedChar(E_U_UPSILON,U_U_PSILON));
        _map.add(new EncodedChar(E_U_PHI,U_P_HI));
        _map.add(new EncodedChar(E_U_CHI,U_C_HI));
        _map.add(new EncodedChar(E_U_PSI,U_P_SI));
        _map.add(new EncodedChar(E_U_OMEGA,U_O_MEGA));
        _map.add(new EncodedChar(E_ALPHA,ALPHA));
        _map.add(new EncodedChar(E_BETA,BETA));
        _map.add(new EncodedChar(E_GAMMA,GAMMA));
        _map.add(new EncodedChar(E_DELTA,DELTA));
        _map.add(new EncodedChar(E_EPSILON,EPSILON));
        _map.add(new EncodedChar(E_ZETA,ZETA));
        _map.add(new EncodedChar(E_ETA,ETA));
        _map.add(new EncodedChar(E_THETA,THETA));
        _map.add(new EncodedChar(E_IOTA,IOTA));
        _map.add(new EncodedChar(E_KAPPA,KAPPA));
        _map.add(new EncodedChar(E_LAMBDA,LAMBDA));
        _map.add(new EncodedChar(E_MU,MU));
        _map.add(new EncodedChar(E_NU,NU));
        _map.add(new EncodedChar(E_XI,XI));
        _map.add(new EncodedChar(E_OMICRON,OMICRON));
        _map.add(new EncodedChar(E_PI,PI));
        _map.add(new EncodedChar(E_RHO,RHO));
        _map.add(new EncodedChar(E_SIGMAF,SIGMAF));
        _map.add(new EncodedChar(E_SIGMA,SIGMA));
        _map.add(new EncodedChar(E_TAU,TAU));
        _map.add(new EncodedChar(E_UPSILON,UPSILON));
        _map.add(new EncodedChar(E_PHI,PHI));
        _map.add(new EncodedChar(E_CHI,CHI));
        _map.add(new EncodedChar(E_PSI,PSI));
        _map.add(new EncodedChar(E_OMEGA,OMEGA));
        _map.add(new EncodedChar(E_THETASYM,THETASYM));
        _map.add(new EncodedChar(E_UPSIH,UPSIH));
        _map.add(new EncodedChar(E_PIV,PIV));
        _map.add(new EncodedChar(E_BULL,BULL));
        _map.add(new EncodedChar(E_HELLIP,HELLIP));
        _map.add(new EncodedChar(E_PRIME,PRIME));
        _map.add(new EncodedChar(E_P_RIME,U_PRIME));
        _map.add(new EncodedChar(E_OLINE,OLINE));
        _map.add(new EncodedChar(E_FRASL,FRASL));
        _map.add(new EncodedChar(E_WEIERP,WEIERP));
        _map.add(new EncodedChar(E_IMAGE,IMAGE));
        _map.add(new EncodedChar(E_REAL,REAL));
        _map.add(new EncodedChar(E_TRADE,TRADE));
        _map.add(new EncodedChar(E_ALEFSYM,ALEFSYM));
        _map.add(new EncodedChar(E_LARR,LARR));
        _map.add(new EncodedChar(E_UARR,UARR));
        _map.add(new EncodedChar(E_RARR,RARR));
        _map.add(new EncodedChar(E_DARR,DARR));
        _map.add(new EncodedChar(E_HARR,HARR));
        _map.add(new EncodedChar(E_CRARR,CRARR));
        _map.add(new EncodedChar(E_L_ARR,U_LARR));
        _map.add(new EncodedChar(E_U_ARR,U_UARR));
        _map.add(new EncodedChar(E_R_ARR,U_RARR));
        _map.add(new EncodedChar(E_D_ARR,U_DARR));
        _map.add(new EncodedChar(E_H_ARR,U_HARR));
        _map.add(new EncodedChar(E_FORALL,FORALL));
        _map.add(new EncodedChar(E_PART,PART));
        _map.add(new EncodedChar(E_EXIST,EXIST));
        _map.add(new EncodedChar(E_EMPTY,EMPTY));
        _map.add(new EncodedChar(E_NABLA,NABLA));
        _map.add(new EncodedChar(E_ISIN,ISIN));
        _map.add(new EncodedChar(E_NOTIN,NOTIN));
        _map.add(new EncodedChar(E_NI,NI));
        _map.add(new EncodedChar(E_PROD,PROD));
        _map.add(new EncodedChar(E_SUM,SUM));
        _map.add(new EncodedChar(E_MINUS,MINUS));
        _map.add(new EncodedChar(E_LOWAST,LOWAST));
        _map.add(new EncodedChar(E_RADIC,RADIC));
        _map.add(new EncodedChar(E_PROP,PROP));
        _map.add(new EncodedChar(E_INFIN,INFIN));
        _map.add(new EncodedChar(E_ANG,ANG));
        _map.add(new EncodedChar(E_AND,AND));
        _map.add(new EncodedChar(E_OR,OR));
        _map.add(new EncodedChar(E_CAP,CAP));
        _map.add(new EncodedChar(E_CUP,CUP));
        _map.add(new EncodedChar(E_INT,INT));
        _map.add(new EncodedChar(E_THERE4,THERE4));
        _map.add(new EncodedChar(E_SIM,SIM));
        _map.add(new EncodedChar(E_CONG,CONG));
        _map.add(new EncodedChar(E_ASYMP,ASYMP));
        _map.add(new EncodedChar(E_NE,NE));
        _map.add(new EncodedChar(E_EQUIV,EQUIV));
        _map.add(new EncodedChar(E_LE,LE));
        _map.add(new EncodedChar(E_GE,GE));
        _map.add(new EncodedChar(E_SUB,SUB));
        _map.add(new EncodedChar(E_SUP,SUP));
        _map.add(new EncodedChar(E_NSUB,NSUB));
        _map.add(new EncodedChar(E_SUBE,SUBE));
        _map.add(new EncodedChar(E_SUPE,SUPE));
        _map.add(new EncodedChar(E_OPLUS,OPLUS));
        _map.add(new EncodedChar(E_OTIMES,OTIMES));
        _map.add(new EncodedChar(E_PERP,PERP));
        _map.add(new EncodedChar(E_SDOT,SDOT));
        _map.add(new EncodedChar(E_LCEIL,LCEIL));
        _map.add(new EncodedChar(E_RCEIL,RCEIL));
        _map.add(new EncodedChar(E_LFLOOR,LFLOOR));
        _map.add(new EncodedChar(E_RFLOOR,RFLOOR));
        _map.add(new EncodedChar(E_LANG,LANG));
        _map.add(new EncodedChar(E_RANG,RANG));
        _map.add(new EncodedChar(E_LOZ,LOZ));
        _map.add(new EncodedChar(E_SPADES,SPADES));
        _map.add(new EncodedChar(E_CLUBS,CLUBS));
        _map.add(new EncodedChar(E_HEARTS,HEARTS));
        _map.add(new EncodedChar(E_DIAMS,DIAMS));
    }

    private static void format1(CustList<EncodedChar> _map) {
        _map.add(new EncodedChar(E_NBSP,NBSP));
        _map.add(new EncodedChar(E_IEXCL,IEXCL));
        _map.add(new EncodedChar(E_CENT,CENT));
        _map.add(new EncodedChar(E_POUND,POUND));
        _map.add(new EncodedChar(E_CURREN,CURREN));
        _map.add(new EncodedChar(E_YEN,YEN));
        _map.add(new EncodedChar(E_BRVBAR,BRVBAR));
        _map.add(new EncodedChar(E_SECT,SECT));
        _map.add(new EncodedChar(E_UML,UML));
        _map.add(new EncodedChar(E_COPY,COPY));
        _map.add(new EncodedChar(E_ORDF,ORDF));
        _map.add(new EncodedChar(E_LAQUO,LAQUO));
        _map.add(new EncodedChar(E_NOT,NOT));
        _map.add(new EncodedChar(E_SHY,SHY));
        _map.add(new EncodedChar(E_REG,REG));
        _map.add(new EncodedChar(E_MACR,MACR));
        _map.add(new EncodedChar(E_DEG,DEG));
        _map.add(new EncodedChar(E_PLUSMN,PLUSMN));
        _map.add(new EncodedChar(E_SUP2,SUP2));
        _map.add(new EncodedChar(E_SUP3,SUP3));
        _map.add(new EncodedChar(E_ACUTE,ACUTE));
        _map.add(new EncodedChar(E_MICRO,MICRO));
        _map.add(new EncodedChar(E_PARA,PARA));
        _map.add(new EncodedChar(E_MIDDOT,MIDDOT));
        _map.add(new EncodedChar(E_CEDIL,CEDIL));
        _map.add(new EncodedChar(E_SUP1,SUP1));
        _map.add(new EncodedChar(E_ORDM,ORDM));
        _map.add(new EncodedChar(E_RAQUO,RAQUO));
        _map.add(new EncodedChar(E_FRAC14,FRAC14));
        _map.add(new EncodedChar(E_FRAC12,FRAC12));
        _map.add(new EncodedChar(E_FRAC34,FRAC34));
        _map.add(new EncodedChar(E_IQUEST,IQUEST));
        _map.add(new EncodedChar(E_U_AGRAVE,U_A_GRAVE));
        _map.add(new EncodedChar(E_U_AACUTE,U_A_ACUTE));
        _map.add(new EncodedChar(E_U_ACIRC,U_A_CIRC));
        _map.add(new EncodedChar(E_U_ATILDE,U_A_TILDE));
        _map.add(new EncodedChar(E_U_AUML,U_A_UML));
        _map.add(new EncodedChar(E_U_ARING,U_A_RING));
        _map.add(new EncodedChar(E_U_AELIG,U_AE_LIG));
        _map.add(new EncodedChar(E_U_CCEDIL,U_C_CEDIL));
        _map.add(new EncodedChar(E_U_EGRAVE,U_E_GRAVE));
        _map.add(new EncodedChar(E_U_EACUTE,U_E_ACUTE));
        _map.add(new EncodedChar(E_U_ECIRC,U_E_CIRC));
        _map.add(new EncodedChar(E_U_EUML,U_E_UML));
        _map.add(new EncodedChar(E_U_IGRAVE,U_I_GRAVE));
        _map.add(new EncodedChar(E_U_IACUTE,U_I_ACUTE));
        _map.add(new EncodedChar(E_U_ICIRC,U_I_CIRC));
        _map.add(new EncodedChar(E_U_IUML,U_I_UML));
        _map.add(new EncodedChar(E_U_ETH,U_ETH));
        _map.add(new EncodedChar(E_U_NTILDE,U_N_TILDE));
        _map.add(new EncodedChar(E_U_OGRAVE,U_O_GRAVE));
        _map.add(new EncodedChar(E_U_OACUTE,U_O_ACUTE));
        _map.add(new EncodedChar(E_U_OCIRC,U_O_CIRC));
        _map.add(new EncodedChar(E_U_OTILDE,U_O_TILDE));
        _map.add(new EncodedChar(E_U_OUML,U_O_UML));
        _map.add(new EncodedChar(E_TIMES,TIMES));
        _map.add(new EncodedChar(E_U_OSLASH,U_O_SLASH));
        _map.add(new EncodedChar(E_U_UGRAVE,U_U_GRAVE));
        _map.add(new EncodedChar(E_U_UACUTE,U_U_ACUTE));
        _map.add(new EncodedChar(E_U_UCIRC,U_U_CIRC));
        _map.add(new EncodedChar(E_U_UUML,U_U_UML));
        _map.add(new EncodedChar(E_U_YACUTE,U_Y_ACUTE));
        _map.add(new EncodedChar(E_U_THORN,U_THORN));
        _map.add(new EncodedChar(E_SZLIG,SZLIG));
        _map.add(new EncodedChar(E_AGRAVE,AGRAVE));
        _map.add(new EncodedChar(E_AACUTE,AACUTE));
        _map.add(new EncodedChar(E_ACIRC,ACIRC));
        _map.add(new EncodedChar(E_ATILDE,ATILDE));
        _map.add(new EncodedChar(E_AUML,AUML));
        _map.add(new EncodedChar(E_ARING,ARING));
        _map.add(new EncodedChar(E_AELIG,AELIG));
        _map.add(new EncodedChar(E_CCEDIL,CCEDIL));
        _map.add(new EncodedChar(E_EGRAVE,EGRAVE));
        _map.add(new EncodedChar(E_EACUTE,EACUTE));
        _map.add(new EncodedChar(E_ECIRC,ECIRC));
        _map.add(new EncodedChar(E_EUML,EUML));
        _map.add(new EncodedChar(E_IGRAVE,IGRAVE));
        _map.add(new EncodedChar(E_IACUTE,IACUTE));
        _map.add(new EncodedChar(E_ICIRC,ICIRC));
        _map.add(new EncodedChar(E_IUML,IUML));
        _map.add(new EncodedChar(E_ETH,ETH));
        _map.add(new EncodedChar(E_NTILDE,NTILDE));
        _map.add(new EncodedChar(E_OGRAVE,OGRAVE));
        _map.add(new EncodedChar(E_OACUTE,OACUTE));
        _map.add(new EncodedChar(E_OCIRC,OCIRC));
        _map.add(new EncodedChar(E_OTILDE,OTILDE));
        _map.add(new EncodedChar(E_OUML,OUML));
        _map.add(new EncodedChar(E_DIVIDE,DIVIDE));
        _map.add(new EncodedChar(E_OSLASH,OSLASH));
        _map.add(new EncodedChar(E_UGRAVE,UGRAVE));
        _map.add(new EncodedChar(E_UACUTE,UACUTE));
        _map.add(new EncodedChar(E_UCIRC,UCIRC));
        _map.add(new EncodedChar(E_UUML,UUML));
        _map.add(new EncodedChar(E_YACUTE,YACUTE));
        _map.add(new EncodedChar(E_THORN,THORN));
        _map.add(new EncodedChar(E_YUML,YUML));
        _map.add(new EncodedChar(E_QUOT,QUOT));
    }

    private static void addEamp(boolean _affectEamp, CustList<EncodedChar> _map) {
        if (_affectEamp) {
            _map.add(new EncodedChar(E_AMP,ASCII_38));
        }
    }

    private static void addLtGt(boolean _affectLtGt, CustList<EncodedChar> _map) {
        if (_affectLtGt) {
            _map.add(new EncodedChar(E_LT,LT));
            _map.add(new EncodedChar(E_GT,GT));
        }
    }

    public static String encodeHtml(String _htmlText) {
        CustList<EncodedChar> map_ = new CustList<EncodedChar>();
        format(map_);
        map_.add(new EncodedChar(E_LT,LT));
        map_.add(new EncodedChar(E_GT,GT));
        map_.add(new EncodedChar(E_AMP,ASCII_38));
        int length_ = _htmlText.length();
        StringBuilder str_ = new StringBuilder();
        int i_ = 0;
        AbstractEncodingText incr_ = new IncrEncodingText(i_,str_);
        return AbstractEncodingText.encodeCommon(_htmlText, map_, length_, incr_);
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

    public static boolean equalsDocs(String _expected, String _found) {
        Document expDoc_ = DocumentBuilder.parseSax(_expected);
        Document foundDoc_ = DocumentBuilder.parseSax(_found);
        if (expDoc_ == null || foundDoc_ == null) {
            return false;
        }
        FullElement expElt_ = (FullElement) expDoc_.getDocumentElement();
        FullElement foundElt_ = (FullElement) foundDoc_.getDocumentElement();
        return StringUtil.quickEq(expElt_.exportSorted(),foundElt_.exportSorted());
    }
    public FullDocument newDocument() {
        return new FullDocument(getTabWidth());
    }
    public DocumentResult parseNoText(String _input) {
        DocumentResult res_ = new DocumentResult();
        NoTextDocument doc_ = new NoTextDocument(getTabWidth());
        int firstPrint_ = StringUtil.getFirstPrintableCharIndex(_input);
        if (firstPrint_ < 0) {
            res_.setLocation(new RowCol());
            return res_;
        }
        String input_ = _input.substring(firstPrint_);
        int len_ = input_.length();
//        StringBuilder currentText_ = new StringBuilder();
        NotTextElement currentElement_ = (NotTextElement)doc_.createElement("");
//        StringBuilder currentComment_ = new StringBuilder();
        int i_ = IndexConstants.FIRST_INDEX;
        if (input_.charAt(i_) != LT) {
            RowCol rc_ = new RowCol();
            rc_.setRow(1);
            rc_.setCol(1);
            res_.setLocation(rc_);
            return res_;
        }
        i_++;
        AbstractParseTextState st_ = new ParseNoTextState(doc_,currentElement_,input_,i_);
        return AbstractParseTextState.parseCommon(res_, doc_, input_, len_, st_);
    }
    public DocumentResult parse(String _input) {
        DocumentResult res_ = new DocumentResult();
        FullDocument doc_ = new FullDocument(getTabWidth());
        int firstPrint_ = StringUtil.getFirstPrintableCharIndex(_input);
        if (firstPrint_ < 0) {
            res_.setLocation(new RowCol());
            return res_;
        }
        String input_ = _input.substring(firstPrint_);
        int len_ = input_.length();
        FullElement currentElement_ = (FullElement) doc_.createElement("");
        int i_ = IndexConstants.FIRST_INDEX;
        if (input_.charAt(i_) != LT) {
            RowCol rc_ = new RowCol();
            rc_.setRow(1);
            rc_.setCol(1);
            res_.setLocation(rc_);
            return res_;
        }
        i_++;
        AbstractParseTextState st_ = new ParseFullTextState(doc_,currentElement_,input_,i_);
        return AbstractParseTextState.parseCommon(res_, doc_, input_, len_, st_);
    }

    public static DocumentResult parseSaxHtmlRowCol(String _xml) {
        String enc_ = DocumentBuilder.encodeHtml(_xml);
        return parseSaxNotNullRowCol(enc_);
    }

    public static DocumentResult parseSaxNotNullRowCol(String _xml) {
        DocumentBuilder builder_ = newXmlDocumentBuilder();
        return builder_.parse(_xml);
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
