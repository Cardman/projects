package code.expressionlanguage;

public final class FileResolver {

    private static final char FOR_BLOCKS = ':';
    private static final char END_LINE = ':';
    private static final char END_IMPORTS = ';';
    private static final char LINE_RETURN = '\n';
    private static final String COMMENT_LINE = "//";
    private static final String COMMENT_MULTI_LINE_BEGIN = "/*";
    private static final String COMMENT_MULTI_LINE_END = "*/";
    private static final String NEW = "$new";
    private static final char SEP_ENUM_CONST = ',';
    private static final char BEGIN_TEMPLATE = '<';
    private static final char END_TEMPLATE = '>';
    private static final char BEGIN_BLOCK = '{';
    private static final char END_BLOCK = '}';
    private static final char BEGIN_CALLING = '(';
    private static final char END_CALLING = ')';
    private static final char PART_SEPARATOR = '=';
    private static final String INTERFACES = "interfaces";

    private FileResolver(){
    }
    public static void parseFile(String _file, ContextEl _context) {
        
    }
}
