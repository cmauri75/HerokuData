package com.example.dataload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;

@Controller
@SpringBootApplication
public class DataloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataloadApplication.class, args);
    }

    @Autowired
    IUserDAO userDao;

    @RequestMapping("/data/{code}")
    @ResponseBody
    String home(@PathVariable(value="code") String code) {
        StringBuffer ret = new StringBuffer("Data loaded successfully!<br/>");
        ret.append(code+"<BR>");
        userDao.findAll().forEach(x -> ret.append(x+"<BR>"));

        if (code.startsWith("add"))
            userDao.save(new User(code));

        return ret.toString();
    }



}
