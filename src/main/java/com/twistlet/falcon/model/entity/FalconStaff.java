package com.twistlet.falcon.model.entity;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FalconStaff generated by hbm2java
 */
@Entity
@Table(name="falcon_staff"
)
public class FalconStaff  implements java.io.Serializable {


     private Integer id;
     private FalconUser falconUser;
     private String name;
     private String nric;
     private String hpTel;
     private String email;
     private Boolean sendSms;
     private Boolean sendEmail;
     private Boolean valid;
     private Set<FalconAppointment> falconAppointments = new HashSet<FalconAppointment>(0);

    public FalconStaff() {
    }

	
    public FalconStaff(FalconUser falconUser, String name, String nric) {
        this.falconUser = falconUser;
        this.name = name;
        this.nric = nric;
    }
    public FalconStaff(FalconUser falconUser, String name, String nric, String hpTel, String email, Boolean sendSms, Boolean sendEmail, Boolean valid, Set<FalconAppointment> falconAppointments) {
       this.falconUser = falconUser;
       this.name = name;
       this.nric = nric;
       this.hpTel = hpTel;
       this.email = email;
       this.sendSms = sendSms;
       this.sendEmail = sendEmail;
       this.valid = valid;
       this.falconAppointments = falconAppointments;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="admin", nullable=false)
    public FalconUser getFalconUser() {
        return this.falconUser;
    }
    
    public void setFalconUser(FalconUser falconUser) {
        this.falconUser = falconUser;
    }

    
    @Column(name="name", nullable=false, length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="nric", nullable=false, length=20)
    public String getNric() {
        return this.nric;
    }
    
    public void setNric(String nric) {
        this.nric = nric;
    }

    
    @Column(name="hp_tel", length=50)
    public String getHpTel() {
        return this.hpTel;
    }
    
    public void setHpTel(String hpTel) {
        this.hpTel = hpTel;
    }

    
    @Column(name="email", length=100)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="send_sms")
    public Boolean getSendSms() {
        return this.sendSms;
    }
    
    public void setSendSms(Boolean sendSms) {
        this.sendSms = sendSms;
    }

    
    @Column(name="send_email")
    public Boolean getSendEmail() {
        return this.sendEmail;
    }
    
    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    
    @Column(name="valid")
    public Boolean getValid() {
        return this.valid;
    }
    
    public void setValid(Boolean valid) {
        this.valid = valid;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="falconStaff")
    public Set<FalconAppointment> getFalconAppointments() {
        return this.falconAppointments;
    }
    
    public void setFalconAppointments(Set<FalconAppointment> falconAppointments) {
        this.falconAppointments = falconAppointments;
    }




}


