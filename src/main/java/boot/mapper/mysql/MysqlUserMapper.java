package boot.mapper.mysql;

import boot.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author bty
 * @version 2022/1/17 11:40
 * @since JDK8
 */

@Repository
public interface MysqlUserMapper {

    User selectUserById(Integer id);
}
