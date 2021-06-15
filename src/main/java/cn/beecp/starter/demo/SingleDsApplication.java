package cn.beecp.starter.demo;


import cn.beecp.boot.EnableDataSourceMonitor;
import cn.beecp.starter.demo.entity.UserInfo;
import cn.beecp.starter.demo.entity.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableDataSourceMonitor
@SpringBootApplication
@RestController
public class SingleDsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SingleDsApplication.class, args);
	}

	@Autowired
	private UserInfoService service;

	@RequestMapping("/getAllUser")
	public List<UserInfo> findLandAttach() throws Exception {
		return service.getAllUserInfo();
	}
}
