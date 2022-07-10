package com.hyperlink.practical.dto;

import com.hyperlink.practical.enums.ComplaintStatus;

import java.util.Date;

public class ComplaintResponseDto {
    private Long id;
    private Long categoryId;
    private String image;
    private String description;
    private String reply;
  private ComplaintStatus status;
  private Date repliedDate;

    public ComplaintResponseDto(Long id, Long categoryId, String image, String description, String reply, ComplaintStatus status, Date repliedDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.image = image;
        this.description = description;
        this.reply = reply;
        this.status = status;
        this.repliedDate = repliedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


}
