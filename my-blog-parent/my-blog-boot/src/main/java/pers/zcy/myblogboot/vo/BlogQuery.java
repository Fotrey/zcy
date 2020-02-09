package pers.zcy.myblogboot.vo;

import lombok.Data;

@Data
public class BlogQuery {

    private String title;
    private Long typeId;
    private boolean recommend;
}
