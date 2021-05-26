package cn.karlguo.spike.mapper;

import cn.karlguo.spike.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author karlguo
 * @since 2021-05-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
