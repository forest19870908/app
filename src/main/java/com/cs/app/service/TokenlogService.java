package com.cs.app.service;

import com.cs.app.mapper.TokenlogMapper;
import com.cs.app.model.Tokenlog;
import com.cs.app.util.TokenUtils;
import com.cs.core.enums.StatusEnum;
import com.cs.core.mapper.UserMapper;
import com.cs.core.model.User;
import com.cs.core.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Service
public class TokenlogService {
    @Resource
    private TokenlogMapper tokenlogMapper;
    @Resource
    private UserMapper userMapper;
    public Tokenlog selectByPrimaryKey(String uuidF){
        return tokenlogMapper.selectByPrimaryKey(uuidF);
    }

    /**
     * 创建tokenlog
     * @param tokentokenlog
     */
    public void createTokenlog(Tokenlog tokentokenlog){
        tokenlogMapper.insertSelective(tokentokenlog);
        //绑定用户
        User user=userMapper.selectByPrimaryKey(tokentokenlog.getUserCreatorF());
        user.setAppnameF(tokentokenlog.getAppuseridF());
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 重启项目的时候初始化TokenUtils.tokenMaps
     */
    @PostConstruct
    public void initTokenlogMap(){
        if(TokenUtils.tokenMaps.size()==0) {
            List<Tokenlog> tokenlogList = tokenlogMapper.selectList();
            for (Tokenlog tokenlog : tokenlogList) {
                TokenUtils.putToken(tokenlog.getUuidF(), tokenlog);
            }
        }
    }

    /**
     * 解除绑定
     * @param token
     */
    public void unbinduser(String token){
        Tokenlog tokenlog = tokenlogMapper.selectByPrimaryKey(token);
        tokenlog.setStatusF(StatusEnum.DELETE.getKey());
        tokenlogMapper.updateByPrimaryKeySelective(tokenlog);
        User user=userMapper.selectByPrimaryKey(tokenlog.getUserCreatorF());
        user.setAppnameF("");
        userMapper.updateByPrimaryKeySelective(user);
    }
}
