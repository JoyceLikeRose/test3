package cn.beecp.starter.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
   private UserInfoRepository repository;

    public List<UserInfo> getAllUserInfo(){
        return repository.findAll();
    }
}
