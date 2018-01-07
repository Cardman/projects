package code.formathtml.classes;
import code.bean.Bean;
import code.bean.translator.Translator;
import code.util.StringList;

public class MyTranslator implements Translator {

    @Override
    public String getString(String _pattern, Object _conf,
            Bean _object, Object _value) {
        return StringList.concat(((Integer)_value).toString()," &lt;= ",((Integer)_value).toString());
    }

}
