package code.util.classestest;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Comparing;
import code.util.pagination.EnumFieldComparator;
import code.util.pagination.FieldComparator;
import code.util.pagination.Pagination;

public class ComparatorPerson implements Comparing<SortingPerson> {

    private static final int MIN_PRIORITY = Pagination.MIN_PRIORITY;

    private FieldComparator<Integer> cmpAge = new FieldComparator<Integer>();

    private FieldComparator<String> cmpFirstName = new FieldComparator<String>();

    private FieldComparator<String> cmpLastName = new FieldComparator<String>();

    private EnumFieldComparator<Sex> cmpSex = new EnumFieldComparator<Sex>();

    private final int nbComparators;

    public ComparatorPerson(FieldComparator<Integer> _cmpAge,
            FieldComparator<String> _cmpFirstName,
            FieldComparator<String> _cmpLastName,
            EnumFieldComparator<Sex> _cmpSex,
            int _nbComparators) {
        cmpAge = _cmpAge;
        cmpFirstName = _cmpFirstName;
        cmpLastName = _cmpLastName;
        cmpSex = _cmpSex;
        nbComparators = _nbComparators;
    }

    @Override
    public int compare(SortingPerson _o1, SortingPerson _o2) {
        for (int i = nbComparators; i >= MIN_PRIORITY; i--) {
            if (cmpAge.getPriority() == i) {
                int res_ = cmpAge.compare(_o1.getAge(), _o2.getAge());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpFirstName.getPriority() == i) {
                int res_ = cmpFirstName.compare(_o1.getFirstName(), _o2.getFirstName());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpLastName.getPriority() == i) {
                int res_ = cmpLastName.compare(_o1.getLastName(), _o2.getLastName());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            } else if (cmpSex.getPriority() == i) {
                int res_ = cmpSex.compare(_o1.getSex(), _o2.getSex());
                if (res_ != CustList.EQ_CMP) {
                    return res_;
                }
            }
        }
        return Numbers.compare(_o1.getIndex(), _o2.getIndex());
    }

}
