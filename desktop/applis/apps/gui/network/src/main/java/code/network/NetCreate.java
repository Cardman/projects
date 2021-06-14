package code.network;

import code.gui.initialize.AbstractAddressList;
import code.gui.initialize.AbstractNetworkInterfaceList;
import code.gui.initialize.AbstractServerSocket;
import code.gui.initialize.AbstractSocketFactory;
import code.maths.litteralcom.MathExpUtil;
import code.network.enums.IpType;
import code.stream.AbstractFileCoreStream;
import code.stream.StreamTextFile;
import code.stream.core.TechStreams;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class NetCreate {

    private static final String LINE_RETURN_N = "\n";
    private static final String LINE_RETURN_R = "\r";
    private static final String NET_ZERO = "net0";
    private static final String WLAN_ZERO = "wlan0";
    private static final int MAX_PORT = 256 * 256;

    private NetCreate(){
    }

    public static int tryToGetPort(String _fileName, int _defaultPort, AbstractFileCoreStream _fact, TechStreams _tech) {
        String content_ = StreamTextFile.contentsOfFile(_fileName,_fact,_tech);
        if (content_ == null) {
            return _defaultPort;
        }
        content_ = StringUtil.removeStrings(content_, LINE_RETURN_R, LINE_RETURN_N);
        if (!MathExpUtil.isPositiveNumber(content_)) {
            return _defaultPort;
        }
        int port_ = NumberUtil.parseInt(content_);
        if (port_ >= MAX_PORT) {
            return _defaultPort;
        }
        return port_;
    }

    public static String getHostAddress(AbstractSocketFactory _socketFactory, IpType _ipType, String _defaultIp) {
        AbstractNetworkInterfaceList list_ = _socketFactory.newList();
        int size_ = list_.size();
        StringList host_ = new StringList();
        for (int i = 0; i < size_; i++) {
            String name_ = list_.getName(i);
            if (!StringUtil.quickEq(name_.trim(), NET_ZERO) && !StringUtil.quickEq(name_.trim(), WLAN_ZERO) || list_.isVirtual(i)) {
                continue;
            }
            feed(_ipType, list_, host_, i);
        }
        if (host_.onlyOneElt()) {
            return host_.first();
        }
        return _defaultIp;
    }

    private static void feed(IpType _ipType, AbstractNetworkInterfaceList _list, StringList _host, int _i) {
        int s_ = _list.size(_i);
        for (int j = 0; j < s_; j++) {
            if (_list.isLoopbackAddress(_i,j)) {
                continue;
            }
            feed(_ipType, _list, _host, _i, j);
        }
    }

    private static void feed(IpType _ipType, AbstractNetworkInterfaceList _list, StringList _host, int _i, int _j) {
        if (_ipType == IpType.HOST_NAME) {
            _host.add(_list.getHost(_i, _j));
            return;
        }
        if (_ipType == IpType.IP_V4) {
            if (_list.isIpFour(_i, _j)) {
                _host.add(_list.getHost(_i, _j));
            }
            return;
        }
        if (_list.isIpSix(_i, _j)) {
            _host.add(_list.getHost(_i, _j));
        }
    }

    public static AbstractServerSocket createServerSocket(AbstractSocketFactory _fact, String _ip, int _port) {
        return bind(_ip, _port, _fact.newServerSocket());
    }

    private static AbstractServerSocket bind(String _ip, int _port, AbstractServerSocket _serverSocket) {
        if (_serverSocket.getClos() == null) {
            return null;
        }
        return _serverSocket.bind(_ip,_port);
    }

    static StringList getAllAddresses(AbstractSocketFactory _socketFactory, IpType _ipType, String _host) {
        StringList addresses_ = new StringList();
        if (_ipType == IpType.HOST_NAME) {
            AbstractAddressList addr_ = _socketFactory.newAddr(_host);
            int size_ = addr_.size();
            for (int i = 0; i < size_; i++) {
                addresses_.add(addr_.getHost(i));
            }
            return addresses_;
        }
        addresses_.add(_host);
        return addresses_;
    }
}
