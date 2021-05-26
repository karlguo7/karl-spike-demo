package cn.karlguo.spike.vo;

import cn.karlguo.spike.util.ValidatorUtil;
import cn.karlguo.spike.validator.IsMobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * @Classname IsMobileValidator
 * @Date 2021/5/26
 * @author karlguo
 * @Description
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
    private boolean required = false;

    /*
     *
     * @return void
     * @Description 获取是否为必填项
     */
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();

    }

    /*
     *
     * @return boolean
     * @Description 校验规则
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isMobile(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }

}
