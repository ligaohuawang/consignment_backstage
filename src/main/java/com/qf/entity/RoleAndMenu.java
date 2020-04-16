package com.qf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("role_menu")
public class RoleAndMenu extends BaseEntity{

    private Integer rid;
    private Integer mid;

/*private String mNane;
private String mAddress;
private Integer mType;
private String  mCode;
private String  mDesc;
private Integer  pId;*/
}
