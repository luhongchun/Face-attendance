package com.attend.face.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attend.face.common.ServerResponse;
import com.attend.face.controller.AttendController;
import com.attend.face.controller.GexinPushController;
import com.attend.face.entity.DailyAttend;
import com.attend.face.entity.DailyStudent;
import com.attend.face.repository.AttendRepository;
import com.attend.face.repository.StudentRepository;
import com.attend.face.service.IAttendService;

@Service("iAttendService")
public class AttendService implements IAttendService {
	
	private static final Logger logger           = LoggerFactory.getLogger(AttendController.class);

    @Autowired
    private AttendRepository attendRepository;
    private StudentRepository studentinformationRepository;
    /**
     * 通过ID查询考勤记录
     */
	@Override
	public ServerResponse findAllAttend(Integer id) {
		// TODO Auto-generated method stub
		try {
			System.out.println("通过ID开始获取");
            DailyAttend dailyAttend = attendRepository.findAllById(id);
            System.out.println(dailyAttend);
            if (dailyAttend != null) {
            	System.out.println(dailyAttend.getName());
            	
                return ServerResponse.createBySuccess(dailyAttend);
            } else {
                return ServerResponse.createByErrorMessage("Fail to get students' attendance!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("Failure to get students' attendance, internal errors");
        }
	}

/**
 * 通过学号查询考勤记录
 */
	@Override
	public ServerResponse findAllPersion(String studentNo) {
		// TODO Auto-generated method stub
		try {
			System.out.println("开始获取学生考勤列表");
            List<DailyAttend> studentList = attendRepository.findAllByStudentNo(studentNo);
            if (studentList != null) {
            	  logger.info("studentList, studentList:"+studentList);
            	  System.out.println(studentList.get(0).getId()+" "+studentList.get(0).getName()+" "+studentList.get(0).getStudentNo()+" "+studentList.get(0).getSimilar()+" "+studentList.get(0).getState());
                  return ServerResponse.createBySuccess(studentList);
               } else {
            		return ServerResponse.createByErrorMessage("Fail to get students' attendance!");
                   }
            } catch (Exception e) {
            	e.printStackTrace();
            	return ServerResponse.createByErrorMessage("Failure to get students' attendance, internal errors");
            }
		}

/*
 * 考勤录入
 * @see com.attend.face.service.IAttendService#create()
 */
@Override
public ServerResponse create(DailyAttend dailyAttend) {
	// TODO Auto-generated method stub
	System.out.println("创建考勤信息");
	 String studentNo = dailyAttend.getStudentNo();
	 String name = dailyAttend.getName();
	 Date createTime = dailyAttend.getCreateTime();
	 List<DailyAttend> studentListTostudentNo = attendRepository.findAllByStudentNo(studentNo);
	 //List<DailyStudent> studentListTostudentNo = studentinformationRepository.findAllByStudentNo(studentNo);
	 System.out.println("studentListTostudentNo"+studentListTostudentNo);
	 if(studentListTostudentNo != null) {
		 Date lastTime = studentListTostudentNo.get(studentListTostudentNo.size() - 1).getCreateTime();
		 SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		 String fromDate = simpleFormat.format(lastTime);
		 String toDate = simpleFormat.format(createTime);
		 long from = 0;
		try {
			from = simpleFormat.parse(fromDate).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 long to = 0;
		try {
			to = simpleFormat.parse(toDate).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 int days = (int) ((to - from)/(1000 * 60 * 60 * 24));
		 if(days == 0) {
			 return ServerResponse.createByErrorMessage("The student today Have taken the attendance!");
		 }
	 }
	try {
		System.out.println("开始录入考勤信息");
        DailyAttend studentList = attendRepository.save(dailyAttend);
        if (studentList != null) {
        	  logger.info("addSubmit, id:"+studentList.getId()+" name:"+studentList.getName()+" studentNo:"+studentList.getStudentNo()+" similar"+studentList.getSimilar());
        	  GexinPushController.unicast(name,studentNo);//客户端推送
        	  return ServerResponse.createBySuccess(studentList);
           } else {
        		return ServerResponse.createByErrorMessage("Attendance records fail!");
               }
        } catch (Exception e) {
        	e.printStackTrace();
        	return ServerResponse.createByErrorMessage("Failure in attendance record and internal error");
        }
}
}
