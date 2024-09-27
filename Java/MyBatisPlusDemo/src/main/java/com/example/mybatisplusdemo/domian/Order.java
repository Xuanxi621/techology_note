package com.example.mybatisplusdemo.domian;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer price;

    private String remark;

    private Integer userId;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    private Integer version;

    private Integer delFlag;
}
