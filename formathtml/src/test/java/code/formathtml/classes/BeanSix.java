package code.formathtml.classes;
import code.bean.Accessible;
import code.bean.Bean;

public class BeanSix extends Bean {

    @Accessible
    private EnumNumber myEnumOne;

    @Accessible
    private EnumNumber myEnumTwo = EnumNumber.ONE;

    @Accessible
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
}
