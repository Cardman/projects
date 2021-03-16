package code.maths.litteraladv;

import code.maths.Rate;
import code.maths.matrix.Polynom;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;

final class MaComparePolynom {
    private MaComparePolynom(){}
    static CustList<Polynom> sorted(CustList<Polynom> _list) {
        CustList<Polynom> sorted_ = new CustList<Polynom>(new CollCapacity(_list.size()));
        for (Polynom p: _list) {
            int size_ = sorted_.size();
            int indexInsert_ = size_;
            for (int i = 0; i < size_; i++) {
                Polynom stored_ = sorted_.get(i);
                int res_ = compare(p, stored_);
                if (res_ <= SortConstants.EQ_CMP) {
                    indexInsert_ = i;
                    break;
                }
            }
            sorted_.add(indexInsert_,p);
        }
        return sorted_;
    }
    static int compare(Polynom _one,Polynom _two) {
        CustList<Rate> numbersOne_ = _one.getNumbers();
        CustList<Rate> numbersTwo_ = _two.getNumbers();
        int len_ = numbersOne_.size();
        int res_ = NumberUtil.compareLg(len_,numbersTwo_.size());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        for (int i = 0; i < len_; i++) {
            Rate nbOne_ = numbersOne_.get(i);
            Rate nbTwo_ = numbersTwo_.get(i);
            int cmp_ = nbOne_.cmp(nbTwo_);
            if (cmp_ != SortConstants.EQ_CMP) {
                return cmp_;
            }
        }
        return SortConstants.EQ_CMP;
    }
}
