package com.group9.bankofaz.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

/**
 * @author Chandrani Mukherjee
 *
 */

@Entity
@Table(name = "Task")
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "taskid", nullable = false)
	private int taskid;
	
	@Column(name = "message", nullable = false)
	private String message;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tid")
	private Transaction tid;
	
	@Any (metaColumn = @Column(name = "tasktype"))
	@AnyMetaDef(idType = "int", metaType = "string",
	metaValues = {
	@MetaValue(targetEntity = InternalUser.class, value = "direct"),
	@MetaValue(targetEntity = ExternalUser.class, value = "merchant")
	})	
    @JoinColumn(name = "userid")
	private AbstractUser assigneeid;

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

	public AbstractUser getAssigneeid() {
		return assigneeid;
	}

	public void setAssigneeid(AbstractUser assigneeid) {
		this.assigneeid = assigneeid;
	}
	
}
