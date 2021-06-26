package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;

abstract class ExecPrefPartType extends ExecParentPartType {
    private final String prefix;
    ExecPrefPartType(ExecParentPartType _parent, int _index, String _prefix, String _previousOperator) {
        super(_parent, _index, _previousOperator);
        prefix = _prefix;
    }

    @Override
    String getBegin() {
        return prefix;
    }

    @Override
    String getPrettyBegin() {
        return prefix;
    }

    @Override
    String getPrettyEnd() {
        return EMPTY_STRING;
    }
    @Override
    String getEnd() {
        return EMPTY_STRING;
    }

    boolean analyzeTree(ContextEl _an) {
        if (!(getParent() instanceof ExecTemplatePartType)) {
            return false;
        }
        return analyzeTree((ExecTemplatePartType) getParent(),_an);
    }
    abstract boolean analyzeTree(ExecTemplatePartType _parent, ContextEl _an);
}
