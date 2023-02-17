package com.milk.job.model.dto;

import com.milk.job.model.pojo.Position;
import lombok.Data;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 23:24
 */
@Data
public class PositionDto extends Position {

    private String categoryName;

    private String hrName;

    private String avatar;

    private String email;

    private String companyCity;

    private String foreignName;

    private String nickName;

    private String logo;

    private String mission;

    private String companyName;
}
