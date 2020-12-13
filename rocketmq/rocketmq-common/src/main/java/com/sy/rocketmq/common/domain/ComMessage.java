package com.sy.rocketmq.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: shenyao
 * @Date: Created by 2020/12/7 21:42
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComMessage implements Serializable {


    private static final long serialVersionUID = 6285206763827150499L;


    private String content;

    private Long messageId;

    private Date sendTime;


}
