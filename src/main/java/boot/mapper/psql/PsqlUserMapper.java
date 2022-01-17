package boot.mapper.psql;

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
public interface PsqlUserMapper {

    User selectUserById(Integer id);
}
