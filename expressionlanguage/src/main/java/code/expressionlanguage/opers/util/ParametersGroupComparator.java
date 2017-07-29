package code.expressionlanguage.opers.util;
import java.util.Comparator;

import code.expressionlanguage.PrimitiveTypeUtil;
import code.util.CustList;

public final class ParametersGroupComparator<T extends Parametrable> implements Comparator<T> {

    private ArgumentsGroup selectedClasses;

    public ParametersGroupComparator(ArgumentsGroup _selectedClasses) {
        selectedClasses = _selectedClasses;
    }

    @Override
    public int compare(T _o1, T _o2) {
        int len_ = _o1.getParameters().size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            ClassArgumentMatching selected_ = selectedClasses.get(i);
            ClassMatching one_ = _o1.getParameters().get(i);
            ClassMatching two_ = _o2.getParameters().get(i);
            if (one_.matchClass(two_)) {
                continue;
            }
            if (selected_.isVariable()) {
                if (one_.isAssignableFrom(two_, selectedClasses.getClasses())) {
                    return CustList.SWAP_SORT;
                }
                if (two_.isAssignableFrom(one_, selectedClasses.getClasses())) {
                    return CustList.NO_SWAP_SORT;
                }
                _o1.getParameters().setError(true);
                _o2.getParameters().setError(true);
                return CustList.NO_SWAP_SORT;
            }
            if (selected_.isPrimitive()) {
                if (selected_.matchClass(PrimitiveTypeUtil.PRIM_BOOLEAN)) {
                    if (one_.matchClass(PrimitiveTypeUtil.PRIM_BOOLEAN)) {
                        return CustList.NO_SWAP_SORT;
                    }
                    if (two_.matchClass(PrimitiveTypeUtil.PRIM_BOOLEAN)) {
                        return CustList.SWAP_SORT;
                    }
                    if (one_.matchClass(Boolean.class)) {
                        return CustList.NO_SWAP_SORT;
                    }
                    if (two_.matchClass(Boolean.class)) {
                        return CustList.SWAP_SORT;
                    }
                    return CustList.NO_SWAP_SORT;
                }
                if (one_.isPrimitive() && two_.isPrimitive()) {
                    int orderOne_ = PrimitiveTypeUtil.getOrderClass(one_);
                    int orderTwo_ = PrimitiveTypeUtil.getOrderClass(two_);
                    if (orderOne_ > orderTwo_) {
                        return CustList.SWAP_SORT;
                    }
                    if (orderTwo_ > orderOne_) {
                        return CustList.NO_SWAP_SORT;
                    }
                    continue;
                }
                if (one_.isPrimitive() && !two_.isPrimitive()) {
                    return CustList.NO_SWAP_SORT;
                }
                if (!one_.isPrimitive() && two_.isPrimitive()) {
                    return CustList.SWAP_SORT;
                }
                ClassArgumentMatching clMath_ = PrimitiveTypeUtil.toWrapper(selected_, true);
                if (clMath_.isAssignableFrom(one_, selectedClasses.getClasses()) && !clMath_.isAssignableFrom(two_, selectedClasses.getClasses())) {
                    return CustList.NO_SWAP_SORT;
                }
                if (clMath_.isAssignableFrom(two_, selectedClasses.getClasses()) && !clMath_.isAssignableFrom(one_, selectedClasses.getClasses())) {
                    return CustList.SWAP_SORT;
                }
                _o1.getParameters().setError(true);
                _o2.getParameters().setError(true);
                return CustList.NO_SWAP_SORT;
            }

            if (selected_.matchClass(Boolean.class)) {
                if (one_.matchClass(Boolean.class)) {
                    return CustList.NO_SWAP_SORT;
                }
                if (two_.matchClass(Boolean.class)) {
                    return CustList.SWAP_SORT;
                }
                if (one_.matchClass(PrimitiveTypeUtil.PRIM_BOOLEAN)) {
                    return CustList.NO_SWAP_SORT;
                }
                if (two_.matchClass(PrimitiveTypeUtil.PRIM_BOOLEAN)) {
                    return CustList.SWAP_SORT;
                }
                return CustList.NO_SWAP_SORT;
            }
            ClassArgumentMatching clMatch_ = PrimitiveTypeUtil.toPrimitive(selected_, true);
            if (clMatch_.isPrimitive()) {
                if (one_.isPrimitive() && two_.isPrimitive()) {
                    int orderOne_ = PrimitiveTypeUtil.getOrderClass(one_);
                    int orderTwo_ = PrimitiveTypeUtil.getOrderClass(two_);
                    if (orderOne_ > orderTwo_) {
                        return CustList.SWAP_SORT;
                    }
                    if (orderTwo_ > orderOne_) {
                        return CustList.NO_SWAP_SORT;
                    }
                    continue;
                }
                if (one_.isPrimitive() && !two_.isPrimitive()) {
                    return CustList.SWAP_SORT;
                }
                if (!one_.isPrimitive() && two_.isPrimitive()) {
                    return CustList.NO_SWAP_SORT;
                }
                if (clMatch_.isAssignableFrom(one_, selectedClasses.getClasses()) && !clMatch_.isAssignableFrom(two_, selectedClasses.getClasses())) {
                    return CustList.NO_SWAP_SORT;
                }
                if (clMatch_.isAssignableFrom(two_, selectedClasses.getClasses()) && !clMatch_.isAssignableFrom(one_, selectedClasses.getClasses())) {
                    return CustList.SWAP_SORT;
                }
                _o1.getParameters().setError(true);
                _o2.getParameters().setError(true);
                return CustList.NO_SWAP_SORT;
            }
            if (one_.isAssignableFrom(two_, selectedClasses.getClasses())) {
                return CustList.SWAP_SORT;
            }
            if (two_.isAssignableFrom(one_, selectedClasses.getClasses())) {
                return CustList.NO_SWAP_SORT;
            }
//            if (!one_.getClazz().isInterface()) {
//                if (!two_.getClazz().isInterface()) {
//                    _o1.getParameters().setError(true);
//                    _o2.getParameters().setError(true);
//                    return CustList.EQ_CMP;
//                }
//            }
            _o1.getParameters().setError(true);
            _o2.getParameters().setError(true);
            return CustList.NO_SWAP_SORT;
        }
        return CustList.NO_SWAP_SORT;
    }

}
