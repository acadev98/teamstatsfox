package com.acadev.teamstatsfox.model.request;

import java.time.LocalDate;

import com.acadev.teamstatsfox.utils.MessagesUtils;
import com.acadev.teamstatsfox.utils.RegexsUtils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PlayerRequest {

	private Long id;

	@NotBlank(message = MessagesUtils.DNI_REQUIRED)
	@Size(min = RegexsUtils.SIZE_MIN_DNI, max = RegexsUtils.SIZE_MAX_DNI, message = MessagesUtils.DNI_SIZE_NOT_VALID)
	@Pattern(regexp = RegexsUtils.REGEX_ONLY_DIGITS, message = MessagesUtils.DNI_FORMAT_NOT_VALID)
	private String dni;

	@NotEmpty(message = MessagesUtils.LASTNAME_REQUIRED)

	@NotBlank(message = MessagesUtils.LASTNAME_REQUIRED)
	@Pattern(regexp = RegexsUtils.REGEX_ONLY_LETTERS, message = MessagesUtils.LASTNAME_NOT_VALID)
	private String lastname;

	@NotEmpty(message = MessagesUtils.NAME_REQUIRED)
	@Pattern(regexp = RegexsUtils.REGEX_ONLY_LETTERS, message = MessagesUtils.NAME_NOT_VALID)
	private String name;

	@NotEmpty(message = MessagesUtils.POSITION_REQUIRED)
	@Pattern(regexp = RegexsUtils.REGEX_POSITIONS, message = MessagesUtils.POSITION_NOT_VALID)
	private String position;

	private Integer number;

	private String secondPosition;

	private Boolean active;

	private LocalDate birthday;

	private LocalDate playingSince;

}
