package com.zeus.common.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 手机短信验证工具类
 * @author xiaoli
 */
public class PhoneUtil {
	
	//发短信的账号的accessKeyId
	private static String accessKeyId = "LTAIW5ySBsxpxb4t";
	//发送的账号的accessSecret
	private static String accessSecret = "ssaQegXfCcCJ5XgofHYvKNu0bRaQ87";
	//短信签名，客户自己定，例如：手工皮圈，短信就以【手工皮圈】开头
	private static String signName = "手工皮圈";
	//短信模板编号，获取验证码模板
	public static String 	templateGetCode = "";
	//短信模板编号，注册
	public static String 	templateRegisterCode = "SMS_172825323";
	//短信模板编号，修改密码
	public static String 	templateUpdatePasswordCode = "SMS_172825322";
	
	/**
	 * 获取短信验证码
	 * @param receivePhone 接收短信的手机号
	 * @param template 所有短信模板编号，如上的静态成员，暂时只有templateGetCode，
	 * 						后期需要发送其他内容的短息，加模板编号即可
	 * @return 验证码
	 */
	public static String getPhoneCode(String receivePhone,String template) {
		//生成随机验证码
		int rand = 100000+Math.abs(new Random().nextInt(900000));
		String code = String.valueOf(rand);
		List<String> receivePhones = new LinkedList<>();
		receivePhones.add(receivePhone);
		//测试时先注释，申请了阿里平台短信业务再释放
//		sendMessage(receivePhones, template, code);
		code = "123456";//开通业务后删掉此行，先用12456测试
		return code;
	}
	
	/**
	 * 向用户发送短信
	 * @param receivePhones 接收短信的手机号
	 * @param template 短信模板类型，从静态成员选择模板编号
	 * @param code 验证码，获取验证码时才传递
	 */
	private static void sendMessage(List<String> receivePhones,String template,String code) {
		//调用阿里短信接口
		DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessSecret);
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("SignName", signName);
		request.putQueryParameter("TemplateCode", template);
		//根据验证码是否不为null，添加该参数，短信模板的内容格式：...验证码为${code}，该验证码...
		if(!StringUtils.isEmpty(code))
			request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
		try {
			for (String receive : receivePhones) {
				if(StringUtils.isEmpty(receive))
					continue;
				request.putQueryParameter("PhoneNumbers", receive);
				CommonResponse response = client.getCommonResponse(request);
				String data = response.getData();
				JSONObject jsonObject = JSONObject.parseObject(data);
				String message = jsonObject.getString("Message");
				if(message.equals("账户余额不足"))
					throw new RuntimeException("短信平台账户余额不足");
			}
		} catch (ClientException e) {
			e.printStackTrace();
			throw new RuntimeException("短信功能繁忙，请稍后再试");
		}
	}
	
	/**
	 * 发送短信重载方法，不传验证码
	 * @param receivePhones
	 * @param template
	 */
	public static void sendMessage(List<String> receivePhones,String template) {
		sendMessage(receivePhones, template, null);
	}

//	/**
//	 * 测试
//	 */
	public static void main(String[] args) {
//		List c=new ArrayList();
//		HashMap a=new HashMap(2);
//		a.put("id",'1');
//		a.put("number",'1');
//		String b=JSONUtil.toJsonStr(a);
//		System.out.println(b);
//		HashMap d=new HashMap(2);
//		d.put("id",'2');
//		d.put("number",'3');
//		c.add(a);
//		c.add(d);
//		String e=JSONUtil.toJsonStr(c);
//		System.out.println(e);
		String a="[{\"number\":1,\"id\":1},{\"number\":3,\"id\":2}]";

	}
	
}
