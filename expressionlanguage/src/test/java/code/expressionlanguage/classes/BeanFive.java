package code.expressionlanguage.classes;
import code.util.NatStringTreeMap;
import code.util.Numbers;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.comparators.ComparatorEnum;


public class BeanFive {


    private Composite composite = new Composite();


    private TreeMap<EnumNumber, String> translations = new TreeMap<EnumNumber, String>(new ComparatorEnum<EnumNumber>());


    private NatStringTreeMap<Numbers<Integer>> numbers = new NatStringTreeMap<Numbers<Integer>>();


    private EnumNumber chosenNumber = EnumNumber.ONE;


    private StringMap<Integer> map = new StringMap<Integer>();


}
