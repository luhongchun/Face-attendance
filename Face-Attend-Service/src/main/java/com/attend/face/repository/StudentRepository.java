package com.attend.face.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.attend.face.entity.DailyAttend;
import com.attend.face.entity.DailyStudent;

public interface StudentRepository extends JpaRepository<DailyStudent, String> {
	DailyStudent findAllById(Integer id);
	List<DailyStudent> findAllByStudentNo(String studentNo);
}
