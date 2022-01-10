package com.example.dataload;

import com.example.dataload.support.redis.Student;
import com.example.dataload.support.redis.StudentRepository;
import com.example.dataload.support.relational.User;
import com.example.dataload.support.relational.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.dataload.support.relational")
@EnableRedisRepositories(basePackages = "com.example.dataload.support.redis.repo", enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
public class DataloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataloadApplication.class, args);
    }

    @Autowired
    public void setUserDao(UserRepository userDao) {
        this.userDao = userDao;
    }

    UserRepository userDao;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    StudentRepository studentRepository;

    @RequestMapping("/data/{code}")
    @ResponseBody
    String home(@PathVariable(value = "code") String code) {
        StringBuffer ret = new StringBuffer("Data loaded successfully!<br/>");
        ret.append(code + "<hr>");
        userDao.findAll().forEach(x -> ret.append(x + "<BR>"));

        if (code.startsWith("add"))
            userDao.save(new User(code));

        ret.append("<hr>");

        studentRepository.save(new Student(code, Student.Gender.MALE));
        studentRepository.findAll().forEach(x -> ret.append("-> " + x + " " + x.getName() + "<BR>"));

        return ret.toString();
    }


}
