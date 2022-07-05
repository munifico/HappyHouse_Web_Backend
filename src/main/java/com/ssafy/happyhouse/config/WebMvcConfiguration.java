package com.ssafy.happyhouse.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.interceptor.ConfirmInterceptor;

@Configuration
//@MapperScan(basePackages = {"com.**.mapper"})
public class WebMvcConfiguration implements WebMvcConfigurer {
	
//	private final List<String> patterns = Arrays.asList("/board/*", "/admin", "/user/list");
//
//	@Autowired
//	private ConfirmInterceptor confirmInterceptor;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
//	}
	
//	@Bean
//  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
//      SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//      sqlSessionFactoryBean.setDataSource(dataSource);
//      Resource[] arrResource = new PathMatchingResourcePatternResolver()
//              .getResources("classpath:mapper/**/*Mapper.xml");
//      sqlSessionFactoryBean.setMapperLocations(arrResource);
//      sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//      return sqlSessionFactoryBean.getObject();
//  }
	
//	@Bean
//	public ViewResolver internalResourceViewResolver() {
//	    InternalResourceViewResolver bean = new InternalResourceViewResolver();
//	    bean.setPrefix("/WEB-INF/views/");
//	    bean.setSuffix(".jsp");
//	    return bean;
//	}
//	
//	@Bean
//	public BeanNameViewResolver beanNameViewResolver() {
//		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
//		beanNameViewResolver.setOrder(0);
//		return beanNameViewResolver;
//	}
	
//  Interceptor를 이용해서 처리하므로 전역의 Cross Origin 처리를 해준다.
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		default 설정.
//		Allow all origins.
//		Allow "simple" methods GET, HEAD and POST.
//		Allow all headers.
//		Set max age to 1800 seconds (30 minutes).
		registry.addMapping("/**")
			.allowedOrigins("*")
//			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//			.allowedHeaders("*")
			.maxAge(6000);
	}
	
//	Swagger UI 실행시 404처리
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/index.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui/index.html");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
