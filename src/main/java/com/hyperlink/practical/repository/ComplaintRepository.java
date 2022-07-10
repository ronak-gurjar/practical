package com.hyperlink.practical.repository;

import com.hyperlink.practical.dto.ComplaintResponseDto;
import com.hyperlink.practical.entity.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Long> {

    @Modifying
    @Query("Update Complaint complaint SET complaint.status='WITHDRAWAL' where complaint.id=:complaintId")
    void deleteComplaint(@Param("complaintId") Long complaintId);

    @Query("select new com.hyperlink.practical.dto.ComplaintResponseDto(c.id,c.categoryId,c.image,c.description,c.reply,c.status,c.repliedDate) from Complaint c ")
    Page<ComplaintResponseDto> findAllComplaint(Pageable pageable);

}
