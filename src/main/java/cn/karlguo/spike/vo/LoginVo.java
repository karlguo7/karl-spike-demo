package cn.karlguo.spike.vo;

import cn.karlguo.spike.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/*
 * @Classname LoginVo
 * @Date 2021/5/12
 * @author karlguo
 * @Description
 */
@Data
public class LoginVo {
    @NotNull
    @Length(max = 11, min = 11)
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
