package com.attend.face.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attend.face.common.ServerResponse;
import com.attend.face.controller.AttendController;
import com.attend.face.entity.DailyAttend;
import com.attend.face.entity.DailyStudent;
import com.attend.face.repository.AttendRepository;
import com.attend.face.repository.StudentRepository;
import com.attend.face.service.IAttendService;
import com.attend.face.service.IStudentService;

@Service("iStudentService")
public class StudentService implements IStudentService {
	
	private static final Logger logger           = LoggerFactory.getLogger(AttendController.class);

    @Autowired
    private AttendRepository attendRepository;
    private StudentRepository studentRepository;
   
/**
 * 通过学号查询考勤记录
 *//*
	@Override
	public ServerResponse findAllName(String studentNo) {
		// TODO Auto-generated method stub
		try {
			System.out.println("开始通过学号获取学生信息表信息");
            List<DailyStudent> studentList = studentRepository.findAllByStudentNo(studentNo);
            if (studentList != null) {
            	  logger.info("studentList, studentList:"+studentList.get(0).getStudentNo()+" "+studentList.get(0).getName()+" "+studentList.get(0).getNational()+" "+studentList.get(0).getSex()+" "+studentList.get(0).getClimate()+" "+studentList.get(0).getCollege()+" "+studentList.get(0).getMajor());
                  return ServerResponse.createBySuccess(studentList);
               } else {
            		return ServerResponse.createByErrorMessage("Fail to get students' attendance!");
                   }
            } catch (Exception e) {
            	e.printStackTrace();
            	return ServerResponse.createByErrorMessage("Failure to get students' attendance, internal errors");
            }
		}*/

@Override
public ServerResponse findAllStu(Integer id) {
	 // TODO Auto-generated method stub
	 System.out.println("开始通过学号获取学生信息表信息");
	 DailyStudent studentList = studentRepository.findAllById(id);
	 logger.info("studentList, studentList:"+studentList.getStudentNo()+" "+studentList.getName());
	 return ServerResponse.createBySuccess(studentList);
}
}
