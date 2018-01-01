package code.formathtml.exceptions;

import code.util.StringList;

public class MessageKeyNotFoundException extends RuntimeException {

    private static final String SEP = " not found in ";

    private static final String SEP_TWO = "\n";

    public MessageKeyNotFoundException(String _key, String _file, String _addon){
        super(StringList.concat(_key,SEP,_file,SEP_TWO,_addon));
    }
}
