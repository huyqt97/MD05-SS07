package com.ra.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateOrder {
	private String address;
	private String phone;
	private String note;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date date;
}
