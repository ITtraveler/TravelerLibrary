package com.hgs.travelerlibrary.netcheck.NetCheckBroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hgs.travelerlibrary.netcheck.NetStatusUtils;


/**
 * Created by Administrator on 2016/10/6.
 * 网络状态广播 ，得到网络类型 wifi、mobile等
 */
public class CDNetBroadCastReceive extends BroadcastReceiver {
    private OnNetStatusListener netStatusListener;

    public CDNetBroadCastReceive(OnNetStatusListener netStatusLinstener) {
        this.netStatusListener = netStatusLinstener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NetStatusUtils netStatus = NetStatusUtils.newNetSatus(context);
        netStatusListener.netCurType(netStatus.getNetStyle());
    }
}
