package code.bean.translator;

import code.bean.Bean;

public interface Translator {

    String getString(String _pattern, Object _conf, Bean _object, Object _value);
}
