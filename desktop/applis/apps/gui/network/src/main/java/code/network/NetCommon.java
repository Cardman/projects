package code.network;

import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.AbstractSocket;
import code.util.CustList;
import code.util.IntMap;
import code.util.IntTreeMap;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class NetCommon {

    public static final boolean QUICK = false;
    private final IntMap<AbstractSocket> sockets =new IntMap<AbstractSocket>();
    private final IntTreeMap< Byte> placesPlayers = new IntTreeMap< Byte>();
    private final IntMap<BoolVal> readyPlayers = new IntMap<BoolVal>();

    private final IntMap<String> nicknames =new IntMap<String>();

    private final IntMap<BasicServer> connectionsServer =new IntMap<BasicServer>();
    private final AbstractProgramInfos programInfos;

    public NetCommon(AbstractProgramInfos _p) {
        this.programInfos = _p;
    }

    public static String exportExiting(Exiting _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(exportBool(_index.isClosing()));
        out_.append(exportBool(_index.isForced()));
        out_.append(exportBool(_index.isServer()));
        out_.append(exportBool(_index.isTooManyPlayers()));
        return out_.toString();
    }

    public static Exiting importExiting(CustList<String> _info) {
        Exiting q_ = new Exiting();
        String i_ = _info.get(0);
        q_.setClosing(toBoolEquals(i_,0));
        q_.setForced(toBoolEquals(i_,1));
        q_.setServer(toBoolEquals(i_,2));
        q_.setTooManyPlayers(toBoolEquals(i_,3));
        return q_;
    }

    public static boolean toBoolEquals(String _l) {
        return StringUtil.quickEq(_l,"1");
    }

    public static boolean toBoolEquals(String _l, int _index) {
        return toBoolEquals(_l.charAt(_index));
    }

    public static boolean toBoolEquals(char _l) {
        return _l == '1';
    }

    public static String exportBool(BoolVal _bv) {
        if (_bv == BoolVal.FALSE) {
            return "0";
        }
        return "1";
    }

    public static String exportBool(boolean _bv) {
        if (!_bv) {
            return "0";
        }
        return "1";
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

    /**server
     @return true &hArr; the players are ready to begin a deal
      * @param _common */
    public boolean allReady() {
        boolean allReady_ = true;
        for (BoolVal r: getReadyPlayers().values()) {
            if (r != BoolVal.TRUE) {
                allReady_ = false;
                break;
            }
        }
        return allReady_;
    }
    public  void resend(String _str) {
        for(AbstractSocket so_:getSockets().values()) {
            NetGroupFrame.trySendString(_str, so_);
        }
    }
    public IntMap<AbstractSocket> getSockets() {
        return sockets;
    }

    public IntMap<BoolVal> getReadyPlayers() {
        return readyPlayers;
    }

    public IntMap<String> getNicknames() {
        return nicknames;
    }

    public IntTreeMap<Byte> getPlacesPlayers() {
        return placesPlayers;
    }

    public IntMap<BasicServer> getConnectionsServer() {
        return connectionsServer;
    }
}
