package code.expressionlanguage.common;

import code.expressionlanguage.exec.util.ExecTypeVar;
import code.util.*;

public interface GeneType extends CommonGeneType {



    CustList<StringList> getBoundAll();
    CustList<ExecTypeVar> getParamTypesMapValues();
    StringList getParamTypesValues();


}
