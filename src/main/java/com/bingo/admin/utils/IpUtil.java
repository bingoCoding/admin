package com.bingo.admin.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


/**
 * IP工具类
 * 
 */
public class IpUtil {

	/** 获取本地IP地址 */
	private static String LOCAL_IP;
	
	/**
	 * 获取本地IP地址
	 */
	public static String getLocalIp() {
		if (StringUtil.isEmpty(LOCAL_IP)) {
			synchronized (IpUtil.class) {
				if (StringUtil.isNotEmpty(LOCAL_IP)) {
					return LOCAL_IP;
				}
				LOCAL_IP = localIp();
			}
		}
		return LOCAL_IP;
	}
	
	/**
	 * 获取本机ip
	 */
	private static String localIp() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address) {
						if (!ip.getHostAddress().equals("127.0.0.1")) {
							return ip.getHostAddress();
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return "";
	}
}
