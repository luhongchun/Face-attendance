package com.attend.face.controller;


import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "attend")
public class AttendController {
	
	 private static final Logger logger = LoggerFactory.getLogger(AttendController.class);
	 
	@Autowired
	 private IAttendService iAttendService;
	 private DailyAttend dailyAttend;
	 private AttendRepository attendRepository;
	 private RestTemplate restTemplate;
	 private IStudentService iStudentService;
	 private PushController pushController;
	   /**
		 * 通过ID查询考勤记录
		 */
	    @GetMapping("getToId/{id}")
	    public ServerResponse AttendToId(@PathVariable("id") Integer id) {
	    	System.out.println("ID"+id+"开始调用");
	        return iAttendService.findAllAttend(id);
	    }
	
	    /**
		 * 通过学号查询考勤记录
		 */
	    @GetMapping("getToStudentNO/{studentNo}")
	    public ServerResponse AttendToStudentNo(@PathVariable("studentNo") String studentNo) {
	    	 System.out.println("学号"+studentNo+"开始调用");
	    	 ServerResponse serverResponse = iAttendService.findAllPersion(studentNo);
	         return serverResponse;
	    }

	/*
	 * 考勤录入
	 */
	   @PostMapping("/create")
	   @ResponseBody
       public String createAttend(@RequestBody DailyAttend dailyAttend) {
		
        System.out.println("考勤信息录入开始调用");
        String name = dailyAttend.getName();
        String  studentNo   = dailyAttend.getStudentNo();
		double similar   = dailyAttend.getSimilar();
		Date createTime  = new Date();
	    logger.info("addSubmit, name:"+name+" studentNo:"+studentNo+" createTime:"+createTime+" similar"+similar);
	        try {
	            createAttend(name,studentNo,createTime, similar);
	        } catch (Exception e) {
	            logger.error("addSubmit exception:" + e);
	          }
	       // pushController.unicast(studentNo);
	        //GexinPushController.unicast(name,studentNo);//客户端推送
	        return "Attendance record is successful";
    }
	
	@Transactional
    public boolean createAttend(String name,String studentNo, Date createTime,double similar) {
        try {
            
        	DailyAttend dailyAttend = new DailyAttend();
        	dailyAttend.setName(name);
        	dailyAttend.setStudentNo(studentNo);
        	dailyAttend.setCreateTime(createTime);
        	dailyAttend.setSimilar(similar);
        	ServerResponse serverResponse = iAttendService.create(dailyAttend);
            
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
