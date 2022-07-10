package com.hyperlink.practical.controller;

import com.hyperlink.practical.dto.*;
import com.hyperlink.practical.service.CategoryService;
import com.hyperlink.practical.service.ComplaintService;
import com.hyperlink.practical.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/complaint/")
public class ComplaintsController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/employee/{id}")
     public ResponseDto saveComplaintsDetail(@PathVariable("id") Long employeeId, @RequestBody @Validated ComplaintDto complaintDto) {
        complaintService.saveComplaint(employeeId,complaintDto);
        return ResponseDto.success(Constants.SUCCESS);
    }

    @GetMapping("/list")
    public Page<ComplaintResponseDto> getAllComplaintsWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,HttpServletRequest request){
      return  complaintService.getAllComplaint(page,size,request);
    }

    @GetMapping("/reply/{id}")
    public void replyComplaint(@PathVariable("id") Long complaintId, @RequestBody @Validated ComplaintReplyDto complaintDto, HttpServletRequest request){
        complaintService.replyToComplaint(complaintId,complaintDto,request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto deleteComplaint(@PathVariable("id") Long complaintId,HttpServletRequest request) {
        complaintService.deleteComplaint(complaintId,request);
        return ResponseDto.success(Constants.DELETED);
    }

}
