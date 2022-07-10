package com.hyperlink.practical.serviceImpl;

import com.hyperlink.practical.config.AuthTokenFilter;
import com.hyperlink.practical.dto.ComplaintDto;
import com.hyperlink.practical.dto.ComplaintReplyDto;
import com.hyperlink.practical.dto.ComplaintResponseDto;
import com.hyperlink.practical.entity.Complaint;
import com.hyperlink.practical.enums.ERole;
import com.hyperlink.practical.exception.AuthorizationException;
import com.hyperlink.practical.exception.NotFoundException;
import com.hyperlink.practical.repository.ComplaintRepository;
import com.hyperlink.practical.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Override
    public void saveComplaint(Long employeeId, ComplaintDto complaintDto) {
        Complaint complaints = new Complaint();
        complaints.setEmployeeId(employeeId);
        complaints.setCategoryId(complaintDto.getCategoryId());
        complaints.setDescription(complaintDto.getDescription());
        complaints.setImage(complaintDto.getImage());
        complaintRepository.save(complaints);
    }

    @Transactional
    @Override
    public void deleteComplaint(Long complaintId, HttpServletRequest request) {
        String role = authTokenFilter.getRole(request);
        if (role.equalsIgnoreCase(String.valueOf(ERole.ROLE_ADMIN))) {
            Optional<Complaint> complaintOptional = complaintRepository.findById(complaintId);
            if (complaintOptional.isPresent()) {
                Complaint complaint = complaintOptional.get();
                complaintRepository.deleteComplaint(complaint.getId());
            } else {
                throw new NotFoundException("Complaint Not Found");
            }
        }else {
            throw new AuthorizationException("only admin can delete complaint");
        }
    }

    @Override
    public Page<ComplaintResponseDto> getAllComplaint(int page, int size,HttpServletRequest request) {
        String role = authTokenFilter.getRole(request);
        if (role.equalsIgnoreCase(String.valueOf(ERole.ROLE_ADMIN))) {
            Pageable pageable = PageRequest.of(Math.max(page, 0), (size > 0) ? size : 10);
            return complaintRepository.findAllComplaint(pageable);
        }else {
            throw new AuthorizationException("only admin can view all complaints");
        }
    }

    @Transactional
    @Override
    public void replyToComplaint(Long complaintId, ComplaintReplyDto complaintDto, HttpServletRequest request) {
        String role = authTokenFilter.getRole(request);
        if (role.equalsIgnoreCase(String.valueOf(ERole.ROLE_ADMIN))) {
            Optional<Complaint> complaint1 = complaintRepository.findById(complaintId);
            if (complaint1.isPresent()) {
                Complaint complaint = complaint1.get();
                complaint.setReply(complaintDto.getReply());
                complaint.setRepliedDate(new Date());
                complaint.setStatus(complaintDto.getStatus());
                complaintRepository.save(complaint);
            }
            else {
                throw new AuthorizationException("complaint not found");
            }
        }
        else {
            throw new AuthorizationException("only admin can reply to complaint");

        }
    }
}
