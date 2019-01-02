package code.formathtml.classes;
import code.bean.Bean;
import code.bean.translator.Translator;
import code.util.StringList;

public class MyTranslator extends Translator {

    public MyTranslator() {
        setClassName("MyTranslator");
    }

    @Override
    public String getString(String _pattern, Object _conf,
            Bean _object, String _value) {
        return StringList.concat(_value," &lt;= ",_value);
    }

}
