package com.attend.face.entity;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;

/**
 * Created by luhongchun on 18-6-8.
 */
@Entity
@Table(name = "daily_attend", schema = "face", catalog = "")
public class DailyAttend {
    private int id;
    private String studentNo;
    private String name;
    private Date createTime;
    private double similar;
    private String state;
    private int sign;

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
    @Column(name = "student_no")
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
	@Basic
    @Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Basic
    @Column(name = "similar")
	public double getSimilar() {
		return similar;
	}

	public void setSimilar(double similar) {
		this.similar = similar;
	}
	@Basic
    @Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Basic
    @Column(name = "sign")
	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}
}
