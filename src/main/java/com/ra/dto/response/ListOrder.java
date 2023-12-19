package com.ra.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.model.Users;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ListOrder {
	
	private Long id;
	
	private String address;
	
	private String phone;
	
	private String note;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date created;
	
	private Boolean status;
	
	private String username;
	
}
