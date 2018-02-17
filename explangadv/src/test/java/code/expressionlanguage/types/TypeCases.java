package code.expressionlanguage.types;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import code.util.CustList;
import code.util.IdMap;

class TypeCases<E> {

    public static final String SIMPLE_CLASS = null;
    public static final String[] SIMPLE_ARRAY = null;
    public static final CustList<String> TEMPLATE = null;
    public static final IdMap<String,String> TEMPLATE_DOUBLE = null;
    public static final IdMap<IdMap<String,String>,String> TEMPLATE_COMPLEX = null;
    public static final IdMap<String[],String> TEMPLATE_COMPLEX_ARR = null;
    public static final CustList<String[]> TEMPLATE_ARRAY = null;
    public static final CustList<String>[] ARRAY_TEMPLATE = null;
    public static final CustList<CustList<String>> TEMPLATE_DOUBLE_END = null;
    public static final int INT_CST = 0;
    public static final int[] INT_ARR_CST = null;
    public static final CustList<int[]> INT_GENE_ARR_CST = null;
    public E returnMethod() {
        return null;
    }
    public E[] returnArrMethod() {
        return null;
    }
    public CustList<E> returnGeneMethod() {
        return null;
    }
    public CustList<E[]> returnGeneArrMethod() {
        return null;
    }
    public static Field getField(String _name) {
        try {
            return TypeCases.class.getField(_name);
        } catch (Throwable _0) {
            return null;
        }
    }
    public static Method getMethod(String _name) {
        try {
            return TypeCases.class.getMethod(_name);
        } catch (Throwable _0) {
            return null;
        }
    }
}
