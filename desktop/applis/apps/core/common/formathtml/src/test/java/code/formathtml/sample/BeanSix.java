package code.formathtml.sample;
import code.bean.Bean;

public class BeanSix extends Bean {

    private EnumNumber myEnumOne;

    private EnumNumber myEnumTwo = EnumNumber.ONE;

    private EnumNumber myEnumThree = EnumNumber.FOUR;

    public BeanSix() {
        setClassName("code.formathtml.classes.BeanSix");
    }

    public EnumNumber getMyEnumOne() {
        return myEnumOne;
    }

    public EnumNumber getMyEnumTwo() {
        return myEnumTwo;
    }

    public EnumNumber getMyEnumThree() {
        return myEnumThree;
    }

    public void setMyEnumOne(EnumNumber _myEnumOne) {
        myEnumOne = _myEnumOne;
    }

    public void setMyEnumTwo(EnumNumber _myEnumTwo) {
        myEnumTwo = _myEnumTwo;
    }

    public void setMyEnumThree(EnumNumber _myEnumThree) {
        myEnumThree = _myEnumThree;
    }

    @Override
    public void beforeDisplaying() {

    }
}
