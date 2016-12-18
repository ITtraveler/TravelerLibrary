package com.hgs.travelerlibrary.netcheck;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * Created by hgs on 2016/10/6.
 * 网络检测工具，在广播中调用此，可以进行实时检测网络状态
 */
public class NetStatusUtils {
    private static NetStatusUtils netUtils;
    private final ConnectivityManager cm;
    public static final String NET_TYPE_MOBILE = "MOBILE";
    public static final String NET_TYPE_WIFI = "WIFI";
    public static final String NET_DISCONNECTED = "DISCONNECTED";

    public static NetStatusUtils newNetSatus(Context context) {
        if (netUtils == null) {
            netUtils = new NetStatusUtils(context);
        }
        return netUtils;
    }

    private Context mContext;

    public NetStatusUtils(Context context) {
        this.mContext = context;
        cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

    }

    /**
     * 检测网络连接状态
     *
     * @return
     */
    public boolean isNetworkAvailableAndConnected() {
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isConnected = false;
        if (isNetworkAvailable) {
            isConnected = cm.getActiveNetworkInfo().isConnected();
        }
        return isConnected;
    }

    /**
     * 约定WIFI状态加载广告，其他情况不加载。
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public String getNetStyle() {
        if (isNetworkAvailableAndConnected()) {
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            System.out.println("netType:" + netInfo.getTypeName() + "   state:" + netInfo.getState().name());
            return netInfo.getTypeName();
        }
        return NET_DISCONNECTED;
    }

}
