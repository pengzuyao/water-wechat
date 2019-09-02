package com.pzy.study;

import com.pzy.study.netty.WSServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-09-02
 */
@Slf4j
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (event.getApplicationContext().getParent() == null){
            log.info(">>>>>spring初始化完毕<<<<<");
            try {
                WSServer.getInstance().start();
            }catch (Exception e){
                log.error("netty启动失败！");
            }
        }
    }
}
