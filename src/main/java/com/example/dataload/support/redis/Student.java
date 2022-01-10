package com.example.dataload.support.redis;

import org.springframework.data.redis.core.RedisHash;

import org.springframework.data.annotation.Id;
import java.io.Serializable;

@RedisHash("Student")
public class Student implements Serializable {

    public enum Gender {
        MALE, FEMALE
    }


    public Student(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public Student() {
    }

    @Id
    private String name;

    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}