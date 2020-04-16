package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Menu extends BaseEntity {
    //mName mAddress mType mCode mDesc pId
    private String mName;
    private String mAddress;
    private Integer mType;
    private String mCode;
    private String mDesc;
    private Integer pId;
}
