package code.formathtml.classes;
import code.bean.Bean;
import code.bean.translator.Translator;

public class MyStrangeTranslator extends Translator {

    public MyStrangeTranslator() {
        setClassName("MyStrangeTranslator");
    }

    @Override
    public String getString(String _pattern, Object _conf,
            Bean _object, Object _value) {
        return "".substring(1);
    }

}
