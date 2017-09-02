package code.datacheck;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Comparator;

import code.datacheck.exception.ContainerException;
import code.datacheck.exception.GettingFieldException;
import code.serialize.ConverterMethod;
import code.serialize.exceptions.BadAccessException;
import code.serialize.exceptions.NullFieldException;
import code.util.EntryCust;
import code.util.IdList;
import code.util.StringList;
import code.util.annot.NullableField;
import code.util.consts.ConstClasses;
import code.util.ints.HasComparator;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.opers.CollectionsUtil;

public final class ObjectComponents {

    private static final String EMPTY_STRING = "";
    private static final String CLASS_EXT = "class";
    private static final String SEP_CLASS = ".";
//    private static final String CLASS_REG_EXP = "(\\w+\\.)*\\w+";
    private static final String LEFT_ARRAY = "[";
    private static final String ARRAY = "[]";
//    private static final String SPACES = "\\s+";
    private static final String BEGIN_TEMPLATE = "<";
    private static final String END_TEMPLATE = ">";
    private static final String FORMAT = "[L{0};";
//    private static final String SET_FIELDS = "setFields";

    private static boolean _checkingNullity_;
    private static boolean _references_;

    private ObjectComponents() {
    }

    public static void checkObjectNotNull(Object _o) {
        setupNotNull(_o);
    }
    static void setupNotNull(Object _o) {
        IdList<ObjectClassName> types_ = new IdList<ObjectClassName>();
        IdList<ObjectClassName> typeCmp_ = new IdList<ObjectClassName>();
        Class<?> class_ = _o.getClass();
        types_.add(new ObjectClassName(_o, class_.getName()));
        IdList<ObjectClassName> currentObjects_;
        IdList<ObjectClassName> newObjects_;
        currentObjects_ = new IdList<ObjectClassName>();
        currentObjects_.add(new ObjectClassName(_o, class_.getName()));
        newObjects_ = new IdList<ObjectClassName>();
        while (true) {
            newObjects_ = new IdList<ObjectClassName>();
            for (ObjectClassName p: currentObjects_) {
                Object obj_ = p.getObject();
                String declaring_ = p.getClassName();
                String name_ = getBaseType(declaring_);
                int l_ = name_.length() - 2;
                int d_ = 0;
                while (true) {
                    if (l_ < 0) {
                        break;
                    }
                    if (!name_.substring(l_).startsWith(ARRAY)) {
                        break;
                    }
                    d_++;
                    l_ -= 2;
                }
                String sub_ = name_;
                if (d_ > 0) {
                    sub_ = name_.substring(0, l_+2);
                }
                if (StringList.quickEq(sub_, int.class.getName())) {
                    sub_ = int[].class.getName();
                    name_ = sub_;
                } else if (StringList.quickEq(sub_, long.class.getName())) {
                    sub_ = long[].class.getName();
                    name_ = sub_;
                } else if (StringList.quickEq(sub_, double.class.getName())) {
                    sub_ = double[].class.getName();
                    name_ = sub_;
                } else if (StringList.quickEq(sub_, float.class.getName())) {
                    sub_ = float[].class.getName();
                    name_ = sub_;
                } else if (StringList.quickEq(sub_, byte.class.getName())) {
                    sub_ = byte[].class.getName();
                    name_ = sub_;
                } else if (StringList.quickEq(sub_, short.class.getName())) {
                    sub_ = short[].class.getName();
                    name_ = sub_;
                } else if (StringList.quickEq(sub_, char.class.getName())) {
                    sub_ = char[].class.getName();
                    name_ = sub_;
                } else if (StringList.quickEq(sub_, boolean.class.getName())) {
                    sub_ = boolean[].class.getName();
                    name_ = sub_;
                } else if (d_ > 0) {
                    sub_ = StringList.simpleFormat(FORMAT, sub_);
                    name_ = sub_;
                }
                Class<?> declClass_ = ConstClasses.classForNameNotInit(name_);
                if (isPrimitive(declClass_)) {
                    if (obj_ == null) {
                        continue;
                    }
                    Class<?> cl_ = obj_.getClass();
                    if (!declClass_.isAssignableFrom(cl_)) {
                        throw new ContainerException(cl_.toString());
                    }
                    continue;
                }
                if (obj_ == null) {
                    continue;
                }
                boolean array_ = false;
                Class<?> cl_ = obj_.getClass();
                if (cl_.isArray()) {
                    if (declaring_.contains(ARRAY) || declaring_.startsWith(LEFT_ARRAY)) {
                        class_ = cl_;
                        array_ = true;
                    }
                }
                boolean containsCmp_ = false;
                for (ObjectClassName c: typeCmp_) {
                    if (c.getObject() == obj_) {
                        containsCmp_ = true;
                        break;
                    }
                }
                if (!array_) {
                    if (cl_.getTypeParameters().length > 0 && !containsCmp_) {
                        class_ = declClass_;
                    } else {
                        if (!declClass_.isAssignableFrom(cl_)) {
                            throw new ContainerException(cl_.toString());
                        }
                        class_ = cl_;
                    }
                } else {
                    if (obj_.getClass() == int[].class) {
                        continue;
                    }
                    if (obj_.getClass() == long[].class) {
                        continue;
                    }
                    if (obj_.getClass() == short[].class) {
                        continue;
                    }
                    if (obj_.getClass() == byte[].class) {
                        continue;
                    }
                    if (obj_.getClass() == float[].class) {
                        continue;
                    }
                    if (obj_.getClass() == double[].class) {
                        continue;
                    }
                    if (obj_.getClass() == char[].class) {
                        continue;
                    }
                    if (obj_.getClass() == boolean[].class) {
                        continue;
                    }
                    Object[] arrayObj_ = (Object[]) obj_;
                    for (Object o: arrayObj_) {
                        if (_checkingNullity_) {
                            if (o == null) {
                                throw new NullFieldException();
                            }
                        }
                        if (_references_) {
                            if (containsObject(types_, o)) {
                                continue;
                            }
                        }
                        types_.add(new ObjectClassName(o, Object.class.getName()));
                        newObjects_.add(new ObjectClassName(o, Object.class.getName()));
                    }
                    continue;
                }
                boolean isInterface_ = class_.isInterface();
                if (isInterface(class_)) {
                    //!containsCmp_
                    continue;
                }
                StringList paramsTypesVar_ = new StringList();
                StringList paramsTypes_ = StringList.getTypes(declaring_);
                for (TypeVariable<?> t: class_.getTypeParameters()) {
                    paramsTypesVar_.add(t.getName());
                }
                Class<?> mapClass_ = getLastSuperClass(obj_, ListableEntries.class);
                Class<?> cmpClass_ = getLastSuperClass(obj_, HasComparator.class);
                Class<?> listClass_ = getLastSuperClass(obj_, Listable.class);
                while (class_ != Object.class) {
                    if (cmpClass_ == class_ || isInterface_) {
                        try {
                            Comparator<?> comp_ = ((HasComparator<?>)obj_).comparator();
                            String nameCmp_ = Comparator.class.getName();
                            if (HasComparator.class.isAssignableFrom(class_)) {
                                StringList cmp_ = getTypes(class_, paramsTypesVar_, paramsTypes_, HasComparator.class);
                                if (!_references_) {
                                    types_.add(new ObjectClassName(comp_, nameCmp_+BEGIN_TEMPLATE+cmp_.first()+END_TEMPLATE));
                                    typeCmp_.add(new ObjectClassName(comp_, nameCmp_+BEGIN_TEMPLATE+cmp_.first()+END_TEMPLATE));
                                    newObjects_.add(new ObjectClassName(comp_, nameCmp_+BEGIN_TEMPLATE+cmp_.first()+END_TEMPLATE));
                                } else {
                                    if (!containsObject(types_, comp_)) {
                                        types_.add(new ObjectClassName(comp_, nameCmp_+BEGIN_TEMPLATE+cmp_.first()+END_TEMPLATE));
                                        typeCmp_.add(new ObjectClassName(comp_, nameCmp_+BEGIN_TEMPLATE+cmp_.first()+END_TEMPLATE));
                                        newObjects_.add(new ObjectClassName(comp_, nameCmp_+BEGIN_TEMPLATE+cmp_.first()+END_TEMPLATE));
                                    }
                                }
                            }
                        } catch (ClassCastException e_) {
                        }
                    }
                    if (mapClass_ == class_ || isInterface_) {
                        try {
                            StringList keysValues_ = getTypes(class_, paramsTypesVar_, paramsTypes_, ListableEntries.class);
                            if (ListableEntries.class.isAssignableFrom(class_)) {
                                for (EntryCust<?, ?> e: ((ListableEntries<?,?>)obj_).entryList()) {
                                    Object k_ = e.getKey();
                                    Object v_ = e.getValue();
                                    if (_checkingNullity_) {
                                        if (k_ == null || v_ == null) {
                                            throw new NullFieldException();
                                        }
                                    }
                                    if (!_references_) {
                                        types_.add(new ObjectClassName(k_, keysValues_.first()));
                                        newObjects_.add(new ObjectClassName(k_, keysValues_.first()));
                                        types_.add(new ObjectClassName(v_, keysValues_.last()));
                                        newObjects_.add(new ObjectClassName(v_, keysValues_.last()));
                                    } else {
                                        if (!containsObject(types_, k_)) {
                                            types_.add(new ObjectClassName(k_, keysValues_.first()));
                                            newObjects_.add(new ObjectClassName(k_, keysValues_.first()));
                                        }
                                        if (!containsObject(types_, v_)) {
                                            types_.add(new ObjectClassName(v_, keysValues_.last()));
                                            newObjects_.add(new ObjectClassName(v_, keysValues_.last()));
                                        }
                                    }
                                }
                            }
                        } catch (ClassCastException _0) {
                        }
                    }
                    if (listClass_ == class_ || isInterface_) {
                        try {
                            StringList keysValues_ = getTypes(class_, paramsTypesVar_, paramsTypes_, Listable.class);
                            if (Listable.class.isAssignableFrom(class_)) {
                                for (Object o: (Listable<?>)obj_) {
                                    if (_checkingNullity_) {
                                        if (o == null) {
                                            throw new NullFieldException();
                                        }
                                    }
                                    if (_references_) {
                                        if (containsObject(types_, o)) {
                                            continue;
                                        }
                                    }
                                    types_.add(new ObjectClassName(o, keysValues_.first()));
                                    newObjects_.add(new ObjectClassName(o, keysValues_.first()));
                                }
                            }
                        } catch (ClassCastException _0) {
                        }
                    }
                    if (isInterface_) {
                        //!containsCmp_
                        //break for the current map or list stored in a declared interface
                        break;
                    }
                    if (!class_.isAnnotationPresent(CheckedData.class)) {
                        for (Field f: class_.getDeclaredFields()) {
                            if (Modifier.isStatic(f.getModifiers())) {
                                continue;
                            }
                            if (isTransient(f, obj_)) {
                                continue;
                            }
                            if (f.isAnnotationPresent(CheckedData.class)) {
                                if (_checkingNullity_) {
                                    f.setAccessible(true);
                                    Object fieldValue_ = getField(f, obj_);
                                    if (fieldValue_ == null) {
                                        throw new NullFieldException(f.getName());
                                    }
                                }
                                continue;
                            }
                            f.setAccessible(true);
                            Object fieldValue_ = getField(f, obj_);
                            if (fieldValue_ == null) {
                                if (_checkingNullity_) {
                                    throw new NullFieldException(f.getName());
                                }
                            }
                            String type_ = removeKeyWordClassSpaces(f.getGenericType().toString());
                            String realType_ = getFormattedType(type_, paramsTypesVar_, paramsTypes_);
                            if (!StringList.quickEq(type_,realType_)) {
                                if (_references_) {
                                    if (containsObject(types_, fieldValue_)) {
                                        continue;
                                    }
                                }
                                types_.add(new ObjectClassName(fieldValue_, realType_));
                                newObjects_.add(new ObjectClassName(fieldValue_, realType_));
                                continue;
                            }
                            if (isPrimitive(realType_)) {
                                continue;
                            }
                            if (_references_) {
                                if (containsObject(types_, fieldValue_)) {
                                    continue;
                                }
                            }
                            types_.add(new ObjectClassName(fieldValue_, realType_));
                            newObjects_.add(new ObjectClassName(fieldValue_, realType_));
                        }
                    } else {
                        if (_checkingNullity_) {
                            for (Field f: class_.getDeclaredFields()) {
                                if (Modifier.isStatic(f.getModifiers())) {
                                    continue;
                                }
                                if (isTransient(f, obj_)) {
                                    continue;
                                }
                                f.setAccessible(true);
                                Object fieldValue_ = getField(f, obj_);
                                if (fieldValue_ == null) {
                                    throw new NullFieldException(f.getName());
                                }
                            }
                        }
                    }
                    Type superClassType_ = class_.getGenericSuperclass();
                    String superClassName_ = superClassType_.toString();
                    superClassName_ = removeKeyWordClassSpaces(superClassName_);
                    superClassName_ = getFormattedType(superClassName_, paramsTypesVar_, paramsTypes_);
                    paramsTypes_ = StringList.getTypes(superClassName_);
                    paramsTypesVar_ = new StringList();
                    class_ = class_.getSuperclass();
                    for (TypeVariable<?> t: class_.getTypeParameters()) {
                        paramsTypesVar_.add(t.getName());
                    }
                }
            }
            if (newObjects_.isEmpty()) {
                break;
            }
            currentObjects_ = new IdList<ObjectClassName>(newObjects_);
        }
        types_.clear();
        typeCmp_.clear();
    }

    private static Object getField(Field _field, Object _instance) {
        try {
            return _field.get(_instance);
        } catch (IllegalAccessException _0) {
            throw new BadAccessException(_0, _field.getName());
        } catch (Error _0) {
            throw new GettingFieldException(_0);
        }
    }

    private static String removeKeyWordClassSpaces(String _string) {
        StringList tokSep_ = StringList.getWordsSeparators(_string);
        StringBuilder str_ = new StringBuilder();
        for (String s: tokSep_) {
            if (StringList.quickEq(s,CLASS_EXT)) {
                continue;
            }
            str_.append(s);
        }
        return removeSpaces(str_.toString());
    }

    private static String removeSpaces(String _string) {
        StringBuilder str_ = new StringBuilder();
        for (char c : _string.toCharArray()) {
            if (Character.isWhitespace(c)) {
                continue;
            }
            str_.append(c);
        }
        return str_.toString();
    }

    private static boolean isTransient(Field _field, Object _obj) {
        if (Modifier.isTransient(_field.getModifiers())) {
            return true;
        }
        if (_field.isAnnotationPresent(NullableField.class)) {
            _field.setAccessible(true);
            Object value_ = getField(_field, _obj);
            return value_ == null;
        }
        return false;
    }

    static boolean isInterface(Class<?> _class) {
        boolean isPureInterface_ = true;
        boolean isInterface_ = _class.isInterface();
        if (ListableEntries.class.isAssignableFrom(_class)) {
            isPureInterface_ = false;
        }
//        try {
//            _class.asSubclass(ListableEntries.class);
//            isPureInterface_ = false;
//        } catch (RuntimeException _0) {
//        }
        if (HasComparator.class.isAssignableFrom(_class)) {
            isPureInterface_ = false;
        }
//        try {
//            _class.asSubclass(HasComparator.class);
//            isPureInterface_ = false;
//        } catch (RuntimeException _0) {
//        }
//        try {
//            _class.asSubclass(Map.class);
//            isPureInterface_ = false;
//        } catch (Exception e_) {
//        }
        if (Listable.class.isAssignableFrom(_class)) {
            isPureInterface_ = false;
        }
//        try {
//            _class.asSubclass(List.class);
//            isPureInterface_ = false;
//        } catch (RuntimeException _0) {
//        }
        if (isInterface_) {
            if (isPureInterface_) {
                return true;
            }
        }
        return false;
    }

    static boolean containsObject(IdList<ObjectClassName> _type, Object _obj) {
        for (ObjectClassName p: _type) {
            if (p.getObject() == _obj) {
                return true;
            }
        }
        return false;
    }

    static StringList getTypes(Class<?> _class, StringList _paramsTypesVar, StringList _paramsTypes,Class<?> _interfaceMapOrList) {
//        _class.asSubclass(_interfaceMapOrList);
        String interfaceType_ = getInterfaceName(_class, _interfaceMapOrList);
        StringList keysValues_ = new StringList();
        if (!interfaceType_.isEmpty()) {
            String realType_ = getFormattedType(interfaceType_, _paramsTypesVar, _paramsTypes);
            keysValues_ = StringList.getTypes(realType_);
        } else {
            keysValues_ = getRealInterfaceTypes(_class, _interfaceMapOrList, _paramsTypesVar, _paramsTypes);
        }
        return keysValues_;
    }

    static StringList getRealInterfaceTypes(Class<?> _interface, Class<?> _superInterface, StringList _paramsTypesVar, StringList _paramsTypes) {
        StringList interfaces_ = getInterfaces(_interface, _superInterface);
        StringList paramsTypes_ = new StringList(_paramsTypes);
        StringList paramsTypesVar_ = new StringList(_paramsTypesVar);
        int i_ = CollectionsUtil.getFirstIndex();
        while (true) {
            Class<?> class_ = ConstClasses.classForNameNotInit(interfaces_.get(i_));
//            Class<?> class_ = Class.forName(interfaces_.get(i_));
//            Class<?> class_ = Constants.classForNameKnownClasses(interfaces_.get(i_));
            if (i_ + 1 >= interfaces_.size()) {
                break;
            }
            // i_ + 1 < interfaces_.size()
            Class<?> superInterface_ = ConstClasses.classForNameNotInit(interfaces_.get(i_ + 1));
//            Class<?> superInterface_ = Class.forName(interfaces_.get(i_ + 1));
//            Class<?> superInterface_ = Constants.classForNameKnownClasses(interfaces_.get(i_ + 1));
            String superClassName_ = getInterfaceName(class_, superInterface_);
            superClassName_ = removeKeyWordClassSpaces(superClassName_);
            //.replaceAll(StringList.BOUNDS+CLASS_EXT+StringList.BOUNDS, EMPTY_STRING);
//            superClassName_ = superClassName_.replaceAll(SPACES, EMPTY_STRING);
            superClassName_ = getFormattedType(superClassName_, paramsTypesVar_, paramsTypes_);
            paramsTypes_ = StringList.getTypes(superClassName_);
            paramsTypesVar_ = new StringList();
            for (TypeVariable<?> t: superInterface_.getTypeParameters()) {
                paramsTypesVar_.add(t.getName());
            }
            i_++;
            // i_ < interfaces_.size()
        }
        return paramsTypes_;
    }

    static String getFormattedType(String _type, StringList _paramsTypesVar, StringList _paramsTypes) {
        StringList wordsAsTokens_ = StringList.getWordsSeparators(_type);
        StringList newList_ = new StringList();
        for (String t: wordsAsTokens_) {
            if (newList_.isEmpty()) {
                newList_.add(t);
                continue;
            }
            if (StringList.isWord(t)) {
                if (newList_.last().endsWith(SEP_CLASS)) {
                    newList_.setLast(newList_.last()+t);
                } else {
                    newList_.add(t);
                }
            } else if (StringList.quickEq(t,SEP_CLASS)) {
                newList_.setLast(newList_.last()+t);
            } else {
                newList_.add(t);
            }
        }
//        StringList tokensVar_ = StringList.getTokensSeparators(_type, CLASS_REG_EXP);
        int len_ = newList_.size();
        for (int i = CollectionsUtil.getFirstIndex(); i < len_; i++) {
            if (i % 2 == CollectionsUtil.getFirstIndex() % 2) {
                continue;
            }
            int index_ = _paramsTypesVar.indexOfObj(newList_.get(i));
            if (index_ != CollectionsUtil.getIndexNotFoundElt()) {
                newList_.set(i, _paramsTypes.get(index_));
            }
        }
        return newList_.join(EMPTY_STRING);
    }

    static String getInterfaceName(Class<?> _class, Class<?> _interface) {
        for (Type interface_: _class.getGenericInterfaces()) {
            if (interface_.toString().startsWith(_interface.getName()+BEGIN_TEMPLATE)) {
//                return interface_.toString().replaceAll(SPACES, EMPTY_STRING);
                return removeSpaces(interface_.toString());
            }
        }
        return EMPTY_STRING;
    }

    static StringList getInterfaces(Class<?> _interface, Class<?> _superInterface) {
        StringList interfaces_ = new StringList();
        StringList currentInterfaces_ = new StringList();
        interfaces_.add(_interface.getName());
        currentInterfaces_.add(_interface.getName());
        StringList newInterfaces_ = new StringList();
        while (true) {
            newInterfaces_ = new StringList();
            Class<?> cl_ = ConstClasses.classForNameNotInit(currentInterfaces_.first());
//            Class<?> cl_ = Class.forName(currentInterfaces_.first());
//            Class<?> cl_ = Constants.classForNameKnownClasses(currentInterfaces_.first());
            for (Class<?> interface_: cl_.getInterfaces()) {
                if (!_superInterface.isAssignableFrom(interface_)) {
                    continue;
                }
                newInterfaces_.add(interface_.getName());
                interfaces_.add(interface_.getName());
                break;
            }
            if (newInterfaces_.isEmpty()) {
                break;
            }
            currentInterfaces_ = new StringList(newInterfaces_);
        }
        return interfaces_;
    }

    static Class<?> getLastSuperClass(Object _obj,Class<?> _class) {
        Class<?> l_ = _obj.getClass();
        try {
            while (l_ != Object.class) {
                Class<?> superClass_;
                superClass_ = l_.getSuperclass();
                if (!_class.isAssignableFrom(superClass_)) {
                    break;
                }
//                superClass_.asSubclass(_class);
                l_ = superClass_;
            }
        } catch (RuntimeException _0) {
        }
        return l_;
    }

    static boolean isPrimitive(String _type) {
        String className_ = getBaseType(_type);
        try {
            Class<?> cl_ = ConstClasses.classForNameNotInit(className_);
            if (cl_.isPrimitive()) {
                return true;
            }
            return isPrimitive(cl_);
//            return isPrimitive(Constants.classForNameKnownClasses(getBaseType(_type)));
        } catch (Error _0) {
            throw new GettingFieldException(_0);
        } catch (RuntimeException _0) {
            return true;
        }
    }
    static boolean isPrimitive(Class<?> _class) {
        if (Number.class.isAssignableFrom(_class)) {
            return true;
        }
//        try {
//            _class.asSubclass(Number.class);
//            return true;
//        } catch (RuntimeException _0) {
//        }
        if (String.class.isAssignableFrom(_class)) {
            return true;
        }
//        try {
//            _class.asSubclass(String.class);
//            return true;
//        } catch (RuntimeException _0) {
//        }
        Method m_ = ConverterMethod.getFromStringMethod(_class);
        if (m_ != null) {
            return true;
        }
        if (Boolean.class.isAssignableFrom(_class)) {
            return true;
        }
//        try {
//            _class.asSubclass(Boolean.class);
//            return true;
//        } catch (RuntimeException _0) {
//        }
        if (Character.class.isAssignableFrom(_class)) {
            return true;
        }
//        try {
//            _class.asSubclass(Character.class);
//            return true;
//        } catch (RuntimeException _0) {
//        }
        if (Enum.class.isAssignableFrom(_class)) {
            return true;
        }
//        try {
//            _class.asSubclass(Enum.class);
//            return true;
//        } catch (RuntimeException _0) {
//        }
        return false;
    }

    static String getBaseType(String _fullType) {
        return StringList.getFirstToken(_fullType, BEGIN_TEMPLATE);
    }

    public static boolean isCheckingNullity() {
        return _checkingNullity_;
    }

    public static void setCheckingNullity(boolean _checkingNullity) {
        _checkingNullity_ = _checkingNullity;
    }

    public static boolean isReferences() {
        return _references_;
    }

    public static void setReferences(boolean _references) {
        _references_ = _references;
    }
}
