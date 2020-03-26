package com.bean;

import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class httpRequst {

	//	private static String POST_K3CloudURL = "http://172.42.21.12:8079/hfemt/duck/busniess/Batch/getList";
	private static String POST_K3CloudURL = "https://www.qdwlhl.com/wlhlwork/message/push";
	private static String dbId = "5c1c5501b116cb";//测试
	private static String user = "kd";//测试
	private static String pwd = "741963ty";//测试
	private static int lang = 2052;
	private static String sessionkey = "kdservice-sessionid";
	private static String aspnetsessionkey = "ASP.NET_SessionId";
	private static String sessionValue = "";
	private static String aspnetsessionValue = "";

	//是否连接成功
	/**
	 * 登陆到k3Cloud
	 * @return
	 */
	public static boolean loginToK3Cloud() {
		// 定义httpClient的实例
		HttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonResult = null;

		/********** 用户登录Begin ************************/
		try {
			//拼接之后的字符串
			//			String loginUrl = "http://172.42.21.12:8079/hfemt/base/loginByOpenId";
			String loginUrl = "https://www.qdwlhl.com/wlhlwork/message/push";
			URI uri = new URI(loginUrl);
			//发送POST请求,创建HttpPost对象
			HttpPost method = new HttpPost(uri);
			method.setHeader("openid", "oa2Xn5WuJ89HlMn0MK88Q8rKrbtg");
			// 登录请求参数
			JSONObject jsonParam = new JSONObject();
			jsonParam.put("easUserNum", "王正志");
			jsonParam.put("title", "收到一条待办提醒");
			jsonParam.put("number", "TH-12323215");
			jsonParam.put("type", "移动审批");
			jsonParam.put("people", "张三123");
			jsonParam.put("task", "周豪因为某种原因需要申请休假1小时,请领导审批");


			//这里传入的是用户登陆的信息
			StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8"); 
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			method.setEntity(entity);          
			// 调用HttpClient对象的execute(HttpUriRequest request)发送请求，该方法返回一个HttpResponse
			HttpResponse result = httpclient.execute(method);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity());
				// 把json字符串转换成json对象
				jsonResult = JSONObject.parseObject(str);
				// 判断登录是否成功
				if (jsonResult.getInteger("code") == 0) {
					System.out.println("登录成功！");
					// 获取标头中的Cookie
					Header[] headers = result.getHeaders("Set-Cookie");
					for (int i = 1; i < headers.length; i++) {
						Header header = headers[i];
						String headerValue = header.getValue();
						// 登录成功返回的登录session信息，保存下来，下面再调用接口的时候传给服务端
						if (headerValue.trim().startsWith(sessionkey)) {
							int endIndex = headerValue.indexOf(';');
							sessionValue = headerValue.substring(20, endIndex);
						} else if (headerValue.trim().startsWith(
								aspnetsessionkey)) {
							int endIndex = headerValue.indexOf(';');
							aspnetsessionValue = headerValue.substring(18,
									endIndex);
						}

					}

					return true;
				}
				// 登录失败，不继续
				else {
					System.out.println("登录失败！"+jsonResult.getIntValue("LoginResultType"));
					return false;
				}
			} else {
				System.out.println("登录异常！"+result.getStatusLine().getStatusCode());
				return false;

			}
		} catch (Exception e) {
			System.out.println("post请求提交失败:" + e);
		}
		return false;
	}


	/**
	 * 
	 * @param method
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String Invoke(String method, String jsonParam){
		// 定义httpClient的实例
		HttpClient httpclient = new DefaultHttpClient();
		try {
			//设置需要连接的接口的地址
			URI uri = new URI(POST_K3CloudURL);
			//建立HttpPost对象，传递数据
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setHeader("openid", "oa2Xn5WuJ89HlMn0MK88Q8rKrbtg");
			//添加参数
			StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			//设置参数
			httpPost.setEntity(entity);
			//调用HttpClient对象的execute(HttpUriRequest request)发送请求，该方法返回一个HttpResponse
			HttpResponse result = httpclient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (result.getStatusLine().getStatusCode() == 200) {
				System.out.println("请求成功");
				String str = EntityUtils.toString(result.getEntity());
				System.out.println(str);
				return str;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}

	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		int j = 0;
		for(int i =0,size = 100;i<size;i++) {
			j = i+1;
			json.put("easUserNum", "周航");
			json.put("title", "收到"+j+"条待办提醒");
			json.put("number", "TH-2020032600"+j);
			json.put("type", "移动审批");
			json.put("people", "周航");
			json.put("task", "周豪因为个人原因需要申请休假"+j+"小时,请领导审批");
			Invoke(null, json.toString());
		}
	}
}
