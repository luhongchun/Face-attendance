package com.attend.face.controller;


import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.attend.face.common.ServerResponse;
import com.attend.face.entity.DailyAttend;
import com.attend.face.entity.DailyStudent;
import com.attend.face.repository.AttendRepository;
import com.attend.face.repository.StudentRepository;
import com.attend.face.service.IAttendService;
import com.attend.face.service.IStudentService;

/**
 *@Title 人脸考勤服务器端
 * @Description please add description for the class 
 * @author 卢宏春
 * @email <a href="luhongchun@smart-f.cn">luhongchun@smart-f.cn</a>
 * @date 2018年6月5日 上午1:30:10 
 * @version V1.0  
 */


@RestController
@Service
@RequestMapping(value = "student")
public class StudentController {
	
	 private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	 @Autowired
	 private IAttendService iAttendService;
	 private IStudentService iStudentService;
	 private StudentRepository studentRepository;
	/**
	 * 通过学号调用学生表
	 */
    @GetMapping("test")
    public ServerResponse test() {
    	Integer id = 1;
    	 System.out.println("学号开始调用");
    	 String studentNo = "201413046";
    	 String name = "张三";
    	 ServerResponse serverResponse =   iStudentService.findAllStu(id);
    	 logger.info("serverResponse, serverResponse:"+serverResponse);
         return serverResponse;
    }
}
