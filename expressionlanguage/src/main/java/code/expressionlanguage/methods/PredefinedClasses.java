package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.stds.LgNames;
import code.util.StringList;


public final class PredefinedClasses {

    public static final String ITERABLE = "$iterable";
    public static final String ITERATOR = "$iterator";
    public static final String ENUM = "$enum";
    public static final String ENUM_PARAM = "$Enum";
    private static final String PUBLIC_ACCESS = "PUBLIC";

    private PredefinedClasses() {
    }
    public static boolean isPredefined(String _type, ContextEl _context) {
        if (_context.getClasses() == null) {
            return isPredefined(_type);
        }
        LgNames stds_ = _context.getStandards();
        boolean pred_ = false;
        if (StringList.quickEq(_type, stds_.getAliasIterable())) {
            pred_ = true;
        } else if (StringList.quickEq(_type, stds_.getAliasIteratorType())) {
            pred_ = true;
        } else if (StringList.quickEq(_type, stds_.getAliasEnum())) {
            pred_ = true;
        } else if (StringList.quickEq(_type, stds_.getAliasEnumParam())) {
            pred_ = true;
        }
        return pred_;
    }
    private static boolean isPredefined(String _type) {
        boolean pred_ = false;
        if (StringList.quickEq(_type, PredefinedClasses.ITERABLE)) {
            pred_ = true;
        } else if (StringList.quickEq(_type, PredefinedClasses.ITERATOR)) {
            pred_ = true;
        } else if (StringList.quickEq(_type, PredefinedClasses.ENUM)) {
            pred_ = true;
        } else if (StringList.quickEq(_type, PredefinedClasses.ENUM_PARAM)) {
            pred_ = true;
        }
        return pred_;
    }
    public static String getIterableType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String iterable_ = "<interface access='"+PUBLIC_ACCESS+"' name='"+stds_.getAliasIterable()+"' template='&lt;#T&gt;'>\n";
        iterable_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='"+stds_.getAliasIterator()+"' class='"+stds_.getAliasIteratorType()+"&lt;#T&gt;'/>\n";
        iterable_ += "</interface>\n";
        return iterable_;
    }

    public static String getIteratorType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String iterable_ = "<interface access='"+PUBLIC_ACCESS+"' name='"+stds_.getAliasIteratorType()+"' template='&lt;#T&gt;'>\n";
        iterable_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='"+stds_.getAliasNext()+"' class='#T'/>\n";
        iterable_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='"+stds_.getAliasHasNext()+"' class='"+stds_.getAliasPrimBoolean()+"'/>\n";
        iterable_ += "</interface>\n";
        return iterable_;
    }

    public static String getEnumType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String iterable_ = "<interface access='"+PUBLIC_ACCESS+"' name='"+stds_.getAliasEnum()+"'>\n";
        iterable_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='"+stds_.getAliasName()+"' class='"+stds_.getAliasString()+"'/>\n";
        iterable_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='"+stds_.getAliasOrdinal()+"' class='"+stds_.getAliasPrimInteger()+"'/>\n";
        iterable_ += "</interface>\n";
        return iterable_;
    }

    public static String getEnumParamType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String type_ = stds_.getAliasEnumParam();
        String typeSup_ = stds_.getAliasEnum();
        String iterable_ = "<interface access='"+PUBLIC_ACCESS+"' name='"+type_+"' template='&lt;#T:"+type_+"&lt;#T&gt;&gt;' class0='"+typeSup_+"'>\n";
        iterable_ += "</interface>\n";
        return iterable_;
    }
    public static String getBracedIterableType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String iterable_ = "$public $interface "+stds_.getAliasIterable()+"<#T>{\n";
        iterable_ += "$public $abstract "+stds_.getAliasIteratorType()+"<#T> "+stds_.getAliasIterator()+"():\n";
        iterable_ += "}\n";
        return iterable_;
    }

    public static String getBracedIteratorType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String iterable_ = "$public $interface "+stds_.getAliasIteratorType()+"<#T>{\n";
        iterable_ += "$public $abstract #T "+stds_.getAliasNext()+"():\n";
        iterable_ += "$public $abstract "+stds_.getAliasPrimBoolean()+" "+stds_.getAliasHasNext()+"():\n";
        iterable_ += "}\n";
        return iterable_;
    }

    public static String getBracedEnumType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String iterable_ = "$public $interface "+stds_.getAliasEnum()+"{\n";
        iterable_ += "$public $abstract "+stds_.getAliasPrimInteger()+" "+stds_.getAliasName()+"():\n";
        iterable_ += "$public $abstract "+stds_.getAliasPrimInteger()+" "+stds_.getAliasOrdinal()+"():\n";
        iterable_ += "}\n";
        return iterable_;
    }

    public static String getBracedEnumParamType(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String type_ = stds_.getAliasEnumParam();
        String typeSup_ = stds_.getAliasEnum();
        String iterable_ = "$public $interface "+type_+"<#T:"+type_+"<#T>>:"+typeSup_+"{\n";
        iterable_ += "}\n";
        return iterable_;
    }
}
