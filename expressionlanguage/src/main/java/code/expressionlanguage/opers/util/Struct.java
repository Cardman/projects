package code.expressionlanguage.opers.util;
import code.util.ObjectMap;

public interface Struct {

    public boolean isNull();

    public Boolean isJavaObject();

    public String getClassName();

    public String getRealClassName();

    public Object getInstance();

    public ObjectMap<ClassField,Struct> getFields();
}
