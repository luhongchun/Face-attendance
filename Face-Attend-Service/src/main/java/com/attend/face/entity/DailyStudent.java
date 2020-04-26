package com.attend.face.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by luhongchun on 18-6-9.
 */
@Entity
@Table(name = "daily_student", schema = "face", catalog = "")
public class DailyStudent {
	private int id;
    private String studentNo;
    private String name;
   
   
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "increment")
    //@GeneratedValue(generator = "idGenerator")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "studentNo")
	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	@Basic
    @Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
