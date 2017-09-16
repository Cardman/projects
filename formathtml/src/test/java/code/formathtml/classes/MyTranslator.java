package code.formathtml.classes;
import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.opers.util.Struct;
import code.util.StringMap;

public class MyTranslator implements Translator {

    @Override
    public String getString(String _pattern, Object _conf,
            StringMap<String> _files, Bean _object, Object _value) {
        return ((Struct)_value).getInstance()+" &lt;= "+((Struct)_value).getInstance();
    }

}
