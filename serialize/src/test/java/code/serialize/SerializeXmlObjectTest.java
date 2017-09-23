package code.serialize;
import static code.serialize.EquallableExUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.xml.sax.SAXException;

import code.serialize.classes.AbMap;
import code.serialize.classes.Composite;
import code.serialize.classes.CompositeConstr;
import code.serialize.classes.CompositeNoConstr;
import code.serialize.classes.CompositePrimitivable;
import code.serialize.classes.CompositePrivate;
import code.serialize.classes.CompositeTwo;
import code.serialize.classes.Container;
import code.serialize.classes.ContainerArray;
import code.serialize.classes.ContainerCompositeTwo;
import code.serialize.classes.ContainerIdMapRef;
import code.serialize.classes.ContainerNatTreeMap;
import code.serialize.classes.ContainerObjectMapClassic;
import code.serialize.classes.ContainerObjectMapComp;
import code.serialize.classes.ContainerObjectMapRef;
import code.serialize.classes.ContainerStringMap;
import code.serialize.classes.ContainerTreeMap;
import code.serialize.classes.Containers;
import code.serialize.classes.InternsClasses;
import code.serialize.classes.InternsClasses.InternStandardTwo;
import code.serialize.classes.InternsClasses.InternStandardTwo.InternStandardOne;
import code.serialize.classes.InternsClasses.InternStaticStandard;
import code.serialize.classes.InternsClasses.InternStaticStandard.InternStaticStandardFour;
import code.serialize.classes.InternsClasses.InternStaticStandard.InternStaticStandardThree;
import code.serialize.classes.MapComponent;
import code.serialize.classes.Maps;
import code.serialize.classes.MapsAtomicInteger;
import code.serialize.classes.MapsAtomicLong;
import code.serialize.classes.MapsBigDecimal;
import code.serialize.classes.MapsBigInteger;
import code.serialize.classes.MapsBoolean;
import code.serialize.classes.MapsByte;
import code.serialize.classes.MapsDouble;
import code.serialize.classes.MapsEnum;
import code.serialize.classes.MapsFloat;
import code.serialize.classes.MapsInteger;
import code.serialize.classes.MapsLong;
import code.serialize.classes.MapsObject;
import code.serialize.classes.MapsShort;
import code.serialize.classes.MyEnum;
import code.serialize.classes.MyEnumTwo;
import code.serialize.classes.MyStringComparator;
import code.serialize.classes.Primitive;
import code.serialize.classes.PrimitiveFive;
import code.serialize.classes.PrimitiveFour;
import code.serialize.classes.PrimitiveSix;
import code.serialize.classes.PrimitiveThree;
import code.serialize.classes.PrimitiveTwo;
import code.serialize.classes.RefOne;
import code.serialize.classes.RefTwo;
import code.serialize.classes.Refs;
import code.serialize.classes.RefsArray;
import code.serialize.classes.RefsList;
import code.serialize.exceptions.BadAccessException;
import code.serialize.exceptions.DuplicatesKeysException;
import code.serialize.exceptions.InexistingValueForEnum;
import code.serialize.exceptions.InvokingException;
import code.serialize.exceptions.NoAttributeForSerializable;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.serialize.exceptions.NullSerialException;
import code.serialize.exceptions.RefException;
import code.util.BooleanMap;
import code.util.CustList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import code.util.comparators.NaturalComparator;
import code.util.exceptions.RuntimeClassNotFoundException;

@SuppressWarnings("static-method")
@RunWith(JUnitParamsRunner.class)
public class SerializeXmlObjectTest {
    private static final String STRING_LIST = StringList.class.getName();
    private static final String TREE_MAP = TreeMap.class.getName();
    private static final String CUST_LIST = CustList.class.getName();
    private static final String NAT_TREE_MAP = NatTreeMap.class.getName();
    private static final String ID_MAP = IdMap.class.getName();
    private static final String ENUM_MAP = EnumMap.class.getName();
    private static final String NUMBER_MAP = NumberMap.class.getName();
    private static final String STRING_MAP = StringMap.class.getName();
    private static final String BOOLEAN_MAP = BooleanMap.class.getName();
    private static final String OBJECT_MAP = ObjectMap.class.getName();
    private static final String NATURAL_COMPARATOR = NaturalComparator.class.getName();
    private static final String CONTAINER = Container.class.getName();
    private static final String CONTAINER_NAT_TREE_MAP = ContainerNatTreeMap.class.getName();
    private static final String CONTAINER_ID_MAP_REF = ContainerIdMapRef.class.getName();
    private static final String CONTAINER_COMPOSITE_TWO = ContainerCompositeTwo.class.getName();
    private static final String CONTAINER_ARRAY = ContainerArray.class.getName();
    private static final String CONTAINER_TREE_MAP = ContainerTreeMap.class.getName();
    private static final String CONTAINER_OBJECT_MAP_CLASSIC = ContainerObjectMapClassic.class.getName();
    private static final String CONTAINER_OBJECT_MAP_COMP = ContainerObjectMapComp.class.getName();
    private static final String CONTAINER_OBJECT_MAP_REF = ContainerObjectMapRef.class.getName();
    private static final String CONTAINER_STRING_MAP = ContainerStringMap.class.getName();
    private static final String CONTAINERS = Containers.class.getName();
    private static final String COMPOSITE_PRIMITIVABLE = CompositePrimitivable.class.getName();
    private static final String COMPOSITE_PRIVATE = CompositePrivate.class.getName();
    private static final String COMPOSITE_NO_CONSTR = CompositeNoConstr.class.getName();
    private static final String COMPOSITE_CONSTR = CompositeConstr.class.getName();
    private static final String COMPOSITE = Composite.class.getName();
    private static final String COMPOSITE_TWO = CompositeTwo.class.getName();
    private static final String REFS_ARRAY = RefsArray.class.getName();
    private static final String REFS_LIST = RefsList.class.getName();
    private static final String MAPS = Maps.class.getName();
    private static final String MAPS_OBJECT = MapsObject.class.getName();
    private static final String MAPS_ENUM = MapsEnum.class.getName();
    private static final String MAPS_BOOLEAN = MapsBoolean.class.getName();
    private static final String MAPS_DOUBLE = MapsDouble.class.getName();
    private static final String MAPS_FLOAT = MapsFloat.class.getName();
    private static final String MAPS_LONG = MapsLong.class.getName();
    private static final String MAPS_INTEGER = MapsInteger.class.getName();
    private static final String MAPS_SHORT = MapsShort.class.getName();
    private static final String MAPS_BYTE = MapsByte.class.getName();
    private static final String MAPS_BIG_INTEGER = MapsBigInteger.class.getName();
    private static final String MAPS_BIG_DECIMAL = MapsBigDecimal.class.getName();
    private static final String MAPS_AT_LONG = MapsAtomicLong.class.getName();
    private static final String MAPS_AT_INTEGER = MapsAtomicInteger.class.getName();
    private static final String MY_ENUM = MyEnum.class.getName();
    private static final String MY_ENUM_TWO = MyEnumTwo.class.getName();
    private static final String PRIMITIVE = Primitive.class.getName();
    private static final String PRIMITIVE_TWO = PrimitiveTwo.class.getName();
    private static final String PRIMITIVE_THREE = PrimitiveThree.class.getName();
    private static final String PRIMITIVE_FOUR = PrimitiveFour.class.getName();
    private static final String PRIMITIVE_FIVE = PrimitiveFive.class.getName();
    private static final String PRIMITIVE_SIX = PrimitiveSix.class.getName();
    private static final String REFS = Refs.class.getName();
    private static final String AB_MAP = AbMap.class.getName();
    private static final String REF_ONE = RefOne.class.getName();
    private static final String REF_TWO = RefTwo.class.getName();
    private static final String MY_STRING_COMPARATOR = MyStringComparator.class.getName();
    private static final String MAP_COMPONENT = MapComponent.class.getName();
    private static final String INTERNS_CLASSES = InternsClasses.class.getName();
    private static final String INTERN_TWO = INTERNS_CLASSES+"$InternStandardTwo";
    private static final String INTERN_ONE = INTERNS_CLASSES+"$InternStandardTwo$InternStandardOne";
    private static final String INTERN_FOUR = INTERNS_CLASSES+"$InternStaticStandard$InternStaticStandardFour";

    Object[] booleanInputs() {
        return $($(false),$(true));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString1Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><java.lang.Integer value=\"1\"/>",SerializeXmlObject.toXmlString(1));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString2Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><java.lang.Boolean value=\"true\"/>",SerializeXmlObject.toXmlString(true));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString3Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><java.lang.Character value=\"_\"/>",SerializeXmlObject.toXmlString('_'));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString4Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><java.lang.String value=\"&quot;STRING&#376;&amp;&lt;'&gt;&#128;&quot;\"/>",SerializeXmlObject.toXmlString("\"STRING"+(char)376+"&<'>"+(char)128+"\""));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString5Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MY_ENUM+" value=\"ONE\"/>",SerializeXmlObject.toXmlString(MyEnum.ONE));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString6Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Primitive pr_ = new Primitive();
        pr_.setPrimitive(2);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+PRIMITIVE+" value=\"2\"/>",SerializeXmlObject.toXmlString(pr_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString7Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        PrimitiveTwo pr_ = new PrimitiveTwo();
        pr_.setPrimitive(2);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+PRIMITIVE_TWO+" value=\"2\"/>",SerializeXmlObject.toXmlString(pr_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString8Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Composite pr_ = new Composite();
        pr_.setBool(true);
        pr_.setCharacter('8');
        pr_.setElement(MyEnum.TWO);
        pr_.setInteger(15);
        Primitive prTwo_ = new Primitive();
        prTwo_.setPrimitive(6);
        pr_.setPrimitive(prTwo_);
        PrimitiveTwo prThree_ = new PrimitiveTwo();
        prThree_.setPrimitive(8);
        pr_.setPrimitiveTwo(prThree_);
        pr_.setString("STR");
        pr_.setTransientMember("unsaved");
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+COMPOSITE+"><java.lang.Integer class=\""+COMPOSITE+"\" field=\"integer\" value=\"15\"/><java.lang.Boolean class=\""+COMPOSITE+"\" field=\"bool\" value=\"true\"/><java.lang.String class=\""+COMPOSITE+"\" field=\"string\" value=\"STR\"/><java.lang.Character class=\""+COMPOSITE+"\" field=\"character\" value=\"8\"/><"+MY_ENUM+" class=\""+COMPOSITE+"\" field=\"element\" value=\"TWO\"/><"+PRIMITIVE+" class=\""+COMPOSITE+"\" field=\"primitive\" value=\"6\"/><"+PRIMITIVE_TWO+" class=\""+COMPOSITE+"\" field=\"primitiveTwo\" value=\"8\"/></"+COMPOSITE+">",SerializeXmlObject.toXmlString(pr_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString9Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Composite pr_ = new Composite();
        pr_.setBool(true);
        pr_.setCharacter('8');
        pr_.setElement(MyEnum.TWO);
        pr_.setInteger(15);
        Primitive prTwo_ = new Primitive();
        prTwo_.setPrimitive(6);
        pr_.setPrimitive(prTwo_);
        PrimitiveTwo prThree_ = new PrimitiveTwo();
        prThree_.setPrimitive(8);
        pr_.setPrimitiveTwo(prThree_);
        pr_.setString(null);
        pr_.setTransientMember("unsaved");
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+COMPOSITE+"><java.lang.Integer class=\""+COMPOSITE+"\" field=\"integer\" value=\"15\"/><java.lang.Boolean class=\""+COMPOSITE+"\" field=\"bool\" value=\"true\"/><null class=\""+COMPOSITE+"\" field=\"string\"/><java.lang.Character class=\""+COMPOSITE+"\" field=\"character\" value=\"8\"/><"+MY_ENUM+" class=\""+COMPOSITE+"\" field=\"element\" value=\"TWO\"/><"+PRIMITIVE+" class=\""+COMPOSITE+"\" field=\"primitive\" value=\"6\"/><"+PRIMITIVE_TWO+" class=\""+COMPOSITE+"\" field=\"primitiveTwo\" value=\"8\"/></"+COMPOSITE+">",SerializeXmlObject.toXmlString(pr_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString10Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Containers containers_ = new Containers();
        StringList list_ = new StringList();
        list_.add("ELEMENT");
        containers_.setList(list_);
        TreeMap<String, Integer> tree_ = new TreeMap<String, Integer>(new NaturalComparator<String>());
        tree_.put("B", 2);
        tree_.put("A", 1);
        containers_.setTreemap(tree_);
        StringMap<MyEnum> map_ = new StringMap<MyEnum>();
        map_.put("STR", MyEnum.ONE);
        containers_.setMap(map_);
        containers_.setArray(new int[]{5,9});
        containers_.setArrayDouble(new int[][]{new int[]{5,9},new int[]{4}});
//        assertEq("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+CONTAINERS+"><"+STRING_LIST+" class=\""+CONTAINERS+"\" field=\"list\"><java.lang.String class='"+SerializeXmlObject.LS_CLASS+"' value=\"ELEMENT\"/></"+STRING_LIST+"><"+TREE_MAP+" class=\""+CONTAINERS+"\" field=\"treemap\"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"A\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"B\"/><java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"1\"/><java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"2\"/></"+TREE_MAP+"><util.Map class=\""+CONTAINERS+"\" field=\"map\"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"STR\"/><"+MY_ENUM+" class='"+SerializeXmlObject.MP_CLASS+"' value=\"ONE\"/></util.Map><array class=\""+CONTAINERS+"\" field=\"array\" type=\"int\"><java.lang.Integer value=\"5\"/><java.lang.Integer value=\"9\"/></array><array class=\""+CONTAINERS+"\" field=\"arrayDouble\" type=\"[I\"><array type=\"int\"><java.lang.Integer value=\"5\"/><java.lang.Integer value=\"9\"/></array><array type=\"int\"><java.lang.Integer value=\"4\"/></array></array></"+CONTAINERS+">", SerializeXmlObject.toXmlString(containers_));
//        assertXMLEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+CONTAINERS+"><"+STRING_LIST+" class=\""+CONTAINERS+"\" field=\"list\"><java.lang.String class='"+SerializeXmlObject.LS_CLASS+"' value=\"ELEMENT\"/></"+STRING_LIST+"><"+TREE_MAP+" class=\""+CONTAINERS+"\" field=\"treemap\"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"A\"/><java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"1\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"B\"/><java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"2\"/></"+TREE_MAP+"><util.Map class=\""+CONTAINERS+"\" field=\"map\"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"STR\"/><"+MY_ENUM+" class='"+SerializeXmlObject.MP_CLASS+"' value=\"ONE\"/></util.Map><array class=\""+CONTAINERS+"\" field=\"array\" type=\"int\"><java.lang.Integer value=\"5\"/><java.lang.Integer value=\"9\"/></array><array class=\""+CONTAINERS+"\" field=\"arrayDouble\" type=\"[I\"><array type=\"int\"><java.lang.Integer value=\"5\"/><java.lang.Integer value=\"9\"/></array><array type=\"int\"><java.lang.Integer value=\"4\"/></array></array></"+CONTAINERS+">", SerializeXmlObject.toXmlString(containers_));
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+CONTAINERS+"><"+STRING_LIST+" class=\""+CONTAINERS+"\" field=\"list\"><java.lang.String class='"+SerializeXmlObject.LS_CLASS+"' value=\"ELEMENT\"/></"+STRING_LIST+"><"+TREE_MAP+" class=\""+CONTAINERS+"\" field=\"treemap\"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"A\"/><java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"1\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"B\"/><java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"2\"/><"+NATURAL_COMPARATOR+" class=\""+TREE_MAP+"\" field=\"comparator\"/></"+TREE_MAP+"><"+STRING_MAP+" class=\""+CONTAINERS+"\" field=\"map\"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"STR\"/><"+MY_ENUM+" class='"+SerializeXmlObject.MP_CLASS+"' value=\"ONE\"/></"+STRING_MAP+"><array class=\""+CONTAINERS+"\" field=\"array\" type=\"int\"><java.lang.Integer value=\"5\"/><java.lang.Integer value=\"9\"/></array><array class=\""+CONTAINERS+"\" field=\"arrayDouble\" type=\"[I\"><array type=\"int\"><java.lang.Integer value=\"5\"/><java.lang.Integer value=\"9\"/></array><array type=\"int\"><java.lang.Integer value=\"4\"/></array></array></"+CONTAINERS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Test
    public void toXmlString11Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        Refs refs_ = new Refs();
        RefOne refOne_ = new RefOne();
        RefTwo refTwo_ = new RefTwo();
        refOne_.setRefTwo(refTwo_);
        refTwo_.setRefOne(refOne_);
        refs_.setRef(refOne_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS+"><"+REF_ONE+" class=\""+REFS+"\" field=\"ref\" id=\"0\"><"+REF_TWO+" class=\""+REF_ONE+"\" field=\"refTwo\"><"+REF_ONE+" class=\""+REF_TWO+"\" field=\"refOne\" ref=\"0\"/></"+REF_TWO+"></"+REF_ONE+"></"+REFS+">", SerializeXmlObject.toXmlString(refs_));
    }

    @Test
    public void toXmlString12Test() {
        SerializeXmlObject.setReferences(false);
        SerializeXmlObject.setCheckReferences(true);
        Refs refs_ = new Refs();
        RefOne refOne_ = new RefOne();
        RefTwo refTwo_ = new RefTwo();
        refOne_.setRefTwo(refTwo_);
        refTwo_.setRefOne(refOne_);
        refs_.setRef(refOne_);
        assertEq("", SerializeXmlObject.toXmlString(refs_));
    }

    @Test
    public void toXmlString13Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray refs_ = new RefsArray();
        Object[] array_ = new Object[2];
        CompositeTwo pr_ = new CompositeTwo();
        pr_.setPrimitive(4);
        array_[0] = pr_;
        array_[1] = pr_;
        refs_.setArray(array_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"java.lang.Object\"><"+COMPOSITE_TWO+" id=\"0\"><java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/></"+COMPOSITE_TWO+"><"+COMPOSITE_TWO+" ref=\"0\"/></array></"+REFS_ARRAY+">", SerializeXmlObject.toXmlString(refs_));
    }

    @Test
    public void toXmlString14Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(true);
        RefsArray refs_ = new RefsArray();
        Object[] array_ = new Object[2];
        CompositeTwo pr_ = new CompositeTwo();
        pr_.setPrimitive(4);
        array_[0] = pr_;
        array_[1] = pr_;
        refs_.setArray(array_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"java.lang.Object\"><"+COMPOSITE_TWO+" id=\"0\"><java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/></"+COMPOSITE_TWO+"><"+COMPOSITE_TWO+" ref=\"0\"/></array></"+REFS_ARRAY+">", SerializeXmlObject.toXmlString(refs_));
    }

    @Test
    public void toXmlString15Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        RefsList refs_ = new RefsList();
        CustList<Object> array_ = new CustList<Object>();
        RefOne refOne_ = new RefOne();
        RefTwo refTwo_ = new RefTwo();
        refOne_.setRefTwo(refTwo_);
        refTwo_.setRefOne(refOne_);
        array_.add(refOne_);
        array_.add(refTwo_);
        array_.add(refOne_);
        refs_.setList(array_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_LIST+"><"+CUST_LIST+" class=\""+REFS_LIST+"\" field=\"list\"><"+REF_ONE+" class='"+SerializeXmlObject.LS_CLASS+"' id=\"0\"><"+REF_TWO+" class=\""+REF_ONE+"\" field=\"refTwo\" ref=\"1\"/></"+REF_ONE+"><"+REF_TWO+" class='"+SerializeXmlObject.LS_CLASS+"' id=\"1\"><"+REF_ONE+" class=\""+REF_TWO+"\" field=\"refOne\" ref=\"0\"/></"+REF_TWO+"><"+REF_ONE+" class='"+SerializeXmlObject.LS_CLASS+"' ref=\"0\"/></"+CUST_LIST+"></"+REFS_LIST+">", SerializeXmlObject.toXmlString(refs_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString16Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<MyEnum> containers_ = new Maps<MyEnum>();
        EnumMap<MyEnum,String> map_ = new EnumMap<MyEnum,String>();
        map_.put(MyEnum.ONE, "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+ENUM_MAP+" class=\""+MAPS+"\" field=\"map\"><"+MY_ENUM+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"ONE\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+ENUM_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString17Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<Object> containers_ = new Maps<Object>();
        IdMap<Object,String> map_ = new IdMap<Object,String>();
        map_.put(null, "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+ID_MAP+" class=\""+MAPS+"\" field=\"map\"><null class='"+SerializeXmlObject.MP_CLASS+"' key=\"\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+ID_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString18Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<Boolean> containers_ = new Maps<Boolean>();
        BooleanMap<String> map_ = new BooleanMap<String>();
        map_.put(true, "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+BOOLEAN_MAP+" class=\""+MAPS+"\" field=\"map\"><java.lang.Boolean class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"true\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+BOOLEAN_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString19Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<Long> containers_ = new Maps<Long>();
        NumberMap<Long,String> map_ = new NumberMap<Long,String>();
        map_.put(1L, "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+NUMBER_MAP+" class=\""+MAPS+"\" field=\"map\"><java.lang.Long class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+NUMBER_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString20Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<Short> containers_ = new Maps<Short>();
        NumberMap<Short,String> map_ = new NumberMap<Short,String>();
        map_.put(new Short((short)1), "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+NUMBER_MAP+" class=\""+MAPS+"\" field=\"map\"><java.lang.Short class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+NUMBER_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString21Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<Byte> containers_ = new Maps<Byte>();
        NumberMap<Byte,String> map_ = new NumberMap<Byte,String>();
        map_.put(new Byte((byte)1), "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+NUMBER_MAP+" class=\""+MAPS+"\" field=\"map\"><java.lang.Byte class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+NUMBER_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString22Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<Float> containers_ = new Maps<Float>();
        NumberMap<Float,String> map_ = new NumberMap<Float,String>();
        map_.put(new Float(1), "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+NUMBER_MAP+" class=\""+MAPS+"\" field=\"map\"><java.lang.Float class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1.0\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+NUMBER_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString23Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<Double> containers_ = new Maps<Double>();
        NumberMap<Double,String> map_ = new NumberMap<Double,String>();
        map_.put(new Double(1), "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+NUMBER_MAP+" class=\""+MAPS+"\" field=\"map\"><java.lang.Double class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1.0\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+NUMBER_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString24Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<BigInteger> containers_ = new Maps<BigInteger>();
        NumberMap<BigInteger,String> map_ = new NumberMap<BigInteger,String>();
        map_.put(new BigInteger("1"), "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+NUMBER_MAP+" class=\""+MAPS+"\" field=\"map\"><java.math.BigInteger class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+NUMBER_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString25Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<BigDecimal> containers_ = new Maps<BigDecimal>();
        NumberMap<BigDecimal,String> map_ = new NumberMap<BigDecimal,String>();
        map_.put(new BigDecimal("1.0"), "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+NUMBER_MAP+" class=\""+MAPS+"\" field=\"map\"><java.math.BigDecimal class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1.0\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+NUMBER_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString26Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<AtomicInteger> containers_ = new Maps<AtomicInteger>();
        NumberMap<AtomicInteger,String> map_ = new NumberMap<AtomicInteger,String>();
        map_.put(new AtomicInteger(1), "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+NUMBER_MAP+" class=\""+MAPS+"\" field=\"map\"><java.util.concurrent.atomic.AtomicInteger class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+NUMBER_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString27Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<AtomicLong> containers_ = new Maps<AtomicLong>();
        NumberMap<AtomicLong,String> map_ = new NumberMap<AtomicLong,String>();
        map_.put(new AtomicLong(1), "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+NUMBER_MAP+" class=\""+MAPS+"\" field=\"map\"><java.util.concurrent.atomic.AtomicLong class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+NUMBER_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString28Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><null/>",SerializeXmlObject.toXmlString(null));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString29Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        array_.setArray(new Object[1]);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"java.lang.Object\"><null/></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString30Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        array_.setArray(new Integer[1][4]);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[Ljava.lang.Integer;\"><array type=\"java.lang.Integer\"><null/><null/><null/><null/></array></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString31Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        array_.setArray(new boolean[1][1]);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[Z\"><array type=\"boolean\"><java.lang.Boolean value=\"false\"/></array></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString32Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        array_.setArray(new int[1][1]);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[I\"><array type=\"int\"><java.lang.Integer value=\"0\"/></array></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString33Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        array_.setArray(new boolean[1][1][1]);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[[Z\"><array type=\"[Z\"><array type=\"boolean\"><java.lang.Boolean value=\"false\"/></array></array></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString34Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        array_.setArray(new int[1][1][1]);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[[I\"><array type=\"[I\"><array type=\"int\"><java.lang.Integer value=\"0\"/></array></array></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString35Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        array_.setArray(new long[1][1]);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[J\"><array type=\"long\"><java.lang.Long value=\"0\"/></array></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString36Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        array_.setArray(new short[1][1]);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[S\"><array type=\"short\"><java.lang.Short value=\"0\"/></array></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString37Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        array_.setArray(new byte[1][1]);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[B\"><array type=\"byte\"><java.lang.Byte value=\"0\"/></array></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString38Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        char[][] ch_ = new char[1][1];
        ch_[0][0] = '0';
        array_.setArray(ch_);
//        assertXMLEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[C\"><array type=\"char\"><java.lang.Character value=\"&#0;\"/></array></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[C\"><array type=\"char\"><java.lang.Character value=\"0\"/></array></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString39Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        array_.setArray(new float[1][1]);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[F\"><array type=\"float\"><java.lang.Float value=\"0.0\"/></array></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString40Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        array_.setArray(new double[1][1]);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[D\"><array type=\"double\"><java.lang.Double value=\"0.0\"/></array></array></"+REFS_ARRAY+">",SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString41Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Maps<Object> containers_ = new Maps<Object>();
        IdMap<Object,String> map_ = new IdMap<Object,String>();
        map_.put(new Object[0], "STR_ONE");
        containers_.setMap(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MAPS+"><"+ID_MAP+" class=\""+MAPS+"\" field=\"map\"><array class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" type=\"java.lang.Object\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/></"+ID_MAP+"></"+MAPS+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString42Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Container containers_ = new Container();
        TreeMap<String, Integer> tree_ = new TreeMap<String, Integer>(new MyStringComparator(-1));
        tree_.put("A", 1);
        tree_.put("B", 2);
        containers_.setObject(tree_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+CONTAINER+"><"+TREE_MAP+" class=\""+CONTAINER+"\" field=\"object\"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"B\"/><java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"2\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"A\"/><java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"1\"/><"+MY_STRING_COMPARATOR+" class=\""+TREE_MAP+"\" field=\"comparator\"><java.lang.Integer class=\""+MY_STRING_COMPARATOR+"\" field=\"mult\" value=\"-1\"/></"+MY_STRING_COMPARATOR+"></"+TREE_MAP+"></"+CONTAINER+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Test
    public void toXmlString43Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        int[] arrayInt_ = new int[1];
        array_.setArray(new int[][]{arrayInt_, arrayInt_});
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[I\"><array id=\"0\" type=\"int\"><java.lang.Integer value=\"0\"/></array><array ref=\"0\" type=\"int\"/></array></"+REFS_ARRAY+">", SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString44Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Container container_ = new Container();
        Object[] array_ = new Object[1];
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        array_[0] = comp_;
        container_.setObject(array_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+CONTAINER+"><array class=\""+CONTAINER+"\" field=\"object\" type=\"java.lang.Object\"><"+COMPOSITE_TWO+"><java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/></"+COMPOSITE_TWO+"></array></"+CONTAINER+">", SerializeXmlObject.toXmlString(container_));
    }

    @Test
    public void toXmlString45Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        Container container_ = new Container();
        Object[] array_ = new Object[2];
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        array_[0] = comp_;
        array_[1] = comp_;
        container_.setObject(array_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+CONTAINER+"><array class=\""+CONTAINER+"\" field=\"object\" type=\"java.lang.Object\"><"+COMPOSITE_TWO+" id=\"0\"><java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/></"+COMPOSITE_TWO+"><"+COMPOSITE_TWO+" ref=\"0\"/></array></"+CONTAINER+">", SerializeXmlObject.toXmlString(container_));
    }

    @Test
    public void toXmlString46Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray array_ = new RefsArray();
        Object[] arrayInt_ = new Object[1];
        arrayInt_[0] = arrayInt_;
        array_.setArray(arrayInt_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" id=\"0\" type=\"java.lang.Object\"><array ref=\"0\" type=\"java.lang.Object\"/></array></"+REFS_ARRAY+">", SerializeXmlObject.toXmlString(array_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString47Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Container container_ = new Container();
        ObjectMap<CompositeTwo,String> map_;
        map_ = new ObjectMap<CompositeTwo,String>();
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        map_.put(comp_, "FOUR");
        container_.setObject(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+CONTAINER+"><"+OBJECT_MAP+" class=\""+CONTAINER+"\" field=\"object\"><"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\"><java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/></"+COMPOSITE_TWO+"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"FOUR\"/></"+OBJECT_MAP+"></"+CONTAINER+">", SerializeXmlObject.toXmlString(container_));
    }

    @Test
    public void toXmlString48Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        RefsArray refs_ = new RefsArray();
        Object[] array_ = new Object[3];
        CompositeTwo pr_ = new CompositeTwo();
        pr_.setPrimitive(4);
        array_[0] = pr_;
        array_[1] = pr_;
        array_[2] = pr_;
        refs_.setArray(array_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"java.lang.Object\"><"+COMPOSITE_TWO+" id=\"0\"><java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/></"+COMPOSITE_TWO+"><"+COMPOSITE_TWO+" ref=\"0\"/><"+COMPOSITE_TWO+" ref=\"0\"/></array></"+REFS_ARRAY+">", SerializeXmlObject.toXmlString(refs_));
    }

    @Test
    public void toXmlString49Test() {
        SerializeXmlObject.setReferences(false);
        SerializeXmlObject.setCheckReferences(true);
        RefsArray refs_ = new RefsArray();
        Object[] array_ = new Object[3];
        CompositeTwo pr_ = new CompositeTwo();
        pr_.setPrimitive(4);
        array_[0] = pr_;
        array_[1] = pr_;
        array_[2] = pr_;
        refs_.setArray(array_);
        assertEq("", SerializeXmlObject.toXmlString(refs_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString50Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Container containers_ = new Container();
        NatTreeMap<String, Integer> tree_ = new NatTreeMap<String, Integer>();
        tree_.put("A", 1);
        tree_.put("B", 2);
        containers_.setObject(tree_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+CONTAINER+"><"+NAT_TREE_MAP+" class=\""+CONTAINER+"\" field=\"object\"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"A\"/><java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"1\"/><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"B\"/><java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"2\"/></"+NAT_TREE_MAP+"></"+CONTAINER+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString51Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Container container_ = new Container();
        IdMap<Object,String> map_;
        map_ = new IdMap<Object,String>();
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        map_.put(comp_, "FOUR");
        comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        map_.put(comp_, "FOUR");
        container_.setObject(map_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+CONTAINER+"><"+ID_MAP+" class=\""+CONTAINER+"\" field=\"object\"><"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\"><java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/></"+COMPOSITE_TWO+"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"FOUR\"/><"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\"><java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/></"+COMPOSITE_TWO+"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"FOUR\"/></"+ID_MAP+"></"+CONTAINER+">", SerializeXmlObject.toXmlString(container_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString52Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Container containers_ = new Container();
        PrimitiveThree p_ = new PrimitiveThree();
        p_.setPrimitive(2);
        containers_.setObject(p_);
        assertXmlEqualRuntime("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+CONTAINER+"><"+PRIMITIVE_THREE+" class=\""+CONTAINER+"\" field=\"object\" value=\"2\"/></"+CONTAINER+">", SerializeXmlObject.toXmlString(containers_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString53Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        CompositePrivate pr_ = new CompositePrivate();
        pr_.setBool(true);
        pr_.setCharacter('8');
        pr_.setElement(MyEnum.TWO);
        pr_.setInteger(15);
        Primitive prTwo_ = new Primitive();
        prTwo_.setPrimitive(6);
        pr_.setPrimitive(prTwo_);
        PrimitiveTwo prThree_ = new PrimitiveTwo();
        prThree_.setPrimitive(8);
        pr_.setPrimitiveTwo(prThree_);
        pr_.setString("STR");
        pr_.setTransientMember("unsaved");
        assertEq("",SerializeXmlObject.toXmlString(pr_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString54Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        InternStaticStandard int_ = new InternStaticStandard();
        assertEq("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+INTERNS_CLASSES+" intern=\"$InternStaticStandard\"/>",SerializeXmlObject.toXmlString(int_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString55Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        InternStandardTwo int_ = new InternsClasses().new InternStandardTwo();
        assertEq("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+INTERNS_CLASSES+" intern=\"$InternStandardTwo\"><"+INTERNS_CLASSES+" class=\""+INTERN_TWO+"\" field=\"this$0\"/></"+INTERNS_CLASSES+">",SerializeXmlObject.toXmlString(int_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void toXmlString56Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Container container_ = new Container();
        EnumMap<MyEnum,String> map_;
        map_ = new EnumMap<MyEnum,String>();
        map_.put(MyEnum.ONE, "1");
        container_.setObject(map_);
        assertEq("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+CONTAINER+"><"+ENUM_MAP+" class=\""+CONTAINER+"\" field=\"object\"><"+MY_ENUM+" class=\""+SerializeXmlObject.MP_CLASS+"\" key=\"\" value=\"ONE\"/><java.lang.String class=\""+SerializeXmlObject.MP_CLASS+"\" value=\"1\"/></"+ENUM_MAP+"></"+CONTAINER+">", SerializeXmlObject.toXmlString(container_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject1Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        int res_ = (Integer) SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><java.lang.Integer value=\"1\"/>");
        assertEq(1, res_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject2Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        assertEq(true, SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><java.lang.Boolean value=\"true\"/>"));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject3Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        assertEq("STRING", SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><java.lang.String value=\"STRING\"/>"));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject4Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        char res_ = (Character) SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><java.lang.Character value=\"_\"/>");
        assertEq('_', res_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject5Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        assertEq(MyEnum.TWO, (MyEnum) SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+MY_ENUM+" value=\"TWO\"/>"));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject6Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+PRIMITIVE+" value=\"2\"/>";
        Primitive res_ = (Primitive) SerializeXmlObject.fromXmlStringObject(xml_);
        assertEq(2, res_.getPrimitive().intValue());
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject7Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+PRIMITIVE_TWO+" value=\"2\"/>";
        PrimitiveTwo res_ = (PrimitiveTwo) SerializeXmlObject.fromXmlStringObject(xml_);
        assertEq(2, res_.getPrimitive().intValue());
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject8Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+COMPOSITE+">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE+"\" field=\"integer\" value=\"15\"/>";
        xml_ += "<java.lang.Boolean class=\""+COMPOSITE+"\" field=\"bool\" value=\"true\"/>";
        xml_ += "<java.lang.String class=\""+COMPOSITE+"\" field=\"string\" value=\"STR\"/>";
        xml_ += "<java.lang.Character class=\""+COMPOSITE+"\" field=\"character\" value=\"8\"/>";
        xml_ += "<"+MY_ENUM+" class=\""+COMPOSITE+"\" field=\"element\" value=\"TWO\"/>";
        xml_ += "<"+PRIMITIVE+" class=\""+COMPOSITE+"\" field=\"primitive\" value=\"6\"/>";
        xml_ += "<"+PRIMITIVE_TWO+" class=\""+COMPOSITE+"\" field=\"primitiveTwo\" value=\"8\"/>";
        xml_ += "</"+COMPOSITE+">";
        Composite res_ = (Composite) SerializeXmlObject.fromXmlStringObject(xml_);
        assertEq(15, res_.getInteger());
        assertEq(true, res_.isBool());
        assertEq("STR", res_.getString());
        assertEq('8', res_.getCharacter());
        assertEq(MyEnum.TWO, res_.getElement());
        assertEq(6, res_.getPrimitive().getPrimitive().intValue());
        assertEq(8, res_.getPrimitiveTwo().getPrimitive().intValue());
        assertEq("TRANSIENT", res_.getTransientMember());
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject9Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+COMPOSITE+">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE+"\" field=\"integer\" value=\"15\"/>";
        xml_ += "<java.lang.Boolean class=\""+COMPOSITE+"\" field=\"bool\" value=\"true\"/>";
        xml_ += "<null class=\""+COMPOSITE+"\" field=\"string\"/>";
        xml_ += "<java.lang.Character class=\""+COMPOSITE+"\" field=\"character\" value=\"8\"/>";
        xml_ += "<"+MY_ENUM+" class=\""+COMPOSITE+"\" field=\"element\" value=\"TWO\"/>";
        xml_ += "<"+PRIMITIVE+" class=\""+COMPOSITE+"\" field=\"primitive\" value=\"6\"/>";
        xml_ += "<"+PRIMITIVE_TWO+" class=\""+COMPOSITE+"\" field=\"primitiveTwo\" value=\"8\"/>";
        xml_ += "</"+COMPOSITE+">";
        Composite res_ = (Composite) SerializeXmlObject.fromXmlStringObject(xml_);
        assertEq(15, res_.getInteger());
        assertEq(true, res_.isBool());
        assertNull(res_.getString());
        assertEq('8', res_.getCharacter());
        assertEq(MyEnum.TWO, res_.getElement());
        assertEq(6, res_.getPrimitive().getPrimitive().intValue());
        assertEq(8, res_.getPrimitiveTwo().getPrimitive().intValue());
        assertEq("TRANSIENT", res_.getTransientMember());
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject10Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINERS+">";
        xml_ += "<"+STRING_LIST+" class=\""+CONTAINERS+"\" field=\"list\">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.LS_CLASS+"' value=\"ELEMENT\"/>";
        xml_ += "</"+STRING_LIST+">";
        xml_ += "<"+TREE_MAP+" class=\""+CONTAINERS+"\" field=\"treemap\">";
        xml_ += "<"+NATURAL_COMPARATOR+" class='"+TREE_MAP+"' field='comparator'/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"A\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"B\"/>";
        xml_ += "<java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"1\"/>";
        xml_ += "<java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"2\"/>";
        xml_ += "</"+TREE_MAP+">";
        xml_ += "<"+STRING_MAP+" class=\""+CONTAINERS+"\" field=\"map\">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"STR\"/>";
        xml_ += "<"+MY_ENUM+" class='"+SerializeXmlObject.MP_CLASS+"' value=\"ONE\"/>";
        xml_ += "</"+STRING_MAP+">";
        xml_ += "<array class=\""+CONTAINERS+"\" field=\"array\" type=\"int\">";
        xml_ += "<java.lang.Integer value=\"5\"/>";
        xml_ += "<java.lang.Integer value=\"9\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+CONTAINERS+"\" field=\"arrayDouble\" type=\"[I\">";
        xml_ += "<array type=\"int\">";
        xml_ += "<java.lang.Integer value=\"5\"/>";
        xml_ += "<java.lang.Integer value=\"9\"/>";
        xml_ += "</array>";
        xml_ += "<array type=\"int\">";
        xml_ += "<java.lang.Integer value=\"4\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+CONTAINERS+">";
        Containers containers_ = (Containers) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(StringList.class, containers_.getList().getClass());
        assertEq(1, containers_.getList().size());
        assertEq("ELEMENT", containers_.getList().first());
        assertSame(TreeMap.class, containers_.getTreemap().getClass());
        assertEq(2, containers_.getTreemap().size());
        assertEq("A", containers_.getTreemap().getKey(0));
        assertSame(Integer.class, containers_.getTreemap().getVal("A").getClass());
        assertEq(1, containers_.getTreemap().getVal("A").intValue());
        assertEq(1, containers_.getTreemap().getValue(0));
        assertEq("B", containers_.getTreemap().getKey(1));
        assertSame(Integer.class, containers_.getTreemap().getVal("B").getClass());
        assertEq(2, containers_.getTreemap().getVal("B").intValue());
        assertEq(2, containers_.getTreemap().getValue(1));
        assertSame(StringMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        assertEq(MyEnum.ONE, containers_.getMap().getVal("STR"));
        assertSame(int[].class, containers_.getArray().getClass());
        assertEq(2, containers_.getArray().length);
        assertEq(5, containers_.getArray()[0]);
        assertEq(9, containers_.getArray()[1]);
        assertSame(int[][].class, containers_.getArrayDouble().getClass());
        assertEq(2, containers_.getArrayDouble().length);
        assertSame(int[].class, containers_.getArrayDouble()[0].getClass());
        assertEq(2, containers_.getArrayDouble()[0].length);
        assertEq(5, containers_.getArrayDouble()[0][0]);
        assertEq(9, containers_.getArrayDouble()[0][1]);
        assertSame(int[].class, containers_.getArrayDouble()[1].getClass());
        assertEq(1, containers_.getArrayDouble()[1].length);
        assertEq(4, containers_.getArrayDouble()[1][0]);
    }

    @Test
    public void fromXmlStringObject11Test() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS+">";
        xml_ += "<"+REF_ONE+" class=\""+REFS+"\" field=\"ref\" id=\"0\">";
        xml_ += "<"+REF_TWO+" class=\""+REF_ONE+"\" field=\"refTwo\">";
        xml_ += "<"+REF_ONE+" class=\""+REF_TWO+"\" field=\"refOne\" ref=\"0\"/>";
        xml_ += "</"+REF_TWO+">";
        xml_ += "</"+REF_ONE+">";
        xml_ += "</"+REFS+">";
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        Refs refs_ = (Refs) SerializeXmlObject.fromXmlStringObject(xml_);
        RefOne refOne_ = refs_.getRef();
        RefTwo refTwo_ = refOne_.getRefTwo();
        assertSame(refOne_, refTwo_.getRefOne());
        assertSame(refTwo_, refTwo_.getRefOne().getRefTwo());
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject12Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_ENUM+">";
        xml_ += "<"+ENUM_MAP+" class=\""+MAPS_ENUM+"\" field=\"map\">";
        xml_ += "<"+MY_ENUM+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"ONE\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+ENUM_MAP+">";
        xml_ += "</"+MAPS_ENUM+">";
        MapsEnum containers_ = (MapsEnum) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(EnumMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        assertEq("STR_ONE", containers_.getMap().getVal(MyEnum.ONE));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject13Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_OBJECT+">";
        xml_ += "<"+ID_MAP+" class=\""+MAPS_OBJECT+"\" field=\"map\">";
        xml_ += "<null class='"+SerializeXmlObject.MP_CLASS+"' key=\"\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+ID_MAP+">";
        xml_ += "</"+MAPS_OBJECT+">";
        MapsObject containers_ = (MapsObject) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(IdMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        assertEq("STR_ONE", containers_.getMap().getVal(null));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject14Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_BOOLEAN+">";
        xml_ += "<"+BOOLEAN_MAP+" class=\""+MAPS_BOOLEAN+"\" field=\"map\">";
        xml_ += "<java.lang.Boolean class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"true\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+BOOLEAN_MAP+">";
        xml_ += "</"+MAPS_BOOLEAN+">";
        MapsBoolean containers_ = (MapsBoolean) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(BooleanMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        assertEq("STR_ONE", containers_.getMap().getVal(true));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject15Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_LONG+">";
        xml_ += "<"+NUMBER_MAP+" class=\""+MAPS_LONG+"\" field=\"map\">";
        xml_ += "<java.lang.Long class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+NUMBER_MAP+">";
        xml_ += "</"+MAPS_LONG+">";
        MapsLong containers_ = (MapsLong) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(NumberMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        assertEq("STR_ONE", containers_.getMap().getVal(1L));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject16Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_SHORT+">";
        xml_ += "<"+NUMBER_MAP+" class=\""+MAPS_SHORT+"\" field=\"map\">";
        xml_ += "<java.lang.Short class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+NUMBER_MAP+">";
        xml_ += "</"+MAPS_SHORT+">";
        MapsShort containers_ = (MapsShort) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(NumberMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        assertEq("STR_ONE", containers_.getMap().getVal(new Short((short) 1)));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject17Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_BYTE+">";
        xml_ += "<"+NUMBER_MAP+" class=\""+MAPS_BYTE+"\" field=\"map\">";
        xml_ += "<java.lang.Byte class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+NUMBER_MAP+">";
        xml_ += "</"+MAPS_BYTE+">";
        MapsByte containers_ = (MapsByte) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(NumberMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        assertEq("STR_ONE", containers_.getMap().getVal(new Byte((byte) 1)));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject18Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_FLOAT+">";
        xml_ += "<"+NUMBER_MAP+" class=\""+MAPS_FLOAT+"\" field=\"map\">";
        xml_ += "<java.lang.Float class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1.0\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+NUMBER_MAP+">";
        xml_ += "</"+MAPS_FLOAT+">";
        MapsFloat containers_ = (MapsFloat) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(NumberMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        assertEq("STR_ONE", containers_.getMap().getVal(new Float(1)));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject19Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_DOUBLE+">";
        xml_ += "<"+NUMBER_MAP+" class=\""+MAPS_DOUBLE+"\" field=\"map\">";
        xml_ += "<java.lang.Double class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1.0\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+NUMBER_MAP+">";
        xml_ += "</"+MAPS_DOUBLE+">";
        MapsDouble containers_ = (MapsDouble) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(NumberMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        assertEq("STR_ONE", containers_.getMap().getVal(new Double(1)));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject20Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_BIG_INTEGER+">";
        xml_ += "<"+NUMBER_MAP+" class=\""+MAPS_BIG_INTEGER+"\" field=\"map\">";
        xml_ += "<java.math.BigInteger class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+NUMBER_MAP+">";
        xml_ += "</"+MAPS_BIG_INTEGER+">";
        MapsBigInteger containers_ = (MapsBigInteger) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(NumberMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        assertEq("STR_ONE", containers_.getMap().getVal(new BigInteger("1")));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject21Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_BIG_DECIMAL+">";
        xml_ += "<"+NUMBER_MAP+" class=\""+MAPS_BIG_DECIMAL+"\" field=\"map\">";
        xml_ += "<java.math.BigDecimal class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1.0\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+NUMBER_MAP+">";
        xml_ += "</"+MAPS_BIG_DECIMAL+">";
        MapsBigDecimal containers_ = (MapsBigDecimal) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(NumberMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        assertEq("STR_ONE", containers_.getMap().getVal(new BigDecimal("1.0")));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject22Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_AT_INTEGER+">";
        xml_ += "<"+NUMBER_MAP+" class=\""+MAPS_AT_INTEGER+"\" field=\"map\">";
        xml_ += "<java.util.concurrent.atomic.AtomicInteger class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+NUMBER_MAP+">";
        xml_ += "</"+MAPS_AT_INTEGER+">";
        MapsAtomicInteger containers_ = (MapsAtomicInteger) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(NumberMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        AtomicInteger at_ = (AtomicInteger) containers_.getMap().getKeys().first();
        assertEq(1, at_.intValue());
        assertEq("STR_ONE", containers_.getMap().getVal(at_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject23Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_AT_LONG+">";
        xml_ += "<"+NUMBER_MAP+" class=\""+MAPS_AT_LONG+"\" field=\"map\">";
        xml_ += "<java.util.concurrent.atomic.AtomicLong class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+NUMBER_MAP+">";
        xml_ += "</"+MAPS_AT_LONG+">";
        MapsAtomicLong containers_ = (MapsAtomicLong) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(NumberMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        AtomicLong at_ = (AtomicLong) containers_.getMap().getKeys().first();
        assertEq(1, at_.intValue());
        assertEq("STR_ONE", containers_.getMap().getVal(at_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject24Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        assertNull(SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><null/>"));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject25Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"java.lang.Object\">";
        xml_ += "<null/>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray ref_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(Object[].class, ref_.getArray().getClass());
        assertEq(1, ref_.getArray().length);
        assertNull(ref_.getArray()[0]);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject26Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[Ljava.lang.Integer;\">";
        xml_ += "<array type=\"java.lang.Integer\">";
        xml_ += "<java.lang.Integer value=\"3\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray ref_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(Integer[][].class, ref_.getArray().getClass());
        Integer[][] arr_ = (Integer[][]) ref_.getArray();
        assertEq(1, arr_.length);
        assertEq(1, arr_[0].length);
        assertEq(new Integer("3"), arr_[0][0]);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject27Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[Z\">";
        xml_ += "<array type=\"boolean\">";
        xml_ += "<java.lang.Boolean value=\"true\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray ref_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(boolean[][].class, ref_.getArray().getClass());
        boolean[][] arr_ = (boolean[][]) ref_.getArray();
        assertEq(1, arr_.length);
        assertEq(1, arr_[0].length);
        assertEq(true, arr_[0][0]);
    }


    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject28Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[[Z\">";
        xml_ += "<array type=\"[Z\">";
        xml_ += "<array type=\"boolean\">";
        xml_ += "<java.lang.Boolean value=\"true\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray ref_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(boolean[][][].class, ref_.getArray().getClass());
        boolean[][][] arr_ = (boolean[][][]) ref_.getArray();
        assertEq(1, arr_.length);
        assertEq(1, arr_[0].length);
        assertEq(1, arr_[0][0].length);
        assertEq(true, arr_[0][0][0]);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject29Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[[I\">";
        xml_ += "<array type=\"[I\">";
        xml_ += "<array type=\"int\">";
        xml_ += "<java.lang.Integer value=\"4\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray ref_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(int[][][].class, ref_.getArray().getClass());
        int[][][] arr_ = (int[][][]) ref_.getArray();
        assertEq(1, arr_.length);
        assertEq(1, arr_[0].length);
        assertEq(1, arr_[0][0].length);
        assertEq(4, arr_[0][0][0]);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject30Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[J\">";
        xml_ += "<array type=\"long\">";
        xml_ += "<java.lang.Long value=\"3\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray ref_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(long[][].class, ref_.getArray().getClass());
        long[][] arr_ = (long[][]) ref_.getArray();
        assertEq(1, arr_.length);
        assertEq(1, arr_[0].length);
        assertEq(3l, arr_[0][0]);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject31Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[S\">";
        xml_ += "<array type=\"short\">";
        xml_ += "<java.lang.Short value=\"3\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray ref_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(short[][].class, ref_.getArray().getClass());
        short[][] arr_ = (short[][]) ref_.getArray();
        assertEq(1, arr_.length);
        assertEq(1, arr_[0].length);
        assertEq(3, arr_[0][0]);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject32Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[B\">";
        xml_ += "<array type=\"byte\">";
        xml_ += "<java.lang.Byte value=\"3\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray ref_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(byte[][].class, ref_.getArray().getClass());
        byte[][] arr_ = (byte[][]) ref_.getArray();
        assertEq(1, arr_.length);
        assertEq(1, arr_[0].length);
        assertEq(3, arr_[0][0]);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject33Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[C\">";
        xml_ += "<array type=\"char\">";
        xml_ += "<java.lang.Character value=\"3\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray ref_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(char[][].class, ref_.getArray().getClass());
        char[][] arr_ = (char[][]) ref_.getArray();
        assertEq(1, arr_.length);
        assertEq(1, arr_[0].length);
        assertEq('3', arr_[0][0]);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject34Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[F\">";
        xml_ += "<array type=\"float\">";
        xml_ += "<java.lang.Float value=\"3.0\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray ref_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(float[][].class, ref_.getArray().getClass());
        float[][] arr_ = (float[][]) ref_.getArray();
        assertEq(1, arr_.length);
        assertEq(1, arr_[0].length);
        assertEq(3.0, arr_[0][0]);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject35Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[D\">";
        xml_ += "<array type=\"double\">";
        xml_ += "<java.lang.Double value=\"3.0\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray ref_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(double[][].class, ref_.getArray().getClass());
        double[][] arr_ = (double[][]) ref_.getArray();
        assertEq(1, arr_.length);
        assertEq(1, arr_[0].length);
        assertEq(3.0, arr_[0][0]);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject36Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_OBJECT+">";
        xml_ += "<"+ID_MAP+" class=\""+MAPS_OBJECT+"\" field=\"map\">";
        xml_ += "<array class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" type=\"java.lang.Object\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+ID_MAP+">";
        xml_ += "</"+MAPS_OBJECT+">";
        MapsObject containers_ = (MapsObject) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(IdMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        Object[] at_ = (Object[]) containers_.getMap().getKeys().first();
        assertSame(Object[].class, at_.getClass());
        assertEq(0, at_.length);
        assertEq("STR_ONE", containers_.getMap().getVal(at_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject37Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER_TREE_MAP+">";
        xml_ += "<"+TREE_MAP+" class=\""+CONTAINER_TREE_MAP+"\" field=\"object\">";
        xml_ += "<"+MY_STRING_COMPARATOR+" class=\""+TREE_MAP+"\" field=\"comparator\">";
        xml_ += "<java.lang.Integer class=\""+MY_STRING_COMPARATOR+"\" field=\"mult\" value=\"-1\"/>";
        xml_ += "</"+MY_STRING_COMPARATOR+">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"B\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"A\"/>";
        xml_ += "<java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"2\"/>";
        xml_ += "<java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"1\"/>";
        xml_ += "</"+TREE_MAP+">";
        xml_ += "</"+CONTAINER_TREE_MAP+">";
        ContainerTreeMap containers_ = (ContainerTreeMap) SerializeXmlObject.fromXmlStringObject(xml_);
        TreeMap<String,Integer> tree_ = containers_.getObject();
        MyStringComparator strCmp_ = (MyStringComparator) tree_.comparator();
        assertEq(-1, strCmp_.getMult());
        assertEq(2, tree_.size());
        assertEq("B", tree_.getKey(0));
        assertEq("A", tree_.getKey(1));
        assertEq(1, (Number)tree_.getVal("A"));
        assertEq(2, (Number)tree_.getVal("B"));
        assertEq(2, tree_.getValue(0));
        assertEq(1, tree_.getValue(1));
    }

    @Test
    public void fromXmlStringObject38Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[I\">";
        xml_ += "<array type=\"int\">";
        xml_ += "<java.lang.Integer value=\"0\"/>";
        xml_ += "</array>";
        xml_ += "<array type=\"int\">";
        xml_ += "<java.lang.Integer value=\"0\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray containers_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(int[][].class, containers_.getArray().getClass());
        int[][] ar_ = (int[][]) containers_.getArray();
        assertEq(2, ar_.length);
        assertEq(1, ar_[0].length);
        assertEq(0, ar_[0][0]);
        assertEq(1, ar_[1].length);
        assertEq(0, ar_[1][0]);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject39Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER_ARRAY+">";
        xml_ += "<array class=\""+CONTAINER_ARRAY+"\" field=\"object\" type=\"java.lang.Object\">";
        xml_ += "<"+COMPOSITE_TWO+">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/>";
        xml_ += "</"+COMPOSITE_TWO+">";
        xml_ += "</array>";
        xml_ += "</"+CONTAINER_ARRAY+">";
        ContainerArray containers_ = (ContainerArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(Object[].class, containers_.getObject().getClass());
        Object[] tree_ = (Object[]) containers_.getObject();
        assertEq(1, tree_.length);
        CompositeTwo comp_ = (CompositeTwo) tree_[0];
        assertEq(4, comp_.getPrimitive().intValue());
    }

    @Test
    public void fromXmlStringObject40Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[I\">";
        xml_ += "<array id=\"0\" type=\"int\">";
        xml_ += "<java.lang.Integer value=\"0\"/>";
        xml_ += "</array>";
        xml_ += "<array ref=\"0\" type=\"int\"/>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray containers_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(int[][].class, containers_.getArray().getClass());
        int[][] tree_ = (int[][]) containers_.getArray();
        assertEq(2, tree_.length);
        assertEq(1, tree_[0].length);
        assertEq(0, tree_[0][0]);
        assertSame(tree_[0], tree_[1]);
    }

    @Test
    public void fromXmlStringObject41Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[I\">";
        xml_ += "<array id=\"0\" type=\"int\">";
        xml_ += "<java.lang.Integer value=\"0\"/>";
        xml_ += "</array>";
        xml_ += "<array ref=\"0\" type=\"int\"/>";
        xml_ += "<array id=\"1\" type=\"int\">";
        xml_ += "<java.lang.Integer value=\"0\"/>";
        xml_ += "</array>";
        xml_ += "<array ref=\"1\" type=\"int\"/>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        RefsArray containers_ = (RefsArray) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(int[][].class, containers_.getArray().getClass());
        int[][] tree_ = (int[][]) containers_.getArray();
        assertEq(4, tree_.length);
        assertEq(1, tree_[0].length);
        assertEq(1, tree_[2].length);
        assertEq(0, tree_[0][0]);
        assertEq(0, tree_[2][0]);
        assertSame(tree_[0], tree_[1]);
        assertSame(tree_[2], tree_[3]);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject42Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER_OBJECT_MAP_CLASSIC+">";
        xml_ += "<"+OBJECT_MAP+" class=\""+CONTAINER_OBJECT_MAP_CLASSIC+"\" field=\"object\">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/>";
        xml_ += "</"+COMPOSITE_TWO+">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"FOUR\"/>";
        xml_ += "</"+OBJECT_MAP+">";
        xml_ += "</"+CONTAINER_OBJECT_MAP_CLASSIC+">";
        ContainerObjectMapClassic containers_ = (ContainerObjectMapClassic) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectMap<CompositeTwo,String> tree_ = containers_.getObject();
        assertEq(1, tree_.size());
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        assertEq("FOUR", tree_.getVal(comp_));
    }

    @Test
    public void fromXmlStringObject43Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER_COMPOSITE_TWO+">";
        xml_ += "<"+CUST_LIST+" class=\""+CONTAINER_COMPOSITE_TWO+"\" field=\"object\">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.LS_CLASS+"' id=\"0\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/>";
        xml_ += "</"+COMPOSITE_TWO+">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.LS_CLASS+"' ref=\"0\"/>";
        xml_ += "</"+CUST_LIST+">";
        xml_ += "</"+CONTAINER_COMPOSITE_TWO+">";
        ContainerCompositeTwo containers_ = (ContainerCompositeTwo) SerializeXmlObject.fromXmlStringObject(xml_);
        CustList<CompositeTwo> tree_ = containers_.getObject();
        assertEq(2, tree_.size());
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        assertEq(comp_, (CompositeTwo)tree_.get(0));
        assertSame(tree_.get(0), tree_.get(1));
    }

    @Test
    public void fromXmlStringObject44Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER_COMPOSITE_TWO+">";
        xml_ += "<"+CUST_LIST+" class=\""+CONTAINER_COMPOSITE_TWO+"\" field=\"object\">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.LS_CLASS+"' id=\"0\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/>";
        xml_ += "</"+COMPOSITE_TWO+">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.LS_CLASS+"' ref=\"0\"/>";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.LS_CLASS+"' id=\"1\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/>";
        xml_ += "</"+COMPOSITE_TWO+">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.LS_CLASS+"' ref=\"1\"/>";
        xml_ += "</"+CUST_LIST+">";
        xml_ += "</"+CONTAINER_COMPOSITE_TWO+">";
        ContainerCompositeTwo containers_ = (ContainerCompositeTwo) SerializeXmlObject.fromXmlStringObject(xml_);
        CustList<CompositeTwo> tree_ = containers_.getObject();
        assertEq(4, tree_.size());
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        assertEq(comp_, (CompositeTwo)tree_.get(0));
        assertEq(comp_, (CompositeTwo)tree_.get(2));
        assertSame(tree_.get(0), tree_.get(1));
        assertSame(tree_.get(2), tree_.get(3));
    }

    @Test
    public void fromXmlStringObject45Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER_STRING_MAP+">";
        xml_ += "<"+STRING_MAP+" class=\""+CONTAINER_STRING_MAP+"\" field=\"object\">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"ref1\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"ref2\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"ref3\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"ref4\"/>";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' id=\"0\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/>";
        xml_ += "</"+COMPOSITE_TWO+">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' ref=\"0\"/>";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' id=\"1\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/>";
        xml_ += "</"+COMPOSITE_TWO+">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' ref=\"1\"/>";
        xml_ += "</"+STRING_MAP+">";
        xml_ += "</"+CONTAINER_STRING_MAP+">";
        ContainerStringMap containers_ = (ContainerStringMap) SerializeXmlObject.fromXmlStringObject(xml_);
        StringMap<CompositeTwo> tree_ = containers_.getObject();
        assertEq(4, tree_.size());
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        assertEq(comp_, (CompositeTwo)tree_.getVal("ref1"));
        assertEq(comp_, (CompositeTwo)tree_.getVal("ref3"));
        assertSame(tree_.getVal("ref1"), tree_.getVal("ref2"));
        assertSame(tree_.getVal("ref3"), tree_.getVal("ref4"));
    }

    @Test
    public void fromXmlStringObject46Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER_OBJECT_MAP_REF+">";
        xml_ += "<"+OBJECT_MAP+" class=\""+CONTAINER_OBJECT_MAP_REF+"\" field=\"object\">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" id=\"0\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/>";
        xml_ += "</"+COMPOSITE_TWO+">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' ref=\"0\"/>";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" id=\"1\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"5\"/>";
        xml_ += "</"+COMPOSITE_TWO+">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' ref=\"1\"/>";
        xml_ += "</"+OBJECT_MAP+">";
        xml_ += "</"+CONTAINER_OBJECT_MAP_REF+">";
        ContainerObjectMapRef containers_ = (ContainerObjectMapRef) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectMap<CompositeTwo,CompositeTwo> tree_ = containers_.getObject();
        assertEq(2, tree_.size());
        EqList<CompositeTwo> obj_ = tree_.getKeys();
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(5);
        assertTrue(obj_.containsObj(comp_));
        comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        assertTrue(obj_.containsObj(comp_));
//        CompositeTwo ret_ = obj_.first();
        assertSame(obj_.first(), tree_.getVal(obj_.first()));
        assertSame(obj_.last(), tree_.getVal(obj_.last()));
    }

    @Test
    public void fromXmlStringObject47Test() {
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER_OBJECT_MAP_REF+">";
        xml_ += "<"+OBJECT_MAP+" class=\""+CONTAINER_OBJECT_MAP_REF+"\" field=\"object\">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" id=\"0\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/>";
        xml_ += "</"+COMPOSITE_TWO+">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' ref=\"0\"/>";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' id=\"1\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"5\"/>";
        xml_ += "</"+COMPOSITE_TWO+">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" ref=\"1\"/>";
        xml_ += "</"+OBJECT_MAP+">";
        xml_ += "</"+CONTAINER_OBJECT_MAP_REF+">";
        ContainerObjectMapRef containers_ = (ContainerObjectMapRef) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectMap<CompositeTwo,CompositeTwo> tree_ = containers_.getObject();
        assertEq(2, tree_.size());
        EqList<CompositeTwo> obj_ = tree_.getKeys();
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(5);
        assertTrue(obj_.containsObj(comp_));
        comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        assertTrue(obj_.containsObj(comp_));
        assertSame(obj_.first(), tree_.getVal(obj_.first()));
        assertSame(obj_.last(), tree_.getVal(obj_.last()));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject48Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER_OBJECT_MAP_COMP+">";
        xml_ += "<"+OBJECT_MAP+" class=\""+CONTAINER_OBJECT_MAP_COMP+"\" field=\"object\">";
        xml_ += "<"+MAP_COMPONENT+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\">";
        xml_ += "<"+STRING_MAP+" class=\""+MAP_COMPONENT+"\" field=\"elements\">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"2\"/>";
        xml_ += "</"+STRING_MAP+">";
        xml_ += "</"+MAP_COMPONENT+">";
        xml_ += "<"+MAP_COMPONENT+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\">";
        xml_ += "<"+STRING_MAP+" class=\""+MAP_COMPONENT+"\" field=\"elements\">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"3\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"4\"/>";
        xml_ += "</"+STRING_MAP+">";
        xml_ += "</"+MAP_COMPONENT+">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"12\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"34\"/>";
        xml_ += "</"+OBJECT_MAP+">";
        xml_ += "</"+CONTAINER_OBJECT_MAP_COMP+">";
        ContainerObjectMapComp containers_ = (ContainerObjectMapComp) SerializeXmlObject.fromXmlStringObject(xml_);
        ObjectMap<MapComponent,String> tree_ = containers_.getObject();
        assertEq(2, tree_.size());
        MapComponent keyOne_ = new MapComponent();
        keyOne_.getElements().put("1", "2");
        assertEq("12", tree_.getVal(keyOne_));
        MapComponent keyTwo_ = new MapComponent();
        keyTwo_.getElements().put("3", "4");
        assertEq("34", tree_.getVal(keyTwo_));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject49Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER_NAT_TREE_MAP+">";
        xml_ += "<"+NAT_TREE_MAP+" class=\""+CONTAINER_NAT_TREE_MAP+"\" field=\"object\">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"A\"/>";
        xml_ += "<java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"1\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"B\"/>";
        xml_ += "<java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"2\"/>";
        xml_ += "</"+NAT_TREE_MAP+">";
        xml_ += "</"+CONTAINER_NAT_TREE_MAP+">";
        ContainerNatTreeMap containers_ = (ContainerNatTreeMap) SerializeXmlObject.fromXmlStringObject(xml_);
        NatTreeMap<String,Integer> tree_ = containers_.getObject();
        assertEq(2, tree_.size());
        assertEq("A", tree_.getKey(0));
        assertEq(1, (Number)tree_.getValue(0));
        assertEq("B", tree_.getKey(1));
        assertEq(2, (Number)tree_.getValue(1));
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject50Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER_ID_MAP_REF+">";
        xml_ += "<"+ID_MAP+" class=\""+CONTAINER_ID_MAP_REF+"\" field=\"object\">";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/>";
        xml_ += "</"+COMPOSITE_TWO+"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"FOUR\"/>";
        xml_ += "<"+COMPOSITE_TWO+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_TWO+"\" field=\"primitive\" value=\"4\"/>";
        xml_ += "</"+COMPOSITE_TWO+"><java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"FOUR\"/>";
        xml_ += "</"+ID_MAP+">";
        xml_ += "</"+CONTAINER_ID_MAP_REF+">";
        ContainerIdMapRef containers_ = (ContainerIdMapRef) SerializeXmlObject.fromXmlStringObject(xml_);
        IdMap<CompositeTwo,CompositeTwo> tree_ = containers_.getObject();
        assertEq(2, tree_.size());
        CompositeTwo k1_ = (CompositeTwo) tree_.getKey(0);
        CompositeTwo k2_ = (CompositeTwo) tree_.getKey(1);
        assertEq(4, k1_.getPrimitive().intValue());
        assertEq(4, k2_.getPrimitive().intValue());
        assertEq("FOUR", tree_.getVal(k1_));
        assertEq("FOUR", tree_.getVal(k2_));
        assertNotSame(k1_, k2_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject51Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER+">";
        xml_ += "<"+COMPOSITE_PRIMITIVABLE+" class=\""+CONTAINER+"\" field=\"object\" value=\"V\">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_PRIMITIVABLE+"\" field=\"integer\" value=\"15\"/>";
        xml_ += "<java.lang.Boolean class=\""+COMPOSITE_PRIMITIVABLE+"\" field=\"bool\" value=\"true\"/>";
        xml_ += "<java.lang.String class=\""+COMPOSITE_PRIMITIVABLE+"\" field=\"string\" value=\"STR\"/>";
        xml_ += "<java.lang.Character class=\""+COMPOSITE_PRIMITIVABLE+"\" field=\"character\" value=\"8\"/>";
        xml_ += "<"+MY_ENUM+" class=\""+COMPOSITE_PRIMITIVABLE+"\" field=\"element\" value=\"TWO\"/>";
        xml_ += "<"+PRIMITIVE+" class=\""+COMPOSITE_PRIMITIVABLE+"\" field=\"primitive\" value=\"6\"/>";
        xml_ += "<"+PRIMITIVE_TWO+" class=\""+COMPOSITE_PRIMITIVABLE+"\" field=\"primitiveTwo\" value=\"8\"/>";
        xml_ += "</"+COMPOSITE_PRIMITIVABLE+">";
        xml_ += "</"+CONTAINER+">";
        Container c_ = (Container) SerializeXmlObject.fromXmlStringObject(xml_);
        CompositePrimitivable res_ = (CompositePrimitivable) c_.getObject();
        assertSame(CompositePrimitivable.class, res_.getClass());
        assertEq(15, res_.getInteger());
        assertEq(true, res_.isBool());
        assertEq("STR", res_.getString());
        assertEq('8', res_.getCharacter());
        assertEq(MyEnum.TWO, res_.getElement());
        assertEq(6, res_.getPrimitive().getPrimitive().intValue());
        assertEq(8, res_.getPrimitiveTwo().getPrimitive().intValue());
        assertEq("TRANSIENT", res_.getTransientMember());
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject52Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER+">";
        xml_ += "<"+PRIMITIVE_FOUR+" class=\""+CONTAINER+"\" field=\"object\" value=\"5\">";
        xml_ += "<java.lang.Integer class=\""+PRIMITIVE_FOUR+"\" field=\"primitive\" value=\"4\"/>";
        xml_ += "</"+PRIMITIVE_FOUR+">";
        xml_ += "</"+CONTAINER+">";
        Container containers_ = (Container) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(PrimitiveFour.class, containers_.getObject().getClass());
        PrimitiveFour p_ = (PrimitiveFour) containers_.getObject();
        assertEq(5, p_.getPrimitive().intValue());
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject53Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER+">";
        xml_ += "<"+PRIMITIVE_FIVE+" class=\""+CONTAINER+"\" field=\"object\" value=\"5\"/>";
        xml_ += "</"+CONTAINER+">";
        Container containers_ = (Container) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(PrimitiveFive.class, containers_.getObject().getClass());
        PrimitiveFive p_ = (PrimitiveFive) containers_.getObject();
        assertEq(5, p_.getPrimitive().intValue());
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject54Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER+">";
        xml_ += "<"+MY_ENUM_TWO+" class=\""+CONTAINER+"\" field=\"object\" value=\"ONE\"/>";
        xml_ += "</"+CONTAINER+">";
        Container containers_ = (Container) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(MyEnumTwo.class, containers_.getObject().getClass());
        MyEnumTwo p_ = (MyEnumTwo) containers_.getObject();
        assertEq(MyEnumTwo.ONE, p_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject55Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER+">";
        xml_ += "<"+PRIMITIVE_FOUR+" class=\""+CONTAINER+"\" field=\"object\" value=\"5\">";
        xml_ += "<java.lang.Integer class=\"java.lang.Integer\" field=\"primitiveTwo\" value=\"5\"/>";
        xml_ += "<java.lang.Integer class=\""+PRIMITIVE_FOUR+"\" field=\"primitive\" value=\"1\"/>";
        xml_ += "<java.lang.Integer class=\""+PRIMITIVE_FOUR+"\" field=\"_nbInstances_\" value=\"3\"/>";
        xml_ += "<java.lang.Integer class=\""+PRIMITIVE_FOUR+"\" field=\"primitiveTwo\" value=\"2\"/>";
        xml_ += "</"+PRIMITIVE_FOUR+">";
        xml_ += "</"+CONTAINER+">";
        Container containers_ = (Container) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(PrimitiveFour.class, containers_.getObject().getClass());
        PrimitiveFour p_ = (PrimitiveFour) containers_.getObject();
        assertEq(5, p_.getPrimitive().intValue());
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject56Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        char res_ = (Character) SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><java.lang.Character value=\"&#65;\"/>");
        assertEq('A', res_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject57Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String res_ = (String) SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><java.lang.String value=\"&#65;\"/>");
        assertEq("A", res_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject58Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        char res_ = (Character) SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><java.lang.Character value=\"&#233;\"/>");
        assertEq(233, (int)res_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject59Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Object o_ = SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+INTERNS_CLASSES+" intern=\"$InternStaticStandard\"/>");
        assertSame(InternStaticStandard.class, o_.getClass());
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject60Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Object o_ = SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+INTERNS_CLASSES+" intern=\"$InternStaticStandard$InternStaticStandardThree\"/>");
        assertSame(InternStaticStandardThree.class, o_.getClass());
    }
    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject61Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Object o_ = SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+INTERNS_CLASSES+" intern=\"$InternStandardTwo\"><"+INTERNS_CLASSES+" class=\""+INTERN_TWO+"\" field=\"this$0\"/></"+INTERNS_CLASSES+">");
        assertSame(InternStandardTwo.class, o_.getClass());
    }
    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject62Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Object o_ = SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+INTERNS_CLASSES+" intern=\"$InternStandardTwo$InternStandardOne\"><"+INTERNS_CLASSES+" class=\""+INTERN_ONE+"\" field=\"this$0\"/></"+INTERNS_CLASSES+">");
        assertSame(InternStandardOne.class, o_.getClass());
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject63Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        Object o_ = SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+INTERNS_CLASSES+" intern=\"$InternStaticStandard$InternStaticStandardFour\"><"+INTERNS_CLASSES+" class=\""+INTERN_FOUR+"\" field=\"this$0\"/></"+INTERNS_CLASSES+">");
        assertSame(InternStaticStandardFour.class, o_.getClass());
    }

    @Parameters(method="booleanInputs")
    @Test
    public void fromXmlStringObject64Test(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_INTEGER+">";
        xml_ += "<"+NUMBER_MAP+" class=\""+MAPS_INTEGER+"\" field=\"map\">";
        xml_ += "<java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"1\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+NUMBER_MAP+">";
        xml_ += "</"+MAPS_INTEGER+">";
        MapsInteger containers_ = (MapsInteger) SerializeXmlObject.fromXmlStringObject(xml_);
        assertSame(NumberMap.class, containers_.getMap().getClass());
        assertEq(1, containers_.getMap().size());
        Integer at_ = (Integer) containers_.getMap().getKeys().first();
        assertEq(1, at_.intValue());
        assertEq("STR_ONE", containers_.getMap().getVal(at_));
    }

    @Parameters(method="booleanInputs")
    @Test(expected=NoAttributeForSerializable.class,timeout=1000)
    public void fromXmlStringObject1FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"java.lang.Object\"><java.lang.String/></array></"+REFS_ARRAY+">");
    }

    @Parameters(method="booleanInputs")
    @Test(expected=NoAttributeForSerializable.class)
    public void fromXmlStringObject2FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"java.lang.Object\"><java.lang.Integer/></array></"+REFS_ARRAY+">");
    }

    @Parameters(method="booleanInputs")
    @Test(expected=NoAttributeForSerializable.class)
    public void fromXmlStringObject3FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"java.lang.Object\"><java.lang.Boolean/></array></"+REFS_ARRAY+">");
    }


    @Parameters(method="booleanInputs")
    @Test(expected=NoAttributeForSerializable.class)
    public void fromXmlStringObject4FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        SerializeXmlObject.fromXmlStringObject("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><"+REFS_ARRAY+"><array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"java.lang.Object\"><java.lang.Character/></array></"+REFS_ARRAY+">");
    }

    @Parameters(method="booleanInputs")
    @Test(expected=RuntimeClassNotFoundException.class)
    public void fromXmlStringObject5FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINERS+">";
        xml_ += "<"+STRING_LIST+" class=\""+CONTAINERS+"\" field=\"list\">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.LS_CLASS+"' value=\"ELEMENT\"/>";
        xml_ += "</"+STRING_LIST+">";
        xml_ += "<"+TREE_MAP+" class=\""+CONTAINERS+"\" field=\"treemap\">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"A\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"B\"/>";
        xml_ += "<java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"1\"/>";
        xml_ += "<java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"2\"/>";
        xml_ += "</"+TREE_MAP+">";
        xml_ += "<"+STRING_MAP+" class=\""+CONTAINERS+"\" field=\"map\">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"STR\"/>";
        xml_ += "<"+MY_ENUM+" class='"+SerializeXmlObject.MP_CLASS+"' value=\"ONE\"/>";
        xml_ += "</"+STRING_MAP+">";
        xml_ += "<array class=\""+CONTAINERS+"\" field=\"array\" type=\"integer\">";
        xml_ += "<java.lang.Integer value=\"5\"/>";
        xml_ += "<java.lang.Integer value=\"9\"/>";
        xml_ += "</array>";
        xml_ += "</"+CONTAINERS+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=InexistingValueForEnum.class)
    public void fromXmlStringObject6FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_ENUM+">";
        xml_ += "<"+ENUM_MAP+" class=\""+MAPS_ENUM+"\" field=\"map\">";
        xml_ += "<"+MY_ENUM+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"ONE_1\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+ENUM_MAP+">";
        xml_ += "</"+MAPS_ENUM+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=NoAttributeForSerializable.class)
    public void fromXmlStringObject7FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS_ENUM+">";
        xml_ += "<"+ENUM_MAP+" class=\""+MAPS_ENUM+"\" field=\"map\">";
        xml_ += "<"+MY_ENUM+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+ENUM_MAP+">";
        xml_ += "</"+MAPS_ENUM+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=InvokingException.class)
    public void fromXmlStringObject8FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS+">";
        xml_ += "<"+OBJECT_MAP+" class=\""+MAPS+"\" field=\"map\">";
        xml_ += "<"+PRIMITIVE+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"ONE\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+OBJECT_MAP+">";
        xml_ += "</"+MAPS+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=NumberFormatException.class)
    public void fromXmlStringObject9FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+COMPOSITE+">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE+"\" field=\"integer\" value=\"FIFTEEN\"/>";
        xml_ += "<java.lang.Boolean class=\""+COMPOSITE+"\" field=\"bool\" value=\"true\"/>";
        xml_ += "<java.lang.String class=\""+COMPOSITE+"\" field=\"string\" value=\"STR\"/>";
        xml_ += "<java.lang.Character class=\""+COMPOSITE+"\" field=\"character\" value=\"8\"/>";
        xml_ += "<"+MY_ENUM+" class=\""+COMPOSITE+"\" field=\"element\" value=\"TWO\"/>";
        xml_ += "<"+PRIMITIVE+" class=\""+COMPOSITE+"\" field=\"primitive\" value=\"6\"/>";
        xml_ += "<"+PRIMITIVE_TWO+" class=\""+COMPOSITE+"\" field=\"primitiveTwo\" value=\"8\"/>";
        xml_ += "</"+COMPOSITE+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=IllegalArgumentException.class)
    public void fromXmlStringObject10FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[D\">";
        xml_ += "<array type=\"double\">";
        xml_ += "<java.lang.Boolean value=\"false\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=IllegalArgumentException.class)
    public void fromXmlStringObject11FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[D\">";
        xml_ += "<array type=\"float\">";
        xml_ += "<java.lang.Float value=\"1.5\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=NoAttributeForSerializable.class)
    public void fromXmlStringObject12FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS_ARRAY+">";
        xml_ += "<array class=\""+REFS_ARRAY+"\" field=\"array\" type=\"[D\">";
        xml_ += "<array>";
        xml_ += "<java.lang.Float value=\"1.5\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+REFS_ARRAY+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=IllegalArgumentException.class)
    public void fromXmlStringObject13FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINERS+">";
        xml_ += "<"+STRING_LIST+" class=\""+CONTAINERS+"\" field=\"list\">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.LS_CLASS+"' value=\"ELEMENT\"/>";
        xml_ += "</"+STRING_LIST+">";
        xml_ += "<"+TREE_MAP+" class=\""+CONTAINERS+"\" field=\"treemap\">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"A\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"B\"/>";
        xml_ += "<java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"1\"/>";
        xml_ += "<java.lang.Integer class='"+SerializeXmlObject.MP_CLASS+"' value=\"2\"/>";
        xml_ += "</"+TREE_MAP+">";
        xml_ += "<"+STRING_MAP+" class=\""+CONTAINERS+"\" field=\"map\">";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"STR\"/>";
        xml_ += "<"+MY_ENUM+" class='"+SerializeXmlObject.MP_CLASS+"' value=\"ONE\"/>";
        xml_ += "</"+STRING_MAP+">";
        xml_ += "<array class=\""+CONTAINERS+"\" field=\"array\" type=\"int\">";
        xml_ += "<java.lang.Integer value=\"5\"/>";
        xml_ += "<java.lang.Integer value=\"9\"/>";
        xml_ += "</array>";
        xml_ += "<array class=\""+CONTAINERS+"\" field=\"arrayDouble\" type=\"[I\">";
        xml_ += "<array type=\"int\">";
        xml_ += "<java.lang.String value=\"5\"/>";
        xml_ += "<java.lang.String value=\"9\"/>";
        xml_ += "</array>";
        xml_ += "<array type=\"int\">";
        xml_ += "<java.lang.Integer value=\"4\"/>";
        xml_ += "</array>";
        xml_ += "</array>";
        xml_ += "</"+CONTAINERS+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=NoSuchDeclaredMethodException.class)
    public void fromXmlStringObject14FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER+">";
        xml_ += "<java.lang.Number class=\""+CONTAINER+"\" field=\"object\" value=\"1\"/>";
        xml_ += "</"+CONTAINER+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=BadAccessException.class)
    public void fromXmlStringObject15FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER+">";
        xml_ += "<"+AB_MAP+" class=\""+CONTAINER+"\" field=\"object\">";
        xml_ += "<java.lang.String class=\""+AB_MAP+"\" field=\"privateField\" value=\"HELLO\"/>";
        xml_ += "</"+AB_MAP+">";
        xml_ += "</"+CONTAINER+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=NoSuchDeclaredMethodException.class)
    public void fromXmlStringObject16FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+CONTAINER+">";
        xml_ += "<"+PRIMITIVE_SIX+" class=\""+CONTAINER+"\" field=\"object\" type=\"java.lang.Object\" value=\"5\"/>";
        xml_ += "</"+CONTAINER+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=BadAccessException.class)
    public void fromXmlStringObject17FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+COMPOSITE_PRIVATE+">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_PRIVATE+"\" field=\"integer\" value=\"15\"/>";
        xml_ += "<java.lang.Boolean class=\""+COMPOSITE_PRIVATE+"\" field=\"bool\" value=\"true\"/>";
        xml_ += "<java.lang.String class=\""+COMPOSITE_PRIVATE+"\" field=\"string\" value=\"STR\"/>";
        xml_ += "<java.lang.Character class=\""+COMPOSITE_PRIVATE+"\" field=\"character\" value=\"8\"/>";
        xml_ += "<"+MY_ENUM+" class=\""+COMPOSITE_PRIVATE+"\" field=\"element\" value=\"TWO\"/>";
        xml_ += "<"+PRIMITIVE+" class=\""+COMPOSITE_PRIVATE+"\" field=\"primitive\" value=\"6\"/>";
        xml_ += "<"+PRIMITIVE_TWO+" class=\""+COMPOSITE_PRIVATE+"\" field=\"primitiveTwo\" value=\"8\"/>";
        xml_ += "</"+COMPOSITE_PRIVATE+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=NoSuchDeclaredMethodException.class)
    public void fromXmlStringObject18FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+COMPOSITE_NO_CONSTR+">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_NO_CONSTR+"\" field=\"integer\" value=\"15\"/>";
        xml_ += "<java.lang.Boolean class=\""+COMPOSITE_NO_CONSTR+"\" field=\"bool\" value=\"true\"/>";
        xml_ += "<java.lang.String class=\""+COMPOSITE_NO_CONSTR+"\" field=\"string\" value=\"STR\"/>";
        xml_ += "<java.lang.Character class=\""+COMPOSITE_NO_CONSTR+"\" field=\"character\" value=\"8\"/>";
        xml_ += "<"+MY_ENUM+" class=\""+COMPOSITE_NO_CONSTR+"\" field=\"element\" value=\"TWO\"/>";
        xml_ += "<"+PRIMITIVE+" class=\""+COMPOSITE_NO_CONSTR+"\" field=\"primitive\" value=\"6\"/>";
        xml_ += "<"+PRIMITIVE_TWO+" class=\""+COMPOSITE_NO_CONSTR+"\" field=\"primitiveTwo\" value=\"8\"/>";
        xml_ += "</"+COMPOSITE_NO_CONSTR+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=InvokingException.class)
    public void fromXmlStringObject19FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+COMPOSITE_CONSTR+">";
        xml_ += "<java.lang.Integer class=\""+COMPOSITE_CONSTR+"\" field=\"integer\" value=\"15\"/>";
        xml_ += "<java.lang.Boolean class=\""+COMPOSITE_CONSTR+"\" field=\"bool\" value=\"true\"/>";
        xml_ += "<java.lang.String class=\""+COMPOSITE_CONSTR+"\" field=\"string\" value=\"STR\"/>";
        xml_ += "<java.lang.Character class=\""+COMPOSITE_CONSTR+"\" field=\"character\" value=\"8\"/>";
        xml_ += "<"+MY_ENUM+" class=\""+COMPOSITE_CONSTR+"\" field=\"element\" value=\"TWO\"/>";
        xml_ += "<"+PRIMITIVE+" class=\""+COMPOSITE_CONSTR+"\" field=\"primitive\" value=\"6\"/>";
        xml_ += "<"+PRIMITIVE_TWO+" class=\""+COMPOSITE_CONSTR+"\" field=\"primitiveTwo\" value=\"8\"/>";
        xml_ += "</"+COMPOSITE_CONSTR+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }
    @Parameters(method="booleanInputs")
    @Test(expected=DuplicatesKeysException.class)
    public void fromXmlStringObject20FailTest(boolean _bool) {
        SerializeXmlObject.setReferences(_bool);
        SerializeXmlObject.setCheckReferences(false);
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+MAPS+">";
        xml_ += "<"+ENUM_MAP+" class=\""+MAPS+"\" field=\"map\">";
        xml_ += "<"+MY_ENUM+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"ONE\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "<"+MY_ENUM+" class='"+SerializeXmlObject.MP_CLASS+"' key=\"\" value=\"ONE\"/>";
        xml_ += "<java.lang.String class='"+SerializeXmlObject.MP_CLASS+"' value=\"STR_ONE\"/>";
        xml_ += "</"+ENUM_MAP+">";
        xml_ += "</"+MAPS+">";
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Test(expected=RefException.class)
    public void fromXmlStringObject21FailTest() {
        String xml_ = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
        xml_ += "<"+REFS+">";
        xml_ += "<"+REF_ONE+" class=\""+REFS+"\" field=\"ref\">";
        xml_ += "<"+REF_TWO+" class=\""+REF_ONE+"\" field=\"refTwo\">";
        xml_ += "<"+REF_ONE+" class=\""+REF_TWO+"\" field=\"refOne\" ref=\"0\"/>";
        xml_ += "</"+REF_TWO+">";
        xml_ += "</"+REF_ONE+">";
        xml_ += "</"+REFS+">";
        SerializeXmlObject.setReferences(true);
        SerializeXmlObject.setCheckReferences(false);
        SerializeXmlObject.fromXmlStringObject(xml_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void checkNullPointers1Test(boolean _bool) {
        SerializeXmlObject.setCheckReferences(_bool);
        SerializeXmlObject.checkNullPointers(1);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void checkNullPointers2Test(boolean _bool) {
        SerializeXmlObject.setCheckReferences(_bool);
        SerializeXmlObject.checkNullPointers(true);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void checkNullPointers3Test(boolean _bool) {
        SerializeXmlObject.setCheckReferences(_bool);
        StringList list_ = new StringList();
        list_.add("STRING");
        SerializeXmlObject.checkNullPointers(list_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void checkNullPointers4Test(boolean _bool) {
        SerializeXmlObject.setCheckReferences(_bool);
        StringMap<String> list_ = new StringMap<String>();
        list_.put("STRING","VALUE");
        SerializeXmlObject.checkNullPointers(list_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void checkNullPointers5Test(boolean _bool) {
        SerializeXmlObject.setCheckReferences(_bool);
        Object[] array_ = new Object[1];
        array_[0] = 1;
        SerializeXmlObject.checkNullPointers(array_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void checkNullPointers6Test(boolean _bool) {
        SerializeXmlObject.setCheckReferences(_bool);
        Object[] array_ = new Object[1];
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        array_[0] = comp_;
        SerializeXmlObject.checkNullPointers(array_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void checkNullPointers7Test(boolean _bool) {
        SerializeXmlObject.setCheckReferences(_bool);
        CustList<CompositeTwo> list_ = new CustList<CompositeTwo>();
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        list_.add(comp_);
        SerializeXmlObject.checkNullPointers(list_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void checkNullPointers8Test(boolean _bool) {
        SerializeXmlObject.setCheckReferences(_bool);
        Container containers_ = new Container();
        TreeMap<String, Integer> tree_ = new TreeMap<String, Integer>(new MyStringComparator(-1));
        tree_.put("A", 1);
        tree_.put("B", 2);
        containers_.setObject(tree_);
        SerializeXmlObject.checkNullPointers(containers_);
    }

    @Test
    public void checkNullPointers9Test() {
        SerializeXmlObject.setCheckReferences(false);
        Object[] array_ = new Object[2];
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        array_[0] = comp_;
        array_[1] = comp_;
        SerializeXmlObject.checkNullPointers(array_);
    }

    @Parameters(method="booleanInputs")
    @Test
    public void checkNullPointers10Test(boolean _bool) {
        SerializeXmlObject.setCheckReferences(_bool);
        Container containers_ = new Container();
        TreeMap<String, Integer> tree_ = new TreeMap<String, Integer>(new NaturalComparator<String>());
        tree_.put("A", 1);
        tree_.put("B", 2);
        containers_.setObject(tree_);
        SerializeXmlObject.checkNullPointers(containers_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=NullSerialException.class)
    public void checkNullPointers1FailTest(boolean _bool) {
        SerializeXmlObject.setCheckReferences(_bool);
        SerializeXmlObject.checkNullPointers(null);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=NullSerialException.class)
    public void checkNullPointers2FailTest(boolean _bool) {
        SerializeXmlObject.setCheckReferences(_bool);
        Object[] array_ = new Object[1];
        CompositeTwo comp_ = new CompositeTwo();
        array_[0] = comp_;
        SerializeXmlObject.checkNullPointers(array_);
    }

    @Parameters(method="booleanInputs")
    @Test(expected=NullSerialException.class)
    public void checkNullPointers3FailTest(boolean _bool) {
        SerializeXmlObject.setCheckReferences(_bool);
        Object[] array_ = new Object[1];
        SerializeXmlObject.checkNullPointers(array_);
    }

    @Test(expected=RefException.class)
    public void checkNullPointers4FailTest() {
        SerializeXmlObject.setCheckReferences(true);
        Object[] array_ = new Object[2];
        CompositeTwo comp_ = new CompositeTwo();
        comp_.setPrimitive(4);
        array_[0] = comp_;
        array_[1] = comp_;
        SerializeXmlObject.checkNullPointers(array_);
    }

    private static void assertXmlEqualRuntime(String _htmlExp, String _htmlRes) {
        try {
            assertXMLEqual(_htmlExp, _htmlRes);
        } catch (RuntimeException _0) {
            throw new CustRuntimeException(_0.getMessage());
        } catch (SAXException _0) {
            throw new CustRuntimeException(_0.getMessage());
        } catch (IOException _0) {
            throw new CustRuntimeException(_0.getMessage());
        }
    }
}
