package com.cxfdemo.ws.service;

import com.cxfdemo.ws.service.model.User;

import java.util.*;

/**
 * 功能：
 *
 * Created by ZhouJW on 2015/3/12 17:32.
 */
public class HelloRestService implements HelloRest {

    private Map<String, User> map = new HashMap<String, User>();

    {
        map.put("1",new User("1","liYi",1));
        map.put("2",new User("2","wangEr",2));
        map.put("3",new User("3","zhangSan",1));
    }

    @Override
    public List<User> getAllUsers() {
        Collection<User> values = map.values();
        List<User> list = new ArrayList<User>(values);
        return list;
    }

    @Override
    public User getUser(String id) {
        return map.get(id);
    }

    @Override
    public User getUser2(String id) {
        return map.get(id);
    }

    @Override
    public Boolean saveUser(User u) {
        map.put(u.getId(),u);
        return true;
    }

    @Override
    public Boolean updateUser(String id, User u) {
        map.put(id,u);
        return true;
    }

    @Override
    public Boolean deleteUser(String id) {
        map.remove(id);
        return true;
    }
}
