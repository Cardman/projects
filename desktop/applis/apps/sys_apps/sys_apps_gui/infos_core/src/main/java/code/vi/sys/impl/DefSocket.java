package code.vi.sys.impl;

import code.gui.initialize.*;
//import code.stream.*;
//import code.util.core.*;
import code.vi.prot.impl.*;

import java.io.*;
import java.net.*;

public final class DefSocket implements AbstractSocket {
    private Socket socket;
    private boolean ko;

    public DefSocket() {
        ko = true;
    }
    public DefSocket(Socket _socket) {
        socket = _socket;
    }


    @Override
    public String read() {
        try {
            BufferedReader br_ = new BufferedReader(new InputStreamReader(socket.getInputStream(),StreamCoreUtil.utf()));
            return br_.readLine();
        } catch (Exception e) {
            return null;
        }
    }

//    @Override
//    public BytesInfo readBytes() {
//        try {
//            return StreamBinaryFile.readBytes(new DefBufferedReader(socket.getInputStream()));
//        } catch (Exception e) {
//            return null;
//        }
//    }

//    private static String decode(BytesInfo _info) {
//        try {
//            return StringUtil.decode(_info.getBytes());
//        } catch (Exception e) {
//            return null;
//        }
//    }

    @Override
    public String println(String _string) {
        try {
            BufferedWriter out_ = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),StreamCoreUtil.utf()));
            out_.write(_string+"\n");
            out_.flush();
            return _string+"\n";
        } catch (Exception e) {
            return "";
        }
    }

//    @Override
//    public byte[] println(byte[] _string) {
//        try {
//            OutputStream out_ = socket.getOutputStream();
//            out_.write(_string);
//            out_.flush();
//            return _string;
//        } catch (Exception e) {
//            return new byte[0];
//        }
//    }

    @Override
    public void close() {
        StreamCoreUtil.close(socket);
    }

    @Override
    public boolean isKo() {
        return ko;
    }

    @Override
    public String inetAddress() {
        try {
            return socket.getInetAddress().toString();
            //ip serveur
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String localAddress() {
        try {
            return socket.getLocalAddress().toString();
            //ip client distante
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String localSocketAddress() {
        try {
            return socket.getLocalSocketAddress().toString();
            //ip client distante
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String remoteSocketAddress() {
        try {
            return socket.getRemoteSocketAddress().toString();
            //ip serveur avec port temporaire
        } catch (Exception e) {
            return "";
        }
    }
}
