package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.springboot.Exception.ServiceException;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IAdminService;
import com.example.springboot.service.IUserService;
import com.example.springboot.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AdminService implements IAdminService {


    @Autowired
    AdminMapper adminMapper;

    private static final String DEFAULT_PASS="123456";
    private static final String PASS_SALT="coco";


    @Override
    public List<Admin> list() {
        return adminMapper.list();
    }

    @Override
    public PageInfo<Admin> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNumber(),baseRequest.getPageSize());
      List<Admin> users=adminMapper.listByCondition(baseRequest);
     return new PageInfo<>(users);
    }

    @Override
    public void save(Admin obj) {

        //默认密码123456
        if(StrUtil.isBlank(obj.getPassword())){
            obj.setPassword(DEFAULT_PASS);
        }
       obj.setPassword(securePass(obj.getPassword())); //设置MD5加密
        adminMapper.save(obj);
    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin user) {
        user.setUpdatetime(new Date());
        adminMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    @Override
    public LoginDTO login(LoginRequest request) {
        request.setPassword(securePass(request.getPassword()));
        Admin admin = adminMapper.getByUsernameAndPassword(request);
        if(admin==null){
          throw new ServiceException("用户名或密码错误！");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(admin, loginDTO);

        //生产token
        String token = TokenUtils.genToken(String.valueOf(admin.getId()),admin.getPassword());
        loginDTO.setToken(token);
        return loginDTO;
    }

    private String securePass(String password){
        return SecureUtil.md5(password+PASS_SALT);
    }
}
