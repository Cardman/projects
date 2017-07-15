package code.expressionlanguage;

public final class OperationPriority {

    private final String operation;

    private final int priority;

    public OperationPriority(String _operation, int _priority) {
        operation = _operation;
        priority = _priority;
    }

    public String getOperation() {
        return operation;
    }

    public int getPriority() {
        return priority;
    }
}
