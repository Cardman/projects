package code.bean.nat.analyze;

import code.formathtml.Configuration;
import code.formathtml.analyze.SetupableAnalyzingDoc;
import code.util.StringMap;

public final class NatAnalyzingDoc extends SetupableAnalyzingDoc {
    public void setup(Configuration _conf, StringMap<String> _properties, String _messagesFolder) {
        setupCommon(_conf, _properties, _messagesFolder);
    }

}
