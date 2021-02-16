package pojo;

import lombok.Data;
import lombok.ToString;

/**
 * {@link User }Hoder
 *
 * @author lihongjian
 */
@Data
@ToString
public class UserHolder {
    private User user;

    private String name;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }
    public UserHolder(User user , String name) {
        this.user = user;
        this.name = name;
    }

}
