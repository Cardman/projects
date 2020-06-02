package code.expressionlanguage.types;

public interface AbstractTokenValidation {

    boolean isStaticAccess();
    boolean isValidSingleToken(String _id);

}
