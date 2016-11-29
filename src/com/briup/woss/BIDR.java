package com.briup.woss;

import java.io.Serializable;

public class BIDR implements Serializable {
	//属性
	String AAA_login_name;//登陆名
	String login_ip;//登陆IP
	String login_date;//登陆时间
	String logout_date;//登出时间
	String NAS_ip;//NAS IP =null
	long time_duration;//在线时间
	public BIDR(){}
	public BIDR(String AAA_login_name,String login_ip, String login_date,
			String logout_date,long time_duration){
		this.AAA_login_name = AAA_login_name;
		this.login_ip = login_ip;
		this.login_date = login_date;
		this.logout_date  =logout_date;
		this.NAS_ip = null;
		this.time_duration = time_duration;
	}
}
