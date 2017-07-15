package code.maths.montecarlo;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.AbstractList;

import code.serialize.ConverterMethod;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.annot.RwXml;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;

public final class MonteCarloUtil {

    private MonteCarloUtil() {
    }

    public static void deleteZeroEventsDeeply(Object _obj, boolean _copying) {
        boolean modif_ = true;
        IdList<Object> visitedObjects_ = new IdList<Object>();
        IdList<Object> newObjects_ = new IdList<Object>();
        IdList<Object> currentObjects_ = new IdList<Object>();
        visitedObjects_.add(_obj);
        currentObjects_.add(_obj);
        while(modif_) {
            modif_ = false;
            newObjects_ = new IdList<Object>();
            for (Object e: currentObjects_) {
                if (e instanceof Listable) {
                    for (Object o: (Listable<?>)e) {
                        if (isPrimitive(o)) {
                            continue;
                        }
                        if (contains(visitedObjects_,o)) {
                            continue;
                        }
                        newObjects_.add(o);
                    }
                }
                if (e instanceof ListableEntries) {
//                    CustList<Object> keys_ = new CustList<>();
                    for (EntryCust<?, ?> o: ((ListableEntries<?,?>)e).entryList()) {
                        Object key_ = o.getKey();
                        if (!isPrimitive(key_)) {
                            if (!contains(visitedObjects_,key_)) {
                                newObjects_.add(key_);
                            }
                        }
                        Object value_ = o.getValue();
                        if (!isPrimitive(value_)) {
                            if (!contains(visitedObjects_,value_)) {
                                newObjects_.add(value_);
                            }
                        }
                    }
//                    for (Object o: keys_) {
//                        if (isPrimitive(o)) {
//                            continue;
//                        }
//                        if (contains(visitedObjects_,o)) {
//                            continue;
//                        }
//                        newObjects_.add(o);
//                    }
//                    for (Object o: keys_) {
//                        Object value_ = ((java.util.Map<?,?>)e).get(o);
//                        if (isPrimitive(value_)) {
//                            continue;
//                        }
//                        if (contains(visitedObjects_,value_)) {
//                            continue;
//                        }
//                        newObjects_.add(value_);
//                    }
                }
                if (e.getClass().isArray()) {
                    int length_ = Array.getLength(e);
                    for (int i = CustList.FIRST_INDEX; i < length_; i++) {
                        Object value_ = Array.get(e, i);
                        if (isPrimitive(value_)) {
                            continue;
                        }
                        if (contains(visitedObjects_,value_)) {
                            continue;
                        }
                        newObjects_.add(value_);
                    }
                }
                Class<?> cl_ = e.getClass();
                while (cl_ != Object.class) {
                    if (cl_.getSuperclass() == AbstractList.class) {
                        break;
                    }
//                    if (cl_.getSuperclass() == AbstractMap.class) {
//                        break;
//                    }
//                    try {
                    for (Field f: cl_.getDeclaredFields()) {
                        if (Modifier.isTransient(f.getModifiers())) {
                            if (!_copying) {
                                continue;
                            }
                        }
                        if (Modifier.isStatic(f.getModifiers())) {
                            continue;
                        }
                        try {
                            f.setAccessible(cl_.getAnnotation(RwXml.class)!=null);
                            Object value_ = f.get(e);
                            if (isPrimitive(value_)) {
                                continue;
                            }
                            if (contains(visitedObjects_,value_)) {
                                continue;
                            }
                            newObjects_.add(value_);
                        } catch (IllegalAccessException _0) {
                        } catch (RuntimeException _0) {
                        } catch (Error _0) {
//                        } catch (Exception _0) {
//                            _0.printStackTrace();
                        }
                    }
//                    } catch (Exception _0) {
//                        _0.printStackTrace();
//                    }
                    cl_ = cl_.getSuperclass();
                }
            }
            if(!newObjects_.isEmpty()) {
                modif_ = true;
                currentObjects_ = new IdList<Object>(newObjects_);
                visitedObjects_.addAllElts(newObjects_);
            }
        }
        for (Object o: visitedObjects_) {
            if (!(o instanceof AbMonteCarlo<?>)) {
                continue;
            }
            ((AbMonteCarlo<?>)o).deleteZeroEvents();
        }
    }


    static boolean isPrimitive(Object _o) {
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
        Method m_ = ConverterMethod.getFromStringMethod(_o.getClass());
        if (m_ != null) {
            return true;
        }
        return false;
    }

    static boolean contains(IdList<Object> _visitedObjects, Object _object) {
        return _visitedObjects.containsObj(_object);
    }
}
