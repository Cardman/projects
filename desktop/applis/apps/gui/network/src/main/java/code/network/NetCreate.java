package code.network;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;

import code.maths.litteralcom.MathExpUtil;
import code.network.enums.IpType;
import code.stream.StreamTextFile;
import code.util.CustList;
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

    public static int tryToGetPort(String _fileName, int _defaultPort) {
        String content_ = StreamTextFile.contentsOfFile(_fileName);
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

    public static String getHostAddress(IpType _ipType, String _defaultIp) {
        if (_ipType == IpType.HOST_NAME) {
            for (NetworkInterface n: getNetworkInterfaces()) {
                for (InetAddress i: Collections.list(n.getInetAddresses())) {
                    if (i.isLoopbackAddress()) {
                        continue;
                    }
                    return i.getHostAddress();
                }
            }
            return _defaultIp;
        }
        if (_ipType == IpType.IP_V4) {
            for (NetworkInterface n: getNetworkInterfaces()) {
                for (InetAddress i: Collections.list(n.getInetAddresses())) {
                    if (i.isLoopbackAddress()) {
                        continue;
                    }
                    if (!(i instanceof Inet4Address)) {
                        continue;
                    }
                    return i.getHostAddress();
                }
            }
        }
        for (NetworkInterface n: getNetworkInterfaces()) {
            for (InetAddress i: Collections.list(n.getInetAddresses())) {
                if (i.isLoopbackAddress()) {
                    continue;
                }
                if (!(i instanceof Inet6Address)) {
                    continue;
                }
                return i.getHostAddress();
            }
        }
        return _defaultIp;
    }

    private static CustList<NetworkInterface> getNetworkInterfaces() {
        CustList<NetworkInterface> l_ = new CustList<NetworkInterface>();
        for (NetworkInterface n: getNetworkInterfacesList()) {
            if (!StringUtil.quickEq(n.getName().trim(),NET_ZERO)) {
                if (!StringUtil.quickEq(n.getName().trim(),WLAN_ZERO)) {
                    continue;
                }
            }
            if (n.isVirtual()) {
                continue;
            }
            l_.add(n);
        }
        return l_;
    }

    private static CustList<NetworkInterface> getNetworkInterfacesList() {
        CustList<NetworkInterface> l_ = new CustList<NetworkInterface>();
        try {
            for (NetworkInterface n: Collections.list(NetworkInterface.getNetworkInterfaces())) {
                l_.add(n);
            }
            return l_;
        } catch (SocketException e) {
            return l_;
        }
    }

    public static ServerSocket createServerSocket(String _ip, int _port) {
        try {
            return bind(_ip, _port, new ServerSocket());
        } catch (IOException e) {
            return null;
        }
    }

    private static ServerSocket bind(String _ip, int _port, ServerSocket _serverSocket) {
        try {
            _serverSocket.bind(new InetSocketAddress(_ip,_port));
            _serverSocket.close();
            return _serverSocket;
        } catch (IOException e) {
            return null;
        }
    }

    static StringList getAllAddresses(IpType _ipType, String _host) {
        StringList addresses_ = new StringList();
        if (_ipType == IpType.HOST_NAME) {
            try {
                //InetAddress.getAllByName throws UnknownHostException
                //if no IP address for the host could be found,
                //or if a scope_id was specified for a global IPv6 address.
                for (InetAddress i: InetAddress.getAllByName(_host)) {
                    addresses_.add(i.getHostAddress());
                }
            } catch (UnknownHostException e) {
                return null;
            }
            return addresses_;
        }
        addresses_.add(_host);
        return addresses_;
    }
}
