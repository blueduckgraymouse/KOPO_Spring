package kr.kopo.ctc.spring.boardItem.webConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 *	- 로컬에 저장된 파일을 웹 브라우저 화면에 보이기 위해서
 *	resource path (url)를 로컬 경로에 맵핑? [docbase]
 *  
 *  - 스프링 부트에서 로컬 폴더에 존재한느 파일들 url로 제공
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private String connectPath = "/NaverNewsBoardImageFile/**";
	private String resourcePath = "file:/C:/KOPO/git_tracking/spring/NaverNewsBoardImageFile/";
	
	 @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(connectPath).addResourceLocations(resourcePath);
	}

}
