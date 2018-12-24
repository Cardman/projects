package code.expressionlanguage.opers.util.annotation;

import code.util.StringList;

final class StackAnnotation extends StackObject {

    private String name;

    void setName(String _name) {
        name = _name;
    }

    @Override
    String getPrefix() {
        return StringList.concat(name,"=");
    }

}
