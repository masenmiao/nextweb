package cn.corp.common.data;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

@Table(name="user")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
//    @Id 
//    @GeneratedValue
    @Id
    private long id;
    private String name;
    private String pwd;
    
    public User() {
		// TODO Auto-generated constructor stub
	}
    public User(long id, String name,String pwd) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}
    
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
       this.name = name;
    }
    public String getPwd() {
       return pwd;
    }
    public void setPwd(String pwd) {
       this.pwd = pwd;
    }
    
    @Override
    public String toString() {
       return"DemoInfo [id=" + id + ", name=" + name + "]";
    }
}
