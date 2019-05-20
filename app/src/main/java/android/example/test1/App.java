package android.example.test1;

import android.app.Application;

import java.util.ArrayList;

public class App extends Application {
    private String ip = "35.200.212.119";
    private ArrayList<String> ipLOG = new ArrayList<String>();

    public String getIP() {
        return ip;
    }
    public void setIp(String IP) {
        if(ipLOG.size() == 0) {
            ipLOG.add(ip);
        }
        this.ip = IP;
        this.ipLOG.add(this.ip);
    }
    public ArrayList<String> getIpLog() {
        if(ipLOG.size() == 0) {
            ipLOG.add(ip);
        }
        return ipLOG;
    }
}
