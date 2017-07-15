package code.util.classestest;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Cmp;
import code.util.pagination.Sorting;

public final class SortingPerson implements Sorting,Cmp<SortingPerson> {

    private int index;

    private String firstName;

    private String lastName;

    private Sex sex;

    private int age;

    @Override
    public boolean eq(SortingPerson _g) {
        return cmp(_g) == CustList.EQ_CMP;
    }

    @Override
    public int cmp(SortingPerson _o2) {
        return Numbers.compare(getIndex(), _o2.getIndex());
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int _index) {
        index = _index;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String _firstName) {
        firstName = _firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String _lastName) {
        lastName = _lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex _sex) {
        sex = _sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int _age) {
        age = _age;
    }
}
