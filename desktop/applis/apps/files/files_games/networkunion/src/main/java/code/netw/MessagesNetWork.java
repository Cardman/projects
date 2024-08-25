package code.netw;

import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;

public final class MessagesNetWork {
    public static final String CANCEL = "0";
    public static final String CREATE_SERVER = "1";
    public static final String IP_SERVER = "2";
    public static final String IP_SERVER_TOOL_TIP = "3";
    public static final String JOIN_SERVER = "4";
    public static final String NUMBER_PLAYERS = "5";
    public static final String TITLE = "6";
    public static final String HOST_NAME = "7";
    public static final String IP_V4 = "8";
    public static final String IP_V6 = "9";
    public static final String CST_MULTI_STOP = "10";
    public static final String TOO_MANY = "11";
    public static final String UNKNOWN_HOST = "12";
    public static final String NOT_CONNECTED = "13";
    public static final String BUG = "14";
    public static final String USED_PORT = "15";
    public static final String APPS_NETWORK = "network";
    public static final String COMMON = "common";

    private MessagesNetWork() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CANCEL,"Cancel");
        e_.add(CREATE_SERVER,"Create a server");
        e_.add(IP_SERVER,"address of the server");
        e_.add(IP_SERVER_TOOL_TIP,"host name or internet protocol address (identification number)");
        e_.add(JOIN_SERVER,"Join a server");
        e_.add(NUMBER_PLAYERS,"number of players");
        e_.add(TITLE,"Create or join a game");
        e_.add(HOST_NAME,"Name of computer");
        e_.add(IP_V4,"Old type of ip addressing");
        e_.add(IP_V6,"New type of ip addressing");
        e_.add(CST_MULTI_STOP,"Stop playing by network");
        e_.add(TOO_MANY,"Maximal number of connections achieved.");
        e_.add(UNKNOWN_HOST,"Unknown host: {0}");
        e_.add(NOT_CONNECTED,"Not connected");
        e_.add(BUG,"bug");
        e_.add(USED_PORT,"The port number {0} is used. You can change the port number or you can try to join a server with a different IP.");
        return e_;
    }

    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CANCEL,"Annuler");
        f_.add(CREATE_SERVER,"Créer un serveur");
        f_.add(IP_SERVER,"adresse du serveur");
        f_.add(IP_SERVER_TOOL_TIP,"nom d'hôte ou adresse de protocole internet (numéro d'identification)");
        f_.add(JOIN_SERVER,"Rejoindre un serveur");
        f_.add(NUMBER_PLAYERS,"nombre de joueurs");
        f_.add(TITLE,"Créer ou rejoindre une partie");
        f_.add(HOST_NAME,"Nom d'ordinateur");
        f_.add(IP_V4,"Ancien type d'adressage");
        f_.add(IP_V6,"Nouveau type d'adressage");
        f_.add(CST_MULTI_STOP,"Arrêter de jouer en réseau");
        f_.add(TOO_MANY,"Nombre maximal de connexions atteint.");
        f_.add(UNKNOWN_HOST,"Hôte inconnu: {0}");
        f_.add(NOT_CONNECTED,"Non connecté");
        f_.add(BUG,"Problème");
        f_.add(USED_PORT,"Le numéro de port {0} est utilisé. Vous pouvez changer le numéro de port ou vous pouvez essayer de rejoindre un serveur avec une IP différente.");
        return f_;
    }

    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(APPS_NETWORK, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(APPS_NETWORK);
    }

    public static void enTr(TranslationsAppli _lgs) {
        append(_lgs, en());
    }

    public static void frTr(TranslationsAppli _lgs) {
        append(_lgs, fr());
    }

    public static void append(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(COMMON,_f);
    }

    public static TranslationsFile getMessages(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(COMMON);
    }
}
