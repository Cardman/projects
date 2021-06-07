package code.expressionlanguage.stds;

import code.util.StringMap;

public final class PrimitiveTypes {
    public static final byte BOOL_WRAP = 0;
    public static final byte CHAR_WRAP = 1;
    public static final byte BYTE_WRAP = 2;
    public static final byte SHORT_WRAP = 3;
    public static final byte INT_WRAP = 4;
    public static final byte LONG_WRAP = 5;
    public static final byte FLOAT_WRAP = 6;
    public static final byte DOUBLE_WRAP = 7;
    public static final byte MAX_WRAP = 8;
    private static final String EMPTY_STRING = "";
    private final StringMap<PrimitiveType> primTypes = new StringMap<PrimitiveType>();
    private String aliasPrimBoolean;
    private String aliasPrimByte;
    private String aliasPrimShort;
    private String aliasPrimChar;
    private String aliasPrimInteger;
    private String aliasPrimLong;
    private String aliasPrimFloat;
    private String aliasPrimDouble;

    public void buildPrimitiveTypes(LgNames _lgNames) {
        primTypes.put(aliasPrimBoolean, new PrimitiveType(aliasPrimBoolean, _lgNames.getContent().getNbAlias().getAliasBoolean(), EMPTY_STRING,false,BOOL_WRAP));
        primTypes.put(aliasPrimChar, new PrimitiveType(aliasPrimChar, _lgNames.getContent().getNbAlias().getAliasCharacter(), aliasPrimInteger,true,CHAR_WRAP));
        primTypes.put(aliasPrimByte, new PrimitiveType(aliasPrimByte, _lgNames.getContent().getNbAlias().getAliasByte(), aliasPrimShort,true,BYTE_WRAP));
        primTypes.put(aliasPrimShort, new PrimitiveType(aliasPrimShort, _lgNames.getContent().getNbAlias().getAliasShort(), aliasPrimInteger,true,SHORT_WRAP));
        primTypes.put(aliasPrimInteger, new PrimitiveType(aliasPrimInteger, _lgNames.getContent().getNbAlias().getAliasInteger(), aliasPrimLong,true,INT_WRAP));
        primTypes.put(aliasPrimLong, new PrimitiveType(aliasPrimLong, _lgNames.getContent().getNbAlias().getAliasLong(), EMPTY_STRING,true,LONG_WRAP));
        primTypes.put(aliasPrimFloat, new PrimitiveType(aliasPrimFloat, _lgNames.getContent().getNbAlias().getAliasFloat(), aliasPrimDouble,true,FLOAT_WRAP));
        primTypes.put(aliasPrimDouble, new PrimitiveType(aliasPrimDouble, _lgNames.getContent().getNbAlias().getAliasDouble(), EMPTY_STRING,true,DOUBLE_WRAP));
    }
    public String getAliasPrimBoolean() {
        return aliasPrimBoolean;
    }
    public void setAliasPrimBoolean(String _aliasPrimBoolean) {
        aliasPrimBoolean = _aliasPrimBoolean;
    }
    public String getAliasPrimByte() {
        return aliasPrimByte;
    }
    public void setAliasPrimByte(String _aliasPrimByte) {
        aliasPrimByte = _aliasPrimByte;
    }
    public String getAliasPrimShort() {
        return aliasPrimShort;
    }
    public void setAliasPrimShort(String _aliasPrimShort) {
        aliasPrimShort = _aliasPrimShort;
    }
    public String getAliasPrimChar() {
        return aliasPrimChar;
    }
    public void setAliasPrimChar(String _aliasPrimChar) {
        aliasPrimChar = _aliasPrimChar;
    }
    public String getAliasPrimInteger() {
        return aliasPrimInteger;
    }
    public void setAliasPrimInteger(String _aliasPrimInteger) {
        aliasPrimInteger = _aliasPrimInteger;
    }
    public String getAliasPrimLong() {
        return aliasPrimLong;
    }
    public void setAliasPrimLong(String _aliasPrimLong) {
        aliasPrimLong = _aliasPrimLong;
    }
    public String getAliasPrimFloat() {
        return aliasPrimFloat;
    }
    public void setAliasPrimFloat(String _aliasPrimFloat) {
        aliasPrimFloat = _aliasPrimFloat;
    }
    public String getAliasPrimDouble() {
        return aliasPrimDouble;
    }
    public void setAliasPrimDouble(String _aliasPrimDouble) {
        aliasPrimDouble = _aliasPrimDouble;
    }
    public StringMap<PrimitiveType> getPrimitiveTypes() {
        return primTypes;
    }

}
