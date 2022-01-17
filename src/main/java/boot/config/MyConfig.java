package boot.config;

import boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author bty
 * @version 2022/1/16 23:08
 * @since JDK8
 */
@Configuration
public class MyConfig {

    //构造器注入
    private User user;


    public MyConfig(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String out(){
        return user.toString();
    }

}
