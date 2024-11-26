package code.network;

import code.gui.initialize.AbstractAddressList;
import code.gui.initialize.AbstractNetworkInterfaceList;
import code.gui.initialize.AbstractSocketFactory;
import code.maths.litteralcom.MathExpUtil;
import code.netw.MessagesNetWork;
import code.network.enums.IpType;
import code.stream.StreamTextFile;
import code.stream.core.TechStreams;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class NetCreate {

    static final int MAX_PORT = 256 * 256;
    private static final String LINE_RETURN_N = "\n";
    private static final String LINE_RETURN_R = "\r";

    private NetCreate(){
    }

    public static int tryToGetPort(String _fileName, int _defaultPort, TechStreams _tech) {
        String content_ = StreamTextFile.contentsOfFile(_fileName, _tech);
        return tryToGetPort(_defaultPort, content_);
    }

    public static int tryToGetPort(int _defaultPort, String _content) {
        String content_ = _content;
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
        StringList host_ = hostAddresses(_socketFactory, _ipType);
        return singleOrDef(_defaultIp, host_);
    }

    static String singleOrDef(String _defaultIp, StringList _host) {
        if (_host.onlyOneElt()) {
            return _host.first();
        }
        return _defaultIp;
    }

    public static StringList hostAddresses(AbstractSocketFactory _socketFactory, IpType _ipType) {
        AbstractNetworkInterfaceList list_ = _socketFactory.newList();
        return hostAddresses(_ipType, list_);
    }

    static StringList hostAddresses(IpType _ipType, AbstractNetworkInterfaceList _list) {
        int size_ = _list.size();
        StringList host_ = new StringList();
        for (int i = 0; i < size_; i++) {
            String name_ = _list.getName(i);
            if (!StringUtil.quickEq(name_.trim(), MessagesNetWork.NET_ZERO) && !StringUtil.quickEq(name_.trim(), MessagesNetWork.WLAN_ZERO) || _list.isVirtual(i)) {
                continue;
            }
            feed(_ipType, host_, _list.list(i));
        }
        return host_;
    }

    private static void feed(IpType _ipType, StringList _host, AbstractAddressList _list) {
        int s_ = _list.size();
        for (int j = 0; j < s_; j++) {
            if (_list.isLoopbackAddress(j)) {
                continue;
            }
            feed(_ipType, _list, _host, j);
        }
    }

    private static void feed(IpType _ipType, AbstractAddressList _list, StringList _host, int _j) {
        if (_ipType == IpType.HOST_NAME) {
            _host.add(_list.getHost(_j));
            return;
        }
        if (_ipType == IpType.IP_V4) {
            if (_list.isIpFour(_j)) {
                _host.add(_list.getHost(_j));
            }
            return;
        }
        if (_list.isIpSix(_j)) {
            _host.add(_list.getHost(_j));
        }
    }

    static StringList getAllAddresses(AbstractSocketFactory _socketFactory, IpType _ipType, String _host) {
        StringList addresses_ = new StringList();
        if (_ipType == IpType.HOST_NAME) {
            AbstractAddressList addr_ = _socketFactory.newAddr(_host);
            return addressesNames(addr_);
        }
        addresses_.add(_host);
        return addresses_;
    }

    static StringList addressesNames(AbstractAddressList _addr) {
        StringList addresses_ = new StringList();
        int size_ = _addr.size();
        for (int i = 0; i < size_; i++) {
            addresses_.add(_addr.getHost(i));
        }
        return addresses_;
    }
}
