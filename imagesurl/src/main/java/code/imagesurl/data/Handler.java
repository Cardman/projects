package code.imagesurl.data;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import javax.imageio.ImageIO;

import code.util.CustList;
import code.util.StringList;

public class Handler extends URLStreamHandler {

    private static final String OR = "|";
    private static final String JAVA_PROTOCOL_HANDLER_PKGS = "java.protocol.handler.pkgs";
    private static final String EMPTY_STRING = "";
    private static final char DOT = '.';

    @Override
    protected URLConnection openConnection(URL _u) {
        return new DataConnection(_u);
    }

    public static void install() {
        ImageIO.setUseCache(false);
        new DataConnection(null);
        String pkgName_ = Handler.class.getPackage().getName();
        String pkg_ = pkgName_.substring(CustList.FIRST_INDEX, pkgName_.lastIndexOf(DOT));

        String protocolHandlers_ = System.getProperty(JAVA_PROTOCOL_HANDLER_PKGS, EMPTY_STRING);
        if (!protocolHandlers_.contains(pkg_)) {
            if (!protocolHandlers_.isEmpty()) {
                protocolHandlers_ = StringList.concat(protocolHandlers_,OR);
            }
            protocolHandlers_ = StringList.concat(protocolHandlers_,pkg_);
            System.setProperty(JAVA_PROTOCOL_HANDLER_PKGS, protocolHandlers_);
        }
    }
}

