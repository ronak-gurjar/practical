package com.hyperlink.practical.service;

import com.hyperlink.practical.dto.ComplaintDto;
import com.hyperlink.practical.dto.ComplaintReplyDto;
import com.hyperlink.practical.dto.ComplaintResponseDto;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

public interface ComplaintService {

    void saveComplaint(Long employeeId, ComplaintDto complaintDto);

    void deleteComplaint(Long complaintId,HttpServletRequest request);

    Page<ComplaintResponseDto> getAllComplaint(int page, int size,HttpServletRequest request);

    void replyToComplaint(Long complaintId, ComplaintReplyDto complaintDto, HttpServletRequest request);
}
