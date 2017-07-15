package code.serialize.classes;
import code.util.annot.RwXml;

@RwXml
public class CompositePrimitivable {
//implements Primitivable
    private int integer;

    private boolean bool;

    private String string;

    private char character;

    private MyEnum element;

    private Primitive primitive;

    private PrimitiveTwo primitiveTwo;

    private transient String transientMember = "TRANSIENT";

    public int getInteger() {
        return integer;
    }

    public void setInteger(int _integer) {
        integer = _integer;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean _bool) {
        bool = _bool;
    }

    public String getString() {
        return string;
    }

    public void setString(String _string) {
        string = _string;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char _character) {
        character = _character;
    }

    public MyEnum getElement() {
        return element;
    }

    public void setElement(MyEnum _element) {
        element = _element;
    }

    public Primitive getPrimitive() {
        return primitive;
    }

    public void setPrimitive(Primitive _primitive) {
        primitive = _primitive;
    }

    public PrimitiveTwo getPrimitiveTwo() {
        return primitiveTwo;
    }

    public void setPrimitiveTwo(PrimitiveTwo _primitiveTwo) {
        primitiveTwo = _primitiveTwo;
    }

    public String getTransientMember() {
        return transientMember;
    }

    public void setTransientMember(String _transientMember) {
        transientMember = _transientMember;
    }
}
