package code.serialize;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.AbstractList;

import code.serialize.exceptions.NullSerialException;
import code.serialize.exceptions.RefException;
import code.util.EntryCust;
import code.util.annot.NullableField;
import code.util.annot.RwXml;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.ints.Viewable;
import code.util.opers.CollectionsUtil;

final class CurrentSerializableElements {

    private SerialList<TemplateSerial> allComposites = new SerialList<TemplateSerial>();
    private SerialList<TemplateSerial> news = new SerialList<TemplateSerial>();
    private SerialList<TemplateSerial> currents = new SerialList<TemplateSerial>();
//    private SerialList<ElementsSerial> allImplicitComparators = new SerialList<>();
    private SerialMap<SerialList<ElementsSerial>> componentComposite = new SerialMap<SerialList<ElementsSerial>>();
    private SerialList<ElementsSerial> components = new SerialList<ElementsSerial>();
    private long id;

    CurrentSerializableElements(ObjectSerial _base) {
        currents.add(_base);
    }

    void initializeObjectsWithoutIdRef() {
        boolean modif_ = true;
        while(modif_) {
            modif_ = false;
            setNews(new SerialList<TemplateSerial>());
            for (TemplateSerial e: getCurrents()) {
                setComponents(new SerialList<ElementsSerial>());
                Object currentValue_ = e.getValue();
                if (currentValue_ instanceof Listable<?>) {

                    for (Object o: (Listable<?>)currentValue_) {
                        addElementInListWithoutIdRef(o);
                    }
                }
                if (currentValue_ instanceof ListableEntries) {
//                    if (currentValue_ instanceof TreeNodeMap) {
//                        Object value_ = ((TreeNodeMap<?,?>)currentValue_).getCmp();
//                        addElementInMapWithoutIdRef(value_,true);
//                    }
                    // else if (currentValue_ instanceof SortableMap && !(currentValue_ instanceof NaturalTreeNodeMap)) {
//                        Object value_ = ((SortableMap<?,?>)currentValue_).comparator();
//                        if (value_ != null) {
//                            addElementInMapWithoutIdRef(value_,false,true);
//                        }
                    //}
                    for (EntryCust<?, ?> o: ((ListableEntries<?,?>)currentValue_).entryList()) {
                        addElementInMapWithoutIdRef(o.getKey(),true);
                        addElementInMapWithoutIdRef(o.getValue(),false);
                    }
                }
                if (currentValue_.getClass().isArray()) {
                    int length_ = Array.getLength(currentValue_);
                    for (int i = CollectionsUtil.getFirstIndex(); i < length_; i++) {
                        addElementInArrayWithoutIdRef(Array.get(currentValue_, i));
                    }
                    addComponentsToComposite(e);
                    continue;
                }
//                if (containsComparator(e)) {
//                    Class<?> cl_ = currentValue_.getClass();
//                    while (cl_ != Object.class) {
//                        boolean comparatorClass_ = false;
//                        try {
//                            cl_.asSubclass(Comparator.class);
//                            comparatorClass_ = true;
//                        } catch (Exception _0) {
//                        }
//                        if (!comparatorClass_) {
//                            break;
//                        }
//
//                        for (Field f: cl_.getDeclaredFields()) {
//                            if (Modifier.isTransient(f.getModifiers())) {
//                                if (!SerializeXmlObject.isCopying()) {
//                                    continue;
//                                }
//                            }
//                            if (Modifier.isStatic(f.getModifiers())) {
//                                continue;
//                            }
//                            f.setAccessible(cl_.getAnnotation(RwXml.class)!=null || f.getAnnotation(RwXml.class) != null);
//                            Object value_ = ConverterMethod.getField(f, currentValue_);
//                            addElementInSerializableWithoutIdRef(value_, cl_, f.getName());
//                        }
//                        cl_ = cl_.getSuperclass();
//                    }
//                    addComponentsToComposite(e);
//                    continue;
//                }

                if (currentValue_ instanceof XmlTransientable) {
                    ((XmlTransientable)currentValue_).beforeSave();
                }
                Class<?> cl_ = currentValue_.getClass();
                while (cl_ != Object.class) {
                    if (cl_.getSuperclass() == AbstractList.class) {
                        break;
                    }
//                    if (cl_.getSuperclass() == SerializeXmlObject.MP_CLASS) {
//                        break;
//                    }
                    for (Field f: cl_.getDeclaredFields()) {
                        if (Modifier.isTransient(f.getModifiers())) {
                            if (!SerializeXmlObject.isCopying()) {
                                continue;
                            }
                        }
                        if (Modifier.isStatic(f.getModifiers())) {
                            continue;
                        }
                        f.setAccessible(cl_.getAnnotation(RwXml.class)!=null || f.getAnnotation(RwXml.class) != null);
                        Object value_ = ConverterMethod.getField(f, currentValue_);
                        addElementInSerializableWithoutIdRef(value_, cl_, f.getName());
                    }
//                    if (cl_ == EnumSerializeXmlObject.MP_CLASS) {
//                        break;
//                    }
                    cl_ = cl_.getSuperclass();
                }
                addComponentsToComposite(e);
            }
            if(!getNews().isEmpty()) {
                modif_ = true;
                setCurrents(new SerialList<TemplateSerial>(getNews()));
            }
        }
    }

//    private boolean containsComparator(TemplateSerial _e) {
//        for (ElementsSerial e: getAllImplicitComparators()) {
//            if (ElementsSerial.sameValue(e, _e)) {
//                return true;
//            }
//        }
//        return false;
//    }

    void checkNullPointersWithoutIdRef() {
        boolean modif_ = true;
        while(modif_) {
            modif_ = false;
            setNews(new SerialList<TemplateSerial>());
            for (TemplateSerial e: getCurrents()) {
                setComponents(new SerialList<ElementsSerial>());
                Object currentValue_ = e.getValue();
                if (currentValue_ instanceof Listable<?>) {

                    for (Object o: (Listable<?>)currentValue_) {
                        checkNullPointerWithoutIdRef(o,
                                false, SerializeXmlObject.LS_CLASS, null, false);
                    }
                }
                if (currentValue_ instanceof ListableEntries) {
//                    if (currentValue_ instanceof TreeNodeMap) {
//                        Object value_ = ((TreeNodeMap<?,?>)currentValue_).getCmp();
//                        checkNullPointerWithoutIdRef(value_,
//                                true, SerializeXmlObject.MP_CLASS, null, true);
//                    }
                    //else if (currentValue_ instanceof SortableMap
                        //    && !(currentValue_ instanceof NaturalTreeNodeMap)) {
//                        Object value_ = ((SortableMap<?,?>)currentValue_).comparator();
//                        if (value_ != null) {
//                            checkNullPointerWithoutIdRef(value_,
//                                    true, SerializeXmlObject.MP_CLASS, null, true);
//                        }
                    //}
                    for (EntryCust<?, ?> o: ((ListableEntries<?,?>)currentValue_).entryList()) {
                        checkNullPointerWithoutIdRef(o.getKey(),
                                true, SerializeXmlObject.MP_CLASS, null, false);
                        checkNullPointerWithoutIdRef(o.getValue(),
                                false, SerializeXmlObject.MP_CLASS, null, false);
                    }
                }
                if (currentValue_ == null) {
                    throw new NullSerialException();
                }
                if (currentValue_.getClass().isArray()) {
                    int length_ = Array.getLength(currentValue_);
                    for (int i = CollectionsUtil.getFirstIndex(); i < length_; i++) {
                        checkNullPointerWithoutIdRef(Array.get(currentValue_, i),
                                false, null, null, false);
                    }
                    continue;
                }
//                if (containsComparator(e)) {
//                    Class<?> cl_ = currentValue_.getClass();
//                    while (cl_ != Object.class) {
//                        boolean comparatorClass_ = false;
//                        try {
//                            cl_.asSubclass(Comparator.class);
//                            comparatorClass_ = true;
//                        } catch (Exception _0) {
//                        }
//                        if (!comparatorClass_) {
//                            break;
//                        }
//
//                        for (Field f: cl_.getDeclaredFields()) {
//                            if (Modifier.isTransient(f.getModifiers())) {
//                                continue;
//                            }
//                            if (Modifier.isStatic(f.getModifiers())) {
//                                continue;
//                            }
//                            f.setAccessible(true);
//                            Object value_ = ConverterMethod.getField(f, currentValue_);
//                            checkNullPointerWithoutIdRef(value_, false, cl_, f.getName(), false);
//                        }
//                        cl_ = cl_.getSuperclass();
//                    }
//                    continue;
//                }

                Class<?> cl_ = currentValue_.getClass();
                while (cl_ != Object.class) {
                    if (cl_.getSuperclass() == AbstractList.class) {
                        break;
                    }
//                    if (cl_.getSuperclass() == SerializeXmlObject.MP_CLASS) {
//                        break;
//                    }
                    for (Field f: cl_.getDeclaredFields()) {
                        if (Modifier.isTransient(f.getModifiers())) {
                            continue;
                        }
                        if (Modifier.isStatic(f.getModifiers())) {
                            continue;
                        }
                        f.setAccessible(true);
                        Object value_ = ConverterMethod.getField(f, currentValue_);
                        boolean nullable_ = f.getAnnotation(NullableField.class) != null;
                        checkNullPointerWithoutIdRef(value_, false, cl_.getName(), f.getName(), nullable_);
                    }
                    cl_ = cl_.getSuperclass();
                }
            }
            if(!getNews().isEmpty()) {
                modif_ = true;
                setCurrents(new SerialList<TemplateSerial>(getNews()));
            }
        }
    }

    void setUnmodifiedWithoutIdRef() {
        boolean modif_ = true;
        while(modif_) {
            modif_ = false;
            setNews(new SerialList<TemplateSerial>());
            for (TemplateSerial e: getCurrents()) {
                setComponents(new SerialList<ElementsSerial>());
                Object currentValue_ = e.getValue();
                if (currentValue_ instanceof Listable<?>) {

                    for (Object o: (Listable<?>)currentValue_) {
                        setUnmodifiedWithoutIdRef(o,
                                false, SerializeXmlObject.LS_CLASS, null);
                    }
                }
                if (currentValue_ instanceof ListableEntries) {
//                    if (currentValue_ instanceof TreeNodeMap) {
//                        Object value_ = ((TreeNodeMap<?,?>)currentValue_).getCmp();
//                        setUnmodifiedWithoutIdRef(value_,
//                                true, SerializeXmlObject.MP_CLASS, null);
//                    }// else if (currentValue_ instanceof SortableMap
                    //        && !(currentValue_ instanceof NaturalTreeNodeMap)) {
//                        Object value_ = ((SortableMap<?,?>)currentValue_).comparator();
//                        if (value_ != null) {
//                            setUnmodifiedWithoutIdRef(value_,
//                                    true, SerializeXmlObject.MP_CLASS, null, true);
//                        }
                    //}
                    for (EntryCust<?, ?> o: ((ListableEntries<?,?>)currentValue_).entryList()) {
                        setUnmodifiedWithoutIdRef(o.getKey(),
                                true, SerializeXmlObject.MP_CLASS, null);
                        setUnmodifiedWithoutIdRef(o.getValue(),
                                false, SerializeXmlObject.MP_CLASS, null);
                    }
                }
//                if (containsComparator(e)) {
//                    Class<?> cl_ = currentValue_.getClass();
//                    while (cl_ != Object.class) {
//                        boolean comparatorClass_ = false;
//                        try {
//                            cl_.asSubclass(Comparator.class);
//                            comparatorClass_ = true;
//                        } catch (Exception _0) {
//                        }
//                        if (!comparatorClass_) {
//                            break;
//                        }
//
//                        for (Field f: cl_.getDeclaredFields()) {
//                            if (Modifier.isTransient(f.getModifiers())) {
//                                continue;
//                            }
//                            if (Modifier.isStatic(f.getModifiers())) {
//                                continue;
//                            }
//                            f.setAccessible(cl_.getAnnotation(RwXml.class)!=null || f.getAnnotation(RwXml.class) != null);
//                            Object value_ = ConverterMethod.getField(f, currentValue_);
//                            setUnmodifiedWithoutIdRef(value_, false, cl_, f.getName(), false);
//                        }
//                        cl_ = cl_.getSuperclass();
//                    }
//                }

                Class<?> cl_ = currentValue_.getClass();
                while (cl_ != Object.class) {
                    if (cl_.getSuperclass() == AbstractList.class) {
                        break;
                    }
//                    if (cl_.getSuperclass() == SerializeXmlObject.MP_CLASS) {
//                        break;
//                    }
                    for (Field f: cl_.getDeclaredFields()) {
                        if (Modifier.isTransient(f.getModifiers())) {
                            continue;
                        }
                        if (Modifier.isStatic(f.getModifiers())) {
                            continue;
                        }
                        f.setAccessible(cl_.getAnnotation(RwXml.class)!=null || f.getAnnotation(RwXml.class) != null);
                        Object value_ = ConverterMethod.getField(f, currentValue_);
                        setUnmodifiedWithoutIdRef(value_, false, cl_.getName(), f.getName());
                    }
                    cl_ = cl_.getSuperclass();
                }
            }
            if(!getNews().isEmpty()) {
                modif_ = true;
                setCurrents(new SerialList<TemplateSerial>(getNews()));
            }
        }
    }

    void setUnmodified() {
        boolean modif_ = true;
        while(modif_) {
            modif_ = false;
            setNews(new SerialList<TemplateSerial>());
            for (TemplateSerial e: getCurrents()) {
                setComponents(new SerialList<ElementsSerial>());
                Object currentValue_ = e.getValue();
                if (currentValue_ instanceof Listable<?>) {

                    for (Object o: (Listable<?>)currentValue_) {
                        setUnmodified(o,
                                false, SerializeXmlObject.LS_CLASS, null);
                    }
                }
                if (currentValue_ instanceof ListableEntries) {
//                    if (currentValue_ instanceof TreeNodeMap) {
//                        Object value_ = ((TreeNodeMap<?,?>)currentValue_).getCmp();
//                        setUnmodified(value_,
//                                true, SerializeXmlObject.MP_CLASS, null);
//                    } //else if (currentValue_ instanceof SortableMap
                        //    && !(currentValue_ instanceof NaturalTreeNodeMap)) {
//                        Object value_ = ((SortableMap<?,?>)currentValue_).comparator();
//                        if (value_ != null) {
//                            setUnmodified(value_,
//                                    true, SerializeXmlObject.MP_CLASS, null, true);
//                        }
                    //}
                    for (EntryCust<?, ?> o: ((ListableEntries<?,?>)currentValue_).entryList()) {
                        setUnmodified(o.getKey(),
                                true, SerializeXmlObject.MP_CLASS, null);
                        setUnmodified(o.getValue(),
                                false, SerializeXmlObject.MP_CLASS, null);
                    }
                }
//                if (containsComparator(e)) {
//                    Class<?> cl_ = currentValue_.getClass();
//                    while (cl_ != Object.class) {
//                        boolean comparatorClass_ = false;
//                        try {
//                            cl_.asSubclass(Comparator.class);
//                            comparatorClass_ = true;
//                        } catch (Exception _0) {
//                        }
//                        if (!comparatorClass_) {
//                            break;
//                        }
//
//                        for (Field f: cl_.getDeclaredFields()) {
//                            if (Modifier.isTransient(f.getModifiers())) {
//                                continue;
//                            }
//                            if (Modifier.isStatic(f.getModifiers())) {
//                                continue;
//                            }
//                            f.setAccessible(cl_.getAnnotation(RwXml.class)!=null || f.getAnnotation(RwXml.class) != null);
//                            Object value_ = ConverterMethod.getField(f, currentValue_);
//                            setUnmodified(value_, false, cl_, f.getName(), false);
//                        }
//                        cl_ = cl_.getSuperclass();
//                    }
//                }

                Class<?> cl_ = currentValue_.getClass();
                while (cl_ != Object.class) {
                    if (cl_.getSuperclass() == AbstractList.class) {
                        break;
                    }
//                    if (cl_.getSuperclass() == SerializeXmlObject.MP_CLASS) {
//                        break;
//                    }
                    for (Field f: cl_.getDeclaredFields()) {
                        if (Modifier.isTransient(f.getModifiers())) {
                            continue;
                        }
                        if (Modifier.isStatic(f.getModifiers())) {
                            continue;
                        }
                        f.setAccessible(cl_.getAnnotation(RwXml.class)!=null || f.getAnnotation(RwXml.class) != null);
                        Object value_ = ConverterMethod.getField(f, currentValue_);
                        setUnmodified(value_, false, cl_.getName(), f.getName());
                    }
                    cl_ = cl_.getSuperclass();
                }
            }
            if(!getNews().isEmpty()) {
                modif_ = true;
                setCurrents(new SerialList<TemplateSerial>(getNews()));
            }
        }
    }

    boolean isModifiedWithoutIdRef() {
        boolean modif_ = true;
        while(modif_) {
            modif_ = false;
            setNews(new SerialList<TemplateSerial>());
            for (TemplateSerial e: getCurrents()) {
                setComponents(new SerialList<ElementsSerial>());
                Object currentValue_ = e.getValue();
                if (currentValue_ instanceof Listable<?>) {

                    for (Object o: (Listable<?>)currentValue_) {
                        if(!isUnmodifiedWithoutIdRef(o,
                                false, SerializeXmlObject.LS_CLASS, null)) {
                            return true;
                        }
                    }
                }
                if (currentValue_ instanceof ListableEntries) {
//                    if (currentValue_ instanceof TreeNodeMap) {
//                        Object value_ = ((TreeNodeMap<?,?>)currentValue_).getCmp();
//                        if(!isUnmodifiedWithoutIdRef(value_,
//                                true, SerializeXmlObject.MP_CLASS, null, true)) {
//                            return true;
//                        }
//                    } //else if (currentValue_ instanceof SortableMap
                        //    && !(currentValue_ instanceof NaturalTreeNodeMap)) {
//                        Object value_ = ((SortableMap<?,?>)currentValue_).comparator();
//                        if (value_ != null) {
//                            if(!isUnmodifiedWithoutIdRef(value_,
//                                    true, SerializeXmlObject.MP_CLASS, null, true)) {
//                                return true;
//                            }
//                        }
                    //}
                    for (EntryCust<?, ?> o: ((ListableEntries<?,?>)currentValue_).entryList()) {
                        if(!isUnmodifiedWithoutIdRef(o.getKey(),
                                true, SerializeXmlObject.MP_CLASS, null)) {
                            return true;
                        }
                        if(!isUnmodifiedWithoutIdRef(o.getValue(),
                                false, SerializeXmlObject.MP_CLASS, null)) {
                            return true;
                        }
                    }
                }
//                if (containsComparator(e)) {
//                    Class<?> cl_ = currentValue_.getClass();
//                    while (cl_ != Object.class) {
//                        boolean comparatorClass_ = false;
//                        try {
//                            cl_.asSubclass(Comparator.class);
//                            comparatorClass_ = true;
//                        } catch (Exception _0) {
//                        }
//                        if (!comparatorClass_) {
//                            break;
//                        }
//
//                        for (Field f: cl_.getDeclaredFields()) {
//                            if (Modifier.isTransient(f.getModifiers())) {
//                                continue;
//                            }
//                            if (Modifier.isStatic(f.getModifiers())) {
//                                continue;
//                            }
//                            f.setAccessible(cl_.getAnnotation(RwXml.class)!=null || f.getAnnotation(RwXml.class) != null);
//                            Object value_ = ConverterMethod.getField(f, currentValue_);
//                            if(!isUnmodifiedWithoutIdRef(value_, false, cl_, f.getName(), false)) {
//                                return true;
//                            }
//                        }
//                        cl_ = cl_.getSuperclass();
//                    }
//                }

                Class<?> cl_ = currentValue_.getClass();
                while (cl_ != Object.class) {
                    if (cl_.getSuperclass() == AbstractList.class) {
                        break;
                    }
//                    if (cl_.getSuperclass() == SerializeXmlObject.MP_CLASS) {
//                        break;
//                    }
                    for (Field f: cl_.getDeclaredFields()) {
                        if (Modifier.isTransient(f.getModifiers())) {
                            continue;
                        }
                        if (Modifier.isStatic(f.getModifiers())) {
                            continue;
                        }
                        f.setAccessible(cl_.getAnnotation(RwXml.class)!=null || f.getAnnotation(RwXml.class) != null);
                        Object value_ = ConverterMethod.getField(f, currentValue_);
                        if(!isUnmodifiedWithoutIdRef(value_, false, cl_.getName(), f.getName())) {
                            return true;
                        }
                    }
                    cl_ = cl_.getSuperclass();
                }
            }
            if(!getNews().isEmpty()) {
                modif_ = true;
                setCurrents(new SerialList<TemplateSerial>(getNews()));
            }
        }
        return false;
    }

    boolean isModified() {
        boolean modif_ = true;
        while(modif_) {
            modif_ = false;
            setNews(new SerialList<TemplateSerial>());
            for (TemplateSerial e: getCurrents()) {
                setComponents(new SerialList<ElementsSerial>());
                Object currentValue_ = e.getValue();
                if (currentValue_ instanceof Listable<?>) {

                    for (Object o: (Listable<?>)currentValue_) {
                        if(!isUnmodified(o,
                                false, SerializeXmlObject.LS_CLASS, null)) {
                            return true;
                        }
                    }
                }
                if (currentValue_ instanceof ListableEntries) {
//                    if (currentValue_ instanceof TreeNodeMap) {
//                        Object value_ = ((TreeNodeMap<?,?>)currentValue_).getCmp();
//                        if(!isUnmodified(value_,
//                                true, SerializeXmlObject.MP_CLASS, null)) {
//                            return true;
//                        }
//                    } //else if (currentValue_ instanceof SortableMap
                        //    && !(currentValue_ instanceof NaturalTreeNodeMap)) {
//                        Object value_ = ((SortableMap<?,?>)currentValue_).comparator();
//                        if (value_ != null) {
//                            if(!isUnmodified(value_,
//                                    true, SerializeXmlObject.MP_CLASS, null, true)) {
//                                return true;
//                            }
//                        }
                    //}
                    for (EntryCust<?, ?> o: ((ListableEntries<?,?>)currentValue_).entryList()) {
                        if(!isUnmodified(o.getKey(),
                                true, SerializeXmlObject.MP_CLASS, null)) {
                            return true;
                        }
                        if(!isUnmodified(o.getValue(),
                                false, SerializeXmlObject.MP_CLASS, null)) {
                            return true;
                        }
                    }
                }
//                if (containsComparator(e)) {
//                    Class<?> cl_ = currentValue_.getClass();
//                    while (cl_ != Object.class) {
//                        boolean comparatorClass_ = false;
//                        try {
//                            cl_.asSubclass(Comparator.class);
//                            comparatorClass_ = true;
//                        } catch (Exception _0) {
//                        }
//                        if (!comparatorClass_) {
//                            break;
//                        }
//
//                        for (Field f: cl_.getDeclaredFields()) {
//                            if (Modifier.isTransient(f.getModifiers())) {
//                                continue;
//                            }
//                            if (Modifier.isStatic(f.getModifiers())) {
//                                continue;
//                            }
//                            f.setAccessible(cl_.getAnnotation(RwXml.class)!=null || f.getAnnotation(RwXml.class) != null);
//                            Object value_ = ConverterMethod.getField(f, currentValue_);
//                            if(!isUnmodified(value_, false, cl_, f.getName(), false)) {
//                                return true;
//                            }
//                        }
//                        cl_ = cl_.getSuperclass();
//                    }
//                }

                Class<?> cl_ = currentValue_.getClass();
                while (cl_ != Object.class) {
                    if (cl_.getSuperclass() == AbstractList.class) {
                        break;
                    }
//                    if (cl_.getSuperclass() == SerializeXmlObject.MP_CLASS) {
//                        break;
//                    }
                    for (Field f: cl_.getDeclaredFields()) {
                        if (Modifier.isTransient(f.getModifiers())) {
                            continue;
                        }
                        if (Modifier.isStatic(f.getModifiers())) {
                            continue;
                        }
                        f.setAccessible(cl_.getAnnotation(RwXml.class)!=null || f.getAnnotation(RwXml.class) != null);
                        Object value_ = ConverterMethod.getField(f, currentValue_);
                        if(!isUnmodified(value_, false, cl_.getName(), f.getName())) {
                            return true;
                        }
                    }
                    cl_ = cl_.getSuperclass();
                }
            }
            if(!getNews().isEmpty()) {
                modif_ = true;
                setCurrents(new SerialList<TemplateSerial>(getNews()));
            }
        }
        return false;
    }

    void initializeObjects() {
        boolean modif_ = true;
        while(modif_) {
            modif_ = false;
            setNews(new SerialList<TemplateSerial>());
            for (TemplateSerial e: getCurrents()) {
                setComponents(new SerialList<ElementsSerial>());
                Object currentValue_ = e.getValue();
                if (currentValue_ instanceof Listable<?>) {

                    for (Object o: (Listable<?>)currentValue_) {
                        addElementInList(o);
                    }
                }
                if (currentValue_ instanceof ListableEntries) {
//                    if (currentValue_ instanceof TreeNodeMap) {
//                        Object value_ = ((TreeNodeMap<?,?>)currentValue_).getCmp();
//                        addElementInMap(value_,true);
//                    }
                    //else if (currentValue_ instanceof SortableMap && !(currentValue_ instanceof NaturalTreeNodeMap)) {
//                        Object value_ = ((SortableMap<?,?>)currentValue_).comparator();
//                        if (value_ != null) {
//                            addElementInMap(value_,false,true);
//                        }
                    //}
                    for (EntryCust<?, ?> o: ((ListableEntries<?,?>)currentValue_).entryList()) {
                        addElementInMap(o.getKey(),true);
                        addElementInMap(o.getValue(),false);
                    }
                }
                if (currentValue_.getClass().isArray()) {
                    int length_ = Array.getLength(currentValue_);
                    for (int i = CollectionsUtil.getFirstIndex(); i < length_; i++) {
                        addElementInArray(Array.get(currentValue_, i));
                    }
                    addComponentsToComposite(e);
                    continue;
                }
//                if (containsComparator(e)) {
//                    Class<?> cl_ = currentValue_.getClass();
//                    while (cl_ != Object.class) {
//                        boolean comparatorClass_ = false;
//                        try {
//                            cl_.asSubclass(Comparator.class);
//                            comparatorClass_ = true;
//                        } catch (Exception _0) {
//                        }
//                        if (!comparatorClass_) {
//                            break;
//                        }
//
//                        for (Field f: cl_.getDeclaredFields()) {
//                            if (Modifier.isTransient(f.getModifiers())) {
//                                if (!SerializeXmlObject.isCopying()) {
//                                    continue;
//                                }
//                            }
//                            if (Modifier.isStatic(f.getModifiers())) {
//                                continue;
//                            }
//                            f.setAccessible(cl_.getAnnotation(RwXml.class)!=null || f.getAnnotation(RwXml.class) != null);
//                            Object value_ = ConverterMethod.getField(f, currentValue_);
//                            addElementInSerializable(value_, cl_, f.getName());
//                        }
//                        cl_ = cl_.getSuperclass();
//                    }
//                    addComponentsToComposite(e);
//                    continue;
//                }

                if (currentValue_ instanceof XmlTransientable) {
                    ((XmlTransientable)currentValue_).beforeSave();
                }
                Class<?> cl_ = currentValue_.getClass();
                while (cl_ != Object.class) {
                    if (cl_.getSuperclass() == AbstractList.class) {
                        break;
                    }
//                    if (cl_.getSuperclass() == SerializeXmlObject.MP_CLASS) {
//                        break;
//                    }
                    for (Field f: cl_.getDeclaredFields()) {
                        if (Modifier.isTransient(f.getModifiers())) {
                            if (!SerializeXmlObject.isCopying()) {
                                continue;
                            }
                        }
                        if (Modifier.isStatic(f.getModifiers())) {
                            continue;
                        }
                        f.setAccessible(cl_.getAnnotation(RwXml.class)!=null || f.getAnnotation(RwXml.class) != null);
                        Object value_ = ConverterMethod.getField(f, currentValue_);
                        addElementInSerializable(value_, cl_, f.getName());
                    }
//                    if (cl_ == EnumSerializeXmlObject.MP_CLASS) {
//                        break;
//                    }
                    cl_ = cl_.getSuperclass();
                }
                addComponentsToComposite(e);
            }
            if(!getNews().isEmpty()) {
                modif_ = true;
                setCurrents(new SerialList<TemplateSerial>(getNews()));
            }
        }
    }

    private void addComponentsToComposite(TemplateSerial _e) {
//        for (Pair<TemplateSerial,SerialList<ElementsSerial>> p: componentComposite.getElements()) {
//            if (p.getFirst().getValue() == _e.getValue()) {
//                p.setSecond(components);
//                return;
//            }
//        }
        componentComposite.getElements().add(new TemplateSerialValue<SerialList<ElementsSerial>>(_e, components));
//        componentComposite.put(_e, components);
    }
    private void addElementInSerializable(
            Object _value, Class<?> _cl, String _fieldName) {
        addElement(_value,
                false, _cl.getName(), _fieldName);
    }
    private void addElementInList(Object _value) {
        addElement(_value,
                false, SerializeXmlObject.LS_CLASS, null);
    }

    private void addElementInMap(Object _value,
            boolean _isKey) {
        addElement(_value,
                _isKey,SerializeXmlObject.MP_CLASS, null);
    }
    private void addElementInSerializableWithoutIdRef(
            Object _value, Class<?> _cl, String _fieldName) {
        addElementWithoutIdRef(_value,
                false, _cl.getName(), _fieldName);
    }
    private void addElementInListWithoutIdRef(Object _value) {
        addElementWithoutIdRef(_value,
                false, SerializeXmlObject.LS_CLASS, null);
    }

    private void addElementInMapWithoutIdRef(Object _value,
            boolean _isKey) {
        addElementWithoutIdRef(_value,
                _isKey, SerializeXmlObject.MP_CLASS, null);
    }

    private void addElementInArrayWithoutIdRef(Object _value) {
        if (isPrimitive(_value)) {
            ElementsSerial elt_ = createPrimitive(_value, false, null);
            getComponents().add(elt_);
        } else {
            TemplateSerial elt_ = createComposite(_value, false, null);
            if (!SerializeXmlObject.isCheckReferences()) {
                getNews().add(elt_);
                getComponents().add(elt_);
                return;
            }
            SerialList<TemplateSerial> records_ = ElementsSerial.findSerialisableInList(_value, getAllComposites());
            if (records_.isEmpty()) {
                getNews().add(elt_);
                getAllComposites().add(elt_);
                getComponents().add(elt_);
            } else {
                throw new RefException();
            }
        }
    }

    private void addElementInArray(Object _value) {
        if (isPrimitive(_value)) {
            ElementsSerial elt_ = createPrimitive(_value, false, null);
            getComponents().add(elt_);
        } else {
            TemplateSerial elt_ = createComposite(_value, false, null);
//            if (!SerializeXmlObject.isCheckReferences()) {
//                getNews().add(elt_);
//                getComponents().add(elt_);
//                return;
//            }
            SerialList<TemplateSerial> records_ = ElementsSerial.findSerialisableInList(_value, getAllComposites());
            if (records_.isEmpty()) {
                getNews().add(elt_);
                getAllComposites().add(elt_);
                getComponents().add(elt_);
            } else {
                long id_ = getId();
                long idRecord_;
                if (records_.first().getId() == null) {
                    records_.first().setId(id_);
                    idRecord_ = id_;
                    id_++;
                    setId(id_);
                } else {
                    idRecord_ = records_.first().getId();
                }
                elt_.setRef(idRecord_);
                getAllComposites().add(elt_);
                getComponents().add(elt_);


                //throw new Exception();
            }
        }
    }
    /**
    @param _value
    @return
    @throws RuntimeException
    */
    private void addElementWithoutIdRef(Object _value,
            boolean _isKey, String _cl, String _fieldName) {
        if (isPrimitive(_value)) {
            ElementsSerial elt_ = createPrimitive(_value, _isKey, _cl, _fieldName);
            getComponents().add(elt_);
        } else {
            TemplateSerial elt_ = createComposite(_value, _isKey, _cl, _fieldName);
//            if (_isComparator) {
//                getAllImplicitComparators().add(elt_);
//            }
            if (!SerializeXmlObject.isCheckReferences()) {
                getNews().add(elt_);
                getComponents().add(elt_);
                return;
            }
            SerialList<TemplateSerial> records_ = ElementsSerial.findSerialisableInList(_value, getAllComposites());
            if (records_.isEmpty()) {
                getNews().add(elt_);
                getAllComposites().add(elt_);
                getComponents().add(elt_);
            } else {
                throw new RefException();
            }
        }
    }

    /**
    @param _value
    @return
    @throws RuntimeException
    */
    private void checkNullPointerWithoutIdRef(Object _value,
            boolean _isKey, String _cl, String _fieldName, boolean _isNullable) {
        if (_value == null && !_isNullable) {
            throw new NullSerialException();
        }
        if (!isPrimitive(_value)) {
            TemplateSerial elt_;
            if (_cl == null) {
                elt_ = createComposite(_value, _isKey, _fieldName);
            } else {
                elt_ = createComposite(_value, _isKey, _cl, _fieldName);
            }
//            if (_isComparator) {
//                getAllImplicitComparators().add(elt_);
//            }
            if (!SerializeXmlObject.isCheckReferences()) {
                getNews().add(elt_);
                return;
            }
            SerialList<TemplateSerial> records_ = ElementsSerial.findSerialisableInList(_value, getAllComposites());
            if (records_.isEmpty()) {
                getNews().add(elt_);
                getAllComposites().add(elt_);
            } else {
                throw new RefException();
            }
        }
    }

    /**
    @param _value
    @return
    @throws RuntimeException
    */
    private void setUnmodifiedWithoutIdRef(Object _value,
            boolean _isKey, String _cl, String _fieldName) {
        if (_value == null) {
            return;
        }
        if (!isPrimitive(_value)) {
            if (!(_value instanceof Viewable)) {
                return;
            }
            ((Viewable)_value).setUnmodified();
            TemplateSerial elt_ = createComposite(_value, _isKey, _cl, _fieldName);
//            if (_isComparator) {
//                getAllImplicitComparators().add(elt_);
//            }
            if (!SerializeXmlObject.isCheckReferences()) {
                getNews().add(elt_);
                return;
            }
            SerialList<TemplateSerial> records_ = ElementsSerial.findSerialisableInList(_value, getAllComposites());
            if (records_.isEmpty()) {
                getNews().add(elt_);
                getAllComposites().add(elt_);
            } else {
                throw new RefException();
            }
        }
    }

    /**
    @param _value
    @return
    */
    private void setUnmodified(Object _value,
            boolean _isKey, String _cl, String _fieldName) {
        if (_value == null) {
            return;
        }
        if (!isPrimitive(_value)) {
            if (!(_value instanceof Viewable)) {
                return;
            }
            ((Viewable)_value).setUnmodified();
            TemplateSerial elt_ = createComposite(_value, _isKey, _cl, _fieldName);
//            if (_isComparator) {
//                getAllImplicitComparators().add(elt_);
//            }
            if (!SerializeXmlObject.isCheckReferences()) {
                getNews().add(elt_);
                return;
            }
            SerialList<TemplateSerial> records_ = ElementsSerial.findSerialisableInList(_value, getAllComposites());
            if (records_.isEmpty()) {
                getNews().add(elt_);
                getAllComposites().add(elt_);
            } else {
                long idRecord_;
                if (records_.first().getId() == null) {
                    long id_ = getId();
                    records_.first().setId(id_);
                    idRecord_ = id_;
                    id_++;
                    setId(id_);
                } else {
                    idRecord_ = records_.first().getId();
                }
                elt_.setRef(idRecord_);
                getAllComposites().add(elt_);
                getComponents().add(elt_);
            }
        }
    }

    /**
    @param _value
    @return
    @throws RuntimeException
    */
    private boolean isUnmodifiedWithoutIdRef(Object _value,
            boolean _isKey, String _cl, String _fieldName) {
        if (_value == null) {
            return true;
        }
        if (!isPrimitive(_value)) {
            if (!(_value instanceof Viewable)) {
                return true;
            }
            if (((Viewable)_value).isModified()) {
                return false;
            }
            TemplateSerial elt_ = createComposite(_value, _isKey, _cl, _fieldName);
//            if (_isComparator) {
//                getAllImplicitComparators().add(elt_);
//            }
            if (!SerializeXmlObject.isCheckReferences()) {
                getNews().add(elt_);
                return true;
            }
            SerialList<TemplateSerial> records_ = ElementsSerial.findSerialisableInList(_value, getAllComposites());
            if (records_.isEmpty()) {
                getNews().add(elt_);
                getAllComposites().add(elt_);
            } else {
                throw new RefException();
            }
        }
        return true;
    }

    /**
    @param _value
    @return
    @throws RuntimeException
    */
    private boolean isUnmodified(Object _value,
            boolean _isKey, String _cl, String _fieldName) {
        if (_value == null) {
            return true;
        }
        if (!isPrimitive(_value)) {
            if (!(_value instanceof Viewable)) {
                return true;
            }
            if (((Viewable)_value).isModified()) {
                return false;
            }
            TemplateSerial elt_ = createComposite(_value, _isKey, _cl, _fieldName);
//            if (_isComparator) {
//                getAllImplicitComparators().add(elt_);
//            }
            if (!SerializeXmlObject.isCheckReferences()) {
                getNews().add(elt_);
                return true;
            }
            SerialList<TemplateSerial> records_ = ElementsSerial.findSerialisableInList(_value, getAllComposites());
            if (records_.isEmpty()) {
                getNews().add(elt_);
                getAllComposites().add(elt_);
            } else {
                long idRecord_;
                if (records_.first().getId() == null) {
                    long id_ = getId();
                    records_.first().setId(id_);
                    idRecord_ = id_;
                    id_++;
                    setId(id_);
                } else {
                    idRecord_ = records_.first().getId();
                }
                elt_.setRef(idRecord_);
                getAllComposites().add(elt_);
                getComponents().add(elt_);
            }
        }
        return true;
    }

    /**
    @param _value
    @return
    */
    private void addElement(Object _value,
            boolean _isKey, String _cl, String _fieldName) {
        long id_ = getId();
        if (isPrimitive(_value)) {
            ElementsSerial elt_ = createPrimitive(_value, _isKey, _cl, _fieldName);
            getComponents().add(elt_);
        } else {
            TemplateSerial elt_ = createComposite(_value, _isKey, _cl, _fieldName);
//            if (_isComparator) {
//                getAllImplicitComparators().add(elt_);
//            }
            SerialList<TemplateSerial> records_ = ElementsSerial.findSerialisableInList(_value, getAllComposites());
            if (records_.isEmpty()) {
                getNews().add(elt_);
                getAllComposites().add(elt_);
                getComponents().add(elt_);
            } else {
                long idRecord_;
                if (records_.first().getId() == null) {
                    records_.first().setId(id_);
                    idRecord_ = id_;
                    id_++;
                    setId(id_);
                } else {
                    idRecord_ = records_.first().getId();
                }
                elt_.setRef(idRecord_);
                getAllComposites().add(elt_);
                getComponents().add(elt_);
            }
        }
    }

    static ElementsSerial createPrimitive(Object _value) {
        return createPrimitive(_value, false, null);
    }

    private static ElementsSerial createPrimitive(
            Object _value, boolean _isKey, String _field) {
        if (_value == null) {
            NullSerial null_ = new NullSerial();
            null_.setField(_field);
            null_.setKeyOfMap(_isKey);
            return null_;
        }
        Method method_ = ConverterMethod.getToStringMethod(_value.getClass());
        if (method_ != null) {
            StringObjectSerial number_ = new StringObjectSerial(_value);
            number_.setKeyOfMap(_isKey);
            number_.setField(_field);
            return number_;
        }
        if (_value instanceof Number) {
            NumberSerial number_ = new NumberSerial((Number) _value);
            number_.setKeyOfMap(_isKey);
            number_.setField(_field);
            return number_;
        }
        if (_value instanceof String) {
            StringSerial number_ = new StringSerial((String) _value);
            number_.setKeyOfMap(_isKey);
            number_.setField(_field);
            return number_;
        }
        if (_value instanceof Character) {
            CharacterSerial number_ = new CharacterSerial((Character) _value);
            number_.setKeyOfMap(_isKey);
            number_.setField(_field);
            return number_;
        }
        if (_value instanceof Boolean) {
            BooleanSerial boolean_ = new BooleanSerial((Boolean) _value);
            boolean_.setKeyOfMap(_isKey);
            boolean_.setField(_field);
            return boolean_;
        }
        if (_value instanceof Enum) {
            EnumSerial enum_ = new EnumSerial((Enum<?>) _value);
            enum_.setKeyOfMap(_isKey);
            enum_.setField(_field);
            return enum_;
        }
        return null;
    }

    private static ElementsSerial createPrimitive(
            Object _value, boolean _isKey, String _clName, String _field) {
        ElementsSerial elt_ = createPrimitive(_value, _isKey, _field);
        elt_.setClassName(_clName);
        return elt_;
    }

    private static TemplateSerial createComposite(
            Object _value, boolean _isKey, String _field) {
//        if (_value instanceof Comparator && _isComparator) {
//            ComparatorSerial serializableField_ = new ComparatorSerial((Comparator<?>)_value);
//            serializableField_.setKeyOfMap(_isKey);
//            serializableField_.setField(_field);
//            return serializableField_;
//        }
        if (_value.getClass().isArray()) {
            ArraySerial serializableField_ = new ArraySerial(_value);
            serializableField_.setKeyOfMap(_isKey);
            serializableField_.setField(_field);
            return serializableField_;
        }
        ObjectSerial serializableField_ = new ObjectSerial(_value);
        serializableField_.setKeyOfMap(_isKey);
        serializableField_.setField(_field);
        return serializableField_;

    }

    private static TemplateSerial createComposite(
            Object _value, boolean _isKey, String _clName, String _field) {
        TemplateSerial temp_ = createComposite(_value, _isKey, _field);
        temp_.setClassName(_clName);
        return temp_;

    }

    private static boolean isPrimitive(Object _o) {
        if (_o == null) {
            return true;
        }
        if (_o instanceof Number) {
            return true;
        }
        if (_o instanceof Character) {
            return true;
        }
        if (_o instanceof String) {
            return true;
        }
        if (_o instanceof Boolean) {
            return true;
        }
        if (_o instanceof Enum) {
            return true;
        }
        Method method_ = ConverterMethod.getToStringMethod(_o.getClass());
        if (method_ != null) {
            return true;
        }
        return false;
    }

    SerialList<ElementsSerial> getComponentComposite(TemplateSerial _temp) {
        for (TemplateSerialValue<SerialList<ElementsSerial>> e: componentComposite.getElements()) {
            if (ElementsSerial.sameValue(e.getTemplate(), _temp)) {
                return e.getValue();
            }
        }
        return null;
//        return componentComposite.getVal(_temp);
    }

    private SerialList<TemplateSerial> getAllComposites() {
        return allComposites;
    }

    private SerialList<TemplateSerial> getNews() {
        return news;
    }

    private void setNews(SerialList<TemplateSerial> _news) {
        news = _news;
    }

    private SerialList<TemplateSerial> getCurrents() {
        return currents;
    }

    private void setCurrents(SerialList<TemplateSerial> _currents) {
        currents = _currents;
    }

//    private SerialList<ElementsSerial> getAllImplicitComparators() {
//        return allImplicitComparators;
//    }

    private SerialList<ElementsSerial> getComponents() {
        return components;
    }

    private void setComponents(SerialList<ElementsSerial> _components) {
        components = _components;
    }

    private long getId() {
        return id;
    }

    private void setId(long _id) {
        id = _id;
    }

}
