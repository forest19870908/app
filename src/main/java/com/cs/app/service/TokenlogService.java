package com.cs.app.service;

import com.cs.app.mapper.TokenlogMapper;
import com.cs.app.model.Tokenlog;
import com.cs.app.util.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Service
public class TokenlogService {
    @Resource
    private TokenlogMapper tokenlogMapper;

    /**
     * 创建tokenlog
     * @param tokentokenlog
     */
    public void createTokenlog(Tokenlog tokentokenlog){
        tokenlogMapper.insertSelective(tokentokenlog);
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
}
