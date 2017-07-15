package code.util.classestest;
import code.util.CustList;
import code.util.EnumMap;
import code.util.Numbers;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.pagination.EnumFieldComparator;
import code.util.pagination.FieldComparator;
import code.util.pagination.Pagination;

public class PaginationPerson extends Pagination<SortingPerson, Person, CriteriaForSearchingPerson> {

    private FieldComparator<Integer> cmpAge = new FieldComparator<Integer>();

    private FieldComparator<String> cmpFirstName = new FieldComparator<String>();

    private FieldComparator<String> cmpLastName = new FieldComparator<String>();

    private EnumFieldComparator<Sex> cmpSex = new EnumFieldComparator<Sex>();

    private final int nbComparators = 4;

    private TreeMap<SortingPerson, Person> people = new TreeMap<SortingPerson, Person>(new ComparatorPersonIndex());

    private CustList<SortingPerson> rendered = new CustList<SortingPerson>();

    public PaginationPerson() {
        super(new CriteriaForSearchingPerson());
    }

//    @Override
    public void search(CustList<Person> _list) {
        people.clear();
        for (int i = CustList.FIRST_INDEX; i < _list.size(); i++) {
            Person person_ = _list.get(i);
            if (!match(person_)) {
                continue;
            }
            SortingPerson s_ = new SortingPerson();
            s_.setIndex(i);
            s_.setFirstName(person_.getFirstName());
            s_.setLastName(person_.getLastName());
            s_.setSex(person_.getSex());
            s_.setAge(person_.getAge());
            people.put(s_, person_);
        }
        if (!people.isEmpty()) {
            setNumberPage(CustList.FIRST_INDEX);
        } else {
            setLine(CustList.INDEX_NOT_FOUND_ELT);
            setNumberPage(CustList.INDEX_NOT_FOUND_ELT);
            getRendered().clear();
            return;
        }
        setLine(CustList.INDEX_NOT_FOUND_ELT);
        if (sortable()) {
            sort();
        }
        calculateRendered();
    }

    @Override
    public boolean match(Person _object) {
        if (!getCriteria().matchFirstName(_object.getFirstName())) {
            return false;
        }
        if (!getCriteria().matchLastName(_object.getLastName())) {
            return false;
        }
        if (!getCriteria().matchAge(_object.getAge())) {
            return false;
        }
        if (!getCriteria().matchSex(_object.getSex())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sortable() {
        Numbers<Integer> priorities_;
        priorities_ = new Numbers<Integer>();
        if (cmpAge.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpAge.getPriority());
        }
        if (cmpFirstName.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpFirstName.getPriority());
        }
        if (cmpLastName.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpLastName.getPriority());
        }
        if (cmpSex.getPriority() != NO_PRIORITY) {
            priorities_.add(cmpSex.getPriority());
        }
        int size_ = priorities_.size();
        priorities_.removeDuplicates();
        return size_ == priorities_.size();
    }

    @Override
    public void sort() {
        TreeMap<SortingPerson, Person> people_ = new TreeMap<SortingPerson, Person>(new ComparatorPerson(cmpAge, cmpFirstName, cmpLastName, cmpSex, nbComparators));
        people_.putAllTreeMap(people);
        people = people_;
    }

    @Override
    public TreeMap<SortingPerson, Person> getResults() {
        return people;
    }

    @Override
    public CustList<SortingPerson> getRendered() {
        return rendered;
    }

    public void setTranslation(StringMap<EnumMap<Sex,String>> _translations,
            String _language) {
        cmpSex.setTranslations(_translations.getVal(_language));
    }

    public FieldComparator<Integer> getCmpAge() {
        return cmpAge;
    }

    public FieldComparator<String> getCmpFirstName() {
        return cmpFirstName;
    }

    public FieldComparator<String> getCmpLastName() {
        return cmpLastName;
    }

    public EnumFieldComparator<Sex> getCmpSex() {
        return cmpSex;
    }
}
