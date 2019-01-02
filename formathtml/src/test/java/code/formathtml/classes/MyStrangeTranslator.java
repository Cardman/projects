package code.formathtml.classes;
import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;

public class MyStrangeTranslator extends Translator {

    public MyStrangeTranslator() {
        setClassName("MyStrangeTranslator");
    }

    @Override
    public String getString(String _pattern, Object _conf,
            Bean _object, String _value) {
        ((Configuration)_conf).getContext().setException(NullStruct.NULL_VALUE);
        return "";
    }

}
