package com.liangjt.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.ws.Endpoint;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;

import com.liangjt.webService.Impl.HelloWorldWs;

/**
 * WsMain以RunAs java Application即可发布这个接口
 * 
 * @author Administrator
 *
 */
public class WsMain {

	public static void main(String[] args) throws IOException {
		HelloWorldWs hw = new HelloWorldWs();
		// 低啊用Endpoint的发布web service方法
		// 第一个参数是访问的地址，第二个参数是实现类
		EndpointImpl ep = (EndpointImpl) Endpoint.publish("http://127.0.0.1:9999/webservice", hw);

		// in.txt和out.txt 会在项目根目录下生成
		// 添加in拦截器,记录日志的拦截器
		ep.getInInterceptors().add(new LoggingInInterceptor(new PrintWriter(new FileWriter("in.txt"))));
		// 添加out拦截器
		ep.getOutInterceptors().add(new LoggingOutInterceptor(new PrintWriter(new FileWriter("out.txt"))));

		System.out.println("web Service暴露成功！");
	}

}
