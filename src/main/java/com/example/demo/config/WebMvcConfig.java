package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

  @Bean
  public ViewResolver viewResolver() {
    final InternalResourceViewResolver bean = new InternalResourceViewResolver();
    bean.setViewClass(JstlView.class);
    bean.setPrefix("/WEB-INF/view/");
    bean.setSuffix(".jsp");
    return bean;
  } 

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.viewResolver(viewResolver());
  }


  // cấu hình đường dẫn của thư mục.
  //giúp ánh xạ tài nguyên tĩnh.
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //khai báo các tài nguyên tĩnh./css/** → Định nghĩa một URL Pattern, 
    //tức là tất cả các file có đường dẫn bắt đầu bằng /css/ sẽ được xử lý theo quy tắc này.
    //resources/css/ → Chỉ định thư mục thực tế chứa các tệp CSS.
  registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
  registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
  registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
  registry.addResourceHandler("/client/**").addResourceLocations("/resources/client/");
 }
}
