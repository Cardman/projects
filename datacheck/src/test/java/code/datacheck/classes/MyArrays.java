package code.datacheck.classes;
import code.util.CustList;
import code.util.annot.RwXml;

@RwXml
public class MyArrays {

    private int[] arrayInt = new int[1];

    private long[] arrayLong = new long[1];

    private short[] arrayShort = new short[1];

    private byte[] arrayByte = new byte[1];

    private double[] arrayDouble = new double[1];

    private float[] arrayFloat = new float[1];

    private boolean[] arrayBoolean = new boolean[1];

    private char[] arrayChar = new char[1];

    private Object[] refs = new Object[1];

    private CustList<?>[] lists = new CustList<?>[1];

    private Carre<String> carre = new MySquare<String>();

    public int[] getArrayInt() {
        return arrayInt;
    }

    public void setArrayInt(int[] _arrayInt) {
        arrayInt = _arrayInt;
    }

    public long[] getArrayLong() {
        return arrayLong;
    }

    public void setArrayLong(long[] _arrayLong) {
        arrayLong = _arrayLong;
    }

    public short[] getArrayShort() {
        return arrayShort;
    }

    public void setArrayShort(short[] _arrayShort) {
        arrayShort = _arrayShort;
    }

    public byte[] getArrayByte() {
        return arrayByte;
    }

    public void setArrayByte(byte[] _arrayByte) {
        arrayByte = _arrayByte;
    }

    public double[] getArrayDouble() {
        return arrayDouble;
    }

    public void setArrayDouble(double[] _arrayDouble) {
        arrayDouble = _arrayDouble;
    }

    public float[] getArrayFloat() {
        return arrayFloat;
    }

    public void setArrayFloat(float[] _arrayFloat) {
        arrayFloat = _arrayFloat;
    }

    public boolean[] getArrayBoolean() {
        return arrayBoolean;
    }

    public void setArrayBoolean(boolean[] _arrayBoolean) {
        arrayBoolean = _arrayBoolean;
    }

    public char[] getArrayChar() {
        return arrayChar;
    }

    public void setArrayChar(char[] _arrayChar) {
        arrayChar = _arrayChar;
    }

    public Object[] getRefs() {
        return refs;
    }

    public void setRefs(Object[] _refs) {
        refs = _refs;
    }

    public CustList<?>[] getLists() {
        return lists;
    }

    public void setLists(CustList<?>[] _lists) {
        lists = _lists;
    }

    public Carre<String> getCarre() {
        return carre;
    }

    public void setCarre(Carre<String> _carre) {
        carre = _carre;
    }
}
