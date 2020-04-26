package com.attend.face.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attend.face.entity.DailyAttend;
public interface AttendRepository extends JpaRepository<DailyAttend, String> {
	DailyAttend findAllById(Integer id);
	List<DailyAttend> findAllByStudentNo(String studentNo);
	DailyAttend save(DailyAttend dailyAttend);
}
