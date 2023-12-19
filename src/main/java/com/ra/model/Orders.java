package com.ra.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String address;
	
	private String phone;
	
	private String note;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date created;
	
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;
	
}
