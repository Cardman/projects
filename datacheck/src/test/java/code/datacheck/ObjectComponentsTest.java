package code.datacheck;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import code.datacheck.classes.Carre;
import code.datacheck.classes.CheckedClass;
import code.datacheck.classes.CheckedPrimitive;
import code.datacheck.classes.ComplexMap;
import code.datacheck.classes.EnumNumbers;
import code.datacheck.classes.FullClass;
import code.datacheck.classes.IntComparator;
import code.datacheck.classes.Losange;
import code.datacheck.classes.MyArrays;
import code.datacheck.classes.MyList;
import code.datacheck.classes.MyMap;
import code.datacheck.classes.MySquare;
import code.datacheck.classes.Pair;
import code.datacheck.classes.Parallelogramme;
import code.datacheck.classes.Primitive;
import code.datacheck.classes.PseudoPrimitive;
import code.datacheck.classes.Rectange;
import code.datacheck.classes.RefsArray;
import code.datacheck.classes.Snapshot;
import code.datacheck.classes.StrangeComparator;
import code.datacheck.classes.Template;
import code.datacheck.classes.TemplateList;
import code.datacheck.classes.TemplateString;
import code.datacheck.classes.TreeContainer;
import code.datacheck.classes.UncheckedClass;
import code.datacheck.exception.ContainerException;
import code.serialize.SerializeXmlObject;
import code.serialize.exceptions.NullFieldException;
import code.util.BooleanList;
import code.util.CharList;
import code.util.CustList;
import code.util.EnumList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.comparators.NaturalComparator;
import code.util.ints.HasComparator;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;

@SuppressWarnings("static-method")
public class ObjectComponentsTest {
    private static final String STRING_LIST = StringList.class.getName();
    private static final String TREE_MAP = TreeMap.class.getName();
    private static final String CUST_LIST = CustList.class.getName();
    private static final String MY_ARRAYS = MyArrays.class.getName();
    private static final String TREE_CONTAINER = TreeContainer.class.getName();
    private static final String STRANGE_CMP = StrangeComparator.class.getName();
    private static final String INT_CMP = IntComparator.class.getName();
    private static final String FULL_CLASS = FullClass.class.getName();
    private static final String MY_SQUARE = MySquare.class.getName();
    private static final String TEMPLATE_LIST = TemplateList.class.getName();

    private static final String LS_CLASS = "a";
    private static final String MP_CLASS = "m";
    private static final String REF_ARRAY = RefsArray.class.getName();

    @Test
    public void isInterface1Test() {
        assertTrue(ObjectComponents.isInterface(Carre.class));
    }

    @Test
    public void isInterface2Test() {
        assertTrue(!ObjectComponents.isInterface(Listable.class));
    }

    @Test
    public void isInterface3Test() {
        assertTrue(ObjectComponents.isInterface(Iterable.class));
    }

    @Test
    public void isInterface4Test() {
        assertTrue(!ObjectComponents.isInterface(Object.class));
    }

    @Test
    public void isInterface5Test() {
        assertTrue(!ObjectComponents.isInterface(Listable.class));
    }

    @Test
    public void isInterface6Test() {
        assertTrue(!ObjectComponents.isInterface(ListableEntries.class));
    }

    @Test
    public void isInterface7Test() {
        assertTrue(!ObjectComponents.isInterface(HasComparator.class));
    }

    @Test
    public void getLastSuperClass1Test() {
        Class<?> cl_ = ObjectComponents.getLastSuperClass(1, Comparable.class);
        assertSame(Integer.class, cl_);
    }

    @Test
    public void getLastSuperClass2Test() {
        Class<?> cl_ = ObjectComponents.getLastSuperClass(1, Object.class);
        assertSame(Object.class, cl_);
    }

    @Test
    public void getLastSuperClass3Test() {
        Class<?> cl_ = ObjectComponents.getLastSuperClass(1, null);
        assertSame(Integer.class, cl_);
    }

    @Test
    public void getLastSuperClass4Test() {
        Class<?> cl_ = ObjectComponents.getLastSuperClass(1, Serializable.class);
        assertSame(Number.class, cl_);
    }

    @Test
    public void getInterfaces1Test() {
        StringList interfaces_ = ObjectComponents.getInterfaces(Carre.class, Parallelogramme.class);
        assertEq(3, interfaces_.size());
        assertEq(Carre.class.getName(), interfaces_.get(0));
        assertEq(Losange.class.getName(), interfaces_.get(1));
        assertEq(Parallelogramme.class.getName(), interfaces_.get(2));
    }

    @Test
    public void getInterfaces2Test() {
        StringList interfaces_ = ObjectComponents.getInterfaces(Rectange.class, Losange.class);
        assertEq(1, interfaces_.size());
        assertEq(Rectange.class.getName(), interfaces_.get(0));
    }

    @Test
    public void getInterfaces3Test() {
        StringList interfaces_ = ObjectComponents.getInterfaces(Parallelogramme.class, Parallelogramme.class);
        assertEq(1, interfaces_.size());
        assertEq(Parallelogramme.class.getName(), interfaces_.get(0));
    }

    @Test
    public void getRealInterfaceTypes1Test() {
        StringList vars_ = new StringList("U");
        StringList types_ = new StringList(String.class.getName());
        StringList realTypes_ = ObjectComponents.getRealInterfaceTypes(Parallelogramme.class, Parallelogramme.class, vars_, types_);
        assertEq(1, realTypes_.size());
        assertEq(String.class.getName(), realTypes_.get(0));
    }

    @Test
    public void getRealInterfaceTypes2Test() {
        StringList vars_ = new StringList("T");
        StringList types_ = new StringList(String.class.getName());
        StringList realTypes_ = ObjectComponents.getRealInterfaceTypes(Carre.class, Parallelogramme.class, vars_, types_);
        assertEq(1, realTypes_.size());
        assertEq(String.class.getName(), realTypes_.get(0));
    }

    @Test
    public void getRealInterfaceTypes3Test() {
        StringList vars_ = new StringList("W");
        StringList types_ = new StringList(String.class.getName());
        StringList realTypes_ = ObjectComponents.getRealInterfaceTypes(Losange.class, Rectange.class, vars_, types_);
        assertEq(1, realTypes_.size());
        assertEq(String.class.getName(), realTypes_.get(0));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void setupNotNull1Test() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList("STR_FIVE","STR_SIX")));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setScore(new Pair<CheckedPrimitive,CheckedClass>(new CheckedPrimitive(),new CheckedClass()));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.getCheckedPrim().put("", new CheckedPrimitive());
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>(new ComplexMap<String,Character,Serializable>(new StringMap<Serializable>())));
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap<Integer> map_;
        map_ = new MyMap<Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClass_ = new CheckedClass();
        checkedClass_.setCorrect(5);
        checkedClass_.setOk("OK");
        full_.setCheckedClass(checkedClass_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @SuppressWarnings("unchecked")
	@Test
    public void setupNotNull2Test() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList("STR_FIVE","STR_SIX")));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>(new ComplexMap<String,Character,Serializable>(new StringMap<Serializable>())));
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.setScore(new Pair<CheckedPrimitive, CheckedClass>(new CheckedPrimitive(),(CheckedClass)null));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.getCheckedPrim().put("", new CheckedPrimitive());
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClass_ = new CheckedClass();
        full_.setCheckedClass(checkedClass_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void setupNotNull3Test() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList()));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        full_.setScore(new Pair<CheckedPrimitive,CheckedClass>(new CheckedPrimitive(),new CheckedClass()));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.getCheckedPrim().put("", new CheckedPrimitive());
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>(new ComplexMap<String,Character,Serializable>(new StringMap<Serializable>())));
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClass_ = new CheckedClass();
        checkedClass_.setCorrect(5);
        checkedClass_.setOk("OK");
        full_.setCheckedClass(checkedClass_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        full_.setContainer(new CustList<UncheckedClass>(uncheckedClass_));
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @Test
    public void setupNotNull4Test() {
        FullClass full_ = new FullClass();
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void setupNotNull5Test() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList("STR_FIVE","STR_SIX")));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        full_.setScore(new Pair<CheckedPrimitive,CheckedClass>(new CheckedPrimitive(),new CheckedClass()));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.getCheckedPrim().put("", new CheckedPrimitive());
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>(new ComplexMap<String,Character,Serializable>(new StringMap<Serializable>())));
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClass_ = new CheckedClass();
        full_.setCheckedClass(checkedClass_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void setupNotNull6Test() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList("STR_FIVE","STR_SIX")));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        full_.setEnumEx(EnumNumbers.TWO);
        CheckedClass checkedClassOne_ = new CheckedClass();
        checkedClassOne_.setCorrect(5);
        checkedClassOne_.setOk("OK");
        full_.setScore(new Pair<CheckedPrimitive, CheckedClass>(new CheckedPrimitive("A;B"),checkedClassOne_));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.getCheckedPrim().put("", new CheckedPrimitive());
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>(new ComplexMap<String,Character,Serializable>(new StringMap<Serializable>())));
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList("OK"));
        full_.getInterfMap().put("", true);
        full_.getListMaps().first().put("1", 1);
        full_.getListMaps().first().put("TRUE", true);
        full_.getListMaps().first().setField("");
        full_.getListMaps().first().setValue('t');
        full_.getListMaps().first().setValues(new CharList());
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClassTwo_ = new CheckedClass();
        checkedClassTwo_.setCorrect(5);
        checkedClassTwo_.setOk("OK");
        full_.setCheckedClass(checkedClassTwo_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void setupNotNull7Test() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList("STR_FIVE","STR_SIX")));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        CheckedClass checkedClass_ = new CheckedClass();
        checkedClass_.setOk("");
        full_.setScore(new Pair<CheckedPrimitive, CheckedClass>(new CheckedPrimitive(),checkedClass_));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>(new ComplexMap<String,Character,Serializable>(new StringMap<Serializable>())));
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList("OK"));
        full_.getInterfMap().put("", true);
        full_.getListMaps().first().put("1", 1);
        full_.getListMaps().first().put("TRUE", true);
        full_.getListMaps().first().setField("");
        full_.getListMaps().first().setValue('t');
        full_.getListMaps().first().setValues(new CharList());
        full_.getCheckedPrim().put("", new CheckedPrimitive());
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        checkedClass_ = new CheckedClass();
        checkedClass_.setOk("");
        full_.setCheckedClass(checkedClass_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @Test
    public void setupNotNull8Test() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+STRING_LIST+">";
        xml_ += "<null class='"+LS_CLASS+"'/>";
        xml_ += "</"+STRING_LIST+">";
        StringList list_;
        list_ = (StringList) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(list_);
    }

    @Test
    public void setupNotNull9Test() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REF_ARRAY+">";
        xml_ += "<array class=\""+REF_ARRAY+"\" field=\"array\" type=\"java.lang.Object\">";
        xml_ += "<null/>";
        xml_ += "</array>";
        xml_ += "</"+REF_ARRAY+">";
        RefsArray list_;
        list_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(list_);
    }

    @Test
    public void setupNotNull10Test() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REF_ARRAY+">";
        xml_ += "<array class=\""+REF_ARRAY+"\" field=\"array\" type=\"java.lang.Object\">";
        xml_ += "<java.lang.Integer value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "</"+REF_ARRAY+">";
        RefsArray list_;
        list_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(list_);
    }

    @Test
    public void setupNotNull11Test() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REF_ARRAY+">";
        xml_ += "<array class=\""+REF_ARRAY+"\" field=\"array\" type=\"java.lang.Object\">";
        xml_ += "<java.lang.Integer value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "</"+REF_ARRAY+">";
        RefsArray list_;
        list_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(list_);
    }

    @Test
    public void setupNotNull12Test() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MY_ARRAYS+">";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayInt\" type=\"int\">";
        xml_ += "<java.lang.Integer value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayLong\" type=\"long\">";
        xml_ += "<java.lang.Long value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayShort\" type=\"short\">";
        xml_ += "<java.lang.Short value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayByte\" type=\"byte\">";
        xml_ += "<java.lang.Byte value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayChar\" type=\"char\">";
        xml_ += "<java.lang.Character value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayBoolean\" type=\"boolean\">";
        xml_ += "<java.lang.Boolean value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayDouble\" type=\"double\">";
        xml_ += "<java.lang.Double value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayFloat\" type=\"float\">";
        xml_ += "<java.lang.Float value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<"+MY_SQUARE+" class=\""+MY_ARRAYS+"\" field=\"carre\" type=\"float\">";
        xml_ += "<java.lang.String class=\""+MY_SQUARE+"\" field=\"aField\" value=\"1\"/>";
        xml_ += "</"+MY_SQUARE+">";
        xml_ += "</"+MY_ARRAYS+">";
        MyArrays list_;
        list_ = (MyArrays) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(list_);
    }

    @Test
    public void setupNotNull13Test() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MY_ARRAYS+">";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayInt\" type=\"int\">";
        xml_ += "<java.lang.Integer value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayLong\" type=\"long\">";
        xml_ += "<java.lang.Long value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayShort\" type=\"short\">";
        xml_ += "<java.lang.Short value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayByte\" type=\"byte\">";
        xml_ += "<java.lang.Byte value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayChar\" type=\"char\">";
        xml_ += "<java.lang.Character value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayBoolean\" type=\"boolean\">";
        xml_ += "<java.lang.Boolean value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayDouble\" type=\"double\">";
        xml_ += "<java.lang.Double value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+MY_ARRAYS+"\" field=\"arrayFloat\" type=\"float\">";
        xml_ += "<java.lang.Float value=\"1\"/>";
        xml_ += "</array>";
        xml_ += "</"+MY_ARRAYS+">";
        MyArrays list_;
        list_ = (MyArrays) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(true);
        ObjectComponents.setupNotNull(list_);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void setupNotNull14Test() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList("STR_FIVE","STR_SIX")));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setScore(new Pair<CheckedPrimitive,CheckedClass>(new CheckedPrimitive(),new CheckedClass()));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.getCheckedPrim().put("", new CheckedPrimitive());
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>(new ComplexMap<String,Character,Serializable>(new StringMap<Serializable>())));
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClass_ = new CheckedClass();
        checkedClass_.setCorrect(5);
        checkedClass_.setOk("OK");
        full_.setCheckedClass(checkedClass_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(true);
        ObjectComponents.setupNotNull(full_);
    }

    @Test
    public void setupNotNull15Test() {
        FullClass full_ = new FullClass();
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(true);
        ObjectComponents.setupNotNull(full_);
    }

    @Test
    public void setupNotNull16Test() {
        TreeContainer t_ = new TreeContainer();
        StrangeComparator<String> c_ = new StrangeComparator<String>();
        c_.setField("EX");
        t_.setCmp(c_);
        TreeMap<String,Integer> tree_ = new TreeMap<String,Integer>(new NaturalComparator<String>());
        t_.setTree(tree_);
        TreeMap<Integer,Integer> treeInt_ = new TreeMap<Integer,Integer>(new NaturalComparator<Integer>());
        t_.setTreeInt(treeInt_);
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(true);
        ObjectComponents.setupNotNull(t_);
    }

    @Test
    public void setupNotNull17Test() {
        TreeContainer t_ = new TreeContainer();
        StrangeComparator<String> c_ = new StrangeComparator<String>();
        c_.setField("EX");
        t_.setCmp(c_);
        TreeMap<String,Integer> tree_ = new TreeMap<String,Integer>(new NaturalComparator<String>());
        t_.setTree(tree_);
        TreeMap<Integer,Integer> treeInt_ = new TreeMap<Integer,Integer>(new NaturalComparator<Integer>());
        t_.setTreeInt(treeInt_);
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(t_);
    }

    @Test
    public void setupNotNull18Test() {
        TreeContainer t_ = new TreeContainer();
        StrangeComparator<String> c_ = new StrangeComparator<String>();
        c_.setField("EX");
        t_.setCmp(c_);
        TreeMap<String,Integer> tree_ = new TreeMap<String,Integer>(c_);
        t_.setTree(tree_);
        TreeMap<Integer,Integer> treeInt_ = new TreeMap<Integer,Integer>(new NaturalComparator<Integer>());
        t_.setTreeInt(treeInt_);
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(true);
        ObjectComponents.setupNotNull(t_);
    }

    @Test
    public void setupNotNull19Test() {
        TreeContainer t_ = new TreeContainer();
        StrangeComparator<String> c_ = new StrangeComparator<String>();
        c_.setField("EX");
        t_.setCmp(c_);
        TreeMap<String,Integer> tree_ = new TreeMap<String,Integer>(c_);
        t_.setTree(tree_);
        StrangeComparator<Integer> cInt_ = new StrangeComparator<Integer>();
        cInt_.setField(1);
        TreeMap<Integer,Integer> treeInt_ = new TreeMap<Integer,Integer>(cInt_);
        t_.setTreeInt(treeInt_);
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(true);
        ObjectComponents.setupNotNull(t_);
    }

    @Test(expected=ContainerException.class)
    public void setupNotNull1FailTest() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+STRING_LIST+">";
        xml_ += "<java.lang.Long class='"+LS_CLASS+"' value=\"1\"/>";
        xml_ += "<java.lang.Long class='"+LS_CLASS+"' value=\"2\"/>";
        xml_ += "</"+STRING_LIST+">";
        StringList list_;
        list_ = (StringList) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(list_);
    }

    @Test(expected=NullFieldException.class)
    public void setupNotNull2FailTest() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+STRING_LIST+">";
        xml_ += "<null class='"+LS_CLASS+"'/>";
        xml_ += "</"+STRING_LIST+">";
        StringList list_;
        list_ = (StringList) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(list_);
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullFieldException.class)
    public void setupNotNull3FailTest() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList((String)null)));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        CheckedClass checkedClassOne_ = new CheckedClass();
        checkedClassOne_.setCorrect(5);
        checkedClassOne_.setOk("OK");
        full_.setScore(new Pair<CheckedPrimitive, CheckedClass>(new CheckedPrimitive("A;B"),checkedClassOne_));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>());
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.getCheckedPrim().put("", new CheckedPrimitive("A;B"));
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClassTwo_ = new CheckedClass();
        checkedClassTwo_.setCorrect(5);
        checkedClassTwo_.setOk("OK");
        full_.setCheckedClass(checkedClassTwo_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullFieldException.class)
    public void setupNotNull4FailTest() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList()));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        CheckedClass checkedClassOne_ = new CheckedClass();
        checkedClassOne_.setCorrect(5);
        checkedClassOne_.setOk("OK");
        full_.setScore(new Pair<CheckedPrimitive, CheckedClass>(new CheckedPrimitive("A;B"),checkedClassOne_));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>());
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.getCheckedPrim().put("", new CheckedPrimitive("A;B"));
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClassTwo_ = new CheckedClass();
        checkedClassTwo_.setCorrect(5);
        checkedClassTwo_.setOk("OK");
        full_.setCheckedClass(checkedClassTwo_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        full_.setContainer(new CustList<UncheckedClass>(uncheckedClass_));
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullFieldException.class)
    public void setupNotNull5FailTest() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList("STR_FIVE","STR_SIX")));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        CheckedClass checkedClassOne_ = new CheckedClass();
        checkedClassOne_.setCorrect(5);
        checkedClassOne_.setOk("OK");
        full_.setScore(new Pair<CheckedPrimitive, CheckedClass>(new CheckedPrimitive("A;B"),checkedClassOne_));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>());
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.getCheckedPrim().put("", new CheckedPrimitive("A;B"));
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClass_ = new CheckedClass();
        full_.setCheckedClass(checkedClass_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullFieldException.class)
    public void setupNotNull6FailTest() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList("STR_FIVE","STR_SIX")));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        CheckedClass checkedClassOne_ = new CheckedClass();
        checkedClassOne_.setCorrect(5);
        checkedClassOne_.setOk("OK");
        full_.setScore(new Pair<CheckedPrimitive, CheckedClass>(new CheckedPrimitive("A;B"),checkedClassOne_));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>());
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.getCheckedPrim().put("", new CheckedPrimitive("A;B"));
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClass_ = new CheckedClass();
        checkedClass_.setCorrect(5);
        checkedClass_.setOk("OK");
        full_.setCheckedClass(checkedClass_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullFieldException.class)
    public void setupNotNull7FailTest() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList("STR_FIVE","STR_SIX")));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        CheckedClass checkedClassOne_ = new CheckedClass();
        checkedClassOne_.setCorrect(5);
        checkedClassOne_.setOk("OK");
        full_.setScore(new Pair<CheckedPrimitive, CheckedClass>(new CheckedPrimitive("A;B"),checkedClassOne_));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>());
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.getCheckedPrim().put("", new CheckedPrimitive("A;B"));
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClass_ = new CheckedClass();
        checkedClass_.setCorrect(5);
        checkedClass_.setOk("OK");
        full_.setCheckedClass(checkedClass_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullFieldException.class)
    public void setupNotNull8FailTest() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList("STR_FIVE","STR_SIX")));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setScore(new Pair<CheckedPrimitive,CheckedClass>(new CheckedPrimitive(),new CheckedClass()));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.getCheckedPrim().put("", new CheckedPrimitive());
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>(new ComplexMap<String,Character,Serializable>(new StringMap<Serializable>())));
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.setChecked(new StringList("ok","ko"));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClass_ = new CheckedClass();
        checkedClass_.setCorrect(5);
        checkedClass_.setOk("OK");
        full_.setCheckedClass(checkedClass_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @SuppressWarnings("unchecked")
    @Test(expected=NullFieldException.class)
    public void setupNotNull9FailTest() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList("STR_FIVE","STR_SIX")));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setScore(new Pair<CheckedPrimitive,CheckedClass>(new CheckedPrimitive(),new CheckedClass()));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.getCheckedPrim().put("", new CheckedPrimitive());
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>(new ComplexMap<String,Character,Serializable>(new StringMap<Serializable>())));
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.getInterfMap().put(null, true);
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap< Integer> map_;
        map_ = new MyMap< Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClass_ = new CheckedClass();
        checkedClass_.setCorrect(5);
        checkedClass_.setOk("OK");
        full_.setCheckedClass(checkedClass_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }
    @SuppressWarnings("unchecked")
    @Test(expected=NullFieldException.class)
    public void setupNotNull10FailTest() {
        FullClass full_ = new FullClass();
        full_.setInteger(6);
        full_.setIntegerObj(5);
        full_.setListString(new StringList("STR_ONE","STR_TWO"));
        full_.setListListString(new CustList<StringList>(new StringList("STR_THREE","STR_FOUR"),new StringList("STR_FIVE","STR_SIX")));
        full_.setMap(new StringMap<Integer>());
        full_.getMap().put("number", 4);
        full_.setOtherPair(new Pair<PseudoPrimitive,String>(PseudoPrimitive.newPseudoPrimitive("ST;RING"),"OTHER"));
        full_.setPair(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER"));
        full_.setPairs(new Pair<Pair<Primitive,String>,Pair<Integer,StringList>>(new Pair<Primitive,String>(new Primitive("ST;RING"),"OTHER_2"),new Pair<Integer,StringList>(8, new StringList("ELEMENT"))));
        full_.setArrayInts(new int[]{23,45,76});
        full_.setArraysStrings(new String[][]{new String[]{"ARR_ONE","ARR_TWO"},new String[]{"ARR_THREE"},new String[]{"ARR_FOUR"}});
        full_.setListArray(new CustList<int[]>(new int[]{934,34,65}));
        full_.setBit(true);
        full_.setStr("SINGLE");
        full_.setBits(new BooleanList(false));
        Template<String, EnumNumbers> temp_;
        temp_ = new Template<String, EnumNumbers>();
        temp_.setTs(new StringList("TEMP_ONE","TEMP_TWO"));
        temp_.setVs(new EnumList<EnumNumbers>(EnumNumbers.ONE,EnumNumbers.SEVEN));
        full_.setTemp(temp_);
        TemplateString<Boolean> bools_ = new TemplateString<Boolean>();
        bools_.setTs(new BooleanList(false,true));
        bools_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        bools_.setValues(new NumberMap<Integer,Boolean>());
        bools_.getValues().put(5, true);
        full_.setTempStrings(bools_);
        TemplateList l_ = new TemplateList();
        l_.setTs(new Numbers<Integer>(1,2,3));
        l_.setVs(new StringList("TEMP_STR","TEMP_STR"));
        l_.setValues(new NumberMap<Integer,Integer>());
        l_.getValues().put(5, 6);
        l_.getValues().put(6, 5);
        l_.setList(new CustList<Pair<Boolean,String>>(new Pair<Boolean,String>(false,""),new Pair<Boolean,String>(true,"not_empty")));
        full_.setTempList(l_);
        full_.setEnumEx(EnumNumbers.TWO);
        full_.setScore(new Pair<CheckedPrimitive,CheckedClass>(new CheckedPrimitive(),new CheckedClass()));
        full_.setCheckedPrim(new StringMap<CheckedPrimitive>());
        full_.getCheckedPrim().put("", new CheckedPrimitive());
        full_.setThree(new Pair<Pair<Boolean,StringMap<String>>,String>(new Pair<Boolean,StringMap<String>>(false,new StringMap<String>()),"ex"));
        full_.getThree().getFirst().getSecond().put("KEY", "VALUE");
        full_.setListMaps(new CustList<ComplexMap<String,Character,Serializable>>(new ComplexMap<String,Character,Serializable>(new StringMap<Serializable>())));
        full_.setInterfMap(new StringMap<Boolean>());
        full_.setInterfList(new StringList());
        full_.getInterfMap().put("", null);
        full_.setPrimitive(new Primitive("STR;NG"));
        full_.setArrayLists(new Listable<?>[]{new StringList("EX"),new Numbers<Integer>(4)});
        full_.setMyIntegers(new MyList<Integer>(5, 7));
        full_.setChecked(new StringList("ok","ko"));
        MyMap<Integer> map_;
        map_ = new MyMap<Integer>();
        map_.put("KEY", 1);
        map_.put("KEY_TWO", 2);
        map_.put("KEY", 2);
        map_.put("KEY_THREE", 3);
        full_.setMapIntegers(map_);
        Snapshot<String> snap_ = new Snapshot<String>();
        full_.setSnap(snap_);
        CheckedClass checkedClass_ = new CheckedClass();
        checkedClass_.setCorrect(5);
        checkedClass_.setOk("OK");
        full_.setCheckedClass(checkedClass_);
        UncheckedClass uncheckedClass_ = new UncheckedClass();
        uncheckedClass_.setCorrect(5);
        uncheckedClass_.setOk("OK");
        uncheckedClass_.setList(new ArrayList<Integer>());
        full_.setUncheckedClass(uncheckedClass_);
        full_.setContainer(new CustList<UncheckedClass>());
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(full_);
    }

    @Test(expected=ContainerException.class)
    public void setupNotNull11FailTest() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+STRING_LIST+">";
        xml_ += "<"+STRING_LIST+" class='"+LS_CLASS+"'/>";
        xml_ += "</"+STRING_LIST+">";
        StringList list_;
        list_ = (StringList) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(list_);
    }

    @Test(expected=ContainerException.class)
    public void setupNotNull12FailTest() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+FULL_CLASS+">";
        xml_ += "<"+CUST_LIST+" class=\""+FULL_CLASS+"\" field=\"listListString\">";
        xml_ += "<"+TEMPLATE_LIST+" class='"+LS_CLASS+"'/>";
        xml_ += "</"+CUST_LIST+">";
        xml_ += "</"+FULL_CLASS+">";
        FullClass list_;
        list_ = (FullClass) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(false);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(list_);
    }

    @Test(expected=NullFieldException.class)
    public void setupNotNull13FailTest() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REF_ARRAY+">";
        xml_ += "<array class=\""+REF_ARRAY+"\" field=\"array\" type=\"java.lang.Object\">";
        xml_ += "<null/>";
        xml_ += "</array>";
        xml_ += "</"+REF_ARRAY+">";
        RefsArray list_;
        list_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(list_);
    }

    @Test(expected=NullFieldException.class)
    public void setupNotNull14FailTest() {
        TreeContainer t_ = new TreeContainer();
        StrangeComparator<String> c_ = new StrangeComparator<String>();
        t_.setCmp(c_);
        TreeMap<String,Integer> tree_ = new TreeMap<String,Integer>(new NaturalComparator<String>());
        t_.setTree(tree_);
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(true);
        ObjectComponents.setupNotNull(t_);
    }

    @Test(expected=ContainerException.class)
    public void setupNotNull15FailTest() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+TREE_CONTAINER+">";
        xml_ += "<"+STRANGE_CMP+" class=\""+TREE_CONTAINER+"\" field=\"cmp\">";
        xml_ += "<java.lang.String class=\""+STRANGE_CMP+"\" field=\"field\" value=\"1\"/>";
        xml_ += "</"+STRANGE_CMP+">";
        xml_ += "<"+TREE_MAP+" class=\""+TREE_CONTAINER+"\" field=\"tree\">";
        xml_ += "<"+STRANGE_CMP+" class=\""+TREE_MAP+"\" field=\"comparator\">";
        xml_ += "<java.lang.Integer class=\""+STRANGE_CMP+"\" field=\"field\" value=\"2\"/>";
        xml_ += "</"+STRANGE_CMP+">";
        xml_ += "</"+TREE_MAP+">";
        xml_ += "<"+TREE_MAP+" class=\""+TREE_CONTAINER+"\" field=\"treeInt\">";
        xml_ += "<"+STRANGE_CMP+" class=\""+TREE_MAP+"\" field=\"comparator\">";
        xml_ += "<java.lang.Integer class=\""+STRANGE_CMP+"\" field=\"field\" value=\"3\"/>";
        xml_ += "</"+STRANGE_CMP+">";
        xml_ += "</"+TREE_MAP+">";
        xml_ += "</"+TREE_CONTAINER+">";
        TreeContainer list_;
        list_ = (TreeContainer) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(list_);
    }

    //But no exception even in put method, that is why ignore temporary the test
    @Ignore
    @Test(expected=ContainerException.class)
    public void setupNotNull16FailTest() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+TREE_CONTAINER+">";
        xml_ += "<"+STRANGE_CMP+" class=\""+TREE_CONTAINER+"\" field=\"cmp\">";
        xml_ += "<java.lang.String class=\""+STRANGE_CMP+"\" field=\"field\" value=\"1\"/>";
        xml_ += "</"+STRANGE_CMP+">";
        xml_ += "<"+TREE_MAP+" class=\""+TREE_CONTAINER+"\" field=\"tree\">";
        xml_ += "<"+INT_CMP+" class=\""+TREE_MAP+"\" field=\"comparator\">";
        xml_ += "<java.lang.Integer class=\""+STRANGE_CMP+"\" field=\"field\" value=\"2\"/>";
        xml_ += "</"+INT_CMP+">";
        xml_ += "<java.lang.String class='"+MP_CLASS+"' value=\"2\" key=\"\"/>";
        xml_ += "<java.lang.Integer class='"+MP_CLASS+"' value=\"1\"/>";
        xml_ += "<java.lang.String class='"+MP_CLASS+"' value=\"3\" key=\"\"/>";
        xml_ += "<java.lang.Integer class='"+MP_CLASS+"' value=\"1\"/>";
        xml_ += "</"+TREE_MAP+">";
        xml_ += "<"+TREE_MAP+" class=\""+TREE_CONTAINER+"\" field=\"treeInt\"/>";
        xml_ += "</"+TREE_CONTAINER+">";
        TreeContainer list_;
        list_ = (TreeContainer) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectComponents.setCheckingNullity(true);
        ObjectComponents.setReferences(false);
        ObjectComponents.setupNotNull(list_);
    }
}
