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
	private static String dbId = "5c1c5501b116cb";//����
	private static String user = "kd";//����
	private static String pwd = "741963ty";//����
	private static int lang = 2052;
	private static String sessionkey = "kdservice-sessionid";
	private static String aspnetsessionkey = "ASP.NET_SessionId";
	private static String sessionValue = "";
	private static String aspnetsessionValue = "";

	//�Ƿ����ӳɹ�
	/**
	 * ��½��k3Cloud
	 * @return
	 */
	public static boolean loginToK3Cloud() {
		// ����httpClient��ʵ��
		HttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonResult = null;

		/********** �û���¼Begin ************************/
		try {
			//ƴ��֮����ַ���
			//			String loginUrl = "http://172.42.21.12:8079/hfemt/base/loginByOpenId";
			String loginUrl = "https://www.qdwlhl.com/wlhlwork/message/push";
			URI uri = new URI(loginUrl);
			//����POST����,����HttpPost����
			HttpPost method = new HttpPost(uri);
			method.setHeader("openid", "oa2Xn5WuJ89HlMn0MK88Q8rKrbtg");
			// ��¼�������
			JSONObject jsonParam = new JSONObject();
			jsonParam.put("easUserNum", "����־");
			jsonParam.put("title", "�յ�һ����������");
			jsonParam.put("number", "TH-12323215");
			jsonParam.put("type", "�ƶ�����");
			jsonParam.put("people", "����123");
			jsonParam.put("task", "�ܺ���Ϊĳ��ԭ����Ҫ�����ݼ�1Сʱ,���쵼����");


			//���ﴫ������û���½����Ϣ
			StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8"); 
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			method.setEntity(entity);          
			// ����HttpClient�����execute(HttpUriRequest request)�������󣬸÷�������һ��HttpResponse
			HttpResponse result = httpclient.execute(method);
			// �����ͳɹ������õ���Ӧ
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				// ��ȡ���������ع�����json�ַ�������
				str = EntityUtils.toString(result.getEntity());
				// ��json�ַ���ת����json����
				jsonResult = JSONObject.parseObject(str);
				// �жϵ�¼�Ƿ�ɹ�
				if (jsonResult.getInteger("code") == 0) {
					System.out.println("��¼�ɹ���");
					// ��ȡ��ͷ�е�Cookie
					Header[] headers = result.getHeaders("Set-Cookie");
					for (int i = 1; i < headers.length; i++) {
						Header header = headers[i];
						String headerValue = header.getValue();
						// ��¼�ɹ����صĵ�¼session��Ϣ�����������������ٵ��ýӿڵ�ʱ�򴫸������
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
				// ��¼ʧ�ܣ�������
				else {
					System.out.println("��¼ʧ�ܣ�"+jsonResult.getIntValue("LoginResultType"));
					return false;
				}
			} else {
				System.out.println("��¼�쳣��"+result.getStatusLine().getStatusCode());
				return false;

			}
		} catch (Exception e) {
			System.out.println("post�����ύʧ��:" + e);
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
		// ����httpClient��ʵ��
		HttpClient httpclient = new DefaultHttpClient();
		try {
			//������Ҫ���ӵĽӿڵĵ�ַ
			URI uri = new URI(POST_K3CloudURL);
			//����HttpPost���󣬴�������
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setHeader("openid", "oa2Xn5WuJ89HlMn0MK88Q8rKrbtg");
			//��Ӳ���
			StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			//���ò���
			httpPost.setEntity(entity);
			//����HttpClient�����execute(HttpUriRequest request)�������󣬸÷�������һ��HttpResponse
			HttpResponse result = httpclient.execute(httpPost);
			// �����ͳɹ������õ���Ӧ
			if (result.getStatusLine().getStatusCode() == 200) {
				System.out.println("����ɹ�");
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
			json.put("easUserNum", "�ܺ�");
			json.put("title", "�յ�"+j+"����������");
			json.put("number", "TH-2020032600"+j);
			json.put("type", "�ƶ�����");
			json.put("people", "�ܺ�");
			json.put("task", "�ܺ���Ϊ����ԭ����Ҫ�����ݼ�"+j+"Сʱ,���쵼����");
			Invoke(null, json.toString());
		}
	}
}
