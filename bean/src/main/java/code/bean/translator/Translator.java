package code.bean.translator;

import code.bean.Bean;
import code.util.StringMap;

public interface Translator {

    String getString(String _pattern, Object _conf, StringMap<String> _files, Bean _object, Object _value);
}
