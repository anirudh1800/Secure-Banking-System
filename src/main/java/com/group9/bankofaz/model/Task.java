package com.group9.bankofaz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "Task")
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "taskid", nullable = false)
	private Transaction taskid;
	
	@Column(name = "message", nullable = false)
	private String message;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tid")
	private Transaction tid;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empid")
	private InternalUser assigneeid;

	public Transaction getTaskid() {
		return taskid;
	}

	public void setTaskid(Transaction taskid) {
		this.taskid = taskid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Transaction getTid() {
		return tid;
	}

	public void setTid(Transaction tid) {
		this.tid = tid;
	}

	public InternalUser getAssigneeid() {
		return assigneeid;
	}

	public void setAssigneeid(InternalUser assigneeid) {
		this.assigneeid = assigneeid;
	}
	
	
	
	
	
	
}
