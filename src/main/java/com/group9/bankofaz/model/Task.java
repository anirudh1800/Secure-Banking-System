package com.group9.bankofaz.model;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.group9.bankofaz.interceptor.ILogs;

/**
 * @author Chandrani Mukherjee
 *
 */

@Entity
@Table(name = "task")
@DynamicUpdate
@SelectBeforeUpdate 
public class Task implements ILogs{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskid", nullable = false)
	private int taskid;

	@Column(name = "message", nullable = false)
	private String message;

	@Column(name = "status")
	private String status;

	@OneToOne
	@JoinColumn(name = "tid")
	private Transaction tid;

	@Column(name = "assigneeid")
	private int assigneeid;

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
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

	public int getAssigneeid() {
		return assigneeid;
	}
	
	public void setAssigneeid(int assigneeid) {
		this.assigneeid = assigneeid;
	}
	
	@Transient
	@Override
	public Long getId() {
		return Long.valueOf(this.taskid);
	}

	@Transient
	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" task ").append(" taskid :" ).append(taskid)
		.append(" message : ").append(message)
		.append(" status : ").append(status)
		.append(" tid : ").append(tid.getTid())
		.append(" assigneeid : ").append(assigneeid);

		return sb.toString();
	}
}
