package com.jbdl63.DigitalLibrary.dto;

import com.jbdl63.DigitalLibrary.model.Author;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class RangeDataDto
{
    private Integer dataSize;
    private List<Author> data;

}
