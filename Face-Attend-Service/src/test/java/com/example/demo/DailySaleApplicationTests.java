package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.attend.face.entity.DailyAttend;
import com.attend.face.repository.AttendRepository;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DailySaleApplicationTests {
	@Autowired
	private AttendRepository attendRepository;
	//@Test
	public void contextLoads() {
		int id = 201413046;
		System.out.println("开始获取数据");
}
}
