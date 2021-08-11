package code.bean.nat.fwd.opers;

import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;

public final class NatAnaCallFctContent {

    private final String methodName;

    private ClassMethodId classMethodId;

    public NatAnaCallFctContent(String _methodName) {
        this.methodName = _methodName;
    }

    public void update(ClassMethodIdReturn _res) {
        String foundClass_ = _res.getRealClass();
        MethodId realId_ = _res.getRealId();
        setClassMethodId(new ClassMethodId(foundClass_, realId_));
    }

    public String getMethodName() {
        return methodName;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public void setClassMethodId(ClassMethodId _classMethodId) {
        this.classMethodId = _classMethodId;
    }

}
