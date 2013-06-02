package com.twistlet.falcon.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FalconFeedback generated by hbm2java
 */
@Entity
@Table(name="falcon_feedback"
)
public class FalconFeedback  implements java.io.Serializable {


     private Integer id;
     private String feedbackType;
     private String content;
     private String emailFrom;

    public FalconFeedback() {
    }

    public FalconFeedback(String feedbackType, String content, String emailFrom) {
       this.feedbackType = feedbackType;
       this.content = content;
       this.emailFrom = emailFrom;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="feedback_type", length=15)
    public String getFeedbackType() {
        return this.feedbackType;
    }
    
    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    
    @Column(name="content", length=1000)
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    
    @Column(name="email_from", length=100)
    public String getEmailFrom() {
        return this.emailFrom;
    }
    
    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }




}


