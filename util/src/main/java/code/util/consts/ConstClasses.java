package code.util.consts;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.exceptions.RuntimeClassNotFoundException;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.opers.CollectionsUtil;
import code.util.pagination.SelectedBoolean;

public final class ConstClasses {

    public static final String SELECTED_BOOLEAN = "sb";

    public static final String LISTABLE_ALIAS = "ls";

    public static final String LISTABLE_ENTRIES_ALIAS = "lse";

    private static final String CFG = "cfg";

    private static final String AM = "am";

    private static final String MONTE_CARLO_STRING = "mcs";

    private static final String MONTE_CARLO_NB = "mcn";

    private static final String MONTE_CARLO_NUMBER = "mcu";

    private static final String MONTE_CARLO_EQ = "mcq";

    private static final String MONTE_CARLO_ENUM = "mce";

    private static final String MONTE_CARLO_BOOLEAN = "mcb";

    private static final String LG_INT = "li";

    private static final String NUMBER_MAP = "nm";

    private static final String EQ_LIST = "ql";

    private static final String BOOLEAN_MAP = "bm";

    private static final String BOOLEAN_LIST = "bl";

    private static final String NAT_CMP_TREE_MAP = "ctm";

    private static final String NAT_TREE_MAP = "ntm";

    private static final String TREE_MAP = "tm";

    private static final String CHAR_MAP = "cm";

    private static final String CHAR_LIST = "cl";

    private static final String OBJECT_NOT_NULL_MAP = "onm";

    private static final String OBJECT_MAP = "om";

    private static final String ID_MAP = "im";

    private static final String ENUM_MAP = "em";

    private static final String STRING_MAP = "sm";

    private static final String ENUM_LIST = "el";

    private static final String STRING_LIST = "sl";

    private static final String ID_LIST = "il";

    private static final String RATE = "r";

    private static final String NUMBERS = "n";

    private static final String LIST = "l";

    private static final String BEAN = "b";

    private static final String ACC_VALUE = "hav";

    private static final String DOT = ".";

    private static final String SEP_CLASS = "/";

    private static final String TWO_PTS = ":";

    private static final StringMap<String> MAPPING = initMapping();

    private ConstClasses() {
    }

    private static StringMap<String> initMapping() {
        StringMap<String> mapping_ = new StringMap<String>();
        mapping_.put(AM, "code.maths.montecarlo.AbMonteCarlo");
        mapping_.put(LIST, "code.util.CustList");
        mapping_.put(ID_LIST, "code.util.IdList");
        mapping_.put(STRING_LIST, "code.util.StringList");
        mapping_.put(ENUM_LIST, "code.util.EnumList");
        mapping_.put(STRING_MAP, "code.util.StringMap");
        mapping_.put(ENUM_MAP, "code.util.EnumMap");
        mapping_.put(ID_MAP, "code.util.IdMap");
        mapping_.put(OBJECT_MAP, "code.util.ObjectMap");
        mapping_.put(OBJECT_NOT_NULL_MAP, "code.util.ObjectNotNullMap");
        mapping_.put(CHAR_LIST, "code.util.CharList");
        mapping_.put(CHAR_MAP, "code.util.CharMap");
        mapping_.put(TREE_MAP, "code.util.TreeMap");
        mapping_.put(NAT_TREE_MAP, "code.util.NatTreeMap");
        mapping_.put(NAT_CMP_TREE_MAP, "code.util.NatCmpTreeMap");
        mapping_.put(BOOLEAN_LIST, "code.util.BooleanList");
        mapping_.put(BOOLEAN_MAP, "code.util.BooleanMap");
        mapping_.put(EQ_LIST, "code.util.EqList");
        mapping_.put(NUMBERS, "code.util.Numbers");
        mapping_.put(NUMBER_MAP, "code.util.NumberMap");
        mapping_.put(LG_INT, "code.maths.LgInt");
        mapping_.put(RATE, "code.maths.Rate");
        mapping_.put(MONTE_CARLO_BOOLEAN, "code.maths.montecarlo.MonteCarloBoolean");
        mapping_.put(MONTE_CARLO_ENUM, "code.maths.montecarlo.MonteCarloEnum");
        mapping_.put(MONTE_CARLO_EQ, "code.maths.montecarlo.MonteCarloEq");
        mapping_.put(MONTE_CARLO_NUMBER, "code.maths.montecarlo.MonteCarloNumber");
        mapping_.put(MONTE_CARLO_NB, "code.maths.montecarlo.MonteCarloNb");
        mapping_.put(MONTE_CARLO_STRING, "code.maths.montecarlo.MonteCarloString");
        mapping_.put(BEAN, "code.bean.Bean");
        mapping_.put(CFG, "code.formathtml.Configuration");
        mapping_.put(ACC_VALUE, "code.formathtml.HtmlAccessValue");
        return mapping_;
    }

    public static Class<?> classAliasForNameNotInit(String _className) {
        if (StringList.quickEq(_className, ConstClasses.SELECTED_BOOLEAN)) {
            return SelectedBoolean.class;
        }
        if (StringList.quickEq(_className, ConstClasses.LISTABLE_ALIAS)) {
            return Listable.class;
        }
        if (StringList.quickEq(_className, ConstClasses.LISTABLE_ENTRIES_ALIAS)) {
            return ListableEntries.class;
        }
        String className_ = ConstClasses.getMapping(_className);
        if (className_ != null) {
            return ConstClasses.classForObjectNameNotInit(className_);
        }
        return ConstClasses.classForNameNotInit(_className);
    }
    public static String resolve(String _alias) {
        for (EntryCust<String,String> e: MAPPING.entryList()) {
            if (StringList.quickEq(e.getKey(), _alias)) {
                return e.getValue();
            }
        }
        return _alias;
    }
    public static StringMap<String> getMapping() {
        return MAPPING;
    }

    public static String getMapping(String _alias) {
        return MAPPING.getVal(_alias);
    }

    /**DO NOT USE DEFAULT PACKAGE FOR ALL CLASSES
    @throws RuntimeClassNotFoundException*/
    public static Class<?> classForNameNotInit(String _name) {
        return classForNameKnownClasses(_name, false);
    }

    /**@throws RuntimeClassNotFoundException*/
    private static Class<?> classForNameKnownClasses(String _name, boolean _initialize) {
        Class<?> primitive_ = getPrimitiveClass(_name);
        if (primitive_ != null) {
            return primitive_;
        }
        return classForNameObjectClasses(_name, _initialize);
//        try {
////            Class<?> class_ = Class.forName(_name);
//            Class<?> class_ = forName(_name);
//            return class_;
//        } catch (NoClassDefFoundError _0) {
//            String message_ = _0.getMessage();
//            int indexTwoPts_ = message_.indexOf(TWO_PTS);
//            message_ = message_.substring(indexTwoPts_ + 1).trim();
//            message_ = message_.substring(CollectionsUtil.getFirstIndex(), message_.length() - 1);
////            Matcher ma_ = Pattern.compile(CLASS_REG_EXP).matcher(e.getMessage());
////            ma_.find();
////            ma_.find();
////            String realName_ = ma_.group();
//            String realName_ = message_;
//            realName_ = StringList.replace(realName_, SEP_CLASS, DOT);
////            Class<?> class_ = Class.forName(realName_);
//            Class<?> class_ = forName(realName_);
//            return class_;
//        }
    }

    public static Class<?> classAliasForObjectNameNotInit(String _name) {
        String className_ = ConstClasses.getMapping(_name);
        if (className_ != null) {
            return ConstClasses.classForObjectNameNotInit(className_);
        }
        return classForNameObjectClasses(_name, false);
    }

    /**DO NOT USE DEFAULT PACKAGE FOR ALL CLASSES
    @throws RuntimeClassNotFoundException*/
    public static Class<?> classForObjectNameNotInit(String _name) {
        return classForNameObjectClasses(_name, false);
    }

    private static Class<?> classForNameObjectClasses(String _name, boolean _initialize) {
        try {
//            Class<?> class_ = Class.forName(_name);
            Class<?> class_ = forName(_name, _initialize);
            return class_;
        } catch (NoClassDefFoundError _0) {
            String message_ = _0.getMessage();
            int indexTwoPts_ = message_.indexOf(TWO_PTS);
            message_ = message_.substring(indexTwoPts_ + 1).trim();
            message_ = message_.substring(CollectionsUtil.getFirstIndex(), message_.length() - 1);
//            Matcher ma_ = Pattern.compile(CLASS_REG_EXP).matcher(e.getMessage());
//            ma_.find();
//            ma_.find();
//            String realName_ = ma_.group();
            String realName_ = message_;
            realName_ = StringList.replace(realName_, SEP_CLASS, DOT);
//            Class<?> class_ = Class.forName(realName_);
            Class<?> class_ = forName(realName_, _initialize);
            return class_;
        }
    }

    /**@throws RuntimeClassNotFoundException*/
    private static Class<?> forName(String _name, boolean _initialize) {
        try {
            return Class.forName(_name, _initialize, ConstClasses.class.getClassLoader());
        } catch (ClassNotFoundException _0) {
            throw new RuntimeClassNotFoundException(_name);
        }
    }

    public static Class<?> getPrimitiveClass(String _name) {
        if (StringList.quickEq(int.class.getName(),_name)) {
            return int.class;
        }
        if (StringList.quickEq(long.class.getName(),_name)) {
            return long.class;
        }
        if (StringList.quickEq(short.class.getName(),_name)) {
            return short.class;
        }
        if (StringList.quickEq(byte.class.getName(),_name)) {
            return byte.class;
        }
        if (StringList.quickEq(boolean.class.getName(),_name)) {
            return boolean.class;
        }
        if (StringList.quickEq(char.class.getName(),_name)) {
            return char.class;
        }
        if (StringList.quickEq(float.class.getName(),_name)) {
            return float.class;
        }
        if (StringList.quickEq(double.class.getName(),_name)) {
            return double.class;
        }
        return null;
    }
}
