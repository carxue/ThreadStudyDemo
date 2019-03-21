package com.push.ios.apns;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;



public class Test
{
    public static void main(String[] args) throws Exception
    {
        String deviceToken = "b970c5c94ec528353b3b71af8bfddb8e46c973e70684af3d20e0d57e7b225dda";
        String alert = "薛奎";//push的内容
        int badge = 3;//图标小红圈的数值
        String sound = "default";//铃音

        List<String> tokens = new ArrayList<String>();
        tokens.add(deviceToken);
        String certificatePath = "D:\\iso_cer\\p12dis.p12";//AntennaPushDeveloper.p12  AntennaPushProduct.p12
        String certificatePassword = "1234";//此处注意导出的证书密码不能为空因为空密码会报错
        boolean sendCount = true;

        try
        {
            PushNotificationPayload payLoad = new PushNotificationPayload();
            payLoad.addAlert(alert); // 消息内容
            payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
           
            if (!StringUtils.isBlank(sound))
            {
                payLoad.addSound(sound);//铃音
            }
            PushNotificationManager pushManager = new PushNotificationManager();
            //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, true));
            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
            // 发送push消息
            if (sendCount)
            {
                Device device = new BasicDevice();
                device.setToken(tokens.get(0));
                PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
                notifications.add(notification);
                System.out.println(notification.isSuccessful());
            }
            else
            {
                List<Device> device = new ArrayList<Device>();
                for (String token : tokens)
                {
                    device.add(new BasicDevice(token));
                }
                notifications = pushManager.sendNotifications(payLoad, device);
                System.out.println(notifications.get(0).isSuccessful());
            }            
            pushManager.stopConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}