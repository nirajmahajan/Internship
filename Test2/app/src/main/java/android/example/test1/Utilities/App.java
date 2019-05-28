package android.example.test1.Utilities;

import android.app.Application;
import android.widget.Toast;

import java.util.ArrayList;

public class App extends Application {
    private String ip = "35.244.49.161";
    private ArrayList<String> ipLOG = new ArrayList<String>();

    public String getIP() {
        return ip;
    }
    public void setIp(String IP) {
        if(ipLOG.size() == 0) {
            ipLOG.add(ip);
        }

        // check if the ip is same as the current ip
        if (IP.equals(ipLOG.get(ipLOG.size() - 1))) {
            Toast.makeText(getApplicationContext(), "The New IP is same as the Current Value", Toast.LENGTH_LONG).show();
        }
        else {
            this.ip = IP;
            this.ipLOG.add(this.ip);
            Toast.makeText(getApplicationContext(), "IP changed succesfully to "+ IP, Toast.LENGTH_SHORT).show();
        }
    }
    public ArrayList<String> getIpLog() {
        if(ipLOG.size() == 0) {
            ipLOG.add(ip);
        }
        return ipLOG;
    }
}
