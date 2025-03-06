package aiki.beans;

import code.util.*;

public interface IntBeanGeneInput {
    IntBeanChgBool newBool();
    IntBeanChgLong newLong();
    IntBeanChgRate newRate();
    IntBeanChgString newString(AbsMap<String,String> _map);
    IntBeanChgInt newInt(AbsMap<Integer,String> _map);
    IntBeanChgString newText();
    IntBeanChgSubmit newSubmit(String _text);
}
