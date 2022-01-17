package boot;

import boot.config.MyConfig;

import boot.mapper.mysql.MysqlUserMapper;
import boot.mapper.psql.PsqlUserMapper;
import boot.model.User;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * @author bty
 * @version 2022/1/16 12:06
 * @since JDK8
 */
@RestController
@SpringBootApplication
public class MainApplication {

    @Autowired
    private MyConfig config;

    @Autowired
    private MysqlUserMapper mysqlUserMapper;

    @Autowired
    private PsqlUserMapper psqlUserMapper;


    @GetMapping("/")
    public User hello() {
        System.out.println("inside");
        return config.getUser();
    }


    @GetMapping("/mysql")
    public User hello2() {
        System.out.println("inside");
        final User user = mysqlUserMapper.selectUserById(1);
        return user;
    }

    @GetMapping("/psql")
    public User hello3() {
        System.out.println("inside");
        final User user = psqlUserMapper.selectUserById(1);
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
