package com.cxfdemo.ws.service.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.staxutils.W3CDOMStreamWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ErrorHandlerInterceptor extends AbstractSoapInterceptor {

	public ErrorHandlerInterceptor() {
		super(Phase.MARSHAL);
	}

	public void handleMessage(SoapMessage message) throws Fault {
		/*
		 * System.out.println("--------------error satrt-------------"); Fault
		 * fault = (Fault) message.getContent(Exception.class); String
		 * errorMessage = fault.getMessage(); Exchange exchange =
		 * message.getExchange(); HttpServletResponse response =
		 * (HttpServletResponse
		 * )message.get(AbstractHTTPDestination.HTTP_RESPONSE);
		 * System.out.println("response = " + response);
		 * System.out.println("-------------message-spilt-------------" + new
		 * Date()); for (Entry<String, Object> entry : message.entrySet()) {
		 * System.out.println(entry.getKey() + "-------" + entry.getValue()); }
		 * System.out.println("-------------exchange-spilt-------------" + new
		 * Date()); for (Entry<String, Object> entry : exchange.entrySet()) {
		 * System.out.println(entry.getKey() + "-------" + entry.getValue()); }
		 * System.out.println("fault.getMessage()" + fault.getMessage());
		 * System.out.println("fault.getCause()" + fault.getCause()); Bus bus =
		 * exchange.getBus(); BindingOperationInfo bindingOperationInfo =
		 * exchange.getBindingOperationInfo(); Destination destination =
		 * exchange.getDestination(); Message inMessage =
		 * exchange.getInMessage(); Message inFaultMessage =
		 * exchange.getInFaultMessage(); Message outFaultMessage =
		 * exchange.getOutFaultMessage(); Message outMessage =
		 * exchange.getOutMessage(); System.out.println("bus = " + bus);
		 * System.out.println("inFaultMessage = " + inFaultMessage);
		 * System.out.println("outFaultMessage = " + outFaultMessage);
		 * System.out.println("outMessage = " + outMessage);
		 * System.out.println("inMessage = " + inMessage);
		 * System.out.println("destination = " + destination);
		 * EndpointReferenceType address = destination.getAddress();
		 * System.out.println("address = " + address);
		 * System.out.println("bindingOperationInfo = " + bindingOperationInfo);
		 * System.out.println("-------------inMessage-spilt-------------" + new
		 * Date()); TreeMap<String, Object> treeMap = (TreeMap<String, Object>)
		 * inMessage.get(Message.PROTOCOL_HEADERS); for (Entry<String, Object>
		 * entry : inMessage.entrySet()) { System.out.println(entry.getKey() +
		 * "-------" + entry.getValue()); }
		 * System.out.println("-------------inMessage-spilt  end-------------" +
		 * new Date()); HttpServletRequest req = (HttpServletRequest)
		 * inMessage.get("HTTP.REQUEST"); System.out.println("req  ip  = " +
		 * getIpAddr(req)); W3CDOMStreamWriter w3CDOMStreamWriter =
		 * (W3CDOMStreamWriter
		 * )inMessage.get(W3CDOMStreamWriter.class.getName()); Document document
		 * = w3CDOMStreamWriter.getDocument();
		 * System.out.println("w3CDOMStreamWriter = " + w3CDOMStreamWriter);
		 * NodeList childNodes = document.getChildNodes(); NodeList username =
		 * document.getElementsByTagName("wsse:Username"); NodeList password =
		 * document.getElementsByTagName("wsse:Password");
		 * 
		 * if (username.getLength() != 1) { throw new Fault(new
		 * IllegalArgumentException("用户Id格式不对")); } if (password.getLength() !=
		 * 1) { throw new Fault(new IllegalArgumentException("用户密码格式不对")); }
		 * 
		 * //获取元素的文本内容 String userId = username.item(0).getTextContent(); String
		 * userPass = password.item(0).getTextContent();
		 * System.out.println("document = " + document);
		 * System.out.println("userId = " + userId);
		 * System.out.println("userPass = " + userPass);
		 * System.out.println("--------------error end-------------" + new
		 * Date());
		 * 
		 * lable : { String[] log =
		 * {errorMessage,exchange.get("javax.xml.ws.wsdl.description"
		 * ).toString(),userId,userPass}; }
		 * 
		 * try { Thread.sleep(1000L); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		// 错误原因
		Fault fault = (Fault) message.getContent(Exception.class);
		// 错误信息
		String errorMessage = null;
		Throwable cause = null;
		if (fault != null) {
			errorMessage = fault.getMessage();
			cause = fault.getCause();
		}

		Exchange exchange = message.getExchange();
		// wsdl描述
		String servicePath = null;
		// url与uri
		String url = null;
		String uri = null;
		// 客户端ip
		String clientIp = null;
		// 用户名
		String username = null;
		// 密码
		String psw = null;
		// 服务中的方法
		String methodName = null;
		// 参数名
		Object[] paramNames = null;
		// 参数值
		Object[] paramValues = null;

		if (exchange != null) {
			Object object = exchange.get("javax.xml.ws.wsdl.description");
			if (object != null) {
				servicePath = object.toString();
			}

			Message inMessage = exchange.getInMessage();
			if (inMessage != null) {
				HttpServletRequest req = (HttpServletRequest) inMessage
						.get("HTTP.REQUEST");
				clientIp = getIpAddr(req);
				url = (String) inMessage.get("org.apache.cxf.request.url");
				uri = (String) inMessage.get("org.apache.cxf.request.uri");
			}

			W3CDOMStreamWriter w3CDOMStreamWriter = (W3CDOMStreamWriter) inMessage
					.get(W3CDOMStreamWriter.class.getName());
			if (w3CDOMStreamWriter != null) {
				Document document = w3CDOMStreamWriter.getDocument();
				if (document != null) {
					NodeList usernames = document
							.getElementsByTagName("wsse:Username");
					NodeList psws = document
							.getElementsByTagName("wsse:Password");

					if (usernames != null && usernames.getLength() == 1) {
						username = usernames.item(0).getTextContent();
					}
					if (psws != null && psws.getLength() == 1) {
						psw = psws.item(0).getTextContent();
					}

					NodeList body = document.getElementsByTagName("soap:Body");
					Node method = body.item(0).getFirstChild();
					if (method != null) {
						methodName = method.getNodeName();
						String[] methods = methodName.split(":");
						if (methods != null && methods.length == 2) {
							methodName = methods[1];
						}

						NodeList args = method.getChildNodes();
						paramNames = getParam(args, true);
						paramValues = getParam(args, false);
					}
				}
			}
		}
		System.out.println(cause);
		System.out.println(errorMessage);
		System.out.println(servicePath);
		System.out.println(url);
		System.out.println(uri);
		System.out.println(clientIp);
		System.out.println(username);
		System.out.println(psw);
		System.out.println(methodName);
		System.out.println("---names--");
		System.out.print(getParam(paramNames) + "\t");
		System.out.println("\n---values--");
		System.out.print(getParam(paramValues) + "\t");
	}

	/**
	 * 获取参数字符串供打印
	 * @param values	参数数组
	 * @return	参数字符串
	 */
	private String getParam(Object[] values) {
		if (values == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		int length = values.length;
		for (int i = 0; i < length; i++) {
			Object obj = values[i];
			if (obj instanceof Object[] && i != length - 1) {
				sb.append("[" + getParam((Object[]) obj) + "],");
			} else if (obj instanceof Object[] && i == length - 1) {
				sb.append("[" + getParam((Object[]) obj) + "]");
			} else if (i != length - 1) {
				sb.append(obj + ",");
			} else {
				sb.append(obj);
			}
		}
		return sb.toString();
	}

	/**
	 * 从节点中获取参数信息
	 * @param args 节点
	 * @param isParamName	是否为参数名
	 * @return	参数数组
	 */
	private Object[] getParam(NodeList args, boolean isParamName) {
		if (args != null) {
			int length = args.getLength();
			Object[] params = new Object[length];
			for (int i = 0; i < length; i++) {
				Node arg = args.item(i);
				NodeList childNodes = arg.getChildNodes();
				if (childNodes != null && childNodes.getLength() > 1) {
					params[i] = getParam(childNodes, isParamName);
				} else if (isParamName) {
					params[i] = arg.getNodeName();
				} else {
					params[i] = arg.getTextContent();
				}
			}
			return params;
		}

		return null;
	}

	private String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return null;
		}

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}