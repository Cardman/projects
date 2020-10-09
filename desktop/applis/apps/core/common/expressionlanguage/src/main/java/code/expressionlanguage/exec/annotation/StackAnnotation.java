package code.expressionlanguage.exec.annotation;

import code.util.core.StringUtil;

final class StackAnnotation extends StackObject {

    private String name;

    void setName(String _name) {
        name = _name;
    }

    @Override
    String getPrefix() {
        return StringUtil.concat(name,"=");
    }

}
