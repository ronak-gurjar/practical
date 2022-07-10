package com.hyperlink.practical.dto;

import com.hyperlink.practical.enums.ComplaintStatus;
import com.hyperlink.practical.service.ComplaintService;

import javax.xml.crypto.Data;
import java.util.Date;

public class ComplaintReplyDto {

    private String reply;
    private ComplaintStatus status;
    private Date repliedDate;

    public ComplaintReplyDto(String reply, ComplaintStatus status, Date repliedDate) {
        this.reply = reply;
        this.status = status;
        this.repliedDate = repliedDate;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public Date getRepliedDate() {
        return repliedDate;
    }

    public void setRepliedDate(Date repliedDate) {
        this.repliedDate = repliedDate;
    }
}
