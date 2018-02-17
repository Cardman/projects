package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.PrimitiveTypeUtilAdv;
import code.serialize.ConstClasses;
import code.util.StringList;
import code.util.exceptions.RuntimeClassNotFoundException;

public final class ExtractObjectAdv {

    private static final String RETURN_LINE = "\n";

    static void classForName(String _className, ContextEl _conf) {
        try {
            if (PrimitiveTypeUtil.isPrimitive(_className, _conf)) {
                if (!PrimitiveTypeUtil.isExistentPrimitive(_className, _conf)) {
                    throw new RuntimeClassNotFoundException(StringList.concat(_className,RETURN_LINE,_conf.joinPages()));
                }
                return;
            }
            if (StringList.quickEq(_className, ConstClasses.LISTABLE_ALIAS)) {
                return;
            }
            if (StringList.quickEq(_className, ConstClasses.LISTABLE_ENTRIES_ALIAS)) {
                return;
            }
            String className_ = ConstClasses.getMapping(_className);
            if (className_ != null) {
                PrimitiveTypeUtilAdv.getSingleNativeClass(className_);
                return;
            }
            PrimitiveTypeUtilAdv.getSingleNativeClass(_className);
            return;
        } catch (Throwable _0) {
            _conf.getLastPage().addToOffset(0);
            throw new RuntimeClassNotFoundException(StringList.concat(_className,RETURN_LINE,_conf.joinPages()));
        }
    }
}
