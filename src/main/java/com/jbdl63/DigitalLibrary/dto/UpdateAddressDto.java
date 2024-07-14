package com.jbdl63.DigitalLibrary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateAddressDto implements Serializable
{
    @NotNull(message = "Auhtor id should not ne null in updateDto")
    private Integer id;

    @NotBlank(message = "Author address should not be null or blank")
    private String address;

}
