package code.expressionlanguage.classes;
import code.expressionlanguage.AccEl;

public class BeanSix {

    @AccEl
    private EnumNumber myEnumOne;

    @AccEl
    private EnumNumber myEnumTwo = EnumNumber.ONE;

    @AccEl
    private EnumNumber myEnumThree = EnumNumber.FOUR;

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
