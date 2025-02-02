package aiki.beans;

import code.util.*;

public interface IntBeanGeneInput {
    IntBeanChgBool newBool();
    IntBeanChgLong newLong();
    IntBeanChgRate newRate();
    IntBeanChgString newString(StringMap<String> _map);
    IntBeanChgSubmit newSubmit(String _text);
}
