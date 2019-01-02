package code.expressionlanguage.stds;

import code.util.StringMap;

public final class PrimitiveTypes {
    private static final String EMPTY_STRING = "";
    private StringMap<PrimitiveType> primitiveTypes = new StringMap<PrimitiveType>();
    private String aliasPrimBoolean;
    private String aliasPrimByte;
    private String aliasPrimShort;
    private String aliasPrimChar;
    private String aliasPrimInteger;
    private String aliasPrimLong;
    private String aliasPrimFloat;
    private String aliasPrimDouble;
    public void buildPrimitiveTypes(LgNames _lgNames) {
        primitiveTypes.put(aliasPrimBoolean, new PrimitiveType(aliasPrimBoolean, _lgNames.getAliasBoolean(), EMPTY_STRING, EMPTY_STRING,false));
        primitiveTypes.put(aliasPrimChar, new PrimitiveType(aliasPrimChar, _lgNames.getAliasCharacter(), aliasPrimInteger, aliasPrimInteger,true));
        primitiveTypes.put(aliasPrimByte, new PrimitiveType(aliasPrimByte, _lgNames.getAliasByte(), aliasPrimShort, aliasPrimShort,true));
        primitiveTypes.put(aliasPrimShort, new PrimitiveType(aliasPrimShort, _lgNames.getAliasShort(), aliasPrimInteger, aliasPrimInteger,true));
        primitiveTypes.put(aliasPrimInteger, new PrimitiveType(aliasPrimInteger, _lgNames.getAliasInteger(), aliasPrimLong, aliasPrimLong,true));
        primitiveTypes.put(aliasPrimLong, new PrimitiveType(aliasPrimLong, _lgNames.getAliasLong(), aliasPrimFloat,EMPTY_STRING,true));
        primitiveTypes.put(aliasPrimFloat, new PrimitiveType(aliasPrimFloat, _lgNames.getAliasFloat(), aliasPrimDouble,aliasPrimDouble,true));
        primitiveTypes.put(aliasPrimDouble, new PrimitiveType(aliasPrimDouble, _lgNames.getAliasDouble(), EMPTY_STRING,EMPTY_STRING,true));
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
        return primitiveTypes;
    }

}
