package com.claudiocarige.portfoliclaudio.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fieldname;
	private String message;

}
