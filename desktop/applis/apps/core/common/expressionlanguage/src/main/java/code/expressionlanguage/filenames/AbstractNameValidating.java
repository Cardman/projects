package code.expressionlanguage.filenames;

public interface AbstractNameValidating {
    boolean okPath(String _input,char... _seps);
    boolean ok(String _input);
}
