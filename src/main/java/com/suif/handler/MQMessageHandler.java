package com.suif.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.suif.config.RabbitMQConfig;
import com.suif.dto.MQMessage;
import com.suif.entity.Ask;
import com.suif.mapper.AskMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@RabbitListener(queues = RabbitMQConfig.FIND_QUEUE)
public class MQMessageHandler {

    public static int count;

    @Resource
    private AskMapper askMapper;

    @RabbitHandler
    public void handler(MQMessage message){

        String[] askTemp = message.getTags().split(" ");

        LambdaQueryWrapper<Ask> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Ask::getCampus,message.getCampus());
        lqw.eq(Ask::getCategoryId,message.getCategoryId());

        List<Ask> posts = askMapper.selectList(lqw);

        if (posts==null){
            return;
        }

        for (Ask post : posts) {
            count =0;
            String[] findTemp = post.getTags().split(" ");
            for (String s : findTemp) {
                for (String s1 : askTemp) {
                    if (s.equals(s1)){
                        count++;
                    }
                }
            }

            if (count >=1){
                Long userId = post.getUserId();
                Integer postId = message.getId();
                askMapper.add(userId,postId);
            }
        }
    }

}
