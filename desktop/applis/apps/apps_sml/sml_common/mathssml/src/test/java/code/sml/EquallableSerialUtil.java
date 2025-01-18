package code.sml;

import code.maths.*;
import code.maths.montecarlo.*;
import code.sml.core.*;
import code.sml.maths.*;
import code.util.*;
import code.util.core.*;
import org.junit.Assert;

public abstract class EquallableSerialUtil {
    public static boolean saveBool(BoolVal _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getBoolean(DocumentWriterCoreUtil.setBoolean(_i,"_",f_));
    }

    public static int saveByte(int _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getByte(DocumentWriterCoreUtil.setByte((byte) _i,"_",f_));
    }

    public static int saveShort(long _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getShort(DocumentWriterCoreUtil.setShort((short) _i,"_",f_));
    }
    public static int saveInt(int _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getInteger(DocumentWriterCoreUtil.setInteger(_i,"_",f_));
    }

    public static long saveLong(long _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getLong(DocumentWriterCoreUtil.setLong(_i,"_",f_));
    }

    public static String saveString(String _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getString(DocumentWriterCoreUtil.setString(_i,"_",f_));
    }

    public static String saveStringSec(String _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getString(DocumentWriterCoreUtil.setString(_i,"",f_));
    }
    public static StringList saveStringList(StringList _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getStringList(DocumentWriterCoreUtil.setStringList(_i,"_",f_));
    }

    public static CustList<BoolVal> saveBoolValList(CustList<BoolVal> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getBoolValList(DocumentWriterCoreUtil.setBoolValList(_i,"_",f_));
    }

    public static Bytes saveBytes(Bytes _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getListByte(DocumentWriterCoreUtil.setListByte(_i,"_",f_));
    }

    public static Shorts saveShorts(Shorts _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getListShort(DocumentWriterCoreUtil.setListShort(_i,"_",f_));
    }
    public static Ints saveInts(Ints _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getListInteger(DocumentWriterCoreUtil.setListInteger(_i,"_",f_));
    }

    public static Longs saveLongs(Longs _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getListLong(DocumentWriterCoreUtil.setListLong(_i,"_",f_));
    }

    public static CustList<CustList<BoolVal>> saveListBoolValList(CustList<CustList<BoolVal>> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getListBooleanList(DocumentWriterCoreUtil.setListBooleanList(_i,"_",f_));
    }

    public static CustList<Bytes> saveListBytes(CustList<Bytes> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getListListByte(DocumentWriterCoreUtil.setListListByte(_i,"_",f_));
    }

    public static CustList<Longs> saveListLongs(CustList<Longs> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getListListLong(DocumentWriterCoreUtil.setListListLong(_i,"_",f_));
    }

    public static StringMap<BoolVal> saveStrMapBool(StringMap<BoolVal> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getStringMapBoolean(DocumentWriterCoreUtil.setStringMapBoolean(_i,"_",f_));
    }

    public static StringMap<Integer> saveStrMapInt(StringMap<Integer> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getStringMapInteger(DocumentWriterCoreUtil.setStringMapInteger(_i,"_",f_));
    }

    public static StringMap<String> saveStrMapString(StringMap<String> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getStringMapString(DocumentWriterCoreUtil.setStringMapString(_i,"_",f_));
    }

    public static StringMap<Ints> saveStrMapInts(StringMap<Ints> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getStringMapListInteger(DocumentWriterCoreUtil.setStringMapListInteger(_i,"_",f_));
    }

    public static StringMap<StringList> saveStrMapStringList(StringMap<StringList> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getStringMapStringList(DocumentWriterCoreUtil.setStringMapStringList(_i,"_",f_));
    }

    public static IntTreeMap<Byte> saveMapIntByte(IntTreeMap<Byte> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getMapIntegerByte(DocumentWriterCoreUtil.setMapIntegerByte(_i,"_",f_));
    }

    public static IntMap<String> saveMapIntString(IntMap<String> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getMapIntegerString(DocumentWriterCoreUtil.setMapIntegerString(_i,"_",f_));
    }

    public static IntMap<BoolVal> saveMapIntBoolVal(IntMap<BoolVal> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getMapIntegerBoolean(DocumentWriterCoreUtil.setMapIntegerBoolean(_i,"_",f_));
    }

    public static IntMap<Integer> saveMapByteByte(IntMap<Integer> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getMapByteByte(DocumentWriterCoreUtil.setMapByteByte(_i,"_",f_));
    }

    public static IntMap<Ints> saveMapByteListByte(IntMap<Ints> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderCoreUtil.getMapByteListByte(DocumentWriterCoreUtil.setMapByteListByte(_i,"_",f_));
    }

    public static LgInt saveLgInt(LgInt _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderMathUtil.getLgInt(DocumentWriterMathUtil.setLgInt(_i,"_",f_));
    }
    public static Rate saveRate(Rate _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderMathUtil.getRate(DocumentWriterMathUtil.setRate(_i,"_",f_));
    }

    public static CustList<LgInt> saveListLgInt(CustList<LgInt> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderMathUtil.getListLgInt(DocumentWriterMathUtil.setListLgInt(_i,"_",f_));
    }
    public static CustList<Rate> saveListRate(CustList<Rate> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderMathUtil.getListRate(DocumentWriterMathUtil.setListRate(_i,"_",f_));
    }

    public static StringMap<LgInt> saveStrMapLgInt(StringMap<LgInt> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderMathUtil.getStringMapLgInt(DocumentWriterMathUtil.setStringMapLgInt(_i,"_",f_));
    }

    public static StringMap<Rate> saveStrMapRate(StringMap<Rate> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderMathUtil.getStringMapRate(DocumentWriterMathUtil.setStringMapRate(_i,"_",f_));
    }

    public static MonteCarloNumber saveMonteCarloNumber(MonteCarloNumber _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderMathUtil.getMonteCarloNumber(DocumentWriterMathUtil.setMonteCarloNumber(_i,"_",f_));
    }

    public static MonteCarloBoolean saveMonteCarloBool(MonteCarloBoolean _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderMathUtil.getMonteCarloBoolean(DocumentWriterMathUtil.setMonteCarloBoolean(_i,"_",f_));
    }

    public static MonteCarloString saveMonteCarloString(MonteCarloString _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderMathUtil.getMonteCarloString(DocumentWriterMathUtil.setMonteCarloString(_i,"_",f_));
    }

    public static LongMap<Rate> saveMapLongRate(LongMap<Rate> _i) {
        FullDocument f_ = DocumentBuilder.newDocumentBuilder().newDocument();
        return DocumentReaderMathUtil.getMapLongRate(DocumentWriterMathUtil.setMapLongRate(_i,"_",f_));
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertEquals(_expected.toNumberString(), _result.toNumberString());
    }
}
