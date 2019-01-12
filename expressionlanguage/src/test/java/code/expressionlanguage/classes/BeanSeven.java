package code.expressionlanguage.classes;
import code.util.CustList;
import code.util.NatStringTreeMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.comparators.ComparatorEnum;

@SuppressWarnings("static-method")
public class BeanSeven {


    private Composite composite = new Composite();


    private TreeMap<EnumNumber, String> translations = new TreeMap<EnumNumber, String>(new ComparatorEnum<EnumNumber>());


    private NatStringTreeMap<Numbers<Integer>> numbers = new NatStringTreeMap<Numbers<Integer>>();


    private NatStringTreeMap< Integer> tree = new NatStringTreeMap< Integer>();


    private StringMap<Integer> map = new StringMap<Integer>();

    private CustList<Composite> composites = new CustList<Composite>();

    private StringList strings = new StringList();


    private int[] arrayInt = new int[2];
    private int[][] arrayInts = new int[2][2];


}
